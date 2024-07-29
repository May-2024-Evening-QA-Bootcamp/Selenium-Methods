package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

// new, you have to manually write it to get access of common actions
// this is possible when they are static in nature, * means all
// This is called static import
import static common.CommonActions.*;

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
	
	@FindBy(linkText = "User ID")
	WebElement forgotUserId;
	
	@FindBy(partialLinkText = "Passwor")
	WebElement forgotPassword;
	
	// To Test implicitly wait
	@FindBy(className = "-newuser-reg")
	WebElement incorrectNewUserRegistration;
	
	@FindBy(xpath = "//label[@id='cms-label-tc']")
	WebElement termsAndCondition;
	
	@FindBy(css = "em.cms-icon.cms-sprite-loggedout.ms-3")
	WebElement logoByCssSelector;
	
	public void clickLogo() {
		logo.click(); // common method 'clickElement()' is not used here
	}
	
	// We used throws to handle exception in this method
	public void clickLoginButton() {
		pause(4000); // common method 'pause()' is used from here
		clickElement(loginButton); // common method 'clickElement()' is used from here
		pause(4000);	
	}
	
	public void clickUserId() {
		clickElement(userId);
	}
	
	public void clickPassword() {
		clickElement(password);
	}
	
	// We used try-catch block to handle exception in this method
	public void clickNewUserRegistration() {
		try {
			Thread.sleep(4000);
			clickElement(newUserRegistration);
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
			clickElement(incorrectNewUserRegistration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// We are using sendKeys() method to input the text in any field
	public void inputTextInUserIdField () {
		pause(4000);
		userId.sendKeys("May2024QA"); // common method 'inputText()' is not used here
		pause(4000);
	}
	
	// We are using 3 common actions here
	public void inputTextInUserIdAndPasswordFieldThenIAgreeAndClickToTheLoginButton() {
		pause(4000);
		inputText(userId, "enthrall_12");
		inputText(password, "OnthrallTest@1234");
		pause(4000);
		clickElement(termsAndCondition);
		pause(4000);
		clickElement(loginButton);
		pause(4000);
	}
	
	// Alternate of above method {Raw Code, some people use like this way}
	// We can use a web element directly in the method, that is also common
	// we don't need to create "webElement" and "common method"
	public void useOfByClassInLoginProcess() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.name("user-d")).sendKeys("enthrall_12");
		driver.findElement(By.name("pass-d")).sendKeys("OnthrallTest@1234");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//label[@id='cms-label-tc']")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("cms-login-submit")).click();
		Thread.sleep(4000);
	}
	
	// Important: Use of linkText as locator
	// Web Element: forgot userId
	public void use_of_linkText_as_locator_in_forgot_userId_hyperlink() {
		pause(3000);
		clickElement(forgotUserId);
		pause(4000);
	}
	
	// Important: Use of partial linkText as locator
	// Web Element: forgot password
	public void use_of_partialLinkText_as_locator_in_forgot_passowrd_hyperlink() {
		pause(3000);
		clickElement(forgotPassword);
		pause(4000);
	}
	
	// Web Element: Logo
	// logo: class attribute value:  cms-icon cms-sprite-loggedout ms-3
	// Exception 'InvalidSelectorException': 'Compound class names not permitted',
	// so even if you see class value is unique but separated by space, 
	// avoid this as class name locator, rather use css selector
	// This scenario will show error and will failed everywhere
	public void why_we_use_cssSelector_as_locator_in_logo () {
		driver.findElement(By.className("cms-icon cms-sprite-loggedout ms-3")).click();
	}
	
	// Web Element: New User Registration Button
	// Important: Use of cssSelector as locator
	// cssSelector is very important for interview
	// cssSelector by class --> inside the string, first put html tag, then dot, then write value of the class attribute 
	public void role1_use_of_cssSelector_by_class_name_value () {
		driver.findElement(By.cssSelector("a.cms-newuser-reg")).click();
	}
	
	// Important: Use of cssSelector as locator
	// Web Element: login Button
	// if you wanna create cssSelector by id 
	// first html tag, then # (hash), then write the value of id attribute inside the string
	public void role2_use_of_cssSelector_by_id_value() {
		driver.findElement(By.cssSelector("button#cms-login-submit")).click();
	}
	
	// Web Element: logo
	// Important: Use of cssSelector as locator
	// if the class (compound class) contain separate words [cms-icon cms-sprite-loggedout ms-3], 
	// they are actually different class, then
	// we have to close the gap between classes by putting the dot/period
	// cssSelector by class --> htmltag.class name value 
	// remove the space between words in class value, and replace with dot/period
	public void role3_use_of_cssSelector_by_compund_class_name_value () {
		driver.findElement(By.cssSelector("em.cms-icon.cms-sprite-loggedout.ms-3")).click();
	}
	
	// Above 3 is mostly used, 99% cases of css selector, also very common important interview question
	// below 3 high level, no need to see if you feel they are tough

	// Use of cssSelector as locator
	// Web Element: login Button
	// high level, you can ignore
	// cssSelector by another attribute
	// --> htmltag[id/class/name attribute = 'value of the attribute' ] , in xpath - you use // and @, which is absent here 
	// and you can use any other attribute name except id and class
	public void role4_use_of_cssSelector_by_attribute_and_its_value () {
		driver.findElement(By.cssSelector("button[name='Submit Login']")).click();
		pause(3);
	}
	
	// Web Element: NUR Button
	// Not important, can ignore
	// cssSelector by another attribute
	// --> htmltag.value of class[id/class/name attribute = 'value of the attribute'] , in xpath you use // and @, which is absent here and you can use attribute except id and class
	// That's why we use title
	public void role5_use_of_cssSelector_by_class_name_value_and_attribute_and_its_value() {
		driver.findElement(By.cssSelector("a.cms-newuser-reg[title='New User Registration']")).click();
		pause(3);
	}
	
	// Important: Use of cssSelector as locator
	// Web Element: login Button
	// Not important, can ignore
	// cssSelector by another attribute
	// --> htmltag#value of ID[attribute name='value'] , in xpath you use // and @, which is absent here and you can use attribute except id and class	
	public void role6_use_of_cssSelector_by_id_value_and_attribute_and_its_value() {
		driver.findElement(By.cssSelector("button#cms-login-submit[title='Login']")).click();
		pause(3);
	}
	
	// Important: Use of tagName as locator
	// tag name: we have to go to Amazon and find "table" tag, which is unique
	
	// in cms portal not a single tag except header
	public void use_of_tagName_as_locator() {
		driver.findElement(By.tagName("header")).click();
	}
	
	// Web Element: logo
	// isDisplayed() method is boolean type, outcome: true or false
	// isDisplayed() is the method used to verify the presence of a web element within the web page.
	// Use of isDisplayed() available in --> image, link, button, text field, check box etc.
	public void use_of_isDisplayed_01() {
		boolean elementDisplayed = driver.findElement(By.cssSelector("em.cms-icon.cms-sprite-loggedout.ms-3")).isDisplayed();
		System.out.println("Is the Logo displayed? Ans: " + elementDisplayed);
	}
	
	public void use_of_isDisplayed_02() {
		boolean eDisplayed = logoByCssSelector.isDisplayed();
		System.out.println("Is the Logo displayed? Ans: " + eDisplayed);
	}

	// use of isDisplayed method inside our common actions --> elementDisplayed()
	public void use_of_isDisplayed_in_login () {
		elementDisplayed(userId); // newly added
		inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password); // newly added
		inputText(password, "OnthrallTest@1234");
		pause(4000);
		clickElement(termsAndCondition);
		pause(4000);
		clickElement(loginButton);
		pause(4000);
	}
	
	// Web Element: login Button
	// isEnabled() is the method used to verify if the web element is enabled or
	// disabled within the web page. isEnabled() is primarily used with buttons.
	// Use of isEnabled(), a boolean type method
	
	// use of isEnabled method inside elementEnabled()
	public void use_of_isEnabled_in_login() {
		elementDisplayed(userId);
		inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password);
		inputText(password, "OnthrallTest@1234");
		pause(4000);
		clickElement(termsAndCondition);
		pause(4000);
		elementEnabled(loginButton); // newly added
		clickElement(loginButton);
		pause(4000);
	}
	
	// Web Element: checkbox
	// Used with radio buttons, dropdowns and checkboxes.
	// use of isSelected() method inside
	
	public void use_of_isSelected_in_login() {
		elementDisplayed(userId);
		inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password);
		inputText(password, "OnthrallTest@1234");
		pause(4000);
		elementSelected(termsAndCondition); // newly added
		clickElement(termsAndCondition);
		pause(4000);
		elementEnabled(loginButton); 
		clickElement(loginButton);
		pause(4000);
	}
	
	
	// In real time scenario we do below test at the beginning of a page, 
	// but by using common action, not by following below codes
	public void getMethodsOfThePage() {
		String actual = driver.getTitle();
		System.out.println("Title of the Page is: " + actual);
		String expected = "CMS Enterprise Portal";
		Assert.assertEquals(actual, expected, "Title doesn't match up");
		
		String currentURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentURL);
		String expectedURL= "https://portal.cms.gov/portal/";
		Assert.assertEquals(currentURL, expectedURL, "The driver failed to direct at right URL");
		
		// use of getText() in "login button"
		String actualTextPresntInTheWebElement = loginButton.getText();
		System.out.println("Text Present as: " + actualTextPresntInTheWebElement);
		String expectedTextPresntInTheWebElement = "Login";
		Assert.assertEquals(actualTextPresntInTheWebElement, expectedTextPresntInTheWebElement, "The text present in the WebElement doesn't match");
	}
	
	// This is the first method used during automation framework
	// what is title?
	// what is the url?
	// is logo displayed?
	// here, method coming from common action
	public void newUserRegistrationPageValidation() {
		pause(3000);
		elementDisplayed(logo);
		verifyTitle(driver, "CMS Enterprise Portal");
		verifyCurrentUrl(driver, "https://portal.cms.gov/portal/");
		elementEnabled(newUserRegistration);
		verifyTextOfTheWebElement(newUserRegistration, "New User Registration");
		clickElement(newUserRegistration);
		pause(5000);
		verifyTitle(driver, "CMS Enterprise Portal - New User Registration");
		verifyCurrentUrl(driver, "https://portal.cms.gov/portal/newuserregistration");
	}
	
	// Here We used User ID field
	// getAttribute() actually give the value of the Attribute, not common
	// raw use, in next method we will use from common action
	public void use_of_getAttribute_method () {
		elementSelected(userId);
		pause(3000);
		String ml = userId.getAttribute("maxlength");
		System.out.println("The value of the maxlength attribute is: " + ml);
		String ph = userId.getAttribute("placeholder");
		System.out.println("The value of the placeholder attribute is: " + ph);
	}
	
	// use of clear()
	public void use_of_clear_in_login() {
		elementDisplayed(userId);
		inputText(userId, "May 2024");
		elementDisplayed(password);
		inputText(password, "abc@1234213");
		pause(3000);
		clearTextFromTheField(userId); // new here
		pause(3000);
		clearTextFromTheField(password); // new here
		pause(3000);
		inputText(userId, "enthrall_12");		
		inputText(password, "OnthrallTest@1234");
		pause(4000);
		elementSelected(termsAndCondition);
		clickElement(termsAndCondition);
		pause(4000);
		elementEnabled(loginButton); 
		verifyTextOfTheWebElement(loginButton, "Login");
		clickElement(loginButton);
		pause(4000);
	}

	
	
	
	
	
	
	
	
	
	
	

	
	

}
