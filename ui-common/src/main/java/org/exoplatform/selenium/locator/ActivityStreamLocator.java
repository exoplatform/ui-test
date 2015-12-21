package org.exoplatform.selenium.locator;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class ActivityStreamLocator extends PlatformBase {

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
	public final String ELEMENT_PUBLICATION_DELETE_LASTCOMMENT = "//*[contains(text(),'${title}')]/../../../..//*[@class='commentRight']/..//*[contains(@class,'uiIconClose')]";
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
	public final String ELEMENT_COMMENTBOX="//*[contains(text(),\"${title}\")]/../../../..//*[contains(@class,'replaceTextArea')]";
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
	
	public final String ELEMENT_PUBLICATION_COMMENTPOSTED = ".//*[contains(text(),'$activity')]/../../..//*[contains(text(),'${content}')]";
	public final String ELEMENT_PUBLICATION_COMMENTPOSTED_MENTION = ".//*[contains(text(),'$activity')]/../../..//*[contains(@href,'$username')]";
	public final String ELEMENT_PUBLICATION_SEEALLCOMMENTBTN = "//*[contains(text(),'${activity}')]/../..//*[contains(@class,'commentListInfo')]//a[@href and contains(text(),'View')]";
	public final String ELEMENT_PUBLICATION_HIDEALLCOMMENTBTN = "//*[contains(text(),'${activity}')]/../..//*[contains(@class,'commentListInfo')]//a[@href and contains(text(),'Hide')]";
	public final String ELEMENT_SUGGEST_USER_IN_COMMENT = ".//*[contains(@class,'autocomplete-menu')]//*[contains(@data-ref-id,'${userName}')]";
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
	
	/*********************************************DOCUMENT PREVIEW****************************************************************************/
	 //Preview mode shadow
	public final By ELEMENT_PREVIEW_MODE=By.xpath(".//*[@id='UIDocumentPreview']");
		
	
	//New File Reader form-->Actions bar
	public final By ELEMENT_ACTIONS_SIDERBAR_TOGGLE_BTN=By.cssSelector("#sidebarToggle");
	public final By ELEMENT_ACTIONS_SEARCH_BTN=By.cssSelector(".uiIconSearch");
	public final By ELEMENT_ACTIONS_ARROW_UP_BTN =By.cssSelector(".uiIconArrowUp");
	public final By ELEMENT_ACTIONS_ARROW_DOWN_BTN =By.cssSelector(".uiIconArrowDown");
	public final By ELEMENT_ACTIONS_PAGE_INPUT_NUMBER_BOX =By.cssSelector("#pageNumber");
	//public final By ELEMENT_ACTIONS_ZOOM_OUT_BTN =By.cssSelector(".uiIconMinimize");
	public final By ELEMENT_ACTIONS_ZOOM_OUT_BTN =By.id("zoomOut");
	public final By ELEMENT_ACTIONS_ZOOM_IN_BTN =By.cssSelector(".uiIconSimplePlusMini");
	public final By ELEMENT_ACTIONS_SCALE_SELECT_BOX =By.cssSelector("#scaleSelect");
	public final By ELEMENT_ACTIONS_DOWNLOAD_BTN =By.cssSelector("#toolbarViewerRight .uiIconDownload.uiIconLightGray");
	public final By ELEMENT_ACTIONS_FULLSCREEN_BTN =By.cssSelector("#presentationMode .uiIconEcmsExpand.uiIconLightGray");
	public final By ELEMENT_ACTIONS_TOOLS_BTN =By.cssSelector("#secondaryToolbarToggle .uiIconMoreAction.uiIconLightGray");
	
	//New File Reader form --> file content
	public final By ELEMENT_READER_FILE_CONTENT_PAGE_1 = By.xpath("//*[@id='viewerContainer']");
	
	//New File Reader form--> Right panel
	public final By ELEMENT_RIGHT_PANEL_HEADER = By.xpath(".//*[@id='UIPreviewCommentArea']//*[@class='title']");
	public final String ELEMENT_RIGHT_PANEL_PROFILE_AVATAR= ".//*[contains(@class,'avatarMedium ')]//img[contains(@alt,'${fullName}')]";
	public final String ELEMENT_RIGHT_PANEL_PROFILE_NAME_LINK=".//*[contains(@class,'rightBlock')]//*[contains(@href,'${firstName}')]";
	public final By ELEMENT_RIGHT_PANEL_PROFILE_DATE_TIME = By.xpath(".//*[contains(@class,'profile')]//*[contains(@class,'dateTime')]");
	public final By ELEMENT_RIGHT_PANEL_COMMENT_INPUT_BOX = By.cssSelector("#commentTextAreaPreview");
	public final By ELEMENT_RIGHT_PANEL_COMMENT_AREA_BOX_WITH_NO_COMMENT=By.cssSelector(".noComment");
	
	/**
	 * Reader Viewer area for a file that has over 99 pages or over 5Mb
	 */
	public final By ELEMENT_PREVIEW_MODE_VIEW_READER= By.id("viewer");
	public final By ELEMENT_PEVIEW_MODE_NOT_AVAIABLE_ICON = By.xpath(".//*[@class='iconContainer']/i");
	public final By ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE= By.xpath(".//*[@id='UIDocumentPreview']//h4[text()='The preview of this document is not available.']");
	public final By ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE_LARGE_FILE= By.xpath(".//*[@id='UIDocumentPreview']//h4[text()='The preview is not available for content larger than 5 MB.']");
	public final By ElEMENT_PREVIEW_MODE_NOT_AVAIABLE_MESSAGE_MANY_PAGES= By.xpath(".//*[@id='UIDocumentPreview']//h4[text()='The preview is not available for content with over 99 pages.']");
	public final By ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_DOWNLOAD_BUTTON= By.xpath(".//*[@class='uiIconDownload uiIconWhite']");
	public final By ELEMENT_PREVIEW_MODE_NOT_AVAIABLE_OPEN_IN_DESKTOP= By.xpath(".//*[@class='btn'][text()='Open on Desktop']");
	public final By ELEMENT_PREVIEW_MODE_READER_AREA= By.id("mainContainer");
	
	 /**
	  * Exit preview mode
	  * ELEMENT_PREVIEW_MODE_CROSS_ICON : "X" icon on the top-right
	 */
	//public final By ELEMENT_PREVIEW_MODE_CROSS_ICON= By.xpath(".//a[@title='Close Window']");
	public final By ELEMENT_PREVIEW_MODE_CROSS_ICON= By.xpath(".//*[@id='UIDocumentPreview']//*[@title='Close Window']");
	
	/**
     * The top of Comment area
     */
    public final By ELEMENT_COMMENT_AREA_TOP_AVATAR = By.xpath(".//*[@id='UIPreviewCommentArea']//div[@class='profile clearfix']//img");
	public final String ELEMENT_COMMENT_AREA_TOP_FULL_NAME = ".//*[@id='UIPreviewCommentArea']//div[@class='rightBlock']//a[text()='${fullName}']";
    public final By ELEMENT_COMMENT_AREA_TOP_DATE_NOTIFICATION= By.xpath(".//*[@id='UIPreviewCommentArea']//p[@class='dateTime']");
    public final String ELEMENT_COMMENT_AREA_TOP_ACTIVITY_TILTE= ".//*[@id='UIPreviewCommentArea']//p[@title='${contentTitle}']";
    public final String ELEMENT_COMMENT_AREA_TOP_ACTIVITY_TITLE_TRUNCATED=".//*[@id='UIPreviewCommentArea']//p[text()='${textTruncated}']";
    public final By ELEMENT_COMMENT_AREA_TOP_COMMENT_ICON=By.xpath(".//*[@id='previewCommentLink']//i[@class='uiIconComment uiIconLightGray']");
    public final By ELEMENT_COMMENT_AREA_TOP_LIKE_ICON = By.xpath(".//*[@id='UIPreviewCommentArea']//*[contains(@class,'uiIconThumbUp')]");
    public final By ELEMENT_COMMENT_AREA_TOP_LIKE_ICON_BLUE= By.xpath(".//*[@id='UIPreviewCommentArea']//i[@class='uiIconThumbUp uiIconBlue']");
    public final String ELEMENT_COMMENT_AREA_TOP_LIKE_NUMBER = "//*[contains(@id,'UIPreviewCommentArea')]//*[contains(@href,'javascript:void(0);')][contains(.,'${number}')]";
	
	/**
	 * Comment area
	 */
	public final By ELEMENT_COMMENT_AREA= By.xpath(".//*[@class='uiBox commentArea pull-right']");
	public final By ELEMENT_COMMENT_COLLAPSE_ICON = By.xpath(".//*[contains(@id,'UIDocumentPreview')]//*[contains(@class,'uiIconMiniArrowRight')]"); 
	public final By ELEMENT_COMMENT_EXPAND_ICON = By.xpath(".//*[contains(@id,'UIDocumentPreview')]//*[contains(@class,'uiIconMiniArrowLeft')]"); 
	public final By ELEMENT_COMMENT_DOCUMENT_ICON = By.xpath(".//*[contains(@id,'UIDocumentPreview')]//*[contains(@class,'uiIcon16x16applicationpdf')]");
	public final By ELEMENT_COMMENT_DOCUMENT_NAME = By.xpath(".//*[@id='UIDocumentPreview']//*[@class='title']");
	public final By ELEMENT_COMMENT_INPUT_AVATAR = By.xpath(".//*[@class='commentInputBox']//*[@class='avatarXSmall pull-left']");
	public final By ELEEMNT_COMMENT_LIST_AVATAR=By.xpath(".//*[@class='commentList']//*[@class='avatarXSmall pull-left']");
	public final By ELEMENT_COMMENT_INPUT_FIELD = By.xpath(".//*[@id='commentTextAreaPreview']");
	public final By ELEMENT_COMMENT_INPUT_PLACEHOLDER= By.xpath(".//*[@placeholder='Add your comment...']");
	public final By ELEMENT_COMMENT_BOX = By.xpath(".//*[@id='commentTextAreaPreview']");
	public final String ELEMENT_COMMENT_CONTENT = ".//*[@class='cont'][contains(text(),'${text}')]";
	public final By ELEMENT_COMMENT_EMPTY = By.xpath(".//*[text()='No comment yet']");
	public final By ELEMENT_COMMENT_LIST = By.xpath(".//*[@id='UIPreviewCommentArea']//ul[@class='commentList']");
	public final String ELEMENT_COMMENT_DELETE_ICON="i.uiIconLightGray.uiIconClose";
    public final By ELEMENT_COMMENT_TEXT_AREA = By.xpath(".//*[@class='cont']");
}