package featureDefinitions;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ServiceavailabilitySteps {


	@Given("^User launchs the Swiggy application$")
    public void user_launchs_the_swiggy_application() throws Throwable {
		BaseClass.applaunch();
	}

	@When("^User enters Pin code info '(.+)' and clicks the FindFood button$")
	public void user_enters_pin_code_info_and_clicks_the_findfood_button(String pincode) throws Throwable {
		BaseClass.validatePinCode(pincode);
	}


    @When("^User enters area/location name '(.+)' and clicks the FindFood button$")
    public void user_enters_arealocation_name_and_clicks_the_findfood_button(String locationname) throws Throwable {
    	BaseClass.validatePinCode(locationname);
    }

    @Then("^User should be able to see Products Page$")
    public void user_should_be_able_to_see_products_page() throws Throwable {
    	BaseClass.ConfirmProductPage();
    }


 
    @Then("^User should not be able to see Products Page$")
    public void user_should_not_be_able_to_see_products_page() throws Throwable {
        	assertFalse(BaseClass.ConfirmProductPage());
    }

    
 }









