package org.exoplatform.selenium.platform.task;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * This class will define actions about management tasks
 *
 */

public class ManagementLabels extends TaskManagementLocatorObject {

	public ManagementLabels(WebDriver dr){
		this.driver=dr;
	}
	/**
	 * Open label
	 */
	public void openLabel(String label){
		info("open label: "+label);
		click(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label", label));
	}
	
	/**
	 * Open Context Menu of Labels by clicking on "+" icon
	 */
	public void goToContMenuLabel(){
		info("Click on + icon to open Context Menu of Labels");
		clickByJavascript(ELEMENT_LEFT_PANE_LABELS_PLUS_MENU,2);
		Utils.pause(1000);
	}
	
	/**
	 * Define options in Context Menu of Labels category
	 */
	public enum optionContMenuLabel{
		Add_Label,Show_Hidden_Label,Hide_Hidden_Label;
	}
	
	/**
	 * Select an option in Context Menu of Labels
	 * @param op
	 *           is an option in Context Menu as:Add Label, Show hidden label.
	 */ 
	public void selectOpContMenuLabel(optionContMenuLabel op){
		goToContMenuLabel();
		switch(op){
		case Add_Label:
			info("Select Add Label option");
			click(ELEMENT_LEFT_PANE_LABELS_ADD,0,true);
			break;
		case Show_Hidden_Label:
			info("Select Show hide Label");
			click(ELEMENT_LEFT_PANE_LABELS_SHOWHIDDEN,0,true);
			break;
		case Hide_Hidden_Label:
			info("Select Hide hide Label");
			click(ELEMENT_LEFT_PANE_LABELS_HIDEHIDDEN,0,true);
			break;
		default:
			info("No option in the list. Please select correct option.");
			break;
		
		}
		Utils.pause(1000);
	}
	
	/**
	 * Open Context menu of a given label
	 * @param label
	 *                is a label's name in the list
	 */
	public void goToContMenuGivenLabel(String label){
		info("Right click on a project in the list");
		Utils.pause(1000);
		mouseHoverByJavaScript(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label",label),2);
		clickByJavascript(ELEMENT_LEFT_PANE_LABEL_MENU.replace("$label", label),2);
	}
	
	/**
	 * Define options in Context Menu of a given label in Labellist
	 */
	public enum optionContMenuGivenLabel{
		Edit,Hide,Show,Delete,Add_Label;
	}
	
	/**
	 * Select an option in Context Menu of a Given label in label list
	 * @param label
	 *               is label's name of a given label in the list
	 * @param op
	 *               is an option in Context Menu as Edit,Hide,Delete,...
	 */
	public void selectOpContMenuGivenLabel(String label,optionContMenuGivenLabel op){
	    goToContMenuGivenLabel(label);
		switch(op){
	    case Edit:
	    	info("Select Edit option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_LABEL_EDIT.replace("$label", label),2);
	    	break;
	    case Hide:
	    	info("Select Hide option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_LABEL_HIDE.replace("$label", label),2);
	    	break;
	    case Show:
	    	info("Select Show option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_LABEL_SHOW.replace("$label", label),2);
	    	break;
	    case Delete:
	    	info("Select Delete option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_LABEL_DELETE.replace("$label", label),2);
	    	break;
	    case Add_Label:
	    	info("Select Add Label option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_LABEL_ADD.replace("$label", label),2);
	    	break;
	    default:
	    	info("No option in the list. Please select correct option");
	    	break;
	    }
	}
	/**
	 * Open a label
	 * @param labelPath
	 *                    is label's names as Project1/sub_Project1
	 */
	public void selectLabel(String labelPath){
		info("Open a label");
		String[] labels = labelPath.split("/");
		for(String label:labels){
			info("Click on "+label+" on left panel");
			openLabel(label);
		}
		Utils.pause(2000);
	}
	/**
	 * Change color of a given label
	 * @param label
	 *                is label's name
	 * @param color
	 *                is a color in color list as pink, red,sky_blue,...
	 *                these colors will be read from ColorDatabase.java file
	 */
	public void selectColor(String label,String color,String...tasks){
		goToContMenuGivenLabel(label);
		info("Select "+color+" in color list");
		click(ELEMENT_LEFT_PANE_COLOR_TABLE_ITEM.replace("$label",label).replace("$color",color));
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_COLOR.replace("$label", label).replace("$color", color));
		if(tasks.length>0){
			for (String task : tasks) {
				openLabel(label);
				Utils.pause(1000);
				waitForAndGetElement(ELEMENT_TASK_COLOR.replace("$task", task).replace("$color", color));
			}
		}
	}
	/**
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> FQA-2696:[Task Management]- Write scripts for Labels - Edit a label
	 * get value attribute
	 * @param locator
	 * @return data-id of element
	 */
	public Integer getDataId(Object locator) {
		try {
			return Integer.parseInt(waitForAndGetElement(locator).getAttribute("data-labelid"));
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);			
			Utils.pause(WAIT_INTERVAL);
			return Integer.parseInt(getValue(locator));
		} finally {
			loopCount = 0;
		}
	}
	/**
<<<<<<< HEAD
=======
>>>>>>> FQA-2694: [Task Management]- Write scripts for Labels - Label Overview
=======
>>>>>>> FQA-2696:[Task Management]- Write scripts for Labels - Edit a label
	 * Add a new label
	 * @param name
	 *              is label's name
	 */
	public void addLabel(String name){
		selectOpContMenuLabel(optionContMenuLabel.Add_Label);
		info("create label:"+name);
		if(!name.isEmpty()){
			WebElement e= waitForAndGetElement(ELEMENT_ADD_LABEL_NAME_INPUT);
			e.sendKeys(name);
	        driver.findElement(ELEMENT_ADD_LABEL_NAME_INPUT).sendKeys(Keys.ENTER);
		}
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label", name));
		click(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label", name),0,true);
	}
	/**
	 * Add sub label
	 * @param parent
	 * @param sub
	 */
	public void addSubLabel(String parent,String sub){
		selectOpContMenuGivenLabel(parent, optionContMenuGivenLabel.Add_Label);
		info("create label:"+sub);
		if(!sub.isEmpty()){
			waitForAndGetElement(ELEMENT_ADD_LABEL_NAME_INPUT).sendKeys(sub);
	        driver.findElement(ELEMENT_ADD_LABEL_NAME_INPUT).sendKeys(Keys.ENTER);
		}
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label", sub));
	}
	/**
	 * Check default label
	 * @param label
	 * @param isDisplay
	 */
	public void checkLabelByDefault(String label,boolean isDisplay){
		openLabel(label);
		info("check default of label:"+label);
		if(isDisplay){
			waitForAndGetElement(ELEMENT_TEXT_NOTASK_DEFAULT);
			waitForAndGetElement(ELEMENT_LEFT_PANE_TOOLTIP_TASK);
		}else{
			waitForElementNotPresent(ELEMENT_TEXT_NOTASK_DEFAULT);
			waitForElementNotPresent(ELEMENT_LEFT_PANE_TOOLTIP_TASK);
		}
	}
	/**
	 * Edit label
	 * @param labelPath
	 * @param name
	 * 				new name of label
	 * @param parent
	 * 				new parent label
	 */
	public void editLabel(String label,String name,String parent){
		selectOpContMenuGivenLabel(label, optionContMenuGivenLabel.Edit);
		Utils.pause(500);
		waitForAndGetElement(ELEMENT_EDIT_LABEL_DIALOG);
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_SAVE_BTN_DISABLED);
		if(!name.isEmpty()){
			info("Input new name");
			type(ELEMENT_EDIT_LABEL_NAME,name,true);
		}else{
			info("left blank");
			waitForAndGetElement(ELEMENT_EDIT_LABEL_NAME).clear();
		}
		Utils.pause(500);
		if(!parent.isEmpty()){
			info("select new parent");
			mouseOverAndClick(ELEMENT_EDIT_LABEL_PARENT_SELECT);
			selectQuick(ELEMENT_EDIT_LABEL_PARENT_SELECT,parent);
		}
		saveEditLabel();
		waitForElementNotPresent(ELEMENT_EDIT_LABEL_DIALOG);
	}
	/**
	 * Save all changes when editing a label
	 */
	public void saveEditLabel(){
		info("Click on Save button");
		click(ELEMENT_EDIT_PROJECT_SAVE_BTN,0,true);
		Utils.pause(2000);
	}
	
	/**
	 * Cancel all changes when editing a label
	 */
	public void cancelEditLabel(){
		info("Click on Cancel button");
		click(ELEMENT_EDIT_PROJECT_CANCEL_BTN,0,true);
		Utils.pause(1000);
	}
	/**
	 * Check edit popup
	 * @param label
	 * @param parent
	 */
	public void checkEditLabelPopup(String label,String parent){
		info("check edit label popup");
		selectOpContMenuGivenLabel(label, optionContMenuGivenLabel.Edit);
		waitForAndGetElement(ELEMENT_EDIT_LABEL_PARENT_TEXT.replace("$parent", parent));
		waitForAndGetElement(ELEMENT_EDIT_LABEL_NAME_TEXT.replace("$label", label));
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_CANCEL_BTN);
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_SAVE_BTN);
	}
	/**
	 * Check list label in parent
	 * @param name
	 * 				name of label
	 * @parent isDisplay
	 * @parent labels
	 * 				list of label with id
	 */
	public void checkListLabelInParent(String name,boolean isDisplay,String...labels){
		selectOpContMenuGivenLabel(name, optionContMenuGivenLabel.Edit);
		mouseOverAndClick(ELEMENT_EDIT_LABEL_PARENT_SELECT);
		if(isDisplay){
			info("labels are in list");
			for (String label : labels) {
				waitForAndGetElement(ELEMENT_EDIT_LABEL_NAME_TEXT.replace("$id", label));
			}
		}else{
			info("labels are not in list");
			for (String label : labels) {
				waitForElementNotPresent(ELEMENT_EDIT_LABEL_NAME_TEXT.replace("$id", label));
			}
		}
	}
	/**
	 * Delete label
	 * @param name
	 */
	public void deleteLabel(String name){
		selectOpContMenuGivenLabel(name,optionContMenuGivenLabel.Delete);
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP);
		info("delete label:"+name);
		click(ELEMENT_DELETE_LABEL_POPUP_DELETE_BTN);
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label", name));
	}
	/**
	 * Check cancel action
	 * @param name
	 */
	public void checkCancelDeleteLabel(String name){
		selectOpContMenuGivenLabel(name,optionContMenuGivenLabel.Delete);
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP);
		info("check cancel delele action");
		click(ELEMENT_DELETE_LABEL_POPUP_CANCEL_BTN);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label", name));
	}
	/**
	 * Check Delete Label popup
	 * @param label
	 */
	public void checkDeleteLabelPopup(String label){
		info("check delete popup");
		selectOpContMenuGivenLabel(label,optionContMenuGivenLabel.Delete);
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP_TITLE);
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP_MESSAGE.replace("$label", label));
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP_CANCEL_BTN);
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP_DELETE_BTN);
	}
	/**
	 * Check default setting groupBy,sortBy
	 * @param project
	 * @param group
	 * @param sort
	 */
	public void checkDefaultGroupSort(String label,String group,String sort){
		openLabel(label);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_SORTBY_ITEM_DEFAULT.replace("$sort",sort));
		waitForAndGetElement(ELEMENT_GROUPBY_ITEM_DEFAULT.replace("$group", group));
	}
	/**
	 * Check cancel action
	 * @param name
	 */
	public void checkCancelDeleteLabel(String name){
		selectOpContMenuGivenLabel(name,optionContMenuGivenLabel.Delete);
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP);
		info("check cancel delele action");
		click(ELEMENT_DELETE_LABEL_POPUP_CANCEL_BTN);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label", name));
	}
	/**
	 * Check Delete Label popup
	 * @param label
	 */
	public void checkDeleteLabelPopup(String label){
		info("check delete popup");
		selectOpContMenuGivenLabel(label,optionContMenuGivenLabel.Delete);
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP_TITLE);
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP_MESSAGE.replace("$label", label));
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP_CANCEL_BTN);
		waitForAndGetElement(ELEMENT_DELETE_LABEL_POPUP_DELETE_BTN);
	}
	/**
	 * Check GroupBy list in Labels
	 * @param project
	 * @param groups
	 */
	public void checkGroupByInLabels(String label,String[] groups){
		info("check group by list of label");
		openLabel(label);
		click(ELEMENT_GROUPBY_ICON);
		Utils.pause(1000);
		for (String group : groups) {
			try {
				waitForAndGetElement(ELEMENT_GROUPBY_ITEM.replace("$group", group));
			}catch (AssertionError e) {
				waitForAndGetElement(ELEMENT_GROUPBY_ITEM_DEFAULT.replace("$group", group));
			}
		}
	}
	/**
	 * Check SortBy list in Labels
	 * @param project
	 * @param sorts
	 */
	public void checkSortByInLabels(String label,String[] sorts){
		info("check sort by list of label");
		openLabel(label);
		click(ELEMENT_SORTBY_ICON);
		Utils.pause(1000);
		for (String sort : sorts) {
			try {
				waitForAndGetElement(ELEMENT_SORTBY_ITEM.replace("$sort",sort));
			}catch (AssertionError e) {
				waitForAndGetElement(ELEMENT_SORTBY_ITEM_DEFAULT.replace("$sort",sort));
			}
		}
	}
	/**
	 * Check display of List, Board
	 * @param label
	 * @param isPresent
	 */
	public void checkDisplayOfListBoard(String label,boolean isPresent){
		openLabel(label);
		Utils.pause(1000);
		if(isPresent){
			 waitForAndGetElement(ELEMENT_LIST_VIEW);
			 waitForAndGetElement(ELEMENT_BOARD_VIEW);
		}else{
			 waitForElementNotPresent(ELEMENT_LIST_VIEW);
			 waitForElementNotPresent(ELEMENT_BOARD_VIEW);
		}
	}
	/**
	 * Check Labels by default
	 * @param isDisplay
	 */
	public void checkLabelsByDefault(boolean isDisplay){
		openLabel("Labels");
		Utils.pause(1000);
		if(isDisplay){
			waitForAndGetElement(ELEMENT_LEFT_PANE_NO_LABEL);
			waitForAndGetElement(ELEMENT_WELCOME_TEXT_LABEL_DEFAULT);
			waitForAndGetElement(ELEMENT_LEFT_PANE_TOOLTIP_LABEL);
		}else{
			waitForElementNotPresent(ELEMENT_LEFT_PANE_NO_LABEL);
			waitForElementNotPresent(ELEMENT_WELCOME_TEXT_LABEL_DEFAULT);
			waitForElementNotPresent(ELEMENT_LEFT_PANE_TOOLTIP_LABEL);
		}
	}
}
