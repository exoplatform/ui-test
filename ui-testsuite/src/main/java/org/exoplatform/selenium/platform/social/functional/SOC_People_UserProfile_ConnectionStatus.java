package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.testng.annotations.*;


public class SOC_People_UserProfile_ConnectionStatus extends SOC_TestConfig{

	/**
	 *<li> Case ID:125195.</li>
	 *<li> Test Case Name: Check connection button when 2 users are connected.</li>
	 *<li> Pre-Condition: - test 1 and test 2 are connected</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckConnectionButtonWhen2UsersAreConnected() {
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
		magAc.signIn(username2, password);
		hp.goToConnections();	
		connMag.goToConnectionTab(selectTabOption.RECEIVE);
		connMag.acceptAConnection(username1);
		magAc.signIn(username2,password);

		info("Test 1: Check connection button when 2 users are connected");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to profile page
		 *Step Description: 
			- Login with test1
			- Go to profile page of test2
		 *Input Data: 

		 *Expected Outcome: 
			- The profile page of test2 is displayed*/
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username1,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username1));

		/*Step number: 2
		 *Step Name: Step 2 : Check connection buttons
		 *Step Description: 
			- Check the connection button displayed in the page
		 *Input Data: 

		 *Expected Outcome: 
			- The portlet shows a button Connected.
			- The button is labeled Connected with a tick icon.*/
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_CONNECTED_STATUS);
		/*Step number: 3
		 *Step Name: Step 3 : Mouseover on Connected
		 *Step Description: 
			- Mouseover the button Connected
		 *Input Data: 

		 *Expected Outcome: 
			- On mouse over, the button becomes Disconnect : * The icon to a disconnect icon* The label to Disconnect*/ 
		mouseOver(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_CONNECTED_STATUS,true);
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_DISCONNECTED_STATUS);

	}

	/**
	 *<li> Case ID:125196.</li>
	 *<li> Test Case Name: Check connection button when 2 users are not connected.</li>
	 *<li> Pre-Condition: - test1 and test2 are not connected</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckConnectionButtonWhen2UsersAreNotConnected() {
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

		info("Test 2: Check connection button when 2 users are not connected");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to profile page
		 *Step Description: 
			- Login with test1
			- Go to profile page of test2
		 *Input Data: 

		 *Expected Outcome: 
			- The profile page of test2 is displayed*/
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username2,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username2));

		/*Step number: 2
		 *Step Name: Step 2 : Check connection buttons
		 *Step Description: 
			- Check the connection button displayed in the page
		 *Input Data: 

		 *Expected Outcome: 
			- The portlet shows a button Connect*/ 
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS);
	}

	/**
	 *<li> Case ID:125198.</li>
	 *<li> Test Case Name: Check connection button when a request is denied.</li>
	 *<li> Pre-Condition: - test1 sent a connection request to test2</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CheckConnectionButtonWhenARequestIsDenied() {
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

		info("Test 3: Check connection button when a request is denied");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to profile page and check connection buttons
		 *Step Description: 
			- Login with test2
			- Go to test1 profile
			- Check the connection buttons displayed in the page
		 *Input Data: 

		 *Expected Outcome: 
			- The portlet shows two buttons : * Accept Request * Deny*/
		magAc.signIn(username2, password);
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username1,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}",username1));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_ACCEPT_STATUS);
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_DENY_STATUS);

		/*Step number: 2
		 *Step Name: Step 2 : Deny connection request
		 *Step Description: 
			- Deny connection request
			- Check connection button displayed in the page
		 *Input Data: 

		 *Expected Outcome: 
			- The portlet shows a button Connect*/
		click(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_DENY_STATUS);
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS);

		/*Step number: 3
		 *Step Name: Step 3 : check connection buttons
		 *Step Description: 
			- Login with test1
			- Go to profile of test2
			- Check the connection button displayed in the page
		 *Input Data: 

		 *Expected Outcome: 
			- The portlet shows a button Connect*/ 
		magAc.signIn(username1,password);
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username2,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username2));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS);
	}

	/**
	 *<li> Case ID:125197.</li>
	 *<li> Test Case Name: Check connection buttons when a request is sent and pending.</li>
	 *<li> Pre-Condition: - test1 sent a connection request to test2</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CheckConnectionButtonsWhenARequestIsSentAndPending() {
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

		info("Test 4: Check connection buttons when a request is sent and pending");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to profile page and check connection buttons
		 *Step Description: 
			- Login with test1
			- Go to test2 profile
			- Check the connection button displayed in the page
		 *Input Data: 

		 *Expected Outcome: 
			- The portlet shows a button "Cancel Request"*/
		hp.goToConnections();
		connMag.searchPeople(username2,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username2));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_CANCEL_STATUS);

		/*Step number: 2
		 *Step Name: Step 2 : Check connection buttons
		 *Step Description: 
			- Login with test2
			- Go to test1 profile
			- Check the connection buttons displayed in the page
		 *Input Data: 

		 *Expected Outcome: 
			- The portlet shows two buttons : * Accept Request * Deny*/ 
		magAc.signIn(username2, password);
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username1,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}",username1));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_ACCEPT_STATUS);
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_DENY_STATUS);
	}
}