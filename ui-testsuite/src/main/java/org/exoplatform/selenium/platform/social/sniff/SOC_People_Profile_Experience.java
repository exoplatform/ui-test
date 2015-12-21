package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class SOC_People_Profile_Experience extends SOC_TestConfig_2{
	/**
	 *<li> Case ID:122951.</li>
	 *<li> Test Case Name: Check my Experience section.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122952.</li>
	 *<li> Test Case Name: Check Experience section of another user.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- The section About me and Experience of the User B is informed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_CheckMyExperienceSection() {
		info("Test 1: Check my Experience section");
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
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
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
			- The followings fields are displayed : 
		 * Organization* Job Title* Job details* Skills Used* Start Date* End Date*/

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


		info("Test 2: Check Experience section of other");
		info("login as user 2");
		magAc.signIn(DATA_USER1, DATA_PASS);

		info("goto profile of user 1");
		hp.goToConnections();
		click(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		connMag.searchPeople(username1,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",jobTitle));
		waitForAndGetElement(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));

	}

}