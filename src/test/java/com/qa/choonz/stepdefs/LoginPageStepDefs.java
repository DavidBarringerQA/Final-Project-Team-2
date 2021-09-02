package com.qa.choonz.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.SeleniumHooks;
import com.qa.choonz.pages.AlbumPage;
import com.qa.choonz.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginPageStepDefs {

	private static WebDriver driver;
	private WebElement webElement;
	
	private LoginPage page;
	
	public LoginPageStepDefs(SeleniumHooks hook) {
		this.driver = hook.getDriver();
		page = PageFactory.initElements(driver, LoginPage.class);

		driver.manage().window().maximize();
	
	}	
		
	
	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
	    driver.get("http://localhost:8082/login.html");
	}
	
	@When("i type a username {string}")
	public void i_type_a_username(String string) {
	    page.addUserName(string);
	}
	
	@And("type password {string}")
	public void type_password(String string) {
	    page.addPassword(string);
	}
	
	@And("click log in")
	public void click_sign_in() {
	page.clickLoginUser();
	}
	
	@And("i have signed into Choonz")
	public void i_have_signed_into_choonz() {
	    page.choonzLogo();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
