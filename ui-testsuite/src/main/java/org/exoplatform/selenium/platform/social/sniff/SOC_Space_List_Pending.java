package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class SOC_Space_List_Pending extends SOC_TestConfig_1 {
	/**
	 *<li> Case ID:121893.</li>
	 *<li> Test Case Name: Check Pending space list.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckPendingSpaceList() {
		info("Test 01: Check Pending space list");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Check displaying on Requests Pending list
		 *Step Description: 
			- Go to My space page and select All spaces tab
			- Send request to specific Space 
			- Click on Requests Pending tab
		 *Input Data: 

		 *Expected Outcome: 
			- Show all pending requests of this user
        */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		
		magAc.signIn(username2, password);
		
		hp.goToMySpaces();
		spaMg.goToAllSpacesTab();
		spaMg.sendARequestToASpace(space);
		
		spaMg.goToRequestPendingTab();
		info("Verify that request to join button is hidden and request pending status is shown");
		waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING.replace("${space}", space),3000,1);
		
		
	}
	
}