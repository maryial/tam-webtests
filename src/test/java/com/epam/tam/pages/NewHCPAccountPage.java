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
import com.epam.tam.util.Property;

public class NewHCPAccountPage extends Page {

	private final static Logger LOGGER = LogManager.getLogger(NewHCPAccountPage.class);

	@FindBy(xpath = "//div[@class='modal-content' and .//*[contains(normalize-space(h1), 'New User')]]")
	private WebElement newHCPAccountForm;

	@FindBy(id = "name")
	private WebElement nameField;

	@FindBy(xpath = "//*[@formcontrolname='lastName']")
	private WebElement lastNameField;

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "sites")
	private WebElement sitesDropDown;

	@FindBy(id = "org")
	private WebElement orgAdminCheckbox;

	@FindBy(id = "site")
	private WebElement siteAdminCheckbox;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveButton;

	public NewHCPAccountPage(WebDriver driver) {
		super(driver);
	}

	public boolean newHCPAccountFormDisplayed() {
		waitForVisibility(newHCPAccountForm);
		return newHCPAccountForm.isDisplayed();
	}

	public AdminPage createNewHCPAccount(String name, String lastName,
			String email, String site, boolean orgAdmin, boolean siteAdmin)
			throws ValueNotFoundInDropDownException, ElementByXpathNotFoundException {
		LOGGER.info(
				"Creating account with name {}, lastName {}, email {}, site {}, orgAdmin {}, siteAdmin {}",
				name, lastName, email, site, orgAdmin, siteAdmin);
		nameField.sendKeys(name);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		selectValueInDropDown(sitesDropDown, site);
		if(orgAdmin) {
			orgAdminCheckbox.click();
		}
		if (siteAdmin) {
			siteAdminCheckbox.click();
		}
		saveButton.click();
		return new AdminPage(driver);
	}

	private void selectValueInDropDown(WebElement dropDown, String value)
			throws ValueNotFoundInDropDownException, ElementByXpathNotFoundException {
		Select drop = new Select(dropDown);
		String xpath = "//*[@id='sites']//*[normalize-space()='" + value + "']";
		WebElement required = findWebElementByXpath(xpath);
		int index = drop.getOptions().indexOf(required);
		if(index != -1) {
			drop.selectByIndex(index);
			return;
		}	
		LOGGER.error("Failed to locate {} value by xpath {} " + xpath);
		LOGGER.error("Available drop down options: {}", dropDown.getText());
		LOGGER.error("Page source:\n" + driver.getPageSource());
		throw new ValueNotFoundInDropDownException(dropDown, value);
	}
	
	 private void waitForVisibility(WebElement element) {
         new WebDriverWait(driver, Property.TIMEOUT)
              .until(ExpectedConditions.visibilityOf(element));
  }
}
