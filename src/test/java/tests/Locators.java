package tests;

import org.openqa.selenium.By;

import util.Property;

public class Locators {

	//Login
	public static final By LOGIN_EMAIL = By.id("email");
	public static final By LOGIN_PASSWORD = By.id("password");
	public static final By LOGIN_BUTTON = By.xpath("//div[@class='auth-card']//button");
	
	//Main Page
	public static final By MAIN_PAGE_ADMIN_LINK = By.xpath("//a[@href='/hcp/']");
	
	//Admin
	public static final By ADMIN_ADD_NEW_USER_BUTTON = By.xpath("//img[@alt='add hcp']");
	public static final By ADMIN_MENU = By.xpath("//div[@class='heading tabs']");
	public static final By ADMIN_CREATED_ACCOUNT_STATUS = By.xpath("//*[.='" + Property.NEW_ACCOUNT_EMAIL + "']/../following-sibling::*/*[.='Pending']");
	public static final By ADMIN_ADD_NEW_USER_POPUP_TEXT = By.xpath("//p[contains(normalize-space(), 'By providing the information below, you are confirming that you have received this person’s consent to provide their information and requesting access to Care4Today')]");
	public static final By ADMIN_ADD_NEW_USER_NAME = By.id("name");
	public static final By ADMIN_ADD_NEW_USER_LASTNAME = By.xpath("//*[@formcontrolname='lastName']");
	public static final By ADMIN_ADD_NEW_USER_EMAIL = By.id("email");
	public static final By ADMIN_ADD_NEW_USER_SITES_DROPDOWN = By.id("sites");
	public static final By ADMIN_ADD_NEW_USER_SUBMIT = By.xpath("//button[@type='submit']");
	
}
