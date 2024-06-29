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
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();

        boolean isTokenExpired = claims.getExpiration().before(new Date());
        System.out.println("claims: " + claims);
        // Verifica si el usuario tiene el rol de "super-admin" (ADMIN, USER, MAINTENANCE)

      //  System.out.println(path);

        if (path.contains("/admin")) {
            System.out.println("Cualquier cosa administrador");
            hasRole = hasRole(claims, "ADMIN");

        }
        else if (path.contains("/cliente")) {
            System.out.println("Cualquier cosa cliente");
            hasRole = hasRole(claims, "ADMIN,USER");

        }
        else if (path.contains("/mantenimiento")) {
            System.out.println("Cualquier cosa mantenimiento");
            hasRole = hasRole(claims, "ADMIN,MAINTENANCE");
        }
        else if (path.contains("/reporteXkmConPausa")) {
            System.out.println("entro aca");
            hasRole = hasRole(claims, "MAINTENANCE");
            System.out.println(hasRole);
            System.out.println(hasRole(claims, "MAINTENANCE"));
            System.out.println(path);

        }

//        } else if (path.contains("/api/mantenimiento/"))
        //        return (!isTokenExpired && hasRole);
        return (hasRole);
    }

    private boolean hasRole(Claims claims, String role) {
        String roles = (String) claims.get("roles");

        List<String> rolesList = Arrays.asList(roles.split(","));

        for (String s : rolesList) {
            if (role.contains(s.toUpperCase())) {
                return true;
            }
        }
        throw new RuntimeException("Incorrect role");
    }
}