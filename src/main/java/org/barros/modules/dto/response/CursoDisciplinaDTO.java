package org.barros.modules.dto.response;


import lombok.Data;


import java.io.Serializable;

@Data
public class CursoDisciplinaDTO implements Serializable {

    private Long id;

    private Long curId;

    private  Long discId;

    private Long profId;
}
