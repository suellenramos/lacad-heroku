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

    @Mapping(target = "discId", expression = "java(cursoDisciplina.getDisciplina().getDiscId())")
    @Mapping(target = "curId", expression = "java(cursoDisciplina.getCurso().getCurId())")
    CursoDisciplinaDTO toDTO(CursoDisciplina cursoDisciplina);

    @Mapping(target = "cDid", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    CursoDisciplina toModel(CursoDisciplinaDTO cursoDisciplinaDTO);

    void updateModelFromDTO(CursoDisciplinaDTO cursoDisciplinaDTO, @MappingTarget CursoDisciplina cursoDisciplina);

    void updateDTOFromModel(CursoDisciplina CursoDisciplina, @MappingTarget CursoDisciplinaDTO cursoDisciplinaDTO);
}


