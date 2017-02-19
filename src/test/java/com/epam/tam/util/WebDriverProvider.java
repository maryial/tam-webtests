package com.epam.tam.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;








import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;







import com.epam.tam.exception.UnsupportedBrowserException;

public class WebDriverProvider {
	
	private final static Logger LOGGER = LogManager.getLogger(WebDriverProvider.class);
	private static final String SELENIUM_GRID_URL = "http://localhost:4444/wd/hub";	
	
	private static final Map<Browsers, IDriver> DRIVERS = new HashMap<Browsers, IDriver>();

	static
    {
		DRIVERS.put(Browsers.CHROME, (() -> { 
			System.setProperty("webdriver.chrome.driver", Property.PATH_TO_CHROMEDRIVER);
			//return new ChromeDriver();
			DesiredCapabilities caps = DesiredCapabilities.chrome();			
			return caps;
			}));
		
		DRIVERS.put(Browsers.FIREFOX, (() -> {
			DesiredCapabilities caps = DesiredCapabilities.firefox();			
			return caps;
		}));
    }

	public WebDriver getDriver() throws UnsupportedBrowserException {
		if(DRIVERS.containsKey(Property.BROWSER)) {			
			try {
				return new RemoteWebDriver(new URL(SELENIUM_GRID_URL), DRIVERS.get(Property.BROWSER).getDesiredCapabilities());
			} catch (MalformedURLException e) {
				LOGGER.error(e.getMessage());
			}
			return null;
		}
		else {
			throw new UnsupportedBrowserException(Property.BROWSER);
		}
	}
	
	interface IDriver {
		DesiredCapabilities getDesiredCapabilities();
	}
}
