package org.exoplatform.selenium.platform.wiki.functional.macro;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.List;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.WikiBase;
import org.exoplatform.selenium.platform.wiki.RichTextMode;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.exoplatform.selenium.platform.wiki.ManageDraft;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Wiki_Macro_Add extends ManageDraft{

	ManageAccount magAc;
	Button button;
	WikiBase magWiki;
	RichTextMode magRTM;
	BasicAction magWikiAction;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		magAc = new ManageAccount(driver);
		button = new Button(driver);
		magWiki = new WikiBase();
		magRTM = new RichTextMode();
		magWikiAction = new BasicAction();
		magAc.signIn("john", "gtn"); 
		goToWiki();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * CaseId 78346: Insert Box macro
	 */

	@Test	
	public void test01_InsertBoxMacro(){
		String title = "Page 69763";
		String content = "";

		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		// Insert Box macro
		info("Insert Box Message macro:");
		createBoxMacro("Box title","Box content");

		//Check the availability of Box macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_BOX.replace("${macro}", "Box content"));

		switchToParentWindow();

		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		info(waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText());
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("Box title");
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("Box content");
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("{{/box}}");


		savePage();
		Utils.pause(1000);

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * CaseId 78347: Insert Children macro
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
		addWikiPageRichText(title1, content1);
		typeEnterInRichText();
		savePage();

		addWikiPageRichText(title2, content2);
		typeEnterInRichText();
		savePage();

		addWikiPageRichText(title3, content3);
		typeEnterInRichText();
		savePage();

		goToWikiPage(title1);
		addWikiPageRichText(title4, content4);
		typeEnterInRichText();
		savePage();

		//Insert Children macro in Page1
		goToWikiPage(title1);
		mouseOverAndClick(ELEMENT_EDIT_PAGE_LINK);
		waitForElementNotPresent(ELEMENT_EDIT_PAGE_LINK);
		typeEnterInRichText();
		info("Insert Children macro:");
		createChildrenMacro(descendant);	

		//Check the availability of Children macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(By.linkText(title2));
		waitForAndGetElement(By.linkText(title3));
		waitForAndGetElement(By.linkText(title4));
		switchToParentWindow();

		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		info(waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText());
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("{{children descendant=");
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("true");


		//Save wiki Page with Children macro
		savePage();
		waitForAndGetElement(ELEMENT_MACRO_CHILDREN.replace("${name}", title2));
		waitForAndGetElement(ELEMENT_MACRO_CHILDREN.replace("${name}", title3));
		waitForAndGetElement(ELEMENT_MACRO_CHILDREN.replace("${name}", title4));


		//Delete wiki page before exit test case
		goToWikiPage(title1);
		deleteCurrentWikiPage();
	}

	/**
	 * * CaseId 78348: Insert Code macro
	 */
	@Test	
	public void test03_InsertCodeMacro(){
		String title = "Page 69765";
		String content = "";
		String language = "html";
		String macro_title = "Test code macro";
		String macro_content = "<html><head>Cool!</head></html>";

		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		//Insert Code macro
		info("Insert Code macro:");
		createCodeMacro(language,macro_title,macro_content);

		//Save wiki Page with Code macro
		savePage();

		//Check the availability of Code macro
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${color}", "#8B008B;").replace("${text}", "<html><head>"));
		waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${color}", "#8B008B;").replace("${text}", "</head></html>"));
		waitForAndGetElement(ELEMENT_MACRO_CODE.replace("${macro}", "Test code macro"));
		waitForAndGetElement(ELEMENT_MACRO_CODE.replace("${macro}", "Cool!"));

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * 	CaseId 78351: Insert Excerpt macro
	 */
	@Test	
	public void test04_InsertExcerptMacro(){
		String title = "Page 69768";
		String content = "";
		String type = "Excerpt";
		String macro_content = "Test Excerpt macro.";

		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		//Insert Excerpt macro
		info("Insert Excerpt macro:");
		createMessageMacro(type,macro_content);

		//Check the availability of Excerpt macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_EXCERPT.replace("${macro}", macro_content));
		driver.switchTo().defaultContent();

		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		info(waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText());
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains(macro_content);
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("{{excerpt}}");

		//Save wiki Page
		savePage();
		waitForAndGetElement(ELEMENT_MACRO_CHECK_MESSAGE.replace("${message}", macro_content));

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 *  CaseId 78354: Insert Info Message macro
	 */
	@Test	
	public void test05_InsertInfoMessageMacro(){
		String title = "Page 69771";
		String content = "";
		String type = "Info";
		String macro_content = "This is Info Message macro.";

		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		//Insert Info Message macro
		info("Insert Info Message macro:");
		createMessageMacro(type,macro_content);

		//Check the availability of Info Message macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_INFO_MESSAGE.replace("${macro}", macro_content));
		driver.switchTo().defaultContent();

		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		info(waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText());
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains(macro_content);
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("{{info}}");



		//Save wiki Page
		savePage();
		waitForAndGetElement(ELEMENT_MACRO_CHECK_MESSAGE.replace("${message}", macro_content));	

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 *  CaseId 78361: Insert Table of Content macro
	 */
	@Test	
	public void test06_InsertTableOfContentMacro(){
		String title = "Page"+getRandomNumber();
		String content = "=Heading1=</br>== Heading2 ==</br>=== Heading3 ===";

		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageSourceEditor(title,content);
		click(ELEMENT_RICHTEXT_BUTTON);

		//Insert Table of Content macro
		info("Insert Table of Content macro:");
		createTableOfContentsMacro();

		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		info(waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText());
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("{{toc/}}");		

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		waitForAndGetElement("//ul/li/span/a[text()='Heading1']");
		waitForAndGetElement("//ul/li/ul//a[text()='Heading2']");
		waitForAndGetElement("//ul/li/ul/li/ul//a[text()='Heading3']");

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * 	CaseId 78362: Insert Tip Message macro
	 */
	@Test	
	public void test07_InsertTipMessageMacro(){
		String title = "Page 69782";
		String content = "";
		String type = "Tip";
		String macro_content = "This is Tip Message macro.";

		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		// Insert Tip Message macro
		info("Insert Tip Message macro:");
		createMessageMacro(type,macro_content);

		//Check the availability of Tip Message macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_TIP_MESSAGE.replace("${macro}", macro_content));
		driver.switchTo().defaultContent();

		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		info(waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText());
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("{{tip}}");		
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains(macro_content);		

		//Save wiki Page
		savePage();
		waitForAndGetElement(ELEMENT_MACRO_CHECK_MESSAGE.replace("${message}", macro_content));	


		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * 	CaseId 78363: Insert Warning Message
	 */
	@Test	
	public void test08_InsertWarningMessageMacro(){
		String title = "Page 69783";
		String content = "";
		String type = "Warning";
		String macro_content = "This is Warning Message macro.";

		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		// Insert Warning Message
		info("Insert Warning Message macro:");
		createMessageMacro(type,macro_content);

		//Check the availability of Warning Message macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_WARNING_MESSAGE.replace("${macro}", macro_content));
		switchToParentWindow();

		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		info(waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText());
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("{{warning}}");		
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains(macro_content);		

		//Save wiki Page
		savePage();
		waitForAndGetElement(ELEMENT_MACRO_CHECK_MESSAGE.replace("${message}", macro_content));	

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * CaseId 78510: Insert Success Message
	 */
	@Test	
	public void test09_InsertSuccessMessageMacro(){
		String title = "Page 71253";
		String content = "";
		String type = "Success";
		String macro_content = "This is Success Message macro.";

		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		//Insert Success Message
		info("Insert Success macro:");
		createMessageMacro(type,macro_content);

		//Check the availability of Success Message macro
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(ELEMENT_MACRO_SUCCESS_MESSAGE.replace("${macro}", macro_content));
		switchToParentWindow();

		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		info(waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText());
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains("{{success}}");		
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains(macro_content);		

		//Save wiki page
		savePage();
		waitForAndGetElement(ELEMENT_MACRO_CHECK_MESSAGE.replace("${message}", macro_content));	

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * CaseId 78534: Insert JIRA macro
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
	 * CaseId 99101: Insert Code macro by source
	 * Bug: WIKI-546 - [PLF-jboss] Code macro doesn't work
	 */
	@Test
	public void test11_InsertSourceMacro(){
		String title = "Page 94914";
		String content = "= Code macro = "
				+"== Java with title == "
				+"{{code language='java' title='HelloWorld.java'}}"
				+"System.out.println('Hello World');"
				+"{{/code}}"
				+"== Java without title =="
				+"{{code language='java'}}"
				+"System.out.println('Hello World');"
				+"{{/code}}"
				+"== HTML =="
				+"use [[xwiki example>>http://extensions.xwiki.org/xwiki/bin/view/Extension/Code+Macro]]"
				+"{{code language='html'}}"
				+"<html>"
				+"<head>How cool?</head>"
				+"</html>{{/code}}";

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		addWikiPageSourceEditor(title,content);
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);

		//Check the availability of Code macro
		waitForTextPresent("Hello World");
		waitForTextPresent("How cool?");
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
	 *  CaseId 78590: Insert macro in a macro (1)
	 */
	@Test
	public void test12_InsertMacroInMacro1(){
		String title = "Page macro1 78590";
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
	@Test
	public void test13_InsertMacroInMacro2(){
		String title = "Page macro2 78591";
		String text = "Test macro in macro 02";
		String syntax = " {{warning}}warning message!!!{{/warning}} ";
		String content = "{{box title='${text}' width='100%'}}${syntax} ${text}{{/box}}".replace("${syntax}", syntax).replace("${text}", text);

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
	 * 	CaseId 78591: Insert macro in a macro (3)
	 */
	@Test
	public void test14_InsertMacroInMacro3(){
		String title = "Page macro3"+getRandomNumber();
		String text = "{{color name = 'red'}} This is tip message {{/color}}";
		String content = "";
		String box_content = "Box content";
		String box_title = "Box title";
		String sourceContent = "{{tip}} {{color name = 'red'}} This is tip message {{/color}} {{/tip}} " +"\\" + "\\" + " {{box title=${title}}} Box content {{/box}}";

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		createBoxMacro("Box title","Box content");
		createMessageMacro("Tip",text);

		//Verify on Source editor
		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		info(waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText());
		info(sourceContent.replace("${title}", "\"" + box_title + "\""));

		//Save wiki page
		savePage();

		//Check the availability of Code macro
		waitForAndGetElement("//div[@class='box tipmessage']/span[contains(@style,'color:red;') and contains(text(),'This is tip message')]");
		waitForAndGetElement(ELEMENT_MACRO_BOX.replace("${macro}", box_content));
		waitForAndGetElement("//div[@class='box' and contains(.,'Box title')]");

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/*
	 * Cases in 4.1.x
	 * Pie chart
		Formula
		HTML
		Footnote
		Put Footnote
		RSS macro1
		RSS macro2
		line chart
		bar chart
		bar 3D
		Time Series  xyline and shape
		Time series xy line3D
		Time series step
		Custom color
	 */
	/**
	 * CaseID: 78577 - Insert HTML macro
	 */
	@Test
	public void test15_InsertHTMLmacro(){
		String title = "InsertHTML macro";
		String clean = "true";
		String wiki = "true";
		String content = "<table><tr><td>* listitem</td></tr></table>";
		String cont = "";

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, cont);
		typeEnterInRichText();

		//Check the availability of Code macro
		createHTMLMacro(clean, wiki, content);
		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		//Save wiki page

		savePage();
		waitForAndGetElement("//*[@id='UIViewContentDisplay']/table//tr/td[contains(text(),'listitem')]");

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * CaseID: 78578 - Insert Footnote macro
	 */
	@Test
	public void test16_InsertFootNoteMacro(){
		String title = "Insert Footnote";
		String type = "Footnote";
		String content = "test";
		String cont = "";
		String sourceContent = "{{footnote}} test {{/footnote}}";

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, cont);
		typeEnterInRichText();

		//Check the availability of Code macro
		createMessageMacro(type, content);
		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains(sourceContent.replace("${wiki}", "\"true\""));

		//Save wiki page
		savePage();
		waitForAndGetElement(By.linkText("1"));
		waitForAndGetElement(ELEMENT_MACRO_FOOTNOTE.replace("${macro}", content));

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * CaseID 78579: Insert Put Footnote macro
	 */
	@Test
	public void test17_InsertPutFootnotemacro(){
		String title = "Insert Footnote";
		String boxContent = "Box Content 78579";
		String sourceContent = "= A title = </br>"
				+ "Lorem ipsum dolor sit amet{{footnote}}Footnote 1{{/footnote}}, consectetuer adipiscing elit. Vivamus lacus est, euismod at, lobortis eu, rhoncus"
				+"et, leo{{footnote}}Footnote 2{{/footnote}}.</br>"
				+"</br>{{putFootnotes/}}";

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		addWikiPageSourceEditor(title, sourceContent);
		click(ELEMENT_RICHTEXT_BUTTON);

		//Create box macro
		createBoxMacro("", boxContent);

		//Verify macro in Rich Text mode
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		waitForAndGetElement(By.linkText("1"));
		waitForAndGetElement(By.linkText("2"));
		waitForAndGetElement(ELEMENT_MACRO_FOOTNOTE.replace("${macro}", "Footnote 2"));
		waitForAndGetElement(ELEMENT_MACRO_FOOTNOTE.replace("${macro}", "Footnote 1"));
		waitForAndGetElement(ELEMENT_MACRO_BOX.replace("${macro}", boxContent));
		switchToParentWindow();

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	
		waitForAndGetElement(By.linkText("1"));
		waitForAndGetElement(By.linkText("2"));
		waitForAndGetElement(ELEMENT_MACRO_FOOTNOTE.replace("${macro}", "Footnote 2"));
		waitForAndGetElement(ELEMENT_MACRO_FOOTNOTE.replace("${macro}", "Footnote 1"));
		waitForAndGetElement(ELEMENT_MACRO_BOX.replace("${macro}", boxContent));

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	/**
	 * CaseID 78580 - Insert RSS macro (1)
	 */
	@Test
	public void test18_InsertRSSmacro1(){
		String title = "Insert_RSS_macro1";
		String content = "true";
		String count = "2";
		String decoration = "true";
		String image = "true";
		String feed = "http://feeds.feedburner.com/massol";
		String width = "px";
		String cont = "";
		String sourceContent = "{{rss content=\"true\" count=\"2\" feed=\"http://feeds.feedburner.com/massol\" image=\"true\" width=\"px\"/}}";

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, cont);
		typeEnterInRichText();

		//Check the availability of Code macro
		createRssMacro(content, count, decoration, feed, image, width);
		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains(sourceContent.replace("\\", ""));

		//Save wiki page
		savePage();

		//Check if count of RSS item is 2
		waitForAndGetElement("//div[@class='box rssfeed']");
		List<WebElement> rssItem = driver.findElements(ELEMENT_MACRO_RSS_TITLE);
		assert rssItem.size() == 2;

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();

	}

	/**
	 * CaseID 78582 - Insert RSS macro (2)
	 */
	@Test
	public void test19_InsertRSSMacro2(){

		String title = "Insert_RSS_macro2";
		String cont = "{{rss feed='http://feeds.feedburner.com/massol' image='true' content='true' width='90%' count='3'/}}";

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		goToAddBlankPage();
		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		addWikiPageSourceEditor(title,cont);

		//Save wiki page
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		//Check if count of RSS item is 3
		waitForAndGetElement("//div[@class='box rssfeed']");
		List<WebElement> rssItem = driver.findElements(ELEMENT_MACRO_RSS_TITLE);
		assert rssItem.size() == 3;

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}

	@Test
	public void test20_InsertTimeSerialChartUsingXYlineAndShapemacro(){
		String height = "300";
		String params = "range:B2-C19;dataset:timetable_xy;domain_axis_type:date;domain_axis_date_format:MMM-yyyy;date_format:yyyy-MM;time_period:month;range_axis_lower:100;range_axis_upper:190";
		String source = "inline";
		String title = "xy_line_and_shape";
		String type = "xy_line_and_shape";
		String[] content = {"|=|=Series1|=Series2","|2001-2|181.8|129.6","|2001-3|167.3|123.2","|2001-4|153.8|117.2","|2001-5|167.6|124.1",
				"|2001-6|158.8|122.6","|2001-7|148.3|119.2", "|2001-8|153.9|116.5", "|2001-9|142.7|112.7","|2001-10|123.2|101.5",
				"|2001-11|131.8|106.1", "|2001-12|139.6|110.3", "|2002-1|142.9|111.7", "|2002-2|138.7|111.0", "|2002-3|137.3|109.6",
				"|2002-4|143.9|113.2", "|2002-5|139.8|111.6", "|2002-6|137.0|108.8", "|2002-7|132.8|101.6"};
		String cont = "";
		String width = "400";
		String source_code = "{{chart params=${params} source=${inline} title=${title} type=${type}}} |=|=Series1|=Series2 |2001-2|181.8|129.6 |2001-3|167.3|123.2 |2001-4|153.8|117.2 |2001-5|167.6|124.1 |2001-6|158.8|122.6 |2001-7|148.3|119.2 |2001-8|153.9|116.5 |2001-9|142.7|112.7 |2001-10|123.2|101.5 |2001-11|131.8|106.1 |2001-12|139.6|110.3 |2002-1|142.9|111.7 {{/chart}}";
		String content_source = source_code.replace("${params}", "\"" + params + "\"")
				.replace("${inline}", "\"" + source + "\"")
				.replace("${title}", "\"" + title +"\"")
				.replace("${type}", "\"" + type + "\"");

		//Add source macro in SourceEditor
		info("Add new wiki page at Rich Text mode:");
		goToWiki();
		addWikiPageRichText(title, cont);
		typeEnterInRichText();

		//Save wiki page
		createChartMacro(height,  params, source, title, type, content,width);
		//click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		//waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);	

		info("-- Check in Source editor --");
		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains(content_source);
		savePage();

		//Check the availability of Code macro
		waitForAndGetElement(ELEMENT_MACRO_CHART.replace("${title}", title));

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();			
	}

	@Test
	public void test21_InsertIFrameMacro(){
		String title = "Page IFrame";
		String content = "Page IFram Content";
		String height = "400";	
		String source = "http://www.w3schools.com";		
		String width = "400";
		String source_content = "{{iframe height=\"400\" src=\"http://www.w3schools.com\" width=\"400\"/}}";

		//Add new Rich Editor Wiki page
		info("Add new wiki page at Rich Text mode:");
		addWikiPageRichText(title, content);
		typeEnterInRichText();

		//Add source macro in SourceEditor
		createIFrameMacro(width, height, source);
		click(ELEMENT_SOURCE_EDITOR_BUTTON);
		assert waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT).getText().contains(source_content.replace("\\", ""));

		//Save wiki page
		savePage();

		//Delete wiki page before exit test case
		deleteCurrentWikiPage();
	}
}
