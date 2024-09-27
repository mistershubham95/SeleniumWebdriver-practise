package featureDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/feature"},
			glue = {"featureDefinitions"},
		monochrome=true,		// enable console output
		plugin = {"pretty",
					"html:test-output/cucumber.html",
					"json:target/cucumber.json",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		
		 //tags = "@PositiveTest"		// Cucumber 6 syntax
		//tags = "@AddSingleProduct or @ChangeQuantityofProducts or @clearCart" 
		)

public class TestRunner {

}


