package management.alunos.domain.service;

import management.alunos.domain.model.dto.AlunoDTO;
import management.alunos.domain.model.dto.AlunoExcluidoDTO;
import management.alunos.domain.model.dto.AlunoNotaDTO;
import management.alunos.domain.model.dto.AlunoRequestDTO;

import java.text.ParseException;
import java.util.List;

public interface AlunoService {

    List<AlunoDTO> findAll();
    AlunoDTO findById(Long id);
    AlunoDTO create(AlunoRequestDTO requestDTO) throws ParseException;
    AlunoDTO update(Long id, AlunoRequestDTO requestDTO);
    AlunoExcluidoDTO delete(Long id);
    AlunoNotaDTO findByNota(Long id);
}
