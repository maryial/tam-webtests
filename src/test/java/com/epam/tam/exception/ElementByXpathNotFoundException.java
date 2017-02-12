package com.epam.tam.exception;

import org.openqa.selenium.NoSuchElementException;

public class ElementByXpathNotFoundException extends Exception {
	public ElementByXpathNotFoundException(String xpath, NoSuchElementException e) {
		super(xpath + " is not found. Original error:\n" + e);
	}
}
