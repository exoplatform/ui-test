package org.exoplatform.selenium.platform.gatein;
import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebDriver;

public class EditorPortlet extends GateinLocator {
	
	ManageAlert alert;
	
	public EditorPortlet(WebDriver dr){
		driver = dr;
		alert = new ManageAlert(dr);
	}
	/**
	 * Open Access Permission tab
	 */
	public void goToAccessPermissionTab(){
		info("Click on Access Permission tab");
		click( ELEMENT_PORTLET_ACCESS_PERMISSION_TAB);
	}
	
	/**
	 * Delete a access permission group
	 * @param group
	 *              is group path as: /platform/users,...
	 */
	public void deleteGroupPermission(String group){
		if(waitForAndGetElement(ELEMENT_PORTLET_ACCESS_PERMISSION_DELETE_GROUP_BTN.replace("$group",group),3000,0)!=null){
			info("Click on delete button");
			click(ELEMENT_PORTLET_ACCESS_PERMISSION_DELETE_GROUP_BTN.replace("$group",group));
			alert.waitForConfirmation(ELEMENT_CONFIRM_DELETE_MESSAGE);
			alert.acceptAlert();
			waitForElementNotPresent(ELEMENT_PORTLET_ACCESS_PERMISSION_DELETE_GROUP_BTN.replace("$group",group));
			info("Group is deleted successfully");
		}
	}
	/**
	 * Add permission for a portlet
	 * @param groupPath
	 *                  is group's path as: Platform/Administration,..
	 * @param membership
	 *                  is membership's name
	 * @param addedGroup
	 *                  is group's name that is added after added permission in list
	 */
	public void addPremission(String groupPath,String membership,String addedGroup){
		info("Click on Add Premission button");
		click(ELEMENT_PORTLET_ACCESS_PERMISSION_ADD_BTN);
		waitForAndGetElement(ELEMENT_PORTLET_SELECT_PERMISSION_POPUP);
		String[] groups=groupPath.split("/");
		for(String group:groups){
			click(ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME.replace("$name",group));
			Utils.pause(2000);
		}
		if(!membership.isEmpty()){
			info("Select membership");
			click(ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME.replace("$name",membership));
			Utils.pause(2000);
		}
		waitForAndGetElement(ELEMENT_PORTLET_ACCESS_PERMISSION_GROUP_NAME.replace("$group",addedGroup));
		info("Access group is added successfully");
	}
	/**
	 * Save or close all changes
	 * @param isSave
	 */
	public void saveCloseChange(Boolean isSave){
		if(isSave){
			info("Click on Save button");
			click(ELEMENT_EDIT_PORTLET_FORM_SAVE_BUTTON);
		}else{
			info("Click on Close button");
			click(ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON);
		}
		waitForElementNotPresent(ELEMENT_EDIT_PORTLET_FORM);
		info("Save or Close successfully");
	}
	
}
