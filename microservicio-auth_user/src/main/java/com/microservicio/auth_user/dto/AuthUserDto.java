package com.microservicio.auth_user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Builder
public class AuthUserDto {
    private String userName;
    private String password;
}
