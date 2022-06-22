package org.barros.modules.dto.response;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Data
public class DisciplinaDTO implements Serializable {

    private Long discId;

    private Long profId;

    private String cursos;

    private String conteudos;

    //private Long conteId;

    private String descricao;
}
