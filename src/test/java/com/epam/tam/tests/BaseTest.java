package com.epam.tam.tests;

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

import com.epam.tam.exception.UnsupportedBrowserException;
import com.epam.tam.util.Property;
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
