package org.exoplatform.selenium.platform.social.functional;
import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting.myNotiType;
import org.testng.annotations.Test;

public class SOC_Notification_Intranet_Connection_Request extends SOC_TestConfig2 {

		
	@Test 
	public void Test01_CheckConnectionRequestNotification(){
		info("Test 01: Check Connection Request notification");
		/*QmetryID:  135080         Check Connection Request notification
		- The notification "Someone sends me a connection request" is activated in User Settings
		- User A sent a connection request to User B
		Step 1
		- Login with User B
		- Click the notifications icon
		- Check the notification list
		*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		Utils.pause(3000);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.ConnectionRequest_intranet);
		Utils.pause(3000);
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		Utils.pause(3000);
		
		
		/*
		 * Step 2
		- Check the notification message
		--> Expected result:  
			The notification message is :  
				- $AVATAR 
				- $USER wants to connect with you
				- $DATE 
				- 2 actions : [Accept] | [Refuse]
	
				Where : 
				- $AVATAR is thumbnail of User A
				- $USER is User A
				- $DATE is the date of the notification
		 */
		info("Log in with User B");
		magAc.signIn(username2, password);
		String status=notiIntranetData.getContentByArrayTypeRandom(2);
		Utils.pause(3000);
		navTool.goToIntranetNotification();
        arrayUser.add(username2);
		intraNot.checkAvatarInStatus(arrayUser,true);
		intraNot.checkUsers(arrayUser,true);
		intraNot.checkStatus(status,username1);
		intraNot.checkBtnConnectJoinRequest(username1);
		
		
		/*
		 * 	Step 3:
		- Click the notification area
		--> Expected: User B is redirected to the profile of User A
		 */
		intraNot.goToDetailRequestConnectionUser(username1,true);
		info("Verify that User B is redirected to the profile of User A");
		waitForAndGetElement(userProPage.ELEMENT_PROFILE_TITLE.replace("${fullName}",username1));
		
	}
	
	@Test 
	public void Test02_AcceptAConnectionRequestFromTheNotification(){
		info("Test 02: Accept a Connection Request from the notification");
		/*	QmetryID: 125081
		 * Accept a Connection Request from the notification
			- The notification "Someone sends me a connection request" is activated in User Settings
			- User A sent a connection request to User B
			- Login with User B
			- Click notifications icon
			- Check the list
			Step 2:
			Click on Accept
			--> Expected: - The connection is approved, the 2 users are connected
			*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		
		Utils.pause(3000);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.ConnectionRequest_intranet);
		Utils.pause(3000);
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		Utils.pause(3000);
		
		
		/*
		 * Step 3: 
			- Check the notification message
			--> Expected: After clicking Accept, the notification message is updated to :
				$AVATAR
				You are connected with $USER
				$DATE
	
				Where : 
				- $AVATAR is thumbnail of User A
				- $USER is User A
				- $DATE is the date of the notification
		 */
		info("Log in with User B");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		String status=notiIntranetData.getContentByArrayTypeRandom(5);
		navTool.goToIntranetNotification();
		intraNot.acceptRqConnection(username1);
		intraNot.checkStatus(status,username1);
		
		info("Verify that User A and User B are friend");
		hp.goToConnections();
		connMag.verifyConnection(username1,true);
		
		/*
		 * Step 4: 
			- Click the notification area
			--> Expected:- User B is redirected to the profile of User A
		 */
		navTool.goToIntranetNotification();
		intraNot.goToDetailAcceptRequestConnectionUser(username1,true);
		info("Verify that User B is redirected to the profile of User A");
		waitForAndGetElement(userProPage.ELEMENT_PROFILE_TITLE.replace("${fullName}",username1+" "+username1));
	}
	
	
	@Test 
	public void Test03_RefuseAConnectionRequestFromTheNotification(){
		info("Test 03: Refuse a Connection Request from the notification");
		/*
		 * QmetryID: 125082
		 * Refuse a Connection Request from the notification
		- The notification "Someone sends me a connection request" is activated in User Settings
		- User A sent a connection request to User B
		- Login with User B
		- Click notifications icon
		- Check the list
		- Click [Refuse]
				--> Expected: - The connection is not approved, the 2 users are not connected
				- The notification message is automatically hidden from the list
				
		- Go to View All page
				--> Expected: - The notification is not available / displayed in the View All page*/
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.ConnectionRequest_intranet);
		Utils.pause(3000);
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		Utils.pause(3000);
		
		info("Log in with User B");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		String status=notiIntranetData.getContentByArrayTypeRandom(5);
		navTool.goToIntranetNotification();
		intraNot.refuseRqConnection(username1);
		intraNot.checkStatus(status,username1);
		
		info("Verify that User A and User B are not friend");
		hp.goToConnections();
		connMag.verifyConnection(username1, false);
		
		info("The notification is not available / displayed in the View All page");
		navTool.goToIntranetNotification();
		intraNot.goToAllNotification();
		intraNot.checkStatus(status,username1);
	}
	
	
	@Test 
	public void Test04_CheckViewAllAfterAcceptingAConnectionRequest(){
		info("Test 04: Check View All after accepting a Connection Request");
		/*QmetryID: 125083
		Check View All after accepting a Connection Request
		Precondition: 
		- The notification "Someone sends me a connection request" is activated in User Settings
		- User A sent a connection request to User B
		Step 1: 
		- Login with User B
		- Click notifications icon
		- Check the list
		- Click [Accept]
		--> Expected: - The connection is approved, the 2 users are connected
					- The notification message is updated accordingly
		*/			
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.ConnectionRequest_intranet);
		Utils.pause(3000);
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		Utils.pause(3000);
		
		info("Log in with User B");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		String status=notiIntranetData.getContentByArrayTypeRandom(5);
		navTool.goToIntranetNotification();
		intraNot.acceptRqConnection(username1);
		intraNot.checkStatus(status,username1);
		
	    /*
	     * Step 2:
	     - Go to View All
	     --> Expected: - The notifications is displayed in the message
				- The message is displayed at the same position as the connection request 
	     */
		info("The notification is not available / displayed in the View All page");
		intraNot.goToAllNotification();
		intraNot.checkStatus(status,username1);
	}
	
	@Test 
	public void Test05_CheckViewAllAfterrefusingAConnectionRequest(){
		info("Test 05: Check View All after refusing a Connection Request");
		/*QmetryID: 125084
		 * Check View All after refusing a Connection Request
		 * 
		 * - The notification "Someone sends me a connection request" is activated in User Settings
			- User A sent a connection request to User B
		 * - Login with User B
			- Click notifications icon
			- Check the list
			- Click [Refuse]
			- Go to View All
			--> Expected: - The notifications message is not displayed in the page
		 */
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		info("goto My notification");
		navTool.goToMyNotifications();
		myNoti.enableNotification(myNotiType.ConnectionRequest_intranet);
		Utils.pause(3000);
		info("User A sent a connection request to User B");
		hp.goToConnections();
		connMag.connectToAUser(username2);
		Utils.pause(3000);
		
		info("Log in with User B");
		magAc.signIn(username2, password);
		Utils.pause(3000);
		String status=notiIntranetData.getContentByArrayTypeRandom(5);
		navTool.goToIntranetNotification();
		intraNot.refuseRqConnection(username1);
		
		info("The notification is not available / displayed in the View All page");
		intraNot.checkStatus(status,username1);
	}
	
	
}
