package org.exoplatform.selenium.platform.task;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.exoplatform.selenium.Utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.exoplatform.selenium.TestLogger.info;

/**
 * This class will define actions about management task
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
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_CLOSE_ICON);
	}
	/**
	 * Close task detail
	 */
	public void closeTask(){
		info("close task detail");
		click(ELEMENT_RIGHT_PANE_TASK_CLOSE_ICON);
		waitForElementNotPresent(ELEMENT_RIGHT_PANE_TASK_CLOSE_ICON);
	}
	/**
	 * Hide task detail on right pane
	 */
	public void hideTaskDetail(){
		info("hide detail of task on right pane");
		click(ELEMENT_RIGHT_PANE_HIDE_ICON);
		waitForElementNotPresent(ELEMENT_RIGHT_PANE_TASK_CLOSE_ICON);
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
			click(ELEMENT_LEFT_PANE_INCOMING,0,true);
			break;
		case All_Tasks:
			info("Select All tasks option");
			click(ELEMENT_LEFT_PANE_ALLTASKS,0,true);
			break;
		case Overdue:
			info("Select Overdue option");
			click(ELEMENT_LEFT_PANE_OVERDUE,0,true);
			break;
		case Today:
			info("Select Today option");
			click(ELEMENT_LEFT_PANE_TODAY,0,true);
			break;
		case Tommorrow:
			info("Select Tommorrow option");
			click(ELEMENT_LEFT_PANE_TOMORROW,0,true);
			break;
		case Upcoming:
			info("Select Upcoming option");
			click(ELEMENT_LEFT_PANE_UPCOMING,0,true);
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
        waitForElementNotPresent(ELEMENT_LEFT_PANE_TOOLTIP_TASK);
		waitForElementNotPresent(ELEMENT_WELCOME_IMG);
	}
	/**
	 * Check first access
	 */
	public void checkFirstAccess(){
		waitForAndGetElement(ELEMENT_LEFT_PANE_TOOLTIP_TASK);
		waitForAndGetElement(ELEMENT_WELCOME_TEXT_TASK_DEFAULT);
		waitForAndGetElement(ELEMENT_WELCOME_IMG);
		waitForAndGetElement(ELEMENT_LEFT_PANE_NO_PROJECT);
		waitForAndGetElement(ELEMENT_LEFT_PANE_INCOMING_ACTIVE);
	}
	/**
	 * Check Task in project
	 * @param project
	 * @param tasks
	 * 				list of tasks
	 */
	public void checkTasksOfProject(String project,String...tasks){
		info("check list of tasks in project");
		click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project",project),0,true);
		for (String task : tasks) {
			waitForAndGetElement(ELEMENT_TASK_TITLE.replace("$task", task));
		}
	}
	/**
	 * Check task detail
	 * @param task
	 * @param project
	 * @param isProject
	 * 					true if has project parent
	 * 					false if has no project
	 */
	public void checkTaskDetail(String task,boolean isProject,String project,String defaultStatus){
		openTask(task);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", task));
		waitForAndGetElement(ELEMENT_UNTITLEDTASK_AND_TASK_INPUT.replace("$task", task));
		if(isProject && !defaultStatus.isEmpty()){
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_STATUS_TEXT.replace("$flow",defaultStatus));
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_PROJECT_TEXT.replace("$project",project));
		}else{
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_NOPROJECT_TEXT);
		}
	}
	/**
	 * Add a task by clicking directly on the blank field
	 * @param task
	 * @param isCheck
	 * 				true if want to check created task in list
	 * 				false if dont want
	 */
	public void addTaskDirectly(String task,boolean isCheck){
		info("add task: "+ task);
		click(ELEMENT_ADD_TASK_TITLE);
		waitForAndGetElement(ELEMENT_ADD_TASK_TITLE).sendKeys(task);
        Utils.pause(500);
        driver.findElement(ELEMENT_ADD_TASK_TITLE).sendKeys(Keys.ENTER);
        waitForElementNotPresent(ELEMENT_LEFT_PANE_TOOLTIP_TASK);
		waitForElementNotPresent(ELEMENT_WELCOME_IMG);
		if(isCheck){
			waitForAndGetElement(ELEMENT_TASK_TITLE.replace("$task", task));
		}
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
	 * Delete task by id
	 * @param id
	 */
	public void deleteTaskById(int id){
		info("Delete task by id: "+id );
		click(ELEMENT_TASK_ID.replace("$id",String.valueOf(id)));
		click(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU);
		mouseOverAndClick(ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_DELETE);
		Utils.pause(500);
		waitForElementNotPresent(ELEMENT_TASK_ID.replace("$id",String.valueOf(id)));
	}
	/**
	 * Check display of task title
	 * @param task
	 */
	public void checkDisplayOfTitle(String task){
		info("check display of long title");
		openTask(task);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", task));
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
	 * Add many comment into task
	 * @param task
	 * @param comment
	 * @param user
	 * @param loop
	 */
	public void addManyTaskComment(String task,String user,String comment,int loop){
		info("add comment to task");
		openTask(task);
		for(int i=1;i<=loop;i++){
			doubleClickOnElement(ELEMENT_RIGHT_PANE_COMMENT_LINK);
			WebElement input= waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_INPUT.replace("$comment",""));
			Actions action =new Actions(driver);
			action.moveToElement(input).sendKeys(comment+i).build().perform();
			click(ELEMENT_RIGHT_PANE_COMMENT_BUTTON);
			Utils.pause(2000);
			waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", user).replace("$comment", comment+i));
		}
	}
	/**
	 * Check View All Comment
	 * @param user
	 * @param comment
	 * @param num
	 * 			number of comments
	 */
	public void checkViewAllComments(String user,String comment,int num){
		driver.navigate().refresh();
		info("check display of View all comment");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", user).replace("$comment", comment+num));
		waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", user).replace("$comment", comment+(num-1)));
		for(int i=1;i<=(num-2);i++){
			waitForElementNotPresent(ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", user).replace("$comment", comment+i));
		}
		waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_VIEW_ALL.replace("$num",String.valueOf(num)));
	}
	/**
	 * Check Hide All Comment
	 * @param user
	 * @param comment
	 * @param num
	 * 			number of comments
	 */
	public void checkHideAllComments(String user,String comment,int num){
		info("check display of Hide all comment");
		click(ELEMENT_RIGHT_PANE_COMMENT_VIEW_ALL.replace("$num",String.valueOf(num)));
		for(int i=1;i<=num;i++){
			waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", user).replace("$comment", comment+i));
		}
		waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_HIDE_ALL.replace("$num",String.valueOf(num)));
	}
	/**
	 * Check suggestion list when mention user
	 * @param key	
	 * 			keyword of username
	 * @param users
	 * 			list of users match to keyword
	 */
	public void checkSuggestionListWhenMention(String key,String...users){
		info("check suggestion list when mention user");
		doubleClickOnElement(ELEMENT_RIGHT_PANE_COMMENT_LINK);
		WebElement input= waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_INPUT.replace("$comment",""));
		Actions action =new Actions(driver);
		action.moveToElement(input).sendKeys("@"+key).build().perform();
		for (String user : users) {
			waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_MENTION.replace("$user", user));
		}
	}
	/**
	 * Check comment button
	 * @param task
	 * @param isDisabled
	 * 					true if button is disabled
	 * 					false if button is enabled
	 */
	public void checkCommentButtonOfTaskComment(String task,boolean isDisabled){
		if(isDisabled){
			info("button is disabled");
			waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_BUTTON_DISABLED);
		}else{
			info("button is enabled");
			waitForElementNotPresent(ELEMENT_RIGHT_PANE_COMMENT_BUTTON_DISABLED);
		}
	}
	/**
	 * Check trash icon of comment
	 * @param user
	 * @param comment
	 * @param enabled
	 * 				true if user has permission to delete comment
	 * 				false if user has NOT permission to delete comment
	 */
	public void checkTrashIconOfComment(String user,String comment,boolean enabled){
		if(enabled){
			info("user has permission to delete comment");
			mouseOver(ELEMENT_RIGHT_PANE_COMMENT_USER.replace("$user", user).replace("$comment", comment),false);
			Utils.pause(500);
			waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_TRASH_ICON.replace("$comment", comment));
		}else{
			info("user has not permission to delete comment");
			mouseOver(ELEMENT_RIGHT_PANE_COMMENT_USER.replace("$user", user).replace("$comment", comment),false);
			Utils.pause(500);
			waitForElementNotPresent(ELEMENT_RIGHT_PANE_COMMENT_TRASH_ICON.replace("$comment", comment));
		}
	}
	/**
	 * Check textarea of comment
	 * @param comment
	 */
	public void checkTextAreaOfTaskComment(String comment){
		doubleClickOnElement(ELEMENT_RIGHT_PANE_COMMENT_LINK);
		WebElement input= waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_INPUT.replace("$comment",""));
		Actions action =new Actions(driver);
		action.moveToElement(input).sendKeys(comment).build().perform();
		waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_INPUT_TEXTAREA);
	}
	/**
	 * Check time of comment
	 * @param user
	 * @param comment
	 * @param time
	 * 			  when user posts comment
	 */
	public void checkDisplayOfCommentTime(String user,String comment,String time){
		info("check display of time change");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_COMMENT_TIME.replace("$user", user).replace("$comment", comment).replace("$time", time));
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
	 * Click on sort by
	 */
	public void clickSortBy(){
		info("click Sort By");
		click(ELEMENT_SORTBY_ICON);
	}
	/**
	 * Define options in sort by
	 */
	public enum optionSortBy{
		Due_Date,Title,Created_Date,Priority,Rank;
	}
	/**
	 * Select sort by
	 * @param opt
	 */
	public void selectOptSortBy(optionSortBy opt){
		clickSortBy();
		switch(opt){
		case Due_Date:
			info("Select Due Date");
			click(ELEMENT_SORTBY_DUEDATE,0,true);
			break;
		case Title:
			info("Select Title");
			click(ELEMENT_SORTBY_TITLE,0,true);
			break;
		case Created_Date:
			info("Select Created Date");
			click(ELEMENT_SORTBY_CREATEDDATE,0,true);
			break;
		case Priority:
			info("Select Priority");
			click(ELEMENT_SORTBY_PRIORITY,0,true);
			break;
		case Rank:
			info("Select Rank");
			click(ELEMENT_SORTBY_RANK,0,true);
			break;
		default:
			info("No option in the list. Please select correct option.");
			break;
		}
	}
	/**
	 * Check order sort by created date
	 * @param tasks
	 */
	public void checkSortByCreatedDate(String[] tasks){
		int i,j;
		for(i=tasks.length,j=1;i>0;i--,j++)
			waitForAndGetElement(ELEMENT_TASK_ORDER.replace("$num",String.valueOf(j)).replace("$task", tasks[i-1]));
		
	}
	/**
	 * Check sort by title: alphabet
	 * @param tasks
	 */
	public void checkSortByTitle(String[] tasks){
		int i;
		for(i=1;i<=tasks.length;i++){
			waitForAndGetElement(ELEMENT_TASK_ORDER.replace("$num",String.valueOf(i)).replace("$task", tasks[i-1]));
		}
	}
	/**
	 * Click on group by
	 */
	public void clickGroupBy(){
		info("click Group By");
		click(ELEMENT_GROUPBY_ICON);
	}
	/**
	 * Define options in group by
	 */
	public enum optionGroupBy{
		None,Due_Date,Label,Status,Assignee,Project;
	}
	/**
	 * Select group by
	 * @param opt
	 */
public void selectOptGroupBy(optionGroupBy opt){
		clickGroupBy();
		Utils.pause(500);
		switch(opt){
		case Due_Date:
			info("Select Due Date");
			clickByJavascript(ELEMENT_GROUPBY_DUEDATE,2);
			break;
		case None:
			info("Select None");
			clickByJavascript(ELEMENT_GROUPBY_NONE,2);
			break;
		case Label:
			info("Select Label");
			click(ELEMENT_GROUPBY_LABEL,0,true);
			break;
		case Status:
			info("Select Status");
			click(ELEMENT_GROUPBY_STATUS,0,true);
			break;
		case Assignee:
			info("Select Assignee");
			clickByJavascript(ELEMENT_GROUPBY_ASSIGNEE,2);
			break;
		case Project:
			info("Select Project");
			click(ELEMENT_GROUPBY_PROJECT,0,true);
			break;
		default:
			info("No option in the list. Please select correct option.");
			break;
		}
	}
	/**
	 * Check group by 
	 * @param isGroup
	 * 				true if group by Assignee,Due Date..
	 * 				false if group by None
	 * @param header
	 * @param num
	 * 			number of tasks 
	 */
	public void checkGroupBy(boolean isGroup,String header,int num){
		if(isGroup){
			waitForAndGetElement(ELEMENT_GROUPBY_HEADER_NUM.replace("$header", header).replace("$num", String.valueOf(num)));
		}else{
			waitForElementNotPresent(ELEMENT_GROUPBY_HEADER_NUM.replace("$header", header).replace("$num", String.valueOf(num)));
		}
	}
	/**
	 * Check sort of group by 
	 * @param header
	 * @param num
	 * 			number of order
	 */
	public void checkSortOfGroupBy(String header,int num){
		waitForAndGetElement(ELEMENT_GROUPBY_HEADER_SORT.replace("$header", header).replace("$num", String.valueOf(num)));
	}
	/**
	 * Check symbol before task
	 * @param task
	 * @param isBlue
	 * 				true if symbol is blue
	 * 				false if symbol is red
	 */
	public void checkTaskSymbol(String task,boolean isBlue){
		if(isBlue){
			waitForAndGetElement(ELEMENT_TASK_SYMBOL_BLUE.replace("$task", task));
		}else{
			waitForAndGetElement(ELEMENT_TASK_SYMBOL_RED.replace("$task", task));
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
		if(!title.isEmpty()){
			info("Input title");
			waitForAndGetElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).clear();
			waitForAndGetElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).sendKeys(title);
	        Utils.pause(500);
	        driver.findElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).sendKeys(Keys.ENTER);
	        waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", title));
		}else{
			info("left title blank");
			waitForAndGetElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).clear();
	        Utils.pause(500);
	        driver.findElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).sendKeys(Keys.ENTER);
	        waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_UNTITLED);
		}
	}
	/**
	 * Edit task description
	 * @param task
	 * @param des
	 * 				new des of task
	 */
	public void editTaskDescription(String task,String des){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_LINK);
		info("input description");
		waitForAndGetElement(ELEMENT_CKEDITOR_IFRAME);
		inputFrame(ELEMENT_CKEDITOR_IFRAME, des);
		mouseOverAndClick(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", task));
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_TEXT.replace("$des", des));
	}

	/**
	 * Decorate description
	 * @param task
	 * @param des
	 * 			 description of task
	 */
	public void decorateDescription(String task, String des){
		openTask(task);
		Utils.pause(1000);
		click(ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_LINK);
		if(des.isEmpty()){
			info("left description blank");
			inputFrame(ELEMENT_CKEDITOR_IFRAME, "");
			mouseOverAndClick(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", task));
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_EMPTY);
		}else{
			info("decorate description");
			inputFrame(ELEMENT_CKEDITOR_IFRAME, des);
			cke_NumberList();
			cke_Bold();
			cke_Italic();
			mouseOverAndClick(ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT.replace("$task", task));
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_TEXT.replace("$des", des));
		}
	}
	/**
	 * Click on Bold button
	 * this function is used for the files has one CKEditor tool
	 * 
	 */
	public void cke_Bold() {
		info("Bold a text");
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ELEMENT_CKEDITOR_BOLD);
		Utils.pause(200);
	}
	/**
	 * Click on Italic button
	 * this function is used for the files has one CKEditor tool
	 * 
	 */
	public void cke_Italic() {
		info("Italic a text");
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ELEMENT_CKEDITOR_ITALIC);
		Utils.pause(200);
	}
	/**
	 * Click on Number List button
	 * this function is used for the files has one CKEditor tool
	 * 
	 */
	public void cke_NumberList() {
		info("NumberList a text");
		pressGroupKeysUsingRobot(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		click(ELEMENT_CKEDITOR_NUMBERLIST);
		Utils.pause(200);
	}
	
	/**
	 * Edit project of task
	 * @param task
	 * @param project
	 */
	public void editTaskProject(String task,String project){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_PROJECT_LINK);
		info("change project");
		type(ELEMENT_EDIT_PROJECT_PATH_INPUT,project,true);
		click(ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU.replace("$project",project),0,true);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_PROJECT_TEXT.replace("$project", project));
	}
	/**
	 * Search project in Projects
	 * @param task
	 * @param project: search key
	 */
	public void searchTaskProject(String task,String project){
		openTask(task);
		info("search project");
		click(ELEMENT_RIGHT_PANE_TASK_PROJECT_LINK);
		type(ELEMENT_EDIT_PROJECT_PATH_INPUT,project,true);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_PARENT_PATH_MATCH_VALUE.replace("$text",project));
		click(ELEMENT_RIGHT_PANE_PARENT_PATH_MATCH_VALUE.replace("$text",project));
		Utils.pause(500);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_PROJECT_TEXT.replace("$project", project));
	}
	/**
	 * Edit assignee
	 * @param task
	 * @param assignee
	 * @param coworkers
	 * 					list of coworkers
	 */
	public void editTaskAssignee(String task,String assignee,String...coworkers){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_ASSIGN_LINK);
		info("edit assignee");
		if(!assignee.isEmpty()){
			type(ELEMENT_RIGHT_PANE_TASK_ASSIGN_INPUT,assignee,false);
			Robot robot;
			try {
				robot = new Robot();
				robot.delay(1000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Utils.pause(1000);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_ASSIGN_INPUT_DISABLED);
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_ASSIGN_REMOVE_ICON.replace("$user",assignee));
		}
		if(coworkers.length>0){
			for (String coworker : coworkers) {
				type(ELEMENT_RIGHT_PANE_TASK_COWORKER_INPUT,coworker,false);
				Robot robot;
				try {
					robot = new Robot();
					robot.delay(1000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Utils.pause(1000);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_COWORKER_REMOVE_ICON.replace("$user",coworker));
			}
		}
		openTask(task);

	}
	/**
	 * Check assignee popup
	 * @param task
	 * @param user
	 * @param username
	 */
	public void checkTaskAssigneePopup(String task,String user,String username){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_ASSIGN_LINK);
		info("check assignee popup");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_ASSIGN_INPUT);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_COWORKER_INPUT);
		type(ELEMENT_RIGHT_PANE_TASK_ASSIGN_INPUT,user,false);
		Robot robot;
		try {
			robot = new Robot();
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.pause(1000);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_ASSIGN_REMOVE_ICON1.replace("$username",username));
	}
	/**
	 * Check default value of assignee
	 * @param task
	 */
	public void checkTaskAssigneeDefault(String task){
		openTask(task);
		info("check default value of assignee");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_ASSIGN_LINK);
	}
	/**
	 * Check assignee full name
	 * @param assignee
	 * @param coworkers
	 */
	public void checkTaskAssignee(String assignee,String...coworkers){
		info("check assignee");
		click(ELEMENT_RIGHT_PANE_TASK_ASSIGN_LINK);
		if(!assignee.isEmpty()){
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_ASSIGN_TEXT.replace("$user",assignee));
		}
		if(coworkers.length>0){
			for (String coworker : coworkers) {
				waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_COWORKER_TEXT.replace("$user",coworker));
			}
		}
	}
	/**
	 * Remove assignee
	 * @param task
	 * @param assignee
	 * @param coworkers
	 */
	public void removeAssigneeOfTask(String task,String assignee,String...coworkers){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_DISPLAY_ASSIGN_COWORKER.replace("$num", assignee));
		click(ELEMENT_RIGHT_PANE_TASK_ASSIGN_REMOVE_ICON1.replace("$username", assignee));
		Utils.pause(500);
		if(coworkers.length>0){
			for (String coworker : coworkers) {
				click(ELEMENT_RIGHT_PANE_TASK_COWORKER_REMOVE_ICON1.replace("$username", coworker));
				Utils.pause(500);
			}
		}
	}
	/**
	 * Check fullname link of group assignee
	 * @param user 
	 */
	public void checkFullnameLink(String user){
		info("check fullname link of group assignee");
		click(ELEMENT_GROUPBY_HEADER_FULLNAME.replace("$header", user));
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_PROFILE_PORTLET.replace("$user", user));
	}
	/**
	 * Check auto complete
	 * @param task
	 * @param key   keyword to search
	 * @param users 
	 * 					values which match to keyword
	 */
	public void checkAutoCompleteUser(String task,String key,String...users){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_ASSIGN_LINK);
		info("check auto complete");
		type(ELEMENT_RIGHT_PANE_TASK_ASSIGN_INPUT,key,false);
		if(users.length>0){
			for (String user : users) {
				waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_ASSIGN_AUTOCOMPLETE.replace("$user", user));
			}
		}
	}
	/**
	 * Check display of assignee and coworkers
	 * @param num
	 * 			text of number coworkers
	 */
	public void checkDisplayOfAssigneeCoworker(String num){
		info("check display of assignee and coworker");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DISPLAY_ASSIGN_COWORKER.replace("$num", num));
	}
	/**
	 * Check display of only coworkers
	 * @param num
	 * 			text of number coworkers
	 */
	public void checkDisplayOfOnlyCoworker(String num){
		info("check display of only coworker");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DISPLAY_ONLY_COWORKER.replace("$num", num));
	}
	/**
	 * Edit status
	 * @param task
	 * @param status
	 */
	public void editTaskStatus(String task,String status){
		openTask(task);
		info("change task status: "+status);
		click(ELEMENT_RIGHT_PANE_TASK_STATUS_LINK);
		selectQuick(ELEMENT_RIGHT_PANE_TASK_STATUS_SELECT_LINK,status);
		Utils.pause(500);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_STATUS_TEXT.replace("$flow", status));
	}
	/**
	 * Check default status of task
	 * @param task
	 * @param status
	 * @param isDisplay
	 * 					true if this status is displayed in task detail
	 * 					false if this status is not displayed in task detail
	 */
	public void checkDefaultTaskStatus(String task,String status,boolean isDisplay){
		openTask(task);
		info("check default status");
		if(isDisplay){
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_STATUS_TEXT.replace("$flow", status));
		}else{
			waitForElementNotPresent(ELEMENT_RIGHT_PANE_TASK_STATUS_TEXT.replace("$flow", status));
		}
	}
	
	/**
	 * Edit tag with new
	 * @param task
	 * @param tags
	 * 				list of tags (not exist before)
	 */
	public void editTaskTag(String task,String...tags){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_TAG_LINK);
		info("edit tag");
		if(tags.length>0){
			for (String tag : tags) {
				type(ELEMENT_RIGHT_PANE_TASK_TAG_INPUT,tag,false);
				waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TAG_SELECT_NEW.replace("$tag", tag));
				Robot robot;
				try {
					robot = new Robot();
					robot.delay(1000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Utils.pause(2000);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TAG_TEXT.replace("$tag",tag));
				waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TAG_REMOVE_ICON.replace("$tag",tag));
			}
		}
		openTask(task);
	}
	/**
	 * Add existed tag
	 * @param task
	 * @param tags
	 * 				list of tags (exist before)
	 */
	public void addExistedTag(String task,String...tags){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_TAG_LINK);
		info("edit tag");
		if(tags.length>0){
			for (String tag : tags) {
				type(ELEMENT_RIGHT_PANE_TASK_TAG_INPUT,tag,false);
				Robot robot;
				try {
					robot = new Robot();
					robot.delay(1000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Utils.pause(2000);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TAG_TEXT.replace("$tag",tag));
				waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TAG_REMOVE_ICON.replace("$tag",tag));
			}
		}
		openTask(task);
	}
	/**
	 * Check default tag
	 * @param task
	 * @param isPresent
	 * 					true if it exists
	 * 					false if it doesnot exist
	 */
	public void checkDefaultTag(String task,boolean isPresent){
		openTask(task);
		if(isPresent){
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TAG_DEFAULT_ICON);
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_TAG_DEFAULT_LABEL);
		}else{
			waitForElementNotPresent(ELEMENT_RIGHT_PANE_TASK_TAG_DEFAULT_ICON);
			waitForElementNotPresent(ELEMENT_RIGHT_PANE_TASK_TAG_DEFAULT_LABEL);
		}
	}
	/**
	 * Edit workplan
	 * @param task
	 * @param fromDay  day to pickup in calendar
	 * @param gap1     loop count for next month in From table
	 * @param toDay	   day to pickup in calendar
	 * @param gap2		loop count for next month in To table
	 * @param fromTime
	 * @param toTime
	 * @param checkAllDay
	 */
	public void editTaskWorkPlan(String task,String fromDay,int gap1,String toDay,int gap2,String fromTime,String toTime,boolean checkAllDay){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_LINK);
		info("edit workplan for task");
		//if not the same month
		if(gap1>0){
			for(int i=0;i<gap1;i++){
				clickByJavascript(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_NEXTMONTH_ICON1);
			}
		}
		if(!fromDay.isEmpty()){
			//cut 0 to get round number, ex 07 > 7
			if(fromDay.startsWith("0")){
				fromDay=fromDay.substring(1);
			}
			mouseHoverByJavaScript(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROM_DAY.replace("$day", fromDay),2);
			clickByJavascript(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROM_DAY.replace("$day", fromDay),2);
		}
		
		//if not the same month
		if(gap2>0){
			for(int i=0;i<gap2;i++){
				clickByJavascript(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_NEXTMONTH_ICON2);
			}
		}
		if(!toDay.isEmpty()){
			//cut 0 to get round number, ex 07 > 7
			if(toDay.startsWith("0")){
				toDay=toDay.substring(1);
			}
			mouseHoverByJavaScript(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TO_DAY.replace("$day", toDay),2);
			clickByJavascript(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TO_DAY.replace("$day", toDay),2);
		}
		if(checkAllDay){
			check(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_ALLDAY_CHECKBOX,2);
		}else{
			click(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROMTIME_LINK);
			click(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROMTIME.replace("$time",fromTime));
			Utils.pause(1000);
			click(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TOTIME_LINK);
			click(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TOTIME.replace("$time",toTime));
		}
	}
	/**
	 * Check display of workplan calculation
	 * @param time
	 * 			 total time in days,hours,minutes
	 * @param dates
	 * 			 date[0] if in the same day
	 * 			 date[0],date[1] if from one day to another day
	 */
	public void checkDisplayOfWorkPlan(String time,String...dates){
		info("check display of workplan calculation");
		if(dates.length>1){
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TEXT_DIFFDAY.replace("$date1",dates[0]).replace("$date2", dates[1]).replace("$time", time));
		}else{
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TEXT_1DAY.replace("$date",dates[0]).replace("$time", time));
		}
	}
	/**
	 * Check default of work plan
	 * @param task
	 */
	public void checkDefaultOfWorkPlan(String task){
		openTask(task);
		info("check default of work plan");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_DEFAULT);
		click(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_LINK);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TO_CALENDAR);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROM_CALENDAR);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TOTIME_LINK);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROMTIME_LINK);
	}
	/**
	 * Check popup contains calendar and time of edited work plan
	 * @param task
	 * @param from
	 * @param to
	 * @param fromTime
	 * @param toTime
	 */
	public void checkPopupContainsCalendarTime(String task,String from,String to,String fromTime,String toTime){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_LINK);
		info("check popup contains calendar and time of edited work plan");
		//cut 0 to get round number, ex 07 > 7
		if(from.startsWith("0")){
			from=from.substring(1);
		}
		if(to.startsWith("0")){
			to=to.substring(1);
		}
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TOTIME_SELECTED.replace("$time", toTime));
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROMTIME_SELECTED.replace("$time", fromTime));
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROM_SELECTED.replace("$day", from));
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TO_SELECTED.replace("$day", to));
	}
	/**
	 * Check all day checkbox by default
	 * @param task
	 */
	public void checkAllDayCheckboxByDefault(String task){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_LINK);
		info("check all day checkbox");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_ALLDAY_CHECKBOX_CHECK);
	}
	/**
	 * Delete workplan
	 */
	public void deleteTaskWorkPlan(){
		click(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_DELETE_ICON);
		Utils.pause(500);
		waitForElementNotPresent(ELEMENT_RIGHT_PANE_TASK_WORKPLAN_VISIBLE);
	}
	/**
	 * Edit priority
	 * @param task
	 * @param priority
	 */
	public void editTaskPriority(String task,String priority){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_PRIORITY_LINK);
		info("edit priority");
		selectQuick(ELEMENT_RIGHT_PANE_TASK_PRIORITY_SELECT_LINK,priority);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_PRIORITY_TEXT.replace("$priority",priority));
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_PRIORITY_ICON.replace("$priority",priority));
	}
	/**
	 * Check default value of priority
	 * @param task
	 * @param dfpriority
	 * 					default value of priority
	 * @param priorities
	 * 					list value of priority
	 */
	public void checkDefaultPriority(String task,String dfpriority,String...priorities){
		openTask(task);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_PRIORITY_TEXT.replace("$priority",dfpriority));
		click(ELEMENT_RIGHT_PANE_TASK_PRIORITY_LINK);
		info("check priority combobox");
		for (String priority : priorities) {
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_PRIORITY_SELECT.replace("$opt",priority));
		}
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
	/**
	 * set due date for task
	 * @param task
	 * @param day
	 * 				due date of task. ex: 10 (Aug 2015) 
	 */
	public void setDueDate(String task, String date, String day,int gap){
		info("set due date for " +task);
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_DUEDATE_LINK);
		
		//if not the same year
		if(gap>0){
			for(int i=0;i<gap;i++){
				clickByJavascript(ELEMENT_RIGHT_PANE_TASK_DUEDATE_NEXTMONTH_ICON);
			}
		}
		//cut 0 to get round number, ex 07 > 7
		if(day.startsWith("0")){
			day=day.substring(1);
		}
		mouseHoverByJavaScript(ELEMENT_RIGHT_PANE_TASK_DUEDATE_DAY.replace("$day", day),2);
		clickByJavascript(ELEMENT_RIGHT_PANE_TASK_DUEDATE_DAY.replace("$day", day),2);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_TEXT.replace("$date",date));
	}
	/**
     * Define options of duedate
     *
     */
	public enum optDueDate{
		None,Today,Tomorrow,NextWeek;
	}
	
	/**
	 * select duedate
	 * @param task
	 * @param opt
	 * 			None,Today...
	 */
	public void selectDueDate(String task,optDueDate opt ){
		openTask(task);
		click(ELEMENT_RIGHT_PANE_TASK_DUEDATE_LINK);
		switch(opt){
		case None:
			info("select None");
			click(ELEMENT_RIGHT_PANE_TASK_DUEDATE_NONE,0,true);
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_NODUEDATE);
			break;
		case Today:
			info("select Today");
			click(ELEMENT_RIGHT_PANE_TASK_DUEDATE_TODAY,0,true);
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_TEXT.replace("$date",getDate(0,"dd MMM yyyy")));
			break;
		case Tomorrow:
			info("select Tomorrow");
			click(ELEMENT_RIGHT_PANE_TASK_DUEDATE_TOMORROW,0,true);
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_TEXT.replace("$date",getDate(1,"dd MMM yyyy")));
			break;
		case NextWeek:
			info("select Next Week");
			click(ELEMENT_RIGHT_PANE_TASK_DUEDATE_NEXTWEEK,0,true);
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_TEXT.replace("$date",getDate(7,"dd MMM yyyy")));
			break;
		default:
			info("No option in the list.Please select correct option.");
			break;
		}
	}
	/**
	 * Check display of Duedate popup
	 * @param opt
	 */
	public void checkDisplayOfDueDatePopup(optDueDate opt){
		click(ELEMENT_RIGHT_PANE_TASK_DUEDATE_LINK);
		switch(opt){
		case None:
			info("None is selected");
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_NONE);
			break;
		case Today:
			info("Today is selected");
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_TODAY);
			break;
		case Tomorrow:
			info("Tomorrow is selected");
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_TOMORROW);
			break;
		case NextWeek:
			info("Next Week is selected");
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_NEXTWEEK);
			break;
		default:
			info("No option in the list.Please select correct option.");
			break;
		}
	}
	/**
	 * Check display of Duedate popup by default
	 * @param task
	 */
	public void checkDisplayOfDueDatePopupByDefault(String task){
		openTask(task);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_NODUEDATE);
		click(ELEMENT_RIGHT_PANE_TASK_DUEDATE_LINK);
		info("check display of Duedate popup");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_CALENDAR);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_NONE);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_TODAY);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_TOMORROW);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_NEXTWEEK);
	}
	/**
	 * Check selected day in Duedate popup
	 * @param day
	 */
	public void checkSelectedDay(String day){
		//cut 0 to get round number, ex 07 > 7
		if(day.startsWith("0")){
			day=day.substring(1);
		}
		click(ELEMENT_RIGHT_PANE_TASK_DUEDATE_LINK);
		info("check selected day");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_DUEDATE_DAY_SELECTED.replace("$day", day));
	}
	/**
	 * check display of List View 
	 * @param list of tasks
	 */
	public void checkDisplayOfListView(String...tasks){
		info("check display of tasks in List View");
		for (String task : tasks) {
			waitForAndGetElement(ELEMENT_TASK_DUEDATE.replace("$task", task).replace("$day",getDate(0,"MMM dd")));
			waitForAndGetElement(ELEMENT_TASK_TITLE.replace("$task", task));
			mouseOverAndClick(ELEMENT_TASK_TITLE.replace("$task", task));
			waitForAndGetElement(ELEMENT_TASK_COMPLETE_ICON.replace("$task", task));
		}
	}
	/*
	 * complete task
	 * @param task
	 */
	public void completeTask(String task){
		info("complete task:"+task );
		mouseHoverByJavaScript(ELEMENT_TASK_TITLE.replace("$task", task),2);
		clickByJavascript(ELEMENT_TASK_COMPLETE_ICON.replace("$task", task),2);
		Utils.pause(500);
		waitForElementNotPresent(ELEMENT_TASK_TITLE.replace("$task", task));
	}
	/**
	 * Check display of task checkbox
	 * @param task
	 */
	public void checkDisplayOfTaskCheckbox(String task){
		info("check display of task checkbox");
		mouseHoverByJavaScript(ELEMENT_TASK_TITLE.replace("$task", task),2);
		waitForAndGetElement(ELEMENT_TASK_COMPLETE_ICON.replace("$task", task));
		openTask(task);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_COMPLETE_ICON.replace("$task", task));
	}
	/**
     * Define options of duedate
     *
     */
	public enum optDueDateLV{
		Date,Year,Today,Tomorrow,Yesterday;
	}
	/**
	 * check display of due date in List View
	 * @param task
	 * @param opt
	 * 			 date, year, today, tomorrow, yesterday
	 */
	public void checkDueDateInListView(optDueDateLV opt,String...tasks){
		switch(opt){
		case Date:
			info("check for same year");
			for (String task : tasks) {
				waitForAndGetElement(ELEMENT_TASK_DUEDATE.replace("$task", task).replace("$day",getDate(0,"MMM dd")));
			}
			break;
		case Year:
			info("check for different year");
			for (String task : tasks) {
				waitForAndGetElement(ELEMENT_TASK_DUEDATE.replace("$task", task).replace("$day",getDateOfNextYear("MMM dd, yyyy", 1)));
			}
			break;
		case Today:
			info("check for today");
			for (String task : tasks) {
				waitForAndGetElement(ELEMENT_TASK_DUEDATE_TODAY.replace("$task", task));
			}
			break;
		case Tomorrow:
			info("check for tomorrow");
			for (String task : tasks) {
				waitForAndGetElement(ELEMENT_TASK_DUEDATE_TOMORROW.replace("$task", task));
			}
			break;
		case Yesterday:
			info("check for yesterday");
			for (String task : tasks) {
				waitForAndGetElement(ELEMENT_TASK_DUEDATE_YESTERDAY.replace("$task", task));
			}
			break;
		default:
			info("No option in the list.Please select correct option.");
			break;
		}
	}
	/**
	 * check list of workflow
	 * @param task
	 * @param flow  default flow
	 * @param flows
	 * 				list of flows
	 */
	public void checkListFlow(String task,String flow,String...flows){
		openTask(task);
		info("check default flow");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_STATUS_TEXT.replace("$flow", flow));
		click(ELEMENT_RIGHT_PANE_TASK_STATUS_LINK);
		click(ELEMENT_RIGHT_PANE_TASK_STATUS_SELECT_LINK);
		Utils.pause(1000);
		info("check list flow");
		for (String eflow : flows) {
			waitForAndGetElement(ELEMENT_RIGHT_PANE_TASK_STATUS_SELECT.replace("$opt",eflow));
		}
		
	}
	/**
	 * Check display of change
	 * @param user
	 * @param text
	 * @param opt
	 */
	public void checkDisplayOfChange(String user,String text,String...opt){
		click(ELEMENT_RIGHT_PANE_CHANGE_TAB_LINK);
		info("check display of change");
		if(opt.length>0){
			waitForAndGetElement(ELEMENT_RIGHT_PANE_CHANGE_TEXT.replace("$user", user).replace("$text", text).replace("$opt", opt[0]));
		}else{
			waitForAndGetElement(ELEMENT_RIGHT_PANE_CHANGE_TEXT.replace("$user", user).replace("$text", text));
		}
	}
	/**
	 * Check display of time change
	 * @param user
	 * @param text
	 * @param time
	 */
	public void checkDisplayOfChangeTime(String user,String text,String time){
		click(ELEMENT_RIGHT_PANE_CHANGE_TAB_LINK);
		info("check display of time change");
		waitForAndGetElement(ELEMENT_RIGHT_PANE_CHANGE_TIME.replace("$user", user).replace("$text", text).replace("$time", time));
	}
	/**
	 * Add new task in Board
	 * @param colId
	 * @param isNone
	 * 				true if group by none
	 * 				false if group by assignee
	 * @param user
	 */
	public void addTaskInBoard(int colId,boolean isNone,String project,String task,String user){
		if(isNone){
			    info("group by none");
			    mouseOverAndClick(ELEMENT_BOARD_TASK_CONTAINER.replace("$num", String.valueOf(colId)));
			    waitForAndGetElement(ELEMENT_BOARD_TASK_TITLE_INPUT.replace("$num", String.valueOf(colId))).sendKeys(task);
		        click(ELEMENT_PROJECT_TITLE.replace("$project", project));
		        waitForAndGetElement(ELEMENT_BOARD_TASK_TITLE.replace("$task", task).replace("$num", String.valueOf(colId)));
		}else{
			info("group by assignee");
				mouseOverAndClick(ELEMENT_BOARD_TASK_CONTAINER_GROUPBY_ASSIGNEE.replace("$num", String.valueOf(colId)).replace("$user", user));
				waitForAndGetElement(ELEMENT_BOARD_TASK_TITLE_GROUPBY_ASSIGNEE_INPUT.replace("$num", String.valueOf(colId)).replace("$user", user)).sendKeys(task);
				click(ELEMENT_PROJECT_TITLE.replace("$project", project));
		        waitForAndGetElement(ELEMENT_BOARD_TASK_TITLE.replace("$task", task).replace("$num", String.valueOf(colId)));
		   
		}
	}
	/**
	 * Check drag and drop task
	 * @param task
	 * @param col
	 * @param target
	 * 				is target column if different column
	 * 				is target item in the same column if same column
	 * @param isSameCol
	 */
	public void checkDragDropTask(String task,int col,int target,boolean isSameCol){
		info("drag task");
		mouseOverAndClick(ELEMENT_BOARD_TASK_BOX.replace("$task", task));
		if(isSameCol){
			dragAndDropToObject(ELEMENT_BOARD_TASK_DRAG_ICON.replace("$task", task).replace("$num", String.valueOf(col)), ELEMENT_BOARD_COL_ITEM.replace("$num1", String.valueOf(col)).replace("$num2", String.valueOf(target)));
			waitForAndGetElement(ELEMENT_BOARD_COL_ITEM_TASK.replace("$task", task).replace("$num1", String.valueOf(col)).replace("$num2", String.valueOf(target)));
		}else{
			dragAndDropToObject(ELEMENT_BOARD_TASK_DRAG_ICON.replace("$task", task).replace("$num", String.valueOf(col)), ELEMENT_BOARD_TASK_CONTAINER.replace("$num", String.valueOf(target)));
			waitForAndGetElement(ELEMENT_BOARD_TASK_TITLE.replace("$task", task).replace("$num", String.valueOf(target)));
		}
	}
}
