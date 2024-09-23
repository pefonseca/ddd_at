package management.cursos.infra.repository.config.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.cursos.domain.model.entity.AlunoNota;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoCadastroCursoDTO {

    private Long alunoId;
    private Long cursoId;
    private Double nota;

    public AlunoNota toEntity() {
        return AlunoNota.builder()
                        .alunoId(this.alunoId)
                        .cursoId(this.cursoId)
                        .nota(this.nota)
                        .build();
    }

}
