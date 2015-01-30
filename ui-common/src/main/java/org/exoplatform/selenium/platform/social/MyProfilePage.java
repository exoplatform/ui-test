package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * @author quynhpt
 * date 21/01/2015
 */
public class MyProfilePage extends PlatformBase {
	//Navigation tabs
	public final By ELEMENT_MY_PROFILE_TAB = By.xpath("//*[@class='nav nav-tabs userNavigation']//*[@class='uiIconAppprofile uiIconDefaultApp']");

	//Current position
	public final By ELEMENT_EDIT_POSITION = By.xpath("//*[@id='UIHeaderSection']//*[@class='uiIconEdit']");
	public final By ELEMENT_POSITION_TEXTBOX_EDIT = By.id("position");
	public final By ELEMENT_EDIT_POSITION_SAVE_BUTTON = By.id("savePosition");
	
	//Basic information
	public final By ELEMENT_EDIT_BASIC_INFORMATION = By.xpath("//*[@id='UIBasicInfoSection']//*[@class='uiIconEdit']");
	public final By ELEMENT_FIRST_NAME_TEXTBOX_EDIT = By.id("firstName");
	public final By ELEMENT_LAST_NAME_TEXTBOX_EDIT = By.id("lastName");
	public final By ELEMENT_EMAIL_TEXTBOX_EDIT = By.id("email");
	public final By ELEMENT_EDIT_BASIC_INFO_SAVE_BUTTON = By.xpath("//*[@id='UIBasicInfoSection']//button[contains(text(), 'Save')]");
	
	/**
	 * constructor
	 * @param dr
	 */
	public MyProfilePage(WebDriver dr){
		this.driver=dr;
	}
	
	/**
	 * Update Current position
	 * By QuynhPT
	 * @param pos
	 */
    public void updateCurrentPosition(String pos){
    	info("Update Current Position");
    	if(pos !=""){
    		waitForAndGetElement(ELEMENT_EDIT_POSITION);
    	    click(ELEMENT_EDIT_POSITION);
    	    Utils.pause(2000);
    	    click(ELEMENT_EDIT_POSITION_SAVE_BUTTON);
    	    Utils.pause(2000);
    	}
    }
    
	/**
	 * Update Basic information
	 * By QuynhPT
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public void updateBasicInformation(String firstName,String lastName,String email){
		if(firstName !="" || lastName !="" || email !=""){
			waitForAndGetElement(ELEMENT_EDIT_BASIC_INFORMATION);
			click(ELEMENT_EDIT_BASIC_INFORMATION);
			if (firstName !="")
				type(ELEMENT_FIRST_NAME_TEXTBOX_EDIT, firstName, true);
			if (lastName !="")
				type(ELEMENT_LAST_NAME_TEXTBOX_EDIT, lastName, true);
			if (email !="")
				type(ELEMENT_EMAIL_TEXTBOX_EDIT, email, true);
			click(ELEMENT_EDIT_BASIC_INFO_SAVE_BUTTON);
			Utils.pause(2000);
		}
	} 
}