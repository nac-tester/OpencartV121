package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;      //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

public static WebDriver driver;	
public Logger logger;	    //Log4j logger variable
public Properties p;       //create properties class

	

	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");     //".// current project location"
		p=new Properties();
		p.load(file);            //this command load the property file
		
		//Log4j
		logger=LogManager.getLogger(this.getClass());    //this.getClass() pass the class here which class we have to generate the log. It get logger and store into that variable.
		
		
		switch(br.toLowerCase())                         //if browser is chrome then launch chrome, if edge then launch edge n so on
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		default: System.out.println("Invalid browser name.."); return;       
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL2"));    //reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()           //after completion test we have to close it
	{
		driver.quit();
}

	
	    //static data and dynamic data(random data) we can create in run time 
		//random method we can created
	public String randomeString()           //we will get random string from this method
		{
			String generatedstring = RandomStringUtils.randomAlphabetic(10);     //RandomStringUtils is predefined class which is there in commons library not directly from java. in that we have randomAlphabetic method
			return generatedstring;
		}
		
		public String randomeNumber()           
		{
			String generatednumber = RandomStringUtils.randomNumeric(10);    //number generate in string format
			return generatednumber;
		}
		
		public String randomeAlphaNumberic()           
		{
			String generatedstring = RandomStringUtils.randomAlphabetic(3);    
			String generatednumber = RandomStringUtils.randomNumeric(3);    
			return (generatedstring+"@"+generatednumber);           //it returns strings, numbers, special char.
		}
		
		
		
		//Capture Screenshot
		public String captureScreen (String tname) throws IOException {   //take some test name as tname
			
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";  //name which will generate with timestamp
			File targetFile = new File(targetFilePath);
			
			//copy source file into target file
			sourceFile.renameTo(targetFile);
			
			return targetFilePath;   //returning path as where exactly screenshot located
			 
		}
		
	}

