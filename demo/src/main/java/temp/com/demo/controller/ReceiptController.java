package temp.com.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import lombok.RequiredArgsConstructor;
import temp.com.demo.model.Receipt;
import temp.com.demo.repository.ReceiptRepository;

@RestController
@RequestMapping("/receipts")
@RequiredArgsConstructor
@Tag(name = "Recibos", description = "Operaciones relacionadas con recibos de servicios legales")
public class ReceiptController {

    private final ReceiptRepository receiptRepository;

    @GetMapping
    @Operation(summary = "Listar todos los recibos", description = "Devuelve una lista con todos los recibos registrados.")
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo recibo", description = "Crea un nuevo recibo con los datos proporcionados.")
    public Receipt createReceipt(@RequestBody Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener recibo por ID", description = "Obtiene los detalles de un recibo específico usando su ID.")
    public ResponseEntity<?> getReceiptById(@PathVariable Long id) {
        return receiptRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
