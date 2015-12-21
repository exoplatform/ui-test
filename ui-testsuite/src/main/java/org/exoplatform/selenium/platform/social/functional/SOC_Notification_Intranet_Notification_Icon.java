package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


	public class SOC_Notification_Intranet_Notification_Icon extends SOC_TestConfig2{

	/**
	*<li> Case ID:125112.</li>
	*<li> Test Case Name: Check Notifications icon in the top navigation.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckNotificationsIconInTheTopNavigation() {
		info("Test 1: Check Notifications icon in the top navigation");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login to platform
		*Input Data: 
			
		*Expected Outcome: 
			- The homepage is displayed*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Check the top navigation
		*Input Data: 
			
		*Expected Outcome: 
			- The Notifications icon is displayed in the top navigation, next to the profile menu*/ 
		info("Check The Notifications icon is displayed in the top navigation");
		waitForAndGetElement(navTool.ELEMENT_INTRANET_NOTIFICATION_NEAR_USER_AVATAR);

 	}

	/**
	*<li> Case ID:125113.</li>
	*<li> Test Case Name: New notifications number is displayed next to the icon.</li>
	*<li> Pre-Condition: The user has unread notifications (no matters the notification type), for instance 3 notifications</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_NewNotificationsNumberIsDisplayedNextToTheIcon() {
		info("Test 2: New notifications number is displayed next to the icon");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login to platform
			- Check the top navigation
		*Input Data: 
			
		*Expected Outcome: 
			- The number of new notifications (3 notifications) is displayed above the icon with a blue badge.*/ 
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		String username4 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email4 = username4+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.NewUser_intranet);
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		addUserPage.addUser(username4, password, email4, username4, username4);
		
		info("Verify that The number of new notifications (3 notifications) is displayed above the icon");
		waitForAndGetElement(navTool.ELEMENT_BADGE_NUMBER_DISPLAY.replace("${number}", "3"));

 	}

	/**
	*<li> Case ID:125114.</li>
	*<li> Test Case Name: Open the notifications list to reset the number of notifications.</li>
	*<li> Pre-Condition: The user has unread notifications (no matters the notification type), for instance 3 notifications</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_OpenTheNotificationsListToResetTheNumberOfNotifications() {
		info("Test 3: Open the notifications list to reset the number of notifications");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login to platform
			- Check the top navigation
		*Input Data: 
			
		*Expected Outcome: 
			- The number of new notifications (3 notifications) is displayed above the icon with a blue badge.*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		String username4 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email4 = username4+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.NewUser_intranet);
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		addUserPage.addUser(username4, password, email4, username4, username4);
		
		info("Verify that The number of new notifications (3 notifications) is displayed above the icon");
		waitForAndGetElement(navTool.ELEMENT_BADGE_NUMBER_DISPLAY.replace("${number}", "3"));

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Click the notification icon
		*Input Data: 
			
		*Expected Outcome: 
			- The list opens
			- When opening the notification list, the number of new notifications displayed in the badge is reset.*/ 
		navTool.goToIntranetNotification();
		info("Verify that The number of new notifications (3 notifications) isnot displayed above the icon");
		waitForElementNotPresent(navTool.ELEMENT_BADGE_NUMBER_DISPLAY.replace("${number}", "3"));

 	}

	/**
	*<li> Case ID:125115.</li>
	*<li> Test Case Name: Check blue badge when no notifications are received.</li>
	*<li> Pre-Condition: The user doesn't have any new notification</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test04_CheckBlueBadgeWhenNoNotificationsAreReceived() {
		info("Test 4: Check blue badge when no notifications are received");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login to platform
			- Check the top navigation
		*Input Data: 
			
		*Expected Outcome: 
			- When there is no new notification, the badge isn't displayed.*/ 

 	}}