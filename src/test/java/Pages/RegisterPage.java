package Pages;

import java.util.UUID;

import org.openqa.selenium.By;
import org.testng.Assert;

import base.PageContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegisterPage  {
	PageContext context;
	
	String randomEmail;
	By register_button=By.className("ico-register");
	By gender=By.id("gender-male");
	By first_name=By.id("FirstName");
	By last_name=By.id("LastName");
	By mail=By.xpath("//input[@class='text-box single-line' and @name='Email' ]");
	By password=By.cssSelector(".password");
	By confirm_password=By.xpath("(//input[@class='text-box single-line password'])[2]");
	By register_Button=By.id("register-button");
	By Assert_login_sucess=By.xpath("(//li/a[@href=\"/customer/info\" and @class='account'])[1]");
	By Assert_login_failure_email = By.xpath("//span[text()='Wrong email']");
	By Assert_login_failure_password = By.xpath("//span[text()='The password should have at least 6 characters.']");
	By Assert_login_failure_both = By.xpath("//span[text()='The password and confirmation password do not match.']");
	By Assert_login_failure_empty = By.xpath("//span[text()='Email is required.']");
	By Assert_login_failure_empty_pass =By.xpath("//span[text()='Password is required.']");
	
	public RegisterPage(PageContext context) {
		this.context=context;
	}
	@Given("user clicks on the Register button")
	public void userClicksOnTheRegisterButton() {
	    context.getDriver().findElement(register_button).click();;
	}

	@And("user should click the Gender option as male")
	public void userShouldClickTheGenderOptionAsMale() {
	   context.getDriver().findElement(gender).click();;
	}

	@And("user should enter a first name {string}")
	public void userShouldEnterAFirstName(String firstName) {
	   context.getDriver().findElement(first_name).sendKeys(firstName);
	}

	@And("user should enter a last name {string}")
	public void userShouldEnterALastName(String lastName) {
	   context.getDriver().findElement(last_name).sendKeys(lastName);
	}

	public String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString();
        return "user" + uuid + "@gmail.com";
    }

    @And("user should enter a random email")
    public void userShouldEnterARandomEmail() {
        randomEmail = generateRandomEmail();
        context.getDriver().findElement(mail).sendKeys(randomEmail);
    }
    
	@And("user should enter the email as {string}")
	public void userShouldEnterTheEmailAs(String Email) {
		context.getDriver().findElement(mail).sendKeys(Email);
	}

	@And("user should enter a password as {string}")
	public void userShouldEnterAPasswordAs(String Password) {
	    context.getDriver().findElement(password).sendKeys(Password);
	}

	@And("user should enter a confirm password {string}")
	public void userShouldEnterAConfirmPassword(String ConfirmPassword) {
	   context.getDriver().findElement(confirm_password).sendKeys(ConfirmPassword);
	}

	@When("user clicks the Register button")
	public void userShouldClickARegisterButton() {
	    context.getDriver().findElement(register_Button).click();
	}

	@Then("login should be successful")
	public void loginShouldBeSuccessful() {
	    String mail = context.getDriver().findElement(Assert_login_sucess).getText();
	    Assert.assertEquals(randomEmail, mail);
	}

	@But("login should fail due to wrong email")
	public void loginShouldFailDueToWrongEmail() {
	    Assert.assertTrue(context.getDriver().findElement(Assert_login_failure_email).isDisplayed());
	}
	
	@But("login should fail due to wrong password")
	public void loginShouldFailDueToWrongPassword() {
	    Assert.assertTrue(context.getDriver().findElement(Assert_login_failure_password).isDisplayed());
	}

	@But("login should fail due to both wrong email and password")
	public void loginShouldFailDueToBothWrongEmailAndPassword() {
	    Assert.assertTrue(context.getDriver().findElement(Assert_login_failure_both).isDisplayed());
	}

	@But("login should fail due to both email and password being empty")
	public void loginShouldFailDueToBothEmailAndPasswordBeingEmpty() {
	    Assert.assertTrue(context.getDriver().findElement(Assert_login_failure_empty).isDisplayed());
	    Assert.assertTrue(context.getDriver().findElement(Assert_login_failure_empty_pass).isDisplayed());
	}
	
	

	
}
