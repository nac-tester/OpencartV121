package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver)           //constructor name should same as class name
	{
		super(driver);              
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement InkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement InkRegister;
	
	@FindBy(linkText = "Login")            //login link added in step5
	WebElement linkLogin;
	
	
	
	public void clickMyAccount()             //action methods
	{
		InkMyaccount.click();
	}
	
	public void clickRegister()            //action methods
	{
		InkRegister.click();
	}
	
	public void clickLogin()            //action methods
	{
		linkLogin.click();
	}
}
