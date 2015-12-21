package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.WebDriver;

public class UserPageBase extends SocialLocator {
	
	
	/**
	 * constructor
	 * @param dr
	 */
	public UserPageBase(WebDriver dr){
		this.driver=dr;
	}
	
	
	/**
	 * Go to my wiki
	 */
	public void goToProfileTab(){
		info("Go to profile tab");
		click(ELEMENT_HORIZONTAL_TOOLBAR_FIRST_APP_PROFILE);
	}
	
	/**
	 * Go to my wiki
	 */
	public void goToWikiTab(){
		info("Go to my wiki");
		click(ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI);
	}
	
	/**
	 * Go to my activity tab
	 */
	public void goToActivityTab(){
		info("Go to activity tab");
		click(ELEMENT_HORIZONTAL_TOOLBAR_SECOND_APP_ACTIVITIES);
	}
	
	/**
	 * Go to my activity tab
	 */
	public void goToConnectionTab(){
		info("Go to activity tab");
		click(ELEMENT_HORIZONTAL_TOOLBAR_THIRD_APP_CONNECTIONS);
	}
	
	/**
	 * Go to my activity tab
	 */
	public void goToDashboardTab(){
		info("Go to dashboard tab");
		click(ELEMENT_HORIZONTAL_TOOLBAR_FIFTH_APP_DASHBOARD);
	}
}
