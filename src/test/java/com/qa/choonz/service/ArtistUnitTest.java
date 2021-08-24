package com.qa.choonz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import com.qa.choonz.exception.ArtistNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ArtistUnitTest{

	@MockBean
	private ArtistRepository repo;

	@Autowired
	private ArtistService service;

	@Test
	void testCreateSuccess(){
		Artist item = new Artist();
		item.setName("NewArtist");
		Artist result = new Artist(1L, "NewArtist", new ArrayList<Album>());
		ArtistDTO expected = new ArtistDTO(1L, "NewArtist", new ArrayList<Album>());

		Mockito.when(repo.save(item)).thenReturn(result);

		assertEquals(expected, service.create(item));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testReadAll(){
		List<Artist> result = new ArrayList<>();
		result.add(new Artist(1L, "TestArtist", new ArrayList<Album>()));
		List<ArtistDTO> expected = new ArrayList<>();
		expected.add(new ArtistDTO(1L, "TestArtist", new ArrayList<Album>()));

		Mockito.when(repo.findAll()).thenReturn(result);

		assertEquals(expected, service.read());
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	void testReadSingleSuccess(){
		Artist result = new Artist(1L, "TestArtist", new ArrayList<Album>());
		ArtistDTO expected = new ArtistDTO(1L, "TestArtist", new ArrayList<Album>());

		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(result));

		assertEquals(expected, service.read(1L));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
	}

	@Test
	void testUpdateSuccess(){
		Artist item = new Artist(1L, "TestArtist", new ArrayList<Album>());
		Artist result = new Artist(1L, "UpdatedName", new ArrayList<Album>());
		ArtistDTO expected = new ArtistDTO(1L, "UpdatedName", new ArrayList<Album>());
		
		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(item));
		Mockito.when(repo.save(result)).thenReturn(result);

		assertEquals(expected, service.update(result, 1L));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
		Mockito.verify(repo, Mockito.times(1)).save(result);
	}

	@Test
	void testDeleteSuccess(){
		Mockito.when(repo.existsById(1L)).thenReturn(false);

		assertTrue(service.delete(1L));
		Mockito.verify(repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(repo, Mockito.times(1)).existsById(1L);
	}

	@Test
	void testCreateFail(){
		Artist item = new Artist();
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

		assertThrows(ArtistNotFoundException.class, () -> {
				service.read(2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
	}

	@Test
	void testUpdateFailId(){
		Artist item = new Artist(2L, "UpdatedName", new ArrayList<Album>());

		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());

		assertThrows(ArtistNotFoundException.class, () -> {
				service.update(item, 2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
		Mockito.verify(repo, Mockito.times(0)).save(item);
	}

	@Test
	void testUpdateFailValues(){
		Artist item  = new Artist(1L, "TestGenre", new ArrayList<Album>());
		Artist result = new Artist(1L, null, new ArrayList<Album>());

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
