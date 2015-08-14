package org.exoplatform.selenium.platform.wiki;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WikiHomePage extends WikiLocators{
	Dialog dialog;

	ManageAlert alert;
	
	public final String ELEMENT_WIKI_HOME_PAGE_TITLE = "//*[@id='titleInfo' and text()='${title}']";

	public final String ELEMENT_WIKI_PAGE_LEFTBOX = "//*[@id='iconTreeExplorer']//*[contains(text(),'${title}')]";
	public final By ELEMENT_EDIT_PAGE_LINK= By.xpath("//*[@class='uiIconEditPage uiIconLightGray']");
	public final By ELEMENT_WIKI_HOME_PAGENOTFOUND = By.xpath("//*[text()='Page Not Found']");
	public final By ELEMENT_WIKI_HOME_PAGE_LOCATION_MYWIKI = By.xpath("//*[@class='btn dropdown-toggle']//*[text()='My Wiki']");
	public final By ELEMENT_BTN_OK = By.xpath("//*[text()='OK']");
	public final By ELEMENT_UNWATCH_CONFIRM = By.xpath("//*[contains(text(),'You have stopped watching this page now.')]");
	public final By ELEMENT_WIKI_HOME_LEFTBOX_WIKIHOME = By.xpath("//*[text()=' Wiki Home']");
	
	//More menu
	public final By ELEMENT_MORE_LINK = By.xpath("//*[@id='UIWikiPageControlArea_PageToolBar']//div[contains(text(), 'More')]");
	public final By ELEMENT_DELETE_LINK = By.xpath(".//*[text()='Delete Page']");
	public final By ELEMENT_DELETE_LINK_2 = By.className("uiIconDeletePage");
	public final By ELEMENT_CONFIRM_WIKI_DELETE = By.xpath(".//*[@id='UIWikiDeletePageConfirm']//button[text()='OK']");
	public final By ELEMENT_MOVE_PAGE = By.xpath(".//*[text()='Move Page']");
	public final By ELEMENT_PAGE_INFO = By.xpath(".//*[text()='Page Info']");
	public final By ELEMENT_MOVE_LINK = By.xpath("//*[@class='uiIconMovePage']");
	public final By ELEMENT_WATCH_LINK = By.xpath("//*[@class='uiIconWatchPage']");
	public final By ELEMENT_UNWATCH_LINK = By.xpath("//*[@class='uiIconUnWatchPage']");
	public final By ELEMENT_PERMISSION_LINK = By.xpath("//*[@class='uiIconPagePermission']");
	public final By ELEMENT_PDF_LINK = By.xpath("//*[@class='uiIconExportAsPDF']");
	
	//Permalink page
	public final By ELEMENT_PERMALINK_LINK = By.xpath("//*[@class='uiIconPermalink']");
	public final By ELEMENT_PERMALINK_LINKCOPY = By.xpath("//*[@id='PermalinkText']");
	public final By ELEMENT_PERMALINK_MANAGEPERM = By.xpath("//*[text()='Manage Permissions']");
	public final By ELEMENT_PERMALINK_MAKEPUBLIC = By.xpath("//*[text()='Make Public']");
	public final By ELEMENT_PERMALINK_RESTRICT = By.xpath("//*[text()='Restrict']");
	public final String ELEMENT_PERMALINK_STATUS = ".//*[@id='UIWikiPermalinkForm']//*[text()='${status}']";
	public final By ELEMENT_PERMALINK_CLOSE=By.xpath(".//*[@id='UIWikiPopupWindowL1']//*[@class='uiIconClose pull-right']");
	
	//permission page
	public final By ELEMENT_PERMISSION_EDIT_ANY = By.xpath("//*[@id='EDITPAGEany']");
	public final String ELEMENT_PERMISSION_EDIT_USER = "//*[@id='EDITPAGE${user}']";
	public final By ELEMENT_PERMISSION_BUTTON_SAVE = By.xpath("//*[text()='Save']");
	public final By ELEMENT_PERMISSION_VIEW_ANY = By.xpath("//*[@id='VIEWPAGEany']");
	public final String ELEMENT_PERMISSION_REMOVE_USER_GROUP = ".//*[@id='UIPermissionGrid']//*[contains(text(),'${name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	
	//move wiki
	public final By ELEMENT_MOVE_SPACESWITCHER = By.xpath("//*[@id='uiSpaceSwitcher_UIWikiMovePageForm']/..//*[@class='btn dropdown-toggle']");
	public final By ELEMENT_MOVE_SPACESWITCHER_MYWIKI = By.xpath("//*[@class='uiIconWikiMyWiki uiIconWikiLightGray']");
	public final String ELEMENT_MOVE_SPACESWITCHER_OTHERSPACE = "//*[text()='${name}']";
	public final By ELEMENT_MOVE_BTNMOVE = By.xpath("//*[@class='btn btn-primary' and contains(text(),'Move')]");
	public final String ELEMENT_MOVE_SPACESWITCHER_OTHERPAGE = "//*[@id='UIMoveTree']/../..//*[contains(text(),'${title}')]";
	public final By ELEMENT_MOVE_RENAMEWIKI = By.xpath("//*[text()='Rename']");
	public final By ELEMENT_MOVE_RESTRICTED = By.xpath("//*[@class='warningIcon' and contains(text(),'You have no edit permission at the destination page')]");
	public final String ELEMENT_MOVE_PAGE_POPUP_DROP_DOWN_LOCATOR=".//*[@id='UIWikiPopupWindowL1']//*[contains(text(),'Move Page')]/../..//*[contains(text(),'${locator}')]";
	public final String ELEMENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_SAME_NAME=".//*[@class='alert'][contains(.,'${message}')]";
	public final By ELEMENT_MOVE_PAGE_POPUP_ALERT_MESSAGE_RENAME=By.xpath(".//*[@class='alert']/a[text()='Rename']");

	public final By ELEMENT_PAGE_PERMISSIONS = By.xpath(".//*[text()='Page Permissions']");
	public final By ELEMENT_PERMALINK = By.xpath("//*[@id='UIWikiPageControlArea_PageToolBar']//*[text()='Permalink']");
	
	//Content of page
	public final String ELEMENT_MARCRO_COLOR = "//*[@style='color:${color};' and contains(text(),'${message}')]";
	public final By ELEMENT_PAGE_TITLE_INFO = By.id("titleInfo");
	public final String ELEMENT_PAGE_TITLE = ".//*[@id='titleInfo'][text()='${title}']";
	public final By ELEMENT_PAGE_TITLE_EDIT_TEXTBOX = By.id("EdiableInput");
	public final By ELEMENT_PAGE_ATTACHFILE = By.xpath("//*[contains(.,'1')]//*[@class='uiIconAttach']");
	public final By ELEMENT_PAGE_DOWNLOADATTACHFILE = By.xpath("//*[@data-original-title='Download Attachment']");
	public final By ELEMENT_PAGE_DELETEATTACHFILE = By.xpath("//*[@class='uiIconDelete uiIconLightGray']");
	
	public final By ELEMENT_SAVE_PERMISSION = By.xpath(".//*[@id='UIWikiPagePermissionForm']//*[contains(text(),'Save')]");
	public final By ELEMENT_CANCEL_PERMISSION = By.xpath(".//*[@id='UIWikiPagePermissionForm']//*[contains(text(),'Cancel')]");
	public final By ELEMENT_ADD_PERMISSION = By.xpath("//*[@id='uiWikiPermissionOwner']//*[contains(text(),'Add')]");
	public final String ELEMENT_CHECK_PERMISSION_EDIT_PAGE =".//*[@id='UIPermissionGrid']/table//*[contains(text(),'{$name}')]/../..//*[@id='EDITPAGE{$name}']";
	public final String ELEMENT_REMOVE_PERMISSION = ".//*[@id='UIPermissionGrid']/table//*[contains(text(),'{$name}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	
	public final By ELEMENT_WIKI_PAGE_NOT_FOUND = By.xpath(".//*[@id='UIWikiPageArea']//*[contains(text(),'Page Not Found')]");
	
	//Action bar
	public final String ELEMENT_ATTACHMENT_NUMBER = "//*[@id='UIWikiPageInfoArea']//a[contains(text(),'${No}')]/*[@class='uiIconAttach']";
	public final String ELEMENT_PAGE_INFO_ADDED_BY = ".//*[@id='UIWikiPageInfoArea']//*[contains(.,'Added by')]//a[contains(text(),'{$name}')]";
	public final String ELEMENT_PAGE_INFO_LAST_MODIFIED_BY = ".//*[@id='UIWikiPageInfoArea']//*[contains(.,'Last modified by')]//a[contains(text(),'{$name}')]";
	public final By ELEMENT_ATTACHMENT_ICON = By.xpath("//*[@id='UIWikiPageInfoArea']//*[@class='uiIconAttach']");
	public final By ELEMENT_SEARCH_TEXTBOX = By.xpath("//*[@id='wikiSearchValue']");
	public final By ELEMENT_SEARCH_BTN = By.xpath(".//*[@id='UIWikiSearchBox']//*[@class='uiIconSearch uiIconLightGray']");
	
	//Browsers
	public final By ELEMENT_SEARCH_BROWSERS_DROPDOWN = By.xpath("//*[@class='uiActionWithLabel']/..//*[text()='Browse']");
	public final By ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS = By.xpath(".//*[@class='dropdown-menu']//*[text()='Wiki Settings']");
	public final By ELEMENT_SEARCH_BROWSERS_MY_DRAFT = By.xpath(".//*[@class='dropdown-menu']//*[text()='My Drafts']");
	
	//tree explorer
	public final String ELEMENT_TREE_WIKI_NAME = ".//*[@id='iconTreeExplorer']//*[contains(text(),'${name}')]";
	//Permission
	public final By ELEMENT_PERMISSION_NAMEORGROUP = By.xpath("//*[@id='PermissionOwner']");
	public final By ELEMENT_PERMISSION_BTNADD = By.xpath("//*[text()='Add']");

	/**
	 * constructor
	 * @param dr
	 */
	public WikiHomePage(WebDriver dr){
		this.driver=dr;
		dialog = new Dialog(dr);
		alert = new ManageAlert(driver, this.plfVersion);
	}
	/**
	 * Go to "Add blank wiki page"
	 * 
	 */
	public void goToAddBlankPage(){
		info("--Go to add blank wiki page--");
		mouseOverAndClick(ELEMENT_ADD_PAGE_LINK);
		mouseOverAndClick(ELEMENT_BLANK_PAGE_LINK);
		info("Blank wiki page is shown");
	}

	/**
	 * Go to "Add template wiki page"
	 * 
	 */
	public void goToAddTemplateWikiPage(){
		info("--Go to add template wiki page--");
		mouseOverAndClick(ELEMENT_ADD_PAGE_LINK);
		mouseOverAndClick(ELEMENT_FROM_TEMPLATE_LINK);
	}

	/**
	 * Go to "Add blank wiki page"
	 * 
	 */
	public void goToEditPage(){
		info("--Go to edit page--");
		click(ELEMENT_EDIT_PAGE_LINK);
		waitForElementNotPresent(ELEMENT_WIKI_HOME_PAGE_TEXT);
	}
	

	/**
	 * Go to Home Wiki Page
	 */
	public void goToHomeWikiPage(){
		info("-- Go to wiki home page --");
		click(ELEMENT_WIKI_HOME_PAGE_LINK);
		waitForAndGetElement(ELEMENT_WIKI_HOME_PAGE_TEXT);
	}

	/**
	 * Select any page
	 * @param title
	 */
	public void goToAPage(String title){
		info("-- Go to wiki page --");
		click(ELEMENT_WIKI_PAGE_LINK.replace("${pageTitle}", title.replace(" ", "_")));
		waitForElementNotPresent(ELEMENT_WIKI_HOME_PAGE_TEXT);
	}

	/**
	 * Select any page
	 * @param title
	 */
	public void deleteWiki(String title){
		if(waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}",title),3000,0)!=null){
			info("Go to delete wiki page...");
			info("Select the wiki page to delete");
			selectAPage(title);
			info("Click on More link");
			click(ELEMENT_MORE_LINK);
			if (waitForAndGetElement(ELEMENT_DELETE_LINK, 5000, 0) == null){
				mouseOverAndClick(ELEMENT_DELETE_LINK);
			}else {
				click(ELEMENT_DELETE_LINK);
			}
			waitForAndGetElement(ELEMENT_CONFIRM_WIKI_DELETE,2000,0).click();
			waitForElementNotPresent(ELEMENT_TREE_WIKI_NAME.replace("${name}",title));
		}
	}
	
	/**
	 * Select any page
	 * @param title
	 */
	public void cancelDeleteWiki(String title){
		if(waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}",title),3000,0)!=null){
			info("Go to delete wiki page...");
			info("Select the wiki page to delete");
			selectAPage(title);
			info("Click on More link");
			click(ELEMENT_MORE_LINK);
			if (waitForAndGetElement(ELEMENT_DELETE_LINK, 5000, 0) == null){
				mouseOverAndClick(ELEMENT_DELETE_LINK);
			}else {
				click(ELEMENT_DELETE_LINK);
			}
			waitForAndGetElement(ELEMENT_CANCEL_WIKI_DELETE,2000,0).click();
			waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}",title));
		}
	}
	/**
	 * Select a page
	 * @param page
	 */
	public void selectAPage(String page){
		info("Go to a wiki page...");
		info("Select the wiki page");
		waitForAndGetElement(ELEMENT_TREE_WIKI_NAME.replace("${name}",page),5000,0).click();
		info("The page is shown");
		waitForAndGetElement(ELEMENT_PAGE_TITLE.replace("${title}", page),3000,0);
	}
	/**
	 * Go to "Go to My Draft"
	 * 
	 */
	public void goToMyDraft(){
		info("Click on Browser drop down");
		click(ELEMENT_SEARCH_BROWSERS_DROPDOWN);
		info("Select wiki settings label");
		click(ELEMENT_SEARCH_BROWSERS_MY_DRAFT);
		waitForAndGetElement(ELEMENT_DRAFT_PAGE_TITLE);
		Utils.pause(2000);
	}
	/**
	 * Open search page with a text
	 * @param text
	 */
	public void goTosearchPage(String text){
		info("Input a text to search field");
		type(ELEMENT_SEARCH_TEXTBOX_POPUP, text, true);
		Utils.pause(1000);
		click(ELEMENT_SEARCH_BTN);
        Utils.pause(2000);
	}
	/**
	 * Open Wiki Settings page
	 */
	public void goToWikiSettingPage(){
		info("Click on Browser drop down");
		click(ELEMENT_SEARCH_BROWSERS_DROPDOWN);
		info("Select wiki settings label");
		click(ELEMENT_SEARCH_BROWSERS_WIKI_SETTINGS);
		Utils.pause(2000);
	}
	/**
	 * Open permission tab
	 */
	public void openPermTab(){
		info("Open Permission tab");
		click(ELEMENT_WIKI_SETTING_PERM_TAB,0,true);
	}
	/**
	 * Open permissions for the wiki
	 */
	public void goToPermissions(){
		info("Permissions page");
		click(ELEMENT_MORE_LINK);
		click(ELEMENT_PERMISSION_LINK);
		waitForAndGetElement(ELEMENT_PAGE_PERMISSION_POPUP);
		info("The permission popup is shown");
	}
	
	/**
	 * Confirm messages
	 * @param isConfirm
	 *                  = true if want to click on Confirm button
	 *                  = false if want to click on Cancel button
	 */
	public void confirmWaringMessage(Boolean isConfirm){
		if(isConfirm){
			if(waitForAndGetElement(ELEMENT_CONFIRM_POPUP_OK_BTN,2000,0)!=null){
				info("Click on OK button");
				click(ELEMENT_CONFIRM_POPUP_OK_BTN);
			}
			if(waitForAndGetElement(ELEMENT_CONFIRM_POPUP_CONFIRM_BTN,2000,0)!=null){
				info("Click on Confirm button");
				click(ELEMENT_CONFIRM_POPUP_CONFIRM_BTN);
			}
			if(waitForAndGetElement(ELEMENT_CONFIRM_POPUP_YES_BTN,2000,0)!=null){
				info("Click on Yes button");
				click(ELEMENT_CONFIRM_POPUP_YES_BTN);
			}
			if(waitForAndGetElement(ELEMENT_CONFIRM_POPUP_YES_BTN,2000,0)!=null){
				info("Click on Yes button");
				click(ELEMENT_CONFIRM_POPUP_YES_BTN);
			}
			if(waitForAndGetElement(ELEMENT_WARNING_OK_BTN,2000,0)!=null){
				info("Click OK button");
				click(ELEMENT_WARNING_OK_BTN);
			}
		}else{
			if(waitForAndGetElement(ELEMENT_CONFIRM_POPUP_CANCEL_BTN,2000,0)!=null){
				info("Click on Cancel button");
				click(ELEMENT_CONFIRM_POPUP_CANCEL_BTN);
			}
			
			if(waitForAndGetElement(ELEMENT_CONFIRM_POPUP_NO_BTN,2000,0)!=null){
				info("Click on No button");
				click(ELEMENT_CONFIRM_POPUP_NO_BTN);
			}
			
		}
	}
	
	/**
	 * Get a permalink of the page
	 * @return perLink
	 */
	public void goToPermalink(){
		info("Go to permalink");
		mouseOverAndClick(ELEMENT_MORE_LINK);
		mouseOverAndClick(ELEMENT_PERMALINK_LINK);
		waitForAndGetElement(ELEMENT_PERMALINK_POPUP);
		Utils.pause(2000);
	}

	
	/**
	 * Restricted a page from infor bar or More menu
	 * @param opParams
	 */
	public void restrictedPage(Boolean...opParams){
		info("Make Restricted page");
		Boolean useRestrictLink = (Boolean)(opParams.length>0 ? opParams[0]:false);
		if(useRestrictLink){
			waitForAndGetElement(ELEMENT_PUBLIC_WIKI_ICON );
			click(ELEMENT_PUBLIC_WIKI_ICON );
		}
		else{
			goToPermalink();
		}
		click(ELEMENT_MAKE_RESTRICT_BUTTON);
		waitForAndGetElement(ELEMENT_MAKE_PUBLIC_BUTTON);
		dialog.closeMessageDialog();
		Utils.pause(2000);
	}
	
	/**
	 * Gets a permanent link by a given value.
	 * 
	 * @return The value.
	 */
	public String getPermalink(){
		return getValue(ELEMENT_PERMALINK_TEXT);
	}
	/**
	 * Close permalink popup
	 */
	public void closePermalinkPopup(){
		info("Click on Close button");
		click(ELEMENT_PERMALINK_CLOSE);
		waitForElementNotPresent(ELEMENT_PERMALINK_POPUP);
		info("Permalink popup is closed");
	}
	
	/**
	 * Go to attach files in Wiki Home page
	 * @param number
	 */
	public void goToAttachFiles(String number){
		info("Click attach file link");
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_PAGE_ATTACHFILE_NUMBER.replace("${number}", number), DEFAULT_TIMEOUT, 1);
		click(ELEMENT_PAGE_ATTACHFILE_NUMBER.replace("${number}", number));
	}
	
	/**
	 * Delete attach file in View mode in Wiki Homepage 
	 * or in edit mode when editing a wiki page
	 * @param fileName
	 */
	public void DeleteAttachFiles(String fileName){
		info("Delete attach files");
		Utils.pause(2000);
		if (waitForAndGetElement(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE.replace("${fileName}", fileName), 5000, 0) != null){
			click(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE.replace("${fileName}", fileName));
			waitForElementNotPresent(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE.replace("${fileName}", fileName));
		}
		else{
			waitForAndGetElement(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE_2.replace("${fileName}", fileName), DEFAULT_TIMEOUT, 0);
			click(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE_2.replace("${fileName}", fileName));
			waitForElementNotPresent(ELEMENT_PAGE_DELETEATTACHFILE_VIEW_MODE_2.replace("${fileName}", fileName));
			}
	}
	/**
	 * Go to Wiki Home of the space
	 * @param space
	 */
	public void goToWikiHomeOfSpaceFromBreadcrumb(String space){
		goToSpaceSwitcher();
		if(!space.isEmpty()){
			info("Select the space");
			click(ELEMENT_SPACE_SWITCHER_SELECTED_SPACE
					.replace("$space",space));
			waitForAndGetElement(ELEMENT_WIKI_HOME_BREADCRUMB_PATH_HOME
					.replace("$locator1",space)
					.replace("$locator2","Wiki Home"));
		}
	}
	/**
	 * Open Space switcher
	 */
	public void goToSpaceSwitcher(){
		info("Click on drop down");
		click(ELEMENT_SPACE_DROP_DOWN);
		Utils.pause(2000);
	}
	
	/**
	 * Input and search a space in space switcher
	 * @param text
	 */
	public void inputSpaceSwitcher(String text){
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).clear();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).click();
		waitForAndGetElement(ELEMENT_SPACE_SWITCHER_INPUT).sendKeys(text);
		Utils.pause(1000);
		
	}
	/**
	 * Close space switcher
	 */
	public void closeSpaceWitcher(){
		info("Click on Close button");
		click(ELEMENT_SPACE_SWITCHER_CLOSE_BTN);
		waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT);
	}
	/**
	 * Close space switcher of the breadcrumb on wiki home page by clicking on outside
	 */
	public void closeSpaceSwitcherByClickOutSide(){
		info("Click on outside to close space switcher");
		click(ELEMENT_SPACE_SWITCHER_OUTSIDE);
		waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT);
	}
	/**
	 * Close space switcher of move page popup by clicking on outside
	 */
	public void closeSpaceSwitcherMovePopupByClickOutside(){
		info("Click on outside to close space switcher");
		click(ELEMENT_SPACE_SWITCHER_OUTSIDE);
		waitForElementNotPresent(ELEMENT_SPACE_SWITCHER_INPUT_MOVE_PAGE_POPUP);
	}
	/**
	 * Open Page information
	 */
	public void goToPageInformation(){
		info("Go to Page Information");
		mouseOverAndClick(ELEMENT_MORE_LINK);
		mouseOverAndClick(ELEMENT_PAGE_INFO);
		waitForAndGetElement(ELEMENT_WIKI_PAGE_PAGE_INFO_TITLE);
		Utils.pause(2000);
	}
	/**
	 * Open information version table
	 * @param version
	 */
	public void goToRevisions(String version){
		info("Click on Version");
        click(ELEMENT_WIKI_PAGE_INFOMATION_VERSION.replace("${version}", version));
        info("Verify that the table is shown");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_INFORMATION_TABLE_TITLE,2000,1);
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
		waitForAndGetElement(ELEMENT_WIKI_PAGE_INFORMATION_TABLE_TITLE,2000,1);
	}
	/**
	 * Open compare version page by clicking on View Changes link
	 */
	public void goToViewChange(){
		info("Click on View change link on the information bar");
		click(ELEMENT_INFOR_BAR_VIEW_CHANGE_LINK);
		info("Verify that compare version page is shown");
		waitForAndGetElement(ELEMENT_WIKI_PAGE_COMPARE_VERSION_TITLE);
	}
	
	/**
	 * Export a Wiki Page
	 */
	public void exportWikiPage(){
		info("Export a Wiki Page");
		click(ELEMENT_MORE_LINK);
		click(ELEMENT_PDF_LINK);
		Utils.pause(2000);
	}
	
	/**
	 * Go to Move page form
	 */
	public void goToMovePageForm(){
		info("Go to Move page form");
		info("Click on More link");
		click(ELEMENT_MORE_LINK);
		info("Click on Move page link");
		if (waitForAndGetElement(ELEMENT_MOVE_PAGE, 5000, 0) == null){
			mouseOverAndClick(ELEMENT_MOVE_PAGE);
		}else {
			click(ELEMENT_MOVE_PAGE);
		}
		waitForAndGetElement(ELEMENT_MOVE_PAGE_POPUP, 3000,1);
		Utils.pause(2000);
	}

public void cancelPermissions(){
	info("Permissions page");
	click(ELEMENT_CANCEL_PERMISSION);
}
	/**
	 * Go to Export a page
	 */
	public void goToExportPage(){
		info("Go to Export a page");
		info("Click on More link");
		click(ELEMENT_MORE_LINK);
		info("Click on Move page link");
		if (waitForAndGetElement(ELEMENT_PDF_LINK, 5000, 0) == null){
			mouseOverAndClick(ELEMENT_PDF_LINK);
		}else {
			click(ELEMENT_PDF_LINK);
		}
		waitForElementNotPresent(ELEMENT_PDF_LINK, 3000,1);
		Utils.pause(2000);
	}
}
