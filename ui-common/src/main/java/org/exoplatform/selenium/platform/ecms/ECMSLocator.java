package org.exoplatform.selenium.platform.ecms;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class ECMSLocator extends PlatformBase{

	/*******************************************CREATE NEW DOCUMENT**************************************************************************************/

	// template form
	public final By ELEMENT_ADDDOCUMENT_FILE = By.xpath("//*[@class='uiIcon64x64Templatent_file']");
	public final By ELEMENT_ADDDOCUMENT_WEBCONTENT = By.xpath("//*[@class='uiIcon64x64Templateexo_webContent']");
    public final By ELEMENT_ADDDOCUMENT_ACCESSIBLE_MEDIA = By.xpath(".//*[@class='uiIcon64x64Templateexo_accessibleMedia']");
    public final By ELEMENT_ADDDOCUMENT_ANNOUNCEMENT = By.xpath(".//*[@class='uiIcon64x64Templateexo_announcement']");
    public final By ELEMENT_ADDDOCUMENT_CSS_FILE = By.xpath(".//*[@class='uiIcon64x64Templateexo_cssFile']");
    public final By ELEMENT_ADDDOCUMENT_CONTACT_US = By.xpath(".//*[@class='uiIcon64x64Templateacme_contact_us']");
    public final By ELEMENT_ADDDOCUMENT_HTML_FILE = By.xpath(".//*[@class='uiIcon64x64Templateexo_htmlFile']");
    public final By ELEMENT_ADDDOCUMENT_ILLUSTRATED_WEB_CONTENT = By.xpath(".//*[@class='uiIcon64x64Templateexo_pictureOnHeadWebcontent']");
    public final By ELEMENT_ADDDOCUMENT_JAVASCRIPT_FILE = By.xpath(".//*[@class='uiIcon64x64Templateexo_jsFile']");
    public final By ELEMENT_ADDDOCUMENT_PRODUCT_FILE = By.xpath(".//*[@class='uiIcon64x64Templateacme_product']");
    public final By ELEMENT_ADDDOCUMENT_WEBLINK = By.xpath(".//*[@class='uiIcon64x64Templateexo_link']");
	public final By ELEMENT_ADDDOCUMENT_PRODUCT = By.xpath("//*[@class='uiIcon64x64Templateacme_product']");
	public final By ELEMENT_ADDDOCUMENT_NEXT_PAGE = By.xpath(".//*[@id='UISelectDocumentForm']//*[@data-original-title='Next Page']");
	
	public final By ELEMENT_DOCFORM_BLANK_TITLE = By.xpath("//*[@id='title0']");
	public final By ELEMENT_DOCFORM_BLANK_DESC = By.xpath("//*[@id='description0']");
	public final By ELEMENT_DOCFORM_BLANK_CREATOR = By.xpath("//*[@id='creator0']");
	public final By ELEMENT_DOCFORM_BLANK_SOURCE = By.xpath("//*[@id='source0']");

	//New file form
	public final By ELEMENT_FILEFORM_BLANK_CONTENT2 = By.xpath("//*[@id='cke_1_contents']/iframe");
	//public final By ELEMENT_FILEFORM_BUTTON_SAVEANDCLOSE = By.xpath("//*[@class='btn' and text()='Save & Close']"); 
	public final By ELEMENT_FILEFORM_BUTTON_SAVEANDCLOSE = By.xpath("//*[@class='btn' and contains(@onclick,'SaveAndClose')]"); 
	public final By ELEMENT_FILEFORM_LANGUAGE = By.xpath("//*[@name='content-lang']");
	
	//New Web content form
	public final By ELEMENT_WEBCONTENTFORM_BUTTON_LINK = By.xpath("//*[@class='cke_button_icon cke_button__link_icon']");
	public final By ELEMENT_WEBCONTENTFORM_LINK_ADRESS = By.xpath("//*[text()='URL']/../..//*[contains(@id,'textInput')]");
	public final By ELEMENT_WEBCONTENTFORM_LINK_OK = By.xpath("//*[@class='cke_dialog_body']//*[text()='OK']");
    public final By ELEMENT_DOCUMENT_VIEW_TAB = By.xpath(".//*[@id='UIDocumentContainer']//*[contains(@data-original-title,'Document View')]");
	
	//New folder popup
	public final By ELEMENT_ADD_NEW_FOLDER_POPUP_TITLE= By.xpath(".//*[@id='UIPopupWindow']//span[text()='New Folder']");
	public final By ELEMENT_USE_CUSTOM_TYPE_FOLDER = By.id("customTypeCheckBox");
	public final By ELEMENT_FOLDER_TITLE_TEXTBOX = By.id("titleTextBox");
	public final By ELEMENT_FOLDER_TYPE_OPTION = By.name("customTypeSelectBox");
	public final String ELEMENT_CONTENT_FOLDER_TYPE = "nt:unstructured";
	
	public final String ELEMENT_DOCUMENT_FOLDER_TYPE = "nt:folder";
	public final By ELEMENT_DOCUMENT_FOLDER_TYPE_XPATH = By.xpath("//option[text()='Document Folder']");
	public final By ELEMENT_CREATE_FOLDER_BUTTON = By.xpath("//*[text()='Create Folder']");
	
    
    /*******************************************PERMISSION**************************************************************************************/
    public final By ELEMENT_PERMISSION_SELECTUSER = By.xpath("//*[@class='uiIconSelectUser uiIconLightGray']");
	public final By ELEMENT_PERMISSION_SELECTMEMBERSHIP = By.xpath("//*[@class='uiIconSelectMember uiIconLightGray']");
	public final By ELEMENT_PERMISSION_SELECTEVERYONE = By.xpath("//*[@class='uiIconAddAny uiIconLightGray']");
	public final String ELEMENT_PERMISSION_USER_ADDUSER = "//*[text()='${name}']/../..//*[@class='actionIcon']";
	public final By ELEMENT_PERMISSION_TEXTBOXUSER = By.xpath("//*[@id='userOrGroup']");
	public final By ELEMENT_PERMISSION_CHECKBOXREAD = By.xpath("//*[@class='checkbox' and @for='read']");
	public final By ELEMENT_PERMISSION_CHECKBOXMODIFY = By.xpath("//*[@class='checkbox' and @for='add_node']");
	public final By ELEMENT_PERMISSION_CHECKBOXREMOVE = By.xpath("//*[@class='checkbox' and @for='remove']");
	public final By ELEMENT_PERMISSION_SAVE = By.xpath("//*[text()='Save']");
	public final By ELEMENT_PERMISSION_CLOSE = By.xpath("//*[text()='Close']");
	public final String ELEMENT_PERMISSION_DELETE = "//*[text()='${name}']/../..//*[@class='actionIcon']";
    public final String ELEMENT_PERMISSION_USER_OR_GROUP_NAME = ".//*[@id='PermissionInfo']//*[text()='${name}']";
    public final By ELEMENT_PERMISSION_ADD = By.xpath("//*[@id='UIQueriesForm']//*[contains(@class,'uiIconAddPermission')]");
    
  	public final By ELEMENT_DRIVE_SELECT_MEMBERSHIP_POPUP = By.xpath(".//*[contains(@id,'UIDrivePermissionSelector')]");
  	public final String ELEMENT_DRIVE_SELECT_RIGHT_PARENT_GROUP = "//*[contains(@id,'UIDrivePermissionSelector')]//a[contains(.,'$group')]";
  	public final By ELEMENT_CAT_SELECT_MEMBERSHIP_POPUP = By.xpath(".//*[contains(@id,'SelectUserOrGroup')]");
  	public final String ELEMENT_CAT_SELECT_RIGHT_PARENT_GROUP = "//*[contains(@id,'SelectUserOrGroup')]//a[contains(.,'$group')]";
  	public final By ELEMENT_LOCK_SELECT_MEMBERSHIP_POPUP = By.xpath(".//*[contains(@id,'UIPermissionSelector')]");
  	public final String ELEMENT_LOCK_SELECT_RIGHT_PARENT_GROUP = "//*[contains(@id,'UIPermissionSelector')]//a[contains(.,'$group')]";
  	public final By ELEMENT_TAG_SELECT_MEMBERSHIP_POPUP = By.xpath(".//*[contains(@id,'UIGroupMemberSelector')]");
  	public final String ELEMENT_TAG_SELECT_RIGHT_PARENT_GROUP = "//*[contains(@id,'UIGroupMemberSelector')]//a[contains(.,'$group')]";
  	public final By ELEMENT_TAG_SELECT_MEMBERSHIP_ADD_BTN = By.xpath("//*[@id='UITagPermissionForm']//*[contains(.,'Add')][contains(@class,'btn-primary')]");
  	
  	/*******************************************SEO MANAGEMENT**************************************************************************************/
  	public final By ELEMENT_SEO_SAVE = By.xpath(".//*[@id='RightContainer']//button[text()='Save']");
	public final By ELEMENT_SEO_CLOSE=By.xpath(".//*[@id='UISEOPopupWindow']//a[@class='uiIconClose pull-right']");
	public final By ELEMENT_SEO_LANGUAGE_SHOW = By.xpath("//*[@class='uiIconPlus uiIconLightGray']");
	public final By ELEMENT_SEO_LANGUAGE_SELECTBOX = By.xpath("//*[@name='language']");
	public final By ELEMENT_SEO_TITLEBOX = By.xpath("//*[@id='title']");
	public final By ELEMENT_SEO_DELETE = By.xpath("//*[@title='Delete']");
	public final By ELEMENT_SEO_HELPDESC = By.xpath("//*[text()='Description: ']/..//*[@id='DescriptionHelp']");
	public final By ELEMENT_SEO_HELPKEYWORD = By.xpath("//*[text()='Keywords: ']/..//*[@id='DescriptionHelp']");
	public final By ELEMENT_SEO_HELPPRIORITY = By.xpath("//*[@id='PriorityHelp']");
	public final By ELEMENT_SEO_HELP_POPOVER = By.xpath("//*[@class='popover-content']");
	public final String ELEMENT_SEO_SELECTED_LANGUAGE= ".//*[@id='LeftContainer']//*[contains(text(),'${language}')]";
	
	/*******************************************SITE EXPLORER HOME**************************************************************************************/
	public final By ELEMENT_SITEEXPLORER_WORKING_PANEL = By.xpath("//*[@class='navItemSelected' and text()='Content Explorer']");
	public final By ELEMENT_DOCUMENT_LIST_ROW_CONTENT = By.xpath(".//*[@id='UIDocumentNodeList']//*[contains(@class,'rowView')]");

	//Address Bar
	public final By ELEMENT_ADDRESS_BAR_ICON_VIEW = By.xpath(".//*[@id='UIAddressBar']//*[@class='uiIconEcmsViewDefault uiIconEcmsViewIcons uiIconEcmsLightGray']");
	public final By ELEMENT_ADDRESS_BAR_LIST_VIEW = By.xpath(".//*[@id='UIAddressBar']//*[@class='uiIconEcmsViewDefault uiIconEcmsViewList uiIconEcmsLightGray']");
	public final By ELEMENT_SITE_PATH= By.cssSelector("#address");

	//Action Bar
	public final By ELEMENT_ACTIONBAR_ADDDOCUMENT = By.xpath("//*[@class='uiIconEcmsAddDocument uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_ADDFOLDER = By.xpath("//*[@class='uiIconEcmsAddFolder uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_SETTINGS = By.xpath("//*[@class='setupPreferencesButton actionIcon pull-right']");
	public final By ELEMENT_ACTIONBAR_PERMISSION = By.xpath("//*[@class='uiIconEcmsViewPermissions uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_SEARCHBAR = By.xpath("//*[@id='simpleSearch']");
	public final By ELEMENT_ACTIONBAR_MORE = By.xpath("//*[@class='dropdown pull-right listHiddenActionsContainer']/..//*[text()='More ']");
	public final By ELEMENT_ACTIONBAR_METADATA = By.xpath(".//*[@class='uiIconEcmsViewMetadatas uiIconEcmsLightGray']");

	public final By ELEMENT_ACTIONBAR_ADDTRANSLATION = By.xpath("//*[@class='uiIconEcmsAddLocalizationLink uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_ADDCOMMENT = By.xpath("//*[@class='uiIconEcmsComment uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_PUBLICATION = By.xpath("//*[@class='uiIconEcmsManagePublications uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_VOTE = By.xpath("//*[@class='uiIconEcmsVote uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_RELATION = By.xpath(".//i[@class='uiIconEcmsManageRelations uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_IMPORT_BUTTON = By.xpath(".//i[@class='uiIconEcmsImportNode uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_EXPORT_BUTTON= By.xpath(".//*[@class='uiIconEcmsExportNode uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_PROPERTIES = By.xpath(".//i[@class='uiIconEcmsViewProperties uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_MANAGER_PUBLISHTATION =By.xpath(".//i[@class='uiIconEcmsManagePublications uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_CATEGORY = By.xpath("//*[@class='uiIconEcmsManageCategories uiIconEcmsLightGray']");
	public final By ELEMENT_ACTIONBAR_TAG = By.xpath("//*[@class='uiIconEcmsTaggingDocument uiIconEcmsLightGray']");
	public final String ELEMENT_ACTIONBAR_ACTION ="//*[@class='actionIcon'][contains(.,'$action')]";
	public final By ELEMENT_ACTIONBAR_SHOWDRIVES = By.id("driveAction");
	public final By ELEMENT_ACTIONBAR_DELETE=By.xpath(".//*[@id='ECMContextMenu']//*[@class='uiIconEcmsDelete']");
	public final By ELEMENT_SITE_EXPLORER_ALL_CHECKBOX= By.xpath("//input[@type='checkbox' and @name= 'UIFileViewCheckBox']");
	public final By ELEMENT_DELETE_ALL_BUTTON = By.xpath(".//*[@id='JCRContextMenu']//i[@class='uiIconEcmsDelete']");
	public final By ELEMENT_ACTIONBAR_WATCH = By.xpath(".//*[contains(@class,'uiIconEcmsWatchDocument')]");
	public final By ELEMENT_ACTIONBAR_WATCH_RADIO = By.xpath("//*[@class='uiRadio']/*[@id='notificationType']/../*[contains(.,'Email')]");
	public final By ELEMENT_ACTIONBAR_WATCH_BUTTON = By.xpath("//*[@class='btn'][contains(.,'Watch')]");
	public final By ELEMENT_ACTIONBAR_WATCH_NOTICE = By.xpath("//*[@id='wcm-notice'][contains(.,'You are watching this document.')]");
	public final By ELEMENT_ACTIONBAR_SHARE = By.xpath("//*[@class='uiIconEcmsShareDocuments uiIconEcmsLightGray']");
	
	//Add Category popup
	public final By ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_TAB = By.xpath(".//*[@id='UICategoryManager']//a[text()='Select Category']");
	public final By ELEMENT_ADD_CATEGORY_POPUP_MENU = By.name("taxonomyTree");
	public final String ELEMENT_ADD_CATEGORY_POPUP_CATEGORY_NAME_LEFT_SIDE=".//*[@id='UIOneTaxonomySelector']//i[@title='${nameTitle}']";
	public final String ELEMENT_ADD_CATEGORY_POPUP_SELECT_CATEGORY_RIGHT_SIDE =".//*[@id='UISelectTaxonomyPanel']//div[contains(.,'${nameCategory}')]//../..//i[@class='uiIconValidate uiIconLightGray']";
	public final By ELEMENT_ADD_CATEGORY_POPUP_CLOSED_BUTTON= By.xpath(".//*[@id='UICategoryManager']//button[text()='Close']");
	public final String ELEMENT_ADD_CATEGORY_POPUP_DELETE_CATEGORY= ".//*[@id='UICategoriesAddedList']//td[contains(.,'${nameCategory}')]/../..//i[@class='uiIconDelete uiIconLightGray']";

	//Import Node popup
	public final By ELEMENT_IMPORT_NODE_POPUP_TITLE= By.xpath(".//*[@id='UIPopupWindow']//span[text()='Import']");
	public final By ELEMENT_IMPORT_NODE_POPUP_UPLOAD_BUTTON = By.name("file");
	public final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR = By.name("behavior");
	public final By ELEMENT_IMPORT_NODE_POPUP_VERSION_HISTORY_BUTTON = By.xpath("//div[@id='versionHistory']//input[@name='file']");
	public final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_CREATE_NEW = By.xpath(".//*[@id='UIImportNode']//option[text()='Create New']");
	public final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REMOVE_EXISTING = By.xpath(".//*[@id='UIImportNode']//option[text()='Remove Existing']");
	public final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_REPLACE_EXISTING = By.xpath(".//*[@id='UIImportNode']//option[text()='Replace Existing']");
	public final By ELEMENT_IMPORT_NODE_POPUP_BEHAVIOR_THROW_EXCEPTION = By.xpath(".//*[@id='UIImportNode']//option[text()='Throw Exception']");
	public final String ELEMENT_IMPORT_NODE_POPUP_UPLOAD_FILE_LABEL = "//div[@class='fileNameLabel' and contains(text(),'${fileName}')]";
	public final By ELEMENT_IMPORT_MODE_POPUP_IMPORT_BUTTON = By.xpath(".//*[@id='UIImportNode']//button[text()='Import']");

	//Export Node popup
	public final By ELEMENT_EXPORT_NODE_POPUP_TITLE= By.xpath(".//*[@id='UIPopupWindow']//span[text()='Export']");
	public final By ELEMENT_EXPORT_NODE_POPUP_DOC_VIEW = By.xpath(".//*[@id='UIExportNode']//input[@value='docview']");
	public final By ELEMENT_EXPORT_NODE_POPUP_SYS_VIEW = By.xpath(".//*[@id='UIExportNode']//input[@value='sysview']");
	public final By ELEMENT_EXPORT_NODE_POPUP_ZIP = By.name("zip");
	public final By ELEMENT_EXPORT_NODE_POPUP_EXPORT_BUTTON = By.xpath("//button[text()='Export']");

	//Relation popup
	public final By ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB=By.xpath(".//*[@id='UIRelationManager']//a[text()='Select Relation']");
	public final String ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_NODE_LEFT_TREE=".//*[@id='UIOneNodePathSelector']//i[@title='${nameNode}']";
	public final String ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_SELECT_CONTENT_RIGHT_TREE =".//*[@id='UISelectPathPanel']//div[contains(.,'${nameContent}')]/../..//*[@class='uiIconValidate uiIconLightGray']";
	public final By ELEMENT_RELATION_POPUP_SELECT_RELATION_TAB_CLOSE_BUTTON=By.xpath(".//*[@id='UIRelationManager']//button[text()='Close']");
	public final String ELEMENT_RELATION_POPUP_RELATION_LIST_DELETE_BUTTON=".//*[@id='RelateAddedList']//span[contains(.,'${nameContent}')]/../..//i[@class='uiIconDelete uiIconLightGray']";
	public final String MESSAGE_DELETE_RELATION = "Are you sure you want to delete this relation?";

	//Metadata popup
	public final By ELEMENT_METADATA_POPUP_CANCEL= By.xpath(".//*[@id='UIViewMetadataContainer']//button[text()='Cancel']");
	public final By ELEMENT_METADATA_POPUP= By.xpath("//*[@id='UIViewMetadataManager']");

	// go to Show drives
	public final By ELEMENT_SHOW_DRIVES = By.cssSelector("#driveAction");
	public final String ELEMENT_SELECTED_DRIVE= ".//*[@data-original-title='${nameDrive}']";

	//Drive area
	public final String ELEMENT_ACTIONBAR_SELECTED_DRIVE= ".//*[@id='UIDrivesArea']//*[contains(@data-original-title,'${driver}')]";

	// View Properties form
	public final By ELEMENT_VIEWPROPERTIES_PROPERTIES_TAB = By.linkText("Properties");
	public final By ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_TAB = By.linkText("Add New Property");
	public final By ELEMENT_VIEWPROPERTIES_VALUE_INPUT = By.xpath("//input[contains(@id,'value')]");
	public final By ELEMENT_VIEWPROPERTIES_ADD_PROPERTY_INPUT = By.name("property_select");
	public final String ELEMENT_VIEWPROPERTIES_PROPERTY = "//td[text()='{$property}']/..//div[contains(text(),'{$value}')]";

	//Manage Publishtation popup
	public final String ELEMENT_MANAGEPUBLICATION_STATE = "//p[contains(text(),'{$state}')]/../a[@class='node']";	
	public final String ELEMENT_MANAGEPUBLICATION_CURRENT_SPECIFIC_STATUS = "//*[@class='currentStatus']/p[contains(text(),'${status}')]";
	public final By ELEMENT_MANAGEPUBLICATION_SCHEDULE_TAB = By.xpath("//a[text()='Scheduled']");	
	public final By ELEMENT_MANAGEPUBLICATION_PUB_FROM_INPUT = By.name("UIPublicationPanelStartDateInput");
	public final By ELEMENT_MANAGEPUBLICATION_PUB_TO_INPUT = By.name("UIPublicationPanelEndDateInput");
	public final String MSG_INVALID_DATE_TIME = "The date format is invalid. Please check again.";
	public final By ELEMENT_MANAGEPUBLICATION_REVISION_TAB = By.linkText("Revision");
	public final By ELEMENT_MANAGEPUBLICATION_HISTORY_TAB = By.linkText("History");
	public final String ELEMENT_MANAGEPUBLICATION_HISTORY_ITEM = "//div[text()='${state}']";

	//add translation popup
	public final By ELEMENT_ACTIONBAR_ADDTAG = By.xpath("//*[@class='uiIconEcmsTaggingDocument uiIconEcmsLightGray']");
	public final By ELEMENT_ADDTRANSLATION_SELECTDOC = By.xpath("//*[@title='Select Document']");
	public final String ELEMENT_FOLDERSELECTOR_PATH = "//*[@class='nodeName'][contains(.,'${path}')]";
	public final String ELEMENT_FOLDERSELECTOR_CONTENTDETAIL_FINALPATH = "//*[@class='OddItem']//*[text()='${name}']";
	public final By ELEMENT_SAVE_BTN = By.xpath("//*[text()='Save']");
	public final By ELEMENT_ADD_BTN = By.xpath("//*[text()='Add']");
	public final By ELEMENT_CLOSE_BTN = By.xpath("//*[text()='Close']");
	public final By ELEMENT_OK_BTN = By.xpath("//*[text()='OK']");

	//Select document popup
	final public String ELEMENT_SELECT_DOCUMENT_NODE_FOLDER= ".//*[@id='LeftWorkspace']//span[contains(.,'${node}')]";
	final public String ELEMENT_SELECT_DOCUMENT_NODE_FILE= ".//*[@id='ListRecords']//*[contains(text(),'${content}')]";

	//settings driver display
	public final By ELEMENT_DRIVERSETTINGS_SORTBY = By.xpath("//*[@class='selectbox' and @name='sortBy']");
	public final By ELEMENT_DRIVERSETTINGS_ORDER = By.xpath("//*[@class='selectbox' and @name='order']");
	public final By ELEMENT_DRIVERSETTINGS_SAVE = By.xpath("//*[@class='btn btn-primary' and text()='Save']");

	// upload
	public final By ELEMENT_ACTIONBAR_UPLOAD = By.xpath("//*[@class='uiIconEcmsUpload uiIconEcmsLightGray']");
	public final By ELEMENT_MORE_LINK_WITHOUT_BLOCK = By.xpath("//*[@id='uiActionsBarContainer']//*[contains(text(), 'More')]");
	//	public final By ELEMENT_UPLOAD_LINK = By.id("MultiUploadInputFiles");
	public final By ELEMENT_UPLOAD_LINK =By.xpath(".//*[@id='UploadButtonDiv']//*[contains(@class,'uiIconEcmsUpload')]");
	public final By ELEMENT_ACTIONBAR_EDIT = By.xpath("//*[@class='uiIconEcmsEditDocument uiIconEcmsLightGray']");
	public final By ELEMENT_FILE_FORM_TITLE = By.xpath("//*[@id='title0']");
	//Drive selection
	public final String ELEMENT_SELECTDRIVE_SPECIFICDRIVE = "//*[@class='driveLabel' and @data-original-title='${spaceName}']";

	//Add document
	public final By ELEMENT_ADDDOCUMENT_CHOICETYPE = By.xpath("//*[@class='templateTitle']");
	public final By ELEMENT_THUMBNAIL_VIEW_ADMIN_VIEW = By.xpath(".//*[contains(@class,'uiThumbnailsView')]");
	public final By ELEMENT_CONTEXT_MENU_ADD_DOCUMENT =By.xpath(".//*[@id='JCRContextMenu']//*[contains(@class,'uiIconEcmsAddDocument')]");
	public final By ELEMENT_WORKING_AREA_TEMPLATE_DOCUMENTS=By.xpath(".//*[@id='UIDocumentFormController']");
	
	//Add folder
	public final By ELEMENT_ADDFOLDERBOX = By.xpath("//*[@class='PopupTitle popupTitle']");
	public final By ELEMENT_ADDFOLDER_NAME = By.xpath("//*[@id='titleTextBox']");
	public final By ELEMENT_ADDFOLDER_FOLDERTYPE = By.xpath("//*[@class='selectbox']");
	public final By ELEMENT_ADDFOLDER_CREATEFOLDERBUTTON = By.xpath("//*[@class='btn addFolderButton']");

	public final By ELEMENT_SITEEXPLORER_ACTION_COPY = By.xpath("//*[@class='uiIconEcmsCopy']");
	public final By ELEMENT_SITEEXPLORER_ACTION_CUT = By.xpath("//*[@class='uiIconEcmsCut']");
	public final By ELEMENT_SITEEXPLORER_ACTION_PASTE = By.xpath("//*[@class='uiIconEcmsPaste']");
	public final By ELEMENT_SITEEXPLORER_ACTION_RENAME = By.xpath("//*[@class='uiIconEcmsRename']");
	public final By ELEMENT_SITEEXPLORER_ACTION_OPEN_IN_MS_OFFICE = By.xpath("//*[@class='uiIconDownload uiIconLightGray']");
	public final By ELEMENT_SITEEXPLORER_ACTION_ADDSYMLINK = By.xpath("//*[@class='uiIconEcmsAddSymLink']");

	//Left explorer box
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_ROOTNODE = By.xpath("//*[@class='uiIconEcmsHome uiIconEcmsLightGray']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_RELATION = By.xpath("//*[@class='uiIconEcmsRelationMini uiIconEcmsLightGray']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_EXPLORER = By.xpath("//*[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");

	public final String ELEMENT_SITEEXPLORER_LEFTBOX_TITLE_TRANSLATION="//*[text()='fr (${title})']";
	public final By ELEMENT_SITEEXPLORER_LIST_LOCK_NODE = By.xpath("//*[@id='ECMContextMenu']//*[@class='uiIconEcmsLock']");
	public final By ELEMENT_SITEEXPLORER_LIST_UNLOCK_NODE = By.xpath("//*[@id='ECMContextMenu']//*[@class='uiIconEcmsUnlock']");
	public final String ELEMENT_SITEEXPLORER_LOCK_ICON= "//*[contains(@class,'iconLockedsmall')]/../../*[contains(.,'$node')]";

	//Left panel of SE
	public final By ELEMENT_FILE_EXPLORER = By.xpath("//*[@data-original-title = 'File Explorer']");
	public final By ELEMENT_FILE_EXPLORER_ICON = By.xpath(".//i[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");
	public String ELEMENT_FILE_TITLE_RIGHT_PANEL=".//*[@class='nodeGroup']//span[text()='${fileName}']";

	//View detail a content in SE
	public By ELEMENT_CONTENT_THUMBNAIL = By.xpath(".//*[@class='iconContainer']/i");
	public String ELEMENT_WEBCONTENT_NAME = ".//*[@id='UIDocumentContainer']//h6[text()='${nameFile}']";
	public By ELEMENT_CONTENT_MESSAGE_NOT_AVAILABLE = By.xpath(".//h4[text()='The preview of this document is not available.']");
	public By ELEMENT_CONTENT_MESSAGE_TOO_MANY_PAGES = By.xpath(".//h4[text()='The preview is not available for content with over 99 pages.']");
	public By ELEMENT_CONTENT_MESSAGE_OVER_SIZE= By.xpath(".//h4[text()='The preview is not available for content larger than 5 MB.']");
	public By ELEMENT_CONTENT_DOWNLOAD_BUTTON= By.xpath(".//*[@class='btn btn-primary']");
	public By ELEMENT_CONTENT_OPEN_DESKTOP=By.xpath(".//*[@class='btn'][text()='Open on Desktop']");

	//advanced search 
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAME = By.xpath("//*[@id='keyword']");
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SEARCHBTN = By.xpath("//*[@id='tab-UIContentNameSearch']//*[@class='btn' and text()='Search']");
	public final By ELEMENT_SITEXPLORER_ADVANCEDSEARCH_CREATEQUERYTAB = By.xpath("//*[text()='New Query']");
	public final By ELEMENT_SITEXPLORER_ADVANCEDSEARCH_SAVEDQUERYTAB = By.xpath("//*[text()='Saved Query']");
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_NAMEQUERY = By.xpath("//*[@id='name']");
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEQUERYBTN = By.xpath("//*[@id='UIJCRAdvancedSearch']//*[@class='btn' and text()='Save']");
	public final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_DELETEQUERYBTN = "//*[text()='${name}']/../..//*[@class='uiIconDelete uiIconLightGray']";
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERYBTN = By.xpath("//*[@class='uiIconEcmsExecute']");
	public final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EXECUTEQUERY = "//*[text()='${name}']/../..//*[@class='uiIconEcmsExecute']";
	public final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_EDITQUERYBTN = "//*[text()='${name}']/../..//*[@class='uiIconEdit uiIconLightGray']";
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_QUERYTYPE = By.xpath("//*[@class='uiForm EditQueryForm']/..//*[@class='selectbox']");
	public final By ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_SAVEEDITQUERYBTN = By.xpath("//*[@id='EditQueryForm']//*[@class='btn' and text()='Save']");
	public final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT ="//*[text()='${name}']";
	public final String ELEMENT_SITEEXPLORER_ADVANCEDSEARCH_RESULT1 = "//*[@id='UISavedQuery']//*[text()='${name}']/../..//*[text()='xpath']";

	//Confirm delete box
	public final By ELEMENT_SITEEXPLORER_CONFIRMBOX_DELETE= By.xpath("//*[@class='uiAction uiActionBorder']//*[text()='Delete']");

	//upload file form
	public String ELEMENT_ATTACHMENT_FORM_FILE_NAME = "//*[text()='$fileName']";
	public By ELEMENT_EVENT_FILE_INPUT = By.xpath("//*[@id='upload']//*[@name='file']");
	public By ELEMENT_ATTACHMENT_SAVE_BUTTON = By.xpath("//*[@id='UIAttachFileForm']//*[text()='Save']");
	public String ELEMENT_ATTACH_FILE_NAME = "//*[@data-original-title='$fileName']";
	public final By ELEMENT_UPLOAD_PROGRESS_BAR = By.xpath(".//*[contains(@class,'progress progress-striped pull-right')]");
	public final By ELEMENT_UPLOAD_BUTTON = By.xpath("//a[@class='actionIcon' and contains(text(),'Upload')]");

	//Permission
	public final By ELEMENT_PERMISSION_USER = By.xpath("//*[@class='uiIconSelectUser uiIconLightGray']");
	public final By ELEMENT_PERMISSION_GROUP = By.xpath("//*[@class='uiIconSelectMember uiIconLightGray']");
	public final By ELEMENT_PERMISSION_ANY = By.xpath("//*[@class='uiIconAddAny uiIconLightGray']");
	public final By ELEMENT_SEARCH_USER_INPUT = By.id("Quick Search");
	public final By ELEMENT_SELECT_SEARCH = By.name("filter");
	public final String ELEMENT_USER_CHECKBOX = "//*[text()='${user}']/../..//*[@type='checkbox']"; 
	public final By ELEMENT_QUICK_SEARCH_BUTTON = By.xpath("//a[@data-original-title='Quick Search']");
	public final By ELEMENT_ADD_USERS_BUTTON = By.xpath("//*[@id='UIUserSelector']//*[text()='Add']");

	//comment
	public final String ELEMENT_SITEEXPLORER_COMMENT = "//*[text()=' ${number} Comment(s)']";
	public final By ELEMENT_SITEEXPLORER_COMMENT_SHOW = By.xpath("//*[text()='Show comments']");
	public final By ELEMENT_SITEEXPLORER_COMMENT_EDIT = By.xpath("//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_SITEEXPLORER_COMMENT_DELETE = By.xpath("//*[@class='uiIconTrash uiIconLightGray']");
	public final String ELEMENT_SITEEXPLORER_COMMENT_CONTENT = "//*[text()='${content}']";
	public final By ELEMENT_SITEEXPLORER_COMMENT_SAVE = By.xpath(".//*[@id='UICommentForm']//button[text()='Save']");


	// clipboard
	public final By ELEMENT_SITEEXPLORER_CLIPBOARD = By.xpath("//*[@id='UISideBar']//*[@class='uiIconEcmsClipboardMini uiIconEcmsLightGray']");
	public final String ELEMENT_CLIPBOARD_PASTE_NODE = "//*[@id='UISideBar']//*[contains(text(),'{$node}')]/../..//*[@class='uiIconEcmsPaste uiIconEcmsLightGray']";
	public final String ELEMENT_CLIPBOARD_DELETE_NODE = "//*[@id='UISideBar']//*[contains(text(),'{$node}')]/../..//*[@class='uiIconEcmsDelete uiIconEcmsLightGray']";
	public final By ELEMENT_CLIPBOARD_CLEAR_ALL = By.xpath("//*[@id='UIClipboard']//*[contains(text(),'Clear All')]");

	//vote
	public final By ELEMENT_SITEEXPLORER_VOTE_AVERAGE = By.xpath("//*[@data-original-title='Average']");
	public final By ELEMENT_SITEEXPLORER_VOTEONDOCUMENT = By.xpath("//*[@class='uiVote clearfix']");

	//Tag
	public final By ELEMENT_SITEEXPLORER_TAG_DELETE = By.xpath("//*[@class='uiIconClose']");
	public final By ELEMENT_SITEEXPLORER_TAG_NAME = By.xpath("//*[@id='names']");
	public final String ELEMENT_SITEEXPLORER_TAG_EXISTING = "//*[@class='actionField']//*[contains(text(),'${name}')]";
	//public final By ELEMENT_SITEEXPLORER_TAG_INPUT= By.xpath("//*[@id='tagName']");
	public final By ELEMENT_TAG_FORM = By.xpath("//*[@id='names']");
	public final By ELEMENT_ADD_TAG_FORM = By.xpath("//*[@id='UITaggingForm']//*[contains(text(),'Add')]");
	public final By ELEMENT_TAG_POPUP_CLOSE = By.xpath("//*[@id='UITaggingForm']//*[contains(text(),'Close')]");
	public final String ELEMENT_TAG_POPUP_LINK_TAGS=".//*[@id='UITaggingForm']//*[contains(text(),'${name}')]";

	//Add category
	public final By ELEMENT_CATEGORY_CHANGE_FORM_SELECT_CATEGORY =By.xpath("//*[@id='UICategoryManager']//*[contains(text(),'Select Category')]");
	public final By ELEMENT_CATEGORY_SELECT_CATEGORY_TREE = By.xpath("//*[@name='taxonomyTree']");
	public final By ELEMENT_CATEGORY_ADD_ROOT_NODE = By.xpath("//*[@class='uiIconAddRootNode uiIconLightGray']");

	public String ELEMENT_DOCUMENT_VIEW = "//*[@id='UITabContent']//*[contains(text(),'{$content}')]";

	public final By ELEMENT_SITEEXPLORER_RENAME_FIELD = By.xpath("//*[@id='renameField']");
	public final By ELEMENT_SITEEXPLORER_RENAME_SAVE = By.xpath("//*[@id='renameLink']");

	public final By ELEMENT_CHECK_OPEN_WEBCONTENT_IN_MSOFFICE = By.xpath("//*[@id='main']//*[contains(text(),'css')]");
	// SideBar
	public final String ELEMENT_SE_NODE = "//*[@title='${node}']";
	public final By ELEMENT_SIDE_BAR_MAINTAB = By.xpath(".//*[@id='UISideBar']//h6[@class='title']");
	//public final By ELEMENT_SIDEBAR_SITES_MANAGEMENT = By.xpath("//*[@data-original-title = 'Sites Management' or @title = 'Sites Management']");
	public final By ELEMENT_SIDEBAR_SITES_MANAGEMENT = By.xpath("//*[@data-original-title='Site Management']");
	public final By ELEMENT_SIDE_BAR_RELATION_ICON = By.xpath(".//*[@id='UISideBar']//i[@class='uiIconEcmsRelationMini uiIconEcmsLightGray']");
	public final String ELEMENT_SIDE_BAR_RELATION_TAB_FILE_TITLE = ".//*[@id='UISideBar']//a[text()='${nameContent}']";
	public final By ELEMENT_SIDE_BAR_FILE_EXPLORER_ICON = By.xpath(".//*[@id='UISideBar']//i[@class='uiIconEcmsExplorerMini uiIconEcmsLightGray']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_INTRANET = By.xpath("//*[@class='uiIcon16x16FolderDefault uiIcon16x16exo_portalFolder' and @title='intranet']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_DOCUMENT = By.xpath("//*[@class='uiIcon16x16FolderDefault uiIcon16x16exo_documentFolder' and @title='documents']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_SPACE = By.xpath("//*[@class='uiIcon16x16FolderDefault uiIcon16x16exo_portalFolder' and @title='intranet']");
	//public final String ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME = "//*[@class='nodeName' and text()='${title}']";
	public final String ELEMENT_SITEEXPLORER_LEFTBOX_NODENAME = ".//*[@class='nodeGroup']//*[@class='nodeName' and text()='${title}']";
	public final By ELEMENT_SITEEXPLORER_ACTION_DELETE = By.xpath("//*[@class='uiIconEcmsDelete']");
	public final By ELEMENT_SITEEXPLORER_TAG_CLOUD_TAB = By.xpath(".//*[@class='uiIconEcmsTagExplorerMini uiIconEcmsLightGray']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_SAVEDSEARCH = By.xpath("//*[@class='uiIconEcmsSavedSearchesMini uiIconEcmsLightGray']");
	public final By ELEMENT_SITEEXPLORER_LEFTBOX_ADVANCEDSEARCH = By.xpath("//*[@class='actionIcon advancedSearchIcon pull-right']//*[@class='uiIconSearch uiIconLightGray']");

	//Side bar-->Tag cloud
	public final String ELEMENT_SIDEBAR_TAGCLOUD_NAME=".//*[@id='UITagExplorer']//a[text()='${name}']";
	public final By ELEMENT_SIDEBAR_TAGCLOUD_EDIT = By.xpath(".//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_SIDEBAR_TAGCLOUD_POPUP_TITLE= By.xpath(".//*[@id='UIPopupWindow']//span[text()='Edit Tag']");
	public final String ELEMENT_SIDEBAR_TAGCLOUD_POPUP_EDIT=".//*[@id='UIEditingTagList']//span[text()='${name}']/../..//*[@class='uiIconEditTag uiIconLightGray']";
	public final String ELEMENT_SIDEBAR_TAGCLOUD_POPUP_DELETE=".//*[@id='UIEditingTagList']//span[text()='${name}']/../..//*[@class='uiIconRemoveTag uiIconLightGray']";
	//Tag Cloud-->Edit-->Tag popup
	public final By ELEMENT_TAG_POPUP_TITLE=By.xpath(".//*[@id='TagPopup']//span[text()='Tag']");
	public final By ELEMENT_TAG_POPUP_NAME_FIELD=By.id("tagName");
	public final By ELEMENT_TAG_POPUP_SAVE=By.xpath(".//*[@id='UITagForm']//*[text()='Save']");
	public final By ELEMENT_TAGE_POPUP_CLOSE=By.xpath(".//span[text()='Edit Tag']/..//*[@title='Close Window']");
	//SEO folder
	public final By ELEMENT_SEO_FOLDER_FILE = By.xpath("//*[@class='text']//*[@data-original-title='sitemaps']");
	//Personal document
	public final String ELEMENT_PERSONAL_DOCUMENT_FILE = ".//*[@id='UIDocumentNodeList']//*[contains(.,'${file}')]";

	//Grid list
	public final String ELEMENT_GRID_LIST_CONTENT = ".//*[@class='uiListGrid']//*[text()='${file}']";


	public final String ELEMENT_PERSONAL_DOCUMENT_FILE_CHECKBOX=".//*[@id='UIDocumentNodeList']//span[text()='${file}']/../../..//span/input";

	//Space drive
	public final String ELEMENT_SPACE_DRIVE_FILE = ".//*[@id='UIDocumentNodeList']//span[text()='${file}']";
	public final String ELEMENT_SPACE_DRIVE_CHECKBOX=".//*[@id='UIDocumentNodeList']//span[text()='${file}']/../../..//*[@type='checkbox']";
	public final String ELEMENT_SPACE_DRIVE_NODE_TREE_FILE =".//*[@class='nodeGroup']//*[contains(text(),'${file}')]";
	//Publication box
	public final String ELEMENT_PUBLICATION_STATUS = "//*[text()='${status}']/..//*[@class='node']";

	//Right column content
	public final String ELEMENT_SITE_EXPLORER_RIGHT_COLUMN_CONTENT=".//*[@id='UITabContent']//a[contains(text(),'${title}')]";

	//View detail a content
	public String ELEMENT_CONTENT_NAME = ".//*[@id='UIDocumentContainer']//span[text()='${nameFile}']";

	// View icons
	public final By ELEMENT_LIST_VIEW_ICON = By.xpath("//*[@data-original-title = 'List']");
	public final By ELEMENT_ADMIN_VIEW_ICON = By.xpath("//*[@data-original-title = 'Admin']");
	public final By ELEMENT_ICONS_VIEW = By.xpath("//*[@data-original-title = 'Icons']");
	public final By ELEMENT_WEB_VIEW = By.xpath("//*[@data-original-title = 'Web']");
	public final By ELEMENT_CATEGORIES_VIEW = By.xpath("//*[@data-original-title = 'Categories']");
	public final String ELEMENT_ITEM_VIEW = "//*[@data-original-title = '$view']";

	//Add new content
	public String ELEMENT_SITE_EXPLORER_CONTENT_NAME= ".//*[@id='UISelectDocumentForm']//i[@data-original-title='${nameContent}']";

	//Share document to space
	public final By ELEMENT_SPACE_LIST = By.xpath("//*[@id='DisplayModesDropDown']");
	public final String ELEMENT_SHARE_DOCUMENT_POPUP = "//*[@id='tipName']//a[contains(text(),'${author}')]";
	public final String ELEMENT_PROFILE_NAME = "//*[@id='UIStatusProfilePortlet']//span[contains(text(),'${author}')]";
	
	public final String ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_COMMENT = "//*[@class='author']//a[contains(text(),'${author}')]"+
													"/..//*[contains(text(),'shared a document')]"+
													"/../..//*[@class='dataInfor']//a[contains(text(),'${spaceName}')]"+
													"/../../..//*[@class='description'][contains(text(),'${comment}')]";
	public final String ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_COMMENT_IN_SPACE = "//*[@class='author']//a[contains(text(),'${author}')]"+
																"/..//*[contains(text(),'shared a document')]"+
																"/../../..//*[@class='description'][contains(text(),'${comment}')]";
	public final By ELEMENT_SHARE_DOCUMENT_FILE_PREVIEW = By.xpath(".//*[@id='UIDocumentPreview']//*[@class='title']");
	
	public final String ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_ICON = "//*[@class='author']//a[contains(text(),'${author}')]" + 
																"/..//*[contains(text(),'shared a document')]/../../..//" + 
																"img[contains(@src,'thumbnailImage') and contains(@src,'${fileName}')]";
	
	public final String ELEMENT_SELECTED_SPACE = "//*[@class='spaceList']//*[text()='${spaceName}']";
	public final String ELEMENT_SHARE_DOCUMENT_ACTION_BUTTON = "//*[@class='uiActionBorder']//*[text()='${name}']";
	public final String ELEMENT_SHARE_DOCUMENT_CONTENT_IN_SPACE = "//*[@class='author']//a[contains(text(),'${author}')]/..//*[contains(text(),'shared a document')]";
	public final String ELEMENT_SHARE_DOCUMENT_AUTHOR = "//*[@class='author']//a[contains(text(),'${author}')]";
	
	public final By ELEMENT_SHARE_DOCUMENT_COMMENT = By.xpath("//*[@id='DisplaytextAreaInput']");
	public final String ELEMENT_SHARE_DOCUMENT_CONTENT_WITH_FILE_NAME = "//*[@class='author']//a[contains(text(),'${author}')]" +
																"/..//*[contains(text(),'shared a document')]" +
																"/../../..//a[contains(text(),'${fileName}')]";
	public final By ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER = By.xpath(".//*[@id='UIDocumentInfo']//*[contains(text(),'Shared')]");
	public final String ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_FILE_SYMLINK = 
										"//*[@id='UIDocumentInfo']//*[contains(@symlink,'${spaceName}')]"+
										"/../../..//*[@class='uiThumbnailsView UIDocumentInfo']//*[contains(text(),'${fileName}')]";
	public final By ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE = By.xpath(".//*[@id='ECMContextMenu']/div/ul/li[7]/a");
	public final String ELEMENT_SPACE_DOCUMENTS_SHARED_FOLDER_SYMLINK_MENU_DELETE_OPTION = "//*[@class='uiAction uiActionBorder']//*[contains(text(),'${action}')]";
		
	//Notifications
	public final String ELEMENT_SHARE_DOCUMENT_CONTENT = "//*[@class='author']//a[contains(text(),'${author}')]/..//*[contains(text(),'shared a document')]/../..//*[@class='dataInfor']//a[contains(text(),'${spaceName}')]";
	public final By ELEMENT_SHARE_DOCUMENT_ACTIVITY_NOT_FOUND =By.xpath(".//*[@id='UIUserActivityStreamPortlet']//*[contains(text(),'Activity not found')]");
	
	public final By ELEMENT_DOCUMENT_SHARE = By.xpath("//*[@class='uiIconEcmsShareDocuments']");
	public final String ELEMENT_DOCUMENT_SELECTED_FOLDER = "//*[@id='UIDocumentNodeList']//*[contains(text(),'${folderName}')]";
	public final String ELEMENT_DOCUMENT_SELECTED_FILE_CHECKBOX = "//*[@id='UIDocumentNodeList']//*[@data-original-title='${fileName}']/../..//*[@class='uiCheckbox']";
	public final String ELEMENT_DOCUMENT_FILE_CHECKBOX = ".//*[@id='FileViewBreadcrumb']//*[@class='uiCheckbox']";
	public final String ELEMENT_DOCUMENT_SELECTED_FILE = "//*[@id='UIDocumentNodeList']//*[@data-original-title='${fileName}']";
	public final String ELEMENT_DOCUMENT_PARENT_FOLDER = ".//*[@id='UIDocumentNodeList']//*[contains(text(),'${folderName}')]";
	public final String ELEMENT_DOCUMENT_FILE_NAME = ".//*[@class='nodeName' and text()='${fileName}']";
	
	public final String ELEMENT_SELECTED_SPACE_TO_SHARE = "//*[@class='uiMention' and text()= '${spaceName}']//*[contains(@onclick, 'RemoveSpace')]";
	public final String ELEMENT_SELECTED_SPACE_TO_REMOVE = "//*[@class='uiIconClose uiIconLightGray' and contains(@onclick,'objectId=/spaces/${spaceName}')]";
	public final By ELEMENT_DOCUMENT_SHARE_DIALOG_TITLE = By.xpath("//*[@class='PopupTitle popupTitle' and contains(text(), 'Share')]");
	public final By ELEMENT_DOCUMENT_SHARE_SPACE_DROPDOWN = By.xpath("//*[@id='UIShareDocumentSpaceMention']//*[contains(text(), 'Share with:')]/..//*[@data-toggle='dropdown']//*[contains(text(),'Select a Space')]");
	public final By ELEMENT_DOCUMENT_SHARE_COMMENT_BOX = By.xpath("//*[@id='DisplaytextAreaInput']/..//*[@class='placeholder' and contains(text(), 'Add a comment about to this file...')]");
	public final String ELEMENT_DOCUMENT_SHARE_ACCESS_OPTION = "//*[@class='accessSpaceMember clearfix']//*[contains(text(), 'Access:')]/..[contains(text(), 'Space Members')]/..//*[@id='permissionDropDown']//option[contains(text(), '${option}')]";
	public final By ELEMENT_DOCUMENT_SHARE_CLOSE_BUTTON = By.xpath("//*[@class='uiIconClose pull-right']");
	public final By ELEMENT_DOCUMENT_SHARE_SPACE_FILTER = By.xpath("//input[@placeholder='Filter Spaces']");
	public final By ELEMENT_DOCUMENT_SHARE_EDIT_FORM = By.xpath("//*[@id='EditFormController']");
	public final String ELEMENT_DOCUMENT_SHARE_UPDATED_USER = "//*[@class='nodeName' and text() = '${fileName}']/../..//*[@class='fileInfoBottom' and contains(text(), 'Updated') and contains(text(), 'by ${user}')]";
	public final String ELEMENT_DOCUMENT_SHARE_UPDATED_TITLE = "//*[@id='title0'][@value='${title}']";
	public final String ELEMENT_DOCUMENT_SHARE_UPDATED_CONTENT = "//*[@id='content' and contains(text(), '${content}')]";
	public final By ELEMENT_DOCUMENT_PERMISSION_DIALOG_TITLE = By.xpath("//*[@class='PopupTitle popupTitle' and contains(text(), 'Permission Management')]");
	public final String ELEMENT_DOCUMENT_PERMISSION = "//*[@id='*:/spaces/${spaceName}${permission}' and @checked='']";
	public final By ELEMENT_DOCUMENT_CONTENT = By.xpath("//*[@id='content']");
	
	public final String SHARE_ACCESS_CAN_VIEW = "Can View";
	public final String SHARE_ACCESS_CAN_EDIT = "Can Edit";
	
}
