package com.microservicio.auth_user.repository;

import java.util.Optional;

import com.microservicio.auth_user.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAuthRepository extends JpaRepository<UserAuth, Long> {
	
	Optional<UserAuth> findByUsername(String username);
}
