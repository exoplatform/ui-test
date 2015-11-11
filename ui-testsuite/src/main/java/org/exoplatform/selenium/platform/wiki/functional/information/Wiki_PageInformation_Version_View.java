package org.exoplatform.selenium.platform.wiki.functional.information;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.Set;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.Version;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Date: Dec 18, 2012
 * Test cases: KS\Wiki\Version\View
 */
public class Wiki_PageInformation_Version_View extends Version{
	ManageAccount magAcc;
	Button button;

	public String admin = DATA_USER1;
	public String pass = DATA_PASS;

	@BeforeMethod
	public void beforeMethods(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver);
		button = new Button(driver);
		magAcc.signIn(admin, pass);
	}

	@AfterMethod
	public void afterMethods(){
		info("-- Finished: test case --");
		driver.quit();
	}

	/**
	 * Qmetry ID: 69803 
	 * Version/View
	 * Case ID 01
	 * View content of current version
	 * Step 1: Create new page
	 * Step 2: Add version for page
	 * Step 3: Add more version for page
	 * Step 4: Open form to view all version of page
	 * Step 5: View content of current version
	 */
	@Test
	public void test01_ViewContentOfCurrentVersion(){
		info("test01_ViewContentOfCurrentVersion");
		String[] dataInfo = {"view content 01", "page content 01",
				"1st edit view content 01", "1st edit page content", 
				"2nd edit view content 01", "2nd edit page content"};
		goToWiki();
		addBlankWikiPage(dataInfo[0], dataInfo[1], 0);
		editWikiPage(dataInfo[2], dataInfo[3], 0);
		editWikiPage(dataInfo[4], dataInfo[5], 0);
		goToRevisionsPage();
		viewVersion("3");
		waitForTextPresent(dataInfo[5]);
		goToWikiHome();
		goToWikiPage(dataInfo[4]);
		deleteCurrentWikiPage();
	}

	/**
	 * Qmetry ID: 69804
	 * Version/View
	 * Case ID 02
	 * View content of other version
	 * Step 1: Create new page
	 * Step 2: Add version for page
	 * Step 3: Add more version for page
	 * Step 4: Open form to view all version of page
	 * Step 5: View content of other version
	 */
	@Test
	public void test02_ViewContentOfOtherVersion(){
		info("test02_ViewContentOfOtherVersion");
		String[] dataInfo = {"view content 02", "page content 02",
				"1st edit view content 02", "1st edit page content", 
				"2nd edit view content 02", "2nd edit page content"};
		goToWiki();
		addBlankWikiPage(dataInfo[0], dataInfo[1], 0);
		editWikiPage(dataInfo[2], dataInfo[3], 0);
		editWikiPage(dataInfo[4], dataInfo[5], 0);
		goToRevisionsPage();
		viewVersion("1");
		waitForTextPresent(dataInfo[1]);
		goToWikiHome();
		goToWikiPage(dataInfo[4]);
		deleteCurrentWikiPage();		
	}

	/**
	 * Qmetry ID: 69699
	 * Version/View
	 * Case ID 03
	 * View content of other version while viewing 1 version
	 * Step 1: Create new page
	 * Step 2: Add version for page
	 * Step 3: Add more version for page
	 * Step 4: Open form to view all version of page
	 * Step 5: View content of 1 version
	 * Step 6: View content of other version
	 */
	@Test
	public void test03_ViewContentOfOtherVersionWhileViewing1Version(){
		info("test03_ViewContentOfOtherVersionWhileViewing1Version");
		String[] dataInfo = {"view content 03", "page content 03",
				"1st edit view content 03", "1st edit page content", 
				"2nd edit view content 03", "2nd edit page content"};
		goToWiki();
		addBlankWikiPage(dataInfo[0], dataInfo[1], 0);
		editWikiPage(dataInfo[2], dataInfo[3], 0);
		editWikiPage(dataInfo[4], dataInfo[5], 0);
		goToRevisionsPage();
		viewVersion("1");
		waitForTextPresent(dataInfo[1]);
		button.next();
		waitForTextPresent(dataInfo[3]);
		goToWikiHome();
		goToWikiPage(dataInfo[4]);
		deleteCurrentWikiPage();
	} 

	/**
	 * Qmetry ID: 
	 * Version/View
	 * Case ID 04
	 * Back to current version while view content of other version
	 * Step 1: Create new page
	 * Step 2: Add version for page
	 * Step 3: Add more version for page
	 * Step 4: Open form to view all version of page
	 * Step 5: View content of 1 version
	 * Step 6: Back to current version
	 */
	@Test
	public void test04_BackToCurrentVersionWhileViewContentOfOtherVersion(){
		info("test04_BackToCurrentVersionWhileViewContentOfOtherVersion");
		String[] dataInfo = {"view content 04", "page content 04",
				"1st edit view content 04", "1st edit page content", 
				"2nd edit view content 04", "2nd edit page content"};
		goToWiki();
		addBlankWikiPage(dataInfo[0], dataInfo[1], 0);
		editWikiPage(dataInfo[2], dataInfo[3], 0);
		editWikiPage(dataInfo[4], dataInfo[5], 0);

		goToRevisionsPage();
		viewVersion("1");
		waitForTextPresent(dataInfo[1]);
		click(By.linkText("current version."));
		waitForTextPresent(dataInfo[5]);
		deleteCurrentWikiPage();
	}

	/**
	 * Qmetry ID: 69802
	 * Version/View Change
	 * Case ID 01
	 * View Change of page
	 * Step 1: Create new page
	 * Step 2: Default view change
	 * Step 3: Add version for page
	 * Step 4: Add more version for page
	 * Step 5: View change
	 * Step 6: Change compare version
	 * Step 7: View page history
	 */
	@Test
	public void test05_ViewChangeOfPage(){
		info("test05_ViewChangeOfPage");
		String[] dataInfo = {"view change 01", "page content 01",
				"1st edit view change 01", "1st edit page content", 
				"2nd edit view change 01", "2nd edit page content"};
		goToWiki();
		addBlankWikiPage(dataInfo[0], dataInfo[1], 0);
		waitForElementNotPresent(ELEMENT_VIEW_CHANGE);
		editWikiPage(dataInfo[2], dataInfo[3], 0);
		editWikiPage(dataInfo[4], dataInfo[5], 0);
		viewChange();
		waitForAndGetElement(ELEMENT_VIEW_CHANGE_CURRENT_VERSION);
		waitForAndGetElement(ELEMENT_VIEW_CHANGE_VERSION.replace("${version}","Version 2"));			
		Utils.captureScreen("FNC_KS_WIKI_VERSION_VIEW_CHANGE_01_1");
		changeCompareVersions("1", "2");
		waitForAndGetElement(ELEMENT_VIEW_CHANGE_VERSION.replace("${version}","Version 1"));
		waitForAndGetElement(ELEMENT_VIEW_CHANGE_VERSION.replace("${version}","Version 2"));
		Utils.captureScreen("FNC_KS_WIKI_VERSION_VIEW_CHANGE_01_2");
		viewPageHistory();
		waitForAndGetElement(ELEMENT_RESTORE_LINK.replace("{$version}","2"));
		
		goToWikiPage(dataInfo[4]);
		deleteCurrentWikiPage();
	}

	/**
	 * Qmetry ID: 69808
	 * Version/View Link
	 * Case ID 01
	 * View Link of Revisions
	 * Step 1: Create new page
	 * Step 2: Add version for page
	 * Step 3: Click Link Revisions
	 */
	@Test
	public void test06_ViewLinkOfRevisions(){
		info("test06_ViewLinkOfRevisions");
		String[] dataInfo = {"view link 01", "page content 01",
				"1st edit view link 01", "1st edit page content"};
		goToWiki();
		addBlankWikiPage(dataInfo[0], dataInfo[1], 0);
		editWikiPage(dataInfo[2], dataInfo[3], 0);
		goToRevisionsPage();		
		waitForAndGetElement(ELEMENT_PAGE_INFO_VERSION.replace("${version}","Current Version (v.2)"));	
		waitForAndGetElement(ELEMENT_PAGE_INFO_VERSION.replace("${version}","v.1"));	
		goToWiki();
		click(By.linkText(dataInfo[2]));
		deleteCurrentWikiPage();
	}

	/**
	 * Qmetry ID: 69809
	 * Version/View Link
	 * Case ID 02
	 * View Link of Revisions when edit page content by another user
	 * Step 1: Create new page
	 * Step 2: Add version for page
	 * Step 3: Open another browser
	 * Step 4: Edit page content again
	 * Step 5: Click Link Revisions
	 */
	@Test
	public void test07_ViewLinkOfRevisionsWhenEditPageContentByAnotherUser(){
		info("test07_ViewLinkOfRevisionsWhenEditPageContentByAnotherUser");
		String[] dataInfo = {"view link 02", "page content 02",
				"1st edit view link 02", "1st edit page content", 
				"2nd edit view link 02", "2nd edit page content"};

		info("Step 1: Create new Wiki page");
		goToWiki();	
		addBlankWikiPage(dataInfo[1], dataInfo[2], 0);
		editWikiPage(dataInfo[2], dataInfo[3], 0);
		info("Step 2: Edit revision of Wiki page");
		Set<Cookie> cookies1 = getBrowserCookies();
		String handlesBefore = driver.getWindowHandle();
		openNewBrowser();
		goToWikiPage(dataInfo[2], ManageAccount.userType.PUBLISHER);
		goToRevisionsPage();
		waitForAndGetElement(ELEMENT_CURRENT_VERSION.replace("${version}", "2"));
		Set<Cookie> cookies2 = getBrowserCookies();
		String handlesAfter = driver.getWindowHandle();
		backToPreviousBrowser(cookies1, handlesBefore);
		editWikiPage(dataInfo[4], dataInfo[5], 0);		
		info("Step 3: Open new browser to confirm page revision");
		backToPreviousBrowser(cookies2, handlesAfter);
		goToWiki();
		goToWikiPage(dataInfo[4]);
		goToRevisionsPage();
		if(isElementNotPresent(ELEMENT_VERSION_LINK.replace("{$version}","2")))
			waitForAndGetElement(ELEMENT_VERSION_LINK_AUX.replace("{$version}","2"));	
		waitForAndGetElement(ELEMENT_CURRENT_VERSION.replace("${version}", "3"));
		backToPreviousBrowser(cookies1, handlesBefore);
		deleteCurrentWikiPage();
	}	
}
