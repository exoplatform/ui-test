package org.exoplatform.selenium.platform.forum;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class ForumLocator extends PlatformBase {

	/***************************************************HOME PAGE************************************************************************/
	//Action bar
	public final By ELEMENT_ACTIONBAR_USER = By.xpath("//*[@class='uiIconUser uiIconLightGray']");
	public final By ELEMENT_ACTIONBAR_SETTINGS = By.xpath("//*[@class='uiIconSetting uiIconLightGray']");

	public final By ELEMENT_ACTIONBAR_ADMINISTRATION = By.xpath(".//*[@id='Administrations']//div[@data-toggle='dropdown']");
	public final By ELEMENT_ACTIONBAR_BOOKMARK_MANAGER = By.xpath(".//*[@id='OpenBookMark']//*[@class='uiIconBookmark uiIconLightGray']");
	public final By ELEMENT_ACTIONBAR_BOOKMARK_ICON=By.xpath(".//*[@id='UITopicDetail' or @id='UITopicContent' or @id='UICategory']//*[@class='actionIcon']/*[@class='uiIconBookmark uiIconLightGray']");

	public final By ELEMENT_ACTIONBAR_MANAGECAT = By.xpath("//*[@class='uiIconForumManageCategory uiIconForumLightGray']");
	public final By ELEMENT_ACTIONBAR_PRIVATE_MESSAGE = By.xpath(".//*[@id='uiRightActionBar']//a[contains(.,'Private Messages')]");
	public final String ELEMENT_ACTIONBAR_TOPIC_TAGDELETE = ".//*[@id='UITopicDetail']//a[@data-original-title='${tag}']/..//*[@class='uiIconClose uiIconLightGray']";

	public final By ELEMENT_FORUM_TOPIC_LOCK = By.xpath("//*[@class='uiIconLockMedium']");
	public final By ELEMENT_FORUM_TOPIC_UNLOCK = By.xpath("//*[@class='uiIconUnlockMedium']");
	public final By ELEMENT_FORUM_TOPIC_POSTLOCKED = By.xpath("//*[@class='uiLockIcon btn disabled']/../../../div[2]//*[@class='pull-left actionContainer']");
	public final By ELEMENT_FORUM_TOPIC_POSTLOCKEDMESSAGE = By.xpath("//*[text()='You cannot reply to this topic.']");

	public final String ELEMENT_FORUM_TOPIC_MARKAVERAGE = "//*[@data-original-title='${rate}']";
	public final String ELEMENT_FORUM_NAVIGATION_BREADCRUMB = "//*[@class='breadcrumb']//*[text()='${name}']";
	
	public final By ELEMENT_MY_SUBSCRIPTIONS_EMAIL_UPDATE= By.id("EmailAddress");

	//Private Message
	public final By ELEMENT_PRIVATE_MESSAGE_COMPOSE_MESSAGE_TAB = By.xpath(".//*[@id='UIPrivateMessageForm']//*[contains(text(),'Compose New Message')]");
	public final By ELEMENT_COMPOSE_NEW_MESSAGE_INPUT_SEARCH_USER_NAME = By.xpath(".//*[@id='QuickSearch']");
	public final String ELEMENT_COMPOSE_NEW_MESSAGE_SELECT_SEARCH_OPTION = "//*[contains(@name,'filter')]";
	public final String ELEMENT_COMPOSE_NEW_MESSAGE_SEARCH_ICON = ".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch uiIconLightGray')]";
	public final By ELEMENT_PRIVATE_MESSAGE_CANCEL = By.xpath(".//*[@id='UIPrivateMessageForm']//*[contains(text(),'Cancel')]");
	public final By ELEMENT_COMPOSE_NEW_MESSAGE_USER_SELECTOR = By.xpath(".//*[@id='MessageTab']//*[@class='uiIconUser uiIconLightGray']");
	public final By ELEMENT_COMPOSE_NEW_MESSAGE_CLOSE_USER_SELETOR = By.xpath(".//*[@id='UIUserSelector']//*[contains(text(),'Close')]");
	
	//Manage User
	public final By ELEMENT_FORUM_USER_LIST = By.xpath(".//*[@id='ManageModerator']//*[contains(@class,'uiIconUser')]");
	public final By ELEMENT_MANAGE_USER_INPUT_SEARCH_USER_NAME = By.xpath(".//*[@id='SearchUser']");
	public final String ELEMENT_MANAGE_USER_SEARCH_ICON = ".//*[@id='UIModeratorManagementForm']//*[contains(@class,'uiIconSearch uiIconLightGray')]";
	public final By ELEMENT_USER_FORM_CLOSE = By.xpath(".//*[@id='UIModeratorManagementForm']//*[contains(text(),'Close')]");
	
	//Add Category popup
	public final By ELEMENT_ADDCATEGORY_POPUP_CATEGORY_TAB= By.xpath(".//*[@id='UICategoryForm']//a[text()='Category']");
	public final By ELEMENT_ADDCATEGORY_POPUP_PERMISSION_TAB= By.xpath(".//*[@id='UICategoryForm']//a[text()='Permissions']");

	//Add forum popup
	public final By ELEMENT_ADDFORUM_POPUP_ADDFORUM_TAB= By.xpath(".//*[@id='UIForumForm']//a[text()='Add Forum']");

	//Home page of forum
	public final String ELEMENT_FORUM_HOME_TOPIC_TILTE=".//*[@id='UICategories']//*[@class='uiIconForumTopic uiIconForumLightGray']/..//*[contains(text(),'${topic}')]";

	//Forum content
	public final String ELEMENT_DETAIL_FORUM_CATEGORY_TITLE= ".//*[@id='UIForumDescription']//strong[text()='${title}']";
	public final String ELEMENT_FORUM_TITLE_LINK = ".//*[contains(@id,'UIContextPopupMenu') and contains(text(),'${name}')]";
	public final String ELEMENT_TOPIC_TITLE_LINK=".//*[contains(@id,'UIContextPopupMenu') and contains(text(),'${name}')]/i";
	public final String ELEMENT_FORUM_DETAIL_FORUM_NAME_LINK=".//*[text()='${name}']";
	public final By ELEMENT_FORUM_WHAT_GOING_ON = By.xpath("//div[contains(text(),'Going on?')]");
	public final String ELEMENT_SELECT_FORUM_TOPIC = "//*[contains(text(),'${link}')]";

	public final By ELEMENT_FORUM_MOREACTIONS_MOVE = By.xpath("//*[@class='uiIconMove']");
	public final By ELEMENT_FORUM_MOREACTIONS_ADDPOLL = By.xpath("//*[text()='Add Poll']");
	public final String ELEMENT_FORUM_MOVE_CATEGORYCOLLAPSE = "//*[@class='uiIconNode collapseIcon' and contains(.,'${forum}')]";
	public final String ELEMENT_FORUM_MOVE_CATEGORYEXPAND = "//*[@class='uiIconNode expandIcon' and contains(.,'${forum}')]";
	public final String ELEMENT_FORUM_MOVE_FORUM = "//*[@class='uiIconUIForms uiIconLightGray' ]/../..//*[contains(text(),'${forum}')]";

	//Form add poll
	public final By ELEMENT_FORUM_ADDPOLL_QUESTION = By.xpath("//*[@id='Question']");
	public final By ELEMENT_FORUM_ADDPOLL_OPTION0 = By.xpath("//*[@id='Option0']");
	public final By ELEMENT_FORUM_ADDPOLL_OPTION1 = By.xpath("//*[@id='Option1']");
	public final By ELEMENT_FORUM_ADDPOLL_CLOSE = By.xpath("//*[@id='TimeOut']");
	public final By ELEMENT_FORUM_POLL_SUBMIT = By.xpath("//*[text()='Submit Poll']");
	public final By ELEMENT_FORUM_ICON_EDIT = By.xpath("//*[text()='Edit']");
	public final By ELEMENT_FORUM_POLL_DELETE = By.xpath("//*[@class='uiIconDelete uiIconLightGray']");
	public final By ELEMENT_FORUM_POLL_DELETECONFIRM = By.xpath("//*[contains(text(),'Are you sure you want to delete this poll ?')]/../../..//*[@class='btn actionOK']");
	public final String ELEMENT_SELECT_TOPIC = "//*[contains(text(),'{$topic}')]";


	//reply on topic
	public final By ELEMENT_TOPIC_REPLY = By.xpath("//*[@class='pull-left actionContainer']//*[@class='uiPostReplyIcon btn btn-primary']");
	public final By ELEMENT_TOPIC_REPLY_TITLE = By.xpath("//*[@id='PostTitle']");
	public final String ELEMENT_TOPIC_PRIVATE_BUTTON = ".//*[contains(text(),'${post}')]/../../../.././/*[contains(@class,'btn')]//*[contains(text(),'Private')]";


	//administration
	public final By ELEMENT_ACTIONBAR_ADMIN_BANIP = By.xpath(".//*[@id='Administrations']//a[contains(.,'Banned IPs')]");
	public final By ELEMENT_ACTIONBAR_ADMIN_BBCODE = By.xpath(".//*[@id='Administrations']//a[contains(.,'BBCodes')]");
	public final By ELEMENT_ACTIONBAR_ADMIN_IMPORT = By.xpath(".//*[@id='Administrations']//a[contains(.,'Import')]");
	public final By ELEMENT_ACTIONBAR_ADMIN_EXPORT = By.xpath(".//*[@id='Administrations']//a[contains(.,'Export')]");

	//export category popup
	public final By ELEMENT_EXPORTCAT_EXPORTALL = By.xpath("//*[@id='checkAll']");
	public final String ELEMENT_EXPORTCAT_EXPORT = "//*[contains(text(),'${title}')]/..//*[@class='uiCheckbox']//*[@class='checkbox']";

	// add BBCODE
	public final By ELEMENT_ADMIN_BBCODE_ADDBBCODE = By.xpath("//*[text()='Add BBCode']");
	public final By ELEMENT_BBCODE_ADDBBCODEFORM_TAG = By.xpath("//*[@id='TagName']");
	public final By ELEMENT_BBCODE_ADDBBCODEFORM_REPLACEMENT = By.xpath("//*[@id='Replacement']");
	public final By ELEMENT_BBCODE_ADDBBCODEFORM_DESCRIPTION = By.xpath("//*[@id='Description']");
	public final By ELEMENT_BBCODE_ADDBBCODEFORM_EXAMPLE = By.xpath("//*[@id='Example']");
	public final String ELEMENT_BBCODE_EDITBBCODE = "//*[contains(text(),'${tag}')]/../..//*[ @class='uiIconEdit uiIconLightGray']";
	public final String ELEMENT_BBCODE_DELETEBBCODE = "//*[contains(text(),'${tag}')]/../..//*[ @class='uiIconDelete uiIconLightGray']";

	//Breadcumb
	public By ELEMENT_CATEGORY_BREADCUMB_HOME=By.xpath("//*[@id='UIBreadcumbs']//*[text()='Home']");
	public String ELEMENT_CATEGORY_FORUM_BREAD = "//*[text()='${category}']/../..//*[text()='${forum}']";

	//Contextmenu by right clicking
	public final By ELEMENT_WATCH = By.xpath("//*[contains(text(),' Watch')]/../..//*[@class='uiIconWatch uiIconLightGray']");
	public final By ELEMENT_UNWATCH = By.xpath("//*[contains(text(),' Unwatch')]/../..//*[@class='uiIconWatch uiIconLightGray']");

	//Message and popup inform
	public final String MESSAGE_WATCH = "You are now watching this item.";
	public final By ELEMENT_OK_INFOR_POPUP = By.xpath("//div[@class='UIPopupWindow UIDragObject uiPopup']/.//a[text()='OK']");
	public final String MESSAGE_UNWATCH = "You are no longer watching this item.";	
	//Bookmark
	public final String ELEMENT_FORUM_BOOKMARK_NAME = "//*[@class='uiShowBookMarkForm resizable']//*[text()='${name}']";
	public final String ELEMENT_FORUM_BOOKMARK_DELETE="//*[@class='uiShowBookMarkForm resizable']//*[text()='${name}']/../..//*[@class='uiIconDelete uiIconLightGray']";
	public final By ELEMENT_FORUM_BOOKMARK_CLOSE_ICON = By.xpath(".//*[@id='UIForumPopupWindow']//div[@class='ClosePopup']");

	//Category right click option
	public final String ELEMENT_FORUM_CONTEXT_MENU_BOOKMARK=".//a[contains(text(),'${name}')]/..//a[contains(.,'Bookmarks')]";

	//Forum/Category portlets
	public By ELEMENT_FORUM_PORTLET = By.id("UIForumPortlet");
	public final String ELEMENT_PORTLET_CONTENT_LINK=".//*[contains(text(),'${topic}')]";

	//Topic 
	public final String ELEMENT_TOPIC_LAST_REPLY = ".//*[contains(text(),'${reply}')]/../../../../following::div[7][@class='uiBox forumQuickReply uiCollapExpand']";
	public final String ELEMENT_GMAIL_CONTENT_TOPIC = ".//span[contains(.,'\"{$title}\"')]";

	//Button
	public final By ELEMENT_OK_BTN = By.xpath("//*[@class='btn actionOK']");
	public final String ELEMENT_BBCODE_TAG_VERIFY = "//*[contains(text(),'${tag}')]";
	public final By ELEMENT_BBCODE_USE_OPTION =By.xpath("//*[@id='UseOption']");
	public final By ELEMENT_BBCODE_CONFIRM_DELETETAG = By.xpath("//*[text()='Are you sure you want to delete this BB Code ?']/../../..//*[@class='btn actionOK']");


	//Settings 
	public final By ELEMENT_FORUM_SETTINGS = By.xpath(".//*[@id='EditProfile']//*[contains(@class,'uiIconSetting uiIconLightGray')]");
	public final By ELEMENT_FORUM_SETTINGS_FORUMSETTINGS = By.xpath("//*[text()='Forum Settings']");
	public final By ELEMENT_FORUM_SETTINGS_MYSUSCRIB = By.xpath("//*[text()='My Subscriptions']");
	public final By ELEMENT_FORUM_SETTINGS_SCREENNAME = By.xpath("//*[@id='ScreenName']");
	public final By ELEMENT_FORUM_SETTINGS_MAXTHREADS = By.xpath("//*[@name='MaximumThreads']");
	public final By ELEMENT_FORUM_SETTINGS_EMAILADRESS = By.xpath("//*[@id='EmailAddress']");
	public final By ELEMENT_FORUM_SETTINGS_UPDATE = By.xpath("//*[text()='Update']");
	public final By ELEMENT_FORUM_SETTINGS_SAVE = By.xpath("//*[text()='Save']");
	public final By ELEMENT_FORUM_SETTINGS_SUBMIT = By.xpath("//*[text()='Submit']");
	public final By ELEMENT_FORUM_USERS_FORUMSETTINGS = By.xpath("//*[@data-target='#ForumUserOption-tab']");
	public final By ELEMENT_FORUM_USERS_BAN = By.xpath("//*[text()='Ban User']");
	public final By ELEMENT_FORUM_USERS_TOPICS = By.xpath("//*[text()='Topics']");
	public final By ELEMENT_FORUM_USERS_POSTS = By.xpath("//*[text()='Posts']");
	public final By ELEMENT_FORUM_CLOSEBTN = By.xpath("//*[@class='btn' and text()='Close']");
	public final String ELEMENT_FORUM_VERIFY_USER = "//*[text()='${user}']";
	public final String ELEMENT_MY_SUBSCRIPTION_TITLE = "//*[@id='ForumUserWatches-tab']//*[contains(text(),'${link}')]";

	//forum & category
	public final String ELEMENT_FORUM_TITLECAT = "//*[text()='${title}']";
	public final String ELEMENT_CATEGORY_TITLE = ".//*[contains(@class,'textTitleCategories pull-left')]//*[contains(text(),'${title}')]";

	//add forum
	public final By ELEMENT_FORUM_FORUM_NAME = By.xpath("//*[@id='ForumTitle']");

	//users
	public final String ELEMENT_FORUM_USERS_EDIT = "//*[text()='${name}']/..//*[@class='uiIconEdit uiIconLightGray']";
	public final By ELEMENT_FORUM_USERS_POPUP_SEARCH_FIELD=By.xpath(".//*[@id='SearchUser']");

	public final By ELEMENT_FORUM_MESSAGE = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");

	//search
	public final By ELEMENT_SEARCH_TEXTBOX = By.xpath("//*[@id='inputValue']");
	public final By ELEMENT_SEARCH__ADVANCEDSEARCH_TEXTBOX = By.xpath("//*[@id='SearchValue']");
	public final String ELEMENT_SEARCH_FORUM_POST = "//*[text()='Post']/../..//*[text()='${name}']";
	public final String ELEMENT_SEARCH_FORUM_TOPIC = "//*[text()='Topic']/../..//*[text()='${name}']";
	public final String ELEMENT_SEARCH_FORUM_FORUM = "//*[text()='Forum']/../..//*[text()='${name}']";
	public final String ELEMENT_SEARCH_FORUM_CATEGORY = "//*[text()='Category']/../..//*[text()='${name}']";
	public final By ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH = By.xpath("//*[text()='Advanced Search']");
	public final By ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCHLOCATION = By.xpath("//*[@name='SearchType']");
	public final By ELEMENT_SEARCH_FORUM_ADVANCEDSEARCH_SEARCH = By.xpath("//*[@class='btn' and text()='Search']");

	public final By ELEMENT_FILENAME_INPUT=By.id("FileName");
	public final By ELEMENT_DELETE_ICON = By.xpath("//*[@class='uiIconDelete uiIconLightGray']");
	public final By ELEMENT_SAVE_BTN = By.xpath("//*[text()='Save']");
	public final By ELEMENT_SUBMIT_BUTTON = By.xpath("//*[text()='Submit']");

	//BBcode popup
	public final By ELEMENT_EDITSITE_SAVEBTN = By.xpath("//*[@class='btn' and text()='Save']");
	public final By ELEMENT_BBCODE_POPUP_CLOSEBTN= By.xpath(".//*[@id='BBCodeManagerForm']//button[text()='Close']");

	/***************************************************FORUM MANAGEMENT************************************************************************/
	//Home page of forum
	public final String ELEMENT_FORUM_FORUM_NAME_LINK=".//*[text()='${name}']";
	public final String ELEMENT_FORUM_CATEGORY_TITLE= ".//*[@id='UIForumDescription']//strong[text()='${title}']";
	public final By ELEMENT_FORUM_START_TOPIC_DISABLE = By.xpath("//*[@id='UITopicContainer']//*[@data-original-title='Forum is closed for posting.']");
	public final By ELEMENT_FORUM_START_TOPIC_BUTTON = By.xpath("//*[@class='btn btn-primary pull-left']");
	
	//Action bar
	public final By ELEMENT_ACTIONBAR_ADDFORUM = By.xpath("//*[@class='uiIconForumCreateForum uiIconForumLightGray']");
	
	//Add/Edit forum popup
	public final By ELEMENT_EDITFORUM_POPUP_TITLE = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Forum']");
	public final By ELEMENT_ADDFORUM_POPUP_TITLE= By.id("ForumTitle");
	public final By ELEMENT_ADDFORUM_POPUP_ORDER= By.id("ForumOrder");
	public final By ELEMENT_ADDFORUM_POPUP_DESCRIPTION= By.id("Description");
	public final By ELEMENT_ADDFORUM_POPUP_SAVE_BUTTON= By.xpath(".//*[@id='UIForumForm']//button[text()='Save']");
	public final By ELEMENT_ADDFORUM_POPUP_CANCEL_BUTTON= By.xpath(".//*[@id='UIForumForm']//button[text()='Cancel']");
	public final By ELEMENT_FORUM_PERMISSION_TAB = By.xpath(".//*[@id='UIForumForm']//*[contains(text(),'Permissions')]");
	public final By ELEMENT_FORUM_PERMISSION_TAB_USER_SELECTOR = By.xpath(".//*[@id='forumPermission']//*[@class='uiIconUser uiIconLightGray']");
	public final By ELEMENT_FORUM_CANCEL = By.xpath(".//*[@id='UIForumForm']//*[contains(text(),'Cancel')]");
	public final By ELEMENT_FORUM_PERMISSION_CLOSE_USER_SELETOR = By.xpath(".//*[@id='UIUserSelector']//*[contains(text(),'Close')]");
	public final By ELEMENT_FORUM_PERMISSION_INPUT_SEARCH_USER_NAME = By.xpath(".//*[@id='QuickSearch']");
	public final String ELEMENT_FORUM_PERMISSION_SELECT_SEARCH_OPTION = "//*[contains(@name,'filter')]";
	public final String ELEMENT_FORUM_PERMISSION_SEARCH_ICON = ".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch uiIconLightGray')]";
	
	//More Action menu
	public final By ELEMENT_MORE_ACTION = By.xpath("//*[@data-toggle='dropdown']/*[@class='uiIconSettings uiIconLightGray']");;
	public final By ELEMENT_MODERATOR=By.xpath("//*[contains(@class,'uiIconForumModerator')]");
	public final By ELEMENT_EDIT_FORUM = By.xpath("//*[contains(@href, 'EditForum')]");
	public final By ELEMENT_DELETE_FORUM = By.xpath("//*[contains(@data-action, 'RemoveForum')]");
	public final By ELEMENT_MOVE_FORUM = By.xpath("//*[contains(@href, 'MoveForum')]");
	public final By ELEMENT_START_TOPIC_BUTTON = By.xpath("//*[contains(@href, 'AddTopic')]");
	public final By ELEMENT_START_TOPIC_BTN = By.xpath(".//*[contains(@class,'uiIconForumCreateTopic')]");
	public final By ELEMENT_LOCK_FORUM = By.className("uiIconLockMedium");
	public final By ELEMENT_UNLOCK_FORUM = By.className("uiIconUnlockMedium");
	public final By ELEMENT_CLOSE_FORUM = By.xpath("//a[contains(@href,'SetCloseForum')]");
	public final By ELEMENT_OPEN_FORUM = By.xpath("//a[contains(@href,'SetOpenForum')]");
	
	//Start topic popup
	public final By ELEMENT_START_TOPIC_POPUP_TITLE = By.xpath(".//*[@id='UIForumPopupWindow']//span[@class='PopupTitle popupTitle']");
	
	//Popup confirmation
	public By ELEMENT_OK_DELETE = By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='OK']");
	
	//Move forum popup
	public final By ELEMENT_POPUP_MOVE_FORUM = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Move Forum']");
	
	
	/***************************************************CATEGORY MANAGEMENT************************************************************************/
    //Home page
	public final String ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK = ".//*[text()='${name}']";
	
	//Action bar
	public final By ELEMENT_ACTIONBAR_ADDCATEGORY = By.xpath("//*[@class='uiIconAddCategory uiIconLightGray']");
	
	//Manage menu
	public final By ELEMENT_MENU_MANAGE_CATEGORY = By.xpath("//*[@class='uiIconForumManageCategory uiIconForumLightGray']");
	public final By ELEMENT_EDIT_CATEGORY = By.xpath("//*[@class='dropdown-menu uiCategoryPopupMenu']/li[1]/a");
	public final By ELEMENT_EXPORT_FORUM  = By.xpath("//*[@class='dropdown-menu uiCategoryPopupMenu']/li[2]/a");
	public final By ELEMENT_IMPORT_FORUM = By.xpath("//*[@class='dropdown-menu uiCategoryPopupMenu']/li[3]/a");
	public final By ELEMENT_DELETE_CATEGORY = By.xpath("//*[@id='UICategoryConfirm0' and contains(text(),'Delete')]");
	
	//Edit category
	public final By ELEMENT_PERM_TAB = By.xpath("//*[contains(@data-toggle,'tab')][contains(.,'Permissions')]");
	public final By ELEMENT_EDITCATEGORY_PERM_ADD_BTN=By.xpath("//*[contains(@id,'Permission')]//*[contains(@class,'addButton')]");
	
	//Add category popup
	public final By ELEMENT_ADDCATEGORY_POPUP_TITLE= By.id("CategoryTitle");
	public final By ELEMENT_ADDCATEGORY_POPUP_ORDER= By.id("CategoryOrder");
	public final By ELEMENT_ADDCATEGORY_POPUP_DESCRIPTION= By.id("Description");
	public final By ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON= By.xpath(".//*[@id='UICategoryForm']//button[text()='Save']");
	public final By ELEMENT_ADDCATEGORY_POPUP_CANCEL_BUTTON= By.xpath(".//*[@id='UICategoryForm']//button[text()='Cancel']");
	public final By ELEMENT_ADDCATEGORY_RESTRICTED_AUDIENCE = By.xpath(".//*[@id='DetailTab']//*[@class='uiIconUser uiIconLightGray']");
	public final By ELEMENT_RESTRICTED_AUDIENCE_INPUT_SEARCH_USER_NAME = By.xpath(".//*[@id='QuickSearch']");
	public final String ELEMENT_RESTRICTED_AUDIENCE_SELECT_SEARCH_OPTION = "//*[contains(@name,'filter')]";
	public final String ELEMENT_RESTRICTED_AUDIENCE_SEARCH_ICON = ".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch uiIconLightGray')]";
	public final By ELEMENT_RESTRICTED_AUDIENCE_CLOSE_USER_SELETOR = By.xpath(".//*[@id='UIUserSelector']//*[contains(text(),'Close')]");
	public final By ELEMENT_CATEGORY_CANCEL = By.xpath(".//*[@id='UICategoryForm']//*[contains(text(),'Cancel')]");
	public final By ELEMENT_CATEGORY_PERMISSION_TAB = By.xpath(".//*[@id='UICategoryForm']//*[contains(text(),'Permissions')]");
	public final By ELEMENT_CATEGORY_PERMISSION_TAB_USER_SELECTOR = By.xpath(".//*[@id='PermissionTab']//*[@class='uiIconUser uiIconLightGray']");
	
	public final String ELEMENT_CATEGORY_DELETE_CONFIRM_MSG="Are you sure you want to delete this category ?";
	
	//Export forum popup
	public final By ELEMENT_EXPORT_FORUM_EXPORTALL = By.id("checkAll");
	public final String ELEMENT_EXPORT_FORUM_EXPORT = "//*[contains(text(),'${title}')]/..//*[@class='uiCheckbox']//*[@class='checkbox']";
	
	/***************************************************FORUM PERMISSION************************************************************************/
	public final By ELEMENT_PERM_MOD_CHECKBOX=By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[2]//input");
	public final By ELEMENT_PERM_STARTTOP_CHECKBOX=By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[3]//input");
	public final By ELEMENT_PERM_POSTREPLY_CHECKBOX=By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[4]//input");
	public final By ELEMENT_PERM_VIEWPOST_CHECKBOX=By.xpath("//*[@id='UIPermissionGrid']//tr[1]/td[5]//input");
	public final By ELEMENT_PERM_ROLE_ICON= By.xpath(".//*[contains(@id,'Permission')]//*[contains(@class,'uiIconMembership')]");
	public final By ELEMENT_RESTRICTED_ROLE_ICON= By.xpath(".//*[@id='DetailTab']//*[contains(@class,'uiIconMembership')]");
	public final By ELEMENT_MESSAGE_ROLE_ICON= By.xpath(".//*[@id='MessageTab']//*[contains(@class,'uiIconMembership')]");
	public final By ELEMENT_SELECT_MEMBERSHIP_POPUP = By.xpath(".//*[@id='UIPopupActionChildPopupWindow']");
	public final By ELEMENT_RESTRICTED_SELECT_MEMBERSHIP_POPUP = By.xpath("//*[@id='UIForumChildPopupWindow']//*[contains(@class,'PopupContent')]");
	public final By ELEMENT_MESSAGE_SELECT_MEMBERSHIP_POPUP = By.xpath(".//*[@id='UIPopupWindow']");
	public final String ELEMENT_SELECT_RIGHT_PARENT_GROUP = "//*[contains(@id,'UIPopupActionChildPopupWindow')]//a[contains(.,'$group')]";
	public final String ELEMENT_RESTRICTED_SELECT_RIGHT_PARENT_GROUP = "//*[@id='UIForumChildPopupWindow']//*[contains(@class,'PopupContent')]//a[contains(.,'$group')]";
	public final String ELEMENT_MESSAGE_SELECT_RIGHT_PARENT_GROUP = ".//*[@id='UIPopupWindow']//a[contains(.,'$group')]";
	public final By ELEMENT_PERM_ADD_BTN=By.xpath("//*[contains(@id,'Permission')]//*[contains(@class,'addButton')]");
	
	/***************************************************TOPIC MANAGEMENT************************************************************************/
	public By ELEMENT_POST_REPLY =By.xpath("//*[@id='UITopicDetail']//*[@class='pull-left actionContainer']/a[contains(text(),'Post Reply')]");
	public final String ELEMENT_TOPIC_POST_TITLE = "//*[@class='postViewTitle']/..//*[contains(text(),'${name}')]";
	public final String ELEMENT_TOPIC_POST_CONTENT = "//*[@class='postContentContainer']/..//*[contains(text(),'${name}')]";
	//Action bar
	public final By ELEMENT_ACTIONBAR_TOPIC_TAG = By.xpath("//*[@class='uiIconTag uiIconLightGray']");
	public final By ELEMENT_ACTIONBAR_TOPIC_TAGNAME = By.xpath("//*[@id='AddTag']");
	public final By ELEMENT_ACTIONBAR_TOPIC_RATE = By.xpath("//*[@class='uiIconForumStar uiIconForumLightGray']");
	public final String ELEMENT_ACTIONBAR_TOPIC_TAGPRESENT = ".//*[@id='UITopicDetail']//a[@data-original-title='${tag}']";
	
	//Tag of topic
    public final By ELEMENT_FORUM_TOPIC_ADD_TAG = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Add Tag')]");
	
    //Rate
    public final String ELEMENT_FORUM_VOTE_MARK = "//*[@data-original-title='${star}']";
	public String ELEMENT_POST_TITLE =".//*[@class='postViewTitle'][contains(text(),'${title}')]";
	
	//More Action menu
	public final By ELEMENT_EDIT_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Edit')]");
	public final By ELEMENT_DELETE_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Delete')]");
	public final By ELEMENT_MOVE_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Move')]");
	public final By ELEMENT_CLOSE_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Close')]");
	public final By ELEMENT_OPEN_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Open')]");
	public final By ELEMENT_LOCK_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Lock')]");
	public final By ELEMENT_UNLOCK_TOPIC = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Unlock')]");
	public final By ELEMENT_ADD_POLL = By.xpath(".//*[@id='UITopicDetail']//a[contains(text(),'Add Poll')]");
	//public final By ELEMENT_FORUM_ICON_CLOSE = By.xpath("//*[@class='uiIconMinus']");
	
	// Post on a topic
	public final By ELEMENT_TOPIC_POST_A_REPLY_TITLE = By.id("PostTitle");
	public final By ELEMENT_TOPIC_CANCEL_A_POST = By.xpath("//*[@id='UIPostForm']//*[contains(text(),'Cancel')]");
	public final By ELEMENT_FORUM_ADDPOST = By.xpath("//*[@class='pull-left actionContainer']//*[@class='uiPostReplyIcon btn btn-primary']");
	public final By ELEMENT_FORUM_POST_TITLE = By.xpath("//*[@id='PostTitle']");
	public final By ELEMENT_FORUM_ADDTOPIC = By.xpath(".//*[@id='UITopicContainer']//*[contains(@class,'uiIconForumCreateTopic ')][1]");
	public final By ELEMENT_FORUM_TOPIC_TITLE = By.xpath("//*[@id='ThreadTitle']");
	public final String ELEMENT_FORUM_TOPIC_LINK = ".//*[contains(text(),'${name}')]";
	
	//Start Topic popup
	public final By ELEMENT_START_TOPIC_POPUP_TITLE_FILED = By.id("ThreadTitle");
	public final By ELEMENT_TOPIC_PERMISSION_TAB = By.xpath(".//*[@id='UITopicForm']//*[contains(text(),'Permissions')]");
	public final By ELEMENT_TOPIC_PERMISSION_TAB_USER_SELECTOR = By.xpath(".//*[@id='ThreadPermission']//*[@class='uiIconUser uiIconLightGray']");
	public final By ELEMENT_TOPIC_PERMISSION_INPUT_SEARCH_USER_NAME = By.xpath(".//*[@id='QuickSearch']");
	public final String ELEMENT_TOPIC_PERMISSION_SELECT_SEARCH_OPTION = "//*[contains(@name,'filter')]";
	public final String ELEMENT_TOPIC_PERMISSION_SEARCH_ICON = ".//*[@id='UIUserSelector']//*[contains(@class,'uiIconSearch uiIconLightGray')]";
	public final By ELEMENT_TOPIC_CANCEL = By.xpath(".//*[@id='UITopicForm']//*[contains(text(),'Cancel')]");
	public final By ELEMENT_TOPIC_PERMISSION_CLOSE_USER_SELETOR = By.xpath(".//*[@id='UIUserSelector']//*[contains(text(),'Close')]");
	
	// reply post form 
    public final By ELEMENT_TITLE_POST = By.id("PostTitle");
    public final By ELEMENT_POST_CONTENT = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
    public final By ELEMENT_POST_FORM_SUBMIT = By.xpath("//*[@id='UIPostForm']//*[contains(text(),'Submit')]");
    public final String ELEMENT_POST_IN_TOPIC = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@class='postContent']//*[contains(text(),'{$content}')]";
    public final String ELEMENT_POST_IN_TOPIC_QUOTE = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@class='contentQuote']//*[contains(text(),'{$content}')]";
    public final String ELEMENT_POST_IN_TOPIC_PRIVATE = "//*[@class='postViewTitle' and contains(text(),'{$title}')]//*[contains(text(),'Post Private')]/../../..//*[contains(text(),'{$content}')]";
    public final By ELEMENT_TOPIC_POST_REPLY_BOTTOM= By.xpath(".//*[@id='UITopicDetail']/div[5]//a[text()='Post Reply']");
    public final By ELEMENT_TOPIC_POST_REPLY_BUTTON_DISABLE=By.xpath(".//*[@id='UITopicDetail']//*[@data-original-title='You cannot reply to this topic.']");
    //New post popup
    public final By ELEMENT_TOPIC_NEW_POST_TITLE= By.xpath(".//*[@id='UIForumPopupWindow']//span[text()='New Post']");
    public final By ELEMENT_TOPIC_NEW_POST_TITLE_FIELD=By.id("PostTitle");
    public final By ELEMENT_START_TOPIC_MESSAGE_FRAME_CKEDITOR = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
    public final By ELEMENT_START_TOPIC_ATTACH_FILE =By.xpath("//*[@id='ThreadContent']//*[@class='uiIconAttach uiIconLightGray']");
    public final By ELEMENT_UPLOAD_POPUP_FILE = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Attach File']");
    public final String ELEMENT_TOPIC_REPPLY_CONTENT=".//*[contains(text(),'${content}')]";
    
    // foot page of post
    public final String ELEMENT_EDIT_POST = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Edit This Post']";
	public final String ELEMENT_QUOTE_POST = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Reply with Quote']";
    public final String ELEMENT_DELETE_POST = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Delete This Post']";
	public final String ELEMENT_PRIVATE_POST = "//*[@class='postViewTitle' and contains(text(),'{$title}')]/../..//*[@data-original-title='Private Reply']";

    public final By ELEMENT_DELETE_BOX_CONFIRMATION = By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(text(),'OK')]");
    
	//  Poll
    public final By ELEMENT_POLL_QUESTION = By.id("Question");
    public final By ELEMENT_POLL_OPTIONS0 = By.id("Option0");
    public final By ELEMENT_POLL_OPTIONS1 = By.id("Option1");
    public final By ELEMENT_POLL_SUBMIT = By.xpath("//*[@id='UIPollForm']//*[contains(text(),'Submit Poll')]");
    public final By ELEMENT_MORE_ACTIONS_POLL = By.xpath("//*[@class='uiIconPoll uiIconLightGray']/../..//*[@class='uiIconSettings uiIconLightGray']");
    public final By ELEMENT_EDIT_POLL = By.xpath("//*[@id='UITopicPoll']//*[contains(text(),'Edit')]");
    public final By ELEMENT_REMOVE_POLL = By.xpath("//*[@id='UITopicPoll']//*[contains(text(),'Remove')]");
    public final By ELEMENT_CLOSE_POLL = By.xpath(".//*[@id='UITopicPoll']//a[contains(text(),'Close')]");
    public final By ELEMENT_OPEN_POLL = By.xpath(".//*[@id='UITopicPoll']//a[contains(text(),'Reopen')]");
    public final By ELEMENT_POLL_POPUP_TITLE= By.xpath(".//*[@id='UIForumPopupWindow']//span[text()='Poll']");
    public final String ELEMENT_FORUM_POLL_DISPLAYOPT = "//*[contains(text(),'${opt}')]";
	public final By ELEMENT_FORUM_POLL_GRID = By.xpath("//*[@class='uiGrid table no-border-cell rounded-corners table-striped tableVoting']");
	public final By ELEMENT_FORUM_POLL_GRIDCLOSE = By.xpath("//*[@class='uiGrid table rounded-corners table-striped tableVoted']");
	
	//move a topic
	public String ELEMENT_UI_POPUP_MOVE_TOPIC ="//*[@id='MoveTopicForm']//*[@class='node']//*[contains(text(),'{$forum}')]";
	public final String ELEMENT_MOVE_POPUP_COLLASPE_NODE =".//*[@class='uiIconNode collapseIcon'][contains(.,'${category}')]";
	
	/***************************************************PRIVATE MESSAGE MANAGEMENT************************************************************************/
	// tab elements
	public By ELEMENT_TABS_SENT_MESSAGES = By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='nav nav-tabs']//*[contains(text(),'Sent Messages')]");
	public By ELEMENT_TABS_INBOX = By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='nav nav-tabs']//*[contains(text(),'Inbox')]");
	public By ELEMENT_TABS_COMPOSE_MESSAGE = By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='nav nav-tabs']//*[contains(text(),'Compose New Message')]");
	public By ELEMENT_PRIVATE_MESSAGE_CANCEL_BUTTON = By.xpath("//*[@id='UIPrivateMessageForm']//*[contains(text(),'Cancel')]");
	
	//send messages
	public By ELEMENT_SEND_TO_MESSAGE = By.id("SendTo");
	public By ELEMENT_TITLE_MESSAGE = By.id("MailTitle");
	public By ELEMENT_MESSAGE_CONTENT = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
	public By ELEMENT_SEND_BUTTON = By.xpath("//*[@id='UIPrivateMessageForm']//*[@class='uiAction']//*[contains(text(),'Send')]");
	public final By ELEMENT_COMPOSE_NEW_MESSAGE_GROUP_SELECTOR = By.xpath(".//*[@id='MessageTab']//*[contains(@class,'uiIconGroup')]");
	public final String ELEMENT_PRIVATE_MESSAGE_SELECT_GROUP=".//*[contains(@title,'${name}')]";
	public final String ELEMENT_PRIVATE_MESSAGE_SELECT_A_GROUP=".//*[@id='UIGroupSelector']//*[contains(text(),'Select this Group')]";
	public final String ELEMENT_PRIVATE_MESSAGE_SEND_SUCCESSFULLY = "Your message was sent successfully.";
	public final By ELEMENT_COMPOSE_NEW_MESSAGE_MEMBERSHIP_SELECTOR = By.xpath(".//*[@id='MessageTab']//*[contains(@class,'uiIconMembership uiIconLightGray')]");
	public final String ELEMENT_PRIVATE_MESSAGE_SELECT_A_MEMBERSHIP=".//*[@id='UIMemberShipSelector']//*[contains(text(),'${membership}')]";
	
	//inbox
	public String ELEMENT_TITLE_AUTHORS_INBOX = "//*[@id='UIListInBoxPrivateMessage']//*[contains(text(),'{$author}')]/../..//*[contains(text(),'{$title}')]";
	public String ELEMENT_CONTACT_INBOX = "//*[@id='PermissionInfo']//*[contains(text(),'{$contact}')]";
	public String ELEMENT_CONTENT_INBOX = "//*[@id='uiViewPrivateMessage']//*[contains(text(),'{$content}')]";
	public By ELEMENT_REPLY = By.xpath("//*[@id='uiViewPrivateMessage']//*[@class='uiIconReply uiIconLightGray']");
	public String ELEMENT_DELETE_MESSAGE = "//*[@id='UIListInBoxPrivateMessage']//*[contains(text(),'{$title}')]/../../..//*[contains(text(),'{$contact}')]/../..//*[@class='uiIconDelete uiIconLightGray']";
	
	// outbox
	public String ELEMENT_FORWARD_MESSAGE = "//*[@id='UIListSentPrivateMessage']//*[contains(text(),'{$title}')]/../../..//*[contains(text(),'{$contact}')]/../..//*[@class='uiIconForumForward uiIconForumLightGray']";
	
	public By ELEMENT_CONFIRM = By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(text(),'OK')]");
	
}
