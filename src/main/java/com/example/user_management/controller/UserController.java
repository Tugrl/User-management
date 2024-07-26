package com.example.user_management.controller;

import com.example.user_management.dto.UserDTO;
import com.example.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public Page<UserDTO> searchUsers(@RequestParam Map<String, String> searchParams, Pageable pageable) {
        return userService.findAllUsersWithFilters(searchParams, pageable);
    }

        @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        Optional<UserDTO> userDTO = userService.getUserById(id);
        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.saveUser(userDTO);
        return ResponseEntity.status(201).body(createdUser);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
//        Optional<UserDTO> updatedUserDTO = userService.getUserById(id);
//        if (updatedUserDTO.isPresent()) {
//            UserDTO savedUser = userService.saveUser(userDTO);
//            return ResponseEntity.ok(savedUser);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDTO> addRoleToUser(@PathVariable UUID id, @RequestBody Map<String, String> request) {
        String roleName = request.get("roleName");
        UserDTO updatedUser = userService.addRoleToUser(id, roleName);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        UserDTO user = userService.findByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }
}
