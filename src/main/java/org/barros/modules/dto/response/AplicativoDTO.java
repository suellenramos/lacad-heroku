package org.barros.modules.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Data
public class AplicativoDTO implements Serializable {

    private Long apliId;

    @JsonIgnore
    private String conteudos;

    private String link;

    private String descricao;
}