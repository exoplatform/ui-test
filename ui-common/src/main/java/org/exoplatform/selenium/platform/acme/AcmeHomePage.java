package org.exoplatform.selenium.platform.acme;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.gatein.ContentList;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class AcmeHomePage extends AcmeHomePageLocator {

	ContentList contList;
	public AcmeHomePage(WebDriver driver) {
		this.driver= driver;
		contList = new ContentList(driver);
	}

	/**
	 * Search in the quick search box on acme
	 * @param name
	 */
	public void searchQuickSearchBox(String name) {
		waitForAndGetElement(ELEMENT_TOPBAR_SEARCHBOX);
		type(ELEMENT_TOPBAR_SEARCHBOX, name, true);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		action.release();
	}
	
	/**
	 * go to news page on acme
	 */
	public void goToNews() {
		click(ELEMENT_BARMENU_NEWS);
		Utils.pause(2000);
	}
	
	/**
	 * Edit preference of Content list
	 * @param newContent
	 */
	public void editPreferenceContentList(String newContent){
		mouseOver(contList.ELEMENT_CONTENT_LIST_CONTENT_BOX, true);
		click(contList.ELEMENT_CONTENT_LIST_CONTENT_BOX_PREFERENCES_BTN );
		click(contList.ELEMENT_CONTENT_LIST_DISPLAY_SETTING_TAB);
		type(contList.ELEMENT_DISPLAY_SETTING_TAB_HEADER_FIELD, newContent, true);
		click(contList.ELEMENT_CONTENT_LIST_SAVE_BTN);
	}
	
}
