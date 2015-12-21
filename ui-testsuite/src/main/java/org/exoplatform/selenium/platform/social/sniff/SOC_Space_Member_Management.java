package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class SOC_Space_Member_Management extends SOC_TestConfig_1 {
	
	/**
	 *<li> Case ID:121890.</li>
	 *<li> Test Case Name: Change member's role in Space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_ChangeMemberRoleInSpace() {
		info("Test 01: Change member's role in Space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Change member's role
		 *Step Description: 
			- Invite a user become member of space
			- User accept invitation
			- Login by manager of requested space: Access space -> space setting -> member tab
			- Select user and click on Remove Leader or Make Leader icon
		 *Input Data: 

		 *Expected Outcome: 
			-User become member of Space
			- After make user is leader, user can access space setting of space.
        */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,false,"");
		
		magAc.signIn(username2, password);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		
		magAc.signIn(username1, password);
		spaHome.goToSpace(space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.changeRole(username2+" "+username2);
		
		magAc.signIn(username2, password);
		spaHome.goToSpace(space);
		spaHome.goToSpaceSettingTab();
		
	}

	/**
	 *<li> Case ID:121895.</li>
	 *<li> Test Case Name: Invite/Accept user to Space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_InviteAcceptUserToSpace() {
		info("Test 02: Invite/Accept user to Space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Invite/Accept user
		 *Step Description: 
			- Access to a space from my space list
			- Click on Space Settings icon, select Member tab
			- Click on User icon, select some users from list and click on Invite icon
			- Login by invited users:
			+ Go to My space -> Invitations Received tab and click on Accept 
			+ Another user click on Ignore button

		 *Input Data: 
	
		 *Expected Outcome: 
			- Space settings is displayed, focus on Member tab
			- Name of invited user is displayed on invited list.
			- After invited user accept invitation, 
			invited  space will move from invitation space list to my space list of user
			-  Accepted user is displayed on Member List
			- After user ignored, the invitation is removed from Received Invitations list 
			and this user does not displayed on Member list of space
	    */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info(" After invited user accept invitation, invited  space will move from invitation space list to my space list of user");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,false,"");
		setSpaceMg.inviteUser(username3,false,"");
		
		magAc.signIn(username2, password);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		
		
		magAc.signIn(username3, password);
		hp.goToMySpaces();
		spaMg.ignoreAInvitation(space);
		
		magAc.signIn(username1, password);
		spaHome.goToSpace(space);
		spaHome.goToSpaceSettingTab();
		info("Accepted user is displayed on Member List");
		click(setSpaceMg.ELEMENT_SPACE_SETTINGS_MEMBERS_TAB);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}",username2+" "+username2),2000,1);
		waitForElementNotPresent(setSpaceMg.ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}",username3+" "+username3));
		
		/*info("Delete a Space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}

	/**
	 *<li> Case ID:121896.</li>
	 *<li> Test Case Name: Leave Space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_LeaveSpace() {
		info("Test 03: Leave Space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Leave Space
		 *Step Description: 
			- Go to My space page
			- Select a space and Click on Leave button on space

		 *Input Data: 
	
		 *Expected Outcome: 
			- Display list of space which user Is member
			- Space is disappeared from list and moved to All spaces list, user is not member of space.
	    */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,false,"");
		
		magAc.signIn(username2, password);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		hp.goToMySpaces();
		spaMg.leaveSpace(space);
		
		magAc.signIn(username1, password);
		spaHome.goToSpace(space);
		spaHome.goToSpaceSettingTab();
		info(" Space is disappeared from list and moved to All spaces list, user is not member of space");
		click(setSpaceMg.ELEMENT_SPACE_SETTINGS_MEMBERS_TAB);
		waitForElementNotPresent(setSpaceMg.ELEMENT_SPACE_MEMBERS_USER_TABLE.replace("${user}",username2+" "+username2),2000,1);
		
	}

	/**
	 *<li> Case ID:121902.</li>
	 *<li> Test Case Name: Remove user from space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_RemoveUSerFromSpace() {
		info("Test 04: Remove user from space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Remove user
		 *Step Description: 
			- Invite a user become member of space
			- User accept invitation
			- Managers of space go to Space settings -> member
			- Select user and click on Remove icon
		 *Input Data: 
	
		 *Expected Outcome: 
			- User become member of Space
			- User is remove out space. User is not display on Member list.
	    */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,false,"");
		
		magAc.signIn(username2, password);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		
		magAc.signIn(username1, password);
		spaHome.goToSpace(space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.removeUser(username2);
		
		/*info("Delete a Space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space,false);*/
	}

	/**
	 *<li> Case ID:121903.</li>
	 *<li> Test Case Name: Request/Accept user to Space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_RequestAcceptUserToSpace() {
		info("Test 05:Request/Accept user to Space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Request/Accept user
		 *Step Description: 
			- Go to My space page and select All Spaces tab
			- Click on Request to Join button corresponding of a specific Space
			- Click on Requests Pending
			- Login by manager of requested space: Access space -> space setting -> member tab
			- Accept/deny request by clicking on Validate or Decline icon
			- Verify requested user is displayed Member List
		 *Input Data: 
	
		 *Expected Outcome: 
			- Public space list is accessed
			- After send request, the button Request to Join is changed to Cancel
			- Display the space user has just requested to join in Requests Pending list
			- After manager accept the request, sent user is member of space
			- Accepted user is displayed Member List
	    */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		
		magAc.signIn(username2, password);
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(space);
		spaMg.goToRequestPendingTab();
		waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING.replace("${space}", space),3000,1);
		
		magAc.signIn(username1, password);
		spaHome.goToSpace(space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.acceptRequest(username2);
	}
	
}