package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


	public class SOC_Notification_Intranet_Notification_List extends SOC_TestConfig2{

	/**
	*<li> Case ID:125117.</li>
	*<li> Test Case Name: Check layout of read / unread notifications.</li>
	*<li> Pre-Condition: The user has read and unread notifications</li>
	*<li> Post-Condition: </li>
	*SHOULD CHECK LAYOUT'S STYLE BY MANUAL
	*/
	@Test(groups="pending")
	public  void test01_CheckLayoutOfReadUnreadNotifications() {
		info("Test 1: Check layout of read / unread notifications");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login
			- Click the notifications icon
		*Input Data: 
			
		*Expected Outcome: 
			- Unread notifications look different than read notification (blue background on unread notifications)*/ 

 	}

	/**
	*<li> Case ID:125118.</li>
	*<li> Test Case Name: Read a notification.</li>
	*<li> Pre-Condition: The user has received several new notifications (like, comment, connection request)</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_ReadANotification() {
		info("Test 2: Read a notification");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login
			- Click the notifications icon
		*Input Data: 
			
		*Expected Outcome: 
			- Unread notifications are displayed in the list*/

		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		
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
		hpAct.likeActivity(activity);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Add comment to Comment list");
		comments.add(comment);
		
		info("User C login");
		magAc.signIn(username3, password);
		
		info("User C sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		String statusSendRq=notiIntranetData.getContentByArrayTypeRandom(2);
		String statusLikeAc=notiIntranetData.getContentByArrayTypeRandom(6);
		String statusCommAc=notiIntranetData.getNotiContent(0);
		
		info("Log in with User A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.checkStatus(statusSendRq,username3);
		intraNot.checkStatus(statusCommAc,username2);
		intraNot.checkStatus(statusLikeAc,username2);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Click 1 notification area of a like or comment notifications
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirected accordingly to the associated activity*/
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		
		info("Click 1 notification area of a like notification");
		intraNot.goToDetailLikeNotification(username2, true);
		waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_DETAIL_ACTIVITY_DES.replace("$des",activity));
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click the notifications icon again
		*Input Data: 
			
		*Expected Outcome: 
			- The list of notifications is displayed
			- The previously clicked notifications is displayed as read (different layout)*/
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.checkReadNotification(statusLikeAc,username2);
		
		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- Click [Accept] action of a connection request notiication
		*Input Data: 
			
		*Expected Outcome: 
			- The notification message is updated accordingly
			- The notification become unread (different layout)*/ 
		intraNot.acceptRqConnection(username3);
		intraNot.checkReadNotification(statusSendRq,username3);

 	}

	/**
	*<li> Case ID:125119.</li>
	*<li> Test Case Name: Check [Mark as read] action.</li>
	*<li> Pre-Condition: The user has new unread notification</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckMarkAsReadAction() {
		info("Test 3: Check [Mark as read] action");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login
			- Click the notifications icons
		*Input Data: 
			
		*Expected Outcome: 
			- The notifications list is revealed
			- [Mark as read] is displayed at the top of the notification popover.*/
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
		
		info("User B login");
		magAc.signIn(username2, password);
		
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		
		info("[Mark as read] is displayed at the top of the notification popover");
		waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_MARK_ALL_AS_READ);
		/*Step number: 2
		*Step Name: Step 2 : Mark all as read
		*Step Description: 
			- Click [Mark as read]
		*Input Data: 
			
		*Expected Outcome: 
			- All unread notifications are changed to read*/
		String statusSendRq=notiIntranetData.getContentByArrayTypeRandom(2);
        intraNot.markAllAsRead();
       
        info("All unread notifications are changed to read");
        intraNot.checkReadNotification(statusSendRq,username1);
        
		/*Step number: 3
		*Step Name: Step 3 : Check View All page
		*Step Description: 
			- Go to View All
		*Input Data: 
			
		*Expected Outcome: 
			- All unread notifications have been changed to read*/ 
       intraNot.goToAllNotification();
     
       info("All unread notifications have been changed to read");
       intraNot.checkReadNotification(statusSendRq,username1);

 	}

	/**
	*<li> Case ID:125120.</li>
	*<li> Test Case Name: Remove notifications from the list.</li>
	*<li> Pre-Condition: The user has several notifications, read and unread</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_RemoveNotificationsFromTheList() {
		info("Test 4: Remove notifications from the list");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login
			- Click the notifications icon
		*Input Data: 
			
		*Expected Outcome: 
			- The notifications list is revealed
			- For each notification, a cross icon is available on the right top corner*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		
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
		hpAct.likeActivity(activity);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Add comment to Comment list");
		comments.add(comment);
		
		info("User C login");
		magAc.signIn(username3, password);
		
		info("User C sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		String statusSendRq=notiIntranetData.getContentByArrayTypeRandom(2);
		String statusLikeAc=notiIntranetData.getContentByArrayTypeRandom(6);
		String statusCommAc=notiIntranetData.getNotiContent(0);
		
		info("Log in with User A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.checkStatus(statusSendRq,username3);
		intraNot.checkStatus(statusCommAc,username2);
		intraNot.checkStatus(statusLikeAc,username2);
		
		Utils.pause(3000);
		info("Click 1 notification area of a like notification");
		intraNot.goToDetailLikeNotification(username2,true);
		waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_DETAIL_ACTIVITY_DES.replace("$des",activity));

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Click the cross icon on read notification
		*Input Data: 
			
		*Expected Outcome: 
			- The read notification is removed from the list*/
		info("The read notification is removed from the list");
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.removeNotificationByIndex(3);
		intraNot.checkNotPresentStatus(statusLikeAc,username2);

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click View All button at the bottom of the list
		*Input Data: 
			
		*Expected Outcome: 
			- The read notification is displayed in the view all page*/
		info("The read notification is displayed in the view all page");
		intraNot.goToAllNotification();
		intraNot.checkStatus(statusLikeAc,username2);

		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- Click the notification icon
			- Click the cross icon on unread notification
		*Input Data: 
			
		*Expected Outcome: 
			- The unread notification is removed from the list*/
		info("The unread notification is removed from the list");
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.removeNotificationByIndex(2);
		intraNot.checkStatus(statusCommAc,username2);
		
		/*Step number: 5
		*Step Name: 
		*Step Description: 
			- Go back to the View All page (or refresh it)
		*Input Data: 
			
		*Expected Outcome: 
			- The unread notification is displayed in the View All page*/ 
		info("The unread notification is displayed in the View All page");
		intraNot.goToAllNotification();
		intraNot.checkStatus(statusCommAc,username2);
 	}

	/**
	*<li> Case ID:125121.</li>
	*<li> Test Case Name: Check order of the notifications.</li>
	*<li> Pre-Condition: A user has received several notifications (from the oldest to the newest)
	*1. A like notification2. A comment notification3. A connection request</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckOrderOfTheNotifications() {
		info("Test 5: Check order of the notifications");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login
			- Click the notification icon
			- Check the order of the notifications
		*Input Data: 
			
		*Expected Outcome: 
			- The list is revealed 
			- The notifications are ordered from the newest at the top to the latest at the bottom (connection request, comment, like)*/ 
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		
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
		hpAct.likeActivity(activity);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Add comment to Comment list");
		comments.add(comment);
		
		info("User C login");
		magAc.signIn(username3, password);
		
		info("User C sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		String statusSendRq=notiIntranetData.getContentByArrayTypeRandom(2);
		String statusLikeAc=notiIntranetData.getContentByArrayTypeRandom(6);
		String statusCommAc=notiIntranetData.getNotiContent(0);
		
		info("Log in with User A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("The notifications are ordered from the newest at the top to the latest at the bottom (connection request, comment, like)");
		navTool.goToIntranetNotification();
		intraNot.checkOrderNotifications(1,statusSendRq,username3);
		intraNot.checkOrderNotifications(2,statusCommAc,username2);
		intraNot.checkOrderNotifications(3,statusLikeAc,username2);
		
		
 	}

	/**
	*<li> Case ID:125122.</li>
	*<li> Test Case Name: Notifications are pushed instantaneously.</li>
	*<li> Pre-Condition: - The user A doesn't have any new notifications while starting the test
	- The test is performed on latest version of FF, Chrome or IE 10/11</li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test06_NotificationsArePushedInstantaneously() throws AWTException {
		info("Test 6: Notifications are pushed instantaneously");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with user A
			- Go to wiki application
		*Input Data: 
			
		*Expected Outcome: 
			- User A is doing some actions on wiki*/
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
		
		info("UserA created a new wiki page");
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

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Login with User B on a different browser
			- Go to Connections or Who's Online and request a connection with User A
		*Input Data: 
			
		*Expected Outcome: 
			- The number of notifications of User A is updated as soon 
			as the connection request notification is received*/
		info("Log in with userB and connect to userA");
		initNewDriver();
		newDriver.get(baseUrl);
        ManageLogInOut magAc2 = new ManageLogInOut(newDriver);
        HomePagePlatform hp2 = new HomePagePlatform(newDriver);
        ConnectionsManagement connMag2 = new ConnectionsManagement(newDriver);
        ActivityStream hpAct2 = new ActivityStream(newDriver);
        
        info("User B login on drifferent browser");
		magAc2.signIn(username2, password);
		
		info("User B sent a connection request to userA");
		Utils.pause(3000);
		hp2.goToConnections();
		connMag2.connectToAUser(username1);
		
		info("Switch to user1 and check intranet notification");
		isDriver = true;
		info("The number of notifications of User A is updated as soon as the connection request notification is received");
		intraNot.checkBadgeNoti(1);
        
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- With User A, click on the notification icon
			- Browse the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- The connection request notifications is displayed*/
		info("The connection request notifications is displayed");
		String statusSendRq=notiIntranetData.getContentByArrayTypeRandom(2);
		navTool.goToIntranetNotification();
		intraNot.checkStatus(statusSendRq,username2);
		
		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- Come back to User B while User A is browsing the list
			- Go to the actiivty stream and mention User A
		*Input Data: 
			
		*Expected Outcome: 
			- While User A is browsing the notification list, 
			the number of notification and the content of the list is updated 
			as soon as a new notifications is received. The new notification is 
			displayed at the top of the list.*/ 
        String text = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String statusMention=notiIntranetData.getContentByArrayTypeRandom(7);
		info("Go to the actiivty stream and mention User A");
		isDriver = false;
        hp2.goToHomePage();
        hpAct2.mentionUserActivity(username1, text);
        newDriver.close();
        
        info("Check badge and content notification of User A");
        isDriver = true;
        intraNot.checkBadgeNoti(1);
        navTool.goToIntranetNotification();
		intraNot.checkStatus(statusMention,username2);
		
 	}

	/**
	*<li> Case ID:125124.</li>
	*<li> Test Case Name: Check notifications after logout / login.</li>
	*<li> Pre-Condition: - Initially, the User A has 3 unread notifications including 2 new notifications 
	*displayed in the blue badge
	- When starting the tests, the User A is connected to the platform</li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test07_CheckNotificationsAfterLogoutLogin() throws AWTException {
		info("Test 7: Check notifications after logout / login");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Logout / Login User A
			- Check badge and notification list
		*Input Data: 
			
		*Expected Outcome: 
			- The number in the badge is still 2
			- User A has still 3 unread notifications*/
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		
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
		navTool.goToIntranetNotification();
		intraNot.acceptRqConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		info("Add a comment to UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Add comment to Comment list");
		comments.add(comment);
		
		info("User A login");
		magAc.signIn(username1, password);
		
		String statusLikeAc=notiIntranetData.getContentByArrayTypeRandom(6);
		String statusCommAc=notiIntranetData.getNotiContent(0);
		String statusMention=notiIntranetData.getContentByArrayTypeRandom(7);
		String textMention=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		Utils.pause(3000);
		
		info("View comment notification of User A");
		navTool.goToIntranetNotification();
		intraNot.checkStatus(statusCommAc,username2);
		
		info("User B login");
		magAc.signIn(username2, password);
		
		info("User B likes User A's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("User B mention User A in a activity");
		hpAct.mentionUserActivity(username1,textMention);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("The number in the badge is still 2");
		intraNot.checkBadgeNoti(2);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Logout User A
			- Login with User B 
			- Send 2 new notifications to User A (mention and connection request)
			- Login again with User A and check the badge and notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- The number in the badge is updated to 4
			- User A has 5 unread notifications*/ 
		info("User C login");
		magAc.signIn(username3, password);
		
		info("User C sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		info("User C mention to User A");
		String textMention1=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.mentionUserActivity(username1,textMention1);
		
		info("User A login");
		magAc.signIn(username1, password);
		
        Utils.pause(3000);
		
		info("The number in the badge is still 4");
		intraNot.checkBadgeNoti(4);
		
		info("User A has still 5 unread notifications");
		String statusSendRq=notiIntranetData.getContentByArrayTypeRandom(2);
        navTool.goToIntranetNotification();
        intraNot.checkUnreadNotification(statusLikeAc,username2);
		intraNot.checkUnreadNotification(statusMention,username2);
		intraNot.checkUnreadNotification(statusCommAc,username2);
		intraNot.checkUnreadNotification(statusSendRq,username3);
		intraNot.checkUnreadNotification(statusMention,username3);
		

 	}

	/**
	*<li> Case ID:125125.</li>
	*<li> Test Case Name: Check [View All] button.</li>
	*<li> Pre-Condition: The user has notifications (read or unread)</li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test08_CheckViewAllButton() throws AWTException {
		info("Test 8: Check [View All] button");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login
			- Click the notifications icon
			- Check the popover
		*Input Data: 
			
		*Expected Outcome: 
			- A [View All] button is displayed at the end of the popover*/
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
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		
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
		navTool.goToIntranetNotification();
		intraNot.acceptRqConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		info("Add a comment to UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Add comment to Comment list");
		comments.add(comment);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("View comment notification of User A");
		navTool.goToIntranetNotification();
		intraNot.goToDetailCommentNotification(activity, true);
		
		info("User B login");
		magAc.signIn(username2, password);
		
		info("User B likes User A's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("User B mention User A in a activity");
		String textMention=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.mentionUserActivity(username1,textMention);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Click [View All]
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirected to the page View All in order to see all notificaitons*/
		info("Open All Notification page");
		intraNot.goToAllNotification();

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click the notification icon
			- Remove all notifications from the list
		*Input Data: 
			
		*Expected Outcome: 
			- The [View All] button is still displayed at the end of the popover*/ 
		info("Remove all notifications from the list");
		navTool.goToIntranetNotification();
		for(int i=0;i<3;i++)
			intraNot.removeNotificationByIndex(1);
		
		info(" The [View All] button is still displayed at the end of the popover");
		waitForAndGetElement(intraNot.ELEMENT_VIEW_ALL);
		

 	}

	/**
	*<li> Case ID:125126.</li>
	*<li> Test Case Name: Check notifications list when there is no notification.</li>
	*<li> Pre-Condition: The notification list is empty 
	*(no notification received or all notifications are removed)</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckNotificationsListWhenThereIsNoNotification() {
		info("Test 9: Check notifications list when there is no notification");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login
			- Click the notifications icon
		*Input Data: 
			
		*Expected Outcome: 
			The list is revealed and is empty :
			- The link Mark as read is hidden 
			- The UI of the list must indicate there is no notification to display*/ 
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		
		navTool.goToIntranetNotification();
		info("The link Mark as read is hidden");
		waitForElementNotPresent(intraNot.ELEMENT_INTRANET_NOTIFICATION_MARK_ALL_AS_READ);
		info("The UI of the list must indicate there is no notification to display");
		waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_EMPTY_LIST);

 	}

	/**
	*<li> Case ID:125127.</li>
	*<li> Test Case Name: Check display of [Mark as read].</li>
	*<li> Pre-Condition: The user has unread notifications</li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test10_CheckDisplayOfMarkAsRead() throws AWTException {
		info("Test 10 Check display of [Mark as read]");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login
			- Click the notifications icon
		*Input Data: 
			
		*Expected Outcome: 
			- [Mark as read] is displayed at the top of the popover*/
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
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		
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
		navTool.goToIntranetNotification();
		intraNot.acceptRqConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		info("Add a comment to UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Add comment to Comment list");
		comments.add(comment);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Click [Mark as read] or read each unread notifications one by one
			- Check the popover
		*Input Data: 
			
		*Expected Outcome: 
			- There is no new notification to read, [Mark as read] is hidden*/
		String statusLikeAc=notiIntranetData.getContentByArrayTypeRandom(6);
		String statusCommAc=notiIntranetData.getNotiContent(0);
		navTool.goToIntranetNotification();
		info("Click [Mark as read]");
		intraNot.markAllAsRead();
		
		info("There is no new notification to read");
		intraNot.checkReadNotification(statusLikeAc,username2);
		intraNot.checkReadNotification(statusCommAc,username2);
		
		info("[Mark as read] is hidden");
		waitForElementNotPresent(intraNot.ELEMENT_INTRANET_NOTIFICATION_MARK_ALL_AS_READ);

 	}

	/**
	*<li> Case ID:125130.</li>
	*<li> Test Case Name: Check notifications list out-of-the-box.</li>
	*<li> Pre-Condition: The user logs for the 1st time to the platform</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckNotificationsListOutofthebox() {
		info("Test 11 Check notifications list out-of-the-box");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login
			- Click the notifications icon
			- Check the popover content
		*Input Data: 
			
		*Expected Outcome: 
			- The button View All is not displayed
			- [Mark as read] is hidden
			- The UI indicates there is no notification to display*/ 
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		
		navTool.goToIntranetNotification();
		info("The link Mark as read is hidden");
		waitForElementNotPresent(intraNot.ELEMENT_INTRANET_NOTIFICATION_MARK_ALL_AS_READ);
		
		info("The UI of the list must indicate there is no notification to display");
		waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_EMPTY_LIST);
		
	    info("The button View All is not displayed");
	    waitForElementNotPresent(intraNot.ELEMENT_VIEW_ALL);

 	}

	/**
	*<li> Case ID:125131.</li>
	*<li> Test Case Name: Configure Number of notifications in the list.</li>
	*<li> Pre-Condition: - In gatein/conf, rename exo
	-sample.properties to exo.properties
	- Update the property : exo.notifications.maxitems=4</li>
	*<li> Post-Condition: </li>
	*CANOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test12_ConfigureNumberOfNotificationsInTheList() {
		info("Test 12 Configure Number of notifications in the list");
		/*Step Number: 1
		*Step Name: Step 1 : Start the server
		*Step Description: 
			- Start eXo Platform with the new property
			- Login
		*Input Data: 
			
		*Expected Outcome: 
			- the homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Generate 5 notifications
		*Step Description: 
			- Generate 5 notifications for the current user logged in
		*Input Data: 
			
		*Expected Outcome: 
			- The number in the badge is 5*/

		/*Step number: 3
		*Step Name: Step 3 : Check nb of item in the list
		*Step Description: 
			- Check the number of item in the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- Only the last 4 items are displayed in the notification list*/ 

 	}

	/**
	*<li> Case ID:125132.</li>
	*<li> Test Case Name: Check layout with 1 notification kept and after refusing.</li>
	*<li> Pre-Condition: - The user has received already several notification 
	*but kept ONLY1 notification is the list
	- The notification kept in the list by the user includes an action [Refuse] 
	(connection request, space join, space request).</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckLayoutWith1NotificationKeptAndAfterRefusing() {
		info("Test 13 Check layout with 1 notification kept and after refusing");
		/*Step Number: 1
		*Step Name: Step 1 : Click Refuse
		*Step Description: 
			- Login
			- Open the notifications list
			- Click [Refuse]
		*Input Data: 
			
		*Expected Outcome: 
			- The notification is hidden
			- The layout of the popover/list is updated accordingly : 
			* The label "No Notifications" is displayed* 
			* The link [Mark all as read] is not displayed anymore* 
			* [View All] is displayed*/ 
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		
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
		navTool.goToIntranetNotification();
		intraNot.acceptRqConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		info("Add a comment to UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Add comment to Comment list");
		comments.add(comment);
		
		info("User C login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User C sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("User A removed some notificaitons form User B");
		navTool.goToIntranetNotification();
		intraNot.removeNotificationByIndex(3);
		intraNot.removeNotificationByIndex(2);
		
		info("User A refuse connection with User C");
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.refuseRqConnection(username1);
		
		info("The link Mark as read is hidden");
		waitForElementNotPresent(intraNot.ELEMENT_INTRANET_NOTIFICATION_MARK_ALL_AS_READ);
		
		info("The UI of the list must indicate there is no notification to display");
		waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_EMPTY_LIST);
		
	    info("The button View All is displayed");
	    waitForAndGetElement(intraNot.ELEMENT_VIEW_ALL);
		
		

 	}

	/**
	*<li> Case ID:125133.</li>
	*<li> Test Case Name: Check layout with 1 notification (since first connection) and after refusing.</li>
	*<li> Pre-Condition: - The user has received only 1 notification is the list since his first connection
	- The notification kept in the list by the user includes an action [Refuse] (connection request, space join, space request).</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_CheckLayoutWith1NotificationSinceFirstConnectionAndAfterRefusing() {
		info("Test 14 Check layout with 1 notification (since first connection) and after refusing");
		/*Step Number: 1
		*Step Name: Step 1 : Click Refuse
		*Step Description: 
			- Login
			- Open the notifications list
			- Click [Refuse]
		*Input Data: 
			
		*Expected Outcome: 
			- The notification is hidden
			- The layout of the popover/list is updated accordingly : 
			* The label "No Notifications" is displayed*
			*  The link [Mark all as read] is not displayed anymore* 
			*  [View All] is not displayed*/ 
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
		
		info("User B login");
		magAc.signIn(username2, password);
		
		info("User B refuse connection with User A");
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.refuseRqConnection(username1);
		
		info("The link Mark as read is hidden");
		navTool.goToIntranetNotification();
		waitForElementNotPresent(intraNot.ELEMENT_INTRANET_NOTIFICATION_MARK_ALL_AS_READ);
		
		info("The UI of the list must indicate there is no notification to display");
		waitForAndGetElement(intraNot.ELEMENT_INTRANET_NOTIFICATION_EMPTY_LIST);
		
	    info("The button View All is not displayed");
	    waitForElementNotPresent(intraNot.ELEMENT_VIEW_ALL);

 	}}