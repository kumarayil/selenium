package BasePackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;

public class BaseClass {
	public String baseUrl = "http://automationpractice.com";
	//public String path = r'./BrowserUtils/chromedriver.exe';
	public WebDriver driver=new ChromeDriver(///D://GitHubProject//seleniumProject_v1//BrowserUtils);
	//public WebDriver driver = new FirefoxDriver();
	public String expected = null;
	public String actual = null;	
	
	@BeforeTest
	public void launchBrowser() {
		//System.setProperty("webdriver.chrome.driver", "./BrowserUtils/chromedriver.exe");
		//WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		expected = "My Store";
		actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}

	//@AfterTest
	public void terminateBrowser() {
	driver.close();
		System.exit(0);
	}
}