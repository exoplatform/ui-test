package org.exoplatform.selenium.platform.administration;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.exoplatform.selenium.TestLogger.info;


public class ManageLayout extends AdministrationLocator{

	
	ManageAlert alert;
	
	public ManageLayout(WebDriver dr) {
		this.driver=dr;
		alert = new ManageAlert(dr);
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
	
	/**
	 * Close the page editing form
	 */
	public void abortPageUpdate(){
		waitForAndGetElement(ELEMENT_EDIT_PORTLET_ABORT);
		click(ELEMENT_EDIT_PORTLET_ABORT);
	}
	
	/**
	 * Permission -> Access/Move apps/Edit...
	 * @param tabName
	 */
	public void gotoPermissionSelector(String tabName){
		info("Go to Permission selector");
		waitForAndGetElement(ELEMENT_PERMISSION_SELECTOR.replace("${tabName}", tabName));
		click(ELEMENT_PERMISSION_SELECTOR.replace("${tabName}", tabName));	
	}	
	
    /**
     * Check the present of Permission table
     * @param tabName
     */
	public void gotoPermissionSelectorContains(String tabName){
		info("Go to Permission selector");
		waitForAndGetElement(ELEMENT_PERMISSION_SELECTOR_CONTAINS.replace("${tabName}", tabName));
		click(ELEMENT_PERMISSION_SELECTOR_CONTAINS.replace("${tabName}", tabName));	
	}		
	
	/**
	 * Check the present of Permission table
	 * @param tabName
	 */
	public void checkPermissionTable(String tabName){		
		waitForAndGetElement(ELEMENT_PERMISSION_TABLE_COLUMN
				.replace("${tabName}", tabName)
				.replace("${columnId}", "groupId").replace("${columnLabel}", "Group ID"));
		waitForAndGetElement(ELEMENT_PERMISSION_TABLE_COLUMN
				.replace("${tabName}", tabName)
				.replace("${columnId}", "membership").replace("${columnLabel}", "Membership"));
		waitForAndGetElement(ELEMENT_PERMISSION_TABLE_COLUMN
				.replace("${tabName}", tabName)
				.replace("${columnId}", "actions").replace("${columnLabel}", "Action"));			
	}
	
	/**
	 * If public mode is checked 
	 * 							hide add button
	 * If public mode is unchecked
	 * 							show add button
	 * @param isPublicMode
	 * 					 Everyone checkbox is checked or not
	 * @param tabName
	 * 				 Access/Move Apps/Move Containers  
	 */
	public void checkPresentOfActionButtons(boolean isPublicMode, String tabName){
		if(isPublicMode){
			waitForElementNotPresent(ELEMENT_PERMISSION_ADD_USER_BUTTON.replace("${tabName}", tabName));					
		} else {
			waitForAndGetElement(ELEMENT_PERMISSION_ADD_USER_BUTTON.replace("${tabName}", tabName));					
		}
		waitForAndGetElement(ELEMENT_ACTION_BUTTON.replace("$action", "Save"));
		waitForAndGetElement(ELEMENT_ACTION_BUTTON.replace("$action", "Cancel"));	
	} 
	
	/**
	 * Uncheck Everyone checkbox
	 */
	public void uncheckPublicMode(){
		info("Uncheck on Public mode checkbox");
		uncheck(ELEMENT_PERMISSION_PUBLIC_CHECKBOX,2);
		waitForAndGetElement(ELEMENT_PERMISSION_GRID);
		info("The public mode is unchecked");
	}
	
	/**
	 * 
	 * Add permission for a portlet
	 * @param groupPath
	 *                  is group's path as: Platform/Administration,..
	 * @param membership
	 *                  is membership's name
	 * @param addedGroup
	 *                  is group's name that is added after added permission in list
	 * @param permissionSelector
	 *  				is tab name: Access/Move Apps/Move Containers   
	 * @param popupName
	 *  				is tab name: Access/Move Apps/Move Containers    
	 */
	public void addPremission(String groupPath,String membership,String addedGroup, String permissionSelector, String popupName){
		info("Click on Add Premission button");
		click(ELEMENT_PERMISSION_ADD_USER_BTN.replace("${tabName}", permissionSelector));
		waitForAndGetElement(ELEMENT_PORTLET_SELECT_PERMISSION_POPUP.replace("${tabName}", popupName));
		String[] groups=groupPath.split("/");
		for(String group:groups){
			click(ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME
														.replace("${tabName}", popupName)
														.replace("${name}",group));
			Utils.pause(2000);
		}
		if(!membership.isEmpty()){
			info("Select membership");
			click(ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME
												.replace("${tabName}", popupName)
												.replace("${name}",membership));
			Utils.pause(2000);
		}
		waitForAndGetElement(ELEMENT_PORTLET_ACCESS_PERMISSION_GROUP_NAME
											.replace("${tabName}", permissionSelector)
											.replace("${group}",addedGroup));
		info("Access group is added successfully");
	}	
	
	
	/**
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> FQA-2756:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Container Permissions
	 * 
	 * Add permission for a portlet
	 * @param groupPath
	 *                  is group's path as: Platform/Administration,..
	 * @param membership
	 *                  is membership's name
	 * @param addedGroup
	 *                  is group's name that is added after added permission in list
	 * @param permissionSelector
	 *  				is tab name: Access/Move Apps/Move Containers   
	 * @param popupName
	 *  				is tab name: Access/Move Apps/Move Containers    
<<<<<<< HEAD
=======
<<<<<<< HEAD
	 * By: QuyenNT
=======
	 * @Author: QuyenNT
>>>>>>> FQA-2756:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Container Permissions
	 * Date: Oct 30, 2015            
>>>>>>> FQA-2756:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Container Permissions
	 */
	public void addPremission(String groupPath,String membership,String addedGroup, String permissionSelector, 
								String popupName, String popupTitle){
		info("Click on Add Premission button");
		click(ELEMENT_PERMISSION_ADD_USER_BTN.replace("${tabName}", permissionSelector));
		waitForAndGetElement(ELEMENT_PORTLET_SELECT_PERMISSION_POPUP_TITLE.replace("${tabName}", popupName)
													.replace("${popupTitle}", popupTitle));
		String[] groups=groupPath.split("/");
		for(String group:groups){
			click(ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME
														.replace("${tabName}", popupName)
														.replace("${name}",group));
			Utils.pause(2000);
		}
		if(!membership.isEmpty()){
			info("Select membership");
			click(ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME
												.replace("${tabName}", popupName)
												.replace("${name}",membership));
			Utils.pause(2000);
		}
		waitForAndGetElement(ELEMENT_PORTLET_ACCESS_PERMISSION_GROUP_NAME
											.replace("${tabName}", permissionSelector)
											.replace("${group}",addedGroup));
		info("Access group is added successfully");
	}		
	
	
	/**
	 * Delete a access permission group
	 * @param group
	 *              is group path as: /platform/users,...
	 */
	public void deleteGroupPermission(String group, String permissionSelector){
		if(waitForAndGetElement(ELEMENT_DELETE_BUTTON.replace("${tabName}", permissionSelector).replace("$group",group),3000,0)!=null){
			info("Click on delete button");
			click(ELEMENT_DELETE_BUTTON.replace("${tabName}", permissionSelector).replace("$group",group));
			alert.waitForConfirmation(ELEMENT_CONFIRM_DELETE_MESSAGE);
			alert.acceptAlert();
			waitForElementNotPresent(ELEMENT_DELETE_BUTTON.replace("${tabName}", permissionSelector).replace("$group",group));
			info("Group is deleted successfully");
		}
	}

	/**
	 * @param tab: category
	 * @param element
	 *                name of application
	 * @param container
	 *                the place to put application			
	 * @param element
	 * 				name of application
	 * @param container
	 * 				the place to put application			
	 */
	public void addApplication(Object tab, Object element, By container) {
		click(ELEMENT_APPLICATION_TAB_ACTIVE);
		click(tab);
		Utils.pause(1000);
		dragAndDropToObject(element,container);
	}	
	
	/**
	 * Container permission -> Access/Move apps/Move containers
	 * @param tabName
	 */
	public void gotoContainerPermissionSelector(String tabName){
		info("Go to Container Permission selector");
		waitForAndGetElement(ELEMENT_CONTAINER_PERMISSION_SELECTOR.replace("${tabName}", tabName));
		click(ELEMENT_CONTAINER_PERMISSION_SELECTOR.replace("${tabName}", tabName));	
	}
	
	
	/**
	 * Open permission tab of a edit container popup
	 */
	public void goToPermissionContainer(Object verifiedElement ){
		info("Click on Permission tab");
		click(ELEMENT_CONTAINER_POPUP_PERMISSION_TAB);
		waitForAndGetElement(verifiedElement);
	}	
	
	/**
	 * Set permission for Move Apps 
	 * @param groupPath
	 * @param membership
	 * @param addedGroup
	 */
	public void setMoveAppsPermission(String groupPath, String membership, String addedGroup){
	
		WebElement permissionTable = waitForAndGetElement(ELEMENT_PERMISSION_TABLE
				.replace("${tabName}", ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2000,0);
	
		info("Set PERMISSION");
		if(permissionTable == null){//public mode is check
			info("Uncheck public mode");
			uncheck(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		} else {//check then uncheck to remove rows in permission table
			check(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
			uncheck(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		}
				
		info("Add permission");
		addPremission(groupPath, membership,addedGroup, 
							ELEMENT_PERMISSION_MOVE_APPS_TAB_ID, 
							ELEMENT_PERMISSION_MOVE_APPS_POPUP_TAB_ID, "Permission Selector");		
	}
	
	/**
<<<<<<< HEAD
	 * Select a container -> Set permission for Move Containers 
=======
	 *  Select a container -> Set permission for Move Containers 
>>>>>>> FQA-2756:PLF43 - Write High Fnc/PLF/Restricted Page Editor/Container Permissions
	 * @param groupPath
	 * @param membership
	 * @param addedGroup
	 */
	public void setMoveContainersPermission(String groupPath, String membership, String addedGroup){
		
		WebElement permissionTable = waitForAndGetElement(ELEMENT_PERMISSION_TABLE
				.replace("${tabName}", ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2000,0);
	
		info("Set PERMISSION");
		if(permissionTable == null){//public mode is check
			info("Uncheck public mode");
			uncheck(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
		} else {//check then uncheck to remove rows in permission table
			check(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
			uncheck(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
		}
				
		info("Add permission");
		addPremission(groupPath, membership,addedGroup, 
							ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID, 
							ELEMENT_PERMISSION_MOVE_CONTAINERS_POPUP_TAB_ID, "Permission Selector");		
	}
	
	
	/**
	 * Select a container -> Set move container permission to No-one
	 */
	public void uncheckMoveContainerPublicMode(){
			WebElement permissionTable = waitForAndGetElement(ELEMENT_PERMISSION_TABLE
					.replace("${tabName}", ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2000,0);
		
			info("Set PERMISSION");
			if(permissionTable == null){//public mode is check
				info("Uncheck public mode");
				uncheck(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
									.replace("${tabName}", ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
			} else {//check then uncheck to remove rows in permission table
				check(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
									.replace("${tabName}", ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
				uncheck(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
									.replace("${tabName}", ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);
			}
	}
	
	/**
	 * Check Everyone checkbox of Move Container
	 */
	public void checkMoveContainerPublicMode(){
		//Check permission table
		WebElement permissionTable = waitForAndGetElement(ELEMENT_PERMISSION_TABLE
				.replace("${tabName}", ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2000,0);
	
		info("Set PERMISSION");
		if(permissionTable != null){//public mode is unchecked			
			check(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
								.replace("${tabName}", ELEMENT_PERMISSION_MOVE_CONTAINERS_TAB_ID), 2);			
		}
	}	

	/**
	 * Select a container -> Set move apps permission to No-one
	 */
	public void uncheckMoveAppsPublicMode(){
		WebElement permissionTable = waitForAndGetElement(ELEMENT_PERMISSION_TABLE
				.replace("${tabName}", ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2000,0);
		info("Set no one can move app");
		if(permissionTable == null){//public mode is check
		info("Uncheck public mode");
		uncheck(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
				.replace("${tabName}", ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		} else {//check then uncheck to remove rows in permission table
		check(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
				.replace("${tabName}", ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		uncheck(ELEMENT_PERMISSION_PUBLIC_MODE_CHECKBOX
				.replace("${tabName}", ELEMENT_PERMISSION_MOVE_APPS_TAB_ID), 2);
		}
	}
	
}