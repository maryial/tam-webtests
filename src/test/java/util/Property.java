package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {

	private static FileInputStream fis;
    private static Properties property;

    public static final String PATH_TO_CHROMEDRIVER;
    public static final String MAIN_APPLICATION_PAGE_URL;
    public static final String HCP_LOGIN;
    public static final String HCP_PASS;
    public static final String NEW_ACCOUNT_EMAIL = "test123@remove.com";
    
    static {
	    try {
	        fis = new FileInputStream("src/test/resources/test.properties");
	        property = new Properties();
	        property.load(fis);
	    } catch (IOException e) {
	        System.err.println("File not found");
	    }
	        PATH_TO_CHROMEDRIVER = property.getProperty("PATH_TO_CHROMEDRIVER");
	        MAIN_APPLICATION_PAGE_URL = property.getProperty("MAIN_APPLICATION_PAGE_URL");	
	        HCP_LOGIN = property.getProperty("HCP_LOGIN");
	        HCP_PASS = property.getProperty("HCP_PASS");
    }
}
