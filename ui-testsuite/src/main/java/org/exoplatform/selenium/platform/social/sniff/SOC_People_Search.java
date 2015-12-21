package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.Test;

public class SOC_People_Search extends SOC_TestConfig_2{
	/**
	 *<li> Case ID:121904.</li>
	 *<li> Test Case Name: Search people by name.</li>
	 *<li> Case ID:121964.</li>
	 *<li> Test Case Name: Search people by position.</li>
	 *<li> Case ID:121965.</li>
	 *<li> Test Case Name: Search people by skill.</li>
	 *<li> Case ID:121966.</li>
	 *<li> Test Case Name: Search people by directory.</li>
	 */
	@Test
	public void test01_02_03_04_SearchPeople(){
		String organization1 = txData.getContentByArrayTypeRandom(4)  + getRandomString();
		String jobTitle1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String jobDetail1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String skill1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String dStart = getDate(-7, "MM/dd/yyyy");
		
		String organization2 = txData.getContentByArrayTypeRandom(4)  + getRandomString();
		String jobTitle2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String jobDetail2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String skill2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		
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
		
		info("Edit user profile of user 1");
		info("Click on the name of user, click on My profile");
		navTool.goToMyProfile();
		info("Click on Edit button to change user's information");
		myProfile.goToEditProfile();
		myProfile.updateGenderJob("", jobTitle1);
		myProfile.updateExperience(organization1,jobTitle1,jobDetail1,skill1,dStart,null,true);
		myProfile.saveCancelUpdateInfo(true);
		
		info("Edit user profile of user 1");
		info("Click on the name of user, click on My profile");
		magAc.signIn(username2,password);
		navTool.goToMyProfile();
		info("Click on Edit button to change user's information");
		myProfile.goToEditProfile();
		myProfile.updateGenderJob("", jobTitle2);
		myProfile.updateExperience(organization2,jobTitle2,jobDetail2,skill2,dStart,null,true);
		myProfile.saveCancelUpdateInfo(true);
				
		info("Login as John");
		magAc.signIn(username3,password);
		info("Click on Connections on the left panel");
		hp.goToConnections();
		
		/*Step Number: 1
		 *Step Name: Step 1:  Search people by name
		 *PreConditions: 
		 	- There's are some people with name has character "t" for example
		 *Step Description: 
			- Log in and click Connections on the left panel
			- Enter keyword "n" into the [Search by name] box and press Enter
		 *Input Data: 
		 *Expected Outcome: 
			- Display all results match with keyword */
		info("Test case 01: Search people by name");
		connMag.searchPeople(username1,"","","");
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
		waitForElementNotPresent(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2));
		
		/*Step Number: 2
		 *Step Name: Step 2:  Search people by position
		 *PreConditions: 
		 	- There's are some people who have the same position
		 *Step Description: 
			- Log in and click Connections on the left panel
			- Enter keyword position into the [Search by position] box and press Enter
		 *Input Data: 
		 *Expected Outcome: 
			- Display all results match with keyword */
		info("Test case 02: Search people by Position");
		connMag.searchPeople("", jobTitle2, "", "");
		waitForElementNotPresent(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2));
	
		/*Step Number: 3
		 *Step Name: Step 3:  Search people by skill
		 *PreConditions: 
		 	- There's are some people who have the same skill
		 *Step Description: 
			- Log in and click Connections on the left panel
			- Enter keyword skill into the [Search by skill] box and press Enter
		 *Input Data: 
		 *Expected Outcome: 
			- Display all results match with keyword */
		info("Test case 03: Search people by skill");
		connMag.searchPeople("", "", skill1,"");
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
		waitForElementNotPresent(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2));

		/*Step Number: 4
		 *Step Name: Step 4:  Search people by directory
		 *Step Description: 
			- Log in and click Connections on the left panel
			- Click on character from people directory characters list
		 *Input Data: 
		 *Expected Outcome: 
			- Display all user which has the last name starting by selected char*/
		info("Test case 04: Search people by directory");
		connMag.searchPeople("", "", "", "B");
		waitForElementNotPresent(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2));
		
		connMag.searchPeople("", "", "", "A");
		waitForAndGetElement(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
		waitForElementNotPresent(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2));
		
	}
}
