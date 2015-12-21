package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;


import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebDriver;


/**
 * Path: Administration-->Portal-->Pages
 */
public class PortalManagePages extends GateinLocator {

	
	ManageAlert alert;
	Button button;
	Dialog dialog;
	
	public PortalManagePages(WebDriver dr){
		driver = dr;
		alert = new ManageAlert(dr);
		button = new Button(driver, this.plfVersion);
		dialog = new Dialog(driver);
	} 

	
	/**
	 * Search a page
	 * @param titlePage
	 * @param siteName
	 * @param type
	 */
	public void searchPage(String title,String siteName, String type,boolean... verify){
		info("waiting the page is loaded full");
		waitForAndGetElement(ELEMENT_MANAGEPAGES_TITLE_FIELD);
		if (!title.isEmpty()) {
			info("Input a new title");
			type(ELEMENT_MANAGEPAGES_TITLE_FIELD, title, true);
		}
		if (!siteName.isEmpty()){
			info("Input a new site Name");
			type(ELEMENT_MANAGEPAGES_SITES_NAME_FIELD,siteName, true);
		}
		if(!type.isEmpty()){
			info("Select a type");
			select(ELEMENT_MANAGEPAGES_TYPE_DROPBOX, type,2);
		}
		info("Click on Search button");
		click(ELEMENT_MANAGEPAGES_SEARCH_BUTTON);
		if (verify.length>0) {
			info("Verify that the search page is shown with correct results");
			if (!title.isEmpty())
				waitForAndGetElement(
						ELEMENT_MANAGEPAGES_CONTENT_TITLE_COLUMN.replace("${title}", title), 3000,0);
			if (!siteName.isEmpty() & !type.isEmpty())
				waitForAndGetElement(
						ELEMENT_MANAGEPAGES_CONTENT_SEARCH_TABLE
								.replace("${type}", type)
								.replace("${siteName}", siteName)
								.replace("${title}", title), 2000, 0);
		}
	}

	
	/**
	 * Delete a page
	 * @param titlePage
	 */
	public void deletePage(String titlePage,String type){
		info("Delete a page");
		searchPage(titlePage,"",type);
		click(ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_DELETE);
		alert.acceptAlert();
		/*waitForAndGetElement(ELEMENT_MANGEPAGES_INFORM_POPUP_OK, 4000, 0);
		click(ELEMENT_MANGEPAGES_INFORM_POPUP_OK, 2);*/
		waitForAndGetElement(ELEMENT_MANGEPAGES_INFORM_POPUP_CLOSE, 4000, 0);
		click(ELEMENT_MANGEPAGES_INFORM_POPUP_CLOSE, 2);
		waitForElementNotPresent(ELEMENT_MANAGEPAGES_CONTENT_TITLE_COLUMN.replace("${tilte}",titlePage));
	    Utils.pause(2000);
	}
	

	
	/**
	 * Go to edit a page
	 * @param titilePage
	 */
	public void editPage(String titlePage,String type){
		info("Go to edit a page");
		searchPage(titlePage,"",type);
		info("Click on Edit button");
		click(ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_EDIT);
		Utils.pause(2000);
	}
	/**
	 * Add a new simple page
	 * @param pageName
	 * @param title
	 * @param type
	 * @param isMaxWindow
	 */
	public void addPage(String pageName,String title,String type,boolean... isMaxWindow){
		info("Click on Add new Page button");
		Utils.pause(3000);
		waitForAndGetElement(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN, DEFAULT_TIMEOUT, 1);
		click(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN);
		driver.navigate().refresh();
		if (!pageName.isEmpty()) {
			info("Input page name");
			type(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_PAGE_NAME,pageName,true);
		}
		if (!title.isEmpty()) {
			info("Input title");
			type(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TITLE,title,true);
		}
		if (!type.isEmpty()){
			info("Select a type");
			select(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TYPE_DROPBOX, type);
		}
		if(isMaxWindow.length>0){
			info("Tick on show Max Window checkbox");
			check(ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_CHECKBOX,2);
		}
		info("Save all changes");
		click(ELEMENT_MANAGEPAGES_ADD_NEW_PAGES_POPUP_SAVE_BTN);
		Utils.pause(2000);
	}
    /**
     * Open page
     * @param url
     */
	public void openPage(String url){
		info("open page:"+url);
		driver.get(url);
		Utils.pause(1000);
	}

}
