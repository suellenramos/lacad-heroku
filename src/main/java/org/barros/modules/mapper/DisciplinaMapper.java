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

   // @Mapping(target = "curId", expression = "java(disciplina.getCurso().getCurId())")
    @Mapping(target = "profId", expression = "java(disciplina.getProfessor().getProfId())")
    @Mapping(target = "cursos", ignore = true)
    DisciplinaDTO toDTO(Disciplina disciplina);

    @Mapping(target = "discId", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "cursos", ignore = true)
    Disciplina toModel(DisciplinaDTO disciplinaDTO);

    @Mapping(target = "cursos", ignore = true)
    void updateModelFromDTO(DisciplinaDTO disciplinaDTO, @MappingTarget Disciplina disciplina);

    @Mapping(target = "cursos", ignore = true)
    void updateDTOFromModel(Disciplina disciplina, @MappingTarget DisciplinaDTO disciplinaDTO);
}


