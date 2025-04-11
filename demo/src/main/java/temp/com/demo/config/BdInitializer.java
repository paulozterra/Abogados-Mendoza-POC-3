package temp.com.demo.config;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import temp.com.demo.model.*;
import temp.com.demo.repository.*;

@Configuration
public class BdInitializer {

    @Bean
    CommandLineRunner initAbogadosClientes(
            ClientRepository clientRepository,
            LawyerRepository lawyerRepository,
            CaseRepository caseRepository,
            ReceiptRepository receiptRepository
    ) {
        return args -> {
            // Insertar 2 clientes si no existen
            if (clientRepository.count() == 0) {
                IntStream.rangeClosed(1, 2).forEach(i -> {
                    Client client = Client.builder()
                            .name("Cliente " + i)
                            .dni("1000000" + i)
                            .email("cliente" + i + "@example.com")
                            .phoneNumber("99900000" + i)
                            .build();
                    clientRepository.save(client);
                });
                System.out.println("✅ Clientes insertados");
            }

            // Insertar 2 abogados si no existen
            if (lawyerRepository.count() == 0) {
                IntStream.rangeClosed(1, 2).forEach(i -> {
                    Lawyer lawyer = Lawyer.builder()
                            .name("Abogado " + i)
                            .dni("2000000" + i)
                            .mailBox("casilla" + i + "@correo.com")
                            .phoneNumber("98800000" + i)
                            .build();
                    lawyerRepository.save(lawyer);
                });
                System.out.println("✅ Abogados insertados");
            }

            // Crear un caso y un recibo si no existen
            if (caseRepository.count() == 0 && receiptRepository.count() == 0) {
                List<Client> clientes = clientRepository.findAll();
                List<Lawyer> abogados = lawyerRepository.findAll();

                if (!clientes.isEmpty() && !abogados.isEmpty()) {
                    Client client = clientes.get(0);
                    Lawyer lawyer = abogados.get(0);

                    Case caso = Case.builder()
                            .caseNumber("CASO-001")
                            .status("Pendiente")
                            .client(client)
                            .lawyer(lawyer)
                            .build();
                    caseRepository.save(caso);

                    Receipt recibo = Receipt.builder()
                            .receiptNumber("R" + System.currentTimeMillis())
                            .status("Emitido")
                            .client(client)
                            .lawyer(lawyer)
                            .subTotal(100.0)
                            .total(118.0)
                            .build();
                    receiptRepository.save(recibo);

                    System.out.println("✅ Caso y recibo creados");
                } else {
                    System.out.println("⚠️ No hay clientes o abogados para asignar al caso y recibo.");
                }
            }
        };
    }
}
