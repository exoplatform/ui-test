package org.exoplatform.selenium.platform.plf.functional.unifiedsearch;

import static org.exoplatform.selenium.TestLogger.info;


import java.awt.event.KeyEvent;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.PageManagement;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
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
import org.testng.*;
import org.exoplatform.selenium.Utils;
public class PLF_UnifiedSearch_QuickSearch extends Activity {

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
		driver.get(baseUrl);
		magAcc = new ManageAccount(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
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
		alert = new ManageAlert(driver);
	}

	@AfterMethod
	public void afterTest(){
//		driver.manage().deleteAllCookies();		
//		driver.quit();
	}

	/*
	 * ==Display "See All Search Results" in the Quick search component==
	 * Test ID : 104279
	 * Step 1 :- From navigation bar, click on magnifying class icon
	 * Step 2 :- Start typing a character
	 */
	@Test
	public void test01_DisplaySeeAllSearchResultsInTheQuickSearchComponent(){
		String researchWord="a";
		/*
		 * Step 1 : From navigation bar, click on magnifying class icon
		 */
		click(ELEMENT_QUICK_SEARCH_ICON);
		info("Click on search Icon");
		Utils.pause(2000);
		/*
		 * Step 2 : Start typing a character
		 */
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys(researchWord);
		waitForAndGetElement(ELEMENT_SEE_ALL_SEARCH_RESULTS);
		info("Type text in the floating box");
	}

	/*
	 * ==Display 5 results per group==
	 * Test ID : 104275
	 * Test 1 : In Navigation bar, in quick search, type "clo"
	 */
	@Test
	public void test02_Display5ResultsPerGroup(){
		String researchWord="clo";
		String eventName="event 1";
		String eventName2="event 2";
		String eventName3="event 3";
		String eventName4="event 4";
		String eventName5="event 5";
		/*
		 * Pre condition
		 */
		evt.goToCalendarPage();
		evt.addQuickEvent(eventName,researchWord,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),false);
		info(" Add event : "+eventName);
		evt.addQuickEvent(eventName2,researchWord,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),false);
		info(" Add event : "+eventName2);
		evt.addQuickEvent(eventName3,researchWord,getDate(2,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),false);
		info(" Add event : "+eventName3);
		evt.addQuickEvent(eventName4,researchWord,getDate(2,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),false);
		info(" Add event : "+eventName4);
		evt.addQuickEvent(eventName5,researchWord,getDate(3,"MM/dd/yyyy"),getDate(3,"MM/dd/yyyy"),false);
		info(" Add event : "+eventName5);

		/*
		 * Step 1 :  In Navigation bar, in quick search, type "clo"
		 */
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys(researchWord);

		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","1").replace("${name}",eventName)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","1").replace("${name}",eventName2)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","1").replace("${name}",eventName3)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","1").replace("${name}",eventName4)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","1").replace("${name}",eventName5)));
		/*
		 * clear data
		 */
		evt.deleteEventTask(eventName);
		evt.deleteEventTask(eventName2);
		evt.deleteEventTask(eventName3);
		evt.deleteEventTask(eventName4);
		evt.deleteEventTask(eventName5);
		info("Delete all events");
	}
	/*
	 * ==Display an added action in the Floating Result==
	 * Test ID : 104277
	 */
	@Test
	public void test03_DisplayAnAddedActionInTheFloatingResult(){
		String spaceName="FQAGroup";
		/*
		 * Step 1 : - Connect to a site (Intranet) with User A
					- From navigation bar, click on magnifying class icon
					- Start typing a character
		 */
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys(spaceName);
		waitForElementNotPresent(ELEMENT_SEE_ALL_SEARCH_RESULTS);
		/*
		 * Step 2 :- Connect to the site with User B in other browser
		   - Add an action (Forum, content, wiki page..) which can be a result in the search of the user A
		 */
		info("Connect with new user Mary");
		loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		evt1=new Event(newDriver, this.plfVersion);
		magMember1 = new ManageMember(newDriver,this.plfVersion);
		newDriver.navigate().refresh();
		magAcc1 = new ManageAccount(newDriver, this.plfVersion);
		magMember1.goToMySpacePage();
		magMember1.addNewSpace(spaceName, "");
		info("Disconnect user");
		magAcc1.signOut();
		Utils.pause(800);
		newDriver.quit();

		/*
		 * Step 3 :- Go back to the session of the User A and type the search keyword.
		 */
		driver.navigate().refresh();
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		WebElement searchBox1 = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox1.sendKeys(spaceName);
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","1").replace("${name}",spaceName)));

		/*
		 * Delete data
		 */
		magAcc.signOut();
		magAcc.signIn(DATA_USER2, DATA_PASS);
		magMember.goToMySpacePage();
		magMember.deleteSpace(spaceName);
	}
	/*
	 * ==Display the quick search component==
	 * Test ID : 104271
	 */
	@Test
	public void test04_DisplayTheQuickSearchComponent(){
		String nameUser="James";

		/*
		 * Step 1 : Click on the magnifying button
		 */
		click(ELEMENT_QUICK_SEARCH_ICON);

		/*
		 * Step 2 : Start typing a character
		 */
		Utils.pause(2000);
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys(nameUser);
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${number}","1").replace("${name}",nameUser)));
	}

	/*
	 * ==Display the search page==
	 * Test ID : 104280
	 */
	@Test
	public void test05_DisplayTheSearchPage(){
		String nameUser="James";
		/*
		 * Step 1 :- From navigation bar, click on magnifying class icon
		 */
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		/*
		 * Step 2 : - Start typing a character
		 */
		info ("Search for user James");
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys(nameUser);
		Utils.pause(2000);
		/*
		 * Step 3 : - Click on "See All Search Results"
		 */
		waitForAndGetElement(ELEMENT_SEE_ALL_SEARCH_RESULTS).click();
		waitForAndGetElement(qsPage.ELEMENT_RESULT_ITEM.replace("${item}", nameUser).replace("${keySearch}", nameUser));
		WebElement elementSearch = waitForAndGetElement(qsPage.ELEMENT_SEARCH_TEXTBOX);
		String mysearch = elementSearch.getAttribute("value");
		Assert.assertTrue(mysearch.equals(nameUser));
	}

	/*
	 * ==Results are group by type==
	 * Test ID : 104273
	 */
	@Test
	public void test06_ResultsAreGroupeByType(){
		// the search word is "apple"
		String discussionName="apple";
		String eventName= "apple event";
		String taskName= "apple task";
		String wikiName= "apple wi";
		String spaceName= "apple space";
		String fileName= "apple file";
		String documentName = "apple w";
		String peopleName="jean";
		String surname="apple";
		String findpeopleName="jean apple";
		String pageName="apple";

		addAllData( eventName, taskName, wikiName, spaceName, fileName, documentName, peopleName, surname, pageName, discussionName);
		/*
		 * Step 1 : search for cloud
		 */
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys("apple");
		Utils.pause(2000);

		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$position}","1").replace("{$result}",fileName)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$position}","3").replace("{$result}",documentName)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$position}","5").replace("{$result}",wikiName)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$position}","6").replace("{$result}",pageName)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$position}","7").replace("{$result}",discussionName)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$position}","8").replace("{$result}",findpeopleName)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$position}","9").replace("{$result}",spaceName)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$position}","10").replace("{$result}",eventName)));
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$position}","11").replace("{$result}",taskName)));
		/*
		 * delete data
		 */
		deleteDataOfEachElement( eventName, taskName, wikiName, spaceName, fileName, documentName, peopleName, pageName, discussionName);
	}

	/*
	 * ==Resulted sorts by pertinence in groups==
	 * Test ID : 104274
	 */
	@Test(groups="pending")
	public void test07_ResultedSortsByPertinenceInGroups(){
		String discussionName1="cloud1";
		String eventName1= "cloud1 event";
		String taskName1= "cloud1 task";
		String wikiName1= "Wiki cloud1";
		String spaceName1= "cloud1 space";
		String fileName1= "cloud1 file";
		String documentName1= "cloud1 web";
		String peopleName1="philipe";
		String surname1="cloud";
		String pageName1="cloudun";
		String discussionName2="cloud2";
		String eventName2= "cloud2 event";
		String taskName2= "cloud2 task";
		String wikiName2= "Wiki cloud2";
		String spaceName2= "cloud2 space";
		String fileName2= "cloud2 file";
		String documentName2 = "cloud2 web";
		String peopleName2="marine";
		String surname2="cloud";
		String pageName2="cloudde";

		String tabVar1[] = {eventName1, taskName1, wikiName1, spaceName1, fileName1, documentName1, peopleName1, surname1, pageName1, discussionName1};
		String tabVar2[] = {eventName2, taskName2, wikiName2, spaceName2, fileName2, documentName2, peopleName2, surname2, pageName2, discussionName2};
		/*
		 * pre conditions
		 */
		addAllData( eventName1, taskName1, wikiName1, spaceName1, fileName1, documentName1, peopleName1, surname1, pageName1, discussionName1);
		naviToolbar.goToHomePage();
		addAllData( eventName2, taskName2, wikiName2, spaceName2, fileName2, documentName2, peopleName2, surname2, pageName2, discussionName2);

		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys("cloud");
		Utils.pause(2000);
		// check all the first data are presents in the floating box
		for(int i=0;i<tabVar1.length;i++)
			waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_RESULT_NO_ORDER.replace("${name}", tabVar1[i]));
		// check the second tab of data		
		for(int j=0;j<tabVar2.length;j++)
			waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_RESULT_NO_ORDER.replace("${name}", tabVar2[j]));

		loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		evt1=new Event(newDriver, this.plfVersion);
		magMember1 = new ManageMember(newDriver,this.plfVersion);
		newDriver.navigate().refresh();
		magAcc1 = new ManageAccount(newDriver, this.plfVersion);

		click(naviToolbar.ELEMENT_SITE_EXPLORER_HOME);
		String theURL = driver.getCurrentUrl();
		driver.get((theURL).replace("portal/intranet/", "/rest/search?q=cloud&types=all"));
		info("Disconnect user");
		magAcc1.signOut();
		Utils.pause(800);
		newDriver.quit();
		/*
		 * delete data 
		 */
		deleteDataOfEachElement( eventName1, taskName1, wikiName1, spaceName1, fileName1, documentName1, peopleName1, pageName1, discussionName1);
		deleteDataOfEachElement( eventName2, taskName2, wikiName2, spaceName2, fileName2, documentName2, peopleName2, pageName2, discussionName2);
	}

	/*
	 * == Search as you type ==
	 * Test ID : 104272
	 */
	@Test
	public void test08_SearchAsYouType(){
		String eventName1="event1";
		String eventName2="event2";
		String content1="1112";
		String content2="11122";
		/*
		 * pre conditions
		 */
		evt.goToCalendarPage();
		// add event
		evt.addQuickEvent(eventName1,content1,getDate(1,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),false);
		evt.addQuickEvent(eventName2,content2,getDate(1,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),false);

		/*
		 * Step 1 
		 */
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys("1");
		Utils.pause(2000);
		waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_RESULT_NO_ORDER.replace("${name}", "1.jpg"));
		/*
		 * step 2
		 */
		searchBox.sendKeys("11222");
		Utils.pause(2000);
		waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_RESULT_NO_ORDER.replace("${name}", eventName2));
		Utils.javaSimulateKeyPress(KeyEvent.VK_BACK_SPACE);
	
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		
		Utils.javaSimulateKeyPress(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Utils.pause(5000);
		Utils.javaSimulateKeyPress(KeyEvent.VK_CUT);
		Utils.pause(10000);
		/*
		 * delete data
		 */
		evt.goToCalendarPage();
		evt.deleteEventTask(eventName1);
		evt.deleteEventTask(eventName2);
	}

	/*
	 *== See All Search results ==
	 * Test ID : 104276
	 */
	@Test
	public void test09_SeeAllSearchResults(){
		String eventName1="event1";
		String content1="cloud";
		String pageSearch="SearchPage09";
		String qsGadget = "Quick Search";
		/*
		 * pre conditions
		 */
		evt.goToCalendarPage();
		evt.addQuickEvent(eventName1,content1,getDate(1,"MM/dd/yyyy"),getDate(2,"MM/dd/yyyy"),false);
		
		/*
		 * Step 1
		 */
		qsPage.addQuickSearchPage(pageSearch,qsGadget);
		qsPage.goToEditSearchPortlet();
		waitForAndGetElement(ELEMENT_EDIT_MODE_TAB).click();
		check(qsPage.ELEMENT_SEARCH_ALL_CHECKBOX,2);
		uncheck(qsPage.ELEMENT_SEARCH_WIKI_CHECKBOX,2);
		Utils.pause(2000);
		click(qsPage.ELEMENT_SAVE_SETTING);
		Utils.pause(2000);
		alert.acceptAlert();
		Utils.pause(2000);
		button.close();
		pagEditor.finishEditLayout();
		/*
		 * Step 2
		 */
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys("cloud");
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_SEE_ALL_SEARCH_RESULTS).click();
		/*
		 * step 3
		 */
		Utils.pause(4000);
		assert !waitForAndGetElement(qsPage.ELEMENT_FILTER_SEARCH_WIKI_CHECKBOX,DEFAULT_TIMEOUT,1,2).isSelected() : "Fail! The wiki checkbox is still checked.";
		String theURL = driver.getCurrentUrl();
		Assert.assertTrue(theURL.contains("cloud"));

		WebElement elementSearch = waitForAndGetElement(qsPage.ELEMENT_SEARCH_TEXTBOX);
		String mysearch = elementSearch.getAttribute("value");
		Assert.assertTrue(mysearch.equals("cloud"));

		waitForAndGetElement(qsPage.ELEMENT_RESULT_ITEM.replace("${item}", eventName1).replace("${keySearch}",eventName1));

		/*
		 * delete data
		 */
		pageMag.deletePageAtManagePageAndPortalNavigation(pageSearch, true, "intranet", false, "Administration");
		evt.goToCalendarPage();
		evt.deleteEventTask(eventName1);
	}

	//-----------------------------------------------\\ Function //-----------------------------------------------\\

	public void addAllData(String eventName,String taskName,String wikiName,String spaceName,String fileName,String documentName,String peopleName,String surname,String pageName,String discussionName){
		naviToolbar.goToPageCreationWizard();
		pagEditor.createNewPageEmptyLayout(pageName);

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
		naviToolbar.goToNewStaff();
		actBar.addNewUserAccount(peopleName, "gtngtn654", "gtngtn654", peopleName, surname, "", peopleName+"@exo.fr", null, null, true);
		//create cloud page
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
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", documentName));
		mngTopic.goToForums();
		click(By.linkText(discussionName));
		mngCat.deleteCategoryInForum(discussionName);
		naviToolbar.goToUsersAndGroupsManagement();
		userGroup.deleteUser(peopleName);
		naviToolbar.goToApplicationRegistry();
		pageMag.deletePageAtManagePageAndPortalNavigation(pageName, false, "intranet", true, "Administration");
		info("data cleared");
	}
}