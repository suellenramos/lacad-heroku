package org.barros.modules.mapper;

import org.barros.modules.dto.response.ConteudoAplicativoDTO;
import org.barros.modules.dto.response.ProfessorAplicativoDTO;
import org.barros.modules.model.ConteudoAplicativo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

//@Mapper(componentModel = "cdi")
public interface ProfessorAplicativoMapper {
//
//    List<ProfessorAplicativoDTO> toDTOList(List<ProfessorAplicativo> professorAplicativos);
//
//    @Mapping(target = "profId", expression = "java(professorAplicativo.getProfessor().getProfId())")
//    @Mapping(target = "apliId", expression = "java(professorAplicativo.getAplicativo().getApliId())")
//    ProfessorAplicativoDTO toDTO(ProfessorAplicativo professorAplicativo);
//
//    @Mapping(target = "id", ignore = true)
//    @InheritInverseConfiguration(name = "toDTO")
//    ProfessorAplicativo toModel(ProfessorAplicativoDTO professorAplicativoDTO);
//
//    void updateModelFromDTO(ProfessorAplicativoDTO professorAplicativoDTO, @MappingTarget ProfessorAplicativo professorAplicativo);
//
//    void updateDTOFromModel(ProfessorAplicativo professorAplicativo, @MappingTarget ProfessorAplicativoDTO professorAplicativoDTO);
}


