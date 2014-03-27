package org.exoplatform.selenium.platform.wiki.functional.macro;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.WikiBase;
import org.exoplatform.selenium.platform.wiki.RichTextMode;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.exoplatform.selenium.platform.wiki.ManageDraft;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: LanLTK
 * @date: 03/24/2014
 */

public class Wiki_Macro_Add extends ManageDraft{

	ManageAccount magAc;
	Button button;
	WikiBase magWiki;
	RichTextMode magRTM;
	BasicAction magWikiAction;

	@BeforeMethod
	public void setUpBeforeTest(){
		getDriverAutoSave();
		magAc = new ManageAccount(driver);
		button = new Button(driver);
		magWiki = new WikiBase();
		magRTM = new RichTextMode();
		magWikiAction = new BasicAction();
		magAc.signIn(DATA_USER1, DATA_PASS); 
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * CaseId 69763: Insert Box macro
	 */
	@Test	
	public void test01_InsertBoxMacro(){
		String title = "Page 69763";
		String content = "";

		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		// Insert Box macro
		info("Insert Box Message macro:");
		createBoxMacro("Box title","Box content");

		//Check the availability of Box macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_BOX.replace("${macro}", "Box content"));
		switchToParentWindow();

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		Utils.pause(1000);

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * CaseId 69764: Insert Children macro
	 */
	@Test	
	public void test02_InsertChildrenMacro(){
		String title1 = "Page 69764_1";
		String content1 = "Content 69764_1";
		String title2 = "Page 69764_2";
		String content2 = "Content 69764_2";
		String title3 = "Page 69764_3";
		String content3 = "Content 69764_3";
		String title4 = "Page 69764_4";
		String content4 = "Content 69764_4";
		String descendant = "Yes";

		//Data Test: Create Page1 > Page2 > Page3; Page1 > Page4
		info("Add new wiki pages:");
		goToAddBlankPage();
		addWikiPageRichText(title1, content1);
		typeEnterInRichText();
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);

		goToAddBlankPage();
		addWikiPageRichText(title2, content2);
		typeEnterInRichText();
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);

		goToAddBlankPage();
		addWikiPageRichText(title3, content3);
		typeEnterInRichText();
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);

		goToWikiPage(title1);
		goToAddBlankPage();
		addWikiPageRichText(title4, content4);
		typeEnterInRichText();
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);

		//Insert Children macro in Page1
		goToWikiPage(title1);
		mouseOverAndClick(ELEMENT_EDIT_PAGE_LINK);
		waitForElementNotPresent(ELEMENT_EDIT_PAGE_LINK);
		addWikiPageRichText(title1, content1);
		typeEnterInRichText();
		info("Insert Children macro:");
		createChildrenMacro(descendant);	

		//Check the availability of Children macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(By.linkText(title2));
		waitForAndGetElement(By.linkText(title3));
		waitForAndGetElement(By.linkText(title4));
		switchToParentWindow();

		//Save wiki Page with Children macro
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Delete wiki page before exit test case
		goToWikiPage(title1);
		deleteCurrentWikiPage();
	}

	/**
	 * * CaseId 69765: Insert Code macro
	 */
	@Test	
	public void test03_InsertCodeMacro(){
		String title = "Page 69765";
		String content = "";
		String language = "html";
		String macro_title = "Test code macro";
		String macro_content = "<html><head>Cool!</head></html>";

		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		//Insert Code macro
		info("Insert Code macro:");
		createCodeMacro(language,macro_title,macro_content);

		//Save wiki Page with Code macro
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Check the availability of Code macro
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${color}", "#8B008B;").replace("${text}", "<html><head>"));
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${color}", "#8B008B;").replace("${text}", "</head></html>"));
		waitForAndGetElement(ELEMENT_MACRO_CODE.replace("${macro}", "Test code macro"));
		waitForAndGetElement(ELEMENT_MACRO_CODE.replace("${macro}", "Cool!"));

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * 	CaseId 69768: Insert Excerpt macro
	 */
	@Test	
	public void test04_InsertExcerptMacro(){
		String title = "Page 69768";
		String content = "";
		String type = "Excerpt";
		String macro_content = "Test Excerpt macro.";

		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		//Insert Excerpt macro
		info("Insert Excerpt macro:");
		createMessageMacro(type,macro_content);

		//Check the availability of Excerpt macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_EXCERPT.replace("${macro}", macro_content));
		driver.switchTo().defaultContent();

		//Save wiki Page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 *  CaseId 69771: Insert Info Message macro
	 */
	@Test	
	public void test05_InsertInfoMessageMacro(){
		String title = "Page 69771";
		String content = "";
		String type = "Info";
		String macro_content = "This is Info Message macro.";

		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		//Insert Info Message macro
		info("Insert Info Message macro:");
		createMessageMacro(type,macro_content);

		//Check the availability of Info Message macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_INFO_MESSAGE.replace("${macro}", macro_content));
		driver.switchTo().defaultContent();

		//Save wiki Page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 *  CaseId 69780: Insert Table of Content macro
	 */
	@Test	
	public void test06_InsertTableOfContentMacro(){
		String title = "Page 69780";
		String content = "=Heading1=</br>== Heading2 ==</br>=== Heading3 ===";

		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageSourceEditor(title,content);
		click(ELEMENT_RICHTEXT_BUTTON);

		//Insert Table of Content macro
		info("Insert Table of Content macro:");
		createTableOfContentsMacro();

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		waitForAndGetElement("//ul/li/span/a[text()='Heading1']"); //Level 1
		waitForAndGetElement("//ul/li/ul//a[text()='Heading2']"); //Level 2
		waitForAndGetElement("//ul/li/ul/li/ul//a[text()='Heading3']"); //Level 3

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * 	CaseId 69782: Insert Tip Message macro
	 */
	@Test	
	public void test07_InsertTipMessageMacro(){
		String title = "Page 69782";
		String content = "";
		String type = "Tip";
		String macro_content = "This is Tip Message macro.";

		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		// Insert Tip Message macro
		info("Insert Tip Message macro:");
		createMessageMacro(type,macro_content);

		//Check the availability of Tip Message macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_TIP_MESSAGE.replace("${macro}", macro_content));
		driver.switchTo().defaultContent();

		//Save wiki Page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * 	CaseId 69783: Insert Warning Message
	 */
	@Test	
	public void test08_InsertWarningMessageMacro(){
		String title = "Page 69783";
		String content = "";
		String type = "Warning";
		String macro_content = "This is Warning Message macro.";

		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		// Insert Warning Message
		info("Insert Warning Message macro:");
		createMessageMacro(type,macro_content);

		//Check the availability of Warning Message macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_WARNING_MESSAGE.replace("${macro}", macro_content));
		switchToParentWindow();

		//Save wiki Page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * CaseId 71253: Insert Success Message
	 */
	@Test	
	public void test09_InsertSuccessMessageMacro(){
		String title = "Page 71253";
		String content = "";
		String type = "Success";
		String macro_content = "This is Success Message macro.";

		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		//Insert Success Message
		info("Insert Success macro:");
		createMessageMacro(type,macro_content);

		//Check the availability of Success Message macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_SUCCESS_MESSAGE.replace("${macro}", macro_content));
		switchToParentWindow();

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * CaseId 71281: Insert JIRA macro
	 */
	@Test	
	public void test10_InsertJIRAMacro(){
		String title = "Page 71281_71282";
		String content = "{{jira URL='https://jira.exoplatform.org/' style='table'}} SOC-123 {{/jira}}";

		//Add Jira macro in SourceEditor; Can not add from RichTextMode
		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageSourceEditor(title,content);

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Check the availability of JIRA macro
		waitForAndGetElement(By.xpath("//a[@href='https://jira.exoplatform.org/browse/SOC-123']"));

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * CaseId 94914: Insert Code macro by source
	 * Bug: WIKI-546 - [PLF-jboss] Code macro doesn't work
	 */
	@Test(groups="error")
	public void test11_InsertSourceMacro(){
		String title = "Page 94914";
		String content = "== Java with title ==\\{{code language='java' title='HelloWorld.java'}} System.out.println('Hello World'); {{/code}} \\ == Java without title ==\\{{code language='java'}} System.out.println('Hello World'); {{/code}} \\ [[xwiki example>>http://extensions.xwiki.org/xwiki/bin/view/Extension/Code+Macro]] \\ {{code language='html'}} <html> <head>How cool?</head></html>{{/code}}";

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageSourceEditor(title,content);
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);

		//Check the availability of Code macro
		waitForTextPresent("HelloWorld.java");
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${color}", "#658b00;").replace("${text}", "out"));
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${color}", "#658b00;").replace("${text}", "println"));
		waitForTextPresent("System");
		waitForTextPresent("Hello World");
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${color}", "#8B008B;").replace("${text}", "<html>"));
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${color}", "#8B008B;").replace("${text}", "<head>"));
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${color}", "#8B008B;").replace("${text}", "</head></html>"));

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 *  CaseId 78257: Insert macro in a macro (1)
	 */
	@Test
	public void test12_InsertMacroInMacro1(){
		String title = "Page macro1 78257";
		String text = "Test macro in macro 01";
		String syntax = "{{info~}~}Hello!{{/info~}~}";
		String content = "{{box title=${syntax}}}${text}{{/box}}".replace("${syntax}", syntax).replace("${text}", text);

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageSourceEditor(title,content);

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Check the availability of Code macro
		waitForAndGetElement(ELEMENT_MACRO_INFO_MESSAGE.replace("${macro}", "Hello!"));
		waitForAndGetElement(ELEMENT_MACRO_BOX.replace("${macro}", text));

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/** CaseID: 78591 - Insert macro in a macro (2)
	 * have bug about display title of warning message
	 * Bug: https://jira.exoplatform.org/browse/WIKI-896
	 */
	@Test(groups="error")
	public void test13_InsertMacroInMacro2(){
		String title = "Page macro2 78591";
		String text = "Test macro in macro 02";
		String syntax = "{{warning~}~}warning message!!!{{/warning~}~}";
		String content = "{{box title=${syntax}}}${text}{{/box}}".replace("${syntax}", syntax).replace("${text}", text);

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageSourceEditor(title,content);
		click(ELEMENT_RICHTEXT_BUTTON);

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Check the availability of Code macro
		waitForAndGetElement(ELEMENT_MACRO_WARNING_MESSAGE.replace("${macro}", "warning message!!!"));
		waitForAndGetElement(ELEMENT_MACRO_BOX.replace("${macro}", text));

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * 	CaseId 78258: Insert macro in a macro (3)
	 */
	@Test
	public void test14_InsertMacroInMacro3(){
		String title = "Page macro3 78258";
		String text = "{{color name = 'red'}} This is tip message {{/color}}";
		String content = "";
		String box_content = "Box content";
		String box_title = "Box title";
		String sourceContent = "{{tip}} {{color name = 'red'}} This is tip message {{/color}} {{/tip}} {{box title=${title}}} Box content {{/box}}";

		//Add source macro in RichText mode
		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		//Create a macro box
		createBoxMacro("Box title","Box content");
		//Create a color macro in macro Tip message
		createMessageMacro("Tip",text);

		//Verify on Source editor
		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		info(waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText());
		info(sourceContent.replace("${title}", "\"" + box_title + "\""));
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains(sourceContent.replace("${title}", "\"" + box_title + "\""));

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Check the availability of Code macro
		waitForAndGetElement("//div[@class='box tipmessage']/span[contains(@style,'color:red;') and contains(text(),'This is tip message')]");
		waitForAndGetElement(ELEMENT_MACRO_BOX.replace("${macro}", box_content));
		waitForAndGetElement("//div[@class='box' and contains(.,'Box title')]");

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}
}
