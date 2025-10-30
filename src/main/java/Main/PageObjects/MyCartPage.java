package Main.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Main.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent{

	WebDriver driver;
	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	By cartBy= By.xpath("//div[@class='cartSection']/h3");
	
	public Boolean verifyProducts(String productName) {
		
		waitForElementToAppear(cartBy);
		Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PlaceOrderPage goToCheckoutPage() {
		
		//(Bypass the selenium safety check)
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",checkoutBtn);
		
		PlaceOrderPage placeOrder = new PlaceOrderPage(driver);
		return placeOrder;
	}
	
	
	

}
