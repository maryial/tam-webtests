package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import util.Property;

public class CreateNewHCPAccountTest extends BaseTest{
	
	private static final String TESTSTRING = "test";
	
	@Test
	public void get10MinutesEmail() {
		driver.get(Property.MAIN_APPLICATION_PAGE_URL);	
	}
		
	@Test
	public void loginHCPAccount() {
		driver.get(Property.MAIN_APPLICATION_PAGE_URL);
		enterText(Locators.LOGIN_EMAIL, Property.HCP_LOGIN);
		enterText(Locators.LOGIN_PASSWORD, Property.HCP_PASS);
		click(Locators.LOGIN_BUTTON);
		WebElement webElement = getWebElement(Locators.MAIN_PAGE_ADMIN_LINK);
		Assert.assertNotNull(webElement);
	}
	
	@Test(dependsOnMethods = {"loginHCPAccount"})
	public void openNewHCPAccountForm() throws InterruptedException {
		click(Locators.MAIN_PAGE_ADMIN_LINK);
		waitForElementPresent(Locators.ADMIN_MENU);
		Thread.sleep(5000);
		click(Locators.ADMIN_ADD_NEW_USER_BUTTON);
		WebElement webElement = getWebElement(Locators.ADMIN_ADD_NEW_USER_POPUP_TEXT);
		Assert.assertNotNull(webElement);
	}
	
	@Test(dependsOnMethods = {"openNewHCPAccountForm"})
	public void createNewHCPAccountAccount() {
		enterText(Locators.ADMIN_ADD_NEW_USER_NAME, TESTSTRING);
		enterText(Locators.ADMIN_ADD_NEW_USER_LASTNAME, TESTSTRING);
		enterText(Locators.ADMIN_ADD_NEW_USER_EMAIL, Property.NEW_ACCOUNT_EMAIL);
		selectInDropDown(Locators.ADMIN_ADD_NEW_USER_SITES_DROPDOWN, 3);
		click(Locators.ADMIN_ADD_NEW_USER_SUBMIT);
		WebElement webElement = getWebElement(Locators.ADMIN_CREATED_ACCOUNT_STATUS);
		Assert.assertNotNull(webElement);
	}

}
