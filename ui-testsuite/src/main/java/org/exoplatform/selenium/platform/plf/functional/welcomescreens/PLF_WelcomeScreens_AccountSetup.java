package org.exoplatform.selenium.platform.plf.functional.welcomescreens;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 *
 */
public class PLF_WelcomeScreens_AccountSetup extends PlatformBase{

	ManageAccount acc; 

	@BeforeMethod
	public void beforeMethod(){
		info("Get Browser");
		initSeleniumTestWithOutTermAndCondition(driver);
		driver.get(DEFAULT_BASEURL);
		acc = new ManageAccount(driver, this.plfVersion);
	}
	@AfterMethod
	public void afterMethod(){
		info("After method");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:79247.
	 * Test Case Name: Display the Account Setup screen.
	 */
	@Test
	public  void test01_DisplayTheAccountSetupScreen() {
		info("Test 1: Display the Account Setup screen");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Acount Setup screen is displayed
		- 2 Blocs are displayed:"Create your acoount" and "Admin password"
		- The button "Submit" is displayed		*/ 
		waitForAndGetElement(By.xpath(ELEMENT_TERM_CONDITION_BOX ));
		Utils.pause(500);
		info("Check the checkbox: I agree with these terms and conditions");
		check(ELEMENT_AGREEMENT_CHECKBOX, 2);
		info("Check Continue button is enabled.");
		waitForAndGetElement(ELEMENT_CONTINUE_BUTTON);
		click(ELEMENT_CONTINUE_BUTTON);		
		Utils.pause(500);
		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);		
		waitForAndGetElement(ELEMENT_YOUR_ACCOUNT_LABEL);
		waitForAndGetElement(ELEMENT_ADMIN_PASS_LABEL);
		waitForAndGetElement(ELEMENT_SUBMIT_BUTTON);
	}

	/**
	 * Case ID:79249.
	 * Test Case Name: Display information text after the label "Create an account".
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test02_DisplayInformationTextAfterTheLabelCreateAnAccount() {
		info("Test 2: Display information text after the label Create an account");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Acount Setup screen is displayed		*/
		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		waitForAndGetElement(ELEMENT_USER_ADMIN).getAttribute("value").equalsIgnoreCase(USER_ROOT);	
		/*
		- Read under "Create your account"
		 *Input Data: 
		 *Expected Outcome: 
		- An info text is displayed "This will be your primary user account" 		*/ 
		waitForAndGetElement(ELEMENT_YOUR_ACCOUNT_LABEL);
		waitForTextPresent("This will be your primary user account");
	}

	/**
	 * Case ID:79250.
	 * Test Case Name: The Field "username" is mandatory.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test03_TheFieldUsernameIsMandatory() {
		info("Test 3: The Field username is mandatory");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Acount Setup screen is displayed
		- 2 Blocs are displayed:* "Create your account" with following fields: ( username, First name, Last name and Email)* "Admin Password" with following fields: (username, password and confirm)		*/

		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		waitForAndGetElement(ELEMENT_YOUR_ACCOUNT_LABEL);
		waitForAndGetElement(ELEMENT_INPUT_USERNAME);
		waitForAndGetElement(ELEMENT_FIRSTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_LASTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_EMAIL_ACCOUNT);
		waitForAndGetElement(ELEMENT_INPUT_PASSWORD);
		waitForAndGetElement(ELEMENT_CONFIRM_PASS_ACCOUNT);
		/*
		- Input valid data on each fields
		- Keep the field "username" empty
		- Click on the button "Submit"
		 *Input Data: 
		 *Expected Outcome: 
		- A warning message is displayed: "Username is required."		*/ 

		type(ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);	
		type(ELEMENT_INPUT_PASSWORD, DATA_PASS, true);
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);	
		type(ELEMENT_ROOT_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);
		click(ELEMENT_SUBMIT_BUTTON);
		Utils.pause(100);
		assert waitForAndGetElement(ELEMENT_ACCOUNT_ERROR2).getText().equalsIgnoreCase("Please input all fields.");
	}

	/**
	 * Case ID:79251.
	 * Test Case Name: The Field "First name" is mandatory.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test04_TheFieldFirstNameIsMandatory() {
		info("Test 4: The Field First name is mandatory");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Acount Setup screen is displayed
		- 2 Blocs are displayed:* "Create your acoount" with following fields: ( username, First name, Last name and Email)* "Admin Password" with following fields: (username, password and confirm)		*/
		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		waitForAndGetElement(ELEMENT_YOUR_ACCOUNT_LABEL);
		waitForAndGetElement(ELEMENT_INPUT_USERNAME);
		waitForAndGetElement(ELEMENT_FIRSTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_LASTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_EMAIL_ACCOUNT);
		waitForAndGetElement(ELEMENT_INPUT_PASSWORD);
		waitForAndGetElement(ELEMENT_CONFIRM_PASS_ACCOUNT);

		/*
		- Input valid data on each fields
		- Keep the field "Firstname" empty
		- Click on the button "Submit"
		 *Input Data: 
		 *Expected Outcome: 
		- A warning message is displayed: "First name is required."		*/ 

		type(ELEMENT_INPUT_USERNAME, "fqa", true);
		type(ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);	
		type(ELEMENT_INPUT_PASSWORD, DATA_PASS, true);
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);	
		type(ELEMENT_ROOT_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_FIRSTNAME_ACCOUNT, "", true);
		click(ELEMENT_SUBMIT_BUTTON);
		Utils.pause(100);
		assert waitForAndGetElement(ELEMENT_ACCOUNT_ERROR2).getText().equalsIgnoreCase("Please input all fields.");
	}

	/**
	 * Case ID:79252.
	 * Test Case Name: The Field "Last name" is mandatory.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test05_TheFieldLastNameIsMandatory() {
		info("Test 5: The Field Last name is mandatory");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Account Setup screen is displayed
		- 2 Blocs are displayed:* "Create your account" with following fields: ( username, First name, Last name and Email)* "Admin Password" with following fields: (username, password and confirm)		*/

		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		waitForAndGetElement(ELEMENT_YOUR_ACCOUNT_LABEL);
		waitForAndGetElement(ELEMENT_INPUT_USERNAME);
		waitForAndGetElement(ELEMENT_FIRSTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_LASTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_EMAIL_ACCOUNT);
		waitForAndGetElement(ELEMENT_INPUT_PASSWORD);
		waitForAndGetElement(ELEMENT_CONFIRM_PASS_ACCOUNT);

		/*
		- Input valid data on each fields
		- Keep the field "Last name" empty
		- Click on the button "Submit"
		 *Input Data: 
		 *Expected Outcome: 
		- A warning message is displayed: "Last name is required."		*/ 
		type(ELEMENT_INPUT_USERNAME, "fqa", true);
		type(ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);	
		type(ELEMENT_INPUT_PASSWORD, DATA_PASS, true);
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);	
		type(ELEMENT_ROOT_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);	
		type(ELEMENT_LASTNAME_ACCOUNT, "", true);
		click(ELEMENT_SUBMIT_BUTTON);
		Utils.pause(100);
		assert waitForAndGetElement(ELEMENT_ACCOUNT_ERROR2).getText().equalsIgnoreCase("Please input all fields.");
	}

	/**
	 * Case ID:79253.
	 * Test Case Name: The Field "Email" is mandatory.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test06_TheFieldEmailIsMandatory() {
		info("Test 6: The Field Email is mandatory");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Acount Setup screen is displayed
		- 2 Blocs are displayed:* "Create your acoount" with following fields: ( username, First name, Last name and Email)* "Admin Password" with following fields: (username, password and confirm)		*/
		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		waitForAndGetElement(ELEMENT_YOUR_ACCOUNT_LABEL);
		waitForAndGetElement(ELEMENT_INPUT_USERNAME);
		waitForAndGetElement(ELEMENT_FIRSTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_LASTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_EMAIL_ACCOUNT);
		waitForAndGetElement(ELEMENT_INPUT_PASSWORD);
		waitForAndGetElement(ELEMENT_CONFIRM_PASS_ACCOUNT);

		/*
		- Input valid data on each fields
		- Keep the field "Email" empty
		- Click on the button "Submit"
		 *Input Data: 
		 *Expected Outcome: 
		- A warning message is displayed: "Email is required."		*/ 
		type(ELEMENT_INPUT_USERNAME, "fqa", true);
		type(ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(ELEMENT_INPUT_PASSWORD, DATA_PASS, true);
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);	
		type(ELEMENT_ROOT_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_EMAIL_ACCOUNT, "", true);	
		click(ELEMENT_SUBMIT_BUTTON);
		Utils.pause(100);
		assert waitForAndGetElement(ELEMENT_ACCOUNT_ERROR2).getText().equalsIgnoreCase("Please input all fields.");
	}

	/**
	 * Case ID:79254.
	 * Test Case Name: Input different data in fields "Password" and "Confirm" on form "Create your account".
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test07_InputDifferentDataInFieldsPasswordAndConfirmOnFormCreateYourAccount() {
		info("Test 7: Input different data in fields Password and Confirm on form Create your account");
		String strPass = "gtn123";
		String strPassConf = "gtn1234";

		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Account Setup screen is displayed
		- 2 Blocs are displayed:* "Create your account" with following fields: ( username, First name, Last name and Email)* "Admin Password" with following fields: (username, password and confirm)		*/

		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		waitForAndGetElement(ELEMENT_YOUR_ACCOUNT_LABEL);
		waitForAndGetElement(ELEMENT_INPUT_USERNAME);
		waitForAndGetElement(ELEMENT_FIRSTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_LASTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_EMAIL_ACCOUNT);
		waitForAndGetElement(ELEMENT_INPUT_PASSWORD);
		waitForAndGetElement(ELEMENT_CONFIRM_PASS_ACCOUNT);

		/*
		- Input valid data on each fields on upper form
		- Input diffrent data in fields "Password" and "Confirm" on upper form
		- Input into fields on under form
		- Click on the button "Submit"
		 *Input Data: 
		 *Expected Outcome: 
		- A warning message is displayed: "Password does not match the Confirm password"		*/ 

		type(ELEMENT_INPUT_USERNAME, "fqa", true);
		type(ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(ELEMENT_INPUT_PASSWORD, strPass, true);
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, strPassConf, true);
		click(ELEMENT_SUBMIT_BUTTON);
		Utils.pause(100);
		waitForTextPresent("Password does not match the Confirm password");
	}

	/**
	 * Case ID:79255.
	 * Test Case Name: The Field "Password" is mandatory.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test08_TheFieldPasswordIsMandatory() {
		info("Test 8: The Field Password is mandatory");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Acount Setup screen is displayed
		- 2 Blocs are displayed:* "Create your acoount" with following fields: ( username, First name, Last name and Email)* "Admin Password" with following fields: (username, password and confirm)		*/

		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		waitForAndGetElement(ELEMENT_YOUR_ACCOUNT_LABEL);
		waitForAndGetElement(ELEMENT_INPUT_USERNAME);
		waitForAndGetElement(ELEMENT_FIRSTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_LASTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_EMAIL_ACCOUNT);
		waitForAndGetElement(ELEMENT_INPUT_PASSWORD);
		waitForAndGetElement(ELEMENT_CONFIRM_PASS_ACCOUNT);

		/*
		- Input valid data on each fields
		- Keep the field "Password" empty
		- Click on the button "Submit"
		 *Input Data: 
		 *Expected Outcome: 
		- A warning message is displayed: "Password is required."		*/ 
		type(ELEMENT_INPUT_USERNAME, "fqa", true);
		type(ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);	
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);	
		type(ELEMENT_ROOT_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_INPUT_PASSWORD, "", true);
		click(ELEMENT_SUBMIT_BUTTON);
		Utils.pause(100);
		assert waitForAndGetElement(ELEMENT_ACCOUNT_ERROR1).getText().equalsIgnoreCase("Please input all fields.");
	}

	/**
	 * Case ID:79256.
	 * Test Case Name: The Field "Confirm" is mandatory.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test09_TheFieldConfirmIsMandatory() {
		info("Test 9 The Field Confirm is mandatory");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Account Setup screen is displayed
		- 2 Blocs are displayed:* "Create your account" with following fields: ( username, First name, Last name and Email)* "Admin Password" with following fields: (username, password and confirm)		*/

		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		waitForAndGetElement(ELEMENT_YOUR_ACCOUNT_LABEL);
		waitForAndGetElement(ELEMENT_INPUT_USERNAME);
		waitForAndGetElement(ELEMENT_FIRSTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_LASTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_EMAIL_ACCOUNT);
		waitForAndGetElement(ELEMENT_INPUT_PASSWORD);
		waitForAndGetElement(ELEMENT_CONFIRM_PASS_ACCOUNT);

		/*
		- Input valid data on each fields
		- Keep the field "Confirm" empty
		- Click on the button "Submit"
		 *Input Data: 
		 *Expected Outcome: 
		- A warning message is displayed: "Confirm is required."		*/ 

		type(ELEMENT_INPUT_USERNAME, "fqa", true);
		type(ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);	
		type(ELEMENT_INPUT_PASSWORD, DATA_PASS, true);
		type(ELEMENT_ROOT_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, "", true);	
		click(ELEMENT_SUBMIT_BUTTON);
		Utils.pause(100);
		assert waitForAndGetElement(ELEMENT_ACCOUNT_ERROR2).getText().equalsIgnoreCase("Please input all fields.");
	}

	/**
	 * Case ID:79315.
	 * Test Case Name: Input different data in fields "Password" and "Confirm" of root.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test10_InputDifferentDataInFieldsPasswordAndConfirmOfRoot() {
		info("Test 10 Input different data in fields Password and Confirm of root");
		String strPassRoot = "gtngtn";
		String strPassConf = "gtn123";
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Acount Setup screen is displayed
		- 2 Blocs are displayed:* "Create your acoount" with following fields: ( username, First name, Last name and Email)* "Admin Password" with following fields: (username, password and confirm)		*/

		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		waitForAndGetElement(ELEMENT_YOUR_ACCOUNT_LABEL);
		waitForAndGetElement(ELEMENT_INPUT_USERNAME);
		waitForAndGetElement(ELEMENT_FIRSTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_LASTNAME_ACCOUNT);
		waitForAndGetElement(ELEMENT_EMAIL_ACCOUNT);
		waitForAndGetElement(ELEMENT_INPUT_PASSWORD);
		waitForAndGetElement(ELEMENT_CONFIRM_PASS_ACCOUNT);
		waitForAndGetElement(ELEMENT_ADMIN_PASS_LABEL);
		waitForAndGetElement(ELEMENT_ROOT_PASS_ACCOUNT);
		waitForAndGetElement(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT);

		/*
		- Input valid data on each fields on upper form
		- Input diffrent data in fields "Password" and "Confirm" on under form (of root)
		- Click on the button "Submit"
		 *Input Data: 
		 *Expected Outcome: 
		- A warning message is displayed: "Password does not match the Confirm password"		*/ 
		type(ELEMENT_INPUT_USERNAME, "fqa", true);
		type(ELEMENT_FIRSTNAME_ACCOUNT, "FQA", true);
		type(ELEMENT_LASTNAME_ACCOUNT, "VN", true);
		type(ELEMENT_EMAIL_ACCOUNT, "fqa@exoplatform.com", true);	
		type(ELEMENT_INPUT_PASSWORD, DATA_PASS, true);
		type(ELEMENT_CONFIRM_PASS_ACCOUNT, DATA_PASS, true);
		type(ELEMENT_ROOT_PASS_ACCOUNT, strPassRoot, true);
		type(ELEMENT_ROOT_CONFIRM_PASS_ACCOUNT, strPassConf, true);
		click(ELEMENT_SUBMIT_BUTTON);
		Utils.pause(100);
		waitForTextPresent("Password does not match the Confirm password");
	}

	/**
	 * Case ID:79257.
	 * Test Case Name: Display "root" by default in the field "Username".
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test11_DisplayRootByDefaultInTheFieldUsername() {
		info("Test 11 Display root by default in the field Username");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Account Setup screen is displayed
		- In the Bloc "Admin Pasword", the field "Username" is prefilled with "root" value and its disable		*/ 

		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		waitForAndGetElement(ELEMENT_ADMIN_PASS_LABEL);
		waitForAndGetElement(ELEMENT_USER_ADMIN).getAttribute("value").equalsIgnoreCase("root");		
	}

	/**
	 * Case ID:79258.
	 * Test Case Name: Display information text after the label "Admin Password".
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test12_DisplayInformationTextAfterTheLabelAdminPassword() {
		info("Test 12 Display information text after the label Admin Password");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Acount Setup screen is displayed		*/
		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);

		/*
		- read after "Admin Password"
		 *Input Data: 
		 *Expected Outcome: 
		- An info text is displayed "Login as root user with the following password for super administrator access" 		*/ 
		waitForAndGetElement(ELEMENT_ADMIN_PASS_LABEL);
		waitForTextPresent("Login as root user with the following password for super administrator access");
	}

	/**
	 * Case ID:79259.
	 * Test Case Name: Submit the accounts by click on "Start".
	 */
	/**
	 * Case ID:79248.
	 * Test Case Name: Not display "Setup account" screen at second launch with the 1st time account has been setup.
	 * Pre-Condition: The terms and conditions screen is submitted during the first launch
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */

	@Test
	public  void test13_SubmitTheAccountsByClickOnStart() {
		info("Test 13 Submit the accounts by click on Start");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		 *Input Data: 
		 *Expected Outcome: 
		- The Acount Setup screen is displayed		*/

		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);

		/*
		- Input valid data on each fields
		- Click on the button "Submit"
		 *Input Data: 
		 *Expected Outcome: 
		- The Greeting screen is displayed		*/

		/*
		- Click on the button "Start"
		 *Input Data: 
		 *Expected Outcome: 
		- The user is automatically logged in with the account created and redirected to the intranet home		*/ 

		/*
		- Launch the app for a second time
		 *Input Data: 
		 *Expected Outcome: 
		- The screen "Terms and Conditions Agreement" is Not displayed
		- The screen "Setup account" is not displayed		*/
		accountSetup();	
		waitForElementNotPresent(By.xpath(ELEMENT_TERM_CONDITION_BOX));
	}

	/**
	 * Case ID:79260.
	 * Test Case Name: The new user account is an administrator.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test
	public  void test14_TheNewUserAccountIsAnAdministrator() {
		String strPassRoot = "gtngtn";
		info("Test 14 The new user account is an administrator");
		/*
		- Launch the app for the first time (/portal)
		- Check the checkbox: "I agree with these terms and conditions"
		- Click on the button "Continue"
		-In the screen "Account Setup", Input valid data on each fields
		- Click on the button "Submit" 
		 *Input Data: 
		 *Expected Outcome: 
		- Intranet Homepage is displayed
		- User is looged with the account created 
		- The user can see the full Administration menu
		- This user in membership:*:/platform/administrators*:/platform/web
		-contributors*:/platform:/users*:/developers		*/ 	
		acc.signIn("fqa", strPassRoot);
		/*Expected Outcome: 
			- Intranet Homepage is displayed
			- User is looged with the account created 
			- The user can see the full Administration menu
			- This user in membership:*:/platform/administrators*:/platform/web
			-contributors*:/platform:/users*:/developers		*/ 
		waitForAndGetElement(ELEMENT_CATEGORY_ADMINISTRATION);
	}

	/**
	 * Case ID:79313.
	 * Test Case Name: Not display the Account setup when the PLF is started in developer mode.
	 * Pre-Condition: *Terms and Conditions is accepted*Account is created and session has been started
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test(groups="pending")
	public  void test15_NotDisplayTheAccountSetupWhenThePLFIsStartedInDeveloperMode() {
		info("Test 15 Not display the Account setup when the PLF is started in developer mode");
		/*
		- Run the server with a command+ "start_eXo.bat 
		-
		-dev", for Windows+ "./start_eXo.sh 
		-
		-dev" for Linux
		 *Input Data: 
		 *Expected Outcome: 
		- The Intranet Homepage is displayed
		- The trial banner is not displayed in the footer of the page		*/

		/*
		- Open platform in browser
		 *Input Data: 
		 *Expected Outcome: 
		- The Account setup is not displayed 		*/ 
	}

	/**
	 * Case ID:94920.
	 * Test Case Name: Skip Creating account.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test(groups="pending")
	public  void test16_SkipCreatingAccount() {
		info("Test 17 Skip Creating account");
		/*
		- Start a fresh package
		 *Input Data: 
		 *Expected Outcome: Server is started without exception		*/
		waitForAndGetElement(ELEMENT_TERM_CONDITION_BOX);

		/*
		- Open Browser and type in address: http://<server>:8080/portal
		- Tick check
		- box to accept
		- Click Continue
		 *Input Data: 
		 *Expected Outcome: 
		- Term and Condition is accepted 
		- Move to Setup account screen		*/

		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);

		/*Click Skip button
		 *Input Data: 
		 *Expected Outcome: 
		- Return to login screen
		- Can log in with root/gtn here to create new account 		*/ 
		click(ELEMENT_SKIP_BUTTON);
		acc = new ManageAccount(driver, plfVersion);
		acc.signIn(USER_ROOT, PASS_ROOT);;		
	}

	/**
	 * Case ID:94923.
	 * Test Case Name: Not Display "Setup account" screen at the second launch with the 1st time skip setup account.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test(groups="pending")
	public  void test17_NotDisplaySetupAccountScreenAtTheSecondLaunchWithThe1stTimeSkipSetupAccount() {
		info("Test 18 Not Display Setup account screen at the second launch with the 1st time skip setup account");
		/*
		- Launch the app for a first time 
		- Accept Term & condition agreement
		- Skip setting up account
		 *Input Data: 
		 *Expected Outcome: Return log in screen, can log in by default users		*/
		info("Account Setup displays");
		waitForAndGetElement(ELEMENT_ACCOUNT_SETUP);
		click(ELEMENT_SKIP_BUTTON);
		acc = new ManageAccount(driver, plfVersion);
		acc.signIn(USER_ROOT, PASS_ROOT);

		/*
		- Restart server
		- Open application via browser
		 *Input Data: 
		 *Expected Outcome: 
		- Account Setup does not appear again
		- Log in screen is shown		*/ 
		driver.quit();
		beforeMethod();
		waitForElementNotPresent(By.xpath(ELEMENT_TERM_CONDITION_BOX));
		waitForAndGetElement(ELEMENT_SIGN_IN_BUTTON);		
	}

	/**
	 * Case ID:79313.
	 * Test Case Name: Not display "Setup account" screen at second launch with the 1st time account has been setup
	 * Pre-Condition: *Terms and Conditions is accepted*Account is created and session has been started
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/01/09 10:00:06
	 */
	@Test(groups="pending")
	public  void test18_NotDisplaySetupAccountScreenAtSecondLaunchWithThe1stTimeAccountHasBeenSetup() {
		info("Test 18 Not display the Account setup when the PLF is started in developer mode");
		/*
		- Launch the app for a second time		*/

		/*
		- The screen "Terms and Conditions Agreement" is Not displayed
        - The screen "Setup accpunt" is not displayed	*/ 
	}
}