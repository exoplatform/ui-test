package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;

public class HomePageActivity extends PlatformBase{

	Dialog dialog;
	ForumManagePost post;

	public final By ELEMENT_MESSAGE_CONFIRM_DELETE_ACTIVITY = By.xpath("//*[text()='Are you sure you want to delete this activity?']");
	public final String ELEMENT_ACTIVITY_AUTHOR_ACTIVITY = "//*[contains(text(), '${activityText}')]/../../../../..//*[@class='author']";
	public final String ELEMENT_ACTIVITY_DELETE = "//*[contains(text(),'${activityText}')]/../../../../..//a[contains(@id, 'DeleteActivityButton')]";
	public final By ELEMENT_LOADMORE = By.xpath("//div[@class='boxLoadMore']");
	public final String ELEMENT_SPECIFIC_PANEL = "//*[@id='UISpaceNavigationPortlet']//*[contains(text(),'{$space}')]";
	
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
	public final By ELEMENT_COMPOSER_SHARE_BUTTON = By.xpath(".//*[@id='ShareButton']");
	public final By ELEMENT_ACTIVITY_WHAT_ARE_YOU_WORKING_LABEL = By.xpath("//div[@id='DisplaycomposerInput']/../div[@class='placeholder']");
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_UPLOAD_BUTTON = By.xpath(".//input[@type='file']");	
	public final String ELEMENT_PUBLICATION_SUGGEST_USER = ".//*[@id='UIComposer']//*[contains(@data-ref-id,'${name}')]";	
	public final String ELEMENT_PUBLICATION_USER_SHARED = ".//*[@class='description']//*[contains(text(),'${name}')]";
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_PROGRESS_UPLOAD = By.xpath(".//*[@id='UIDocumentSelector']//*[@class='pull-left percent']");
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_CLOSE_BTN =By.xpath(".//*[@id='UIPopupComposer']//*[contains(@title,'Close Window')]");
	public final By ELEMENT_UPLOAD_FILE_FRAME_XPATH = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	
	//Upload popup
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP=By.xpath(".//*[@id='DriveTypeDropDown']/div[@class='btn dropdown-toggle']");
	public final String ELEMENT_ACTIVITY_UPLOAD_POPUP_NODE=".//*[@id='ListRecords']//a[@data-original-title='${nameNode}']";
	public final By ELEMENT_ACTIVITY_UPLOAD_POPUP_CLOSE=By.xpath(".//*[@id='UIPopupComposer']//*[@class='uiIconClose pull-right']");
	public final String ELEMENT_DRIVER_OPTION = "//a[@class='OptionItem' and contains(text(),'${driveName}')]";
	
	//like and unlike icon
	public final String ELEMENT_LIKE_ICON = "//*[contains(text(),'${activityText}')]/ancestor::div[@class='boxContainer']//*[starts-with(@id, 'LikeLink')]";
	public final String ELEMENT_UNLIKE_ICON = "//*[contains(text(),'${activityText}')]/ancestor::div[@class='boxContainer']//*[starts-with(@id, 'UnLikeLink')]";

	//activity layout
	public final String ELEMENT_ACTIVITY_AUTHOR_NAME = "//div[contains(@id,'activityContainer')][${index}]//div[@class='author']//a[text()='${author}']";
	public final String ELEMENT_ACTIVITY_AUTHOR_AVATAR = "//div[contains(@id,'activityContainer')][${index}]//div[@class='activityAvatar avatarCircle']//img[@alt='${author}']";

	public final By ELEMENT_ACTIVITY_TEXTBOX = By.id("DisplaycomposerInput");
	public final String ELEMENT_ACTIVITY = "//*[contains(text(),'${activityText}')]";
	public final String ELEMENT_DOWNLOAD_ICON = "//*[contains(text(),'${activityText}')]/../../../..//i[@class='uiIconDownload uiIconLightGray']"; 
	public final String ELEMENT_ACTIVITY_ORDER = "//div[contains(@id,'activityContainer')][${index}]//*[contains(text(),'${activityText}')]";

	//Comment box
	public final String ELEMENT_COMMENT_LINK = "//div[@class='text' or @class = 'description'or @class='linkSource' or contains(@id, 'ContextBox')]/*[contains(text(), '${activityText}')]//ancestor::div[contains(@id,'ActivityContextBox')]//*[starts-with(@id, 'CommentLink')]";
	public final String ELEMENT_ACTIVITY_COMMENT_CONTENT = "//*[contains(text(),'${title}')]/../../../..//*[@class='contentComment']/..//*[contains(text(), '${comment}')]";

	public final String ELEMENT_GET_COMMENT_CONTENT = "//*[contains(text(),'${title}')]/../../../..//*[@class='commentItem commentItemLast']/..//p";
	public final String ELEMENT_ACTIVITY_COMMENT_CONTENT_1 = "//*[text()='${title}']/ancestor::div[@class='boxContainer']//*[@class='contentComment']";
	public final String ELEMENT_COMMENTBOX="//*[text()='${title}']/../../../..//div[@class='exo-mentions']/div[contains(@id,'DisplayCommentTextarea')]";
	public final String ELEMENT_COMMENTBOX_PLF4_1="//*[text()='${title}']/../../../../..//div[@class='exo-mentions']/div[contains(@id,'DisplayCommentTextarea')]";
	public final String ELEMENT_ICON_COMMENT = "//*[contains(text(),'${title}')]/../../../..//i[@class='uiIconComment uiIconLightGray']";
	public final String ELEMENT_COMMENT_BLOCK = "//a[contains(text(),'${title}')]/../../../..//div[contains(@class,'commentItem commentItemLast')]";
	public final String ELEMENT_COMMENT_LAST = "//*[contains(text(),'${title}')]/../../../..//div[contains(@class,'commentItem commentItemLast')]//*[contains(text(), '${comment}')]";

	//Comment box for ECMS data type
	public final String ELEMENT_ACTIVITY_COMMENT_CONTENT_2 = "//*[@class='contentComment' and contains(text(),'${comment}')]";
	public final String ELEMENT_ACTIVITY_COMMENT_CONTENT_3 = "//*[@class='ContentBlock' and contains(text(),'${comment}')]";
	public final String ELEMENT_ACTIVITY_DELETE_COMMENT_ICON = "//*[@class='contentComment' and contains(text(), '${comment}')]/../..//*[contains(@id, 'DeleteCommentButton')]";
	public final String DATA_MESSAGE_CONFIRM_DELETE_COMMENT = "Are you sure you want to delete this comment?";

	//Content activity
	public final String ELEMENT_CONTENT_NAME = "//a[@title='@{fileName}']";
	public final String ELEMENT_CONTENT_NAME_41 = "//a[contains(text(),'@{fileName}')]";
	public final String ELEMENT_CONTENT_TYPE_ICON = "//a[@title='@{fileName}']/../..//*[@class='${icon}']";
	public final String ELEMENT_CONTENT_TYPE_ICON_41 = "//a[@data-original-title='@{fileName}']/../..//*[@class='${icon}']";
	public final String ELEMENT_CONTENT_TYPE = "//a[@title='@{fileName}']/..//*[@class='versionFile' and contains(text(), '${type}')]";
	public final String ELEMENT_CONTENT_TYPE_PLF41 = "//*[contains(text(),'${activityText}')]";
	public final String ELEMENT_CONTENT_DESCRIPTION = "//a[@title='@{fileName}']/..//*[@class='descriptionText' and text()='${des}']";
	public final String ELEMENT_CONTENT_DESCRIPTION_41 = "//a[@data-original-title='@{fileName}']/..//*[@class='descriptionText' and text()='${des}']";
	public final String ELEMENT_CONTENT_VERSION = "//a[@title='@{fileName}']/..//*[contains(text(), '${version} -')]";
	public final String ELEMENT_CONTENT_VERSION_41 = "//a[@data-original-title='@{fileName}']/..//*[contains(text(), '${version} -')]";
	public final String ELEMENT_CONTENT_STATUS = "//a[@title='@{fileName}']/..//*[contains(text(), '${status}')]";
	public final String ELEMENT_CONTENT_STATUS_41 = "//a[@data-original-title='@{fileName}']/..//*[contains(text(), '${status}')]";
	public final String ELEMENT_CONTENT_SUMMARY = "//*[@title='@{fileName}']/..//p[1 or 2]";
	public final String ELEMENT_CONTENT_SUMMARY_41 = "//*[@data-original-title='@{fileName}']/..//p[1 or 2]";
	public final String ELEMENT_CONTENT_SUMMARY_WEBCONTENT = "//*[@title='@{fileName}']/../../div[@class='text']//p[${index}]";
	public final String eLEMENT_CONTENT_SUMMARY_WEBCONTENT_41 = "//*[contains(text(),'@{fileName}')]/../../div[@class='text']//p[${index}]";
	public final String ELEMENT_ACTIVITY_DOCUMENT_MEDIA_TITLE = ".//*[@class='linkTitle'][@data-original-title='${title}']";
	public final String ELEMENT_CONTENT_COMMENT_EDIT_TITLE = "//*[contains(text(), '@{fileName}')]";
	public final String ELEMENT_CONTENT = "//a[@title='@{fileName}']/..//*[contains(text(), '${text}')]";
	public final String ELEMENT_CONTENT_ACTIVITY_INDEX = "//div[contains(@id,'UIActivitiesContainer')]/div[contains(@id,'activityContainer')][${index}]//*[contains(text(),'${content}')]";

	public final String ELEMENT_CONTENT_COMMENT_ADD_A_TAG = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Tag: ${tags} has been added.']";
	public final String ELEMENT_CONTENT_COMMENT_ADD_TAGS = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Tags: ${tags} have been added.']";
	public final String ELEMENT_CONTENT_COMMENT_REMOVE_A_TAG = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Tag: ${tags} has been removed.']";
	public final String ELEMENT_CONTENT_COMMENT_REMOVE_TAGS = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Tags: ${tags} have been removed.']";
	public final String ELEMENT_CONTENT_COMMENT_PUBLISH = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Publication has been published.']";
	public final String ELEMENT_CONTENT_COMMENT_RESTORE_VERSION = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Publication has been restored to version: ${version}']";
	public final String ELEMENT_CONTENT_COMMENT_PUBLISH_1 = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Document has been published.']";
	public final String ELEMENT_CONTENT_EDIT_LINK = "//a[@title='@{fileName}']/../../../..//*[@class='uiIconEdit uiIconLightGray']";
	public final String ELEMENT_CONTENT_EDIT_LINK_41 = "//a[@data-original-title='@{fileName}']/../../../..//*[@class='uiIconEdit uiIconLightGray']";
	public final String ELEMENT_CONTENT_VIEW_LINK = "//a[@title='@{fileName}']/../../../..//*[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_CONTENT_VIEW_LINK_41 = "//a[@data-original-title='@{fileName}']/../../../..//*[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_CONTENT_VIEW_LINK_PLF41 = "//*[contains(text(),'${activityText}')]/../../../..//*[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_CONTENT_EDIT_LINK_PLF41 = "//*[contains(text(),'${activityText}')]/../../../..//*[@class='uiIconEdit uiIconLightGray']"; 
	public final String ELEMENT_CONTENT_COMMENT_MOVING = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Publication has been moved to: ${path}']";
	public final String ELEMENT_CONTENT_COMMENT_MOVING_41 = "//*[@data-original-title='@{fileName}' or @title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Publication has been moved to: ${path}']";
	public final String ELEMENT_CONTENT_COMMENT_RENAME_SPACE = "//div[@class='author']/a[contains(text(), '${spacename}')]//ancestor::div[contains(@id,'ContextBox')]/div[contains(@id,'CommentBlockBound')]//p[contains(text(),'Name has been updated to: ${spacename}.')]";
	public final String ELEMENT_CONTENT_COMMENT_USER_JOIN_SPACE="//div[@class='author']/a[contains(text(), '${spacename}')]//ancestor::div[contains(@id,'ContextBox')]/div[contains(@id,'CommentBlockBound')]//div[@class='author']/a[text()='${username}']/../../p[contains(text(),'Has joined the space')]";
	public final String ELEMENT_CONTENT_COMMENT_USER_CHANGE_AVATAR="//div[@class='author']/a[contains(text(), '${username}')]//ancestor::div[contains(@id,'ContextBox')]/div[contains(@id,'CommentBlockBound')]//div[@class='author']/a[text()='${username}']/../../p[contains(text(),'Avatar has been updated.')]";
	public final String ELEMENT_CONTENT_COMMENT_USER_EDIT_BASIC_INFO="//div[@class='author']/a[contains(text(), '${username}')]//ancestor::div[contains(@id,'ContextBox')]/div[contains(@id,'CommentBlockBound')]//div[@class='author']/a[text()='${username}']/../../p[contains(text(),'Basic information has been updated.')]";
	

	//File activity
	public final String ELEMENT_FILE_SIZE = "//a[@data-original-title='@{fileName}']/..//*[@class='versionFile' and contains(text(), '${size}')]";
	public final String ELEMENT_FILE_SIZE_41 = "//a[@data-original-title='@{fileName}']/..//*[@class='versionFile' and contains(text(), '${size}')]";
	public final String ELEMENT_FILE_COMMENT_ADD_CATEGORY = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Category: ${category} has been added.']";
	public final String ELEMENT_FILE_COMMENT_ADD_CATEGORY_41 = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Category: ${category} has been added.']";
	public final String ELEMENT_FILE_COMMENT_REMOVE_CATEGORY = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='Category: ${category} has been removed.']";
	public final By ELEMENT_FILE_VIEW_POPUP = By.id("UISocialPopupWindow");
	public final String ELEMENT_FILE_VIEW_NAME = "//*[@id='UISocialPopupWindow']//*[text()='${fileName}']";
	public final String ELEMENT_FILE_COMMENT_MOVING = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='File has been moved to: ${path}']";
	public final String ELEMENT_FILE_COMMENT_MOVING_41 = "//*[@data-original-title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='File has been moved to: ${path}']";
	public final String ELEMENT_FILE_EDIT = "//*[contains(text(),'${title}')]/../../../..//*[@class='uiIconEdit uiIconLightGray']";
	public final String ELEMENT_FILE_VIEW = "//*[contains(text(),'${title}')]/../../../..//*[@class='uiIconWatch uiIconLightGray']";
	public final String ELEMENT_FILE_CHECKCOMMENTRIGHT = "//*[contains(text(),'${title}')]/../../../..//*[@style='text-align: right;']";
	public final String ELEMENT_FILE_PREVIEW = ".//*[@data-original-title='{$file}']/img";
	public final By ELEMENT_FILE_CHECK_TOOLBAR_VIEW =By.xpath(".//*[@id='DocViewer_PDF_']//*[@class='PDFViewerBar clearfix']");
	public final By ELEMENT_FILE_DOMWLOAD_TOOLBAR_VIEW = By.xpath(".//*[@id='UISocialPopupWindow']//*[@class='uiIconDownload']");
	public final By ELEMENT_FILE_VIEW_CLOSE = By.xpath(".//*[@id='UISocialPopupWindow']//*[@class='uiIconClose pull-right']");
	public final String ELEMENT_FILE_DOWNLOAD_BTN="//*[contains(@data-original-title,'${file}')]/../../../..//*[contains(@class,'uiIconDownload')]";
	public final String ELEMENT_FILE_COMMENT_ICON="//*[@data-original-title='${file}']/../../../..//*[contains(@class,'uiIconComment')]";
	public final String ELEMENT_FILE_LIKE_ICON="//*[@data-original-title='${file}']/../../../..//*[contains(@class,'uiIconThumbUp')]";
	
	//Attach file
	public final String ELEMENT_FILE_COMMENT_ADD_ATTACH_FILE = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='File(s) has been attached.']";
	public final String ELEMENT_FILE_COMMENT_REMOVE_ATTACH_FILE = "//*[@title='@{fileName}']/../../../..//*[@class='commentBox']//*[text()='File(s) has been removed.']";
	public final By ELEMENT_UPLOAD_PROGRESS_BAR = By.xpath(".//*[contains(@class,'progress-striped')]");
	//Edit screen from click Edit in activity
	public final By ELEMENT_CONTENT_EDIT_SCREEN_FROM_ACTIVITY = By.id("UIJCRExplorerPortlet");
	public final By ELEMENT_CONTENT_EDIT_SCREEN_BACK = By.xpath("//a[@data-original-title='Back']");

	//Wiki activity
	public final String ELEMENT_WIKI_COMMENT_EDIT_TITLE = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'title has been updated to: ${title}')]";
	public final String ELEMENT_WIKI_COMMENT_EDIT_CONTENT = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'content has been edited')]";
	public final String ELEMENT_ACTIVITY_WIKI_TITLE = "//*[@class='linkTitle' and text()='${title}']";
	public final String ELEMENT_ACTIVITY_WIKI_CONTENT = "//*[@class='linkTitle' and text()='${title}']/../../..//*[@class='contentWiki theContent']/*[@class='text']";
	public final String ELEMENT_ACTIVITY_WIKI_CONTENT_TEXT="//*[@class='linkTitle' and text()='$title']/../../..//*[contains(text(),'$content')]";
	public final String ELEMENT_ACTIVITY_WIKI_VERSION = "//*[@class='linkTitle' and text()='${title}']/../..//*[@class = 'pull-right versionLabel' and contains(text(), 'Version: ${version}')]";
	public final String ELEMENT_ACTIVITY_MOVE_WIKI_PAGE = "//*[text()='${title}']/../../../..//*[@class='contentComment' and contains(text(), 'Page has been moved to: ${path}')]";
    public final String ELEMENT_ACTIVITY_WIKI_COMMENT=".//*[contains(@class,'linkTitle')][contains(text(),'$title')]/../../../..//*[contains(text(),\"$comment\")]";
    public final String ELEMENT_CONTENT_WIKI_VIEW_CHANGE ="//*[@class='linkTitle' and text()='$title']/../../../..//*[@class='uiIconViewChange uiIconLightGray']";
    
	//Answer activity
	public final String ELEMENT_QUESTION_CONTENT = "//a[text()='${title}']/../../..//div[@class='contentAnswer theContent']//p";
	public final String ELEMENT_QUESTION_NUM_ANSWER = "//a[contains(text(),'${title}')]/../../../..//div[@class='contentAnswer theContent']//span[contains(text(),'${number} Answer')]";
	public final String ELEMENT_QUESTION_NUM_COMMENT = "//a[contains(text(),'${title}')]/../../../..//div[@class='contentAnswer theContent']//span[contains(text(),'${number} Comment')]";
	public final String ELEMENT_QUESTION_COMMENT = "//a[contains(text(),'${title}')]/../../../..//div[@class='commentList']//*[contains(text(),'${comment}')]";

	public final String ELEMENT_QUESTION_RATE = "//a[@class='linkTitle' and text()='${title}']/../..//div[@class='avgRatingImages sumaryRate']/i[@class='voted'][${rate}]";
	public final String ELEMENT_QUESTION_UNRATE = "//a[@class='linkTitle' and text()='${title}']/../..//div[@class='avgRatingImages sumaryRate']/i[@class='unvoted'][${rate}]";
	public final String ELEMENT_QUESTION_HAFT_RATE = "//a[@class='linkTitle' and text()='${title}']/../..//div[@data-original-title='Average']/i[@class='votedHaft']";
	public final String ELEMENT_QUESTION_VIEW_COMMENT = "//a[text()='${title}']/../../../..//div[@class='commentListInfo clearfix']/a";
	public final String MSG_ANSWER_QUESTION = "Answer has been submitted: ${answer}";
	public final String MSG_ANSWER_EDIT = "Answer has been edited to: ${answer}";
	public final String ELEMENT_ANSWER_ACTIVITY_LINK = "//a[text()='${title}']/../../../..//a[contains(@id,'AnswerLink')]";
	public final String ELEMENT_COMMENT_LABEL = "//*[text()='${title}']/../../../..//*[contains(@id,'DisplayCommentTextarea')]/../div[@class='placeholder']";
	public final String ELEMENT_COMMENT_BUTTON = "//*[text()='${title}']/../../../..//button[contains(@id,'CommentButton')]";
	public final String MSG_ANSWER_APPROVE = "Answer has been approved: ${answer}"; 
	public final String MSG_ANSWER_DISAPPROVE = "Answer has been unapproved: ${answer}";
	public final String MSG_QUESTION_ADD_LANGUAGE = "Question has been added in: ${language}";
	public final String MSG_QUESTION_ACTIVATE = "Question has been activated.";
	public final String MSG_QUESTION_UNACTIVATE = "Question has been unactivated.";
	public final String MSG_ANSWER_UNACTIVATE = "Answer has been unactivated: ${answer}";
	public final String MSG_ANSWER_ACTIVATE = "Answer has been activated: ${answer}";
	public final String MSG_QUESTION_ADD_ATTACHMENT = "Attachment(s) has been added.";
	public final String MSG_QUESTION_UPDATE_DESCRIPTON = "Detail has been updated to: ${description}";
	public final String MSG_PROMOTE_COMMENT = "Comment ${comment} has been promoted as an answer";

	//Forum activity
	public final String ELEMENT_FORUM_ACT_CONTENT = "//a[text()='${title}']/../../..//div[@class='contentForum theContent']//p";
	public final String ELEMENT_FORUM_NUMBER_REPLY = "//a[text()='${title}']/../../..//div[@class='contentForum theContent']/span[text()='${number} Replies']";
	public final String ELEMENT_FORUM_ONE_REPLY = "//a[text()='${title}']/../../..//div[@class='contentForum theContent']/span[text()='${number} reply']";
	public final String ELEMENT_TOPIC_RATE = "//a[@class='textBold linkTitle' and text()='${title}']/../..//div[@class='avgRatingImages sumaryRate']/i[@class='voted'][${rate}]";
	public final String ELEMENT_TOPIC_HAFT_RATE = "//a[@class='textBold linkTitle' and text()='${title}']/../..//div[@data-original-title='Average']/i[@class='votedHaft']";
	public final String ELEMENT_TOPIC_REPLY = "//a[contains(text(),'${title}')]/../../../..//i[@class='uiIconReply uiIconLightGray']";
	public final String ELEMENT_TOPIC_LAST_REPLY = "//a[contains(text(),'${title}')]/../../../..//i[@class='uiIconSocLastestReply uiIconSocLightGray']";
	public final String ELEMENT_REPLY_VIEW = "//*[contains(text(), '${comment}')]/../..//*[@class='viewComment']";
	public final String ELEMENT_TOPIC_COMMENT = "//*[contains(text(),'${title}')]/../../../..//*[@class='contentComment' and contains(text(), '${comment}')]";
	public final String ELEMENT_TOPIC_TITLE=".//*[@class='titleForum']//..[contains(text(),'${nameTopic}')]";
	//Poll activity
	public final String ELEMENT_POLL_ACTIVITY = "//div[@class='uiBox roundedBottom introBox pollShare']//a[contains(text(),'${poll}')]";
	public final String ELEMENT_VOTE = "//div[@class='uiBox roundedBottom introBox pollShare']//a[contains(text(),'${poll}')]/../../..//span[@class='text-vote' and contains(text(),'${vote}')]";
	public final String ELEMENT_VOTE_PROGRESSBAR = "//div[@class='uiBox roundedBottom introBox pollShare']//a[contains(text(),'${poll}')]/../../..//span[@class='text-vote' and contains(text(),'${vote}')]/../..//div[@class='progressBar']";
	public final String ELEMENT_VOTE_RATE = "//div[@class='uiBox roundedBottom introBox pollShare']//a[contains(text(),'${poll}')]/../../..//span[@class='text-vote' and contains(text(),'${vote}')]/../..//div[@class='progressing' and contains(text(),'(${rate} vote)')]";
	public final String ELEMENT_POLL_VOTE = "//a[contains(text(),'${poll}')]/../../../..//i[@class='uiIconSocVote uiIconSocLightGray']";
	public final String ELEMENT_VOTE_TOTAL = "//div[@class='uiBox roundedBottom introBox pollShare']//a[contains(text(),'${poll}')]/../..//*[@class='numberVoted']";

	//Calendar activity
	public final String ELEMENT_EVENT_MONTH_ICON = "//a[text()='${event}']/../..//div[@class='pull-left calendarBox']/div[@class='heading' and contains(text(),'${month}')]";
	public final String ELEMENT_EVENT_DATE_ICON = "//a[text()='${event}']/../..//div[@class='pull-left calendarBox']/div[@class='content' and contains(text(),'${date}')]";
	public final String MSG_EVENT_COMMENT_UPDATE_ALL_DAY = "Event is now an all-day event.";
	public final String MSG_EVENT_COMMENT_UPDATE_SUMMARY = "Title has been updated to: ${newTitle}.";
	public final String MSG_EVENT_COMMENT_UPDATE_DESC = "Description has been updated to: ${description}.";
	public final String MSG_EVENT_COMMENT_UPDATE_LOCATION = "Location has been updated to: ${location}.";
	public final String MSG_TASK_COMMENT_UPDATE_ATTACHMENT = "Attachment(s) has been added to the task.";
	public final String MSG_TASK_COMMENT_UPDATE_NOTE = "Note has been updated to: ${note}.";
	public final String MSG_TASK_COMMENT_UPDATE_STATUS = "Task has been completed.";
	public final String MSG_EVENT_COMMENT_DELETE_EDITED_EVENT = "Event cancelled for ${date}";
	public final String MSG_EVENT_REPEAT_EVERYDAY = "Event will be repeated every day.";
	public final String ELEMENT_EVENT_ACTIVITY_DATE_TIME_INFO = "//div[${index}][contains(@id,'activityContainer')]//*[contains(@id,'ActivityContextBox')]//*[@class='text']/*[@class='dateTime']";
	public final String ELEMENT_TASK_COMPLETED_ICON = "//*[contains(text(),'${title}')]/../../..//i[@class='uiIconStatus-completed']";

	public final By ELEMENT_DELETE_ICON = By.xpath(".//*[@class='uiIconDelete uiIconLightGray']");
	public final By ELEMENT_SELECT_FILE = By.xpath("//*[@class='btn' and contains(text(),'Select File')]");
	public final By ELEMENT_UPLOAD_LINK = By.id("MultiUploadInputFiles");
	
	public HomePageActivity(WebDriver dr, String...plfVersion){
		driver = dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		post = new ForumManagePost(driver);
		button = new Button(driver);
	}
	/** function check info in activity of a content/file
	 * @author lientm
	 * @param name
	 * @param iconType
	 * @param contentType
	 * @param size
	 * @param content
	 * @param version
	 * @param des
	 * @param status
	 */
	public void checkInforAfterAddingDocument(String name, String iconType, String contentType, String size, String content, 
			String version, String des, String status){
		info("Check name of content");
		if(waitForAndGetElement(ELEMENT_CONTENT_NAME.replace("@{fileName}", name), 5000, 0,2) == null)
			waitForAndGetElement(ELEMENT_CONTENT_NAME_41.replace("@{fileName}", name));
		if (iconType != ""){
			info("Check icon content type");
			if(waitForAndGetElement(ELEMENT_CONTENT_TYPE_ICON.replace("@{fileName}", name).replace("${icon}", iconType), 10000, 0,2) == null)
				waitForAndGetElement(ELEMENT_CONTENT_TYPE_ICON_41.replace("@{fileName}", name).replace("${icon}", iconType));
		}
		if (contentType != ""){
			info("Check content type");
			if(waitForAndGetElement(ELEMENT_CONTENT_TYPE.replace("@{fileName}", name).replace("${type}", contentType), 5000, 0,2) == null)
				waitForAndGetElement(ELEMENT_CONTENT_TYPE_PLF41.replace("${activityText}", name).replace("${type}", contentType));
		}
		if (content != ""){
			info("Check content summary");
			String[] sum = null;
			if(waitForAndGetElement(ELEMENT_CONTENT_SUMMARY.replace("@{fileName}", name),5000,0,2)!=null)
				sum = getText(ELEMENT_CONTENT_SUMMARY.replace("@{fileName}", name)).split("\n");
			else //if(waitForAndGetElement(ELEMENT_CONTENT_SUMMARY_41.replace("@{fileName}", name),5000,0,2)!=null)
				sum = getText(ELEMENT_CONTENT_SUMMARY_41.replace("@{fileName}", name)).split("\n");

			String[] cont = content.split("/");
			if(sum.length>2){
				if (cont.length > 4){
					assert sum[4].equalsIgnoreCase("...");
					for (int i = 0; i < 4; i++){
						assert sum[i].equalsIgnoreCase(cont[i]);
					}
				}else {
					for (int i = 0; i < cont.length; i ++){
						assert sum[i].equalsIgnoreCase(cont[i]);
					}
				}
			}
			else{
				if (cont.length > 4){
					assert getText(ELEMENT_CONTENT_SUMMARY_WEBCONTENT.replace("@{fileName}", name).replace("${index}", "6")).equalsIgnoreCase("...");
					int contentIndex = 2;
					for (int i = 0; i < 4; i++){
						assert getText(ELEMENT_CONTENT_SUMMARY_WEBCONTENT.replace("@{fileName}", name).replace("${index}", String.valueOf(contentIndex))).equalsIgnoreCase(cont[i]);
						contentIndex++;
					}
				}else {
					int contentIndex = 1;
					for (int i = 0; i < cont.length; i ++){
						if(this.plfVersion.equalsIgnoreCase("4.0")){
							info("check on 4.0 home");
							assert getText(ELEMENT_CONTENT_SUMMARY_WEBCONTENT.replace("@{fileName}", name).replace("${index}", String.valueOf(contentIndex))).equalsIgnoreCase(cont[i]);
							
						}else{
							info("check on 4.1 home");//if(waitForAndGetElement(eLEMENT_CONTENT_SUMMARY_WEBCONTENT_41.replace("@{fileName}", name).replace("${index}", String.valueOf(contentIndex)),5000,0,2)!=null)
						
							assert getText(eLEMENT_CONTENT_SUMMARY_WEBCONTENT_41.replace("@{fileName}", name).replace("${index}", String.valueOf(contentIndex))).equalsIgnoreCase(cont[i]);
						}
							contentIndex++;
					}
				}
			}
		}
		if (size != ""){
			info("Check content size");
			if(waitForAndGetElement(ELEMENT_FILE_SIZE.replace("@{fileName}", name).replace("${size}", size), 5000, 0,2) == null)
				waitForAndGetElement(ELEMENT_FILE_SIZE_41.replace("@{fileName}", name).replace("${size}", size));
		}
		if (version != ""){
			info("Check content version");
			if(waitForAndGetElement(ELEMENT_CONTENT_VERSION.replace("@{fileName}", name).replace("${version}", version), 5000, 0,2) == null)
				waitForAndGetElement(ELEMENT_CONTENT_VERSION_41.replace("@{fileName}", name).replace("${version}", version));
		}
		if (des != ""){
			info("Check content description");
			if(waitForAndGetElement(ELEMENT_CONTENT_DESCRIPTION.replace("@{fileName}", name).replace("${des}", des), 5000, 0,2) == null)
				waitForAndGetElement(ELEMENT_CONTENT_DESCRIPTION_41.replace("@{fileName}", name).replace("${des}", des));
		}
		if (status != ""){
			info("Check content status");
			if(waitForAndGetElement(ELEMENT_CONTENT_STATUS.replace("@{fileName}", name).replace("${status}", status), 5000, 0,2) == null)
				waitForAndGetElement(ELEMENT_CONTENT_STATUS_41.replace("@{fileName}", name).replace("${status}", status));
		}
	}

	/**Function check add comment in activity after editing title of a file/content
	 * @author lientm
	 * @param name
	 * @param titleEdit
	 */
	public void checkTitleAfterEditing(String name, String titleEdit){
		waitForAndGetElement(ELEMENT_CONTENT_COMMENT_EDIT_TITLE.replace("@{fileName}", name).replace("${title}", titleEdit));
	}

	/**Function check add comment in activity after editing title of a question
	 * @author Thuntn
	 * @param name
	 * @param titleEdit
	 */
	public void checkTitleAfterEditQuestion(String newName){
		waitForAndGetElement(ELEMENT_CONTENT_COMMENT_EDIT_TITLE.replace("@{fileName}", newName).replace("${title}", newName));
	}

	/** function check add comment in activity after adding tags for a content/file
	 * @author lientm
	 * @param name
	 * @param tags
	 * @param number
	 */
	public void checkTagAfterAddingToContent(String name, String tags, int number){
		if (number == 1){
			waitForAndGetElement(ELEMENT_CONTENT_COMMENT_ADD_A_TAG.replace("@{fileName}", name).replace("${tags}", tags));
		}else {
			waitForAndGetElement(ELEMENT_CONTENT_COMMENT_ADD_TAGS.replace("@{fileName}", name).replace("${tags}", tags));
		}
	}

	/** function check add comment in activity after removing tags for a content/file
	 * @author phuongdt
	 * @param name
	 * @param tags
	 * @param number
	 */
	public void checkTagAfterRemovingToContent(String name, String tags, int number){
		if (number == 1){
			waitForAndGetElement(ELEMENT_CONTENT_COMMENT_REMOVE_A_TAG.replace("@{fileName}", name).replace("${tags}", tags));
		}else {
			waitForAndGetElement(ELEMENT_CONTENT_COMMENT_REMOVE_TAGS.replace("@{fileName}", name).replace("${tags}", tags));
		}
	}

	/** function check comment of content in activity after adding tags for a content/file
	 * @author phuongdt
	 * @param commenttext
	 * @param name
	 */
	public void checkCommentOfContentAfterAddingToContent(String name, String contenttext){
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", name).replace("${comment}", contenttext));
	}

	/** function check comment of content in activity after removing tags for a content/file
	 * @author phuongdt
	 * @param commenttext
	 * @param name
	 */
	public void checkCommentOfContentAfterRemovingToContent(String name, String contenttext){
		waitForElementNotPresent(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", name).replace("${comment}", contenttext));
	}

	/** function check add comment in activity after publishing a content/file
	 * @author lientm
	 * @param name
	 */
	public void checkStatusAfterPublishAContent(String name){

		if (waitForAndGetElement(ELEMENT_CONTENT_COMMENT_PUBLISH.replace("@{fileName}", name), 5000, 0) != null){
			info("Element " + ELEMENT_CONTENT_COMMENT_PUBLISH.replace("@{fileName}", name) + " is displayed");
		}else{
			waitForAndGetElement(ELEMENT_CONTENT_COMMENT_PUBLISH_1.replace("@{fileName}", name));
		}
		info("Check status after publishing a content: Successful");
	}

	/** function check add comment in activity after adding new category for a content/file
	 * @author lientm
	 * @param name
	 * @param category
	 */
	public void checkCategoryAfterAddingToContent(String name, String category){
		if(this.plfVersion.equalsIgnoreCase("4.0"))
			waitForAndGetElement(ELEMENT_FILE_COMMENT_ADD_CATEGORY.replace("@{fileName}", name).replace("${category}", category));
		else if(this.plfVersion.equalsIgnoreCase("4.1"))
			waitForAndGetElement(ELEMENT_FILE_COMMENT_ADD_CATEGORY_41.replace("@{fileName}", name).replace("${category}", category));
	}

	/** function check add comment in activity after remove category for a content/file
	 * @author phuongdt
	 * @param name
	 * @param category
	 */
	public void checkCategoryAfterRemovingToContent(String name, String category){
		waitForAndGetElement(ELEMENT_FILE_COMMENT_REMOVE_CATEGORY.replace("@{fileName}", name).replace("${category}", category));
	}

	/** function check add comment in activity after attach a new file for a content/file
	 * @author phuongdt
	 * @param name
	 */
	public void checkAttachFileAfterAddingToContent(String name){
		waitForAndGetElement(ELEMENT_FILE_COMMENT_ADD_ATTACH_FILE.replace("@{fileName}", name));
	}

	/** function check add comment in activity after remove category for a content/file
	 * @author phuongdt
	 * @param name
	 */
	public void checkAttachFileAfterRemovingToContent(String name){
		waitForAndGetElement(ELEMENT_FILE_COMMENT_REMOVE_ATTACH_FILE.replace("@{fileName}", name));
	}

	/** function check add comment in activity after remove category for a content/file
	 * @author phuongdt
	 * @param name
	 */
	public void checkRestoreVersionAfterRestoringToContent(String name, String version){
		waitForAndGetElement(ELEMENT_CONTENT_COMMENT_RESTORE_VERSION.replace("@{fileName}", name).replace("${version}", version));
	}

	/**function check go to edit screen after clicking Edit icon in activity
	 * @author lientm
	 * @param name
	 */
	public void goToEditFromContentActivity(String name){
		button = new Button(driver);
		if(waitForAndGetElement(ELEMENT_CONTENT_EDIT_LINK.replace("@{fileName}", name), 5000,0,2)!=null)
			click(ELEMENT_CONTENT_EDIT_LINK.replace("@{fileName}", name));
		else 
			click(ELEMENT_CONTENT_EDIT_LINK_41.replace("@{fileName}", name));
		waitForAndGetElement(ELEMENT_CONTENT_EDIT_SCREEN_FROM_ACTIVITY);
		waitForAndGetElement(button.ELEMENT_SAVE_CLOSE_BUTTON);
	}

	/**
	 * Delete and replace the old content by a new one
	 * @param newContentName
	 */

	public void editContent(String newContentName, Object...params){
		
		click(ELEMENT_DELETE_ICON);
		//click(ELEMENT_SELECT_FILE);
		
		Boolean verify = (Boolean) (params.length > 0 ? params[0] : true);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility = 'block'; arguments[0].style.height = '1px'; " +
				"arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", waitForAndGetElement(ELEMENT_UPLOAD_LINK, DEFAULT_TIMEOUT, 1, 2));
		
		driver.findElement(ELEMENT_UPLOAD_LINK).sendKeys(Utils.getAbsoluteFilePath(newContentName));
		info("Upload file " + Utils.getAbsoluteFilePath(newContentName));
		switchToParentWindow();
		waitForElementNotPresent(ELEMENT_UPLOAD_PROGRESS_BAR,3000,1);
		
		Utils.pause(5000);
		if (verify){
			String links[] = newContentName.split("/");
			int length = links.length;
			Utils.pause(2000);
			waitForAndGetElement(By.xpath("//*[contains(text(),'" + links[length-1]+ "')]"));
		}

		info("Upload file successfully");
		Utils.pause(2000);
		click(button.ELEMENT_SAVE_CLOSE_BUTTON);
	}
	
	
	/** function back homepage from edit screen
	 * @author lientm
	 */
	public void backToHomePageFromEditContentScreen(){
		click(ELEMENT_CONTENT_EDIT_SCREEN_BACK);
		waitForElementNotPresent(ELEMENT_CONTENT_EDIT_SCREEN_BACK);
	}

	/**function check go to view screen after clicking View icon in activity of a content
	 * @author lientm
	 * @param name
	 */
	public void goToViewFromContentActivity(String name){
		click(ELEMENT_CONTENT_VIEW_LINK.replace("@{fileName}", name));
		waitForAndGetElement(ELEMENT_CONTENT_EDIT_SCREEN_FROM_ACTIVITY);
		assert getValue(By.id("address")).contains(name);
	}

	/**function check go to view screen after clicking View icon in activity of a file
	 * @author lientm
	 * @param name
	 */
	public void goToViewFromFileActivity(String name){
		dialog = new Dialog(driver);
		if(waitForAndGetElement(ELEMENT_CONTENT_VIEW_LINK.replace("@{fileName}", name), 5000,0,2)!=null)
			click(ELEMENT_CONTENT_VIEW_LINK.replace("@{fileName}", name));
		else //if(waitForAndGetElement(ELEMENT_CONTENT_VIEW_LINK_41.replace("@{fileName}", name), 5000,0,2)!=null)
			click(ELEMENT_CONTENT_VIEW_LINK_41.replace("@{fileName}", name));
		waitForAndGetElement(ELEMENT_FILE_VIEW_POPUP);
		waitForAndGetElement(ELEMENT_FILE_VIEW_NAME.replace("${fileName}", name));
		dialog.closeMessageDialog();
	}

	/** function check add comment in activity after moving a conent/file
	 * @author lientm
	 * @param name
	 * @param path
	 * @param content
	 */
	public void checkContentAfterMovingANode(String name, String path, boolean content){
		if (content){
			String e40 = ELEMENT_CONTENT_COMMENT_MOVING.replace("@{fileName}", name).replace("${path}", path + name);
			String e41 = ELEMENT_CONTENT_COMMENT_MOVING_41.replace("@{fileName}", name).replace("${path}", path + name);
			if(this.plfVersion.equalsIgnoreCase("4.0"))
				waitForAndGetElement(e40);
			else// if(this.plfVersion.equalsIgnoreCase("4.1"))
				waitForAndGetElement(e41);
		}else {
			String e40 = ELEMENT_FILE_COMMENT_MOVING.replace("@{fileName}", name).replace("${path}", path + name);
			String e41 = ELEMENT_FILE_COMMENT_MOVING_41.replace("@{fileName}", name).replace("${path}", path + name);
			if(this.plfVersion.equalsIgnoreCase("4.0"))
				waitForAndGetElement(e40);
			else// if(this.plfVersion.equalsIgnoreCase("4.1"))
				waitForAndGetElement(e41);
		}
	}

	/**
	 * function check info in activity's header of wiki activity
	 * @param title
	 * @param content
	 * @param version
	 */
	public void checkActivityInfoOfWiki(String title, String content, String version){
		waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_WIKI_TITLE.replace("${title}", title)));
		if (content != ""){
			info("Check content");
			String[] sum = getText(ELEMENT_ACTIVITY_WIKI_CONTENT.replace("${title}", title)).split("\n");
			String[] cont = content.split("/");
			if (cont.length > 4){
				info("Check multiline content");
				assert sum[4].equalsIgnoreCase("...");
				for (int i = 0; i < 4; i++){
					assert sum[i].equalsIgnoreCase(cont[i]);
				}
			}else {
				for (int i = 0; i < cont.length; i ++){
					assert sum[i].equalsIgnoreCase(cont[i]) : "Failed! " + cont[i] + " differ " + sum[i];
				}
			}
		}
		if (version != ""){
			info("Check version");
			waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_WIKI_VERSION.replace("${title}", title).replace("${version}", version)));
		}
		Utils.pause(2000);
	}

	/**
	 * function delete a comment in activity
	 * @param comment
	 * @param title
	 */
	public void deleteComment(String title, String comment){
		alert = new ManageAlert(driver);
		button = new Button(driver);
		mouseOver(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", title).replace("${comment}", comment), false);
		click(ELEMENT_ACTIVITY_DELETE_COMMENT_ICON.replace("${comment}", comment), 2);
		waitForMessage(DATA_MESSAGE_CONFIRM_DELETE_COMMENT);
		button.ok();
		waitForElementNotPresent(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${comment}", comment));
	}

	/**
	 * function check comment in activity after moving a page
	 * @param title
	 * @param path
	 */
	public void checkCommentAfterMoveWikiPage(String title, String path){
		WebElement contentComment = waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT_1.replace("${title}", title), 5000, 0);
		if (contentComment != null){
			String verifyText = contentComment.getText();
			assert verifyText.equals("Page has been moved to: intranet > " + path): "Failed: moving a wiki page...";
		}else{
			waitForAndGetElement(ELEMENT_ACTIVITY_MOVE_WIKI_PAGE.replace("${title}", title).replace("${path}", path));
		}
		info("Move a wiki page: successful...");
		Utils.pause(2000);
	}


	/**
	 * function check comment in activity after moving a topic
	 * @param title
	 * @param path
	 */
	public void checkCommentAfterMoveTopic(String title, String path){
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", title).replace("${comment}", "Topic have been moved to: " + path));
	}

	/** Check content and number of lines of content on activity
	 * @author thuntn
	 * @param name
	 * @param content
	 */
	public void checkNumberOfLineOfContent(String activityContent, String content, boolean...checkActivity){
		boolean check = checkActivity.length > 0 ? checkActivity[0] : true;
		String[] sum = activityContent.split("\n");
		String[] cont = content.split("<br>");
		char[] character = activityContent.toCharArray();
		char[] contChar = content.toCharArray();

		info("Check content and number of lines of content on activity");

		if(content.contains("<br>")){
			cont = content.split("<br>");
			contChar= content.toCharArray();
		}else{
			cont = content.split("<br/>");
		}

		if (activityContent.contains("...")){
			String sumary = activityContent.replace("...", "");
			sum = sumary.split("\n");
			character = sumary.toCharArray();
			if(content.contains("...")){
				String contentTemp = content.replace("...", "");
				cont = contentTemp.split("<br>");

			}
		}
		if ((sum.length > 4) & check){
			Assert.assertFalse(false, "This content has more than 4 lines");

		}else {
			if(content.contains("<br>") || content.contains("<br/>")){
				for (int i = 0; i < sum.length; i ++){
					assert sum[i].equalsIgnoreCase(cont[i]);
				} 
				return;
			}
			if (contChar.length >= 430) {
				assert (character.length == 430);
				for (int i = 0; i < 430; i ++){
					assert (character[i]==contChar[i]);
				}
			}else{
				for (int i = 0; i < contChar.length; i ++){
					assert (character[i]==contChar[i]):"Character " + i +" ("+contChar[i]+","+character[i]+") is different";
				}
			}
		}

	} 
	/** Check Rate of Question
	 * @author thuntn
	 * @param name: name of question
	 * @param rate: rate of question
	 */
	public void checkRateQuestion(String name, Double rate){
		int round = rate.intValue();
		Double remaining = rate - round;
		for (int i = 1; i <= rate ; i++){
			waitForAndGetElement(ELEMENT_QUESTION_RATE.replace("${title}", name).replace("${rate}", Integer.toString(i)));
		}
		if (remaining != 0 ){
			waitForAndGetElement(ELEMENT_QUESTION_HAFT_RATE.replace("${title}", name));
		}
		for (int j = 5; j > rate ; j--){
			waitForAndGetElement(ELEMENT_QUESTION_UNRATE.replace("${title}", name).replace("${rate}", Integer.toString(j-round)));
			info("i is " + Integer.toString(j));
		}

	}
	/** Check number of answer, and comment of Answer
	 * @author thuntn
	 * @param question
	 * @param number
	 * @param answer
	 */
	public void checkAnswerOfQuestion(String question, String...answer){
		int number = answer.length;
		info("Check for comment for Answer of question on activity");
		//Check number of answer
		waitForAndGetElement(ELEMENT_QUESTION_NUM_ANSWER.replace("${title}",question).replace("${number}", Integer.toString(number)));
		if(this.plfVersion.equalsIgnoreCase("4.0")){
			for (int i = 0; i < number; i ++){
				waitForAndGetElement(ELEMENT_QUESTION_COMMENT.replace("${title}", question).replace("${comment}", MSG_ANSWER_QUESTION.replace("${answer}", answer[i])));
			}
		}


	}

	/** Check number of comment, and comment of question
	 * @author thuntn
	 * @param question
	 * @param comment: optional, comment of question
	 */
	public void checkCommentOfQuestion(String question, String...comment){
		int number = comment.length;
		info("Check for comment of question on activity");

		//Check number of comment
		waitForAndGetElement(ELEMENT_QUESTION_NUM_COMMENT.replace("${title}", question).replace("${number}", Integer.toString(number)));
		if(this.plfVersion.equalsIgnoreCase("4.0")){
			for (int i = 0; i < number; i ++){
				waitForAndGetElement(ELEMENT_QUESTION_COMMENT.replace("${title}", question).replace("${comment}", comment[i]));
			} 
		}
	}
	/** Check comment on activity after activating a question
	 * @author thuntn
	 * @param question: name of question
	 * @param activate: optional
	 * 					= true: check activity in case activate question
	 * 					= false: check activity in case unactivate question
	 * 					if not passed, check activity in case activate question
	 */
	public void checkActivateQuestion(String question, boolean...activate){
		boolean act = activate.length > 0 ? activate[0]: true;
		if(act){
			info("Check for comment of question after activating a question");
			waitForAndGetElement(ELEMENT_QUESTION_COMMENT.replace("${title}", question).replace("${comment}", MSG_QUESTION_ACTIVATE));
		}else{
			info("Check for comment of question after deactivating a question");
			waitForElementNotPresent(ELEMENT_QUESTION_COMMENT.replace("${title}", question).replace("${comment}", MSG_QUESTION_ACTIVATE));
		}
	}

	/** Check comment on activity after activating, deactivating an answer
	 * @author thuntn
	 * @param question: name of question
	 * @param activate: optional
	 * 					= true: check activity in case activate answer
	 * 					= false: check activity in case unactivate answer
	 * 					if not passed, check activity in case activate answer
	 */
	public void checkActivateAnswer(String question, String answer, boolean...activate){
		boolean act = activate.length > 0 ? activate[0]: true;
		if(act){
			info("Check for comment on activity after activating an answer");
			waitForAndGetElement(ELEMENT_QUESTION_COMMENT.replace("${title}", question).replace("${comment}", MSG_ANSWER_ACTIVATE.replace("${answer}", answer)));
		}else{
			info("Check for commenton activity  after deactivating an answer");
			waitForAndGetElement(ELEMENT_QUESTION_COMMENT.replace("${title}", question).replace("${comment}", MSG_ANSWER_UNACTIVATE.replace("${answer}", answer)));
		}
	}


	/** Check reply of a topic, and number of reply
	 * @author thuntn
	 * @param topic
	 * @param reply
	 */
	public void checkReplyForum(String topic, String...reply){
		int number = reply.length;

		info("Check reply of a topic, and number of reply");
		//Check number of reply
		if (number == 1)
			waitForAndGetElement(ELEMENT_FORUM_ONE_REPLY.replace("${title}", topic).replace("${number}", Integer.toString(number)));
		else 
			waitForAndGetElement(ELEMENT_FORUM_NUMBER_REPLY.replace("${title}", topic).replace("${number}", Integer.toString(number)));

		for(int i = 0; i < number; i++){
			waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", topic).replace("${comment}", reply[i]));
		}

	}

	/** Check Rate of Topic
	 * @author thuntn
	 * @param name: name of topic
	 * @param rate: rate of topic
	 */
	public void checkRateTopic(String topic, Double rate){
		int round = rate.intValue();
		Double remaining = rate - round;

		info("Check Rate of Topic");
		for (int i = 1; i <= rate ; i++){
			waitForAndGetElement(ELEMENT_TOPIC_RATE.replace("${title}", topic).replace("${rate}", Integer.toString(round)));
		}
		if (remaining != 0 ){
			waitForAndGetElement(ELEMENT_TOPIC_HAFT_RATE.replace("${title}", topic));
		}
	} 
	/** Check activity after updating a topic
	 * @author thuntn
	 * @param topicTitle: Title of topic
	 * @param newContent: new content of topic 
	 */
	public void checkUpdateTopic(String topicTitle, String newContent){

		info("Check activity after updating a topic");
		//Check content
		checkNumberOfLineOfContent(getText((ELEMENT_FORUM_ACT_CONTENT.replace("${title}", topicTitle))), newContent);
		//Check comment
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", topicTitle).replace("${comment}", "Content has been edited."));

	}

	/** Check activity after Locking a topic
	 * @author thuntn
	 * @param topicTitle: Title of topic
	 */
	public void checkLockTopic(String topicTitle){

		info("Check activity after Locking a topic");

		waitForAndGetElement(By.linkText(topicTitle));
		waitForElementNotPresent(ELEMENT_ICON_COMMENT.replace("${title}", topicTitle));
		waitForElementNotPresent(ELEMENT_COMMENTBOX.replace("${title}", topicTitle));
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", topicTitle).replace("${comment}", "Topic has been locked."));
	}

	/** Check activity after Unlocking a topic
	 * @author thuntn
	 * @param topicTitle: Title of topic
	 */
	public void checkUnlockTopic(String topicTitle){

		info("Check activity after Unlocking a topic");

		waitForAndGetElement(By.linkText(topicTitle));
		//Check if icon comment, comment box appear
		waitForAndGetElement(ELEMENT_ICON_COMMENT.replace("${title}", topicTitle));
		click(ELEMENT_ICON_COMMENT.replace("${title}", topicTitle));
		waitForAndGetElement(ELEMENT_COMMENTBOX.replace("${title}", topicTitle));
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", topicTitle).replace("${comment}", "Topic has been unlocked."));

	}

	/** Check activity after adding a poll
	 * @author thuntn
	 * @param topic: title of topic
	 * @param poll: title of poll
	 * @param options: array of options of the poll
	 * @param rate: array of rate of options, corresponding to options array
	 */
	public void checkAddPoll(String topic, String poll,String[] options, String[] rate){
		int length = options.length;

		info("Check activity after adding a poll");
		//Check comment of topic activity
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", topic).replace("${comment}", "A poll has been added to the topic."));
		//Check poll activity
		waitForAndGetElement(ELEMENT_POLL_ACTIVITY.replace("${poll}", poll));

		//Check vote, rate of each vote
		for(int i = 0; i < length; i ++){
			waitForAndGetElement(ELEMENT_VOTE.replace("${poll}", poll).replace("${vote}", options[i]));
			waitForAndGetElement(ELEMENT_VOTE_PROGRESSBAR.replace("${poll}", poll).replace("${vote}", options[i]));
			if (i < rate.length)
				waitForAndGetElement(ELEMENT_VOTE_RATE.replace("${poll}", poll).replace("${vote}", options[i]).replace("${rate}", rate[i]));
		}

	}

	/** Check activity after editing a poll
	 * @author thuntn
	 */
	public void checkEditPoll(String poll){
		info("Check activity after editing a poll");
		waitForAndGetElement(ELEMENT_ACTIVITY_COMMENT_CONTENT.replace("${title}", poll).replace("${comment}", "Poll has been updated."));
	}

	/** Open Poll from activity
	 * @author thuntn
	 * @param poll
	 */
	public void openVoteFromActivity(String poll){
		info("Open Poll from activity");
		click(ELEMENT_POLL_VOTE.replace("${poll}", poll));

		waitForAndGetElement(By.linkText("Vote Now"));
	}

	/** Open Reply form from activity
	 * @author thuntn
	 * @param topic
	 */
	public void openReplyFormFromActivity(String topic){
		info("Open Reply form from activity");
		click(ELEMENT_TOPIC_REPLY.replace("${title}", topic));
		waitForAndGetElement(post.ELEMENT_POST_POPUP_NEW);
		assert waitForAndGetElement(post.ELEMENT_POST_TITLE).getAttribute("value").equalsIgnoreCase("Re: " + topic);

		click(post.ELEMENT_POST_CANCEL_BUTTON);
		waitForElementNotPresent(post.ELEMENT_POST_POPUP_NEW);
	}
	/** Open last reply of topic
	 * @author thuntn
	 * @param topic
	 */
	public void openLastReplyFromActivity(String topic, String lastReply){
		info("Open last reply of topic");
		click(ELEMENT_TOPIC_LAST_REPLY.replace("${title}", topic));
		waitForAndGetElement(post.ELEMENT_POST_REPLY_BUTTON);
		waitForTextPresent(lastReply);

	}

	/** View Answer, comment, reply of topic...from comment of activity
	 * @author thuntn
	 * @param activity: title of question, topic...
	 * @param comment: comment of activity
	 */
	public void viewActivity(String activity, String comment){
		Actions actions = new Actions(driver);
		WebElement element = waitForAndGetElement(ELEMENT_TOPIC_COMMENT.replace("${title}",activity).replace("${comment}", comment));

		Locatable hoverItem = (Locatable) waitForAndGetElement(By.xpath("//div[@class='activityBottom']"),DEFAULT_TIMEOUT,1,2);
		int y = hoverItem.getCoordinates().onPage().getY();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+y+");");
		actions.moveToElement(element).click().perform();

		WebElement elementView = waitForAndGetElement(ELEMENT_REPLY_VIEW.replace("${comment}", comment), 3000, 1, 2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementView);
	}

	/**View a reply by clicking on View in activity
	 * @author thuntn
	 * @param topic
	 * @param reply
	 */
	public void viewReplyFromActivity(String topic, String reply){
		info("View a reply by clicking on View in activity");

		viewActivity(topic, reply);
		waitForAndGetElement(post.ELEMENT_POST_REPLY_BUTTON);
		waitForAndGetElement(post.ELEMENT_POST_CONTENT_TEXT.replace("${post}", reply));
	}

	/**
	 * @author phuongdt
	 * Delete activity 
	 * @param activityText: input a String 
	 * @param verify[0] -> false: user cannot delete this activity 
	 */
	public void deleteActivity (String activityText,boolean...verify) {
		info("-- Deleting an activity " +activityText+" --");
		boolean canDelete = verify.length>0?verify[0]:true;
		boolean verifyElement = verify.length>1?verify[1]:true;
		if(!canDelete)
			waitForElementNotPresent(By.xpath(ELEMENT_ACTIVITY_DELETE.replace("${activityText}", activityText)), DEFAULT_TIMEOUT,1,2);
		else{
			WebElement elem = waitForAndGetElement(By.xpath(ELEMENT_ACTIVITY_DELETE.replace("${activityText}", activityText)), DEFAULT_TIMEOUT,1,2);

			String deleteActivityIconID;
			deleteActivityIconID = elem.getAttribute("id");

			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("document.getElementById('"+deleteActivityIconID+"').click();");
			waitForAndGetElement(ELEMENT_MESSAGE_CONFIRM_DELETE_ACTIVITY);
			button.ok();
			if(verifyElement)
				waitForElementNotPresent(By.xpath(ELEMENT_ACTIVITY_DELETE.replace("${activityText}", activityText)), 5000,1,2);
		}
		Utils.pause(2000);

	}

	/**
	 * Like/Unlike an activity
	 * @param activityText: input a text (String) 
	 */
	public void likeOrUnlikeActivity(String activityText){
		info("-- Action: Like or Unlike an activity --");

		if (waitForAndGetElement(ELEMENT_LIKE_ICON.replace("${activityText}", activityText), DEFAULT_TIMEOUT, 0) != null){
			info("-- Like activity --");
			int numLike = Integer.parseInt(waitForAndGetElement(ELEMENT_LIKE_ICON.replace("${activityText}", activityText)).getText().trim());
			click(ELEMENT_LIKE_ICON.replace("${activityText}", activityText));
			info("-- Verify Like button is highlighted --");
			waitForAndGetElement(ELEMENT_UNLIKE_ICON.replace("${activityText}", activityText)+"/i[@class='uiIconThumbUp uiIconBlue']");
			info("-- Like successfully and Verify number of like is updated --");
			int newNumLike = Integer.parseInt(waitForAndGetElement(ELEMENT_UNLIKE_ICON.replace("${activityText}", activityText)).getText().trim());
			assert (newNumLike==(numLike+1)):"Number of like is not updated";

		}else{
			info("-- Unlike activity --");

			int numLike = Integer.parseInt(waitForAndGetElement(ELEMENT_UNLIKE_ICON.replace("${activityText}", activityText)).getText().trim());
			click(ELEMENT_UNLIKE_ICON.replace("${activityText}", activityText));
			info("-- Verify UnLike button is gray --");
			waitForAndGetElement(ELEMENT_LIKE_ICON.replace("${activityText}", activityText)+"/i[@class='uiIconThumbUp uiIconLightGray']");
			info("-- Unlike successfully and Verify number of like is updated --");
			int newNumLike = Integer.parseInt(waitForAndGetElement(ELEMENT_LIKE_ICON.replace("${activityText}", activityText)).getText().trim());
			assert (newNumLike==(numLike-1)):"Number of like is not updated";
		}
		Utils.pause(2000);

	}

	/** Check Event activity after adding an event
	 * @author thuntn
	 * @param event: name of event
	 * @param date: date of event which is shown on activity home page, in format "dd", eg:13
	 * @param month: month of event which is shown on activity home page, in format "MMM", eg:Oct
	 */
	public void checkEventActivity(String event,String date, String month,boolean...display){
		info("Check Event activity after adding an event");
		boolean show = display.length > 0 ? display[0] : true;
		if(show){
			waitForAndGetElement(By.linkText(event));
			waitForAndGetElement(ELEMENT_EVENT_MONTH_ICON.replace("${event}", event).replace("${month}", month));
			waitForAndGetElement(ELEMENT_EVENT_DATE_ICON.replace("${event}", event).replace("${date}", date));
		}
		else{
			waitForElementNotPresent(By.linkText(event));
			waitForElementNotPresent(ELEMENT_EVENT_MONTH_ICON.replace("${event}", event).replace("${month}", month));
			waitForElementNotPresent(ELEMENT_EVENT_DATE_ICON.replace("${event}", event).replace("${date}", date));
		}
	}

	/** Check Event activity after adding an event
	 * @author thuntn
	 * @param event: name of event
	 * @param date: date of event which is shown on activity home page, in format "dd", eg:13
	 * @param month: month of event which is shown on activity home page, in format "MMM", eg:Oct
	 */
	public void checkTaskActivity(String task,boolean...display){
		info("Check Event activity after adding an event");
		boolean show = display.length > 0 ? display[0] : true;

		driver.navigate().refresh();
		if(show){
			waitForAndGetElement(By.linkText(task));
		}
		else{
			waitForElementNotPresent(By.linkText(task));
		}
	}

	/**
	 * Check activity of a question after just creating it
	 * @param name: name of question
	 * @param content: content of question
	 */
	public void checkActivityAfterCreatingQuestion(String name, String content){
		info("Check activity of a question after just creating it");

		waitForAndGetElement(By.linkText(name));
		waitForElementNotPresent(ELEMENT_COMMENT_BLOCK.replace("${title}", name));
		waitForAndGetElement(ELEMENT_QUESTION_CONTENT.replace("${title}", name));
		waitForAndGetElement(ELEMENT_QUESTION_NUM_COMMENT.replace("${title}", name).replace("${number}", "No"));
		checkNumberOfLineOfContent(getText(ELEMENT_QUESTION_CONTENT.replace("${title}", name)), content);
	}
	
	/**
	 * Add an activity stream with selecting a document that
	 * existed in SE
	 * @param nameDrive
	 * @param pathFolder
	 * @param nameFile
	 * @param textDes
	 */
	public void addActivity(String nameDrive,String pathFolder,String nameFile,String textDes){
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
		if(!textDes.isEmpty())
		addText(textDes);
		info("----Click share button----");
		waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON);
		click(ELEMENT_COMPOSER_SHARE_BUTTON);
		Utils.pause(2000);
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
		info("Click on drop down list");
		click(ELEMENT_ACTIVITY_UPLOAD_POPUP);
		info("select a driver:"+nameDrive);
		if(!nameDrive.isEmpty())
		click(ELEMENT_DRIVER_OPTION.replace("${driveName}",nameDrive));
		info("go to the folder by path:"+path);
		String[] arrayPath = path.split("/");
		for(String arrayElement:arrayPath){
			click(ELEMENT_ACTIVITY_UPLOAD_POPUP_NODE.replace("${nameNode}", arrayElement));
			Utils.pause(2000);
		}
	}
	
	/**
	 * Add a Text to Composer box of AS
	 * @param contentText
	 */
	public void addText(String contentText) {
		info("----Add text into activity text box-----");
		WebElement inputText = waitForAndGetElement(ELEMENT_COMPOSER_INPUT_FILED, 100000);
		WebElement shareButton = waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON);
		WebElement workingLabel = waitForAndGetElement(ELEMENT_ACTIVITY_WHAT_ARE_YOU_WORKING_LABEL);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].textContent = '';", workingLabel);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].textContent = '" + contentText + "';", inputText);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].disabled = false;", shareButton);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].className = 'pull-right btn btn-primary';",
				shareButton);
		Utils.pause(1000);
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
	public void addActivity(String nameDrive,String pathFolder,String pathData,String nameFile,boolean addText,String text) {
		info("-- Adding an activity--");
		Utils.pause(3000);
		openUploadPopup(nameDrive,pathFolder);
		uploadFileFromAS(pathData,nameFile);
		
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
		if(addText)
		addText(text);
		info("----Click share button----");
		waitForAndGetElement(ELEMENT_COMPOSER_SHARE_BUTTON,2000,0);
		click(ELEMENT_COMPOSER_SHARE_BUTTON);
		Utils.pause(2000);
	}
	/**
	 * Upload a file from Upload Popup
	 * @param path     is TestData folder
	 * @param nameFile
	 */
	public void uploadFileFromAS(String path,String nameFile){
		info("-- Upload file --");
		WebElement frame = waitForAndGetElement(ELEMENT_UPLOAD_FILE_FRAME_XPATH,3000,0);
		driver.switchTo().frame(frame);
		Utils.pause(2000);
		((JavascriptExecutor)driver).executeScript("document.getElementsByTagName('input')[0].style.display = 'block';");
		Utils.pause(2000);
		driver.findElement(ELEMENT_ACTIVITY_UPLOAD_POPUP_UPLOAD_BUTTON).sendKeys(Utils.getAbsoluteFilePath(path+nameFile));
		switchToParentWindow();
		waitForElementNotPresent(ELEMENT_ACTIVITY_UPLOAD_POPUP_PROGRESS_UPLOAD,3000,0);
		click(ELEMENT_ACTIVITY_UPLOAD_POPUP_CLOSE_BTN);
		Utils.pause(2000);
		info("Upload finished");
	}
	
	
	 /**
    * Go to a space
    * @param space
    */
	public void goToSpecificSpace(String space){
		info("Go to space "+space);
		click(By.xpath(ELEMENT_SPECIFIC_PANEL.replace("{$space}",space)));
	}
}