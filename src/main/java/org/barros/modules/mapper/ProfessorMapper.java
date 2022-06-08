package org.barros.modules.mapper;

import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper
public interface ProfessorMapper {

    ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);
    Logger logger = LoggerFactory.getLogger(ProfessorMapper.class);

    /**
     *
     * @param professor Modelo
     * @return DTO convertido
     */

    // @Mapping(target = "pesId", expression = "java(envolvido.getPessoa().getId())")
    ProfessorDTO modelToDTO(Professor professor);

    /**
     * @param professorDTO DTO
     * @return Modelo convertido
     */
    @Mapping(target = "id", ignore = true)
    Professor dtoToModel(ProfessorDTO professorDTO);
}
