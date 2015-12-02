package org.exoplatform.selenium.platform.plf.OAuthAuthentication.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class SignInFlow2 extends PlatformBase{
		ManageLogInOut manageLoginOut;
		HomePagePlatform homepage;
		NavigationToolbar navigationToolbar;
		Button button;		
		UserAndGroupManagement userAndGroup;
		
		UserAddManagement addUserPage;
		
		@BeforeClass
		public void setupBeforeClass() throws Exception{
			info("Start setUpBeforeClass");			
			initSeleniumTest();
			
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		
		@BeforeMethod
		public void setupBeforeMethod() throws Exception{
			info("Start setUpBeforeMethod");
			
			initSeleniumTest();

			manageLoginOut = new ManageLogInOut(driver);
		
			homepage = new HomePagePlatform(driver);
			navigationToolbar = new NavigationToolbar(driver);
			button = new Button(driver);
			userAndGroup = new UserAndGroupManagement(driver);
			
			addUserPage = new UserAddManagement(driver);
		}		
		
		@AfterMethod
	    public void afterMethod(){
			driver.manage().deleteAllCookies();
			driver.quit();
	    }	
		
	/**
	*<li> Case ID:128294.</li>
	*<li> Test Case Name: Check the OAuth flow when has not authorized eXo to access his user data and  not currently logged in the social network.</li>
	*<li> Pre-Condition: User A is not currently logged in the social network
	*					 User A has not authorized eXo to access his user data in the social network</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckTheOAuthFlowWhenHasNotAuthorizedEXoToAccessHisUserDataAndNotCurrentlyLoggedInTheSocialNetwork() {
		info("Test 2: Check the OAuth flow when has not authorized eXo to access his user data and  not currently logged in the social network");
		/*Step Number: 1
		*Step Name: Step 1: Choose a social network to sign in
		*Step Description: 
			- Open Login page (accessible from /portal/login)
			- Choose Sign in with a social network
		*Input Data: 
			
		*Expected Outcome: 
			- Login page of Social network is displayed*/

		manageLoginOut.checkGoogleLoginForm();
				
		/*Step number: 2
		*Step Name: Step 2: Sign in to social network
		*Step Description: 
			- Enter valid value of User A to sign in to social network
		*Input Data: 
			
		*Expected Outcome: 
			- The user is signed in to the chosen social network successfully.
			- OAuth authorization screen is displayed*/ 
		
		info("check login with Google account");
		manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_GOOGLE, SOCIAL_NETWORKS_PASSWORD);
		Utils.pause(3000);
		
		info("Check OAuth authorization screen");
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_DENY);
 	}		

	/**
	*<li> Case ID:128304.</li>
	*<li> Test Case Name: Check the Register pop up after click on create new account.</li>
	*<li> Pre-Condition: - user has already an existing account in exo.
						 - ACME addon is already installed.</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckTheRegisterPopUpAfterClickOnCreateNewAccount() {
		info("Test 6: Check the Register pop up after click on create new account");
		//Change user's email
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		navigationToolbar.goToCommunityManagement();
		userAndGroup.goToEditUserInfo(DATA_USER4);
		userAndGroup.editUserInfo_AccountTab(null, null, null, SOCIAL_NETWORKS_ACCOUNT_FACEBOOK);
		
		manageLoginOut.signOut();
		
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to acme site (portal/acme).
			- Click on Register button.
		*Input Data: 
			
		*Expected Outcome: 
			- The Registration form is displayed.
			- Fields are displayed.*/

		driver.get(baseUrl+"/acme");
		waitForAndGetElement(manageLoginOut.ELEMENT_ACME_REGISTER_LINK);
		click(manageLoginOut.ELEMENT_ACME_REGISTER_LINK);
		waitForAndGetElement(manageLoginOut.ELEMENT_ACME_REGISTER_NEW_ACCOUNT);
		waitForAndGetElement(manageLoginOut.ELEMENT_ACME_REGISTER_NEW_ACCOUNT_FACEBOOK);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			-Select to register with "Facebook"
		*Input Data: 
			
		*Expected Outcome: 
			- A dialog Existing Account Detected is displayed 
			- Buttons: Confirm and Register New Account are displayed*/

		click(manageLoginOut.ELEMENT_ACME_REGISTER_NEW_ACCOUNT_FACEBOOK);
		manageLoginOut.loginWithFacebookAccount(SOCIAL_NETWORKS_ACCOUNT_FACEBOOK, SOCIAL_NETWORKS_PASSWORD);
		Utils.pause(3000);
		
		info("Verify POPUP");
		waitForAndGetElement(manageLoginOut.ELEMENT_ACME_REGISTER_NEW_ACCOUNT);
				
		info("Verify actions");
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CONFIRM);
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_REGISTER);	
		
		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click on the button "Register New Account"
		*Input Data: 
			
		*Expected Outcome: 
			- A Register New Account form is displayed with pre-filled vales that allows user to adjust account settings.*/ 
		click(manageLoginOut.ELEMENT_DETECT_ACCOUNT_REGISTER);	
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_USERNAME.replace("${value}", "exomailtest01"));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_FIRST_NAME.replace("${value}", "Hoang"));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_LAST_NAME.replace("${value}", "Trang"));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_DISPLAY_NAME.replace("${value}", "Hoang Trang"));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_EMAIL_ACME.replace("${value}", SOCIAL_NETWORKS_ACCOUNT_FACEBOOK));		
 	}
		
		

	/**
	*<li> Case ID:128361.</li>
	*<li> Test Case Name: Check the OAuth flow when has not authorized eXo to access his user data and 
	* currently logged in the social network.</li>
	*<li> Pre-Condition: User A is currently logged in the social network
	*User A has not authorized eXo to access his user data in the social network</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckTheOAuthFlowWhenHasNotAuthorizedEXoToAccessHisUserDataAndCurrentlyLoggedInTheSocialNetwork() {
		info("Test 9: Check the OAuth flow when has not authorized eXo to access his user data and  currently logged in the social network");
		
		info("Login to gmail");
		String url = "gmail.com";
		driver.get(url);
		
		manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_TWITTER, SOCIAL_NETWORKS_PASSWORD);
		Utils.pause(3000);
		
		/*Step Number: 1
		*Step Name: Step 1: Choose a social network to sign in
		*Step Description: 
			- Open Login page (accessible from /portal/login)
			- Choose Sign in with a social network
		*Input Data: 
			
		*Expected Outcome: 
			- The user is signed in to the chosen social network successfully.
			- OAuth authorization screen is displayed*/ 

		driver.get(baseUrl);
		click(manageLoginOut.ELEMENT_SIGN_IN_GOOGLE_BUTTON);
		
		waitForAndGetElement(manageLoginOut.ELEMENT_SOCIAL_ACCOUNT_LOGGEDIN.replace("${acount}", SOCIAL_NETWORKS_ACCOUNT_TWITTER));
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_DENY);
 	}


	/**
	*<li> Case ID:128363.</li>
	*<li> Test Case Name: Check the registration on the fly in case username extracted from the social network matches an existing eXo user name.</li>
	*<li> Pre-Condition: -
	- By default, sign up on fly mode is activated for FACEBOOK, GOOGLE, LINKEDIN providers (If want to change the setting for sign up on fly mode, we can setting in exo.properties file)
	-
	- Social network account has not authorized eXo to access his user data in the social network
	-
	- Social network account is not currently logged in the social network
	-
	- Social network account is not linked to any eXo user account
	-
	- USERNAME extracted from the social network MATCHES an existing eXo user name
	-
	- DELETE all COOKIES before testing this case</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckTheRegistrationOnTheFlyInCaseUsernameExtractedFromTheSocialNetworkMatchesAnExistingEXoUserName() {
		info("Test 11 Check the registration on the fly in case username extracted from the social network matches an existing eXo user name");
	
		//Create user having the same name with social account
		String username = "exofqagplus";
		String firstName = "exofqagplus";
		String lastName = "fqaplus";
		String email = "useremail@mail.com";
		String password = "123456";
		
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		navigationToolbar.goToAddUser();
		addUserPage.addUser(username, password, email, firstName, lastName);
		manageLoginOut.signOut();
		
		/*Step Number: 1
		*Step Name: Step 1: Open Login page
		*Step Description: 
			- Login page (accessible from /portal/login)
			- Choose Sign in with a provider that is actived sign up on fly mode (For example: Facebook)
		*Input Data: 
			
		*Expected Outcome: 
			- Login page of chosen social network is displayed*/
		
		manageLoginOut.checkGoogleLoginForm();
		
		/*Step number: 2
		*Step Name: Step 2: Sign in to social network
		*Step Description: 
			- Enter valid value for social network account to sign in
		*Input Data: 
			
		*Expected Outcome: 
			- OAuth authorization page is displayed*/
		
		manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_GOOGLE, SOCIAL_NETWORKS_PASSWORD);
		Utils.pause(3000);
		
		/*Step number: 3
		*Step Name: Step 3: Sign in on OAuth authorization page
		*Step Description: 
			- On OAuth authorization page: click Accept button
		*Input Data: 
			
		*Expected Outcome: 
			- Existing Account Detected screen is displayed with following information:
			+ Title : Existing Account Detected
			+ Message : We have detected that an eXo account already exists for $DETECTED. 
				If you would like , please enter your eXo password to confirm where $DETECTED is user name detected.
			+ eXo Password a password field
			+ Actions : Confirm, Create new Account*/ 
	
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW, 2000,1);
		click(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
		
		info("Verify title");
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_POPUP);
		
		info("Verify message");
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_MESSAGE.replace("${detectedName}", SOCIAL_NETWORKS_ACCOUNT_GOOGLE.subSequence(0, SOCIAL_NETWORKS_ACCOUNT_GOOGLE.indexOf('@'))));
		
		info("Verify password");
		waitForAndGetElement(manageLoginOut.ELEMENT_NEW_ACCOUNT_PASSWORD);
		
		info("Verify actions");
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CONFIRM);
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_REGISTER);	
 	}

	/**
	*<li> Case ID:128364.</li>
	*<li> Test Case Name: Check the registration on the fly in case the email address extracted from the social network matches an existing user account email.</li>
	*<li> Pre-Condition: -
	- By default, sign up on fly mode is activated for FACEBOOK, GOOGLE, LINKEDIN providers (If want to change the setting for sign up on fly mode, we can setting in exo.properties file)
	-
	- Social network account has not authorized eXo to access his user data in the social network
	-
	- Social network account is not currently logged in the social network
	-
	- Social network account is not linked to any eXo user account
	-
	- EMAIL address extracted from the social network MATCHES an existing user account email
	-
	- DELETE all COOKIES before testing this case</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CheckTheRegistrationOnTheFlyInCaseTheEmailAddressExtractedFromTheSocialNetworkMatchesAnExistingUserAccountEmail() {
		info("Test 12 Check the registration on the fly in case the email address extracted from the social network matches an existing user account email");
		//Change user's email
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		navigationToolbar.goToCommunityManagement();
		userAndGroup.goToEditUserInfo("mary");
		userAndGroup.editUserInfo_AccountTab(null, null, null, SOCIAL_NETWORKS_ACCOUNT_LINKEDIN);
		
		manageLoginOut.signOut();
		
		/*Step Number: 1
		*Step Name: Step 1: Open Login page
		*Step Description: 
			- Login page (accessible from /portal/login)
			- Choose Sign in with a provider that is actived sign up on fly mode (For example: Facebook)
		*Input Data: 
			
		*Expected Outcome: 
			- Login page of chosen social network is displayed*/

		manageLoginOut.checkGoogleLoginForm();
		
		/*Step number: 2
		*Step Name: Step 2: Sign in to social network
		*Step Description: 
			- Enter valid value for social network account to sign in
		*Input Data: 
			
		*Expected Outcome: 
			- OAuth authorization page is displayed*/

		manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_LINKEDIN, SOCIAL_NETWORKS_PASSWORD);
		Utils.pause(3000);
		
		/*Step number: 3
		*Step Name: Step 3: Sign in on OAuth authorization page
		*Step Description: 
			- On OAuth authorization page: click Accept button
		*Input Data: 
			
		*Expected Outcome: 
			- Existing Account Detected screen is displayed with following information:
			+ Title : Existing Account Detected
			+ Message : We have detected that an eXo account already exists for $DETECTED. 
				If you would like , please enter your eXo password to confirm where $DETECTED is email detected.
			+ eXo Password a password field
			+ Actions : Confirm, Register New Account*/ 
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW, 2000,1);
		click(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
		
		info("Verify title");
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_POPUP);
		
		info("Verify message");
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_MESSAGE.replace("${detectedName}", SOCIAL_NETWORKS_ACCOUNT_LINKEDIN));
		
		info("Verify password");
		waitForAndGetElement(manageLoginOut.ELEMENT_NEW_ACCOUNT_PASSWORD);
		
		info("Verify actions");
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CONFIRM);
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_REGISTER);	
		
		info("Close");
		click(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CLOSE_BTN);	
		
		//Unlink
		navigationToolbar.goToMySettings();
		userAndGroup.unLinkedSocialAccount(manageLoginOut.ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_GOOGLE);
		button.ok();
		button.close();		
		
		//Change user's email back
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		navigationToolbar.goToCommunityManagement();
		userAndGroup.goToEditUserInfo("mary");
		userAndGroup.editUserInfo_AccountTab(null, null, null, "mary@acme.exoplatform.com");
		
		manageLoginOut.signOut();
 	}

	/**
	*<li> Case ID:128365.</li>
	*<li> Test Case Name: Check failed authentication on Existing Account Detected form via using eXo password.</li>
	*<li> Pre-Condition: -
	- By default, sign up on fly mode is activated for FACEBOOK, GOOGLE, LINKEDIN providers (If want to change the setting for sign up on fly mode, we can setting in exo.properties file)
	-
	- Social network account has not authorized eXo to access his user data in the social network
	-
	- Social network account is not currently logged in the social network
	-
	- Open "Existing Account Detected" form in following cases:
	+ The username extracted from the social network matches an existing eXo user name
	+ The email address extracted from the social network matches an existing user account email
	+ Current browser has recently logged on this eXo server(user name detected via a cookie)</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckFailedAuthenticationOnExistingAccountDetectedFormViaUsingEXoPassword() {
		info("Test 13 Check failed authentication on Existing Account Detected form via using eXo password");
		//Change user's email
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		navigationToolbar.goToCommunityManagement();
		userAndGroup.goToEditUserInfo("mary");
		userAndGroup.editUserInfo_AccountTab(null, null, null, SOCIAL_NETWORKS_ACCOUNT_LINKEDIN);
		
		manageLoginOut.signOut();
		
		/*Step Number: 1
		*Step Name: Step 1: Open Login page
		*Step Description: 
			- Login page (accessible from /portal/login)
			- Choose Sign in with a provider that is actived sign up on fly mode (For example: FACEBOOK)
		*Input Data: 
			
		*Expected Outcome: 
			- Login page of chosen social network is displayed*/
		
		manageLoginOut.checkGoogleLoginForm();
		
		/*Step number: 2
		*Step Name: Step 2: Sign in to social network
		*Step Description: 
			- Enter valid value of social network account to sign in
		*Input Data: 
			
		*Expected Outcome: 
			- OAuth authorization screen is displayed*/
		manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_LINKEDIN, SOCIAL_NETWORKS_PASSWORD);
		Utils.pause(3000);
		
		/*Step number: 3
		*Step Name: Step 3: Open "Existing Account Detected" form
		*Step Description: 
			- On OAuth authorization page: click Accept button
		*Input Data: 
			
		*Expected Outcome: 
			- Existing Account Detected form is displayed*/
		
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW, 2000,1);
		click(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_POPUP);
		
		/*Step number: 4
		*Step Name: Step 4: Enter invalid eXo password
		*Step Description: 
			- Enter an invalid password of eXo account
			- Click "Confirm" button
		*Input Data: 
			
		*Expected Outcome: 
			- Error message "Wrong password, please try again!" is displayed.*/ 
		type(manageLoginOut.ELEMENT_NEW_ACCOUNT_PASSWORD, "123456", true);
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CONFIRM);
		click(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CONFIRM);
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_ERROR_MESSAGE.replace("${message}", "Wrong password"));
 	
		info("Close");
		click(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CLOSE_BTN);	
		
		//Change user's email back
		manageLoginOut.signIn(DATA_USER1, DATA_PASS);
		navigationToolbar.goToCommunityManagement();
		userAndGroup.goToEditUserInfo("mary");
		userAndGroup.editUserInfo_AccountTab(null, null, null, "mary@acme.exoplatform.com");
		
		manageLoginOut.signOut();
	}
}