package com.epam.tam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.tam.exception.ElementByXpathNotFoundException;
import com.epam.tam.util.Property;

public class AdminPage extends Page {
	
	final static Logger logger = LogManager.getLogger(LoginPage.class);

	@FindBy(xpath = "//img[@alt='add hcp']")
	private WebElement newHCPAccountBtn;
		
	public AdminPage(WebDriver driver) {
		super(driver);		
	}
	
	public boolean newHCPAccountBtnDisplayed() {
		return newHCPAccountBtn.isDisplayed();
	}
	
	public NewHCPAccountPage openNewHCPAccountForm() {
		logger.info("Opening new HCP Account form");
		newHCPAccountBtn.click();
		return new NewHCPAccountPage(driver);
	}
	
	public boolean newHCPAccountIsCreated(String email) throws ElementByXpathNotFoundException {
		logger.info("Looking for 'Pending' status for account with email {}", email);
		return findWebElementWithEmail(email).isDisplayed();
	}
	
	private WebElement findWebElementWithEmail(String email) throws ElementByXpathNotFoundException {
		String xpath = "//*[.='" + email + "']/../following-sibling::*/*[.='Pending']";
		return findWebElementByXpath(xpath);
	}
}
