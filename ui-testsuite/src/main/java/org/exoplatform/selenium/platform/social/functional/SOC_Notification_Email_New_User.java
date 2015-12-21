package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.notiMode;
import org.testng.annotations.*;


	public class SOC_Notification_Email_New_User extends SOC_TestConfig3{

	/**
	*<li> Case ID:117401.</li>
	*<li> Test Case Name: Check category which new user notification belongs to.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCategoryWhichNewUserNotificationBelongsTo() {
		info("Test 1: Check category which new user notification belongs to");
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
		*Step Name: Step 2: Check category which new user notification belongs to
		*Step Description: 
			Check category which new user notification belongs to
		*Input Data: 
			
		*Expected Outcome: 
			- New user notification belongs to "General" category*/
		String category = notiCatData.getCategoryByArrayTypeRandom(1);
		myNoti.verifyNotiBelongToCategory(category,notiMode.NewUser);

 	}

	/**
	*<li> Case ID:117419.</li>
	*<li> Test Case Name: Check default settings of new user notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDefaultSettingsOfNewUserNotification() {
		info("Test 2: Check default settings of new user notification");
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
		*Step Name: Step 2: Check default settings of new user notification
		*Step Description: 
			Check default settings of new user notification
		*Input Data: 
			
		*Expected Outcome: 
			- Default settings of new user notification is Daily*/ 
		myNoti.verifyNotificationDefault(notificationType.NewUser_email);

 	}

	/**
	*<li> Case ID:117443.</li>
	*<li> Test Case Name: Check digest message after delete a new created user.</li>
	*<li> Pre-Condition: A new user is already created</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test03_CheckDigestMessageAfterDeleteANewCreatedUser() {
		info("Test 3: Check digest message after delete a new created user");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet
			- Delete the new created user
		*Input Data: 
			
		*Expected Outcome: 
			- The new user is deleted*/

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
			- Check daily digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The digest mail is sent
			- The new user is not displayed in the digest message list*/ 

 	}

	/**
	*<li> Case ID:117451.</li>
	*<li> Test Case Name: Check digest message when digest mail setting is Weekly.</li>
	*<li> Pre-Condition: - Add new user or new user sign up
	- Setting for digest mail is Weekly</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test04_CheckDigestMessageWhenDigestMailSettingIsWeekly() {
		info("Test 4: Check digest message when digest mail setting is Weekly");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change date/time to the date weekly digest mail is sent (23h30, Sunday)
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The digest mail is sent*/ 

 	}

	/**
	*<li> Case ID:117453.</li>
	*<li> Test Case Name: Check digest message when there are <= 3 notifications.</li>
	*<li> Pre-Condition: - Add new 3 users or 3 new users sign up
	- $USER_LIST = UserA, UserB, UserC
	- $PORTAL_NAME = Social intranet for ex
	- Setting for digest mail is Daily</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test05_CheckDigestMessageWhenThereAreLeastThan3Notifications() {
		info("Test 5: Check digest message when there are <= 3 notifications");
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
		*Step Name: Step 2: Check digest message in digest mail
		*Step Description: 
			Check digest message in digest mail at 23h30
		*Input Data: 
			
		*Expected Outcome: 
			The digest message is: UserA, UserB, UserC have joined Social Intranet*/ 

 	}

	/**
	*<li> Case ID:117458.</li>
	*<li> Test Case Name: Check digest message when there are > 3 notifications.</li>
	*<li> Pre-Condition: - Add new more than 3 users or more than 3 new users sign up (5 users for ex)
	- $PORTAL_NAME = Social intranet for ex
	- $LAST3_USERS = UserA, UserB, UserC
	- $COUNT = 2
	- Setting for digest mail is Daily</li>
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
			- Change date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message in digest mail
		*Step Description: 
			Check digest message in digest mail at 23h30
		*Input Data: 
			
		*Expected Outcome: 
			The digest message is: UserA, UserB, UserC and 2 more have joined Social Intranet*/ 

 	}

	/**
	*<li> Case ID:117464.</li>
	*<li> Test Case Name: Check digest message when there is only one notification.</li>
	*<li> Pre-Condition: - Add new user or new user sign up
	- $FIRSTNAME = John
	- $USER = Fqa Test for example
	- $PORTAL_NAME = Social intranet for ex
	- Setting for digest mail is Daily</li>
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
			- Change date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message in digest mail
		*Step Description: 
			Check digest message in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			The digest message is: Fqa Test has joined eXo*/ 

 	}

	/**
	*<li> Case ID:117474.</li>
	*<li> Test Case Name: Check event label of new user notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckEventLabelOfNewUserNotification() {
		info("Test 8: Check event label of new user notification");
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
		*Step Name: Step 2: Check event label of new user notification
		*Step Description: 
			- Check event label of new user notification
		*Input Data: 
			
		*Expected Outcome: 
			- The event label is: Someone joins the social intranet*/
		String label = notiLabelData.getContentByArrayTypeRandom(1);
		myNoti.verifyLabelNotificationType(label);

 	}

	/**
	*<li> Case ID:117491.</li>
	*<li> Test Case Name: Check new user notification mail format.</li>
	*<li> Pre-Condition: - Add new user or new user sign up
	- $FIRSTNAME = John
	- $USER = Fqa Test for example
	- $PORTAL_NAME = Social intranet for ex
	- New user notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckNewUserNotificationMailFormat() {
		info("Test 9: Check new user notification mail format");
		/*Step Number: 1
		*Step Name: Step 1: Check new user notification mail format
		*Step Description: 
			Check new user notification mail format
		*Input Data: 
			
		*Expected Outcome: 
			- Subject of the notification mail is: 
			New user on Social IntranetHi John,[Avatar of new user] 
			Fqa Test has joined eXo. Interested to connect and start collaborate with Fqa TestConnect now*/
		
		info("Create 1 users for testing");
		createNewUser(1);
		
		info("John add UserA to administration group");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup("Platform/Administration");
		userAndGroup.addUsersToGroup(arrayUser.get(0),"*",false, true);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.NewUser_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("Create 1 users for testing");
		createNewUser(1);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(4);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(4);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(12);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName,arrayUser.get(1), emailContent,true);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117504.</li>
	*<li> Test Case Name: Check notification name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckNotificationName() {
		info("Test 10 Check notification name");
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
			- Check notification name of new user
		*Input Data: 
			
		*Expected Outcome: 
			- Notification name is: New user*/ 
		String name =  notiLabelData.getModeByArrayTypeRandom(1);
		notiAdmin.verifyNameNotificationType(name);

 	}

	/**
	*<li> Case ID:117513.</li>
	*<li> Test Case Name: Check notification when adding new user by other user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckNotificationWhenAddingNewUserByOtherUser() {
		info("Test 11 Check notification when adding new user by other user");
		/*Step Number: 1
		*Step Name: Step 1: Add new user
		*Step Description: 
			- Login as admin
			- Go to Administration 
			-
			-> Users 
			-
			-> Add user
			- Add new user
		*Input Data: 
			
		*Expected Outcome: 
			- New user is added successfully*/

		info("Create 1 users for testing");
		createNewUser(1);
		
		info("John add UserA to administration group");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup("Platform/Administration");
		userAndGroup.addUsersToGroup(arrayUser.get(0),"*",false, true);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.NewUser_email);
		
		
		info("Create 1 users for testing");
		createNewUser(1);
		

		/*Step number: 2
		*Step Name: Step 2: Check new user notification is sent
		*Step Description: 
			Check new user notification is sent
		*Input Data: 
			
		*Expected Outcome: 
			- New user notification is sent with correct content*/ 
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(4);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117514.</li>
	*<li> Test Case Name: Check notification when new user sign up.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED BECAUSE ON REGISTER PAGE HAS TEXT VALIDATION.
	*/
	@Test(groups="pending")
	public  void test12_CheckNotificationWhenNewUserSignUp() {
		info("Test 12 Check notification when new user sign up");
		/*Step Number: 1
		*Step Name: Step 1: Sign up
		*Step Description: 
			- Sign up new user (user1 for ex)
		*Input Data: 
			
		*Expected Outcome: 
			- New user is signed up successfully*/

		/*Step number: 2
		*Step Name: Step 2: Check new user notification is sent
		*Step Description: 
			Check new user notification is sent
		*Input Data: 
			
		*Expected Outcome: 
			- New user notification is sent with correct content*/ 

 	}

	/**
	*<li> Case ID:117528.</li>
	*<li> Test Case Name: Check subject of new user notification mail.</li>
	*<li> Pre-Condition: - Add new user or new user sign up
	- $USER = Fqa Test for example
	- $PORTAL_NAME = Social intranet for ex
	- New user notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckSubjectOfNewUserNotificationMail() {
		info("Test 13 Check subject of new user notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Check subject of new user notification mail
		*Step Description: 
			Check subject of new user notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Subject of the notification mail is: Fqa Test has joined eXo*/ 
		info("Create 1 users for testing");
		createNewUser(1);
		
		info("John add UserA to administration group");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup("Platform/Administration");
		userAndGroup.addUsersToGroup(arrayUser.get(0),"*",false, true);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.NewUser_email);
		
		
		info("Create 1 users for testing");
		createNewUser(1);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(4);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117553.</li>
	*<li> Test Case Name: Check [Connect now] link.</li>
	*<li> Pre-Condition: - Add new user or new user sign up
	- New user notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_CheckConnectNowLink() {
		info("Test 14 Check [Connect now] link");
		/*Step Number: 1
		*Step Name: Step 1: Check [Connect now] link
		*Step Description: 
			- Click [Connect now] link in the notification email
		*Input Data: 
			
		*Expected Outcome: 
			Connect now link will send a connection request 
			to the user that has just joined the social intranet.
			 It will also take the user to the portal and open this new connection's profile.*/ 

		info("Create 1 users for testing");
		createNewUser(1);
		
		info("John add UserA to administration group");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup("Platform/Administration");
		userAndGroup.addUsersToGroup(arrayUser.get(0),"*",false, true);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.NewUser_email);
		
		info("Create 1 users for testing");
		createNewUser(1);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(4);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickConnectNowBtn();
		emailNot.getAllChildWindows();
	    waitForAndGetElement(userProPage.ELEMENT_USER_NAME_PAGE.replace("$fullName", fullName));
        emailNot.closeChildBrowsers(parentWindow);
        
        switchToParentWindow();
        hp.goToConnections();
        connMag.goToConnectionTab(selectTabOption.PENDING);
        connMag.verifyRequestPending(arrayUser.get(1),true);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
        
 	}

	/**
	*<li> Case ID:117562.</li>
	*<li> Test Case Name: Check [Count] link on digest message.</li>
	*<li> Pre-Condition: - Add new more than 3 users or more than 3 new users sign up (5 users for ex)
	- $PORTAL_NAME = Social intranet for ex
	- $LAST3_USERS = UserA, UserB, UserC
	- $COUNT = 2
	- Setting for digest mail is Daily</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test15_CheckCountLinkOnDigestMessage() {
		info("Test 15 Check [Count] link on digest message");
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
		*Step Name: Step 2: Check digest message in digest mail
		*Step Description: 
			Check digest message in digest mail at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			The digest message is: UserA, UserB, UserC and 2 more have joined eXo*/

		/*Step number: 3
		*Step Name: Step 3: Check [Count] link
		*Step Description: 
			- Click [2] on digest message
		*Input Data: 
			
		*Expected Outcome: 
			- The user is taken to the Connections screen.*/ 

 	}}