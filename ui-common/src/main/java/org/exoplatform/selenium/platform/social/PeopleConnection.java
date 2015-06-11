package org.exoplatform.selenium.platform.social;

import static org.exoplatform.selenium.TestLogger.*;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author thaopth
 * Date: 09/10/2012
 */
/**
 * Update By vuna2
 * -- Add removeConnection 
 */
public class PeopleConnection extends SocialBase {
	PeopleSearch peoSearch;
	public PeopleConnection(WebDriver dr, String...plfVersion){
		driver = dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		peoSearch = new PeopleSearch(driver);
	}

	// Go to Account Name link	
	// My Connections
	public final By	ELEMENT_CONNECTION_OF_USER = By.xpath("//*[@id='UIUserNavigationPortlet']//../*[contains(text(),'Connections')]");
	public final By ELEMENT_REQUESTS_RECEIVED_TAB = By.linkText("Requests Received");
	public final By ELEMENT_REQUEST_PENDING_TAB = By.linkText("Requests Pending");
	public final By ELEMENT_MY_CONNECTIONS_TAB = By.linkText("My Connections");
	public final By ELEMENT_EVERYONE_TAB = By.linkText("Everyone");
	public final String ELEMENT_EVERYONE_TAB_ACTIVE = "//li[@class='active']/a[contains(text(),'Everyone')]";
	public final By ELEMENT_REQUEST_SENT_TAB = By.linkText("Requests Sent");

	public final String ELEMENT_CONNECTION_BUTTON = "//*[@id='UIAllPeoplePortlet']//*[contains(text(), '${peopleName}')]/../../..//*[contains(text(),'Connect')]";
	public final String ELEMENT_CANCEL_REQUEST_BUTTON = "//a[text()='${peopleName}']/ancestor::div[contains(@class,'pull-left')]//button[contains(text(),'Cancel Request')]";
	public final String ELEMENT_REMOVE_CONNECTION_BUTTON = "//a[text()='${peopleName}']/ancestor::div[contains(@class,'pull-left')]//button[contains(text(),'Remove Connection')]";
	public final String ELEMENT_CONFIRM_BUTTON = "//a[text()='${peopleName}']/ancestor::div[contains(@class,'pull-left')]//button[contains(text(),'Confirm')]";

	public final String ELEMENT_IGNORE_BUTTON = "//a[text()='${peopleName}']/ancestor::div[contains(@class,'pull-left')]//button[contains(text(),'Ignore')]";
	//public final String ELEMENT_IGNORE_BUTTON = "//*[@data-original-title='${peopleName}']/../..//*[text()='Ignore']";
	public final String ELEMENT_CONNECT_LIST = "//*[text()='Connect']";
	public final String ELEMENT_PEOPLE_SEARCH = "//*[@class='uiProfileUserSearch']/..//*[text()='${peopleName}']";
	public final By ELEMENT_REVOKE_BUTTON = By.xpath("//button[text()='Revoke']");

	//-----------------------Connections page------------------------
	public String ELEMENT_INVITATION_RECEIVED_MSG = "//h4[@class='spaceTitle']/a[contains(text(),'${acc}')]/ancestor::div[@class='spaceBox pull-left']/div[@class='connectionBtn clearfix']/span[contains(text(),'Invitation Received')]";
	//---------------------------Generic user popup - Connection Application------------
	public final String ELEMENT_CONNECTION_MEMBER = "//a[text()='${peopleName}']";

	/**
	 * Connect to people
	 * @param peopleName: name of selected people (String)
	 */
	public void connectPeople (String peopleName) {
		info("-- Connect to: " + peopleName);
		info("-- Connect the user: " + peopleName);
		if(waitForAndGetElement(ELEMENT_EVERYONE_TAB,5000,0)==null){
			info("----Go to My connections----");
			goToMyConnections();
			info("---Click  every one tab-----");
			click(ELEMENT_EVERYONE_TAB);
		}
		if(waitForAndGetElement(ELEMENT_EVERYONE_TAB_ACTIVE,5000,0) == null)
			click(ELEMENT_EVERYONE_TAB);
		//resetConnection(peopleName);
		info("-----Click connect to people-----");
		click(ELEMENT_CONNECTION_BUTTON.replace("${peopleName}", peopleName));
		info("---Verify Connect button is disappeared----");
		waitForElementNotPresent(ELEMENT_CONNECTION_BUTTON.replace("${peopleName}", peopleName));
		info("-----Verify Cancel request button is displayed-----");
		waitForAndGetElement(ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", peopleName),5000, 1);
	}

	/**
	 * Accept the invitation
	 * @param peopleName: name of selected people (String)
	 */
	public void acceptInvitation (String peopleName) {
		info("-- Accept the invitation: " + peopleName);
		if(waitForAndGetElement(ELEMENT_REQUESTS_RECEIVED_TAB,5000,0)==null){
			info("----Go to My connections----");
			goToMyConnections();
			info("---Click Requests Received tab-----");
			click(ELEMENT_REQUESTS_RECEIVED_TAB);
		}
		else
			click(ELEMENT_REQUESTS_RECEIVED_TAB);
		peoSearch.searchPeople(true,peopleName);
		waitForAndGetElement(By.linkText(peopleName));
		info("----Confirm the invitation from user '"+peopleName+"' ");
		click(ELEMENT_CONFIRM_BUTTON.replace("${peopleName}", peopleName));
		waitForElementNotPresent(ELEMENT_CONFIRM_BUTTON.replace("${peopleName}", peopleName));
		info("----Go to My connections tab----");
		click(ELEMENT_MY_CONNECTIONS_TAB);
		info("---Verify remove connection button----");
		waitForAndGetElement(ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", peopleName));
	}

	/**
	 * Ignore the invitation
	 * @param peopleName: name of selected people (String)
	 */
	public void ignoreInvitation (String peopleName) {
		info("-- Ignore the invitation from: " + peopleName);
		if(waitForAndGetElement(ELEMENT_REQUESTS_RECEIVED_TAB,5000,0)==null){
			info("----Go to My connections----");
			goToMyConnections();
			info("---Click Requests Received tab-----");
			click(ELEMENT_REQUESTS_RECEIVED_TAB);
		}
		else
			click(ELEMENT_REQUESTS_RECEIVED_TAB);
		waitForAndGetElement(ELEMENT_IGNORE_BUTTON.replace("${peopleName}", peopleName));
		info("---Ignore the invitation from user '"+peopleName+"'-----");
		click(ELEMENT_IGNORE_BUTTON.replace("${peopleName}", peopleName));
		waitForElementNotPresent(ELEMENT_IGNORE_BUTTON.replace("${peopleName}", peopleName));
		info("---Go to Everyone tab----");
		click(ELEMENT_EVERYONE_TAB);
		waitForAndGetElement(ELEMENT_CONNECTION_BUTTON.replace("${peopleName}", peopleName));
	}

	/**
	 * Remove connection 
	 * @param peopleName: name of selected people (String)
	 */
	public void removeConnection(String peopleName){
		info("-- Remove connection with: " + peopleName);

		if(waitForAndGetElement(ELEMENT_MY_CONNECTIONS_TAB,5000,0)==null){
			info("----Go to My connections----");
			goToMyConnections();
			waitForAndGetElement(ELEMENT_MY_CONNECTIONS_TAB);
			info("---Click Requests Received tab-----");
			click(ELEMENT_MY_CONNECTIONS_TAB);
		}
		else
			click(ELEMENT_MY_CONNECTIONS_TAB);
		waitForAndGetElement(ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", peopleName));
		click(ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", peopleName));
		Utils.pause(3000);
		waitForElementNotPresent(ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", peopleName));
		Utils.pause(1000);
	}

	/**
	 * @author dunghm
	 * Cancel a connection request
	 * @param name : User's name that sent connection request
	 */
	/**
	 * Update by vuna2
	 * @param name : User's name that sent connection request 
	 */
	/**
	 * @update by PhuongDT
	 * @param name: change how to find element CancelRequest
	 */
	public void cancelRequest(String peopleName){
		info("-- Cancel the invitation: " + peopleName);
		if(waitForAndGetElement(ELEMENT_REQUEST_PENDING_TAB,5000,0)==null){
			info("----Go to My connections----");
			goToMyConnections();
			waitForAndGetElement(ELEMENT_REQUEST_PENDING_TAB);
			info("---Click Pending Requests tab-----");
			click(ELEMENT_REQUEST_PENDING_TAB);
		}
		else
			click(ELEMENT_REQUEST_PENDING_TAB);
		waitForAndGetElement(ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", peopleName));
		info("---Cancel the invitation to user '"+peopleName+"'-----");
		click(ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", peopleName));
		waitForElementNotPresent(ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", peopleName));
		info("---Go to Everyone tab----");
		click(ELEMENT_EVERYONE_TAB);
		waitForAndGetElement(ELEMENT_CONNECTION_BUTTON.replace("${peopleName}", peopleName));
	}

	public void quickRemoveConnection(String peopleName){    

		By ELEMENT_REMOVE_CONNECTION_BTN = By.xpath("//a[contains(@class,'InviteTitle') and text()='" + peopleName + "']/ancestor::div[contains(@class,'ContentSpace')]//a[text()='Remove Connection']");    

		click(ELEMENT_REMOVE_CONNECTION_BTN);
		waitForElementNotPresent(ELEMENT_REMOVE_CONNECTION_BTN);
	}

	/**function reset all relation (if exist) with other user
	 * @author lientm
	 * @param user
	 */

	public void resetConnection(String user){
		info("-- Reset Connection to: " + user);
		if(waitForAndGetElement(ELEMENT_EVERYONE_TAB, 5000, 0) == null){
			goToMyConnections();
			click(ELEMENT_EVERYONE_TAB);
		}
		else
			click(ELEMENT_EVERYONE_TAB);
		peoSearch.searchPeople(true,user);
		waitForAndGetElement(By.linkText(user));
		if (waitForAndGetElement(ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", user), 5000, 0) != null){
			click(ELEMENT_CANCEL_REQUEST_BUTTON.replace("${peopleName}", user));
		}
		if (waitForAndGetElement(ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user), 5000, 0) != null){
			click(ELEMENT_REMOVE_CONNECTION_BUTTON.replace("${peopleName}", user));
		}
		if (waitForAndGetElement(ELEMENT_IGNORE_BUTTON.replace("${peopleName}", user), 5000, 0) != null) {
			click(ELEMENT_IGNORE_BUTTON.replace("${peopleName}", user));
		}
	}
	
	/**
	 * Go to user profile
	 * @param user
	 */
	public void goToUserProfile(String user){
		info("Go to user profile of an user");
		click(ELEMENT_CONNECTION_MEMBER.replace("${peopleName}", user));
		Utils.pause(1000);
	}

}
