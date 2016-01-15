package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class Wiki_Search extends Wiki_TestConfig {
	/**
	 *<li> Case ID:122814.</li>
	 *<li> Test Case Name: Quick search.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: Some wiki pages already exists and contain the word "cluster" in the title and content</li>
	 */
	@Test
	public  void test01_QuickSearch() {
		info("Test 1: Quick search");
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Quick Search
		 *Step Description: 
			- Log in and access Wiki app
			- Input the text "cluster"into Search box
			- Hit Enter key
		 *Input Data: 

		 *Expected Outcome: 
			The Search Screen is displayed and the results matching the keyword into search box are displayed.*/ 
		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(wiki,wiki);
		wikiMg.saveAddPage();
		wHome.goTosearchPage(wiki);
		info("Verify that search page is shown with the text");
		waitForAndGetElement(wSearchMg.ELEMENT_SEARCH_RESULT.replace("${title}",wiki),3000,0);
	}

	/**
	 *<li> Case ID:122815.</li>
	 *<li> Test Case Name: Advanced search.</li>
	 *<li> Pre-Condition: Some wiki pages already exists and contain the word "cluster" in the title and content</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_AdvancedSearch() {
		info("Test 2: Advanced search");
		String wiki = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Do quick search
		 *Step Description: 
			- Log in and access Wiki app
			- Input the word "cluster" into the Search box
			- Press Enter key
		 *Input Data: 

		 *Expected Outcome: 
			The Search Screen is displayed and the results matching the keyword into search box are displayed.*/

		hp.goToWiki();
		wHome.goToAddBlankPage();
		rtMode.addSimplePage(wiki,wiki);
		wikiMg.saveAddPage();
		wHome.goTosearchPage(wiki);
		info("Verify that search page is shown with the text");
		waitForAndGetElement(wSearchMg.ELEMENT_SEARCH_RESULT.replace("${title}",wiki),3000,0);
		/*Step number: 2
		 *Step Name: Step 2: Advanced Search
		 *Step Description: 
			- In Search screen form: input keyword, select space in drop down list and click on Search button
		 *Input Data: 
	
		 *Expected Outcome: 
			Search results will list pages that matches with keyword and selected space*/ 
		wSearchMg.advancedSearch("","My Wiki");
		info("Verify that the searched results is listed that matches with keyword and selected location");
		waitForAndGetElement(wSearchMg.ELEMENT_SEARCH_NORESULT,3000,0);
	}

	/**
	 *<li> Case ID:122839.</li>
	 *<li> Test Case Name: Search template.</li>
	 *<li> Pre-Condition: There are some wiki templates include the text "test" for ex in the title and content</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_SearchTemplate() {
		info("Test 3: Search template");
	
		String template = wTempData.getWikiTemplateRandom();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Search Template
		 *Step Description: 
			- Log in and access Wiki app
			- Click on Browse at top right and select Wiki Settings action
			- Input keyword "test" for ex into Search box
			- Hit Enter key
		 *Input Data: 

		 *Expected Outcome: 
			The Search results matching keyword into search box are displayed.*/
		hp.goToWiki();
		wHome.goToWikiSettingPage();
		wSettingMg.searchTemplate(template);
	}
	
}