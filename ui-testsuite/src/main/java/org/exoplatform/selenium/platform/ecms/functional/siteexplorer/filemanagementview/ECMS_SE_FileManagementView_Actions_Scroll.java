package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.filemanagementview;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu.actionType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * updated by anhpp
 *
 */
public class ECMS_SE_FileManagementView_Actions_Scroll extends PlatformBase {
	//Platform
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	Dialog Dialog;

	//ECMS
	EcmsBase ecms;
	ContentTemplate cTemplate;
	ActionBar actBar;
	ContextMenu cMenu;

	public String DATA_USER = "john";
	public String DATA_PASS = "gtngtn";

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		actBar = new ActionBar(driver);
		magAcc = new ManageAccount(driver);		
		magAcc.signIn(DATA_USER, DATA_PASS);
		navToolBar = new NavigationToolbar(driver);
		ecms = new EcmsBase(driver);
		cTemplate = new ContentTemplate(driver);
		cMenu= new ContextMenu(driver);
	}

	@AfterMethod
	public void afterMethod() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * CaseID: 119521
	 * Show Breadcrumb/Sort By panel after a vertical scroll
	 */
	@Test
	public void test01_ShowBreadcrumbSortByPanelAfterAVerticalScroll(){
		String folderName= "folderTest01_";

		navToolBar.goToPersonalDocuments();
		actBar.goToViewMode("Admin");		

		info( "-- create data --");
		for(int i=0; i<=5; i++) {
			info("-- Create parent folder --");
			//cTemplate.createNewFolder(folderName +i, folderType.None);
			actBar.goToAddNewFolder();
			type(ecms.ELEMENT_FOLDER_TITLE_TEXTBOX, folderName +i, true);
			click(cTemplate.ELEMENT_CREATE_FOLDER_BUTTON);
		}

		info( "--check the scroll bar present --");
		String execScript = "return document.documentElement.scrollHeight>document.documentElement.clientHeight;";
		JavascriptExecutor scrollBarPresent = (JavascriptExecutor) driver;
		scrollBarPresent.executeScript(execScript); 

		info( "-- scroll the window --");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		info( "-- check element --");
		waitForAndGetElement(By.xpath("//*[@class= 'uiIconTree uiIconLightGray']"));
		waitForAndGetElement(By.xpath("//*[@class= 'sortByLabel pull-right']"));

		info( "-- clear data	--");
		for(int i=0; i<=5; i++) {
			info("-- Delete both of parent and child nodes --");
			actBar.actionsOnElement(folderName +i, actionType.DELETE);
			waitForElementNotPresent(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", folderName +i));
		}
	}

	/**
	 * CaseID: 119526
	 * Show selection actions panel after a vertical scroll
	 */
	@Test
	public void test02_ShowSelectionActionsPanelAfterAVerticalScroll(){
		String folderName= "folderTest01_";


		navToolBar.goToPersonalDocuments();
		actBar.goToViewMode("Admin");		

		info( "-- create data --");
		for(int i=0; i<=5; i++) {
			info("-- Create parent folder --");
			//cTemplate.createNewFolder(folderName+i, folderType.None);
			actBar.goToAddNewFolder();
			type(ecms.ELEMENT_FOLDER_TITLE_TEXTBOX, folderName +i, true);
			click(cTemplate.ELEMENT_CREATE_FOLDER_BUTTON);
		}

		click(By.xpath(("//*[@class='nodeName' and text()= 'Favorites']/../..")));

		info("--Verify actions for this node are displayed on top of the top action bar before scrolling --");
		info("--Verify display action copy node --");
		waitForAndGetElement(actBar.ELEMENT_COPY_NODE);
		info("--Verify display action cut node --");
		waitForAndGetElement(actBar.ELEMENT_CUT_NODE);
		info("--Verify display action delete node --");
		waitForAndGetElement(actBar.ELEMENT_DELETE_NODE);
		info("--Verify display action lock node --");
		waitForAndGetElement(actBar.ELEMENT_LOCK_NODE);
		info("--Verify display action rename node --");
		waitForAndGetElement(actBar.ELEMENT_RENAME_NODE);
		info("--Verify display action add symlink node --");
		waitForAndGetElement(actBar.ELEMENT_ADD_SYMLINK_NODE);
		info("--Verify display action view information node --");
		waitForAndGetElement(cMenu.ELEMENT_VIEW_INFORMATION);

		info( "-- scroll the window --");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		info("--Verify actions for this node are displayed on top of the top action bar after scrolling --");
		info("--Verify display action copy node --");
		waitForAndGetElement(actBar.ELEMENT_COPY_NODE);
		info("--Verify display action cut node --");
		waitForAndGetElement(actBar.ELEMENT_CUT_NODE);
		info("--Verify display action delete node --");
		waitForAndGetElement(actBar.ELEMENT_DELETE_NODE);
		info("--Verify display action lock node --");
		waitForAndGetElement(actBar.ELEMENT_LOCK_NODE);
		info("--Verify display action rename node --");
		waitForAndGetElement(actBar.ELEMENT_RENAME_NODE);
		info("--Verify display action add symlink node --");
		waitForAndGetElement(actBar.ELEMENT_ADD_SYMLINK_NODE);
		info("--Verify display action view information node --");
		waitForAndGetElement(cMenu.ELEMENT_VIEW_INFORMATION);

		info( "-- clear data	--");
		for(int i=0; i<=5; i++) {
			info("-- Delete both of parent and child nodes --");
			actBar.actionsOnElement(folderName+i, actionType.DELETE);
			waitForElementNotPresent(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", folderName +i));
		}
	}
}
