package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.testng.annotations.*;


public class SOC_People_UserProfile_Connections extends SOC_TestConfig{

	/**
	 *<li> Case ID:125200.</li>
	 *<li> Test Case Name: Check display of Connections section.</li>
	 *<li> Case ID:125202.</li>
	 *<li> Test Case Name: Check View All link in Connections section.</li>
	 *<li> Case ID:125201.</li>
	 *<li> Test Case Name: Hover avatar in Connections section.</li>
	 *<li> Pre-Condition: - User A has at least 15 connections</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_04_05_CheckDisplayOfConnectionsSection() {
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();
		
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		Utils.pause(3000);
		
		String numberAllConnection = "13";

		ArrayList<String> userList = new ArrayList<String>();

		userList.add(getRandomString()+"a");
		userList.add(getRandomString()+"b");
		userList.add(getRandomString()+"c");
		userList.add(getRandomString()+"d");
		userList.add(getRandomString()+"e");
		userList.add(getRandomString()+"f");
		userList.add(getRandomString()+"g");
		userList.add(getRandomString()+"h");
		userList.add(getRandomString()+"i");
		userList.add(getRandomString()+"j");
		userList.add(getRandomString()+"k");
		userList.add(getRandomString()+"l");
		userList.add(getRandomString()+"m");

		info("Add new user");
		navTool.goToAddUser();

		info("Add user " + username1);
		addUserPage.addUser(username1,password, email1, username1, username1);
		addUserPage.addUser(username2,password, email2, username2, username2);
		for(int i = 0; i<13; i++){
			String userName=userList.get(i);
			String email=userName+ mailSuffixData.getMailSuffixRandom();
			info("Add user " + userName);
			addUserPage.addUser(userName, userName, email, userName, userName);
		}
		magAc.signIn(username1, username1);

		info("Create pre-condition");
		info("Click on Connections on the left panel");
		hp.goToConnections();

		info("Click on Connect button to invite an user");
		for(int i = 0; i<13; i++){
			String userName=userList.get(i);
			info(userName + " connect request");
			connMag.connectToAUser(userName);
		}

		info("Click on Accept button");
		for(int i = 0; i<13; i++){
			String userName=userList.get(i);
			info(userName + " accept request");
			magAc.signIn(userName, userName);
			hp.goToConnections();
			connMag.acceptAConnection(username1);
		}

		info("Test 1: Check display of Connections section");
		/*Step Number: 1
		 *Step Name: Step 1 : check Connections section in profile page
		 *Step Description: 
			- Login
			- Go to profile page of User A
		 *Input Data: 

		 *Expected Outcome: 
			- A new section Connections is added on the right column of the page.*/

		/*Step number: 2
		 *Step Name: Step 2 : check connections display
		 *Step Description: 
			- Check connections display
		 *Input Data: 

		 *Expected Outcome: 
			- The section displays the last 12 connections of User A
			- Connections displays a maximum of 4 users' avatar per row.*/ 
		magAc.signIn(username2,password);
		info("goto profile of user 1");
		hp.goToConnections();
		click(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		connMag.searchPeople(username1,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
		info("Number of last 12 connections: " + String.valueOf(getElements(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_NUMBER_CONNECTION).size()));
		for(int i = 12; i>0; i--){
			String userName=userList.get(i);
			waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_AVATAR.replace("${username}", userName));
		}
		waitForElementNotPresent(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_AVATAR.replace("${username}", userList.get(0)));

		assert getElements(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_NUMBER_CONNECTION).size()==12;
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}",numberAllConnection));

		info("Test 5: Hover avatar in Connections section");
		/*Step Number: 1
		 *Step Name: Step 1 : check Connections in profile page
		 *Step Description: 
			- Login
			- Go to profile page of User A
		 *Input Data: 

		 *Expected Outcome: 
			- A new section Connections is added on the right column of the page.*/

		/*Step number: 2
		 *Step Name: Step 2 : check hover effect
		 *Step Description: 
			- Mouseover one user's avatar
		 *Input Data: 

		 *Expected Outcome: 
			- On hover of a user's avatar, the Generic User Popup is displayed.*/ 
		mouseOver(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_AVATAR.replace("${username}", userList.get(11)),true);
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_HOVER_POPUP_AVATAR.replace("${username}", userList.get(11)));
		
		info("Test 4: Check View All link in Connections section");
		/*Step Number: 1
		 *Step Name: Step 1 : check Connections in profile page
		 *Step Description: 
			- Login
			- Go to profile page of User A
		 *Input Data: 

		 *Expected Outcome: 
			- A new section Connections is added in the right column of the page.
			- A link View all ($NB_CONN) is displayed at the bottom of the section, where $NB_CONN is the total number of connections owned by the user. Here, $NB_CONN is 3*/

		/*Step number: 2
		 *Step Name: Step 2 : Check View All link
		 *Step Description: 
			- Click [View All (3)]
		 *Input Data: 

		 *Expected Outcome: 
			- The user is redirected to User A's connections page*/ 
		click(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}",numberAllConnection));
		waitForAndGetElement(connMag.ELEMENT_MY_CONNECTIONS_TAB);
	}

	/**
	 *<li> Case ID:125204.</li>
	 *<li> Test Case Name: Check display of Connections section on his own profile when no connection.</li>
	 *<li> Pre-Condition: - User A doesn't have any connection</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckDisplayOfConnectionsSectionOnHisOwnProfileWhenNoConnection() {
		String msg_me = conStatus.getConStatus(6);
		info("Test 2: Check display of Connections section on his own profile when no connection");
		/*Step Number: 1
		 *Step Name: Step 1 : check Connections in profile page
		 *Step Description: 
			- Login with User A
			- Go to user menu and click [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			- A new section Connections is added on the right column of the page.*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		navTool.goToMyProfile();

		/*Step number: 2
		 *Step Name: Step 2 : check connections display
		 *Step Description: 
			- Check content of Connections sections
		 *Input Data: 

		 *Expected Outcome: 
			- The message "You do not have connection yet" is displayed
			- The link is updated to "Find connections" and redirect to my connections page on everyone tab.*/ 
		info("check right-column");
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_me));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_FIND);

		info("click on Find connections");
		click(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_FIND);
		waitForAndGetElement(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
	}

	/**
	 *<li> Case ID:125203.</li>
	 *<li> Test Case Name: Check display of Connections section when no connection.</li>
	 *<li> Pre-Condition: - User A, B are created
	- User A doesn't have any connection</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CheckDisplayOfConnectionsSectionWhenNoConnection() {
		String msg_other = conStatus.getConStatus(7);

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
		
		info("Test 3: Check display of Connections section when no connection");
		/*Step Number: 1
		 *Step Name: Step 1 : check Connections in profile page
		 *Step Description: 
			- Login as user B
			- Go to profile page of User A
		 *Input Data: 

		 *Expected Outcome: 
			- A new section Connections is added on the right column of the page.*/

		/*Step number: 2
		 *Step Name: Step 2 : check connections display
		 *Step Description: 
			- Check content of Connections sections
		 *Input Data: 

		 *Expected Outcome: 
			- A message is displayed in the section : "This user does not have connections yet."*/ 
		hp.goToConnections();
		connMag.goToConnectionTab(selectTabOption.ALL);
		connMag.searchPeople(username2,null,null,null);
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username2));

		info("check right-column");
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_other));

	}
}