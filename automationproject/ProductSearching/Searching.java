package ProductSearching;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Searching extends BasePackage.BaseClass{
	public String keyword ="dress";
	public String expected = "Search - My Store";
	
	@Test
	public void searchingProduct() {	
		//input the keyword and submit the form
		driver.findElement(By.id("search_query_top")).sendKeys(keyword);
		driver.findElement(By.id("searchbox")).submit();
		
		//verify the page title, ensure user redirected to search result page
		Assert.assertEquals(driver.getTitle(), expected);
	}
	
	@Test
	public void verifySearchSuggestion(){	
		int i=1;
		
		//input the keyword
		driver.findElement(By.id("search_query_top")).sendKeys(keyword);
		
		//wait for max 120 second before return error element ac_result not visible 
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ac_results")));
		
		//get all list of suggestion
		List<WebElement> allElements = driver.findElements(By.xpath("//*[@class='ac_results']/ul/li")); 
		
		//looping along the list
		for (WebElement element: allElements) {
			//check if the suggestion contain the keyword
			Assert.assertTrue(element.getText().toLowerCase().contains(keyword));
			
			//check if there is duplicate suggestion
			Assert.assertEquals(i, 1);
			i = 0;
			for (WebElement suggestion: allElements) {
				if(element.getText().contentEquals(suggestion.getText())){
					i++;
				}
			}
		}
	}
	
	@Test
	public void verifySearchSuggestionResult(){
		//input the keyword
		driver.findElement(By.id("search_query_top")).sendKeys(keyword);
		
		//wait for max 120 second before return error element ac_result not visible 
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ac_results")));
		
		//click the suggestion
		driver.findElement(By.xpath("//*[@class='ac_results']/ul/li")).click();
		
		//verify the page title, ensure user redirected to search result page
		Assert.assertEquals(driver.getTitle(), expected);
	}
}