package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.forum.ForumCategoryManagement.specifManageCategoryMenu;
import org.exoplatform.selenium.platform.forum.ForumForumManagement.specifMoreActionMenu;
import org.exoplatform.selenium.platform.forum.ForumTopicManagement.specifMoreActionMenuTopic;
import org.testng.annotations.*;


	public class Disable_User_Forum extends Disable_User_TestConfig{

	/**
	*<li> Case ID:127993.</li>
	*<li> Test Case Name: Check disabled user in Restricted Audience of category.</li>
	*<li> Pre-Condition: User A is disabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckDisabledUserInRestrictedAudienceOfCategory() {
		/*Step Number: 1
		*Step Name: Step 1: Select Restricted Audience:
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Forum 
			-> Add/Edit Category 
			-> Restricted Audience 
			-> Select User
		*Input Data: 
		*Expected Outcome: 
			- User A is not displayed*/ 
		info("Test 1: Check disabled user in Restricted Audience of category");
		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("Check the display of Enable User in User Selector form when adding new category");
		hp.goToForum();
	    click(forumCatMag.ELEMENT_ACTIONBAR_ADDCATEGORY);
		forumCatMag.gotoUserSelectorForRestrictedAudience();
		info("Check the display of Enable User in User Selector form");
		forumCatMag.searchUser(username, searchUserName);
		forumCatMag.closeUserSelector();
		
		info("Disable user");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);

		info("Check the display of Disable User in User Selector form when adding new Category");
		hp.goToForum();
		forumCatMag.gotoUserSelectorForRestrictedAudience();
		forumCatMag.searchUserNotFound(username, searchUserName);
		forumCatMag.closeUserSelector();
		click(forumCatMag.ELEMENT_CATEGORY_CANCEL);
		
		info("Add new Category");
		hp.goToForum();
		forumCatMag.addCategorySimple(nameCat,"",nameCat);
		
		info("edit category");
		forumCatMag.selectItemManageCategoryMenu(specifManageCategoryMenu.EDIT_CATEGORY);
		forumCatMag.gotoUserSelectorForRestrictedAudience();
		forumCatMag.searchUserNotFound(username, searchUserName);
		forumCatMag.closeUserSelector();
		click(forumCatMag.ELEMENT_CATEGORY_CANCEL);
		
		info("Delete data");
		forumHP.goToHomeCategory();
		forumCatMag.deleteCategory(nameCat);
		
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:127994.</li>
	*<li> Test Case Name: Check disabled user in Permission of category.</li>
	*<li> Pre-Condition: User A is disabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDisabledUserInPermissionOfCategory() {
		info("Test 2: Check disabled user in Permission of category");
		/*Step Number: 1
		*Step Name: Step 1: Select user
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Forum 
			-> Add/Edit Category 
			-> Permissions 
			->Select User
		*Input Data: 
		*Expected Outcome: 
			- User A is not displayed*/ 
		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("Check the display of Enable User in Permission form when adding new category");
		hp.goToForum();
		click(forumCatMag.ELEMENT_ACTIONBAR_ADDCATEGORY);
		forumCatMag.goToPermissions();
		forumCatMag.gotoUserSelectorInPermissionTab();
		info("Check the display of Enable User in User Selector form");
		forumCatMag.searchUser(username, searchUserName);
		forumCatMag.closeUserSelector();
		forumCatMag.cancelAddEditCategory();
		
		info("Disable user");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);

		info("Check the display of Disable User in User Selector form when adding new Category");
		hp.goToForum();
		click(forumCatMag.ELEMENT_ACTIONBAR_ADDCATEGORY);
		forumCatMag.goToPermissions();
		forumCatMag.gotoUserSelectorInPermissionTab();
		forumCatMag.searchUserNotFound(username, searchUserName);
		forumCatMag.closeUserSelector();
		forumCatMag.cancelAddEditCategory();
		
		info("Add new Category");
		hp.goToForum();
		forumCatMag.addCategorySimple(nameCat,"",nameCat);
		
		info("edit category");
		forumCatMag.selectItemManageCategoryMenu(specifManageCategoryMenu.EDIT_CATEGORY);
		forumCatMag.goToPermissions();
		forumCatMag.gotoUserSelectorInPermissionTab();
		forumCatMag.searchUserNotFound(username, searchUserName);
		forumCatMag.closeUserSelector();
		forumCatMag.cancelAddEditCategory();
		
		info("Delete data");
		forumHP.goToHomeCategory();
		forumCatMag.deleteCategory(nameCat);
		
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:127995.</li>
	*<li> Test Case Name: Check disabled user in Permission of forum.</li>
	*<li> Pre-Condition: User A is disabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckDisabledUserInPermissionOfForum() {
		info("Test 3: Check disabled user in Permission of forum");
		/*Step Number: 1
		*Step Name: Step 1: Select User
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Forum 
			-> Add/Edit Forum 
			-> Permissions 
			->Select User
		*Input Data: 
		*Expected Outcome: 
			- User A is not displayed*/ 
		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String nameForum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("Add new Category");
		hp.goToForum();
		forumCatMag.addCategorySimple(nameCat,"",nameCat);
		
		info("Check the display of Enable User in Permission form when adding new forum");
		waitForAndGetElement(forumMag.ELEMENT_ACTIONBAR_ADDFORUM,3000,1);
		click(forumMag.ELEMENT_ACTIONBAR_ADDFORUM);
		forumMag.goToPermissions();
		forumMag.gotoUserSelectorInPermissionTab();
		info("Check the display of Enable User in User Selector form");
		forumMag.searchUser(username, searchUserName);
		forumMag.closeUserSelector();
		forumMag.cancelAddEditForum();
		
		info("Disable user");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);

		info("Check the display of Disable User in User Selector form when adding new Category");
		hp.goToForum();
		waitForAndGetElement(forumMag.ELEMENT_ACTIONBAR_ADDFORUM,3000,1);
		click(forumMag.ELEMENT_ACTIONBAR_ADDFORUM);
		forumMag.goToPermissions();
		forumMag.gotoUserSelectorInPermissionTab();
		info("Check the display of Enable User in User Selector form");
		forumMag.searchUserNotFound(username, searchUserName);
		forumMag.closeUserSelector();
		forumMag.cancelAddEditForum();
		
		info("Add a forum in the category");
		hp.goToForum();
		forumHP.goToCategory(nameCat);
		forumMag.addForumSimple(nameForum,"",nameForum);
		
		info("Edit forum");
		forumMag.selectItemMoreActionMenu(specifMoreActionMenu.EDIT);
		forumMag.goToPermissions();
		forumMag.gotoUserSelectorInPermissionTab();
		info("Check the display of Enable User in User Selector form");
		forumMag.searchUserNotFound(username, searchUserName);
		forumMag.closeUserSelector();
		forumMag.cancelAddEditForum();
		
		info("Delete data");
		forumHP.goToHomeCategory();
		forumCatMag.deleteCategory(nameCat);
		
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:127996.</li>
	*<li> Test Case Name: Check disabled user in Permission of topic.</li>
	*<li> Pre-Condition: User A is disabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDisabledUserInPermissionOfTopic() {
		info("Test 4: Check disabled user in Permission of topic");
		/*Step Number: 1
		*Step Name: Step 1: Select user
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Forum 
			-> Add/Edit Topic 
			-> Permissions 
			->Select User
		*Input Data: 
		*Expected Outcome: 
			- User A is not displayed*/ 
		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String nameForum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("go to Forum home page");
		hp.goToForum();
		
		info("Add a category");
		forumCatMag.addCategorySimple(nameCat,"",nameCat);
		
		info("Add a forum in the category");
		forumMag.addForumSimple(nameForum,"",nameForum);
		
		info("Go to Start Topic");
		forumMag.goToStartTopic();
		info("Check the display of Enable User in Permission form when adding new topic");
		foTopic.goToPermissions();
		foTopic.gotoUserSelectorInPermissionTab();
		info("Check the display of Enable User in User Selector form");
		foTopic.searchUser(username, searchUserName);
		foTopic.closeUserSelector();
		foTopic.cancelAddEditTopic();
		
		info("Disable user");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);

		hp.goToForum();
		forumHP.goToCategory(nameCat);
		forumHP.goToForum(nameForum);
		forumMag.goToStartTopic();
		info("Check the display of Disable User in Permission form when adding new topic");
		foTopic.goToPermissions();
		foTopic.gotoUserSelectorInPermissionTab();
		foTopic.searchUserNotFound(username, searchUserName);
		foTopic.closeUserSelector();
		foTopic.cancelAddEditTopic();
		
		info("Add a topic in a forum");
		forumMag.goToStartTopic();
		foTopic.startTopic(topic, topic,"","");
		
		info("Edit topic");
		forumHP.goToTopic(topic);
		foTopic.selectItemMoreActionMenuTopic(specifMoreActionMenuTopic.EDIT);
		foTopic.goToPermissions();
		foTopic.gotoUserSelectorInPermissionTab();
		foTopic.searchUserNotFound(username, searchUserName);
		foTopic.closeUserSelector();
		foTopic.cancelAddEditTopic();
		
		info("Delete data");
		forumHP.goToHomeCategory();
		forumCatMag.deleteCategory(nameCat);
		
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:127997.</li>
	*<li> Test Case Name: Check disabled user in Sent To screen of a Forum.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckDisabledUserInSentToScreenOfAForum() {
		info("Test 5: Check disabled user in Sent To screen of a Forum");
		/*Step Number: 1
		*Step Name: Step 1: Select User
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Forum > Private Message > Compose New message > Send To > Select User
		*Input Data:
		*Expected Outcome: 
			- User A is not displayed*/ 
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("go to Forum home page");
		hp.goToForum();
		forumHP.goToPrivateMessage();
		forumHP.goToComposeNewMessageTab();
		forumHP.gotoUserSelectorInComposeNewMessageTab();
		info("Check the display of Enable User in Select User form when compose new message");
		forumHP.searchUser(username, searchUserName);
		forumHP.closeUserSelector();
		forumHP.cancelSendPrivateMessage();
		
		info("Disable user");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("go to Forum home page");
		hp.goToForum();
		forumHP.goToPrivateMessage();
		forumHP.goToComposeNewMessageTab();
		forumHP.gotoUserSelectorInComposeNewMessageTab();
		info("Check the display of Disable User in Select User form when compose new message");
		forumHP.searchUserNotFound(username, searchUserName);
		forumHP.closeUserSelector();
		forumHP.cancelSendPrivateMessage();
		
		info("Delete data");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:127998.</li>
	*<li> Test Case Name: Check disabled user in Users list of a Forum.</li>
	*<li> Pre-Condition: User A is disabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckDisabledUserInUsersListOfAForum() {
		info("Test 6: Check disabled user in Users list of a Forum");
		/*Step Number: 1
		*Step Name: Step 1: Open user list
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Forum appplication 
			->Choose "Users" on administration bar
		*Input Data: 
		*Expected Outcome: 
			- User A is not displayed*/
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("go to Forum home page");
		hp.goToForum();
		forumHP.goToManageUser();
		info("Check the display of Enable User in Manage User form");
		forumHP.searchUserInUserList(username, searchUserName);
		forumHP.closeUserForm();
		
		info("Disable user");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("go to Forum home page");
		hp.goToForum();
		forumHP.goToManageUser();
		info("Check the display of Disable User in Manage User form");
		forumHP.searchUserInUserListNotFound(username, searchUserName);
		forumHP.closeUserForm();
		
		info("Delete data");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:127999.</li>
	*<li> Test Case Name: Check email from watch on topic for disabled user.</li>
	*<li> Pre-Condition: user A has watched a topicuser A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckEmailFromWatchOnTopicForDisabledUser() {
		info("Test 7: Check email from watch on topic for disabled user");
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nameForum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, EMAIL_ADDRESS2, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		hp.goToForum();
		info("Add a category");
		forumCatMag.addCategorySimple(nameCat,"",nameCat);
		info("Add a forum in the category");
		forumMag.addForumSimple(nameForum,"",nameForum);
		info("Add and go to a topic in the forums");
		forumMag.goToStartTopic();
		foTopic.startTopic(topic, topic,"","");
		
		info("Watch the topic");
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToForum();
		forumHP.goToTopic(topic);
		forumHP.watchItem(true);
		
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
		*Step Name: Step 1: Post a reply in watched topic
		*Step Description: 
			- Connect to Intranet with User B
			- Open the topic that has already watched by the user A
			- Post a reply for this topic
		*Input Data: 
		*Expected Outcome: 
			- The post is displayed*/
		info("Post reply to a topic");
		hp.goToForum();
		forumHP.goToTopic(topic);
		foTopic.postReply(title, content);
		info("Verify that the post is created");
		waitForAndGetElement(foTopic.ELEMENT_POST_IN_TOPIC.replace("{$title}",title)
				.replace("{$content}",content));
		
		/*Step number: 2
		*Step Name: Step 2: Check email box
		*Step Description: 
			- Check the email inbox of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- Email about the new post in watched topic is NOT sent to User A*/ 
		info("Check email notification and link's format of the page");
		goToMail(EMAIL_ADDRESS2, EMAIL_PASS);
		Utils.pause(3000);
		waitForTextNotPresent(forumHP.ELEMENT_GMAIL_CONTENT_TOPIC.replace("{$title}",title), 2000,1);
		switchToParentWindow();
		
		info("Delete data");
		forumHP.goToHomeCategory();
		forumCatMag.deleteCategory(nameCat);
		
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:128000.</li>
	*<li> Test Case Name: Check private message in Forum for disabled user member of a group.</li>
	*<li> Pre-Condition: User A is member in a forum/topic</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckPrivateMessageInForumForDisabledUserMemberOfAGroup() {
		info("Test 8: Check private message in Forum for disabled user member of a group");
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String groupPlatform = PLATFORM_GROUP;
		String groupAdministration = ADMINISTRATION_GROUP;
		String title =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String membership1 = portMemPermisData.getContentByIndex(0);
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup(groupPlatform+"/"+groupAdministration);
		userAndGroup.addUsersToGroup(username, membership1, false, true);
		
		/*Step Number: 1
		*Step Name: Step 1: Disable user
		*Step Description: 
			- Connect to Intranet with an administrator (User B)
			- From Administration/User and Group, Disable the User A
		*Input Data: 
		*Expected Outcome: 
			- The User A is disabled*/
		info("Disable user");
		userAndGroup.gotoUserTab();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);

		/*Step number: 2
		*Step Name: Step 2: Send private message to a group
		*Step Description: 
			- Go to Forum app
			- Send a private message to a Group where "User A is member
		*Input Data: 
		*Expected Outcome: 
			- The Send is done from the User B side*/
		info("Send Private Message to a Group");
		hp.goToForum();
		forumHP.goToPrivateMessage();
		msgManage.goComposeMessage();
		msgManage.openSelectGroupForm();
		click (msgManage.ELEMENT_PRIVATE_MESSAGE_SELECT_GROUP.replace("${name}", groupPlatform));
		msgManage.writeMessageToGroup(groupAdministration, title, content);
		msgManage.cancelPrivateMessage();
		
		/*Step number: 3
		*Step Name: Step 3: Check Private message after re-enable user
		-enabling user
		*Step Description: 
			- Enable the User A
			- Connect to Intranet with User A
			- Go to Forum application
			- Check the Private message inbox of the User A
		*Input Data: 
		*Expected Outcome: 
			- The private message email is not received*/ 
		info("Re-enable User");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		userAndGroup.enableDisableUser(username, true);
	
		userAndGroup.selectDisableStatus("Enabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("Check the sending Private Message to User");
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToForum();
		forumHP.goToPrivateMessage();
		msgManage.checkInboxMessageNotFound(title);
		msgManage.cancelPrivateMessage();
 	}

	/**
	*<li> Case ID:128001.</li>
	*<li> Test Case Name: Check email from My Subscription in Forum for disabled user.</li>
	*<li> Pre-Condition: - User A is enable</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckEmailFromMySubscriptionInForumForDisabledUser() {
		info("Test 9: Check email from My Subscription in Forum for disabled user");
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String groupPlatform = PLATFORM_GROUP;
		String groupAdministration = ADMINISTRATION_GROUP;
		String title =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String membership1 = portMemPermisData.getContentByIndex(0);
		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nameForum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup(groupPlatform+"/"+groupAdministration);
		userAndGroup.addUsersToGroup(username, membership1, false, true);

		/*Step Number: 1
		*Step Name: Step 1: Create Category/Forum/Topic/Post
		*Step Description: 
			- Connect to Intranet with User A
			- Go to Forum application
			- Create Category/Forum/Topic/Post
		*Input Data: 
		*Expected Outcome: 
			- Category/Forum/Topic/Post are created*/
		
		/*Step number: 2
		*Step Name: Step 2: Disable user
		*Step Description: 
			- Disable User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is disabled*/
		hp.goToForum();
		info("Add a category");
		forumCatMag.addCategorySimple(nameCat,"",nameCat);
		info("Add a forum in the category");
		forumMag.addForumSimple(nameForum,"",nameForum);
		info("Add and go to a topic in the forums");
		forumMag.goToStartTopic();
		foTopic.startTopic(topic, topic,"","");
		
		/*Step number: 3
		*Step Name: Step 3: Watch a category
		*Step Description: 
			- Connect to Intranet with User B
			- Go to Forum application
			- Watch created Category or Forum or Topic at step 1
		*Input Data: 
			
		*Expected Outcome: 
			- Category/Forum/Topic are watched*/
		info("Watch the topic");
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToForum();
		forumHP.goToTopic(topic);
		forumHP.watchItem(true);
		
		info("Update email in My Subscription");
	    forumHP.gotoMySubscriptions();
	    waitForAndGetElement(forumHP.ELEMENT_MY_SUBSCRIPTION_TITLE.replace("${link}", topic),2000,1);
	    forumHP.updateEmailInMySubscriptions(EMAIL_ADDRESS2);
	    
	    info("Disable user");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("Post reply to a topic");
		hp.goToForum();
		forumHP.goToTopic(topic);
		foTopic.postReply(title, content);
		info("Verify that the post is created");
		waitForAndGetElement(foTopic.ELEMENT_POST_IN_TOPIC.replace("{$title}",title).replace("{$content}",content));

		/*Step number: 4
		*Step Name: Step 4: Check the email inbox of User A
		*Step Description: 
			- Check the email inbox of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- The email from My Subscription is not received*/ 
		info("Check email notification and link's format of the page");
		goToMail(EMAIL_ADDRESS2, EMAIL_PASS);
		Utils.pause(3000);
		waitForTextNotPresent(forumHP.ELEMENT_GMAIL_CONTENT_TOPIC.replace("{$title}",title), 2000,1);
		switchToParentWindow();
		
		info("Delete data");
		forumHP.goToHomeCategory();
		forumCatMag.deleteCategory(nameCat);
		
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:128002.</li>
	*<li> Test Case Name: Check the button Private in Topic for disabled user.</li>
	*<li> Pre-Condition: User A has posted a topicUser A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckTheButtonPrivateInTopicForDisabledUser() {
		info("Test 10 Check the button Private in Topic for disabled user");
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String title =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nameForum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		hp.goToForum();
		info("Add a category");
		forumCatMag.addCategorySimple(nameCat,"",nameCat);
		info("Add a forum in the category");
		forumMag.addForumSimple(nameForum,"",nameForum);
		info("Add and go to a topic in the forums");
		forumMag.goToStartTopic();
		foTopic.startTopic(topic, topic,"","");
		
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToForum();
		forumHP.goToTopic(topic);
		foTopic.postReply(title, content);
		info("Verify that the post is created");
		waitForAndGetElement(foTopic.ELEMENT_POST_IN_TOPIC.replace("{$title}",title).replace("{$content}",content),2000,1);

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
		*Step Name: Step 1: Open Topic
		*Step Description: 
			- Connect to Intranet with User B
			- Open Forum/Topic where User A has a post
		*Input Data: 
			
		*Expected Outcome: 
			- The topic is displayed
			- In the post of the User A, the button "Private" is not displayed*/ 
		hp.goToForum();
		forumHP.goToTopic(topic);
		waitForElementNotPresent(forumHP.ELEMENT_TOPIC_PRIVATE_BUTTON.replace("${post}", content), 2000,1);
		
		info("Delete data");
		forumHP.goToHomeCategory();
		forumCatMag.deleteCategory(nameCat);
		
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.deleteUser(username);
 	}

	/**
	*<li> Case ID:128009.</li>
	*<li> Test Case Name: Check private message in Forum for membership of disabled user.</li>
	*<li> Pre-Condition: User A is member in a forum/topic</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckPrivateMessageInForumForMembershipOfDisabledUser() {
		info("Test 11 Check private message in Forum for membership of disabled user");
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String groupPlatform = PLATFORM_GROUP;
		String groupAdministration = ADMINISTRATION_GROUP;
		String title =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String membership2 = portMemPermisData.getContentByIndex(4);
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup(groupPlatform+"/"+groupAdministration);
		userAndGroup.addUsersToGroup(username, membership2, false, true);
		
		/*Step Number: 1
		*Step Name: Step 1: Disable user
		*Step Description: 
			- Connect to Intranet with an administrator (User B)
			- From Administration 
			-
			-> Community, Manage Community 
			-
			-> User Management tab, disable the User A
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disabled*/
		info("Disable user");
		userAndGroup.gotoUserTab();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
	
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);

		/*Step number: 2
		*Step Name: Step 2: Send private message
		*Step Description: 
			- Go to Forum app
			- Send a private message to a membership where User A has the selected membership
		*Input Data: 
			
		*Expected Outcome: 
			- The Send is done from the User B side*/
		info("Send Private Message to a Group");
		hp.goToForum();
		forumHP.goToPrivateMessage();
		msgManage.goComposeMessage();
		msgManage.openSelectMembershipForm();
		click (msgManage.ELEMENT_PRIVATE_MESSAGE_SELECT_GROUP.replace("${name}", groupPlatform));
		msgManage.writeMessageToMembership(groupAdministration, membership2, title, content);
		msgManage.cancelPrivateMessage();
		/*Step number: 3
		*Step Name: Step 3: Check the ability to receive private message after user is re
		-enabled
		*Step Description: 
			- Enable the User A
			- Connect to Intranet with User A
			- Go to Forum application
			- Check the Private message inbox of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- The private message email is not received*/ 
		info("Re-enable User");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		userAndGroup.enableDisableUser(username, true);
	
		userAndGroup.selectDisableStatus("Enabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		info("Check the sending Private Message to User");
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToForum();
		forumHP.goToPrivateMessage();
		msgManage.checkInboxMessageNotFound(title);
		msgManage.cancelPrivateMessage();

 	}

	/**
	*<li> Case ID:128105.</li>
	*<li> Test Case Name: Check Category/Forum/Topic/Post of disabled user.</li>
	*<li> Pre-Condition: - User A is enable
	- User B is admin
	- User A has already created Category/Forum/Topic/Post</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckCategoryForumTopicPostOfDisabledUser() {
		info("Test 12 Check Category/Forum/Topic/Post of disabled user");
		String username = userInfoData.getUserNameByIndex(5)+getRandomString();
		String password = userInfoData.getPassWordByIndex(5)+getRandomString();
		String firstName = userInfoData.getFirstNameByIndex(5)+getRandomString();
		String lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
		String email = userInfoData.getEmailByIndex(5)+getRandomString()+mailSuffixData.getMailSuffixByIndex(2);
		String searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		String groupPlatform = PLATFORM_GROUP;
		String groupAdministration = ADMINISTRATION_GROUP;
		String title =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String membership2 = portMemPermisData.getContentByIndex(4);
		String nameCat = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String nameForum = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String topic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Create new user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup(groupPlatform+"/"+groupAdministration);
		userAndGroup.addUsersToGroup(username, membership2, false, true);
		
		/*Step Number: 1
		*Step Name: Step 1: Open Forum application
		*Step Description: 
			- Connect to Intranet with User A
			- Open Forum application
		*Input Data: 
			
		*Expected Outcome: 
			- Category/Forum/Topic/Post that is created by User A is displayed*/
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToForum();
		info("Add a category");
		forumCatMag.addCategorySimple(nameCat,"",nameCat);
		info("Add a forum in the category");
		forumMag.addForumSimple(nameForum,"",nameForum);
		info("Add and go to a topic in the forums");
		forumMag.goToStartTopic();
		foTopic.startTopic(topic, topic,"","");
		forumHP.goToTopic(topic);
		foTopic.postReply(title, content);
		info("Verify that the post is created");
		waitForAndGetElement(foTopic.ELEMENT_POST_IN_TOPIC.replace("{$title}",title).replace("{$content}",content),2000,1);

		/*Step number: 2
		*Step Name: Step 2: Disable user
		*Step Description: 
			- Connect to Intranet with the User B
			- Disable the user A
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is disable*/
		info("Disable user");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.enableDisableUser(username, false);
		userAndGroup.selectDisableStatus("Disabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		/*Step number: 3
		*Step Name: Step 3: Open Forum application
		*Step Description: 
			- Open Forum application
		*Input Data: 
			
		*Expected Outcome: 
			- Category/Forum/Topic/Post that is created by User A is still remain*/
		hp.goToForum();
		waitForAndGetElement(forumHP.ELEMENT_CATEGORY_TITLE.replace("${title}", nameCat),2000,1);
		forumHP.goToCategory(nameCat);
		waitForAndGetElement(forumHP.ELEMENT_FORUM_DETAIL_FORUM_NAME_LINK.replace("${name}", nameForum),2000,1);
		forumHP.goToForum(nameForum);
		waitForAndGetElement(forumHP.ELEMENT_SELECT_FORUM_TOPIC.replace("${link}",topic),2000,1);
		forumHP.goToTopic(topic);
		waitForAndGetElement(foTopic.ELEMENT_POST_IN_TOPIC.replace("{$title}",title).replace("{$content}",content),2000,1);
		
		/*Step number: 4
		*Step Name: Step 4: Re-enable user
		*Step Description: 
			- Enable the User A again
			- Go to Forum application
		*Input Data: 
			
		*Expected Outcome: 
			- Category/Forum/Topic/Post that is created by User A is still remain*/
		info("Re-enable User");
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		userAndGroup.enableDisableUser(username, true);
	
		userAndGroup.selectDisableStatus("Enabled");
		userAndGroup.searchUser(username, searchUserName);
		userAndGroup.verifyUserPresent(username);
		
		/*Step number: 5
		*Step Name: Step 5: Check the displayed of Category/Forum/Topic/Postafter re
		-enabling user
		*Step Description: 
			- Connect to Intranet with User A
			- Open Forum application
		*Input Data: 
			
		*Expected Outcome: 
			- Category/Forum/Topic/Post that is created by User A is still remain*/ 
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToForum();
		waitForAndGetElement(forumHP.ELEMENT_CATEGORY_TITLE.replace("${title}", nameCat),2000,1);
		forumHP.goToCategory(nameCat);
		waitForAndGetElement(forumHP.ELEMENT_FORUM_DETAIL_FORUM_NAME_LINK.replace("${name}", nameForum),2000,1);
		forumHP.goToForum(nameForum);
		waitForAndGetElement(forumHP.ELEMENT_SELECT_FORUM_TOPIC.replace("${link}",topic),2000,1);
		forumHP.goToTopic(topic);
		waitForAndGetElement(foTopic.ELEMENT_POST_IN_TOPIC.replace("{$title}",title).replace("{$content}",content),2000,1);

 	}}