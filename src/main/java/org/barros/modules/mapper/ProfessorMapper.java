package org.barros.modules.mapper;

import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.model.Professor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ProfessorMapper {

    List<ProfessorDTO> toDTOList(List<Professor> professores);

    ProfessorDTO toDTO(Professor professor);

    @Mapping(target = "profId", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    Professor toModel(ProfessorDTO professorDTO);

    void updateModelFromDTO(ProfessorDTO professorDTO, @MappingTarget Professor professor);

    void updateDTOFromModel(Professor professor, @MappingTarget ProfessorDTO professorDTO);

    void insert(ProfessorDTO professorDTO);
    Professor findByNomeAndSenha(String login, String senha);

    List<ProfessorDTO> listaProfessores();
}


