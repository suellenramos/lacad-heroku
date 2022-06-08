package org.barros.modules.service;

import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.mapper.ProfessorMapper;
import org.barros.modules.repository.ProfessorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProfessorService {

    private static final String PROFESSOR_NAO_ENCONTRADO = "Professor não encontrado";
    @Inject
    ProfessorRepository professorRepository;

    @Transactional
    public ProfessorDTO salvar(ProfessorDTO professorDTO){
        var professor = ProfessorMapper.INSTANCE.dtoToModel(professorDTO);
        professorRepository.persist(professor);
        return ProfessorMapper.INSTANCE.modelToDTO(professor);
    }

}


