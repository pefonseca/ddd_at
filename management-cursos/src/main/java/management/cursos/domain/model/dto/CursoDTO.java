package management.cursos.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.cursos.domain.model.entity.Curso;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private int duracao;

    public Curso toEntity() {
        return Curso.builder()
                    .id(this.id)
                    .nome(this.nome)
                    .descricao(this.descricao)
                    .duracao(this.duracao)
                    .build();
    }

}
