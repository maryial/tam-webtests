package com.epam.tam.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.epam.tam.pages.Page;

public class Utils {
	
	private final static Logger LOGGER = LogManager.getLogger(Utils.class);

	public static void makeScreenshot(WebDriver driver) {
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFileToDirectory(screenshot, new File(Property.PATH_TO_SCREENSHOTS));
			LOGGER.info("Taken screenshot " + screenshot.getName());
		}
		catch (Exception e){
			LOGGER.error(e.getMessage());
		}
	}
}
