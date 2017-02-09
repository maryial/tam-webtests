package com.epam.tam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.epam.tam.exception.ElementByXpathNotFoundException;

public abstract class Page {
	private final static Logger logger = LogManager.getLogger(Page.class);
	protected WebDriver driver;
	protected JavascriptExecutor jsExecutor;
	
	public Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jsExecutor = (JavascriptExecutor) driver;
	}
	
	protected WebElement findWebElementByXpath(String xpath) throws ElementByXpathNotFoundException {
		WebElement element = driver.findElement(By.xpath(xpath));
		if(element.isDisplayed()) {
			highlightElement(element);
			return element;
		}
		logger.error(driver.getPageSource());
		throw new ElementByXpathNotFoundException(xpath);
	}
	
	private void highlightElement(WebElement element) {
		String bg = element.getCssValue("backgroundColor");
		jsExecutor.executeScript("arguments[0].style.backgroundColor = 'yellow'", element);
		jsExecutor.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);
	}
}
