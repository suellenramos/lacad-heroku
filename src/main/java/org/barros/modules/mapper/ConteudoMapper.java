package org.barros.modules.mapper;

import org.barros.modules.dto.response.ConteudoDTO;
import org.barros.modules.model.Conteudo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ConteudoMapper {

    List<ConteudoDTO> toDTOList(List<Conteudo> conteudos);

    @Mapping(target = "disciplinas", ignore = true)
    @Mapping(target = "aplicativos", ignore = true)
    ConteudoDTO toDTO(Conteudo conteudo);

    @Mapping(target = "conteId", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "aplicativos", ignore = true)
    @Mapping(target = "disciplinas", ignore = true)
    Conteudo toModel(ConteudoDTO conteudoDTO);

    @Mapping(target = "aplicativos", ignore = true)
    @Mapping(target = "disciplinas", ignore = true)
    void updateModelFromDTO(ConteudoDTO conteudoDTO, @MappingTarget Conteudo conteudo);

    @Mapping(target = "aplicativos", ignore = true)
    @Mapping(target = "disciplinas", ignore = true)
    void updateDTOFromModel(Conteudo conteudo, @MappingTarget ConteudoDTO conteudoDTO);
}


