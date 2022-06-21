package org.barros.modules.dto.response;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Data
public class DisciplinaDTO implements Serializable {

    private Long discId;

    private Long profId;

    private String cursos;

    private Long conteId;

    private String descricao;
}
