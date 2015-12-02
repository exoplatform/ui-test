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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class SignInFlow extends PlatformBase{
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
	*<li> Case ID:128293.</li>
	*<li> Test Case Name: Check the OAuth flow when eXo has not access to the Social Network.</li>
	*<li> Pre-Condition: User A has not authorized eXo to access his user data in the social network</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckTheOAuthFlowWhenEXoHasNotAccessToTheSocialNetwork() {
		info("Test 1: Check the OAuth flow when eXo has not access to the Social Network");
		
		/*Step Number: 1
		*Step Name: Step 1: Sign in with a social network
		*Step Description: 
			- Open Login page (accessible from /portal/login)
			- Sign in with a social network with User A
		*Input Data: 
			
		*Expected Outcome: 
			- User A is redirected to the chosen social network*/ 
		info("Check facebook");
		manageLoginOut.checkFacebookLoginForm();
		waitForAndGetElement(manageLoginOut.ELEMENT_FACEBOOK_LOGIN_FORM_LOGIN_BUTTON);
		
		info("Back to the platform login page");
		driver.navigate().back();
		
		info("Check twitter");
		manageLoginOut.checkTwitterLoginForm();
		waitForAndGetElement(manageLoginOut.ELEMENT_TWITTER_LOGIN_FORM_LOGIN_BUTTON);
		
		info("Back to the platform login page");
		driver.navigate().back();
		
		info("Check linkedin");
		manageLoginOut.checkLinkedinLoginForm();
		waitForAndGetElement(manageLoginOut.ELEMENT_LINKEDIN_LOGIN_FORM_LOGIN_BUTTON);
		
		info("Back to the platform login page");
		driver.navigate().back();
		
		info("Check G+");
		manageLoginOut.checkGoogleLoginForm();
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON);
 	}


	/**
	*<li> Case ID:128300.</li>
	*<li> Test Case Name: Check the registration NOT on the fly after user has granted authorization.</li>
	*<li> Pre-Condition: -
	- By default, sign up on fly mode is not activated for Twitter provider 
	(If want to change the setting for sign up on fly mode, we can setting in exo.properties file)
	-
	- Twitter account has not authorized eXo to access his user data in the social network
	-
	- Twitter account is not currently logged in the social network</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckTheRegistrationNOTOnTheFlyAfterUserHasGrantedAuthorization() {
		info("Test 5: Check the registration NOT on the fly after user has granted authorization");
			
		String name = "exofqa-twitt";
		/*Step Number: 1
		*Step Name: Step 1: Open Login page
		*Step Description: 
			- Login page (accessible from /portal/login)
			- Choose Sign in with a provider that is not actived sign up on fly mode (For example: Twitter)
		*Input Data: 
			
		*Expected Outcome: 
			- OAuth authorization page is displayed*/
		manageLoginOut.checkTwitterLoginForm();
	
		/*Step number: 2
		*Step Name: Step 2: Sign in on OAuth authorization page
		*Step Description: 
			- On OAuth authorization page: click Sign in button
			- Enter valid value for Username and Password fields
			- Click Sign in button
		*Input Data: 
			
		*Expected Outcome: 
			- Registration New Account form is displayed
			- Registration New Account form is pre-filled with information extracted from the social network 
			(Username,First Name, Last Name, Email)*/
		
		manageLoginOut.loginWithTwitterAccount(SOCIAL_NETWORKS_ACCOUNT_TWITTER, SOCIAL_NETWORKS_PASSWORD);
		Utils.pause(3000);
		waitForAndGetElement(manageLoginOut.ELEMENT_NEW_ACCOUNT_POPUP);
		waitForAndGetElement(manageLoginOut.ELEMENT_NEW_ACCOUNT_VALUE_USERNAME.replace("${value}", "exofqa"));
		waitForAndGetElement(manageLoginOut.ELEMENT_NEW_ACCOUNT_VALUE_FIRSTNAME.replace("${value}", "exofqa_twitt"));
		waitForAndGetElement(manageLoginOut.ELEMENT_NEW_ACCOUNT_VALUE_DISPLAYNAME.replace("${value}", "exofqa_twitt"));
		
		/*Step number: 3
		*Step Name: Step 3: Register a new account
		*Step Description: 
			- Enter valid value for other fields (Password, Confirm Password,Display Name)
			- Click "Subscribe and Login" button
		*Input Data: 
			
		*Expected Outcome: 
			- A new account is created automatically using user attributes from the linked social network
			- The user is authenticated and redirected to the eXo page he initially requested.*/

		type(manageLoginOut.ELEMENT_NEW_ACCOUNT_PASSWORD, SOCIAL_NETWORKS_PASSWORD, true);
		type(manageLoginOut.ELEMENT_NEW_ACCOUNT_PASSWORD_CONFIRM, SOCIAL_NETWORKS_PASSWORD, true);
		type(manageLoginOut.ELEMENT_NEW_ACCOUNT_FIRST_NAME, name, true);
		type(manageLoginOut.ELEMENT_NEW_ACCOUNT_LAST_NAME, name, true);
		type(manageLoginOut.ELEMENT_NEW_ACCOUNT_DISPLAY_NAME, name, true);
		type(manageLoginOut.ELEMENT_NEW_ACCOUNT_EMAIL, SOCIAL_NETWORKS_ACCOUNT_TWITTER, true);
		
		click(manageLoginOut.ELEMENT_NEW_ACCOUNT_SUBSCRIBE_AND_LOGIN_BUTTON);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		/*Step number: 4
		*Step Name: Step 4: Check information in My profile page
		*Step Description: 
			- Choose User menu -> My profile
		*Input Data: 
			
		*Expected Outcome: 
			- Information of registered account (First Name, Last Name, Email, Avatar) 
			is displayed in My profile page*/
		info("Go to My Profile");
		navigationToolbar.goToMyProfile();
		
		//Check email
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",SOCIAL_NETWORKS_ACCOUNT_TWITTER));
		//Check name
		waitForAndGetElement(myProfile.ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", name));
		//Check avatar
		waitForAndGetElement(myProfile.ELEMENT_USER_AVATAR);		
		
		/*Step number: 5
		*Step Name: Step 5: Check information in Account profile page
		*Step Description: 
			- Choose User menu -> Settings-> Open Account profile tab
		*Input Data: 
			
		*Expected Outcome: 
			- Information of registered account (User Name, First Name, Last Name, Display Name, Email)
			 is displayed in Account profile page*/ 
		navigationToolbar.goToMySettings();
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_FIRST_NAME.replace("${value}", name));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_LAST_NAME.replace("${value}", name));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_DISPLAY_NAME.replace("${value}", name));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_EMAIL.replace("${value}", SOCIAL_NETWORKS_ACCOUNT_TWITTER));
		
		//Unlink
		navigationToolbar.goToMySettings();
		userAndGroup.unLinkedSocialAccount(manageLoginOut.ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_TWITTER);
		button.ok();
		button.close();
 	}


	/**
	*<li> Case ID:128346.</li>
	*<li> Test Case Name: Check the registration on the fly in case 
	*								the social network account is not linked to any eXo user account.</li>
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
	- Delete cookies before test</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckTheRegistrationOnTheFlyInCaseTheSocialNetworkAccountIsNotLinkedToAnyEXoUserAccount() {
		info("Test 7: Check the registration on the fly in case the social network account is not linked to any eXo user account");
			
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
		
		info("check login with Google account");
		manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_GOOGLE, SOCIAL_NETWORKS_PASSWORD);
		
		info("Check OAuth authorization screen");
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_DENY);
		
		/*Step number: 3
		*Step Name: Step 3: Sign in on OAuth authorization page
		*Step Description: 
			- On OAuth authorization page: click Accept button
		*Input Data: 
			
		*Expected Outcome: 
			- The user is authenticated and redirected to eXo.*/
		Utils.pause(3000);
		click(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);
		
		/*Step number: 4
		*Step Name: Step 4: Check information in My profile page
		*Step Description: 
			- Go to My profile of User
		*Input Data: 
			
		*Expected Outcome: 
			- The providers should extract the following user profile attributes from the social network and display them in "My profile" of user+ First Name+ Last Name+ Email*/
		info("Go to My Profile");
		navigationToolbar.goToMyProfile();
		
		//Check email
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",SOCIAL_NETWORKS_ACCOUNT_GOOGLE));
		//Check name
		waitForAndGetElement(myProfile.ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", "exofqagplus fqaplus"));
		//Check avatar
		waitForAndGetElement(myProfile.ELEMENT_USER_AVATAR);	
		/*Step number: 5
		*Step Name: Step 5: Check information in "Account profile" page
		*Step Description: 
			- Choose User menu -> Settings
			-> Open Account profile tab
		*Input Data: 
			
		*Expected Outcome: 
			- The providers should extract the following user profile attributes from the social network and display them in "My profile" of user+ User Name+ First Name+ Last Name+ Display Name+ Email*/ 
		navigationToolbar.goToMySettings();
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_FIRST_NAME.replace("${value}", "exofqagplus"));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_LAST_NAME.replace("${value}", "fqaplus"));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_DISPLAY_NAME.replace("${value}", "exofqagplus fqaplus"));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_EMAIL.replace("${value}", SOCIAL_NETWORKS_ACCOUNT_GOOGLE));
 	
		//Unlink
		userAndGroup.unLinkedSocialAccount(manageLoginOut.ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_GOOGLE);
		button.ok();
		button.close();
	}

	/**
	*<li> Case ID:128348.</li>
	*<li> Test Case Name: Check successfully authentication on Existing Account Detected form via 
	*									using eXo password.</li>
	*<li> Pre-Condition: -
	- By default, sign up on fly mode is activated for FACEBOOK, GOOGLE, LINKEDIN providers 
	(If want to change the setting for sign up on fly mode, we can setting in exo.properties file)
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
	public  void test08_CheckSuccessfullyAuthenticationOnExistingAccountDetectedFormViaUsingEXoPassword() {
		info("Test 8: Check successfully authentication on Existing Account Detected form via using eXo password");
		
		String firstName = "Mary";
		String lastName = "Williams";
		String name = "Mary Williams";
		
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
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW, 2000,1);
		waitForAndGetElement(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_DENY, 2000,1);
		
		/*Step number: 3
		*Step Name: Step 3: Open "Existing Account Detected" form
		*Step Description: 
			- On OAuth authorization page: click Accept button
		*Input Data: 
			
		*Expected Outcome: 
			- Existing Account Detected form is displayed*/
		
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
		
		/*Step number: 4
		*Step Name: Step 4: Enter valid password
		*Step Description: 
			- Enter valid password of eXo account
			- Click "Confirm" button
		*Input Data: 
			
		*Expected Outcome: 
			- The user is authenticated to eXo platform successfully
			- A link is established between the eXo Platform user account and the social network account.*/

		type(manageLoginOut.ELEMENT_NEW_ACCOUNT_PASSWORD, DATA_PASS, true);
		waitForAndGetElement(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CONFIRM);
		click(manageLoginOut.ELEMENT_DETECT_ACCOUNT_CONFIRM);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
		
		info("Check link established");
		navigationToolbar.goToMySettings();
		userAndGroup.checkLinkedSocialAccount(userAndGroup.ELEMENT_USER_SOCIAL_NETWORKS_TAB_GOOGLE_ACCOUNT, 
			"${account}", SOCIAL_NETWORKS_ACCOUNT_LINKEDIN.substring(0,SOCIAL_NETWORKS_ACCOUNT_LINKEDIN.indexOf('@')));
		button.close();
		
		/*Step number: 5
		*Step Name: Step 5: Check information in My profile page
		*Step Description: 
			- Choose User menu 
			-
			-> My profile
		*Input Data: 
			
		*Expected Outcome: 
			- The information of eXo account (First Name, Last Name, Email, Avatar) are kept*/

		info("Go to My Profile");
		navigationToolbar.goToMyProfile();
		
		//Check email
		waitForAndGetElement(myProfile.ELEMENT_EMAIL_INFO.replace("${email}",SOCIAL_NETWORKS_ACCOUNT_LINKEDIN));
		//Check name
		waitForAndGetElement(myProfile.ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", "Mary Williams"));
		//Check avatar
		waitForAndGetElement(myProfile.ELEMENT_USER_AVATAR);		
		
		/*Step number: 6
		*Step Name: Step 6: Check information in Account profile page
		*Step Description: 
			- Choose User menu 
			-
			-> Settings 
			-
			-> Open Account Profile tab
		*Input Data: 
			
		*Expected Outcome: 
			- The information of eXo account (Username, First Name, Last Name, Display Name, Email) are kept*/ 
		navigationToolbar.goToMySettings();
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_FIRST_NAME.replace("${value}", firstName));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_LAST_NAME.replace("${value}", lastName));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_DISPLAY_NAME.replace("${value}", name));
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_PROFILE_EMAIL.replace("${value}", SOCIAL_NETWORKS_ACCOUNT_LINKEDIN));
		
		//Unlink
		userAndGroup.unLinkedSocialAccount(manageLoginOut.ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_GOOGLE);
		button.ok();
		button.close();
	}
}