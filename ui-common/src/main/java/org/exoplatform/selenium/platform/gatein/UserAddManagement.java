package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class UserAddManagement extends GateinLocator {
	
	UserAndGroupManagement userAndGroupManage;
	Dialog dialog;
	ManageAlert alert;
	Button button;
	public UserAddManagement(WebDriver dr){
		driver = dr;
		button = new Button(driver, this.plfVersion);
	} 
	
	
	/**
	 * Add en user on the plf
	 * @param userName
	 * @param Password
	 * @param email
	 * @param Firstname
	 * @param lastName
	 */
	public void addUser(String userName, String Password, String email, String Firstname, String lastName){
		info("Add an user");
		Utils.pause(3000);
		waitForAndGetElement(ELEMENT_USERNAME, 60000, 0);
		type(ELEMENT_USERNAME,userName,true);
		type(ELEMENT_PASSWORD,Password,true);
		type(ELEMENT_CONFIRM_PASSWORD,Password,true);
		type(ELEMENT_EMAIL,email,true);
		type(ELEMENT_FIRSTNAME,Firstname,true);
		type(ELEMENT_LASTNAME,lastName,true);
		click(ELEMENT_SAVE_ADD_USER);
		Utils.pause(3000);
		waitForMessage(ELEMENT_MSG_CREATE_ACCOUNT);
		click(ELEMENT_CLOSE_MESSAGE);
		info("Finish adding an user");
	}
	/**
	 * Add many users at the same time
	 * @param number
	 *              is the number of user that wants to create
	 * @param password
	 * @return array
	 */
	public  ArrayList<String> addUsers(int number,String password){
		ArrayList<String> array = new ArrayList<String>();
		for(int i=0;i<number;i++){
			info("Add new a user");
			String user=getRandomString();
			String email=user+"@gmail.com";
			addUser(user,password, email,user,user);
			info("Add users to user's array");
			array.add(user);
			info("User"+i+": "+user);
		}
		return  array;
	}

	
	/**
	 * function: check content of mail then delete mail
	 * @param title title of the page
	 * @object if true check it's present, false check if it's not present
	 */
	public void checkEmailNotification(String title,Object... opParams){
		info("Check and delete mail");
		Boolean checkOrNo = (Boolean)(opParams.length > 0 ? opParams[0]: true);
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		  for(String windowHandle  : driver.getWindowHandles()){
			     driver.switchTo().window(windowHandle);
			     info("driver.title:"+driver.getTitle());
		}
		if(checkOrNo==true){
			waitForAndGetElement(ELEMENT_GMAIL_CONTENT.replace("${title}",title),30000,1);
		}else{
			waitForElementNotPresent(ELEMENT_GMAIL_CONTENT.replace("${title}",title),30000,1);
		}		
	}
	
	/**
	 * function: changePasswordFor User
	 * @param currentPass Current password of user
	 * @param newPass new Password of User
	 * @param confirmNewPass confirm new pass word of User
	 */
	public void changePassWord(String currentPass, String newPass, String confirmNewPass){
		info("Change password");
		waitForAndGetElement(ELEMENT_CHANGE_PASSWORD_LINK, 2000, 0);
		click(ELEMENT_CHANGE_PASSWORD_LINK);
		if (currentPass != null && currentPass!="")
			type(ELEMENT_CURRENT_PASSWOR, currentPass, true);
		if (newPass != null && newPass!="")
			type(ELEMENT_NEW_PASSWORD, newPass, true);
		if (confirmNewPass != null && confirmNewPass!="")
			type(ELEMENT_CONFIRM_NEW_PASSWORD, confirmNewPass, true);
		click(ELEMENT_SAVE_PASSWORD);
		waitForMessage(ELEMENT_MSG_CHANGE_PASS_WORD);
		click(ELEMENT_CLOSE_MESSAGE);
		button.close();
		waitForElementNotPresent(button.ELEMENT_CLOSE_BUTTON);
	}
}
