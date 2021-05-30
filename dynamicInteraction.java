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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.common.io.Files;
public class dynamicInteraction {
	
	public static WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./BrowserUtils/chromedriver.exe");
		driver = new ChromeDriver();  	
	}
	
	@Test
	public void browsePage() {
		driver.get("https://the-internet.herokuapp.com/dynamic_controls");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(160, TimeUnit.SECONDS);
		
		
		//select the checkbox and click on remove button
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//button[normalize-space()='Remove']")).click();

		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//click on Enable button 
		driver.findElement(By.xpath("//button[normalize-space()='Enable']")).click();
		
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TakesScreenshot ts = (TakesScreenshot)driver;

		//capture screenshot as output type FILE
		File file = ts.getScreenshotAs(OutputType.FILE);

		try {
			//save the screenshot taken in destination path
			Files.copy(file, new File("./ScreenShot_Folder/dynamic_interaction_success.png"));
		} catch (IOException err) {
			err.printStackTrace();
		}
		
		System.out.println("the login page screenshot is taken");
}
		

	@AfterClass
	public void cleanUp() {
	driver.quit();
	}		
}