package org.exoplatform.selenium.platform.answer;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AnswerCategoryManagement extends AnswerLocator {
	AnswerHomePage aHome;
	QuestionManagement qMang;
	ManageAlert alert;
	Button button;
	PlatformPermission plfPerm;
	public AnswerCategoryManagement(WebDriver dr){
		this.driver=dr;
		aHome = new AnswerHomePage(dr);
		alert = new ManageAlert(dr);
		button = new Button(dr);
		qMang = new QuestionManagement(dr);
		plfPerm =  new PlatformPermission(dr);
	}
	
	/**
	 * action menu of category
	 */
	public enum actionCategoryOption{
		EDIT, ADD, EXPORT, IMPORT, DELETE, MOVE, WATCH, UNWATCH, RSS, SUBMITQUESTION
	}

	/**
	 * Execute action of category from action bar: EDIT, ADD, EXPORT, IMPORT
	 * @param action
	 * 				action that needs to be done
	 */
	public void goToActionOfCategoryFromActionBar(actionCategoryOption action){
		info("Select action from menu");
		Utils.pause(2000);
		waitForAndGetElement(aHome.ELEMENT_CATEGORY_BUTTON, DEFAULT_TIMEOUT, 1);
		click(aHome.ELEMENT_CATEGORY_BUTTON);
		switch(action){
		case EDIT:
			info("Edit category");
			waitForAndGetElement(ELEMENT_CATEGORY_EDIT_BUTTON, DEFAULT_TIMEOUT, 1);
			click(ELEMENT_CATEGORY_EDIT_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_EDIT_FORM);
			break;
		case ADD:
			info("ADD category");
			waitForAndGetElement(ELEMENT_CATEGORY_ADD_BUTTON, DEFAULT_TIMEOUT, 1);
			click(ELEMENT_CATEGORY_ADD_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_ADD_FORM);
			break;
		case EXPORT:
			info("EXPORT category");
			waitForAndGetElement(ELEMENT_CATEGORY_EXPORT_BUTTON, DEFAULT_TIMEOUT, 1);
			click(ELEMENT_CATEGORY_EXPORT_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_EXPORT_FORM);
			break;
		case IMPORT:
			info("IMPORT category");
			waitForAndGetElement(ELEMENT_CATEGORY_IMPORT_BUTTON, DEFAULT_TIMEOUT, 1);
			click(ELEMENT_CATEGORY_IMPORT_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_IMPORT_FORM);
			break;
		case DELETE:
			info("DELETE category");
			waitForAndGetElement(ELEMENT_CATEGORY_DELETE_BUTTON, DEFAULT_TIMEOUT, 1);
			click(ELEMENT_CATEGORY_DELETE_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_DELETE_CONFIRM_POPUP);
			break;
		default:
			info("Do nothing");
			break;
		}
	}

	/**
	 * Execute action of category from right click: EDIT, ADD, EXPORT, IMPORT, DELETE, MOVE, WATCH, RSS, SUBMITQUESTION
	 * @param cat
	 * 				name of category
	 * @param action
	 * 				action that needs to be done
	 */
	public void goToActionOfCategoryFromRightClick(String cat, actionCategoryOption action){
		info("Select action from menu");
		rightClickOnElement(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
		switch(action){
		case EDIT:
			info("Edit category");
			click(ELEMENT_CATEGORY_RIGHT_EDIT_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_EDIT_FORM);
			break;
		case ADD:
			info("ADD category");
			click(ELEMENT_CATEGORY_RIGHT_ADD_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_ADD_FORM);
			break;
		case EXPORT:
			info("EXPORT category");
			click(ELEMENT_CATEGORY_RIGHT_EXPORT_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_EXPORT_FORM);
			break;
		case IMPORT:
			info("IMPORT category");
			click(ELEMENT_CATEGORY_RIGHT_IMPORT_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_IMPORT_FORM);
			break;
		case DELETE:
			info("DELETE category");
			click(ELEMENT_CATEGORY_RIGHT_DELETE_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_DELETE_CONFIRM_POPUP);
			break;
		case MOVE:
			info("MOVE category");
			click(ELEMENT_CATEGORY_RIGHT_MOVE_BUTTON);
			waitForAndGetElement(ELEMENT_CATEGORY_MOVE_FORM);
			break;
		case WATCH:
			info("WATCH category");
			click(ELEMENT_CATEGORY_RIGHT_WATCH_BUTTON);
			click(ELEMENT_CATEGORY_WATCH_OK_BUTTON);
			break;
		case UNWATCH:
			info("UNWATCH category");
			click(ELEMENT_CATEGORY_RIGHT_UNWATCH_BUTTON);
			break;
		case SUBMITQUESTION:
			info("SUBMITQUESTION category");
			click(ELEMENT_CATEGORY_RIGHT_SUBMIT_QUESTION_BUTTON);
			waitForAndGetElement(qMang.ELEMENT_SUBMIT_QUESTION_FORM);
			break;
		case RSS:
			info("RSS category");
			click(ELEMENT_CATEGORY_RIGHT_RSS_BUTTON);
			break;
		default:
			info("Do nothing");
			break;
		}
	}


	/**
	 * Input data to setting tab of category form
	 * @param cat
	 * 				category name
	 * @param order
	 * 				order of category
	 * @param des
	 * 				description of category
	 * @param modQues
	 * 				true: check Moderate New Questions
	 * 				false: uncheck Moderate New Questions
	 * @param viewAuthor
	 * 				true: check View Question Authors
	 * 				false: uncheck View Question Authors
	 * @param modAnswer
	 * 				true: check Moderate Answers
	 * 				false: uncheck Moderate Answers
	 */
	public void inputDataToSettingTab(String cat, String order, String des, Boolean modQues, Boolean viewAuthor, Boolean modAnswer){
		info("Input data to setting tab of category form");
		info("input category name");
		if(cat!=null && cat!=""){
			type(ELEMENT_CATEGORY_ADD_CATEGORY_INPUT,cat,true);
		}
		info("input category order");
		if(order!=null && order!=""){
			type(ELEMENT_CATEGORY_ADD_ORDER_INPUT,cat,true);
		}
		info("input category des");
		if(des!=null && des!=""){
			type(ELEMENT_CATEGORY_ADD_DESCRIPTION_INPUT,cat,true);
		}
		info("input category moderator question");
		if(modQues!=null){
			if(modQues)
				check(ELEMENT_CATEGORY_ADD_MOD_QUES_CHECKBOX,2);
			else
				uncheck(ELEMENT_CATEGORY_ADD_MOD_QUES_CHECKBOX,2);
		}
		info("input category viewAuthor");
		if(viewAuthor!=null){
			if(viewAuthor)
				check(ELEMENT_CATEGORY_ADD_MOD_VIEW_CHECKBOX,2);
			else
				uncheck(ELEMENT_CATEGORY_ADD_MOD_VIEW_CHECKBOX,2);
		}
		info("input category modAnswer");
		if(modAnswer!=null){
			if(modAnswer)
				check(ELEMENT_CATEGORY_ADD_MOD_ANS_CHECKBOX,2);
			else
				uncheck(ELEMENT_CATEGORY_ADD_MOD_ANS_CHECKBOX,2);
		}
	}

	/**
	 * delete Category
	 * @param cat
	 */
	public void deleteCategory(String cat){
		info("Delete category");
		goToActionOfCategoryFromActionBar(actionCategoryOption.DELETE);
		assert getText(ELEMENT_CATEGORY_DELETE_CONFIRM).contains(ELEMENT_CATEGORY_DELETE_CONFIRM_MSG):"Message is wrong. Actual msg is "+getText(ELEMENT_CATEGORY_DELETE_CONFIRM);
		click(ELEMENT_CATEGORY_DELETE_OK_BUTTON);
		waitForElementNotPresent(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
	}

	/**
	 * move Category to target
	 * @param target
	 * 				target category (Ex: cat1/cat2/cat3...)
	 */
	public void moveCategory(String target){
		info("Move category");
		String[] nodes = target.split("/");
		if(nodes.length>1){
			for(int i = 0; i<nodes.length-2; i++){
				click(ELEMENT_CATEGORY_MOVE_TARGET_ITEM.replace("$category", nodes[i]));
			}
		}
		doubleClickOnElement(ELEMENT_CATEGORY_MOVE_TARGET_ITEM.replace("$category", nodes[nodes.length-1]));
	}
	
	/**
	 * function export category in answer
	 * @param fileName
	 */
	public void exportAnswerCategory(String fileName){
		info("Export category to file " + fileName);
		goToActionOfCategoryFromActionBar(actionCategoryOption.EXPORT);
		type(ELEMENT_FILE_NAME_EXPORT, fileName, true);
		button.save();
	}

	/**
	 * function import category in answer
	 * @param path
	 */
	public void importAnswerCategory(String path){
		info("Import category from file " + path);
		String[] links = path.split("/");
		goToActionOfCategoryFromActionBar(actionCategoryOption.IMPORT);
		WebElement eFile = waitForAndGetElement(ELEMENT_IMPORT_CATEGORY_INPUT,DEFAULT_TIMEOUT,1,2);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';",eFile);
		eFile.sendKeys(getAbsoluteFilePath(path));
		waitForAndGetElement(ELEMENT_ATTACHMENT_FORM_FILE_NAME.replace("$fileName", links[links.length-1]));
		switchToParentWindow();
		click(ELEMENT_ATTACHMENT_SAVE_BUTTON);
		alert.verifyAlertMessage(ELEMENT_IMPORT_SUCCESS_MESSAGE);
		click(ELEMENT_CATEGORY_OK_BUTTON);
	}
	/**
<<<<<<< HEAD
	 * Set permission
	 * @param cat
	 * @param group
	 * @param isRestriected
	 * @param isMod
	 */
	public void setPermission(String cat,String group,boolean isRestricted,boolean isMod){
 	 	goToActionOfCategoryFromActionBar(actionCategoryOption.EDIT);
 	 	click(ELEMENT_CATEGORY_EDIT_PERM_TAB,0,true);
 	 	selectGroupMembership(group,"*");
 	 	click(plfPerm.ELEMENT_ADD_USERS_BUTTON);
 	 	if(isRestricted)
 	 		check(By.xpath(ELEMENT_MANAGE_QUESTION_PERM_RESTRICTED.replace("$group",group)),2);
 	 	if(isMod)
 	 		check(By.xpath(ELEMENT_MANAGE_QUESTION_PERM_MODERATOR.replace("$group", group)),2);
		click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
	}
	/**
	 * Select group membership
	 */
	public void selectGroupMembership(String groupPath,String mem){
		info("select group membership");
		String[] temp;	
		click(ELEMENT_SELECT_MEMBERSHIP_ICON);
		waitForAndGetElement(ELEMENT_SELECT_MEMBERSHIP_POPUP);
		temp = groupPath.split("/");
		for (int i = 0; i < temp.length; i ++){
			click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
		}
		click(ELEMENT_SELECT_RIGHT_PARENT_GROUP.replace("$group", mem));
		waitForElementNotPresent(ELEMENT_SELECT_MEMBERSHIP_POPUP);
	}
	/**
	 * create category
	 * @param cat
	 */
	public void createCategory(String cat){
		goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
 	 	inputDataToSettingTab(cat, null, cat, null, null, null);
 	 	click(ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
 	 	waitForAndGetElement(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
	}
	/**
	 * check accessibility of category
	 * @param cat
	 * @param isAccess
	 * @param ques
	 */
	public void checkAccessibilityOfCat(String cat,boolean isAccess,String...ques){
		if(isAccess){
			click(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat),0,true);
			for (String q : ques) {
				waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", q)));
			}
		}else
			waitForElementNotPresent(ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
	}
	/**
	 * Check user list
	 * @param user
	 * @param isPresent
	 */
	public void checkPermUserList(String user,boolean isPresent){
		goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		click(ELEMENT_CATEGORY_TAB_PERMISSIONS,0,true);
		Utils.pause(500);
		click(ELEMENT_CATEGORY_USER_ICON_SELECTOR,0,true);
		if(isPresent){
			waitForAndGetElement(ELEMENT_CATEGORY_LIST_USER.replace("$user", user));
		}else{
			waitForElementNotPresent(ELEMENT_CATEGORY_LIST_USER.replace("$user", user));
		}
	}
}
