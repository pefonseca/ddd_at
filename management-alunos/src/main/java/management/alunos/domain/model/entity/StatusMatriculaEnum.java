package management.alunos.domain.model.entity;

import lombok.Getter;

@Getter
public enum StatusMatriculaEnum {

    ATIVO(0), INATIVO(1), SUSPENSO(2);

    private final int codigo;

    StatusMatriculaEnum(int codigo) {
        this.codigo = codigo;
    }

    public static StatusMatriculaEnum fromCodigo(int codigo) {
        for (StatusMatriculaEnum status : values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status de matrícula inválido.");
    }

}
