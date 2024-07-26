package com.example.user_management.controller;

import com.example.user_management.dto.UserDTO;
import com.example.user_management.entity.LoginRequest;
import com.example.user_management.entity.LoginResponse;
import com.example.user_management.security.JwtUtil;
import com.example.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);
            UserDTO userDTO = userService.findByUsername(userDetails.getUsername());

            return new LoginResponse(jwt, userDTO);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        // Implement registration logic here
        UserDTO registeredUser = userService.saveUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

}

