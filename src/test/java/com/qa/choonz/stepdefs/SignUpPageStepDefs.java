package com.qa.choonz.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.choonz.SeleniumHooks;
import com.qa.choonz.pages.SignUpPage;
import com.qa.choonz.pages.TracksPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpPageStepDefs {

	private static WebDriver driver;
	private WebElement webElement;
	
	private SignUpPage page;
	
	public SignUpPageStepDefs(SeleniumHooks hook) {
		this.driver = hook.getDriver();
		page = PageFactory.initElements(driver, SignUpPage.class);

		driver.manage().window().maximize();
	
}
		
	
	@Given("I am on the sign-up page")
	public void i_am_on_the_sign_up_page() {
	    driver.get("http://localhost:8082/signup.html");
	}
	
	@When("I add a username {string}")
	public void i_add_a_username(String string) {
	  page.addUserName(string);
	}
	
	@And("Enter a password {string}")
	public void enter_a_password(String string) {
	    page.addPassword(string);
	}
	
	@And("Enter confirmed password {string}")
	public void enter_confirmed_password(String string) {
	    page.confirmPassword(string);
	}
	
	@And("Click create user")
	public void click_create_user() {
	  page.clickRegisterUser();
	}
	
	@Then("i have successfully created a new user")
	public void i_have_successfully_created_a_new_user() {
	    
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
