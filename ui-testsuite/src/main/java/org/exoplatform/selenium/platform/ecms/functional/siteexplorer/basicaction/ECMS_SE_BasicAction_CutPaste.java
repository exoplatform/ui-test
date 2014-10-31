package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.basicaction;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.admin.ManageView;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.BrowserPreferences;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate.folderType;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.TestLogger.*;

/**
 * @date: 08/10/2012
 */
public class ECMS_SE_BasicAction_CutPaste extends PlatformBase {
	//Platform
	Button button;
	ManageAlert alt;
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;

	//Ecms
	EcmsBase ecms;
	EcmsPermission ecmsPer;
	ContentTemplate cTemplate;
	ContextMenu cMenu;
	BrowserPreferences bPre;
	ManageView magView;
	SitesExplorer siteExp;

	public String USER = DATA_USER1;
	public String PASS = DATA_PASS;

	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		button = new Button(driver, this.plfVersion);
		alt = new ManageAlert(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		navToolBar = new NavigationToolbar(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		ecms = new EcmsBase(driver, this.plfVersion);
		ecmsPer = new EcmsPermission(driver);
		cTemplate = new ContentTemplate(driver, this.plfVersion);
		cMenu = new ContextMenu(driver, this.plfVersion);
		bPre = new BrowserPreferences(driver);
		magView = new ManageView(driver);
		siteExp = new SitesExplorer(driver, this.plfVersion);
		magAcc.signIn(USER,PASS);
		navToolBar.goToSiteExplorer();
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	/* 
	 * Test_Case_ID:101958
	 * Case 1: Cut a Content folder and paste it into a Content Folder
	 */
	@Test
	public void test01_1_CutContentFolderPasteInContentFolder() {
		String titleCut = "titleCut1019581";
		String titlePaste = "titlePaste1019581";
		By bCont = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut)); 
		By bTarget = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a Content Folder and paste it into a Content Folder!");
		
		//Create a content folder 
		info(titleCut);
		cTemplate.createNewFolder(titlePaste, folderType.Content);
		cTemplate.createNewFolder(titleCut, folderType.Content);
		
		//Cut and paste a Content Folder into a Content Folder
		cMenu.cutAndPasteNode(bCont, bTarget);

		//Verify if paste the Content Folder successfully into Content Folder
		click(siteExp.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		waitForElementNotPresent(bCont);
		ecms.goToNode(titlePaste);
		waitForAndGetElement(bCont);

		//Delete data
		cMenu.deleteDocument(bTarget);
	}
	
	/* 
	 * @date: 30/10/2014
	 * Test_Case_ID:101958
	 * Case 2: Cut a Content folder and paste it into a Web Content document
	 */
	@Test
	public void test01_2_CutContentFolderPasteIntoWebContent() {
		String titleCut = "titleCut1019582";
		String titlePaste = "titlePaste1019582";
		By bCont = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut)); 
		By bTarget = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a Content Folder and paste it into a Web Content!");
		
		//Create a Content Folder 
		info(titleCut);
		cTemplate.createNewFolder(titleCut, folderType.Content);
		
		//Create a Web Content document
		info(titlePaste);
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(titlePaste, titlePaste, "", "", "", "");
		
		//Cut and paste a Content Folder into a Web Content document
		cMenu.cutNode(bCont);
		rightClickOnElement(bTarget);
		cMenu.pasteNode(bTarget);
		
		//Verify if paste the Content Folder successfully into Content Folder
		click(siteExp.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		waitForElementNotPresent(bCont);
		ecms.goToNode(titlePaste);
		waitForAndGetElement(bCont);
		
		//Delete data
		cMenu.deleteDocument(bTarget);
	}
	
	/* 
	 * @date: 30/10/2014
	 * Test_Case_ID:101958
	 * Case 3: Cut a Content folder and paste it into an Announcement
	 */
	@Test
	public void test01_3_CutContentFolderPasteIntoAnnouncement() {
		String titleCut = "titleCut1019583";
		String titlePaste = "titlePaste1019583";
		By bCont = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut)); 
		By bTarget = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a Content Folder and paste it into an Announcement!");
		
		//Create a content folder 
		info(titleCut);
		cTemplate.createNewFolder(titleCut, folderType.Content);
		
		//Create an Announcement document
		info(titlePaste);
		actBar.goToAddNewContent();
		cTemplate.createNewAnnouncement(titlePaste, titlePaste);
		
		//Cut and paste a Content Folder into an Announcement document
		cMenu.cutNode(bCont);
		rightClickOnElement(bTarget);
		cMenu.pasteNode(bTarget);
		
		//Verify if paste the Content Folder successfully into Content Folder
		click(siteExp.ELEMENT_SIDEBAR_SITES_MANAGEMENT);
		waitForElementNotPresent(bCont);
		ecms.goToNode(titlePaste);
		waitForAndGetElement(bCont);
		
		//Delete data
		cMenu.deleteDocument(bTarget);
	}
	
	/*
	 * Test_Case_ID: 101959
	 * Case 1: Cut a Document Folder and paste it into a Document folder
	 */
	@Test
	public void test02_1_CutDocumentFolderPasteInDocumentFolder() {
		String titleCut="titleCut1019591";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		String titlePaste="titlePaste1019591";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a Document Folder and paste it to Document Folder!");
		
		//Create a Document folder - source
		cTemplate.createNewFolder(titleCut, folderType.Document);

		//Create a Document folder -destination
		cTemplate.createNewFolder(titlePaste, folderType.Document);

		//Cut and paste the Document folder into Document Folder
		cMenu.cutAndPasteNode(bDoc, bDocDes);

		//Verify if paste Document Folder successfully in Document Folder 
		ecms.goToNode(bDocDes);
		waitForAndGetElement(bDocDes);

		//Delete data
		cMenu.deleteDocument(bDocDes);
	}

	/*
	 * @date: 30/10/2014
	 * Test_Case_ID: 101959
	 * Case 2: Cut a Document Folder and paste it into a Content Folder
	 */
	@Test
	public void test02_2_CutDocumentFolderPasteIntoContentFolder() {
		String titleCut="titleCut1019592";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		String titlePaste="titlePaste1019592";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a Document Folder and paste it into Content Folder!");
		
		//Create a Document Folder - source
		cTemplate.createNewFolder(titleCut, folderType.Document);

		//Create a Content Folder -destination
		cTemplate.createNewFolder(titlePaste, folderType.Content);

		//Cut and paste a Document Folder into a Content Folder
		cMenu.cutAndPasteNode(bDoc, bDocDes);

		//Verify if paste Document Folder successfully in Content Folder 
		ecms.goToNode(bDocDes);
		waitForAndGetElement(bDocDes);

		//Delete data
		cMenu.deleteDocument(bDocDes);
	}
	
	/*
	 * @date: 30/10/2014
	 * Test_Case_ID: 101959
	 * Case 3: Cut a Document Folder and paste it into a Web Content
	 */
	@Test
	public void test02_3_CutDocumentFolderPasteIntoWebContent() {
		String titleCut="titleCut1019593";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		
		String titlePaste="titlePaste1019593";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a Document Folder and paste it into Web Content!");
		
		//Create a Document Folder - source
		cTemplate.createNewFolder(titleCut, folderType.Document);

		//Create a Web Content document
		info(titlePaste);
		actBar.goToAddNewContent();
		cTemplate.createNewWebContent(titlePaste, titlePaste, "", "", "", "");

		//Cut and paste the Document Folder into Web Content document
		cMenu.cutAndPasteNode(bDoc, bDocDes);

		//Verify if paste Document folder successfully into Web Content document 
		ecms.goToNode(bDocDes);
		waitForAndGetElement(bDocDes);

		//Delete data
		cMenu.deleteDocument(bDocDes);
	}
	
	/*
	 * Test_Case_ID: 101960
	 * Case 1: Cut a Document Folder and paste it into File document!
	 */
	@Test
	public void test03_1_CutDocumentFolderPasteIntoFileDocument() {
		String titleCut="titleCut1019601";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		
		String titlePaste="titlePaste1019601";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a Focument Folder and paste it into File Document!");
		
		//Create a Document Folder - source
		cTemplate.createNewFolder(titleCut, folderType.Document);

		//Create a File Document -destination
		actBar.goToAddNewContent();
		cTemplate.createNewFile(titlePaste, titlePaste, titlePaste);

		//Cut and paste the Document Folder into File Document
		cMenu.cutNode(bDoc);
		rightClickOnElement(bDocDes);
		waitForElementNotPresent(ELEMENT_PASTE_NODE);
			
		//Delete data
		cMenu.deleteDocument(bDocDes);
		cMenu.deleteDocument(bDoc);
	}
	
	/*
	 * @date: 30/10/2014
	 * Test_Case_ID: 101960
	 * Case 2: Cut a Document Folder and paste it into HTML File document!
	 */
	@Test
	public void test03_2_CutDocumentFolderPasteIntoHTMLFileDocument() {
		String titleCut="titleCut1019602";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		
		String titlePaste="titlePaste1019602";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a Document Folder and paste it to HTML File Document!");
		
		//Create a Document Folder - source
		cTemplate.createNewFolder(titleCut, folderType.Document);

		//Create a HTML File Document -destination
		actBar.goToAddNewContent();
		cTemplate.createNewHtmlFile(titlePaste, "");

		//Cut and paste the Document Folder into HTML File Document
		cMenu.cutNode(bDoc);
		rightClickOnElement(bDocDes);
		waitForElementNotPresent(ELEMENT_PASTE_NODE);
			
		//Delete data
		cMenu.deleteDocument(bDocDes);
		cMenu.deleteDocument(bDoc);
	}
	
	/*
	 * @date: 30/10/2014
	 * Test_Case_ID: 101960
	 * Case 3: Cut a Document Folder and paste it into CSS File document!
	 */
	@Test
	public void test03_3_CutDocumentFolderPasteIntoCSSFileDocument() {
		String titleCut="titleCut1019603";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		
		String titlePaste="titlePaste1019603";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a Document Folder and paste it into CSS File document!");
		
		//Create a Document Folder - source
		cTemplate.createNewFolder(titleCut, folderType.Document);

		//Create a CSS File Document -destination
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_CSS_FILE_LINK);
		cTemplate.createNewCssFile(titlePaste, "", ""	);

		//Cut and paste the Document Folder into CSS File Document
		cMenu.cutNode(bDoc);
		rightClickOnElement(bDocDes);
		waitForElementNotPresent(ELEMENT_PASTE_NODE);
			
		//Delete data
		cMenu.deleteDocument(bDocDes);
		cMenu.deleteDocument(bDoc);
	}
	
	/*
	 * @date: 30/10/2014
	 * Test_Case_ID: 101960
	 * Case 4: Cut a Document Folder and paste it into JS File document!
	 */
	@Test
	public void test03_4_CutDocumentFolderPasteIntoJSFileDocument() {
		String titleCut="titleCut1019604";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		
		String titlePaste="titlePaste1019604";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a document folder and paste it into CSS File document!");
		
		//Create a Document Folder - source
		cTemplate.createNewFolder(titleCut, folderType.Document);

		//Create a JS File Document -destination
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_JS_FILE_LINK);
		cTemplate.createNewJsFile(titlePaste, "", "");

		//Cut and paste the Document Folder into JS File Document
		cMenu.cutNode(bDoc);
		rightClickOnElement(bDocDes);
		waitForElementNotPresent(ELEMENT_PASTE_NODE);
			
		//Delete data
		cMenu.deleteDocument(bDocDes);
		cMenu.deleteDocument(bDoc);
	}
	
	/*
	 * Test_Case_ID: 101960
	 * Case 5: Cut a Document Folder and paste it into Uploaded File!
	 */
	@Test
	public void test03_5_CutDocumentFolderPasteIntoUploadedFile() {
		String titleCut="titleCut1019605";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		
		String img="TestData/ECMS_Admin_ManageCategories_Display.jpg";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", img));

		info("Cut a Document Folder and paste it into Uploaded File!");
		
		//Create a Document Folder - source
		cTemplate.createNewFolder(titleCut, folderType.Document);

		//create an Uploaded file -destination
		ecms.uploadFile(img);
		waitForAndGetElement(bDocDes);

		//Cut and paste the Document Folder into Uploaded File
		cMenu.cutNode(bDoc);
		rightClickOnElement(bDocDes);
		waitForElementNotPresent(ELEMENT_PASTE_NODE);
			
		//Delete data
		cMenu.deleteDocument(bDocDes);
		cMenu.deleteDocument(bDoc);
	}
	
	
	/*
	 * Test_Case_ID: 101961
	 * Case 1: Cut a File Document and paste it into a Content Folder!
	 * This test case is failed if before executing this test case, user execute action "Delete a Document Folder"
	 * Issue ECMS-6479 is created for this problem
	 */
	@Test
	public void test04_1_CutFilePasteIntoDocumentFolder(){
		String titleCut="titleCut1019611";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		String titlePaste="titlePaste1019611";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a File Document and paste it into Content Folder!");
		
		//Create a Content Folder -destination
		cTemplate.createNewFolder(titlePaste, folderType.Content);

		//Create a File document - source
		actBar.goToAddNewContent();
		cTemplate.createNewFile(titleCut, titleCut, titleCut);

		//Cut and paste the File Document into Content Folder
		cMenu.cutAndPasteNode(bDoc, bDocDes);
	
		//Verify if paste file document in a node
		waitForElementNotPresent(bDoc);
		ecms.goToNode(bDocDes);
		ecms.goToNode(bDoc);
		waitForAndGetElement(By.xpath("//*[contains(@value,'"+ titlePaste + "/" + titleCut + "')]"));

		//Delete data
		cMenu.deleteDocument(bDocDes);
	}

	/*
	 * @date: 31/10/2014
	 * Test_Case_ID: 101961
	 * Case 2: Cut a CSS File Document and paste it into a Content Folder!
	 * This test case is failed if before executing this test case, user execute action "Delete a Document Folder"
	 * Issue ECMS-6479 is created for this problem
	 */
	@Test
	public void test04_2_CutCSSFilePasteIntoContentFolder(){
		String titleCut="titleCut1019612";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		String titlePaste="titlePaste1019612";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a CSS File Document and paste it into a Content Folder!");
	
		//Create a CSS File document - source
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_CSS_FILE_LINK);
		cTemplate.createNewCssFile(titleCut, "", ""	);

		//Create a Content Folder -destination
		cTemplate.createNewFolder(titlePaste, folderType.Content);

		//Cut and paste the CSS File Document into Content Folder
		cMenu.cutAndPasteNode(bDoc, bDocDes);
	
		//Verify if paste CSS File document in Content Folder
		ecms.goToNode(bDocDes);
		waitForAndGetElement(bDocDes);
		
		//Delete data
		cMenu.deleteDocument(bDocDes);
	}

	/*
	 * @date: 31/10/2014
	 * Test_Case_ID: 101961
	 * Case 3: Cut a JS File Document and paste it into a Content Folder!
	 * This test case is failed if before executing this test case, user execute action "Delete a Document Folder"
	 * Issue ECMS-6479 is created for this problem
	 */
	@Test
	public void test04_3_CutJSFilePasteIntoContentFolder(){
		String titleCut="titleCut1019613";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		String titlePaste="titlePaste1019613";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a JS File Document and paste it into a Content Folder!");
					
		//Create a Content Folder -destination
		cTemplate.createNewFolder(titlePaste, folderType.Content);
		
		//Create a JS File document - source
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_JS_FILE_LINK);
		cTemplate.createNewJsFile(titleCut, "", "");

		//Cut and paste the JS File Document into Content Folder
		cMenu.cutAndPasteNode(bDoc, bDocDes);
	
		//Verify if paste JS File document in Content Folder
		ecms.goToNode(bDocDes);
		waitForAndGetElement(bDocDes);
		
		//Delete data
		cMenu.deleteDocument(bDocDes);
	}
	
	/*
	 * @date: 31/10/2014
	 * Test_Case_ID: 101961
	 * Case 4: Cut a HTML File Document and paste it into a Content Folder!
	 * This test case is failed if before executing this test case, user execute action "Delete a Document Folder"
	 * Issue ECMS-6479 is created for this problem
	 */
	@Test
	public void test04_4_CutHTMLFilePasteIntoContentFolder(){
		String titleCut="titleCut1019614";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		String titlePaste="titlePaste1019614";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a HTML File Document and paste it into a Content Folder!");
				
		//Create a Content Folder -destination
		cTemplate.createNewFolder(titlePaste, folderType.Content);
		
		//Create a HTML File document - source
		actBar.goToAddNewContent();
		cTemplate.createNewHtmlFile(titleCut, "");

		//Cut and paste the HTML File Document into Content Folder
		cMenu.cutAndPasteNode(bDoc, bDocDes);
	
		//Verify if paste HTML File document into Content Folder
		ecms.goToNode(bDocDes);
		waitForAndGetElement(bDocDes);
		
		//Delete data
		cMenu.deleteDocument(bDocDes);
	}
	
	/*
	 * Test_Case_ID: 101966
	 * Cut a checked in node and paste it to a content folder!
	 */
	@Test
	public void test05_CutCheckInNodePasteToContentFolder() {
		String titleCut="titleCut101966";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));
		String titlePaste="titlePaste101966";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titlePaste));

		info("Cut a checkin File Document and paste it into a Content Folder!");

		//Create a File document
		actBar.goToAddNewContent();
		cTemplate.createNewAnnouncement(titleCut, titleCut);
		cMenu.contextMenuAction(bDoc, cMenu.ELEMENT_MENU_CHECKIN);

		//Add icon Version to action bar
		actBar.addItem2ActionBar("manageVersions", actBar.ELEMENT_VERSIONS_LINK);
		actBar.chooseDrive(ecms.ELEMENT_SITES_MANAGEMENT_DRIVE);
		actBar.addVersionForNode(bDoc, "version file");

		click(siteExp.ELEMENT_SIDEBAR_SITES_MANAGEMENT);

		//Create a Content Folder -destination
		cTemplate.createNewFolder(titlePaste, folderType.Content);

		//Cut and paste the checked in File Document into Content Folder
		cMenu.cutAndPasteNode(bDoc, bDocDes);

		//Verify if paste the File Document into Content Folder
		waitForElementNotPresent(bDoc);
		ecms.goToNode(titlePaste);
		waitForAndGetElement(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleCut));

		//Delete data
		cMenu.deleteDocument(bDocDes);
	}	
	
	/*
	 * Test_Case_ID: 101969
	 * Cut an uploaded file and paste it to a Node
	 */
	@Test
	public void test06_CutUploadedFilePasteToANode() {
		String img = "TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";
		By bDoc=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", "ECMS_DMS_SE_BasicAction_CutPaste.png"));
		String titleDes="des66819";
		By bDocDes=By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", titleDes));
		
		info("Cut an Uploaded File and paste it into a Content Folder!");

		//Upload an Uploaded File - source
		ecms.uploadFile(img);
		waitForAndGetElement(bDoc);

		click(siteExp.ELEMENT_SIDEBAR_SITES_MANAGEMENT);

		//Create a Content Folder -destination
		cTemplate.createNewFolder(titleDes, folderType.Content);

		//Cut and paste the Uploaded File to a Content Folder
		cMenu.cutAndPasteNode(bDoc, bDocDes);

		//Verify if paste Uploaded File in a Content Folder
		waitForElementNotPresent(bDoc);
		ecms.goToNode(titleDes);
		waitForAndGetElement(bDoc);

		//Delete data
		cMenu.deleteDocument(bDocDes);
	}
}
