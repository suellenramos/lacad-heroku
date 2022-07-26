package org.barros.modules.dto.response;


import lombok.Data;

import java.io.Serializable;

@Data
public class FavoritoDTO implements Serializable {

    private Long favoId;

    private  Long apliId;

    private Long profId;
}
