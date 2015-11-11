package org.exoplatform.selenium.platform.wiki.functional.information;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.Version;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test cases: KS/Wiki/Version/Compare
 * Date: Dec 17, 2012
 */
public class Wiki_PageInformation_Version_Compare extends Version{
	ManageAccount magAcc;

	public String admin = DATA_USER1;
	public String pass = DATA_PASS;

	@BeforeMethod
	public void beforeMethods(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver);
		magAcc.signIn(admin, pass);
	}

	@AfterMethod
	public void afterMethods(){
		info("-- user.Logout --");
		driver.quit();
	}

	/**
	 * Qmetry ID: 69717
	 * Case ID 01
	 * Compare version when select 2 versions
	 *  Step 1: Create new page 
	 *  Step 2: Add version for page 
	 *  Step 3: Add more version for page 
	 *  Step 4: Open form to view all version of page
	 *  Step 5: Compare versions
	 */
	@Test
	public void test01_CompareVersionWhenSelect2Versions(){
		info("test01_CompareVersionWhenSelect2Versions");
		String[] pageInfo = {"compare 01", "page content 01"};
		goToWiki();
		addBlankWikiPage(pageInfo[0], pageInfo[1], 0);
		editWikiPage("1st edit compare 01", "1st edit page content", 0);
		editWikiPage("2nd edit compare 01", "2nd edit page content", 0);
		goToRevisionsPage();
		waitForAndGetElement(ELEMENT_CURRENT_VERSION.replace("${version}", "3"));
		click(ELEMENT_VIEW_PAGE_HISTORY);
		compareVersion("1", "2");
		Utils.captureScreen("FNC_KS_WIKI_VERSION_COMPARE_01");
		goToWikiPage("compare 01/2nd edit compare 01");
		deleteCurrentWikiPage();
	}

	/**
	 * Qmetry ID: 69718
	 * Case ID 02
	 * Compare version when select only 1 version
	 * Step 1: Create new page
	 * Step 2: Add version for page
	 * Step 3: Add more version for page
	 * Step 4: Open form to view all version of page
	 * Step 5: Compare versions
	 */
	@Test
	public void test02_CompareVersionWhenSelectOnly1Version(){
		info("test02_CompareVersionWhenSelectOnly1Version");
		String[] pageInfo = {"compare 02", "page content 02"};
		goToWiki();
		addBlankWikiPage(pageInfo[0], pageInfo[1], 0);
		editWikiPage("1st edit compare 02", "1st edit page content", 0);
		editWikiPage("2nd edit compare 02", "2nd edit page content", 0);
		goToRevisionsPage();
		waitForAndGetElement(ELEMENT_CURRENT_VERSION.replace("${version}", "3"));
		click(ELEMENT_VIEW_PAGE_HISTORY);
		click(ELEMENT_VERSION_CHECKBOX.replace("{$version}", "2"), 2);
		waitForAndGetElement(ELEMENT_DISABLE_COMPARE_BUTTON_AUX);
		goToWikiPage("compare 02/2nd edit compare 02");
		deleteCurrentWikiPage();
	}

	/**
	 * Qmetry ID: 69716
	 * Case ID 03
	 * Compare version when do not select any version
	 * Step 1: Create new page
	 * Step 2: Add version for page
	 * Step 3: Add more version for page
	 * Step 4: Open form to view all version of page
	 * Step 5: Compare versions
	 */
	@Test
	public void test03_CompareVersionWhenDoNotSelectAnyVersion(){
		info("test03_CompareVersionWhenDoNotSelectAnyVersion");
		String[] pageInfo = {"compare_03", "page content 03"};
		goToWiki();
		addBlankWikiPage(pageInfo[0], pageInfo[1], 0);
		editWikiPage("1st edit compare 03", "1st edit page content", 0);
		editWikiPage("2nd edit compare 03", "2nd edit page content", 0);
		goToRevisionsPage();
		waitForAndGetElement(ELEMENT_CURRENT_VERSION.replace("${version}", "3"));
		click(ELEMENT_VIEW_PAGE_HISTORY);
		waitForAndGetElement(ELEMENT_DISABLE_COMPARE_BUTTON_AUX);
		goToWikiPage("2nd edit/edit compare 03");
		deleteCurrentWikiPage();
	}	
}
