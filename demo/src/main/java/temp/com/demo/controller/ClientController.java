package temp.com.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import temp.com.demo.repository.ClientRepository;
import temp.com.demo.model.Client;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operaciones relacionadas con clientes")
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping
    @Operation(summary = "Listar todos los clientes", description = "Obtiene una lista completa de todos los clientes registrados.")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Registra un nuevo cliente en la base de datos.")
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Busca un cliente específico usando su ID.")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
