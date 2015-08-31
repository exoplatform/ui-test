package org.exoplatform.selenium.platform.answer;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.exoplatform.selenium.TestLogger.info;
public class QuestionManagement extends AnswerLocator {

	Button button;
	/**
	 * constructor
	 * @param dr
	 */
	public QuestionManagement(WebDriver dr){
		this.driver=dr;
		button = new Button(driver);
	}

	/**
	 * Go to mange question form
	 */
	public void goToManageQuestionForm(){
		info("Go to mange question");
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_MANAGE_QUESTION_BUTTON, DEFAULT_TIMEOUT, 1);
		click(ELEMENT_MANAGE_QUESTION_BUTTON);
		waitForAndGetElement(ELEMENT_MANAGE_QUESTION_FORM);
	}

	/**
	 * goToSubmitQuestion
	 */
	public void goToSubmitQuestion(){
		info("Open submin question form");
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_SUBMIT_QUESTION, DEFAULT_TIMEOUT, 1);
		clickByJavascript(ELEMENT_SUBMIT_QUESTION);
		waitForAndGetElement(ELEMENT_SUBMIT_QUESTION_FORM);
	}

	/**
	 * input data to question form
	 * @param title
	 * @param content
	 * @param language
	 * @param pathFile
	 */
	public void inputDataToQuestionForm(String title, String content, String language, String pathFile){
		if(title!=null && title!=""){
			info("input title");
			type(ELEMENT_SUBMIT_QUESTION_FORM_TITLE_INPUT,title,true);
		}
		
		if(content!=null && content!=""){
			info("input content");
			inputDataToCKEditor(ELEMENT_SUBMIT_QUESTION_FORM_DATA_FRAME_INPUT,content);
		}
		
		if(language!=null && language!=""){
			info("input language");
			select(ELEMENT_SUBMIT_QUESTION_FORM_LANGUAGE_SELECT_BOX,language);
		}
		
		if(pathFile!=null && pathFile!=""){
			info("input pathFile");
			String[] links = pathFile.split("/");
			click(ELEMENT_SUBMIT_QUESTION_FORM_ATTACHMENT_BUTTON);
			WebElement eFile = waitForAndGetElement(ELEMENT_QUESTION_FILE_INPUT,DEFAULT_TIMEOUT,1,2);
			((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';",eFile);
			eFile.sendKeys(getAbsoluteFilePath(pathFile));
			waitForAndGetElement(ELEMENT_ATTACHMENT_FORM_FILE_NAME.replace("$fileName", links[links.length-1]));
			click(ELEMENT_ATTACH_SAVE_BUTTON);
			waitForAndGetElement(ELEMENT_ATTACH_FILE_NAME.replace("$fileName", links[links.length-1]));
			switchToParentWindow();
		}
		Utils.pause(2000);
	}

	/**
	 * action menu of question
	 */
	public enum actionQuestionOption{
		COMMENT, ANSWER, EDIT, DELETE, MOVE, SEND, PRINT
	}

	/**
	 * Execute action of question: COMMENT, ANSWER, EDIT, DELETE, MOVE, SEND
	 * @param question
	 * @param action
	 * 				action that needs to be done
	 */
	public void goToActionOfQuestionByRightClick(String question, actionQuestionOption action){
		info("Select action from menu");
		Utils.pause(3000);
		rightClickOnElement(ELEMENT_QUESTION_LIST_ITEM.replace("$question", question));
		switch(action){
		case COMMENT:
			info("Comment question");
			click(ELEMENT_QUESTION_COMMENT.replace("$question", question));
			waitForAndGetElement(ELEMENT_QUESTION_COMMENT_FORM);
			break;
		case ANSWER:
			info("Answer question");
			click(ELEMENT_QUESTION_ANSWER.replace("$question", question));
			waitForAndGetElement(ELEMENT_QUESTION_ANSWER_FORM);
			break;
		case EDIT:
			info("Edit question");
			click(ELEMENT_QUESTION_EDIT.replace("$question", question));
			waitForAndGetElement(ELEMENT_QUESTION_EDIT_FORM);
			break;
		case DELETE:
			info("Delete question");
			click(ELEMENT_QUESTION_DELETE.replace("$question", question));
			waitForAndGetElement(ELEMENT_QUESTION_DELETE_FORM);
			break;
		case MOVE:
			info("Move question");
			click(ELEMENT_QUESTION_MOVE.replace("$question", question));
			waitForAndGetElement(ELEMENT_QUESTION_MOVE_FORM);
			break;
		case SEND:
			info("Send question");
			click(ELEMENT_QUESTION_SEND.replace("$question", question));
			waitForAndGetElement(ELEMENT_QUESTION_SEND_FORM);
			break;
		default:
			info("Do nothing");
			break;
		}
	}

	/**
	 * Execute action of question: PRINT, EDIT, DELETE, MOVE, SEND
	 * @param action
	 * 				action that needs to be done
	 */
	public void goToActionOfQuestionFromMoreAction(actionQuestionOption action){
		info("Select action from menu");
		click(ELEMENT_QUESTION_MORE_ACTION_BUTTON);
		switch(action){
		case PRINT:
			info("PRINT question");
			click(ELEMENT_QUESTION_PRINT_BUTTON);
			break;
		case EDIT:
			info("EDIT question");
			click(ELEMENT_QUESTION_EDIT_BUTTON);
			waitForAndGetElement(ELEMENT_QUESTION_EDIT_FORM);
			break;
		case DELETE:
			info("DELETE question");
			click(ELEMENT_QUESTION_DELETE_BUTTON);
			waitForAndGetElement(ELEMENT_QUESTION_DELETE_FORM);
			break;
		case MOVE:
			info("MOVE question");
			click(ELEMENT_QUESTION_MOVE_BUTTON);
			waitForAndGetElement(ELEMENT_QUESTION_MOVE_FORM);
			break;
		case SEND:
			info("SEND question");
			click(ELEMENT_QUESTION_SEND_BUTTON);
			waitForAndGetElement(ELEMENT_QUESTION_SEND_FORM);
			break;
		default:
			info("Do nothing");
			break;
		}
	}

	/**
	 * Delete question
	 * @param question
	 */
	public void deleteQuestion(String question){
		info("Delete question");
		goToActionOfQuestionByRightClick(question, actionQuestionOption.DELETE);
		waitForAndGetElement(ELEMENT_QUESTION_CONFIRM_DELETE);
		clickByJavascript(ELEMENT_QUESTION_DELETE_FORM_OK_BUTTON, 2);
		waitForElementNotPresent(ELEMENT_QUESTION_LIST_ITEM.replace("$question", question));
	}

	/**
	 * Cancel to Delete question
	 * @param question
	 */
	public void cancelDeleteQuestion(String question){
		info("Delete question");
		goToActionOfQuestionByRightClick(question, actionQuestionOption.DELETE);
		waitForAndGetElement(ELEMENT_QUESTION_CONFIRM_DELETE);
		click(ELEMENT_QUESTION_DELETE_FORM_CANCEL_BUTTON);
		waitForAndGetElement(ELEMENT_QUESTION_LIST_ITEM.replace("$question", question));
	}

	/**
	 * Go to edit question from manage question form
	 * @param question
	 */
	public void goToEditQuestionFromManageQuestionForm(String question){
		info("Go to edit question from manage question form");
		if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
			click(ELEMENT_ANY_PAGE.replace("$page", "1"));
			while((waitForAndGetElement(ELEMENT_MANAGE_QUESTION_EDIT_QUESTION.replace("$question", question),5000,0)==null)
					&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
				click(ELEMENT_NEXT_PAGE);
		}
		click(ELEMENT_MANAGE_QUESTION_EDIT_QUESTION.replace("$question", question));
		waitForAndGetElement(ELEMENT_QUESTION_EDIT_FORM);
	}

	/**
	 * Go to delete question from manage question form
	 * @param question
	 */
	public void goToDeleteQuestionFromManageQuestionForm(String question){
		info("Go to delete question from manage question form");
		if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
			click(ELEMENT_ANY_PAGE.replace("$page", "1"));
			while((waitForAndGetElement(ELEMENT_MANAGE_QUESTION_DELETE_QUESTION.replace("$question", question),5000,0)==null)
					&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
				click(ELEMENT_NEXT_PAGE);
		}
		click(ELEMENT_MANAGE_QUESTION_DELETE_QUESTION.replace("$question", question));
		waitForAndGetElement(ELEMENT_QUESTION_DELETE_FORM);
	}

	/**
	 * Approve question or not from manage question form
	 * @param question
	 * @param isApprove
	 * 					true: check to approve
	 * 					false: uncheck to un-approve
	 */
	public void approveQuestionFromManageQuestionForm(String question, Boolean isApprove){
		if(isApprove){
			info("Approve question");
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while((waitForAndGetElement(ELEMENT_MANAGE_QUESTION_APPROVE_QUESTION_CHECKBOX.replace("$question", question),5000,0,2)==null)
						&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
					click(ELEMENT_NEXT_PAGE);
			}
			check(By.xpath(ELEMENT_MANAGE_QUESTION_APPROVE_QUESTION_CHECKBOX.replace("$question", question)),2);
		}
		else{
			info("Dis-approve question");
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while((waitForAndGetElement(ELEMENT_MANAGE_QUESTION_APPROVE_QUESTION_CHECKBOX.replace("$question", question),5000,0,2)==null)
						&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
					click(ELEMENT_NEXT_PAGE);
			}
			uncheck(By.xpath(ELEMENT_MANAGE_QUESTION_APPROVE_QUESTION_CHECKBOX.replace("$question", question)),2);
		}
	}

	/**
	 * Active question or not from manage question form
	 * @param question
	 * @param isActive
	 * 					true: check to active
	 * 					false: uncheck to un-active
	 */
	public void activeQuestionFromManageQuestionForm(String question, Boolean isActive){
		if(isActive){
			info("Active question");
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while((waitForAndGetElement(ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX.replace("$question", question),5000,0,2)==null)
						&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
					click(ELEMENT_NEXT_PAGE);
			}
			check(By.xpath(ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX.replace("$question", question)),2);
		}
		else{
			info("Un-active question");
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while((waitForAndGetElement(ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX.replace("$question", question),5000,0,2)==null)
						&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
					click(ELEMENT_NEXT_PAGE);
			}
			uncheck(By.xpath(ELEMENT_MANAGE_QUESTION_ACTIVE_QUESTION_CHECKBOX.replace("$question", question)),2);
		}
	}

	/**
	 * Approve question or not
	 * @param isApprove
	 * 					true: check to approve
	 * 					false: uncheck to un-approve
	 */
	public void approveQuestion(Boolean isApprove){
		if(isApprove){
			info("Approve question");
			check(ELEMENT_SUBMIET_QUESTION_APPROVE_CHECKBOX,2);
		}
		else{
			info("Dis-approve question");
			uncheck(ELEMENT_SUBMIET_QUESTION_APPROVE_CHECKBOX,2);
		}
	}

	/**
	 * Active question or not
	 * @param isActive
	 * 					true: check to active
	 * 					false: uncheck to un-active
	 */
	public void activeQuestion(Boolean isActive){
		if(isActive){
			info("Active question");
			check(ELEMENT_SUBMIET_QUESTION_ACTIVE_CHECKBOX,2);
		}
		else{
			info("Un-active question");
			uncheck(ELEMENT_SUBMIET_QUESTION_ACTIVE_CHECKBOX,2);
		}
	}

	/**
	 * Rate question
	 * @param number
	 * 				number of rating
	 */
	public void rateQuestion(int number){
		info("Rate a question");
		String st = Integer.toString(number);
		if(number!=0){
			WebElement e1 = waitForAndGetElement(By.xpath(ELEMENT_QUESTION_RATE_ITEM.replace("$index", st)),DEFAULT_TIMEOUT,1,2);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", e1);
			waitForAndGetElement(ELEMENT_QUESTION_RATE_NUMBER.replace("$index", st));
		}
	}
	/**
	 * Save or cancel all change of submit question form
	 * @param isSave
	 *               = true if want to save all changes
	 *               = false if want to cancel all changes
	 */
	public void saveCancelSubmitQuestion(Boolean isSave){
		if(isSave){
			info("Click on Save button");
			click(ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
			button.ok();
		}else{
			info("Click on Cancel button");
			click(ELEMENT_SUBMIT_QUESTION_FORM_CANCEL_BUTTON);
		}
		waitForElementNotPresent(ELEMENT_SUBMIT_QUESTION_FORM);
		info("Submint question form is closed");
	}
	/**
	 * Open Answer form
	 * @param question
	 *                is the question that want to answer
	 */
	public void goToAnswerForm(String question){
	    if(question!="" || question!=null){
	    	info("Click on Answer link");
	    	click(ELEMENT_QUESTION_ANSWER_LINK.replace("$question",question));
	    	info("Respone form is shown");
	    	waitForAndGetElement(ELEMENT_QUESTION_RESPONE_FORM);
	    	info("Answer form is shown successfully");
	    }
	}
	/**
	 * Check manage question form
	 * @param isDisplay
	 * @param ques
	 * 			  list of question
	 */
	public void checkMngQuesForm(boolean isDisplay,String...ques){
		if(isDisplay){
			waitForAndGetElement(ELEMENT_MANAGE_QUESTION_BUTTON);
			click(ELEMENT_MANAGE_QUESTION_BUTTON,0,true);
			for (String q : ques) {
				waitForAndGetElement(ELEMENT_MANAGE_QUESTION_EDIT_QUESTION.replace("$question", q));
				waitForAndGetElement(ELEMENT_MANAGE_QUESTION_DELETE_QUESTION.replace("$question", q));
			}
		}else
			waitForElementNotPresent(ELEMENT_MANAGE_QUESTION_BUTTON);
	}
	
}
