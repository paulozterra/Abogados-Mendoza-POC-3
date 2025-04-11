package temp.com.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import temp.com.demo.dto.NuevoCasoRequest;
import temp.com.demo.model.*;
import temp.com.demo.service.*;

import java.util.Optional;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Operaciones centrales del sistema, como registrar casos y generar recibos.")
public class DashboardController {

    private final ClientService clientService;
    private final CaseService caseService;
    private final ReceiptService receiptService;
    private final LawyerService lawyerService;

    @PostMapping("/nuevo-caso")
    @Operation(
        summary = "Registrar nuevo caso y generar recibo",
        description = "Permite registrar un nuevo caso. Si el cliente no existe, se crea. Se asigna un abogado (ID fijo) y se genera un recibo por la revisión."
    )
    public ResponseEntity<?> registrarCasoConRecibo(@RequestBody NuevoCasoRequest request) {
        Client cliente;

        // Validar que caseNumber no esté vacío
        if (request.getCaseNumber() == null || request.getCaseNumber().isBlank()) {
            return ResponseEntity.badRequest().body("El número de caso no puede estar vacío");
        }

        // Verificar si el cliente ya existe por DNI
        Optional<Client> clienteExistente = clientService.buscarPorDni(request.getDni());
        if (clienteExistente.isPresent()) {
            cliente = clienteExistente.get();
        } else {
            cliente = Client.builder()
                    .name(request.getNombre())
                    .dni(request.getDni())
                    .email(request.getEmail())
                    .phoneNumber(request.getTelefono())
                    .build();
            cliente = clientService.save(cliente);
        }

        // Obtener abogado con ID fijo (1)
        Optional<Lawyer> abogadoOpt = lawyerService.findById(1L);
        if (abogadoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Abogado con ID 1 no encontrado");
        }
        Lawyer abogado = abogadoOpt.get();

        // Crear el caso
        Case nuevoCaso = Case.builder()
                .caseNumber(request.getCaseNumber())
                .status("Pendiente")
                .client(cliente)
                .lawyer(abogado)
                .build();
        nuevoCaso = caseService.save(nuevoCaso);

        // Generar recibo por la revisión
        Receipt recibo = Receipt.builder()
                .client(cliente)
                .lawyer(abogado)
                .status("Emitido")
                .receiptNumber("R" + System.currentTimeMillis())
                .subTotal(100.0)
                .total(118.0)
                .build();
        recibo = receiptService.save(recibo);

         return ResponseEntity.ok(nuevoCaso);
    }
}
