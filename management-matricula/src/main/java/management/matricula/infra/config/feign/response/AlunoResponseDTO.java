package management.matricula.infra.config.feign.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private Date dataNascimento;
    private String status;
    private String contatoEmergencia;
    private Double nota;

}
