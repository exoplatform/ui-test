package org.exoplatform.selenium.platform.wiki.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.wiki.WikiPermission.permissionType;
import org.testng.annotations.*;


	public class Wiki_Permission_Wiki_Permission_Check_Permission extends WIKI_TestConfig_2{

	/**
	*<li> Case ID:139595.</li>
	*<li> Test Case Name: Verify View permission of an user.</li>
	*<li> Pre-Condition: - The User A has already the permission : View Pages in the Wiki Settings
	- Some wiki pages are already created in this space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_VerifyViewPermissionOfAnUser() {
		info("Test 1: Verify View permission of an user");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		*Step Name: Step 2: Check permission for a User
		*Step Description: 
			- Check the User A permission
			- In the permission table, 
			check the View Pages checkbox of the User A, uncheck all other checkbox
			- Click Save 
			-
			-> OK
		*Input Data: 
			
		*Expected Outcome: 
			- The permission View Pages is checked
			- Other checkbox are unchecked*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create new page 1");
		String page = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageContent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page,pageContent);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page);
		arrayPage.add(page);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();
		

		/*Step number: 3
		*Step Name: Step 3: Check if theuser can view the page
		*Step Description: 
			- Log in as User A
			- Go to Wiki of the space.
		*Input Data: 
			
		*Expected Outcome: 
			- The user can view the wiki page but cannot see Edit Page, Add Page; Move Page 
			and Delete Page and Page Permissions from More menu
			- The user can not see Wiki Settings from Browse menu*/ 
		info("Select user to add permission");
		wPermission.addPermisisonByType(arrayUsers.get(0));
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAPage(page);
		waitForElementNotPresent(wHome.ELEMENT_EDIT_PAGE_LINK);
		waitForElementNotPresent(wHome.ELEMENT_ADD_PAGE_LINK);
		click(wHome.ELEMENT_MORE_LINK);
		waitForElementNotPresent(wHome.ELEMENT_MOVE_PAGE);
		waitForElementNotPresent(wHome.ELEMENT_DELETE_LINK);
		waitForElementNotPresent(wHome.ELEMENT_PERMISSION_LINK);
		
		click(wHome.ELEMENT_SEARCH_BROWSERS_DROPDOWN);
		waitForElementNotPresent(wHome.ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);
 	}

	/**
	*<li> Case ID:139596.</li>
	*<li> Test Case Name: Verify Edit permission for a user.</li>
	*<li> Pre-Condition: - The User A has already the permission: Edit Pages in the Wiki Settings
	- Some wiki pages are already created in this space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_VerifyEditPermissionForAUser() {
		info("Test 2: Verify Edit permission for a user");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		*Step Name: Step 2: Check Edit Pages permission for User A
		*Step Description: 
			- Check the User A permission
			- In the permission table, check the Edit Pages checkbox of the User A
			- Uncheck permission "Admin Pages" and "Admin Wiki"
			- Click Save 
			-
			-> OK
		*Input Data: 
			
		*Expected Outcome: 
			- Edit permission and View permission are selected
			- Other permission are unchecked*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create new page 1");
		String page = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageContent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page,pageContent);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page);
		arrayPage.add(page);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 3
		*Step Name: Step 3: Check if the User A has Edit Pages permission
		*Step Description: 
			- Login as User A
			- Go to space 
			-
			-> Wiki
			- Select a wiki page
		*Input Data: 
			
		*Expected Outcome: 
			- The user A can view the wiki page and can see Edit Page, Add Page; 
			Move Page and Delete Page from More menu
			- The user can not see Wiki Settings from Browse menu
			- The user A can not see Page Permissions from More menu*/ 
		info("Select user to add permission");
		wPermission.addPermisisonByType(arrayUsers.get(0));
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Edit_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAPage(page);
		waitForAndGetElement(wHome.ELEMENT_EDIT_PAGE_LINK);
		waitForAndGetElement(wHome.ELEMENT_ADD_PAGE_LINK);
		click(wHome.ELEMENT_MORE_LINK);
		waitForAndGetElement(wHome.ELEMENT_MOVE_PAGE);
		waitForAndGetElement(wHome.ELEMENT_DELETE_LINK);
		waitForElementNotPresent(wHome.ELEMENT_PERMISSION_LINK);
		
		click(wHome.ELEMENT_SEARCH_BROWSERS_DROPDOWN);
		waitForElementNotPresent(wHome.ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);

 	}

	/**
	*<li> Case ID:139597.</li>
	*<li> Test Case Name: Verify Admin Pages permission of a user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - The User A has already the permission: Admin Pages in the Wiki Settings
	- Some wiki pages are already created in this space</li>
	*/
	@Test
	public  void test03_VerifyAdminPagesPermissionOfAUser() {
		info("Test 3: Verify Admin Pages permission of a user");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		*Step Name: Step 2: Check Admin Pages permission
		*Step Description: 
			- Check the User A permission
			- In the permission table, check the Admin Pages checkbox of the User A
			- Uncheck permission "Admin Wiki"
			- Click Save 
			-
			-> OK
		*Input Data: 
			
		*Expected Outcome: 
			- All permissions are selected except permission "Admin Wiki"*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create new page 1");
		String page = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageContent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page,pageContent);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page);
		arrayPage.add(page);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 3
		*Step Name: Step 3: Check if theuser A has Admin Pages permission
		*Step Description: 
			- Login as user A
			- Go to the space 
			-
			-> Wiki
			- Select a wiki page
		*Input Data: 
			
		*Expected Outcome: 
			- The user can not see Wiki Settings from Browse menu
			- The user A can see Page Permissions from More menu*/ 
		info("Select user to add permission");
		wPermission.addPermisisonByType(arrayUsers.get(0));
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Edit_Pages);
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Admin_Pages);
		wPermission.savePermisison();
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAPage(page);
		waitForAndGetElement(wHome.ELEMENT_EDIT_PAGE_LINK);
		waitForAndGetElement(wHome.ELEMENT_ADD_PAGE_LINK);
		click(wHome.ELEMENT_MORE_LINK);
		waitForAndGetElement(wHome.ELEMENT_MOVE_PAGE);
		waitForAndGetElement(wHome.ELEMENT_DELETE_LINK);
		waitForAndGetElement(wHome.ELEMENT_PERMISSION_LINK);
		
		click(wHome.ELEMENT_SEARCH_BROWSERS_DROPDOWN);
		waitForElementNotPresent(wHome.ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);

 	}

	/**
	*<li> Case ID:139598.</li>
	*<li> Test Case Name: Verify Admin Wiki permission for a user.</li>
	*<li> Pre-Condition: - The User A has already the permission: Admin Wiki in the Wiki Settings
	- Some wiki pages are already created in this space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_VerifyAdminWikiPermissionForAUser() {
		info("Test 4: Verify Admin Wiki permission for a user");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		*Step Name: Step 2: Check the Admin Wiki permission for User A
		*Step Description: 
			- Check the User A permission
			- In the permission table, check the Admin Wiki checkbox of the User A
			- Click Save 
			-
			-> OK
		*Input Data: 
			
		*Expected Outcome: 
			- All permission are selected*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create new page 1");
		String page = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageContent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page,pageContent);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page);
		arrayPage.add(page);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 3
		*Step Name: Step 3: Check if theuser A has Admin Wiki permission
		*Step Description: 
			- Login as user A
			- Go to the space 
			-
			-> Wiki
			- Select a wiki page
		*Input Data: 
			
		*Expected Outcome: 
			- The user can see Wiki Settings from Browse menu
			- The user A can see Page Permissions from More menu*/ 
		info("Select user to add permission");
		wPermission.addPermisisonByType(arrayUsers.get(0));
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Edit_Pages);
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Admin_Pages);
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Admin_Wiki);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAPage(page);
		waitForAndGetElement(wHome.ELEMENT_EDIT_PAGE_LINK);
		waitForAndGetElement(wHome.ELEMENT_ADD_PAGE_LINK);
		click(wHome.ELEMENT_MORE_LINK);
		waitForAndGetElement(wHome.ELEMENT_MOVE_PAGE);
		waitForAndGetElement(wHome.ELEMENT_DELETE_LINK);
		waitForAndGetElement(wHome.ELEMENT_PERMISSION_LINK);
		
		click(wHome.ELEMENT_SEARCH_BROWSERS_DROPDOWN);
		waitForAndGetElement(wHome.ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);

 	}

	/**
	*<li> Case ID:139599.</li>
	*<li> Test Case Name: Verify View permission for a group.</li>
	*<li> Pre-Condition: - The Group A has already the permission : View Pages in the Wiki Settings
	- Some wiki pages are already created in this space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_VerifyViewPermissionForAGroup() {
		info("Test 5: Verify View permission for a group");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		*Step Name: Step 2: Check permission for a Group
		*Step Description: 
			- Check the Group A permission
			- In the permission table, check the View Pages checkbox of the Group A and uncheck other checkbox
			- Click Save 
			-
			-> OK
		*Input Data: 
			
		*Expected Outcome: 
			- The permission View Pages is selected
			- Other permissions are unchecked*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a new group");
		String groupName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupLabel = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupDesc = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.addGroup(groupName, groupLabel, groupDesc, true);
		arrayGroups.add(groupLabel);
		
		info("Add users to the group");
		String membership = permisMem.getDataContentByArrayTypeRandom(1);
		Utils.pause(2000);
		userAndGroup.selectGroup(groupLabel);
		userAndGroup.addUsersToGroup(arrayUsers.get(0),membership,false,true);
		
		info("Create new page 1");
		String page = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageContent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page,pageContent);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page);
		arrayPage.add(page);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 3
		*Step Name: Step 3: Check if the group can view the page
		*Step Description: 
			- Log in with a user member of the Group A
			- Go to Wiki of the space.
		*Input Data: 
			
		*Expected Outcome: 
			- The user can view the wiki page but cannot see Edit Page, Add Page; Move Page and Delete Page and Page Permissions from More menu
			- The user can not see Wiki Settings from Browse menu*/
		info("Select group to add permission");
		String groupUsers ="*:/"+groupName;
		wPermission.addPermisisonByType(groupUsers);
		wPermission.savePermisison();
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(groupUsers);
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAPage(page);
		waitForElementNotPresent(wHome.ELEMENT_EDIT_PAGE_LINK);
		waitForElementNotPresent(wHome.ELEMENT_ADD_PAGE_LINK);
		click(wHome.ELEMENT_MORE_LINK);
		waitForElementNotPresent(wHome.ELEMENT_MOVE_PAGE);
		waitForElementNotPresent(wHome.ELEMENT_DELETE_LINK);
		waitForElementNotPresent(wHome.ELEMENT_PERMISSION_LINK);
		
		click(wHome.ELEMENT_SEARCH_BROWSERS_DROPDOWN);
		waitForElementNotPresent(wHome.ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);

 	}

	/**
	*<li> Case ID:139600.</li>
	*<li> Test Case Name: Verify Admin Pages permission of a group.</li>
	*<li> Pre-Condition: - The Group A has already the permission : Admin Pages in the Wiki Settings
	- Some wiki pages are already created in this space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_VerifyAdminPagesPermissionOfAGroup() {
		info("Test 6: Verify Admin Pages permission of a group");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		*Step Name: Step 2: Check the Admin Pages permission for Group A
		*Step Description: 
			- Check the Group A permission
			- In the permission table, check the Admin Pages checkbox of the Group A
			- Uncheck "Admin Wiki" permission
			- Click Save 
			-
			-> OK
		*Input Data: 
			
		*Expected Outcome: 
			- All permission are selected except permission "Admin Wiki"*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a new group");
		String groupName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupLabel = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupDesc = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.addGroup(groupName, groupLabel, groupDesc, true);
		arrayGroups.add(groupLabel);
		
		info("Add users to the group");
		String membership = permisMem.getDataContentByArrayTypeRandom(1);
		Utils.pause(2000);
		userAndGroup.selectGroup(groupLabel);
		userAndGroup.addUsersToGroup(arrayUsers.get(0),membership,false,true);
		
		info("Create new page 1");
		String page = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageContent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page,pageContent);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page);
		arrayPage.add(page);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 3
		*Step Name: Step 3: Check ifGroup A has Admin Pages permission
		*Step Description: 
			- Log in with a user member of the Group A
			- Go to Wiki of the space.
		*Input Data: 
			
		*Expected Outcome: 
			- The user can not see Wiki Settings from Browse menu
			- The user A can see Page Permissions from More menu*/ 
		info("Select group to add permission");
		String groupUsers ="*:/"+groupName;
		wPermission.addPermisisonByType(groupUsers);
		wPermission.selectPermission(groupUsers, permissionType.Admin_Pages);
		wPermission.savePermisison();
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(groupUsers);
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAPage(page);
		click(wHome.ELEMENT_MORE_LINK);
		waitForAndGetElement(wHome.ELEMENT_PERMISSION_LINK);
		
		click(wHome.ELEMENT_SEARCH_BROWSERS_DROPDOWN);
		waitForElementNotPresent(wHome.ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);

 	}

	/**
	*<li> Case ID:139601.</li>
	*<li> Test Case Name: Verify Admin Wiki permission for a group.</li>
	*<li> Pre-Condition: - The Group A has already the permission: Admin Wiki in the Wiki Settings
	- Some wiki pages are already created in this space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_VerifyAdminWikiPermissionForAGroup() {
		info("Test 7: Verify Admin Wiki permission for a group");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		*Step Name: Step 2: Select the Admin Wiki permission
		*Step Description: 
			- Check the Group A permission
			- In the permission table, check the Admin Wiki checkbox of the Group A
			- Click Save 
			-
			-> OK
		*Input Data: 
			
		*Expected Outcome: 
			- All permission are checked*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a new group");
		String groupName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupLabel = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupDesc = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.addGroup(groupName, groupLabel, groupDesc, true);
		arrayGroups.add(groupLabel);
		
		info("Add users to the group");
		String membership = permisMem.getDataContentByArrayTypeRandom(1);
		Utils.pause(2000);
		userAndGroup.selectGroup(groupLabel);
		userAndGroup.addUsersToGroup(arrayUsers.get(0),membership,false,true);
		
		info("Create new page 1");
		String page = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageContent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page,pageContent);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page);
		arrayPage.add(page);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 3
		*Step Name: Step 3: Check ifGroup A has Admin Wiki permission
		*Step Description: 
			- Log in with a user member of the Group A
			- Go to Wiki of the space.
		*Input Data: 
			
		*Expected Outcome: 
			- The user can see Wiki Settings from Browse menu
			- The user A can see Page Permissions from More menu*/ 
		info("Select group to add permission");
		String groupUsers ="*:/"+groupName;
		wPermission.addPermisisonByType(groupUsers);
		wPermission.selectPermission(groupUsers, permissionType.Admin_Wiki);
		wPermission.savePermisison();
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(groupUsers);
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAPage(page);
		click(wHome.ELEMENT_MORE_LINK);
		waitForAndGetElement(wHome.ELEMENT_PERMISSION_LINK);
		
		click(wHome.ELEMENT_SEARCH_BROWSERS_DROPDOWN);
		waitForAndGetElement(wHome.ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);

 	}

	/**
	*<li> Case ID:139602.</li>
	*<li> Test Case Name: Verify Edit permission for a group.</li>
	*<li> Pre-Condition: - The Group A has already the permission: Edit Pages in the Wiki Settings
	- Some wiki pages are already created in this space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_VerifyEditPermissionForAGroup() {
		info("Test 8: Verify Edit permission for a group");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Wiki Permissions form appears*/

		/*Step number: 2
		*Step Name: Step 2: Delete Edit Pages permission for Group A
		*Step Description: 
			- Check the Group A permission
			- In the permission table, check the Edit Pages checkbox of the Group A
			- Uncheck permission "Admin Pages" and "Admin Wiki"
			- Click Save 
			-
			-> OK
		*Input Data: 
			
		*Expected Outcome: 
			- Edit Pages and View Page permission are selected
			- Other permission are not selected*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a new group");
		String groupName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupLabel = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupDesc = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.addGroup(groupName, groupLabel, groupDesc, true);
		arrayGroups.add(groupLabel);
		
		info("Add users to the group");
		String membership = permisMem.getDataContentByArrayTypeRandom(1);
		Utils.pause(2000);
		userAndGroup.selectGroup(groupLabel);
		userAndGroup.addUsersToGroup(arrayUsers.get(0),membership,false,true);
		
		info("Create new page 1");
		String page = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageContent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page,pageContent);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page);
		arrayPage.add(page);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 3
		*Step Name: Step 3: Check if the Group A has Edit Pages permission
		*Step Description: 
			- Log in with a user member of the Group A
			- Go to Wiki of the space.
		*Input Data: 
			
		*Expected Outcome: 
			- The user can view the wiki page and can see Edit Page, Add Page; 
			Move Page and Delete Page from More menu
			- The user can not see Wiki Settings from Browse menu
			- The user A can not see Page Permissions from More menu*/ 
		info("Select group to add permission");
		String groupUsers ="*:/"+groupName;
		wPermission.addPermisisonByType(groupUsers);
		wPermission.selectPermission(groupUsers, permissionType.Edit_Pages);
		wPermission.savePermisison();
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(groupUsers);
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAPage(page);
		waitForAndGetElement(wHome.ELEMENT_EDIT_PAGE_LINK);
		waitForAndGetElement(wHome.ELEMENT_ADD_PAGE_LINK);
		click(wHome.ELEMENT_MORE_LINK);
		waitForAndGetElement(wHome.ELEMENT_MOVE_PAGE);
		waitForAndGetElement(wHome.ELEMENT_DELETE_LINK);
		waitForElementNotPresent(wHome.ELEMENT_PERMISSION_LINK);
		
		click(wHome.ELEMENT_SEARCH_BROWSERS_DROPDOWN);
		waitForElementNotPresent(wHome.ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);


 	}}