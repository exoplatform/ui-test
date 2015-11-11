package org.exoplatform.selenium.platform.plf.functional.unifiedsearch;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationManagement;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.PageManagement;
import org.exoplatform.selenium.platform.SearchAdministration;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.exoplatform.selenium.platform.forum.AnswerManageAnwser;
import org.exoplatform.selenium.platform.forum.AnswerManageQuestion;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.PeopleProfile;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.wiki.Template;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class PLF_UnifiedSearch_FileSearch extends Template {

	//General
	Button button;

	//Platform
	NavigationToolbar naviToolbar;
	ManageAccount magAcc;
	SearchAdministration searchAdmin;
	SettingSearchPage qsPage;
	PageEditor pEditor;
	PageManagement pageMag;
	ActionBar actBar;
	ContentTemplate conTemp;
	ManageMember magMember;
	ContextMenu cMenu;
	SitesExplorer siteExp;
	PeopleProfile peoPro;
	SpaceManagement spaceMag;
	Event evt;
	Task tsk;
	ForumManageForum mngFru;
	ForumManageTopic mngTopic;
	ForumManagePost mngPost;
	ForumManageCategory mngCat;
	AnswerManageAnwser ansMagAn;
	AnswerManageQuestion magQuest;
	NavigationManagement navMag;
	EcmsBase ecms;
	EcmsPermission ePerm;


	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(plfURL);
		info("Login with " + DATA_USER1);
		magAcc = new ManageAccount(driver, this.plfVersion);
		naviToolbar = new NavigationToolbar(driver, this.plfVersion);

		searchAdmin = new SearchAdministration(driver);
		qsPage = new SettingSearchPage(driver);
		pEditor = new PageEditor(driver, this.plfVersion);
		pageMag = new PageManagement(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		conTemp = new ContentTemplate(driver, this.plfVersion);
		magMember = new ManageMember(driver, this.plfVersion);
		cMenu = new ContextMenu(driver, this.plfVersion);
		siteExp = new SitesExplorer(driver, this.plfVersion);
		evt = new Event(driver, this.plfVersion);
		tsk = new Task(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		peoPro = new PeopleProfile(driver, this.plfVersion);
		spaceMag = new SpaceManagement(driver, this.plfVersion);
		mngFru = new ForumManageForum(driver, this.plfVersion);
		mngTopic = new ForumManageTopic(driver, this.plfVersion);
		mngPost = new ForumManagePost(driver, this.plfVersion);
		mngCat = new ForumManageCategory(driver, this.plfVersion);
		ansMagAn = new AnswerManageAnwser(driver, this.plfVersion);
		magQuest = new AnswerManageQuestion(driver, this.plfVersion);
		navMag = new NavigationManagement(driver, this.plfVersion);
		ecms = new EcmsBase(driver, this.plfVersion);
		alert = new ManageAlert(driver, this.plfVersion);
		ePerm = new EcmsPermission(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}


	@Test
	/** 
	 * == Display a File in the Floating Result ==
	 * Test case ID: 104243
	 * Step 1: Connect to Site, in the Quick Search box, input a valid characters to search a File (Test)
	 */
	public void test01_DisplayAFileInTheFloatingResult() {
		/*Declare variables*/
		String fileName1 = "Test104243";
		String result = "Test104243";

		/*Step 1 : connect to site*/
		//Create data
		//Some files are existed
		//Some documents are existed on Site explorer
		info("Add new webcontent");
		naviToolbar.goToSiteExplorer();
		info("Open the site explorer page");
		actBar.addItem2ActionBar("addDocument", actBar.ELEMENT_NEW_CONTENT_LINK);
		actBar.goToAddNewContent();
		info("Open document creation form");
		conTemp.createNewFile(fileName1, fileName1, fileName1);
		info("Document created");
		click(naviToolbar.ELEMENT_SITE_EXPLORER_HOME);
		info("Back to homepage");
		driver.navigate().refresh();
		/*Step 2: In the Quick Search box, input a valid characters to search a File (Test)*/
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(5000);
		type(ELEMENT_QUICK_SEARCH_TEXTBOX,result,true);
		Utils.pause(5000);
		//check the result
		waitForAndGetElement((qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${name}", fileName1.toLowerCase()).replace("${number}","1")));

		//clean the data
		naviToolbar.goToSiteExplorer();
		cMenu.deleteDocument(By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", fileName1)));

	}


	@Test
	 /** 
	  * == Display a File in the Search Result page ==
	  * Test case ID: 104244
	  * Step 1: Connect to Site, in the Quick Search box, input a valid characters to search a File (Test)
	  * Step 2: Click on "See All Search Results"
	 */
	public void test02_DisplayAFileInTheSearchResultPage() {
		/*Declare variables*/
		String fileName1 = "Test104244";
		String searchText = "Test104244";


		/*Step 1 : Connect to Site, in the Quick Search box, input a valid characters to search a File (Test)*/
		//Create data
		//Some files are existed
		//Some documents are existed on Site explorer
		info("Add new webcontent");
		naviToolbar.goToSiteExplorer();
		info("Open the site explorer page");
		actBar.addItem2ActionBar("addDocument", actBar.ELEMENT_NEW_CONTENT_LINK);
		actBar.goToAddNewContent();
		info("Open document creation form");
		conTemp.createNewFile(fileName1, fileName1, fileName1);
		info("Document created");
		Utils.pause(5000);
		click(naviToolbar.ELEMENT_SITE_EXPLORER_HOME);
		info("Back to homepage");

		//	Step 2: Click on "See All Search Results" 
		// fill the search bar & click on All search results
		qsPage.quickSearch(searchText);
		info("Search the document");
		waitForAndGetElement(qsPage.ELEMENT_RESULT_ITEM.replace("${item}", fileName1).replace("${keySearch}", searchText));
		driver.navigate().back();
		//clean the data
		naviToolbar.goToSiteExplorer();
		cMenu.deleteDocument(By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", fileName1)));
	}

	@Test
	/** 
	 * == Not Display a File as result when user has not read permission ==
	 * Test case ID: 104247
	 * Step 1: Connect to a Site, input a valid characters in the quich search box to have the file 'Test' as result
	 */
	public void test03_NotDisplayAFileAsResultWhenUserHasNotReadPermission() {
		String searchText = "TestUnseen";
		String fileName1 = "TestUnseen";

		/*Step 1: Connect to a Site, input a valid characters in the quich search box to have the file 'Test' as result*/
		//Create data
		//Some files are existed
		//Some documents are existed on Site explorer
		info("Add new webcontent");
		naviToolbar.goToSiteExplorer();
		info("Open the site explorer page");
		actBar.addItem2ActionBar("addDocument", actBar.ELEMENT_NEW_CONTENT_LINK);
		actBar.goToAddNewContent();
		info("Open document creation form");
		conTemp.createNewFile(fileName1, fileName1, fileName1);
		info("Document created");

		//Define the rigths for the file 
		actBar.goToNodePermissionManagement();
		ePerm.removeDefaultPermissionOfNode();

		//Change user 
		magAcc.signOut();
		magAcc.userSignIn(userType.PUBLISHER);
		qsPage.quickSearchType(searchText);
		click(qsPage.ELEMENT_QUICKSEARCH_NEW_PAGE);
		
		//Check the result
		waitForElementNotPresent(qsPage.ELEMENT_RESULT_ITEM.replace("${item}", fileName1).replace("${keySearch}", searchText));
		
		//clean the data
		magAcc.signOut();
		magAcc.userSignIn(userType.ADMIN);
		naviToolbar.goToSiteExplorer();
		cMenu.deleteDocument(By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", fileName1)));
	}

	@Test
	 /** 
	  * == Download the File from the Search Results ==
	  * Test case ID: 104248
	  * Step 1: Connect to Site, in the Quick Search box, input a valid characters to search a File (Test)
	  * Step 2: Click on the File 
	 */
	public void test04_DownloadTheFileFromTheSearchResults () {
		String searchText = "Test104248";
		String fileName1 = "Test104248";
		String content = "Content of Test104248";

		//Create data
		//Some files are existed
		//Some documents are existed on Site explorer
		info("Add new webcontent");
		naviToolbar.goToSiteExplorer();
		info("Open the site explorer page");
		actBar.addItem2ActionBar("addDocument", actBar.ELEMENT_NEW_CONTENT_LINK);
		actBar.goToAddNewContent();
		info("Open document creation form");
		conTemp.createNewFile(fileName1, content, fileName1);

		info("Document created");
		click(naviToolbar.ELEMENT_SITE_EXPLORER_HOME);
		info("Back to homepage"); 
		qsPage.quickSearch(searchText);

		/*Step 2: Click on the File*/
		click(qsPage.ELEMENT_RESULT_LINK.replace("${item}", fileName1));

		//check the result
		waitForMessage(content);
		driver.navigate().back();

		naviToolbar.goToSiteExplorer();
		cMenu.deleteDocument(By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", fileName1)));
	}
	
	/** 
	 * == Display Files in the Floating Result by pertinence ==
	 * Test case ID: 104250
	 * Step 1: Connect to iIntranet in the Quick Search box, input a valid characters to search a File (Test)
	 * Step 2: Open new tab (tab 2) Input into the address bar of new tab on browser: {host}:{port}/rest/search?q={keysearch}&types=all / Then Enter
	 * Step 3: Back to tab at step 1, check order of items of search result
	 **/
	@Test
	public void test05_DisplayFilesInTheFloatingResultByPertinence() {
		String searchText = "Test104250";
		String fileName1 = "Test104250";
		String fileName2 = "title2";
		String fileName3 = "title3";

		String fileTitle = "Test104250";
		String content = "Test104250";
		String content2 = "Content";
		String desc = "desc";
		String desc2= "Test104250";
		String creator = "creator";
		String source = "source";
		
		/*Step 1: - Connect to iIntranet in the Quick Search box, input a valid characters to search a File (Test) */
		//Create files 
		for(int i=0; i <=2; i++) {
			//Create data
			//Some files are existed
			//Some documents are existed on Site explorer
			info("Add new webcontent");
			naviToolbar.goToSiteExplorer();
			info("Open the site explorer page");
			actBar.addItem2ActionBar("addDocument", actBar.ELEMENT_NEW_CONTENT_LINK);
			actBar.goToAddNewContent();
			info("Open document creation form");
			if(i==0) {
				conTemp. createNewFullFile(fileName1, content2, fileTitle, desc, creator, source);
				info("Document created");
			}
			else if(i==1) {
				conTemp. createNewFullFile(fileName2, content, fileTitle, desc, creator, source);
				info("Document created");
			}
			else if(i==2) {
				conTemp. createNewFullFile(fileName3, content, fileTitle, desc2, creator, source);
				info("Document created");
			}
			click(naviToolbar.ELEMENT_SITE_EXPLORER_HOME);
			info("Back to homepage"); 
		}	
		
		//search files
		qsPage.quickSearch(searchText);

		//check the result
		waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${name}", fileName1).replace("${number}","1"));
		waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${name}", fileName2).replace("${number}","3"));
		waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${name}", fileName2).replace("${number}","2"));

		//clean the data
		naviToolbar.goToSiteExplorer();
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", fileName1));
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", fileName2));
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", fileName3));
	}
}
