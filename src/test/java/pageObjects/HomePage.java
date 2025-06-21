package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	// This is the constructor with the same name as the class name
	public HomePage(WebDriver driver) { // Here we are expecting the driver parameter from the Test case and later we will pass the driver
		
		// By this driver is got initiated
		super(driver); // We are passing the driver to the Parent class constructor and Parent class constructor will receive the driver and initiate the driver
		               // Here we have invoked driver variable of the BasePage class which is a parent class by using the super keyword.
	}
	
	@FindBy(xpath="//span[@class='caret']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
	WebElement clklogin;
	
	public void clickMyAccount() {
		
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		
		lnkRegister.click();
	}
	
	public void clickLogin() {
		
		clklogin.click();
	}

}
