package com.qa.choonz.rest.dto;

import java.util.Objects;

public class UserDTO{

	private long id;
	private String username;

	public UserDTO(){
		super();
	}

	public UserDTO(long id, String username){
		super();
		this.id = id;
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode(){
		return Objects.hash(id, username);
	}

	@Override
	public boolean equals(Object obj){
		if (this == obj){
			return true;
		}
		if (!(obj instanceof UserDTO)){
			return false;
		}
		UserDTO other = (UserDTO) obj;
		return Objects.equals(username, other.username) && id == other.id;
	}
}

