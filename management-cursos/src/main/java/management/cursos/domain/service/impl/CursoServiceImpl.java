package management.cursos.domain.service.impl;

import lombok.RequiredArgsConstructor;
import management.cursos.domain.consumer.CursoRequestConsumer;
import management.cursos.domain.model.dto.AlunoNotaDTO;
import management.cursos.domain.model.dto.CursoDTO;
import management.cursos.domain.model.dto.CursoExcluidoDTO;
import management.cursos.domain.model.dto.CursoRequestDTO;
import management.cursos.domain.service.CursoService;
import management.cursos.infra.repository.AlunoNotaRepository;
import management.cursos.infra.repository.CursoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;
    private final AlunoNotaRepository alunoNotaRepository;
    private final CursoRequestConsumer consumer;

    /**
     * @return
     */
    @Override
    public List<CursoDTO> findAll() {
        return repository.findAll().stream().map(curso ->
                CursoDTO.builder()
                        .id(curso.getId())
                        .nome(curso.getNome())
                        .descricao(curso.getDescricao())
                        .duracao(curso.getDuracao())
                        .build()
        ).toList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CursoDTO findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Curso not found.")).toDTO();
    }

    /**
     * @param requestDTO
     * @return
     */
    @Override
    public CursoDTO create(CursoRequestDTO requestDTO) {
        return repository.save(requestDTO.toEntity()).toDTO();
    }

    /**
     * @param id
     * @param cursoRequestDTO
     * @return
     */
    @Override
    public CursoDTO update(Long id, CursoRequestDTO cursoRequestDTO) {
        var cursoDB = findById(id).toEntity();
        BeanUtils.copyProperties(cursoRequestDTO, cursoDB);
        return repository.save(cursoDB).toDTO();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CursoExcluidoDTO delete(Long id) {
        CursoExcluidoDTO cursoExcluidoDTO = new CursoExcluidoDTO();

        var cursoDB = repository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        if(Objects.nonNull(cursoDB)) {
            repository.delete(cursoDB);
            cursoExcluidoDTO.setStatus("Aluno Excluído com sucesso!");
        }

        return cursoExcluidoDTO;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<AlunoNotaDTO> findNotas(Long id) {
        var relatorioDB = alunoNotaRepository.findAll().stream().filter(curso -> curso.getCursoId().equals(id)).toList();

        return relatorioDB.stream().map(alunoNota ->
                        AlunoNotaDTO.builder()
                                .id(alunoNota.getId())
                                .alunoId(alunoNota.getAlunoId())
                                .cursoId(alunoNota.getCursoId())
                                .nota(alunoNota.getNota())
                                .build())
                .toList();
    }
}
