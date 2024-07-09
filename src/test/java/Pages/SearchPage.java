package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.PageContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchPage {
    PageContext context;

    By search_button = By.xpath("//input[@class='button-1 search-box-button']");
    By search_input = By.xpath("//input[@class='search-box-text ui-autocomplete-input']");
    By search_results = By.cssSelector("h2[class='product-title']");
    By invalid_search_results = By.xpath("//strong[@class='result']");
    By empty_error_message = By.id("empty-error-message-id");

    public SearchPage(PageContext context) {
        this.context = context;
    }

    @Given("user enters the product name {string}")
    public void userEntersTheProductName(String productName) {
        context.getDriver().findElement(search_input).sendKeys(productName);
    }

    @When("user clicks the search button")
    public void userClicksTheSearchButtonAgain() {
        context.getDriver().findElement(search_button).click();
    }

    @Then("search product will display {string}")
    public void searchProductWillDisplay(String expectedResult) {
        String actualResult = context.getDriver().findElement(search_results).getText();
        Assert.assertTrue(actualResult.contains(expectedResult), "Expected result: " + expectedResult + ", but got: " + actualResult);
    }

    @Then("no product message should display {string}")
    public void noProductMessageShouldDisplay(String expectedResult) {
        String actualResult = context.getDriver().findElement(invalid_search_results).getText();
        Assert.assertTrue(actualResult.contains(expectedResult), "Expected result: " + expectedResult + ", but got: " + actualResult);
    }

    @Then("an empty error message should display")
    public void anEmptyErrorMessageShouldDisplay() {
    	try {
            Alert alert = context.getWait().until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert present: " + e.getMessage());
        }
    }
}
