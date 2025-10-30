package Main.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Main.PageObjects.MyCartPage;
import Main.PageObjects.ProductCatalogue;
import Main.TestComponents.BaseTest;
import Main.TestComponents.Retry;

public class ErrorValidations extends BaseTest{

	@Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidations() throws IOException, InterruptedException {
		
		//Login with wrong credentials
		String email= "ck@gmail.com";
		String pwd = "Selenium@111111";
		landingPage.LoginWebpage(email, pwd);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidations() throws IOException, InterruptedException {
		
		//Select Wrong Product
		String productName= "ZARA COAT 3";
		ProductCatalogue productCatalogue= landingPage.LoginWebpage("kc0000@gmail.com", "Selenium@0");
		productCatalogue.getProductList();
		productCatalogue.getProductByName(productName);
		productCatalogue.AddToCart(productName);
		MyCartPage myCartPage= productCatalogue.goToCartPage();
		
		//Verify products on MyCart page
		Boolean match= myCartPage.verifyProducts("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	

}
