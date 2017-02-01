package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import exception.UnsupportedBrowserException;
import util.Property;
import util.WebDriverProvider;

public class BaseTest {
	public static WebDriver driver;
	protected static final int WAIT_ELEMENT_TIMEOUT = 5;
	protected WebDriverProvider webDriverProvider;
	
	public BaseTest() {
		webDriverProvider = new WebDriverProvider();
	}
	
	public void waitForElementPresent(By locator) {
		new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public void waitForElementVisible(By locator) {
		new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	@BeforeClass
	public void setUp() {
		if(driver == null) {
			try {
				driver = webDriverProvider.getDriver();
				driver.manage().window().maximize();
			} catch (UnsupportedBrowserException e) {
				e.printStackTrace();
			}
		}
	}
	
	@AfterClass
	public void cleanUp() {
		driver.close();
		driver = null;
	}
	
	public void enterText(By by, String text) {
		waitForElementPresent(by);
		driver.findElement(by).sendKeys(text);
	}
	
	public void click(By by) {
		waitForElementPresent(by);
		driver.findElement(by).click();
	}
	
	public WebElement getWebElement(By by) {
		waitForElementPresent(by);
		waitForElementVisible(by);
		WebElement webElement = driver.findElement(by);
		return webElement;
	}
	
	public void selectInDropDown(By by, int index) {
		waitForElementPresent(by);
		Select select = new Select(driver.findElement(by));
		select.selectByIndex(index);		
	}
}
