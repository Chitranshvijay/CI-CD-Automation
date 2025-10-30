package Main.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Main.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement ele;
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By productAddedMessage= By.id("toast-container");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod= getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findAny().orElse(null);
		return prod;
	}
	
	public void AddToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductByName(productName); 
		prod.findElement(addToCart).click();
		
		waitForElementToDisappear(ele);
	}
	
	
}
