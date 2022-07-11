package org.barros.modules.security.mapper;

import org.barros.modules.security.dto.UsuarioDto;
import org.barros.modules.security.model.Usuario;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RoleMapper.class})
public interface UsuarioMapper {
    UsuarioMapper MAPPER = Mappers.getMapper(UsuarioMapper.class);

    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUsuarioFromUsuarioDto(UsuarioDto usuarioDto, @MappingTarget Usuario usuario);
}
