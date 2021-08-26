package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrackDTOTest{

	private TrackDTO track;

	@BeforeEach
	void setup(){
		Artist artist = new Artist(1L, "TestArtist", new ArrayList<Album>());
		Genre genre = new Genre(1L, "TestGenre", "TestDescription", new ArrayList<Album>());
		Album album = new Album(1L, "TestAlbum", new ArrayList<Track>(), artist, genre, "TestCover");
		Playlist playlist = new Playlist(1L, "TestPlaylist", "TestDescription", "TestArtwork", new ArrayList<Track>());
		track = new TrackDTO(1L, "TestTrack", album, playlist, 100, "TestLyrics");
	}

	@Test
	void testToString(){
		String expected = "TrackDTO [id=1, name=TestTrack, album={1=TestAlbum}, playlist={1=TestPlaylist}, "
			+ "duration=100, lyrics=TestLyrics]";
		String actual = track.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testToStringNoPlaylist(){
		track.setPlaylist(null);
		String expected = "TrackDTO [id=1, name=TestTrack, album={1=TestAlbum}, playlist={}, "
			+ "duration=100, lyrics=TestLyrics]";
		String actual = track.toString();
		assertEquals(expected, actual);
	}
}
