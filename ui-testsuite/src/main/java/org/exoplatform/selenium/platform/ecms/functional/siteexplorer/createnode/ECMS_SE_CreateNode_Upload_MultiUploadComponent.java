package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu.actionType;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** 
 * By HaTV
 * Date: December, 19th, 2014
 */

public class ECMS_SE_CreateNode_Upload_MultiUploadComponent extends PlatformBase{
	
	//Platform
		Button button;
		ManageAccount magAcc;
		NavigationToolbar navToolBar;
		ActionBar actBar;
		ManageAlert magAlt;
		SettingSearchPage qsPage;

		//Ecms
		EcmsBase ecms;
		ContextMenu cMenu;
		ContentTemplate cTemplate;
		public String ELEMENT_NODE_AT_ROOT = "//*[@id='iconTreeExplorer_2fsites_2f${nodeName}']/a/span";

		@BeforeMethod
		public void beforeMethod(){
			initSeleniumTest();
			driver.get(baseUrl);
			button = new Button(driver, this.plfVersion);
			magAcc = new ManageAccount(driver, this.plfVersion);
			navToolBar = new NavigationToolbar(driver);
			actBar = new ActionBar(driver, this.plfVersion);
			ecms = new EcmsBase(driver, this.plfVersion);
			qsPage = new SettingSearchPage(driver);
			cMenu = new ContextMenu(driver);
			cTemplate = new ContentTemplate(driver, this.plfVersion);
			magAlt = new ManageAlert(driver);
			magAcc.signIn(DATA_USER1, DATA_PASS);
			
		}

		@AfterMethod
		public void afterMethod() {
			info("-- User signOut --");
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		
		/**
		 * Test case ID: 102231
		 * Display specific zone in root folder and sub folders when upload in Document
		 */
		@Test (groups = "pending")
		public void test01_DisplaySpecificZoneInRootAndSubFolders(){
			// This case is pending
		}
		
		/**
		 * Test case ID: 102232
		 * Display specific zone only in root folder when upload in Document
		 */
		@Test (groups = "pending")
		public void test02_DisplaySpecificZoneOnlyInRootFolder(){
			// This case is pending
		}
		
		/**
		 * Test case ID: 102233
		 * Display specific zone only in sub folders when upload in Document
		 */
		@Test (groups = "pending")
		public void test03_DisplaySpecificZoneOnlyInSubFolder(){
			// This case is pending
		}
		
		/**
		 * Test case ID: 102234
		 * Specific zone isn't displayed when upload in Site Explorer
		 */
		@Test (groups = "pending")
		public void test04_DoNotDisplaySpecificZoneInSiteExplorer(){
			// This case is pending
		}
		
		/**
		 * By: HaTV
		 * Test case ID: 102236
		 * Display the tooltip in the top of the upload component in Document
		 */
		@Test
		public void test05_DisplayTooltip(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_docfile.doc";
			String data = "TestData/";
			String actualTooltip;
			String expectedTooltip1 = "Maximum file size is 200 Mb.";
			String expectedTooltip2 ="Invalid characters will be changed by '-'";
			
			info("Display the tooltip in the top of the upload component in Document !");
			
			navToolBar.goToPersonalDocuments();
			
			//Upload an Uploaded File - source
			ecms.uploadFile(data + FILE_NAME);
			mouseOver(ecms.ELEMENT_MULTI_UPLOAD_HELP_ICON,true);
			Utils.pause(10000);
			
			info("Wait the Help element of upload zone at the bottom");
			actualTooltip=waitForAndGetElement(ecms.ELEMENT_MULTI_UPLOAD_HELP_ICON).getAttribute("data-original-title");	
			info(actualTooltip);
			info(expectedTooltip1);
			info(expectedTooltip2);
			assert actualTooltip.contains(expectedTooltip1);
			assert actualTooltip.contains(expectedTooltip2);
			
			//Delete data
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			actBar.actionsOnElement(FILE_NAME, actionType.DELETE);
		}
		
		/**
		 * By: HaTV
		 * Test case ID: 102240
		 * Display the scroll bar in the specific zone when upload in Document
		 */
		@Test
		public void test06_DisplayScrollBar(){
			String FILE_EXE_NAME = "ECMS_DMS_SE_Upload_odsfile.ods";		
			String FILE_HTML_NAME = "ECMS_DMS_SE_Upload_htmlfile.html";
			String FILE_PDF_NAME = "ECMS_DMS_SE_Upload_pdffile.pdf";
			String FILE_IMAGE_NAME = "ECMS_DMS_SE_Upload_imgfile.jpg";
			
			String FILE_EXE_TITLE = "ECMS_DMS_SE_Upload_odsfile";
			String FILE_HTML_TITLE = "ECMS_DMS_SE_Upload_htmlfile";
			String FILE_PDF_TITLE = "ECMS_DMS_SE_Upload_pdffile";
			String FILE_IMAGE_TITLE = "ECMS_DMS_SE_Upload_imgfile";
			
			String[] setFile1 = {FILE_EXE_NAME, FILE_HTML_NAME, FILE_PDF_NAME, FILE_IMAGE_NAME};
			String[] setFile_TITLE_1 = {FILE_EXE_TITLE, FILE_HTML_TITLE, FILE_PDF_TITLE, FILE_IMAGE_TITLE};
			
			String FILE_XML_NAME = "ECMS_DMS_SE_Upload_xmlfile.xml";
			String FILE_XML_TITLE = "ECMS_DMS_SE_Upload_xmlfile";
			String data = "TestData/";	
			
			info("Display the scroll bar in the specific zone when upload in Document !");
			
			navToolBar.goToPersonalDocuments();
			actBar.goToViewMode("Admin");
			for (int j=0; j < setFile_TITLE_1.length; j++){
				info("Upload file: " + setFile1[j]);
				ecms.uploadFile(data + setFile1[j]);
				info("Wait for file: " + setFile_TITLE_1[j]);
				waitForAndGetElement(ecms.ELEMENT_PERSONAL_DOCUMENT_NODE.replace("${content}", setFile_TITLE_1[j]));
			    boolean isScrollBar = checkExitScrollBar(ecms.ELEMENT_MULTI_UPLOAD_LIST_PROGRESS);
				if (isScrollBar==true)
					assert false:"The scroll bar is shown";
			}	
			
			info("Upload file: " + FILE_XML_NAME);
			ecms.uploadFile(data + FILE_XML_NAME);
			info("Wait for file: " + FILE_XML_TITLE);
			waitForAndGetElement(ecms.ELEMENT_PERSONAL_DOCUMENT_NODE.replace("${content}", FILE_XML_TITLE));
			info ("Check the existing of scroll bar"+checkExitScrollBar(ecms.ELEMENT_MULTI_UPLOAD_LIST_PROGRESS));
			 boolean isScrollBar = checkExitScrollBar(ecms.ELEMENT_MULTI_UPLOAD_LIST_PROGRESS);
				if (isScrollBar==false)
					assert false:"The scroll bar is not shown";
				
			//Delete data
			info("Restore data");
			for (int j=0; j < setFile1.length; j++){
				 info("Delete File: " + setFile1[j]);
				 actBar.actionsOnElement(setFile1[j], actionType.DELETE);
			}
			
			actBar.actionsOnElement(FILE_XML_NAME, actionType.DELETE);	
		}
		
		/**
		 * Test case ID: 102242
		 * Message when uploaded files are in progress in Document
		 * ====================== PENDING =======================
		 * ===== Do not upload a large file to git repository === 
		 * If upload file with small size, the message Upload file inprogress is displayed very quickly => This case is pending 
		 */
		@Test(groups="pending")
		public void test07_DisplayMessageWhenUploadFileInprogressInDocument(){
			
		}
		
		/**
		 * By HaTV
		 * Test case ID: 102243
		 * Message when uploaded file are in awaiting queue in Document
		 * The purpose of this case is to check the displayed of message inform that uploaded file is in awaiting queue
		 * To automate this case, I have already written script not follow the steps that described in test case 102274
		 */
		@Test
		public void test08_DisplayMessageUploadedFileInAwaitingQueueInDocument(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_htmlfile.html";
			String FILE_TITLE = "ECMS_DMS_SE_Upload_htmlfile";
			
			String data = "TestData/"; 
			
			info("Check the display of message when uploaded file are in awaiting queue in Document !");
			
			info("Go to Document application");
			navToolBar.goToPersonalDocuments();
			
			info("Create a data test");
			ecms.uploadFile(data + FILE_NAME);
			ecms.uploadFile(data + FILE_NAME);
			
			info("Check if the message when uploaded file are in awaiting queue is displayed !");
			waitForAndGetElement(ecms.ELEMENT_AWAITING_FILE);
			mouseOver(ecms.ELEMENT_AWAITING_FILE,true);	
			Utils.pause(5000);
			
			//Delete data
			info("Restore data");
			info("Delete File: " + FILE_TITLE);
			actBar.actionsOnElement(FILE_TITLE, actionType.DELETE);	
		}
		
		/**
		 * By HaTV
		 * Test case ID: 102244
		 * Message when files are finished to upload in Document
		 */
		@Test
		public void test09_DisplayMessageFinishUploadFileInDocument(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_htmlfile.html";
			String FILE_TITLE = "ECMS_DMS_SE_Upload_htmlfile";
			String data = "TestData/"; 
			
			info("Check the display of the message when files are finished to upload in Document !");
			
			info("Go to Document application");
			navToolBar.goToPersonalDocuments();
			
			info("Create a data test");
			ecms.uploadFile(data + FILE_NAME);
			
			info("Check if the message when finishing to upload file is displayed !");
			waitForAndGetElement(ecms.ELEMENT_MESSAGE_FILE_UPLOADED);
			mouseOver(ecms.ELEMENT_MESSAGE_FILE_UPLOADED,true);	
			Utils.pause(5000);
			
			//Delete data
			info("Restore data");
			info("Delete File: " + FILE_TITLE);
			actBar.actionsOnElement(FILE_TITLE, actionType.DELETE);
		}
		
		/**
		 * By HaTV
		 * Test case ID: 102245
		 * Message when file has been canceled upload in Document
		 */
		@Test(groups="pending")
		public void test10_DisplayMessageCancelUploadFileInDocument(){
			
		}
		
		/**
		 * By HaTV
		 * Test case ID: 102246
		 * Messages with many status of uploaded files in Document
		 */
		@Test(groups="pending")
		public void test11_DisplayMessageWithManyStatusOfUploadedFileInDocument(){
			
			
		}
		
		/**
		 * By HaTV
		 * Test case ID: 102250
		 * Display  tool tip of the path of the file when upload in Document
		 */
		@Test
		public void test12_DisplayToolTipOfPathFile(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_docfile.doc";
			String data = "TestData/";
			String expectedTooltip = "in Personal Documents/";
			
			info("Display  tool tip of the path of the file when upload in Document !");
			
			navToolBar.goToPersonalDocuments();
			
			//Upload an Uploaded File - source
			ecms.uploadFile(data + FILE_NAME);	
			mouseOver(ecms.ELEMENT_IN_PERSON_DOCUMENT,true);	
			Utils.pause(10000);
			
			info("Wait the file element of upload zone at the bottom");
			waitForAndGetElement(ecms.ELEMENT_NAME_CONTENT_PERSON_DOCUMENT.replace("${name}", expectedTooltip)).getText();	
			
			//Delete data
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			actBar.actionsOnElement(FILE_NAME, actionType.DELETE);
		}
		
		/**
		 * By HaTV
		 * Date 29 - Dec -2014
		 * Test case ID: 102251
		 * Close the upload component in Document
		 */
		@Test
		public void test13_CloseUploadComponenInDocument(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_docfile.doc";
			String data = "TestData/";
			
			info("Close the upload component in Document !");
			
			navToolBar.goToPersonalDocuments();
			
			//Upload an Uploaded File - source
			ecms.uploadFile(data + FILE_NAME);
			Utils.pause(10000);
			
			info("Wait the Close element of upload zone at the bottom");
			waitForAndGetElement(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
			click(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
			
			waitForElementNotPresent(ecms.ELEMENT_BOX_UPLOAD);
			
			//Delete data
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			actBar.actionsOnElement(FILE_NAME, actionType.DELETE);
		}
		
		/**
		 * By HaTV
		 * Date 29 - Dec -2014
		 * Test case ID: 102253
		 * An existing file is displayed at the top of the list when upload in Document
		 */
		@Test
		public void test14_CheckMessageInformExistingFileInDocument(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_docfile.doc";
			String data = "TestData/"; 
			
			info("An existing file is displayed at the top of the list when upload in Document !");
			
			navToolBar.goToPersonalDocuments();
			
			//Upload an Uploaded File - source
			ecms.uploadFile(data + FILE_NAME);
			Utils.pause(5000);
			ecms.uploadFile(data + FILE_NAME);
			Utils.pause(5000);
			
			info("Check if The message is:  Already in use!  Keep both, Replace or Cancel is displayed");
			waitForAndGetElement(ecms.ELEMENT_MESSAGE_IN_BOX_UPLOAD);
			mouseOver(ecms.ELEMENT_MESSAGE_IN_BOX_UPLOAD,true);	
			Utils.pause(10000);
			
			info("Check if the file jump at the top of the list");
			waitForAndGetElement(ecms.ELEMENT_FILE_TOP_MESSAGE.replace("${fileName}", FILE_NAME));	
			
			//Delete data
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			actBar.actionsOnElement(FILE_NAME, actionType.DELETE);
		}
		
		/**
		 * by HaTV
		 * Date 30 - Dec -2014
		 * Test case ID: 102254
		 * Display a file exceeds the size limit at the top of the list when upload in Document
		 * ====================== PENDING =======================
		 * ===== Do not upload a large file to git repository ===  
		 *
		 */
		@Test (groups = "pending")
		public void test15_CheckMessageInformFileExceedsSizeLimit(){
			String FILE_NAME = "file6.pdf";
			String data = "TestData/"; 
			
			info("Display a file exceeds the size limit at the top of the list when upload in Document !");
			
			navToolBar.goToPersonalDocuments();
			
			//Upload an Uploaded File - source
			ecms.uploadFile(data + FILE_NAME);
			Utils.pause(5000);
			

			info("Check if The message is:  The file size exceeds the size limit of  200Mb! ");
			waitForAndGetElement(ecms.ELEMENT_MESSAGE_UPLOAD_BOX_OVER_200MB);
			mouseOver(ecms.ELEMENT_MESSAGE_UPLOAD_BOX_OVER_200MB,true);	
			Utils.pause(5000);
			
			info("Check if the file jump at the top of the list");
			waitForAndGetElement(ecms.ELEMENT_FILE_TOP_MESSAGE.replace("${fileName}", FILE_NAME));	
			
			//Close upload zone
			info("Wait the Close element of upload zone at the bottom");
			waitForAndGetElement(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
			click(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
		}
		
		
		/**
		 * Test case ID: 102261
		 * Display specific zone in root folder and sub folders when upload in Site Explorer
		 */
		@Test (groups = "pending")
		public void test16_DisplaySpecificZoneInRootAndSubFoldersInSiteExplorer(){
			// This case is pending
		}
		
		/**
		 * Test case ID: 102262
		 * Display specific zone in root folder and sub folders when upload in Site Explorer
		 */
		@Test (groups = "pending")
		public void test17_DisplaySpecificZoneOnlyInRootFolderInSiteExplorer(){
			// This case is pending
		}
		
		/**
		 * Test case ID: 102263
		 * Display specific zone in root folder and sub folders when upload in Site Explorer
		 */
		@Test (groups = "pending")
		public void test18_DisplaySpecificZoneOnlyInSubFolderInSiteExplorer(){
			// This case is pending
		}
		
		/**
		 * Test case ID: 102264
		 * Display specific zone in root folder and sub folders when upload in Site Explorer
		 */
		@Test (groups = "pending")
		public void test19_NotDisplaySpecificZoneWhenUploadInDocument(){
			// This case is pending
		}
		
		/**
		 * Test case ID: 102266
		 * Display the tool tip in the top of the upload component in Site explorer
		 */
		@Test
		public void test20_DisplayTooltipInUloadZoneInSiteExplorer(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_docfile.doc";
			String data = "TestData/"; 
			String actualTooltip;
			String expectedTooltip1 = "Maximum file size is 200 Mb.";
			String expectedTooltip2 ="Invalid characters will be changed by '-'";
			
			info("Display the tool tip in the top of the upload component in Site explorer !");
			
			info("Go to Site Explorer");
			navToolBar.goToSiteExplorer();
			
			info("Create a data test");
			ecms.uploadFile(data + FILE_NAME);
			
			mouseOver(ecms.ELEMENT_MULTI_UPLOAD_HELP_ICON,true);
			Utils.pause(10000);
			
			info("Wait the Help element of upload zone at the bottom");
			actualTooltip=waitForAndGetElement(ecms.ELEMENT_MULTI_UPLOAD_HELP_ICON).getAttribute("data-original-title");	
			info(actualTooltip);
			info(expectedTooltip1);
			info(expectedTooltip2);
			assert actualTooltip.contains(expectedTooltip1);
			assert actualTooltip.contains(expectedTooltip2);
			
			//Delete data
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			cMenu.deleteDocument(By.linkText(FILE_NAME));	
		}
		
		/**
		 * by HaTV
		 * Test case ID: 102270
		 * Display the scroll bar in the specific zone when upload in Site Explorer
		 */
		@Test
		public void test21_DisplayScrollBarInUloadZoneInSiteExplorer(){
			String FILE_HTML_NAME = "ECMS_DMS_SE_Upload_htmlfile.html";
			String FILE_PDF_NAME = "ECMS_DMS_SE_Upload_pdffile.pdf";
			String FILE_XML_NAME = "ECMS_DMS_SE_Upload_xmlfile.xml";
			String FILE_IMAGE_NAME = "ECMS_DMS_SE_Upload_imgfile.jpg";
			String FILE_DOC_NAME = "ECMS_DMS_SE_Upload_docfile.doc";

			String[] setFile = {FILE_HTML_NAME, FILE_PDF_NAME, FILE_XML_NAME, FILE_IMAGE_NAME};
			String data = "TestData/";		
			
			info("Go to Content Explorer");
			navToolBar.goToSiteExplorer();
			
			info("Select a folder [intranet/documents]");
			ecms.goToNode("intranet/documents");
			for (int i=0; i < setFile.length; i++){
				info("Upload file: " + setFile[i]);
				ecms.uploadFile(data + setFile[i]);
				waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", setFile[i]));
				boolean isScrollBar = checkExitScrollBar(ecms.ELEMENT_MULTI_UPLOAD_LIST_PROGRESS);
				if (isScrollBar==true)
					assert false:"The scroll bar is shown";
			}
			
			info("Upload file: " + FILE_DOC_NAME);
			ecms.uploadFile(data + FILE_DOC_NAME);
			waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", FILE_DOC_NAME));
			info ("Check the existing of scroll bar"+checkExitScrollBar(ecms.ELEMENT_MULTI_UPLOAD_LIST_PROGRESS));
			 boolean isScrollBar = checkExitScrollBar(ecms.ELEMENT_MULTI_UPLOAD_LIST_PROGRESS);
				if (isScrollBar==false)
					assert false:"The scroll bar is not shown";
			
			info("Restore data");
			for (int i=0; i < setFile.length; i++){
				info("Delete File: " + setFile[i]);
				cMenu.deleteDocument(By.linkText(setFile[i]));	
			}
			
			info("Delete File: " + FILE_DOC_NAME);
			cMenu.deleteDocument(By.linkText(FILE_DOC_NAME));
		}
		
		/**
		 * Test case ID: 102273
		 * Message when uploaded files are in progress in Site Explorer
		 * ====================== PENDING =======================
		 * ===== Do not upload a large file to git repository === 
		 * If upload file with small size, the message Upload file inprogress is displayed very quickly => This case is pending 
		 */
		@Test(groups = "pending")
		public void test22_DisplayMessageWhenUploadFileInprogress(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_docfile_7MB.ods";
			String data = "TestData/"; 
						
			info("Check the display of message when uploaded files are in progress in Site Explorer !");
			
			info("Go to Site Explorer");
			navToolBar.goToSiteExplorer();
			
			info("Create a data test");
			ecms.uploadFile(data + FILE_NAME);
			
			
			waitForAndGetElement(ecms.ELEMENT_UPLOADING_FILE);
			Utils.pause(1000);
			mouseOver(ecms.ELEMENT_UPLOADING_FILE,true);
			
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			cMenu.deleteDocument(By.linkText(FILE_NAME));
		}
		
		/**
		 * by HaTV
		 * Test case ID: 102274
		 * Message when uploaded file are in awaiting queue in Site Explorer
		 * The purpose of this case is to check the displayed of message inform that uploaded file is in awaiting queue
		 * To automate this case, I have already written script not follow the steps that described in test case 102274
		 */
		@Test
		public void test23_DisplayMessageAwaitingQueueInSiteExplorer(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_htmlfile.html";
			String data = "TestData/"; 
			
			info("Check the display of message when uploaded file are in awaiting queue in Site Explorer !");
			
			info("Go to Site Explorer");
			navToolBar.goToSiteExplorer();
			
			info("Create a data test");
			ecms.uploadFile(data + FILE_NAME);
			ecms.uploadFile(data + FILE_NAME);
			
			info("Check if the message when uploaded file are in awaiting queue is displayed !");
			waitForAndGetElement(ecms.ELEMENT_AWAITING_FILE);
			mouseOver(ecms.ELEMENT_AWAITING_FILE,true);	
			Utils.pause(5000);
			
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			cMenu.deleteDocument(By.linkText(FILE_NAME));
		}
		

		/**
		 * by HaTV
		 * Test case ID: 102275
		 * Message when files are finished to upload in Site Explorer
		 */
		@Test
		public void test24_DisplayMessageFinishUploadInSiteExplorer(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_htmlfile.html";
			String data = "TestData/"; 
			
			info("Check the display of the message when files are finished to upload in Site Explorer !");
			
			info("Go to Site Explorer");
			navToolBar.goToSiteExplorer();
			
			info("Create a data test");
			ecms.uploadFile(data + FILE_NAME);
			
			info("Check if the message when finishing to upload file is displayed !");
			waitForAndGetElement(ecms.ELEMENT_MESSAGE_FILE_UPLOADED);
			mouseOver(ecms.ELEMENT_MESSAGE_FILE_UPLOADED,true);	
			Utils.pause(5000);
			
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			cMenu.deleteDocument(By.linkText(FILE_NAME));
		}
		
		/**
		 * by HaTV
		 * Test case ID: 102276
		 * Message when file has been canceled upload in Site Explorer
		 */
		@Test(groups="pending")
		public void test25_DisplayMessageCancelUploadFileInSiteExplorer(){
			
		}
		
		/**
		 * by HaTV
		 * Test case ID: 102277
		 * Messages with many status of uploaded files in Site Explorer
		 */
		@Test(groups="pending")
		public void test26_DisplayMessageWithManyStatusOfUploadedFileInSiteExplorer(){
			
			
		}
		
		/**
		 * By HaTV
		 * Test case ID: 102281
		 * Display tool tip of the path of the file when upload in Site Explorer
		 */
		@Test
		public void test27_DisplayTooltipOfPathOfUploadedFileInSiteExplorer(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_docfile.doc";
			String data = "TestData/";
			
			String expectedTooltip = "in Sites Management/";
			
			info("Display  tool tip of the path of the file when upload in Site Explorer !");
			
			info("Go to Site Explorer");
			navToolBar.goToSiteExplorer();
						
			//Upload an Uploaded File - source
			ecms.uploadFile(data + FILE_NAME);	
			mouseOver(ecms.ELEMENT_IN_SITE_MANAGEMENT,true);	
			Utils.pause(10000);
			
			info("Wait the file element of upload zone at the bottom");
			waitForAndGetElement(ecms.ELEMENT_NAME_CONTENT_PERSON_DOCUMENT.replace("${name}", expectedTooltip)).getText();	
			
			
			//Delete data
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			cMenu.deleteDocument(By.linkText(FILE_NAME));
		}
		
		/**
		 * by HaTV
		 * Test case ID: 102288
		 * Close the upload component in Site Explorer
		 */
		@Test
		public void test28_CloseUploadComponentInSiteExplorer(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_docfile.doc";
			String data = "TestData/";
			
			info("Close the upload component in Site Explorer !");
			
			info("Go to Site Explorer");
			navToolBar.goToSiteExplorer();
			
			//Upload an Uploaded File - source
			ecms.uploadFile(data + FILE_NAME);
			Utils.pause(10000);
			
			info("Wait the Close element of upload zone at the bottom");
			waitForAndGetElement(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
			click(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
			
			waitForElementNotPresent(ecms. ELEMENT_MUITI_UPLOAD);
			
			//Delete data
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			cMenu.deleteDocument(By.linkText(FILE_NAME));
		}
		/**
		 * By HaTV
		 * Test case ID: 102290
		 * An existing file is displayed at the top of the list when upload in Site Explorer
		 */
		@Test
		public void test29_CheckMessageInformExistingFileInSiteExplorer(){
			String FILE_NAME = "ECMS_DMS_SE_Upload_docfile.doc";
			String data = "TestData/"; 
			
			info("An existing file is displayed at the top of the list when upload in Document !");
			
			info("Go to Site Explorer");
			navToolBar.goToSiteExplorer();
			
			//Upload an Uploaded File - source
			ecms.uploadFile(data + FILE_NAME);
			Utils.pause(5000);
			ecms.uploadFile(data + FILE_NAME);
			Utils.pause(5000);
			
			info("Check if The message is:  Already in use!  Keep both, Replace or Cancel is displayed");
			waitForAndGetElement(ecms.ELEMENT_MESSAGE_IN_BOX_UPLOAD);
			mouseOver(ecms.ELEMENT_MESSAGE_IN_BOX_UPLOAD,true);	
			Utils.pause(10000);
			
			info("Check if the file jump at the top of the list");
			waitForAndGetElement(ecms.ELEMENT_FILE_TOP_MESSAGE.replace("${fileName}", FILE_NAME));	
			
			//Delete data
			info("Restore data");
			info("Delete File: " + FILE_NAME);
			cMenu.deleteDocument(By.linkText(FILE_NAME));
		}
}

