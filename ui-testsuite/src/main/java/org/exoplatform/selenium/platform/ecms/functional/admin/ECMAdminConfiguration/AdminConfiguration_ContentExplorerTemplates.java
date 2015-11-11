package org.exoplatform.selenium.platform.ecms.functional.admin.ECMAdminConfiguration;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
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
public class AdminConfiguration_ContentExplorerTemplates extends PlatformBase{


	//Platform
	NavigationToolbar nav;
	ManageAccount magAcc;
	Dialog dialog;
	Button button;
	//Ecms
	EcmsBase ecms; 
	ECMainFunction ecmMain;
	ActionBar actBar;
	ManageDrive magDr;
	ContextMenu cMenu;
	ManageCategory magCa;
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
		magDr = new ManageDrive(driver);
		ecms = new EcmsBase(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		magCa = new ManageCategory(driver);
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
	 *<li> Case ID:124521.</li>
	 *<li> Test Case Name: Check configuration of Content template.</li>
	 *<li> Pre-Condition: Platform is started with no added extensions</li>
	 *<li> Case ID:124523.</li>
	 *<li> Test Case Name: Check configuration of Thumbnails template.</li>
	 *<li> Case ID:124522.</li>

	 */

	@Test
	public  void test01_02_03_CheckConfigurationOfContentTemplate() {
		info("Test 1: Check configuration of Content template");
		String template1="Content";
		String driverName="content_explorer";
		String template2="List";
		String template3="Thumbnails";
		/*Step Number: 1
		 *Step Name: Step 1: Show Content Explorer Templates list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Select Explorer 
			-
			-> Views
			- Go to Explorer Templates tab
		 *Input Data: 

		 *Expected Outcome: 
			- The list of Explorer Templates is shown*/
		ecmMain.goToManageViews();	
		click(magView.ELEMENT_EXPLORER_TEMPLATE);
		waitForAndGetElement(By.xpath(magView.ELEMENT_TEMPLATE.replace("${template}",template1)));
		waitForAndGetElement(By.xpath(magView.ELEMENT_TEMPLATE.replace("${template}",template2)));
		waitForAndGetElement(By.xpath(magView.ELEMENT_TEMPLATE.replace("${template}",template3)));

		/*Step number: 2
		 *Step Name: Step 2: Check Content Explorer Templates list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- Content template appears in the list*/

		/*Step number: 3
		 *Step Name: Step 3: Add new drive
		 *Step Description: 
			- Go to Explorer 
			-> Drives 
			- Click Add Drive button
			- Create a new drive+ Name: content_explorer+ Workspace: dms
			-system+ Permission: any+ Select any view
		 *Input Data: 

		 *Expected Outcome: 
			- The new drive is created*/
		ecmMain.goToManageDrive();	
		magDr.addNewDrive(driverName, "dms-system","Root", "", "", "","Admin/Icons/List", false, true );

		/*Step number: 4
		 *Step Name: Step 4: Check new drive
		 *Step Description: 
			- Go to site explorer 
			- Open drivre content_explorer
			- Enter the path: /exo:ecm/views/templates/ecm-explorer


		 *Input Data: 

		 *Expected Outcome: 
			- The content explorer templates list is displayed*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_CHANGE_DRIVE);
		click(ecms.ELEMENT_SELECT_DRIVER.replace("${drive}", driverName));
		actBar.goToNodeByAddressPath("/exo:ecm/views/templates/ecm-explorer");
		actBar.goToViewMode("List");
		waitForAndGetElement(By.xpath(ecms.ELEMENT_SITE_EXPLORER_RIGHT_PANEL_NODE_PATH1.replace("${name}",template1)));
		waitForAndGetElement(By.xpath(ecms.ELEMENT_SITE_EXPLORER_RIGHT_PANEL_NODE_PATH1.replace("${name}",template2)));
		waitForAndGetElement(By.xpath(ecms.ELEMENT_SITE_EXPLORER_RIGHT_PANEL_NODE_PATH1.replace("${name}",template3)));
	}


}
