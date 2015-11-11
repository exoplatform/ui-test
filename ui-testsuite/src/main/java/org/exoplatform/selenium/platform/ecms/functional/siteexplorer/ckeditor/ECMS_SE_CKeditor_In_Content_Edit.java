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
import org.testng.annotations.*;


public class ECMS_SE_CKeditor_In_Content_Edit  extends PlatformBase{
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
	 *<li> Case ID:119880.</li>
	 *<li> Test Case Name: Edit an accessible breadcrum.</li>
	 *<li> Pre-Condition: an accessible breadcrum exists.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_EditAnAccessibleBreadcrum() {
		info("Test 1: Edit an accessible breadcrum");
		String ACCESSIBLE_BREADCRUM_NAME = "breadcrum"+getRandomNumber();
		String ACCESSIBLE_BREADCRUM_CONTENT = "Breadcrum_content_"+getRandomNumber();
		String ACCESSIBLE_BREADCRUM_SUMMARY = "Breadcrum_summary_"+getRandomNumber();
		String ACCESSIBLE_FILE ="Accessible Breadcrumb";

		String NEW_ACCESSIBLE_BREADCRUM_NAME = "breadcrum"+getRandomNumber();
		String NEW_ACCESSIBLE_BREADCRUM_CONTENT = "Breadcrum_content_"+getRandomNumber();
		String NEW_ACCESSIBLE_BREADCRUM_SUMMARY = "Breadcrum_summary_"+getRandomNumber();

		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new accessible breadcrum");
		cTemplate.createNewContentForCK(ACCESSIBLE_FILE,ACCESSIBLE_BREADCRUM_NAME,ACCESSIBLE_BREADCRUM_NAME,ACCESSIBLE_BREADCRUM_CONTENT, ACCESSIBLE_BREADCRUM_SUMMARY, false);

		/*Step Number: 1
		 *Step Name: Open accessible breadcrum
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Open accessible breadcrum
		 *Input Data: 

		 *Expected Outcome: 
			Accessible breadcrum is opened*/
		actBar.goToNodeByAddressPath("/");

		/*Step number: 2
		 *Step Name: Edit content in Main content
		 *Step Description: 
			- Edit content in main content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is Edited & decorated*/

		/*Step number: 3
		 *Step Name: Edit content in Summary
		 *Step Description: 
			- Move to illustration tab
			- Edit content for summary field. Use some features of editor to decorate this summary
		 *Input Data: 

		 *Expected Outcome: 
			Summary is Edited & decorated*/

		/*Step number: 4
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- Accessible breadcrum is Edited successfully
			- Summary and Content are displayed as decorated.*/ 
		cTemplate.editNewContentForCK(ACCESSIBLE_BREADCRUM_NAME,NEW_ACCESSIBLE_BREADCRUM_NAME,NEW_ACCESSIBLE_BREADCRUM_CONTENT, NEW_ACCESSIBLE_BREADCRUM_SUMMARY, true);

		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ACCESSIBLE_BREADCRUM_NAME));
		cMenu.deleteDocument(NEW_ACCESSIBLE_BREADCRUM_NAME);
	}

	/**
	 *<li> Case ID:119881.</li>
	 *<li> Test Case Name: Edit a accessible navigation.</li>
	 *<li> Pre-Condition: accessible navigation exists.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_EditAAccessibleNavigation() {
		info("Test 2: Edit a accessible navigation");
		String ACCESSIBLE_NAVIGATION_NAME = "navigation"+getRandomNumber();
		String ACCESSIBLE_NAVIGATION_CONTENT = "Navigation_content_"+getRandomNumber();
		String ACCESSIBLE_NAVIGATION_SUMMARY = "Navigation_summary_"+getRandomNumber();
		String NEW_ACCESSIBLE_NAVIGATION_NAME = "navigation"+getRandomNumber();
		String NEW_ACCESSIBLE_NAVIGATION_CONTENT = "Navigation_content_"+getRandomNumber();
		String NEW_ACCESSIBLE_NAVIGATION_SUMMARY = "Navigation_summary_"+getRandomNumber();

		String ACCESSIBLE_FILE ="Accessible Navigation";
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new accessible navigation");
		cTemplate.createNewContentForCK(ACCESSIBLE_FILE,ACCESSIBLE_NAVIGATION_NAME,ACCESSIBLE_NAVIGATION_NAME,ACCESSIBLE_NAVIGATION_CONTENT, ACCESSIBLE_NAVIGATION_SUMMARY, false);

		/*Step Number: 1
		 *Step Name: Open accessible navigation
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Open accessible navigation
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Navigation template is opened*/
		actBar.goToNodeByAddressPath("/");

		/*Step number: 2
		 *Step Name: Edit content in Main content
		 *Step Description: 
			- Edit content in main content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is Edited & decorated*/

		/*Step number: 3
		 *Step Name: Edit content in Summary
		 *Step Description: 
			- Move to illustration tab
			- Edit content for summary field. Use some features of editor to decorate this summary
		 *Input Data: 

		 *Expected Outcome: 
			Summary is Edited & decorated*/

		/*Step number: 4
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- Accessiblenavigation is Edited successfully
			- Summary and Content are displayed as decorated.*/ 
		cTemplate.editNewContentForCK(ACCESSIBLE_NAVIGATION_NAME,NEW_ACCESSIBLE_NAVIGATION_NAME,NEW_ACCESSIBLE_NAVIGATION_CONTENT, NEW_ACCESSIBLE_NAVIGATION_SUMMARY, true);

		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ACCESSIBLE_NAVIGATION_NAME));
		cMenu.deleteDocument(NEW_ACCESSIBLE_NAVIGATION_NAME);
	}

	/**
	 *<li> Case ID:119882.</li>
	 *<li> Test Case Name: Edit a accessible search box.</li>
	 *<li> Pre-Condition: An accessible search box exists.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_EditAAccessibleSearchBox() {
		info("Test 3: Edit a accessible search box");
		String ACCESSIBLE_SEARCHBOX_NAME = "searchbox"+getRandomNumber();
		String ACCESSIBLE_SEARCHBOX_CONTENT = "Searchbox_content_"+getRandomNumber();
		String ACCESSIBLE_SEARCHBOX_SUMMARY = "Searchbox_summary_"+getRandomNumber();
		String NEW_ACCESSIBLE_SEARCHBOX_NAME = "searchbox"+getRandomNumber();
		String NEW_ACCESSIBLE_SEARCHBOX_CONTENT = "Searchbox_content_"+getRandomNumber();
		String NEW_ACCESSIBLE_SEARCHBOX_SUMMARY = "Searchbox_summary_"+getRandomNumber();
		String ACCESSIBLE_FILE ="Accessible Site Search Box";
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("accessible search box");
		cTemplate.createNewContentForCK(ACCESSIBLE_FILE,ACCESSIBLE_SEARCHBOX_NAME,ACCESSIBLE_SEARCHBOX_NAME,ACCESSIBLE_SEARCHBOX_CONTENT,ACCESSIBLE_SEARCHBOX_SUMMARY, false);
		/*Step Number: 1
		 *Step Name: Open accessible search box
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Open accessible search box
		 *Input Data: 

		 *Expected Outcome: 
			Accessible search boxtemplate is opened*/
		actBar.goToNodeByAddressPath("/");

		/*Step number: 2
		 *Step Name: Edit content in Main content
		 *Step Description: 
			- Edit content in main content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is edited & decorated*/

		/*Step number: 3
		 *Step Name: Edit content in Summary
		 *Step Description: 
			- Move to illustration tab
			- Edit content for summary field. Use some features of editor to decorate this summary
		 *Input Data: 

		 *Expected Outcome: 
			Summary is edited & decorated*/

		/*Step number: 4
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- New Accessiblesearch boxis Edited successfully
			- Summary and Content are displayed as decorated.*/ 
		cTemplate.editNewContentForCK(ACCESSIBLE_SEARCHBOX_NAME,NEW_ACCESSIBLE_SEARCHBOX_NAME,NEW_ACCESSIBLE_SEARCHBOX_CONTENT,NEW_ACCESSIBLE_SEARCHBOX_SUMMARY, true);

		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ACCESSIBLE_SEARCHBOX_NAME));
		cMenu.deleteDocument(NEW_ACCESSIBLE_SEARCHBOX_NAME);
	}

	/**
	 *<li> Case ID:119883.</li>
	 *<li> Test Case Name: Edit a web content.</li>
	 *<li> Pre-Condition: A webcontent exists.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_EditAWebContent() {
		info("Test 4: Edit a web content");
		String WEBCONTENT_NAME = "webcontent"+getRandomNumber();
		String WEBCONTENT_CONTENT = "Webcontent_content_"+getRandomNumber();
		String WEBCONTENT_SUMMARY = "Webcontent_summary_"+getRandomNumber();
		String NEW_WEBCONTENT_CONTENT = "Webcontent_content_"+getRandomNumber();
		String NEW_WEBCONTENT_SUMMARY = "Webcontent_summary_"+getRandomNumber();

		String WEBCONTENT_FILE ="Web Content";

		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new web content");
		cTemplate.createNewWebContentForCK(WEBCONTENT_FILE,WEBCONTENT_NAME,false,"",WEBCONTENT_CONTENT, WEBCONTENT_SUMMARY,"tab4","tab5");

		/*Step Number: 1
		 *Step Name: Open wc
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Open webcontent
		 *Input Data: 

		 *Expected Outcome: 
			web content is opened*/
		actBar.goToNodeByAddressPath("/");
		/*Step number: 2
		 *Step Name: Edit content in Main content
		 *Step Description: 
			- Edit content in main content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is Edited& decorated*/

		/*Step number: 3
		 *Step Name: Edit content in Summary
		 *Step Description: 
			- Move to illustration tab
			- Edit content for summary field. Use some features of editor to decorate this summary
		 *Input Data: 

		 *Expected Outcome: 
			Summary is Edited & decorated*/

		/*Step number: 4
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- Web content is Edited successfully
			- Summary and Content are displayed as decorated.*/ 
		cTemplate.editNewWebContentForCK(WEBCONTENT_NAME,true,"",NEW_WEBCONTENT_CONTENT, NEW_WEBCONTENT_SUMMARY,"tab4","tab5");

		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", WEBCONTENT_NAME));
		cMenu.deleteDocument(WEBCONTENT_NAME);
	}

	/**
	 *<li> Case ID:119884.</li>
	 *<li> Test Case Name: Edit a Illustrated web content.</li>
	 *<li> Pre-Condition: An Illustrated wc exists.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_EditAIllustratedWebContent() {
		info("Test 5: Edit a Illustrated web content");
		String ILL_WEBCONTENT_NAME = "illWebcontent"+getRandomNumber();
		String ILL_WEBCONTENT_CONTENT = "IllWebcontent"+getRandomNumber();
		String ILL_WEBCONTENT_SUMMARY = "IllWebcontent"+getRandomNumber();
		String  ILL_WEBCONTENT_FILE ="Illustrated Web Content";
		String NEW_ILL_WEBCONTENT_CONTENT = "IllWebcontent"+getRandomNumber();
		String NEW_ILL_WEBCONTENT_SUMMARY = "IllWebcontent"+getRandomNumber();

		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new Illustrated web content");
		cTemplate.createNewIllWebContentForCK(ILL_WEBCONTENT_FILE,ILL_WEBCONTENT_NAME,false,"",ILL_WEBCONTENT_CONTENT,ILL_WEBCONTENT_SUMMARY,"tab1","tab2");

		/*Step Number: 1
		 *Step Name: Open Illustrated web content
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Open Illustrated web content
		 *Input Data: 

		 *Expected Outcome: 
			Illustrated web content template is Opened*/
		actBar.goToNodeByAddressPath("/");
		/*Step number: 2
		 *Step Name: Edit content in Main content
		 *Step Description: 
			- Edit content in main content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is Edited & decorated*/

		/*Step number: 3
		 *Step Name: Edit content in Summary
		 *Step Description: 
			- Move to illustration tab
			- Edit content for summary field. Use some features of editor to decorate this summary
		 *Input Data: 

		 *Expected Outcome: 
			Summary is Edited & decorated*/

		/*Step number: 4
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- Illustrated web contentis Edited successfully
			- Summary and Content are displayed as decorated.*/ 
		cTemplate.editNewIllWebContentForCK(ILL_WEBCONTENT_NAME,true,"",NEW_ILL_WEBCONTENT_CONTENT,NEW_ILL_WEBCONTENT_SUMMARY,"tab1","tab2");

		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ILL_WEBCONTENT_NAME));
		cMenu.deleteDocument(ILL_WEBCONTENT_NAME);
	}

	/**
	 *<li> Case ID:119885.</li>
	 *<li> Test Case Name: Edit an Accessible media.</li>
	 *<li> Pre-Condition: an Accessible media exists.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_EditAnAccessibleMedia() {
		info("Test 6: Edit an Accessible media");
		String ACCESSIBLE_MEDIA_NAME = "Accessiblemedia"+getRandomNumber();
		String ACCESSIBLE_MEDIA_ALTER = "Accessiblemedia"+getRandomNumber();
		String NEW_ACCESSIBLE_MEDIA_NAME = "Accessiblemedia"+getRandomNumber();
		String NEW_ACCESSIBLE_MEDIA_ALTER = "Accessiblemedia"+getRandomNumber();

		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new Accessible media");
		cTemplate.createNewAccessibleMedia(ACCESSIBLE_MEDIA_NAME,false,ACCESSIBLE_MEDIA_NAME,ACCESSIBLE_MEDIA_ALTER,"",true);

		/*Step Number: 1
		 *Step Name: OpenAccessible media
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- OpenAccessible media
		 *Input Data: 

		 *Expected Outcome: 
			Accessible media template is Opened*/
		actBar.goToNodeByAddressPath("/");
		/*Step number: 2
		 *Step Name: Edit content in Alternative text
		 *Step Description: 
			- Edit content in Alternative text, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Alternative text is Edited & decorated*/

		/*Step number: 3
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- Accessible media is Edited successfully
			- Alternative text is displayed as decorated.*/ 
		cTemplate.editNewAccessibleMedia(ACCESSIBLE_MEDIA_NAME,true,NEW_ACCESSIBLE_MEDIA_NAME,NEW_ACCESSIBLE_MEDIA_ALTER,"",true);
		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ACCESSIBLE_MEDIA_NAME));
		cMenu.deleteDocument(NEW_ACCESSIBLE_MEDIA_NAME);
	}

	/**
	 *<li> Case ID:119886.</li>
	 *<li> Test Case Name: Edit announcement.</li>
	 *<li> Pre-Condition: An Announcement content exists.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_EditAnnouncement() {
		info("Test 7: Edit announcement");
		String ANNOUNCEMENT_NAME = "Announcement"+getRandomNumber();
		String ANNOUNCEMENT_SUM = "Announcement_Summary"+getRandomNumber();
		String NEW_ANNOUNCEMENT_SUM = "Announcement_Summary"+getRandomNumber();
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new announcement");
		cTemplate.createNewAnnouncement(ANNOUNCEMENT_NAME,ANNOUNCEMENT_SUM,false);
		/*Step Number: 1
		 *Step Name: Open announcement
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Open announcement
		 *Input Data: 

		 *Expected Outcome: 
			Announcement template is opened*/

		/*Step number: 2
		 *Step Name: Edit content in Summary
		 *Step Description: 
			- Edit content for summary field. Use some features of editor to decorate this summary
		 *Input Data: 

		 *Expected Outcome: 
			Summary is Edited & decorated*/
		actBar.goToNodeByAddressPath("/");

		/*Step number: 3
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- Announcement is Edited successfully
			- Summary is displayed as decorated.*/ 
		cTemplate.editNewAnnouncement(ANNOUNCEMENT_NAME,NEW_ANNOUNCEMENT_SUM,true);
		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", ANNOUNCEMENT_NAME));
		cMenu.deleteDocument(ANNOUNCEMENT_NAME);
	}

	/**
	 *<li> Case ID:119887.</li>
	 *<li> Test Case Name: Edit File with text/HTML.</li>
	 *<li> Pre-Condition: A File with mine type is text/html exists.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test08_EditFileWithTextHTML() {
		info("Test 8: Edit File with text/HTML");
		String FILE_NAME = "File"+getRandomNumber();
		String FILE_CONTENT = "File "+getRandomNumber();
		String NEW_FILE_CONTENT = "File "+getRandomNumber();
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new File with text/HTML");
		cTemplate.createNewFileForCK(FILE_NAME,FILE_CONTENT,false);

		/*Step Number: 1
		 *Step Name: OpenFiletemplate
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Open File
		 *Input Data: 

		 *Expected Outcome: 
			File is opened*/

		/*Step number: 2
		 *Step Name: Edit content in Content field
		 *Step Description: 
			- Edit content in Content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is Edited & decorated*/

		/*Step number: 3
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- New file is Edited successfully
			- Content is displayed as decorated.*/
		actBar.goToNodeByAddressPath("/");
		cTemplate.editNewFileForCK(FILE_NAME,NEW_FILE_CONTENT,true);

		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", FILE_NAME));
		cMenu.deleteDocument(FILE_NAME);
	}

	/**
	 *<li> Case ID:119888.</li>
	 *<li> Test Case Name: Edit File with application/x-groovy+html.</li>
	 *<li> Pre-Condition: A File with mine type is application/x
	-groovy+html exists.</li>
	 *<li> Post-Condition: </li>
	 *https://jira.exoplatform.org/browse/ECMS-6644
	 */
	@Test (groups="pending")
	public  void test09_EditFileWithApplicationxGroovyHtml() {
		info("Test 9: Edit File with application/x-groovy+html");
		String FILE_GROOVYHTML_NAME = "groovyhtml"+getRandomNumber();
		String FILE_GROOVYHTML_CONTENT = "application/x-groovy+html "+getRandomNumber();
		String NEW_FILE_GROOVYHTML_CONTENT = "application/x-groovy+html "+getRandomNumber();
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new File with application/x-groovy+html");

		cTemplate.createNewFileForCK(FILE_GROOVYHTML_NAME,FILE_GROOVYHTML_CONTENT,false,"application/x-groovy+html");

		/*Step Number: 1
		 *Step Name: OpenFile
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Open file
		 *Input Data: 

		 *Expected Outcome: 
			File is opened*/

		/*Step number: 2
		 *Step Name: Edit content in Content field
		 *Step Description: 
			- Edit content in Content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is Edited & decorated*/

		/*Step number: 3
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- New file is Edited successfully
			- Content is displayed as decorated.*/ 
		actBar.goToNodeByAddressPath("/");
		cTemplate.editNewFileForCK(FILE_GROOVYHTML_NAME,NEW_FILE_GROOVYHTML_CONTENT,true);

		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", FILE_GROOVYHTML_NAME));
	}

	/**
	 *<li> Case ID:119889.</li>
	 *<li> Test Case Name: Edit HTML file.</li>
	 *<li> Pre-Condition: A html file exists</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test10_EditHTMLFile() {
		info("Test 10 Edit HTML file");
		String HTMLFILE_NAME = "HTMLfile"+getRandomNumber();
		String HTMLFILE_CONTENT = "HTML file "+getRandomNumber();
		String NEW_HTMLFILE_CONTENT = "HTML file "+getRandomNumber();
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new HTML file");

		cTemplate.createNewHtmlFile(HTMLFILE_NAME,false,"",HTMLFILE_CONTENT);
		//waitForAndGetElement(By.xpath("//*[@id='UIDocumentContainer']//u/em/strong[contains(text(),HTMLFILE_CONTENT)]"));

		/*Step Number: 1
		 *Step Name: ChooseHTML Filetemplate
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Open HTML file
		 *Input Data: 

		 *Expected Outcome: 
			HTML File template is opened*/

		/*Step number: 2
		 *Step Name: Edit content in Content field
		 *Step Description: 
			- Edit content in Content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is edited & decorated*/

		/*Step number: 3
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- NewHTML File is edited successfully
			- Content is displayed as decorated.*/ 
		actBar.goToNodeByAddressPath("/");
		cTemplate.editNewHtmlFile(HTMLFILE_NAME,true,"",NEW_HTMLFILE_CONTENT);

		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", HTMLFILE_NAME));
		cMenu.deleteDocument(HTMLFILE_NAME);
	}

	/**
	 *<li> Case ID:119890.</li>
	 *<li> Test Case Name: Edit a product.</li>
	 *<li> Pre-Condition: A product exists.</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test11_EditAProduct() {
		info("Test 11 Edit a product");
		String PRODUCT_NAME = "Productname"+getRandomNumber();
		String PRODUCT_SUM = "Product summary "+getRandomNumber();
		String PRODUCT_BENEFIT = "Product benefit "+getRandomNumber();
		String PRODUCT_FEATURE = "Product feature "+getRandomNumber();
		String NEW_PRODUCT_SUM = "Product summary "+getRandomNumber();
		String NEW_PRODUCT_BENEFIT = "Product benefit "+getRandomNumber();
		String NEW_PRODUCT_FEATURE = "Product feature "+getRandomNumber();
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new Product");
		cTemplate.createNewProductForCK(PRODUCT_NAME,PRODUCT_SUM,PRODUCT_BENEFIT,PRODUCT_FEATURE,false);

		/*Step Number: 1
		 *Step Name: Choose Product template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Open Product content
		 *Input Data: 

		 *Expected Outcome: 
			Product is opened*/

		/*Step number: 2
		 *Step Name: Edit content in Summary
		 *Step Description: 
			- Edit content for summary field. Use some features of editor to decorate this summary
		 *Input Data: 

		 *Expected Outcome: 
			Summary is Edited & decorated*/

		/*Step number: 3
		 *Step Name: Edit content in Benefit
		 *Step Description: 
			- Edit content in Benefit, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Benefit is Edited & decorated*/

		/*Step number: 4
		 *Step Name: Edit content in Feature
		 *Step Description: 
			- Edit content in Feature, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Feature is Edited & decorated*/

		/*Step number: 5
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- New Product is created successfully
			- Summary, Benefit and Feature are displayed as Edited*/ 
		actBar.goToNodeByAddressPath("/");
		cTemplate.editNewProductForCK(PRODUCT_NAME,NEW_PRODUCT_SUM,NEW_PRODUCT_BENEFIT,NEW_PRODUCT_FEATURE,true,true,true);

		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", PRODUCT_NAME));
		cMenu.deleteDocument(PRODUCT_NAME);
	}

	/**
	 *<li> Case ID:119891.</li>
	 *<li> Test Case Name: Edit Comment for document.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test12_EditCommentForDocument() {
		info("Test 12 Edit Comment for document");
		String WEB_CONTENT_NAME = "webcontent01"+getRandomNumber();
		String CK_COMMENT = "Comment of test01"+getRandomNumber();
		String NEW_CK_COMMENT = "Comment of test01"+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Create node
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Create new content or upload new files
		 *Input Data: 

		 *Expected Outcome: 
			Content is created/ file is uploaded*/
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		info("Create new file document");
		cTemplate.createNewWebContent(WEB_CONTENT_NAME, WEB_CONTENT_NAME, "", "", "", "");

		/*Step number: 2
		 *Step Name: Comment document
		 *Step Description: 
			- Open document
			- Click Comment on action bar
			- Fill comments
			- Use CKEditor to decorate comments
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			Comment is filled & decorated & well displayed.*/
		info("Add comment using CKeditor");
		actBar.addComment(CK_COMMENT,false);

		/*Step number: 3
		 *Step Name: Edit comment
		 *Step Description: 
			- Edit this comment
		 *Input Data: 

		 *Expected Outcome: 
			Comment is edited & well displayed*/ 

		actBar.editComment(CK_COMMENT,NEW_CK_COMMENT,true);
		
		info("Restore data");
		actBar.goToNodeByAddressPath("/");
		//cMenu.deleteDocument(ecms.ELEMENT_NODE_ROW_VIEW.replace("${nodeName}", WEB_CONTENT_NAME));
		cMenu.deleteDocument(WEB_CONTENT_NAME);
	}
}
