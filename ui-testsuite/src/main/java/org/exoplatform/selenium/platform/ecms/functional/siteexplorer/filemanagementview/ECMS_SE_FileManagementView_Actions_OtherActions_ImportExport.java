package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.filemanagementview;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate.folderType;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu.actionType;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @date Mar 16, 2015
 * @author anhpp
 */

public class ECMS_SE_FileManagementView_Actions_OtherActions_ImportExport extends PlatformBase{

	//Platform
	ManageAccount magAcc;
	ActionBar actBar;
	NavigationToolbar navToolBar;

	//Ecms
	EcmsBase ecms;
	ContentTemplate cTemplate;
	SitesExplorer siteExp;
	ContextMenu cMenu;
	Button btn;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(plfURL);
		info("Login ECMS with " + DATA_USER1);
		magAcc = new ManageAccount(driver);
		actBar = new ActionBar(driver);
		cTemplate = new ContentTemplate(driver);
		siteExp = new SitesExplorer(driver);
		navToolBar = new NavigationToolbar(driver);
		ecms = new EcmsBase(driver);
		cMenu= new ContextMenu(driver);
		btn = new Button(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * == Export a folder ==
	 * Test case ID: 119554
	 * Step 1: Create a node
	 * Step 2: Open form to export node
	 * Step 3: Select  format for exported document
	 * Step 4: Export node
	 */
	@Test
	public void test01_ExportAFolder(){
		/*Declare variables*/
		String folder = "folder15";
		/*Step 1: Create a node*/		
		//Add icon "Export" to action bar
		navToolBar.goToPersonalDocuments();
		actBar.goToViewMode("Admin");
		actBar.addItem2ActionBar("exportNode", actBar.ELEMENT_EXPORT_LINK, "Admin", "Admin");

		//Go to intranet/documents
		navToolBar.goToPersonalDocuments();

		//Create a folder
		info("-- Create a folder --");
		cTemplate.createNewFolder(folder, folderType.None);

		//Select a folder node
		info("-- Select folder node --");
		actBar.goToNodeByAddressPath("/"+folder);

		/*Step 2: Open form to export node*/
		/*Step 3: Select  format for exported document*/
		/*Step 4: Export node*/
		/*Note: Use function getDriverAutoSave() instead of initSeleniumTest() in method beforeMethods
		 * to set output folder of export function
		 */
		//Export node in zip file, systemview, 
		info("Export node in zip file, systemview");
		actBar.exportNode(true, true, false);

		//Node is exported into your computer
		assert checkFileExisted("sysview.zip");

		/*Clear data*/
		//delete  node
		actBar.goToNodeByAddressPath("/");
		info("-- Delete folder node --");
		actBar.actionsOnElement(folder, actionType.DELETE);
		waitForElementNotPresent(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", folder));

		//delete file on computer
		deleteFile("sysview.zip");
		assert (!checkFileExisted("sysview.zip"));
	}

	/**
	 * == Import a node ==
	 * Test case ID: 119555
	 * Step 1: Create a node
	 * Step 2: Export a node
	 * Step 3: Open 'Import Node' pop-up
	 * Step 4: Browse an .xml file to import
	 * Step 5: Select format of imported node
	 * Step 6: Import node
	 */
	@Test
	public void test02_ImportANode(){
		/*Declare variables*/
		String folder = "folder16";
		String subfolder = "subfolder16";
		String filePath = "TestData/TestOutput/sysview.xml";
		/*Step 1: Create a node*/		
		//Add icon "Export" and "Import" to action bar
		navToolBar.goToPersonalDocuments();
		actBar.goToViewMode("Admin");
		actBar.addItem2ActionBar("importNode", actBar.ELEMENT_IMPORT_LINK, "Admin", "Admin");
		actBar.addItem2ActionBar("exportNode", actBar.ELEMENT_EXPORT_LINK, "Admin", "Admin");

		//Go to intranet/documents
		navToolBar.goToPersonalDocuments();

		//Create a folder
		info("-- Create a folder --");
		cTemplate.createNewFolder(folder, folderType.None);
		cTemplate.createNewFolder(subfolder, folderType.None);

		//Select a folder node to export
		info("-- Select sub folder node --");
		actBar.goToNodeByAddressPath("/"+subfolder);

		/*Step 2: Export a node*/				
		//Export node in xml file, systemview, 
		info("Export node in xml file, systemview");
		actBar.exportNode(true, false, false);

		//Node is exported into your computer
		assert checkFileExisted("sysview.xml");

		/* Step 3: Open 'Import Node' pop-up */
		/* Step 4: Browse an .xml file to import*/
		/* Step 5: Select format of imported node*/
		/* Step 6: Import node*/
		//Import node
		//Select a folder node to import
		info("-- Select folder node --");
		actBar.goToNodeByAddressPath("/"+folder);

		info("Import node");
		actBar.importNode(filePath,"","Create New",false);

		//Node is imported into Content folder
		info("Node is imported into Content folder");
		waitForAndGetElement(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", subfolder));

		/*Clear data*/
		//Go to root node
		actBar.goToNodeByAddressPath("/");
		info("-- Delete folder node --");
		actBar.actionsOnElement(folder, actionType.DELETE);
		actBar.actionsOnElement(subfolder, actionType.DELETE);

		//Verify folder node is not present
		waitForElementNotPresent(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", folder));
		waitForElementNotPresent(ecms.ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", subfolder));

		//delete file on computer
		deleteFile("sysview.xml");
		assert (!checkFileExisted("sysview.xml"));
	}

}



