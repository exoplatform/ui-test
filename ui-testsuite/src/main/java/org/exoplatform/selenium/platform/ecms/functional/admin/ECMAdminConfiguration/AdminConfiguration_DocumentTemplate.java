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
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.testng.annotations.*;


public class AdminConfiguration_DocumentTemplate extends PlatformBase {

	//Platform
	NavigationToolbar nav;
	ManageAccount magAcc;
	Dialog dialog;
	Button button;
	//Ecms
	EcmsBase ecms; 
	ActionBar actBar;
	ContextMenu cMenu;
	ContentTemplate cTemplate;
	ManageCategory magCa;

	ManageAlert alert;
	//Data for these test cases
	String categoryName = "category1";
	String newCategoryName = "category2";
	String optionLifeCycle = "Content Addition"; 
	String nodeTargetPath = "jcr:system/exo:namespaces";    

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(plfURL);
		info("Login ECMS with " + DATA_USER1);
		nav = new NavigationToolbar(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		dialog = new Dialog(driver);
		ecms = new EcmsBase(driver, this.plfVersion);
		magCa = new ManageCategory(driver);
		cMenu = new ContextMenu(driver, this.plfVersion);
		alert = new ManageAlert(driver, this.plfVersion);
		actBar = new ActionBar(driver , this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
		//actions = null;
	}

	/**
	 *<li> Case ID:124524.</li>
	 *<li> Test Case Name: Check configuration of Accessible Media Document Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124525.</li>
	 *<li> Test Case Name: Check configuration of Announcement Document Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124526.</li>
	 *<li> Test Case Name: Check configuration of Contact Us Document Template (acme site).</li>
	 *<li> Pre-Condition: acme extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124527.</li>
	 *<li> Test Case Name: Check configuration of CSS File Document Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124528.</li>
	 *<li> Test Case Name: Check configuration of Javascript File Document Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124529.</li>
	 *<li> Test Case Name: Check configuration of HTML File Document Template.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124530.</li>
	 *<li> Test Case Name: Check configuration of Illustrated Web Content Document Template.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124531.</li>
	 *<li> Test Case Name: Check configuration of Product Document Template (acme site).</li>
	 *<li> Pre-Condition: acme extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124532.</li>
	 *<li> Test Case Name: Check configuration of Accessible Breadcrumb Document Template (wai site).</li>
	 *<li> Pre-Condition: wai extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124533.</li>
	 *<li> Test Case Name: Check configuration of Accessible Navigation Document Template (wai site).</li>
	 *<li> Pre-Condition: WAI add-on must have been installed</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124534.</li>
	 *<li> Test Case Name: Check configuration of Accessible Site Search Box Document Template (wai site).</li>
	 *<li> Pre-Condition: wai extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124535.</li>
	 *<li> Test Case Name: Check configuration of Web Content Document Template.</li>
	 *<li> Pre-Condition: Platform is started with added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124536.</li>
	 *<li> Test Case Name: Check configuration of Web Link Document Template.</li>
	 *<li> Pre-Condition: Platform is started with no installed addon</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124537.</li>
	 *<li> Test Case Name: Check configuration of File Document Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 */
	@Test
	public  void test01To14_CheckConfigurationOfAllDocumentTemplate() {
		info("Test 1: Check configuration of Accessible Media Document Template");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> Document
		 *Input Data: 

		 *Expected Outcome: 
			Document Template list is shown*/

        nav.goToContentAdministration();
		click(ecms.ELEMENT_TEMPLATE_DOCUMENT);
		info("Document Template list is shown");
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_TEMPLATE_DOCUMENT_DOCUMENT_LIST);
		
		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- In Documents tab, check items in the list
			- Check node type of Accessible Media
		 *Input Data: 

		 *Expected Outcome: 
			- The Template Accessible Media is in the list.
			- Node Type of Accessible Media isexo:accessibleMedia*/

        select(ecms.ELEMENT_TEMPLATE_DOCS_RESULTSPERPAGE, "15", 2);
        Utils.pause(2000);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCMEDIA);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCMEDIA_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCBREAD);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCBREAD_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ANNOUNC);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ANNOUNC_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_CSSFILE_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_CSSFILE);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_CONTACTUS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_CONTACTUS_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_HTMLFILE);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_HTMLFILE_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ILLUSTRATEDWC);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ILLUSTRATEDWC_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_JS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_JS_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_PRODUCT);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_PRODUCT_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_WEBCONTENT);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_WEBCONTENT_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_WEBLINK);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_WEBLINK_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_FILE);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_FILE_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCBREAD);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCBREAD_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCNAV);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCNAV_NAME);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCSITESB);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCSITESB_NAME);
        
		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Click Edit button of Accessible Media template 
			- Go to Dialog tab
		 *Input Data: 

		 *Expected Outcome: 
			- A dialog is configured the name is dialog1
			- Permission are (*)*/
		/*Step number: 4
		 *Step Name: 
		 *Step Description: 
			- Go to View tab
		 *Input Data: 

		 *Expected Outcome: 
			A view is configured : 
			- Name is view1
			- Permission are (*)*/
		/*Step number: 5
		 *Step Name: 
		 *Step Description: 
			- Go to CSS tab
		 *Input Data: 

		 *Expected Outcome: 
			2 stylesheets are configured :
			- name : Stylesheet
			-ltand permission (*)
			- name : Stylesheet
			-rt and permission (*)*/
        info("Check  Accessible Media template");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ACCMED);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT_ACCMED1);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT_ACCMED2);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("Check Accessible Breadcrumb template");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ACCBREAD);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ACC_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("Check Accessible Navigation template");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ACCNAV);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ACC_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("Accessible Site Search Box");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ACCSITESB);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ACC_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("Announcement");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ANNOUNC);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ANNOUN_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("CSS File");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSFILE);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ACC_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
      
        info("Contact us");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CONTACTUS);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("File");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_FILE);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION_2);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT_ACCMED1);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT_ACCMED2);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("HTML File");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_HTMLFILE);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ACC_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("Illustrated Web Content");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ILLUSTRATEDWC);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ANNOUN_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("JS file");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_JS);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ACC_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("Product file");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_PRODUCT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ANNOUN_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("Webcontent");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_WEBCONTENT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ANNOUN_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
        
        info("Web link");
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_WEBLINK);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ANNOUN_DIAL_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
        click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
                
		/*Step number: 6
		 *Step Name: 
		 *Step Description: 
			- Go to Site Explorer
			- Add new content
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Media is in the list of templates*/ 
		
		nav.goToSiteExplorer();
		info("Add a new content");
		actBar.goToAddNewContent();
		
		info("All document's tempaltes are in the list of templates");
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCMEDIA);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCBREAD);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ANNOUNC);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_CSSFILE);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_CONTACTUS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_HTMLFILE);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ILLUSTRATEDWC);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_JS);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_PRODUCT);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_WEBCONTENT);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_WEBLINK);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_FILE);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCBREAD);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCNAV);
        waitForAndGetElement(ecms.ELEMENT_TEMPLATE_DOC_ACCSITESB);
	}

	/**
	 *<li> Case ID:124607.</li>
	 *<li> Test Case Name: Check configuration of Contact Us Document Template when acme is not installed.</li>
	 *<li> Pre-Condition: acme extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test15_CheckConfigurationOfContactUsDocumentTemplateWhenAcmeIsNotInstalled() {
		info("Test 15 Check configuration of Contact Us Document Template when acme is not installed");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect as an admistrator
			- Go to Administration 
			-> Content Administration
			- Go to Templates 
			-> Document
		 *Input Data: 

		 *Expected Outcome: 
			- The list of Document Templates is shown*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- In Documents tab, check item in the list
			- Check node type of Contact Us document template
		 *Input Data: 

		 *Expected Outcome: 
			- Contact Us does not appears in the list*/ 

	}

	/**
	 *<li> Case ID:124608.</li>
	 *<li> Test Case Name: Check configuration of Product Document Template when acme extension is not installed.</li>
	 *<li> Pre-Condition: acme extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test16_CheckConfigurationOfProductDocumentTemplateWhenAcmeExtensionIsNotInstalled() {
		info("Test 16 Check configuration of Product Document Template when acme extension is not installed");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> Document
		 *Input Data: 

		 *Expected Outcome: 
			Document Template list is shown*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Go to Document tab, check items in the list
			- Check node type of Product
		 *Input Data: 

		 *Expected Outcome: 
			- The Template Product does not appear in the list.*/ 

	}

	/**
	 *<li> Case ID:124613.</li>
	 *<li> Test Case Name: Check configuration of Accessible Breadcrumb Document Template when wai extension is not installed.</li>
	 *<li> Pre-Condition: wai extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test17_CheckConfigurationOfAccessibleBreadcrumbDocumentTemplateWhenWaiExtensionIsNotInstalled() {
		info("Test 17 Check configuration of Accessible Breadcrumb Document Template when wai extension is not installed");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> Document
		 *Input Data: 

		 *Expected Outcome: 
			Document Template list is shown*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- In Documents tab, check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The template Accessible Breadcrumb does not appear in the list.*/

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Go to intranet site 
			-> Document
			- Add new content
		 *Input Data: 

		 *Expected Outcome: 
			- Accessible Breadcrumb is not available in the list of templates*/ 

	}

	/**
	 *<li> Case ID:124614.</li>
	 *<li> Test Case Name: Check configuration of Accessible Navigation Document Template when wai extension is not installed.</li>
	 *<li> Pre-Condition: wai extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test18_CheckConfigurationOfAccessibleNavigationDocumentTemplateWhenWaiExtensionIsNotInstalled() {
		info("Test 18 Check configuration of Accessible Navigation Document Template when wai extension is not installed");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> Document
		 *Input Data: 

		 *Expected Outcome: 
			Document Template list is shown*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Go to Documents tab, check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The template Accessible Navigation does not appear in list*/

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Go to Site Explorer 
			-> Document
			- Add new content
		 *Input Data: 

		 *Expected Outcome: 
			- Accessible Navigation is not available in the list of templates*/ 

	}

	/**
	 *<li> Case ID:124615.</li>
	 *<li> Test Case Name: Check configuration of Accessible Site Search Box Document Template when wai extension is not installed.</li>
	 *<li> Pre-Condition: wai extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test19_CheckConfigurationOfAccessibleSiteSearchBoxDocumentTemplateWhenWaiExtensionIsNotInstalled() {
		info("Test 19 Check configuration of Accessible Site Search Box Document Template when wai extension is not installed");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> Document
		 *Input Data: 

		 *Expected Outcome: 
			Document Template list is shown*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- In Documents Tab, check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The template Accessible Site Search Box does not appear in the list.*/

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Go to Site Explorer
			- Add new content
		 *Input Data: 

		 *Expected Outcome: 
			- Accessible Site Search Box is not available in the list of templates*/ 

	}}
