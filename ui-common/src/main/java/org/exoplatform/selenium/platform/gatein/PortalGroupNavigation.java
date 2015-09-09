package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;


import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PortalGroupNavigation extends PlatformBase {

	public final String ELEMENT_ADD_NAVIGATION_BUTTON = "//*[@class='btn' and contains(text(),'Add Navigation')]";
	public final String ELEMENT_GROUP_SELECT_ADD_NAVIGATION = "//*[contains(text(),'${groupName}')]/..//a[contains(text(),'Add Navigation')]";
	public final By ELEMENT_CANCEL_BUTON = By.linkText("Cancel");
	public final String ELEMENT_GROUP_NAME = ".//*[@id='UIGroupNavigationGrid']//*[contains(text(),'${groupName}')]";
	public final String ELEMENT_DELETE_NAVIGATION_ICON = "//*[contains(text(),'${groupName}')]/../..//i[@class='uiIconTrash uiIconLightGray']";
	
	
	public final String ELEMENT_GROUP_NAVIGATION_POSITION = ".//*[@id='UIGroupNavigationGrid']//tr[${index}]//*[contains(text(),'${groupTitle}')]";
	public final String ELEMENT_EDIT_PROPERTIES_ICON = "//*[text()='${groupName}']/../..//*[@class='uiIconEditPortalConfig uiIconLightGray']";
	public final String ELEMENT_GROUP_NAVIGATION_PRIORITY = "//*[@name='priority']";
	public final String ELEMENT_SAVE_BTN = "//*[text()='Save']";
	public final String ELEMENT_EDIT_NAVIGATION = "//*[text()='${groupName}']/../..//i[@class='uiIconNavigation uiIconLightGray']";
	public final String ELEMENT_TITLE_NAVIGATION_MANAGEMENT = "//*[text()='Navigation Management']";

	ManageAlert alert;
	PortalManagePages portMg;
	public PortalGroupNavigation(WebDriver dr){
		this.driver = dr;
		alert = new ManageAlert(driver);
		portMg = new PortalManagePages(driver);
	} 
	
	
	/**function: add new navigation for group
	 * @param groupName name of group you want to add navigation
	 */
	public void addNewNavigationForGroup(String groupName){
		info("Add navigation for group " + groupName);
		click(ELEMENT_ADD_NAVIGATION_BUTTON);
		click(ELEMENT_GROUP_SELECT_ADD_NAVIGATION.replace("${groupName}", groupName));
		waitForElementNotPresent(ELEMENT_GROUP_SELECT_ADD_NAVIGATION.replace("${groupName}", groupName));
		click(ELEMENT_CANCEL_BUTON);
		waitForAndGetElement(ELEMENT_GROUP_NAME.replace("${groupName}", groupName));
	}
	
	
	/**function delete navigation for group
	 * @param groupName name of Group
	 */
	public void deleteNavigationForGroup(String groupName){
		
		info("Delete navigation of group " + groupName);
		click(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", groupName));
		alert.acceptAlert();
		waitForElementNotPresent(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", groupName));
	}
	
	/**
	 * function: Edit Priority For Group
	 * @param groupAdmin Description of group
	 * @param priority Priority you want to set for group
	 */
	public void editPriorityForGroup(String groupAdmin, String priority){
		info("Select group navigation [Administration] and click [Edit Properties]");
		click(ELEMENT_EDIT_PROPERTIES_ICON.replace("${groupName}", groupAdmin));
		info("Change priority for this group");
		select(ELEMENT_GROUP_NAVIGATION_PRIORITY, priority);
		click(ELEMENT_SAVE_BTN);
	}
	
	/**
	 * function: Go to Edit navigation
	 * 
	 * @param currentNavigation
	 */
	public void editNavigation(String currentNavigation) {
		String navigation = ELEMENT_EDIT_NAVIGATION.replace("${groupName}",
				currentNavigation);
		click(navigation);
		waitForAndGetElement(ELEMENT_TITLE_NAVIGATION_MANAGEMENT);
	}
	/**
	 * Verify Add Navigation permission
	 * @param title
	 * 				page's title
	 * @param isEnable
	 * @param groupName
	 */
	public void verifyAddNavigationPerm(String title,boolean isEnable,String groupName){
		info("verify Add Navigation permission");
		portMg.openPage(baseUrl+"/intranet/home/"+title);
		if(isEnable && groupName.length()>0){
			waitForAndGetElement(ELEMENT_ADD_NAVIGATION_BUTTON);
			click(ELEMENT_ADD_NAVIGATION_BUTTON);
			click(ELEMENT_GROUP_SELECT_ADD_NAVIGATION.replace("${groupName}", groupName));
			waitForElementNotPresent(ELEMENT_GROUP_SELECT_ADD_NAVIGATION.replace("${groupName}", groupName));
			click(ELEMENT_CANCEL_BUTON);
			waitForAndGetElement(ELEMENT_GROUP_NAME.replace("${groupName}", groupName));	
		}
	}
	/**
	 * Verify Manage Group Site
	 * @param group
	 * @param isEnable
	 */
	public void verifyManageGroupSitePerm(String group,boolean isEnable){
		info("Verify Manage Group Site");
		if(isEnable){
			waitForAndGetElement(ELEMENT_EDIT_NAVIGATION.replace("${groupName}", group));
			waitForAndGetElement(ELEMENT_EDIT_PROPERTIES_ICON.replace("${groupName}", group));
			waitForAndGetElement(ELEMENT_DELETE_NAVIGATION_ICON.replace("${groupName}", group));
		}
	}
}
