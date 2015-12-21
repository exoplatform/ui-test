package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


	public class SOC_Notification_Intranet_Comment_Activity extends SOC_TestConfig2{

	/**
	*<li> Case ID:125064.</li>
	*<li> Test Case Name: Check Comment Notification (1 comment).</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCommentNotificationOneComment() {
		info("Test 1: Check Comment Notification (1 comment)");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(username2, password);
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The activity message is : $AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/
		String status=notiIntranetData.getNotiContent(0);
		info("Log in with User A");
		magAc.signIn(username1, password);
		info("Check format notification in the notification list");
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,true);
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.
			- The comment that this notification is about is highlighted.*/
		info("Check detail of Activity Comment");
		intraNot.goToDetailCommentNotification(activity,true);
		notiAct.checkCommentExpand(comment, true);
 	}
	
	

	/**
	*<li> Case ID:125065.</li>
	*<li> Test Case Name: Check Comment Notification (2 comments).</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A and User C are connected
	- User A has posted an activity
	- User B has commented on User A activity
	- User C has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckCommentNotificationTwoComments(){
		info("Test 2: Check Comment Notification (2 comments)");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list
			- The badge includes only 1 notification in the total number*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		
		Utils.pause(3000);
		info("User A sent a connection request to UserB");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		info("User A sent a connection request to UserC");
		connMag.connectToAUser(username3);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("UserA and User B are connected");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("User A and User C are connected");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("User C comment on UserA's activity");
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment1);
		
		info("Add all comments to the list");
		comments.add(comment);
		Utils.pause(3000);
		comments.add(comment1);
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The activity message is : $LAST_AVATAR$USER_LIST have commented on your activity$ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LISTis User B, User C
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/
		String status=notiIntranetData.getNotiContent(1);
		info("Log in with User A");
		magAc.signIn(username1, password);
		info("Check format notification in the notification list");
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		arrayUser.add(username3);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,true);
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/ 
		info("Check detail of Activity comment");
		intraNot.goToDetailCommentNotification(activity,true);
		notiAct.checkCommentsExpand(comments, true);
 	}

	/**
	*<li> Case ID:125066.</li>
	*<li> Test Case Name: Check Comment Notification (4 comments).</li>
	*<li> Pre-Condition: - User A is connected with User B, User C and User D
	- User A has posted an activity
	- User B has commented on User A activity
	- User C has commented on User A activity
	- User D has commented on User A activity
	- User B has commented again on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckCommentNotificationFourComments() {
		info("Test 3: Check Comment Notification (4 comments)");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3+"@gmail.com";
		String username4 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email4= username4+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		addUserPage.addUser(username4, password, email4, username4, username4);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		Utils.pause(3000);
		info("User A sent a connection request to UserB");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		info("User A sent a connection request to UserC");
		connMag.connectToAUser(username3);
		info("User A sent a connection request to UserD");
		connMag.connectToAUser(username4);
		
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		Utils.pause(3000);
		hp.goToHomePage();
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("UserA and User B are connected");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("User A and User C are connected");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("User C comment on UserA's activity");
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment1);
		
		info("User A and User D are connected");
		magAc.signIn(username4, password);
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("User D comment on UserA's activity");
		String comment2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment2);
		
		info("Add all comments to the list");
		comments.add(comment);
		comments.add(comment1);
		comments.add(comment2);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The activity message is : $LAST_AVATAR$LAST2_USERS and $COUNT more have commented on your activity $ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User B
			- $LAST2_USERSis User D, User B
			- $COUNT is 1
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/
		String status=notiIntranetData.getNotiContent(2);
		info("Log in with User A");
		magAc.signIn(username1, password);
		info("Check format notification in the notification list");
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		arrayUser.add(username3);
		arrayUser.add(username4);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,true);
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/ 
		info("Check detail of activity comment");
		intraNot.goToDetailCommentNotification(activity,true);
		notiAct.checkCommentsExpand(comments, true);

 	}

	/**
	*<li> Case ID:125067.</li>
	*<li> Test Case Name: Check Comment Notification when a new comment is pushed.</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckCommentNotificationWhenANewCommentIsPushed() {
		info("Test 4: Check Comment Notification when a new comment is pushed");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("Add a activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User A and User B are connected");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Login with User C in another browser session
			- Make a connection request to User A
		*Input Data: 
			
		*Expected Outcome: 
			- The Connect Request notification is displayed in the list of User A*/

		info("User C login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		info("User C sent a connection request to User A");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- With User A, [Accept] the Connection Request
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User C are connected*/
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		info("User A and User C are connected");
		hp.goToConnections();
		connMag.acceptAConnection(username3);

		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- With User C, comment the activity of User A
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- The Comment notification is listed/merged in the same previous notification (step 1)
			- The notification is displayed at the top of the list*/
        info("Log in with User C");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		info("UserC comments in UserA's activity");
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment1);
		
		info("Add all comments to the list");
		comments.add(comment);
		comments.add(comment1);
		
		/*Step number: 5
		*Step Name: 
		*Step Description: 
			- Check notification message
		*Input Data: 
			
		*Expected Outcome: 
			- The notification message is : LAST_AVATAR$USER_LIST have commented on your activity$ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LIST is User B, User C
			- $ACTIVITY is the activity message/title
			- $DATE is the date of the last notification of User C*/

		String status=notiIntranetData.getNotiContent(1);
		info("Log in with user A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		info("Check format notification in the notification list");
		navTool.goToIntranetNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		arrayUser.add(username3);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,true);

 	}

	/**
	*<li> Case ID:125068.</li>
	*<li> Test Case Name: Check Comment Notification after reading the notification.</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckCommentNotificationAfterReadingTheNotification() {
		info("Test 5: Check Comment Notification after reading the notification");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		info("User A and User B are connected");
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
	  
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Click the notification message
		*Input Data: 
			
		*Expected Outcome: 
			- The activity in the activity viewer with all comments expanded. 
			- The comment that this notification is about is highlighted.
			- The notification is read*/
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
	    info("Verify that badge notification is shown. The notification still is not read");
	    waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_BADGE_NUMBER.replace("$num","1"),2000,1);
	   
	    info("Open Notification list");
	    navTool.goToIntranetNotification();
	    info("Read detail notification");
	    intraNot.goToDetailCommentNotification(activity,true);
		notiAct.checkCommentExpand(comment, true);
		info("Verify that badge notification is not shown. The notification is read.");
		waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_BADGE_NUMBER.replace("$num","0"),2000,1);

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Login with User C in another browser session
			- Make a connection request to User A
		*Input Data: 
			
		*Expected Outcome: 
			- The Connection Request is displayed in the notiication list of User A*/
        info("Login with User C");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		info("User sent a connnection request to User A");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- With User A, [Accept] the Connection Request
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User C are connected*/
		info("Login with User A");
		magAc.signIn(username1, password);
		info("User A and User C are connected");
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.acceptRqConnection(username3);

		/*Step number: 5
		*Step Name: 
		*Step Description: 
			- With User C, comment the activity of User A
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A new notification entry is created in the list*/
		
		info("Login with User C");
		magAc.signIn(username3, password);
		info("UserC comments in UserA's activity");
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment1);
		
		info("Add all comments to the list");
	    comments.add(comment1);

		/*Step number: 6
		*Step Name: 
		*Step Description: 
			- Check notification message
		*Input Data: 
			
		*Expected Outcome: 
			- The notification message is : $AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User C
			- $USER is User C
			- $ACTIVITY is the activity message/title
			- $DATE is the date of the notification of User C*/ 
		info("Login with User A");
		magAc.signIn(username1, password);
		info("Check format notification in the notification list");
		String status=notiIntranetData.getNotiContent(0);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		arrayUser.add(username3);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,true);

 	}

	/**
	*<li> Case ID:125069.</li>
	*<li> Test Case Name: Check View All after receiving a Comment notification (1 comment).</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckViewAllAfterReceivingACommentNotificationOneComment() {
		info("Test 6: Check View All after receiving a Comment notification (1 comment)");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("Login with User B");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		info("User A and User B are connected");
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
	    
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to View All page
		*Input Data: 
			
		*Expected Outcome: 
			The notification is available/displayed in the View All page :$AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/ 
		info("Log in with user A");
		magAc.signIn(username1, password);
	    info("Check format notification in the notification list page");
		String status=notiIntranetData.getNotiContent(0);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		arrayUser.add(username3);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,false);
 	}

	/**
	*<li> Case ID:125070.</li>
	*<li> Test Case Name: Check View All page after receiving a Comment notification (2 comments).</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A and User C are connected
	- User A has posted an activity
	- User B has commented on User A activity
	- User C has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckViewAllPageAfterReceivingACommentNotificationTwoComments() {
		info("Test 7: Check View All page after receiving a Comment notification (2 comments)");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		
		hp.goToConnections();
		Utils.pause(3000);
		info("User A sent a connection request to User B");
		connMag.connectToAUser(username2);
		info("User A sent a connection request to User C");
		connMag.connectToAUser(username3);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("Log in with User B");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		info("User A and User B are connected");
		hp.goToConnections();
		Utils.pause(3000);
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Log in with User C");
		magAc.signIn(username3, password);
		info("User A and User C are connected");
		hp.goToConnections();
		Utils.pause(3000);
		connMag.acceptAConnection(username1);
		
		info("UserC comments in UserA's activity");
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment1);
	    
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to View All page
		*Input Data: 
			
		*Expected Outcome: 
			The Comment notification is displayed / available in the page : $LAST_AVATAR$USER_LIST have commented on your activity$ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LISTis User B, User C
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/
		
		magAc.signIn(username1, password);
	    info("Check format notification in the notification list page");
		String status=notiIntranetData.getNotiContent(1);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		arrayUser.add(username3);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,false);

 	}

	/**
	*<li> Case ID:125071.</li>
	*<li> Test Case Name: Check View All page after receiving a Comment notification (4 comments).</li>
	*<li> Pre-Condition: - User A is connected with User B, User C and User D
	- User A has posted an activity
	- User B has commented on User A activity
	- User C has commented on User A activity
	- User D has commented on User A activity
	- User B has commented again on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckViewAllPageAfterReceivingACommentNotificationFourComments() {
		info("Test 8: Check View All page after receiving a Comment notification (4 comments)");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3+"@gmail.com";
		String username4 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email4= username4+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		addUserPage.addUser(username4, password, email4, username4, username4);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		
		hp.goToConnections();
		Utils.pause(3000);
		info("User A sent a connection request to User B");
		connMag.connectToAUser(username2);
		info("User A sent a connection request to User C");
		Utils.pause(3000);
		connMag.connectToAUser(username3);
		info("User A sent a connection request to User D");
		Utils.pause(3000);
		connMag.connectToAUser(username4);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("Log in with User B");
		magAc.signIn(username2, password);
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Log in with User C");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		info("User A and User C are connected");
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserC comments in UserA's activity");
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment1);
		
		info("Log in with User D");
		magAc.signIn(username4, password);
		info("User A and User D are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserD comments in UserA's activity");
		String comment2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment2);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to View All page
		*Input Data: 
			
		*Expected Outcome: 
			The Intranet Notifications is displayed / available in the View All page : $LAST_AVATAR$USER_LIST and $COUNT more have commented on your activity $ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User B
			- $LAST2_USERSis User D, User B
			- $COUNT is 2
			- $ACTIVITY is the activity title/message
			- $DATE is the date of the activity*/ 
		magAc.signIn(username1, password);
	    info("Check format notification in the notification list page");
		String status=notiIntranetData.getNotiContent(2);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		arrayUser.add(username3);
		arrayUser.add(username4);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,false);

 	}

	/**
	*<li> Case ID:125072.</li>
	*<li> Test Case Name: Check View All page right after a new Comment is pushed.</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckViewAllPageRightAfterANewCommentIsPushed() {
		info("Test 9: Check View All page right after a new Comment is pushed");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("Log in with User B");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		info("User A and User B are connected");
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Login with User C in another browser session
			- Make a connection request to User A
		*Input Data: 
			
		*Expected Outcome: 
			- The Connection Request is displayed in the notiication list of User A*/
		info("Log in with User C");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		info("User C sent a connection request to User A");
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- With User A, [Accept] the Connection Request
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User C are connected*/
		info("Log in with User A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.acceptRqConnection(username3);
		
		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- With User C, comment the activity of User A
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- The Comment notification is listed/merged in the same previous notification (step 1)
			- The notification is displayed at the top of the list*/

		info("Log in with User C");
		magAc.signIn(username3, password);
		info("UserB comments in UserA's activity");
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment1);
		
		/*Step number: 5
		*Step Name: 
		*Step Description: 
			- Go to View All page
		*Input Data: 
			
		*Expected Outcome: 
			- The Comment notification is available/displayed in the View All page, at the top of the page. LAST_AVATAR$USER_LIST have commented on your activity$ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User C
			- $USER_LIST is User B, User C
			- $ACTIVITY is the activity message/title
			- $DATE is the date of the last notification of User C*/ 
		info("Log in with User A");
		magAc.signIn(username1, password);
		info("Check format notification in the notification list page");
		String status=notiIntranetData.getNotiContent(1);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		arrayUser.add(username3);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,false);

 	}

	/**
	*<li> Case ID:125073.</li>
	*<li> Test Case Name: Check View All page after reading a Comment notification.</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckViewAllPageAfterReadingACommentNotification() {
		info("Test 10 Check View All page after reading a Comment notification");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list (keep the notification unread)
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3= username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("Log in with User B");
		magAc.signIn(username2, password);
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Click the notification message
		*Input Data: 
			
		*Expected Outcome: 
			- The activity in the activity viewer with all comments expanded. 
			- The comment that this notification is about is highlighted.
			- The notification is read*/
		info("Log in with User A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("Verify that badge notification is shown. The notification still is not read");
	    waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_BADGE_NUMBER.replace("$num","1"),2000,2);
	   
	    info("Open Notification list");
	    navTool.goToIntranetNotification();
	    info("Read detail notification");
	    intraNot.goToDetailCommentNotification(activity,true);
	    notiAct.checkCommentExpand(comment, true);
		info("Verify that badge notification is not shown. The notification is read.");
		waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_BADGE_NUMBER.replace("$num","0"),2000,2);

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Login with User C in another browser session
			- Make a connection request to User A
		*Input Data: 
			
		*Expected Outcome: 
			- The Connection Request is displayed in the notiication list of User A*/
		info("Log in with User C");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		info("User C sent a connection request to User A");
		hp.goToConnections();
		connMag.connectToAUser(username1);

		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- With User A, [Accept] the Connection Request
		*Input Data: 
			
		*Expected Outcome: 
			- User A and User C are connected*/
		
		info("Log in with User A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.acceptRqConnection(username3);

		/*Step number: 5
		*Step Name: 
		*Step Description: 
			- With User C, comment the activity of User A
			- Chek the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A new notification entry is created in the list*/
		
		info("Log in with User C");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		info("UserB comments in UserA's activity");
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment1);

		/*Step number: 6
		*Step Name: 
		*Step Description: 
			- Go to View All page
		*Input Data: 
			
		*Expected Outcome: 
			- The new notification is available/displayed in the View All page : $AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere : 
			- $LAST_AVATAR is the thumbnail of User C
			- $USER is User C
			- $ACTIVITY is the activity message/title
			- $DATE is the date of the notification of User C*/ 
		info("Log in with User A");
		magAc.signIn(username1, password);
		info("Check format notification in the notification list page");
		String status=notiIntranetData.getNotiContent(0);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		arrayUser.add(username3);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,false);

 	}

	/**
	*<li> Case ID:125076.</li>
	*<li> Test Case Name: Check Comment Notification on activity with a link.</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity including a link
	- User B has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckCommentNotificationOnActivityWithALink() {
		info("Test 11 Check Comment Notification on activity with a link");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		Utils.pause(3000);
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		Utils.pause(3000);
		
		info("User A add an activity with a link");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String link = linkData.getLinkByArrayTypeRandom(2);
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("Log in with User B");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		info("User A and User B are connected");
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The activity message is : $AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message : the link is displayed
			- $DATE is the date of the activity*/
		info("Log in with User A");
		magAc.signIn(username1, password);
		info("Check format notification in the notification list page");
		String status=notiIntranetData.getNotiContent(0);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,link,true);

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.
			- The comment that this notification is about is highlighted.*/ 
		intraNot.goToDetailCommentNotification(activity,true);
		notiAct.checkCommentExpand(comment, true);

 	}

	/**
	*<li> Case ID:125077.</li>
	*<li> Test Case Name: Check Comment Notification on activity with a content.</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity including a content
	- User B has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckCommentNotificationOnActivityWithAContent() {
		info("Test 12 Check Comment Notification on activity with a content");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		Utils.pause(3000);
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		Utils.pause(3000);
		info("User A add an activity with attachment file");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String attachedFile=attFileData.getAttachFileByArrayTypeRandom(1);
		String folderPath=siteExPath.getSiteExpPathByIndex(10);
		String drive=siteExDrive.getSiteExpDriveByIndex(4);
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.uploadAndShareFileActivity(drive,folderPath, "TestData/", attachedFile,activity);
		hpAct.checkActivity(activity);
		
		info("Log in with User B");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		info("User A and User B are connected");
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		Utils.pause(3000);
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The activity message is : $AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message : the name of the content
			- $DATE is the date of the activity*/
		
		info("Log in with User A");
		magAc.signIn(username1, password);
		info("Check format notification in the notification list page");
		String status=notiIntranetData.getNotiContent(0);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,attachedFile,true);

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.
			- The comment that this notification is about is highlighted.*/ 
		intraNot.goToDetailCommentNotification(activity,true);
		notiAct.checkCommentExpand(comment, true);

 	}

	/**
	*<li> Case ID:125078.</li>
	*<li> Test Case Name: Check Comment Notification on activity with a wiki page.</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity including a wiki page
	- User B has commented on User A activity
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckCommentNotificationOnActivityWithAWikiPage() {
		info("Test 13 Check Comment Notification on activity with a wiki page");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		Utils.pause(3000);
		info("John sent a connection request to User A");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("John add an activity with wiki page");
		String wiki = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToWiki();
		Utils.pause(3000);
		wHome.goToAddBlankPage();
		Utils.pause(3000);
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wiki, wiki);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.
				replace("${name}",wiki),2000,1);
		
		info("Log in with User A");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		info("John and User A are connected");
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		Utils.pause(3000);
		info("UserA comments in John's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(wiki, comment);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The activity message is : $AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message : the name of the wiki page
			- $DATE is the date of the activity*/
		
		info("Log in with admin account");
		magAc.signIn(username1,password);
		info("Check format notification in the notification list page");
		String status=notiIntranetData.getNotiContent(0);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,wiki,true);

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.
			- The comment that this notification is about is highlighted.*/ 
		intraNot.goToDetailCommentNotification(wiki,true);
		notiAct.checkCommentExpand(comment, true);

 	}

	/**
	*<li> Case ID:125079.</li>
	*<li> Test Case Name: Check Comment Notification on activity with an Event.</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A is manager of Space 1 and User B is member of Space 1
	- User A create an event in Space 1=>
	 Activity of created event is displayed on activity stream of User A
	- User B has commented on event activity of User A
	- The notification "Someone comments on one of my activities" is activated in User A settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_CheckCommentNotificationOnActivityWithAnEvent() {
		info("Test 14 Check Comment Notification on activity with an Event");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- A comment notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		Utils.pause(3000);
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		Utils.pause(3000);
		
		info("User A create a new space");
		String space = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToMySpaces();
		Utils.pause(3000);
		spaMg.addNewSpaceSimple(space,space,60000);
		info("User A invite User B to the space");
		spaHome.goToSpaceSettingTab();
		Utils.pause(3000);
		setSpaceMg.goToMemberTab();
		Utils.pause(3000);
		setSpaceMg.inviteUser(username2,true,username2+" "+username2);
		
		
		info("User A create a new event");
		String newEvent= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space);
		Utils.pause(3000);
		spaMg.goToAgendaTab();
		Utils.pause(3000);
		evMg.goToAddEventFromActionBar();
		evMg.inputBasicQuickEvent(newEvent,newEvent);
		evMg.saveQuickAddEvent();
		
		
		info("Log in with User B");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		info("User B accepted invitation from space of User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(space);
		Utils.pause(3000);
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(newEvent, comment);
		

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The activity message is : $AVATAR$USER has commented on your activity$ACTIVITY$DATEWhere : 
			- $AVATAR is the thumbnail of User B
			- $USER is User B
			- $ACTIVITY is the activity title/message : the name of event
			- $DATE is the date of the activity*/
		info("Log in with User A");
		magAc.signIn(username1, password);
		info("Check format notification in the notification list page");
		String status=notiIntranetData.getNotiContent(0);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,newEvent,true);

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.
			- The comment that this notification is about is highlighted.*/ 
		//intraNot.checkDetailActivityNotifications(newEvent, comments,true);
		intraNot.goToDetailCommentNotification(newEvent,true);
		notiAct.checkCommentExpand(comment, true);
 	}}