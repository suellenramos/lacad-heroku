package org.barros.modules.dto.request;

import lombok.Data;
import org.barros.modules.dto.response.ConteudoDTO;
import org.barros.modules.dto.response.ProfessorDTO;

import java.io.Serializable;
import java.util.List;

@Data
public class DisciplinaDTO implements Serializable {

    private Long discId;

    private String descricao;

    private Boolean ativo = true;

    private List<ConteudoDTO> conteudos;

    private ProfessorDTO professor;
}
