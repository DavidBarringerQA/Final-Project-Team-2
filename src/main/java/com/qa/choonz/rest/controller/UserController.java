package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.AuthenticationRequest;
import com.qa.choonz.persistence.domain.AuthenticationResponse;
import com.qa.choonz.persistence.domain.ChoonzUser;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.JwtUtil;
import com.qa.choonz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService service;

	@Autowired
	private JwtUtil jwtTokenUtil;

	public UserController(UserService service){
		super();
		this.service = service;
	}

	@PostMapping("/auth")
	public ResponseEntity<AuthenticationResponse> loginReq(@RequestBody AuthenticationRequest authReq){
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		final UserDetails userDetails = service.loadUserByUsername(authReq.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@RequestBody ChoonzUser user){
		return new ResponseEntity<>(service.register(user), HttpStatus.OK);
	}
}
