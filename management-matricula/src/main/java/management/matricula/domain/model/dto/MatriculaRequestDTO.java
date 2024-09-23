package management.matricula.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.matricula.domain.model.entity.Matricula;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaRequestDTO {

    private Long alunoId;
    private Long cursoId;
    private String dataMatricula;
    private boolean ativo;

    private Date convertStringForDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    public Matricula toEntity() throws ParseException {
        return Matricula.builder()
                        .alunoId(this.alunoId)
                        .cursoId(this.cursoId)
                        .dataMatricula(convertStringForDate(this.dataMatricula))
                        .ativo(this.ativo)
                        .build();
    }

}
