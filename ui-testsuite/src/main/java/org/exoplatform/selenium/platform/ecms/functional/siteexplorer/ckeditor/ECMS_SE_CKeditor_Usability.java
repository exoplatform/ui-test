package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.ckeditor;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.event.KeyEvent;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.CKeditor;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.EcmsPermission;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ECMS_SE_CKeditor_Usability extends PlatformBase {
	Button button;
	ManageAccount magAcc;
	NavigationToolbar navToolBar;
	ActionBar actBar;
	ManageAlert magAlt;
	CKeditor cke;

	//Ecms
	EcmsBase ecms;
	EcmsPermission ecmsPer;
	ContextMenu cMenu;
	ContentTemplate cTemplate;

	@BeforeMethod
	public void beforeMethod(){
		initSeleniumTest();
		driver.get(plfURL);
		button = new Button(driver, this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		navToolBar = new NavigationToolbar(driver, this.plfVersion);
		actBar = new ActionBar(driver, this.plfVersion);
		ecms = new EcmsBase(driver, this.plfVersion);
		ecmsPer = new EcmsPermission(driver);
		cMenu = new ContextMenu(driver);
		cTemplate = new ContentTemplate(driver, this.plfVersion);
		magAlt = new ManageAlert(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		cke = new CKeditor(driver, this.plfVersion);
	}

	@AfterMethod
	public void afterMethod() {
		info("-- User signOut --");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:119846.
	 * Test Case Name: Check spelling as you type.
	 * Pre-Condition: network is available.
	 * Post-Condition: 
	 */
	@Test (groups="pending")
	public  void test01_CheckSpellingAsYouType() {
		info("Test 1: Check spelling as you type");
		/*Step Number: 1
		 *Step Name: Choose a template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pane then choose [New Content]
			- Choosea template with CKEditor(Accessible breadcrum, acc navigation, acc search box, acc media, web content, illustrated web content, announcement, file, html file, product)
		 *Input Data: 

		 *Expected Outcome: 
			Template is chosen*/
		
		/*Step number: 2
		 *Step Name: Check spelling
		 *Step Description: 
			- Typea word with wrong spelling, eg Testtt
		 *Input Data: 

		 *Expected Outcome: 
			- The work is underline by a red line
			- Right click on this word, there will be some suggestions.*/ 
	}

	/**
	 * Case ID:119847.
	 * Test Case Name: Resize CKEditor.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test (groups="pending")
	public  void test02_ResizeCKEditor() {
		info("Test 2: Resize CKEditor");
		/*Step Number: 1
		 *Step Name: Resize CKEditor in Site explorer 
		- Comment
		 *Step Description: 
			- Go to CE
			- Open document
			- Click Comment on action bar
			- Resize Ckeditor by click resize icon at the botton right Conner of ckeditor
			- Start drag up and down
		 *Input Data: 

		 *Expected Outcome: 
			CKEditor is resized as expected*/

		/*Step number: 2
		 *Step Name: Resize CKEditorin Site explorer 
		- new content
		 *Step Description: 
			- Go to CE
			- Click [New Content] on action bar
			- Open one template: eg: Accessible breadcrum, acc navigation, acc search box, acc media, web content, illustrated web content, announcement, file, html file, product
			- Resize CKEditor by click resize icon at the bottom right Conner of CKEditor
			- Start drag up and down
		 *Input Data: 

		 *Expected Outcome: 
			CKEditor is resized as expected*/ 

	}

	/**
	 * Case ID:119848.
	 * Test Case Name: Find words or phases in CKEditor.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test (groups="pending")
	public  void test03_FindWordsOrPhasesInCKEditor() {
		info("Test 3: Find words or phases in CKEditor");
		String content="content"+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Choose a template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pane then choose [New Content]
			- Choosea template with CKEditor(Accessible breadcrum, acc navigation, acc search box, acc media, web content, illustrated web content, announcement, file, html file, product)
		 *Input Data: 

		 *Expected Outcome: 
			Template is chosen*/
		driver.get(plfURL);
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();

		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, content,true);
		switchToParentWindow();
		
		/*Step number: 2
		 *Step Name: Find words or phases
		 *Step Description: 
			- Fill content in CKeditor, eg containing word "test"
			- Click [Find] icon on editting bar
			- Fill "test" in [Find what] field
			- Click [Find]
		 *Input Data: 

		 *Expected Outcome: 
			Searching word "test" is found and highlighted*/ 
		click(cke.ELEMENT_CKEDITOR_REPLACE);
		cke.findText(content);
		switchToParentWindow();
		click(button.ELEMENT_CLOSE_BUTTON);
		magAlt.acceptAlert();
	}

	/**
	 * Case ID:119849.
	 * Test Case Name: Replace words or phases in CKEditor.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test04_ReplaceWordsOrPhasesInCKEditor() {
		info("Test 4: Replace words or phases in CKEditor");
		String content="content"+getRandomNumber();
		String newContent="new"+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Choose a template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pane then choose [New Content]
			- Choosea template with CKEditor(Accessible breadcrum, acc navigation, acc search box, acc media, web content, illustrated web content, announcement, file, html file, product)
		 *Input Data: 

		 *Expected Outcome: 
			Template is chosen*/
		driver.get(plfURL);
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();

		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, content,true);
		switchToParentWindow();
		
		/*Step number: 2
		 *Step Name: Find words or phases
		 *Step Description: 
			- Fill content in CKeditor, eg containing word "test"
			- Click [Replace] icon on editing bar
			- Fill "test" in [Find what] field
			- Fill words you want to replace, eg "replace test"
			- Click [Replace] or [Replace all]
		 *Input Data: 

		 *Expected Outcome: 
			Word "test" will be replaced by "replace test".*/ 
		click(cke.ELEMENT_CKEDITOR_REPLACE);
		cke.replaceText(content, newContent);
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextPresent(newContent);
		assert isTextNotPresent(content);
		switchToParentWindow();
		click(button.ELEMENT_CLOSE_BUTTON);
		magAlt.acceptAlert();
	}

	/**
	 * Case ID:119862.
	 * Test Case Name: Check action change to source view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test (groups="pending")
	public  void test05_CheckActionChangeToSourceView() {
		info("Test 5: Check action change to source view");
		String content="content"+getRandomNumber();
		//String sourceContent="<p>"+content+"</p>";
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Accessible breadcrumb for ex)
		 *Input Data: 

		 *Expected Outcome: 
			New content form is appeared*/
		driver.get(plfURL);
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();

		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, content,true);
		switchToParentWindow();
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_B);
		
		/*Step number: 2
		 *Step Name: Check action view source
		 *Step Description: 
			- Add content into Main Content form
			- Change to source view
		 *Input Data: 

		 *Expected Outcome: 
			- Content is displayed as source view correctly*/ 
		click(cke.ELEMENT_CKEDITOR_SOURCE);
		//assert isTextPresent(sourceContent);
		switchToParentWindow();
		click(button.ELEMENT_CLOSE_BUTTON);
		magAlt.acceptAlert();
	}

	/**
	 * Case ID:119863.
	 * Test Case Name: Check action add templates.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test06_CheckActionAddTemplates() {
		info("Test 6: Check action add templates");
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Accessible Navigation for ex)
		 *Input Data: 

		 *Expected Outcome: 
			New content form is appeared*/
		driver.get(plfURL);
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();
		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		
		/*Step number: 2
		 *Step Name: Check action add templates
		 *Step Description: 
			- On Main Content form Click [Templates] icon
			- Select any template (Image and Title for ex)
			- Input value into template
			- Save & Close
		 *Input Data: 

		 *Expected Outcome: 
			- Template is displayed correctly on Main content form*/ 
		click(cke.ELEMENT_CKEDITOR_TEMPLATE);
		click(cke.ELEMENT_CKEDITOR_TEMPLATE_TYE.replace("${type}", "Image and Title"));
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextPresent("Type the title here");
		switchToParentWindow();
		click(button.ELEMENT_CLOSE_BUTTON);
		magAlt.acceptAlert();
	}

	/**
	 * Case ID:119864.
	 * Test Case Name: Check action cancel adding templates.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test07_CheckActionCancelAddingTemplates() {
		info("Test 7: Check action cancel adding templates");
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Accessible Site Search box for ex)
		 *Input Data: 

		 *Expected Outcome: 
			New content form is appeared*/
		driver.get(plfURL);
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();
		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		
		/*Step number: 2
		 *Step Name: Check action cancel adding template
		 *Step Description: 
			- On Main Content form Click [Templates] icon
			- On Content Templates form, do not select any template and click Cancel button
		 *Input Data: 

		 *Expected Outcome: 
			- Template is not displayed correctly on Main content form*/ 
		click(cke.ELEMENT_CKEDITOR_TEMPLATE);
		click(cke.ELEMENT_CKEDITOR_TEMPLATE_CANCEL);
		click(button.ELEMENT_CLOSE_BUTTON);
		magAlt.acceptAlert();
	}

	/**
	 * Case ID:119865.
	 * Test Case Name: Check action cut - paste.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test08_CheckActionCutPaste() {
		info("Test 8: Check action cut - paste");
		String content="content"+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Web content for ex)
		 *Input Data: 

		 *Expected Outcome: 
			New content form is appeared*/
		driver.get(plfURL);
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();
		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		
		/*Step number: 2
		 *Step Name: Check action cut
		-paste
		 *Step Description: 
			- On Main Content form, input content
			- Select content and click [Cut] icon on toolbar or user key Ctrl+X
			- Select other place and click [Paste] icon on toolbar or use key Ctrl+V
			- Save & Close
		 *Input Data: 

		 *Expected Outcome: 
			- Content is cut and pasted successfully*/ 
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, content,true);
		switchToParentWindow();
		
		//cut paste using keyboard
		clearClipboard();
		click(cke.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_X);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextPresent(content);
		
		//cut paste using copy paste icon	
		switchToParentWindow();
		clearClipboard();
		click(cke.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		click(cke.ELEMENT_CKEDITOR_CUT);
		click(cke.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		click(cke.ELEMENT_CKEDITOR_PASTE);
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextPresent(content);
		switchToParentWindow();
		click(button.ELEMENT_CLOSE_BUTTON);
		magAlt.acceptAlert();
	}

	/**
	 * Case ID:119866.
	 * Test Case Name: Check action copy - paste.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test09_CheckActionCopyPaste() {
		info("Test 9: Check action copy - paste");
		String content="content"+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Illustrated Web content for ex)
		 *Input Data: 

		 *Expected Outcome: 
			New content form is appeared*/
		driver.get(plfURL);
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();
		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		
		/*Step number: 2
		 *Step Name: Check action copy
		-paste
		 *Step Description: 
			- On Main Content form, input content
			- Select content and click [Copy] icon on toolbar or use key Ctrl+C
			- Select other place and click [Paste] icon on toolbar or use key Ctrl+V
			- Save & Close
		 *Input Data: 

		 *Expected Outcome: 
			- Content is copied and pasted successfully*/ 
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, content,true);
		switchToParentWindow();
		
		//copy paste using keyboard
		clearClipboard();
		click(cke.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_C);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextPresent(content);
		
		//copy paste using copy paste icon	
		switchToParentWindow();
		clearClipboard();
		click(cke.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		click(cke.ELEMENT_CKEDITOR_COPY);
		click(cke.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		click(cke.ELEMENT_CKEDITOR_PASTE);
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextPresent(content);
		switchToParentWindow();
		click(button.ELEMENT_CLOSE_BUTTON);
		magAlt.acceptAlert();
	}

	/**
	 * Case ID:119867.
	 * Test Case Name: Check action Select All.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test10_CheckActionSelectAll() {
		info("Test 10 Check action Select All");
		String name="node_"+getRandomNumber();
		String content="content"+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Product for ex)
		 *Input Data: 

		 *Expected Outcome: 
			New content form is appeared*/
		driver.get(plfURL);
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();

		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		
		/*Step number: 2
		 *Step Name: Check action Select All
		 *Step Description: 
			- On Benefits or Features form, input content
			- Click [Select All] icon on tool bar or use key Ctrl+A
		 *Input Data: 

		 *Expected Outcome: 
			- All content is selected*/ 
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, content,true);
		switchToParentWindow();
		
		//Select All using keyboard
		clearClipboard();
		click(cke.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_C);
		assert content.contains(getClipboard());
		
		//Select All using select all icon
		click(cke.ELEMENT_CKEDITOR_BUTTON_SELECTALL);
		click(cke.ELEMENT_CKEDITOR_COPY);
		assert content.contains(getClipboard());
		clearClipboard();

		click(button.ELEMENT_CLOSE_BUTTON);
		magAlt.acceptAlert();
	}

	/**
	 * Case ID:119868.
	 * Test Case Name: Check action Undo/Redo.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test11_CheckActionUndoRedo() {
		info("Test 11 Check action Undo/Redo");
		String name="node_"+getRandomNumber();
		String content="content"+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Product for ex)
		 *Input Data: 

		 *Expected Outcome: 
			New content form is appeared*/
		driver.get(plfURL);
		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();

		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, content,true);
		switchToParentWindow();
		
		/*Step number: 2
		 *Step Name: Check Undo action
		 *Step Description: 
			- On Benefits or Features form, input content
			- Input content
			- Do some actions
			- Click [Undo] icon or use key Ctrl+Z
		 *Input Data: 

		 *Expected Outcome: 
			- Undo action successfully*/

		/*Step number: 3
		 *Step Name: Check Redo action
		 *Step Description: 
			- Click [Redo] icon or use key Ctrl+Y
		 *Input Data: 

		 *Expected Outcome: 
			- Redo action successfully*/ 
		//Undo-Redo Undo-Redo icon
		cke.cke_Cut();
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextNotPresent(content);
		switchToParentWindow();
		cke.cke_Undo();
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextPresent(content);
		switchToParentWindow();
		cke.cke_Redo();
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextNotPresent(content);
		switchToParentWindow();
		
		//Undo-Redo using keyboard
		inputDataToFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, content,true);
		switchToParentWindow();
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_X);
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextNotPresent(content);
		switchToParentWindow();
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_Z);
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextPresent(content);
		switchToParentWindow();
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_Y);
		driver.switchTo().frame(waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41));
		assert isTextNotPresent(content);
		switchToParentWindow();
		
		click(button.ELEMENT_CLOSE_BUTTON);
		magAlt.acceptAlert();
	}
}
