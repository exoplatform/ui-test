package org.exoplatform.selenium.platform.forum;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;

import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;

public class ForumCategoryManagement extends ForumLocator {
	
	ManageAlert alert;
	Button button;
	ForumPermission forumPerm;
	/**
	 * constructor
	 * @param dr
	 */
	public ForumCategoryManagement(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(dr);
		button = new Button(driver);
		forumPerm = new ForumPermission(driver);
	}
	
	
	/**
	 * Add a new category By QuynhPT
	 * @param nameCat
	 *            the title of category
	 * @param order
	 *            the order's number as:0,1,2,3...(0 is default value)
	 * @param description
	 *            the content of description for category
	 */
	public void addCategorySimple(String nameCat, String order, String description) {
		info("Add Category Simple");
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_ACTIONBAR_ADDCATEGORY);
		info("click on Add Category button");
		click(ELEMENT_ACTIONBAR_ADDCATEGORY);
	    info("input the title for the category");
	    Utils.pause(2000);
	    waitForAndGetElement(ELEMENT_ADDCATEGORY_POPUP_TITLE, DEFAULT_TIMEOUT, 1);
	    type(ELEMENT_ADDCATEGORY_POPUP_TITLE,nameCat,true);
	    info("check and input Oder field");
	    if(order!=null && order!=""){
	    	 info("Clear all old order data");
	    	 waitForAndGetElement(ELEMENT_ADDCATEGORY_POPUP_ORDER).clear();
	    	 info("Input new order");
	    	 type(ELEMENT_ADDCATEGORY_POPUP_ORDER,order,true);
	    }
        info("check and input description");
        if (description!=null && description!=""){
	    	info("Clear all old description data");
	    	waitForAndGetElement(ELEMENT_ADDCATEGORY_POPUP_DESCRIPTION).clear();
	    	info("Input new description data");
	    	type(ELEMENT_ADDCATEGORY_POPUP_DESCRIPTION,description,true);
	    }
	    info("Click on Save button");
		click(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON);
		Utils.pause(2000);
		info("Fisnihed adding a category");
	}
	/**
	 * list sublinks in Manage Cagegory menu
	 * @author quynhpt
	 *
	 */
	public enum specifManageCategoryMenu{
		EDIT_CATEGORY,EXPORT_FORUM,IMPORT_FORUM,DELETE,WATCHES,
		ADD_FORUM,EDIT_FORUM,LOCK,UNLOCK,OPEN,CLOSE,MOVE,REMOVE;
	}
	
	/**
	 * select a item in Manage Category Menu
	 * By QuynhPT
	 * @param item
	 */
	public void selectItemManageCategoryMenu(specifManageCategoryMenu item) {
		info("Waiting manage menu is shown");
		waitForAndGetElement(ELEMENT_MENU_MANAGE_CATEGORY);
		info("Click on Manage menu");
		click(ELEMENT_MENU_MANAGE_CATEGORY,0,true);
		Utils.pause(1000);
		switch (item) {
		case EDIT_CATEGORY:
			info("click on Edit link");
			click(ELEMENT_EDIT_CATEGORY);
			Utils.pause(1000);
			break;
		case EXPORT_FORUM:
			info("Click on Export link");
			click(ELEMENT_EXPORT_FORUM);
			Utils.pause(1000);
			break;
		case IMPORT_FORUM:
			info("Click on Import link");
			click(ELEMENT_IMPORT_FORUM);
			Utils.pause(1000);
			break;
		case DELETE:
			info("Click on Delete link");
			click(ELEMENT_DELETE_CATEGORY);
			Utils.pause(1000);
			alert.waitForMessage(ELEMENT_CATEGORY_DELETE_CONFIRM_MSG);
			click(ELEMENT_OK_DELETE);
			break;
		case WATCHES:
			break;
		case ADD_FORUM:
			break;
		case EDIT_FORUM:
			break;
		case LOCK:
			break;
		case UNLOCK:
			break;
		case CLOSE:
			break;
		case OPEN:
			break;
		case MOVE:
			break;
		case REMOVE:
			break;
		}
	}
	 
	/**
	 * Edit a category
	 * @param newName
	 */
	public void editCategory(String newName) {
		selectItemManageCategoryMenu(specifManageCategoryMenu.EDIT_CATEGORY);
		info("Imput a new name");
		type(ELEMENT_ADDCATEGORY_POPUP_TITLE, newName, true);
		info("Save all changes");
		click(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON);
		info("Verify that the name is changed with new name");
		waitForAndGetElement(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", newName));
	}
	
	/**
	 * Delete Category 
	 * By QuynhPT
	 * @param nameCat
	 */
	public void deleteCategory(String nameCat) {
		// TODO Auto-generated method stub
		info("Wait the category is shown");
		waitForAndGetElement(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", nameCat),3000,0);
		info("Click on the category");
		click(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", nameCat));
		info("Select Delete link");
		selectItemManageCategoryMenu(specifManageCategoryMenu.DELETE);
		info("Verify that the category is deleted");
		waitForElementNotPresent(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", nameCat),3000,0);
		info("The category is deleted successfully");
	}
	
	/**
	 * Cancel all changes of Add Category
	 * By QuynhPT
	 */
	public void cancelChangeCategory(){
		waitForAndGetElement(ELEMENT_ADDCATEGORY_POPUP_CANCEL_BUTTON);
		click(ELEMENT_ADDCATEGORY_POPUP_CANCEL_BUTTON);
		Utils.pause(2000);
	}
	
	/**
	 * Export a forum
	 * @param forumName
	 * @param fileName
	 */
	public void exportForum(String forumName, String fileName) {
		selectItemManageCategoryMenu(specifManageCategoryMenu.EXPORT_FORUM);
		info("Uncheck All check boxes");
		uncheck(ELEMENT_EXPORT_FORUM_EXPORTALL, 2);
		info("Select check box of the forum");
		check((ELEMENT_EXPORT_FORUM_EXPORT).replace("${title}", forumName), 2);
		info("input name");
		type(ELEMENT_FILENAME_INPUT,fileName,true);
		info("Save all changes");
		click(ELEMENT_SAVE_BTN);
	}
	
	/**
	 * Import a forum
	 * 
	 * @param folderDowloadFile
	 * @param nameFile
	 */
	public void importForum(String folderDowloadFile, String nameFile) {
		selectItemManageCategoryMenu(specifManageCategoryMenu.IMPORT_FORUM);
		importFile(folderDowloadFile, nameFile);
		button.ok();
	}
	/**
	 * Edit permission of category
	 * @param cat
	 * @param groupPath
	 * @param member
	 */
	public void editPermOfCategory(String cat,String groupPath,String member,boolean isMod,boolean isStartTop,boolean isPostReply,boolean isViewPost){
		info("edit permission of category:"+cat);
		click(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", cat));
		selectItemManageCategoryMenu(specifManageCategoryMenu.EDIT_CATEGORY);
		click(ELEMENT_PERM_TAB,0,true);
		forumPerm.selectPermGroupMember(groupPath, member, isMod, isStartTop, isPostReply, isViewPost);
		click(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON);
	}
	/**
	 * Edit permission of category
	 * @param cat
	 * @param groupPath
	 * @param member
	 */
	public void editRestrictedAudience(String cat,String groupPath,String member){
		info("edit permission of category:"+cat);
		click(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", cat));
		selectItemManageCategoryMenu(specifManageCategoryMenu.EDIT_CATEGORY);
		forumPerm.selectPermGroupMemberRestricted(groupPath, member);
		click(ELEMENT_ADDCATEGORY_POPUP_SAVE_BUTTON);
	}
	/**
	 * Check display of category
	 * @param cat
	 * @param isDisplay
	 */
	public void checkDisplayOfCat(String cat,boolean isDisplay){
		info("check display of category:"+cat);
		if(isDisplay){
			waitForAndGetElement(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", cat));
			click(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", cat),0,true);
		}else{
			waitForElementNotPresent(ELEMENT_FORUM_CATEGORY_HOME_TITLE_LINK.replace("${name}", cat));
		}
	}
	public void gotoUserSelectorForRestrictedAudience(){
		info("-- Go to wiki home page --");
		click(ELEMENT_ADDCATEGORY_RESTRICTED_AUDIENCE);
	}
	
	/**
	 * function: Search user in User Seletion Form in Restricted Audience
	 * 
	 */
	
	public void searchUser(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_RESTRICTED_AUDIENCE_INPUT_SEARCH_USER_NAME, user, true);
		select(ELEMENT_RESTRICTED_AUDIENCE_SELECT_SEARCH_OPTION, searchOption);
		click(ELEMENT_RESTRICTED_AUDIENCE_SEARCH_ICON);
		waitForTextPresent(user);
	}
	
	public void searchUserNotFound(String user, String searchOption) {
		info("--Search user " + user + "--");
		type(ELEMENT_RESTRICTED_AUDIENCE_INPUT_SEARCH_USER_NAME, user, true);
		select(ELEMENT_RESTRICTED_AUDIENCE_SELECT_SEARCH_OPTION, searchOption);
		click(ELEMENT_RESTRICTED_AUDIENCE_SEARCH_ICON);
		waitForTextNotPresent(user);
	}
	
	/**
	 * Close User Selector page
	 */
	public void closeUserSelector(){
		info("-- Go to User Selector page --");
		click(ELEMENT_RESTRICTED_AUDIENCE_CLOSE_USER_SELETOR);
		Utils.pause(2000);
	}
	
	/**
	 * Open permissions tab in Add/Edit Category form
	 */
	public void goToPermissions(){
		info("Permissions page");
		   click(ELEMENT_CATEGORY_PERMISSION_TAB);
	}
	
	/**
	 * Select User in Permission tab
	 * 
	 */
	public void gotoUserSelectorInPermissionTab(){
		info("-- Go to wiki home page --");
		click(ELEMENT_CATEGORY_PERMISSION_TAB_USER_SELECTOR);
	}
	
	/**
	 * Cancel Add/Edit Category form
	 */
	public void cancelAddEditCategory(){
		info("Cancel Add or Edit Category");
		click(ELEMENT_CATEGORY_CANCEL);
	}
}
