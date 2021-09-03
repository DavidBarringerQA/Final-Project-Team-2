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
import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TrackUnitTest{

	@MockBean
	private TrackRepository repo;

	@Autowired
	private TrackService service;

	private Playlist playlist = new Playlist(1L, "PlaylistName", "PlaylistDesc", "artwork/path", new ArrayList<Track>());
	private Artist artist = new Artist(1L, "ArtistName", new ArrayList<Album>());
	private Genre genre = new Genre(1L, "GenreName", "GenreDesc", new ArrayList<Album>());
	private Album album = new Album(1L, "AlbumName", new ArrayList<Track>(), artist, genre, "cover/path");
	private String lyrics = "Test lyrics";

	@Test
	void testCreateSuccess(){
		Track item = new Track();
		item.setName("NewTrack");
		item.setAlbum(album);
		item.setPlaylist(playlist);
		item.setDuration(100);
		item.setLyrics(lyrics);
		Track result = new Track(1L, "NewTrack", album, playlist, 100, lyrics);
		TrackDTO expected = new TrackDTO(1L, "NewTrack", album, playlist, 100, lyrics);

		Mockito.when(repo.save(item)).thenReturn(result);

		assertEquals(expected, service.create(item));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testReadAll(){
		List<Track> result = new ArrayList<>();
		result.add(new Track(1L, "TestTrack", album, playlist, 100, lyrics));
		List<TrackDTO> expected = new ArrayList<>();
		expected.add(new TrackDTO(1L, "TestTrack", album, playlist, 100, lyrics));

		Mockito.when(repo.findAll()).thenReturn(result);

		assertEquals(expected, service.read());
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	void testReadSingleSuccess(){
		Track result = new Track(1L, "TestTrack", album, playlist, 100, lyrics);
		TrackDTO expected = new TrackDTO(1L, "TestTrack", album, playlist, 100, lyrics);

		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(result));

		assertEquals(expected, service.read(1L));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
	}

	@Test
	void testUpdateSuccess(){
		Track item = new Track(1L, "TestTrack", album, playlist, 100, lyrics);
		Track result = new Track(1L, "UpdatedName", album, playlist, 100, lyrics);
		TrackDTO expected = new TrackDTO(1L, "UpdatedName", album, playlist, 100, lyrics);

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
		Track item = new Track();

		Mockito.when(repo.save(item)).thenThrow(new ConstraintViolationException(null));

		assertEquals(null, service.create(item));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testReadSingleFail(){
		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());

		assertThrows(TrackNotFoundException.class, () -> {
				service.read(2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
	}

	@Test
	void testUpdateFailId(){
		Track item = new Track(2L, "UpdatedName", album, playlist, 100, lyrics);

		Mockito.when(repo.findById(2L)).thenReturn(Optional.empty());

		assertThrows(TrackNotFoundException.class, () -> {
				service.update(item, 2L);
			});
		Mockito.verify(repo, Mockito.times(1)).findById(2L);
		Mockito.verify(repo, Mockito.times(0)).save(item);
	}

	@Test
	void testUpdateFailValues(){
		Track item = new Track(1L, "TestTrack", album, playlist, 100, lyrics);
		Track result = new Track(1L, null, album, playlist, 100, lyrics);

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
