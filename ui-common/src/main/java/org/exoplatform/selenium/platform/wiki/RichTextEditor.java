package org.exoplatform.selenium.platform.wiki;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.JavascriptExecutor;

import java.awt.event.KeyEvent;
import java.io.File;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RichTextEditor extends WikiLocators{
	Button but;
	/**
	 * constructor
	 * @param dr
	 * @throws Exception 
	 */
	public RichTextEditor(WebDriver dr) throws Exception{
		this.driver=dr;
		but = new Button(driver);
	}
	
	/**
	 * Define tab's types of the attached file popup	
	 */
	public enum attachedFileTabType{
		Current_page,All_pages;
	}

	public enum alignType{
		None,Left,Center,Right,Top,Middle,Bottom;
	}

	public enum wikiPageLinkTab{
		My_Recent_Changes,All_pages,Search;
	}
	
	/**
	 * Click End then Enter in content frame in Rich text mode of Wiki page editor
	 */
	public void typeEnterInRichText(){
		try {
			WebElement inputsummary = null;
			driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
			inputsummary = driver.switchTo().activeElement();
			inputsummary.click();
			inputsummary.sendKeys(Keys.END);
			inputsummary.sendKeys(Keys.ENTER);
			switchToParentWindow();
			Utils.pause(1000);
			driver.switchTo().defaultContent();
		}catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			typeEnterInRichText();
		} catch (ElementNotVisibleException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			typeEnterInRichText();
		}catch (WebDriverException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			Utils.pause(WAIT_INTERVAL);
			driver.switchTo().defaultContent();
			typeEnterInRichText();
		}
		finally {
			loopCount = 0;
		}
	}
	
	/**
	 * Select a macro in a Wiki page editor
	 * 
	 * @param cat
	 * 			category to which a macro that will be chosen belongs
	 * @param macro
	 * 			macro name that will be chosen
	 */
	public void goToMacro(String cat, String macro){
		info("Select a macro: " + macro);
		mouseOverAndClick(ELEMENT_MACRO_LINK);
		mouseOverAndClick(ELEMENT_INSERT_MACRO_LINK);
		if(!cat.isEmpty())
		select(ELEMENT_MACRO_CATEGORY_SELECT, cat);
		if(!macro.isEmpty()){
			mouseOverAndClick(ELEMENT_MACRO_TYPE_FILTER);
			type(ELEMENT_MACRO_TYPE_FILTER,macro,true);
			Utils.pause(1000);
		}
		click(ELEMENT_MACRO_LABEL.replace("${macro}", macro));
		click(but.ELEMENT_SELECT_BUTTON);
		Utils.pause(3000);
	}
	
	/**
	 * Add link to a Wiki page
	 * 
	 * @param search
	 * 			parameter to choose whether to search page link or not
	 * @param page
	 * 			Wiki page that will be the target link
	 * @param label
	 * 			label of link that will be added into Wiki page
	 * @param tooltip
	 * 			
	 */
	public void insertPageLink2WikiPage(boolean search, String page, String label, String tooltip,Object...opParam){
		Boolean verify =(Boolean)(opParam.length>0?opParam[0]:false);
		mouseOverAndClick(ELEMENT_LINK);
		mouseOverAndClick(ELEMENT_WIKI_PAGE_LINK);
		Utils.pause(500);
		info("Create link to the page " + page);
		if (search){
			click(ELEMENT_SEARCH_TAB);
			type(ELEMENT_SEARCH_TEXTBOX_POPUP, page, true);
			click(ELEMENT_SEARCH_BUTTON);
		}
		if(waitForAndGetElement(ELEMENT_PAGE_SELECTED.replace("${page}", page), 5000,0)!=null)
			click(ELEMENT_PAGE_SELECTED.replace("${page}", page));
		else
			click(ELEMENT_PAGE_SELECTED_PLF41.replace("${page}", page));
		click(but.ELEMENT_SELECT_BUTTON);
		if(label!=null && label!="")
			type(ELEMENT_LABEL_LINK_TEXTBOX,label,true);
		if(tooltip!=null && tooltip!="")
			type(ELEMENT_TOOLTIP_LINK_TEXTBOX,tooltip,true);
		Utils.pause(500);
		click(but.ELEMENT_CREATE_LINK_BUTTON);
		waitForElementNotPresent(but.ELEMENT_CREATE_LINK_BUTTON);
		if(verify){
			WebElement e = waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME,DEFAULT_TIMEOUT,1,2);
			driver.switchTo().frame(e);
			if(label!=null && label!="")
				waitForAndGetElement(By.linkText(label));
			if(tooltip!=null && tooltip!="")
				waitForAndGetElement(By.xpath("//*[@title='"+tooltip+"']"));
			switchToParentWindow();
		}
	}
	
	/**
	 * Add table to a Wiki page
	 * 
	 * @param rows
	 * 			Number of rows that will be added in the table
	 * @param columns
	 * 			Number of columns that will be added in the table
	 */
	public void insertTable2WikiPage(String rows, String columns){
		mouseOverAndClick(ELEMENT_TABLE_LINK);
		mouseOverAndClick(ELEMENT_INSERT_TABLE_LINK);
		type(ELEMENT_ROW_TEXTBOX, rows, true);
		type(ELEMENT_COLUMN_TEXTBOX, columns, true);
		click(but.ELEMENT_INSERT_TABLE);
		waitForAndGetElement("//table");
	}
	
	/**
	 * Add macro: "color" into a Wiki page
	 * 
	 * @param color
	 * 			color setting of macro
	 * @param message
	 * 			message setting of macro
	 */
	public void insertMacroColor(String color, String message){
		type(ELEMENT_COLOR_TEXTBOX, color, true);
		type(ELEMENT_COLOR_MESSAGE, message, true);
		goToMacroCreateBtn();
	}
	/**
	 * InsertMacroBox
	 * @param cssClass
	 * @param image
	 * @param title
	 * @param width
	 * @param content
	 */
	public void insertMacroBox(String cssClass, String image,
			String title,String width,String content){
		if(!cssClass.isEmpty()){
			info("Input css class");
			type(ELEMENT_MACRO_BOX_CSSCLASS_FIELD,cssClass, true);
		}
		
        if(!image.isEmpty()){
        	info("Input image");
        	type(ELEMENT_MACRO_BOX_IMAGE_FIELD, image, true);
		}
        
        if(!title.isEmpty()){
        	info("Input title");
        	type(ELEMENT_MACRO_BOX_TITLE_FIELD, title, true);
		}
        
        if(!width.isEmpty()){
        	info("Input width");
        	type(ELEMENT_MACRO_BOX_WIDTH_FIELD, width, true);
		}
        
        if(!content.isEmpty()){
        	info("Input content");
        	type(ELEMENT_MACRO_BOX_CONTENT_FIELD,content, true);
		}
        clickInsertMacroBtn();
		
	}
	/**
	 * Click on Insert Macro button
	 */
	public void clickInsertMacroBtn(){
		info("Click on Insert Macro button");
		click(ELEMENT_CREATE_MACRO_BUTTON);
		waitForElementNotPresent(ELEMENT_CREATE_MACRO_BUTTON);
	}
	
	public enum acceptType{
		yes,no;
	}
	/**
	 * Insert Children macro
	 * @param childNum
	 * @param depth
	 * @param descendantType
	 * @param excerptType
	 * @param parent
	 */
	public void insertChildrenMacro(String childNum,String depth,
			acceptType descendantType,acceptType excerptType,String parent){
		if(!childNum.isEmpty()){
			info("Input child num");
        	type(ELEMENT_MACRO_CHILD_CHILDNUM_FIELD,childNum, true);
		}
		
		if(!depth.isEmpty()){
			info("Input depth");
        	type(ELEMENT_MACRO_CHILD_DEPTH_FIELD,depth, true);
		}
		
		switch(descendantType){
		case yes:
			info("Select yes");
			selectOption(ELEMENT_MACRO_CHILD_DESCENDANT_FIELD,"true");
			break;
		case no:
			info("Select no");
			selectOption(ELEMENT_MACRO_CHILD_DESCENDANT_FIELD,"false");
			break;
		}
		
		switch(excerptType){
		case yes:
			info("Select yes");
			selectOption(ELEMENT_MACRO_CHILD_EXCERPT_FIELD,"true");
			break;
		case no:
			info("Select no");
			selectOption(ELEMENT_MACRO_CHILD_EXCERPT_FIELD,"false");
			break;
		}
		
		if(!parent.isEmpty()){
			info("Input parent");
        	type(ELEMENT_MACRO_CHILD_PARENT_FIELD,parent, true);
		}
		
		 clickInsertMacroBtn();
	}
	/**
	 * Insert macro code
	 * @param cssClass
	 * @param image
	 * @param language
	 * @param title
	 * @param width
	 * @param content
	 */
	public void insertMacroCode(String cssClass,String image,String language,
			String title,String width,String content){
		if(!cssClass.isEmpty()){
			info("Input css class");
			type(ELEMENT_MACRO_CODE_CSSCLASS_FIELD,cssClass, true);
		}
		if(!image.isEmpty()){
			info("Input image");
			type(ELEMENT_MACRO_CODE_IMAGE_FIELD,image, true);
		}
		if(!language.isEmpty()){
			info("Input language");
			type(ELEMENT_MACRO_CODE_LANGUAGE_FIELD,language, true);
		}
		if(!title.isEmpty()){
			info("Input title");
			type(ELEMENT_MACRO_CODE_TITLE_FIELD,title, true);
		}
		if(!width.isEmpty()){
			info("Input width");
			type(ELEMENT_MACRO_CODE_WIDTH_FIELD,width, true);
		}
		if(!content.isEmpty()){
			info("Input content");
			type(ELEMENT_MACRO_CODE_CONTENT_FIELD,content, true);
		}
		clickInsertMacroBtn();
	}
	/**
	 * Insert Macro excerpt
	 * @param hideMode
	 * @param content
	 */
	public void insertMacroExcerpt(acceptType hideMode,String content){
		switch(hideMode){
		case yes:
			info("Select yes");
			selectOption(ELEMENT_MACRO_EXCERPT_DROPBOX,"true");
			break;
		case no:
			info("Select no");
			selectOption(ELEMENT_MACRO_EXCERPT_DROPBOX,"false");
			break;
		}
		
		if(!content.isEmpty()){
			info("Input content");
			type(ELEMENT_MACRO_EXCERPT_CONTENT_FIELD,content, true);
		}
		clickInsertMacroBtn();
	}
	
	/**
	 * Insert Macro Tip message
	 * @param content
	 */
	public void insertMacroMessage(String content){
		if(!content.isEmpty()){
			info("Input content");
			type(ELEMENT_MACRO_MESSAGE_CONTENT_FIELD,content, true);
		}
		clickInsertMacroBtn();
	}
	
	/**
	 * Insert Macro FootNode
	 * @param content
	 */
	public void insertMacroFootNode(String content){
		if(!content.isEmpty()){
			info("Input content");
			type(ELEMENT_MACRO_FOOTNODE_CONTENT_FIELD,content, true);
		}
		clickInsertMacroBtn();
	}
	
	public enum scopeMode{
		PAGE,LOCAL;
	}
	/**
	 * Insert Macro table of content
	 * @param depth
	 * @param numberedMode
	 * @param scope
	 * @param start
	 */
	public void insertMacroTableOfContent(String depth,acceptType numberedMode,
			scopeMode scope,String start){
		if(!depth.isEmpty()){
			info("Input depth");
			type(ELEMENT_MACRO_TABLE_OF_CONTENT_DEPTH_FIELD,depth, true);
		}
		
		switch(numberedMode){
		case yes:
			info("Select yes");
			selectOption(ELEMENT_MACRO_TABLE_OF_CONTENT_NUMBERED_FIELD,"true");
			break;
		case no:
			info("Select no");
			selectOption(ELEMENT_MACRO_TABLE_OF_CONTENT_NUMBERED_FIELD,"false");
			break;
		}
		
		switch(scope){
		case PAGE:
			info("Select Page");
			selectOption(ELEMENT_MACRO_TABLE_OF_CONTENT_SCOPE_FIELD,scopeMode.PAGE.name());
			break;
		case LOCAL:
			info("Select Local");
			selectOption(ELEMENT_MACRO_TABLE_OF_CONTENT_SCOPE_FIELD,scopeMode.LOCAL.name());
			break;
		}
		
		if(!start.isEmpty()){
			info("Input start");
			type(ELEMENT_MACRO_TABLE_OF_CONTENT_START_FIELD,start, true);
		}
		
		clickInsertMacroBtn();
	}
	/**
	 * Insert Macro IFrame
	 * @param height
	 * @param src
	 * @param width
	 */
	public void insertMacroIFrame(String height,String src,String width){
		if(!height.isEmpty()){
			info("Input height");
			type(ELEMENT_MACRO_IFRAME_HEIGHT_FIELD,height, true);
		}
		if(!src.isEmpty()){
			info("Input src");
			type(ELEMENT_MACRO_IFRAME_SRC_FIELD,src, true);
		}
		if(!width.isEmpty()){
			info("Input width");
			type(ELEMENT_MACRO_IFRAME_WIDTH_FIELD,width, true);
		}
		clickInsertMacroBtn();
	}
	/**
	 * Insert Macro JIRA
	 * @param url
	 * @param fieldNames
	 * @param fields
	 * @param source
	 * @param style
	 * @param content
	 */
	public void insertMacroJIRA(String url,String fieldNames,String fields,
			String source,String style,String content){
		if(!url.isEmpty()){
			info("Input url");
			type(ELEMENT_MACRO_JIRA_URL_FIELD,url, true);
		}
		if(!fieldNames.isEmpty()){
			info("Input field Names");
			type(ELEMENT_MACRO_JIRA_FIELD_NAMES_FIELD,fieldNames, true);
		}
		if(!fields.isEmpty()){
			info("Input fields");
			type(ELEMENT_MACRO_JIRA_FIELDS_FIELD,fields, true);
		}
		if(!source.isEmpty()){
			info("Input source");
			type(ELEMENT_MACRO_JIRA_SOURCE_FIELD,source, true);
		}
		if(!style.isEmpty()){
			info("Input style");
			type(ELEMENT_MACRO_JIRA_STYLE_FIELD,style, true);
		}
		if(!content.isEmpty()){
			info("Input content");
			type(ELEMENT_MACRO_JIRA_CONTENT_FIELD,content, true);
		}
		clickInsertMacroBtn();
	}
	/**
	 * Insert Macro HTML
	 * @param cleanMode
	 * @param wikiMode
	 * @param content
	 */
	public void insertMacroHtml(acceptType cleanMode,acceptType wikiMode,String content){
		switch(cleanMode){
		case yes:
			info("Select yes");
			selectOption(ELEMENT_MACRO_HTML_CLEAN_FIELD,"true");
			break;
		case no:
			info("Select no");
			selectOption(ELEMENT_MACRO_HTML_CLEAN_FIELD,"false");
			break;
		}
		
		switch(wikiMode){
		case yes:
			info("Select yes");
			selectOption(ELEMENT_MACRO_HTML_WIKI_NAMES_FIELD,"true");
			break;
		case no:
			info("Select no");
			selectOption(ELEMENT_MACRO_HTML_WIKI_NAMES_FIELD,"false");
			break;
		}
		
		if(!content.isEmpty()){
			info("Input content");
			type(ELEMENT_MACRO_HTML_CONENT_FIELD,content, true);
		}
		clickInsertMacroBtn();
	}

	/**
	 * Open Webpage link
	 */
	public void goToWebPageLink(){
		info("Click on Link menu");
		mouseOverAndClick(ELEMENT_LINK);
		info("Click on Web Page Link menu");
		mouseOverAndClick(ELEMENT_WEB_PAGE_LINK_MENU);
		Utils.pause(500);
	}

	/**
	 * Open Edit link popup
	 */
	public void goToEditLink(){
		info("Click on Link menu");
		mouseOverAndClick(ELEMENT_LINK);
		info("Click on Edit Link menu");
		mouseOverAndClick(ELEMENT_EDIT_LINK_MENU);
		Utils.pause(500);
	}

	/**
	 * Add a simple wiki page with rich text
	 * 
	 * @param title
	 *            updated title of the wiki page. Can not be <code>null</code>
	 * @param content
	 *            updated content of the wiki page. Can not be <code>null</code>
	 */
	public void addSimplePage(String title, String content){
		Utils.pause(2000);
		info("Input a title for the page");
		if(!title.isEmpty())
			type(ELEMENT_TITLE_WIKI_INPUT, title, true);
		info("Input a content for the page");
		if(!content.isEmpty()){
			inputFrame(ELEMENT_CONTENT_WIKI_FRAME, content);
		}
	}

	/**
	 * Edit an attached file link
	 * @param fileName
	 * @param label
	 * @param tooltip
	 */
	public void editAttachedFileLink(String fileName,String label,String tooltip){
		info("Go To Edit Link");
		goToEditLink();
		info("Select the image");
		click(ELEMENT_CURRENT_TAB_ATTACHED_FILE_SELECTED.replace("$file",fileName));
		info("click on Select button");
		click(ELEMENT_SELECT_BUTTON);
		info("Input Label for the page");
		inputLabel(label);
		info("Input Tooltip for the page");
		inputToolTip(tooltip);
		info("Click on Create link button");
		goToCreateLink();
		info("Move focus at the end of the line");
		pressEndKey(this.driver);
	}

	/**
	 * Open Attached File link
	 */
	public void goToAttachedFileLink(){
		info("Click on Link menu");
		mouseOverAndClick(ELEMENT_LINK);
		info("Click on Attached file Link menu");
		mouseOverAndClick(ELEMENT_ATTACHED_FILE_LINK_MENU);
		Utils.pause(500);
	}

	/**
	 * Insert an attached file to the page
	 * @param attachedFile
	 * @param tooltip
	 * @param tab
	 */
	public void insertAttachedFileLink(String attachedFile,Boolean isPreEndKey){
		info("Go to Attached file Link");
		goToAttachedFileLink();
		info("Input attached file link");
		uploadAttachedFile(attachedFile);
		info("Click on Create link button");
		goToCreateLink();
		if(isPreEndKey){
			info("Move focus at the end of the line");
			pressEndKey(this.driver);
		}
	}

	/**
	 * Insert attached File link into the page
	 * @param page
	 * @param attachedFile
	 * @param tooltip
	 * @param tab
	 */
	public void insertAttachedFileLink(String page,String attachedFile,String tooltip,attachedFileTabType tab){
		info("Go to Attached file Link");
		goToAttachedFileLink();
		switch(tab){
		case Current_page:
			info("Open Current page tab");
			goToCurrentPageTab();
			info("Input attached file link");
			uploadAttachedFile(attachedFile);
			goToLinkSetting();
			break;
		case All_pages:
			info("Open All pages tab");
			goToAllPagesTab();
			info("Expand WikiHome node");
			goToExplorerWikiHome();
			info("Select attached file");
			selectAttachedFile(page,attachedFile);
			break;
		}
		info("Input the tooltip of the link");
		inputToolTip(tooltip);
		info("Click on Create link button");
		goToCreateLink();
		info("Move focus at the end of the line");
		pressEndKey(this.driver);
	}

	/**
	 * Modify Wiki content with rich text
	 * 
	 * 
	 * @param title
	 * 			updated title of the wiki page. Can not be <code>null</code>
	 * @param content
	 * 			updated content of the wiki page. Can not be <code>null</code>
	 * 
	 */
	public void inputDataToPage(String title, String content, Boolean isClearTitle, Boolean isClearContent){
		if(title != null){
			if(isClearTitle)
				type(ELEMENT_TITLE_WIKI_INPUT, title, true);
			else
				type(ELEMENT_TITLE_WIKI_INPUT, title, false);
		}
		if (content != null){
			if(isClearContent){
				inputDataToCKEditor(ELEMENT_CONTENT_WIKI_FRAME, content);
			}
			else{
				inputDataToCKEditor(ELEMENT_CONTENT_WIKI_FRAME, content);
			}
			Utils.pause(1000);
			driver.switchTo().defaultContent();
		}
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
	}

	/**
	 * Open wiki page popup
	 */
	public void goToWikiPageLink(){
		info("Click on Link menu");
		mouseOverAndClick(ELEMENT_LINK);
		info("Click on Page Link menu");
		mouseOverAndClick(ELEMENT_WIKI_PAGE_LINK_MENU);
		Utils.pause(500);
	}

	/**
	 * Input email address into EMail link popup
	 * @param address
	 */
	public void inputEmailAddress(String address){
		if(!address.isEmpty()){
			info("Input web address");
			type(ELEMENT_EMAIL_LINK_EMAIL_ADDRESS,address,true);
		}
		Utils.pause(2000);
	}

	/**
	 * Insert email address into the page
	 * @param address
	 * @param label
	 * @param tooltip
	 */
	public void insertEmailLink(String address,String label,String tooltip,Boolean isPressEndKey){
		info("Go to Email Link");
		goToEmailLink();
		info("Input Email for the page");
		inputEmailAddress(address);
		info("Input Label for the page");
		inputLabel(label);
		info("Input Tooltip for the page");
		inputToolTip(tooltip);
		info("Click on Create link button");
		goToCreateLink();
		if(isPressEndKey){
			info("Move focus at the end of the line");
			pressEndKey(this.driver);
		}
	}

	/**
	 * Insert a exist wiki page link into other page
	 * @param search
	 * @param page
	 * @param label
	 * @param tooltip
	 * @param opParam
	 */
	public void insertExistWikiPageLink(String page, 
			String label, String tooltip,wikiPageLinkTab tab){
		switch(tab){
		case My_Recent_Changes:
			info("Select My recent changes tab");
			goToMyRecentChangesTab();
			info("Select a page in the list");
			selectPageInMyRecentChangesTab(page);
			break;
		case All_pages:
			info("Select All pages tab");
			goToAllPagesTab();
			info("Expand WikiHome node");
			goToExplorerWikiHome();
			info("Select a page in the list");
			selectPageInAllPagesTab(page);
			break;
		case Search:
			info("Select Search tab and search the page");
			searchPage(page);
			info("Select a page");
			selectPageInSearchTab(page);
			break;
		}
		info("Input the label of the link");
		inputLabel(label);
		info("Input the tooltip of the link");
		inputToolTip(tooltip);
		info("Click on Create link button");
		goToCreateLink();
		info("Move focus at the end of the line");
		pressEndKey(this.driver);
	}

	/**
	 * Insert a new wiki page into other page
	 * @param page
	 * @param label
	 * @param tooltip
	 * @param tab
	 */
	public void insertNewWikiPageLink(String page, 
			String label, String tooltip,wikiPageLinkTab tab,Boolean isPressEndKey){
		info("Open Wiki Page link popup");
		goToWikiPageLink();
		switch(tab){
		case My_Recent_Changes:
			info("Select My recent changes tab");
			goToMyRecentChangesTab();
			info("Select a page in the list");
			addNewPageInMyRecentChangesTab(page);
			goToLinkSetting();
			break;
		case All_pages:
			info("Select All pages tab");
			goToAllPagesTab();
			info("Double click on Add New Page button");
			doubleClickOnElement(ELEMENT_ALL_PAGES_TAB_ADD_NEW_PAGE_BTN);
			break;
		case Search:
			info("Open Seach tab");
			goToSearchTab();
			info("Create a new wiki page");
			addNewPageInSearchTab(page);
			goToLinkSetting();
			break;
		}
		info("Input the label of the link");
		inputLabel(label);
		info("Input the tooltip of the link");
		inputToolTip(tooltip);
		info("Click on Create link button");
		goToCreateLink();
		if(isPressEndKey){
			info("Move focus at the end of the line");
			pressEndKey(this.driver);
		}
	}
	/**
	 * Input web address
	 * @param address
	 */
	public void inputWebAddress(String address){
		if(!address.isEmpty()){
			info("Input web address");
			type(ELEMENT_WEB_PAGE_WEB_ADDRESS,address,true);
		}
		Utils.pause(2000);
	}

	/**
	 * Edit an image 
	 * @param imageName
	 * @param width
	 * @param height
	 */
	public void editInsertedImage(String imageName,String width,String height,String altText){
		info("Go To Edit Image Link");
		goToEditImageLink();
		info("Select the image");
		click(ELEMENT_IMAGE_LINK_IMAGE_THUMBNAIL.replace("$image",imageName));
		info("click on Select button");
		click(ELEMENT_SELECT_BUTTON);
		if(!width.isEmpty()){
			info("Input width");
			type(ELEMENT_IMAGE_WIDTH,width,true);
		}
		if(!height.isEmpty()){
			info("Input height");
			type(ELEMENT_IMAGE_HEIGHT,height,true);
		}
		if(!altText.isEmpty()){
			info("Change alt text");
			type(ELEMENT_IMAGE_ALTERNATIVE_TEXT,altText,true);
		}
	}

	/**
	 * Open Edit Image link popup
	 */
	public void goToEditImageLink(){
		info("Click on Link menu");
		mouseOverAndClick(ELEMENT_IMAGE_LINK);
		info("Click on Edit image Link menu");
		mouseOverAndClick(ELEMENT_EDIT_IMAGE_LINK_MENU);
		Utils.pause(500);
	}

	/**
	 * Insert a web link into the page
	 * @param address
	 * @param label
	 * @param tooltip
	 */
	public void insertWebLink(String address,String label,String tooltip,Boolean isPressEndKey){
		info("Input web address for the page");
		inputWebAddress(address);
		info("Input Label for the page");
		inputLabel(label);
		info("Input Tooltip for the page");
		inputToolTip(tooltip);
		info("Click on Create link button");
		goToCreateLink();
		if(isPressEndKey){
			info("Move focus at the end of the line");
			pressEndKey(this.driver);
		}
	}

	
	/**
	 * Edit a simple wiki page with rich editor
	 * 
	 * @param newTitle
	 *            updated title of the wiki page. Can not be <code>null</code>
	 * @param newContent
	 *            updated content of the wiki page. Can not be <code>null</code>
	 */
	public void editSimplePage(String newTitle,String newContent){
		info("Input a new title for the page");
		if(!newTitle.isEmpty())
			type(ELEMENT_TITLE_WIKI_INPUT, newTitle, true);
		info("Input a new content for the page");
		if(!newContent.isEmpty()){
			inputFrame(ELEMENT_CONTENT_WIKI_FRAME, newContent);
		}
	}
	/**
	 * Open Email link popup
	 */
	public void goToEmailLink(){
		info("Click on Link menu");
		mouseOverAndClick(ELEMENT_LINK);
		info("Click on Attached file Link menu");
		mouseOverAndClick(ELEMENT_EMAIL_LINK_MENU);
		Utils.pause(500);
	}

	/**
	 * Input a tooltip
	 * @param tooltip
	 */
	public void inputToolTip(String tooltip){
		if(tooltip!=null && tooltip!=""){
			type(ELEMENT_TOOLTIP_LINK_TEXTBOX,tooltip,true);
		}
	}

	/**
	 * Input a label
	 * @param label
	 */
	public void inputLabel(String label){
		if(label!=null && label!=""){
			type(ELEMENT_LABEL_LINK_TEXTBOX,label,true);
		}
	}

	/**
	 * Open External Image link popup
	 */
	public void goToExternalImageLink(){
		info("Click on Link menu");
		mouseOverAndClick(ELEMENT_IMAGE_LINK);
		info("Click on Attached file Link menu");
		mouseOverAndClick(ELEMENT_EXTERNAL_IMAGE_LINK_MENU);
		Utils.pause(500);
	}

	/**
	 * Input an external image into the content of the page
	 * @param link
	 * @param width
	 * @param height
	 * @param altText
	 */
	public void insertExternalImageLink(String link,String width,String height,String altText){
		info("Go to External Image Link");
		goToExternalImageLink();
		info("Input a link");
		inputExternalImageLink(link);
		info("Click on Image Settings button");
		goToImageSettings();
		if(!width.isEmpty()){
			info("Input width");
			type(ELEMENT_IMAGE_WIDTH,width,true);
		}
		if(!height.isEmpty()){
			info("Input height");
			type(ELEMENT_IMAGE_HEIGHT,height,true);
		}
		if(!altText.isEmpty()){
			info("Change alt text");
			type(ELEMENT_IMAGE_ALTERNATIVE_TEXT,altText,true);
		}
	}

	/**
	 * Input an external image link
	 * @param link
	 */
	public void inputExternalImageLink(String link){
		if(!link.isEmpty()){
			info("Input external Image link");
			type(ELEMENT_EXTERNAL_IMAGE_INPUT_LINK,link,true);
		}
	}

	/**
	 * Open Attached Image link popup
	 */
	public void goToAttachedImageLink(){
		info("Click on Link menu");
		mouseOverAndClick(ELEMENT_IMAGE_LINK);
		info("Click on Attached file Link menu");
		mouseOverAndClick(ELEMENT_ATTACHED_IMAGE_LINK_MENU);
		Utils.pause(500);
	}

	/**
	 * Insert an image into the content of the page
	 * @param attachedFile
	 * @param width
	 * @param height
	 * @param altText
	 */
	public void insertImage(String attachedFile,String width,String height,String altText){
		info("Open Current page tab");
		goToCurrentPageTab();
		info("Input attached file link");
		uploadImageFile(attachedFile);
		info("Click on Image Setting button");
		goToImageSettings();
		if(!width.isEmpty()){
			info("Input width");
			type(ELEMENT_IMAGE_WIDTH,width,true);
		}
		if(!height.isEmpty()){
			info("Input height");
			type(ELEMENT_IMAGE_HEIGHT,height,true);
		}
		if(!altText.isEmpty()){
			info("Change alt text");
			type(ELEMENT_IMAGE_ALTERNATIVE_TEXT,altText,true);
		}
		
	}

	/**
	 * Insert an image into the content of the page
	 * @param attachedFile
	 */
	public void insertImage(String attachedFile,Boolean isPressEndKey){
		info("Open Current page tab");
		goToCurrentPageTab();
		info("Input attached file link");
		uploadImageFile(attachedFile);
		info("Click on Insert Image button");
		goToInsertImage();
		if(isPressEndKey){
			info("Move focus at the end of the line");
			pressEndKey(this.driver);
		}
	}

	/**
	 * Delete an image in wiki page
	 * @param content
	 */
	public void removeImage(String content){
		info("Click on Image link");
		mouseOverAndClick(ELEMENT_IMAGE_LINK);
		info("Click on Remove Image link");
		mouseOverAndClick(ELEMENT_REMOVE_IMAGE_LINK_MENU);
		info("Switch to the frame");
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		info("Verify that the image is removed");
		waitForElementNotPresent(ELEMENT_CHECK_IMAGE.replace("${file}",content));
		info("Switch to the parent");
		switchToParentWindow();
		Utils.pause(500);
		info("click on Image link again");
		mouseOverAndClick(ELEMENT_IMAGE_LINK);
		info("Verify that Remove Image link is not shown");
		waitForElementNotPresent(ELEMENT_REMOVE_IMAGE_LINK_MENU);
	}

	
	/**
	 * Select algin of Image
	 * @param type
	 */
	public void selectAlign(alignType type){
		switch(type){
		case None:
			break;
		case Left:
			info("Select left align");
			check(ELEMENT_IMAGE_ALIGN_LEFT,2);
			break;
		case Center:
			info("Select center align");
			check(ELEMENT_IMAGE_ALIGN_CENTER,2);
			break;
		case Right:
			info("Select right align");
			check(ELEMENT_IMAGE_ALIGN_RIGHT,2);
			break;
		case Top:
			info("Select top align");
			check(ELEMENT_IMAGE_ALIGN_TOP,2);
			break;
		case Middle:
			info("Select middle align");
			check(ELEMENT_IMAGE_ALIGN_MIDDLE,2);
			break;
		case Bottom:
			info("Select bottom align");
			check(ELEMENT_IMAGE_ALIGN_BOTTOM,2);
			break;
		}
	}

	/**
	 * Remove a link in wiki page
	 * @param content
	 */
	public void removeLink(String content){
		info("Click on link");
		//mouseOverAndClick(ELEMENT_LINK);
		//click(By.linkText(content));
		click(ELEMENT_LINK);
		info("Click on Remove link");
		//mouseOverAndClick(ELEMENT_REMOVE_LINK_MENU);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_REMOVE_LINK_MENU, DEFAULT_TIMEOUT, 1);
		click(ELEMENT_REMOVE_LINK_MENU);
		info("Switch to the frame");
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
		info("Verify that the link is removed");
		waitForElementNotPresent(By.linkText(content));
		info("Switch to the parent");
		switchToParentWindow();
		Utils.pause(500);
		info("click on link again");
		mouseOverAndClick(ELEMENT_LINK);
		info("Verify that Remove link is not shown");
		waitForElementNotPresent(ELEMENT_REMOVE_LINK_MENU);
	}

	/**
	 * Input a name for a new wiki page link
	 * @param page
	 */
	public void inputNameWikiPageLink(String page){
		info("Input the name of the page");
		type(ELEMENT_INPUT_NAME_NEW_WIKI_PAGE,page,true);
		Utils.pause(2000);
	}

	/**
	 * Click on Create link button on Wiki page popup
	 */
	public void goToCreateLink(){
		Utils.pause(500);
		click(ELEMENT_CREATE_LINK_BUTTON);
		waitForElementNotPresent(ELEMENT_CREATE_LINK_BUTTON);
	}

	/**
	 * Open My Recent Changes tab
	 */
	public void goToMyRecentChangesTab(){
		info("Click on My Recent Changes Tab");
		click(ELEMENT_MY_RECENT_CHANGES_TAB);
		Utils.pause(2000);
	}

	/**
	 * Add a new wiki page in My Recent changes tab
	 * @param page
	 */
	public void addNewPageInMyRecentChangesTab(String page){
		info("Double click on Add New Page button");
		doubleClickOnElement(ELEMENT_MY_RECENT_CHANGES_TAB_ADD_NEW_PAGE_BTN);
		info("Input the name of the page");
		inputNameWikiPageLink(page);
	}

	/**
	 * Add a  new wiki page in Search tab
	 * @param page
	 */
	public void addNewPageInSearchTab(String page){
		info("Double click on Add New Page button");
		doubleClickOnElement(ELEMENT_SEARCH_TAB_ADD_NEW_PAGE_BTN);
		info("Input the name of the page");
		inputNameWikiPageLink(page);
	}

	/**
	 * Upload an attached file link in Attached File link popup
	 * @param link
	 */
	public void uploadAttachedFile(String link){
		info("Double Click on Upload New file button");
		String fs = File.separator;
		String path=getAbsoluteFilePath(link.replace("/", fs));
		info("path in uploadRobot:"+path);
		doubleClickOnElement(ELEMENT_CURRENT_PAGE_TAB_UPLOAD_NEW_FILE_BTN);
		
		Utils.pause(3000);
		((JavascriptExecutor) driver)
				.executeScript("document.getElementsByTagName('input')[0].style.display = 'block';");
		Utils.pause(2000);
		driver.findElement( By.xpath("//*[@name='filepath']"))
					.sendKeys(path);
		/*WebElement elem = waitForAndGetElement(ELEMENT_CURRENT_PAGE_TAB_UPLOAD_NAME,5000,1,2);
		scrollToElement(elem, driver);
		click(elem,2,true);
		uploadFileUsingRobot(link);*/
		Utils.pause(3000);
	}

	/**
	 * Click on Link Setting button
	 */
	public void goToLinkSetting(){
		info("Click on Link Setting button");
		click(ELEMENT_WIKI_PAGE_LINK_LINK_SETTING_BTN);
		Utils.pause(2000);
	}

	/**
	 * Open All Pages Tab
	 */
	public void goToAllPagesTab(){
		info("Click on All Pages tab");
		click(ELEMENT_ALL_PAGE_TAB);
		Utils.pause(2000);
	}

	/**
	 * Open Search tab in Wiki page link popup
	 */
	public void goToSearchTab(){
		info("Select Search tab");
		click(ELEMENT_SEARCH_TAB);
		waitForAndGetElement(ELEMENT_SEARCH_BUTTON);
		info("Search tab's content is shown");
	}

	/**
	 * Search a page in Wiki page popup
	 * @param page
	 */
	public void searchPage(String page){
		goToSearchTab();
		info("Input the page:"+page);
		type(ELEMENT_SEARCH_TEXTBOX_POPUP, page, true);
		info("Search the page");
		click(ELEMENT_SEARCH_BUTTON);
	}

	/**
	 * Open Current page tab in Attached file link popup
	 */
	public void goToCurrentPageTab(){
		info("Click on Current Page Tab");
		click(ELEMENT_CURRENT_PAGE_TAB);
		Utils.pause(2000);
	}

	/**
	 * Upload an image file in Attached Image link popup
	 * @param link
	 */
	public void uploadImageFile(String link){
		info("Double Click on Upload New file button");
		String fs = File.separator;
		String path=getAbsoluteFilePath(link.replace("/", fs));
		info("path in uploadRobot:"+path);
		doubleClickOnElement(ELEMENT_CURRENT_PAGE_TAB_UPLOAD_IMAGE_BTN);
		
		Utils.pause(2000);
		((JavascriptExecutor) driver)
				.executeScript("document.getElementsByTagName('input')[0].style.display = 'block';");
		Utils.pause(2000);
		driver.findElement(By.xpath("//*[@name='filepath']"))
					.sendKeys(path);
		
		/*WebElement elem = waitForAndGetElement(ELEMENT_CURRENT_PAGE_TAB_UPLOAD_NAME,5000,1,2);
		scrollToElement(elem, driver);
		click(elem,2,true);
		uploadFileUsingRobot(link);*/
		Utils.pause(3000);
	}

	/**
	 * Click on Image Settings button on Attached Image link popup
	 */
	public void goToImageSettings(){
		info("Click on Insert Image");
		click(ELEMENT_IMAGE_MENU_IMAGE_SETTINGS_BTN);
		waitForElementNotPresent(ELEMENT_IMAGE_MENU_IMAGE_SETTINGS_BTN);
	}

	/**
	 * Expand Wiki Home node in All pages tab
	 */
	public void goToExplorerWikiHome(){
		if(waitForAndGetElement(ELEMENT_EXPLORER_WIKIHOME,3000,0)!=null){
			info("click on Wiki Home note");
			click(ELEMENT_EXPLORER_WIKIHOME);
		}
		
		Utils.pause(2000);
	}

	/**
	 * Select an attached file in list of All pages tab
	 * @param page
	 * @param attachedFile
	 */
	public void selectAttachedFile(String page,String attachedFile){
		WebElement el = waitForAndGetElement(ELEMENT_ALL_PAGE_TAB_PAGE_SELECTED.replace("$title", page), 5000,1,2);
		scrollToElement(el,this.driver);
		if(waitForAndGetElement(ELEMENT_ALL_PAGE_TAB_PAGE_SELECTED.replace("$title", page), 5000,0)!=null){
			info("Select the page");
			click(ELEMENT_ALL_PAGE_TAB_PAGE_SELECTED.replace("$title", page));
			info("Open Attachment file");
			click(ELEMENT_ALL_PAGE_SELECT_ATTACHEMENT_FILE_PAGE.replace("$page",page));
			info("Select attached file");
			click(ELEMENT_ALL_PAGE_TAB_PAGE_SELECTED.replace("$title",attachedFile));
			info("Click on Select button");
			click(ELEMENT_SELECT_BUTTON);
		}
	}

	
	/**
	 * Click on Insert Image button
	 */
	public void goToInsertImage(){
		info("Click on Insert Image");
		click(ELEMENT_IMAGE_MENU_INSERT_IMAGE_BTN);
		waitForElementNotPresent(ELEMENT_IMAGE_MENU_INSERT_IMAGE_BTN);
	}

	/**
	 * Add a page with checking auto save after 30s
	 * @param title
	 * @param content
	 */
	public void addSimplePageWithAutoSaveStatus(String title, String content){
		info("Input a title for the page");
		if(!title.isEmpty())
			type(ELEMENT_TITLE_WIKI_INPUT, title,true);
		info("Input a content for the page");
		if(!content.isEmpty()){
			inputFrame(ELEMENT_CONTENT_WIKI_FRAME, content);
		}
		info("Waiting 30s before saved all changes");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT,31000,1);
	
	}


	/**
	 * Select a page from All pages tab
	 * @param page
	 */
	public void selectPageInAllPagesTab(String page){
		WebElement el = waitForAndGetElement(ELEMENT_ALL_PAGE_TAB_PAGE_SELECTED.replace("$title", page), 5000,1,2);
		scrollToElement(el,this.driver);
		if(waitForAndGetElement(ELEMENT_ALL_PAGE_TAB_PAGE_SELECTED.replace("$title", page), 5000,0)!=null){
			info("Select the page");
			click(ELEMENT_ALL_PAGE_TAB_PAGE_SELECTED.replace("$title", page));
			info("Click on Select button");
			click(ELEMENT_SELECT_BUTTON);
		}
		
	}

	/**
	 * Select a page in Search Tab
	 * @param page
	 */
	public void selectPageInSearchTab(String page){
		if(waitForAndGetElement(ELEMENT_SEARCH_TAB_PAGE_SELECTED.replace("${page}", page), 5000,0)!=null){
			info("Select the page");
			click(ELEMENT_SEARCH_TAB_PAGE_SELECTED.replace("${page}", page));
			info("Click on Select button");
			click(ELEMENT_SELECT_BUTTON);
		}
		
	}
	/**
	 * Select a page in My Recent Changes list
	 * @param page
	 */
	public void selectPageInMyRecentChangesTab(String page){
		WebElement el = waitForAndGetElement(ELEMENT_MY_RECENT_CHANGES_TAB_PAGE_SELECTED.replace("$title", page), 5000,1,2);
		scrollToElement(el,this.driver);
		if(waitForAndGetElement(ELEMENT_MY_RECENT_CHANGES_TAB_PAGE_SELECTED.replace("$title", page), 5000,0)!=null){
			info("Select the page");
			click(ELEMENT_MY_RECENT_CHANGES_TAB_PAGE_SELECTED.replace("$title", page));
			info("Click on Select button");
			click(ELEMENT_SELECT_BUTTON);
		}
	}

	/**
	 * Remove an image
	 */
	public void goToRemoveImageLink(){
		info("Click on Link menu");
		mouseOverAndClick(ELEMENT_IMAGE_LINK);
		info("Click on Edit image Link menu");
		mouseOverAndClick(ELEMENT_REMOVE_IMAGE_LINK_MENU);
		Utils.pause(500);
	}

	/**
	 * Edit a wiki page with auto save status
	 * @param newTitle
	 * @param newContent
	 */
	public void editSimplePageWithAutoSave(String newTitle, String newContent){
		info("Input a title for the page");
		if(!newTitle.isEmpty())
			type(ELEMENT_TITLE_WIKI_INPUT, newTitle,true);
		info("Input a content for the page");
		if(!newContent.isEmpty()){
			inputFrame(ELEMENT_CONTENT_WIKI_FRAME, newContent);
		}
		info("Waiting 30s before saved all changes");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT,31000,0);
	}

	/**
	 * Add a new page that has auto save without save
	 * @param title
	 * @param content
	 */
	public void addSimplePageHasAutoSaveWithoutSave(String title, String content){
		info("Input a title for the page");
		if(!title.isEmpty())
			type(ELEMENT_TITLE_WIKI_INPUT, title,true);
		info("Input a content for the page");
		if(!content.isEmpty()){
			inputFrame(ELEMENT_CONTENT_WIKI_FRAME, content);
		}
		info("Waiting 30s before saved all changes");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT,31000,1);
		info("Cancel adding page");
		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE,0,true);
		click(ELEMENT_CONFIRMATION_POPUP_YES_BTN);
		Utils.pause(2000);
	}

	/**
	 * Replace a new link for old link that inserted into the page
	 * @param label 
	 * @param xOffsetLabel
	 * @param yOffsetLabel
	 */
	public void changeLink(String label){
		info("Focus on the frame");
		switchFrame(ELEMENT_CONTENT_WIKI_FRAME);
		selectLabelLink(label);
		switchToParentWindow();
	}
	/**
	 * Select the image
	 * @param altTextImage
	 */
	public void selectImage(String altTextImage){
		info("Focus on the frame");
		switchFrame(ELEMENT_CONTENT_WIKI_FRAME);
		WebElement element = driver.findElement(By.xpath(ELEMENT_WIKI_CONTENT_IMAGE_ALT
				.replace("$alt",altTextImage)));
		selectItems(element);
		switchToParentWindow();
	}
	
	/**
	 * Select the label's link that is inserted into the page
	 * @param driver
	 */
	public void selectLabelLink(String label){
		info("Select a line text");
		WebElement element = driver.findElement(By.linkText(label));
		selectItems(element);
	}
	/**
	 * Select element by click and hold
	 * @param el
	 */
	public void selectItems(WebElement el){
		action = new Actions(driver);
		action.moveToElement(el)
		.clickAndHold()
		.perform();
		action.release();
	}

	/**
     * Uncheck Open New Window checkbox
	 */
	public void uncheckOpenNewWindow(){
		info("Uncheck Open New Window checkbox");
		uncheck(ELEMENT_OPEN_NEW_WINDOW_CHECKBOX,2);
		Utils.pause(2000);
	}

	/**
	 * Check Open New Window checkbox
	 */
	public void checkOpenNewWindow(){
		info("Check Open New Window checkbox");
		check(ELEMENT_OPEN_NEW_WINDOW_CHECKBOX,2);
		Utils.pause(2000);
	}

	/** 
	 * Attach a file to a Wiki page
	 * 
	 * @param link
	 * 			link of file that will be attached
	 */
	/** 
	 * Attach a file to a Wiki page
	 * 
	 * @param link
	 * 			link of file that will be attached
	 * @param type
	 * 			optional parameter of this method.
	 */
	public void attachFile(String link ){
		String fs = File.separator;
		WebElement elem = waitForAndGetElement(ELEMENT_UPLOAD_NAME,5000,1,2);
		scrollToElement(elem, driver);
		click(elem,2,true);
		uploadFileUsingRobot(link);
		waitForAndGetElement(By.linkText(link.substring(link.lastIndexOf(fs)+1)));
	}
	/**
	 * Insert Macro RSS
	 * @param content
	 * @param count
	 * @param decoration
	 * @param feed
	 * @param image
	 * @param width
	 */
	public void insertMacroRSS(acceptType content,String count,acceptType decoration,
			String feed,acceptType image,String width){
		switch(content){
		case yes:
			info("Select yes");
			selectOption(ELEMENT_MACRO_RSSS_CONTENT_FIELD,"true");
			break;
		case no:
			info("Select no");
			selectOption(ELEMENT_MACRO_RSSS_CONTENT_FIELD,"false");
			break;
		}
		
		if(!count.isEmpty()){
			info("Input count");
			type(ELEMENT_MACRO_RSS_COUNT_FIELD,count, true);
		}
		
		switch(decoration){
		case yes:
			info("Select yes");
			selectOption(ELEMENT_MACRO_RSS_DECORATION_FIELD,"true");
			break;
		case no:
			info("Select no");
			selectOption(ELEMENT_MACRO_RSS_DECORATION_FIELD,"false");
			break;
		}
		
		if(!feed.isEmpty()){
			info("Input feed");
			type(ELEMENT_MACRO_RSS_FEED_FIELD,feed, true);
		}
		
		switch(image){
		case yes:
			info("Select yes");
			selectOption(ELEMENT_MACRO_RSS_IMAGE_FIELD,"true");
			break;
		case no:
			info("Select no");
			selectOption(ELEMENT_MACRO_RSS_IMAGE_FIELD,"false");
			break;
		}
		
		if(!width.isEmpty()){
			info("Input width");
			type(ELEMENT_MACRO_RSS_WIDTH_FIELD,width, true);
		}
		clickInsertMacroBtn();
	}
	
	/**
	 * Insert macro: "JIRA" into a Wiki page
	 * 
	 * @param URL
	 * 			URL setting of macro
	 * @param Content
	 * 			Content setting of macro
	 */
	public void insertMacroJIRA(String URL, String Content){
		info("Go to insert a macro Jira");
		if (URL != null && URL != ""){
			info("Insert URL");
			waitForAndGetElement(ELEMENT_JIRA_URL);
			type(ELEMENT_JIRA_URL, URL, true);
		}
		
		if (Content != null && Content != ""){
			info("Insert Content");
			waitForAndGetElement(ELEMENT_JIRA_CONTENT);
			type(ELEMENT_JIRA_CONTENT, Content, true);
		}
	 Utils.pause(1000);
	}
	/**
	 * Click on Create button of Insert Macro form
	 */
	public void goToMacroCreateBtn(){
		info("Click on Create button");
		click(but.ELEMENT_CREATE_MACRO_BUTTON);
		waitForElementNotPresent(but.ELEMENT_CREATE_MACRO_BUTTON);
		Utils.pause(3000);
	}
	
	/**
	 * Open Edit Macro form
	 */
	public void goToEditMacro(){
		info("Click on Macro link");
		mouseOverAndClick(ELEMENT_MACRO_LINK);
		info("Click on Edit Macro link");
		mouseOverAndClick(ELEMENT_EDIT_MACRO_LINK);
		Utils.pause(500);
	}
	/**
	 * Collapse all macro
	 * @param control
	 * 				true if use key 				
	 */
	public void CollapseAllMacro(boolean control){
			info("Collapse all macro");
			Utils.pause(2000);
			if (control){
				info("Using Ctrl + Shift + C");
				Utils.javaSimulateKeyPress(KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT,KeyEvent.VK_C);
				driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
			}
			else{
				info("Click on collapse link");
				waitForAndGetElement(ELEMENT_MACRO_LINK);
				mouseOverAndClick(ELEMENT_MACRO_LINK);
				waitForAndGetElement(ELEMENT_MACRO_COLLAPSE_LINK);
				mouseOverAndClick(ELEMENT_MACRO_COLLAPSE_LINK);
				driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
			}
	}
	
	/**
	 * Expand all macro
	 * @param control
	 * 				true if use key 				
	 */
	public void ExpandAllMacro(boolean control){
			info("Expand all macro");
			if (control){
				info("Using Ctrl + Shift + E");
				Utils.javaSimulateKeyPress(KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT,KeyEvent.VK_E);
				driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
			}
			else{
				info("Click on expand link");
				waitForAndGetElement(ELEMENT_MACRO_LINK);
				mouseOverAndClick(ELEMENT_MACRO_LINK);
				waitForAndGetElement(ELEMENT_MACRO_EXPAND_LINK);
				mouseOverAndClick(ELEMENT_MACRO_EXPAND_LINK);
				driver.switchTo().frame(waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME));
			}
	}
	
	/**
	 * Verify after collapse macro
	 * @param control
	 * 				true if use key 				
	 */
	public void verifyCollapsemacro(String macro){
		info("Verify collapse macro");
		waitForAndGetElement(ELEMENT_MACRO_COLLAPSED_LINK.replace("${macro}", macro));
	}
	
	public enum macroCategories{
		JIRA,COLOR;
	}
	
	/**
	 * Verify after expand macro
	 * @param control
	 * 				true if use key 				
	 */
	public void verifyExpandmacro(macroCategories macroCate, String ...Content ){
		String content = (Content.length > 0 ? Content[0]: null);
		String color = (Content.length > 1 ? Content[1]: null);
		info("Verify collapse macro");
		switch(macroCate){
		case COLOR :
			info ("Verify Color macro when expanding");
			waitForAndGetElement(ELEMENT_MACRO_TEXT.replace("${text}", content).replace("${color}", color));
			break;
		case JIRA :
			info ("Verify Jira macro when expanding");
			waitForAndGetElement(ELEMENT_JIRA_MACRO_LINK.replace("${content}", content));
			break;
		}
	}
	
	/**
	 * Select the JIRA Macro
	 */
	public void selectJIRAMacro(){
		info("Focus on the frame");
		switchFrame(ELEMENT_CONTENT_WIKI_FRAME,1);
		Utils.pause(2000);
		WebElement element = driver.findElement(ELEMENT_JIRA_TABLE);
		selectItems(element);
		element.click();
		switchToParentWindow();
	}
	/**
	 * Click on Apply button of Macro Edit form
	 */
	public void goToMacroEditFormApplyBtn(){
		info("click on Apply button");
		click(ELMENET_MACRO_JIRA_EDIT_FORM_APPLY_BTN);
		waitForElementNotPresent(ELMENET_MACRO_JIRA_EDIT_FORM_APPLY_BTN);
		Utils.pause(5000);
	}
}
	


