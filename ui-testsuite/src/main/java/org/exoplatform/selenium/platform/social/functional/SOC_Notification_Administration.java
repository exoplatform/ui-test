package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notiMode;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


	public class SOC_Notification_Administration extends SOC_TestConfig1{

	/**
	*<li> Case ID:117480.</li>
	*<li> Test Case Name: Check global settings of the second section "Notification Types".</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckGlobalSettingsOfTheSecondSectionNotificationTypes() {
		info("Test 1: Check global settings of the second section Notification Types");
		resetNotifitcaiton=true;
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		
		
		/*Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description: 
			- Login as admin 
			- Move mouse over:Administration > Portal > Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- Notifications Administration page is displayed*/
		info("Go to Notification Administration");
		navTool.goToAdminNotifications();
		info("Notifications Administration page is displayed");
        waitForAndGetElement(notiAdmin.ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE);
        
		/*Step number: 2
		*Step Name: Step 2: Change settings
		*Step Description: 
			- Disable and enable some notification types.For example: 
			disable "Someone joins the social intranet" and enable "Someone @mention me"
		*Input Data: 
			
		*Expected Outcome: 
			- The change is saved.*/
        info("Change setting.Disable New User intranet");
        notiAdmin.disableNotification(notificationType.NewUser_intranet);
        notiAdmin.disableNotification(notificationType.NewUser_email);
        info("Change setting.Disable Mention intranet");
        notiAdmin.disableNotification(notificationType.AS_Mention_intranet);
        notiAdmin.disableNotification(notificationType.AS_Mention_email);

		/*Step number: 3
		*Step Name: Step 3: Check settings by other user
		*Step Description: 
			- Login as other user
			- Select My Connections from the user menu to go to Notification Settings page.
			- Check settings in Notification Admin
		*Input Data: 
			
		*Expected Outcome: 
			- The change in Notification admin at step 2 is applied for other user
			- Notification type "Someone joins the social intranet" is not displayed, 
			"Someone @mention me" is displayed*/ 
        magAc.signIn(username,password);
        info("Go to My notification");
        navTool.goToMyNotifications();
        info("Verify that new user and mention plugin is not displayed");
        waitForElementNotPresent(myNoti.ELEMENT_MY_NOTIFICATION_NEW_USER_PLUGIN);
        waitForElementNotPresent(myNoti.ELEMENT_MY_NOTIFICATION_MENTION_PLUGIN);
        
        magAc.signIn(USER_ROOT,PASS_ROOT);
        info("Go to Admin notification");
        navTool.goToAdminNotifications();
        info("Change setting.Enable New User intranet");
        notiAdmin.enableNotification(notificationType.NewUser_intranet);
        notiAdmin.enableNotification(notificationType.NewUser_email);
        info("Change setting.Enable Mention intranet");
        notiAdmin.enableNotification(notificationType.AS_Mention_intranet);
        notiAdmin.enableNotification(notificationType.AS_Mention_email);
        
        magAc.signIn(username,password);
        info("Go to My notification");
        navTool.goToMyNotifications();
        info("Verify that New user and Mention plugin is shown");
        waitForAndGetElement(myNoti.ELEMENT_MY_NOTIFICATION_NEW_USER_PLUGIN);
        waitForAndGetElement(myNoti.ELEMENT_MY_NOTIFICATION_MENTION_PLUGIN,2000,1);
        
        
 	}

	/**
	*<li> Case ID:117512.</li>
	*<li> Test Case Name: Check Notification types table.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckNotificationTypesTable() {
		info("Test 2: Check Notification types table");
		/*Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description: 
			- Login as admin.
			- Move mouse over:Administration > Portal > Notifications.
		*Input Data: 
			
		*Expected Outcome: 
			- Notifications Administration page is displayed*/
		info("Go to Notification Administration");
		navTool.goToAdminNotifications();
		info("Notifications Administration page is displayed");
        waitForAndGetElement(notiAdmin.ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE);

		/*Step number: 2
		*Step Name: Step 2: Check Notification Types table
		*Step Description: 
			- Check Notification Types table
		*Input Data: 
			
		*Expected Outcome: 
			-A table list all currently loaded notification plugins. Columns are : 
			+ Notifications: the name of the notificaiton plugin 
			+ Title: the title of the notificaiton that will be shown in the notification's settings 
			and in the email,  
			+ Enable: the actions possible on this plugin*/ 
        info("Verify that Notificaiton column is shown");
        waitForAndGetElement(notiAdmin.ELEMENT_NOTIFICATION_GRID_TITLE);
        info("Verify that Title column is shown");
        waitForAndGetElement(notiAdmin.ELEMENT_TITLE_NOTIFICATION_GRID);
        info("Verify that Enable column is shown");
        waitForAndGetElement(notiAdmin.ELEMENT_ENABLE_NOTIFICATION_GRID);

 	}

	/**
	*<li> Case ID:117552.</li>
	*<li> Test Case Name: Check [Address] field when input invalid value.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckAddressFieldWhenInputInvalidValue() {
		info("Test 3: Check [Address] field when input invalid value");
		/*Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description: 
			- Login as admin.
			- Move mouse over:Administration > Portal >Notifications.
		*Input Data: 
			
		*Expected Outcome: 
			- The Notifications Administration page is displayed.*/
		info("Go to Notification Administration");
		navTool.goToAdminNotifications();
		info("Notifications Administration page is displayed");
        waitForAndGetElement(notiAdmin.ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE);

		/*Step number: 2
		*Step Name: Step 2: Check [Address] field
		*Step Description: 
			- Check [Address] field when input invalid value
		*Input Data: 
			
		*Expected Outcome: 
			- Show warning message about invalid email:
			 "Cannot update the sender information: empty value or the format is not correct."*/ 
        String invalid_emal=txData.getContentByArrayTypeRandom(1);
        notiAdmin.changeNotificationSender("",invalid_emal);
        notiAdmin.verifyFailChangeNotificationSender();

 	}

	/**
	*<li> Case ID:117563.</li>
	*<li> Test Case Name: Uncheck one notification type.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_UncheckOneNotificationType() {
		info("Test 4: Uncheck one notification type");
		resetNotifitcaiton=true;
		String username = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email = username+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username, password, email, username, username);
		/*Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description: 
			- Login as admin.
			- Move mouse over:Administration > Portal >Notifications.
		*Input Data: 
			
		*Expected Outcome: 
			- The Notifications Administration page is displayed.*/
		
		info("Go to Notification Administration");
		navTool.goToAdminNotifications();
		info("Notifications Administration page is displayed");
        waitForAndGetElement(notiAdmin.ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE);

		/*Step number: 2
		*Step Name: Step 2: Uncheck one notification type.
		*Step Description: 
			- In Disable column, s
			elect Edit icon on any item in Notification Types table, 
			then untick two checkboxes: Email Notifications and Intranet Notifications.
			- Click Save button.
		*Input Data: 
			
		*Expected Outcome: 
			- One text "No notifications" is shown. 
			- No new notification of this type is sent anymore.*/
        
        info("Change setting.Disable Activity comment intranet");
        notiAdmin.disableNotification(notificationType.AS_Comment_email);
        notiAdmin.disableNotification(notificationType.AS_Comment_intranet);
        info("Change setting.Disable Space Join request intranet");
        notiAdmin.disableNotification(notificationType.Space_Join_email);
        notiAdmin.disableNotification(notificationType.Space_Join_intranet);
        
        notiAdmin.veriftyNotificationTypeDisable(notiMode.AS_Comment);
        notiAdmin.veriftyNotificationTypeDisable(notiMode.Space_Join);

		/*Step number: 3
		*Step Name: Step 3: Check the Notification Settings
		*Step Description: 
			- Log in as a user.
			- Select My Notifications from the User menu to open the Notification Settings page.
		*Input Data: 
			
		*Expected Outcome: 
			- The correspondingrow disappears from all users'Notification Settings screen.*/ 
        magAc.signIn(username,password);
        info("Go to My notification");
        navTool.goToMyNotifications();
        info("Verify that new user and mention plugin is not displayed");
        waitForElementNotPresent(myNoti.ELEMENT_MY_NOTIFICATION_SPACE_REQJOIN_PLUGIN);
        waitForElementNotPresent(myNoti.ELEMENT_MY_NOTIFICATION_ACTIVITY_COMMENT_PLUGIN);
        
        
        magAc.signIn(USER_ROOT,PASS_ROOT);
        info("Go to Admin notification");
        navTool.goToAdminNotifications();
        info("Change setting.Enable Space join intranet");
        notiAdmin.enableNotification(notificationType.AS_Comment_email);
        notiAdmin.enableNotification(notificationType.AS_Comment_intranet);
        info("Change setting.Enable Activity comment intranet");
        notiAdmin.enableNotification(notificationType.Space_Join_email);
        notiAdmin.enableNotification(notificationType.Space_Join_intranet);
        
        magAc.signIn(username,password);
        info("Go to My notification");
        navTool.goToMyNotifications();
        info("Verify that Space join and Activity comment plugin is shown");
        waitForAndGetElement(myNoti.ELEMENT_MY_NOTIFICATION_SPACE_REQJOIN_PLUGIN);
        waitForAndGetElement(myNoti.ELEMENT_MY_NOTIFICATION_ACTIVITY_COMMENT_PLUGIN);

 	}

	/**
	*<li> Case ID:117577.</li>
	*<li> Test Case Name: Check [Save] button of Notification Sender section.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckSaveButtonOfNotificationSenderSection() {
		info("Test 5: Check [Save] button of Notification Sender section");
		resetNotifitcaiton=true;
		/*Step Number: 1
		*Step Name: Step 1: Access notification Administration
		*Step Description: 
			- Login as admin 
			- Move mouse over:Administration > Portal > Notifications.
		*Input Data: 
			
		*Expected Outcome: 
			- The Notifications Administration page is displayed.*/
		info("Go to Notification Administration");
		navTool.goToAdminNotifications();
		info("Notifications Administration page is displayed");
        waitForAndGetElement(notiAdmin.ELEMENT_TITLE_ADMIN_NOTIFICATIONS_PAGE);
        
		/*Step number: 2
		*Step Name: Step 2: Check [Save] button
		*Step Description: 
			- Input valid value into [Name] and [Address] field: Name is "Test" and Address is: "test@exoplatform.com" for ex
			- Click [Save] button.
		*Input Data: 
			
		*Expected Outcome: 
			- A feedback message is displayed:Notifications will now be sent to your users from "$SENDER_NAME"<$SENDER_ADDRESS>where $SENDER_NAME = Test and <$SENDER_ADDRESS> = test@exoplatform.com*/ 
        String name ="Test";
        String address="test@exoplatform.com";
        notiAdmin.changeNotificationSender(name,address);
        notiAdmin.verifySuccessChangeNotificationSender(name,address);
 	}}