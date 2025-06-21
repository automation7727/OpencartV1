package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		*/
		
		// Generate report with timestamp
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName); // Specify the location of the file
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of the Report
		sparkReporter.config().setReportName("Opencart Functional Testing"); // Name of the Report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os"); // os we are getting from the xml file
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser"); // browser we are getting from the xml file
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		
		// test is representing the actual report
		// result is the actual test case
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //To display Groups in the Reports
		test.log(Status.PASS,result.getName()+"Executed Successfully");
		//test.log(Status.FAIL, result.getName() + " Got Failed");
	}
	
	public void onTestFailure(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // Use groups for categories, not error messages
		test.log(Status.FAIL, result.getName() + " Got Failed");
		//test.assignCategory(result.getMethod().getGroups()); // Log the error message separately
		try {
			// this will attach the screenshot to the report
			String imgPath= new BaseClass().captureScreen(result.getName()); // this is calling captureScreen() by the object of BaseClass as captureScreen() is in BaseClass and not a static() 
			                                                                 //and getting the test case name which is in result and take the screenshot img and storing and returning the img path in imgPath variable  
			test.addScreenCaptureFromPath(imgPath);
		}catch(Exception e1) {
			e1.getMessage();
			System.out.println("Failed to capture screenshot: " + e1.getMessage());
		}
	}

	private String captureScreenDirectly(WebDriver driver, String testName) {
	    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp + ".png";
	    
	    try {
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(screenshot, new File(targetFilePath));
	        return targetFilePath;
	    } catch (Exception e) {
	        return null;
	    }
	}
	public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"Got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathofExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
