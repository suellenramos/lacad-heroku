package org.barros.modules.dto.response;

import lombok.Data;

import java.io.Serializable;


@Data
public class ConteudoDTO implements Serializable {

    private Long conteId;

    private String descricao;
}
