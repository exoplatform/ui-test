package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class SOC_People_Profile_ContactInformation extends SOC_TestConfig_2 {

	/**
	 *<li> Case ID:122947.</li>
	 *<li> Test Case Name: Check my Contact Information section.</li>
	 *<li> Pre-Condition: all fields of user A profile are updated
	- Job title
	- Gender
	- Phone has Work,Home,Others values
	- IM has Gtalk,Msn,Skype,Yahoo, Other values
	- URL</li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122948.</li>
	 *<li> Test Case Name: Check Contact Information section of another user.</li>
	 *<li> Pre-Condition: all fields of user A profile are updated
	- Job title
	- Gender
	- Phone has Work,Home,Others values
	- IM has Gtalk,Msn,Skype,Yahoo, Other values
	- URL</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_CheckMyContactInformationSection() {
		info("Test 1: Check my Contact Information section");
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
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);

		/*Step Number: 1
		 *Step Name: Step 1: User login
		 *Step Description: 
			- Login with User A
			- Click user menu and [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			- My profile is displayed*/
		navTool.goToMyProfile();
		info("goto edit profile page");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);

		/*Step number: 2
		 *Step Name: Step 2: Check contact information
		 *Step Description: 
			- Check left column
		 *Input Data: 

		 *Expected Outcome: 
			The section is named "Contact Information" and includes
			- Email
			- Job Title
			- Gender
			- PhoneWorkHomeOthers
			- IMs:GtalkMsnSkypeYahooOther
			- URLs*/ 
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

		info("login as user 2");
		magAc.signIn(USER_ROOT,PASS_ROOT);
		info("goto profile of user 1");
		hp.goToConnections();
		click(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		connMag.searchPeople(username1,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}", username1));
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

	}
}