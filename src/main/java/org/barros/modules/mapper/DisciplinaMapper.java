package org.barros.modules.mapper;

import org.barros.modules.dto.response.DisciplinaDTO;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.model.Disciplina;
import org.barros.modules.model.Professor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface DisciplinaMapper {

    List<DisciplinaDTO> toDTOList(List<Disciplina> disciplinas);

    DisciplinaDTO toDTO(Disciplina disciplina);

    @Mapping(target = "discId", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    Disciplina toModel(DisciplinaDTO disciplinaDTO);

    void updateModelFromDTO(DisciplinaDTO disciplinaDTO, @MappingTarget Disciplina disciplina);

    void updateDTOFromModel(Disciplina disciplina, @MappingTarget DisciplinaDTO disciplinaDTO);
}


