package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.filemanagementview;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu.actionType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author hangntt
 * August, 2013
 * updated by anhpp
 */
public class ECMS_SE_FileManagementView_Actions_Sort extends PlatformBase {

	//Platform
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;

	//Ecms
	EcmsBase ecms;
	ContentTemplate cTemp;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver,this.plfVersion);
		navToolBar = new NavigationToolbar(driver,this.plfVersion);
		actBar = new ActionBar(driver,this.plfVersion);
		ecms = new EcmsBase(driver,this.plfVersion);
		cTemp = new ContentTemplate(driver,this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**
	 * Qmetry ID: 119505 & 119506
	 * Sort & Revert the default sort of documents by "Date"
	 */
	@Test
	public void test01_02_RevertTheDefaultSortOfDocumentsByDate(){
		String dFile = "TestData/ECMS_DMS_SE_Upload_docfile.doc";
		String document = "ECMS_DMS_SE_Upload_docfile";
		String aFolder = "testdocuments";

		info("-- Go to Personal Documents --");
		navToolBar.goToPersonalDocuments();

		ecms.uploadFile(dFile);
		//cTemp.createNewFolder(aFolder, folderType.None);
		actBar.goToAddNewFolder();
		type(ecms.ELEMENT_FOLDER_TITLE_TEXTBOX, aFolder, true);
		click(cTemp.ELEMENT_CREATE_FOLDER_BUTTON);
		

		actBar.sortBy("Date");
		waitForAndGetElement(ecms.ELEMENT_NODE_NAME_CONSECUTIVE.replace("${node1}", document).replace("${node2}", aFolder));

		actBar.sortBy("Date");
		waitForAndGetElement(ecms.ELEMENT_NODE_NAME_CONSECUTIVE.replace("${node1}", aFolder).replace("${node2}", document));

		//reset data
		actBar.actionsOnElement(document + ".doc" + "/" + aFolder, actionType.DELETE, true);
	}

	/**
	 * Qmetry ID: 119504 & 119507
	 * Sort & Revert the default sort of documents by "Name"
	 */
	@Test
	public void test03_04_RevertTheDefaultSortOfDocumentsByName(){
		String aFolder = "adocuments";
		String bFolder = "bdocuments";

		info("-- Go to Personal Documents --");
		navToolBar.goToPersonalDocuments();

		/*cTemp.createNewFolder(aFolder, folderType.None);
		cTemp.createNewFolder(bFolder, folderType.None);*/
		
		actBar.goToAddNewFolder();
		type(ecms.ELEMENT_FOLDER_TITLE_TEXTBOX, aFolder, true);
		click(cTemp.ELEMENT_CREATE_FOLDER_BUTTON);
		actBar.goToAddNewFolder();
		type(ecms.ELEMENT_FOLDER_TITLE_TEXTBOX, bFolder, true);
		click(cTemp.ELEMENT_CREATE_FOLDER_BUTTON);

		info("-- Select & Revert [Sort By] Name --");
		actBar.sortBy("Name");
		waitForAndGetElement(ecms.ELEMENT_NODE_NAME_CONSECUTIVE.replace("${node1}", aFolder).replace("${node2}", bFolder));
		actBar.sortBy("Name");
		waitForAndGetElement(ecms.ELEMENT_NODE_NAME_CONSECUTIVE.replace("${node1}", bFolder).replace("${node2}", aFolder));

		//reset data
		actBar.actionsOnElement(bFolder + "/" + aFolder, actionType.DELETE, true);
	}

	/**
	 * Qmetry ID: 119503 & 119508
	 * Sort & Revert the default sort of documents by "Size"
	 */
	@Test
	public void test05_06_RevertTheDefaultSortOfDocumentsBySize(){
		String dFile = "TestData/ECMS_DMS_SE_Upload_docfile.doc";
		String document = "ECMS_DMS_SE_Upload_docfile";
		String iFile = "TestData/ECMS_DMS_SE_Upload_imgfile.jpg";
		String image = "ECMS_DMS_SE_Upload_imgfile";

		info("-- Go to Personal Documents --");
		navToolBar.goToPersonalDocuments();

		ecms.uploadFile(dFile);
		ecms.uploadFile(iFile);

		info("-- Select & Revert [Sort By] Size --");
		actBar.sortBy("Size");
		waitForAndGetElement(ecms.ELEMENT_NODE_NAME_CONSECUTIVE.replace("${node1}", document).replace("${node2}", image));
		actBar.sortBy("Size");
		waitForAndGetElement(ecms.ELEMENT_NODE_NAME_CONSECUTIVE.replace("${node1}", image).replace("${node2}", document));

		//reset data
		actBar.actionsOnElement(document + ".doc" + "/" + image + ".jpg" + "/", actionType.DELETE, true);
	}

	/**
	 * Qmetry ID: 119529 & 119534
	 * Show the DOWN/UP arrow for Descending ordering/ Ascending ordering"
	 */
	@Test
	public void test07_08_ShowTheDownUpArrowForDescendingAscendingOrdering(){
		info("-- Go to Personal Documents --");
		navToolBar.goToPersonalDocuments();
		actBar.sortBy("Size");
		click(actBar.ELEMENT_SORT_BY_BUTTON);
		waitForAndGetElement(actBar.ELEMENT_SORT_UP_ARROW);
		click(actBar.ELEMENT_SORT_BY_BUTTON);
		actBar.sortBy("Size");
		click(actBar.ELEMENT_SORT_BY_BUTTON);
		waitForAndGetElement(actBar.ELEMENT_SORT_DOWN_ARROW);
	}
	
	/**
	 * Qmetry ID: 119516
	 * Show "Sort By" list"
	 */
	@Test
	public void test09_SortBylist(){
		info("-- Go to Personal Documents --");
		navToolBar.goToPersonalDocuments();
		click(actBar.ELEMENT_SORT_BY_BUTTON);
		waitForTextPresent("Date");
		waitForTextPresent("Name");
		waitForTextPresent("Size");
		click(actBar.ELEMENT_SORT_BY_BUTTON);
	}
	

}
