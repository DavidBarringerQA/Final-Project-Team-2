package com.qa.choonz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import com.qa.choonz.exception.UserNotFoundException;
import com.qa.choonz.persistence.domain.AuthenticationRequest;
import com.qa.choonz.persistence.domain.AuthenticationResponse;
import com.qa.choonz.persistence.domain.ChoonzUser;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserUnitTest{

	@MockBean
	private UserRepository repo;

	@Autowired
	private UserService service;
	
	@MockBean
	private PasswordEncoder mockEncoder;
	@MockBean
	private JwtUtil jwtTokenUtil;
	@MockBean
	private AuthenticationManager authManager;

	@Test
	void testRegisterSuccess(){
		ChoonzUser item = new ChoonzUser();
		item.setUsername("NewName");
		item.setPassword("NewPass");
		ChoonzUser result = new ChoonzUser(1L, "NewName", "hashedPass", "ROLE_USER");
		UserDTO expected = new UserDTO(1L, "NewName");

		Mockito.when(mockEncoder.encode("NewPass")).thenReturn("hashedPass");
		Mockito.when(repo.save(item)).thenReturn(result);

		assertEquals(expected, service.register(item));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testLoginSuccess(){
		String hashedPass = new BCryptPasswordEncoder().encode("TestPass");
	  System.out.println(new BCryptPasswordEncoder().encode("pass"));
		ChoonzUser item = new ChoonzUser(1L, "TestName", hashedPass, "ROLE_USER");
		AuthenticationRequest req = new AuthenticationRequest("TestName", "TestPass");
		List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority(item.getRole()));
		UserDetails userDetails = new User(item.getUsername(), item.getPassword(), roles);
		AuthenticationResponse expected = new AuthenticationResponse("token");

		Mockito.when(repo.findByUsername("TestName")).thenReturn(item);
		Mockito.when(jwtTokenUtil.generateToken(userDetails)).thenReturn("token");

		assertEquals(expected.getToken(), service.login(req).getToken());
		Mockito.verify(repo, Mockito.times(1)).findByUsername("TestName");
	}

	@Test
	void testUpdateSuccess(){
		ChoonzUser item = new ChoonzUser(1L, "TestName", "hashedPass", "ROLE_USER");
		ChoonzUser req = new ChoonzUser(1L, "UpdatedName", "UpdatedPass", "ROLE_USER");
		ChoonzUser result = new ChoonzUser(1L, "UpdatedName", "updatedHash", "ROLE_USER");
		UserDTO expected = new UserDTO(1L, "UpdatedName");

		Mockito.when(mockEncoder.encode("UpdatedPass")).thenReturn("updatedHash");
		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(item));
		Mockito.when(repo.save(result)).thenReturn(result);

		assertEquals(expected, service.update(req, 1L));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
		Mockito.verify(repo, Mockito.times(1)).save(result);
	}

	@Test
	void testDeleteSuccess(){
		doNothing().when(repo).deleteById(1L);
		Mockito.when(repo.existsById(1L)).thenReturn(false);

		assertTrue(service.delete(1L));
		Mockito.verify(repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(repo, Mockito.times(1)).existsById(1L);
	}

	@Test
	void testRegisterFail(){
		ChoonzUser item = new ChoonzUser();
		item.setPassword("Test");

		Mockito.when(repo.save(item)).thenThrow(new ConstraintViolationException(null));

		assertEquals(null, service.register(item));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testLoginFail(){
		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> {
				service.login(new AuthenticationRequest("TestName", "TestPass"));
			});
		Mockito.verify(repo, Mockito.times(1)).findByUsername("TestName");
	}

	@Test
	void testUpdateFailId(){
		ChoonzUser item = new ChoonzUser(2L, "UpdatedName", "hashedPass", "ROLE_USER");

		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());

		assertThrows(UserNotFoundException.class, () -> {
				service.update(item, 2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
		Mockito.verify(repo, Mockito.times(0)).save(item);
	}

	@Test
	void testUpdateFailValues(){
		ChoonzUser item = new ChoonzUser(1L, "TestName", "hashedPass", "ROLE_USER");
		ChoonzUser result = new ChoonzUser(1L, null, "hashedPass", "ROLE_USER");
		ChoonzUser req = new ChoonzUser(1L, null, null, "ROLE_USER");

		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(item));
		Mockito.when(repo.save(result)).thenThrow(new ConstraintViolationException(null));

		assertThrows(ConstraintViolationException.class, () -> {
				service.update(req, 1L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
		Mockito.verify(repo, Mockito.times(1)).save(result);
	}

	@Test
	void testDeleteFail(){
		doThrow(new EmptyResultDataAccessException(1)).when(repo).deleteById(1L);

		assertEquals(null, service.delete(1L));
		Mockito.verify(repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(repo, Mockito.times(0)).existsById(1L);
	}
}
