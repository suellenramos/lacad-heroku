package org.barros.modules.mapper;

import org.barros.modules.dto.response.ConteudoAplicativoDTO;
import org.barros.modules.dto.response.DisciplinaConteudoDTO;
import org.barros.modules.model.ConteudoAplicativo;
import org.barros.modules.model.DisciplinaConteudo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ConteudoAplicativoMapper {

    List<ConteudoAplicativoDTO> toDTOList(List<ConteudoAplicativo> conteudoAplicativos);

    @Mapping(target = "conteId", expression = "java(conteudoAplicativo.getConteudo().getConteId())")
    @Mapping(target = "apliId", expression = "java(conteudoAplicativo.getAplicativo().getApliId())")
    ConteudoAplicativoDTO toDTO(ConteudoAplicativo conteudoAplicativo);

    @Mapping(target = "id", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    ConteudoAplicativo toModel(ConteudoAplicativoDTO conteudoAplicativoDTO);

    void updateModelFromDTO(ConteudoAplicativoDTO conteudoAplicativoDTO, @MappingTarget ConteudoAplicativo conteudoAplicativo);

    void updateDTOFromModel(ConteudoAplicativo conteudoAplicativo, @MappingTarget ConteudoAplicativoDTO conteudoAplicativoDTO);
}


