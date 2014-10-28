package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.publishactivities;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;

import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author havtt
 * Sep, 19th, 2013
 *
 */
public class ECMS_SE_PublishActivities_ContentActivities_NotUpdateAS extends PlatformBase{

	//Platform
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;
	SitesExplorer SE;

	//Ecms
	ContextMenu cMenu;
	ContentTemplate cTemplate;
	HomePageActivity sActivity;
	EcmsPermission ePermission;
	EcmsBase ecms;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAcc = new ManageAccount(driver, this.plfVersion);
		navToolBar = new NavigationToolbar(driver, this.plfVersion);
		actBar = new ActionBar(driver,this.plfVersion);
		cMenu = new ContextMenu(driver, this.plfVersion);
		cTemplate = new ContentTemplate(driver, this.plfVersion);
		sActivity = new HomePageActivity(driver, this.plfVersion);
		SE = new SitesExplorer(driver, this.plfVersion);
		ecms = new EcmsBase(driver, this.plfVersion);
		ePermission = new EcmsPermission(driver);
		
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Qmetry ID: 81215
	 * Not Update Content activity after edit permission of a content
	 * Step 1: Add new content
	 * Step 2: Edit permission of the content
	 * Step 3: Check content activity after change permission 
	 */
	@Test
	public void test01_NotUpdateContentActivityAfterEditPermissionOfaContent(){
		String FILE_TITLE_01 = "ECMS_SE_Content_AS_01";
		By bNode = By.xpath(SE.ELEMENT_SE_NODE.replace("{$node}", FILE_TITLE_01));

		//Step 1: Create a new content in Site Explorer
		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a node in root path");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(FILE_TITLE_01, FILE_TITLE_01, "", "", "", "");

		info("Go to Intranet Homepage");
		navToolBar.goToHomePage();
		isTextPresent(FILE_TITLE_01);
		WebElement COMMENT = waitForAndGetElement(sActivity.ELEMENT_COMMENT_LINK.replace("${activityText}",FILE_TITLE_01));
		Utils.pause(3000);
		String nbComment_old = COMMENT.getText();

		//Step 2: Change permission of the content
		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Change permission of a node/content");
		if(isElementNotPresent(bNode) && isElementPresent(By.xpath(actBar.ELEMENT_VIEW_MODE_LINK.replace("${viewName}", "Web")))){
			actBar.goToViewMode("Web");
			click(SE.ELEMENT_SIDEBAR_FILE_EXPLORER);
		}
		ecms.goToNode(FILE_TITLE_01);
		//click(ecms.ELEMENT_PERMISSION_LINK);
		actBar.goToNodePermissionManagement();
		ePermission.deletePermission("any", true);

		info("Go to Intranet Homepage");
		navToolBar.goToHomePage();
		// Step 3 : Check content activity after change permission 
		info("Check if content activity on AS is updated or not");
		WebElement COMMENT2 = waitForAndGetElement(sActivity.ELEMENT_COMMENT_LINK.replace("${activityText}",FILE_TITLE_01));
		String nbComment_new = COMMENT2.getText();
		assert nbComment_old.equals(nbComment_new);

		//Delete data
		info("Restore data");
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(bNode);

	}
	/**
	 * Qmetry ID: 81221
	 * Not Update Content activity after edit shedule settings of a content
	 * Step 1: Add new content
	 * Step 2: Edit Schedule setting of content
	 * Step 3: Check Content's activity after change schedule setting
	 */
	@Test
	public void test02_NotUpdateContentActivityAfterEditSheduleSettingsOfaContent(){
		String FILE_TITLE_02 = "ECMS_SE_Content_AS_02";
		By bNode = By.xpath(SE.ELEMENT_SE_NODE.replace("{$node}", FILE_TITLE_02));
		String STAGE = "Staged";
		//This variable should be reset each time Test suite is run
		String DATE = getCurrentDate("MM/dd/yyyy hh:mm:ss");

		//Step 1: Create a new content in Site Explorer
		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a node in root path");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(FILE_TITLE_02, FILE_TITLE_02, "", "", "", "");

		info("Go to Intranet Homepage");
		navToolBar.goToHomePage();
		Utils.pause(3000);
		WebElement COMMENT = waitForAndGetElement(sActivity.ELEMENT_COMMENT_LINK.replace("${activityText}",FILE_TITLE_02));
		String nbComment_old = COMMENT.getText().trim();
		info(nbComment_old);
		assert nbComment_old.equals("0");

		//Step 2: Change schedule setting of a content

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Set schedule setting of a node/content");
		if(isElementNotPresent(bNode) && isElementPresent(By.xpath(actBar.ELEMENT_VIEW_MODE_LINK.replace("${viewName}", "Web")))){
			actBar.goToViewMode("Web");
			click(SE.ELEMENT_SIDEBAR_FILE_EXPLORER);
		}
		ecms.goToNode(bNode);
		actBar.managePublication(STAGE,DATE);

		//Step 3: Check Content's activity after change schedule setting
		info("Check if content activity is updated or not");
		navToolBar.goToHomePage();
		WebElement COMMENT2 = waitForAndGetElement(sActivity.ELEMENT_COMMENT_LINK.replace("${activityText}",FILE_TITLE_02));
		String nbComment_new = COMMENT2.getText().trim();
		info(nbComment_new);
		assert nbComment_new.equals("1");


		//Delete data
		info("Restore data");
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(bNode);
	}

	/**
	 * Qmetry ID: 81223
	 * Not Update Content activity after check out a content
	 * Step 1: Check in a content
	 * Step 2: Check out content
	 * Step 3: Check activity after checkout
	 */
	@Test
	public void test03_NotUpdateASafterCheckoutContent(){
		String FILE_TITLE_03 = "ECMS_SE_Content_AS_03";
		By bNode = By.xpath(SE.ELEMENT_SE_NODE.replace("{$node}", FILE_TITLE_03));

		//Step 1: Create a new content in Site Explorer
		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a node in root path");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(FILE_TITLE_03, FILE_TITLE_03, "", "", "", "");

		info("Go to Intranet Homepage");
		navToolBar.goToHomePage();
		Utils.pause(3000);
		WebElement COMMENT = waitForAndGetElement(sActivity.ELEMENT_COMMENT_LINK.replace("${activityText}",FILE_TITLE_03));
		String nbComment_old = COMMENT.getText();

		//Step 2: Check out a node in Site Explorer
		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Checkin a node/content");
		cMenu.contextMenuAction(bNode, cMenu.ELEMENT_MENU_CHECKIN);

		info("Checkout a node/content");
		cMenu.contextMenuAction(bNode, cMenu.ELEMENT_MENU_CHECKOUT);

		info("Go to Intranet Homepage");
		navToolBar.goToHomePage();
		
		//Step 3: Check activity after checkout
		info("Check if content activity is updated or not");
		WebElement COMMENT2 = waitForAndGetElement(sActivity.ELEMENT_COMMENT_LINK.replace("${activityText}",FILE_TITLE_03));
		String nbComment_new = COMMENT2.getText();
		assert nbComment_old.equals(nbComment_new);

		//Delete data
		info("Restore data");
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(bNode);
	}
}