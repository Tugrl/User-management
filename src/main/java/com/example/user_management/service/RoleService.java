package com.example.user_management.service;

import com.example.user_management.dto.RoleDTO;
import com.example.user_management.entity.Role;
import com.example.user_management.logic.RoleLogic;
import com.example.user_management.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleLogic roleLogic;

    @Autowired
    private RoleMapper roleMapper;

    public List<RoleDTO> getAllRoles() {
        return roleLogic.findAll().stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public RoleDTO addAuthorityToRole(UUID roleId, String authorityName) {
        Role updatedRole = roleLogic.addAuthorityToRole(roleId, authorityName);
        return roleMapper.toDTO(updatedRole);
    }
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE')")
    public RoleDTO removeAuthorityFromRole(UUID roleId, String authorityName) {
        Role updatedRole = roleLogic.removeAuthorityFromRole(roleId, authorityName);
        return roleMapper.toDTO(updatedRole);
    }

    public RoleDTO save(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        Role savedRole = roleLogic.save(role);
        return roleMapper.toDTO(savedRole);
    }

    public RoleDTO findById(UUID id) {
        Role role = roleLogic.findById(id);
        return roleMapper.toDTO(role);
    }
}
