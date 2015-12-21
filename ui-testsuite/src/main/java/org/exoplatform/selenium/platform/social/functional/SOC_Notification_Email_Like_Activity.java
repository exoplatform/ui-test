package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.notiMode;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.Test;

public class SOC_Notification_Email_Like_Activity extends SOC_TestConfig3{

	/**
	*<li> Case ID:117399.</li>
	*<li> Test Case Name: Check category which Like notification belongs to.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCategoryWhichLikeNotificationBelongsTo() {
		info("Test 1: Check category which Like notification belongs to");
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
		*Step Name: Step 2: Check category which Like notification belongs to
		*Step Description: 
			Check category which Like notification belongs to
		*Input Data: 
			
		*Expected Outcome: 
			- - Like notification belongs to "Activity Stream" category*/ 
		String category = notiCatData.getCategoryByArrayTypeRandom(4);
		myNoti.verifyNotiBelongToCategory(category,notiMode.AS_Like);
 	}
	
	/**
	*<li> Case ID:117483.</li>
	*<li> Test Case Name: Check Like notification mail content.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckLiketNotificationMailContent() {
		info("Test 2: Check Like notification mail content");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		/*Step Number: 1
		*Step Name: Step 1: Add new activity
		*Step Description: 
			- Login as UserA
			- Add new activity
		*Input Data: 
		*Expected Outcome: 
			- New activity is added*/ 
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepts connection request from User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		/*Step Number: 2
		*Step Name: Step 2: Like activity
		*Step Description: 
			- Login as UserB
			- Like UserA's activity at step 1
		*Input Data: 
		*Expected Outcome: 
			- Like activity successfully
			- Notification mail is sent to UserA*/ 
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(2);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(7);

		/*Step Number: 3
		*Step Name: Step 3: Check notification mail content
		*Step Description: 
			- Check notification mail content
		*Input Data: 
		*Expected Outcome: 
			- Notification mail content is: New like on your activity stream
			Hi UserA, UserB likes your activity: [Activity]Reply | View the full discussion*/ 
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
	*<li> Case ID:117484.</li>
	*<li> Test Case Name: Check Like notification mail subject.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckLikeNotificationMailSubject() {
		info("Test 3: Check Comment notification mail subject");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		/*Step Number: 1
		*Step Name: Step 1: Add new activity
		*Step Description: 
			- Login as UserA
			- Add new activity
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is added*/ 
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepted connection request from user A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		

		/*Step Number: 2
		*Step Name: Step 2: Like activity
		*Step Description: 
			- Login as UserB
			- Like UserA's activity at step 1
		*Input Data: 
			
		*Expected Outcome: 
			- Like activity successfully
			- Notification mail is sent to UserA*/ 
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
		
		/*Step Number: 3
		*Step Name: Step 3: Check notification mail subject
		*Step Description: 
			- Check notification mail subject
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail subject is: UserB likes your activity [Activity title] */ 
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
	*<li> Case ID:117418.</li>
	*<li> Test Case Name: Check default settings of Like notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDefaultSettingsOfCommentNotification() {
		info("Test 4: Check default settings of like notification");
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
		*Step Name: Step 2: Check default settings of Like notification
		*Step Description: 
			Check default settings of Like notification
		*Input Data: 
			
		*Expected Outcome: 
			- Default settings of like notification is Weekly*/ 
		myNoti.verifyNotificationDefault(notificationType.AS_Like_email);
 	}
	
	/**
	*<li> Case ID:117429.</li>
	*<li> Test Case Name: Check digest mail when digest mail setting is Daily.</li>
	*<li> Pre-Condition: 
		- Login as UserA
		- Add new activity
		- Login as UserB
		- Like UserA's activity
		- Digest mail setting is: Daily</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test05_CheckDigestMailWhenDigestMailSettingIsdaily() {
		info("Test 5: Check digest mail when digest mail setting is Daily");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time 23:20 of this day
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
			- Digest mail is sent to UserA at 23:20 of this day*/ 
 	}
	
	/**
	*<li> Case ID:117449.</li>
	*<li> Test Case Name: Check digest message after Unlike an activity.</li>
	*<li> Pre-Condition: User A add a Like to an activity of the User B</li>
	*<li> Post-Condition: </li>
	*CANNOUT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test06_CheckDigestMessageAfterUnikeAnActivity() {
		info("Test 6: Check digest message after unlike an activity");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with the User A
			- Unlike activity added to the activity of the User B
		*Input Data: 
			
		*Expected Outcome: 
			- The like number of activity decreases 1*/

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
			- The like is not diaplayed in the digest mail*/ 
 	}
	
	/**
	*<li> Case ID:117437.</li>
	*<li> Test Case Name: Check digest message when there are <=3 notifications.</li>
	*<li> Pre-Condition: 
		- Login as UserA
		- Add new activity
		- Login as UserB, UserC
		- Like UserA's activity
		- Digest mail setting is: Weekly</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test07_CheckDigestMessageWhenThereAreLessThan3Notifications() {
		info("Test 7: Check digest message when there are <=3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of Friday
			- Restart server 
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest message
		*Step Description: 
			- Check digest mail sent to UserA
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: UserB, UserC have liked your activity: [Activity title]*/ 
 	}
	
	/**
	*<li> Case ID:117438.</li>
	*<li> Test Case Name: Check digest message when there are >3 notifications.</li>
	*<li> Pre-Condition: 
		- Login as UserA
		- Add new activity
		- More than 3 users like UserA's activity (5 users for ex)
		- Digest mail setting is: Weekly</li>
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
			- Check digest mail sent to UserA
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: UserB, UserC, UserD and 2 others have liked your activity: [Activity title]*/ 
 	}
	
	/**
	*<li> Case ID:117439.</li>
	*<li> Test Case Name: Check digest message when there is only one notification.</li>
	*<li> Pre-Condition: 
		- Login as UserA
		- Add new activity
		- Login as UserB
		- Like UserA's activity
		- Digest mail setting is: Weekly</li>
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
			- Check digest mail sent to UserA at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: UserB has liked your activity: [Activity title]*/ 
 	}
	
	/**
	*<li> Case ID:117472.</li>
	*<li> Test Case Name: Check event label of Like notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckEventLabelOfLikeNotification() {
		info("Test 10: Check event label of Like notification");
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
		*Step Name: Step 2: Check event label of Like notification
		*Step Description: 
			Check event label of Like notification
		*Input Data: 
			
		*Expected Outcome: 
			- Event label of Like notification is: Someone likes one of my activities*/ 
		String label = notiLabelData.getContentByArrayTypeRandom(6);
		myNoti.verifyLabelNotificationType(label);
 	}
	
	/**
	*<li> Case ID:117515.</li>
	*<li> Test Case Name: Check notification when someone likes an activity for which the receiver is the author.</li>
	*<li> Pre-Condition: The icon "send me an email right away" is checked</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckNotificationMailWhenSomeoneLikesAnActivityForWhichTheRecieverIsTheAuthor() {
		info("Test 11 Check notification mail when someone likes an activity for which the receiver is the author");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		/*Step Number: 1
		*Step Name: Step 1: Add new activity
		*Step Description: 
			- Login as UserA
			- Add new activity
		*Input Data: 
		*Expected Outcome: 
			- New activity is added*/ 
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepts connection request from User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		/*Step Number: 2
		*Step Name: Step 2: Like activity
		*Step Description: 
			- Login as UserB
			- Like UserA's activity at step 1
		*Input Data: 
		*Expected Outcome: 
			- Like activity successfully*/ 
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);

		/*Step Number: 3
		*Step Name: Step 3: Check notification mail content
		*Step Description: 
			- Check notification mail 
		*Input Data: 
		*Expected Outcome: 
			- Like notification mail is sent to UserA*/ 
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117502.</li>
	*<li> Test Case Name: Check notification name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckNotificationName() {
		info("Test 12: Check notification name");
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
			- Check notification name of Like
		*Input Data: 
			
		*Expected Outcome: 
			- Notification name is: Like on Activity*/ 
		String name =  notiLabelData.getModeByArrayTypeRandom(6);
		notiAdmin.verifyNameNotificationType(name);
 	}
	
	/**
	*<li> Case ID:117531.</li>
	*<li> Test Case Name: Check the digest message is appended for each activity that was liked.</li>
	*<li> Pre-Condition: 
		- There are 3 activities of UserA
		- More than 3 users like activity1 ( 6 users for ex)
		- Less than or equal 3 users like activity2 
		- An user likes activity 3
		- Digest mail setting is: Weekly</li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="pending")
	public  void test13_CheckTheDigestMessageIsAppendedForEachActivityThatWasLiked() {
		info("Test 13: Check the digest message is appended for each activity that was liked");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of Friday
			- Restart server 
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail sent to UserA
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is appended for each activity that was liked
For example:
    John Smith, Mary Williams, Jack Daniels and 3 others like your activity : When will PLF 4.1 be released
    John Smith, Mary Williams  like your activity : Thanks for your presentation
    James Davis likes your activity : GitHub has been down all day. I can't push my work today*/ 
 	}
	
	/**
	*<li> Case ID:117554.</li>
	*<li> Test Case Name: Check [Count] and [Activity title] link in digest mail.</li>
	*<li> Pre-Condition: 
		- UserA add new activity
		- More than 3 users like UserA's activity ( 6 users for ex)
		- Digest mail setting: Weekly</li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="pending")
	public  void test14_CheckCountAndActivityTitleLinkInDigestMessage() {
		info("Test 14: Check [Count] and [Activity Title] link in digest message");
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
			- Click [3] link in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- the user is taken directly to the activity and all likers are expanded..*/

		/*Step number: 3
		*Step Name: Step 3: Check [Activity title] link
		*Step Description: 
			- Click [Activity title] link in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- the user is taken directly to the Activity Viewer and the activity. a
			- All likers are expanded.*/ 
 	}
	
	/**
	*<li> Case ID:117572.</li>
	*<li> Test Case Name: Check [Reply] link in notification mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_CheckReplyLinkInNotificationMail() {
		info("Test 15: Check [Reply] link in notification mail");
		info("Create data Test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		/*Step Number: 1
		*Step Name: Step 1: Add new activity
		*Step Description: 
			- Login as UserA
			- Add new activity
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is added.*/ 
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepted connection request from user A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		/*Step Number: 2
		*Step Name: Step 2: Like activity
		*Step Description: 
			- Login as UserB
			- Like UserA's activity at step 1
		*Input Data: 
			
		*Expected Outcome: 
			- Like activity successfully
			- Notification mail is sent to UserA.*/ 
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signOut();
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
		
		/*Step Number: 3
		*Step Name: Step 3: Check [Reply] link in notification mail 
		*Step Description: 
			- Click [Reply] link in notification mail 
		*Input Data: 
			
		*Expected Outcome: 
			- Reply link will take the user to the portal 
			and display the activity viewer on the position of the activity 
			with the comment box opened, ready for reply*/ 
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(true,activity);
		notAct.checkLikeInActivityViewer("1");
        emailNot.closeChildBrowsers(parentWindow);
 	}
	
	/**
	*<li> Case ID:117583.</li>
	*<li> Test Case Name: Check [View full discussion] link in notification mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_CheckViewFullDiscussionLinkInNotificationMail() {
		info("Test 16: Check [View full discussion] link in notification mail");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		/*Step Number: 1
		*Step Name: Step 1: Add new activity
		*Step Description: 
			- Login as UserA
			- Add new activity
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is added.*/ 
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
		
		/*Step Number: 2
		*Step Name: Step 2: Like activity
		*Step Description: 
			- Login as UserB
			- Like UserA's activity at step 1
		*Input Data: 
			
		*Expected Outcome: 
			- Like activity successfully
			- Notification mail is sent to UserA.*/ 
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
		
		/*Step Number: 3
		*Step Name: Step 3: Check [View full discussion] link in notification mail 
		*Step Description: 
			- Click [View full discussion] link in notification mail 
		*Input Data: 
			
		*Expected Outcome: 
			- View full discussion link will take the user to the portal 
			and display the activity viewer on the position of the activity 
			with all comments expanded..*/ 
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,activity);
		notAct.checkLikeInActivityViewer("1");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
}
