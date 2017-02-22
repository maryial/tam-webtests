package com.epam.tam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.tam.exception.ElementByXpathNotFoundException;
import com.epam.tam.exception.ValueNotFoundInDropDownException;
import com.epam.tam.model.Doctor;
import com.epam.tam.model.Site;
import com.epam.tam.util.Property;

public class NewSitePage extends Page {

	private final static Logger LOGGER = LogManager.getLogger(NewSitePage.class);
	
	@FindBy(xpath = "//*[@class='modal-content' and .//*[normalize-space(h1)='New Site']]//*[@id='name']")
	private WebElement siteName;
	
	@FindBy(xpath = "//*[@class='modal-content' and .//*[normalize-space(h1)='New Site']]//*[@id='address']")
	private WebElement siteAddress;
	
	@FindBy(xpath = "//*[@class='modal-content' and .//*[normalize-space(h1)='New Site']]//*[@id='number']")
	private WebElement siteNumber;
	
	@FindBy(xpath = "//*[@class='modal-content' and .//*[normalize-space(h1)='New Site']]//*[@id='name']")
	private WebElement saveSite;
	
	public NewSitePage(WebDriver driver) {
		super(driver);
	}

	public boolean newSiteFormDisplayed() {
		waitForVisibility(saveSite);
		return saveSite.isDisplayed();
	}

	public AdminPage createNewSite(Site	site) {			
		String name = site.getName();
		String address = site.getAddress();
		String number = site.getNumber();
		
		LOGGER.info(
				"Creating new site with name {}, address {}, number {}", 
				name, address, number);
		waitForVisibility(siteName);
		siteName.sendKeys(name);
		siteAddress.sendKeys(address);
		siteNumber.sendKeys(number);		
		saveSite.click();
		saveSite.click();
		return new AdminPage(driver);
	}	
}
