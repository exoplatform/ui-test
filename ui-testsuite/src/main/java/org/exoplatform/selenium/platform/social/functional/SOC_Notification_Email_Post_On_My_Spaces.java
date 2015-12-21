package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.notiMode;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting.notificationType;
import org.testng.annotations.*;


	public class SOC_Notification_Email_Post_On_My_Spaces extends SOC_TestConfig3{
		String spaceName;
		String activity;
	/**
	*<li> Case ID:117392.</li>
	*<li> Test Case Name: Check "Post on my spaces" notification mail content.</li>
	*<li> Pre-Condition: - A space is created: Space1 for ex
	- UserA is member or manager of Space1
	- UserB is member of Space1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckPostOnMySpacesNotificationMailContent() {
		info("Test 1: Check Post on my spaces notification mail content");
		/*Step Number: 1
		*Step Name: Step 1: Post activity
		*Step Description: 
			- Login as UserB
			- Post an activity on Space1's stream
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is added*/
		info("Create 2 new users");
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
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User A invites UserB to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(arrayUser.get(1),true,arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accept invitation of User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User B post an activity on the space");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(spaceName);
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		/*Step number: 2
		*Step Name: Step 2: Check notification mail content
		*Step Description: 
			- Check notification mail content
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail content is:New post in $SPACEHi UserA,
			[Full name of UserB] has posted an activity in the Space1 space. See the post below: [Activity]Reply | View the full discussion*/ 
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(10);
		String firstName=arrayUser.get(0);
		String emailTitle=notiFormatEmailData.getContentByArrayTypeRandom(20);
		String emailContent=notiFormatEmailData.getContentByArrayTypeRandom(21);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.verifyFormatEmailNotifcationForSpace(emailTitle,firstName,arrayUser.get(1), emailContent,spaceName,false);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117393.</li>
	*<li> Test Case Name: Check "Post on my spaces" notification mail subject.</li>
	*<li> Pre-Condition: - A space is created: Space1 for ex
	- UserA is member or manager of Space1
	- UserB is member of Space1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckPostOnMySpacesNotificationMailSubject() {
		info("Test 2: Check Post on my spaces notification mail subject");
		/*Step Number: 1
		*Step Name: Step 1: Post activity
		*Step Description: 
			- Login as UserB
			- Post an activity on Space1's stream
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is added*/
		info("Create 2 new users");
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
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User A invites UserB to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(arrayUser.get(1),true,arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accept invitation of User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User B post an activity on the space");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(spaceName);
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		/*Step number: 2
		*Step Name: Step 2: Check notification mail subject
		*Step Description: 
			- Check notification mail subject
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail subject is:[Full name of UserB] has posted an activity in the Space1 space: [Activity title]*/ 
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(10);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117402.</li>
	*<li> Test Case Name: Check category which Post on my spaces notification belongs to.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckCategoryWhichPostOnMySpacesNotificationBelongsTo() {
		info("Test 3: Check category which Post on my spaces notification belongs to");
		/*Step Number: 1
		*Step Name: Step 1: Access notification settings
		*Step Description: 
			- Login
			- Go to Administration/Portal and select [Email Notifications] on the menu
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Settings page is appeared*/
		info("Create 1 users for testing");
		createNewUser(1);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();
		
		/*Step number: 2
		*Step Name: Step 2: Check category which Post on my spaces notification belongs to
		*Step Description: 
			Check category which "Post on my spaces" notification belongs to
		*Input Data: 
			
		*Expected Outcome: 
			- "Post on my spaces" notification belongs to "Spaces" category*/
		
		String category = notiCatData.getCategoryByArrayTypeRandom(3);
		myNoti.verifyNotiBelongToCategory(category,notiMode.Space_Post);

 	}

	/**
	*<li> Case ID:117420.</li>
	*<li> Test Case Name: Check default settings of Post on my spaces notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDefaultSettingsOfPostOnMySpacesNotification() {
		info("Test 4: Check default settings of Post on my spaces notification");
		/*Step Number: 1
		*Step Name: Step 1: Access notification settings
		*Step Description: 
			- Login
			- Move mouse over the full name of user and select [Notifications] on the menu
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Settings page is appeared*/
		info("Create 1 users for testing");
		createNewUser(1);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();
		
		/*Step number: 2
		*Step Name: Step 2: Check default settings of Post on my spaces notification
		*Step Description: 
			Check default settings of Post on my spaces notification
		*Input Data: 
			
		*Expected Outcome: 
			- Default settings of Post on my spaces notification is: Never*/ 
		myNoti.verifyNotificationDefault(notificationType.Space_Post_email);
 	}

	/**
	*<li> Case ID:117435.</li>
	*<li> Test Case Name: Check digest mail when digest mail setting is: Weekly.</li>
	*<li> Pre-Condition: - A space is created: Space1 for ex
	- UserA is member or manager of Space1
	- UserB is member of Space1
	- UserB posts an activity on Space1's stream
	- Digest mail setting is Weekly</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test (groups ="pending")
	public  void test05_CheckDigestMailWhenDigestMailSettingIsWeekly() {
		info("Test 5: Check digest mail when digest mail setting is: Weekly");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of Friday
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The digest mail is sent to UserA at 23:30 of Sunday*/ 

 	}

	/**
	*<li> Case ID:117446.</li>
	*<li> Test Case Name: Check digest message after delete an activity from stream of my space.</li>
	*<li> Pre-Condition: User A add an activity in the space activity stream of the User B</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test (groups ="pending")
	public  void test06_CheckDigestMessageAfterDeleteAnActivityFromStreamOfMySpace() {
		info("Test 6: Check digest message after delete an activity from stream of my space");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet with the User A
			- Open a Space of the User B
			- Add an activity
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is added in the space activity stream of the User B*/

		/*Step number: 2
		*Step Name: Step 1: Change date/time
		*Step Description: 
			- Stop server
			- Change date/time to 23:30 of this day
		*Input Data: 
			
		*Expected Outcome: 
			- Date/time is changed successfully*/

		/*Step number: 3
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check daily digest mail of the User B
		*Input Data: 
			
		*Expected Outcome: 
			- The digest mail is sent
			- The activity is not diaplayed in the digest mail*/ 

 	}

	/**
	*<li> Case ID:117475.</li>
	*<li> Test Case Name: Check event label of Post on my spaces notification.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckEventLabelOfPostOnMySpacesNotification() {
		info("Test 7: Check event label of Post on my spaces notification");
		/*Step Number: 1
		*Step Name: Step 1: Access notification settings
		*Step Description: 
			- Login
			- Move mouse over the full name of user and select [Notifications] on the menu
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Settings page is appeared*/
		info("Create 1 users for testing");
		createNewUser(1);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		navTool.goToMyNotifications();
		myNoti.verifyTilePage();
		
		/*Step number: 2
		*Step Name: Step 2: Check event label of Post on my spaces notification
		*Step Description: 
			Check event label of Post on my spaces notification
		*Input Data: 
			
		*Expected Outcome: 
			- Event label of Post on my spaces notification is: An activity is posted on one of my spaces.*/
		String label = notiLabelData.getContentByArrayTypeRandom(5);
		myNoti.verifyLabelNotificationType(label);

 	}

	/**
	*<li> Case ID:117505.</li>
	*<li> Test Case Name: Check notification name.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckNotificationName() {
		info("Test 8: Check notification name");
		/*Step Number: 1
		*Step Name: Step 1: Access notification administration
		*Step Description: 
			- Login as admin
			- Go to Administration 
			-
			-> Portal 
			-
			-> Email Notifications
		*Input Data: 
			
		*Expected Outcome: 
			- Notification Administration page is appeared*/

		navTool.goToAdminNotifications();
		notiAdmin.verifyTilePage();
		
		/*Step number: 2
		*Step Name: Step 2: Check notification name
		*Step Description: 
			- Check notification name of Post on my spaces
		*Input Data: 
			
		*Expected Outcome: 
			- Notification name is: Post on my Spaces*/ 
		String modeName = notiLabelData.getModeByArrayTypeRandom(5);
		myNoti.verifyLabelNotificationType(modeName);
 	}

	/**
	*<li> Case ID:117519.</li>
	*<li> Test Case Name: Check Post on my spaces notification mail.</li>
	*<li> Pre-Condition: - A space is created: Space1 for ex
	- UserA is member or manager of Space1
	- UserB is member of Space1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckPostOnMySpacesNotificationMail() {
		info("Test 9: Check Post on my spaces notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Post activity
		*Step Description: 
			- Login as UserB
			- Post an activity on Space1's stream
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is added*/
		info("Create 2 new users");
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
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User A invites UserB to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(arrayUser.get(1),true,arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accept invitation of User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User B post an activity on the space");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(spaceName);
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		/*Step number: 2
		*Step Name: Step 2: Check notification mail
		*Step Description: 
			- Check notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- Notification mail is sent to UserA about new post on space's stream*/
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(10);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
		 
	}

	/**
	*<li> Case ID:117532.</li>
	*<li> Test Case Name: Check the digest message is appended for each activity that was posted in the spaces.</li>
	*<li> Pre-Condition: - UserA is member or manager of 3 spaces: Space1, Space2 and Space3
	- There are more than 3 users (who are member of Space1) post activity on Space1 (6 users for example)
	- There are 2 users post activity on Space2
	- There is 1 user post activity on Space3
	- Digest mail setting is Daily for ex</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test (groups ="pending")
	public  void test10_CheckTheDigestMessageIsAppendedForEachActivityThatWasPostedInTheSpaces() {
		info("Test 10 Check the digest message is appended for each activity that was posted in the spaces");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is appended for each activity that was posted in the spacesFor ex:John Smith, Mary Williams, Jack Daniels and 3 others have posted in Products.John Smith, Mary Williams posted have posted in Human Resources.James Davis has posted in Engineering.*/ 

 	}

	/**
	*<li> Case ID:117538.</li>
	*<li> Test Case Name: Check the digest message when there are <=3 notifications.</li>
	*<li> Pre-Condition: - A space is created: Space1 for ex
	- UserA is member or manager of Space1
	- UserB, UserC are member of Space1
	- UserB and UserC post an activity on Space1's stream
	- Digest mail setting is Daily for ex</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test (groups ="pending")
	public  void test11_CheckTheDigestMessageWhenThereAreLessThan3Notifications() {
		info("Test 11 Check the digest message when there are <=3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this dat
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: [UserB's full name], [UserC's full name] have posted in Space1.*/ 

 	}

	/**
	*<li> Case ID:117541.</li>
	*<li> Test Case Name: Check the digest message when there are >3 notifications.</li>
	*<li> Pre-Condition: - A space is created: Space1 for ex
	- UserA is member or manager of Space1
	- There are more than 3 users (who are member of Space1) post activity on this space (5 users for example)
	- Digest mail setting is Daily for ex</li>
	*<li> Post-Condition: </li>
	*
	*CANNOT AUTOMATED
	*/
	@Test (groups ="pending")
	public  void test12_CheckTheDigestMessageWhenThereAreMorethan3Notifications() {
		info("Test 12 Check the digest message when there are >3 notifications");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail at 23:30
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: [UserB's full name], [UserC's full name], [UserD's full name] and 2 others have posted in Space1.*/ 

 	}

	/**
	*<li> Case ID:117543.</li>
	*<li> Test Case Name: Check the digest message when there is only one notification.</li>
	*<li> Pre-Condition: - A space is created: Space1 for ex
	- UserA is member or manager of Space1
	- UserB is member of Space1
	- UserB posts an activity on Space1's stream
	- Digest mail setting is Daily for ex</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test (groups ="pending")
	public  void test13_CheckTheDigestMessageWhenThereIsOnlyOneNotification() {
		info("Test 13 Check the digest message when there is only one notification");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed*/

		/*Step number: 2
		*Step Name: Step 2: Check digest mail
		*Step Description: 
			- Check digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- The digest message is: [UserB's full name] has posted in Space1.*/ 

 	}

	/**
	*<li> Case ID:117557.</li>
	*<li> Test Case Name: Check [Count] and [Space] link in digest mail.</li>
	*<li> Pre-Condition: - UserA is member or manager of a spaces: Space1
	- There are more than 3 users (who are member of Space1) post activity on Space1 (6 users for example)
	- Digest mail setting is Daily for ex</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test (groups ="pending")
	public  void test14_CheckCountAndSpaceLinkInDigestMail() {
		info("Test 14 Check [Count] and [Space] link in digest mail");
		/*Step Number: 1
		*Step Name: Step 1: Change server date/time
		*Step Description: 
			- Stop server
			- Change server date/time to 23:20 of this day
			- Restart server
		*Input Data: 
			
		*Expected Outcome: 
			- Server date/time is changed
			- Digest mail is sent to UserA*/

		/*Step number: 2
		*Step Name: Step 2: Check [Count] link in digest mail
		*Step Description: 
			- Click [3] link in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- the user is taken directly to the activity stream of the space.*/

		/*Step number: 3
		*Step Name: Step 3: Check [Space] link in digest mail
		*Step Description: 
			- Click [Space1] link in digest mail
		*Input Data: 
			
		*Expected Outcome: 
			- the user is taken directly to the user activity stream (My Activities).*/ 

 	}

	/**
	*<li> Case ID:117570.</li>
	*<li> Test Case Name: Check [Reply] link "Post on my spaces" notification mail.</li>
	*<li> Pre-Condition: - A space is created: Space1 for ex
	- UserA is member or manager of Space1
	- UserB is member of Space1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_CheckReplyLinkPostOnMySpacesNotificationMail() {
		info("Test 15 Check [Reply] link Post on my spaces notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Post activity
		*Step Description: 
			- Login as UserB
			- Post an activity on Space1's stream
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is added
			- Notification mail is sent to UserA*/
		info("Create 2 new users");
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
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User A invites UserB to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(arrayUser.get(1),true,arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accept invitation of User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User B post an activity on the space");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(spaceName);
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		/*Step number: 2
		*Step Name: Step 2: Check notification mail
		*Step Description: 
			- Click [Reply] link
		*Input Data: 
			
		*Expected Outcome: 
			- Reply link will take the user to the portal and displays the activity stream on the position of the activity with the comment box opened, ready for reply*/ 
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(10);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(true,activity);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}

	/**
	*<li> Case ID:117581.</li>
	*<li> Test Case Name: Check [View full discussion] link "Post on my spaces" notification mail.</li>
	*<li> Pre-Condition: - A space is created: Space1 for ex
	- UserA is member or manager of Space1
	- UserB is member of Space1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_CheckViewFullDiscussionLinkPostOnMySpacesNotificationMail() {
		info("Test 16 Check [View full discussion] link Post on my spaces notification mail");
		/*Step Number: 1
		*Step Name: Step 1: Post activity
		*Step Description: 
			- Login as UserB
			- Post an activity on Space1's stream
		*Input Data: 
			
		*Expected Outcome: 
			- New activity is added
			- Notification mail is sent to UserA*/
		info("Create 2 new users");
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
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User A invites UserB to the space");
		hp.goToSpecificSpace(spaceName);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(arrayUser.get(1),true,arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User B accept invitation of User A");
		hp.goToAllSpace();
		spaMg.goToInvitationsReceivedTab();
		spaMg.acceptAInvitation(spaceName);
		
		info("User B post an activity on the space");
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(spaceName);
		hpAct.addActivity(activity,"");
		hpAct.checkActivity(activity);
		
		/*Step number: 2
		*Step Name: Step 2: Check [View full discussion] link in notification mail
		*Step Description: 
			- Click [View full discussion] link in notification mail
		*Input Data: 
			
		*Expected Outcome: 
			- View full discussion link will take the user to the portal and displays the activity stream on the position of the activity with all comments expanded.*/ 
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(10);
		
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,spaceName);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,spaceName);
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,activity);
		emailNot.closeChildBrowsers(parentWindow);
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
 	}}