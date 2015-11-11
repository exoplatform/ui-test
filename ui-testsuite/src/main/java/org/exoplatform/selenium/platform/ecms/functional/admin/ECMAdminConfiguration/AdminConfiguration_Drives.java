package org.exoplatform.selenium.platform.ecms.functional.admin.ECMAdminConfiguration;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageDrive;
import org.exoplatform.selenium.platform.ecms.admin.ManageView;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.openqa.selenium.By;
import org.testng.annotations.*;


/**
 * @author eXo
 *
 */
public class AdminConfiguration_Drives extends PlatformBase{


	//Platform
	NavigationToolbar nav;
	ManageAccount magAcc;
	Dialog dialog;
	Button button;
	//Ecms
	EcmsBase ecms; 
	ECMainFunction ecmMain;
	ActionBar actBar;
	ContextMenu cMenu;
	ManageDrive magDr;
	ManageView magView;
	ContentTemplate cTemplate;
	SitesExplorer siteExp;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(plfURL);
		info("Login ECMS with " + DATA_USER1);
		nav = new NavigationToolbar(driver, this.plfVersion);
		ecmMain = new ECMainFunction(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		dialog = new Dialog(driver);
		ecms = new EcmsBase(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		magDr = new ManageDrive(driver);
		magView = new ManageView(driver, this.plfVersion);
		cMenu = new ContextMenu(driver, this.plfVersion);
		alert = new ManageAlert(driver, this.plfVersion);
		siteExp = new SitesExplorer(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
	}

	/**
	 *<li> Case ID:124539.</li>
	 *<li> Test Case Name: Check Configuration of Collaboration drive.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 */
	@Test
	public  void test01_CheckConfigurationOfCollaborationDrive() {
		info("Test 1: Check Configuration of Collaboration drive");
		String name ="Collaboration";
		String workspace = "collaboration";
		String path = "/";
		String permission = "Any in Web contributorsAny in Administrators";
		String view = "Admin Web";

		/*Step Number: 1
		 *Step Name: Step 1: Show drive list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> Drives
		 *Input Data: 

		 *Expected Outcome: 
			- The list of Drives is shown*/
		ecmMain.goToManageDrive();	
		
		/*Step number: 2
		 *Step Name: Step 2: Check drive list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The drive Collaboration appears in the list*/

		/*Step number: 3
		 *Step Name: Step 3: Check Collaboration drive
		 *Step Description: 
			- Check Collaboration' row values
		 *Input Data: 

		 *Expected Outcome: 
			- The workspace is collaboration
			- The home path is/
			- The permissions are : Any in Web contributors andAny in Administrators
			- Views associated are : Web and Admin*/
		waitForAndGetElement(By.xpath(magDr.ELEMENT_DRIVE_CHECK_NAME_PATH_WORKSPACE_PERMISSIONS_VIEWS.replace("${name}",name).replace("${path}",path).replace("${workspace}",workspace).replace("${permission}",permission).replace("${view}",view)));

		/*Step number: 4
		 *Step Name: Step 4: Check config when editing drive
		 *Step Description: 
			- Click Edit button of Collaboration' row
			- Check information in the Edit Drive popup
		 *Input Data: 

		 *Expected Outcome: 
			- Show Referenced Documents : option not ticked
			- Show non
			-document Nodes : option ticked
			- Show Sidebar : option ticked
			- Show Hidden Nodes : option not ticked
			- Allow Folder Creation : Content Folder and Document Folder
			- Allowed node type on left tree : **/ 
		click(By.xpath(magDr.ELEMENT_EDIT_A_DRIVE.replace("${name}", name)));
		Utils.pause(2000);

		assert (waitForAndGetElement(magDr.ELEMENT_REFERENCED_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")==null);
		assert (waitForAndGetElement(magDr.ELEMENT_NON_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")!=null);
		assert (waitForAndGetElement(magDr.ELEMENT_SHOW_SIDEBAR_CHECKBOX,5000,1,2).getAttribute("checked")!=null);
		assert (waitForAndGetElement(magDr.ELEMENT_HIDDEN_NODES_CHECKBOX,5000,1,2).getAttribute("checked")==null);

		waitForAndGetElement(By.xpath(magDr.ELEMENT_ALLOWANCE_TYPE.replace("${value}", "*")));
	}

	/**
	 *<li> Case ID:124540.</li>
	 *<li> Test Case Name: Check Configuration of Groups drive.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 */
	@Test
	public  void test02_CheckConfigurationOfGroupsDrive() {
		info("Test 2: Check Configuration of Groups drive");
		String name ="Groups";
		String workspace = "collaboration";
		String path = "/Groups${groupId} /Documents";
		String permission = "Any in ${groupId}";
		String view = "List Icons";
		/*Step Number: 1
		 *Step Name: Step 1: Show drive list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> Drives
		 *Input Data: 

		 *Expected Outcome: 
			- The list of Drives is shown*/
		ecmMain.goToManageDrive();	
		
		/*Step number: 2
		 *Step Name: Step 2: Check drive list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The drive Groups appears in the list*/

		/*Step number: 3
		 *Step Name: Step 3: Check Group drive
		 *Step Description: 
			- Check Group's row values
		 *Input Data: 

		 *Expected Outcome: 
			- The workspace is collaboration
			- The home path is 	/Groups${groupId}/Documents
			- The permissions are : Any in ${groupId}
			- View associated is : List and Icons*/
		waitForAndGetElement(By.xpath(magDr.ELEMENT_DRIVE_CHECK_NAME_PATH_WORKSPACE_PERMISSIONS_VIEWS.replace("${name}",name).replace("${path}",path).replace("${workspace}",workspace).replace("${permission}",permission).replace("${view}",view)));

		/*Step number: 4
		 *Step Name: Step 4: Check Group config when editing Group drive
		 *Step Description: 
			- Click Edit button of Groups' row
			- Check information in the Edit Drive popup
		 *Input Data: 

		 *Expected Outcome: 
			- Show Referenced Documents: option not ticked
			- Show non
			-document Nodes: option not ticked
			- Show Sidebar: option not ticked 
			- Show Hidden Nodes: option not ticked 
			- Allow Folder Creation: Document Folder 
			- Node Types allowed on left tree : **/ 
		click(By.xpath(magDr.ELEMENT_EDIT_A_DRIVE.replace("${name}", name)));
		Utils.pause(2000);

		assert (waitForAndGetElement(magDr.ELEMENT_REFERENCED_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")==null);
		assert (waitForAndGetElement(magDr.ELEMENT_NON_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")==null);
		assert (waitForAndGetElement(magDr.ELEMENT_SHOW_SIDEBAR_CHECKBOX,5000,1,2).getAttribute("checked")==null);
		assert (waitForAndGetElement(magDr.ELEMENT_HIDDEN_NODES_CHECKBOX,5000,1,2).getAttribute("checked")==null);

		waitForAndGetElement(By.xpath(magDr.ELEMENT_ALLOWANCE_TYPE.replace("${value}", "*")));
	}

	/**
	 *<li> Case ID:124541.</li>
	 *<li> Test Case Name: Check Configuration of Managed Sites drive.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/04/21 12:21:48</li>
	 */
	@Test
	public  void test03_CheckConfigurationOfManagedSitesDrive() {
		info("Test 3: Check Configuration of Managed Sites drive");
		String name ="Managed Sites";
		String workspace = "collaboration";
		String path = "/sites";
		String permission = "Any in AdministratorsAny in Web contributors";
		String view = "Web";
		/*Step Number: 1
		 *Step Name: Step 1: Show drive list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> Drives
		 *Input Data: 

		 *Expected Outcome: 
			- The list of Drives is shown*/
		ecmMain.goToManageDrive();	
		
		/*Step number: 2
		 *Step Name: Step 2: Check drive list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The drive Managed Sites appears in the list*/

		/*Step number: 3
		 *Step Name: Step 3: Check Manage Site drive
		 *Step Description: 
			- Check Managed Sites' row values
		 *Input Data: 

		 *Expected Outcome: 
			- The workspace is collaboration
			- The home path is /sites
			- The permissions are : Any in Administrators,Any in Web contributors
			- View associated is: Web*/
		waitForAndGetElement(By.xpath(magDr.ELEMENT_DRIVE_CHECK_NAME_PATH_WORKSPACE_PERMISSIONS_VIEWS.replace("${name}",name).replace("${path}",path).replace("${workspace}",workspace).replace("${permission}",permission).replace("${view}",view)));

		/*Step number: 4
		 *Step Name: Step 4: Check Manage Site drive config
		 *Step Description: 
			- Click Edit button of Managed Sites' row
			- Check information in the Edit Drive popup
		 *Input Data: 

		 *Expected Outcome: 
			- Show Referenced Documents : option not ticked
			- Show non
			-document Nodes : option ticked
			- Show Sidebar : option ticked
			- Show Hidden Nodes : option not ticked
			- Allow Folder Creation: Content Folder, Document Folder
			- Node Types allowed on left tree : **/ 
		click(By.xpath(magDr.ELEMENT_EDIT_A_DRIVE.replace("${name}", name)));
		Utils.pause(2000);

		assert (waitForAndGetElement(magDr.ELEMENT_REFERENCED_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")==null);
		assert (waitForAndGetElement(magDr.ELEMENT_NON_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")!=null);
		assert (waitForAndGetElement(magDr.ELEMENT_SHOW_SIDEBAR_CHECKBOX,5000,1,2).getAttribute("checked")!=null);
		assert (waitForAndGetElement(magDr.ELEMENT_HIDDEN_NODES_CHECKBOX,5000,1,2).getAttribute("checked")==null);

		waitForAndGetElement(By.xpath(magDr.ELEMENT_ALLOWANCE_TYPE.replace("${value}", "*")));
	}

	/**
	 *<li> Case ID:124542.</li>
	 *<li> Test Case Name: Check Configuration of Personal Documents drive.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 */
	@Test
	public  void test04_CheckConfigurationOfPersonalDocumentsDrive() {
		info("Test 4: Check Configuration of Personal Documents drive");
		String name ="Personal Documents";
		String workspace = "collaboration";
		String path = "/Users /${userId} /Private";
		String permission = "Any in Users";
		String view = "List Icons Admin";
		/*Step Number: 1
		 *Step Name: Step 1: Show drive list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> Drives
		 *Input Data: 

		 *Expected Outcome: 
			- The list of Drives is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check drive list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The drive Personal Documents appears in the list*/
		ecmMain.goToManageDrive();	
		
		/*Step number: 3
		 *Step Name: Step 3: Check Personal Documents drive
		 *Step Description: 
			- Check Personal Documents' row values
		 *Input Data: 

		 *Expected Outcome: 
			- The workspace is collaboration
			- The home path is /Users/${userId}/Private
			- The permissions are : Any in Users
			- Views associated are : List, Icons, Admin*/
		waitForAndGetElement(By.xpath(magDr.ELEMENT_DRIVE_CHECK_NAME_PATH_WORKSPACE_PERMISSIONS_VIEWS.replace("${name}",name).replace("${path}",path).replace("${workspace}",workspace).replace("${permission}",permission).replace("${view}",view)));

		/*Step number: 4
		 *Step Name: Step 4: Check Personal Document config
		 *Step Description: 
			- Click Edit button of Personal Documents' row
			- Check information in the Edit Drive popup
		 *Input Data: 

		 *Expected Outcome: 
			- Show Referenced Documents : option not ticked
			- Show non
			-document Nodes : option not ticked
			- Show Sidebar : option not ticked
			- Show Hidden Nodes : option not ticked
			- Allow Folder Creation : Document Folder
			- Node Types allowed on left tree : **/ 
		click(By.xpath(magDr.ELEMENT_EDIT_A_DRIVE.replace("${name}", name)));
		Utils.pause(2000);

		assert (waitForAndGetElement(magDr.ELEMENT_REFERENCED_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")==null);
		assert (waitForAndGetElement(magDr.ELEMENT_NON_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")==null);
		assert (waitForAndGetElement(magDr.ELEMENT_SHOW_SIDEBAR_CHECKBOX,5000,1,2).getAttribute("checked")==null);
		assert (waitForAndGetElement(magDr.ELEMENT_HIDDEN_NODES_CHECKBOX,5000,1,2).getAttribute("checked")==null);

		waitForAndGetElement(By.xpath(magDr.ELEMENT_ALLOWANCE_TYPE.replace("${value}", "*")));
	}

	/**
	 *<li> Case ID:124538.</li>
	 *<li> Test Case Name: Check Configuration of Powers drive (acme site).</li>
	 *<li> Pre-Condition: acme extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/04/21 12:21:48</li>
	 */
	@Test
	public  void test05_CheckConfigurationOfPowersDrive_acmeSite_() {
		info("Test 5: Check Configuration of Powers drive (acme site)");

		String name ="Powers";
		String workspace = "collaboration";
		String path = "/sites /acme /categories /powers";
		String permission = "Any in Web contributors";
		String view = "Categories";

		/*Step Number: 1
		 *Step Name: Step 1: Show drive list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> Drives
		 *Input Data: 

		 *Expected Outcome: 
			- The list of Drives is shown*/
		ecmMain.goToManageDrive();	
		
		/*Step number: 2
		 *Step Name: Step 2: Check drive list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The drive Powers appears in the list*/

		/*Step number: 3
		 *Step Name: Step 3: Check Power drive
		 *Step Description: 
			- Check Powers' row values
		 *Input Data: 

		 *Expected Outcome: 
			- The workspace is collaboration
			- The home path is /sites/acme/categories/powers
			- The permission is: Any in platform/web
			-contributors
			- View associated is : Categories*/
		waitForAndGetElement(By.xpath(magDr.ELEMENT_DRIVE_CHECK_NAME_PATH_WORKSPACE_PERMISSIONS_VIEWS.replace("${name}",name).replace("${path}",path).replace("${workspace}",workspace).replace("${permission}",permission).replace("${view}",view)));

		/*Step number: 4
		 *Step Name: Step 4: Check Power drive config
		 *Step Description: 
			- Click Edit button of Powers' row
			- Check information in the Edit Drive popup
		 *Input Data: 

		 *Expected Outcome: 
			Show Referenced Documents : option not ticked
			- Show non
			-document Nodes : option ticked
			- Show Sidebar : option not ticked
			- Show Hidden Nodes : option not ticked
			- Allow folder creation: none
			- Node Types allowed on left tree : **/
		click(By.xpath(magDr.ELEMENT_EDIT_A_DRIVE.replace("${name}", name)));
		Utils.pause(2000);

		assert (waitForAndGetElement(magDr.ELEMENT_REFERENCED_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")==null);
		assert (waitForAndGetElement(magDr.ELEMENT_NON_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")!=null);
		assert (waitForAndGetElement(magDr.ELEMENT_SHOW_SIDEBAR_CHECKBOX,5000,1,2).getAttribute("checked")==null);
		assert (waitForAndGetElement(magDr.ELEMENT_HIDDEN_NODES_CHECKBOX,5000,1,2).getAttribute("checked")==null);

		waitForAndGetElement(By.xpath(magDr.ELEMENT_ALLOWANCE_TYPE.replace("${value}", "*")));


		/*Step number: 5
		 *Step Name: Step 5: Check option "Allow Folder Creation: None"
		 *Step Description: 
			- Go to Explorer 
			-> Views 
			- Go to Categories view and click Edit Button
			- Check Action tab (collaboration)
		 *Input Data: 

		 *Expected Outcome: 
			- Option Add folder is not ticked.*/ 
		click(magDr.ELEMENT_CANCEL_BUTTON);
		Utils.pause(2000);
		click(ecmMain.ELEMENT_VIEWS_TAB);

		click(By.xpath(magView.ELEMENT_EDIT_A_DRIVE.replace("${name}", "Categories")));
		click(magView.ELEMENT_ACTION_TAB);
		waitForAndGetElement(By.xpath(magView.ELEMENT_ACTION_NOT_PRESENT.replace("${action}", "Add Folder")));

	}

	/**
	 *<li> Case ID:124543.</li>
	 *<li> Test Case Name: Check Configuration of Trash drive.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/04/21 12:21:48</li>
	 */
	@Test
	public  void test06_CheckConfigurationOfTrashDrive() {
		info("Test 6: Check Configuration of Trash drive");

		String name ="Trash";
		String workspace = "collaboration";
		String path = "/Trash";
		String permission = "Any in Administrators";
		String view = "Admin";

		/*Step Number: 1
		 *Step Name: Step 1: Show drive list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> Drives
		 *Input Data: 

		 *Expected Outcome: 
			- The list of Drives is shown*/
		ecmMain.goToManageDrive();	
		
		/*Step number: 2
		 *Step Name: Step 2: Check drive list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The drive Trash appears in the list*/

		/*Step number: 3
		 *Step Name: Step 3: Check Trash drive
		 *Step Description: 
			- Check Trash's row values
		 *Input Data: 

		 *Expected Outcome: 
			- The workspace is collaboration
			- The home path is /Trash
			- The permissions is: Any in Administrators
			- View associated is Admin*/
		waitForAndGetElement(By.xpath(magDr.ELEMENT_DRIVE_CHECK_NAME_PATH_WORKSPACE_PERMISSIONS_VIEWS.replace("${name}",name).replace("${path}",path).replace("${workspace}",workspace).replace("${permission}",permission).replace("${view}",view)));

		/*Step number: 4
		 *Step Name: Step 4: Check Trash drive config
		 *Step Description: 
			- Click Edit button of Trash' row
			- Check information in the Edit Drive popup
		 *Input Data: 

		 *Expected Outcome: 
			- Show Referenced Documents : option is ticked
			- Show non
			-document Nodes : option ticked
			- Show Sidebar : option ticked
			- Show Hidden Nodes : option not ticked
			- Allow Folder Creation : Content Folder, Document Folder
			- Node Types allowed on left tree : **/ 
		click(By.xpath(magDr.ELEMENT_EDIT_A_DRIVE.replace("${name}", name)));
		Utils.pause(2000);

		assert (waitForAndGetElement(magDr.ELEMENT_REFERENCED_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")!=null);
		assert (waitForAndGetElement(magDr.ELEMENT_NON_DOCUMENT_CHECKBOX,5000,1,2).getAttribute("checked")!=null);
		assert (waitForAndGetElement(magDr.ELEMENT_SHOW_SIDEBAR_CHECKBOX,5000,1,2).getAttribute("checked")!=null);
		assert (waitForAndGetElement(magDr.ELEMENT_HIDDEN_NODES_CHECKBOX,5000,1,2).getAttribute("checked")==null);

		waitForAndGetElement(By.xpath(magDr.ELEMENT_ALLOWANCE_TYPE.replace("${value}", "*")));
	}

	/**
	 *<li> Case ID:124584.</li>
	 *<li> Test Case Name: Check Powers drive when acme extension is not installed.</li>
	 *<li> Pre-Condition: Platform is started without acme extension</li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by rosso at 2015/04/21 12:21:48</li>
	 */
	@Test(groups="pending")
	public  void test07_CheckPowersDriveWhenAcmeExtensionIsNotInstalled() {
		info("Test 7: Check Powers drive when acme extension is not installed");
		/*Step Number: 1
		 *Step Name: Step 1: Show drive list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Explorer 
			-
			-> Drives
		 *Input Data: 

		 *Expected Outcome: 
			- The list of Drives is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check drive list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The drive Powers does not appear in the list*/ 

	}
}
