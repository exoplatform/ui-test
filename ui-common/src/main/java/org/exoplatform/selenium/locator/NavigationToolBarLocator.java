package org.exoplatform.selenium.locator;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class NavigationToolBarLocator extends PlatformBase {


	//Tool bar
	public final By ELEMENT_TOOLBAR_ADMINISTRATION = By.xpath(".//*[@id='UISetupPlatformToolBarPortlet']//i");
	public final By ELEMENT_TOOLBAR_THEMELIGHT = By.xpath("//*[@class='UIContainer UIToolbarContainer UIToolbarContainerLight']");
	public final By ELEMENT_UPLOAD_FILE_FRAME_XPATH = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public final By ELEMENT_HELP_TOOLBAR = By.className("uiIconPLF24x24Help");
	public final By ELEMENT_TOOLBAR_NOTIFICATION_LIST = By.xpath(".//*[@id='UINotificationPopoverToolbarPortlet']//*[contains(@class,'uiIconPLF24x24Bell')]");
	public final String ELEMENT_TOOLBAR_NOTIFICATION_NUMEBER=".//*[contains(@class,'badgeNotification')][contains(text(),'${num}')]";

	//Notificaiton list
	public final By ELEMENT_NOTIFICATION_DROPDOWN=By.cssSelector("#NotificationPopup");
	public final String ELEMENT_NOTIFICATION_LIST_COMMENT_ACTIVITY = ".//*[@id='NotificationPopup']//*[contains(@class,'user-name')][contains(text(),'${user}')]/../..//*[contains(.,'${des}')]/..//*[contains(.,'${act}')]";
	public final String ELEMENT_NOTIFICATION_LIST_CONNECT_USER=".//*[@id='NotificationPopup']//*[contains(@class,'user-name')][contains(text(),'${user}')]/../..//*[contains(.,' ${des}')]";
	public final By ELEMENT_NOTIFICATION_LIST_CONNECT_USER_STATUS= By.xpath(".//*[@id='NotificationPopup']//*[contains(text(),'Accept')]/../../..//*[contains(@class,'status')]");
	public final String ELEMENT_NOTIFICATION_LIST_INVITATION_SPACE_STATUS=".//*[@id='NotificationPopup']//*[contains(@class,'text-bold')][contains(text(),'${space}')]/..";
	public final String ELEMENT_NOTIFICATION_LIST_USER = "//*[@id='NotificationPopup']/../..//*[contains(@class,'user-name text-bold')][contains(text(),'${user}')]/..";
	public final By ELEMENT_NOTIFICATION_REMOVE_ICON = By.xpath(".//*[@id='NotificationPopup']//i[contains(@class,'uiIconClose uiIconLightGray')]");
	public final By ELEMENT_INTRANET_NOTIFICATION_BELL = By.xpath("//*[@class='uiIconPLF24x24Bell']");
	public final By ELEMENT_INTRANET_NOTIFICATION_NEAR_USER_AVATAR=By.xpath(".//*[contains(@class,'NotificationPopoverPortletTDContainer')]/..//following-sibling::*//img[@alt='avatar']");

	public final By ELEMENT_POSITION_OF_INTRANET_NOTIFICATION = By.xpath("//*[@class='UITableColumnContainer']//*[@class='UserInfoPortletTDContainer pull-left']/../*[@class='NotificationPopoverPortletTDContainer pull-left']");
	public final By ELEMENT_DOC_EXO_OF_HOME_GETTING_STARTED = By.xpath(".//*[@id='newBreadcrumbs']//*[contains(text(),'Getting Started')]");

	// Intranet notification 
	public final String ELEMENT_BADGE_NUMBER_DISPLAY = "//*[contains(@class,'badgeDefault') and @style='display: inline;' and text()='${number}']";
	public final By ELEMENT_BADGE_NUMBER_NOT_DISPLAY = By.xpath("//*[contains(@class,'badgeDefault') and text()='0']");
	public final By ELEMENT_BADGE_NUMBER=By.xpath("//*[@class='badgeDefault badgePrimary mini badgeNotification']");
	public final By ELEMENT_NOTIFICATION_MARK_ALL_AS_READ_WITH_POSITION = By.xpath(".//*[@id='NotificationPopup']//*[contains(text(),'Mark all as read')]");
	public final By ELEMENT_VIEW_ALL_BUTTON = By.xpath(".//*[@id='NotificationPopup']//a[text()='View All']");
	public final By ELEMENT_NO_NOTIFICATIONS= By.xpath(".//*[@id='NotificationPopup']//*[@class='no-items' and text()='No notifications']");
	public final String ELEMENT_CONNECT_NOTIFICATION_POSITION = "//li[${position}]//*[contains(@alt,'${fullName}')]/../..//*[contains(text(),'${fullName}')]/../..//*[contains(.,'wants to connect with you')]";
	public final String ELEMENT_COMMENT_JUST_NOW_READ = "//*[@class='read clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]";
	public final String ELEMENT_COMMENT_POSITION_ONE_MINUTE_READ = "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]";
	public final String ELEMENT_COMMENT_MARK_ALL_AS_READ = "//*[@class='clearfix']//*[contains(@alt,'${userName}')]/../..//*[@class='status' and contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]";
	public final String ELEMENT_COMMENT_JUST_NOW_UNREAD = "//*[@class='unread clearfix']//[contains(@alt,'${userName}')]/../..//*[@class='status' and contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]";
	public final String ELEMENT_COMMENT_POSITION_ONE_MINUTE_UNREAD = "//*[@class='unread clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]";
	public final String ELEMENT_LIKE_NOTIFICATION_JUST_NOW_READ = "//*[@class='read clearfix']//*[contains(@alt,'${userName}')]/../..//*[.contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]";
	public final String ELEMENT_LIKE_NOTIFICATION_ONE_MINUTE_READ = "//*[@class='read clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]";
	public final String ELEMENT_LIKE_NOTIFICATION_MARK_ALL_AS_READ = "//*[@class='clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]";
	public final String ELEMENT_LIKE_NOTIFICATION_JUST_NOW_UNREAD = "//*[@class='unread clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]";
	public final String ELEMENT_LIKE_NOTIFICATION_ONE_MINUTE_UNREAD = "//*[@class='unread clearfix']//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]";
	public final String ELEMENT_COMMENT_ONE_MINUTE_DELETE = "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]/../../../..//*[@class='uiIconClose uiIconLightGray']";
	public final String ELEMENT_COMMENT_JUST_NOW_DELETE = "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'has commented on your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]/../../../..//*[@class='uiIconClose uiIconLightGray']";
	public final String ELEMENT_LIKE_NOTIFICATION_ONE_MINUTE_DELETE = "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'${time} minute ago')]/../../../..//*[@class='uiIconClose uiIconLightGray']";
	public final String ELEMENT_LIKE_NOTIFICATION_JUST_NOW_DELETE = "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime' and contains(text(),'Just Now')]/../../../..//*[@class='uiIconClose uiIconLightGray']";
	public final String ELEMENT_LIKE_NOTIFICATION_DELETE = "//*[contains(@alt,'${userName}')]/../..//*[contains(.,'likes your activity.')]//*[contains(text(), '${userName}')]/../..//*[contains(text(),'${activity}')]/..//*[@class='lastUpdatedTime']/../../../..//*[@class='uiIconClose uiIconLightGray']";
	public final String ELEMENT_CONNECT_NOTIFICATION_DELETE = "//*[contains(@alt,'${fullName}')]/../..//*[ contains(text(),'${fullName}')]/../..//*[contains(.,'wants to connect with you')]/../../../..//*[@class='uiIconClose uiIconLightGray']";
	public final String ELEMENT_NEW_USER_NOTIFICATION_DELETE = "//*[contains(@alt,'${userName}')]/../..//*[contains(text(),'${userName}')]/../..//*[contains(.,'has joined eXo')]/../../../..//*[@class='uiIconClose uiIconLightGray']";
	
	// toolbar--> upload file
	public By ELEMENT_UPLOAD_FILE_TOOLBAR_PERSONNAL_DOCUMENTS = By.xpath("//*[@id='ListRecords']//*[contains(text(),'Personal Documents')]");
	public By ELEMENT_UPLOAD_FILE_GO_TO_UPLOAD = By.xpath("//*[@id='UIDocumentSelector']//*[@class='UIDSUploadInput']");
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_UPLOAD_BUTTON = By.xpath(".//input[@type='file']");

	//Administration Menu
	// users 
	public final By ELEMENT_ADMINISTRATION_USERS =By.xpath("//*[contains(@href,'g/:platform:administrators/administration/newStaff') and text()='Community']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_ADD_USERS = By.xpath("//*[contains(@href,'g/:platform:administrators/administration/newStaff') and text()='Add Users']");
	public final By ELEMENT_GROUP_AND_ROLE_LINK = By.xpath(".//*[@id='UISetupPlatformToolBarPortlet']//a[contains(@href,'management')]");

	//Administration-->Portal
	public final By ELEMENT_ADMINISTRATION_PORTAL = By.xpath("//*[text()='Portal']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_SITES=By.xpath("//*[text()='Sites']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_PAGES=By.xpath("//*[text()='Pages']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_EMAIL_NOTIFICATIONS= By.xpath(".//*[contains(@id,'UISetupPlatformToolBarPortlet')]//*[contains(@href,'notification')]");
	public final By ELEMENT_ADMINISTRATION_PORTAL_BRANDING=By.xpath("//*[text()='Branding']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_IDE=By.xpath("//*[text()='IDE']");
	public final By ELEMENT_ADMINISTRATION_PORTAL_GROUP_SITES = By.xpath("//*[text()='Group Sites']");


	//Administation-->Content
	public final By ELEMENT_LINK_CONTENT_ADMIN = By.xpath("//*[text()='Content Administration']");
	public final By ELEMENT_MENU_CONTENT_LINK = By.xpath("//li[@class='dropdown-submenu']/a[text()='Content']");
	public final By ELEMENT_MENU_SITE_EXPLORER = By.linkText("Sites Explorer");
	public final By ELEMENT_SITE_EXPLORER_HOME = By.className("uiIconEcmsHome");
	public final By ELEMENT_NEW_FOLDER_LINK = By.xpath("//*[@class='actionIcon']//*[contains(@class, 'uiIconEcmsAddFolder')]");
	public final By ELEMENT_SEARCH_LINK = By.xpath("//*[@class='dropdown-menu']//*[text()='Search']");


	// administration panel
	public final By ELEMENT_TOPBAR_ADMINISTRATION_BUTTON =By.xpath("//*[@class='uiIconPLF24x24Setup']");
	public final By ELEMENT_TOPBAR_CONTENT = By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Content')]");
	public final By ELEMENT_CONTENT_TOPBAR_ADMINISTRATION = By.xpath("//*[@id='UISetupPlatformToolBarPortlet']//a[contains(text(),'Content Administration')]");

	//Setup icon
	public final By ELEMENT_LINK_SETUP=By.xpath(".//*[@id='UISetupPlatformToolBarPortlet']//*[@class='uiIconPLF24x24Setup']");
	public final By ELEENT_LINK_APPLICATION=By.xpath("//*[contains(@href,'/portal/g/:platform:administrators/administration/registry')]");

	//Edit menu
	public final By ELEMENT_LINK_EDIT=By.xpath("//*[@class='uiIconPLF24x24Edit']");
	public final By ELEMENT_MENU_PAGE_LINK = By.xpath("//*[@tabindex='-1' and contains(text(),'Page')]");
	public final By ELEMENT_MENU_SITE_LINK = By.xpath("//*[@tabindex='-1' and contains(text(),'Site')]");
	public final By ELEMENT_MENU_EDIT_LAYOUT = By.xpath("//*[contains(text(),'Edit Layout')]");
	public final By ELEMENT_MENU_LAYOUT = By.xpath("//*[@tabindex='-1' and contains(text(),'Site')]/..//*[contains(text(),'Layout')]");
	public final By ELEMENT_MENU_SEO_LINK = By.xpath("//span[contains(text(), 'SEO')]");
	public final By ELEMENT_MENU_ADD_PAGE_LINK = By.xpath("//*[contains(text(), 'Add Page')]");
	public final By ELEMENT_EDIT_PAGE = By.xpath("//*[@id='UIAdminToolbarContainer']//*[@class='dropdown-submenu']//*[@href='#' and contains(text(), 'Page')]");
	public final By ELEMENT_EDIT_PAGE_SEO = By.xpath("//*[@data-original-title = 'SEO Management']");
	public final By ELEMENT_EDIT_CONTENT = By.xpath("//*[@class='quickEditUnchecked']");
	public final By ELEMENT_EDIT_CONTENT_CHECK = By.xpath("//*[@class='quickEditChecked']");

	//Edit->site
	public final By ELEMENT_MENU_EDIT_SITES = By.xpath("//*[contains(@href,'#')][contains(text(),'Site')]");
	public final By ELEMENT_MENU_EDIT_SITES_NAV = By.xpath("//*[@id='UIAdminToolbarContainer']//a[contains(text(),'Navigation')]");
	public final By ELEMENT_MENU_EDIT_SITE_LAYOUT = By.xpath(".//*[contains(@href,'#')][contains(text(),'Site')]/..//*[contains(text(),'Layout')]");
	public final By ELEMENT_MENU_EDIT_ADDSITE = By.linkText("Add Site");
	public final By ELEMENT_MENU_EDIT_CONTENT_TEXT = By.linkText("Content");
	public final By ELEMENT_NAVIGATION_MANAGE_POPUP=By.xpath(".//*[@id='UINavigationManagement']");
	public final By ELEMENT_ADDSITE_MANAGE_POPUP=By.xpath(".//*[@id='UIMaskWorkspace']//*[contains(@class,'MaskContainer')]");
	//User Menu
	public final By ELEMENT_MY_PROFILE_LINK = By.xpath("//i[@class='uiIconPLFProfile']/..");
	public final By ELEMENT_MY_DASHBOARD_LINK = By.xpath("//i[@class='uiIconPLFDashboard']/..");
	public final By ELEMENT_MY_SETTINGS_LINK = By.className("uiIconSetting");
	public final By ELEMENT_MY_CONNECTION_LINK = By.className("uiIconPLFMyConnection");
	public final By ELEMENT_ACTIVITIES_LINK = By.className("uiIconPLFActivityStream");
	public final By ELEMENT_TOPBAR_AVATAR = By.xpath("//*[@alt='avatar']");
	public final By ELEMENT_AVATAR_CHANGELANGUAGE = By.xpath("//*[@class='uiIconFlags']");
	public final By ELEMENT_MY_WIKI_LINK = By.xpath("//i[@class='uiIconWikiMyWiki']/..");
	public final By ELEMENT_MY_NOTIFICATIONS_LINK = By.className("uiIconPLFNotifications");
	public final By ELEMENT_ACTIVITIES_PORTLET = By.id("UIUserActivityStreamPortlet");

	//Administration-->Application
	public final By ELEMENT_ADMINISTRATION_APPLICATION = By.xpath(".//*[text()='Applications']");
	public final By ELEMENT_ADD_TOOTLBAR = By.xpath("//*[@id='UICreatePlatformToolBarPortlet']//*[@class='uiIconPLF24x24Add']");

	public final By ELEMENT_ADD_WIKI_TOOLBAR = By.xpath("//*[@id='UICreateList']//*[@class='uiIconWikiWiki']");
	public final By ELEMENT_ADD_POOL_TOOLBAR = By.xpath("//*[@id='UICreateList']//*[@class='uiIconPoll']");
	public final By ELEMENT_ADD_TOPIC_TOOLBAR = By.xpath("//*[@id='UICreateList']//*[@class='uiIconUIForms']");
	public final By ELEMENT_ADD_EVENT_CLASS_TOOLBAR = By.xpath("//*[@id='UICreateList']//*[@class='uiIconPLFEventTask']");
	public final By ELEMENT_UPLOAD_FILE_TOOLBAR = By.xpath("//*[@id='UICreateList']//*[@class='uiIconUpload']");

	public final By ELEMENT_NEXT_BUTTON = By.xpath("//*[@id='UICreateList']//*[contains(text(),'Next')]");
	public final By ELEMENT_SAVE_BUTTON = By.xpath("//*[@id='UICreateList']//*[contains(text(),'Save')]");

	// add wiki from toolbar
	public final By ELEMENT_ADD_WIKI_SET_LOCATION = By.xpath("//*[@id='uiWikiSpaceSwitcher_CreateWiki']//*[@id='DisplayModesDropDown']/div");
	public final String ELEMENT_ADD_WIKI_CHOOSE_LOCATION = "//*[@class='spaceChooserPopup']//*[contains(text(),'{$location}')]";
	//add poll/topic 
	public final By ELEMENT_ADD_POLL_SET_LOCATION = By.xpath("//*[@id='ScrollSelectlocation']//*[@class='btn dropdown-toggle']");
	public final By ELEMENT_SELECT_FORUM_COMBOBOX = By.xpath(".//*[@id='uiForumFilterforumId']//div[@class='btn dropdown-toggle']");
	public final String ELEMENT_SELECT_FORUM_NAME = ".//*[@id='uiForumFilterforumId']//*[contains(text(),'${forum}')]";

	// event or task
	public final By ELEMENT_ADD_EVENT_RADIO_BUTTON = By.xpath("//*[@id='QuickAddEventContainer']//*[@class='radio' and @value='Event']");
	public final By ELEMENT_ADD_TASK_RADIO_BUTTON = By.xpath("//*[@id='QuickAddEventContainer']//*[@class='radio' and @value='Task']");
	public final By ELEMENT_ADD_TITLE = By.id("Title");

	public final  String ELEMENT_CHECK_NAME_UPLOADED_FILE= "//*[@id='ListRecords']//*[contains(text(),'{$name}')]";
	//Quick search
	public final By ELEMENT_TOOLBAR_QUICKSEARCH = By.xpath("//*[@class='uiIconPLF24x24Search']");
	
	
	//Notifications
	//Get the content of the first notification
	public final String ELEMENT_SPACE_DOCUMENTS_SHARED_NOTIFICATION = "//*[@id='NotificationPopup']/li[4]/ul//*[contains(text(),'${author}')]"+
			"/../..//*[contains(text(),'has posted an activity in the')]/../..//*[contains(text(),'${spaceName}')]";

}
