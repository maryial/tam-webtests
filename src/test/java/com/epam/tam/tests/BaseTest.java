package com.epam.tam.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.epam.tam.exception.UnsupportedBrowserException;
import com.epam.tam.util.WebDriverProvider;

public class BaseTest {
	protected static WebDriver driver;
	protected WebDriverProvider webDriverProvider;
	
	
	public BaseTest() {
		webDriverProvider = new WebDriverProvider();
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
}
