package org.barros.modules.dto.response;

import lombok.Data;
import org.barros.modules.dto.request.DisciplinaDTO;

import java.io.Serializable;
import java.util.List;

@Data
public class CursoResponseDTO implements Serializable {

    private Long curId;

    private String descricao;

    private Boolean ativo = true;

    private List<DisciplinaDTO> disciplinas;

    private List <ProfessorDTO> professores;
}
