package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


	public class SOC_Notification_Intranet_User_Settings extends SOC_TestConfig2{

	/**
	*<li> Case ID:125160.</li>
	*<li> Test Case Name: Check Email Notifications User settings.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckEmailNotificationsUserSettings() {
		info("Test 1: Check Email Notifications User settings");
		/*Step Number: 1
		*Step Name: Step 1 : Go to My Notifications
		*Step Description: 
			- Login
			- Click the user menu and go to My Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- The user settings page is displayed*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();

		/*Step number: 2
		*Step Name: Step 2 : Check Email Notifications toggle
		*Step Description: 
			- Check the toggle related to email notifications
		*Input Data: 
			
		*Expected Outcome: 
			- The toggle is displayed at the top of the table
			- The toggle is labeled :Notify me by email
			- The toggle if ON by default*/
		
		myNoti.checkEmailNotifiToggle();
		
		/*Step number: 3
		*Step Name: Step 3 : Update Email Notifications toggle
		*Step Description: 
			- Switch OFF email notifications
		*Input Data: 
			
		*Expected Outcome: 
			- All settings related to Email Notifications are disabled (instant notification and digest) 
			and the settings of the user are remembere*/
        myNoti.turnOnOffNotiEmail(false);
        myNoti.veriftyAllEmailNotiDefaultDisable();
        
        info("the settings of the user are remembered");
        info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();
		myNoti.veriftyAllEmailNotiDefaultDisable();
        
		/*Step number: 4
		*Step Name: Step 4 : Update Email Notifications toggle
		*Step Description: 
			- Switch ON emails notifications
		*Input Data: 
			
		*Expected Outcome: 
			- The user finds back the same configuration*/
		myNoti.turnOnOffNotiEmail(true);
		myNoti.veriftyAllEmailNotiDefaultEnabled();

 	}

	/**
	*<li> Case ID:125161.</li>
	*<li> Test Case Name: Check Intranet Notifications User settings.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckIntranetNotificationsUserSettings() {
		info("Test 2: Check Intranet Notifications User settings");
		/*Step Number: 1
		*Step Name: Step 1 : Go to My Notifications
		*Step Description: 
			- Login
			- Click the user menu and go to My Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- The user settings page is displayed*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();

		/*Step number: 2
		*Step Name: Step 2 : Check Intranet Notifications toggle
		*Step Description: 
			- Check the toggle related to Intranet Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- The toggle is displayed at the top of the table
			- The toggle is labeled : Get Intranet Notifications
			- The toggle if ON by default*/
		myNoti.checkIntranetNotifiToggle();
		/*Step number: 3
		*Step Name: Step 3 : Update Intranet Notifications toggle
		*Step Description: 
			- Switch OFF Intranet Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- All settings related to Intranet Notifications are disabled and the settings of the user are remembered.*/

		 myNoti.turnOnOffNotiIntranet(false);
         myNoti.veriftyAllIntranetNotiDefaultDisable();
        
        info("the settings of the user are remembered");
        info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();
		myNoti.veriftyAllIntranetNotiDefaultDisable();
		/*Step number: 4
		*Step Name: Step 4 : Update Intranet Notifications toggle
		*Step Description: 
			- Switch ON Intranet Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- The user finds back the same configuration*/ 
		myNoti.turnOnOffNotiIntranet(true);
		myNoti.veriftyAllIntranetNotiDefaultEnabled();

 	}

	/**
	*<li> Case ID:125166.</li>
	*<li> Test Case Name: Check default user settings.</li>
	*<li> Pre-Condition: - The user connects for the first time to the platform 
	*(or haven't done any change in the user settings since his first connection)</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckDefaultUserSettings() {
		info("Test 3: Check default user settings");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login
			- Go to User Menu and click [My Notifications]
			- Check the Intranet Notification setings
		*Input Data: 
			
		*Expected Outcome: 
			The default Intranet Notification settings are : 
			* New User : No Notifications
			* Connection Request : Intranet Notification
			* * Space Invitation : Intranet Notification
			* * Space Join Request: Intranet Notification
			* * Mention : Intranet Notification
			* * Comment : Intranet Notification
			* * Like : No notifications
			* * Post on my Steam : Intranet Notification
			* * Post on my Space : Intranet Notification*/ 
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();
		myNoti.verifyNotificationDefault(notificationType.NewUser_intranet);
		myNoti.verifyNotificationDefault(notificationType.ConnectionRequest_intranet);
		myNoti.verifyNotificationDefault(notificationType.Space_Invitation_intranet);
		myNoti.verifyNotificationDefault(notificationType.Space_Join_intranet);
		myNoti.verifyNotificationDefault(notificationType.AS_Mention_intranet);
		myNoti.verifyNotificationDefault(notificationType.AS_Comment_intranet);
		myNoti.verifyNotificationDefault(notificationType.AS_Like_intranet);
		myNoti.verifyNotificationDefault(notificationType.AS_Post_intranet);
		myNoti.verifyNotificationDefault(notificationType.Space_Post_intranet);
		

 	}}