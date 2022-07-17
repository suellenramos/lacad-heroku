package org.barros.modules.dto.response;


import lombok.Data;

import java.io.Serializable;

@Data
public class ConteudoAplicativoDTO implements Serializable {

    private Long id;

    private  Long apliId;

    private Long conteId;

    private Boolean ativo = true;

}
