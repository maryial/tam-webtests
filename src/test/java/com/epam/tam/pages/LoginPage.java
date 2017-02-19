package com.epam.tam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.tam.model.LoginAccount;

public class LoginPage extends Page {
	
	private final static Logger LOGGER = LogManager.getLogger(LoginPage.class);
	
	@FindBy(id="email")
	private WebElement emailField;
	
	@FindBy(id="password")
	private WebElement passwordField;
	
	@FindBy(xpath="//div[@class='auth-card']//button")
	private WebElement loginBtn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public MainPage doLogin(LoginAccount loginAccount) {
		String email = loginAccount.getEmail();
		String password = loginAccount.getPassword();
		LOGGER.info("Logging in with {} / {}", email, password);
		emailField.clear();
		emailField.sendKeys(email);
		passwordField.clear();
		passwordField.sendKeys(password);
		loginBtn.click();
		return new MainPage(driver);		
	}
}
