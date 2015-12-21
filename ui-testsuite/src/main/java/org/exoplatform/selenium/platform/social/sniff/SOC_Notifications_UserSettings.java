package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


	public class SOC_Notifications_UserSettings extends SOC_TestConfig_3{
		
	// configure mail send every 2' in exo.properties: 
    // exo.notification.NotificationDailyJob.expression=*/2 * * * *
	/**
	*<li> Case ID:121950.</li>
	*<li> Test Case Name: Notification Settings.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_NotificationSettings() {
		info("Test 1: Notification Settings");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		
		/*Step Number: 1
		*Step Name: Step 1: Access notification settings
		*Step Description: 
			- Login
			- Click full name of user and select [Notifications] on the menu
		*Input Data: 
			
		*Expected Outcome: 
			Notification setting page is shown with 2 main parts1. 
			Toggle buttons for Email and Intranet Notifications, by default set to YES
			2. A table contains all notification settings for the user.The columns are :
			- Notify me when: contains title of the notifications and categories
			- How to get notifications : the notification information and an edit button*/
		info("goto My notification");
		navTool.goToMyNotifications();
		
		waitForAndGetElement(myNotifPage.ELEMENT_SWITCH_ONOFF_MAIL_ON,5000,1,2);
		waitForAndGetElement(myNotifPage.ELEMENT_SWITCH_ONOFF_WEB_ON,5000,1,2);
		waitForAndGetElement(myNotifPage.ELEMENT_COLUMN_NOTIFYME);
		waitForAndGetElement(myNotifPage.ELEMENT_COLUMN_HOWTO);
		
		/*Step number: 2
		*Step Name: Step 2 : check edit
		*Step Description: 
			- Click edit button of a notification type
		*Input Data: 
			
		*Expected Outcome: 
			The edition options are : 
			- a checkbox to indicate that the user wants to receive the email notification instantly named "Send me an email right away"
			- a combo boxwith one of the following values :+ Never : to not include this notification in any digest + Daily : to include notifications of this type in the daily digestemail + Weekly : to include the notifications of this type in the weekly digest email
			- a checkbox to indicate if the user wants to receive Intranet Notifications named "See on My Intranet"A button [Save] is displayed to save the selection*/ 
		click(myNotifPage.ELEMENT_EDIT_NEWUSER_ICON);
		Utils.pause(1000);
		waitForAndGetElement(myNotifPage.ELEMENT_EDIT_NEWUSER_LIST_DAILY);
		waitForAndGetElement(myNotifPage.ELEMENT_EDIT_NEWUSER_LIST_WEEKLY);
		waitForAndGetElement(myNotifPage.ELEMENT_EDIT_NEWUSER_LIST_NEVER);
		waitForAndGetElement(myNotifPage.ELEMENT_EDIT_NEWUSER_MAIL_CHECKBOX);
		waitForAndGetElement(myNotifPage.ELEMENT_EDIT_NEWUSER_WEB_CHECKBOX);
		waitForAndGetElement(myNotifPage.ELEMENT_EDIT_NEWUSER_SAVE_BTN);
 	}

	/**
	*<li> Case ID:121947.</li>
	*<li> Test Case Name: Update an Email notification option.</li>
	*<li> Pre-Condition: PLF server has mail configured </li>
	*<li> Post-Condition: </li>
	*/
	
	@Test
	public  void test04_UpdateAnEmailNotificationOption() {
		info("Test 04: Update an Email notification option");
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		
		info("Go to My Profile");
		navTool.goToMyProfile();
		myProfile.goToEditProfile();
		
		info("Change email address");
		Utils.pause(2000);
		myProfile.updateBasicInformation("", "", EMAIL_ADDRESS1);
		myProfile.saveCancelUpdateInfo(true);
		
		/*Step Number: 1
		*Step Name: Step 1: Email is sent when option is ticked
		*Step Description: 
			- Login with user A
			- Click username on the right of top navigation
			- Click Notifications
			- In column [How to get notifications], select 1 row (eg: Someone joins the social intranet) 
			- Click the edit button
			- Tick the check box "Send me an email right away"
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			The settings is saved*/
		info("setting send email when having new user");
		navTool.goToMyNotifications();
		myNotifPage.turnOnOffNotiEmail(true);
		myNotifPage.enableNotification(myNotiType.NewUser_email,"Daily");
		
		/*Step number: 2
		*Step Name: Step 2 : Check settings
		*Step Description: 
			- Login as an admin and create new user successfully
			- Check mailbox of User A
		*Input Data: 
			
		*Expected Outcome: 
			An email notification is sent*/
		magAc.signIn(USER_ROOT,PASS_ROOT);
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		info("add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username2, password, email2, username2, username2);
		
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=username2+" "+username2;
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(4);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"","1");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
        emailNot.closeChildBrowsers(parentWindow);
		
		/*Step number: 3
		*Step Name: Step 2: Email is not sent when option is not ticked
		*Step Description: 
			- Login with User A
			- Go to Notification Setting screen again
			- Un
			-tick option in step 1
			- With an admin account create another new user
		*Input Data: 
			
		*Expected Outcome: 
			No notification email is sent.*/ 
		info("setting not send mail");
		switchToParentWindow();
		magAc.signIn(username1, password);
		navTool.goToMyNotifications();
		myNotifPage.disableNotification(myNotiType.NewUser_email);
		
		magAc.signIn(USER_ROOT,PASS_ROOT);
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+ mailSuffixData.getMailSuffixRandom();
		info("add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username3, password, email3, username3, username3);
		
		String fullName3=username3+" "+username3;
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyNOTPresentTitleASEmailNoti(titleEmail,fullName3,"","1");
		emailNot.goToDetailEmailNoti(titleEmail, fullName3,"");
		emailNot.getAllChildWindows();
        emailNot.closeChildBrowsers(parentWindow);
		
		switchToParentWindow();
		info("restore data");
		navTool.goToMyNotifications();
		myNotifPage.confirmResetNotificationSetting();
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
 	}

	/**
	*<li> Case ID:121949.</li>
	*<li> Test Case Name: Switch OFF Email Notifications.</li>
	*<li> Pre-Condition: - User A has activated Email Notifications on all type.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_SwitchOFFEmailNotifications() {
		info("Test 05: Switch OFF Email Notifications");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();
		
		/*Step Number: 1
		*Step Name: Step 1: Access notification settings
		*Step Description: 
			- Login with user A
			- Click full name of user and select [Notifications] on the menu
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Settings page is appeared*/
		info("goto My notification page");
		navTool.goToMyNotifications();
		
		/*Step number: 2
		*Step Name: Step 2: Switch OFF Email Notifications
		*Step Description: 
			- Switch on NO the toggle "Get Email Notifications"
		*Input Data: 
			
		*Expected Outcome: 
			- The email notification settings is disabled is hidden. 
			- The user settings related to Email are disabled in the table*/
		info("turn off send email");
		myNotifPage.turnOnOffNotiEmail(false);
		waitForAndGetElement(myNotifPage.ELEMENT_MAIL_VIEWMODE_FALSE,2000,1,2);
		
		/*Step number: 3
		*Step Name: Step 3 : Check Email Notification disabled
		*Step Description: 
			- Generate an email notification activated previously by the user
		*Input Data: 
			
		*Expected Outcome: 
			- No email notifications are sent*/ 
		info("add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		
		info("Check email notification");

		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=username1+" "+username1;
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(4);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyNOTPresentTitleASEmailNoti(titleEmail,fullName,"","1");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
        emailNot.closeChildBrowsers(parentWindow);
		
		info("restore data");
		switchToParentWindow();
		info("restore data");
		navTool.goToMyNotifications();
		myNotifPage.confirmResetNotificationSetting();
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
 	}

	
	/**
	*<li> Case ID:122973.</li>
	*<li> Test Case Name: Switch OFF Intranet Notifications.</li>
	*<li> Pre-Condition: - User A has activated Intranet Notifications on all type.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_SwitchOFFIntranetNotifications() {
		info("Test 03: Switch OFF Intranet Notifications");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();
		
		/*Step Number: 1
		*Step Name: Step 1: Access notification settings
		*Step Description: 
			- Login with user A
			- Click full name of user and select [Notifications] on the menu
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Settings page is appeared*/
		info("goto My notification page");
		navTool.goToMyNotifications();
		
		/*Step number: 2
		*Step Name: Step 2: Switch OFF Intranet Notifications
		*Step Description: 
			- Switch on NO the toggle "Get Intranet Notifications"
		*Input Data: 
			
		*Expected Outcome: 
			- The intranet notifications settings are disabled.
			- The user settings related to Intranet are disabled in the table*/
		/*myNotifPage.turnOnOffNotiIntranet(true);
		myNotifPage.enableNotification(myNotiType.NewUser_intranet);*/
		
		info("turn off intranet notifications");
		myNotifPage.turnOnOffNotiIntranet(false);
		waitForAndGetElement(myNotifPage.ELEMENT_WEB_VIEWMODE_FALSE,3000,1,2);
		
		/*Step number: 3
		*Step Name: Step 3 : Check Intranet Notification disabled
		*Step Description: 
			- Generate an intranet notification activated previously by the user
		*Input Data: 
			
		*Expected Outcome: 
			- No intranet notifications are sent or displayed in the Intranet*/ 
		info("add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		Utils.pause(2000);
		
		info("Check intranet notification");
		waitForElementNotPresent(navTool.ELEMENT_TOOLBAR_NOTIFICATION_LIST);
		waitForElementNotPresent(navTool.ELEMENT_NOTIFICATION_LIST_USER.replace("${user}", username1));
		
		info("restore data");
		navTool.goToMyNotifications();
		myNotifPage.confirmResetNotificationSetting();
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.deleteUser(username1);
 	}

	/**
	*<li> Case ID:122974.</li>
	*<li> Test Case Name: Update an Intranet notification option.</li>
	*<li> Pre-Condition: PLF server has mail configured</li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test02_UpdateAnIntranetNotificationOption() throws AWTException {
		info("Test 02: Update an Intranet notification option");
		String mention = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String mention2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1, password);
		
		/*Step Number: 1
		*Step Name: Step 1: Email is sent when option is ticked
		*Step Description: 
			- Login with user A
			- Click username on the right of top navigation
			- Click Notifications
			- In column [How to get notifications], select 1 row (eg: Someone mentions you) 
			- Click the edit button
			- Tick the check box "See on My Intranet"
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			The settings is saved*/
		info("goto My notification page");
		navTool.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.AS_Mention_intranet);
		
		/*Step number: 2
		*Step Name: Step 2 : Check settings
		*Step Description: 
			- Login with User B and mention User A
			- Login with User A and check notification list in the top navigation
		*Input Data: 
			
		*Expected Outcome: 
			An intranet notification is displayed in the list*/
		info("login by another user");
		magAc.signIn(username2, password);
		
		info("add mention");
		hpAct.mentionUserActivity(username1,mention);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_MENTION_USER.replace("${content}", mention).replace("${user}",username1));
		
		magAc.signIn(username1, password);
		info("Check intranet notification");
		navTool.goToNotificationList();
		String text=waitForAndGetElement(navTool.ELEMENT_NOTIFICATION_LIST_USER.replace("${user}",username2+" "+username2)).getText();
		assert text.contains(username2+" "+username2+" has mentioned you.");
		click(navTool.ELEMENT_NOTIFICATION_REMOVE_ICON);
		
		/*Step number: 3
		*Step Name: Step 2: Email is not sent when option is not ticked
		*Step Description: 
			- Login with User A
			- Go to Notification Setting screen again
			- Un
			-tick option in step 1
			- With User B, mention again User A new user
		*Input Data: 
			
		*Expected Outcome: 
			No intranet notification is displayed in the notifications list.*/ 
		info("goto My notification page");
		navTool.goToMyNotifications();
		myNotifPage.disableNotification(myNotiType.AS_Mention_intranet);
		
		info("login by another user");
		magAc.signIn(username3, password);
		
		info("add mention");
		hpAct.mentionUserActivity(username1,mention2);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_MENTION_USER.replace("${content}", mention2).replace("${user}",username1));
		
		magAc.signIn(username1, password);
		info("Check intranet notification");
		navTool.goToNotificationList();
		waitForElementNotPresent(navTool.ELEMENT_NOTIFICATION_LIST_USER.replace("${user}",username3+" "+username3));
 	}
    
}