package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	// parameterized constructor initialized when class in instantiated [object created]
	public HomePage(WebDriver driver) {
		this.driver = driver;
		// PageFactory class help to instantiate WebElements
		// Important interview question
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//em[@id='cms-homepage-header-logo-unauth' and @class='cms-icon cms-sprite-loggedout ms-3' ]")
	WebElement logo;
	
	// 1st way: Most common way to write the WebElement (@FindBy), 
	// you must know how to do that, 98% cases, this is used
	// Use of unique 'Id' attribute as locator
	@FindBy(id = "cms-login-submit")
	WebElement loginButton;
	
	// Use of unique 'name' attribute as locator
	@FindBy(name = "user-d")
	WebElement userId;
	
	// 2nd way to create a WebElement: not common, here I used for the 'password'
	// Just to make you guys familiar, if you see in your job
	@FindBy(how = How.NAME, using = "pass-d")
	WebElement password;
	
	// Use of unique  'class' attribute as locator
	// FYI: Never take a class value as unique if they have white space between words. 
	// Example: class value of logo, this is unique, but a compound class --> "cms-icon cms-sprite-loggedout ms-3"
	@FindBy(className = "cms-newuser-reg")
	WebElement newUserRegistration;
	
	// 3rd way to create a WebElement with "By" Class: 
	// Not common, here I used unlock web element from the home page
	// instead of xpath, we can use id, name, class etc as locator.
	// By unlock = By.id("cms-unlock-account");	
	By unlock = By.xpath("//a[contains(text(), 'un')]"); 
	
	// To Test implicitly wait
	@FindBy(className = "-newuser-reg")
	WebElement incorrectNewUserRegistration;
	
	@FindBy(xpath = "//label[@id='cms-label-tc']")
	WebElement checkBox;
	
	public void clickLogo() {
		logo.click();
	}
	
	// We used throws to handle exception in this method
	public void clickLoginButton() throws InterruptedException {
		Thread.sleep(4000);
		loginButton.click();
		Thread.sleep(4000);		
	}
	
	public void clickUserId() {
		userId.click();
	}
	
	public void clickPassword() {
		password.click();
	}
	
	// We used try-catch block to handle exception in this method
	public void clickNewUserRegistration() {
		try {
			Thread.sleep(4000);
			newUserRegistration.click();
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Not common, just for your reference
	public void clickUnlock() {
		try {
			Thread.sleep(4000);
			// This is the new way to call the web element
			driver.findElement(unlock).click();
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			System.out.println(e); // alternate of e.printStackTrace()
		}		
	}
	
	// How to test implicitlyWait()? or
	// How to show the exception: NoSuchElementException
	public void clickIncorrectNewUserRegistration() {
		try {
			Thread.sleep(4000);
			incorrectNewUserRegistration.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
