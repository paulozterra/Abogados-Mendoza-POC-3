package temp.com.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import lombok.RequiredArgsConstructor;
import temp.com.demo.model.Lawyer;
import temp.com.demo.repository.LawyerRepository;

@RestController
@RequestMapping("/lawyers")
@RequiredArgsConstructor
@Tag(name = "Abogados", description = "Operaciones relacionadas con los abogados")
public class LawyerController {

    private final LawyerRepository lawyerRepository;

    @GetMapping
    @Operation(summary = "Obtener todos los abogados", description = "Devuelve una lista con todos los abogados registrados.")
    public List<Lawyer> getAllLawyers() {
        return lawyerRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo abogado", description = "Crea un nuevo abogado con los datos proporcionados.")
    public Lawyer createLawyer(@RequestBody Lawyer lawyer) {
        return lawyerRepository.save(lawyer);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar abogado por ID", description = "Obtiene los datos de un abogado específico por su ID.")
    public ResponseEntity<?> getLawyerById(@PathVariable Long id) {
        return lawyerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
