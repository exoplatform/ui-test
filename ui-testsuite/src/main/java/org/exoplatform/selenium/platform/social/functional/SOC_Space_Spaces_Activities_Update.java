package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;

	public class SOC_Space_Spaces_Activities_Update extends SOC_TestConfig3{

	
	/**
	*<li> Case ID:122437.</li>
	*<li> Test Case Name: Update Space activity after a user leave a space.</li>
	*<li> Pre-Condition: a user is member of a space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_UpdateSpaceActivityAfterAUserLeaveASpace() {
		info("Test 1:  Update Space activity after a user leave a space");
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
		*Step Name: 
		*Step Description: 
			- Connect to Intranet
			- Open Spaces page
			- Leave a space
			- Back to the Homepage
		*Input Data: 
			
		*Expected Outcome: 
			- The Space activity is updated in the activity stream: the number of members is updated to 
			-1
			- No comment is added into activity*/ 
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes,6000);
		
		info("User A invites UserB to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B accept invitation from User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User B leaves the space");
		hp.goToMySpaces();
	    spaMg.searchSpace(spaceName, "");
	    spaMg.leaveSpace(spaceName);
		
		info("User B back to Home page");
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_SPACE_MEMBER_NUMBER.replace("${space}",spaceName).replace("${num}", "1"));
		
 	}
}