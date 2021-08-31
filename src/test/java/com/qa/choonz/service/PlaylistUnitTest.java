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
import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PlaylistUnitTest{

	@MockBean
	private PlaylistRepository repo;

	@Autowired
	private PlaylistService service;

	private String artwork = "artwork/path";

	@Test
	void testCreateSuccess(){
		Playlist item = new Playlist();
		item.setName("NewPlaylist");
		item.setDescription("NewDescription");
		item.setArtwork(artwork);
		item.setTracks(new ArrayList<Track>());
		Playlist result = new Playlist(1L, "NewPlaylist", "NewDescription", artwork, new ArrayList<Track>());
		PlaylistDTO expected = new PlaylistDTO(1L, "NewPlaylist", "NewDescription", artwork, new ArrayList<Track>());

		Mockito.when(repo.save(item)).thenReturn(result);

		assertEquals(expected, service.create(item));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testReadAll(){
		List<Playlist> result = new ArrayList<>();
		result.add(new Playlist(1L, "TestPlaylist", "TestDesc", artwork, new ArrayList<Track>()));
		List<PlaylistDTO> expected = new ArrayList<>();
		expected.add(new PlaylistDTO(1L, "TestPlaylist", "TestDesc", artwork, new ArrayList<Track>()));

		Mockito.when(repo.findAll()).thenReturn(result);

		assertEquals(expected, service.read());
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	void testReadSingleSuccess(){
		Playlist result = new Playlist(1L, "TestPlaylist", "TestDesc", artwork, new ArrayList<Track>());
		PlaylistDTO expected = new PlaylistDTO(1L, "TestPlaylist", "TestDesc", artwork, new ArrayList<Track>());

		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(result));

		assertEquals(expected, service.read(1L));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
	}

	@Test
	void testUpdateSuccess(){
		Playlist item = new Playlist(1L, "TestPlaylist", "TestDesc", artwork, new ArrayList<Track>());
		Playlist result = new Playlist(1L, "UpdatedName", "UpdatedDesc", artwork, new ArrayList<Track>());
		PlaylistDTO expected = new PlaylistDTO(1L, "UpdatedName", "UpdatedDesc", artwork, new ArrayList<Track>());

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
		Playlist item = new Playlist();

		Mockito.when(repo.save(item)).thenThrow(new ConstraintViolationException(null));

		assertEquals(null, service.create(item));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testReadSingleFail(){
		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());

		assertThrows(PlaylistNotFoundException.class, () -> {
				service.read(2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
	}

	@Test
	void testUpdateFailId(){
		Playlist item = new Playlist(2L, "UpdatedName", "UpdatedDesc", artwork, new ArrayList<Track>());

		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());

		assertThrows(PlaylistNotFoundException.class, () -> {
				service.update(item, 2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
		Mockito.verify(repo, Mockito.times(0)).save(item);
	}

	@Test
	void testUpdateFailValues(){
		Playlist item = new Playlist(1L, "TestPlaylist", "TestDesc", artwork, new ArrayList<Track>());
		Playlist result = new Playlist(1L, null, "TestDesc", artwork, new ArrayList<Track>());

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
