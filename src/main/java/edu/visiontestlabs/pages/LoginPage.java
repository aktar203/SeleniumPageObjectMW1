package edu.visiontestlabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import edu.visiontestlabs.base.PageBase;

public class LoginPage extends PageBase{

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	// Elements
	@FindBy(id = "loginId")
	private WebElement loginLink;
	
	@FindBy(xpath = "//a[jkshfshfjk]")
	private WebElement userNameField;

	@FindBy(name = "successfulLoign")
	private WebElement loginVerfy;
	
	
	
	
	// Actions/ Methods
	
	public void clickLoginLink() {
		//loginLink.click();
		super.click(loginLink);
		super.getDriver().getTitle();
	}


	public void enterUserName(String userName) {
	
	super.sendkeys(userNameField, userName);
		
	}


	public void enterPassword(String string) {
		// TODO Auto-generated method stub
		
	}



	public void submitButton() {
		// TODO Auto-generated method stub
		
	}



	public void verifyLogin(String msg) {
		super.assertText(loginVerfy, msg);
		
	}

}
