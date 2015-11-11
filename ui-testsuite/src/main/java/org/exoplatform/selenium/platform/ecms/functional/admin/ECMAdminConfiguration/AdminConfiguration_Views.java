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
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
import org.exoplatform.selenium.platform.ecms.admin.ManageView;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.testng.annotations.*;


public class AdminConfiguration_Views extends PlatformBase{

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
	ManageView magView;
	ECMainFunction ecmMain;
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
		ecmMain = new ECMainFunction(driver, this.plfVersion);
		userGroup = new UserGroupManagement(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		cTemplate = new ContentTemplate(driver);
		magView = new ManageView(driver , this.plfVersion);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
	}		

	/**
	 *<li> Case ID:124578.</li>
	 *<li> Test Case Name: Check configuration of Admin view.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124579.</li>
	 *<li> Test Case Name: Check configuration of Web view.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124580.</li>
	 *<li> Test Case Name: Check configuration of Icons view.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124581.</li>
	 *<li> Test Case Name: Check configuration of List view.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124582.</li>
	 *<li> Test Case Name: Check configuration of Categories view.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_03_04_05_CheckConfigurationOfAllViews() {
		String actions1="openDocument, Upload, Add Folder, View Metadata, "
				+ "View Permissions, Edit Document, Manage Actions, Manage Relations,"
				+ " Check In, Check Out, View Properties, Manage Auditing, Show JCR Structure";
		
		String actions2="Add Category, Add Document, Edit Document, Upload, View Permissions, Manage Publication, Manage Categories, Tag Document, Vote, Watch Document, Request Approval, Approve Content, Publish, Comment";
		String actions3="openDocument, Upload, Add Folder, View Metadata, View Permissions, Edit Document, Tag Document, Vote, Watch Document, Manage Versions, Check In, Check Out, Comment";
		String actions4="openDocument, Upload, Add Folder, View Metadata, View Permissions, Edit Document, Tag Document, Vote, Overload Thumbnail, Watch Document, Manage Versions, Check In, Check Out, Comment";
		String actions5="Add Category, Add Folder, Add Document, Edit Document, Upload, View Permissions, Manage Publication, Manage Categories, Tag Document, Vote, Watch Document, Request Approval, Approve Content, Publish, Add Translation, Comment";
		info("Test 1: Check configuration of Admin view");
	
		/*Step Number: 1
		 *Step Name: Step 1: Show View list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> View
		 *Input Data: 

		 *Expected Outcome: 
			The list of Views is shown*/
		ecmMain.goToManageViews();
		/*Step number: 2
		 *Step Name: Step 2: Check View list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			The view Admin appears in the list*/
		waitForAndGetElement(magView.ELEMENT_VIEW_ICON.replace("${viewName}", "Admin"));
		waitForAndGetElement(magView.ELEMENT_VIEW_ICON.replace("${viewName}", "Categories"));
		waitForAndGetElement(magView.ELEMENT_VIEW_ICON.replace("${viewName}", "List"));
		waitForAndGetElement(magView.ELEMENT_VIEW_ICON.replace("${viewName}", "Icons"));
		waitForAndGetElement(magView.ELEMENT_VIEW_ICON.replace("${viewName}", "Web"));

		/*Step number: 3
		 *Step Name: Step 3: Check view permission
		 *Step Description: 
			- Check Admin view permission
		 *Input Data: 

		 *Expected Outcome: 
			- Permission value is: Any in Administrators*/
		waitForAndGetElement(magView.ELEMENT_VIEW_PERMISSION.replace("${viewName}", "Admin").replace("${permission}", "Any in Administrators"));
		waitForAndGetElement(magView.ELEMENT_VIEW_PERMISSION.replace("${viewName}", "Categories").replace("${permission}", "Any in Web contributors"));
		waitForAndGetElement(magView.ELEMENT_VIEW_PERMISSION.replace("${viewName}", "List").replace("${permission}", "Any in Users"));
		waitForAndGetElement(magView.ELEMENT_VIEW_PERMISSION.replace("${viewName}", "Icons").replace("${permission}", "Any in Users"));
		waitForAndGetElement(magView.ELEMENT_VIEW_PERMISSION.replace("${viewName}", "Web").replace("${permission}", "Any in Web contributors"));
		
		/*Step number: 4
		 *Step Name: Step 4: Check template
		 *Step Description: 
			- Click [Edit] icon of Admin view 
			- In View tab, check the template
		 *Input Data: 

		 *Expected Outcome: 
			- The template is : List*/
		/*Step number: 5
		 *Step Name: Step 5: Check action of admin view
		 *Step Description: 
			- In Action tab, check tab name and actions ticked
		 *Input Data: 

		 *Expected Outcome: 
			- Tab name is : Admin
			- Actions ticked are : Add Folder, Edit Document, Manage Actions, Manage Auditing, Manage Relations, Show JCR Structure, Upload, View Metadata, View Properties, View Permission*/
		click(magView.ELEMENT_EDIT_VIEW.replace("${viewName}", "Admin"));
		click(magView.ELEMENT_ACTION_TAB);
		waitForAndGetElement(magView.ELEMENT_VIEW_ACTION_LIST.replace("${viewName}", "Admin").replace("${action}", actions1));
		button.cancel();
		
		click(magView.ELEMENT_EDIT_VIEW.replace("${viewName}", "Categories"));
		click(magView.ELEMENT_ACTION_TAB);
		waitForAndGetElement(magView.ELEMENT_VIEW_ACTION_LIST.replace("${viewName}", "Collaboration").replace("${action}", actions2));
		button.cancel();
		
		click(magView.ELEMENT_EDIT_VIEW.replace("${viewName}", "List"));
		click(magView.ELEMENT_ACTION_TAB);
		waitForAndGetElement(magView.ELEMENT_VIEW_ACTION_LIST.replace("${viewName}", "List").replace("${action}", actions3));
		button.cancel();
		
		click(magView.ELEMENT_EDIT_VIEW.replace("${viewName}", "Icons"));
		click(magView.ELEMENT_ACTION_TAB);
		waitForAndGetElement(magView.ELEMENT_VIEW_ACTION_LIST.replace("${viewName}", "Icons").replace("${action}", actions4));
		button.cancel();
		
		click(magView.ELEMENT_EDIT_VIEW.replace("${viewName}", "Web"));
		click(magView.ELEMENT_ACTION_TAB);
		waitForAndGetElement(magView.ELEMENT_VIEW_ACTION_LIST.replace("${viewName}", "Authoring").replace("${action}", actions5));
		button.cancel();
	}

	/**
	 *
	 */
	@Test
	public  void test02_CheckConfigurationOfWebView() {
		info("Test 2: Check configuration of Web view");
		/*Step Number: 1
		 *Step Name: Step 1: Show View list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> View
		 *Input Data: 

		 *Expected Outcome: 
			The list of Views is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check View list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			The view Web appears in the list*/

		/*Step number: 3
		 *Step Name: Step 3: Check view permission
		 *Step Description: 
			- Check Web view permission
		 *Input Data: 

		 *Expected Outcome: 
			- Permission value is: Any in Web contributors*/

		/*Step number: 4
		 *Step Name: Step 4: Check view template
		 *Step Description: 
			- Click [Edit] icon of Web view
			- In View tab, check the template
		 *Input Data: 

		 *Expected Outcome: 
			- The template is : Content*/

		/*Step number: 5
		 *Step Name: Step 5: Check action of view
		 *Step Description: 
			- In Action tab, check tab name and actions ticked
		 *Input Data: 

		 *Expected Outcome: 
			- Tab name is Authoring
			- Actions ticked are : Add Category, Add Document, Add Folder, Edit Document, Manage Categories, Manage Publication, Approve Content, Publish, Request Approval, Upload, Tag Document, Vote, Watch Document, Add Translation,View Permissions and Comment*/

		/*Step number: 6
		 *Step Name: Step 6: Check action of view on Site Explorer
		 *Step Description: 
			- Go to Site explorer 
			- Select Site management drive
			- Select Web view
			- Add a web content
			- Check all actions
		 *Input Data: 

		 *Expected Outcome: 
			- All actions listed above in the config must be available*/ 

	}

	/**
	 *
	 */
	@Test
	public  void test03_CheckConfigurationOfIconsView() {
		info("Test 3: Check configuration of Icons view");
		/*Step Number: 1
		 *Step Name: Step 1: Show View list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> View
		 *Input Data: 

		 *Expected Outcome: 
			The list of Views is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check View list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			The view Icons appears in the list*/

		/*Step number: 3
		 *Step Name: Step 3: Check view permission
		 *Step Description: 
			- Check permission of Icons view
		 *Input Data: 

		 *Expected Outcome: 
			- Permission value is: Any in Users*/

		/*Step number: 4
		 *Step Name: Step 4: Check view template
		 *Step Description: 
			- Click Edit icon of Icons view
			- In View tab, check the template
		 *Input Data: 

		 *Expected Outcome: 
			- The template is : Thumbnails*/

		/*Step number: 5
		 *Step Name: Step 5: Check action of view
		 *Step Description: 
			- Go to Action tab 
			- Check tab name and all actions ticked
		 *Input Data: 

		 *Expected Outcome: 
			- tab name is : Icons
			- Actions ticked are : Add Folder, Edit Document, Manage Versions, Overload Thumbnail,Tag Document, Upload, View Metadata, Vote, Watch Document, Comment, View Permissions*/

		/*Step number: 6
		 *Step Name: Step 6: Add Icons view for drive
		 *Step Description: 
			- Go to Explorer 
			-
			-> Drive
			- Select a drive (Managed Site for ex)
			- Click Edit icon
			- Go to Apply View tab
			- Select Icons view and save
		 *Input Data: 

		 *Expected Outcome: 
			- Icons view is added for drive*/

		/*Step number: 7
		 *Step Name: Step 7: Check action of view on Site Explorer
		 *Step Description: 
			- Go to site explorer 
			- Select Site Management drive
			- Select Icons view
			- Create new content 
			- Check actions
		 *Input Data: 

		 *Expected Outcome: 
			- All actions listed above should be available*/ 

	}

	/**
	 *
	 */
	@Test
	public  void test04_CheckConfigurationOfListView() {
		info("Test 4: Check configuration of List view");
		/*Step Number: 1
		 *Step Name: Step 1: Show View list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> View
		 *Input Data: 

		 *Expected Outcome: 
			The list of Views is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check View list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			The view List appears in the list*/

		/*Step number: 3
		 *Step Name: Step 3: Check view permission
		 *Step Description: 
			- Check List view permission
		 *Input Data: 

		 *Expected Outcome: 
			- Permission value is: Any in Users*/

		/*Step number: 4
		 *Step Name: Step 4: Check view template
		 *Step Description: 
			- Click [Edit] icon of List view
			- In View tab, check the template
		 *Input Data: 

		 *Expected Outcome: 
			- The template is : List*/

		/*Step number: 5
		 *Step Name: Step 5: Check action of view
		 *Step Description: 
			- Go to Action tab, check tab name and ticked action
		 *Input Data: 

		 *Expected Outcome: 
			- Tab name should be : List
			- Action ticked should be Add Folder, Edit Document, Manage Versions, Tag Document, Upload, View Metadata, Vote, Watch Document, Comment, View Permission*/

		/*Step number: 6
		 *Step Name: Step 6
		 *Step Description: 
			- Go to Personal Document drive
			- Select List view
			- Add new content
			- Check actions available
		 *Input Data: 

		 *Expected Outcome: 
			- Action available must match with the actions listed above and configured.*/ 

	}

	/**
	 *
	 */
	@Test
	public  void test05_CheckConfigurationOfCategoriesView() {
		info("Test 5: Check configuration of Categories view");
		/*Step Number: 1
		 *Step Name: Step 1: Show View list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> View
		 *Input Data: 

		 *Expected Outcome: 
			The list of Views is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check View list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			The view Categories appears in the list*/

		/*Step number: 3
		 *Step Name: Step 3: Check Permission of view
		 *Step Description: 
			- Check Categories view permission
		 *Input Data: 

		 *Expected Outcome: 
			- Permission value is: Any in web contributor*/

		/*Step number: 4
		 *Step Name: Step 4: Check template
		 *Step Description: 
			- Click Edit icon of Categories view
			- In the View tab, check the template
		 *Input Data: 

		 *Expected Outcome: 
			- The template is : Content*/

		/*Step number: 5
		 *Step Name: Step 5: Check Action of view
		 *Step Description: 
			- In Action tab, check tab name and actions ticked
		 *Input Data: 

		 *Expected Outcome: 
			- The tab name is : Collaboration
			- Actions ticked are : Add Category, Add Document,Edit Document, View Permissions, Manage Categories, Manage Publication,Approve Content, Publish, Request Approval, Upload, Tag Document, Vote, Comment, Watch Document*/

		/*Step number: 6
		 *Step Name: Step 6: Add Categories view for drive
		 *Step Description: 
			- Go to Explorer 
			-
			-> Drive
			- Select a drive (Managed Site for ex)
			- Click Edit icon
			- Go to Apply view tab
			- Select Categories view and save
		 *Input Data: 

		 *Expected Outcome: 
			- Category view is added for drive*/

		/*Step number: 7
		 *Step Name: Step 7: Check action on Site Explorer
		 *Step Description: 
			- Go to Site Explorer
			- Select Site Management drive
			- Select Categories view 
			- Create new content
			- Check all actions are available
		 *Input Data: 

		 *Expected Outcome: 
			- All actions listed above (and configured) are available.*/ 

	}
}
