package com.epam.tam.exception;

import com.epam.tam.util.Browsers;

public class UnsupportedBrowserException extends Exception {
	public UnsupportedBrowserException(Browsers browser) {
		super(browser.toValue() + " is not supported");
	}
}
