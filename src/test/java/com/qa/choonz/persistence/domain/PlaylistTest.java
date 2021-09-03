package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaylistTest{

	private Playlist playlist;
	
	@BeforeEach
	void setup(){
		Track track = new Track();
		track.setName("TestTrack");
		List<Track> tracks = new ArrayList<>();
		tracks.add(track);
		playlist = new Playlist(1L, "TestPlaylist", "TestDescription", "TestArtwork", tracks);
	}

	@Test
	void toStringTest(){
		String expected = "Playlist [id=1, name=TestPlaylist, description=TestDescription, artwork=TestArtwork, tracks=[TestTrack]]";
		String actual = playlist.toString();
		assertEquals(expected, actual);
	}

	@Test
	void toStringEmptyListTest(){
		playlist.setTracks(new ArrayList<Track>());
		String expected = "Playlist [id=1, name=TestPlaylist, description=TestDescription, artwork=TestArtwork, tracks=[]]";
		String actual = playlist.toString();
		assertEquals(expected, actual);
	}
}
