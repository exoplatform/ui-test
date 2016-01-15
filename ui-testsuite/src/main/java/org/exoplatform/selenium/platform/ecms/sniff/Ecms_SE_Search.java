package org.exoplatform.selenium.platform.ecms.sniff;

import static org.exoplatform.selenium.TestLogger.info;






//import org.exoplatform.selenium.Button;
//import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
//import org.exoplatform.selenium.platform.HomePagePlatform;
//import org.exoplatform.selenium.platform.ManageLogInOut;
//import org.exoplatform.selenium.platform.NavigationToolbar;
//import org.exoplatform.selenium.platform.PlatformBase;
//import org.exoplatform.selenium.platform.PlatformPermission;
//import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
//import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
//import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;


public class Ecms_SE_Search extends ECMS_TestConfig_Part2 {
	/**
	 *<li> Case ID:116566.</li>
	 *<li> Test Case Name: Advanced search.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_AdvancedSearch() {
		info("Test 1: Advanced search");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		navTool.goToSiteExplorer();
		/*Step Number: 1
		 *Step Name: Step 1: Advanced search
		 *Step Description: 
			- Go to Sites Explorer
			- Click Saved Searched icon on the sidebar
			- Click Advanced Search icon
			- Choose Constraint and fill value to search, click Add 
			- Click Search button
		 *Input Data: 

		 *Expected Outcome: 
			Search Result tab is shown with result*/ 
		SEHome.goToAdvancedSearch();
		Utils.pause(2000);
		type(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAME, name, true);
		click(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SEARCHBTN);
		waitForAndGetElement(By.xpath("//*[@class='active']//*[@href='#tab-AdvancedSearchResult']"));

	}

	/**
	 *<li> Case ID:116594.</li>
	 *<li> Test Case Name: Create query in Advanced search.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 **<li> Case ID:116647.</li>
	 *<li> Test Case Name: Delete query in Advanced search.</li>
	 *<li> Pre-Condition: A query is created.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_04_CreateDeleteQueryInAdvancedSearch() {
		info("Test 2: Create query in Advanced search");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		navTool.goToSiteExplorer();
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create query in Advanced search
		 *Input Data: 
			- Go to Sites Explorer
			- Click Saved search icon on the sidebar
			- Click advanced search
			- Go to New query tab
			- Perform to create a query
		 *Expected Outcome: 
			Query is created successfully*/ 
		SEHome.goToAdvancedSearch();
		Utils.pause(2000);
		click(SEHome.ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB);
		type(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY, name, true);
		click(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN);
		waitForAndGetElement(By.xpath("//*[@id='UISavedQuery']//*[text()='"+name+"']"));
		click(By.xpath((SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN).replace("${name}", name)));
		alert.acceptAlert();
		waitForElementNotPresent(By.xpath("//*[text()='"+name+"']"));
	}

	/**
	 *<li> Case ID:116607.</li>
	 *<li> Test Case Name: Simple Search.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_SimpleSearch() {
		info("Test 3: Simple Search");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Simple Search
		 *Input Data: 
			- Go to Content Explorer
			- Input keyword into Search field above action bar
			- Click Quick Search or press Enter
		 *Expected Outcome: 
			The result appears: all documents which has title or content including search keyword appear*/ 
		navTool.goToSiteExplorer();

		type(SEHome.ELEMENT_ACTIONBAR_SEARCHBAR, "document" , true);
		driver.findElement(SEHome.ELEMENT_ACTIONBAR_SEARCHBAR).sendKeys(Keys.ENTER);
	}

	/**
	 *<li> Case ID:116648.</li>
	 *<li> Test Case Name: Execute query in Advanced search.</li>
	 *<li> Pre-Condition: A query is created.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_ExecuteQueryInAdvancedSearch() {
		info("Test 5: Execute query in Advanced search");

		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		navTool.goToSiteExplorer();
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Execute query in Advanced search
		 *Input Data: 
			- Go to Sites Explorer
			- Click Saved search icon on the sidebar
			- Click advanced search
			- Go to Saved Query tab
			- Click Execute icon of a saved query
		 *Expected Outcome: 
			Query is executed successfully*/ 
		SEHome.goToAdvancedSearch();
		Utils.pause(2000);
		click(SEHome.ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB);
		type(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY, name, true);
		click(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN);
		waitForAndGetElement(By.xpath("//*[text()='"+name+"']"));
		
		click(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERYBTN);
		waitForAndGetElement(By.xpath("//*[@class='active']//*[@href='#tab-AdvancedSearchResult']"));
	}

	/**
	 *<li> Case ID:116649.</li>
	 *<li> Test Case Name: Edit query in Advanced search.</li>
	 *<li> Pre-Condition: A query is created.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_EditQueryInAdvancedSearch() {
		info("Test 6: Edit query in Advanced search");
		
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		userAndGroup.addUserAdmin(username1, "");
		userAndGroup.addUserContentManagement(username1, "");
		magAc.signIn(username1, password);
		navTool.goToSiteExplorer();
		
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Edit query in Advanced search
		 *Input Data: 
			- Go to Sites Explorer
			- Click Saved search icon on the sidebar
			- Click advanced search
			- Go to Saved Query tab
			- Click on corresponding Edit icon of a query
			- Perform to edit the query
		 *Expected Outcome: 
			Query is edited successfully*/ 
		SEHome.goToAdvancedSearch();
		Utils.pause(2000);
		click(SEHome.ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB);
		type(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY, name, true);
		click(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN);
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT.replace("${name}",name));
		
		
		click(By.xpath((SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EDITQUERYBTN).replace("${name}", name)));
		select(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_QUERYTYPE, "xPath" );
		click(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEEDITQUERYBTN);
		waitForAndGetElement(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT1.replace("${name}",name));
		click(By.xpath((SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN).replace("${name}", name)));
		alert.acceptAlert();
		waitForElementNotPresent(SEHome.ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT.replace("${name}",name));
	}}