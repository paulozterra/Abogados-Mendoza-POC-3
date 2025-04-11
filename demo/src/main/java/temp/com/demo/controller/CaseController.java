package temp.com.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import lombok.RequiredArgsConstructor;
import temp.com.demo.model.Case;
import temp.com.demo.repository.CaseRepository;

@RestController
@RequestMapping("/cases")
@RequiredArgsConstructor
@Tag(name = "Casos", description = "Operaciones relacionadas con casos legales")
public class CaseController {

    private final CaseRepository caseRepository;

    @GetMapping
    @Operation(summary = "Obtener todos los casos", description = "Devuelve una lista de todos los casos registrados en el sistema.")
    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo caso", description = "Permite registrar un nuevo caso legal.")
    public Case createCase(@RequestBody Case caso) {
        return caseRepository.save(caso);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un caso por ID", description = "Devuelve los detalles de un caso específico dado su ID.")
    public ResponseEntity<?> getCaseById(@PathVariable Long id) {
        return caseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
