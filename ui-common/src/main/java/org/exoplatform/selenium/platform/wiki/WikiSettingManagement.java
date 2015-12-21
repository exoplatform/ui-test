package org.exoplatform.selenium.platform.wiki;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class WikiSettingManagement extends WikiLocators{
	
	
	/**
	 * constructor
	 * @param dr
	 */
	ManageAlert alert ;
	public WikiSettingManagement(WebDriver dr){
		this.driver=dr;
		alert= new ManageAlert(dr);
	}
	
	/**
	 * Search a template
	 * @param template
	 */
	public void searchTemplate(String template){
		info("Input a template's name");
		type(ELEMENT_TEMPLATE_SEARCH_TEXTBOX, template, true);
		info("Press Enter key");
		driver.findElement(ELEMENT_TEMPLATE_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);
		info("Verify that the search results is shown that matchs with keyword");
		waitForAndGetElement(ELEMENT_WIKI_SETTINGS_RESULTS.replace("${tempalte}",template),3000,0);
	}
	
	/**
	 * Edit a wiki template
	 * @param template
	 * @param text
	 */
	public void editTemplate(String template,String title, String subTitle, String text){
		click(By.xpath(ELEMENT_EDIT_TEMPLATE.replace("{$template}",template)));
		if(title!=""){
			type(ELEMENT_TITLE_TEMPLATE,title,true);
		}
			
		click(ELEMENT_SAVE_TEMPLATE);
	}
	
	public void deleteTemplate(String template){
		info("Delete template "+template);
		click(By.xpath(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template)));
		alert.acceptAlert();
		waitForElementNotPresent(By.xpath(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template)));
	}
	
	/**
	 * Go to Wiki Setting Permission page
	 */
	public void goToWikiSettingPermission(){
		info("-- Go to wiki home page --");
		click(ELEMENT_WIKI_SETTINGS_PERMISSION);
	}
	
	/**
	 * Go to User Selector page
	 */
	public void gotoUserSelector(){
		info("-- Go to wiki home page --");
		click(ELEMENT_WIKI_SETTINGS_PERMISSION_SELECT_USER_ICON);
	}
	

	/**
	 * function: Search user in Wiki Settings Permission Page
	 * 
	 * @param user
	 *            (Can be: User name, Last name, First name or Email of the user
	 *            you want to search)
	 * @param searchOption
	 *            (Can be: User name, Last name, First name or Email option
	 *            corresponding with information you input in "Search")
	 */
	public void searchUser(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_WIKI_SETTINGS_PERMISSION_INPUT_SEARCH_USER_NAME, user, true);
		select(ELEMENT_WIKI_SETTINGS_PERMISSION_SELECT_SEARCH_OPTION, searchOption);
		click(ELEMENT_WIKI_SETTINGS_PERMISSION_SEARCH_ICON);
		waitForTextPresent(user);
	}
	
	public void searchUserNotPresent(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_WIKI_SETTINGS_PERMISSION_INPUT_SEARCH_USER_NAME, user, true);
		select(ELEMENT_WIKI_SETTINGS_PERMISSION_SELECT_SEARCH_OPTION, searchOption);
		click(ELEMENT_WIKI_SETTINGS_PERMISSION_SEARCH_ICON);
		waitForTextNotPresent(user);
	}
	
	/**
	 * Verify that the user is shown in the user list
	 * @param userName
	 *                 is user-name of the user
	 */
	public void verifyUserPresent(String userName){
		info("---Verify that the user is shown in the table");
		waitForAndGetElement(ELEMENT_WIKI_SETTINGS_PERMISSION_USER_NAME_IN_USER_LIST.replace("$userName",userName));
		info("The user is shown in the table");
	}
	
	/**
	 * Close User Selector page
	 */
	public void closeUserSelector(){
		info("-- Go to User Selector page --");
		click(ELEMENT_WIKI_SETTINGS_CLOSE_USER_SELETOR);
		Utils.pause(2000);
	}
}
