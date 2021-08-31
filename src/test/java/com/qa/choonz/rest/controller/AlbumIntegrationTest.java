package com.qa.choonz.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AlbumIntegrationTest{

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;


	private Genre gen;
	private Artist art;
	
	@BeforeEach
	void setup(){
		gen = new Genre();
		gen.setId(1L);
		gen.setName("TestGenre");
		art = new Artist();
		art.setId(1L);
		art.setName("TestArtist");
	}

	@Test
	void testCreateSuccess() throws Exception{
		Album item = new Album();
		item.setName("NewAlbum");
		item.setArtist(art);
		item.setGenre(gen);
		item.setCover("NewCover");
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/albums/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		AlbumDTO expected = new AlbumDTO(2L, "NewAlbum", new ArrayList<Track>(), art, gen, "NewCover");
		String expectedJSON = mapper.writeValueAsString(expected);
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadAll() throws Exception{
		List<AlbumDTO> expected = new ArrayList<>();
		expected.add(new AlbumDTO(1L, "TestAlbum", new ArrayList<Track>(), art, gen, "TestCover"));
		String expectedJSON = mapper.writeValueAsString(expected);
		RequestBuilder mockRequest = get("/albums/read");
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadSingleSuccess() throws Exception{
		AlbumDTO expected = new AlbumDTO(1L, "TestAlbum", new ArrayList<Track>(), art, gen, "TestCover");
		String expectedJSON = mapper.writeValueAsString(expected);
		RequestBuilder mockRequest = get("/albums/read/1");
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testUpdateSuccess() throws Exception{
		Album item = new Album(1L, "UpdatedName", new ArrayList<Track>(), art, gen, "UpdatedCover");
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/albums/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		AlbumDTO expected = new AlbumDTO(1L, "UpdatedName", new ArrayList<Track>(), art, gen, "UpdatedCover");
		String expectedJSON = mapper.writeValueAsString(expected);
		ResultMatcher matchStatus = status().isAccepted();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testDeleteSuccess() throws Exception{
		RequestBuilder mockRequest = delete("/albums/delete/1");
		ResultMatcher matchStatus = status().isNoContent();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testCreateFail() throws Exception{
		Album item = new Album();
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/albums/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isBadRequest();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testReadFail() throws Exception{
		RequestBuilder mockRequest = get("/albums/read/2");
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testUpdateIdFail() throws Exception{
		Album item = new Album(1L, "UpdatedName", new ArrayList<Track>(), art, gen, "UpdatedCover");
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/albums/update/2")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testUpdateDataFail() throws Exception{
		Album item = new Album(1L, null, new ArrayList<Track>(), art, gen, "UpdatedCover");
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/albums/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isBadRequest();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testDeleteFail() throws Exception{
		RequestBuilder mockRequest = delete("/albums/delete/2");
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}
}
