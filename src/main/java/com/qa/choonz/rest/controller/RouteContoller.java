package com.qa.choonz.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteContoller {
	@GetMapping(value = "/")
	public String index() {
		return "index.html";
	}

	@GetMapping(value = "/home")
	public String home() {
		return "index.html";
	}

	@GetMapping(value = "/tracks")
	public String tracks() {
		return "tracks.html";
	}

	@GetMapping(value = "/artist")
	public String artist(){
		return "artist.html";
	}

	@GetMapping(value = "/albums")
	public String albums(){
		return "albums.html";
	}

	@GetMapping(value = "/playlists")
	public String playlists(){
		return "playlist.html";
	}

	@GetMapping(value = "/playlist")
	public String playlist(){
		return "playlist.html";
	}

	@GetMapping(value = "/genre")
	public String genre(){
		return "genre.html";
	}

	@GetMapping(value = "/login")
	public String login() {
		return "login.html";
	}
    
	@GetMapping(value = "/signup")
	public String signup() {
		return "signup.html";
	}

	@GetMapping(value = "/albumChild")
	public String albumChild(){
		return "albumChild.html";
	}

	@GetMapping(value = "/genreChild")
	public String genreChild(){
		return "genreChild.html";
	}

	@GetMapping(value = "/artistChild")
	public String artistChild(){
		return "artistChild.html";
	}

	@GetMapping(value = "/playlistChild")
	public String playlistChild(){
		return "playlistChild.html";
	}

	@GetMapping(value = "/trackChild")
	public String trackChild(){
		return "trackChild.html";
	}

	@GetMapping(value = "/header")
	public String header(){
		return "header.html";
	}
}
