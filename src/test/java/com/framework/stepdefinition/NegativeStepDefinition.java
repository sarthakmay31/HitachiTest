package com.framework.stepdefinition;

import io.cucumber.java.en.Given;

import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

import com.framework.configruler.ConfigRuler;


public class NegativeStepDefinition extends AbstractSteps{

	@Given("^user launch the url with in extension domain$")
	public void user_launch_the_url_with_in_extension_domain() throws Throwable {
		try{
			getDriver().get(ConfigRuler.fetchAsString("Application_URL_INDomain"));
		}
		catch (WebDriverException e) {
			Assert.assertNotEquals(getPageObjectManager().getHitachiHomepage().getTitile(), "Home – Hitachi Solutions");
		}

	}

}
