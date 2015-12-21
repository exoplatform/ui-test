package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


	public class SOC_Notification_Email_Activity_Viewer extends SOC_TestConfig3{

	/**
	*<li> Case ID:117389.</li>
	*<li> Test Case Name: Activity Viewer app displays only one activity at once.</li>
	*<li> Pre-Condition: - User A posts an activity on User B Activity stream
	- Post notification is sent to User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_00_ActivityViewerAppDisplaysOnlyOneActivityAtOnce() {
		info("Test 1: Activity Viewer app displays only one activity at once");
		/*Step Number: 1
		*Step Name: Step 1: Receive a notification
		*Step Description: 
			- Check mailbox of User A
		*Input Data: 
			
		*Expected Outcome: 
			- The user A received a notification email: New post on your activity stream*/
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
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B add an activity on User A's stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2: Check the View full discussion link
		*Step Description: 
			- Click [View the full discussion] in the email
		*Input Data: 
			
		*Expected Outcome: 
			The user is redirection to the Activity Viewer page*/

		/*Step number: 3
		*Step Name: Step 3: Activity Viewer display
		*Step Description: 
			- Check feature, layout, UI of the Activity Viewer apps of step 2
		*Input Data: 
			
		*Expected Outcome: 
			- Only 1 activity is displayed 
			- The activity is made out of different parts:
			* the author
			* * the author's avatar 
			* * the space (optional)
			* * the type (optional)
			* * the activity message
			* * the featured content (optional)
			* * the Action bar (Comment and Like links + custom actions)*
			*  the like section
			*  * the comment section*/
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,activity);
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,activity);
        emailNot.closeChildBrowsers(parentWindow);
 	}
	
	@Test
	public  void test01_01_ActivityViewerAppDisplaysOnlyOneActivityAtOnce() {
		info("Test 1: Activity Viewer app displays only one activity at once");
		/*Step Number: 1
		*Step Name: Step 1: Receive a notification
		*Step Description: 
			- Check mailbox of User A
		*Input Data: 
			
		*Expected Outcome: 
			- The user A received a notification email: New post on your activity stream*/
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
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B add an activity on User A's stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);

		/*Step number: 2
		*Step Name: Step 2: Check the View full discussion link
		*Step Description: 
			- Click [View the full discussion] in the email
		*Input Data: 
			
		*Expected Outcome: 
			The user is redirection to the Activity Viewer page*/

		/*Step number: 3
		*Step Name: Step 3: Check the Reply link
		*Step Description: 
			- Click [Reply] in the email
		*Input Data: 
			
		*Expected Outcome: 
			The user is redirection to the Activity Viewer page*/
		

		/*Step number: 5
		*Step Name: Step 5: Activity Viewer display
		*Step Description: 
			- Check feature, layout, UI of the Activity Viewer apps of step 3
		*Input Data: 
			
		*Expected Outcome: 
			- Only 1 activity is displayed 
			- The activity is made out of different parts:
			* the author
			* * the author's avatar
			* * the space (optional)
			* * the type (optional)
			* * the activity message
			* * the featured content (optional)
			* * the Action bar (Comment and Like links + custom actions)
			* * the like section
			* * the comment section and comment text box*/ 
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,activity);
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(true,activity);
        emailNot.closeChildBrowsers(parentWindow);

 	}


	/**
	*<li> Case ID:117390.</li>
	*<li> Test Case Name: All comments of an activity are expanded when a comment is designed through the anchor.</li>
	*<li> Pre-Condition: - User A comments on User B activity
	- An email notification is send to User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_AllCommentsOfAnActivityAreExpandedWhenACommentIsDesignedThroughTheAnchor() {
		info("Test 2: All comments of an activity are expanded when a comment is designed through the anchor");
		/*Step Number: 1
		*Step Name: Step1 : Click link in the email
		*Step Description: 
			- Login as user B
			- Open email notification, then right
			-click the [View the full discussion] button and get the link
			- Paste the link on a new tab
		*Input Data: 
			
		*Expected Outcome: 
			The user is redirected to the portal and the activity is displayed in the activity viewer.*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		/*Step number: 2
		*Step Name: Step2 : Check the comments layout
		*Step Description: 
			- Look at the comment area
		*Input Data: 
			
		*Expected Outcome: 
			- All comments linked to the activity are expanded
			- The comment of User A should be highlighted in a different color*/
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(1);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
		notAct.checkCommentExpand(comment, true);
        emailNot.closeChildBrowsers(parentWindow);
 	}

	/**
	*<li> Case ID:117391.</li>
	*<li> Test Case Name: Check "activity not found" message.</li>
	*<li> Pre-Condition: - User A posts an activity on User B activity stream.
	- User B receives a notification email and a link to the activity (with an id)Please also note :
	- The id 0123456789 doesn't match to any activity</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckActivityNotFoundMessage() {
		info("Test 3: Check activity not found message");
		/*Step Number: 1
		*Step Name: Step 1: The id parameter is not passed
		*Step Description: 
			- Connect to the platform with User B or user C
			- Input the url: http://localhost:8080/portal/intranet/activity
		*Input Data: 
			
		*Expected Outcome: 
			The portlet displays the message : Activity not found.*/
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
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B add an activity on User A's stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		info("The portlet displays the message : Activity not found");
		driver.get(baseUrl+"/intranet/activity");
		notAct.verifyActivityNotFound();

		/*Step number: 2
		*Step Name: Step 2: the id parameter doesn't match with an activity
		*Step Description: 
			- Input the url http://localhost:8080/portal/intranet/activity?id=0123456789
		*Input Data: 
			
		*Expected Outcome: 
			The portlet displays the message : Activity not found.*/ 
		info("The portlet displays the message : Activity not found");
		driver.get(baseUrl+"/intranet/activity?id=0123456789");
		notAct.verifyActivityNotFound();
 	}

	/**
	*<li> Case ID:117548.</li>
	*<li> Test Case Name: Check title page when Activity Viewer displays an activity.</li>
	*<li> Pre-Condition: - User A is friend with User B
	- User A post an activity named : "I like Platform 4.0!"
	- User B likes an activity of User A
	- An email notifications is sent to User A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckTitlePageWhenActivityViewerDisplaysAnActivity() {
		info("Test 4: Check title page when Activity Viewer displays an activity");
		/*Step Number: 1
		*Step Name: Step1: Check email notification
		*Step Description: 
			- Connect to the Platform with User A
			- Check mailbox of User A and click [View the full discussion] in the email notification
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirected to the Activity Viewer portlet
			- The activity is displayed*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB comments in UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);

		/*Step number: 2
		*Step Name: Step2: Check title page
		*Step Description: 
			- Check the title of the page
		*Input Data: 
			
		*Expected Outcome: 
			- The html page title displayed the activity title. 
			- The format is : Activity : $ACTIVITY_TITLE where $ACTIVITY_TITLE is : "I like Platform 4.0!"*/ 
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
		String expectTitle ="Activity: "+activity;
		info("expect Title as:"+expectTitle);

		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
		notAct.verifyTitlePage(expectTitle);
        emailNot.closeChildBrowsers(parentWindow);

 	}

	/**
	*<li> Case ID:117587.</li>
	*<li> Test Case Name: The Activity viewer portlet is accessible 
	*to any authenticated user under the /activity uri.</li>
	*<li> Pre-Condition: Log
	-in with user which has no access right to this this link 
	http://localhost:8080.org/portal/intranet/activity?id=[id value]</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_TheActivityViewerPortletIsAccessibleToAnyAuthenticatedUserUnderTheActivityUri() {
		info("Test 5: The Activity viewer portlet is accessible to any authenticated user under the /activity uri");
		/*Step Number: 1
		*Step Name: Step 1: Connect with any authenticated user and check uri of the portlet
		*Step Description: 
			- Login to the platform
			- Access to http://localhost:8080.org/portal/intranet/activity?id=[id value]
		*Input Data: 
			
		*Expected Outcome: 
			- The portlet is displayed
			- 
			- The portlet displays a simple message : Activity not found.*/ 
		
		info("Create 3 users for testing");
		createNewUser(3);
		
		info("John add UserA to administration group");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup("Platform/Administration");
		userAndGroup.addUsersToGroup(arrayUser.get(0),"*",false, true);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB comments in UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,activity);
		emailNot.getAllChildWindows();
		
		info("Get activity url of the button");
		String getUrl=waitForAndGetElement(emailNot.ELEMENT_GMAIL_VIEW_FULL_BTN).
				getAttribute("href").toString();
        emailNot.closeChildBrowsers(parentWindow);
        switchToParentWindow();
        
        driver.get(getUrl);
		String actualUrl=driver.getCurrentUrl();
		info("Activity url:"+actualUrl);
        
		info("Change premission of Activity portlet");
		String removeGroup="/platform/users";
		String addedGroup="/platform/administrators";
		String groupPath="Platform/Administration";
		navTool.goToEditLayout();
		pgEditor.goToEditPortlet("User Activity Stream");
		edPortlet.goToAccessPermissionTab();
		edPortlet.deleteGroupPermission(removeGroup);
		edPortlet.addPremission(groupPath, "*",addedGroup);
		edPortlet.saveCloseChange(true);
		pgEditor.finishEditLayout();
		
		info("User C login");
		switchToParentWindow();
		magAc.signIn(arrayUser.get(2), password);
		Utils.pause(3000);
		
		driver.get(actualUrl);
		notAct.verifyActivityNotFound();
		
 	}

	/**
	*<li> Case ID:117588.</li>
	*<li> Test Case Name: The comment id is passed to the page of the portlet.</li>
	*<li> Pre-Condition: - User A posts an activity on User B activity stream.
	- User B receives a notification email and a link to the activity (with an id)</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_TheCommentIdIsPassedToThePageOfThePortlet() {
		info("Test 6: The comment id is passed to the page of the portlet");
		/*Step Number: 1
		*Step Name: Step 1: Check email notification to open the Activity Viewer page
		*Step Description: 
			- Connect to the platform with user B
			- Check mailbox of the user B and click [View the full discussion] of the notification email
		*Input Data: 
			
		*Expected Outcome: 
			The user is redirected to the Activity View page*/

		/*Step number: 2
		*Step Name: Step 2: The id parameter is passed
		*Step Description: 
			- Check URL of the activity viewer page
		*Input Data: 
			
		*Expected Outcome: 
			- The identifier of the activity to display os passed as a query param id to the page.
			Example : http://localhost:8080.org/portal/intranet/activity?id=88ed9a9rcd5f*/ 
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
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B add an activity on User A's stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,activity);
		emailNot.getAllChildWindows();
		info("Get activity url of the button");
		String getUrl=waitForAndGetElement(emailNot.ELEMENT_GMAIL_VIEW_FULL_BTN).
				getAttribute("href").toString();
        emailNot.closeChildBrowsers(parentWindow);
        
        switchToParentWindow();
        driver.get(getUrl);
		String actualUrl=driver.getCurrentUrl();
		
		info("Activity url:"+actualUrl);
        
        String[] splitUrl=actualUrl.split("=");
        info("split Url 0:"+splitUrl[0]);
        info("split Url 1:"+splitUrl[1]);
		String url_format=baseUrl+"/intranet/activity?id";
		if(!splitUrl[1].isEmpty()) assert true;
		else assert false:"Activity url's id format is not correct";
		if(splitUrl[0].equals(url_format)) assert true;
		else assert false:"Activity url format is not correct";
		
		

 	}

	/**
	*<li> Case ID:117589.</li>
	*<li> Test Case Name: The comment input area is displayed at the bottom of the comments.</li>
	*<li> Pre-Condition: - User A is friend with User B
	- User A posts an activity on User B Activity Stream
	- User B received an email notifications</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_TheCommentInputAreaIsDisplayedAtTheBottomOfTheComments() {
		info("Test 7: The comment input area is displayed at the bottom of the comments");
		/*Step Number: 1
		*Step Name: Step 1 : check email notifications
		*Step Description: 
			- Connect to the platform with User B
			- Check mailbox of User B and click [Reply] in order to reply to the activity of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- The Activity Viewer opens*/

		/*Step number: 2
		*Step Name: Step 2 : Check comment input area
		*Step Description: 
			- Check the activity's display
		*Input Data: 
			
		*Expected Outcome: 
			- The comment input area is displayed at the bottom of the comments
			- The focus is automatically set in the comment input area and the user can start typing right away.*/ 
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
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B add an activity on User A's stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(arrayUser.get(0));
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(2);
		String inputComment=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,activity);
		emailNot.goToDetailEmailNoti(titleEmail, fullName,activity);
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
        notAct.reply(inputComment);
        emailNot.closeChildBrowsers(parentWindow);
 	}

	/**
	*<li> Case ID:117592.</li>
	*<li> Test Case Name: View Activity of question via email notification.</li>
	*<li> Pre-Condition: - User A create a question on Answer page
	- User B access to this question and answer this question
	- An email notification of comment activity is sent to user A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_ViewActivityOfQuestionViaEmailNotification() {
		info("Test 8: View Activity of question via email notification");
		/*Step Number: 1
		*Step Name: Open the activity
		*Step Description: 
			From this email, click on [View the full discussion] button
		*Input Data: 
			
		*Expected Outcome: 
			- The corresponding activity displays with the URL: 
			http://localhost:8080/portal/intranet/activity?id=[ActivityID]
			- Ensure that the content must include: 
			+ Only one activity displays
			* the author* the author's avatar
			* * the space (optional)
			* * the type (optional)
			* * the featured content: 
			* Question, detail of question, 
			* number of answer, number of comment, rate, points, 
			* * the Action bar (Comment and Like links + custom actions)
			* * the like section* the comment section*/
		
		/*Step number: 4
		*Step Name: Check view rate
		*Step Description: 
			- On this activity, hover the rate area (stars)
		*Input Data: 
			
		*Expected Outcome: 
			- The "average" rate is displayed
			- If this question is not rated, the text "not value yet"*/ 
		
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
		
		info("User A create a question in Aswser page");
		String titleQuestion=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String contentQuestion=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAnswer();
		questionMg.goToSubmitQuestion();
		questionMg.inputDataToQuestionForm(titleQuestion,contentQuestion,null,null);
		questionMg.saveCancelSubmitQuestion(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("User B access to the question and answser");
		String answerContent=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAnswer();
		ansHome.goToQuestion(titleQuestion);
		questionMg.goToAnswerForm(titleQuestion);
		answerMg.inputDataToAnswer(answerContent,null,null,null);
		answerMg.saveCancelAnswerForm(true);
		answerMg.verifyAnswerAdded(answerContent);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(1);
		String actComment=actCommentData.getMessageByArrayTypeRandom(1);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
        notAct.checkFormatDetailActivity(false,titleQuestion);
        notAct.verifyAnswerActivity("0","1","");
        notAct.checkCommentExpand(actComment,answerContent,false);
        emailNot.closeChildBrowsers(parentWindow);
        switchToParentWindow();

		/*Step number: 2
		*Step Name: Check the Answer action
		*Step Description: 
			On this activity, click on Answer link
		*Input Data: 
			
		*Expected Outcome: 
			- The browser navigates to the corresponding question detail and the answer popup displays
			- Ensure that user can add the answer successfully or cancelling the adding answer*/
        hp.goToHomePage();
        hpAct.goToReplyAnswerQuestion(titleQuestion);
        answerMg.saveCancelAnswerForm(false);

		/*Step number: 3
		*Step Name: Check the View action
		*Step Description: 
			- On this activity, hover on the comment of this activity
			- Click on View icon
		*Input Data: 
			
		*Expected Outcome: 
			The browser navigates to corresponding question detail on Answer page*/
        hp.goToHomePage();
        hpAct.viewComment(titleQuestion,actComment,answerContent);
        info("the question in Answer page is shown");
    	waitForAndGetElement(questionMg.ELEMENT_QUESTION_ANSWER_LINK.replace("$question",titleQuestion));
    	
 	}

	/**
	*<li> Case ID:117594.</li>
	*<li> Test Case Name: View Activity with image file via email notification.</li>
	*<li> Pre-Condition: - Set the email configuration in exo.properties file
	- Set the email notification for Someone likes one of my activities
	- userA is friend of userB
	- userA post an activity with image uploading
	- userB likes this activity of userA
	- An email is sent to userA</li>
	*<li> Post-Condition: </li>
	 * @throws Exception 
	*/
	@Test
	public  void test09_00_ViewActivityWithImageFileViaEmailNotification() throws Exception {
		info("Test 9: View Activity with image file via email notification");
		/*Step Number: 1
		*Step Name: Check the mail information by click on "view the full discussion"
		*Step Description: 
			- Open the email of new posted from userA on step3
			- Click on "view the full discussion"
		*Input Data: 
			
		*Expected Outcome: 
			- The full activity displays correctly with linkhttp:
			//localhost:8080/portal/intranet/activity?id=[activityID]
			- Only 1 activity is displayed 
			- The activity is made out of different parts:
			* the author* the author's avatar* the type (optional)
			* * the activity message
			* * the featured content: 
			* thumbnail of file, file name, size, View, Download, Edit
			* * the Action bar (Comment and Like links + custom actions)
			* * the like section* the comment section*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String nameDrive = siteExDrive.getSiteExpDriveByIndex(4);
		String pathData=dataTestForlderPath.getDataTestPathByIndex(1);
		String pathFolder=siteExPath.getSiteExpPathByIndex(10);
		String nameFile=attFileData.getAttachFileByArrayTypeRandom(26);
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.uploadAndShareFileActivity(nameDrive, pathFolder, pathData, nameFile,activity);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
        notAct.checkFormatDetailActivity(false,activity);
        notAct.verifyActivityFileUpload(nameFile);
       
        
        
		/*Step number: 2
		*Step Name: Check the view button
		*Step Description: 
			- On the activity, Click on View button of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
        
        notAct.viewDetailActivityByViewBtn();
        waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
        docPreview.closeByClickBackground();
        
		/*Step number: 3
		*Step Name: Check click on the image
		*Step Description: 
			- On the activity, Click on the thumbnail of this file button of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
        notAct.viewDetailActivityByImageThumbnail();
        waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
        docPreview.closeByClickBackground();

		/*Step number: 4
		*Step Name: Check Download action
		*Step Description: 
			- On this activity, click on [Download] button
		*Input Data: 
			
		*Expected Outcome: 
			The file is downloaded successfully*/
        info("emailNot.getDownloadFileUrl():"+notAct.getDownloadFileUrl());
        String downloadUrl=notAct.getDownloadFileUrl();
        downloadControl.downloadFile(downloadUrl);
        emailNot.closeChildBrowsers(parentWindow);
 	}
	
	/**
	*<li> Case ID:117594.</li>
	*<li> Test Case Name: View Activity with image file via email notification.</li>
	*<li> Pre-Condition: - Set the email configuration in exo.properties file
	- Set the email notification for Someone likes one of my activities
	- userA is friend of userB
	- userA post an activity with image uploading
	- userB likes this activity of userA
	- An email is sent to userA</li>
	*<li> Post-Condition: </li>
	 * @throws Exception 
	*/
	@Test
	public  void test09_01_ViewActivityWithImageFileViaEmailNotification() throws Exception {
		info("Test 9: View Activity with image file via email notification");
		/*Step number: 1
		*Step Name: Check the mail information by click on "reply"
		*Step Description: 
			- Open the email of new posted from userA on step3
			- Click on "reply"
		*Input Data: 
			
		*Expected Outcome: 
			- The full activity displays correctly with 
			linkhttp://localhost:8080/portal/intranet/activity?id=19a116b47f000101030f1dbbb6d97dc1&comment=1
			- Only 1 activity is displayed 
			- The activity is made out of different parts:
			* the author* the author's avatar* the space (optional)
			* * the type (optional)* the activity message* the featured content: 
			* thumbnail of file, file name, size, View, Download, Edit
			* * the Action bar (Comment and Like links + custom actions)
			* * the like section* the comment section* the comment text box and comment button*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String nameDrive = siteExDrive.getSiteExpDriveByIndex(4);
		String pathData=dataTestForlderPath.getDataTestPathByIndex(1);
		String pathFolder=siteExPath.getSiteExpPathByIndex(10);
		String nameFile=attFileData.getAttachFileByArrayTypeRandom(26);
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.uploadAndShareFileActivity(nameDrive, pathFolder, pathData, nameFile,activity);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
        notAct.checkFormatDetailActivity(false,activity);
        notAct.verifyActivityFileUpload(nameFile);

		/*Step number: 2
		*Step Name: Check the view button
		*Step Description: 
			- On the activity, Click on View button of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
        notAct.viewDetailActivityByViewBtn();
        waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
        docPreview.closeByClickBackground();

		/*Step number: 3
		*Step Name: Check click on the image
		*Step Description: 
			- On the activity, Click on the thumbnail of this file button of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
        notAct.viewDetailActivityByImageThumbnail();
        waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
        docPreview.closeByClickBackground();

		/*Step number: 4
		*Step Name: Check Download action
		*Step Description: 
			- On this activity, click on [Download] button
		*Input Data: 
			
		*Expected Outcome: 
			The file is downloaded successfully*/
        info("emailNot.getDownloadFileUrl():"+notAct.getDownloadFileUrl());
        String downloadUrl=notAct.getDownloadFileUrl();
        downloadControl.downloadFile(downloadUrl);
        emailNot.closeChildBrowsers(parentWindow);
 	}

	/**
	*<li> Case ID:117595.</li>
	*<li> Test Case Name: View Activity with office file via email notification.</li>
	*<li> Pre-Condition: - Set the email configuration in exo.properties file
	- Set the email notification for Someone likes one of my activities
	- userA is friend of userB
	- userA post an activity with uploading office (doc) file
	- userB likes this activity of userA
	- An email is sent to userA</li>
	*<li> Post-Condition: </li>
	 * @throws Exception 
	*/
	@Test
	public  void test10_00_ViewActivityWithOfficeFileViaEmailNotification() throws Exception {
		info("Test 10 View Activity with office file via email notification");
		/*Step Number: 1
		*Step Name: Check the mail information by click on "view the full discussion"
		*Step Description: 
			- Open the email of new posted from userA on step3
			- Click on "view the full discussion"
		*Input Data: 
			
		*Expected Outcome: 
			- The full activity displays correctly with linkhttp://localhost:8080/portal/intranet/activity?id=19a116b47f000101030f1dbbb6d97dc1
			- Only 1 activity is displayed 
			- The activity is made out of different parts:* the author* the author's avatar* the space (optional)* the type (optional)* the activity message* the featured content: thumbnail of file, file name, size, View, Download, Edit* the Action bar (Comment and Like links + custom actions)* the like section* the comment section*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String nameDrive = siteExDrive.getSiteExpDriveByIndex(4);
		String pathData=dataTestForlderPath.getDataTestPathByIndex(1);
		String pathFolder=siteExPath.getSiteExpPathByIndex(10);
		String nameFile=attFileData.getAttachFileByArrayTypeRandom(2);
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.uploadAndShareFileActivity(nameDrive, pathFolder, pathData, nameFile,activity);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
        notAct.checkFormatDetailActivity(false,activity);
        notAct.verifyActivityFileUpload(nameFile);
		
		/*Step number: 2
		*Step Name: Check the view button
		*Step Description: 
			- On the activity, Click on View button of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
        notAct.viewDetailActivityByViewBtn();
        waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
        docPreview.closeByClickBackground();

		/*Step number: 3
		*Step Name: Check click on the image
		*Step Description: 
			- On the activity,the thumbnail of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
        notAct.viewDetailActivityByImageThumbnail();
        waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
        docPreview.closeByClickBackground();

		/*Step number: 4
		*Step Name: Check Download action
		*Step Description: 
			- On this activity, click on [Download] button
		*Input Data: 
			
		*Expected Outcome: 
			The file is downloaded successfully*/
        info("emailNot.getDownloadFileUrl():"+notAct.getDownloadFileUrl());
        String downloadUrl=notAct.getDownloadFileUrl();
        downloadControl.downloadFile(downloadUrl);
        emailNot.closeChildBrowsers(parentWindow);
 	}
	
	/**
	*<li> Case ID:117595.</li>
	*<li> Test Case Name: View Activity with office file via email notification.</li>
	*<li> Pre-Condition: - Set the email configuration in exo.properties file
	- Set the email notification for Someone likes one of my activities
	- userA is friend of userB
	- userA post an activity with uploading office (doc) file
	- userB likes this activity of userA
	- An email is sent to userA</li>
	*<li> Post-Condition: </li>
	 * @throws Exception 
	*/
	@Test
	public  void test10_01_ViewActivityWithOfficeFileViaEmailNotification() throws Exception {
		info("Test 10 View Activity with office file via email notification");
		
		/*Step number: 1
		*Step Name: Check the mail information by click on "reply"
		*Step Description: 
			- Open the email of new posted from userA on step3
			- Click on "reply"
		*Input Data: 
			
		*Expected Outcome: 
			- The full activity displays correctly with linkhttp://localhost:8080/portal/intranet/activity?id=19a116b47f000101030f1dbbb6d97dc11&comment=1*/

		/*Step number: 2
		*Step Name: Check the view button
		*Step Description: 
			- On the activity, Click on View button of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String nameDrive = siteExDrive.getSiteExpDriveByIndex(4);
		String pathData=dataTestForlderPath.getDataTestPathByIndex(1);
		String pathFolder=siteExPath.getSiteExpPathByIndex(10);
		String nameFile=attFileData.getAttachFileByArrayTypeRandom(2);
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.uploadAndShareFileActivity(nameDrive, pathFolder, pathData, nameFile,activity);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,activity);
		notAct.verifyActivityFileUpload(nameFile);
        
		notAct.viewDetailActivityByViewBtn();
        waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
        docPreview.closeByClickBackground();
        
		/*Step number: 3
		*Step Name: Check click on the image
		*Step Description: 
			- On the activity,the thumbnail of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
        
        notAct.viewDetailActivityByImageThumbnail();
        waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
        docPreview.closeByClickBackground();

		/*Step number: 4
		*Step Name: Check Download action
		*Step Description: 
			- On this activity, click on [Download] button
		*Input Data: 
			
		*Expected Outcome: 
			The file is downloaded successfully*/
        info("emailNot.getDownloadFileUrl():"+notAct.getDownloadFileUrl());
        String downloadUrl=notAct.getDownloadFileUrl();
        downloadControl.downloadFile(downloadUrl);
        emailNot.closeChildBrowsers(parentWindow);
 	}

	/**
	*<li> Case ID:117596.</li>
	*<li> Test Case Name: View Activity with pdf file via email notification.</li>
	*<li> Pre-Condition: - Set the email configuration in exo.properties file
	- Set the email notification for Someone likes one of my activities
	- userA is friend of userB
	- userA post an activity with uploading a pdf file
	- userB likes this activity of userA
	- An email is sent to userA</li>
	*<li> Post-Condition: </li>
	 * @throws Exception 
	*/
	@Test
	public  void test11_00_ViewActivityWithPdfFileViaEmailNotification() throws Exception {
		info("Test 11 View Activity with pdf file via email notification");
		/*Step Number: 1
		*Step Name: Check the mail information by click on "view the full discussion"
		*Step Description: 
			- Open the email of new posted from userA on step3
			- Click on "view the full discussion"
		*Input Data: 
			
		*Expected Outcome: 
			- The full activity displays correctly with linkhttp://localhost:8080/portal/intranet/activity?id=19a116b47f000101030f1dbbb6d97dc1
			- Only 1 activity is displayed 
			- The activity is made out of different parts:* the author* the author's avatar* the type (optional)* the activity message* the featured content: thumbnail of file, file name, size, View, Download, Edit* the Action bar (Comment and Like links + custom actions)* the like section* the comment section*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String nameDrive = siteExDrive.getSiteExpDriveByIndex(4);
		String pathData=dataTestForlderPath.getDataTestPathByIndex(1);
		String pathFolder=siteExPath.getSiteExpPathByIndex(10);
		String nameFile=attFileData.getAttachFileByArrayTypeRandom(1);
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.uploadAndShareFileActivity(nameDrive, pathFolder, pathData, nameFile,activity);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,activity);
		notAct.verifyActivityFileUpload(nameFile);
	
		/*Step number: 2
		*Step Name: Check the view button
		*Step Description: 
			- On the activity, Click on View button of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
		notAct.viewDetailActivityByViewBtn();
        waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
        docPreview.closeByClickBackground();

		/*Step number: 3
		*Step Name: Check click on the image
		*Step Description: 
			- On the activity, the thumbnail of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
        notAct.viewDetailActivityByImageThumbnail();
        waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
        docPreview.closeByClickBackground();

		/*Step number: 4
		*Step Name: Check Download action
		*Step Description: 
			- On this activity, click on [Download] button
		*Input Data: 
			
		*Expected Outcome: 
			The file is downloaded successfully*/
        info("emailNot.getDownloadFileUrl():"+notAct.getDownloadFileUrl());
        String downloadUrl=notAct.getDownloadFileUrl();
        downloadControl.downloadFile(downloadUrl);
        emailNot.closeChildBrowsers(parentWindow);
 	}
	
	/**
	*<li> Case ID:117596.</li>
	*<li> Test Case Name: View Activity with pdf file via email notification.</li>
	*<li> Pre-Condition: - Set the email configuration in exo.properties file
	- Set the email notification for Someone likes one of my activities
	- userA is friend of userB
	- userA post an activity with uploading a pdf file
	- userB likes this activity of userA
	- An email is sent to userA</li>
	*<li> Post-Condition: </li>
	 * @throws Exception 
	*/
	@Test
	public  void test11_01_ViewActivityWithPdfFileViaEmailNotification() throws Exception {
		info("Test 11 View Activity with pdf file via email notification");
		/*Step number: 1
		*Step Name: Check the mail information by click on "reply"
		*Step Description: 
			- Open the email of new posted from userA on step3
			- Click on "reply"
		*Input Data: 
			
		*Expected Outcome: 
			- The full activity displays correctly with linkhttp://localhost:8080/portal/intranet/activity?id=19a116b47f000101030f1dbbb6d97dc11&comment=1*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String nameDrive = siteExDrive.getSiteExpDriveByIndex(4);
		String pathData=dataTestForlderPath.getDataTestPathByIndex(1);
		String pathFolder=siteExPath.getSiteExpPathByIndex(10);
		String nameFile=attFileData.getAttachFileByArrayTypeRandom(1);
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.uploadAndShareFileActivity(nameDrive, pathFolder, pathData, nameFile,activity);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,activity);
		notAct.verifyActivityFileUpload(nameFile);
        
		/*Step number: 2
		*Step Name: Check the view button
		*Step Description: 
			- On the activity, Click on View button of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
        
		 notAct.viewDetailActivityByViewBtn();
         waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
         docPreview.closeByClickBackground();

		/*Step number: 3
		*Step Name: Check click on the image
		*Step Description: 
			- On the activity, the thumbnail of the activity
		*Input Data: 
			
		*Expected Outcome: 
			The preview of pdf file displays successfully*/
         
         notAct.viewDetailActivityByImageThumbnail();
         waitForAndGetElement(docPreview.ELEMENT_PREVIEW_MODE);
         docPreview.closeByClickBackground();

		/*Step number: 4
		*Step Name: Check Download action
		*Step Description: 
			- On this activity, click on [Download] button
		*Input Data: 
			
		*Expected Outcome: 
			The file is downloaded successfully*/
		info("emailNot.getDownloadFileUrl():"+notAct.getDownloadFileUrl());
        String downloadUrl=notAct.getDownloadFileUrl();
        downloadControl.downloadFile(downloadUrl);
        emailNot.closeChildBrowsers(parentWindow);
 	}


	/**
	*<li> Case ID:117597.</li>
	*<li> Test Case Name: View Activity with Topic via email notification.</li>
	*<li> Pre-Condition: - User A checked "Someone likes one of my activities" and save this setting in the notification settings
	- User A is friend with User B
	- User A starts a topic in the forum portlet
	- An activity is posted in the activity stream
	- User B likes the activity of User A
	- An email notifications is sent to User A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_00_ViewActivityWithTopicViaEmailNotification() {
		info("Test 12 View Activity with Topic via email notification");
		/*Step Number: 1
		*Step Name: Check the [View the full discussion]
		*Step Description: 
			- User A opens the email of topic activity 
			- Click on [View the full discussion] button
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is opened with the link format: http://localhost:8080/portal/intranet/activity?id=[activity id]
			- Only 1 activity is displayed 
			- The activity is made out of different parts:
			* the author* the author's avatar
			* * the space (optional)
			* * the type (optional)
			* * the featured content: Topic name, topic message, Reply, View latest reply
			* * the Action bar (Comment and Like links + custom actions)
			* * the like section* the comment section*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("John add UserA to administration group");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup("Platform/Administration");
		userAndGroup.addUsersToGroup(arrayUser.get(0),"*",false, true);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A start a new forum");
		String titleCategory=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titleForum=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String desForum=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titleTopic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String desTopic=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("go to Forum home page");
		hp.goToForum();
		info("Add a category");
		forumCatMag.addCategorySimple(titleCategory,"", titleCategory);
		info("Add a forum in the category");
		forumMag.addForumSimple(titleForum,"",desForum);
		info("Add a new topic");
		forumMag.goToStartTopic();
		foTopic.startTopic(titleTopic, desTopic, "", "");
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(titleTopic);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,titleTopic);
        notAct.verifyActivityTopic(desTopic, "");
        
		/*Step number: 3
		*Step Name: Check the [Reply] action
		*Step Description: 
			- On this activity, click on [Reply] button
		*Input Data: 
			
		*Expected Outcome: 
			- Ensure the that the browser navigates to the corresponding topic, the [new post] pop
			-up displays
			- And user can add new post successfully*/
        String replyTitleTopic=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String replyDesTopic=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        notAct.goToReplyBtnTopicActivity();
        notAct.replyTopic(replyTitleTopic,replyDesTopic);

		/*Step number: 4
		*Step Name: Check the [View the latest reply] action
		*Step Description: 
			- On this activity, click on [view the latest reply] button
		*Input Data: 
			
		*Expected Outcome: 
			- Ensure the that the browser navigates to the corresponding topic, and focuses on the latest post*/ 
        emailNot.goToPreviousPage();
        notAct.goToViewLastReplyBtnTopicActivity();
        notAct.verifyActivityTopic(replyTitleTopic, replyDesTopic);
        emailNot.closeChildBrowsers(parentWindow);
 	}

	/**
	*<li> Case ID:117597.</li>
	*<li> Test Case Name: View Activity with Topic via email notification.</li>
	*<li> Pre-Condition: - User A checked "Someone likes one of my activities" and save this setting in the notification settings
	- User A is friend with User B
	- User A starts a topic in the forum portlet
	- An activity is posted in the activity stream
	- User B likes the activity of User A
	- An email notifications is sent to User A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_01_ViewActivityWithTopicViaEmailNotification() {
		info("Test 12 View Activity with Topic via email notification");
		/*Step number: 2
		*Step Name: Check the [reply] button
		*Step Description: 
			- User A opens the email of topic activity 
			- Click on [Reply] button
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is opened with the link format: 
			http://localhost:8080/portal/intranet/activity?id=[activity id]&comment=1
			- Only 1 activity is displayed 
			- The activity is made out of different parts:
			* the author* the author's avatar
			* * the space (optional)* the type (optional)
			* * the featured content: Topic name, topic message, Reply, View latest reply
			* * the Action bar (Comment and Like links + custom actions)
			* * the like section
			* * the comment section and comment textbox, the cursor focuses in the comment textbox*/

		info("Create 2 users for testing");
		createNewUser(2);
		
		info("John add UserA to administration group");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup("Platform/Administration");
		userAndGroup.addUsersToGroup(arrayUser.get(0),"*",false, true);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A start a new forum");
		String titleCategory=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titleForum=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String desForum=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String titleTopic = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String desTopic=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("go to Forum home page");
		hp.goToForum();
		info("Add a category");
		forumCatMag.addCategorySimple(titleCategory,"", titleCategory);
		info("Add a forum in the category");
		forumMag.addForumSimple(titleForum,"",desForum);
		info("Add a new topic");
		forumMag.goToStartTopic();
		foTopic.startTopic(titleTopic, desTopic, "", "");
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(titleTopic);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
        notAct.checkFormatDetailActivity(false,titleTopic);
        notAct.verifyActivityTopic(desTopic, "");
		
		/*Step number: 3
		*Step Name: Check the [Reply] action
		*Step Description: 
			- On this activity, click on [Reply] button
		*Input Data: 
			
		*Expected Outcome: 
			- Ensure the that the browser navigates to the corresponding topic, the [new post] pop
			-up displays
			- And user can add new post successfully*/
        String replyTitleTopic=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        String replyDesTopic=txData.getContentByArrayTypeRandom(1)+getRandomNumber();
        notAct.goToReplyBtnTopicActivity();
        notAct.replyTopic(replyTitleTopic,replyDesTopic);

		/*Step number: 4
		*Step Name: Check the [View the latest reply] action
		*Step Description: 
			- On this activity, click on [view the latest reply] button
		*Input Data: 
			
		*Expected Outcome: 
			- Ensure the that the browser navigates to the corresponding topic, and focuses on the latest post*/ 
        emailNot.goToPreviousPage();
        notAct.goToViewLastReplyBtnTopicActivity();
        notAct.verifyActivityTopic(replyTitleTopic, replyDesTopic);
        emailNot.closeChildBrowsers(parentWindow);
 	}
	/**
	*<li> Case ID:117598.</li>
	*<li> Test Case Name: View activity with Wiki page via email notification.</li>
	*<li> Pre-Condition: - User A is friend with User B
	- User A creates and edits a wiki page in wiki portlet
	- An activity is posted in the activity stream
	- User B like this activity
	- An email notifications is sent to User A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_00_ViewActivityWithWikiPageViaEmailNotification() {
		info("Test 13 View activity with Wiki page via email notification");
		/*Step Number: 1
		*Step Name: Open the activity via email notification
		*Step Description: 
			- User A opens the email of topic activity 
			- Click [View the full discussion] button
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is opened with the link format: http://localhost:8080/portal/intranet/activity?id=[activityID]
			- Only 1 activity is displayed 
			- The activity is made out of different parts:
			* the author
			* * the author's avatar
			* * the space (optional)
			* * the type (optional)
			* * the featured content: wiki title, wiki page, version, 
			* * the Action bar (Comment and Like links + custom actions)
			* * the like section* the comment section*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("John add UserA to administration group");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup("Platform/Administration");
		userAndGroup.addUsersToGroup(arrayUser.get(0),"*",false, true);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A create a new wiki page");
		String wiki = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToWiki();
		Utils.pause(3000);
		wHome.goToAddBlankPage();
		Utils.pause(3000);
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wiki,des);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.
				replace("${name}",wiki),2000,1);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(wiki);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,wiki);
		notAct.verifyActivityWiki(des);

		/*Step number: 2
		*Step Name: Check the wiki page navigation
		*Step Description: 
			- On this activity, click on title of this wiki page
		*Input Data: 
			
		*Expected Outcome: 
			The browser navigates to the corresponding wiki page in wiki portlet*/
		notAct.goToDetailWikiPage(wiki);
        emailNot.closeChildBrowsers(parentWindow);
 	}
	/**
	*<li> Case ID:117598.</li>
	*<li> Test Case Name: View activity with Wiki page via email notification.</li>
	*<li> Pre-Condition: - User A is friend with User B
	- User A creates and edits a wiki page in wiki portlet
	- An activity is posted in the activity stream
	- User B like this activity
	- An email notifications is sent to User A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_01_ViewActivityWithWikiPageViaEmailNotification() {
		info("Test 13 View activity with Wiki page via email notification");
		/*Step Number: 1
		*Step Name: Open the activity via email notification
		*Step Description: 
			- User A opens the email of topic activity 
			- Click [Reply] button
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is opened with the link format: http://localhost:8080/portal/intranet/activity?id=[activityID]
			- Only 1 activity is displayed 
			- The activity is made out of different parts:
			* the author
			* * the author's avatar
			* * the space (optional)
			* * the type (optional)
			* * the featured content: wiki title, wiki page, version, 
			* * the Action bar (Comment and Like links + custom actions)
			* * the like section* the comment section*/
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("John add UserA to administration group");
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.selectGroup("Platform/Administration");
		userAndGroup.addUsersToGroup(arrayUser.get(0),"*",false, true);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A create a new wiki page");
		String wiki = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToWiki();
		Utils.pause(3000);
		wHome.goToAddBlankPage();
		Utils.pause(3000);
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wiki,des);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.
				replace("${name}",wiki),2000,1);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB likes UserA's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(wiki);
		
		info("Log in with User A");
		magAc.signOut();
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(3);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnReplyBtnActivity();
		emailNot.getAllChildWindows();
		notAct.checkFormatDetailActivity(false,wiki);
		notAct.verifyActivityWiki(des);

		/*Step number: 2
		*Step Name: Check the wiki page navigation
		*Step Description: 
			- On this activity, click on title of this wiki page
		*Input Data: 
			
		*Expected Outcome: 
			The browser navigates to the corresponding wiki page in wiki portlet*/
		notAct.goToDetailWikiPage(wiki);
        emailNot.closeChildBrowsers(parentWindow);
 	}

	/**
	*<li> Case ID:117599.</li>
	*<li> Test Case Name: When a comment is designed through the anchor, the browser scroll on it.</li>
	*<li> Pre-Condition: - User A comments on User B activity
	- An email notification is send to User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_WhenACommentIsDesignedThroughTheAnchorTheBrowserScrollOnIt() {
		info("Test 14 When a comment is designed through the anchor, the browser scroll on it");
		/*Step Number: 1
		*Step Name: Step1 : Click link in the email
		*Step Description: 
			- Login as user B 
			- Open email notification and click [View the full discussion]
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirected to the portal and the activity is displayed in the activity viewer.
			- The url contain an comment anchor*/
		
		info("Create 2 users for testing");
		createNewUser(2);
		
		info("User A login");
		magAc.signIn(arrayUser.get(0), password);
		Utils.pause(3000);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_email);
		
		info("Change email address");
		navTool.goToMyProfile();
		userProPage.goToEditProfile();
		userProPage.updateBasicInformation("","",EMAIL_ADDRESS1);
		userProPage.saveCancelUpdateInfo(true);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(arrayUser.get(1));
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(arrayUser.get(1), password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(arrayUser.get(0));
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Log in with User A");
		magAc.signIn(arrayUser.get(0), password);
		
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		
		String fullName=arrayUser.get(1)+" "+arrayUser.get(1);
		String titleEmail=notiEmailData.getContentByArrayTypeRandom(1);
	    
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		Utils.pause(10000);
		emailNot.getAllChildWindows();
		emailNot.verifyPresentEmailActivityNotifications(titleEmail,fullName,"");
		emailNot.goToDetailEmailNoti(titleEmail, fullName,"");
		emailNot.getAllChildWindows();
		emailNot.clickOnViewDiscussBtn();
		emailNot.getAllChildWindows();

		/*Step number: 2
		*Step Name: Step2 : Check the focus on the activity
		*Step Description: 
			- Check the activity
		*Input Data: 
			
		*Expected Outcome: 
			- The browser have scrolled on the specific comment 
			- The comment of the User A should be highlighted in a different color*/ 
		
		notAct.checkCommentExpand(comment, true);
		emailNot.closeChildBrowsers(parentWindow);
 	}}