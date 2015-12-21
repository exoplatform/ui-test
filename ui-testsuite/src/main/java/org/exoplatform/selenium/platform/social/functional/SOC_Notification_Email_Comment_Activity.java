package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.notiMode;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


	public class SOC_Notification_Email_Comment_Activity extends SOC_TestConfig3{

	/**
	*<li> Case ID:117397.</li>
	*<li> Test Case Name: Check category which Comment notification belongs to.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCategoryWhichCommentNotificationBelongsTo() {
		info("Test 1: Check category which Comment notification belongs to");
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
		*Step Name: Step 2: Check category which Comment notification belongs to
		*Step Description: 
			Check category which Comment notification belongs to
		*Input Data: 
			
		*Expected Outcome: 
			- Comment notification belongs to "Activity Stream" category*/ 
		String category = notiCatData.getCategoryByArrayTypeRandom(3);
		myNoti.verifyNotiBelongToCategory(category,notiMode.AS_Comment);

 	}

	/**
	*<li> Case ID:117406.</li>
	*<li> Test Case Name: Check Comment notification mail content.</li>
	*<li> Pre-Condition: - UserB comment on activity of UserA 
	*or comment on activity where UserA has already commented
	- Comment notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckCommentNotificationMailContent() {
		info("Test 2: Check Comment notification mail content");
		/*Step Number: 1
		*Step Name: Step 1: Check Comment notification mail content
		*Step Description: 
			- Check Comment notification mail content
		*Input Data: 
			
		*Expected Outcome: 
			- Comment notification mail content is: 
			New comment on your activityHi UserA,UserB has posted a comment on your activity. 
			See the comment below:[Activity][Comment]Reply | View the full discussion*/ 
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
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(1);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(1);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(6);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName, fullName, emailContent,activity);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117407.</li>
	*<li> Test Case Name: Check Comment notification mail subject.</li>
	*<li> Pre-Condition: - UserB comment on activity of UserA 
	*or comment on activity where UserA has already commented
	- Comment notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckCommentNotificationMailSubject() {
		info("Test 3: Check Comment notification mail subject");
		/*Step Number: 1
		*Step Name: Step 1: Check Comment notification mail subject
		*Step Description: 
			- Check Comment notification mail subject
		*Input Data: 
			
		*Expected Outcome: 
			- Comment notification mail subject is:
			Notification subject is :UserB has commented one of your activities*/ 
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
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(1);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117417.</li>
	*<li> Test Case Name: Check default settings of Comment notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDefaultSettingsOfCommentNotification() {
		info("Test 4: Check default settings of Comment notification");
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
		*Step Name: Step 2: Check default settings of Comment notification
		*Step Description: 
			Check default settings of Comment notification
		*Input Data: 
			
		*Expected Outcome: 
			- Default settings of Comment notification are: Email and Intranet notification are enabled*/
		myNoti.verifyNotificationDefault(notificationType.AS_Comment_email);

 	}

	/**
	*<li> Case ID:117432.</li>
	*<li> Test Case Name: Check digest mail when digest mail setting is Weekly.</li>
	*<li> Pre-Condition: - UserB comment on activity of UserA 
	*or comment on activity where UserA has already commented
	- Digest mail settings is Weekly</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test05_CheckDigestMailWhenDigestMailSettingIsWeekly() {
		info("Test 5: Check digest mail when digest mail setting is Weekly");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time 11:20 am of Sundy of this week
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check the digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- Digest mail is sent to UserA (at 11:30)*/ 

 	}

	/**
	*<li> Case ID:117448.</li>
	*<li> Test Case Name: Check digest message after deleting a comment.</li>
	*<li> Pre-Condition: User A add a comment to an activity of the User B</li>
	*<li> Post-Condition: </li>
	*CANNOUT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test06_CheckDigestMessageAfterDeletingAComment() {
		info("Test 6: Check digest message after deleting a comment");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with the User A
			- Delete the comment added to the activity of the User B
		*Input Data: 
			
		*Expected Outcome: 
			- Thecomment is deleted*/

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
			- The comment is not diaplayed in the digest mail*/ 

 	}

	/**
	*<li> Case ID:117455.</li>
	*<li> Test Case Name: Check digest message when there are <=3 notifications.</li>
	*<li> Pre-Condition: - UserB, UserC, UserD comment on activity of UserA or comment on activity where UserA has already commented
	- Digest mail settings is Daily</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test07_CheckDigestMessageWhenThereAreLeastAt3Notifications() {
		info("Test 7: Check digest message when there are <=3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message
		*Step Description: 
			- Check the digest message in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: UserB, UserC, UserD have commented on your activity : [Activity title]*/ 

 	}

	/**
	*<li> Case ID:117460.</li>
	*<li> Test Case Name: Check digest message when there are >3 notifications.</li>
	*<li> Pre-Condition: - More than 3 users comment on activity of UserA or comment on activity where UserA has already commented (5 users for ex)
	- Digest mail settings is Daily</li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="pending")
	public  void test08_CheckDigestMessageWhenThereAreMoreThan3Notifications() {
		info("Test 8: Check digest message when there are >3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message
		*Step Description: 
			- Check the digest message in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: UserB, UserC, UserD and 2 others have commented on your activity : [Activity title]*/ 

 	}

	/**
	*<li> Case ID:117462.</li>
	*<li> Test Case Name: Check digest message when there is only one notification.</li>
	*<li> Pre-Condition: - UserB comment on activity of UserA or comment on activity where UserA has already commented
	- Digest mail settings is Daily</li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="pending")
	public  void test09_CheckDigestMessageWhenThereIsOnlyOneNotification() {
		info("Test 9: Check digest message when there is only one notification");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message
		*Step Description: 
			- Check the digest message in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: UserB has commented on your activity : [Activity title]*/ 

 	}

	/**
	*<li> Case ID:117470.</li>
	*<li> Test Case Name: Check event label of Comment notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckEventLabelOfCommentNotification() {
		info("Test 10 Check event label of Comment notification");
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
		*Step Name: Step 2: Check event label of Comment notification
		*Step Description: 
			Check event label of Comment notification
		*Input Data: 
			
		*Expected Outcome: 
			- Event label of Comment notification is: Someone comments on one of my activities*/ 
		String label = notiLabelData.getContentByArrayTypeRandom(9);
		myNoti.verifyLabelNotificationType(label);

 	}

	/**
	*<li> Case ID:117496.</li>
	*<li> Test Case Name: Check notification mail when someone comment on activity where user already commented.</li>
	*<li> Pre-Condition: UserA is connected with UserB</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckNotificationMailWhenSomeoneCommentOnActivityWhereUserAlreadyCommented() {
		info("Test 11 Check notification mail when someone comment on activity where user already commented");
		/*Step Number: 1
		*Step Name: Step 1: Add new activity
		*Step Description: 
			- Login as userA
			- Comment on an activity (not added by himself)
		*Input Data: 
			
		*Expected Outcome: 
			- Comment is added*/
		info("Create 3 users for testing");
		createNewUser(3);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_email);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A sent a connection request to User C");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(2));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);

		/*Step number: 2
		*Step Name: Step 2: Add comment to activity
		*Step Description: 
			- Login as userB and add comment on activity at step 1
		*Input Data: 
			
		*Expected Outcome: 
			- New comment is added*/
		
		info("Log in with User C");
		magAc.signIn(arrayUser.get(2), password);
		
		info("User A and User C are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserC comments in UserA's activity");
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment1);

		/*Step number: 3
		*Step Name: Step 3: Check notification mail
		*Step Description: 
			- Check notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Comment notification mail isn't sent to UserA*/ 
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(1);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyNOTPresentTitleASEmailNoti(titleEmail,fullName,"");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117497.</li>
	*<li> Test Case Name: Check notification mail when someone comment on user's activity.</li>
	*<li> Pre-Condition: UserA is connected with UserB</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckNotificationMailWhenSomeoneCommentOnUsersActivity() {
		info("Test 12 Check notification mail when someone comment on user's activity");
		/*Step Number: 1
		*Step Name: Step 1: Add new activity
		*Step Description: 
			- Login as userA
			- Add new activity
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is added*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_email);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		/*Step number: 2
		*Step Name: Step 2: Add comment to activity
		*Step Description: 
			- Login as userB and add comment on UserA's activity
		*Input Data: 
			
		*Expected Outcome: 
			- New comment is added*/
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);

		/*Step number: 3
		*Step Name: Step 3: Check notification mail
		*Step Description: 
			- Check notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Comment notification mail is sent to UserA*/ 
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(1);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyNOTPresentTitleASEmailNoti(titleEmail,fullName,"");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117500.</li>
	*<li> Test Case Name: Check notification name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckNotificationName() {
		info("Test 13 Check notification name");
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
			- Check notification name of Comment
		*Input Data: 
			
		*Expected Outcome: 
			- Notification name is: Comment*/ 
		String name =  notiLabelData.getModeByArrayTypeRandom(9);
		notiAdmin.verifyNameNotificationType(name);
 	}

	/**
	*<li> Case ID:117533.</li>
	*<li> Test Case Name: Check the digest message is appended for each activity where the user has been commented.</li>
	*<li> Pre-Condition: - There are 3 activities
	- More than 3 users comment on activity1
	- Less than or equal 3 users comment on activity2
	- An user comments on activity3
	- Digest mail setting is: Daily</li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="pending")
	public  void test14_CheckTheDigestMessageIsAppendedForEachActivityWhereTheUserHasBeenCommented() {
		info("Test 14 Check the digest message is appended for each activity where the user has been commented");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message
		*Step Description: 
			Check the digest message
		*Input Data: 
			
		*Expected Outcome: 
			- Check the digest message is appended for each activity where the user has been commented.For ex:John Smith, Mary Williams, Jack Daniels and 3 others have commented your activity : When will PLF 4.1 be releasedJohn Smith, Mary Williams have commented on your activity : Thanks for your presentationJames Davis has commented your activity : GitHub has been down all day. I can't push my work today*/ 

 	}

	/**
	*<li> Case ID:117556.</li>
	*<li> Test Case Name: Check [Count] and [Activity Title] link in digest message.</li>
	*<li> Pre-Condition: - More than 3 users (5 users for ex) comment on activity1: "Activity test 01" for ex
	- Digest mail setting is: Daily
	- The digest mail is sent with content for example:John Smith, Mary Williams, Jack Daniels and 2 others have commented your activity : Activity test 01</li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="pending")
	public  void test15_CheckCountAndActivityTitleLinkInDigestMessage() {
		info("Test 15 Check [Count] and [Activity Title] link in digest message");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check [Count] link
		*Step Description: 
			- Click [2] link in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- the user is taken directly to the activity and all comments are expanded.*/

		/*Step number: 3
		*Step Name: Step 3: Check [Activity title] link
		*Step Description: 
			- Click [Activity test 01] title
		*Input Data: 
			
		*Expected Outcome: 
			- the user is taken directly to the activity Viewer and activity. 
			- all comments are expanded.*/ 

 	}

	/**
	*<li> Case ID:117571.</li>
	*<li> Test Case Name: Check [Reply] link in notification mail.</li>
	*<li> Pre-Condition: - UserB comment on activity of 
	*UserA or comment on activity where UserA has already commented
	- Comment notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_CheckReplyLinkInNotificationMail() {
		info("Test 16 Check [Reply] link in notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Check [Reply] link in notification mail
		*Step Description: 
			- Click [Reply] link in notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Replylink will take the user to the portal and display 
			the activity in the activity viewer with all comments expanded and the comment box opened, 
			ready for reply. 
			- The comment in notification is highlighted.*/ 
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
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(1);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(true,activity);
		notAct.checkCommentExpand(comment, true);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117582.</li>
	*<li> Test Case Name: Check [View full discussion] link in notification mail.</li>
	*<li> Pre-Condition: - UserB comment on activity of UserA or comment on activity where UserA has already commented
	- Comment notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test17_CheckViewFullDiscussionLinkInNotificationMail() {
		info("Test 17 Check [View full discussion] link in notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Check [View full discussion] link in notification mail
		*Step Description: 
			- Click [View full discussion] link in notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- [View full discussion] link will take the user to the portal and 
			display the activity in the activity viewer with all comments expanded. 
			- The comment in notification is highlighted.*/ 
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
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(1);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
		notAct.checkCommentExpand(comment, true);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}}