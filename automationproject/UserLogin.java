package com.automationproject;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class UserLogin extends BasePackage.BaseClass{
	public String[] email = {"joko","joko.com","","joko[at]joko[dot]com"};
	public String unregisteredEmail = "lalayeyeye@outlook.com";
	public String registeredEmail = "rianamahliadewi@gmail.com";
	public String password = "lalayeyeye";
	public String invalidPassword = "lailayeye";	
	
	@Test(priority=0)
	public void loginUsingInvalidEmailFormat(){	
		//navigate to user authentication page
		driver.findElement(By.className("login")).click();
		
		//fill the password field
		driver.findElement(By.id("passwd")).sendKeys(password);
		
		//try to fill the email field using invalid email format
		//submit the form and display the error message
		for(int i=0; i<email.length; i++){
			driver.findElement(By.id("email")).sendKeys(email[i]);
			driver.findElement(By.id("SubmitLogin")).click();
			if(driver.findElement(By.xpath("//*[@class='alert alert-danger']/ol/li")).isDisplayed()){
				System.out.println("email: "+email[i]+" | Error: "+driver.findElement(By.xpath("//*[@class='alert alert-danger']/ol/li")).getText());
			}
			driver.findElement(By.id("email")).clear();
		}
		driver.findElement(By.id("passwd")).clear();
		
	}
	@Test(priority=1)
	public void loginUsingUnregisteredEmail(){	
		//navigate to user authentication page
		driver.findElement(By.className("login")).click();
		
		//fill the password field
		driver.findElement(By.id("passwd")).sendKeys(password);
		
		//fill the email field using unregistered email
		driver.findElement(By.id("email")).sendKeys(unregisteredEmail);
		
		//submit the form and display the error message
		driver.findElement(By.id("SubmitLogin")).click();	
		if(driver.findElement(By.xpath("//*[@class='alert alert-danger']/ol/li")).isDisplayed()){
			System.out.println("Error: "+driver.findElement(By.xpath("//*[@class='alert alert-danger']/ol/li")).getText());
		}
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("passwd")).clear();
		
	}
	@Test(priority=2)
	public void loginUsingInvalidPassword(){
		//navigate to user authentication page
		driver.findElement(By.className("login")).click();
		
		//fill the password field
		driver.findElement(By.id("passwd")).sendKeys(invalidPassword);
		
		//fill the email field using registered email
		driver.findElement(By.id("email")).sendKeys(registeredEmail);
		
		//submit the form and display the error message
		driver.findElement(By.id("SubmitLogin")).click();
		if(driver.findElement(By.xpath("//*[@class='alert alert-danger']/ol/li")).isDisplayed()){
			System.out.println("Error: "+driver.findElement(By.xpath("//*[@class='alert alert-danger']/ol/li")).getText());
		}
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("passwd")).clear();		
	}
	@Test(priority=3)
	public void resetPassword(){
		//navigate to user authentication page
		driver.findElement(By.className("login")).click();
		
		//reset password
		driver.findElement(By.linkText("Forgot your password?")).click();
		driver.findElement(By.id("email")).sendKeys(registeredEmail);
		driver.findElement(By.id("form_forgotpassword")).submit();		
	}
	@Test(priority=4)
	public void loginUsingValidData(){	
		//navigate to user authentication page
		driver.findElement(By.className("login")).click();
		
		//login using valid data
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.id("email")).sendKeys(registeredEmail);
		driver.findElement(By.id("SubmitLogin")).click();		
	}
	
}
