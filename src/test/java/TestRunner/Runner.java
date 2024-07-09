package TestRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
  features = "src/test/java/features", 
  dryRun=false,
  glue = {"hooks","Pages"},		
  snippets=SnippetType.CAMELCASE,
  monochrome = true,
  plugin =  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
  //plugin= {"pretty","html:reports","json:reports/result.json","junit:reports/result.xml"},
  //tags= {"@smoke and @regression"}
)
public class Runner extends AbstractTestNGCucumberTests {
    // This class will be empty
}
