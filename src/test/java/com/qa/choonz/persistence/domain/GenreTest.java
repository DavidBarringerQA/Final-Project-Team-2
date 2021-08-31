package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenreTest{

	private Genre genre;
	
	@BeforeEach
	void setup(){
		Album album = new Album();
		album.setName("TestAlbum");
		List<Album> albums = new ArrayList<>();
		albums.add(album);
		genre = new Genre(1L, "TestGenre", "TestDescription", albums);
	}

	@Test
	void toStringTest(){
		String expected = "Genre [id=1, name=TestGenre, description=TestDescription, albums=[TestAlbum]]";
		String actual = genre.toString();
		assertEquals(expected, actual);
	}

	@Test
	void toStringEmptyListTest(){
		genre.setAlbums(new ArrayList<Album>());
		String expected = "Genre [id=1, name=TestGenre, description=TestDescription, albums=[]]";
		String actual = genre.toString();
		assertEquals(expected, actual);
	}
}
