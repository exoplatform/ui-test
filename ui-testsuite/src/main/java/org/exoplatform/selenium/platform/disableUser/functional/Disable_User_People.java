package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.testng.annotations.*;


	public class Disable_User_People extends Disable_User_TestConfig{
		
	/**
	*<li> Case ID:128015.</li>
	*<li> Test Case Name: Check disable user in Invitation gadget.</li>
	*<li> Pre-Condition: User A and User B are not connectedUser A is enabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128013.</li>
	*<li> Test Case Name: Check disable user in Requests Received page.</li>
	*<li> Pre-Condition: User A and User B are not connectedUser A is enabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_03_CheckDisableUserInInvitationGadget() {
		info("Test 1: Check disable user in Invitation gadget");
		createNewUser();
		/*Step Number: 1
		*Step Name: Step 1: Send Connection Invitation
		*Step Description: 
			- Connect to Intranet with User A
			- Go to the Connections app
			- Send a connection invitation to the User B
		*Input Data: 
			
		*Expected Outcome: 
			- Connection request is sent to User B*/
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToConnections();
		connMag.connectToAUser(DATA_USER1);
		
		/*Step number: 2
		*Step Name: Step 2: Checking the NOT display of disabled user on Invitation gadget
		*Step Description: 
			- Login with User B
			- Disable the User A
			- Go to the Intranet Homepage
			- Look at Invitation gadget
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed in the Invitation gadget*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		disableUser();
 	 	hp.checkDisplayInInvitationGadget(user, false);
 	 	
 	 	hp.goToConnections();
 	 	connMag.checkDisplayInConnection(user, false, selectTabOption.RECEIVE);
 	 	
 	 	deleteUser();
 	}

	/**
	*<li> Case ID:127971.</li>
	*<li> Test Case Name: Check disable user in Request Pending page.</li>
	*<li> Pre-Condition: User A and User B are not connectedUser A is enabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDisableUserInRequestPendingPage() {
		info("Test 2: Check disable user in Request Pending page");
		createNewUser();
		/*Step Number: 1
		*Step Name: Step 1: Open Connection Request
		*Step Description: 
			- Connect to Intranet with User B
			- Go to the Connections app
			- Send a connect invitation to the User A
			- Go to the tab "Requests Pending"
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is displayed in the "Requests Pending" page*/
		hp.goToConnections();
		connMag.checkDisplayInConnection(username, true, selectTabOption.ALL);
		connMag.connectToAUser(username);
		connMag.checkDisplayInConnection(username, true, selectTabOption.PENDING);
		
		/*Step number: 2
		*Step Name: Step 2: Check "Connection Request" page after disabling user
		*Step Description: 
			- Disable the User A
			- Back to the "Requests Pending" page
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed in the "Requests Pending" page*/
		disableUser();
 	 	
 	 	hp.goToConnections();
 	 	connMag.checkDisplayInConnection(username, false, selectTabOption.PENDING);
 	 	
		/*Step number: 3
		*Step Name: Step 3: Re-enable User A
		*Step Description: 
			- Re-enable User A
			- Back to the "Requests Pending" page
		*Input Data: 
			
		*Expected Outcome: 
			- The User A is displayed in the "Requests Pending" page*/ 
 	 	enableUser();
 	 	
 	 	hp.goToConnections();
 	 	connMag.checkDisplayInConnection(username, true, selectTabOption.PENDING);
 	 	
 	 	deleteUser();
 	}

	/**
	*<li> Case ID:127970.</li>
	*<li> Test Case Name: Check disable user in Suggestion gadget.</li>
	*<li> Pre-Condition: User A and User B are not connectedUser A is enabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckDisableUserInSuggestionGadget() {
		info("Test 4: Check disable user in Suggestion gadget");
		createNewUser();
		/*Step Number: 1
		*Step Name: Step 1: Connect to intranet
		*Step Description: 
			- Connect to Intranet with User B
		*Input Data: 
			
		*Expected Outcome: 
			- The Suggestion Gadget is displayed
			- User A is displayed as a suggestion*/
		hp.checkDisplayInSuggestionGadget(username,true);
		
		/*Step number: 2
		*Step Name: Step 2: Check Suggestion gadget after disabling user
		*Step Description: 
			- Disable the User A
			- Back to the Homepage
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed in the Gadget "Suggestion"*/ 
		disableUser();
 	 	hp.checkDisplayInSuggestionGadget(username,false);
 	 	
 	 	deleteUser();
 	}
	/**
	*<li> Case ID:127967.</li>
	*<li> Test Case Name: Check disabled user in All people apps.</li>
	*<li> Pre-Condition: User A and User B are connectedUser A is enabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:127966.</li>
	*<li> Test Case Name: Check disabled user in Connections app.</li>
	*<li> Pre-Condition: User A and User B are connectedUser A is enabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_06_CheckDisabledUserInAllPeopleApps() {
		info("Test 5: Check disabled user in All people apps");
		createNewUser();
		hp.goToConnections();
		connMag.connectToAUser(username);
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		/*Step Number: 1
		*Step Name: Step 1: Open "Everyone" tab in Connection application
		*Step Description: 
			- Connect to Intranet with User B
			- Open the Connection application
			- Open "Everyone" tab
		*Input Data: 
			
		*Expected Outcome: 
			- User A is displayed in the list of All people users*/
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		hp.goToConnections();
		connMag.checkDisplayInConnection(username, true, selectTabOption.MYCONNECTION);
		connMag.checkDisplayInConnection(username, true, selectTabOption.ALL);
		
		/*Step number: 2
		*Step Name: Step 2: Check "Everyone" tab in Connection application after disabling user
		*Step Description: 
			- Disable the User A
			- Back to the Connections app, All people tab
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed in the list of All people user*/ 
		disableUser();
 	 	hp.goToConnections();
		connMag.checkDisplayInConnection(username, false, selectTabOption.MYCONNECTION);
 	 	connMag.checkDisplayInConnection(username, false, selectTabOption.ALL);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:128023.</li>
	*<li> Test Case Name: Check the display of user in "My Connections" application after re-enabling.</li>
	*<li> Pre-Condition: User A and User B are connectedUser A is adminUser B is disabled</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128022.</li>
	*<li> Test Case Name: Check the display of user in Every application after re-enabling.</li>
	*<li> Pre-Condition: User A and User B are connectedUser A is adminUser B is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_08_CheckTheDisplayOfUserInMyConnectionsApplicationAfterReenabling() {
		info("Test 7: Check the display of user in My Connections application after re-enabling");
		createNewUser();
		hp.goToConnections();
		connMag.connectToAUser(username);
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: NOT display disabled user on "My Connections" page
		*Step Description: 
			- Connect to Intranet with User A
			- Open the Connection app
			- Open the "My Connections" tab
		*Input Data: 
			
		*Expected Outcome: 
			- User B is NOT displayed in the list of connection users*/
 	 	hp.goToConnections();
		connMag.checkDisplayInConnection(username, false, selectTabOption.MYCONNECTION);
 	 	connMag.checkDisplayInConnection(username, false, selectTabOption.ALL);
 	 	
		/*Step number: 2
		*Step Name: Step 1: Re-display user on "My Connections" page after re-enabling
		*Step Description: 
			- Re-enable User B
			- Back to the Connections app 
			-
			-> My Connections tab
		*Input Data: 
			
		*Expected Outcome: 
			- User B is displayed in the list of connection users*/ 
 	 	enableUser();
 	 	
 	 	hp.goToConnections();
		connMag.checkDisplayInConnection(username, true, selectTabOption.MYCONNECTION);
 	 	connMag.checkDisplayInConnection(username, true, selectTabOption.ALL);
 	 	
 	 	deleteUser();
 	}

	/**
	*<li> Case ID:128026.</li>
	*<li> Test Case Name: Check the display of user in Invitation gadget after re-enabling.</li>
	*<li> Pre-Condition: User B has already sent a connection request to User AUser A is adminUser B is disable</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128024.</li>
	*<li> Test Case Name: Check the display of user in Requests Received page after re-enabling user.</li>
	*<li> Pre-Condition: User A is adminUser B is disableUser B has already sent to User A a connection request</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_11_CheckTheDisplayOfUserInInvitationGadgetAfterReenabling() {
		info("Test 9: Check the display of user in Invitation gadget after re-enabling");
		createNewUser();
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToConnections();
		connMag.connectToAUser(DATA_USER1);
		
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: NOT display disable user on Invitation gadget
		*Step Description: 
			- Connect to Intranet with User A
			- Look at Invitation gadget
		*Input Data: 
			
		*Expected Outcome: 
			- User B is NOT displayed on the Invitation gadget*/
 	 	hp.checkDisplayInInvitationGadget(username, false);
 	 	hp.goToConnections();
		connMag.checkDisplayInConnection(username, false, selectTabOption.RECEIVE);
		
		/*Step number: 2
		*Step Name: Step 2: Re-display user on Invitation gadget after re-enabling
		*Step Description: 
			- Enable User B
			- Go to the Intranet Homepage
			- Look at Invitation gadget
		*Input Data: 
			
		*Expected Outcome: 
			- User B is displayed on the Invitation gadget*/ 
		enableUser();
 	 	
 	 	hp.checkDisplayInInvitationGadget(username, true);
 	 	hp.goToConnections();
		connMag.checkDisplayInConnection(username, true, selectTabOption.RECEIVE);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:128020.</li>
	*<li> Test Case Name: Check the display of user in Request Pending page after re-enabling.</li>
	*<li> Pre-Condition: User A is adminUser B is disableUser A and User B are NOT connected</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckTheDisplayOfUserInRequestPendingPageAfterReenabling() {
		info("Test 10 Check the display of user in Request Pending page after re-enabling");
		createNewUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Send Connection Invitation to disabled user
		*Step Description: 
			- Connect to Intranet with User A
			- Go to the profile of disabled User B via link http://ip-address:8080/portal/intranet/profile/userb
			- Click on "Connect" button
		*Input Data: 
			
		*Expected Outcome: 
			- Connection request is sent to User B.
			- "Cancel Request" button is displayed instead of "Connect" button*/
 	 	myProfile.connectToUserInProfilePage(user);
 	 	
		/*Step number: 2
		*Step Name: Step 2: Checking the display of user on Request Pending page after re-enable
		*Step Description: 
			- Login with User A
			- Re-enable User B
			- Go to Connections application
			- Open "Request Pending" tab
		*Input Data: 
			
		*Expected Outcome: 
			- User B is displayed in the Request Pending page*/ 
 	 	enableUser();
 	 	
 	 	hp.goToConnections();
		connMag.checkDisplayInConnection(username, true, selectTabOption.PENDING);
		
		deleteUser();
 	}

	/**
	*<li> Case ID:128025.</li>
	*<li> Test Case Name: Check the display of user in Suggestion gadget after re-enabling.</li>
	*<li> Pre-Condition: User A and User B are not connectedUser A is adminUser B is disable</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckTheDisplayOfUserInSuggestionGadgetAfterReenabling() {
		info("Test 12 Check the display of user in Suggestion gadget after re-enabling");
		createNewUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: NOT display disabled user in "Suggestions" gadget
		*Step Description: 
			- Connect to Intranet with User A
		*Input Data: 
			
		*Expected Outcome: 
			- User B is NOT displayed in the "Suggestions" gadget*/
 	 	hp.checkDisplayInSuggestionGadget(username, false);
 	 	
		/*Step number: 2
		*Step Name: Step 2: Re-display user in "Suggestions" gadget after re-enabling
		*Step Description: 
			- Re-enable User B
			- Back to the Homepage
		*Input Data: 
			
		*Expected Outcome: 
			- User B is displayed in the "Suggestions" gadget*/ 
 	 	enableUser();
 	 	hp.checkDisplayInSuggestionGadget(username, true);
 	 	
 	 	deleteUser();
 	}

	/**
	*<li> Case ID:127972.</li>
	*<li> Test Case Name: Check the profile page of a disabled user.</li>
	*<li> Pre-Condition: User B is adminUser A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckTheProfilePageOfADisabledUser() {
		info("Test 13 Check the profile page of a disabled user");
		createNewUser();
		hp.goToConnections();
		connMag.connectToAUser(username);
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Connect to Intranet
		*Step Description: 
			- Connect to Intranet with User B
		*Input Data: 
			
		*Expected Outcome: 
			- The Homepage is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Open profile page of the disabled user User A
		*Step Description: 
			- Open the profile page of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- The Profile of User A is accessible and it actions: Connect, Cancel Request, Connect/Disconnect are displayed as usual.*/ 
 	 	myProfile.disconnectInProfilePage(username);
 	 	
 	 	deleteUser();
 	}

	/**
	*<li> Case ID:127968.</li>
	*<li> Test Case Name: Search disabled user in All people app.</li>
	*<li> Pre-Condition: User A and User B are connectedUser A is enabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:127969.</li>
	*<li> Test Case Name: Search disabled user in Connection app.</li>
	*<li> Pre-Condition: User A and User B are connectedUser A is enabledUser B is admin</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_15_SearchDisabledUserInAllPeopleApp() {
		info("Test 14 Search disabled user in All people app");
		createNewUser();
		hp.goToConnections();
		connMag.connectToAUser(username);
		magAc.signOut();
		magAc.signIn(username, password);
		hp.goToConnections();
		connMag.acceptAConnection(DATA_USER1);
		
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		/*Step Number: 1
		*Step Name: Step 1: Open "All people" page
		*Step Description: 
			- Connect to Intranet with User B
			- Open the Connection app
			- Open the "All people" tab
		*Input Data: 
			
		*Expected Outcome: 
			- All people page is displayed
			- User A is displayed in the page*/
		hp.goToConnections();
		connMag.checkDisplayInConnection(username, true,selectTabOption.MYCONNECTION);
		connMag.checkDisplayInConnection(username, true,selectTabOption.ALL);
		
		/*Step number: 2
		*Step Name: Step 2: Search disabled user
		*Step Description: 
			- Disable the User A
			- Back to the Connections app, All people tab
			- Search the User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed the search page*/ 
		disableUser();
 	 	
 	 	hp.goToConnections();
 	 	connMag.checkSearchResultInConnection(username, false,selectTabOption.MYCONNECTION);
 	 	connMag.checkSearchResultInConnection(username, false,selectTabOption.ALL);
 	 	
 	 	deleteUser();
 	}
}