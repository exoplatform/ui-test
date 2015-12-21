package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class SOC_People_Profile_UserStatus extends SOC_TestConfig_2{
	/**
	 *<li> Case ID:122957.</li>
	 *<li> Test Case Name: Check my Status when Chat is not installed.</li>
	 *<li> Pre-Condition: - The Chat add-on is NOT installed in the platform</li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122958.</li>
	 *<li> Test Case Name: Check the Status of another when Chat is not installed.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- The Chat add-on is NOT installed in the platform</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_CheckMyStatusWhenChatIsNotInstalled() {
		info("Test 1: Check my Status when Chat is not installed");
		String iconOnline = chatStatus.getIcon(1);
		String statusOnline = chatStatus.getStatus(1);
		String tooltipOnline = chatStatus.getTooltip(1);
		String iconOffline = chatStatus.getIcon(0);
		String statusOffline = chatStatus.getStatus(0);
		String tooltipOffline = chatStatus.getTooltip(0);
		//Create data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1 : Go to user profile
		 *Step Description: 
			- Login as user A
			- Go to User Menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			- The profile of User A is displayed
			- A user status, symbolized by a round shape, is displayed next to the user name. The status isn't clickable but a simple visual indication.*/
		navTool.goToMyProfile();

		/*Step number: 2
		 *Step Name: Step 2 : Check user A's status
		 *Step Description: 
			- User A is online
		 *Input Data: 

		 *Expected Outcome: 
			- A green dot is displayed next to the user full name*/
		waitForAndGetElement(chat.ELEMENT_CHAT_UISTATUSPROFILEPORTLET.replace("${icon}", iconOnline).replace("${status}", statusOnline));

		/*Step number: 3
		 *Step Name: Step 3 : Checktooltip
		 *Step Description: 
			- Mouse over the status
		 *Input Data: 

		 *Expected Outcome: 
			-The tooltip is "Online"*/ 
		mouseOver(chat.ELEMENT_CHAT_TOOLTIP.replace("${tooltip}", tooltipOnline),true);

		info("Test 2: Check other Status when Chat is not installed");
		info("goto profile of user 2");
		hp.goToConnections();
		click(connMag.ELEMENT_ALL_CONNECTIONS_TAB);
		connMag.searchPeople(username2,null,null,null);
		click(connMag.ELEMENT_CONNECTION_USER_NAME.replace("${user}",username2));

		waitForAndGetElement(chat.ELEMENT_CHAT_UISTATUSPROFILEPORTLET.replace("${icon}", iconOffline).replace("${status}", statusOffline));
		mouseOver(chat.ELEMENT_CHAT_TOOLTIP.replace("${tooltip}", tooltipOffline),true);
	}

	/**
	 *<li> Case ID:122959.</li>
	 *<li> Test Case Name: Check my Status when Chat is installed.</li>
	 *<li> Pre-Condition: - The Chat add-on is installed in the platform</li>
	 *<li> Post-Condition: </li>
	 */
	/**
	 *<li> Case ID:122960.</li>
	 *<li> Test Case Name: Check the Status of another when Chat is installed.</li>
	 *<li> Pre-Condition: - User A and User B are created
	- The Chat add-on is installed in the platform</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_04_CheckMyStatusWhenChatIsInstalled() {
		info("Test 3: Check my Status when Chat is installed");
		String iconAway = chatStatus.getIcon(2);
		String statusAway = chatStatus.getStatus(2);
		String tooltipAway = chatStatus.getTooltip(2);
		String iconDisturb = chatStatus.getIcon(3);
		String statusDisturb = chatStatus.getStatus(3);
		String tooltipDisturb = chatStatus.getTooltip(3);
		String iconInvi = chatStatus.getIcon(4);
		String statusInvi = chatStatus.getStatus(4);
		String tooltipInvi = chatStatus.getTooltip(4);
		String statusOnline = chatStatus.getStatus(1);
		
		//Create data test
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);

		/*Step Number: 1
		 *Step Name: Step 1 : Go to user profile
		 *Step Description: 
			- Login as user A
			- Go to User Menu > [My Profile]
		 *Input Data: 

		 *Expected Outcome: 
			- The profile of User A is displayed
			- A user status, symbolized by a round shape, is displayed next to the user name. The status isn't clickable but a simple visual indication.*/
		navTool.goToMyProfile();

		/*Step number: 2
		 *Step Name: Step 2 : Check Away status
		 *Step Description: 
			- Update chat status of User A to Away
			- Refresh the page
			- Mouse over the status
		 *Input Data: 

		 *Expected Outcome: 
			- A yellow dot is displayed next to the user full name
			-The tooltip is "Away"*/
		info("change status to Away");
		mouseOverAndClick(chat.ELEMENT_CHAT_ICON);
		click(chat.ELEMENT_CHAT_STATUS.replace("${status}", statusAway));
		driver.navigate().refresh();
		info(chat.ELEMENT_CHAT_UISTATUSPROFILEPORTLET.replace("${icon}", iconAway).replace("${status}", statusAway));
		waitForAndGetElement(chat.ELEMENT_CHAT_UISTATUSPROFILEPORTLET.replace("${icon}", iconAway).replace("${status}", statusAway));
		mouseOver(chat.ELEMENT_CHAT_TOOLTIP.replace("${tooltip}", tooltipAway),true);

		/*Step number: 3
		 *Step Name: Step 3 : Check Do not disturb status
		 *Step Description: 
			- Update chat status of User A to Do not disturb
			- Refresh the page
			- Mouse over the status
		 *Input Data: 

		 *Expected Outcome: 
			- A red dot is displayed next to the user full name
			-The tooltip is " Do not disturb"*/
		info("change status to Do not disturb");
		mouseOverAndClick(chat.ELEMENT_CHAT_ICON);
		click(chat.ELEMENT_CHAT_STATUS.replace("${status}", statusDisturb));
		driver.navigate().refresh();
		waitForAndGetElement(chat.ELEMENT_CHAT_UISTATUSPROFILEPORTLET.replace("${icon}", iconDisturb).replace("${status}", statusDisturb));
		mouseOver(chat.ELEMENT_CHAT_TOOLTIP.replace("${tooltip}", tooltipDisturb),true);

		/*Step number: 4
		 *Step Name: Step 4 : Check Invisible status
		 *Step Description: 
			- Update chat status of User A to Invisible
			- Refresh the page
			- Mouse over the status
		 *Input Data: 

		 *Expected Outcome: 
			- A grey dot is displayed next to the user full name
			-The tooltip is "Offline"*/ 
		info("change status to Offline");
		mouseOverAndClick(chat.ELEMENT_CHAT_ICON);
		click(chat.ELEMENT_CHAT_STATUS.replace("${status}", statusInvi));
		driver.navigate().refresh();
		waitForAndGetElement(chat.ELEMENT_CHAT_UISTATUSPROFILEPORTLET.replace("${icon}", iconInvi).replace("${status}", statusInvi));
		mouseOver(chat.ELEMENT_CHAT_TOOLTIP.replace("${tooltip}", tooltipInvi),true);

		info("restore data");
		info("change status to Online");
		mouseOverAndClick(chat.ELEMENT_CHAT_ICON);
		click(chat.ELEMENT_CHAT_STATUS.replace("${status}", statusOnline));
	}
}