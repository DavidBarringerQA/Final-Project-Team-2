package com.qa.choonz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import com.qa.choonz.exception.GenreNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class GenreUnitTest{

	@MockBean
	private GenreRepository repo;

	@Autowired
	private GenreService service;

	@Test
	void testCreateSuccess(){
		Genre item = new Genre();
		item.setName("NewTestGenre");
		item.setDescription("NewTestDesc");
		item.setAlbums(new ArrayList<Album>());
		Genre result = new Genre(1L, "NewTestGenre", "NewTestDesc", new ArrayList<Album>());
		GenreDTO expected = new GenreDTO(1L, "NewTestGenre", "NewTestDesc", new ArrayList<Album>());

		Mockito.when(repo.save(item)).thenReturn(result);

		assertEquals(expected, service.create(item));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testReadAll(){
		List<Genre> result = new ArrayList<>();
		result.add(new Genre(1L, "TestGenre", "TestDescription", new ArrayList<Album>()));
		List<GenreDTO> expected = new ArrayList<>();
		expected.add(new GenreDTO(1L, "TestGenre", "TestDescription", new ArrayList<Album>()));

		Mockito.when(repo.findAll()).thenReturn(result);

		assertEquals(expected, service.read());
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	void testReadSingleSuccess(){
		Genre result = new Genre(1L, "TestGenre", "TestDescription", new ArrayList<Album>());
		GenreDTO expected = new GenreDTO(1L, "TestGenre", "TestDescription", new ArrayList<Album>());

		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(result));

		assertEquals(expected, service.read(1L));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
	}

	@Test
	void testUpdateSuccess(){
		Genre item = new Genre(1L, "TestGenre", "TestDescription", new ArrayList<Album>());
		Genre result = new Genre(1L, "UpdatedName", "UpdatedDesc", new ArrayList<Album>());
		GenreDTO expected = new GenreDTO(1L, "UpdatedName", "UpdatedDesc", new ArrayList<Album>());
		
		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(item));
		Mockito.when(repo.save(result)).thenReturn(result);

		assertEquals(expected, service.update(result, 1L));
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
	void testCreateFail(){
		Genre item = new Genre();
		item.setName("NewTestGenre");
		item.setAlbums(new ArrayList<Album>());

		Mockito.when(repo.save(item)).thenThrow(new ConstraintViolationException(null));
		assertThrows(ConstraintViolationException.class, () -> {
				service.create(item);
			});
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testReadSingleFail(){
		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());

		assertThrows(GenreNotFoundException.class, () -> {
				service.read(2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
	}

	@Test
	void testUpdateFailId(){
		Genre item = new Genre(2L, "UpdatedName", "UpdatedDesc", new ArrayList<Album>());

		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());

		assertThrows(GenreNotFoundException.class, () -> {
				service.update(item, 2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
		Mockito.verify(repo, Mockito.times(0)).save(item);
	}

	@Test
	void testUpdateFailValues(){
		Genre item  = new Genre(1L, "TestGenre", "TestDescription", new ArrayList<Album>());
		Genre result = new Genre(1L, null, "UpdatedDesc", new ArrayList<Album>());

		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(item));
		Mockito.when(repo.save(result)).thenThrow(new ConstraintViolationException(null));

		assertThrows(ConstraintViolationException.class, () -> {
				service.update(result, 1L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
		Mockito.verify(repo, Mockito.times(1)).save(result);
	}

	@Test
	void testDeleteFail(){
		doThrow(new EmptyResultDataAccessException(1)).when(repo).deleteById(1L);
		assertThrows(EmptyResultDataAccessException.class, () -> {
				service.delete(1L);
			});
		Mockito.verify(repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(repo, Mockito.times(0)).existsById(1L);
	}
}
