package org.exoplatform.selenium.platform.ecms.functional.admin.ECMAdminConfiguration;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;


public class AdminConfiguration_CLVTemplates extends PlatformBase {

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
	//By ELEMENT_SELECTED_CATEGORY_NAME = By.xpath(cMenu.ELEMENT_FILE_TITLE.replace("${titleOfFile}", categoryName));

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
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout ECMS");
		driver.quit();
		//actions = null;
	}


	/**
	 *<li> Case ID:124504.</li>
	 *<li> Test Case Name: Check configuration of Acme Big Hot News CLV Templates (acme site).</li>
	 *<li> Pre-Condition: Acme extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckConfigurationOfAcmeBigHotNewsCLVTemplates() {
		info("Test 1: Check configuration of Acme Big Hot News CLV Templates (acme site)");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

	    nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);

		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template name : Acme Big Hot News template appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ACMEBHN);

		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template name of Acme Big Hot News
		 *Input Data: 

		 *Expected Outcome: 
			Template name of Acme Big Hot News is: AcmeHotBigNews.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ACMEBHN_NAME);

		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name: content_list + Workspace: dms
			-system + Permission : Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- Drive is created*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);

		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/list
		 *Input Data: 

		 *Expected Outcome: 
			- Acme Big Hot News is displayed
			- Acme Big Hot News template is AcmeBigHotNews.gtmpl*/ 

		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}",nameDrive));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/list", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ACMEBHN);
	}

	/**
	 *<li> Case ID:124505.</li>
	 *<li> Test Case Name: Check Acme Big Hot News template is not configured out-of-the-box.</li>
	 *<li> Pre-Condition: Platform is started with no acme extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test02_CheckAcmeBigHotNewsTemplateIsNotConfiguredOut_of_the_box() {
		info("Test 2: Check Acme Big Hot News template is not configured out-of-the-box");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check list template
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template Acme Big Hot News does not appears in the list*/ 

	}

	/**
	 *<li> Case ID:124506.</li>
	 *<li> Test Case Name: Check configuration of Acme Powers CLV Templates (acme site).</li>
	 *<li> Pre-Condition: Acme extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CheckConfigurationOfAcmePowersCLVTemplates() {
		info("Test 3: Check configuration of Acme Powers CLV Templates (acme site)");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

	     nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);

		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template name : Acme Powers template appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ACMEPOWER);

		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template name of Acme Powers
		 *Input Data: 

		 *Expected Outcome: 
			Template is: AcmePowers.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ACMEPOWER_NAME);

		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name: content_list + Workspace: dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);

		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/list
		 *Input Data: 

		 *Expected Outcome: 
			- Acme Powers is displayed in the list
			- Acme Powers template is AcmePowers.gtmpl*/ 
		nav.goToSiteExplorer();
		
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}", nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/list", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ACMEPOWER);
	}

	/**
	 *<li> Case ID:124507.</li>
	 *<li> Test Case Name: Check configuration of Accessible Banner template (wai portal).</li>
	 *<li> Pre-Condition: wai extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CheckConfigurationOfAccessibleBannerTemplate() {
		info("Test 4: Check configuration of Accessible Banner template (wai portal)");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			- The List template is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);

		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Banner template appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_BANNER);

		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template name of the Accessible Bannertemplate.
		 *Input Data: 

		 *Expected Outcome: 
			Template of Accessible Banner is: AccessibleBanner.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_BANNER_NAME);

		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name: content_list+ Workspace: dms
			-system workspace + Permission: any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME,nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);

		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/list
		 *Input Data: 

		 *Expected Outcome: 
			- Accessible Banner is in the list of templates
			- Accessible Banner template is AccessibleBanner.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}",nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/list", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_BANNER);
	}

	/**
	 *<li> Case ID:124508.</li>
	 *<li> Test Case Name: Check configuration of Accessible Breadcrumb template (wai portal).</li>
	 *<li> Pre-Condition: wai extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_CheckConfigurationOfAccessibleBreadcrumbTemplate() {
		info("Test 5: Check configuration of Accessible Breadcrumb template (wai portal)");
		/*Step Number: 1
		 *Step Name: Step 1: Show List template
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			The List templates is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);

		/*Step number: 2
		 *Step Name: Step 2: Check List Template
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Breadcrumb template appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_BREADCRUMB);

		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template value of Accessible Breadcrumb template
		 *Input Data: 

		 *Expected Outcome: 
			Template of Accessible Breadcrumb is: AccessibleBreadcrumb.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_BREADCRUMB_NAME);

		/*Step number: 4
		 *Step Name: Step 4: Create new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name content_list + Workspace: dms
			-system+ Permission: any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);

		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/list
		 *Input Data: 

		 *Expected Outcome: 
			- The Accessible Breadcrumb template is displayed in the list
			- The Accessible Breadcrumb template is AccessibleBreadcrumb.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}", nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/list", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_BREADCRUMB);
	}

	/**
	 *<li> Case ID:124509.</li>
	 *<li> Test Case Name: Check configuration of Accessible Sitemap template (wai portal).</li>
	 *<li> Pre-Condition: wai extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_CheckConfigurationOfAccessibleSitemapTemplate() {
		info("Test 6: Check configuration of Accessible Sitemap template (wai portal)");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			The list of templates is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);

		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Sitemap template appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_SITEMAP);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template value of Accessible Sitemap template
		 *Input Data: 

		 *Expected Outcome: 
			Template of Accessible Sitemap is: AccessibleSitemap.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_SITEMAP_NAME);
		
		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click Add Drive button
			- Create a new drive:+ Name: content_list+ Workspace: dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);
		
		/*Step number: 5
		 *Step Name: Step 5: Check template on drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/list
		 *Input Data: 

		 *Expected Outcome: 
			- The Accessible Sitemap template is displayed in the list
			- Accessible Sitemap template is AccessibleSitemap.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}",nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/list", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_SITEMAP);
		
	}

	/**
	 *<li> Case ID:124510.</li>
	 *<li> Test Case Name: Check configuration of Accessible Toolbar template (wai portal).</li>
	 *<li> Pre-Condition: wai extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_CheckConfigurationOfAccessibleToolbarTemplate() {
		info("Test 7: Check configuration of Accessible Toolbar template (wai portal)");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			The list of templates is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Toolbar template appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_TOOLBAR);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template value of Accessible Toolbar template
		 *Input Data: 

		 *Expected Outcome: 
			Template name of Accessible Toolbar is: AccessibleToolbar.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_TOOLBAR_NAME);
		
		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click Add Drive button
			- Create a new drive:+ Name: content_list+ Workspace: dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);
		
		/*Step number: 5
		 *Step Name: Step 4: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/list
		 *Input Data: 

		 *Expected Outcome: 
			- The Accessible Toolbar is displayed in the list of templates
			- The Accessible Toolbar template is AccessibleToolbar.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}", nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/list", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_TOOLBAR);
	}

	/**
	 *<li> Case ID:124511.</li>
	 *<li> Test Case Name: Check configuration of Announcement Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test08_CheckConfigurationOfAnnouncementTemplate() {
		info("Test 8: Check configuration of Announcement Template");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template name : Announcement appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ANNOUNCMENT);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template name of Announcement
		 *Input Data: 

		 *Expected Outcome: 
			Template is: Announcement.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ANNOUNCMENT_NAME);
		
		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name: content_list+ Workspace: dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME,nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);
		
		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/list
		 *Input Data: 

		 *Expected Outcome: 
			- Announcement is displayed in the list of template
			-Announcement template is Announcement.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}",nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/list", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ANNOUNCMENT);
	}

	/**
	 *<li> Case ID:124512.</li>
	 *<li> Test Case Name: Check configuration of Documents Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test09_CheckConfigurationOfDocumentsTemplate() {
		info("Test 9: Check configuration of Documents Template");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template name : Documents appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_DOCUMENT);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template value of Documents
		 *Input Data: 

		 *Expected Outcome: 
			Template is: Documents.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_DOCUMENT_NAME);
		
		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name content_list+ Workspace: dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is shown*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);
		
		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/list
		 *Input Data: 

		 *Expected Outcome: 
			- Documents is displayed in the list of template
			- Documents template is Documents.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}", nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/list", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_DOCUMENT);
	}

	/**
	 *<li> Case ID:124513.</li>
	 *<li> Test Case Name: Check configuration of One Column Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test10_CheckConfigurationOfOneColumnTemplate() {
		info("Test 10 Check configuration of One Column Template");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template name : One Column appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ONECOL);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template name of One Column
		 *Input Data: 

		 *Expected Outcome: 
			Template is: OneColumn.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ONECOL_NAME);
		
		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name: content_list+ Workspace: dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);
		
		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/list
		 *Input Data: 

		 *Expected Outcome: 
			- One Column is displayed in the list of templates
			- One Column template is OneColumn.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}",nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/list", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_ONECOL);
	}

	/**
	 *<li> Case ID:124514.</li>
	 *<li> Test Case Name: Check configuration of Two Columns Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test11_CheckConfigurationOfTwoColumnsTemplate() {
		info("Test 11 Check configuration of Two Columns Template");
		/*Step Number: 1
		 *Step Name: Step 1: Show List template
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check List template
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template name : Two Columns appears in the list*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_TWOCOL);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template value of Two Columns
		 *Input Data: 

		 *Expected Outcome: 
			Template is: TwoColumns.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_TWOCOL);
		
		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name: content_list + Workspace:dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- Drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);
		
		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/list
		 *Input Data: 

		 *Expected Outcome: 
			- Two Columns is displayed in the list of templates
			- Two Columns template is TwoColumns.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}", nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/list", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_TWOCOL);
	}

	/**
	 *<li> Case ID:124515.</li>
	 *<li> Test Case Name: Check Acme Powers template is not configured out-of-the-box.</li>
	 *<li> Pre-Condition: Platform is started without acme extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test12_CheckAcmePowersTemplateIsNotConfiguredOut_of_the_box() {
		info("Test 12 Check Acme Powers template is not configured out-of-the-box");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template Acme Powers does not appears in the list*/ 

	}

	/**
	 *<li> Case ID:124516.</li>
	 *<li> Test Case Name: Check configuration of Accessible Navigation template (wai portal).</li>
	 *<li> Pre-Condition: wai extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test13_CheckConfigurationOfAccessibleNavigationTemplate() {
		info("Test 13 Check configuration of Accessible Navigation template (wai portal)");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			- The list of templates if shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);

		
		/*Step number: 2
		 *Step Name: Step 2: Check List template
		 *Step Description: 
			- Check items in the list 
			-> Navigation tab
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Navigation template appears in the list*/

		click(ecms.ELEMENT_TEMPLATE_LIST_NAVIGATION);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_NAVIG);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template value of the Accessible Navigation
		 *Input Data: 

		 *Expected Outcome: 
			Template of Accessible Navigation is: AccessibleNavigation.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_NAVIG_NAME);
		
		/*Step number: 4
		 *Step Name: Step 4: Create new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive :+ Name: content_list on + Workspace: dms
			-system + Permission : Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);

		
		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/navigation
		 *Input Data: 

		 *Expected Outcome: 
			- Accessible Navigationis displayed is the templates list
			- Accessible Navigation template is AccessibleNavigation.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}", nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/navigation", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_NAVIG);
	}

	/**
	 *<li> Case ID:124517.</li>
	 *<li> Test Case Name: Check configuration of Acme Powers Category Tree Templates (acme site).</li>
	 *<li> Pre-Condition: Acme add
	-on must have been installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test14_CheckConfigurationOfAcmePowersCategoryTreeTemplates() {
		info("Test 14 Check configuration of Acme Powers Category Tree Templates (acme site)");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			Check item in the list 
			-
			-> Navigation tab
		 *Input Data: 

		 *Expected Outcome: 
			The template name : Acme Powers Category Tree appears in the list*/

		click(ecms.ELEMENT_TEMPLATE_LIST_NAVIGATION);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_CATTREE);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template name of Acme Powers
		 *Input Data: 

		 *Expected Outcome: 
			- Template name is : AcmePowersCategoryTree.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_CATTREE_NAME);
		
		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name: content_list+ Workspace: dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME,nameDrive , true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);
		
		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/navigation
		 *Input Data: 

		 *Expected Outcome: 
			- Acme Powers Category Tree is displayed in the list of templates
			- Acme Powers Category Tree template is AcmePowersCategoryTree.gtmpl*/ 

		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}",nameDrive )));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/navigation", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_CATTREE);
		
	}

	/**
	 *<li> Case ID:124518.</li>
	 *<li> Test Case Name: Check configuration of Category List Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test15_CheckConfigurationOfCategoryListTemplate() {
		info("Test 15 Check configuration of Category List Template");
		/*Step Number: 1
		 *Step Name: Step 1: Check List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check item in the list 
			-> Navigation tab
		 *Input Data: 

		 *Expected Outcome: 
			The template name : Category List appears in the list*/

		click(ecms.ELEMENT_TEMPLATE_LIST_NAVIGATION);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_CATLIST);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template value of Category List
		 *Input Data: 

		 *Expected Outcome: 
			The name of the template is CategoryList.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_CATLIST_NAME);
		
		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name: content_list + Workspace: dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);
		
		/*Step number: 5
		 *Step Name: Step 5: Check template on drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/navigation
		 *Input Data: 

		 *Expected Outcome: 
			- The Category List is displayed in the template
			- Category List template is CategoryList.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}", nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/navigation", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_CATLIST);
	}

	/**
	 *<li> Case ID:124519.</li>
	 *<li> Test Case Name: Check configuration of Default Paginator Template.</li>
	 *<li> Pre-Condition: Platform is started with no added extension</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test16_CheckConfigurationOfDefaultPaginatorTemplate() {
		info("Test 16 Check configuration of Default Paginator Template");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
			- Go to paginator tab
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template name : Default Paginator appears in the list*/

		click(ecms.ELEMENT_TEMPLATE_LIST_PAGINATION);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_DEFPAG);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template value of Default Paginator template
		 *Input Data: 

		 *Expected Outcome: 
			Template name is: DefaultPaginator.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_DEFPAG_NAME);
		
		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name: content_list+ Workspace:dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);
		
		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/paginators
		 *Input Data: 

		 *Expected Outcome: 
			- Default Paginator is displayed in the list of templates
			- Default Paginatortemplate is DefaultPaginator.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}", nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/paginators", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_DEFPAG);
	}

	/**
	 *<li> Case ID:124520.</li>
	 *<li> Test Case Name: Check configuration of No Pagination Template (acme site).</li>
	 *<li> Pre-Condition: acme extension must have been installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test17_CheckConfigurationOfNoPaginationTemplate() {
		info("Test 17 Check configuration of No Pagination Template (acme site)");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
			- Go to paginator tab
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		nav.goToContentAdministration();
	    click(ecms.ELEMENT_TEMPLATE_LIST);
		info("List templates list is shown");
		waitForAndGetElement(ecms. ELEMENT_ACTIONS_TEMPLATE_LIST_LIST);
		
		/*Step number: 2
		 *Step Name: Step 2: Check List template
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template name : No Pagination appears in the list*/

		click(ecms.ELEMENT_TEMPLATE_LIST_PAGINATION);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_NOPAG);
		
		/*Step number: 3
		 *Step Name: Step 3: Check template name
		 *Step Description: 
			- Check template name of No Pagination template
		 *Input Data: 

		 *Expected Outcome: 
			Template is:EmptyPaginator.gtmpl*/

		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_NOPAG_NAME);
		
		/*Step number: 4
		 *Step Name: Step 4: Add new drive
		 *Step Description: 
			- Still in Content Configuration, Go to Explorer 
			-> Drives
			- Click [Add Drive] button
			- Create a new drive:+ Name: content_list + Workspace: dms
			-system + Permission: Any+ Select Admin view
		 *Input Data: 

		 *Expected Outcome: 
			- The drive is added*/
		String nameDrive = "Drive"+getRandomNumber();
		nav.goToContentAdministration();
		click(ecms.ELEMENT_EXPLORER);
		click(ecms.ELEMENT_EXPLORER_DRIVES);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME, nameDrive, true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE);
		click(ecms.ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM);
		type(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION , "*", true);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW);
		check(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX, 2);
		click(ecms.ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE);
		
		/*Step number: 5
		 *Step Name: Step 5: Check template on new drive
		 *Step Description: 
			- Go to Site Explorer 
			-> Drive content_list
			- Enter the path: /exo:ecm/views/templates/content
			-list
			-viewer/paginators
		 *Input Data: 

		 *Expected Outcome: 
			- No Pagination is displayed in the list of templates<br />
			- No Pagination template is EmptyPaginator.gtmpl*/ 
		nav.goToSiteExplorer();
		click(ecms.ELEMENT_SE_SHOWDRIVES);
		click(By.xpath(ecms.ELEMENT_SE_SHOWDRIVES_DRIVE.replace("${name}", nameDrive)));
		type(ecms.ELEMENT_SE_DRIVES_PATH , "/exo:ecm/views/templates/content-list-viewer/paginators", true);
		driver.findElement(ecms.ELEMENT_SE_DRIVES_PATH).sendKeys(Keys.ENTER);
		waitForAndGetElement(ecms.ELEMENT_TEMPLATE_LIST_NOPAG);
	}

	/**
	 *<li> Case ID:124585.</li>
	 *<li> Test Case Name: Check Accessible Banner template when wai extension is not installed.</li>
	 *<li> Pre-Condition: wai extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test18_CheckAccessibleBannerTemplateWhenWaiExtensionIsNotInstalled() {
		info("Test 18 Check Accessible Banner template when wai extension is not installed");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			- The List template is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Banner template does not appear in the list*/ 

	}

	/**
	 *<li> Case ID:124586.</li>
	 *<li> Test Case Name: Check Accessible Breadcrumb template when wai extension is not installed.</li>
	 *<li> Pre-Condition: wai extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test19_CheckAccessibleBreadcrumbTemplateWhenWaiExtensionIsNotInstalled() {
		info("Test 19 Check Accessible Breadcrumb template when wai extension is not installed");
		/*Step Number: 1
		 *Step Name: Step 1: Show List template
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			The List templates is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check List Template
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Breadcrumb template does not appear in the list*/ 

	}

	/**
	 *<li> Case ID:124587.</li>
	 *<li> Test Case Name: Check Accessible Navigation template when wai extension is not installed.</li>
	 *<li> Pre-Condition: wai extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test20_CheckAccessibleNavigationTemplateWhenWaiExtensionIsNotInstalled() {
		info("Test 20 Check Accessible Navigation template when wai extension is not installed");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			- The list of templates if shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check List template
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Navigation template does not appear in the list*/ 

	}

	/**
	 *<li> Case ID:124588.</li>
	 *<li> Test Case Name: Check Accessible Sitemap template when wai extension is not installed.</li>
	 *<li> Pre-Condition: wai extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test21_CheckAccessibleSitemapTemplateWhenWaiExtensionIsNotInstalled() {
		info("Test 21 Check Accessible Sitemap template when wai extension is not installed");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			The list of templates is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Sitemap template does not appear in the list*/ 

	}

	/**
	 *<li> Case ID:124589.</li>
	 *<li> Test Case Name: Check Accessible Toolbar template when wai extension is not installed.</li>
	 *<li> Pre-Condition: wai extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test22_CheckAccessibleToolbarTemplateWhenWaiExtensionIsNotInstalled() {
		info("Test 22 Check Accessible Toolbar template when wai extension is not installed");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			The list of templates is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check items in the list
		 *Input Data: 

		 *Expected Outcome: 
			Accessible Toolbar template does not appears in the list*/ 

	}

	/**
	 *<li> Case ID:124590.</li>
	 *<li> Test Case Name: Check No Pagination Template when acme extension is not installed.</li>
	 *<li> Pre-Condition: acme extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test23_CheckNoPaginationTemplateWhenAcmeExtensionIsNotInstalled() {
		info("Test 23 Check No Pagination Template when acme extension is not installed");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
			- Go to paginator tab
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check List template
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template name : No Pagination does not appear in the list*/ 

	}

	/**
	 *<li> Case ID:124591.</li>
	 *<li> Test Case Name: Check Acme Powers Category Tree Templates when acme extension is not installed.</li>
	 *<li> Pre-Condition: Acme extension is not installed</li>
	 *<li> Post-Condition: </li>
	 */
	@Test(groups="Pending")
	public  void test24_CheckAcmePowersCategoryTreeTemplatesWhenAcmeExtensionIsNotInstalled() {
		info("Test 24 Check Acme Powers Category Tree Templates when acme extension is not installed");
		/*Step Number: 1
		 *Step Name: Step 1: Show List templates
		 *Step Description: 
			- Login as administrator
			- Go to Content Administration
			- Click Templates 
			-
			-> List
		 *Input Data: 

		 *Expected Outcome: 
			List templates list is shown*/

		/*Step number: 2
		 *Step Name: Step 2: Check List templates
		 *Step Description: 
			- Check item in the list
		 *Input Data: 

		 *Expected Outcome: 
			The template name : Acme Powers Category Tree does not appear in the list*/ 

	}}
