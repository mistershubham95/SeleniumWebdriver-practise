package featureDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Additemstothecart {
	
	@Given("^User has launched the Swiggy application already$")
    public void user_has_launched_the_swiggy_application_already() throws Throwable {
		BaseClass.applaunch();
		BaseClass.validatePinCode("110017");
		BaseClass.ConfirmProductPage();
    }

    @When("^User add a product to the cart$")
    public void user_add_a_product_to_the_cart() throws Throwable {
    	BaseClass.addToCartSwiggy();
    }

    @Then("^User should able to see cart has value$")
    public void user_should_able_to_see_cart_has_value() throws Throwable {
    	BaseClass.CheckCartValue();
    }

    @Then("^User clicks on plus '(.+)' to increase the quantity$")
    public void user_clicks_on_plus_to_increase_the_quantity(String AddorRemove) throws Throwable {
        BaseClass.SwiggyalterCartValue(AddorRemove);
    }

    @And("^User should be able to see change in the quantity$")
    public void user_should_be_able_to_see_change_in_the_quantity() throws Throwable {
       BaseClass.ExamineCartValue();
    }

    @And("^User clicks on '(.+)' to clear the cart$")
    public void user_clicks_on_to_clear_cart(String clearCart) throws Throwable {
         BaseClass.SwiggyalterCartValue(clearCart);
    }

    @And("^User should be able to see change in the cart$")
    public void user_should_be_able_to_see_change_in_the_cart() throws Throwable {
       BaseClass.ExamineCartValue();
    }

	
}
