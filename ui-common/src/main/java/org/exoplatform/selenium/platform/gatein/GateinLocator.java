package org.exoplatform.selenium.platform.gatein;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;


public class GateinLocator extends PlatformBase {
	/*************************************ANSWER PAGE***************************************************************/
	//Answer page
	public final String ELEMENT_ANSWER_EDIT_PORTLET_TAB="//*[text()='$name']";

	//Permission tab
	public final By ELEMENT_ANSWER_PERMISSION_TAB=By.xpath("//*[@data-target='#PortletPermission-tab']");
	public final By ELEMENT_ANSWER_PERMISSION_TAB_PUBLIC_MODE=By.id("publicMode");

	//Display mode tab
	public final By ELEMENT_DISPLAY_MODE_TAB = By.xpath("//button[text()='Display Mode']");
	public final By ELEMENT_SELECT_DISPLAY_MODE = By.name("display-mode");
	public final By ELEMENT_SELECT_ORDER_BY_MODE = By.name("order-by");
	public final By ELEMENT_SELECT_DIRECTION_MODE = By.name("order-type");
	public final By ELEMENT_ENABLE_VOTE_COMMENT = By.id("enableVotComment");
	public final By ELEMENT_ENABLE_SUBMIT_QUESTION = By.id("enableAnonymousSubmitQuestion");
	public final By ELEMENT_ENABLE_RSS = By.id("enableRSS");
	public final By ELEMENT_VIEW_AVATAR = By.id("enableViewAvatar");
	public final By ELEMENT_POST_QUESTION_IN_ROOT = By.id("isPostQuestionInRootCategory");

	//Category scoping tab
	public final By ELEMENT_CATEGORY_SCOPING_TAB = By.xpath("//button[text()='Category Scoping']");
	public final String ELEMENT_CATEGORY_IN_SCOPE_TAB = "//*[contains(text(),'${catName}')]/..//input[@type='checkbox']";

	//Discussion tab
	public final By ELEMENT_DISCUSSION_TAB = By.xpath("//button[text()='Discussion']");
	public final By ELEMENT_ENABLE_DISCUSSION_CHECKBOX = By.id("EnableDiscuss");
	public final By ELEMENT_ADD_FORUM = By.xpath("//*[@data-original-title='Select Forum']");
	public final String ELEMENT_CATEGORY_EXPAND_ITEM="//*[@class='uiIconNode expandIcon nodeSelected']/*[text()='$name']";
	public final String ELEMENT_CATEGORY_COLLAPSE_ITEM="//*[@class='uiIconNode collapseIcon']/*[text()='$name']";
	public final String ELEMENT_CATEGORY_NODE_ITEM="//*[@class='uiIconNode uiIconEmpty']/*[text()='$name']";

	//Email tab
	public final By ELEMENT_MAIL_NOTIFICATION_TEMPLATE_TAB = By.xpath("//button[text()='Email Notifications']");
	public final By ELEMENT_MAIL_NEW_QUESTION_TAB = By.xpath("//button[text()='New Question']");
	public final By ELEMENT_MAIL_EDIT_ANSWER_TAB = By.xpath("//button[text()='Edit/Answer']");
	public final By ELEMENT_MAIL_MOVE_QUESTION_TAB = By.xpath("//button[text()='Move Question']");
	public final By ELEMENT_MAIL_CONTENT_FRAME1 = By.id("EmailMoveQuestion___Frame");
	public final By ELEMENT_MAIL_CONTENT_FRAME2 = By.xpath("//*[@id='xEditingArea']/iframe");
	public final By ELEMENT_MAIL_MOVE_QUESTION_FRAME = By.xpath("//div[@id='cke_EmailMoveQuestion']//iframe");
	public final By ELEMENT_MAIL_EDIT_ANSWER_FRAME = By.xpath("//div[@id='cke_EmailEditQuestion']//iframe");
	public final By ELEMENT_MAIL_NEW_QUESTION_FRAME = By.xpath("//div[@id='cke_EmailAddNewQuestion']//iframe");
	public final By ELEMENT_CLOSE_SETTING_BUTTON = By.id("Close");
	public final By ELEMENT_EDIT_ANSWER_RELOAD_DEFAULT_EMAIL = By.xpath("//*[@for='EmailEditQuestion']/..//*[@class='uiIconRefresh uiIconLightGray']");
	public final By ELEMENT_NEW_QUESTION_RELOAD_DEFAULT_EMAIL = By.xpath("//*[@for='EmailAddNewQuestion']/..//*[@class='uiIconRefresh uiIconLightGray']");
	public final By ELEMENT_MOVE_QUESTION_RELOAD_DEFAULT_EMAIL = By.xpath("//*[@for='EmailMoveQuestion']/..//*[@class='uiIconRefresh uiIconLightGray']");
	
	/*************************************APPLICATION REGISTRY***************************************************************/
	public final By ELEMENT_APPLICATION_REGISTRY_PORTLET=By.id("UIApplicationRegistryPortlet");
	public final By ELEMENT_MANAGE_APPLICATION_BUTTON=By.xpath("//*[@class='uiIconManageApplication uiIconLightGray']");
    public final By ELEMENT_APPLICATION_REGISTRY_ADD_CATEGORY_BTN=By.xpath(".//*[contains(@class,'uiIconManageCategory uiIconLightGray')]");
    
    public final String ELEMENT_SELECT_RIGHT_PARENT_GROUP = "//*[@title='$group']";
    //Add category page
    public final By ELEMENT_ADD_CATEGORY_NAME=By.id("name");
    public final By ELEMENT_ADD_CATEGORY_DISPLAY_NAME=By.id("displayName");
    public final By ELEMENT_ADD_CATEGORY_DESCRIPTION=By.id("description");
    public final By ELEMENT_ADD_CATEGORY_SAVE_BTN=By.xpath(".//*[@id='UICategoryForm']//button[text()='Save']");
    public final By ELEMENT_ADD_CATEGORY_CANCEL_BTN=By.xpath(".//*[@id='UICategoryForm']//button[text()='Cancel']");
    public final By ELEMENT_ADD_CATEGORY_PERMISSION_TAB = By.xpath(".//*[contains(@data-target,'#categoryPermission-tab')]");
    public final By ELEMENT_ADD_CATEGORY_PERMISSION_PUBLIC_CHECKBOX= By.xpath(".//*[@id='publicMode']");
    public final By ELEMENT_ADD_CATEGORY_ADD_PERMISSION_BTN = By.xpath("//*[contains(text(),'Add Permission')]");
    
    public final String ELEMENT_EDIT_PORTLET_DELETE_PERMISSION_ICON = "//*[@id='PermissionGrid']//*[contains(@onclick,'$group')]/*[contains(@class,'uiIconDelete')]";
    //Application registry page
	public final By ELEMENT_SHOW_IMPORT_APPLICATION = By.id("showImport");
	public final By ELEMENT_IMPORT_ALL_APPLICATION=By.xpath("//*[@class='uiIconImport uiIconLightGray']");
	public final By ELEMENT_APPLICATION_GADGETBTN = By.cssSelector(".uiIconGadgets.uiIconLightGray");

	//Left panel
	public final String ELEMENT_LEFT_PANEL_ADD_APPLICATION_BTN=".//*[contains(@href,'#${category}')]/../..//*[@class='uiIconPlus uiIconLightGray']";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_NAME = ".//*[@id='${category}']//*[contains(@data-original-title,'${application}')]";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_DELETE_BTN =".//*[contains(@data-original-title,'${application}')]/..//*[contains(@class,'uiIconTrashMini uiIconLightGray')]";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_TAB=".//*[@id='ApplicationRegistryCategory']//*[@href='#${category}']";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_DELETE_BTN=".//*[@href='#${category}']/../..//*[contains(@class,'uiIconDelete uiIconLightGray')]";
	public final String ELEMENT_LEFT_PANEL_APPLICATION_CATEGORY_EDIT_BTN=".//*[@href='#${category}']/../..//*[contains(@class,'uiIconEdit uiIconLightGray')]";
	//Right panel Add Application
	public final By ELEMENT_RIGHT_PANEL_ADD_APPLICATION_DISPLAY_FILED=By.id("displayName");
	public final By ELEMENT_RIGHT_PANEL_ADD_APPLICATION_SAVE_BTN = By.xpath(".//*[@id='UIAddApplicationForm']//button[text()='Add']");
	public final By ELEMENT_RIGHT_PANEL_ADD_APPLICATION_SELECTMENU=By.xpath(".//*[@id='UIAddApplicationForm']//*[@class='selectbox']");
	public final String ELEMENT_RIGHT_PANEL_ADD_APPLICATION_RADIOBTN=".//*[contains(@id,'description')][text()='${des}']/../..//*[contains(@id,'label')][text()='${name}']/../..//input[@type='radio']";
	public final String ELEMENT_RIGHT_PANEL_ADD_APPLICATION_NAME=".//*[contains(@id,'description')][text()='${des}']/../..//*[contains(@id,'label')][text()='${name}']";
	
	//paging control
	public final String ELEMENT_PAGING_CONTROL_NUMBER = ".//*[@id='UIAddApplicationForm']//a[text()='${number}']";
	public final By ELEMENT_PAGING_CONTROL_NEXT_PAGE_ENABLED=By.xpath(".//*[@data-original-title='Next Page' and @href='javascript:void(0);']");

	//view detail a porlet
	public final String ELEMENT_DETAIL_PORTLET_BREADCRUMB = ".//*[contains(@class,'breadcrumb')]//*[contains(text(),'${disName}')]";
	public final String ELEMENT_DETAIL_PORTLET_DISPLAY_NAME = ".//strong[contains(@title,'${disName}')]";
	public final String ELEMENT_DETAIL_PORTLET_APPLICATION_NAME =".//*[contains(@title,'${appName}')]";
	public final String ELEMENT_DETAIL_PORTLET_DESCRIPTION = ".//span[contains(@title,'${des}')]";
	public final By ELEMENT_PERMISSION_FORM = By.cssSelector(".UIPermissionForm");
	
	/*************************************CONTENT DETAIL***************************************************************/
	// Edit popup
	final public By ELEMENT_CONTENT_DETAIL_EDIT_BTN = By.xpath("//*[@class='uiIconEdit uiIconWhite']");
	final public By ELEMENT_CONTENT_DETAIL_SAVE_BTN = By.xpath("//*[@class='btn' and text()='Save']");
	final public By ELEMENT_CONTENT_DETAIL_CLOSE_BTN = By.id("Close");
	final public By ELEMENT_CONTENT_DETAIL_ADDPATH_BTN = By.xpath("//*[@class='uiIconAddPath uiIconLightGray']");
	final public By ELEMENT_CONTENT_DETAIL_BY_CONTENT_MODE = By.xpath("//*[@class='radio' and @value='ManualViewerMode']");
	
	//Select Content popup
	final public String ELEMENT_SELECT_CONTENT_POPUP_NODE_FOLDER= ".//span[contains(text(),'${node}')]";
	final public String ELEMENT_SELECT_CONTENT_POPUP_FILE= ".//*[@id='ListRecords']//*[contains(text(),'${content}')]";
	final public By ELEMENT_SELECT_CONTENT_POPUP_SAVE_BTN=By.xpath(".//*[@id='tab-UIContentBrowsePanelMulti']//button[text()='Save']");

	//View content
	final public String ELEMENT_CONTENT_DETAIL_VIEW_CONTENT=".//h4[text()='${title}']";
	final public String ELEMENT_CONTENT_DETAIL_CONTENT_BOX_CONTENT_TITLE = "//*[@class='title']//*[text()='${title}']";
	final public By ELEMENT_CONTENT_DETAIL_CONTENT_BOX = By.xpath("//*[@class='uiContentBox']"); 
	final public String ELEMENT_CONTENT_DETAIL_CONTENT_BOX_PREFERENCES_BTN = "//*[contains(text(),'${title}')]/../../..//*[@data-original-title='Preferences']";
	
	/*************************************CONTENT LIST***************************************************************/
	public final By ELEMENT_EDIT_CLV = By.xpath("//*[@class='clv uiBox']");
	public final By ELEMENT_EDIT_PREFERENCE = By.xpath("//*[@class='UIPageBody']//*[@data-original-title='Preferences']");
	
	
	//Edit popup
	final public By ELEMENT_CONTENT_LIST_EDIT_BTN = By.xpath("//*[@class='uiIconEdit uiIconWhite']");
	final public By ELEMENT_CONTENT_LIST_SAVE_BTN = By.xpath("//*[@class='btn' and text()='Save']");
	final public By ELEMENT_CONTENT_LIST_CLOSE_BTN = By.id("Close");
	final public By ELEMENT_CONTENT_LIST_ADDPATH_BTN = By.xpath("//*[@class='uiIconAddPath uiIconLightGray']");
	final public By ELEMENT_CONTENT_LIST_BY_CONTENT_MODE = By.xpath("//*[@class='radio' and @value='ManualViewerMode']");
	final public By ELEMENT_CONTENT_LIST_BY_FOLDER_MODE = By.xpath("//*[@class='radio' and @value='AutoViewerMode']");
	
	//Conntent list Preference popup
	final public By ELEMENT_CONTENT_LIST_PREFERENCE_SAVE_BTN = By.xpath(".//*[@id='UICLVConfig']//a[text()='Save']");
	final public String ELEMENT_CONTENT_LIST_CONTENT_TITLE= "//*[@title='${title}']";
	
	
	//Multiple content selector popup
	final public String ELEMENT_MULTIPLE_CONTENT_POPUP_NODE_FOLDER= ".//span[contains(text(),'${node}')]";
	final public String ELEMENT_MULTIPLE_CONTENT_POPUP_FILE= ".//*[@id='ListRecords']//*[contains(text(),'${content}')]";
	final public By ELEMENT_MULTIPLE_CONTENT_POPUP_SAVE_BTN=By.xpath(".//*[@id='tab-UIContentBrowsePanelMulti']//button[text()='Save']");
	
	//Content Detail Preference
	final public By ELEMENT_CONTENT_LIST_DISPLAY_SETTING_TAB = By.xpath("//*[@data-target='#clvDisplayTab-tab']");
	
	//Content Detail Preference-->Display Setting tab
	final public By ELEMENT_DISPLAY_SETTING_TAB_HEADER_FIELD = By.xpath("//*[@id='UICLVConfigHeaderFormStringInput']");

    //View Content
	final public String ELEMENT_CONTENT_LIST_CONTENT_BOX_CONTENT_TITLE = "//*[@class='title']//*[text()='${title}']";
	final public By ELEMENT_CONTENT_LIST_CONTENT_BOX = By.xpath("//*[@class='uiContentBox']");
	final public By ELEMENT_CONTENT_LIST_CONTENT_BOX_PREFERENCES_BTN = By.xpath("//*[@class='uiContentBox']/../../../..//*[@data-original-title='Preferences']");
	
	/*************************************DISABLE USER***************************************************************/
	//Drop box
    public By ELEMENT_DISABLE_USER_DROP_BOX=By.id("UIListUsers-userStatusFilter");
    
    /*************************************EDIT PORTLET***************************************************************/
  //Edit portlet form
  	public final By ELEMENT_EDIT_PORTLET_FORM=By.id("tab-UIPortletForm");
  	public final By ELEMENT_EDIT_PORTLET_FORM_CLOSE_BUTTON=By.xpath(".//*[@id='Close']");
  	public final By ELEMENT_EDIT_PORTLET_FORM_SAVE_BUTTON=By.xpath(".//*[@id='Save']");
  	public final By ELEMENT_EDIT_PORTLET_FORM_SAVESETTINGS_BUTTON=By.xpath("//*[@id='tab-UIPortletForm']//*[text()='Save Settings']");
  	public final By ELEMENT_EDIT_PORTLET_FORM_SAVE_AND_CLOSE_BUTTON=By.xpath("//*[@id='tab-UIPortletForm']//*[text()='Save And Close']");
  	public final By ELEMENT_EDIT_PORTLET_FORM_CANCEL_BUTTON=By.xpath("//*[@id='tab-UIPortletForm']//*[text()='Cancel']");
  	public final By ELEMENT_EDIT_PORTLET_FORM_RESULTPERPAGE = By.xpath("//*[@id='resultsPerPage']");
  	
  	//Permission tab
  	public final By ELEMENT_PORTLET_ACCESS_PERMISSION_TAB=By.xpath(".//*[@id='tab-UIPortletForm']//*[@data-target='#PortletPermission-tab']");
  	public final String ELEMENT_PORTLET_ACCESS_PERMISSION_DELETE_GROUP_BTN=".//*[@id='PermissionGrid']//*[contains(text(),'$group')]/../..//*[contains(@class,'uiIconDelete')]";
  	public final String ELEMENT_CONFIRM_DELETE_MESSAGE="Are you sure you want to delete this Access Group?";
  	public final By ELEMENT_PORTLET_ACCESS_PERMISSION_ADD_BTN =By.xpath(".//*[contains(@class,'uiAccessGroup')]//*[contains(@class,'uiAction')]//*[contains(@class,'uiIconAddUser')]");
  	public final By ELEMENT_PORTLET_SELECT_PERMISSION_POPUP=By.xpath(".//*[contains(@class,'PopupContent')]");
  	public final String ELEMENT_PORTLET_SELECT_PERMISSION_GROUP_MEMBERSHIP_NAME=".//*[contains(@class,'PopupContent')]//*[@title='$name']";
  	public final String ELEMENT_PORTLET_ACCESS_PERMISSION_GROUP_NAME=".//*[@id='PermissionGrid']//*[contains(text(),'$group')]";
  	
    /*************************************FQA PAGE***************************************************************/
  //Template tab
  	public final By ELEMENT_FAQ_EDIT_TEMPLATE_TAB = By.xpath("//button[contains(text(), 'Edit Template')]");
  	public final By ELEMENT_FAQ_EDIT_TEMP_INPUT = By.id("ContentTemplate");
  	
  	public final By ELEMENT_EDIT_SAVE_BUTTON=By.xpath("//*[text()='Save']");
  	public final By ELEMENT_EDIT_CLOSE_BUTTON=By.xpath("//*[text()='Close']");
  	
    /*************************************GADGET MANAGEMENT***************************************************************/
  //Remote gadget on righ panel
    public final By ELEMENT_REMOTE_GADGETBTN = By.xpath(".//*[@id='UIGadgetManagement']//a[contains(.,'Add a remote gadget')]");
	public final By ELEMENT_REMOTE_GADGET_URL = By.id("url");
	public final By ELEMENT_REMOTE_GADGET_ADDBTN = By.xpath(".//*[@id='UIAddGadget']//button[text()='Add']");
	public final By ELEMENT_REMOTE_GADGET_ADD_INTO_CATEGORY_LINK = By.xpath(".//*[@id='UIGadgetInfo']//a[contains(text(),'Click here to add into categories')]");
	public final By ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES=By.xpath(".//*[@id='GadgetCategory']//div[@class='controls-full']");
	public final String ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_CHECKBOX = ".//*[@id='categoryName'][contains(text(),'${category}')]/../..//input[@type='checkbox']";
	public final By ELEMENT_REMOTE_GADGET_TABLE_CATEGORIES_SAVEBTN = By.xpath(".//*[@id='GadgetCategory']//button[text()='Save']");
	public final String ELEMENT_REMOTE_GADGET_INFO_CATEGORY =".//*[text()='Categories:']/..//*[contains(text(),'${category}')]";
	//left panel
	public final String ELEMENT_REMOTE_GADGET_LEFT_CONTENT=".//*[contains(text(),'${name}')]";
	public final String ELEMENT_REMOTE_GADGET_LEFT_DELETE_BTN = ".//*[contains(text(),'${name}')]/../../../..//*[@class='uiIconDelete uiIconLightGray']";

	//Create new gadget
	public final By ELEMENT_CREATE_NEW_GADGET_BTN = By.cssSelector(".uiIconCreateNewGadget.uiIconLightGray");
	public final By ELEMENT_CREATE_NEW_GADGET_INPUT_NAME_FIELD= By.id("name");
	public final By ELEMENT_CREATE_NEW_GADGET_SOURCE_FIELD= By.id("source");
	public final By ELEMENT_CREATE_NEW_GADGET_SAVE_BTN =By.xpath(".//button[text()='Save']");
	
	//View detail of a Gadget
	public final By ELEMENT_GADGET_EDIT_BTN = By.cssSelector(".uiIconEdit.uiIconLightGray");
	
    /*************************************MY DASHBOARD***************************************************************/
	public final By ELEMENT_DASHBOARD_WORKSPACE_POPUP_TITLE=By.xpath(".//*[@class='PopupTitle popupTitle'][contains(text(),'Dashboard Workspace')]");
    public final By ELEMENT_DASHBOARD_WORKSPACE_POPUP_CLOSE=By.xpath(".//*[contains(@class,'UIPopupWindow')]//*[contains(text(),'Dashboard Workspace')]/..//*[contains(@class,'uiIconClose')]");
	//tab
	public final By ELEMENT_MYDASH_BTN_ADDTAB = By.xpath("//*[@class='uiIconSimplePlusMini uiIconLightGray']");
	public final String ELEMENT_MYDASH_TAB_NAME = "//*[@id='${name}']";
	public final String ELEMENT_MYDASH_BTN_NAMETAB = "//*[@value='${name}']";
	public final String ELEMENT_MYDASH_BTN_DELETETAB = "//*[@id='${name}']/../../..//*[contains(@class,'uiIconClose')]";

	public final By ELEMENT_MYDASH_BTN_ADDGADGET = By.xpath(".//*[@id='GadgetContainer']//*[contains(text(),'Add Gadgets')]");
	public final String  ELEMENT_MYDASH_GADGET_NAME = "//*[@class='GadgetTitle' and @title='${name}']";
	public final String ELEMENT_MYDASH_ADDED_GADGET_IN_DASHBOARD=".//*[@class='gadgetTitle'][contains(text(),'${name}')]";
	public final String ELEMENT_MYDASH_DELETE_GADGET = ".//*[@class='gadgetTitle'][contains(text(),'${name}')]/..//*[contains(@class,'uiIconClose')][1]";
	public final By ELEMENT_MYDASH_GADGET_FORUMPOST = By.xpath("//*[@class='GadgetTitle' and @title='Latest Forum Posts']");
	public final By ELEMENT_MYDASH_GADGET_CONNECTIONHISTORY = By.xpath("//*[@class='GadgetTitle' and @title='Login History']");
	public final String ELEMENT_MYDASH_GADGET_COLUMN = "//*[@class='UIColumn'][${number}]";

	public final By ELEMENT_MYDASH_GADGETDISPLAYED_FORUMPOST = By.xpath("//*[@class='gadgetTitle' and text()='Lastest Forum Posts']");
	public final By ELEMENT_MYDASH_GADGETDISPLAYED_LOGINHISTORY = By.xpath("//*[@class='gadgetTitle' and text()='Login History']");
	//public final By ELEMENT_MYDASH_GADGETDISPLAYED_OTHERS = By.xpath("//*[@class='gadgetTitle' and text()='My Stock Portfolio']");
	public final By ELEMENT_MYDASH_GADGETDISPLAYED_GADGETCONTROL = By.xpath("//*[@class='GadgetDragHandleArea uiIconDragDrop']");
	public final By ELEMENT_MYDASH_GADGET_BYURL = By.xpath("//*[@id='url']");
	public final By ELEMENT_MYDASH_GADGET_ADDBTN = By.xpath(".//*[@id='UIAddGadgetForm']//*[contains(@class,'uiIconPlus')]");
	public final By ELEMENT_MYDASH_BTN_EDITGADGET = By.xpath("//*[@class='uiIconEditMini uiIconLightGray']");
	public final By ELEMENT_MYDASH_BTN_DELETEGADGET = By.xpath("//*[@class='GadgetDragHandleArea uiIconDragDrop']/..//*[@class='uiIconClose uiIconLightGray']");

	public final By ELEMENT_MYDASH_LOGINHISTORY_EDIT_HISTORYTAB = By.xpath("//div/button[3]");
	public final By ELEMENT_MYDASH_LOGINHISTORY_EDIT_HISTORYTAB_EARLIER = By.xpath("//*[text()='Earlier (15)']");
   
	/*************************************NAVIGATION MANAGEMENT***************************************************************/
    
	public final By ELEMENT_MANAGESITES_TITLE=By.xpath(".//*[@id='UIPortalNavigationPortlet']//h5[text()='Manage Sites']");
	public final String ELEMENT_MANAGESITES_EDIT_NAVIGATION_ICON=".//*[@class='managementBlock']//div[text()='${site}']/../..//*[@class='uiIconNavigation uiIconLightGray']";

	public final String ELEMENT_MANAGESITES_EDIT_LAYOUT_ICON=".//*[@class='managementBlock']//div[text()='${site}']/../..//*[contains(@class,'uiIconEditLayout')]";
	public final String ELEMENT_MANAGESITES_EDIT_CONFIG_ICON=".//*[@class='managementBlock']//div[text()='${site}']/../..//*[contains(@class,'uiIconEditPortalConfig')]";
	
	public final By ELEMENT_MANAGESITES_ADD_NEW_BTN=By.cssSelector("#UISiteManagement .btn");
	public final By ELEMENT_MANAGESITES_EDIT_LAYOUT_SITE_CONFIG_BTN = By.cssSelector(".PageProfileIcon");
	public final String ELEMENT_MANAGESITES_PORTAL_LABEL =".//*[contains(@class,'siteName')][contains(text(),'${portal}')]/..//*[contains(@class,'siteLabel')][contains(text(),'${label}')]";
	public final String ELEMENT_MANAGESITES_PORTAL_DESC =".//*[contains(@class,'siteName')][contains(text(),'${portal}')]/..//*[contains(@class,'siteDescription')][contains(text(),'${desc}')]";

	public final String ELEMENT_ADD_NAVIGATION_BUTTON = "//*[contains(text(),'Add Navigation')]";

	//Navigation Management popup
	public final By ELEMENT_NAVIGATION_MANAGEMENT_POPUP_TITLE=By.xpath(".//*[@class='PopupTitle popupTitle'][text()='Navigation Management']");
	public final String ELEMENT_NAVIGATION_MANAGEMENT_NODE_NAME=".//*[@title='${name}']";
	public final By ELEMENT_NAVIGATION_MANAGEMENT_SAVE = By.xpath(".//*[text()='Save']");
	public final String ELEMENT_NAVIGATION_SPECIFIC_NODE ="//*[@id='UINavigationNodeSelector']//*[@class='uiIconFileMini uiIconLightGray' ]/../..//*[contains(text(),'${name}')]";
	public final String ELEMENT_NAVIGATION_SUB_NODE_CHECK = "//*[@id='UINavigationNodeSelector']//*[@class='uiIconNode collapseIcon' and contains(text(),'{$node}')]";
	public final String ELEMENT_NAVIGATION_PARENT_CHILD_NODE =".//*[contains(@class,'uiIconNode')][@title='${parent}']/..//*[contains(@class,'childrenContainer')]//*[contains(@title,'${child}')]";
	public final String ELEMENT_NAVIGATION_NUMBER_CHILD_NODES=".//*[contains(@class,'uiIconNode')][@title='${parent}']/..//*[contains(@class,'childrenContainer')][count(*)=${numberChild}]";
	public final String ELEMENT_NAVIGATION_PARENT_NODE =".//*[contains(@class,'treeContainer')]//*[contains(@class,'nodeGroup')]//*[contains(@title,'${parent}')]";
	public final String ELEMENT_NAVIGATION_PREVIOUS_NODE=".//*[contains(@class,'node')]/*[contains(@title,'${currentNode}')]/..//preceding-sibling::*[contains(@class,'node')]/*[contains(@title,'${previousNode}')]";
	public final String ELEMENT_NAVIGATION_NEXT_NODE=".//*[contains(@class,'node')]/*[contains(@title,'${currentNode}')]/..//following-sibling::*[contains(@class,'node')]/*[contains(@title,'${nextNode}')]";
	//Add new portal popup
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_NAME=By.cssSelector("#name");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_LABEL=By.cssSelector("#label");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_DESC=By.cssSelector("#description");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_LOCALE=By.cssSelector("#PortalSetting-tab .selectbox[name~='locale']");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_SITE=By.cssSelector("#PortalSetting-tab .selectbox[name~='skin']");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_SAVE_BTN=By.xpath(".//*[@id='UIPortalForm']//button[text()='Save']");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_PUBLIC_PERMISSION=By.cssSelector("#publicMode");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_PERMISSION_TAB = By.xpath(".//*[contains(@data-target,'#PermissionSetting-tab')]");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_EDIT_PERMISSITION_SETTINGS= By.xpath(".//*[contains(text(),'Edit Permission Settings')]");
	public final By ELEMENT_ADD_NEW_PORTAL_POPUP_SELECT_PERMISSION_BTN= By.xpath(".//*[contains(text(),'Select Permission')]");
	
	//Permission selector
	public final String ELEMENT_PERMISSION_SELECTOR_POPUP_GROUP = ".//*[contains(@class,'uiIconNode')][contains(@title,'${group}')]";
	public final String ELEMENT_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP = ".//*[@id='PermissionSelector']//*[contains(@title,'${member}')]";

	//Contextmenu
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_DELETE_ICON= By.xpath(".//*[@id='NavigationNodePopupMenu']//*[@class='uiIconDeleteNode']");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_EDIT_ICON= By.cssSelector(".ContainerConfigOptions .uiIconEditSelectedNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_EDIT_NODE_PAGE_ICON= By.cssSelector(".ContainerConfigOptions .uiIconEditPageNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_COPY_ICON= By.cssSelector(".ContainerConfigOptions .uiIconCopyNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_PASTE_ICON= By.cssSelector(".ContainerConfigOptions .uiIconPasteNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_CUT_ICON= By.cssSelector(".ContainerConfigOptions .uiIconCutNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_CLONE_ICON= By.cssSelector(".ContainerConfigOptions .uiIconCloneNode");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_UP_ICON= By.cssSelector(".ContainerConfigOptions .uiIconMoveUp");
	public final By ELEMENT_MANAGESITES_CONTEXTMENU_MOVE_DOWN_ICON= By.cssSelector(".ContainerConfigOptions .uiIconMoveDown");
	
	// new node
	public final By ELEMENT_ADD_NODE_FORM = By.id("UIPageNodeForm");
	public final By ELEMENT_BACK_BUTTON = By.xpath("//*[text()='Back']");
	public final By ELEMENT_UP_LEVEL_PATH_NODE = By.xpath("//*[@id='UINavigationNodeSelector']//*[@class='uiIconUpLevel uiIconLightGray']");
	public final By ELEMENT_ADD_NODE = By.xpath("//*[@id='UINavigationManagement']/..//*[contains(text(),'Add Node')]");
	public final By ELEMENT_SAVE_NODE = By.xpath("//*[@id='UINavigationManagement']/..//*[contains(text(),'Save')]");
	public final By ELEMENT_NODE_NAME = By.id("name");
	public final By ELEMENT_NODE_PAGE_SELECTOR_TAB=By.cssSelector("a[data-target='#UIPageSelector-tab']");
	public final By ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGES_BTN = By.cssSelector("#SelectPage .uiIconSelectPage");
	
	//new node-->Page Node Setting
	public final By ELEMENT_NODE_SETTING_SWITCH_MODE=By.cssSelector("#switchmode");
	public final By ELEMENT_NODE_SETTING_LANGUAGE_BOX=By.cssSelector(".selectbox");
	public final By ELEMENT_NODE_SETTING_LABEL_FIELD_1=By.cssSelector("#i18nizedLabel");
	public final By ELEMENT_NODE_SETTING_LABEL_FIELD_2=By.cssSelector("#label");
	public final By ELEMENT_NODE_SETTING_VISIBLE=By.cssSelector("#visible");
	public final By ELEMENT_NODE_SETTING_PUBLISH_DATE_TIME=By.cssSelector("#showPublicationDate");
	public final By ELEMENT_NODE_SETTING_VISIBLE_CHECKED=By.cssSelector("#visible[checked='']");
	public final By ELEMENT_NODE_SETTING_SWITCH_MODE_CHECKED=By.cssSelector("#switchmode[checked='']");
	
	//new node-->Page selector
	public final By ELEMENT_NODE_PAGE_SELECTOR_PAGE_NAME=By.cssSelector("#pageName");
	public final By ELEMENT_NODE_PAGE_SELECTOR_PAGE_TITLE=By.cssSelector("#pageTitle");
	public final By ELEMENT_NODE_PAGE_SELECTOR_CREATE_PAGE_BTN=By.xpath(".//*[@id='UIPageSelector']//button[1]");
	public final By ELEMENT_NODE_PAGE_SELECTOR_SELECT_PAGE_BTN=By.xpath(".//*[@id='UIPageSelector']//button[2]");
	public final By ELEMENT_NODE_PAGE_SELECTOR_CLEAR_PAGE_BTN=By.xpath(".//*[@id='UIPageSelector']//button[3]");
	
	public final By ELEMENT_NODE_PAGE_SELECTOR_TITLE_FIELD=By.cssSelector("#UIPageSearchForm #pageTitle");
	public final By ELEMENT_NODE_PAGE_SELECTOR_SITES_NAME_FIELD=By.cssSelector("#UIPageSearchForm #siteName");
	public final By ELEMENT_NODE_PAGE_SELECTOR_TYPE_DROPBOX=By.cssSelector("#UIPageSearchForm .selectbox");
	public final String ELEMENT_NODE_PAGE_SELECTOR_SELECT_TYPE=".//*[@name='searchOption']//*[contains(@value,'${type}')]";
	public final By ELEMENT_NODE_PAGE_SELECTOR_SEARCH_BUTTON = By.cssSelector("#UIPageSearchForm .uiIconSearch");
	
    /*************************************PAGE CREATION WINZARD***************************************************************/
	//Common
	public final By ELEMENT_PAGE_CREATION_WIZARD = By.id("UIPageCreationWizard");
	public final By ELEMENT_PAGE_NEXT_BUTTON=By.xpath("//*[@id='UIPageCreationWizard']//*[text()='Next']");
	public final By ELEMENT_PAGE_ABORT_BUTTON=By.xpath(".//*[@id='UIPageEditor']//*[@data-original-title='Abort']");
	public final By ELEMENT_WARNING_PAGE_NOT_FOUND=By.xpath("//*[@class='TitleWaring' and text()='Page not found.']");
	final public By ELEMENT_ADDNEWPAGE_BTNNEXT = By.xpath("//*[@class='btn btn-primary' and text()='Next']");
	public final By ELEMENT_CONFIRM_YES_BUTTON = By.xpath(".//*[@id='UIConfirmation']//*[@class='btn' and contains(text(),'Yes')]");
	public final By ELEMENT_SAVE_BTN_2 = By.xpath(".//*[@id='UIContainerForm']//*[text()='Save']");
	//Step 1
	public final By ELEMENT_PAGE_SETUP_INFO_FORM=By.id("UIWizardPageSetInfo");
	public final By ELEMENT_PAGE_NAME_INPUT=By.id("pageName");
	public final By ELEMENT_PAGE_MODE_CHECKBOX=By.id("switchmode");
	public final By ELEMENT_PAGE_LANGUAGE_SELECT_BOX=By.name("languages");
	public final By ELEMENT_PAGE_DISPLAY_NAME_INPUT=By.id("i18nizedLabel");
	public final By ELEMENT_PAGE_VISIBLE_CHECKBOX=By.id("visible");
	public final By ELEMENT_PAGE_PUBLICATION_DATE_CHECKBOX=By.id("showPublicationDate");
	public final By ELEMENT_PAGE_UP_LEVEL=By.xpath("//*[@class='uiIconUpLevel uiIconLightGray']");
	public final String ELEENT_NODE_NAME="//*[@title='$name']";

	//Step 2
	public final By ELEMENT_PAGE_SELECT_LAYOUT_FORM = By.id("UIWizardPageSelectLayoutForm");

	//Step 3
	public final By ELEMENT_PAGE_PORTAL_EDITOR=By.id("UIPortalApplication");

	//Page Editor left side bar header
	public final By ELEMENT_PAGE_FINISH_BTN = By.xpath("//*[@class='uiIconSave uiIconDarkGray pull-right']");
	public final By ELEMENT_PAGE_ABORT_BTN = By.xpath(".//*[@id='UIPageEditor']//*[@data-original-title='Abort']");

	//Application panel
	public final By ELEMENT_APPLICATION_TAB = By.xpath(".//*[@data-target='#appList']");
	public final By ELEMENT_APPLICATION_TAB_ACTIVE = By.xpath("//*[@class='active']/*[@data-target='#appList']");
	public final By ELEMENT_APPLICATION_CONTENT_TAB = By.xpath("//*[@title='Content']");
	public final By ELEMENT_APPLICATION_ADMINISTRATION_TAB=By.xpath("//*[@title='Administration']");
	public final String ELEMENT_APPLICATION_SUB_TAB = ".//*[@id='UIApplicationList']//*[contains(@title,'${tabName}')]";
	public final By ELEMENT_APPLICATION_CONTENT_DETAIL = By.xpath("//*[@id='Content/SingleContentViewer']");
	public final By ELEMENT_APPLICATION_CONTENT_LIST = By.xpath("//*[@id='Content/ContentListViewerPortlet']");
	public final String ELEMENT_APPLICATION_APPLICATION = ".//*[@id='${name}']";
	public final String ELEMENT_APPLICATION_REMOTE_GADGET = ".//*[@id='UIApplicationList']//*[contains(text(),'${name}')]";
	public final String ELEMENT_APPLICATION_CATEGORY = "//*[@title='${applicationCategor}']/i";
	public final String ELEMENT_APPLICATION_ID = ".//*[@id='${applicationId}']";
	public final String ELEMENT_NAME_PORTLET = "//*[@class='portletName' and contains(text(), '${portletName}')]";
	public final String ELEMENT_PORTLET_FRAGMENT = "//*[@id='${portletName}']/ancestor::div[contains(@class, 'UIApplication')]";
	final public By ELEMENT_PORTLET_FRAGMENT_2 = By.xpath("//*[contains(@class,'PORTLET-FRAGMENT UIResizableBlock UIApplication')]");
	final public By ELEMENT_UIWINDOW_DEFAULT_THEME = By.xpath("//*[contains(@class,'UIWindow DefaultTheme UIDragObject UIResizeObject')]");
	public final String ELEMENT_FORUM_PORTLET_IN_VIEW_PAGE = "//*[@class='portletName' and contains(text(),'Forum')]";

	public final String ELEMENT_PORTLET_VIEW_PAGE = "//*[@class='VIEW-PAGE']";

	//Container panel
	public final By ELEMENT_CONTAINER_TAB = By.linkText("Containers");
	public final By ELEMENT_SWITCH_VIEW_MODE = By.linkText("Switch View mode");
	public final By ELEMENT_VIEW_PROPERTIES = By.cssSelector(".PageProfileIcon");
	public final By ELEMENT_DROP_TARGET_HAS_LAYOUT = By.xpath("//div[@class='UIRowContainer EmptyContainer']");
	public final String ELEMENT_DRAG_CONTAINER = "//*[@title='Hold this area to drag this container']";
	public final String ELEMENT_DRAG_CONTAINER_PLF41 = "//*[@data-original-title='Hold this area to drag this container']";
	public final String ELEMENT_NAME_CONTAINER = ELEMENT_DRAG_CONTAINER + "/../*[text()='${nameContainer}']";
	public final String ELEMENT_NAME_CONTAINER_PLF41 = ELEMENT_DRAG_CONTAINER_PLF41 + "/../*[text()='${nameContainer}']";
	public final By ELEMENT_EDITING_CONTAINER = By.xpath("//div[@class='UIRowContainer EmptyContainer']/ancestor::div[contains(@class, 'EdittingContainer')]");
	public final String ELEMENT_LIST_CONTAINER = "//*[@class='UIRowContainer']/div[${number}]//*[contains(text(), '${nameContainer}')]";
	public final String ELEMENT_DRAG_CURRENT_CONTAINER = "//*[text()='${nameContainer}']/../*[@data-original-title='Hold this area to drag this container']";
	public final By ELEMENT_PORTLET_LAYOUT_DECORATOR = By.className("portletLayoutDecorator");

	//Container popup editor
	public final By ELEMENT_CONTAINER_POPUP_TITLE = By.name("title");
	public final String ELEMENT_CONTAINER_TITLE="//*[@class='UIRowContainer']//span[text()='${title}']";
	public final By ELEMENT_CONTAINER_POPUP_WIDTH = By.name("width");
	public final By ELEMENT_CONTAINER_POPUP_HEIGHT = By.name("height");

	//View properties popup
	public final By ELEMENT_VIEW_PROPERTIES_POPUP = By.cssSelector(".MaskContainer");
	public final By ELEMENT_VIEW_PROPERTIES_TITLE = By.id("title");
	public final String ELEMENT_EDIT_PERMISSION_SELECTOR_POPUP_GROUP = ".//*[@id='PermissionSelector']//*[contains(@class,'uiIconNode')][contains(@title,'${group}')]";
	public final String ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_GROUP = "//*[contains(@class,'uiIconNode')][contains(@title,'${group}')]";
	public final String ELEMENT_EDIT_PERMISSION_MOVE_APPS_SELECT= "//*[contains(@id,'UIListMoveAppsPermissionSelector')]//*[contains(@title,'${group}')]";
	public final String ELEMENT_EDIT_PERMISSION_MOVE_CONTAINERS_SELECT= "//*[contains(@id,'UIListMoveContainersPermissionSelector')]//*[contains(@title,'${group}')]";
	public final String ELEMENT_ADD_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP = "//*[contains(@id,'UIListPermissionSelector')]//*[contains(@title,'${member}')]";
	public final String ELEMENT_EDIT_PERMISSION_SELECTOR_POPUP_MEMEBRSHIP = ".//*[@id='PermissionSelector']//*[contains(@title,'${member}')]";
	public final By ELEMENT_VIEW_PROPERTIES_SHOW_MAX_WINDOW = By.cssSelector("#showMaxWindow");
	public final By ELEMENT_VIEW_PROPERTIES_SAVE_BTN = By.xpath(".//*[@id='UIPageForm']//button[text()='Save']");
	public final String ELEMENT_VIEW_PROPERTIES_ACCESS_PERMISSTION_VALUE=".//*[@id='PermissionGrid']//*[contains(text(),'${group}')]";
	public final String ELEMENT_VIEW_PROPERTIES_GROUP_REMOVE_BTN=".//*[@id='PermissionGrid']//*[contains(text(),'${group}')]/../..//*[contains(@class,'uiIconDelete')]";
	public final By ELEMENT_VIEW_PROPERTIES_DELETE_EDIT_PERMISSION_BTN=By.xpath(".//*[@id='UIPermissionSelector']//*[contains(text(),'Delete Permission')]");
	public final By ELEMENT_VIEW_PROPERTIES_PERMISSION_TAB = By.xpath(".//*[contains(@data-target,'#PermissionSetting-tab')]");
	public final By ELEMENT_VIEW_PROPERTIES_EDIT_PERMISSITION_SETTINGS= By.xpath(".//*[@id='PermissionSetting']//a[contains(.,'Edit')]");
	public final By ELEMENT_VIEW_PROPERTIES_SELECT_PERMISSION_BTN= By.xpath(".//*[contains(text(),'Select Permission')]");
	public final By ELEMENT_VIEW_PROPERTIES_ADD_PERMISSION_BTN= By.cssSelector(".uiIconAddUser");
	
	//Layout
	public  final By ELEMENT_PAGEEDITOR_VIEWPAGE = By.xpath("//*[@class='VIEW-PAGE']");
	public final String ELEMENT_PAGEEDITOR_CONTENT=".//*[@class='UIComponentBlock']//*[contains(text(),'${name}')]";
	public final By ELEMENT_PAGEEDITOR_FINISHBTN = By.xpath("//*[contains(@class,'uiIconSave')]");
	public final By ELEMENT_SWITCH_VIEW_MODE_NAME_APPLICATION_CLASS = By.xpath(".//*[contains(@class,'portletName')]");


	public final String ELEMENT_APPLICATION_IN_LAYOUT_PAGE = "//*[contains(@class,'LAYOUT-PORTLET')]//*[contains(text(),'${name}')]";
	public final String ELEMENT_APPLICATION_EDIT_ICON = "//span[contains(text(),'${name}')]/../../../..//*[contains(@class,'uiIconEdit')]";
	public final String ELEMENT_APPLICATION_DELETE_ICON = "//span[contains(text(),'${name}')]/../../../..//*[contains(@class,'uiIconTrash')]";

	public final By ELEMENT_DROP_SOURCE_HAS_LAYOUT = By.xpath("//div[@class='UIRowContainer EmptyContainer']");
	public final String ELEMENT_DROP_SOURCE_HAS_LAYOUT_BY_NAME ="//span[contains(text(),'${name}')]/../../../..//*[contains(@class,'UIRowContainer')]";
	public final By ELEMENT_EDIT_CONTAINER_ICON = By.xpath(".//*[@data-original-title='Edit Container']//*[contains(@class,'uiIconEdit')]");
	public final By ELEMENT_DELETE_CONTAINER_ICON = By.xpath(".//*[@data-original-title='Edit Container']/..//*[contains(@class,'uiIconTrash')]");
	public final String ELEMENT_EDIT_CONTAINER_ICON_BY_NAME = "//span[contains(text(),'${name}')]/..//*[contains(@class,'uiIconEdit')]";
	public final String ELEMENT_DELETE_CONTAINER_ICON_BY_NAME = "//span[contains(text(),'${name}')]/..//*[contains(@class,'uiIconTrash')]";
	public final String ELEMENT_EDITED_COTAINER = "//*[contains(@class, 'UIContainer EdittingContainer')]";

	public final String ELEMENT_CONTAINER_PORTLET_APPLICATION="//*[contains(@class,'UIPortlet')]//div[contains(text(),'${name}')]";
	public final By ELEMENT_CONTAINER_PRECEDING_PORTLET = By.xpath("//*[contains(@class,'UIPortlet')]/preceding-sibling::*[contains(@class,'UIContainer')]");
	public final By ELEMENT_CONTAINER_FOLLOWING_PORTLET= By.xpath("//*[contains(@class,'UIPortlet')]/following-sibling::*[contains(@class,'UIContainer')]");
	public final By ELEMENT_CONTAINER_HOLDER_MOVE = By.xpath(".//*[@class='UIRowContainer EmptyContainer']/../../../..//*[contains(@class,'uiIconDragDrop')]");
	public final By ELEMENT_PORTLET = By.xpath(".//*[contains(@class,'UIPortlet')]");
	public final String ELEMENT_APPLICATION_HOLDER_MOVE="//span[contains(text(),'${name}')]/..//*[contains(@class,'uiIconDragDrop')]";
	public final String ELEMENT_APPLICATION_PRECEDING_PORTLET = "//*[contains(@class,'LAYOUT-PORTLET')]//*[contains(text(),'${app1}')]/../../../preceding-sibling::*//*[contains(@class,'LAYOUT-PORTLET')]//*[contains(text(),'${app2}')]";
	public final String ELEMENT_APPLICATION_FOLLOWING_PORTLET= "//*[contains(@class,'LAYOUT-PORTLET')]//*[contains(text(),'${app1}')]/../../../following-sibling::*//*[contains(@class,'LAYOUT-PORTLET')]//*[contains(text(),'${app2}')]";

	//Application popup
	public final By ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_TAB = By.xpath(".//*[@id='tab-UIPortletForm']//*[contains(@data-target,'PortletSetting')]");
	public final By ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_TITLE = By.id("title");
	public final By ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_WIDTH = By.id("width");
	public final By ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_HEIGHT = By.id("height");
	public final By ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_SHOWINFOBAR = By.id("showInfoBar");
	public final By ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_SHOWPORTLET_MODE = By.id("showPortletMode");
	public final By ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_SHOWWINDOW_STATE = By.id("showWindowState");
	public final By ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_DESCRIPTION = By.id("description");
	public final By ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_SAVE = By.id("Save");
	public final By ELEMENT_APPLICATION_EDIT_POPUP_PORTLET_CANCEL = By.id("Close");

	final public By ELEMENT_ADDNEWPAGE_NODENAME = By.xpath("//*[@id='pageName']");
	final public By ELEMENT_ADDNEWPAGE_DISPLAYNAME = By.xpath("//*[@id='i18nizedLabel']");

	//page editor
	final public By ELEMENT_PAGEEDITOR_CONTENTTAB = By.xpath("//*[@title='Content']");
	final public By ELEMENT_PAGEEDITOR_CONTENTLIST = By.xpath("//*[@id='Content/ContentListViewerPortlet']");
	final public By ELEMENT_PAGEEDITOR_CONTENTDETAIL = By.xpath("//*[@id='Content/SingleContentViewer']");
	final public By ELEMENT_PAGEEDITOR_FORUM = By.xpath("//*[@class='LAYOUT-BLOCK LAYOUT-PORTLET']");
	final public By ELEMENT_PAGEEDITOR_EDITELEMENT = By.xpath("//*[@class='uiIconEdit uiIconWhite']");
	final public By ELEMENT_PAGEEDITOR_ADDPATHBTN = By.xpath("//*[@class='uiIconAddPath uiIconLightGray']");
	final public By ELEMENT_PAGEEDITOR_SAVEBTN = By.xpath("//*[@class='btn' and text()='Save']");
	final public By ELEMENT_PAGEEDITOR_OKBTN = By.xpath("//*[@class='btn' and text()='OK']");
	final public By ELEMENT_PAGEEDITOR_CLOSEBTN = By.xpath("//*[@class='btn' and text()='Close']");
	final public By ELEMENT_PAGEEDITOR_FINISHLIGHTBTN = By.xpath("//*[@class='uiIconSave uiIconLightGray pull-right']");
	//final public By ELEMENT_PAGEEDITOR_FINISHBTN = By.xpath("//*[contains(@class,'uiIconSave')]");
	final public By ELEMENT_PAGEEDITOR_BYCONTENTRADIOBN = By.xpath("//*[@class='radio' and @value='ManualViewerMode']");
	final public By ELEMENT_PAGEEDITOR_ACCESS_PERM_BTN= By.xpath("//*[@data-target='#PortletPermission-tab']");
	public final By ELEMENT_PAGEEDITOR_ACCESS_PUBLIC_CHECKBOX= By.xpath("//*[contains(@id,'UIListPermissionSelector')]//*[@id='publicMode']");
	public final By ELEMENT_PAGEEDITOR_MOVE_APPS_PUBLIC_CHECKBOX= By.xpath(".//*[contains(@id,'UIListMoveAppsPermissionSelector')]//*[@id='publicMode']");
	public final By ELEMENT_PAGEEDITOR_MOVE_CONTAINERS_PUBLIC_CHECKBOX= By.xpath("//*[contains(@id,'UIListMoveContainersPermissionSelector')]//*[@id='publicMode']");
	//Multiple content selector
	final public String ELEMENT_FOLDERSELECTOR_PATH = "//*[@class='nodeName' and text()=' ${path}' ]";
	final public String ELEMENT_FOLDERSELECTOR_CONTENTLIST_FINALPATH = "//*[@class='Item' and text()='${name}']";
	final public String ELEMENT_FOLDERSELECTOR_CONTENTDETAIL_FINALPATH = "//*[@class='OddItem']//*[text()='${name}']";

	//Content Detail Preference
	final public By ELEMENT_CONTENTDETAILPREF_TABCONTENTDISPLAY = By.xpath("//*[@class='uiContentBox']");
	final public By ELEMENT_CONTENTDETAILPREF_TABCONTENTPREFERENCES = By.xpath("//*[@data-original-title='Preferences']");
	final public By ELEMENT_CONTENTDETAILPREF_TABDISPLAYSETTINGS = By.xpath("//*[@data-target='#clvDisplayTab-tab']");
	final public By ELEMENT_CONTENTDETAILPREF_HEADERTXTBOX = By.xpath("//*[@id='UICLVConfigHeaderFormStringInput']");

	public final By ELEMENT_EDITFORUM_CATEGORY = By.xpath("//*[@class='uiIconCategory uiIconLightGray']/../..//*[@class='checkbox']");

	//Edit properties of page
	public final String ELEMENT_VIEW_PAGE_PROPERTIES = ".//*[@id='UIPageEditor']//*[contains(text(),'View Page properties')]";
	public final By ELEMENT_VIEWPAGE_PAGETITLE = By.id("title");
	public final String ELEMENT_PERMISSION_SETTING_TAB = "//*[@data-target='#PermissionSetting-tab']";
	public final String ELEMENT_CONTAINER_PERMISSION_SETTING_TAB = "//*[@data-target='#UIContainerPermission-tab']";
	public final String ELEMENT_EDIT_PERMISSION_SETTING = ".//*[@id='PermissionSetting']//*[contains(text(),'Edit')]";
	public final String ELEMENT_MOVE_APPS_PERMISSION_SETTING = ".//*[@id='PermissionSetting']//a[contains(text(),'Move Apps')]";
	public final String ELEMENT_MOVE_CONTAINERS_PERMISSION_SETTING = ".//*[@id='PermissionSetting']//a[contains(text(),'Move Containers')]";
	public final String ELEMENT_SELECT_PERMISSION_BUTTON = ".//*[@id='UIPermissionSelector']//*[contains(text(),'Select Permission')]";
	public final By ELEMENT_EDIT_PORTLET_FORM_ADD_PERM_BTN = By.xpath("//*[contains(@class,'uiIconAddUser')]");
	public final String ELEMENT_SELECT_EDIT_GROUP_ITEM = ".//*[@id='PermissionSelector']//*[@title='${group}']/i";
	public final String ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM = ".//*[@id='PermissionSelector']//*[@title='${membership}']";
	public final String ELEMENT_SELECTED_EDIT_PERMISSION_MEMBERSHIP = ".//*[@id='UIPermissionSelector']//*[contains(text(),'Membership')]/../*[contains(text(),'${membership}')]";

    /*************************************PAGE EDITOR***************************************************************/
	//Common
	public final By ELEMENT_EDIT_PORTLET_ICON = By.xpath("//*[@data-original-title='Edit Portlet']");
	public final By ELEMENT_DELETE_PORTLET_ICON = By.xpath("//*[@data-original-title='Delete Portlet']");
	public final String ELEMENT_EDIT_PORTLET = "//*[@data-original-title='Edit Portlet']/../*[contains(.,'${portlet}')]";
	public final String ELEMENT_DELETE_PORTLET = "//*[@data-original-title='Edit Portlet']/../*[contains(.,'${portlet}')]";
	public final String ELEMENT_EDITOR_PAGE_APPLICATION_PORTLET =".//*[@class='portletLayoutDecorator'][contains(text(),'${name}')]";
	
	//Edit portlet form
	public final By ELEMENT_EDIT_PORTLET_FORM_ACCESS_PERM = By.xpath("//*[contains(@data-target,'PortletPermission')]");
	public final By ELEMENT_SELECT_MEMBERSHIP_POPUP = By.xpath("//*[contains(@id,'UIListPermissionSelectorPopup')]");
	
	//Application
	public final String ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_RIGHT="//*[@title='$category']/*[contains(@class,'uiIconArrowRight')]";
	public final String ELEMENT_EDIT_PORTLET_CATEGORY_APPLICATION_ARROW_DOWN="//*[@title='$category']/*[contains(@class,'uiIconArrowDown')]";
	public final String ELEMENT_EDIT_PORTLET_APPLICATION_ID="//*[contains(@id,'$portlet')]";
	public final String ELEMENT_APPLICATION_NAME = "//*[@class='txtLeft'][contains(.,'$app')]";
	
	//Finish and Abort button
	public final By ELEMENT_EDIT_PORTLET_FINISH = By.xpath("//*[@data-original-title='Finish']");
	public final By ELEMENT_EDIT_PORTLET_ABORT= By.xpath("//*[@data-original-title='Abort']");
	
	//View page properties
	public final By ELEMENT_PAGE_EDITOR_VIEW_PAGE_PROPERTIES=By.xpath("//*[@class='btn btn-primary PageProfileIcon']");
	public final By ELEMENT_PERMISSTION_SETTING_TAB = By.xpath("//*[@data-target='#PermissionSetting-tab']");
	public final By ELEMENT_PUBLIC_MODE=By.id("publicMode");
	
	//Middle container
	public final By ELEMENT_EDIT_PAGE_PAGE = By.id("UIPage");
	public final By ELEMENT_PAGE_EDITOR_SAVE_BUTTON=By.xpath("//*[@id='UIPageForm']//*[text()='Save']");
	public final By ELEMENT_PAGE_EDITOR_SAVE_AND_CLOSE_BUTTON=By.xpath("//*[text()='Save And Close']");
	public final By ELEMENT_PAGE_EDITOR_CLOSE_BUTTON=By.id("Close");
	public final By ELEMENT_FRAME_CONTAIN_PORTLET = By.xpath("//div[contains(@id,'UIPortlet')]");
	public final By ELEMENT_PAGE_OK_BUTTON=By.xpath("//*[contains(@class,'UIPopupWindow')]//a[text()='OK']");
	
	//Container
	public final String ELEMENT_CONTAINER_ID=".//*[@id='${id}']/div/div[1]/div/div";
	
    /*************************************PORTAL BRANDING***************************************************************/
	public final By ELEMENT_UPLOAD_LINK = By.name("file");

	public final By ELEMENT_PLF_BRANDINGPAGE = By.xpath("//*[@class='uiBreadcumbsNavigations']//*[text()='Branding']");
	public final By ELEMENT_BANDING_PAGE_SELECT_LOGO =By.xpath(".//h4[text()='Select Logo']");
	public final By ELEMENT_BANDING_PAGE_SELECT_NAVIGATION_BAR_STYLE =By.xpath(".//h4[text()='Select Navigation Bar Style']");
	public final By ELEMENT_BANDING_PAGE_PREVIEW =By.xpath(".//h4[text()='Preview']");
	
	//Theme selection
	public final By ELEMENT_PLF_BRANDING_SELECTTHEME = By.xpath("//*[@class='btn dropdown-toggle']");
	public final By ELEMENT_PLF_BRANDING_THEMEDARK = By.xpath("//*[@class='OptionItem' and text()='Dark']");
	public final By ELEMENT_PLF_BRANDING_THEMELIGHT = By.xpath("//*[@class='OptionItem' and text()='Light']");
	
	//Displayed top bar 
	public final By ELEMENT_PLF_BRANDING_TOPBAR_THEMELIGHT = By.xpath("//*[@class='UIContainer UIToolbarContainer  UIToolbarContainerLight']");
	public final By ELEMENT_PLF_BRANDING_TOPBAR_THEMEDARK = By.xpath("//*[@class='UIContainer UIToolbarContainer  UIToolbarContainerLight']");
	public final By ELEMENT_PLF_BRANDING_TOPBAR_LOGO = By.xpath("//*[@alt='Home' and contains(@src, 'logo_preview.png')]");
	
	//Button
	public final By ELEMENT_BUTTON_SAVE = By.xpath("//*[text()='Save']");
	public final By ELEMENT_BUTTON_CANCEL=By.xpath(".//*[@id='cancel']");
	public final By ELEMENT_BUTTON_UPLOAD = By.xpath("//*[@id='btUpload']");
	
    /*************************************PORTAL GROUP NAVIGATION***************************************************************/
	public final String ELEMENT_GROUP_SELECT_ADD_NAVIGATION = "//*[contains(text(),'${groupName}')]/..//a[contains(text(),'Add Navigation')]";
	public final By ELEMENT_CANCEL_BUTON = By.linkText("Cancel");
	public final String ELEMENT_GROUP_NAME = ".//*[@id='UIGroupNavigationGrid']//*[contains(text(),'${groupName}')]";
	public final String ELEMENT_DELETE_NAVIGATION_ICON = "//*[contains(text(),'${groupName}')]/../..//i[@class='uiIconTrash uiIconLightGray']";
	
	
	public final String ELEMENT_GROUP_NAVIGATION_POSITION = ".//*[@id='UIGroupNavigationGrid']//tr[${index}]//*[contains(text(),'${groupTitle}')]";
	public final String ELEMENT_EDIT_PROPERTIES_ICON = "//*[text()='${groupName}']/../..//*[@class='uiIconEditPortalConfig uiIconLightGray']";
	public final String ELEMENT_GROUP_NAVIGATION_PRIORITY = "//*[@name='priority']";
	public final String ELEMENT_SAVE_BTN = "//*[text()='Save']";
	public final String ELEMENT_EDIT_NAVIGATION = "//*[text()='${groupName}']/../..//i[@class='uiIconNavigation uiIconLightGray']";
	public final String ELEMENT_TITLE_NAVIGATION_MANAGEMENT = "//*[text()='Navigation Management']";

    /*************************************PORTAL MANAGE PAGES***************************************************************/
	public final By ELEMENT_MANAGEPAGES_TITLE=By.xpath(".//*[text()='Manage Pages']");
	public final By ELEMENT_MANAGEPAGES_TITLE_FIELD=By.id("pageTitle");
	public final By ELEMENT_MANAGEPAGES_SITES_NAME_FIELD=By.id("siteName");
	public final By ELEMENT_MANAGEPAGES_TYPE_DROPBOX=By.xpath(".//*[@name='searchOption']");
	public final String ELEMENT_MANAGEPAGES_SELECT_TYPE=".//*[@name='searchOption']//*[contains(@value,'${type}')]";
	public final By ELEMENT_MANAGEPAGES_SEARCH_BUTTON = By.xpath(".//*[@class='uiIconSearch uiIconLightGray']");
	public final By ELEMENT_MANGEPAGES_INFORM_POPUP_OK= By.xpath(".//*[text()='OK']");
	public final By ELEMENT_MANGEPAGES_INFORM_POPUP_CLOSE= By.xpath("//*[@title='Close Window']");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_BTN = By.xpath(".//*[@id='UIPageBrowser']//*[text()='Add New Page']");
	
	//Results search
	public final String ELEMENT_MANAGEPAGES_CONTENT_TITLE_COLUMN=".//*[contains(text(),'${title}')]";
	public final String ELEMENT_MANAGEPAGES_CONTENT_SEARCH_TABLE=".//*[contains(@title,'${type}::${siteName}')]/../..//*[contains(text(),'${title}')]";
	public final By ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_DELETE=By.xpath(".//*[@class='uiIconDelete uiIconLightGray']");
	public final By ELEMENT_MAGEPAGES_CONTENT_ACTION_COLUMN_EDIT=By.xpath(".//*[@class='uiIconEditInfo uiIconLightGray']");
	
	
	//Add new page popup
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGES_POPUP_FORM=By.xpath(".//*[@id='UIPageForm']");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGES_POPUP_CANCEL_BTN=By.xpath(".//*[@id='UIPageForm']//*[@class='btn' and text()='Cancel']");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGES_POPUP_SAVE_BTN=By.cssSelector("#UIMaskWorkspace .btn[onclick~=\"javascript:eXo.webui.UIForm.submitForm('UIPageForm','Save',true)\"]");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_PAGE_NAME =By.cssSelector("#UIMaskWorkspace #name");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TITLE = By.cssSelector("#UIMaskWorkspace #title");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_CHECKBOX = By.cssSelector("#UIMaskWorkspace #showMaxWindow");
	public final By ELEMENT_MANAGEPAGES_ADD_NEW_PAGE_POPUP_TYPE_DROPBOX =By.xpath(".//*[@name='ownerType']"); 
	
	//Search Page
	public final By ELEMENT_INPUT_SEARCH_TITLE = By.id("pageTitle");
	public final String ELEMENT_SELECT_SEARCH_OPTION = "//*[@class='selectbox' and @name='searchOption']";
	public final By ELEMENT_INPUT_SITE_NAME = By.id("siteName");
	public final String ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON = "//*[@data-original-title='Quick Search']/i";
	//public final String ELEMENT_LIST_PAGE = ".//*[@id='UIRepeater']//tr[${number}]//*[@title='${titlePage}']";
	public final String ELEMENT_LIST_PAGE = "//*[@id='UIRepeater']//tbody/tr[${number}]//*[@title='${titlePage}']"; 
	public final String ELEMENT_PAGE_DELETE_ICON_AUX = ELEMENT_LIST_PAGE.replace("${number}", "${number}").replace("${titlePage}", "${titlePage}") + "/../..//*[@class='uiIconDelete uiIconLightGray']";	
	public final String ELEMENT_PAGE_DELETE_ICON = "//*[contains(@title,'${page}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	public final String MESSAGE_DELETE_PAGE = "Do you want to delete this page?";
	public final String ELEMENT_NODE_LINK = ".//*[@id='UINavigationNodeSelector']//*[@title='${nodeLabel}']/i";
	
	public final String ELEMENT_PAGE_FINISH_BUTTON = ".//*[@id='UIPageEditor']//*[@data-original-title='Finish']";
	
    /*************************************PORTAL MANAGE SITES***************************************************************/
	// Add New Portal
	public final String ELEMENT_ADD_NEW_PORTAL_LINK = ".//*[@id='UISiteManagement']//a[contains(text(),'Add New Site')]";
	public final By ELEMENT_INPUT_NAME = By.id("name");
	public final By ELEMENT_PORTAL_LABEL = By.id("label");
	public final By ELEMENT_PORTAL_DESCRIPTION = By.id("description");
	public final String ELEMENT_SELECT_LOCALE = "//*[@class='selectbox' and contains(@name,'locale')]";
	public final String ELEMENT_SELECT_SKIN = "//*[@class='selectbox' and contains(@name,'skin')]";
	
	public final String ELEMENT_PROPERTIES_TAB = "//a[contains(text(),'Properties')]";
	public final String ELEMENT_SELECT_SESSION_ALIVE = "//*[@class='selectbox' and contains(@name,'sessionAlive')]";
	
	public final By ELEMENT_CHECKBOX_PUBLIC_MODE = By.id("publicMode");
	public final By ELEMENT_ADD_PERMISSION_BUTTON = By.xpath("//*[contains(@class,'uiIconAddUser')]");
	public final String ELEMENT_PORTAL_TEMPLATE_TAB = "//a[contains(text(),'Portal Templates')]";
	
	public final String ELEMENT_SAVE_BUTTON = "//button[contains(text(),'Save')]";
	
	public final String ELEMENT_SELECT_ACCESS_GROUP_ITEM = "//a[contains(@title,'${group}')]/i";
	public final String ELEMENT_SELECT_ACCESS_MEMBERSHIP_ITEM = "//a[contains(@title,'${membership}')]";
	public final String ELEMENT_SELECTED_ACCESS_PERMISSION_MEMBERSHIP = ".//*[@id='PermissionGrid']//span[contains(text(),'${membership}')]";
	public final String ELEMENT_SELECT_EDIT_PERMISSION_MEMBERSHIP = ".//*[@id='PermissionSelector']//a[contains(.,'${membership}')]";	
	public final By ELEMENT_POPUP_ADD_PORTAL = By.id("UIMaskWorkspace");
	public final String ELEMENT_PORTAL_DELETE_ICON = "//*[contains(text(),'${portalName}')]/../..//i[@class='uiIconTrash uiIconLightGray']";
	
	public final String ELEMENT_NEW_PORTAL_ADD = "//*[@class='siteName' and text()='${portalName}']";
	public final String ELEMENT_NEW_PORTAL_SWITCH = "//img[contains(@src, 'sites/${portalName}')]";
	public final By ELEMENT_NEW_PORTAL_LOGOUT= By.xpath("//*[@id='AcmeWebSiteLogInLogOut']");
    
	
	/*************************************PORTAL MANAGEMENT***************************************************************/
    
    /*************************************USER ADD MANAGEMENT***************************************************************/
	public By ELEMENT_USERNAME = By.id("username");
	public By ELEMENT_PASSWORD = By.id("password");
	public By ELEMENT_CONFIRM_PASSWORD = By.id("Confirmpassword");
	public By ELEMENT_EMAIL = By.id("email");
	public By ELEMENT_FIRSTNAME = By.id("firstName");
	public By ELEMENT_LASTNAME = By.id("lastName");
	public By ELEMENT_DISPLAY_NAME = By.id("displayName");
	public final String ELEMENT_USER_EDIT_ICON = ".//*[contains(text(),'${username}')]/../..//*[@data-original-title='Edit User Info']/i";
	public final String ELEMENT_USER_DELETE_ICON = ".//*[contains(text(),'${username}')]/../..//*[@data-original-title='Delete User']/i";
	public final By ELEMENT_SAVE_UPDATE_USER = By.xpath("//*[@id='UIUserManagement']//*[text()='Save']");
	public final By ELEMENT_SAVE_ADD_USER = By.xpath("//*[@id='UIAccountForm']//*[text()='Save']");
	public final By ELEMENT_SAVE_PASSWORD = By.xpath(".//*[@id='UIAccountChangePass']//*[text()='Save']");
	public final By ELEMENT_INPUT_SEARCH_USER_NAME = By.id("searchTerm");
	public final String ELEMENT_SEARCH_ICON_USERS_MANAGEMENT = "//*[contains(@title,'Quick Search')]";
	public final String ELEMENT_CLOSE_MESSAGE = "//*[contains(@title,'Close Window')]";
	
	//message
	public final String ELEMENT_MSG_CREATE_ACCOUNT = "You have registered a new account.";
	public final String ELEMENT_MSG_UPDATE_PROFILE = "The user profile has been updated.";
	public final String ELEMENT_MSG_SEARCH_USER_NAME = "User Name";
	public final String ELEMENT_MSG_CONFIRM_DELETE = "Are you sure you want to delete ${userName} user?";
	public final String ELEMENT_MSG_RESULT = "No result found.";
	public final String ELEMENT_MSG_CHANGE_PASS_WORD = "The password has been changed.";
	
	//change passWord
	public final By ELEMENT_CHANGE_PASSWORD_LINK = By.linkText("Change Password");
	public final By ELEMENT_CURRENT_PASSWOR = By.id("currentpass");
	public final By ELEMENT_NEW_PASSWORD = By.id("newpass");
	public final By ELEMENT_CONFIRM_NEW_PASSWORD = By.id("confirmnewpass");
	
    /*************************************USER AND GROUP MANAGEMENT***************************************************************/
	public final String ELEMENT_LINK_SETUP = ".//*[@id='UISetupPlatformToolBarPortlet']/a";
	public final String ELEMENT_MANAGE_USER = ".//*[@id='UISetupPlatformToolBarPortlet']//a[text()='Users']";	
	public final String ELEMENT_GROUP_AND_ROLE_LINK = ".//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Groups and Roles')]";
	public final String ELEMENT_USER_MANAGEMENT_ACTIVE_TAB = "//a[contains(@class,'actionIcon userButton active')]/i";
	public final String ELEMENT_USER_MANAGEMENT_TAB = "//a[contains(@class,'actionIcon userButton')]/i";
	public final String ELEMENT_LINK_USERS = ".//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Add Users')]";
	public final String ELEMENT_GROUP_MANAGEMENT_TAB = "//a[contains(@class,'actionIcon groupButton')]/i";
	public final String ELEMENT_GROUP_MANAGEMENT_INFO = ".//*[contains(text(),'Group Info')]";
	public final String ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP=".//*[@class='groupNavigationContainer']//*[contains(@title,'${name}')]";
	
	public final String ELEMENT_TAB_MEMBERSHIP_MANAGEMENT = "//a[contains(@class,'actionIcon membershipButton')]/i";
	public final String ELEMENT_MEMBERSHIP_MANAGEMENT_GRID = "//*[contains(text(), 'Add/Edit Membership')]";
	public final String ELEMENT_GROUP_ADD_NEW_ICON = "//*[@data-original-title='Add New Group']/i";
	public final By ELEMENT_INPUT_GROUP_NAME = By.id("groupName");
	public final By ELEMENT_INPUT_LABEL = By.id("label");
	public final By ELEMENT_TEXTAREA_DESCRIPTION = By.name("description");
	public final String ELEMENT_GROUP_EDIT_ICON = "//*[@data-original-title='Edit Selected Group']/i";
	public final By ELEMENT_GROUP_SEARCH_USER_ICON = By.className("uiIconSelectUser uiIconLightGray");
	public final String ELEMENT_GROUP_SEARCH_USER_ICON_2 = "//*[contains(@class, 'uiIconSelectUser')]";
	public final By ELEMENT_GROUP_SEARCH_USER_SEARCH_INPUT = By.id("Quick Search");
	public final String ELEMENT_GROUP_SEARCH_USER_OPTION = "//*[@class='selectbox' and @name='filter']";
	public final String ELEMENT_GROUP_SEARCH_USER_SEARCH_ICON = ".//*[@data-original-title='Quick Search']/i";
	public final String ELEMENT_ADDED_GROUP_USER_IN_TABLE = "//*[@id='UIGridUser']//span[contains(text(),'${username}')]";
	public final String ELEMENT_ADDED_GROUP_USER_IN_TABLE1 = "//*[@id='UIGridUser']//span[contains(text(),'$mem')]/../../*[@headers='userName']/*[contains(.,'$user')]";
	public final String ELEMENT_EDIT_USER_MEM_IN_TABLE_ICON ="//*[@headers='userName']/*[contains(.,'$user')]/../..//*[contains(@class,'uiIconEdit')]";
	public final String ELEMENT_EDIT_USER_MEM_FORM = "//*[contains(@class,'UIGroupEditMembershipForm')][contains(.,'$mem')]";
	public final String ELEMENT_SEARCH_GROUP_USER_IN_TABLE = "//*[@id='UIListUsers']//span[contains(text(),'${username}')]";
	public final String ELEMENT_ADD_BUTTON = "//*[@class='uiAction uiActionBorder']//a[contains(@class,'btn') and contains(text(),'Add')]";
	public final String ELEMENT_SELECT_MEMBERSHIP = "//*[@class='selectbox' and @name='membership']";
	public final String ELEMENT_GROUP_REMOVE_ICON = "//*[@data-original-title='Delete Selected Group']/i";
	public final String ELEMENT_SAVE_BUTTON_2 = "//a[contains(@class,'btn') and contains(text(),'Save')]";
	public final String ELEMENT_MEMBERSHIP_EDIT_ICON = "//span[contains(text(),'${membership}')]/../..//*[contains(@data-original-title,'Edit Membership')]/i";
	public final String ELEMENT_MEMBERSHIP_DELETE_ICON = "//span[contains(text(),'${membership}')]/../..//*[contains(@data-original-title,'Delete Membership')]/i";
	public final String ELEMENT_GROUP_ADDED = "//a[@title='${groupLabel}']";
	public final String ELEMENT_USER_NAME = "User Name";
	public final String ELEMENT_CHECK = "//input[@name='${user}']";
	public final String ELEMENT_USER_NOT_FOUND = "User ${user}not found in group";
	public final String ELEMENT_GROUP_NODE = "//a[@title='${groupName}']";
	public final String ELEMENT_MEMBERSHIHP = "//*[@id='UIGrid']//span[text()='${membershipName}']";
	public final String ELEMENT_MEMBERSHIP_DESCRIPTION ="//*[@id='UIGrid']//span[text()='$des']/../../*[@headers='name']/*[contains(.,'$mem')]";
	public final String ELEMENT_MEMBERSHIP_INPUT = "//input[@value='${membershipName}']";

	//Paging control
	public final By ELEMENT_PAGINATION_CONTROL=By.xpath(".//*[contains(@class,'pagination')]");
	public final By ELMEMENT_PAGINATION_CONTROL_DISBALED_NEXT_ARROW=By.xpath(".//*[@class='disabled']//*[contains(@class,'uiIconNextArrow')]");
	public final By ELEMENT_PAGINATION_TOTAL_PAGE=By.xpath("//*[contains(@class,'pagesTotalNumber')]");
	public final By ELEMENT_PAGINATION_ENABLED_NEXT_ARROW=By.xpath(".//*[@ data-original-title='Next Page']//*[@class='uiIconNextArrow']");
	
	//User tab
	public final String ELEMENT_USER_TAB=".//*[@id='UIOrganizationPortlet']//*[contains(@class,'uiIconUser uiIconLightGray')]";
	public final String ELEMENT_USER_DELETE_ICON1 = "//*[@data-original-title='Delete User']/i";
	public final String ELEMENT_USER_NAME_IN_USER_LIST=".//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]";
	//message
	public final String ELEMENT_MSG_SELECT_USER = "Select User";
	public final String ELEMENT_MSG_TOTAL_PAGES = "Total pages";
	public final String ELEMENT_MSG_CONFIRM_DELETE_GROUP = "Are you sure you want to delete this group?";
	public final String ELEMENT_MSG_CONFIRM_DELETE_MEMBERSHIP = "Are you sure you want to delete this membership?";
	public final String ELEMENT_MSG_CONFIRM_DELETE1 = "Are you sure you want to delete";
	public final String ELEMENT_MSG_UPDATE_USER_PROFILE = "The user profile has been updated.";
	public final By ELEMENT_OK_BUTTON = By.xpath("//*[contains(text(),'OK')]");
	public final String ELEMENT_MSG_CANNOT_DELETE = "You cannot delete this membership because it is mandatory.";
	public final By ELEMENT_OK_BTN = By.xpath("//*[@class='btn'][contains(.,'OK')]");
	//Account tab
	public final By ELEMENT_USER_ACCOUNT_INFO_TAB = By.xpath("//*[@data-target='#UIAccountEditInputSet-tab']");

	//Edit user profile Tab
	public final By ELEMENT_USER_PROFILE_TAB = By.xpath("//*[@data-target='#UIUserProfileInputSet-tab']");
	public final By ELEMENT_GIVEN_NAME = By.id("user.name.given");
	public final By ELEMENT_FAMILY_NAME = By.id("user.name.family");
	public final By ELEMENT_NICK_NAME = By.id("user.name.nickName");
	public final By ELEMENT_BIRTHDAY = By.id("user.bdate");
	public final By ELEMENT_GENDER = By.xpath("//*[@name='user.gender']");
	public final By ELEMENT_EMPLOYER = By.id("user.employer");
	public final By ELEMENT_DEPARTMENT = By.id("user.department");
	public final By ELEMENT_JOB_TITLE = By.id("user.jobtitle");
	public final By ELEMENT_LANGUAGE = By.name("user.language");
	
	//User membership tab
	public final By ELEMENT_USER_MEMBERSHIP_TAB = By.xpath("//*[@data-target='#UIUserMembershipSelector-tab']");

	//Group management
	public final String ELEMENT_USER_REMOVE_MEMBER_ICON = ".//*[contains(text(),'${userName}')]/../..//*[contains(@class,'uiIconDeleteUser')]";

	//Up level
	public final By ELEMENT_UP_LEVEL=By.xpath("//*[@data-original-title='Up Level']");
	
	//Disable Users
	public final String ELEMENT_DISABLE_USER_HANDLE_BTN=".//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]/../..//*[contains(@class,'switchBtnHandle')]";
    public final String ELEMENT_DISBALE_USER_ENABLED=".//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]/../..//*[@data-original-title='Disable User']";
    public final String ELEMENT_DISBALE_USER_DISABLED=".//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]/../..//*[@data-original-title='Enable User']";
    public final By ELEMENT_DISABLE_USER_COLUMN = By.xpath(".//th[@id='DisableEnableUser'][contains(.,'Enabled')]");
    public final By ELEMENT_DISABLE_USER_STATUS_DISABLED= By.xpath("//*[@id='UIListUsers-userStatusFilter']/*[contains(.,'Disabled')]");
    public final By ELEMENT_DISABLE_USER_STATUS_ALL= By.xpath("//*[@id='UIListUsers-userStatusFilter']/*[contains(.,'All')]");
    public final By ELEMENT_DISABLE_USER_STATUS_ENABLED= By.xpath("//*[@id='UIListUsers-userStatusFilter']/*[contains(.,'Enabled')]");
    public final String ELEMENT_DISABLE_USER_STATUS_SELECTED ="//*[@id='UIListUsers-userStatusFilter']/*[contains(.,'$status')][@selected='selected']";
    public final String ELEMENT_DISABLE_USER_TOGGLE_NO = "//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]/../..//*[contains(@class,'switchBtnLabelOff')]//*[contains(text(),'No')]";
    public final String ELEMENT_DISABLE_USER_TOGGLE_YES = "//*[@id='UIListUsersGird']//*[contains(text(),'$userName')]/../..//*[contains(@class,'switchBtnLabelOn')]//*[contains(text(),'Yes')]";
    
    
  
	//Social networks tab
	public final By ELEMENT_USER_SOCIAL_NETWORKS_TAB = By.xpath("//*[@data-target='#UIAccountSocial-tab']");
	public final String ELEMENT_USER_SOCIAL_NETWORKS_TAB_GOOGLE_ACCOUNT =".//*[@id='user.social-info.google.userName' and @value= '${account}']";
	public final By ELEMENT_USER_ACCOUNT_PROFILE_TAB = By.xpath("//*[@data-target='#UIAccountProfiles-tab']");
}
