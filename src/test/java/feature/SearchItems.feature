@SearchItems @AllTest
Feature: User will be able to search some food items.
  
  @PositiveTest
  Scenario Outline: User will try search some food items
    Given User has already launched the Swiggy application
    When User enter '<SearchItems>' in the search bar
    Then Will confirm how many restaurants are having this item '<SearchItems>'
       Examples: 
      | SearchItems | 
      | Gulab jamun | 
	    | Seek kabab  | 
      | Biryani     |
    


