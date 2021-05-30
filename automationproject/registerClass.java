package com.automationproject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class registerClass {

		@Test 
		public static void registerUser()
		{
		System.setProperty("webdriver.chrome.driver", "./BrowserUtils/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");	
		
				//click on Sign in link
				driver.findElement(By.linkText("Sign in")).click();

				//enter email id and click on create account button
				driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("myid@gmail.com");
				driver.findElement(By.xpath("//span[normalize-space()='Create an account']")).click();
				
				//enter the registration details and press register button		
				driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
				
				WebElement radiomale = driver.findElement(By.id("uniform-id_gender1"));
			    radiomale.click();
				driver.findElement(By.id("customer_firstname")).sendKeys("Dileep");	
				driver.findElement(By.id("customer_lastname")).sendKeys("Kumar");
				driver.findElement(By.id("passwd")).sendKeys("QAZWSXEDC#1234");
				driver.findElement(By.name("days")).sendKeys("12");
				driver.findElement(By.name("months")).sendKeys("December");
				driver.findElement(By.name("years")).sendKeys("1974");
				driver.findElement(By.id("company")).sendKeys("MAFIL");
				driver.findElement(By.id("address1")).sendKeys("My Home, my place - 6890");
				driver.findElement(By.id("city")).sendKeys("My City");
				driver.findElement(By.id("id_state")).sendKeys("Alabama");
				driver.findElement(By.id("postcode")).sendKeys("682305");
				driver.findElement(By.id("id_country")).sendKeys("India");
				driver.findElement(By.id("phone_mobile")).sendKeys("9999999999");
				driver.findElement(By.id("phone")).sendKeys("9999999999");
				driver.findElement(By.id("alias")).sendKeys("my address");
				
				driver.findElement(By.xpath("//span[normalize-space()='Register']")).click();
				
				//close the browser
				driver.close();
		
				System.out.println("End of Registration");
}
}