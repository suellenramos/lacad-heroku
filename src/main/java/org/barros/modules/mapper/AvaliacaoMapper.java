package org.barros.modules.mapper;

import org.barros.modules.dto.response.AvaliacaoDTO;
import org.barros.modules.dto.response.DisciplinaDTO;
import org.barros.modules.model.Avaliacao;
import org.barros.modules.model.Disciplina;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AvaliacaoMapper {
//
//    List<AvaliacaoDTO> toDTOList(List<Avaliacao> avaliacaos);
//
//    @Mapping(target = "profId", expression = "java(avaliacao.getProfessor().getProfId())")
//    @Mapping(target = "apliId", expression = "java(avaliacao.getAplicativo().getApliId())")
//    AvaliacaoDTO toDTO(Avaliacao avaliacao);
//
//    @Mapping(target = "avaId", ignore = true)
//    @InheritInverseConfiguration(name = "toDTO")
//    Avaliacao toModel(AvaliacaoDTO avaliacaoDTO);
//
//    void updateModelFromDTO(AvaliacaoDTO avaliacaoDTO, @MappingTarget Avaliacao avaliacao);
//
//    void updateDTOFromModel(Avaliacao avaliacao, @MappingTarget AvaliacaoDTO avaliacaoDTO);
}


