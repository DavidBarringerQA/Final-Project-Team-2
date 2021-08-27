package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import com.qa.choonz.persistence.domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaylistDTOTest{

	private PlaylistDTO playlist;

	@BeforeEach
	void setup(){
		List<Track> tracks = new ArrayList<>();
		Track track = new Track();
		track.setId(1L);
		track.setName("TestTrack");
		tracks.add(track);
		playlist = new PlaylistDTO(1L, "TestPlaylist", "TestDescription", "TestArtwork", tracks);
	}

	@Test
	void testToString(){
		String expected = "PlaylistDTO [id=1, name=TestPlaylist, description=TestDescription, artwork=TestArtwork, tracks={1=TestTrack}]";
		String actual = playlist.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testToStringListEmpty(){
		playlist.setTracks(new ArrayList<Track>());
		String expected = "PlaylistDTO [id=1, name=TestPlaylist, description=TestDescription, artwork=TestArtwork, tracks={}]";
		String actual = playlist.toString();
		assertEquals(expected, actual);
	}
}
