package com.qa.choonz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class TracksPage {
	
final static String URL ="http://localhost:8082";
	
	private WebDriver driver;
	
	public TracksPage (WebDriver driver) {
		this.driver = driver;
	}
	
	//CREATE
	@FindBy(xpath="//*[@id=\"crud-genre\"]/button[1]")
	WebElement addtracksbutton;
	public void clickAddTracksButton() {
		addtracksbutton.click();
	}
	
	@FindBy(id="add-name")
	WebElement tracknametextbox;
	public void addTrackName(String name) {
	tracknametextbox.sendKeys(name);
	
	}
	
	@FindBy(id="add-duration")
	WebElement trackdurationtextbox;
	public void addTrackDuration(String duration) {
	trackdurationtextbox.sendKeys(duration); 
	
	}
	
	@FindBy(id="add-lyrics")
	WebElement tracklyricstextbox;
	public void addTrackLyrics(String lyrics) {
	tracklyricstextbox.sendKeys(lyrics);
	
	}
	
	@FindBy(id="add-album")
	WebElement addTrackAlbumButton;
	public void clickAddAlbumsButton() {
		addTrackAlbumButton.click();
	}
	
	
	//clicking album i want
	@FindBy(xpath="/html/body/div[8]/div/div/div[2]/select/option[2]")
	WebElement addAlbumBtn;
	public void AddTrackAlbumBtn() {
		addAlbumBtn.click();
	}
	//first button
	
	@FindBy(id="add-inputs")
	WebElement AddTrackButton;
	public void clickTheTrackButton() {
		AddTrackButton.click();
	}
	
	//second button

	@FindBy(id="add-btn")
	WebElement confirmAddTrackButton;
	public void clickConfirmTrackButton() {
		confirmAddTrackButton.click();
	}
	
	//READ
	
	@FindBy(id="search-input")
	WebElement searchForTrack;
	public void searchATrack(String search) {
	searchForTrack.sendKeys(search);
	
	}
	
	@FindBy(id="search-btn")
	WebElement searchTrackButton;
	public void clickSearchTrack() {
		searchTrackButton.click();
	}
	
	@FindBy(xpath="/html/body/div[3]/div[2]/table/tbody/tr/td[1]/a")
	WebElement searchResults;
	public void searchResult() {
		searchResults.click();
	}
	
	
	
	
	
	//UPDATE
	@FindBy(xpath="//*[@id=\"crud-genre\"]/button[3]")
	WebElement updatetrackbutton;
	public void updateNewTrack() {
		updatetrackbutton.click();
	}
	
	@FindBy(id="old-track")
	WebElement updatetrackstextbox;
	public void updateATrack(String update) {
	updatetrackstextbox.sendKeys(update);
	
	}
	

	@FindBy(id="update-name")
	WebElement newtracknametextbox;
	public void newTrackName(String name) {
	newtracknametextbox.sendKeys(name);
	
	}
	
	@FindBy(id="update-duration")
	WebElement newtrackdurationtextbox;
	public void newTrackDuration(String duration) {
	newtrackdurationtextbox.sendKeys(duration);
	
	}
	
	@FindBy(id="update-lyrics")
	WebElement newtracklyricstextbox;
	public void newTrackLyrics(String lyrics) {
	newtracklyricstextbox.sendKeys(lyrics);
	
	}
	
	@FindBy(id="update-album")
	WebElement clickDropDown;
	public void clickDropDown() {
		clickDropDown.click();
	}
	
	@FindBy(xpath="/html/body/div[6]/div/div/div[2]/select/option[3]")
	WebElement clickDropDownAlbum;
	public void clickDropAlbum() {
		clickDropDownAlbum.click();
	}
	
	@FindBy(id="update-inputs")
	WebElement clickUpdateTrack;
	public void clickTheUpdateTrackBtn() {
		clickUpdateTrack.click();
	}
	
	@FindBy(id="update-btn")
	WebElement clickUpdateTrackButton;
	public void updateConfirmButton() {
		clickUpdateTrackButton.click();
	}
	
	
	//DELETE

	@FindBy(xpath="//*[@id=\"crud-genre\"]/button[2]")
	WebElement deletetrackbutton;
	public void clickDeleteTrackButton() {
		deletetrackbutton.click();
	}
	
	@FindBy(id="delete-name")
	WebElement deletetrackbox;
	@FindBy(xpath= "/html/body/div[4]/div/div/div[3]/button[2]")
	WebElement deleteInputsBtn;
	public void deleteATrack(String delete) {
     deletetrackbox.sendKeys(delete);
     System.out.println("textvalue " + deleteInputsBtn.getText());
     Actions action = new Actions(driver);
     action.moveToElement(deleteInputsBtn).click().perform();
	}
	
	
	@FindBy(id="delete-btn")
	WebElement deleteOldTrackButton;
	public void confirmDeleteTrackButton() {
		Actions action = new Actions(driver);
		action.moveToElement(deleteOldTrackButton).click().perform();

	}
	
	
}
