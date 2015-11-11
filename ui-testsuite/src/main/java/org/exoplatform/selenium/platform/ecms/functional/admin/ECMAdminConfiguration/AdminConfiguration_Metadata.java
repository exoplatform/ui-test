package org.exoplatform.selenium.platform.ecms.functional.admin.ECMAdminConfiguration;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
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


public class AdminConfiguration_Metadata extends PlatformBase{

	//Platform
	NavigationToolbar navBar;
	ManageAccount magAcc;
	UserGroupManagement userGroup;
	Dialog dialog;
	Button button;
	ManageTemplate magTemp;
	//Ecms
	EcmsBase ecms; 
	ActionBar actBar;
	ContextMenu cMenu;
	ContentTemplate cTemplate;
	ManageCategory magCa;
	ECMainFunction ecmMain;
	ManageAlert alert;
	//Data for these test cases
	String categoryName = "category1";
	String newCategoryName = "category2";
	String optionLifeCycle = "Content Addition"; 
	String nodeTargetPath = "jcr:system/exo:namespaces";    
	//By ELEMENT_SELECTED_CATEGORY_NAME = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", categoryName));

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
		ecmMain = new ECMainFunction(driver, this.plfVersion);
		magTemp = new ManageTemplate(driver, this.plfVersion);
		userGroup = new UserGroupManagement(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
		//actions = null;
	}		

	/**
	 *<li> Case ID:124544.</li>
	 *<li> Test Case Name: Check configuration Dublin Core metadata.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckConfigurationDublinCore() {
		info("Test 1: Check configuration Dublin Core metadata");
		/*Step Number: 1
		 *Step Name: Step 1: Show metadata list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> Metadata
		 *Input Data: 

		 *Expected Outcome: 
			- The list of metadata is shown*/

		ecmMain.goToMetadataTab();	
		
		/*Step number: 2
		 *Step Name: Step 2: Check metadata list
		 *Step Description: 
			- Check items in the list
			- Check node type of Dublin Core metadata
		 *Input Data: 

		 *Expected Outcome: 
			- The metadata Dublin Core appears in the list
			- The node type of Dublin Core is dc:elementSet*/
		waitForAndGetElement(magTemp.ELEMENT_VIEW_TEMPLATE_ICON.replace("${templateName}", "Dublin Core"));
		waitForAndGetElement(magTemp.ELEMENT_VIEW_TEMPLATE_TYPE.replace("${templateName}", "Dublin Core").replace("${type}", "dc:elementSet"));
		
		/*Step number: 3
		 *Step Name: Step 3: Check permission of metadata
		 *Step Description: 
			- Click Edit button of Dublin Core's row
			- Check information in Metadata Type tab
		 *Input Data: 

		 *Expected Outcome: 
			- Permission is: all (*)*/ 

		click(magTemp.ELEMENT_EDIT_METADATA_TEMPLATE_ICON.replace("${templateName}", "Dublin Core"));
		assert waitForAndGetElement(magTemp.ELEMENT_METADATA_PERMISSION_VALUE).getAttribute("value").equals("*");
		button.closeWindow();
	}
	
	 /*
	 *<li> Case ID:124545.</li>
	 *<li> Test Case Name: Check configuration Site Metadata.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_CheckConfigurationSiteMetadata() {
		info("Test 1: Check configuration Dublin Core metadata");
		/*Step Number: 1
		 *Step Name: Step 1: Show metadata list
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> Metadata
		 *Input Data: 

		 *Expected Outcome: 
			- The list of metadata is shown*/

		ecmMain.goToMetadataTab();	
		
		/*Step number: 2
		 *Step Name: Step 2: Check metadata list
		 *Step Description: 
			- Check items in the list
			- Check node type of Dublin Core metadata
		 *Input Data: 

		 *Expected Outcome: 
			- The metadata Site Metadata appears in the list
			- The node type of Site Metadata is metadata:siteMetadata*/
		waitForAndGetElement(magTemp.ELEMENT_VIEW_TEMPLATE_ICON.replace("${templateName}", "Site Metadata"));
		waitForAndGetElement(magTemp.ELEMENT_VIEW_TEMPLATE_TYPE.replace("${templateName}", "Site Metadata").replace("${type}", "metadata:siteMetadata"));
		
		/*Step number: 3
		 *Step Name: Step 3: Check permission of metadata
		 *Step Description: 
			- Click Edit button of Site Metadata's row
			- Check information in Metadata Type tab
		 *Input Data: 

		 *Expected Outcome: 
			- Permission is: all (*)*/ 

		click(magTemp.ELEMENT_EDIT_METADATA_TEMPLATE_ICON.replace("${templateName}", "Site Metadata"));
		assert waitForAndGetElement(magTemp.ELEMENT_METADATA_PERMISSION_VALUE).getAttribute("value").equals("*");
		button.closeWindow();
	}
}
