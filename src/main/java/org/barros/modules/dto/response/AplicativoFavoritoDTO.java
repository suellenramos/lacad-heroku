package org.barros.modules.dto.response;


import lombok.Data;

import java.io.Serializable;

@Data
public class AplicativoFavoritoDTO implements Serializable {

    private Long id;

    private Boolean favorito;

    //private Long profId;

    private  Long caID;
}
