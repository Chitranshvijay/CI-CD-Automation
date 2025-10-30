package Main.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Main.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPwd;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue LoginWebpage(String email, String pwd) {
		
		userEmail.sendKeys(email);
		userPwd.sendKeys(pwd);
		loginBtn.click();
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		String errorMsg= errorMessage.getText();
		return errorMsg;
		}
	
	public void goToURL() {
		
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
