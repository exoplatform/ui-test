package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author vuna2
 * July, 23th, 2013
 *
 */
public class ECMS_SE_CreateNode_Document extends PlatformBase{
	//Platform
	Button button;
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;
	ManageAlert magAlt;

	//Ecms
	EcmsBase ecms;
	EcmsPermission ecmsPer;
	ContextMenu cMenu;
	ContentTemplate cTemplate;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(baseUrl);
		button = new Button(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		navToolBar = new NavigationToolbar(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		ecms = new EcmsBase(driver, this.plfVersion);
		ecmsPer = new EcmsPermission(driver);
		cMenu = new ContextMenu(driver);
		cTemplate = new ContentTemplate(driver, this.plfVersion);
		magAlt = new ManageAlert(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/*
	 * Test case ID: 102180
	 * Title: Cancel when select a template 
	 */
	@Test
	public void test01_CancelWhenSelectATemplate(){
		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(ecms.ELEMENT_SIDEBAR_ACME);

		info("Go to [Add New Content]");
		actBar.goToAddNewContent();

		info("Cancel [Add New Content]");
		button.cancel();

		info("Verify: users back to previous directory");
		waitForElementNotPresent(cTemplate.ELEMENT_WEBCONTENT_LINK);
	}

	/*
	 * Test case ID: 102217
	 * Title: Add Content in a locked node by not locker 
	 */
	@Test
	public void test02_AddContentInALockedNodeByUserIsNotLocker(){
		String FILE_TITLE = "ECMS_SE_Document_02_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a node and lock this one");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(FILE_TITLE, FILE_TITLE, "", "", "", "");
		cMenu.contextMenuAction(By.linkText(FILE_TITLE), cMenu.ELEMENT_CONTEXT_MENU_LOCK);
		assert cMenu.isLockedNode(By.linkText(FILE_TITLE)): "Failed to lock the node..." + FILE_TITLE;
		driver.close();

		info("Login by user is not locker");
		initSeleniumTest();
		driver.get(baseUrl);
		magAcc = new ManageAccount(driver);
		navToolBar = new NavigationToolbar(driver);
		ecms = new EcmsBase(driver);
		cMenu = new ContextMenu(driver);
		actBar = new ActionBar(driver);

		info("Login with Mary");
		magAcc.signIn(DATA_USER2, DATA_PASS);

		info("Verify that [Mary] can not see [Add Content] icon on action bar");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(By.linkText(FILE_TITLE));
		waitForElementNotPresent(actBar.ELEMENT_NEW_CONTENT_LINK);

		info("Restore data");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);

		//Delete data
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(By.linkText(FILE_TITLE));
	}

	/*
	 * Test case ID: 102218
	 * Title: Add Content in a node has parent node is in 'Check in' status
	 */
	@Test
	public void test03_AddContentInANodeHasParentNodeIsInCheckInStatus(){
		String FILE_TITLE = "ECMS_SE_Document_03_"+getRandomNumber();
		String SUB_FILE_TITLE_1 = "ECMS_SE_Sub_Document_03_"+getRandomNumber();
		String SUB_FILE_TITLE_2 = "ECMS_SE_Sub_Document_03_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		//Create parent node (except folder and nt:file) 
		info("Create a parent node");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(FILE_TITLE, FILE_TITLE, "", "", "", "");

		//Create child node 1 is nt:file
		info("Create child node 1 is nt:file");
		ecms.goToNode(By.linkText(FILE_TITLE));
		actBar.goToAddNewContent();
		cTemplate.createNewFile(SUB_FILE_TITLE_1, SUB_FILE_TITLE_1, SUB_FILE_TITLE_1);

		//Create child node 2 is not nt:file
		info("Create child node 2 is not nt:file");
		ecms.goToNode(By.linkText(FILE_TITLE));
		actBar.goToAddNewContent();
		cTemplate.createNewAnnouncement(SUB_FILE_TITLE_2, SUB_FILE_TITLE_2);

		//Check in parent node
		info("Check in " + FILE_TITLE);
		ecms.goToNode(By.linkText(FILE_TITLE));
		cMenu.contextMenuAction(By.linkText(FILE_TITLE), cMenu.ELEMENT_MENU_CHECKIN);

		//Check "New Content" button on action bar when choosing child node 1
		info("Go to the child node " + SUB_FILE_TITLE_1);
		info("Verify [Add New Content] is not displayed on Action Bar");
		ecms.goToNode(By.linkText(SUB_FILE_TITLE_1));
		waitForElementNotPresent(actBar.ELEMENT_NEW_CONTENT_LINK);

		//Check "New Content" button on action bar when choosing child node 2
		info("Go to the child node " + SUB_FILE_TITLE_2);
		info("Verify [Add New Content] is displayed on Action Bar");
		ecms.goToNode(By.linkText(SUB_FILE_TITLE_2));
		waitForAndGetElement(actBar.ELEMENT_NEW_CONTENT_LINK);

		//Delete data
		info("Restore data");
		cMenu.contextMenuAction(By.linkText(FILE_TITLE), cMenu.ELEMENT_MENU_CHECKOUT);
		cMenu.deleteDocument(By.linkText(FILE_TITLE));
	}

	/*
	 * Test case ID: 102219
	 * Title: Add Content in node which is in 'check in' status
	 */
	@Test
	public void test04_AddContentInNodeWhichIsInCheckInStatus(){
		String FILE_TITLE = "ECMS_SE_Document_04_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a node and check in node");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(FILE_TITLE, FILE_TITLE, "", "", "", "");
		ecms.goToNode(By.linkText(FILE_TITLE));
		waitForAndGetElement(actBar.ELEMENT_NEW_CONTENT_LINK);

		info("Check in node");
		cMenu.contextMenuAction(By.linkText(FILE_TITLE), cMenu.ELEMENT_MENU_CHECKIN);

		info("Verify New Content is not displayed on action bar");
		waitForElementNotPresent(actBar.ELEMENT_NEW_CONTENT_LINK);

		info("Restore data");
		cMenu.contextMenuAction(By.linkText(FILE_TITLE), cMenu.ELEMENT_MENU_CHECKOUT);
		cMenu.deleteDocument(By.linkText(FILE_TITLE));
	}

	/*
	 * Test case ID: 102220
	 * Add Content in root path
	 */
	@Test
	public void test05_AddContentInRootPath(){
		String FILE_TITLE = "ECMS_SE_Document_05_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a node in root path");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(FILE_TITLE, FILE_TITLE, "", "", "", "");
		waitForAndGetElement(By.linkText(FILE_TITLE));

		info("Restore data");
		cMenu.deleteDocument(By.linkText(FILE_TITLE));
	}


	/*
	 * Test case ID: 102221
	 * Add Content when  Click on both [Save] button and  [Close] button
	 */
	@Test
	public void test06_AddContentWhenClickOnBothSaveButtonAndCloseButton(){
		String FILE_TITLE = "ECMS_SE_Document_06_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Go to [Add New Content]");
		actBar.goToAddNewContent();

		info("Add a new Web Content using [Save] and [Close] buttons");
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, FILE_TITLE, true);
		inputDataToCKEditor(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, FILE_TITLE);
		switchToParentWindow();
		button.save();
		waitForAndGetElement(By.linkText(FILE_TITLE));
		button.close();
		waitForElementNotPresent(button.ELEMENT_CLOSE_BUTTON);

		info("Restore data");
		cMenu.deleteDocument(By.linkText(FILE_TITLE));	
	}

	/*
	 * Qmetry ID: 102222
	 * Add Content when Click on Maximize/Minimize icon
	 */
	@Test
	public void test07_AddContentWhenClickOnMaximizeMinimizeIcon(){
		String FILE_TITLE = "ECMS_SE_Document_07_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Go to [Add New Content]");
		actBar.goToAddNewContent();

		info("Add a new Web Content using Maximize Icon");
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, FILE_TITLE, true);
		inputDataToCKEditor(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, FILE_TITLE);
		switchToParentWindow();
		click(cTemplate.ELEMENT_MAXIMIZE_ICON);
		waitForAndGetElement(cTemplate.ELEMENT_MINIMIZE_ICON);		
		click(cTemplate.ELEMENT_MINIMIZE_ICON);
		button.saveAndClose();
		waitForAndGetElement(By.linkText(FILE_TITLE));

		info("Restore data");
		cMenu.deleteDocument(By.linkText(FILE_TITLE));
	}

	/*
	 * Test case ID: 102223
	 * Add Content when click on [Change Content Type] link
	 */
	@Test
	public void test08_AddContentWhenClickOnChangeContentTypeLink(){
		String FILE_TITLE = "ECMS_SE_Document_08_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Go to [Add New Content]");
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, FILE_TITLE, true);
		switchToParentWindow();

		info("Click [Change Content Type] link");
		click(cTemplate.ELEMENT_CHANGE_CONTENT_TYPE);
		magAlt.acceptAlert();
		waitForAndGetElement(cTemplate.ELEMENT_NEWFILE_LINK);	

		info("Change Content Type...successful");
	}

	/*
	 * Test case ID: 102224
	 * Title: Add Content when Click on [Save & Close] button
	 */
	@Test
	public void test09_AddContentWhenClickOnSaveAndCloseButton(){
		String FILE_TITLE = "ECMS_SE_Document_09_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(ecms.ELEMENT_SIDEBAR_ACME);

		info("Go to [Add New Content]");
		actBar.goToAddNewContent();	
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, FILE_TITLE, true);
		inputDataToCKEditor(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, FILE_TITLE);
		button.saveAndClose();		
		waitForAndGetElement(By.linkText(FILE_TITLE));

		info("Restore data");
		cMenu.deleteDocument(By.linkText(FILE_TITLE));
	}

	/*
	 * Test case ID: 102225
	 * Title: Add Content when Click on [Save] button
	 * 
	 */
	@Test
	public void test10_AddContentWhenClickOnSaveButton(){
		String FILE_TITLE = "ECMS_SE_Document_10_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(ecms.ELEMENT_SIDEBAR_ACME);

		info("Go to [Add New Content]");
		actBar.goToAddNewContent();

		info("Add new content using Save Button");
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, FILE_TITLE, true);
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, FILE_TITLE);
		switchToParentWindow();
		button.save();
		waitForAndGetElement(By.linkText(FILE_TITLE));
		waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX);
		info("Add Content form is still displayed");

		button.close();
		info("Restore data");
		cMenu.deleteDocument(By.linkText(FILE_TITLE));
	}

	/*
	 * Test case ID: 102227
	 * Add Content when only Click on [Close] button
	 */
	@Test
	public void test11_AddContentWhenOnlyClickOnCloseButton(){
		String FILE_TITLE = "ECMS_SE_Document_11_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(ecms.ELEMENT_SIDEBAR_ACME);

		info("Go to [Add New Content]");
		actBar.goToAddNewContent();

		info("Add new content when click on [Close] Button");
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, FILE_TITLE, true);
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, FILE_TITLE);
		switchToParentWindow();
		click(button.ELEMENT_CLOSE_BUTTON);
		magAlt.acceptAlert();

		info("Verify that there is no document which was added and [Add Content] form is closed");
		waitForElementNotPresent(By.linkText(FILE_TITLE));
		waitForElementNotPresent(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX);
	}

	/*
	 * Test case ID: 102228
	 * Add Content when user does not have add node right
	 */
	@Test
	public void test12_AddContentWhenUserDoesNotHaveAddNodeRight(){
		String FILE_TITLE = "ECMS_SE_Document_12";
		By ELEMENT_FILE_TITLE = By.linkText(FILE_TITLE);

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a node");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(FILE_TITLE, FILE_TITLE, "", "", "", "");
		waitForAndGetElement(ELEMENT_FILE_TITLE);

		info("Set permission for users whose have not add node right");
		ecms.goToNode(ELEMENT_FILE_TITLE);
		actBar.goToNodePermissionManagement();
		ecmsPer.removeDefaultPermissionOfNode();
		ecms.selectUser(DATA_USER2);
		ecmsPer.setPermissionForNode(true, false, false);
		button.save();

		info("Login by user who does not have permission to add node inside the above node");
		magAcc.signOut();
		magAcc.signIn(DATA_USER2, DATA_PASS);

		info("Add a new content: user does not have right to do this action");
		navToolBar.goToSiteExplorer();
		ecms.goToNode(ELEMENT_FILE_TITLE);
		waitForElementNotPresent(actBar.ELEMENT_NEW_CONTENT_LINK);

		info("Login with [john] to restore data");
		magAcc.signOut();
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(ELEMENT_FILE_TITLE);
	}

	/*
	 * Test case ID: 102229
	 * Add Content with special characters in Name
	 */
	@Test
	public void test13_AddContentWithSpecialCharactersInName(){
		String WEB_CONTENT = "ECMS_SE_Document_13_"+getRandomNumber();

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);

		info("Add Content with special characters in Name Field");
		for (int i = 0 ; i < cTemplate.DATA_SPECIAL_CHARACTER_2.length; i++) { 
			info("Add content with character..." + cTemplate.DATA_SPECIAL_CHARACTER_2[i]);
			type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, cTemplate.DATA_SPECIAL_CHARACTER_2[i] + WEB_CONTENT, true);
			click(button.ELEMENT_SAVE_BUTTON);
			magAlt.verifyAlertMessage(cTemplate.MESSAGE_FIELD_NAME_INVALID_CHARS);
		}
		click(button.ELEMENT_CLOSE_BUTTON);
	}
}