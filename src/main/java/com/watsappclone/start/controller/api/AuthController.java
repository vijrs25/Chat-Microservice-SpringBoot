package com.watsappclone.start.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.watsappclone.start.dto.LoginRequest;
import com.watsappclone.start.dto.LoginResponse;
import com.watsappclone.start.entity.User;
import com.watsappclone.start.repository.UserRepository;
import com.watsappclone.start.security.JwtUtil;

import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getEmail());

        return new LoginResponse(
                token,
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}