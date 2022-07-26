package org.barros.modules.mapper;

import org.barros.modules.dto.response.FavoritoDTO;
import org.barros.modules.model.Favorito;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface FavoritoMapper {

    List<FavoritoDTO> toDTOList(List<Favorito> favoritos);

    @Mapping(target = "apliId", expression = "java(favorito.getAplicativo().getApliId())")
    @Mapping(target = "profId", expression = "java(favorito.getProfessor().getProfId())")
    FavoritoDTO toDTO(Favorito favorito);

    @Mapping(target = "favoId", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    Favorito toModel(FavoritoDTO favoritoDTO);

    void updateModelFromDTO(FavoritoDTO favoritoDTO, @MappingTarget Favorito favorito);

    void updateDTOFromModel(Favorito favorito, @MappingTarget FavoritoDTO favoritoDTO);
}


