package management.matricula.domain.service.impl;

import lombok.RequiredArgsConstructor;
import management.matricula.domain.model.dto.AlunoCadastroCursoDTO;
import management.matricula.domain.model.dto.MatriculaDTO;
import management.matricula.domain.model.dto.MatriculaExcluidaDTO;
import management.matricula.domain.model.dto.MatriculaRequestDTO;
import management.matricula.domain.producer.CursoRequestProducer;
import management.matricula.domain.service.MatriculaService;
import management.matricula.infra.config.feign.AlunoFeignClient;
import management.matricula.infra.config.feign.CursoFeignClient;
import management.matricula.infra.repository.MatriculaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository repository;
    private final AlunoFeignClient alunoFeignClient;
    private final CursoFeignClient cursoFeignClient;
    private final CursoRequestProducer producer;

    /**
     * @return
     */
    @Override
    public List<MatriculaDTO> findAll() {
        return repository.findAll().stream().map(matricula -> {

            var alunoResponse = alunoFeignClient.findById(matricula.getAlunoId()).getBody();
            var cursoResponse = cursoFeignClient.findById(matricula.getCursoId()).getBody();

            return MatriculaDTO.builder()
                               .id(matricula.getId())
                               .aluno(alunoResponse)
                               .curso(cursoResponse)
                               .dataMatricula(matricula.getDataMatricula())
                               .ativo(matricula.isAtivo())
                               .build();
        }).toList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MatriculaDTO findById(Long id) {
        var matriculaDB = repository.findById(id).orElseThrow(() -> new RuntimeException("Matricula not found"));

        var alunoResponse = alunoFeignClient.findById(matriculaDB.getAlunoId()).getBody();
        var cursoResponse = cursoFeignClient.findById(matriculaDB.getCursoId()).getBody();

        return MatriculaDTO.builder()
                           .id(matriculaDB.getId())
                           .aluno(alunoResponse)
                           .curso(cursoResponse)
                           .dataMatricula(matriculaDB.getDataMatricula())
                           .ativo(matriculaDB.isAtivo())
                           .build();
    }

    /**
     * @param matriculaRequestDTO
     * @return
     */
    @Override
    public MatriculaDTO create(MatriculaRequestDTO matriculaRequestDTO) throws ParseException {

        var matriculaDB = repository.save(matriculaRequestDTO.toEntity());

        var alunoResponse = alunoFeignClient.findById(matriculaDB.getAlunoId()).getBody();

        producer.integration(AlunoCadastroCursoDTO.builder()
                                                  .alunoId(matriculaRequestDTO.getAlunoId())
                                                  .cursoId(matriculaRequestDTO.getCursoId())
                                                  .nota(alunoResponse.getNota())
                                                  .build());

        var cursoResponse = cursoFeignClient.findById(matriculaDB.getCursoId()).getBody();

        return MatriculaDTO.builder()
                           .id(matriculaDB.getId())
                           .aluno(alunoResponse)
                           .curso(cursoResponse)
                           .dataMatricula(matriculaDB.getDataMatricula())
                           .ativo(matriculaDB.isAtivo())
                           .build();
    }

    /**
     * @param id
     * @param matriculaRequestDTO
     * @return
     */
    @Override
    public MatriculaDTO update(Long id, MatriculaRequestDTO matriculaRequestDTO) {
        var matriculaDB = repository.findById(id).orElseThrow(() -> new RuntimeException("Matricula not found"));

        BeanUtils.copyProperties(matriculaRequestDTO, matriculaDB);

        matriculaDB = repository.save(matriculaDB);

        var alunoResponse = alunoFeignClient.findById(matriculaDB.getAlunoId()).getBody();
        var cursoResponse = cursoFeignClient.findById(matriculaDB.getCursoId()).getBody();

        return MatriculaDTO.builder()
                            .id(matriculaDB.getId())
                            .aluno(alunoResponse)
                            .curso(cursoResponse)
                            .dataMatricula(matriculaDB.getDataMatricula())
                            .ativo(matriculaDB.isAtivo())
                            .build();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MatriculaExcluidaDTO delete(Long id) {
        MatriculaExcluidaDTO matriculaExcluidaDTO = new MatriculaExcluidaDTO();

        var matriculaDB = repository.findById(id).orElseThrow(() -> new RuntimeException("Matricula not found"));
        if (Objects.nonNull(matriculaDB)) {
            repository.delete(matriculaDB);
            matriculaExcluidaDTO.setStatus("Matricula excluída com sucesso!");
        }

        return matriculaExcluidaDTO;
    }
}
