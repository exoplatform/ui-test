package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.publishactivities;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author havtt
 * Sep, 18th, 2013
 *
 */

public class ECMS_SE_PublishActivities_ContentActivities_Delete extends PlatformBase{

	ManageAlert magAlert = new ManageAlert(driver);

	//Platform
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;
	SitesExplorer SE;

	//Ecms
	ContextMenu cMenu;
	ContentTemplate cTemplate;
	HomePageActivity sActivity;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver, this.plfVersion);
		navToolBar = new NavigationToolbar(driver, this.plfVersion);
		actBar = new ActionBar(driver,this.plfVersion);
		cMenu = new ContextMenu(driver, this.plfVersion);
		cTemplate = new ContentTemplate(driver, this.plfVersion);
		sActivity = new HomePageActivity(driver, this.plfVersion);
		SE = new SitesExplorer(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);

	}

	@AfterMethod
	public void afterMethod() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}


	/**
	 * Qmetry ID: 81225
	 * Remove Content activity after delete a content
	 * 
	 */
	@Test
	public void test01_RemoveContentActivityAfterDeleteAContent(){
		String FILE_TITLE_01 = "ECMS_SE_Content_01" + getRandomNumber();
		By bNode = By.xpath(SE.ELEMENT_SE_NODE.replace("{$node}", FILE_TITLE_01));
		
		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();
		
		info("Create a node in root path");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(FILE_TITLE_01, FILE_TITLE_01, "", "", "", "");

		//Make sure content activity is added successfully
		info("-- Back to the Home page --");
	    navToolBar.goToHomePage();
	     
	    //A new Content acsActivitytivity is added in the activity stream
	    info("-- Check activity after adding a content --");
	    sActivity.checkInforAfterAddingDocument(FILE_TITLE_01, "", "Web Content", "", "", "", "", "");
		
	    // Delete Node added above
	    navToolBar.goToSiteExplorer();
	    click(By.linkText(FILE_TITLE_01));
	    
		info("Delete a node in Site Explorer");
		cMenu.deleteDocument(bNode);

		info("Go to Intranet Homepage");
		navToolBar.goToHomePage();

		//Content acsActivitytivity is removed from the activity stream
		info("Check if a node activity is displayed or not");
		waitForElementNotPresent(By.linkText(FILE_TITLE_01));
	}

	/**
	 * Qmetry ID: 81228
	 * Delete a Content activity from activity stream by its user
	 * 
	 */
	@Test
	public void test02_DeleteaContentActivityFromActivityStreamByItsUser(){
		String FILE_TITLE_02 = "ECMS_SE_Content_02";
		By bNode = By.xpath(SE.ELEMENT_SE_NODE.replace("{$node}", FILE_TITLE_02));

		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();

		info("Create a node in root path");
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(FILE_TITLE_02, FILE_TITLE_02, "", "", "", "");
		
		info("Go to Intranet Homepage");
		navToolBar.goToHomePage();
		
		//A new Content acsActivitytivity is added in the activity stream
	    info("-- Check activity after adding a content --");
	    sActivity.checkInforAfterAddingDocument(FILE_TITLE_02, "", "Web Content", "", "", "", "", "");

		info("Delete a node activity");
		sActivity.deleteActivity(FILE_TITLE_02);
		
		info("Check if a node activity is displayed or not");
		waitForElementNotPresent(By.linkText(FILE_TITLE_02));

		info("Restore data");
		navToolBar.goToSiteExplorer();
		cMenu.deleteDocument(bNode);

	}
}
