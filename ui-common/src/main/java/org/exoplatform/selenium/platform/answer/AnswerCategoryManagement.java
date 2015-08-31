package org.exoplatform.selenium.platform.answer;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.PlatformPermission;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AnswerCategoryManagement extends PlatformBase {
	AnswerHomePage aHome;
	QuestionManagement qMang;
	ManageAlert alert;
	Button button;
	PlatformPermission plfPerm;
	
	//Action of category from action bar
	public By ELEMENT_CATEGORY_EDIT_BUTTON=By.xpath("//*[@class='uiIconEditCategory']");
	public By ELEMENT_CATEGORY_ADD_BUTTON=By.xpath("//*[@class='uiIconAddCategory']");
	public By ELEMENT_CATEGORY_IMPORT_BUTTON=By.xpath("//*[@class='uiIconImport']");
	public By ELEMENT_CATEGORY_EXPORT_BUTTON=By.xpath("//*[@class='uiIconExport']");
	public By ELEMENT_CATEGORY_DELETE_BUTTON=By.xpath("//*[@class='uiIconDeleteCategory']");

	//Action of category from right click
	public By ELEMENT_CATEGORY_RIGHT_EDIT_BUTTON=By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconEditCategory']");
	public By ELEMENT_CATEGORY_RIGHT_ADD_BUTTON=By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconAddCategory']");
	public By ELEMENT_CATEGORY_RIGHT_IMPORT_BUTTON=By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[contains(@class,'faqCategory oncontextmenu') and not (contains(@style,'display: block'))]//*[@class='uiIconLightGray uiIconImport']");
	public By ELEMENT_CATEGORY_RIGHT_EXPORT_BUTTON=By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconExport']");
	public By ELEMENT_CATEGORY_RIGHT_DELETE_BUTTON=By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconDeleteCategory']");
	public By ELEMENT_CATEGORY_RIGHT_MOVE_BUTTON=By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconMoveCategory']");
	public By ELEMENT_CATEGORY_RIGHT_WATCH_BUTTON=By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconWatch']");
	public By ELEMENT_CATEGORY_WATCH_OK_BUTTON=By.xpath("//*[contains(text(),'OK') and contains(@onclick,'answers')]");
	public By ELEMENT_CATEGORY_RIGHT_UNWATCH_BUTTON=By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconLightGray uiIconUnWatch']");
	public By ELEMENT_CATEGORY_RIGHT_RSS_BUTTON=By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconRss uiIconLightGray']");
	public By ELEMENT_CATEGORY_RIGHT_SUBMIT_QUESTION_BUTTON=By.xpath("//*[contains(@class,'faqCategory oncontextmenu') and contains(@style,'display: block')]//*[@class='uiIconAnsLightGray uiIconAnsAddNewQuestion']");

	//Category list
	public String ELEMENT_CATEGORY_LIST_ITEM="//*[contains(@class,'faqCategory oncontextmenu')]//*[contains(.,'$category')]";
	public By ELEMENT_CATEGORY_UP_LEVEL=By.xpath("//*[@class='uiIconUpLevel uiIconLightGray']");

	//Edit category form
	public By ELEMENT_CATEGORY_EDIT_FORM=By.id("EditCategoryForm");

	//Add category form
	public By ELEMENT_CATEGORY_ADD_FORM=By.id("SubCategoryForm");
	public By ELEMENT_CATEGORY_ADD_CATEGORY_INPUT=By.id("eventCategoryName");
	public By ELEMENT_CATEGORY_ADD_ORDER_INPUT=By.id("index");
	public By ELEMENT_CATEGORY_ADD_DESCRIPTION_INPUT=By.id("description");
	public By ELEMENT_CATEGORY_ADD_MOD_QUES_CHECKBOX=By.id("moderatequestions");
	public By ELEMENT_CATEGORY_ADD_MOD_VIEW_CHECKBOX=By.id("ViewAuthorInfor");
	public By ELEMENT_CATEGORY_ADD_MOD_ANS_CHECKBOX=By.id("moderateAnswers");	
	public By ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON=By.xpath("//*[@id='UICategoryForm']//*[text()='Save']");
	public By ELEMENT_CATEGORY_ADD_FORM_CANCEL_BUTTON=By.xpath("//*[@id='UICategoryForm']//*[text()='Save']");

	//Export category form
	public By ELEMENT_CATEGORY_EXPORT_FORM=By.id("FAQExportForm");
	public final By ELEMENT_FILE_NAME_EXPORT = By.id("FileName");

	//Import category form
	public By ELEMENT_CATEGORY_IMPORT_FORM=By.id("FAQImportForm");
	public By ELEMENT_IMPORT_CATEGORY_INPUT = By.name("file");
	public String ELEMENT_ATTACHMENT_FORM_FILE_NAME = "//*[text()='$fileName']";
	public String ELEMENT_EVENT_ATTACHMENT = "//*[@id='UIEventForm']/..//a[@data-original-title='${file}']";
	public By ELEMENT_ATTACHMENT_SAVE_BUTTON = By.xpath("//*[@id='FAQImportForm']//*[text()='Save']");
	public String ELEMENT_IMPORT_SUCCESS_MESSAGE = "The file has been imported";
	public By ELEMENT_CATEGORY_OK_BUTTON=By.xpath("//*[contains(@class,'UIPopupWindow')]//a[text()='OK']");

	//Delete category
	public By ELEMENT_CATEGORY_DELETE_CONFIRM_POPUP=By.id("UIForumPopupConfirmation");
	public String ELEMENT_CATEGORY_DELETE_CONFIRM_MSG="Are you sure you want to delete this category ?";
	public By ELEMENT_CATEGORY_DELETE_CONFIRM=By.xpath("//*[@id='UIForumPopupConfirmation']//*[contains(@class, 'confirmationIcon')]");
	public By ELEMENT_CATEGORY_DELETE_OK_BUTTON=By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='OK']");
	public By ELEMENT_CATEGORY_DELETE_CANCEL_BUTTON=By.xpath("//*[@id='UIForumPopupConfirmation']//*[text()='Cancel']");

	//Move category
	public By ELEMENT_CATEGORY_MOVE_FORM=By.id("MoveCategoryForm");
	public String ELEMENT_CATEGORY_MOVE_TARGET_ITEM="//*[@class='uiIconCategory uiIconLightGray']/../..//*[contains(.,'$category')]";

	//Permission tab
	public By ELEMENT_CATEGORY_EDIT_PERM_TAB = By.xpath("//*[contains(@data-target,'PermissionTab')]");
	public final String ELEMENT_MANAGE_QUESTION_PERM_RESTRICTED = "//*[contains(.,'$group')]/../td[2]/*[@class='uiCheckbox']/input[@type='checkbox']";
	public final String ELEMENT_MANAGE_QUESTION_PERM_MODERATOR = "//*[contains(.,'$group')]/../td[3]/*[@class='uiCheckbox']/input[@type='checkbox']";
	public final By ELEMENT_SELECT_MEMBERSHIP_ICON = By.xpath("//*[@class='uiIconMembership uiIconLightGray']");
	public final String ELEMENT_SELECT_RIGHT_PARENT_GROUP = "//*[contains(@id,'UIMemberShipSelector')]//a[contains(.,'$group')]";
	public final By ELEMENT_SELECT_MEMBERSHIP_POPUP = By.xpath("//*[contains(@id,'UIPopupWindow')]");
	
	//Permission
	public By ELEMENT_CATEGORY_TAB_PERMISSIONS = By.xpath("//*[contains(@data-toggle,'tab')][contains(.,'Permissions')]");
	public By ELEMENT_CATEGORY_USER_ICON_SELECTOR = By.xpath("//*[contains(@class,'uiIconUser')]");
	public String ELEMENT_CATEGORY_LIST_USER = ".//*[@id='UIListUsers']/*[contains(.,'$user')]";
	/**
	 * constructor
	 * @param dr
	 */
	public AnswerCategoryManagement(WebDriver dr){
		this.driver=dr;
		aHome = new AnswerHomePage(dr);
		alert = new ManageAlert(driver);
		button = new Button(driver);
		qMang = new QuestionManagement(driver);
		plfPerm =  new PlatformPermission(driver);
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
