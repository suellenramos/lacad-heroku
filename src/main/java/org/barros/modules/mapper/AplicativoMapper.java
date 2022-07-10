package org.barros.modules.mapper;

import org.barros.modules.dto.response.AplicativoDTO;
import org.barros.modules.dto.response.ConteudoDTO;
import org.barros.modules.model.Aplicativo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AplicativoMapper {

    List<AplicativoDTO> toDTOList(List<Aplicativo> aplicativos);

    AplicativoDTO toDTO(Aplicativo aplicativo);

    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(target = "apliId", ignore = true)
    Aplicativo toModel(AplicativoDTO aplicativoDTO);

    void updateModelFromDTO(AplicativoDTO aplicativoDTO, @MappingTarget Aplicativo aplicativo);

    void updateDTOFromModel(Aplicativo aplicativo, @MappingTarget AplicativoDTO aplicativoDTO);
}


