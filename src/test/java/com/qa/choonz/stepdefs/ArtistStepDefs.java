package com.qa.choonz.stepdefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.SeleniumHooks;
import com.qa.choonz.pages.ArtistPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ArtistStepDefs {

	private static WebDriver driver;
	private WebElement webElement;
	private ArtistPage page;

	public ArtistStepDefs(SeleniumHooks hook) {
		this.driver = hook.getDriver();
		page = PageFactory.initElements(driver, ArtistPage.class);

		driver.manage().window().maximize();
	}
	// CREATE

	@Given("I am on the artist page")
	public void i_am_on_the_artist_page() {
		driver.get("http://localhost:8082/artist.html");
	}

	@When("I click add artist")
	public void i_click_add_artist() {
		page.clickAddButton();

	}

	@And("Enter the name {string}")
	public void enter_the_name(String string) {
		page.addArtistName(string);
	}

	@And("click the confirm add button")
	public void click_the_confirm_add_button() {
		page.clickConfirmAddBtn();

	}

	@Then("I refresh the page and have successfully created an artist")
	public void i_have_successfully_created_an_artist() {
		driver.navigate().refresh();

	}

	// READ

	@When("I click the search bar and enter name {string}")
	public void enterTheName(String string) {
		page.searchForArtistName(string);
	}

	
	@And("I click artist search")
	public void i_click_artist_search() {
	    page.clickSearchArtistBtn();
	}
	
	@Then("The artist will appear in the search results")
	public void the_artist_will_appear_in_the_search_results() {
	    page.artistSearchRes();
	}
	
		
		
	
	
	
	// UPDATE

	@When("I click update artist")
	public void i_click_update_artist() {
		page.clickUpdateArtistBtn();
	}

	
	@And("Enter the current artist name {string}")
	public void enter_the_current_artist_name(String string) {
	   page.addCurrentArtistName(string);
	}
	
	@And("Enter the new artist name {string}")
	public void enter_the_new_artist_name(String string) {
	    page.addNewArtistName(string);
	}
	
	@And("Click the update artist button")
	public void click_the_update_artist_button() {
	    page.clickUpdate();
	}
	
	@And("click the confirm update artist button")
	public void click_the_confirm_update_artist_button() {
	   page.confirmUpdate();
	}

	@Then("I have successfully updated an artist")
	public void i_have_successfully_updated_an_artist() {

	}
	
	//DELETE
	
	@When("I click delete artist")
	public void i_click_delete_artist() {
		page.clickDeleteBtn();
	    
	}
	
	@And("Enter an artist name {string}")
	public void enterName(String string) {
		page.typeArtistName(string);
	
	}
		
	@When("click the confirm deletion button")
	public void click_the_confirm_deletion_button() {
	 page.clickDeletionBtn();
	}
	
	@Then("I have successfully deleted an artist")
	public void i_have_successfully_deleted_an_artist() {
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
