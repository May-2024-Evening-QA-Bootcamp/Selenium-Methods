package baseUtil;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;

public class BaseClass {
	public WebDriver driver; 
	// public ChromeDriver driver;
	// public FirefoxDriver driver;
	// public EdgeDriver driver;
	public  HomePage homePage; 
	
	@BeforeMethod
	public void setUp() {
		// First job is to recognize the location of driver from your Framework
		// right click on chromedriver.exe(windows)/chromedriver(mac) ---> properties ---> copy the location and paste below
		// System is a Java class and setProperty is a method of System Class
		
		// 1st way, to show the location of chrome driver
		// This is an absolute path
		// System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tofael\\eclipse-workspace\\gov.cms.portal.may2024\\driver\\chromedriver.exe");
		
		// 2nd way, to show the location of the chrome driver
		// This is a dynamic way (relative path)
		// user.dir means --> System set the property to User Directory
		// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
		
		// 3rd and final way, to show the location of chrome driver
		// This is a dynamic path (relative path)
		// I will use this one, till end of the course
		
		// For Chrome Driver
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");		
		driver = new ChromeDriver();
		
		// For Firefox Driver
		// for Firefox, please download version 32
		// System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");		
		// driver = new FirefoxDriver();
		
		// For Edge Driver
		// System.setProperty("webdriver.edge.driver", "./driver/msedgedriver.exe");		
		// driver = new EdgeDriver();
		
		// We need to add the WebDriverManager dependency in the pom.xml file
		// When physical driver absent, or driver is not  working, because of version issue, then you can use WebDriverManager
		// WebDriverManager doesn't need any physical driver
		// From below line, the most updated version of chrome browser will be initialized, when no version is mentioned	
		
		// WebDriverManager.chromedriver().setup();
		// driver = new ChromeDriver();
		
		// WebDriverManager.firefoxdriver().setup();
		// driver = new FirefoxDriver();
		
		// WebDriverManager.edgedriver().setup();
		// driver = new EdgeDriver();
		
		// present version: 126.0.6478.127
		// stable version: 127.0.6533.72
		// older version: 125.0.6422.78 [used here]
		// older version sometimes used for stability of browser, sometime the request from the Authority
		// if you choose version, then it will use that specific version of driver
		// WebDriverManager.chromedriver().driverVersion("125.0.6422.78").setup();
		// driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://portal.cms.gov/portal/");
		// We can also use fullscreen() instead of maximize() method
		// driver.manage().window().fullscreen();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		// How to test implicitlyWait()?
		// by giving wrong web element
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		homePage = new HomePage(driver);
	}
		
	@AfterMethod
	public void tearUp() {
		driver.quit();
	}

}
