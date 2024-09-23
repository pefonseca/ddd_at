package management.matricula.domain.service;

import management.matricula.domain.model.dto.MatriculaDTO;
import management.matricula.domain.model.dto.MatriculaExcluidaDTO;
import management.matricula.domain.model.dto.MatriculaRequestDTO;

import java.text.ParseException;
import java.util.List;

public interface MatriculaService {

    List<MatriculaDTO> findAll();
    MatriculaDTO findById(Long id);
    MatriculaDTO create(MatriculaRequestDTO matriculaRequestDTO) throws ParseException;
    MatriculaDTO update(Long id, MatriculaRequestDTO matriculaRequestDTO);
    MatriculaExcluidaDTO delete(Long id);

}
