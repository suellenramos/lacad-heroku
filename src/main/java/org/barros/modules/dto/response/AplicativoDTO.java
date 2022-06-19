package org.barros.modules.dto.response;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Data
public class AplicativoDTO implements Serializable {

    private Long apliId;

    private Long conteId;

    private String link;

    private String descricao;
}
