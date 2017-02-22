package com.epam.tam.tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;

import com.epam.tam.exception.ElementByXpathNotFoundException;
import com.epam.tam.exception.UnsupportedDoctorTypeException;
import com.epam.tam.exception.ValueNotFoundInDropDownException;
import com.epam.tam.model.Doctor;
import com.epam.tam.model.DoctorFactory;
import com.epam.tam.model.DoctorType;
import com.epam.tam.model.LoginAccount;
import com.epam.tam.model.Site;
import com.epam.tam.model.SiteBuilder;
import com.epam.tam.model.SiteManager;
import com.epam.tam.model.SiteWithAddressBuilder;
import com.epam.tam.pages.AdminPage;
import com.epam.tam.pages.LoginPage;
import com.epam.tam.pages.MainPage;
import com.epam.tam.pages.NewHCPAccountPage;
import com.epam.tam.pages.NewSitePage;
import com.epam.tam.util.Property;

public class CreateNewHCPAccountTestPO extends BaseTest{
	
	private static final String TEST_STRING = "test";
	private static String TEST_SITE = "";
	private static final String NEW_ACCOUNT_EMAIL = RandomStringUtils.random(10) + "@" + RandomStringUtils.random(5) + ".delete";
	private static final String IMPORTANT_INFORMATION_TEXT = "The adherence percentage is calculated by the number of doses that have been indicated as taken, divided by the number of doses scheduled over the last 30 days";
	private static final String NO_RESULTS_FOUND = "NO RESULTS FOUND";
	private static final String TEST_SEARCH_STRING = "Notexistingtext3w33rer";
	
	
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
		boolean loginIsComplete = new LoginPage(driver).doLogin(LoginAccount.getInstance()).adminPageButtonDisplayed();
		Assert.assertTrue(loginIsComplete);
	}	
	
	@Test(dependsOnMethods = {"loginHCPAccount"})
	public void verifyImportantInformationTooltipText() throws ElementByXpathNotFoundException {
		String text = new MainPage(driver).getImportantInformationTooltipText().getText();
		Assert.assertTrue(text.contains(IMPORTANT_INFORMATION_TEXT), "The expected text '" + IMPORTANT_INFORMATION_TEXT + "' not found in tooltip pop up: " + text);
	}
	
	@Test(dependsOnMethods = {"verifyImportantInformationTooltipText"})
	public void verifySearchOfNotExistingPatient() throws ElementByXpathNotFoundException {
		String text = new MainPage(driver).searchText(TEST_SEARCH_STRING).getText();
		Assert.assertTrue(text.contains(NO_RESULTS_FOUND), "The expected text '" + NO_RESULTS_FOUND + "' not found: " + text);
	}

	@Test(dependsOnMethods = {"verifySearchOfNotExistingPatient"})
	public void navigateToAdminPage() {
		boolean navigatedToAdminPage = new MainPage(driver).navigateToAdminPage().newHCPAccountBtnDisplayed();
		Assert.assertTrue(navigatedToAdminPage);
	}
	
	@Test(dependsOnMethods = {"navigateToAdminPage"})
	public void openNewSiteForm() {
		boolean newSiteFormOpen = new AdminPage(driver).openNewSiteForm().newSiteFormDisplayed();
		Assert.assertTrue(newSiteFormOpen);
	}
	
	@Test(dependsOnMethods = {"openNewSiteForm"})
	public void createNewSite() throws ElementByXpathNotFoundException {
		SiteManager manager = new SiteManager();
		SiteBuilder builder = new SiteWithAddressBuilder();		
		manager.setSiteBuilder(builder);
		Site site = manager.getSite();
		TEST_SITE = site.getName();
		boolean siteCreated = new NewSitePage(driver).createNewSite(site).newSiteIsCreated(TEST_SITE);
		Assert.assertTrue(siteCreated, "Site with name " + TEST_SITE + " failed to get created");
	}	
	
	@Test(dependsOnMethods = {"createNewSite"})
	public void createNewHCPAccount() throws ValueNotFoundInDropDownException, ElementByXpathNotFoundException, UnsupportedDoctorTypeException {
		Doctor doctor = DoctorFactory.getDoctor(NEW_ACCOUNT_EMAIL, TEST_STRING, TEST_STRING, TEST_SITE, DoctorType.ORG_ADMIN);
		boolean accountCreated = new NewHCPAccountPage(driver).createNewHCPAccount(doctor).newHCPAccountIsCreated(NEW_ACCOUNT_EMAIL);
		Assert.assertTrue(accountCreated);
	}
}
