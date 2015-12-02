package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


public class SOC_Notification_Notification_Settings extends SOC_TestConfig{

	/**
	 *<li> Case ID:117388.</li>
	 *<li> Test Case Name: Access notification settings.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_AccessNotificationSettings() {
		info("Test 1: Access notification settings");
		/*Step Number: 1
		 *Step Name: Step 1: Access notification settings
		 *Step Description: 
			- Log in as a user.
			- Move mouse over the full name of user and select [My Notifications] on the menu.
		 *Input Data: 

		 *Expected Outcome: 
			- Notification Settings page is appeared.
			- The entry is placed in the menu after My Dashboard.
			-It opens the Notifications Settings page in the user navigation.*/ 
		navTool.goToMyNotifications();
		waitForAndGetElement(myNotifPage.ELEMENT_MY_NOTIFICATION_SETTING_FORM);
	}

	/**
	 *<li> Case ID:117511.</li>
	 *<li> Test Case Name: Check notification types are grouped in categories.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckNotificationTypesAreGroupedInCategories() {
		info("Test 2: Check notification types are grouped in categories");
		/*Step Number: 1
		 *Step Name: Step 1: Access notification settings
		 *Step Description: 
			- Login
			- Move mouse over the full name of user and select [My Notifications] on the menu
		 *Input Data: 

		 *Expected Outcome: 
			- Notification Settings page is appeared*/
		navTool.goToMyNotifications();
		waitForAndGetElement(myNotifPage.ELEMENT_MY_NOTIFICATION_SETTING_FORM);

		/*Step number: 2
		 *Step Name: Step 2: Check notification types are grouped in categories
		 *Step Description: 
			- Check notification types are grouped in categories
		 *Input Data: 

		 *Expected Outcome: 
			- Category "General" includes: "Someone joins the social intranet"
			- Category "Connections" includes: Someone sends me a connection request
			- Category "Spaces" includes:Someone requests to join one of my spaces, I receive a Space invitation, An activity is posted on one of my spaces
			- Category "Activity Stream" includes: Someone likes one of my activities, Someone @mentions me, Someone posts a message on my activity stream, Someone comments on one of my activities*/ 
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_GROUP.replace("$number","1").replace("$groupName","General"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number","2").replace("$id","NewUserPlugin"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_GROUP.replace("$number","3").replace("$groupName","Connections"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number","4").replace("$id","RelationshipReceivedRequestPlugin"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_GROUP.replace("$number","5").replace("$groupName","Spaces"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number","6").replace("$id","RequestJoinSpacePlugin"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number","7").replace("$id","SpaceInvitationPlugin"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number","8").replace("$id","PostActivitySpaceStreamPlugin"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_GROUP.replace("$number","9").replace("$groupName","Activity Stream"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number","10").replace("$id","LikePlugin"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number","11").replace("$id","ActivityMentionPlugin"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number","12").replace("$id","PostActivityPlugin"));
		waitForAndGetElement(myNotifPage.ELEMENT_MYNOTIFICATION_SETTING_TYPE.replace("$number","13").replace("$id","ActivityCommentPlugin"));

	}

	/**
	 *<li> Case ID:117522.</li>
	 *<li> Test Case Name: Check settings in notification table are context=user, scope=global..</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CheckSettingsInNotificationTableAreContextUserScopeGlobal() {
		info("Test 3: Check settings in notification table are context=user, scope=global.");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		/*Step Number: 1
		 *Step Name: Step 1: Access notification settings
		 *Step Description: 
			- Login as user1
			- Move mouse over the full name of user and select [My Notifications] in the menu
		 *Input Data: 

		 *Expected Outcome: 
			- Notification Settings page is appeared*/

		magAc.signIn(username1, password1);
		navTool.goToMyNotifications();
		myNotifPage.verifyTilePage();

		/*Step number: 2
		 *Step Name: Step 2: Change notification settings
		 *Step Description: 
			- Change settings for some notification types
		 *Input Data: 

		 *Expected Outcome: 
			- The change is saved*/
		myNotifPage.enableNotification(myNotiType.NewUser_intranet);
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);

		/*Step number: 3
		 *Step Name: Step 3: Check notification settings by other user
		 *Step Description: 
			- Login as user2
			- Go to notification settings page
		 *Input Data: 

		 *Expected Outcome: 
			- The change at step 2 is not saved in notification settings of user2*/ 
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToMyNotifications();
		myNotifPage.verifyTilePage();
		myNotifPage.verifyNotificationTypeDisable(notificationType.NewUser_intranet);
		myNotifPage.verifyNotificationTypeDisable(notificationType.AS_Like_intranet);
	}

	/**
	 *<li> Case ID:117575.</li>
	 *<li> Test Case Name: Check [Reset] button.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CheckResetButton() {
		info("Test 4: Check [Reset] button");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String email1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber() + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);

		/*Step Number: 1
		 *Step Name: Step 1: Access notification settings
		 *Step Description: 
			- Login
			- Move mouse over the full name of user and select [Notifications] on the menu
		 *Input Data: 

		 *Expected Outcome: 
			- Notification Settings page is appeared*/
		magAc.signIn(username1, password1);
		navTool.goToMyNotifications();
		myNotifPage.verifyTilePage();
		myNotifPage.enableNotification(myNotiType.NewUser_intranet);
		myNotifPage.enableNotification(myNotiType.AS_Like_intranet);

		/*Step number: 3
		 *Step Name: Step 3: Check [Reset] button when Cancel
		 *Step Description: 
			- Make change somethings in notification settings table
			- Click [Reset]
			- Click [Cancel]
		 *Input Data: 

		 *Expected Outcome: 
			- Message "All your notification settings will be reset to default values. Your previous settings will be lost" is displayed
			- All changes are not reset*/ 
		myNotifPage.cancelResetNotiSetting();
		myNotifPage.verifyNotificationTypeEnable(notificationType.NewUser_intranet);
		myNotifPage.verifyNotificationTypeEnable(notificationType.AS_Like_intranet);

		/*Step number: 2
		 *Step Name: Step 2: Check [Reset] button when Confirm
		 *Step Description: 
			- Make change somethings in notification settings table
			- Click [Reset]
			- Click [Confirm]
		 *Input Data: 

		 *Expected Outcome: 
			- Message "All your notification settings will be reset to default values. Your previous settings will be lost" is displayed
			- All changes are reset to default values*/
		myNotifPage.confirmResetNotificationSetting();
		myNotifPage.verifyNotificationTypeDisable(notificationType.NewUser_intranet);
		myNotifPage.verifyNotificationTypeDisable(notificationType.AS_Like_intranet);
	}
}