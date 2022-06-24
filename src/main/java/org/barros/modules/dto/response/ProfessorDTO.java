package org.barros.modules.dto.response;

import lombok.*;

import java.io.Serializable;

@Data
public class ProfessorDTO implements Serializable {

    private Long profId;

    private String aplicativos;

    private String nome;

    private String email;

    private String senha;
}
