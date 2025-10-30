Feature: Error Validation 

	 @ErrorValidation
	 Scenario Outline:
		Given I landed on Ecommerce page
		When Logged in with the <name> username and password <password>
		Then "Incorrect email or password." message is displayed
		
		Examples:
		|name            | password     |
		|ck412@gmail.com | Selenium@111 |