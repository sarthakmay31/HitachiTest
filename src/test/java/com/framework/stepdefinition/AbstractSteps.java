package com.framework.stepdefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.framework.configruler.ConfigRuler;
import com.framework.pageobjectmanager.PageObjectManager;
import com.framework.seleniumadapters.DriverManager;
import com.framework.seleniumadapters.DriverManagerFactory;

public abstract class AbstractSteps {

	private static ThreadLocal<DriverManager> driverManager = new ThreadLocal<>();
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<PageObjectManager> pageObjectManager = new ThreadLocal<>();


	public void startDriver() {
		if (driverManager.get() == null)
			driverManager.set(DriverManagerFactory.getManager(ConfigRuler.fetchAsString("browser")));
		driver.set(driverManager.get().fetchDriver());
		driver.get().manage().timeouts().implicitlyWait(ConfigRuler.fetchAsInt("IMPLICIT_WAIT"), TimeUnit.SECONDS);
		if (!ConfigRuler.fetchAsString("browser").equalsIgnoreCase("chrome")) ;
		driver.get().manage().window().maximize();
		pageObjectManager.set(new PageObjectManager(driver.get()));
	}

	public void stopDriver() {
		driverManager.get().stopService();
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void closeDriver() {
		getDriver().close();
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager.get();
	}

}
