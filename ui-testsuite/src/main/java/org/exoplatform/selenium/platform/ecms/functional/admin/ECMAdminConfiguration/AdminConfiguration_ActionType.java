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


public class AdminConfiguration_ActionType extends PlatformBase { 

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
	 *<li> Case ID:124496.</li>
	 *<li> Test Case Name: Check configuration of Add Metadata Action Type.</li>
	 *<li> Pre-Condition: - Platform is started with no added extension
	- Selected drive at step 3 has "Manage Actions" in view</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckConfigurationOfAddMetadataActionType() {
		info("Test 1: Check configuration of Add Metadata Action Type");

		/*Step Number: 1
		 *Step Name: Step 1: Show Action Types list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Actions
		 *Input Data: 

		 *Expected Outcome: 
			The list of Action Types is shown*/

		nav.goToContentAdministration();
		click(ecms.ELEMENT_ADVANCED_FUNCTION);
		click(ecms.ELEMENT_ACTIONS_FUNCTION);
		info("The list of Action Types is shown");
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_TEMPLATE_LIST);
		/*Step number: 2
		 *Step Name: Step 2: Check Action Types list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- Action type Add Metadata appears in the list*/
        info("Action type Add Metadata appears in the list");
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_LIST_ADD_METADATA);
		
		/*Step number: 3
		 *Step Name: Step 3: Check action type when adding action for node
		 *Step Description: 
			- Go to site explorer
			- Add new content, select a drive
			- Select content above and click Action button 
			-
			-> go to Add Action tab
		 *Input Data: 

		 *Expected Outcome: 
			- The Action type exo:addMetadataAction is in the list*/ 
		
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
		
	}

	/**
	 *<li> Case ID:124497.</li>
	 *<li> Test Case Name: Check configuration of Add To Favorites Action Type.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckConfigurationOfAddToFavoritesActionType() {
		info("Test 2: Check configuration of Add To Favorites Action Type");
		/*Step Number: 1
		 *Step Name: Step 1: Show Action Types list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Actions
		 *Input Data: 

		 *Expected Outcome: 
			The list of Action Types is shown*/

		nav.goToContentAdministration();
		click(ecms.ELEMENT_ADVANCED_FUNCTION);
		click(ecms.ELEMENT_ACTIONS_FUNCTION);
		info("The list of Action Types is shown");
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_TEMPLATE_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check Action Types list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- Action Type Add To Favorites appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_ACTIONS_ADDTOFAVORITES);
		
		/*Step number: 3
		 *Step Name: Step 3: Check action type when adding action
		 *Step Description: 
			- Go to site explorer
			- Select drive Collaboration 
			-> /Users
			- Click Action button and check available action
		 *Input Data: 

		 *Expected Outcome: 
			- The action type exo:addToFavoriteAction is displayed in the list*/ 

		nav.goToSiteExplorer();
		click(ecms.ELEMENT_ACTIONS_DRIVE);
		click(ecms.ELEMENT_ACTIONS_DRIVE_COLLABORATION);
		ecms.goToNode("Users");
		
		info(" The action type exo:addToFavoriteAction is displayed in the list");
		WebElement element = waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS, 5000, 0);
		if(element==null)
			click(ecms.ELEMENT_ACTIONS_MORE);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS);
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_FAVORITES);
		
	}

	/**
	 *<li> Case ID:124498.</li>
	 *<li> Test Case Name: Check configuration of Auto Versioning Action Type.</li>
	 *<li> Pre-Condition: - Platform is started with no added extension
	- Selected drive at step 3 has "Manage Actions" in view</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CheckConfigurationOfAutoVersioningActionType() {
		info("Test 3: Check configuration of Auto Versioning Action Type");
		/*Step Number: 1
		 *Step Name: Step 1: Show Action Types list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Actions
		 *Input Data: 

		 *Expected Outcome: 
			The list of Action Types is shown*/
		
		nav.goToContentAdministration();
		click(ecms.ELEMENT_ADVANCED_FUNCTION);
		click(ecms.ELEMENT_ACTIONS_FUNCTION);
		info("The list of Action Types is shown");
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_TEMPLATE_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Action types list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- Action type Auto Versioning appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_ACTIONS_AUTOVERSIONING);
		
		/*Step number: 3
		 *Step Name: Step 3: Check action type when adding node
		 *Step Description: 
			- Go to Site Explorer
			- Select a drive
			- Add new content
			- Select content above and click Actions button
			- Go to Add Action tab
		 *Input Data: 

		 *Expected Outcome: 
			Action typeexo:autoVersioning is displayed in list*/ 
		
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
		
		info("Action typeexo:autoVersioning is displayed in list");
		WebElement element = waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS, 5000, 0);
		if(element==null)
			click(ecms.ELEMENT_ACTIONS_MORE);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION);
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_AUTOVERSIONING);
		
	}

	/**
	 *<li> Case ID:124499.</li>
	 *<li> Test Case Name: Check configuration of Enable Versioning Action Type.</li>
	 *<li> Pre-Condition: - Platform is started with no added extension
	- Selected drive at step 3 has "Manage Actions" in view</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CheckConfigurationOfEnableVersioningActionType() {
		info("Test 4: Check configuration of Enable Versioning Action Type");
		/*Step Number: 1
		 *Step Name: Step 1: Show Action Types list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Actions
		 *Input Data: 

		 *Expected Outcome: 
			The list of Action Types is shown*/

		nav.goToContentAdministration();
		click(ecms.ELEMENT_ADVANCED_FUNCTION);
		click(ecms.ELEMENT_ACTIONS_FUNCTION);
		info("The list of Action Types is shown");
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_TEMPLATE_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check Action Types list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- Action Type Enable Versioning appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_ACTIONS_ENABLEVERSIONING);
		
		/*Step number: 3
		 *Step Name: Step 3: Check action type when adding action
		 *Step Description: 
			- Go to site explorer, select a drive
			- Add new content
			- Click Action button and go to Add Action tab
		 *Input Data: 

		 *Expected Outcome: 
			- The action type exo:enableVersioning is displayed in the list*/ 
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
		
		info("The action type exo:enableVersioning is displayed in the list");
		WebElement element = waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS, 5000, 0);
		if(element==null)
			click(ecms.ELEMENT_ACTIONS_MORE);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION);
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_VERSIONING);
		
	}

	/**
	 *<li> Case ID:124500.</li>
	 *<li> Test Case Name: Check configuration of Populate To Menu Action Type.</li>
	 *<li> Pre-Condition: - Platform is started with acme extension
	- Selected drive at step 3 has "Mange Actions" in view</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_CheckConfigurationOfPopulateToMenuActionType() {
		info("Test 5: Check configuration of Populate To Menu Action Type");
		/*Step Number: 1
		 *Step Name: Step 1: Show Action Types list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Actions
		 *Input Data: 

		 *Expected Outcome: 
			The list of Action Types is shown*/

		nav.goToContentAdministration();
		click(ecms.ELEMENT_ADVANCED_FUNCTION);
		click(ecms.ELEMENT_ACTIONS_FUNCTION);
		info("The list of Action Types is shown");
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_TEMPLATE_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check Action Types list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- Action Type Populate To Menu appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_ACTIONS_POPULATETOMENU);
		
		/*Step number: 3
		 *Step Name: Step 3: Check action type when adding action
		 *Step Description: 
			- Go to site explorer, select a drive
			- Add new content
			- Click Action button and go to Add Action tab
		 *Input Data: 

		 *Expected Outcome: 
			The action type : exo:populateToMenu is displayed in the list*/ 

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
		
		info("The action type : exo:populateToMenu is displayed in the list");
		WebElement element = waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS, 5000, 0);
		if(element==null)
			click(ecms.ELEMENT_ACTIONS_MORE);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION);
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_POPULATETOMENU);
		
	}

	/**
	 *<li> Case ID:124501.</li>
	 *<li> Test Case Name: Check configuration of Trash Document Action Type.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_CheckConfigurationOfTrashDocumentActionType() {
		info("Test 6: Check configuration of Trash Document Action Type");
		/*Step Number: 1
		 *Step Name: Step 1: Show Action Types list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Actions
		 *Input Data: 

		 *Expected Outcome: 
			The list of Action Types is shown*/

		nav.goToContentAdministration();
		click(ecms.ELEMENT_ADVANCED_FUNCTION);
		click(ecms.ELEMENT_ACTIONS_FUNCTION);
		info("The list of Action Types is shown");
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_TEMPLATE_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check Action Types list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- Action Type Trash Document appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_ACTIONS_TRASHDOC);
		
		/*Step number: 3
		 *Step Name: Step 3: Check action type on Trash Folder
		 *Step Description: 
			- Go to Drive Trash
			- Click action button
			- Check available actions tab
		 *Input Data: 

		 *Expected Outcome: 
			- The action typeexo:trashFolderActionis listed*/
		
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_ACTIONS_DRIVE);
		click(ecms.ELEMENT_ACTIONS_DRIVE_TRASH);
		Utils.pause(2000);
		info("The action typeexo:trashFolderActionis listed");
		WebElement element = waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS, 5000, 0);
		if(element==null)
			click(ecms.ELEMENT_ACTIONS_MORE);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS);
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_TRASHDOCUMENT);
	}

	/**
	 *<li> Case ID:124502.</li>
	 *<li> Test Case Name: Check configuration of Add to Category Action Type.</li>
	 *<li> Pre-Condition: - Platform is started with no added extension
	- Selected drive at step 3 has "Manage Actions" in view</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_CheckConfigurationOfAddToCategoryActionType() {
		info("Test 7: Check configuration of Add to Category Action Type");
		/*Step Number: 1
		 *Step Name: Step 1: Show Action Types list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Advanced 
			-
			-> Actions
		 *Input Data: 

		 *Expected Outcome: 
			The list of Action Types is shown*/

		nav.goToContentAdministration();
		click(ecms.ELEMENT_ADVANCED_FUNCTION);
		click(ecms.ELEMENT_ACTIONS_FUNCTION);
		info("The list of Action Types is shown");
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_TEMPLATE_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check Action Types list
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- Action type Add to Categoryappears in the list*/

		waitForAndGetElement(ecms.ELEMENT_ACTIONS_ADDTOCAT);
		
		/*Step number: 3
		 *Step Name: Step 3: Check action type when adding action for content
		 *Step Description: 
			- Go to site explorer, select a drive
			- Add new content
			- Click Action button and go to Add Action tab
		 *Input Data: 

		 *Expected Outcome: 
			- The actions type exo:taxonomyAction is displayed in the select box.*/ 
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
		
		info("The actions type exo:taxonomyAction is displayed in the select box");
		WebElement element = waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS, 5000, 0);
		if(element==null)
			click(ecms.ELEMENT_ACTIONS_MORE);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS);
		click(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION);
		waitForAndGetElement(ecms.ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_TAXONOMYACTION);
	}}
