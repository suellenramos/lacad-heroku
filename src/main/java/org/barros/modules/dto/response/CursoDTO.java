package org.barros.modules.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class CursoDTO implements Serializable {

    private Long curId;

    private String disciplinas;

    private String descricao;
}
