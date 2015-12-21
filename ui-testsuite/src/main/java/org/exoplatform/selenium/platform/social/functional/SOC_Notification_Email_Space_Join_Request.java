package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.notiMode;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


	public class SOC_Notification_Email_Space_Join_Request extends SOC_TestConfig3{

	/**
	*<li> Case ID:117405.</li>
	*<li> Test Case Name: Check category which Space join reuquest notification belongs to.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCategoryWhichSpaceJoinReuquestNotificationBelongsTo() {
		info("Test 1: Check category which Space join reuquest notification belongs to");
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
		*Step Name: Step 2: Check category which Space join request notification belongs to
		*Step Description: 
			Check category which Space join request notification belongs to
		*Input Data: 
			
		*Expected Outcome: 
			- Space join request notification belongs to "Spaces" category*/ 
		String category = notiCatData.getCategoryByArrayTypeRandom(3);
		myNoti.verifyNotiBelongToCategory(category,notiMode.Space_Join);

 	}

	/**
	*<li> Case ID:117423.</li>
	*<li> Test Case Name: Check default settings of Space join request notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDefaultSettingsOfSpaceJoinRequestNotification() {
		info("Test 2: Check default settings of Space join request notification");
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
		*Step Name: Step 2: Check default settings of Space join request notification
		*Step Description: 
			Check default settings of Space join request notification
		*Input Data: 
			
		*Expected Outcome: 
			- Default settings of Space join request notification are: Weekly*/
		myNoti.verifyNotificationDefault(notificationType.Space_Join_email);

 	}

	/**
	*<li> Case ID:117431.</li>
	*<li> Test Case Name: Check digest mail when digest mail setting is Daily.</li>
	*<li> Pre-Condition: - Login as user 1
	- Create a space "Space1"
	- Login as user2
	- Click [Join a space]
	- Send request to join Space1
	- Digest mail is set: Daily</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test03_CheckDigestMailWhenDigestMailSettingIsDaily() {
		info("Test 3: Check digest mail when digest mail setting is Daily");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			- Digest mail is sent to user1*/ 

 	}

	/**
	*<li> Case ID:117441.</li>
	*<li> Test Case Name: Check digest message after cancel of a space join request.</li>
	*<li> Pre-Condition: User B send a request to join Space X to the User A</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test04_CheckDigestMessageAfterCancelOfASpaceJoinRequest() {
		info("Test 4: Check digest message after cancel of a space join request");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with the User B
			- Go to Spaces page, Cancel the join request of the Space X
		*Input Data: 
			
		*Expected Outcome: 
			- The space join request is cancelled*/

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
			- Check daily digest mail of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- The digest mail is sent
			- The Space Join Request of the Space X is not displayed in the digest message list*/ 

 	}

	/**
	*<li> Case ID:117454.</li>
	*<li> Test Case Name: Check digest message when there are <= 3 notifications.</li>
	*<li> Pre-Condition: - Login as user 1
	- Create a space: "Space1"
	- Login as userA, userB, userC
	- Click [Join a space]
	- Send request to join Space1
	- Digest mail is set: Weekly</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test05_CheckDigestMessageWhenThereAreLessThan3Notifications() {
		info("Test 5: Check digest message when there are <= 3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change date/time to 23:20 of Friday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message in digest mail when there are <=3 notifications
		*Step Description: 
			- Check digest message in digest mail when there are <=3 notifications
		*Input Data: 
			
		*Expected Outcome: 
			The digest message is: UserA, UserB, UserC have asked to join the Space1 space.*/ 

 	}

	/**
	*<li> Case ID:117459.</li>
	*<li> Test Case Name: Check digest message when there are > 3 notifications.</li>
	*<li> Pre-Condition: - Login as user 1
	- Create a space: "Space1"
	- Login as userA, userB, userC, userD, userE
	- Click [Join a space]
	- Send request to join Space1
	- Digest mail is set: Weekly</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test06_CheckDigestMessageWhenThereAreMoreThan3Notifications() {
		info("Test 6: Check digest message when there are > 3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change date/time to 23:20 of Friday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message in digest mail when there are >3 notifications
		*Step Description: 
			- Check digest message in digest mail when there are >3 notifications
		*Input Data: 
			
		*Expected Outcome: 
			The digest message is: UserE, UserD, UserC and 2 others have asked to join the Space1 space.*/ 

 	}

	/**
	*<li> Case ID:117466.</li>
	*<li> Test Case Name: Check digest message when there is only one notification.</li>
	*<li> Pre-Condition: $FIRSTNAME = John for ex (first name of user1) $USER = Mary Williams for ex (full name of user2)
	- Login as user 1
	- Create a space "Space1"
	- Login as user2
	- Click [Join a space]
	- Send request to join Space1
	- Digest mail is set: Weekly</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test07_CheckDigestMessageWhenThereIsOnlyOneNotification() {
		info("Test 7: Check digest message when there is only one notification");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change date/time to 23:20 of Friday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message in digest mail when there is only one notification
		*Step Description: 
			- Check digest message in digest mail when there is only one notification
		*Input Data: 
			
		*Expected Outcome: 
			The digest message is: Mary Williams has asked to join the Space1 space.*/ 

 	}

	/**
	*<li> Case ID:117508.</li>
	*<li> Test Case Name: Check notification name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckNotificationName() {
		info("Test 8: Check notification name");
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
			- Check notification name of Space request
		*Input Data: 
			
		*Expected Outcome: 
			- Notification name is: Space Join Request*/ 
		String name =  notiLabelData.getModeByArrayTypeRandom(3);
		notiAdmin.verifyNameNotificationType(name);

 	}

	/**
	*<li> Case ID:117526.</li>
	*<li> Test Case Name: Check Space join request notification mail content.</li>
	*<li> Pre-Condition: $FIRSTNAME = John for ex (first name of user1) $USER = Mary Williams for ex (full name of user2)</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckSpaceJoinRequestNotificationMailContent() {
		info("Test 9: Check Space join request notification mail content");
		/*Step Number: 1
		*Step Name: Step 1: Create a space
		*Step Description: 
			- Login as user 1
			- Create a space "Space1"
		*Input Data: 
			
		*Expected Outcome: 
			- Space is created successfully*/

		/*Step number: 2
		*Step Name: Step 2: Send request to join space by other user
		*Step Description: 
			- Login as user2
			- Click [Join a space]
			- Send request to join Space1
		*Input Data: 
			
		*Expected Outcome: 
			- Request to join is sent
			- Notification email is sent to user1*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Join_Req_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);

		/*Step number: 3
		*Step Name: Step 3: Check notification mail content
		*Step Description: 
			- Check notification mail content
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail content is: 
			New request to join a spaceHi John,
			[Avatar of user who send request]Mary Williams has requested access to the Space1 space 
			where you are a manager. Would you like to authorize Mary Williams to join ?
			Validate | Refuse*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(14);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(15);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcationForSpace(emailTitle,firstName,arrayUser.get(1), emailContent,spaceName,true);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117527.</li>
	*<li> Test Case Name: Check Space join request notification mail subject.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckSpaceJoinRequestNotificationMailSubject() {
		info("Test 10 Check Space join request notification mail subject");
		/*Step Number: 1
		*Step Name: Step 1: Create a space
		*Step Description: 
			- Login as user 1
			- Create a space "Space1"
		*Input Data: 
			
		*Expected Outcome: 
			- Space is created successfully*/

		/*Step number: 2
		*Step Name: Step 2: Send request to join space by other user
		*Step Description: 
			- Login as user2
			- Click [Join a space]
			- Send request to join Space1
		*Input Data: 
			
		*Expected Outcome: 
			- Request to join is sent
			- Notification email is sent to user1*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Join_Req_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);

		/*Step number: 3
		*Step Name: Step 3: Check notification mail subject
		*Step Description: 
			- Check notification mail subject
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail subject is: $USER has requested access to $SPACE space.*/
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117535.</li>
	*<li> Test Case Name: Check the digest message is appended for each space that received a request to join.</li>
	*<li> Pre-Condition: - Login as user 1
	- Create a space: "Space1", "Space2" and "Space 3"
	- UserA, UserB, UserC, UserD, UserE send request to join Space1
	- User A, UserB send request to join Space2
	- UserA send request to join Space3
	- Digest mail is set: Weekly</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test11_CheckTheDigestMessageIsAppendedForEachSpaceThatReceivedARequestToJoin() {
		info("Test 11 Check the digest message is appended for each space that received a request to join");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change date/time to 23:20 of Friday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check the digest message is appended for each space that received a request to join
		*Step Description: 
			- Check the digest message is appended for each space that received a request to join
		*Input Data: 
			
		*Expected Outcome: 
			The digest message is:
			- UserE, UserD, UserC and 2 others have asked to join the Space1 space
			- UserA, UserB have asked to join the Space2 space
			- UserA has asked to join the Space3 space*/ 

 	}

	/**
	*<li> Case ID:117545.</li>
	*<li> Test Case Name: Check the event label of Space join request notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckTheEventLabelOfSpaceJoinRequestNotification() {
		info("Test 12 Check the event label of Space join request notification");
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
		*Step Name: Step 2: Check the event label of Space join request notification
		*Step Description: 
			Check the event label of Space join request notification
		*Input Data: 
			
		*Expected Outcome: 
			- The event label of Space join request notification is: Someone requests to join one of my spaces*/ 
		String label = notiLabelData.getContentByArrayTypeRandom(3);
		myNoti.verifyLabelNotificationType(label);
 	}

	/**
	*<li> Case ID:117547.</li>
	*<li> Test Case Name: Check the Space join request notification when some join a space where the user is manager.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckTheSpaceJoinRequestNotificationWhenSomeJoinASpaceWhereTheUserIsManager() {
		info("Test 13 Check the Space join request notification when some join a space where the user is manager");
		/*Step Number: 1
		*Step Name: Step 1: Create a space
		*Step Description: 
			- Login as user 1
			- Create a space "Space1"
		*Input Data: 
			
		*Expected Outcome: 
			- Space is created successfully*/

		/*Step number: 2
		*Step Name: Step 2: Send request to join space by other user
		*Step Description: 
			- Login as user2
			- Click [Join a space]
			- Send request to join Space1
		*Input Data: 
			
		*Expected Outcome: 
			- Request to join is sent
			- Notification email is sent to user1*/ 
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Join_Req_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117558.</li>
	*<li> Test Case Name: Check [Count] and [Space] link in digest mail.</li>
	*<li> Pre-Condition: There are > 3 notifications: the digest message is $LAST3_USERS and $COUNT others have asked to join the $SPACE space.</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test14_CheckCountAndSpaceLinkInDigestMail() {
		info("Test 14 Check [Count] and [Space] link in digest mail");
		/*Step Number: 1
		*Step Name: Step 1: Check [Count] link in notification mail
		*Step Description: 
			- Click [2] link in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The user is taken directly settings/member of the space.*/

		/*Step number: 2
		*Step Name: Step 2: Check [Space] link in notification mail
		*Step Description: 
			- Click [Space1] link
		*Input Data: 
			
		*Expected Outcome: 
			- The user is taken directly settings/member of the space.*/ 

 	}

	/**
	*<li> Case ID:117566.</li>
	*<li> Test Case Name: Check [Refuse] link in notification mail.</li>
	*<li> Pre-Condition: $FIRSTNAME = John for ex (first name of user1) $USER = Mary Williams for ex (full name of user2)
	- Login as user 1
	- Create a space "Space1"
	- Login as user2
	- Click [Join a space]
	- Send request to join Space1
	- Notification mail is sent to user1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_CheckRefuseLinkInNotificationMail() {
		info("Test 15 Check [Refuse] link in notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Check [Refuse] link in notification mail
		*Step Description: 
			- Click [Refuse] link
		*Input Data: 
			
		*Expected Outcome: 
			- Refuse link will refuse the registration request and it will also take the user to the portal and open the space on its setting screen on "Members" tab.
			- A feedback message is displayed: You have refused Mary Williams to join the space.*/ 
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Join_Req_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		String message = notiMessData.getContentByArrayTypeRandom(2);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickRefuseBtnSpaceJoinRequest();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message, fullName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
		
 	}

	/**
	*<li> Case ID:117567.</li>
	*<li> Test Case Name: Check [Refuse] link in notification mail after refused once.</li>
	*<li> Pre-Condition: $FIRSTNAME = John for ex (first name of user1) $USER = Mary Williams for ex (full name of user2)
	- Login as user 1
	- Create a space "Space1"
	- Login as user2
	- Click [Join a space]
	- Send request to join Space1
	- Notification mail is sent to user1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_CheckRefuseLinkInNotificationMailAfterRefusedOnce() {
		info("Test 16 Check [Refuse] link in notification mail after refused once");
		/*Step Number: 1
		*Step Name: Step 1: Check [Validate] link in notification mail
		*Step Description: 
			- Click [Refuse] link on email
		*Input Data: 
			
		*Expected Outcome: 
			- Refuse link will refuse the registration request and it will also take the user to the portal and open the space on its setting screen on "Members" tab.
			- A feedback message is displayed: You have refused Mary Williams to join the space.*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Join_Req_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		String message = notiMessData.getContentByArrayTypeRandom(2);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickRefuseBtnSpaceJoinRequest();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message, fullName);
		emailNot.closeChildBrowsers(parentWindow);
		switchToParentWindow();
		

		/*Step number: 2
		*Step Name: Step2: Validate when user is already a member of space
		*Step Description: 
			- Click [Refuse] link on email again
		*Input Data: 
			
		*Expected Outcome: 
			- It will also take the user to the portal on another tab (window) of 
			browserand open the space on its setting screen on "Members" tab 
			- A feedback message is displayed: You have refused Mary Williams to join the space.*/ 
		openMail();
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickRefuseBtnSpaceJoinRequest();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message, fullName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117568.</li>
	*<li> Test Case Name: Check [Refuse] link in notification mail when user is already a member of space.</li>
	*<li> Pre-Condition: $FIRSTNAME = John for ex (first name of user1) $USER = Mary Williams for ex (full name of user2)
	- Login as user 1
	- Create a space "Space1"
	- Login as user2
	- Click [Join a space]
	- Send request to join Space1
	- Notification mail is sent to user1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test17_CheckRefuseLinkInNotificationMailWhenUserIsAlreadyAMemberOfSpace() {
		info("Test 17 Check [Refuse] link in notification mail when user is already a member of space");
		/*Step Number: 1
		*Step Name: Step 1: Check [Validate] link in notification mail
		*Step Description: 
			- Click [Validate] link on email
		*Input Data: 
			
		*Expected Outcome: 
			Validate link willaccept the registration request and it will also take the user to the portal and open the space on its setting screen on "Members" tab
			- A feedback message is displayed: You have approved Mary Williams 's request to join the space.*/

		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Join_Req_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		String message = notiMessData.getContentByArrayTypeRandom(3);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickAcceptBtnSpaceJoinReqest();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message, fullName);
		emailNot.closeChildBrowsers(parentWindow);
		switchToParentWindow();
		
		/*Step number: 2
		*Step Name: Step2: Validate when user is already a member of space
		*Step Description: 
			- Click [Refuse] link on email again
		*Input Data: 
			
		*Expected Outcome: 
			- It will take the user to the portal and open the space on its setting screen on "Members" tab
			- The feedback message is displayed : Mary Williams is already a member of $SPACE.*/ 
		String message1 = notiMessData.getContentByArrayTypeRandom(4);
		openMail();
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickRefuseBtnSpaceJoinRequest();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message1, fullName,spaceName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117578.</li>
	*<li> Test Case Name: Check [Validate] link in notification mail.</li>
	*<li> Pre-Condition: $FIRSTNAME = John for ex (first name of user1) $USER = Mary Williams for ex (full name of user2)
	- Login as user 1
	- Create a space "Space1"
	- Login as user2
	- Click [Join a space]
	- Send request to join Space1
	- Notification mail is sent to user1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test18_CheckValidateLinkInNotificationMail() {
		info("Test 18 Check [Validate] link in notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Check [Validate] link in notification mail
		*Step Description: 
			- Click [Validate] link
		*Input Data: 
			
		*Expected Outcome: 
			Validate link willaccept the registration request and it will also take the user to the portal and open the space on its setting screen on "Members" tab
			- A feedback message is displayed: You have approved Mary Williams 's request to join the space.*/ 
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Join_Req_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		String message = notiMessData.getContentByArrayTypeRandom(3);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickAcceptBtnSpaceJoinReqest();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message, fullName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117579.</li>
	*<li> Test Case Name: Check [Validate] link in notification mail after refused once.</li>
	*<li> Pre-Condition: $FIRSTNAME = John for ex (first name of user1) $USER = Mary Williams for ex (full name of user2)
	- Login as user 1
	- Create a space "Space1"
	- Login as user2
	- Click [Join a space]
	- Send request to join Space1
	- Notification mail is sent to user1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test19_CheckValidateLinkInNotificationMailAfterRefusedOnce() {
		info("Test 19 Check [Validate] link in notification mail after refused once");
		/*Step Number: 1
		*Step Name: Step 1: Check [Validate] link in notification mail
		*Step Description: 
			- Click [Refuse] link on email
		*Input Data: 
			
		*Expected Outcome: 
			- Refuse link will refuse the registration request and it will also take the user to the portal and open the space on its setting screen on "Members" tab.
			- A feedback message is displayed: You have refused Mary Williams to join the space.*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Join_Req_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		String message = notiMessData.getContentByArrayTypeRandom(2);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickRefuseBtnSpaceJoinRequest();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message, fullName);
		emailNot.closeChildBrowsers(parentWindow);
		switchToParentWindow();

		/*Step number: 2
		*Step Name: Step2: Validate when user is already a member of space
		*Step Description: 
			- Click [Validate] link on email
		*Input Data: 
			
		*Expected Outcome: 
			Validate link willaccept the registration request and it will also take the user to the portal and open the space on its setting screen on "Members" tab
			- A feedback message is displayed: You have approved Mary Williams 's request to join the space.*/ 
		String message1 = notiMessData.getContentByArrayTypeRandom(3);
		openMail();
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickAcceptBtnSpaceJoinReqest();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message1, fullName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117580.</li>
	*<li> Test Case Name: Check [Validate] link in notification mail when user is already a member of space.</li>
	*<li> Pre-Condition: $FIRSTNAME = John for ex (first name of user1) $USER = Mary Williams for ex (full name of user2)
	- Login as user 1
	- Create a space "Space1"
	- Login as user2
	- Click [Join a space]
	- Send request to join Space1
	- Notification mail is sent to user1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test20_CheckValidateLinkInNotificationMailWhenUserIsAlreadyAMemberOfSpace() {
		info("Test 20 Check [Validate] link in notification mail when user is already a member of space");
		/*Step Number: 1
		*Step Name: Step 1: Check [Validate] link in notification mail
		*Step Description: 
			- Click [Validate] link on email
		*Input Data: 
			
		*Expected Outcome: 
			Validate link willaccept the registration request and it will also take the user to the portal and open the space on its setting screen on "Members" tab
			- A feedback message is displayed: You have approved Mary Williams 's request to join the space.*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Join_Req_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		String message = notiMessData.getContentByArrayTypeRandom(3);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickAcceptBtnSpaceJoinReqest();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message, fullName);
		emailNot.closeChildBrowsers(parentWindow);
		switchToParentWindow();

		/*Step number: 2
		*Step Name: Step2: Validate when user is already a member of space
		*Step Description: 
			- Click [Validate] link on email again
		*Input Data: 
			
		*Expected Outcome: 
			- It will take the user to the portal on another tab or window of browser and open the space on its setting screen on "Members" tab
			- The feedback message is displayed : Mary Williams is already a member of $SPACE.*/ 
		String message1 = notiMessData.getContentByArrayTypeRandom(4);
		openMail();
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickAcceptBtnSpaceJoinReqest();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(message1, fullName,spaceName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}}