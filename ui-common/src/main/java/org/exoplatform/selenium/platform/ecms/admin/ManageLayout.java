package org.exoplatform.selenium.platform.ecms.admin;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;


public class ManageLayout extends PlatformBase{
	
	public final By ELEMENT_PERMISSION_PUBLIC_CHECKBOX=By.xpath(".//*[contains(@id,'UIListPermissionSelector')]//input[@id='publicMode']");
	public final By ELEMENT_PERMISSION_GRID=By.xpath(".//*[@id='PermissionGrid']");
	
	//*================================================================================*\\
	public ManageLayout(WebDriver dr) {
		super(dr);
		// TODO Auto-generated constructor stub
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
	
	//*=========================================================EDIT/SITE/LAYOUT==============================================*\
	public final By ELEMENT_EDIT_SITE_LAYOUT_SITE_CONFIG_BTN=By.xpath(".//*[@id='UIPortalComposer']//*[contains(@class,'PageProfileIcon')]");
	public final By ELEMENT_EDIT_SITE_LAYOUT_SAVE_BTN=By.xpath(".//*[@id='UIPortalComposer']//*[contains(@class,'uiIconSave')]");
	
	//SITE CONFIG POPUP
	public final By ELEMENT_SITE_CONFIG_POPUP_PERMISSION_TAB=By.xpath(".//*[@id='UIMaskWorkspace']//*[contains(@data-target,'#PermissionSetting-tab')]");
	public final By ELEMENET_SITE_CONFIG_POPUP_SAVE_BTN=By.xpath(".//*[@id='UIPortalForm']//button[1]");
	
	
	//Edit Portlet popup
	public final By ELEMENT_PORTLET_POPUP_PERMISSION_TAB=By.xpath(".//*[@data-target='#PortletPermission-tab']");
	public final By ELEMETN_PORTLET_POPUP_SAVE_BTN=By.xpath(".//*[@id='Save']");
	
	//HOME PAGE LAYOUT
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_BREADCRUM_NAVIGATION=By.xpath(".//*[contains(@id,'LeftBreadCrumbNavigationPortlet')]//*[contains(@class,'LAYOUT-CONTAINER')]");
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_BREADCRUM_NAVIGATION_EDIT_BTN=By.xpath(".//*[contains(@id,'LeftBreadCrumbNavigationPortlet')]//*[contains(@class,'EDITION-PORTLET')]//*[contains(@class,'uiIconEdit')]");
	
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_COMPANY_NAVIGATION=By.xpath(".//*[contains(@id,'LeftNavigationPortlet')]//*[contains(@class,'LAYOUT-CONTAINER')]");
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_COMPANY_NAVIGATION_EDIT_BTN=By.xpath(".//*[contains(@id,'LeftNavigationPortlet')]//*[contains(@class,'EDITION-PORTLET')]//*[contains(@class,'uiIconEdit')]");
	
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_GROUPS_NAVIGATION=By.xpath(".//*[contains(@id,'GroupsNavigationPortlet')]//*[contains(@class,'LAYOUT-CONTAINER')]");
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_GROUPS_NAVIGATION_EDIT_BTN=By.xpath(".//*[contains(@id,'GroupsNavigationPortlet')]//*[contains(@class,'EDITION-PORTLET')]//*[contains(@class,'uiIconEdit')]");
	
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_SPACES_NAVIGATION=By.xpath(".//*[contains(@id,'SpaceNavigationPortlet')]//*[contains(@class,'LAYOUT-CONTAINER')]");
	public final By ELEMENT_HOME_PAGE_LEFT_PORTLET_SPACES_NAVIGATION_EDIT_BTN=By.xpath(".//*[contains(@id,'SpaceNavigationPortlet')]//*[contains(@class,'EDITION-PORTLET')]//*[contains(@class,'uiIconEdit')]");
	//*=============================================================*\\
	
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
	
	//*=============================================================EDIT/PAGE/EDIT LAYOUT===========================================*\

	public final By ELEMENT_PAGE_EDIT_LAYOUT_PROPERITES_BTN =By.xpath(".//*[@id='UIPageEditor']//*[contains(@class,'PageProfileIcon')]");
	public final By ELEMENT_PAGE_EDIT_LAYOUT_SAVE_BTN=By.xpath(".//*[@id='UIPageEditor']//*[contains(@class,'uiIconSave')]");
	public final By ELEMENT_PAGE_EDIT_LAYOUT_CONTAINER_TAB=By.xpath(".//*[@data-target='#contList']");
	
	//WIKI CONTAINER
	public final By ELEMENT_PAGE_EDIT_LAYOUT_WIKI_CONTAINER=By.xpath(".//*[@id='myWikiPortlet']//*[contains(@class,'LAYOUT-CONTAINER')]");
	public final By ELEMENT_PAGE_EDIT_LAYOUT_WIKI_CONTAINER_EDIT_BTN=By.xpath(".//*[@id='myWikiPortlet']//*[contains(@class,'EDITION-CONTAINER')]//*[contains(@class,'uiIconEdit')]");
	
	//EDIT CONTAINER POPUP
	public final By ELEMENT_CONTAINER_POPUP_PERMISSION_TAB=By.xpath(".//*[@data-target='#UIContainerPermission-tab']");
	public final By ELEMENT_CONTAINER_POPUP_SAVE_BTN=By.xpath(".//*[@id='UIContainerForm']//button[1]");
	
	//PROPERTIES POPUP
	public final By ELEMENT_PROPERTIES_POPUP_PERMISSION_TAB=By.xpath(".//*[@data-target='#PermissionSetting-tab']");
	public final By ELEMENT_PROPERTIES_POPUP_SAVE_BTN=By.xpath(".//*[@id='UIPageForm']//button[1]");
	
	//*===================================================================*\\
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