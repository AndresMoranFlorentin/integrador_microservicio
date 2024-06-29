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


        // Verifica si el usuario tiene el rol de "super-admin" (ADMIN, USER, MAINTENANCE)
          boolean isSuperAdmin = (hasRole(claims, "ADMIN"));
          boolean tieneAlMenosUnRol=hasRole(claims, "ADMIN") || hasRole(claims, "USER") || hasRole(claims, "MAINTENANCE");
        System.out.println(path);
        if (path.contains("/admin")) {
            System.out.println("Cualquier cosa");
            hasRole = hasRole(claims,"ADMIN");

        }
        if (path.contains("/cliente")) {
                 System.out.println("Cualquier cosa");
                hasRole = hasRole(claims,"ADMIN,USER");

        }
//        } else if (path.contains("/api/mantenimiento/")) {
//            System.out.println("entro al path mantenimiento: ");
//            System.out.println(hasRole(claims, "MAINTENANCE") || isSuperAdmin );
//            if (hasRole(claims, "MAINTENANCE") || isSuperAdmin ) {
//                hasRole = true;
//            }
//            hasRole=false;
//        } else if (path.contains("/api/monopatin/")) {
//            System.out.println("entro al path monopatin: ");
//            if (tieneAlMenosUnRol) {
//                hasRole = true;
//            }
//            hasRole=false;
//        }
         return (!isTokenExpired && hasRole);
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