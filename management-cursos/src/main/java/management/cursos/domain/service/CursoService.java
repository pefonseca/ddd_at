package management.cursos.domain.service;

import management.cursos.domain.model.dto.AlunoNotaDTO;
import management.cursos.domain.model.dto.CursoDTO;
import management.cursos.domain.model.dto.CursoExcluidoDTO;
import management.cursos.domain.model.dto.CursoRequestDTO;

import java.util.List;

public interface CursoService {

    List<CursoDTO> findAll();
    CursoDTO findById(Long id);
    CursoDTO create(CursoRequestDTO requestDTO);
    CursoDTO update(Long id, CursoRequestDTO cursoRequestDTO);
    CursoExcluidoDTO delete(Long id);
    List<AlunoNotaDTO> findNotas(Long id);

}
