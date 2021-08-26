package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlbumDTOTest{

	private AlbumDTO album;

	@BeforeEach
	void setup(){
		List<Track> tracks = new ArrayList<>();
		Track track = new Track();
		track.setId(1L);
		track.setName("TestTrack");
		tracks.add(track);
		Artist artist = new Artist(1L, "TestArtist", new ArrayList<Album>());
		Genre genre = new Genre(1L, "TestGenre", "TestDescription", new ArrayList<Album>());
		album = new AlbumDTO(1L, "TestAlbum", tracks, artist, genre, "TestCover");
	}

	@Test
	void testToString(){
		String expected = "AlbumDTO [id=1, name=TestAlbum, tracks={1=TestTrack}, artist={1=TestArtist}, "
			+ "genre={1=TestGenre}, cover=TestCover]";
		String actual = album.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testToStringListEmpty(){
		album.setTracks(new ArrayList<Track>());
		String expected = "AlbumDTO [id=1, name=TestAlbum, tracks={}, artist={1=TestArtist}, "
			+ "genre={1=TestGenre}, cover=TestCover]";
		String actual = album.toString();
		assertEquals(expected, actual);
	}
}
