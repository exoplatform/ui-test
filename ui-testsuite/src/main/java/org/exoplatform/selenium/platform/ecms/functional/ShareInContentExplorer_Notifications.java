package org.exoplatform.selenium.platform.ecms.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


	public class ShareInContentExplorer_Notifications extends ECMS_TestConfig{

	/**
	*<li> Case ID:130870.</li>
	*<li> Test Case Name: Check Intranet Notification displayed when a file is shared.</li>
	*<li> Pre-Condition: In Notification Settings of users: Intranet Notification is configured to alert when any activity posted in Space AS.userA and userB are member of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckIntranetNotificationDisplayedWhenAFileIsShared() {
		info("Test 1: Check Intranet Notification displayed when a file is shared");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to Space1.*/

		String spaceName = "space130870";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);		
		
		info("Add/upload a file and share file");
		//Create a space with 2 members
		initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
				
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
			
		//Go to Documents & upload file
		shareDocumentToSpace(fileName, spaceName, null);
		manageLoginOut.signOut();		
		
		/*Step number: 2
		*Step Name: Step 2: Check Notification displayed.
		*Step Description: 
			- Log in by userB, member of space.
			- Check Notification displayed.
		*Input Data: 
			
		*Expected Outcome: 
			- The intranet notification about sharing file displayed.*/


		info("Check Notification displayed");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);	
		Utils.pause(3000);
		checkFileShareNotification(DATA_NAME_USER1, spaceName, true);
		
		/*Step number: 3
		*Step Name: Step 3:Check Opening Activity from Intranet Notification
		*Step Description: 
			- Click on the Notification.
		*Input Data: 
			
		*Expected Outcome: 
			- The activity of file shared is opened.*/ 
		
		info("Check Opening Activity from Intranet Notification");
		click(navTool.ELEMENT_SPACE_DOCUMENTS_SHARED_NOTIFICATION
						.replace("${author}", DATA_NAME_USER1)
						.replace("${spaceName}", spaceName));
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT
							.replace("${author}", DATA_NAME_USER1)
							.replace("${spaceName}", spaceName));
		
		info("End Test 1: Check Intranet Notification displayed when a file is shared");
 	}

	/**
	*<li> Case ID:130871.</li>
	*<li> Test Case Name: Check Email Notification received when a file is shared.</li>
	*<li> Pre-Condition: Email Settings is configured to send and receive Email Notification. userA and userB are configured to received Email Notifications when activity is posted in Space AS.userA and userB are members of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckEmailNotificationReceivedWhenAFileIsShared() {
		info("Test 2: Check Email Notification received when a file is shared");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to Space1.*/

		String spaceName = "space130871";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);		
		
		//Change email address of User B
		info("Change email address");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);
		navTool.goToMyProfile();
		userProfilePage.goToEditProfile();
		userProfilePage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProfilePage.saveCancelUpdateInfo(true);
		manageLoginOut.signOut();		
		
		info("Add/upload a file and share file");
		//Create a space with 2 members
		initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
				
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
			
		//Go to Documents & upload file
		shareDocumentToSpace(fileName, spaceName, null);
		manageLoginOut.signOut();				
		
		/*Step number: 2
		*Step Name: Step 2: Check Email Notification received.
		*Step Description: 
			- Check Email Notification received.
		*Input Data: 
			
		*Expected Outcome: 
			- Space's member, userB can received the email notification.*/
		
		info("Check Email Notification received");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);		
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
				
		String titleEmail=notificationEmailData.getContentByArrayTypeRandom(12);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNotifications.getAllChildWindows();
		emailNotifications.verifyPresentEmailActivityNotifications(titleEmail,DATA_NAME_USER1,"");
				 		
		
		/*Step number: 3
		*Step Name: Step 3:Check Opening Activity from Email Notification.
		*Step Description: 
			- Click on the button "View full discussion"/"Reply" in email.
		*Input Data: 
			
		*Expected Outcome: 
			- The activity of file shared is opened.*/ 
	
		emailNotifications.goToDetailEmailNoti(titleEmail, DATA_NAME_USER1,"");
		emailNotifications.getAllChildWindows();
		emailNotifications.clickOnViewDiscussBtn();
		emailNotifications.getAllChildWindows();
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_CONTENT
								.replace("${author}", DATA_NAME_USER1).replace("${spaceName}", spaceName));

		Utils.pause(3000);
		info("End Test 2: Check Email Notification received when a file is shared");
 	}

	/**
	*<li> Case ID:130904.</li>
	*<li> Test Case Name: Check Opening Activity from Notification while activity was removed by other.</li>
	*<li> Pre-Condition: In Notification Settings of users: Intranet Notification is configured to alert when any activity posted in Space AS.userA and userB are member of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckOpeningActivityFromNotificationWhileActivityWasRemovedByOther() {
		info("Test 3: Check Opening Activity from Notification while activity was removed by other");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to Space1.*/

		String spaceName = "space130904";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);		
		
		info("Add/upload a file and share file");
		//Create a space with 2 members
		initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
				
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
			
		//Go to Documents & upload file
		shareDocumentToSpace(fileName, spaceName, null);
		manageLoginOut.signOut();				
		
		/*Step number: 2
		*Step Name: Step 2: Check Notification displayed.
		*Step Description: 
			- Log in by userB, member of space.
			- Check Notification displayed.
		*Input Data: 
			
		*Expected Outcome: 
			- The intranet notification about sharing file displayed.*/

		info("Check Notification displayed");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);
		Utils.pause(3000);
		checkFileShareNotification(DATA_NAME_USER1, spaceName, true);		
		
		/*Step number: 3
		*Step Name: Step 3: Activity of File shared is deleted by another user.
		*Step Description: 
			- Log in as userA.
			- Remove activity in AS, or remove symlink in Space1 Documents > Shared folder.
		*Input Data: 
			
		*Expected Outcome: 
			- Activity is deleted in Intranet AS, Space AS.*/
		
		info("Activity of File shared is deleted by another user");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);	
		deleteSymlink(spaceName, fileName);
		
		checkShareActivityAfterDeleted(spaceName, true);
		
		manageLoginOut.signOut();
		
		/*Step number: 4
		*Step Name: Step 4:Check Opening Activity from Intranet Notification
		*Step Description: 
			From browser of user1.
			- Click on the Notification about File shared.
		*Input Data: 
			
		*Expected Outcome: 
			- The UI should not be broken.
			- There should have a suitable warning message displayed in UI to notice that the activity is not available.*/ 
		
		
		info("Check Opening Activity from Intranet Notification");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);	
		checkFileShareNotification(DATA_NAME_USER1, spaceName, false);	
		manageLoginOut.signOut();
		
		info("End Test 3: Check Opening Activity from Notification while activity was removed by other");
 	}

	/**
	*<li> Case ID:130905.</li>
	*<li> Test Case Name: Check Opening Activity from Email Notification while activity was removed by other.</li>
	*<li> Pre-Condition: Email Settings is configured to send and receive Email Notification. userA and userB are configured to received Email Notifications when activity is posted in Space AS.userA and userB are members of Space1.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckOpeningActivityFromEmailNotificationWhileActivityWasRemovedByOther() {
		info("Test 4: Check Opening Activity from Email Notification while activity was removed by other");
		/*Step Number: 1
		*Step Name: Step 1:Add/upload a file and share file.
		*Step Description: 
			- Log in as userA.
			- Go to Content Explorer.(Ex: Personal Documents / Space Documents / Site Explorer).
			- Add/Upload a file.
			- Share this file to Space1.
		*Input Data: 
			
		*Expected Outcome: 
			- File is shared to Space1.*/

		String spaceName = "space130905";
		String fileName = attFileData.getAttachFileByArrayTypeRandom(1);		
		
		//Change email address of User B
		info("Change email address");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);
		navTool.goToMyProfile();
		userProfilePage.goToEditProfile();
		userProfilePage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProfilePage.saveCancelUpdateInfo(true);
		manageLoginOut.signOut();		
		
		info("Add/upload a file and share file");
		//Create a space with 2 members
		initSpaceWithUsers(spaceName, DATA_USER1, DATA_USER2, DATA_NAME_USER2, DATA_PASS);
				
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		Utils.pause(3000);
			
		//Go to Documents & upload file
		shareDocumentToSpace(fileName, spaceName, null);
		manageLoginOut.signOut();	
		
		/*Step number: 2
		*Step Name: Step 2: Check Email Notification received.
		*Step Description: 
			UserB, member of space:
			- Check Email Notification received.
		*Input Data: 
			
		*Expected Outcome: 
			- The email notification about sharing file received.*/

		info("Check Email Notification received");
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);		
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);

		String titleEmail=notificationEmailData.getContentByArrayTypeRandom(12);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNotifications.getAllChildWindows();
		emailNotifications.verifyPresentEmailActivityNotifications(titleEmail,DATA_NAME_USER1,"");	
	
		switchToParentWindow();
		manageLoginOut.signOut();
		
		/*Step number: 3
		*Step Name: Step 3: Activity of File shared is deleted by another user.
		*Step Description: 
			- Log in userA.
			- Remove activity in AS, or remove symlink in Space1 Documents > Shared folder.
		*Input Data: 
			
		*Expected Outcome: 
			- Activity is deleted in Intranet AS, Space AS.*/
		
		info("Activity of File shared is deleted by another user");
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);	
		deleteSymlink(spaceName, fileName);
		
		checkShareActivityAfterDeleted(spaceName, true);
		
		manageLoginOut.signOut();		
		
		/*Step number: 4
		*Step Name: Step 4:Check Opening Activity from Email Notification
		*Step Description: 
			From email notification of userB.
			- Click on"View Full Discussion" / "Reply".
		*Input Data: 
			
		*Expected Outcome: 
			- The UI should not be broken.
			- There should have a suitable warning message displayed in UI to notice that the activity is not available.*/ 
		
		manageLoginOut.signIn(DATA_USER2, DATA_PASS);	
		Utils.pause(10000);
		emailNotifications.getAllChildWindows();		
		emailNotifications.goToDetailEmailNoti(titleEmail, DATA_NAME_USER1,"");
		emailNotifications.getAllChildWindows();
		emailNotifications.clickOnViewDiscussBtn();
		emailNotifications.getAllChildWindows();
		
		waitForAndGetElement(siteExplorerHome.ELEMENT_SHARE_DOCUMENT_ACTIVITY_NOT_FOUND);
		homepage.goToHomePage();
		checkShareActivityAfterDeleted(spaceName, true);
		
		info("End Test 4: Check Opening Activity from Email Notification while activity was removed by other");
 	}
	
	private void checkFileShareNotification(String author, String spaceName, boolean available){
		waitForAndGetElement(navTool.ELEMENT_TOOLBAR_NOTIFICATION_LIST);
		click(navTool.ELEMENT_TOOLBAR_NOTIFICATION_LIST);
		if(available){
			waitForAndGetElement(navTool.ELEMENT_SPACE_DOCUMENTS_SHARED_NOTIFICATION
									.replace("${author}", author)
									.replace("${spaceName}", spaceName));
		}else {
			waitForElementNotPresent(navTool.ELEMENT_SPACE_DOCUMENTS_SHARED_NOTIFICATION
					.replace("${author}", author)
					.replace("${spaceName}", spaceName));
		}
	}
}