package com.microservicio.auth_user.controllers;

import com.microservicio.auth_user.dto.AuthUserDto;
import com.microservicio.auth_user.dto.TokenDto;
import com.microservicio.auth_user.entities.AuthUser;
import com.microservicio.auth_user.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto dto) {
        TokenDto token = authService.login(dto);
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(token);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token){
        TokenDto tokenDto = authService.validate(token);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody AuthUserDto dto){
        AuthUser authUser = authService.save(dto);
        if(authUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);
    }
    //se termina segunda parte del video
    //ahora viene: Microservicios con Spring Boot: Capítulo 17: JWT(parte 3)
}
