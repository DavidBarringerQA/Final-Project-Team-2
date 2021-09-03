package com.qa.choonz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArtistPage {


	final static String URL ="http://localhost:8082/artist.html";
	
	private WebDriver driver;
	
	//CREATE
	@FindBy(xpath="/html/body/div[3]/div/div[2]/button[1]")
	WebElement addbutton;
	public void clickAddButton() {
		addbutton.click();
	}
	
	
	@FindBy(id="add-name")
	WebElement createArtisttextbox;
	public void addArtistName(String name) {
	createArtisttextbox.sendKeys(name);
	
	}
	
	
	@FindBy(id="add-inputs")
	WebElement confirmAddBtn;
	public void clickConfirmAddBtn() {
		confirmAddBtn.click();
	}
	
	//READ
	@FindBy(id="search-input")
	WebElement searchBar;
	public void searchForArtistName(String name) {
		searchBar.sendKeys(name);
	}
	
	
	@FindBy(id="search-btn")
	WebElement searchArtistBtn;
	public void clickSearchArtistBtn() {
		searchArtistBtn.click();
	}

	@FindBy(xpath="/html/body/div[4]/div/div/a/div/img")
	WebElement artistSearchResults;
	public void artistSearchRes() {
		artistSearchResults.click();
	}		
			
			
			
	
	//UPDATE
	
	@FindBy(xpath="/html/body/div[3]/div/div[2]/button[3]")
	WebElement updateArtistBtn;
	public void clickUpdateArtistBtn() {
	updateArtistBtn.click();
	
	}
		
	@FindBy(id="update-name")
	WebElement enterCurrentArtistName;
	public void addCurrentArtistName(String name) {
		enterCurrentArtistName.sendKeys(name);
	}
	
	@FindBy(id="new-name")
	WebElement enterNewArtistName;
	public void addNewArtistName(String name) {
		enterNewArtistName.sendKeys(name);
	}
	
	@FindBy(id="update-inputs")
	WebElement updatedArtistButton;
	public void clickUpdate() {
		updatedArtistButton.click();
	}
	
	@FindBy(id="update-inputs")
	WebElement confirmUpdateBtn;
	public void confirmUpdate() {
		confirmUpdateBtn.click();
	}
	
	
	//DELETE
	
	@FindBy(xpath="/html/body/div[3]/div/div[2]/button[2]")
	WebElement deleteArtistBtn;
	public void clickDeleteBtn() {
		deleteArtistBtn.click();
	}
	
	@FindBy(id="delete-name")
	WebElement deleteTextBox;
	public void typeArtistName(String name) {
		deleteTextBox.sendKeys(name);
	}
	
	@FindBy(id="delete-inputs")
	WebElement confirmDeletionBtn;
	public void clickDeletionBtn() {
		confirmDeletionBtn.click();
	}
	
	
	
	
	
}
	
	
	
	
	
	
	
