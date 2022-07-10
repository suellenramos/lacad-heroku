package org.barros.modules.mapper;

import org.barros.modules.dto.response.AplicativoFavoritoDTO;
import org.barros.modules.model.AplicativoFavorito;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AplicativoFavoritoMapper {

    List<AplicativoFavoritoDTO> toDTOList(List<AplicativoFavorito> aplicativoFavoritos);

    //@Mapping(target = "profId", expression = "java(professorAplicativo.getProfessor().getProfId())")
    @Mapping(target = "caID", expression = "java(aplicativoFavorito.getConteudoAplicativo().getId())")
    AplicativoFavoritoDTO toDTO(AplicativoFavorito aplicativoFavorito);

    @Mapping(target = "id", ignore = true)
    @InheritInverseConfiguration(name = "toDTO")
    AplicativoFavorito toModel(AplicativoFavoritoDTO aplicativoFavoritoDTO);

    void updateModelFromDTO(AplicativoFavoritoDTO aplicativoFavoritoDTO, @MappingTarget AplicativoFavorito aplicativoFavorito);

    void updateDTOFromModel(AplicativoFavorito aplicativoFavorito, @MappingTarget AplicativoFavoritoDTO aplicativoFavoritoDTO);
}


