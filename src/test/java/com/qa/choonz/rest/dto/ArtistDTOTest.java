package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import com.qa.choonz.persistence.domain.Album;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtistDTOTest{

	private ArtistDTO artist;

	@BeforeEach
	void setup(){
		List<Album> albums = new ArrayList<>();
		Album album = new Album();
		album.setId(1L);
		album.setName("TestAlbum");
		albums.add(album);
		artist = new ArtistDTO(1L, "TestArtist", albums);
	}

	@Test
	void testToString(){
		String expected = "ArtistDTO [id=1, name=TestArtist, albums={1=TestAlbum}]";
		String actual = artist.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testToStringListEmpty(){
		artist.setAlbums(new ArrayList<Album>());
		String expected = "ArtistDTO [id=1, name=TestArtist, albums={}]";
		String actual = artist.toString();
		assertEquals(expected, actual);
	}
}
