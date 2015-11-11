package org.exoplatform.selenium.platform.ecms.functional.admin.ECMAdminConfiguration;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.AdvancedSearch;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.testng.annotations.*;


public class AdminConfiguration_Queries extends PlatformBase {

	//Platform
	NavigationToolbar navBar;
	ManageAccount magAcc;
	UserGroupManagement userGroup;
	Dialog dialog;
	Button button;
	//Ecms
	EcmsBase ecms; 
	ActionBar actBar;
	ContextMenu cMenu;
	ContentTemplate cTemplate;
	ManageCategory magCa;

	ManageAlert alert;
	//Data for these test cases
	String categoryName = "category1";
	String newCategoryName = "category2";
	String optionLifeCycle = "Content Addition"; 
	String nodeTargetPath = "jcr:system/exo:namespaces";    
	AdvancedSearch aSearch;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(plfURL);
		info("Login ECMS with " + DATA_USER1);
		navBar = new NavigationToolbar(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		dialog = new Dialog(driver);
		ecms = new EcmsBase(driver, this.plfVersion);
		magCa = new ManageCategory(driver);
		cMenu = new ContextMenu(driver, this.plfVersion);
		alert = new ManageAlert(driver, this.plfVersion);
		actBar = new ActionBar(driver , this.plfVersion);
		userGroup = new UserGroupManagement(driver);
		aSearch = new AdvancedSearch(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
		//actions = null;
	}	


	/**
	 *<li> Case ID:124567.</li>
	 *<li> Test Case Name: Check My Documents query configuration.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckMyDocumentsQueryConfiguration() {
		info("Test 1: Check My Documents query configuration");
		String username = getRandomString();
		String password = "gtngtn";
		String firstName = username;
		String lastName = username;
		String email1 = username+"@exoplatform.com";
		/*Step Number: 1
		 *Step Name: Step 1: Create new user
		 *Step Description: 
			- Login as administrator
			- Go to Admin 
			-
			-> Users 
			-
			-> Add user
			- Create a new platform user : ecmconfigtest
		 *Input Data: 

		 *Expected Outcome: 
			- This user is created*/

		navBar.goToNewStaff();
		magAcc.addNewUserAccount(username, password, password, firstName, lastName, "", email1, null, null, true);
		navBar.goToCommunity();

		userGroup.chooseGroupTab();
		userGroup.selectGroup("Platform/Content Management", true);
		userGroup.addUsersToGroup(username, "*", false, false);

		/*Step number: 2
		 *Step Name: Step 2: Check query My Document
		 *Step Description: 
			- Connect with this new user
			- Go to Site Explorer, select Site Management drive for ex
			- Click on search icon in the left panel
		 *Input Data: 

		 *Expected Outcome: 
			- The query My Documents appears in the list*/

		magAcc.signIn(username, password);
		navBar.goToSiteExplorer();
		click(aSearch.ELEMENT_SAVED_SEARCH_ICON);
		waitForAndGetElement(aSearch.ELEMENT_SAVED_SEARCH_LIST.replace("${name}", "My Documents"));

		/*Step number: 3
		 *Step Name: Step 3: Check config of My Document
		 *Step Description: 
			- Click on icon Advanced search (on the top of the search panel)
			- Go to Saved Query Tab
			- Check properties of My Documents query
		 *Input Data: 

		 *Expected Outcome: 
			- Type : XPath
			- Permission: user (ecmconfigtest)*/ 
		aSearch.goToSavedSearch();
		waitForAndGetElement(aSearch.ELEMENT_SAVE_QUERY_PERMISSION.replace("${query}", "My Documents").replace("${user}", username));
	}
}
