package org.barros.modules.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Data
public class DisciplinaDTO implements Serializable {

    private Long discId;

    private Long profId;
   // @JsonIgnore
    /* String esta referenciando a lista de cursos*/
    private String cursos;

    private String conteudos;

    private String descricao;
}
