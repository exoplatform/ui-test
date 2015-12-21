package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;

	public class SOC_Space_Spaces_Activities_Display extends SOC_TestConfig{

	/**
	*<li> Case ID:122442.</li>
	*<li> Test Case Name: Not display Space activity after edit Visibility/Registration of space.</li>
	*<li> Pre-Condition: - At least a space activity is shared in the activity stream</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_NotDisplaySpaceActivityAfterEditVisibilityRegistrationOfSpace() {
		info("Test 1: Not display Space activity after edit Visibility/Registration of space");
		String regist =spRegisData.getSpaceRegistration(2);
		String[] arrayRight ={regist};
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		info("create new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		/*Step Number: 1
		*Step Name: Edit Visibility/Registration of space
		*Step Description: 
			- Connect to Intranet
			- Open a space
			- Edit the visibility and registration of the space
			- Back to the Homepage
		*Input Data: 
			
		*Expected Outcome: 
			- The Space activity isn't updated*/ 
		info("change access to close");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		info("back to homepage");
		hp.goToHomePage();
		info("last comment is still Join space");
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_SPACE_SPACE_LAST_COMMENT_JOINSPACE.replace("${space}", space));
		
	}

	/**
	*<li> Case ID:122443.</li>
	*<li> Test Case Name: Not display Space activity after removing a space member.</li>
	*<li> Pre-Condition: - A space activity is shared in the activity stream
	- Space has at least 2 users
	- User A is administrator of space</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:122446.</li>
	*<li> Test Case Name: Not display Space activity after invite other user to a space.</li>
	*<li> Pre-Condition: - A space activity is shared in the activity stream</li>
	*<li> Post-Condition: </li>
	*/
	
	@Test
	public  void test02_03_NotDisplaySpaceActivityAfterRemovingASpaceMember() {
		info("Test 02: Not display Space activity after invite other user to a space");
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
		*Step Name: Invite an user
		*Step Description: 
			- Connect to Intranet
			- Open a space
			- Go to Space Settings
			-
			-> Member tab and select user to invite 
			- Back to the Homepage
		*Input Data: 
			
		*Expected Outcome: 
			- The Space activity isn't updated*/ 
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String urlSpace =baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+space.toLowerCase();
		info("create new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,false,"");
		
		info("back to home page");
		hp.goToHomePage();
		info("last comment is still Join space");
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_SPACE_SPACE_LAST_COMMENT_JOINSPACE.replace("${space}", space));
		
		magAc.signIn(username2,password);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		
		info("Test 03: Not display Space activity after removing a space member");
		/*Step Number: 1
		*Step Name: Remove a member from space
		*Step Description: 
			- Connect to Intranet by user A
			- Open a space
			- Go to Space Settings 
			-
			-> Member
			- Click on Delete icon to remove a member of space
			- Back to the Homepage
		*Input Data: 
			
		*Expected Outcome: 
			- The number of members of the space activity is updated 
			-1*/ 
		info("remove space member");
		magAc.signIn(username1,password);
		driver.get(urlSpace);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.deleteMember(username2);
		
		info("space member is 1 now");
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_SPACE_MEMBER_NUMBER.replace("${space}",space).replace("${num}", "1"));
		
 	}

	/**
	*<li> Case ID:122634.</li>
	*<li> Test Case Name: Not display activity for space if the space is hidden.</li>
	*<li> Pre-Condition: User activities for a public space is displayed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_NotDisplayActivityForSpaceIfTheSpaceIsHidden() {
		info("Test 04: Not display activity for space if the space is hidden");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={visib};
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		/*Step Number: 1
		*Step Name: Check activity create a hidden space
		*Step Description: 
			- Connect to Intranet
			- Click Join a Space button
			- Click Add new space
			- Input name and go to Access & Edit tab
			- Select Visibility is Hidden 
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space is created
			- No activity is created*/ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		info("not display comment on activity stream");
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_SPACE_SPACE_LAST_COMMENT_JOINSPACE.replace("${space}", space));
 	
	}
}