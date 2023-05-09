package com.framework.pageobjectmanager;

import org.openqa.selenium.WebDriver;

import com.framework.pages.*;

public class PageObjectManager {

	private WebDriver driver;
	private HitachiHomePage hitachiHomePage;
	private SearchResultsPage searchResultsPage;


	public PageObjectManager(WebDriver driver) {
		this.driver=driver;
	}

	public HitachiHomePage getHitachiHomepage() {
		return (hitachiHomePage == null) ? hitachiHomePage = new HitachiHomePage(driver) : hitachiHomePage;

	}

	public SearchResultsPage getSearchResultsPage() {
		return (searchResultsPage == null) ? searchResultsPage = new SearchResultsPage(driver) : searchResultsPage;

	}
}
