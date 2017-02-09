package com.epam.tam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.epam.tam.exception.ElementByXpathNotFoundException;

public class MainPage extends Page {

	final static Logger logger = LogManager.getLogger(LoginPage.class);

	@FindBy(xpath = "//a[@href='/hcp/']")
	private WebElement adminPageBtn;
	
	@FindBy(xpath = "//*[@class='question-mark clickable']")
	private WebElement importantInformationTooltip;
	
	@FindBy(xpath = "//*[@alt='update profile']")
	private WebElement accountSettingsIcon;
	
	@FindBy(name = "global-search")
	private WebElement search;
	
	private static final String SEARCH_RESULT_XPATH = "//jhi-search//*[@class='dropdown-menu']";
	
	private static final String TEST_STRING = "blablablblaldldldkd";
		
	private static final String IMPORTANT_INFO_TOOLTIP_XPATH = "//*[@class='tooltip bottom tooltip-bottom in fade']";
	
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
	
	public WebElement getImportantInformationTooltipText() throws ElementByXpathNotFoundException {
		logger.info("Hovering the mouse over important information tooltip");
		new Actions(driver).contextClick(importantInformationTooltip).build().perform();
		return findWebElementByXpath(IMPORTANT_INFO_TOOLTIP_XPATH);
	}
	
	public WebElement searchForRubbishText() throws ElementByXpathNotFoundException {
		logger.info("Will search for rubbish text");
		search.sendKeys(TEST_STRING);
		//new Actions(driver).clickAndHold(accountSettingsIcon).moveToElement(search).release(accountSettingsIcon).build().perform();;
		//new Actions(driver).dragAndDrop(accountSettingsIcon, search).build().perform();
		new Actions(driver).sendKeys(search, Keys.ENTER).build().perform();
		return findWebElementByXpath(SEARCH_RESULT_XPATH);
	}	
}
