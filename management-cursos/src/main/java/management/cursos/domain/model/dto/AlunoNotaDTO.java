package management.cursos.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoNotaDTO {

    private Long id;
    private Long alunoId;
    private Long cursoId;
    private Double nota;

}
