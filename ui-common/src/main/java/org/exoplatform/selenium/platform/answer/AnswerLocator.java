package org.exoplatform.selenium.platform.answer;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

public class AnswerLocator extends PlatformBase {

	//Answer portlets
	public By ELEMENT_ANSWER_PORTLET = By.id("UIAnswersContainer");
	public By ELEMENT_SUBMIT_QUESTION=By.xpath("//*[contains(@class,'uiIconAnsSubmitQuestion')]");
	public By ELEMENT_CATEGORY_BUTTON=By.xpath("//*[@class='uiIconAnsManageCategory']");
	public String ELEMENT_QUESTION_LIST_ITEM="//*[@class='rightContent']//*[text()='$question']";
	public String ELEMENT_QUESTION_SELECTED_ITEM="//*[contains(@class,'questionSelect')]//*[@class='theContent questionName' and contains(text(),'$question')]";
	public By ELEMENT_MANAGE_QUESTION_BUTTON=By.xpath("//*[@class='uiIconAnsManageQuestion']");
	public String ELEMENT_QUESTION_LINK=".//*[contains(@class,'titleWithBorder')]//*[contains(text(),'$question')]";

	//Breadcumb
	public By ELEMENT_CATEGORY_BREADCUMB_HOME=By.xpath("//*[@id='UIBreadcumbs']//*[text()='Home']");
	
	//Quick Search
	public By ELEMENT_QUICK_SEARCH_INPUT=By.id("inputValue");
	public By ELEMENT_QUICK_SEARCH_BUTTON=By.xpath("//*[@class='uiIconSearch uiIconLightGray']");
	public By ELEENT_QUICK_SEARCH_POPUP=By.id("UIResultQuickSearchs");
	public String ELEMENT_QUICK_SEARCH_RESULT_ITEM="//*[@id='UIResultQuickSearchs']//*[contains(text(),'$name')]";
	public String ELEMENT_QUICK_SEARCH_CLOSE="//*[@id='UIResultQuickSearchs']//*[text()='Close']";
	public String ELEMENT_QUICK_SEARCH_ADVANCE_SEARCH_BUTTON="//*[@id='UIResultQuickSearchs']//*[text()='Advanced Search']";
	
	//Advance search
	public By ELEMENT_ADVANCE_SEARCH_POPUP=By.id("AdvanceSearchForm");
	public By ELEMENT_ADVANCE_SEARCH_KEY_INPUT=By.id("Text");
	public By ELEMENT_ADVANCE_SEARCH_ALL_RADIO_BUTTON=By.xpath("//*[@value='categoryAndQuestion']");
	public By ELEMENT_ADVANCE_SEARCH_CATEGORY_RADIO_BUTTON=By.xpath("//*[@value='faqCategory']");
	public By ELEMENT_ADVANCE_SEARCH_ENTRY_RADIO_BUTTON=By.xpath("//*[@value='faqQuestion']");
	public String ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON="//*[@id='AdvanceSearchForm']//*[text()='Search']";
	public String ELEMENT_ADVANCE_SEARCH_ADVANCE_CLOSE_BUTTON="//*[@id='AdvanceSearchForm']//*[text()='Close']";
	public String ELEMENT_ADVANCE_SEARCH_RESULT_ITEM="//*[@id='AdvanceSearchForm']//*[contains(text(),'$name')]";
	public By ELEENT_ADVANCE_SEARCH_CLOSE_RESULT_QUICK_SEARCH=By.id("//*[@class='resultQuickSearch']//*[text()='Close']");
	
	public String ELEMENT_QUESTION_ANSWER_LINK="//*[contains(text(),'$question')]/../../..//*[@class='questionAction']//*[contains(text(),'Answer')]";
	public By ELEMENT_QUESTION_RESPONE_FORM=By.xpath(".//*[@id='UIResponseForm']");
	
	
	//Manage question form
	public By ELEMENT_MANAGE_QUESTION_FORM=By.id("FAQQuestionManagerment");
	public By ELEMENT_MANAGE_QUESTION_FORM_ALL_QUESTION_TAB=By.xpath("//*[@data-toggle='tab' and text()='All Questions']");
	public By ELEMENT_MANAGE_QUESTION_FORM_OPEN_QUESTION_TAB=By.xpath("//*[@data-toggle='tab' and text()='Open Questions']");
	public String ELEMENT_MANAGE_QUESTION_ANSWER_QUESTION="//*[text()='$question']/..//*[@data-original-title='Answer']";
	public String ELEMENT_MANAGE_QUESTION_EDIT_QUESTION="//*[text()='$question']/..//*[@data-original-title='Edit']";
	public String ELEMENT_MANAGE_QUESTION_DELETE_QUESTION="//*[text()='$question']/../..//*[@data-original-title='Delete']";
	public String ELEMENT_MANAGE_QUESTION_APPROVE_QUESTION_CHECKBOX="//*[text()='$question']/..//*[@data-original-title='Approve' or @data-original-title='Disapprove']//*[@id='allDay']";
	public String ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX="//*[text()='$question']/..//*[@data-original-title='Deactivate' or @data-original-title='Activate']//*[@id='allDay']";
	public By ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON=By.xpath("//*[@id='UIAnswersPopupAction']//*[text()='Close']");
	
	//Submit Question form
	public By ELEMENT_SUBMIT_QUESTION_FORM = By.id("UIQuestionForm");
	public By ELEMENT_SUBMIT_QUESTION_FORM_TITLE_INPUT=By.id("QuestionTitle");
	public By ELEMENT_SUBMIT_QUESTION_FORM_DATA_FRAME_INPUT=By.xpath("//*[@class='cke_wysiwyg_frame cke_reset']");
	public By ELEMENT_SUBMIT_QUESTION_FORM_LANGUAGE_SELECT_BOX=By.name("AllLanguages");
	public By ELEMENT_SUBMIT_QUESTION_FORM_DELETE_LANG_BUTTON=By.xpath("//*[@name='AllLanguages']/../..//*[@data-original-title='Remove']");
	public By ELEMENT_SUBMIT_QUESTION_FORM_AUTHOR=By.id("Author");
	public By ELEMENT_SUBMIT_QUESTION_FORM_EMAIL=By.id("EmailAddress");
	public By ELEMENT_SUBMIT_QUESTION_FORM_ATTACHMENT_BUTTON=By.xpath("//*[@class='uiIconAttach uiIconLightGray']");
	public String ELEMENT_SUBMIT_QUESTION_FORM_DELETE_ATTACHMENT_BUTTON="//*[@data-original-title='$file']/../..//*[@data-original-title='Remove']";
	public By ELEMENT_SUBMIET_QUESTION_APPROVE_CHECKBOX=By.id("IsApproved");
	public By ELEMENT_SUBMIET_QUESTION_ACTIVE_CHECKBOX=By.id("IsActivated");
	public By ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON=By.xpath("//*[@id='UIQuestionForm']//*[text()='Save']");
	public By ELEMENT_SUBMIT_QUESTION_FORM_CANCEL_BUTTON=By.xpath("//*[@id='UIQuestionForm']//*[text()='Cancel']");
	public By ELEMENT_QUESTION_FILE_INPUT = By.xpath("//*[@name='file']");

	//Attach file form
	public By ELEMENT_ATTACH_SAVE_BUTTON = By.xpath("//form[@id='UIAttachmentForm']//*[text()='Save']");
	public String ELEMENT_ATTACHMENT_FORM_FILE_NAME = "//*[text()='$fileName']";
	public String ELEMENT_ATTACH_FILE_NAME = "//*[@data-original-title='$fileName']";

	//More actions
	public By ELEMENT_QUESTION_MORE_ACTION_BUTTON=By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconSettings')]");
	public By ELEMENT_QUESTION_PRINT_BUTTON=By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconPrint')]");
	public By ELEMENT_QUESTION_EDIT_BUTTON=By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconEdit')]");
	public By ELEMENT_QUESTION_MOVE_BUTTON=By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconMove')]");
	public By ELEMENT_QUESTION_SEND_BUTTON=By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconAnsSentMail')]");
	public By ELEMENT_QUESTION_DELETE_BUTTON=By.xpath("//*[contains(@class,'answersContainer')]//*[contains(@class,'actionBar')]//*[contains(@class,'uiIconTrash')]");

	//Question menu action
	public String ELEMENT_QUESTION_COMMENT="//*[@class='rightContent']//*[text()='$question']//ancestor::*[@class='rightContent']//*[@class='uiIconComment uiIconLightGray']";
	public String ELEMENT_QUESTION_ANSWER="//*[@class='rightContent']//*[text()='$question']//ancestor::*[@class='rightContent']//*[@class='uiIconAnsAnswer uiIconLightGray']";
	public String ELEMENT_QUESTION_EDIT="//*[@class='rightContent']//*[text()='$question']//ancestor::*[@class='rightContent']//*[@class='uiIconEdit uiIconLightGray']";
	public String ELEMENT_QUESTION_DELETE="//*[@class='rightContent']//*[text()='$question']//ancestor::*[@class='rightContent']//*[@class='uiIconTrash uiIconLightGray']";
	public String ELEMENT_QUESTION_MOVE="//*[@class='rightContent']//*[text()='$question']//ancestor::*[@class='rightContent']//*[@class='uiIconMove uiIconLightGray']";
	public String ELEMENT_QUESTION_SEND="//*[@class='rightContent']//*[text()='$question']//ancestor::*[@class='rightContent']//*[@class='uiIconAnsSentMail uiIconLightGray']";

	//Comment question form
	public By ELEMENT_QUESTION_COMMENT_FORM=By.id("UICommentForm");

	//Answer question form
	public By ELEMENT_QUESTION_ANSWER_FORM=By.id("UIAnswersPopupWindow");

	//Edit question form
	public By ELEMENT_QUESTION_EDIT_FORM=By.id("UIQuestionForm");

	//Delete question form
	public By ELEMENT_QUESTION_DELETE_FORM=By.id("UIDeleteQuestion");
	public By ELEMENT_QUESTION_CONFIRM_DELETE=By.xpath("//*[@id='UIDeleteQuestion']//*[contains(text(),'Are you sure you want to delete this question and its answers?')]");
	public By ELEMENT_QUESTION_DELETE_FORM_OK_BUTTON=By.xpath("//*[@id='UIDeleteQuestion']//*[text()='OK']");
	public By ELEMENT_QUESTION_DELETE_FORM_CANCEL_BUTTON=By.xpath("//*[@id='UIDeleteQuestion']//*[text()='Cancel']");

	//Move question form
	public By ELEMENT_QUESTION_MOVE_FORM=By.id("FAQMoveQuestion");

	//Send question form
	public By ELEMENT_QUESTION_SEND_FORM=By.id("FAQSendMailForm");
	public By ELEMENT_QUESTION_SEND_TO_INPUT=By.id("To");
	public By ELEMENT_QUESTION_SEND_SEND_BUTTON=By.xpath("//*[@id='FAQSendMailForm']//*[text()='Send']");
	public By ELEMENT_QUESTION_OK_BUTTON=By.xpath("//*[contains(@class,'UIPopupWindow')]//a[text()='OK']");

	//Vote question
	public String ELEMENT_QUESTION_RATE_ITEM="//*[@data-original-title='Rate Question']/*[@data-index='$index']";
	public String ELEMENT_QUESTION_RATE_NUMBER="//*[contains(@class,'voteResult')]//*[contains(text(),'$index')]";
	
	//Answer form
	public By ELEMENT_ANSWER_BUTTON=By.xpath("//*[@class='questionAction']//*[text()='Answer']");
	public By ELEMENT_ANSWER_FORM=By.id("UIResponseForm");
	public By ELEMENT_ANSWER_FORM_DATA_FRAME_INPUT=By.xpath("//*[@class='cke_wysiwyg_frame cke_reset']");
	public By ELEMENT_ANSWER_APPROVE_CHECKBOX=By.id("QuestionApproved");
	public By ELEMENT_ANSWER_ACTIVATE_CHECKBOX=By.id("QuestionShowAnswer");
	public By ELEMENT_ANSWER_RELATED_BUTTON=By.xpath("//*[@class='uiIconLightGray uiIconPlus']");
	public By ELEMENT_ANSWER_FORM_SAVE_BUTTON=By.xpath(".//*[@id='UIResponseForm']//button[1]");
	public By ELEMENT_ANSWER_FORM_CANCEL_BUTTON=By.xpath(".//*[@id='UIResponseForm']//button[2]");

	//More actions
	public String ELEMENT_ANSWER_MORE_ACTION_BUTTON="//*[@class='responseContainer']//*[contains(@id,'Answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconSettings uiIconLightGray']";
	public String ELEMENT_ANSWER_EDIT_BUTTON="//*[@class='responseContainer']//*[contains(@id,'Answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconLightGray uiIconEdit']";
	public String ELEMENT_ANSWER_APPROVE_BUTTON="//*[@class='responseContainer']//*[contains(@id,'Answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconLightGray uiIconEdit']";
	public String ELEMENT_ANSWER_DISAPPROVE_BUTTON="//*[@class='responseContainer']//*[contains(@id,'Answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconAnsLightGray uiIconAnsDisapprove']";
	public String ELEMENT_ANSWER_ACTIVE_BUTTON="//*[@class='responseContainer']//*[contains(@id,'Answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconAnsLightGray uiIconAnsEnable']";
	public String ELEMENT_ANSWER_DEACTIVE_BUTTON="//*[@class='responseContainer']//*[contains(@id,'Answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconAnsLightGray uiIconAnsEnable']";
	public String ELEMENT_ANSWER_DELETE_BUTTON="//*[@class='responseContainer']//*[contains(@id,'Answer')]//*[text()='$answer']/../../../../..//*[@class='uiIconLightGray uiIconTrash']";
	
	//Answer container
	public String ELEMENT_ANSWER_AUTHOR="//*[contains(@id,'Answer')]//*[text()='$answer']/../..//*[@class='userName' and contains(text(),'$fullname')]";
	public String ELEMENT_ANSWER_CONTENT="//*[contains(@id,'Answer')]//*[text()='$answer']";

	//Delete 
	public By ELEMENT_ANSWER_DELETE_CONFIRM_POPUP=By.id("UIForumPopupConfirmation");
	public By ELEMENT_ANSWER_CONFIRM_DELETE=By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(text(),'Are you sure you want to delete this answer ?')]");
	public By ELEMENT_ANSWER_DELETE_FORM_OK_BUTTON=By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='OK']");
	public By ELEMENT_ANSWER_DELETE_FORM_CANCEL_BUTTON=By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='Cancel']");

	//vote answer
	public final String ELEMENT_ANSWER_VOTE_ICON = "//*[@class='responseContainer']//*[contains(@id,'Answer')]//*[text()='$answer']/../../..//*[contains(@id, 'FAQVoteAnswerUp')]";
	public final String ELEMENT_ANSWER_UNVOTE_ICON = "//*[@class='responseContainer']//*[contains(@id,'Answer')]//*[text()='$answer']/../../..//*[contains(@id, 'FAQVoteAnswerDown')]";
	public final String ELEMENT_ANSWER_NUMBER_VOTE = "//*[@class='responseContainer']//*[contains(@id,'Answer')]//*[text()='$answer']/../../..//*[@class='textVoteAnswer']";
	public final By ELEMENT_SORT_BY_RATE = By.xpath("//a[@data-original-title='Sort Answers by Rate']");
	public final String ELEMENT_ANSWER_POSITION_IN_LIST = "//*[@class='responseContainer']//*[contains(@id,'Answer')][${no}]//*[text()='${answer}']";
	
	
	
	
}
