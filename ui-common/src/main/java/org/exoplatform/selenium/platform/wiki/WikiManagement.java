package org.exoplatform.selenium.platform.wiki;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.exoplatform.selenium.Button;

public class WikiManagement extends WikiLocators{

	ManageAlert alert;
	Button but ;
	
	/**
	 * constructor
	 * @param dr
	 */
	public WikiManagement(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(dr);
		but = new Button(dr);
	}

	/**
	 * Select template to create page
	 */
	public void selectTemplateWikiPage(String template){
		info("--Select a template--");
		waitForAndGetElement(ELEMENT_TEMPLATE_SELECT_FORM);
		By eTemplate = By.xpath(ELEMENT_SELECT_TEMPLATE_LINK.replace("${template}",template));
		info("eTemplate:"+eTemplate.toString());
		click(eTemplate, 2,true);
	}

	/**
	 * Change to Source Editor mode
	 */
	public void goToSourceEditor(){
		if(waitForAndGetElement(ELEMENT_SOURCE_EDITOR_BUTTON,5000,0)!=null){
			info("Go to Source Editor");
			click(ELEMENT_SOURCE_EDITOR_BUTTON,0,true);	
		}
		waitForAndGetElement(ELEMENT_CONTENT_WIKI_INPUT);
	}

	/**
	 * Change to Rich Text Mode
	 */
	public void goToRichTextEditor(){
		if(waitForAndGetElement(ELEMENT_RICHTEXT_BUTTON,5000,0)!=null){
			info("Go to Rich Text Mode");
			click(ELEMENT_RICHTEXT_BUTTON,0,true);	
		}
		waitForAndGetElement(ELEMENT_CONTENT_WIKI_FRAME);
	}

	
	/**
	 * Add a content for a wiki page
	 * @param content
	 */
	public void addContentPage(String content){
		info("Input a content for the page");
		if(!content.isEmpty())
			inputFrame(ELEMENT_CONTENT_WIKI_FRAME, content);
		Utils.pause(2000);
	}
	/**
	 * Save add page
	 */
	public void saveAddPage(){
		info("Save all changes");
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		info("Wiki page simple is created successfully");
	}
	/**
	 * Click on save add Page without verifying
	 */
	public void savePage(){
		info("Save all changes");
		click(ELEMENT_SAVE_BUTTON_ADD_PAGE);
		Utils.pause(300);
	}

	/**
	 * Cancel add page
	 */
	public void cancelAddPage(){
		info("Click on Cancel button");
		click(ELEMENT_CANCEL_BUTTON_ADD_PAGE,0,true);
		Utils.pause(2000);
	}

	/**
	 * publish page
	 */
	public void publishPageWhenEditPage(){
		info("check on publish checkbox");
		check(ELEMENT_PUBLISH_ACTIVITY_CHECKBOX, 2);
		Utils.pause(2000);
	}

	/**
	 * unpublish page
	 */
	public void unPublishPageWhenEditPage(){
		uncheck(ELEMENT_PUBLISH_ACTIVITY_CHECKBOX, 2);
		Utils.pause(2000);
	}

	/**
	 * Go to Preview Page
	 */
	public void goToPreviewPage(){
		click(ELEMENT_PREVIEW_BUTTON);
		waitForAndGetElement(ELEMENT_PREVIEW_SCREEN);
	}

	/**
	 * Edit paragraph in a Wiki page
	 * 
	 * 
	 * @param paragraphTitle
	 * 				input paragraph title without space character.  Can not be <code>null</code>.
	 * @param paragraphContent
	 * 				input paragraph content with heading followed help tips.  Can not be <code>null</code>.
	 * 
	 */
	public void editParagraph (String paragraphTitle, String paragraphContent) {
		info("-- Editing a paragraph... " + paragraphTitle);
		String ELEMENT_PARAGRAPH_ID = "H"+paragraphTitle;
		mouseOver(By.id(ELEMENT_PARAGRAPH_ID), true);
		WebElement element = waitForAndGetElement(By.xpath("//*[@data-original-title='Edit Section: " + paragraphTitle + "']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		Utils.pause(500);
		driver.navigate().refresh();
		Utils.pause(2000);
		type(ELEMENT_CONTENT_WIKI_INPUT, paragraphContent, true);
		switchToParentWindow();
	}
	/**
	 * input a comment to new wiki page
	 * @param comment
	 */
	public void addComment(String comment){
		info("Input a comment to wiki page");
		type(ELEMENT_WIKI_PAGE_INPUT_COMMENT,comment,true);
	}
	/**
	 * Move page 1 to page 2
	 * @param page1
	 * @param page2
	 */
	public void movePage(String page1, String page2){
		info("Open a wiki page 1");
		waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}",page1),2000,0).click();
		info("Click on More link");
		click(ELEMENT_MORE_LINK);
		info("Click on Move page link");
		if (waitForAndGetElement(ELEMENT_MOVE_PAGE, 5000, 0) == null){
			mouseOverAndClick(ELEMENT_MOVE_PAGE);
		}else {
			click(ELEMENT_MOVE_PAGE);
		}
		info("Move page popup is shown");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_MOVE_POPUP_NODE.replace("${name}", page2),2000,0).click();
		waitForAndGetElement(ELEMENT_WIKI_PAGE_MOVE_POPUP_SAVE,2000,0).click();
		waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}",page1),2000);
		info("The page 1 is moved to page 2");
	}
	/**
	 * Move a page1 of destination 1 to a page 2 of destination 2
	 * @param page1
	 * @param page2
	 * @param locator
	 */
	public void movePageDiffDestination(String page1, String page2, String locator){
		info("Open a wiki page 1");
		waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}",page1),2000,0).click();
		info("Click on More link");
		click(ELEMENT_MORE_LINK);
		info("Click on Move page link");
		if (waitForAndGetElement(ELEMENT_MOVE_PAGE, 5000, 0) == null){
			mouseOverAndClick(ELEMENT_MOVE_PAGE);
		}else {
			click(ELEMENT_MOVE_PAGE);
		}
		info("Move page popup is shown");
		info("Click on Drop down");
		waitForAndGetElement(ELEMENT_MOVE_SPACESWITCHER,2000,0).click();
		info("Select a location");
		click(ELEMENT_MOVE_PAGE_POPUP_DROP_DOWN_LOCATOR.replace("${locator}",locator));
		info("Select a page in the list");
		waitForAndGetElement(ELEMENT_MOVE_PAGE_POPUP_DROP_DOWN_LOCATOR.replace("${locator}",page2),2000,0).click();
		info("Save all changes");
		waitForAndGetElement(ELEMENT_MOVE_BTNMOVE,2000,0).click();

	}
	/**
	 * Open information table
	 * @param page
	 * @param version
	 */
	public void viewInformationTable(String page, String version){
		info("Open a wiki page 1");
		waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}",page),2000,0).click();
		info("Open information table");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_INFOMATION_VERSION.replace("${version}", version),2000,0).click();
		info("Verify that the table is shown");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_INFORMATION_TABLE_TITLE,2000,0);
	}
	/**
	 * Open page info
	 * @param page
	 */
	public void viewPageInfo(String page){
		info("Open a wiki page 1");
		waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}",page),2000,0).click();
		info("Click on More link");
		click(ELEMENT_MORE_LINK);
		info("Click on Page Info link");
		if (waitForAndGetElement(ELEMENT_PAGE_INFO, 5000, 0) == null){
			mouseOverAndClick(ELEMENT_PAGE_INFO);
		}else {
			click(ELEMENT_PAGE_INFO);
		}
		info("The page info is shown");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_PAGE_INFO_TITLE,2000,0);
	}
	/**
	 * Open Page History
	 */
	public void openPageHistory(){
		info("Click on View page info button");
		waitForAndGetElement(ELEMENT_PAGE_INFO_VIEW_PAGE_INFO_BTN,2000,0).click();
		info("Page history is shown");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_PAGE_HISTORY_TITLE,2000,0);
	}
	/**
	 * Compare 2 reversion
	 * @param reversion1
	 * @param reversion2
	 */
	public void compareTwoReversion(String reversion1, String reversion2){
		info("Select reversion 1");
		check(ELEMENT_WIKI_PAGE_PAGE_HISTORY_CHECKBOX.replace("${reversion}",reversion1),2);
		info("Select reversion 2");
		check(ELEMENT_WIKI_PAGE_PAGE_HISTORY_CHECKBOX.replace("${reversion}",reversion2),2);
		info("Click on Compare button");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_PAGE_HISTORY_COMPARE_BTN,2000,0).click();
		info("Compare reversion page is shown");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_COMPARE_REVERSION_TITLE,2000,0);

	}
	/**
	 * Delete an attachment file
	 */
	public void deleteAttachmentFile(){
		info("Click on detele button");
		click(ELEMENT_PAGE_DELETEATTACHFILE);
		info("Verify that the file is removed");
		waitForElementNotPresent(ELEMENT_PAGE_DOWNLOADATTACHFILE);
	}
	/**
	 * Open add related page popup
	 */
	public void openAddRelationsPopup(){
		info("Click on Add more relations");
		waitForAndGetElement(ELEMENT_PAGE_INFO_ADD_MORE_RELATIONS,2000,0).click();
		waitForAndGetElement(ELEMENT_ADD_RELATED_PAGE_POPUP_TITLE,200,0);
		info("Add related page popup is shown");
	}
	/**
	 * Check options in Add Relations drop down list
	 * @param spaces
	 */
	public void checkAddRelationDropDownList(String spaces){
		info("Click on Drop down");
		waitForAndGetElement(ELEMENT_ADD_RELATED_PAGE_POPUP_DROPDOWN,2000,0).click();
		info("Verify that Intranet location is shown is the list");
		waitForAndGetElement(ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION.replace("${location}","Intranet"),2000,0);
		info("Verify that My wiki location is shown is the list");
		waitForAndGetElement(ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION.replace("${location}","My Wiki"),2000,0);
		if (!spaces.isEmpty()) {
			String[] arraySpace = spaces.split("/");
			for(String space:arraySpace){
				info("Verify that "+space+" location is shown is the list");
				waitForAndGetElement(
						ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION.replace(
								"${location}",space), 2000, 0);
			}

		}
		waitForAndGetElement(ELEMENT_SPACE_SWITHCHER_DROPDOWN_CLOSE,2000,0).click();
		info("All options are checked");
	}
	/**
	 * Add a relation to a page
	 * @param location
	 * @param page
	 */
	public void addRelations(String location,String page){
		info("Click on Drop down");
		waitForAndGetElement(ELEMENT_ADD_RELATED_PAGE_POPUP_DROPDOWN,2000,0).click();
		info("Select a location");
		click(ELEMENT_ADD_RELATED_POPUP_DROPDOWN_LOCATION.replace("${location}",location));
		info("Select a page in the list");
		waitForAndGetElement(ELEMENT_ADD_RELATED_POPUP_CONTENT.replace("${page}",page),2000,0).click();
		info("Save all changes");
		waitForAndGetElement(ELEMENT_ADD_RELATED_POPUP_SELECT_BTN,2000,0).click();
	}


	/**
	 * Delete a relation of a page
	 * @param relation
	 */
	public void deleteRelation(String relation){
		info("Click on Delete button");
		waitForAndGetElement(ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN.replace("${name}",relation),2000,0);
		click(ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN.replace("${name}",relation));
		alert.acceptAlert();
		waitForElementNotPresent(ELEMENT_PAGE_INFO_RELATED_TABLE_DELETE_BTN.replace("${name}",relation));
		info("The relation is deleted");
	}
	/**
	 * Add a simple wiki page by template format
	 * @param template
	 */
	public void addSimpleWikiPageByTemplate(String template,String newTitle){
		info("Select a template");
		selectTemplateWikiPage(template);
		click(ELEMENT_TEMPLATE_SELECT_BTN);
		if(!newTitle.isEmpty())
			type(ELEMENT_TITLE_WIKI_INPUT, newTitle, true);
		Utils.pause(2000);
		info("Save all changes");
		saveAddPage();
	}

	/**
	 * Add a simple wiki page with template with auto save status
	 * @param template
	 */
	public void addSimplePageByTemplateWithAutoSave(String template,String newTitle){
		info("Select a template");
		selectTemplateWikiPage(template);
		click(ELEMENT_TEMPLATE_SELECT_BTN);
		Utils.pause(2000);
		if(!newTitle.isEmpty())
			type(ELEMENT_TITLE_WIKI_INPUT, newTitle, true);
		info("Waiting 30s before saved all changes");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_TOOL_BAR_AUTO_SAVE_TEXT,31000,0);
		info("Save all changes");
		saveAddPage();
	}
	/**
	 * Preview a template
	 * @param template
	 */
	public void previewATemplate(String template){
		info("Preview the template");
		click(ELEMENT_TEMPLATE_PREVIEW_BTN.replace("${template}",template));
		info("Verify that the layout is shown");
		waitForAndGetElement(ELEMENT_PREVIEW_TEMPLATE_CONTENT.replace("${template}",template),2000,0);
		click(ELEMENT_TEMPLATE_PREVIEW_PAGE_CLOSE_BTN);
		Utils.pause(2000);
		click(ELEMENT_TEMPLATE_CANCEL_BTN);
		Utils.pause(2000);
	}
	/**
	 * Rename the title of the page by double-click on the title field
	 * @param title
	 * @param newTitle
	 */
	public void renamePageByDoubleClick(String title,String newTitle){
		info("Open the page");
		Actions action = new Actions(this.driver);
		action.doubleClick(waitForAndGetElement(ELEMENT_PAGE_TITLE.replace("${title}", title),2000,0)).perform();
		type(ELEMENT_WIKI_PAGE_TITLE_RENAME_FIELD,newTitle,true);
		Actions actionEnter = new Actions(this.driver);
		actionEnter.sendKeys(Keys.ENTER).perform();
		actionEnter.release();
		Utils.pause(20000);
	}
	/**
	 * Watch a page
	 */
	public void watchAPage(String mess){
		info("Click on More link");
		click(ELEMENT_MORE_LINK);
		info("Click on watch link");
		click(ELEMENT_WATCH_LINK);
		info("Show message :'You started watching this page now.'");
		waitForAndGetElement(ELEMENT_POPUP_MESSAGE_CONTENT.replace("${message}",mess),2000,0);
		click(ELEMENT_BTN_OK);
		Utils.pause(2000);
	}

	/**
	 * un-Watch a page
	 */
	public void unWatchAPage(String mess){
		info("Click on More link");
		click(ELEMENT_MORE_LINK);
		info("Click on watch link");
		click(ELEMENT_UNWATCH_LINK);
		info("Show message : 'You stopped watching this page now.'");
		waitForAndGetElement(ELEMENT_POPUP_MESSAGE_CONTENT.replace("${message}",mess),2000,0);
		click(ELEMENT_BTN_OK);
		Utils.pause(2000);
	}

	/**
	 * function: check content of mail then delete mail
	 * @param title title of the page
	 */
	public void checkEmailNotification(String title){
		info("Check and delete mail");

		for(String windowHandle  : driver.getWindowHandles()){
			driver.switchTo().window(windowHandle);
			info("driver.title:"+driver.getTitle());
		}
		waitForAndGetElement(ELEMENT_GMAIL_CONTENT_WIKI.replace("${title}",title),30000,0);
		info("Found notify mail");

		info("ELEMENT_GMAIL_CONTENT:"+ELEMENT_GMAIL_CONTENT_WIKI.replace("${title}",title));
		info("Open email");
		waitForAndGetElement(ELEMENT_GMAIL_CONTENT_WIKI.replace("${title}",title)).click();
		String defaultLink = baseUrl+"/intranet/wiki/"+title;
		//Store childs and parent windows
		Object[] allWindows =driver.getWindowHandles().toArray();
		//Get parent window
		String paWindow=allWindows[0].toString();
		//Get child window 1. Here is gmail browser
		String chilwindow1=allWindows[1].toString();
		//Get child window 2. Here is last child window when click on subtitle of email notification
		String chilwindow2=allWindows[2].toString();
		//Focus on Child window2
		driver.switchTo().window(chilwindow2);
		info("Verify that the link is shown as correct format:");
		click(ELEMENT_GMAIL_PREVIOUS_EMAIL);
		info("Check the link's format");
		String link = waitForAndGetElement(ELEMENT_GMAIL_CONTENT_LINK_WIKI.replace("${page}",title),3000,0).getAttribute("href").toString();
		//close child window 2
		driver.close();
		//Focus on child window 1
		driver.switchTo().window(chilwindow1);
		//close child window 1
		driver.close();
		//Focus on parent window
		driver.switchTo().window(paWindow);

		info("link:"+link);
		info("default:"+defaultLink);
		if(link.contentEquals(defaultLink)==true)
			assert true;
		else assert false:"the link's format is incorrect";
		Utils.pause(2000);

	}
	
	/**
	 * Un check view permission for a user or a group
	 * @param locator
	 */
	public void unCheckViewAUserOfPage(Object locator){
		info("Click on More link");
		click(ELEMENT_MORE_LINK);
		info("Click on permission link");
		click(ELEMENT_PERMISSION_LINK);
		info("Uncheck view permission checkbox");
		uncheck(locator, 2);
		info("Click on save button");
		click(ELEMENT_PERMISSION_BUTTON_SAVE);
	}

	/**
	 * Add more permission for a user
	 * @param namegroup
	 */
	public void addAUserToPermission(String namegroup,Object permission){
		info("Click on More link");
		click(ELEMENT_MORE_LINK);
		info("Click on permission link");
		click(ELEMENT_PERMISSION_LINK);
		info("type a name or a group");
		type(ELEMENT_PERMISSION_NAMEORGROUP, namegroup, true);
		click(ELEMENT_PERMISSION_BTNADD);
		if(!permission.toString().isEmpty())
			check(permission, 2);
		info("Click on save button");
		click(ELEMENT_PERMISSION_BUTTON_SAVE);
	}
	/**
	 * remove a user or a group in permission table of a page
	 * @param usergroup
	 */
	public void removeAUserGroup(String usergroup){
		info("Click on More link");
		click(ELEMENT_MORE_LINK);
		info("Click on permission link");
		click(ELEMENT_PERMISSION_LINK);
		click(ELEMENT_PERMISSION_REMOVE_USER_GROUP.replace("${name}",usergroup));
		info("Click on save button");
		click(ELEMENT_PERMISSION_BUTTON_SAVE);
	}
	/**
	 * Rename a page from alert message when move a page to a destination that has same name
	 * @param newTitle
	 * @param newContent
	 */
	public void renameNameFromAlertMessage(){
		info("Click on Rename link on the alert message area");
		click(ELEMENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME);
		Utils.pause(2000);
	}
	
	/**
	 * Click on inserted link in the wiki page
	 * @param label
	 */
	public void viewInsertLink(String label){
		info("Verify that the inserted link is shown in the page");
		waitForAndGetElement(By.linkText(label));
		info("Click on the label");
		click(By.linkText(label));
	}
	
	/**
	 * Click on View Changes link on the Status when editting a wiki
	 */
	public void goToViewChangesLinkOnStatus(){
		info("Click on View Changes link on the status");
		click(ELEMENT_WIKI_STATUS_VERSION_VIEW_CHANGES_LINK);
		Utils.pause(2000);
	}
	/**
	 * Click on Continue link on the Status when editting a wiki
	 */
	public void goToContinueEdittingLinkOnStatus(){
		info("Click on Continue Editting link on the status");
		click(ELEMENT_WIKI_STATUS_VERSION_CONTINUE_EDITTING_LINK);
		info("The status is not shown and Edit page is shown");
		waitForElementNotPresent(ELEMENT_WIKI_STATUS_VERSION_CONTINUE_EDITTING_LINK);
		waitForAndGetElement(ELEMENT_TITLE_WIKI_INPUT);
	}
	/**
	 * Click on Delete on The status when editing a wiki
	 */
	public void goToDeleteLinkOnStatus(){
		info("Click on Continue Editting link on the status");
		click(ELEMENT_WIKI_STATUS_VERSION_DELETE_LINK);
		info("The status is not shown and Edit page is shown");
		waitForElementNotPresent(ELEMENT_WIKI_STATUS_VERSION_DELETE_LINK);
	}
	/**
	 * Click on Resume the Draf link on the Status when editting a page
	 */
	public void goToResumDrafLinkOnStatus(){
		info("Click on Resume the Draf link");
		click(ELEMENT_WIKI_STATUS_RESUME_THE_DRAF_LINK);
		Utils.pause(2000);
		
	}
	
	

}