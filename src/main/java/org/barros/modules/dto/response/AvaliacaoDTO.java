package org.barros.modules.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO implements Serializable {

    private Long avaId;

    private Long profId;

    private Long apliId;

    private String nota;

    private String comentarios;

}
