package org.barros.modules.service;

import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.model.Professor;

import java.util.List;


public interface IProfessorService {

    void insert(ProfessorDTO professorDTO);

    Professor findByNomeAndSenha(String login, String senha);

    List<ProfessorDTO> listaProfessores();
}



