package com.roadready.controller;

import com.roadready.dto.LoginRespDto;
import com.roadready.dto.PasswordDto;
import com.roadready.dto.TokenDto;
import com.roadready.model.User;
import com.roadready.repository.UserRepository;
import com.roadready.service.UserService;
import com.roadready.util.JwtUtility;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    private final JwtUtility jwtUtility;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public TokenDto login(Principal principal) {

        String username = principal.getName();
        String token = jwtUtility.generateToken(username);
        return new TokenDto(username, token);
    }


    @GetMapping("/user-details")
    public LoginRespDto getUserDetails(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return new LoginRespDto(
                user.getId(),
                user.getUsername(),
                user.getRole().toString()
        );
    }

    @PostMapping("/newpass")
    public void newPassword(Principal principal, @Valid @RequestBody PasswordDto dto) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        user.setPassword(passwordEncoder.encode(dto.newPassword()));
        userRepository.save(user);

    }


}
