package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/*Login DataDriver Test Case scenarios:
*1) Valid Data ---> Login Successful --> Test Case Passed(Positive) --> Logout
*2) Valid Data ---> Login Unsuccessful--> Test Case Failed
*3) Invalid Data--> Login Successful ---> Test Case Failed(Negative)--> Logout
*4) Invalid Data--> Login UnSuccessful--> Test Case Passed
*/
public class TC003_LoginDDT extends BaseClass {
	
	// // We are providing the @DataProvider to Test method() in the @Test annotation and if @DataProvider method() is in same class means in this class
	// Then this Test method can get the Data from excel sheet to this @test method but in DDT @DataProvider is in another package and another class
	// That is why we need to add one more parameter in @Test method
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven") 
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException { // These String email pwd we are getting from @DataProvider and exp variable is representing data is valid or not from excel file
		
		logger.info("******Starting TC003_LoginDDT Started***********");
		// 1) Home Page access
		try {			
		            HomePage hpp=new HomePage(driver);
					hpp.clickMyAccount();
					hpp.clickLogin();
					
					LoginPage lp=new LoginPage(driver);
					
					// 2) Login Page
					// This is the Login Test Case with valid credentials so we need to pass the valid username and password hard coded values which we are maintaining in the config.properties file
					// In BaseClass we already loaded this file o=in p variable so we will call method by p variable
					lp.setUsername(email); // Here we will get the username from @DataProvider method() email and pwd are the java variables so no need to put in ""
					lp.setPassword(pwd); // Here we will get the Password from @DataProvider
					lp.clicklogin();
					
					// 3) My Account Page for Verification
					MyAccountPage mp=new MyAccountPage(driver);
					boolean targetpage=mp.isMyAccountPageExists(); // This method will return true or false
					
					if(exp.equalsIgnoreCase("Valid")){ 
					    if(targetpage==true) {
					        mp.clkLogout();
					        Assert.assertTrue(true);
					    } else {
					        Assert.assertTrue(false);
					    }
					} else if(exp.equalsIgnoreCase("Invalid")) {  // ✅ Fixed: else-if
					    if(targetpage==true) {
					        mp.clkLogout();
					        Assert.assertTrue(false);
					    } else {
					        Assert.assertTrue(true);
					    }
					}
		}
		catch(Exception e) {
			Assert.fail();
		}
		Thread.sleep(4);
		logger.info("Finished************");
	}
}
