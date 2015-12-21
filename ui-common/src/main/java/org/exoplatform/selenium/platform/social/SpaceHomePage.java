package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.openqa.selenium.WebDriver;

public class SpaceHomePage extends SocialLocator{
	
	SpaceSettingManagement setSpaceMg;
	ManageLogInOut magAc;
	/**
	 * constructor
	 * @param dr
	 */
	public SpaceHomePage(WebDriver dr){
		this.driver=dr;
		magAc = new ManageLogInOut(driver);
	}
	
	/**
	 * Open Space setting page
	 */
	public void goToSpaceSettingTab(){
		info("--Open Setting tab of the space");
		info("Click on the tab");
		Utils.pause(2000);
		if (waitForAndGetElement(ELEMENT_SPACE_SPACE_SETTINGS, 5000, 0) == null){
			click(ELEMENT_SPACE_MENU_MORE, 2);
			clickByJavascript(ELEMENT_SPACE_SPACE_SETTINGS);
		}
		else
			clickByJavascript(ELEMENT_SPACE_SPACE_SETTINGS);
		Utils.pause(3000);
		waitForAndGetElement(ELEMENT_SPACE_SPACE_SETTINGS_TITLE,3000,1);
		info("Space setting page is shown");
	}
	/**
	 * Open Wiki portlet of space
	 */
	public void goToWikiTab(){
		info("--Open Wiki tab of the space");
		info("Click on the tab");
		waitForAndGetElement(ELEMENT_SPACE_WIKI_TAB,3000,0).click();
		info("wiki page is shown");
	}

	/**
	 * Open a space from left menu
	 * @param name
	 */
	public void goToSpace(String name){
		info("Go to the Space:"+name);
		waitForAndGetElement(ELEMENT_SPACE_LEFT_MENU_SPACE_NAME.replace("${name}",name),2000,0).click();
		waitForAndGetElement(ELEMENT_SPACE_NAME.replace("${name}",name),2000,0);
		info("The space is shown");
	}
	/**
	 * Verify that the space is shown 
	 * @param name
	 *             is the name of space
	 */
	public void verifyTitleSpace(String name){
		info("Verify that the space is shown");
		waitForAndGetElement(ELEMENT_SPACE_NAME.replace("${name}",name));
		
	}
	/**
	 * Open space in All spaces
	 * @param name
	 */
	public void openSpace(String name,boolean hasPerm){
		info("open space in All spaces");
		waitForAndGetElement(ELEMENT_SPACE_TITLE.replace("${space}", name)).click();
		if(hasPerm)
			waitForAndGetElement(ELEMENT_SPACE_NAME.replace("${name}",name));
		else
			waitForElementNotPresent(ELEMENT_SPACE_NAME.replace("${name}",name));
	}
	/**
	 * Open more mentu
	 */
	public void goToMore(){
		info("Click more link on the navigation");
		if(waitForAndGetElement(ELEMENT_SPACE_MENU_MORE,2000,0)!=null){
			click(ELEMENT_SPACE_MENU_MORE);
			waitForAndGetElement(ELEMENT_MEMBER_TAB,3000,1);
			info("List menu is shown");
		}
	}
	/**
	 * Open Dashboard portlet
	 */
	public void goToDashBoard(){
		if(waitForAndGetElement(ELEMENT_SPACE_MENU_DASHBOARD,2000,0)==null){
			goToMore();
		}
		info("Click on Dash board link");
		click(ELEMENT_SPACE_MENU_DASHBOARD);
		waitForAndGetElement(ELEMENT_MYDASH_BTN_ADDGADGET,3000,1);
		info("Dashboard is shown");
	}
}