package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


public class SOC_People_Activity_UserProfileActivities extends SOC_TestConfig{

	/**
	 *<li> Case ID:125306.</li>
	 *<li> Test Case Name: Update user profile activity after changing avatar.</li>
	 *<li> Case ID:126694.</li>
	 *<li> Test Case Name: Dislike User profile activity from like icon.</li>
	 *<li> Case ID:126693.</li>
	 *<li> Test Case Name: Delete User profile activity from activity stream by owner.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_04_UpdateUserProfileActivityAfterChangingAvatar() {
		info("Test 4: Update user profile activity after changing avatar");
		String link = atData.getAttachFileByArrayTypeRandom(26);

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		String activity1 = username1 + " "+username1;
		String comment1= activityMes.getActivityMessage(2);

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
		info("edit avatar");
		String oldUrl=waitForAndGetElement(myProfile.ELEMENT_USER_AVATAR.replace("${userName}",username1)).getAttribute("src");
		info("edit profile");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		/*Step number: 3
		 *Step Name: Step 3: Change avatar
		 *Step Description: 
			- Click on [Change Avatar] button 
			- Browser to image file ( .png, .gif, .jpg, .jpeg) on local with size is smaller 2MB
			- Click on Confirm button and then [OK]
		 *Input Data: 

		 *Expected Outcome: 
			- A comment is added in user activity stream and Recent Activities: Avatar has been updated.
			- Activity content is updated with new avatar*/ 
		myProfile.changeAvatar("TestData/"+link);
		myProfile.saveCancelUpdateInfo(false);
		String newUrl=waitForAndGetElement(myProfile.ELEMENT_USER_AVATAR.replace("${userName}",username1)).getAttribute("src");
		assert !oldUrl.equals(newUrl);
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1));
		navTool.goToMyActivities();
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment1));

		info("Test 2: Dislike User profile activity from like icon");
		/*Step Number: 1
		 *Step Name: Step 1: Like user profile activity
		 *Step Description: 
			- Connect to Intranet 
			- Click Like icon
		 *Input Data: 

		 *Expected Outcome: 
			- The User profile activity is displayed in the activity stream
			-Number of liker is increased 1*/
		navTool.goToMyActivities();
		hpAct.likeActivity(activity1);

		/*Step number: 2
		 *Step Name: Step 2: Dislike user profile activity
		 *Step Description: 
			- Click on like icon again
		 *Input Data: 

		 *Expected Outcome: 
			- The User profile activity is disliked by the user, the number of like is updated to 
			-1*/ 
		hpAct.unlikeActivity(activity1);

		info("Test 1: Delete User profile activity from activity stream by owner");
		/*Step Number: 1
		 *Step Name: Step 1: Show Remove icon
		 *Step Description: 
			- Connect to intranet
			- Move the mouse over the User profile activity on activity stream
		 *Input Data: 

		 *Expected Outcome: 
			A (X) icon is displayed in the top right panel of the activity*/
		/*Create data test*/

		/*Step number: 2
		 *Step Name: Step 2: Click Remove icon
		 *Step Description: 
			- Click on the (X) icon
		 *Input Data: 

		 *Expected Outcome: 
			The User profile activity for spaceis removed from the activity stream*/ 
		navTool.goToMyActivities();
		hpAct.deleteActivity(activity1);
	}

	/**
	 *<li> Case ID:125307.</li>
	 *<li> Test Case Name: Update user profile activity after editing About me.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_UpdateUserProfileActivityAfterEditingAboutMe() {
		info("Test 5: Update user profile activity after editing About me");
		String aboutMe = txData.getContentByArrayTypeRandom(1)+getRandomNumber();

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		String comment1 = activityMes.getActivityMessage(3);
		String comment2 = "\"About me\" has been updated.";
		String activity1 = username1 + " "+username1;

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

		info("edit about me");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		/*Step number: 3
		 *Step Name: Step 3: Edit About me
		 *Step Description: 
			- Edit About me field with some values
			- Click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enable. 
			- A user profile activity is updated in activity stream
			- A comment is added in activity stream and Recent Activities: About me has been updated.*/ 
		myProfile.updateAboutMe(aboutMe);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_UIEXPERIENCE_PROFILE_PORTLET.replace("${content}", aboutMe));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1));
		navTool.goToMyActivities();
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT_QUOTES.replace("${activityText}",activity1).replace("${commentText}",comment2));
	}

	/**
	 *<li> Case ID:125308.</li>
	 *<li> Test Case Name: Update user profile activity after editing contact information.</li>
	 *<li> Case ID:126691.</li>
	 *<li> Test Case Name: Display the content of the user profile activity.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_06_UpdateUserProfileActivityAfterEditingContactInformation() {
		info("Test 6: Update user profile activity after editing contact information");
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

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		String comment1= activityMes.getActivityMessage(0);
		String comment2 = activityMes.getActivityMessage(7);
		String activity1 = username1 + " "+username1;

		/*Step Number: 1
		 *Step Name: Step 1: Go to my profile page
		 *Step Description: 
			- Login
			- Go to User Menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			Show content of my profile page*/
		navTool.goToMyProfile();
		info("goto edit profile page");

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
			- Edit contact information (First Name/Last Name/Email....)
			- Click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enable. 
			- A comment is added in activity stream and Recent Activities: Contact information has been updated.
			- Activity content is updated with new value
			- A user profile activity is created in the activity stream with the following information:* User's job title* User's email* User's phone* User's gender* User's avatar*/ 

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

		info("Test 3: Display the content of the user profile activity");

		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1));
		navTool.goToMyActivities();
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment2));
	}

	/**
	 *<li> Case ID:125309.</li>
	 *<li> Test Case Name: Update user profile activity after editing experiences.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_UpdateUserProfileActivityAfterEditingExperiences() {
		info("Test 7: Update user profile activity after editing experiences");
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
		String email1 = username1 + mailSuffixData.getMailSuffixRandom();
		String activity1= username1 + " "+username1;
		String comment1 = activityMes.getActivityMessage(1);
		String comment2 = activityMes.getActivityMessage(8);
		info("Add new user");
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

		/*Step number: 2
		 *Step Name: Step 2: Edit Profile
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner, next to the user avatar
		 *Input Data: 

		 *Expected Outcome: 
			Edit Profile page is displayed*/
		navTool.goToMyProfile();
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		/*Step number: 3
		 *Step Name: Step 3 : Add experience
		 *Step Description: 
			- Click the + icon next to [Add an experience]
			- Add one experience (Organization, Job Title) 
			- click on [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enable.
			- A comment is added in activity stream and Recent Activities: Experiences have been updated.
			- Activity content is updated with new experience*/ 
		myProfile.updateExperience(organization,jobTitle,jobDetail,skill,dStart,dEnd,false);
		myProfile.saveCancelUpdateInfo(true);
		waitForAndGetElement(myProfile.ELEMENT_COMPANY_INFO.replace("${company}",organization));
		waitForAndGetElement(myProfile.ELEMENT_POSITION_INFO.replace("${position}",jobTitle));
		waitForAndGetElement(myProfile.ELEMENT_JOB_DETAIL_INFO.replace("${description}",jobDetail));
		waitForAndGetElement(myProfile.ELEMENT_SKILL_INFO.replace("${skill}",skill));
		waitForAndGetElement(myProfile.ELEMENT_STARTDATE_INFO.replace("${date}",actdStart));
		waitForAndGetElement(myProfile.ELEMENT_ENDDATE_INFO.replace("${date}",actdEnd));
		waitForAndGetElement(myProfile.ELEMENT_RECENT_ACTIVITY_CONTENT.replace("${index}", "1").replace("${content}", comment1));

		navTool.goToMyActivities();
		waitForAndGetElement(hpAct.ELEMENT_COMMENT_TEXT.replace("${activityText}",activity1).replace("${commentText}",comment2));
	}
}