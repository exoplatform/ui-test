package org.exoplatform.selenium.platform.wiki.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.wiki.WikiPermission.permissionType;
import org.exoplatform.selenium.platform.wiki.WikiPermission.userGroupTypes;
import org.testng.annotations.*;


	public class Wiki_Permission_Wiki_Permission_Add extends WIKI_TestConfig_2{

	/**
	*<li> Case ID:139325.</li>
	*<li> Test Case Name: Add permission for group by putting group's name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_AddPermissionForGroupByPuttingGroupsName() {
		info("Test 1: Add permission for group by putting group's name");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
		
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
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 2
		*Step Name: Step 2: Select group to add permission
		*Step Description: 
			- Put group's name in field
			- Click on Add icon
		*Input Data: /
			
		*Expected Outcome: 
			Selected group is listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info("Select group to add permission");
		String groupUsers ="*:/"+groupName;
		wPermission.addPermisisonByType(groupUsers);
		
		/*Step number: 3
		*Step Name: Step 3: Select permission for group
		*Step Description: 
			- Select permission for user to view/edit/admin
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			Users of the selected group have permission to view/edit/ admin .. with this page*/ 
		wPermission.selectPermission(groupUsers, permissionType.Edit_Pages);
		wPermission.selectPermission(groupUsers, permissionType.Admin_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(groupUsers);
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();

 	}

	/**
	*<li> Case ID:139327.</li>
	*<li> Test Case Name: Add permission for group by selecting directly.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_AddPermissionForGroupBySelectingDirectly() {
		info("Test 2: Add permission for group by selecting directly");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
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
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 2
		*Step Name: Step 2: Select group to add permission
		*Step Description: 
			- Click on Add Group icon
			- Select 1 group
			- Click on Add icon
		*Input Data: 
			
		*Expected Outcome: 
			-
			-> Selected group is listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info("Select group to add permission");
		String groupUsers ="*:/"+groupName;
		wPermission.addPermissionBySelect(groupLabel, "",userGroupTypes.Groups);
		
		/*Step number: 3
		*Step Name: Step 3: Select permission for group
		*Step Description: 
			- Select permission for user to view/edit
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			Users of this group have permission to view/edit this page*/ 
		wPermission.selectPermission(groupUsers, permissionType.Edit_Pages);
		wPermission.selectPermission(groupUsers, permissionType.Admin_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(groupUsers);
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();

 	}

	/**
	*<li> Case ID:139329.</li>
	*<li> Test Case Name: Add permission for group's membership by putting group's membership name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_AddPermissionForGroupsMembershipByPuttingGroupsMembershipName() {
		info("Test 3: Add permission for group's membership by putting group's membership name");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
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
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 2
		*Step Name: Step 2: Select group to add permission
		*Step Description: 
			- Put group membership into field
			- Click on Add icon
		*Input Data: 
			
		*Expected Outcome: 
			Users who have the selected persion are listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info("Select membership to add permission");
		String groupUsers ="*:/"+groupName;
		wPermission.addPermisisonByType(groupUsers);
		
		/*Step number: 3
		*Step Name: Step 3: Select permission for group
		*Step Description: 
			- Select permission for the membership to view/edit/admin
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			Users with the selected membership have permissions to view/edit/ admin .. with this page*/ 
		wPermission.selectPermission(groupUsers, permissionType.Edit_Pages);
		wPermission.selectPermission(groupUsers, permissionType.Admin_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(groupUsers);
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();
 	}

	/**
	*<li> Case ID:139330.</li>
	*<li> Test Case Name: Add permission for group's membership by selecting.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_AddPermissionForGroupsMembershipBySelecting() {
		info("Test 4: Add permission for group's membership by selecting");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
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
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 2
		*Step Name: Step 2: Select group to add permission
		*Step Description: 
			- Click on Membership icon
			- Select 1 membership
			- Click on Add icon
		*Input Data: 
			
		*Expected Outcome: 
			Selected membershipis listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info("Select group to add permission");
		String group ="*:/"+groupName;
		wPermission.addPermissionBySelect(groupLabel,membership,userGroupTypes.Membership);
		
		/*Step number: 3
		*Step Name: Step 3: Select permission for group
		*Step Description: 
			- Select permission for membership to view/edit
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			Usershaving the selected memberships have permission to view/edit this page*/ 
		wPermission.selectPermission(group, permissionType.Edit_Pages);
		wPermission.selectPermission(group, permissionType.Admin_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(group);
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();

 	}

	/**
	*<li> Case ID:139332.</li>
	*<li> Test Case Name: Add permission for multi-user at the same time.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_AddPermissionForMultiUserAtTheSameTime() {
		info("Test 5: Add permission for multi-user at the same time");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
		info("Create 3 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(3,password);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 2
		*Step Name: Step 2: Select user to add permission
		*Step Description: 
			- Click on Add User icon
			- Tick on some check
			-boxes corresponding with users want to add
			- Click on Add button
			- Click on Add icon
		*Input Data: 
			
		*Expected Outcome: 
			Selected users is listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info("Select user to add permission");
		wPermission.addPermissionBySelect(arrayUsers.get(0),"",userGroupTypes.Users_UserName);
		wPermission.addPermissionBySelect(arrayUsers.get(1),"",userGroupTypes.Users_UserName);
		wPermission.addPermissionBySelect(arrayUsers.get(2),"",userGroupTypes.Users_UserName);
	
		/*Step number: 3
		*Step Name: Step 3: Select permission for user
		*Step Description: 
			- Select permission for users to view/edit/admin
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			Usershas permission to view/edit/ admin .. with this page*/ 
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Edit_Pages);
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Admin_Pages);
		wPermission.selectPermission(arrayUsers.get(1), permissionType.Edit_Pages);
		wPermission.selectPermission(arrayUsers.get(1), permissionType.Admin_Pages);
		wPermission.selectPermission(arrayUsers.get(2), permissionType.Edit_Pages);
		wPermission.selectPermission(arrayUsers.get(2), permissionType.Admin_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		arrayGroupsPath.add(arrayUsers.get(1));
		arrayGroupsPath.add(arrayUsers.get(2));
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();
		
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(1),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();
		
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(2),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();
		
		

 	}

	/**
	*<li> Case ID:139334.</li>
	*<li> Test Case Name: Add permission for user by putting user's name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_AddPermissionForUserByPuttingUsersName() {
		info("Test 6: Add permission for user by putting user's name");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();
		
		/*Step number: 2
		*Step Name: Step 2: Select user to add permission
		*Step Description: 
			- Put user's name in field
			- Click on Add icon
		*Input Data: 
			
		*Expected Outcome: 
			Selected user is listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info("Select user to add permission");
		wPermission.addPermisisonByType(arrayUsers.get(0));
		
		
		/*Step number: 3
		*Step Name: Step 3: Select permission for user
		*Step Description: 
			- Check if view permission is set to user and don't check edit/admin permssion. 
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			user has view permission only*/ 
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		waitForElementNotPresent(wHome.ELEMENT_EDIT_PAGE_LINK);
		waitForElementNotPresent(wHome.ELEMENT_ADD_PAGE_LINK);
		click(wHome.ELEMENT_MORE_LINK);
		waitForElementNotPresent(wHome.ELEMENT_MOVE_PAGE);
		waitForElementNotPresent(wHome.ELEMENT_DELETE_LINK);

 	}

	/**
	*<li> Case ID:139336.</li>
	*<li> Test Case Name: Add permission for user by searching user by email.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_AddPermissionForUserBySearchingUserByEmail() {
		info("Test 7: Add permission for user by searching user by email");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 2
		*Step Name: Step 2: Searching user
		*Step Description: 
			- Click on Add User icon 
			- Choose Email
			- Put keyword in Search field (case
			-sensitive)
			- Click on Search icon
		*Input Data: 
			
		*Expected Outcome: 
			All users corresponding with the keyword are listed*/

		/*Step number: 3
		*Step Name: Step 3: Select user to add permission
		*Step Description: 
			- Tick on check
			-box corresponding with user want to add
			- Click on Add button
			- Click on Add icon
		*Input Data: 
			
		*Expected Outcome: 
			Selected user is listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info(" Select user to add permission");
		String email = arrayUsers.get(0)+"@gmail.com";
		wPermission.addPermissionBySelect(email,"",userGroupTypes.Users_Email);
		
		/*Step number: 4
		*Step Name: Step 4: Select permission for user
		*Step Description: 
			- Select permission for user to view/edit/admin
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			User has permission to view/edit/ admin*/
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Edit_Pages);
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Admin_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();

 	}

	/**
	*<li> Case ID:139338.</li>
	*<li> Test Case Name: Add permission for user by searching user by first name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_AddPermissionForUserBySearchingUserByFirstName() {
		info("Test 8: Add permission for user by searching user by first name");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 2
		*Step Name: Step 2: Searching user
		*Step Description: 
			- Click on Add User icon 
			- ChooseFirst Name
			- Put keyword in Search field(case
			-sensitive)
			- Click on Search icon
		*Input Data: 
			
		*Expected Outcome: 
			All users corresponding with the keyword are listed*/

		/*Step number: 3
		*Step Name: Step 3: Select user to add permission
		*Step Description: 
			- Tick on check
			-box corresponding with user want to add
			- Click on Add button
			- Click on Add icon
		*Input Data: 
			
		*Expected Outcome: 
			Selected user is listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info("Select user to add permission");
		wPermission.addPermissionBySelect(arrayUsers.get(0),"",userGroupTypes.Users_FirstName);
		
		/*Step number: 4
		*Step Name: Step 4: Select permission for user
		*Step Description: 
			- Select permission for user to view/edit/admin
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			User has permission to view/edit/ admin .. with this page*/ 
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Edit_Pages);
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Admin_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();

 	}

	/**
	*<li> Case ID:139340.</li>
	*<li> Test Case Name: Add permission for user by searching user by last name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_AddPermissionForUserBySearchingUserByLastName() {
		info("Test 9: Add permission for user by searching user by last name");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 2
		*Step Name: Step 2: Searching user
		*Step Description: 
			- Click on Add User icon 
			- Choose Last Name
			- Put keyword in Search field (case
			-sensitive)
			- Click on Search icon
		*Input Data: 
			
		*Expected Outcome: 
			All users corresponding with the keyword are listed*/

		/*Step number: 3
		*Step Name: Step 3: Select user to add permission
		*Step Description: 
			- Tick on check
			-box corresponding with user want to add
			- Click on Add button
			- Click on Add icon
		*Input Data: 
			
		*Expected Outcome: 
			Selected user is listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info("Select user to add permission");
		wPermission.addPermissionBySelect(arrayUsers.get(0),"",userGroupTypes.Users_LastName);
		
		/*Step number: 4
		*Step Name: Step 4: Select permission for user
		*Step Description: 
			- Select permission for user to view/edit/admin
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			User has permission to view/edit/ admin .. with this page*/ 
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Edit_Pages);
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Admin_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();

 	}

	/**
	*<li> Case ID:139342.</li>
	*<li> Test Case Name: Add permission for user by searching user by user name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_AddPermissionForUserBySearchingUserByUserName() {
		info("Test 10 Add permission for user by searching user by user name");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 2
		*Step Name: Step 2: Searching user
		*Step Description: 
			- Click on Add User icon 
			- Choose User Name
			- Put keyword in Search field (case
			-sensitive)
			- Click on Search icon
		*Input Data: 
			
		*Expected Outcome: 
			All users corresponding with the keyword are listed*/

		/*Step number: 3
		*Step Name: Step 3: Select user to add permission
		*Step Description: 
			- Tick on check
			-box corresponding with user want to add
			- Click on Add button
			- Click on Add icon
		*Input Data: 
			
		*Expected Outcome: 
			Selected user is listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info("Select user to add permission");
		wPermission.addPermissionBySelect(arrayUsers.get(0),"",userGroupTypes.Users_UserName);
		
		/*Step number: 4
		*Step Name: Step 4: Select permission for user
		*Step Description: 
			- Select permission for user to view/edit/admin
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			User has permission to view/edit/ admin .. with this page*/ 
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Edit_Pages);
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Admin_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();

 	}

	/**
	*<li> Case ID:139344.</li>
	*<li> Test Case Name: Add permission for user by selecting directly.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_AddPermissionForUserBySelectingDirectly() {
		info("Test 11 Add permission for user by selecting directly");
		/*Step Number: 1
		*Step Name: Step 1: Open form to add permission for space
		*Step Description: 
			- Go to Browse 
			-
			-> Wiki Settings
			- Choose Permission tab
		*Input Data: 
			
		*Expected Outcome: 
			Space Permissions form appears*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info(" Open form to add permission for space");
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSetting.goToPermissionTab();

		/*Step number: 2
		*Step Name: Step 2: Select user to add permission
		*Step Description: 
			- Click on Add User icon
			- Tick on check
			-box corresponding with user want to add
			- Click on Add button
			- Click on Add icon
		*Input Data: 
			
		*Expected Outcome: 
			Selected user is listed in permission's list with view permission and no edit/admin pages/admin wiki by default*/
		info("Select user to add permission");
		wPermission.addPermissionBySelect(arrayUsers.get(0),"",userGroupTypes.Users_UserName);
		/*Step number: 3
		*Step Name: Step 3: Select permission for user
		*Step Description: 
			- Select permission for user to view/edit/admin
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			User has permission to view/edit/ admin .. with this page*/ 
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Edit_Pages);
		wPermission.selectPermission(arrayUsers.get(0), permissionType.Admin_Pages);
		wPermission.savePermisison(true);
		wHome.confirmWaringMessage(true);
		arrayGroupsPath.add(arrayUsers.get(0));
		info("Mermbers of the selected group has view/edit permission");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0),password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToAddBlankPage();

 	}}