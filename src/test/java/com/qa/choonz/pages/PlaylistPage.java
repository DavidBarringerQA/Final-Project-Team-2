package com.qa.choonz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaylistPage {

final static String URL ="http://localhost:8082";
	
	private WebDriver driver;
	
	public PlaylistPage (WebDriver driver) {
		this.driver = driver;
	}
	
	
	//CREATE
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div/button[1]")
	WebElement addplaylistbutton;
	public void clickAddPlaylistButton() {
		addplaylistbutton.click();
	}
	
	@FindBy(id="add-name")
	WebElement playlistTextBox;
	public void addPlaylistName(String name) {
	playlistTextBox.sendKeys(name);
	
	}
	
	@FindBy(id="add-description")
	WebElement playlistDescriptionBox;
	public void addPlaylistDes(String description) {
	playlistDescriptionBox.sendKeys(description);
	
	}
	
	@FindBy(id="add-inputs")
	WebElement addAPlaylistBtn;
	public void clickPlaylistButton() {
		addAPlaylistBtn.click();
	}
	
	@FindBy(id="add-btn")
	WebElement confirmPlaylistBtn;
	public void confirmPlaylistButton() {
		confirmPlaylistBtn.click();
	}
	
	//READ
	
	@FindBy(id="search-input")
	WebElement playlistSearchBar;
	public void searchForAPlaylist(String playlist) {
		playlistSearchBar.sendKeys(playlist);
	}
	
	@FindBy(id="search-btn")
	WebElement searchPlaylistBtn;
	public void clickSearchPlaylistBtn() {
		searchPlaylistBtn.click();
	}
	
	@FindBy(xpath="/html/body/div[3]/div/div/a/div/div/h1")
	WebElement searchPlaylistResults;
	public void searchPlaylistResults() {
		searchPlaylistResults.click();
	}
	
	
	//UPDATE
	
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div/button[3]")
	WebElement updatePlaylistButton;
	public void clickUpdatePlaylistButton() {
		updatePlaylistButton.click();
	}
	

	@FindBy(id="update-old-name")
	WebElement updateOldPlaylistBox;
	public void updateOldPlaylistName(String update ) {
	updateOldPlaylistBox.sendKeys(update);
	
	}
	
	@FindBy(id="update-name")
	WebElement updatedPlaylistName;
	public void updatedPlaylistName(String name ) {
	updatedPlaylistName.sendKeys(name);
	
	}
	
	@FindBy(id="update-description")
	WebElement updatedPlaylistDescription;
	public void updatePlaylistDescription(String name ) {
	updatedPlaylistDescription.sendKeys(name);
	
	}
	

	@FindBy(id="update-inputs")
	WebElement updateInputsButton;
	public void clickAddUpdatedPlaylistBtn() {
		updateInputsButton.click();
	}
	
	
	@FindBy(id="update-btn")
	WebElement confirmUpdatePlaylistButton;
	public void clickConfirmUpdatePlaylistBtn() {
		confirmUpdatePlaylistButton.click();
	}
	

	
	//DELETE
	
	@FindBy(xpath="//*[@id=\"crud-genre\"]/button[2]")
	WebElement deletePlaylistButton;
	public void clickDeletePlaylistButton() {
		deletePlaylistButton.click();
	}
	
	
	@FindBy(id="delete-name")
	WebElement deletePlaylist;
	public void deletePlaylist(String name ) {
	deletePlaylist.sendKeys(name);
	
	}
	
	
	@FindBy(id="delete-inputs")
	WebElement deleteCurrentPlaylistButton;
	public void DeleteCurrentPlaylistButton() {
		deleteCurrentPlaylistButton.click();
	}
	
	
	
	@FindBy(id="delete-btn")
	WebElement confirmDeletePlaylistButton;
	public void ConfirmDeletePlaylistButton() {
		confirmDeletePlaylistButton.click();
	}
	
	
	
	
	
	
}
