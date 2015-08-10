package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


	public class Disable_User_Wiki extends Disable_User_TestConfig{

	/**
	*<li> Case ID:127973.</li>
	*<li> Test Case Name: Check disabled user in Permission settings of wiki app.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckDisabledUserInPermissionSettingsOfWikiApp() {
		info("Test 1: Check disabled user in Permission settings of wiki app");
		
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()
				+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("Check enabled user in Wiki Setting Permission");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSettingMg.goToWikiSettingPermission();
		wSettingMg.gotoUserSelector();
		wSettingMg.searchUser(username, searchUserName);
		Utils.pause(2000);
		waitForAndGetElement(userAndGroup.ELEMENT_SEARCH_GROUP_USER_IN_TABLE
				.replace("${username}",username),2000,1);
		wSettingMg.closeUserSelector();
		
		info("Disable user");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		/*Step Number: 1
		*Step Name: Step 1: Select User
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Wiki > Browse > Wiki Settings > Permissions > Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		info("Check disabled user in Wiki Setting Permission");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSettingMg.goToWikiSettingPermission();
		wSettingMg.gotoUserSelector();
		wSettingMg.searchUserNotPresent(username, searchUserName);
		wSettingMg.closeUserSelector();
		
		info("Delete data");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:127974.</li>
	*<li> Test Case Name: Check disabled user in page Permission of wiki app.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDisabledUserInPagePermissionOfWikiApp() {
		info("Test 2: Check disabled user in page Permission of wiki app");
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()
				+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Create a new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(wiki, wiki);
        wikiMg.saveAddPage();
        
		info("Check the display of Enable User in Wiki Page Permission");
		hp.goToWiki();
		wHome.selectAPage(wiki);
		wHome.goToPermissions();
		wSettingMg.gotoUserSelector();
		wSettingMg.searchUser(username, searchUserName);
		Utils.pause(2000);
		waitForAndGetElement(userAndGroup.ELEMENT_SEARCH_GROUP_USER_IN_TABLE
				.replace("${username}",username),2000,1);
		wSettingMg.closeUserSelector();
		wHome.cancelPermissions();
		
		info("Disable user");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
			
		/*Step Number: 1
		*Step Name: Step 1: Select User
		*Step Description: 
			- Connect to Intranet with User B
			- Open Wiki app 
			- Go to More > Page Permissions > Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		info("Check the display of Disabled User in Wiki Page Permission");
		hp.goToWiki();
		wHome.selectAPage(wiki);
		wHome.goToPermissions();
		wSettingMg.gotoUserSelector();
		wSettingMg.searchUserNotPresent(username, searchUserName);
		wSettingMg.closeUserSelector();
		wHome.cancelPermissions();
		

		info("Delete data");
		info ("Delete user");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
		
		info ("Delete wiki page");
		hp.goToWiki();
		wHome.selectAPage(wiki);
		wHome.deleteWiki(wiki);
 	}


	/**
	*<li> Case ID:127975.</li>
	*<li> Test Case Name: Check watch app for a disabled user.</li>
	*<li> Pre-Condition: User A is disabledUser A has already a wiki page watching</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckWatchAppForADisabledUser() {
		info("Test 3: Check watch app for a disabled user");
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String mess = wMessage.getWikiMessage(3);
		String wikiEdit = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Create a new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, EMAIL_ADDRESS2, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("Create a new wiki page");
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(wiki, wiki);
        wikiMg.saveAddPage();
		
		info("Watch the wiki");
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToWiki();
		wHome.goToAPage(wiki);
		wikiMg.watchAPage(mess);
		
		info("Disable user");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		/*Step Number: 1
		*Step Name: Step 1: Edit wiki page
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Wiki app, edit a page already watched by the User A
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			- The page is edit*/
		info("Edit wiki page");
		hp.goToWiki();
		wHome.goToAPage(wiki);
		wHome.goToEditPage();
		richEditor.editSimplePage("", wikiEdit);
	    wikiMg.publishPageWhenEditPage();
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wiki),2000,1);
		
		/*Step number: 2
		*Step Name: Step 2: Check inbox email
		*Step Description: 
			- Check mail Inbox of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- The User has not received a mail from watch app*/ 
		info("Check email notification and link's format of the page");
		goToMail(EMAIL_ADDRESS2, EMAIL_PASS);
		waitForElementNotPresent(wikiMg.ELEMENT_GMAIL_CONTENT_WIKI
				.replace("${title}",wiki),30000,1);
		switchToParentWindow();
		
		info("Delete data");
		info ("Delete user");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
		
		info ("Delete wiki page");
		hp.goToWiki();
		hp.goToWiki();
		wHome.deleteWiki(wiki);
 	}

	/**
	*<li> Case ID:128079.</li>
	*<li> Test Case Name: Check the display of created Wiki Page by disabled user.</li>
	*<li> Pre-Condition: User A is disabledUser B is adminWiki page "Test" is created by User A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckTheDisplayOfCreatedWikiPageByDisabledUser() {
		info("Test 4: Check the display of created Wiki Page by disabled user");
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()
				+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String membership1 = portMemPermisData.getContentByIndex(0);
		
		info("Create a new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup("Platform/Administration");
		userAndGroup.addUsersToGroup(username, membership1, false, true);
		
		info("Create a new wiki page");
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(wiki, wiki);
		wikiMg.saveAddPage();
		
		info("Check the display of Enabled User in Page Info");
		wHome.goToAPage(wiki);
		waitForAndGetElement(wHome.ELEMENT_PAGE_INFO_ADDED_BY
				.replace("{$name}",firstName + " "+ lastName), 2000,1);
		waitForAndGetElement(wHome.ELEMENT_PAGE_INFO_LAST_MODIFIED_BY
				.replace("{$name}",firstName + " "+ lastName), 2000,1);
		
		info("Disable user");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		/*Step Number: 1
		*Step Name: Step 1: Check the display of created Wiki page by disabled user
		*Step Description: 
			- Connect to Intranet with User B
			- Open Wiki application
			- Check the display of Wiki page "Test"
		*Input Data: 
			
		*Expected Outcome: 
			- Wiki page "Test" is still remained
			- On Page information, information related to disabled user (For example: Added by, Last modified by) is kept "User A"*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToWiki();
		wHome.goToAPage(wiki);
		waitForAndGetElement(wHome.ELEMENT_PAGE_INFO_ADDED_BY
				.replace("{$name}",firstName + " "+ lastName), 2000,1);
		waitForAndGetElement(wHome.ELEMENT_PAGE_INFO_LAST_MODIFIED_BY
				.replace("{$name}",firstName + " "+ lastName), 2000,1);
		
		info("Delete data");
		info ("Delete user");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
		
		info ("Delete wiki page");
		hp.goToWiki();
		hp.goToWiki();
		wHome.deleteWiki(wiki);
 	}}