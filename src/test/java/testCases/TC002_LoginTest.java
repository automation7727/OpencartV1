package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity", "Master"})
	public void login() {
		
		logger.info("*******Starting TC002_Login*******"); // Create a log here
		
		try {
			// 1) Home Page access
			HomePage hpp=new HomePage(driver);
			hpp.clickMyAccount();
			hpp.clickLogin();
			
			LoginPage lp=new LoginPage(driver);
			
			// 2) Login Page
			// This is the Login Test Case with valid credentials so we need to pass the valid username and password hard coded values which we are maintaining in the config.properties file
			// In BaseClass we already loaded this file o=in p variable so we will call method by p variable
			lp.setUsername(p.getProperty("email")); // we put email variable in "" because these are not coming from java these are from config file
			lp.setPassword(p.getProperty("password"));
			lp.clicklogin();
			
			// 3) My Account Page for Verification
			MyAccountPage mp=new MyAccountPage(driver);
			boolean targetpage=mp.isMyAccountPageExists(); // This method will return true or false
			
			// Actual verification
			//Assert.assertEquals(targetpage, true, "Login Failed");
			Assert.assertTrue(targetpage);
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("********* TC002_LoginTest Completed*********");
	}

}
