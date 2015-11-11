package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.ckeditor;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.event.KeyEvent;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.CKeditor;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


public class ECMS_SE_CKEditor_PublishActivity extends PlatformBase {


	//Platform
	NavigationToolbar navToolBar;
	ActionBar actBar;
	ManageAccount magAcc;
	HomePageActivity hpActivity;

	//Ecms
	EcmsBase ecms;
	ContentTemplate cTemplate;
	ContextMenu cMenu;
	SitesExplorer siteExp;
	CKeditor ckEdit;

	//Define data
	public String DATA_CONTENT_FOLDER = "contentfolder";
	public String DATA_DOCUMENT_FOLDER = "documentfolder";
	public String DATA_FILE_NAME = "filename";
	public String DATA_UPLOAD_FILE_PATH ="TestData/Winter.jpg";
	//public String ELEMENT_TITLE = "//*[@title='${DATA}']";	

	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		driver.get(plfURL);
		navToolBar = new NavigationToolbar(driver,this.plfVersion);
		magAcc = new ManageAccount(driver,this.plfVersion);
		actBar = new ActionBar(driver,this.plfVersion);
		ecms = new EcmsBase(driver,this.plfVersion);
		cTemplate = new ContentTemplate(driver,this.plfVersion);
		cMenu = new ContextMenu(driver,this.plfVersion);
		siteExp = new SitesExplorer(driver,this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		ckEdit = new CKeditor(driver);
		hpActivity = new HomePageActivity(driver);
	}

	@AfterMethod()
	public void afterTest()
	{
		//logoutEcms();
		driver.quit();
	}

	/**
	 *<li> Case ID:119899.</li>
	 *<li> Test Case Name: Edit a web content from activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:119906.</li>
	 *<li> Test Case Name: View a web content from activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_08_Edit_ViewAWebContentFromActivityStream() {
		info("Test 1: Edit a web content from activity stream");

		String name="node_"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Choose web contenttemplate
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choose web content
			- Fill Name/ title
		 *Input Data: 

		 *Expected Outcome: 
			web content template is chosen & filled name/title*/
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();


		/*Step number: 2
		 *Step Name: Fill content in Main content
		 *Step Description: 
			- Fill content in main content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is filled & decorated*/

		By eWebContentSum;
		eWebContentSum = cTemplate.ELEMENT_WEBCONTENT_SUMMARY_FRAME_41;

		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name,true);
		switchToParentWindow();
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);



		/*Step number: 3
		 *Step Name: Fill content in Summary
		 *Step Description: 
			- Move to illustration tab
			- Fill content for summary field. Use some features of editor to decorate this summary
		 *Input Data: 

		 *Expected Outcome: 
			Summary is filled & decorated*/

		click(cTemplate.ELEMENT_ILLUSTRATION_TAB);
		Utils.pause(3000);
		inputDataToFrame(eWebContentSum, name);
		switchToParentWindow();
		click(ckEdit.ELEMENT_CKEDITOR_INCREASE_INDENT);

		/*Step number: 4
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- New web content is created successfully
			- Summary and Content are displayed as decorated.*/

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_ALIGNRIGHT);		
		Utils.pause(3000);

		/*Step number: 5
		 *Step Name: Edit wcfrom intranet homepage
		 *Step Description: 
			- Go to intranet home page
			- Click [Edit] icon of this wc
			- Edit something and decorate it
			- Save
			- Click Back icon to come back homepage
		 *Input Data: 

		 *Expected Outcome: 
			- Wc is opened in site explorer with CKeditor is well displayed.<br />
			- Activity is well shown.*/
		navToolBar.goToHomePage();
		click(hpActivity.ELEMENT_FILE_EDIT.replace("${title}", name));
		//		actBar.goToEditDocument(title);
		click(ckEdit.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		click(ckEdit.ELEMENT_CKEDITOR_BOLD);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		driver.navigate().back();
		click(hpActivity.ELEMENT_FILE_VIEW.replace("${title}", name));
		//		waitForAndGetElement(siteExp.ELEMENT_CONTENT_STRONG.replace("${title}", title));
	}

	/**
	 *<li> Case ID:119900.</li>
	 *<li> Test Case Name: Edit an illustrated web content from activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:119907.</li>
	 *<li> Test Case Name: View an illustrated web content from activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *check issue https://jira.exoplatform.org/browse/ECMS-6742
	 */
	@Test(groups="PENDING")
	public  void test02_09_Edit_ViewAnIllustratedWebContentFromActivityStream() {
		info("Test 2: Edit an illustrated web content from activity stream");

		String name="node_"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Choose Illustrated web content template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choose Illustrated web content
			- Fill Name/ title
		 *Input Data: 

		 *Expected Outcome: 
			Illustrated web content template is chosen & filled name/title*/
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_HEAD_LAYOUT_LINK);
		type(cTemplate.ELEMENT_HEAD_LAYOUT_NAME_TEXTBOX, name, false);
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name);
		switchToParentWindow();
		/*Step number: 2
		 *Step Name: Fill content in Main content
		 *Step Description: 
			- Fill content in main content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is filled & decorated*/

		click(ckEdit.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		click(ckEdit.ELEMENT_CKEDITOR_BOLD);

		/*Step number: 3
		 *Step Name: Fill content in Summary
		 *Step Description: 
			- Move to illustration tab
			- Fill content for summary field. Use some features of editor to decorate this summary
		 *Input Data: 

		 *Expected Outcome: 
			Summary is filled & decorated*/

		click(cTemplate.ELEMENT_ILLUSTRATION_TAB);
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_SUMMARY_FRAME_41, name);
		switchToParentWindow();

		/*Step number: 4
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- New Illustrated web contentis created successfully
			- Summary and Content are displayed as decorated.*/

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);

		/*Step number: 5
		 *Step Name: Edit Illustrated web content from intranet homepage
		 *Step Description: 
			- Go to intranet home page
			- Click [Edit] icon of this Illustrated web content
			- Edit something and decorate it
			- Save
			- Click Back icon to come back homepage
		 *Input Data: 

		 *Expected Outcome: 
			- Illustrated web content is opened in site explorer with CKeditor is well displayed.
			- Activity is well shown.*/ 
		navToolBar.goToHomePage();
		click(hpActivity.ELEMENT_FILE_EDIT.replace("${title}", name));
		//		actBar.goToEditDocument(name);
		click(ckEdit.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		click(ckEdit.ELEMENT_CKEDITOR_ITALIC);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		driver.navigate().back();
		click(hpActivity.ELEMENT_FILE_VIEW.replace("${title}", name));
		//		waitForAndGetElement(siteExp.ELEMENT_CONTENT_STRONG.replace("${title}", name));
	}

	/**
	 *<li> Case ID:119901.</li>
	 *<li> Test Case Name: Edit an accessible media from activity stream.</li>
	 *<li> Pre-Condition: Package is started with all extensions</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:119908.</li>
	 *<li> Test Case Name: View an accessible media from activity stream.</li>
	 *<li> Pre-Condition: Package is started with all extensions</li>
	 *<li> Post-Condition: </li>
	 */
	@Test (groups="pending")
	public  void test03_10_Edit_ViewAnAccessibleMediaFromActivityStream() {
		info("Test 3: Edit an accessible media from activity stream");

		String name="node_"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: ChooseAccessible media template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choose Accessible media
			- Fill Name
		 *Input Data: 

		 *Expected Outcome: 
			Accessible media template is chosen & filled name*/
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_ACCESSIBLEMEDIA_LINK);
		type(cTemplate.ELEMENT_MAIN_TAB_NAME, name, true);
		switchToParentWindow();

		/*Step number: 2
		 *Step Name: Fill content in Alternative text
		 *Step Description: 
			- Fill content in Alternative text, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Alternative text is filled & decorated*/
	
		Utils.pause(3000);
		//inputDataToFrame(cTemplate.ELEMENT_ACCESSIBLE_MEDIA_ALTERNATIVE_FRAME, name, true);
		cTemplate.inputFrame(cTemplate.ELEMENT_ACCESSIBLE_MEDIA_ALTERNATIVE_FRAME, name);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ckEdit.ELEMENT_CKEDITOR_INCREASE_INDENT);
		switchToParentWindow();

		/*Step number: 3
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- New Accessible media is created successfully
			- Alternative text is displayed as decorated.*/

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_INDENT);
		/*Step number: 4
		 *Step Name: Edit Acc media from intranet homepage
		 *Step Description: 
			- Go to intranet home page
			- Click [Edit] icon of this accessible media
			- Edit something and decorate it
			- Save
			- Click Back icon to come back homepage
		 *Input Data: 

		 *Expected Outcome: 
			- Accessible media is opened in site explorer with CKeditor is well displayed.
			- Activity is well shown.*/
		navToolBar.goToHomePage();
		click(hpActivity.ELEMENT_FILE_EDIT.replace("${title}", name));
		//		actBar.goToEditDocument(name);
		click(ckEdit.ELEMENT_CKEDITOR_DECREASE_INDENT);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		driver.navigate().back();
		click(hpActivity.ELEMENT_FILE_VIEW.replace("${title}", name));
		//		waitForAndGetElement(siteExp.ELEMENT_TEXT.replace("{$node}", name));
	}

	/**
	 *<li> Case ID:119902.</li>
	 *<li> Test Case Name: Edit a File with text/HTML from activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:119909.</li>
	 *<li> Test Case Name: View a File with text/HTML from activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 */
	@Test
	public  void test04_11_Edit_ViewAFileWithTextHTMLFromActivityStream() {
		info("Test 4: Edit a File with text/HTML from activity stream");

		String name="node_"+getRandomNumber();
		String mimeType = ("text/html");

		/*Step Number: 1
		 *Step Name: ChooseFiletemplate
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choose File
			- Fill Name
			- Choose Mime Type as text/html
		 *Input Data: 

		 *Expected Outcome: 
			File template is chosen & filled name with Mime Type is text/html*/
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_NEWFILE_LINK);
		type(cTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX, name, false);
		selectOption(cTemplate.ELEMENT_NEWFILE_MIME_COMBOX_ID, mimeType);

		/*Step number: 2
		 *Step Name: Fill content in Content field
		 *Step Description: 
			- Fill content in Content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is filled & decorated*/
		cTemplate.inputFrame(cTemplate.ELEMENT_NEWFILE_CONTENT_FRAME_41, name);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		//inputDataToFrame(cTemplate.ELEMENT_NEWFILE_CONTENT_FRAME_41, name, false);
		switchToParentWindow();
		

		/*Step number: 3
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- New file is created successfully
			- Content is displayed as decorated.*/

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		Object framelocator = "//iframe[@class='ECMIframe']";
		WebElement e = waitForAndGetElement(framelocator,DEFAULT_TIMEOUT,1,2);
		driver.switchTo().frame(e);
//		waitForAndGetElement(siteExp.ELEMENT_CONTENT_ALIGNRIGHT_INFRAME.replace("${name}", name));	
		switchToParentWindow();
		
		/*Step number: 4
		 *Step Name: Edit File with application/x
		-groovy+htmlfrom intranet homepage
		 *Step Description: 
			- Go to intranet home page
			- Click [Edit] icon of this File with text/HTML
			- Edit something and decorate it
			- Save
			- Click Back icon to come back homepage
		 *Input Data: 

		 *Expected Outcome: 
			- File with text/HTMis opened in site explorer with CKeditor is well displayed.
			- Activity is well shown.*/ 
		navToolBar.goToHomePage();
		click(hpActivity.ELEMENT_FILE_EDIT.replace("${title}", name));
		//		actBar.goToEditDocument(name);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNLEFT);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		driver.navigate().back();
		click(hpActivity.ELEMENT_FILE_VIEW.replace("${title}", name));
		//		waitForAndGetElement(siteExp.ELEMENT_TEXT.replace("{$node}", name));

	}

	/**
	 *<li> Case ID:119903.</li>
	 *<li> Test Case Name: Edit a File with application/x-groovy+html from activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:119910.</li>
	 *<li> Test Case Name: View a File with application/x-groovy+html from activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 * https://jira.exoplatform.org/browse/ECMS-6140
	 */
	@Test (groups="pending")
	public  void test05_12_Edit_ViewAFileWithApplicationxGroovyHtmlFromActivityStream() {
		info("Test 5: Edit a File with application/x-groovy+html from activity stream");

		String name="node_"+ getRandomNumber();
		String mimeType = ("application/x-groovy+html");

		/*Step Number: 1
		 *Step Name: ChooseFiletemplate
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choose File
			- Fill Name
			- Choose Mime Type as application/x
			-groovy+html
		 *Input Data: 

		 *Expected Outcome: 
			File template is chosen & filled name with Mime Type isapplication/x
			-groovy+html*/

		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_NEWFILE_LINK);
		type(cTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX, name, false);
		selectOption(cTemplate.ELEMENT_NEWFILE_MIME_COMBOX_ID, mimeType);


		/*Step number: 2
		 *Step Name: Fill content in Content field
		 *Step Description: 
			- Fill content in Content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is filled & decorated*/

		inputDataToFrame(cTemplate.ELEMENT_NEWFILE_CONTENT_FRAME_41, name, false);
		switchToParentWindow();
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);

		/*Step number: 3
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- New file is created successfully
			- Content is displayed as decorated.*/

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_ALIGNRIGHT);	

		/*Step number: 4
		 *Step Name: Edit File with application/x
		-groovy+htmlfrom intranet homepage
		 *Step Description: 
			- Go to intranet home page
			- Click [Edit] icon of this File with application/x
			-groovy+html
			- Edit something and decorate it
			- Save
			- Click Back icon to come back homepage
		 *Input Data: 

		 *Expected Outcome: 
			- File with application/x
			-groovy+html is opened in site explorer with CKeditor is well displayed.
			- Activity is well shown.*/ 
		navToolBar.goToHomePage();
		click(hpActivity.ELEMENT_FILE_EDIT.replace("${title}", name));
		//		actBar.goToEditDocument(name);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNLEFT);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		driver.navigate().back();
		click(hpActivity.ELEMENT_FILE_VIEW.replace("${title}", name));
		//		waitForAndGetElement(siteExp.ELEMENT_TEXT.replace("{$node}", name));
	}

	/**
	 *<li> Case ID:119904.</li>
	 *<li> Test Case Name: Edit a HTML file from activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:119911.</li>
	 *<li> Test Case Name: View a HTML file from activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test06_13_Edit_ViewAHTMLFileFromActivityStream() {
		info("Test 6: Edit a HTML file from activity stream");

		String name="node_"+ getRandomNumber();

		/*Step Number: 1
		 *Step Name: ChooseHTML Filetemplate
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- ChooseHTML File
			- Fill Name
		 *Input Data: 

		 *Expected Outcome: 
			HTML File template is chosen & filled name*/

		/*Step number: 2
		 *Step Name: Fill content in Content field
		 *Step Description: 
			- Fill content in Content, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Content is filled & decorated*/

		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_NEW_HTML_FILE_LINK);
		type(cTemplate.ELEMENT_HTML_FILE_NAME, name, true);
		cTemplate.inputFrame(cTemplate.ELEMENT_HTML_FILE_CKEDITOR_FRAME_41, name);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		//inputDataToFrame(cTemplate.ELEMENT_HTML_FILE_CKEDITOR_FRAME_41, name, true);
		switchToParentWindow();
		//click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);

		/*Step number: 3
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- NewHTML File is created successfully
			- Content is displayed as decorated.*/

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_ALIGNRIGHT);	
		/*Step number: 4
		 *Step Name: Edit HTML file from intranet homepage
		 *Step Description: 
			- Go to intranet home page
			- Click [Edit] icon of this HTML file
			- Edit something and decorate it
			- Save
			- Click Back icon to come back homepage
		 *Input Data: 

		 *Expected Outcome: 
			- HTML file is opened in site explorer with CKeditor is well displayed.<br />
			- Activity is well shown.*/ 
		navToolBar.goToHomePage();
		click(hpActivity.ELEMENT_FILE_EDIT.replace("${title}", name));
		//		actBar.goToEditDocument(name);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNLEFT);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		driver.navigate().back();
		click(hpActivity.ELEMENT_FILE_VIEW.replace("${title}", name));
		//		waitForAndGetElement(siteExp.ELEMENT_TEXT.replace("{$node}", name));
	}

	/**
	 *<li> Case ID:119905.</li>
	 *<li> Test Case Name: Edit a product from activity stream.</li>
	 *<li> Pre-Condition: Package is started with all extensions</li>
	 *<li> Post-Condition: </li>
	 *
	 *<li> Case ID:119912.</li>
	 *<li> Test Case Name: View a product from activity stream.</li>
	 *<li> Pre-Condition: Package is started with all extensions</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_14_Edit_ViewAProductFromActivityStream() {
		info("Test 7: Edit a product from activity stream");

		String name="node_"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Choose Product template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choose Product
			- Fill Name
		 *Input Data: 

		 *Expected Outcome: 
			Product template is chosen & filled name.*/

		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();
		actBar.goToAddNewContent();
		click(cTemplate.ELEMENT_PRODUCT_LINK);
		type(cTemplate.ELEMENT_PRODUCT_NAME_TEXTBOX, name, false);

		/*Step number: 2
		 *Step Name: Fill content in Summary
		 *Step Description: 
			- Fill content for summary field. Use some features of editor to decorate this summary
		 *Input Data: 

		 *Expected Outcome: 
			Summary is filled & decorated*/
		cTemplate.inputFrame(cTemplate.ELEMENT_PRODUCT_SUMMARY_FRAME_41, name);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		//inputDataToFrame(cTemplate.ELEMENT_PRODUCT_SUMMARY_FRAME_41, name, true);
		switchToParentWindow();
		//click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		
		/*Step number: 3
		 *Step Name: Fill content in Benefit
		 *Step Description: 
			- Fill content in Benefit, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Benefit is filled & decorated*/
		cTemplate.inputFrame(cTemplate.ELEMENT_PRODUCT_BENEFIT_FRAME_41, name);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		//inputDataToFrame(cTemplate.ELEMENT_PRODUCT_BENEFIT_FRAME_41, name, true);
		//switchToParentWindow();
		//click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		
		/*Step number: 4
		 *Step Name: Fill content in Feature
		 *Step Description: 
			- Fill content in Feature, use some features of editor to decorate this content
		 *Input Data: 

		 *Expected Outcome: 
			Feature is filled & decorated*/
		cTemplate.inputFrame(cTemplate.ELEMENT_PRODUCT_FEATURE_FRAME_41, name);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		
		//inputDataToFrame(cTemplate.ELEMENT_PRODUCT_FEATURE_FRAME_41, name, true);
		switchToParentWindow();
		//click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		
		/*Step number: 5
		 *Step Name: Save content
		 *Step Description: 
			- Click Save and Close
		 *Input Data: 

		 *Expected Outcome: 
			- New Product is created successfully
			- Summary, Benefit and Feature are displayed as decorated.*/

		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_ALIGNRIGHT);
		
		/*Step number: 6
		 *Step Name: Edit productfrom intranet homepage
		 *Step Description: 
			- Go to intranet home page
			- Click [Edit] icon of this Product
			- Edit something and decorate it
			- Save
			- Click Back icon to come back homepage
		 *Input Data: 

		 *Expected Outcome: 
			- Product is opened in site explorer with CKeditor is well displayed.<br />
			- Activity is well shown.*/ 
		navToolBar.goToHomePage();
		click(hpActivity.ELEMENT_FILE_EDIT.replace("${title}", name));
		//		actBar.goToEditDocument(name);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNLEFT);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		driver.navigate().back();
		//click(hpActivity.ELEMENT_FILE_VIEW.replace("${title}", name));
		waitForAndGetElement(siteExp.ELEMENT_TEXT.replace("{$node}", name));
	}

	/**
	 *<li> Case ID:119913.</li>
	 *<li> Test Case Name: View a comment of document on activity stream.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test15_ViewACommentOfDocumentOnActivityStream() {
		info("Test 15 View a comment of document on activity stream");
				
		String name="node_"+ getRandomNumber();

		/*Step Number: 1
		 *Step Name: Create node
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Create new content or upload new files
		 *Input Data: 

		 *Expected Outcome: 
			Content is created/ file is uploaded*/
		
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();

		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		/*inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name,true);
		switchToParentWindow();
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);*/
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		waitForAndGetElement(siteExp.ELEMENT_CONTENT_ALIGNRIGHT);	
		
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

		actBar.goToAddComment();
		cTemplate.inputFrame(cTemplate.ELEMENT_ADD_COMMENT_FRAME_41, name);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		//inputDataToFrame(actBar.ELEMENT_ADD_COMMENT_FRAME_41, name, true);
		switchToParentWindow();
		//click(ckEdit.ELEMENT_CKEDITOR_ALIGNRIGHT);
		click(button.ELEMENT_SAVE_BUTTON);
		
		/*Step number: 3
		 *Step Name: View comment from intranet homepage
		 *Step Description: 
			- Go to intranet home page
			- View comment above
		 *Input Data: 

		 *Expected Outcome: 
			Comment is well displayed without decorate in site explorer.*/ 
		
		navToolBar.goToHomePage();
		waitForAndGetElement(hpActivity.ELEMENT_FILE_CHECKCOMMENTRIGHT.replace("${title}", name));
	}
}
