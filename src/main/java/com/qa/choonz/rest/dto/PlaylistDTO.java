package com.qa.choonz.rest.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.qa.choonz.persistence.domain.Track;

public class PlaylistDTO {

	private long id;
	private String name;
	private String description;
	private String artwork;
	private Map<Long, String> tracks;

	public PlaylistDTO() {
		super();
	}

	public PlaylistDTO(long id, String name, String description, String artwork, List<Track> tracks) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.artwork = artwork;
		this.tracks = new HashMap<>();
		if(tracks != null) {
			for(Track track : tracks){
				this.tracks.put(track.getId(), track.getName());
			}
		}
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the artwork
	 */
	public String getArtwork() {
		return artwork;
	}

	/**
	 * @param artwork the artwork to set
	 */
	public void setArtwork(String artwork) {
		this.artwork = artwork;
	}

	/**
	 * @return the tracks
	 */
	public Map<Long, String> getTracks() {
		return tracks;
	}

	/**
	 * @param tracks the tracks to set
	 */
	public void setTracks(List<Track> tracks) {
		this.tracks = new HashMap<>();
		if(tracks != null){
			for(Track track : tracks){
				this.tracks.put(track.getId(), track.getName());
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlaylistDTO [id=").append(id).append(", name=").append(name).append(", description=")
			.append(description).append(", artwork=").append(artwork).append(", tracks=").append(tracks)
			.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(artwork, description, id, name, tracks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PlaylistDTO)) {
			return false;
		}
		PlaylistDTO other = (PlaylistDTO) obj;
		return Objects.equals(artwork, other.artwork) && Objects.equals(description, other.description)
			&& id == other.id && Objects.equals(name, other.name) && Objects.equals(tracks, other.tracks);
	}

}
