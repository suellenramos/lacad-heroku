package org.barros.modules.mapper;

import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.model.Professor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ProfessorMapper {

    List<ProfessorDTO> toDTOList(List<Professor> professores);

    @Mapping(target = "aplicativos", ignore = true)
    ProfessorDTO toDTO(Professor professor);

    @Mapping(target = "aplicativos", ignore = true)
    @Mapping(target = "profId", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    Professor toModel(ProfessorDTO professorDTO);

    @Mapping(target = "aplicativos", ignore = true)
    void updateModelFromDTO(ProfessorDTO professorDTO, @MappingTarget Professor professor);

    @Mapping(target = "aplicativos", ignore = true)
    void updateDTOFromModel(Professor professor, @MappingTarget ProfessorDTO professorDTO);
}


