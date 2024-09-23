package management.matricula.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.matricula.infra.config.feign.response.AlunoResponseDTO;
import management.matricula.infra.config.feign.response.CursoResponseDTO;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaDTO {

    private Long id;
    private AlunoResponseDTO aluno;
    private CursoResponseDTO curso;
    private Date dataMatricula;
    private boolean ativo;

}
