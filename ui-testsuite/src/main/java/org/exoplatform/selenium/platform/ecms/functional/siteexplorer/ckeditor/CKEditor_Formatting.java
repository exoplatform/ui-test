package org.exoplatform.selenium.platform.ecms.functional.siteexplorer.ckeditor;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.CKeditor;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.ecms.EcmsAcme;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


public class CKEditor_Formatting extends PlatformBase {

	//Platform
	NavigationToolbar navToolBar;
	ActionBar actBar;
	ManageAccount magAcc;
	HomePageActivity hpActivity;
	Button but;

	//Ecms
	EcmsBase ecms;
	ContentTemplate cTemplate;
	ContextMenu cMenu;
	SitesExplorer siteExp;
	CKeditor ckEdit;
	Robot robot;
	
	EcmsAcme acme;

	//Define data
	public String DATA_CONTENT_FOLDER = "contentfolder";
	public String DATA_DOCUMENT_FOLDER = "documentfolder";
	public String DATA_FILE_NAME = "filename";
	public String DATA_UPLOAD_FILE_PATH ="TestData/Winter.jpg";

	@BeforeMethod()
	public void beforeTest() throws AWTException
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
		but = new Button(driver);
		robot = new Robot();
		acme = new EcmsAcme(driver);
	}

	@AfterMethod()
	public void afterTest()
	{
		driver.manage().deleteAllCookies();
		driver.quit();
	}


	/**
	 *<li> Case ID:119834.</li>
	 *<li> Test Case Name: Paste from Word.</li>
	 *<li> Pre-Condition: Templates for this case are (Accessible breadcrum, acc navigation, acc search box,web content, illustrated web content, product)</li>
	 *<li> Post-Condition: </li>
	 *
	 * No way to copy / paste out of the browser
	 */
	@Test(groups="pending")
	public  void test01_PasteFromWord() {
		info("Test 1: Paste from Word");
		/*Step Number: 1
		 *Step Name: Choose a template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choosea template with CKEditor
		 *Input Data: 

		 *Expected Outcome: 
			Template is chosen*/

		/*Step number: 2
		 *Step Name: Copy content from word file
		 *Step Description: 
			- Open a word file in your computer with good format (colorful text, bold, highlight, Bullet & number...)
			- Copy content of this file
		 *Input Data: 

		 *Expected Outcome: 
			Content of Word is copied*/

		/*Step number: 3
		 *Step Name: Paste from Word
		 *Step Description: 
			- Back to ckeditor at step 1
			- Click icon [Paste From Word]
			- Paste the content
			- Click OK
		 *Input Data: 

		 *Expected Outcome: 
			Content pasted in CKEditor has the same format as in Word file.*/ 

	}

	/**
	 *<li> Case ID:119835.</li>
	 *<li> Test Case Name: Paste as plain text.</li>
	 *<li> Pre-Condition: Templates for this case are (Accessible breadcrum, acc navigation, acc search box,web content, illustrated web content, product)</li>
	 *<li> Post-Condition: </li>
	 *
	 * No way to copy / paste out of the browser
	 *
	 */
	@Test(groups="pending")
	public  void test02_PasteAsPlainText() {
		info("Test 2: Paste as plain text");
		/*Step Number: 1
		 *Step Name: Choose a template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choosea template with CKEditor
		 *Input Data: 

		 *Expected Outcome: 
			Template is chosen*/

		/*Step number: 2
		 *Step Name: Copy content from word fille
		 *Step Description: 
			- Open a word file in your computer with good format (colorful text, bold, highlight, Bullet & number )
			- Copy content of this file
		 *Input Data: 

		 *Expected Outcome: 
			Content of Word is copied*/

		/*Step number: 3
		 *Step Name: Paste from Word
		 *Step Description: 
			- Back to ckeditor at step 1
			- Click icon [Paste as plain text]
			- Paste the content by Control V or right click paste
			- Click OK
		 *Input Data: 

		 *Expected Outcome: 
			Content pasted in CKEditor is shown as plain text, no text decoration*/ 

	}

	/**
	 *<li> Case ID:119836.</li>
	 *<li> Test Case Name: Check 3 types of paste.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *
	 * No way to copy / paste out of the browser
	 *
	 */
	@Test(groups="pending")
	public  void test03_Check3TypesOfPaste() {
		info("Test 3: Check 3 types of paste");
		/*Step Number: 1
		 *Step Name: Choose a template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choosea template with CKEditor(Web Content, Illustrated Web content)
		 *Input Data: 

		 *Expected Outcome: 
			Template is chosen*/

		/*Step number: 2
		 *Step Name: Paste from Word
		 *Step Description: 
			- See paste options in ckeditor
		 *Input Data: 

		 *Expected Outcome: 
			There are 3 icons to paste
			- Paste
			- Paste from Word
			- Paste as plain text
			- You can also right click on CKeditor then Paste*/ 

	}

	/**
	 *<li> Case ID:119837.</li>
	 *<li> Test Case Name: Paste option only.</li>
	 *<li> Pre-Condition: - Templates for this case are (Accessible Media, Announcement, File, HTML File, Web content, Illustrated Web Content, Product)
	- Have a word file with content</li>
	 *<li> Post-Condition: </li>
	 * 
	 * No way to copy / paste out of the browser
	 * 
	 */
	@Test
	public  void test04_PasteOptionOnly(){
		info("Test 4: Paste option only");
		String title ="Wind";
		String name="node_"+getRandomNumber();

		driver.get(plfURL+"/acme");
		waitElementAndTryGetElement(acme.ELEMENT_CONTENT_BY_DATA_TITLE.replace("${title}",title));
		String text_cp=waitForAndGetElement(acme.ELEMENT_CONTENT_BY_DATA_TITLE.replace("${title}",title)).getText();
		info("Copy the text from acme site");
		navToolBar.changeEditModeEnable();
		acme.editContentByTitle(title);
		waitForElementNotPresent(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title));
		click(acme.ELEMENT_SELECT_ALL_WIND);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		click(acme.ELEMENT_SELECT_CANCEL_WIND);
		navToolBar.changeEditMode();
		waitForElementNotPresent(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title));


		/*Step Number: 1
		 *Step Name: Choose a template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choosea template with CKEditor
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
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);


		/*Step number: 2
		 *Step Name: Copy content from word file/webpage
		 *Step Description: 
			- Open a web page, eg acme homepage or Word file
			- Control A then Copy all content and format of this webpage
		 *Input Data: 

		 *Expected Outcome: 
			Content of Webpage/word file is copied*/

		/*Step number: 3
		 *Step Name: Paste content
		 *Step Description: 
			- Back to ckeditor at step 1
			- Click icon [Paste]
			- Paste the content by press Ctr
			-V or right click then Paste
			- Click OK
		 *Input Data: 

		 *Expected Outcome: 
			Content pasted in CKEditor has the same format as in Webpage/Word file*/ 

		info("Paste the text to the content");
		ckEdit.cke_Paste("tab4");
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		info("Verify that the text is pasted correctly");
		cTemplate.waitForAndGetElement(cTemplate.ELEMETN_CONTENT_OF_WEBCONTENT_FILES.replace("${content}",text_cp),2000,1);
		
		info("Delete the file");
		ecms.deleteData(name);
	}

	/**
	 *<li> Case ID:119838.</li>
	 *<li> Test Case Name: Paste directly in full CKEditor.</li>
	 *<li> Pre-Condition: - Templates for this case are "Accessible Media, Announcement, File, HTML File, Web content, Illustrated Web Content, Product"
	- Have word file with content file</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_PasteDirectlyInFullCKEditor() {
		info("Test 5: Paste directly in full CKEditor");
		String name="node_"+getRandomNumber();
        String url ="http://ckeditor.com/forums/CKEditor/CKEditor-demo-Standard-Editor-Includes-Image-Upload-misleading-or-misunderstood";
		
        driver.get(url);
		Utils.pause(5000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		/*Step Number: 1
		 *Step Name: Choose a template
		 *Step Description: 
			- Log in as admin
			- Go to CE
			- Click [New Content] on action bar or right click on main pain then choose [New Content]
			- Choosea template with CKEditor
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
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);

		/*Step number: 2
		 *Step Name: Copy from webpage/Word file
		 *Step Description: 
			- Open a web page, eg acme homepage or open a word file
			- Control A then Copy all content and format of this webpage
		 *Input Data: 

		 *Expected Outcome: 
			Content of Webpage/Word file is copied*/

		/*Step number: 3
		 *Step Name: Paste content
		 *Step Description: 
			- Back to ckeditor at step 1
			- Maximize CK Editor
			- Paste the content directly into CK Editor by press Ctr
			-V or right click then Paste
			- Click OK
		 *Input Data: 

		 *Expected Outcome: 
			Content pasted in CKEditor has the same format as in Webpage/ word file*/ 
		click(ckEdit.ELEMENT_CKEDITOR_MAXIMIZE);
		info("Paste the text to the content");
		ckEdit.cke_Paste("tab4");
		click(ckEdit.ELEMENT_CKEDITOR_MAXIMIZE);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		
		info("Verify that the text is pasted correctly");
		cTemplate.waitForAndGetElement(cTemplate.ELEMETN_CONTENT_OF_WEBCONTENT_FILES.replace("${content}","Add new post"),2000,1);
		
		info("Delete the file");
		ecms.deleteData(name);
	}

	/**
	 *<li> Case ID:119839.</li>
	 *<li> Test Case Name: Paste option in Basic CKeditor.</li>
	 *<li> Pre-Condition: - Templates for this case are in Forum, Answer, ECMS: File, html file, Announcement, Accessible media
	- Have a file with content</li>
	 *<li> Post-Condition: </li>
	 *
	 * No access to the frame for paste with ctrl+v
	 */
	@Test
	public  void test06_PasteOptionInBasicCKeditor() {
		info("Test 6: Paste option in Basic CKeditor");
		/*Step number: 2
		 *Step Name: Open a file with content
		 *Step Description: 
			- Open a file with content
			- Select text to copy (by Ctr
			-A or by mouse)
			- Right click on selected text, click [Copy] option, or press Ctr
			-C
		 *Input Data: 

		 *Expected Outcome: 
			Text is copied*/
		
		String title ="Wind";
		String name="node_"+getRandomNumber();

		driver.get(plfURL+"/acme");
		waitElementAndTryGetElement(acme.ELEMENT_CONTENT_BY_DATA_TITLE.replace("${title}",title));
		String text_cp=waitForAndGetElement(acme.ELEMENT_CONTENT_BY_DATA_TITLE.replace("${title}",title)).getText();
		info("Copy the text from acme site");
		navToolBar.changeEditModeEnable();
		acme.editContentByTitle(title);
		waitForElementNotPresent(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title));
		click(acme.ELEMENT_SELECT_ALL_WIND);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		click(acme.ELEMENT_SELECT_CANCEL_WIND);
		navToolBar.changeEditMode();
		waitForElementNotPresent(By.xpath(acme.ELEMENT_CONTENT_BY_TITLE_TEXT_STRONG.replace("{$title}", title)));
		
		/*Step Number: 1
		 *Step Name: Choose a template
		 *Step Description: 
			- Log in as admin
			- Open Ckeditor of template in precondition
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
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);

		/*Step number: 3
		 *Step Name: Paste content by using icon
		 *Step Description: 
			- Back to ckeditor at step 1
			- Click icon [Paste]
			- Paste the content by press Ctr
			-V or right click then click [Paste]
			- Click OK
		 *Input Data: 

		 *Expected Outcome: 
			Content pasted in CKEditor is plain text*/
		

		/*Step number: 4
		 *Step Name: Paste content by using keyboards
		 *Step Description: 
			- Clear the text above
			- Click on input area of CKeditor, Press Ctr
			-V
		 *Input Data: 

		 *Expected Outcome: 
			Above content is pasted again, and shown in the CKeditor*/ 


		info("Paste the text to the content");
		ckEdit.cke_PasteDialog("tab4");
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		info("Verify that the text is pasted correctly");
		cTemplate.waitForAndGetElement(cTemplate.ELEMETN_CONTENT_OF_WEBCONTENT_FILES.replace("${content}",text_cp),2000,1);
		
		info("Delete the file");
		ecms.deleteData(name);


	}

	/**
	 *<li> Case ID:119840.</li>
	 *<li> Test Case Name: Add new content with blockquote.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test07_AddNewContentWithBlockquote() {
		info("Test 7: Add new content with blockquote");

		String name="node_"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Show New content form
		 *Step Description: 
			- Log in
			- Go to SE
			- Choose [New Content]
			- Select File content for eg.
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

		/*Step number: 2
		 *Step Name: Fill content with block quote
		 *Step Description: 
			- On Content form, use Block quote to design content
		 *Input Data: 

		 *Expected Outcome: 
			Content is filled & decorated*/

		/*Step number: 3
		 *Step Name: Save Content
		 *Step Description: 
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			New content is display with block quote*/ 
		navToolBar.goToSiteExplorer();
		info("Create a web content");
		actBar.goToAddNewContent();

		click(cTemplate.ELEMENT_NEWFILE_LINK);
		type(cTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX, name, false);
        cTemplate.inputFrame(cTemplate.ELEMENT_NEWFILE_CONTENT_FRAME_41, name);
        ckEdit.cke_BlockQuote();
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		info("Verify that the text is blocked quote correctly");
		WebElement e = waitForAndGetElement(cTemplate.ELEMENT_FILE_CONTENT_FRAME,DEFAULT_TIMEOUT,1,2);
		info("Switch to the frame");
		driver.switchTo().frame(e);
		driver.switchTo().activeElement();
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_FILE_BLOCKQUTE,2000,1);
		driver.switchTo().defaultContent();
		
		info("Delete the file");
		ecms.deleteData(name);

	}

	/**
	 *<li> Case ID:119850.</li>
	 *<li> Test Case Name: Check Text effect bold, italic, underline, strike.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test08_CheckTextEffectBoldItalicUnderlineStrike() {
		info("Test 8: Check Text effect bold, italic, underline, strike");

		String name="node_"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg File)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

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
		 *Step Name: Check bold action
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Press control + B or, Click B icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Text is in bold effect*/

		/*Step number: 3
		 *Step Name: Check italic action
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Press control + I or, Click I icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Text is in italic effect*/
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name);
        ckEdit.cke_Bold("tab4");
        ckEdit.cke_Italic("tab4");
        ckEdit.cke_Underline("tab4");
        ckEdit.cke_Strike("tab4");
		
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(3000);

		/*Step number: 6
		 *Step Name: Save
		 *Step Description: 
			- Click Save or Save & close
		 *Input Data: 

		 *Expected Outcome: 
			Above effect is saved*/

		info("Verify that above effect is displayed correctly");
		waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_BOLD_ITALIC_UNDERLINE_STRIKE.replace("${content}",name),2000,1);
		
		info("Delete the file");
		ecms.deleteData(name);
	}

	/**
	 *<li> Case ID:119851.</li>
	 *<li> Test Case Name: Check align effect.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test09_CheckAlignEffect() {
		info("Test 9: Check align effect");
		String name0 = "node"+getRandomNumber();
		String name1 = "node"+getRandomNumber();
		String name2="node"+getRandomNumber();
		String name3="node"+getRandomNumber();
		String name4="The biggest change in Selenium recently has been the inclusion of the WebDriver API. "
				+ "Driving a browser natively as a user would either locally or on a remote machine using "
				+ "the Selenium Server it marks a leap forward in terms of browser automation.";

		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg HTML file)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();

		/*Step number: 2
		 *Step Name: Check Align left
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Click [Align Left] icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Text is left aligned*/
		
		/*Step number: 3
		 *Step Name: Check Center
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Click [Center] icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Text is centered*/


		/*Step number: 4
		 *Step Name: Check Align Right
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Click [Align Right] icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Text is right aligned*/


		/*Step number: 5
		 *Step Name: Check Justify
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Click [Justify] icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Text is justified*/


		/*Step number: 6
		 *Step Name: Save
		 *Step Description: 
			- Click Save or Save & close
		 *Input Data: 

		 *Expected Outcome: 
			Above effect is saved*/ 
		
		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name0, false);
        ckEdit.cke_AlignLeft("tab4");
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name1+"\n", false);
		ckEdit.cke_Center("tab4");
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name2+"\n", false);
		ckEdit.cke_AlignRight("tab4");
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name3+"\n", false);
		ckEdit.cke_Justify("tab4");
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name4, false);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		info("Verify that the effects are applied successfully");
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_WEBCONTENT_ALIGHLEFT.replace("${content}", name1),2000,1);
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_WEBCONTENT_ALIGHCENTER.replace("${content}", name2),2000,1);
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_WEBCONTENT_ALIGHRIGHT.replace("${content}", name3),2000,1);
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_WEBCONTENT_JUSTIFY,2000,1);
		info("Delete the file");
		ecms.deleteData(name0);
	}

	/**
	 *<li> Case ID:119852.</li>
	 *<li> Test Case Name: Bullet & numbering.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test10_BulletNumbering() {
		info("Test 10 Bullet & numbering");

		String name = "node"+getRandomNumber();
		String name1="node"+getRandomNumber();
		String name2="node"+getRandomNumber();
		String name3="node"+getRandomNumber();
		String name4="node"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg web content)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();


		/*Step number: 2
		 *Step Name: CheckBullet
		 *Step Description: 
			- Fill text into main content field egaaabbbccc
			- Select it
			- Click [Insert/remove bulleted List] icon on formatting bar
			- Click again
		 *Input Data: 

		 *Expected Outcome: 
			- Text is inserted bullet
			- Text is removed bullet*/
		
		/*Step number: 3
		 *Step Name: Check numbering
		 *Step Description: 
			- Fill text into main content field egaaabbbccc
			- Select it
			- Click [Insert/remove numbered List] icon on formatting bar
			- Click again
		 *Input Data: 

		 *Expected Outcome: 
			- Text is inserted number list
			- Text is removed number list*/

		/*Step number: 4
		 *Step Name: Save
		 *Step Description: 
			- Click Save or Save & close
		 *Input Data: 

		 *Expected Outcome: 
			Above effect is saved*/ 
		
		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
        ckEdit.cke_InsertRemoveBulletList("tab4");
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name1+"\n", false);
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name2+"\n", false);
		ckEdit.cke_InsertRemoveNumList("tab4");
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name3+"\n", false);
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name4, false);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		info("Verify that bullet and numeric are inserted");
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_WEBCONTENT_BULLET.replace("${content}", name1),2000,1);
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_WEBCONTENT_BULLET.replace("${content}", name2),2000,1);
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_WEBCONTENT_NUMBERIC.replace("${content}", name3),2000,1);
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_WEBCONTENT_NUMBERIC.replace("${content}", name4),2000,1);

		info("Delete the file");
		ecms.deleteData(name);
		
	}

	/**
	 *<li> Case ID:119853.</li>
	 *<li> Test Case Name: Check Text color, background color.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test11_CheckTextColorBackgroundColor() {
		info("Test 11 Check Text color, background color");

		String name ="node"+getRandomNumber();
		String name1="node"+getRandomNumber();
		String name2="node"+getRandomNumber();
		String codeColorText="008000";
		String codeColorBackGroundText="FF8C00";

		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg illustrated web content)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Create a web content");
		actBar.goToAddNewContent();


		/*Step number: 2
		 *Step Name: Check text color
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Click [Text color] icon on formatting bar, choose a color: eg red
		 *Input Data: 

		 *Expected Outcome: 
			Text is in red*/

		
		/*Step number: 3
		 *Step Name: Check background color
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Click [Background color] icon on formatting bar, choose a color, eg yellow
		 *Input Data: 

		 *Expected Outcome: 
			Text has background yellow*/

		/*Step number: 4
		 *Step Name: Save
		 *Step Description: 
			- Click Save or Save & close
		 *Input Data: 

		 *Expected Outcome: 
			Above effect is saved*/ 
		info("-- Creating a new Web Content --");
		Utils.pause(500);
		click(cTemplate.ELEMENT_WEBCONTENT_LINK);
		type(cTemplate.ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, false);
		ckEdit.cke_PaintColorText("tab4",codeColorText);
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name1+"\n", false);
		ckEdit.cke_PaintColorBackgroundText("tab4",codeColorBackGroundText);
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name2);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		
		info("Verify that the text is painted for text and background");
		waitForAndGetElement(
				cTemplate.ELEMENT_CONTENT_WEBCONTENT_PAINT_TEXT_COLOR.replace(
						"${codeColor}", codeColorText).replace("${content}",
						name1), 2000, 1);
		waitForAndGetElement(
				cTemplate.ELEMENT_CONTENT_WEBCONTENT_PAINT_TEXT_BACKGROUND_COLOR
						.replace("${codeColor}", codeColorBackGroundText)
						.replace("${content}", name2), 2000, 1);
		
		info("Delete the file");
		ecms.deleteData(name);
	}

	/**
	 *<li> Case ID:119854.</li>
	 *<li> Test Case Name: Check Remove format.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test12_CheckRemoveFormat() {
		info("Test 12 Check Remove format");

		String name="node_"+getRandomNumber();

		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg Announcement)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

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
		 *Step Name: Check bold action
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Press control + B or, Click B icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Text is in bold effect*/

		/*Step number: 3
		 *Step Name: Check italic action
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Press control + I or, Click I icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Text is in italic effect*/

		/*Step number: 4
		 *Step Name: Check underline action
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Press control + U or, Click U icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Text is in underline effect*/

		/*Step number: 5
		 *Step Name: Check strike through action
		 *Step Description: 
			- Fill text into main content field
			- Select it
			- Click (S) icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Text is striked through*/

		/*Step number: 6
		 *Step Name: Check remove format
		 *Step Description: 
			- Select all text above.
			-Click [Remove Format] on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			- All text effect above are removed, Text becomes normal*/


		/*Step number: 7
		 *Step Name: Save
		 *Step Description: 
			- Click Save or Save & close
		 *Input Data: 

		 *Expected Outcome: 
			Text becomes normal*/ 

		ckEdit.cke_Bold("tab4");
        ckEdit.cke_Italic("tab4");
        ckEdit.cke_Underline("tab4");
        ckEdit.cke_Strike("tab4");
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(100);
		ckEdit.cke_removeFormat("tab4");
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(3000);
		info("Verify that the text becomes normally");
		cTemplate.waitForAndGetElement(cTemplate.ELEMENT_CONTENT_WEBCONTENT_ALIGHLEFT.replace("${content}",name),2000,1);
		info("Delete the file");
		ecms.deleteData(name);
	}

	/**
	 *<li> Case ID:119855.</li>
	 *<li> Test Case Name: Link & Unlink.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test13_LinkUnlink() {
		info("Test 13 Link & Unlink");

		String name="node_"+getRandomNumber();
		String url="google.com";


		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg web content)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

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
		 *Step Name: Link
		 *Step Description: 
			- In main content field 
			- Click icon [Link]
			- Type in URL, eg google.com
			- Click OK
			- Click Save & Close
			- Click link
		 *Input Data: 

		 *Expected Outcome: 
			- Http://google.com is shown as a link
			- Go to google page*/

		/*click(ckEdit.ELEMENT_CKEDITOR_INSERT_LINK);
		type(ckEdit.ELEMENT_CKEDITOR_TYPE_LINK, name2, false);*/
		ckEdit.cke_link("tab4", url);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(3000);
		info("Verify that url is saved as a link text");
		waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_DOCUMENT_VIEW_AS_LINK.replace("${url}",url),2000,1);
		
		/*Step number: 3
		 *Step Name: Unlink
		 *Step Description: 
			- Click Edit icon on action bar
			- Select All google link above
			- Click [Unlink] icon
			- Save & Close
		 *Input Data: 

		 *Expected Outcome: 
			- http://google.com is not shown as a link
			- Cannot click to go to google page*/ 
        actBar.goToEditDocument(name);
        ckEdit.cke_Bold("tab4");
        robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(100);
		ckEdit.cke_removeLink("tab4");
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(3000);

		info("Verify that url is saved as normal text");
		waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_NAME_DOCUMENT_VIEW.replace("${content}",url),2000,1);
		
		info("Delete the file");
		ecms.deleteData(name);
	}

	/**
	 *<li> Case ID:119856.</li>
	 *<li> Test Case Name: Paragraph format.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test14_ParagraphFormat() {
		info("Test 14 Paragraph format");

		String name="node_"+getRandomNumber();
        String value = "Heading 2";
        String format= "h2";
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg web content)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

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
		 *Step Name: Choose format
		 *Step Description: 
			- In main content field, enter some text
			- Select it
			- Click [Paragraph Format]
			- Choose a format you want
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			Text is shown as chosen format*/ 

		ckEdit.cke_paragraphFormat("tab4",value);
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(3000);
		
		info("Verify that the text is shown as chosen format");
		waitForAndGetElement(cTemplate.ELEMENT_WEBCONTENT_CONTENT_PARAGRAPH_FORMAT.replace("${format}",format).replace("${content}",name),2000,1);
		info("Delete the file");
		ecms.deleteData(name);
	}

	/**
	 *<li> Case ID:119857.</li>
	 *<li> Test Case Name: Check selecting font & font size.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test15_CheckSelectingFont_FontSize() {
		info("Test 15 Check selecting font & font size");
		
		String name="node_"+ getRandomNumber();
		String value="Times New Roman";
		String font = "times new roman";
		String fontSize = "24";
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg illustrated web content)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

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
		 *Step Name: Choose font
		 *Step Description: 
			- In main content field, enter some text
			- Select it
			- Click [Font Name]
			- Choose a font you want
		 *Input Data: 

		 *Expected Outcome: 
			Text is shown as chosen font*/
		
		
		/*Step number: 3
		 *Step Name: Choose font size
		 *Step Description: 
			- Click [Font size] icon
			- Choose a size
		 *Input Data: 

		 *Expected Outcome: 
			Text is shown as chosen size*/

		
		/*Step number: 4
		 *Step Name: Save
		 *Step Description: 
			Save or Save & Close
		 *Input Data: 

		 *Expected Outcome: 
			All format is saved & well shown*/ 
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(100);
		ckEdit.cke_paragraphFont("tab4",value);
		ckEdit.cke_FontSize("tab4",fontSize);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
	    Utils.pause(3000);
		
		info("Verify that the text is shown as chosen format");
		waitForAndGetElement(
				cTemplate.ELEMENT_WEBCONTENT_CONTENT_PARAGRAPH_FONT_SIZE_FONT_NAME
						.replace("${fontSize}", fontSize)
						.replace("${fontName}", font)
						.replace("${content}", name), 2000, 1);
	
		info("Delete the file");
		ecms.deleteData(name);
		
	}

	/**
	 *<li> Case ID:119858.</li>
	 *<li> Test Case Name: Maximize/ Minimize.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test16_MaximizeMinimize() {
		info("Test 16 Maximize/ Minimize");
		
		String name="node_"+ getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg File)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/
	
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
		 *Step Name: Maximize
		 *Step Description: 
			- In main content field
			- Click [Maximize] icon
			- Enter some text
		 *Input Data: 

		 *Expected Outcome: 
			CKEditor is shown in full screen*/

		// Get minimize size of the area text content
		Dimension form_size_min = waitForAndGetElement(
		ckEdit.ELEMENT_CKEDITOR_TEXT_AREA_FRAME.replace("${tab}","tab4")).getSize();
				
		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name);
		ckEdit.cke_Max_Minmize("tab4");
		Utils.pause(2000);
		
		// Get maximize size of the area text content
		Dimension form_size_max = waitForAndGetElement(
				ckEdit.ELEMENT_CKEDITOR_TEXT_AREA_FRAME.replace("${tab}","tab4")).getSize();
				
		//Verify that the maximize popup is resize correctly
		assert (form_size_max.getHeight() > form_size_min.getHeight()):"Cannot Maximize the form";
		assert (form_size_max.getWidth() > form_size_min.getWidth()):"Cannot Minimize the form";

		ckEdit.cke_Max_Minmize("tab4");
		// Get minimize size of the area text content
		Dimension form_size_min_1 = waitForAndGetElement(
				ckEdit.ELEMENT_CKEDITOR_TEXT_AREA_FRAME.replace("${tab}","tab4")).getSize();
				
	    //Verify that the minmize popup is resize correctly
		assert (form_size_max.getHeight() > form_size_min_1.getHeight()):"Cannot Minimize the form";
		assert (form_size_max.getWidth() > form_size_min_1.getWidth()):"Cannot Minimize the form";
							
		ckEdit.cancelPopup();
		info("-- The test is successfull--");
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(3000);
		/*Step number: 3
		 *Step Name: Minimize
		 *Step Description: 
			- Click [Minimize] icon
		 *Input Data: 

		 *Expected Outcome: 
			- CKeditor is minimized as normal, text inputed in maximized mode stands there*/

		
		/*Step number: 4
		 *Step Name: Save
		 *Step Description: 
			Save or Save & Close
		 *Input Data: 

		 *Expected Outcome: 
			All format is saved & well shown*/ 
		info("Delete the file");
		ecms.deleteData(name);
	}

	/**
	 *<li> Case ID:119859.</li>
	 *<li> Test Case Name: Decrease indent/Increase Indent.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test17_DecreaseIndentIncreaseIndent() {
		info("Test 17 Decrease indent/Increase Indent");
		
		String name="node_"+getRandomNumber();
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg File)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Create a web content");
		actBar.goToAddNewContent();

		click(cTemplate.ELEMENT_NEWFILE_LINK);
		type(cTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX, name, false);
		
		/*Step number: 2
		 *Step Name: Check Increase indent
		 *Step Description: 
			- Fill text into main content field egaaabbbccc
			- Select it
			- Click [Increase Indent] icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			- Indent is Increased*/

		cTemplate.inputFrame(cTemplate.ELEMENT_NEWFILE_CONTENT_FRAME_41, name);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(100);
		ckEdit.cke_InscrIndent();
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
        Utils.pause(3000);

        info("Verify that the indent is inscreased");
        WebElement e = waitForAndGetElement(cTemplate.ELEMENT_FILE_CONTENT_FRAME,DEFAULT_TIMEOUT,1,2);
		info("Switch to the frame");
		driver.switchTo().frame(e);
		driver.switchTo().activeElement();
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_FILE_INSCREASE ,2000,1);
		driver.switchTo().defaultContent();

		/*Step number: 3
		 *Step Name: Check Decrease indent
		 *Step Description: 
			- Fill text into main content field egaaabbbccc
			- Select it
			- Click [Decrease Indent] icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			- Indent is Decreased*/
		
		/*Step number: 4
		 *Step Name: Save
		 *Step Description: 
			- Click Save
		 *Input Data: 

		 *Expected Outcome: 
			Above effect is saved*/ 
		
		actBar.goToEditDocument(name);
	    ckEdit.cke_Bold();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(100);
		ckEdit.cke_DescrIndent();
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
        Utils.pause(3000);

        info("Verify that the indent is descreased");
		info("Switch to the frame");
		WebElement e1 = waitForAndGetElement(cTemplate.ELEMENT_FILE_CONTENT_FRAME,DEFAULT_TIMEOUT,1,2);
		driver.switchTo().frame(e1);
		driver.switchTo().activeElement();
		waitForAndGetElement(cTemplate.ELEMENT_CONTENT_FILE_DESCREASE ,2000,1);
		driver.switchTo().defaultContent();
		info("Delete the file");
		ecms.deleteData(name);
		
	}

	/**
	 *<li> Case ID:119860.</li>
	 *<li> Test Case Name: Show blocks.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *Pending: cannot auto for checking format of show Blocks in CKEditor
	 */
	@Test(groups="pending")
	public  void test18_ShowBlocks() {
		info("Test 18 Show blocks");
		
		String name="node_"+ getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg File)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

		info("-- Open Sites Explorer --");
		navToolBar.goToSiteExplorer();

		info("Import a Web Content into Web Content");
		info("Create a web content");
		actBar.goToAddNewContent();

		click(cTemplate.ELEMENT_NEWFILE_LINK);
		type(cTemplate.ELEMENT_NEWFILE_NAME_TEXTBOX, name, false);
		
		/*Step number: 2
		 *Step Name: Check show blocks
		 *Step Description: 
			- Fill text into main content field egaaabbbccc
			- Select it
			- Click [Show Blocks] icon on formatting bar
		 *Input Data: 

		 *Expected Outcome: 
			Blocks with P is shown in each line with text inside*/

		cTemplate.inputFrame(cTemplate.ELEMENT_NEWFILE_CONTENT_FRAME_41, name);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(100);
        ckEdit.cke_showBlocks();
        ckEdit.cke_showBlocks();
		info("Delete the file");
		ecms.deleteData(name);
		/*Step number: 3
		 *Step Name: Not show block
		 *Step Description: 
			- Click [Show Blocks] again
		 *Input Data: 

		 *Expected Outcome: 
			Blocks with P is not shown in each line, just show text*/ 
		
		
	}

	/**
	 *<li> Case ID:119861.</li>
	 *<li> Test Case Name: Add/ Remove anchor.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *Pending: cannot auto for selecting first and last word in ckeditor
	 */
	@Test(groups="pending")
	public  void test19_AddRemoveAnchor() {
		info("Test 19 Add/ Remove anchor");

		String name="node_"+ getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Show Add content form
		 *Step Description: 
			- Login
			- Go to SE
			- Choose Drive/Folder and click [New Content]
			- Select content type (Eg webcontent)
		 *Input Data: 

		 *Expected Outcome: 
			New content form appears*/

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
		 *Step Name: Add anchor
		 *Step Description: 
			- Fill long text into main content field
			- Point mouse to the 1st word of content
			- Click [Anchor] icon on formatting bar
			- Fill anchor name eg test
			- Click OK
		 *Input Data: 

		 *Expected Outcome: 
			Anchor is added with a red flag icon.*/

		cTemplate.inputFrame(cTemplate.ELEMENT_WEBCONTENT_CONTENT_FRAME_41, name);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(100);
		ckEdit.cke_anchor("tab4");
		Utils.pause(3000);
		
		/*Step number: 3
		 *Step Name: Link to anchor
		 *Step Description: 
			- Choose some text, eg, the final word of text above
			- Click [Link] icon on formatting bar
			- Choose Link Type is [Link to anchor in the text]
			- Select anchor test in step 2
			- Click OK
			- Click Save&Close
			- Click link of the last word above
		 *Input Data: 

		 *Expected Outcome: 
			- Link to anchor is created
			- Click link will direct to the 1st word of this paragraph*/

		
		/*Step number: 4
		 *Step Name: Remove anchor
		 *Step Description: 
			- Edit this web content
			- Mouse focus on anchor (reg flag)
			- Press Delete
			- Save & Close
		 *Input Data: 

		 *Expected Outcome: 
			Anchor is deleted*/ 

		
		
	}
	
}
