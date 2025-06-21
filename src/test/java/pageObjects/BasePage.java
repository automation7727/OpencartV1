package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	
	// This is the constructor with the same name as the class name
	// BY creating this constructor in Parent class which is BasePage class we achieved the Inheritance (Re-usability).
	//The constructor sets up your "contact list" of web elements so you can easily use them throughout your page class!
	
	/*
	 * Very Important
	 * In Plain English for classes:

		Test wants HomePage to control a browser
		Test gives the browser control (driver) to HomePage
		HomePage says "I don't know how to set up browsers, let me give this to BasePage"
		BasePage receives the browser control and does all the setup work
		Now HomePage can use the browser through BasePage
	 */
	public BasePage(WebDriver driver) {
		
		this.driver=driver; // This line is storing the WebDriver instance so the entire class can use it later
		PageFactory.initElements(driver, this); // The constructor sets up your "contact list" of web elements so you can easily use them throughout your page class!
	}

}
