package com.framework.stepdefinition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.Assert;


public class APIStepDefinition extends AbstractSteps{

	static int actualstatuscode;

	@Given("^user provide a keyword as (.+) to be searched with URI as (.+)$")
	public void user_provide_a_keyword_as_to_be_searched_with_uri_as(String keyword, String uri) throws Throwable {

		RestAssured.baseURI = uri;

		Response response = given()
				.queryParam("s", keyword)
				.queryParam("post_type", "page")
				.when()
				.post("")
				.then()
				.extract()
				.response();

		actualstatuscode = response.getStatusCode();

		System.out.println("Status code: " + actualstatuscode);

	}
	@Then("^verify stausCode as (.+)$")
	public void verify_stauscode_as(String statuscode) throws Throwable {
		Assert.assertEquals(String.valueOf(actualstatuscode),statuscode.toString());
	}

}


