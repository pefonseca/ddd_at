package management.matricula.infra.config.feign.response;

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
public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private int duracao;

}