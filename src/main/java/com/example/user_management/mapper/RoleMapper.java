package com.example.user_management.mapper;

import com.example.user_management.dto.RoleDTO;
import com.example.user_management.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleMapper {

    @Autowired
    private AuthorityMapper authorityMapper;

    public RoleDTO toDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setAuthorities(role.getAuthorities().stream()
                .map(authorityMapper::toDTO)
                .collect(Collectors.toSet()));
        return dto;
    }

    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setAuthorities(dto.getAuthorities().stream()
                .map(authorityMapper::toEntity)
                .collect(Collectors.toSet()));
        return role;
    }
}
