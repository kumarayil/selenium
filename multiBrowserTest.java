/* 
DevOps assignment - Assignment 2 - Test automation using Selenium and Java
Submitted by
Dileep Kumar M | 2020mt93744@wilp.bits-pilani.ac.in,
Sanu V Mony | 2020mt93710@wilp.bits-pilani.ac.in, 
Joby Jose | 2020MT93754@wilp.bits-pilani.ac.in, 
Hridya N S | 2020mt93709@wilp.bits-pilani.ac.in,
Priyanka K P | 2020mt93735@wilp.bits-pilani.ac.in 
*/

package com.devopsassignment;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.google.common.io.Files;

public class multiBrowserTest {
	
	public static WebDriver driver;
		
	@BeforeTest
	@Parameters("browser")
	
	public void setup(String browser) throws Exception{
		//Check if parameter passed from TestNG is 'firefox'
		if(browser.equalsIgnoreCase("firefox")){
		//create firefox instance
			System.setProperty("webdriver.gecko.driver", "./BrowserUtils/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver","./BrowserUtils/chromedriver.exe");
			//create chrome instance
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@Test
	public  static void UsingFirefoxBrowser()
	{
		driver.get("https://the-internet.herokuapp.com/login");		
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.className("radius")).click();
	
	
	TakesScreenshot ts = (TakesScreenshot)driver;
	//capture screenshot as output type FILE
	File file = ts.getScreenshotAs(OutputType.FILE);

	try {
		//save the screenshot taken in destination path
		Files.copy(file, new File("./ScreenShot_Folder/website"+Math.random()+ ".png"));
	} catch (IOException err) {
		err.printStackTrace();
	}	
	System.out.println("the screenshot is taken");	
	}	

	@AfterClass
	public void browserCleanUp() {
	driver.quit();
	}	

}