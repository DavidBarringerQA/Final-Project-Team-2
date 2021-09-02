package com.qa.choonz.rest.controller;

import com.qa.choonz.persistence.domain.AuthenticationRequest;
import com.qa.choonz.persistence.domain.AuthenticationResponse;
import com.qa.choonz.persistence.domain.ChoonzUser;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController{

	private UserService service;

	public UserController(UserService service){
		super();
		this.service = service;
	}

	@PostMapping("/auth")
	public ResponseEntity<AuthenticationResponse> loginReq(@RequestBody AuthenticationRequest authReq){
		AuthenticationResponse res = service.login(authReq);
		if(res != null){
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@RequestBody ChoonzUser user){
		return new ResponseEntity<>(service.register(user), HttpStatus.OK);
	}
}
