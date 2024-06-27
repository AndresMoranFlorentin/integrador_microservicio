package com.microservicio.gateway.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public boolean validateJwtToken(String token, String path, String httpMethod) {
        boolean hasRole = false;

        System.out.println("hasRole: " + hasRole + "  ----------------------------------------------------------------");
        System.out.println(" Get.equalsIgnoreCase(httpMethod) da:  " + ("GET".equalsIgnoreCase(httpMethod)));
        System.out.println("contiene /api/ ?: " + path.contains("/api/"));
        System.out.println("path entera  : " + path);
        System.out.println("true o false en el if de claims: " + path.contains("/api/"));

        Claims claims;
             claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("Claims: " + claims);

        boolean isTokenExpired = claims.getExpiration().before(new Date());

        System.out.println("Expiro el token?: " + isTokenExpired);
        System.out.println("El role tiene hasRole(claims, 'ADMIN,USER') ?: " + hasRole(claims, "ADMIN,USER"));

        if (path.contains("/api/")) {

            if ("GET".equalsIgnoreCase(httpMethod)) {
                hasRole = hasRole(claims, "ADMIN,USER");
            } else {
                hasRole = hasRole(claims, "ADMIN");
            }
        } else {
            hasRole = hasRole(claims, "ADMIN");
        }

        return !isTokenExpired && hasRole;
    }
    private boolean hasRole(Claims claims, String role) {
        String roles = (String)claims.get("roles");

        List<String> rolesList = Arrays.asList(roles.split(","));

        for(String s: rolesList) {
            if(role.contains(s.toUpperCase())) {
                return true;
            }
        }
        throw new RuntimeException("Incorrect role");
    }
}