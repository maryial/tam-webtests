package com.epam.tam.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	private static final String IMPORTANT_INFORMATION_TEXT = "The adherence percentage is calculated by the number of doses that have been indicated as taken, divided by the number of doses scheduled over the last 30 days.It should not be relied upon as an indicator that medication is being taken as prescribed.";
	private static final String NO_RESULTS_FOUND = "NO RESULTS FOUND";
	
	
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
	public void verifyImportantInformationTooltipText() throws ElementByXpathNotFoundException {
		String text = new MainPage(driver).getImportantInformationTooltipText().getText();
		Assert.assertTrue(text.contains(IMPORTANT_INFORMATION_TEXT), "The expected text '" + IMPORTANT_INFORMATION_TEXT + "' not found in tooltip pop up: " + text);
	}
	
	@Test(dependsOnMethods = {"verifyImportantInformationTooltipText"})
	public void verifySearchOfNotExistingPatient() throws ElementByXpathNotFoundException {
		String text = new MainPage(driver).searchForRubbishText().getText();
		Assert.assertTrue(text.contains(NO_RESULTS_FOUND), "The expected text '" + NO_RESULTS_FOUND + "' not found: " + text);
	}

	@Test(dependsOnMethods = {"verifySearchOfNotExistingPatient"})
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
