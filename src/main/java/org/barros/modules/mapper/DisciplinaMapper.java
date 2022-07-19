package org.barros.modules.mapper;

import org.barros.modules.dto.request.DisciplinaDTO;
import org.barros.modules.dto.response.DisciplinaResponseDTO;
import org.barros.modules.model.Disciplina;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface DisciplinaMapper {

    List<DisciplinaResponseDTO> toDTOList(List<Disciplina> disciplinas);

     DisciplinaResponseDTO toDTObyId(Disciplina disciplina);

    //@Mapping(target = "CDid", expression = "java(disciplina.getCursoDisciplinas().getCDId())")
   // @Mapping(target = "profId", expression = "java(disciplina.getProfessor().getProfId())")
    DisciplinaDTO toDTO(Disciplina disciplina);

    @Mapping(target = "discId", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    Disciplina toModel(DisciplinaDTO disciplinaDTO);

    void updateModelFromDTO(DisciplinaDTO disciplinaDTO, @MappingTarget Disciplina disciplina);

    void updateDTOFromModel(Disciplina disciplina, @MappingTarget DisciplinaDTO disciplinaDTO);
}


