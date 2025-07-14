
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass {
	
	//How we get the data provider into your current test cases so we have to provide location.
	//dataProvider="LoginData-its just a name. dataProviderClass- in which class data provider present.DataProviders-its a class.
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups="Datadriven")  //getting data provider from diff class
	public void verify_loginDDT(String email,String pwd, String exp)
	{   
		logger.info("***** Starting TC003_LoginDDT *****");
		
		try
		{
		
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);                     
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		
		
		/*	Data       Login          Result  
		Valid -    success    -   passed
		Valid -    unsuccess  -   failed
		Invalid -  success    -   failed
		Invalid -  unsuccess  -   passed

		*/
		
		if(exp.equalsIgnoreCase("Valid"))               
         {
	        if(targetPage==true)
	        {
	        	
	        	macc.clickLogout();
	        	Assert.assertTrue(true);
	        	
	        }
	        else
	        {
	          
	 	       Assert.assertTrue(false);
	 	    }
         }   
	        
	        if(exp.equalsIgnoreCase("Invalid"))               
	         {
		        if(targetPage==true)
		        {
		        	macc.clickLogout();
		        	Assert.assertTrue(false);
		        
		        }
		        else
		         {
		 	        Assert.assertTrue(true);
		 	      
		         }
	         }  
		}  
		     catch( Exception e)
		        {
		        	Assert.fail();
		        }
	        
		        logger.info("***** Finished TC003_LoginDDT *****");
	         
         }
		
}        
		        
	       
	        
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	


