package com.microservicio.auth_user.repositories;

import com.microservicio.auth_user.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser,Long> {
    //@Query("SELECT a FROM AuthUser a WHERE (a.userName =: nombre)")
    Optional<AuthUser> findByUserName(String userName);
}
