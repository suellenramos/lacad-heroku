package org.barros.modules.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdDto {
    private Long id;

    public IdDto(Long id) {
        this.id = id;
    }
}
