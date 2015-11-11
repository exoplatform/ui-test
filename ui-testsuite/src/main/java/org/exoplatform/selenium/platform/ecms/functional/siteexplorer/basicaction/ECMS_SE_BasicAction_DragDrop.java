package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.basicaction;
import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageDrive;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ECMS_SE_BasicAction_DragDrop extends PlatformBase {


	NavigationToolbar navToolBar;
	ActionBar actBar;
	ManageAccount magAcc;
	SettingSearchPage qsPage;
	public ManageAlert alert ;
	ManageDrive magDrv;
	EcmsBase ecms;
	ContentTemplate cTemplate;
	ContextMenu cMenu;
	SitesExplorer siteExp;
	ECMainFunction ecMain;
	Button button;
	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		driver.get(plfURL);
		navToolBar = new NavigationToolbar(driver,this.plfVersion);
		magAcc = new ManageAccount(driver,this.plfVersion);
		actBar = new ActionBar(driver,this.plfVersion);
		ecms = new EcmsBase(driver,this.plfVersion);
		cTemplate = new ContentTemplate(driver,this.plfVersion);
		cMenu = new ContextMenu(driver,this.plfVersion);
		siteExp = new SitesExplorer(driver,this.plfVersion);
		qsPage = new SettingSearchPage(driver);
		alert = new ManageAlert(driver,this.plfVersion);
		magDrv = new ManageDrive(driver);
		ecMain = new ECMainFunction(driver,this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		button = new Button(driver,this.plfVersion);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();		
		driver.quit();
	}

	/*
	 * == Drag and drop from left to right pane ==
	 * Test ID : 101932
	 * 1 - Create node
	 * 2 - Drag node
	 * 3 - Drop node
	 */
	@Test
	public void test01_DragAndDropFromLeftToRightPane(){
		String documentName="document101932" + getRandomNumber();

		// step 1 : Add a new content
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(documentName, documentName, "", "", "", "");
		info(" Web content added : "+documentName);
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);

		// Step 2 & 3 : drag it from the left to the right
		qsPage.dragAndDropToObject(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}",documentName),siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}","intranet"));
		alert.acceptAlert();
		info("drag and drop object in 'intranet' ");
		Utils.pause(3000);
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		waitForElementNotPresent(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}",documentName));

		// delete data
		click(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}","intranet"));
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}",documentName));
		//cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}",documentName));
		cMenu.deleteDocument(documentName);
		info("Data deleted");


	}

	/*
	 *== Drag and drop from right to left pane ==
	 *Test Id : 101933 
	 * 1 - Create node
	 * 2 - Drag node
	 * 3 - Drop node
	 */
	@Test
	public void test02_DragAndDropFromRightToLeftPane(){
		String documentName="document101933" + getRandomNumber();

		// step 1 : Add a new content
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(documentName, documentName, "", "", "", "");
		info(" Web content added : "+documentName);
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		/*Utils.pause(4000);
		driver.navigate().refresh();
		Utils.pause(10000);*/
		// Step 2 & 3 : drag it from the right to the left
		qsPage.dragAndDropToObject(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}",documentName),cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}","intranet"));
		alert.acceptAlert();
		info("drag and drop object in 'intranet' ");
		Utils.pause(3000);
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}",documentName));
		/*click(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}","intranet"));
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}",documentName));*/

		// delete data
		click(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}","intranet"));
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}",documentName));
		//cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}",documentName));
		cMenu.deleteDocument(documentName);
		info("Data deleted");
	}

	/*
	 * Drag and drop in right pane
	 * Test ID : 101936
	 * ---- Impossible to drag and drop in the right pane. ----
	 */
	@Test(groups={"pending"})
	public void test03_DragAndDropInRightPane(){
		String documentName="document101936" + getRandomNumber();

		// step 1 : Add a new content
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(documentName, documentName, "", "", "", "");
		info(" Web content added : "+documentName);
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);

		// Step 2 & 3 : drag it from the left to the right
		Utils.pause(1000);
		qsPage.dragAndDropToObject(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}",documentName),siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}","intranet"));	
		/*if (waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}",documentName), 3000, 0) != null)
			qsPage.dragAndDropToObject(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}",documentName),siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}","intranet"));*/	
		alert.acceptAlert();
		info("drag and drop object in 'intranet' ");
		Utils.pause(3000);
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		waitForElementNotPresent(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}",documentName));
		/*click(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}","intranet"));
		waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}",documentName));*/

		// delete data
		click(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}","intranet"));
		waitForAndGetElement(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}",documentName));
		cMenu.deleteDocument(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}",documentName));
		info("Data deleted");
	}

	/*
	 * == Drag and drop in the left pane ==
	 * Test ID : 101937
	 */
	@Test
	public void test04_DragAndDropInTheLeftPane(){
		String documentName="document101933" + getRandomNumber();

		// step 1 : Add a new content
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(documentName, documentName, "", "", "", "");
		info(" Web content added : "+documentName);
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);

		// Step 2 & 3 : drag it from the left to the right
		qsPage.dragAndDropToObject(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}",documentName),cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}","intranet"));
		alert.acceptAlert();
		info("drag and drop object in 'intranet' ");
		Utils.pause(3000);
		click(actBar.ELEMENT_SITES_MANAGEMENT_ICON);
		waitForElementNotPresent(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}",documentName));
		/*click(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}","intranet"));
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}",documentName));*/

		// delete data
		click(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}","intranet"));
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}",documentName));
		//cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}",documentName));
		cMenu.deleteDocument(documentName);
		info("Data deleted");
	}

	/*
	 * == Drag And drop a node in admin view ==
	 * Test ID : 102125
	 *  ---- Impossible to drag and drop in the right pane. ----
	 */
	@Test(groups={"pending"})
	public void test05_DragAndDropANodeInAdminView(){
		String drive = "Managed Sites";
		String view = "Admin";
		String documentName  = "document102125" + getRandomNumber();

		// Show admin view
		info("Change view of drive, check admin");
		ecMain.goToManageDrive();
		click(By.xpath(magDrv.ELEMENT_DRIVE_EDIT_AUX.replace("${driveName}", drive)));
		click(magDrv.ELEMENT_APPLY_VIEW_TAB);
		magDrv.selectCheckBoxList(view);
		button.save();

		navToolBar.goToSiteExplorer();

		info("Change the view for web");
		click(cTemplate.ELEMENT_VIEW_MODE_LINK.replace("${viewName}","Web"));
		
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(documentName, documentName, "", "", "", "");
		info(" Web content added : "+documentName);

		info("Change the view for Admin");
		click(cTemplate.ELEMENT_VIEW_MODE_LINK.replace("${viewName}","Admin"));
		
		
		info("pass");
		alert.acceptAlert();
		info("pass2");
		//qsPage.dragAndDropToObject(By.xpath("//*[@id='UIDocumentNodeList']/div/div[3]"),By.xpath("//*[@id='UIDocumentNodeList']/div/div[1]"));
		qsPage.dragAndDropToObject(siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}",documentName),siteExp.ELEMENT_DOCUMENT_TITLE.replace("${title}","acme"));
		
		// check the document has been move
		waitForElementNotPresent(cMenu.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}",documentName));
		click(cMenu.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}","acme"));
		waitForAndGetElement(cMenu.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}",documentName));

		// delete data
		cMenu.deleteDocument(siteExp.ELEMENT_SE_NODE.replace("{$node}",documentName));

	}

	/*
	 * == Drag and drop a node in web view ==
	 * Test ID : 102126
	 *  ---- Impossible to drag and drop in the right pane. ----
	 */
	@Test(groups={"pending"})
	public void test06_DragAndDropANodeInWebView(){
		
	}

	/*
	 * == Drag and drop a node in icons view ==
	 * Test ID : 102127
	 *  ---- Impossible to drag and drop in the right pane. ----
	 */
	@Test(groups={"pending"})
	public void test07_DragAndDropANodeInIconsView(){

	}

	/*
	 * == Drag and drop a node in list view ==
	 * Test ID : 102129
	 * ---- Impossible to drag and drop in the right pane. ----
	 */
	@Test(groups={"pending"})
	public void test08_DragAndDropANodeInListView(){

	}

	/*
	 * == Drag and drop a node in categories view ==
	 * Test ID : 112477
	 *  ---- Impossible to drag and drop in the right pane. ----
	 */
	@Test(groups={"pending"})
	public void test09_DragAndDropANodeInCategoriesView(){

	}
}
