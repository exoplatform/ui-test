package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


	public class SOC_Space_MemberManagement_Invite extends SOC_TestConfig{
	/**
	*<li> Case ID:122310.</li>
	*<li> Test Case Name: Add user into Space group.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
		@Test
		public  void test01_AddUserIntoSpaceGroup() {
			info("Test 1: Add user into Space group");
			String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
			String email1 = username1+"@gmail.com";
			String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
			String email2 = username2+"@gmail.com";
			info("Add new user");
			navTool.goToAddUser();
			addUserPage.addUser(username1, password, email1, username1, username1);
			addUserPage.addUser(username2, password, email2, username2, username2);
			navTool.goToUsersAndGroupsManagement();
			userGroupMg.addUserAdmin(username1);
			magAc.signIn(username1,password);
			Utils.pause(3000);

			/*Step Number: 1
			*Step Name: Step 1: Create a new Space
			*Step Description: 
				- Login and go to intranet home
				- Click "Join a new space" button
				- Input all required fields with valid data and click Save
			*Input Data: 
				
			*Expected Outcome: 
				- Add New Space form is appeared
				- Create new Space successfully.*/
			String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			
			info("Create a space");
			hp.goToAllSpace();
			spaMg.addNewSpaceSimple(space,space);
			
			/*Step number: 2
			*Step Name: Step 2: Add user into space group
			*Step Description: 
				- Login as admin
				- Go to Groups and Roles
				- Click Group Management tab
				- Select space group created at step 1
				- Select a user and select membership is member or manager
				- Click on Save button
			*Input Data: 
				
			*Expected Outcome: 
				- Group management form is shown
				- User becomes member or manage of space create at step 1*/ 
	         navTool.goToUsersAndGroupsManagement();
	         userGroupMg.goToGroupTab();
	         ArrayList<String> groups=spGroupsData.getArrayGroupByType(4);
	 		 String[] arrayGroupPath ={groups.get(0),space};
	 		 String membership = membershipData.getContentByIndex(4);
	 		 userGroupMg.selectGroup(arrayGroupPath);
	 		 userGroupMg.addUsersToGroup(username2,membership,false,true);
	 	}

	/**
	*<li> Case ID:122312.</li>
	*<li> Test Case Name: Invited user accept invitation.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
		@Test
		public  void test02_InvitedUserAcceptInvitation() {
			info("Test 2: Invited user accept invitation");
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
			Utils.pause(3000);

			/*Step Number: 1
			*Step Name: Step 1: Create a new Space
			*Step Description: 
				- Sign in system
				- Select space page to view
				- Click on [Add new Space] 
				- Enter all valid data
				- Click [Create]
			*Input Data: 
				
			*Expected Outcome: 
				- Create new Space successfully.*/
	        String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			
			info("Create a space");
			hp.goToAllSpace();
			spaMg.addNewSpaceSimple(space,space);
			
			/*Step number: 2
			*Step Name: Step 2: Send invitation
			*Step Description: 
				- Creator selects the Space which he/she has created at step 1.1.
				- Click on [Edit space] icon or access the Space and select space settings portlet
				- Select [Members] tab
				- Enter valid user name or select a user from list
				- Click [invite]
			*Input Data: 
				
			*Expected Outcome: 
				Send invitation successfully:*/
			spaHome.goToSpaceSettingTab();
			setSpaceMg.goToMemberTab();
			setSpaceMg.inviteUser(username2,true,fullName2);
			
			/*Step number: 3
			*Step Name: Step 3: Invited user accepts invitation
			*Step Description: 
				- Invited user signs in 
				- Select space page
				- Select invitations space list
				- Select Space which has created at step 1.
				- Click on [Accept] button
			*Input Data: 
				
			*Expected Outcome: 
				User becomes member of the space:
				- On the screen of invited user: [Accept] & [Ignore] button are changed by button [leave]. 
				  The space is removed from invitations list and added into my space list. 
				  Member can access the Space by click on displaying name of the space.
				- On screen of user who sends invitation: 
				  user name of invited user is removed from invited user list but it has been added in member list.
				- When user accesses the User list portlet, user will see profile of new member.*/ 
		    magAc.signIn(username2,password);
		    info("On the screen of invited user: [Accept] & [Ignore] button are changed by button [leave]");
		    hp.goToAllSpace();
		    spaMg.goToInvitationsReceivedTab();
		    spaMg.acceptAInvitation(space);
		    
		    hp.goToMySpaces();
		    waitForAndGetElement(spaMg.ELEMENT_SPACE_LEAVE_BTN.replace("${space}",space),2000,1);
		   
		    info("The space is removed from invitations list and added into my space list");
		    spaMg.goToInvitationsReceivedTab();
		    waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
		    
		    info("Member can access the Space by click on displaying name of the space.");
		    hp.goToSpecificSpace(space);
		    
		    magAc.signIn(username1,password);
		    info("user name of invited user is removed from invited user list but it has been added in member list");
		    hp.goToSpecificSpace(space);
		    spaHome.goToSpaceSettingTab();
		    setSpaceMg.goToMemberTab();
		    waitForAndGetElement(setSpaceMg.ELEMENT_USER_IN_MEMBER_TABLE.replace("${fullName}",fullName2));
		    waitForElementNotPresent(setSpaceMg.ELEMENT_SPACE_INVITED_USER_TABLE.replace("${user}",fullName2));
		    
		    info("When user accesses the User list portlet, user will see profile of new member.");
		    spaMg.goToMemberTab();
		    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_NAME.replace("${fullName}",fullName2),2000,1);
		    spaMg.goToUserProfilePage(fullName2);
		    waitForAndGetElement(myProfile.ELEMENT_PROFILE_TITLE.replace("${fullName}",fullName2),2000,1);
	 	}

	/**
	*<li> Case ID:122314.</li>
	*<li> Test Case Name: Send invitation to user who is not member of space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
		@Test
		public  void test03_SendInvitationToUserWhoIsNotMemberOfSpace() {
			info("Test 3: Send invitation to user who is not member of space");
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
			*Step Name: Step 1: Create a new Space
			*Step Description: 
				- Sign in system
				- Select space page to view
				- Click on [Add new Space] 
				- Enter all valid data
				- Click [create]
			*Input Data: 
				
			*Expected Outcome: 
				- Create new Space successfully.*/
	        String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			
			info("Create a space");
			hp.goToAllSpace();
			spaMg.addNewSpaceSimple(space,space);
			
			/*Step number: 2
			*Step Name: Step 2: Send invitation
			*Step Description: 
				- Creator selects the Space which he/she has created at step 1.
				- Click on [Edit space] icon or access the Space and select space settings portlet
				- Select [Members] tab
				- Enter valid user name or select a user from list
				- Click [invite] icon
			*Input Data: 
				
			*Expected Outcome: 
				Send invitation successfully:
				- On screen ofinvitation sender: if no has list Invited user,
				 display list invited user, else add name of user was invited into list.*/
			spaHome.goToSpaceSettingTab();
			setSpaceMg.goToMemberTab();
			setSpaceMg.inviteUser(username2,true,fullName2);
			
			/*Step number: 3
			*Step Name: Step 3: Check invitation sending successfully
			*Step Description: 
				- Invited user signs in
				- Select space page 
				- Select invitation spaces list
			*Input Data: 
				
			*Expected Outcome: 
				By the default: Name of space which has sent invitation to user, 
				are list on invited space. If user accept this invitation, 
				Click on [Accept] button else click on [Ignore] button*/ 
			 magAc.signIn(username2,password);
		    info("On the screen of invited user: [Accept] & [Ignore] button are changed by button [leave]");
		    hp.goToAllSpace();
		    spaMg.goToInvitationsReceivedTab();
		    waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN.replace("${space}",space),2000,1);
		    waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
		    spaMg.ignoreAInvitation(space);
	 	}

	/**
	*<li> Case ID:122423.</li>
	*<li> Test Case Name: Send invitation to users who are existing in inviting list.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
		@Test
		public  void test04_SendInvitationToUsersWhoAreExistingInInvitingList() {
			info("Test 4: Send invitation to users who are existing in inviting list");
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
				- Click on [Add new Space] 
				- Enter name of the space
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
				- Enter user name or select a user who is the member of the Space
				- Click [invite]
			*Expected Outcome: 
				Invited user successfully*/
			spaHome.goToSpaceSettingTab();
			setSpaceMg.goToMemberTab();
			setSpaceMg.inviteUser(username2,true,fullName2);
			
			/*Step number: 3
			*Step Name: -
			*Step Description: 
				Step 3: Send invitation to users who are existing in inviting list
			*Input Data: 
				- Enter user name or select users who are existing in invitation list joining to the Space
			*Expected Outcome: 
				Show warning message: â€ Some users have already existed in the inviting list, including: usernameâ€*/ 
			ArrayList<String> warningTextArray=spWarnMessg.getArrayContentByType(1);
			String warningText=warningTextArray.get(0);
			setSpaceMg.inviteUser(username2,false,"");
			waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_WARNING_MESSAGE.replace("${warningText}",warningText).replace("${username}",username2),2000,1);
			button.ok();
	 	}

	/**
	*<li> Case ID:122456.</li>
	*<li> Test Case Name: Manager Cancel the request.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
		@Test
		public  void test05_RemoveANormalMemberFromTheSpace() {
			info("Test 5: Remove a normal member from the space");
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
				- On screen ofinvitation sender: if no has list Invited user, 
				display list invited user, else add name of user was invited into list.*/
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
		     setSpaceMg.removeUserFromMemberlist(username2);
	 	}

	/**
	*<li> Case ID:122472.</li>
	*<li> Test Case Name: Send request to Space at hidden mode and open for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
		@Test
		public  void test06_SendRequestToSpaceAtHiddenModeAndOpenForRegistration() {
			info("Test 6: Send request to Space at hidden mode and open for registration");
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
				Step 1: create a new space at the hidden and open mode
			*Input Data: 
				- Sign in system
				- Select space page
				- Click on [Add new space] button 
				- Enter valid name 
				- Select visibility and check in hidden and open
				- Click on [create] button.
			*Expected Outcome: 
				Create a new space successfully*/
			String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			String regist =spRegisData.getSpaceRegistration(0);
			String visib = spVisiData.getSpaceVisible(1);
			String[] arrayRight ={regist,visib};
			
			info("Create a space");
			hp.goToMySpaces();
			spaMg.addNewSpaceSimple(space,space,60000);
			info("Set permission for the space");
			spaHome.goToSpaceSettingTab();
			setSpaceMg.goToAccessEditTab();
			setSpaceMg.setPermissionForSpace(arrayRight);
			
			/*Step number: 2
			*Step Name: -
			*Step Description: 
				Step 2: Send request
			*Input Data: 
				- Other user sign in
				- Go to space page
				- Select All spaces list
			*Expected Outcome: 
				User can't see the space which has created at step 1. So user can't send request*/ 
			 magAc.signIn(username2,password);
		     hp.goToAllSpace();
		     spaMg.searchSpace(space,"");
		     waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
		     
	 	}
	/**
	*<li> Case ID:122474.</li>
	*<li> Test Case Name: Send request to Space at hidden mode and validation for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
		@Test
		public  void test07_SendRequestToSpaceAtHiddenModeAndValidationForRegistration() {
			info("Test 7: Send request to Space at hidden mode and validation for registration");
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
				Step 1: create a new space at the hidden and validation mode
			*Input Data: 
				- Sign in system
				- Select space page
				- Click on [Add new space] button 
				- Enter valid name 
				- Select visibility and check in hidden and validation
				- Click on [create] button.
			*Expected Outcome: 
				Create a new space successfully*/
			String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			String regist =spRegisData.getSpaceRegistration(1);
			String visib = spVisiData.getSpaceVisible(1);
			String[] arrayRight ={regist,visib};
			
			info("Create a space");
			hp.goToMySpaces();
			spaMg.addNewSpaceSimple(space,space,60000);
			info("Set permission for the space");
			spaHome.goToSpaceSettingTab();
			setSpaceMg.goToAccessEditTab();
			setSpaceMg.setPermissionForSpace(arrayRight);
			
			/*Step number: 2
			*Step Name: -
			*Step Description: 
				Step 2: Send request
			*Input Data: 
				- Other user sign in
				- Go to space page
				- Select All space list
			*Expected Outcome: 
				User can't see the space which has created at step 1. So user can't send request*/ 
			 magAc.signIn(username1,password);
		     hp.goToAllSpace();
		     spaMg.searchSpace(space,"");
		     waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
		     
	 	}

	/**
	*<li> Case ID:122477.</li>
	*<li> Test Case Name: Send request to Space at hidden mode and close for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
		@Test
		public  void test08_SendRequestToSpaceAtHiddenModeAndCloseForRegistration() {
			info("Test 8: Send request to Space at hidden mode and close for registration");
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
				Step 1: create a new space at the hidden and close mode
			*Input Data: 
				- Sign in system
				- Select space page
				- Click on [Add new space] button 
				- Enter valid name 
				- Select visibility and check in hidden and close
				- Click on [create] button.
			*Expected Outcome: 
				Create a new space successfully*/
			String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			String regist =spRegisData.getSpaceRegistration(2);
			String visib = spVisiData.getSpaceVisible(1);
			String[] arrayRight ={regist,visib};
			
			info("Create a space");
			hp.goToMySpaces();
			spaMg.addNewSpaceSimple(space,space,60000);
			info("Set permission for the space");
			spaHome.goToSpaceSettingTab();
			setSpaceMg.goToAccessEditTab();
			setSpaceMg.setPermissionForSpace(arrayRight);
			
			/*Step number: 2
			*Step Name: -
			*Step Description: 
				Step 2: Send request
			*Input Data: 
				- Other user sign in
				- Go to space page
				- Select All space list
			*Expected Outcome: 
				User can't see the space which has created at step 1. So user can't send request*/ 
			magAc.signIn(username2,password);
		     hp.goToAllSpace();
		     spaMg.searchSpace(space,"");
		     waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
		     
	 	}


	/**
	*<li> Case ID:122482.</li>
	*<li> Test Case Name: Add member with membership is manager into space group.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
		@Test
		public  void test09_AddMemberWithMembershipIsManagerIntoSpaceGroup() {
			info("Test 09: Add member with membership is manager into space group");
			String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
			String email1 = username1+"@gmail.com";
			
			info("Add new user");
			navTool.goToAddUser();
			addUserPage.addUser(username1, password, email1, username1, username1);
			/*Step Number: 1
			*Step Name: -
			*Step Description: 
				Step 1: Create a new Space
			*Input Data: 
				- Login and go to intranet home
				- Click "Join a new space" button
				- Input all required fields with valid data and click Save
			*Expected Outcome: 
				- Add New Space form is appeared
				- Create new Space successfully.*/
			String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
			info("Create a space");
			hp.goToMySpaces();
			spaMg.addNewSpaceSimple(space,space,60000);
			
			/*Step number: 2
			*Step Name: -
			*Step Description: 
				Step 2: Add member with membership is manager, into space group
			*Input Data: 
				- Login as admin
				- Go to Groups and Roles
				- Click Group Management tab
				- Select space group created at step 1
				- Select a user and select membership is member or manager
				- Click on Save button
			*Expected Outcome: 
				- Group management form is shown
				- User becomes member or manage of space create at step 1*/ 
			 navTool.goToUsersAndGroupsManagement();
	         userGroupMg.goToGroupTab();
	         ArrayList<String> groups=spGroupsData.getArrayGroupByType(4);
			 String[] arrayGroupPath ={groups.get(0),space};
			 String membership = membershipData.getContentByIndex(4);
			 userGroupMg.selectGroup(arrayGroupPath);
			 Utils.pause(2000);
			 userGroupMg.addUsersToGroup(username1,membership,false,true);
			 
	 	}


	/**
	*<li> Case ID:122487.</li>
	*<li> Test Case Name: User cancel request.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_UserCancelRequest() {
		info("Test 10 User cancel request");
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
			- Sign in system
			- Select space page to view
			- Click on [Add new Space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Users send request
		*Input Data: 
			- User, who is not member of the Space which has created at step 1, log in
			- Select space page to view
			- Select All space list and selects this space and click Request to join
		*Expected Outcome: 
			Send request successfully*/
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.requestToJoinSpace(space);
	     
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: User cancel request
		*Input Data: 
			- Select pending space list
			- Select the space which user sent request
			- Click on Cancel button
		*Expected Outcome: 
			User cancel the request joining the space. 
			The space is leave from Pending space and is added back All spaces list*/ 
        spaMg.goToRequestPendingTab();
        spaMg.cancelPendingRequest(space);
        hp.goToAllSpace();
        spaMg.searchSpace(space, "");
        waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
 	}

	/**
	*<li> Case ID:122492.</li>
	*<li> Test Case Name: Send request to Space at Visible mode and open for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_SendRequestToSpaceAtVisibleModeAndOpenForRegistration() {
		info("Test 11 Send request to Space at Visible mode and open for registration");
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
			Step 1: create a new space at the visible and open mode
		*Input Data: 
			- Sign in system
			- Select space page
			- Click on [Add new space] button 
			- Enter valid name 
			- Select visibility and check in visible and open
			- Click on [create] button.
		*Expected Outcome: 
			Create a new space successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send request
		*Input Data: 
			- Other user sign in
			- Go space page
			- Select All spaces list
			- Select the space which has created and click on [Request to Join] button
		*Expected Outcome: 
			User becomes member of the space*/
		magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.joinToSpace(space);
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Check request sending successfully
		*Input Data: 
			- user created the space sign in
			- Go to space page
			- Select the space and click [edit space] or access the space and access the space setting portlet
			- Select [members] tab
			- Or select Member portlet
		*Expected Outcome: 
			Name of user at step 2, is displayed in member list of the space*/ 
	     
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     spaMg.goToMemberTab();
	     waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_NAME.replace("${fullName}",fullName2),2000,1);

 	}

	/**
	*<li> Case ID:122496.</li>
	*<li> Test Case Name: Send request to Space at Visible mode and Validation for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_SendRequestToSpaceAtVisibleModeAndValidationForRegistration() {
		info("Test 12 Send request to Space at Visible mode and Validation for registration");
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
			Step 1: create a new space at the visible and validation mode
		*Input Data: 
			- Sign in system
			- Select space page
			- Click on [Add new space] button 
			- Enter valid name 
			- Select visibility and check in visible and Validation
			- Click on [create] button.
		*Expected Outcome: 
			Create a new space successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(1);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send request
		*Input Data: 
			- Other user sign in
			- Go space page
			- Selectpublic spaces list
			- Select the space which has created and click on [Request to Join] button
		*Expected Outcome: 
			Send request successfully: The space is move out Public space and added into pending space*/
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.requestToJoinSpace(space,true);
	     spaMg.goToRequestPendingTab();
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Check request sending successfully
		*Input Data: 
			- User created the space sign in
			- Go to space page
			- Select the space and click [edit space] or access the space and access the space setting portlet
			- Select [members] tab
		*Expected Outcome: 
			Name of user at step 3, is in pending list of the space.
			Click on [validate] if manager accept the request else click on [decline] button*/ 
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     setSpaceMg.goToMemberTab();
	     setSpaceMg.acceptRequest(username2);

 	}

	/**
	*<li> Case ID:122498.</li>
	*<li> Test Case Name: Send request to Space at Visible mode and Close for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_SendRequestToSpaceAtVisibleModeAndCloseForRegistration() {
		info("Test 13 Send request to Space at Visible mode and Close for registration");
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
			Step 1: create a new space at the visible and close mode
		*Input Data: 
			- Sign in system
			- Select space page
			- Click on [Add new space] button 
			- Enter valid name 
			- Select visibility and check in visible and close
			- Click on [create] button.
		*Expected Outcome: 
			Create a new space successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(2);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send request
		*Input Data: 
			- Other user sign in
			- Go space page
			- Select All space list
			- Select the space which has created
		*Expected Outcome: 
			Don't see any button to send request. Only manager of space can send invitation to user*/ 
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.searchSpace(space, "");
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForElementNotPresent(spaMg.ELEMENT_REQUEST_TO_JOIN_SPACE_BTN.replace("${space}", space));
	     
 	}

	/**
	*<li> Case ID:122505.</li>
	*<li> Test Case Name: User, who is not member of the space, sends request.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_UserWhoIsNotMemberOfTheSpaceSendsRequest() {
		info("Test 14 User, who is not member of the space, sends request");
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
			- Sign in system
			- Select space page to view
			- Click on [Add new Space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Users send request
		*Input Data: 
			- User, who is not member of the Space which has created at step 1, log in
			- Select space page to view
			- Select All spaces list andselects this space and click Request to join
		*Expected Outcome: 
			Send request successfully: 
			- On screen of sender request: 
			The space is moved from Public space and added into Pending space list and user can using 
			Cancel button to Cancel the request*/
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.requestToJoinSpace(space,true);
	     spaMg.goToRequestPendingTab();
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_CANCEL_BUTTON.replace("${space}",space));
	     
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Check request sending successfully
		*Input Data: 
			- user created the space sign in
			- Go to space page
			- Select my space list
			- Select the space and click [edit] or access the space and access the space setting portlet
			- Select [members] tab
		*Expected Outcome: 
			Display list pending user, name of user was sent request, is added into list.*/ 
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     setSpaceMg.goToMemberTab();
	     waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_MEMBERS_TAB_VALIDATE_REQUEST_jOINT.replace("${user}",username2),2000,1);

 	}

	/**
	*<li> Case ID:122509.</li>
	*<li> Test Case Name: The Manager of Space declines the request.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_TheManagerOfSpaceDeclinesTheRequest() {
		info("Test 15 The Manager of Space declines the request");
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
			- Click on [Add new Space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Users send request
		*Input Data: 
			- User, who is not member of the Space which has created at step 1, log in
			- Select space page to view
			- Select All space list andselects this space and click Request to join
		*Expected Outcome: 
			Send request successfully*/
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.requestToJoinSpace(space,true);
	     
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: The Manager of Space declines the request
		*Input Data: 
			- The manger of the Space which has just created at step 1, sign in system.
			- Select this space and click on [Edit space] icon or access the space and select Space settings
			- Select [Members] tab, at list pending user, select user who sent request at step 2 and click on [Decline] icon
		*Expected Outcome: 
			- User has been declined, and be removed from list pending user. 
			- On Screen of sender request, 
			when user select this Space, user see[Cancel] button be changed to [Request to Join] button*/ 
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     setSpaceMg.goToMemberTab();
	     setSpaceMg.declineRequest(fullName2);

	     magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.searchSpace(space, "");
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForElementNotPresent(spaMg.ELEMENT_SPACE_CANCEL_BUTTON.replace("${space}",space));
	     waitForAndGetElement(spaMg.ELEMENT_REQUEST_TO_JOIN_SPACE_BTN.replace("${space}", space));
	     
 	}

	/**
	*<li> Case ID:122514.</li>
	*<li> Test Case Name: The Manager of Space validates the request.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_TheManagerOfSpaceValidatesTheRequest() {
		info("Test 16 The Manager of Space validates the request");
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
			- Sign in system
			- Select space page to view
			- Click on [Add new Space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Users send request
		*Input Data: 
			- User, who is not member of the Space which has created at step 1, log in
			- Select space page to view
			- Select All space list andselects this space and click Request to join
		*Expected Outcome: 
			Send request successfully*/
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.requestToJoinSpace(space,true);
	    
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: The Manager of Space Accepts the request
		*Input Data: 
			- The manager of the Space which has just created at step 1, sign in system.
			- Select this space and click on [Edit space] icon or access space and select space settings portlet
			- Select [Members] tab, at list pending user, select user who sent request at step 2 and click on [Validate] icon
		*Expected Outcome: 
			- Invited user is removed from list pending user and added into list of member*/
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     setSpaceMg.goToMemberTab();
	     setSpaceMg.acceptRequest(username2);

		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: check accept request
		*Input Data: 
			- Log in by user who is validated request
			- Go to Space page
			- Select my space
		*Expected Outcome: 
			The space is added into my space list of user and by the name of space, 
			there is a Leave button to allow user leave out space*/ 
	     magAc.signIn(username2,password);
	     hp.goToMySpaces();
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_LEAVE_BTN.replace("${space}",space),2000,1);
	     
 	}

	/**
	*<li> Case ID:122535.</li>
	*<li> Test Case Name: Send invitation to member of space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test17_SendInvitationToMemberOfSpace() {
		info("Test 17 Send invitation to member of space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new Space] 
			- Enter all valid data
			- Click [Create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send invitation
		*Input Data: 
			- Creator selects the Space which he/she has created at step 1.
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select [Members] tab
			- Enter user name or select a user who is the member of the Space
			- Click [invite]
		*Expected Outcome: 
			Sending the invitation Failed.Show warning message: â€ Some users have already existed in the space, including: usernameâ€*/ 
		 ArrayList<String> warningTextArray=spWarnMessg.getArrayContentByType(1);
		 String warningText=warningTextArray.get(0);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username1,false,"");
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_WARNING_MESSAGE.replace("${warningText}",warningText).replace("${username}",username1),2000,1);
		button.ok();
 	}

	/**
	*<li> Case ID:122546.</li>
	*<li> Test Case Name: Send invitation from Space at hidden mode and close for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test18_SendInvitationFromSpaceAtHiddenModeAndCloseForRegistration() {
		info("Test 18 Send invitation from Space at hidden mode and close for registration");
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
			Step 1:Create the hidden space
		*Input Data: 
			- Sign in system
			- Select space page
			- Click on [Add new space] button 
			- Enter valid name 
			- Select visibility and check in hidden and close
			- Click on [create] button.
		*Expected Outcome: 
			Create a new space successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send invitation
		*Input Data: 
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select the [members] tab
			- Enter or select valid user to send invitation
			- Click on [invite]
		*Expected Outcome: 
			- Send invitation successfully:name of user are existing on the invited list.*/
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Check invitation sending successfully
		*Input Data: 
			- User has select at step 2, sign in
			- Go to the space page
		*Expected Outcome: 
			By the default: On the list of invitations space: 
			name of space which has sent invitation to user, are list on invited space. 
			If user accept this invitation, Click on [Accept] button else click on [Ignore] button*/ 
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.goToInvitationsReceivedTab();
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN .replace("${space}",space));
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
	     
 	}

	/**
	*<li> Case ID:122552.</li>
	*<li> Test Case Name: send invitation to Root.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test19_SendInvitationToRoot() {
		info("Test 19 send invitation to Root");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String fullName1=username1+" "+username1;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create a new Space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new Space] 
			- Enter all valid data
			- Click [Create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send invitation
		*Input Data: 
			- Creator selects the Space which he/she has created at step 1.
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select [Members] tab
			- Enter root or select a user Root
			- Click [invite]
		*Expected Outcome: 
			Sending the invitation successfully. Root automatic become a member of space*/
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(USER_ROOT,false,"");

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: check list user
		*Input Data: 
			- Select List user portlet
		*Expected Outcome: 
			Show 2 list of users:
			- Administrators of space: include all users are manager of space
			- Members of space: include all users are member of space. Root is a member of space*/ 
        waitForAndGetElement(spaMg.ELEMENT_SPACE_MEMBER_USER_MEMBER.replace("${fullName}",DATA_NAME_ROOT),2000,1);
        waitForAndGetElement(spaMg.ELEMENT_SPACE_MEMBER_USER_MANAGER.replace("${fullName}",fullName1),2000,1);
	}

	/**
	*<li> Case ID:122559.</li>
	*<li> Test Case Name: Send invitation from Space at hidden mode and open for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test20_SendInvitationFromSpaceAtHiddenModeAndOpenForRegistration() {
		info("Test 20 Send invitation from Space at hidden mode and open for registration");
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
			Step 1:Create the hidden space
		*Input Data: 
			- Sign in system
			- Select space page
			- Click on [Add new space] button 
			- Enter valid name 
			- Select visibility and check in hidden and open
			- Click on [create] button.
		*Expected Outcome: 
			Create a new space successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send invitation
		*Input Data: 
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select the [members] tab
			- Enter or select valid user to send invitation
			- Click on [invite]
		*Expected Outcome: 
			- Send invitation successfully:
			name of user are existing on the invited list.*/
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Check invitation sending successfully
		*Input Data: 
			- User has select at step 2, sign in
			- Go to the space page
		*Expected Outcome: 
			By the default: 
			On the list of invitations space: name of space which has sent invitation to user, 
			are list on invited space. If user accept this invitation, 
			Click on [Accept] button else click on [Ignore] button*/ 
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.goToInvitationsReceivedTab();
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN .replace("${space}",space));
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
	     
 	}

	/**
	*<li> Case ID:122565.</li>
	*<li> Test Case Name: Send invitation from Space at hidden mode and Validation for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test21_SendInvitationFromSpaceAtHiddenModeAndValidationForRegistration() {
		info("Test 21 Send invitation from Space at hidden mode and Validation for registration");
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
			Step 1:Create the hidden space
		*Input Data: 
			- Sign in system
			- Select space page
			- Click on [Add new space] button 
			- Enter valid name 
			- Select visibility and check in hidden and validation
			- Click on [create] button.
		*Expected Outcome: 
			Create a new space successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(1);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send invitation
		*Input Data: 
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select the [members] tab
			- Enter or select valid user to send invitation
			- Click on [invite]
		*Expected Outcome: 
			- Send invitation successfully:name of user are existing on the invited list.*/
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Check invitation sending successfully
		*Input Data: 
			- User has select at step 2, sign in
			- Go to the space page
		*Expected Outcome: 
			By the default: On the list of invitations space: 
			name of space which has sent invitation to user, 
			are list on invited space. 
			If user accept this invitation, 
			Click on [Accept] button else click on [Ignore] button*/ 
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.goToInvitationsReceivedTab();
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN .replace("${space}",space));
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
	     
 	}

	/**
	*<li> Case ID:122573.</li>
	*<li> Test Case Name: Send invitation from Space at Visible mode and validation for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test22_SendInvitationFromSpaceAtVisibleModeAndValidationForRegistration() {
		info("Test 22 Send invitation from Space at Visible mode and validation for registration");
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
			Step 1:Create the private space
		*Input Data: 
			- Sign in system
			- Select space page
			- Click on [Add new space] button 
			- Enter valid name 
			- Select visibility and check in visible and validation
			- Click on [create] button.
		*Expected Outcome: 
			Create a new space successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(1);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send invitation
		*Input Data: 
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select the [members] tab
			- Enter or select valid user to send invitation
			- Click on [invite]
		*Expected Outcome: 
			- Send invitation successfully:name of user are existing on the invited list.*/
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Check invitation sending successfully
		*Input Data: 
			- User has select at step 2, sign in
			- Go to the space page
		*Expected Outcome: 
			By the default: On the list of invitations space: 
			name of space which has sent invitation to user, are list on invited space. 
			If user accept this invitation, Click on [Accept] button else click on [Ignore] button*/ 
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.goToInvitationsReceivedTab();
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN .replace("${space}",space));
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
	     
 	}

	/**
	*<li> Case ID:122580.</li>
	*<li> Test Case Name: Send invitation from Space at Visible mode and close for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test23_SendInvitationFromSpaceAtVisibleModeAndCloseForRegistration() {
		info("Test 23 Send invitation from Space at Visible mode and close for registration");
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
			Step 1:Create the private space
		*Input Data: 
			- Sign in system
			- Select space page
			- Click on [Add new space] button 
			- Enter valid name 
			- Select visibility and check in visible and Close
			- Click on [create] button.
		*Expected Outcome: 
			Create a new space successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(2);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send invitation
		*Input Data: 
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select the [members] tab
			- Enter or select valid user to send invitation
			- Click on [invite]
		*Expected Outcome: 
			- Send invitation successfully:name of user are existing on the invited list.*/
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Check invitation sending successfully
		*Input Data: 
			- User has select at step 2, sign in
			- Go to the space page
		*Expected Outcome: 
			By the default: On the list of invitations space: 
			name of space which has sent invitation to user, are list on invited space. 
			If user accept this invitation, 
			Click on [Accept] button else click on [Ignore] button*/ 
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.goToInvitationsReceivedTab();
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN .replace("${space}",space));
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
	     
 	}

	/**
	*<li> Case ID:122595.</li>
	*<li> Test Case Name: Send invitation from Space at Visible mode and open for registration.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test24_SendInvitationFromSpaceAtVisibleModeAndOpenForRegistration() {
		info("Test 24 Send invitation from Space at Visible mode and open for registration");
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
			Step 1:Create the private space
		*Input Data: 
			- Sign in system
			- Select space page
			- Click on [Add new space] button 
			- Enter valid name 
			- Select visibility and check in visible and open
			- Click on [create] button.
		*Expected Outcome: 
			Create a new space successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send invitation
		*Input Data: 
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select the [members] tab
			- Enter or select valid user to send invitation
			- Click on [invite]
		*Expected Outcome: 
			- Send invitation successfully:name of user are existing on the invited list.*/
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Check invitation sending successfully
		*Input Data: 
			- User has select at step 2, sign in
			- Go to the space page
		*Expected Outcome: 
			By the default: 
			On the list of invitations space: name of space which has sent invitation to user, 
			are list on invited space. 
			If user accept this invitation, Click on [Accept] button else click on [Ignore] button*/ 
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.goToInvitationsReceivedTab();
	     waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN .replace("${space}",space));
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
	     
 	}

	/**
	*<li> Case ID:122623.</li>
	*<li> Test Case Name: Invited user denies invitation.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test25_InvitedUserDeniesInvitation() {
		info("Test 25 Invited user denies invitation");
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
			- Click on [Add new Space] 
			- Enter all valid data
			- Click [create]
		*Expected Outcome: 
			- Create new Space successfully.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Send invitation
		*Input Data: 
			- Creator selects the Space which he/she has created at step 1.
			- Click on [Edit space] icon or access the Space and select space settings portlet
			- Select [Members] tab
			- Enter valid user name or select a user from list
			- Click [invite]
		*Expected Outcome: 
			Send invitation successfully:*/
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Invited user denies invitation
		*Input Data: 
			- Invited user signs in 
			- Select space page
			- Select invitations space list
			- Select Space which has created at step 1.
			- Click on [Ignore] button
		*Expected Outcome: 
			- On the screen of invited user: The space is moved from invitations space list to Public space list.
			  [Accept] & [Ignore] button are changed by button [Request to join]
			- On screen of user invitation sender: user name of invited user is removed from list invited user.*/ 
		 magAc.signIn(username2,password);
	     hp.goToAllSpace();
	     spaMg.goToInvitationsReceivedTab();
	     spaMg.ignoreAInvitation(space);
	     waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	     waitForElementNotPresent(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN .replace("${space}",space));
	     waitForElementNotPresent(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN.replace("${space}",space));
	     hp.goToAllSpace();
	     spaMg.searchSpace(space, "");
	     waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN.replace("${space}", space));
	    
	     magAc.signIn(username1,password);
	     hp.goToSpecificSpace(space);
	     spaHome.goToSpaceSettingTab();
	     setSpaceMg.goToMemberTab();
	     waitForElementNotPresent(setSpaceMg.ELEMENT_SPACE_INVITED_USER_TABLE.replace("${user}",username2),2000,1);
	     
 	}
}