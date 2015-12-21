package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


	public class SOC_Notification_Intranet_New_User extends SOC_TestConfig2{

	/**
	*<li> Case ID:125108.</li>
	*<li> Test Case Name: Check New User notification.</li>
	*<li> Pre-Condition: - The settings "Someone joins the social intranet" is activated in User Settings
	- A new user has joined the Intranet</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckNewUserNotification() {
		info("Test 1: Check New User notification");
		/*Step Number: 1
		*Step Name: Step1 ; Check notification list
		*Step Description: 
			- Login to the platform (not with the new user)
			- Click the notifications icon
			- Check the list
		*Input Data: 
			
		*Expected Outcome: 
			A New User notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
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

		/*Step number: 2
		*Step Name: Step 2 : Check notification message
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The notification message is :$AVATAR$USER has joined $PORTAL_NAME.$DATEWhere : 
			- $USER is the new user
			- $AVATAR is the thumbnail of the new user
			- $DATE is the date of the notification*/ 
		info("Check intranet notification");
		String status = notiIntranetData.getContentByArrayTypeRandom(11);
		navTool.goToIntranetNotification();
		intraNot.checkAvatarInStatus(username2,true);
		intraNot.checkStatus(status,username2);

 	}

	/**
	*<li> Case ID:125109.</li>
	*<li> Test Case Name: Click the New User notification.</li>
	*<li> Pre-Condition: - The settings "Someone joins the social intranet" is activated in User Settings
	- A New User notification has been received</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_ClickTheNewUserNotification() {
		info("Test 2: Click the New User notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login
			- Click the notifications icon
		*Input Data: 
			
		*Expected Outcome: 
			- A New User notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
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
		
		info("Check intranet notification");
		String status = notiIntranetData.getContentByArrayTypeRandom(11);
		navTool.goToIntranetNotification();
		intraNot.checkStatus(status,username2);

		/*Step number: 2
		*Step Name: Step 2 : Click the notification area
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirected to the profile of the new user*/
		info("The user is redirected to the profile of the new user");
		intraNot.goToDetailNewUserJoinIntranet(username2,true);
		waitForAndGetElement(userProPage.ELEMENT_USER_NAME_PAGE.replace("$fullName",username2+" "+username2));

 	}

	/**
	*<li> Case ID:125110.</li>
	*<li> Test Case Name: Check View All after receiving a New User notification.</li>
	*<li> Pre-Condition: - The settings "Someone joins the social intranet" is activated in User Settings
	- A New User notification has been received</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckViewAllAfterReceivingANewUserNotification() {
		info("Test 3: Check View All after receiving a New User notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login
			- Click the notifications icon
		*Input Data: 
			
		*Expected Outcome: 
			- A New User notification is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
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
		
		/*Step number: 2
		*Step Name: Step 2 : Check View All page
		*Step Description: 
			- Go to View All
		*Input Data: 
			
		*Expected Outcome: 
			- The New User Notification is displayed*/ 
		info("Check intranet notification");
		String status = notiIntranetData.getContentByArrayTypeRandom(11);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkStatus(status,username2);

 	}}