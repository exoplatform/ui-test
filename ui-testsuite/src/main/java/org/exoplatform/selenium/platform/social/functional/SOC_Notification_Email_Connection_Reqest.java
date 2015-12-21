package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.notiMode;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


	public class SOC_Notification_Email_Connection_Reqest extends SOC_TestConfig3{

	/**
	*<li> Case ID:117398.</li>
	*<li> Test Case Name: Check category which Connect request notification belongs to.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCategoryWhichConnectRequestNotificationBelongsTo() {
		info("Test 1: Check category which Connect request notification belongs to");
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
		*Step Name: Step 2: Check category which Connect request notification belongs to
		*Step Description: 
			Check category which Connect request notification belongs to
		*Input Data: 
			
		*Expected Outcome: 
			- Connection request notification belongs to "Connections" category*/ 
		String category = notiCatData.getCategoryByArrayTypeRandom(1);
		myNoti.verifyNotiBelongToCategory(category, notiMode.ConnectionRequest);

 	}

	/**
	*<li> Case ID:117408.</li>
	*<li> Test Case Name: Check connect request notification mail content.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Go to Connections page and send request to user2
	- $PORTAL_NAME = Social intranet for ex
	- $FIRSTNAME = user2
	- Notification mail is sent to user2</li>
	*/
	@Test
	public  void test02_CheckConnectRequestNotificationMailContent() {
		info("Test 2: Check connect request notification mail content");
		/*Step Number: 1
		*Step Name: Step 1: Check notification mail format
		*Step Description: 
			- Check notification mail format
		*Input Data: 
			
		*Expected Outcome: 
			- The notification mail format is: New connection requestHi user2,
			[Avatar of user1] user1 has sent you a connection request. 
			Accept the connection request and start collaborating with user1?Accept | Refuse*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(0));
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(5);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(8);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(9);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName,arrayUser.get(1), emailContent);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117409.</li>
	*<li> Test Case Name: Check connection request notification when the user receives a connection request from another user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckConnectionRequestNotificationWhenTheUserReceivesAConnectionRequestFromAnotherUser() {
		info("Test 3: Check connection request notification when the user receives a connection request from another user");
		/*Step Number: 1
		*Step Name: Step 1: Request to connect
		*Step Description: 
			- Login as user1
			- Go to Connections page and send request to user2
		*Input Data: 
			
		*Expected Outcome: 
			- Request is sent*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(0));
		
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
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(5);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117415.</li>
	*<li> Test Case Name: Check default setting of connection request notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDefaultSettingOfConnectionRequestNotification() {
		info("Test 4: Check default setting of connection request notification");
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
		*Step Name: Step 2: Check default setting of connection request notification
		*Step Description: 
			Check default setting of connection request notification
		*Input Data: 
			
		*Expected Outcome: 
			- Default setting of connection request notification are: Instantly and Daily*/ 
		myNoti.verifyNotificationDefault(notificationType.ConnectionRequest_email);

 	}

	/**
	*<li> Case ID:117433.</li>
	*<li> Test Case Name: Check digest mail when digest mail setting is Weekly.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - UserA send request to connect with UserB
	- Digest mail setting is Weekly</li>
	CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test05_CheckDigestMailWhenDigestMailSettingIsWeekly() {
		info("Test 5: Check digest mail when digest mail setting is Weekly");
		/*Step Number: 1
		*Step Name: Step 1: Change date/time of server
		*Step Description: 
			- Stop server
			- Change date/time of server before weekly digest mail is sent 10 minutes (11:20, Sunday)
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check the digest mail
		*Step Description: 
			- Check the digest mail at 11:30
		*Input Data: 
			
		*Expected Outcome: 
			- Digest mail is sent to UserB*/ 

 	}

	/**
	*<li> Case ID:117440.</li>
	*<li> Test Case Name: Check digest message after cancel of a connection request.</li>
	*<li> Pre-Condition: User B send a connection request to the User A</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test06_CheckDigestMessageAfterCancelOfAConnectionRequest() {
		info("Test 6: Check digest message after cancel of a connection request");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with the User B
			- Go to the Connection page, Cancel the connection request sent to the User A
		*Input Data: 
			
		*Expected Outcome: 
			- The connection request is cancelled*/

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
			- The Connection Request from the User B is not displayed in the digest message list*/ 

 	}

	/**
	*<li> Case ID:117471.</li>
	*<li> Test Case Name: Check event label of connection request in notification settings page.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckEventLabelOfConnectionRequestInNotificationSettingsPage() {
		info("Test 7: Check event label of connection request in notification settings page");
		/*Step Number: 1
		*Step Name: Step 1: Access notification settings
		*Step Description: 
			- Login
			- Move mouse over the full name of user and select [Notifications] on the menu
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Settings page is appeared*/
		navTool.goToAdminNotifications();
		notiAdmin.verifyTilePage();

		/*Step number: 2
		*Step Name: Step 2: Check label event of connection request in notification settings page
		*Step Description: 
			Check label event of connection request in notification settings page
		*Input Data: 
			
		*Expected Outcome: 
			- The event label is: Someone sends me a connection request*/ 
		String label = notiLabelData.getContentByArrayTypeRandom(2);
		notiAdmin.verifyLabelNotificationType(label);
 	}

	/**
	*<li> Case ID:117494.</li>
	*<li> Test Case Name: Check notification mail subject.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Go to Connections page and send request to user2
	- $PORTAL_NAME = Social intranet for ex
	- Notification mail is sent to user2</li>
	*/
	@Test
	public  void test08_CheckNotificationMailSubject() {
		info("Test 8: Check notification mail subject");
		/*Step Number: 1
		*Step Name: Step 1: Check notification mail subject
		*Step Description: 
			- Check notification mail subject
		*Input Data: 
			
		*Expected Outcome: 
			- The notification mail subject is: user1 wants to connect with you on eXo*/ 
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(0));
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(5);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117501.</li>
	*<li> Test Case Name: Check notification name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckNotificationName() {
		info("Test 9: Check notification name");
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
			- Check notification name o Connection request
		*Input Data: 
			
		*Expected Outcome: 
			- Notification name is: Connection Request*/ 
		String name =  notiLabelData.getModeByArrayTypeRandom(2);
		notiAdmin.verifyNameNotificationType(name);

 	}

	/**
	*<li> Case ID:117536.</li>
	*<li> Test Case Name: Check the digest message when there are <= 3 notifications.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as UserA, UserB and UserC
	- Go to Connections page and send request to user2
	- Digest mail setting for connection request is Daily</li>
	CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test10_CheckTheDigestMessageWhenThereAreLeastAt3Notifications() {
		info("Test 10 Check the digest message when there are <= 3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change date/time of server
		*Step Description: 
			- Stop server
			- Change date/time of server to Saturday 11:20am of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check the digest message when there are <= 3 notifications
		*Step Description: 
			- Check digest message in digest mail sent to user2 at 11:30am
		*Input Data: 
			
		*Expected Outcome: 
			the digest message is: You've received a connection request from UserA, UserB, UserC*/ 

 	}

	/**
	*<li> Case ID:117539.</li>
	*<li> Test Case Name: Check the digest message when there are > 3 notifications.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as UserA, UserB and UserC, user D and UserE
	- Go to Connections page and send request to user2
	- Digest mail setting for connection request is Daily</li>
	CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test11_CheckTheDigestMessageWhenThereAreMoreThan3Notifications() {
		info("Test 11 Check the digest message when there are > 3 notifications");
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
		*Step Name: Step 2: Check the digest message when there are <= 3 notifications
		*Step Description: 
			- Check digest message in digest mail sent to user2
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: You've received a connection request from user E, user D, user C and 2 others.*/ 

 	}

	/**
	*<li> Case ID:117542.</li>
	*<li> Test Case Name: Check the digest message when there is only one notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Go to Connections page and send request to user2
	- Digest mail setting for connection request is Daily</li>
	CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test12_CheckTheDigestMessageWhenThereIsOnlyOneNotification() {
		info("Test 12 Check the digest message when there is only one notification");
		/*Step Number: 1
		*Step Name: Step 1: Change date/time of server
		*Step Description: 
			- Stop server
			- Change date/time of server 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check the digest message when there is only one notification
		*Step Description: 
			- Check digest message in digest mail sent to user2
		*Input Data: 
			
		*Expected Outcome: 
			the digest message is: You've received a connection request from user1*/ 

 	}

	/**
	*<li> Case ID:117549.</li>
	*<li> Test Case Name: Check [Accept] link in notification mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Go to Connections page and send request to user2
	- $PORTAL_NAME = Social intranet for ex
	- $FIRSTNAME = user2
	- Notification mail is sent to user2</li>
	*/
	@Test
	public  void test13_CheckAcceptLinkInNotificationMail() {
		info("Test 13 Check [Accept] link in notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Check [Accept] link in notification mail
		*Step Description: 
			- Click [Accept] link in notification mail
		*Input Data: 
			
		*Expected Outcome: 
			Accept link will approve the connection and 
			will take the user to the activity stream of the new connection*/ 
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(0));
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(5);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickAcceptBtnActivity();
		emailNot.getAllChildWindows();
		waitForAndGetElement(userProPage.ELEMETN_ACTIVITY_TAB);
		waitForAndGetElement(userProPage.ELEMENT_USER_NAME_PAGE.replace("$fullName",fullName));
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117561.</li>
	*<li> Test Case Name: Check [Count] link in the digest message.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as UserA, UserB and UserC, user D and UserE
	- Go to Connections page and send request to user2
	- Digest mail setting for connection request is Daily</li>
	CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test14_CheckCountLinkInTheDigestMessage() {
		info("Test 14 Check [Count] link in the digest message");
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
		*Step Name: Step 2: Check the digest message when there are <= 3 notifications
		*Step Description: 
			- Check digest message in digest mail sent to user2
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: You've received a connection request from user E, user D, user C and 2 others.*/

		/*Step number: 3
		*Step Name: Step 3: Check [Count] link
		*Step Description: 
			- Click on [2] link in the digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The user is taken to the receivers' Received Requests screen.*/ 

 	}

	/**
	*<li> Case ID:117565.</li>
	*<li> Test Case Name: Check [Refuse] link in notification mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - Login as user1
	- Go to Connections page and send request to user2
	- $PORTAL_NAME = Social intranet for ex
	- $FIRSTNAME = user2
	- Notification mail is sent to user2</li>
	*/
	@Test
	public  void test15_CheckRefuseLinkInNotificationMail() {
		info("Test 15 Check [Refuse] link in notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Check [Refuse] link in notification mail
		*Step Description: 
			- Click [Refuse] link in notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Refuse link will deny the connection request and will take the user to Request Received list.
			- A Feedback message will display: You refused user1 connection request.*/ 
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(0));
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(5);
		String refuseMessage=notiMessData.getContentByArrayTypeRandom(1);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickRefuseBtn();
		emailNot.getAllChildWindows();
		emailNot.verifyFeedBackMessageRefuseConnection(refuseMessage, fullName);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}}