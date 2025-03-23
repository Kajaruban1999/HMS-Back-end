package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Users;
import com.HMS.HMS.Repositories.UserRepo;
import com.HMS.HMS.dto.AuthResponseDto;
import com.HMS.HMS.dto.LoginRequest;
import com.HMS.HMS.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenProvider;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public String login(LoginRequest loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    public AuthResponseDto register(Users user) {
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
        String token = jwtTokenProvider.generateToken(authentication);
        String userName = user.getFirstName() + " " + user.getLastName();
        AuthResponseDto response = new AuthResponseDto();
        response.setAccessToken(token);
        response.setUserName(userName);
        return response;
    }

    public Users updateUser(Users updatedUser) {
        Users existingUser = userRepo.findByEmail(updatedUser.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setPhoneNum(updatedUser.getPhoneNum());
        existingUser.setPassportNic(updatedUser.getPassportNic());
        existingUser.setCountry(updatedUser.getCountry());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return userRepo.save(existingUser);
    }
}
