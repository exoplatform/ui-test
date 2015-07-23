package org.exoplatform.selenium.platform.task;

import org.exoplatform.selenium.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
	 * Open task
	 */
	public void openTask(String task){
		info("open task: "+task);
		click(ELEMENT_TASK_TITLE.replace("$task", task));
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

		switch(op){
		case Incoming:
			info("Select Incomming option");
			clickByJavascript(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Incoming"),2);
			break;
		case All_Tasks:
			info("Select All tasks option");
			click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "All Tasks"),0,true);
			break;
		case Overdue:
			info("Select Overdue option");
			click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Overdue"),0,true);
			break;
		case Today:
			info("Select Today option");
			click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Today"),0,true);
			break;
		case Tommorrow:
			info("Select Tommorrow option");
			click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Tomorrow"),0,true);
			break;
		case Upcoming:
			info("Select Upcoming option");
			click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Upcoming"),0,true);
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
		click(ELEMENT_TASK_TITLE.replace("$task", task),0,true);
		info("click on arrow menu");
		click(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU,0,true);
		switch(opt){
		case Watch:
			info("select Watch");
			break;
		case Clone:
			info("select Clone");
			click(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_CLONE,0,true);
			break;
		case Delete:
			info("select Delete");
			click(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_DELETE,0,true);
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
		info("add task: "+ task);
		click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project",project),0,true);
		click(ELEMENT_ADD_TASK_BTN,0,true);
		waitForAndGetElement(ELEMENT_ADD_TASK_TITLE).sendKeys(task);
        driver.findElement(ELEMENT_ADD_TASK_TITLE).sendKeys(Keys.ENTER);
        waitForAndGetElement(ELEMENT_TASK_TITLE.replace("$task", task));
        waitForElementNotPresent(ELEMENT_LEFT_PANE_TOOLTIP.replace("$mes", "Let's create your first task."));
		waitForElementNotPresent(ELEMENT_PROJECT_WELCOME_IMG);
	}
	/**
	 * Check task detail
	 * @param task
	 */
	public void checkTaskDetail(String task){
		openTask(task);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", task));
		waitForAndGetElement(ELEMENT_UNTITLEDTASK_AND_TASK_INPUT.replace("$task", task));
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKFLOW_TEXT.replace("$flow","To Do"));
	}
	/**
	 * Add a task by clicking directly on the blank field
	 * @param task
	 */
	public void addTaskDirectly(String task){
		info("add task: "+ task);
		click(ELEMENT_ADD_TASK_TITLE);
		waitForAndGetElement(ELEMENT_ADD_TASK_TITLE).sendKeys(task);
        Utils.pause(500);
        driver.findElement(ELEMENT_ADD_TASK_TITLE).sendKeys(Keys.ENTER);
        waitForAndGetElement(ELEMENT_TASK_TITLE.replace("$task", task));
        waitForElementNotPresent(ELEMENT_LEFT_PANE_TOOLTIP.replace("$mes", "Let's create your first task."));
		waitForElementNotPresent(ELEMENT_PROJECT_WELCOME_IMG);
	}
	/**
	 * Delete task
	 * @param task
	 */
	public void deleteTask(String task){
		info("Delete task");
		click(ELEMENT_TASK_TITLE.replace("$task", task));
		click(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU);
		mouseOverAndClick(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_DELETE);
		Utils.pause(500);
		waitForElementNotPresent(ELEMENT_TASK_TITLE.replace("$task",task));
	}
	/**
	 * Add comment to task
	 * @param task
	 * @param comment
	 * @param user
	 */
	public void addTaskComment(String task,String user,String comment){
		info("add comment to task");
		openTask(task);
		doubleClickOnElement(ELEMENT_RIGHT_PANE_COMMENT_LINK);
		WebElement input= waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_INPUT.replace("$comment",""));
		Actions action =new Actions(driver);
		action.moveToElement(input).sendKeys(comment).build().perform();		
		//type(ELEMENT_RIGHT_PANE_COMMENT_INPUT,comment,false);
		click(ELEMENT_RIGHT_PANE_COMMENT_BUTTON);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", user).replace("$comment", comment));
	}
	/**
	 * get value attribute
	 * @param locator
	 * @return data-taskid of element
	 */
	public Integer getTaskId(Object locator) {
		try {
			return Integer.parseInt(waitForAndGetElement(locator).getAttribute("data-taskid"));
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);			
			Utils.pause(WAIT_INTERVAL);
			return Integer.parseInt(getValue(locator));
		} finally {
			loopCount = 0;
		}
	}
	/**
	 * Clone task
	 * @param task
	 */
	public void cloneTask(String task){
		info("clone task");
		openTask(task);
		selectOptMenuTask(task, optMenuTask.Clone);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task","Copy of "+ task));
	}
	/**
	 * Check comment of user
	 * @param project
	 * @param task
	 * @param comment
	 * @param user: user posts comment
	 */
	public void checkCommentOfUser(String project,String task,String comment,String user){
		info("check comment of: "+user);
		click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
		openTask(task);
		Utils.pause(500);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", user).replace("$comment", comment));
	}
	/**
	 * Check order by sort type
	 * @param tasks
	 */
	public void checkOrderBySortCreatedDate(String[] tasks){
		for(int i=1;i<=tasks.length;i++){
			waitForAndGetElement(ELEMENT_TASK_ORDER.replace("$num",String.valueOf(i)).replace("$task", tasks[i]));
		}
	}
	/**
	 * Edit task title
	 * @param task
	 * @param title
	 * 				new title of task
	 */
	public void editTaskTitle(String task,String title){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", task));
		info("Input title");
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).sendKeys(title);
        Utils.pause(500);
        driver.findElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).sendKeys(Keys.ENTER);
        waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", title));
	}
	/**
	 * Edit task title
	 * @param task
	 * @param des
	 * 				new des of task
	 */
	public void editTaskDescription(String task,String des){
		click(ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_LINK);
		info("input description");
		inputFrame(ELEMENT_CKEDITOR_IFRAME, des);
		mouseOverAndClick(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", task));
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_TEXT.replace("$des", des));
	}
	/**
	 * Check menu of task detail
	 * 	 
	 */
	public void checkMenuOfTask(){
		info("check menu of task detail");
		click(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_CLONE);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_DELETE);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_WATCH);
	}
}
