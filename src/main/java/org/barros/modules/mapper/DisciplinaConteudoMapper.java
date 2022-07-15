package org.barros.modules.mapper;

import org.barros.modules.dto.response.DisciplinaConteudoDTO;
import org.barros.modules.model.DisciplinaConteudo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface DisciplinaConteudoMapper {

    List<DisciplinaConteudoDTO> toDTOList(List<DisciplinaConteudo> disciplinaConteudos);

   // @Mapping(target = "conteId", expression = "java(disciplinaConteudo.getConteudo().getConteId())")
    @Mapping(target = "cdID", expression = "java(disciplinaConteudo.getCursoDisciplina().getId())")
    DisciplinaConteudoDTO toDTO(DisciplinaConteudo disciplinaConteudo);

    @Mapping(target = "id", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    DisciplinaConteudo toModel(DisciplinaConteudoDTO disciplinaConteudoDTO);

    void updateModelFromDTO(DisciplinaConteudoDTO disciplinaConteudoDTO, @MappingTarget DisciplinaConteudo disciplinaConteudo);

    void updateDTOFromModel(DisciplinaConteudo disciplinaConteudo, @MappingTarget DisciplinaConteudoDTO disciplinaConteudoDTO);
}


