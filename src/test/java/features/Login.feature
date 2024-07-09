Feature: Demo Web Shop

  Background: 
    Given user clicks on the Login button

  @smoke
  Scenario: Login should be successful
  	And user should enter a email "dheemegam002@gmail.com"
    And user should enter a password as "Dineshdk@08"
    And user should clicks a remember me check box
    When user should click the login button 
    Then user should login into the account 

  @smoke @regression
  Scenario: Login should fail due to wrong email and password
  	And user should enter a email "dinesh002@gmail.com"
    And user should enter a password as "Dinesh"
    And user should clicks a remember me check box
    When user should click the login button 
    But  user should fail to login into the account due to wrong email and password
    
   Scenario: Login should fail due to wrong email
   	And user should enter a email "dheemegam002"
    And user should enter a password as "Dineshdk@08"
    And user should clicks a remember me check box
    When user should click the login button 
    But  user should fail to login into the account due to wrong email
    
   Scenario: Login should fail due to the wrong password
   	And user should enter a email "dheemegam002@gmail.com"
    And user should enter a password as "Dineshdk"
    And user should clicks a remember me check box
    When user should click the login button 
    But  user should fail to login into the account due to wrong password
    
   Scenario: Login should fail due to the both email and password as empty
   	And user should enter a email ""
    And user should enter a password as ""
    And user should clicks a remember me check box
    When user should click the login button 
    But  user should fail to login into the account due to both email and password as empty
    
    
    
    
    
   
