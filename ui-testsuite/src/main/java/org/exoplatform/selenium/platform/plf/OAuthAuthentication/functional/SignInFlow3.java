package org.exoplatform.selenium.platform.plf.OAuthAuthentication.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class SignInFlow3 extends PlatformBase{
		ManageLogInOut manageLoginOut;
		HomePagePlatform homepage;
		NavigationToolbar navigationToolbar;
		Button button;		
		UserAndGroupManagement userAndGroup;
		UserProfilePage myProfile;
		
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
			
			myProfile = new UserProfilePage(driver);
		}		
		
		@AfterMethod
	    public void afterMethod(){
			driver.manage().deleteAllCookies();
			driver.quit();
	    }	
		
		/**
		*<li> Case ID:128295.</li>
		*<li> Test Case Name: Check the OAuth flow when user did not accept on OAuth authorization screen.</li>
		*<li> Pre-Condition: User A has not authorized eXo to access his user data in the social network
		*					 User A is not currently logged in the social network</li>
		*<li> Post-Condition: </li>
		*/
		@Test
		public  void test03_CheckTheOAuthFlowWhenUserDidNotAcceptOnOAuthAuthorizationScreen() {
			info("Test 3: Check the OAuth flow when user did not accept on OAuth authorization screen");
			/*Step Number: 1
			*Step Name: Step 1: Choose Sign in with a social network
			*Step Description: 
				- Open Login page (accessible from /portal/login)
				- Choose Sign in with a social network
			*Input Data: 
				
			*Expected Outcome: 
				- Login page of chosen social network is displayed*/
			
			manageLoginOut.checkGoogleLoginForm();
			
			/*Step number: 2
			*Step Name: Step 2: Login to chosen social network
			*Step Description: 
				- Input valid value for Username and Password of User A to connect to the social network
				- Click "Sign In"
			*Input Data: 
				
			*Expected Outcome: 
				- The OAuth authorization of chosen social network is displayed*/
			
			info("check login with Google account");
			manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_GOOGLE, SOCIAL_NETWORKS_PASSWORD);
			
			info("Check OAuth authorization screen");
			waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
			waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_DENY);
			
			/*Step number: 3
			*Step Name: Step 3: Do not accept eXo platform receive the information of User A on social network
			*Step Description: 
				- On OAuth authorization page: click Cancel to do not accept eXo platform to receive the information of User A on social network
			*Input Data: 
				
			*Expected Outcome: 
				- It should be redirected to Login page of eXo page*/ 
			info("Click Cancel");

			Utils.pause(3000);
			click(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_DENY);
			waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_BUTTON);
	 	}

		/**
		*<li> Case ID:128296.</li>
		*<li> Test Case Name: Check the OAuth flow when Social network is linked to eXo account.</li>
		*<li> Pre-Condition: User has granted authorization
		*The social network account is already linked to an eXo user accountted Authorization to access to Social Network</li>
		*<li> Post-Condition: </li>
		*/
		@Test
		public  void test04_CheckTheOAuthFlowWhenSocialNetworkIsLinkedToEXoAccount() {
			info("Test 4: Check the OAuth flow when Social network is linked to eXo account");
			
			//Create account linked to Google 
			manageLoginOut.checkGoogleLoginForm();
			manageLoginOut.loginWithGoogleAccount(EMAIL_ADDRESS2, SOCIAL_NETWORKS_PASSWORD);
			Utils.pause(3000);
			click(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
			
			manageLoginOut.signOut();
			
			afterMethod();
			try {
				setupBeforeMethod();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			/*Step Number: 1
			*Step Name: 
			*Step Description: 
				- Connect to the Portal
				- Sign With a social network (A)
			*Input Data: 
				
			*Expected Outcome: 
				- The AOuth page is displayed*/

			manageLoginOut.checkGoogleLoginForm();
			
			/*Step number: 2
			*Step Name: 
			*Step Description: 
				- Input Username and Password of the social Network
				- Click "Connect"
			*Input Data: 
				
			*Expected Outcome: 
				- the user is authenticated and redirected to eXo*/ 
			info("check login with Google account");
			manageLoginOut.loginWithGoogleAccount(EMAIL_ADDRESS2, SOCIAL_NETWORKS_PASSWORD);
			Utils.pause(3000);
			click(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
			waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
			
			//Unlink
			navigationToolbar.goToMySettings();
			userAndGroup.unLinkedSocialAccount(manageLoginOut.ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_GOOGLE);
			button.ok();
			button.close();	
	 	}
		
		/**
		*<li> Case ID:128362.</li>
		*<li> Test Case Name: Check the registration on the fly in case an eXo account is detected via a cookie.
		*</li>
		*<li> Pre-Condition: -
		- By default, sign up on fly mode is activated for FACEBOOK, GOOGLE, LINKEDIN providers 
		(If want to change the setting for sign up on fly mode, we can setting in exo.properties file)
		-
		- Social network account has not authorized eXo to access his user data in the social network
		-
		- Social network account is not currently logged in the social network
		-
		- Social network account is not linked to any eXo user account
		-
		- An eXo account is detected via a cookie. Do it as following:
		+ Login with an eXo account (for example: Root)
		+ Logout
		+ After that choosing Sign in with a social network</li>
		*<li> Post-Condition: </li>
		*/
		@Test
		public  void test10_CheckTheRegistrationOnTheFlyInCaseAnEXoAccountIsDetectedViaACookie() {
			info("Test 10 Check the registration on the fly in case an eXo account is detected via a cookie");
			
			info("Login to exo");
			manageLoginOut.signIn(DATA_USER1, DATA_PASS);
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

			manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_TWITTER, SOCIAL_NETWORKS_PASSWORD);
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
				If you would like to use it, please enter your eXo password to confirm 
				where $DETECTED is user name detected.
				+ eXo Password a password field
				+ Actions : Confirm, Register New Account*/ 
		
			waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW, 2000,1);
			click(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
			
			info("Verify title");
			waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_POPUP);
			
			info("Verify message");
			waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_MESSAGE.replace("${detectedName}", DATA_USER1));
			
			info("Verify password");
			waitForAndGetElement(manageLoginOut.ELEMENT_NEW_ACCOUNT_PASSWORD);
			
			info("Verify actions");
			waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CONFIRM);
			waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_REGISTER);	
			Utils.pause(3000);
	 	}
		
		/**
		*<li> Case ID:128366.</li>
		*<li> Test Case Name: Check failed authentication on Existing Account Detected form in case the user is disabled.</li>
		*<li> Pre-Condition: -
		- By default, sign up on fly mode is activated for FACEBOOK, GOOGLE, LINKEDIN providers 
		(If want to change the setting for sign up on fly mode, we can setting in exo.properties file)
		-
		- Social network account has not authorized eXo to access his user data in the social network
		-
		- Social network account is not currently logged in the social network
		-
		-Username extracted from the social network matches an existing eXo user name BUT eXo user name is DISABLED</li>
		*<li> Post-Condition: </li>
		*/
		@Test
		public  void test14_CheckFailedAuthenticationOnExistingAccountDetectedFormInCaseTheUserIsDisabled() {
			info("Test 14 Check failed authentication on Existing Account Detected form in case the user is disabled");
					
			//Update user
			manageLoginOut.signIn(DATA_USER1, DATA_PASS);
			navigationToolbar.goToCommunityManagement();
			//Change email
			userAndGroup.goToEditUserInfo("james");
			userAndGroup.editUserInfo_AccountTab(null, null, null, SOCIAL_NETWORKS_ACCOUNT_SUSPENDED);
			//Disable user
			navigationToolbar.goToCommunityManagement();
			WebElement userSwitchOn = waitForAndGetElement(manageLoginOut.ELEMENT_USER_STATUS_SWITCHON_BTN.replace("${userName}", "james"), 2000,0);
			if(userSwitchOn != null){
				click(manageLoginOut.ELEMENT_USER_STATUS_SWITCHON_BTN.replace("${userName}", "james"));
			}
			
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

			manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_SUSPENDED, SOCIAL_NETWORKS_PASSWORD);
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
			*Step Name: Step 4: Enter valid eXo password
			*Step Description: 
				- Enter valid password of eXo account but eXo account is disabled
				- Click "Confirm" button
			*Input Data: 
				
			*Expected Outcome: 
				- Error message "This user is disabled" is displayed.*/ 
			type(manageLoginOut.ELEMENT_NEW_ACCOUNT_PASSWORD, DATA_PASS, true);
			waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CONFIRM);
			click(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CONFIRM);
			waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_ERROR_MESSAGE.replace("${message}", "This user is suspended"));
	 	}

		/**
		*<li> Case ID:128367.</li>
		*<li> Test Case Name: Check successfully authentication on Existing Account Detected via new account.</li>
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
		public  void test15_CheckSuccessfullyAuthenticationOnExistingAccountDetectedViaNewAccount() {
			info("Test 15 Check successfully authentication on Existing Account Detected via new account");
			//Change user's email
			manageLoginOut.signIn(DATA_USER1, DATA_PASS);
			navigationToolbar.goToCommunityManagement();
			userAndGroup.goToEditUserInfo(DATA_USER2);
			userAndGroup.editUserInfo_AccountTab(null, null, null, EMAIL_ADDRESS1);
			
			manageLoginOut.signOut();
			
			String name = "test exoservice";
			String firstName = "test";
			String lastName = "exoservice";
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
			manageLoginOut.loginWithGoogleAccount(EMAIL_ADDRESS1, SOCIAL_NETWORKS_PASSWORD);
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
			waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_REGISTER);
			
			/*Step number: 4
			*Step Name: Step 4: Click "Create New Account" button
			*Step Description: 
				- Click "Register New Account" button on Existing Account Detected form
			*Input Data: 
				
			*Expected Outcome: 
				- "Register New Account" form is displayed.*/
			click(manageLoginOut.ELEMENT_DETECT_ACCOUNT_REGISTER);
			waitForAndGetElement(manageLoginOut.ELEMENT_NEW_ACCOUNT_POPUP);
			
			/*Step number: 5
			*Step Name: Step 5: Enter valid information
			*Step Description: 
				- Enter valid information for all fields
				- Click "Subscribe and Login" button
			*Input Data: 
				
			*Expected Outcome: 
				- The account is created
				- The user is authenticated to eXo platform successfully
				- A link is established between the eXo Platform user account and the social network account.*/

			type(manageLoginOut.ELEMENT_NEW_ACCOUNT_PASSWORD, SOCIAL_NETWORKS_PASSWORD, true);
			type(manageLoginOut.ELEMENT_NEW_ACCOUNT_PASSWORD_CONFIRM, SOCIAL_NETWORKS_PASSWORD, true);
			type(manageLoginOut.ELEMENT_NEW_ACCOUNT_EMAIL, SOCIAL_NETWORKS_ACCOUNT_LINKEDIN, true);
			
			click(manageLoginOut.ELEMENT_NEW_ACCOUNT_SUBSCRIBE_AND_LOGIN_BUTTON);
			waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
			
			info("Check link established");
			navigationToolbar.goToMySettings();
			userAndGroup.checkLinkedSocialAccount(userAndGroup.ELEMENT_USER_SOCIAL_NETWORKS_TAB_GOOGLE_ACCOUNT, 
				"${account}", EMAIL_ADDRESS1.substring(0,EMAIL_ADDRESS1.indexOf('@')));
			button.close();
			
			/*Step number: 6
			*Step Name: Step 6: Check information in My profile page
			*Step Description: 
				- Choose User menu -> My profile
			*Input Data: 
				
			*Expected Outcome: 
				- The information of new created eXo account at step 5 (First Name, Last Name, Email) is displayed*/

			info("Go to My Profile");
			navigationToolbar.goToMyProfile();
			
			//Check email
			waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",SOCIAL_NETWORKS_ACCOUNT_LINKEDIN));
			//Check name
			waitForAndGetElement(myProfile.ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", name));
			//Check avatar
			waitForAndGetElement(myProfile.ELEMENT_USER_AVATAR);		
			
			/*Step number: 7
			*Step Name: Step 7: Check information in Account profile page
			*Step Description: 
				- Choose User menu 
				-
				-> Settings 
				-
				-> Open Account profile tab
			*Input Data: 
				
			*Expected Outcome: 
				- The information of new created eXo account at step 5 (User Name, First Name, Last Name, Display Name, Email) is displayed*/ 

			navigationToolbar.goToMySettings();
			waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_FIRST_NAME.replace("${value}", firstName));
			waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_LAST_NAME.replace("${value}", lastName));
			waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_DISPLAY_NAME.replace("${value}", name));
			waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_EMAIL.replace("${value}", SOCIAL_NETWORKS_ACCOUNT_GOOGLE));
		}		
}