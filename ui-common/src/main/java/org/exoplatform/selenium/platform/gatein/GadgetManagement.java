package org.exoplatform.selenium.platform.gatein;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GadgetManagement extends GateinLocator {

	
	ManageAlert alert;
	public GadgetManagement(WebDriver driver) {
		this.driver=driver;
		alert = new ManageAlert(driver);
	}
	/**
	 * Add a Remote gadget
	 * @param url
	 */
	public void addRemoteGadget(String url){
		info("Click on Gadgets button");
		Utils.pause(3000);
		click(ELEMENT_REMOTE_GADGETBTN);
		waitForAndGetElement(ELEMENT_REMOTE_GADGET_URL);
		info("input a url");
		type(ELEMENT_REMOTE_GADGET_URL,url,true);
		info("Click on Add button");
		click(ELEMENT_REMOTE_GADGET_ADDBTN);
		Utils.pause(2000);
	}
	/**
	 * Add a new gadget into a category
	 * @param category
	 */
	public void addIntoCategory(String category){
		info("Click on the link: Click here to add into category");
		click(ELEMENT_REMOTE_GADGET_ADD_INTO_CATEGORY_LINK);
		info("Verify that the links is hided after clicked");
		waitForElementNotPresent(ELEMENT_REMOTE_GADGET_ADD_INTO_CATEGORY_LINK);
		info("Waiting categories table is shown");
		waitForAndGetElement( ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES,2000,0);
		check(ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_CHECKBOX.replace("${category}",category),2);
		info("Save all changes");
		click(ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_SAVEBTN);
		Utils.pause(2000);
	}
	/**
	 * Delete a gadget
	 * @param name
	 */
	public void deleteGadget(String name){
		info("Click on delete button");
		click(ELEMENT_REMOTE_GADGET_LEFT_DELETE_BTN.replace("${name}",name));
		alert.acceptAlert();
		waitForElementNotPresent(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}", name));
	}
	/**
	 * Create a new gadget
	 * @param name
	 * @param xmlCode
	 */
	public void createNewGadget(String name, String xmlCode){
		info("Click on Create new gadget button");
		click(ELEMENT_CREATE_NEW_GADGET_BTN);
		info("Input a new name");
		type(ELEMENT_CREATE_NEW_GADGET_INPUT_NAME_FIELD,name,true);
		if(!xmlCode.isEmpty()){
			info("Input a new source code");
			WebElement element = driver.findElement(ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD);
			element.clear();
			element.click();
			System.out.println(xmlCode);
			element.sendKeys(xmlCode);
			//type(ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD,xmlCode,true);
		}
		info("Click on save button");
		click(ELEMENT_CREATE_NEW_GADGET_SAVE_BTN);
		info("Verify that the gadget is created successfully");
		waitForAndGetElement(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}",name),2000,0);
	}
	/**
	 * Edit a Gadget with newXmlCode that include newName
	 * @param oldName
	 * @param newXmlCode
	 * @param newName
	 */
	public void editAGadget(String oldName, String newXmlCode,String newName){
		info("Select a Gadget");
		click(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}",oldName));
		info("Click on Edit button");
		click(ELEMENT_GADGET_EDIT_BTN);
		info("Change xml code");
		type(ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD,newXmlCode,true);
		info("Click on Save button");
		click(ELEMENT_CREATE_NEW_GADGET_SAVE_BTN);
		info("Verify that the gadget is edit successfully");
		waitForAndGetElement(ELEMENT_REMOTE_GADGET_LEFT_CONTENT.replace("${name}",newName),2000,0);
	}
}
