package com.epam.tam.exception;

import org.openqa.selenium.WebElement;

public class ElementByXpathNotFoundException extends Exception {
	public ElementByXpathNotFoundException(String xpath) {
		super(xpath + " is not found");
	}
}
