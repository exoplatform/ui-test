package org.exoplatform.selenium.platform.wiki.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


	public class Wiki_Space_Switcher extends WIKI_TestConfig{

	/**
	*<li> Case ID:139372.</li>
	*<li> Test Case Name: Changing from one space wiki to another should change the currently browsed space.</li>
	*<li> Pre-Condition: User is member of Space 1 and Space 2
	*Wiki of Space 2 has :
	- Page 1
	- Page 2
	--- Sub-Page 3</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_ChangingFromOneSpaceWikiToAnotherShouldChangeTheCurrentlyBrowsedSpace() {
		info("Test 1: Changing from one space wiki to another should change the currently browsed space");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Using the left sidebar navigation, select "Space 2"
		*Input Data: 
			
		*Expected Outcome: 
			- Space 2 is displayed with space navigation, on its activity stream*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);
		
		info("Create a space");
		String space2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space2,space2,6000);
		Utils.pause(2000);
		
		info("Create pages");
		String page1 =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space2);
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page1,page1);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page1);
		arrayPage.add(page1);
		
		info("Create pages");
		String page2 =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToHomeWikiPage();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page2,page2);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page2);
		arrayPage.add(page2);
		
		info("Create subpage");
		String subpage =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAPage(page2);
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(subpage,subpage);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(subpage);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Go to wiki application
		*Input Data: 
			
		*Expected Outcome: 
			- Space 2 is displaying wiki application.
			- The space navigation is still displayed*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Open the space switcher
			- Select "Space 1"
		*Input Data: 
			
		*Expected Outcome: 
			- The wiki of "Space 1" is displayed
			- The Space navigation of "Space 2" is not displayed anymore
			- Wiki is selected on the left sidebar navigation*/ 
		hp.goToSpecificSpace(space2);
		spaMg.goToWikiTab();
		wHome.goToWikiHomeOfSpaceFromBreadcrumb(space1, null);
		wValidate.verifyBreadCrumbePath(space1,"Wiki Home");
		waitForAndGetElement(spaHome.ELEMENT_WIKI_TAB);

 	}

	/**
	*<li> Case ID:139373.</li>
	*<li> Test Case Name: Changing wiki should display the root page of the targeted wiki.</li>
	*<li> Pre-Condition: User is member of Space 2Wiki of Space 2 has :
	- Page 1
	- Page 2
	--- Sub-Page 3</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_ChangingWikiShouldDisplayTheRootPageOfTheTargetedWiki() {
		info("Test 2: Changing wiki should display the root page of the targeted wiki");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go into Company wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Company wiki is displayed
			- Space switcher is displayed in the breadcrumb*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);
		
		info("Create pages");
		String page1 =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space1);
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page1,page1);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page1);
		arrayPage.add(page1);
		
		info("Create pages");
		String page2 =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToHomeWikiPage();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page2,page2);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page2);
		arrayPage.add(page2);
		
		info("Create subpage");
		String subpage =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAPage(page2);
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(subpage,subpage);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(subpage);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open Space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Space switcher is displaying its list of options*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Select "Space 2"
		*Input Data: 
			
		*Expected Outcome: 
			- Wiki of Space 2 is opened on "Wiki Home" page*/ 
		hp.goToWiki();
		wHome.goToWikiHomeOfSpaceFromBreadcrumb(space1, null);
		wValidate.verifyBreadCrumbePath(space1,"Wiki Home");
		waitForAndGetElement(spaHome.ELEMENT_WIKI_TAB);

 	}

	/**
	*<li> Case ID:139374.</li>
	*<li> Test Case Name: Selecting the same wiki should redirect to the wiki's home.</li>
	*<li> Pre-Condition: User is member of Space 2Wiki of Space 2 has :
	- Page 1
	- Page 2
	--- Sub-Page 3</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_SelectingTheSameWikiShouldRedirectToTheWikisHome() {
		info("Test 3: Selecting the same wiki should redirect to the wiki's home");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go into "Space 2" wiki
		*Input Data: 
			
		*Expected Outcome: 
			- "Space 2" wiki is displayed
			- Space switcher is displayed in the breadcrumb*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);
		
		info("Create pages");
		String page1 =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space1);
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page1,page1);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page1);
		arrayPage.add(page1);
		
		info("Create pages");
		String page2 =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToHomeWikiPage();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(page2,page2);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(page2);
		arrayPage.add(page2);
		
		info("Create subpage");
		String subpage =  txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAPage(page2);
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(subpage,subpage);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(subpage);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open Space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Space switcher is displaying its list of options*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Select "Space 2"
		*Input Data: 
			
		*Expected Outcome: 
			- Wiki of Space 2 is opened on "Wiki Home" page*/ 
		hp.goToSpecificSpace(space1);
		spaMg.goToWikiTab();
		wHome.goToWikiHomeOfSpaceFromBreadcrumb(space1, null);
		wValidate.verifyBreadCrumbePath(space1,"Wiki Home");
		waitForAndGetElement(spaHome.ELEMENT_WIKI_TAB);

 	}

	/**
	*<li> Case ID:139375.</li>
	*<li> Test Case Name: Switching to "Intranet" from a space wiki should not display space navigation anymore.</li>
	*<li> Pre-Condition: User is member of "Space 1"</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_SwitchingToIntranetFromASpaceWikiShouldNotDisplaySpaceNavigationAnymore() {
		info("Test 4: Switching to Intranet from a space wiki should not display space navigation anymore");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go into to "Space 1"
			- Select wiki application
		*Input Data: 
			
		*Expected Outcome: 
			- Space 1 is displayed
			- Wiki is opened*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Space switcher is displaying its list with only:* Intranet* My Wiki* input text* Space 1*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Select "Intranet"
		*Input Data: 
			
		*Expected Outcome: 
			- Intranet wiki is displayed
			- Breadcrumb is displaying "Intranet"
			- Space navigation from "Space 1" is not displayed anymore*/ 
		
		hp.goToSpecificSpace(space1);
		spaMg.goToWikiTab();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher(space1);
		wValidate.verifyPresentSpaceSwitcher("Intranet");
		wValidate.verifyPresentSpaceSwitcher("My Wiki");
		wValidate.verifyPresentSpaceSwitcher(space1);
		wHome.closeSpaceWitcher();
		wHome.goToWikiHomeOfSpaceFromBreadcrumb("Intranet", null);
		wValidate.verifyBreadCrumbePath("Intranet","Wiki Home");
		waitForElementNotPresent(spaHome.ELEMENT_WIKI_TAB);

 	}

	/**
	*<li> Case ID:139376.</li>
	*<li> Test Case Name: Switching to "My Wiki" from Company Wiki should display User Navigation.</li>
	*<li> Pre-Condition: User is member of 0 spaces</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_SwitchingToMyWikiFromCompanyWikiShouldDisplayUserNavigation() {
		info("Test 5: Switching to My Wiki from Company Wiki should display User Navigation");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to "Company" wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying the space switcher component*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Space switcher is displaying its list with only:* Intranet* My Wiki*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Select "My Wiki"
		*Input Data: 
			
		*Expected Outcome: 
			- Personal wiki is displayed
			- Breacrumb is displaying "My Wiki"
			- User navigation is displayed "My wiki" selected*/ 
		
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher(" ");
		wValidate.verifyPresentSpaceSwitcher("Intranet");
		wValidate.verifyPresentSpaceSwitcher("My Wiki");
		wValidate.verifyNoAnySpaces();
		wHome.closeSpaceWitcher();
		wHome.goToWikiHomeOfSpaceFromBreadcrumb("My Wiki", arrayUsers.get(0));
		wValidate.verifyBreadCrumbePath("My Wiki","Wiki Home");
        waitForAndGetElement(userPageBase.ELEMENT_HORIZONTAL_TOOLBAR_FORTH_APP_WIKI);
 	}

	/**
	*<li> Case ID:139377.</li>
	*<li> Test Case Name: Switching to a space wiki from Company wiki should display space navigation.</li>
	*<li> Pre-Condition: User is member of "Space 1"</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_SwitchingToASpaceWikiFromCompanyWikiShouldDisplaySpaceNavigation() {
		info("Test 6: Switching to a space wiki from Company wiki should display space navigation");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to "Company" wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying the space switcher component*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Space switcher is displaying its list with only:* Intranet* My Wiki* input text* Space 1*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Select "Space 1"
		*Input Data: 
			
		*Expected Outcome: 
			- Personal wiki is displayed
			- Breacrumb is displaying "Space 1"
			- Space navigation is displayed ("Wiki" application selected)*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher(space1);
		wValidate.verifyPresentSpaceSwitcher("Intranet");
		wValidate.verifyPresentSpaceSwitcher("My Wiki");
		wValidate.verifyPresentSpaceSwitcher(space1);
		wHome.closeSpaceWitcher();
		wHome.goToWikiHomeOfSpaceFromBreadcrumb(space1, null);
		wValidate.verifyBreadCrumbePath(space1,"Wiki Home");
		waitForAndGetElement(spaHome.ELEMENT_WIKI_TAB);

 	}

	/**
	*<li> Case ID:139378.</li>
	*<li> Test Case Name: Hover in space switcher's list is displayed.</li>
	*<li> Pre-Condition: User is member of some spaces</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATED
	*/
	@Test(groups="pending")
	public  void test07_HoverInSpaceSwitchersListIsDisplayed() {
		info("Test 7: Hover in space switcher's list is displayed");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Using left sidebar navigation, go into Wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Wiki application is displayed
			- Breadcrumb is displaying space switcher component*/
		

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space Switcher
			- Put your mouse over an item of the list
		*Input Data: 
			
		*Expected Outcome: 
			- Item of the list is changing his display state to hover*/ 

 	}

	/**
	*<li> Case ID:139379.</li>
	*<li> Test Case Name: Input text should display a placeholder.</li>
	*<li> Pre-Condition: User is member of some spaces</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_InputTextShouldDisplayAPlaceholder() {
		info("Test 8: Input text should display a placeholder");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to Company wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying Space Switcher*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);


		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space Switcher
		*Input Data: 
			
		*Expected Outcome: 
			- The first item of the list is an input text field
			- Placeholder is "Filter spaces"*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		waitForAndGetElement(wHome.ELEMENT_SPACE_SWITCHER_SEARCH_FIELD_PLACEHOLDER_TEXT);

 	}

	/**
	*<li> Case ID:139380.</li>
	*<li> Test Case Name: List of spaces should be displayed when user is member of at least one space.</li>
	*<li> Pre-Condition: User is member of the space "Mobile"
	*Mobile Space avatar is defined</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_ListOfSpacesShouldBeDisplayedWhenUserIsMemberOfAtLeastOneSpace() {
		info("Test 9: List of spaces should be displayed when user is member of at least one space");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to Company wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying Space Switcher*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space Switcher
		*Input Data: 
			
		*Expected Outcome: 
			- The list of spaces is displayed after the input textfield
			- First item is "Mobile"
			- Avatar of the space is displayed before "Mobile"*/ 
		
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		waitForAndGetElement(wHome.ELEMENT_SPACE_SWITCHER_SEARCH_FIELD_PLACEHOLDER_TEXT);
		waitForAndGetElement(wHome.ELEMENT_SPACE_SWITCHER_SPACE_AVATAR.replace("$space",space1.toLowerCase()));

 	}

	/**
	*<li> Case ID:139381.</li>
	*<li> Test Case Name: Space switcher component should display a title.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_SpaceSwitcherComponentShouldDisplayATitle() {
		info("Test 10 Space switcher component should display a title");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to Company wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying Space Switcher*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space Switcher
		*Input Data: 
			
		*Expected Outcome: 
			- Space switcher title is displaying "Select Location"*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		waitForAndGetElement(wHome.ELEMENT_SPACE_SWITCHER_TITLE);

 	}

	/**
	*<li> Case ID:139382.</li>
	*<li> Test Case Name: Space switcher is closed if user is clicking on the close button.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_SpaceSwitcherIsClosedIfUserIsClickingOnTheCloseButton() {
		info("Test 11 Space switcher is closed if user is clicking on the close button");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to Wiki using left sidebar navigation
		*Input Data: 
			
		*Expected Outcome: 
			- Wiki application is displayed
			- Breadcrumb is displayed with space switcher component*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the space switcher
		*Input Data: 
			
		*Expected Outcome: 
			- space switcher is displaying its list
			- space switcher is displaying on the top right, a small "x" icon to close*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click on the "x" icon
		*Input Data: 
			
		*Expected Outcome: 
			- Space switcher is closed (not showing its list anymore)*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		waitForAndGetElement(wHome.ELEMENT_SPACE_SWITCHER_TITLE);
		wHome.closeSpaceWitcher();
		waitForElementNotPresent(wHome.ELEMENT_SPACE_SWITCHER_TITLE);

 	}

	/**
	*<li> Case ID:139383.</li>
	*<li> Test Case Name: Space switcher is closed if user is clicking outside the list.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_SpaceSwitcherIsClosedIfUserIsClickingOutsideTheList() {
		info("Test 12 Space switcher is closed if user is clicking outside the list");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to Wiki using left sidebar navigation
		*Input Data: 
			
		*Expected Outcome: 
			- Wiki application is displayed
			- Breadcrumb is displayed with space switcher component*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the space switcher
		*Input Data: 
			
		*Expected Outcome: 
			- space switcher is displaying its list*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Click outiside the list
		*Input Data: 
			
		*Expected Outcome: 
			- Space switcher is closed (not showing its list anymore)*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		waitForAndGetElement(wHome.ELEMENT_SPACE_SWITCHER_TITLE);
		wHome.closeSpaceSwitcherByClickOutSide();

 	}

	/**
	*<li> Case ID:139384.</li>
	*<li> Test Case Name: Space switcher is displaying "Intranet" when user is browsing the company wiki.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_SpaceSwitcherIsDisplayingIntranetWhenUserIsBrowsingTheCompanyWiki() {
		info("Test 13 Space switcher is displaying Intranet when user is browsing the company wiki");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- connect to Intranet
		*Input Data: 
			
		*Expected Outcome: 
			- left sidebar navigation is displayed*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- using the left sidebar navigation, go into the company wiki
		*Input Data: 
			
		*Expected Outcome: 
			- wiki's breadcrumb is showing the space switcher component with the label "Intranet"*/
		hp.goToWiki();
		wValidate.verifyBreadCrumbePath("Intranet","Wiki Home");

 	}

	/**
	*<li> Case ID:139385.</li>
	*<li> Test Case Name: Space switcher is displaying "My Wiki" for user personal wiki.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_SpaceSwitcherIsDisplayingMyWikiForUserPersonalWiki() {
		info("Test 14 Space switcher is displaying My Wiki for user personal wiki");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- connect to Intranet
		*Input Data: 
			
		*Expected Outcome: 
			- Intranet is displaying top navigation bar*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- using the top navigation bar, go into user menu> Wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Personal wiki is displayed*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- check display of "My Wiki" on Space Switcher
		*Input Data: 
			
		*Expected Outcome: 
			- Space Switcher component is displayed in the breadcrumb with value "My Wiki"*/ 
		hp.goToWiki();
		wValidate.verifyBreadCrumbePath("Intranet","Wiki Home");
		navTool.goToMyWiki();
		wValidate.verifyBreadCrumbePath("My Wiki","Wiki Home");

 	}

	/**
	*<li> Case ID:139386.</li>
	*<li> Test Case Name: Space switcher is displaying a list when opening it.</li>
	*<li> Pre-Condition: User is member of no spaceUser is connect to the intranet</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_SpaceSwitcherIsDisplayingAListWhenOpeningIt() {
		info("Test 15 Space switcher is displaying a list when opening it");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to Company Wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying the space switcher*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Click on the space switcher
		*Input Data: 
			
		*Expected Outcome: 
			- A list is opened*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wValidate.verifyPresentSpaceSwitcher("Intranet");
		wValidate.verifyPresentSpaceSwitcher("My Wiki");
		wHome.closeSpaceWitcher();

 	}

	/**
	*<li> Case ID:139387.</li>
	*<li> Test Case Name: Space switcher is displaying the name of the space if browsing a space's wiki.</li>
	*<li> Pre-Condition: User is member of a space named: Mobile</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_SpaceSwitcherIsDisplayingTheNameOfTheSpaceIfBrowsingASpacesWiki() {
		info("Test 16 Space switcher is displaying the name of the space if browsing a space's wiki");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- connect to Intranet
		*Input Data: 
			
		*Expected Outcome: 
			- Intranet is displaying left sidebar navigation*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- using the left sidebar navigation, go into the space "Mobile"
		*Input Data: 
			
		*Expected Outcome: 
			- Intranet is displaying space navigation*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- using space navigation, go into wiki application
		*Input Data: 
			
		*Expected Outcome: 
			- Space Switcher component is displayed in the breadcrumb with value "Mobile"*/ 
		hp.goToSpecificSpace(space1);
		spaHome.goToWikiTab();
		wValidate.verifyBreadCrumbePath(space1,"Wiki Home");

 	}

	/**
	*<li> Case ID:139388.</li>
	*<li> Test Case Name: Space switcher is displaying the name of the space if browsing a space's wiki in its list.</li>
	*<li> Pre-Condition: User is member of a space named: Mobile</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test17_SpaceSwitcherIsDisplayingTheNameOfTheSpaceIfBrowsingASpacesWikiInItsList() {
		info("Test 17 Space switcher is displaying the name of the space if browsing a space's wiki in its list");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- connect to Intranet
		*Input Data: 
			
		*Expected Outcome: 
			- Intranet is displaying left sidebar navigation*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- using the left sidebar navigation, go into the space "Mobile"
		*Input Data: 
			
		*Expected Outcome: 
			- Intranet is displaying space navigation*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- using space navigation, go into wiki application
		*Input Data: 
			
		*Expected Outcome: 
			- Space Switcher component is displayed in the breadcrumb with value "Mobile"*/

		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- Open the Space switcher
		*Input Data: 
			
		*Expected Outcome: 
			- In the list of spaces, "Mobile" is the first one on the list*/
		hp.goToSpecificSpace(space1);
		spaHome.goToWikiTab();
		wValidate.verifyBreadCrumbePath(space1,"Wiki Home");
		wHome.goToSpaceSwitcher();
		wValidate.verifyPresentSpaceSwitcher("Intranet");
		wValidate.verifyPresentSpaceSwitcher("My Wiki");
		wValidate.verifyPositionOfASpaceInList(1, space1);

 	}

	/**
	*<li> Case ID:139389.</li>
	*<li> Test Case Name: Space switcher list should display "Intranet".</li>
	*<li> Pre-Condition: User is member of 0 spaceUser has not browsed any space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test18_SpaceSwitcherListShouldDisplayIntranet() {
		info("Test 18 Space switcher list should display Intranet");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to Company wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying Space Switcher*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space Switcher
		*Input Data: 
			
		*Expected Outcome: 
			- The first item of the list is "Intranet"
			- Before "Intranet", an icon is displayed (as in the test case description)*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		waitForAndGetElement(wHome.ELEMENT_SPACE_SWITCHER_INTRANET_POSITION);
		waitForAndGetElement(wHome.ELEMENT_SPACE_SWITCHER_INTRANET_ICON);

 	}

	/**
	*<li> Case ID:139390.</li>
	*<li> Test Case Name: Space switcher list should display "My Wiki".</li>
	*<li> Pre-Condition: User is member of 0 Spaces</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test19_SpaceSwitcherListShouldDisplayMyWiki() {
		info("Test 19 Space switcher list should display My Wiki");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to Company wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying Space Switcher*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space Switcher
		*Input Data: 
			
		*Expected Outcome: 
			- The second item of the list is "My Wiki"
			- An icon is displayed before "My Wiki" (as in the description of the test case)*/ 
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		waitForAndGetElement(wHome.ELEMENT_SPACE_SWITCHER_MY_WIKI_POSITION);
		waitForAndGetElement(wHome.ELEMENT_SPACE_SWITCHER_MY_WIKI_ICON);

 	}

	/**
	*<li> Case ID:139391.</li>
	*<li> Test Case Name: Space Switcher must only display spaces where wiki application exist.</li>
	*<li> Pre-Condition: User is member of some spaces
	*User is administrator of space "Without Wiki", where wiki application has been removed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test20_SpaceSwitcherMustOnlyDisplaySpacesWhereWikiApplicationExist() {
		info("Test 20 Space Switcher must only display spaces where wiki application exist");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- From left sidebar navigation, go into the space "Without Wiki"
		*Input Data: 
			
		*Expected Outcome: 
			- Space "Without Wiki" is displayed
			- Wiki application is not available in the space navigation menu*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);
		
		info("Remove wiki application");
		hp.goToSpecificSpace(space1);
		spaHome.goToSpaceSettingTab();
		spSettingMg.goToApplicationTab();
		spSettingMg.removeApplication("Wiki");
		
		info("Verify that Wiki application on the menu is removed");
		driver.navigate().refresh();
		waitForElementNotPresent(spaHome.ELEMENT_SPACE_WIKI_TAB);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- From left sidebar navigation, go into the wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Wiki application is displayed
			- Breadcrumb is displayed with the space switcher component*/
		info("Go to the wiki of the intranet");
		hp.goToWiki();
		wValidate.verifyBreadCrumbePath("Intranet","Wiki Home");

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Open the space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- In the list of spaces, "Without Wiki" is not displayed*/
		wHome.goToSpaceSwitcher();
		wValidate.verifyNotPresentSpaceSwitcher(space1);

		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- in the filter, type "Without"
		*Input Data: 
			
		*Expected Outcome: 
			- In the list of spaces, "Without Wiki" is NOT displayed*/
		wHome.inputSpaceSwitcher(space1);
		wValidate.verifyNotPresentSpaceSwitcher(space1);
		wHome.closeSpaceWitcher();

 	}

	/**
	*<li> Case ID:139392.</li>
	*<li> Test Case Name: Space switcher should display last browsed spaces.</li>
	*<li> Pre-Condition: User is member of spaces :
	- Mobile
	- Space 1
	- Space 2
	- Space 3</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test21_SpaceSwitcherShouldDisplayLastBrowsedSpaces() {
		info("Test 21 Space switcher should display last browsed spaces");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to the intranet
		*Input Data: 
			
		*Expected Outcome: 
			- Intranet is displaying left sidebar with list of spaces*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Using left sidebar, go into Mobile space
			- Using left sidebar, go into Space 1 
			- Using left sidebar, go into Space 2 
			- Using left sidebar, go into Space 3
		*Input Data: 
			
		*Expected Outcome: 
			- Intranet is displaying activity stream of Space 3*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Create a space 1");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);
		
		info("Create a space 2");
		String space2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space2,space2,6000);
		Utils.pause(2000);
		
		info("Create a space 3");
		String space3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space3,space3,6000);
		Utils.pause(2000);
		
		info("Create a space 4");
		String space4 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space4,space4,6000);
		Utils.pause(2000);

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Go into Space 3 wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Intranet is displaying wiki of Space 3
			- Space switcher is displayed in the wiki's breadcrumb*/
		
		info("Go to Space 4");
		spaHome.goToWikiTab();
		wValidate.verifyBreadCrumbePath(space4,"Wiki Home");
		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- Open Space Switcher
		*Input Data: 
			
		*Expected Outcome: 
			- The list of spaces is displaying latest browse space :* Space 3* Space 2* Space 1* Mobile*/ 
		wHome.goToSpaceSwitcher();
		wValidate.verifyPositionOfASpaceInList(1, space4);
		wValidate.verifyPositionOfASpaceInList(2, space3);
		wValidate.verifyPositionOfASpaceInList(3, space2);
		wValidate.verifyPositionOfASpaceInList(4, space1);

 	}

	/**
	*<li> Case ID:139393.</li>
	*<li> Test Case Name: space switcher should not display more than 10 lastest browsed spaces.</li>
	*<li> Pre-Condition: User is member of:Mobile, Space 1, Space 2, Space 3, Space 4,
	* Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test22_SpaceSwitcherShouldNotDisplayMoreThan10LastestBrowsedSpaces() {
		info("Test 22 space switcher should not display more than 10 lastest browsed spaces");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- go to intranet
		*Input Data: 
			
		*Expected Outcome: 
			- Left sidebar is displayed*/

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open Space 11
			- Open Space 10
			- Open Space 9
			- Open Space 8
			- Open Space 7
			- Open Space 6
			- Open Space 5
			- Open Space 4
			- Open Space 3
			- Open Space 2
			- Open Space 1
			- Open Mobile
		*Input Data: 
			
		*Expected Outcome: 
			- Space Mobile is opened on its activity stream*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		String spaceName = "Space"+getRandomString();
		ArrayList<String>array= getListData(spaceName,12);
		for(int i=0;i<array.size();i++){
			info("Create a space:"+array.get(i));
			hp.goToMySpaces();
			spaMg.addNewSpaceSimple(array.get(i),array.get(i),6000);
			Utils.pause(2000);
			
		}
		

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Open wiki application of Mobile Space
		*Input Data: 
			
		*Expected Outcome: 
			- Wiki is opened in the space Mobile
			- Breadcrumb is displaying space switcher component*/
		info("Open wiki application of last space");
		spaHome.goToWikiTab();
		wValidate.verifyBreadCrumbePath(array.get(10),"Wiki Home");
		
		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- Open Space Switcher list
		*Input Data: 
			
		*Expected Outcome: 
			The list of last browsed space is displaying :
			- Mobile
			- Space 1
			- Space 2
			- Space 3
			- Space 4
			- Space 5
			- Space 6 
			- Space 7
			- Space 8
			- Space 9*/ 
		wHome.goToSpaceSwitcher();
		wValidate.verifyPositionOfASpaceInList(1,array.get(10));
		wValidate.verifyPositionOfASpaceInList(2,array.get(9));
		wValidate.verifyPositionOfASpaceInList(3,array.get(8));
		wValidate.verifyPositionOfASpaceInList(4,array.get(7));
		wValidate.verifyPositionOfASpaceInList(5,array.get(6));
		wValidate.verifyPositionOfASpaceInList(6,array.get(5));
		wValidate.verifyPositionOfASpaceInList(7,array.get(4));
		wValidate.verifyPositionOfASpaceInList(8,array.get(3));
		wValidate.verifyPositionOfASpaceInList(9,array.get(2));
		wValidate.verifyPositionOfASpaceInList(10,array.get(1));
 	}}