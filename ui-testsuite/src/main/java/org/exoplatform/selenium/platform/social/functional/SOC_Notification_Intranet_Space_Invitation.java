package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


	public class SOC_Notification_Intranet_Space_Invitation extends SOC_TestConfig2{

	/**
	*<li> Case ID:125143.</li>
	*<li> Test Case Name: Check Space Invitation notification.</li>
	*<li> Pre-Condition: - User A is manager of the space 1
	- User A invite User B to join the space 1
	- Space Invitation notification is activated in the settings of User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckSpaceInvitationNotification() {
		info("Test 1: Check Space Invitation notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User B
			- Click Notifications icon
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A Space Invitation notifications is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_Intranet);
		
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B invites UserA to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username1,true,username1+" "+username1);
		

		/*Step number: 2
		*Step Name: Step 2 : Check notification message
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The notification message is :
			- $AVATAR
			- You're invited to join $SPACE space
			- [Accept] | [Refuse]
			- $DATEWhere : 
			- $AVATAR is the thumbnail of the space
			- $SPACE is space 1
			- $DATE is the date of the notification*/
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		String status = notiIntranetData.getContentByArrayTypeRandom(3);
		navTool.goToIntranetNotification();
		intraNot.checkAvatarInStatus(spaceName, true);
		intraNot.checkStatusSpace(status,spaceName);
		intraNot.checkBtnConnectJoinRequest(spaceName);

		/*Step number: 3
		*Step Name: Step 3 : Click Notification
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The user isnot redirected to the home of Space 1*/ 
		intraNot.goToDetailInvitationSpace(spaceName, true);
		waitForElementNotPresent(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET);

 	}

	/**
	*<li> Case ID:125144.</li>
	*<li> Test Case Name: Accept a Space Invitation from notification.</li>
	*<li> Pre-Condition: - User A is manager of the space 1
	- User A invite User B to join the space 1
	- Space Invitation notification is activated in the settings of User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_AcceptASpaceInvitationFromNotification() {
		info("Test 2: Accept a Space Invitation from notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User B
			- Click Notifications icon
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A Space Invitation notifications is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_Intranet);
		
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B invites UserA to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username1,true,username1+" "+username1);

		/*Step number: 2
		*Step Name: Step 2 : Accept Space invitation
		*Step Description: 
			- Click [Accept]
		*Input Data: 
			
		*Expected Outcome: 
			- The invitation is approved and User B is member of Space 1*/
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		String status = notiIntranetData.getContentByArrayTypeRandom(3);
		navTool.goToIntranetNotification();
		intraNot.checkStatusSpace(status,spaceName);
		
		info("User A accepted invitation");
        intraNot.acceptRqConnection(spaceName);	
        hp.goToHomePage();
        hp.goToSpecificSpace(spaceName);
        waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET);

		/*Step number: 3
		*Step Name: Step 3 : Check Notification message
		*Step Description: 
			- Click the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The notification message is updated to : $AVATARYou joined $SPACE space$DATEWhere : 
			- $AVATAR is the thumbnail of the space
			- $SPACE is space 1
			- $DATE is the date of the notification*/ 
        navTool.goToIntranetNotification();
        String statusJoin=notiIntranetData.getContentByArrayTypeRandom(9);
        intraNot.checkStatusSpace(statusJoin,spaceName);
        intraNot.checkAvatarInStatus(spaceName, true);

 	}

	/**
	*<li> Case ID:125145.</li>
	*<li> Test Case Name: Refuse a Space Invitation from notification.</li>
	*<li> Pre-Condition: - User A is manager of the space 1
	- User A invite User B to join the space 1
	- Space Invitation notification is activated in the settings of User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_RefuseASpaceInvitationFromNotification() {
		info("Test 3: Refuse a Space Invitation from notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User B
			- Click Notifications icon
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A Space Invitation notifications is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_Intranet);
		
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B invites UserA to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username1,true,username1+" "+username1);

		/*Step number: 2
		*Step Name: Step 2 : Refuse space invitation
		*Step Description: 
			- Click [Refuse]
		*Input Data: 
			
		*Expected Outcome: 
			- User B is not member of Space 1*/
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		String status = notiIntranetData.getContentByArrayTypeRandom(3);
		navTool.goToIntranetNotification();
		intraNot.checkStatusSpace(status,spaceName);
		
		info("User A refuse invitation");
        intraNot.refuseRqConnection(spaceName);

		/*Step number: 3
		*Step Name: Step 3 : Check notification message
		*Step Description: 
			- Click the notification message
		*Input Data: 
			
		*Expected Outcome: 
			- The notifications message is hidden from the list*/ 
        intraNot.checkNotStatusSpace(status, spaceName);

 	}

	/**
	*<li> Case ID:125146.</li>
	*<li> Test Case Name: Check View All after accepting a Space Invitation.</li>
	*<li> Pre-Condition: - User A is manager of the space 1
	- User A invite User B to join the space 1
	- Space Invitation notification is activated in the settings of User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckViewAllAfterAcceptingASpaceInvitation() {
		info("Test 4: Check View All after accepting a Space Invitation");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User B
			- Click Notifications icon
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A Space Invitation notifications is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_Intranet);
		
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B invites UserA to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,username2+" "+username2);

		/*Step number: 2
		*Step Name: Step 2 : Check View All page
		*Step Description: 
			- Click [Accept]
			- Go to View All
		*Input Data: 
			
		*Expected Outcome: 
			- The notifications is displayed / available in the View All page
			- The message displayed in the last one updated after accepting the request*/ 
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		String status = notiIntranetData.getContentByArrayTypeRandom(3);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkStatusSpace(status,spaceName);

 	}

	/**
	*<li> Case ID:125147.</li>
	*<li> Test Case Name: Check View All after refusing a Space Invitation.</li>
	*<li> Pre-Condition: - User A is manager of the space 1
	- User A invite User B to join the space 1
	- Space Invitation notification is activated in the settings of User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckViewAllAfterRefusingASpaceInvitation() {
		info("Test 5: Check View All after refusing a Space Invitation");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User B
			- Click Notifications icon
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A Space Invitation notifications is displayed in the list*/
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_Intranet);
		
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B invites UserA to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username1,true,username1+" "+username1);

		/*Step number: 2
		*Step Name: Step 2 : Check View all page
		*Step Description: 
			- Click [Refuse]
			- Go to View All
		*Input Data: 
			
		*Expected Outcome: 
			- The notifications is not displayed / available in the View All page*/ 
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		String status = notiIntranetData.getContentByArrayTypeRandom(3);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkStatusSpace(status,spaceName);
		
		info("User A refuse invitation");
        intraNot.refuseRqConnection(spaceName);
        intraNot.checkNotStatusSpace(status,spaceName);

 	}

	/**
	*<li> Case ID:125149.</li>
	*<li> Test Case Name: Check Space Invitation notification (from group invitation).</li>
	*<li> Pre-Condition: - User A is manager of the space 1
	- User B is part of the group "test"
	- User A invites the group "test" join the space 1
	- Space Invitation notification is activated in the settings of User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckSpaceInvitationNotificationFromGroupInvitation() {
		info("Test 6: Check Space Invitation notification (from group invitation)");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User B
			- Click Notifications icon
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A Space Invitation notifications is displayed in the list*/
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		
		
		info("John add UserA to administration group");
		String groupName=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupLabel=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String groupDesc=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.addGroup(groupName, groupLabel, groupDesc, true);
		userAndGroup.selectGroup(groupLabel);
		userAndGroup.addUsersToGroup(username1,"*",false,false);
		
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_Intranet);
		
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B invites UserA to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		String[] group = {groupLabel};
		setSpaceMg.inviteGroup(group);

		/*Step number: 2
		*Step Name: Step 2 : Check notification message
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The notification message is :
			- $AVATAR
			- You're invited to join $SPACE space
			- [Accept] | [Refuse]
			- $DATEWhere : 
			- $AVATAR is the thumbnail of the space
			- $SPACE is space 1
			- $DATE is the date of the notification*/
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		String status = notiIntranetData.getContentByArrayTypeRandom(3);
		navTool.goToIntranetNotification();
		intraNot.checkAvatarInStatus(spaceName, true);
		intraNot.checkStatusSpace(status,spaceName);
		intraNot.checkBtnConnectJoinRequest(spaceName);

		/*Step number: 3
		*Step Name: Step 3 : Click Notification
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The user isnot redirected to the home of Space 1*/ 
		intraNot.goToDetailInvitationSpace(spaceName, true);
		waitForElementNotPresent(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET);

 	}}