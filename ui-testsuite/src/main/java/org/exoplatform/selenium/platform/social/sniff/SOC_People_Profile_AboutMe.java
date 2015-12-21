package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;

public class SOC_People_Profile_AboutMe extends SOC_TestConfig_2{

	/**
	 *<li> Case ID:122949.</li>
	 *<li> Test Case Name: Check my About Me section.</li>
	 *<li> Case ID:122950.</li>
	 *<li> Test Case Name: Check About Me section of another user.</li>
	 *<li> Pre-Condition: User has informed the about me section</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_CheckAboutMeSection() {
		String aboutMe = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);

		info("Test 1: Check my About Me section");

		/*Step Number: 1
		 *Step Name: Step 1 : Go to my profile page
		 *Step Description: 
			- Login
			- Go to User Menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			- The section About me is displayed at the top of mid
			-column of the page
			- The section is composed of 1 text area allowing the user to introduce about himself.
			- The content of the section must fit in the area*/ 
		navTool.goToMyProfile();

		info("edit about me");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		myProfile.updateAboutMe(aboutMe);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));

		info("Test 2: Check About Me section of another user");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to other's profile page
		 *Step Description: 
			- Login with User A
			- Go to Connections and go to the profile of user B
		 *Input Data: 

		 *Expected Outcome: 
			- The section About me is displayed at the top of mid
			-column of the page
			- The section is composed of 1 text area allowing the user to introduce about himself.
			- The content of the section must fit in the area*/ 
		info("Test 1: Check my About Me section");
		magAc.signIn(username2, password);
		info("goto profile of user 1");
		hp.goToConnections();
		click(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		connMag.searchPeople(username1,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
	}
}