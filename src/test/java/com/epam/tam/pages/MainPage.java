package com.epam.tam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends Page {

	final static Logger logger = LogManager.getLogger(LoginPage.class);

	@FindBy(xpath = "//a[@href='/hcp/']")
	private WebElement adminPageBtn;

	public MainPage(WebDriver driver) {
		super(driver);
	}

	public AdminPage navigateToAdminPage() {
		logger.info("Navigating to admin page");
		adminPageBtn.click();
		return new AdminPage(driver);
	}
	
	public boolean adminPageButtonDisplayed() {
		return adminPageBtn.isDisplayed();
	}
}
