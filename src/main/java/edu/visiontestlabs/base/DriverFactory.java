package edu.visiontestlabs.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
	
	public static final String USERNAME = "iqbalchowdhury1";
    public static final String AUTOMATE_KEY = "nD1DvzLsmuLTBxtL388S";
    public static final String REMOTE_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";



    private DriverFactory(){
        //Do-nothing..Do not allow to initialize this class from outside
    }
    
    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance()
    {
        return instance;
    }

    //ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
    		ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>()
    {
        @Override
        protected WebDriver initialValue()
        {

            URL SELENIUM_HUB = null;

            try {
                SELENIUM_HUB = new URL("http://10.10.30.140:4444/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String driverName = ResourceFactory.getInstance().getProperty("DRIVER").toString();
            if(driverName.toUpperCase().contentEquals("CHROME")){
                //ChromeDriverManager.getInstance().setup();
                String chromeBinayPath;
                chromeBinayPath =  System.getProperty("user.dir") + "/src/main/resources/driver/32/chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", chromeBinayPath);
                return new ChromeDriver();

            }
            else if(driverName.toUpperCase().contentEquals("IE")){
                String ieBinayPath;
                ieBinayPath = System.getProperty("user.dir") + "/src/main/resources/driver/32/IEDriverServer.exe";
                System.setProperty("webdriver.ie.driver", ieBinayPath );
                return new InternetExplorerDriver();
            }
            
            else if(driverName.toUpperCase().contentEquals("FIREFOX")){
                return new FirefoxDriver();
            }
      
			/*
			 * else if(driverName.toUpperCase().contentEquals("R-FF")){ WebDriver driver =
			 * null; DesiredCapabilities capabilities = DesiredCapabilities.firefox(); //
			 * driver = new RemoteWebDriver(new
			 * URL("http://10.10.30.35:4444/wd/hub"),capabilities); driver = new
			 * RemoteWebDriver(SELENIUM_HUB,capabilities); return driver; }
			 */
			/*
			 * else if(driverName.toUpperCase().contentEquals("R-CH")){ WebDriver driver =
			 * null; DesiredCapabilities capabilities = DesiredCapabilities.chrome(); driver
			 * = new RemoteWebDriver(SELENIUM_HUB,capabilities); return driver; }
			 */
            else if (driverName.toUpperCase().contentEquals("BS-FF")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("browser", "Firefox");
                caps.setCapability("browser_version", "46.0");
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "7");
                caps.setCapability("resolution", "1024x768");

                caps.setCapability("browserstack.debug", "true");

                WebDriver driver= null;
                try {
                    driver= new RemoteWebDriver(new URL(REMOTE_URL),caps);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return driver;
            }
            else if(driverName.toUpperCase().contentEquals("BS-IE")){
                DesiredCapabilities caps = new DesiredCapabilities();

                caps.setCapability("browser", "IE");
                caps.setCapability("browser_version", "11.0");
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("resolution", "1280x1024");

                caps.setCapability("browserstack.debug", "true");

                WebDriver driver = null;
                try {
                    driver = new RemoteWebDriver(new URL(REMOTE_URL), caps);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return driver;
            }
            else {
                return new FirefoxDriver(); // can be replaced with other browser drivers
            }
        }
    };

    public WebDriver getDriver() // call this method to get the driver object and launch the browser
    {
        return driver.get();
    }

    public void removeDriver() // Quits the driver and closes the browser
    {
        driver.get().close();
        driver.get().quit();
        driver.remove();
    }

}
