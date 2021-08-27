package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import com.qa.choonz.persistence.domain.Album;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenreDTOTest{

	private GenreDTO genre;

	@BeforeEach
	void setup(){
		List<Album> albums = new ArrayList<>();
		Album album = new Album();
		album.setId(1L);
		album.setName("TestAlbum");
		albums.add(album);
		genre = new GenreDTO(1L, "TestGenre", "TestDescription", albums);
	}

	@Test
	void testToString(){
		String expected = "GenreDTO [id=1, name=TestGenre, description=TestDescription, albums={1=TestAlbum}]";
		String actual = genre.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testToStringListEmpty(){
		genre.setAlbums(new ArrayList<Album>());
		String expected = "GenreDTO [id=1, name=TestGenre, description=TestDescription, albums={}]";
		String actual = genre.toString();
		assertEquals(expected, actual);
	}
}
