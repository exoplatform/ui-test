package org.exoplatform.selenium.platform.ecms.sniff.wcm;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.PageManagement;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.wcm.ContentListPreference;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author vuna2
 * June, 2013
 *  
 */
public class ECMS_WCM_Viewer extends PlatformBase{

	Button button;
	PageEditor pEditor;
	PageManagement pMag;
	NavigationToolbar navToolBar;
	UserGroupManagement userGroup;
	ManageAccount magAcc;
	EcmsBase ecms;
	ActionBar actBar;
	ContentTemplate cTemplate;
	ContextMenu cMenu;
	ContentListPreference cList;

	String acmeURL = DEFAULT_BASEURL + "/acme";

	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);
		button = new Button(driver, this.plfVersion);
		userGroup = new UserGroupManagement(driver, this.plfVersion);
		navToolBar = new NavigationToolbar(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		pEditor = new PageEditor(driver, this.plfVersion);
		pMag = new PageManagement(driver, this.plfVersion);
		ecms = new EcmsBase(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		cTemplate = new ContentTemplate(driver, this.plfVersion);
		cMenu = new ContextMenu(driver, this.plfVersion);
		cList = new ContentListPreference(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		driver.get(acmeURL);
		driver.navigate().refresh();
		waitForElementNotPresent(ELEMENT_PERSONAL_DOCUMENTS);
	}

	@AfterMethod
	public void afterMethods() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/*===== Single Content Viewer =====*/
	/**
	 * Qmetry ID: 65833
	 * Create Single Content Viewer page 
	 * ================
	 * Qmetry ID: 65833
	 * Edit Single Content Viewer page
	 * 
	 */
	@Test
	public void test01_CreateAndEditSingleContentViewerPage(){	
		String pageName = "sniff658333";
		String contentPath = "General Drives/Sites Management/acme/documents/offices.jpg";
		String editContentPath = "General Drives/Sites Management/acme/documents/conditions.doc";

		info("-- Create Single Content Viewer page --");
		pEditor.addSCVPageAndContentFolderPaths(pageName, contentPath);
		waitForTextPresent("offices.jpg");

		info("-- Edit Single Content Viewer page --");
		navToolBar.goToEditPageEditor();
		pEditor.openAddContentPathForm();
		userGroup = new UserGroupManagement(driver);
		userGroup.selectGroup(editContentPath);
		button.save();
		button.close();
		click(ELEMENT_PAGE_FINISH_BUTTON);
		waitForTextNotPresent("offices.jpg");
		waitForTextPresent("conditions.doc");

		info("-- Restore original data--");
		pMag.deletePageAtManagePageAndPortalNavigation(pageName, true, "acme", false, "");
	}

	/*===== Content List Viewer =====*/
	/**
	 * Qmetry ID: 65829
	 * Create new Content List viewer page with mode [By Folder]
	 * ================
	 * Qmetry ID: 67825
	 * Edit Content List viewer page with mode [By Folder]
	 * 
	 */
	@Test
	public void test02_CreateAndEditContentListViewerPageByFolder(){
		String cName = "page1678252";
		String cContent = "Web content: CLV By folder";
		String contentPath = "acme/web contents/News";

		String pCLVName = "CLV_By_Folder_1678252";
		String pCLVPath = "General Drives/Sites Management/acme/web contents";
		String pCLVFolder = "News";
		String editpCLVFolder = "Events";

		info("-- Create a web content --");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(contentPath);
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(cName, cContent, "", "", "", "");
		actBar.publishDocument();

		info("-- Create a new content list viewer page --");
		driver.get(baseUrl);
		ecms.goToOverviewPage();
		pEditor.addCLVPageAndCLVpath(pCLVName, pCLVPath, pCLVFolder);
		//waitForTextPresent(cName);

		info("-- Edit a content list viewer page --");
		navToolBar.goToEditPageEditor();
		pEditor.openAddContentPathForm();
		String[] node = pCLVPath.split("/");
		for (int i = 0; i < node.length; i ++){
			Utils.pause(3000);
			click(By.xpath("//*[@class='node']//*[contains(text(),'" + node [i] + "')]"));
		}
		click(pEditor.ELEMENT_RIGHT_WORKSPACE_NODE_NEW.replace("${node}", editpCLVFolder));
		button.save();
		//wait a second: avoid pop-up blocker 
		waitForTextNotPresent("News");
		button.close();
		click(ELEMENT_PAGE_FINISH_BUTTON);
		waitForTextNotPresent(cName);

		info("-- Restore original data--");
		pMag.deletePageAtManagePageAndPortalNavigation(pCLVName, true, "acme", false, "");
		navToolBar.goToSiteExplorer();
		//ecms.goToNode(contentPath);
		cMenu.deleteDocument(By.linkText(cName));
	}

	/**
	 * Qmetry ID: 65832
	 * Create new Content List Viewer page with mode [By Contents]
	 * ================
	 * Qmetry ID: 67824
	 * Edit Content List Viewer page with mode [By Contents] 
	 *  
	 */
	@Test
	public void test03_CreateAndEditListViewerPageByContent(){
		String cName = "CLV_By_Content_658324";
		String cContent = "Web content: CLV By Content";
		String contentPath = "acme/web contents/Events";

		String pCLVName = "CLV_By_Content_678244";
		String pCLVPath = "General Drives/Sites Management/acme/web contents/News";
		String pCLVContent = "New: Speed";
		String editCLVPath = "General Drives/Sites Management/acme/web contents/Events/" + cName;

		info("-- Create a web content --");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(contentPath);
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(cName, cContent, "", "", "", "");
		actBar.publishDocument();

		info("-- Create a new content list viewer page --");
		ecms.goToOverviewPage();		
		pEditor.addCLVPageAndCLVpath(pCLVName, pCLVPath, pCLVContent, "content");

		info("-- Edit a content list viewer page --");
		navToolBar.goToEditPageEditor();
		pEditor.selectContentPathInEditMode(editCLVPath, false);
		click(ELEMENT_PAGE_FINISH_BUTTON);

		info("-- Restore original data--");
		pMag.deletePageAtManagePageAndPortalNavigation(pCLVName, true, "acme", false, "");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(contentPath);
		cMenu.deleteDocument(By.linkText(cName));
	}

	/**
	 * Qmetry ID: 65843
	 * Edit Preference of Content List Viewer (Documents, Last news ...)
	 * 
	 */
	@Test
	public void test04_EditPreferenceOfContentListViewer(){
		String cName = "page658453";
		String cContent = "Edit Preference of Content List Viewer";
		String contentPath = "acme/web contents/Events";
		String image = "TestData/Winter.jpg";
		String pCLVPath = "General Drives/Sites Management/acme/web contents/Events/"+cName;		

		info("-- Create a new web content --");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(contentPath);
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(cName, cContent, image, "", "", "");
		actBar.publishDocument();
		actBar.openManagePublicationForm();
		String pDate;
		pDate = actBar.getPublishDate(By.xpath(actBar.ELEMENT_REVISION_DATE.replace("${status}", "Published[Current Revision]")));
		button.close();

		info("-- Open Overview page --");
		ecms.goToOverviewPage();
		//waitForTextNotPresent(pDate);
		ecms.enableEditMode(true);

		info("-- Latest news: Open Content List Preference --");
		cList.goToContentListPreference(ecms.ELEMENT_RSS_ICON, cList.ELEMENT_PREFERENCE_LATEST_NEWS_ICON);
		//Edit properties
		cList.setDisplayOptions(cList.ELEMENT_SHOW_DATE);
		click(cList.ELEMENT_CONTENT_SELECTION_TAB);
		click(pEditor.ELEMENT_BY_CONTENT_MODE,2);
		click(ELEMENT_SELECT_CONTENT_PATH_LINK);
		userGroup = new UserGroupManagement(driver);
		userGroup.selectGroup(pCLVPath);
		click(By.xpath(".//*[@id='tab-UIContentBrowsePanelMulti']//*[text()='Save']"));
		click(By.xpath(".//*[@id='UICLVConfig']//*[text()='Save']"));
		waitForAndGetElement(ecms.ELEMENT_CLV_TITLE.replace("${title}", cName), DEFAULT_TIMEOUT, 0, 2);
		waitForAndGetElement(ecms.ELEMENT_CLV_PUBLISH_DATE.replace("${date}", pDate), DEFAULT_TIMEOUT, 0, 2);
		info("-- Restore original data--");
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(By.linkText(cName));
	}
} 