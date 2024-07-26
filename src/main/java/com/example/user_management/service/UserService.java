package com.example.user_management.service;

import com.example.user_management.dto.UserDTO;
import com.example.user_management.entity.User;
import com.example.user_management.logic.UserLogic;
import com.example.user_management.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserLogic userLogic;

    @Autowired
    private UserMapper userMapper;


    public Page<UserDTO> findAllUsersWithFilters(Map<String, String> searchParams, Pageable pageable) {
        Page<User> users = userLogic.findAllUsersWithFilters(searchParams, pageable);
        return users.map(userMapper::userToUserDTO);
    }

    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public List<UserDTO> getAllUsers() {
        return userLogic.getAllUsers().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public Optional<UserDTO> getUserById(UUID id) {
        return userLogic.getUserById(id)
                .map(userMapper::userToUserDTO);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userLogic.saveUser(user);
        return userMapper.userToUserDTO(savedUser);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUser(UUID id) {
        userLogic.deleteUser(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public Optional<User> findById(UUID id) {
        return userLogic.getUserById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserDTO addRoleToUser(UUID userId, String roleName) {
        User user = userLogic.getUserById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return userMapper.userToUserDTO(userLogic.saveUser(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userLogic.loadUserByUsername(username);
        return user;
    }

    public UserDTO findByUsername(String username) {
        User user = (User) userLogic.loadUserByUsername(username);
        return userMapper.userToUserDTO(user);
    }
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public UserDTO updateUser(UUID id,UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User updatedUser = userLogic.saveUser(user);
        return userMapper.userToUserDTO(updatedUser);
    }
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public UserDTO updateUserWithPatch(UUID id, Map<String,Object> updates) {
    User updatedUser = userLogic.updateUserWithPatch(id, updates);
    return userMapper.userToUserDTO(updatedUser);
    }
}
