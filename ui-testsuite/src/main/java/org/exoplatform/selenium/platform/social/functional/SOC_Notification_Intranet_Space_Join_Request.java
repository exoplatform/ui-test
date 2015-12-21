package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.*;


	public class SOC_Notification_Intranet_Space_Join_Request extends SOC_TestConfig2{

	/**
	*<li> Case ID:125150.</li>
	*<li> Test Case Name: Check Space Join Request notification.</li>
	*<li> Pre-Condition: - User A requested to join Space 1
	- User B is manager of Space 1
	- Space Join Request notification is activated in User's B settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckSpaceJoinRequestNotification() {
		info("Test 1: Check Space Join Request notification");
		/*Step Number: 1
		*Step Name: Step 1 : check notification list
		*Step Description: 
			- Login with User B
			- Click the notification icon
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- The Space Join Request notification is displayed in the list*/
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
		myNoti.enableNotification(myNotiType.Space_Join_Req_intranet);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);

		/*Step number: 2
		*Step Name: Step : Check content of notification
		*Step Description: 
			- Check the notification message
		*Input Data: 
			
		*Expected Outcome: 
			The notification message is :
			- $AVATAR
			- $USER has requested access to $SPACE space.
			- [Accept] | [Refuse]
			- $DATEWhere : 
			- $AVATAR is the thumbnail of User A
			- $USER is User A
			- $DATE is the date of the notification*/
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("Check intranet notification format");
		String status = notiIntranetData.getContentByArrayTypeRandom(12);
		navTool.goToIntranetNotification();
		intraNot.checkAvatarInStatus(arrayUser.get(1),true);
		intraNot.checkStatusSpace(status, spaceName);
		intraNot.checkBtnConnectJoinRequest(spaceName);

		/*Step number: 3
		*Step Name: Step 3 : click notification area
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirect to the Space 1*/ 
		info("The user is redirected to the Space");
		intraNot.goToDetailRequestJoinSpace(arrayUser.get(1),true);
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET);
 	}

	/**
	*<li> Case ID:125151.</li>
	*<li> Test Case Name: Accept a Space Join Request from notification.</li>
	*<li> Pre-Condition: - User A requested to join Space 1
	- User B is manager of Space 1
	- Space Join Request notification is activated in User's B settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_AcceptASpaceJoinRequestFromNotification() {
		info("Test 2: Accept a Space Join Request from notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User B
			- Click the notification icon
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- The Space Join Request notification is displayed in the list*/
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
		myNoti.enableNotification(myNotiType.Space_Join_Req_intranet);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("Check intranet notification format");
		String status = notiIntranetData.getContentByArrayTypeRandom(12);
		navTool.goToIntranetNotification();
		intraNot.checkStatusSpace(status, spaceName);

		/*Step number: 2
		*Step Name: Step 2 : Accept space request
		*Step Description: 
			- Click the button [Accept]
		*Input Data: 
			
		*Expected Outcome: 
			- User A is accepted and member of the space*/
		info("User A is accepted and member of the space");
		intraNot.acceptRqConnection(arrayUser.get(1));
		hp.goToHomePage();
	    hp.goToSpecificSpace(spaceName);
	    waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET);

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
	    info("Check intranet notification format");
		String statusJoin = notiIntranetData.getContentByArrayTypeRandom(4);
		navTool.goToIntranetNotification();
		intraNot.checkAvatarInStatus(arrayUser.get(1),true);
		intraNot.checkStatusSpace(statusJoin, spaceName);

		/*Step number: 4
		*Step Name: Step 4 : click notification area
		*Step Description: 
			- Click the notification area
		*Input Data: 
			
		*Expected Outcome: 
			- The user is redirector to the Space 1*/ 
		info("The user is redirected to the Space");
		intraNot.goToDetailJoinSpace(arrayUser.get(1),true);
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET);

 	}

	/**
	*<li> Case ID:125152.</li>
	*<li> Test Case Name: Refuse a Space Join Request from notification.</li>
	*<li> Pre-Condition: - User A requested to join Space 1
	- User B is manager of Space 1
	- Space Join Request notification is activated in User's B settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_RefuseASpaceJoinRequestFromNotification() {
		info("Test 3: Refuse a Space Join Request from notification");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User B
			- Click the notification icon
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- The Space Join Request notification is displayed in the list*/
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
		myNoti.enableNotification(myNotiType.Space_Join_Req_intranet);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("Check intranet notification format");
		String status = notiIntranetData.getContentByArrayTypeRandom(12);
		navTool.goToIntranetNotification();
		intraNot.checkStatusSpace(status, spaceName);

		/*Step number: 2
		*Step Name: Step 2 : Refuse space request
		*Step Description: 
			- Click the button [Refuse]
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not member of the space*/
		info("User A is accepted and member of the space");
		intraNot.refuseRqConnection(username2);

		/*Step number: 3
		*Step Name: Step 3 : check notification list
		*Step Description: 
			- Check the notifications list
		*Input Data: 
			
		*Expected Outcome: 
			- The notification message is automatically hidden from the list*/
		 intraNot.checkNotStatusSpace(status, spaceName);

 	}

	/**
	*<li> Case ID:125153.</li>
	*<li> Test Case Name: Check View All after refusing a Space Join Request.</li>
	*<li> Pre-Condition: - User A requested to join Space 1
	- User B is manager of Space 1 
	- Space Join Request notification is activated in User's B settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckViewAllAfterRefusingASpaceJoinRequest() {
		info("Test 4: Check View All after refusing a Space Join Request");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User B
			- Click the notification icon
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- The Space Join Request notification is displayed in the list*/
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
		myNoti.enableNotification(myNotiType.Space_Join_Req_intranet);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("Check intranet notification format");
		String status = notiIntranetData.getContentByArrayTypeRandom(12);
		navTool.goToIntranetNotification();
		intraNot.checkStatusSpace(status, spaceName);

		/*Step number: 2
		*Step Name: Step 2 : Refuse space request and check view all
		*Step Description: 
			- Click the button [Refuse]
			- Go to View All page
		*Input Data: 
			
		*Expected Outcome: 
			- The notification is not displayed / available in the page*/ 
		info("User A is accepted and member of the space");
		intraNot.refuseRqConnection(username2);
		
		intraNot.goToAllNotification();
		intraNot.checkNotStatusSpace(status,spaceName);

 	}

	/**
	*<li> Case ID:125154.</li>
	*<li> Test Case Name: Check View All after accepting a Space Join Request.</li>
	*<li> Pre-Condition: - User A requested to join Space 1
	- User B is manager of Space 1
	- Space Join Request notification is activated in User's B settings</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckViewAllAfterAcceptingASpaceJoinRequest() {
		info("Test 5: Check View All after accepting a Space Join Request");
		/*Step Number: 1
		*Step Name: Step 1 : Check notification list
		*Step Description: 
			- Login with User B
			- Click the notification icon
			- Check the notification list
		*Input Data: 
			
		*Expected Outcome: 
			- The Space Join Request notification is displayed in the list*/
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
		myNoti.enableNotification(myNotiType.Space_Join_Req_intranet);
		
		info("User A create a new space");
		String spaceName= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spaceDes= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.goToCreateSpace();
		spaMg.addNewSpaceSimple(spaceName,spaceDes);
		
		info("User B login");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		
		info("User B send a join request to UserA's space");
		hp.goToAllSpace();
		spaMg.sendARequestToASpace(spaceName, true);
		
		info("User A login");
		magAc.signIn(username1, password);
		Utils.pause(3000);
		
		info("Check intranet notification format");
		String status = notiIntranetData.getContentByArrayTypeRandom(12);
		navTool.goToIntranetNotification();
		intraNot.checkStatusSpace(status, spaceName);

		/*Step number: 2
		*Step Name: Step 2: Accept Space Request
		*Step Description: 
			- Click the button [Accept]
		*Input Data: 
			
		*Expected Outcome: 
			- User A is accepted and member of the space*/
		info("User A is accepted and member of the space");
		intraNot.acceptRqConnection(username2);
		hp.goToHomePage();
	    hp.goToSpecificSpace(spaceName);
	    waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET);

		/*Step number: 3
		*Step Name: Step 3 : Check View All page
		*Step Description: 
			- Go to View All page
		*Input Data: 
			
		*Expected Outcome: 
			- The notification is displayed / available in the View All page
			- The message displayed is the last one updated*/ 
	    info("Check intranet notification format");
		String statusJoin = notiIntranetData.getContentByArrayTypeRandom(4);
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkStatusSpace(statusJoin, spaceName);
		intraNot.checkNotStatusSpace(status,spaceName);

 	}}