package com.qa.choonz.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.SeleniumHooks;
import com.qa.choonz.pages.GenrePage;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GenreStepDefs {

	private static WebDriver driver;
	private WebElement webElement;
	
	private GenrePage page;
	
	public GenreStepDefs(SeleniumHooks hook) {
		this.driver = hook.getDriver();
		page = PageFactory.initElements(driver, GenrePage.class);

		driver.manage().window().maximize();
	
	}
	// CREATE

	@Given("I am on the genre page")
	public void i_am_on_the_genre_page() {
		driver.get("http://localhost:8082/genre.html");
	}
	
	@When("I click add genre")
	public void i_click_add_genre() {
		page.clickAddButton();
	}
	
	@And("Enter the genre {string}")
	public void enter_the_genre(String string) {
		page.addGenre(string);
	}
	
	@And("the genre description {string}")
	public void the_genre_description(String string) {
	   page.addDescription(string);
	}
	
	@And("click the confirm button")
	public void clickConfirmAdd() {
		page.pressConfirmAddBtn();
	}
	
	@Then("I refresh the page and have successfully created a genre")
	public void i_refresh_the_page_and_have_successfully_created_a_genre() {
		driver.navigate().refresh();
	}
	
	//READ
		
	@When("I click the search bar and enter the genre {string}")
	public void i_click_the_search_bar_and_enter_the_genre(String string) {
		page.searchForAGenre(string);
	}
	
	@And("I click the genre search button")
	public void i_click_the_search_button() {
	   page.clickSearchBtn();
	}
	
	@Then("The genre will appear in the search results")
	public void the_genre_will_appear_in_the_search_results() {
	  	page.genreSearchRes();
	}
	
	//UPDATE
	
	
	@When("I click update genre")
	public void i_click_update_genre() {
	   page.clickUpdate();
	}
	
	@And("Enter current genre {string}")
	public void enter_current_genre(String string) {
	  page.addCurrentGenre(string);
	}
	
	@And("Enter the updated genre {string}")
	public void enter_the_updated_genre(String string) {
	   page.addTheUpdatedGenre(string);
	}
	
	@And("Enter the updated genre description {string}")
	public void enter_the_updated_description(String string) {
	   page.updateDescription(string);
	}
	
	@And("click the update button")
	public void click_the_update_button() {
		  page.clickUpdateGenreButton();
	}
	
	
	@And("click the confirm update genre")
	public void click_the_confirm_update_genre() {
		page.clickConfirmUpdateGenre();
	}
	
	
	@Then("I have successfully updated a genre")
	public void i_have_successfully_updated_a_genre() {
	    
}
	
	//DELETE

	@When("I click delete genre")
	public void i_click_delete_genre() {
	    page.clickDeleteGenreBtn();
	}
	
	@And("I enter the genre {string}")
	public void i_enter_the_genre(String string) {
	 page.typeGenre(string);
	}
	
	@And("click the confirm delete button")
	public void click_the_confirm_delete_button() {
	  page.clickGenreDelete();
	}
	
	@Then("I have successfully deleted a genre")
	public void i_have_successfully_deleted_a_genre() {	
		
	}	
	
}