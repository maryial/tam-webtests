package com.epam.tam.exception;

import org.openqa.selenium.WebElement;

public class ValueNotFoundInDropDownException extends Exception {
	public ValueNotFoundInDropDownException(WebElement dropDown, String value) {
		super(dropDown.toString() + " does not contain " + value);
	}
}
