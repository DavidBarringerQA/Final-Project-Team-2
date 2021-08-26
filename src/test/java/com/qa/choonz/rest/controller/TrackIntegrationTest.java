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
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.TrackDTO;
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
class TrackIntegrationTest{

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;


	private Playlist playlist;
	private Album album;
	
	@BeforeEach
	void setup(){
	  album = new Album();
		album.setId(1L);
		album.setName("TestAlbum");
		playlist = new Playlist();
		playlist.setId(1L);
		playlist.setName("TestPlaylist");
	}

	@Test
	void testCreateSuccess() throws Exception{
		Track item = new Track();
		item.setName("NewTrack");
		item.setLyrics("NewLyrics");
		item.setDuration(200);
		item.setAlbum(album);
		item.setPlaylist(playlist);
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/tracks/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		TrackDTO expected = new TrackDTO(2L, "NewTrack", album, playlist, 200, "NewLyrics");
		String expectedJSON = mapper.writeValueAsString(expected);
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testCreateSuccessNoPlaylist() throws Exception{
		Track item = new Track();
		item.setName("NewTrack");
		item.setLyrics("NewLyrics");
		item.setDuration(200);
		item.setAlbum(album);
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/tracks/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		TrackDTO expected = new TrackDTO(2L, "NewTrack", album, null, 200, "NewLyrics");
		String expectedJSON = mapper.writeValueAsString(expected);
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadAll() throws Exception{
		List<TrackDTO> expected = new ArrayList<>();
		expected.add(new TrackDTO(1L, "TestTrack", album, playlist, 100, "TestLyrics"));
		String expectedJSON = mapper.writeValueAsString(expected);
		RequestBuilder mockRequest = get("/tracks/read");
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadSingleSuccess() throws Exception{
		TrackDTO expected = new TrackDTO(1L, "TestTrack", album, playlist, 100, "TestLyrics");
		String expectedJSON = mapper.writeValueAsString(expected);
		RequestBuilder mockRequest = get("/tracks/read/1");
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testUpdateSuccess() throws Exception{
		Track item = new Track(1L, "UpdatedName", album, null, 300, "UpdatedLyrics");
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/tracks/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		TrackDTO expected = new TrackDTO(1L, "UpdatedName", album, null, 300, "UpdatedLyrics");
		String expectedJSON = mapper.writeValueAsString(expected);
		ResultMatcher matchStatus = status().isAccepted();
		ResultMatcher matchBody = content().json(expectedJSON);
		mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testDeleteSuccess() throws Exception{
		RequestBuilder mockRequest = delete("/tracks/delete/1");
		ResultMatcher matchStatus = status().isNoContent();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testCreateFail() throws Exception{
		Track item = new Track();
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/tracks/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isBadRequest();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testReadFail() throws Exception{
		RequestBuilder mockRequest = get("/tracks/read/2");
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testUpdateIdFail() throws Exception{
		Track item = new Track(1L, "UpdatedName", album, null, 300, "UpdatedLyrics");
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/tracks/update/2")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testUpdateDataFail() throws Exception{
		Track item = new Track(1L, null, null, null, 300, null);
		String itemJSON = mapper.writeValueAsString(item);
		RequestBuilder mockRequest = post("/tracks/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(itemJSON);
		ResultMatcher matchStatus = status().isBadRequest();
		mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testDeleteFail() throws Exception{
		RequestBuilder mockRequest = delete("/tracks/delete/2");
		ResultMatcher matchStatus = status().isNotFound();
		mock.perform(mockRequest).andExpect(matchStatus);
	}
}
