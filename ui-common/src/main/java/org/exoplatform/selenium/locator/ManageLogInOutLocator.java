package org.exoplatform.selenium.locator;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class ManageLogInOutLocator extends PlatformBase {
	
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


}
