package com.epam.tam.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.tam.exception.ElementByXpathNotFoundException;
import com.epam.tam.exception.ValueNotFoundInDropDownException;
import com.epam.tam.pages.AdminPage;
import com.epam.tam.pages.LoginPage;
import com.epam.tam.pages.MainPage;
import com.epam.tam.pages.NewHCPAccountPage;
import com.epam.tam.util.Property;

public class CreateNewHCPAccountTestPO extends BaseTest{
	
	private static final String TESTSTRING = "test";
	private static final String TESTSITE = "JHI review";
	
	@BeforeClass
	public void openApplicationPage() {
		driver.get(Property.MAIN_APPLICATION_PAGE_URL);
	}
	
	@BeforeClass
	public void addImplicitly() {
		driver.manage().timeouts().implicitlyWait(Property.TIMEOUT, TimeUnit.SECONDS);
	}
	
	@Test
	public void loginHCPAccount() {
		boolean loginIsComplete = new LoginPage(driver).doLogin(Property.HCP_LOGIN, Property.HCP_PASS).adminPageButtonDisplayed();
		Assert.assertTrue(loginIsComplete);
	}
	
	@Test(dependsOnMethods = {"loginHCPAccount"})
	public void navigateToAdminPage() {
		boolean navigatedToAdminPage = new MainPage(driver).navigateToAdminPage().newHCPAccountBtnDisplayed();
		Assert.assertTrue(navigatedToAdminPage);
	}
	
	@Test(dependsOnMethods = {"navigateToAdminPage"})
	public void openNewHCPAccountForm() {
		boolean newHCPAccountFormOpen = new AdminPage(driver).openNewHCPAccountForm().newHCPAccountFormDisplayed();
		Assert.assertTrue(newHCPAccountFormOpen);
	}
	
	@Test(dependsOnMethods = {"openNewHCPAccountForm"})
	public void createNewHCPAccount() throws ValueNotFoundInDropDownException, ElementByXpathNotFoundException {
		boolean accountCreated = new NewHCPAccountPage(driver).createNewHCPAccount(TESTSTRING, TESTSTRING, Property.NEW_ACCOUNT_EMAIL, TESTSITE, true, true).newHCPAccountIsCreated(Property.NEW_ACCOUNT_EMAIL);
		Assert.assertTrue(accountCreated);
	}
}
