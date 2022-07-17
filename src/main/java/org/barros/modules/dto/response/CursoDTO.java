package org.barros.modules.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CursoDTO implements Serializable {

    private Long curId;

    private String descricao;

    private Boolean ativo = true;

    private List<DisciplinaDTO> disciplinas;

    private ProfessorDTO professor;

    //private String profId;

}
