	Feature: Demo Web Shop
	
	  Background:
	    Given user clicks on the Login button
	    And user should enter a email "dheemegam002@gmail.com"
    	And user should enter a password as "Dineshdk@08"
    	And user should clicks a remember me check box
    	When user should click the login button 
    	Then user should login into the account 

	
	  @smoke
	  Scenario Outline: Search for a product
	    Given user enters the product name "<product_name>"
	    When user clicks the search button
	    Then search product will display "<expected_result>"
	
	    Examples:
	      | product_name | expected_result    |
	      | Laptop       | 14.1-inch Laptop   |
	      | Lap          | 14.1-inch Laptop   |
	
	  @smoke
	  Scenario Outline: Search for an invalid product
	    Given user enters the product name "<product_name>"
	    When user clicks the search button
	    Then no product message should display "<expected_result>"
	
	    Examples:
	      | product_name | expected_result                                              |
	      | XYZ123       | No products were found that matched your criteria.           |
	      | @#$%^&*      | No products were found that matched your criteria.           |
	      | 12345        | No products were found that matched your criteria.           |
	
	  @smoke
	  Scenario: Search for an empty product
	    Given user enters the product name " "
	    When user clicks the search button
	    Then an empty error message should display
