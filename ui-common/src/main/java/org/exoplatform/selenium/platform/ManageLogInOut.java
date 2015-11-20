package org.exoplatform.selenium.platform;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.exoplatform.selenium.TestLogger.info;

public class ManageLogInOut extends PlatformBase {
	
	public final By ELEMENT_SIGN_IN_BUTTON = By.xpath("//*[@class='loginButton']/*");
	public final By ELEMENT_SIGN_OUT_LINK = By.className("uiIconPLFLogout");
	public final By ELEMENT_DISABLE_USER_ERROR_MES = By.xpath("//*[@class='signinFail'][contains(.,'This user account has been disabled. If you think this is an error, please contact the administrator.')]");
	
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
		}
		else{
			info("login normally if not use SSO with user " + username + " and pass " + password);
			type(ELEMENT_INPUT_USERNAME, username, true);
			type(ELEMENT_INPUT_PASSWORD, password, true);
			click(ELEMENT_SIGN_IN_BUTTON,0,true);
			if(verify)
				waitForElementNotPresent(ELEMENT_SIGN_IN_BUTTON);
		}
		//Utils.pause(3000);
		//driver.navigate().refresh();
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
			clickByJavascript(ELEMENT_ACCOUNT_NAME_LINK);
			if (waitForAndGetElement(ELEMENT_SIGN_OUT_LINK, 5000, 0) != null){
				info("Element " + ELEMENT_SIGN_OUT_LINK + "... is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
			driver.navigate().refresh();
		}
		click(ELEMENT_SIGN_OUT_LINK);
		Utils.pause(3000);
		if ( ExpectedConditions.alertIsPresent() != null ){
			magAlert = new ManageAlert(driver);
			magAlert.acceptAlert();
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
}
