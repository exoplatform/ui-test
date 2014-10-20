package org.exoplatform.selenium.platform.plf.functional.unifiedsearch;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.TestBase;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageManagement;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.exoplatform.selenium.platform.wiki.Template;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PLF_UnifiedSearch_FileSearch extends Template {

	//General
	Button button;

	//Platform
	NavigationToolbar naviToolbar;
	ManageAccount magAcc;
	SettingSearchPage qsPage;
	PageManagement pageMag;
	ActionBar actBar;
	ContentTemplate conTemp;
	ContextMenu cMenu;
	SitesExplorer siteExp;
	EcmsPermission ePerm;
	TestBase testBase;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		info("Login with " + DATA_USER1);
		magAcc = new ManageAccount(driver, this.plfVersion);
		naviToolbar = new NavigationToolbar(driver, this.plfVersion);
		qsPage = new SettingSearchPage(driver);
		pageMag = new PageManagement(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		conTemp = new ContentTemplate(driver, this.plfVersion);
		cMenu = new ContextMenu(driver, this.plfVersion);
		siteExp = new SitesExplorer(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		ePerm = new EcmsPermission(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		testBase = new TestBase();
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
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", fileName1));

	}

	@Test
	/** 
	 * == Display a File in the Search Result page ==
	 * Test case ID: 104244
	 * Step 1: Connect to Site, in the Quick Search box, input a valid characters to search a File (Test)
	 * Step 2: Click on "See All Search Results"
	 */
	public void test02_DisplayAFileInTheSearchResultPage() {
		/*Declare varia	bles*/
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
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", fileName1));
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
		String user = "any";
		String user2 = "*:/platform/web-contributors";

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
		ePerm.deletePermission(user, true);
		ePerm.deletePermission(user2, true);
		//Change user 
		magAcc.signOut();
		magAcc.userSignIn(userType.PUBLISHER);
		qsPage.quickSearch(searchText);

		//Check the result
		waitForElementNotPresent(qsPage.ELEMENT_RESULT_ITEM.replace("${item}", fileName1).replace("${keySearch}", searchText));
		
		//clean the data
		magAcc.signOut();
		magAcc.userSignIn(userType.ADMIN);
		naviToolbar.goToSiteExplorer();
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", fileName1));
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
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", fileName1));
	}

	@Test(groups="pending")
	/** 
	 * == Display Files in the Floating Result by pertinence ==
	 * Test case ID: 104250
	 * Step 1: Connect to iIntranet in the Quick Search box, input a valid characters to search a File (Test)
	 * Step 2: Open new tab (tab 2) Input into the address bar of new tab on browser: {host}:{port}/rest/search?q={keysearch}&types=all / Then Enter
	 * Step 3: Back to tab at step 1, check order of items of search result
	 */
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
		String adress= "rest/search?q={keysearch}&types=all";
		int i;


		/*Step 1: - Connect to iIntranet in the Quick Search box, input a valid characters to search a File (Test) */
		//Create files 
		for(i=0; i <=2; i++) {
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

		//open new window
		click(naviToolbar.ELEMENT_SITE_EXPLORER_HOME);
		String theURL = driver.getCurrentUrl();
		driver.get((theURL).replace("portal/intranet/", adress).replace("{keysearch}", searchText));

		//check the result
		waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${name}", fileName1).replace("${number}","1"));
		waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${name}", fileName2).replace("${number}","2"));
		waitForAndGetElement(qsPage.ELEMENT_QUICKSEARCH_RESULT.replace("${name}", fileName2).replace("${number}","3"));

		//clean the data
		naviToolbar.goToSiteExplorer();
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", fileName1));
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", fileName2));
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}", fileName3));
	}
}
