package com.example.user_management.logic;

import com.example.user_management.entity.Authority;
import com.example.user_management.entity.Role;
import com.example.user_management.repository.AuthorityRepository;
import com.example.user_management.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Component
public class RoleLogic {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public Role addAuthorityToRole(UUID roleId, String authorityName) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NoSuchElementException("Role not found"));
        Authority authority = authorityRepository.findByName(authorityName)
                .orElseThrow(() -> new NoSuchElementException("Authority not found"));
        role.getAuthorities().add(authority);
        return roleRepository.save(role);
    }

    public Role removeAuthorityFromRole(UUID roleId, String authorityName) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NoSuchElementException("Role not found"));
        Authority authority = authorityRepository.findByName(authorityName)
                .orElseThrow(() -> new NoSuchElementException("Authority not found"));
        role.getAuthorities().remove(authority);
        return roleRepository.save(role);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role findById(UUID id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Role not found"));
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
