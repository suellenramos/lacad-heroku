package org.barros.modules.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessorDTO implements Serializable {

    private Long id;

    private String nome;

    private String email;

    private String senha;
}
