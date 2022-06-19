package org.barros.modules.dto.response;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Data
public class ConteudoDTO implements Serializable {

    private Long conteId;

    private Long discId;

    private String descricao;
}
