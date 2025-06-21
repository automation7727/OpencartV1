package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
    public Properties p; // Created Properties file variable p
 // This is the first driver in the BaseClass and second driver is created in ExtentReportManager in captureScreen method()
 // as we created the object of the BaseClass which also have a driver so there will be a conflict so to make this problem 
 // sorted we need to make the WebDriver driver in BaseClass as static so that same driver will be reffered in the BasesClass object
    public static WebDriver driver; 
    public Logger logger; // Creating public variable of Logger class
	
	@BeforeClass(groups= {"Sanity","Master","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException { // We must make all these methods public as we are working with multiple packages so that we can access them through out the project
		
		// Below code is representing the location of the config fiel in the Project
		FileReader fr=new FileReader("./src//test/resources//config.properties");
		
		// Loading config.properties file
		p=new Properties(); // created the object of the Properties class
		p.load(fr); // This command will load the file
		
		// We have to use this logger variable for the logs for particular class we execute at run time as it will get the log4j2.xml file
		// so we will use the logger variable to generate the logs
		logger=LogManager.getLogger(this.getClass()); // Here this represent the class so it will take the class dynamically at the run time and it will get the logs for that class and store into a variable
		
		// Condition for launching the Browser on remote machine taking it from config file
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap=new DesiredCapabilities();
			
			// OS
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			}else if(os.equalsIgnoreCase("linux")){
				cap.setPlatform(Platform.LINUX);
			}else {
				System.out.println("No matching os");
				return;
			}
			
			// browser
			switch(br.toLowerCase()) {
			case "chrome" : cap.setBrowserName("chrome");break;
			case "edge"  :  cap.setBrowserName("MicrosoftEdge");break;
			case "firefox"  :  cap.setBrowserName("firefox");break;
			default: System.out.println("No matching Browser");return;
			}
			
			// Creating the driver below for Remote Execution
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap); // This URL will be the same even if we use the Docker Images // http://localhost:4444
		}
			
			// Condition for launching the Browser on remote machine taking it from config file
		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
				
				// Launching the browser on local environment
				switch(br.toLowerCase()) {
				case "chrome" : driver=new ChromeDriver(); break;
				case "firefox": driver=new FirefoxDriver(); break;
				case "edge"   : driver=new EdgeDriver(); break;
				default  : System.out.println("Invalid Browser name.."); return; // return means it will come out from the entire Test cases execution and nothing will execute after this
				}
		}
		
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies(); // This will delete all the cookies from the Web Page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Capturing the data from the config.properties file using p variable as it has the file inside this variable and passing the variable to driver where we hard coded the values
		driver.get(p.getProperty("appURL")); // Reading URL from the config.properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Master","Regression"})
	public void tearDown() {
		
		driver.quit();
	}
	
	@SuppressWarnings("deprecation")
	public String randomString() {  // When we will call this method it will return the random String
				
		String generatedmail=RandomStringUtils.randomAlphabetic(5);
		return generatedmail;
	}
	
	@SuppressWarnings("deprecation")
	public String randomNumber() {  // When we will call this method it will return the random String
		
		String rnum=RandomStringUtils.randomNumeric(10);
		return rnum;
	}
	
	@SuppressWarnings("deprecation")
	public String alphaNumeric() {  // When we will call this method it will return the random String
		
		String anum=RandomStringUtils.randomAlphabetic(3);
		String anum1=RandomStringUtils.randomNumeric(4);
		return (anum+"@"+anum1);
	}
	
	public String captureScreen(String tname) {
		
		String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		TakesScreenshot takesScreenshot=(TakesScreenshot) driver; // TakesScreenshot is special type of Interface to capture the screenshots
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}

}
