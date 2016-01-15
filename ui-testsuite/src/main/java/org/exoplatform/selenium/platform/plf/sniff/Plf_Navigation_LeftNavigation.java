package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


/**
 * @author eXo
 *
 */
public class Plf_Navigation_LeftNavigation extends Plf_TestConfig{

	/**
	 *<li> Case ID:120896.</li>
	 *<li> Test Case Name: Show applications in "COMPANY" list.</li>
	 */
	@Test
	public  void test01_ShowApplicationsInCOMPANYList() {
		info("Test 1: Show applications in COMPANY list");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: - Connect to Intranet
		 *Step Description: 
			- Login as a user
			- Connect to Intranet
		 *Input Data: 

		 *Expected Outcome: 
			- The left Navigation is displayed
			- The "COMPANY" list is displayed with applications in the following order:* Home* Connections* Wiki* Documents* Forums* Calendar* Other personal pages*/ 
		info("Verify expected outcome");
		waitForAndGetElement(hp.ELEMENT_FORUM_LINK_PLF,3000,1);
		waitForAndGetElement(hp.ELEMENT_WIKI_LINK_PLF,3000,1);
		waitForAndGetElement(hp.ELEMENT_HOME_LINK_PLF,3000,1);
		waitForAndGetElement(hp.ELEMENT_CALENDAR_LINK_PLF,3000,1);
		waitForAndGetElement(hp.ELEMENT_CONNECTIONS_LINK_PLF,3000,1);

	}

	/**
	 *<li> Case ID:120897.</li>
	 *<li> Test Case Name: Display Subnavigations in Group Navigation panel.</li>
	 */
	@Test
	public  void test02_DisplaySubnavigationsInGroupNavigationPanel() {
		info("Test 2: Display Subnavigations in Group Navigation panel");
		String title= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title2= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1,"");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Add a node to group navigation
		 *Step Description: 
			- Login as amdin
			- Connect to Intranet
			- From Administration/Portal/Group Sites",
			- Click on Edit navigation of one group
			- Right Click on a node, Select "add node", input valid data, select page for node and click [Save]
		 *Input Data: 

		 *Expected Outcome: 
			- In the Left Navigation, the Group Navigation is displayed before "MY SPACES" panel
			- the node is displayed in the panel*/
		info("Add a note to left Navigation menu");
		navToolBar.goToPotalSites();
		magSite.goToEditNavigation("intranet");
		navMag.addNode(title,"");
		navMag.saveNode();
		info("Verify that the node is added");
		waitForAndGetElement(hp.ELEMENT_LEFT_PANEL.replace("{$name}",title),3000,1);

		/*Step number: 2
		 *Step Name: Add sub
		-node to the group navigation
		 *Step Description: 
			- Add sub
			-node of the above node by the same way as above
		 *Input Data: 

		 *Expected Outcome: 
			- In the Group Navigation panel, a small button is displayed to fold/unfold sub nodes*/ 
		magSite.goToEditNavigation("intranet");
		navMag.addNode(title,title2);
		navMag.saveNode();
		magSite.goToEditNavigation("intranet");
		click(navMag.ELEMENT_NAVIGATION_SUB_NODE_CHECK.replace("{$node}",title));
		waitForAndGetElement(navMag.ELEMENT_NAVIGATION_SUB_NODE_CHECK.replace("{$node}",title2),3000,1);
		navMag.closeNavigationManagementPopup();

	}

	/**
	 *<li> Case ID:120898.</li>
	 *<li> Test Case Name: Open a Space.</li>
	 */
	@Test
	public  void test03_OpenASpace() {
		info("Test 3: Open a Space");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Connect to intranet
		 *Step Description: 
			- Login as a normal user
			- Connect to Intranet
			- Open an application from "COMPANY"
		 *Input Data: 

		 *Expected Outcome: 
			- The left Navigation is displayed
			- The application is opened*/

		/*Step number: 2
		 *Step Name: Open a space
		 *Step Description: 
			- Open a space from "MY SPACES"
		 *Input Data: 

		 *Expected Outcome: 
			The space is opened in the Home space's stream*/ 

		hp.goToMySpaces();
		waitForAndGetElement(spaceHome.ELEMENT_SPACE_PANEL);

	}

	/**
	 *<li> Case ID:120899.</li>
	 *<li> Test Case Name: Display list of spaces ordered by the last browsed.</li>
	 *<li> Pre-Condition: more than two spaces exists</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/01/28 14:15:22</li>
	 */
	@Test
	public  void test04_DisplayListOfSpacesOrderedByTheLastBrowsed() {
		info("Test 4: Display list of spaces ordered by the last browsed");
		String space1= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String space2= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		
		info("Create space 1");
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space1, space1);
		info("Create space 2");
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space2, space2);


		/*Step Number: 1
		 *Step Name: Connect to Intranet
		 *Step Description: 
			- Login as john
			- Connect to Intranet
		 *Input Data: 

		 *Expected Outcome: 
			- The Left Navigation is displayed
			- The "MY SPACES" panel is displayed*/
		info("Verify that My space link is shown on left nagivation menu");
		hp.goToHomePage();
		waitForAndGetElement(hp.ELEMENT_MY_SPACE_LINK_PLF);
		/*Step number: 2
		 *Step Name: Open a space
		 *Step Description: 
			- Open a space from the list, not the first one
		 *Input Data: 

		 *Expected Outcome: 
			- The space is opened*/
		info("Open space 1 in the list");
		click(hp.ELEMENT_NUMBER_OF_SPACE_IN_LIST_LINK.replace("{$number}","2"));
		waitForAndGetElement(spaceMg.ELEMENT_SPACE_NAME_BREADCUMB.replace("{$name}",space1));
		/*Step number: 3
		 *Step Name: Show last browsed spaces on the top
		 *Step Description: 
			- Back to the homepage
		 *Input Data: 

		 *Expected Outcome: 
			- The page is refreshed
			- In "MY SPACES" The last browsed space jump to the top of the list*/ 

		hp.goToHomePage();
		click(hp.ELEMENT_NUMBER_OF_SPACE_IN_LIST_LINK.replace("{$number}","2"));
		waitForAndGetElement(spaceMg.ELEMENT_SPACE_NAME_BREADCUMB.replace("{$name}",space2));
	}

	/**
	 *<li> Case ID:120900.</li>
	 *<li> Test Case Name: Search space in "MY SPACES".</li>
	 *<li> Pre-Condition: spaces exists</li>
	 */
	@Test
	public  void test05_SearchSpaceInMYSPACES() {
		info("Test 5: Search space in MY SPACES");
		String space1= "abc"+getRandomNumber();
		String space2= "ahd"+getRandomNumber();

		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space1, space1);

		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space2, space2);

		/*Step Number: 1
		 *Step Name: Connect to intranet
		 *Step Description: 
			- Login as an user
			- Connect to Intranet
		 *Input Data: 

		 *Expected Outcome: 
			- The Left Navigation is displayed
			- The "MY SPACES" panel is displayed
			- Search filter is displayed*/

		/*Step number: 2
		 *Step Name: Search by inputting one letter
		 *Step Description: 
			- Input a letter "a" in the search box under My spaces label
		 *Input Data: 

		 *Expected Outcome: 
			- All spaces having a word containing with the inputed letter are displayed*/
		type(hp.ELEMENT_SEARCH_SPACE,"a",false);
		waitForAndGetElement(hp.ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}", space1),3000,1);
		waitForAndGetElement(hp.ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}", space2),3000,1);

		hp.goToHomePage();

		type(hp.ELEMENT_SEARCH_SPACE,"ah",false);
		waitForAndGetElement(hp.ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}", space2),3000,1);
		waitForElementNotPresent(hp.ELEMENT_RESULT_SEARCH_SPACE.replace("{$space}", space1),3000,1);
		/*Step number: 3
		 *Step Name: Search by inputting two letters
		 *Step Description: 
			- Input a second letter "b"
		 *Input Data: 

		 *Expected Outcome: 
			- Only spaces containing "ab" are displayed*/ 
	}

	/**
	 *<li> Case ID:120905.</li>
	 *<li> Test Case Name: Display "Left Navigation" for Social Intranet.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/01/28 14:15:22</li>
	 */
	@Test
	public  void test06_DisplayLeftNavigationForSocialIntranet() {
		info("Test 6: Display Left Navigation for Social Intranet");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1,"");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Step 1: Check Left navigation
		 *Step Description: 
			- Login as a user
			- Connect to Intranet
		 *Input Data: 

		 *Expected Outcome: 
			- The Left Navigation is displayed: Company List" and "My Spaces" panels*/

		/*Step number: 2
		 *Step Name: Step 2: Check Left navigation on other portal
		 *Step Description: 
			- Connect to other sites. Go to via url: host:port/portal/acme/.
		 *Input Data: 

		 *Expected Outcome: 
			- The Left Navigation isn't displayed*/
		driver.get(baseUrl+"/acme");

		waitForElementNotPresent(hp.ELEMENT_FORUM_LINK_PLF);
		waitForElementNotPresent(hp.ELEMENT_WIKI_LINK_PLF);
		waitForElementNotPresent(hp.ELEMENT_HOME_LINK_PLF);
		waitForElementNotPresent(hp.ELEMENT_CALENDAR_LINK_PLF);
		waitForElementNotPresent(hp.ELEMENT_CONNECTIONS_LINK_PLF);


	}
}