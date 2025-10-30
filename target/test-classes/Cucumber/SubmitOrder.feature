@tag
Feature: Purchase the order from E-commerce website.

	Background:
		Given I landed on Ecommerce page
	@Regression 
	Scenario Outline: Positive test for submitting the order.
		Given Logged in with the <name> username and password <password>
		When Add product <productName> to cart
		And Checkout <productName> and submit the order
		Then "THANKYOU FOR THE ORDER."Message is displayed on ConfirmationPage
		
		Examples:
		|name            | password   | productName |
		|ck412@gmail.com | Selenium@1 | ZARA COAT 3 |
		