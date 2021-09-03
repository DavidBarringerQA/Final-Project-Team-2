package com.qa.choonz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class GenrePage {

final static String URL ="http://localhost:8082";
	
	private WebDriver driver;
	
	public GenrePage (WebDriver driver) {
		this.driver = driver;
	}
	
	//CREATE
	@FindBy(xpath="//*[@id=\"crud-genre\"]/button[1]")
	WebElement addbutton;
	public void clickAddButton() {
		addbutton.click();
	}
	
	
	@FindBy(id="add-name")
	WebElement genretextbox;
	public void addGenre(String genre) {
	genretextbox.sendKeys(genre);
	
	}
	
	@FindBy(id="add-description")
	WebElement descriptionsearchbox;
	public void addDescription(String description) {
	descriptionsearchbox.sendKeys(description);
	
	}
	

	@FindBy(id="add-inputs")
	WebElement confirmAddBtn;
	public void pressConfirmAddBtn() {
		confirmAddBtn.click();
	}
	
	
	//READ
	
	@FindBy(xpath="/html/body/div[3]/div/div[1]/div/input")
	WebElement genreSearchBar;
	public void searchForAGenre(String genre) {
		genreSearchBar.sendKeys(genre);
	}
	
	@FindBy(id="search-btn")
	WebElement searchGenreBtn;
	public void clickSearchBtn() {
		searchGenreBtn.click();
	}
	
	@FindBy(xpath="/html/body/div[10]/div/div/a/div/img")
	WebElement genreSearchResults;
	public void genreSearchRes() {
		genreSearchResults.click();
	}
	
	
	
	//UPDATE
	
	@FindBy(xpath="/html/body/div[3]/div/div[2]/div/button[3]")
	WebElement updateGenreButton;
	public void clickUpdate() {
	updateGenreButton.click();
	
	}
	
	@FindBy(id="update-name")
	WebElement addACurrentGenre;
	public void addCurrentGenre(String genre) {
		addACurrentGenre.sendKeys(genre);
	}
	
	@FindBy(id="new-name")
	WebElement addUpdatedGenre;
	public void addTheUpdatedGenre(String genre) {
		addUpdatedGenre.sendKeys(genre);
	}
	
	@FindBy(xpath="//*[@id=\"update-description\"]")
	WebElement addUpdatedDescription;
	public void updateDescription(String description) {
		addUpdatedDescription.sendKeys(description);
	}
	
	//FIRST BUTTON IN MODAL
	@FindBy(id="update-inputs")
	WebElement confirmGenreUpdateButton;
	public void clickUpdateGenreButton() {
	Actions action = new Actions(driver);
	action.moveToElement(confirmGenreUpdateButton).click().perform();
	
	}
	
	@FindBy(id="update-btn")
	WebElement GenreUpdateButton;
	public void clickConfirmUpdateGenre() {
		GenreUpdateButton.click();
	}
	
	//DELETE
	
	@FindBy(xpath="//*[@id=\"crud-genre\"]/button[2]")
	WebElement deleteGenreBtn;
	public void clickDeleteGenreBtn() {
		deleteGenreBtn.click();
	}
	
	@FindBy(id="delete-name")
	WebElement deleteGenreTextBox;
	public void typeGenre(String genre) {
		deleteGenreTextBox.sendKeys(genre);
	}

	@FindBy(id="delete-inputs")
	WebElement confirmGenreDeleteBtn;
	public void clickGenreDelete() {
		confirmGenreDeleteBtn.click();
	}
	
	
	
	
	
	
	
	
	
}
