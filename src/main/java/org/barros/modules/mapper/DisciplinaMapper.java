package org.barros.modules.mapper;

import org.barros.modules.model.Disciplina;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import org.barros.modules.dto.response.DisciplinaDTO;

@Mapper(componentModel = "cdi")
public interface DisciplinaMapper {

    List<DisciplinaDTO> toDTOList(List<Disciplina> disciplinas);
    //@Mapping(target = "CDid", expression = "java(disciplina.getCursoDisciplinas().getCDId())")
    @Mapping(target = "profId", expression = "java(disciplina.getProfessor().getProfId())")
    DisciplinaDTO toDTO(Disciplina disciplina);
    @Mapping(target = "discId", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    Disciplina toModel(DisciplinaDTO disciplinaDTO);
    void updateModelFromDTO(DisciplinaDTO disciplinaDTO, @MappingTarget Disciplina disciplina);
    void updateDTOFromModel(Disciplina disciplina, @MappingTarget DisciplinaDTO disciplinaDTO);
}


