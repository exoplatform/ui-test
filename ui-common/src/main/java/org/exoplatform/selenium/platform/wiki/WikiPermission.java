package org.exoplatform.selenium.platform.wiki;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WikiPermission extends WikiLocators{
	
	Dialog dialog;
	Button button;
	
	/**
	 * constructor
	 * @param dr
	 */
	public WikiPermission(WebDriver dr){
		this.driver=dr;
		button = new Button(dr);
		dialog = new Dialog(dr);
	}
	/**
	 * Define permission Types
	 * as Users, Membership or Groups
	 */
	public enum userGroupTypes{
		Users_UserName,Users_Email,Users_FirstName,Users_LastName,Membership,Groups;
	}
	/**
	 * Define mode to input permission
	 * as type or select
	 */
	public enum modeInput{
		Type,Select;
	}
	
	/**
	 * Delete a group, users in permission popup
	 * @param group
	 */
	public void deletePermission(String groupUsers){
		By bDelete = By.xpath(ELEMENT_DELETE_PERMISSION.replace("$user",groupUsers));
		if (waitForAndGetElement(bDelete, 20000, 0) != null){
			info("--Delete permission--");
			click(bDelete);
			waitForElementNotPresent(bDelete);
		}
		Utils.pause(1000);
	}
	/**
	 *Define permission types 
	 *as View page or Edit page
	 */
	public enum permissionType{
		View_Pages,Edit_Pages,Admin_Pages,Admin_Wiki;
	}
	
	/**
	 * Select permission for a username/group/membership
	 * @param userGroup
	 * @param type
	 */
	public void selectPermission(String userGroup,permissionType type){
		switch(type){
		case View_Pages:
			info("Select View pages permission");
			check(ELEMENT_PERMISSION_VIEW_CHECKBOX
					.replace("$userGroup",userGroup),2);
			break;
		case Edit_Pages:
			info("Select Edit pages permission");
			check(ELEMENT_PERMISSION_EDIT_CHECKBOX
					.replace("$userGroup",userGroup),2);
			break;
		case Admin_Pages:
			info("Select View pages permission");
			check(ELEMENT_PERMISSION_ADMPAGE_CHECKBOX
					.replace("$userGroup",userGroup),2);
			break;
		case Admin_Wiki:
			info("Select View pages permission");
			check(ELEMENT_PERMISSION_ADMWIKI_CHECKBOX
					.replace("$userGroup",userGroup),2);
			break;
		}
	}
	/**
	 * Unselect permission for a username/group/membership
	 * @param userGroup
	 * @param type
	 */
	public void unSelectPermission(String userGroup,permissionType type){
		switch(type){
		case View_Pages:
			info("un Select View pages permission");
			uncheck(ELEMENT_PERMISSION_VIEW_CHECKBOX
					.replace("$userGroup",userGroup),2);
			break;
		case Edit_Pages:
			info("un Select View pages permission");
			uncheck(ELEMENT_PERMISSION_EDIT_CHECKBOX
					.replace("$userGroup",userGroup),2);
			break;
		case Admin_Pages:
			info("Select View pages permission");
			uncheck(ELEMENT_PERMISSION_ADMPAGE_CHECKBOX
					.replace("$userGroup",userGroup),2);
			break;
		case Admin_Wiki:
			info("Select View pages permission");
			uncheck(ELEMENT_PERMISSION_ADMWIKI_CHECKBOX
					.replace("$userGroup",userGroup),2);
			break;
		}
	}
	/**
	 * Add a group/user/membership to permission table by type
	 * @param groupUsers
	 */
	public void addPermisisonByType(String groupUsers){
		if(!groupUsers.isEmpty()){
			info("Type a:"+groupUsers+" to the textbox");
			type(ELEMENT_PERMISSION_TYPE_INPUT,groupUsers,true);
			info("Click on Add button");
			click(ELEMENT_PERMISSION_ADD_BUTTON);
			waitForAndGetElement(ELEMENT_DELETE_PERMISSION.replace("$user",groupUsers));
			info("The group/user/membership is added successfully");
		}
	}
	/**
	 * Open User list
	 */
	public void goToSelectUser(){
		info("Click on select user button");
		click(ELEMENT_PERMISSION_SELECT_USER);
		Utils.pause(1000);
	}
	/**
	 * Open Membership list
	 */
	public void goToSelectMemberShip(){
		info("Click on select membership button");
		click(ELEMENT_PERMISSION_SELECT_MEMBERSHIP);
		Utils.pause(1000);
	}
	/**
	 * Open Group list
	 */
	public void goToGroup(){
		info("Click on select membership button");
		click(ELEMENT_PERMISSION_SELECT_GROUP);
		Utils.pause(1000);
	}
	
	/**
	 * Add permission for a user/group/membership by selecting
	 * @param groupUsers
	 * @param membership
	 * @param type
	 * @param op
	 */
	public void addPermissionBySelect(String groupUsers,String membership,
			userGroupTypes type){
		switch(type){
		case Users_UserName:
			goToSelectUser();
			selectUser(groupUsers,filterOption.userName);
			break;
		case Users_FirstName:
			goToSelectUser();
			selectUser(groupUsers,filterOption.firstName);
			break;
		case Users_LastName:
			goToSelectUser();
			selectUser(groupUsers,filterOption.lastName);
			break;
		case Users_Email:
			goToSelectUser();
			selectUser(groupUsers,filterOption.email);
			break;
		case Membership:
			goToSelectMemberShip();
			selectMembership(groupUsers, membership);
			break;
		case Groups:
			goToGroup();
			selectGroup(groupUsers);
			break;
	    }
		info("Click on Add button");
		click(ELEMENT_PERMISSION_ADD_BUTTON);
		Utils.pause(2000);
		info("The group/user/membership is added successfully");
	}
	
	/**
	 * Click on Save button in More/Permission
	 */
	public void savePermisison(Boolean...booleans ){
		boolean savePresent = (booleans.length > 0 ? booleans[0]: true);
		info("Click on Save button");
		click(ELEMENT_PERMISSION_BUTTON_SAVE,0,true);
		if (!savePresent)
			waitForElementNotPresent(ELEMENT_PERMISSION_BUTTON_SAVE);
		Utils.pause(3000);
	}
	/**
	 * Save permission in wiki setting
	 */
	public void savePermWikiSetting(){
		info("Click on Save button");
		click(ELEMENT_PERMISSION_BUTTON_SAVE,0,true);
		click(ELEMENT_PERMISSION_BUTTON_OK,0,true);
	}
}
