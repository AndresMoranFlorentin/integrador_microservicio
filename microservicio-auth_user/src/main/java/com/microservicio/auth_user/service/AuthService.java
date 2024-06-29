package com.microservicio.auth_user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.microservicio.auth_user.dto.UserCreateDto;
import com.microservicio.auth_user.model.Role;
import com.microservicio.auth_user.exception.*;

import com.microservicio.auth_user.model.UserAuth;
import com.microservicio.auth_user.repository.IAuthRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService{
	
	private IAuthRepository authRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public AuthService(IAuthRepository authRepository) {
		this.authRepository = authRepository;
		this.bCryptPasswordEncoder =  new BCryptPasswordEncoder();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UserNotFoundException {

		return authRepository.findByUsername(username)
				.map(user -> new User(user.getUserName(), user.getPassword(), getAuthorities(user.getRoles())))
				.orElseThrow(() -> new UserNotFoundException("User not found","User not found with username: " + username));
	}
	
	public void register(UserCreateDto user) {
		String bCryptedPassword = encodePassword(user.getPassword());
		List<Role> roles = user.getRoles().stream().map(Role::new).toList();
		
		this.authRepository.save(UserAuth.builder()
				.username(user.getUsername())
				.password(bCryptedPassword)
				.roles(roles)
				.build());
	}
	
	private String encodePassword(String password) {
		
		return bCryptPasswordEncoder.encode(password);
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles){
		List<GrantedAuthority> authorities = new ArrayList<>();
	    for (Role role: roles) {
	        authorities.add(new SimpleGrantedAuthority(role.getRole()));
	    }
	    return authorities;
	}

}
