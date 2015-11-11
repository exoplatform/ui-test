package org.exoplatform.selenium.platform.ecms.functional.admin.ECMAdminConfiguration;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.testng.annotations.*;


public class AdminConfiguration_Namespaces extends PlatformBase {

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
	ECMainFunction ecmMain;

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
		ecmMain = new ECMainFunction(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
	}

	/**
	 *<li> Case ID:124549.</li>
	 *<li> Test Case Name: Check configuration of exo namespace.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124562.</li>
	 *<li> Test Case Name: Check configuration of wai namespace.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:124563.</li>
	 *<li> Test Case Name: Check configuration of acme namespace (acme site).</li>
	 *<li> Pre-Condition: acme extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_02_03_CheckConfigurationOfExo_Wai_AcmeNamespace() {
		info("Test 1: Check configuration of exo namespace");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Repository 
			-
			-> Namespaces
		 *Input Data: 

		 *Expected Outcome: 
			- The list of namespaces is shown*/
		ecmMain.goToNamespacesTab();

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The namespace exo appears in the list*/

		select(ecms.ELEMENT_TEMPLATE_DOCS_RESULTSPERPAGE, "60", 2);
		waitForAndGetElement(ecms.ELEMENT_CA_REPOSITORY_NAMESPACES_EXO);
		waitForAndGetElement(ecms.ELEMENT_CA_REPOSITORY_NAMESPACES_WAI);
		waitForAndGetElement(ecms.ELEMENT_CA_REPOSITORY_NAMESPACES_ACME);

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Check URI of exo namespace
		 *Input Data: 

		 *Expected Outcome: 
			URI is : http://www.exoplatform.com/jcr/exo/1.0*/

		waitForAndGetElement(ecms.ELEMENT_CA_REPOSITORY_NAMESPACES_EXO_URL);
		waitForAndGetElement(ecms.ELEMENT_CA_REPOSITORY_NAMESPACES_WAI_URL);
		waitForAndGetElement(ecms.ELEMENT_CA_REPOSITORY_NAMESPACES_ACME_URL);

		/*Step number: 4
		 *Step Name: 
		 *Step Description: 
			- Go to Site Explorer
			- Create new content
			- In Admin view, click Properties button and go to tab Add New Properties
		 *Input Data: 

		 *Expected Outcome: 
			- Check that exo is in the list of properties*/ 

		click(ecms.ELEMENT_CA_EXPLORER);
		click(ecms.ELEMENT_CA_EXPLORER_DRIVES);
		click(ecms.ELEMENT_CA_EXPLORER_DRIVES_EDIT_MGDSITES);
		click(ecms.ELEMENT_CA_EXPLORER_DRIVES_EDIT_MGDSITES_APPLYVIEWS);
		check(ecms.ELEMENT_CA_EXPLORER_DRIVES_EDIT_MGDSITES_APPLYVIEWS_ADMIN, 2);
		button.save();
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		click(ecms.ELEMENT_SE_MORE_PROPERTIES);
		click(ecms.ELEMENT_SE_MORE_PROPERTIES_ADDPROPERTY);
		waitForAndGetElement(ecms.ELEMENT_SE_MORE_PROPERTIES_ADDPROPERTY_NAMESPACE_EXO);
		waitForAndGetElement(ecms.ELEMENT_SE_MORE_PROPERTIES_ADDPROPERTY_NAMESPACE_WAI);
		waitForAndGetElement(ecms.ELEMENT_SE_MORE_PROPERTIES_ADDPROPERTY_NAMESPACE_ACME);
	}

	/**
	 *<li> Case ID:124606.</li>
	 *<li> Test Case Name: Check configuration of acme namespace when acme extension is not installed.</li>
	 *<li> Pre-Condition: acme extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test04_CheckConfigurationOfAcmeNamespaceWhenAcmeExtensionIsNotInstalled() {
		info("Test 4: Check configuration of acme namespace when acme extension is not installed");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Repository 
			-
			-> Namespaces
		 *Input Data: 

		 *Expected Outcome: 
			- The list of namespaces is shown*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			- The namespace acme does not appear in the list*/

		/*Step number: 3
		 *Step Name: 
		 *Step Description: 
			- Go to Site Explorer<br />
			- Create new content<br />
			- In Admin view, click Properties button and go to tab Add New Properties<br />
		 *Input Data: 

		 *Expected Outcome: 
			- acme does not appear in the list of properties*/ 
	}
}
