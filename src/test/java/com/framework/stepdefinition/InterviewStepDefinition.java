package com.framework.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import com.framework.configruler.ConfigRuler;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;


public class InterviewStepDefinition extends AbstractSteps{
	/*
	 * Step Definition for TC1
	 */
	@Given("^user launch the url in browser$")
	public void user_launch_the_url_in_browser() throws Throwable {
		startDriver();
		getDriver().get(ConfigRuler.fetchAsString("Application_URL"));
	}

	@Then("^verify user is landed on hitachi solutions homepage$")
	public void verify_user_is_landed_on_hitachi_solutions_homepage() throws Throwable {
		getPageObjectManager().getHitachiHomepage().verifyLandingOnHomepage();
	}
	/*
	 * Step Definition for TC2
	 */


	@And("^clicks on the search icon$")
	public void clicks_on_the_search_icon() throws Throwable {
		getPageObjectManager().getHitachiHomepage().clickSearchIcon();
	}

	@And("^provide a keyword as (.+) to be searched$")
	public void provide_a_keyword_as_to_be_searched(String keyword) throws Throwable {
		getPageObjectManager().getHitachiHomepage().searchKeyword(keyword);
	}


	@When("^user clicks on search button$")
	public void user_clicks_on_search_button() throws Throwable {
		getPageObjectManager().getHitachiHomepage().clickSearchBtn();
	}


	@Then("^verify search results are displayed$")
	public void verify_search_results_are_displayed() throws Throwable {
		getPageObjectManager().getSearchResultsPage().verifySearchResults();
	}

	/*
	 * StepDefinition for TC3
	 */

	@And("^verify user can successfully open returned search results$")
	public void verify_user_can_successfully_open_returned_search_results() throws Throwable {
		getPageObjectManager().getSearchResultsPage().verifyOpenSearchResults();
	}

	/*
	 * TC5 Negative
	 */

	@Then("^verify no search result message is displayed$")
	public void verify_no_search_result_message_is_displayed() throws Throwable {
		getPageObjectManager().getSearchResultsPage().verifyNoResults();
	}
}
