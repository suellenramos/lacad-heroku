package org.barros.modules.mapper;

import org.barros.modules.dto.response.CursoDTO;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.model.Curso;
import org.barros.modules.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mapper
public interface CursoMapper {

    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);
    Logger logger = LoggerFactory.getLogger(CursoMapper.class);

    /**
     *
     * @param curso Modelo
     * @return DTO convertido
     */

    CursoDTO modelToDTO(Curso curso);

    /**
     * @param cursoDTO DTO
     * @return Modelo convertido
     */
    @Mapping(target = "id", ignore = true)
    Curso dtoToModel(CursoDTO cursoDTO);
}
