package com.qa.choonz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage {

final static String URL ="http://localhost:8082";
	
	private WebDriver driver;
	
	public SignUpPage (WebDriver driver) {
		this.driver = driver;
	}	
	
	@FindBy(id="floatingInput")
	WebElement signUpPageUserName;
	public void addUserName(String name) {
	signUpPageUserName.sendKeys(name);
	
	}
	
	@FindBy(id="floatingPassword")
	WebElement signUpPagePassword;
	public void addPassword(String password) {
	signUpPagePassword.sendKeys(password);
	
	}
	
	@FindBy(id="floatingPasswordConf")
	WebElement signUpPagePasswordConf;
	public void confirmPassword(String password) {
	signUpPagePasswordConf.sendKeys(password);
	
	}
	
	@FindBy(id="register")
	WebElement registerUser;
	public void clickRegisterUser() {
		registerUser.click();
	}
	
	
}
