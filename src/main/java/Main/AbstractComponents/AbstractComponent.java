package Main.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Main.PageObjects.MyCartPage;
import Main.PageObjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement addtoCart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersBtn;

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement elements) {
		
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(elements));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(3000);
		//WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		//w.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public MyCartPage goToCartPage() {
		
		addtoCart.click();
		MyCartPage myCartPage = new MyCartPage(driver);
		return myCartPage;
	}
	
	public OrdersPage goToOrdersPage() {
		
		ordersBtn.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
}
