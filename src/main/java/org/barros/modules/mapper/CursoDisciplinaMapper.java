package org.barros.modules.mapper;

import org.barros.modules.dto.response.CursoDisciplinaDTO;
import org.barros.modules.model.CursoDisciplina;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CursoDisciplinaMapper {

    List<CursoDisciplinaDTO> toDTOList(List<CursoDisciplina> cursoDisciplinas);

    @Mapping(target = "curId", expression = "java(cursoDisciplina.getCurso().getCurId())")
    @Mapping(target = "discId", expression = "java(cursoDisciplina.getDisciplina().getDiscId())")
    CursoDisciplinaDTO toDTO(CursoDisciplina cursoDisciplina);

    @Mapping(target = "id", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    CursoDisciplina toModel(CursoDisciplinaDTO cursoDisciplinaDTO);

    void updateModelFromDTO(CursoDisciplinaDTO cursoDisciplinaDTO, @MappingTarget CursoDisciplina cursoDisciplina);

    void updateDTOFromModel(CursoDisciplina CursoDisciplina, @MappingTarget CursoDisciplinaDTO cursoDisciplinaDTO);
}


