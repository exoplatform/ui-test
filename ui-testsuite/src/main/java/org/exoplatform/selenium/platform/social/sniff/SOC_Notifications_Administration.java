package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class SOC_Notifications_Administration extends SOC_TestConfig_3{

	/**
	*<li> Case ID:121951.</li>
	*<li> Test Case Name: Notification Administration.</li>
	*/
	@Test
	public  void test01_NotificationAdministration() {
		info("Test 1: Notification Administration");
		/*Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description: 
			- Login as admin
			- From top navigation, click Administration/Portal/Email Notification
		*Input Data: 

		*Expected Outcome: 
			- Email Notifications Administration page appears as attachment with
			1. Notification types with 3 columns:  
			   + [Notifications] list all activities which support notification email 
			   + [Title] Title of activities which is shown in [Notification setting] table 
			   + [Enable] let users enable/disable notification option*/ 
		navTool.goToAdminNotifications();
		waitForAndGetElement(emailNotif.ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE,2000,1);
		waitForAndGetElement(emailNotif.ELEMENT_NOTIFICATION_GRID_TITLE,2000,1);
		waitForAndGetElement(emailNotif.ELEMENT_TITLE_NOTIFICATION_GRID,2000,1);
		waitForAndGetElement(emailNotif.ELEMENT_ENABLE_NOTIFICATION_GRID,2000,1);
 	}

	/**
	*<li> Case ID:121962.</li>
	*<li> Test Case Name: Disable an Email Notification type.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - The New User notification is set enabled for Email Notifications
	- User A has enabled the notification New User</li>
	*/
	@Test
	public  void test02_DisableAnEmailNotificationType() {
		info("Test 2: Disable an Email Notification type");
		/*Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description: 
			- Login as admin
			- From top navigation, click Administration/Portal/Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- Notifications Administration page is displayed*/
		navTool.goToAdminNotifications();
		waitForAndGetElement(emailNotif.ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE,2000,1);
		/*Step number: 2
		*Step Name: Step 2: Disable one option
		*Step Description: 
			- Select the notification[New User] and click the Edit icon
			- Untick the checkbox "Email Notifications
			- Save
			- In the user navigation, go to [Notifications]
		*Input Data: 
			
		*Expected Outcome: 
			The option is not shown in Notification Setting anymore. 
			("Someone joins the social intranet with email notification option" is not listed)*/
		emailNotif.disableNotification(notificationType.NewUser_email);
		navTool.goToMyNotifications();
		waitForElementNotPresent(myNotifPage.ELEMENT_NEWUSER_ICON_EMAIL_NOTIFICATION,2000,1);
		
		/*Step number: 3
		*Step Name: Step 3: Test the disable option
		*Step Description: 
			- With an admin account, go to Administration > Users > Add Users
			- Add a new user
			- Check mailbox of User A
		*Input Data: 
			
		*Expected Outcome: 
			- No email notifications is sent*/ 
		/*int index = userInfoData.getRandomIndexByType(3);
		String username = userInfoData.newUserName.get(index);
		String firstname = userInfoData.newFirstName.get(index);
		String lastname = userInfoData.newLastName.get(index);
		String password = userInfoData.newPassword.get(index);
		String email = userInfoData.newEmail.get(index);*/
		
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		String fullName=username+" "+username;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		
		addUserPage.goToMail(EMAIL_ADDRESS2, EMAIL_PASS);
		Utils.pause(20000);
		checkEmailNotification(fullName+" has joined eXo",false,true);
        switchToParentWindow();
        
        info("Reset changed data");
        navTool.goToAdminNotifications();
		emailNotif.enableNotification(notificationType.NewUser_email);
 	}

	/**
	*<li> Case ID:121963.</li>
	*<li> Test Case Name: Enable an Email Notification type.</li>
	*<li> Pre-Condition: - User A and user B are not connected
	- The Connection Request notification is disabled for the User A (and was set to daily, sent email right now before disabling it).</li>
	*/
	@Test
	public  void test04_EnableAnEmailNotificationType() {
		info("Test 3: Enable an Email Notification type");
		/*Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description: 
			- Login as admin
			- From top navigation, click Administration/Portal/Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- Notifications Administration page is displayed*/
		navTool.goToAdminNotifications();
		waitForAndGetElement(emailNotif.ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE,2000,1);
		/*Step number: 2
		*Step Name: Step 3: Enable option again
		*Step Description: 
			- In the Notifications row Connection Request, click the Edit Icon
			- Tick the option Email Notifications
			- Save
			- Go to Notification setting page again
		*Input Data: 
			
		*Expected Outcome: 
			The option is shown in the user settings page.
			Connection request with email notification option is shown*/
		emailNotif.enableNotification(notificationType.ConnectionRequest_email);
		navTool.goToMyNotifications();
		waitForAndGetElement(myNotifPage.ELEMENT_CONNECTION_REQUEST_ICON_EMAIL_NOTIFICATION,2000,1);
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Login with User B
			- Send a connection request to User A
			- Login with User A
		*Input Data: 
			
		*Expected Outcome: 
			- An email notification is sent to User A*/ 
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		String fullName=username+" "+username;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		magAc.signIn(username,password);
		
		hp.goToConnections();
		connMag.connectToAUser(USER_ROOT,DATA_NAME_ROOT);
		
		magAc.signIn(USER_ROOT,PASS_ROOT);
		
		int index= notiIntranetData.getRandomIndexByType(2);
		String des= notiIntranetData.newContent.get(index);
		
		
		navTool.goToNotificationList();
		info("Verify that the notification is listed in the list");
		String actualText=waitForAndGetElement(navTool.ELEMENT_NOTIFICATION_LIST_CONNECT_USER_STATUS).getText().trim();
		String expectedText = fullName+" "+des;
		click(navTool.ELEMENT_NOTIFICATION_REMOVE_ICON);
		
		if(expectedText.contentEquals(actualText)==true)
			assert true;
		else assert false;
		
	
 	}

	/**
	*<li> Case ID:122975.</li>
	*<li> Test Case Name: Enable an Intranet Notification type.</li>
	*<li> Pre-Condition: - User B is manager of Space 1
	- User A is not member of Space 1
	- The Space Invitation notification is disabled for the User A (and was set to "See on My Intranet" initially).</li>
	*/
	@Test
	public  void test05_EnableAnIntranetNotificationType() {
		info("Test 4: Enable an Intranet Notification type");
		String spaceName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description: 
			- Login as admin
			- From top navigation, click Administration/Portal/Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- Notifications Administration page is displayed*/
		navTool.goToAdminNotifications();
		waitForAndGetElement(emailNotif.ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE,2000,1);
		
		/*Step number: 2
		*Step Name: Step 3: Enable option again
		*Step Description: 
			- In the Notifications row Space Invitation, click the Edit Icon
			- Tick the option Intranet Notification Notifications
			- Save
			- Go to Notification setting page again
		*Input Data: 
			
		*Expected Outcome: 
			The option is shown in the user settings page.
			"I receive a Space invitation" with intranet nofication option is shown*/
		emailNotif.enableNotification(notificationType.Space_Invitation_intranet);
		navTool.goToMyNotifications();
		waitForAndGetElement(myNotifPage.ELEMENT_SPACE_INVITATION_INTRANET_NOTIFICATION_ICON,2000,1);
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Login with User B
			- Invite User A to join Space 1
			- Login with User A and check the notification list from the top navigation
		*Input Data: 
			
		*Expected Outcome: 
			- An intranet notification is displayed in the list*/ 
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		magAc.signIn(username,password);
		hp.goToMySpaces();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,"");
		
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(USER_ROOT,true,DATA_NAME_ROOT);
		
		magAc.signIn(USER_ROOT,PASS_ROOT);
		
		int index= notiIntranetData.getRandomIndexByType(3);
		String des= notiIntranetData.newContent.get(index);
		
		navTool.goToNotificationList();
		info("Verify that the notification is listed in the list");
		String text=waitForAndGetElement(navTool.ELEMENT_NOTIFICATION_LIST_INVITATION_SPACE_STATUS.replace("${space}",spaceName)).getText();
		click(navTool.ELEMENT_NOTIFICATION_REMOVE_ICON);
		info("text:"+text);
		assert text.contains(des+" "+spaceName+" space.");
		
 	}

	/**
	*<li> Case ID:122976.</li>
	*<li> Test Case Name: Disable an Intranet Notification type.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: - User A and User B are connected
	- User A has enabled Intranet Notification for the Comment</li>
	*<li> Done with OSs and browsers : </li>
	*/
	@Test
	public  void test03_DisableAnIntranetNotificationType() {
		info("Test 03: Disable an Intranet Notification type");
		

		/*Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description: 
			- Login as admin
			- From top navigation, click Administration/Portal/Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- Notifications Administration page is displayed*/
		navTool.goToAdminNotifications();
		waitForAndGetElement(emailNotif.ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE,2000,1);

		/*Step number: 2
		*Step Name: Step 2: Disable one option
		*Step Description: 
			- Select the notification[Comment] and click the Edit icon
			- Untick the checkbox "Intranet Notifications
			- Save
			- In the user navigation, go to [Notifications]
		*Input Data: 
		
		*Expected Outcome: 
			The option is not shown in Notification Setting anymore. 
			("Someone comments on one of my activity" is not listed)*/

		emailNotif.disableNotification(notificationType.AS_Comment_intranet);
		navTool.goToMyNotifications();
		waitForElementNotPresent(myNotifPage.ELEMENT_ACTIVITY_COMMENT_ICON_INTRANET_NOTIFICATION,2000,1);
		
		/*Step number: 3
		*Step Name: Step 3: Test the disable option
		*Step Description: 
			- With User A post an activity
			- With User B, comment on the activity of User A 
			- With User A, check notifications list from the top navigation
		*Input Data: 
			
		*Expected Outcome: 
			- No intranet notifications is displayed*/ 
		String text = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		int index= notiIntranetData.getRandomIndexByType(1);
		String des= notiIntranetData.newContent.get(index);
		
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		
		
		hp.goToConnections();
		connMag.connectToAUser(username);
		
		magAc.signIn(username,password);
		
		hp.goToConnections();
		connMag.acceptAConnection(USER_ROOT,DATA_NAME_ROOT);
		
		magAc.signIn(USER_ROOT,PASS_ROOT);
		
		hp.goToHomePage();
		hpAct.addActivity(text,"");
		
		magAc.signIn(username,password);
		hpAct.addComment(text,comment);
		
		magAc.signIn(USER_ROOT,PASS_ROOT);
		navTool.goToNotificationList();
		info("Verify that the notification isnot listed in the list");
		waitForTextNotPresent(navTool.ELEMENT_NOTIFICATION_LIST_COMMENT_ACTIVITY.replace("${user}",username).
				replace("${des}",des).replace("${act}",text),2000,1);
		
		 info("Reset changed data");
		 navTool.goToAdminNotifications();
		emailNotif.enableNotification(notificationType.AS_Comment_intranet);

 	}
	
}