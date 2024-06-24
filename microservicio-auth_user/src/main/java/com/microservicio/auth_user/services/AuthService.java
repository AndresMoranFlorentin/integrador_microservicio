package com.microservicio.auth_user.services;

import com.microservicio.auth_user.dto.AuthUserDto;
import com.microservicio.auth_user.dto.TokenDto;
import com.microservicio.auth_user.entities.AuthUser;
import com.microservicio.auth_user.repositories.AuthUserRepository;
import com.microservicio.auth_user.security.JwtProvider;
import com.microservicio.auth_user.security.PasswordEncoderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Transactional
    public AuthUser save(AuthUserDto dto) {
        System.out.println("#------------------------------------");
        System.out.println("dto userName: "+dto.getUserName());
        System.out.println("#-------------------------------------");
        String nombre= dto.getUserName();
        Optional<AuthUser> user = authUserRepository.findByUserName(nombre);
        if(user.isPresent())
            return null;
        String password = passwordEncoder.encode(dto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(dto.getUserName())
                .password(password)
                .build();
        return authUserRepository.save(authUser);
    }
    @Transactional
    public TokenDto login(AuthUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
        if(!user.isPresent())
            return null;
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }
    @Transactional
    public TokenDto validate(String token) {
        if(!jwtProvider.validate(token))
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByUserName(username).isPresent())
            return null;
        return new TokenDto(token);
    }
}
