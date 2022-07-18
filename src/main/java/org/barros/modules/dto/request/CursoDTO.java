package org.barros.modules.dto.request;

import lombok.Data;
import org.barros.modules.dto.response.DisciplinaDTO;
import org.barros.modules.dto.response.ProfessorDTO;

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
