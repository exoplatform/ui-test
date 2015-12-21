package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.testng.annotations.*;

public class SOC_People extends SOC_TestConfig_2{
	
	/**
	 *<li> Case ID:121886.</li>
	 *<li> Test Case Name: Accept/Deny Invitation.</li>
	 *<li> Case ID:121901.</li>
	 *<li> Test Case Name: Received Invitations.</li>
	 */
	@Test
	public void test01_06_AcceptDenyRecievedInvitation(){
		info("test01_06_AcceptDenyRecievedInvitation");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+ mailSuffixData.getMailSuffixRandom();
		
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1, password);
		
		/*Step Number: 1
		 *Step Name: Step 1: Send invitation
		 *Step Description: 
			- Login intranet home
			- Click on Connections on the left panel
			- Click on Connect button to invite about 2 users
			- Login by invited users, go to My Connections/Requests Received
			- An user click on Confirm button and another user Ignore the invitation
		 *Input Data: 
		 *Expected Outcome: 
			- Display list of people
			- Invitation is sent to user, Connect button is changed to Cancel Request
			- With user confirmed the invitation, user  becomes friend and user's name is displayed on user's network list 
			- With user ignored the invitation, User isn't displayed on user's network list*/

		info("Click on Connections on the left panel");
		hp.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username2);
		connMag.connectToAUser(username3);

		info("Test Case 01 + Test Case 06: User can accept or deny invitaions");
		info("Login by invited users, go to My Connections/Requests Received and accept invitation");
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.acceptAConnection(username1);

		info("Login by invited users, go to My Connections/Requests Received and ignore invitation");
		magAc.signIn(username3, password);
		hp.goToConnections();	
		connMag.acceptAConnection(username1);

		info("Verify after invitation");
		magAc.signIn(username1, password);
		hp.goToConnections();
		info("With user confirmed the invitation, user  becomes friend and user's name is displayed on user's network list ");
		connMag.verifyConnection(username2, true);
		info("With user ignored the invitation, User isn't displayed on user's network list");
		connMag.verifyConnection(username3, false);

	}
	
	/**
	 *<li> Case ID:121899.</li>
	 *<li> Test Case Name: Network.</li>
	 */
	@Test
	public void test02_Network(){
		info("test02_Network");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		
		/*Step Number: 1
		 *Step Name: Step 1: Open Network list
		 *Step Description: 
			- Login intranet home
			- Access people list, invite an user
			- Invited user accept invitation
			- Go to My Connections 
			- Click Remove Connection button
		 *Input Data: 
		 *Expected Outcome: 
			- Display list of people
			- Invitation is sent to user, Connect button is changed to Cancel Request
			- With user confirmed the invitation, user  becomes friend and user's name is displayed on user's network list 
			- With user ignored the invitation, User isn't displayed on user's network list*/

		info("Click on Connections on the left panel");
		hp.goToConnections();

		info("Access people list, invite an user");
		connMag.connectToAUser(username2);

		info("Invited user accept invitation");
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.acceptAConnection(username1);
		info("Verify after accept");
		connMag.verifyConnection(username1, true);
		
		info("Go to My Connections and Click Remove Connection button");
		hp.goToConnections();
		connMag.removeConnection(username1);
		info("Verify after remove invitation");
		connMag.verifyConnection(username1, false);
		
		info("User can re-add friend");
		magAc.signIn(username1, password);
		hp.goToConnections();
		connMag.connectToAUser(username2);

	}
	
	/**
	 *<li> Case ID:121907.</li>
	 *<li> Test Case Name: View Connections list of other user.</li>
	 */
	@Test
	public void test03_ViewConnectionsListOfOtherUser(){
		info("test03_ViewConnectionsListOfOtherUser");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+ mailSuffixData.getMailSuffixRandom();
		
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: View Connections list
		 *Step Description: 
			- Click on name or avatar
			- Click on My network tab
		 *Input Data: 
		 *Expected Outcome: 
			- Display user's relation*/
		
		info("Click on Connections on the left panel");
		hp.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username2);
		connMag.connectToAUser(username3);

		info("Login by invited users, go to My Connections from avatar and accept invitation");
		info("Verify before accept John's invitaion");
		magAc.signIn(username2, password);
		navTool.goToMyConnection();
		connMag.verifyConnection(username1, false);
		info("Accept John'sinvitaion");
		hp.goToConnections();	
		connMag.acceptAConnection(username1);
		info("Verify after accept John's invitaion");
		navTool.goToMyConnection();
		connMag.verifyConnection(username1, true);
		
		info("Login by invited users, go to My Connections/Requests Received and accept invitations");
		magAc.signIn(username3, password);
		hp.goToConnections();	
		connMag.acceptAConnection(username1);
		
		info("Login by John and check Connections by click on My Connections in Avartar");
		magAc.signIn(username1, password);
		navTool.goToMyConnection();
		connMag.verifyConnection(username2, true);
		connMag.verifyConnection(username3, true);

	}
	
	/**
	 *<li> Case ID:121894.</li>
	 *<li> Test Case Name: Check People listing.</li>
	 */
	@Test
	public void test04_CheckPeopleListing(){
		info("test04_CheckPeopleListing");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+ mailSuffixData.getMailSuffixRandom();
		String username4 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email4 = username4+ mailSuffixData.getMailSuffixRandom();
		String username5 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email5 = username5+ mailSuffixData.getMailSuffixRandom();
		
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		addUserPage.addUser(username4, password, email4, username4, username4);
		addUserPage.addUser(username5, password, email5, username5, username5);
		magAc.signIn(username1, password);

		/*Step Number: 1
		 *Step Name: Step 1: People listing
		 *Step Description: 
			- Login and go to intranet home page
			- Click on Connections on the left panel
		 *Input Data: 
		 *Expected Outcome: 
			- Show all users on Social and user can send friend request to connect with other users*/
		info("Click on Connections on the left panel");
		hp.goToConnections();

		info("Show all users on Social and user can send friend request to connect with other users");
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CONNECT_BTN.replace("${user}", username2));
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CONNECT_BTN.replace("${user}", username3));
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CONNECT_BTN.replace("${user}", username4));
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CONNECT_BTN.replace("${user}", username5));
	}
	
	/**
	 *<li> Case ID:121900.</li>
	 *<li> Test Case Name: Pending Requests list.</li>
	 *<li> Case ID:121901.</li>
	 *<li> Test Case Name: Received Invitations</li>
	 */
	@Test
	public void test05_06_PendingRequestsList(){	
		info("test05_PendingRequestsList");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		
		/*Step Number: 1
		 *Step Name: Step 1:  Open Pending Requests list
		 *Step Description: 
			- Login intranet home
			- Access people list, invite an user
			- Go to My Connections/Requests Sent
			- Click Cancel Request
		 *Input Data: 
		 *Expected Outcome: 
			- Display all user's requests
			- Requested user is removed from list*/
		info("Click on Connections on the left panel");
		hp.goToConnections();
		info("Click on Connect button to invite an user");
		connMag.connectToAUser(username2);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}", username2));
		connMag.goToConnectionTab(selectTabOption.PENDING);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}", username2));

		info("Cancel request");
		connMag.cancelConnection(username2);	
		
		info("test06_ReceivedInvitations");
		hp.goToConnections();
		connMag.goToConnectionTab(selectTabOption.ALL);
		connMag.connectToAUser(username2);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}", username2));
		
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username1));
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}",username1));
		
	}
}
