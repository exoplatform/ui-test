package org.exoplatform.selenium.platform.plf.functional.unifiedsearch;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.SearchAdministration;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.wiki.Template;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PLF_UnifiedSearch_SpaceSearch extends Template {

	// Platform
	NavigationToolbar naviToolbar;
	ManageAccount magAcc;
	ManageMember magMember;

	SearchAdministration searchAdmin;
	SettingSearchPage qsPage;

	@BeforeTest
	public void setBeforeTest() {
		initSeleniumTest();
		driver.get(plfURL);
		info("Login with " + DATA_USER1);

		magAcc = new ManageAccount(driver, this.plfVersion);
		magMember = new ManageMember(driver, this.plfVersion);
		naviToolbar = new NavigationToolbar(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		qsPage = new SettingSearchPage(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);

	}

	@AfterTest
	public void setAfterTest() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}


	/**
	 * == Display a Space in the Search Result page ==
	 * Test case ID: 121204
	 * Step 1: Connect to Site / In the Quick Search box, input a valid characters to search a Space (Test) 
	 * Step 2: Click on "See All Search Results"
	 */
	@Test(priority=0)
	public void test01_DisplayASpaceInTheSearchResultPage() {
		String name = "test121204";

		//Pre-condition
		//Add space
		info("-- Create space --");
		magMember.goToMySpacePage();
		magMember.addNewSpace(name, name);
		info("data created");

		/*Step 1: Connect to Site / In the Quick Search box, input a valid characters to search a Space (Test)*/
		/*Step 2: Click on "See All Search Results"*/
		qsPage.quickSearch(name);
		info("searching");

		//check the result
		waitForAndGetElement(qsPage.ELEMENT_RESULT_TITLE);
		waitForAndGetElement(By.xpath(qsPage.ELEMENT_RESULT_PEOPLE_ICON));
		waitForAndGetElement(qsPage.ELEMENT_RESULT_CONTENT_DETAIL);
		//waitForAndGetElement(By.xpath((qsPage.ELEMENT_RESULT_SEARCH_ITEM).replace("${item}", name)));
		waitForAndGetElement(qsPage.ELEMENT_RESULT_ITEM.replace("${keySearch}", name));
		waitForAndGetElement(By.xpath("//*[@class='detail' and contains(text(), '1 Member')]"));
		waitForAndGetElement(By.xpath("//*[@class='detail' and contains(text(), 'Register')]"));

		info("test succeed");

		//clear the data
		magMember.goToAllSpaces();
		magMember.deleteSpace(name,300000);
		info("data cleaned");
	}

	/**
	 * == Not Display a Hidden Space in the Search Result ==
	 * Test case ID: 121544
	 * Step 1: Connect to Intranet / Create a Hidden space (Test)
	 * Step 2: Log in as another user who isn't member of the space "Test" and is not Root acc. / In the Search box, input a valid characters to search the Hidden Space (Test)
	 */
	@Test(priority=1)
	public void test02_NotDisplayAHiddenSpaceInTheSearchResult() {
		String name = "test121544";

		/*Step 1: Connect to Intranet / Create a Hidden space (Test)*/
		info("-- Create space --");
		magMember.goToMySpacePage();
		magMember.addNewSpace(name, name, "Hidden", "Validation", "", "");
		info("data created");

		/*Step 2: Log in as another user who isn't member of the space "Test" and is not Root acc. / In the Search box, input a valid characters to search the Hidden Space (Test)*/
		magAcc.signOut();
		magAcc.userSignIn(userType.PUBLISHER);

		qsPage.quickSearchType(name);
		click(ELEMENT_QUICK_SEARCH_ICON);
		info("searching");
		//check the result
		waitForElementNotPresent(qsPage.ELEMENT_RESULT_ITEM.replace("${keySearch}", name));
		info("test succeed");

		//clean the data
		magAcc.signOut();
		Utils.pause(800);
		magAcc.userSignIn(userType.ADMIN);
		magMember.goToAllSpaces();
		magMember.deleteSpace(name,300000);
		info("data cleaned");

	}

	/**
	 * == Display Spaces in the Floating Result by weight ==
	 * Test case ID: 104359
	 * Step 1:  Connect to Site / In the Quick Search box, input a valid characters to search a space (Test)
	 * https://jira.exoplatform.org/browse/SOC-4890
	 */
	@Test
	public void test03_DisplaySpacesInTheFloatingResultByWeight() {
		String content = "test";
		String content2 = "content";

		//Pre-condition
		//Add space
		info("-- Create space --");
		magMember.goToMySpacePage();
		magMember.addNewSpace(content, content2);
		magMember.goToMySpacePage();
		magMember.addNewSpace(content2, content);
		info("data created");

		/*Step 1:  Connect to Site / In the Quick Search box, input a valid characters to search a space (Test)*/
		qsPage.quickSearch(content);
		info("searching");

		//check the result
		waitForAndGetElement((qsPage.ELEMENT_RESULT_ITEM_ORDER_BY.replace("${keysearch}", content).replace("{$index}","1")));
		waitForAndGetElement((qsPage.ELEMENT_RESULT_ITEM_ORDER_BY.replace("${keysearch}", content2).replace("{$index}","2")));
		info("test succeed");

		//clean the data
		magMember.goToAllSpaces();
		magMember.deleteSpace(content,300000);
		magMember.deleteSpace(content2,300000);
		info("data cleaned");
	}

	/**
	 * == Open a Space from the Search Results ==
	 * Test case ID: 121598
	 * Step 1: Connect to Intranet / In the Quick Search box, input a valid characters to search a Document (Test)
	 * Step 2: Click on the Space's name
	 */
	@Test(priority=3)
	public void test04_OpenASpaceFromTheSearchResults() {
		String name = "test121598";

		//Pre-condition
		//Add space
		info("-- Create space --");
		magMember.goToMySpacePage();
		magMember.addNewSpace(name, name);
		info("data created");

		/*Step 1: Connect to Site / In the Quick Search box, input a valid characters to search a Space (Test)*/
		qsPage.quickSearch(name);
		info("searching");

		/*Step 2: Click on "See All Search Results"*/
		click((qsPage.ELEMENT_RESULT_ITEM_ORDER_BY.replace("${keysearch}", name).replace("{$index}","1")));
		waitForAndGetElement((magMember.ELEMENT_SPACE_CURRENT_NAME).replace("${spaceName}", name));
		info("test succeed");

		//clean the data
		magMember.goToAllSpaces();
		magMember.deleteSpace(name,300000);			
		info("data cleaned");
	}
}
