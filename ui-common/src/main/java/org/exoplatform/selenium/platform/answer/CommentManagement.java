package org.exoplatform.selenium.platform.answer;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommentManagement extends AnswerLocator {

	AnswerHomePage aHome;
	AnswerManagement aMang;

	/**
	 * constructor
	 * @param dr
	 */
	public CommentManagement(WebDriver dr){
		this.driver=dr;
		aHome = new AnswerHomePage(dr);
		aMang= new AnswerManagement(dr);
	}

	/**
	 * Go to COMMENT a question
	 */
	public void goToCommentQuestion(String question){
		info("Go to COMMENT a question");
		if(waitForAndGetElement(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question),5000,0)!=null)
			click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question)));
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_SELECTED_ITEM.replace("$question", question)));
		click(ELEMENT_COMMENT_BUTTON);
		waitForAndGetElement(ELEMENT_COMMENT_FORM);
	}

	/**
	 * Input data to COMMENT form
	 * @param content
	 * @param isApprove
	 * @param isActive
	 * @param related
	 */
	public void inputDataToComment(String content){
		info("Input data to COMMENT form");
		if(content!=null && content!=""){
			info("input content");
			inputDataToCKEditor(ELEMENT_COMMENT_FORM_DATA_FRAME_INPUT,content);
		}
	}

	/**
	 * action menu of comment
	 */
	public enum actionCommentOption{
		EDIT, PROMOTE, DELETE
	}

	/**
	 * Execute action of COMMENT: EDIT, APPROVE, DISAPPROVE, ACTIVE, DEACTIVE, DELETE
	 * @param COMMENT
	 * @param action
	 * 				action that needs to be done
	 */
	public void goToActionOfCommentFromMoreAction(String comment, actionCommentOption action){
		info("Select action from menu");
		click(ELEMENT_COMMENT_MORE_ACTION_BUTTON.replace("$comment", comment));
		switch(action){
		case EDIT:
			info("EDIT COMMENT");
			click(ELEMENT_COMMENT_EDIT_BUTTON.replace("$comment", comment));
			waitForAndGetElement(ELEMENT_COMMENT_FORM);
			break;
		case PROMOTE:
			info("PROMOTE COMMENT");
			click(ELEMENT_COMMENT_PROMOTE_TO_ANSWER_BUTTON.replace("$comment", comment));
			waitForAndGetElement(aMang.ELEMENT_ANSWER_CONTENT.replace("$answer", comment));
			break;
		case DELETE:
			info("DELETE COMMENT");
			waitForAndGetElement(ELEMENT_COMMENT_DELETE_BUTTON, DEFAULT_TIMEOUT, 1);
			click(ELEMENT_COMMENT_DELETE_BUTTON.replace("$comment", comment));
			waitForAndGetElement(ELEMENT_COMMENT_DELETE_CONFIRM_POPUP);
			break;
		default:
			info("Do nothing");
			break;
		}
	}

	/**
	 * Delete comment
	 * @param comment
	 */
	public void deleteComment(String comment){
		info("Delete COMMENT");
		goToActionOfCommentFromMoreAction(comment, actionCommentOption.DELETE);
		waitForAndGetElement(ELEMENT_COMMENT_CONFIRM_DELETE);
		click(ELEMENT_COMMENT_DELETE_FORM_OK_BUTTON);
		waitForElementNotPresent(ELEMENT_COMMENT_CONTENT.replace("$comment", comment));
	}
}

