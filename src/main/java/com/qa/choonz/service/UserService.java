package com.qa.choonz.service;

import java.util.Arrays;
import java.util.List;
import com.qa.choonz.persistence.domain.ChoonzUser;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Adapted from javainuse.com/webseries/spring-security-jwt
@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository repo;
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public UserService(ModelMapper mapper){
		super();
//		this.repo = repo;
		this.mapper = mapper;
	}

	private UserDTO mapToDTO(ChoonzUser user){
		return this.mapper.map(user, UserDTO.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		List<SimpleGrantedAuthority> roles;
		ChoonzUser user = repo.findByUsername(username);
		if(user != null){
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getUsername(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User with username: " + username + " does not exist.");
	}

	public UserDTO register(ChoonzUser user){
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		return this.mapToDTO(repo.save(user));
	}
}
