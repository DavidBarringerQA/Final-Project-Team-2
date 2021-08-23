package com.qa.choonz.rest.dto;

import java.util.HashMap;
import java.util.Objects;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;

public class TrackDTO {

	private long id;
	private String name;
	private HashMap<Long, String> album;
	private HashMap<Long, String> playlist;
	private int duration;
	private String lyrics;

	public TrackDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrackDTO(long id, String name, Album album, Playlist playlist, int duration, String lyrics){
		super();
		this.id = id;
		this.name = name;
		this.album = new HashMap<>();
		this.album.put(album.getId(), album.getName());
		this.playlist = new HashMap<>();
		this.playlist.put(playlist.getId(), playlist.getName());
		this.duration = duration;
		this.lyrics = lyrics;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<Long, String> getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = new HashMap<>();
		this.album.put(album.getId(), album.getName());
	}

	public HashMap<Long, String> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = new HashMap<>();
		this.playlist.put(playlist.getId(), playlist.getName());
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TrackDTO [id=").append(id).append(", name=").append(name).append(", album=").append(album)
			.append(", playlist=").append(playlist).append(", duration=").append(duration).append(", lyrics=")
			.append(lyrics).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, album, playlist, duration, lyrics);
	}

	@Override
	public boolean equals(Object obj){
		if (this == obj){
			return true;
		}
		if(!(obj instanceof TrackDTO)){
			return false;
		}
		TrackDTO other = (TrackDTO) obj;
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(album, other.album)
			&& Objects.equals(playlist, other.playlist) && duration == other.duration &&
			Objects.equals(lyrics, other.lyrics);
	}

}
