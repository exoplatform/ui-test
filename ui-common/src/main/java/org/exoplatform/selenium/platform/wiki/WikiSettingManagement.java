package org.exoplatform.selenium.platform.wiki;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class WikiSettingManagement extends PlatformBase{
	
	public final By ELEMENT_TEMPLATE_SEARCH_TEXTBOX = By.xpath(".//*[@id='TemplateSeachBox']");
	public final By ELEMENT_WIKI_SETTINGS_TITLE = By.xpath(".//*[@id='UIWikiSettingContainer']/h4[text()='Wiki Settings']");
	public final String ELEMENT_WIKI_SETTINGS_RESULTS = ".//*[@id='UIWikiTemplateGrid']//*[text()='${template}']";
	public final String ELEMENT_EDIT_TEMPLATE = ".//*[@id='UIWikiTemplateGrid']//*[contains(text(),'{$template}')]/../..//*[@class='uiIconEditTemplate uiIconLightGray']";
	public final String ELEMENT_DELETE_TEMPLATE = "//*[@id='UIWikiTemplateGrid']//*[contains(text(),'{$template}')]/../..//*[@class='uiIconDeleteTemplate uiIconLightGray']";
	public final By ELEMENT_TITLE_TEMPLATE = By.id("titleInput");
	public final By ELEMENT_SAVE_TEMPLATE = By.id("UISubmitToolBarUpper_SaveTemplate_");
	public final By ELEMENT_WIKI_SETTINGS_PERMISSION = By.xpath(".//*[@id='myTab']//*[contains(text(),'Permission')]");
	public final By ELEMENT_WIKI_SETTINGS_PERMISSION_SELECT_USER_ICON = By.xpath("//*[@class='uiIconUser uiIconLightGray']");
	public final By ELEMENT_WIKI_SETTINGS_PERMISSION_INPUT_SEARCH_USER_NAME = By.xpath(".//input[@id='Quick Search']");
	public final String ELEMENT_WIKI_SETTINGS_PERMISSION_SELECT_SEARCH_OPTION = "//*[contains(@name,'filter')]";
	public final String ELEMENT_WIKI_SETTINGS_PERMISSION_SEARCH_ICON = ".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch uiIconLightGray')]";
	public final String ELEMENT_WIKI_SETTINGS_PERMISSION_USER_NAME_IN_USER_LIST=".//*[@id='UIListUsers']//*[contains(text(),'$userName')]";
	public final By ELEMENT_WIKI_SETTINGS_CLOSE_USER_SELETOR = By.xpath(".//*[@id='UIUserSelector']//*[contains(text(),'Close')]");
	public final String ELEMENT_WIKI_SETTING_PERMISSION_SEARCH_GROUP_USER_IN_TABLE = "//*[@id='UIListUsers']//span[contains(text(),'${username}')]";
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
