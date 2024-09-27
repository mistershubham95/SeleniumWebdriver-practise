@additemstocart @AllTest
Feature: This feature will verify the feature add-to-cart funcationality.

  Background: Restaurant is at servicable location and user has landed at Product page
    Given User has launched the Swiggy application already
  
   @AddSingleProduct
  Scenario: Verify that user able to add single product to the cart
    When User add a product to the cart
    Then User should able to see cart has value

  @ChangeQuantityofProducts
  Scenario Outline: Verify that user able to add multiple product to the cart
    When User add a product to the cart
    Then User clicks on plus '<AddorRemove>' to increase the quantity
    And User should be able to see change in the quantity 
	Examples:
		|AddorRemove|
		|Add| 
    
 @clearCart
  Scenario Outline: User should be able to clear the cart
    When User add a product to the cart
		Then User clicks on plus '<AddorRemove>' to increase the quantity
    And User clicks on '<clearCart>' to clear the cart
	Examples:
		|AddorRemove|clearCart|
		|Add        |Remove|