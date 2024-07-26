//package com.example.user_management.entity;
//
//import com.example.user_management.dto.UserDTO;
//
//public class LoginResponse {
//    private String message;
//    private UserDTO userDTO;
//
//    public LoginResponse(String message, UserDTO userDTO) {
//        this.message = message;
//        this.userDTO = userDTO;
//    }
//    // Getter
//    public String getMessage() {
//        return message;
//    }
//
//}
package com.example.user_management.entity;

import com.example.user_management.dto.UserDTO;

public class LoginResponse {
    private String jwt;
    private UserDTO user;

    public LoginResponse(String jwt, UserDTO user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}

