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

public class Login {
	
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
	 
	 
	
	 
	 @Test(priority =4,enabled = true)
	  public void Setup_For_Login_Page() throws IOException {    
		  
		 try {
			 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
			 driver = new ChromeDriver();
			 
			 appUrl="http://automationpractice.com/index.php";
			 
			 Reporter.log(passb+"<b> >> Browser Initialized Sucessfully of Login Module </b>");
		 
		 }
		 catch(Exception e) {
			 Reporter.log(failb+"<b> >> Browser Initialization Failed. Please verify the chrome driver file available in project location of Login Module </b>");
		 }

	  }
	 
	 
	 @Test(priority =5,enabled = true)
	    public void Test_Initialize_For_Login_Page() throws InterruptedException,  IOException{ 
	    	try {
		        
			    driver.get(appUrl);
		        driver.manage().window().maximize();
		        Reporter.log(passb+"<b> >> Application Getting Launched and Maximized successfully of Login Module </b>");
	    	}
	    	catch (Exception e) {
	    		Reporter.log(failb+"<b> >> Application Not Getting Launched for the following error of Login Module </b>");
	    		Reporter.log("<br> "+e);
	    	}
	    }
	 
	 @Test(priority =6,enabled = true)
	    public void TestCases_For_Login_Page() throws InterruptedException,  IOException{ 
	    	try {
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
			    WebElement signInButton = driver.findElement(By.linkText("Sign in"));
			    Thread.sleep(500);
			    boolean bst = Login.isClickable(signInButton);
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
			    	//user name
			    	WebElement userName = driver.findElement(By.id("email"));
			    	
			    	userName.clear();
			    	Thread.sleep(500);
			    	userName.sendKeys(testData.invalidUserName1);
			    	Thread.sleep(1000);
			    	
			    	//password
			    	WebElement passwd = driver.findElement(By.id("passwd"));
			    	passwd.clear();
			    	Thread.sleep(500);
			    	passwd.sendKeys(testData.invalidPassword1);
			    	Thread.sleep(1000);
			    	
			    	WebElement signInBut = driver.findElement(By.id("SubmitLogin"));
			    	bst = isClickable(signInBut);
			    	if(bst)	{
			    		Reporter.log(passb+"<b> >> Sign in button is clickable when we give wrong password</b>");
			    		signInBut.click();
				    	Thread.sleep(3000);
				    	
				    	if(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText().contains("Invalid")) {
				    		Reporter.log(passb+"<b> >> Verfication of Invalid credentials is passed when we give wrong password</b>");
				    	}
				    	else {
					    	Reporter.log(failb+"<b> >> Verfication of Invalid credentials is failed when we give wrong password </b>");

				    	}
			    	}
			    	else {
			    		Reporter.log(failb+"<b> >> Sign in button is not clickable when we give wrong password</b>");
			    		
			    	}
			    	
			    		
			    	js.executeScript("window.scrollBy(0,300)");
			    	
			    	//Second Invalid Credential
			    	//user name
			    	userName = driver.findElement(By.id("email"));
			    	userName.clear();
			    	Thread.sleep(500);
			    	userName.sendKeys(testData.invalidUserName2);
			    	Thread.sleep(1000);
			    	
			    	//password
			    	passwd = driver.findElement(By.id("passwd"));
			    	passwd.clear();
			    	passwd.sendKeys(testData.invalidPassword2);
			    	Thread.sleep(1000);
			    	
			    	signInBut = driver.findElement(By.id("SubmitLogin"));
			    	bst = isClickable(signInBut);
			    	if(bst)	{
			    		Reporter.log(passb+"<b> >> Sign in button is clickable when we give wrong email and password</b>");
			    		signInBut.click();
				    	Thread.sleep(3000);
				    	
				    	if(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText().contains("failed")) {
				    		Reporter.log(passb+"<b> >> Verfication of Invalid credentials is passed when we give wrong email and password</b>");
				    	}
				    	else {
					    	Reporter.log(failb+"<b> >> Verfication of Invalid credentials is failed when we give wrong email and password </b>");

				    	}
			    	}
			    	else {
			    		Reporter.log(failb+"<b> >> Sign in button is not clickable when we give wrong email and password</b>");
			    		
			    	}
			    	
			    	
			    	// valid Credential
			    	js.executeScript("window.scrollBy(0,300)");
			    	
			    	
			    	//user name
			    	userName = driver.findElement(By.id("email"));
			    	userName.clear();
			    	userName.sendKeys(testData.newUserEmailId);
			    	Thread.sleep(1000);
			    	
			    	//password
			    	passwd = driver.findElement(By.id("passwd"));
			    	passwd.clear();
			    	passwd.sendKeys(testData.password);
			    	Thread.sleep(1000);
			    	
			    	signInBut = driver.findElement(By.id("SubmitLogin"));
			    	bst = isClickable(signInBut);
			    	if(bst)	{
			    		Reporter.log(passb+"<b> >> Sign in button is clickable when we give correct credential</b>");
			    		signInBut.click();
				    	Thread.sleep(2000);
				    	
				    	if(driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1")).getText().contains("MY ACCOUNT")) {
				    		Reporter.log(passb+"<b> >> Page has been redirected to My Account</b>");
				    		Thread.sleep(2000);
				    		
				    		//Order details
				    		driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a/span")).click();
				    		Thread.sleep(5000);
				    		
				    		//MY account - breadcrumb
				    		driver.findElement(By.xpath("//*[@id=\"columns\"]/div[1]/a[2]")).click();
				    		Thread.sleep(5000);
				    		
				    		//MY Whishlist
				    		driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[2]/ul/li/a/span")).click();
				    		Thread.sleep(5000);
				    		js.executeScript("window.scrollBy(0,100)");
				    		
				    		
				    		//MY account - breadcrumb
				    		driver.findElement(By.xpath("//*[@id=\"columns\"]/div[1]/a[2]")).click();
				    		Thread.sleep(5000);
				    		
				    		//MY creditslips
				    		driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[2]/a/span")).click();
				    		Thread.sleep(5000);
				    		js.executeScript("window.scrollBy(0,100)");
				    		//MY account - breadcrumb
				    		driver.findElement(By.xpath("//*[@id=\"columns\"]/div[1]/a[2]")).click();
				    		Thread.sleep(5000);
				    		
				    		//MY address
				    		driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[3]/a/span")).click();
				    		Thread.sleep(5000);
				    		js.executeScript("window.scrollBy(0,250)");
				    		//MY account - breadcrumb
				    		driver.findElement(By.xpath("//*[@id=\"columns\"]/div[1]/a[2]")).click();
				    		Thread.sleep(5000);
				    		
				    		//MY personal info
				    		driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[4]/a/span")).click();
				    		Thread.sleep(5000);
				    		js.executeScript("window.scrollBy(0,100)");
				    		Thread.sleep(1500);
				    		js.executeScript("window.scrollBy(0,100)");
				    		Thread.sleep(1500);
				    		js.executeScript("window.scrollBy(0,100)");
				    		Thread.sleep(1500);
				    		js.executeScript("window.scrollBy(0,-300)");
				    		Thread.sleep(1500);
				    		
				    		//MY account - breadcrumb
				    		driver.findElement(By.xpath("//*[@id=\"columns\"]/div[1]/a[2]")).click();
				    		Thread.sleep(5000);
				    		js.executeScript("window.scrollBy(0,0)");
				    		Thread.sleep(1000);
				    		WebElement signout = driver.findElement(By.linkText("Sign out"));
				    		bst = isClickable(signout);
				    		if(bst) {
				    			Reporter.log(passb+"<b> >> Sign out button is clickable in My Account Page</b>");
				    			signout.click();
				    			
				    			Thread.sleep(5000);
				    			if(driver.getTitle().contains("Login - My Store"))
				    			{
				    			
				    			Reporter.log(passb+"<b> >> Page has been redirected to Login page when sign out button is clicked</b>");
				    			}
				    			else {
				    			Reporter.log(failb+"<b> >> Page has not been redirected to Login page when sign out button is clicked</b>");
				    			}
				    		}
				    		else {
				    			
				    			Reporter.log(failb+"<b> >> Sign out button is not clickable in My Account page</b>");
				    			}
				    		
				    	}
				    	else {
					    	Reporter.log(failb+"<b> >> Page has not been redirected to My Account</b>");

				    	}
			    	}
			    	else {
			    		Reporter.log(failb+"<b> >> Sign in button is not clickable when we give correct credential</b>");
			    		
			    	}
			    
			    }
			    else {
			    	Reporter.log(passb+"<b> Sign-In Button is not clickable in Home page </b>");
			    }
			  
		        
	    	}
	    	catch (Exception e) {
	    		Reporter.log(passb+"<b> >> Following error has been occurred in Login page </b>");
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
