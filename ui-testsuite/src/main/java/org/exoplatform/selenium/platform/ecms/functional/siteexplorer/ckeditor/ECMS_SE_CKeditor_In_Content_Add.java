package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.ckeditor;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.platform.CKeditor;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
import org.exoplatform.selenium.platform.ecms.admin.ManageDrive;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

    /**
     * 
     * @author anhpp
     * 16/3/2015
     */
	public class ECMS_SE_CKeditor_In_Content_Add extends PlatformBase{
		//Platform
		Button button;
		ManageAccount magAcc;
		NavigationToolbar navToolBar;
		ActionBar actBar;
		ECMainFunction ecMain;
		Dialog dialog;

		//Ecms
		EcmsBase ecms;
		ContextMenu cMenu;
		ContentTemplate cTemplate;
		SitesExplorer sitesExp;
		ManageCategory magCa;
		ManageDrive magDrv;
		CKeditor ck;
		
		@BeforeMethod
		public void beforeMethod(){
			initSeleniumTest();
			driver.get(plfURL);
			button = new Button(driver);
			magAcc = new ManageAccount(driver);
			navToolBar = new NavigationToolbar(driver);
			actBar = new ActionBar(driver);
			ecms = new EcmsBase(driver);
			cMenu = new ContextMenu(driver);
			cTemplate = new ContentTemplate(driver);
			ecMain = new ECMainFunction(driver);
			sitesExp = new SitesExplorer(driver);
			magCa = new ManageCategory(driver);
			dialog = new Dialog(driver);
			magDrv = new ManageDrive(driver);
			magAcc.signIn(DATA_USER1, DATA_PASS);
			ck = new CKeditor(driver);
		}

		@AfterMethod
		public void afterMethod() {
			info("-- User signOut --");
			driver.manage().deleteAllCookies();
			driver.quit();
		}
		
	/**
	*Case ID:119347.
	*Test Case Name: Add Comment for document.
	*Pre-condition: Pkg is installed with exo-wai-sample add-on
	*/
	@Test
	public  void test01_AddCommentForDocument() {
		info("Test 1: Add Comment for document");
		String WEB_CONTENT_NAME = "webcontent01" + getRandomNumber();
		String CK_COMMENT = "Comment of test01";
		/*Step Number: 1
		*Step Name: Create node*/
	    navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new file document");
		cTemplate.createNewWebContent(WEB_CONTENT_NAME, WEB_CONTENT_NAME, "", "", "", "");
		
		/*Step number: 2
		*Step Name: Comment document*/ 
         info("Add comment using CKeditor");
         actBar.addComment(CK_COMMENT,true);
         
         info("Restore data");
         actBar.goToNodeByAddressPath("/");
         //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", WEB_CONTENT_NAME));
         cMenu.deleteDocument(WEB_CONTENT_NAME);
 	}

	/**
	* Case ID:119869.
	* Test Case Name: Add new a accessible breadcrum. 
	*/
	@Test
	public  void test02_AddNewAAccessibleBreadcrum() {
		info("Test 2: Add new a accessible breadcrum");
		String ACCESSIBLE_BREADCRUM_NAME = "breadcrum"+getRandomNumber();
		String ACCESSIBLE_BREADCRUM_CONTENT = "Breadcrum_content_"+getRandomNumber();
		String ACCESSIBLE_BREADCRUM_SUMMARY = "Breadcrum_summary_"+getRandomNumber();
		String ACCESSIBLE_FILE ="Accessible Breadcrumb";
		/*Step Number: 1
		*Step Name: Choose accessible breadcrum template
		*/
		/*Step number: 2
		*Step Name: Fill content in Main content*/
		/*Step number: 3
		*Step Name: Fill content in Summary*/
		/*Step number: 4
		*Step Name: Save content*/ 
		
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new accessible breadcrum");
		cTemplate.createNewContentForCK(ACCESSIBLE_FILE,ACCESSIBLE_BREADCRUM_NAME,ACCESSIBLE_BREADCRUM_NAME,ACCESSIBLE_BREADCRUM_CONTENT, ACCESSIBLE_BREADCRUM_SUMMARY, true);
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ACCESSIBLE_BREADCRUM_NAME));
        cMenu.deleteDocument(ACCESSIBLE_BREADCRUM_NAME);
 	}

	/**
	* Case ID:119870.
	* Test Case Name: Add new a accessible navigation.
	*/
	@Test
	public  void test03_AddNewAAccessibleNavigation() {
		info("Test 3: Add new a accessible navigation");
		String ACCESSIBLE_NAVIGATION_NAME = "navigation"+getRandomNumber();
		String ACCESSIBLE_NAVIGATION_CONTENT = "Navigation_content_"+getRandomNumber();
		String ACCESSIBLE_NAVIGATION_SUMMARY = "Navigation_summary_"+getRandomNumber();
		String ACCESSIBLE_FILE ="Accessible Navigation";
		/*Step Number: 1
		*Step Name: Choose accessible navigation template*/
        
		/*Step number: 2
		*Step Name: Fill content in Main content*/

		/*Step number: 3
		*Step Name: Fill content in Summary*/

		/*Step number: 4
		*Step Name: Save content*/ 
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new accessible navigation");
		cTemplate.createNewContentForCK(ACCESSIBLE_FILE,ACCESSIBLE_NAVIGATION_NAME,ACCESSIBLE_NAVIGATION_NAME,ACCESSIBLE_NAVIGATION_CONTENT, ACCESSIBLE_NAVIGATION_SUMMARY, true);
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ACCESSIBLE_NAVIGATION_NAME));
        cMenu.deleteDocument(ACCESSIBLE_NAVIGATION_NAME);
 	}

	/**
	* Case ID:119871.
	* Test Case Name: Add new a accessible search box.
	*/
	@Test
	public  void test04_AddNewAAccessibleSearchBox() {
		info("Test 4: Add new a accessible search box");
		String ACCESSIBLE_SEARCHBOX_NAME = "searchbox"+getRandomNumber();
		String ACCESSIBLE_SEARCHBOX_CONTENT = "Searchbox_content_"+getRandomNumber();
		String ACCESSIBLE_SEARCHBOX_SUMMARY = "Searchbox_summary_"+getRandomNumber();
		String ACCESSIBLE_FILE ="Accessible Site Search Box";
		/*Step Number: 1
		*Step Name: Choose accessible search box template*/

		/*Step number: 2
		*Step Name: Fill content in Main content*/

		/*Step number: 3
		*Step Name: Fill content in Summary*/

		/*Step number: 4
		*Step Name: Save content*/ 
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("accessible search box");
		cTemplate.createNewContentForCK(ACCESSIBLE_FILE,ACCESSIBLE_SEARCHBOX_NAME,ACCESSIBLE_SEARCHBOX_NAME,ACCESSIBLE_SEARCHBOX_CONTENT,ACCESSIBLE_SEARCHBOX_SUMMARY, true);
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ACCESSIBLE_SEARCHBOX_NAME));
        cMenu.deleteDocument(ACCESSIBLE_SEARCHBOX_NAME);
 	}

	/**
	* Case ID:119872.
	* Test Case Name: Add new a web content.
	*/
	@Test
	public  void test05_AddNewAWebContent() {
		info("Test 5: Add new a web content");
		String WEBCONTENT_NAME = "webcontent"+getRandomNumber();
		String WEBCONTENT_CONTENT = "Webcontent_content_"+getRandomNumber();
		String WEBCONTENT_SUMMARY = "Webcontent_summary_"+getRandomNumber();
		String WEBCONTENT_FILE ="Web Content";
		/*Step Number: 1
		*Step Name: Choose web contenttemplate*/

		/*Step number: 2
		*Step Name: Fill content in Main content*/

		/*Step number: 3
		*Step Name: Fill content in Summary*/

		/*Step number: 4
		*Step Name: Save content*/ 
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new web content");
		cTemplate.createNewWebContentForCK(WEBCONTENT_FILE,WEBCONTENT_NAME,true,"",WEBCONTENT_CONTENT, WEBCONTENT_SUMMARY,"tab4","tab5");
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", WEBCONTENT_NAME));
        cMenu.deleteDocument( WEBCONTENT_NAME);
		
 	}

	/**
	* Case ID:119873.
	* Test Case Name: Add new a Illustrated web content. 
	*/
	@Test
	public  void test06_AddNewAIllustratedWebContent() {
		info("Test 6: Add new a Illustrated web content");
		String ILL_WEBCONTENT_NAME = "illWebcontent"+getRandomNumber();
		String ILL_WEBCONTENT_CONTENT = "IllWebcontent"+getRandomNumber();
		String ILL_WEBCONTENT_SUMMARY = "IllWebcontent"+getRandomNumber();
		String  ILL_WEBCONTENT_FILE ="Illustrated Web Content";
		/*Step Number: 1
		*Step Name: Choose Illustrated web contenttemplate*/

		/*Step number: 2
		*Step Name: Fill content in Main content*/

		/*Step number: 3
		*Step Name: Fill content in Summary*/

		/*Step number: 4
		*Step Name: Save content*/ 
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new Illustrated web content");
		cTemplate.createNewIllWebContentForCK(ILL_WEBCONTENT_FILE,ILL_WEBCONTENT_NAME,true,"",ILL_WEBCONTENT_CONTENT,ILL_WEBCONTENT_SUMMARY,"tab1","tab2");
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ILL_WEBCONTENT_NAME));
        cMenu.deleteDocument(ILL_WEBCONTENT_NAME);
	}

	/**
	* Case ID:119874.
	* Test Case Name: Add new a Accessible media. 
	*/
	@Test (groups="pending")
	public  void test07_AddNewAAccessibleMedia() {
		info("Test 7: Add new a Accessible media");
		String ACCESSIBLE_MEDIA_NAME = "Accessiblemedia"+getRandomNumber();
		String ACCESSIBLE_MEDIA_ALTER = "Accessiblemedia"+getRandomNumber();
		
		/*Step Number: 1
		*Step Name: ChooseAccessible media template*/

		/*Step number: 2
		*Step Name: Fill content in Alternative text*/

		/*Step number: 3
		*Step Name: Save content*/ 
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new Accessible media");
		cTemplate.createNewAccessibleMedia(ACCESSIBLE_MEDIA_NAME,true,ACCESSIBLE_MEDIA_NAME,ACCESSIBLE_MEDIA_ALTER,"",true);
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ACCESSIBLE_MEDIA_NAME));
        cMenu.deleteDocument(ACCESSIBLE_MEDIA_NAME);
 	}

	/**
	* Case ID:119875.
	* Test Case Name: Add new announcement.
	*/
	@Test
	public  void test08_AddNewAnnouncement() {
		info("Test 8: Add new announcement");
		String ANNOUNCEMENT_NAME = "Announcement"+getRandomNumber();
		String ANNOUNCEMENT_SUM = "Announcement_Summary"+getRandomNumber();
		/*Step Number: 1
		*Step Name: Choose announcement template*/

		/*Step number: 2
		*Step Name: Fill content in Summary*/

		/*Step number: 3
		*Step Name: Save content*/ 
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new announcement");
		cTemplate.createNewAnnouncement(ANNOUNCEMENT_NAME,ANNOUNCEMENT_SUM,true);
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ANNOUNCEMENT_NAME));
        cMenu.deleteDocument(ANNOUNCEMENT_NAME);
 	}

	/**
	* Case ID:119876.
	* Test Case Name: Add new File with text/HTML.
	*/
	@Test
	public  void test09_AddNewFileWithTextHTML() {
		info("Test 9: Add new File with text/HTML");
		String FILE_NAME = "File"+getRandomNumber();
		String FILE_CONTENT = "File "+getRandomNumber();
		/*Step Number: 1
		*Step Name: ChooseFiletemplate*/

		/*Step number: 2
		*Step Name: Fill content in Content field*/

		/*Step number: 3
		*Step Name: Save content*/ 
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new File with text/HTML");
		cTemplate.createNewFileForCK(FILE_NAME,FILE_CONTENT,true);
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", FILE_NAME));
        cMenu.deleteDocument(FILE_NAME);
 	}

	/**
	* Case ID:119877.
	* Test Case Name: Add new File with application/x-groovy+html.
	* https://jira.exoplatform.org/browse/ECMS-6644
	*/
	@Test (groups="pending")
	public  void test10_AddNewFileWithApplicationGroovyHtml() {
		info("Test 10 Add new File with application/x-groovy+html");
		String FILE_GROOVYHTML_NAME = "groovyhtml"+getRandomNumber();
		String FILE_GROOVYHTML_CONTENT = "application/x-groovy+html "+getRandomNumber();
		/*Step Number: 1
		*Step Name: ChooseFiletemplate*/

		/*Step number: 2
		*Step Name: Fill content in Content field*/

		/*Step number: 3
		*Step Name: Save content*/ 
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new File with application/x-groovy+html");
		
		cTemplate.createNewFileForCK(FILE_GROOVYHTML_NAME,FILE_GROOVYHTML_CONTENT,true,"application/x-groovy+html");
		//waitForAndGetElement(By.xpath("//*[@id='UIDocumentContainer']//u/em/strong[contains(text(),FILE_GROOVYHTML_CONTENT)]"));
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", FILE_GROOVYHTML_NAME));
        cMenu.deleteDocument(FILE_GROOVYHTML_NAME);
 	}

	/**
	* Case ID:119878.
	* Test Case Name: Add new HTML file.
	*/
	@Test
	public  void test11_AddNewHTMLFile() {
		info("Test 11 Add new HTML file");
		String HTMLFILE_NAME = "HTMLfile"+getRandomNumber();
		String HTMLFILE_CONTENT = "HTML file "+getRandomNumber();
		/*Step Number: 1
		*Step Name: ChooseHTML Filetemplate*/

		/*Step number: 2
		*Step Name: Fill content in Content field*/

		/*Step number: 3
		*Step Name: Save content*/ 
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new HTML file");
		
		cTemplate.createNewHtmlFile(HTMLFILE_NAME,true,"",HTMLFILE_CONTENT);
		//waitForAndGetElement(By.xpath("//*[@id='UIDocumentContainer']//u/em/strong[contains(text(),HTMLFILE_CONTENT)]"));
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", HTMLFILE_NAME));
        cMenu.deleteDocument(HTMLFILE_NAME);
 	}

	/**
	* Case ID:119879.
	* Test Case Name: Add new Product.
	*/
	@Test
	public  void test12_AddNewProduct() {
		info("Test 12 Add new Product");
		String PRODUCT_NAME = "Productname"+getRandomNumber();
		String PRODUCT_SUM = "Product summary "+getRandomNumber();
		String PRODUCT_BENEFIT = "Product benefit "+getRandomNumber();
		String PRODUCT_FEATURE = "Product feature "+getRandomNumber();
		/*Step Number: 1
		*Step Name: Choose Product template*/

		/*Step number: 2
		*Step Name: Fill content in Summary*/

		/*Step number: 3
		*Step Name: Fill content in Benefit*/

		/*Step number: 4
		*Step Name: Fill content in Feature*/

		/*Step number: 5
		*Step Name: Save content*/ 
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new Product");
		cTemplate.createNewProductForCK(PRODUCT_NAME,PRODUCT_SUM,PRODUCT_BENEFIT,PRODUCT_FEATURE,true);
		
		info("Restore data");
        actBar.goToNodeByAddressPath("/");
        //cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", PRODUCT_NAME));
        cMenu.deleteDocument(PRODUCT_NAME);
 	}	
}
