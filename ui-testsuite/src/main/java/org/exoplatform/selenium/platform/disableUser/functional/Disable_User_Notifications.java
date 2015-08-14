package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


	public class Disable_User_Notifications extends Disable_User_TestConfig{
		
	/**
	*<li> Case ID:128010.</li>
	*<li> Test Case Name: Check email notification for disabled user.</li>
	*<li> Pre-Condition: User A is member in Intranet and was able to receive email notificationUser A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128113.</li>
	*<li> Test Case Name: Check intranet notification for disabled user.</li>
	*<li> Pre-Condition: User A is member in Intranet and 
	*was able to receive social notification on intranetUser A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_CheckEmailNotificationForDisabledUser() {
		info("Test 1: Check email notification for disabled user");
		searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
		info("Create a new user");
		createNewUser();
		info("Log in with new user");
		String userDisable=username;
		String passwordDisable=password;
		magAc.signOut();
		magAc.signIn(username,password);
		
		Utils.pause(2000);
		info("Change email address");
		navToolBar.goToMyProfile();
		myProfile.goToEditProfile();
		myProfile.updateBasicInformation("","",EMAIL_ADDRESS2);
		myProfile.saveCancelUpdateInfo(true);
		
		navToolBar.goToMyNotifications();
		myNotifPage.enableNotification(myNotiType.NewUser_email);
		myNotifPage.enableNotification(myNotiType.NewUser_intranet);
		
		/*Step Number: 1
		*Step Name: Step 1: Perform some activities
		*Step Description: 
			- Connect to Intranet with the User B
			- Perform social activities (add user, for example)
		*Input Data: 
			
		*Expected Outcome: 
			- Activities are performed*/
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		info("disable user");
		navToolBar.goToUsersAndGroupsManagement();
 	 	userAndGroup.searchUser(username, searchUserName);
 	 	userAndGroup.enableDisableUser(username, false);
		createNewUser();
		
		/*Step number: 2
		*Step Name: Step 2: Check the email inbox of User A
		*Step Description: 
			- Check the email inbox of the User A
		*Input Data: 
			
		*Expected Outcome: 
			-Social notification email is NOT displayed*/ 
		info("check mail");
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=username+" "+lastName;
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(4);

		goToMail(EMAIL_ADDRESS2, EMAIL_PASS);
		Utils.pause(10000);
		notiEmail.getAllChildWindows();
		notiEmail.verifyNotPresentEmailActivityNotifications(titleEmail,fullName,"");
		notiEmail.closeChildBrowsers(parentWindow);
		
		info("enable user");
		switchToParentWindow();
		navToolBar.goToUsersAndGroupsManagement();
		userAndGroup.selectDisableStatus("All");
 	 	userAndGroup.searchUser(userDisable, searchUserName);
 	 	userAndGroup.enableDisableUser(userDisable, true);
 	 	
 	 	info("Check intranet notification");
 	 	magAc.signOut();
		magAc.signIn(userDisable,passwordDisable);
		intraNot.checkNotPresentStatus("joined", username);
		
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		deleteUser();
 	}
}