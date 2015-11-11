package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.basicaction;

import static org.exoplatform.selenium.TestLogger.*;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate.folderType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author vuna2
 * Update for PLF4
 * May, 2013
 */
/*
 * @author: ThuNTN
 * @date: 24/09/2012
 */
public class ECMS_SE_BasicAction_Edit extends PlatformBase {
	//Platform
	Button button;
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;
	Dialog dialog;
	ManageAlert magAlert;

	//Ecms
	EcmsBase ecms;
	EcmsPermission ecmsPer;
	ContentTemplate cTemplate;
	ContextMenu cMenu;
	SitesExplorer siteExp;

	String DATA_UPLOAD_IMG_2 = "TestData/FNC_ECMS_FEX_ACTION_09_2.png";
	String DATA_UPLOAD_IMG_1 = "TestData/FNC_ECMS_FEX_ACTION_09_1.png";

	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);
		dialog = new Dialog(driver);
		button = new Button(driver, this.plfVersion);
		magAlert = new ManageAlert(driver);
		magAcc = new ManageAccount(driver, this.plfVersion);
		navToolBar = new NavigationToolbar(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		ecms = new EcmsBase(driver, this.plfVersion);
		ecmsPer = new EcmsPermission(driver);
		cTemplate = new ContentTemplate(driver, this.plfVersion);
		cMenu = new ContextMenu(driver);
		siteExp = new SitesExplorer(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
	}
	
	@AfterMethod
	public void afterMethod() {
		//logoutEcms();
		driver.quit();
	}
	

	/**
	 * Qmetry ID: 101874
	 * Edit CSS file
	 * 
	 */
	@Test
	public void test01_EditCssFile(){	
		String DATA_CSS_TITLE_39 = "file101874";
		By bDocument = By.linkText(DATA_CSS_TITLE_39);
		String filePath1 = "TestData/ECMS_CSS_File_After.txt";
		String filePath0 = "TestData/ECMS_CSS_File_Before.txt";
		String data_1 = Utils.getFileContent(filePath1);
		String data_0 = Utils.getFileContent(filePath0);

		//Switch to ACME Site
		driver.get(DEFAULT_PLF_URL + "/acme");

		info("Edit a css file");
		//choose  site management drive, and create a css file 	
		navToolBar.goToSiteExplorer();
		ecms.goToNode("acme/css");
		actBar.goToAddNewContent();
		cTemplate.createNewCssFile(DATA_CSS_TITLE_39, "0", data_0, true);
		click(ELEMENT_OVERVIEW);
		driver.navigate().refresh();
		Utils.captureScreen("ECMS_Before_Edit_Css_File");

		//edit and delete css file
		navToolBar.goToSiteExplorer();
		ecms.goToNode("acme/css");
		cTemplate.editCssFile(DATA_CSS_TITLE_39, "1", data_1, true);

		//Verify the changes
		click(ELEMENT_OVERVIEW);
		driver.navigate().refresh();
		Utils.captureScreen("ECMS_After_Edit_Css_File");

		WebElement element = driver.findElement(By.className("title"));
		String Script = "return document.defaultView.getComputedStyle(arguments[0], null).getPropertyValue('color');";
		String styleValue = (String)((JavascriptExecutor)driver).executeScript(Script, element);
		info("-- Dispalyed Color -- " + styleValue);
		assert styleValue.equals("rgb(51, 51, 51)"): "cannot set the background color of ....";	

		//reset data
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(bDocument);
	}
	/**
	 * Qmetry ID: 101876
	 * Edit document  when user does not have Set Property Right
	 * 
	 */
	//edit a document by a user with no edit permission
	@Test
	public void test02_EditDocumentWhenUserDoesNotHaveSetPropertyRight(){	
		String DATA_FILE_TITLE_35 = "FNC_ECMS_FEX_ACTION_09_35";
		By bDocument = By.linkText(DATA_FILE_TITLE_35);

		info("Edit document in checked-in document");
		//choose  site management drive, and create an article document
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_TITLE_35, DATA_FILE_TITLE_35, DATA_FILE_TITLE_35);

		//set permission for node: james has not Edit permission
		actBar.goToNodePermissionManagement();
		ecmsPer.deletePermission("*:/platform/web-contributors", true);
		ecmsPer.deletePermission("any", true);
		ecms.selectUser(DATA_USER3);
		ecmsPer.setPermissionForNode(true, false, false);
		button.save();
		button.close();

		info("Login by user who does not have edit node right");
		magAcc.signOut();
		magAcc.signIn(DATA_USER3, DATA_PASS);

		navToolBar.goToSiteExplorer();
		ecms.goToNode(bDocument);
		waitForElementNotPresent(actBar.ELEMENT_EDIT_LINK);

		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);

		//delete data
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(bDocument);
	}
	
	/**
	 * Qmetry ID: 101877
	 * Edit Free layout content in edit mode
	 * 
	 */
	@Test
	public void test03_EidtFreeLayoutContentInEditMode(){
		String DATA_FILE_TITLE_03 = "EMCS_LAyout_03";
		By bDocument = By.linkText(DATA_FILE_TITLE_03);
		String newDATA="new data 03";

		//create a content in acme web contents
		ecms.goToNode("acme/web contents");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(DATA_FILE_TITLE_03, DATA_FILE_TITLE_03, "", "", "", "");
		
		// Turn on the edit mode
		mouseOverAndClick(ELEMENT_MENU_EDIT_LINK);
		click(ELEMENT_MENU_EDIT_CONTENT, 2); 
		Utils.pause(3000);
		WebElement contentEdit = waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_CONTENT_EDIT_MODE.replace("${content}",DATA_FILE_TITLE_03));
		
		// edit the content
		contentEdit.sendKeys(newDATA);
		click(siteExp.ELEMENT_ACCEPT_EDIT);
		Utils.pause(1000);
		driver.navigate().refresh();
		waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_CONTENT_EDIT_MODE.replace("${content}",""+newDATA+""+DATA_FILE_TITLE_03), 3000, 0);
		
		// delete data
		cMenu.deleteData(bDocument);
	}
	
	/*
	 * Test ID : 101878
	 * Edit Web content by Edit button in Action bar.
	 * 
	 */
	@Test
	public void test04_EditWebContentByEditButtonInActionBar(){
		String DATA_FILE_TITLE_04 = "EMCS_LAyout_04";
		By bDocument = By.linkText(DATA_FILE_TITLE_04);
		String newDATA="new data 04";
		
		//create a content in acme web contents
		ecms.goToNode("acme/web contents");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(DATA_FILE_TITLE_04, DATA_FILE_TITLE_04, "", "", "", "");
		
		cTemplate.editWebContent(DATA_FILE_TITLE_04, newDATA, "", "", "", "");
		
		waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_NAME_DOCUMENT_VIEW.replace("${content}", newDATA));

		// delete data
		cMenu.deleteData(bDocument);
	}
	
	
	/**
	 * Qmetry ID: 101880
	 * Edit a JS file
	 * 
	 */ 
	@Test
	public void test05_EditJsFile(){	
		String DATA_JS_TITLE_40 = "FNC_ECMS_FEX_ACTION_09_40";
		By bDocument = By.linkText(DATA_JS_TITLE_40);
		String filePath = "TestData/ECMS_JS_File.txt";
		String data = Utils.getFileContent(filePath);
		String data_0 = "alert(\"Before Edit: Hello!\");";

		info("Edit a js file");
		//choose site management drive, and create a js file 	
		ecms.goToNode("intranet/js");
		actBar.goToAddNewContent();
		//cTemplate.createNewJsFile(DATA_JS_TITLE_40, "0", "");
		cTemplate.createNewJsFile(DATA_JS_TITLE_40, "0", data_0, true);
		click(ELEMENT_PERSONAL_DOCUMENTS);
		Utils.captureScreen("ECMS_Before_Edit_JS_File");
		magAlert.acceptAlert();

		//edit js file
		navToolBar.goToSiteExplorer();
		magAlert.acceptAlert();
		cTemplate.editJsFile(DATA_JS_TITLE_40, "1", data, true);

		//Expected result: display [alert] with the changed message
		click(ELEMENT_PERSONAL_DOCUMENTS);
		Utils.captureScreen("ECMS_After_Edit_JS_File");
		magAlert.acceptAlert();

		//delete js file
		navToolBar.goToSiteExplorer();
		magAlert.acceptAlert();
		cMenu.deleteDocument(bDocument);
	}
	
	/**
	 * Qmetry ID: 101881
	 * Edit locked document by user is locker
	 * 
	 */
	@Test
	public void test06_EditLockedDocumentByUserIsLocker(){	
		String DATA_FILE_TITLE_30 = "FNC_ECMS_FEX_ACTION_09_30";
		By bDocument = By.linkText(DATA_FILE_TITLE_30);

		info("Edit a locked document");
		//choose  site management drive, and create a locked document
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_TITLE_30, DATA_FILE_TITLE_30, DATA_FILE_TITLE_30);
		cMenu.contextMenuAction(bDocument, cMenu.ELEMENT_CONTEXT_MENU_LOCK);
		assert cMenu.isLockedNode(bDocument): "Lock node unsuccessfully";

		//edit File
		actBar.goToEditDocument(DATA_FILE_TITLE_30);

		//Edit Document form appears: current information of document is displayed in form. Name is disable, can not be changed
		isTextPresent(DATA_FILE_TITLE_30);
		WebElement element = waitForAndGetElement(cTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX);
		isElementPresent(element.getAttribute("disabled"));
		button.close();

		cMenu.deleteDocument(bDocument);
	}
	
	/**
	 * Qmetry ID: 101882
	 * case31: Edit locked document by user isn't locker
	 * create new document with user John
	 * Lock document by John
	 * check can not edit this document with user Mary
	 */
	@Test
	public void test07_EditLockedDocumentByUserIsNotLocker(){
		String DATA_FILE_TITLE = "FNC_ECMS_FEX_ACTION_09_31";
		By bDocument = By.linkText(DATA_FILE_TITLE);

		//create new document with John: FILE document
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_TITLE, DATA_FILE_TITLE, DATA_FILE_TITLE);
		info("Create new File document is successful");

		//lock node with John
		cMenu.contextMenuAction(bDocument, cMenu.ELEMENT_CONTEXT_MENU_LOCK);

		//check lock node
		assert cMenu.isLockedNode(bDocument): "Lock node unsuccessfully";
		

		//login with mary
		info("Initialize a new session and Login with Mary");
		loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		magAcc = new ManageAccount(newDriver);
		navToolBar = new NavigationToolbar(newDriver,this.plfVersion);
		ecms = new EcmsBase(newDriver,this.plfVersion);
		cMenu = new ContextMenu(newDriver,this.plfVersion);
		actBar = new ActionBar(newDriver,this.plfVersion);
		siteExp = new SitesExplorer(newDriver,this.plfVersion);

		//check can not edit this document with user Mary
		navToolBar.goToSiteExplorer();
		ecms.goToNode(DATA_FILE_TITLE);
		
		WebElement myElement =newDriver.findElement(By.xpath("//*[@title = '"+DATA_FILE_TITLE+"']/.."));
		Actions action= new Actions(newDriver);
		action.contextClick(myElement).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
		magAcc.waitForElementNotPresent(cMenu.ELEMENT_MENU_EDIT);
		info("Cannot edit a locked document with user is not locker");

		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(bDocument);
		
		driver.close();
		newDriver.close();
	}
	
	
	/**
	 * Qmetry ID: 101883
	 * Edit name of File document
	 * <li>Input: Move mouse to [Name] field and try to edit name of document</li>
	 * <li>Output: Can not edit name of document </li>
	 */
	@Test
	public void test08_EditNameOfFileDocument() {
		String DATA_FILE_NAME_12="FNC_ECMS_FEX_ACTION_09_12";
		By bDocument=By.linkText(DATA_FILE_NAME_12);
		info("Edit name of File document");

		//choose  site management drive, and create a file document
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_NAME_12, DATA_FILE_NAME_12, DATA_FILE_NAME_12);

		//edit a file document
		actBar.goToEditDocument(DATA_FILE_NAME_12);

		//verify that user cannot edit "Name" field
		WebElement element = waitForAndGetElement(cTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX);
		isElementPresent(element.getAttribute("disabled"));
		button.close();

		//delete document
		cMenu.deleteDocument(bDocument);
	}
	
	/**
	 * Qmetry ID: 101884
	 * Edit node is in check in status
	 * 
	 */
	//edit checked in document
	@Test
	public void test09_EditNodeIsInCheckInStatus(){	
		String DATA_FILE_TITLE_33 = "FNC_ECMS_FEX_ACTION_09_33";
		By bDocument = By.linkText(DATA_FILE_TITLE_33);

		info("Edit locked document");
		//choose  site management drive, and create an article document
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_TITLE_33, DATA_FILE_TITLE_33, DATA_FILE_TITLE_33);
		cMenu.contextMenuAction(bDocument, cMenu.ELEMENT_MENU_CHECKIN);

		//user cannot see the [Edit] icon in ActionBar or in [Right Click Menu]
		ecms.goToNode(DATA_FILE_TITLE_33);
		waitForElementNotPresent(actBar.ELEMENT_EDIT_LINK);

		rightClickOnElement(bDocument);
		waitForElementNotPresent(cMenu.ELEMENT_MENU_EDIT);

		//checkOutNode
		cMenu.contextMenuAction(bDocument, cMenu.ELEMENT_MENU_CHECKOUT);

		//resetData
		cMenu.deleteDocument(bDocument);
	}
	
	/**
	 * Qmetry ID: 101885
	 * Edit node is the file has uploaded
	 * 
	 */
	//edit uploaded file
	@Test
	public void test10_EditNodeIsTheFileHasUploaded(){	
		String DATA_UPLOAD_TITLE_29 = "FNC_ECMS_FEX_ACTION_09_2";
		String DATA_UPLOAD_FILE_TXT = "TestData/test.txt";
		By bDocument = By.linkText(DATA_UPLOAD_TITLE_29 + ".png");
		By bText = By.linkText("test.txt");

		info("Edit an uploaded file");
		//choose  site management drive, and create a uploaded file
		ecms.uploadFile(DATA_UPLOAD_IMG_2);
		ecms.uploadFile(DATA_UPLOAD_FILE_TXT);

		//edit an uploaded file
		//Show the form displaying information of file
		actBar.goToEditDocument(DATA_UPLOAD_TITLE_29 + ".png");
		waitForAndGetElement(cTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX);
		Utils.captureScreen("ECMS_Edit_Uploaded_File_Image");
		button.close();

		actBar.goToEditDocument("test.txt");
		waitForAndGetElement(cTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX);
		Utils.captureScreen("ECMS_Edit_Uploaded_File_Txt"); 
		button.close();

		cMenu.deleteDocument(bDocument);
		cMenu.deleteDocument(bText);
	}
	
	/**
	 * Qmetry ID: 101942
	 * Edit a Document when click on both [Save] button and [Close] button
	 * 
	 */
	@Test
	public void test11_EditDocumentWhenClickOnSaveAndCloseButtons() {
		String DATA_FILE_TITLE_05 = "FNC_ECMS_FEX_ACTION_09_05";
		String DATA_FILE_TITLE_05_EDIT = "FNC_ECMS_FEX_ACTION_09_05 edit";
		By bDocument = By.linkText(DATA_FILE_TITLE_05);

		info("Edit document by clicking on  Save and Close buttons");
		//choose  site management drive, and create an article
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_TITLE_05, DATA_FILE_TITLE_05, DATA_FILE_TITLE_05);

		//edit File
		cTemplate.editFile(DATA_FILE_TITLE_05, DATA_FILE_TITLE_05_EDIT, DATA_FILE_TITLE_05_EDIT, 0);
		click(button.ELEMENT_CLOSE_BUTTON);

		//check after editing
		waitForAndGetElement(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", DATA_FILE_TITLE_05_EDIT), 3000, 0);

		//delete document
		cMenu.deleteDocument(bDocument);
	}
	
	/**
	 * Qmetry ID: 101943
	 * Edit document by clicking on [Close] button
	 *  
	 */
	@Test
	public void test12_EditADocumentWhenClickOnCloseButton() {
		String DATA_FILE_TITLE_04="FNC_ECMS_FEX_ACTION_09_04";
		String DATA_FILE_TITLE_04_EDIT="FNC_ECMS_FEX_ACTION_09_04 edit";
		By bDocument=By.linkText(DATA_FILE_TITLE_04);

		info("Edit document by clicking on Close button");
		//choose  site management drive, and create an article
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_TITLE_04, DATA_FILE_TITLE_04, DATA_FILE_TITLE_04);

		//edit article
		actBar.goToEditDocument(DATA_FILE_TITLE_04);
		type(cTemplate.ELEMENT_NEWFILE_TITLE_TEXTBOX, DATA_FILE_TITLE_04_EDIT, true);
		click(button.ELEMENT_CLOSE_BUTTON);
		Utils.pause(3000);
		magAlert.acceptAlert();
		button.yes(); 
		
		waitForElementNotPresent(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", DATA_FILE_TITLE_04_EDIT), 3000, 0);
		waitForAndGetElement(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", DATA_FILE_TITLE_04), 3000, 0);
		
		//delete document
		cMenu.deleteDocument(bDocument);
	}
	
	/**
	 * Qmetry ID: 101944 
	 * Edit document by clicking on [Save & Close] button
	 * 
	 */
	@Test
	public void test13_EditDocumentWhenClickOnSaveAndCloseButton(){
		String DATA_FILE_TITLE_02 = "FNC_ECMS_FEX_ACTION_09_02";
		String DATA_FILE_TITLE_02_EDIT = "FNC_ECMS_FEX_ACTION_09_02_Edit";
		By bDoc= By.linkText(DATA_FILE_TITLE_02);

		info("Edit document by clicking on Save & Close button");
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_TITLE_02, DATA_FILE_TITLE_02,DATA_FILE_TITLE_02);
		cTemplate.editFile(DATA_FILE_TITLE_02, DATA_FILE_TITLE_02_EDIT, DATA_FILE_TITLE_02_EDIT);

		waitForAndGetElement(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", DATA_FILE_TITLE_02_EDIT), 3000, 0);
		
		//delete document
		cMenu.deleteDocument(bDoc);
	}

	/**
	 * Qmetry ID: 101945
	 * Edit document by clicking on [Save] button
	 * 
	 */
	@Test
	public void test14_EditDocumentWhenClickOnSaveButton(){
		String DATA_FILE_TITLE_03="FNC_ECMS_FEX_ACTION_09_03";
		String DATA_FILE_TITLE_03_EDIT="FNC_ECMS_FEX_ACTION_09_03_Edit";
		By bDocEdit= By.linkText(DATA_FILE_TITLE_03);

		info("Edit document by clicking on Save button");
		//choose  site management drive, and create an article
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_TITLE_03, DATA_FILE_TITLE_03, DATA_FILE_TITLE_03);

		//edit a file
		cTemplate.editFile(DATA_FILE_TITLE_03, DATA_FILE_TITLE_03_EDIT, DATA_FILE_TITLE_03_EDIT, 0);
		button.saveAndClose();

		//delete document
		cMenu.deleteDocument(bDocEdit);
	}
	
	/** 
	 * Test ID: 101947
	 * Edit child node (nt:file & folder) has parent node is in 'Check in' status
	 * 
	 */
	@Test
	public void test15_EditChildNodeNtFileFolderHasParentNodeIsInCheckInStatus(){	
		String DATA_FILE_TITLE_90 = "FNC_ECMS_FEX_ACTION_09_90";
		//String DATA_UPLOAD_FILE_90 = "TestData/test.txt";
		String DATA_FOLDER_90 = "FNC_ECMS_FOLDER_DATA_90";
		By bDocument = By.linkText(DATA_FILE_TITLE_90);
		By bFolder = By.linkText(DATA_FOLDER_90);
		//By bTest = By.linkText("test.txt");

		info("Edit a document in a locked document");
		//choose site management drive, and create an article
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(DATA_FILE_TITLE_90, DATA_FILE_TITLE_90, "", "", "", "");

		info("add a child node: Create a folder");
		cTemplate.createNewFolder(DATA_FOLDER_90, folderType.Content);
		//ecms.uploadFile(DATA_UPLOAD_FILE_90);
		
		info("Check in parent node");
		cMenu.contextMenuAction(bDocument, cMenu.ELEMENT_MENU_CHECKIN);

		info("Select child node");
		ecms.goToNode(bFolder);

		info("expected: can not see Edit button in action Bar or right click into child node");
		waitForElementNotPresent(actBar.ELEMENT_EDIT_LINK);

		rightClickOnElement(bFolder);
		waitForElementNotPresent(cMenu.ELEMENT_MENU_EDIT);
		
		info("check out Parent Node");
		ecms.goToNode(bDocument);
		cMenu.contextMenuAction(bDocument, cMenu.ELEMENT_MENU_CHECKOUT);

		info("Reset Data");
		cMenu.deleteDocument(bDocument);	
	}
	
	
	/**
	 * Qmetry ID: 101948  
	 * Edit child node has parent node is in lock status
	 * 
	 */
	//edit a document in a locked document
	@Test
	public void test16_EditChildNodeHasParentNodeIsInLockStatus(){	
		String DATA_FILE_TITLE_32 = "FNC_ECMS_FEX_ACTION_09_32";
		String DATA_UPLOAD_FILE_32 = "TestData/test.txt";
		By bDocument = By.linkText(DATA_FILE_TITLE_32);

		info("Edit a document in a locked document");
		//choose site management drive, and create an article
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(DATA_FILE_TITLE_32, DATA_FILE_TITLE_32, "", "", "", "");

		//add a child node / upload a file
		ecms.uploadFile(DATA_UPLOAD_FILE_32);
		cMenu.contextMenuAction(bDocument, cMenu.ELEMENT_CONTEXT_MENU_LOCK);

		//check lock node
		assert cMenu.isLockedNode(bDocument): "Lock node unsuccessfully";

		//Select a child node and go to [Edit] 
		actBar.goToEditDocument("test.txt");

		//expected: can edit an uploaded file, and user cannot modify  'name' field
		WebElement element = waitForAndGetElement(cTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX);
		isElementPresent(element.getAttribute("disabled"));
		button.close();

		cMenu.deleteDocument(bDocument);	
	}

	/**
	 * Qmetry ID: 101949
	 * Edit content of file document
	 * 
	 */
	@Test
	public void test17_EditContentOfFile(){	
		String DATA_FILE_TITLE_13_EDIT = "FNC_ECMS_FEX_ACTION_09_13 edit";
		String DATA_FILE_TITLE_13 = "FNC_ECMS_FEX_ACTION_09_13";
		By bDocument = By.linkText(DATA_FILE_TITLE_13);

		info("Edit content of file document");
		//choose  site management drive, and create a file document	
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_TITLE_13, DATA_FILE_TITLE_13, DATA_FILE_TITLE_13);

		//edit and delete file
		cTemplate.editFile(DATA_FILE_TITLE_13, DATA_FILE_TITLE_13_EDIT, DATA_FILE_TITLE_13_EDIT);
		waitForAndGetElement(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", DATA_FILE_TITLE_13_EDIT), 3000, 0);
		
		info("Reset data");
		cMenu.deleteDocument(bDocument);
	}

	/**
	 * Qmetry ID: 102137
	 * Edit not-nt:file child node which has parent node is in [Check in] status
	 * 
	 */
	//edit a document in a checked in document
	@Test
	public void test18_EditNotNtFileChildNodeWhichHasParentNodeIsInCheckInStatus(){	
		String DATA_WEB_CONTENT_TITLE_34 = "FNC_ECMS_FEX_ACTION_09_34";
		String DATA_FILE_TITLE_34 = "FNC_ECMS_FEX_ACTION_09_34_File";
		By bDocument = By.linkText(DATA_WEB_CONTENT_TITLE_34);
		By bFile = By.linkText(DATA_FILE_TITLE_34);

		info("Edit a document in a checked in document");
		//choose  site management drive, and create an article document
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(DATA_WEB_CONTENT_TITLE_34, DATA_WEB_CONTENT_TITLE_34, "", "", "", "");

		//add a child node - File
		actBar.goToAddNewContent();
		cTemplate.createNewFile(DATA_FILE_TITLE_34, DATA_FILE_TITLE_34, DATA_FILE_TITLE_34);

		//check in  parent document
		cMenu.contextMenuAction(bDocument, cMenu.ELEMENT_MENU_CHECKIN);

		//user cannot see the [Edit] icon in ActionBar or in [Right Click Menu]
		ecms.goToNode(DATA_FILE_TITLE_34);
		waitForElementNotPresent(actBar.ELEMENT_EDIT_LINK);

		rightClickOnElement(bFile);
		waitForElementNotPresent(cMenu.ELEMENT_MENU_EDIT);

		//checkOutNode
		cMenu.contextMenuAction(bDocument, cMenu.ELEMENT_MENU_CHECKOUT);

		//reset data
		cMenu.deleteDocument(bDocument);
	}









	



//	/**
//	 * Qmetry ID: 
//	 * Edit Free layout content in public mode
//	 * 
//	 */
//	//Edit free layout content document
//	@Test
//	public void test36_EditFreeLayoutContentInPublicMode(){	
//		String DATA_FREE_TITLE_36 = "FNC_ECMS_FEX_ACTION_09_36";
//		String DATA_FREE_TITLE_36_EDIT = "FNC_ECMS_FEX_ACTION_09_36 edit";
//		By bDocument = By.linkText(DATA_FREE_TITLE_36);
//
//		info("Edit free layout content document");
//		//choose  site management drive, and create a free layout content	
//		actBar.goToAddNewContent();
//		cTemplate.createNewWebContent(DATA_FREE_TITLE_36, DATA_FREE_TITLE_36, DATA_UPLOAD_IMG_1, DATA_FREE_TITLE_36, "", "");
//		ecms.goToNode(DATA_FREE_TITLE_36 + "/medias/images/illustration");
//		Utils.captureScreen("ECMS_Before_Edit_WebContent_Change_Illustration_Image");
//
//		//edit and delete node
//		cTemplate.editWebContent(DATA_FREE_TITLE_36, DATA_FREE_TITLE_36_EDIT, "TestData/Winter.jpg", DATA_FREE_TITLE_36_EDIT, "", "");
//
//		//Verify the changes
//		ecms.goToNode(DATA_FREE_TITLE_36 + "/medias/images/illustration");
//		Utils.captureScreen("ECMS_After_Edit_WebContent_Change_Illustration_Image");
//
//		//reset data
//		cMenu.deleteDocument(bDocument);
//	}
//

//	//Edit a free layout content in-line
//	/*@Test
//	public void test37_EditFreeLayoutContentInline(){	
//
//	}*/
//
//	/**
//	 * Qmetry ID: 66449
//	 * Edit Picture on head web layout
//	 * exo:pictureOnHeadWebContent is changed to Illustrated Web Content
//	 */
//	@Test
//	public void test38_EditPictureOnHeadWebLayout(){	
//		String DATA_PICTURE_TITLE_38 = "FNC_ECMS_FEX_ACTION_09_38";
//		String DATA_PICTURE_TITLE_38_EDIT = "FNC_ECMS_FEX_ACTION_09_38 edit";
//		By bDocument = By.linkText(DATA_PICTURE_TITLE_38);
//
//		info("Edit a picture on head web layout ");
//
//		//choose site management drive, and create a picture on head web layout 	
//		actBar.goToAddNewContent();
//		cTemplate.createNewIllustratedWebContent(DATA_PICTURE_TITLE_38, DATA_PICTURE_TITLE_38, DATA_UPLOAD_IMG_1, "", "", "", "");
//		Utils.captureScreen("ECMS_Before_Edit_Illustrated_Web_Content");
//
//		//edit node and verify the changes
//		cTemplate.editIllustratedWebContent(DATA_PICTURE_TITLE_38, DATA_PICTURE_TITLE_38_EDIT, "", "TestData/Winter.jpg", 
//				DATA_PICTURE_TITLE_38_EDIT, "body {color:red;}", "", "250", "250");
//		Utils.captureScreen("ECMS_After_Edit_Illustrated_Web_Content_01");
//
//		ecms.goToNode(DATA_PICTURE_TITLE_38 + "/medias/images/illustration");
//		Utils.captureScreen("ECMS_After_Edit_Illustrated_Web_Content_02");
//
//		//reset data
//		cMenu.deleteDocument(bDocument);
//	}


}
