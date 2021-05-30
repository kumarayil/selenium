package com.automationproject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistration extends BasePackage.BaseClass{	
	private String firstname, lastname, validemail, password,
		datev, monthv, yearv, comp, add1, add2, city, zip, vstate,
		mobile1, mobile2,info;
	public String[] email = {"joko","joko.com","joko[at]joko[dot]com","","joko@joko.com"};
	public Select date, month, year, state;
	
	public UserRegistration(){
		this.firstname = "Paimin";
		this.lastname = "Pulgoso";
		this.validemail = "paimin.pulgoso@gmail.com";
		this.password = "paimin123";
		this.datev = "16";
		this.monthv ="12";
		this.yearv = "1991";
		this.comp = "Yello Company";
		this.add1 = "Jalan Pelangi";
		this.add2 = "Jalan Jalan Sore";
		this.city = "Jombang";
		this.zip = "90876";
		this.vstate = "5";
		this.mobile1 = "081789567328";
		this.mobile2 = "081303098721";
		this.info = "hello hello";
	}
	
	@Test
	public void openAuthenticationPage(){
		//Open the authentication page contain sign in and sign up form
		driver.findElement(By.className("login")).click();
		
		//verify the title of authentication page
		expected = "Login - My Store";
		actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}	
	
	@Test
	public void regUsingInvalidEmailFormat(){
		//Testing the email form validation
		for(int i=0;i<email.length;i++){
			driver.findElement(By.id("email_create")).sendKeys(email[i]);
			driver.findElement(By.id("SubmitCreate")).submit();
			
			//displaying the error message
			if(driver.findElement(By.xpath("//*[@id='create_account_error']/ol/li")).isDisplayed()){
				System.out.println("email: "+email[i]+" | Error: "+driver.findElement(By.xpath("//*[@id='create_account_error']/ol/li")).getText());
			}
			driver.findElement(By.id("email_create")).clear();
		}
	}
	@Test
	public void regUsingValidEmail(){
		//register using valid email address
		driver.findElement(By.id("email_create")).sendKeys(validemail);
		driver.findElement(By.id("SubmitCreate")).submit();		
	}
	
	@Test
	public void regUsingValidData(){		
		date = new Select(driver.findElement(By.id("days")));
		month = new Select(driver.findElement(By.id("months")));
		year = new Select(driver.findElement(By.id("years")));
		state = new Select(driver.findElement(By.id("id_state")));
		
		//fill the fields on the registration form using valid data
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).sendKeys(firstname);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastname);
		Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), validemail);
		driver.findElement(By.id("passwd")).sendKeys(password);
		date.selectByValue(datev);
		month.selectByValue(monthv);
		year.selectByValue(yearv);
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		driver.findElement(By.id("company")).sendKeys(comp);
		driver.findElement(By.id("address1")).sendKeys(add1);
		driver.findElement(By.id("address2")).sendKeys(add2);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("postcode")).sendKeys(zip);
		state.selectByValue(vstate);
		driver.findElement(By.id("other")).sendKeys(info);
		driver.findElement(By.id("phone")).sendKeys(mobile1);
		driver.findElement(By.id("phone_mobile")).sendKeys(mobile2);
		
		//submit the registration form
		driver.findElement(By.id("submitAccount")).click();	
	}
	
	@Test
	public void registerWithBlankInput(){
		state = new Select(driver.findElement(By.id("id_state")));
		
		//fill the fields on the registration form using invalid data
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).clear();
		driver.findElement(By.id("customer_lastname")).clear();
		//driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("passwd")).clear();
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		driver.findElement(By.id("firstname")).clear();
		driver.findElement(By.id("lastname")).clear();
		driver.findElement(By.id("company")).clear();
		driver.findElement(By.id("address1")).clear();
		//driver.findElement(By.id("address2")).clear()
		driver.findElement(By.id("city")).clear();
		driver.findElement(By.id("postcode")).clear();
		state.selectByValue("");
		driver.findElement(By.id("other")).clear();
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone_mobile")).clear();
		
		//submit the registration form and display the error message
		driver.findElement(By.id("submitAccount")).click();
		if(driver.findElement(By.xpath("//*[@id='center_column']/div")).isDisplayed()){
			System.out.println("validation error: "+driver.findElement(By.xpath("//*[@id='center_column']/div")).getText());
		}
	}	
	
	//@AfterTest
	public void backToHomepage(){
		driver.navigate().to(baseUrl);
	}
}