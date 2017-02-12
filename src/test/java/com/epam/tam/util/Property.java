package com.epam.tam.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.tam.pages.NewHCPAccountPage;

public class Property {

	private final static Logger LOGGER = LogManager.getLogger(Property.class);
	private static FileInputStream fis;
    private static Properties property;
    private final static String fileLocation = "src/test/resources/test.properties";
    
    public static final String PATH_TO_CHROMEDRIVER;
    public static final String MAIN_APPLICATION_PAGE_URL;
    public static final String HCP_LOGIN;
    public static final String HCP_PASS;
    public static final Browsers BROWSER;
    public static final int TIMEOUT;
    public static final String PATH_TO_SCREENSHOTS;
    
    static {
	    try {
	        fis = new FileInputStream(fileLocation);
	        property = new Properties();
	        property.load(fis);
	    } catch (IOException e) {
	        LOGGER.error("File not found");
	    }
	        PATH_TO_CHROMEDRIVER = property.getProperty("PATH_TO_CHROMEDRIVER");
	        MAIN_APPLICATION_PAGE_URL = property.getProperty("MAIN_APPLICATION_PAGE_URL");	
	        HCP_LOGIN = property.getProperty("HCP_LOGIN");
	        HCP_PASS = property.getProperty("HCP_PASS");
	        BROWSER = Browsers.forValue(property.getProperty("BROWSER"));
	        if(StringUtils.isNumeric(property.getProperty("TIMEOUT"))) {
	        	TIMEOUT = Integer.parseInt(property.getProperty("TIMEOUT"));
	        }
	        else {
	        	LOGGER.error("Could not parse TIMEOUT property, setting it to 5 sec");
	        	TIMEOUT = 5;
	        }	        
	        PATH_TO_SCREENSHOTS = property.getProperty("PATH_TO_SCREENSHOTS");
    }
}
