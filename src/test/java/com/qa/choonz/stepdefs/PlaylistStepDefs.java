package com.qa.choonz.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.SeleniumHooks;
import com.qa.choonz.pages.GenrePage;
import com.qa.choonz.pages.PlaylistPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaylistStepDefs {


	private static WebDriver driver;
	private WebElement webElement;
	
	private PlaylistPage page;
	
	public PlaylistStepDefs(SeleniumHooks hook) {
		this.driver = hook.getDriver();
		page = PageFactory.initElements(driver, PlaylistPage.class);

		driver.manage().window().maximize();
	
	}
	//CREATE
	@Given("I am on the playlist page")
	public void i_am_on_the_playlist_page() {
		driver.get("http://localhost:8082/index.html");
	}
	
	
	@When("I click add playlist")
	public void i_click_add_playlist() {
	    page.clickAddPlaylistButton();
	}
	
	@And("Enter a playlist name {string}")
	public void enter_a_playlist_name(String string) {
	    page.addPlaylistName(string);
	}
	
	@And("the playlist description {string}")
	public void the_playlist_description(String string) {
	  page.addPlaylistDes(string);
	}
	
	@And("Click the add button")
	public void click_the_add_button() {
	  page.clickPlaylistButton();
	}
	
	@And("click the confirm playlist button")
	public void click_the_confirm_playlist_button() {
	   page.confirmPlaylistButton();
	}
	
	@Then("I refresh the page and have successfully created a playlist")
	public void i_refresh_the_page_and_have_successfully_created_a_playlist() {
		driver.navigate().refresh();
	}
	
	//READ
	
	@When("I click the search bar and enter the playlist {string}")
	public void i_click_the_search_bar_and_enter_the_track(String string) {
	    page.searchForAPlaylist(string);
	}
	
	@And("I click the search playlist button")
	public void i_click_the_search_track_button() {
	    page.clickSearchPlaylistBtn();
	    
	}
	
	@Then("The playlist will appear in the search results")
	public void the_track_will_appear_in_the_search_results() {
	   page.searchPlaylistResults();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//UPDATE
		
	
	@When("I click update playlist")
	public void i_click_update_playlist() {
	   page.clickUpdatePlaylistButton();
	}
	
	@And("Insert the name of the playlist i want to update {string}")
	public void insert_the_name_of_the_playlist_i_want_to_update(String string) {
	   page.updateOldPlaylistName(string);
	}
	
	@And("Enter a new playlist name {string}")
	public void enter_a_new_playlist_name(String string) {
	   page.updatedPlaylistName(string);
	}
	
	@And("Enter a playlist description {string}")
	public void enter_a_playlist_description(String string) {
	   page.updatePlaylistDescription(string);
	}
	
	@And("Click the update playlist button")
	public void click_the_update_playlist_button() {
	    page.clickAddUpdatedPlaylistBtn();
	}
	
	@And("Click the confirm update playlist button")
	public void click_the_confirm_update_playlist_button() {
	    page.clickConfirmUpdatePlaylistBtn();
	}
	
	@Then("I have updated a playlist")
	public void i_have_updated_a_playlist() {
	  
	}
		
	//DELETE
	
	
	@Then("I click delete playlist")
	public void i_click_delete_playlist() {
	 page.clickDeletePlaylistButton();
	}
	
	@Then("Enter the name of the playlist i want to delete {string}")
	public void enter_the_name_of_the_playlist_i_want_to_delete(String string) {
	   page.deletePlaylist(string);
	}
	
	@Then("Click delete playlist")
	public void click_delete_playlist() {
	    page.DeleteCurrentPlaylistButton();
	}
	
	@Then("Click confirm delete playlist")
	public void click_confirm_delete_playlist() {
	page.ConfirmDeletePlaylistButton();
	}
	
	@Then("I have successfully deleted a playlist")
	public void i_have_successfully_deleted_a_playlist() {
	   
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
