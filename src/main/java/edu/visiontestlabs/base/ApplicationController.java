package edu.visiontestlabs.base;

import org.openqa.selenium.WebDriver;

import edu.visiontestlabs.pages.HomePage;
import edu.visiontestlabs.pages.LoginPage;
import edu.visiontestlabs.pages.RegistrationPage;

public class ApplicationController {
	
	protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    

    public ApplicationController(WebDriver driver) {
        this.driver = driver;
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        
    }


    public WebDriver getDriver(){
        return driver;
    }

	
	  public void navigateToApplication(){
	  driver.navigate().to(ResourceFactory.getInstance().getProperty("APP_URL").toString());
	  
	  }
	 


    public HomePage homePage() {
        return homePage;
    }
    
    public LoginPage loginPage() {
    	return loginPage;
    }

    public RegistrationPage registrationPage() {
    	return registrationPage;
    }

}
