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

public class ShareProduct {
	
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
	 
	 
	
	 
	 @Test(priority =7,enabled = true)
	  public void Setup_For_Share_Product_Page() throws IOException {    
		  
		 try {
			 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
			 driver = new ChromeDriver();
			 appUrl="http://automationpractice.com/index.php";
			 
			 Reporter.log(passb+"<b> >> Browser Initialized Sucessfully of Share Product Module</b>");
		 
		 }
		 catch(Exception e) {
			 Reporter.log(failb+"<b> >> Browser Initialization Failed. Please verify the chrome driver file available in project location of Share Product Module</b>");
		 }

	  }
	 
	 @Test(priority =8,enabled = true)
	    public void Test_Initialize_For_Share_Product_Page() throws InterruptedException,  IOException{ 
	    	try {
		        
			    driver.get(appUrl);
		        Thread.sleep(1000);
		        driver.manage().window().maximize();
		        Reporter.log(passb+"<b> >> Application Getting Launched and Maximized successfully in Share Product Module</b>");
	    	}
	    	catch (Exception e) {
	    		Reporter.log(failb+"<b> >> Application Not Getting Launched for the following error in Share Product Module</b>");
	    		Reporter.log("<br> "+e);
	    	}
	    }
	 
	 @Test(priority = 9,enabled = true)
	    public void TestCases_For_Share_Product_Page() throws InterruptedException,  IOException{ 
	    	try {
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
	    		Actions action = new Actions(driver);
			    WebElement signInButton = driver.findElement(By.linkText("Sign in"));
			    Thread.sleep(500);
			    boolean bst = ShareProduct.isClickable(signInButton);
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
			    	
			    	// Login
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
				    	Thread.sleep(3000);
				    	
				    	if(driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1")).getText().contains("MY ACCOUNT")) {
				    		Reporter.log(passb+"<b> >> Page has been redirected to My Account</b>");
				    		
				    		
				    		//Search Field
				    		WebElement searchField = driver.findElement(By.id("search_query_top"));
				    		Thread.sleep(1000);
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
				    				
				    				//Click on First Search Result
				    				js.executeScript("window.scrollBy(0,300)");
				    				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img"))).moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[2]/span"))).build().perform();
				    				driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[2]/a[2]/span")).click();
				    				Thread.sleep(5000);
				    				Reporter.log(passb+"<b> >> First Search result is clicked </b>");

				    				
				    				//Share on Facebbok
				    				WebElement fbbtn = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/p[7]/button[2]"));
				    				fbbtn.click();
				    				Thread.sleep(3000);
				    				Reporter.log(passb+"<b> >> Facebook Share linked is clicked </b>");
				    				
				    				
				    				Set<String> windows = driver.getWindowHandles();
				    				// window handles iterate
				    				Iterator<String> iter = windows.iterator();
				    				String myaccPage = iter.next();
				    				String fbPage = iter.next();
				    				// switching Facebook window
				    				driver.switchTo().window(fbPage);
			    			      
				    				WebElement fbEmail = driver.findElement(By.id("email"));
				    				fbEmail.clear();
				    				fbEmail.sendKeys(testData.facebookUserName);
				    				Thread.sleep(1000);
				    				Reporter.log(passb+"<b> >> Facebook Dummy Username has been passed successfully </b>");
			    			      
				    				WebElement fbPwd = driver.findElement(By.id("pass"));
				    				fbPwd.clear();
				    				fbPwd.sendKeys(testData.facebookPassword);
				    				Thread.sleep(2000);
				    				Reporter.log(passb+"<b> >> Facebook Dummy password has been passed successfully </b>");
			    			     	
				    				// close the Facebook browser window
				    				driver.close();
				    				Reporter.log(passb+"<b> >> Facebook Window has been Closed successfully </b>");
				    				// switching to My account window
				    			    driver.switchTo().window(myaccPage);
				    			    Thread.sleep(3000);
				    			    Reporter.log(passb+"<b> >> Page has been switch over to Apparel store screen from Facebook browser window </b>");
				    			    
				    			    //Share on google
				    				WebElement googbtn = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/p[7]/button[3]"));
				    				googbtn.click();
				    				Thread.sleep(3000);
				    				Reporter.log(passb+"<b> >> Google Share linked is clicked </b>");
				    				
				    				
				    				Set<String> windows1 = driver.getWindowHandles();
				    				// window handles iterate
				    				Iterator<String> iter1 = windows1.iterator();
				    				String myaccPage1 = iter1.next();
				    				String fbPage1 = iter1.next();
				    				// switching Google window
				    				driver.switchTo().window(fbPage1);
			    			      
				    				WebElement googEmail = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
				    				googEmail.clear();
				    				googEmail.sendKeys(testData.googleUserName);
				    				Thread.sleep(1000);
				    				Reporter.log(passb+"<b> >> Google Dummy Username has been passed successfully </b>");
			    			     	
				    				// close the Google browser window
				    				driver.close();
				    				Reporter.log(passb+"<b> >> Google Window has been Closed successfully </b>");
				    				// switching to My account window
				    			    driver.switchTo().window(myaccPage1);
				    			    Thread.sleep(3000);
				    			    Reporter.log(passb+"<b> >> Page has been switch over to Apparel store screen from Google browser window</b>");
				    				
				    			    //Share on Pinterest
				    				WebElement pinbtn = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/p[7]/button[4]"));
				    				pinbtn.click();
				    				Thread.sleep(3000);
				    				Reporter.log(passb+"<b> >> Pinterest Share linked is clicked </b>");
				    				
				    				
				    				Set<String> windows2 = driver.getWindowHandles();
				    				// window handles iterate
				    				Iterator<String> iter2 = windows2.iterator();
				    				String myaccPage2 = iter2.next();
				    				String fbPage2 = iter2.next();
				    				// switching Pinterest window
				    				driver.switchTo().window(fbPage2);
			    			      
				    				WebElement pinEmail = driver.findElement(By.id("email"));
				    				pinEmail.clear();
				    				pinEmail.sendKeys(testData.pinterestUserName);
				    				Thread.sleep(1000);
				    				Reporter.log(passb+"<b> >> Pinterest Dummy Username has been passed successfully </b>");
			    			      
				    				WebElement pinPwd = driver.findElement(By.id("password"));
				    				pinPwd.clear();
				    				pinPwd.sendKeys(testData.pinterestPassword);
				    				Thread.sleep(2000);
				    				Reporter.log(passb+"<b> >> Pinterest Dummy password has been passed successfully </b>");
			    			     	
				    				// close the Pinterest browser window
				    				driver.close();
				    				Reporter.log(passb+"<b> >> Pinterest Window has been Closed successfully </b>");
				    				// switching to My account window
				    			    driver.switchTo().window(myaccPage2);
				    			    
				    			    Thread.sleep(3000);
				    			    Reporter.log(passb+"<b> >> Page has been switch over to Apparel store screen from Pinterest browser window </b>");
				    			    
				    			    
				    			    
				    			    //Writing review
				    			    js.executeScript("window.scrollBy(0,200)");
				    			    
				    			    
				    			    WebElement reviewBtn = driver.findElement(By.xpath("//*[@id=\"product_comments_block_extra\"]/ul/li/a"));
				    			    reviewBtn.click();
				    			    Thread.sleep(1500);
				    			    
				    			    //click the rating
				    			    driver.findElement(By.xpath("//*[@id=\"criterions_list\"]/li/div[1]/div[6]/a")).click();
				    			    Thread.sleep(1000);
				    			    Reporter.log(passb+"<b> >> Rating has been given successfully </b>");
				    			    
				    			    //Send text to Title
				    			    WebElement rwTle = driver.findElement(By.id("comment_title"));
				    			    rwTle.clear();
				    			    rwTle.sendKeys(testData.reviewTitle);
				    				Thread.sleep(1000);
				    				Reporter.log(passb+"<b> >> Review Title has been passed successfully </b>");
			    			      
				    				WebElement rwCom = driver.findElement(By.id("content"));
				    				rwCom.clear();
				    				rwCom.sendKeys(testData.reviewComments);
				    				Thread.sleep(2000);
				    				Reporter.log(passb+"<b> >> Review Comments has been passed successfully </b>");
				    				
				    				//Send button
				    				WebElement sendBtn = driver.findElement(By.id("submitNewMessage"));
				    				sendBtn.click();
				    			    Thread.sleep(15000);
				    			    Reporter.log(passb+"<b> >> Review has been submitted successfully </b>");
				    			    
				    			    //ok button
				    			    
				    			    action.sendKeys(Keys.ESCAPE).perform();
				    			    Thread.sleep(1500);
				    			    
				    			    //sign out
				    			    WebElement signout = driver.findElement(By.linkText("Sign out"));
						    		bst = isClickable(signout);
						    		if(bst) {
						    			signout.click();
						    			
						    			Thread.sleep(5000);
						    			
						    		}
						    		else {
						    			
						    			Reporter.log(failb+"<b> >> Sign out button is not clickable in My Account page - Share Product module</b>");
						    			}
				    			    
				    			}
				    		}
				    		else {
				    			Reporter.log(failb+"<b> >>  Search button is not clickable</b>");
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
	    		Reporter.log(passb+"<b> >> Following error has been occurred in Share Product Module </b>");
	    		Reporter.log("<br> "+e);
	    	}
	    }
	 
	 
	 
	 @AfterSuite 
	    protected void afterSuite() throws IOException, InterruptedException
	    { 
		 //driver.close();
//		 	//Make sure place correctly your report path.
	    	driver.get(System.getProperty("user.dir") + "/test-output/index.html"); 
	    	
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.linkText("Reporter output")).click();
	    	 	
	    	
	    }
	 

}
