package com.qa.choonz.rest.dto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.qa.choonz.persistence.domain.Album;
public class GenreDTO {

	private long id;
	private String name;
	private String description;
	private Map<Long, String> albums;

	public GenreDTO() {
		super();
	}

	public GenreDTO(long id, String name, String description, List<Album> albums) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.albums = new HashMap<>();
		for(Album album : albums){
			this.albums.put(album.getId(), album.getName());
		}
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<Long,String> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = new HashMap<>();
		if(albums != null) {
			for(Album album : albums){
				this.albums.put(album.getId(), album.getName());
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GenreDTO [id=").append(id).append(", name=").append(name).append(", description=")
			.append(description).append(", albums=").append(albums).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(albums, description, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GenreDTO)) {
			return false;
		}
		GenreDTO other = (GenreDTO) obj;
		return Objects.equals(albums, other.albums) && Objects.equals(description, other.description) && id == other.id
			&& Objects.equals(name, other.name);
	}

}
