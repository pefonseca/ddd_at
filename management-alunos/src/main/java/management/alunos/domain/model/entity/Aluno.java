package management.alunos.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.alunos.domain.model.dto.AlunoDTO;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    private StatusMatriculaEnum status;
    private String contatoEmergencia;
    private Double nota;

    public AlunoDTO toDTO() {
        return AlunoDTO.builder()
                       .id(this.id)
                       .nome(this.nome)
                       .email(this.email)
                       .cpf(this.cpf)
                       .dataNascimento(this.dataNascimento)
                       .status(this.status.name())
                       .contatoEmergencia(this.contatoEmergencia)
                       .nota(this.nota)
                       .build();
    }

}
