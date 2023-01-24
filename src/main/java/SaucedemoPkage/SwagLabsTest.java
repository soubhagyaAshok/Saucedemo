package SaucedemoPkage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SwagLabsTest {

	WebDriver driver;
	@BeforeTest
	public void searchTest() throws InterruptedException {
		
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
	}
	@AfterTest
	public void closeApp() throws Exception {
		
		Thread.sleep(5000);
		driver.close();	
	}
	
	@Test
	public void VerifyApplication() throws InterruptedException{
		// Verify the title as Swag Labs	
		
		String expectedTitle ="Swag Labs";
		String actualTitle = driver.getTitle();
		System.out.println("Application Title : " + actualTitle); 
		if(actualTitle.equalsIgnoreCase(expectedTitle))
			System.out.println("Title Matched");
		else
			System.out.println("Title didn't match");

		Thread.sleep(2000);

		//  Verify the login button text is capitalized 
		
		 String buttontext= driver.findElement(By.xpath("//*[@class=\"submit-button btn_action\"]")).getCssValue("text-transform");
	    Thread.sleep(1000);

		System.out.println(buttontext);
	    if(buttontext.matches("uppercase"))
	        System.out.println("The button text is all capitalized");
	    else
	        System.out.println("The button text is not in calpital");
	    
	    // Login with standard_user & secret_sauce	    
		driver.findElement(By.xpath("//*[@name=\"user-name\"]")).sendKeys("standard_user" + Keys.ENTER);
		driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys("secret_sauce" + Keys.ENTER);
		driver.findElement(By.xpath("//*[@name=\"login-button\"]")).click();
		Thread.sleep(2000);
		
		// Verify default filter dropdown is A-Z
		WebElement Filter = driver.findElement(By.xpath("//*[@class=\"product_sort_container\"]"));
		Select sel = new Select(Filter);
		String defaultActualvalue = sel.getFirstSelectedOption().getText();
	    System.out.println(defaultActualvalue);
	    String expectedActualvalue ="Name (A to Z)";
	    if(defaultActualvalue.equalsIgnoreCase(expectedActualvalue))
			System.out.println("default Dropdown value Matched");
		else
			System.out.println("default Dropdown value didn't match");	    
	    Thread.sleep(2000);
	   
	    // Add the first product to the cart
 
	    driver.findElement(By.xpath("(//button[contains(text(),'cart')])[1]")).click();
		
	    //  Verify the cart badge has 1 product
	    
	    WebElement CartValue = driver.findElement(By.xpath("//*[@class=\"shopping_cart_badge\"]"));
	    
	    if(CartValue.getText().equals("1"))
	        System.out.println("Cart has 1 item");
	    else
	        System.out.println("cart value is not 1");
	    
	    // Add the last product to the cart
	    
	    
	    driver.findElement(By.xpath("(//button[contains(text(),'cart')])[5]")).click();
	    
	    WebElement ProductName = driver.findElement(By.xpath("//*[contains(text(),\"Test.allTheThing\")]"));
	    //Verify the cart badge value is increased
	    System.out.println(ProductName.getText());
	    if(CartValue.getText().equals("1"))
	        System.out.println("Cart has 1 item");
	    else
	        System.out.println("cart value is more than 1");
	    
	    //Remove the first product from the cart
	    driver.findElement(By.xpath("(//button[contains(text(),'Remove')])[1]")).click();
	    
	    //Verify the cart badge has 1 product
	    
	    if(CartValue.getText().equals("1"))
	        System.out.println("Cart has 1 item");
	    else
	        System.out.println("cart value is not 1");
	    
	    // Click on the cart 🛒
	    
	    CartValue.click();
	    Thread.sleep(5000);
	    
	    // Verify the added product is available
	    
	    WebElement ItemInCart = driver.findElement(By.xpath("//*[@class=\"inventory_item_name\"]"));
	    if(ItemInCart.getText().equals(ProductName))
	    	System.out.println("The item is matching with the added product");
	    else
	    	System.out.println("Item is not matching");
	    // Click on the continue shopping
	    
	    driver.findElement(By.xpath("//*[@id=\"continue-shopping\"]")).click();
	   
	    //Change the price filter from low to high
	    WebElement Filter1 = driver.findElement(By.xpath("//*[@class=\"product_sort_container\"]"));
	 Thread.sleep(10000);
	 Select sel1 = new Select(Filter1);
	 sel1.selectByValue("lohi");
	
	    // Verify the price sorted properly
	 List<WebElement> price = driver.findElements(By.xpath("//*[@class=\"inventory_item_price\"]"));

     //List ourAl = new ArrayList<>();
     for (int i = 0; i<price.size(); i=i+1) 
     {
    	 if(i<=i+1)
     System.out.println(price.get(i).getText());          
     }          
	}
	    
	}

