package com.automationproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class signinClass {
	
	@Test
	public static void signIn()
	{
	System.setProperty("webdriver.chrome.driver", "./BrowserUtils/chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://automationpractice.com/index.php");	
	
	//click on Sign in link
	driver.findElement(By.linkText("Sign in")).click();
	
	//Enter registered user credentials
	driver.findElement(By.id("email")).sendKeys("kumarayil@gmail.com");
	driver.findElement(By.id("passwd")).sendKeys("QAZwsx#rt67");
	driver.findElement(By.id("SubmitLogin")).click();		
	System.out.println("Sign In completed");
	
	driver.findElement(By.xpath("//body/div[@id='page']/div[@class='header-container']/header[@id='header']/div/div[@class='container']/div[@class='row']/div[@id='block_top_menu']/ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[3]/a[1]")).click();
	//driver.findElement(By.id("selectProductSort")).sendKeys("Price: Highest first");
	driver.findElement(By.id("selectProductSort")).sendKeys("Reference: Lowest first");
	System.out.println("Test");
	
	//pending
	//driver.findElement(By.xpath("//span[normalize-space()='Add to cart']")).click();
	//driver.findElement(By.linkText("Add to cart")).click();
	//driver.quit();
	
}
}