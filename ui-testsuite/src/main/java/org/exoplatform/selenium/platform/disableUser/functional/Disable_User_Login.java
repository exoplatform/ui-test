package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class Disable_User_Login extends Disable_User_TestConfig{
		
	/**
	*<li> Case ID:127962.</li>
	*<li> Test Case Name: Check Autologin feature for disabled user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test01_CheckAutologinFeatureForDisabledUser() {
		info("Test 1: Check Autologin feature for disabled user");
		/*Step Number: 1
		*Step Name: Step 1: Login with enable user
		*Step Description: 
			- Connect to Intranet with enable user A
			- Check the "Remember My login"
		*Input Data: 
			
		*Expected Outcome: 
			- The user is connected to Intranet*/
		
		/*Step number: 2
		*Step Name: Step 2: Disable user
		*Step Description: 
			- Connect with an admin to Intranet
			- Disable the user A
		*Input Data: 
			
		*Expected Outcome: 
			- The user A is disabled*/
		
		/*Step number: 3
		*Step Name: Step 3: Check auto login with disable user
		*Step Description: 
			- Refresh the User A session
		*Input Data: 
			
		*Expected Outcome: 
			- The Login screen is displayed
			- The user is not able to login
			- The error message on the login page is dispalyed:This user account has been disabled. If you think this is an error, please contact the administrator.*/ 
		
 	}

	/**
	*<li> Case ID:127960.</li>
	*<li> Test Case Name: Check login screen for disabled user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckLoginScreenForDisabledUser() {
		info("Test 2: Check login screen for disabled user");
		createNewUser();
		/*Step Number: 1
		*Step Name: Step 1: Open "User Management" page
		*Step Description: 
			- Log in as admin.
			- Go to Administration 
			-
			-> Community 
			-
			-> Manage Community.
			- Choose the tab "User Management".
		*Input Data: 
			
		*Expected Outcome: 
			- The list of users is displayed*/

		/*Step number: 2
		*Step Name: Step 2: Disable an user
		*Step Description: 
			- Choose an enabled user and change the toggle to ON 
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			- The user is disabled*/
		disableUser();
		
		/*Step number: 3
		*Step Name: Step 3: Connect with disabled user
		*Step Description: 
			- Deconnet to intranet
			- Connect to Intranet with the disabled user
		*Input Data: 
			
		*Expected Outcome: 
			- The user is not able to login
			- The error message on the login page is dispalyed:This user account has been disabled. If you think this is an error, please contact the administrator.*/ 
		magAc.signOut();
		magAc.signInAsDisableUser(username, password);
		
		magAc.signIn(DATA_USER1, DATA_PASS);
		deleteUser();
 	}

	/**
	*<li> Case ID:127961.</li>
	*<li> Test Case Name: Check sign up screen in eXo mobile app for disabled user.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	* CANNOT AUTO
	*/
	@Test (groups="pending")
	public  void test03_CheckSignUpScreenInEXoMobileAppForDisabledUser() {
		info("Test 3: Check sign up screen in eXo mobile app for disabled user");
		/*Step Number: 1
		*Step Name: Step 1: Login with disabled user on eXo Mobile
		*Step Description: 
			- Connect to eXo mobile application with a disabled user
		*Input Data: 
			
		*Expected Outcome: 
			- The user is not able to login
			- The error message on the login page is dispalyed:This user account has been disabled. If you think this is an error, please contact the administrator.*/ 

 	}}