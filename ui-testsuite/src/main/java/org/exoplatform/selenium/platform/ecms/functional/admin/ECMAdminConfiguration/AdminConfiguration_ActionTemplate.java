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
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


public class AdminConfiguration_ActionTemplate extends PlatformBase {

	//Platform
	NavigationToolbar nav;
	ManageAccount magAcc;
	Dialog dialog;
	Button button;
	//Ecms
	EcmsBase ecms; 
	ActionBar actBar;
	ContextMenu cMenu;
	ManageCategory magCa;
	ContentTemplate cTemplate;

	//Data for these test cases
	String categoryName = "category1";
	String newCategoryName = "category2";
	String categoryWorkspace = "collaboration";
	String nodeHomePath = "sites/intranet";
	//String username = DATA_USER1;
	String groupID = "Platform/Administration"; 
	String actionName = "test";
	String optionLifeCycle = "Content Addition"; 
	String nodeTargetPath = "jcr:system/exo:namespaces";	
	//By ELEMENT_SELECTED_CATEGORY_NAME = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", categoryName));
	//By ELEMENT_SELECTED_CATEGORY_CHILD_NAME = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", newCategoryName));

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
		actBar = new ActionBar(driver, this.plfVersion);
		cTemplate = new ContentTemplate(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		nav.goToSiteExplorer();
		info("Add Manage Actions button for action bar if it does not existed");
		actBar.addItem2ActionBar("manageActions", actBar.ELEMENT_MANAGE_ACTIONS_LINK);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
		//actions = null;
	}


	/**
	 *<li> Case ID:124491.</li>
	 *<li> Test Case Name: Check configuration of Add Metadata Action Template.</li>
	 *<li> Pre-Condition: - Platform is started with no added extensions
	- Selected drive at step 6 has "Manage Actions" in view</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124492.</li>
	 *<li> Test Case Name: Check configuration of Automatic Versioning Action Template.</li>
	 *<li> Pre-Condition: - Platform is started with no added extensions
	- Selected drive at step 6 has "Manage Actions" in view</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124493.</li>
	 *<li> Test Case Name: Check configuration of Enable Versioning Action Template.</li>
	 *<li> Pre-Condition: - Platform is started with no added extensions
	- Selected drive at step 6 has "Manage Actions" in view</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124494.</li>
	 *<li> Test Case Name: Check configuration of Populate to Menu Action Template.</li>
	 *<li> Pre-Condition: - Platform is started with acme extension
	- Selected drive at step 6 has "Manage Actions" in view</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124495.</li>
	 *<li> Test Case Name: Check configuration of Add Category Action Template.</li>
	 *<li> Pre-Condition: - Platform is started with no added extensions
	- View of drive has "Manage Actions"</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_03_04_05_CheckConfigurationOfAllTemplate() {
		info("Test 1: Check configuration of Add Metadata Action Template");
		/*Step Number: 1
		 *Step Name: Step 1: Show Action Templates list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> Documents 
			-> Actions tab
		 *Input Data: 

		 *Expected Outcome: 
			Actions Templates list is shown*/

		nav.goToContentAdministration();
		click(ecms.ELEMENT_TEMPLATE_DOCUMENT);
		click(ecms.ELEMENT_TEMPLATE_DOCUMENT_ACTIONS);
		info("Actions Templates list is shown");
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_TEMPLATE_DOCUMENT_ACTIONS_LIST);

		/*Step number: 2
		 *Step Name: Step 2: Check node type of Add Metadata Action
		 *Step Description: 
			- Check the list of templates
			- Check node type of Add Metadata Action template
		 *Input Data: 

		 *Expected Outcome: 
			- Add Metadata Action template appears in the list
			- Node type of Add Metadata Action is exo:addMetadataAction*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_ADDMETADATA);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_AUTOVERSIONING);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_ENABLEVERSIONING);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_POPTOMENU);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_ADDCATACTION);

		/*Step number: 3
		 *Step Name: Step 3: Check dialog name and permission
		 *Step Description: 
			- Click Edit button of Add Metadata Action row
			-Go to Dialog tab
		 *Input Data: 

		 *Expected Outcome: 
			A dialog is configured with : 
			- name : dialog1
			- permission : all (*)

		 Step number: 4
		 *Step Name: Step 4: Check View and CSS
		 *Step Description: 
			- Go to View tab
			- Go to CSS tab
		 *Input Data: 

		 *Expected Outcome: 
			- A view is configure with + name : view1+permission : all (*)
			- No Style sheet is configured*/
        info("Check Add metadata action");
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ADDMETADATA);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);
		
		info("Check Auto versioning action");
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_AUTOVERSIONING);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);

		info("Check Enable versioning action");
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ENABLEVERSIONING);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);

		info("Check Popup menu action");
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_POPTOMENU);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);

		info("Check Add category action");
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_ADDCATACTION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT);
		click(ecms.ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL);

		/*Step number: 5
		 *Step Name: Step 5: Check Add Metadata Action on Document Templates list
		 *Step Description: 
			- Go to Documents tab
			- Check items of Document Templates
		 *Input Data: 

		 *Expected Outcome: 
			Add Metadata Action is not shown in the list (it is only in Action)*/
        info("Select Document tab");
		click(ecms.ELEMENT_TEMPLATE_DOCUMENT_DOC);
		waitForElementNotPresent(ecms.ELEMENT_TEMPLATE_ACTIONS_ADDMETADATA);

		/*Step number: 6
		 *Step Name: Step 6: Check Add Metadata Action when adding action for node
		 *Step Description: 
			- Go to Administrator 
			-> Site Explorer
			- Click on Sites Managermant
			- Select a drive
			- Create a new content
			- Select node above and click Actions button
			- Go to Add Action tab
		 *Input Data: 

		 *Expected Outcome: 
			exo:addMetadataAction (Add Metadata Action) template is shown in the select box of action templates.*/ 

        String name="webct"+getRandomNumber();
		
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_ACTIONS_DRIVE);
		click(ecms.ELEMENT_ACTIONS_DRIVE_COLLABORATION);
		ecms.goToNode("sites");
		ecms.goToNode("intranet");
		ecms.goToNode("documents");
		
		info("Add a new content");
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(2000);
		
		info("The Action type exo:addMetadataAction is in the list");
		WebElement element = waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS, 5000, 0);
		if(element==null)
			click(ecms.ELEMENT_ACTIONS_MORE);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION);
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_METADATA);
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_AUTOVERSIONING);
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_VERSIONING);
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_POPULATETOMENU);

	}


}
