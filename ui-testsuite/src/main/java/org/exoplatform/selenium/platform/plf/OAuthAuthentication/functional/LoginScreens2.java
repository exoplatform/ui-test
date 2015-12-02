package org.exoplatform.selenium.platform.plf.OAuthAuthentication.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


	public class LoginScreens2 extends PlatformBase {
		ManageLogInOut manageLoginOut;
	
		
		@BeforeMethod
		public void setupBeforeMethod() throws Exception{
			info("Start setUpBeforeMethod");
			
			initSeleniumTest();

			manageLoginOut = new ManageLogInOut(driver);		
		}		
		
		@AfterMethod
	    public void afterMethod(){
			driver.manage().deleteAllCookies();
			driver.quit();
	    }	
		

	/**
	*<li> Case ID:128290.</li>
	*<li> Test Case Name: Check buttons of social network after add/delete of provider.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckButtonsOfSocialNetworkAfterAdddeleteOfProvider() {
		info("Test 3: Check buttons of social network after add/delete of provider");
		
		//Step 1 & 3 was checked in LoginScreens class
		
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
		
		waitForElementNotPresent(manageLoginOut.ELEMENT_SIGN_IN_FACEBOOK_BUTTON);
		waitForElementNotPresent(manageLoginOut.ELEMENT_SIGN_IN_TWITTER_BUTTON);
		waitForElementNotPresent(manageLoginOut.ELEMENT_SIGN_IN_LINKEDIN_BUTTON);
		waitForElementNotPresent(manageLoginOut.ELEMENT_SIGN_IN_GOOGLE_BUTTON);	
		
		/*Step number: 3
		*Step Name: Step 3: Activate providers
		*Step Description: 
			- Activate providers from the file configuration.properties
			- Open Login screen
		*Input Data: 
			
		*Expected Outcome: 
			- The button related to provider is displayed*/ 

 	}

}