package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.locator.ManageLogInOutLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManageLogInOut extends ManageLogInOutLocator {
	
	ManageAlert magAlert;
	public ManageLogInOut(WebDriver dr){
		driver = dr;
		magAlert = new ManageAlert(driver);
	}
	public enum SSO{
		OPENAM,CAS;
	}
	/**
	 * Log in to intranet
	 * @param username
	 * @param password
	 * @param opParams
	 */
	public void signIn(String username, String password,Boolean...opParams) {
		Boolean verify = (Boolean) (opParams.length > 0 ? opParams[0]: false);
		if(waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK,5000,0)!=null){
			signOut();
		}
		if(ssoType != "" && ssoType != null){
			SSO sso = SSO.valueOf(ssoType.toUpperCase());
			switch(sso){
			case OPENAM:
				info("login by openam with user " + username + " and pass " + password);
				signInOpenam(username, password);
				break;
			case CAS:
			    info("Login by cas with user " + username + " and pass " + password);
			    signInCas(username,password);
			    break;
			}
			if(waitForAndGetElement(ELEMENT_REGISTER_SKIP_BUTTON,3000,0,2)!=null){
				info("-- Skipping register account--");
				click(ELEMENT_REGISTER_SKIP_BUTTON);
				waitForElementNotPresent(ELEMENT_REGISTER_SKIP_BUTTON);
			}
		}
		else{
			info("login normally if not use SSO with user " + username + " and pass " + password);
			type(ELEMENT_INPUT_USERNAME, username, true);
			type(ELEMENT_INPUT_PASSWORD, password, true);
			clickByJavascript(ELEMENT_SIGN_IN_BUTTON, 2);
			if(verify)
				waitForElementNotPresent(ELEMENT_SIGN_IN_BUTTON);
		}
	}
	/**
	 * Log in via OpenAM
	 * @param username
	 * @param password
	 */
    public void signInOpenam(String username, String password){
    	type(ELEMENT_INPUT_USERNAME_OPENAM, username, true);
		type(ELEMENT_INPUT_PASSWORD_OPENAM, password, true);
		click(ELEMENT_SIGN_IN_BUTTON_OPENAM);
		Utils.pause(3000);
		//waitForElementNotPresent(ELEMENT_SIGN_IN_BUTTON_OPENAM,3000);
		
    }
    /**
     * Log in via CAS
     * @param username
     * @param password
     */
    public void signInCas(String username, String password){
    	this.driver.get(baseUrl);
    	type(ELEMENT_INPUT_USERNAME_CAS, username, true);
		type(ELEMENT_INPUT_PASSWORD_CAS, password, true);
		click(ELEMENT_SIGN_IN_BUTTON_CAS);
		Utils.pause(3000);
		//waitForElementNotPresent(ELEMENT_SIGN_IN_BUTTON_CAS,3000);
		
    }
	/**
	 * Sign out from intranet
	 */
	public void signOut(){
		
		info("Sign out");
		for(int repeat=0;; repeat ++){
			if (repeat > 1){
				mouseOverAndClick(ELEMENT_ACCOUNT_NAME_LINK);
				break;
			}
			click(ELEMENT_ACCOUNT_NAME_LINK);
			if (waitForAndGetElement(ELEMENT_SIGN_OUT_LINK, 5000, 0) != null){
				info("Element " + ELEMENT_SIGN_OUT_LINK + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
			driver.navigate().refresh();
		}
		click(ELEMENT_SIGN_OUT_LINK);
		if(waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK,2000,0)!=null){
			info("Clear cache and reconnect to the package");
			driver.manage().deleteAllCookies();
			driver.get(baseUrl);
		}
			
		Utils.pause(3000);
		if ( ExpectedConditions.alertIsPresent() != null ){
			magAlert = new ManageAlert(driver);
			magAlert.acceptAlert();
		}
		WebElement logOutSucess = waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK, 3000, 0);
		if (logOutSucess != null){
			info("Because issue: in jboss, logout then come back homepage, we have to close IE and init the new one");
			driver.manage().deleteAllCookies();
			driver.get(baseUrl);
		}
		else{
			info("Logout sucessfully");
		}
	}
	/**
	 * Sign in as disable user
	 * @param user
	 * @param pass
	 */
	public void signInAsDisableUser(String user,String pass){
		type(ELEMENT_INPUT_USERNAME, user, true);
		type(ELEMENT_INPUT_PASSWORD, pass, true);
		click(ELEMENT_SIGN_IN_BUTTON,0,true);
		waitForAndGetElement(ELEMENT_DISABLE_USER_ERROR_MES,2000,1);
		waitForAndGetElement(ELEMENT_SIGN_IN_BUTTON);
	}
	
	/**
	 * Log in to intranet
	 * @param username
	 * @param password
	 * @param opParams
	 */
	public void signInWithoutRefresh(String username, String password,Boolean...opParams) {
		Boolean verify = (Boolean) (opParams.length > 0 ? opParams[0]: false);
		if(waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK,5000,0)!=null){
			signOut();
		}
		if(ssoType != "" && ssoType != null){
			SSO sso = SSO.valueOf(ssoType.toUpperCase());
			switch(sso){
			case OPENAM:
				info("login by openam with user " + username + " and pass " + password);
				signInOpenam(username, password);
				break;
			case CAS:
			    info("Login by cas with user " + username + " and pass " + password);
			    signInCas(username,password);
			    break;
			}
		}
		else{
			info("login normally if not use SSO with user " + username + " and pass " + password);
			type(ELEMENT_INPUT_USERNAME, username, true);
			type(ELEMENT_INPUT_PASSWORD, password, true);
			click(ELEMENT_SIGN_IN_BUTTON);
			if(verify)
				waitForElementNotPresent(ELEMENT_SIGN_IN_BUTTON);
		}
		Utils.pause(3000);
	}	
	 
	/** Check Login screen with social login buttons
	 * By: QuyenNT
	 * Date: Nov 24, 2015	
	 */
	public void checkSocialLoginButtons(){		
		waitForAndGetElement(ELEMENT_SIGN_IN_FACEBOOK_BUTTON);
		waitForAndGetElement(ELEMENT_SIGN_IN_TWITTER_BUTTON);
		waitForAndGetElement(ELEMENT_SIGN_IN_LINKEDIN_BUTTON);
		waitForAndGetElement(ELEMENT_SIGN_IN_GOOGLE_BUTTON);		
	}
	
	/**
	 * Go to facebook login form and check elements
	 * By: QuyenNT
	 * Date: Nov 25, 2015
	 */
	public void checkFacebookLoginForm(){
		click(ELEMENT_SIGN_IN_FACEBOOK_BUTTON);
		waitForAndGetElement(ELEMENT_FACEBOOK_LOGIN_FORM_LOGO);
		waitForAndGetElement(ELEMENT_FACEBOOK_LOGIN_FORM_USERNAME);
		waitForAndGetElement(ELEMENT_FACEBOOK_LOGIN_FORM_PASSWORD);
		waitForAndGetElement(ELEMENT_FACEBOOK_LOGIN_FORM_LOGIN_BUTTON);		
	}
	
	
	/**
	 * Go to TWITTER login form and check elements
	 * By: QuyenNT
	 * Date: Nov 25, 2015
	 */
	public void checkTwitterLoginForm(){
		click(ELEMENT_SIGN_IN_TWITTER_BUTTON);
		waitForAndGetElement(ELEMENT_TWITTER_LOGIN_FORM_LOGO);
		waitForAndGetElement(ELEMENT_TWITTER_LOGIN_FORM_USERNAME);
		waitForAndGetElement(ELEMENT_TWITTER_LOGIN_FORM_PASSWORD);
		waitForAndGetElement(ELEMENT_TWITTER_LOGIN_FORM_LOGIN_BUTTON);		
	}		
	
	/**
	 * Go to LINKEDIN login form and check elements
	 * By: QuyenNT
	 * Date: Nov 25, 2015
	 */
	public void checkLinkedinLoginForm(){
		click(ELEMENT_SIGN_IN_LINKEDIN_BUTTON);
		waitForAndGetElement(ELEMENT_LINKEDIN_LOGIN_FORM_LOGO);
		waitForAndGetElement(ELEMENT_LINKEDIN_LOGIN_FORM_USERNAME);
		waitForAndGetElement(ELEMENT_LINKEDIN_LOGIN_FORM_PASSWORD);
		waitForAndGetElement(ELEMENT_LINKEDIN_LOGIN_FORM_LOGIN_BUTTON);		
	}
	
	/**
	 * Go to GOOGLE login form and check elements
	 * By: QuyenNT
	 * Date: Nov 25, 2015
	 */
	public void checkGoogleLoginForm(){
		click(ELEMENT_SIGN_IN_GOOGLE_BUTTON);
		waitForAndGetElement(ELEMENT_GOOGLE_LOGIN_FORM_LOGO);
		waitForAndGetElement(ELEMENT_GOOGLE_LOGIN_FORM_USERNAME);
		waitForAndGetElement(ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON);	
	}
	
	/**
	 * Login platform with facebook account
	 * By: QuyenNT
	 * Date: Nov 25, 2015
	 */
	public void loginWithFacebookAccount(String userName, String password){
		info("login with facebook account:" + userName + " and pass " + password);
		waitForAndGetElement(ELEMENT_FACEBOOK_LOGIN_FORM_USERNAME);
		
		type(ELEMENT_FACEBOOK_LOGIN_FORM_USERNAME, userName, true);
		type(ELEMENT_FACEBOOK_LOGIN_FORM_PASSWORD, password, true);
		Utils.pause(3000);
		click(ELEMENT_FACEBOOK_LOGIN_FORM_LOGIN_BUTTON);
	}
	
	/**
	 * Login platform with twitter account
	 * By: QuyenNT
	 * Date: Nov 25, 2015
	 */
	public void loginWithTwitterAccount(String userName, String password){
		info("login with twitter account:" + userName + " and pass " + password);
		waitForAndGetElement(ELEMENT_TWITTER_LOGIN_FORM_USERNAME);
		click(ELEMENT_TWITTER_LOGIN_FORM_USERNAME);
		
		type(ELEMENT_TWITTER_LOGIN_FORM_USERNAME, userName, false);		
		type(ELEMENT_TWITTER_LOGIN_FORM_PASSWORD, password, false);
	
		click(ELEMENT_TWITTER_LOGIN_FORM_LOGIN_BUTTON);		
	}	
	
	/**
	 * Login platform with Linkedin account
	 * By: QuyenNT
	 * Date: Nov 25, 2015
	 */
	public void loginWithLinkedinAccount(String userName, String password){
		info("login with Linkedin account:" + userName + " and pass " + password);
		waitForAndGetElement(ELEMENT_LINKEDIN_LOGIN_FORM_USERNAME);
		
		type(ELEMENT_LINKEDIN_LOGIN_FORM_USERNAME, userName, true);
		type(ELEMENT_LINKEDIN_LOGIN_FORM_PASSWORD, password, true);
		click(ELEMENT_LINKEDIN_LOGIN_FORM_LOGIN_BUTTON);		
	}	
	
	/**
	 * Login platform with Google account
	 * By: QuyenNT
	 * Date: Nov 25, 2015
	 */
	public void loginWithGoogleAccount(String userName, String password){
		info("login with Google account:" + userName + " and pass " + password);
		waitForAndGetElement(ELEMENT_GOOGLE_LOGIN_FORM_USERNAME);
		
		type(ELEMENT_GOOGLE_LOGIN_FORM_USERNAME, userName, true);
		waitForAndGetElement(ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON);
		click(ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON);
		
		type(ELEMENT_GOOGLE_LOGIN_FORM_PASSWORD, password, true);		
		click(ELEMENT_GOOGLE_LOGIN_FORM_LOGIN_BUTTON);		
	}		

}
