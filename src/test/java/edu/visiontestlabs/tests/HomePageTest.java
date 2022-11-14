package edu.visiontestlabs.tests;



import org.testng.annotations.Test;

import edu.visiontestlabs.base.TestBase;

public class HomePageTest extends TestBase{
	
	
	  @Test 
	  public void homePageLinkTest() {
			
			
			  heatClinic().homePage().clickHomeLink();
			  heatClinic().homePage().clickSubmit();
			 
	  }
	 
	
	@Test
	public void loginTest() {
		
		
		  heatClinic().homePage().clickLoginButton();
		  heatClinic().homePage().enterUsername("Emadad");
		  heatClinic().homePage().enterPassword("abc12233");
		  heatClinic().homePage().clickSubmit();
		 
		
	}
	
	public void invalidLoginTest() {
		heatClinic().homePage().clickLoginButton();
		//heatClinic().homePage().enterUsername("yrujsdhsfhksjf");
		
		heatClinic().homePage().login("Emdad","abc12233");
		heatClinic().homePage().clickSubmit();
		heatClinic().homePage().verifyLogin("Welcome Emadad");
		
		
	}
}
