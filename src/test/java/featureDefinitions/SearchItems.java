package featureDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchItems {
	
	@Given("^User has already launched the Swiggy application$")
    public void user_has_already_launched_the_swiggy_application() throws Throwable {
		BaseClass.applaunch();
		BaseClass.validatePinCode("South Delhi");
		BaseClass.ConfirmProductPage();
    }

    @When("^User enter '(.+)' in the search bar$")
    public void user_enter_in_the_search_bar(String searchitems) throws Throwable {
    	BaseClass.SearchingFoodItems(searchitems);
    }

    @Then("^Will confirm how many restaurants are having this item '(.+)'$")
    public void will_confirm_how_many_restaurants_are_having_this_item_(String searchitems) throws Throwable {
         BaseClass.NumberofSearchingFoodItems(searchitems);
    }
}
