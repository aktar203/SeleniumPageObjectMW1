package edu.visiontestlabs.base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
	private static DriverFactory instance = null;
	private static String browser = null;
	private ThreadLocal<WebDriver> driverCollection = new ThreadLocal<WebDriver>();
	
	private static final String AUTOMATE_USERNAME = "shiftqauser1";
	private static final String AUTOMATE_ACCESS_KEY = "KqRYsSY1LcUwxif6AArC";
	private static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	private static final String LOCAL_GRID_URL = "http://192.168.86.118:4444/wd/hub";
	  
	private DriverFactory(){
		
    }

	public static DriverFactory getInstance(){  
		String browserType = System.getenv("browser");
		if(browserType == null) {
			browserType = "CHROME";
		}
        instance = getInstance(browserType);
        return instance;
    }

	public static DriverFactory getInstance(String browserType) {
		
		if(browserType == null || browserType.length() == 0) {
			throw new RuntimeException("Please setup browser as environment variable or send browser as a parameter");
		}
		else {
			DriverFactory.browser = browserType;
		}
		
        if (instance == null) {
            instance = new DriverFactory();
        }

        if(instance.driverCollection.get() == null) {
        	WebDriver driver = null;
            if(DriverFactory.browser.toUpperCase().contentEquals("CHROME")) {
            	 ChromeDriverManager.chromedriver().setup();
            	 ChromeOptions options = new ChromeOptions();
            	 options.addArguments("--ignore-certificate-errors")
                 		.addArguments("--whitelisted-ips=\"\"");
                 driver = new ChromeDriver(options);
            }
            else if(DriverFactory.browser.toUpperCase().contentEquals("CHROME-HEADLESS")) {
           	    ChromeDriverManager.chromedriver().setup();
	           	ChromeOptions options = new ChromeOptions();
	            options.addArguments("--headless")
	                    .addArguments("--disable-gpu")
	                    .addArguments("--window-size=1920,1080")
	                    .addArguments("--ignore-certificate-errors")
	                    .addArguments("--whitelisted-ips=\"\"");

             //options.setBinary("/Path/to/specific/version/of/Google Chrome");
             driver = new ChromeDriver(options);

           }
            else if(DriverFactory.browser.toUpperCase().contentEquals("LINUX-CHROME")) {
				ChromeDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
				options.addArguments("--headless");
				options.addArguments("start-maximized"); // open Browser in maximized mode
				options.addArguments("disable-infobars"); // disabling infobars
				options.addArguments("--disable-extensions"); // disabling extensions
				options.addArguments("--disable-gpu"); // applicable to windows os only
				options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
	           	options.addArguments("--ignore-certificate-errors");
	           	options.addArguments("--whitelisted-ips=\"\"");
	            
	           	driver = new ChromeDriver(options);
	        }
            else if(DriverFactory.browser.toUpperCase().contentEquals("CHROME-GRID")) {
            	ChromeOptions options = new ChromeOptions();
           	 	options.addArguments("--ignore-certificate-errors")
                		.addArguments("--whitelisted-ips=\"\"");
           	 
            	DesiredCapabilities caps = new DesiredCapabilities();
            	caps.setPlatform(Platform.ANY);
                caps.setBrowserName("chrome");
                caps.setCapability("browser", "Chrome");
                caps.setCapability("browser_version", "latest");
                
                caps.setCapability(ChromeOptions.CAPABILITY, options);
                
                try {
					driver = new RemoteWebDriver(new URL(LOCAL_GRID_URL), caps);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
            }
            else if(DriverFactory.browser.toUpperCase().contentEquals("WIN-CHROME-CLOUD")) {
            	ChromeOptions options = new ChromeOptions();
           	 	options.addArguments("--ignore-certificate-errors")
                		.addArguments("--whitelisted-ips=\"\"");
           	 
            	DesiredCapabilities caps = new DesiredCapabilities();
            	caps.setCapability("os_version", "10");
                caps.setCapability("resolution", "1920x1080");
                caps.setCapability("browser", "Chrome");
                caps.setCapability("browser_version", "latest");
                caps.setCapability("os", "Windows");
                caps.setCapability("name", "BStack-[Java] Sample Test"); // test name
                caps.setCapability("build", "WIN-CHROME-CLOUD"); // CI/CD job or build name
                
                caps.setCapability(ChromeOptions.CAPABILITY, options);
                
                try {
					driver = new RemoteWebDriver(new URL(URL), caps);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
            }
            else if(DriverFactory.browser.toUpperCase().contentEquals("WIN-EDGE-CLOUD")) {
              	 
            	DesiredCapabilities caps = new DesiredCapabilities();
            	caps.setCapability("os_version", "10");
                caps.setCapability("resolution", "1920x1080");
                caps.setCapability("browser", "Edge");
                caps.setCapability("browser_version", "latest");
                caps.setCapability("os", "Windows");
                caps.setCapability("name", "BStack-[Java] Sample Test"); // test name
                caps.setCapability("build", "BStack Build Number 1"); // CI/CD job or build name	
            	
                try {
					driver = new RemoteWebDriver(new URL(URL), caps);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
            }
            else if(DriverFactory.browser.toUpperCase().contentEquals("MAC-CHROME-CLOUD")) {
            	ChromeOptions options = new ChromeOptions();
           	 	options.addArguments("--ignore-certificate-errors")
                		.addArguments("--whitelisted-ips=\"\"");
           	 
           	 	
            	DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("os_version", "Catalina");
                caps.setCapability("resolution", "1920x1080");
                caps.setCapability("browser", "Chrome");
                caps.setCapability("browser_version", "latest");
                caps.setCapability("os", "OS X");
                caps.setCapability("name", "BStack-[Java] Sample Test"); // test name
                caps.setCapability("build", "MAC-CHROME-CLOUD"); // CI/CD job or build name	
                caps.setCapability(ChromeOptions.CAPABILITY, options);
                
                try {
					driver = new RemoteWebDriver(new URL(URL), caps);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
            }
            else if(DriverFactory.browser.toUpperCase().contentEquals("FIREFOX")) {
            	FirefoxDriverManager.firefoxdriver().arch64().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("marionette", true);
                driver = new FirefoxDriver(options);
            }
            else{
                throw new RuntimeException("Invalid browser name : " + browser);
            }
            
            instance.driverCollection.set(driver);
        }
        
        return instance;
	}
	
	public WebDriver getDriver() {
        return driverCollection.get();
    }

	public void quit() {
        // Quits the driver and closes the browser
        try {
            driverCollection.get().quit();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            driverCollection.remove();
        }
    }


}
