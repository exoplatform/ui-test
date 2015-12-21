package org.exoplatform.selenium.platform.social.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class SOC_Space_Search extends SOC_TestConfig_1 {
	/**
	 *<li> Case ID:121905.</li>
	 *<li> Test Case Name: Search Space.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_SearchSpace() {
		info("Test 01: Search Space");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ "@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Access the space page
		 *Step Description: 
			- Click MY SPACES on the left panel, or click Join space
		 *Input Data: 

		 *Expected Outcome: 
			- Display the spaces page
        */ 
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		
		
		/*Step Number: 2
		 *Step Name: Step 2: Search by Name/description
		 *Step Description: 
			- Input keyword "test" for ex into search text box and click Search button
		 *Input Data: 

		 *Expected Outcome: 
			- Display all results matching with keyword 
        */ 
		hp.goToMySpaces();
		spaMg.searchSpace(space, "1");
		
		/*Step Number: 3
		 *Step Name: Step 3: Search by directory
		 *Step Description: 
			-Search space by directory: Click on each characters (filter by A -> Z)
		 *Input Data: 

		 *Expected Outcome: 
			- Display all spaces which has name starts by the selected char
        */ 
		spaMg.searchByLetterList("C", space);
		
	}
	
}