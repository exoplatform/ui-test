package org.exoplatform.selenium.platform.wiki.functional.search;


import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.BasicAction;

/**
 *
 * @author HangNTT
 * @date: 13/12/2012
 * updated by anhpp
 */
public class Wiki_Advanced_Search extends BasicAction {

	ManageAccount magAc;
	
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		magAc = new ManageAccount(driver);

		magAc.signIn(DATA_USER1, DATA_PASS);
	}
	
	//Qmetry ID: 118253
	//Search when the keyword is matched
	@Test
	public void test01_SearchWhenKeyWordIsMatched() {
		
		String PAGE_NAME1 = "Wiki_Advanced_Search_01" + getRandomNumber();

		By ELEMENT_PAGE1 = By.xpath(ELEMENT_RESULT_SEARCH.replace("${pageName}", PAGE_NAME1));

		goToWiki();

		addBlankWikiPage(PAGE_NAME1, PAGE_NAME1, 0);

		advancedSearch(PAGE_NAME1, "Intranet");

		click(ELEMENT_PAGE1);

		deleteCurrentWikiPage();
	}
	
	//Qmetry ID: 118255
	//Search when the keyword is not matched
	@Test
	public void test02_SearchWhenKeyWordIsNotMatched() {

		String PAGE_NAME1 = "Wiki_Advanced_Search_02";

		By ELEMENT_PAGE1 = By.linkText(PAGE_NAME1);

		goToWiki();

		addBlankWikiPage(PAGE_NAME1, PAGE_NAME1, 0);

		advancedSearch("bbb", "Intranet");

		//waitForTextPresent(ELEMENT_VERIFY_MESSAGE);
		waitForAndGetElement(ELEMENT_VERIFY_RESULT_SEARCH.replace("${pageName}", "bbb"), DEFAULT_TIMEOUT, 1, 2);
			
		click(ELEMENT_PAGE1);

		deleteCurrentWikiPage();
	}
	
	//Qmetry ID: 118257
	//View content of search result when user does not have permission to view page
	@Test
	public void test03_ViewContentOfSearchResultWhenUserDoesNotHavePermissionToViewPage() {

		String[] user1= {DATA_USER3};

		String PAGE_NAME1 = "Wiki_Advanced_Search_03";

		By ELEMENT_PAGE1 = By.linkText(PAGE_NAME1);

		goToWiki();

		addBlankWikiPage(PAGE_NAME1, PAGE_NAME1, 0);

		deletePagePermission("any");

		addPagePermission(0,user1);

		editPagePermission(DATA_USER3, false,false, false, 2);

		magAc.signOut();

		magAc.signIn(DATA_USER3, DATA_PASS);

		goToWiki();

		advancedSearch(PAGE_NAME1, "Intranet");

		//waitForTextPresent(ELEMENT_VERIFY_MESSAGE);
		waitForAndGetElement(ELEMENT_VERIFY_RESULT_SEARCH.replace("${pageName}", PAGE_NAME1), DEFAULT_TIMEOUT, 1, 2);

		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);

		goToWiki();

		click(ELEMENT_PAGE1);

		deleteCurrentWikiPage();
	}

	@AfterMethod
	public void afterTest(){
		//signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
