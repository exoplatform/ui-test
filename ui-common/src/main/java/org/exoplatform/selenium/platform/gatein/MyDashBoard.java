package org.exoplatform.selenium.platform.gatein;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MyDashBoard extends GateinLocator {

	ManageAlert magAlert;
	
	public MyDashBoard(WebDriver dr){
		this.driver = dr;
		magAlert = new ManageAlert(dr);
	}
	/**
	 * Add a gadget to dashboard
	 * @param name  the name of gadget that will be added to dashboard
	 * @param numberCol the number of column that will put the gadget. We have 3 columns as: 
	 * + numberCol= 1: this is for left column
	 * + numberCol= 2: this is for middle column
	 * + numberCol =3: this is for right column
	 */
	public void addGadget(String name, String numberCol){
		info("Click on GadGet button");
		click(ELEMENT_MYDASH_BTN_ADDGADGET);
		waitForAndGetElement(ELEMENT_DASHBOARD_WORKSPACE_POPUP_TITLE,2000,0);
		info("The popup is shown");
		dragAndDropToObject(ELEMENT_MYDASH_GADGET_NAME.replace("${name}",name),ELEMENT_MYDASH_GADGET_COLUMN.replace("${number}",numberCol));
		Utils.pause(2000);
		click(ELEMENT_DASHBOARD_WORKSPACE_POPUP_CLOSE);
		waitForAndGetElement(ELEMENT_MYDASH_ADDED_GADGET_IN_DASHBOARD.replace("${name}",name),2000,0);
		info("The gadget is added to dashboard");
	}
	/**
	 * Delete a Gadget in Dashboard page
	 * @param name
	 */
	public void deleteGadget(String name){
		info("Click on Delete button");
		clickByJavascript(ELEMENT_MYDASH_DELETE_GADGET.replace("${name}", name));
		magAlert.acceptAlert();
		waitForElementNotPresent(ELEMENT_MYDASH_DELETE_GADGET.replace("${name}", name));
		info("The gadget is deleted successfully");
		
	}
	/**
	 * Add a remote gadget to the page
	 * @param url
	 * @param name
	 */
	public void addRemoteGadget(String url,String name){
		info("Open add gadget popup");
		clickByJavascript(ELEMENT_MYDASH_BTN_ADDGADGET);
		info("Input a url link");
		if(browser.contains("iexplorer"))
		typeByJavascript("url", url);
		else type(ELEMENT_MYDASH_GADGET_BYURL, url, true);
		info("Click on add button");
		click(ELEMENT_MYDASH_GADGET_ADDBTN);
		info("Close the popup");
		click(ELEMENT_DASHBOARD_WORKSPACE_POPUP_CLOSE);
		info("Verify that the remote gadget is shown on the page");
		waitForAndGetElement(ELEMENT_MYDASH_ADDED_GADGET_IN_DASHBOARD.replace("${name}",name),2000,1);
		Utils.pause(2000);
	}
	/**
	 * Add new tab
	 * @param name
	 */
	public void addTab(String name){
		info("Click on add button");
		click(ELEMENT_MYDASH_BTN_ADDTAB);
		waitForAndGetElement(ELEMENT_MYDASH_BTN_NAMETAB.replace("${name}", "Tab_2")).clear();
		type(ELEMENT_MYDASH_BTN_NAMETAB.replace("${name}", "Tab_2"),name,false);
		type(ELEMENT_MYDASH_BTN_NAMETAB.replace("${name}", "Tab_2"),"\n",false);
		info("Verify that the new tab is added");
		waitForAndGetElement(ELEMENT_MYDASH_TAB_NAME.replace("${name}",name),2000,0);
		
	}
	/**
	 * Rename a tab
	 * @param oldName
	 * @param newName
	 */
	public void renameTab(String oldName, String newName){
        info("Click on the tab");		
		click(ELEMENT_MYDASH_TAB_NAME.replace("${name}",oldName));
		info("Input new name");
		waitForAndGetElement(ELEMENT_MYDASH_TAB_NAME.replace("${name}", oldName),2000,0).clear();
		waitForAndGetElement(ELEMENT_MYDASH_TAB_NAME.replace("${name}", oldName),2000,0).sendKeys(newName);
		(waitForAndGetElement(ELEMENT_MYDASH_TAB_NAME.replace("${name}", oldName),2000,0)).sendKeys(Keys.ENTER);
		info("Verify that the new name is added");
		waitForAndGetElement(ELEMENT_MYDASH_TAB_NAME.replace("${name}",newName),2000,0);
	}
	/**
	 * Delete a tab
	 * @param name
	 */
	public void deleteTab(String name){
		info("Click on delete button");
		click(ELEMENT_MYDASH_BTN_DELETETAB.replace("${name}",name));
		magAlert.acceptAlert();
		info("Verify that the tab is deleted");
		waitForElementNotPresent(ELEMENT_MYDASH_BTN_DELETETAB.replace("${name}",name),2000,0);
	}
}
