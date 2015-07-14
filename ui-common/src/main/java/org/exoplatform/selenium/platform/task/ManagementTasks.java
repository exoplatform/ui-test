package org.exoplatform.selenium.platform.task;

import org.exoplatform.selenium.Utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.exoplatform.selenium.TestLogger.info;

/**
 * This class will define actions about management project
 *
 */
public class ManagementTasks extends TaskManagementLocatorObject {
	
	TaskManagementHome tasHome;
	
	public ManagementTasks(WebDriver dr){
		this.driver=dr;
		tasHome=new TaskManagementHome(dr);
	}
	
	
    /**
     * Define options in Task list
     *
     */
	public enum optionTask{
		Incoming,All_Tasks,Overdue,Today,Tommorrow,Upcoming;
	}
	
	
	/**
	 * Select an option in Task List
	 * @param op
	 *          is an option in the list as: Incoming,All tasks,...
	 */
	public void selectOptionTask(optionTask op){
		info("--Open Task Management--");
		tasHome.goToTasks();
		switch(op){
		case Incoming:
			info("Select Incomming option");
			break;
		case All_Tasks:
			info("Select All tasks option");
			break;
		case Overdue:
			info("Select Overdue option");
			break;
		case Today:
			info("Select Today option");
			break;
		case Tommorrow:
			info("Select Tommorrow option");
			break;
		case Upcoming:
			info("Select Upcoming option");
			break;
		default:
			info("No option in the list.Please select correct option.");
			break;
		}
	}
	/**
     * Define options in menu of task detail
     *
     */
	public enum optMenuTask{
		Watch,Clone,Delete;
	}
	
	/**
	 * Select option in menu of task detail
	 * @param task
	 * @param opt
	 * 			 is Clone, Watch, Delete...
	 */
	public void selectOptMenuTask(String task,optMenuTask opt){
		info("click on task");
		click(ELEMENT_TASK_TITLE.replace("$task", task));
		info("click on arrow menu");
		click(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU);
		switch(opt){
		case Watch:
			info("select Watch");
			break;
		case Clone:
			info("select Clone");
			click(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_CLONE);
			break;
		case Delete:
			info("select Delete");
			click(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_DELETE);
			waitForElementNotPresent(ELEMENT_TASK_TITLE.replace("$task", task));
			break;
		default:
			info("No option in the list.Please select correct option.");
			break;
		}
	}
	
	/**
	 * Open Sorting list of Incoming
	 */
	public void goToSortIncomingList(){
		info("Click on Sort list");
	}
	
	/**
	 * Open Group list in Incoming
	 */
	public void goToGroupIncoming(){
		info("Click on Group list");
	}
	
	/**
	 * Add a task into project
	 * @param project 
	 * 				can be project
	 * 				can be Incoming, Upcoming,Today,....
	 * @param task
	 */
	public void addTask(String project, String task){
		info("Create a task");
		click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project",project));
		click(ELEMENT_ADD_TASK_BTN);
		waitForAndGetElement(ELEMENT_ADD_TASK_TITLE).sendKeys(task);
        Utils.pause(500);
        driver.findElement(ELEMENT_ADD_TASK_TITLE).sendKeys(Keys.ENTER);
        waitForAndGetElement(ELEMENT_TASK_TITLE.replace("$task", task));
	}
	/**
	 * Delete task
	 * @param task
	 */
	public void deleteTask(String task){
		info("Delete task");
		click(ELEMENT_TASK_TITLE.replace("$task", task));
		
	}
}
