package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActivityStream extends PlatformBase {

	//Author of activity
	public final String ELEMENT_ACTIVITY_AUTHOR_SPACE="//*[@class='author']//*[contains(@href,'$user')]/../..//*[@data-original-title='$space']";
	public final String ELEMENT_ACTIVITY_BOX="//*[@id='boxContainer']//*[contains(text(),\"${name}\")]/../../../..//*[@class='heading']";
	public final String ELEMENT_ACTIVITY_BOX_DELETE_BUTTON="//*[@id='boxContainer']//*[contains(text(),\"${name}\")]/../../../..//*[contains(@class,'uiIconClose')]";

	//Home page space menu
	public final By ELEMENT_SPACE_MENU_ACTIVITY_PORTLET=By.xpath(".//*[@class='uiIconAppSpaceActivityStreamPortlet uiIconDefaultApp']");
	public final By ELEMENT_SPACE_MENU_FORUM_PORTLET=By.xpath(".//*[@class='uiIconAppForumPortlet uiIconDefaultApp']");
	public final By ELEMENT_SPACE_MENU_WIKI_PORTLET=By.xpath(".//*[@class='uiIconAppWikiPortlet uiIconDefaultApp']");
	public final By ELEMENT_SPACE_MENU_DOCUMENT_PORTLET=By.xpath(".//*[@class='uiIconAppFileExplorerPortlet uiIconDefaultApp']");
	public final By ELEMENT_SPACE_MENU_AGENDA_PORTLET=By.xpath(".//*[@class='uiIconAppCalendarPortlet uiIconDefaultApp']");
	public final By ELEMENT_SPACE_MENU_SPACE_SETTING_PORTLET=By.xpath(".//*[@class='class='uiIconAppSpaceSettingPortlet uiIconDefaultApp']");
	public final By ELEMENT_SPACE_MENU_MEMBER_PORTLET=By.xpath(".//*[@class='class='uiIconAppMembersPortlet uiIconDefaultApp']");
	public final By ELEMENT_SPACE_MENU_MORE_BTN =By.xpath(".//*[@class='uiIconAppMoreButton']");
	public final String ELEMENT_SPACE_MENU_APPLICATION_PORTLET=".//*[@id='spaceMenuTab']//*[contains(text(),'${app}')]";

	// Composer
	public final By ELEMENT_COMPOSER_INPUT_FILED = By.xpath(".//*[@id='DisplaycomposerInput']");
	public final By ELEMENT_COMPOSER_FILE_BUTTON = By.cssSelector(".uiIconSocUIDocActivityComposer.uiIconSocLightGray");
	public final By ELEMENT_SELECT_BUTTON= By.cssSelector("#UIPopupComposer .uiAction .btn:first-child");
	public final By ELEMENT_COMPOSER_FILE_ATTACHMENT_ACTIVITY=By.cssSelector(".uiActivityFileAttachment");
	public final By ELEMENT_SELECT_FILE_POPUP = By.xpath("//span[text()='Select File']");

	public final By ELEMENT_COMPOSER_MENTION_BUTTON = By.xpath(".//i[@class='uiIconSocMention uiIconSocLightGray']");
	public final By ELEMENT_COMPOSER_LINK_BUTTON = By.xpath(".//i[@class='uiIconSocUILinkActivityComposer uiIconSocLightGray']");
	public final By ELEMENT_COMPOSER_INPUT_LINK_FIELD = By.xpath(".//*[@id='InputLink']");
	public final By ELEMENT_COMPOSER_ATTACH_LINK_BUTTON = By.xpath(".//*[@id='AttachButton']");
	public final By ELEMENT_COMPOSER_CLOSE_SHARE_LINK_BUTTON=By.xpath("//*[@id='UIActivityComposerContainer']//*[@class='uiIconClose uiIconLightGray']");
	public final By ELEMENT_COMPOSER_SHARE_BUTTON = By.xpath(".//*[@id='ShareButton']");
	public final By ELEMENT_ACTIVITY_WHAT_ARE_YOU_WORKING_LABEL = By.xpath("//div[@id='DisplaycomposerInput']/../div[@class='placeholder']");
	public final String ELEMENT_ACTIVITY_AUTHOR_ACTIVITY = "//*[contains(text(), '${activityText}')]/../../../../..//*[@class='author']";
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_UPLOAD_BUTTON = By.xpath(".//input[@type='file']");	
	public final String ELEMENT_PUBLICATION_SUGGEST_USER = ".//*[@id='UIComposer']//*[contains(@data-ref-id,'${name}')]";	
	public final String ELEMENT_PUBLICATION_USER_SHARED = ".//*[@class='description']//*[contains(text(),'${name}')]";
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_PROGRESS_UPLOAD = By.xpath(".//*[@id='UIDocumentSelector']//*[@class='pull-left percent']");
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_CLOSE_BTN =By.xpath(".//*[@id='UIPopupComposer']//*[contains(@title,'Close Window')]");
	public final By ELEMENT_UPLOAD_FILE_FRAME_XPATH = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public final By ELEMENT_UPLOAD_BUTTON=By.xpath("//*[@class='uiIconUpload uiIconLightGray']");

	//Upload popup
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP=By.xpath(".//*[@id='DriveTypeDropDown']/div[@class='btn dropdown-toggle']");
	public final String ELEMENT_ACTIVITY_UPLOAD_POPUP_NODE=".//*[@id='ListRecords']//a[@data-original-title='${nameNode}']";
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_CLOSE=By.xpath(".//*[@id='UIPopupComposer']//*[@class='uiIconClose pull-right']");
	public final String ELEMENT_DRIVER_OPTION = "//a[@class='OptionItem' and contains(text(),'${driveName}')]";

	public final String ELEMENT_DRIVER_OPTION_2 = ".//*[@id='DriveTypeDropDown']//li[$num]";
	public final By ELEMENT_DRIVES_LIST=By.xpath(".//*[@id='DriveTypeDropDown']");
	
	//Task/Event activity
	public final String ELEMENT_ACTIVITY_TASK_EVENT_TITLE = "//*[@class='linkTitle' and text()='$name']";
	public final String ELEMENT_ACTIVITY_TASK_EVENT_TITLE_SPACE_AS_LINK=".//*[contains(@href,'${space}')][contains(text(),'${event}')]";
	public final String ELEMENT_ACTIVITY_TASK_EVENT_START_DATE_MONTH=".//*[contains(text(),'${name}')]/../..//*[contains(@class,'heading')][contains(text(),'${month}')]";
	public final String ELEMENT_ACTIVITY_TASK_EVENT_START_DATE_DAY=".//*[contains(text(),'${name}')]/../..//*[contains(@class,'content')][contains(text(),'${day}')]";
	public final String ELEMENT_ACTIVITY_TASK_EVENT_DESCRIPTION = "//*[@class='linkTitle' and text()='$name']/../..//*[text()='$description ']";
	public final String ELEMENT_ACTIVITY_TASK_EVENT_DATE = "//*[@class='linkTitle' and text()='$name']/../..//*[@class='dateTime' and contains(text(),'$date')]";
	public final String ELEMENT_ACTIVITY_TASK_EVENT_LOCATION = "//*[@class='linkTitle' and text()='$name']/../..//*[@class='location']/text()";
	public final String ELEMENT_ACTIVITY_TASK_EVENT_TOTAL_COMMENT_NUMBER="//*[@class='linkTitle' and text()='${name}']/../../../..//*[contains(@class,'actionBar')][contains(.,'${number}')]";
	public final String ELEMENT_ACTIVITY_TASK_EVENT_COMMENT = "//*[@class='linkTitle' and text()='$name']/../../../..//*[@class='commentList']//*[contains(text(),'$comment')]";
	public final String ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_ITEM="//*[@class='linkTitle' and text()='$name']/../../../..//*[@class='commentList']//*[contains(@class,'commentItem')]";
	public final String ELEMENT_ACTIVITY_TASK_EVENT_COMMENT_RECURRING_CANCEL="//*[@class='linkTitle' and text()='$name']/../../../..//*[@class='commentList']//*[contains(text(),'Event cancelled for $date')]";
	public final String ELEMENT_ACTIVITY_EVENT_COMMENT_REPEAT_DAY="Event will be repeated every day, $number times";
	public final String ELEMENT_ACTIVITY_EVENT_COMMENT_CHECK_ALL_DAY="Event is now an all-day event";
	public final String ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_TITLE="Title has been updated to: $title";
	public final String ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_DES="Description has been updated to: $description";
	public final String ELEMENT_ACTIVITY_EVENT_COMMENT_UPDATE_LOC="Location has been updated to: $location";
	public final String ELEMENT_ACTIVITY_TASK_COMMENT_ATTACHMENT="Attachment(s) has been added to the task";
	public final String ELEMENT_ACTIVITY_TASK_COMMENT_UPDATE_NOTE="Note has been updated to: $note";
	public final String ELEMENT_ACTIVITY_TASK_COMMENT_UPDATE_STATUS_CANCEL="Task has been canceled.";

	//Wiki activity
	public final String ELEMENT_WIKI_COMMENT_EDIT_TITLE = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'title has been updated to: ${title}')]";
	public final String ELEMENT_WIKI_COMMENT_EDIT_CONTENT = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'content has been edited')]";
	public final String ELEMENT_ACTIVITY_WIKI_TITLE = "//*[@class='linkTitle' and text()='${title}']";
	public final String ELEMENT_ACTIVITY_WIKI_CONTENT = "//*[@class='linkTitle' and text()='${title}']/../../..//*[@class='contentWiki theContent']/*[@class='text']";
	public final String ELEMENT_ACTIVITY_WIKI_VERSION = "//*[@class='linkTitle' and text()='${title}']/../..//*[@class = 'pull-right versionLabel' and contains(text(), 'Version: ${version}')]";
	public final String ELEMENT_ACTIVITY_MOVE_WIKI_PAGE = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'Page has been moved to: ${path}')]";
	public final String ELEMENT_ACTIVITY_WIKI_LINK = "//*[@class='titleWiki']/a[@href='/portal/intranet/wiki/${title}']";
    public final String ELEMENT_ACTIVITY_WIKI_VIEW_CHANGE_LINK=".//*[contains(text(),'$title')]/../../../..//*[contains(@class,'uiIconViewChange')]";
	
	//Question activity
	public final String ELEMENT_QUESTION_ACTIVITY_TITLE="//*[@class='author']/*[contains(@href,'$user')]/../../..//*[@class='titleAnswer']/*[@class='linkTitle' and text()='$question']";
	public final String ELEMENT_QUESTION_ACTIVITY_RATING="//*[@class='author']/*[contains(@href,'$user')]/../../..//*[@class='titleAnswer']/*[@class='linkTitle' and text()='$question']/../..//*[@class='avgRatingImages sumaryRate']";
	public final String ELEMENT_QUESTION_ACTIVITY_CONTENT="//*[@class='author']/*[contains(@href,'$user')]/../../..//*[contains(@class,'titleAnswer')]/*[@class='linkTitle' and text()='$question']/../../..//*[contains(@class,'contentAnswer')]//*[@class='text']";
	public final String ELEMENT_QUESTION_ACTIVITY_ANSWER_NUMBER="//*[@class='author']/*[contains(@href,'$user')]/../../..//*[contains(@class,'titleAnswer')]/*[@class='linkTitle' and text()='$question']/../../..//*[contains(@class,'contentAnswer')]//*[contains(text(),'$number Answer')]";
	public final String ELEMENT_QUESTION_ACTIVITY_COMMENT_NUMBER="//*[@class='author']/*[contains(@href,'$user')]/../../..//*[contains(@class,'titleAnswer')]/*[@class='linkTitle' and text()='$question']/../../..//*[contains(@class,'contentAnswer')]//*[contains(text(),'$number Comment')]";
	public final String ELEMENT_QUESTION_ACTIVITY_COMMENT_CONTENT="//*[@class='author']/*[contains(@href,'$userActivity')]/../../..//*[contains(@class,'titleAnswer')]/*[@class='linkTitle' and text()='$question']/../../../..//*[@class='commentList']/div[$index]//*[@class='author']/*[contains(@href,'$userComment')]/../../*[@class='contentComment']";
	public final String ELEMENT_QUESTION_ACTIVITY_COMMENT_ANSWER="Answer has been submitted: ";
	public final String ELEMENT_QUESTION_ACTIVITY_NUMBER_COMMENT="//*[@class='author']/*[contains(@href,'$user')]/../../..//*[contains(@class,'titleAnswer')]/*[@class='linkTitle' and text()='$question']/../../../..//*[contains(@id,'CommentLink')]";
	public final String ELEMENT_QUESTION_ACTIVITY_UNACTIVATE_COMMENT="Question has been unactivated";
	public final String ELEMENT_QUESTION_ACTIVITY_ACTIVATE_COMMENT="Question has been activated";
	public final String ELEMENT_QUESTION_ACTIVITY_UPDAT_TITLE_COMMENT="Title has been updated to: $value";
	public final String ELEMENT_QUESTION_ACTIVITY_ANSWER_ICON="//*[contains(text(),'$question')]/../../../..//*[contains(@class,'uiIconReply')]";
	public By ELEMENT_ANSWER_FORM=By.id("UIResponseForm");
	
	// Activity of file
	public String ELEMENT_ACTIVITY_FILE_TITLE = "//*[@class='fileTypeContent']/..//*[@class='linkTitle' and contains(text(),'{$title}')]";
	public String ELEMENT_ACTIVITY_FILE_CHECK_ICON_FILE = "//*[@data-original-title='{$title}']//*[@class='uiIcon64x64FileDefault uiIcon64x64nt_file uiIcon64x64texthtml']";
	public String ELEMENT_ACTIVITY_FILE_TITLE_CHECK_FILE_SIZE = "//*[@class='fileTypeContent']/..//*[@class='linkTitle' and contains(text(),'{$title}')]/..//*[@class='versionFile' and contains(text(),'File Size:')]";
	public String ELEMENT_ACTIVITY_FILE_EDIT_FILE_FROM_ACTIVITY = "//*[@class='fileTypeContent']/..//*[@class='linkTitle' and contains(text(),'{$title}')]/../../../..//*[@class='uiIconEdit uiIconLightGray']";
	public String ELEMENT_ACTIVITY_FILE_USER_ICON = "//*[@class='activityAvatar avatarCircle']/*[contains(@href,'${user}')]/../..//*[@class='uiIconSocFileSharing uiIconSocWhite']";
	public String ELEMENT_ACTIVITY_FILE = "//*[contains(text(),'${title}')]/..//*[@class='linkTitle' and contains(text(),'${file}')]";

	// Activity of web content
	//public String ELEMENT_ACTIVITY_WEBCONTENT_TITLE = "//*[@class='uiIcon64x64Templateexo_webContent']/../..//*[@class='linkTitle' and contains(text(),'{$title}')]";
	public String ELEMENT_ACTIVITY_WEBCONTENT_CHECK_VERSION ="//*[@class='uiIcon64x64Templateexo_webContent']/../..//*[@class='linkTitle' and contains(text(),'${title}')]/..//*[@class='versionFile' and contains(text(),'Version: {$version}')]";
	public String ELEMENT_ACTIVITY_WEBCONTENT_CHECK_STATUS ="//*[@class='uiIcon64x64Templateexo_webContent']/../..//*[@class='linkTitle' and contains(text(),'${title}')]/..//*[@class='versionFile' and contains(text(),'- {$status}')]";

	// Activity of Product
	public String ELEMENT_ACTIVITY_PRODUCT_TITLE = "//*[@class='uiIcon64x64Templateacme_product']/../..//*[@class='linkTitle' and contains(text(),'{$title}')]";
	public String ELEMENT_ACTIVITY_PRODUCT_CHECK_VERSION ="//*[@class='uiIcon64x64Templateacme_product']/../..//*[@class='linkTitle' and contains(text(),'{$title}')]/..//*[@class='versionFile' and contains(text(),'Version: {$version}')]";
	public String ELEMENT_ACTIVITY_PRODUCT_CHECK_STATUS ="//*[@class='uiIcon64x64Templateacme_product']/../..//*[@class='linkTitle' and contains(text(),'{$title}')]/..//*[@class='versionFile' and contains(text(),'- {$status}')]";

	// Activity upload file
	public String ELEMENT_ACTIVITY_FILE_UPLOAD_TITLE ="//*[contains(text(),'{$title}')]";
	public String ELEMENT_ACTIVITY_PREVIEW_FILE_WINDOW_NAME_OF_FILE = "//*[@id='UISocialPopupWindow']//*[contains(text(),'{$title}')]";
	public String ELEMENT_ACTIVITY_EDIT_FROM_HOMEPAGE ="//*[@id='UIDocumentForm']//*[contains(text(),'{$title}')]";

	// Common activity
	public final String ELEMENT_ACTIVITY_COMMENT = ".//*[contains(text(),'${title}')]/../../../../..//*[contains(text(),\"${comment}\")]";
	public final String ELEMENT_ACTIVITY_NOT_ANY_COMMENT=".//*[contains(text(),'$title')]/../../../../..//*[contains(@class,'commentList')][not(div)]";
	public final String ELEMENT_ACTIVITY_COMMOM_CHECK_COMMENT_OF_ACTIVITY = ".//*[contains(text(),'${title}')]/../../../../..//*[contains(text(),\"${comment}\")]";
	public final String ELEMENT_ACTIVITY_NUM_LIKE ="//*[@id='boxContainer']//*[contains(.,'$activity')]//*[contains(@class,'statusAction')]//*[contains(@id,'Like')][contains(.,'$num')]";
	public final String ELEMENT_ACTIVITY_VIEW_A_NODE = "//*[@class='linkTitle' and contains(text(),'{$title}')]/../../../..//*[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_ACTIVITY_EDIT_A_NODE = "//*[@class='linkTitle' and contains(text(),'{$title}')]/../../../..//*[@class='uiIconEdit uiIconLightGray']";
	public final String ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM ="//*[@id='boxContainer']//*[contains(text(),'${title}')]";
	public final String ELEMENT_ACTIVITY_VERSION = ".//*[contains(text(),'${name}')]/../../..//*[.//*[@class='pull-right versionLabel'][text()='${version}']";
	public final String ELEMENT_ACTIVITY_TITLE="//*[@id='boxContainer']//*[contains(text(),\"${text}\")]/../..//*[contains(text(),\"${file}\")]";
	public final String ELEMENT_PUBLICATION_LASTCOMMENT = "//*[contains(text(),'${title}')]/../../../..//*[@class='commentItem commentItemLast']";
	public final String ELEMENT_PUBLICATION_DELETE_LASTCOMMENT = "//*[contains(text(),'${title}')]/../../../..//*[@class='commentRight']/..//*[@class='uiIconClose uiIconLightGray controllDelete']";
	public final String ELEMENT_PUBLICATION_FIRSTPOST_AUTHOR = "//div[1]/form//*[@class='heading']//*[@class='author']//*[contains(text(),'${name}')]";
	public final By ELEMENT_PUBLICATION_FIRSTPOST_AUTHORAVATAR = By.xpath("//div[1]/form//*[@class='activityAvatar avatarCircle']");
	public final By ELEMENT_PUBLICATION_FIRSTPOST_ACTIVITYTEXT = By.xpath("//div[1]/form//*[@class='description']");
	public final String ELEMENT_ACTIVITY_LINK = "//*[@id='boxContainer']//*[contains(text(),'${title}')]/../..//*[contains(@onclick,'${link}')]";
	public final String ELEMENT_ACTIVITY_LINK_USER_ICON = "//*[@class='activityAvatar avatarCircle']/*[contains(@href,'${user}')]/../..//*[@class='uiIconSocLinkMini uiIconSocWhite']";
	public final String ELEMENT_ACTIVITY_MENTION_USER = "//*[@id='boxContainer']//*[contains(text(),'${content}')]/a[contains(@href,'${user}')]";
	public final String ELEMENT_ACTIVITY_PUBLICATION_VIEW_LASTCOMMENT="//*[contains(text(),'$comment')]/../../../..//*[@class='viewComment']/..//*[contains(@class,'uiIconWatch')]";
	
	//Document preview activity
	public final String ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE = ".//*[@class='linkTitle'][@data-original-title='${title}']";
	public final String ELEMENT_ACTIVITY_WEBCONTENT_TITLE =".//a[@title='${title}']";
	public final String ELEMENT_ACTIVITY_AUDIO_VIDEO_TITLE=".//a[@href='${link}']";
	public final String ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK =".//*[contains(@data-original-title,'${nameFile}')]/../../../..//i[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_ACTIVITY_EMBBED_MEDIA_VIEW_LINK =".//*[contains(@href,'${linkFile}')]/../../../..//i[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_ACTIVITY_WEBCONTENT_VIEW_LINK=".//a[@title='${nameContent}']/../../../..//i[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_ACTIVITY_DOCUMENT_MEDIA_DOWNLOAD_BTN=".//*[contains(@data-original-title,'${nameFile}')]/../../../..//*[contains(@class,'uiIconDownload')]/..";

	//Title box
	public final String ELEMENT_TITLE_BOX="//*[@id='boxContainer']//*[contains(text(),\"${title}\")]/../..//*[@class='linkTitle']";
	
	//Comment box
	public final String ELEMENT_COMMENTBOX="//*[contains(text(),\"${title}\")]/../../../..//div[@class='replaceTextArea editable']";
	public final String ELEMENT_ICON_COMMENT = "//*[contains(text(),\"${title}\")]/../../../..//i[@class='uiIconComment uiIconLightGray']";
	public final String ELEMENT_ICON_LIKE = "//*[contains(text(),\"${title}\")]/../../../..//i[@class='uiIconThumbUp uiIconLightGray']";
	public final String ELEMENT_ICON_UNLIKE = "//*[contains(text(),\"${title}\")]/../../../..//i[@class='uiIconThumbUp uiIconBlue']";
	public final String ELEMENT_LIKE_NUMBER = "//*[contains(text(),\"${title}\")]/../../../..//*[contains(@class,'uiIconThumbUp')]/..";
	public final String ELEMENT_COMMENT_BUTTON = "//*[contains(text(), \"${activityText}\")]/../../../..//button[contains(@id,'CommentButton')]";
	public final String ELEMENT_ACTIVITY_ADD_YOUR_COMMENTLABEL = "//*[contains(text(),\"${activityText}\")]/../../../..//*[contains(@id,'DisplayCommentTextarea')]/../div[@class='placeholder']";
	public final String ELEMENT_DELETE_COMMENT_BUTTON = "//*[contains(text(),'${activityText}')]/..//*[@class='contentComment'  and contains(text(),\"${commentText}\")]/../..//*[contains(@id,'DeleteCommentButton')]";
	
	public final String ELEMENT_COMMENT_TEXT = "//*[contains(text(),\"${activityText}\")]/../../../..//p[@class='contentComment'  and contains(.,\"${commentText}\")]";
	public final String ELEMENT_COMMENT_TEXT_QUOTES = "//*[contains(text(),\"${activityText}\")]/../../../..//p[@class='contentComment'  and contains(.,'${commentText}')]";
	public final String ELEMENT_ACTIVITY_LIKE_ICON_BLUE = ".//*[contains(text(),'${nameFile}')]/../../../..//*[@class='uiIconThumbUp uiIconBlue']";
	public final String ELEMENT_ACTIVITY_COMMENT_VIEW_HOVEROVER = ".//*[contains(text(),'${comment}')]/../..//*[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_PUBLICATION_COMMENTPOSTED = "//*[@class='commentList']//*[contains(text(),'${content}')]";
	public final String ELEMENT_PUBLICATION_SEEALLCOMMENTBTN = "//*[contains(text(),'${activity}')]/../..//*[contains(@class,'commentListInfo')]//a[@href and contains(text(),'View')]";
	public final String ELEMENT_PUBLICATION_HIDEALLCOMMENTBTN = "//*[contains(text(),'${activity}')]/../..//*[contains(@class,'commentListInfo')]//a[@href and contains(text(),'Hide')]";
	public final String ELEMENT_SUGGEST_USER_IN_COMMENT = ".//*[contains(@data-display, '${userName}')]";
	public final String ELEMENT_PUBLICATION_COMMENT_NAMEAUTHOR = "//*[contains(text(),'${comment}')]/../..//*[@class='author']/*[contains(text(),'${name}')]";
	public final String ELEMENT_PUBLICATION_COMMENT_TIMESTAMP = "//*[contains(text(),'${comment}')]/../..//*[@class='author']/*[contains(@class,'dateTime')]";
	public final String ELEMENT_PUBLICATION_COMMENT_AVATAR = "//*[contains(text(),'${comment}')]/../..//*[@class='avatarXSmall']/*[@alt='${name}']";
	public final String ELEMENT_COMMENT_AVATAR_USER="//*[contains(text(),'$activity')]/../../../..//*[contains(text(),'$comment')]/../..//*[contains(@src,'UserAvtDefault.png')][contains(@alt,'$fullName')]";
	public final String ELEMENT_COMMENT_AUTHOR ="//*[contains(text(),'$activity')]/../../../..//*[contains(text(),'$comment')]/../..//*[contains(@class,'author')]//*[contains(text(),'$fullName')]";
	public final String ELMEMENT_COMMENT_TIME="//*[contains(text(),'$activity')]/../../../..//*[contains(text(),'$comment')]/../..//*[contains(text(),'less than a minute ago')]";
	
	//Activity for Forum
	public final String ELEMENT_ACTIVITY_POLL_VOTE_FOR_POLL = "//*[@id='boxContainer']//*[contains(text(),'{$name}')]/../../../..//*[@class='uiIconSocVote uiIconSocLightGray']";
	public final String ELEMENT_ACTIVITY_TOPIC_REPLY = "//*[@id='boxContainer']//*[contains(text(),'{$name}')]/../../../..//*[@class='uiIconReply uiIconLightGray']";
	public final String ELEMENT_ACTIVITY_TOPIC_VIEW_LAST_REPLY = ".//*[contains(text(),'${topic}')]/../../..//*[@class='uiIconSocLastestReply uiIconSocLightGray']";

	//Activity for connection
	public final String ELEMENT_PUBLICATION_ACTIVITYTEXT_CONNECTED = "//*[contains(text(),\"I'm now connected with 1 user(s)\")]/../../../..//p[@class='contentComment'  and contains(text(),\"I'm now connected with ${user}\")]";

	//Activity for Space
	public final String ELEMENT_ACTIVITY_SPACE_AVATAR = ".//*[@id='boxContainer']//*[contains(text(),'${space}')]/../../..//*[contains(@src,'SpaceAvtDefault.png')]";
	public final String ELEMENT_ACTIVITY_SPACE_DESCRIPTION = ".//*[@id='boxContainer']//*[contains(text(),'${space}')]/../../..//*[contains(text(),'${des}')]";
	public final String ELEMENT_ACTIVITY_SPACE_MEMBER_NUMBER = ".//*[@id='boxContainer']//*[contains(text(),'${space}')]/../../..//*[contains(text(),'${num}')]";
	public final String ELEMENT_ACTIVITY_USERJOIN_SPACE = "//*[contains(text(),'${user}')]/../..//*[contains(text(),'Has joined the space.')]";
	public final String ELEMENT_ACTIVITY_SPACE_CHANGE_NAME=".//*[@id='boxContainer']//*[contains(text(),'${space}')]/../../..//*[contains(text(),'Name has been updated to: ${space}.')]";
	public final String ELEMENT_ACTIVITY_SPACE_SPACE_LAST_COMMENT=".//*[@id='boxContainer']//*[contains(text(),'${space}')]/../../..//*[@class='commentItem commentItemLast']//*[@class='contentComment']";
	public final String ELEMENT_ACTIVITY_SPACE_SPACE_LAST_COMMENT_JOINSPACE="//*[@id='boxContainer']//*[contains(text(),'${space}')]/../../..//*[@class='commentItem commentItemLast']//*[contains(text(),'Has joined the space')]";
	public final String ELEMENT_ACTIVITY_SPACE_ACTIVITY_DELETE_BTN = "//*[contains(text(),'${space}')]/../../../..//*[@class='heading']/..//*[@class='uiIconClose uiIconLightGray controllDelete']";
	public final String ELEMENT_ACTIVITY_SPACE_HEADING = "//*[@class='heading']//*[contains(text(),'${space}')]";
	public final String ELEMENT_ACTIVITY_SPACE_AUTHOR = "//*[contains(text(),'${title}')]/../*[contains(@class,'heading')]/*[contains(@class,'author')]";
	public final String ELEMENT_ACTIVITY_USER_ACTIVITY_DELETE_BTN = "//*[contains(text(),'${title}')]/../*[contains(@class,'heading')]/*[contains(@class,'uiIconClose uiIconLightGray controllDelete')]";
    // Arrow menu Activity
	public final By ELEMENT_ACTIVITY_ARROWDOWN_MENU = By.xpath("//*[contains(@class,'MiniArrowDown')]");
	public final By ELEMENT_ACTIVITY_ALL_ACTIVITIES = By.xpath("//*[@class='OptionItem'][contains(.,'All Activities')]");
	public final By ELEMENT_ACTIVITY_MY_ACTIVITIES = By.xpath("//*[@class='OptionItem'][contains(.,'My Activities')]");
	public final By ELEMENT_ACTIVITY_MY_SPACES = By.xpath("//*[@class='OptionItem'][contains(.,'My Spaces')]");
	public final By ELEMENT_ACTIVITY_CONNECTIONS = By.xpath("//*[@class='OptionItem'][contains(.,'Connections')]");
	Button button;
	/**
	 * constructor
	 * @param dr
	 */
	
	public ActivityStream(WebDriver dr){
		this.driver=dr;
		button = new Button(dr);
	}
	/**
	 * Define options of menu activity
	 */
	public enum optionMenuActivity{
		All_Activities,My_Spaces,My_Activities,Connections;
	}
	/**
	 * Activity arrow menu
	 * @param opt
	 */
	public void selectOptMenuActivity(optionMenuActivity opt){
		click(ELEMENT_ACTIVITY_ARROWDOWN_MENU,0,true);
		Utils.pause(500);
		switch(opt){
		case All_Activities:
			info("Select All Activities");
			click(ELEMENT_ACTIVITY_ALL_ACTIVITIES,0,true);
			break;
		case My_Spaces:
			info("Select My Spaces");
			click(ELEMENT_ACTIVITY_MY_SPACES,0,true);
			break;
		case My_Activities:
			info("Select My Activities");
			click(ELEMENT_ACTIVITY_MY_ACTIVITIES,0,true);
			break;
		case Connections:
			info("Select Connections");
			click(ELEMENT_ACTIVITY_CONNECTIONS,0,true);
			break;
		default:
			info("No option in the list. Please select correct option.");
			break;
		}
	}
	/**
	 * Check activity after added a file
	 * @param title
	 */
	public void checkActivityAddFile(String title){
		info("Verify that the file's title is shown");
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_FILE_TITLE.replace("{$title}", title)));
		info("Verify that file's icon is shown");
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_FILE_CHECK_ICON_FILE.replace("{$title}", title)));
		info("Verify that file's size is shown");
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_FILE_TITLE_CHECK_FILE_SIZE.replace("{$title}", title)));
	}

	/**
	 * Check if there is an activity in the stream
	 * @param name
	 */
	public void checkActivity(String name){
		info("Verify that the activity of the name:"+name+" is shown");
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}",name)),DEFAULT_TIMEOUT,1);
		info("The activity of the name:"+name+" is shown successfully");
	}
	/**
	 * Check if there is not an activity in the stream
	 * @param name
	 */
	public void checkNotShownActivity(String name){
		info("Verify that the activity of the name:"+name+" isnot shown");
		waitForElementNotPresent(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}",name),DEFAULT_TIMEOUT,1);
		info("The activity of the name:"+name+" isnot shown successfully");
	}
	/**
	 * Check if there is no an activity in the stream
	 * @param name
	 */
	public void checkNoActivity(String name){
		info("Verify that the activity of the name:"+name+" is not shown");
		waitForElementNotPresent(By.xpath(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}",name)),3000,1);
		info("The activity of the name:"+name+" is not shown successfully");
	}
	/**
	 * Check comment of an activity
	 * @param activity
	 * @param comment
	 */
	public void checkCommentOfActivity(String activity, String comment){
		info("Verify that the comment is added");
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT
				.replace("${title}",activity).replace("${comment}", comment),3000,1);
		info("The comment is added successfully");
	}
	
	public enum changeTypes{
		No_Value,Has_One_Value;
	}
	
	public void checkComment(String title, String comment,String value,changeTypes type){
		switch(type){
		case Has_One_Value:
			info("Verify that the comment is added");
			waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT
					.replace("${title}",title)
					.replace("${comment}",comment)
					.replace("$value",value));
			break;
		case No_Value:
			waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT
					.replace("${title}",title).replace("${comment}", comment));	
			break;
		
		}
			
	}
	/**
	 * Check number like of activity
	 * @param activity
	 * @param num
	 */
	public void checkNumLikeOfActivity(String activity,int num){
		info("Check number like of: "+activity);
		waitForAndGetElement(ELEMENT_ACTIVITY_NUM_LIKE.replace("$activity", activity).replace("$num", String.valueOf(num)));
	}
	/**
	 * Check activity of adding wiki page with 4 lines in the content
	 * @param title
	 * @param content
	 * @param version
	 */
	public void checkActivityAddWikiPage(String title, String content, String version){
		if(version==null)
			version="Version: 1";
		String[] arrayline;
		arrayline=content.split("</br>");
		info("Check wiki page's title");
		waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",title),2000,1);
		info("Check wiki page's version");
		waitForAndGetElement(ELEMENT_ACTIVITY_VERSION.replace("${name}",title).replace("${version}",version),2000,1);
		info("Check first 4 lines of the wiki page");
		waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",arrayline[0]),2000,1);
		waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",arrayline[1]),2000,1);
		waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",arrayline[2]),2000,1);
		waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",arrayline[3]),2000,1);
		info("Check line 5 of the wiki page is not shown");
		waitForElementNotPresent(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",arrayline[4]));
	}

	/**
	 * Check Activity of Wiki page
	 * @param title
	 * @param content
	 * @param version
	 * @param isEdit
	 *               = true for checking View Change link is shown
	 *               = false if not View change link
	 */
	public void checkActivityWikiPage(String title,String content,String version,boolean isEdit){
		if(!title.isEmpty()){
			info("Check wiki page's title");
			waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}",title),2000,1);
		}
		if(!content.isEmpty()){
			info("Check the content");
			String[] arrayline;
			arrayline=content.split("</br>");
			if(arrayline.length>1){
				info("Check first 4 lines of the wiki page");
				waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",arrayline[0]),2000,1);
				waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",arrayline[1]),2000,1);
				waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",arrayline[2]),2000,1);
				waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",arrayline[3]),2000,1);
				info("Check line 5 of the wiki page is not shown");
				waitForElementNotPresent(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("{$name}",arrayline[4]));
			}else{
				info("Check first line of the wiki page");
				waitForAndGetElement(ELEMENT_ACTIVITY_ELEMENT_IN_ACTIVITY_STREAM.replace("${title}",arrayline[0]),2000,1);
			}
			
		}
		if(isEdit){
			info("View change link is shown");
			waitForAndGetElement(ELEMENT_ACTIVITY_WIKI_VIEW_CHANGE_LINK.replace("$title",title));
		}
	}
	/**
	 * Check activity after add a web content
	 * @param title
	 * @param version
	 * @param status
	 */
	public void checkActivityAddWebContent(String title, String version, String status){
		if(version==null)
			version="0";
		if(status==null)
			status="Draft";	
		// check icon and title
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_WEBCONTENT_TITLE.replace("${title}", title)));
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_WEBCONTENT_CHECK_VERSION.replace("${title}", title).replace("{$version}", version)));
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_WEBCONTENT_CHECK_STATUS.replace("${title}", title).replace("{$status}", status)));
	}

	/**
	 * Check activity after add a product
	 * @param title
	 * @param version
	 * @param status
	 */
	public void checkActivityAddProduct(String title, String version, String status){
		if(version==null)
			version="0";
		if(status==null)
			status="Draft";	
		// check icon and title
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_PRODUCT_TITLE.replace("{$title}", title)));
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_PRODUCT_CHECK_VERSION.replace("{$title}", title).replace("{$version}", version)));
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_PRODUCT_CHECK_STATUS.replace("{$title}", title).replace("{$status}", status)));
	}

	/**
	 * Check content and number of lines of content on activity
	 * @param activityContent
	 * @param content
	 */
	public void checkContentOfActivity(String activityContent, String content){
		String[] sum;
		String[] cont;
		String summaryTemp=activityContent;
		String contentTemp=content;

		info("Check content and number of lines of content on activity");
		if (activityContent.contains("...")){
			summaryTemp = activityContent.replace("...", "");
		}
		if(content.contains("...")){
			contentTemp = content.replace("...", "");

		}
		sum = summaryTemp.split("\n");
		cont = contentTemp.split("<br>");
		String []sumTemp= activityContent.split("\n");

		if(cont.length<=4){
			for(int i = 0; i<sum.length; i++){
				info("sum[i]: "+sum[i]);
				info("cont[i]: "+cont[i]);
				assert sum[i].contains(cont[i]);
			}
		}
		else{
			for(int i = 0; i<4; i++){
				assert sum[i].contains(cont[i]);
			}
			assert sumTemp[3].contains(cont[3]+"...");
		}
	}

	/**
	 * Add a new comment on activity stream
	 * @param filename
	 * @param textContent
	 */
	public void addComment(String filename, String textContent){
		/*waitForAndGetElement(By.xpath(ELEMENT_ICON_COMMENT.replace("${title}", filename))).click();
		switchToParentWindow();
		WebElement input= waitForAndGetElement(By.xpath(ELEMENT_COMMENTBOX.replace("${title}",filename)));
		Actions action =new Actions(driver);
		action.moveToElement(input).sendKeys(textContent).build().perform();
		waitForAndGetElement(By.xpath(ELEMENT_COMMENT_BUTTON.replace("${activityText}", filename))).click();
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_PUBLICATION_COMMENTPOSTED.replace("${content}",textContent),2000,1);
		info("The comment is added successfully");*/
		info("Click on icon comment");
		int repeat = 0;
		while (waitForAndGetElement(ELEMENT_COMMENTBOX.replace("${title}", filename), 3000, 0,2) == null) {
			if (repeat > 5)
				break;
			info("Click on icon comment");
			click(ELEMENT_ICON_COMMENT.replace("${title}", filename));
			repeat++;
		}
		info("Put a comment to comment box");
		int repeat1 = 0;
		while(waitForAndGetElement(ELEMENT_PUBLICATION_COMMENTPOSTED.replace(
				"${content}", textContent),2000,0,2)==null){
			if (repeat1 > 5)
				break;
			switchToParentWindow();
			WebElement input = waitForAndGetElement(ELEMENT_COMMENTBOX.replace(
					"${title}", filename));
			Actions action = new Actions(driver);
			action.moveToElement(input).sendKeys(textContent).build().perform();
			click(ELEMENT_COMMENT_BUTTON.replace("${activityText}",filename));
			Utils.pause(2000);
			repeat++;
		}
		info("The comment is added successfully");
	}

	/**
	 * Add a new comment on activity stream using javascript
	 * @param activityText
	 * @param contentOfComment
	 */
	public void addCommentUsingJavascript(String activityText, String contentOfComment){
		info("add comment using javascript");
		click (ELEMENT_ICON_COMMENT.replace("${title}", activityText));
		WebElement commentText = waitForAndGetElement(ELEMENT_COMMENTBOX.replace("${title}", activityText));
		WebElement commentButton = waitForAndGetElement(ELEMENT_COMMENT_BUTTON.replace("${activityText}", activityText));
		WebElement workingLabel = waitForAndGetElement(ELEMENT_ACTIVITY_ADD_YOUR_COMMENTLABEL
				.replace("${activityText}", activityText));

		((JavascriptExecutor)driver).executeScript("arguments[0].textContent = '';", workingLabel);
		((JavascriptExecutor)driver).executeScript("arguments[0].textContent = '"+contentOfComment+"';", commentText);
		((JavascriptExecutor)driver).executeScript("arguments[0].disabled = false;", commentButton);
		((JavascriptExecutor)driver).executeScript("arguments[0].className = 'btn pull-right btn-primary';", commentButton);
		click(ELEMENT_COMMENT_BUTTON.replace("${activityText}", activityText));
		info("Verify comment successfully");
		waitForAndGetElement(ELEMENT_DELETE_COMMENT_BUTTON.
				replace("${activityText}", activityText).
				replace("${commentText}", contentOfComment), DEFAULT_TIMEOUT,1,2);
		info("Add comment successfully");
	}

	/**
	 * Show all comment of activities
	 * @param name
	 */
	public void showComment(String name){
		info("Show all comment");
		click(ELEMENT_PUBLICATION_SEEALLCOMMENTBTN.replace("${activity}",name));
		waitForAndGetElement(ELEMENT_PUBLICATION_HIDEALLCOMMENTBTN.replace("${activity}",name));
	}
	
	/**
	 * Hide all comment of activities
	 * @param name
	 */
	public void hideComment(String name){
		info("Show all comment");
		click(ELEMENT_PUBLICATION_HIDEALLCOMMENTBTN.replace("${activity}",name));
		waitForAndGetElement(ELEMENT_PUBLICATION_SEEALLCOMMENTBTN.replace("${activity}",name));
	}

	/**
	 * Delete a comment of an activity
	 * @param name
	 * @param comment
	 */
	public void deleteComment(String name,String comment){
		info("Hover over on the comment");
		mouseOver(ELEMENT_PUBLICATION_LASTCOMMENT.replace("${title}", name), true);
		click(ELEMENT_PUBLICATION_DELETE_LASTCOMMENT.replace("${title}", comment));
		click(button.ELEMENT_OK_BUTTON);
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_PUBLICATION_COMMENTPOSTED.replace("${content}", comment),2000,1);
		info("The comment is deleted successfully");
	}
	/**
	 * View a comment
	 * @param activity
	 *                  is the activity's name
	 * @param comment
	 *                  is comment's content
	 */
	public void viewComment(String activity,String comment,String value){
		info("Hover over on the comment");
		mouseOver(ELEMENT_PUBLICATION_LASTCOMMENT.replace("${title}",activity), true);
		if(value!="" || value!=null)
			click(ELEMENT_ACTIVITY_PUBLICATION_VIEW_LASTCOMMENT.replace("$comment", comment).replace("$comment",value));
		else
			click(ELEMENT_ACTIVITY_PUBLICATION_VIEW_LASTCOMMENT.replace("$comment", comment));
		Utils.pause(3000);
	}

	/**
	 * Add text into activity text box
	 * @param text
	 */
	public void addText(String text){
		info("----Add text into activity text box-----");
		WebElement inputText = waitForAndGetElement(ELEMENT_COMPOSER_INPUT_FILED,100000);
		WebElement shareButton = waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON);
		WebElement workingLabel = waitForAndGetElement(ELEMENT_ACTIVITY_WHAT_ARE_YOU_WORKING_LABEL);
		((JavascriptExecutor)driver).executeScript("arguments[0].textContent = '';", workingLabel);
		((JavascriptExecutor)driver).executeScript("arguments[0].textContent = '"+text+"';", inputText);
		((JavascriptExecutor)driver).executeScript("arguments[0].disabled = false;", shareButton);
		((JavascriptExecutor)driver).executeScript("arguments[0].className = 'pull-right btn btn-primary';", shareButton);
	}

	/**
	 * Add link activity
	 * @param link
	 */
	public void addLink(String link){
		info("----Click on Link----");
		waitForAndGetElement( ELEMENT_COMPOSER_LINK_BUTTON).click();
		info("----Input link into link box-----");
		waitForAndGetElement(ELEMENT_COMPOSER_INPUT_LINK_FIELD);
		type(ELEMENT_COMPOSER_INPUT_LINK_FIELD, link, true);
		waitForAndGetElement(ELEMENT_COMPOSER_ATTACH_LINK_BUTTON);
		info("----Click attach button-----");
		click(ELEMENT_COMPOSER_ATTACH_LINK_BUTTON);
		waitForAndGetElement(By.id("LinkTitle"));
	}

	public void closeShareLink(){
		info("close share link");
		click(ELEMENT_COMPOSER_CLOSE_SHARE_LINK_BUTTON);
	}

	/**
	 * Share activity
	 */
	public void shareActivity(){
		waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON);
		info("----Click share button----");
		click(ELEMENT_COMPOSER_SHARE_BUTTON);
		Utils.pause(1000);
	}
	/**
	 * Add new activity for space 
	 * @param addText
	 * @param text
	 * @param addLink
	 * @param link
	 */
	public void addActivity (String text, String link) {
		info("-- Adding an activity--");
		Utils.pause(3000);
		if (text!="" && text!=null) 
		{
			info("----Add text into activity text box-----");
			addText(text);
		}
		if (link!="" && link!=null) 
		{
			info("----Add link into activity text box-----");
			addLink(link);
		}
		shareActivity();
		Utils.pause(3000);
		info("-- Verify that an activity has been added --");
		if (text!="" && text!=null){
			waitForAndGetElement(ELEMENT_ACTIVITY_AUTHOR_ACTIVITY.replace("${activityText}", text),3000,1);
		}
	}
	/**
	 * Open More menu of Space menu
	 */
	public void openMorelist(){
		info("Click on More button");
		click(ELEMENT_SPACE_MENU_MORE_BTN);
		Utils.pause(2000);
	}

	/**
	 *  Add an activity stream with a text and a attached file
	 * @param nameDrive
	 * @param pathFolder
	 * @param pathData
	 * @param nameFile
	 * @param addText
	 * @param text
	 */
	public void uploadAndShareFileActivity(String nameDrive,String pathFolder,String pathData,String nameFile,String text) {
		info("-- Adding an activity--");
		Utils.pause(3000);
		openUploadPopup(nameDrive,pathFolder);
		uploadFileFromAS(pathData,nameFile);
		shareFileActivity(nameDrive,pathFolder,nameFile,text);
	}

	/**
	 * Add an activity stream with selecting a document that
	 * existed in SE
	 * @param nameDrive
	 * @param pathFolder
	 * @param nameFile
	 * @param textDes
	 */
	public void shareFileActivity(String nameDrive,String pathFolder,String nameFile,String textDes){
		info("-- Adding an activity--");
		Utils.pause(3000);
		for(int repeat=0;; repeat ++){
			if (repeat > 1) {
				if (waitForAndGetElement(ELEMENT_COMPOSER_FILE_ATTACHMENT_ACTIVITY, 3000, 0) != null)
					break;
			}
			if (waitForAndGetElement(ELEMENT_COMPOSER_FILE_ATTACHMENT_ACTIVITY, 5000, 0) != null) {
				info("Element " + ELEMENT_COMPOSER_FILE_ATTACHMENT_ACTIVITY
						+ " is displayed");
				break;
			}
			info("Retry...[" + repeat + "]");
			this.driver.navigate().refresh();
			openUploadPopup(nameDrive,pathFolder);
			waitForAndGetElement(By.linkText(nameFile)).click();
			Utils.pause(2000);
			info("click on Select button");
			click(ELEMENT_SELECT_BUTTON);
		}

		info("add a text to composer box of AS");
		if(textDes!=null && textDes!="")
			addText(textDes);
		info("----Click share button----");
		shareActivity();
	}

	/**
	 * Open Upload Popup from Activity Stream
	 * @param nameDrive 
	 * @param path  where put the upload file
	 */
	public void openUploadPopup(String nameDrive,String path){
		info("----Click on file icon----");
		Utils.pause(500);
		for(int repeat=0;; repeat ++){
			if (repeat > 1) {
				if (waitForAndGetElement(ELEMENT_COMPOSER_FILE_BUTTON, 3000, 0) != null) {
					click(ELEMENT_COMPOSER_FILE_BUTTON);
					if (waitForAndGetElement(ELEMENT_SELECT_FILE_POPUP, 3000, 0) != null) {
						break;
					}
				}
			}
			if (waitForAndGetElement(ELEMENT_COMPOSER_FILE_BUTTON, 5000, 0) != null) {
				info("Element " + ELEMENT_COMPOSER_FILE_BUTTON
						+ " is displayed");
				click(ELEMENT_COMPOSER_FILE_BUTTON);
				if (waitForAndGetElement(ELEMENT_SELECT_FILE_POPUP, 3000, 0) != null) {
					break;
				}
			}
			info("Retry...[" + repeat + "]");
			this.driver.navigate().refresh();
			click(ELEMENT_COMPOSER_FILE_BUTTON);
		}
		info("----Upload a file-----");
		waitForAndGetElement(ELEMENT_SELECT_FILE_POPUP,2000,1);
		
		if(!nameDrive.isEmpty()){
			info("Click on drop down list");
			click(ELEMENT_ACTIVITY_UPLOAD_POPUP);
			info("Drop list is shown");
			waitForAndGetElement(ELEMENT_DRIVES_LIST);
			info("Drive item is shown in the list");
			if(waitForAndGetElement(ELEMENT_DRIVER_OPTION.replace("${driveName}",nameDrive),3000,0)==null){
				info("select a driver:"+1);
				click("(.//*[@id='DriveTypeDropDown']//*[@class='OptionItem'])[1]");
			}else{
			info("select a driver:"+nameDrive);
			click(ELEMENT_DRIVER_OPTION.replace("${driveName}",nameDrive));
			}
		}
			
		info("go to the folder by path:"+path);
		String[] arrayPath = path.split("/");
		for(String arrayElement:arrayPath){
			click(ELEMENT_ACTIVITY_UPLOAD_POPUP_NODE.replace("${nameNode}", arrayElement));
			Utils.pause(2000);
		}
	}

	/**
	 * Upload a file from Upload Popup
	 * @param path     where put TestData folder
	 * @param nameFile
	 */
	public void uploadFileFromAS(String path,String nameFile){
		info("-- Upload file --");
		WebElement frame = waitForAndGetElement(ELEMENT_UPLOAD_FILE_FRAME_XPATH,3000,0);
		driver.switchTo().frame(frame);
		click(ELEMENT_UPLOAD_BUTTON);
		uploadFileUsingRobot(path+nameFile);
		switchToParentWindow();
		waitForElementNotPresent(ELEMENT_ACTIVITY_UPLOAD_POPUP_PROGRESS_UPLOAD,3000,0);
		click(ELEMENT_ACTIVITY_UPLOAD_POPUP_CLOSE_BTN);
		Utils.pause(2000);
		info("Upload finished");
	}


	/**
	 * Post a activity with mention a user and description text
	 * @param username
	 * @param text
	 * @throws AWTException 
	 */
	public void mentionUserActivity(String username, String text) throws AWTException{
		type(ELEMENT_COMPOSER_INPUT_FILED, "@"+username,false);
		Robot robot = new Robot();
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.pause(2000);
		if(!text.isEmpty())
			type(ELEMENT_COMPOSER_INPUT_FILED,text,false);
		click(ELEMENT_COMPOSER_SHARE_BUTTON);
		Utils.pause(2000);
	}
	/**
	 * Check mention list user
	 * @param user
	 * @param text
	 * @param isPresent
	 * 					true if user is in the list
	 * 					false if user is not in the list
	 */
	public void checkMentionListUser(String user,String text,boolean isPresent){
		if(!text.isEmpty())
			type(ELEMENT_COMPOSER_INPUT_FILED,text,false);
		type(ELEMENT_COMPOSER_INPUT_FILED, "@"+user,false);
	    if(isPresent){
	    	waitForAndGetElement(ELEMENT_PUBLICATION_SUGGEST_USER.replace("${name}", user));
	    }else{
	    	waitForElementNotPresent(ELEMENT_PUBLICATION_SUGGEST_USER.replace("${name}", user));
	    }
	}
	/**
	 * Post a comment with mention a user and description text
	 * @param username
	 * @param textContent
	 * @param activity
	 */
	public void addCommentWithMentionUser(String activity, String username, String textContent){
		info("Add comment with mention user");
		click(ELEMENT_ICON_COMMENT.replace("${title}", activity));
		type(ELEMENT_COMMENTBOX.replace("${title}",activity), "@"+username, false);
		click(ELEMENT_SUGGEST_USER_IN_COMMENT.replace("${userName}", username));
		Utils.pause(2000);
		if (!textContent.isEmpty())
			type(ELEMENT_COMMENTBOX.replace("${title}",activity), textContent, false);
		click(ELEMENT_COMMENT_BUTTON.replace("${activityText}", activity));
	}

	/**
	 * Open Preview mode by clicking on View link
	 * 
	 * @param nameDocument
	 * @param type
	 *            if type=1, this is for office document files and media files
	 *            if type=2, this is for webcontent files if type=3, this is for
	 *            embedded medias as: youtube, vimeo, slideshared...
	 * @param link
	 */
	public void openPreviewModeOnViewLink(String nameDocument,int type,String link){
		info("Open Preview mode");
		switch(type){
		case 1:
			info("this is a documents or medias");
			waitElementAndTryGetElement(ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}", nameDocument));
			click(ELEMENT_ACTIVITY_DOCUMENT_MEDIA_VIEW_LINK.replace("${nameFile}", nameDocument));
			break;
		case 2:
			info("this is a content");
			waitElementAndTryGetElement(ELEMENT_ACTIVITY_WEBCONTENT_VIEW_LINK.replace("${nameContent}", nameDocument));
			click(ELEMENT_ACTIVITY_WEBCONTENT_VIEW_LINK.replace("${nameContent}", nameDocument));
			break;
		case 3: 
			info("this is a embedded media");
			waitElementAndTryGetElement(ELEMENT_ACTIVITY_EMBBED_MEDIA_VIEW_LINK.replace("${linkFile}",link));
			click(ELEMENT_ACTIVITY_EMBBED_MEDIA_VIEW_LINK.replace("${linkFile}",link));
			break;
		default:
			info("Not type for your format.Please check your type");
			break;
		}
		Utils.pause(2000);
	}

	/**
	 * Open Preview mode by clicking on file's name
	 * 
	 * @param fileName
	 * @param link
	 * @param type
	 *            if type=1, this is for office document files and media files
	 *            if type=2, this is for webcontent files if type=3, this is for
	 *            embedded medias as: youtube, vimeo, slideshared...
	 */
	public void openPreviewModeOnFileName(String fileName,String link,int type){
		info("Open Preview mode");
		switch(type){
		case 1:
			info("this is a documents or medias");
			waitElementAndTryGetElement(ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}", fileName));
			click(ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE.replace("${title}", fileName));
			break;
		case 2:
			info("this is a content");
			waitElementAndTryGetElement(ELEMENT_ACTIVITY_WEBCONTENT_TITLE.replace("${title}",fileName));
			click(ELEMENT_ACTIVITY_WEBCONTENT_TITLE.replace("${title}",fileName));
			break;
		case 3: 
			info("this is a embedded media");
			waitElementAndTryGetElement(ELEMENT_ACTIVITY_AUDIO_VIDEO_TITLE.replace("${link}",link));
			click(ELEMENT_ACTIVITY_AUDIO_VIDEO_TITLE.replace("${link}",link));
			break;
		default:
			info("Not type for your format.Please check your type");
			break;
		}
		Utils.pause(2000);
	}

	/**
	 * Like/Unlike an activity
	 * @param activityText input a text (String) 
	 */
	public void likeActivity(String activityText){
		info("-- Action: Like or Unlike an activity --");
		info("-- Like activity --");
		int numLike = Integer.parseInt(waitForAndGetElement(ELEMENT_LIKE_NUMBER.replace("${title}", activityText)).getText().trim());
		click(ELEMENT_ICON_LIKE.replace("${title}", activityText));
		info("-- Verify Like button is highlighted --");
		waitForAndGetElement(ELEMENT_ICON_UNLIKE.replace("${title}", activityText));
		info("-- Like successfully and Verify number of like is updated --");
		int newNumLike = Integer.parseInt(waitForAndGetElement(ELEMENT_LIKE_NUMBER.replace("${title}", activityText)).getText().trim());
		assert (newNumLike==(numLike+1)):"Number of like is updated";
		Utils.pause(2000);
	}

	/**
	 * Like/Unlike an activity
	 * @param activityText input a text (String) 
	 */
	public void unlikeActivity(String activityText){
		info("-- Action: Like or Unlike an activity --");
		info("-- Unlike activity --");
		int numLike = Integer.parseInt(waitForAndGetElement(ELEMENT_LIKE_NUMBER.replace("${title}", activityText)).getText().trim());
		click(ELEMENT_ICON_UNLIKE.replace("${title}", activityText));
		info("-- Verify UnLike button is gray --");
		waitForAndGetElement(ELEMENT_ICON_LIKE.replace("${title}", activityText));
		info("-- Unlike successfully and Verify number of like is updated --");
		int newNumLike = Integer.parseInt(waitForAndGetElement(ELEMENT_LIKE_NUMBER.replace("${title}", activityText)).getText().trim());
		assert (newNumLike==(numLike-1)):"Number of like is updated";
		Utils.pause(2000);
	}
	/**
	 * Remove an activity
	 * @param name
	 */
	public void deleteActivity(String name){
		info("remove activity");
		mouseOver(ELEMENT_ACTIVITY_BOX.replace("${name}", name), true);
		click(ELEMENT_ACTIVITY_BOX_DELETE_BUTTON.replace("${name}", name),2);
		Utils.pause(500);
		click(button.ELEMENT_OK_BUTTON);
		waitForElementNotPresent(ELEMENT_ACTIVITY_BOX.replace("${name}", name));
		info("the activity is removed successfully");
	}
	/**
	 * Open answer form from Activity Stream
	 * @param question
	 */
	public void goToReplyAnswerQuestion(String question){
		info("Click on Answer link");
		click(ELEMENT_QUESTION_ACTIVITY_ANSWER_ICON.replace("$question",question));
		waitForAndGetElement(ELEMENT_ANSWER_FORM);
		info("Answer form is shown successfully");
	}
	/**
	 * Check format of a comment 
	 * @param activity
	 * @param comment
	 * @param fullName
	 */
	public void checkFormatComment(String activity,String comment,String fullName){
		info("Avatar and content of user comment");
		waitElementAndTryGetElement(ELEMENT_COMMENT_AVATAR_USER
				.replace("$activity",activity)
				.replace("$comment",comment)
				.replace("$fullName",fullName));
		info("Name of user comment");
		waitElementAndTryGetElement(ELEMENT_COMMENT_AUTHOR
				.replace("$activity",activity)
				.replace("$comment",comment)
				.replace("$fullName",fullName));
		info("Time comment is posted");
		waitElementAndTryGetElement(ELMEMENT_COMMENT_TIME
				.replace("$activity",activity)
				.replace("$comment",comment));
	}
	/**
	 * Click on View Change link on action bar
	 * @param title
	 */
	public void clickOnViewChange(String title){
		info("Click on View change link");
		click(ELEMENT_ACTIVITY_WIKI_VIEW_CHANGE_LINK.replace("$title",title));
		waitForElementNotPresent(ELEMENT_ACTIVITY_WIKI_VIEW_CHANGE_LINK.replace("$title",title));
	}
	/**
	 * Verify that has not any comment of an activity
	 * @param title
	 */
	public void checkNotComment(String title){
		info("Verify that the activity hasn't any comment");
		waitForAndGetElement(ELEMENT_ACTIVITY_NOT_ANY_COMMENT.replace("$title",title));
	}
	
}
