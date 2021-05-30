package apparelStore;

import java.util.*;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Registration {
	
	
	 static WebDriver driver;
	 static String appUrl;
	 
	 String passb = "<br><b style=\"background-color: #2dfd26eb;color: #0300a9;\">Test Passed</b>";
	 String failb = "<br><b style=\"background-color: #ef2a1e;color: #e9f3f3;\">Test Failed </b>";
	 public static boolean isClickable(WebElement webe)  {
	      try {
	          WebDriverWait wait = new WebDriverWait(driver, 15);  //seconds
	          wait.until(ExpectedConditions.elementToBeClickable(webe));
	          return true;
	      }
	      catch (Exception e) {
	          return false;
	      }
	  }
	 
	 
	
	 
	 @Test(priority =1,enabled = true)
	  public void Setup_For_Registration_Page() throws IOException {    
		  
		 try {
			 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
			 driver = new ChromeDriver();
			 appUrl="http://automationpractice.com/index.php";
			 
			 Reporter.log(passb+"<b> >> Browser Initialized Sucessfully of Registration Module</b>");
		 
		 }
		 catch(Exception e) {
			 Reporter.log(failb+"<b> >> Browser Initialization Failed. Please verify the chrome driver file available in project location of Registration Module</b>");
		 }

	  }
	 
	 @Test(priority =2,enabled = true)
	    public void Test_Initialize_For_Registration_Page() throws InterruptedException,  IOException{ 
	    	try {
		        
			    driver.get(appUrl);
		        Thread.sleep(1000);
		        driver.manage().window().maximize();
		        Reporter.log(passb+"<b> >> Application Getting Launched and Maximized successfully in Registration Module</b>");
	    	}
	    	catch (Exception e) {
	    		Reporter.log(failb+"<b> >> Application Not Getting Launched for the following error in Registration Module </b>");
	    		Reporter.log("<br> "+e);
	    	}
	    }
	 
	 @Test(priority =3,enabled = true)
	    public void TestCases_in_Registration_Page() throws InterruptedException,  IOException{ 
	    	try {
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
			    WebElement signInButton = driver.findElement(By.linkText("Sign in"));
			    Thread.sleep(500);
			    boolean bst = Registration.isClickable(signInButton);
			    if(bst) {
			    	Reporter.log(passb+"<b> >> Sign-In Button is clickable in Home page </b>");
			    	signInButton.click();
			    	Thread.sleep(3000);
			    	
			    	//Verifying the page redirection and page title
			    	if(driver.getTitle().equals("Login - My Store")) {
			    		Reporter.log(passb+"<b> >> Page has been redirected to Login screen </b>");
			    	}
			    	else {
				    	Reporter.log(failb+"<b> >> Page has not been redirected to Login screen </b>");

			    	}
			    	testData.main(null);
			    	WebElement emailIdField = driver.findElement(By.id("email_create"));
			    	Thread.sleep(1000);
			    	
			    	emailIdField.clear();
			    	Thread.sleep(500);
			    	emailIdField.sendKeys(testData.alreadyUserEmailId);
			    	Thread.sleep(1000);
			    	String exceptedEmail =driver.findElement(By.id("email_create")).getAttribute("value");
			    	
			    	WebElement createAccountButton = driver.findElement(By.id("SubmitCreate"));
			    	createAccountButton.click();
			    	Thread.sleep(8000);
			    	
			    	if(driver.findElement(By.xpath("//*[@id=\"create_account_error\"]/ol/li")).getText().contains("email address has already been registered.")) {
			    		Reporter.log(passb+"<b> >> Verfication of already registered email Id is passed</b>");
			    	}
			    	else {
				    	Reporter.log(failb+"<b> >> Verfication of already registered email Id is failed </b>");

			    	}
			    		
			    	js.executeScript("window.scrollBy(0,300)");
			    	emailIdField.clear();
			    	emailIdField.sendKeys(testData.newUserEmailId);
			    	Thread.sleep(1000);
			    	createAccountButton = driver.findElement(By.id("SubmitCreate"));
			    	createAccountButton.click();
			    	Thread.sleep(8000);
			    	
			    	if(driver.findElement(By.xpath("//*[@id=\"noSlide\"]/h1")).getText().contains("CREATE AN ACCOUNT")) {
			    		Reporter.log(passb+"<b> >> Page has been redirected to account creation screen</b>");
			    		
			    		//Select the Mr
			    		WebElement MrMrs = driver.findElement(By.id("id_gender1"));
			    		
			    			
			    			MrMrs.click();
			    			Thread.sleep(1000);
			    			if(MrMrs.isSelected()) {
			    				Reporter.log(passb+"<b> >> Radio button Mr. is selected </b>");	
			    			}
			    			else {
			    				Reporter.log(failb+"<b> >> Radio button Mr. is not selected when click on </b>");	
			    			}
			    			
			    		
			    			
			    			
		
			    		//First name
			    		WebElement firstName = driver.findElement(By.id("customer_firstname"));
			    		firstName.clear();
			    		Thread.sleep(1000);
			    		firstName.sendKeys(testData.firstName);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the First Name Field </b>");
			    		
			    		//Last name
			    		WebElement lastName = driver.findElement(By.id("customer_lastname"));
			    		lastName.clear();
			    		Thread.sleep(1000);
			    		lastName.sendKeys(testData.lastName);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the Last Name Field </b>");
			    		
			    		//Email_ID
			    		WebElement emailId = driver.findElement(By.id("email"));
			    		if(emailId.getAttribute("value").equals(testData.newUserEmailId)){
			    			Thread.sleep(500);
			    			Reporter.log(passb+"<b> >> Email ID is displayed correctly from previous page </b>");
				    	}
			    		else {
			    			Reporter.log(failb+"<b> >> Email ID is not displayed correctly from previous page </b>");
			    			emailId.clear();
			    			emailId.sendKeys(testData.newUserEmailId);
			    			Reporter.log(passb+"<b> >> Email ID is Retrieved from Excel sheet for new user </b>");
			    		}
			    		//password
			    		js.executeScript("window.scrollBy(0,300)");
			    		WebElement pwd = driver.findElement(By.id("passwd"));
			    		pwd.clear();
			    		Thread.sleep(1000);
			    		pwd.sendKeys(testData.password);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the Password Field </b>");
			    		
			    		//DOB - Days
			    		Select day = new Select(driver.findElement(By.id("days")));
			    		day.selectByValue("7");
			    		Thread.sleep(500);
			    		Reporter.log(passb+"<b> >> Passing Value to the DOB - Days Field </b>");
			    		
			    		//DOB - Months
			    		Select months = new Select(driver.findElement(By.id("months")));
			    		months.selectByValue("5");
			    		Thread.sleep(500);
			    		Reporter.log(passb+"<b> >> Passing Value to the DOB - Months Field </b>");
			    		
			    		//DOB - Year
			    		Select years = new Select(driver.findElement(By.id("years")));
			    		years.selectByValue("2009");
			    		Thread.sleep(500);
			    		Reporter.log(passb+"<b> >> Passing Value to the DOB - Years Field </b>");
			    		
			    		//Address First name
			    		WebElement addFirstName = driver.findElement(By.id("firstname"));
			    		addFirstName.clear();
			    		addFirstName.sendKeys(testData.firstName);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the Address First Name Field </b>");
			    		
			    		//Address Last name
			    		WebElement addLastName = driver.findElement(By.id("lastname"));
			    		addLastName.clear();
			    		addLastName.sendKeys(testData.lastName);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the Address Last Name Field </b>");
			    		
			    		//Company Name
			    		js.executeScript("window.scrollBy(0,300)");
			    		WebElement companyName = driver.findElement(By.id("company"));
			    		companyName.clear();
			    		companyName.sendKeys(testData.companyName);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the company Name Field </b>");
			    		
			    		//Address 1
			    		WebElement addressOne = driver.findElement(By.id("address1"));
			    		addressOne.clear();
			    		addressOne.sendKeys(testData.address1);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the Address 1 Field </b>");
			    		
			    		//Address 2
			    		WebElement addressTwo = driver.findElement(By.id("address2"));
			    		addressTwo.clear();
			    		addressTwo.sendKeys(testData.address2);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the Address 2 Field </b>");
			    		
			    		//City Name
			    		WebElement cityName = driver.findElement(By.id("city"));
			    		cityName.clear();
			    		cityName.sendKeys(testData.cityName);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the City Name Field </b>");
			    		

			    		//Country unselect
			    		Select country = new Select(driver.findElement(By.id("id_country")));
			    		country.selectByVisibleText("-");
			    		Reporter.log(passb+"<b> >> Unselect the country to check the validation message</b>");
			    		Thread.sleep(2000);
			    		
			    		//submit button
			    		js.executeScript("window.scrollBy(0,500)");
			    		WebElement submitButton = driver.findElement(By.id("submitAccount"));
			    		bst = isClickable(submitButton);
			    		if(bst) {
			    			Reporter.log(passb+"<b> >> Register button is clickable</b>");
			    			submitButton.click();
			    		}
			    		else {
				    		Reporter.log(failb+"<b> >> Register button is not clickable</b>");

			    		}
			    		Thread.sleep(5000);
			    		if(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/ol/li[4]")).getText().contains("Country is invalid")) {
				    		Reporter.log(passb+"<b> >> Validation Message is displayed correctly for invalid selection of country</b>");
			    		}
			    		else {
				    		Reporter.log(failb+"<b> >> Validation Message is not displayed correctly for invalid selection of country</b>");

			    		}
			    		
			    		//password again
			    		js.executeScript("window.scrollBy(0,300)");
			    		pwd = driver.findElement(By.id("passwd"));
			    		pwd.clear();
			    		pwd.sendKeys(testData.password);
			    		Thread.sleep(1000);
			    		
			    		//Again select the country
			    		js.executeScript("window.scrollBy(0,800)");
			    		Thread.sleep(1000);
			    		country = new Select(driver.findElement(By.id("id_country")));
			    		country.selectByVisibleText("United States");
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Again selected the valid country </b>");
			    			
			    		//Select the state
			    		js.executeScript("window.scrollBy(0,400)");
			    		Select state = new Select(driver.findElement(By.id("id_state")));
			    		state.selectByValue("5");
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Again selected the valid country </b>");
				    	
			    		//Postal code
			    		WebElement zipcode = driver.findElement(By.id("postcode"));
			    		zipcode.clear();
			    		zipcode.sendKeys(testData.invZip);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Four characters to the Zip / Postal Code Field </b>");
			    		
			    		
			    		//submit button
			    		js.executeScript("window.scrollBy(0,500)");
			    		submitButton = driver.findElement(By.id("submitAccount"));
			    		bst = isClickable(submitButton);
			    		if(bst) {
			    			Reporter.log(passb+"<b> >> Register button is clickable after passing invalid postal code</b>");
			    			submitButton.click();
			    		}
			    		else {
				    		Reporter.log(failb+"<b> >> Register button is not clickable after passing invalid postal code</b>");

			    		}
			    		
			    		Thread.sleep(5000);
			    		if(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/ol/li[2]")).getText().contains("Zip/Postal code you've entered is invalid.")) {
				    		Reporter.log(passb+"<b> >> Validation Message is displayed correctly for invalid Zip / Postal code</b>");
			    		}
			    		else {
				    		Reporter.log(failb+"<b> >> Validation Message is not displayed correctly for invalid Zip / Postal code</b>");

			    		}
			    		//password again
			    		js.executeScript("window.scrollBy(0,300)");
			    		pwd = driver.findElement(By.id("passwd"));
			    		pwd.clear();
			    		pwd.sendKeys(testData.password);
			    		Thread.sleep(1000);
			    		
			    		//home phone
			    		js.executeScript("window.scrollBy(0,1000)");
			    		WebElement homePhone = driver.findElement(By.id("phone"));
			    		homePhone.clear();
			    		homePhone.sendKeys(testData.homePhone);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the Home Phone Field </b>");
			    		
			    		//Mobile phone
			    		//js.executeScript("window.scrollBy(0,200)");
			    		WebElement mbPhone = driver.findElement(By.id("phone_mobile"));
			    		mbPhone.clear();
			    		mbPhone.sendKeys(testData.mobilePhone);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the Mobile Phone Field </b>");

			    		zipcode = driver.findElement(By.id("postcode"));
			    		zipcode.clear();
			    		zipcode.sendKeys(testData.zipcode);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing correct values to the Zip / Postal Code Field </b>");
			    		
			    		//Additional info
			    		
			    		WebElement addInfo = driver.findElement(By.id("other"));
			    		addInfo.clear();
			    		addInfo.sendKeys(testData.additionalInfo);
			    		Thread.sleep(1000);
			    		Reporter.log(passb+"<b> >> Passing Value to the Additional Information Field </b>");
			    		
			    		//submit button
			    		
			    		submitButton = driver.findElement(By.id("submitAccount"));
			    		bst = isClickable(submitButton);
			    		if(bst) {
			    			Reporter.log(passb+"<b> >> Register button is clickable after passing all correct values</b>");
			    			submitButton.click();
			    			Thread.sleep(4000);
			    			
			    			
			    			//Redirect to My account page
			    			if(driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1")).getText().contains("MY ACCOUNT")) {
					    		Reporter.log(passb+"<b> >> Page has been redirected to my account screen</b>");
					    		Thread.sleep(2000);
					    		//sign out
					    		WebElement signout = driver.findElement(By.linkText("Sign out"));
					    		signout.click();
					    		Thread.sleep(2000);
					    		
					    		if(driver.getTitle().equals("Login - My Store")) {
						    		Reporter.log(passb+"<b> >> Page has been redirected to Login screen </b>");
						    	}
						    	else {
							    	Reporter.log(failb+"<b> >> Page has not been redirected to Login screen </b>");

						    	}
			    			}
			    			else {
			    				Reporter.log(failb+"<b> >> Page has not been redirected to my account screen</b>");
			    			}
			    		}
			    		else {
				    		Reporter.log(failb+"<b> >> Register button is not clickable after passing all correct values</b>");

			    		}
			    		
			    		
			    	}
			    	else {
				    	Reporter.log(failb+"<b> >> Page has not been redirected to account creation screen </b>");

			    	}
			    	
			    	
			    }
			    else {
			    	Reporter.log(passb+"<b> Sign-In Button is not clickable in Home page </b>");
			    }
			  
		        
	    	}
	    	catch (Exception e) {
	    		Reporter.log(passb+"<b> >> Following error has been occurred in Registration Module </b>");
	    		Reporter.log("<br> "+e);
	    	}
	    }
	 
	 
	 
	 @AfterSuite 
	    protected void afterSuite() throws IOException, InterruptedException
	    { 
		 //driver.close();
		 	//Make sure place correctly your report path.
	    	driver.get(System.getProperty("user.dir") + "/test-output/index.html"); 
	    	
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.linkText("Reporter output")).click();
	    	 	
	    	
	    }
	 

}
