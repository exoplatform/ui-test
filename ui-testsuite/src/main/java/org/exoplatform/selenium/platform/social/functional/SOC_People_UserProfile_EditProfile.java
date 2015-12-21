package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


public class SOC_People_UserProfile_EditProfile extends SOC_TestConfig{

	/**
	 *<li> Case ID:125216.</li>
	 *<li> Test Case Name: Check Save button while editing profile.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckSaveButtonWhileEditingProfile() {
		info("Test 1: Check Save button while editing profile");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		/*Step Number: 1
		 *Step Name: Step 1 : Go to my Profile
		 *Step Description: 
			- Login
			- Go to the user menu in the top navigation and click My Profile
		 *Input Data: 

		 *Expected Outcome: 
			- My profile page is displayed*/
		navTool.goToMyProfile();
		info("edit about me");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		
		/*Step number: 2
		 *Step Name: Step 2 : Check Save button
		 *Step Description: 
			- Do not change anything in the page
			- Check [Save] button at the bottom of the page
		 *Input Data: 

		 *Expected Outcome: 
			- The [Save] button is disabled until a change is made in the page.*/
		waitForAndGetElement(myProfile.ELEMENT_CONTACT_SAVE_BUTTON_DISABLE);
		
		/*Step number: 3
		 *Step Name: Step 3 : Check Save button
		 *Step Description: 
			- Do a change in the page
			- Check the [Save] button
		 *Input Data: 

		 *Expected Outcome: 
			- The [Save] button is enabled*/ 
		type(myProfile.ELEMENT_FIRST_NAME_TEXTBOX_EDIT, "test", true);
		waitForAndGetElement(myProfile.ELEMENT_CONTACT_SAVE_BUTTON);
		waitForElementNotPresent(myProfile.ELEMENT_CONTACT_SAVE_BUTTON_DISABLE);

	}

	/**
	 *<li> Case ID:125218.</li>
	 *<li> Test Case Name: Edit my Profile not displayed while editing.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_EditMyProfileNotDisplayedWhileEditing() {
		info("Test 2: Edit my Profile not displayed while editing");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		/*Step Number: 1
		 *Step Name: - Step 1 : Go to Edit Profile
		 *Step Description: 
			- Login
			- Go to My Profile from the user menu
			- Click [Edit my Profile] on the left top corner (next to the avatar)
		 *Input Data: 

		 *Expected Outcome: 
			- Edit profile page is displayed*/
		navTool.goToMyProfile();
		info("edit about me");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		
		/*Step number: 2
		 *Step Name: Step 2 : Check [Edit my Profile]
		 *Step Description: 
			- Check [Edit my Profile] in the top left corner
		 *Input Data: 

		 *Expected Outcome: 
			- While editing a profile, the link Edit my Profile is not displayed.*/ 
		waitForElementNotPresent(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
	}

	/**
	 *<li> Case ID:125268.</li>
	 *<li> Test Case Name: Leave Email blank.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_LeaveEmailBlank() {
		info("Test 3: Leave Email blank");
		/*Step Number: 1
		 *Step Name: Step 1: Go to the My Profile page
		 *Step Description: 
			- Log in as a user.
			- Select My Profile page from the User menu.
		 *Input Data: 

		 *Expected Outcome: 
			The My Profile page is shown.*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		
		navTool.goToMyProfile();
		
		/*Step number: 2
		 *Step Name: Step 2: Edit Profile
		 *Step Description: 
			- Click on [Edit my Profile] in the top left corner or in the center.
		 *Input Data: 

		 *Expected Outcome: 
			The Edit Profile page is displayed.*/
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		
		/*Step number: 3
		 *Step Name: Step 3: Let email field is blank
		 *Step Description: 
			- Enter valid data into First Name field, Last Name field and keep Email field as null.
			- Click on [Save] button.
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enabled.
			- Message shows: The field Email is required.*/ 
		myProfile.updateBasicInformation(null, null, " ");
		myProfile.saveCancelUpdateInfo(true);
		alert.verifyAlertMessage("The field \"Email\" is required");
	}

	/**
	 *<li> Case ID:125264.</li>
	 *<li> Test Case Name: Leave Last Name field empty.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_LeaveLastNameFieldEmpty() {
		info("Test 4: Leave Last Name field empty");
		/*Step Number: 1
		 *Step Name: Step 1: Go to My Profile page
		 *Step Description: 
			- Log in as a user.
			- Select My Profile from the User menu.
		 *Input Data: 

		 *Expected Outcome: 
			The My Profile page is shown.*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		navTool.goToMyProfile();
		/*Step number: 2
		 *Step Name: Step 2: Open the Edit Profile page
		 *Step Description: 
			- Click [Edit My Profile] on the top left corner.
		 *Input Data: 

		 *Expected Outcome: 
			TheEdit Profile page is displayed.*/
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		/*Step number: 3
		 *Step Name: Step 3: Leave Last Name field blank
		 *Step Description: 
			- In Contact Information section, leave Last Name field blank, enter valid data into First Name field.
			- Click on [Save] button.
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enabled
			- A warming message is shown: The field "Last Name" is required.*/ 
		myProfile.updateBasicInformation(null, " ", null);
		myProfile.saveCancelUpdateInfo(true);
		alert.verifyAlertMessage("The field \"Last Name\" is required");
	}

	/**
	 *<li> Case ID:125261.</li>
	 *<li> Test Case Name: Leave the First Name field empty.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_LeaveTheFirstNameFieldEmpty() {
		info("Test 5: Leave the First Name field empty");
		/*Step Number: 1
		 *Step Name: Step 1: Go to My Profile page
		 *Step Description: 
			- Log in as a user.
			- Select My Profile from the User menu.
		 *Input Data: 

		 *Expected Outcome: 
			The My Profile page is shown.*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		Utils.pause(3000);
		navTool.goToMyProfile();
		/*Step number: 2
		 *Step Name: Step 2: Open Edit Profile page
		 *Step Description: 
			- Click [Edit my Profile] in the top left corner.
		 *Input Data: 

		 *Expected Outcome: 
			The Edit Profile page is displayed.*/
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		/*Step number: 3
		 *Step Name: Step 3: Leave First Name empty
		 *Step Description: 
			- In Contact Information section, leave First Name empty, enter valid data intoLast Name field and email address.
			- Click on [Save] button.
		 *Input Data: 

		 *Expected Outcome: 
			- [Save] button is enabled
			- Show message: The field "First Name" is required.*/ 
		myProfile.updateBasicInformation(" ", null, null);
		myProfile.saveCancelUpdateInfo(true);
		alert.verifyAlertMessage("The field \"First Name\" is required");
	}}