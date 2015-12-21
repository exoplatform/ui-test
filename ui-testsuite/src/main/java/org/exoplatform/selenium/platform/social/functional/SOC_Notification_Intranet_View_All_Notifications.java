package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.Test;


	public class SOC_Notification_Intranet_View_All_Notifications extends SOC_TestConfig2{

	/**
	*<li> Case ID:125167.</li>
	*<li> Test Case Name: Check View All page.</li>
	*<li> Pre-Condition: The user has several notifications 
	- Like
	- Comment
	- Connection request
	- Post in My stream</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckViewAllPage() {
		info("Test 1: Check View All page");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All page
		*Step Description: 
			- Login
			- Click the notifications icon in the top navigation
			- Click View All button
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirected to the View All page*/
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		myNoti.enableNotification(myNotiType.AS_Post_intranet);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(username2, password);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.acceptRqConnection(username1);
		
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		info("Like UserA's activity");
		hpAct.likeActivity(activity);
		info("Add a comment to UserA's activity");
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Add comment to Comment list");
		comments.add(comment);
		
		info("Go to UserA's profile page");
		hp.goToFriendProfilePage(username1);
		userProPage.goToActivity();
		
		info("User B add an activity in UserA's activity stream");
		String myActivity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hpAct.addActivity(myActivity,null);
		hpAct.checkActivity(myActivity);
		
		info("User C login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User C sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("The user is redirected to the View All page");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		

		/*Step number: 2
		*Step Name: Step 2 : Check title of the page
		*Step Description: 
			- Check title of the View All page
		*Input Data: 
			
		*Expected Outcome: 
			- The title of the page isAll Notifications*/
	
		String titlePage=driver.getTitle();
		if(titlePage.equals("All Notifications")==true) assert true;
		else assert false:"the page's title is not correct";

		/*Step number: 3
		*Step Name: Step 3 : Check content of the View All page
		*Step Description: 
			- Check the content of the list
		*Input Data: 
			
		*Expected Outcome: 
			The page displays the following notifications 
			- Like
			- Comment
			- Connection request
			- Post in My stream*/
		String likeStatus=notiIntranetData.getContentByArrayTypeRandom(6);
		String commentStatus=notiIntranetData.getNotiContent(0);
		String postStatus=notiIntranetData.getContentByArrayTypeRandom(8);
		String connectStatus=notiIntranetData.getContentByArrayTypeRandom(2);
		intraNot.checkStatus(likeStatus,username2);
		intraNot.checkStatus(commentStatus,username2);
		intraNot.checkStatus(postStatus,username2);
		intraNot.checkStatus(connectStatus,username2);
		

 	}

	/**
	*<li> Case ID:125168.</li>
	*<li> Test Case Name: Check layout of read / unread notifications in View All.</li>
	*<li> Pre-Condition: The user has read and unread notifications</li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="pending")
	public  void test02_CheckLayoutOfReadUnreadNotificationsInViewAll() {
		info("Test 2: Check layout of read / unread notifications in View All");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login
			- Click the notifications icon
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- Unread notifications look different than read notification (blue background on unread notifications)*/ 

 	}

	/**
	*<li> Case ID:125169.</li>
	*<li> Test Case Name: Click a New User notification from View All page.</li>
	*<li> Pre-Condition: - User A has received a New User notification from User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_ClickANewUserNotificationFromViewAllPage() {
		info("Test 3: Click a New User notification from View All page");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
        String fullName2=username2+" "+username2;		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		magAc.signIn(username1,password);
		
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All
		*Step Description: 
			- Login with User A
			- Go to View all
		*Input Data: 
			
		*Expected Outcome: 
			- The View All page is displayed
			- The New User notification is available/displayed in the page*/
		
		info("Go to My notification");
		navTool.goToMyNotifications();
		info("Enable new user notification");
		myNoti.enableNotification(myNotiType.NewUser_intranet);
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username2, password, email2, username2, username2);
		
		this.driver.navigate().refresh();
		info("The user is redirected to the View All page");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		String connectStatus=notiIntranetData.getContentByArrayTypeRandom(11);
		intraNot.checkStatus(connectStatus,username2);

		/*Step number: 2
		*Step Name: Step 2 : Check click on Notification
		*Step Description: 
			- Click the New User notification
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirected to the profile of User B*/ 
		intraNot.goToDetailNewUserJoinIntranet(username2,false);
		waitForAndGetElement(userProPage.ELEMENT_PROFILE_TITLE.replace("${fullName}",fullName2),2000,1);

 	}

	/**
	*<li> Case ID:125170.</li>
	*<li> Test Case Name: Accept a Connection Request from View All.</li>
	*<li> Pre-Condition: User A sent a connection request to User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_AcceptAConnectionRequestFromViewAll() {
		info("Test 4: Accept a Connection Request from View All");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String fullName1=username1+" "+username1;
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All
		*Step Description: 
			- Login with User B
			- Click notifications icon in the top navigation
			- Go to View All page
		*Input Data: 
			
		*Expected Outcome: 
			- A connection request notification is displayed in the page*/
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("The user is redirected to the View All page");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		
		info("A connection request notification is displayed in the page");
		String connectStatus=notiIntranetData.getContentByArrayTypeRandom(2);
		intraNot.checkStatus(connectStatus,username1);

		/*Step number: 2
		*Step Name: Step 2 : Accept the Connection Request
		*Step Description: 
			- Click [Accept]
		*Input Data: 
			
		*Expected Outcome: 
			- The connection is approved, the 2 users are connected*/
		info("Click [Accept]");
		intraNot.acceptRqConnection(username1);
		
		info("The connection is approved, the 2 users are connected");
		hp.goToConnections();
		connMag.verifyConnection(username1,true);

		/*Step number: 3
		*Step Name: Step 3: Check the notification message
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			After clicking Accept, the notification message is updated to :$AVATARYou are connected with $USER$DATEWhere : 
			- $AVATAR is thumbnail of User A
			- $USER is User A
			- $DATE is the date of the notification*/
		info("Check format of the notification after accepted");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		String connectAcceptStatus=notiIntranetData.getContentByArrayTypeRandom(5);
		intraNot.checkStatus(connectAcceptStatus,username1);
		arrayUser.add(username1);
		arrayUser.add(username2);
		intraNot.checkAvatarInStatus(arrayUser,true);
		intraNot.checkUsers(arrayUser,true);

		/*Step number: 4
		*Step Name: Step 4 : Click the notification
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- User B is redirected to the profile of User A*/ 
		info("User B is redirected to the profile of User A");
		intraNot.goToDetailAcceptRequestConnectionUser(username1,false);
		waitForAndGetElement(userProPage.ELEMENT_PROFILE_TITLE.replace("${fullName}",fullName1),2000,1);

 	}

	/**
	*<li> Case ID:125171.</li>
	*<li> Test Case Name: Refuse a Connection Request from View All.</li>
	*<li> Pre-Condition: User A sent a connection request to User B</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_RefuseAConnectionRequestFromViewAll() {
		info("Test 5: Refuse a Connection Request from View All");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All page
		*Step Description: 
			- Login with User B
			- Click notifications icon
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- View All page is displayed
			- A connection request notification is displayed in the View All page*/
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("The user is redirected to the View All page");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		
		info("A connection request notification is displayed in the page");
		String connectStatus=notiIntranetData.getContentByArrayTypeRandom(2);
		intraNot.checkStatus(connectStatus,username1);

		/*Step number: 2
		*Step Name: Step 2 : Refuse Connection Request
		*Step Description: 
			- Click [Refuse]
		*Input Data: 
			
		*Expected Outcome: 
			- The connection is not approved, the 2 users are not connected
			- The notification message is automatically hidden from the list*/ 
		info("Click [Refuse]");
		String acceptStatus=notiIntranetData.getContentByArrayTypeRandom(5);
		intraNot.refuseRqConnection(username1);
		intraNot.checkNotPresentStatus(connectStatus,username1);
		intraNot.checkNotPresentStatus(acceptStatus,username1);
		
		info("The connection isnot approved, the 2 users are not connected");
		hp.goToConnections();
		connMag.verifyConnection(username1,false);

 	}

	/**
	*<li> Case ID:125172.</li>
	*<li> Test Case Name: Accept a Space Invitation from View All.</li>
	*<li> Pre-Condition: - User A is manager of the space 1
	- User A invite User B to join the space 1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_AcceptASpaceInvitationFromViewAll() {
		info("Test 6: Accept a Space Invitation from View All");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2 = username2+" "+username2;
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All
		*Step Description: 
			- Login with User B
			- Click Notifications icon
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- View All page is displayed
			- A Space Invitation notifications is displayed in the page*/
		info("Create a new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space,space);
		Utils.pause(3000);
		info("Invite UserB to the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		
		info("A Space Invitation notifications is displayed in the page");
		String inviteSpaceStatus=notiIntranetData.getContentByArrayTypeRandom(3);
		intraNot.checkStatusSpace(inviteSpaceStatus,space);

		/*Step number: 2
		*Step Name: Step 2 : Accept Space Invitation
		*Step Description: 
			- Click [Accept]
		*Input Data: 
			
		*Expected Outcome: 
			- The invitation is approved and User B is member of Space 1*/
		
		info("Click [Accept]");
		intraNot.acceptRqConnection(space);
		Utils.pause(2000);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("The invitation is approved and User B is member of Space 1");
		hp.goToHomePage();
		hp.goToSpecificSpace(space);
		spaMg.verifyMember(username2,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check notification message
		*Step Description: 
			- Click the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The notification message is updated to : $AVATARYou joined $SPACE space$DATEWhere : 
			- $AVATAR is the thumbnail of the space
			- $SPACE is space 1
			- $DATE is the date of the notification*/ 
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("Check format of the notification after accepted");
		String acceptSpaceStatus=notiIntranetData.getContentByArrayTypeRandom(9);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		intraNot.checkAvatarInStatus(arrayUser,true);
		intraNot.checkUsers(arrayUser,true);
		intraNot.checkStatusSpace(acceptSpaceStatus,space);

 	}

	/**
	*<li> Case ID:125173.</li>
	*<li> Test Case Name: Refuse a Space Invitation from View All.</li>
	*<li> Pre-Condition: - User A is manager of the space 1
	- User A invite User B to join the space 1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_RefuseASpaceInvitationFromViewAll() {
		info("Test 7: Refuse a Space Invitation from View All");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Login with User B
			- Click Notifications icon
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- A Space Invitation notifications is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2 = username2+" "+username2;
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("Create a new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space,space);
		Utils.pause(3000);
		info("Invite UserB to the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		
		info("A Space Invitation notifications is displayed in the page");
		String inviteSpaceStatus=notiIntranetData.getContentByArrayTypeRandom(3);
		intraNot.checkStatusSpace(inviteSpaceStatus,space);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Click [Refuse]
		*Input Data: 
			
		*Expected Outcome: 
			- User B is not member of Space 1*/
		info("Click [Refuse]");
		intraNot.refuseRqConnection(space);
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click the notification message
		*Input Data: 
			
		*Expected Outcome: 
			- The notifications message is hidden from the list*/ 
		
		info("Check format of the notification after accepted");
		String acceptSpaceStatus=notiIntranetData.getContentByArrayTypeRandom(9);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkNotStatusSpace(acceptSpaceStatus,space);
		intraNot.checkNotStatusSpace(inviteSpaceStatus,space);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("User B isnot member of Space 1");
		hp.goToSpecificSpace(space);
		spaMg.verifyMember(username1,false);

 	}

	/**
	*<li> Case ID:125174.</li>
	*<li> Test Case Name: Accept a Space Join Request from View All.</li>
	*<li> Pre-Condition: - User A requested to join Space 1
	- User B is manager of Space 1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_AcceptASpaceJoinRequestFromViewAll() {
		info("Test 8: Accept a Space Join Request from View All");
		/*Step Number: 1
		*Step Name: Step 1 : Go to View all
		*Step Description: 
			- Login with User B
			- Click the notification icon
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- The View All page is displayed
			- The Space Join Request notification is displayed in the oage*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2 = username2+" "+username2;
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("Create a new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space,space);
		Utils.pause(3000);
		info("Invite UserB to the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		
		info("A Space Invitation notifications is displayed in the page");
		String inviteSpaceStatus=notiIntranetData.getContentByArrayTypeRandom(3);
		intraNot.checkStatusSpace(inviteSpaceStatus,space);

		/*Step number: 2
		*Step Name: Step 2 : Accept Space Join Request
		*Step Description: 
			- Click the button [Accept]
		*Input Data: 
			
		*Expected Outcome: 
			- User A is accepted and member of the space*/

		info("Click [Accept]");
		intraNot.acceptRqConnection(space);
		
		info("The invitation is approved and User B is member of Space 1");
		hp.goToHomePage();
		hp.goToSpecificSpace(space);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_ACTIVITY_STREAM);
		
		/*Step number: 3
		*Step Name: Step 3 : Check notification message
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The notification message is updated to :$AVATAR$USER joined $SPACE space$DATEWhere:
			- $AVATAR is the thumbnail of User A
			- $USER is User A
			- $SPACE is Space 1
			- $DATE is the date of the notification*/
		
		info("Check format of the notification after accepted");
		String acceptSpaceStatus=notiIntranetData.getContentByArrayTypeRandom(9);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		arrayUser.add(username1);
		arrayUser.add(username2);
		intraNot.checkAvatarInStatus(arrayUser,true);
		intraNot.checkUsers(arrayUser,true);
		intraNot.checkStatusSpace(acceptSpaceStatus,space);

		/*Step number: 4
		*Step Name: Step 4 : Click the notification
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirector to the 
			Space 1*/ 
		info("Click the notification area");
		intraNot.goToDetailAcceptInvitationSpace(space,false);
		
	}
	/**
	*<li> Case ID:125175.</li>
	*<li> Test Case Name: Refuse a Space Join Request from View All.</li>
	*<li> Pre-Condition: - User A requested to join Space 1
	- User B is manager of Space 1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_RefuseASpaceJoinRequestFromViewAll() {
		info("Test 9: Refuse a Space Join Request from View All");
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All page
		*Step Description: 
			- Login with User B
			- Click the notification icon
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- The View All page is displayed
			- The Space Join Request notification is displayed in the page*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2 = username2+" "+username2;
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("Create a new space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space,space);
		Utils.pause(3000);
		info("Invite UserB to the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		
		info("A Space Invitation notifications is displayed in the page");
		String inviteSpaceStatus=notiIntranetData.getContentByArrayTypeRandom(3);
		intraNot.checkStatusSpace(inviteSpaceStatus,space);

		/*Step number: 2
		*Step Name: Step 2 : Refuse the Space Join Request
		*Step Description: 
			- Click the button [Refuse]
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not member of the space*/
		info("Click [Refuse]");
		intraNot.refuseRqConnection(space);
		
		/*Step number: 3
		*Step Name: Step 3 : Check notification message
		*Step Description: 
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- The notification message is automatically hidden from the list*/ 
		info("Check format of the notification after accepted");
		String acceptSpaceStatus=notiIntranetData.getContentByArrayTypeRandom(9);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkNotStatusSpace(acceptSpaceStatus,space);
		intraNot.checkNotStatusSpace(inviteSpaceStatus,space);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("User B isnot member of Space 1");
		hp.goToSpecificSpace(space);
		spaMg.verifyMember(username1,false);

 	}

	/**
	*<li> Case ID:125176.</li>
	*<li> Test Case Name: Click Mention notifications in View All (in comment).</li>
	*<li> Pre-Condition: - An wiki activity is generated (create a new page)
	- User A has mentioned User B directly in a comment</li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test10_ClickMentionNotificationsInViewAllInComment() throws AWTException {
		info("Test 10 Click Mention notifications in View All (in comment)");
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All Page
		*Step Description: 
			- Login with User B
			- Click the notification icons in the top navigation
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- The Mention notification is displayed in the list*/
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2 = username2+" "+username2;
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.addUserAdmin(username1);
		magAc.signIn(username1,password);
		
		info("John add an activity with wiki page");
		String wiki = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToWiki();
		Utils.pause(3000);
		wHome.goToAddBlankPage();
		Utils.pause(3000);
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wiki, wiki);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.
				replace("${name}",wiki),2000,1);
		
		info("John mention User A");
		hp.goToHomePage();
		String actMention = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addCommentWithMentionUser(wiki,username2, actMention);
		info("Add comment to Comment list");
		comments.add(actMention);
		
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		String status =notiIntranetData.getContentByArrayTypeRandom(7);
		intraNot.checkStatus(status,fullName2);

		/*Step number: 2
		*Step Name: Step 2 : Click Mention notification message
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirected to the activity in the activity viewer with all comments expanded
			- The comment is highlighted*/ 
		info("Check detail of Activity Comment");
		intraNot.goToDetailMentionNotification(fullName2, false);
		notiAct.checkCommentExpand(actMention, true);
 	}

	/**
	*<li> Case ID:125177.</li>
	*<li> Test Case Name: Click the Mention notifications from View All (in activity message).</li>
	*<li> Pre-Condition: - User A has mentioned User B directly in the activity message</li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test11_ClickTheMentionNotificationsFromViewAllInActivityMessage() throws AWTException {
		info("Test 11 Click the Mention notifications from View All (in activity message)");
		
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All
		*Step Description: 
			- Login with User B
			- Click the notification icons in the top navigation
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- The View All page is displayed
			- The Mention notification is displayed in the page*/
		info("Create 2 users for testing");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("UserA mention User B");
		hp.goToHomePage();
		String actMention = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.mentionUserActivity(username2, actMention);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		String status =notiIntranetData.getContentByArrayTypeRandom(7);
		intraNot.checkStatus(status,username1);

		/*Step number: 2
		*Step Name: Step 2 : Click Mention notification
		*Step Description: 
			- Click the notification
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirected to the activity viewer with all comment expanded.*/ 
		info("Check detail of Activity comment");
		intraNot.goToDetailMentionNotification(username1,false);
		notiAct.checkTitleActivityExpand(actMention);

 	}

	/**
	*<li> Case ID:125178.</li>
	*<li> Test Case Name: Click Comment Notification from View All (1 comment).</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has commented on User A activity</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_ClickCommentNotificationFromViewAll1Comment() {
		info("Test 12 Click Comment Notification from View All (1 comment)");
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Go to View All
		*Input Data: 
			
		*Expected Outcome: 
			- The View All Page is displayed
			- A comment notification is displayed in the page*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Log in with User A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info(" Go to View All Notification");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		String status=notiIntranetData.getNotiContent(0);
		arrayUser.add(username1);
		arrayUser.add(username2);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,false);

		/*Step number: 2
		*Step Name: Step 2 : Click the Comment notification
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.
			- The comment that this notification is about is highlighted.*/ 
		info("Check detail of activity comment");
		intraNot.goToDetailCommentNotification(activity, false);
		notiAct.checkCommentExpand(comment, true);

 	}

	/**
	*<li> Case ID:125179.</li>
	*<li> Test Case Name: Click Comment Notification from View All (>= 2 comments).</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A and User C are connected
	- User A has posted an activity
	- User B has commented on User A activity
	- User C has commented on User A activity</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_ClickCommentNotificationFromViewAllMore2Comments() {
		info("Test 13 Click Comment Notification from View All (>= 2 comments)");
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- The View All page is displayed
			- A comment notification is displayed in the page*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		
		info("User A sent a connection request to UserB");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		info("User A sent a connection request to UserC");
		connMag.connectToAUser(username3);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("UserA and User B are connected");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("User A and User C are connected");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("User C comment on UserA's activity");
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addCommentUsingJavascript(activity, comment1);
		
		info("Add all comments to the list");
		comments.add(comment);
		Utils.pause(3000);
		comments.add(comment1);
		
		
		info("Log in with User A");
		magAc.signIn(username1, password);
		info("Check format notification in the notification list");
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		String status=notiIntranetData.getNotiContent(1);
		arrayUser.add(username1);
		arrayUser.add(username2);
		arrayUser.add(username3);
		intraNot.checkFormatStatusCommentNotification(arrayUser,status,activity,false);

		/*Step number: 2
		*Step Name: Step 2: click Notification message
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/ 
		info("Check detail of activity comment");
		intraNot.goToDetailCommentNotification(activity,false);
		notiAct.checkCommentsExpand(comments,false);

 	}

	/**
	*<li> Case ID:125180.</li>
	*<li> Test Case Name: Click Like Notification from View All.</li>
	*<li> Pre-Condition: - User A and User B are connected
	- User A has posted an activity
	- User B has liked User A activity</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_ClickLikeNotificationFromViewAll() {
		info("Test 14 Click Like Notification from View All");
		
		/*Step Number: 1
		*Step Name: Step 1 : Go to All page
		*Step Description: 
			- Login with User A
			- Click the notifications icon in the top navigation
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- The View All page is displayed
			- A Like notification is displayed in the page*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB likes UserA's activity");
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		
		
		info("Log in with User A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info(" Go to View All Notification");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		String status=notiIntranetData.getContentByArrayTypeRandom(6);
		intraNot.checkStatus(status,username2);

		/*Step number: 2
		*Step Name: Step 3 : Read the notification
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The activity is displayed in the activity viewer with all comment expanded.*/ 
		info("Check detail of activity comment");
		intraNot.goToDetailLikeNotification(username2,false);
		notiAct.checkTitleActivityExpand(activity);
 	}

	/**
	*<li> Case ID:125181.</li>
	*<li> Test Case Name: Click Post on my Stream notification from View All.</li>
	*<li> Pre-Condition: User A has posted an activity on User B activity stream</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_ClickPostOnMyStreamNotificationFromViewAll() {
		info("Test 15 Click Post on my Stream notification from View All");
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All page
		*Step Description: 
			- Login with User B
			- Click the notifications icon in the top navigation
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- View All page is displayed
			- A Post on my Stream notifications is displayed in the list*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Post_intranet);
		
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("User B add an activity on User A's stream");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(username1);
		userProPage.goToActivity();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		
		info("Log in with User A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info(" Go to View All Notification");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		String status=notiIntranetData.getContentByArrayTypeRandom(8);
		intraNot.checkStatus(status,username2);

		/*Step number: 2
		*Step Name: Step 2 : Click the notification
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			The activity is displayedin the activity viewer with all comments expanded*/ 
		info("Check detail of activity comment");
		intraNot.goToDetailPostInMyActivity(username2,false);
		notiAct.checkTitleActivityExpand(activity);
 	}

	/**
	*<li> Case ID:125182.</li>
	*<li> Test Case Name: Click Post on my Space notification from View All.</li>
	*<li> Pre-Condition: - User A and User B are members of Space 1
	- User B has posted in Space 1</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_ClickPostOnMySpaceNotificationFromViewAll() {
		info("Test 16 Click Post on my Space notification from View All");
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All
		*Step Description: 
			- Login with User B
			- Click the notifications icon in the top navigation
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- View All page is displayed
			- A Post on my Space notifications is displayed in the page*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.Space_Post_intranet);
		
		info("Create a space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		Utils.pause(3000);
		spaMg.addNewSpaceSimple(space,space,30000);
		
		info("User A sent a join request to User B from the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("UserB accepted the connection");
		hp.goToAllSpace();
		Utils.pause(3000);
	    spaMg.goToInvitationsReceivedTab();
	    spaMg.acceptAInvitation(space);
	    
	    info("User B post an activity on the space's activity");
	    String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
	    hp.goToSpecificSpace(space);
	    spaMg.goToActivityStreamTab();
	    hpAct.addActivity(activity,null);
	    
	    info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info(" Go to View All Notification");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		String status=notiIntranetData.getContentByArrayTypeRandom(10);
		intraNot.checkStatus(status,username2);

		/*Step number: 2
		*Step Name: Step 2 : Click the notification
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			The activity is displayed in the activity viewer with all comments expanded*/ 
		info("Check detail of activity comment");
		intraNot.goToDetailPostInSpace(space,false);
		notiAct.checkTitleActivityExpand(activity);
 	}

	/**
	*<li> Case ID:125184.</li>
	*<li> Test Case Name: Check order of the notifications in View All.</li>
	*<li> Pre-Condition: A user has received several notifications (from the oldest to the newest)
	*1. A like notification
	*2. A comment notification
	*3. A connection request</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test17_CheckOrderOfTheNotificationsInViewAll() {
		info("Test 17 Check order of the notifications in View All");
		/*Step Number: 1
		*Step Name: Step 1 : check View All page
		*Step Description: 
			- Login
			- Click the notification icon in the top navigation
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- The View All page is displayed
			- The notifications are ordered from the newest 
			at the top to the latest at the bottom (connection request, comment, like)*/ 
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		
		
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.AS_Comment_intranet);
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User A add an activity");
		String activity = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity,null);
		hpAct.checkActivity(activity);
		
		info("User B login");
		magAc.signIn(username2, password);
		
		info("User A and User B are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("UserB comments in UserA's activity");
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.likeActivity(activity);
		hpAct.addCommentUsingJavascript(activity, comment);
		
		info("Add comment to Comment list");
		comments.add(comment);
		
		info("User C login");
		magAc.signIn(username3, password);
		
		info("User C sent a connection request to User A");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username1);
		
		String statusSendRq=notiIntranetData.getContentByArrayTypeRandom(2);
		String statusLikeAc=notiIntranetData.getContentByArrayTypeRandom(6);
		String statusCommAc=notiIntranetData.getNotiContent(0);
		
		info("Log in with User A");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("The notifications are ordered from the newest at the top to the latest at the bottom "
				+ "(connection request, comment, like)");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkOrderNotifications(1,statusSendRq,username3);
		intraNot.checkOrderNotifications(2,statusCommAc,username2);
		intraNot.checkOrderNotifications(3,statusLikeAc,username2);
		
 	}

	/**
	*<li> Case ID:125185.</li>
	*<li> Test Case Name: Check number of notifications displayed in the page.</li>
	*<li> Pre-Condition: - The user must has between 20 and 30 notifications existing</li>
	*<li> Post-Condition: </li>
	 * @throws AWTException 
	*/
	@Test
	public  void test18_CheckNumberOfNotificationsDisplayedInThePage() throws AWTException {
		info("Test 18 Check number of notifications displayed in the page");
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All
		*Step Description: 
			- Login
			- Click the notification icon a the top
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- The View All page is displayed
			- Max 20 notifications are displayed in the page*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		String username4 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email4 = username4+"@gmail.com";
		String username5 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email5 = username5+"@gmail.com";
		String username6 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email6 = username6+"@gmail.com";
		String username7 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email7 = username7+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		addUserPage.addUser(username4, password, email4, username4, username4);
		addUserPage.addUser(username5, password, email5, username5, username5);
		addUserPage.addUser(username6, password, email6, username6, username6);
		addUserPage.addUser(username7, password, email7, username7, username7);
		magAc.signIn(username1,password);
		
		info("goto My notification");
		navTool.goToMyNotifications();
		info("Enable like notification");
		myNoti.enableNotification(myNotiType.AS_Like_intranet);
		
		info("User 1 add an activity");
		String activity1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity1,null);
		hpAct.checkActivity(activity1);
		
		info("User 1 add an activity");
		String activity2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity2,null);
		hpAct.checkActivity(activity2);
		
		info("User 1 add an activity");
		String activity3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity3,null);
		hpAct.checkActivity(activity3);
		
		info("User 1 add an activity");
		String activity4 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToHomePage();
		Utils.pause(3000);
		hpAct.addActivity(activity4,null);
		hpAct.checkActivity(activity4);
		
		
		info("User 1 sent a connection request to User 2");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User 1 sent a connection request to User 3");
		Utils.pause(3000);
		connMag.connectToAUser(username3);
		
		info("User 1 sent a connection request to User 4");
		Utils.pause(3000);
		connMag.connectToAUser(username4);
		
		info("User 1 sent a connection request to User 5");
		Utils.pause(3000);
		connMag.connectToAUser(username5);
		
		info("User 1 create a space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		Utils.pause(3000);
		spaMg.addNewSpaceSimple(space,space,30000);
		
		info("User 2 login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User 1 and User 2 are connected");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("User2 comments in User1's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		String comment = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hpAct.likeActivity(activity1);                           //notification 1
		hpAct.addCommentUsingJavascript(activity1, comment);     //notification 2
		info("Add comment to Comment list");
		comments.add(comment);
		
		info("User2 mention User1 in activity");
		String actMention = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hpAct.mentionUserActivity(username1, actMention); //notification 3
		
		info("User 2 posts a message in User1's activity");
		String myActivity= txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(username1);
		userProPage.goToActivity();
		hpAct.addActivity(myActivity,null);                      //notification 4
		
		info("User 2 create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		Utils.pause(3000);
		spaMg.addNewSpaceSimple(space1,space1,30000);
		
		
		info("User 2 sent a join request to User 1 from the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username1,true,username1+" "+username1); //notification 5
		
		
		info("User 2 sent a join request to User1's space");
		hp.goToAllSpace();
		spaMg.requestToJoinSpace(space,true);                        //notification 6
		
		info("User 3 login");
		magAc.signIn(username3, password);
		Utils.pause(3000);
		
		info("User 3 accepted with User1's connection request");
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		Utils.pause(3000);
		
		info("User3 comments and likes in User1's activity");
		hp.goToHomePage();
		String comment1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hpAct.likeActivity(activity2);                           //notification 7
		hpAct.addCommentUsingJavascript(activity2, comment1);     //notification 8
		info("Add comment to Comment list");
		comments.add(comment1);
		
		info("User3 mention User1 in activity");
		String actMention1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hpAct.mentionUserActivity(username1, actMention1); //notification 9
		
		info("User 3 posts a message in User1's activity");
		String myActivity1= txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(username1);
		userProPage.goToActivity();
		hpAct.addActivity(myActivity1,null);                      //notification 10
		
		info("User 3 create a space");
		String space2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		Utils.pause(3000);
		spaMg.addNewSpaceSimple(space2,space2,30000);
		
		
		info("User 3 sent a join request to User 1 from the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username1,true,username1+" "+username1); //notification 11
		
		
		info("User 3 sent a join request to User1's space");
		hp.goToAllSpace();
		spaMg.requestToJoinSpace(space,true);                        //notification 12
		
		info("User 4 login");
		magAc.signIn(username4, password);
		
		info("User 4 accepted with User1's connection request");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("User4 comments and likes in User1's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		String comment2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hpAct.likeActivity(activity3);                           //notification 13
		hpAct.addCommentUsingJavascript(activity3, comment2);     //notification 14
		info("Add comment to Comment list");
		comments.add(comment2);
		
		info("User4 mention User1 in activity");
		String actMention2 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hpAct.mentionUserActivity(username1, actMention2); //notification 15
		
		info("User 4 posts a message in User1's activity");
		String myActivity2= txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(username1);
		userProPage.goToActivity();
		hpAct.addActivity(myActivity2,null);                      //notification 16
		
		info("User 4 create a space");
		String space3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		Utils.pause(3000);
		spaMg.addNewSpaceSimple(space3,space3,30000);
		
		
		info("User 4 sent a join request to User 1 from the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username1,true,username1+" "+username1); //notification 17
		
		
		info("User 4 sent a join request to User1's space");
		hp.goToAllSpace();
		spaMg.requestToJoinSpace(space,true);                        //notification 18
		
		
		info("User 5 login");
		magAc.signIn(username5, password);
		
		info("User 5 accepted with User1's connection request");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.acceptAConnection(username1);
		
		info("User5 comments and likes in User1's activity");
		hp.goToHomePage();
		Utils.pause(3000);
		String comment3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hpAct.likeActivity(activity4);                           //notification 19
		hpAct.addCommentUsingJavascript(activity4, comment3);     //notification 20
		info("Add comment to Comment list");
		comments.add(comment3);
		
		info("User5 mention User1 in activity");
		String actMention3 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hpAct.mentionUserActivity(username1, actMention3); //notification 21
		
		info("User 5 posts a message in User1's activity");
		String myActivity3= txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		hp.goToFriendProfilePage(username1);
		userProPage.goToActivity();
		hpAct.addActivity(myActivity3,null);                      //notification 22
		
		info("User 6 login");
		magAc.signIn(username6, password);
		Utils.pause(3000);
		
		info("User 6 sent a connection request to User 1");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username1);                   //notification 23
		
		
		info("User 7 login");
		magAc.signIn(username7, password);
		Utils.pause(3000);
		
		info("User 7 sent a connection request to User 1");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username1);               //notification 24
		
		
		info("User 1 login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		
		String statusSendRq=notiIntranetData.getContentByArrayTypeRandom(2);
		String statusMention=notiIntranetData.getContentByArrayTypeRandom(7);
		String statusJoinSpace=notiIntranetData.getContentByArrayTypeRandom(4);
		String statusInviteSpace=notiIntranetData.getContentByArrayTypeRandom(3);
		String statusLikeAc=notiIntranetData.getContentByArrayTypeRandom(6);
		String statusPostFriendAct=notiIntranetData.getContentByArrayTypeRandom(8);
		String statusCommAc=notiIntranetData.getNotiContent(0);
		
		info("Check status send connection request from User 6 and User 7");
		intraNot.checkStatus(statusSendRq, username7);        //Notification 24
		intraNot.checkStatus(statusSendRq, username6);        //Notification 23
		
		info("Check status from User5");
		intraNot.checkStatus(statusPostFriendAct, username5);  // Notification 22
		intraNot.checkStatus(statusMention, username5);        // Notification 21
		intraNot.checkStatus(statusCommAc,username5);         //Notification 20
		intraNot.checkStatus(statusLikeAc, username5);         //Notification 19
		
		info("Check status from User4");
		intraNot.checkStatus(statusJoinSpace, username4);       // Notification 18
		intraNot.checkStatus(statusInviteSpace,username4);     //Notification 17
		intraNot.checkStatus(statusPostFriendAct,username4);  // Notification 16
		intraNot.checkStatus(statusMention,username4);        // Notification 15
		intraNot.checkStatus(statusCommAc,username4);         //Notification 14
		intraNot.checkStatus(statusLikeAc,username4);         //Notification 13
		
		
		info("Check status from User3");
		intraNot.checkStatus(statusJoinSpace,username3);       // Notification 12
		intraNot.checkStatus(statusInviteSpace,username3);     //Notification 11
		intraNot.checkStatus(statusPostFriendAct,username3);  // Notification 10
		intraNot.checkStatus(statusMention,username3);        // Notification 9
		intraNot.checkStatus(statusCommAc,username3);         //Notification 8
		intraNot.checkStatus(statusLikeAc,username3);         //Notification 7
		
		
		info("Check status from User2");
		intraNot.checkStatus(statusJoinSpace, username2);       // Notification 6
		intraNot.checkStatus(statusInviteSpace, username2);     //Notification 5
		intraNot.checkNotPresentStatus(statusPostFriendAct,username2);  // Notification 4
		intraNot.checkNotPresentStatus(statusMention,username2);        // Notification 3
		intraNot.checkNotPresentStatus(statusCommAc,username2);         //Notification 2
		intraNot.checkNotPresentStatus(statusLikeAc,username2);         //Notification 1
		
		/*Step number: 2
		*Step Name: Step 2 : Scroll down
		*Step Description: 
			- Scroll down in the page
		*Input Data: 
			
		*Expected Outcome: 
			- We can scroll to see the other notifications. 
			When these notifications are loaded, we display a loading icon.*/ 
		scrollToBottomPage(driver);
		intraNot.checkStatus(statusPostFriendAct,username2);  // Notification 4
		intraNot.checkStatus(statusMention,username2);        // Notification 3
		intraNot.checkStatus(statusCommAc,username2);         //Notification 2
		intraNot.checkStatus(statusLikeAc,username2);         //Notification 1

 	}

	/**
	*<li> Case ID:125186.</li>
	*<li> Test Case Name: Check Notifications Settings link in View All.</li>
	*<li> Pre-Condition: The user has notifications</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test19_CheckNotificationsSettingsLinkInViewAll() {
		info("Test 19 Check Notifications Settings link in View All");
		/*Step Number: 1
		*Step Name: Step 1 : Go to View All
		*Step Description: 
			- Login
			- Click the notifications icon in the top navigation
			- Click View All
		*Input Data: 
			
		*Expected Outcome: 
			- The View All page is displayed*/
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		info("User A sent a connection request to User B");
		Utils.pause(3000);
		hp.goToConnections();
		connMag.connectToAUser(username2);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info(" Go to View All Notification");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();

		/*Step number: 2
		*Step Name: Step 2 : Check UI
		*Step Description: 
			- Check the top of the View All page
		*Input Data: 
			
		*Expected Outcome: 
			- At the top of the page, 1 link "Notifications Settings" is available.*/
		info(" Check the top of the View All page");
		waitForAndGetElement(intraNot.ELEMENT_NOTIFICATION_SETTINGS_LINK);

		/*Step number: 3
		*Step Name: Step 3 : Click the link
		*Step Description: 
			- Click [Notifications Settings]
		*Input Data: 
			
		*Expected Outcome: 
			- The link redirects the user to his settings page*/ 
		intraNot.goToNotificationSettings();

 	}

	/**
	*<li> Case ID:125187.</li>
	*<li> Test Case Name: Configure Number of notifications in View All page.</li>
	*<li> Pre-Condition: - In gatein/conf, rename exo
	-sample.properties to exo.properties
	- Update the property : exo.notification.viewall=1</li>
	*<li> Post-Condition: </li>
	*/
	@Test(groups="pending")
	public  void test20_ConfigureNumberOfNotificationsInViewAllPage() {
		info("Test 20 Configure Number of notifications in View All page");
		/*Step Number: 1
		*Step Name: Step 1 : Start the server
		*Step Description: 
			- Start eXo Platform with the new property
			- Login
		*Input Data: 
			
		*Expected Outcome: 
			- the homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Generate notification
		*Step Description: 
			- Generate several notifications for the current user logged in
			- Go to View All
		*Input Data: 
			
		*Expected Outcome: 
			- The notification are displayed in the page*/

		/*Step number: 3
		*Step Name: Step 3 : Change server time
		*Step Description: 
			- Update your server time to 10:59 pm the day after
			- Wait for 1 min
		*Input Data: 
			
		*Expected Outcome: 
			- The cleaning job must be started*/

		/*Step number: 4
		*Step Name: Step 4 : Check View all
		*Step Description: 
			- Go back to View All page
		*Input Data: 
			
		*Expected Outcome: 
			- All notification old of 1 day are cleaned (the notification created in previous steps)*/ 

 	}}