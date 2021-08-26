package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenreTest{

	private Genre genre;
	
	@BeforeEach
	public void setup(){
		Album album = new Album();
		album.setName("TestAlbum");
		List<Album> albums = new ArrayList<>();
		albums.add(album);
		genre = new Genre(1L, "TestGenre", "TestDescription", albums);
	}

	@Test
	public void toStringTest(){
		String expected = "Genre [id=1, name=TestGenre, description=TestDescription, albums=[TestAlbum]]";
		String actual = genre.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void toStringEmptyListTest(){
		genre.setAlbums(new ArrayList<Album>());
		String expected = "Genre [id=1, name=TestGenre, description=TestDescription, albums=[]]";
		String actual = genre.toString();
		assertEquals(expected, actual);
	}
}
