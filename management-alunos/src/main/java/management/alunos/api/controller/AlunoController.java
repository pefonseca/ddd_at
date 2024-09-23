package management.alunos.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import management.alunos.domain.model.dto.AlunoDTO;
import management.alunos.domain.model.dto.AlunoExcluidoDTO;
import management.alunos.domain.model.dto.AlunoNotaDTO;
import management.alunos.domain.model.dto.AlunoRequestDTO;
import management.alunos.domain.service.AlunoService;
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

@Slf4j
@RestController
@RequestMapping("/api/v1/aluno")
@RequiredArgsConstructor
public class AlunoController {

    public final AlunoService service;

    @Operation(summary = "Buscar todos os alunos", description = "Retorna uma lista de todos os alunos cadastrados.")
    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        log.info("Buscando todos os alunos.");
        var listAlunos = service.findAll();
        log.info("Total de alunos encontrados: {}", listAlunos.size());
        return ResponseEntity.ok(listAlunos);
    }

    @Operation(summary = "Buscar aluno por ID", description = "Retorna os detalhes de um aluno específico pelo seu ID.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable(value = "id") Long id) {
        log.info("Buscando aluno com ID: {}", id);
        var aluno = service.findById(id);
        log.info("Aluno encontrado: {}", aluno);
        return ResponseEntity.ok(aluno);
    }

    @Operation(summary = "Buscar nota do aluno", description = "Retorna a nota de um aluno específico pelo seu ID.")
    @GetMapping(value = "/nota/{id}")
    public ResponseEntity<AlunoNotaDTO> findByNota(@PathVariable(value = "id") Long id) {
        log.info("Buscando nota do aluno com ID: {}", id);
        var alunoNota = service.findByNota(id);
        log.info("Nota do aluno encontrada: {}", alunoNota);
        return ResponseEntity.ok(alunoNota);
    }

    @Operation(summary = "Criar novo aluno", description = "Cria um novo aluno com as informações fornecidas.")
    @PostMapping
    public ResponseEntity<AlunoDTO> create(@Valid @RequestBody AlunoRequestDTO alunoRequestDTO) throws ParseException {
        log.info("Criando novo aluno: {}", alunoRequestDTO);
        var alunoSaved = service.create(alunoRequestDTO);
        log.info("Aluno criado com sucesso: {}", alunoSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSaved);
    }

    @Operation(summary = "Atualizar aluno", description = "Atualiza as informações de um aluno existente pelo seu ID.")
    @PutMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody AlunoRequestDTO alunoRequestDTO) {
        log.info("Atualizando aluno com ID: {}", id);
        var alunoUpdate = service.update(id, alunoRequestDTO);
        log.info("Aluno atualizado com sucesso: {}", alunoUpdate);
        return ResponseEntity.ok(alunoUpdate);
    }

    @Operation(summary = "Excluir aluno", description = "Exclui um aluno pelo seu ID e retorna informações sobre a exclusão.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AlunoExcluidoDTO> delete(@PathVariable(value = "id") Long id) {
        log.info("Excluindo aluno com ID: {}", id);
        var alunoDelete = service.delete(id);
        log.info("Aluno excluído com sucesso: {}", alunoDelete);
        return ResponseEntity.ok(alunoDelete);
    }
}
