package org.barros.modules.dto.response;


import lombok.Data;

import java.io.Serializable;

@Data
public class DisciplinaConteudoDTO implements Serializable {

    private Long id;

    private  Long discId;

    private Long conteId;

}
