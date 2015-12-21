package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class SOC_People_Profile_Activities extends SOC_TestConfig_2{

	/**
	 *<li> Case ID:122972.</li>
	 *<li> Test Case Name: Update user profile activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_UpdateUserProfileActivity() {
		info("Test 1: Update user profile activity");
		String firstName = txData.getContentByArrayTypeRandom(4) + getRandomString()+"first";
		String lastName =txData.getContentByArrayTypeRandom(4) + getRandomString()+"last";
		String email = txData.getContentByArrayTypeRandom(4) + getRandomString()+"@gmail.com";
		String link = atData.getAttachFileByArrayTypeRandom(26);
		String aboutMe = txData.getContentByArrayTypeRandom(4)+getRandomNumber();
		String organization = txData.getContentByArrayTypeRandom(4)  + getRandomString();
		String jobTitle = txData.getContentByArrayTypeRandom(4) + getRandomString();

		String actAboutMe = activityMes.getActivityMessage(3);
		String actExperience = activityMes.getActivityMessage(1);
		String actAvatar= activityMes.getActivityMessage(2);
		String actContactInfo= activityMes.getActivityMessage(0);

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Go to my profile page
		 *Step Description: 
			- Login
			- Go to User Menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			Show content of my profile page*/
		navTool.goToMyProfile();

		/*Step number: 2
		 *Step Name: Step 2: Edit Profile
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner, next to the user avatar
		 *Input Data: 

		 *Expected Outcome: 
			Edit Profile page is displayed*/
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		/*Step number: 3
		 *Step Name: Step 3: Edit Contact Information
		 *Step Description: 
			- Edit contact information (First Name/Last Name/Email....), Experience, About Me and Avatar
			- Click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- A comment is added in activity stream and Recent Activities: "Contact information has been updated."
			- Activity content is updated with new value*/
		info("edit info");
		myProfile.updateBasicInformation(firstName, lastName, email);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_FULLNAME_INFO.replace("${fullname}",firstName + " " + lastName));
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", actContactInfo));
		
		/*Step number: 4
		 *Step Name: Step 4: Edit Experiences
		 *Step Description: 
			- Edit Experiences
			- Click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- A comment is added in activity stream and Recent Activities: "Experiences haves been updated."
			- Activity content is updated with new value*/
		info("edit experience");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		myProfile.updateExperience(organization, jobTitle,null,null,null,null,null);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",jobTitle));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", actExperience));
		
		/*Step number: 5
		 *Step Name: Step 5: Edit Avatar
		 *Step Description: 
			- Edit Avatar
			- Click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- A comment is added in activity stream and Recent Activities: "Avatar has been updated."
			- Activity content is updated with new value*/
		info("edit avatar");
		String oldUrl=waitForAndGetElement(myProfile.ELEMENT_USER_AVATAR).getAttribute("src");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		myProfile.changeAvatar("TestData/"+link);
		myProfile.saveCancelUpdateInfo(false);
		String newUrl=waitForAndGetElement(myProfile.ELEMENT_USER_AVATAR).getAttribute("src");
		assert !oldUrl.equals(newUrl);
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", actAvatar));
		
		/*Step number: 6
		 *Step Name: Step 6: Edit About me
		 *Step Description: 
			- Edit About me
			- Click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- A comment is added in activity stream and Recent Activities: "About me has been updated."
			- Activity content is updated with new value*/
		info("edit about me");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		myProfile.updateAboutMe(aboutMe);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", actAboutMe));
		
	}
}