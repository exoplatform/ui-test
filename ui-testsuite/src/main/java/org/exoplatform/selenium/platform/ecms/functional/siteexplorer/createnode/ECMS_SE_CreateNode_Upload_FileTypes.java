package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * July, 31th, 2013
 * updated by anhpp
 */
public class ECMS_SE_CreateNode_Upload_FileTypes extends PlatformBase{
	//Platform
	ManageAccount magAcc;
	NavigationToolbar navToolBar;

	//Ecms
	EcmsBase ecms;
	ContextMenu cMenu;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver);
		navToolBar = new NavigationToolbar(driver);
		ecms = new EcmsBase(driver);
		cMenu = new ContextMenu(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethod() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Qmetry ID: 119731 <Upload .exe file>
	 * Qmetry ID: 119732 <Upload .html file>
	 * Qmetry ID: 119733 <Upload .PDF file>
	 * Qmetry ID: 119734 <Upload .xml file>
	 * Qmetry ID: 119735 <Upload image file> 
	 * Qmetry ID: 119736 <Upload Microsoft office file>
	 * Qmetry ID: 119737 <Upload Open office file>
	 * 
	 */
	@Test
	public void test01_uploadFileTypes(){
		String FILE_EXE_NAME = "ECMS_DMS_SE_Upload_exefile.exe";		
		String FILE_PDF_NAME = "ECMS_DMS_SE_Upload_pdffile.pdf";
		String FILE_XML_NAME = "ECMS_DMS_SE_Upload_xmlfile.xml";
		String FILE_HTML_NAME = "ECMS_DMS_SE_Upload_htmlfile.html";
		String FILE_IMAGE_NAME = "ECMS_DMS_SE_Upload_imgfile.jpg";
		String FILE_MOFFICE_NAME = "ECMS_DMS_SE_Upload_docfile.doc";
		String FILE_OPOFFICE_NAME = "ECMS_DMS_SE_Upload_odsfile.ods";
		String[] setFile = {FILE_EXE_NAME, FILE_HTML_NAME, FILE_PDF_NAME, FILE_XML_NAME, FILE_IMAGE_NAME, FILE_MOFFICE_NAME, FILE_OPOFFICE_NAME};
		String data = "TestData/";		
		
		info("Go to Content Explorer");
		navToolBar.goToSiteExplorer();
		
		info("Select a folder [intranet/documents]");
		ecms.goToNode("intranet/documents");
		for (int i=0; i < setFile.length; i++){
			info("Upload file: " + setFile[i]);
			ecms.uploadFile(data + setFile[i]);
			waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", setFile[i]));
		}
		
		info("Restore data");
		for (int i=0; i < setFile.length; i++){
			info("Delete File: " + setFile[i]);
			cMenu.deleteDocument(By.linkText(setFile[i]));	
		}
	}
}
