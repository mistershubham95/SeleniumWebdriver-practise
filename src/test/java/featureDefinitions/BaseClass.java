package featureDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

public class BaseClass {

	public static WebDriver driver;
	public static Scenario scenario;
	public static boolean ScrenShotFlag; // default enable ScreenShot after each step

	// Before hook === Setup the Chrome Webdriver

	@Before
	public static void setup() {

		System.setProperty("webdriver.chrome.driver","C:\\Users\\ahmad\\eclipse-workspace\\SwiggyAutomationPhase2Project\\chromedriver.exe");
		driver = new ChromeDriver();
		ScrenShotFlag = true;

	}

	public static void applaunch() {

		driver.get("https://www.swiggy.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.MILLISECONDS);
		// validatePinCode("110025");
	}

// Method for validating location.
	public static boolean validatePinCode(String PinorLocation) {

		
		WebElement findfoodButtonElement = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div/div[1]/div[1]/div/div[2]/a/span"));
		WebElement searchlocationElement = driver.findElement(By.id("location"));
		searchlocationElement.sendKeys(PinorLocation);

		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		WebElement FindFoodButton = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div/div[1]/div[1]/div/div[2]/a/span"));

		// ----------------------------------Product
		// page------------------------------------------------------------------------------
		

			// Getting list of addresses based on 'Pin Code' or Name of the location.
						List<WebElement> searchresultsElements = driver
								.findElements(By.xpath("//div[@class='_1oLDb']//div/descendant::span[@class='_2W-T9']"));
						System.out.println("List of locations: " + searchresultsElements.size());

			// selecting a particular address from the list we obtained above.
						if (searchresultsElements.size() > 0) {
							searchresultsElements.get(1).click();
							if (driver.findElements(By.xpath("//div[@class='_1b8uz']")).size() == 0) {

								driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
								WebElement swiggyloggoElement = driver
										.findElement(By.xpath("//*[@id='root']/div[1]/header/div/div/a"));
								WebElement cartlogoElement = driver.findElement(
										By.xpath("//*[@id='root']/div[1]/header/div/div/ul/li[1]/div/div/div/a/span[2]"));
								driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

								// checking if we goto the Product page.
								if (swiggyloggoElement.isDisplayed() && cartlogoElement.isDisplayed()) {
									System.out.println("Your have sucessfully landed on the Product page");
								}
							//return true;
							}
							

							else if (driver
									.findElements(By.xpath("//*[@id='root']/div[1]/div[1]/div/div[1]/div[1]/div/div[2]/a/span"))
									.size() != 0) {
								FindFoodButton = driver
										.findElement(By.xpath("//*[@id='root']/div[1]/div[1]/div/div[1]/div[1]/div/div[2]/a/span"));
								FindFoodButton.click();
								System.out.println("You are in Else");
								if ((driver.findElements(By.xpath("//div[@class='_1b8uz']")).size() != 0)
										&& (driver.findElement(By.xpath("//div[@class='_1b8uz']")).isDisplayed() == true)) {

									System.out.print("Enter your delivery location.");
									System.out.println(" " + "Sorry! We don't serve at your location currently.");
									
									return false;
								}
								
							}
							

						}

						else if (driver.findElements(By.xpath("//*[@id='root']/div[1]/div[1]/div/div[1]/div[1]/div/div[2]/a/span"))
								.size() != 0) {
							FindFoodButton = driver
									.findElement(By.xpath("//*[@id='root']/div[1]/div[1]/div/div[1]/div[1]/div/div[2]/a/span"));
							FindFoodButton.click();
							System.out.println("You are in Else");
							if ((driver.findElements(By.xpath("//div[@class='_1b8uz']")).size() != 0)
									&& (driver.findElement(By.xpath("//div[@class='_1b8uz']")).isDisplayed() == true)) {

								System.out.print("Enter your delivery location.");
								System.out.println(" " + "Sorry! We don't serve at your location currently.");

					}
				}
						return ScrenShotFlag;
	}

	public static boolean ConfirmProductPage() {

			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
			if ((driver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/header/div/div/a")).size() !=0) && (driver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/header/div/div/ul/li[1]/div/div/div/a/span[2]")).size() != 0)) {
				System.out.println("Your have sucessfully landed on the Product page");
				return true;
				}
			
			else {
				
			System.out.println("This isn't a product page");
			System.out.println("Enter your delivery location. Sorry! We don't serve at your location currently.");
			return false;
				}
		
		
		

//		try {
//			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
//			if ((driver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/header/div/div/a")).size() !=0) && (driver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/header/div/div/ul/li[1]/div/div/div/a/span[2]")).size() != 0)) {
//				System.out.println("Your have sucessfully landed on the Product page");
//				
//				}
//		   }
//		return true;
//	catch(Throwable e) {
//			System.out.println("This isn't a product page");
//			System.out.println("Enter your delivery location. Sorry! We don't serve at your location currently.");
//			return false;
//		}
	}

	@SuppressWarnings("unused")
	public static void addToCartSwiggy() {

		List<WebElement> findResturantlistElement = null;
		List<WebElement> sideOptionalitemstoselectElements = null;
		List<WebElement> selectingItemtoEatWebElement = null;
		WebDriverWait wait = new WebDriverWait(driver, 5);

		if (driver.findElements(By.xpath("//div[@class='MZy1T']//div/descendant::*[@href]")).size() >= 0) {
			// System.out.println("Finding restaurants");
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
			findResturantlistElement = driver.findElements(By.xpath("//div[@class='MZy1T']//div/descendant::*[@href]"));
		} else {
			System.out.println("Checking for Food options, else");
			findResturantlistElement = driver.findElements(By.xpath("//div[@class='MZy1T']//div/descendant::*[@href]"));
		}

		System.out.println("List of Resturants: " + findResturantlistElement.size());

		// selecting a random restaurant
		findResturantlistElement.get(1).click();

		// ---------------------Selecting a Food item from the
		// list----------------------------------------

		if (driver.findElement(By.xpath("//div[@class='_1RPOp']")).isDisplayed()) {
			System.out.println("We are in finding food options, if ");
			selectingItemtoEatWebElement = driver.findElements(By.xpath("//div[@class='_1RPOp']"));
		}

		else if (driver.findElement(By.xpath("//div[@class='_2wg_t']")).isDisplayed()) {
			selectingItemtoEatWebElement = driver.findElements(By.xpath("//div[@class='_2wg_t']"));
			System.out.println("We are in finding food options, Else ");
		}
		System.out.println("Number of Food choices: " + selectingItemtoEatWebElement.size());

//Clicking Rain Alert
		if (driver.findElements(By.xpath("//*[@id='root']/div[2]/div/div/div[3]")).size() > 0) {
			WebElement rainAlertElement = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div[2]/div/div/div[3]")));
			driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div/div[3]")).click();
			System.out.println("Checking Rain Alert");
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		}

// To check if the selected item from the selected restaurant is deliverable.
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		if (driver.findElements(By.xpath("//*[@id='root']/div[2]/div/div/div[2]/div[2]")).size() > 0) {
			System.out.println("The Selected Restaurant doesn't deliver to your location");
		}
//Selecting one of item from the available list of food items.		
		selectingItemtoEatWebElement.get(1).click();

//Capturing high demand charges, alert.
		System.out.println("You are at the Demand high, condition");
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		if (driver.findElements(By.xpath("//div[@id='root']//div/descendant::div[@class='_1zVBl']")).size() > 0) {
			driver.findElement(By.xpath("//div[@id='root']//div/descendant::div[@class='_1zVBl']")).click();
			System.out.println("Demand is currently high");
		}

//looking for optional items to select if available.
		System.out.println("looking for optional");
		if (driver.findElements(By.xpath("//label[@class='_3MtuI']//div/descendant::label[@class='b5XpK']"))
				.size() > 0) {
			sideOptionalitemstoselectElements = driver
					.findElements(By.xpath("//label[@class='_3MtuI']//div/descendant::label[@class='b5XpK']"));

			// Printing list of optional items available with the selected restaurant.
			System.out.println("optional items: " + sideOptionalitemstoselectElements.size());

			// Adding items to the cart.
			sideOptionalitemstoselectElements.get(0).click();
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		}

		else if (driver.findElements(By.xpath("//*[@id='modal-placeholder']//div/descendant::*[text()='Add Item']"))
				.size() > 0) {
			driver.findElement(By.xpath("//*[@id='modal-placeholder']//div/descendant::*[text()='Add Item']")).click();
		} else if (driver.findElements(By.xpath("//*[@id='modal-placeholder']//div/descendant::*[text()='Continue']"))
				.size() > 0) {
			driver.findElement(By.xpath("//*[@id='modal-placeholder']//div/descendant::*[text()='Continue']")).click();

			if (driver.findElements(By.xpath("//label[@class='_3MtuI']//div/descendant::label[@class='b5XpK']"))
					.size() > 0) {
				sideOptionalitemstoselectElements = driver
						.findElements(By.xpath("//label[@class='_3MtuI']//div/descendant::label[@class='b5XpK']"));

				// Printing list of optional items available with the selected restaurant.
				System.out.println("optional items: " + sideOptionalitemstoselectElements.size());

				// Adding items to the cart.
				sideOptionalitemstoselectElements.get(0).click();
				driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
			}

			if (driver.findElements(By.xpath("//*[@id='modal-placeholder']//div/descendant::*[text()='Add Item']"))
					.size() > 0) {
				driver.findElement(By.xpath("//*[@id='modal-placeholder']//div/descendant::*[text()='Add Item']"))
						.click();
			}
		}

		if (driver.findElements(By.xpath("//*[@id='modal-placeholder']//div/descendant::*[text()='Add Item']"))
				.size() > 0) {
			driver.findElement(By.xpath("//*[@id='modal-placeholder']//div/descendant::*[text()='Add Item']")).click();
		}

		if (driver.findElements(By.xpath("//*[text()='Repeat last used customization?']")).size() > 0) {
			driver.findElement(By.xpath("//*[text()='REPEAT LAST']")).click();
			System.out.println("App is asking for whether to continue previous customization of the food item");
		}

		System.out.println("Positioned at Check Cart Value");

		WebElement cartWebElement = null;
		cartWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='_2vS77']")));
		String cartValue;
		cartValue = driver.findElement(By.xpath("//span[@class='_2vS77']")).getText();
		System.out.println("Cart Value: " + cartValue);
	}
	
	
	public static void CheckCartValue() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement cartWebElement = null;
		cartWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='_2vS77']")));
		String cartValue;
		cartValue = driver.findElement(By.xpath("//span[@class='_2vS77']")).getText();
		System.out.println("Cart Value: " + cartValue);
		//driver.findElement(By.xpath("//*[text()='Checkout']")).click();
		
	}
	
	

	

	public static void SwiggyalterCartValue(String AddorRemove) {


		WebElement increaseCartItemElement, decreaseCartItemElement;
		WebDriverWait wait = new WebDriverWait(driver, 20);
	
		System.out.println("Printing AddorRemove :::-> "+ AddorRemove );
		
		//Increasing or deleting item from the cart. 
	if ( AddorRemove.equals("Add")){
	
		System.out.println("I am in Add");
			increaseCartItemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_1ds9T']")));
			increaseCartItemElement = driver.findElement(By.xpath("//div[@class='_1ds9T']"));
			//decreaseCartItemElement = driver.findElement(By.xpath("//div[@class='_29Y5Z']"));

			increaseCartItemElement.click();
			if (driver.findElements(By.xpath("//*[text()='Repeat last used customization?']")).size() > 0) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Repeat last used customization?']")));
				if (driver.findElements(By.xpath("//*[text()='Repeat last used customization?']")).size() > 0) {
					driver.findElement(By.xpath("//*[text()='REPEAT LAST']")).click();
					//System.out.println("while altering cart value");
				}
			}

			increaseCartItemElement = driver.findElement(By.xpath("//div[@class='_1ds9T']"));
			increaseCartItemElement.click();
			if (driver.findElements(By.xpath("//*[text()='Repeat last used customization?']")).size() > 0) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Repeat last used customization?']")));
				if (driver.findElements(By.xpath("//*[text()='Repeat last used customization?']")).size() > 0) {
					driver.findElement(By.xpath("//*[text()='REPEAT LAST']")).click();
					//System.out.println("while altering cart value");
				}
			}
		}
    else if(AddorRemove.equals("Remove") )	{
		
		System.out.println("I am in clear");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_29Y5Z']")));
		//decreaseCartItemElement = driver.findElement(By.xpath("//div[@class='_29Y5Z']"));
		
		driver.findElement(By.xpath("//*[text()='Checkout']")).click();
		WebElement nEwCartWebElement = driver.findElement(By.xpath("//div[@class='_2zAXs']"));
		String Newcartvalue = nEwCartWebElement.getText();
		//System.out.println("Cart Value after altering Cart value: " + Newcartvalue);
		
		int loopelement = Integer.parseInt(Newcartvalue);
		
		System.out.println("Loop Element Value::" + loopelement);
		
		if (loopelement == 0) {
			
			System.out.println("No item in the cart");
		}
		
		else if(loopelement > 0) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_29Y5Z']")));
			for (int i = 0 ; i < loopelement; i++ ) {
				System.out.println("Inside the loop");
				decreaseCartItemElement = driver.findElement(By.xpath("//div[@class='_29Y5Z']"));
				decreaseCartItemElement.click();
			}
		}
	}	
	
}

	public static void SearchingFoodItems(String ToSearchItem) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Search']")));
		WebElement searchBarElement = driver.findElement(By.xpath("//*[text()='Search']"));
		searchBarElement.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='_2BJMh']")));
		WebElement searchBoxElement = driver.findElement(By.xpath("//*[@class='_2BJMh']"));
		searchBoxElement.sendKeys(ToSearchItem);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//div[@class='_34T1N']"))));
		List<WebElement> listofitemsfromsearchElements = driver.findElements(By.xpath("//div[@class='_34T1N']"));
		System.out.println("search elements: "+ listofitemsfromsearchElements.size());
		
		if(listofitemsfromsearchElements.size() == 0) {
			System.out.println("No item found");
		}
		else {
			listofitemsfromsearchElements.get(1).click();
		}
		
		
	}
	
	public static void NumberofSearchingFoodItems(String ToSearchItem) {

		WebDriverWait wait = new WebDriverWait(driver,20);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Dishes']")));
		
		if (driver.findElement(By.xpath("//*[text()='Dishes']")).isDisplayed() == true) {
		driver.findElement(By.xpath("//*[text()='Dishes']")).click();
		
		List<WebElement> listOfResturantBysearchElements = driver.findElements(By.xpath("//h2[@class='_2xYa7']")); 
		
		System.out.println("List of the restaurants which serve " + ToSearchItem  + " are :->  "  + listOfResturantBysearchElements.size() );
		}
		else {
			System.out.println("No item found");
		}
	
	}
	
	

	public static void ExamineCartValue() {
//		WebDriverWait wait = new WebDriverWait(driver,20);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='_2vS77']")));
//		String cartValue;
//		cartValue = driver.findElement(By.xpath("//span[@class='_2vS77']")).getText();
//		System.out.println("Cart Value: " + cartValue);

		driver.findElement(By.xpath("//*[text()='Checkout']")).click();
		WebElement nEwCartWebElement = driver.findElement(By.xpath("//div[@class='_2zAXs']"));
		String Newcartvalue = nEwCartWebElement.getText();
		System.out.println("Cart Value after altering Cart value: " + Newcartvalue);
		
	}
	
	
	
	/*
	 * AfterStep hook === We can perform screen capture, tweaked by the ssFlag from
	 * each step methods, default to true (enable)
	 */
	@AfterStep
	public void afterStep(Scenario scenario) {
		
		System.out.println("DEBUG: afterStep: ScrenShotFlag " + BaseClass.ScrenShotFlag);

		if (BaseClass.ScrenShotFlag) {
			scenario.log("Add Screenshot");
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "image");
		} else {
			BaseClass.ScrenShotFlag = true; // toggle back to enable
		}
	}

	/*
	 * === After hook === clean up step
	 */
	@After
	public void teardown() throws InterruptedException {

		Thread.sleep(1000);
		driver.quit();

	}

}
