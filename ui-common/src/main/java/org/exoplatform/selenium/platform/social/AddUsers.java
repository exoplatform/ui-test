package org.exoplatform.selenium.platform.social;
import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebDriver;

public class AddUsers extends SocialLocator{

	public AddUsers(WebDriver dr){
		driver = dr;
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
		type(ELEMENT_USERNAME,userName,true);
		type(ELEMENT_PASSWORD,Password,true);
		type(ELEMENT_CONFIRM_PASSWORD,Password,true);
		type(ELEMENT_EMAIL,email,true);
		type(ELEMENT_FIRSTNAME,Firstname,true);
		type(ELEMENT_LASTNAME,lastName,true);
		click(ELEMENT_SAVE);
		Utils.pause(2500);
		click(ELEMENT_CONFIRM_INFORMATION);
	}
	
	/**
	 * function: check content of mail then delete mail
	 * @param title title of the page
	 * @param opParams if true check it's present, false check if it's not present
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
			waitForAndGetElement(ELEMENT_GMAIL_CONTENT.replace("${title}",title),30000,0);
		}else{
			waitForElementNotPresent(ELEMENT_GMAIL_CONTENT.replace("${title}",title),30000,0);
		}
		
	}
}
