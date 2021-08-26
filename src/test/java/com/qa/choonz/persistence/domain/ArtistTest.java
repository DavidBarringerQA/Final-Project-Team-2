package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtistTest{

	private Artist artist;
	
	@BeforeEach
	public void setup(){
		Album album = new Album();
		album.setName("TestAlbum");
		List<Album> albums = new ArrayList<>();
		albums.add(album);
		artist = new Artist(1L, "TestArtist", albums);
	}

	@Test
	public void toStringTest(){
		String expected = "Artist [id=1, name=TestArtist, albums=[TestAlbum]]";
		String actual = artist.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void toStringEmptyListTest(){
		artist.setAlbums(new ArrayList<Album>());
		String expected = "Artist [id=1, name=TestArtist, albums=[]]";
		String actual = artist.toString();
		assertEquals(expected, actual);
	}
}
