package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


public class SOC_People_UserProfile_AboutMe extends SOC_TestConfig{

	/**
	 *<li> Case ID:125191.</li>
	 *<li> Test Case Name: Check display of About me section when informed.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- User B has informed the about me section</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckDisplayOfAboutMeSectionWhenInformed() {
		String aboutMe = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

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
		navTool.goToMyProfile();
		info("edit about me");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		myProfile.updateAboutMe(aboutMe);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));

		info("Test 1: Check display of About me section when informed");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to other's profile page
		 *Step Description: 
			- Login with User A
			- Go to Connections and go to the profile of user B
		 *Input Data: 

		 *Expected Outcome: 
			- The section About me is displayed at the top of mid
			-column of the page
			- The section is composed of 1 text area allowing the user to introduce about himself.*/ 
		magAc.signIn(username2,password);

		info("goto profile of user 1");
		hp.goToConnections();
		click(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		connMag.searchPeople(username1,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
	}

	/**
	 *<li> Case ID:125192.</li>
	 *<li> Test Case Name: Check display of About me section when not informed.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- User B has not informed the about me section</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckDisplayOfAboutMeSectionWhenNotInformed() {

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
		
		info("Test 2: Check display of About me section when not informed");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to other's profile page
		 *Step Description: 
			- Login with User A
			- Go to Connections and go to the profile of user B
		 *Input Data: 

		 *Expected Outcome: 
			- The section About me is not displayed in the page.*/ 
		info("goto profile of user 1");
		hp.goToConnections();
		click(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		connMag.searchPeople(username2,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2));
		waitForElementNotPresent(myProfile.ELEMENT_UIEXPERIENCE_PORLET);
	}
}