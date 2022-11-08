package edu.visiontestlabs.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class TestBase {
	
	protected WebDriver driver;
    //private ThreadLocal<ApplicationController> threadedApplication = null;
    private ApplicationController applicationController= null;
  

    @BeforeClass
    public void beforeclass(){
      
    }
    @BeforeMethod
    public void startUp(){
        driver = DriverFactory.getInstance().getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        applicationController = new ApplicationController(DriverFactory.getInstance().getDriver());
        
        
		/*
		 * threadedApplication = new ThreadLocal<ApplicationController>(){
		 * 
		 * @Override protected ApplicationController initialValue() { return new
		 * ApplicationController(DriverFactory.getInstance().getDriver()); } };
		 */

    }

    public ApplicationController heatClinic(){
        //return threadedApplication.get();
    	 return applicationController;
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.getInstance().removeDriver();
    }

	

	
	

}
