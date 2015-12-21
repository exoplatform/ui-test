package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.testng.annotations.*;


public class SOC_People_FindConnections extends SOC_TestConfig{

	/**
	 *<li> Case ID:122750.</li>
	 *<li> Test Case Name: Create connection with user in your contact.</li>
	 *<li> Pre-Condition: Have 2 users: user1, user2</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CreateConnectionWithUserInYourContact() {
		/*Create data test*/
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
		info("Test 1: Create connection with user in your contact");
		/*Step Number: 1
		 *Step Name: Step 1: Go to people page
		 *Step Description: 
			- Sign in system as user1
			- Click [Connections] link on left navigation
		 *Input Data: 

		 *Expected Outcome: 
			- Connection page is shown
			- Tab Every one is shown by default. All users are listed.*/
		info("Click on Connections on the left panel");
		hp.goToConnections();

		/*Step number: 2
		 *Step Name: Step 2: Invite user
		 *Step Description: 
			- Click [Connect] button on user (user2) with whom user1 has no connection
		 *Input Data: 

		 *Expected Outcome: 
			- Connection request is sent to user2
			- The button [Connect] is changed to [Cancel request] button respectively*/
		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username2);

		/*Step number: 3
		 *Step Name: Step 3: Accept the friend
		 *Step Description: 
			- Sign in with user2 which invited at step 2
			- Click [Connections] link on left navigation
			- Click on button [Confirm] on user1
		 *Input Data: 

		 *Expected Outcome: 
			- A connection between two users is created. 
			- The buttons [Confirm], [Ignore] disappear from user1
			- The button [Remove connection] is shown on user1.*/
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		connMag.acceptAConnection(username1);

		/*Step number: 4
		 *Step Name: Step 4: Try to re
		-invite connection
		 *Step Description: 
			- Find [Connect] button on user1
		 *Input Data: 

		 *Expected Outcome: 
			- There isn't anymore other buttons except for [Remove connection] on user1. So user2 cannot re
			-invite user1 again.*/ 
		magAc.signIn(username1,password);
		hp.goToConnections();	
		connMag.searchPeople(username2,null,null,null);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}",username2),2000,1);
	}

	/**
	 *<li> Case ID:122709.</li>
	 *<li> Test Case Name: Invite User.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_InviteUser() {
		/*Create data test*/
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
		info("Test 2: Invite User");
		/*Step Number: 1
		 *Step Name: Step 1: Go to Connections page
		 *Step Description: 
			- Login by User1
			- Click on [Connection] on the left panel
		 *Input Data: 

		 *Expected Outcome: 
			- Show connection page*/
		info("Click on Connections on the left panel");
		hp.goToConnections();

		/*Step number: 2
		 *Step Name: Step 2: Invite user
		 *Step Description: 
			- Click [Connect] button of a use you want to send invitation
		 *Input Data: 

		 *Expected Outcome: 
			- Invitation is sent
			- [Connect] button is changed to [Cancel request]*/
		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username2);

		/*Step number: 3
		 *Step Name: Step 3: Check user after invited
		 *Step Description: 
			- Login as user 2
			- Click on [Connections] on the left panel
			- Check invitation
		 *Input Data: 

		 *Expected Outcome: 
			- Display: Confirm and Ignore button bellow User1's avatar*/ 
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.searchPeople(username1,null,null,null);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username1),2000,1);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}",username1),2000,1);
	}

	/**
	 *<li> Case ID:122734.</li>
	 *<li> Test Case Name: Remove connection.</li>
	 *<li> Case ID:122748.</li>
	 *<li> Test Case Name: User accept invitation.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: User1 is connected with User2</li>
	 */
	@Test
	public  void test03_09_AcceptRemoveConnection() {
		/*Create data test*/
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
		
		info("Click on Connections on the left panel");
		hp.goToConnections();
		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username2);

		info("Test 9: User accept invitation");
		/*Step Number: 1
		 *Step Name: Step 1: Accept the invitation
		 *Step Description: 
			- Login as User2
			- Click [Connections] on the left panel
			- Click [Confirm] on invitation
		 *Input Data: 

		 *Expected Outcome: 
			- Connections page is shown
			- Invitation is accepted
			- Button is changed to "Remove connection]*/ 

		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		connMag.acceptAConnection(username1);

		info("Test 3: Remove connection");
		/*Step Number: 1
		 *Step Name: Step 1: Remove connection
		 *Step Description: 
			- Login as User1
			- Click [Connections] page on the left panel
			- Click [Remove Connection]
		 *Input Data: 

		 *Expected Outcome: 
			- Connection is removed
			- Button is changed to [Connect]*/ 
		magAc.signIn(username1,password);
		hp.goToConnections();	
		hp.goToConnections();	
		connMag.removeConnection(username2);
	}

	/**
	 *<li> Case ID:122742.</li>
	 *<li> Test Case Name: Search by name.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_SearchByName() {
		info("Test 4: Search by name");
		/*Step Number: 1
		 *Step Name: -Step 1: Go to Connections page
		 *Step Description: 
			- Log in
			- Click [Connections] on the left panel
		 *Input Data: 

		 *Expected Outcome: 
			Show content of people page with a list of users exited in system*/
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
		Utils.pause(3000);
		hp.goToConnections();

		/*Step number: 2
		 *Step Name: - Step 2: Search user
		 *Step Description: 
			- On search by name text box: input some text
			- Click on search button or hit Enter on Keyboard
		 *Input Data: 

		 *Expected Outcome: 
			- Display all matching results*/ 
		connMag.searchPeople(username2,null,null,null);
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}",username2));
		waitForElementNotPresent(connMag.ELEMENT_USER_LINK.replace("${userName}",username3));
	}

	/**
	 *<li> Case ID:122739.</li>
	 *<li> Test Case Name: Search by name when text box search is blank.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_SearchByNameWhenTextBoxSearchIsBlank() {
		info("Test 5: Search by name when text box search is blank");
		/*Step Number: 1
		 *Step Name: Step 1: Go to [Connections] page
		 *Step Description: 
			- Log in
			- Click [Connections] on the left panel
		 *Input Data: 

		 *Expected Outcome: 
			Show content of Connections page with a list of users exited in system*/
		String username1 = "a" + getRandomString();
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
		Utils.pause(3000);
		hp.goToConnections();

		/*Step number: 2
		 *Step Name: Step 2: Search user
		 *Step Description: 
			- Leave Search by name text box blank, click Search button or hit Enter on Keyboard
		 *Input Data: 

		 *Expected Outcome: 
			Return all existing users in list. Users are ordered by last name.*/ 
		connMag.clearSearchTextbox();
		connMag.searchPeople(null,null,null,null);
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}",username1));
	}

	/**
	 *<li> Case ID:122779.</li>
	 *<li> Test Case Name: Search when click on a alphabet.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_SearchWhenClickOnAAlphabet() {
		info("Test 6: Search when click on a alphabet");
		/*Step Number: 1
		 *Step Name: - Step 1: Go to people page
		 *Step Description: 
			- Log in
			- Go to Connections page.
		 *Input Data: 

		 *Expected Outcome: 
			Show content of people page with a list of users exited in system*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = getRandomString()+" William";
		String email2 = username2+"@gmail.com";
		String username3 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email3 = username3+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		addUserPage.addUser(username3, password, email3, username3, username3);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		hp.goToConnections();
		/*Step number: 2
		 *Step Name: - Step 2: Search user
		 *Step Description: 
			- Click on Everyone tab: select a letter and click on it
		 *Input Data: 

		 *Expected Outcome: 
			Show all the users which has last name start by the letter or show message: $number Results are displayed in alphabetical order*/ 
		connMag.clearSearchTextbox();
		connMag.searchPeople(null,null,null,"W");
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}",username2));
		waitForElementNotPresent(connMag.ELEMENT_USER_LINK.replace("${userName}",username1));
		waitForElementNotPresent(connMag.ELEMENT_USER_LINK.replace("${userName}",username3));
		connMag.clearSearchTextbox();
	}

	/**
	 *<li> Case ID:122746.</li>
	 *<li> Test Case Name: Search when click on All.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_SearchWhenClickOnAll() {
		info("Test 7: Search when click on All");
		/*Step Number: 1
		 *Step Name: Step 1: Go to Connections page
		 *Step Description: 
			- Login
			- Click [Connections] on the left panel
		 *Input Data: 

		 *Expected Outcome: 
			Show content of Connections page with a list of users exited in system*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = "Ab" + getRandomString();
		String email2 = username2+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		hp.goToConnections();
		/*Step number: 2
		 *Step Name: Step 2: Search user
		 *Step Description: 
			- On search people directory: Click on All
		 *Input Data: 

		 *Expected Outcome: 
			Show all the exited users in system. Users are ordered by last name.*/ 
		connMag.clearSearchTextbox();
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}",username2));
	}

	/**
	 *<li> Case ID:122708.</li>
	 *<li> Test Case Name: The both users send friend requests.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test08_TheBothUsersSendFriendRequests() {
		info("Test 8: The both users send friend requests");
		/*Create data test*/
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
		
		hp.goToConnections();
		connMag.searchPeople(username2,null,null,null);

		/*Step Number: 1
		 *Step Name: Step1: User1 invite User2 to connect
		 *Step Description: 
			- Login as User1
			- Login as User2 on other browser
			- Both 2 users click [Connections] page on the left panel
			- Click Connect button to invite User2
		 *Input Data: 

		 *Expected Outcome: 
			- Invitation is sent to User2
			- Connect button is changed to Cancel request*/
		initNewDriver();
		newDriver.get(baseUrl);
		ManageLogInOut  acc = new ManageLogInOut(newDriver);
		HomePagePlatform newhp= new HomePagePlatform(newDriver);
		ConnectionsManagement conn = new ConnectionsManagement(newDriver);
		acc.signIn(username2, password);
		newhp.goToConnections();	
		conn.searchPeople(username1,null,null,null);
		conn.connectToAUser(username1);

		/*Step number: 2
		 *Step Name: Step 2: User 2 invite User1 on other browser
		 *Step Description: 
			- User2 on other browser click on [Connect] button to invite User1
		 *Input Data: 

		 *Expected Outcome: 
			- Message "Your request has been sent." is displayed
			- [Connect] button is changed to [Confirm] and [Ignore]*/ 
		click(connMag.ELEMENT_CONNECTION_CONNECT_BTN.replace("${user}",username2));
		alert.verifyAlertMessage("Your request has been sent");
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username2),2000,1);

		newDriver.quit();
	}

	/**
	 *<li> Case ID:122747.</li>
	 *<li> Test Case Name: User Ignore invitation.</li>
	 *<li> Pre-Condition: Invitation is sent to user2</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test10_UserIgnoreInvitation() {
		/*Create data test*/
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
		Utils.pause(3000);
		info("Click on Connections on the left panel");
		hp.goToConnections();
		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username2);

		info("Test 10 User Ignore invitation");
		/*Step Number: 1
		 *Step Name: Step 1: Accept the invitation
		 *Step Description: 
			- Login as User2
			- Click [Connections] on the left panel
			- Click [Ignore] on invitation
		 *Input Data: 

		 *Expected Outcome: 
			- Connections page is shown
			- Invitation is rejected
			- Button is changed to [Connect]*/ 
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		connMag.ignoreConnection(username1);
	}
}