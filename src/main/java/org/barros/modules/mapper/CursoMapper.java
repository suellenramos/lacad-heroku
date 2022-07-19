package org.barros.modules.mapper;

import org.barros.modules.dto.request.CursoDTO;
import org.barros.modules.dto.response.CursoResponseDTO;
import org.barros.modules.model.Curso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CursoMapper {

    List<CursoResponseDTO> toDTOList(List<Curso> cursos);

    CursoResponseDTO toDTObyId(Curso curso);

    CursoDTO toDTO(Curso curso);

    @Mapping(target = "curId", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    Curso toModel(CursoDTO cursoDTO);

    void updateModelFromDTO(CursoDTO cursoDTO, @MappingTarget Curso curso);

    void updateDTOFromModel(Curso curso, @MappingTarget CursoDTO cursoDTO);
}


