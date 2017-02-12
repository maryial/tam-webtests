package com.epam.tam.util;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.epam.tam.exception.UnsupportedBrowserException;

public class WebDriverProvider {
	
	private static final Map<Browsers, IDriver> DRIVERS = new HashMap<Browsers, IDriver>();
	static
    {
		DRIVERS.put(Browsers.CHROME, (() -> { 
			System.setProperty("webdriver.chrome.driver", Property.PATH_TO_CHROMEDRIVER);
			return new ChromeDriver();
			}));
		
		DRIVERS.put(Browsers.FIREFOX, (() -> new FirefoxDriver()));
    }

	public WebDriver getDriver() throws UnsupportedBrowserException {
		if(DRIVERS.containsKey(Property.BROWSER)) {
			return DRIVERS.get(Property.BROWSER).getDriver();
		}
		else {
			throw new UnsupportedBrowserException(Property.BROWSER);
		}
	}
	
	interface IDriver {
		WebDriver getDriver();
	}
}
