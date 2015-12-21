package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.notiMode;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


	public class SOC_Notification_Email_Post_On_My_Stream extends SOC_TestConfig3{

	/**
	*<li> Case ID:117403.</li>
	*<li> Test Case Name: Check category which Post on my stream notification belongs to.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCategoryWhichPostOnMyStreamNotificationBelongsTo() {
		info("Test 1: Check category which Post on my stream notification belongs to");
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
		*Step Name: Step 2: Check category which Post on my stream notification belongs to
		*Step Description: 
			Check category which Post on my stream notification belongs to
		*Input Data: 
			
		*Expected Outcome: 
			- Post on my stream notification belongs to "Activity Stream" category*/ 
		String category = notiCatData.getCategoryByArrayTypeRandom(4);
		myNoti.verifyNotiBelongToCategory(category,notiMode.AS_Post);

 	}

	/**
	*<li> Case ID:117421.</li>
	*<li> Test Case Name: Check default settings of Post on my stream notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDefaultSettingsOfPostOnMyStreamNotification() {
		info("Test 2: Check default settings of Post on my stream notification");
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
		*Step Name: Step 2: Check default settings of Post on my stream notification
		*Step Description: 
			Check default settings of Post on my stream notification
		*Input Data: 
			
		*Expected Outcome: 
			- Default settings of Post on my stream notification is: Never*/ 
		myNoti.verifyNotificationDefault(notificationType.AS_Post_email);

 	}

	/**
	*<li> Case ID:117434.</li>
	*<li> Test Case Name: Check digest mail when digest mail setting is Weekly.</li>
	*<li> Pre-Condition: - UserA is connected with UserB
	- Login as UserA
	- Go to activity stream of UserB
	- Post an activity on activity stream of userB
	- Digest mail setting is: Weekly</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test03_CheckDigestMailWhenDigestMailSettingIsWeekly() {
		info("Test 3: Check digest mail when digest mail setting is Weekly");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 11:20 am of Sunday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail at 11:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest mail is sent to UserB after 1 week*/ 

 	}

	/**
	*<li> Case ID:117445.</li>
	*<li> Test Case Name: Check digest message after delete an activity from my stream.</li>
	*<li> Pre-Condition: User A add an activity in the activities stream of the User B</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test04_CheckDigestMessageAfterDeleteAnActivityFromMyStream() {
		info("Test 4: Check digest message after delete an activity from my stream");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with the User A
			- Open the activity stream of the User B
			- Add an activity
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is added to the activities stream of the User B*/

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
			- The activity is not diaplayed in the digest mail*/ 

 	}

	/**
	*<li> Case ID:117456.</li>
	*<li> Test Case Name: Check digest message when there are <=3 notifications.</li>
	*<li> Pre-Condition: - UserA, UserC are connected with UserB
	- Login as UserA, UserC
	- Go to activity stream of UserB
	- Post an activity on activity stream of userB
	- Digest mail setting is: Daily for example</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test05_CheckDigestMessageWhenThereAreLessThan3Notifications() {
		info("Test 5: Check digest message when there are <=3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail sent to UserB at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: UserA, UserC posted on your activity stream.*/ 

 	}

	/**
	*<li> Case ID:117461.</li>
	*<li> Test Case Name: Check digest message when there are >3 notifications.</li>
	*<li> Pre-Condition: - There are more than 3 users are connect with UserB (5 users for ex)
	- 5 users go to activity stream of UserB
	- Post an activity on activity stream of userB
	- Digest mail setting is: Daily for example</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test06_CheckDigestMessageWhenThereAreMoreThan3Notifications() {
		info("Test 6: Check digest message when there are >3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail sent to UserB at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: [Full name of UserA], [Full name of UserC], [Full name of UserD] and 2 others posted on your activity stream.*/ 

 	}

	/**
	*<li> Case ID:117465.</li>
	*<li> Test Case Name: Check digest message when there is only one notification.</li>
	*<li> Pre-Condition: - UserA is connected with UserB
	- Login as UserA
	- Go to activity stream of UserB
	- Post an activity on activity stream of userB
	- Digest mail setting is: Daily for example</li>
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
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail sent to UserB at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: [Full name of UserA] posted on your activity stream.*/ 

 	}

	/**
	*<li> Case ID:117476.</li>
	*<li> Test Case Name: Check event label of Post on my stream notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckEventLabelOfPostOnMyStreamNotification() {
		info("Test 8: Check event label of Post on my stream notification");
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
		*Step Name: Step 2: Check event label of Post on my stream notification
		*Step Description: 
			Check event label of Post on my stream notification
		*Input Data: 
			
		*Expected Outcome: 
			- Event label of Post on my stream notification is: 
			Someone posts a message on my activity stream*/ 
		String label = notiLabelData.getContentByArrayTypeRandom(8);
		myNoti.verifyLabelNotificationType(label);
 	}

	/**
	*<li> Case ID:117498.</li>
	*<li> Test Case Name: Check notification maill content.</li>
	*<li> Pre-Condition: UserA is connected with UserB</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckNotificationMaillContent() {
		info("Test 9: Check notification maill content");
		/*Step Number: 1
		*Step Name: Step 1: Post activity to other user's stream
		*Step Description: 
			- Login as UserA
			- Go to activity stream of UserB
			- Post an activity on activity stream of userB
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is posted*/
		
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Post_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepts connection request from User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B post an activity on User A's activity Stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2: Check notification mail content
		*Step Description: 
			- Check notification mail content
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail content is:
			New post on your activity streamHi UserB,
			[Full name of UserA] hasposted on your activity stream. 
			See the post below:[Activity]Reply | View the full discussion*/ 
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(3);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(13);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,activity);
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName, fullName, emailContent,activity);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117499.</li>
	*<li> Test Case Name: Check notification maill subject.</li>
	*<li> Pre-Condition: UserA is connected with UserB</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckNotificationMaillSubject() {
		info("Test 10 Check notification maill subject");
		/*Step Number: 1
		*Step Name: Step 1: Post activity to other user's stream
		*Step Description: 
			- Login as UserA
			- Go to activity stream of UserB
			- Post an activity on activity stream of userB
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is posted*/

		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Post_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepts connection request from User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B post an activity on User A's activity Stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2: Check notification mail subject
		*Step Description: 
			- Check notification mail subject
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail subject is:
			UserA has posted on your activity stream: $ACTIVITY_TITLE*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117506.</li>
	*<li> Test Case Name: Check notification name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckNotificationName() {
		info("Test 11 Check notification name");
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
			- Check notification name of Post on my Stream
		*Input Data: 
			
		*Expected Outcome: 
			- Notification name is: Post on my Stream*/ 
		String name =  notiLabelData.getModeByArrayTypeRandom(8);
		notiAdmin.verifyNameNotificationType(name);

 	}

	/**
	*<li> Case ID:117516.</li>
	*<li> Test Case Name: Check notification when someone posts an activity on the receiver's stream.</li>
	*<li> Pre-Condition: UserA is connected with UserB</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckNotificationWhenSomeonePostsAnActivityOnTheReceiversStream() {
		info("Test 12 Check notification when someone posts an activity on the receiver's stream");
		/*Step Number: 1
		*Step Name: Step 1: Post activity to other user's stream
		*Step Description: 
			- Login as UserA
			- Go to activity stream of UserB
			- Post an activity on activity stream of userB
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is posted*/
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Post_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepts connection request from User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B post an activity on User A's activity Stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2: Check notification mail
		*Step Description: 
			- Check notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail is sent to UserB about new post on his stream*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117560.</li>
	*<li> Test Case Name: Check [Count] link in digest message.</li>
	*<li> Pre-Condition: - There are more than 3 users are connect with UserB (5 users for ex)
	- 5 users go to activity stream of UserB
	- Post an activity on activity stream of userB
	- Digest mail setting is: Daily for example</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test13_CheckCountLinkInDigestMessage() {
		info("Test 13 Check [Count] link in digest message");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check [Count] link in digest mail
		*Step Description: 
			- Check mail at 23:30
			- Click [2] link in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The user is taken directly to the user activity stream (My Activities).*/ 

 	}

	/**
	*<li> Case ID:117574.</li>
	*<li> Test Case Name: Check [Reply] link in notification mail.</li>
	*<li> Pre-Condition: UserA is connected with UserB</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_CheckReplyLinkInNotificationMail() {
		info("Test 14 Check [Reply] link in notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Post activity to other user's stream
		*Step Description: 
			- Login as UserA
			- Go to activity stream of UserB
			- Post an activity on activity stream of userB
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is posted
			- Notification is sent to UserB*/
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Post_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepts connection request from User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B post an activity on User A's activity Stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2: Check [Reply] link in notification mail
		*Step Description: 
			- Click [Reply] link in notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Replylink will take the user to the portal and displays 
			the activity stream on the position of the activity with the comment box opened, ready for reply*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,activity);
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(true,activity);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117585.</li>
	*<li> Test Case Name: Check [View full discussion] link in notification mail.</li>
	*<li> Pre-Condition: UserA is connected with UserB</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_CheckViewFullDiscussionLinkInNotificationMail() {
		info("Test 15 Check [View full discussion] link in notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Post activity to other user's stream
		*Step Description: 
			- Login as UserA
			- Go to activity stream of UserB
			- Post an activity on activity stream of userB
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is posted
			- Notification is sent to UserB*/
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Post_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepts connection request from User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B post an activity on User A's activity Stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2: Check [View full discussion] link in notification mail
		*Step Description: 
			- Click [View full discussion] link in notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- View full discussion link will take the user to the portal 
			and displays the activity stream on the position of the activity with all comments expanded.*/
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,activity);
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,activity);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117586.</li>
	*<li> Test Case Name: Check [your activity stream] link in digest mail.</li>
	*<li> Pre-Condition: - UserA is connect with UserB
	- UserA posted an activity on UserB's activity stream
	- Digest mail setting is: Daily for example</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test16_CheckYourActivityStreamLinkInDigestMail() {
		info("Test 16 Check [your activity stream] link in digest mail");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check [your activity stream] in digest mail
		*Step Description: 
			- Click [your activity stream] in digest mail at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			- [your activity stream] is a link to the receivers activity stream.*/ 

 	}}