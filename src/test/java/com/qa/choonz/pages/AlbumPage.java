package com.qa.choonz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AlbumPage {
	
final static String URL ="http://localhost:8082";
	
	private WebDriver driver;
	
	public AlbumPage (WebDriver driver) {
		this.driver = driver;
	}
	

	//CREATE
	@FindBy(xpath="//*[@id=\"crud-genre\"]/button[1]")
	WebElement addAlbumbutton;
	public void clickAddButton() {
		addAlbumbutton.click();
	}
	
	@FindBy(id="add-name")
	WebElement addAlbumName;
	public void addAlbumName(String name) {
	addAlbumName.sendKeys(name);
	
	}
	//FIRST DROPDOWN
	@FindBy(id="read-artists-add")
	WebElement addArtistName;
	public void addArtistName() {
	addArtistName.click();
	
	}
	
	@FindBy(xpath="//*[@id=\"read-artists-add\"]/option[2]")
	WebElement selectArtist;
	public void selectTheArtist() {
	selectArtist.click();
	}
	//SECOND DROPDOWN
	@FindBy(id="read-genres-add")
	WebElement addthegenre;
	public void addAGenre() {
	addthegenre.click();
	
	}
	
	@FindBy(xpath="//*[@id=\"read-genres-add\"]/option[4]")
	WebElement selectGenre;
	public void selectTheGenre() {
	selectGenre.click();
	}
	
	
	@FindBy(id="add-inputs")
	WebElement AlbumAddBtn;
	public void pressAlbumAddBtn() {
		AlbumAddBtn.click();
	}
	
	@FindBy(id="add-btn")
	WebElement AlbumConfirmAddBtn;
	public void clickConfirmAlbumAddBtn() {
		AlbumConfirmAddBtn.click();
	}
	
	@FindBy(xpath="/html/body/div[4]/div[1]/div[6]/a/div/div/h1")
	WebElement ConfirmAddedNewAlbum;
	public void clickNewAlbum() {
		ConfirmAddedNewAlbum.click();
	}
	
	
	
	
	//READ	
	

	@FindBy(id="search-input")
	WebElement albumSearchBar;
	public void searchForAnAlbum(String name) {
		albumSearchBar.sendKeys(name);
	}
	
	@FindBy(id="search-btn")
	WebElement searchAlbumBtn;
	public void clickSearchBtn() {
		searchAlbumBtn.click();
	}
	
	@FindBy(xpath="/html/body/div[4]/div/div/a/div/div/h1")
	WebElement searchAlbumResults;
	public void SearchResultsAlbum() {
		searchAlbumResults.click();
	}
	
	
	
	
	//UPDATE
	
	@FindBy(xpath="/html/body/div[3]/div/div[2]/div/button[3]")
	WebElement updateAlbumButton;
	public void clickUpdateAlbumButton() {
	updateAlbumButton.click();
	
	}
	
	@FindBy(id="old-name")
	WebElement addACurrentAlbum;
	public void addCurrentAlbum(String album) {
		addACurrentAlbum.sendKeys(album);
	}
	

	@FindBy(id="update-name")
	WebElement addANewAlbum;
	public void addNewAlbum(String album) {
		addANewAlbum.sendKeys(album);
	}
	//artist dropdown
	
	@FindBy(id="read-artists-update")
	WebElement addANewArtist;
	public void addNewArtist() {
		addANewArtist.click();
	}
	
	@FindBy(xpath="//*[@id=\"read-artists-update\"]/option[6]")
	WebElement selectTheNewArtist;
	public void selectTheNewArtist() {
	selectTheNewArtist.click();
	}
	
	//genre dropdown
	
	@FindBy(id="read-genres-update")
	WebElement clickANewGenre;
	public void addNewGenre() {
		clickANewGenre.click();
	}
	
	@FindBy(xpath="//*[@id=\"read-genres-update\"]/option[3]")
	WebElement selectTheNewGenre;
	public void selectTheNewGenre() {
	selectTheNewGenre.click();
	}
	

	
	//FIRST BUTTON IN MODAL	
	@FindBy(id="update-inputs")
	WebElement updateAlbumsBtn;
	public void clickUpdateAnAlbum() {
	Actions action = new Actions(driver);
	action.moveToElement(updateAlbumsBtn).click().perform();
	
	}
	
	@FindBy(id="update-btn")
	WebElement confirmUpdateAlbum;
	public void confirmUpdateButton() {
	confirmUpdateAlbum.click();
	
	}

	
	//DELETE

	@FindBy(xpath="//*[@id=\"crud-genre\"]/button[2]")
	WebElement deleteAlbumBtn;
	public void clickDeleteAlbumButton() {
		deleteAlbumBtn.click();
	}
	
	@FindBy(id="delete-name")
	WebElement deleteAlbumTextBox;
	public void typeGenre(String album) {
		deleteAlbumTextBox.sendKeys(album);
	}

	@FindBy(id="delete-btn")
	WebElement confirmAlbumDeleteBtn;
	public void clickGenreDelete() {
		confirmAlbumDeleteBtn.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
