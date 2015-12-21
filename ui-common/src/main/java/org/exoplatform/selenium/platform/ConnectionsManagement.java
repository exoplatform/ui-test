package org.exoplatform.selenium.platform;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.locator.ConnectionsLocator;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;

public class ConnectionsManagement extends ConnectionsLocator {
	UserProfilePage myProf;
    
    public ConnectionsManagement(WebDriver dr){
		this.driver = dr;
		myProf = new UserProfilePage(dr);
    }
		/**
	 * Tab list
	 */
	public enum selectTabOption{
		ALL, MYCONNECTION, RECEIVE, PENDING;
	}

	/**
	 * 
	 * @param option
	 */
	public void goToConnectionTab(selectTabOption option){
		info("Go to tab");
		switch(option){
		case ALL:
			info("Go to all tab");
			click(ELEMENT_ALL_CONNECTIONS_TAB,0,true);
			break;
		case MYCONNECTION:
			info("Go to my connection tab");
			clickByJavascript(ELEMENT_MY_CONNECTIONS_TAB,0,true);
			break;
		case RECEIVE:
			info("Go to receive tab");
			click(ELEMENT_REQUEST_RECEIVE_CONNECTIONS_TAB,0,true);
			break;
		case PENDING:
			info("Go to pending tab");
			click(ELEMENT_REQUEST_PENDING_CONNECTIONS_TAB,0,true);
			break;
		default:
			info("Go to all tab");
			click(ELEMENT_ALL_CONNECTIONS_TAB,0,true);
			break;
		}
		Utils.pause(1000);
   
	}

	/**
	 * Connect to a user
	 * @param username
	 */
	public void connectToAUser(String username,String... name){
		info("--Connect to a user--");
		info("Click on connect button");
		if(name.length>0)
		searchPeople(name[0],null,null,null);
		else searchPeople(username,null,null,null);
		clickByJavascript(ELEMENT_CONNECTION_CONNECT_BTN.replace("${user}",username));
		waitForAndGetElement(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username),2000,1);
		info("Connected to the user");
	}

	/**
	 * Remove a connection of user
	 * @param username
	 */
	public void removeConnection(String username){
		info("--Remove a connection of a user--");
		info("Click on remove button");
		searchPeople(username,null,null,null);
		clickByJavascript(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}",username));
		waitForElementNotPresent(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}",username));
		info("Removed to the user");
	}
	/**
	 * Cancel a connection to a user
	 * @param username
	 */
	public void cancelConnection(String username){
		info("--Cancel a connection of a user--");
		info("Click on Cancel button");
		searchPeople(username,null,null,null);
		clickByJavascript(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username));
		waitForElementNotPresent(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username));
		info("Canceled to the user");
	}
	/**
	 * Ignore a connection that is sent from a user
	 * @param username
	 */
	public void ignoreConnection(String username){
		info("--Ignore a connection of a user--");
		info("Click on Ignore button");
		searchPeople(username,null,null,null);
		clickByJavascript(ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}",username));
		waitForElementNotPresent(ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}",username));
		info("Connected to the user");
	}
	/**
	 * Reset all connections to default status
	 * @param username
	 */
	public void resetConnection(String username){
		searchPeople(username,null,null,null);
		if(waitForAndGetElement(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}",username),3000,0)!=null)
			removeConnection(username);
		if(waitForAndGetElement(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",username),3000,0)!=null)
			cancelConnection(username);
		if(waitForAndGetElement(ELEMENT_CONNECTION_IGNORE_BTN.replace("${user}",username),3000,0)!=null)
			ignoreConnection(username);
	}
	/**
	 * Accept a connection from a user in Connection page
	 * @param username
	 */
	public void acceptAConnection(String username,String... fullName){
		info("--Accept a connection of a user--");
		info("Click on Confirm button");
		if(fullName.length>0)
		    searchPeople(fullName[0],null,null,null);
		else searchPeople(username,null,null,null);
		waitForAndGetElement(ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username));
		clickByJavascript(ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username));
		waitForElementNotPresent(ELEMENT_CONNECTION_CONFIRM_BTN.replace("${user}",username));
		info("Accepted to the user");
	}

	/**
	 * Function Verify connection
	 * @param username
	 * @param accept (true: if user accept invitation)
	 */
	public void verifyConnection(String username, Boolean accept){
		click(ELEMENT_MY_CONNECTIONS_TAB);
		//With user confirmed the invitation, user  becomes friend and user's name is displayed on user's network list
		searchPeople(username,null,null,null);
		if (accept)
			waitForAndGetElement(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}", username));
		else
			waitForElementNotPresent(ELEMENT_CONNECTION_REVOVE_BTN.replace("${user}", username));
	}
	/**
	 * Verify that a request pending is sent to the user
	 * @param userName
	 * @param isSent
	 */
	public void verifyRequestPending(String userName, Boolean isSent){
		searchPeople(userName,null,null,null);
		if(isSent){
			info("Verify that connection request is sent to the user");
			waitForAndGetElement(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",userName));
			info("The request is sent successfully");
		}else{
			info("Verify that connection request isnot sent to the user");
			waitForElementNotPresent(ELEMENT_CONNECTION_CANCEL_BTN.replace("${user}",userName));
			info("The request isnot sent successfully");
		}
	}

	/**
	 * Clear search textbox
	 */
	public void clearSearchTextbox(){
		info("Clear search textbox");
		click(ELEMENT_ALL_RESULTS);
		type(ELEMENT_NAME_OF_PEOPLE,"",true);
		type(ELEMENT_POSITIONS_OF_PEOPLE,"",true);
		type(ELEMENT_SKILL_OF_PEOPLE,"",true);
	}
	/**
	 * Function search people
	 * @param peopleName
	 * @param position
	 * @param skills
	 * @param directory
	 */
	public void searchPeople(String peopleName, String position, String skills, String directory){
		info("-- Searching people ... --");
		if(peopleName!="" && peopleName!=null){
			type(ELEMENT_NAME_OF_PEOPLE, peopleName, true);
		}
		else{
			type(ELEMENT_NAME_OF_PEOPLE, "", true);
		}
		if(position!="" && position!=null){
			type(ELEMENT_POSITIONS_OF_PEOPLE, position, true);
		}
		else{
			type(ELEMENT_POSITIONS_OF_PEOPLE, "", true);
		}
		if(skills!="" && skills!=null){
			type(ELEMENT_SKILL_OF_PEOPLE, skills, true);
		}
		else{
			type(ELEMENT_SKILL_OF_PEOPLE, "", true);
		}
		clickByJavascript(ELEMENT_SEARCH_BUTTON);
		if (directory!="" && directory!=null)
			click(By.linkText(directory));
		Utils.pause(1000);
	}
	/**
	 * Go to User
	 * @param userName
	 */
	public void goToUserByFullName(String fullName){
		info("Go to User profile page");
		searchPeople(fullName, "", "", "");
		click(ELEMENT_USER_AVATAR.replace("${fullname}", fullName));
		waitForAndGetElement(myProf.ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", fullName));
	}
	
	/**
	 * Go to User
	 * @param userName
	 */
	public void goToUserByUserName(String userName){
		info("Go to User profile page");
		searchPeople(userName, "", "", "");
		click(ELEMENT_USER_LINK.replace(""
				+ "${userName}", userName));
		waitForAndGetElement(myProf.ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("${name}", userName));
	}
	
	/**
	 * Check display of user in Connection
	 * @param user
	 * @param isPresent
	 * @param opt
	 * 			all, my connections, request pending,...
	 */
	public void checkDisplayInConnection(String user,boolean isPresent,selectTabOption opt){
		goToConnectionTab(opt);
		info("check display of user in Connection");
		if(isPresent)
			waitForAndGetElement(ELEMENT_USER_LINK.replace("${userName}", user),2000,0);
		else
			waitForElementNotPresent(ELEMENT_USER_LINK.replace("${userName}", user));
	}
	/**
	 * Check search result in Connection
	 * @param user
	 * @param isPresent
	 * @param opt
	 *  			all, my connections, request pending,...
	 */
	public void checkSearchResultInConnection(String user,boolean isPresent,selectTabOption opt){
		goToConnectionTab(opt);
		info("check display of search result");
		if(isPresent){
			searchPeople(user, "", "", "");
			waitForAndGetElement(ELEMENT_USER_LINK.replace("${userName}", user),2000,0);
		}else {
			searchPeople(user, "", "", "");
			waitForElementNotPresent(ELEMENT_USER_LINK.replace("${userName}", user));
		}
	}
}