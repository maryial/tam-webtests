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
	
	private final static Logger LOGGER = LogManager.getLogger(AdminPage.class);

	@FindBy(id = "users-title-heading-tab") 
	private WebElement doctorsTab;
	
	@FindBy(xpath = "//img[@alt='add hcp']")
	private WebElement newHCPAccountBtn;	
	
	@FindBy(id="sites-title-heading-tab")
	private WebElement sitesTab;
	
	@FindBy(id = "new-site-button")
	private WebElement addSiteButton;
		
	public AdminPage(WebDriver driver) {
		super(driver);		
	}
	
	public boolean newHCPAccountBtnDisplayed() {
		return newHCPAccountBtn.isDisplayed();
	}
	
	public NewHCPAccountPage openNewHCPAccountForm() {
		LOGGER.info("Opening new HCP Account form");
		doctorsTab.click();
		waitForVisibility(newHCPAccountBtn);
		newHCPAccountBtn.click();
		return new NewHCPAccountPage(driver);
	}
	
	public boolean newHCPAccountIsCreated(String email) throws ElementByXpathNotFoundException {
		LOGGER.info("Looking for 'Pending' status for account with email {}", email);
		jsExecutor.executeScript("location.reload()");
		return findWebElementWithEmail(email).isDisplayed();
	}
	
	public NewSitePage openNewSiteForm() {
		LOGGER.info("Opening new Site form");
		sitesTab.click();
		waitForVisibility(addSiteButton);
		addSiteButton.click();
		return new NewSitePage(driver);
	}
	
	public boolean newSiteIsCreated(String site) throws ElementByXpathNotFoundException {
		LOGGER.info("Looking for the newly created site with name {}", site);
		jsExecutor.executeScript("location.reload()");
		return findSiteWithName(site).isDisplayed();
	}
	
	private WebElement findSiteWithName(String name) throws ElementByXpathNotFoundException {
		String xpath = "//*[normalize-space(td)='" + name + "']";
		return findWebElementByXpath(xpath);
	}
	
	private WebElement findWebElementWithEmail(String email) throws ElementByXpathNotFoundException {
		String xpath = "//*[.='" + email + "']/../following-sibling::*/*[.='Pending']";
		return findWebElementByXpath(xpath);
	}
}
