package org.exoplatform.selenium.platform.plf.functional.homepagegadgets.invitationgadget;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageGadget;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.PeopleConnection;
import org.exoplatform.selenium.platform.social.PeopleSearch;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 * @date 28 Jan 2014
 */

public class PLF_HomepageGadgets_InvitationGadget extends Activity{
	ManageAccount acc; 
	NavigationToolbar nav; 
	PeopleConnection pConn; 
	PeopleSearch peoSearch;
	String user = "John Smith";
	String user1= "Jack Miller";	
	String user2= "Mary Williams";
	ManageMember mMember; 
	HomePageGadget hGadget; 
	UserGroupManagement uGroup; 
	SpaceManagement mSpace;

	@BeforeMethod
	public void beforeMethods(){	
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver, this.plfVersion);
		nav = new NavigationToolbar(driver, this.plfVersion);	
		pConn = new PeopleConnection(driver, this.plfVersion);
		mMember = new ManageMember(driver, this.plfVersion);	
		hGadget = new HomePageGadget(driver, this.plfVersion);
		uGroup = new UserGroupManagement(driver, this.plfVersion);
		mSpace = new SpaceManagement(driver, this.plfVersion);
		peoSearch = new PeopleSearch(driver);
		acc.signIn(DATA_USER1, DATA_PASS);		
	}

	/*@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}*/
	
	@AfterMethod
	public void afterMethods(){
		info("clear connections");
		acc.userSignIn(userType.ADMIN);
		nav.goToConnectionPage();
		peoSearch.searchPeople(true,user1);
		info("---Cancel the request to user '"+user1+"'-----");
		if(waitForAndGetElement(pConn.ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", user1), 7000, 0) != null){
			click(pConn.ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", user1));
			waitForElementNotPresent(pConn.ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", user1));
			info("---Go to Everyone tab----");
			click(pConn.ELEMENT_EVERYONE_TAB);
			waitForAndGetElement(pConn.ELEMENT_CONNECTION_BUTTON.replace("${peopleName}", user1));
		}
		info("---Remove the invitation from user '"+user1+"'-----");
		if(waitForAndGetElement(pConn.ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user1), 7000, 0) != null){
			click(pConn.ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user1));
			Utils.pause(3000);
			waitForElementNotPresent(pConn.ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user1));
			Utils.pause(1000);
		}
		acc.userSignIn(userType.PUBLISHER);
		nav.goToConnectionPage();
		peoSearch.searchPeople(true,user1);
		info("---Cancel the request to user '"+user1+"'-----");
		if(waitForAndGetElement(pConn.ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", user1), 7000, 0) != null){
			click(pConn.ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", user1));
			waitForElementNotPresent(pConn.ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", user1));
			info("---Go to Everyone tab----");
			click(pConn.ELEMENT_EVERYONE_TAB);
			waitForAndGetElement(pConn.ELEMENT_CONNECTION_BUTTON.replace("${peopleName}", user1));
		}
		acc.userSignIn(userType.DEVELOPER);
		nav.goToConnectionPage();
		peoSearch.searchPeople(true,user);
		info("---Remove the invitation from user '"+user+"'-----");
		if(waitForAndGetElement(pConn.ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user), 7000, 0) != null){
			click(pConn.ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user));
			Utils.pause(3000);
			waitForElementNotPresent(pConn.ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user));
			Utils.pause(1000);
		}
		info("---Ignore the invitation from user '"+user+"'-----");
		if(waitForAndGetElement(pConn.ELEMENT_IGNORE_BUTTON.replace("${peopleName}", user), 7000, 0) != null){
			click(pConn.ELEMENT_IGNORE_BUTTON.replace("${peopleName}", user));
			waitForElementNotPresent(pConn.ELEMENT_IGNORE_BUTTON.replace("${peopleName}", user));
			info("---Go to Everyone tab----");
			click(pConn.ELEMENT_EVERYONE_TAB);
			waitForAndGetElement(pConn.ELEMENT_CONNECTION_BUTTON.replace("${peopleName}", user));			
		}
		peoSearch.searchPeople(true,user2);
		info("---Ignore the invitation from user '"+user2+"'-----");
		if(waitForAndGetElement(pConn.ELEMENT_IGNORE_BUTTON.replace("${peopleName}", user2), 7000, 0) != null){
			click(pConn.ELEMENT_IGNORE_BUTTON.replace("${peopleName}", user2));
			waitForElementNotPresent(pConn.ELEMENT_IGNORE_BUTTON.replace("${peopleName}", user2));
			info("---Go to Everyone tab----");
			click(pConn.ELEMENT_EVERYONE_TAB);
			waitForAndGetElement(pConn.ELEMENT_CONNECTION_BUTTON.replace("${peopleName}", user2));			
		}
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	 * Case ID:79660.
	 * Test Case Name: Display a request to join a user.
	 * Pre-Condition: User A and User B aren't connected in Intranet
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=2)
	public  void test01_DisplayARequestToJoinAUser() {
		info("Test 1: Display a request to join a user");

		/*
		- Connect to Intranet with User A
		- From Connection, send a request to the User B
		 *Input Data: 
		 *Expected Outcome: The request is sent to the User B		*/
		nav.goToConnectionPage();
		pConn.connectPeople(user1);

		/*
		- Connect to Intranet with User B
		 *Input Data: 
		 *Expected Outcome: 
		- The Gadget Invitation is displayed in the right of the screen
		- The request of the User A is displayed		*/ 
		acc.userSignIn(userType.DEVELOPER);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}", user));

		//Delete data
		/*nav.goToConnectionPage();
		pConn.ignoreInvitation(user);*/
	}

	/**
	 * Case ID:79661.
	 * Test Case Name: Display a request to join a space.
	 * Pre-Condition: User B isn't a member of space
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=3)
	public  void test02_DisplayARequestToJoinASpace() {
		info("Test 2: Display a request to join a space");
		String spaceName = "Space79661" + getRandomNumber();

		/*
		- Connect to Intranet with User A
		- Add a space
		-Send an invitation to User B
		 *Input Data: 
		 *Expected Outcome: The space is added		*/
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName, "");
		goToMembers(spaceName);
		mMember.inviteSingleUser(userType.DEVELOPER);

		/*
		- Connect to Intranet with User B
		 *Input Data: 
		 *Expected Outcome: 
		- The Gadget Invitation is displayed in the right of the screen
		- The request to join the space is displayed		*/ 
		acc.userSignIn(userType.DEVELOPER);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName));

		//Delete data
		acc.userSignIn(userType.ADMIN);
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName,300000);
	}

	/**
	 * Case ID:79662.
	 * Test Case Name: Check order of invitation.
	 * Pre-Condition: Space A and Space B are created by the User AUser A and User B and User C aren't connected to IntranetUser B isn't a memeber in Space A and B
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=4)
	public  void test03_CheckOrderOfInvitation() {
		info("Test 3: Check order of invitation");
		String spaceNameA = "SpaceA";
		String spaceNameB = "SpaceB";
		
		/*- Connect to Intranet with User A
		- Action1: Invite User B to connect
		- Action2: Invite User B to join Space A
		- Action3: Invite User B to join Space B 
		 *Input Data: 
		 *Expected Outcome: Requests are sent to the User B		*/
		info("Action1: Invite User B to connect");
		nav.goToConnectionPage();
		pConn.connectPeople(user1);
		info("Action2: Invite User B to join Space A");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceNameA, "");
		goToMembers(spaceNameA);
		mMember.inviteSingleUser(userType.DEVELOPER);
		info("Action3: Invite User B to join Space B ");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceNameB, "");
		goToMembers(spaceNameB);
		mMember.inviteSingleUser(userType.DEVELOPER);

		
		/*- Connect to Intranet with the User B
		 *Input Data: 
		 *Expected Outcome: 
		- In the Gadget Invitation, the oldest request is displayed at the bottom of the list:Action1, Action3, Action2		 */
		acc.userSignIn(userType.DEVELOPER);
		nav.goToHomePage();
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_CONNECTIONS_REQUEST_USER_INDEX.replace("${index}","1").replace("${nameinvitation}", user), 5000, 1,2);
		waitForAndGetElement(hGadget.ELEMENT_CONNECTIONS_REQUEST_SPACE_INDEX.replace("${index}","1").replace("${namespace}", spaceNameB), 5000, 1,2);
		waitForAndGetElement(hGadget.ELEMENT_CONNECTIONS_REQUEST_SPACE_INDEX.replace("${index}","1").replace("${namespace}", spaceNameA), 5000, 1,2);	

		//Delete data
		acc.userSignIn(userType.ADMIN);
		/*info("Delete request");
		nav.goToConnectionPage();
		pConn.cancelRequest(user1);*/
		info("Delete spaces");
		nav.goToHomePage();
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceNameA,300000);
		mMember.deleteSpace(spaceNameB,300000);
	}

	/**
	 * Case ID:79663.
	 * Test Case Name: Display 4 requests.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=5)
	public  void test04_Display4Requests() {
		info("Test 4: Display 4 requests");
		String spaceName1 = "SpaceD";
		String spaceName2 = "SpaceE";
		String spaceName3 = "SpaceF";
		String spaceName4 = "SpaceG";
		/*
		- Connect to Intranet with the User A
		- Send more than 4 requests to the User B
		 *Input Data: 
		 *Expected Outcome: Requests are sent		*/
		info("Request1: connect User");
		nav.goToConnectionPage();
		pConn.connectPeople(user1);

		info("Request2: Invite to space D");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName1, "");
		goToMembers(spaceName1);
		mMember.inviteSingleUser(userType.DEVELOPER);

		info("Request3: Invite to space E");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName2, "");
		goToMembers(spaceName2);
		mMember.inviteSingleUser(userType.DEVELOPER);

		info("Request4: Invite to space F");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName3, "");
		goToMembers(spaceName3);
		mMember.inviteSingleUser(userType.DEVELOPER);

		info("Request5: Invite to space G");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName4, "");
		goToMembers(spaceName4);
		mMember.inviteSingleUser(userType.DEVELOPER);

		/*
		- Connect to Intranet with the User B
		 *Input Data: 
		 *Expected Outcome: In gadget Invitation, only 4 requests are displayed		*/ 
		acc.userSignIn(userType.DEVELOPER);
		nav.goToHomePage();
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}", user), 5000, 1,2);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName1), 5000, 1,2);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName2), 5000, 1,2);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName3), 5000, 1,2);
		waitForElementNotPresent(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName1));

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		/*info("Remove request");
		nav.goToConnectionPage();
		pConn.cancelRequest(user1);*/
		info("Clean data test");
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName1,300000);
		driver.navigate().refresh();
		mMember.deleteSpace(spaceName2,300000);
		driver.navigate().refresh();
		mMember.deleteSpace(spaceName3,300000);
		driver.navigate().refresh();
		mMember.deleteSpace(spaceName4,300000);	

	}

	/**
	 * Case ID:79664.
	 * Test Case Name: Display user's informations in Invitation gadget.
	 * Pre-Condition: User A and User B aren't connected in Intranet
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=6)
	public  void test05_DisplayUsersInformationsInInvitationGadget() {
		info("Test 5: Display user's informations in Invitation gadget");
		/*
		- Connect to Intranet with User A
		- Send a request to User B to connect
		 *Input Data: 
		 *Expected Outcome: The request is sent		*/
		nav.goToConnectionPage();
		pConn.connectPeople(user1);

		/*
		- Connect to Intranet with the User B
		 *Input Data: 
		 *Expected Outcome: 
		- In the Gagdet Invitation, the request of the user A is displayed
		- The profile picture, the name and the title of the user A are displayed		*/ 
		acc.userSignIn(userType.DEVELOPER);
		nav.goToHomePage();
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}", user));
		waitForAndGetElement(hGadget.ELEMENT_PROFILE_PICTURE_GADGET);

		//Delete data
		/*nav.goToConnectionPage();
		pConn.ignoreInvitation(user);*/
	}

	/**
	 * Case ID:79665, 79666.
	 * Test Case Name: 
	 * -------- Display a long first name in Invitation Gadget.
	 * -------- Display a long last name in Invitation Gadget. 
	 * Pre-Condition: 
	 * -------- User A has a long first name
	 * -------- User A has a long last name
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 * This case is pending because Can not get element containing "..." at the end of user name. 
	 */
	@Test (groups = "pending")
	public  void test06_DisplayALongFirstNameInInvitationGadget() {
		info("Test 6: Display a long first name in Invitation Gadget");
		String username = getRandomString();
		String password = "gtn123";
		String firstName = "FirstName caseAAA ";
		String lastName = "LastName BBB Invitation Gadget";
		String email = username + "@exoplatform.com";

		/*
		- Connect to Intranet with the User A
		- Send a request to connect to User B
		 *Input Data: 
		 *Expected Outcome: The request is sent		*/
		nav.goToNewStaff();
		acc.addNewUserAccount(username, password, password, firstName, lastName, "", email, null, null, false);
		Utils.pause(2000);
		acc.signOut();
		acc.signIn(username, password);
		nav.goToConnectionPage();
		pConn.connectPeople(user);

		/*
		- Connect to Intranet with the User B
		 *Input Data: 
		 *Expected Outcome: In Invitation Gadget, the name of the User A is truncated and appended "..."		*/ 
		acc.userSignIn(userType.ADMIN);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}", firstName+" "+lastName));

		//Delete data
		info("Delete user");
		nav.goToUsersAndGroupsManagement();
		uGroup.deleteUser(username);
	}

	/**

	 * Case ID:79666.
	 * Test Case Name: Display a long last name in Invitation Gadget.
	 * Pre-Condition: User A has a long last name
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 * This case is pending because Can not get element containing "..." at the end of user name.
	 */
	@Test(groups="pending")
	public  void test07_DisplayALongTitleOfUserInInvitationGadget() {

		info("Test 7: Display a long title of user in Invitation Gadget");
		String username = "testBBB";
		String password = "gtn123";
		String firstName = "FirstName";
		String lastName = "LastName caseBBB Invitation Gadget";
		String email = "testBBB@exoplatform.com";

		/*
		- Connect to Intranet with the User A
		- Send a request to connect to User B
		 *Input Data: 
		 *Expected Outcome: The request is sent		*/

		nav.goToNewStaff();
		acc.addNewUserAccount(username, password, password, firstName, lastName, "", email, null, null, false);

		Utils.pause(2000);
		acc.signOut();
		acc.signIn(username, password);
		nav.goToConnectionPage();
		pConn.connectPeople(user);
		acc.signOut();

		/*
 		- Connect to Intranet with the User B
		 *Input Data: 
		 *Expected Outcome: In Invitation Gadget, the name of the User A is truncated and appended "..."		*/ 

		acc.userSignIn(userType.ADMIN);
		nav.goToHomePage();
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}", firstName+" "+lastName));

		//Delete data

		info("Delete user");
		nav.goToHomePage();
		nav.goToUsersAndGroupsManagement();
		uGroup.deleteUser(username);

	}

	/**
	 * Case ID:79667.
	 * Test Case Name: Display a space's informations in Invitation gadget.
	 * Pre-Condition: User isn't member in the space
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=7)
	public  void test08_DisplayASpacesInformationsInInvitationGadget() {
		info("Test 8: Display a space's informations in Invitation gadget");
		String spaceName = "Space79667";

		//Create a space
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName, "");

		/*
		- Connect to Intranet with User A
		- Send an invitation to User B to join space
		 *Input Data: 
		 *Expected Outcome: The request is sent		*/
		goToMembers(spaceName);
		mMember.inviteSingleUser(userType.DEVELOPER);

		/*
		- Connect to Intranet with the User B
		 *Input Data: 
		 *Expected Outcome: 
		- In the Gagdet Invitation, the request to join Space is displayed
		- The space icon, the name, status of space (public or private) and the number of members in the space are displayed		*/ 
		acc.userSignIn(userType.DEVELOPER);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_ICON_SPACE_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName));
		waitForAndGetElement(hGadget.ELEMENT_VERIFY_STATUS_SPACE.replace("${namespace}", spaceName).replace("${statusspace}", "Private Space - 1 Members"));

		//Delete data
		acc.userSignIn(userType.ADMIN);
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName,300000);
	}

	/**
	 * Case ID:79668.
	 * Test Case Name: Display button "Accept" and "Refuse for a request.
	 * Pre-Condition: The invitation gagdet contains request from user and space
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=8)
	public  void test09_DisplayButtonAcceptAndRefuseForARequest() {
		info("Test 9: Display button Accept and Refuse for a request");

		//Sent a request
		nav.goToConnectionPage();
		pConn.connectPeople(user1);

		/*
		- Connect to Intranet
		- In the Gadget Invitation, move the mouse over an invitation label
		 *Input Data: 
		 *Expected Outcome: 
		- Accept is displayed as a bouton 
		- Refuse is displayed ad (x)
		- If the label is partially hidden, it's truncated and appended "..." 		*/ 
		acc.userSignIn(userType.DEVELOPER);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}", user), 5000, 1,2);
//		mouseOver(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}", user), true);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET_USER_ACCEPT_41.replace("${peopleName}", user), 5000, 1,2);
		waitForAndGetElement(hGadget.ELEMENT_REMOVE_INVITATION_BUTTON_41.replace("${peopleName}", user), 5000, 1,2);

		//Delete data
		/*nav.goToConnectionPage();
		pConn.ignoreInvitation(user);*/
	}

	/**
	 * Case ID:79669.
	 * Test Case Name: Remove an invitation from Invitation Gadget.
	 * Pre-Condition: Invitations are displayed in the gadgetThe user receive only 4 requests
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=9)
	public  void test10_RemoveAnInvitationFromInvitationGadget() {
		info("Test 10 Remove an invitation from Invitation Gadget");

		//Sent two request
		info("Send two request to connect");
		nav.goToConnectionPage();
		pConn.connectPeople(user1);
		acc.userSignIn(userType.PUBLISHER);
		nav.goToConnectionPage();
		pConn.connectPeople(user1);
		/*
		- Connect to Intranet
		- From the gagdet Invitation, Accept/Refuse an invitation
		 *Input Data: 
		 *Expected Outcome: 
		- The invitation is permanently removed from the list
		- Other requests are moving to the top of the gadget
		- One less request is displayed		*/ 
		acc.userSignIn(userType.DEVELOPER);
		waitForAndGetElement((hGadget.ELEMENT_INVITATION_GADGET_USER_41).replace("${nameinvitation}", user));
		waitForAndGetElement((hGadget.ELEMENT_INVITATION_GADGET_USER_41).replace("${nameinvitation}", user2));
		hGadget.removeInvitationGadget(user);
		waitForAndGetElement((hGadget.ELEMENT_INVITATION_GADGET_USER_41).replace("${nameinvitation}", user2));
	}
	

	/**
	 * Case ID:79670.
	 * Test Case Name: Replace a removed invitation by other.
	 * Pre-Condition: The user receive more than 4 requests
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=10)
	public  void test11_ReplaceARemovedInvitationByOther() {
		info("Test 11 Replace a removed invitation by other");
		String spaceName1 = "SpaceP";
		String spaceName2 = "SpaceQ";
		String spaceName3 = "SpaceR";
		String spaceName4 = "SpaceT";

		//Pre-condition: 
		info("Request1: connect User");
		nav.goToConnectionPage();
		pConn.connectPeople(user1);

		info("Request2: Invite to space 1");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName1, "");
		goToMembers(spaceName1);
		mMember.inviteSingleUser(userType.DEVELOPER);

		info("Request3: Invite to space 2");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName2, "");
		goToMembers(spaceName2);
		mMember.inviteSingleUser(userType.DEVELOPER);

		info("Request4: Invite to space 3");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName3, "");
		goToMembers(spaceName3);
		mMember.inviteSingleUser(userType.DEVELOPER);

		info("Request5: Invite to space 4");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName4, "");
		goToMembers(spaceName4);
		mMember.inviteSingleUser(userType.DEVELOPER);

		/*
		- Connect to Intranet
		- From the Gadget Invitation, Accept/Refuse an invitation
		 *Input Data: 
		 *Expected Outcome: 
		- The invitation is permanently removed from the list
		- Other requests are moving to the top of the gadget 
		- Another request fades in at the end of the gadget to replace the disappearing one		*/ 
		info("Goto the Gadget Invitation");
		acc.userSignIn(userType.DEVELOPER);
		waitForAndGetElement((hGadget.ELEMENT_INVITATION_GADGET_USER_41).replace("${nameinvitation}", user));
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName4), 5000, 1, 2);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName3), 5000, 1, 2);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName2), 5000, 1, 2);
		waitForElementNotPresent(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName1));
		info("Remove the top invation");
		hGadget.removeInvitationGadget(user);
		waitForElementNotPresent(hGadget.ELEMENT_INVITATION_GADGET_USER_41.replace("${nameinvitation}", user));
		info("Another request fades in at the end of the gadget");
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName1), 5000, 1, 2);

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		info("Clean data test");
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName1,300000);
		driver.navigate().refresh();
		mMember.deleteSpace(spaceName2,300000);
		driver.navigate().refresh();
		mMember.deleteSpace(spaceName3,300000);
		driver.navigate().refresh();
		mMember.deleteSpace(spaceName4,300000);		
	}

	/**
	 * Case ID:79671.
	 * Test Case Name: Remove all invitations from the gadget Invitation.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=1)
	public  void test12_RemoveAllInvitationsFromTheGadgetInvitation() {
		info("Test 12 Remove all invitations from the gadget Invitation");
		String spaceNameA = "Space79671";

		//Pre-condition: 
		info("Request1: connect User");
		nav.goToConnectionPage();
		pConn.connectPeople(user1);

		info("Request2: Invite to space A");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceNameA, "");
		goToMembers(spaceNameA);
		mMember.inviteSingleUser(userType.DEVELOPER);

		/*
		- Connect to Intranet
		- Accept/refuse all invitations from the gagdet invitation
		 *Input Data: 
		 *Expected Outcome: 
		- All invitations disappears
		- The Gadget "Invitation" disappears from the Intranet Homepage		*/ 
		acc.userSignIn(userType.DEVELOPER);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET_USER_41.replace("${nameinvitation}", user), 5000, 1, 2);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceNameA), 5000, 1, 2);
		info("Remove a connection");
		hGadget.removeInvitationGadget(user);
		waitForElementNotPresent(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER_PLF41.replace("${nameinvitation}", user), 5000, 1, 2);
		info("Click on Accept button a space");
		hGadget.acceptSpaceInvitationGadget(spaceNameA);
		driver.navigate().refresh();
		waitForElementNotPresent(hGadget.ELEMENT_INVITATION_GADGET);

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceNameA, 300000);
	}

	/**
	 * Case ID:79672.
	 * Test Case Name: Display the Number of invitations in the Gadget.
	 * Pre-Condition: The User receives a total of x invitations
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=0)
	public  void test13_DisplayTheNumberOfInvitationsInTheGadget() {
		info("Test 13 Display the Number of invitations in the Gadget");
		String spaceNameA = "SpaceA";
		String spaceNameB = "SpaceB";

		//Pre-Condition 
		info("Request1: Invite to space A");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceNameA, "");
		goToMembers(spaceNameA);
		mMember.inviteSingleUser(userType.DEVELOPER);

		info("Request2: Invite to space B");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceNameB, "");
		goToMembers(spaceNameB);
		mMember.inviteSingleUser(userType.DEVELOPER);

		/*
		- Connect to Intranet with user A
		 *Input Data: 
		 *Expected Outcome: In the header of Gadget Invitation, is displayed "INVITATIONS (X)"The number is display a total of invitation shown and hidden		*/
		acc.userSignIn(userType.DEVELOPER);		
		info("Gadget Invitation shows");
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceNameA));
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceNameB));
		waitForAndGetElement(hGadget.ELEMENT_TITLE_OF_GAGDET.replace("${number}", "2"));

		/*
		- Accept or refuse an invitation
		 *Input Data: 
		 *Expected Outcome: The Total Number is updated to (x
		-1)		*/
		info("Click on Accept button a space");
		hGadget.acceptSpaceInvitationGadget(spaceNameA);
		waitForAndGetElement(hGadget.ELEMENT_TITLE_OF_GAGDET.replace("${number}", "1"));

		/*
		- Connect with user B and send a request to the User A
		- Connect with the User A
		 *Input Data: 
		 *Expected Outcome: The number of invitation is set to (x+1)		*/ 
		acc.userSignIn(userType.ADMIN);
		info("Request3: connect User");
		nav.goToConnectionPage();
		pConn.connectPeople(user1);
		acc.userSignIn(userType.DEVELOPER);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_TITLE_OF_GAGDET.replace("${number}", "2"));

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		info("Delete spaces");
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceNameA, 300000);
		driver.navigate().refresh();				
		mMember.deleteSpace(spaceNameB, 300000);
		/*info("Delete connection");
		nav.goToConnectionPage();
		pConn.cancelRequest(user1);*/		
	}

	/**
	 * Case ID:79673.
	 * Test Case Name: Accept a people invitation from the Gadget Invitation.
	 * Pre-Condition: a people invitation is displayed in the gadget
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=11)
	public  void test14_AcceptAPeopleInvitationFromTheGadgetInvitation() {
		info("Test 14 Accept a people invitation from the Gadget Invitation");
		//Pre-condition: 
		info("Request1: connect User");
		nav.goToConnectionPage();
		pConn.connectPeople(user1);

		/*
		- Connect to Intranet
		- From the gadget Invitations, move the mouse over an invitation label
		 *Input Data: 
		 *Expected Outcome: 
		- The button "Accept" is displayed
		- The cross (x) to revoke is displayed		*/
		acc.userSignIn(userType.DEVELOPER);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}", user), 5000, 1, 2);
//		mouseOver(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}", user), true);
		Utils.pause(1000);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET_USER_ACCEPT_41.replace("${peopleName}", user), 5000, 1, 2);
		waitForAndGetElement(hGadget.ELEMENT_REMOVE_INVITATION_BUTTON_41.replace("${peopleName}", user), 5000, 1, 2);

		/*
		- Click on the button "Accept"
		 *Input Data: 
		 *Expected Outcome: The invitation is removed from the gadget		*/
		hGadget.acceptInvitationGadget(user);	

		/*Go to My connexion
		 *Input Data: 
		 *Expected Outcome: The user is displayed in the list of "My connexions"		*/ 
		nav.goToConnectionPage();
		click(pConn.ELEMENT_MY_CONNECTIONS_TAB);
		waitForAndGetElement(pConn.ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user), 5000, 1, 2);

		//Delete data test
		info("Delete connection");
		/*click(pConn.ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user));
		Utils.pause(3000);
		waitForElementNotPresent(pConn.ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user));*/
	}

	/**
	 * Case ID:79674.
	 * Test Case Name: Accept a space invitation from the Gadget Invitation.
	 * Pre-Condition: a space invitation is displayed in the gadget
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=12)
	public  void test15_AcceptASpaceInvitationFromTheGadgetInvitation() {
		info("Test 15 Accept a space invitation from the Gadget Invitation");
		String spaceName = "Space79674";
		//Pre-Condition 
		info("Request: Invite to space");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName, "");
		goToMembers(spaceName);
		mMember.inviteSingleUser(userType.DEVELOPER);
		/*
		- Connect to Intranet
		- From the gadget Invitations, move the mouse over an invitation label
		 *Input Data: 
		 *Expected Outcome: 
		- The button "Accept" is displayed
		- The cross (x) to revoke is displayed		*/
		acc.userSignIn(userType.DEVELOPER);
		info("Gadget Invitation shows");
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName));
		Utils.pause(1000);

		/*
		- Click on the button "Accept"
		 *Input Data: 
		 *Expected Outcome: The invitation is removed from the gadget		*/
		hGadget.acceptSpaceInvitationGadget(spaceName);

		/*Go to My spaces
		 *Input Data: 
		 *Expected Outcome: The space is displayed in the list of "My spaces"		*/ 
		mMember.goToMySpacePage();
		waitForAndGetElement(mSpace.ELEMENT_SPACE_TITLE.replace("${spaceName}", spaceName));

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		info("Delete spaces");
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName, 300000);
	}

	/**
	 * Case ID:79675.
	 * Test Case Name: Cancel a people invitation from the Gadget Invitation.
	 * Pre-Condition: a people invitation is displayed in the gadget
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=13)
	public  void test16_CancelAPeopleInvitationFromTheGadgetInvitation() {
		info("Test 16 Cancel a people invitation from the Gadget Invitation");
		//Pre-condition: 
		info("Request1: connect User");
		nav.goToConnectionPage();
		pConn.connectPeople(user1);

		/*
		- Connect to Intranet
		- From the gadget Invitations, move the mouse over an invitation label
		 *Input Data: 
		 *Expected Outcome: 
		- The button "Accept" is displayed
		- The cross (x) to revoke is displayed		*/
		acc.userSignIn(userType.DEVELOPER);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET_USER_41.replace("${nameinvitation}", user), 5000, 1, 2);
		Utils.pause(1000);
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET_USER_ACCEPT_41.replace("${peopleName}", user), 5000, 1, 2);
		waitForAndGetElement(hGadget.ELEMENT_REMOVE_INVITATION_BUTTON_41.replace("${peopleName}", user), 5000, 1, 2);

		/*
		- Click on the button (X)
		 *Input Data: 
		 *Expected Outcome: The invitation is removed from the gadget		*/
		info("Click on Remove button a request");
		hGadget.removeInvitationGadget(user);

		/*Go to My connexion
		 *Input Data: 
		 *Expected Outcome: The user isn't displayed in the list of "My connexions"		*/ 
		nav.goToConnectionPage();
		click(pConn.ELEMENT_MY_CONNECTIONS_TAB);
		waitForElementNotPresent(pConn.ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user));			
	}

	/**
	 * Case ID:79676.
	 * Test Case Name: Cancel a space invitation from the Gadget Invitation.
	 * Pre-Condition: a space invitation is displayed in the gadget
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/28 13:55:42
	 */
	@Test(priority=14)
	public  void test17_CancelASpaceInvitationFromTheGadgetInvitation() {
		info("Test 17 Cancel a space invitation from the Gadget Invitation");
		String spaceName = "Space79676";

		//Pre-Condition 
		info("Request: Invite to space");
		nav.goToHomePage();
		mMember.goToMySpacePage();
		mMember.addNewSpace(spaceName, "");
		goToMembers(spaceName);
		mMember.inviteSingleUser(userType.DEVELOPER);

		/*
		- Connect to Intranet
		- From the gadget Invitations, move the mouse over an invitation label
		 *Input Data: 
		 *Expected Outcome: 
		- The button "Accept" is displayed
		- The cross (x) to revoke is displayed		*/
		acc.userSignIn(userType.DEVELOPER);
		info("Gadget Invitation shows");
		waitForAndGetElement(hGadget.ELEMENT_INVITATION_GADGET);
		waitForAndGetElement(hGadget.ELEMENT_SHOW_CONNECTIONS_REQUEST_SPACE.replace("${namespace}", spaceName));
		Utils.pause(1000);

		/*
		- Click on the button (X)
		 *Input Data: 
		 *Expected Outcome: The invitation is removed from the gadget		*/
		hGadget.removeSpaceInvitationGadget(spaceName);

		/*Go to My spaces
		 *Input Data: 
		 *Expected Outcome: The space isn't displayed in the list of "My spaces"		*/ 
		mMember.goToMySpacePage();
		waitForElementNotPresent(mSpace.ELEMENT_SPACE_TITLE.replace("${spaceName}", spaceName));

		//Delete data test
		acc.userSignIn(userType.ADMIN);
		info("Delete spaces");
		mMember.goToAllSpaces();
		mMember.deleteSpace(spaceName, 300000);
	}
}