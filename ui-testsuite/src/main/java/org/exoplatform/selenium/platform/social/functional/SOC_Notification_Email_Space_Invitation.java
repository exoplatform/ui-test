package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.notiMode;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


	public class SOC_Notification_Email_Space_Invitation extends SOC_TestConfig3{

	/**
	*<li> Case ID:117404.</li>
	*<li> Test Case Name: Check category which Space Invitation notification belongs to.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCategoryWhichSpaceInvitationNotificationBelongsTo() {
		info("Test 1: Check category which Space Invitation notification belongs to");
		/*Step Number: 1
		*Step Name: Step 1: Access notification settings
		*Step Description: 
			- Login
			- Move mouse over the full name of user and select [Notifications] on the menu
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Settings page is appeared*/
		info("Create 1 users for testing");
		createNewUser(1);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();

		/*Step number: 2
		*Step Name: Step 2: Check category which Space Invitation notification belongs to
		*Step Description: 
			Check category which Space Invitation notification belongs to
		*Input Data: 
			
		*Expected Outcome: 
			- Space Invitation notification belongs to "Spaces" category*/ 
		String category = notiCatData.getCategoryByArrayTypeRandom(3);
		myNoti.verifyNotiBelongToCategory(category,notiMode.Space_Invitation);
 	}

	/**
	*<li> Case ID:117422.</li>
	*<li> Test Case Name: Check default settings of Space Invitation notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDefaultSettingsOfSpaceInvitationNotification() {
		info("Test 2: Check default settings of Space Invitation notification");
		/*Step Number: 1
		*Step Name: Step 1: Access notification settings
		*Step Description: 
			- Login
			- Move mouse over the full name of user and select [Notifications] on the menu
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Settings page is appeared*/
		info("Create 1 users for testing");
		createNewUser(1);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();

		/*Step number: 2
		*Step Name: Step 2: Check default settings of Space Invitation notification
		*Step Description: 
			Check default settings of Space Invitation notification
		*Input Data: 
			
		*Expected Outcome: 
			- Default settings of new user notification is:Weekly*/ 
		myNoti.verifyNotificationDefault(notificationType.Space_Invitation_email);
 	}

	/**
	*<li> Case ID:117430.</li>
	*<li> Test Case Name: Check digest mail when digest mail setting is Daily.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Create 1 space: "Space1"
	- Go to Space Settings 
	-
	-> Members 
	-
	-> Invite user2 to join space
	- Digest mail of space invitation is set: Daily</li>
	CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test03_CheckDigestMailWhenDigestMailSettingIsDaily() {
		info("Test 3: Check digest mail when digest mail setting is Daily");
		/*Step Number: 1
		*Step Name: Step 1: Change date/time of server
		*Step Description: 
			- Stop server
			- Change date/time of server to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check the digest mail
		*Step Description: 
			- Check the digest mail at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			- Digest mail is sent to user2*/ 

 	}

	/**
	*<li> Case ID:117442.</li>
	*<li> Test Case Name: Check digest message after cancel of the space invitation.</li>
	*<li> Pre-Condition: User A invite the User B to join the Space X</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test04_CheckDigestMessageAfterCancelOfTheSpaceInvitation() {
		info("Test 4: Check digest message after cancel of the space invitation");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with the User A
			- Open the Space X, cancel the invitation space of the User B
		*Input Data: 
			
		*Expected Outcome: 
			- The invitation space was cancelled*/

		/*Step number: 2
		*Step Name: Step 1: Change date/time
		*Step Description: 
			- Stop server
			- Change date/time to 23:30 of this day
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time is changed successfully*/

		/*Step number: 3
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check daily digest mail of the User B
		*Input Data: 
			
		*Expected Outcome: 
			- The digest mail is sent
			- The Space invitation activity is not diaplayed in the digest mail*/ 

 	}

	/**
	*<li> Case ID:117477.</li>
	*<li> Test Case Name: Check event label of Space Invitation in notification settings page.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckEventLabelOfSpaceInvitationInNotificationSettingsPage() {
		info("Test 5: Check event label of Space Invitation in notification settings page");
		/*Step Number: 1
		*Step Name: Step 1: Access notification settings
		*Step Description: 
			- Login
			- Move mouse over the full name of user and select [Notifications] on the menu
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Settings page is appeared*/
		info("Create 1 users for testing");
		createNewUser(1);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();

		/*Step number: 2
		*Step Name: Step 2: Check label event of Space Invitation in notification settings page
		*Step Description: 
			Check label event of Space Invitation in notification settings page
		*Input Data: 
			
		*Expected Outcome: 
			- The event label is: I receive a Space invitation*/ 
		String label = notiLabelData.getContentByArrayTypeRandom(4);
		myNoti.verifyLabelNotificationType(label);

 	}

	/**
	*<li> Case ID:117507.</li>
	*<li> Test Case Name: Check notification name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckNotificationName() {
		info("Test 6: Check notification name");
		/*Step Number: 1
		*Step Name: Step 1: Access notification administration
		*Step Description: 
			- Login as admin
			- Go to Administration 
			-
			-> Portal 
			-
			-> Email Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Administration page is appeared*/
		navTool.goToAdminNotifications();
		notiAdmin.verifyTilePage();

		/*Step number: 2
		*Step Name: Step 2: Check notification name
		*Step Description: 
			- Check notification name of Space invitaion
		*Input Data: 
			
		*Expected Outcome: 
			- Notification name is: Space Invitation*/ 
		String name =  notiLabelData.getModeByArrayTypeRandom(4);
		notiAdmin.verifyNameNotificationType(name);

 	}

	/**
	*<li> Case ID:117523.</li>
	*<li> Test Case Name: Check Space Invitation notification mail format.</li>
	*<li> Pre-Condition: $FIRSTNAME = Fqa for example (first name of user2)</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckSpaceInvitationNotificationMailFormat() {
		info("Test 7: Check Space Invitation notification mail format");
		/*Step Number: 1
		*Step Name: Step 1: Invite user to join space
		*Step Description: 
			- Login as user1
			- Create a space "Space1"
			- Go to Space Settings 
			-
			-> Member and invite user2 to join
		*Input Data: 
			
		*Expected Outcome: 
			- Invitation is sent to user 2
			- Notification mail is sent to user2*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
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
		setSpaceMg.inviteUser(arrayUser.get(0),true,arrayUser.get(0));

		/*Step number: 2
		*Step Name: Step 2: Check notification mail format
		*Step Description: 
			- Check notification mail format
		*Input Data: 
			
		*Expected Outcome: 
			- The notification mail format is: 
			New space invitationHi Fqa,[Avatar of space] 
			You've received an invitation to join the Space1 space. 
			Interested to join the space and access its documents and applications ?
			Accept | Refuse*/
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(8);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(16);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(17);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcationForSpace(emailTitle,firstName, fullName, emailContent,spaceName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117524.</li>
	*<li> Test Case Name: Check Space Invitation notification mail subject.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckSpaceInvitationNotificationMailSubject() {
		info("Test 8: Check Space Invitation notification mail subject");
		/*Step Number: 1
		*Step Name: Step 1: Invite user to join space
		*Step Description: 
			- Login as user1
			- Create a space "Space1"
			- Go to Space Settings 
			-
			-> Member and invite user2 to join
		*Input Data: 
			
		*Expected Outcome: 
			- Invitation is sent to user 2
			- Notification mail is sent to user2*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
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
		setSpaceMg.inviteUser(arrayUser.get(0),true,arrayUser.get(0));

		/*Step number: 2
		*Step Name: Step 2: Check notification mail subject
		*Step Description: 
			- Check notification mail subject
		*Input Data: 
			
		*Expected Outcome: 
			- The notification mail subject is: You're invited to join Space 1 space*/
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(8);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117525.</li>
	*<li> Test Case Name: Check Space Invitation notification when the user is invited to join a space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckSpaceInvitationNotificationWhenTheUserIsInvitedToJoinASpace() {
		info("Test 9: Check Space Invitation notification when the user is invited to join a space");
		/*Step Number: 1
		*Step Name: Step 1: Invite user to join space
		*Step Description: 
			- Login as user1
			- Create a space
			- Go to Space Settings 
			-
			-> Member and invite user2 to join
		*Input Data: 
			
		*Expected Outcome: 
			- Invitation is sent to user 2*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
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
		setSpaceMg.inviteUser(arrayUser.get(0),true,arrayUser.get(0));

		/*Step number: 2
		*Step Name: Step 2: Check notification
		*Step Description: 
			- Check notification
		*Input Data: 
			
		*Expected Outcome: 
			- There is a notification mail about new connection request is sent to user2*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(8);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117537.</li>
	*<li> Test Case Name: Check the digest message when there are <= 3 notifications.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Create <= 3 spaces: "Space1", "Space2" and "Space3" 
	- Go to Space Settings 
	-
	-> Members 
	-
	-> Invite user2 to join space
	- Digest mail of space invitation is set: Weekly</li>
	CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test10_CheckTheDigestMessageWhenThereAreLessThan3Notifications() {
		info("Test 10 Check the digest message when there are <= 3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change date/time of server
		*Step Description: 
			- Stop server
			- Change date/time of server to 11:20 of Sunday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check the digest message when there are <=3 notifications
		*Step Description: 
			- Check digest messagesent to user2 at 11:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: You have been invited to joing the following spaces: Space1, Space2, Space3*/ 

 	}

	/**
	*<li> Case ID:117540.</li>
	*<li> Test Case Name: Check the digest message when there are > 3 notifications.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Create more than 3 spaces: "Space1", "Space2" and "Space3", "Space4", "Space5" 
	- Go to Space Settings 
	-
	-> Members 
	-
	-> Invite user2 to join space
	- Digest mail of space invitation is set: Weekly</li>
	CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test11_CheckTheDigestMessageWhenThereAreMoreThan3Notifications() {
		info("Test 11 Check the digest message when there are > 3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change date/time of server
		*Step Description: 
			- Stop server
			- Change date/time of server to 11:20 of Sunday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check the digest message when there are >3 notifications
		*Step Description: 
			- Check digest messageto user2 at 11:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: You have been invited to join the following spaces: Space5, Space4, Space3 and 2 others.*/ 

 	}

	/**
	*<li> Case ID:117544.</li>
	*<li> Test Case Name: Check the digest message when there is only one notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Create a space "Space1" 
	- Go to Space Settings 
	-
	-> Members 
	-
	-> Invite user2 to join space
	- Digest mail of space invitation is set: Weekly</li>
	CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test12_CheckTheDigestMessageWhenThereIsOnlyOneNotification() {
		info("Test 12 Check the digest message when there is only one notification");
		/*Step Number: 1
		*Step Name: Step 1: Change date/time of server
		*Step Description: 
			- Stop server
			- Change date/time of server to 11:20 of Sunday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check the digest message when there is only one notification
		*Step Description: 
			- Check digest message sent to user2 at 11:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: You have been invited to join the following space: Space1*/ 

 	}

	/**
	*<li> Case ID:117550.</li>
	*<li> Test Case Name: Check [Accept] link in Space Invitation notification mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Create a space "Space 1"
	- Go to Space Settings 
	-
	-> Members 
	-
	-> Invite user2 to join space
	- Space invitation notification mail is sent</li>
	*/
	@Test
	public  void test13_CheckAcceptLinkInSpaceInvitationNotificationMail() {
		info("Test 13 Check [Accept] link in Space Invitation notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Check [Accept] link in notification mail
		*Step Description: 
			- Click [Accept] link in notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Accept link will approve the invitation
			- The user become a member of the space and is taken to the space home*/ 
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
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
		setSpaceMg.inviteUser(arrayUser.get(0),false,arrayUser.get(0));
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(8);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickAcceptBtnSpaceInvitation();
		emailNot.getAllChildWindows();
		spaHome.verifyTitleSpace(spaceName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117559.</li>
	*<li> Test Case Name: Check [Count] link in digest mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Create more than 3 spaces: "Space1", "Space2" and "Space3", "Space4", "Space5" 
	- Go to Space Settings 
	-
	-> Members 
	-
	-> Invite user2 to join space
	- Digest mail of space invitation is set: Weekly</li>
	CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test14_CheckCountLinkInDigestMail() {
		info("Test 14 Check [Count] link in digest mail");
		/*Step Number: 1
		*Step Name: Step 1: Change date/time of server
		*Step Description: 
			- Stop server
			- Change date/time of server to 11:20 of Sunday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check the digest message when there are >3 notifications
		*Step Description: 
			- Check digest message in digest mail sent to user2 at 11:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: You have been invited to join the following spaces: Space5, Space4, Space3 and 2 others.*/

		/*Step number: 3
		*Step Name: Step 3: Check [Count] link
		*Step Description: 
			- Click [2] link in the digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The user is taken to the space invitations screen.*/ 

 	}

	/**
	*<li> Case ID:117569.</li>
	*<li> Test Case Name: Check [Refuse] link in Space Invitation notification mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Create a space "Space 1"
	- Go to Space Settings 
	-
	-> Members 
	-
	-> Invite user2 to join space
	- Space invitation notification mail is sent</li>
	*/
	@Test
	public  void test15_CheckRefuseLinkInSpaceInvitationNotificationMail() {
		info("Test 15 Check [Refuse] link in Space Invitation notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Check [Refuse] link in notification mail
		*Step Description: 
			- Click [Refuse] link in notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Refuse link will deny the invitation and will take the user to the spaces list
			- A Feedback message will display: You refused to join Space1.*/ 
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
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
		setSpaceMg.inviteUser(arrayUser.get(0),true,arrayUser.get(0));
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(8);
		String message = notiMessData.getContentByArrayTypeRandom(1);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickRefuseBtnSpaceInvitation();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message, fullName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}}