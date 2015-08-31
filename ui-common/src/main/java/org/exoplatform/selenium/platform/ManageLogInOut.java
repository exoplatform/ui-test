package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManageLogInOut extends PlatformBase {
	
	public final By ELEMENT_SIGN_IN_BUTTON = By.xpath("//*[@class='loginButton']/*");
	public final By ELEMENT_SIGN_OUT_LINK = By.className("uiIconPLFLogout");
	public final By ELEMENT_DISABLE_USER_ERROR_MES = By.xpath("//*[@class='signinFail'][contains(.,'This user account has been disabled. If you think this is an error, please contact the administrator.')]");
	
	public final By ELEMENT_SIGN_IN_FACEBOOK_BUTTON = By.xpath(".//*[@id='login-FACEBOOK']/i");
	public final By ELEMENT_SIGN_IN_TWITTER_BUTTON = By.xpath(".//*[@id='login-TWITTER']/i");
	public final By ELEMENT_SIGN_IN_LINKEDIN_BUTTON = By.xpath(".//*[@id='login-LINKEDIN']/i");
	public final By ELEMENT_SIGN_IN_GOOGLE_BUTTON = By.xpath(".//*[@id='login-GOOGLE']/i");
	
	public final By ELEMENT_ACME_LOGIN_LINK= By.xpath(".//*[@id='AcmeWebSiteLogInLogOut']");
	public final By ELEMENT_ACME_REGISTER_LINK= By.xpath(".//*[@id='AcmeWebSiteRegister']/a");
	public final By ELEMENT_ACME_REGISTER_NEW_ACCOUNT= By.xpath(".//*[@class = 'title' and contains(text(),'Register New Account' )]");
	public final By ELEMENT_ACME_REGISTER_NEW_ACCOUNT_FACEBOOK = By.xpath(".//*[@class='register-button register-FACEBOOK']");
	public final By ELEMENT_ACME_SIGN_IN_BUTTON = By.xpath(".//*[@id='UIPortalComponentLogin']/div[3]/input[1]");
	
	public final String ELEMENT_USER_STATUS_SWITCHON_BTN = ".//*[@id='UIListUsersGird']//*[@title='${userName}']/../..//*[@class='switchBtnLabelOn']";
	public final By ELEMENT_ACCOUNT_LINK_TO_SOCIAL_FACEBOOK = By.xpath(".//*[contains(text(), 'Facebook User Name')]/..//*[contains(text(), 'Link social account')]");
	public final By ELEMENT_ACCOUNT_LINK_TO_SOCIAL_GOOGLE = By.xpath(".//*[contains(text(), 'Google+ User Name')]/..//*[contains(text(), 'Link social account')]");
	public final By ELEMENT_ACCOUNT_LINK_TO_SOCIAL_LINKEDIN = By.xpath(".//*[contains(text(), 'LinkedIn User Name')]/..//*[contains(text(), 'Link social account')]");
	public final String ELEMENT_ACCOUNT_LINK_TO_SOCIAL_INFO = ".//*[contains(text(), 'Social network \"$network}\" connected for user \"$account}\"')]";
	
	//unlink
	public final By ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_TWITTER = By.xpath("//*[contains(text(), 'Twitter User Name')]/..//*[contains(text(), 'Unlink social account')]");
	public final By ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_GOOGLE = By.xpath("//*[contains(text(), 'Google+ User Name')]/..//*[contains(text(), 'Unlink social account')]");
	public final By ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_FACEBOOK = By.xpath("//*[contains(text(), 'Facebook User Name')]/..//*[contains(text(), 'Unlink social account')]");
	public final By ELEMENT_USER_SOCIAL_NETWORKS_TAB_UNLINK_LINKEDIN = By.xpath("//*[contains(text(), 'LinkedIn User Name')]/..//*[contains(text(), 'Unlink social account')]");

	//Facebook login form
	public final By ELEMENT_FACEBOOK_LOGIN_FORM_LOGO = By.xpath(".//*[@href='https://www.facebook.com/']");
	public final By ELEMENT_FACEBOOK_LOGIN_FORM_USERNAME = By.xpath(".//*[@id='email']");
	public final By ELEMENT_FACEBOOK_LOGIN_FORM_PASSWORD = By.xpath(".//*[@id='pass']");
	public final By ELEMENT_FACEBOOK_LOGIN_FORM_LOGIN_BUTTON = By.xpath(".//*[@id='loginbutton']");
	public final By ELEMENT_FACEBOOK_LOGIN_FORM_CONTINUE_BUTTON = By.xpath(".//*[@id='platformDialogForm']//*[text()='Ok']");
	public final By ELEMENT_FACEBOOK_LOGIN_FORM_CANCEL_BUTTON = By.xpath(".//*[@id='platformDialogForm']/div[2]/table/tbody/tr/td[2]/button[1]");
	public final By ELEMENT_FACEBOOK_LOGIN_FORM_ACCEPT_BUTTON = By.xpath(".//*[@id='platformDialogForm']/div[2]/table/tbody/tr/td[2]/button[2]");
	
	//Twitter login form
	public final By ELEMENT_TWITTER_LOGIN_FORM_LOGO = By.xpath(".//*[@href='https://twitter.com/home']");
	public final By ELEMENT_TWITTER_LOGIN_FORM_USERNAME = By.xpath(".//*[@id='oauth_form']//*[contains(text(), 'Username or email')]");
	public final By ELEMENT_TWITTER_LOGIN_FORM_PASSWORD = By.xpath(".//*[@id='oauth_form']//*[contains(text(), 'Password')]");
	public final By ELEMENT_TWITTER_LOGIN_FORM_LOGIN_BUTTON = By.xpath(".//*[@id='allow']");
	public final By ELEMENT_TWITTER_LOGIN_FORM_CANCEL_BUTTON = By.xpath(".//*[@id='cancel']");
	
	//Linkedin login form
	public final By ELEMENT_LINKEDIN_LOGIN_FORM_LOGO = By.xpath(".//*[@class='logo' and text()='LinkedIn']");
	public final By ELEMENT_LINKEDIN_LOGIN_FORM_USERNAME = By.xpath(".//*[@id='session_key-oauthAuthorizeForm' and @placeholder='Email']");
	public final By ELEMENT_LINKEDIN_LOGIN_FORM_PASSWORD = By.xpath(".//*[@id='session_password-oauthAuthorizeForm' and @placeholder='Password']");
	public final By ELEMENT_LINKEDIN_LOGIN_FORM_LOGIN_BUTTON = By.xpath(".//input[@value='Allow access' and @name='authorize']");

	
	//Google login form
	public final By ELEMENT_GOOGLE_LOGIN_FORM_LOGO = By.xpath("//*[contains(text(),'Sign in with your Google Account')]");
	public final By ELEMENT_GOOGLE_LOGIN_FORM_USERNAME = By.xpath(".//*[@id='Email']");
	public final By ELEMENT_GOOGLE_LOGIN_FORM_PASSWORD = By.xpath(".//*[@id='Passwd' and @placeholder='Password']");
	public final By ELEMENT_GOOGLE_LOGIN_FORM_NEXT_BUTTON = By.xpath(".//*[@id='next']");
	public final By ELEMENT_GOOGLE_LOGIN_FORM_LOGIN_BUTTON = By.xpath(".//*[@id='signIn']");

	
	public final By ELEMENT_GOOGLE_PERMISSION_FORM_ALLOW = By.xpath(".//*[@id='submit_approve_access']");
	public final By ELEMENT_GOOGLE_PERMISSION_FORM_DENY = By.xpath(".//*[@id='submit_deny_access']");
	
	//Register new account form
	public final By ELEMENT_NEW_ACCOUNT_PASSWORD = By.xpath("//*[@name = 'password']");
	public final By ELEMENT_NEW_ACCOUNT_PASSWORD_CONFIRM = By.xpath("//*[@name = 'password2']");
	public final By ELEMENT_NEW_ACCOUNT_FIRST_NAME = By.xpath("//*[@name = 'firstName']");
	public final By ELEMENT_NEW_ACCOUNT_LAST_NAME = By.xpath("//*[@name = 'lastName']");
	public final By ELEMENT_NEW_ACCOUNT_DISPLAY_NAME = By.xpath("//*[@name = 'displayName']");
	public final By ELEMENT_NEW_ACCOUNT_EMAIL = By.xpath("//*[@name = 'email']");
	
	public final String ELEMENT_NEW_ACCOUNT_VALUE_USERNAME = "//*[@name = 'username' and @value='${value}']";
	public final String ELEMENT_NEW_ACCOUNT_VALUE_FIRSTNAME = "//*[@name = 'firstName' and @value='${value}']";
	public final String ELEMENT_NEW_ACCOUNT_VALUE_DISPLAYNAME = "//*[@name = 'displayName' and @value='${value}']";
	public final String ELEMENT_NEW_ACCOUNT_VALUE_EMAIL = "//*[@name = 'email' and @value='${value}']";
		
	public final By ELEMENT_NEW_ACCOUNT_POPUP = By.xpath("//*[@class='popupTitle center' and contains(text(), 'Register New Account')]");
	public final By ELEMENT_NEW_ACCOUNT_SUBSCRIBE_AND_LOGIN_BUTTON = By.xpath(".//*[@id='UIPortalLoginFormAction']//*[contains(text(), 'Subscribe and Login')]");
	public final By ELEMENT_DETECT_ACCOUNT_POPUP = By.xpath("//*[@class='PopupTitle popupTitle' and text() = 'Existing Account Detected']");
	public final By ELEMENT_DETECT_ACCOUNT_CONFIRM = By.xpath("//*[@class='uiAction uiActionBorder']//*[contains(text(), 'Confirm')]");
	public final By ELEMENT_DETECT_ACCOUNT_REGISTER = By.xpath("//*[@class='uiAction uiActionBorder']//*[contains(text(), 'Register New Account')]");
	public final String ELEMENT_DETECT_ACCOUNT_ERROR_MESSAGE = "//*[@class='uiIconColorError']/../..//*[contains(text(), '${message}')]";
	public final String ELEMENT_DETECT_ACCOUNT_MESSAGE = "//*[contains(text(),'We have detected that an eXo account already exists for')]//*[contains(text(),'${detectedName}')]/../..//*[contains(text(),'If you would like to use it, please enter your eXo password to confirm')]";
	public final By ELEMENT_DETECT_ACCOUNT_CLOSE_BTN = By.xpath("//*[@class='uiIconClose pull-right']");

	//Account value
	public final String ELEMENT_ACCOUNT_PROFILE_FIRST_NAME = ".//*[@id='firstName' and @value='${value}']";
	public final String ELEMENT_ACCOUNT_PROFILE_LAST_NAME = ".//*[@id='lastName' and @value='${value}']";
	public final String ELEMENT_ACCOUNT_PROFILE_DISPLAY_NAME = ".//*[@id='displayName' and @value='${value}']";
	public final String ELEMENT_ACCOUNT_PROFILE_EMAIL = ".//*[@id='email' and @value='${value}']";
	public final String ELEMENT_ACCOUNT_PROFILE_USERNAME = ".//*[@id='username' and @value='${value}']";
	public final String ELEMENT_ACCOUNT_PROFILE_EMAIL_ACME = ".//*[@id='emailAddress' and @value='${value}']";
	
	public final String ELEMENT_SOCIAL_ACCOUNT_LOGGEDIN = "//*[@title = 'Google Account: ${acount}']";


	
	
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
		clickByJavascript(ELEMENT_SIGN_OUT_LINK,2);
		if(browser.contains("iexplorer")){
			if(waitForAndGetElement(ELEMENT_ACCOUNT_NAME_LINK,2000,0)!=null){
				info("Clear cache and reconnect to the package");
				driver.manage().deleteAllCookies();
				driver.get(baseUrl);
			}
			
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
