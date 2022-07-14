package org.barros.modules.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CursoDTO implements Serializable {

    private Long curId;

    private String descricao;

    private Boolean ativo = true;
}
