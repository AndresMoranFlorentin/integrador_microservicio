package com.microservicio.auth_user.security;

import com.microservicio.auth_user.entities.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    //genera la clave de curva eliptica
    private KeyPair keyPair;

    @PostConstruct
    protected void init(){
        keyPair=KeyGeneratorUtil.generateKeyPair();
        //secret= Base64.getEncoder().encodeToString(secret.getBytes());
    }
    public String createToken(AuthUser authUser){
        Map<String,Object> claims= new HashMap<>();
        claims= Jwts.claims().setSubject(authUser.getUserName());
        claims.put("id",authUser.getId());
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
