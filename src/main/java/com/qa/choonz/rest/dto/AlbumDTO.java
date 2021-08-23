package com.qa.choonz.rest.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;

public class AlbumDTO {

	private long id;
	private String name;
	private Map<Long, String> tracks;
	private Map<Long, String> artist;
	private Map<Long, String> genre;
	private String cover;

	public AlbumDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlbumDTO(long id, String name, List<Track> tracks, Artist artist, Genre genre, String cover) {
		super();
		this.id = id;
		this.name = name;
		this.tracks = new HashMap<>();
		for(Track track : tracks){
			this.tracks.put(track.getId(), track.getName());
		}
		this.artist = new HashMap<>();
		this.artist.put(artist.getId(), artist.getName());
		this.genre = new HashMap<>();
		this.genre.put(genre.getId(), genre.getName());
		this.cover = cover;
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

	public Map<Long, String> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = new HashMap<>();
		if(tracks != null) {
			for(Track track : tracks){
				this.tracks.put(track.getId(), track.getName());
			}
		}
	}

	public Map<Long, String> getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = new HashMap<>();
		if(artist != null) {
			this.artist.put(artist.getId(), artist.getName());
		}
	}

	public Map<Long, String> getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = new HashMap<>();
		if(genre != null) {
			this.genre.put(genre.getId(), genre.getName());
		}
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlbumDTO [id=").append(id).append(", name=").append(name).append(", tracks=").append(tracks)
			.append(", artist=").append(artist).append(", genre=").append(genre).append(", cover=").append(cover)
			.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(artist, cover, genre, id, name, tracks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AlbumDTO)) {
			return false;
		}
		AlbumDTO other = (AlbumDTO) obj;
		return Objects.equals(artist, other.artist) && Objects.equals(cover, other.cover)
			&& Objects.equals(genre, other.genre) && id == other.id && Objects.equals(name, other.name)
			&& Objects.equals(tracks, other.tracks);
	}

}
