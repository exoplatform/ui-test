package org.exoplatform.selenium.platform.administration;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;


public class ManageLayout extends AdministrationLocator{
	
	public ManageLayout(WebDriver dr) {
		this.driver=dr;
	}
	
	/**
	 * Public mode of a Site
	 */
	public void publicMode(){
		info("Check on Public mode checkbox");
		check(ELEMENT_PERMISSION_PUBLIC_CHECKBOX,2);
		waitForElementNotPresent(ELEMENT_PERMISSION_GRID);
		info("The public mode is checked");
	}
	
	/**
	 * Open Site config popup
	 */
	public void goToSiteConfigPopup(){
		info("Click on Site Config button");
		click(ELEMENT_EDIT_SITE_LAYOUT_SITE_CONFIG_BTN);
		waitForAndGetElement(ELEMENT_SITE_CONFIG_POPUP_PERMISSION_TAB);
		info("The popup is shown");
	}

	/**
	 * Open Permission tab on Site config popup
	 */
	public void goToSitePermissionTab(){
		info("Click on Permission tab");
		click(ELEMENT_SITE_CONFIG_POPUP_PERMISSION_TAB);
		waitForAndGetElement(ELEMENT_PERMISSION_GRID);
		info("The permission tab's content is shown");
	}
	/**
	 * Save all changes of Site config popup
	 */
	public void saveChangesSiteConfig(){
		info("Click on Save button");
		click(ELEMENET_SITE_CONFIG_POPUP_SAVE_BTN);
		waitForElementNotPresent(ELEMENT_SITE_CONFIG_POPUP_PERMISSION_TAB);
		info("All changes are saved");
	}
	/**
	 * Save all changes of edit portlet popup
	 */
	public void saveChangesPortletPopup(){
		info("Click on Save button");
		click(ELEMETN_PORTLET_POPUP_SAVE_BTN);
		waitForElementNotPresent(ELEMETN_PORTLET_POPUP_SAVE_BTN);
		info("All changes are saved");
	}
	
	/**
	 * Define portles of Home page site
	 */
	public enum homePortletName{
		Breadcrumbs,Company,Groups,Space;
	}
	/**
	 * Open permission tab of edit portlet popup
	 */
	public void goToPortletPermissionTab(){
		info("Click on Permission tab");
		click(ELEMENT_PORTLET_POPUP_PERMISSION_TAB);
		waitForAndGetElement(ELEMENT_PERMISSION_GRID);
		info("The permission tab's content is shown");
	}
	/**
	 * public modes for portlet of Home page sites
	 * @param portlet
	 */
	public void publicModePortlet(homePortletName portlet){
		switch(portlet){
		case Breadcrumbs:
			info("Mouse over on the portlet");
			mouseOver(ELEMENT_HOME_PAGE_LEFT_PORTLET_BREADCRUM_NAVIGATION,true);
			info("Click on Edit button");
			click(ELEMENT_HOME_PAGE_LEFT_PORTLET_BREADCRUM_NAVIGATION_EDIT_BTN);
			break;
		case Company:
			info("Mouse over on the portlet");
			mouseOver(ELEMENT_HOME_PAGE_LEFT_PORTLET_COMPANY_NAVIGATION,true);
			info("Click on Edit button");
			click(ELEMENT_HOME_PAGE_LEFT_PORTLET_COMPANY_NAVIGATION_EDIT_BTN);
			break;
		case Groups:
			info("Mouse over on the portlet");
			mouseOver(ELEMENT_HOME_PAGE_LEFT_PORTLET_GROUPS_NAVIGATION,true);
			info("Click on Edit button");
			click(ELEMENT_HOME_PAGE_LEFT_PORTLET_GROUPS_NAVIGATION_EDIT_BTN);
			break;
		case Space:
			info("Mouse over on the portlet");
			mouseOver(ELEMENT_HOME_PAGE_LEFT_PORTLET_SPACES_NAVIGATION,true);
			info("Click on Edit button");
			click(ELEMENT_HOME_PAGE_LEFT_PORTLET_SPACES_NAVIGATION_EDIT_BTN);
			break;
		}
		goToPortletPermissionTab();
		publicMode();
		saveChangesPortletPopup();
	}
	
	/**
	 * Save all changes of the layout
	 */
	public void saveChangesSiteLayout(){
		info("click on Finish button of the layout");
		click(ELEMENT_EDIT_SITE_LAYOUT_SAVE_BTN);
		waitForElementNotPresent(ELEMENT_EDIT_SITE_LAYOUT_SAVE_BTN);
		info("All changes are saved");
	}
	
	/**
     * Open Page properties popup
     */
	public void goToPagePropertiesPopup(){
		info("Click on Properties button");
		click(ELEMENT_PAGE_EDIT_LAYOUT_PROPERITES_BTN);
		waitForAndGetElement(ELEMENT_PAGE_EDIT_LAYOUT_SAVE_BTN);
		info("The popup is shown");
	}
	/**
	 * Open page permission tab
	 */
	public void goToPagePermissionTab(){
		info("Click on Permission tab");
		click(ELEMENT_PROPERTIES_POPUP_PERMISSION_TAB);
		waitForAndGetElement(ELEMENT_PERMISSION_GRID);
		info("The permission tab's content is shown");
	}
	/**
	 * Save all changes of Page layout
	 */
	public void saveChangesPageLayout(){
		info("Click on Save button");
		click(ELEMENT_PAGE_EDIT_LAYOUT_SAVE_BTN);
		waitForElementNotPresent(ELEMENT_PAGE_EDIT_LAYOUT_SAVE_BTN);
		info("All changes are saved");
	}
	/**
	 * Save all changes of properties popup
	 */
	public void saveChangesPropertiesPopup(){
		info("Click on Save button");
		click(ELEMENT_PROPERTIES_POPUP_SAVE_BTN);
		waitForElementNotPresent(ELEMENT_PROPERTIES_POPUP_SAVE_BTN);
		info("All changes are saved");
	}
	/**
	 * Open container tab
	 */
	public void goToContainerTab(){
		info("Click on Container tab");
		click(ELEMENT_PAGE_EDIT_LAYOUT_CONTAINER_TAB);
		Utils.pause(2000);
	}
	/**
	 * Save all changes of Container popup
	 */
	public void saveChangesContainerPopup(){
		info("Click on Save button");
		click(ELEMENT_CONTAINER_POPUP_SAVE_BTN);
		waitForElementNotPresent(ELEMENT_CONTAINER_POPUP_SAVE_BTN);
		info("All changes are saved");
	}
	/**
	 * Open permission tab of a edit container popup
	 */
	public void goToPermissionContainer(){
		info("Click on Permission tab");
		click(ELEMENT_CONTAINER_POPUP_PERMISSION_TAB);
		waitForAndGetElement(ELEMENT_PERMISSION_GRID);
	}
	/**
	 * Public mode the container of Wiki portlet
	 */
	public void publicModeWikiPortletContainer(){
		goToContainerTab();
		info("Mouse over on the container");
		mouseOver(ELEMENT_PAGE_EDIT_LAYOUT_WIKI_CONTAINER,true);
		info("Click on Edit button");
		click(ELEMENT_PAGE_EDIT_LAYOUT_WIKI_CONTAINER_EDIT_BTN);
		info("The container popup is shown");
		goToPermissionContainer();
		publicMode();
	}
	
}