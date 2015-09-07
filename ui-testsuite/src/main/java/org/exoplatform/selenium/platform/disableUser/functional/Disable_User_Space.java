package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class Disable_User_Space extends Disable_User_TestConfig{
		
	/**
	*<li> Case ID:128006.</li>
	*<li> Test Case Name: Check disabled user in invited member list of Space.</li>
	*<li> Pre-Condition: User A is disabledUser A is a member in the Space A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckDisabledUserInInvitedMemberListOfSpace() {
		info("Test 1: Check disabled user in invited member list of Space");
		createNewUser();
		createSpace();
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username,true,username+" "+lastName);
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Open Member list
		*Step Description: 
			- Connect to Intranet with User B
			- Open the Space (A)
			- Go toSpace > Space Settings > Members
		*Input Data: 
			
		*Expected Outcome: 
			- User A is displayed*/ 
		hp.goToSpecificSpace(space);
		setSpaceMg.checkUserInInvitedSpace(username+" "+lastName, space, true);
		deleteUser();
 	}

	/**
	*<li> Case ID:128007.</li>
	*<li> Test Case Name: Check disabled user in members list of Space.</li>
	*<li> Pre-Condition: User A is disabledUser is a member in the Space A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDisabledUserInMembersListOfSpace() {
		info("Test 2: Check disabled user in members list of Space");
		createNewUser();
		createSpace();
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username,true,username+" "+lastName);
		magAc.signOut();
	    magAc.signIn(username,password);
	    hp.goToAllSpace();
	    spaMg.goToInvitationsReceivedTab();
	    spaMg.acceptAInvitation(space);
	    
	    magAc.signOut();
	    magAc.signIn(DATA_USER1,DATA_PASS);
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Open Member list of space
		*Step Description: 
			- Connect to Intranet with User B
			- Open the Space A
			- Go to Space > Members
		*Input Data: 
			
		*Expected Outcome: 
			- User A is NOT displayed*/ 
		hp.goToSpecificSpace(space);
		spaMg.verifyMember(username+" "+lastName, false);
		deleteUser();
 	}

	/**
	*<li> Case ID:128005.</li>
	*<li> Test Case Name: Check disabled user in select users list of Space.</li>
	*<li> Pre-Condition: User A is disabledUser A is not a member of the Space A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckDisabledUserInSelectUsersListOfSpace() {
		info("Test 3: Check disabled user in select users list of Space");
		createNewUser();
		disableUser();
		createSpace();
		/*Step Number: 1
		*Step Name: Step 1: Select user to invite to space
		*Step Description: 
			- Connect to Intranet with User B
			- Open the Space A
			- Go toSpace 
			-
			-> Space Settings 
			-
			-> Members 
			-
			-> Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		setSpaceMg.checkUserSelectorInviteSpace(username+" "+lastName,false);
		deleteUser();
 	}

	/**
	*<li> Case ID:128008.</li>
	*<li> Test Case Name: Search a disabled user in members list of Space.</li>
	*<li> Pre-Condition: User A is disabledUser A is NOT a member in the Space A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_SearchADisabledUserInMembersListOfSpace() {
		info("Test 4: Search a disabled user in members list of Space");
		createNewUser();
		disableUser();
		createSpace();
		/*Step Number: 1
		*Step Name: Step 1: Search User A
		*Step Description: 
			- Connect to Intranet with User B
			- Open the Space A
			- Go toSpace Settings 
			-
			-> Members
			- Enter User A into the text box
		*Input Data: 
			
		*Expected Outcome: 
			- User A is NOT displayed in the search result list*/ 
		setSpaceMg.checkSearchUserInviteSpace(username,false);
		deleteUser();
 	}}