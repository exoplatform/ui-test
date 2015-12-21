package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


public class SOC_People_UserProfile_Experience extends SOC_TestConfig{

	/**
	 *<li> Case ID:125275.</li>
	 *<li> Test Case Name: Check all fields of Experience section.</li>
	 *<li> Case ID:125274.</li>
	 *<li> Test Case Name: Check display of Experience section when About me is not informed.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_03_CheckAllFieldsOfExperienceSection() {
		info("Test 1: Check all fields of Experience section");
		info("Test 3: Check display of Experience section when About me is not informed");
		String organization = txData.getContentByArrayTypeRandom(4)  + getRandomString();
		String jobTitle = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String jobDetail = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String skill = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String dStart = getDate(-7, "MM/dd/yyyy");
		String dEnd = getDate(-1, "MM/dd/yyyy");
		String actdStart = getDate(-7, "MMMM dd, yyyy");
		String actdEnd = getDate(-1, "MMMM dd, yyyy");

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);

		/*Step Number: 1
		 *Step Name: Step1 : Edit my Profile
		 *Step Description: 
			- Login
			- Go to user profile menu and click My Profile
			- Click Edit my Profile on the top left corner
		 *Input Data: 

		 *Expected Outcome: 
			Edit profile page is displayed*/
		navTool.goToMyProfile();
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		/*Step number: 2
		 *Step Name: Step 2 : Check fields of experience
		 *Step Description: 
			- Click the + icon next to [Add an experience]
		 *Input Data: 

		 *Expected Outcome: 
			- The followings fields are displayed : * Organization* Job Title* Job details* Skills Used* Start Date* End Date*/

		/*Step number: 3
		 *Step Name: Step 3 : Fill experience
		 *Step Description: 
			- Fill all experience fields
			- Click save
			- Check display
		 *Input Data: 

		 *Expected Outcome: 
			Respecting the order, the following fields can be displayed in the section : * Organization* Job Title* Job details* Skills Used* Start Date* End Date*/
		myProfile.updateExperience(organization,jobTitle,jobDetail,skill,dStart,dEnd,false);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",jobTitle));
		waitForAndGetElement(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));

		/*Step number: 4
		 *Step Name: Step 4 : Empty fied
		 *Step Description: 
			- Edit profile
			- Keep empty one field (for instance position)
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enable
			- If not informed, a field is not displayed in the section.*/ 
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		myProfile.updateExperience(organization,jobTitle," ",skill,dStart,dEnd,false);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",jobTitle));
		waitForElementNotPresent(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));

	}

	/**
	 *<li> Case ID:125273.</li>
	 *<li> Test Case Name: Check display of Experience section when About me is informed.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- The section About me and Experience of the User B is informed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckDisplayOfExperienceSectionWhenAboutMeIsInformed() {	
		String aboutMe = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String organization = txData.getContentByArrayTypeRandom(4)  + getRandomString();
		String jobDetail = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String skill = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String dStart = getDate(-7, "MM/dd/yyyy");
		String dEnd = getDate(-1, "MM/dd/yyyy");
		String actdStart = getDate(-7, "MMMM dd, yyyy");
		String actdEnd = getDate(-1, "MMMM dd, yyyy");
		String expJobTitle = txData.getContentByArrayTypeRandom(4) + getRandomString();
		
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		navTool.goToMyProfile();

		info("goto edit profile page");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		info("edit contact info");
		myProfile.updateAboutMe(aboutMe);
		myProfile.updateExperience(organization,expJobTitle,jobDetail,skill,dStart,dEnd,false);
		myProfile.saveCancelUpdateInfo(true);

		info("Test 2: Check display of Experience section when About me is informed");
		/*Step Number: 1
		 *Step Name: Step 1 : Go to other's profile page
		 *Step Description: 
			- Login with User A
			- Go to Connections and go to the profile of user B
		 *Input Data: 

		 *Expected Outcome: 
			- The section Experience is displayed at the mid
			-column of the page, below About me section.*/ 
		info("Login as John");
		magAc.signIn(DATA_USER1, DATA_PASS);
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username1,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username1));
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",expJobTitle));
		waitForAndGetElement(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));
	}

	/**
	 *<li> Case ID:125276.</li>
	 *<li> Test Case Name: Check display of Experience section when not informed.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- No experience are informed for User B</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CheckDisplayOfExperienceSectionWhenNotInformed() {
		info("Test 4: Check display of Experience section when not informed");
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

		/*Step Number: 1
		 *Step Name: Step 1 : Go to other's profile page
		 *Step Description: 
			- Login with User A
			- Go to Connections and go to the profile of user B
		 *Input Data: 

		 *Expected Outcome: 
			The section Experience is not displayed in the page.*/ 
		info("Click on Connections on the left panel");
		hp.goToConnections();
		connMag.searchPeople(username2,"","","");
		click(connMag.ELEMENT_USER_LINK.replace("${userName}", username2));
		waitForElementNotPresent(myProfile.ELEMENT_UIEXPERIENCE_PORLET);
	}
}