package com.framework.listeners;

import java.util.HashMap;
import java.util.Map;

import java.util.logging.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener{



	private static Logger logger=Logger.getLogger(TestListener.class.getName());
	private Map<String, String> allParameters=new HashMap<>();
	private Map<String, String> suitParameters=new HashMap<>();
	private Map<String, String> localParameters=new HashMap<>();
	public static String fetchTestMethodName(ITestResult iTestResult)
	{
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}


	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("Test suite execution started");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		logger.info(iTestResult.getName() + "passed successfully");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		logger.warning(fetchTestMethodName(iTestResult)+"Failed");
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		logger.info("");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		logger.info("");
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		allParameters=iTestContext.getSuite().getXmlSuite().getAllParameters();
		suitParameters=iTestContext.getSuite().getXmlSuite().getParameters();
		localParameters=iTestContext.getCurrentXmlTest().getLocalParameters();

	}

	public Map<String, String> fetchAllParameters()
	{
		return allParameters;
	}

	public Map<String, String> fetchSuiteParameters()
	{
		return suitParameters;
	}

	public Map<String, String> fetchLocalParameters()
	{
		return localParameters;
	}




	@Override
	public void onFinish(ITestContext context) {

	}
}
