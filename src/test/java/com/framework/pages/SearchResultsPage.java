package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.framework.utilities.CredLibrary;

public class SearchResultsPage extends CredLibrary {

	String header_searchResults = "//h4[contains(text(),'Search results for')]";
	String link_totalResults 	= "//div[@class='search-result']/p/a";
	String link_firstResult		= "(//div[@class='search-result']/p/a)[1]";
	String txt_NoResult			= "//h4[contains(text(),'Sorry, your search')]";
	public SearchResultsPage(WebDriver webDriver) {
		super(webDriver);
	}

	public void verifySearchResults() {
		Assert.assertEquals(isElementDisplayed(header_searchResults),true);
		int searchResults = getElementsCount(link_totalResults);
		if(searchResults>0) {
			Assert.assertEquals(true, true);
		}
		else {
			Assert.assertEquals(true, false);
		}

	}

	public void verifyOpenSearchResults() {
		String beforetitle = getTitile();
		clickElement(link_firstResult);
		String aftertitle = getTitile();
		Assert.assertNotEquals(beforetitle, aftertitle);


	}

	public void verifyNoResults() {
		Assert.assertEquals(isElementDisplayed(txt_NoResult),true);
	}
}
