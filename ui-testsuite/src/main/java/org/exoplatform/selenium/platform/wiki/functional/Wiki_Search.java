package org.exoplatform.selenium.platform.wiki.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Utils;
import org.testng.annotations.*;


	public class Wiki_Search extends WIKI_TestConfig{

	/**
	*<li> Case ID:139356.</li>
	*<li> Test Case Name: Search when the keyword is matched.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_SearchWhenTheKeywordIsMatched() {
		info("Test 1: Search when the keyword is matched");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create pages
		*Input Data: 
			- Go to Add Page â†’Blank Page (or From Template)
			- Add values in required fields
			- Click Save
		*Expected Outcome: 
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages is created successful*/
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Do search
		*Input Data: 
			- Put keyword in the search box
			- Press Enter
		*Expected Outcome: 
			The results are matched with the query are listed*/
		info("Do Search");
		wSearch.quickSeach(title);
		info("Verify that the page is shown in searched results");
		wValidate.verifySearchResults(title);

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: View content of result
		*Input Data: 
			- Click on link of one result
		*Expected Outcome: 
			The content of page is displayed*/ 
		info("View content of the page result");
		wSearch.viewContentOfSearchedPage(title, content);

 	}

	/**
	*<li> Case ID:139357.</li>
	*<li> Test Case Name: Search when the keyword is matched.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_SearchWhenTheKeywordIsMatched() {
		info("Test 2: Search when the keyword is matched");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create pages
		*Input Data: 
			- Go to Add Page â†’Blank Page (or From Template)
			- Add values in required fields
			- Click Save
		*Expected Outcome: 
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages is created successful*/
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
		
		info("Create a space 1: moving space");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);
		
		info("Create page A");
		String pageC = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageContent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToSpecificSpace(space1);
		spaHome.goToWikiTab();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(pageC,pageContent);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(pageC);

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Open Advanced search form
		*Input Data: 
			- Put keyword in the search box
			- Press Enter
		*Expected Outcome: 
			The search result screen appears*/
		info("Do Search");
		hp.goToWiki();
		wSearch.quickSeach(title);
		info("Verify that the page is shown in searched results");
		wValidate.verifySearchResults(title);

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Do advanced search
		*Input Data: 
			- Enter keyword in the Search field
			- Select space in the Space field
			- Press Enter
		*Expected Outcome: 
			The results are matched with the query are listed*/
		info("Go to Advanced search");
		wSearch.advancedSearch(pageC,space1);
		info("Verify that the page is shown in searched results");
		wValidate.verifySearchResults(pageC);

		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: View content of result
		*Input Data: 
			- Click on link of page
		*Expected Outcome: 
			Content of page are shown*/ 
		info("View content of the page result");
		wSearch.viewContentOfSearchedPage(pageC,pageContent);

 	}

	/**
	*<li> Case ID:139358.</li>
	*<li> Test Case Name: Search when the keyword is not matched.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_SearchWhenTheKeywordIsNotMatched() {
		info("Test 3: Search when the keyword is not matched");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create pages
		*Input Data: 
			- Go to Add Page â†’Blank Page (or From Template)
			- Add values in required fields
			- Click Save
		*Expected Outcome: 
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages is created successful*/
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Do search
		*Input Data: 
			- Put keyword in the search box
			- Press Enter
		*Expected Outcome: 
			Show message alert that no result found*/ 
		info("Do Search");
		String title1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wSearch.quickSeach(title1);
		info("Verify that the searched results is empty");
		wValidate.verifyEmptySearchResults();

 	}

	/**
	*<li> Case ID:139359.</li>
	*<li> Test Case Name: Search when the keyword is not matched.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_SearchWhenTheKeywordIsNotMatched() {
		info("Test 4: Search when the keyword is not matched");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create pages
		*Input Data: 
			- Go to Add Page â†’Blank Page (or From Template)
			- Add values in required fields
			- Click Save
		*Expected Outcome: 
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages is created successful*/
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Open Advanced search form
		*Input Data: 
			- Put keyword in the search box
			- Press Enter
		*Expected Outcome: 
			The search result screen appears*/
		info("Do Search");
		wSearch.quickSeach(title);
		info("Verify that the page is shown in searched results");
		wValidate.verifySearchResults(title);

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Do advanced search
		*Input Data: 
			- Enter keyword in the Search field
			- Select space in the Space field
			- Press Enter
		*Expected Outcome: 
			Show message alert that no result found*/ 
		info("Go to Advanced search");
		wSearch.advancedSearch(title,"My Wiki");
		info("Verify that the searched results is empty");
		wValidate.verifyEmptySearchResults();

 	}

	/**
	*<li> Case ID:139360.</li>
	*<li> Test Case Name: View content of search result when user does not have permission to view page.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_ViewContentOfSearchResultWhenUserDoesNotHavePermissionToViewPage() {
		info("Test 5: View content of search result when user does not have permission to view page");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create pages
		*Input Data: 
			- Go to Add Page â†’Blank Page (or From Template)
			- Add values in required fields
			- Click Save
		*Expected Outcome: 
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages is created successful*/
		
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Set permission for page
		*Input Data: 
			- Add permission for page that some users/groups can not view and edit this page
		*Expected Outcome: 
			Page is added permission*/
		info("Set permisison for PageD");
		wHome.goToAPage(title);
		wHome.goToPermissions();
		wPermission.deletePermission("any");
		wPermission.savePermisison();
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Do search
		*Input Data: 
			- Login by user does not have permission to view and edit page
			- Put keyword in the search box
			- Press Enter
		*Expected Outcome: 
			Page which user does not have permission is not listed in search result*/
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Do Search");
		hp.goToWiki();
		wSearch.quickSeach(title);
		info("Verify that the searched results is empty");
		wValidate.verifyEmptySearchResults();

 	}

	/**
	*<li> Case ID:139361.</li>
	*<li> Test Case Name: View content of search result when user does not have permission to view page.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_ViewContentOfSearchResultWhenUserDoesNotHavePermissionToViewPage() {
		info("Test 6: View content of search result when user does not have permission to view page");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Create pages
		*Input Data: 
			- Go to Add Page â†’Blank Page (or From Template)
			- Add values in required fields
			- Click Save
		*Expected Outcome: 
			- By default, the [Create Wiki page] is displayed in the [Rich Text] mode
			- New pages is created successful*/
		info("Create 1 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Create a wiki page");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToWiki();
		wHome.goToAddBlankPage();
		richEditor.addSimplePage(title, content);
		wikiMg.saveAddPage();
		Utils.pause(2000);
		wValidate.verifyTitleWikiPage(title);
		arrayPage.add(title);

		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Set permission for page
		*Input Data: 
			- Add permission for page that some users/groups can not view and edit this page
		*Expected Outcome: 
			Page is added permission*/
		
		info("Set permisison for PageD");
		wHome.goToAPage(title);
		wHome.goToPermissions();
		wPermission.deletePermission("any");
		wPermission.savePermisison();

		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Open Advanced search form
		*Input Data: 
			- Login by user does not have permission to view and edit page
			- Put keyword in the search box
			- Press Enter
		*Expected Outcome: 
			The search result screen appears*/
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		info("Do Search");
		hp.goToWiki();
		wSearch.quickSeach(title);
		info("Verify that the searched results is empty");
		wValidate.verifyEmptySearchResults();

		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Do advanced search
		*Input Data: 
			- Enter keyword in the Search field
			- Select space in the Space field
			- Press Enter
		*Expected Outcome: 
			Page which user does not have permission is not listed in search result*/
		info("Go to Advanced search");
		wSearch.advancedSearch(title,"My Wiki");
		info("Verify that the searched results is empty");
		wValidate.verifyEmptySearchResults();


 	}

	/**
	*<li> Case ID:139363.</li>
	*<li> Test Case Name: Search - Space switcher is scrollable when there are more than 10 results.</li>
	*<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
	*<li> Post-Condition: </li>
	*<li> Case ID:139364.</li>
	*<li> Test Case Name: Search doesn't take into account order of words.</li>
	*<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
	*<li> Post-Condition: </li>
	*<li> Case ID:139365.</li>
	*<li> Test Case Name: Search is not case sensitive.</li>
	*<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
	*<li> Post-Condition: </li>
	*<li> Case ID:139366.</li>
	*<li> Test Case Name: Search is working as a filter in the list of spaces.</li>
	*<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
	*<li> Post-Condition: </li>
	*<li> Case ID:139367.</li>
	*<li> Test Case Name: Search should not take into account spaces at the end of a text inputted.</li>
	*<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4,
	* Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
	*<li> Post-Condition: </li>
	*<li> Case ID:139368.</li>
	*<li> Test Case Name: Search should start on first characters inputed.</li>
	*<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
	*<li> Post-Condition: </li>
	*<li> Case ID:139369.</li>
	*<li> Test Case Name: Search should take into account spaces in the middle of text inputted.</li>
	*<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
	*<li> Post-Condition: </li>
	*<li> Case ID:139370.</li>
	*<li> Test Case Name: When no results search result, space switcher should display "No Spaces".</li>
	*<li> Pre-Condition: User is member of : Mobile, Space 1, Space 2, Space 3, Space 4, Space 5, Space 6, Space 7, Space 8, Space 9, Space 10, Space 11, "Long title for a space name 30"</li>
	*<li> Post-Condition: </li>
	*
	*/
	@Test
	public  void test07_14_SearchSpaceSwitcher() {
		info("Test 7: Search - Space switcher is scrollable when there are more than 10 results");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Using left sidebar navigation, go into the wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Wiki is displayed
			- Breadcrumb is displayed with space switcher component*/
		info("Create 2 new users");
		String password="123456";
		navTool.goToAddUser();
		arrayUsers = addUserPage.addUsers(1,password);
		
		info("Login with user 1");
		magAc.signOut();
		magAc.signIn(arrayUsers.get(0), password);
		Utils.pause(2000);
		
		ArrayList<String>array= new ArrayList<String>();
		for(int i=0;i<11;i++){
			info("Create a space");
			String space = "Space "+getRandomNumber();
			hp.goToAllSpace();
			spaMg.addNewSpaceSimple(space, space,6000);
			Utils.pause(2000);
			array.add(space);
		}
		
		info("Create a other space");
		String space1 = "Long title space "+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space1,space1,6000);
		Utils.pause(2000);
		
		info("Create a other space");
		String space2 = "Mobile "+txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space2,space2,6000);
		Utils.pause(2000);

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Input text is displayed*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Input "Spac" in the input text
		*Input Data: 
			
		*Expected Outcome: 
			- List of spaces is updated and displaying :
			* Space 1* Space 2* Space 3* Space 4* Space 5* Space 6* Space 7* Space 8* Space 9* Space 10
			* User can scroll down to see* Space 11* Long title for a space name 30*/
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher("Spac");
		wValidate.verifyPresentSpaceSwitcher(array);
		wValidate.verifyPresentSpaceSwitcher(space1);
		wValidate.verifyNotPresentSpaceSwitcher(space2);
		wValidate.verifyScrollDownOfSpaceSwitcher();
		
		info("Test 8: Search doesn't take into account order of words");
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Input text is displayed*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Input "title" in the input text
		*Input Data: 
			
		*Expected Outcome: 
			- List of spaces is updated and displaying :* "Long title for a space name 30"*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher("title");
		wValidate.verifyNotPresentSpaceSwitcher(array);
		wValidate.verifyPresentSpaceSwitcher(space1);
		wValidate.verifyNotPresentSpaceSwitcher(space2);
		
		info("Test 9: Search is not case sensitive");
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Input text is displayed*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Input "mob" in the input text
		*Input Data: 
			
		*Expected Outcome: 
			- List of spaces is updated and displaying :* Mobile*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher("mob");
		wValidate.verifyNotPresentSpaceSwitcher(array);
		wValidate.verifyPresentSpaceSwitcher(space2);
		wValidate.verifyNotPresentSpaceSwitcher(space1);
		
		info("Test 10 Search is working as a filter in the list of spaces");
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Input text is displayed*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Input "Mo" in the input text
		*Input Data: 
			
		*Expected Outcome: 
			- List of spaces is updated and displaying :* Mobile*/
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher("Mo");
		wValidate.verifyNotPresentSpaceSwitcher(array);
		wValidate.verifyPresentSpaceSwitcher(space2);
		wValidate.verifyNotPresentSpaceSwitcher(space1);

		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- Delete "Mo" from input text
		*Input Data: 
			
		*Expected Outcome: 
			- List of last browsed space is displayed again*/
		wHome.inputSpaceSwitcher(" ");
		wValidate.verifyPresentSpaceSwitcher(array);
		wValidate.verifyPresentSpaceSwitcher(space1);
		wValidate.verifyPresentSpaceSwitcher(space2);

		/*Step number: 5
		*Step Name: 
		*Step Description: 
			- Input "bile"
		*Input Data: 
			
		*Expected Outcome: 
			- List of sapces is updated and displaying : * Mobile*/ 
		wHome.inputSpaceSwitcher("bile");
		wValidate.verifyNotPresentSpaceSwitcher(array);
		wValidate.verifyPresentSpaceSwitcher(space2);
		wValidate.verifyNotPresentSpaceSwitcher(space1);
		
		info("Test 11 Search should not take into account spaces at the end of a text inputted");
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Input text is displayed*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Input "Mo" (with 2 spaces at the end) in the input text
		*Input Data: 
			
		*Expected Outcome: 
			- List of spaces is updated and displaying :* Mobile*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher("Mo");
		wValidate.verifyNotPresentSpaceSwitcher(array);
		wValidate.verifyPresentSpaceSwitcher(space2);
		wValidate.verifyNotPresentSpaceSwitcher(space1);
		
		info("Test 12 Search should start on first characters inputed");
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Input text is displayed*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Input "x" in the input text
		*Input Data: 
			
		*Expected Outcome: 
			- List of spaces is updated*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher("x");
		wValidate.verifyNotPresentSpaceSwitcher(array);
		wValidate.verifyNotPresentSpaceSwitcher(space2);
		wValidate.verifyNotPresentSpaceSwitcher(space1);
		
		info("Test 13 Search should take into account spaces in the middle of text inputted");
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Input text is displayed*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Input "Long title" iin the input text
		*Input Data: 
			
		*Expected Outcome: 
			- List of spaces is updated and displaying :* Long title for a space name 30*/
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher("Long title");
		wValidate.verifyNotPresentSpaceSwitcher(array);
		wValidate.verifyNotPresentSpaceSwitcher(space2);
		wValidate.verifyPresentSpaceSwitcher(space1);

		/*Step number: 4
		*Step Name: 
		*Step Description: 
			- Remove "Long title" from the input text
		*Input Data: 
			
		*Expected Outcome: 
			- List of last browsed spaces is displayed again*/
		wHome.inputSpaceSwitcher(" ");
		wValidate.verifyPresentSpaceSwitcher(array);
		wValidate.verifyPresentSpaceSwitcher(space2);
		wValidate.verifyPresentSpaceSwitcher(space1);

		/*Step number: 5
		*Step Name: 
		*Step Description: 
			- Input "Longtitle" (with 2 spaces between "Long" and "title") in the input text
		*Input Data: 
			
		*Expected Outcome: 
			Space switcher is displaying :
			- "No Spaces"*/ 
		wHome.inputSpaceSwitcher("Longtitle");
		wValidate.verifyNotPresentSpaceSwitcher(array);
		wValidate.verifyNotPresentSpaceSwitcher(space2);
		wValidate.verifyNotPresentSpaceSwitcher(space1);
		
		info("Test 14 When no results search result, space switcher should display No Spaces");
		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space switcher component
		*Input Data: 
			
		*Expected Outcome: 
			- Input text is displayed*/

		/*Step number: 3
		*Step Name: 
		*Step Description: 
			- Input "x" in the input text
		*Input Data: 
			
		*Expected Outcome: 
			- List of spaces is updated and displaying "No Spaces"*/ 
		hp.goToWiki();
		wHome.goToSpaceSwitcher();
		wHome.inputSpaceSwitcher("x");
		wValidate.verifyNotPresentSpaceSwitcher(array);
		wValidate.verifyNotPresentSpaceSwitcher(space2);
		wValidate.verifyNotPresentSpaceSwitcher(space1);
 	}
	/**
	*<li> Case ID:139371.</li>
	*<li> Test Case Name: When user is member of at least one space, input text should be displayed.</li>
	*<li> Pre-Condition: User is member of space "Mobile"</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_WhenUserIsMemberOfAtLeastOneSpaceInputTextShouldBeDisplayed() {
		info("Test 15 When user is member of at least one space, input text should be displayed");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Go to Company wiki
		*Input Data: 
			
		*Expected Outcome: 
			- Company wiki is displayed
			- Breadcrumb is displaying Space Switcher*/
		info("Go to space switcher");
		hp.goToWiki();
		wHome.goToWikiHomeOfSpaceFromBreadcrumb("My Wiki", "John");

		/*Step number: 2
		*Step Name: 
		*Step Description: 
			- Open the Space Switcher
		*Input Data: 
			
		*Expected Outcome: 
			- The first item of the list is an input text field*/ 
		info("Verify that The first item of the list is an input text field");
		waitForAndGetElement(wHome.ELEMENT_WIKI_SEARCH_FIELD);

 	}}