package org.barros.modules.security.mapper;


import org.barros.modules.security.dto.RoleDto;
import org.barros.modules.security.model.Role;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
    RoleMapper MAPPER = Mappers.getMapper(RoleMapper.class);

    Role roleDtoToRole(RoleDto roleDto);

    RoleDto roleToRoleDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoleFromRoleDto(RoleDto roleDto, @MappingTarget Role role);
}
