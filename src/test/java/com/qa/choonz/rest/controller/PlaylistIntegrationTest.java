package com.qa.choonz.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.AuthenticationRequest;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.UserService;
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
class PlaylistIntegrationTest{

	@Autowired
	private MockMvc mock;

	@Autowired
	private UserService userService;

	@Autowired
	private ObjectMapper mapper;

	private String token;

	@BeforeEach
	void setup() throws Exception{
		AuthenticationRequest req = new AuthenticationRequest("user", "pass");
		token = userService.login(req).getToken();
	}

	@Test
	void testCreateSuccess() throws Exception{
		Playlist item = new Playlist();
		item.setName("NewPlaylist");
		item.setDescription("NewDesc");
		item.setArtwork("artwork/path");
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/playlists/create")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", "Bearer " + token)
			.content(itemJSON);
		PlaylistDTO expected = new PlaylistDTO(2L, "NewPlaylist", "NewDesc", "artwork/path", new ArrayList<Track>());
		String expectedJSON = mapper.writeValueAsString(expected);
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadAll() throws Exception{
		List<PlaylistDTO> expected = new ArrayList<>();
		expected.add(new PlaylistDTO(1L, "TestPlaylist", "TestDescription", "TestArtwork", new ArrayList<Track>()));
		String expectedJSON = mapper.writeValueAsString(expected);
		RequestBuilder mockRequest = get("/playlists/read");
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadSingleSuccess() throws Exception{
		PlaylistDTO expected = new PlaylistDTO(1L, "TestPlaylist", "TestDescription", "TestArtwork", new ArrayList<Track>());
		String expectedJSON = mapper.writeValueAsString(expected);
		RequestBuilder mockRequest = get("/playlists/read/1");
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testUpdateSuccess() throws Exception{
		Playlist item = new Playlist(1L, "UpdatedName", "UpdatedDesc", "UpdatedArt", new ArrayList<Track>());
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/playlists/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", "Bearer " + token)
			.content(itemJSON);
		PlaylistDTO expected = new PlaylistDTO(1L, "UpdatedName", "UpdatedDesc", "UpdatedArt", new ArrayList<Track>());
		String expectedJSON = mapper.writeValueAsString(expected);
		ResultMatcher matchStatus = status().isAccepted();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testDeleteSuccess() throws Exception{
		RequestBuilder mockRequest = delete("/playlists/delete/1")
			.header("Authorization", "Bearer " + token);
		ResultMatcher matchStatus = status().isNoContent();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testCreateFail() throws Exception{
		Playlist item = new Playlist();
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/playlists/create")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", "Bearer " + token)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isBadRequest();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testReadFail() throws Exception{
		RequestBuilder mockRequest = get("/playlists/read/2");
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testUpdateIdFail() throws Exception{
		Playlist item = new Playlist(1L, "UpdatedName", "UpdatedDesc", "UpdatedArt", new ArrayList<Track>());
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/playlists/update/2")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", "Bearer " + token)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testUpdateDataFail() throws Exception{
		Playlist item = new Playlist(1L, null, null, null, new ArrayList<Track>());
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/playlists/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", "Bearer " + token)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isBadRequest();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testDeleteFail() throws Exception{
		RequestBuilder mockRequest = delete("/playlists/delete/2")
			.header("Authorization", "Bearer " + token);
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}
}
