package com.framework.cucumber;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@Listeners(com.framework.listeners.TestListener.class)
public abstract class AbstractTestNGCucumberTest {

	private TestNGCucumberRunner testNGCucumberRunner;



	@BeforeClass(alwaysRun = true)
	public void setUpClass()
	{
		testNGCucumberRunner= new TestNGCucumberRunner(this.getClass());
	}


	@Test(groups = "cucumber", description = "Run Cucumber Scenarios",dataProvider = "scenarios")
	public void runScenariod(PickleWrapper picklekWrapper, FeatureWrapper featureWrapper) throws Throwable{
		testNGCucumberRunner.runScenario(picklekWrapper.getPickle());
	}

	@DataProvider
	public Object[] scenarios() {
		if(testNGCucumberRunner==null)
		{
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if(testNGCucumberRunner==null)
		{
			return;
		}
		testNGCucumberRunner.finish();
	}
}
