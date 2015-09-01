package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class SOC_People_MyConnections extends SOC_TestConfig{

	/**
	 *<li> Case ID:122731.</li>
	 *<li> Test Case Name: Check request pending after inviting an user.</li>
	 *<li> Case ID:122713.</li>
	 *<li> Test Case Name: Accept the invitation.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_06_AcceptTheInvitation() {
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);

		info("Test 6: Check request pending after inviting an user");
		/*Step Number: 1
		 *Step Name: Step 1: Go to My Connection page
		 *Step Description: 
			- Login as User1
			- Go to My Connections page, Everyone tab.
		 *Input Data: 

		 *Expected Outcome: 
			- Show content of Connections page*/

		/*Step number: 2
		 *Step Name: Step 2: Invite user
		 *Step Description: 
			- Select user User2 who has no connection with User1.
			- Click on [Connect] button
		 *Input Data: 

		 *Expected Outcome: 
			- Invitation is sent to User2 successfully. 
			- User2 is listed in Request Pending list*/
		info("Click on Connections on the left panel");
		hp.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username1);
		connMag.goToConnectionTab(selectTabOption.PENDING);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username1),2000,1);

		info("Test 1: Accept the invitation");
		/*Step number: 3
		 *Step Name: Step 3: Ignore a request
		 *Step Description: 
			- Login as User2
			- Go to My Connections page, Select Requests Received tab
			- Click on button [Confirm]
		 *Input Data: 

		 *Expected Outcome: 
			- User1 is listed in Requests Received list of User2. 
			- There are [Confirm] and [Ignore]
			- After clicking on [Confirm] button, User1 will be removed from Requests Received tab of User2.*/ 
		magAc.signIn(username1, password1);
		hp.goToConnections();	
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		connMag.acceptAConnection(DATA_USER1);
	}

	/**
	 *<li> Case ID:122735.</li>
	 *<li> Test Case Name: Cancel a invitation request.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CancelAInvitationRequest() {
		info("Test 2: Cancel a invitation request");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		/*Step Number: 1
		 *Step Name: Step 1: Go to Connections page
		 *Step Description: 
			- Login as User1
			- Go to Connections page
		 *Input Data: 

		 *Expected Outcome: 
			Connections page is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Invite User2
		 *Step Description: 
			- In Everyone tab
			- Click on [Connect] button to connect with User2
		 *Input Data: 

		 *Expected Outcome: 
			- Invitation is sent to User2*/
		info("Click on Connections on the left panel");
		hp.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username1);
		connMag.goToConnectionTab(selectTabOption.PENDING);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username1),2000,1);

		/*Step number: 3
		 *Step Name: Step 3: Cancel Request
		 *Step Description: 
			- In Pending Request tab, click [Cancel Request] button
		 *Input Data: 

		 *Expected Outcome: 
			- The request is cancelled, [Cancel Request] button is changed to [Connect] button*/ 
		connMag.cancelConnection(username1);
	}

	/**
	 *<li> Case ID:122730.</li>
	 *<li> Test Case Name: Cancel request when user Denied invitation.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CancelRequestWhenUserDeniedInvitation() {
		info("Test 3: Cancel request when user Denied invitation");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		/*Step Number: 1
		 *Step Name: Step 1: Go to My Connections page (browser 1)
		 *Step Description: 
			- Login as User1 on browser1
			- Move mouse over Username
			- Select [My Connections]
		 *Input Data: 

		 *Expected Outcome: 
			- Show content of My Connections page*/

		/*Step number: 2
		 *Step Name: Step 2: Invite user
		 *Step Description: 
			- Select user who has no relation with user to add relation
			- Click on [Connect] (User2)
		 *Input Data: 

		 *Expected Outcome: 
			Invited user successfully*/
		info("Click on Connections on the left panel");
		hp.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username1);

		/*Step number: 3
		 *Step Name: Step 3: Open pending request list
		 *Step Description: 
			- Click [Pending Request] tab
		 *Input Data: 

		 *Expected Outcome: 
			-Show user who invited by user login*/
		connMag.goToConnectionTab(selectTabOption.ALL);
		connMag.searchPeople(username1,null,null,null);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username1),2000,1);

		/*Step number: 4
		 *Step Name: Step 4: User2 Ignore the invitation on browser 2
		 *Step Description: 
			- Login as User2 on other browser
			- Go to My Connections page
			- Click [Request Received] tab
			- Click [Ignore] button to deny invitation from User1
		 *Input Data: 

		 *Expected Outcome: 
			- The invitation is denied*/
		initNewDriver();
		newDriver.get(baseUrl);
		ManageLogInOut  acc = new ManageLogInOut(newDriver);
		HomePagePlatform newhp= new HomePagePlatform(newDriver);
		ConnectionsManagement conn = new ConnectionsManagement(newDriver);
		acc.signIn(username1, password1);
		newhp.goToConnections();	
		conn.goToConnectionTab(selectTabOption.RECEIVE);
		conn.ignoreConnection(DATA_USER1);

		/*Step number: 5
		 *Step Name: Step 5: Cancel Request by User1 on browser1
		 *Step Description: 
			- User1 click [Cancel Request] button
		 *Input Data: 

		 *Expected Outcome: 
			- Message "Request was canceled or deleted by another." is shown
			- Button [Cancel Request] is changed to [Connect]*/ 
		click(connMag.ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username1));
		alert.verifyAlertMessage("Request was canceled or deleted by another");
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CONNECT_BTN.replace("${user}",username1),2000,1);
		
		newDriver.quit();
	}

	/**
	 *<li> Case ID:122711.</li>
	 *<li> Test Case Name: Check information of user in Pending Request list.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CheckInformationOfUserInPendingRequestList() {
		info("Test 4: Check information of user in Pending Request list");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		magAc.signIn(username1, password1);
		navTool.goToMyActivities();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		/*Step Number: 1
		 *Step Name: Step 1: Go to My Connections
		 *Step Description: 
			- Login 
			- Move mouse over Username
			- Select My Connections page
		 *Input Data: 

		 *Expected Outcome: 
			- Show content of My Connections page*/

		/*Step number: 2
		 *Step Name: Step 2: Invite user
		 *Step Description: 
			- Click [Everyone tab]
			- Click [Connect] button of a user who is not friend
		 *Input Data: 

		 *Expected Outcome: 
			Invited user successfully*/
		info("Click on Connections on the left panel");
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username1);

		/*Step number: 3
		 *Step Name: Step 3: Check information of user
		 *Step Description: 
			- Click [Pending Request] tab
			- Click on Username or avatar of User who is sent the invitation above
		 *Input Data: 

		 *Expected Outcome: 
			Show all information of user includes:
			- Activities: can see activities of user
			- Profile: Show profile of user
			- Connections: show all users which have relations with usera*/ 
		connMag.goToConnectionTab(selectTabOption.PENDING);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username1),2000,1);
		connMag.goToUserByUserName(username1);
		uBase.goToActivityTab();
		waitForAndGetElement(By.xpath(hpAct.ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", activity1)));
		uBase.goToProfileTab();
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email1));
		uBase.goToConnectionTab();
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER1));
	}

	/**
	 *<li> Case ID:122714.</li>
	 *<li> Test Case Name: Check information of user in Request Received list.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_CheckInformationOfUserInRequestReceivedList() {
		info("Test 5: Check information of user in Request Received list");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);
		magAc.signIn(username1, password1);
		navTool.goToMyActivities();
		String activity1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity1, "");
		/*Step Number: 1
		 *Step Name: Step 1: Go to My Connections
		 *Step Description: 
			- Login 
			- Move mouse over Username
			- Select My Connections page
		 *Input Data: 

		 *Expected Outcome: 
			- Show content of My Connections page*/

		/*Step number: 2
		 *Step Name: Step 2: Invite user
		 *Step Description: 
			- Click [Everyone tab]
			- Click [Connect] button of a user who is not friend
		 *Input Data: 

		 *Expected Outcome: 
			Invited user successfully*/
		info("Click on Connections on the left panel");
		magAc.signIn(username1, password1);
		hp.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(DATA_USER1);

		/*Step number: 3
		 *Step Name: Step 3: Check information of user
		 *Step Description: 
			- Login as invited user
			- Click [Request Received] tab
			- Click on Username or avatar of User who is sent the invitation above
		 *Input Data: 

		 *Expected Outcome: 
			Show all information of user includes:
			- Activities: can see activities of user
			- Profile: Show profile of user
			- Connections: show all users which have relations with usera*/ 
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToConnections();
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username1),2000,1);
		connMag.goToUserByUserName(username1);
		uBase.goToActivityTab();
		waitForAndGetElement(By.xpath(hpAct.ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", activity1)));
		uBase.goToProfileTab();
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email1));
		uBase.goToConnectionTab();
		connMag.goToConnectionTab(selectTabOption.PENDING);
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER1));
	}

	/**
	 *<li> Case ID:122712.</li>
	 *<li> Test Case Name: Ignore the invitation.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_IgnoreTheInvitation() {
		info("Test 7: Ignore the invitation");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);

		/*Step Number: 1
		 *Step Name: Step 1: Go to Connection page
		 *Step Description: 
			- Login as User1
			- Go to My Connections page, Everyone tab.
		 *Input Data: 

		 *Expected Outcome: 
			- Show content of Connections page*/

		/*Step number: 2
		 *Step Name: Step 2: Invite user
		 *Step Description: 
			- Select user User2 who has no connection with User1.
			- Click on [Connect] button
		 *Input Data: 

		 *Expected Outcome: 
			- Invitation is sent to User2 successfully. 
			- User2 is listed in Request Pending list*/
		info("Click on Connections on the left panel");
		hp.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username1);
		connMag.goToConnectionTab(selectTabOption.PENDING);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username1),2000,1);

		/*Step number: 3
		 *Step Name: Step 3: Ignore a request
		 *Step Description: 
			- Login as User2
			- Go to My Connections page, Select Requests Received tab
			- Click on button [Ignore]
		 *Input Data: 

		 *Expected Outcome: 
			- User1 is listed in Requests Received list of User2. 
			- There are [Confirm] and [Ignore]
			- After clicking on [Ignore] button, User1 will be removed from Requests Received tab of User2.*/ 
		magAc.signIn(username1, password1);
		hp.goToConnections();	
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		connMag.ignoreConnection(DATA_USER1);
	}

	/**
	 *<li> Case ID:122710.</li>
	 *<li> Test Case Name: Remove Connection from My Connections page.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test08_RemoveConnectionFromMyConnectionsPage() {
		info("Test 8: Remove Connection from My Connections page");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String password1 = username1;
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();

		info("Add new user");
		magAc.signIn(DATA_USER1, DATA_PASS);
		navTool.goToAddUser();
		addUserPage.addUser(username1, password1, email1, username1, username1);

		/*Step Number: 1
		 *Step Name: Step 2: Connect User2
		 *Step Description: 
			- Select user who has no relation with user to add relation
			- Click on [Connect] (User2)
		 *Input Data: 

		 *Expected Outcome: 
			- Invitation is sent*/
		info("Click on Connections on the left panel");
		hp.goToConnections();

		info("Click on Connect button to invite about 2 users");
		connMag.connectToAUser(username1);
		connMag.goToConnectionTab(selectTabOption.PENDING);
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username1),2000,1);

		/*Step number: 2
		 *Step Name: Step 3: Accept the invitation
		 *Step Description: 
			- Login as User2
			- Go to My connections page 
			- Choose Request Received tab
			- Click [Confirm] button
		 *Input Data: 

		 *Expected Outcome: 
			- Invitation is accepted*/
		magAc.signIn(username1, password1);
		hp.goToConnections();	
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		connMag.acceptAConnection(DATA_USER1);

		/*Step number: 3
		 *Step Name: Step 4: Open My Connections list
		 *Step Description: 
			- Select [My connections] tab
			- Click [Remove Connection] to remove User2
		 *Input Data: 

		 *Expected Outcome: 
			- Connection is removed
			- Button [Remove Connection] is changed to [Connect]*/ 
		hp.goToConnections();	
		connMag.goToConnectionTab(selectTabOption.MYCONNECTION);
		connMag.removeConnection(DATA_USER1);
	}

	/**
	 *<li> Case ID:122729.</li>
	 *<li> Test Case Name: Search by name.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test09_SearchByName() {
		info("Test 9: Search by name");
		/*Step Number: 1
		 *Step Name: -Step 1: Go to Connections page
		 *Step Description: 
			- Log in
			- Click [Connections] on the left panel
		 *Input Data: 

		 *Expected Outcome: 
			Show content of people page with a list of users exited in system*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToConnections();

		/*Step number: 2
		 *Step Name: - Step 2: Search user
		 *Step Description: 
			- On search by name text box: input some text
			- Click on search button or hit Enter on Keyboard
		 *Input Data: 

		 *Expected Outcome: 
			- Display all matching results*/ 
		connMag.searchPeople(DATA_USER2,null,null,null);
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER2));
		waitForElementNotPresent(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER3));
		waitForElementNotPresent(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER4));
	}

	/**
	 *<li> Case ID:122722.</li>
	 *<li> Test Case Name: Search by name when text box search is blank.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test10_SearchByNameWhenTextBoxSearchIsBlank() {
		info("Test 10 Search by name when text box search is blank");
		/*Step Number: 1
		 *Step Name: Step 1: Go to [Connections] page
		 *Step Description: 
			- Log in
			- Click [Connections] on the left panel
		 *Input Data: 

		 *Expected Outcome: 
			Show content of Connections page with a list of users exited in system*/
		magAc.signIn(DATA_USER1, DATA_PASS);
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
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER3));
	}

	/**
	 *<li> Case ID:122723.</li>
	 *<li> Test Case Name: Search when click on a alphabet.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test11_SearchWhenClickOnAAlphabet() {
		info("Test 11 Search when click on a alphabet");
		/*Step Number: 1
		 *Step Name: - Step 1: Go to people page
		 *Step Description: 
			- Log in
			- Go to Connections page.
		 *Input Data: 

		 *Expected Outcome: 
			Show content of people page with a list of users exited in system*/
		magAc.signIn(DATA_USER1, DATA_PASS);
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
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER2));
		waitForElementNotPresent(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER3));
		waitForElementNotPresent(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER4));
		connMag.clearSearchTextbox();
	}

	/**
	 *<li> Case ID:122728.</li>
	 *<li> Test Case Name: Search when click on All.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test12_SearchWhenClickOnAll() {
		info("Test 12 Search when click on All");
		/*Step Number: 1
		 *Step Name: Step 1: Go to Connections page
		 *Step Description: 
			- Login
			- Click [Connections] on the left panel
		 *Input Data: 

		 *Expected Outcome: 
			Show content of Connections page with a list of users exited in system*/
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToConnections();

		/*Step number: 2
		 *Step Name: Step 2: Search user
		 *Step Description: 
			- On search people directory: Click on All
		 *Input Data: 

		 *Expected Outcome: 
			Show all the exited users in system. Users are ordered by last name.*/ 
		connMag.clearSearchTextbox();
		waitForAndGetElement(connMag.ELEMENT_USER_LINK.replace("${userName}", DATA_USER3));
	}
}