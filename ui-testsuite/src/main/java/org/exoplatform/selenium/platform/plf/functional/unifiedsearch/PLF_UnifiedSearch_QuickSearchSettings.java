package org.exoplatform.selenium.platform.plf.functional.unifiedsearch;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.HomePageGadget;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.PageManagement;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.Utils;

public class PLF_UnifiedSearch_QuickSearchSettings extends Activity {

	EcmsBase acme;
	ManageAccount magAcc;
	ManageAccount magAcc1;
	ManageMember magMember;
	ManageMember magMember1;
	ForumManageTopic mngTopic;
	ContentTemplate conTemp;
	Event evt;
	ActionBar actBar;
	Event evt1;
	Task task;
	ContextMenu cMenu;
	BasicAction ba;
	SettingSearchPage qsPage;
	HomePageGadget hGadget; 
	NavigationToolbar naviToolbar;
	SitesExplorer siteExp;
	ForumManageCategory mngCat;
	UserGroupManagement userGroup;
	PageEditor pagEditor;
	PageManagement pageMag;
	public ManageAlert alert ;
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		hGadget = new HomePageGadget(driver);
		magMember = new ManageMember(driver,this.plfVersion);
		mngTopic = new ForumManageTopic(driver, this.plfVersion);
		naviToolbar = new NavigationToolbar(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		cMenu = new ContextMenu(driver, this.plfVersion);
		pagEditor = new PageEditor(driver);
		conTemp = new ContentTemplate(driver, this.plfVersion);
		evt = new Event(driver, this.plfVersion);
		task = new Task(driver, this.plfVersion);
		qsPage = new SettingSearchPage(driver);
		ba  = new BasicAction(driver);
		button = new Button(driver);
		pageMag = new PageManagement(driver);
		siteExp = new SitesExplorer(driver, this.plfVersion);
		mngCat = new ForumManageCategory(driver, this.plfVersion);
		userGroup = new UserGroupManagement(driver);
		acme = new EcmsBase(driver, this.plfVersion);
		alert = new ManageAlert(driver);
	}
	
	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();		
		driver.quit();
	}

	/*
	 * ==Display "Quick Search Settings" screen==
	 * Test ID : 121147
	 */
	@Test
	public void test01_DisplayQuickSearchSettings(){
		String pageSearch="searchPage1";
		String qsGadget = "Quick Search";
		/*
		 * step 1
		 */
		qsPage.addQuickSearchPage(pageSearch,qsGadget);

		/*
		 * step 2
		 */
		naviToolbar.goToEditLayout();
		click(ELEMENT_CATEGORY_TOOLS);
		click(ELEMENT_SWITCH_VIEW_MODE);
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);
		/*
		 * step 3
		 */
		waitForAndGetElement(ELEMENT_EDIT_PORTLET_ICON, DEFAULT_TIMEOUT, 1);
		click(ELEMENT_EDIT_PORTLET_ICON);
		// check if the edit mode is display
		waitForAndGetElement(qsPage.ELEMENT_PORTLET_SETTING_TAB);
		info("Test successfully");
		
		click(button.ELEMENT_CLOSE_BUTTON);
		pagEditor.finishEditLayout();
		pageMag.deletePageAtManagePageAndPortalNavigation(pageSearch, true, "intranet", false, "Administration");
	}
	/*
	 * Display floating result after uncheck the box "Search in current site only"
	 * Id Test : 121270
	 */
	@Test
	public void test02_DisplayFloatingResultAfterUncheckTheBoxSearchInCurrentSiteOnly(){
		String pageSearch="recherchePage2";
		String qsGadget = "Quick Search";
		String research = "Miller";
		String result="Jack Miller";
		/*
		 * step 1 & 2
		 */
		qsPage.addQuickSearchPage(pageSearch,qsGadget);
		qsPage.goToEditSearchPortlet();
		waitForAndGetElement(ELEMENT_EDIT_MODE_TAB).click();
		check(qsPage.ELEMENT_SEARCH_ALL_CHECKBOX,2);
		/*
		 * step 3
		 */
		uncheck(qsPage.ELEMENT_SEARCH_CURRENT_SITE_ONLY_CHECKBOX,2);
		Utils.pause(2000);
		click(qsPage.ELEMENT_SAVE_SETTING);
		Utils.pause(2000);
		alert.acceptAlert();
		Utils.pause(2000);
		button.close();
		pagEditor.finishEditLayout();
		/*
		 * step 4
		 */
		click(qsPage.ELEMENT_QUICKSEARCH_NEW_PAGE);
		WebElement searchBox = waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_TEXTBOX_NEW_PAGE);
		searchBox.sendKeys(research);
		Utils.pause(2000);
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","1").replace("${name}",result)));
		info("Test successfully");
		
		pageMag.deletePageAtManagePageAndPortalNavigation(pageSearch, true, "intranet", false, "Administration");
	}
	/*
	 * == Display results after edit of "Search in" in Search Settings ==
	 * Test ID : 121321
	 */
	@Test
	public void test03_DisplayResultsAfterEditOfSearchinInSearchSettings(){
		// the two kind of elements we will search are : wiki and files
		String pageSearch="searchPage3" + getRandomNumber();
		String qsGadget = "Quick Search";
		String discussionName="cloud" + getRandomNumber();
		String eventName= "cloud event" + getRandomNumber();
		String taskName= "cloud task" + getRandomNumber();
		String wikiName= "Wiki cloud" + getRandomNumber();
		String spaceName= "cloud space" + getRandomNumber();
		String fileName= "cloud file" + getRandomNumber();
		String documentName = "cloud web" + getRandomNumber();
		String peopleName="herveee";
		String pageName="cloud12" + getRandomNumber();
		String surname="clouddd";
		String searchWord="cloud";

		addAllData(eventName, taskName, wikiName, spaceName, fileName, documentName, peopleName, surname, pageName, discussionName);
		/*
		 * step 1
		 */
		qsPage.addQuickSearchPage(pageSearch,qsGadget);
		/*
		 * step 2
		 */
		qsPage.goToEditSearchPortlet();
		/*
		 * step 3
		 */
		waitForAndGetElement(ELEMENT_EDIT_MODE_TAB).click();
		uncheck(qsPage.ELEMENT_SEARCH_ALL_CHECKBOX,2);
		check(qsPage.ELEMENT_SEARCH_WIKI_CHECKBOX,2);
		check(qsPage.ELEMENT_SEARCH_FILES_CHECKBOX,2);
		click(qsPage.ELEMENT_SAVE_SETTING);
		Utils.pause(2000);
		alert.acceptAlert();
		Utils.pause(2000);
		button.close();
		pagEditor.finishEditLayout();
		/*
		 * step 4
		 */
		click(qsPage.ELEMENT_QUICKSEARCH_NEW_PAGE);
		WebElement searchBox = waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_TEXTBOX_NEW_PAGE);
		searchBox.sendKeys(searchWord);
		Utils.pause(2000);
		// we check only the two elements are show
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_TYPE_RESULTS_FLOATING_BOX_NEW_PAGE.replace("${type}","Files")));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_TYPE_RESULTS_FLOATING_BOX_NEW_PAGE.replace("${type}","Wiki")));
		waitForElementNotPresent(By.xpath(qsPage.ELEMENT_TYPE_RESULTS_FLOATING_BOX_NEW_PAGE.replace("${type}","Page")));
		waitForElementNotPresent(By.xpath(qsPage.ELEMENT_TYPE_RESULTS_FLOATING_BOX_NEW_PAGE.replace("${type}","People")));
		waitForElementNotPresent(By.xpath(qsPage.ELEMENT_TYPE_RESULTS_FLOATING_BOX_NEW_PAGE.replace("${type}","Events")));
		waitForElementNotPresent(By.xpath(qsPage.ELEMENT_TYPE_RESULTS_FLOATING_BOX_NEW_PAGE.replace("${type}","Spaces")));
		waitForElementNotPresent(By.xpath(qsPage.ELEMENT_TYPE_RESULTS_FLOATING_BOX_NEW_PAGE.replace("${type}","Discussions")));
		waitForElementNotPresent(By.xpath(qsPage.ELEMENT_TYPE_RESULTS_FLOATING_BOX_NEW_PAGE.replace("${type}","Documents")));
		waitForElementNotPresent(By.xpath(qsPage.ELEMENT_TYPE_RESULTS_FLOATING_BOX_NEW_PAGE.replace("${type}","Tasks")));
		/*
		 * step 5
		 */
		// check if File and Wiki radio button are checked
		click(qsPage.ELEMENT_ALL_SEARCH_RESULT_NEW_PAGE);
		Utils.pause(2000);
		waitForAndGetElement(qsPage.ELEMENT_FILTER_SEARCH_WIKI_CHECKBOX, 5000,1,2).getAttribute("checked").equalsIgnoreCase("checked");
		waitForAndGetElement(qsPage.ELEMENT_FILTER_SEARCH_FILE_CHECKBOX, 5000,1,2).getAttribute("checked").equalsIgnoreCase("checked");
		info("Test successfully");
		/*
		 * delete data
		 */
		pageMag.deletePageAtManagePageAndPortalNavigation(pageSearch, true, "intranet", false, "Administration");
		deleteDataOfEachElement(eventName, taskName, wikiName, spaceName, fileName, documentName, peopleName, pageName, discussionName);
	}
	/*
	 * == Display the Floating result after edit of "Result per type" ==
	 * Test ID : 121378
	 */
	@Test
	public void test04_DisplayTheFloatingResultAfterEditOfResultPerType(){
		String event1="event1" + getRandomNumber();String event1Name="cloud" + getRandomNumber();
		String event2="event2" +  getRandomNumber();String event2Name="fav cloud" + getRandomNumber();
		String event3="event3" + getRandomNumber();String event3Name="av cloud" + getRandomNumber();
		String pageSearch="searchPage" + getRandomNumber();
		String qsGadget = "Quick Search";
		String searchWord="cloud";
		/*
		 * pre conditions
		 */
		
		evt.goToCalendarPage();
		// add event
		evt.addQuickEvent(event1Name,event1,getDate(0,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),false);
		evt.addQuickEvent(event2Name,event2,getDate(0,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),false);
		evt.addQuickEvent(event3Name,event3,getDate(0,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),false);

		/*
		 * Step 2
		 */
		qsPage.addQuickSearchPage(pageSearch,qsGadget);
		/*
		 * step 3
		 */
		qsPage.goToEditSearchPortlet();
		waitForAndGetElement(ELEMENT_EDIT_MODE_TAB).click();
		uncheck(qsPage.ELEMENT_SEARCH_ALL_CHECKBOX,2);
		Utils.pause(2000);
		click(qsPage.ELEMENT_RESULT_PER_PAGE_OPTION.replace("${resultsPerPage}", "2"));
		check(qsPage.ELEMENT_SEARCH_EVENTS_CHECKBOX,2);
		click(qsPage.ELEMENT_RESULTS_PER_PAGE_DROPDOWN,2);
		click(qsPage.ELEMENT_SAVE_SETTING);
		Utils.pause(2000);
		alert.acceptAlert();
		Utils.pause(2000);
		button.close();
		pagEditor.finishEditLayout();

		/*
		 * step 4
		 */
		click(qsPage.ELEMENT_QUICKSEARCH_NEW_PAGE);
		WebElement searchBox = waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_TEXTBOX_NEW_PAGE);
		searchBox.sendKeys(searchWord);
		Utils.pause(2000);
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","1").replace("${name}",event1Name)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","2").replace("${name}",event2Name)));
		waitForElementNotPresent(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","3").replace("${name}",event3Name)));
		info("Test successfully");
		
		naviToolbar.goToApplicationRegistry();
		pageMag.deletePageAtManagePageAndPortalNavigation(pageSearch, true, "intranet", false, "Administration");
		/*
		 * delete all data
		 */
		evt.goToCalendarPage();
		evt.deleteEventTask(event2Name);
		evt.deleteEventTask(event3Name);
		evt.deleteEventTask(event1Name);
	}
	/*
	 * == Display the list "Result per type" of quick search setting ==
	 * Test ID : 121387
	 */
	@Test
	public void test05_DisplayTheListResultPerTypeOfQuickSearchSetting(){
		String pageSearch="searchPageTest6";
		String qsGadget = "Quick Search";

		/*
		 * step 1
		 */
		qsPage.addQuickSearchPage(pageSearch,qsGadget);
		/*
		 * step 2
		 */
		qsPage.goToEditSearchPortlet();
		waitForAndGetElement(ELEMENT_EDIT_MODE_TAB).click();
		Utils.pause(2000);

		// check if all the 10 values are presents
		for(int i=1;i<11;i++){
			waitForAndGetElement(By.xpath(qsPage.ELEMENT_RESULT_PER_PAGE_OPTION.replace("${resultsPerPage}", ""+i)));
		}
		// check it for the next tests
		check(qsPage.ELEMENT_SEARCH_ALL_CHECKBOX,2);
		click(qsPage.ELEMENT_RESULTS_PER_PAGE_DROPDOWN,2);
		click(qsPage.ELEMENT_SAVE_SETTING);
		info("Test successfully");
		Utils.pause(2000);
		alert.acceptAlert();
		Utils.pause(2000);
		button.close();
		pagEditor.finishEditLayout();

		/*
		 * delete data
		 */
		naviToolbar.goToApplicationRegistry();
		pageMag.deletePageAtManagePageAndPortalNavigation(pageSearch, true, "intranet", false, "Administration");
	}

	/*
	 * == Save changes in " Search Settings" screen ==
	 * Test ID : 121700
	 */
	@Test
	public void test06_SaveChangesInSearchSettingsScreen(){
		String researchWord="event";
		/*
		 * Step 1
		 */
		click(ELEMENT_QUICK_SEARCH_ICON);
		info("Click on search Icon");
		Utils.pause(2000);
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys(researchWord);
		/*
		 * Step 2 
		 */
		waitForAndGetElement(ELEMENT_SEE_ALL_SEARCH_RESULTS).click();
		/*
		 * step 3
		 */
		qsPage.goToEditSearchPortlet();
		waitForAndGetElement(ELEMENT_EDIT_MODE_TAB).click();
		/*
		 * Step 4
		 */
		click(qsPage.ELEMENT_RESULT_PER_PAGE_OPTION.replace("${resultsPerPage}", "5"));
		click(qsPage.ELEMENT_SAVE_SETTING);
		info("Test successfully");
		Utils.pause(2000);
		alert.acceptAlert();
		Utils.pause(2000);
		button.close();
		pagEditor.finishEditLayout();
	}
	//-----------------------------------------------\\ Function //-----------------------------------------------\\	

	// add each kinf of data 1 time
	public void addAllData(String eventName,String taskName,String wikiName,String spaceName,String fileName,String documentName,String peopleName,String surname,String pageName,String discussionName){
		naviToolbar.goToPageCreationWizard();
		pagEditor.createNewPageEmptyLayout(pageName);

		naviToolbar.goToNewStaff();
		actBar.addNewUserAccount(peopleName, "gtngtn654", "gtngtn654", peopleName, surname, "", peopleName+"@exo.fr", null, null, true);

		evt.goToCalendarPage();
		// add event
		evt.addQuickEvent(eventName,"cloud",getDate(2,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),false);
		// add task
		task.addQuickTask(taskName,"Cloud",getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),false);
		// add wiki page
		ba.goToWiki();
		click(ba.ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		click(ba.ELEMENT_SPACE_SWITCHER_AVATAR_MYWIKI);
		ba.addBlankWikiPage(wikiName, "cloud", 0);
		// add space
		magMember.goToMySpacePage();
		magMember.addNewSpace(spaceName, "");
		info("New space added : Cloud ");
		//Add new topic
		//Create data
		mngTopic.goToForums();
		info("Create a topic");
		mngTopic.addCategoryForumTopic(discussionName, discussionName, discussionName,discussionName); 
		//Add new WebContent
		naviToolbar.goToSiteExplorer();
		actBar.goToAddNewContent();
		conTemp.createNewWebContent(documentName, documentName, "", "", "", "");
		info("new web content created");
		// add data
		naviToolbar.goToSiteExplorer();
		actBar.addItem2ActionBar("addDocument", actBar.ELEMENT_NEW_CONTENT_LINK);
		actBar.goToAddNewContent();
		// add file
		conTemp.createNewFile(fileName, "cloud", "cloud");
		click(naviToolbar.ELEMENT_SITE_EXPLORER_HOME);
		//add people  
	}

	public void deleteDataOfEachElement(String eventName,String taskName,String wikiName,String spaceName,String fileName,String documentName,String peopleName,String pageName,String discussionName){
		ba.goToWiki();
		click(ba.ELEMENT_SPACE_SWITCHER_BREADCRUMB);
		click(ba.ELEMENT_SPACE_SWITCHER_AVATAR_MYWIKI);
		String[] deleteWikiPage2 = {"Wiki Home/"+wikiName};
		ba.deleteWikiPage(deleteWikiPage2);
		evt.goToCalendarPage();
		Utils.pause(3000);
		evt.deleteEventTask(taskName);
		evt.deleteEventTask(eventName);
		magMember.goToAllSpaces();
		magMember.deleteSpace(spaceName,300000);
		naviToolbar.goToSiteExplorer();
		cMenu.deleteDocument(By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", documentName)));
		mngTopic.goToForums();
		click(By.linkText(discussionName));
		mngCat.deleteCategoryInForum(discussionName);
		naviToolbar.goToUsersAndGroupsManagement();
		userGroup.deleteUser(peopleName);
		naviToolbar.goToApplicationRegistry();
		pageMag.deletePageAtManagePageAndPortalNavigation(pageName, true, "intranet", false, "Administration");
		info("data cleared");
	}

}
