package org.barros.modules.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO implements Serializable {

    private Long profId;

    private String nome;

    private String email;

    private String password;

}
