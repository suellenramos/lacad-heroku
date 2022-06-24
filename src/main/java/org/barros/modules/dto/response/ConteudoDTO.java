package org.barros.modules.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;


@Data
public class ConteudoDTO implements Serializable {

    private Long conteId;

   // @JsonIgnore
    private String  disciplinas;

    private String  aplicativos;

    private String descricao;
}
