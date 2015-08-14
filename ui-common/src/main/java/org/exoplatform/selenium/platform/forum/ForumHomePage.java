package org.exoplatform.selenium.platform.forum;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForumHomePage extends PlatformBase {

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

	ManageAlert alert;
	Button button;

	/**
	 * constructor
	 * @param dr
	 */
	public ForumHomePage(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(driver);
		button = new Button(driver);
	}
	/**
	 * Go to home category
	 */
	public void goToHomeCategory(){
		if(waitForAndGetElement(ELEMENT_CATEGORY_BREADCUMB_HOME,5000,0)!=null){
			info("Go to home category");
			click(ELEMENT_CATEGORY_BREADCUMB_HOME);
		}
		waitForElementNotPresent(ELEMENT_CATEGORY_BREADCUMB_HOME);
	}

	/**
	 * list sublinks in Administration menu
	 * @author quynhpt
	 *
	 */
	public enum specifAdministrationMenu{
		SORT_SETTING,CENSOR_KEYWORDS,NOTIFICATIONS,BBCODE,PRUNNING,BANNED_IPS,EXPORT,IMPORT;
	}
	/**
	 * select a item in Manage Category Menu
	 * By QuynhPT
	 * @param item
	 */
	public void selectItemAdministrationMenu(specifAdministrationMenu item) {
		info("Waiting administration menu is shown");
		waitForAndGetElement(ELEMENT_ACTIONBAR_ADMINISTRATION);
		info("Click on Manage menu");
		click(ELEMENT_ACTIONBAR_ADMINISTRATION);
		Utils.pause(1000);
		switch (item) {
		case SORT_SETTING:
			break;
		case CENSOR_KEYWORDS:
			break;
		case BBCODE:
			info("Click on BBCode link");
			click(ELEMENT_ACTIONBAR_ADMIN_BBCODE);
			break;
		case NOTIFICATIONS:
			break;
		case BANNED_IPS:
			break;
		case PRUNNING:
			break;
		case EXPORT:
			info("Export a category");
			click(ELEMENT_ACTIONBAR_ADMIN_EXPORT);
			break;
		case IMPORT:
			info("Import a category");
			click(ELEMENT_ACTIONBAR_ADMIN_IMPORT);
			break;
		default:
			break;
		}
	}

	/**
	 * Import a category from Administration menu
	 * @param folderDowloadFile
	 * @param nameFile
	 */
	public void importCategory(String folderDowloadFile,String nameFile){
		selectItemAdministrationMenu(specifAdministrationMenu.IMPORT);
		importFile(folderDowloadFile,nameFile);
		button.ok();


	}
	/**
	 * Go to a detail category in list
	 * By QuynhPT
	 * @param name
	 */
	public void goToCategory(String name){
		goToHomeCategory();
		click(ELEMENT_FORUM_DETAIL_FORUM_NAME_LINK.replace("${name}", name));
	}

	/**
	 * Watch or UnWatch 
	 * true is for watching
	 * false is for un-watching
	 * Update QuynhPT
	 * @param watch
	 */
	public void watchItem(boolean watch){	
		if (watch){
			if (waitForAndGetElement(ELEMENT_WATCH, DEFAULT_TIMEOUT, 0) != null){
				info("Watch item");
				click(ELEMENT_WATCH);
				waitForMessage(MESSAGE_WATCH);
				click(ELEMENT_OK_INFOR_POPUP);
				waitForAndGetElement(ELEMENT_UNWATCH);
				info("Watch item successfully");
			}else {
				info("Not found watch link");
			}	
		} else {
			if (waitForAndGetElement(ELEMENT_UNWATCH, DEFAULT_TIMEOUT, 0) != null) {
				info("Unwatch item");
				click(ELEMENT_UNWATCH);
				waitForMessage(MESSAGE_UNWATCH);
				click(ELEMENT_OK_INFOR_POPUP);
				waitForAndGetElement(ELEMENT_WATCH);
				info("Unwatch item successfully");
			} else {
				info("Not found unwatch link");
			}
		}
	}

	/**
	 * Open a forum
	 * @param name
	 */
	public void goToForum(String name){
		info("Click on the forum with the name:"+name);
		click(ELEMENT_SELECT_FORUM_TOPIC.replace("${link}",name));
		Utils.pause(2000);
	}

	/**
	 * Attach file in attach popup
	 * @param pathFile
	 * @param fileName
	 */
	public void attachFile(String pathFile, String fileName) {
		info("Attach a file");
		WebElement element = waitForAndGetElement(ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_INPUT, DEFAULT_TIMEOUT, 1,2);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", element);
		info("Get the file to attach");
		element.sendKeys(getAbsoluteFilePath(pathFile+fileName));
		info("Verify that the file is attached");
		waitForAndGetElement(ELEMENT_UPLOAD_POPUP_NAMEFILE.replace("${fileName}", fileName));
		info("The file is attached successfully");
		info("Click on Save button");
		click(ELEMENT_UPLOAD_POPUP_ATTACHMENT_FILE_SAVE_BUTTON);
		Utils.pause(2000);
	}

	/**
	 * Add a BBcode
	 * Update QuynhPT
	 * @param tag
	 * @param replacement
	 * @param description
	 * @param example
	 * @param use
	 *<li> add a java doc </li>
	 */
	public void addBBCode(String tag, String replacement, String description, String example, boolean use ) {
		selectItemAdministrationMenu(specifAdministrationMenu.BBCODE);
		info("Click on Add button of Add BBCode popup");
		click(ELEMENT_ADMIN_BBCODE_ADDBBCODE);
		info("Input new tag");
		type(ELEMENT_BBCODE_ADDBBCODEFORM_TAG , tag, true);
		info("Input new replacement");
		type(ELEMENT_BBCODE_ADDBBCODEFORM_REPLACEMENT , replacement, true);
		info("Input new description");
		type(ELEMENT_BBCODE_ADDBBCODEFORM_DESCRIPTION , description, true);
		info("Input new example");
		type(ELEMENT_BBCODE_ADDBBCODEFORM_EXAMPLE , example, true);
		if(use==true)
			check(ELEMENT_BBCODE_USE_OPTION);
		info("Click on Save button and save all changes");
		click(ELEMENT_EDITSITE_SAVEBTN);
		info("Verify that BBcode is created");
		waitForAndGetElement(ELEMENT_BBCODE_TAG_VERIFY.replace("${tag}", tag.toUpperCase()));
		info("Close the popup");
		click(ELEMENT_BBCODE_POPUP_CLOSEBTN);
	}
	/**
	 * Edit BBcode
	 * @param newTag
	 * @param newReplacement
	 * @param newDescription
	 * @param newExample
	 * @param use
	 */
	public void editBBCode(String newTag,String newReplacement, String newDescription, String newExample, boolean use){
		selectItemAdministrationMenu(specifAdministrationMenu.BBCODE);
		info("Click on Edit BBcode");
		click(ELEMENT_BBCODE_EDITBBCODE.replace("${tag}", newTag.toUpperCase()));
		info("Input new tag");
		type(ELEMENT_BBCODE_ADDBBCODEFORM_TAG , newTag, true);
		info("Input new replacement");
		type(ELEMENT_BBCODE_ADDBBCODEFORM_REPLACEMENT , newReplacement, true);
		info("Input new description");
		type(ELEMENT_BBCODE_ADDBBCODEFORM_DESCRIPTION , newDescription, true);
		info("Input new example");
		type(ELEMENT_BBCODE_ADDBBCODEFORM_EXAMPLE , newExample, true);
		if(use==true)
			check(ELEMENT_BBCODE_USE_OPTION);
		info("Click on Save button and save all changes");
		click(ELEMENT_EDITSITE_SAVEBTN);
		info("Verify that BBcode is edited with changes");
		waitForAndGetElement(ELEMENT_BBCODE_TAG_VERIFY.replace("${tag}", newTag.toUpperCase()));
		info("Close the popup");
		click(ELEMENT_BBCODE_POPUP_CLOSEBTN);
	}
	/**
	 * Delete a BBcode
	 * @param tag
	 */
	public void deleteBBcode(String tag){
		selectItemAdministrationMenu(specifAdministrationMenu.BBCODE);
		info("Click on Delete of the tag");
		click(ELEMENT_BBCODE_DELETEBBCODE.replace("${tag}", tag.toUpperCase()));
		info("Click on OK buton of Confirm popup");
		click(ELEMENT_BBCODE_CONFIRM_DELETETAG);
		info("Verify that BBcode is closed");
		waitForElementNotPresent(ELEMENT_BBCODE_TAG_VERIFY.replace("${tag}", tag.toUpperCase()));
		info("Close the popup");
		click(ELEMENT_BBCODE_POPUP_CLOSEBTN);
	}

	/**
	 * Bookmark a topic, a category and a forum
	 * @param name
	 */
	public void bookmark(String name){
		info("Click on Bookmark link on Action bar");
		click(ELEMENT_ACTIONBAR_BOOKMARK_ICON);
		Utils.pause(3000);
		info("Click on Bookmark link on Action bar to open Bookmark popup");
		click(ELEMENT_ACTIONBAR_BOOKMARK_MANAGER);
		info("Verify that the topic is bookmarked");
		waitForAndGetElement(ELEMENT_FORUM_BOOKMARK_NAME.replace("${name}", name));
		info("Delete the bookmark of the topic");
		click(ELEMENT_FORUM_BOOKMARK_DELETE.replace("${name}",name));
		info("Verify that the bookmark is deleted");
		waitForElementNotPresent(ELEMENT_FORUM_BOOKMARK_NAME.replace("${name}", name));
		info("Close the popup");
		click(ELEMENT_FORUM_BOOKMARK_CLOSE_ICON);
	}

	/**
	 * Export a category from Action bar
	 * @param catName
	 * @param fileName
	 */
	public void exportCategory(String catName, String fileName) {
		selectItemAdministrationMenu(specifAdministrationMenu.EXPORT);
		info("Uncheck all category");
		uncheck(ELEMENT_EXPORTCAT_EXPORTALL, 2);
		info("Select the category:"+catName);
		check(ELEMENT_EXPORTCAT_EXPORT.replace("${title}", catName), 2);
		info("input name");
		type(ELEMENT_FILENAME_INPUT,fileName,true);
		info("Save all changes");
		click(ELEMENT_SAVE_BTN);
	}

	/**
	 * Open a topic
	 * @param name
	 */
	public void goToTopic(String name){
		info("Click on the topic with the name:"+name);
		click(ELEMENT_SELECT_FORUM_TOPIC.replace("${link}", name),0,true);
		Utils.pause(2000);
	}
	/**
	 * Go to Private Message
	 */
	public void goToPrivateMessage() {
		// TODO Auto-generated method stub
		info("Click on Private Message button");
		click(ELEMENT_ACTIONBAR_PRIVATE_MESSAGE);
		Utils.pause(2000);
	}
	/**
	 * Go to Compose New Message tab in Private Message
	 */
	public void goToComposeNewMessageTab() {
		info("Click on Compose New Message tab");
		click(ELEMENT_PRIVATE_MESSAGE_COMPOSE_MESSAGE_TAB);
		Utils.pause(2000);
	}
	
	/**
	 * Select User in Compose New Message tab
	 * 
	 */
	public void gotoUserSelectorInComposeNewMessageTab(){
		info("-- Go to wiki home page --");
		click(ELEMENT_COMPOSE_NEW_MESSAGE_USER_SELECTOR);
	}
	
	/**
	 * function: Search user in User Selection Form when Compose New Private Message
	 * 
	 */
	
	public void searchUser(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_COMPOSE_NEW_MESSAGE_INPUT_SEARCH_USER_NAME, user, true);
		select(ELEMENT_COMPOSE_NEW_MESSAGE_SELECT_SEARCH_OPTION, searchOption);
		click(ELEMENT_COMPOSE_NEW_MESSAGE_SEARCH_ICON);
		waitForTextPresent(user);
	}
	
	public void searchUserNotFound(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_COMPOSE_NEW_MESSAGE_INPUT_SEARCH_USER_NAME, user, true);
		select(ELEMENT_COMPOSE_NEW_MESSAGE_SELECT_SEARCH_OPTION, searchOption);
		click(ELEMENT_COMPOSE_NEW_MESSAGE_SEARCH_ICON);
		waitForTextNotPresent(user);
	}
	
	/**
	 * Cancel send Private Message 
	 */
	public void cancelSendPrivateMessage(){
		info("Cancel Add or Edit Forum");
		click(ELEMENT_PRIVATE_MESSAGE_CANCEL);
	}
	
	/**
	 * Close User Selector page
	 */
	public void closeUserSelector(){
		info("-- Go to User Selector page --");
		click(ELEMENT_COMPOSE_NEW_MESSAGE_CLOSE_USER_SELETOR);
		Utils.pause(2000);
	}
	
	/**
	 * Go to Manage User in Forum
	 */
	public void goToManageUser() {
		// TODO Auto-generated method stub
		info("Click on Users button on Forum administration bar");
		click(ELEMENT_FORUM_USER_LIST);
		Utils.pause(2000);
	}
	
	/**
	 * function: Search user in User Manage Form
	 * 
	 */
	
	public void searchUserInUserList(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_MANAGE_USER_INPUT_SEARCH_USER_NAME, user, true);
		click(ELEMENT_MANAGE_USER_SEARCH_ICON);
		waitForTextPresent(user);
	}
	
	public void searchUserInUserListNotFound(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_MANAGE_USER_INPUT_SEARCH_USER_NAME, user, true);
		click(ELEMENT_MANAGE_USER_SEARCH_ICON);
		waitForTextNotPresent(user);
	}
	
	/**
	 * Close User form
	 */
	public void closeUserForm(){
		info("-- Go to User form --");
		click(ELEMENT_USER_FORM_CLOSE);
		Utils.pause(2000);
	}
	
	/**
	 * Go to My Subscription
	 */
	public void gotoMySubscriptions(){
		click(ELEMENT_FORUM_SETTINGS);
		click(ELEMENT_FORUM_SETTINGS_MYSUSCRIB);
	}
	
	/**
	 * Update email in Sub-scriptions
	 * 
	 */
	public void updateEmailInMySubscriptions(String email){
	    info("Update Email in My Subscriptions ");
	    type (ELEMENT_MY_SUBSCRIPTIONS_EMAIL_UPDATE,email,true);
	    click(ELEMENT_FORUM_SETTINGS_UPDATE);
	    waitForTextPresent(email);
	}
	
}
