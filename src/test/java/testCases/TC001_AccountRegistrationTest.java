package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() { // To implement this method we need to take the help of 2 POC we created
		
		logger.info("*****Starting TC001_AccountRegistrationTest.java  ******");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("*****Clicked on My Account  ******");
		hp.clickRegister();
		
		AccountRegistrationPage ar=new AccountRegistrationPage(driver);
		
		logger.info("*****Providing Customer Details ******");
		ar.setFirstName(randomString().toUpperCase());
		ar.setLastName(randomString().toUpperCase());
		// If we will run test cases again then same email exist error will come so we need to prepare the test Data or we need to generate the email randomely
		//1) Before executing the test case we can create our static Data and use it 
		//2) We can use the dynamic data at the run time Randomly
		//3) To create the email Randomly we need to create our own user defined method to generate the email randomly so this method will randomly generate the Strings from a-z for email
		//4) In common library not directly in Java there is a Pre-defined class RandomStringUtils inside this there is a method randomAlphabetic(5)
		ar.setEmail(randomString()+"@gmail.com"); // abc772747@gmail.com
		ar.setTelephone(randomNumber());
		
		//String password=randomAlphaNumeric();
		
		String pass=alphaNumeric();
		
		ar.setPassword(pass);  // xyz123
		ar.setConfirmPassword(pass);
		ar.setchkPolicy();
		ar.setchkAgree();
		ar.setbtnContinue();
		
		// Perform the validation of success msg
		
		String msg=ar.getConfirmationMsg(); // This will return the confirmation in String format
		if(msg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Test Failed:...");
			logger.debug("Debug logs:.."); // It will capture all the debug level logs
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(msg, "Your Account Has Been Created!!"); // Validated using Assert class
		}catch(Exception e) {
			Assert.fail();
		}
	}
	
}
