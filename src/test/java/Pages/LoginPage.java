package Pages;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.By;
import org.testng.Assert;

import base.PageContext;
import cucumber.api.java.en.And;

public class LoginPage {
	PageContext context;
	
	By remember_me=By.xpath("//input[@name='RememberMe' and @id='RememberMe']");
	By Pass=By.cssSelector("input[id='Password']");
	By mail=By.cssSelector("input[id='Email']");
	By login_btn=By.className("ico-login");
	By login_login_btn=By.cssSelector(".login-button");
	By login_sucess=By.xpath("//a[@class='account' and  text()='dheemegam002@gmail.com']");
	By wrong_email_password=By.xpath("//ul/li[text()='No customer account found']");
	By wrong_email=By.xpath("//span[@class='field-validation-error']/span");
	By wrong_password=By.xpath("//ul/li[text()='The credentials provided are incorrect']");
	
	public LoginPage(PageContext context) {
		this.context=context;
	}
	@Given("user clicks on the Login button")
	public void userClicksOnTheLoginButton() {
		context.getDriver().findElement(login_btn).click();
	}

	@And("user should enter a email {string}")
	public void userShouldEnterAEmail(String Email) {
		context.getDriver().findElement(mail).sendKeys(Email);
	}

	@And("user should enter a password {string}")
	public void userShouldEnterAPassword(String Password) {
		context.getDriver().findElement(Pass).sendKeys(Password);
	}
	
	@And("user should clicks a remember me check box")
	public void userShouldClicksARememberMeCheckBox() {
	    context.getDriver().findElement(remember_me).click();;
	}	

	@And("user should click the login button")
	public void userShouldClickTheLoginButton() {
		context.getDriver().findElement(login_login_btn).click();
	}
	
	@And("user should login into the account")
	public void userShouldLoginIntoTheAccount() {
		String LoginSucess=context.getDriver().findElement(login_sucess).getText();
		Assert.assertEquals("dheemegam002@gmail.com",LoginSucess); 
	}

	@And("user should fail to login into the account due to wrong email and password")
	public void userShouldFailToLoginIntoTheAccountDueToWrongEmailAndPassword() {
		Assert.assertTrue(context.getDriver().findElement(wrong_email_password).isDisplayed()); 
	}

	@And("user should fail to login into the account due to wrong email")
	public void userShouldFailToLoginIntoTheAccountDueToWrongEmail() {
		Assert.assertTrue(context.getDriver().findElement(wrong_email).isDisplayed()); 
	}

	@And("user should fail to login into the account due to wrong password")
	public void userShouldFailToLoginIntoTheAccountDueToWrongPassword() {
		Assert.assertTrue(context.getDriver().findElement(wrong_password).isDisplayed()); 
	}

	@And("user should fail to login into the account due to both email and password as empty")
	public void userShouldFailToLoginIntoTheAccountDueToBothEmailAndPasswordAsEmpty() {
		Assert.assertTrue(context.getDriver().findElement(wrong_email_password).isDisplayed()); 
	}

}
