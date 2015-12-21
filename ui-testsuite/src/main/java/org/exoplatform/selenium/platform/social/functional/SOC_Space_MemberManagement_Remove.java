package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.testng.annotations.*;


	public class SOC_Space_MemberManagement_Remove extends SOC_TestConfig{

	/**
	*<li> Case ID:122433.</li>
	*<li> Test Case Name: Remove a normal member from the space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_RemoveANormalMemberFromTheSpace() {
		info("Test 1: Remove a normal member from the space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Invited user
		*Input Data: 
			- Creator selects the Space which he/she has created at step 1.
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select [Members] tab
			- Enter valid user name or select a user from list
			- Click [invite] icon
		*Expected Outcome: 
			Send invitation successfully:
			- On screen ofinvitation sender: 
			if no has list Invited user, display list invited user, 
			else add name of user was invited into list.*/
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.goToMemberTab();
		 setSpaceMg.inviteUser(username2,true,fullName2);
		 
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Accept invitation
		*Input Data: 
			- Login with invited user
			- Go to Spaces manage/Invitation
			- Click accept
		*Expected Outcome: 
			- Login in successfully
			- In my spaces of invited user: display space which added at step 1*/
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.goToInvitationsReceivedTab();
	     spaMg.acceptAInvitation(space);
	     
		/*Step number: 4
		*Step Name: 
		*Step Description: 
			Step 4: Remove member from space
		*Input Data: 
			- The Manager of space which has created at step 1, signs in 
			- Go to space setting
			- Select normal memberand click on [remove] icon
		*Expected Outcome: 
			User has been removed from list member of the space and space group. User can't access this space but user can send request to join back. The space is removed from my space list and added into Public spaces list. [Leave] button is changed to [Request to join] button.*/ 
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     setSpaceMg.removeUserFromMemberlist(fullName2);
 	}

	/**
	*<li> Case ID:122612.</li>
	*<li> Test Case Name: Delete user from Space group.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_DeleteUserFromSpaceGroup() {
		info("Test 2: Delete user from Space group");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Login
			- Click "Join a space" button
			- Input all required fields and click Save
		*Expected Outcome: 
			- Add New Space form is appeared
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Invite user
		*Input Data: 
			- Go to Space Settings
			- Select Members tab
			- Select user or group and click Invite button
		*Expected Outcome: 
			Send invitation successfully, user is displayed on inviting list*/
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.goToMemberTab();
		 setSpaceMg.inviteUser(username2,true,fullName2);
		 
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			Step 3: Accept the invitation
		*Input Data: 
			- Login as invited user
			- Click "Join a space" button
			- Go to Invitation Received tab
			- Click Accept button
		*Expected Outcome: 
			- User become member of space*/
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.acceptAInvitation(space);
	     
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Delete a member from space group
		*Input Data: 
			- Login as admin
			- Go to Group and Roles page
			- Go to Group Management tab
			- Select space group which is created at step 1 and delete user at step 3 from this group
		*Expected Outcome: 
			- User is removed from space group and member list of this space*/
	     magAc.signIn(USER_ROOT,PASS_ROOT);
	     navTool.goToUsersAndGroupsManagement();
         userGroupMg.goToGroupTab();
         ArrayList<String> groups=spGroupsData.getArrayGroupByType(4);
 		 String[] arrayGroupPath ={groups.get(0),space};
 		 userGroupMg.selectGroup(arrayGroupPath);
 		 userGroupMg.removeUser(username2);
 	}

	/**
	*<li> Case ID:122624.</li>
	*<li> Test Case Name: Remove user Root from the Space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_RemoveUserRootFromTheSpace() {
		info("Test 3: Remove user Root from the Space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system ( excepted root)
			- Select space page to view
			- Click on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Invited user
		*Input Data: 
			- Add Root into space group or invite user Root to join in Space
		*Expected Outcome: 
			- Invited member successfully*/
		 spaHome.goToSpaceSettingTab();
		 setSpaceMg.inviteUser(username2,false,"");
		 
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Remove a member from the space
		*Input Data: 
			- The Manager of space which has created at step 1, signs in and select this space.
			- Select user Root and click on [remove]
		*Expected Outcome: 
			- User Root has been removed from list member of the space and space group but Root has all right like manager of space.*/ 
		 magAc.signIn(USER_ROOT,PASS_ROOT);
		 navTool.goToUsersAndGroupsManagement();
         userGroupMg.goToGroupTab();
         ArrayList<String> groups=spGroupsData.getArrayGroupByType(4);
 		 String[] arrayGroupPath ={groups.get(0),space};
 		 userGroupMg.selectGroup(arrayGroupPath);
 		 userGroupMg.removeUser(username2);
 	}

	/**
	*<li> Case ID:122633.</li>
	*<li> Test Case Name: Remove a normal member from the space ( by request to join).</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_RemoveANormalMemberFromTheSpace() {
		info("Test 4: Remove a normal member from the space ( by request to join)");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Request to join space by step 1
		*Input Data: 
			- Login by another user ( excepted root)
			- Go to Spaces public 
			- Click Request to join
		*Expected Outcome: 
			- Login in successfully 
			- In public space: display space which added at step 1*/
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.searchSpace(space, "");
	     spaMg.requestToJoinSpace(space);
	     
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Accept request to join
		*Input Data: 
			- Login with user which created space at step 1
			- Go to Spaces manage/Pending
			- Click Validate
		*Expected Outcome: 
			- Login in successfully
			- In my spaces of invited user: display space which added at step 1*/
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     setSpaceMg.acceptRequest(fullName2);
	     
	     magAc.signIn(username2,password);
	     hp.goToMySpaces();
	     spaMg.searchSpace(space, "");
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Remove a member from the space
		*Input Data: 
			- The Manager of space which has created at step 1, signs in 
			- Go to space setting
			- Select normal memberand click on [remove]
		*Expected Outcome: 
			User has been removed from list member of the space and space group. 
			User can't access this space but user can send request to join back. 
			The space is removed from my space list and added into Public spaces list. 
			[Leave] button is changed to [Request to join] button.*/ 
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     setSpaceMg.removeUserFromMemberlist(fullName2);
	     
	     magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.searchSpace(space, "");
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN.replace("${space}",space),2000,1);
	     hp.goToMySpaces();
	     waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     
 	}
}