package org.barros.modules.dto.response;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProfessorAplicativoDTO implements Serializable {

    private Long id;

    private Long profId;

    private  Long apliId;

}
