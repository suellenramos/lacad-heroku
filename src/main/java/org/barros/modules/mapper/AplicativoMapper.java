package org.barros.modules.mapper;

import org.barros.modules.dto.response.AplicativoDTO;
import org.barros.modules.dto.response.ConteudoDTO;
import org.barros.modules.model.Aplicativo;
import org.barros.modules.model.Conteudo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AplicativoMapper {

    List<AplicativoDTO> toDTOList(List<Aplicativo> aplicativos);

    @Mapping(target = "conteudos", ignore = true)
    AplicativoDTO toDTO(Aplicativo aplicativo);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "conteudos", ignore = true)
    @Mapping(target = "apliId", ignore = true)
    Aplicativo toModel(AplicativoDTO aplicativoDTO);

    @Mapping(target = "conteudos", ignore = true)
    void updateModelFromDTO(AplicativoDTO aplicativoDTO, @MappingTarget Aplicativo aplicativo);

    @Mapping(target = "conteudos", ignore = true)
    void updateDTOFromModel(Aplicativo aplicativo, @MappingTarget AplicativoDTO aplicativoDTO);
}


