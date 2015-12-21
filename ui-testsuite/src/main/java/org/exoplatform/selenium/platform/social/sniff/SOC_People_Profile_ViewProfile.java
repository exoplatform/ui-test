package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ConnectionsManagement.selectTabOption;
import org.testng.annotations.*;


public class SOC_People_Profile_ViewProfile extends SOC_TestConfig_2{
	/**
	 *<li> Case ID:122923.</li>
	 *<li> Test Case Name: Check My Profile page with default value.</li>
	 *<li> Pre-Condition: User A has not updated his profile yet</li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122925.</li>
	 *<li> Test Case Name: Check the Profile of another user.</li>
	 *<li> Pre-Condition: User A and user B are createdUser A has not updated contact information</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_03_CheckMyProfilePageWithDefaultValue() {
		info("Test 1: Check My Profile page with default value");
		String msgAboutMe = activityMes.getActivityMessage(4);
		String msgRecent_me = activityMes.getActivityMessage(5);
		String msgRecent_other = activityMes.getActivityMessage(6);
		String msg_me = conStatus.getConStatus(6);
		String msg_other = conStatus.getConStatus(7);

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
		
		/*Step Number: 1
		 *Step Name: Step 1: Sign in system
		 *Step Description: 
			- Login with User A
			- Click user menu and [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			- My profile is displayed
			- The title of the page (displayed in the browser tab) is My Profile*/
		navTool.goToMyProfile();
		assert driver.getTitle().contains("My Profile");

		/*Step number: 2
		 *Step Name: Step 2: Check layout
		 *Step Description: 
			- Check the mid-column
		 *Input Data: 

		 *Expected Outcome: 
			- The 1st section is named About me and displays to the user :"Help people find out more about you by sharing information on your profile." followed by a button Edit my Profile which takes the user the edit page.
			- The 2nd sections is name "Recent Activities" display message: You do not have activities yet*/
		info("check mid-column");
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", msgAboutMe));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", msgRecent_me));

		/*Step number: 3
		 *Step Name: Step 3: Check layout
		 *Step Description: 
			- Check left column
		 *Input Data: 

		 *Expected Outcome: 
			- The section is named "Contact Information"
			- Only the email field is filled*/
		info("check left-column");
		waitForAndGetElement(myProfile.ELEMENT_UIBASICPROFILEPORTLET);
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email1));

		/*Step number: 4
		 *Step Name: Step 4 : Check layout
		 *Step Description: 
			- Check right column
		 *Input Data: 

		 *Expected Outcome: 
			- The section is named Connections
			- The message "You do not have connection yet" is displayed
			- The link is updated to "Find connections" and redirect to my connections page on everyone tab.*/ 
		info("check right-column");
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_me));
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_FIND);

		info("click on Find connections");
		click(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_FIND);
		waitForAndGetElement(connMag.ELEMENT_ALL_CONNECTIONS_TAB);

		info("Test 2: Check the Profile of another user");
		hp.goToConnections();
		connMag.goToConnectionTab(selectTabOption.ALL);
		connMag.searchPeople(username2,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username2));

		info("check mid-column");
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_NO_CONTENT.replace("${content}", msgRecent_other));

		info("check left-column");
		waitForAndGetElement(myProfile.ELEMENT_UIBASICPROFILEPORTLET);
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email2));

		info("check right-column");
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TEXT.replace("${content}", msg_other));
	}

	/**
	 *<li> Case ID:122924.</li>
	 *<li> Test Case Name: Check My Profile page after all information is filled.</li>
	 *<li> Pre-Condition: User A updates About me, Experience, contact information</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckMyProfilePageAfterAllInformationIsFilled() {
		String aboutMe = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String jobTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String url1 = getRandomString()+".com";
		String gender = "Male";
		String im1 = txData.getContentByArrayTypeRandom(1);
		String phone1 = getRandomNumber()+getRandomNumber();
		String email = txData.getContentByArrayTypeRandom(4) + getRandomString()+"@gmail.com";

		String organization = txData.getContentByArrayTypeRandom(4)  + getRandomString();
		String expJobTitle = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String jobDetail = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String skill = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String dStart = getDate(-7, "MM/dd/yyyy");
		String dEnd = getDate(-1, "MM/dd/yyyy");
		String actdStart = getDate(-7, "MMMM dd, yyyy");
		String actdEnd = getDate(-1, "MMMM dd, yyyy");

		String actAboutMe = activityMes.getActivityMessage(3);
		String actExperience = activityMes.getActivityMessage(1);
		String actContactInfo= activityMes.getActivityMessage(0);

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);

		info("Test 2: Check My Profile page after all information is filled");
		/*Step Number: 1
		 *Step Name: Step 1: Sign in system
		 *Step Description: 
			- Login with User A
			- Click user menu and [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			- My profile is displayed*/

		/*Step number: 2
		 *Step Name: Step 2: Check layout
		 *Step Description: 
			- Check the mid
			-column
		 *Input Data: 

		 *Expected Outcome: 
			- The 1st section is named About me and displays to the a paragraph to describe user
			- The 2nd section is Experience and list of his experiences
			- The 3rd section is named "Recent Activities" and list the activities of the User A*/
		navTool.goToMyProfile();

		info("goto edit profile page");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		info("edit contact info");
		myProfile.updateAboutMe(aboutMe);
		myProfile.updateBasicInformation(null, null, email);
		myProfile.updateGenderJob(gender, jobTitle);
		myProfile.updateIms(contactIM.getProfileIM(0), im1,"1");
		myProfile.updatePhone(contactPhone.getContactPhoneData(0), phone1,"1");
		myProfile.updateUrl(url1,"1");
		myProfile.updateExperience(organization,expJobTitle,jobDetail,skill,dStart,dEnd,false);
		myProfile.saveCancelUpdateInfo(true);

		info("check after saving");
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",expJobTitle));
		waitForAndGetElement(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}", actAboutMe));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}",actContactInfo));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_ALL_CONTENT.replace("${content}",actExperience));

		/*Step number: 3
		 *Step Name: Step 3: Check layout
		 *Step Description: 
			- Check left column
		 *Input Data: 

		 *Expected Outcome: 
			The section is named "Contact Information" and includes
			- First Name : text field (mandatory)
			- Last Name (mandatory)
			- Email (mandatory)
			- Job Title
			- Gender
			- Phone
			- IMs:
			- URLs*/
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email));
		waitForAndGetElement(myProfile.ELEMENT_GENDER_INFO.replace("${gender}",gender));
		waitForAndGetElement(myProfile.ELEMENT_JOB_TITLE_INFO.replace("${job}",jobTitle));
		waitForAndGetElement(myProfile.ELEMENT_PHONE_INFO.replace("${type}",contactPhone.getContactPhoneData(0)).replace("${phone}", phone1));
		waitForAndGetElement(myProfile.ELEMENT_IM_INFO.replace("${type}",contactIM.getProfileIM(0)).replace("${im}", im1));
		waitForAndGetElement(myProfile.ELEMENT_URL_INFO.replace("${url}",url1));

		/*Step number: 4
		 *Step Name: Step 4 : Check layout
		 *Step Description: 
			- Check right column
		 *Input Data: 

		 *Expected Outcome: 
			- The section is named "Connections" and display the last connections of the user*/ 
		waitForAndGetElement(myProfile.ELEMENT_UIMINICONNECTIONS_PORTLET_TITLE);

	}
}