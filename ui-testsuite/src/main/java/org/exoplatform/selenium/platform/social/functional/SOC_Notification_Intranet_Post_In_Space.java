package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


	public class SOC_Notification_Intranet_Post_In_Space extends SOC_TestConfig2{

	/**
	*<li> Case ID:125134.</li>
	*<li> Test Case Name: Check Post on my Space notification.</li>
	*<li> Pre-Condition: - User A and User B are members of Space 1
	- User B has posted in Space 1
	- The notification "An activity is posted on one of my spaces" is activated in the user settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckPostOnMySpaceNotification() {
		info("Test 1: Check Post on my Space notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A Post on my Space notifications is displayed in the list*/
		
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
		myNoti.enableNotification(myNotiType.Space_Post_intranet);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User A invites UserB to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,username2+" "+username2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B accepted to join the space");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User B added an new activity to the space");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(spaceName);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2 : Check notification message
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The notification message is :$AVATAR$USER has posted an activity in the $SPACE space.$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $SPACE is Space 1
			- $ACTIVITY is the activity title / message
			- $DATE is the date of the notification*/ 
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		String status = notiIntranetData.getContentByArrayTypeRandom(10);
		navTool.goToIntranetNotification();
		intraNot.checkAvatarInStatus(username2, true);
		intraNot.checkStatusSpace(status,spaceName);
		intraNot.checkActivityTitleInStatus(activity, true);
 	}

	/**
	*<li> Case ID:125135.</li>
	*<li> Test Case Name: Click Post on my Space notification.</li>
	*<li> Pre-Condition: - User A and User B are members of Space 1
	- User B has posted in Space 1
	- The notification "An activity is posted on one of my spaces" is activated in the user settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_ClickPostOnMySpaceNotification() {
		info("Test 2: Click Post on my Space notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A Post on my Space notifications is displayed in the list*/
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
		myNoti.enableNotification(myNotiType.Space_Post_intranet);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User A invites UserB to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,username2+" "+username2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B accepted to join the space");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User B added an new activity to the space");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(spaceName);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2 : View the notification
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			The activity is displayedin the activity viewer with all comments expanded*/ 
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		navTool.goToIntranetNotification();
		intraNot.goToDetailPostInSpace(spaceName, true);
		notiAct.checkTitleActivityExpand(activity);
   
 	}

	/**
	*<li> Case ID:125136.</li>
	*<li> Test Case Name: Check View All after receiving a Post on my Stream notification.</li>
	*<li> Pre-Condition: - User A and User B are members of Space 1
	- User B has posted in Space 1
	- The notification "An activity is posted on one of my spaces" is activated in the user settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckViewAllAfterReceivingAPostOnMyStreamNotification() {
		info("Test 3: Check View All after receiving a Post on my Stream notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A Post on my Space notifications is displayed in the list*/
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
		myNoti.enableNotification(myNotiType.Space_Post_intranet);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User A invites UserB to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,username2+" "+username2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B accepted to join the space");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User B added an new activity to the space");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(spaceName);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2 : Check in View all page
		*Step Description: 
			- Go to View All
		*Input Data: 
			
		*Expected Outcome: 
			- Post of my Space notification is displayed / available in the page*/
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		String status = notiIntranetData.getContentByArrayTypeRandom(10);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkStatus(status, activity);

 	}}