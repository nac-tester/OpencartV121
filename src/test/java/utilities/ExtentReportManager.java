package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;     //variable report name as repName
	
	public void onStart(ITestContext testContext) {    //it will capture testContext means which test method got executed
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		
		repName = "Test-Report-" + timeStamp + ".html";  //Test-Report-first name of the report and attaching timestamp here we did concatenate
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); //specify location of the report
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report"); //Title of the report
		sparkReporter.config().setReportName("opencart Functional Testing"); //name of the report
		sparkReporter.config().setTheme(Theme.DARK);   //theme of the report
		
		//common data we populated
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application" , "opencart");
		extent.setSystemInfo("Module" , "Admin");
		extent.setSystemInfo("Sub Module" , "Customers");
		extent.setSystemInfo("User Name" , System.getProperty("user.name"));
		extent.setSystemInfo("Environment" , "QA");
		
		
		//this value we have pass through xml file
		String os = testContext.getCurrentXmlTest().getParameter("os");   //from xml we are getting parameter os
		extent.setSystemInfo("Operating System" , os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
	    List<String> includedGroups = (List<String>) testContext.getCurrentXmlTest().getIncludedGroups();
	    if(!includedGroups.isEmpty()) {       
	    extent.setSystemInfo("Groups", includedGroups.toString());	 //then add this info to the reports
	    }
	
	   }
	
	
	  public void onTestSuccess(ITestResult result) {       //from result object 
		
		test = extent.createTest(result.getTestClass().getName());   //from result which class we have executed from that we are getting name 
		test.assignCategory(result.getMethod().getGroups());        //to display groups in report
		test.log(Status.PASS,result.getName()+ "got successfully executed");
	}
	
	
     
	  public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+ "got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());    //error msg also we can print
		
		//screenshot of the failure
		try {

			 String imgPath = new BaseClass().captureScreen(result.getName());    //created object from the base class from that object we can call captureScreen method.
			 test.addScreenCaptureFromPath(imgPath);                             //It invoke capturescreen method from baseclass by passing the name and it will return location of the image
			                                                                     //and attached the screenshot to the report.        
			 }catch (IOException e1) {
				 e1.printStackTrace();
			 }
          }
     
   
     
   
	  public void onTestSkipped(ITestResult result) {
 		test = extent.createTest(result.getTestClass().getName());
 		test.assignCategory(result.getMethod().getGroups());
 		test.log(Status.SKIP,result.getName()+ "got skipped");
 		test.log(Status.INFO,result.getThrowable().getMessage());
 		
     }
     
     
     
     
	  public void onFinish(ITestContext  testContext) {
    	 
    	 extent.flush();    //consolidate all the info from the report and generated
    	 
    	
    	 
    	 //Automatically report will be open
    	 /* String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
    	 File extentReport = new File(pathOfExtentReport);
    	 
    	 try {
    		 Desktop.getDesktop().browse(extentReport.toURI());
    	 }catch (IOException e) {
    		 e.printStackTrace();
    	 }
     */
     }
}
     
     
