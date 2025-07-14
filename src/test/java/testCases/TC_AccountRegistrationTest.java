package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest *****");      //here we used logger and debug logs
		
		//used try catch block if any failure happen in this we need to log error logger. not info log
		try
		{
		
		HomePage hp = new HomePage(driver);    // we will create object to access this page and pass the driver
		hp.clickMyAccount();
		logger.info("***** Cliked on MyAccount Link *****");
		
		hp.clickRegister();
		logger.info("***** Cliked on Register Link *****");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details...");
		regpage.setFirstName(randomeString().toUpperCase());      //dynamically we pass the data hence used randomeString
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");     //here we calling random string method so this method will call and return random string and it will generate in gmail form 
		regpage.setTelephone(randomeNumber());  
		
		
		String password=randomeAlphaNumberic();         //password and confirm password value will not be match hence we capture value in variable and pass that variable
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message...");
		String confmsg =regpage.getConfirmationMsg();                     //it return confirmation msg msg in string format
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg,"Your Account Has Been Created!!");   //adding just assertion. this is the msg we are accepting
		}
		catch(Exception e)
		{
		
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
		
		}
	
	
}