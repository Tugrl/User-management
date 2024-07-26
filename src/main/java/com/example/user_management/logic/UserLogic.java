package com.example.user_management.logic;

import com.example.user_management.dto.UserDTO;
import com.example.user_management.entity.Role;
import com.example.user_management.entity.User;
import com.example.user_management.repository.RoleRepository;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.search.UserSearchEntity;
import com.example.user_management.security.EncoderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserLogic {

    @Autowired
    private EncoderConfig encoderConfig;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSearchEntity userSearchEntity;
    public Page<User> findAllUsersWithFilters(Map<String, String> searchParams, Pageable pageable) {
        String name = searchParams.get("name");
        String surname = searchParams.get("surname");
        String identityNumber = searchParams.get("identityNumber");
        String birthDate = searchParams.get("birthDate");
        Float salary = searchParams.get("salary") != null ? Float.parseFloat(searchParams.get("salary")) : null;
        String email = searchParams.get("email");
        String username = searchParams.get("username");

        return userRepository.findByCriteria(name, surname, identityNumber, birthDate, salary, email, username, pageable);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new NoSuchElementException("ROLE_USER not found"));
        user.getRoles().add(userRole);
        user.setPassword(encoderConfig.passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User addRoleToUser(UUID userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // Add role to user logic
        return userRepository.save(user);
    }

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    public User updateUser(UUID id,User user) {
        User currentUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        currentUser.setIdentityNumber(user.getIdentityNumber());
        currentUser.setBirthDate(user.getBirthDate());
        currentUser.setSalary(user.getSalary());
        currentUser.setPassword(encoderConfig.passwordEncoder().encode(user.getPassword()));
        return userRepository.save(currentUser);
    }

    public User updateUserWithPatch(UUID id, Map<String, Object> updates) {
    User currentUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    updates.forEach((key, value) -> {
        switch (key) {
         case "name":
          currentUser.setName((String) value);
         break;
        case "surname":
        currentUser.setSurname((String) value);
         break;
         case "birthDate":
         currentUser.setBirthDate((String) value);
         break;
          case "email":
           currentUser.setEmail((String) value);
          break;
        }
    });
    return userRepository.save(currentUser);
    }

}
