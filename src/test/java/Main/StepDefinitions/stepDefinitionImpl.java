package Main.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import Main.PageObjects.ConfirmationPage;
import Main.PageObjects.LandingPage;
import Main.PageObjects.MyCartPage;
import Main.PageObjects.PlaceOrderPage;
import Main.PageObjects.ProductCatalogue;
import Main.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest{

	public LandingPage landingPage; 
	public ProductCatalogue productCatalogue;
	public MyCartPage myCartPage;
	public PlaceOrderPage placeOrder;
	public ConfirmationPage confirmationPage;
	
	//Static expression, so don't require ^ and $
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		
		//code
		landingPage= launchWebPage();
	}
	
	//It is a regular expression(regex), so it should start with ^ and ends with $
	@Given("^Logged in with the (.+) username and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		
		productCatalogue= landingPage.LoginWebpage(username,password);
		
	}
	
	@When("^Add product (.+) to cart$")
	public void add_product_to_cart(String productName) throws InterruptedException {
		
		productCatalogue.getProductList();
		productCatalogue.getProductByName(productName);
		productCatalogue.AddToCart(productName);
		myCartPage= productCatalogue.goToCartPage();
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		
		//Verify products on MyCart page
		Boolean match= myCartPage.verifyProducts(productName);
		Assert.assertTrue(match);
				
		//Click on checkout button
		placeOrder= myCartPage.goToCheckoutPage();
				
		//Fill the country details and place order
		String countryName= "India";
		placeOrder.selectCountry(countryName);
		confirmationPage= placeOrder.goToConfirmationPage();
	}
	
	//Static Expression but having the parameterization
	@Then("{string}Message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		
		String confirmationMsg= confirmationPage.getConfirmMessage();
		System.out.println(confirmationMsg);
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase(string));
		
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_displayed(String string){
		
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		driver.close();
	}
}
