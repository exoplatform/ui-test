package org.exoplatform.selenium.platform.plf.functional.navigation.leftnavigation;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.social.PeopleConnection;
import org.exoplatform.selenium.platform.wiki.ManageDraft;
import org.exoplatform.selenium.platform.wiki.Template;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 * @date 17 Mar 2014 
 */
public class PLF_Navigation_LeftNavigation_CompanyList extends Template{
	ManageAccount acc; 
	NavigationToolbar nav; 
	PeopleConnection pConn;
	ManageDraft mnDraft;
	ForumManageCategory catMag;
	@BeforeMethod
	public void beforeMethods(){	
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver);
		nav = new NavigationToolbar(driver);	
		pConn = new PeopleConnection(driver);
		catMag = new ForumManageCategory(driver, this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);		
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:76804.
	 * Test Case Name: Display icon of application in "COMPANY" list.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/17 13:47:24
	 * PENDING: Can't check icon of element --> need to be checked manually
	 */
	@Test (groups="pending")
	public  void test01_DisplayIconOfApplicationInCOMPANYList() {
		info("Test 1: Display icon of application in COMPANY list");
		/*
		- Connect to Intranet
		 *Input Data: 
		 *Expected Outcome: 
		- Left Navigation is displayed
		- Items are displayed in the "COMPANY" list with an icon (see attachments)		*/ 
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_COMPANY);
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}","1").replace("${menuItem}", "Home"));
		
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}","2").replace("${menuItem}", "Connections"));
		
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}","3").replace("${menuItem}", "Wiki"));
		
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}","4").replace("${menuItem}", "Documents"));
		
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}","5").replace("${menuItem}", "Forums"));
		
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}","6").replace("${menuItem}", "Calendar"));
		
	}

	/**
	 * Case ID:76806.
	 * Test Case Name: Show a selected item in "COMPANY" list.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/17 13:47:24
	 */
	@Test
	public  void test02_ShowASelectedItemInCOMPANYList() {
		info("Test 2: Show a selected item in COMPANY list");
		/*
		- Connect to Intranet
		 *Input Data: 
		 *Expected Outcome: 
		- The left Navigation is displayed
		- The "COMPANY" list is displayed with applications in the following order:* Home* Connections* Wiki* Documents* Forums* Calendar* Other personal pages		*/
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "1").replace("${menuItem}", "Home"));
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "2").replace("${menuItem}", "Connections"));
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "3").replace("${menuItem}", "Wiki"));
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "4").replace("${menuItem}", "Documents"));
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "5").replace("${menuItem}", "Forums"));
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "6").replace("${menuItem}", "Calendar"));
		
		/*
		- Select an application
		 *Input Data: 
		 *Expected Outcome: 
		- The application item is displayed with another style to show page is selected		*/ 
		info("Click on Homepage");
		click(ELEMENT_HOME_PAGE);
		Utils.pause(2000);
		driver.navigate().refresh();
		waitForAndGetElement(ELEMENT_REFRESH);
		
		info("Click on Connections");
		click(ELEMENT_CONNECTION_PAGE);
		waitForAndGetElement(pConn.ELEMENT_EVERYONE_TAB);
		Utils.pause(500);
		nav.goToHomePage();
		
		info("Click on Wiki");
		click(ELEMENT_WIKI_PAGE);
		waitForAndGetElement(ELEMENT_TITLE_WIKI_HOME_LINK);	
		Utils.pause(500);
		nav.goToHomePage();
		
		info("Click on Documments");
		click(ELEMENT_PERSONAL_DOCUMENTS);
		waitForAndGetElement(By.id("uiActionsBarContainer"), 3000, 1, 2);
		Utils.pause(500);
		nav.goToHomePage();
		
		info("Click on Forum");
		click(ELEMENT_FORUM_PAGE);
		waitForAndGetElement(catMag.ELEMENT_FORUM_STATE);
		Utils.pause(500);
		nav.goToHomePage();
		
		info("Click on Calendar");
		click(ELEMENT_CALENDAR_PAGE);
		waitForAndGetElement(ELEMENT_CALENDAR_PANEL);
		Utils.pause(500);
		nav.goToHomePage();
	}

	/**
	 * Case ID:76805.
	 * Test Case Name: Show an UI hover under items  in "COMPANY" list.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/17 13:47:24
	 * ---Pending this case because can't auto check UI hover under items  in "COMPANY" list
	 */
	@Test (groups = "pending")
	public  void test03_ShowAnUIHoverUnderItemsInCOMPANYList() {
		info("Test 3: Show an UI hover under items  in COMPANY list");
		/*
		- Connect to Intranet
		 *Input Data: 
		 *Expected Outcome: 
		- The left Navigation is displayed
		- The "COMPANY" list is displayed with applications in the following order:* Home* Connections* Wiki* Documents* Forums* Calendar* Other personal pages		*/

		/*
		- Move the mouse over each items
		 *Input Data: 
		 *Expected Outcome: 
		- An UI hover is displayed under the item.		*/ 
	}

	/**
	 * Case ID:76803.
	 * Test Case Name: Show applications in "COMPANY" list.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/17 13:47:24
	 */
	@Test
	public  void test04_ShowApplicationsInCOMPANYList() {
		info("Test 4: Show applications in COMPANY list");
		/*
		- Connect to Intranet
		 *Input Data: 
		 *Expected Outcome: 
		- The left Navigation is displayed
		- The "COMPANY" list is displayed with applications in the following order:* Home* Connections* Wiki* Documents* Forums* Calendar* Other personal pages		*/ 
		info("-- Verify order of company navigation --");
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "1").replace("${menuItem}", "Home"));
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "2").replace("${menuItem}", "Connections"));
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "3").replace("${menuItem}", "Wiki"));
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "4").replace("${menuItem}", "Documents"));
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "5").replace("${menuItem}", "Forums"));
		waitForAndGetElement(ELEMENT_LEFT_NAVIGATION_ITEM_INDEX.replace("${index}", "6").replace("${menuItem}", "Calendar"));
	}
}