package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.basicaction;

import static org.exoplatform.selenium.TestLogger.*;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.TestBase;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageView;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate.folderType;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu.actionType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Update @author vuna2
 * May, 2013
 */
/**
 *@author NhungVT
 *@date: 02/10/2012
 */
public class ECMS_SE_BasicAction_Delete extends PlatformBase {

	//Platform
	Dialog dialog;
	ManageAlert magAlert;
	Button button;
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;

	//Ecms
	EcmsBase ecms;
	EcmsPermission ecmsPer;
	ContentTemplate cTemplate;
	ContextMenu cMenu;
	ContextMenu c2Menu;
	SitesExplorer siteExp;
	ECMainFunction ecMain;
	ManageView mnView;
	TestBase testBase;

	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		dialog = new Dialog(driver); 
		magAlert = new ManageAlert(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion); 
		navToolBar = new NavigationToolbar(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);	
		ecms = new EcmsBase(driver, this.plfVersion);
		ecmsPer = new EcmsPermission(driver); 
		cTemplate = new ContentTemplate(driver, this.plfVersion);
		cMenu = new ContextMenu(driver, this.plfVersion); 
		siteExp = new SitesExplorer(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1,DATA_PASS);
		mnView = new ManageView(driver,this.plfVersion);
		ecMain = new ECMainFunction(driver,this.plfVersion);
	}

	@AfterMethod()
	public void afterTest()
	{
		driver.quit();
	}
	/**
	 * Qmetry ID: 101908
	 */
	//Delete node when user has right to remove node
	@Test
	public void test01_DeleteNodeWhenUserHasRightToRemove(){	

		String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_BasicAction_Delete_content_01";
		By CONTENT_FOLDER = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", DATA_CONTENT_FOLDER));

		String DATA_FILE_NAME = "ECMS_DMS_SE_BasicAction_Delete_File_01";
		By FILE_DOCUMENT = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", DATA_FILE_NAME));

		String DATA_UPLOAD_FILE_PATH ="TestData/Winter.jpg";
		By UPLOAD_FILE_NAME = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", "Winter.jpg"));

		//goto Site Explorer
		navToolBar.goToSiteExplorer();

		//Create a Content Folder
		cTemplate.createNewFolder(DATA_CONTENT_FOLDER, folderType.Content);

		//Create a File Document
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_NAME, DATA_FILE_NAME, DATA_FILE_NAME);

		click(siteExp.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		waitForAndGetElement(FILE_DOCUMENT);

		//Upload a File
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH);
		waitForAndGetElement(UPLOAD_FILE_NAME);

		//Delete Nodes
		cMenu.deleteDocument(CONTENT_FOLDER);
		cMenu.deleteDocument(FILE_DOCUMENT);
		cMenu.deleteDocument(UPLOAD_FILE_NAME);

		//Goto Trash driver
		actBar.chooseDrive(ecms.ELEMENT_TRASH_DRIVE);

		//Verify Delete Nodes: Search by Name
		assert siteExp.simpleSearch(DATA_CONTENT_FOLDER): "Can not found deleted content folder in Trash";
		assert siteExp.simpleSearch(DATA_FILE_NAME): "Can not found deleted content folder in Trash";
		assert siteExp.simpleSearch("Winter.jpg"): "Can not found deleted content folder in Trash";
	}

	/**
	 * 
	 * Qmetry ID: 101906
	 *  
	 */
	//Delete node in 'check in' status
	@Test
	public void test02_DeleteCheckInNode(){	
		String DATA_UPLOAD_FILE_PATH ="TestData/Winter.jpg";
		By UPLOAD_FILE_NAME = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", "Winter.jpg"));

		//goto Site Explorer
		navToolBar.goToSiteExplorer();

		//Upload a File
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH);
		waitForAndGetElement(UPLOAD_FILE_NAME);

		//Right click and check-in node
		cMenu.contextMenuAction(UPLOAD_FILE_NAME, cMenu.ELEMENT_MENU_CHECKIN);

		//Verify item Delete item not exist
		rightClickOnElement(UPLOAD_FILE_NAME);
		waitForElementNotPresent(cMenu.ELEMENT_MENU_DELETE);
		info("Cannot delete node being in check in status");

		//Delete data
		ecms.goToNode(UPLOAD_FILE_NAME);
		cMenu.contextMenuAction(UPLOAD_FILE_NAME, cMenu.ELEMENT_MENU_CHECKOUT);
		cMenu.deleteDocument(UPLOAD_FILE_NAME);
	}

	/**
	 * Qmetry ID: 102069 
	 * Delete single file from trash
	 * 
	 */
	@Test
	public void test03_DeleteSingleFileFromTrash(){
		String data1 = "ECMS_Admin_SendMailScript_Template.txt";
		String DATA_UPLOAD_FILE_PATH_1 ="TestData/" + data1;

		info("-- Upload a file --");
		navToolBar.goToPersonalDocuments();
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_1);

		info("-- Delete a file --");
		actBar.actionsOnElement(data1, actionType.DELETE);

		info("-- Delete in trash --");
		actBar.chooseDrive(ecms.ELEMENT_TRASH_DRIVE);
		click(ELEMENT_PERSONAL_DOCUMENTS);
		usePaginator(ecms.ELEMENT_UI_CHECKBOX.replace("${element}", data1), "Cannot find element...");
		click(ecms.ELEMENT_UI_CHECKBOX.replace("${element}", data1), 2);
		click(cMenu.ELEMENT_MENU_DELETE);
		magAlert.verifyAlertMessage("Are you sure you want to permanently remove the file 'ECMS_Admin_SendMailScript_Template.txt'?");
		dialog.deleteInDialog();
		waitForElementNotPresent(ecms.ELEMENT_UI_CHECKBOX.replace("${element}", data1));
	}

	/**
	 * Qmetry ID: 101902
	 * case07: Delete locked node by user is not locker
	 * create node by user John: content folder
	 * lock node by user John
	 * check user mary cannot delete this node
	 */
	@Test
	public void test07_DeleteLockedNodeByUserIsNotLocker(){
		String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_BasicAction_Delete_07";
		By ELEMENT_CONTENT_FOLDER = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", "ECMS_DMS_SE_BasicAction_Delete_07"));

		//create new content folder by John
		navToolBar.goToSiteExplorer();

		info("Create new content folder by user John");
		cTemplate.createNewFolder(DATA_CONTENT_FOLDER, folderType.Content);
		info("Create new content folder successfully");

		//lock node
		cMenu.contextMenuAction(ELEMENT_CONTENT_FOLDER, cMenu.ELEMENT_CONTEXT_MENU_LOCK);

		info("Lock node successfully");
		assert cMenu.isLockedNode(ELEMENT_CONTENT_FOLDER):"Lock node unsuccessfully";
		//            ---------------------------------------- try to delete it on the mary browser -------------------
		//login with user mary
		info("Initialize a new session and Login with Mary");
		loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		navToolBar = new NavigationToolbar(newDriver, this.plfVersion);
		magAcc = new ManageAccount(newDriver, this.plfVersion); 

		newDriver.navigate().refresh();
		Utils.pause(1000);
		navToolBar.goToSiteExplorer();

		WebElement myElement =newDriver.findElement(By.xpath("//*[@title = 'ECMS_DMS_SE_BasicAction_Delete_07']/.."));

		Actions action= new Actions(newDriver);
		action.contextClick(myElement).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();

		//check user mary can not delete node
		waitForElementNotPresent(newDriver.findElement(By.xpath(".//*[@class='uiIconEcmsDelete']/..")));
		info("User mary cannot delete this locker node");
		magAcc.signOut();

		//delete data
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(ELEMENT_CONTENT_FOLDER);
		newDriver.quit();
	}

	/**
	 * Qmetry ID: 101898
	 * case09: Delete child node while parent node is being locked by user is not locker
	 * create parent node: content folder by user John
	 * create child node: article document by user John
	 * lock parent node
	 * check user Mary can delete child node
	 */
	@Test
	public void test09_DeleteChildNodeWhileParentNodeIsBeingLockedByUserIsNotLocker(){
		String DATA_CONTENT_FOLDER = "ECMS_DMS_SE_BasicAction_Delete_content_folder_09";
		By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);
		String DATA_ARTICLE = "ECMS_DMS_SE_BasicAction_Delete_article_09";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE);

		//create new content folder by John
		navToolBar.goToSiteExplorer();
		info("Create new content folder by user John");
		cTemplate.createNewFolder(DATA_CONTENT_FOLDER, folderType.Content);
		info("Create new content folder successfully");

		//create new child node in content folder: article document
		ecms.goToNode(ELEMENT_CONTENT_FOLDER);
		actBar.goToAddNewContent();
		info("Create new article document");
		cTemplate.createNewFile(DATA_ARTICLE, DATA_ARTICLE, DATA_ARTICLE);

		//lock node
		ecms.goToNode(ELEMENT_CONTENT_FOLDER);
		cMenu.contextMenuAction(ELEMENT_CONTENT_FOLDER, cMenu.ELEMENT_CONTEXT_MENU_LOCK);
		info("Lock parent node");
		assert cMenu.isLockedNode(ELEMENT_CONTENT_FOLDER);
		driver.close();

		//login with mary
		initSeleniumTest();
		driver.get(baseUrl);
		ecms = new EcmsBase(driver);
		navToolBar = new NavigationToolbar(driver);
		magAcc = new ManageAccount(driver);
		cMenu = new ContextMenu(driver);
		magAcc.signIn(DATA_USER2, DATA_PASS);
		navToolBar.goToSiteExplorer();

		//check user mary can delete child node
		ecms.goToNode(ELEMENT_CONTENT_FOLDER);
		cMenu.deleteDocument(ELEMENT_ARTICLE);
		info("Delete child node successfully");
		magAcc.signOut();

		//delete data
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(ELEMENT_CONTENT_FOLDER);		
	}

	/**
	 * Qmetry ID: 101909
	 * 
	 */
	//case10: Delete node while user does not have remove right
	@Test
	public void test10_DeleteNodeByUserNotHaveRemoveRight(){
		String DATA_CONTENT_FOLDER = "ECMS_BasicAction_Delete_content_10";
		By CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER);

		//goto Site Explorer
		navToolBar.goToSiteExplorer();

		//Create a Content Folder
		cTemplate.createNewFolder(DATA_CONTENT_FOLDER, folderType.Content);

		//Add a view permission to Action Bar 
		actBar.addItem2ActionBar("viewPermissions", actBar.ELEMENT_PERMISSION_LINK);

		//Goto System > Permissions > Uncheck Remove Right for Group *:/platform/web-contributor
		doubleClickOnElement(CONTENT_FOLDER);
		actBar.goToNodePermissionManagement();

		ecms.selectMembership("Platform/Content Management", "*", "Select Membership");
		ecmsPer.setPermissionForNode(true, true, false);
		Utils.pause(9000);

		info("Save then close");
		button.save();
		button.close();

		//Sign out and Sign in as mary
		magAcc.signOut();
		magAcc.signIn(DATA_USER2, DATA_PASS);

		//goto Site Explorer
		navToolBar.goToSiteExplorer();

		//Verify Deleted item not existed
		waitForAndGetElement(CONTENT_FOLDER);
		rightClickOnElement(CONTENT_FOLDER);
		waitForElementNotPresent(cMenu.ELEMENT_MENU_DELETE);

		//Delete data
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);

		//goto Site Explorer
		navToolBar.goToSiteExplorer();

		//Verify Deleted item not existed
		waitForAndGetElement(CONTENT_FOLDER);
		cMenu.deleteDocument(CONTENT_FOLDER);
	}


	/**
	 * Qmetry ID: 102059
	 * Delete document with references
	 * <li>Go to Sites Explorer</li>
	 * <li>Create 2 nodes</li> 
	 * <li>Add a relation</li>
	 * <li>Delete a document</li>
	 */
	@Test
	public void test12_DeleteDocumentWithReferences(){
		String webContentName_0 = "deleteDocumentWithRef";
		String content_0 = "Delete document with references";
		String webContentName_1 = "deleteDocumentWithRef_1";
		String content_1 = "Delete document with references_1";
		String pathTowebContentName_1 = "sites/" + webContentName_1;

		Object[] paras = {true,"Authoring","manageRelations"};

		info("make relations available");
		ecMain.goToManageViews();
		mnView.editView("Web", "", false, false, paras);

		info("-- logout to take effect --");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);

		info("-- Go to Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("-- Create 2 nodes --");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(webContentName_0, content_0, "", "", "", "");
		click(siteExp.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		waitForTextNotPresent(content_0);
		driver.navigate().refresh();
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(webContentName_1, content_1, "", "", "", "");

		info("-- Create a relation between 2 nodes --");
		actBar.addItem2ActionBar("manageRelations", actBar.ELEMENT_ADD_RELATION_LINK);
		actBar.createRelation(webContentName_0, pathTowebContentName_1);

		info("-- Delete a document --");
		rightClickOnElement(By.linkText(webContentName_0));
		click(cMenu.ELEMENT_MENU_DELETE);
		magAlert.verifyAlertMessage("Are you sure you want to delete the item "+ "'" + webContentName_0 + "'" + " and its references?");
		Utils.captureScreen("Delete_document_with_references");
		dialog.deleteInDialog();
		driver.navigate().refresh();
		waitForElementNotPresent(By.linkText(webContentName_0));

		cMenu.deleteDocument(By.linkText(webContentName_1));
	}

	/**
	 * Qmetry ID: 102063
	 * Delete multiple documents with references
	 * <li>Go to Sites Explorer/Collaboration drive </li>
	 * <li>Create 2 nodes </li>
	 * <li>Add a relation</li>
	 * <li>Delete a document</li>
	 */
	@Test
	public void test13_DeleteMultipleDocumentsWithReferences(){
		String webContentName_0 = "deleteMultipleDocumentWithRef";
		String content_0 = "Delete multiple documents with references";

		String webContentName_1 = "deleteMultipleDocumentWithRef_1";
		String content_1 = "Delete multiple documents with references_1";
		Object[] paras = {true,"Authoring","manageRelations"};

		info("make relations available");
		ecMain.goToManageViews();
		mnView.editView("Web", "", false, false, paras);

		info("-- logout to take effect --");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);
		info("-- Go to Sites Explorer --");
		navToolBar.goToSiteExplorer();
		actBar.chooseDrive(ecms.ELEMENT_COLLABORATION_DRIVE);

		info("-- Create 2 nodes --");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(webContentName_0, content_0, "", "", "", "");
		click(siteExp.ELEMENT_SIDEBAR_COLLABORATION);
		waitForTextNotPresent(content_0);
		driver.navigate().refresh();
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(webContentName_1, content_1, "", "", "", "");
		click(siteExp.ELEMENT_SIDEBAR_COLLABORATION);

		info("-- Create a relation between 2 nodes --");
		actBar.addItem2ActionBar("manageRelations", actBar.ELEMENT_ADD_RELATION_LINK);
		actBar.chooseDrive(ecms.ELEMENT_COLLABORATION_DRIVE);
		actBar.createRelation(webContentName_0, webContentName_1);

		click(siteExp.ELEMENT_SIDEBAR_COLLABORATION);

		info("-- Delete a document --");
		WebElement file1 = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", webContentName_0));
		WebElement file2 = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", webContentName_1));
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(file1).click(file2).keyUp(Keys.CONTROL).build().perform();
		rightClickOnElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", webContentName_0));

		((JavascriptExecutor)driver).executeScript("arguments[0].click();", waitForAndGetElement(cMenu.ELEMENT_MENU_DELETE_RIGHT_CLICK_POPUP, DEFAULT_TIMEOUT, 1));
		magAlert.verifyAlertMessage("Are you sure you want to delete the 3 selected items and their references?");
		Utils.captureScreen("Delete_multiple_document_with_references");

		button.cancel();
		cMenu.deleteDocument(By.linkText(webContentName_0));
		cMenu.deleteDocument(By.linkText(webContentName_1));
	}

	/**
	 * Qmetry ID: 102061
	 * Delete multiple files and folders with references
	 */
	@Test
	public void test14_DeleteMultipleFilesAndFoldersWithReferences(){
		String data1 = "Delete_multiple_files_folder_1.txt";
		String data2 = "Delete_multiple_files_folder_2.txt";		
		String DATA_UPLOAD_FILE_PATH_1 = "TestData/" + data1;
		String DATA_UPLOAD_FILE_PATH_2 = "TestData/" + data2;
		String folderName = "deleteMutipleFilesFolders";
		Object[] paras = {true,"Authoring","manageRelations"};

		info("make relations available");
		ecMain.goToManageViews();
		mnView.editView("Web", "", false, false, paras);

		info("-- logout to take effect --");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);

		info("-- Create files and folder --");
		navToolBar.goToSiteExplorer();
		actBar.chooseDrive(ecms.ELEMENT_COLLABORATION_DRIVE);

		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_1);
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_2);		
		cTemplate.createNewFolder(folderName, folderType.None);

		info("-- Add a relation for File --");
		//actBar.addRelationToActionBar();
		actBar.addItem2ActionBar("manageRelations", actBar.ELEMENT_ADD_RELATION_LINK);
		actBar.chooseDrive(ecms.ELEMENT_COLLABORATION_DRIVE);
		actBar.createRelation(data1, data2);
		click(siteExp.ELEMENT_SIDEBAR_COLLABORATION);
		Utils.pause(1000);
		info("-- Delete multiple items with reference --");
		WebElement folder = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", folderName));
		WebElement file1 = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data1));
		WebElement file2 = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data2));

		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(folder).click(file2).click(file1).keyUp(Keys.CONTROL).build().perform();
		rightClickOnElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data1));

		//click(cMenu.ELEMENT_MENU_DELETE_RIGHT_CLICK_POPUP);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", waitForAndGetElement(cMenu.ELEMENT_MENU_DELETE_RIGHT_CLICK_POPUP, DEFAULT_TIMEOUT, 1));

		magAlert.verifyAlertMessage("Are you sure you want to delete the 4 selected files, their references, folders and all subfolders?");
		Utils.captureScreen("Delete_multiple_Files_Folders_with_references");

		dialog.deleteInDialog();
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data1));
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data2));
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", folderName));
	}

	/**
	 * Qmetry ID: 102060
	 * Delete multiple files with references
	 * 
	 */
	@Test
	public void test15_DeleteMultipleFilesWithReferences(){
		String data1 = "ECMS_Admin_SendMailScript_Template.txt"; 
		String data2 = "KS_WiKi_Attachment_TxtFile.txt";		
		String DATA_UPLOAD_FILE_PATH_1 ="TestData/" + data1;
		String DATA_UPLOAD_FILE_PATH_2 ="TestData/" + data2;

		Object[] paras = {true,"Authoring","manageRelations"};

		info("make relations available");
		ecMain.goToManageViews();
		mnView.editView("Web", "", false, false, paras);

		info("-- logout to take effect --");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);


		info("-- Upload 2 files --");
		navToolBar.goToSiteExplorer();
		actBar.chooseDrive(ecms.ELEMENT_COLLABORATION_DRIVE);

		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_1);
		driver.navigate().refresh();
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_2);

		info("-- Add a relation --");

		actBar.createRelation(data1, data2);
		click(siteExp.ELEMENT_SIDEBAR_COLLABORATION);
		Utils.pause(1000);

		info("-- Delete files with reference --");
		WebElement file1 = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data1));
		WebElement file2 = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data2));

		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(file1).click(file2).keyUp(Keys.CONTROL).build().perform();
		rightClickOnElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data1));

		((JavascriptExecutor)driver).executeScript("arguments[0].click();", waitForAndGetElement(cMenu.ELEMENT_MENU_DELETE_RIGHT_CLICK_POPUP, DEFAULT_TIMEOUT, 1));

		magAlert.verifyAlertMessage("Are you sure you want to delete the 3 selected files and their references?");
		Utils.captureScreen("Delete_multiple_files_with_references");
		dialog.deleteInDialog();
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data1));
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data2));		
	}

	/**
	 * Qmetry ID: 102062
	 * Delete multiple files, folders and items with references
	 * 
	 */
	@Test
	public void test16_DeleteMultipleFilesFoldersAndItemsWithReferences(){
		String data1 = "ECMS_Admin_SendMailScript_Template.txt"; 
		String data2 = "KS_WiKi_Attachment_TxtFile.txt";		
		String DATA_UPLOAD_FILE_PATH_1 ="TestData/" + data1;
		String DATA_UPLOAD_FILE_PATH_2 ="TestData/" + data2;
		String folderName = "deleteMutipleFilesFoldersItems";
		String webContentName = "webContent";
		String content = "Delete multiple files, folders and items with references";
		Object[] paras = {true,"Authoring","manageRelations"};

		info("make relations available");
		ecMain.goToManageViews();
		mnView.editView("Web", "", false, false, paras);

		info("-- logout to take effect --");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);

		info("-- Create documents and folders --");
		navToolBar.goToSiteExplorer();
		actBar.chooseDrive(ecms.ELEMENT_COLLABORATION_DRIVE);

		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_1);
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_2);
		cTemplate.createNewFolder(folderName, folderType.None);

		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(webContentName, content, "", "", "", "");
		click(siteExp.ELEMENT_SIDEBAR_COLLABORATION);
		waitForTextNotPresent(content);

		info("-- Add a relation --");
		actBar.createRelation(data1, data2);
		click(siteExp.ELEMENT_SIDEBAR_COLLABORATION);
		Utils.pause(1000);

		info("-- Delete multiple files and folder with relation --");

		WebElement folder = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", folderName));
		WebElement file1 = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data1));
		WebElement file2 = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data2));
		WebElement webC = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", webContentName));

		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(folder).click(file2).click(file1).click(webC).keyUp(Keys.CONTROL).build().perform();
		rightClickOnElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data1));

		//click(cMenu.ELEMENT_MENU_DELETE_RIGHT_CLICK_POPUP);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", waitForAndGetElement(cMenu.ELEMENT_MENU_DELETE_RIGHT_CLICK_POPUP, DEFAULT_TIMEOUT, 1));

		magAlert.verifyAlertMessage("Are you sure you want to delete the 5 selected items, files, their references, folders and all subfolders?");
		Utils.captureScreen("Delete_multiple_files_folders_Items_with_references");
		dialog.deleteInDialog();
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", folderName));
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data1));
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", data2));
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", webContentName));
	}



	/**
	 * Qmetry ID: 102058
	 * Delete single file with references 
	 */
	@Test
	public void test18_DeleteSingleFileWithReferences(){
		info("-- Create documents --");
		String data1 = "KS_WiKi_Attachment_TxtFile.txt";
		String DATA_UPLOAD_FILE_PATH_1 ="TestData/" + data1;
		String webContentName = "webContent";
		String content = "Delete single file with references";
		Object[] paras = {true,"Authoring","manageRelations"};

		info("make relations available");
		ecMain.goToManageViews();
		mnView.editView("Web", "", false, false, paras);

		info("-- logout to take effect --");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);

		info("-- Create documents and folders --");
		navToolBar.goToSiteExplorer();

		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_1);
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(webContentName, content, "", "", "", "");

		info("-- Add a relation --");
		//actBar.addRelationToActionBar();
		//actBar.addItem2ActionBar("manageRelations", actBar.ELEMENT_ADD_RELATION_LINK);
		actBar.createRelation(webContentName, "sites/" + data1);
		click(siteExp.ELEMENT_SIDEBAR_SITES_MANAGEMENT);

		info("-- Delete document with relation --");
		rightClickOnElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", webContentName));
		click(cMenu.ELEMENT_MENU_DELETE);
		waitForTextPresent("Are you sure you want to delete the item " + "'" + webContentName + "'" + " and its references?");
		dialog.deleteInDialog();
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}", webContentName));
		cMenu.deleteDocument(By.linkText(data1));
	}

	/**
	 * Qmetry ID: 102065
	 * Undo delete single file
	 * 
	 */
	@Test
	public void test19_UndoDeleteSingleFile(){
		String data1 = "ECMS_Undo_Delete_1.txt";
		String DATA_UPLOAD_FILE_PATH_1 = "TestData/" + data1;

		info("-- Upload a file --");
		navToolBar.goToPersonalDocuments();
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_1);

		info("-- Delete a file --");
		actBar.actionsOnElement(data1, actionType.DELETE);		
		waitForAndGetElement(ecms.MESSAGE_ITEM_DELETED_SUCCESSFULLY.replace("${title}", data1));

		info("-- Undo Deletion --");
		actBar.undoDeletion();
		waitForAndGetElement(ecms.MESSAGE_ITEM_RESTORED_SUCCESSFULLY.replace("${title}", data1));

		actBar.actionsOnElement(data1, actionType.DELETE);
	}

	/**
	 * Qmetry ID: 102068 
	 * Undo delete multiple items
	 * 
	 */
	@Test
	public void test20_UndoDeleteMultipleItems(){
		String data1 = "ECMS_Undo_Delete_2.txt";
		String data2 = "ECMS_Undo_Delete_3.txt";
		String DATA_UPLOAD_FILE_PATH_1 ="TestData/" + data1;
		String DATA_UPLOAD_FILE_PATH_2 ="TestData/" + data2;

		info("-- Upload a file --");
		navToolBar.goToPersonalDocuments();
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_1);
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_2);

		info("-- Delete Files--");
		click(ELEMENT_PERSONAL_DOCUMENTS);
		click(ecms.ELEMENT_UI_CHECKBOX.replace("${element}", data1), 2);
		click(ecms.ELEMENT_UI_CHECKBOX.replace("${element}", data2), 2);
		click(actBar.ELEMENT_DELETE_NODE_ICON);
		dialog.deleteInDialog();
		waitForAndGetElement(ecms.MESSAGE_MULTI_ITEMS_DELETED_SUCCESSFULLY.replace("${title}", "2 items"));

		info("-- Undo Deletion --");
		actBar.undoDeletion();
		waitForAndGetElement(ecms.MESSAGE_MULTI_ITEMS_RESTORED_SUCCESSFULLY.replace("${title}", "2 items"));

		actBar.actionsOnElement(data1, actionType.DELETE);
		actBar.actionsOnElement(data2, actionType.DELETE);
	}

	/**
	 * Qmetry ID: 74528
	 * Undo Delete with relations
	 * 
	 */
	@Test
	public void test21_UndoDeleteWithRelations(){
		String data1 = "ECMS_Undo_Delete_4.txt";
		String data2 = "ECMS_Undo_Delete_5.txt";
		String DATA_UPLOAD_FILE_PATH_1 ="TestData/" + data1;
		String DATA_UPLOAD_FILE_PATH_2 ="TestData/" + data2;

		Object[] paras = {true,"Authoring","manageRelations"};

		info("make relations available");
		ecMain.goToManageViews();
		mnView.editView("Web", "", false, false, paras);

		info("-- logout to take effect --");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);

		info("-- Upload a file --");
		navToolBar.goToPersonalDocuments();
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_1);
		ecms.uploadFile(DATA_UPLOAD_FILE_PATH_2);
		
		info("-- Add a relation --");
		actBar.createRelation("ECMS_Undo_Delete_4", "Users/j___/jo___/joh___/john/Private/"+ data2, true);
		click(ecms.ELEMENT_BACK_PREVIOUS_NODE);
		
		info("-- Delete Files--");
		click(ELEMENT_PERSONAL_DOCUMENTS);
		click(ecms.ELEMENT_UI_CHECKBOX.replace("${element}", data1), 2);
		actBar.actionsOnElement(data1, actionType.DELETE);
		
		info("-- Undo Deletion --");
		actBar.undoDeletion();
		waitForAndGetElement(ecms.MESSAGE_MULTI_ITEMS_RESTORED_SUCCESSFULLY.replace("${title}", "1 items"));

		info("-- Review a relation --");
		ecms.goToNode("ECMS_Undo_Delete_4", true);
		click(actBar.ELEMENT_ADD_RELATION_LINK);
		waitForTextPresent(data2);
		button.close();
		
		actBar.actionsOnElement(data1, actionType.DELETE);
		actBar.actionsOnElement(data2, actionType.DELETE);

	} 


}
