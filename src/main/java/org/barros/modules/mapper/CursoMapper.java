package org.barros.modules.mapper;

import org.barros.modules.dto.response.CursoDTO;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.model.Curso;
import org.barros.modules.model.Professor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CursoMapper {

    List<CursoDTO> toDTOList(List<Curso> curssos);

   // @Mapping(target = "discId", expression = "java(curso.getDisciplina().getDiscId())")
   @Mapping(target = "disciplinas", ignore = true)
   CursoDTO toDTO(Curso curso);

    @Mapping(target = "curId", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "disciplinas", ignore = true)
    Curso toModel(CursoDTO cursoDTO);

    @Mapping(target = "disciplinas", ignore = true)
    void updateModelFromDTO(CursoDTO cursoDTO, @MappingTarget Curso curso);

    @Mapping(target = "disciplinas", ignore = true)
    void updateDTOFromModel(Curso curso, @MappingTarget CursoDTO cursoDTO);
}


