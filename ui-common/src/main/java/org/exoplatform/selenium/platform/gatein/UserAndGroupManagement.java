package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import junit.framework.Assert;

import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class UserAndGroupManagement extends GateinLocator {
	
	Dialog dialog;
	ManageAlert alert;
	public UserAndGroupManagement(WebDriver dr) {
		this.driver = dr;
		alert = new ManageAlert(dr);
		dialog = new Dialog(dr);
	}
	/**
	 * Select users tab
	 * function: Choose Users Tab
	 */
	public void goToUsersTab() {
		info("-- Choose Users Management tab--");
		click(ELEMENT_USER_MANAGEMENT_TAB,0,true);
		waitForAndGetElement(ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);
		Utils.pause(2000);
	}
	/**
	 * Select group management tab
	 * function: Choose Group Tab
	 */
	public void goToGroupTab() {
		info("-- Choose Group Management tab--");
		click(ELEMENT_GROUP_MANAGEMENT_TAB);
		waitForAndGetElement(ELEMENT_GROUP_MANAGEMENT_INFO);
		Utils.pause(2000);
	}
	/**
	 * Select membership management tab
	 * function: Choose MemberShip Tab
	 */
	public void goToMembershipTab() {
		info("-- Choose Membership Management tab--");
		Utils.pause(500);
		click(ELEMENT_TAB_MEMBERSHIP_MANAGEMENT);
		waitForAndGetElement(ELEMENT_MEMBERSHIP_MANAGEMENT_GRID);
		Utils.pause(2000);
	}
	
	/**
	 * Select a group by array
	 * 
	 * @param arrayGroupPath
	 */
	public void selectGroup(String[] arrayGroupPath) {
		info("Select a group in the list");
		for (String group : arrayGroupPath) {
			info("Select a group:" + group);
			click(ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", group));
		}
		Utils.pause(2000);
	}
	
	/**
	 * Select a group in permission selector popup by string
	 * @param groupsPath is path of groups as:Platform/Content Manangement
	 */
	public void selectGroup(String groupsPath){
		info("Select a group with the path:"+groupsPath);
		String[] groups = groupsPath.split("/");
		for(String groupSelect: groups){
			info("Select group:"+groupSelect);
			click(ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}", groupSelect));
		}
		Utils.pause(2000);
	}
	/**
	 * function: Add new group
	 * 
	 * @param groupName
	 *            name of Group
	 * @param groupLabel
	 *            label of Group
	 * @param groupDesc
	 *            description of Group
	 * @param verify
	 *            (True if you want to verify new group created successfully)
	 */
	public void addGroup(String groupName, String groupLabel, String groupDesc, boolean verify){
		info("--Add a new group--");
		Utils.pause(500);
		click(ELEMENT_GROUP_ADD_NEW_ICON);
		inputDataGroup(groupName, groupLabel, groupDesc);
		click(ELEMENT_SAVE_BUTTON);
		if (verify && groupLabel != null && groupLabel != "") {
			waitForAndGetElement(ELEMENT_GROUP_ADDED.replace("${groupLabel}",
					groupLabel));
		}
	}
	/**
	 * Add a user to Administration group
	 * @param user
	 * @param membership
	 */
	public void addUserAdmin(String user,String... membership){
		info("Go to Group tab");
		goToGroupTab();
		scrollToBottomPage(this.driver);
		info("Select Platform/Administration group");
		selectGroup("Platform/Administration");
		info("Add user to Administration group by type");
		type(ELEMENT_INPUT_USERNAME,user, true);
		if(membership.length>0)
		  select(ELEMENT_SELECT_MEMBERSHIP, membership[0]);
		scrollToElement(waitForAndGetElement(ELEMENT_SAVE_BUTTON_2),this.driver);
		click(ELEMENT_SAVE_BUTTON_2);
		String addedUser = ELEMENT_ADDED_GROUP_USER_IN_TABLE.replace(
				"${username}", user);
		if (isTextPresent(ELEMENT_MSG_TOTAL_PAGES)) {
			usePaginator(addedUser,
					ELEMENT_USER_NOT_FOUND.replace("${user}", user));
		} else {
			waitForAndGetElement(addedUser);
		}
		info("User is added to Administration group");
	}
	
	/**
	 * Add a user to content management group
	 * @param user
	 * @param membership
	 */
	public void addUserContentManagement(String user,String... membership){
		info("Go to Group tab");
		goToGroupTab();
		scrollToBottomPage(this.driver);
		info("Select Platform/Content Management group");
		selectGroup("Platform/Content Management");
		info("Add user to Content Management group by type");
		scrollToBottomPage(this.driver);
		type(ELEMENT_INPUT_USERNAME,user, true);
		if(membership.length>0)
		  select(ELEMENT_SELECT_MEMBERSHIP, membership[0]);
		scrollToBottomPage(this.driver);
		click(ELEMENT_SAVE_BUTTON_2);
		String addedUser = ELEMENT_ADDED_GROUP_USER_IN_TABLE.replace(
				"${username}", user);
		if (isTextPresent(ELEMENT_MSG_TOTAL_PAGES)) {
			usePaginator(addedUser,
					ELEMENT_USER_NOT_FOUND.replace("${user}", user));
		} else {
			waitForAndGetElement(addedUser);
		}
		info("User is added to Content Managment group");
	}

	/**
	 * function: Input data to create a new group
	 * 
	 * @param groupName
	 *            name of Group
	 * @param groupLabel
	 *            label of Group
	 * @param groupDesc
	 *            description of Group
	 */
	public void inputDataGroup(String groupName, String groupLabel,
			String groupDesc) {
		if (groupName != null) {
			if (waitForAndGetElement(ELEMENT_INPUT_GROUP_NAME, 5000, 0) != null) {
				type(ELEMENT_INPUT_GROUP_NAME, groupName, true);
			}
		}
		if (groupLabel != null) {
			type(ELEMENT_INPUT_LABEL, groupLabel, true);
		}
		if (groupDesc != null) {
			type(ELEMENT_TEXTAREA_DESCRIPTION, groupDesc, true);
		}
	}

	/**
	 * function: Edit group
	 * 
	 * @param oldName
	 *            old name of Group
	 * @param groupName
	 *            name of Group
	 * @param groupLabel
	 *            label of Group
	 * @param groupDesc
	 *            description of Group
	 * @param verify
	 *            (True if you want to verify new group edited successfully)
	 */
	public void editGroup(String oldName, String groupName, String groupLabel, String groupDesc, boolean verify){
		info("-- Edit group: " + groupName + "--");
		click(ELEMENT_GROUP_EDIT_ICON);
		Utils.pause(1000);
		inputDataGroup(groupName, groupLabel, groupDesc);
		click(ELEMENT_SAVE_BUTTON);
		if (verify) {
			waitForAndGetElement(ELEMENT_GROUP_ADDED.replace("${groupLabel}",
					groupLabel));
		}
	}

	/**
	 * function: Add user to Group
	 * 
	 * @param userNames
	 *            name of user you want to add to group
	 * @param memberShip
	 *            membership of user in group
	 * @param select
	 *            (True: if you want to search user by selecting user / False:
	 *            If you want to search user by typing user name)
	 * @param verify
	 *            (True: if you want to verify new user added successfully)
	 */
	public void addUsersToGroup(String userNames, String memberShip, boolean select, boolean verify) {
		info("--Adding users to group--");
		String[] users = userNames.split(",");
		if (select) {
			if (isElementPresent(ELEMENT_GROUP_SEARCH_USER_ICON)) {
				click(ELEMENT_GROUP_SEARCH_USER_ICON);
			} else if (isElementPresent(By
					.xpath(ELEMENT_GROUP_SEARCH_USER_ICON_2))) {
				click(By.xpath(ELEMENT_GROUP_SEARCH_USER_ICON_2));
			}
			waitForTextPresent(ELEMENT_MSG_SELECT_USER);
			for (String user : users) {
				searchUserInGroupManagement(user, ELEMENT_USER_NAME, true);
				check(ELEMENT_CHECK.replace("${user}", user), 2);
			}
			click(ELEMENT_ADD_BUTTON);
			Utils.pause(500);
			Assert.assertEquals(getValue(ELEMENT_INPUT_USERNAME), userNames);
		} else {
			type(ELEMENT_INPUT_USERNAME, userNames, true);
		}
		select(ELEMENT_SELECT_MEMBERSHIP, memberShip);
		click(ELEMENT_SAVE_BUTTON_2);
		if (verify) {
			for (String user : users) {
				String addedUser = ELEMENT_ADDED_GROUP_USER_IN_TABLE.replace(
						"${username}", user);
				if (isTextPresent(ELEMENT_MSG_TOTAL_PAGES)) {
					usePaginator(addedUser,
							ELEMENT_USER_NOT_FOUND.replace("${user}", user));
				} else {
					waitForAndGetElement(addedUser);
				}
			}
		}
	}
    /**
     * Verify user-membership
     * @param user
     * @param member
     * @param isDisplay
     */
	public void verifyUserMemInTable(String user,String member,boolean isDisplay){
		info("verify user -membership");
		if(isDisplay){
			waitForAndGetElement(ELEMENT_ADDED_GROUP_USER_IN_TABLE1.replace("$mem", member).replace("$user", user));
		}
	}
	/**
	 * function: Search user In Group Management (To add to group)
	 * 
	 * @param user
	 *            (Can be: User name, Last name, First name or Email of the user
	 *            you want to search)
	 * @param searchOption
	 *            (Can be: User name, Last name, First me or Email option
	 *            corresponding with information you input in "Search")
	 * @param verify
	 *            (True: if you want to verify new user Searched successfully)
	 */
	public void searchUserInGroupManagement(String user, String searchOption,
			boolean verify) {
		info("--Search user " + user + "--");
		click(ELEMENT_GROUP_SEARCH_USER_SEARCH_INPUT);
		type(ELEMENT_GROUP_SEARCH_USER_SEARCH_INPUT, user, true);
		select(ELEMENT_GROUP_SEARCH_USER_OPTION, searchOption);
		click(ELEMENT_GROUP_SEARCH_USER_SEARCH_ICON);
		if (verify) {
			waitForAndGetElement(ELEMENT_SEARCH_GROUP_USER_IN_TABLE.replace(
					"${username}", user));
		}
	}

	/**
	 * function: Delete group
	 * 
	 * @param groupName
	 *            name of Group
	 * @param searchOption
	 *            (Can be: User name, Last name, First me or Email option
	 *            corresponding with information you input in "Search")
	 * @param verify
	 *            (True: if you want to verify group deleted successfully)
	 * @param wait
	 *            time to wait to verify group deleted successfully
	 */
	public void deleteGroup(String groupName, boolean verify, int...wait) {
		info("-- Delete group: " + groupName + "--");
		int waitTime= wait.length > 0 ? wait[0]: DEFAULT_TIMEOUT;
		click(ELEMENT_GROUP_REMOVE_ICON);
		alert.acceptAlert();
		if (verify) {
			waitForElementNotPresent(
					ELEMENT_GROUP_NODE.replace("${groupName}", groupName),
					waitTime);
		}
		Utils.pause(1000);
	}

	/**
	 * function: Add new membership
	 * 
	 * @param membershipName
	 *            name of membership
	 * @param membershipDesc
	 *            description of membership
	 * @param verify
	 *            (True if you want to verify new membership created
	 *            successfully)
	 */
	public void addMembership(String membershipName, String membershipDesc, boolean verify){
		By member = By.xpath(ELEMENT_MEMBERSHIHP.replace("${membershipName}", membershipName));
		info("--Creating new membership--");
		click(ELEMENT_TAB_MEMBERSHIP_MANAGEMENT);
		waitForAndGetElement(ELEMENT_INPUT_NAME);
		type(ELEMENT_INPUT_NAME, membershipName, true);
		type(ELEMENT_TEXTAREA_DESCRIPTION, membershipDesc, true);
		click(ELEMENT_SAVE_BUTTON);
		if (waitForAndGetElement(member, 10000, 0) == null) {
			click(ELEMENT_NEXT_PAGE);
		}
		if (verify)
			waitForAndGetElement(member);
	}

	/**
	 * function: Edit membership
	 * 
	 * @param membershipName
	 *            name of membership you want to edit
	 * @param newDesc
	 *            new description of membership
	 */
	public void editMembership(String membershipName, String newDesc) {
		info("-- Edit membership: " + membershipName + "--");
		boolean verifyMembership;
		verifyMembership = isTextPresent(membershipName);
		if (verifyMembership) {
			waitForTextPresent(membershipName);
		} else {
			click(ELEMENT_NEXT_PAGE);
		}
		String editIcon = ELEMENT_MEMBERSHIP_EDIT_ICON.replace("${membership}",
				membershipName);
		String membershipInput = ELEMENT_MEMBERSHIP_INPUT.replace(
				"${membershipName}", membershipName);
		click(editIcon);
		Utils.pause(1000);
		waitForAndGetElement(membershipInput);
		type(ELEMENT_TEXTAREA_DESCRIPTION, newDesc, true);
		click(ELEMENT_SAVE_BUTTON);
		if (verifyMembership) {
			waitForTextPresent(membershipName);
		} else {
			click(ELEMENT_NEXT_PAGE);
		}
		waitForTextPresent(newDesc);
	}
    /**
     * Verify membership
     * @param mem
     * @param des
     * @param isDisplay
     */
	public void verifyMembership(String mem,String des,boolean isDisplay){
		info("verify display of membership");
		click(ELEMENT_TAB_MEMBERSHIP_MANAGEMENT);
		if(isDisplay){
			waitForAndGetElement(ELEMENT_MEMBERSHIP_DESCRIPTION.replace("$mem", mem).replace("$des", des));
		}
	}
	 /**
     * Verify membership in edit form
     * @param mem
     * @param des
     * @param isDisplay
     */
	public void verifyMembershipInEditForm(String user,String mem,boolean isDisplay){
		info("verify display of membership");
		click(ELEMENT_EDIT_USER_MEM_IN_TABLE_ICON.replace("$user", user),0,true);
		if(isDisplay){
			waitForAndGetElement(ELEMENT_EDIT_USER_MEM_FORM.replace("$mem", mem));
		}
	}
	/**
	 * function: Delete Membership
	 * 
	 * @param membershipName
	 *            name of membership
	 * @param verify
	 *            (True: if you want to verify membership deleted successfully)
	 */
	public void deleteMembership(String membershipName, boolean verify) {
		boolean verifyMembership;
		verifyMembership = isTextPresent(membershipName);
		if (verifyMembership) {
			waitForTextPresent(membershipName);
		} else {
			click(ELEMENT_NEXT_PAGE);
		}
		String deleteIcon = ELEMENT_MEMBERSHIP_DELETE_ICON.replace(
				"${membership}", membershipName);
		info("--Deleting membership--");
		click(deleteIcon);
		alert.waitForConfirmation(ELEMENT_MSG_CONFIRM_DELETE_MEMBERSHIP);
		if (verify) {
			if (verifyMembership)
				waitForTextNotPresent(membershipName);
			if (waitForAndGetElement(ELEMENT_NEXT_PAGE, 10000, 0) != null) {
				click(ELEMENT_NEXT_PAGE);
				waitForTextNotPresent(membershipName);
			}
		}else{
			waitForTextPresent(ELEMENT_MSG_CANNOT_DELETE);
			click(ELEMENT_OK_BUTTON,0,true);
		}
	}
	
	/**
	 * function: Go to edit user's information
	 * 
	 * @param username
	 *            name of user need to edit information
	 */
	public void goToEditUserInfo(String username) {
		String userEditIcon = ELEMENT_USER_EDIT_ICON.replace("${username}",
				username);
		info("--Search user " + username + "--");
		if (isTextPresent("Search")) {
			type(ELEMENT_INPUT_SEARCH_USER_NAME, username, true);
			select(ELEMENT_SELECT_SEARCH_OPTION, ELEMENT_MSG_SEARCH_USER_NAME);
		}
		click(ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);
		info("--Editing user " + username + "--");
		click(userEditIcon);
		Utils.pause(1000);
	}

	/**
	 * function: Edit user's information in Account Tab
	 * 
	 * @param first
	 *            first name of user
	 * @param last
	 *            last name of user
	 * @param displayName
	 *            display name of user
	 * @param email
	 *            email of user
	 */
	public void editUserInfo_AccountTab(String first, String last,
			String displayName, String email) {
		waitForAndGetElement(ELEMENT_USER_ACCOUNT_INFO_TAB, 2000, 1);
		click(ELEMENT_USER_ACCOUNT_INFO_TAB);
		info("--editUserInfo_AccountTab--");
		if (first != null && first!="") {
			type(ELEMENT_FIRSTNAME, first, true);
		}
		if (last != null && last!="") {
			type(ELEMENT_LASTNAME, last, true);
		}
		if (displayName != null && displayName!="") {
			type(ELEMENT_DISPLAY_NAME, displayName, true);
		}
		if (email != null && email!="") {
			type(ELEMENT_EMAIL, email, true);
		}
		click(ELEMENT_SAVE_BUTTON);
		Utils.pause(3000);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
		waitForMessage("The user profile has been updated.");
		click(ELEMENT_CLOSE_MESSAGE);
	}

	/**
	 * function: Edit user's information in User Profile Tab
	 * 
	 * @param givenName
	 *            given name of user
	 * @param familyName
	 *            family name of user
	 * @param nickName
	 *            nick name of user
	 * @param birthday
	 *            birthday of user
	 * @param Gender
	 *            Gender of user
	 * @param Employer
	 *            Employer of user
	 * @param Department
	 *            Department of user
	 * @param jobTitle
	 *            jobTitle of user
	 * @param language
	 *            language of user           
	 */
	public void editUserInfo_UserProfileTab(String givenName, String familyName, String nickName, String birthday, String Gender, String Employer, String Department, String jobTitle, String language) {
		waitForAndGetElement(ELEMENT_USER_PROFILE_TAB, 2000, 1);
		click(ELEMENT_USER_PROFILE_TAB);
		info("--editUserInfo_UserProfileTab--");
		if (givenName != null && givenName!="") {
			type(ELEMENT_GIVEN_NAME, givenName, true);
		}
		if (familyName != null && familyName!="") {
			type(ELEMENT_FAMILY_NAME, familyName, true);
		}
		if (nickName != null && nickName!="") {
			type(ELEMENT_NICK_NAME, nickName, true);
		}
		if (birthday != null && birthday!="") {
			type(ELEMENT_BIRTHDAY, birthday, true);
		}
		if (Gender != null && Gender!="") {
			select(ELEMENT_GENDER, Gender);
		}
		if (Employer != null && Employer!="") {
			type(ELEMENT_EMPLOYER, Employer, true);
		}
		if (Department != null && Department!="") {
			type(ELEMENT_DEPARTMENT, Department, true);
		}
		if (jobTitle != null && jobTitle!="") {
			type(ELEMENT_JOB_TITLE, jobTitle, true);
		}
		if (language != null && language!="") {
			select(ELEMENT_LANGUAGE, language);
		}
		click(ELEMENT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
		waitForMessage(ELEMENT_MSG_UPDATE_USER_PROFILE);
		click(ELEMENT_CLOSE_MESSAGE);
		waitForElementNotPresent(ELEMENT_CLOSE_MESSAGE);
	}


	/**
	 * function: Search user
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
		if (isTextPresent("Search")) {
			type(ELEMENT_INPUT_SEARCH_USER_NAME, user, true);
			select(ELEMENT_SELECT_SEARCH_OPTION, searchOption);
		}
		
		click(ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);
		if (isTextNotPresent(user))
			click(ELEMENT_OK_BUTTON);
	}
	/**
	 * Search User is not found
	 * @param user
	 * @param searchOption
	 */
	public void searchUserNotFound(String user, String searchOption) {
		info("--Search user " + user + "--");
		if (isTextPresent("Search")) {
			type(ELEMENT_INPUT_SEARCH_USER_NAME, user, true);
			select(ELEMENT_SELECT_SEARCH_OPTION, searchOption);
		}
		
		click(ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);
		waitForTextNotPresent(user);
	}
	
	
	/**
	 * Find a user by clicking next arrow
	 * @param user
	 */
	public void findUsersBbyNextArrow(String user){
		if(waitForAndGetElement(ELEMENT_PAGINATION_CONTROL,2000,0)!=null){
			int totalPage = Integer.parseInt(waitForAndGetElement(ELEMENT_PAGINATION_TOTAL_PAGE).getText());
			//if not found user
			if (waitForAndGetElement( ELEMENT_USER_DELETE_ICON.replace("${username}",
					user), 2000, 0) == null){
				//click on next arrow
				for(int i=0;i<totalPage;i++){
					info("Click on Next arrow");
					click(ELEMENT_PAGINATION_ENABLED_NEXT_ARROW);
					//if found the user, break the loop
					if (waitForAndGetElement( ELEMENT_USER_DELETE_ICON.replace("${username}",
							user), 2000, 0) != null) break;
					//if next arrow is disabled, break the loop
					if(waitForAndGetElement(ELMEMENT_PAGINATION_CONTROL_DISBALED_NEXT_ARROW,2000,0)!=null)
						break;
				}
			}
		}
	}
	
	
	//************************************************DISABLE USERS***********************************//
	/**
	 * Select an option in Disable User drop list
	 * @param option
	 *                is a value as :ENABLED,DISABLED OR ALL
	 */
	public void selectDisableStatus(String option){
		info("---Select status----");
		if(!option.isEmpty()){
			select(ELEMENT_DISABLE_USER_DROP_BOX,option);
		}
		Utils.pause(2000);
	}
	/**
	 * Enable or disable a User
	 * @param userName
	 *                   is username of the user
	 * @param isEnabled
	 *                   = true if want to check the user that is enabled
	 *                   = false if want to check the user that is disabled
	 */
	public void enableDisableUser(String userName,boolean isEnabled){
		info("---Enable a user---");
		click(ELEMENT_DISABLE_USER_HANDLE_BTN.replace("$userName",userName));
		Utils.pause(1000);
		if(isEnabled){
			info("Verify that user is enabled");
			selectDisableStatus("Enabled");
			waitForAndGetElement(ELEMENT_DISBALE_USER_ENABLED.replace("$userName",userName),2000,1);
		}else{
			info("Verify that user is disabled");
			selectDisableStatus("Disabled");
			waitForElementNotPresent(ELEMENT_DISBALE_USER_ENABLED.replace("$userName",userName),2000,1);
		}
	}
	
	/**
	 * Verify that the user is shown in the user list
	 * @param userName
	 *                 is user-name of the user
	 */
	public void verifyUserPresent(String userName){
		info("---Verify that the user is shown in the table");
		waitForAndGetElement(ELEMENT_USER_NAME_IN_USER_LIST.replace("$userName",userName));
		info("The user is shown in the table");
	}
	
	/**
	 * Verify that the user is not shown in the user list
	 * @param userName
	 *                 is user-name of the user
	 */
	public void verifyUserNotPresent(String userName){
		info("---Verify that the user is not shown in the table");
		waitForElementNotPresent(ELEMENT_USER_NAME_IN_USER_LIST.replace("$userName",userName));
		info("The user is not shown in the table");
	}
//*********************************************************************************************************//
	/**
	 * function: Delete user
	 * 
	 * @param username
	 *            name of user
	 */
	public void deleteUser(String username) {
		info("--Deleting user " + username + "--");
		info("--Search user " + username + "--");
		if (isTextPresent("Search")) {
			type(ELEMENT_INPUT_SEARCH_USER_NAME, username, true);
			select(ELEMENT_SELECT_SEARCH_OPTION, ELEMENT_MSG_SEARCH_USER_NAME);
		}
	
		click(ELEMENT_SEARCH_ICON_USERS_MANAGEMENT,0,true);
		if (waitForAndGetElement( ELEMENT_USER_DELETE_ICON.replace("${username}",
				username), 2000, 0) != null) {
			Utils.pause(2000);
			click( ELEMENT_USER_DELETE_ICON.replace("${username}",
					username));
			alert.acceptAlert();
			Utils.pause(1000);
			type(ELEMENT_INPUT_SEARCH_USER_NAME, username, true);
			select(ELEMENT_SELECT_SEARCH_OPTION, ELEMENT_MSG_SEARCH_USER_NAME);
			click(ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);
			Utils.pause(3000);
			waitForMessage(ELEMENT_MSG_RESULT);
			dialog.closeMessageDialog();
			Utils.pause(2000);
		}
	}
	/**
	 * Remove a user from a group
	 * @param username
	 */
	public void removeUser(String username){
		info("Click on Delete button");
		click(ELEMENT_USER_REMOVE_MEMBER_ICON.replace("${userName}",username));
		alert.acceptAlert();
		waitForElementNotPresent(ELEMENT_USER_REMOVE_MEMBER_ICON.replace("${userName}",username));
		info("User is removed from the group successfully");
	}
	/**
	 * Delete many users at the same time
	 * @param arrayUsers
	 */
	public void deleteAllUsers(ArrayList<String> arrayUsers){
		for(int i=0;i<arrayUsers.size();i++){
			info("Delete user:"+arrayUsers.get(i));
			deleteUser(arrayUsers.get(i));
			info("Delete user:"+arrayUsers.get(i)+" successfully");
		}
	}
	 /**
	 * Check status drop box
	 */
	public void checkStatusDropBox(){
		info("check status drop box");
		waitForAndGetElement(ELEMENT_DISABLE_USER_STATUS_SELECTED.replace("$status", "Enabled"),2000,1);
		waitForAndGetElement(ELEMENT_DISABLE_USER_DROP_BOX);
		waitForAndGetElement(ELEMENT_DISABLE_USER_STATUS_ALL);
		waitForAndGetElement(ELEMENT_DISABLE_USER_STATUS_DISABLED);
	}
	/**
	 * Check display of all enable and disable user
	 * @param users
	 */
	public void checkDisplayAllUsers(String...users){
		info("check display of all users");
		selectDisableStatus("All");
 	 	waitForAndGetElement(ELEMENT_INPUT_SEARCH_USER_NAME).clear();
 	 	click(ELEMENT_SEARCH_ICON_USERS_MANAGEMENT);
 	 	for (String user : users) {
 	 		findUsersBbyNextArrow(user);
 	 		verifyUserPresent(user);
		}
	}
	/**
	 * Check display of enable user
	 * @param users
	 */
	public void checkDisplayEnableUser(String...users){
		info("check display of enable users");
		waitForAndGetElement(ELEMENT_DISABLE_USER_COLUMN);
 	 	for (String user : users) {
 	 		waitForAndGetElement(ELEMENT_DISBALE_USER_ENABLED.replace("$userName", user),2000,1);
 	 		waitForAndGetElement(ELEMENT_DISABLE_USER_TOGGLE_NO.replace("$userName", user),2000,1,2);
 			waitForAndGetElement(ELEMENT_DISABLE_USER_TOGGLE_YES.replace("$userName", user),2000,1,2);
		}
	}
	/**
	 * Check display of disable user
	 * @param users
	 */
	public void checkDisplayDisableUser(String...users){
		info("check display of disable users");
		waitForAndGetElement(ELEMENT_DISABLE_USER_COLUMN);
 	 	for (String user : users) {
 	 		waitForAndGetElement(ELEMENT_DISBALE_USER_DISABLED.replace("$userName", user),2000,1);
 	 		waitForAndGetElement(ELEMENT_DISABLE_USER_TOGGLE_NO.replace("$userName", user),2000,1,2);
 			waitForAndGetElement(ELEMENT_DISABLE_USER_TOGGLE_YES.replace("$userName", user),2000,1,2);
		}
	}
	/**
	* function: Delete user
	*/
		public void deleteUser() {
			info("--Deleting user ");
			if (waitForAndGetElement( ELEMENT_USER_DELETE_ICON1, 2000, 0) != null) {
				Utils.pause(2000);
				click( ELEMENT_USER_DELETE_ICON1);
				alert.waitForConfirmation(ELEMENT_MSG_CONFIRM_DELETE1);
				Utils.pause(1000);
				waitForElementNotPresent(ELEMENT_USER_DELETE_ICON1);
		}
		}
	
	/**
	 * Open Users tab
	 */
	public void gotoUserTab(){
		info("Open Users tab");
		click(ELEMENT_USER_TAB);
		Utils.pause(2000);
	}	
	/**
	 * Check A link is established between the eXo Platform user account and the social network account.
	 * By: QuyenNT
	 * Date: Dec 1, 2015
	 */
	public void checkLinkedSocialAccount(String element, String placeHolder, String accountValue){
		waitForAndGetElement(ELEMENT_USER_SOCIAL_NETWORKS_TAB);
		click(ELEMENT_USER_SOCIAL_NETWORKS_TAB);
		waitForAndGetElement(element.replace(placeHolder, accountValue));
	}
	
	/**
	 * Unlink social network account
	 * By: QuyenNT
	 * Date: Dec 3, 2015
	 */
	public void unLinkedSocialAccount(Object unlinkElement){
		waitForAndGetElement(ELEMENT_USER_SOCIAL_NETWORKS_TAB);
		click(ELEMENT_USER_SOCIAL_NETWORKS_TAB);
		waitForAndGetElement(unlinkElement);
		click(unlinkElement);
	}	
}
