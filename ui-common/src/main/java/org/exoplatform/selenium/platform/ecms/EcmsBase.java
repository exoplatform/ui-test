package org.exoplatform.selenium.platform.ecms;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.ecms.contentexplorer.SitesExplorer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.exoplatform.selenium.TestLogger.info;

public class EcmsBase extends ManageAccount {


	public EcmsBase(WebDriver dr, String...plfVersion) {
		super(dr);
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
	}

	protected UserGroupManagement userGroup = new UserGroupManagement(driver);
	protected SitesExplorer se ;
	Button button;
	protected PlatformPermission pPer= new PlatformPermission(driver);
	/*
	 * Portal Acme - http://localhost:8080/portal/acme
	 * */

	public final String ELEMENT_OVERVIEW_LINK = "//i[@class='uiIconFile uiIconExt-overview']";
	public final String ELEMENT_DRAFT_ACME= "//*[text()='${content}']/../..//span[text()='Draft']";
	public final String ELEMENT_ACME_TITLE= "//*[text()='${content}']";
	public final String ELEMENT_PUBLISH_ACME = "//*[text()='${content}']/../..//span[@class='publishText']";
	public final By ELEMENT_ACME_WELCOME_TEXT = By.xpath("//div[contains(text(),'Welcome to Acme')]");
	public final By ELEMENT_ACME_SEARCH_INPUT = By.name("keyword");
	public final String ELEMENT_ACME_SEARCH_RESULT = "//a[text()='${result}']";
	public final By ELEMENT_ACME_SEARCH_BUTTON = By.linkText("Search");  
	public final String ELEMENT_ACME_TAB_CATEGORY = "//*[@class='Tab']//*[text()='${content}']";

	//Sign-in form
	public final By ELEMENT_LOGIN_BUTTON = By.name("signIn");

	//Side bar
	public final By ELEMENT_THUMBNAIL_HIDDEN_NODE = By.xpath("//span[text()='exo:thumbnails']");
	public final By ELEMENT_DMS_STRUCTURE = By.xpath("//span[text()='jcr:content']");
	public final By ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON = By.xpath(".//*[@id='UISideBar']//i[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");
	public final String ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME = "//*[@class='nodeName' and text()='${title}']";
	public final By ELEMENT_SITEEXPLORER_ACTION_DELETE = By.xpath("//*[@class='uiIconEcmsDelete']");

	//Confirm delete box
	public final By ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE= By.xpath("//*[@class='uiAction uiActionBorder']//*[text()='Delete']");


	//UI address bar
	public final String ELEMENT_VIEW_MODE_LINK = "//*[ @data-original-title='${viewName}']";
	public final String ELEMENT_ADMIN_VIEW_MODE_LINK = "//i[@class='uiIconEcmsViewDefault uiIconEcmsViewAdmin uiIconEcmsLightGray']";
	public final By ELEMENT_BACK_PREVIOUS_NODE = By.className("uiIconEcmsGoBack uiIconEcmsLightGray");
	public final By ELEMENT_BACK_PREVIOUS = By.xpath("//i[@class='uiIconEcmsGoBack uiIconEcmsLightGray']");
	public final By ELEMENT_ADDRESS_BAR = By.id("address");

	//New Folder
	public final By ELEMENT_NEW_FOLDER_LINK = By.xpath("//*[@class='actionIcon']//*[@class='uiIconEcmsAddFolder uiIconEcmsLightGray']");
	public final By ELEMENT_FOLDER_POPUP_TITLE = By.xpath("//*[contains(@class, 'popupTitle') and text()='New Folder']");
	public final By ELEMENT_FOLDER_TITLE_TEXTBOX = By.id("titleTextBox");
	public final By ELEMENT_FOLDER_TYPE_OPTION = By.name("customTypeSelectBox");
	public final String ELEMENT_CONTENT_FOLDER_TYPE = "nt:unstructured";
	public final By ELEMENT_CONTENT_FOLDER_TYPE_XPATH = By.xpath("//option[text()='Content Folder']");
	public final String ELEMENT_DOCUMENT_FOLDER_TYPE = "nt:folder";
	public final By ELEMENT_DOCUMENT_FOLDER_TYPE_XPATH = By.xpath("//option[text()='Document Folder']");
	public final String ELEMENT_CSS_FOLDER_TYPE = "exo:cssFolder";
	public final By ELEMENT_CSS_FOLDER_TYPE_XPATH =  By.xpath("//option[text()='CSS Folder']"); 
	public final String ELEMENT_LINK_FOLDER_TYPE = "exo:linkFolder";
	public final By ELEMENT_LINK_FOLDER_TYPE_XPATH = By.xpath("//option[text()='Link Folder']");
	public final String ELEMENT_WEB_CONTENT_FOLDER_TYPE = "exo:webFolder";
	public final By ELEMENT_WEB_CONTENT_FOLDER_TYPE_XPATH = By.xpath("//option[text()='Web Content Folder']");
	public final String ELEMENT_JS_FOLDER_TYPE = "exo:jsFolder";
	public final By ELEMENT_JS_FOLDER_TYPE_XPATH = By.xpath("//option[text()='Javascript Folder']");
	public final String ELEMENT_FOLDER_ICON = "//*[@title='${folderTitle}' and contains(@class, '${folderType}')]";	
	public final String MESSAGE_FOLDER_HINT_CHECKBOX = "Check if you intend to store web contents or custom document types in this folder";

	//Add new Page
	public final By ELEMENT_NEWPAGE_NAME_TEXTBOX = By.id("pageName");	
	public final By ELEMENT_NEWPAGE_SAVE_BUTTON = By.xpath("//a[@title='Finish']");
	public final By ELEMENT_NEWPAGE_LAYOUT_OPTION = By.xpath("//div[@class='DropDownSelectLabel']") ;

	//Page Creation Wizard -> Page Configs
	public final By ELEMENT_NEWPAGE_LAYOUT_COLUMN_PAGE_OPTION = By.linkText("Column Page Configs") ;
	public final By ELEMENT_NEWPAGE_LAYOUT_ROW_PAGE_OPTION = By.linkText("Row Page Configs");
	public final By ELEMENT_NEWPAGE_LAYOUT_TAB_PAGE_OPTION = By.linkText("Tabs Page Config");
	public final By ELEMENT_NEWPAGE_LAYOUT_MIX_PAGE_OPTION = By.linkText("Mix Page Configs");
	public final By ELEMENT_NEWPAGE_LAYOUT_DEFAULT_OPTION = By.linkText("Page Configs");

	public final By ELEMENT_ADD_CONTENT_DETAIL_PORTLET = By.xpath("//div[contains(text(),'Content Detail')]");
	public final By ELEMENT_DROP_TARGET_NO_LAYOUT = By.xpath("//div[@id='UIPage']");
	public final By ELEMENT_DROP_TARGET_HAS_LAYOUT = By.xpath("//*[@class='UIRowContainer EmptyContainer']");
	public final By ELEMENT_ADD_CONTENT_LIST_PORTLET = By.xpath("//div[text()='Content List']");
	public final By ELEMENT_FRAME_CONTAIN_PORTLET = By.xpath("//div[contains(@id,'UIPortlet')]");
	public final By ELEMENT_SELECT_CONTENT_PATH_LINK = By.xpath("//img[@class='AddIcon16x16 SelectFolderPathIcon']");

	public final By ELEMENT_CONTENTS_BY_QUERY_PORTLET = By.xpath("//div[contains(text(),'Content By Query')]");
	public final By ELEMENT_BY_QUERY_TEXTAREA = By.xpath("//textarea[@id='UICLVConfigContentByQueryTextArea']");
	public final By ELEMENT_WORKSPACE_SELECT = By.xpath("//select[@id='UICLVConfigWorkspaceFormSelectBox']");
	public final By ELEMENT_ACME_CATEGORY = By.xpath("//*[@id='ListRecords']/thead/tr[2]/td/a");
	public final By ELEMENT_FLIGHT = By.xpath("//a[@title='Flight']");
	public final By ELEMENT_SELECT_BY_CONTENT_PATH = By.xpath("//input[@id='UICLVConfigDisplayModeFormRadioBoxInput_ManualViewerMode']");
	public final By ELEMENT_BLOCK_LAYOUT = By.xpath("//div[@class='LAYOUT-BLOCK LAYOUT-PORTLET']");
	public final By ELEMENT_PAGE_EDIT_ABORT = By.xpath("//a[@title='Abort']");

	public final By ELEMENT_SELECT_CONTENT_PATH = By.xpath("//a[@title='offices.jpg']");
	public final By ELEMENT_ADD_TARGET = By.xpath("//img[@class='AddIcon16x16 SelectTargetPageIcon']");
	public final By ELEMENT_HEADER_PORTLET = By.id("UICLVConfigHeaderFormStringInput");
	public final By ELEMENT_TEMPLATE_PORTLET = By.id("UICLVConfigDisplayTemplateFormSelectBox");
	public final By ELEMENT_ADVANCE_PORTLET = By.linkText("Advanced");
	public final By ELEMENT_NEW_TARGET_PATH = By.xpath("//div[text()='news']/../../td/a/div[@class='Select16x16Icon']");
	public final By ELEMENT_CONTENT_BYURL_PORTLET = By.xpath("//div[text()='Content by URL']");
	public final By ELEMENT_NEWS_PORTLET = By.xpath("//div[text()='News']/../../../../../..");
	public final By ELEMENT_NEW_EDIT_PORTLET = By.xpath("//div[text()='News']/../a[@class='EditIcon']");
	public final By ELEMENT_HOMEPATH_ROOT = By.xpath("//div[@class='BreadcumbsPortlet']/div[2]/div[1]/a");
	public final By ELEMENT_FOLDER_BROWSER = By.xpath("//div[contains(text(),'Folder Browser')]");

	//-------------News page form---------------------------------------
	public final By ELEMENT_CATEGORY_CONTAINER = By.xpath("//div[text()='Browse by']");
	public final By ELEMENT_CATEGORY_PREFER = By.xpath("//div[text()='Browse by']/../../../*//a[@title='Preferences']");
	public final By ELEMENT_CATEGORY_ACME = By.xpath("//div[text()='Browse by']/../../*//a[text()='acme']");
	public final By ELEMENT_CATEGORY_DEFENCE = By.xpath("//div[text()='Browse by']/../../*//a[text()='Defense']");
	public final By ELEMENT_CATEGORY_VISION = By.xpath("//h5/a[text()='Defense']/../../*//a[text()='Vision']");
	public final By ELEMENT_CATEGORY_VISIBILITY = By.xpath("//h5/a[text()='Defense']/../../*//a[text()='Invisibility']");
	public final By ELEMENT_CATEGORY_HEALING = By.xpath("//h5/a[text()='Defense']/../../*//a[text()='Healing']");
	public final By ELEMENT_CATEGORY_IMMUNITY = By.xpath("//h5/a[text()='Defense']/../../*//a[text()='Immunity']");
	public final By ELEMENT_CATEGORY_MOVEMENT = By.xpath("//div[text()='Browse by']/../*//a[text()='Movement']");
	public final By ELEMENT_CATEGORY_NATURAL = By.xpath("//div[text()='Browse by']/../*//a[text()='Natural Elements']");
	public final By ELEMENT_UPLEVEL = By.xpath("//a[@class='LevelUpArrowIcon']");
	public final By ELEMENT_PCLV_CONTAINER = By.xpath("//div[text()='Top News']");
	public final By ELEMENT_PCLV_PREFER = By.xpath("//div[text()='Top News']/../../../../../*//a[@title='Preferences']");
	public final By ELEMENT_ORDER_BY = By.id("UICLVConfigOrderByFormSelectBox");
	public final By ELEMENT_ASCE_RADIO = By.id("UICLVConfigOrderTypeFormRadioBoxInput_ASC");
	public final By ELEMENT_DESC_RADIO = By.id("UICLVConfigOrderTypeFormRadioBoxInput_DESC");
	//--------------End News page form---------------------------------------------

	//Locator of SetPermission
	public final By ELEMENT_SELECT_USER = By.xpath("//img[@alt='Select User']");
	public final By ELEMENT_SEARCH_TEXTBOX = By.id("QuickSearch");
	public final By ELEMENT_SEARCH_LINK = By.xpath("//a[@class='SearchIcon' and @title='Quick Search']");
	public final By ELEMENT_SEARCH_CHOOSE = By.xpath("//img[@class='SelectPageIcon']");
	public final By ELEMENT_READ_CHECKBOX = By.id("read"); 
	public final By ELEMENT_ADD_NODE_CHECKBOX = By.id("add_node"); 
	public final By ELEMENT_SET_PROPERTY_CHECKBOX = By.id("set_property");
	public final By ELEMENT_REMOVE_CHECKBOX = By.id("remove");

	//Permission Management Form
	public final By ELEMENT_PERMISSION_MANAGEMENT_POPUP = By.id("UIPopupWindow");
	public final String ELEMENT_PERMISSION_MANAGEMENT_TEXT = "Permission Management";
	public final By ELEMENT_PERMISSION_MANAGEMENT_GRID = By.className("permissionInfo");
	public final By ELEMENT_MANAGE_TAGS = By.className("uiIconEcmsFolksonomyManager"); 

	//By.linkText("Manage Tags");
	public final By ELEMENT_TAG_PERMISSION = By.xpath("//*[contains(text(),'Tag Permission Manager')]");

	//DMS Administration - Simple View - Add Category Form
	public final By ELEMENT_BUTTON_ADD_CATEGORY = By.linkText("Add Category");
	public final By ELEMENT_ADD_CATEGORY_FORM = By.xpath("//span[text()='Add Category']");
	public final By ELEMENT_INPUT_CATEGORY_NAME = By.id("name");

	//For symlink
	public final By ELEMENT_ADD_SYMLINK_POPUP = By.xpath("//*[contains(@class, 'popupTitle') and text()='Symlink Manager']");
	public final By ELEMENT_ADD_SYMLINK_CLOSE_WINDOWS = By.xpath("//*[text()='Symlink Manager']/..//*[contains(@class, 'uiIconClose')]");
	public final By ELEMENT_REMOVE_PATH_NODE = By.xpath("//*[@data-original-title='Remove Item']");
	public final By ELEMENT_SYMLINK_PATH_NODE = By.id("pathNode0");
	public final By ELEMENT_SYMLINK_NAME = By.id("symLinkName");
	public final By ELEMENT_SYMLINK_WORKSPACE = By.name("workspaceName");
	public final By ELEMENT_SITE_CONTENT = By.xpath("//div[@title='sites content']");
	public final By ELEMENT_LIVE_DIV = By.xpath("//div[@title='live']");
	public final By ELEMENT_ACTION_BAR_ADD_SYMLINK = By.xpath("//*[@class='actionIcon']//*[contains(@class,'uiIconEcmsAddSymLink')]");
	public final By ELEMENT_CONTEXT_MENU_ADD_SYMLINK = By.xpath("//*[@class='uiContextMenuContainer']//*[@class='uiIconEcmsAddSymLink']");
	public final String ELEMENT_DATA_TITLE = "//*[@data-original-title = '${dataTitle}']";
	public final String ELEMENT_SYMLINK_TITLE = "//*[@data-original-title = '${symlinkTitle}']";

	//public final String ELEMENT_TARGET_NODE = ".//*[@id='UISelectPathPanel']//*[@class='Text' and contains(.,'${node}')]/../..//*[@data-original-title='Select']";

	//public final String ELEMENT_TARGET_NODE = "//*[contains(text(),'${node}')]/../../td/a[@data-original-title='select']";
	public final String ELEMENT_TARGET_NODE = "//div[contains(normalize-space(), '${node}') and @class='Text']/../../td/a[@data-original-title='Select']/i";

	public final String ELEMENT_TARGET_REFERENCE = ".//*[@class='uiGrid table table-hover table-striped']//tr[${index}]//*[@class='uiIconValidate uiIconLightGray']";
	public final String ELEMENT_SELECT_NODE = "//*[@id='UISelectPathPanel']//tr[2]//i[contains(@class,'uiIconValidate uiIconLightGray')]";
	public final String ELEMENT_SELECT_NODE_1 = "//*[@id='UISelectPathPanel']//tr[1]//i[contains(@class,'uiIconValidate uiIconLightGray')]";
	public final String ELEMENT_SYMLINK = "//*[@title='${symlinkTitle}']/i[@class='iconLinkSmall']/../..";
	public final String ELEMENT_SYMLINK_OTHER = "//*[@data-original-title='${name}.lnk']/*[@class='LinkSmall']";
	public final String ELEMENT_SYMLINK_PATH_NODE_TITLE = "//*[@id='UIOneNodePathSelector']//a/i[@title='${node}']";
	public final String ELEMENT_DOCUMENT_NODE_LIST = "//*[@id='UIDocumentNodeList']//*[@data-original-title='${node}']";

	public final By ELEMENT_NO_CATEGORIES_POP_UP = By.xpath(".//*[@class='infoIcon' and contains(.,'There are no categories')]");
	public final By ELEMENT_ACCEPT_NO_CATEGORIES = By.xpath(".//*[@class='infoIcon' and contains(.,'There are no categories')]/../../..//*[contains(text(),'OK')]");
	//Rename Form in Sites Explorer (Right-click -> Rename)
	public final By ELEMENT_INPUT_TITLE_NODE = By.xpath("//input[@id = 'titleField']");
	public final By ELEMENT_INPUT_NAME_NODE = By.xpath("//input[@id = 'nameField']");
	public final By ELEMENT_INPUT_RENAME_NODE = By.xpath("//input[@id = 'renameField']");

	/* Default Data (Document and folder like: acme, Document,....) */
	//Sidebar - Tree node
	public final By ELEMENT_SIDEBAR_ACME = By.xpath("//*[@title='acme']");
	public final By ELEMENT_SIDEBAR_ACME_WEB_CONTENT = By.xpath("//a[@title='web contents']");
	public final By ELEMENT_SIDEBAR_ACME_DOCUMENTS = By.linkText("documents");
	public final By ELEMENT_COLLABORATION_DRIVE_LIVE = By.xpath("//a[@title='Live']");
	public final String ELEMENT_SIDEBAR_NODE_TITLE = "//*[@class='node']/div/div/a/i[@title='${nodeName}']";
	public final By ELEMENT_SITEBAR_INTRANET = By.xpath("//a/span[@class='nodeName' and text()='intranet']");
	public final By ELEMENT_SITEBAR_SHARED = By.xpath("//a/span[@class='nodeName' and text()='shared']");
	public final By ELEMENT_SITEBAR_INTRANET_DOCUMENT = By.xpath("//a/span[@class='nodeName' and text()='documents']");

	//View Area
	public final By ELEMENT_MORE_LINK = By.xpath("//*[@id='uiActionsBarContainer']//*[@style='display: block; ']//*[contains(text(), 'More')]");
	public final By ELEMENT_MORE_LINK_WITHOUT_BLOCK = By.xpath("//*[@id='uiActionsBarContainer']//*[contains(text(), 'More')]/*[@class='uiIconMiniArrowDown uiIconLightGray']");
	public final By ELEMENT_ACTION_BAR_UPLOAD_BTN=By.xpath("//*[@class='actionIcon' and contains(text(),'Upload')]");

	//Collaboration TAB
	public final By ELEMENT_COLLABORATION_TAB = By.linkText("Collaboration");
	public final By ELEMENT_TAG = By.linkText("Tag");
	public final By ELEMENT_TAG_LINK = By.className("uiIconEcmsTaggingDocument");

	public final By ELEMENT_CHANGE_DRIVE = By.id("driveAction");
	public final String ELEMENT_SELECT_DRIVER = ".//*[@id='UIDrivesArea']//*[@data-original-title='${drive}']";
	//System TAB  
	public final By ELEMENT_SYSTEM_TAB = By.xpath("//a[contains(@title,'System')]");

    //Action bar
	public final By ELEMENT_PERMISSION_LINK = By.xpath("//*[@class='actionIcon']//*[contains(@class,'uiIconEcmsViewPermissions')]");
	public final By ELEMENT_EXPORT_LINK = By.xpath("//i[contains(@class,'uiIconEcmsExportNode')]");
	public final By ELEMENT_IMPORT_LINK = By.className("uiIconEcmsImportNode");
	public final By ELEMENT_MANAGE_ACTIONS_LINK=By.xpath("//i[contains(@class,'uiIconEcmsManageActions')]");
	public final By ELEMENT_CONTENT_NAVIGATION_LINK=By.xpath(".//*[@id='ListHideContainer']//i[@class='uiIconEcmsContentNavigation uiIconEcmsLightGray']");

	//Content template
	public final By ELEMENT_EDIT_NODE_CHECKBOX = By.id("set_property");
	public final By ELEMENT_REMOVE_NODE_CHECKBOX = By.id("remove");
	public final By ELEMENT_UPLOAD_NAME = By.name("file");
	public final By ELEMENT_UPLOAD_ILLUSTRATION=By.xpath("//*[@id='illustration']//*[@name='file']");
	public final By ELEMENT_UPLOAD_FRAME_EDIT = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public final By ELEMENT_UPLOAD_REMOVE = By.xpath("//i[@class='uiIconDelete uiIconLightGray']");
	public final By ELEMENT_PIC_FILE_REMOVE = By.xpath("//img[@class='ActionIcon Remove16x16Icon']");
	public final By ELEMENT_SELECT_FILE = By.xpath("//*[@class='btn' and contains(text(),'Select File')]");

	//Explorer
	/* Manage View Page */
	public final By ELEMENT_MANAGE_VIEW = By.xpath("//a[contains(text(),'Manage View')]");
	public final By ELEMENT_MANAGEMENT_VIEW = By.className("uiIconEcmsViewManager");
	public final String ELEMENT_NODE_RIGHT_PANEL ="//*[@class='nodeName' and contains(text(),'${nodeName}')]";

	public final String ELEMENT_EDIT_VIEW = "//*[@data-original-title='${viewName}']/../..//*[@data-original-title='Edit']"; //*[@id='UIViewList']
	public final String ELEMENT_DELETE_VIEW = "//*[@data-original-title='${viewName}']/../..//*[@data-original-title='Delete']";
	public final By ELEMENT_OPEN_IN_MS_OFFICE = By.xpath(".//*[@id='ECMContextMenu']//*[@class='uiIconDownload uiIconLightGray']");
	public final By ELEMENT_CHECK_PDF_NEW_WINDOW = By.xpath(".//*[@id='pageContainer1']//*[@class='textLayer']");

	//Edit View Form 
	public final By ELEMENT_CHECKBOX_VERSION=By.id("manageVersions");
	public final By ELEMENT_BUTTON_BACK=By.linkText("Back");
	public final By ELEMENT_ADDNEW_BUTTON = By.linkText("Add View");
	public final By ELEMENT_TEMPLATE_VIEW_NAME = By.name("viewName");
	public final By ELEMENT_TEMPLATE_VIEW = By.name("template");
	public final By ELEMENT_TAB_NAME = By.id("tabName");
	public final By ELEMENT_TAB_EXPLORER_TEMPLATE = By.xpath("//*[text()='Explorer Templates']");
	public final By ELEMENT_EXPLORER_TEMPLATE_CONTENT = By.id("content");
	public final By ELEMENT_EXPLORER_TEMPLATE_NAME = By.id("name");
	public final By ELEMENT_EXPLORER_TEMPLATE_TYPE = By.name("homeTemplate");
	public final By ELEMENT_ENABLE_VERSION = By.id("enableVersion");
	public final By ELEMENT_EDIT_VIEW_FORM = By.xpath("//*[contains(@class, 'popupTitle') and text()='Edit View']");
	public final By ELEMENT_VERSION_OPTION = By.id("version"); 
	public final By ELEMENT_ECM_TEMPLATE_FORM = By.xpath("//span[@class='PopupTitle' and text()='Add ECM Template']");
	public final By ELEMENT_ADDVIEW_FORM = By.xpath("//span[@class='PopupTitle' and text()='Add New']");

	public final By ELEMENT_COLLABORATION = By.linkText("Collaboration");
	public final By ELEMENT_SYMLINK_CHECKBOX = By.id("addSymLink");
	public final By ELEMENT_BACK_BUTTON = By.linkText("Back");
	public final By ELEMENT_MANAGE_DRIVE_LINK = By.linkText("Manage Drives");

	//View icons
	public final By ELEMENT_LIST_VIEW_ICON = By.xpath("//*[@data-original-title = 'List']");
	public final By ELEMENT_ADMIN_VIEW_ICON = By.xpath("//*[@data-original-title = 'Admin']");
	public final By ELEMENT_ICONS_VIEW = By.xpath("//*[@data-original-title = 'Icons']");

	//Overload thumbnail
	public final By ELEMENT_OVERLOAD_THUMBNAIL = By.xpath("//*[@class = 'actionIcon']//*[contains(@class,'uiIconEcmsOverloadThumbnail')]");
	public final By ELEMENT_CHOOSE_THUMBNAIL_IMAGE = By.xpath("//*[text() = 'Choose Thumbnail Image']");
	public final By ELEMENT_REMOVE_THUMBNAIL = By.xpath("//*[text() = 'Remove Thumbnail']");
	public final String ELEMENT_VERIFY_THUMBNAIL = "//*[text()='${name}']//../../../*[@class='nodeLabel']/*[@class='thumbnailImage']";
	public final String ELEMENT_VERIFY_DATE_NODE = "//*[text()='${namenode}']/../../*[contains(text(),'Created on')or contains(text(),'by')]";
	public final String ELEMENT_VERIFY_CREATEDON_LABEL = "//*[contains(text(),'${content}')]/../..//p[contains(text(), 'Created on')]";

	public final By ELEMENT_ACME_DRIVE = By.linkText("acme-category");
	public final By ELEMENT_SITES_MANAGEMENT_DRIVE = By.xpath("//*[@class = 'driveLabel' and @data-original-title = 'Sites Management']");
	public final By ELEMENT_DMS_ADMIN_DRIVE = By.linkText("DMS Administration");
	public final By ELEMENT_TRASH_DRIVE = By.xpath("//*[@data-original-title = 'Trash']");
	public final By ELEMENT_PERSONAL_DRIVE = By.xpath("//*[@data-original-title = 'Personal Documents']");
	public final By ELEMENT_COLLABORATION_DRIVE= By.xpath("//*[@class = 'driveLabel' and @data-original-title = 'Collaboration']");

	//Site explorer > Upload 
	public final By ELEMENT_UPLOAD_TITLE = By.id("title0");
	public final By ELEMENT_UPLOAD_DESC = By.id("description0");
	public final By ELEMENT_UPLOAD_CREATOR = By.id("creator0");
	public final By ELEMENT_UPLOAD_SOURCE = By.id("source0");
	public final By ELEMENT_UPLOAD_LINK = By.id("MultiUploadInputFiles");
	public final By ELEMENT_UPLOAD_FILE_LINK = By.id("UploadButtonDiv");
	public final String ELEMENT_CANCEL_UPLOAD_FILE = "//*[text()='${title}']/ancestor::div[contains(@class, 'loadContent')]//*[contains(@id, 'cancel')]";
	public final By ELEMENT_UPLOAD_CLOSE_TAB = By.id("MultiUploadClose");
	public final By ELEMENT_UPLOAD_INFORMATION_ICON = By.id("MultiUploadHelp");
	public final By ELEMENT_MESSAGE_FILE_UPLOADED = By.id("MultiUploadFilesUploaded-text");
	public final By ELEMENT_WARNING_UPLOAD_FILE_ICON = By.xpath("//*[contains(@class, 'uiIconWarning')]");
	public final String ELEMENT_UPLOAD_FILE_ACTION = "//*[contains(@class, 'uiIconWarning')]/../..//*[text()='${action}']";
	public final String ELEMENT_HREF_NODE_LINK = "//*[contains(@href, '${nodeName}')]"; 
	public final String ELEMENT_FILE_CLONE = ELEMENT_HREF_NODE_LINK.replace("${nodeName}", "${node}") + "/ancestor::div[contains(@class, 'rowView')]";
	public final String ELEMENT_FILE_CREATED_DATE = ELEMENT_DATA_TITLE.replace("${dataTitle}", "${nodeTitle}") + "/../../*[contains(@class, 'columnDatetime')]";
	public final By ELEMENT_UPLOAD_PROGRESS_BAR = By.xpath(".//*[contains(@class,'progress progress-striped pull-right')]");

	//Site Explorer-->Pdf viewer
	public final String ELEMENT_FILE_CONTENT_FIRST_PAGE=".//*[@id='pageContainer1']//*[contains(text(),'${text}')]";
	public final By ELEMENT_FILE_CONTENT_PAGE =By.xpath(".//*[@id='pdf_viewer_image']/a/img");
	//Edit Tag Form
	public final By ELEMENT_TAG_CLOUD = By.className("uiIconEcmsTagExplorerMini");
	public final By ELEMENT_EDIT_TAGS = By.xpath("//*[@title='Edit Tags']");
	public final By ELEMENT_EDIT_TAGS_OTHER = By.xpath("//*[@data-original-title = 'Edit Tags']");
	public final By ELEMENT_EDIT_PRIVATE_TAG = By.xpath("//*[@title='Edit Private Tags']");
	public final String REMOVE_TAG = "//div[text()='${TagsName}']/../../td/div/img[@title='Remove Tag']";
	public final String MESSAGE_WARNING_AFTER_DELETE_TAG = "Are you sure to delete this tag?";
	public final By ELEMENT_EDIT_TAGS_FORM = By.xpath("//*[contains(@class, 'PopupTitle') and text()='Edit Tag']");
	public final String ELEMENT_EDIT_A_TAG_ICON = "//*[text()='${tagName}']/../..//a[@data-original-title='Edit Tag']";
	public final By ELEMENT_EDIT_A_TAG_FORM = By.id("TagPopup");
	public final By ELEMENT_EDIT_TAG_NAME = By.id("tagName");

	//Tag Manager Form
	public By ELEMENT_ADD_TAGS_BUTTON = By.xpath("//a[contains(text(),'Add Tags')]");
	public By ELEMENT_CLOSE_TAG_FORM = By.xpath("//a[contains(text(),'Close')]");
	public By ELEMENT_TAG_NAME = By.id("names");
	public By ELEMENT_TAG_SCOPE = By.id("tagScopes");

	//Intranet > Documents
	public final String ELEMENT_UI_CHECKBOX = "//*[contains(@data-original-title, '${element}')]/../..//*[@name = 'checkbox']";
	public final String ELEMENT_NODE_ADMIN_VIEW = "//*[contains(@class, 'columnText')]//*[contains(text(), '${nodeName}')]";
	public final String ELEMENT_NODE_ICON_ARROW_RIGHT = "//*[contains(text(), '${nodeName}')]/../..//*[contains(@class, 'columnArrow')]";
	public final String ELEMENT_NODE_NAME_CONSECUTIVE = "//*[@class='uiListGrid']/div[contains(@mousedown, '${node1}')]/..//*[@class='nodeName' and contains(text(), '${node2}')]";
	public final String ELEMENT_FILE_INFORMATION = ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", "${node}") + "/../../*[@class='fileInfoBottom']";
	public final String ELEMENT_FILE_CLONE_CHECKBOX = ELEMENT_HREF_NODE_LINK.replace("${nodeName}", "${node}") + "/ancestor::div[contains(@class, 'rowView')]//*[@name='checkbox']";	
	public final String ELEMENT_NODE_ROW_VIEW = "//*[contains(@data-original-title,'${nodeName}')]/ancestor::div[contains(@class, 'rowView')]"; //HaVTT added
	public final By ELEMENT_VIEW_CHECKBOX_ALL = By.id("UIFileViewCheckBox"); //HaVTT added
	public final String ELEMENT_PERSONAL_DOCUMENT_NODE= "//*[@class='nodeName' and contains(text(), '${content}')]";
	public final String ELEMENT_NODE_ADDRESS = "//input[@id='address' and contains(@value,'/${content}')]";
	public final String PERSONAL_DRIVE_BREADCRUMB = "//div[@class='breadcrumbLink']//a[@data-original-title='Personal Documents']";
	public final By ELEMENT_VIEW_MORE_BUTTON = By.xpath("//div[@class='pull-right MoreButton']");

	//Undo Deleted items
	public final By ELEMENT_UNDO_DELETED_ITEM = By.xpath("//*[@class='uiIconSuccess']/../*[contains(text(), 'Undo')]");
	public final String MESSAGE_ITEM_DELETED_SUCCESSFULLY = "//*[contains(text(), \"\'${title}\' was deleted succesfully.\")]";
	public final String MESSAGE_MULTI_ITEMS_DELETED_SUCCESSFULLY = "//*[contains(text(), '${title} were deleted succesfully.')]";
	public final String MESSAGE_ITEM_RESTORED_SUCCESSFULLY = "//*[contains(text(), \"\'${title}\' was successfully restored.\")]";
	public final String MESSAGE_MULTI_ITEMS_RESTORED_SUCCESSFULLY = "//*[contains(text(), '${title} were succesfully restored.')]";

	/* File management view - Personal document	 */
	public final String ELEMENT_SELECT_CHECKBOX = "//*[@data-original-title='${name}']/../..//input[@type='checkbox']";
	public final String ELEMENT_ARROW_RIGHT = "//*[@data-original-title='${nodeName}']/../..//*[contains(@class,'uiIconArrowRight')]";
	public final String ELEMENT_ARROW_DOWN = "//*[@data-original-title='${nodeName}']/../..//*[contains(@class,'uiIconArrowDown')]";

	//Add comment form
	public final By ELEMENT_ADD_COMMENT_LINK = By.xpath("//a[contains(text(), 'Comment')]");
	public final By ELEMENT_ADD_COMMENT_POPUP = By.xpath("//*[@id='UIPopupWindow']//span[text()='Comment']");
	public final By ELEMENT_ADD_COMMENT_FRAME = By.xpath("//*[@id='cke_contents_comment']/iframe");
	public final By ELEMENT_ADD_COMMENT_FRAME_41 = By.xpath("//*[@id='cke_comment']//iframe"); 
	public final By ELEMENT_SHOW_COMMENT_LINK = By.xpath("//*[text()='Show comments']");
	public final String ELEMENT_HIDE_COMMENT_LINK = "//*[@onclick='eXo.ecm.WCMUtils.showHideComponent(this)']//a[contains(text(),'Hide comments')]";
	public final String ELEMENT_SHOW_COMMENT_CONTENT = "//*[@class='commentBox uiBox']//p[contains(text(), '${comment}')]";
	public final String ELEMENT_SHOW_COMMENT_CONTENT_DECORADE = "//*[@class='commentBox uiBox']//p/u/em/strong[contains(text(), '${comment}')]";
	public final String ELEMENT_EDIT_COMMENT_ICON = "//*[contains(text(), '${comment}')]/..//a[@data-original-title='Edit this comment']";
	public final String ELEMENT_DELETE_COMMENT_ICON = "//*[contains(text(), '${comment}')]/..//a[@data-original-title='Remove this comment']";

	//Vote form
	public final By ELEMENT_VOTE_LINK = By.xpath("//a[contains(text(), 'Vote')]");
	public final By ELEMENT_VOTE_POPUP = By.xpath("//*[@id='UIPopupWindow']//span[text()='Vote Document']");
	public final String ELEMENT_VOTE_RATE = "//*[@id='UIVoteForm']//*[@data-original-title='${rate}']";
	public final By ELEMENT_VOTE_COMPONENT = By.xpath("//*[@class='uiVote clearfix']");
	public final String ELEMENT_VOTE_RATING_INFOR = "//*[@class='voteRatingInfo']//span[text()='${rate}']";

	//Add translation form
	public final By ELEMENT_ADD_TRANSLATION_LINK = By.xpath("//a[contains(text(), 'Add Translation')]");
	public final By ELEMENT_ADD_TRANSLATION_POPUP = By.xpath("//*[@id='UIPopupWindow']//span[text()='Add Translation']");
	public final By ELEMENT_SELECT_DOCUMENT_BUTTON = By.xpath("//*[text()='Select Document']");
	public final By ELEMENT_PARENT_DRIVER = By.xpath("//*[@id='BreadcumbsContainer']/li[2]/a");
	public final String ELEMENT_BREADCUMBSCONTAINER = "//*[@id='BreadcumbsContainer']//a[text()='${fileName}']";

	//Content explorer
	public final By ELEMENT_TEMPLATE_LIST_NAVIGATION = By.xpath(".//*[text()='Navigation']");
	public final By ELEMENT_TEMPLATE_LIST_PAGINATION = By.xpath(".//*[text()='Paginator']");

//	template documents
	public final By ELEMENT_TEMPLATE_DOC_ACCMEDIA = By.xpath("//*[text()='Accessible Media']");
	public final By ELEMENT_TEMPLATE_DOC_ACCMEDIA_NAME = By.xpath("//*[text()='exo:accessibleMedia']");
	public final By ELEMENT_TEMPLATE_DOC_ACCBREAD = By.xpath("//*[text()='Accessible Breadcrumb']");
	public final By ELEMENT_TEMPLATE_DOC_ACCBREAD_NAME = By.xpath("//*[text()='wai:siteBreadcrumb']");
	public final By ELEMENT_TEMPLATE_DOC_ACCNAV = By.xpath("//*[text()='Accessible Navigation']");
	public final By ELEMENT_TEMPLATE_DOC_ACCNAV_NAME = By.xpath("//*[text()='wai:siteNavigationWebContent']");
	public final By ELEMENT_TEMPLATE_DOC_ACCSITESB = By.xpath("//*[text()='Accessible Site Search Box']");
	public final By ELEMENT_TEMPLATE_DOC_ACCSITESB_NAME = By.xpath("//*[text()='wai:siteSearchBox']");
	public final By ELEMENT_TEMPLATE_DOC_ANNOUNC = By.xpath("//*[text()='Announcement']");
	public final By ELEMENT_TEMPLATE_DOC_ANNOUNC_NAME = By.xpath("//*[text()='exo:announcement']");
	public final By ELEMENT_TEMPLATE_DOC_CSSFILE = By.xpath("//*[text()='CSS File']");
	public final By ELEMENT_TEMPLATE_DOC_CSSFILE_NAME = By.xpath("//*[text()='exo:cssFile']");
	public final By ELEMENT_TEMPLATE_DOC_CONTACTUS = By.xpath("//*[text()='Contact Us']");
	public final By ELEMENT_TEMPLATE_DOC_CONTACTUS_NAME = By.xpath("//*[text()='acme:contact_us']");
	public final By ELEMENT_TEMPLATE_DOC_FILE = By.xpath("//*[text()='File']");	
	public final By ELEMENT_TEMPLATE_DOC_FILE_NAME = By.xpath("//*[text()='nt:file']");
	public final By ELEMENT_TEMPLATE_DOC_HTMLFILE = By.xpath("//*[text()='HTML File']");
	public final By ELEMENT_TEMPLATE_DOC_HTMLFILE_NAME = By.xpath("//*[text()='exo:htmlFile']");
	public final By ELEMENT_TEMPLATE_DOC_ILLUSTRATEDWC = By.xpath("//*[text()='Illustrated Web Content']");
	public final By ELEMENT_TEMPLATE_DOC_ILLUSTRATEDWC_NAME = By.xpath("//*[text()='exo:pictureOnHeadWebcontent']");
	public final By ELEMENT_TEMPLATE_DOC_JS = By.xpath("//*[text()='Javascript File']");
	public final By ELEMENT_TEMPLATE_DOC_JS_NAME = By.xpath("//*[text()='exo:jsFile']");
	public final By ELEMENT_TEMPLATE_DOC_PRODUCT = By.xpath("//*[text()='Product']");
	public final By ELEMENT_TEMPLATE_DOC_PRODUCT_NAME = By.xpath("//*[text()='acme:product']");
	public final By ELEMENT_TEMPLATE_DOC_WEBCONTENT = By.xpath("//*[text()='Web Content']");
	public final By ELEMENT_TEMPLATE_DOC_WEBCONTENT_NAME = By.xpath("//*[text()='exo:webContent']");
	public final By ELEMENT_TEMPLATE_DOC_WEBLINK = By.xpath("//*[text()='Web Link']");
	public final By ELEMENT_TEMPLATE_DOC_WEBLINK_NAME = By.xpath("//*[text()='exo:link']");
	
	//template edit
	public final By ELEMENT_TEMPLATE_ACTIONS_ADDMETADATA = By.xpath("//*[text()='Add Metadata Action']");
	public final By ELEMENT_TEMPLATE_ACTIONS_AUTOVERSIONING = By.xpath("//*[text()='Automatic Versioning']");
	public final By ELEMENT_TEMPLATE_ACTIONS_ENABLEVERSIONING = By.xpath("//*[text()='Enable Versioning']");
	public final By ELEMENT_TEMPLATE_ACTIONS_ENABLEVERSIONING_NAME = By.xpath("//*[text()='Enable Versioning']");
	public final By ELEMENT_TEMPLATE_ACTIONS_POPTOMENU = By.xpath("//*[text()='Populate to Menu']");
	public final By ELEMENT_TEMPLATE_ACTIONS_POPTOMENU_NAME = By.xpath("//*[text()='Populate to Menu']");
	public final By ELEMENT_TEMPLATE_ACTIONS_ADDCATACTION = By.xpath("//*[text()='Add Category Action']");
	public final By ELEMENT_TEMPLATE_ACTIONS_ADDCATACTION_NAME = By.xpath("//*[text()='Add Category Action']");
	
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ACCMED = By.xpath("//*[text()='Accessible Media']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ACCBREAD = By.xpath("//*[text()='Accessible Breadcrumb']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ACCNAV = By.xpath("//*[text()='Accessible Navigation']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ACCSITESB = By.xpath("//*[text()='Accessible Site Search Box']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ANNOUNC = By.xpath("//*[text()='Announcement']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSFILE = By.xpath("//*[text()='CSS File']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_CONTACTUS = By.xpath("//*[text()='Contact Us']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_FILE = By.xpath("//*[text()='File']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_HTMLFILE = By.xpath("//*[text()='HTML File']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ILLUSTRATEDWC = By.xpath("//*[text()='Illustrated Web Content']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_JS = By.xpath("//*[text()='Javascript File']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_PRODUCT = By.xpath("//*[text()='Product']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_WEBCONTENT = By.xpath("//*[text()='Web Content']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_WEBLINK = By.xpath("//*[text()='Web Link']/../..//*[@class='uiIconEdit uiIconLightGray']");
	
	
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ADDMETADATA = By.xpath("//*[text()='Add Metadata Action']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_AUTOVERSIONING = By.xpath("//*[text()='Automatic Versioning']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ENABLEVERSIONING = By.xpath("//*[text()='Enable Versioning']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_POPTOMENU = By.xpath("//*[text()='Populate to Menu']/../..//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ADDCATACTION = By.xpath("//*[text()='Add Category Action']/../..//*[@class='uiIconEdit uiIconLightGray']");

	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL = By.xpath("//*[text()='Dialog']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_DIALTEXT = By.xpath("//*[text()='dialog1']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW = By.xpath("//*[text()='View']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEWTEXT = By.xpath("//*[text()='view1']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_CSS = By.xpath("//*[text()='CSS']");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT = By.xpath("//*[contains(text(), 'Empty data')]");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT_ACCMED2 = By.xpath("//*[text()='Stylesheet-rt']/../..//*[contains(text(),'[*]')]");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_CSSTEXT_ACCMED1 = By.xpath("//*[text()='Stylesheet-lt']/../..//*[contains(text(),'[*]')]");

	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_CANCEL = By.xpath("//*[@class='uiForm SkinForm']//*[@class='uiAction uiActionBorder']//*[text()='Cancel']");

	//template list
	public final By ELEMENT_TEMPLATE_LIST = By.xpath(".//*[@class='uiIconEcmsCLVTemplatesManager uiIconEcmsLightGray']");
	public final By ELEMENT_TEMPLATE_LIST_ACMEBHN = By.xpath("//*[text()='Acme Big Hot News']");
	public final By ELEMENT_TEMPLATE_LIST_ACMEBHN_NAME = By.xpath("//*[text()='AcmeBigHotNews.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_ACMEPOWER = By.xpath("//*[text()='Acme Powers']");
	public final By ELEMENT_TEMPLATE_LIST_ACMEPOWER_NAME = By.xpath("//*[text()='AcmePowers.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_BANNER = By.xpath("//*[text()='Accessible Banner']");
	public final By ELEMENT_TEMPLATE_LIST_BANNER_NAME = By.xpath("//*[text()='AccessibleBanner.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_BREADCRUMB = By.xpath("//*[text()='Accessible Breadcrumb']");
	public final By ELEMENT_TEMPLATE_LIST_BREADCRUMB_NAME = By.xpath("//*[text()='AccessibleBreadcrumb.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_SITEMAP = By.xpath("//*[text()='Accessible Sitemap']");
	public final By ELEMENT_TEMPLATE_LIST_SITEMAP_NAME = By.xpath("//*[text()='AccessibleSitemap.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_TOOLBAR = By.xpath("//*[text()='Accessible Toolbar']");
	public final By ELEMENT_TEMPLATE_LIST_TOOLBAR_NAME = By.xpath("//*[text()='AccessibleToolbar.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_NAVIG = By.xpath("//*[text()='Accessible Navigation']");
	public final By ELEMENT_TEMPLATE_LIST_NAVIG_NAME = By.xpath("//*[text()='AccessibleNavigation.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_CATTREE = By.xpath("//*[text()='Acme Powers Category Tree']");
	public final By ELEMENT_TEMPLATE_LIST_CATTREE_NAME = By.xpath("//*[text()='AcmePowersCategoryTree.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_CATLIST = By.xpath("//*[text()='Category List']");
	public final By ELEMENT_TEMPLATE_LIST_CATLIST_NAME = By.xpath("//*[text()='CategoryList.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_NOPAG = By.xpath("//*[text()='No Pagination']");
	public final By ELEMENT_TEMPLATE_LIST_NOPAG_NAME = By.xpath("//*[text()='EmptyPaginator.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_DEFPAG = By.xpath("//*[text()='Default Paginator']");
	public final By ELEMENT_TEMPLATE_LIST_DEFPAG_NAME = By.xpath("//*[text()='DefaultPaginator.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_ANNOUNCMENT = By.xpath("//*[text()='Announcement']");
	public final By ELEMENT_TEMPLATE_LIST_ANNOUNCMENT_NAME = By.xpath("//*[text()='Announcement.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_DOCUMENT = By.xpath("//*[text()='Documents']");
	public final By ELEMENT_TEMPLATE_LIST_DOCUMENT_NAME = By.xpath("//*[text()='Documents.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_ONECOL = By.xpath("//*[text()='One Column']");
	public final By ELEMENT_TEMPLATE_LIST_ONECOL_NAME = By.xpath("//*[text()='OneColumn.gtmpl']");
	public final By ELEMENT_TEMPLATE_LIST_TWOCOL = By.xpath("//*[text()='Two Columns']");
	public final By ELEMENT_TEMPLATE_LIST_TWOCOL_NAME = By.xpath("//*[text()='TwoColumns.gtmpl']");

	public final By ELEMENT_EXPLORER = By.xpath("//*[contains(text(),'Explorer')]//*[@class='uiIconSelected uiIconLightGray pull-right']");
	public final By ELEMENT_EXPLORER_DRIVES = By.xpath(".//*[@class='uiIconEcmsDriveManager uiIconEcmsLightGray']");
	public final By ELEMENT_EXPLORER_DRIVES_ADDDRIVE = By.xpath(".//*[text()='Add Drive']");

	public final By ELEMENT_EXPLORER_DRIVES_ADDDRIVE_NAME = By.xpath(".//*[@id='name']");
	public final By ELEMENT_EXPLORER_DRIVES_ADDDRIVE_WORKSPACE = By.xpath(".//*[@class='uiSelectbox']");
	public final By ELEMENT_EXPLORER_DRIVES_ADDDRIVE_PERMISSION = By.xpath(".//*[@id='permissions']");
	public final By ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW = By.xpath(".//*[text()='Apply Views']");
	public final By ELEMENT_EXPLORER_DRIVES_ADDDRIVE_TABAPPLYVIEW_ADMINBOX = By.xpath(".//*[@id='Admin' and @class='checkbox']");
	public final By ELEMENT_EXPLORER_DRIVES_ADDDRIVE_SAVE = By.xpath("//*[text()='Save']");

	public final By ELEMENT_SE_SHOWDRIVES = By.xpath(".//*[@class='uiIconEcmsGeneralDrive uiIconEcms16x16DriveManagedSites']");
	public final String ELEMENT_SE_SHOWDRIVES_DRIVE = ".//*[@data-original-title='${name}']";

	public final By ELEMENT_SE_DRIVES_RIGHTARROW = By.xpath("//*[text()='exo:ecm']/../../..//*[@class='uiIconArrowRight']");
	public final By ELEMENT_SE_DRIVES_RIGHTARROW_VIEWS = By.xpath("//*[text()='views']/../../..//*[@class='uiIconArrowRight uiIconLightGray']");
	public final By ELEMENT_SE_DRIVES_RIGHTARROW_TEMPLATES = By.xpath("//*[@style='padding-left:80px']/..//*[text()='templates']");
	public final By ELEMENT_SE_DRIVES_RIGHTARROW_CONTENTLISTVIEWERS = By.xpath("//*[text()='content-list-viewer']/../../..//*[@class='uiIconArrowRight']");
	public final By ELEMENT_SE_DRIVES_RIGHTARROW_LIST = By.xpath("//*[text()='list']/../../..//*[@class='uiIconArrowRight']");
	public final By ELEMENT_SE_DRIVES_PATH = By.xpath(".//*[@id='address']");

	public final By ELEMENT_SELECT_DOCUMENT_POPUP = By.xpath("//*[@id='UIPopupSymLink']//span[text()='Select Document']");
	public final String ELEMENT_TRANSLATION_IN_RELATION_TAB = "//*[@class='uiViewRelationList']//a[text()='fr (${fileName})']";
	public final String ELEMENT_REF_IN_RELATION_TAB = "//*[@class='uiViewRelationList']//a[text()='${fileName}']";

	//Acme site > Overview page
	public final By ELEMENT_OVERVIEW_PAGE = By.xpath("//*[@class = 'uiIconFile uiIconExt-overview']");
	public final By ELEMENT_RSS_ICON = By.xpath("//*[@data-original-title='RSS Feed']");
	public final String ELEMENT_CLV_TITLE = "//*[@class='Title' and contains(text(), '${title}')]";
	public final String ELEMENT_CLV_PUBLISH_DATE = ELEMENT_CLV_TITLE + "/../div[contains(text(), '${date}')]";

	//Content Administration
	//Advanced tab
	public final By ELEMENT_ADVANCED_FUNCTION = By.xpath(".//*[text()='Advanced']//*[@class='uiIconSelected uiIconLightGray pull-right']");
	public final By ELEMENT_ACTIONS_FUNCTION = By.xpath(".//*[@class='uiIconEcmsActionManager uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONS_TAB=By.xpath(".//*[contains(@href,'#tab-UIActionsTemplateContainer')]");
	public final By ELEMENT_ACTIONS_TEMPLATE_LIST=By.xpath(".//*[@id='UIActionTypeList']");
	public final By ELEMENT_ACTIONS_TEMPLATE_DOCUMENT_ACTIONS_LIST=By.xpath(".//*[@id='UIActionsTemplateList']");
	public final By ELEMENT_ACTIONS_LIST_ADD_METADATA=By.xpath(".//*[@id='UIActionTypeList']//*[contains(@data-original-title,'Add Metadata')]");
	
	//Document tab
	public final By ELEMENT_ACTIONS_TEMPLATE_DOCUMENT_DOCUMENT_LIST=By.xpath(".//*[@id='UITemplateList']");
	
	//List tab
	public final By ELEMENT_ACTIONS_TEMPLATE_LIST_LIST=By.xpath(".//*[@id='UICLVTemplateList']");
	
	//Explorer tab-->Drives
	public final By ELEMENT_EXPLORER_DRIVER_WORKSPACE_DMS_SYSTEM = By.xpath("//*[@value='dms-system']");
	
	//Content explorer
	public final By ELEMENT_TEMPLATE = By.xpath(".//*[text()='Templates']//*[@class='uiIconSelected uiIconLightGray pull-right']");
	public final By ELEMENT_TEMPLATE_DOCUMENT = By.xpath(".//*[@class='uiIconEcmsTemplatesManager uiIconEcmsLightGray']");
	public final By ELEMENT_TEMPLATE_DOCUMENT_DOC = By.xpath("//*[@id='myTab']//*[text()='Documents']");
	public final By ELEMENT_TEMPLATE_DOCUMENT_ACTIONS = By.xpath("//*[text()='Actions']");
	
	
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION_2=By.xpath("//*[text()='admin_dialog']/../..//*[contains(text(),'[*:/platform/administrators]')]");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_DIAL_PERMISSION=By.xpath("//*[text()='dialog1']/../..//*[contains(text(),'[*]')]");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_VIEW_PERMISSION=By.xpath("//*[text()='view1']/../..//*[contains(text(),'[*]')]");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ACC_DIAL_PERMISSION=By.xpath("//*[text()='dialog1']/../..//*[contains(text(),'[webdesigner:/platform/web-contributors]')]");
	public final By ELEMENT_TEMPLATE_ACTIONS_EDIT_ANNOUN_DIAL_PERMISSION=By.xpath("//*[text()='dialog1']/../..//*[contains(text(),'[*:/platform/web-contributors]')]");
	
	
	public final By ELEMENT_ACTIONS_ADDMETADATA = By.xpath(".//*[@id='UIActionTypeList']//*[text()='Add Metadata']");
	public final By ELEMENT_ACTIONS_ADDTOFAVORITES = By.xpath(".//*[@id='UIActionTypeList']//*[text()='Add To Favorites']");
	public final By ELEMENT_ACTIONS_AUTOVERSIONING = By.xpath(".//*[@id='UIActionTypeList']//*[text()='Auto Versioning']");
	public final By ELEMENT_ACTIONS_ENABLEVERSIONING = By.xpath(".//*[@id='UIActionTypeList']//*[text()='Enable Versioning']");
	public final By ELEMENT_ACTIONS_POPULATETOMENU = By.xpath(".//*[@id='UIActionTypeList']//*[text()='Populate To Menu']");
	public final By ELEMENT_ACTIONS_TRASHDOC = By.xpath(".//*[@id='UIActionTypeList']//*[text()='Trash Document']");
	public final By ELEMENT_ACTIONS_ADDTOCAT = By.xpath(".//*[@id='UIActionTypeList']//*[text()='Add To Category']");
	
	public final By ELEMENT_ACTIONS_DRIVE = By.xpath(".//*[@id='driveAction']");
	public final By ELEMENT_ACTIONS_DRIVE_SITEXPLORER = By.xpath(".//*[@class='uiIconEcms24x24DriveGeneral uiIconEcms24x24DriveManagedSites uiIconEcms24x24LightGray driveIcon']");
	public final By ELEMENT_ACTIONS_DRIVE_COLLABORATION=By.xpath(".//*[contains(@class,'uiIconEcms24x24DriveCollaboration')]");
	public final By ELEMENT_ACTIONS_DRIVE_TRASH=By.xpath(".//*[contains(@class,'uiIconEcms24x24DriveTrash')]");
	public final By ELEMENT_ACTIONS_MORE = By.xpath(".//*[@id='uiActionsBarContainer']//*[text()='More ']//*[@class='uiIconMiniArrowDown uiIconLightGray']");
	public final By ELEMENT_ACTIONS_MORE_ACTIONS = By.xpath(".//*[contains(@class,'uiIconEcmsManageActions')]");
	public final By ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION = By.xpath(".//*[text()='Add Action']");
	public final By ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_METADATA = By.xpath("//*[@selected='selected' and text()='exo:addMetadataAction']/..");
	public final By ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_FAVORITES = By.xpath(".//*[@id='UIActionList']//*[contains(text(),'[exo:addToFavoriteAction]')]");
	public final By ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_AUTOVERSIONING = By.xpath("//*[contains(@value,'exo:autoVersioning')]/..");
	public final By ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_VERSIONING = By.xpath("//*[contains(@value,'exo:enableVersioning')]/..");
	public final By ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_POPULATETOMENU= By.xpath("//*[contains(@value,'exo:populateToMenu')]/..");
	public final By ELEMENT_ACTIONS_MORE_ACTIONS_TRASHDOCUMENT = By.xpath(".//*[@id='UIActionList']//*[contains(text(),'[exo:trashFolderAction]')]");
	public final By ELEMENT_ACTIONS_MORE_ACTIONS_ADDACTION_TAXONOMYACTION = By.xpath("//*[contains(@value,'exo:taxonomyAction')]/..");

	public final String ELEMENT_SITE_EXPLORER_NODE_PATH1="//*[@id='UITreeExplorer']//*[@class='nodeName' and text()='${name}']";
	public final String ELEMENT_SITE_EXPLORER_NODE_PATH2="//*[@id='UITreeExplorer']//*[contains(@data-original-title,'${name}')]";
	public final String ELEMENT_SITE_EXPLORER_NODE_PATH3="//*[@title='${name}']";
	public final String ELEMENT_SITE_EXPLORER_NODE_PATH4="//span[contains(text(),'${name}')]";
	public final String ELEMENT_SITE_EXPLORER_RIGHT_PANEL_NODE_PATH1="//*[@id='UIDocumentNodeList']//*[@class='nodeName' and text()='${name}']";

	public final By ELEMENT_SE_SEARCH = By.xpath(".//*[@class='uiIconEcmsSavedSearchesMini uiIconEcmsLightGray']");
	public final By ELEMENT_SE_SEARCH_MYDOC = By.xpath("//*[text()='My Documents']");
	public final By ELEMENT_SE_ADVANCEDSEARCH = By.xpath("//*[@data-original-title='Advanced Search']//*[@class='uiIconSearch uiIconLightGray']");
	public final By ELEMENT_SE_SAVEDSEARCH = By.xpath("//*[text()='Saved Query']");
	public final By ELEMENT_SE_SAVEDSEARCH_TYPEXPATH = By.xpath("//*[text()='xpath']");
	public final String ELEMENT_SE_SAVEDSEARCH_USER = "//*[text()='${user}']";
	
	//content admin
	public final By ELEMENT_TEMPLATE_DOCS_RESULTSPERPAGE = By.xpath(".//*[@class='selectbox pull-left']");
	
	public final By ELEMENT_CA_REPOSITORY = By.xpath("//*[text()='Repository']");
	public final By ELEMENT_CA_EXPLORER = By.xpath("//*[text()='Explorer']");
	public final By ELEMENT_CA_EXPLORER_DRIVES = By.xpath(".//*[@class='uiIconEcmsDriveManager uiIconEcmsLightGray']");
	public final By ELEMENT_CA_EXPLORER_VIEW = By.xpath(".//*[@class='uiIconEcmsViewManager uiIconEcmsLightGray']");
	public final By ELEMENT_CA_EXPLORER_VIEW_EDIT_ADMIN = By.xpath(".//*[text()='Admin']/../..//*[@class='uiIconEditInfo uiIconLightGray']");
	public final By ELEMENT_CA_EXPLORER_VIEW_EDIT_ADMIN_ACTION = By.xpath("//*[@id='myTab']//*[text()='Action']");
	public final By ELEMENT_CA_EXPLORER_VIEW_EDIT_ADMIN_ACTION_EDIT = By.xpath(".//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_CA_EXPLORER_VIEW_EDIT_ADMIN_ACTION_EDIT_COMMENT = By.xpath(".//*[@id='comment']");
	public final By ELEMENT_CA_EXPLORER_DRIVES_EDIT_MGDSITES = By.xpath(".//*[text()='Managed Sites']/../..//*[@class='uiIconEditInfo uiIconLightGray']");
	public final By ELEMENT_CA_EXPLORER_DRIVES_EDIT_MGDSITES_APPLYVIEWS = By.xpath("//*[text()='Apply Views']");
	public final By ELEMENT_CA_EXPLORER_DRIVES_EDIT_MGDSITES_APPLYVIEWS_ADMIN = By.xpath(".//*[@id='Admin']");
	public final By ELEMENT_SE_MORE_PROPERTIES = By.xpath(".//*[@class='uiIconEcmsViewProperties uiIconEcmsLightGray']");
	public final By ELEMENT_SE_MORE_PROPERTIES_ADDPROPERTY = By.xpath(".//*[text()='Add New Property']");
	public final By ELEMENT_SE_MORE_PROPERTIES_ADDPROPERTY_NAMESPACE = By.xpath(".//*[@id='UIPropertyForm']//*[@for='namespace']/..//*[@class='uiSelectbox']");
	public final By ELEMENT_CA_REPOSITORY_NAMESPACES = By.xpath(".//*[@class='uiIconEcmsNamespaceManager uiIconEcmsLightGray']");
	public final By ELEMENT_CA_REPOSITORY_NAMESPACES_EXO = By.xpath("//*[text()='exo']");
	public final By ELEMENT_CA_REPOSITORY_NAMESPACES_WAI = By.xpath("//*[text()='wai']");
	public final By ELEMENT_CA_REPOSITORY_NAMESPACES_ACME = By.xpath("//*[text()='acme']");
	public final By ELEMENT_CA_REPOSITORY_NAMESPACES_EXO_URL = By.xpath("//*[text()='http://www.exoplatform.com/jcr/exo/1.0']");
	public final By ELEMENT_CA_REPOSITORY_NAMESPACES_WAI_URL = By.xpath("//*[text()='http://www.exoplatform.com/ecm/accessibility/1.0']");
	public final By ELEMENT_CA_REPOSITORY_NAMESPACES_ACME_URL = By.xpath("//*[text()='http://www.exoplatform.com/plf/acme/4.0/']");
	
	public final By ELEMENT_SE_MORE_PROPERTIES_ADDPROPERTY_NAMESPACE_EXO = By.xpath("//*[@value='exo']");
	public final By ELEMENT_SE_MORE_PROPERTIES_ADDPROPERTY_NAMESPACE_WAI = By.xpath("//*[@value='wai']");
	public final By ELEMENT_SE_MORE_PROPERTIES_ADDPROPERTY_NAMESPACE_ACME = By.xpath("//*[@value='acme']");	
	/**
	 * Acme sites > Go to Overview page
	 */
	public void goToOverviewPage(){
		info("-- Go to Overview page--");
		Utils.pause(500);
		if (waitForAndGetElement(ELEMENT_OVERVIEW_PAGE, 3000, 0) != null){
			click(ELEMENT_OVERVIEW_PAGE);
		}else {
			driver.get(DEFAULT_PLF_URL + "/acme/overview");
		}
		Utils.pause(1000);
	}

	/**
	 * go to a node
	 * input: path: path of a node, split by  "/" character 
	 * @param locator
	 * @param params
	 */
	public void goToNode(Object locator, Object...params)
	{
		info("Go to Node");
		Boolean nodeAdminView = (Boolean) (params.length > 0 ? params[0]: false);
		if (nodeAdminView && (locator instanceof String)){
			String[] nodes = ((String) locator).split("/");
			for (String node: nodes)
			{
				rightClickOnElement(ELEMENT_NODE_ADMIN_VIEW.replace("${nodeName}", node));
			}
		}else{
			if (locator instanceof By){
				click(locator);
			}else if (locator instanceof String){
				String[] nodes = ((String) locator).split("/");
				for (String node: nodes)
				{
					if (waitForAndGetElement(ELEMENT_SITE_EXPLORER_NODE_PATH1.replace("${name}", node), 3000, 0) != null)
						click(ELEMENT_SITE_EXPLORER_NODE_PATH1.replace("${name}", node));
					else if (waitForAndGetElement(ELEMENT_SITE_EXPLORER_NODE_PATH2.replace("${name}", node), 3000, 0) != null)
						click(ELEMENT_SITE_EXPLORER_NODE_PATH2.replace("${name}", node));
					else if (waitForAndGetElement(ELEMENT_SITE_EXPLORER_NODE_PATH3.replace("${name}", node), 3000, 0) != null)
						click(ELEMENT_SITE_EXPLORER_NODE_PATH3.replace("${name}", node));
					else
						click(ELEMENT_SITE_EXPLORER_NODE_PATH4.replace("${name}", node));
					Utils.pause(500);
				}
			}
		}	
		Utils.pause(1000);
	}

	/**
	 * function enable edit mode
	 * @param enable
	 */
	public void enableEditMode(boolean enable){
		Utils.pause(1000);
		mouseOverAndClick(ELEMENT_MENU_EDIT_LINK);
		if (waitForAndGetElement(ELEMENT_MENU_EDIT_CONTENT, 5000, 0) == null){
			mouseOver(ELEMENT_MENU_EDIT_LINK, true);
			Utils.pause(1000);
		}
		if ((enable == true && isElementPresent(ELEMENT_MENU_EDIT_CONTENT) == true) || 
				(enable == false && isElementPresent(ELEMENT_MENU_EDIT_CONTENT) == true)){
			info("Change edit mode");
			click(ELEMENT_MENU_EDIT_CONTENT, 2); 
		}
		Utils.pause(1000);
	}

	/**
	 * Up one level
	 */
	public void clickUpLevel(){
		By ELEMENT_UP_LEVEL_ICON = By.className("uiIconUpLevel");
		By ELEMENT_UP_LEVEL_ICON_0 = By.xpath("//*[contains(@class, 'uiIconUpLevel')]");
		if (waitForAndGetElement(ELEMENT_UP_LEVEL_ICON, 3000, 0) != null){
			click(ELEMENT_UP_LEVEL_ICON);
		}else {
			click(ELEMENT_UP_LEVEL_ICON_0);
		}
		Utils.pause(500);
	}

	/**
	 * Function to select user to set permission on permission management popup
	 * @param user
	 */
	public void selectUser(String user){
		By ELEMENT_USER = By.xpath("//*[text() = '"+user+"']/../../td//*[@class='uiIconPlus uiIconLightGray']");
		String selectUser1=".//*[@id='UIPermissionForm']//*[@class='uiIconSelectUser uiIconLightGray']";
		String selectUser2=".//*[@title = 'Select User']";
		String selectUser3=".//*[@data-original-title = 'Select User']";
		info("Set permission for user "+ user);
		if (waitForAndGetElement(selectUser1, 3000,0)!=null){
			click(selectUser1);
		}else if (waitForAndGetElement(selectUser2, 3000,0)!=null){
			click(selectUser2);
		}
		else if (waitForAndGetElement(selectUser3, 3000,0)!=null){
			click(selectUser3);
		}
		type(pPer.ELEMENT_SEARCH_USER_INPUT, user, true);
		click(pPer.ELEMENT_QUICK_SEARCH_BUTTON);
		click(ELEMENT_USER);
		Utils.pause(1000);
	}

	/**
	 * Select Membership
	 * @param groupPath: Group string is separate by slash, for example platform/web-contributors
	 * @param membership: Membership 
	 * @param anchor: link icon to open select memebership form
	 */
	public void selectMembership(String groupPath, String membership, String anchor){	
		if (waitForAndGetElement(By.xpath("//*[contains(@data-original-title,'" + anchor + "')]"),5000,0)!=null){
			click(By.xpath("//*[contains(@data-original-title,'" + anchor + "')]"));
		}else if (waitForAndGetElement(By.xpath("//*[contains(@title,'" + anchor + "')]"),5000,0)!=null){
			click(By.xpath("//*[contains(@title,'" + anchor + "')]")); 
		}else if (waitForAndGetElement(By.xpath("//*[@class='uiIconAddPermission uiIconLightGray']"),5000,0)!=null){
			click(By.xpath("//*[@class='uiIconAddPermission uiIconLightGray']"));
		}

		userGroup.selectGroup(groupPath, true);
		click(By.linkText(membership));
		Utils.pause(1000);
	}

	/**
	 * Select Everyone
	 * @author phuongdt
	 * @date	12/09/2013
	 */
	public void selectEveryone(){
		if (isElementPresent(By.xpath("//*[@data-original-title = 'Select Everyone']"))){
			click(By.xpath("//*[@data-original-title = 'Select Everyone']"));
		}else if (isElementPresent(By.xpath("//*[@title = 'Select Everyone']"))){
			click(By.xpath("//*[@title = 'Select Everyone']"));
		}
		Utils.pause(1000);
	}

	/**
	 * @Modified by PhuongDT
	 * @Modified Date: 05/09/2013
	 * @param link
	 */
	//Upload file Thumbnail
	public void uploadThumbnail(String link){
		button = new Button(driver);
		info("-- Uploading a thumbnail image --");
		if (isTextNotPresent("Overload Thumbnail")){
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		click(ELEMENT_OVERLOAD_THUMBNAIL);
		Utils.pause(1000);

		WebElement upload = waitForAndGetElement(ELEMENT_UPLOAD_NAME, DEFAULT_TIMEOUT, 1, 2);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; " +
				"arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", upload);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible'", upload);
		upload.sendKeys(Utils.getAbsoluteFilePath(link));
		Utils.pause(3000);
		waitForAndGetElement(ELEMENT_CHOOSE_THUMBNAIL_IMAGE);
		info("Upload file " + Utils.getAbsoluteFilePath(link));
		switchToParentWindow();
		click(button.ELEMENT_SAVE_BUTTON);
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);
		Utils.pause(1000);
	}

	/**
	 * upload file
	 * @param link
	 * @param params
	 */
	public void uploadFile(String link, Object...params){
		Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
		if (waitForAndGetElement(ELEMENT_ACTION_BAR_UPLOAD_BTN,500,2,0)==null){
			info("click on More link");
			click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		}
		((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility = 'block'; arguments[0].style.height = '1px'; " +
				"arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", waitForAndGetElement(ELEMENT_UPLOAD_LINK, DEFAULT_TIMEOUT, 1, 2));

		Utils.pause(10000);
		info(link);
		driver.findElement(ELEMENT_UPLOAD_LINK).sendKeys(Utils.getAbsoluteFilePath(link));
		info("Upload file " + Utils.getAbsoluteFilePath(link));

		//switchToParentWindow();
		/*info("upload progress bar is hideded");
		waitForElementNotPresent(ELEMENT_UPLOAD_PROGRESS_BAR);*/

		if (verify){
			String links[] = link.split("/");
			int length = links.length;
			Utils.pause(2000);
			waitForAndGetElement(By.xpath("//*[contains(text(),'" + links[length-1]+ "')]"),2000,1);
		}

		info("Upload file successfully");
		Utils.pause(2000);
	}

	/**
	 * Edit an uploaded file
	 * @param name
	 * @param uploadFile
	 * @param title
	 * @param desc
	 * @param creator
	 * @param source
	 */
	public void editUploadedFile(String name, String uploadFile, String title, String desc, String creator, String source)
	{
		button = new Button(driver);
		//goToEditDocument(name);
		if (uploadFile != ""){
			click(ELEMENT_UPLOAD_REMOVE);
			click(ELEMENT_SELECT_FILE);
			uploadFileUsingRobot(uploadFile);			
			/*driver.switchTo().frame(waitForAndGetElement(ELEMENT_UPLOAD_FRAME_EDIT));
			type(ELEMENT_UPLOAD_NAME, Utils.getAbsoluteFilePath(uploadFile), false);
			switchToParentWindow();*/
		}
		if (title != ""){
			type(ELEMENT_UPLOAD_TITLE,title, true);
		}
		if (desc != ""){
			type(ELEMENT_UPLOAD_DESC, desc, true);
		}
		if (creator != ""){
			type(ELEMENT_UPLOAD_CREATOR, creator, true);	
		}
		if (source != ""){
			type(ELEMENT_UPLOAD_SOURCE, source, true);
		}
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * upload many file
	 * @param file
	 */
	public void uploadMultiFileSerial(String...file){

		if (file.length > 0){
			if (waitForAndGetElement(ELEMENT_ACTION_BAR_UPLOAD_BTN,5000,0,2)==null){
				info("click on More link");
				click(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			}
			((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility = 'block'; arguments[0].style.height = '1px'; " +
					"arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", waitForAndGetElement(ELEMENT_UPLOAD_LINK, DEFAULT_TIMEOUT, 1, 2));

			for (int i = 0; i < file.length; i ++){
				type(ELEMENT_UPLOAD_LINK, Utils.getAbsoluteFilePath("TestData/" + file[i]), false);
			}
			switchToParentWindow();
			String links[] = file[0].split("/");
			int length = links.length;
			waitForAndGetElement(By.xpath("//*[contains(text(),'" + links[length-1]+ "')]"));
			waitForElementNotPresent(By.xpath("//*[@class='loaddingPercent pull-right']"));
		}
	}


	/**
	 * Function to select home path
	 * @param homePath
	 */
	public void selectHomePathForCategoryTree(String homePath){
		String[] temp;
		/* delimiter */
		String delimiter = "/";

		temp = homePath.split(delimiter);
		for(int i =0; i < temp.length - 1 ; i++){
			info("Go to "+temp[i]);
			click(By.xpath("//*[@id='UIOneNodePathSelector']//a/i[@title='" + temp[i] + "']"));
			Utils.pause(1000);
		}
		By element_select1 = By.xpath("//*[contains(text(),'"+ temp[temp.length - 1] +"')]/../../td/a[@title='select']");
		By element_select2 = By.xpath(ELEMENT_TARGET_NODE.replace("${node}", temp[temp.length - 1]));
		if (waitForAndGetElement(element_select1, 5000, 0) != null){
			info("youy : "+element_select1);
			click(element_select1);
		}else if (waitForAndGetElement(element_select2, 5000, 0) != null){
			info("youy 2: "+element_select2);
			click(element_select2);

		}else if (waitForAndGetElement(element_select1, 5000, 0) != null){
			info("youy 3: "+element_select1);
			click(element_select1);
		}/*else if (waitForAndGetElement(element_select1, 5000, 0) != null){
			click(element_select1);
		}*/
		Utils.pause(500);
	}
	/**
	 * Select multiHome path for category tree
	 * @param homePath
	 * @param index
	 */
	public void selectMultiHomePathForCategoryTree(String homePath, String index){
		String[] temp;
		/* delimiter */
		String delimiter = "/";

		temp = homePath.split(delimiter);
		info(temp[0]);
		System.out.println("Temp[0] is: " + temp[0]);

		if(temp[0] != ""){
			for(int i =0; i < temp.length - 1 ; i++){
				info("Go to "+temp[i]);
				click(By.xpath("//*[@id='UIOneNodePathSelector']//a/i[@title='" + temp[i] + "']"));
				Utils.pause(100);
			}
		}
		By element_select1 = By.xpath(ELEMENT_TARGET_REFERENCE.replace("${index}", index));
		By element_select2 = By.xpath(ELEMENT_TARGET_NODE.replace("${node}", temp[temp.length - 1]));
		if (waitForAndGetElement(element_select1, 5000, 0) != null){
			click(element_select1);
		}else if (waitForAndGetElement(element_select2, 5000, 0) != null){
			click(element_select2);
		}
		Utils.pause(500);
	}

	/**
	 * Function to select check-box list using id of check-box
	 * @param viewList
	 */
	public void selectCheckBoxList(String viewList){
		String[] temp = viewList.split("/");
		if (temp.length != 0){
			for (int i=0; i < temp.length ; i++ ){
				if (waitForAndGetElement(By.id(temp[i]), DEFAULT_TIMEOUT, 0, 2) != null){
					check(By.id(temp[i]), 2);
					info("Select checkbox with id " + temp[i]);
				}else{
					info("Can not found checkbox with id " + temp[i]);
				}
			}
		}else{
			info("Input checkbox list wrong");
		}
	}

	/**
	 * Function to uncheck check-box list using id of check-box
	 * @param viewList
	 */
	public void unSelectCheckBoxList(String viewList){
		String[] temp = viewList.split("/");
		if (temp.length != 0){
			for (int i=0; i < temp.length ; i++ ){
				if (waitForAndGetElement(By.id(temp[i]), DEFAULT_TIMEOUT, 0, 2) != null){
					uncheck(By.id(temp[i]), 2);
					info("Select checkbox with id " + temp[i]);
				}else{
					info("Can not found checkbox with id " + temp[i]);
				}
			}
		}else{
			info("Input checkbox list wrong");
		}
	}


	/**
	 * View a file in ms Office
	 * @param node
	 */
	public void viewInMsOffice(String node){
		rightClickOnElement(ELEMENT_NODE_RIGHT_PANEL.replace("${nodeName}", node));
		click(ELEMENT_OPEN_IN_MS_OFFICE);
		Utils.pause(50000);
		switchToNewWindow();
		waitForAndGetElement(ELEMENT_CHECK_PDF_NEW_WINDOW);
		switchToParentWindow();
	}


	/**
	 *  Goto upload in Content/Site Explorer
	 *  Mouse over on the button "More"
	 *  Click on "Upload"
	 */
	public void goToUpload(){
		info("--Goto Upload in Content/Site Explorer");
		if(waitForAndGetElement(ELEMENT_MORE_LINK_WITHOUT_BLOCK, DEFAULT_TIMEOUT,0)==null){
			mouseOverAndClick(ELEMENT_UPLOAD_LINK_XPATH);
		}
		else{
			mouseOverAndClick(ELEMENT_MORE_LINK_WITHOUT_BLOCK);
			if(waitForAndGetElement(ELEMENT_UPLOAD_LINK_XPATH, DEFAULT_TIMEOUT,0)==null){
				info("cannot find Upload button");
			}
			else{
				mouseOverAndClick(ELEMENT_UPLOAD_LINK_XPATH);
			}

		}
	}

	/**
	 * Delete data by title
	 * @param title
	 */
	public void deleteData(String title) {
		info("Click on File Explorer icon");
		click(ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON);
		Utils.pause(2000);
		info("Right click on nodename");
		rightClickOnElement(By.xpath((ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME).replace("${title}", title)));
		info("Click on Delete link");
		click(ELEMENT_SITEEXPLORER_ACTION_DELETE);
		info("Click on Delete button on Confirm popup");
		click(ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE);
		info("Verify that the node is deleted");
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME.replace("${title}", title));
		info("the node is deleted successfully");
	}
}
