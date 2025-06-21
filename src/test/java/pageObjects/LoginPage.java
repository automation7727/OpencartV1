package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtusername;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;	
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnlogin;
	
	public void setUsername(String uname) { // We will pass the parameters for Username from the Login Test Case
		
		txtusername.sendKeys(uname);
	}
	
	public void setPassword(String pwdd) { // We will pass the parameters for Password from the Login Test Case
		
		txtpassword.sendKeys(pwdd);
	}
	
	public void clicklogin() {
		
		btnlogin.click();
	}


}
