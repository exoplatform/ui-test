package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.testng.annotations.*;


public class SOC_People_Profile_Connection extends SOC_TestConfig_2 {

	/**
	 *<li> Case ID:122970.</li>
	 *<li> Test Case Name: Check my Connections section when no connection.</li>
	 *<li> Pre-Condition: User A doesn't have any connection</li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122971.</li>
	 *<li> Test Case Name: Check the Connections of another user when no connection.</li>
	 *<li> Pre-Condition: User A doesn't have any connection</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_CheckMyConnectionsSectionWhenNoConnection() {
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);

		info("Test 1: Check my Connections section when no connection");
		String msg_me = conStatus.getConStatus(6);
		String msg_other = conStatus.getConStatus(7);
		//String mes_me = myProfilPage.ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", newChar)
		/*Step Number: 1
		 *Step Name: Step 1 : check Connections in profile page
		 *Step Description: 
			- Login
			- Go to profile page of User A
		 *Input Data: 

		 *Expected Outcome: 
		- A new section Connections is added in the right column of the page.
		- a message is displayed in the section : "You do not have connections yet."
		- a link"Find connections" is displayed and redirect to My Connections page, on everyone tab*/ 
		info("goto my profile's page");
		navTool.goToMyProfile();
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_me));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_FIND);

		info("click on Find connections");
		click(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_FIND);
		waitForAndGetElement(connMag.ELEMENT_ALL_CONNECTIONS_TAB);

		info("Test 2: Check the Connections of another user when no connection");
		/*Step Number: 1
		 *Step Name: Step 1 : check Connections in profile page
		 *Step Description: 
			- Login
			- Go to profile page of User A
		 *Input Data: 

		 *Expected Outcome: 
			- A new section Connections is added in the right column of the page.
			- a message is displayed in the section : "This user does not have connections yet."*/ 
		magAc.signIn(USER_ROOT,PASS_ROOT);
		hp.goToConnections();
		click(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		connMag.searchPeople(username1,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_other));
	}

	/**
	 *<li> Case ID:122955.</li>
	 *<li> Test Case Name: Check my Connections section.</li>
	 *<li> Pre-Condition: User A has 13 connections</li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122956.</li>
	 *<li> Test Case Name: Check the Connections section of another user.</li>
	 *<li> Pre-Condition: User A has 3 connections</li>
	 *<li> Post-Condition: </li>
	 * Issue: https://jira.exoplatform.org/browse/SOC-4789
	 */
	@Test
	public  void test03_04_CheckMyConnectionsSection() {
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
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

		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		
		for(int i = 0; i<13; i++){
			String userName=userList.get(i);
			String email=userName+ mailSuffixData.getMailSuffixRandom();
			info("Add user " + userName);
			addUserPage.addUser(userName, userName, email, userName, userName);
		}
		magAc.signIn(username1, password);

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

		info("Test 3: Check my Connections section");
		/*Step Number: 1
		 *Step Name: Step 1 : check Connections in profile page
		 *Step Description: 
			- Login
			- Go to profile page of User A
		 *Input Data: 

		 *Expected Outcome: 
			- A new section Connections is added in the right column of the page.
			- The section displays the last 12 connections of a user, with a maximum of 4 users' avatar per row
			- A link View all (13) displayed at the bottom of the section*/
		info("login as user");
		magAc.signIn(username1, password);
		navTool.goToMyProfile();
		info("Number of last 12 connections: " + String.valueOf(getElements(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_NUMBER_CONNECTION).size()));
		for(int i = 12; i>0; i--){
			String userName=userList.get(i);
			waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_AVATAR.replace("${username}", userName));
		}
		waitForElementNotPresent(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_AVATAR.replace("${username}", userList.get(0)));
		
		assert getElements(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_NUMBER_CONNECTION).size()==12;
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}",numberAllConnection));

		/*Step number: 3
		 *Step Name: Step 3 : check hover effect
		 *Step Description: 
			- Mouseover one user's avatar
		 *Input Data: 

		 *Expected Outcome: 
			- On hover of a user's avatar, the Generic User Popup is displayed.*/ 
		mouseOver(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_AVATAR.replace("${username}", userList.get(11)),true);
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_HOVER_POPUP_AVATAR.replace("${username}", userList.get(11)));
		
		/*Step number: 2
		 *Step Name: Step 2 : CheckView All link
		 *Step Description: 
			- Click [View All (13)]
		 *Input Data: 

		 *Expected Outcome: 
			- The user is redirected to User A's connections page*/
		click(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}",numberAllConnection));
		waitForAndGetElement(connMag.ELEMENT_MY_CONNECTIONS_TAB);
		
		info("Test 4: Check the Connections section of another user.");
		magAc.signIn(USER_ROOT,PASS_ROOT);
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
		
		mouseOver(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_AVATAR.replace("${username}", userList.get(11)),true);
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORLET_HOVER_POPUP_AVATAR.replace("${username}", userList.get(11)));
		
		click(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_VIEWALL.replace("${num}",numberAllConnection));
		waitForAndGetElement(connMag.ELEMENT_MY_CONNECTIONS_TAB);

	}
}