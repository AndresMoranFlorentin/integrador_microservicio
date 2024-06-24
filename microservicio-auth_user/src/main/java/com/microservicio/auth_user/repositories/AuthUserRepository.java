package com.microservicio.auth_user.repositories;

import com.microservicio.auth_user.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser,Long> {
     Optional<AuthUser> findByUserName(String userName);
}
