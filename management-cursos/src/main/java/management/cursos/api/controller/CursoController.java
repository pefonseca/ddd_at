package management.cursos.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.cursos.domain.model.dto.AlunoNotaDTO;
import management.cursos.domain.model.dto.CursoDTO;
import management.cursos.domain.model.dto.CursoExcluidoDTO;
import management.cursos.domain.model.dto.CursoRequestDTO;
import management.cursos.domain.service.CursoService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/curso")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService service;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> findAll() {
        var listCurso = service.findAll();
        return ResponseEntity.ok(listCurso);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable(value = "id") Long id) {
        var cursoDTO = service.findById(id);
        return ResponseEntity.ok(cursoDTO);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> create(@Valid @RequestBody CursoRequestDTO cursoRequestDTO) {
        var cursoDTO = service.create(cursoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CursoDTO> update(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody CursoRequestDTO cursoRequestDTO) {
        var cursoUpdate = service.update(id, cursoRequestDTO);
        return ResponseEntity.ok(cursoUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CursoExcluidoDTO> delete(@PathVariable(value = "id") Long id) {
        var userDelete = service.delete(id);
        return ResponseEntity.ok(userDelete);
    }

    @GetMapping(value = "/relatorio_notas/{id}")
    public ResponseEntity<List<AlunoNotaDTO>> getRelatorioNotas(@PathVariable(value = "id") Long id) {
        var relatorio = service.findNotas(id);
        return ResponseEntity.ok(relatorio);
    }

}
