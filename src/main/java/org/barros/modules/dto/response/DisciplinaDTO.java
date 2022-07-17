package org.barros.modules.dto.response;

import lombok.Data;

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
