package org.exoplatform.selenium.platform.plf.functional.unifiedsearch;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.wiki.Template;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * 
 * @author mugnierCh
 * @date 30/09/2014
 *
 */
public class PLF_UnifiedSearch_SearchResults extends Template {

	//Platform
	NavigationToolbar naviToolbar;
	ManageAccount magAcc;
	SettingSearchPage qsPage;
	ActionBar actBar;
	ContentTemplate conTemp;
	ManageMember magMember;
	ContextMenu cMenu;
	SitesExplorer siteExp;
	Event evt;
	Task tsk;
	ForumManageTopic mngTopic;
	ForumBase frmBase;
	ForumManageCategory mngCat;
	PageEditor pEditor;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		info("Login with " + DATA_USER1);
		magAcc = new ManageAccount(driver, this.plfVersion);
		naviToolbar = new NavigationToolbar(driver, this.plfVersion);
		qsPage = new SettingSearchPage(driver);
		actBar = new ActionBar(driver, this.plfVersion);
		cMenu = new ContextMenu(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		conTemp = new ContentTemplate(driver, this.plfVersion);
		magMember = new ManageMember(driver, this.plfVersion);
		evt = new Event(driver, this.plfVersion);
		tsk = new Task(driver, this.plfVersion);
		siteExp= new SitesExplorer(driver, this.plfVersion);
		mngTopic = new ForumManageTopic(driver, this.plfVersion);
		mngCat = new ForumManageCategory(driver, this.plfVersion);
		pEditor = new PageEditor(driver, this.plfVersion);
		
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}


	@Test
	/**
	 * == Check uri of search results page ==
	 * Test case ID: 121095
	 * Step 1: From quick search field, type anything / From keyboard, hit "Enter"
	 * Step 2: Go to homepage / In browser location bar, type : /portal/intranet/search after the host:port (ex: http://localhost:8080/portal/intranet/search)
	 */
	public void test01_CheckUriOfSearchResultsPage(){
		String search = "test121095";

		//Step 1: From quick search field, type anything / From keyboard, hit "Enter"
		qsPage.quickSearchType(search);
		driver.findElement(ELEMENT_QUICK_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);
		info("Search by typing 'enter'");


		//Step 2: Go to homepage / In browser location bar, type : /portal/intranet/search after the host:port (ex: http://localhost:8080/portal/intranet/search)
		click(ELEMENT_HOME_PAGE);
		Utils.pause(1000);
		driver.get(DEFAULT_BASEURL+"/intranet/search");
		info("go to the URL /intranet/search");

		//check result
		waitForAndGetElement(By.xpath("//*[@class = 'navItemSelected' and text()='Search Result']"));
		info("test succeed !");

	}

	@Test
	/**
	 * == Check search results page ui ==
	 * Test case ID: 121073
	 * Step 1:  Enter 'sample' in quicksearch field, then hit Enter key
	 */
	public void test02_CheckSearchResultsPageUi(){
		String search = "121073";
		String titleCat = "cat 121073";
		String titleForum = "forum 121073";
		String titleTop = "topic 121073";

		//Create data
		mngTopic.goToForums();
		info("Create a topic");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 

		//Step 1:  Enter 'sample' in quicksearch field, then hit Enter key
		qsPage.quickSearchType(search);
		waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);
		info("Search by typing 'enter'");
		Utils.pause(1000);

		//check the result
		waitForAndGetElement(qsPage.ELEMENT_SEARCH_TEXTBOX);
		waitForAndGetElement(qsPage.ELEMENT_SEARCH_BUTTON);
		waitForAndGetElement(By.xpath("//*[@id = 'resultHeader' and contains(text(), 'Results 1 to')]"));
		waitForAndGetElement(qsPage.ELEMENT_RESULT_FORUM_ICON);
		waitForAndGetElement(qsPage.ELEMENT_RESULT_ITEM.replace("${item}", titleTop).replace("${keySearch}", search));
		info("test succeed !");

		//clear the data
		mngTopic.goToForums();
		click(mngTopic.ELEMENT_CATEGORY_BREADCUMB.replace("${category}",titleCat));
		mngCat.deleteCategoryInForum(titleCat);

	}

	@Test
	/**
	 * == Display of file results sizes ==
	 * Test case ID: 121311
	 * Step 1: Search for 'search_results_06'
	 */
	public void test03_DisplayOfFileResultsSizes(){
		String search = "ECMS_DMS_SE_BasicAction_cutPaste";
		String file1 = "ECMS_DMS_SE_BasicAction_CutPaste_03_des.png";
		String file2 = "ECMS_DMS_SE_BasicAction_CutPaste.png";
		String file3 = "ECMS_DMS_SE_BasicAction_CutPaste_04_des.zip";

		naviToolbar.goToSiteExplorer();
		actBar.uploadFile("TestData/" + file1);
		actBar.uploadFile("TestData/" + file2);
		actBar.uploadFile("TestData/" + file3);
		info("items upload");

		//Step 1: Search for 'search_results_06'
		qsPage.quickSearch(search);
		info("searching");
		Utils.pause(4000);

		//check the result
		waitForAndGetElement(By.xpath("//*[@class='detail' and contains(text(), '142,3 KB')]"));
		waitForAndGetElement(By.xpath("//*[@class='detail' and contains(text(), '338,4 KB')]"));
		waitForAndGetElement(By.xpath("//*[@class='detail' and contains(text(), '1 KB')]"));
		info("test succeed");

		//clear data
		naviToolbar.goToSiteExplorer();
		cMenu.deleteDocument(By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", file1)));
		cMenu.deleteDocument(By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", file2)));
		cMenu.deleteDocument(By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", file3)));
		info("data cleared");

	}
	
	@Test(groups="pending")
	/**
	 * == Search results are sorted by pertinence ==
	 * Test case ID: 121716
	 * Step 1: Connect to Intranet / from navigation bar, input "cloud", in the Quick search box
	 */
	public void test04_SearchResultsAreSortedByPertinence(){
		String search = "cloud";

		createData();

		/*Step 1: Connect to Intranet / from navigation bar, input "cloud", in the Quick search box*/
		qsPage.quickSearch(search);
		info("searching");
		Utils.pause(1000);

		//check the result
		click(qsPage.ELEMENT_SORT_DROPDOWN);
		waitForAndGetElement(By.xpath("//*[@id='sortField' and @sort='relevancy']"));
		waitForAndGetElement(By.xpath("//*[@class='uiIconSortDown']"));
		info("test succeed");

		deleteData();
	}

	@Test
	/**
	 * == Display the button "Show More Results" ==
	 * Test case ID: 121349
	 * Step 1: Connect to Intranet / From navigation bar, input 'cloud' in the quick search box / Click on "See All  Search Result "
	 * Step 2: Click on "Show More Results"
	 */
	/**
	 * == Sort by relevancy by default ==
	 * Test case ID: 121789
	 * Step 1: Connect to Intranet / From Navigation bar, input "cloud" in the Quick Search box / Hit the key "Enter"
	 */
	/**
	 * == Hide the button "Show More Results" ==
	 * Test case ID: 121498
	 * Step 1: Connect to Intranet / From navigation bar, input 'cloud' in the quick search box / Click on "See All  Search Result 
	 * Step 2: Click on "Show More Results"
	 * Step 3: Click again on the button "Show More Results"	
	 */
	public void test05_06_09_DisplayTheButtonShowMoreResults(){
		String search = "cloud";

		createData();

		/*Step 1: Connect to Intranet / From navigation bar, input 'cloud' in the quick search box / Click on "See All  Search Result " */
		qsPage.quickSearch(search);
		info("searching");
		waitForAndGetElement(By.xpath("//div[@id='result']/div[5]"));
		waitForAndGetElement(By.xpath("//div[@id='result']/div[10]"));
		/*Step 2: Click on "Show More Results" */
		click(By.xpath("//*[@id='btnShowMore']"));
		info("display more results");
		// Check the result
		waitForAndGetElement(By.xpath("//div[@id='result']/div[5]"));
		waitForAndGetElement(By.xpath("//div[@id='result']/div[10]"));
		waitForAndGetElement(By.xpath("//div[@id='result']/div[11]"));
		info("test succeed");
		
		/*Step 1: Connect to Intranet / From Navigation bar, input "cloud" in the Quick Search box / Hit the key "Enter"*/
		qsPage.quickSearch(search);
		info("Search by typing 'enter'");
		Utils.pause(1000);

		//check the result
		waitForAndGetElement(By.xpath("//*[@id='sortField' and @sort='relevancy']"));
		info("test succeed");
		
		/* Step 2: Click on "Show More Results" */
		click(qsPage.ELEMENT_SHOW_MORE_RESULT);
		info("display more results");
		Utils.pause(2000);

		/* Step 3: Click again on the button "Show More Results" */
		click(qsPage.ELEMENT_SHOW_MORE_RESULT);
		// Check the result
		//		waitForAndGetElement(By.xpath("//*[@id='btnShowMore']"));
		info("test succeed");
		waitForElementNotPresent(qsPage.ELEMENT_SHOW_MORE_RESULT);
		
		deleteData();
	}

	@Test(groups="pending")
	/**
	 * == Display searching during search ==
	 * Test case ID: 121150
	 * PENDING: Can't locate element "Searching...."
	 */
	public void test07_DisplaySearchingDuringSearch(){

		String search = "clo";
		String search2 = "search";

		qsPage.quickSearch(search);
		Utils.pause(1000);
		qsPage.searchInSearchPage(search2);


	}

	@Test
	/**
	 * == Check Search results page URL ==
	 * Test case ID: 121074
	 * Step 1: Connect to Intranet / From Navigation bar, Enter 'clo' in quicksearch field, then hit Enter key

	 */
	public void test08_CheckSearchResultsPageURL(){
		String search = "clo";

		/* Step 1: Connect to Intranet / From Navigation bar, Enter 'clo' in quicksearch field, then hit Enter key */
		qsPage.quickSearchType(search);
		driver.findElement(ELEMENT_QUICK_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);
		info("Search by typing 'enter'");
		Utils.pause(2000);

		// get the URL
		String theURL = driver.getCurrentUrl();
		info("get the URL of the current page");

		// check the result
		if(theURL.contains("q=") && (theURL.contains("types=")))
		{
			info("test succeed");
		}
		Utils.pause(2000);
	}

	@Test
	/**
	 * == Display the list sort by ==
	 * Test case ID: 121388
	 * Step 1: Connect to Intranet / From Navigation bar, input "cloud" in the Quick Search box / Hit the key "Enter"
	 * Step 2: Click on the arrow beside "$order"
	 */
	public void test10_DisplayTheListSortBy(){
		String search = "Test104327";
		String relevance = "Relevance";
		String date = "Date";
		String title = "Title";
		String contentName="Test104327";

		//create the data
		naviToolbar.goToSiteExplorer();
		actBar.addItem2ActionBar("addDocument", actBar.ELEMENT_NEW_CONTENT_LINK);
		actBar.goToAddNewContent();
		conTemp.createNewWebContent(contentName, contentName, "", "", "", "");
		info("new web content created");

		/* Step 1: From navigation bar, input 'cloud' in the quick search box / Hit the key "Enter" */
		qsPage.quickSearchType(search);
		driver.findElement(ELEMENT_QUICK_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);
		info("Search by typing 'enter'");
		Utils.pause(2000);

		/*Step 2: Click on the arrow beside "$order"*/
		click(qsPage.ELEMENT_SORT_DROPDOWN);
		info("display the list of element's sorting");

		//check the result
		waitForAndGetElement(By.xpath((qsPage.ELEMENT_SORT_ITEM_OPTION).replace("${sortItem}", relevance)));
		waitForAndGetElement(By.xpath((qsPage.ELEMENT_SORT_ITEM_OPTION).replace("${sortItem}", date)));
		waitForAndGetElement(By.xpath((qsPage.ELEMENT_SORT_ITEM_OPTION).replace("${sortItem}", title)));
		info("test succeed");

	}

	@Test
	/**
	 * == Display search results settings screen ==
	 * Test case ID: 121149
	 * Step 1: Connect to Intranet / From Navigation bar, input data in the Quick Search box
	 * Step 2: Select the result item with the mouse
	 * Step 3: From Navigation bar, choose "Edit/Page/Edit Layout"
	 * Step 4: Edit the portlet "Search"
	 */
	public void test11_DisplaySearchResultsSettingsScreen(){
		String search = "cloud";

		/* Step 1: Connect to Intranet / From Navigation bar, input data in the Quick Search box */
		/*Step 2: Select the result item with the mouse*/
		qsPage.quickSearch(search);
		info("searching");
		Utils.pause(2000);

		/*Step 3: From Navigation bar, choose "Edit/Page/Edit Layout"*/
		naviToolbar.goToEditLayout();
		info("go to the edit layout page");
		waitForAndGetElement(qsPage.ELEMENT_SEARCH_PORTLET);
		pEditor.goToEditPortlet(qsPage.ELEMENT_SEARCH_PORTLET);
		//check the result
		waitForAndGetElement(By.xpath("//*[@class='PopupTitle popupTitle' and text()='Page Editor']"));
		waitForAndGetElement(By.xpath("//*[@class='active']//*[text()='Edit Mode']"));
		info("test succeed");
	}

	@Test
	/**
	 * == Display the list result per page ==
	 * Test case ID: 121386
	 * Step 1: Connect to Intranet / From Navigation bar, input data in the Quick Search box / Select the result item with the mouse
	 * Step 2: From Navigation bar, choose "Edit/Page/Edit Layout"
	 * Step 3: Edit the portlet "Search"
	 * Step 4: Click on the arrow from the pulldown
	 */
	public void test12_DisplayTheListResultPerPage(){
		String search = "cloud";
		int i = 0;
		int[] number = {5, 10, 20, 50, 100};

		/* Step 1: Connect to Intranet / From Navigation bar, input data in the Quick Search box / Select the result item with the mouse*/
		qsPage.quickSearch(search);
		info("searching");
		Utils.pause(2000);

		/*Step 2: From Navigation bar, choose "Edit/Page/Edit Layout"*/
		naviToolbar.goToEditLayout();
		info("go to the edit layout page");


		/*Step 3: Edit the portlet "Search" */
		qsPage.goToEditSearchPortlet();
		info("go to the edit 'search page' page");

		/*Step 4: Click on the arrow from the pulldown */
		click(qsPage.ELEMENT_RESULTS_PER_PAGE_DROPDOWN,2);
		info("display the number of items list");

		//check the result
		for(i = 0; i>=4; i++) {
			waitForAndGetElement(By.xpath((qsPage.ELEMENT_RESULT_PER_PAGE_OPTION).replace("${resultsPerPage}", ""+number[i]+"")));
		}
		info("test succeed");
	}

	@Test
	/**
	 * == Return all types in search result ==
	 * Test case ID: 121698
	 * Step 1: Connect to Intranet / In the search box, input "Clo"
	 * can search with seperate word, cannot search with contain word
	 */
	public void test13_ReturnAllTypesInSearchResult(){
		String search = "clo";
		String spaceName = "clo space";
		String wikiName = "clo wiki";
		String contentName = "clo content";
		String eventName = "clo event";
		String taskName = "clo task";
		String timeZone = "(GMT +07:00) Asia/Saigon";
		String titleCat = "clo cat";
		String titleForum = "clo forum";
		String titleTop = "clo topic";

		//Add new topic
		//Create data
		mngTopic.goToForums();
		info("Create a topic");

		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop); 

		//Add new WebContent
		naviToolbar.goToSiteExplorer();
		actBar.addItem2ActionBar("addDocument", actBar.ELEMENT_NEW_CONTENT_LINK);
		actBar.goToAddNewContent();
		conTemp.createNewWebContent(contentName, contentName, "", "", "", "");
		info("new web content created");

		//Add wiki page
		info("Add new wiki page");		
		goToWiki();
		addBlankWikiPage(wikiName, wikiName, 0);
		info("new wiki page created");

		//Add space
		info("-- Create space --");
		magMember.goToMySpacePage();
		magMember.addNewSpace(spaceName, spaceName);
		info("new space created");

		//Add event and task
		evt.goToCalendarPage();
		evt.setTimezoneForCalendar(timeZone);
		evt.addQuickEvent(eventName,eventName,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),true);
		tsk.addQuickTask(taskName,taskName,getDate(2,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),true);
		info("new event created");
		info("new task created");

		//Step 1: Connect to Intranet / In the search box, input "Clo"
		qsPage.quickSearch(search);
		info("searching");
		Utils.pause(1000);
		//check the result
		waitForAndGetElement(By.xpath(("//*[@class='resultBox clearfix task']/..//*[ contains(text(), '${type}')]").replace("${type}", search)));
		waitForAndGetElement(By.xpath(("//*[@class='resultBox clearfix event']/..//*[ contains(text(), '${type}')]").replace("${type}", search)));
		waitForAndGetElement(By.xpath(("//*[@class='resultBox clearfix space']/..//*[ contains(text(), '${type}')]").replace("${type}", search)));
		waitForAndGetElement(By.xpath(("//*[@class='resultBox clearfix wiki']/..//*[ contains(text(), '${type}')]").replace("${type}", search)));
		waitForAndGetElement(By.xpath(("//*[@class='resultBox clearfix document']/..//*[ contains(text(), '${type}')]").replace("${type}", search)));
		waitForAndGetElement(By.xpath(("//*[@class='resultBox clearfix post']/..//*[ contains(text(), '${type}')]").replace("${type}", search)));
		info("test succeed"); 
		//clear data 
		goToWiki();
		click(ELEMENT_NODE_WIKI_PAGE.replace("${node}",wikiName));
		deleteCurrentWikiPage();
		evt.goToCalendarPage();
		Utils.pause(3000);
		evt.deleteEventTask(taskName);
		evt.deleteEventTask(eventName);
		magMember.goToAllSpaces();
		magMember.deleteSpace(spaceName,300000);
		naviToolbar.goToSiteExplorer();
		cMenu.deleteDocument(By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", contentName)));
		mngTopic.goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat);
		info("data cleared");
	}

	/*create the data to run the tests*/
	public void createData(){
		String fileName1 = "cloud";
		int i = 0;

		for(i= 0; i <= 19; i++) {
			naviToolbar.goToSiteExplorer();
			actBar.addItem2ActionBar("addDocument", actBar.ELEMENT_NEW_CONTENT_LINK);
			actBar.goToAddNewContent();

			conTemp.createNewFile(fileName1+""+i, fileName1, fileName1);
			click(naviToolbar.ELEMENT_SITE_EXPLORER_HOME);
		} 
		info("Data created");

	}

	/*delete all the data created by createData*/
	public void deleteData(){
		int j=0;
		String fileName1 ="cloud";

		naviToolbar.goToSiteExplorer();
		for(j= 0; j <= 19; j++) {
			cMenu.deleteDocument(By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", ""+fileName1+""+j)));
			Utils.pause(3000);
		}
		info("all data cleared");
	}

}
