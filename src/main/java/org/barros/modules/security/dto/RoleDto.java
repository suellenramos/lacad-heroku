package org.barros.modules.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleDto implements Serializable {

    private  Long id;
    private  String nome;
}
