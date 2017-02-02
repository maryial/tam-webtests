package com.epam.tam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {
	
	final static Logger logger = LogManager.getLogger(LoginPage.class);
	
	@FindBy(id="email")
	private WebElement emailField;
	
	@FindBy(id="password")
	private WebElement passwordField;
	
	@FindBy(xpath="//div[@class='auth-card']//button")
	private WebElement loginBtn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public MainPage doLogin(String email, String password) {
		logger.info("Logging in with {} / {}", email, password);
		emailField.clear();
		emailField.sendKeys(email);
		passwordField.clear();
		passwordField.sendKeys(password);
		loginBtn.click();
		return new MainPage(driver);		
	}
}
