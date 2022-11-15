package edu.visiontestlabs.tests;

import org.testng.annotations.Test;

import edu.visiontestlabs.base.TestBase;

public class LoginPageTest extends TestBase{
	
	@Test
	public void validLoginTest() {
		heatClinic().loginPage().clickLoginLink();
		heatClinic().loginPage().enterUserName("Emdad1122@yahoo.com");
		heatClinic().loginPage().enterPassword("ed1222333");
		heatClinic().loginPage().submitButton();
		heatClinic().loginPage().verifyLogin("Welcome to Emdad");
		
	}
	
	
	

}
