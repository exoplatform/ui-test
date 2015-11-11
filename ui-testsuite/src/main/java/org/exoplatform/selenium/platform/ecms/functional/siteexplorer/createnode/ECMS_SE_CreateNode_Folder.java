package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.TestLogger.warn;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageDrive;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate.folderType;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author vuna2
 * July, 16th, 2013
 * updated by anhpp
 */
public class ECMS_SE_CreateNode_Folder extends PlatformBase{
	//Platform
	Button button;
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;
	ManageAlert magAlt;
	ECMainFunction ecMain;
	ManageDrive magDrv;

	//Ecms
	EcmsBase ecms;
	EcmsPermission ecmsPer;
	ContextMenu cMenu;
	ContentTemplate cTemplate;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		button = new Button(driver);
		magAcc = new ManageAccount(driver);
		navToolBar = new NavigationToolbar(driver);
		actBar = new ActionBar(driver);
		ecms = new EcmsBase(driver);
		cMenu = new ContextMenu(driver);
		cTemplate = new ContentTemplate(driver);
		magAlt = new ManageAlert(driver);
		ecmsPer = new EcmsPermission(driver);
		ecMain = new ECMainFunction(driver);
		magDrv = new ManageDrive(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Qmetry ID: 102185
	 * Check if a normal user can add folder in a node locked by another user
	 *  
	 */
	@Test
	public void test01_CheckIfANormalUserCanAddFolderInANodeLockedByAnotherUser(){
		String DOCUMENT_FOLDER_TITLE = "ECMS_SE_Folder_DocumentFolder_01";

		info("Step 1: Create a document folder and lock this node");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(DOCUMENT_FOLDER_TITLE, folderType.Document);	
		cMenu.contextMenuAction(By.linkText(DOCUMENT_FOLDER_TITLE), cMenu.ELEMENT_CONTEXT_MENU_LOCK);
		assert cMenu.isLockedNode(By.linkText(DOCUMENT_FOLDER_TITLE)): "Failed to lock the node..." + DOCUMENT_FOLDER_TITLE;
		driver.close();

		info("Step 2: Add folder into locked node by another user");
		info("Init new session");
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver);
		navToolBar = new NavigationToolbar(driver);
		ecms = new EcmsBase(driver);
		cMenu = new ContextMenu(driver);

		info("Login with Mary");
		magAcc.signIn(DATA_USER2, DATA_PASS);

		info("Verify that [Mary] can not see [Add Folder] icon on action bar");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(By.linkText(DOCUMENT_FOLDER_TITLE));
		waitForElementNotPresent(cTemplate.ELEMENT_NEW_FOLDER_LINK);

		info("Restore data");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);

		//delete data
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(By.linkText(DOCUMENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102186
	 * Add folder in a node of which parent is in 'Check in' status
	 * updated by anhpp 
	 */
	@Test
	public void test02_AddFolderInANodeOfWhichParentIsInCheckInStatus(){
		String CONTENT_FOLDER_TITLE = "ECMS_SE_Folder_ContentFolder_02";
		String CONTENT_SUB_FOLDER_TITLE = "ECMS_SE_Folder_ContentSubFolder_02";

		info("Step 1: Create a web content and its sub-folder");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(CONTENT_FOLDER_TITLE, CONTENT_FOLDER_TITLE, "", "", "", "");
		cTemplate.createNewFolder(CONTENT_SUB_FOLDER_TITLE, folderType.Content);

		info("Check In Parent Node: " + CONTENT_FOLDER_TITLE);
		ecms.goToNode(By.linkText(CONTENT_FOLDER_TITLE));
		cMenu.contextMenuAction(By.linkText(CONTENT_FOLDER_TITLE), cMenu.ELEMENT_MENU_CHECKIN);

		info("Step 2: Check User cannot add sub-node into child node: folder, document");
		ecms.goToNode(By.linkText(CONTENT_SUB_FOLDER_TITLE));
		waitForElementNotPresent(cTemplate.ELEMENT_NEW_FOLDER_LINK);
		waitForElementNotPresent(actBar.ELEMENT_NEW_CONTENT_LINK);
		driver.close();

		info("Login by another user");
		info("Init new session");
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver);
		navToolBar = new NavigationToolbar(driver);
		ecms = new EcmsBase(driver);
		cMenu = new ContextMenu(driver);
		actBar = new ActionBar(driver);

		info("Login with Mary");
		magAcc.signIn(DATA_USER2, DATA_PASS);

		info("Verify that [Mary] can not see [Add Folder] icon on action bar");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(By.linkText(CONTENT_FOLDER_TITLE));
		ecms.goToNode(By.linkText(CONTENT_SUB_FOLDER_TITLE));
		waitForElementNotPresent(cTemplate.ELEMENT_NEW_FOLDER_LINK);
		waitForElementNotPresent(actBar.ELEMENT_NEW_CONTENT_LINK);

		info("Restore data");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);

		//delete data
		navToolBar.goToSiteExplorer();
		cMenu.contextMenuAction(By.linkText(CONTENT_FOLDER_TITLE), cMenu.ELEMENT_MENU_CHECKOUT);
		cMenu.deleteDocument(By.linkText(CONTENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102187
	 * Add folder in a node with 'Check in' status
	 *  
	 */
	@Test  
	public void test03_AddFolderInANodeWithCheckInStatus(){
		String CONTENT_FOLDER_TITLE = "ECMS_SE_Folder_ContentFolder_03";

		info("Step 1: Create a content folder and lock this node");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(CONTENT_FOLDER_TITLE, CONTENT_FOLDER_TITLE, "", "", "", "");	
		cMenu.contextMenuAction(By.linkText(CONTENT_FOLDER_TITLE), cMenu.ELEMENT_MENU_CHECKIN);

		info("Step 2: Check action for the check-in node: " + "Can't add child node for this node");
		ecms.goToNode(By.linkText(CONTENT_FOLDER_TITLE));
		waitForElementNotPresent(cTemplate.ELEMENT_NEW_FOLDER_LINK);
		waitForElementNotPresent(actBar.ELEMENT_NEW_CONTENT_LINK);

		info("Restore data");
		cMenu.contextMenuAction(By.linkText(CONTENT_FOLDER_TITLE), cMenu.ELEMENT_MENU_CHECKOUT);
		cMenu.deleteDocument(By.linkText(CONTENT_FOLDER_TITLE));
	}
	
	/**
	 * Qmetry ID: 102188
	 * Add folder when do not input data in [Name] field
	 *  
	 */
	@Test
	public void test04_AddFolderWhenDoNotInputDataInNameField(){
		String MESSAGE_NEW_FOLDER_ALERT = "Please enter the folder name.";

		info("Add a folder with blank name");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewFolder();
		selectOption(ecms.ELEMENT_FOLDER_TYPE_OPTION, ecms.ELEMENT_CONTENT_FOLDER_TYPE);
		click(cTemplate.ELEMENT_CREATE_FOLDER_BUTTON);
		magAlt.verifyAlertMessage(MESSAGE_NEW_FOLDER_ALERT);	

		actBar.goToAddNewFolder();
		selectOption(ecms.ELEMENT_FOLDER_TYPE_OPTION, ecms.ELEMENT_DOCUMENT_FOLDER_TYPE);
		click(cTemplate.ELEMENT_CREATE_FOLDER_BUTTON);
		magAlt.verifyAlertMessage(MESSAGE_NEW_FOLDER_ALERT);
	}

	/**
	 * Qmetry ID: 102189
	 * Add folder when user does not have permission to add node
	 * 
	 */
	@Test
	public void test05_AddFolderWhenUserDoesNotHavePermissionToAddNode(){
		String CONTENT_FOLDER_TITLE = "ECMS_SE_Folder_ContentFolder_06";

		info("Create a content folder and set a permission for users");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(CONTENT_FOLDER_TITLE, folderType.Content);
		ecms.goToNode(CONTENT_FOLDER_TITLE);
		actBar.goToNodePermissionManagement();
		ecmsPer.removeDefaultPermissionOfNode();
		ecms.selectUser(DATA_USER2);
		ecmsPer.setPermissionForNode(true, false, false);
		button.save();

		info("Login by user who has not permission to add node inside the above node");
		magAcc.signOut();
		magAcc.signIn(DATA_USER2, DATA_PASS);

		info("Add a new folder: user does not have right to do this action");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(CONTENT_FOLDER_TITLE);
		//click(actBar.ELEMENT_NEW_FOLDER_LINK);		
		//magAlt.verifyAlertMessage(ecmsPer.MESSAGE_NO_RIGHT_TO_ADD_NODE);
		waitForElementNotPresent(cTemplate.ELEMENT_NEW_FOLDER_LINK);

		info("Login with [john] to delete data");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(By.linkText(CONTENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102190
	 * Add folder with special characters in 'Name' field like !,@,#
	 * Refer https://jira.exoplatform.org/browse/ECMS-5954
	 */
	@Test 
	public void test06_AddFolderWithSpecialCharactersInNameField(){
		String cTitle = "ECMS_SE_ContentFolder_07";
		String dTitle = "ECMS_SE_DocumentFolder_07";
		String CONTENT_FOLDER_TITLE = cTemplate.DATA_SPECIAL_CHARACTER_STRING + cTitle;
		String DOCUMENT_FOLDER_TITLE = cTemplate.DATA_SPECIAL_CHARACTER_STRING + dTitle;

		info("Add a new folder with special character in name field");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(CONTENT_FOLDER_TITLE, folderType.Content, false);
		waitForAndGetElement(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", cTitle));
		cTemplate.createNewFolder(DOCUMENT_FOLDER_TITLE, folderType.Document, false);
		waitForAndGetElement(By.xpath(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", dTitle)));

		info("Restore data");
		cMenu.deleteDocument(By.linkText(CONTENT_FOLDER_TITLE));
		if (waitForAndGetElement(button.ELEMENT_OK_BUTTON, 3000, 0) != null){
			click(button.ELEMENT_OK_BUTTON);
		}
		warn("Exception: delete Folder with special characters");
		cMenu.deleteDocument(By.linkText(DOCUMENT_FOLDER_TITLE));
		if (waitForAndGetElement(button.ELEMENT_OK_BUTTON, 3000, 0) != null){
			click(button.ELEMENT_OK_BUTTON);
		}
		warn("Exception: delete Folder with special characters");
	}

	/**
	 * Qmetry ID: 102203
	 * Add a folder in root path
	 * 
	 */
	@Test
	public void test07_AddAFolderInRootPath(){
		String CONTENT_FOLDER_TITLE = "ECMS_SE_Folder_ContentFolder_08";

		info("Add a folder in root path");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(CONTENT_FOLDER_TITLE, folderType.Content);

		info("Restore data");
		cMenu.deleteDocument(By.linkText(CONTENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102205
	 * Add Content folder in a locked Document folder by locker
	 * 
	 */
	@Test
	public void test08_AddContentFolderInALockedDocumentFolderByLocker(){
		String DOCUMENT_FOLDER_TITLE = "ECMS_SE_Folder_DocumentFolder_09";

		info("Add a folder in root path and lock this node");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(DOCUMENT_FOLDER_TITLE, folderType.Document);
		cMenu.contextMenuAction(By.linkText(DOCUMENT_FOLDER_TITLE), cMenu.ELEMENT_CONTEXT_MENU_LOCK);
		assert cMenu.isLockedNode(By.linkText(DOCUMENT_FOLDER_TITLE)): "Failed to lock the node..." + DOCUMENT_FOLDER_TITLE;

		info("Verify that Users can not add Content folder in Document folder");
		ecms.goToNode(DOCUMENT_FOLDER_TITLE);
		actBar.goToAddNewFolder();
		waitForElementNotPresent(ecms.ELEMENT_FOLDER_TYPE_OPTION);
		button.cancel();

		info("Restore data");
		cMenu.deleteDocument(By.linkText(DOCUMENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102207
	 * Add Document folder in a locked Document folder by locker
	 * 
	 */
	@Test
	public void test09_AddDocumentFolderInALockedDocumentFolderByLocker(){
		String DOCUMENT_FOLDER_TITLE = "ECMS_SE_Folder_DocumentFolder_10";
		String DOCUMENT_SUB_FOLDER_TITLE = "ECMS_SE_Folder_DocumentSubFolder_10";

		info("Step 1: Create document folder and Lock node");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(DOCUMENT_FOLDER_TITLE, folderType.Document);
		cMenu.contextMenuAction(By.linkText(DOCUMENT_FOLDER_TITLE), cMenu.ELEMENT_CONTEXT_MENU_LOCK);
		assert cMenu.isLockedNode(By.linkText(DOCUMENT_FOLDER_TITLE)): "Failed to lock the node..." + DOCUMENT_FOLDER_TITLE;

		info("Step 2: Add New Document folder into locked node");
		ecms.goToNode(DOCUMENT_FOLDER_TITLE);
		actBar.goToAddNewFolder();
		type(ecms.ELEMENT_FOLDER_TITLE_TEXTBOX, DOCUMENT_SUB_FOLDER_TITLE, false);
		click(cTemplate.ELEMENT_CREATE_FOLDER_BUTTON);
		waitForAndGetElement(cTemplate.ELEMENT_VERIFY_FILE_CONTENT.replace("${content}", DOCUMENT_SUB_FOLDER_TITLE));
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		ecms.goToNode(DOCUMENT_FOLDER_TITLE);
		waitForAndGetElement(By.linkText(DOCUMENT_SUB_FOLDER_TITLE));
		info("Add New Document folder into locked folder: successful");

		info("Restore data");
		cMenu.deleteDocument(By.linkText(DOCUMENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102209
	 * Add Content folder in another Content folder
	 * 
	 */
	@Test
	public void test10_AddContentFolderInAnotherContentFolder(){
		String CONTENT_FOLDER_TITLE = "ECMS_SE_Folder_ContentFolder_11";
		String CONTENT_SUB_FOLDER_TITLE = "ECMS_SE_Folder_ContentSubFolder_11";

		info("Add a folder in root path");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(CONTENT_FOLDER_TITLE, folderType.Content);

		info("Add a new sub-folder");
		ecms.goToNode(CONTENT_FOLDER_TITLE);
		cTemplate.createNewFolder(CONTENT_SUB_FOLDER_TITLE, folderType.Content);		
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE_AUX.replace("${title1}", CONTENT_FOLDER_TITLE).replace("${title2}", CONTENT_SUB_FOLDER_TITLE));
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		ecms.goToNode(CONTENT_FOLDER_TITLE);
		waitForAndGetElement(By.linkText(CONTENT_SUB_FOLDER_TITLE));
		
		info("Restore data");
		cMenu.deleteDocument(By.linkText(CONTENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102210
	 * Add Document folder in another Document folder
	 * 
	 */
	@Test
	public void test11_AddDocumentFolderInAnotherDocumentFolder(){
		String DOCUMENT_FOLDER_TITLE = "ECMS_SE_Folder_DocumentFolder_12" + getRandomNumber();
		String DOCUMENT_SUB_FOLDER_TITLE = "ECMS_SE_Folder_DocumentSubFolder_12" + getRandomNumber();

		info("Add a folder in root path");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(DOCUMENT_FOLDER_TITLE, folderType.Document);

		info("Add a new sub-folder");
		ecms.goToNode(DOCUMENT_FOLDER_TITLE);
		cTemplate.createNewFolder(DOCUMENT_SUB_FOLDER_TITLE, folderType.None);		
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE_AUX.replace("${title1}", DOCUMENT_FOLDER_TITLE).replace("${title2}", DOCUMENT_SUB_FOLDER_TITLE));
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		ecms.goToNode(DOCUMENT_FOLDER_TITLE);
		waitForAndGetElement(By.linkText(DOCUMENT_SUB_FOLDER_TITLE));
		
		info("Restore data");
		cMenu.deleteDocument(By.linkText(DOCUMENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102211
	 * Add Document folder in Content folder
	 * 
	 */
	@Test
	public void test12_AddDocumentFolderInContentFolder(){
		String CONTENT_FOLDER_TITLE = "ECMS_SE_Folder_ContentFolder_13";
		String DOCUMENT_FOLDER_TITLE = "ECMS_SE_Folder_DocumentFolder_13";

		info("Step 1: Create Content folder");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(CONTENT_FOLDER_TITLE, folderType.Content);

		info("Step 2: Show form to add folder");
		ecms.goToNode(CONTENT_FOLDER_TITLE);

		info("Step 3: Do add Document folder");
		cTemplate.createNewFolder(DOCUMENT_FOLDER_TITLE, folderType.Document);
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE_AUX.replace("${title1}", CONTENT_FOLDER_TITLE).replace("${title2}", DOCUMENT_FOLDER_TITLE));
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		ecms.goToNode(CONTENT_FOLDER_TITLE);
		waitForAndGetElement(By.linkText(DOCUMENT_FOLDER_TITLE));
		
		info("Restore data");
		cMenu.deleteDocument(By.linkText(CONTENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102212
	 * Add Content folder in Document folder
	 * 
	 */
	@Test
	public void test13_AddContentFolderInDocumentFolder(){
		String DOCUMENT_FOLDER_TITLE = "ECMS_SE_Folder_DocumentFolder_14";

		info("Create Document folder at root path");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(DOCUMENT_FOLDER_TITLE, folderType.Document);

		info("Show form to add folder");
		ecms.goToNode(DOCUMENT_FOLDER_TITLE);
		actBar.goToAddNewFolder();

		info("Verify that Users can not add Content folder in Document folder");
		waitForElementNotPresent(ecms.ELEMENT_FOLDER_TYPE_OPTION);
		button.cancel();

		info("Restore data");
		cMenu.deleteDocument(By.linkText(DOCUMENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102294
	 * Creates a Document folder if not specified
	 * Pre-condition:
	 * A drive allows creation of all folder types.
       If the drive don't allow creation of folder types that you want. It is configured as follows:
       - Go to Administration/Content/Content administration
       - Go to Manage Drive, edit that drive, 
       - On field "Allow Folder Creation", select types, save
	 */
	@Test
	public void test14_CreatesDocumentFolderIfNotSpecified(){
		String FOLDER_TITLE = "ECMS_SE_Folder_15" + getRandomNumber();

		info("Step1: Go to [intranet/documents] and Open form [Add new folder]");
		navToolBar.goToSiteExplorer();
		ecms.goToNode("intranet/documents");

		info("Step 2: Create a folder without using [Use a custom type of folde]");
		cTemplate.createNewFolder(FOLDER_TITLE, folderType.None);

		info("Step 3: Check properties and view information");
		ecms.goToNode(By.linkText(FOLDER_TITLE));
		info("Add viewProperties to Action bar");
		actBar.addItem2ActionBar("viewProperties", actBar.ELEMENT_VIEW_PROPERTIES);
		
		info("Check jcr:primaryType must be nt:folder");
		ecms.goToNode("intranet/documents");
		ecms.goToNode(By.linkText(FOLDER_TITLE));
		if(waitForAndGetElement(actBar.ELEMENT_VIEW_PROPERTIES, 3000, 0) != null)
			click(actBar.ELEMENT_VIEW_PROPERTIES);
		else { 
			click (actBar.ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			click(actBar.ELEMENT_VIEW_PROPERTIES);
		}
		waitForAndGetElement("//div[contains(text(),'nt:folder')]/../..//td[contains(text(),'jcr:primaryType')]");
		button.close();
		
		info("View Information");
		ecms.goToNode(By.linkText(FOLDER_TITLE));
		cMenu.contextMenuAction(By.linkText(FOLDER_TITLE), cMenu.ELEMENT_VIEW_INFORMATION);
		waitForAndGetElement(cMenu.ELEMENT_POPUP_VIEW_INFORMATION_TYPE.replace("${folderName}", "Folder"));
		button.close();

		info("Restore data");
		cMenu.deleteDocument(By.linkText(FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102295
	 * List of folder types is defined in drive configuration
	 * 
	 */
	@Test
	public void test15_ListFolderTypesIsDefinedInDriveConfiguration(){
		String drive = "Managed Sites";
		String typeFolder = "Content Folder/CSS Folder/Document Folder/Link Folder/Web Content Folder";

		info("Go to Content Admin/Manage Drive");
		ecMain.goToManageDrive();

		info("Edit [Sites Management] Drive");
		click(By.xpath(magDrv.ELEMENT_DRIVE_EDIT_AUX.replace("${driveName}", drive)));

		info("Select all values for [Allow Folder Creation] except [Javascript Folder]");
		magDrv.selectTypeOfFolderCreation(typeFolder);
		button.save();

		info("Go to [Sites Management] Drive");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewFolder();

		info("Check list of folder types");
		click(ecms.ELEMENT_FOLDER_TYPE_OPTION);
		waitForAndGetElement(ecms.ELEMENT_CONTENT_FOLDER_TYPE_XPATH);
		waitForAndGetElement(ecms.ELEMENT_DOCUMENT_FOLDER_TYPE_XPATH);
		waitForAndGetElement(ecms.ELEMENT_CSS_FOLDER_TYPE_XPATH);
		waitForAndGetElement(ecms.ELEMENT_LINK_FOLDER_TYPE_XPATH);
		waitForAndGetElement(ecms.ELEMENT_WEB_CONTENT_FOLDER_TYPE_XPATH);
		waitForElementNotPresent(ecms.ELEMENT_JS_FOLDER_TYPE_XPATH);
		button.cancel();
	}

	/**
	 * Qmetry ID: 102296
	 * List of folder types is determined based on the nodetype restrictions
	 * 
	 */
	@Test
	public void test16_ListOfFolderTypesIsDeterminedBasedOnTheNodeTypeRestrictions(){
		String DOCUMENT_FOLDER_TITLE = "ECMS_SE_Folder_17";

		info("Go to Shared folder");
		navToolBar.goToSiteExplorer();
		ecms.goToNode("shared");

		info("Step1: Create Document folder");
		cTemplate.createNewFolder(DOCUMENT_FOLDER_TITLE, folderType.Document);

		info("Step 2: Check types of folder when creating a folder inside a document folder");
		//The list contains : CSS Folder, Document Folder
		ecms.goToNode(By.linkText(DOCUMENT_FOLDER_TITLE));
		actBar.goToAddNewFolder();
		click(ecms.ELEMENT_FOLDER_TYPE_OPTION);
		waitForAndGetElement(ecms.ELEMENT_DOCUMENT_FOLDER_TYPE_XPATH);
		waitForAndGetElement(ecms.ELEMENT_CSS_FOLDER_TYPE_XPATH);
		waitForElementNotPresent(ecms.ELEMENT_CONTENT_FOLDER_TYPE_XPATH);
		button.cancel();

		info("Restore data");
		cMenu.deleteDocument(By.linkText(DOCUMENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102297
	 * Created a folder which is one of type: Content, Css, Link Folder...
	 * 
	 */
	@Test
	public void test17_CreatedFolderIsOfTheSelectedType(){
		String CSS_FOLDER_TITLE = "ECMS_SE_CSS_Folder_18";

		info("Step 1: Create a new folder using [Css Folder] type");
		navToolBar.goToSiteExplorer();
		cTemplate.createNewFolder(CSS_FOLDER_TITLE, folderType.Css);
		waitForAndGetElement(ecms.ELEMENT_FOLDER_ICON.replace("${folderTitle}", CSS_FOLDER_TITLE).replace("${folderType}", "cssFolder"));

		info("Step 2 Check type of folder");
		ecms.goToNode(By.linkText(CSS_FOLDER_TITLE));
		info("Add viewProperties to Action bar");
		actBar.addItem2ActionBar("viewProperties", actBar.ELEMENT_VIEW_PROPERTIES);
		
		info("Check jcr:primaryType must be exo:cssFolder");
		ecms.goToNode(By.linkText(CSS_FOLDER_TITLE));
		if(waitForAndGetElement(actBar.ELEMENT_VIEW_PROPERTIES, 3000, 0) != null)
			click(actBar.ELEMENT_VIEW_PROPERTIES);
		else { 
			click (actBar.ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			click(actBar.ELEMENT_VIEW_PROPERTIES);
		}
		waitForAndGetElement("//div[contains(text(),'exo:cssFolder')]/../..//td[contains(text(),'jcr:primaryType')]");
		info("View information");
		cMenu.contextMenuAction(By.linkText(CSS_FOLDER_TITLE), cMenu.ELEMENT_VIEW_INFORMATION);
		waitForAndGetElement(cMenu.ELEMENT_POPUP_VIEW_INFORMATION_TYPE.replace("${folderName}", "Folder"));
		button.close();

		info("Restore data");
		cMenu.deleteDocument(By.linkText(CSS_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102299
	 * UI check dialog when multiple folder types are allowed
	 * 
	 */
	@Test
	public void test18_UICheckDialogWhenMultipleFolderTypesAreAllowed(){
		info("Check UI  dialog when multiple folder types are allowed");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewFolder();

		info("Verify the popup title");
		waitForAndGetElement(ecms.ELEMENT_FOLDER_POPUP_TITLE);

		info("Verify the input for Folder name");
		waitForAndGetElement(ecms.ELEMENT_FOLDER_TITLE_TEXTBOX);

		info("Checkbox [use a custom type of folder] is diplayed");
		waitForAndGetElement(cTemplate.ELEMENT_USE_CUSTOM_TYPE_FOLDER, DEFAULT_TIMEOUT, 1, 2);

		info("Check a hint on the checkbox is available");
		mouseOver(cTemplate.ELEMENT_USE_CUSTOM_TYPE_FOLDER, true, 2);
		waitForTextPresent(ecms.MESSAGE_FOLDER_HINT_CHECKBOX);

		info("[Create Folder] button is displayed");
		waitForAndGetElement(cTemplate.ELEMENT_CREATE_FOLDER_BUTTON);

		info("[Cancel] button is displayed");
		waitForAndGetElement(button.ELEMENT_CANCEL_BUTTON);
	}

	/**
	 * Qmetry ID: 102300
	 * UI check dialog with only one folder type allowed
	 * 
	 */
	@Test
	public void test19_UICheckDialogWithOnlyOneFolderTypeAllowed(){
		String drive = "Managed Sites";
		String typeFolder = "Content Folder";

		info("Go to Content Admin/Manage Drive");
		ecMain.goToManageDrive();

		info("Edit [Sites Management] Drive");
		click(By.xpath(magDrv.ELEMENT_DRIVE_EDIT_AUX.replace("${driveName}", drive)));

		info("Select [Allow Folder Creation]: " + typeFolder);
		magDrv.selectTypeOfFolderCreation(typeFolder, true);
		button.save();

		info("Go to [Sites Management] Drive");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewFolder();

		info("Verify that there is no checkbox in the dialog");
		waitForElementNotPresent(cTemplate.ELEMENT_USE_CUSTOM_TYPE_FOLDER, DEFAULT_TIMEOUT, 1, 2);		
		button.cancel();
	} 

	/**
	 * Qmetry ID: 102322
	 * Add Document folder in a locked Content folder by locker
	 * 
	 */
	@Test
	public void test20_AddDocumentFolderInALockedContentFolderByLocker(){
		String DOCUMENT_FOLDER_TITLE = "ECMS_SE_DocumentFolder_22";
		String CONTENT_FOLDER_TITLE = "ECMS_SE_ContentFolder_22";
		String drive = "Managed Sites";
		String typeFolder = "Content Folder/Document Folder";
		
		info("Go to Content Admin/Manage Drive");
		ecMain.goToManageDrive();

		info("Edit [Sites Management] Drive");
		click(By.xpath(magDrv.ELEMENT_DRIVE_EDIT_AUX.replace("${driveName}", drive)));

		info("Select [Allow Folder Creation]: " + typeFolder);
		magDrv.selectTypeOfFolderCreation(typeFolder);
		button.save();

		info("Go to [Sites Management] Drive");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		
		info("Step 1: Create Content folder and Lock node");
		cTemplate.createNewFolder(CONTENT_FOLDER_TITLE, folderType.Content);
		cMenu.contextMenuAction(By.linkText(CONTENT_FOLDER_TITLE), cMenu.ELEMENT_CONTEXT_MENU_LOCK);
		assert cMenu.isLockedNode(By.linkText(CONTENT_FOLDER_TITLE)): "Failed to lock the node..." + CONTENT_FOLDER_TITLE;
		
		info("Step 2: Add New Document folder into locked node");
		ecms.goToNode(CONTENT_FOLDER_TITLE);
		cTemplate.createNewFolder(DOCUMENT_FOLDER_TITLE, folderType.Document);
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE_AUX.replace("${title1}", CONTENT_FOLDER_TITLE).replace("${title2}", DOCUMENT_FOLDER_TITLE));
		
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		ecms.goToNode(CONTENT_FOLDER_TITLE);
		waitForAndGetElement(By.linkText(DOCUMENT_FOLDER_TITLE));
		
		info("Restore data");
		cMenu.deleteDocument(By.linkText(CONTENT_FOLDER_TITLE));
	}

	/**
	 * Qmetry ID: 102323
	 * Admin cannot add while node is locked by another user
	 * ========== ECMS-5483 ==========
	 */
	@Test
	public void test21_CheckIfAdminCanAddFolderIntoANodeLockedByAnotherUser(){
		String CONTENT_FOLDER_TITLE = "ECMS_SE_ContentFolder_23";
		String CONTENT_SUB_FOLDER_TITLE = "ECMS_SE_ContentSubFolder_23";
		
		info("Step 1: Create Content folder and Lock node by a [Web-Contributor] User");
		magAcc.signOut();
		magAcc.signIn(DATA_USER2, DATA_PASS);
		navToolBar.goToSiteExplorer();
		
		cTemplate.createNewFolder(CONTENT_FOLDER_TITLE, folderType.Content);
		
		cMenu.contextMenuAction(By.linkText(CONTENT_FOLDER_TITLE), cMenu.ELEMENT_CONTEXT_MENU_LOCK);
		assert cMenu.isLockedNode(By.linkText(CONTENT_FOLDER_TITLE)): "Failed to lock the node..." + CONTENT_FOLDER_TITLE;
		driver.close();
		
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver);
		navToolBar = new NavigationToolbar(driver);
		ecms = new EcmsBase(driver);
		cMenu = new ContextMenu(driver);
		actBar = new ActionBar(driver);
		cTemplate = new ContentTemplate(driver);

		info("Login with Root");
		magAcc.signIn(USER_ROOT, PASS_ROOT);
		
		info("Step 2: Add folder into locked node using Admin account");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(CONTENT_FOLDER_TITLE);
		cTemplate.createNewFolder(CONTENT_SUB_FOLDER_TITLE, folderType.Content,false);
		
		//pop-up opens, cannot create folder
		waitForAndGetElement(button.ELEMENT_OK_BUTTON).click();
		waitForAndGetElement(button.ELEMENT_CANCEL_BUTTON).click();
		cTemplate.waitForElementNotPresent(CONTENT_SUB_FOLDER_TITLE);
		
		info("Logout Root");
		magAcc.signOut();
		
		info("Restore data");
		magAcc.signIn(DATA_USER2, DATA_PASS);
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(By.linkText(CONTENT_FOLDER_TITLE));
		
	}
	
	/**
	 * Qmetry ID: 102327
	 * Add folder in a node ( not folder/nt:file) of which parent is in 'Check in' status
	 *  
	 **/
	@Test
	public void test22_AddFolderInANodeNotNTFileOfWhichParentIsInCheckInStatus(){
		String CONTENT_FOLDER_TITLE = "ECMS_SE_Folder_ContentFolder_24";
		String ANNOUNCE_NAME = "ECMS_SE_Folder_Announce_24";
		String ANNOUNCE_SUM = "ECMS_SE_Folder_Announce_24";
		String ANNOUNCE_NAME_SUB_FOLDER_TITLE = "ECMS_SE_Folder_Announce_SubFolder_24";
		
		info("Step 1: Create a web content and its sub-folder");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(CONTENT_FOLDER_TITLE, CONTENT_FOLDER_TITLE, "", "", "", "");
		actBar.goToAddNewContent();
		cTemplate.createNewAnnouncement(ANNOUNCE_NAME, ANNOUNCE_SUM,false);

		info("Check In Parent Node: " + CONTENT_FOLDER_TITLE);
		ecms.goToNode(By.linkText(CONTENT_FOLDER_TITLE));
		cMenu.contextMenuAction(By.linkText(CONTENT_FOLDER_TITLE), cMenu.ELEMENT_MENU_CHECKIN);

		info("Step 2: Check User can add sub-node into child node: folder");
		ecms.goToNode(By.linkText(ANNOUNCE_NAME));
		cTemplate.createNewFolder(ANNOUNCE_NAME_SUB_FOLDER_TITLE, folderType.Content);
		
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		ecms.goToNode(CONTENT_FOLDER_TITLE);
		waitForAndGetElement(By.linkText(ANNOUNCE_NAME));
		ecms.goToNode(ANNOUNCE_NAME);
		waitForAndGetElement(By.linkText(ANNOUNCE_NAME_SUB_FOLDER_TITLE));
		
		//delete data
		navToolBar.goToSiteExplorer();
		cMenu.contextMenuAction(By.linkText(CONTENT_FOLDER_TITLE), cMenu.ELEMENT_MENU_CHECKOUT);
		cMenu.deleteDocument(By.linkText(CONTENT_FOLDER_TITLE));
	}

}
