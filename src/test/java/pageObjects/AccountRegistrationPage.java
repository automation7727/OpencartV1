package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	// // This is the constructor with the same name as the class name
	public AccountRegistrationPage(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement textFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement textLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement chkPolicy;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		
		textFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		
		textLastname.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tphone) {
		
		txtTelephone.sendKeys(tphone);
	}
	
	public void setPassword(String pwd) {
		
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd) { // Here Password and confirm password is same so passed the same parameter
		
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setchkPolicy() {
		
		chkPolicy.click();
	}
	
	public void setchkAgree() {
		
		chkAgree.click();
	}
	
	public void setbtnContinue() {
		
		btnContinue.click();
		
		/*btnContinue.submit();
		
		Actions act=new Actions(driver);
		act.moveToElement(btnContinue).click().perform();
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", btnContinue);
		
		btnContinue.sendKeys(Keys.RETURN);
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10))
		wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();	*/	
	}
	
	public String getConfirmationMsg() { // Based on the return String of the confirmation msg we will do the validation inside the Test Case
		
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
	}

}
