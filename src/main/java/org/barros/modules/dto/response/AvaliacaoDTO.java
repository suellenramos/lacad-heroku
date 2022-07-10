package org.barros.modules.dto.response;

import lombok.*;

import java.io.Serializable;
@Data
public class AvaliacaoDTO implements Serializable {

    private Long avaId;

    private Long profId;

    private Long apliId;

    private Integer nota;

    private String comentarios;
}
