package org.exoplatform.selenium.platform;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.locator.QuickSearchResultLocator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class QuickSearchResult extends QuickSearchResultLocator{
	
	public QuickSearchResult(WebDriver dr){
		driver = dr;
	} 
	
	/**
	 * Search a text
	 * @param textSearch
	 */
	public void search(String textSearch) {
		if (!textSearch.isEmpty()) {
	        waitForAndGetElement(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).sendKeys(textSearch);
	        Utils.pause(5000);
	        driver.findElement(ELEMENT_TOOLBAR_QUICKSEARCH_TEXTBOX).sendKeys(Keys.ENTER);
		}else assert false:"Not input a text to search";
	}

}

