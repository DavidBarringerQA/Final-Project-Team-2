package com.qa.choonz.service;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintViolationException;
import com.qa.choonz.exception.UserNotFoundException;
import com.qa.choonz.persistence.domain.AuthenticationRequest;
import com.qa.choonz.persistence.domain.AuthenticationResponse;
import com.qa.choonz.persistence.domain.ChoonzUser;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

// Adapted from javainuse.com/webseries/spring-security-jwt
@Service
public class UserService implements UserDetailsService{
	

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
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
	public UserDetails loadUserByUsername(String username) throws UserNotFoundException{
		List<SimpleGrantedAuthority> roles;
		ChoonzUser user = repo.findByUsername(username);
		if(user != null){
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getUsername(), user.getPassword(), roles);
		}
		throw new UserNotFoundException();
	}

	public UserDTO register(ChoonzUser user){
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		try{
			ChoonzUser created = this.repo.save(user);
			return this.mapToDTO(created);
		} catch(ConstraintViolationException e){
			return null;
		}
	}

	public AuthenticationResponse login(AuthenticationRequest authReq){
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
		final UserDetails userDetails = loadUserByUsername(authReq.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return new AuthenticationResponse(token);
	}

	public UserDTO update(ChoonzUser user, long id){
		ChoonzUser toUpdate = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
		toUpdate.setUsername(user.getUsername());
		if(user.getPassword() != null){
			toUpdate.setPassword(bcryptEncoder.encode(user.getPassword()));
		}
		try{
			ChoonzUser updated = this.repo.save(toUpdate);
			return this.mapToDTO(updated);
		} catch(TransactionSystemException e){
			return null;
		}
	}

	public Boolean delete(long id){
		try{
			this.repo.deleteById(id);
			return !this.repo.existsById(id);
		} catch(EmptyResultDataAccessException e){
			return null;
		}
	}
}
