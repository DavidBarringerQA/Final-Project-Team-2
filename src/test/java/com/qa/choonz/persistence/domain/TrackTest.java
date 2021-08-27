package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrackTest{

	private Track track;
	
	@BeforeEach
	void setup(){
		Album album = new Album();
		album.setName("TestAlbum");
		Playlist playlist = new Playlist();
		playlist.setName("TestPlaylist");
		track = new Track(1L, "TestTrack", album, playlist, 100, "TestLyrics");
	}

	@Test
	void toStringTest(){
		String expected = "Track [id=1, name=TestTrack, album=TestAlbum, playlist=TestPlaylist, duration=100, lyrics=TestLyrics]";
		String actual = track.toString();
		assertEquals(expected, actual);
	}
}
