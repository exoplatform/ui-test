package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class SOC_People_Profile_EditProfile extends SOC_TestConfig_2{

	/**
	 *<li> Case ID:122964.</li>
	 *<li> Test Case Name: Edit About Me.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_EditAboutMe() {
		info("Test 1: Edit About Me");
		String aboutMe = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String aboutMe1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String actAboutMe = activityMes.getActivityMessage(3);

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
		info("edit profile");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		/*Step number: 3
		 *Step Name: Step 3: Edit About me
		 *Step Description: 
			- Edit About me field with some values
			- Click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enable. 
			- About me content is displayed in the middle column
			- A comment is added in Recent Activities: About me has been updated.*/
		info("update about me");
		myProfile.updateAboutMe(aboutMe);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", actAboutMe));

		/*Step number: 4
		 *Step Name: Step 4 : Check Cancel button
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner, next to the user avatar
			- Do some changes in the page<br />
			- Click [Cancel] button
		 *Input Data: 

		 *Expected Outcome: 
			- Change aren't saved*/ 
		info("edit profile");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		info("update about me");
		myProfile.updateAboutMe(aboutMe1);
		myProfile.saveCancelUpdateInfo(false);
		waitForElementNotPresent(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe1));
	}

	/**
	 *<li> Case ID:122965.</li>
	 *<li> Test Case Name: Edit Contact Information.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_EditContactInformation() {
		info("Test 2: Edit Contact Information");
		String jobTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		String url1 = getRandomString()+".com";
		String url2 = getRandomString()+".net";

		String gender = "Male";

		String im1 = txData.getContentByArrayTypeRandom(1);
		String im2 = txData.getContentByArrayTypeRandom(1);
		String im3 = txData.getContentByArrayTypeRandom(1);
		String im4 = txData.getContentByArrayTypeRandom(1);
		String im5 = txData.getContentByArrayTypeRandom(1);

		String phone1 = getRandomNumber()+getRandomNumber();
		String phone2 = getRandomNumber()+getRandomNumber();
		String phone3 = getRandomNumber()+getRandomNumber();

		String email = txData.getContentByArrayTypeRandom(4) + getRandomString()+"@gmail.com";
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
		 *Step Name: Step 2: Edit profile
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner, next to the user avatar
		 *Input Data: 

		 *Expected Outcome: 
			Show edit page*/
		info("edit profile");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		/*Step number: 3
		 *Step Name: Step 3: Edit information of the Contact
		 *Step Description: 
			- Select gender, Enter the valid value for phone, instant message, URLs field.
			- Click on [save] button
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enable
			- New value of contact information is on the left column
			- A comment is added in Recent Activities: Contact information has been updated.*/ 
		info("update information");
		info("edit contact info");
		info("edit info");
		myProfile.updateBasicInformation(null, null, email);
		myProfile.updateGenderJob(gender, jobTitle);

		myProfile.updateIms(contactIM.getProfileIM(0), im1,"1");
		myProfile.updateIms(contactIM.getProfileIM(1), im2,"2");
		myProfile.updateIms(contactIM.getProfileIM(2), im3,"3");
		myProfile.updateIms(contactIM.getProfileIM(3), im4,"4");
		myProfile.updateIms(contactIM.getProfileIM(4), im5,"5");

		myProfile.updatePhone(contactPhone.getContactPhoneData(0), phone1,"1");
		myProfile.updatePhone(contactPhone.getContactPhoneData(1), phone2,"2");
		myProfile.updatePhone(contactPhone.getContactPhoneData(2), phone3,"3");

		myProfile.updateUrl(url1,"1");
		myProfile.updateUrl(url2,"2");

		myProfile.saveCancelUpdateInfo(true);

		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",email));
		waitForAndGetElement(myProfile.ELEMENT_GENDER_INFO.replace("${gender}",gender));

		waitForAndGetElement(myProfile.ELEMENT_PHONE_INFO.replace("${type}",contactPhone.getContactPhoneData(0)).replace("${phone}", phone1));
		waitForAndGetElement(myProfile.ELEMENT_PHONE_INFO.replace("${type}",contactPhone.getContactPhoneData(1)).replace("${phone}", phone2));
		waitForAndGetElement(myProfile.ELEMENT_PHONE_INFO.replace("${type}",contactPhone.getContactPhoneData(2)).replace("${phone}", phone3));

		waitForAndGetElement(myProfile.ELEMENT_IM_INFO.replace("${type}",contactIM.getProfileIM(0)).replace("${im}", im1));
		waitForAndGetElement(myProfile.ELEMENT_IM_INFO.replace("${type}",contactIM.getProfileIM(1)).replace("${im}", im2));
		waitForAndGetElement(myProfile.ELEMENT_IM_INFO.replace("${type}",contactIM.getProfileIM(2)).replace("${im}", im3));
		waitForAndGetElement(myProfile.ELEMENT_IM_INFO.replace("${type}",contactIM.getProfileIM(3)).replace("${im}", im4));
		waitForAndGetElement(myProfile.ELEMENT_IM_INFO.replace("${type}",contactIM.getProfileIM(4)).replace("${im}", im5));

		waitForAndGetElement(myProfile.ELEMENT_URL_INFO.replace("${url}",url1));
		waitForAndGetElement(myProfile.ELEMENT_URL_INFO.replace("${url}",url2));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", actContactInfo));

	}

	/**
	 *<li> Case ID:122966.</li>
	 *<li> Test Case Name: Edit Experience.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_EditExperience() {
		info("Test 3: Edit Experience");
		String organization = txData.getContentByArrayTypeRandom(4)  + getRandomString();
		String jobTitle = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String jobDetail = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String skill = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String dStart = getDate(-7, "MM/dd/yyyy");
		String dEnd = getDate(-1, "MM/dd/yyyy");
		String actdStart = getDate(-7, "MMMM dd, yyyy");
		String actdEnd = getDate(-1, "MMMM dd, yyyy");
		String actExperience = activityMes.getActivityMessage(1);

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
		 *Step Name: Step 2: Edit information of the experiences
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner, next to the user avatar
			- Select experiences and Click on [+] button
			- Enter valid data into all field: Organization, Job Title, Job Details, Skills
			- Start date: select month and enter valid year
			- End date: Tick or Untick on check box "Still in this position" , if [Untick], select valid month and enter valid year
			- Click on [save] button
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enable
			- Experience is displayed above Recent activities
			- A comment is added in Recent Activities:Experiences have been updated.*/ 
		info("edit profile");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		myProfile.updateExperience(organization,jobTitle,jobDetail,skill,dStart,dEnd,false);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",jobTitle));
		waitForAndGetElement(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", actExperience));
		
	}

	/**
	 *<li> Case ID:122967.</li>
	 *<li> Test Case Name: Change Avatar.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_ChangeAvatar() {
		info("Test 4: Change Avatar");
		String link = atData.getAttachFileByArrayTypeRandom(26);
		String actAvatar= activityMes.getActivityMessage(2);

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
		 *Step Name: Step 2: Show form upload avatar
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner, next to the user avatar
			- Click on Change avatar in the middle column
		 *Input Data: 

		 *Expected Outcome: 
			Avatar upload form is displayed with:
			- Select File: browser to file on local and upload image
			- Confirm: confirm the uploading image
			- Cancel: cancel the uploading*/

		/*Step number: 3
		 *Step Name: Step 3: upload image
		 *Step Description: 
			- Browser to image file ( .png, .gif, .jpg, .jpeg) on local 
			- Click on Confirm button
		 *Input Data: 

		 *Expected Outcome: 
			Show avatar preview form with image and all information about the image*/

		/*Step number: 4
		 *Step Name: Step 4: Finish change avatar
		 *Step Description: 
			- Click on Save button on avatar preview form
		 *Input Data: 

		 *Expected Outcome: 
			- New avatar is displayed*/

		/*Step number: 5
		 *Step Name: Step 5: Save edit page
		 *Step Description: 
			Click on [Save] button of edit page
		 *Input Data: 

		 *Expected Outcome: 
			- A comment is added in Recent Activities: Avatar has been updated.*/ 
		info("edit avatar");
		String oldUrl=waitForAndGetElement(myProfile.ELEMENT_USER_AVATAR).getAttribute("src");
		info("edit profile");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		myProfile.changeAvatar("TestData/"+link);
		myProfile.saveCancelUpdateInfo(false);
		String newUrl=waitForAndGetElement(myProfile.ELEMENT_USER_AVATAR).getAttribute("src");
		assert !oldUrl.equals(newUrl);
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", actAvatar));

	}
}