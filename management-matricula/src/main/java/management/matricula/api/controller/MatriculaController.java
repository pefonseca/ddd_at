package management.matricula.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.matricula.domain.model.dto.MatriculaDTO;
import management.matricula.domain.model.dto.MatriculaExcluidaDTO;
import management.matricula.domain.model.dto.MatriculaRequestDTO;
import management.matricula.domain.model.entity.Matricula;
import management.matricula.domain.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/matricula")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService service;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> findAll() {
        var listMatricula = service.findAll();
        return ResponseEntity.ok(listMatricula);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MatriculaDTO> findById(@PathVariable(value = "id") Long id) {
        var matriculaDB = service.findById(id);
        return ResponseEntity.ok(matriculaDB);
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> create(@Valid @RequestBody MatriculaRequestDTO matriculaRequestDTO) throws ParseException {
        var matriculaDB = service.create(matriculaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MatriculaDTO> update(@PathVariable(value = "id") Long id,
                                               @Valid @RequestBody MatriculaRequestDTO matriculaRequestDTO) {
        var matriculaUpdate = service.update(id, matriculaRequestDTO);
        return ResponseEntity.ok(matriculaUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MatriculaExcluidaDTO> delete(@PathVariable(value = "id") Long id) {
        var matriculaDelete = service.delete(id);
        return ResponseEntity.ok(matriculaDelete);
    }

}
