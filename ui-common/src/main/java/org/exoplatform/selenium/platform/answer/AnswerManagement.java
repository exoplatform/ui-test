package org.exoplatform.selenium.platform.answer;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;
public class AnswerManagement  extends AnswerLocator {
	
	/**
	 * constructor
	 * @param dr
	 */
	public AnswerManagement(WebDriver dr){
		this.driver=dr;
	}

	/**
	 * Go to answer a question
	 */
	public void goToAnswerQuestion(String question){
		info("Go to answer a question");
		Utils.pause(3000);
		if(waitForAndGetElement(ELEMENT_QUESTION_LIST_ITEM.replace("$question", question),5000,0)!=null)
			click(ELEMENT_QUESTION_LIST_ITEM.replace("$question", question));
		waitForAndGetElement(ELEMENT_QUESTION_SELECTED_ITEM.replace("$question", question));
		click(ELEMENT_ANSWER_BUTTON);
		waitForAndGetElement(ELEMENT_ANSWER_FORM);
	}

	/**
	 * Input data to answer form
	 * @param content
	 * @param isApprove
	 * @param isActive
	 * @param related
	 */
	public void inputDataToAnswer(String content, Boolean isApprove, Boolean isActive, String related){
		info("Input data to answer form");
		
		if(content!=null && content!=""){
			info("input content");
			inputDataToCKEditor(ELEMENT_ANSWER_FORM_DATA_FRAME_INPUT,content);
		}
		
		if(isApprove!=null){
			info("approve or not");
			if(isApprove)
				check(ELEMENT_ANSWER_APPROVE_CHECKBOX,2);
			else
				uncheck(ELEMENT_ANSWER_APPROVE_CHECKBOX,2);
		}

		if(isActive!=null){
			info("active or not");
			if(isActive)
				check(ELEMENT_ANSWER_ACTIVATE_CHECKBOX,2);
			else
				uncheck(ELEMENT_ANSWER_ACTIVATE_CHECKBOX,2);
		}
		
		if(related!=null && related!=""){
			info("input related questions");

		}
	}

	/**
	 * action menu of answer
	 */
	public enum actionAnswerOption{
		EDIT, APPROVE, DISAPPROVE, ACTIVE, DEACTIVE, DELETE
	}
	
	/**
	 * Execute action of answer: EDIT, APPROVE, DISAPPROVE, ACTIVE, DEACTIVE, DELETE
	 * @param answer
	 * @param action
	 * 				action that needs to be done
	 */
	public void goToActionOfAnswerFromMoreAction(String answer, actionAnswerOption action){
		info("Select action from menu");
		click(ELEMENT_ANSWER_MORE_ACTION_BUTTON.replace("$answer", answer));
		switch(action){
		case EDIT:
			info("EDIT answer");
			click(ELEMENT_ANSWER_EDIT_BUTTON.replace("$answer", answer));
			waitForAndGetElement(ELEMENT_ANSWER_FORM);
			break;
		case APPROVE:
			info("APPROVE answer");
			click(ELEMENT_ANSWER_APPROVE_BUTTON.replace("$answer", answer));
			click(ELEMENT_ANSWER_MORE_ACTION_BUTTON.replace("$answer", answer));
			waitForAndGetElement(ELEMENT_ANSWER_DISAPPROVE_BUTTON);
			break;
		case DISAPPROVE:
			info("DISAPPROVE answer");
			click(ELEMENT_ANSWER_DISAPPROVE_BUTTON.replace("$answer", answer));
			click(ELEMENT_ANSWER_MORE_ACTION_BUTTON.replace("$answer", answer));
			waitForAndGetElement(ELEMENT_ANSWER_APPROVE_BUTTON);
			break;
		case ACTIVE:
			info("ACTIVE answer");
			click(ELEMENT_ANSWER_ACTIVE_BUTTON.replace("$answer", answer));
			click(ELEMENT_ANSWER_MORE_ACTION_BUTTON.replace("$answer", answer));
			waitForAndGetElement(ELEMENT_ANSWER_DEACTIVE_BUTTON);
			break;
		case DEACTIVE:
			info("DEACTIVE answer");
			click(ELEMENT_ANSWER_DEACTIVE_BUTTON.replace("$answer", answer));
			click(ELEMENT_ANSWER_MORE_ACTION_BUTTON.replace("$answer", answer));
			waitForAndGetElement(ELEMENT_ANSWER_ACTIVE_BUTTON);
			break;
		case DELETE:
			info("DELETE answer");
			click(ELEMENT_ANSWER_DELETE_BUTTON.replace("$answer", answer));
			waitForAndGetElement(ELEMENT_ANSWER_DELETE_CONFIRM_POPUP);
			break;
		default:
			info("Do nothing");
			break;
		}
	}
	
	/**
	 * Delete answer
	 * @param answer
	 */
	public void deleteAnswer(String answer){
		info("Delete answer");
		goToActionOfAnswerFromMoreAction(answer, actionAnswerOption.DELETE);
		waitForAndGetElement(ELEMENT_ANSWER_CONFIRM_DELETE);
		click(ELEMENT_ANSWER_DELETE_FORM_OK_BUTTON);
		waitForElementNotPresent(ELEMENT_ANSWER_CONTENT.replace("$answer", answer));
	}
	
	/**
	 * function vote/unvote an answer
	 * @param answer
	 * @param rate
	 */
	public void rateAnswer(String answer, boolean rate){
		if (rate){
			info("Vote answer");
			click(ELEMENT_ANSWER_VOTE_ICON.replace("$answer", answer));
		} else {
			info("Unvote answer");
			click(ELEMENT_ANSWER_UNVOTE_ICON.replace("$answer", answer));
		}
	}
	/**
	 * Save or Cancel all changes of Answer form
	 * @param isSave
	 *                = true if want to save all changes
	 *                = false if want to cancel all changes
	 */
	public void saveCancelAnswerForm(Boolean isSave){
		if(isSave){
			info("Click on Save button");
			click(ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		}else{
			info("Click on Cancel button");
			click(ELEMENT_ANSWER_FORM_CANCEL_BUTTON);
		}
		waitForElementNotPresent(ELEMENT_ANSWER_FORM);
		info("Answer form is closed");
	}
	/**
	 * Verify the answer that is added
	 */
	public void verifyAnswerAdded(String answer){
		info("Verify that the answer is added successfully");
		waitForAndGetElement(ELEMENT_ANSWER_CONTENT.replace("$answer",answer));
		info("The answer is added successfully");
	}
}
