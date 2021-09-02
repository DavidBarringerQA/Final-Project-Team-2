package com.qa.choonz;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class SeleniumHooks {

	private WebDriver webdriver;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		this.webdriver = new ChromeDriver();
		this.webdriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	@After
	public void teardown() {
		this.webdriver.quit();
	}

	public WebDriver getDriver() {
		return this.webdriver;
	}



}
