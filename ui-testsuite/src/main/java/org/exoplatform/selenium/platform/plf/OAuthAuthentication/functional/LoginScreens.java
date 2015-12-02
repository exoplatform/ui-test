package org.exoplatform.selenium.platform.plf.OAuthAuthentication.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class LoginScreens extends PlatformBase {
		ManageLogInOut manageLoginOut;
		HomePagePlatform homepage;
		NavigationToolbar navigationToolbar;
		
		UserAndGroupManagement userAndGroup;
		Button button;
		
		@BeforeMethod
		public void setupBeforeMethod() throws Exception{
			info("Start setUpBeforeMethod");
			
			initSeleniumTest();

			manageLoginOut = new ManageLogInOut(driver);
		
			homepage = new HomePagePlatform(driver);
			navigationToolbar = new NavigationToolbar(driver);
			userAndGroup = new UserAndGroupManagement(driver);	
			button = new Button(driver);
		}		
		
		@AfterMethod
	    public void afterMethod(){
			driver.manage().deleteAllCookies();
			driver.quit();
	    }	
		
	/**
	*<li> Case ID:128288.</li>
	*<li> Test Case Name: Check sign-in buttons in acme login screen.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test01_CheckSigninButtonsInAcmeLoginScreen() {
		info("Test 1: Check sign-in buttons in acme login screen");
		/*Step Number: 1
		*Step Name: Step 1: Open Login page
		*Step Description: 
			- Open the acme page (portal/acme).
			- Click Login.
		*Input Data: 
			
		*Expected Outcome: 
			- The Login screen is displayed 
			- 4 sign up buttons are displayed : Facebook, Google, LinkedIn, Twitter*/
		driver.get(baseUrl+"/acme");
		Utils.pause(3000);
		click(manageLoginOut.ELEMENT_ACME_LOGIN_LINK);
		
		info("Check buttons");
		waitForAndGetElement(manageLoginOut.ELEMENT_ACME_SIGN_IN_BUTTON);
		manageLoginOut.checkSocialLoginButtons();
		
		/*Step number: 2
		*Step Name: Step 2: Mouse over buttons
		*Step Description: 
			- Move the mouse over each button (Facebook, Google, LinkedIn, Twitter)
		*Input Data: 
			
		*Expected Outcome: 
			- The button is displayed with a highlighting effect*/ 
		info("Check highlight effect");
		mouseOver(manageLoginOut.ELEMENT_SIGN_IN_FACEBOOK_BUTTON, true);

		String css = waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_FACEBOOK_BUTTON,2000,0).getCssValue("background-image");
		if(css.contains("oauthIconHover.png")){
			assert true;
		} else {	
			assert false: "facebook is not highlighted";
		}
		
		//Check twitter
		mouseOver(manageLoginOut.ELEMENT_SIGN_IN_TWITTER_BUTTON, true);

		css = waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_TWITTER_BUTTON,2000,0).getCssValue("background-image");
		if(css.contains("oauthIconHover.png")){
			assert true;
		} else {	
			assert false: "twitter is not highlighted";
		}
		
		//Check linkedin
		mouseOver(manageLoginOut.ELEMENT_SIGN_IN_LINKEDIN_BUTTON, true);

		css = waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_LINKEDIN_BUTTON,2000,0).getCssValue("background-image");
		if(css.contains("oauthIconHover.png")){
			assert true;
		} else {	
			assert false: "Linkedin is not highlighted";
		}
		//Check Google
		mouseOver(manageLoginOut.ELEMENT_SIGN_IN_GOOGLE_BUTTON, true);

		css = waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_GOOGLE_BUTTON,2000,0).getCssValue("background-image");
		if(css.contains("oauthIconHover.png")){
			assert true;
		} else {	
			assert false: "Goolge is not highlighted";
		}
 	}

	/**
	*<li> Case ID:128289.</li>
	*<li> Test Case Name: Check sign-in buttons in portal login screen.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckSigninButtonsInPortalLoginScreen() {
		info("Test 2: Check sign-in buttons in portal login screen");
		/*Step Number: 1
		*Step Name: Step 1: Open Login page
		*Step Description: 
			- Open Login page (/portal/login)
		*Input Data: 
			
		*Expected Outcome: 
			- Login screen is displayed 
			- 4 sign up buttons are displayed : Facebook, Google, LinkedIn, Twitter*/
		waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_BUTTON);
		manageLoginOut.checkSocialLoginButtons();
		
		
		/*Step number: 2
		*Step Name: Step 2: Mouse over on buttons
		*Step Description: 
			- Move the mouse over each button (Facebook, Google, LinkedIn, Twitter)
		*Input Data: 
			
		*Expected Outcome: 
			- The button is displayed with a highlighting effect*/ 
		info("Check highlight effect");
		mouseOver(manageLoginOut.ELEMENT_SIGN_IN_FACEBOOK_BUTTON, true);

		String css = waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_FACEBOOK_BUTTON,2000,0).getCssValue("background-image");
		if(css.contains("oauthIconHover.png")){
			assert true;
		} else {	
			assert false: "facebook is not highlighted";
		}
		
		//Check twitter
		mouseOver(manageLoginOut.ELEMENT_SIGN_IN_TWITTER_BUTTON, true);

		css = waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_TWITTER_BUTTON,2000,0).getCssValue("background-image");
		if(css.contains("oauthIconHover.png")){
			assert true;
		} else {	
			assert false: "twitter is not highlighted";
		}
		
		//Check linkedin
		mouseOver(manageLoginOut.ELEMENT_SIGN_IN_LINKEDIN_BUTTON, true);

		css = waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_LINKEDIN_BUTTON,2000,0).getCssValue("background-image");
		if(css.contains("oauthIconHover.png")){
			assert true;
		} else {	
			assert false: "Linkedin is not highlighted";
		}
		//Check Google
		mouseOver(manageLoginOut.ELEMENT_SIGN_IN_GOOGLE_BUTTON, true);

		css = waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_GOOGLE_BUTTON,2000,0).getCssValue("background-image");
		if(css.contains("oauthIconHover.png")){
			assert true;
		} else {	
			assert false: "Goolge is not highlighted";
		}
		
 	}

	/**
	*<li> Case ID:128290.</li>
	*<li> Test Case Name: Check buttons of social network after add/delete of provider.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	//@Test
	public  void test03_CheckButtonsOfSocialNetworkAfterAdddeleteOfProvider() {
		info("Test 3: Check buttons of social network after add/delete of provider");
		/*Step Number: 1
		*Step Name: Step 1: Open Login screen
		*Step Description: 
			- Connect to portal
		*Input Data: 
			
		*Expected Outcome: 
			- Login screen is displayed 
			- 4 sign up buttons are displayed: Facebook, Google, LinkedIn, Twitter*/
	
		
		/*Step number: 2
		*Step Name: Step 2: Deactivate providers
		*Step Description: 
			- Deactivate providers from the file exo.properties
			- Open Login screen
		*Input Data: 
			
		*Expected Outcome: 
			- The button related to provider is not displayed*/

		/*Step number: 3
		*Step Name: Step 3: Activate providers
		*Step Description: 
			- Activate providers from the file configuration.properties
			- Open Login screen
		*Input Data: 
			
		*Expected Outcome: 
			- The button related to provider is displayed*/ 

 	}


	/**
	*<li> Case ID:128291.</li>
	*<li> Test Case Name: Check sign-in to exo platform with a social network login form.</li>
	*<li> Pre-Condition: -
	- User has an established link to a social network AND an eXo account
	-
	- Make an user has an established link to a social network as following:
	- Login with an user with an eXo account
	- Click on the Display Name of user on administration bar 
	-
	-> Choose Settings 
	-
	-> Open Social Network tab: Click on "Link to social account" of coresponding provider (Facebook, Google, LinkedIn, Twitter)</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckSigninToExoPlatformWithASocialNetworkLoginForm() {
		info("Test 4: Check sign-in to exo platform with a social network login form");
		
		//Pre-condition
		manageLoginOut.signIn("fqa", PASS_ROOT);
		navigationToolbar.goToMySettings();
		waitForAndGetElement(userAndGroup.ELEMENT_USER_SOCIAL_NETWORKS_TAB);
		click(userAndGroup.ELEMENT_USER_SOCIAL_NETWORKS_TAB);
		
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_LINK_TO_SOCIAL_GOOGLE);
		click(manageLoginOut.ELEMENT_ACCOUNT_LINK_TO_SOCIAL_GOOGLE);
		
		manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_GOOGLE, SOCIAL_NETWORKS_PASSWORD);
		Utils.pause(3000);
		
		click(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
		
		
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_LINK_TO_SOCIAL_INFO.replace("$network}", "Google+")
																				.replace("$account}", "fqa"));
		button.ok();
		button.close();
		
		manageLoginOut.signOut();
				
		afterMethod();
		try {
			setupBeforeMethod();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Step Number: 1
		*Step Name: Step 1: Open Login page
		*Step Description: 
			- Open Login page (accessible from /portal/login)
		*Input Data: 
			
		*Expected Outcome: 
			- The Login screen is displayed 
			- 4 sign up buttons are displayed : Facebook, Google, LinkedIn, Twitter*/
		info("Check social buttons");
		waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_BUTTON);
		manageLoginOut.checkSocialLoginButtons();
		
		/*Step number: 2
		*Step Name: Step 2: Choose a social network
		*Step Description: 
			- Click on social network button
		*Input Data: 
			
		*Expected Outcome: 
			- Login page of the chosen social network is displayed*/
	
		
		info("Check twitter login form");
		manageLoginOut.checkTwitterLoginForm();
		
		info("Back to the platform login page");
		driver.navigate().back();
		Utils.pause(2000);
		
		info("Check linkedin login form");
		manageLoginOut.checkLinkedinLoginForm();
		
		info("Back to the platform login page");
		driver.navigate().back();
		Utils.pause(2000);
		
		info("Check facebook login form");
		manageLoginOut.checkFacebookLoginForm();
			
		info("Back to the platform login page");
		driver.navigate().back();
		
		info("Check google login form");
		manageLoginOut.checkGoogleLoginForm();	
		
		/*Step number: 3
		*Step Name: Step 3: Input user credentials
		*Step Description: 
			- Input user credentials (social account)
		*Input Data: 
			
		*Expected Outcome: 
			- The user is signed in exoplatform with social account*/ 
		info("check login with Facebook account");
		manageLoginOut.loginWithGoogleAccount(SOCIAL_NETWORKS_ACCOUNT_GOOGLE, SOCIAL_NETWORKS_PASSWORD);
		Utils.pause(3000);
		click(manageLoginOut.ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW);
		Utils.pause(3000);
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);					
 	}

	/**
	*<li> Case ID:128292.</li>
	*<li> Test Case Name: Check sign in to exo with exo signin form.</li>
	*<li> Pre-Condition: -
	- User has an established link to a social network AND an eXo account
	-
	- Make an user has an established link to a social network as following:
	- Login with an user with an eXo account
	- Click on the Display Name of user on administration bar 
	-
	-> Choose Settings 
	-
	-> Open Social Network tab: Click on "Link to social account" of coresponding provider (Facebook, Google, LinkedIn, Twitter)</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckSignInToExoWithExoSigninForm() {
		info("Test 5: Check sign in to exo with exo signin form");
		//Pre-condition
		manageLoginOut.signIn("fqa", PASS_ROOT);
		navigationToolbar.goToMySettings();
		waitForAndGetElement(userAndGroup.ELEMENT_USER_SOCIAL_NETWORKS_TAB);
		click(userAndGroup.ELEMENT_USER_SOCIAL_NETWORKS_TAB);
		
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_LINK_TO_SOCIAL_LINKEDIN);
		click(manageLoginOut.ELEMENT_ACCOUNT_LINK_TO_SOCIAL_LINKEDIN);
		
		manageLoginOut.loginWithLinkedinAccount(SOCIAL_NETWORKS_ACCOUNT_LINKEDIN, SOCIAL_NETWORKS_PASSWORD);
		Utils.pause(3000);
		waitForAndGetElement(manageLoginOut.ELEMENT_ACCOUNT_LINK_TO_SOCIAL_INFO.replace("$network}", "LinkedIn")
																	.replace("$account}", "fqa"));
		button.ok();
		button.close();
		
		manageLoginOut.signOut();
		
		/*Step Number: 1
		*Step Name: Step 1: Open Login screen
		*Step Description: 
			- Open Login page (accessible from /portal/login)
		*Input Data: 
			
		*Expected Outcome: 
			- The Login screen is displayed 
			- 4 sign up buttons are displayed : Facebook, Google, LinkedIn, Twitter*/
		waitForAndGetElement(manageLoginOut.ELEMENT_SIGN_IN_BUTTON);
		manageLoginOut.checkSocialLoginButtons();
		
		/*Step number: 2
		*Step Name: Step 2: Sign in with eXo account
		*Step Description: 
			- Input user credentials (eXo account) in exoplatform sign in form
		*Input Data: 
			
		*Expected Outcome: 
			- The user is signed in eXo platform*/ 
		
		manageLoginOut.signIn("fqa", PASS_ROOT);
		Utils.pause(3000);	
		waitForAndGetElement(homepage.ELEMENT_PLF_HOMEPAGE_DISPLAY);	
 	}
	
	}