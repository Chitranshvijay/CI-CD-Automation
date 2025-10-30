package Main.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Main.PageObjects.LandingPage;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//Test Command to check the integration between Jenkins and Github using WebHook

		System.setProperty("webdriver.chrome.driver", "C:/Users/2165573/OneDrive - Cognizant/Desktop/Selenium/chromedriver-win64/chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		
		String email= "ck412@gmail.com";
		String pwd = "Selenium@1";
		String productName= "ZARA COAT 3";
		
		//Login
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(pwd);
		driver.findElement(By.id("login")).click();
		LandingPage landingPage = new LandingPage(driver);
		
		//Click on 'Add to Cart'
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findAny().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='cartSection']/h3"))));
		
		//Verify products on MyCart page and click Checkout
		List<WebElement> cartProducts= driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//Click the checkout button(Bypass the selenium safety check)
		WebElement checkoutBtn = driver.findElement(By.cssSelector(".totalRow button"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",checkoutBtn);
		
		
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[placeholder='Select Country']"))));
		//Fill the checkout details and place order
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		WebElement country= driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",country);
		
		WebElement placeOrder= driver.findElement(By.cssSelector(".action__submit"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",placeOrder);
		
		//Confirmation of order placed
		String confirmMsg= driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.quit();
	}
	

}
