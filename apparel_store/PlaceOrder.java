package apparelStore;

import java.util.*;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PlaceOrder {
	
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
	 
	 
	
	 
	 @Test(priority =10,enabled = true)
	  public void Setup_For_Place_Order_Page() throws IOException {    
		  
		 try {
			 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
			 
			 driver = new ChromeDriver();
			 appUrl="http://automationpractice.com/index.php";
			 
			 Reporter.log(passb+"<b> >> Browser Initialized Sucessfully of Place Order Module </b>");
		 
		 }
		 catch(Exception e) {
			 Reporter.log(failb+"<b> >> Browser Initialization Failed. Please verify the chrome driver file available in project location of Place Order Module </b>");
		 }

	  }
	 
	 @Test(priority =11,enabled = true)
	    public void Test_Initialize_For_Place_Order_Page() throws InterruptedException,  IOException{ 
	    	try {
		        
			    driver.get(appUrl);
		        Thread.sleep(1000);
		        driver.manage().window().maximize();
		        Reporter.log(passb+"<b> >> Application Getting Launched and Maximized successfully of Place Order Module </b>");
	    	}
	    	catch (Exception e) {
	    		Reporter.log(failb+"<b> >> Application Not Getting Launched for the following error of Place Order Module </b>");
	    		Reporter.log("<br> "+e);
	    	}
	    }
	 
	 @Test(priority =12,enabled = true)
	    public void TestCases_For_Place_Order_Page() throws InterruptedException,  IOException{ 
	    	try {
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		Actions action = new Actions(driver);
			    WebElement signInButton = driver.findElement(By.linkText("Sign in"));
			    Thread.sleep(500);
			    boolean bst = PlaceOrder.isClickable(signInButton);
			    if(bst) {
			    	Reporter.log(passb+"<b> >> Sign-In Button is clickable in Home page </b>");
			    	signInButton.click();
			    	Thread.sleep(1000);
			    	
			    	//Verifying the page redirection and page title
			    	if(driver.getTitle().equals("Login - My Store")) {
			    		Reporter.log(passb+"<b> >> Page has been redirected to Login screen </b>");
			    	}
			    	else {
				    	Reporter.log(failb+"<b> >> Page has not been redirected to Login screen </b>");

			    	}
			    	testData.main(null);
			    	
			    	// valid Credential
			    	js.executeScript("window.scrollBy(0,300)");
			    	//user name
			    	WebElement userName = driver.findElement(By.id("email"));
			    	userName.clear();
			    	userName.sendKeys(testData.newUserEmailId);
			    	Thread.sleep(1000);
			    	
			    	//password
			    	WebElement passwd = driver.findElement(By.id("passwd"));
			    	passwd.clear();
			    	passwd.sendKeys(testData.password);
			    	Thread.sleep(1000);
			    	
			    	WebElement signInBut = driver.findElement(By.id("SubmitLogin"));
			    	bst = isClickable(signInBut);
			    	if(bst)	{
			    		Reporter.log(passb+"<b> >> Sign in button is clickable when we give correct credential</b>");
			    		signInBut.click();
				    	Thread.sleep(1000);
				    	
				    	if(driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1")).getText().contains("MY ACCOUNT")) {
				    		Reporter.log(passb+"<b> >> Page has been redirected to My Account</b>");
				    		Thread.sleep(1000);
				    		
				    		
				    		//Search Field
				    		WebElement searchField = driver.findElement(By.id("search_query_top"));
				    		searchField.clear();
				    		searchField.sendKeys(testData.searchKeyWord);
				    		Thread.sleep(1000);
				    		
				    		//Search button
				    		WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button"));
				    		bst = isClickable(searchButton);
				    		if(bst) {
				    			Reporter.log(passb+"<b> >> Search button is clickable </b>");
				    			searchButton.click();
				    			Thread.sleep(3500);
				    			
				    			WebElement count = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span"));
				    			String resultCount = count.getText();
				    			
				    			if(resultCount.equals("0 results have been found.")) {
				    				Reporter.log("<b> >> Zero results found. please pass valid search keyword in the test data excel sheet </b>");
				    			}
				    			else {
				    				
				    				
				    				//Select manufacture
				    				WebElement fashManu = driver.findElement(By.xpath("//*[@id=\"manufacturers_block_left\"]/div/ul/li/a"));
				    				action.moveToElement(fashManu).build().perform();
				    				js.executeScript("window.scrollBy(0,100)");
				    				Thread.sleep(1000);
				    				Reporter.log(passb+"<b> >> Fashion Manufacture has been clicked successfully </b>");
				    				fashManu.click();
				    				Thread.sleep(3000);
				    				Reporter.log(passb+"<b> >> Search results for Fashion Manufacture has been displayed successfully </b>");
				    				
				    				//add to cart first item
				    				js.executeScript("window.scrollBy(0,300)");
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img"))).moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[1]/span"))).build().perform();
				    				driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[1]/span")).click();
				    				Thread.sleep(10000);
				    				Reporter.log(passb+"<b> >> First item has been added to cart </b>");
				    				
				    				//click on continue shopping
				    				driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")).click();
				    				Thread.sleep(1500);
				    				
				    				//add to cart second item
				    				
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[3]/div/div[1]/div/a[1]/img"))).moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[3]/div/div[2]/div[2]/a[1]/span"))).build().perform();
				    				driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[3]/div/div[2]/div[2]/a[1]/span")).click();
				    				Thread.sleep(10000);
				    				Reporter.log(passb+"<b> >> Second item has been added to cart </b>");
				    				
				    				//click on continue shopping
				    				driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span")).click();
				    				Thread.sleep(1500);
				    				
				    				//add to cart third item
				    				js.executeScript("window.scrollBy(0,500)");
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[5]/div/div[1]/div/a[1]/img"))).moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[5]/div/div[2]/div[2]/a[1]/span"))).build().perform();
				    				driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[5]/div/div[2]/div[2]/a[1]/span")).click();
				    				Thread.sleep(15000);
				    				Reporter.log(passb+"<b> >> Third item has been added to cart </b>");
				    				
				    				//click on proceed to checkout
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"))).build().perform();
				    				js.executeScript("window.scrollBy(0,100)");
				    				driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
				    				Thread.sleep(7500);
				    				Reporter.log(passb+"<b> >> Clicked on Proceed to check out </b>");
				    				//click on proceed to checkout in summary tab
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]"))).build().perform();
				    				js.executeScript("window.scrollBy(0,100)");
				    				driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).click();
				    				Thread.sleep(7500);
				    				Reporter.log(passb+"<b> >> Clicked on Proceed to check out in summary tab </b>");
				    				//click on proceed to checkout in address
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button"))).build().perform();
				    				js.executeScript("window.scrollBy(0,100)");
				    				driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button")).click();
				    				Thread.sleep(7500);
				    				Reporter.log(passb+"<b> >> Clicked on Proceed to check out in Address tab </b>");
				    				//click on agree and terms in shipping
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"cgv\"]"))).build().perform();
				    				js.executeScript("window.scrollBy(0,100)");
				    				driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();
				    				Thread.sleep(1000);
				    				Reporter.log(passb+"<b> >> Clicked on Agree and terms </b>");
				    				//click on proceed to checkout in shipping
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"form\"]/p/button"))).build().perform();
				    				js.executeScript("window.scrollBy(0,100)");
				    				driver.findElement(By.xpath("//*[@id=\"form\"]/p/button")).click();
				    				Thread.sleep(7500);
				    				Reporter.log(passb+"<b> >> Clicked on Proceed to check out in Shipping tab </b>");
				    				
				    				//pay by check
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a"))).build().perform();
				    				js.executeScript("window.scrollBy(0,100)");
				    				driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[2]/div/p/a")).click();
				    				Thread.sleep(7500);
				    				Reporter.log(passb+"<b> >> Clicked on Pay by check button in Payment Tab </b>");
				    				
				    				//Confirm the order
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button"))).build().perform();
				    				js.executeScript("window.scrollBy(0,100)");
				    				driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
				    				Thread.sleep(10000);
				    				Reporter.log(passb+"<b> >> Clicked on Confirm the order </b>");
				    				
				    				if(driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[1]")).getText().contains("complete.")) {
				    					js.executeScript("window.scrollBy(0,300)");
				    					Reporter.log(passb+"<b> >> Order Placed Successfully </b>");
				    				}
				    				
				    			}
				    				
				    			}
				    		
				    		WebElement signout = driver.findElement(By.linkText("Sign out"));
				    		bst = isClickable(signout);
				    		if(bst) {
				    			
				    			signout.click();
				    			Reporter.log(passb+"<b> >> Sign out Successfully</b>");
				    			Thread.sleep(5000);
				    			
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
	    		Reporter.log(passb+"<b> >> Following error has been occurred in Place Order module </b>");
	    		Reporter.log("<br> "+e);
	    	}
	    }
	 
	 
	 
	 @AfterSuite 
	    protected void afterSuite() throws IOException, InterruptedException
	    { 
		 	//Make sure place correctly your report path.
	    	driver.get(System.getProperty("user.dir") + "/test-output/index.html"); 
	    	
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.linkText("Reporter output")).click();
	    	 	
	    	
	    }
	 

}
