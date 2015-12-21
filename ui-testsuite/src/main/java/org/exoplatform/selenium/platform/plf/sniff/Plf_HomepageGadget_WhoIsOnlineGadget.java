package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;

	public class Plf_HomepageGadget_WhoIsOnlineGadget extends Plf_TestConfig_2{
		
	/**
	*<li> Case ID:120864.</li>
	*<li> Test Case Name: Check display of Who's online gadget.</li>
	*<li> Pre-Condition: mary and demo are connected to intranet in different browsers.</li>
	*/
	@Test
	public  void test01_CheckDisplayOfWhosOnlineGadget() {
		info("Test 1: Check display of Who's online gadget");
		//initBrowsers();
		/*Step Number: 1
		*Step Name: Check display of this gadget
		*Step Description: 
			- Login as John for example
			- Go to intranet home
			- Check display of Who's online gadget
		*Input Data: 
			
		*Expected Outcome: 
			- The gadget is displaying all people (see WhoOnline.png) that are connected to the social intranet: mary, demo*/ 
		driver.navigate().refresh();
		magAc.mouseOver(hp.ELEMENT_WHO_ONLINE_ICON_PEOPLE_NUMBER.replace("${number}","1"),true);
		waitForAndGetElement(hp.ELEMENT_WHO_ONLINE_POP_UP_NAME.replace("${name}", "Jack"),2000,0);
		
		magAc.mouseOver(hp.ELEMENT_WHO_ONLINE_ICON_PEOPLE_NUMBER.replace("${number}","2"),true);
		waitForAndGetElement(hp.ELEMENT_WHO_ONLINE_POP_UP_NAME.replace("${name}", "Mary"),2000,0);
		//closeBrowsers();
 	}

	/**
	*<li> Case ID:120865.</li>
	*<li> Test Case Name: Show information of user.</li>
	*<li> Pre-Condition: mary and demo are connected to intranet in different browsers.</li>
	*/
	@Test
	public  void test02_ShowInformationOfUser() {
		info("Test 2: Show information of user");
		//initBrowsers();
		//magAc.signOut();
		
		/*Step Number: 1
		*Step Name: Show information of user
		*Step Description: 
			- Login as Demo for ex, go to intranet home page
			- Move the mouse over avatar of Mary at Who's online gadget
		*Input Data: 
			
		*Expected Outcome: 
			-A popup with more mary's informations is shown, including avatar, name, title, last activity message (if existed)*/ 
		magAc3.mouseOver(hp.ELEMENT_WHO_ONLINE_ICON_PEOPLE_NUMBER.replace("${number}","1"), true);
		magAc3.waitForAndGetElement(hp.ELEMENT_WHO_ONLINE_POP_UP_NAME.replace("{$name}", "Mary"),3000,0);
		magAc3.waitForAndGetElement(hp.ELEMENT_WHO_ONLINE_DEFAULT_AVATAR,3000,0);
		//closeBrowsers();
		//magAc.signIn(DATA_USER1, DATA_PASS);
 	}

	/**
	*<li> Case ID:120866.</li>
	*<li> Test Case Name: Connect to users.</li>
	*<li> Pre-Condition: mary and demo are connected to intranet in different browsers.</li>
	*/
	@Test
	public  void test03_ConnectToUsers() {
		info("Test 3: Connect to users");
		//initBrowsers();
		//magAc3.signOut();
		/*Step Number: 1
		*Step Name: Connect to users
		*Step Description: 
			- Login as John for example
			- Open intranet home
			- Hover the mouse on avatar of Mary at Who's Online Gadget
			- Click on Connect
		*Input Data: 
			
		*Expected Outcome: 
			- Who's online is at the right
			- Connect button is shown
			- Connection request is sent to mary*/

		driver.navigate().refresh();
		Utils.pause(2000);
		magAc.mouseOver(hp.ELEMENT_WHO_ONLINE_PEOPLE_AVATAR.replace("${name}","mary"),true);
		Utils.pause(2000);
		magAc.waitForAndGetElement(hp.ELEMENT_WHO_ONLINE_CONNECT,3000,0).click();
		magAc.waitForAndGetElement(hp.ELEMENT_WHO_ONLINE_POP_UP_NAME.replace("{$name}", "Mary"),3000,0);
		/*Step number: 2
		*Step Name: Verify if connection request is sent
		*Step Description: 
			- Login as mary
			- Open intranet home
		*Input Data: 
			
		*Expected Outcome: 
			- Invitation of John is shown at Invitation gadget*/ 
		driver2.navigate().refresh();
		magAc2.waitForAndGetElement(hp.ELEMENT_INVITATIONS_NAME_OF_PEOPLE_WHO_SEND_REQUEST.replace("{$name}", "John"),2000,0);
		
		driver.navigate().refresh();
		Utils.pause(2000);
		magAc.mouseOver(hp.ELEMENT_WHO_ONLINE_PEOPLE_AVATAR.replace("${name}","mary"),true);
		Utils.pause(2000);
		magAc.waitForAndGetElement(hp.ELEMENT_WHO_ONLINE_CANCEL_CONNECT,3000,0).click();
		//closeBrowsers();
	}

	/**
	*<li> Case ID:120867.</li>
	*<li> Test Case Name: Access activity's stream of users.</li>
	*<li> Pre-Condition: mary and demo are connected to intranet in different browsers.</li>
	*/
	@Test
	public  void test04_AccessActivitysStreamOfUsers() {
		info("Test 4: Access activity's stream of users");
		//initBrowsers();
		//magAc.signOut();
		/*Step Number: 1
		*Step Name: Access to activity stream of other users
		*Step Description: 
			- Login as Demo and open intranet home
			- Click on Avatar of Mary
		*Input Data: 
			
		*Expected Outcome: 
			- Who's online gadget is shown with Mary
			- Browser redirect to Mary's activity stream*/ 
		driver3.navigate().refresh();
		magAc3.click(hp.ELEMENT_WHO_ONLINE_ICON_PEOPLE_NUMBER.replace("{$number}","1"));
		magAc3.waitForAndGetElement(myProfile.ELEMENT_NAME_OF_PROFILE_TOP_LEFT.replace("{$name}","Mary"),3000,0);
		//closeBrowsers();
 	}
}