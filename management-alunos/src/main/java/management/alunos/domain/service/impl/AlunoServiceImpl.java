package management.alunos.domain.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import management.alunos.domain.model.dto.AlunoDTO;
import management.alunos.domain.model.dto.AlunoExcluidoDTO;
import management.alunos.domain.model.dto.AlunoNotaDTO;
import management.alunos.domain.model.dto.AlunoRequestDTO;
import management.alunos.domain.service.AlunoService;
import management.alunos.infra.repository.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository repository;

    @Override
    public List<AlunoDTO> findAll() {
        log.info("Buscando todos os alunos.");
        List<AlunoDTO> alunos = repository.findAll().stream().map(aluno ->
                AlunoDTO.builder()
                        .id(aluno.getId())
                        .nome(aluno.getNome())
                        .email(aluno.getNome())
                        .cpf(aluno.getCpf())
                        .dataNascimento(aluno.getDataNascimento())
                        .status(aluno.getStatus().name())
                        .nota(aluno.getNota())
                        .contatoEmergencia(aluno.getContatoEmergencia())
                        .build()
        ).toList();
        log.info("Total de alunos encontrados: {}", alunos.size());
        return alunos;
    }

    @Override
    public AlunoDTO findById(Long id) {
        log.info("Buscando aluno com ID: {}", id);
        AlunoDTO aluno = repository.findById(id).orElseThrow(() -> {
            log.error("Aluno com ID {} não encontrado.", id);
            return new RuntimeException("Aluno not found.");
        }).toDTO();
        log.info("Aluno encontrado: {}", aluno);
        return aluno;
    }

    @Override
    public AlunoDTO create(AlunoRequestDTO requestDTO) throws ParseException {
        log.info("Criando novo aluno: {}", requestDTO);
        AlunoDTO alunoSaved = repository.save(requestDTO.toEntity()).toDTO();
        log.info("Aluno criado com sucesso: {}", alunoSaved);
        return alunoSaved;
    }

    @Override
    public AlunoDTO update(Long id, AlunoRequestDTO requestDTO) {
        log.info("Atualizando aluno com ID: {}", id);
        var alunoDB = repository.findById(id).orElseThrow(() -> {
            log.error("Aluno com ID {} não encontrado.", id);
            return new RuntimeException("Aluno not found");
        });
        BeanUtils.copyProperties(requestDTO, alunoDB);
        AlunoDTO alunoUpdated = repository.save(alunoDB).toDTO();
        log.info("Aluno atualizado com sucesso: {}", alunoUpdated);
        return alunoUpdated;
    }

    @Override
    public AlunoExcluidoDTO delete(Long id) {
        log.info("Excluindo aluno com ID: {}", id);
        AlunoExcluidoDTO alunoExcluidoDTO = new AlunoExcluidoDTO();
        var alunoDB = repository.findById(id).orElseThrow(() -> {
            log.error("Aluno com ID {} não encontrado.", id);
            return new RuntimeException("Aluno not found");
        });
        if (Objects.nonNull(alunoDB)) {
            repository.delete(alunoDB);
            alunoExcluidoDTO.setStatus("Aluno Excluído com sucesso!");
            log.info("Aluno com ID {} excluído com sucesso.", id);
        }
        return alunoExcluidoDTO;
    }

    @Override
    public AlunoNotaDTO findByNota(Long id) {
        log.info("Buscando nota do aluno com ID: {}", id);
        Double nota = repository.findById(id).orElseThrow(() -> {
            log.error("Aluno com ID {} não encontrado.", id);
            return new RuntimeException("Aluno not found");
        }).getNota();
        AlunoNotaDTO alunoNotaDTO = AlunoNotaDTO.builder().nota(nota).build();
        log.info("Nota do aluno com ID {}: {}", id, nota);
        return alunoNotaDTO;
    }
}
