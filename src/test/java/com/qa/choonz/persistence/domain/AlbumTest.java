package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlbumTest{

	private Album album;
	
	@BeforeEach
	void setup(){
		Track track = new Track();
		track.setName("TestTrack");
		List<Track> tracks = new ArrayList<>();
		tracks.add(track);
		Artist artist = new Artist();
		artist.setName("TestArtist");
		Genre genre = new Genre();
		genre.setName("TestGenre");
		album = new Album(1L, "TestAlbum", tracks, artist, genre, "TestCover");
	}

	@Test
	void toStringTest(){
		String expected = "Album [id=1, name=TestAlbum, tracks=[TestTrack], artist=TestArtist, genre=TestGenre, cover=TestCover]";
		String actual = album.toString();
		assertEquals(expected, actual);
	}

	@Test
	void toStringEmptyListTest(){
		album.setTracks(new ArrayList<Track>());
		String expected = "Album [id=1, name=TestAlbum, tracks=[], artist=TestArtist, genre=TestGenre, cover=TestCover]";
		String actual = album.toString();
		assertEquals(expected, actual);
	}
}
