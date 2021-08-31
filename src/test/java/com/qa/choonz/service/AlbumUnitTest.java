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
import com.qa.choonz.exception.AlbumNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AlbumUnitTest{

	@MockBean
	private AlbumRepository repo;

	@Autowired
	private AlbumService service;

	private Artist artist = new Artist(1L, "ArtistName", new ArrayList<Album>());
	private Genre genre = new Genre(1L, "GenreName", "GenreDesc", new ArrayList<Album>());
	private String cover = "cover/path";

	@Test
	void testCreateSuccess(){
		Album item = new Album();
		item.setName("NewAlbum");
		item.setArtist(artist);
		item.setGenre(genre);
		item.setTracks(new ArrayList<Track>());
		item.setCover("cover/path");
		Album result = new Album(1L, "NewAlbum", new ArrayList<Track>(), artist, genre, cover);
		AlbumDTO expected = new AlbumDTO(1L, "NewAlbum", new ArrayList<Track>(), artist, genre, cover);

		Mockito.when(repo.save(item)).thenReturn(result);

		assertEquals(expected, service.create(item));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testReadAll(){
		List<Album> result = new ArrayList<>();
		result.add(new Album(1L, "NewAlbum", new ArrayList<Track>(), artist, genre, cover));
		List<AlbumDTO> expected = new ArrayList<>();
		expected.add(new AlbumDTO(1L, "NewAlbum", new ArrayList<Track>(), artist, genre, cover));

		Mockito.when(repo.findAll()).thenReturn(result);

		assertEquals(expected, service.read());
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	void testReadSingleSuccess(){
		Album result = new Album(1L, "NewAlbum", new ArrayList<Track>(), artist, genre, cover);
		AlbumDTO expected = new AlbumDTO(1L, "NewAlbum", new ArrayList<Track>(), artist, genre, cover);

		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(result));

		assertEquals(expected, service.read(1L));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
	}

	@Test
	void testUpdateSuccess(){
		Album item = new Album(1L, "TestAlbum", new ArrayList<Track>(), artist, genre, cover);
		Album result = new Album(1L, "UpdatedName", new ArrayList<Track>(), artist, genre, "new/path");
		AlbumDTO expected = new AlbumDTO(1L, "UpdatedName", new ArrayList<Track>(), artist, genre, "new/path");

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
		Album item = new Album();

		Mockito.when(repo.save(item)).thenThrow(new ConstraintViolationException(null));
		
		assertEquals(null, service.create(item));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testReadSingleFail(){
		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());

		assertThrows(AlbumNotFoundException.class, () -> {
				service.read(2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
	}

	@Test
	void testUpdateFailId(){
		Album item = new Album(2L, "UpdatedName", new ArrayList<Track>(), artist, genre, cover);

		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());

		assertThrows(AlbumNotFoundException.class, () -> {
				service.update(item, 2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
		Mockito.verify(repo, Mockito.times(0)).save(item);
	}

	@Test
	void testUpdateFailValues(){
		Album item = new Album(1L, "TestAlbum", new ArrayList<Track>(), artist, genre, cover);
		Album result = new Album(1L, null, new ArrayList<Track>(), artist, genre, cover);

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
	  assertEquals(null, service.delete(1L));
		Mockito.verify(repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(repo, Mockito.times(0)).existsById(1L);
	}
}
