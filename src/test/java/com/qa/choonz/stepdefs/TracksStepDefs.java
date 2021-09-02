package com.qa.choonz.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.SeleniumHooks;
import com.qa.choonz.pages.TracksPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TracksStepDefs {

	private static WebDriver driver;
	private WebElement webElement;
	
	private TracksPage page;
	
	public TracksStepDefs(SeleniumHooks hook) {
		this.driver = hook.getDriver();
		page = PageFactory.initElements(driver, TracksPage.class);

		driver.manage().window().maximize();
	
}
//CREATE
	@Given("I am on the tracks page")
	public void i_am_on_the_playlist_page() {
		driver.get("http://localhost:8082/tracks.html");
	}
		
	
	@When("I click add track")
	public void i_click_add_track() {
	    page.clickAddTracksButton();
	}
	
	@And("Enter the track name {string}")
	public void enter_the_track_name(String string) {
	    page.addTrackName(string);
	}
	
	@And("Enter the track duration {int}")
	public void enter_the_track_duration(Integer int1) {
	    page.addTrackDuration(String.valueOf(int1));
	}
	
	@And("Enter the track lyrics {string}")
	public void enter_the_track_lyrics(String string) {
	    page.addTrackLyrics(string);
	}
	
	
	@And("Click the drop down button")
	public void click_the_album() {
	    page.clickAddAlbumsButton();
	}
	
	@And("Click the album")
	public void click_add() {
	  page.AddTrackAlbumBtn();
	}
	
	
	//first button
	@And("Click add track button")
	public void clickAddTrackButton() {
	  page.clickTheTrackButton();
	
	}
	//second button
	@And("Click confirm add")
	public void clickConfirmAddBtn() {
	  page.clickConfirmTrackButton();
	
	}
	
	@Then("I have successfully created a track")
	public void i_have_successfully_created_a_track() {
	 
	}
	
	//READ
	

	
	@When("I click the search bar and enter the track {string}")
	public void i_click_the_search_bar_and_enter_the_track(String string) {
	    page.searchATrack(string);
	}
	
	@When("I click the search track button")
	public void i_click_the_search_track_button() {
	    page.clickSearchTrack();
	    
	}
	
	@Then("The track will appear in the search results")
	public void the_track_will_appear_in_the_search_results() {
	   page.searchResult();
	}

	
	
	//UPDATE
	
	@When("I click update track")
	public void i_click_update_track() {
		page.updateNewTrack();
	}
	
	@And("insert the name of the track i want to update {string}")
	public void insert_the_name_of_the_track_i_want_to_update(String string) {
	    page.updateATrack(string);
	}

	@And("Enter the new track name {string}")
	public void enter_the_new_track_name(String string) {
	   page.newTrackName(string);
	}
	
	@And("Enter the duration {int}")
	public void enter_the_duration(Integer int1) {
	    page.newTrackDuration(String.valueOf(int1));
	}
	
	@And("Enter the new track lyrics {string}")
	public void enter_the_new_track_lyrics(String string) {
	   page.newTrackLyrics(string);
	}
	
	@And("Click the drop down")
	public void click_the_drop_down() {
	   page.clickDropDown();
	}
	
	@When("Choose an album")
	public void choose_the_album() {
	   page.clickDropAlbum();
	}
	
	@When("Click update track")
	public void click_update_track() {
	    page.clickTheUpdateTrackBtn();
	}
	
	@When("Click confirm track update")
	public void click_confirm_track_update() {
	    
	}
	@Then("you have updated a track")
	public void you_have_updated_a_track() {
	    
	}
	
	//DELETE
	
	
	@When("i click delete track")
	public void i_click_delete_track() {
	   page.clickDeleteTrackButton();
	}
	
	@And("Enter the name of the track i want to delete {string}")
	public void enter_the_name_of_the_track_i_want_to_delete(String string) throws InterruptedException {
	  page.deleteATrack(string);
	  Thread.sleep(100);
	}
	
	@And("click confirm track delete")
	public void click_confirm_track_delete() {
	   page.confirmDeleteTrackButton();
	}
	
	@Then("i have deleted a track")
	public void i_have_deleted_a_track() {
	    
	}
	
	
	
	
	
	
	
	
	
}