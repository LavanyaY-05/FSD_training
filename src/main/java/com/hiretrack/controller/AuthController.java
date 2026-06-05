package com.hiretrack.controller;

import com.hiretrack.dto.TokenDto;
import com.hiretrack.util.JwtUtility;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtility jwtUtility;
    @GetMapping("/login")
    public TokenDto login(Principal principal)
    {
        String username = principal.getName();
        String token = jwtUtility.generateToken(username);
        return new TokenDto(username, token);
    }


   }

