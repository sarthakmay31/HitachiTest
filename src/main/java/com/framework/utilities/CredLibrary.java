package com.framework.utilities;



import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import com.framework.configruler.ConfigRuler;


public class CredLibrary {

	private static final int DEFAULT_IMPLICIT_WAIT = 0;
	private FluentWait<WebDriver> fluentWait;
	private WebDriver driver;
	private Duration pollingInterval = Duration.ofMillis(ConfigRuler.fetchAsInt("POLLING_INTERVAL"));
	private Duration fluentWaitDuration = Duration.ofSeconds(ConfigRuler.fetchAsInt("FLUENT_WAIT"));
	public CredLibrary(WebDriver webDriver) {
		this.driver = webDriver;
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(fluentWaitDuration).pollingEvery(pollingInterval)
				.ignoring(StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class)
				.ignoring(NoSuchElementException.class);
		PageFactory.initElements(driver, this);
	}

	protected void clickElement(final String loc) {
		try {
			getElement(loc).click();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void setElementInputValue(String loc, String value) {
		WebElement element = getElement(loc);
		//element.clear();
		element.sendKeys(value);
	}
	public String getTitile() {
		return driver.getTitle();

	}
	protected WebElement getElement(final String locator) {
		return fluentWait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(locator));
			}
		});
	}

	protected boolean isElementDisplayed(final String locator) {
		List<WebElement> elementList=getElements(locator);

		if(!elementList.isEmpty())
		{
			return elementList.get(0).isDisplayed();				
		}
		return false;
	}
	protected List<WebElement> getElements(final String locator){
		return fluentWait.until(new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver driver){
				return driver.findElements(By.xpath(locator));
			}
		});

	}


	public int getElementsCount(final String locator) {

		if (isElementOnPage(locator)) {
			return getElements(locator).size();
		} else {
			return 0;
		}
	}

	protected boolean isElementOnPage(final String locator) {
		setImplicitWait(DEFAULT_IMPLICIT_WAIT);
		boolean flag = !getElements(locator).isEmpty();
		setImplicitWait(ConfigRuler.fetchAsInt("IMPLICIT_WAIT"));
		return flag;
	}

	protected void setImplicitWait(int duration) {
		driver.manage().timeouts().implicitlyWait(duration, TimeUnit.SECONDS);
	}



}
