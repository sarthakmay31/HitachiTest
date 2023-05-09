package com.framework.cucumber;

import org.openqa.selenium.WebDriver;

public class CucumberRuntime {

	private WebDriver driver;

	public CucumberRuntime(WebDriver driver)
	{
		this.driver=driver;
	}

	private static ThreadLocal<CucumberRuntime> instances=new ThreadLocal<CucumberRuntime>();

	public static synchronized CucumberRuntime get()
	{
		return instances.get();
	}

	public void putDriver(WebDriver driver)
	{
		this.driver=driver;
	}
	public WebDriver fetchDriver() {
		return driver;
	}

	public static synchronized void put(WebDriver driver)
	{
		instances.set(new CucumberRuntime(driver));
	}

	public static synchronized void putPom(WebDriver driver)
	{
		instances.set(new CucumberRuntime(driver));
	}

}
