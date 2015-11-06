package org.exoplatform.selenium.platform.ecms.contentexplorer;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.CKeditor;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageView;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import static org.exoplatform.selenium.TestLogger.info;


public class ActionBar extends EcmsBase{

	public ActionBar(WebDriver dr, String...plfVersion) {
		super(dr);
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.1";
		// TODO Auto-generated constructor stub
	}
	CKeditor ck = new CKeditor(driver);
	Button button = new Button(driver);
	Dialog dialog = new Dialog(driver);
	ManageAlert alt = new ManageAlert(driver);
	NavigationToolbar navToolBar = new NavigationToolbar(driver);
	ManageAccount magAcc = new ManageAccount(driver,this.plfVersion);
	ManageView magView = new ManageView(driver);
	ContextMenu cMenu = new ContextMenu(driver);
	ManageAlert alert = new ManageAlert(driver);
	ECMainFunction ecMain = new ECMainFunction(driver);
	PageEditor ePage = new PageEditor(driver, this.plfVersion);
	/*
	 * Added by PhuongDT
	 * Date: 04/09/2013
	 * */
	public final By ELEMENT_MORE_LINK_ACTION_BAR = By.xpath("//*[@class='uiContextMenuContainer']//*[@style='display: block;']//*[contains(text(), 'More')]");
	public final By ELEMENT_RENAME_NODE = By.className("uiIconEcmsRename");
	public final By ELEMENT_DOWNLOAD_NODE = By.className("uiIconDownload");
	public final By ELEMENT_COPY_TO_URL_NODE = By.className("uiIconEcmsCopyUrlToClipboard");
	public final By ELEMENT_ADD_TO_FAVORITE_NODE = By.className("uiIconEcmsAddToFavourite");
	public final By ELEMENT_VIEW_DOCUMENT_NODE = By.className("uiIconEcmsViewDocument");
	public final By ELEMENT_DELETE_NODE = By.xpath("//*[@id='ECMContextMenu']//*[@class='uiIconEcmsDelete']");
	public final By ELEMENT_ADD_SYMLINK_NODE = By.xpath("//*[@id='ECMContextMenu']//*[@class='uiIconEcmsAddSymLink']");
	public final By ELEMENT_ADD_SYMLINK_NODE_PARENT_CHILD = By.xpath("//*[@id='JCRContextMenu']//*[contains(@class,'uiIconEcmsAddSymLink')]");
	public final By ELEMENT_LOCK_NODE = By.xpath("//*[@id='ECMContextMenu']//i[@class='uiIconEcmsLock']");
	public final By ELEMENT_UNLOCK_NODE = By.xpath("//*[@id='ECMContextMenu']//i[@class='uiIconEcmsUnlock']");
	/*End added*/

	/*
	 * @Added by: PhuongDT
	 * @date: 27/08/2013
	 * @Function: Manage Action Bar
	 */
	public final By ELEMENT_ACTION_ICON = By.className("uiIconEcmsManageActions");
	public final By ELEMENT_ADD_ACTION_TAB = By.xpath("//*[text()='Add Action']");
	public final By ELEMENT_AVAILABLE_ACTIONS = By.xpath("//*[text()='Available Actions']");
	public final By ELEMENT_ACTION_NAME = By.id("actionName");
	public final By ELEMENT_ACTION_LIFE_CYCLE = By.name("lifecycle");
	public final By ELEMENT_ACTION_TYPE = By.name("actionType");
	public final By ELEMENT_ACTION_METADATA = By.name("metadata");
	public final By ELEMENT_ACTION_NODE_TYPE = By.name("nodetypes0");
	public final By ELEMENT_ACTION_DESCRIPTION = By.name("description");
	public final By ELEMENT_ACTION_AFFECTED_NODE_TYPE = By.name("affectedNodetypes0");
	public final By ELEMENT_ACTION_NODE_TYPE_SEARCH = By.xpath("//*[@class='uiIconSearch']");
	public final By ELEMENT_ACTION_AFFECTED_NODE_TYPE_SEARCH = By.xpath("//*[@text()='Affected Node Types:']//*[@class='uiIconSearch']");
	public final By ELEMENT_ACTION_SELECT_ALL_DOC = By.xpath(".//*[@id='ALL_DOCUMENT_TYPES']");
	public final String ELEMENT_ACTION_AVAILABLE_ACTION = "//*[@id='UIActionList']//*[@class='text' and contains(text(), '${actionname}')]";
	public final String ELEMENT_EDIT_ACTION_ICON = "//*[@id='UIActionList']//*[@class='text' and contains(text(), '${actionname}')]/../..//*[contains(@class, 'uiIconEdit')]";
	public final String ELEMENT_DELETE_ACTION_ICON = "//*[@id='UIActionList']//*[@class='text' and contains(text(), '${actionname}')]/../..//*[contains(@class, 'uiIconDelete')]";
	/*End Add*/

	//Export Form
	public By ELEMENT_DOC_VIEW = By.xpath("//form[@id='UIExportNode']//input[@name='format' and @value='docview']");
	public By ELEMENT_ZIP = By.name("zip");
	public By ELEMENT_EXPORT_VERSION = By.xpath("//button[text()='Export Version History']");
	public By ELEMENT_EXPORT = By.xpath("//button[text()='Export']");
	public String ELEMENT_IMPORT_FILE_LABEL = "//div[@class='fileNameLabel' and contains(text(),'${fileName}')]";

	//Import Form
	public By ELEMENT_UPLOAD_FILE_FRAME = By.xpath("//label[contains(text(),'Upload File:')]/following::div/iframe[contains(@id,'uploadFrame')]");
	public By ELEMENT_UPLOAD_VERSION_FRAME = By.xpath("//label[contains(text(),'Version History:')]/following::div/iframe[contains(@id,'uploadFrame')]");
	public By ELEMENT_BEHAVIOR = By.name("behavior");
	public By ELEMENT_IMPORT = By.xpath("//button[text()='Import']");

	//Publication > Add Category Form
	public By ELEMENT_CATEGORIES_LINK = By.xpath("//*[@class='actionIcon']//*[@class='uiIconEcmsManageCategories uiIconEcmsLightGray']"); 
	//public By ELEMENT_MULTI_CATEGORIES = By.xpath("//*[@class='uiGrid table table-hover table-striped']//tr[${index}]//i[@class='uiIconValidate uiIconLightGray']");
	public String ELEMENT_MULTI_CATEGORIES = "//*[@class='uiGrid table table-hover table-striped']//tr[${index}]//i[@class='uiIconValidate uiIconLightGray']";
	public By ELEMENT_CATEGORIES_MORE_LINK = By.xpath("//*[text()='More']/..//a[text()='Categories']");
	public By ELEMENT_SELECT_CATEGORY_TAB = By.xpath("//*[text()='Select Category']");
	public By ELEMENT_CATEGORY_TREE_BOX = By.name("taxonomyTree");
	public By ELEMENT_ADD_ROOT_BUTTON = By.xpath("//label[text()='Root Tree']/following::img[@title='Add Root Node']");
	public By ELEMENT_REFERENCE_TAB = By.xpath("//a[contains(text(),'Referenced Categories')]");
	public String ELEMENT_DELETE_CATEGORY_ICON = "//td[text()='{$categoryPath}']/..//i[@class='uiIconDelete uiIconLightGray']";
	public String MSG_DELETE_CATEGORY = "Are you sure you want to delete this reference?";

	//Version Info form
	public By ELEMENT_ICON_VERSION_ADD=By.xpath("//*[@data-original-title='Add Label']");
	public By ELEMENT_TEXTBOX_VERSION=By.id("label");
	public By ELEMENT_ACTIVATE_VERSION_BUTTON = By.xpath("//*[text()='Activate']");
	public By ELEMENT_CANCEL_ACTIVATE_VERSION_BUTTON = By.xpath("//*[text()='Cancel']");
	public By ELEMENT_VERSION_INFO_FORM = By.xpath("//*[text()='Close']");

	//publication form

	public final By ELEMENT_PUBLIC_STATUS = By.xpath("//*[contains(text(),'Published')]/..//a");
	public final By ELEMENT_STAGED_STATUS = By.xpath("//*[contains(text(),'Staged')]/..//a");
	public final By ELEMENT_PENDING_STATUS = By.xpath("//*[contains(text(),'Pending')]/..//a");
	public final By ELEMENT_APPROVED_STATUS = By.xpath("//*[contains(text(),'Approved')]/..//a");
	public final By ELEMENT_CURRENT_STATUS = By.xpath("//*[@class='currentStatus']");
	public final String ELEMENT_CURRENT_SPECIFIC_STATUS = "//*[@class='currentStatus']/p[contains(text(),'${status}')]";
	public final By ELEMENT_CURRENT_PUBLIC_STATUS = By.xpath("//*[@class='currentStatus']/*[text()='Published']");
	public final By ELEMENT_CURRENT_DRAFT_STATUS = By.xpath("//*[@class='currentStatus']/*[text()='Draft']");
	public final By ELEMENT_CURRENT_PENDING_STATUS = By.xpath("//*[@class='currentStatus']/*[text()='Pending']");
	public final By ELEMENT_CURRENT_APPROVED_STATUS = By.xpath("//*[@class='currentStatus']/*[text()='Approved']");
	public final By ELEMENT_CURRENT_STAGED_STATUS = By.xpath("//*[@class='currentStatus']/*[text()='Staged']");
	public final String MSG_INVALID_DATE_TIME = "The date format is invalid. Please check again.";
	public final String ELEMENT_REVISION = "//td[contains(text(),'${state}[Current Revision]')]";
	public final String ELEMENT_HISTORY_ITEM = "//div[text()='${state}']";

	//View Properties form
	public final By ELEMENT_VIEW_PROPERTIES_ICON = By.xpath("//i[contains(@class,'uiIconEcmsViewProperties')]");
	public final By ELEMENT_PROPERTIES_TAB = By.linkText("Properties");
	public final By ELEMENT_ADD_PROPERTY_TAB = By.linkText("Add New Property");
	public final By ELEMENT_VALUE_INPUT = By.xpath("//input[contains(@id,'value')]");
	public final By ELEMENT_ADD_PROPERTY_INPUT = By.name("property_select");
	public final String ELEMENT_PROPERTY = "//td[text()='{$property}']/..//div[contains(text(),'{$value}')]"; 
	//Metadata form
	public final By ELEMENT_VIEW_METADATA_ICON = By.xpath("//i[contains(@class,'uiIconEcmsViewMetadatas')]");
	public final By ELEMENT_METADATA_POPUP_TEXT = By.xpath("//span[@class='PopupTitle popupTitle' and text()='View Metadata']");
	public final String ELEMENT_EDIT_PROPERTY_ICON = "//*[@class='uiPropertyTab']//*[text()='${property}']/..//*[contains(@class, 'uiIconEdit')]";
	public final String ELEMENT_DELETE_PROPERTY_ICON = "//*[@class='uiPropertyTab']//*[text()='${property}']/..//*[contains(@class, 'uiIconDelete')]";
	public final String ELEMENT_VALUE_PROPERTY = "//*[@class='uiPropertyTab']//*[text()='${property}']/..//*[@class='text']";

	//Show Drives link
	public final By ELEMENT_SHOW_DRIVES = By.xpath("//*[@data-original-title = 'Show Drives']");

	//Action bar 
	public final By ELEMENT_ADD_ITEM = By.xpath("//*[@data-original-title='Add Item']");	
	public final By ELEMENT_DELETE_NODE_ICON = By.xpath("//*[@id='JCRContextMenu']//*[@class='uiIconEcmsDelete']");
	public final By ELEMENT_CUT_NODE_ICON = By.xpath("//*[@id='JCRContextMenu']//*[@class='uiIconEcmsCut']");
	public final By ELEMENT_COPY_NODE_ICON = By.xpath("//*[@id='JCRContextMenu']//*[@class='uiIconEcmsCopy']");
	public final By ELEMENT_ADD_SYMLINK_LIST_VIEW = By.xpath("//*[@id='JCRContextMenu']//i[@class='uiIconEcmsAddSymLink']");
	public final By ELEMENT_LOCK_ICON = By.xpath("//*[@id='JCRContextMenu']//i[@class='uiIconEcmsLock']");
	public final By ELEMENT_UNLOCK_ICON = By.xpath("//*[@id='JCRContextMenu']//i[@class='uiIconEcmsUnlock']");

	//publication TAB
	public final By ELEMENT_PUBLICATION_TAB = By.xpath("//a[contains(text(),'Publication')]");
	public final By ELEMENT_TEMPLATE_LIST_TEXT = By.xpath("//div[contains(text(),'Select your template in the list below')]");
	public final By ELEMENT_EDIT_LINK = By.xpath("//*[@class='actionIcon']//*[@class='uiIconEcmsEditDocument uiIconEcmsLightGray']");
	//public final By ELEMENT_NEW_CONTENT_LINK = By.xpath("//*[@class='actionIcon']//*[@class='uiIconEcmsAddDocument']");
	public final By ELEMENT_NEW_CONTENT_LINK = By.xpath("//*[@class='actionIcon']//*[@class='uiIconEcmsAddDocument uiIconEcmsLightGray']");

	public final By ELEMENT_PUBLICATION = By.xpath("//a[contains(text(),'Publications')]");
	public final By ELEMENT_PUBLISH_ICON = By.xpath("//*[@class='actionIcon']//*[@class='uiIconEcmsPublicationPublish uiIconEcmsLightGray']");
	public final By ELEMENT_PUBLICATION_ICON = By.className("uiIconEcmsManagePublications uiIconEcmsLightGray");
	public final By ELEMENT_VIEW_PROPERTIES = By.xpath("//*[@class='actionIcon']//*[@class='uiIconEcmsViewProperties uiIconEcmsLightGray']");

	public final By ELEMENT_PUBLICATION_NODE_ICON=By.xpath("//*[contains(@class,'uiIconEcmsManagePublications uiIconEcmsLightGray')]");
	public final By ELEMENT_CONTENT_NAVIGATION_ICON = By.xpath("//*[@class='actionIcon']//*[contains(@class,'uiIconEcmsContentNavigation')]");
	/*
	 * Added by PhuongDT
	 * Date 06/09/2013
	 */
	public final String ELEMENT_PUBLICATION_STATUS = "//*[@class = 'activeStatus']/*[text()='${status}']/../a[@class='node']";
	public final String ELEMENT_REVISION_STATUS = "//*[@class = 'currentStatus']/*[text()='${status}']";
	public final By ELEMENT_CLEAR_SELECTION = By.xpath("//*[@class='fileViewStatus']//*[@id='FileViewClearSelection']");
	/*End Added*/

	public final By ELEMENT_VERSIONS_LINK = By.xpath("//*[@class='actionIcon']//*[@class='uiIconEcmsManageVersions uiIconEcmsLightGray']");
	public final String ELEMENT_RESTORE_VERSION_ICON = "//*[contains(text(), 'Version: ${version}')]/..//*[@class = 'uiIconRestore uiIconLightGray']";
	public final String ELEMENT_PUBLICATION_STATE = "//p[contains(text(),'{$state}')]/../a[@class='node']";	
	public final By ELEMENT_SCHEDULE_TAB = By.xpath("//a[text()='Scheduled']");	
	public final By ELEMENT_PUB_FROM_INPUT = By.name("UIPublicationPanelStartDateInput");
	public final By ELEMENT_HISTORY_TAB = By.linkText("History");
	public final By ELEMENT_REVISION_TAB = By.linkText("Revision");

	public final By ELEMENT_PUB_TO_INPUT = By.name("UIPublicationPanelEndDateInput");
	public final String ELEMENT_REVISION_DATE = "//*[contains(text(), '${status}')]/../td[2]";
	public final By ELEMENT_FIRST_REVISION_DATE = By.xpath(ELEMENT_REVISION_DATE.replace("${status}", "Draft[Current Revision]"));
	public final By ELEMENT_ADD_RELATION_LINK = By.xpath("//*[@class='actionIcon']//*[@class='uiIconEcmsManageRelations uiIconEcmsLightGray']");
	public final By ELEMENT_SELECT_RELATION_TAB = By.xpath("//*[contains(text(), 'Select Relation')]");
	public final By ELEMENT_RELATION_LIST_TAB = By.xpath("//*[contains(text(), 'Relation List')]");
	public final By ELEMENT_SHOW_RELATION_ICON = By.xpath("//i[contains(@class,'uiIconEcmsRelationMini')]");
	public final String ELEMENT_RELATION_LINK = "//a[text()='{$relation}']";
	public final String ELEMENT_DELETE_RELATION_ICON = "//span[contains(text(),'{$relation}')]/../..//i[@class='uiIconDelete uiIconLightGray']";
	public final String MESSAGE_DELETE_RELATION = "Are you sure you want to delete this relation?";

	//Action Bar > Categories
	public final String ELEMENT_CATEGORY_OPTION = "//*[@name='taxonomyTree']/option[@value='${CATEGORY_TREE_NAME}']";

	//Personal Documents > Action Bar > Sort By 
	public final By ELEMENT_SORT_BY_BUTTON = By.xpath("//*[@id='FileViewBreadcrumb']//*[contains(@class,'btn btn-small dropdown-toggle')]");
	public final String ELEMENT_SORT_BY_TYPE = "//*[@class='dropdown-menu']//*[contains(text(), '${type}')]";
	public final By ELEMENT_SORT_DOWN_ARROW = By.className("uiIconSortDown");
	public final By ELEMENT_SORT_UP_ARROW = By.className("uiIconSortUp");

	// Add site path
	public final String ELEMENT_SITE_PATH = "//span[@class='nodeName' and text()='${title}']";
	public final String ELEMENT_ACTIONS = "//*[contains(text(), '${action}')]";
	public By ELEMENT_ADD_ACTION_LINK = By.linkText("Add Action");
	public By ELEMENT_UPLOAD_FILE = By.linkText("Upload");
	public By ELEMENT_PUBLISH_FILE = By.linkText("Publish");
	public String ELEMENT_STATUS_FILE = "//span[@class='nodeName' and text()='${title}']/../../../..//div[@data-original-title= 'status' and text() = '${status}']";
	public final By ELEMENT_MORE_LINK = By.linkText("More");
	//public final By ELEMENT_NAVIGATION_LINK = By.linkText("Content Navigation");
	public final By ELEMENT_NAVIGATION_LINK = By.xpath(".//*[@id='ListHideContainer']//*[@class='uiIconEcmsContentNavigation uiIconEcmsLightGray']");
	public final By ELEMENT_NAVIGATION_FROM=By.xpath("//*[@class='uiForm UINavigationForm']");
	public final By ELEMENT_MANAGE_ACTION_LINK = By.linkText("Actions");
	public final By ELEMENT_VISIBLE_CHECKBOX = By.id("Visible");
	public final By ELEMENT_NAVIGATION_NODE = By.id("NavigationNode");
	public final By ELEMENT_CLICKABLE_CHECKBOX = By.id("Clickable");
	public final By ELEMENT_NAVIGATION_SELECT_NODE = By.xpath("//i[@class= 'uiIconSelectNavigationNode uiIconLightGray']");
	public final String ELEMENT_NAVIGATION_PATH = "//*[contains(@title,'${path}')]/../..//*[contains(@class,'uiIconSelectPage')]";
	public final By ELEMENT_NAVIGATION_SELECT_LIST = By.xpath("//i[@class='uiIconSelectListTargetPage uiIconLightGray']");
	public final By ELEMENT_NAVIGATION_SELECT_DETAIL = By.xpath("//i[@class='uiIconSelectDetailTargetPage uiIconLightGray']");
	public final String ELEMENT_NAVIGATION_LIST_PATH = "//*[@title='${path}']/*[@class='uiIconFileMini uiIconLightGray']";
	public final By ELEMENT_NAVIGATION_DISPLAY_ORDER = By.id("Index");

	public final By ELEMENT_REFRESH_BUTTON = By.xpath("//*[contains(@class,'uiIconRefresh')]");

	// site management link
	public final By ELEMENT_SITES_MANAGEMENT_ICON=By.xpath("//*[@class='uiIconEcmsHome uiIconEcmsLightGray']");	
	public final By ELEMENT_RESTORE_FROM_TRASH=By.xpath("//*[@class='uiIconEcmsRestoreFromTrash']");

	//List View
	String ELEMENT_LOCKED_NODE_LIST_VIEW = "//*[contains(@data-original-title, 'Locked by')]//*[contains(text(),'${name}')]";
	/*==================================================================================*/
	/**
	 * Go to show all Drives
	 */
	public void showDrives(){
		Utils.pause(500);
		if (waitForAndGetElement(ELEMENT_SHOW_DRIVES, 3000, 0) != null){
			click(ELEMENT_SHOW_DRIVES);
		}else {
			click(By.xpath("//*[@title = 'Show Drives']"));
		}
		Utils.pause(1000);
	}

	/**
	 * Go to content navigation
	 */
	public void goToContentNavigation(){
		info("Go to content navigation");
		if(waitForAndGetElement(ELEMENT_NAVIGATION_LINK, 5000,0)==null){
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		waitForAndGetElement(ELEMENT_NAVIGATION_LINK, DEFAULT_TIMEOUT, 0);
		click(ELEMENT_NAVIGATION_LINK);
	}

	/**
	 * Go to add new content
	 */
	public void goToAddNewContent(){
		for (int repeat = 1;; repeat++)	{	
			if (repeat >= ACTION_REPEAT) {
				Assert.fail("Cannot perform the action after " + ACTION_REPEAT + "tries");
			}
			WebElement newContent = waitForAndGetElement(ELEMENT_NEW_CONTENT_LINK, 10000, 0);
			if (newContent == null){
				WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK, 10000, 0);
				if (more != null){
					mouseOverAndClick(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
					Utils.pause(1000);
				}else {
					info("There is not Add New content icon in action bar");
					break;
				}
			}else break;
			Utils.pause(WAIT_INTERVAL);
			info("retry...[" + repeat + "]");
		}
		click(ELEMENT_NEW_CONTENT_LINK);
		waitForElementNotPresent(ELEMENT_NEW_CONTENT_LINK, DEFAULT_TIMEOUT, 1);
	}

	/**
	 * Go to add new folder
	 */
	public void goToAddNewFolder(){	
		Utils.pause(1000);
		for (int repeat = 0;; repeat++)	{	
			if (repeat >= ACTION_REPEAT) {
				Assert.fail("Cannot perform the action after " + ACTION_REPEAT + "tries");
			}
			WebElement newFolder = waitForAndGetElement(ELEMENT_NEW_FOLDER_LINK, 5000, 0);
			if (newFolder == null){
				WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK, 5000, 0);
				if (more != null){
					mouseOverAndClick(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
					Utils.pause(1000);
				}else {
					info("There is not Add New Folder icon in action bar");
					break;
				}
			} 
			mouseOverAndClick(ELEMENT_NEW_FOLDER_LINK);
			if (waitForAndGetElement(ELEMENT_FOLDER_TITLE_TEXTBOX,30000,0) != null) break;
			Utils.pause(WAIT_INTERVAL);
			info("retry...[" + repeat + "]");
		}
	}

	/**
	 * Collaboration Tab
	 */
	public void goToCollaboration(){
		mouseOver(ELEMENT_COLLABORATION_TAB, true);
		click(ELEMENT_COLLABORATION_TAB);
	}

	/**
	 * Edit a document
	 * @param title
	 */
	public void goToEditDocument(String title)
	{	
		if (title != null){
			goToNode(title);
		}
		if (waitForAndGetElement(ELEMENT_EDIT_LINK, 3000, 0) != null){
			click(ELEMENT_EDIT_LINK);
		}else{
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			click(ELEMENT_EDIT_LINK);
		}
		waitForAndGetElement(button.ELEMENT_SAVE_CLOSE_BUTTON);
	}

	/**
	 * Go to view mode (eg: Web view, List view ...)
	 * @param viewType
	 */
	public void goToViewMode(String viewType){
		info("-- Change to view mode... --" + viewType);
		Utils.pause(1000);
		click(By.xpath(ELEMENT_VIEW_MODE_LINK.replace("${viewName}", viewType)));
		Utils.pause(1000);
	}


	/**
	 * Go to 1 node by path in Intranet/document
	 * @param path
	 */
	public void goToNodeByAddressPath(String path){
		type(ELEMENT_ADDRESS_BAR,path,true);
		String pageId = waitForAndGetElement(By.xpath("//*[@id='UIPage']/div/div")).getAttribute("id");
		((JavascriptExecutor) driver).executeScript("javascript:eXo.webui.UIForm.submitForm('" + pageId + "#UIAddressBar','ChangeNode',true)");
		Utils.pause(2000);
	}

	/**
	 * Add a category in DMS Administration - Simple View
	 * @param categoryName
	 * @param params
	 */
	public void addCategoryInSimpleView(String categoryName, Object...params){
		Boolean checkCategory = (Boolean) (params.length > 0 ? params[0]:true);

		info("Add a simple category");
		click(ELEMENT_BUTTON_ADD_CATEGORY);
		waitForAndGetElement(ELEMENT_ADD_CATEGORY_FORM);
		type(ELEMENT_INPUT_CATEGORY_NAME, categoryName, false);
		click(button.ELEMENT_SAVE_BUTTON);
		if (checkCategory){
			waitForAndGetElement(ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", categoryName));
		}
		Utils.pause(500);
	}

	/**
	 * Export node
	 * @param systemView
	 * @param zip
	 * @param exportVersionHistory
	 */
	public void exportNode(boolean systemView, boolean zip, boolean exportVersionHistory) {
		WebElement eProperties = waitForAndGetElement(ELEMENT_VIEW_PROPERTIES_ICON,10000,0);
		WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK,5000,0);
		if(eProperties == null)
			if (more !=null )
				click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);

		waitForAndGetElement(ELEMENT_EXPORT_LINK);
		click(ELEMENT_EXPORT_LINK);
		if (!systemView)
		{
			click(ELEMENT_DOC_VIEW,2);
		}

		if (zip)
		{
			click(ELEMENT_ZIP,2);
		}

		if (exportVersionHistory)
		{
			click(ELEMENT_EXPORT_VERSION);
			Utils.pause(5000);
			waitForAndGetElement(ELEMENT_EXPORT_VERSION);
			button.cancel();
		}
		else
		{
			click(ELEMENT_EXPORT);
		}

		Utils.pause(20000);
		waitForElementNotPresent(ELEMENT_EXPORT);
	}

	/**Import node
	 * @param linkFile
	 * @param linkVersion
	 * @param behavior
	 * @param version
	 */
	public void importNode(String linkFile, String linkVersion, String behavior, boolean version) {
		WebElement eProperties = waitForAndGetElement(ELEMENT_VIEW_PROPERTIES_ICON,10000,0);
		WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK,5000,0);
		String[] files = linkFile.split("/");
		int length = files.length;
		if(eProperties == null)
			if (more !=null )
				click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		//Click import button
		waitForAndGetElement(ELEMENT_IMPORT_LINK);
		click(ELEMENT_IMPORT_LINK);
		//Switch to frame upload file
		WebElement upload = waitForAndGetElement(ELEMENT_UPLOAD_IMG_ID, DEFAULT_TIMEOUT,1,2);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';", upload);
		upload.sendKeys(Utils.getAbsoluteFilePath(linkFile));	
		Utils.pause(500);
		switchToParentWindow();

		select(ELEMENT_BEHAVIOR, behavior);
		if (version)
		{		
			WebElement uploadVersion = waitForAndGetElement(ELEMENT_UPLOAD_VERSION_ID, DEFAULT_TIMEOUT,1,2);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';", uploadVersion);
			uploadVersion.sendKeys(Utils.getAbsoluteFilePath(linkVersion));
			Utils.pause(500);
			click(ELEMENT_IMPORT);
			Utils.pause(500);
			waitForMessage("Imported successfully.");
			click(button.ELEMENT_OK_BUTTON);
		}
		else 
		{
			waitForAndGetElement(ELEMENT_IMPORT_FILE_LABEL.replace("${fileName}", files[length-1]));
			Utils.pause(1000);
			click(ELEMENT_IMPORT);

			waitForMessage("Imported successfully.");
			click(button.ELEMENT_OK_BUTTON);
		}
	}

	/**
	 * Add category for node
	 * @param categoryTree
	 * @param rootTree
	 * @param params
	 */
	public void addCategoryForNode(String categoryTree, boolean rootTree, Object...params) {
		String categoryPath = (String) (params.length > 0 ? params[0]:"");
		String categoryName = (String) (params.length > 1 ? params[1]:"");

		By ELEMENT_ADD_CATEGORY_SPECIFIC = By.xpath("//div[contains(text(),'"+categoryName+"')]/following::a[@title='select']");
		By ELEMENT_ADD_CATEGORY_SPECIFIC_OTHER = By.xpath("//div[contains(normalize-space(), categoryName) and @class='Text']/../../td/a[@data-original-title='Select']");
		if (waitForAndGetElement(ELEMENT_CATEGORIES_LINK, 5000, 0) == null){
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			waitForAndGetElement(ELEMENT_CATEGORIES_LINK);
		}
		click(ELEMENT_CATEGORIES_LINK);

		waitForAndGetElement(ELEMENT_SELECT_CATEGORY_TAB);
		click(ELEMENT_SELECT_CATEGORY_TAB);
		Utils.pause(500);
		select(ELEMENT_CATEGORY_TREE_BOX, categoryTree);
		if (rootTree) {
			click(ELEMENT_ADD_ROOT_BUTTON);
			waitForTextPresent(categoryTree);
			checkUnexpectedError();	
		}
		else {
			if (categoryPath != ""){
				String paths [] = categoryPath.split("/");
				for (String path : paths)
					click(By.xpath("//*[@title='"+path+"']"));
			}
			if (waitForAndGetElement(ELEMENT_ADD_CATEGORY_SPECIFIC, 5000, 0) != null){
				click(ELEMENT_ADD_CATEGORY_SPECIFIC);	
			}else {
				click(ELEMENT_ADD_CATEGORY_SPECIFIC_OTHER);
			}
			Utils.pause(500);
			checkUnexpectedError();
		}
		if (waitForAndGetElement(button.ELEMENT_CLOSE_BUTTON, 3000, 0) != null ){
			click(button.ELEMENT_CLOSE_BUTTON);
		}
		info ("------Category " + categoryName + " is added succesfully");
	}

	/**
	 * Add multi categories for node
	 * @param categoryTree
	 * @param rootTree
	 * @param index
	 * @param params
	 */
	public void addMultiCategoriesForNode(String categoryTree,
			boolean rootTree, String index, Object... params) {
		String categoryPath = (String) (params.length > 0 ? params[0] : "");
		By ELEMENT_ADD_CATEGORY_SPECIFIC_OTHER = By
				.xpath(ELEMENT_MULTI_CATEGORIES.replace("${index}", index));

		if (waitForAndGetElement(ELEMENT_CATEGORIES_LINK, 5000, 0) == null) {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			waitForAndGetElement(ELEMENT_CATEGORIES_LINK);
		}
		click(ELEMENT_CATEGORIES_LINK);

		waitForAndGetElement(ELEMENT_SELECT_CATEGORY_TAB);
		click(ELEMENT_SELECT_CATEGORY_TAB);
		Utils.pause(500);
		select(ELEMENT_CATEGORY_TREE_BOX, categoryTree);
		if (rootTree) {
			click(ELEMENT_ADD_ROOT_BUTTON);
			waitForTextPresent(categoryTree);
			checkUnexpectedError();
		} else {
			if (categoryPath != "") {
				String paths[] = categoryPath.split("/");
				for (String path : paths)
					click(By.xpath("//*[@title='" + path + "']"));
			}
			click(ELEMENT_ADD_CATEGORY_SPECIFIC_OTHER);
			Utils.pause(500);
			checkUnexpectedError();
		}
		if (waitForAndGetElement(button.ELEMENT_CLOSE_BUTTON, 3000, 0) != null) {
			click(button.ELEMENT_CLOSE_BUTTON);
		}
	}


	/**
	 * Add version for a node
	 * @param locator
	 * @param vesion
	 */
	public void addVersionForNode(By locator, String vesion){
		info("-- Add a version for a document... --");
		goToNode(locator);
		clearCache();
		if (waitForAndGetElement(ELEMENT_VERSIONS_LINK,10000,0)!=null){
			info("-- Versions tab is already displayed --");
		}else if (isElementPresent(ELEMENT_MORE_LINK_WITHOUT_BLOCK)){
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			if (isElementPresent(ELEMENT_VERSIONS_LINK)){
				info("-- Versions tab is already displayed --");
			}
		}
		click(ELEMENT_VERSIONS_LINK);
		click(ELEMENT_ICON_VERSION_ADD);
		type(ELEMENT_TEXTBOX_VERSION,vesion,true);
		click(button.ELEMENT_SAVE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);
		click(button.ELEMENT_CLOSE_BUTTON);
	}

	/**
	 * Select a drive
	 * @param locator
	 */
	public void chooseDrive(By locator){
		info("-- Select a drive --");
		showDrives();
		Utils.pause(1000);
		click(locator);
		Utils.pause(1000);
	}

	/**
	 * Publish a document
	 */
	public void publishDocument(){
		button = new Button(driver);
		info("Publish a document");
		openManagePublicationForm();
		WebElement current = waitForAndGetElement(ELEMENT_CURRENT_STATUS);
		info(current.getText());
		if (current.getText().contains("Published") == false){
			click(ELEMENT_PUBLIC_STATUS);
		}
		waitForAndGetElement(ELEMENT_CURRENT_PUBLIC_STATUS);
		button.close();
		info("Publish a document is successful");
	}

	/**
	 * Node Permission
	 */
	public void goToNodePermissionManagement(){
		if ( waitForAndGetElement(ELEMENT_PERMISSION_LINK, 5000, 0) !=null){
			info("-- Permission tab is already displayed --");
			click(ELEMENT_PERMISSION_LINK);
		}else{
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			click(ELEMENT_PERMISSION_LINK);
		}
		Utils.pause(1000);
	}

	/**
	 * Add item
	 * @param item
	 * @param eItem
	 * @param params
	 */
	public void addItem2ActionBar(String item, By eItem, Object...params){
		String view = (String) (params.length > 0 ? params[0] : "Web");
		String tab = (String) (params.length > 1 ? params[1] : "Authoring");
		WebElement element = waitForAndGetElement(eItem, 5000, 0);
		WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK, 5000, 0);
		Utils.pause(2000);
		if (element != null){
			info(item + " tab is already displayed --");
		}else if (more != null){
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			Utils.pause(2000);
			if (waitForAndGetElement(eItem, 5000, 0) != null){
				info(item + " tab is already displayed --");
			}else{
				magView.setup2ShowViewAction(item, view, tab);
				magAcc.signOut();
				magAcc.signIn(DATA_USER1, DATA_PASS);
				navToolBar.goToSiteExplorer();
			}
		}else {
			Utils.pause(2000);
			magView.setup2ShowViewAction(item, view, tab);
			magAcc.signOut();
			magAcc.signIn(DATA_USER1, DATA_PASS);
			navToolBar.goToSiteExplorer();
		}
		driver.navigate().refresh();
		Utils.pause(2000);
	}

	/**
	 * Go to Favorite
	 */
	public void goToAddToFavorite(){
		if (isTextPresent("Add To Favorite")){
			info("-- Add To Favorite tab is already displayed --");
			click(ELEMENT_ADD_TO_FAVORITE_NODE);
		}else{
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			click(ELEMENT_ADD_TO_FAVORITE_NODE);
		}
		Utils.pause(1000);
	}

	/**
	 * Go to add Symlink
	 */
	public void goToAddSymlinkTab(){
		if (isTextPresent("Add Symlink")){
			info("-- Add Symlink tab is already displayed --");
			if(isElementPresent(ELEMENT_ACTION_BAR_ADD_SYMLINK))
				click(ELEMENT_ACTION_BAR_ADD_SYMLINK);
			else
				click(ELEMENT_ADD_SYMLINK_NODE);
		}else{
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			if(isElementPresent(ELEMENT_ACTION_BAR_ADD_SYMLINK))
				click(ELEMENT_ACTION_BAR_ADD_SYMLINK);
			else
				click(ELEMENT_ADD_SYMLINK_NODE);
		}
		Utils.pause(1000);
	}

	/**
	 * Go to Target of a node when adding symlink
	 * @param path
	 */
	public void goToTargetNodeWhenAddSymlink(String path){
		goToAddSymlinkTab();
		waitForAndGetElement(ELEMENT_ADD_SYMLINK_POPUP);
		click(ELEMENT_ADD_ITEM);	
		String[] temp;
		String delimiter = "/";
		temp = path.split(delimiter);
		for(int i =0; i < temp.length; i++){
			info("Go to "+temp[i]);
			click(By.xpath(ELEMENT_SYMLINK_PATH_NODE_TITLE.replace("${node}", temp[i])));
			Utils.pause(100);
		}
		Utils.pause(1000);
	}

	/**
	 * Add symlink for node with target node is documents
	 * @param workspace
	 * @param path
	 * @param name
	 */
	public void addSymlink(String workspace, String path, String name){
		goToAddSymlinkTab();
		waitForAndGetElement(ELEMENT_ADD_SYMLINK_POPUP);
		if (path != null && path != ""){
			click(ELEMENT_ADD_ITEM);
			select(ELEMENT_SYMLINK_WORKSPACE, workspace);
			selectHomePathForCategoryTree(path);
		}
		String[] temp;
		String delimiter = "/";
		temp = path.split(delimiter);
		if (name.equalsIgnoreCase(temp[temp.length - 1] + ".lnk") == false){
			type(ELEMENT_SYMLINK_NAME, name, true);
		}
		assert getValue(ELEMENT_SYMLINK_NAME).equalsIgnoreCase(name);
		click(button.ELEMENT_SAVE_BUTTON); 
	}

	/**
	 * Acions on Element
	 * @param elementName
	 * @param action
	 * @param params
	 */
	public void actionsOnElement(String elementName, ContextMenu.actionType action, Object...params){
		Boolean mDelete = (Boolean) (params.length > 0 ? params[0]: false);
		Boolean notPersonalDoc = (Boolean) (params.length > 1 ? params[1]: false);
		info("-- Action: "+ action + " the element: " + elementName);
		if(!notPersonalDoc)
			click(ELEMENT_PERSONAL_DOCUMENTS);
		if (mDelete){
			info("mDelete is true");
			String[] nodes = elementName.split("/");
			for (String node : nodes){
				info("-- Delete node: " + node); 
				if (waitForAndGetElement(ELEMENT_SELECT_CHECKBOX.replace("${name}", node), 3000, 0, 2) != null){
					info("Checkbox is not in admin view");
					check(ELEMENT_SELECT_CHECKBOX.replace("${name}", node), 2);
				}else{
					info("Checkbox is in admin view");
					check(ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", node) + "/../../../div[@class='columnCheckbox']", 2);
				}
			}
		}else{
			info("mDelete is false");
			if (waitForAndGetElement(ELEMENT_UI_CHECKBOX.replace("${element}", elementName), 3000, 0, 2) != null){
				click(ELEMENT_UI_CHECKBOX.replace("${element}", elementName), 2);
			}else{
				click(ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", elementName) + "/../../../div[@class='columnCheckbox']", 2);
			}
		}
		switch (action){
		case COPY:
			click(ELEMENT_COPY_NODE);
			break;
		case CUT:
			click(ELEMENT_CUT_NODE);
			break;
		case EDIT:
			click(cMenu.ELEMENT_MENU_EDIT);
			break;
		case DELETE:
			if (waitForAndGetElement(cMenu.ELEMENT_MENU_DELETE, 3000, 0) != null){
				click(cMenu.ELEMENT_MENU_DELETE);
			}else {
				click(By.className("uiIconEcmsDelete"));
			}
			dialog.deleteInDialog();
			waitForElementNotPresent(ELEMENT_UI_CHECKBOX.replace("${element}", elementName));
			break;
		case PASTE:
			click(ELEMENT_PASTE_NODE);
			break;
		default:
			break;
		}
		Utils.pause(1000);
	}

	/**
	 * Create a relation between 2 nodes
	 * @param nodeName1
	 * @param pathToNodeName2
	 * @param params
	 */
	public void createRelation(String nodeName1, String pathToNodeName2, Object...params){
		String[] temp;
		String delimiter = "/";
		temp = pathToNodeName2.split(delimiter);
		Boolean nodeAdminView = (Boolean) (params.length > 0 ? params[0]: false);

		info("-- Create a relation:--" + nodeName1 + " and " + temp[temp.length - 1]);
		if (nodeAdminView){
			goToNode(nodeName1, true);
		}else {
			goToNode(nodeName1);
		}
		if (isTextPresent("Relations")){
			info("-- Add Relation tab is already displayed --");
			click(ELEMENT_ADD_RELATION_LINK);
		}else {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			click(ELEMENT_ADD_RELATION_LINK);
		}
		click(ELEMENT_SELECT_RELATION_TAB);
		selectHomePathForCategoryTree(pathToNodeName2);
		waitForTextPresent(temp[temp.length - 1]);
		button.close();	
		Utils.pause(500);
	}
	/**
	 * Create multiRelation
	 * @param nodeName1
	 * @param pathToNodeName2
	 * @param index
	 * @param params
	 */
	public void createMultiRelation(String nodeName1, String pathToNodeName2, String index, Object...params){
		String[] temp;
		String delimiter = "/";
		temp = pathToNodeName2.split(delimiter);
		Boolean nodeAdminView = (Boolean) (params.length > 0 ? params[0]: false);

		info("-- Create a relation:--" + nodeName1 + " and " + temp[temp.length - 1]);
		if (nodeAdminView){	
			goToNode(nodeName1, true);
		}else {
			goToNode(nodeName1);
		}
		if (isTextPresent("Relations")){
			info("-- Add Relation tab is already displayed --");
			click(ELEMENT_ADD_RELATION_LINK);
		}else {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			click(ELEMENT_ADD_RELATION_LINK);
		}
		click(ELEMENT_SELECT_RELATION_TAB);
		selectMultiHomePathForCategoryTree(pathToNodeName2,index);
		waitForTextPresent(temp[temp.length - 1]);
		button.close();	
		Utils.pause(500);
	}

	/**
	 * Undo deleted Items
	 * @param nodeName
	 */
	public void undoDeletion(String...nodeName){
		String node = nodeName.length > 0 ? nodeName[0]: "";

		info("-- Undo deletion --");
		if (node != ""){
			info("\'" + node + "' was deleted succesfully.");
			waitForTextPresent("\'" + node + "' was deleted succesfully.");
		}
		if(this.plfVersion.equalsIgnoreCase("4.1"))
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",waitForAndGetElement(ELEMENT_UNDO_DELETED_ITEM));
		else if(this.plfVersion.equalsIgnoreCase("4.0"))
			click(ELEMENT_UNDO_DELETED_ITEM);
		if (waitForAndGetElement(button.ELEMENT_OK_BUTTON, 3000, 0) != null){
			click(button.ELEMENT_OK_BUTTON);
		}
		if (node != ""){
			waitForTextPresent("\'" + node + "' was successfully restored.");
		}
		Utils.pause(1000);		
	}

	/**
	 * Delete a relation
	 * @param relation
	 */
	public void deleteRelation(String relation){
		WebElement addRelation = waitForAndGetElement(ELEMENT_ADD_RELATION_LINK, 5000, 0);
		if(addRelation == null)
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		click(ELEMENT_ADD_RELATION_LINK);

		click(ELEMENT_DELETE_RELATION_ICON.replace("{$relation}", relation));
		alert.waitForConfirmation(MESSAGE_DELETE_RELATION);

		waitForTextNotPresent(relation);

		button.close();
	}

	/**
	 * Go to Manage Categories
	 */
	public void goToManageCategories(){
		info("-- Go to Action Bar/Categories Tab --");
		if(waitForAndGetElement(ELEMENT_CATEGORIES_LINK, 5000, 0) == null){
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			click(ELEMENT_CATEGORIES_LINK);
		}
		else{
			goToNode(ELEMENT_CATEGORIES_LINK);
			waitForAndGetElement(ELEMENT_PERMISSION_MANAGEMENT_POPUP);
		} 
	}
	/**
	 * Go to add a comment
	 */
	public void goToAddComment(){
		WebElement comment = waitForAndGetElement(ELEMENT_ADD_COMMENT_LINK, 2000, 0);
		if (comment == null){
			WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK, 2000, 0);
			if (more != null){
				click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			} else {
				info("Do not have add comment icon in action bar");
				return;
			}
		}
		click(ELEMENT_ADD_COMMENT_LINK);
		waitForAndGetElement(ELEMENT_ADD_COMMENT_POPUP);
		Utils.pause(1000);
	}

	/**
	 * Add comment
	 * @param comment
	 * @param params
	 */
	public void addComment(String comment,Object...params){
		Boolean isDecoredSum = (Boolean)(params.length>0 ? params[0]:false);
		info("Add a comment to the file");
		goToAddComment();
		inputFrame(ELEMENT_ADD_COMMENT_FRAME_41, comment);
		if(isDecoredSum){
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}
		button.save();
		waitForElementNotPresent(ELEMENT_ADD_COMMENT_POPUP);
		click(ELEMENT_SHOW_COMMENT_LINK);
		waitForAndGetElement(ELEMENT_HIDE_COMMENT_LINK);
		info("Verify that the comment is added successfully");
		if(isDecoredSum)
			waitForAndGetElement(By.xpath(ELEMENT_SHOW_COMMENT_CONTENT_DECORADE.replace("${comment}", comment)));
		else
			waitForAndGetElement(By.xpath(ELEMENT_SHOW_COMMENT_CONTENT.replace("${comment}", comment)));
	}

	/**
	 * Edit a comment
	 * @param oldComment
	 * @param newComment
	 * @param params
	 */
	public void editComment(String oldComment, String newComment,Object...params){
		Boolean isDecoredSum = (Boolean)(params.length>0 ? params[0]:false);
		if(waitForAndGetElement(ELEMENT_SHOW_COMMENT_LINK, 5000,0)!=null)
			click(ELEMENT_SHOW_COMMENT_LINK);
		click(By.xpath(ELEMENT_EDIT_COMMENT_ICON.replace("${comment}", oldComment)),2);
		waitForAndGetElement(ELEMENT_ADD_COMMENT_POPUP);
		inputFrame(ELEMENT_ADD_COMMENT_FRAME_41, newComment);
		if(isDecoredSum){
			pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
			ck.cke_Bold();
			ck.cke_Italic();
			ck.cke_Underline();
		}
		button.save();
		waitForElementNotPresent(ELEMENT_ADD_COMMENT_POPUP);
		click(ELEMENT_SHOW_COMMENT_LINK);
		if(isDecoredSum)
			waitForAndGetElement(By.xpath(ELEMENT_SHOW_COMMENT_CONTENT_DECORADE.replace("${comment}", newComment)));
		else
			waitForAndGetElement(By.xpath(ELEMENT_SHOW_COMMENT_CONTENT.replace("${comment}", newComment)));
	}

	/**
	 * Delete a comment
	 * @param comment
	 */
	public void deleteComment(String comment){
		if(waitForAndGetElement(ELEMENT_SHOW_COMMENT_LINK, 5000,0)!=null)
			click(ELEMENT_SHOW_COMMENT_LINK);
		click(By.xpath(ELEMENT_DELETE_COMMENT_ICON.replace("${comment}", comment)));
		alert.acceptAlert();
		waitForElementNotPresent(By.xpath(ELEMENT_SHOW_COMMENT_CONTENT.replace("${comment}", comment)));
		waitForElementNotPresent(ELEMENT_SHOW_COMMENT_LINK);
	}

	/**
	 * Vote a document
	 * @param rate
	 */
	public void voteDocument(int rate){
		WebElement comment = waitForAndGetElement(ELEMENT_VOTE_LINK, 5000, 0);
		if (comment == null){
			WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK, 5000, 0);
			if (more != null){
				click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			} else {
				info("Do not have add comment icon in action bar");
				return;
			}
		}
		click(ELEMENT_VOTE_LINK);
		waitForAndGetElement(ELEMENT_VOTE_POPUP);
		Utils.pause(1000);

		HashMap<Integer, String> rateVote = new HashMap<Integer, String>();
		rateVote.put(1, "Poor");
		rateVote.put(2, "Below average");
		rateVote.put(3, "Average");
		rateVote.put(4, "Above average");
		rateVote.put(5, "Good");

		click(By.xpath(ELEMENT_VOTE_RATE.replace("${rate}", rateVote.get(rate))));
		waitForAndGetElement(ELEMENT_VOTE_COMPONENT);
	}

	/**
	 * Add a translation for a document
	 * @param paths
	 * @param fileName
	 */
	public void addTranslationForDocument(String paths, String fileName){
		WebElement comment = waitForAndGetElement(ELEMENT_ADD_TRANSLATION_LINK, 5000, 0);
		if (comment == null){
			WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK, 5000, 0);
			if (more != null){
				click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			} else {
				info("Do not have add comment icon in action bar");
				return;
			}
		}
		click(ELEMENT_ADD_TRANSLATION_LINK);
		Utils.pause(1000);

		click(ELEMENT_SELECT_DOCUMENT_BUTTON);

		if (paths != ""){
			goToNode(paths);
		}
		click(By.xpath("//*[@id='ListRecords']//a[text()='" + fileName + "']"));
		waitForElementNotPresent(ELEMENT_SELECT_DOCUMENT_POPUP);
		button.save();
		waitForElementNotPresent(ELEMENT_ADD_TRANSLATION_POPUP);
	}


	/**
	 * Delete a category
	 * @param categoryPath
	 */
	public void deleteCategory(String categoryPath){
		WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK,5000,0);
		if (more !=null ){
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		if (waitForAndGetElement(ELEMENT_CATEGORIES_LINK, 3000, 0) != null){
			click(ELEMENT_CATEGORIES_LINK);
		}else {
			click(By.className("uiIconEcmsManageCategories"));
		}
		click(ELEMENT_DELETE_CATEGORY_ICON.replace("{$categoryPath}", categoryPath));
		alert.waitForConfirmation(MSG_DELETE_CATEGORY);

		waitForTextNotPresent(categoryPath);

		button.close();
	}

	/**
	 * Manage Publication
	 * @param state
	 * @param date
	 */
	public void managePublication(String state, String...date){
		By bState = By.xpath(ELEMENT_PUBLICATION_STATE.replace("{$state}", state));
		String date1 = (String) (date.length > 0 ? date[0]:"");
		String date2 = (String) (date.length > 1 ? date[1]:"");

		info("Manage publication state");
		openManagePublicationForm();
		click(bState);
		waitForAndGetElement(ELEMENT_CURRENT_SPECIFIC_STATUS.replace("${status}", state));
		if (state.equals("Staged") & (date.length > 0)){
			click(ELEMENT_SCHEDULE_TAB);
			if((date1!=""&& date1!= null))
				type(ELEMENT_PUB_FROM_INPUT,date1,true);
			if((date2!="" && date2!= null))
				type(ELEMENT_PUB_TO_INPUT,date2,true);
			button.save();
			if(date.length > 2){
				waitForMessage(MSG_INVALID_DATE_TIME);
				button.ok();
				button.close();
				return;
			}
			waitForMessage("Your new publication schedule was saved successfully.");
			button.ok();
			click(ELEMENT_REVISION_TAB);
		}
		waitForAndGetElement(ELEMENT_HISTORY_TAB);
		waitForAndGetElement(ELEMENT_REVISION_TAB);

		click(ELEMENT_HISTORY_TAB);
		waitForAndGetElement(ELEMENT_HISTORY_ITEM.replace("${state}", state));

		button.close();

	}


	/**
	 * Add property
	 * @param property
	 * @param value
	 */
	public void addProperty(String property, String value){

		info("Add property for a node");
		WebElement eProperties = waitForAndGetElement(ELEMENT_VIEW_PROPERTIES_ICON,10000,0);

		if(eProperties == null){
			WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK,5000,0);
			if (more !=null )
				click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		click(ELEMENT_VIEW_PROPERTIES_ICON);
		waitForAndGetElement(ELEMENT_PROPERTIES_TAB);

		click(ELEMENT_ADD_PROPERTY_TAB);
		Utils.pause(1000);
		select(ELEMENT_ADD_PROPERTY_INPUT, property);
		type(ELEMENT_VALUE_INPUT,value,true);
		button.save();

		info("Check if a property is added successfully");
		waitForAndGetElement(ELEMENT_PROPERTY.replace("{$property}", property).replace("{$value}", value));
		button.close();
	}

	/**
	 * Go to View properties tab
	 */
	public void goToPropertiesTab(){
		info("-- Go to Properties Tab --");
		WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK, 5000, 0);
		if (more !=null ){
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		click(ELEMENT_VIEW_PROPERTIES_ICON);
		waitForAndGetElement(ELEMENT_PROPERTIES_TAB);
	}

	/**
	 * Actions on selected property: Edit/Delete
	 * @param property
	 * @param option
	 * @param params
	 */
	public void actionsOnSelectedProperty(String property, String option, Object...params){
		String editProperty = (String) (params.length > 0 ? params[0]: "");
		String value = (String) (params.length > 1 ? params[1]: "");

		info(option + "-- property --" + property);
		if (option.equals("Edit")){
			click(ELEMENT_EDIT_PROPERTY_ICON.replace("${property}", property));
			select(ELEMENT_ADD_PROPERTY_INPUT, editProperty);
			type(ELEMENT_VALUE_INPUT, value, true);
			button.save();
			//Check if a property is added successfully.
			waitForAndGetElement(ELEMENT_PROPERTY.replace("{$property}", property).replace("{$value}", value));
			button.close();

		}else if (option.equals("Delete")){
			click(ELEMENT_DELETE_PROPERTY_ICON.replace("${property}", property));
			alt.acceptAlert();
			waitForElementNotPresent(ELEMENT_DELETE_PROPERTY_ICON.replace("${property}", property));
			button.close();
		}
		Utils.pause(500);
	}

	/**
	 * View metadata
	 */
	public void viewMetadata(){
		info("View metadata of a node");
		WebElement viewMetadata = waitForAndGetElement(ELEMENT_VIEW_METADATA_ICON,5000,0);
		if (viewMetadata == null)
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		click(ELEMENT_VIEW_METADATA_ICON);
		waitForAndGetElement(ELEMENT_METADATA_POPUP_TEXT);
		button.cancel();
	}

	/**
	 * Lock a node
	 * @param nodes
	 */
	public void lockNode(String nodes){
		String ELEMENT_LOCKED_NODE_LIST_VIEW = "//*[contains(@data-original-title, 'Locked by')]//*[contains(text(),'${name}')]";

		String[] node = nodes.split("/");
		for (int i = 0; i < node.length; i ++){
			click(By.xpath(ELEMENT_SELECT_CHECKBOX.replace("${name}", node[i])), 2);
		}
		if(isElementPresent(ELEMENT_LOCK_ICON))
			click(ELEMENT_LOCK_ICON);
		else
			click(ELEMENT_LOCK_NODE);
		for (int j = 0; j < node.length; j ++){
			waitForAndGetElement(ELEMENT_LOCKED_NODE_LIST_VIEW.replace("${name}", node[j]));
		}
	}

	/**
	 * Unlock a node
	 * @param nodes
	 */
	public void unLockNodeFromActionBar(String nodes){
		String[] node = nodes.split("/");
		for (int i = 0; i < node.length; i ++){
			click(By.xpath(ELEMENT_SELECT_CHECKBOX.replace("${name}", node[i])), 2);
		}
		if(isElementPresent(ELEMENT_UNLOCK_ICON))
			click(ELEMENT_UNLOCK_ICON);
		else
			click(ELEMENT_UNLOCK_NODE);
		for (int j = 0; j < node.length; j ++){
			waitForElementNotPresent(ELEMENT_LOCKED_NODE_LIST_VIEW.replace("${name}", node[j]));
		}
	}

	/**
	 * Open [Manage Publication] form
	 */
	public void openManagePublicationForm(){
		info("-- Open Manage Publication Form --");
		if ((waitForAndGetElement(ELEMENT_PUBLICATION, 5000, 0) == null )){
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		if (waitForAndGetElement(ELEMENT_PUBLICATION, 3000, 0) != null){
			click(ELEMENT_PUBLICATION);
		}else {
			click(ELEMENT_PUBLICATION_ICON);
		}
		Utils.pause(500);
	}

	/**
	 * Manage Publication > get a publish date
	 * @param revision: locator of revision date
	 */
	public String getPublishDate(By revision){
		String date = "";
		WebElement element = waitForAndGetElement(revision);
		date = element.getText();
		info("-- Publish date is " + date);
		return date;	
	}

	/**
	 * Change status to Publication
	 * @param status
	 */
	public void changeStatusPublication(String status){
		openManagePublicationForm();
		click(By.xpath(ELEMENT_PUBLICATION_STATUS.replace("${status}", status)));
		waitForAndGetElement(By.xpath(ELEMENT_REVISION_STATUS.replace("${status}", status)));
		button.close();
	}

	/**
	 * Personal Documents > Action Bar > Sort By
	 * @param type: NAME, SIZE, DATE
	 */
	public void sortBy(String type){
		info("-- Choose a field type to sort: " + type);
		click(ELEMENT_SORT_BY_BUTTON);
		click(ELEMENT_SORT_BY_TYPE.replace("${type}", type));
		Utils.pause(1000);	
	}

	/**
	 * Go to Action
	 */
	public void goToAction(){
		WebElement actionicon = waitForAndGetElement(ELEMENT_ACTION_ICON, 5000, 0);
		if (actionicon == null){
			WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK, 5000, 0);
			if (more != null){
				click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			} else {
				info("Do not have action icon in action bar");
				return;
			}
		}
		click(ELEMENT_ACTION_ICON);
		Utils.pause(1000);
	}

	/**
	 * Add a new action
	 * @param actionName
	 * @param lifeCycle
	 * @param actionType
	 * @param params
	 */
	public void addNewAction(String actionName, String lifeCycle, String actionType, Object... params){
		goToAction();
		click(ELEMENT_ADD_ACTION_TAB); 
		Utils.pause(1000);
		select (ELEMENT_ACTION_TYPE, actionType);
		type(ELEMENT_ACTION_NAME, actionName, true);
		select(ELEMENT_ACTION_LIFE_CYCLE, lifeCycle);
		if (isElementPresent(ELEMENT_ACTION_NODE_TYPE_SEARCH)){
			click(ELEMENT_ACTION_NODE_TYPE_SEARCH);
			check(ELEMENT_ACTION_SELECT_ALL_DOC,2);	
		}
		waitForAndGetElement(button.ELEMENT_SAVE_BUTTON);
		button.save();
		Utils.pause(2000);
		button.close();
	}
	/**
	 * Action Present is availabled
	 * @param actionName
	 * @return
	 */
	public Boolean isActionPresent(String actionName){
		goToAction();
		click(ELEMENT_AVAILABLE_ACTIONS);
		String verifyXPath = ELEMENT_ACTION_AVAILABLE_ACTION.replace("${actionname}", actionName);
		Boolean isElement = isElementPresent(By.xpath(verifyXPath));
		Utils.pause(1000);
		button.close();
		return isElement;
	}

	/**
	 * Delete a action
	 * @param actionName
	 */
	public void deleteAction(String actionName){
		goToAction();
		click(ELEMENT_AVAILABLE_ACTIONS);
	}

	/**
	 * actions on Action of a node
	 * @param actionName
	 * @param option
	 * @param params
	 */
	public void actionsOnActionsOfNode(String actionName, String option, Object...params){
		String newactionname = (String) (params.length > 0 ? params[0]: "");
		String newactiontype = (String) (params.length > 1 ? params[1]: "");
		String newlifecycle = (String) (params.length > 1 ? params[1]: "");

		goToAction();
		click(ELEMENT_AVAILABLE_ACTIONS);

		info(option + "-- actionName --" + actionName);
		if (option.equals("Edit")){
			if(!newactionname.isEmpty())
				select (ELEMENT_ACTION_TYPE, newactiontype);
			if (!newactionname.isEmpty())
				type(ELEMENT_ACTION_NAME, newactionname, true);
			if (newlifecycle.isEmpty())
				select(ELEMENT_ACTION_LIFE_CYCLE, newlifecycle);
			button.save();
			//Check if a property is added successfully.
			waitForElementNotPresent(ELEMENT_ACTION_AVAILABLE_ACTION.replace("${actionname}", newactionname));
			button.close();

		}else if (option.equals("Delete")){
			click(ELEMENT_DELETE_ACTION_ICON.replace("${actionname}", actionName));
			alt.acceptAlert();
			waitForElementNotPresent(ELEMENT_ACTION_AVAILABLE_ACTION.replace("${actionname}", actionName));
			button.close();
		}
		Utils.pause(500);
	}

	/**
	 * Go to Relation
	 * @param nodeName1
	 * @param params
	 */
	public void goToRelation(String nodeName1, Object...params){
		Boolean nodeAdminView = (Boolean) (params.length > 0 ? params[0]: false);
		if (nodeAdminView){
			goToNode(nodeName1, true);
		}else {
			goToNode(nodeName1);
		}
		if (isTextPresent("Relations")){
			info("-- Add Relation tab is already displayed --");
			click(ELEMENT_ADD_RELATION_LINK);
		}else {
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			click(ELEMENT_ADD_RELATION_LINK);
		}
		click(ELEMENT_RELATION_LIST_TAB);
	}
	/**
	 * Open a Version info form
	 * @param locator
	 */
	public void openVersionInfoForm(By locator){
		info("-- Open version Info form of a document... --");
		goToNode(locator);
		if (waitForAndGetElement(ELEMENT_VERSIONS_LINK,10000,0)!=null){
			info("-- Versions tab is already displayed --");
		}else if (isElementPresent(ELEMENT_MORE_LINK_WITHOUT_BLOCK)){
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			if (isElementPresent(ELEMENT_VERSIONS_LINK)){
				info("-- Versions tab is already displayed --");
			}
		}
		click(ELEMENT_VERSIONS_LINK);
		if (waitForAndGetElement(ELEMENT_ACTIVATE_VERSION_BUTTON, DEFAULT_TIMEOUT, 0, 2) != null){
			click(ELEMENT_ACTIVATE_VERSION_BUTTON);
			if (waitForAndGetElement(ELEMENT_VERSIONS_LINK,10000,0)!=null){
				info("-- Versions tab is already displayed --");
			}else if (isElementPresent(ELEMENT_MORE_LINK_WITHOUT_BLOCK)){
				click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
				if (isElementPresent(ELEMENT_VERSIONS_LINK)){
					info("-- Versions tab is already displayed --");
				}
			}
			click(ELEMENT_VERSIONS_LINK);
		}
	}

	/**
	 * Restore a version
	 * @param locator
	 * @param version
	 */
	public void restoreVersion(By locator, String version){
		openVersionInfoForm(locator);
		waitForAndGetElement(By.xpath(ELEMENT_RESTORE_VERSION_ICON.replace("${version}", version)));
		click(By.xpath(ELEMENT_RESTORE_VERSION_ICON.replace("${version}", version)));
		waitForElementNotPresent(By.xpath(ELEMENT_RESTORE_VERSION_ICON.replace("${version}", version)));
		button.close();
		Utils.pause(3000);
	}

	/**
	 * Action is availabled on Action bar
	 * @param locator
	 * @return
	 */
	public Boolean isActionsOnActionBarPresent(Object locator){
		WebElement actionicon = waitForAndGetElement(locator, 5000, 0);
		if (actionicon == null){
			WebElement more = waitForAndGetElement(ELEMENT_MORE_LINK_ACTION_BAR, 5000, 0);
			if (more != null){
				info("-- Click more link --");
				click(ELEMENT_MORE_LINK_ACTION_BAR);
				actionicon = waitForAndGetElement(locator, 5000, 0);
				if (actionicon == null)
					return false;
				else
					return true;

			} else {
				info("Do not have action icon in action bar");
				return false;
			}
		}
		else{
			return true;
		}
	}

	/**
	 * Add content Navigation
	 * @param visible
	 * @param path
	 * @param displayOrder
	 * @param clickable
	 * @param forList
	 * @param forDetail
	 * @param verify
	 */
	public void addContentNavigation(boolean visible, String path, String displayOrder, boolean clickable, String forList, String forDetail, boolean verify){
		info("Add Content Navigation");
		if (!visible) check(ELEMENT_VISIBLE_CHECKBOX, 2);
		else uncheck(ELEMENT_VISIBLE_CHECKBOX, 2);
		if(path!=null && path!=""){
			click(ELEMENT_NAVIGATION_SELECT_NODE);
			check(By.xpath(ELEMENT_NAVIGATION_PATH.replace("${path}", path)),2);			
		}
		if(displayOrder!=null && displayOrder!=""){
			WebElement order = waitForAndGetElement(ELEMENT_NAVIGATION_DISPLAY_ORDER);
			order.sendKeys(displayOrder);
		}
		if (clickable) check(ELEMENT_CLICKABLE_CHECKBOX, 2);
		else uncheck(ELEMENT_CLICKABLE_CHECKBOX, 2);
		if(forList!=null && forList!=""){
			click(ELEMENT_NAVIGATION_SELECT_LIST);
			check(By.xpath(ELEMENT_NAVIGATION_LIST_PATH.replace("${path}", forList)),2);	
		}
		if(forDetail!=null && forDetail!=""){
			click(ELEMENT_NAVIGATION_SELECT_DETAIL);
			check(By.xpath(ELEMENT_NAVIGATION_LIST_PATH.replace("${path}", forDetail)),2);	
		}
		if(verify){
			Utils.pause(500);
			button.save();	
		}
		else button.cancel();

	}
}
