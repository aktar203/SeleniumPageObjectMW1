package edu.visiontestlabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import edu.visiontestlabs.base.PageBase;

public class HomePage extends PageBase{

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
		
	}
	
	// Web Element
	@FindBy(how = How.XPATH, using = "gffhfhhjjsj")
	private WebElement homeLink;
	
	@FindBy(id = "hgdhgjkdhjkg")
	private WebElement loginLink;
	
	@FindBy(id = "hgdhgj")
	private WebElement eleUserName;
	
	

	
	// Method, Actions
	
	public void clickHomeLink() {
		
		//homeLink.click();
		super.click(homeLink);
		
	}
	
	
	public void clickloginLink() {
		//homeLink.click();
		super.click(loginLink);
		
	}


	public void clickLoginButton() {
		// TODO Auto-generated method stub
		
	}


	public WebElement enterUsername(String user) {
		//eleUserName.sendKeys("hjshjkdjshd");
		super.sendkeys(eleUserName, user);
		return eleUserName;
		
	}


	public void enterPassword(String string) {
		
		
	}


	public void clickSubmit() {
		// TODO Auto-generated method stub
		
	}


	public void verifyLogin(String string) {	
		
	}


	public void login(String username, String password) {
		
		this.enterUsername(username);
		this.enterPassword(password);
		
	}
	
	

}
