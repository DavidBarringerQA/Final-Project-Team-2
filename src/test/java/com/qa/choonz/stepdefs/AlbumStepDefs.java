package com.qa.choonz.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.SeleniumHooks;
import com.qa.choonz.pages.AlbumPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlbumStepDefs {

	private static WebDriver driver;
	private WebElement webElement;
	
	private AlbumPage page;
	
	public AlbumStepDefs(SeleniumHooks hook) {
		this.driver = hook.getDriver();
		page = PageFactory.initElements(driver, AlbumPage.class);

		driver.manage().window().maximize();
	
	}
	// CREATE
	
	@Given("I am on the album page")
	public void i_am_on_the_album_page() {
		driver.get("http://localhost:8082/albums.html");
	}
	
	@When("I click add album")
	public void i_click_add_album() {
	 page.clickAddButton();
	}
	
	@And("Enter the album name {string}")
	public void enter_the_album_name(String string) {
	  page.addAlbumName(string);
	}
	
	
	@And("click the artist dropdown")
	public void click_the_dropdown() {
	  page.addArtistName();
	}
	
	@And("choose an artist")
	public void choose_the_artist() {
	    page.selectTheArtist();
	}
	
	@And("click the genre dropdown")
	public void click_the_genre_dropdown() {
	   page.addAGenre();
	}
	
	@And("choose a genre")
	public void choose() {
	    page.selectTheGenre();
	}
	
	@And("click the create album button")
	public void click_the_create_album_button() {
	 page.pressAlbumAddBtn();
	}
	
	@And("click the confirm album button")
	public void click_the_confirm_album_button() {
	 page.clickConfirmAlbumAddBtn();
	}
	
	
	@Then("I refresh the page and have successfully created an album")
	public void i_refresh_the_page_and_have_successfully_created_an_album() {
	   page.clickNewAlbum();
	}
	
	//READ
		
	
	@When("I click the search bar and enter the album name {string}")
	public void i_click_the_search_bar_and_enter_the_album_name(String string) {
	    page.searchForAnAlbum(string);
	}
	
	@And("I click the search album button")
	public void i_click_the_search_album_button() {
	page.clickSearchBtn();
	}
	
	@Then("The album will appear in the search results")
	public void the_album_will_appear_in_the_search_results() {
	page.SearchResultsAlbum();
	}

	//UPDATE
	
	
	@When("I click update album")
	public void i_click_update_album() {
	   page.clickUpdateAlbumButton();
	}
	
	@And("Enter current album {string}")
	public void enter_current_album(String string) {
	    page.addCurrentAlbum(string);
	}
	
	@And("Enter the updated album {string}")
	public void enter_the_updated_album(String string) {
	page.addNewAlbum(string);
	}
		
	@And("Click the artist dropdown")
	public void click_the_artist_dropdown() {
	    page.addNewArtist();
	}
	
	@And("Choose an artist")
	public void choose_an_artist() {
	   page.selectTheNewArtist();
	}
	
	
	@And("Click the genre dropdown")
	public void click_the_genres_dropdown() {
	    page.addNewGenre();
	}
	

	@And("Choose the genre")
	public void choose_the_genre() {
		page.selectTheNewGenre();
	}
	
	@And("click the update albums button")
	public void click_the_update_albums_button() {
	   page.clickUpdateAnAlbum();
	}
	
	
	@And("click the confirm update album")
	public void click_the_confirm_update_album() {
	 page.confirmUpdateButton();
	}
	
	@Then("I have successfully updated an album")
	public void i_have_successfully_updated_an_album() {
	  
	}

	//DELETE
	
	@When("I click delete album")
	public void i_click_delete_album() {
	    
	}
	
	@When("I enter the album {string}")
	public void i_enter_the_album(String string) {
	    
	}
	
	@When("click the confirm delete album button")
	public void click_the_confirm_delete_album_button() {
	  
	}
	
	@Then("I have successfully deleted the album")
	public void i_have_successfully_deleted_the_album() {
	   
	}

	




}
