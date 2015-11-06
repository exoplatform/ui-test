package org.exoplatform.selenium.platform.plf.functional.unifiedsearch;

import static org.exoplatform.selenium.TestLogger.info;


import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.PageManagement;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.social.Activity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.Utils;

public class PLF_UnifiedSearch_Pages extends Activity {

	PageManagement pageMag;
	ManageAccount magAcc;
	NavigationToolbar naviToolbar;
	PageEditor pagEditor;
	SettingSearchPage qsPage;
	
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAcc = new ManageAccount(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		naviToolbar = new NavigationToolbar(driver, this.plfVersion);
		pagEditor = new PageEditor(driver);
		qsPage = new SettingSearchPage(driver);
		pageMag = new PageManagement(driver);
	}
	
	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();		
		driver.quit();
	}
	/*
	 * == Display a page in the Floating Result ==
	 * Test ID : 121191
	 */
	@Test
	public void test01_DisplayAPageInTheFloatingResult(){
		String pageName="Test";
		
		naviToolbar.goToPageCreationWizard();
		pagEditor.createNewPageEmptyLayout(pageName);
		/*
		 * step 1
		 */
		// search for page and check it's display in the floating box
		click(ELEMENT_QUICK_SEARCH_ICON);
		info("Click on search Icon");
		Utils.pause(2000);
		WebElement searchBox = waitForAndGetElement(ELEMENT_QUICK_SEARCH_TEXTBOX);
		click(ELEMENT_QUICK_SEARCH_TEXTBOX);
		searchBox.sendKeys(pageName);
		waitForAndGetElement(ELEMENT_SEE_ALL_SEARCH_RESULTS);
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_QUICKSEARCH_RESULT_NO_ORDER.replace("${number}","1").replace("${name}",pageName)));
		info("Test successfully");
		/*
		 * delete data
		 */
		naviToolbar.goToApplicationRegistry();
		pageMag.deletePageAtManagePageAndPortalNavigation(pageName, true, "intranet", false, "Administration");
	}
	
}
