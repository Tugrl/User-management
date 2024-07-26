package com.example.user_management.mapper;

import com.example.user_management.dto.AuthorityDTO;
import com.example.user_management.entity.Authority;
import org.springframework.stereotype.Component;

@Component
public class AuthorityMapper {

    public AuthorityDTO toDTO(Authority authority) {
        AuthorityDTO dto = new AuthorityDTO();
        dto.setId(authority.getId());
        dto.setName(authority.getName());
        return dto;
    }

    public Authority toEntity(AuthorityDTO dto) {
        Authority authority = new Authority();
        authority.setId(dto.getId());
        authority.setName(dto.getName());
        return authority;
    }
}
