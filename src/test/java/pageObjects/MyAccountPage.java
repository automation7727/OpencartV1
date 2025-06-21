package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement btnlogout;
	
	public boolean isMyAccountPageExists() {
		
		try {
			return (msgHeading.isDisplayed()); // This statement will return either true or false for MyAccountPage displayed or not
		}catch(Exception e) {
			return false;
		}	
	}
	
	public void clkLogout() {
		
		btnlogout.click();
	}
}
