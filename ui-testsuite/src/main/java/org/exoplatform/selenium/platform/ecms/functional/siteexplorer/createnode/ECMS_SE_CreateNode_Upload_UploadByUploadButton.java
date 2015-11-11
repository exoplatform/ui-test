package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate.folderType;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu.actionType;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * updated by anhpp
 */
public class ECMS_SE_CreateNode_Upload_UploadByUploadButton extends PlatformBase {
	//Platform
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	//Ecms
	EcmsBase ecms;
	ContextMenu cMenu;
	ActionBar action;
	ContentTemplate contentTemp;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver);
		navToolBar = new NavigationToolbar(driver);
		ecms = new EcmsBase(driver);
		cMenu = new ContextMenu(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		action = new ActionBar(driver);
		contentTemp = new ContentTemplate(driver, plfVersion);
	}

	@AfterMethod
	public void afterMethod() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/*QMetry ID:119766
	 * Title: Upload a file from the [Upload] button in Document
	 * */
	@Test
	public void Test01_UploadFileFromTheUploadButtonInDocument(){
		String FileName = "ECMS_DMS_SE_Upload_docfile.doc";
		String title = "ECMS_DMS_SE_Upload_docfile";
		String data = "TestData/";
		
		info("Go to Personal Documents");
		navToolBar.goToPersonalDocuments();
		action.goToViewMode("List");
		click(ELEMENT_UPLOAD_LINK_XPATH);
		ecms.uploadFile(data + FileName);
		
		info("Wait the element of upload zone at the bottom");
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_INFORMATION_ICON);
		waitForAndGetElement(ecms.ELEMENT_MESSAGE_FILE_UPLOADED);
		
		info("Wait the element of new file");
		waitForAndGetElement(ecms.ELEMENT_PERSONAL_DOCUMENT_NODE.replace("${content}",title));
		
		info("Delete File: " + title);
		action.actionsOnElement(title, actionType.DELETE);
	}
	
	/* QmetryID: 119790
	 * Upload a file from the [Upload] button in Site Explorer 
	 * */
	@Test
	public void Test02_UploadFileFromTheUploadButtonInSiteExplorer(){
		String FileName = "ECMS_DMS_SE_Upload_docfile.doc";
		String data = "TestData/";
		
		info("Go to site explorer");
		navToolBar.goToSiteExplorer();
		ecms.goToUpload();
		ecms.uploadFile(data + FileName);
		
		info("Wait the element of upload zone at the bottom");
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_INFORMATION_ICON);
		waitForAndGetElement(ecms.ELEMENT_MESSAGE_FILE_UPLOADED);
		
		info("Wait the element of new file");
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", FileName));
		
		info("Delete File: " + FileName);
		cMenu.deleteDocument(By.linkText(FileName));
		
	}
	/*QmetryID: 102283
	 * Upload multiple files from the [Upload] button in Site Explorer
	 * Note: Cannot verify the expected behavior "Upload start in parallel for the first 3 files then continue remaining files"
	 * --> Can not automated
	 * */
	@Test(groups="pending")
	public void Test03_UploadMultipleFilesFromTheUploadButtonInSiteExplorer(){
		
	}
	
	/*QmetryID: 102311
	 * Upload multiple file from the [Upload] button in Document
	 * Note: Cannot verify the expected behavior "Upload start in parallel for the first 3 files then continue remaining files"
	 * --> Can not automated
	 * */
	@Test (groups="pending")
	public void Test04_UploadMultipleFileFromTheUploadButtonInDocument(){
	}
	
	/*QmetryID: 119824
	 * Upload a file from the [Upload] button in a document in Intranet/Document
	 * 
	 * */
	@Test
	public void Test05_UploadAFileFromTheUploadButtonInADocumentInIntranetDocument(){
		String Web_Content_Name = "webcontent102312";
		String Web_Content_Cont = "contentWebcontent102312";
		String FileName = "ECMS_DMS_SE_Upload_imgfile.jpg";
		String title = "ECMS_DMS_SE_Upload_imgfile";
		String data = "TestData/";
		
		info("Go to Personal Documents");
		navToolBar.goToPersonalDocuments();
		
		info("Add [Add Document] action in this view");
		action.addItem2ActionBar("addDocument", action.ELEMENT_NEW_CONTENT_LINK, "Admin", "Admin");
		
		info("Create a webcontent");
		navToolBar.goToPersonalDocuments();
		action.goToAddNewContent();
		contentTemp.createNewWebContent(Web_Content_Name, Web_Content_Cont, "", "", "", "");
		
		info("Upload file in webcontent folder");
		action.goToViewMode("List");
		ecms.goToUpload();
		ecms.uploadFile(data + FileName);
		waitForAndGetElement(ecms.ELEMENT_MESSAGE_FILE_UPLOADED);
		
		info("Go to Personal folder and check the new uploaded file");
		action.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		click(ecms.ELEMENT_ARROW_RIGHT.replace("${nodeName}", Web_Content_Name));
		click(ecms.ELEMENT_ARROW_RIGHT.replace("${nodeName}", Web_Content_Name));
		waitForAndGetElement(ecms.ELEMENT_PERSONAL_DOCUMENT_NODE.replace("${content}",title));
		
		info("Delete File: " + Web_Content_Name);
		action.actionsOnElement(Web_Content_Name, actionType.DELETE);
	}
	/*QmetryID: 119825
	 * Upload a file from the [Upload] button in a document in Site Explorer
	 * 
	 * */
	@Test
	public void Test06_UploadAFileFromTheUploadButtonInADocumentInSiteExplorer(){
		String Illustrated_Web_Content_Title = "IllustratedWebContent_Case_102312";
		String Illustrated_Web_Content_Cont = "Content of IllustratedWebContent_Case_102312";
		String FileName = "ECMS_DMS_SE_Upload_imgfile.jpg";
		String data = "TestData/";
		
		info("Go to Site exoplorer");
		navToolBar.goToSiteExplorer();
		
		info("Create a IllustratedWebContent");
		action.goToAddNewContent();
		contentTemp.createNewIllustratedWebContent(Illustrated_Web_Content_Title, Illustrated_Web_Content_Cont, "", "", "", "", "", "");
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", Illustrated_Web_Content_Title));
		
		info("Upload file in Illustrated web content folder");
		ecms.goToUpload();
		ecms.uploadFile(data + FileName);
		waitForAndGetElement(ecms.ELEMENT_MESSAGE_FILE_UPLOADED);
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", FileName));
		
		info("Delete File: " + Illustrated_Web_Content_Title);
		cMenu.deleteDocument(By.linkText(Illustrated_Web_Content_Title));
	}
	
	/*QmetryID: 119826
	 * Upload a file from the [Upload] button in a content folder in Sites Explorer
	 * */
	@Test
	public void Test07_UploadFileFromTheUploadButtonInContentFolderInSiteExplorer(){
		String FolderName ="Folder_Case_102314";
		String FileName = "ECMS_DMS_SE_Upload_docfile1.docx";
		
		info("Go to Site exoplorer");
		navToolBar.goToSiteExplorer();
		info("create new content folder");
		contentTemp.createNewFolder(FolderName,folderType.Content);
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", FolderName));
		
		info("Upload file in content folder");
		click(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", FolderName));
		ecms.goToUpload();
		ecms.uploadFile("TestData/" + FileName);
		
		info("Wait the element of upload zone at the bottom");
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_INFORMATION_ICON);
		waitForAndGetElement(ecms.ELEMENT_MESSAGE_FILE_UPLOADED);
		
		info("Wait the uploaded file");
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", FileName));
		
		info("Delete File: " + FolderName);
		cMenu.deleteDocument(By.linkText(FolderName));
	}
	
	/*QmetryID: 119827
	 * Upload a file from the [Upload] button in a folder in Document
	 * */
	@Test
	public void Test08_UploadFileFromTheUploadButtonInFolderInDocument(){
		String FolderName ="Folder_Case_102315";
		String FileName = "ECMS_DMS_SE_Upload_docfile1.docx";
		String FileTitle = "ECMS_DMS_SE_Upload_docfile1";
		
		info("Go to Personal Folder");
		navToolBar.goToPersonalDocuments();
		
		info("create new content folder");
		contentTemp.createNewFolder(FolderName, folderType.None);
		waitForAndGetElement(ecms.ELEMENT_PERSONAL_DOCUMENT_NODE.replace("${content}",FolderName));
		action.goToViewMode("List");
		
		info("Upload file in content folder");
		click(ecms.ELEMENT_PERSONAL_DOCUMENT_NODE.replace("${content}", FolderName));
		click(ELEMENT_UPLOAD_LINK_XPATH);
		ecms.uploadFile("TestData/" + FileName);
		
		info("Wait the element of upload zone at the bottom");
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_INFORMATION_ICON);
		waitForAndGetElement(ecms.ELEMENT_MESSAGE_FILE_UPLOADED);

		info("Go to Personal folder and check the new uploaded file");
		action.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		click(ecms.ELEMENT_ARROW_RIGHT.replace("${nodeName}", FolderName));
		click(ecms.ELEMENT_ARROW_RIGHT.replace("${nodeName}", FolderName));
		waitForAndGetElement(ecms.ELEMENT_PERSONAL_DOCUMENT_NODE.replace("${content}",FileTitle));
		
		
		info("Delete File: " + FolderName);
		action.actionsOnElement(FolderName, actionType.DELETE);
	}
	
	/*QmetryID: 119828
	 * Upload a file from the [Upload] button in a document folder in Sites Explorer
	 * */
	@Test
	public void Test09_UploadFileFromUploadButtonInDocumentFolderInSiteExplorer(){
		String DocFolderName = "Document_Folder_102316";
		String FileName = "test05_AccessibleNavigation.xml";
		
		info("Go to Site exoplorer");
		navToolBar.goToSiteExplorer();
		info("Create a Document Folder in Site explorer");
		contentTemp.createNewFolder(DocFolderName,folderType.Document);
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", DocFolderName));
		
		info("Upload file in content folder");
		click(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", DocFolderName));
		ecms.goToUpload();
		ecms.uploadFile("TestData/" + FileName);
		
		info("Wait the element of upload zone at the bottom");
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_CLOSE_TAB);
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_INFORMATION_ICON);
		waitForAndGetElement(ecms.ELEMENT_MESSAGE_FILE_UPLOADED);
		
		
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", FileName));
		
		info("Delete File: " + DocFolderName);
		cMenu.deleteDocument(By.linkText(DocFolderName));
	}
	
	/* Qmetry:102317 
	 * Upload a file from the [Upload] button in a document folder in Document
	 * This case is not applicable. No Folder Type in Personal Document
	 * 	 * --> Can not automated
	 * */
	@Test (groups="pending")
	public void Test10_UploadFileFromTheUploadButtonInDocumentFolderInDocument(){
	
	}
	
	
	
}
