package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Services.AuthService;
import com.HMS.HMS.dto.AuthResponseDto;
import com.HMS.HMS.dto.LoginRequest;
import com.HMS.HMS.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping
    public ResponseEntity<AuthResponseDto> login (@RequestBody LoginRequest loginDto){
        String token = authService.login(loginDto);
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);
        authResponseDto.setUserName(loginDto.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(authResponseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody Users user) {
        user.setRole("ADMIN");
        AuthResponseDto response = authService.register(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/me")
    public ResponseEntity<Users> updateUser(
            @RequestBody Users updatedUser) {
//        String email = authentication.getName();
        Users user = authService.updateUser(updatedUser);
        return ResponseEntity.ok(user);
    }
}
