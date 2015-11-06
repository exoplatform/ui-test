package org.exoplatform.selenium.platform.wiki.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.wiki.Template;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** 
 * @author lientm
 * @date: 5-July-2013
 */

public class Wiki_Search extends Template {
	ManageAccount magAc;
	NavigationToolbar naTool;
	HomePageActivity activity;
	ManageMember magMem;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		naTool = new NavigationToolbar(driver);
		activity = new HomePageActivity(driver);
		magMem = new ManageMember(driver);
		
		magAc.signIn(DATA_USER1, DATA_PASS);
		goToWiki();
	}
	
	@AfterMethod
	public void afterTest(){
		//magAc.signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**CaseId: 99398
	 * Title: Quick search
	 */
	@Test
	public void test01_QuickSearch(){
		String title01 = "Wiki Page 01 "+getRandomNumber();
		String content01 = "line1/cluster/line2/line3/line4";
		
		String title02 = "Wiki Page 02 With Cluster "+getRandomNumber();
		String content02 = "Content Of Wiki Page 01";
		
		String result1 = ELEMENT_PAGE_RESULT_1.replace("${title}", title01);
		String result2 = ELEMENT_PAGE_RESULT_1.replace("${title}", title02);
		
		info("Add new wiki page include Cluster keyword in Content");		
		addWikiPageWithContentMultiLine(title01, content01);
		
		goToWikiHome();
		info("Add new wiki page include Cluster keyword in Title");		
		addWikiPageRichText(title02, content02);
		savePage();
		
		quickSearch("Cluster");
		assert getText(ELEMENT_SEARCH_RESULT) != "0";
		waitForAndGetElement(result1);
		click(result1);
		waitForAndGetElement(ELEMENT_ADD_PAGE_LINK);
		deleteCurrentWikiPage();
		
		quickSearch("Cluster");
		assert getText(ELEMENT_SEARCH_RESULT) != "0";
		waitForAndGetElement(result2);
		click(result2);
		waitForAndGetElement(ELEMENT_ADD_PAGE_LINK);
		deleteCurrentWikiPage();
	}
	
	/**CaseId: 68845
	 * Advanced search -> wait to merge SOC common functions (need create page in space)
	 * 
	 */
	@Test
	public void test02_AdvancedSearch(){
		String title = "Wiki_search_title_02";
		String content = "line1/line2/line3/line4/line5";
		String spaceName = "SearchSpace021";
		
		magMem.goToAllSpaces();
		magMem.addNewSpace(spaceName, "", "Visible", "Validation", "", "");
		magMem.accessSpace(spaceName);
		goToWikiFromSpace(spaceName);
		addWikiPageWithContentMultiLine(title, content);
		
		goToWiki();
		advancedSearch("Wiki_search_title_02", spaceName);
		assert getText(ELEMENT_SEARCH_RESULT) != "0";
		//waitForAndGetElement(ELEMENT_PAGE_RESULT.replace("${title}", title));
		waitForAndGetElement(ELEMENT_PAGE_RESULT_AUX.replace("${title}", title));
		
		magMem.goToAllSpaces();
		magMem.deleteSpace(spaceName, 180000);
	}
	
	/**CaseId: 70263
	 * Search template
	 */
	@Test
	public void test03_SearchTemplate(){
		String title = "Wiki template title 03";
		String description = "Global";
		String content = "Wiki template content 03";

		info("Search template when the key is matched");
		goToTemplateManagement();
		addTemplate(title, description, content);

		searchTemplate("template");
		waitForAndGetElement(ELEMENT_NEW_TEMPLATE_LINK.replace("${TEMPLATE_TITLE}", title));

		deleteTemplate(title);
	}
}
