package com.framework.stepdefinition;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks extends AbstractSteps {

	@AfterStep
	public void addScreenshot(Scenario scenario) throws IOException {
		File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
		scenario.attach(fileContent, "image/png", "screenshot");
		File file = new File(System.getProperty("user.dir") + "/Reports");

	}

	@AfterClass
	public void tearDown(io.cucumber.java.Scenario scenario) throws IOException {
		closeDriver();
		if (scenario.isFailed()) {
			addScreenshot(scenario);
		}


	}


}
