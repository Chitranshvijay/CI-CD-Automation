package Main.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Main.AbstractComponents.AbstractComponent;

public class PlaceOrderPage extends AbstractComponent{

	WebDriver driver;
	
	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryField;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement countrySelected;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	
	By countryBy= By.cssSelector("[placeholder='Select Country']");
	By countryResult= By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		waitForElementToAppear(countryBy);
		countryField.sendKeys(countryName);
		
		waitForElementToAppear(countryResult);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",countrySelected);
	}
	
	public ConfirmationPage goToConfirmationPage() {
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",placeOrderBtn);
		
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
	


}
