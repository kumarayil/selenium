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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class browserStackLogin {
	
	public String user_id="2020mt93744@wilp.bits-pilani.ac.in";
	public String pass_wd="QAZwsx@123";
	
	public static WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./BrowserUtils/chromedriver.exe");
		driver = new ChromeDriver();  
	}
	
	
	@Test
	public void browsePortalPage() {
                      
	//define the url
	driver.get("https://www.browserstack.com/users/sign_in");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	
	//get the title of the webpage
	String pageTitle = driver.getTitle();							 
	System.out.println("The title of this page before login is ===> " +pageTitle);
	Assert.assertEquals(pageTitle, "BrowserStack Login | Sign Into The Best Mobile & Browser Testing Tool");	
	
	}
	
	@Test
	public void loginPortal() {
		
		driver.findElement(By.id("accept-cookie-notification")).click();
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//clear the input field before entering any value and enter login credentials
		
	driver.findElement(By.id("user_email_login")).sendKeys(user_id);		
	driver.findElement(By.id("user_password")).sendKeys(pass_wd);
	
	
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//Click on login button
			driver.findElement(By.id("user_submit")).click();	
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

	String loginPageTitle = driver.getCurrentUrl();
	System.out.println(loginPageTitle);
	if(loginPageTitle.contains("dashboard"))		{
			System.out.println("The title of this page after login is ===> " +loginPageTitle);
			TakesScreenshot ts = (TakesScreenshot)driver;

			//capture screenshot as output type FILE
			File file = ts.getScreenshotAs(OutputType.FILE);

			try {
				//save the screenshot taken in destination path
				Files.copy(file, new File("./ScreenShot_Folder/portal_login_success.png"));
			} catch (IOException err) {
				err.printStackTrace();
			}
			
			System.out.println("the login page screenshot is taken");
	}
			else {
			System.out.println("Page title error");
			}
}
	
	@AfterClass
	public void cleanUp() {
	driver.quit();
	}		
}