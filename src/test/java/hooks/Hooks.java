package hooks;

import java.time.Duration;
import java.util.Collection;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;


public class Hooks {
	PageContext context;
	public Hooks(PageContext context) {
		this.context=context;
	}
 
	@Before
	@Given ("user should navigate to the application")
	public void beforeScenario(Scenario scenario){
		RemoteWebDriver driver = new ChromeDriver(); 
		context.setDriver(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		context.setWait(wait);
        context.getDriver().manage().window().maximize();
        context.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));   
        context.getDriver().get("https://demowebshop.tricentis.com/");
	}
	@After(order=1)
	public void afterScenario(Scenario scenario) {
		System.out.println("Runnig tear down");
		boolean failed=scenario.isFailed();
		if(failed) {
		byte[] screenShotAs=context.getDriver().getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenShotAs, "image/png");
		}
		Collection<String>	sourceTagNames=scenario.getSourceTagNames();
		boolean contian=sourceTagNames.contains("@cleanUp");
		if(contian) {
			//
		}
		context.getDriver().quit();
	}
	@After("@cleanUp")
	public void cleanData() {
		
	}
 
}
