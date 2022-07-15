package org.barros.modules.dto.response;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;


@Data
public class AplicativoDTO implements Serializable {

    private Long apliId;

    private String link;

    private String descricao;

    private Boolean ativo = true;
}
