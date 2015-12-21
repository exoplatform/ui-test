package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;

	public class SOC_Space_Spaces_Activities_Delete extends SOC_TestConfig3{

	
	/**
	*<li> Case ID:122449.</li>
	*<li> Test Case Name: Delete a Space activity from activity stream by its user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_DeleteASpaceActivityFromActivityStreamByItsUser() {
		info("Test 01: Delete a Space activity from activity stream by its user");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String fullName1=username1+" "+username1;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Add space
		*Step Description: 
			- Connect to Intranet
			- Add a Space
		*Input Data: 
			
		*Expected Outcome: 
			- The Space activity is displayed in the activity stream*/

		/*Step number: 2
		*Step Name: Step 2: Display activity
		*Step Description: 
			- Move the mouse over the Space activity
		*Input Data: 
			
		*Expected Outcome: 
			A (X) icon is displayed in the top right panel of the activity*/
		info("create new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_USERJOIN_SPACE.replace("${user}",fullName1));
		mouseOver(hpAct.ELEMENT_ACTIVITY_SPACE_HEADING.replace("${space}", space),false);
		
		/*Step number: 3
		*Step Name: Step 3: Delete activity
		*Step Description: 
			- Click on the (X) icon
			- Click on OK button of confirmation message
		*Input Data: 
			
		*Expected Outcome: 
			The Space activity is removed from the activity stream*/ 
		info("delete space activity");
		Utils.pause(2000);
		click(hpAct.ELEMENT_ACTIVITY_SPACE_ACTIVITY_DELETE_BTN.replace("${space}", space));
		click(button.ELEMENT_OK_BUTTON);
		waitForElementNotPresent(hpAct.ELEMENT_ACTIVITY_USERJOIN_SPACE.replace("${user}",fullName1),2000,1);
	}

	/**
	*<li> Case ID:122451.</li>
	*<li> Test Case Name: Delete a User activity for space from activity stream by its user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_DeleteAUserActivityForSpaceFromActivityStreamByItsUser() {
		info("Test 8: Delete a User activity for space from activity stream by its user");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Add activity
		*Step Description: 
			- Connect to Intranet
			- Add a User activity for space
		*Input Data: 
			
		*Expected Outcome: 
			- The User activity for spaceis displayed in the activity stream*/
		info("create new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		info("add activity");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		/*Step number: 2
		*Step Name: Step 2: Show Remove icon
		*Step Description: 
			- Move the mouse over the User activity for space
		*Input Data: 
			
		*Expected Outcome: 
			A (X) icon is displayed in the top right panel of the activity*/
		mouseOver((hpAct.ELEMENT_ACTIVITY_SPACE_AUTHOR).replace("${title}",activity), true);
		
		/*Step number: 3
		*Step Name: Step 3: Click Remove icon
		*Step Description: 
			- Click on the (X) icon
			- Click OK on confirmation form
		*Input Data: 
			
		*Expected Outcome: 
			The User activity for spaceis removed from the activity stream*/ 
		info("remove activity");
		hpAct.deleteActivity(activity);
	}

	/**
	*<li> Case ID:122452.</li>
	*<li> Test Case Name: Delete comment by manager.</li>
	*<li> Pre-Condition: - Space is created
	- Space has member: User1 
	- User1 comment on any activity</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_DeleteCommentByManager() {
		info("Test 9: Delete comment by manager");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step1: Add new activities
		*Step Description: 
			- Connect to intranet
			- Access space
			- Add new activity
		*Input Data: 
			
		*Expected Outcome: 
			- Activity is added*/ 
		info("create new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		info("Add new an activity");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		/*Step Number: 2
		*Step Name: Step 2: Add comment
		*Step Description: 
			- Click Comment icon and add comment for activity above
		*Input Data: 
			
		*Expected Outcome: 
			- Comment is added*/ 
		info("Add an comment on space activity");
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addComment(activity,comment);
		
		/*Step Number: 3
		*Step Name: Step 3: Delete comment by owner
		*Step Description: 
			- Move mouse over the comment
			- Click (X) icon to remove
			- Click OK on confirmation form
		*Input Data: 
			
		*Expected Outcome: 
			- Comment is removed*/ 
		
		info("Delete comment by owner");
		hpAct.deleteComment(activity, comment);
		waitForElementNotPresent(hpAct.ELEMENT_PUBLICATION_COMMENTPOSTED.replace("${content}",comment),2000,1);
 	
	}

	/**
	*<li> Case ID:122453.</li>
	*<li> Test Case Name: Delete activity by other space member.</li>
	*<li> Pre-Condition: - Space is created
	- Space has some members: User1 and User2 (who are not manager of space)
	- User1 add activity on the space</li>
	*<li> Post-Condition: </li>
	*/
	
	@Test
	public  void test04_DeleteActivityByOtherSpaceMember() {
		info("Test 04 Delete activity by other space member");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		String fullName3=username3+" "+username3;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		
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
		
		info("User A invites UserC to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username3,true,fullName3);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B accept invitation from User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User C login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User C accept invitation from User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User C add activity");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		/*Step Number: 1
		*Step Name: Step 1: Remove space activity of other space member
		*Step Description: 
			- Login as User2
			- Go to Space Activity stream
			- Move mouse over activity of User1
		*Input Data: 
			
		*Expected Outcome: 
			- (X) icon is not displayed, so he cannot remove space activity of other member*/ 
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("Remove space activity of other space member");
		hp.goToSpecificSpace(spaceName);
		
		info("user B doesnt see X icon to remove user C's activity");
		mouseOver((hpAct.ELEMENT_ACTIVITY_SPACE_AUTHOR).replace("${title}",activity), true);
		waitForElementNotPresent((hpAct.ELEMENT_ACTIVITY_USER_ACTIVITY_DELETE_BTN).replace("${title}",activity),2000,1);
		
	}
	
	/**
	*<li> Case ID:122478.</li>
	*<li> Test Case Name: Delete comment by owner.</li>
	*<li> Pre-Condition: Space is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_DeleteCommentByOwner() {
		info("Test 05: Delete comment by owner");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step1: Add new activities
		*Step Description: 
			- Connect to intranet
			- Access space
			- Add new activity
		*Input Data: 
			
		*Expected Outcome: 
			- Activity is added*/
		
		info("create new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		info("Add a new activity");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		/*Step Number: 2
		*Step Name: Step 2: Add comment
		*Step Description: 
			- Click Comment icon and add comment for activity above
		*Input Data: 
			
		*Expected Outcome: 
			- Comment is added*/
		
		info("Add comment on space activity");
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addComment(activity,comment);
		
		/*Step number: 3
		*Step Name: Step 3: Delete comment by owner
		*Step Description: 
			- Move mouse over the comment
			- Click (X) icon to remove
			- Click OK on confirmation form
		*Input Data: 
			
		*Expected Outcome: 
			Comment is removed*/ 
		info("Remove comment");
		hpAct.deleteComment(activity,comment);
	}

	/**
	*<li> Case ID:122480.</li>
	*<li> Test Case Name: Delete comment by other member.</li>
	*<li> Pre-Condition: - Space is created
	- Space has some members: User1 and User2 (who are not space manager)
	- User1 comment on an activity which is not added by User2</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_DeleteCommentByOtherMember() {
		info("Test 15 Delete comment by other member");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		String fullName3=username3+" "+username3;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		
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
		
		info("User A invites UserC to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username3,true,fullName3);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B accept invitation from User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User C login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User C accept invitation from User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User C add activity");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("Add comment on space activity");
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addComment(activity,comment);
		
		/*Step Number: 1
		*Step Name: Step 1: Remove comment of other member
		*Step Description: 
			- Login as User2
			- Go to Space activity
			- Move mouse over the comment added by User1
		*Input Data: 
			
		*Expected Outcome: 
			- User2 cannot see (X) icon, so he cannot delete comment of other member*/ 
		info("User C login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User C cannot remove comment of user B");
		hp.goToSpecificSpace(spaceName);
		mouseOver(hpAct.ELEMENT_PUBLICATION_LASTCOMMENT.replace("${title}",activity), true);
		waitForElementNotPresent(hpAct.ELEMENT_PUBLICATION_DELETE_LASTCOMMENT.replace("${title}",comment),2000,1);
 	}

	
	/**
	*<li> Case ID:122499.</li>
	*<li> Test Case Name: Delete activities by manager of space.</li>
	*<li> Pre-Condition: - Space is created
	- Space has some members
	- Some mebers have some activities on space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_DeleteActivitiesByManagerOfSpace() {
		info("Test 07: Delete activities by manager of space");
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
		
		
		info("User B add activity");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		/*Step Number: 1
		*Step Name: Step 1: Remove space activity of other space member by Manager
		*Step Description: 
			- Login as manager of space
			- Go to Space Activity stream
			- Move mouse over activity of other space member
			- Click on (X) icon
			- Click OK on confirmation form
		*Input Data: 
			
		*Expected Outcome: 
			- Activity of space member is removed by manager*/ 
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("User A remove activity of user B");
		hp.goToSpecificSpace(spaceName);
		hpAct.deleteActivity(activity);
 	}
}