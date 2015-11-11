package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate.folderType;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu.actionType;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;

import org.openqa.selenium.By;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author vuna2
 * July, 31th, 2013
 * updated by anhpp
 */
public class ECMS_SE_CreateNode_Upload_Action_Other extends PlatformBase{
	//Platform
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	Button button;

	//Ecms
	EcmsBase ecms;
	ContextMenu cMenu;
	ContentTemplate cTemplate;
	ActionBar actBar;
	EcmsPermission ecmsPer;
	SitesExplorer siteExp;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver);
		navToolBar = new NavigationToolbar(driver);
		ecms = new EcmsBase(driver);
		cMenu = new ContextMenu(driver);
		cTemplate = new ContentTemplate(driver);
		actBar = new ActionBar(driver);
		ecmsPer = new EcmsPermission(driver);
		button = new Button(driver);
		siteExp = new SitesExplorer(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Qmetry ID: 119729
	 * Upload a file in locked node by user is not locker in Site Explorer
	 * 
	 */
	@Test
	public void test01_UploadFileInLockedNodeByUserIsNotLockerInSiteExplorer(){
		String DOCUMENT_FOLDER_TITLE = "ECMS_SE_Upload_Other_01" + getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a document folder and lock this node");
		cTemplate.createNewFolder(DOCUMENT_FOLDER_TITLE, folderType.Document);	
		cMenu.contextMenuAction(By.linkText(DOCUMENT_FOLDER_TITLE), cMenu.ELEMENT_CONTEXT_MENU_LOCK);
		assert cMenu.isLockedNode(By.linkText(DOCUMENT_FOLDER_TITLE)): "Failed to lock the node..." + DOCUMENT_FOLDER_TITLE;
		driver.close();

		info("Login by user is not locker");
		initSeleniumTest();
		driver.get(plfURL);
		ecms = new EcmsBase(driver);
		navToolBar = new NavigationToolbar(driver);
		magAcc = new ManageAccount(driver);
		cMenu = new ContextMenu(driver);
		magAcc.signIn(DATA_USER2, DATA_PASS);
		
		info("Checking... [Mary] can not see [Upload] icon on action bar");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(By.linkText(DOCUMENT_FOLDER_TITLE));
		waitForElementNotPresent(ELEMENT_UPLOAD_LINK_XPATH,DEFAULT_TIMEOUT,1);
		//switchToParentWindow();
		magAcc.signOut();
		
		info("Restore data");
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(By.linkText(DOCUMENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 119730
	 * Upload a file in path is in 'check in' status
	 * 
	 */
	@Test
	public void test02_UploadFileInPathIsInCheckInStatus(){
		String WEB_CONTENT_TITLE = "ECMS_SE_Upload_Other_WebContent_02";

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a webContent and check-in this node");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(WEB_CONTENT_TITLE, WEB_CONTENT_TITLE, "", "", "", "");
		cMenu.contextMenuAction(By.linkText(WEB_CONTENT_TITLE), cMenu.ELEMENT_MENU_CHECKIN);

		info("Verification... the button [Upload] is not displayed");
		ecms.goToNode(WEB_CONTENT_TITLE);
		waitForElementNotPresent(ELEMENT_UPLOAD_LINK_XPATH);

		info("Restore data");
		cMenu.contextMenuAction(By.linkText(WEB_CONTENT_TITLE), cMenu.ELEMENT_MENU_CHECKOUT);
		cMenu.deleteDocument(By.linkText(WEB_CONTENT_TITLE));
	}

	/**
	 * Qmetry ID: 119738
	 * Upload a file when user does not have add node right
	 * 
	 */
	@Test
	public void test03_UploadFileWhenUserDoesNotHaveAddNodeRight(){
		String WEB_CONTENT_TITLE = "ECMS_SE_Upload_Other_WebContent_03";

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a new Web Content");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(WEB_CONTENT_TITLE, WEB_CONTENT_TITLE, "", "", "", "");

		info("Set permission for this node");
		actBar.goToNodePermissionManagement();
		ecmsPer.removeDefaultPermissionOfNode();
		ecms.selectUser(DATA_USER2);
		ecmsPer.setPermissionForNode(true, false, false);
		button.save();

		info("Login by user who has not permission to add node inside the above node");
		magAcc.signOut();
		magAcc.signIn(DATA_USER2, DATA_PASS);
		navToolBar.goToSiteExplorer();
		ecms.goToNode(WEB_CONTENT_TITLE);
		waitForElementNotPresent(ELEMENT_UPLOAD_LINK_XPATH);

		info("Reset data");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(By.linkText(WEB_CONTENT_TITLE));		
	}

	/**
	 * Qmetry ID: 119739
	 * Upload a file in a node has parent node is in 'Check in' status
	 * 
	 */
	@Test
	public void test04_UploadFileInNodeHasParentNodeIsInCheckInStatus(){
		String WEB_CONTENT_FOLDER_TITLE = "ECMS_SE_Upload_Other_04";
		String WEB_CONTENT_TITLE = "ECMS_SE_Upload_Other_WebContent_04";
		String FILE_NAME = "ECMS_DMS_SE_Upload_pdffile.pdf";

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a new webcontent folder");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(WEB_CONTENT_FOLDER_TITLE, WEB_CONTENT_FOLDER_TITLE, "", "", "", "");

		info("Create a new content inside the above folder");
		actBar.goToAddNewContent();
		cTemplate.createNewIllustratedWebContent(WEB_CONTENT_TITLE, WEB_CONTENT_TITLE, "", "", "", "", "");

		info("Check in the document folder");
		ecms.goToNode(WEB_CONTENT_FOLDER_TITLE);
		cMenu.contextMenuAction(By.linkText(WEB_CONTENT_FOLDER_TITLE), cMenu.ELEMENT_MENU_CHECKIN);

		info("Upload file in child node");
		ecms.goToNode(WEB_CONTENT_TITLE);
		ecms.uploadFile("TestData/" + FILE_NAME);

		info("Reset data");
		cMenu.contextMenuAction(By.linkText(WEB_CONTENT_FOLDER_TITLE), cMenu.ELEMENT_MENU_CHECKOUT);
		cMenu.deleteDocument(By.linkText(WEB_CONTENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 119770
	 * File uploaded is stored and saved in Document
	 * 
	 */
	@Test
	public void test05_FileUploadedIsStoredAndSavedInDocument(){
		String FILE_PDF_NAME = "ECMS_DMS_SE_Upload_pdffile";

		info("Go to Intranet/Documents");
		navToolBar.goToPersonalDocuments();

		info("Upload a file");
		ecms.uploadFile("TestData/" + FILE_PDF_NAME + ".pdf");
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", FILE_PDF_NAME));

		info("Restore data");
		actBar.actionsOnElement(FILE_PDF_NAME, actionType.DELETE);
	}

	/**
	 * Qmetry ID: 119794
	 * File uploaded is stored and saved in Site Explorer
	 * 
	 */
	@Test
	public void test06_FileUploadedIsStoredAndSavedInSiteExplorer(){
		String FILE_NAME = "ECMS_DMS_SE_Upload_docfile.doc";

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Upload a file");
		ecms.uploadFile("TestData/" + FILE_NAME);

		info("Reset data");
		cMenu.deleteDocument(By.linkText(FILE_NAME));
	}

	/**
	 * Qmetry ID: 119795
	 * Upload a file with invalid characters([], ', ") in Site Explorer
	 * 
	 */
	@Test
	public void test07_UploadFileWithInvalidCharactersInSiteExplorer(){
		String FILE_NAME = "ECMS_DMS_SE_Upload_[]'\"Chars.doc";
		String FILE_NAME_UPDATE = "ECMS_DMS_SE_Upload_---\\"+"-Chars.doc";

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Upload a file with invalid characters");
		ecms.uploadFile("TestData/" + FILE_NAME,false);

		info("Verification: invalid characters will be replaced by character [Dash]");
		waitForAndGetElement(ecms.ELEMENT_DATA_TITLE.replace("${dataTitle}", FILE_NAME_UPDATE));

		info("Reset data");
		cMenu.deleteDocument(By.linkText(FILE_NAME_UPDATE));
	}
	/**
	 * Qmetry ID: 74419
	 * Upload a file with size more than 200 MB from Document
	 * ====================== PENDING =======================
	 * ===== Do not upload a large file to git repository ===  
	 * 
	 */
	@Test(groups={"pending"})
	public void test08_UploadFileWithSizeMoreThan200MBFromDocument(){

	}

	/**
	 * Qmetry ID: 74422
	 * File uploaded with "waiting" status when upload in Document 
	 * 
	 */
	@Test(groups={"pending"})
	public void test09_FileUploadedWithWaitingStatusWhenUploadInDocument(){

	}

	/**
	 * Qmetry ID: 74451
	 * Upload a file with size more than 200 MB form Site Explorer
	 * ====================== PENDING =======================
	 * ===== Do not upload a large file to git repository ===  
	 */
	@Test(groups={"pending"})
	public void test10_UploadFileWithSizeMoreThan200MBformSiteExplorer(){

	}

	/**
	 * Qmetry ID: 74461
	 * File uploaded with "waiting" status when upload in Site Explorer
	 * 
	 * 
	 */
	@Test(groups={"pending"})
	public void test11_FileUploadedWithWaitingStatusWhenUploadInSiteExplorer(){

	}

	/**
	 * Qmetry ID: 74922
	 * Upload a file with invalid characters in Document
	 * 
	 * Pending because file which is used to upload is illegal on Windows
	 */
	@Test(groups="pending")
	public void test12_UploadFileWithInvalidCharactersInDocument(){
		String FILE_NAME = "ECMS_DMS_SE_Upload_[]'\"Chars";
		String FILE_NAME_UPDATE = "ECMS_DMS_SE_Upload_----Chars";

		info("Go to Intranet/Documents");
		navToolBar.goToPersonalDocuments();

		info("Upload a file with invalid characters");
		ecms.uploadFile("TestData/" + FILE_NAME + ".doc", false);

		info("Verification: invalid characters will be replaced by character [Dash]");
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", FILE_NAME_UPDATE));

		info("Reset data");
		actBar.actionsOnElement(FILE_NAME_UPDATE, actionType.DELETE);	
	}
}
