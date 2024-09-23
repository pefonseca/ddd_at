package management.alunos.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.alunos.domain.model.entity.Aluno;
import management.alunos.domain.model.entity.StatusMatriculaEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRequestDTO {

    private String nome;
    private String email;
    private String cpf;
    private String dataNascimento;
    private int status;
    private Double nota;
    private String contatoEmergencia;

    private Date convertStringForDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    public Aluno toEntity() throws ParseException {
        return Aluno.builder()
                    .nome(this.nome)
                    .email(this.email)
                    .cpf(this.cpf)
                    .nota(this.nota)
                    .dataNascimento(convertStringForDate(dataNascimento))
                    .status(StatusMatriculaEnum.fromCodigo(this.status))
                    .contatoEmergencia(this.contatoEmergencia)
                    .build();
    }

}
