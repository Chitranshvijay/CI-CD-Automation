package Main.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Main.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProducts;
	
	
	By ordersBy= By.cssSelector("tr td:nth-child(3)");
	
	public Boolean verifyOrders(String productName) {
		
		waitForElementToAppear(ordersBy);
		Boolean match= orderProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	

}
