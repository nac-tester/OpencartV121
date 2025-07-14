package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	WebDriver driver;
	
	public BasePage(WebDriver driver)           //base page only has constructor this is the parent of all page object classes
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);      
	}
}
