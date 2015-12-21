package org.exoplatform.selenium.platform.social.functional;

import java.awt.AWTException;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.EmailNotifications.linkEmailNotification;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;

import static org.exoplatform.selenium.TestLogger.info;

public class SOC_notification_Email_Notification_Email extends SOC_TestConfig3{

	/**
	*<li> Case ID:117394.</li>
	*<li> Test Case Name: Check another file activity notification mail content.</li>
	*<li> Pre-Condition: 
		- UserB share an another file (.doc, .ppt...) on activity of UserA 
		- another file activity notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAnotherFileActivityNotificationMailContent() {
		info("Test 1: Check another file activity notification mail content");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Post_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepted connection request from user A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		
		info("User B shares a file in activity stream of User A");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String nameDrive = siteExDrive.getSiteExpDriveByIndex(4);
		String pathData=dataTestForlderPath.getDataTestPathByIndex(1);
		String pathFolder=siteExPath.getSiteExpPathByIndex(10);
		String nameFile=attFileData.getAttachFileByArrayTypeRandom(2);
		Utils.pause(3000);
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		hpAct.uploadAndShareFileActivity(nameDrive, pathFolder, pathData, nameFile,activity);
		hpAct.checkActivity(activity);
		
		/*Step Number: 1
		*Step Name: Step 1: Check Comment notification mail content
		*Step Description: 
			- Check another file (.doc, .ppt...) activity notification mail content
		*Input Data: 
			
		*Expected Outcome: 
			- Another file activity notification mail content is: New post on your activity
			Hi UserA,UserB has posted on your activity stream. See the post below:[activity message]
			[the featured content] [File icon]Reply | View the full discussion*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(3);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(13);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.goToDetailEmailNoti(titleEmail, fullName, activity);
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName, fullName, emailContent,nameFile);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117410.</li>
	*<li> Test Case Name: Check default avatar on connection request notification mail.</li>
	*<li> Pre-Condition: 
		- Don't set avatar for user1 
		- User1 send connection request to user2</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDefaultAvatarOnConnectionRequestNotificationMail() {
		info("Test 2: Check default avatar on connection request notification mail.");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.ConnectionRequest_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(0));
		
		/*Step Number: 1
		*Step Name: Step 1: Check default avatar in notification mail
		*Step Description: 
			- User2 check connect request notification mail 
		*Input Data: 
			
		*Expected Outcome: 
			- Avatar of user who is sent request is default avatar, see attachment.*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(5);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(8);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(9);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName, arrayUser.get(0), emailContent);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117411.</li>
	*<li> Test Case Name: Check default avatar on mention notification mail.</li>
	*<li> Pre-Condition: 
		- Don't set avatar for user1 
		- User1 send connection request to user2</li>
	*<li> Post-Condition: </li>
	* @throws AWTException 
	*/
	@Test
	public  void test03_CheckDefaultAvatarOnMentionNotificationMail() throws AWTException {
		info("Test 1: Check another file activity notification mail content");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Mention_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepted connection request from user A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B mentions user A in activity stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.mentionUserActivity(arrayUser.get(0), activity);
		hpAct.checkActivity(activity);
		
		/*Step Number: 1
		*Step Name: Step 1: Check default avatar in notification mail
		*Step Description: 
			- Check notification mail after user1 mentioned user2
		*Input Data: 
			
		*Expected Outcome: 
			- Avatar of user1 in notification mail is default avatar, see attachment*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(6);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(10);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(11);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName, fullName, emailContent, activity);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117412.</li>
	*<li> Test Case Name: Check default avatar on new user notification mail.</li>
	*<li> Pre-Condition: 
		- Sign up the social intranet
		- Don't upload your avatar</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDefaultAvatarOnNewUserNotificationMail() {
		info("Test 1: Check another file activity notification mail content");
		info("Create data test");
		info("Create 1 user for testing");
		createNewUser(1);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.NewUser_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("Login By John");
		magAc.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
		
		info("John added a new user");
		Utils.pause(3000);
		createNewUser(1);
		
		/*Step Number: 1
		*Step Name: Step 1: Check default avatar in notification mail
		*Step Description: 
			- Check notification mail after signing up
		*Input Data: 
			
		*Expected Outcome: 
			- Avatar of user who is sign up is default avatar, see attachment*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(4);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(4);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(12);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName, arrayUser.get(0), emailContent, true);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117413.</li>
	*<li> Test Case Name: Check default avatar on space join request notification mail.</li>
	*<li> Pre-Condition: 
		- user1 create new space
		- Don't set avatar for user 2
		- user2 login and send request to join space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckDefaultAvatarOnSpaceJoinRequestNotificationmail() {
		info("Test 5: Check default avatar on space join request notification mail");
		info("Create data test");
		info("Create 1 user for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Join_Req_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("user A add a new space");
		String space = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space, space);
		
		info("Login By user B");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("user B request to join space created by user A");
		hp.goToAllSpace();
		spaMg.requestToJoinSpace(space);
		
		/*Step Number: 1
		*Step Name: Step 1: Check default avatar in notification mail
		*Step Description: 
			- Check notification mail after user2 send request to join space
		*Input Data: 
			
		*Expected Outcome: 
			- Avatar of user 2 in notification mail is default avatar*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(7);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(14);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(15);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcationForSpace(emailTitle,firstName, arrayUser.get(1), emailContent, space, true);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117414.</li>
	*<li> Test Case Name: Check default avatar on space notification mail.</li>
	*<li> Pre-Condition: 
		- Create a space
		- Don't set avatar for this space
		- Invite user1 to join this space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckDefaultAvatarOnSpaceNotificationmail() {
		info("Test 6: Check default avatar on space notification mail");
		info("Create data test");
		info("Create 1 user for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Invitation_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("Login By user B");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("user B add a new space");
		String space = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space, space);
		
		info("User B invites user A to space that just created");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(arrayUser.get(0),true,arrayUser.get(0));
		
		/*Step Number: 1
		*Step Name: Step 1: Check default avatar in notification mail
		*Step Description: 
			- Login as user1
			- Check avatar in notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Avatar of space is default avatar, see attachment*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(8);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(16);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(17);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,space);
		emailNot.goToDetailEmailNoti(titleEmail, fullName, space);
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcationForSpace(emailTitle,firstName, "", emailContent, space, false);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117424.</li>
	*<li> Test Case Name: Check default value of $SENDER_NAME and $SENDER_ADDRESS.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CAN NOT AUTOMATED
	*/
	@Test (groups ="pending")
	public  void test07_CheckDefaultOfSenderAndSenderAddress() {
		info("Test 7: Check default value of $SENDER_NAME and $SENDER_ADDRESS");	
		/*Step Number: 1
		*Step Name: Step 1: Check default value of $SENDER_NAME and $SENDER_ADDRESS
		*Step Description: 
			- Open file configuration.properties
			- Check default value of: $SENDER_NAME and $SENDER_ADDRESS
		*Input Data: 
			
		*Expected Outcome: 
			- Default value of these fields are:
				+ exo.notifications.portalname=eXo 
				+ exo.email.smtp.from=noreply@exoplatform.com*/ 
 	}
	
	/**
	*<li> Case ID:117481.</li>
	*<li> Test Case Name: Check image activity notification mail content.</li>
	*<li> Pre-Condition: 
		- UserB share a image activity on activity of UserA 
		- image activity notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckImageActivityNotificationMailContent() {
		info("Test 8: Check image activity notification mail content");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Post_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepted connection request from user A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B shares an image in activity stream of User A");
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String nameDrive = siteExDrive.getSiteExpDriveByIndex(4);
		String pathData=dataTestForlderPath.getDataTestPathByIndex(1);
		String pathFolder=siteExPath.getSiteExpPathByIndex(10);
		String nameFile=attFileData.getAttachFileByArrayTypeRandom(26);
		
		Utils.pause(3000);
		hpAct.uploadAndShareFileActivity(nameDrive, pathFolder, pathData, nameFile,activity);
		hpAct.checkActivity(activity);
		
		/*Step Number: 1
		*Step Name: Step 1: Check Comment notification mail content
		*Step Description: 
			- Check Image activity notification mail content
		*Input Data: 
			
		*Expected Outcome: 
			- Image activity notification mail content is: New post on your activity
			Hi UserA, UserB has posted on your activity stream. See the post below:
			[activity message][the featured content] (as it is displayed in the activity stream)
			Reply | View the full discussion*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(3);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(13);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.goToDetailEmailNoti(titleEmail, fullName, activity);
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName, fullName, emailContent,nameFile);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117486.</li>
	*<li> Test Case Name: Check Link notification mail content.</li>
	*<li> Pre-Condition: 
		- UserB share a link activity on activity of UserA 
		- Link activity notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckLinkNotificationmailContent() {
		info("Test 9: Check Link notification mail content");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Post_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepted connection request from user A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B shares a link in activity stream of User A");
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = linkData.getLinkByArrayTypeRandom(1);
		
		Utils.pause(3000);
		hpAct.addActivity(textDes, link);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",link));
			
		/*Step Number: 1
		*Step Name: Step 1: Check Comment notification mail content
		*Step Description: 
			- Check Link activity notification mail content
		*Input Data: 
			
		*Expected Outcome: 
			- Link activity notification mail content is: New post on your activity
			Hi UserA, UserB has posted on your activity stream. See the post below:
			[activity message][the featured content] (as it is displayed in the activity stream)
			Reply | View the full discussion*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(9);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(3);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(13);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"","1");
		emailNot.goToDetailEmailNoti(titleEmail, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName, fullName, emailContent,textDes, link);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117520.</li>
	*<li> Test Case Name: Check recipient field in notification mail.</li>
	*<li> Pre-Condition: 
		- A notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckrecipientFiledInNotificationMail() {
		info("Test 10: Check recipient field in notification mail.");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.ConnectionRequest_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(0));
		
		/*Step Number: 1
		*Step Name: Step 1: Check recipient field in notification mail
		*Step Description: 
			- Check recipient field in notification mail 
		*Input Data: 
			
		*Expected Outcome: 
			- The recipient field contains the user full name and email
			For ex: John Smith <john.smith@exoplatform.com>.*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(5);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.verifyRecipientAndSenderEmailNotifcation(true, arrayUser.get(0), EMAIL_ADDRESS1);
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117521.</li>
	*<li> Test Case Name: Check sender of all notification emails.</li>
	*<li> Pre-Condition: 
		- A notification email is sent to receiver
		- $SENDER_NAME is "eXo" for ex
		- <$SENDER_ADDRESS> is "noreply@exoplatform.com" for ex</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckSenderOfAllNotificationMail() {
		info("Test 11: Check sender of all notification emails.");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.ConnectionRequest_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(0));
		
		/*Step Number: 1
		*Step Name: Step 1: Check sender of notification email
		*Step Description: 
			- Check sender of notification email 
		*Input Data: 
			
		*Expected Outcome: 
			- The sender used for all emails should be: eXo (noreply@exoplatform.com)
			- These fields are configurable in notification administration.*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(5);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.verifyRecipientAndSenderEmailNotifcation(false, "eXo", "noreply@exoplatform.com");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117529.</li>
	*<li> Test Case Name: Check the bottom content of notification mail.</li>
	*<li> Pre-Condition: 
		- A notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckTheBottomContentOfNotificationMail() {
		info("Test 2: Check default avatar on connection request notification mail.");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.ConnectionRequest_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(0));
		
		/*Step Number: 1
		*Step Name: Step 1: Check the bottom content of notification mail
		*Step Description: 
			- Check the bottom content of notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- The bottom content of notification mail is: 
			"If you do not want to receive such notifications, click here to change your notification settings"..*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(5);
		String content1=notiFormatEmailData.getContentByArrayTypeRandom(18);
		String content2=notiFormatEmailData.getContentByArrayTypeRandom(19);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.verifyBottomContentOfEmailNotifcation(content1,content2);
		
		/*Step Number: 2
		*Step Name: Step 2: Click on [Click here] link
		*Step Description: 
			- Click on [Click here] link
		*Input Data: 
			
		*Expected Outcome: 
			- The click here takes the user to the portal and displays his notification settings screen.*/ 
		emailNot.checkLinkInEmailNotification(linkEmailNotification.Click_Here_Link, "", "", "", "", "");
        emailNot.closeChildBrowsers(parentWindow);
        if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117485.</li>
	*<li> Test Case Name: Check link in notification email.</li>
	*<li> Pre-Condition: 
		- Post notification is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_1_CheckLinkInNotificationEmail_SpaceLink() {
		info("Test 13: Check link in notification email.");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Post_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("user A add a new space");
		String space = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(space, space);
		
		info("User A invites user B to space that just created");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(arrayUser.get(1),true,arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("user B accept space invitation from user A");
		Utils.pause(3000);
		hp.goToAllSpace();
		spaMg.acceptAInvitation(space);
		
		info("User B posts to Space activity of User A");
		hp.goToAllSpace();
		spaHome.goToSpace(space);
		
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		Utils.pause(3000);
		hpAct.addActivity(activity, "");
		hpAct.checkActivity(activity);
		
		/*Step Number: 1
		*Step Name: Step 1: Check space link in notification email
		*Step Description: 
			- Check space link in space invitation email
		*Input Data: 
			
		*Expected Outcome: 
			- Space should link to the space home */
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmailSpacePost=notiEmailData.getContentByArrayTypeRandom(10);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmailSpacePost,fullName,space);
		emailNot.goToDetailEmailNoti(titleEmailSpacePost, fullName, space);
		emailNot.getAllChildWindows();
		emailNot.checkLinkInEmailNotification(linkEmailNotification.Space_Link, "", "", space, "", "");
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117485.</li>
	*<li> Test Case Name: Check link in notification email.</li>
	*<li> Pre-Condition: 
		- A new user notification is sent
	</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_2_CheckLinkInNotificationEmail_PortalLink() {
		info("Test 13: Check link in notification email.");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(1);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.NewUser_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("Login By John");
		magAc.signIn(USER_ROOT,PASS_ROOT);
		Utils.pause(3000);
		
		info("John added a new user B");
		Utils.pause(3000);
		createNewUser(1);
		
		/*Step Number: 2
		*Step Name: Step 2: Check portal link in notification mail
		*Step Description: 
			- Check portal link in new user notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- portal should link to the portal home.*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmailNewlUser=notiEmailData.getContentByArrayTypeRandom(4);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmailNewlUser,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmailNewlUser, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.checkLinkInEmailNotification(linkEmailNotification.Portal_Link, "", "", "", "", "");
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117485.</li>
	*<li> Test Case Name: Check link in notification email.</li>
	*<li> Pre-Condition: 
		- A new user notification is sent
	</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_3_CheckLinkInNotificationEmail_NewUserLink() {
		info("Test 13: Check link in notification email.");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(1);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.NewUser_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("Login By John");
		magAc.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
		
		info("John added a new user B");
		Utils.pause(3000);
		createNewUser(1);
		
		/*Step Number: 3
		*Step Name: Step 3:  Check username link in notification mail
		*Step Description: 
			- Check username link in new user notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- user name should link to the user profile page. */
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String userName=arrayUser.get(1);
		String titleEmailNewlUser=notiEmailData.getContentByArrayTypeRandom(4);
        
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmailNewlUser,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmailNewlUser, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.checkLinkInEmailNotification(linkEmailNotification.NewUser_Link, userName, fullName, "", "", "");
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
	/**
	*<li> Case ID:117482.</li>
	*<li> Test Case Name: Check language of notification mail.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test14_CheckLanguageOfNotificationMail() {
		info("Test 14: Check language of notification mail");
		/*info("Create data test");
		info("Create 1 user for testing");
		String language1 = langData.getLanguageByIndex(0);
		createNewUser(1);
		
		info("User A login");
		magAc.signOut();
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.NewUser_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);*/
		
		/*Step Number: 1
		*Step Name: Step 1: Setting language for user
		*Step Description: 
			- Login 
			- Move mouse over user full name --> Language
			- Select the language you want to change (French for ex)
		*Input Data: 
			
		*Expected Outcome: 
			- Language for user is changed successfully*/ 
		/*info("Change language of user A to French");
		navTool.goToChangeLanguage();
		changeLang.changeLanguage(language1, "Apply");
		
		info("Login By John");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
		
		info("John added a new user");
		Utils.pause(3000);
		createNewUser(1);*/
		
		/*Step Number: 2
		*Step Name: Step 2: Check language on notification email
		*Step Description: 
			- Login by other user, comment or like activity (or do any action which notification email will be sent)
			- Check the email notification in the inbox of receiver
		*Input Data: 
			
		*Expected Outcome: 
			- The notification mail is sent in selected language of user (French for ex)*/ 
		/*info("Log in with User A");
		magAc.signOut();
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(11);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(19);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(20);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,"", arrayUser.get(0), emailContent, true);
        emailNot.closeChildBrowsers(parentWindow);*/
 	}
	
	/**
	*<li> Case ID:117468.</li>
	*<li> Test Case Name: Check embed slideshare presentation activity notification mail content.</li>
	*<li> Pre-Condition: 
		- UserB share an embed slideshare presentation on activity of UserA 
		- embed slideshare presentation activity notification mail is sent</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_CheckEmbededSlidesharePresentationActivityNotificationMailContent() {
		info("Test 15: Check embed slideshare presentation activity notification mail content");
		info("Create data test");
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Post_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accepted connection request from user A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B shares a link in activity stream of User A");
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		
		String textDes = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String link = linkData.getLinkByArrayTypeRandom(3);
		
		Utils.pause(3000);
		hpAct.addActivity(textDes, link);
		waitForAndGetElement(hpAct.ELEMENT_ACTIVITY_TITLE.replace("${text}",textDes).replace("${file}",link));
			
		/*Step Number: 1
		*Step Name: Step 1: Check Comment notification mail content
		*Step Description: 
			Check Comment notification mail content
		*Input Data: 
			
		*Expected Outcome: 
			The mail text contains:
		 	* an activity message 
		 	* a thumbnail image of the embedded
		 	* a link "Watch the video"*/ 
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(9);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(3);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(13);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName, "");
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcation(emailTitle,firstName, fullName, emailContent,textDes, link);
		emailNot.getAllChildWindows();
		emailNot.checkLinkInEmailNotification(linkEmailNotification.Watch_This_Video, "", "", "", textDes, link);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}
	
}
