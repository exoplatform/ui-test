package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.notiMode;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


	public class SOC_Notification_Email_Mention extends SOC_TestConfig3{

	/**
	*<li> Case ID:117400.</li>
	*<li> Test Case Name: Check category which Mention notification belongs to.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCategoryWhichMentionNotificationBelongsTo() {
		info("Test 1: Check category which Mention notification belongs to");
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
		*Step Name: Step 2: Check category which Mention notification belongs to
		*Step Description: 
			Check category whichMention notification belongs to
		*Input Data: 
			
		*Expected Outcome: 
			- Mention notification belongs to "Activity Stream" category*/ 
		String category = notiCatData.getCategoryByArrayTypeRandom(3);
		myNoti.verifyNotiBelongToCategory(category,notiMode.AS_Mention);

 	}

	/**
	*<li> Case ID:117416.</li>
	*<li> Test Case Name: Check default setting of Mention notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDefaultSettingOfMentionNotification() {
		info("Test 2: Check default setting of Mention notification");
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
		*Step Name: Step 2: Check default setting of Mention notification
		*Step Description: 
			- Check default setting of Mention notification
		*Input Data: 
			
		*Expected Outcome: 
			- Default setting is:Email and intranet notification is enabled*/ 
		myNoti.verifyNotificationDefault(notificationType.AS_Mention_email);

 	}

	/**
	*<li> Case ID:117436.</li>
	*<li> Test Case Name: Check digest mail when digest mail settings is Weekly.</li>
	*<li> Pre-Condition: - Login as UserA
	- Mention UserB in his activity "Test mention an user" for ex
	- Digest mail for mention is set: Weekly</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test03_CheckDigestMailWhenDigestMailSettingsIsWeekly() {
		info("Test 3: Check digest mail when digest mail settings is Weekly");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of Sunday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- Digest mail is sent to UserB at 23:30 of Sunday*/ 

 	}

	/**
	*<li> Case ID:117444.</li>
	*<li> Test Case Name: Check digest message after delete activity of the mention.</li>
	*<li> Pre-Condition: User A mention the User B in an activity</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test04_CheckDigestMessageAfterDeleteActivityOfTheMention() {
		info("Test 4: Check digest message after delete activity of the mention");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with the User A
			- Delete the activity where the User B is mentionned
		*Input Data: 
			
		*Expected Outcome: 
			- Theactivity is deleted*/

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
			- The activity of mention is not diaplayed in the digest mail*/ 

 	}

	/**
	*<li> Case ID:117447.</li>
	*<li> Test Case Name: Check digest message after delete comment of the mention.</li>
	*<li> Pre-Condition: User A mention the User B in a comment of an activity</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test05_CheckDigestMessageAfterDeleteCommentOfTheMention() {
		info("Test 5: Check digest message after delete comment of the mention");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with the User A
			- Delete the comment where the User B is mentionned
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
			- The comment of mention is not diaplayed in the digest mail*/ 

 	}

	/**
	*<li> Case ID:117452.</li>
	*<li> Test Case Name: Check digest message when there are <= 3 notifications.</li>
	*<li> Pre-Condition: - Login as UserA
	- Mention UserD in his activity "Test mention an user" for ex
	- UserB and UserC are connected with UserA
	- Login as UserB, mention UserD in UserA's activity: Test mention an user
	- Login as UserC, mention UserD in UserA's activity: Test mention an user
	- Digest mail for mention is set: Daily for ex</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test06_CheckDigestMessageWhenThereAreLeastthan3Notifications() {
		info("Test 6: Check digest message when there are <= 3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message sent
		*Step Description: 
			- Check digest message sent to userD
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is:UserA, UserB, UserC have mentioned you in: Test mention an user.*/ 

 	}

	/**
	*<li> Case ID:117457.</li>
	*<li> Test Case Name: Check digest message when there are > 3 notifications.</li>
	*<li> Pre-Condition: - Login as UserA
	- Mention UserF in his activity "Test mention an user" for ex
	- UserB and UserC, UserD, UserE are connected with UserA
	- UserB, UserC, UserD, UserE also mention userF in UserA's activity: Test mention an user
	- Digest mail for mention is set: Daily for ex</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test07_CheckDigestMessageWhenThereAreMorethan3Notifications() {
		info("Test 7: Check digest message when there are > 3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change date/time next to 1 day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message sent
		*Step Description: 
			- Check digest message sent to userF
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is:UserE, UserD, UserC and 2 others have mentioned you in: Test mention an user*/ 

 	}

	/**
	*<li> Case ID:117463.</li>
	*<li> Test Case Name: Check digest message when there is only one notification.</li>
	*<li> Pre-Condition: - Login as UserA
	- Mention UserB in his activity "Test mention an user" for ex
	- Digest mail for mention is set: Daily for ex</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test08_CheckDigestMessageWhenThereIsOnlyOneNotification() {
		info("Test 8: Check digest message when there is only one notification");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message sent
		*Step Description: 
			- Check digest message sent to userB
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is:UserA has mentioned you in: Test mention an user.*/ 

 	}

	/**
	*<li> Case ID:117473.</li>
	*<li> Test Case Name: Check event label of Mention notification in settings page.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckEventLabelOfMentionNotificationInSettingsPage() {
		info("Test 9: Check event label of Mention notification in settings page");
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
		*Step Name: Step 2: Check event label of Mention notification in settings page
		*Step Description: 
			- Check event label of Mention notification in settings page
		*Input Data: 
			
		*Expected Outcome: 
			- The event label of Mention notification in settings page: Someone @mentions me*/ 
		String label = notiLabelData.getContentByArrayTypeRandom(7);
		myNoti.verifyLabelNotificationType(label);
 	}

	/**
	*<li> Case ID:117488.</li>
	*<li> Test Case Name: Check Mention notification mail content.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test10_CheckMentionNotificationMailContent() throws AWTException {
		info("Test 10 Check Mention notification mail content");
		/*Step Number: 1
		*Step Name: Step 1: Mention user
		*Step Description: 
			- Login as userA
			- Mention userB in activity or comment in any activity stream ("Test mention" for example)
		*Input Data: 
			
		*Expected Outcome: 
			- Mention user successfully*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Mention_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B mention User A in an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.mentionUserActivity(arrayUser.get(0),activity);
		hpAct.checkActivity(activity);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		/*Step number: 2
		*Step Name: Step 2: Check Mention notification mail content
		*Step Description: 
			- Check Mention notification mail content
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail subject is: 
			New mention of youHi UserB,[Avatar of UserA] 
			UserA has mentioned you in the following post:
			Test mentionReply | View the full discussion*/ 
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(10);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(11);

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
	*<li> Case ID:117489.</li>
	*<li> Test Case Name: Check Mention notification mail subject.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test11_CheckMentionNotificationMailSubject() throws AWTException {
		info("Test 11 Check Mention notification mail subject");
		/*Step Number: 1
		*Step Name: Step 1: Mention user
		*Step Description: 
			- Login as userA
			- Mention userB in activity or comment in any activity stream
		*Input Data: 
			
		*Expected Outcome: 
			- Mention user successfully*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Mention_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B mention User A in an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.mentionUserActivity(arrayUser.get(0),activity);
		hpAct.checkActivity(activity);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);

		/*Step number: 2
		*Step Name: Step 2: Check Mention notification mail subject
		*Step Description: 
			- Check Mention notification mail subject
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail subject is: You have been mentioned by UserA*/ 
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117490.</li>
	*<li> Test Case Name: Check Mention notification mail when someone mentions the user somewhere (activity or comment) in any activity stream.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test12_CheckMentionNotificationMailWhenSomeoneMentionsTheUserSomewhereActivityOrCommentInAnyActivityStream() throws AWTException {
		info("Test 12 Check Mention notification mail when someone mentions the user somewhere (activity or comment) in any activity stream");
		/*Step Number: 1
		*Step Name: Step 1: Mention user
		*Step Description: 
			- Login as userA
			- Mention userB in activity or comment in any activity stream
		*Input Data: 
			
		*Expected Outcome: 
			- Mention user successfully*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Mention_email);
		
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
		hpAct.addCommentWithMentionUser(activity,arrayUser.get(0),comment);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);

		/*Step number: 2
		*Step Name: Step 2: Check Mention notification mail
		*Step Description: 
			- Check Mention notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Mention notification mail is sent to userB*/ 
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}

	/**
	*<li> Case ID:117503.</li>
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
			- Check notification name of mention
		*Input Data: 
			
		*Expected Outcome: 
			- Notification name is: Mention*/ 
		String name =  notiLabelData.getModeByArrayTypeRandom(7);
		notiAdmin.verifyNameNotificationType(name);
 	}

	/**
	*<li> Case ID:117534.</li>
	*<li> Test Case Name: Check the digest message is appended for each activity where the user has been mentioned.</li>
	*<li> Pre-Condition: - There are 3 activities are added by users
	- 5 users mention UserA on activity1
	- 2 users mention UserA on activity2
	- 1 user mentions UserA on his activity (activity3)
	- Digest mail setting: Daily</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test14_CheckTheDigestMessageIsAppendedForEachActivityWhereTheUserHasBeenMentioned() {
		info("Test 14 Check the digest message is appended for each activity where the user has been mentioned");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time of server is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message sent
		*Step Description: 
			- Check digest message sent to userA
		*Input Data: 
			
		*Expected Outcome: 
			The digest message is appended for each activity where the user has been mentionedFor ex:John Smith, Mary Williams, Jack Daniels and 3 others have mentioned you in : When will PLF 4.1 be releasedJohn Smith, Mary Williams have mentioned you in : Thanks for your presentationJames Davis has mentioned you in : GitHub has been down all day. I can't push my work today*/ 

 	}

	/**
	*<li> Case ID:117555.</li>
	*<li> Test Case Name: Check [Count] and [Activity title] link in digest mail.</li>
	*<li> Pre-Condition: - There are more than 3 users mention UserA on an activity: "Test activity with mention" ( 5 users for ex)
	- Digest mail is sent to UserA with content: UserB, UserC, UserD and [2] others have mention you in: [Test activity with mention]</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test15_CheckCountAndActivityTitleLinkInDigestMail() {
		info("Test 15 Check [Count] and [Activity title] link in digest mail");
		/*Step Number: 1
		*Step Name: Step 1: Click [Count] link in digest mail
		*Step Description: 
			- Click [2] link in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The user is taken directly to the activity and all comments are expanded.*/

		/*Step number: 2
		*Step Name: Step 2: Click [Activity title] link in digest mail
		*Step Description: 
			- Click [Test activity with mention] link in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The user is taken directly to the activity and all comments are expanded.*/ 

 	}

	/**
	*<li> Case ID:117573.</li>
	*<li> Test Case Name: Check [Reply] link in notification mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test16_CheckReplyLinkInNotificationMail() throws AWTException {
		info("Test 16 Check [Reply] link in notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Mention user
		*Step Description: 
			- Login as userA
			- Mention userB in activity or comment in any activity stream
		*Input Data: 
			
		*Expected Outcome: 
			- Mention user successfully
			- Notification mail is sent to UserB*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Mention_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B mention User A in an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.mentionUserActivity(arrayUser.get(0),activity);
		hpAct.checkActivity(activity);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);

		/*Step number: 2
		*Step Name: Step 2: Check [Reply] link in notification mail
		*Step Description: 
			- Click [Reply] link
		*Input Data: 
			
		*Expected Outcome: 
			- Replylink will take the user to the portal and displays the activity stream on the position of the activity with the comment box opened, ready for reply
			- If the mention is made in a comment, all comments are expanded and this comment is highlighted*/ 
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(true,activity);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117584.</li>
	*<li> Test Case Name: Check [View full discussion] link in notification mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test17_CheckViewFullDiscussionLinkInNotificationMail() throws AWTException {
		info("Test 17 Check [View full discussion] link in notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Mention user
		*Step Description: 
			- Login as userA
			- Mention userB in activity or comment in any activity stream
		*Input Data: 
			
		*Expected Outcome: 
			- Mention user successfully
			- Notification mail is sent to UserB*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Mention_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B mention User A in an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.mentionUserActivity(arrayUser.get(0),activity);
		hpAct.checkActivity(activity);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);


		/*Step number: 2
		*Step Name: Step 2: Check [View full discussion] link in notification mail
		*Step Description: 
			- Click [View full discussion] link
		*Input Data: 
			
		*Expected Outcome: 
			- View full discussion link will take the user to the portal and displays the activity stream on the position of the activity with all comments expanded.
			- If the mention is made in a comment, this comment is highlighted*/ 
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,activity);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);

 	}}