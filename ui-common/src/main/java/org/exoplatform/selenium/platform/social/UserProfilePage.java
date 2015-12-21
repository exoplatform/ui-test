package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
public class UserProfilePage extends SocialLocator {
	
	/**
	 * constructor
	 * @param dr
	 */
	public UserProfilePage(WebDriver dr){
		this.driver=dr;
	}

	/**
	 * Update Current position
	 * By QuynhPT
	 * @param pos
	 */
	public void updateCurrentPosition(String pos){
		info("Update Current Position");
		if(pos !="" && pos !=null){
			waitForAndGetElement(ELEMENT_EDIT_POSITION);
			click(ELEMENT_EDIT_POSITION);
			Utils.pause(2000);
			click(ELEMENT_EDIT_POSITION_SAVE_BUTTON);
			Utils.pause(2000);
		}
	}

	/**
	 * function: Go to Edit profile
	 */
	public void goToEditProfile(){
		info("Go to edit profile");
		click(ELEMENT_EDIT_MY_PROFILE_BUTTON);
		waitForAndGetElement(ELEMENT_EDIT_PROFILE_FORM);
	}

	/**
	 * Update About me section
	 * @param pos
	 */
	public void updateAboutMe(String pos){
		info("Update About me");
		if(pos !=""){
			type(ELEMENT_ABOUTME_TEXTAREA_EDIT, pos, true);
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
		info("Update basic information");
		scrollToBottomPage(this.driver);
		if(waitForAndGetElement(ELEMENT_EDIT_BASIC_INFORMATION,5000,0)!=null){
			click(ELEMENT_EDIT_BASIC_INFORMATION);
		}
		if (firstName !="" && firstName!=null){
			info("update firstname");
			type(ELEMENT_FIRST_NAME_TEXTBOX_EDIT, firstName, true);
		}
		if (lastName !="" && lastName!=null){
			info("update lastName");
			type(ELEMENT_LAST_NAME_TEXTBOX_EDIT, lastName, true);
		}
		if (email !="" && email!=null){
			info("update email");
			type(ELEMENT_EMAIL_TEXTBOX_EDIT, email, true);
		}
	} 

	/**
	 * Change avatar
	 * @param linkfile : File path of new avatar
	 */
	public void changeAvatar(String linkfile){
		info("-- changeAvatar --");
		Utils.pause(2000);
		if(browser.contains("iexplorer"))
		   clickByJavascript(ELEMENT_CHANGE_AVATAR_LINK);
		else
		   click(ELEMENT_CHANGE_AVATAR_LINK);
		click(ELEMENT_SELECT_AVATAR);
		uploadFileUsingRobot(linkfile);
		clickByJavascript(ELEMENT_CONFIRM);
		waitForElementNotPresent(ELEMENT_CONFIRM);
		clickByJavascript(ELEMENT_SAVE_AVATAR);
		Utils.pause(1000);
	}

	/**
	 * Update information of contact of a user
	 * @param gender
	 * @param job
	 */
	public void updateGenderJob(String gender, String job) {
		scrollToBottomPage(this.driver);
		if(waitForAndGetElement(ELEMENT_CONTACT_EDIT_ICON,5000,0)!=null)
			click(ELEMENT_CONTACT_EDIT_ICON);
		if(gender !="" && gender!=null){
			info("update gender");
			select(ELEMENT_CONTACT_GENDER_SELECTION, gender);
		}
		if(job !="" && job!=null){
			info("update job");
			type(ELEMENT_CONTACT_JOB_TITLE,job,true);
		}
		
	}

	/**
	 * Update ims
	 * @param type
	 * @param ims
	 * @param opParams
	 */
	public void updateIms(String type, String ims, Object... opParams){
		info("Update ims");
		scrollToBottomPage(this.driver);
		String index = (String) (opParams.length > 0 ? opParams[0]: "0");
		Integer xpathCount= getElements(ELEMENT_CONTACT_IMS_INPUT_LIST).size();
		if(Integer.valueOf(index)>=xpathCount){
			click(ELEMENT_CONTACT_IMS_ADD_ICON);
		}
		if(type!=null && !type.isEmpty()){
			info("select type of ims");
			select(ELEMENT_CONTACT_IMS_OPTION.replace("${index}", index),type);
		}
		if(ims!=null && !ims.isEmpty()){
			info("update ims "+ims);
			type(ELEMENT_CONTACT_IMS_INPUT.replace("${index}", index),ims,true);
		}
	}

	/**
	 * 
	 * @param url
	 * @param opParams
	 */
	public void updateUrl(String url, Object... opParams){
		scrollToBottomPage(this.driver);
		String index = (String) (opParams.length > 0 ? opParams[0]: "0");
		Integer xpathCount= getElements(ELEMENT_CONTACT_URL_INPUT_LIST).size();
		if(Integer.valueOf(index)>=xpathCount){
			click(ELEMENT_CONTACT_URL_ADD_ICON);
		}
		if(url!=null && !url.isEmpty()){
			info("update url");
			WebElement input= waitForAndGetElement(By.xpath(ELEMENT_CONTACT_URL_INPUT.replace("${index}", index)), DEFAULT_TIMEOUT, 1);
			Actions action =new Actions(driver);
			action.moveToElement(input).click().perform();
			action.sendKeys(url).perform();
			//action.moveToElement(input).sendKeys(url).build().perform();
			//type(ELEMENT_CONTACT_URL_INPUT.replace("${index}", index),url,true);
		}
	}

	/**
	 * Update phone
	 * @param type
	 * @param phone
	 * @param opParams
	 */
	public void updatePhone(String type,String phone, Object... opParams){
		info("Update phone");
		scrollToBottomPage(this.driver);
		String index = (String) (opParams.length > 0 ? opParams[0]: "1");
		Integer xpathCount= getElements(ELEMENT_CONTACT_PHONE_INPUT_LIST).size();
		if(Integer.valueOf(index)>=xpathCount){
			click(ELEMENT_CONTACT_PHONE_ADD_ICON);
		}
		if(type!=null && !type.isEmpty()){
			info("select type of phone");
			select(ELEMENT_CONTACT_PHONE_OPTION.replace("${index}", index),type);
		}
		if(phone!=null && !phone.isEmpty()){
			info("update phone");
			type(ELEMENT_CONTACT_PHONE_INPUT.replace("${index}", index),phone,true);
		}
	}

	/**
	 * update experience 
	 * @param organization
	 * @param jobTitle
	 * @param jobDetail
	 * @param skill
	 * @param startDate
	 * @param endDate
	 * @param curPos
	 * @param opParams
	 */
	public void updateExperience(String organization,String jobTitle,String jobDetail, 
			String skill, String startDate, String endDate, Boolean curPos, Object... opParams){
		String index = (String) (opParams.length > 0 ? opParams[0]: "0");	
		Integer xpathCount= getElements(ELEMENT_EXPERIENCE_LIST).size();
		if(Integer.valueOf(index)>=xpathCount){
			click(ELEMENT_ADD_MORE_EXP_ICON,0,true);
		}
		info("-- update experience --");
		if(organization!=null && organization != ""){
			type(ELEMENT_EXPERIENCE_COMPANY_INPUT.replace("${index}", index),organization, true);
		}
		if(jobTitle!=null && jobTitle != ""){
			type(ELEMENT_EXPERIENCE_POSITION_INPUT.replace("${index}", index),jobTitle, true);
		}
		if(jobDetail!=null && jobDetail != ""){
			type(ELEMENT_EXPERIENCE_DESCRIPTION_INPUT.replace("${index}", index),jobDetail, true);
		}
		if(skill!=null && skill != ""){
			type(ELEMENT_EXPERIENCE_SKILL_INPUT.replace("${index}", index),skill, true);
		}
		if(startDate!=null && startDate != ""){
			type(ELEMENT_EXPERIENCE_START_DATE_INPUT.replace("${index}", index),startDate, true);
		}
		if(endDate!=null && endDate != ""){
			type(ELEMENT_EXPERIENCE_END_DATE_INPUT.replace("${index}", index),endDate, true);
		}
		if(curPos!=null && curPos){
			check(ELEMENT_EXPERIENCE_CURRENT_CHECKBOX.replace("${index}", index),2);
		}
		else{
			uncheck(ELEMENT_EXPERIENCE_CURRENT_CHECKBOX.replace("${index}", index),2);
		}
	} 

	/**
	 * Save or cancle update info
	 * @param isSave 
	 * 				null or true: save updating
	 * 				false: cancel
	 */
	public void saveCancelUpdateInfo(Boolean isSave){
		scrollToBottomPage(this.driver);
		if(isSave==null || isSave){
			info("Save updating information");
			Utils.pause(2000);
			clickByJavascript(ELEMENT_CONTACT_SAVE_BUTTON, 2);
			Utils.pause(2000);	
		}
		else{
			info("Cancel updating information");
			Utils.pause(2000);
			clickByJavascript(ELEMENT_CONTACT_CANCEL_BUTTON, 2);
			Utils.pause(2000);	
		}	
	}
	/**
	 * Open Activity tab
	 */
	public void goToActivity(){
		info("Click on Activity tab");
		click(ELEMETN_ACTIVITY_TAB);
		Utils.pause(3000);
	}
	/**
	 * Connect in profile page
	 * @param user
	 */
	public void connectToUserInProfilePage(String user){
		info("connect to: "+user);
		driver.get(baseUrl+"/intranet/profile/"+user);
		click(ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS,0,true);
		waitForAndGetElement(ELEMENT_UIMINICONNECTIONS_PORLET_CANCEL_STATUS);
		waitForElementNotPresent(ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS);
	}
	/**
	 * Disconnect in profile page
	 * @param user
	 */
	public void disconnectInProfilePage(String user){
		info("disconnect: "+user);
		driver.get(baseUrl+"/intranet/profile/"+user);
		mouseOver(ELEMENT_UIMINICONNECTIONS_PORLET_CONNECTED_STATUS,true);
		waitForAndGetElement(ELEMENT_UIMINICONNECTIONS_PORLET_DISCONNECTED_STATUS).click();
		waitForAndGetElement(ELEMENT_UIMINICONNECTIONS_PORLET_CONNECT_STATUS);
		waitForElementNotPresent(ELEMENT_UIMINICONNECTIONS_PORLET_CONNECTED_STATUS);
	}
}
