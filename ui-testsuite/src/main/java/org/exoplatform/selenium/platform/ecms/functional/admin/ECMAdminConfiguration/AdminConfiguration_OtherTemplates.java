package org.exoplatform.selenium.platform.ecms.functional.admin.ECMAdminConfiguration;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
import org.exoplatform.selenium.platform.ecms.admin.ManageTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.testng.annotations.*;


public class AdminConfiguration_OtherTemplates extends PlatformBase{

	//Platform
	NavigationToolbar navBar;
	ManageAccount magAcc;
	UserGroupManagement userGroup;
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
	ECMainFunction ecmMain;
	ManageTemplate magTemp;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(plfURL);
		info("Login ECMS with " + DATA_USER1);
		navBar = new NavigationToolbar(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		dialog = new Dialog(driver);
		ecms = new EcmsBase(driver, this.plfVersion);
		magCa = new ManageCategory(driver);
		cMenu = new ContextMenu(driver, this.plfVersion);
		alert = new ManageAlert(driver, this.plfVersion);
		actBar = new ActionBar(driver , this.plfVersion);
		magTemp = new ManageTemplate(driver , this.plfVersion);
		userGroup = new UserGroupManagement(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		cTemplate = new ContentTemplate(driver);
		ecmMain = new ECMainFunction(driver, this.plfVersion);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
	}		

	/**
	 *<li> Case ID:124564.</li>
	 *<li> Test Case Name: Check configuration of Comments Template.</li>
	 *<li> Pre-Condition: Firebug must be installed on the machine</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124565.</li>
	 *<li> Test Case Name: Check configuration of File Content Template.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124566.</li>
	 *<li> Test Case Name: Check configuration of Votes Template.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_03_CheckConfigurationOfCommentsTemplate() {
		info("Test 1: Check configuration of Comments Template");
		String name="node_"+ getRandomNumber();
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> Documents 
			-> Others tab
		 *Input Data: 

		 *Expected Outcome: 
			Actions Templates list is shown*/
		ecmMain.goToTemplateTab();	

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Check the list of templates
			- Check node type of Comments template
		 *Input Data: 

		 *Expected Outcome: 
			- Comments template appears in the list
			- Node type of Comments isexo:comments*/
		click(magTemp.ELEMENT_OTHER_TAB);
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_EDIT.replace("${template}", "Comments"));
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_EDIT.replace("${template}", "Votes"));
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_EDIT.replace("${template}", "File Content"));

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Click Edit button of Comments row
			-Go to Dialog tab
		 *Input Data: 

		 *Expected Outcome: 
			No dialog is configured with*/
		
		/*Step number: 4
		 *Step Name: 
		 *Step Description: 
			- Go to View tab
		 *Input Data: 

		 *Expected Outcome: 
			A view is configure with 
			- name : view1
			- permission : all (*)*/


		/*Step number: 5
		 *Step Name: 
		 *Step Description: 
			- Go to CSS tab
		 *Input Data: 

		 *Expected Outcome: 
			2 stylesheets are configured : 
			- name :Stylesheet
			-lt and permission : all (*)
			- name :Stylesheet
			-rt and permission : all (*)*/
		click(magTemp.ELEMENT_TEMPLATE_DOCUMENT_EDIT.replace("${template}", "Comments"));
		click(magTemp.ELEMENT_DIALOG_TAB);
		waitForAndGetElement(magTemp.ELEMENT_EMPTY_SPACE);
		click(magTemp.ELEMENT_VIEW_TAB);
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_PERMISSION.replace("${view}","view1").replace("${permission}","*"));
		click(magTemp.ELEMENT_CSS_TAB);
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_PERMISSION.replace("${view}","Stylesheet-lt").replace("${permission}","*"));
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_PERMISSION.replace("${view}","Stylesheet-rt").replace("${permission}","*"));
		click(magTemp.ELEMENT_TEMPLATE_CLOSE_WINDOW);
		
		click(magTemp.ELEMENT_TEMPLATE_DOCUMENT_EDIT.replace("${template}", "Votes"));
		click(magTemp.ELEMENT_DIALOG_TAB);
		waitForAndGetElement(magTemp.ELEMENT_EMPTY_SPACE);
		click(magTemp.ELEMENT_VIEW_TAB);
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_PERMISSION.replace("${view}","view1").replace("${permission}","*"));
		click(magTemp.ELEMENT_CSS_TAB);
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_PERMISSION.replace("${view}","Stylesheet-lt").replace("${permission}","*"));
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_PERMISSION.replace("${view}","Stylesheet-rt").replace("${permission}","*"));
		click(magTemp.ELEMENT_TEMPLATE_CLOSE_WINDOW);
		
		click(magTemp.ELEMENT_TEMPLATE_DOCUMENT_EDIT.replace("${template}", "File Content"));
		click(magTemp.ELEMENT_DIALOG_TAB);
		waitForAndGetElement(magTemp.ELEMENT_EMPTY_SPACE);
		click(magTemp.ELEMENT_VIEW_TAB);
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_PERMISSION.replace("${view}","view1").replace("${permission}","*"));
		click(magTemp.ELEMENT_CSS_TAB);
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_PERMISSION.replace("${view}","Stylesheet-lt").replace("${permission}","*"));
		waitForAndGetElement(magTemp.ELEMENT_TEMPLATE_DOCUMENT_PERMISSION.replace("${view}","Stylesheet-rt").replace("${permission}","*"));
		click(magTemp.ELEMENT_TEMPLATE_CLOSE_WINDOW);
		/*Step number: 6
		 *Step Name: 
		 *Step Description: 
			- Go to Documents tab
			- Check items of Document Templates
		 *Input Data: 

		 *Expected Outcome: 
			Comments template is not shown (it is only in Others)*/
		click(magTemp.ELEMENT_TAB.replace("${typeTemplate}", "Documents").replace("${tab}", "Documents"));
		waitForElementNotPresent(magTemp.ELEMENT_TEMPLATE_DOCUMENT_EDIT.replace("${template}", "Comments"));
		waitForElementNotPresent(magTemp.ELEMENT_TEMPLATE_DOCUMENT_EDIT.replace("${template}", "Votes"));
		waitForElementNotPresent(magTemp.ELEMENT_TEMPLATE_DOCUMENT_EDIT.replace("${template}", "File Content"));

		/*Step number: 7
		 *Step Name: 
		 *Step Description: 
			- Edit Admin View to add the Comment action
			- Go to Site Explorer 
			- Create a new content (webcontent)
			- Comment on this content
		 *Input Data: 

		 *Expected Outcome: 
			- New content created
			- Comment added to this content*/ 
		info("-- Open Sites Explorer --");
		navBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();

		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name,true);
		switchToParentWindow();
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);

		actBar.goToAddComment();
		inputDataToFrame(actBar.ELEMENT_ADD_COMMENT_FRAME_41, name, true);
		switchToParentWindow();
		click(button.ELEMENT_SAVE_BUTTON);
	}
}
