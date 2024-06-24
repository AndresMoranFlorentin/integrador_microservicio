package com.microservicio.auth_user.security;

import com.microservicio.auth_user.model.UserAuth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private KeyPair keyPair;

    @PostConstruct
    protected void init() {
        keyPair = generateKeyPair();
    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating key pair", e);
        }
    }

    public String createToken(UserAuth authUser) {
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(authUser.getUserName());
        claims.put("id", authUser.getId());
        Date now = new Date();
        Date expc = new Date(now.getTime() + 3600000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expc)
                .signWith(SignatureAlgorithm.ES256, keyPair.getPrivate())
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserNameFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(keyPair.getPublic()).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            return "token incorrecto!!";
        }
    }
}
