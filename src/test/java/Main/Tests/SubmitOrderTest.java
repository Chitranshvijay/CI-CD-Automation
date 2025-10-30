package Main.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Main.PageObjects.ConfirmationPage;
import Main.PageObjects.MyCartPage;
import Main.PageObjects.OrdersPage;
import Main.PageObjects.PlaceOrderPage;
import Main.PageObjects.ProductCatalogue;
import Main.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	//String productName= "ZARA COAT 3";

	@Test(dataProvider= "getData", groups = {"Package"})
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		//Login
		//String email= "ck412@gmail.com";
		//String pwd = "Selenium@1";
		ProductCatalogue productCatalogue= landingPage.LoginWebpage(input.get("email"), input.get("password"));
		
		//Click on 'Add to Cart'
		productCatalogue.getProductList();
		productCatalogue.getProductByName(input.get("productName"));
		productCatalogue.AddToCart(input.get("productName"));
		MyCartPage myCartPage= productCatalogue.goToCartPage();
		
		//Verify products on MyCart page
		Boolean match= myCartPage.verifyProducts(input.get("productName"));
		Assert.assertTrue(match);
		
		//Click on checkout button
		PlaceOrderPage placeOrder= myCartPage.goToCheckoutPage();
		
		//Fill the country details and place order
		String countryName= "India";
		placeOrder.selectCountry(countryName);
		ConfirmationPage confirmationPage= placeOrder.goToConfirmationPage();
		
		//Confirmation of order placed
		String confirmationMsg= confirmationPage.getConfirmMessage();
		System.out.println(confirmationMsg);
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest() throws IOException, InterruptedException {
		
		//To verify the product ordered is displayed on Orders Page.
		String email= "ck412@gmail.com";
		String pwd = "Selenium@1";

		ProductCatalogue productCatalogue= landingPage.LoginWebpage(email, pwd);
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrders("ZARA COAT 3"));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		//HashMap<String, String> map1= new HashMap<String, String>();
		//map1.put("email", "ck412@gmail.com");
		//map1.put("password", "Selenium@1");
		//map1.put("productName", "ZARA COAT 3");
		
		//HashMap<String, String> map2= new HashMap<String, String>();
		//map2.put("email", "kc0000@gmail.com");
		//map2.put("password", "Selenium@0");
		//map2.put("productName", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "//src//test//java//Main//Data//PurchaseOrder.json");
		return new Object [] [] {{data.get(0)}, {data.get(1)}};
	}
	
	

}
