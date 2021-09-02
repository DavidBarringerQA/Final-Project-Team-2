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
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.GenreDTO;

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
class ArtistIntegrationTest{

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;

	private String token;

	@BeforeEach
	void setup(){
		
	}

	@Test
	void testCreateSuccess() throws Exception{
		Artist item = new Artist();
		item.setName("NewArtist");
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/artists/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ArtistDTO expected = new ArtistDTO(2L, "NewArtist", new ArrayList<Album>());
		String expectedJSON = mapper.writeValueAsString(expected);
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadAll() throws Exception{
		List<ArtistDTO> expected = new ArrayList<>();
		expected.add(new ArtistDTO(1L, "TestArtist", new ArrayList<Album>()));
		String expectedJSON = mapper.writeValueAsString(expected);
		RequestBuilder mockRequest = get("/artists/read");
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadSingleSuccess() throws Exception{
		ArtistDTO expected = new ArtistDTO(1L, "TestArtist", new ArrayList<Album>());
		String expectedJSON = mapper.writeValueAsString(expected);
		RequestBuilder mockRequest = get("/artists/read/1");
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testUpdateSuccess() throws Exception{
		Artist item = new Artist(1L, "UpdatedName", new ArrayList<Album>());
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/artists/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ArtistDTO expected = new ArtistDTO(1L, "UpdatedName", new ArrayList<Album>());
		String expectedJSON = mapper.writeValueAsString(expected);
		ResultMatcher matchStatus = status().isAccepted();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testDeleteSuccess() throws Exception{
		RequestBuilder mockRequest = delete("/artists/delete/1");
		ResultMatcher matchStatus = status().isNoContent();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testCreateFail() throws Exception{
		Artist item = new Artist();
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/artists/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isBadRequest();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testReadFail() throws Exception{
		RequestBuilder mockRequest = get("/artists/read/2");
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testUpdateIdFail() throws Exception{
		Artist item = new Artist(1L, "UpdatedName", new ArrayList<Album>());
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/artists/update/2")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testUpdateDataFail() throws Exception{
		Artist item = new Artist(1L, null, new ArrayList<Album>());
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/artists/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isBadRequest();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testDeleteFail() throws Exception{
		RequestBuilder mockRequest = delete("/artists/delete/2");
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}
}
