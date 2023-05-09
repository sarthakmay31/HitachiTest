package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.framework.utilities.CredLibrary;

public class HitachiHomePage extends CredLibrary {

	String logo_hitachi	   	    = "(//a[@class='hitachi-solutions-logo'])[1]";
	String icon_search 			= "//a[@id='open-global-search']";
	String input_searchText 	= "//input[@placeholder='What can we help you find?']";
	String btn_search			= "//button[@aria-label='search']";

	public HitachiHomePage(WebDriver webDriver) {
		super(webDriver);
	}

	public void verifyLandingOnHomepage() {
		System.out.println("Title of the page is " +getTitile());
		Assert.assertEquals(getTitile(), "Home – Hitachi Solutions");

	}

	public void clickSearchIcon() {
		clickElement(icon_search);
	}

	public void searchKeyword(String keyword) {
		setElementInputValue(input_searchText, keyword);
	}

	public void clickSearchBtn() {
		clickElement(btn_search);
	}




}
