package com.qa.choonz.pages;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	
	import org.openqa.selenium.support.FindBy;

	public class LoginPage {
		
	final static String URL ="http://localhost:8082";
		
		private WebDriver driver;
		
		public LoginPage (WebDriver driver) {
			this.driver = driver;
		}
		
		@FindBy(id="floatingInput")
		WebElement userNameLogin;
		public void addUserName(String name) {
		userNameLogin.sendKeys(name);
		
		}
		
		@FindBy(id="floatingPassword")
		WebElement passwordLogin;
		public void addPassword(String password) {
		passwordLogin.sendKeys(password);
		
		}
		
		@FindBy(id="login")
		WebElement userLogin;
		public void clickLoginUser() {
			userLogin.click();
		}
		
		
		@FindBy(xpath="/html/body/div[1]/nav/a/img")
		WebElement choonzLogo;
		public void choonzLogo() {
			choonzLogo.click();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
}
