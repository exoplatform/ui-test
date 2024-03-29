package org.exoplatform.selenium.platform;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.locator.PlatformPermissionLocator;
import org.openqa.selenium.WebDriver;

public class PlatformPermission extends PlatformPermissionLocator {


	public PlatformPermission(WebDriver dr){
		driver = dr;
	}

	/**
	 * Search user by key search
	 * @param keySearch
	 * @param type
	 * 				type of search
	 * 				1: search by user name
	 * 				2: search by first name
	 * 				3: search by last name
	 * 				4: search by email
	 * 				default: search by user name
	 * 
	 */
	public void searchUser(String keySearch, int type){
		type(ELEMENT_SEARCH_USER_INPUT, keySearch, true);
		switch(type){
		case 1: //search by user name
			select(ELEMENT_SELECT_SEARCH, "User Name");
			break;
		case 2: //search by first name
			select(ELEMENT_SELECT_SEARCH, "First Name");
			break;
		case 3: //search by last name
			select(ELEMENT_SELECT_SEARCH, "Last Name");
			break;
		case 4: //search by email
			select(ELEMENT_SELECT_SEARCH, "Email");
			break;
		default: //search by user name
			select(ELEMENT_SELECT_SEARCH, "User Name");
			break;

		}
		Utils.pause(500);
		click(ELEMENT_QUICK_SEARCH_BUTTON);
		//clickByJavascript(ELEMENT_QUICK_SEARCH_BUTTON);
		waitForAndGetElement((ELEMENT_USER_CHECKBOX.replace("${user}", keySearch)),5000,1,2);
	}

	/**
	 * Select user permission
	 * @param user
	 * 				list of user: john/mary
	 * @param type
	 * 				type of search
	 * 				1: search by user name
	 * 				2: search by first name
	 * 				3: search by last name
	 * 				4: search by email
	 * 				default: search by user name
	 */
	public void selectUserPermission(String user, int type){	
		String[] temp = user.split("/");
		if (temp.length > 0){
			for (int i = 0; i < temp.length; i ++){
				if(waitForAndGetElement(ELEMENT_SELECT_USER_ICON, 5000,0)!=null)
					click(ELEMENT_SELECT_USER_ICON);
				searchUser(temp[i], type);
				Utils.pause(1000);
				//check((ELEMENT_USER_CHECKBOX.replace("${user}", temp[i])), 2);
				clickByJavascript(ELEMENT_USER_CHECKBOX.replace("${user}", temp[i]), 2);
				click(ELEMENT_ADD_USERS_BUTTON);
			}
		}
	}
	/**
	 * Check display of user selector
	 * @param user
	 * @param isPresent
	 */
	public void checkUserSelector(String user,boolean isPresent){
		if(isPresent)
			waitForAndGetElement(ELEMENT_USER_LIST.replace("${user}", user));
		else
			waitForElementNotPresent(ELEMENT_USER_LIST.replace("${user}", user));
	}
	/**
	 * Select group permission
	 * @param grouppath
	 * 					path group: (Ex: Organization/Employees)
	 */
	public void selectGroupPermission(String grouppath){
		String[] temp;	
		click(ELEMENT_SELECT_GROUP_ICON);
		waitForAndGetElement(ELEMENT_SELECT_GROUP_POPUP);
		temp = grouppath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
		}
		if(waitForAndGetElement(ELEMENT_SELECT_THIS_GROUP, DEFAULT_TIMEOUT,0) != null)
			click(ELEMENT_SELECT_THIS_GROUP);
		waitForElementNotPresent(ELEMENT_SELECT_GROUP_POPUP);
	}

	/**
	 * Select group membership
	 * @param groupPath
	 * 					path group: (Ex: Organization/Employees)
	 * @param membership
	 * 					membership: (Ex:  author)
	 */
	public void selectGroupMembership(String groupPath, String membership){
		String[] temp;	
		click(ELEMENT_SELECT_MEMBERSHIP_ICON);
		waitForAndGetElement(ELEMENT_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
		}
		click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));
		waitForElementNotPresent(ELEMENT_SELECT_MEMBERSHIP_POPUP);
	}
	/**
	 * Check search result
	 * @param keySearch
	 * @param isPresent
	 * 					true if it has result
	 * 					false if it doesn't have result
	 */
	public void searchUser(String keySearch, boolean isPresent){
		type(ELEMENT_SEARCH_USER_INPUT, keySearch, true);
		Utils.pause(500);
		click(ELEMENT_QUICK_SEARCH_BUTTON);
		if(isPresent)
				waitForAndGetElement((ELEMENT_USER_CHECKBOX.replace("${user}", keySearch)),5000,1,2);
		else
				waitForElementNotPresent((ELEMENT_USER_CHECKBOX.replace("${user}", keySearch)),5000,1,2);
	}
}