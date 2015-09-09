package org.exoplatform.selenium.platform.social;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class SpaceLocator extends PlatformBase{

	//****************************************************SPACE HOME PAGE ***********************************************************************\\
	public final By ELEMENT_SPACE_PANEL=By.xpath(".//*[@id='UIMySpacesPortlet']");
	
	//select menu (actvity stream, forum, agenda etc ..)
	public final By ELEMENT_SPACE_MENU_ACTIVITY_STREAM = By.xpath(".//*[@class='tabName' and contains(text(),' Activity Stream')]");
	public final By ELEMENT_SPACE_MENU_AGENDA = By.xpath(".//*[@id='calendar' and contains(text(),'Agenda')]");
	public final By ELEMENT_SPACE_MENU_FORUMS = By.xpath(".//*[@class='tabName' and contains(text(),'Forums')]");
	public final By ELEMENT_SPACE_MENU_WIKI = By.xpath(".//*[@class='tabName' and contains(text(),'Wiki')]");
	public final By ELEMENT_SPACE_MENU_DOCUMENTS = By.xpath(".//*[@class='tabName' and contains(text(),'Documents')]");
	public final By ELEMENT_SPACE_MENU_SETTINGS = By.xpath(".//*[@class='tabName' and contains(text(),'Space Settings')]");
	public final By ELEMENT_SPACE_MENU_ANSWER = By.xpath(".//*[@class='tabName' and contains(text(),'Answer')]");
	public final String ELEMENT_SPACE_NAME = ".//*[@id='UIBreadCrumbsNavigationPortlet']//*[@class='name'][contains(text(),'${name}')]";
	public final String ELEMENT_SPACE_MENU_DISPLAYORDER_ID = ".//*[@id='spaceMenuTab']/li[${number}]//*[contains(@class,'${tab}')]";
	public final String ELEMENT_SPACE_MENU_DISPLAYORDER = ".//*[@id='spaceMenuTab']/li[${number}]//*[contains(text(),'${tab}')]";
	
	//Navigation menu
	public final By ELEMENT_HORIZOLTAL_MENU_BAR = By.xpath(".//*[@id='spaceMenuTab']");
	public final String ELEMENT_SPACE_TAB_NAME=".//*[@id='spaceMenuTab']//*[contains(text(),'${name}')]";
	public final By ELEMENT_ACTIVITY_STREAM_TAB = By.xpath(".//*[contains(@class,'uiIconAppSpaceActivityStreamPortlet')]");
	public final By ELEMENT_FORUM_TAB = By.xpath(".//*[contains(@class,'uiIconAppForumPortlet')]");
	public final By ELEMENT_WIKI_TAB = By.xpath(".//*[contains(@class,'uiIconAppWikiPortlet')]");
	public final By ELEMENT_DOCUMENT_TAB = By.xpath(".//*[contains(@class,'uiIconAppFileExplorerPortlet')]");
	public final By ELEMENT_AGENDA_TAB = By.xpath(".//*[contains(@class,'uiIconAppCalendarPortlet')]");
	public final By ELEMENT_MEMBER_TAB = By.xpath(".//*[contains(@class,'uiIconAppMembersPortlet')]");
	public final By ELEMENT_NAVIGATION_SPACE_SETTING_TAB = By.xpath(".//*[contains(@class,'uiIconAppSpaceSettingPortlet')]");
	
	public By ELEMENT_SPACE_SPACE_SETTINGS=By.xpath("//*[@class='uiIconAppSpaceSettingPortlet uiIconDefaultApp']/..");
	public By ELEMENT_SPACE_WIKI_TAB=By.xpath(".//*[@id='spaceMenuTab']//*[contains(text(),'Wiki')]");
	public By ELEMENT_SPACE_MEMBERS_TAB=By.xpath(".//*[@id='spaceMenuTab']//*[contains(text(),'Members')]");
	public By ELEMENT_SPACE_WIKI_TAB_CONTENT=By.xpath("//*[@id='UIWikiPortlet']");
	public By ELEMENT_SPACE_WIKI_TAB_ACTIVE=By.xpath("//*[@class='active item']/*[@data-toggle='tab']/*[contains(text(),'Wiki')]");
	public By ELEMENT_SPACE_ACTIVITY_TAB_ACTIVE=By.xpath("//*[@class='active item']/*[@data-toggle='tab']/*[contains(text(),'Activity')]");
	public String ELEMENT_SPACE_MENU_TAB=".//*[@id='spaceMenuTab']//*[@id='${tab}']";
	public By ELEMENT_SPACE_MENU_MORE = By.xpath(".//*[@id='spaceMenuTab']//*[contains(text(),'More')]");
	public By ELEMENT_SPACE_MENU_DASHBOARD = By.xpath(".//*[@id='spaceMenuTab']//*[@id='DashboardPortlet']");
	
	
	//left menu
	public String ELEMENT_SPACE_LEFT_MENU_SPACE_NAME = ".//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'${name}')]";
	
	
	//****************************************************SPACE MANAGEMENTT***********************************************************************\\
	// Add form space
	public final By ELEMENT_ADDNEWSPACE_BUTTON = By.xpath("//button[contains(.,'Add New Space')]");
	public final By ELEMENT_ADDNEWSPACE_FORM = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Add New Space']");
	public final By ELEMENT_ADDNEWSPACE_ICON = By.xpath("//*[contains(@class, 'uiIconSocSimplePlus')]");
	
	//Search panel
	public final By ELEMENT_MY_SPACE_SEARCH_TEXT_BOX = By.xpath(".//*[@id='SpaceSearch']");
	public final By ELEMENT_MY_SPACE_SEARCH_BTN = By.xpath(".//*[@id='UISpaceSearch']//i[@class='uiIconSearch uiIconLightGray']");
	public final String ELEMENT_MY_SPACE_SEARCH_RESULT = ".//*[@id='UIManageMySpaces']//*[contains(text(),'${name}')]";
	public final String ELEMENT_MY_SPACE_SEARCH_RESULT_NUMBER = ".//*[@id='UIManageMySpaces']//*[@class='number'][text()='${number}']";

	//Letter list 
	public final String ELEMENT_MY_SPACE_LETTER_LIST= ".//*[@class='letterList']//*[text()='${alpha}']";

	//Space portlets
	public By ELEMENT_SPACE_MY_SPACE_PORTLET = By.id("UIMySpacesPortlet");
	public By ELEMENT_SPACE_ALL_SPACE_PORTLET = By.id("UIAllSpacesPortlet");
	public By ELEMENT_SPACE_INVITATION_SPACE_PORTLET = By.id("UIInvitationSpacesPortlet");
	public By ELEMENT_SPACE_PENDING_SPACE_PORTLET = By.id("UIPendingSpacesPortlet");

	//Add new space buttons
	public By ELEMENT_ADD_NEW_SPACE_BUTTON = By.xpath("//*[@class='uiIconSocSimplePlus uiIconSocWhite']");
	public By ELEMENT_ADD_SPACE_FORM = By.id("UIPopupAddSpace");

	public final By ELEMENT_UPLOAD_POPUP_SELECT_FILE_BTN=By.xpath(".//*[@id='Uploader']//label[text()='Select File']");
	public final By ELEMENT_SPACE_SAVE_BTN = By.xpath(".//*[@id='UISpaceInfo']//button[text()='Save']");
	public final By ELEMENT_SPACE_UPLOAD_CONFIRM_BTN=By.xpath(".//*[@id='UIAvatarUploader']//button[text()='Confirm']");
	public final By ELEMENT_SPACE_UPLOAD_SAVE_BTN=By.xpath(".//*[@id='UIAvatarUploadContent']//button[text()='Save']");

	//Access and Edit tab form
	public By ELEMENT_SPACE_ACCESS_EDIT_TAB=By.xpath("//*[@data-target='#UISpacePermission-tab']");
	public By ELEMENT_SPACE_VISIBILITY_VISIBLE_CHECKBOX=By.xpath("//*[@value='private']");
	public By ELEMENT_SPACE_VISIBILITY_HIDDEN_CHECKBOX=By.xpath("//*[@value='hidden']");
	public By ELEMENT_SPACE_REGISTRATION_OPEN_CHECKBOX=By.xpath("//*[@value='open']");
	public By ELEMENT_SPACE_REGISTRATION_CLOSED_CHECKBOX=By.xpath("//*[@value='close']");
	public By ELEMENT_SPACE_REGISTRATION_VALIDATION_CHECKBOX=By.xpath("//*[@value='validation']");


	//Access and Edit tab form
	public By ELEMENT_SPACE_INVITE_GROUP_USER_TAB=By.xpath("//*[@data-target='#UISpaceGroupBound-tab']");
	public By ELEMENT_SPACE_SELECT_EXIST_GROUP_CHECKBOX=By.id("UseExistingGroupCheckBox");

	//My space
	public final By ELEMENT_SPACE_MY_SPACE_TAB=By.xpath(".//*[@id='UIManageAllSpaces']//*[contains(text(),'My Spaces')]");
	public String ELEMENT_SPACE_TITLE="//*[@class='spaceTitle']//*[text()='${space}']";
	public final String ELEMENT_SPACE_DESCRIPTION=".//*[@id='UIManageMySpaces']//*[@class='content limitText'][text()='${des}']";
	public final By ELEMENT_SPACE_AVATAR_DEFAULT=By.xpath(".//*[@id='UISpaceInfo']//*[contains(@src,'SpaceAvtDefault.png')]");
	public final String ELEMENT_SPACE_DELETE_BUTTON="//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Delete']";
	public final String ELEMENT_SPACE_LEAVE_BTN = "//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Leave']";
	public final String ELEMENT_SPACE_EDIT_BTN = "	//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Edit']";
    public final By ELEMENT_SPACE_EDIT_SETTING_TAB =By.xpath(".//*[contains(@data-target,'#UISpaceInfo-tab')]");
	public final String ELEMENT_SPACE_MEMBER_INFOR="//*[@class='spaceTitle']//*[text()='${space}']/../..//*[contains(@class,'membersCount')]";
	public final String ELEMENT_SPACE_DESC_INFOR="//*[@class='spaceTitle']//*[text()='${space}']/../..//*[contains(@class,'content')]";
	public final String ELEMENT_SPACE_MANAGER_STATUS="//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[contains(@class,'statusLabel') and text()='Manager']";
	public String ELEMENT_SPACE_CONFIRM_DELETE="Are you sure you want to delete this space? This cannot be undone. All page navigations and this group will also be deleted";
	public By ELEMENT_SPACE_DELETE_SPACE_OK_BUTTON=By.xpath("//*[text()='OK']");

	public String ELEMENT_SPACE_NAME_BREADCUMB ="//*[@id='UIBreadCrumbsNavigationPortlet']//*[@class='name' and contains(text(),'{$name}')]";

	//Invitations received tab
	public final By ELEMENT_MY_SPACE_INVITATION_RECEIVED = By.xpath(".//a[text()='Invitations Received']");
	public final String ELEMENT_MY_SPACE_INVITATION_RECEIVED_ACCEPT_BTN = ".//*[contains(text(),'${space}')]/../../..//button[text()='Accept']";
	public final String ELEMENT_MY_SPACE_INVITATION_RECEIVED_CANCEL_BTN = ".//*[contains(text(),'${space}')]/../../..//button[text()='Ignore']";

	//All Spaces tab
	public final By ELEMENT_MY_SPACE_ALL_SPACES_TAB = By.xpath(".//*[@id='UIPage']//*[contains(@href,'all-spaces')]");
	public final String ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN = ".//*[contains(text(),'${space}')]/../../..//button[text()='Request to Join']";
	public final String ELEMENT_MY_SPACE_ALL_SPACES_JOIN_BTN = ".//*[contains(text(),'${space}')]/../../..//button[text()='Join']";
	public final String ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_PENDING = ".//*[contains(text(),'${space}')]/../../..//*[contains(text(),'Request Pending')]";
    public final By ELEMENT_ALL_SPACE_ACTIVE_TAB=By.xpath(".//*[@id='UIManageAllSpaces']//*[contains(@class,'active')]//*[contains(@href,'all-spaces')]");
	public final String ELEMENT_ALL_SPACE_SPACE_NAME=".//*[contains(@class,'spaceBox')]//*[contains(@href,'$space')]";
    
    //Request pending tab
	public final By ELEMENT_MY_SPACE_REQUEST_PENDING_TAB = By.xpath("//*[contains(@href,'pendingSpace')]");
	public final String ELEMENT_SPACE_CANCEL_BUTTON="//*[@class='spaceTitle']//*[text()='${space}']/../../..//*[text()='Cancel']";
	
	//Members
	public final By ELEMENT_SPACE_GOWIKI = By.xpath("//*[@class='uiIconAppWikiPortlet uiIconDefaultApp']/..//*[@id='wiki']");
	public final By ELEMENT_SPACE_MEMBERS = By.xpath("//*[@data-toggle='tab' and text()='Members']");
	public final By ELEMENT_SPACE_GOSETTINGS = By.xpath("//*[@id='settings']");
	public final By ELEMENT_SPACE_TEXTBOX_USER = By.xpath("//*[@id='user']");
	public final By ELEMENT_SPACE_TEXTBOX_USER_SUGGEST = By.xpath("//*[@class='text' and text()='Mary Williams']");
	public final By ELEMENT_SPACE_BTN_INVITE = By.xpath("//*[text()='Invite']");
	public final String ELEMENT_SPACE_BTN_MANAGER = "//*[text()='${name}']/..//*[@class='switchBtnLabelOff']";
	public final String ELEMENT_SPACE_MEMBER_USER_MANAGER=".//*[@id='existingUsersTable']//*[contains(text(),'${fullName}')]/..//*[@class='switchBtnHandle' and contains(@style,'left: 41px;')]";
	public final String ELEMENT_SPACE_MEMBER_USER_MEMBER=".//*[@id='existingUsersTable']//*[contains(text(),'${fullName}')]/..//*[@class='switchBtnHandle' and not(contains(@style,'left: 41px;'))]";
	
	public final By ELEMENT_SPACE_BTN_ACCEPT_INVITE = By.xpath("//*[text()='Accept']");
	public final By ELEMENT_SPACE_ALLSPACES = By.xpath("//*[text()='All Spaces']");

	//Request to join a space
	public final String ELEMENT_REQUEST_TO_JOIN_SPACE_BTN = "//*[contains(text(),'${space}')]/../../..//button[text()='Request to Join']";
	public final String ELEMENT_REQUEST_PENDING = "//*[contains(text(),'${space}')]/../../..//*[text()='Request Pending']";
	
	//Forum tab
	public final By ELEMENT_FORUM_START_BUTTON_UP = By.xpath("(.//*[@id='UITopicContainer']//*[contains(@class,'uiIconForumCreateTopic ')])[1]");

	//Wiki tab
	public final By ELEMENT_WIKI_HOME_TITLE =By.xpath(".//*[@id='titleInfo']");
	//Document tab
	public final By ELEMENT_DOCUMENT_FOLDER_ADD_BTN = By.xpath(".//*[contains(@class,'uiIconEcmsAddFolder ')]");	
	
	//Agenda tab
    public final By ELEMENT_AGENDA_EVENT_ADD_BTN =By.xpath(".//*[@id='UIActionBarQuickAddEvent']");
	
    //Member tab
	public final By ELEMENT_MEMBER_USER_INFOR = By.xpath(".//*[@id='spaceManagerListBox']");
	public final By ELEMENT_MEMBER_USER_SEARCH= By.xpath(".//*[@id='UIProfileUserSearch']");
	public final By ELEMENT_MEMBER_USER_CONTACT_LIST=By.xpath(".//*[@id='spaceMemberListBox']");
	public final String ELEMENT_MEMBER_USER_NAME = ".//*[@id='spaceMemberListBox']//*[contains(@data-text,'${fullName}')]";
	public final String ELEMENT_MANAGER_USER_NAME = ".//*[@id='spaceManagerListBox']//*[contains(@data-text,'${fullName}')]";
	
	//**************************************************************SPACE SETTING MANAGEMENT ********************************************************\\
	public By ELEMENT_SPACE_SPACE_SETTINGS_TITLE = By.xpath(".//*[@id='UISpaceSettingPortlet']/h3[text()='Space Configuration']");

	// Members tab
	public By ELEMENT_SPACE_SETTINGS_MEMBERS_TAB = By.xpath(".//*[contains(@data-target,'#UISpaceMember-tab')]");
	public By ELEMENT_SPACE_MEMBERS_SELECT_USER = By.xpath("//*[@id='UISpaceMember']//*[@class='uiIconUser uiIconLightGray']");
	public String ELEMENT_SPACE_SELECT_USER_IN_FORM = "//*[@id='UIListUsers']//*[contains(text(),'{$name}')]/../..//*[@class='uiCheckbox']//input";
	public By ELEMENT_ADD = By.xpath("//*[@id='UIUserSelector']//*[contains(text(),'Add')]");
	public By ELEMENT_SPACE_MEMBERS_INVITE = By.xpath("//*[@id='UISpaceMember']//*[contains(text(),'Invite')]");
	public final By ELEMENT_SEARCH_INPUT_USER_NAME = By.xpath(".//*[@id='Quick Search']");
	public final By ELEMENT_SEARCH_USERS_ICON=By.xpath(".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch')]");
	public final By ELEMENT_INPUT_USER=By.xpath(".//*[@id='user']");
	public final By ELEMENT_SELECT_USER_FROM_GROUP=By.xpath(".//*[@id='UISpaceMember']//*[contains(@class,'uiIconGroup')]");
	public final By ELEMENT_ACCESS_ONLY_ONE_MANAGER_NUMBER = By.xpath("(.//*[@id='existingUsersTable']//*[contains(@class,'uiSwitchBtn')]//input[@checked='checked'])[1]");
	public final By ELEMENT_ACCESS_MORE_ONE_MANAGER_NUMBER = By.xpath("(.//*[@id='existingUsersTable']//*[contains(@class,'uiSwitchBtn')]//input[@checked='checked'])[2]");
	public final By ELEMENT_MEMBER_TABLE = By.xpath("(.//*[@id='existingUsersTable']");

	//Search user
	public final String ELEMENT_CLOSE_MESSAGE = "//*[contains(@title,'Close Window')]";
	public final By ELEMENT_INPUT_SEARCH_USER_NAME = By.id("Quick Search");
	public final String ELEMENT_SELECT_SEARCH_OPTION = "//*[contains(@name,'filter')]";
	public final String ELEMENT_SEARCH_ICON_USERS_MANAGEMENT = "//*[contains(@class,'uiIconSearch uiIconLightGray')]";
	public String ELEMENT_MSG_SEARCH_USER_NAME = "User Name";

	//Application tab
	public By ELEMENT_SETTINGS_APP_TAB = By.xpath(".//*[contains(@data-target,'#UISpaceApplication-tab')]");
	public String ELEMENT_DELETE_APP_FROM_TOPBAR = ".//*[@id='UISpaceApplication']//*[contains(text(),'{$application}')]/../..//*[@class='uiIconClose pull-right']";
	public final By ELEMENT_APPLICATION_TAB_ADD_APPLICATION_BTN=By.xpath(".//*[@id='UISpaceApplication-tab']//button[text()='Add Application']");
	public final String ELEMENT_APPLICATION_TAB_APPLICATION_LIST_CONTENT=".//*[@id='UISpaceApplication']//strong[contains(text(),'${app}')]";
	public final String ELEMENT_APPLICATION_TAB_APPLICATION_DELETE_BTN=".//*[@id='UISpaceApplication']//strong[contains(text(),'${app}')]/../..//*[@class='uiIconClose pull-right']";
	public final By ELEMENT_APPLICATION_TAB_LIST_APPLICATIONS=By.xpath(".//*[@id='UISpaceApplication']");

	//Access and Edit tab
	public final By ELEMENT_ACCESS_AND_EDIT_TAB = By.xpath(".//*[contains(@data-target,'#UISpacePermission-tab')]"); 
	public final By ELEMENT_ACCESS_AND_EDIT_TAB_OF_POPUP=By.xpath(".//*[contains(@data-target,'#UISpaceVisibility-tab')]");
	public final By ELEMENT_ACCESS_HIDDEN_RADIO=By.xpath("//input[@value='hidden']");
	public final String ELEMENT_ACCESS_PERMISSION_RADIO="//input[@value='${right}']";
	public final By ELEMENT_ACCESS_PERMISSION_SAVE_BTN=By.xpath(".//*[@id='UISpacePermission']//button[text()='Save']");
	public final By ELEMENT_ACCESS_AND_EDIT_TAB_OF_POPUP_CREATE_BTN=By.xpath(".//button[text()='Create']");
	public final By ELEMENT_ACCESS_ALERTS_POPUP_OK_BTN= By.xpath(".//*[@class='PopupTitle popupTitle'][contains(text(),'Alerts')]/../..//*[@class='btn']");
	public final By ELEMENT_ACCESS_INFO_OK_BTN = By.xpath("//*[@class='PopupContent popupContent']//*[contains(text(),'OK')]");
	public final By ELEMENT_ACCESS_VISIBILITY_RADIO_CHECKED=By.xpath(".//*[@id='UISpacePermission']//input[@value='private' and @checked='checked']");
	public final By ELEMENT_ACCESS_VALIDATION_RADIO_CHECKED=By.xpath(".//*[@id='UISpacePermission']//input[@value='validation' and @checked='checked']']");


	//Add application popup
	public final By ELEMENT_ADD_APPLICATION_POPUP_TITLE=By.xpath("//*[contains(text(),'Space Application Installer')]");
	public final String ELEMENT_ADD_APPLICATION_POPUP_CATEGOGY=".//*[@id='${category}']";
	public final String ELEMENT_ADD_APPLICATION_POPUP_APPLICATION_ADD_BTN =".//*[@id='UIApplicationListSelector']//*[contains(text(),'${app}')]/../..//*[contains(text(),'Add')]";
	public final By ELEMENT_ADD_APPLICATION_POPUP_CLOSE_BTN=By.xpath(".//*[@id='UIAddApplication']//*[@class='uiIconClose pull-right']");


	//Settings tab
	public final By ELEMENT_SPACE_NAME_INPUT = By.xpath("//input[contains(@name,'displayName')]");
	public final By ELEMENT_SPACE_DESCRIPTION_INPUT = By.xpath("//textarea[contains(@name,'description')]");
	public final String ELEMENT_SPACE_CHANGE_ROLE_USER_MEMBER= ".//*[contains(text(),'${user}')]/..//*[@class='uiSwitchBtn']";
	public final String ELEMENT_SPACE_CHANGE_ROLE_USER_MEMBER_DISABLE ="//*[contains(text(),'${user}')]/..//*[contains(@class,'switchBtnDisabled')]";
	public final String ELEMENT_SPACE_DELETE_USER_BTN = ".//*[contains(@onclick,'${user}')]/..//*[@class='uiIconDelete uiIconLightGray']";
	public final String ELEMENT_SPACE_REMOVE_USER_BTN_MEMBER_TABLE = ".//*[contains(@class,'uiIconDelete')]/../../../*[contains(text(),'${fullName}')]";
	public final String ELEMENT_SPACE_MEMBERS_TAB_VALIDATE_REQUEST_jOINT=".//*[contains(@onclick,'${user}')]/..//*[@class='uiIconValidate uiIconLightGray']";
	public final String ELEMENT_SPACE_MEMBERS_TAB_DECLINE_REQUEST_jOINT =".//*[contains(text(),'${user}')]/..//*[contains(@class,'uiIconRemove')]";
	public final By ELEMENT_SPACE_SETTING_TAB = By.xpath(".//*[contains(@data-target,'#UISpaceInfo-tab')]");
	public final By ELEMENT_SPACE_CHANGE_AVATAR_BTN = By.xpath(".//*[@id='UISpaceInfo']//button[text()='Change Picture']");
    public final By ELEMENET_SPACE_UPDATE_SAVE_BTN=By.xpath(".//*[@id='UISpaceInfo']//button[contains(@onclick,'Save')]");
	
	//invitation member
	public final String ELEMENT_SPACE_INVITED_USER_TABLE = ".//*[@id='UISpaceMember']//th[contains(text(),'Invited')]/../../..//*[contains(text(),'${user}')]";
	public final String ELEMENT_SPACE_MEMBERS_USER_TABLE = ".//*[@id='UISpaceMember']//th[contains(text(),'Members')]/../../..//*[contains(text(),'${user}')]";
	public final String ELEMENT_USER_IN_MEMBER_TABLE =".//*[@id='existingUsersTable']//*[contains(text(),'${fullName}')]";
	
	//Invitation a group
	public final By ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_TAB=By.xpath(".//*[contains(@data-target,'#UISpaceGroupBound-tab')]");
	public final By ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX=By.xpath(".//*[@id='UseExistingGroupCheckBox']");
	public final String ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_SELECT_GROUP=".//*[@id='UISocialGroupSelector']//*[contains(@title,'${name}')]";
	public final By ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_SELECTED_LINK=By.xpath(".//*[@id='UISocialGroupSelector']//*[contains(@data-placement,'bottom')]");
	public final By ELEMENT_SPACE_INVITE_USERS_FROM_GROU_SELECTED_GROUP_INFO=By.xpath(".//*[@id='groupId']");
	public final By ELEMENT_SPACE_INVITED_GROUP_BTN=By.xpath(".//*[@id='UISpaceMember']//*[contains(@class,'uiIconGroup')]");
	public final String ELEMENT_SPACE_INVITED_GROUP_NAME="//*[contains(@title,'$name')]";
	public final By ELEMENT_SPACE_INVITED_SELECT_GROUP=By.xpath(".//*[@id='UIUsersInGroupSelector']//*[contains(@data-placement,'bottom')]");

	//Button create
	public By ELEMENET_SPACE_CREATE_BUTTON=By.xpath("//*[@class='uiAction']/*[text()='Create']");

	//Navigation tab
	public final By ELEMENT_SPACE_SETTING_NAVIGATION_TAB = By.xpath(".//*[contains(@data-target,'#UISpaceNavigationManagement-tab')]");
	public final By ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON = By.xpath(".//*[@id='UISpaceNavigationManagement']//button[text()='Add Node']");
	public final String ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST =".//*[@id='UISpaceNavigationNodeSelector']//*[contains(text(),'${name}')]";
	public final String ELEMENT_SPACE_NAVIGAION_ADD_NODE_CHILDREN_UNDER_PARENT = ".//*[@id='UISpaceNavigationNodeSelector']//*[contains(text(),'${childrenNode}')]/../../..//*[contains(text(),'${parentNode}')]";
	public final By ELEMENT_SPACE_NAVIGATION_UP_LEVEL_BUTTON = By.xpath(".//*[@id='UISpaceNavigationNodeSelector']//*[@class='uiIconUpLevel uiIconLightGray']");
	public final By ELEMENT_SPACE_NAVIGATION_NODE_LIST =By.xpath(".//*[@id='UISpaceNavigationNodeSelector']");

	//Add/Edit page node popup
	public final By ELEMENT_SPACE_NAVIGATION_ADD_EDIT_NODE_TITLE = By.xpath(".//*[@id='AddNode']//*[contains(.,'Add/ Edit Page Node')]");
	public final By ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_NAME = By.xpath(".//*[@id='name']");
	public final By ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_SAVE = By.xpath(".//*[@id='UIPageNodeForm']//button[text()='Save']");
	public final By ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_LABEL = By.xpath(".//*[@id='UIPageNodeForm']//*[contains(text(),'Label')]/..//input");
	public final By ELEMENT_SPACE_NAVIGATION_ADD_EDIT_POPUP_LANGUAGE = By.xpath(".//*[@id='PageNodeSetting-tab']//*[@class='selectbox' and @name='languages']");
	public final By ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE = By.id("switchmode");
	public final By ELEMENT_INPUT_LABEL = By.id("i18nizedLabel");
	public final By ELEMENT_PAGE_SELECTOR_TAB = By.xpath(".//*[@id='AddNode']//a[text()='Page Selector']");
	public final By ELEMENT_INPUT_PAGE_NAME = By.id("pageName");
	public final By ELEMENT_INPUT_PAGE_TITLE = By.id("pageTitle");
	public final By ELEMENT_CREATE_PAGE_LINK = By.xpath(".//*[@id='UIPageSelector']//*[@class='uiIconAddPage uiIconWhite']");
	public final By ELEMENT_SEARCH_SELECTOR_PAGE_LINK = By.xpath(".//*[@id='UIPageSelector']//*[@class='uiIconSelectPage']");

	//Context menu
	public final By ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_EDIT = By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Edit this Node')]");
	public final By ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_DELETE= By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Delete Node')]");
	public final By ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_ADD_NEW_NODE = By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Add new Node')]");
	public final By ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_EDIT_NODE_PAGE = By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//*[@class='uiIconEditPageNode']");
	public final By ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_COPY_NODE = By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Copy Node')]");
	public final By ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_PASTE_NODE = By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Paste Node')]");
	public final By ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_CLONE_NODE = By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Clone Node')]");
	public final By ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_CUT_NODE = By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Cut Node')]");
	public final By ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_MOVE_UP = By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Move Up')]");
	public final By ELEMENT_SPACE_NAVIGATION_CONTEXT_MENU_MOVE_DOWN = By.xpath(".//*[@id='SpaceNavigationNodePopupMenu']//a[contains(.,'Move Down')]");

	public final String ELEMENT_SPACE_NAVIGATION_NODE_POSITION = "//*[@class='childrenContainer nodeGroup']/li[${position}]//a[contains(text(),'${nodeName}')]";

	//Access space information
	public final By ELEMENT_SPACE_ACCESS_RESTRICED_AREA_TITLE=By.xpath(".//*[@id='UISpaceAccessPortlet']//h3[text()='Restricted Area']");
	public final By ELEMENT_SPACE_ACCESS_INFO=By.xpath(".//*[@class='spaceAccessInfo']");
	public final By ELEMENT_SPACE_ACCESS_JOIN_BTN=By.xpath(".//*[@title='Join']");
	public final By ELEMENT_SPACE_ACCESS_REQUEST_JOIN_BTN=By.xpath(".//*[@title='Request to Join']");

	public final By ELEMENT_SPACE_ACCESS_SPACE_NOT_FOUND_TITLE=By.xpath(".//*[@id='UISpaceAccessPortlet']//h3[text()='Space not found']");
	public final By ELEMENT_SPACE_ACCESS_SPACE_NOT_FOUND_INFO=By.xpath(".//*[@id='UISpaceAccessPortlet']//*[contains(text(),'No space is available at this URL.')]");
	public final By ELEMENT_SPACE_ACCESS_SPACE_NOT_FOUND_FIND_BTN=By.xpath(".//*[@id='UISpaceAccessPortlet']//a[text()='Find Spaces']");
	public final By ELEMENT_SPACE_ACCESS_SPACE_DENIED = By.xpath(".//*[@id='UISpaceAccessPortlet']//h3[text()='Access Denied']");
	public final By ELEMENT_SPACE_ACCESS_SPACE_DENIED_INFO=By.xpath(".//*[@class='spaceAccessInfo']");
	public final String ELEMENT_SPACE_ACCESS_SPACE_REQUEST_JOIN_MESSAGE=".//*[contains(text(),'You must be a member of the space')]//b[contains(text(),'$space')]/../..//*[contains(.,'to view this page')]";

	//message
	public final String ELEMENT_SPACE_NAVIGATION_COPY_AT_SAME_LEVEL = "This node name already exists.";

	//Warining popup
    public final String ELEMENT_SPACE_WARNING_MESSAGE=".//*[contains(@class,'UIPopupWindow')]//*[contains(text(),'${warningText}')]";	
	
}