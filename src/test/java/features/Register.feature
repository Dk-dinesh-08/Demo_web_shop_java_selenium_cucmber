Feature: Demo Web Shop

  Background: 
    Given user clicks on the Register button

  @smoke
  Scenario: Register should be successful
    And user should click the Gender option as male
    And user should enter a first name "Dinesh Kumar"
    And user should enter a last name "k"
    And user should enter a random email
    And user should enter a password as "Dineshdk@08"
    And user should enter a confirm password "Dineshdk@08"
    When user clicks the Register button
    Then Register should be successful

  @smoke @regression
  Scenario: Register should fail due to wrong email
    And user should click the Gender option as male
    And user should enter a first name "Dinesh Kumar"
    And user should enter a last name "k"
    And user should enter the email as "dheemegam"
    And user should enter a password as "Dineshdk@08"
    And user should enter a confirm password "Dineshdk@08"
    When user clicks the Register button
    But Register should fail due to wrong email

  Scenario: Register should fail due to wrong password
    And user should click the Gender option as male
    And user should enter a first name "Dinesh Kumar"
    And user should enter a last name "k"
    And user should enter the email as "dheemegam002@gmail.com"
    And user should enter a password as "Dines"
    And user should enter a confirm password "Dinesh"
    When user clicks the Register button
    But Register should fail due to wrong password

  Scenario: Register should fail due to both wrong email and password
    And user should click the Gender option as male
    And user should enter a first name "Dinesh Kumar"
    And user should enter a last name "k"
    And user should enter the email as "dheemegam"
    And user should enter a password as "Dines"
    And user should enter a confirm password "Dinesh"
    When user clicks the Register button
    But Register should fail due to both wrong email and password

  Scenario: Register should fail due to both email and password being empty
    And user should click the Gender option as male
    And user should enter a first name ""
    And user should enter a last name ""
    And user should enter the email as ""
    And user should enter a password as ""
    And user should enter a confirm password ""
    When user clicks the Register button
    But Register should fail due to both email and password being empty
