package org.exoplatform.selenium.platform.task;

import static org.exoplatform.selenium.TestLogger.info;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.exoplatform.selenium.Utils;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;

/**
 * This class will define actions about management tasks
 *
 */


public class ManagementProjects extends TaskManagementLocatorObject {

	public ManagementProjects(WebDriver dr){
		this.driver=dr;
	}
	/**
	 * Open Projects 
	 */
	public void goToProjects(){
		info("open Projects");
		click(ELEMENT_LEFT_PANE_PROJECTS,0,true);
		Utils.pause(1000);
	}
	/**
	 * Open Labels
	 */
	public void goToLabels(){
		info("open Labels");
		click(ELEMENT_LEFT_PANE_LABELS,0,true);
		Utils.pause(1000);
	}
	/**
	 * Open project
	 */
	public void openProject(String project){
		info("open project: "+project);
		click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project),0,true);
		Utils.pause(1000);
	}
	/**
	 * Close right pane
	 */
	public void closeProject(){
		info("close project detail");
		click(ELEMENT_PROJECT_CLOSE_ICON);
		waitForElementNotPresent(ELEMENT_PROJECT_CLOSE_ICON);
	}
	/**
	 * Open Board view
	 */
	public void openBoard(){
		info("open Board view");
		doubleClickOnElement(ELEMENT_BOARD_VIEW);
		Utils.pause(500);
	}
	/**
	 * Hide project detail on right pane
	 */
	public void hideProjectDetail(){
		info("hide detail of project on right pane");
		click(ELEMENT_PROJECT_HIDE_ICON);
		waitForElementNotPresent(ELEMENT_PROJECT_CLOSE_ICON);
	}
	/**
	 * Open Context Menu of Projects by clicking on "+" icon
	 */
	public void goToContMenuProject(){
		info("Click on + icon to open Context Menu of Projects");
		clickByJavascript(ELEMENT_LEFT_PANE_PROJECTS_PLUS_MENU,2);
		Utils.pause(1000);
	}
	
	/**
	 * Define options in Context Menu of Projects category
	 */
	public enum optionContMenuProject{
		Add_Project,Show_Hidden_Project,Hide_Hidden_Project;
	}
	
	/**
	 * Select an option in Context Menu of Projects
	 * @param op
	 *           is an option in Context Menu as:Add Project, Show hidden project.
	 */ 
	public void selectOpContMenuProject(optionContMenuProject op){
		goToContMenuProject();
		switch(op){
		case Add_Project:
			info("Select Add Project option");
			click(ELEMENT_LEFT_PANE_PROJECTS_ADD,0,true);
			break;
		case Show_Hidden_Project:
			info("Select Show hide Project");
			click(ELEMENT_LEFT_PANE_PROJECTS_SHOWHIDDEN,0,true);
			break;
		case Hide_Hidden_Project:
			info("Select Hide hide Project");
			click(ELEMENT_LEFT_PANE_PROJECTS_HIDEHIDDEN,0,true);
			break;
		default:
			info("No option in the list. Please select correct option.");
			break;
		}
	}
	
	/**
	 * Open Context menu of a given project
	 * @param project
	 *                is a project's name in the list
	 */
	public void goToContMenuGivenProject(String project){
		info("Right click on a project in the list");
		mouseHoverByJavaScript(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project",project),2);
		Utils.pause(1000);
		clickByJavascript(ELEMENT_LEFT_PANE_PROJECT_MENU.replace("$project", project),2);
	}
	
	/**
	 * Define options in Context Menu of a given project in Project list
	 */
	public enum optionContMenuGivenProject{
		Edit,Share,Clone,Hide,Show,Delete,Add_Project;
	}
	
	/**
	 * Select an option in Context Menu of a Given Project in Project list
	 * @param project
	 *               is project's name of a given project in the list
	 * @param op
	 *               is an option in Context Menu as Edit,Share,Clone,...
	 */
	public void selectOpContMenuGivenProject(String project,optionContMenuGivenProject op){
	    goToContMenuGivenProject(project);
		switch(op){
	    case Edit:
	    	info("Select Edit option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_PROJECT_EDIT.replace("$project", project),2);
	    	break;
	    case Share:
	    	info("Select Share option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_PROJECT_SHARE.replace("$project", project),2);
	    	break;
	    case Clone:
	    	info("Select Clone option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_PROJECT_CLONE.replace("$project", project),2);
	    	break;
	    case Hide:
	    	info("Select Hide option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_PROJECT_HIDE.replace("$project", project),2);
	    	break;
	    case Show:
	    	info("Select Show option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_PROJECT_SHOW.replace("$project", project),2);
	    	break;
	    case Delete:
	    	info("Select Delete option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_PROJECT_DELETE.replace("$project", project),2);
	    	break;
	    case Add_Project:
	    	info("Select Add Project option");
	    	clickByJavascript(ELEMENT_LEFT_PANE_PROJECT_ADD.replace("$project", project),2);
	    	break;
	    default:
	    	info("No option in the list. Please select correct option");
	    	break;
	    }
	}
	/**
	 * Change color of a given project 
	 * @param project
	 *                is project's name
	 * @param color
	 *                is a color in color list as pink, red,sky_blue,...
	 *                these colors will be read from ColorDatabase.java file
	 */
	public void selectColor(String project,String color,String...tasks){
		goToContMenuGivenProject(project);
		info("Select "+color+" in color list");
		click(ELEMENT_LEFT_PANE_PROJECT_COLOR_TABLE_ITEM.replace("$project",project).replace("$color",color));
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_COLOR.replace("$project", project).replace("$color", color));
		if(tasks.length>0){
			for (String task : tasks) {
				openProject(project);
				Utils.pause(1000);
				waitForAndGetElement(ELEMENT_TASK_COLOR.replace("$task", task).replace("$color", color));
			}
		}
	}
	
	/**
	 * Check add project popup
	 */
	public void checkAddProjectPopup(){
		info("check Add Project popup");
		selectOpContMenuProject(optionContMenuProject.Add_Project);
		waitForAndGetElement(ELEMENT_ADD_PROJECT_HEADER);
		waitForAndGetElement(ELEMENT_PROJECT_PARENT_PATH_TEXT.replace("$project", "Projects"));
		waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_CKEDITOR_INPUT);
		waitForAndGetElement(ELEMENT_ADD_PROJECT_TITLE_INPUT);
		waitForAndGetElement(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR);
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_SAVE_BTN);
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_CANCEL_BTN);
	}
	/**
	 * Check edit project popup
	 */
	public void checkEditProjectPopup(String project){
		info("check edit Project popup");
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Edit);
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_HEADER);
		waitForAndGetElement(ELEMENT_PROJECT_PARENT_PATH_LINK);
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT_LINK);
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_DES_INPUT_LINK);
		waitForAndGetElement(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR);
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_SAVE_BTN);
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_CANCEL_BTN);
	}
	/**
	 * Check cancel action when adding new project
	 * @param project
	 */
	public void checkCancelAction(String project){
		info("Check cancel action");
		type(ELEMENT_ADD_PROJECT_TITLE_INPUT,project,true);
		waitForElementNotPresent(ELEMENT_EDIT_PROJECT_SAVE_BTN_DISABLED);
		cancelAddProject();
		waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
	}
	/**
	 * Add a new project
	 * @param title
	 *              is project's name
	 * @param des
	 *              is project's description
	 * @param enableCalendar
	 *              = true if want to create a task calendar for the project in Calendar application
	 *              = false if don't want.
	 */
	public void addProject(String title,String des,String projectPath,boolean enableCalendar){
		selectOpContMenuProject(optionContMenuProject.Add_Project);
		info("Create project:" + title);
		if(!des.isEmpty()){
			info("Input description");
			inputFrame(ELEMENT_CKEDITOR_IFRAME, des);
		}
		if(!title.isEmpty()){
			info("Input title");
			type(ELEMENT_ADD_PROJECT_TITLE_INPUT,title,true);
		}
		if(!projectPath.isEmpty()){
			info("Input project path");
			searchProjectPath(projectPath);
		}
		if(enableCalendar){
			info("Enable Calendar intergration");
			check(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}else{
			info("Disable Calendar intergration");
			uncheck(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}
		saveAddProject();
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", title));
		waitForElementNotPresent(ELEMENT_LEFT_PANE_TOOLTIP_PROJECT);
		//waitForAndGetElement(ELEMENT_TEXT_NOTASK_DEFAULT);
	}
	/**
	 * Check project detail
	 * @param title
	 * @param des
	 * @param projectPath
	 */
	public void checkProjectDetail(String title,String des,String projectPath){
		selectOpContMenuGivenProject(title, optionContMenuGivenProject.Edit);
		info("Check project detail" + title);
		if(!title.isEmpty()){
			info("check title");
			waitForAndGetElement(ELEMENT_ADD_PROJECT_TITLE_TEXT.replace("$title", title));
		}
		if(!des.isEmpty()){
			info("check description");
			waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_TEXT.replace("$des", des));
		}
		if(!projectPath.isEmpty()){
			info("check project path");
			waitForAndGetElement(ELEMENT_ADD_PROJECT_PARENT_PROJECT_TEXT.replace("$path", projectPath));
		}
	}
	/**
	 * Check project detail by default
	 * @param project
	 */
	public void checkProjectDetailByDefault(String project){
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Edit);
		waitForAndGetElement(ELEMENT_PROJECT_PARENT_PATH_TEXT.replace("$project", "Projects"));
		waitForAndGetElement(ELEMENT_WELCOME_TEXT_PROJECT1);
		waitForAndGetElement(ELEMENT_WELCOME_TEXT_PROJECT2);
		mouseOver(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project),false);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_MENU.replace("$project",project));
		cancelEditProject();
	}
	
	/**
	 * Add sub-project
	 * @param parent
	 *              is title of project parent
	 * @param child
	 * 				is title of subproject
	 * @param des
	 *              is project's description
	 * @param enableCalendar
	 *              = true if want to create a task calendar for the project in Calendar application
	 *              = false if don't want.
	 */
	public void addSubProject(String parent, String child, String des,String path,boolean enableCalendar){
		selectOpContMenuGivenProject(parent, optionContMenuGivenProject.Add_Project);
		info("Create project:" + child);
		if(!des.isEmpty()){
			info("Input description");
			inputFrame(ELEMENT_CKEDITOR_IFRAME, des);
		}
		if(!child.isEmpty()){
			info("Input title");
			type(ELEMENT_ADD_PROJECT_TITLE_INPUT,child,true);
		}
		if(!path.isEmpty()){
			info("Input project path");
			searchProjectPath(path);
		}
		if(enableCalendar){
			info("Enable Calendar intergration");
			check(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}else{
			info("Disable Calendar intergration");
			uncheck(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}
		saveAddProject();
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", child));
	}
	/**
	 * Enable calendar integration
	 * @param project
	 * @param isEnable
	 */
	public void enableCalendar(String project,boolean isEnable){
		openProject(project);
		if(isEnable){
			info("Enable Calendar intergration");
			check(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}else{
			info("Disable Calendar intergration");
			uncheck(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}
	}
	/**
	 * Save all changes for adding project
	 */
	public void saveAddProject(){
		info("Click on Save button");
		click(ELEMENT_EDIT_PROJECT_SAVE_BTN,0,true);
		Utils.pause(2000);
	}
	
	/**
	 * Cancel all changes for adding project
	 */
	public void cancelAddProject(){
		info("Click on Cancel button");
		click(ELEMENT_EDIT_PROJECT_CANCEL_BTN,0,true);
		Utils.pause(1000);
	}
	
	/**
	 * Open a project
	 * @param projectPath
	 *                    is project's names as Project1/sub_Project1
	 */
	public void selectProject(String projectPath){
		info("Open a project");
		String[] projects = projectPath.split("/");
		for(String project:projects){
			info("Click on "+project+" on left panel");
			openProject(project);
		}
		Utils.pause(2000);
		
	}
	/**
	 * Edit a project
	 * @param projectPath 
	 *                    is project's names as Project1/sub_Project1
	 * @param title
	 *                    is a new project's title to edit
	 * @param des
	 *                    is a new description of the project to edit
	 * @param opt
	 *                   >0 and opt[0]==true if want to input new title/description with keeping all old title/description
	 *                   =0 or <0 if want to input new title/description after cleared all old title/description
	 *                   >1 and opt[1]==true if want to enable calendar integration
	 *                   >0 and <1 if want to disable or uncheck calendar integration
	 */
	public void editProject(String projectPath,String title,String des,String path,boolean... opt){
		selectOpContMenuGivenProject(projectPath, optionContMenuGivenProject.Edit);
		if(!title.isEmpty()){
			info("Input title");
			click(ELEMENT_ADD_PROJECT_TITLE_TEXT.replace("$title", projectPath),0,true);
			type(ELEMENT_EDIT_PROJECT_TITLE_INPUT,title,true);
		}
		if(!des.isEmpty()){
			info("Input description");
			click (ELEMENT_EDIT_PROJECT_DES_INPUT_LINK,0,true);
			inputFrame(ELEMENT_CKEDITOR_IFRAME, des);
		}
		if(!path.isEmpty()){
			info("Input project path");
			searchProjectPath(path);
		}
		if(opt.length>0 && opt[0]==true){
			info("Enable Calendar intergration");
			check(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}else{
			info("Disable Calendar intergration");
			uncheck(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}
		saveEditProject();
	}
	
	/**
	 * Save all changes when editing a project
	 */
	public void saveEditProject(){
		info("Click on Save button");
		click(ELEMENT_EDIT_PROJECT_SAVE_BTN,0,true);
		Utils.pause(2000);
	}
	
	/**
	 * Cancel all changes when editing a project
	 */
	public void cancelEditProject(){
		info("Click on Cancel button");
		click(ELEMENT_EDIT_PROJECT_CANCEL_BTN,0,true);
		Utils.pause(1000);
	}
	/**
	 * Change Project Parent
	 * @param project
	 * @param parentPrj
	 */
	public void changeParentProject(String project,String parentPrj){
		info("Click on Parent project field");
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Edit);
		click(ELEMENT_PROJECT_PARENT_PATH_LINK,0,true);
		click(ELEMENT_PARENT_PATH_DROPDOWN_MENU.replace("$project",parentPrj),0,true);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_PROJECT_PARENT_PATH_TEXT.replace("$project", parentPrj));
	}
	
	/**
	 * Share a Project
	 * @throws AWTException 
	 * @param project
	 * @param user
	 * 		can be a user/users/group user
	 * @param manager
	 * 		true if user is setted as manager
	 * 		false if user is setted as paticipant
	 */
	public void shareProject(String project,String[] users, boolean manager) {
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Share);
		Utils.pause(1000);
		if (manager){
			click(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_ICON);
			for (String user : users) {
				type(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_INPUT,user,false);
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
			}
			click(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SAVE_BTN);
		}
		else{
			click(ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_ICON);
			for (String user : users) {
				type(ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_INPUT,user,false);
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
			}
			click(ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SAVE_BTN);
		}
		Utils.pause(5000);
		click(ELEMENT_CLOSE_BTN);
	}
	
	/**
	 * Delete project
	 * @param project
	 * @param subPrj = true if delete also all sub-projects
	 * 				 = false if not delete
	 */
	public void deleteProject(String project,boolean subPrj,String...subs){
		info("delete project:" + project);
		selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		if(subPrj){
			info("also delete all sub-projects");
			check(ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX,2);
			click(ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN);
			Utils.pause(2000);
			if(subs.length>0){
			for (String subprj : subs) {
				waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", subprj));
				}
			}
		}else{
			info("no delete all sub-projects");
			uncheck(ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX,2);
			click(ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN);
			Utils.pause(2000);
			if(subs.length>0){
			for (String subprj : subs) {
				waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", subprj));
				}
			}
		}
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
	}
	/**
	 * Delete project by data id
	 * @param project
	 * @param id
	 * 			data-id of project (for case: projects have the same name)
	 * @param subPrj = true if delete also all sub-projects
	 * 				 = false if not delete
	 */
	public void deleteProjectByDataId(String project,String id,boolean subPrj,String...subs){
		info("delete project:" + project);
		clickByJavascript(ELEMENT_LEFT_PANE_PROJECTID_MENU.replace("$project", project).replace("$id", id),2);
		Utils.pause(1000);
		click(ELEMENT_LEFT_PANE_PROJECTID_DELETE.replace("$project", project).replace("$id", id),0,true);
		if(subPrj){
			info("also delete all sub-projects");
			check(ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX,2);
			click(ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN);
			Utils.pause(2000);
			if(subs.length>0){
			for (String subprj : subs) {
				waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", subprj));
				}
			}
		}else{
			info("no delete all sub-projects");
			uncheck(ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX,2);
			click(ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN);
			Utils.pause(2000);
			if(subs.length>0){
			for (String subprj : subs) {
				waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", subprj));
				}
			}
		}
		Utils.pause(2000);
		waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECT_ID.replace("$project", project).replace("$id", id));
	}
	/**
	 * Check confirm delete project
	 * @param project
	 */
	public void checkConfirmDeleteProject(String project){
		info("open delete confirmation popup");
		selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_CONFIRMATION_POPUP_TITLE);
		waitForAndGetElement(ELEMENT_DELETE_PROJECT_POPUP_CANCEL_BTN);
		waitForAndGetElement(ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN);
		waitForAndGetElement(ELEMENT_DELETE_PROJECT_POPUP_MESSAGE.replace("${project}",project));
		waitForAndGetElement(ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX_TEXT);
		click(ELEMENT_DELETE_PROJECT_POPUP_CANCEL_BTN);
	}
	/**
	 * Cancel delete project
	 * @param project
	 */
	public void cancelDeleteProject(String project){
		info("click Cancel delete");
		selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_CONFIRMATION_POPUP_TITLE);
		click(ELEMENT_DELETE_PROJECT_POPUP_CANCEL_BTN);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
	}
	/**
	 * get value attribute
	 * @param locator
	 * @return data-id of element
	 */
	public Integer getDataId(Object locator) {
		try {
			return Integer.parseInt(waitForAndGetElement(locator).getAttribute("data-id"));
		} catch (StaleElementReferenceException e) {
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);			
			Utils.pause(WAIT_INTERVAL);
			return Integer.parseInt(getValue(locator));
		} finally {
			loopCount = 0;
		}
	}
	/**
	 * Clone project
	 * @param project
	 * @param uncompleteTask = true if clone also uncompleted task
	 * 						 = false if not
	 */
	public void cloneProject(String project, boolean uncompleteTask,String...tasks) {
		info("clone project");
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Clone);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_CLONE_PROJECT_CANCEL_BUTTON);
		waitForAndGetElement(ELEMENT_CLONE_PROJECT_CLONE_BUTTON);
		waitForAndGetElement(ELEMENT_CONFIRMATION_POPUP_TITLE);
		waitForAndGetElement(ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX_TEXT);
		waitForAndGetElement(ELEMENT_CLONE_PROJECT_POPUP_MESSAGE.replace("$project", project));
		if(uncompleteTask){
			info("also clone uncompleted task");
			check(ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX,2);
			click(ELEMENT_CLONE_PROJECT_CLONE_BUTTON);
			openProject("Copy of "+project);
			Utils.pause(1000);
			for (String task : tasks) {
				waitForAndGetElement(ELEMENT_TASK_TITLE.replace("$task","Copy of "+task));
			}
		}else{
			info("no clone uncompleted task");
			uncheck(ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX,2);
			click(ELEMENT_CLONE_PROJECT_CLONE_BUTTON);
			openProject("Copy of "+project);
			Utils.pause(1000);
			if(tasks.length>0){
			for (String task : tasks) {
				waitForElementNotPresent(ELEMENT_TASK_TITLE.replace("$task","Copy of "+task));
				}
			}
		}
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+project));
	}
	/**
	 * Check cloned project inherits
	 * @param project
	 */
	public void checkClonedProjectInherits(String project, Object... opParams){
		info("check inherits");
		String parent = (String)(opParams.length > 0 ? opParams[0]: "");
		String manager = (String)(opParams.length > 1 ? opParams[1]: "");
		String color = (String)(opParams.length > 2 ? opParams[2]: "");
		String des = (String)(opParams.length > 3 ? opParams[3]: "");
		if (parent !=null && !parent.isEmpty() ){
			waitForAndGetElement(ELEMENT_PROJECT_PARENT_PATH_TEXT.replace("$project",parent));
		}
		if (manager !=null && !manager.isEmpty() ){
			waitForAndGetElement(ELEMENT_PROJECT_MANAGER_NAME.replace("$user", manager));
		}
		if (color !=null && !color.isEmpty() ){
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_COLOR.replace("$project", "Copy of "+project).replace("$color", color));
		}
		if (des !=null && !des.isEmpty() ){
			waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_TEXT.replace("$des", des));
		}
		click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", "Copy of "+project));
		
	}
	/**
	 * Cancel clone project
	 * @param project
	 */
	public void cancelCloneProject(String project){
		info("choose clone but cancel");
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Clone);
		click(ELEMENT_CLONE_PROJECT_CANCEL_BUTTON);
		Utils.pause(1000);
		waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project","Copy of "+project));
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
	 * Check menu given project of manager view
	 * @param project
	 * @param manager
	 * 				true if manager
	 * 				false if participant
	 */
	public void checkMenuGivenProjectOfUser(String project,boolean manager){
		goToContMenuGivenProject(project);
		if(manager){
			info("menu of manager");
			Utils.pause(1000);
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_EDIT.replace("$project", project));
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_ADD.replace("$project", project));
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_SHARE.replace("$project", project));
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_CLONE.replace("$project", project));
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_DELETE.replace("$project", project));
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_HIDE.replace("$project", project));
		}
		else{
			info("menu of participant");
			Utils.pause(1000);
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_HIDE.replace("$project", project));
		}
	}
	/**
	 * Check menu Projects of manager view
	 * @param manager
	 * 				true if manager
	 * 				false if participant
	 */
	public void checkMenuProjectsOfUser(boolean manager){
		goToContMenuProject();
		if(manager){
			info("menu of manager");
			Utils.pause(1000);
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECTS_SHOWHIDDEN);
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECTS_ADD);
		}
		else{
			info("menu of normal user");
			Utils.pause(1000);
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECTS_SHOWHIDDEN);
		}
	}
	/**
	 * Check GroupBy list in Projects
	 * @param project
	 * @param groups
	 * @param isBoard
	 * 				true if it's Board view
	 * 				false if not
	 */
	public void checkGroupByInProjects(String project,String[] groups,boolean isBoard){
		info("check group by list of project");
		driver.navigate().refresh();
		openProject(project);
		if(isBoard){
			openBoard();
		}
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
	 * Check SortBy list in Projects
	 * @param project
	 * @param sorts
	 * @param isBoard
	 * 				true if it's Board view
	 * 				false if not
	 */
	public void checkSortByInProjects(String project,String[] sorts,boolean isBoard){
		info("check sort by list of project");
		driver.navigate().refresh();
		openProject(project);
		if(isBoard){
			openBoard();
		}
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
	 * Check first access of group by, sort by
	 * @param isPresent
	 */
	public void checkFirstAccessGroupSort(boolean isPresent){
		Utils.pause(1000);
		if(isPresent){
			waitForAndGetElement(ELEMENT_GROUPBY_ICON);
			waitForAndGetElement(ELEMENT_SORTBY_ICON);
		}else{
			waitForElementNotPresent(ELEMENT_GROUPBY_ICON);
			waitForElementNotPresent(ELEMENT_SORTBY_ICON);
		}
	}
	
	/**
	 * Check Projects by default
	 */
	public void checkProjectsByDefault(){
		goToProjects();
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_NO_PROJECT);
		waitForAndGetElement(ELEMENT_WELCOME_IMG);
		waitForAndGetElement(ELEMENT_WELCOME_TEXT_PROJECT_DEFAULT);
		waitForAndGetElement(ELEMENT_LEFT_PANE_TOOLTIP_PROJECT);
		waitForElementNotPresent(ELEMENT_ADD_TASK_BTN);
		waitForElementNotPresent(ELEMENT_ADD_TASK_TITLE);
	}
	/**
	 * Check default setting groupBy,sortBy
	 * @param project
	 */
	public void checkDefaultGroupSort(String project,String group,String sort,boolean isBoard){
		driver.navigate().refresh();
		openProject(project);
		if(isBoard){
			openBoard();
		}
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_SORTBY_ITEM_DEFAULT.replace("$sort",sort));
		waitForAndGetElement(ELEMENT_GROUPBY_ITEM_DEFAULT.replace("$group", group));
	}
	/**
	 * Check default shared project
	 * @param project
	 */
	public void checkDefaultSharedProject(String project){
		openProject(project);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_WELCOME_TEXT_PROJECT_SHARE1);
		waitForAndGetElement(ELEMENT_WELCOME_TEXT_PROJECT_SHARE2);
	}
	/**
	 * Check share users
	 * @param project
	 * @param managers
	 * @param participants
	 */
	public void checkShareUsers(String project,String[] managers,String[] participants){
		info("check list of manager, participant");
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Share);
		Utils.pause(1000);
		if(participants.length>0 && !participants[0].isEmpty()){
		for (String participant : participants) {
			waitForAndGetElement(ELEMENT_SHARE_PROJECT_DISPLAY_PARTICIPANT.replace("$user",participant),DEFAULT_TIMEOUT,1);
			}
		}
		if(managers.length>0 && managers[0].isEmpty()){
		for (String manager : managers) {
			waitForAndGetElement(ELEMENT_SHARE_PROJECT_DISPLAY_MANAGER.replace("$user",manager),DEFAULT_TIMEOUT,1);
			}
		}
		Utils.pause(1000);
		click(ELEMENT_CLOSE_BTN);
	}
	/**
	 * Check share project popup
	 */
	public void checkShareProjectPopup(){
		click(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_ICON);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_INPUT);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SAVE_BTN);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SELECT_GROUP_ICON);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SELECT_USER_ICON);
		click(ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_ICON);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_INPUT);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SAVE_BTN);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SELECT_GROUP_ICON);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SELECT_USER_ICON);
	}
	/**
	 * Check color table of project
	 * @param project
	 */
	public void checkColorTable(String project){
		goToContMenuGivenProject(project);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_COLOR_TABLE_ITEM.replace("$project", project).replace("$color", "noColor"));
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_COLOR_TABLE_ITEM.replace("$project", project).replace("$color", "red"));
		goToContMenuGivenProject(project);
	}
	/**
	 * Decorate description
	 * @param project
	 * @param des
	 * 			 description of project
	 */
	public void decorateDescription(String project, String des){
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Edit);
		click(ELEMENT_EDIT_PROJECT_DES_INPUT_LINK,0,true);
		if(des.isEmpty()){
			info("left description blank");
			inputFrame(ELEMENT_CKEDITOR_IFRAME, "");
			
		}else{
			info("decorate description");
			inputFrame(ELEMENT_CKEDITOR_IFRAME, des);
			cke_Bold();
		}
		saveEditProject();
	}
	/**
	 * Check decorate description
	 * @param project
	 * @param des
	 */
	public void checkDecorateDescription(String project,String des){
		info("check decorate description");
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Edit);
		if(!des.isEmpty())
			waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_CKEDITOR.replace("$des", des));
		else
			waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_EMPTY);	
		cancelEditProject();
	}
	/**
	 * Search project in Project Path
	 * @param key: search key
	 */
	public void searchProjectPath(String key){
		info("search project");
		click(ELEMENT_PROJECT_PARENT_PATH_LINK);
		type(ELEMENT_EDIT_PROJECT_PATH_INPUT,key,true);
		waitForAndGetElement(ELEMENT_PARENT_PATH_MATCH_VALUE.replace("$text",key));
	}
	/**
	 * Left project title blank
	 * @param project
	 */
	public void leftProjectTitleBlank(String project){
		info("left title blank");
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Edit);
		Utils.pause(500);
		click(ELEMENT_EDIT_PROJECT_TITLE_INPUT_LINK,0,true);
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).clear();
        Utils.pause(500);
        driver.findElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).sendKeys(Keys.ENTER);
        waitForAndGetElement(ELEMENT_EDIT_PROJECT_UNTITLED);
	}
	/**
	 * Hide project
	 * @param project
	 * @param params
	 * 				sub-project if it has
	 */
	public void hideProject(String project,String...prjs){
		info("hide project");
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Hide);
		waitForTextNotPresent(project);
		if(prjs.length>0){
			for (String prj : prjs) {
				waitForTextNotPresent(prj);
			}
		}
	}
	/**
	 * Show project
	 * @param project
	 * @param params
	 * 				sub-project if it has
	 */
	public void showProject(String project,String...prjs){
		info("show project");
		selectOpContMenuProject(optionContMenuProject.Show_Hidden_Project);
		Utils.pause(1000);
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Show);
		Utils.pause(1000);
		waitForTextPresent(project);
		if(prjs.length>0){
			for (String prj : prjs) {
				waitForTextPresent(prj);
			}
		}
		selectOpContMenuProject(optionContMenuProject.Hide_Hidden_Project);
	}
	/**
	 * Check children project
	 * @param project
	 * @param sub: sub-project
	 */
	public void checkChildrenProject(String prj,String sub){
		click(ELEMENT_PROJECT_PARENT_PATH_LINK);
		Utils.pause(500);
		waitForAndGetElement(ELEMENT_PARENT_PATH_FULL.replace("$parent", prj).replace("$child",sub));
	}
	/**
	 * Check display of List, Board
	 * @param project
	 * @param isPresent
	 */
	public void checkDisplayOfListBoard(String project,boolean isPresent){
		openProject(project);
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
	 * Check display of left pane
	 */
	public void checkDisplayOfLeftPane(){
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECTS);
		waitForAndGetElement(ELEMENT_LEFT_PANE_LABELS);
		waitForAndGetElement(ELEMENT_LEFT_PANE_TASKS);
		waitForAndGetElement(ELEMENT_LEFT_PANE_INCOMING);
		waitForAndGetElement(ELEMENT_LEFT_PANE_ALLTASKS);
		waitForAndGetElement(ELEMENT_LEFT_PANE_TODAY);
		waitForAndGetElement(ELEMENT_LEFT_PANE_TOMORROW);
		waitForAndGetElement(ELEMENT_LEFT_PANE_OVERDUE);
		waitForAndGetElement(ELEMENT_LEFT_PANE_UPCOMING);
	}
	/**
	 * Define options in No New Task
	 */
	public enum optionNoNewTask{
		Projects,Labels,Overdue,Normal;
	}
	/**
	 * check top of list view
	 * @param project
	 * @param newTask
	 * 				true if it'll displayed
	 * 				false if it won't displayed
	 * @param opt 
	 * 			can select Projects,Labels,...
	 */
	public void checkTopOfListView(String project,boolean newTask,optionNoNewTask opt){
	
			switch(opt){
			case Projects:
				info("Select Projects option");
				goToProjects();
				Utils.pause(1000);
				waitForElementNotPresent(ELEMENT_ADD_TASK_BTN);
				waitForElementNotPresent(ELEMENT_ADD_TASK_TITLE);
				break;
			case Labels:
				info("Select Labels option");
				goToLabels();
				Utils.pause(1000);
				waitForElementNotPresent(ELEMENT_ADD_TASK_BTN);
				waitForElementNotPresent(ELEMENT_ADD_TASK_TITLE);
				break;
			case Overdue:
				info("Select Overdue option");
				openProject("Overdue");
				Utils.pause(1000);
				waitForElementNotPresent(ELEMENT_ADD_TASK_BTN);
				waitForElementNotPresent(ELEMENT_ADD_TASK_TITLE);
				break;
			case Normal:
				info("Select normal project");
				openProject(project);
				Utils.pause(1000);
				waitForAndGetElement(ELEMENT_ADD_TASK_BTN);
				waitForAndGetElement(ELEMENT_ADD_TASK_TITLE);
				break;
			default:
				info("No option is selected");
				break;
			}
	}
	/**
	 * Check display of Board view
	 * @param flows
	 * 				list of flow (status)
	 */
	public void checkDisplayOfBoard(String[] flows){
		info("check display of Board");
		openBoard();
		Utils.pause(1000);
		for (String flow : flows) {
			waitForAndGetElement(ELEMENT_BOARD_STATUS.replace("$flow", flow));
		}
	}
	/**
	 * Check display number of status
	 * @param status
	 * @param num
	 * 			number of task per status
	 */
	public void checkDisplayNumberOfStatus(String status,int num){
		info("status : "+status);
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_BOARD_STATUS_NUMBER.replace("$flow", status).replace("$num", String.valueOf(num)));
	}
	/**
	 * Delete status
	 * @param status
	 */
	public void deleteStatus(String status){
		info("delete status");
		mouseHoverByJavaScript(ELEMENT_BOARD_STATUS.replace("$flow", status));
		clickByJavascript(ELEMENT_BOARD_STATUS_DELETE_ICON.replace("$flow", status),2);
		Utils.pause(500);
		waitForElementNotPresent(ELEMENT_BOARD_STATUS.replace("$flow", status));
	}
	/**
	 * Check delete status
	 * @param status
	 * @param canDelete
	 */
	public void checkDeleteStatus(String status,boolean canDelete){
		info("check delete icon");
		mouseOver(ELEMENT_BOARD_STATUS.replace("$flow", status),false);
		if(canDelete){
			waitForAndGetElement(ELEMENT_BOARD_STATUS_DELETE_ICON.replace("$flow", status));
		}else
			waitForElementNotPresent(ELEMENT_BOARD_STATUS_DELETE_ICON.replace("$flow", status));
		
	}
	/**
	 * Edit status
	 * @param status
	 * @param newTitle
	 */
	public void editStatus(String status,String newTitle){
		info("change status to" + newTitle);
		doubleClickOnElement(ELEMENT_BOARD_STATUS.replace("$flow", status));
		Utils.pause(500);
		waitForAndGetElement(ELEMENT_BOARD_STATUS_TITLE_INPUT).clear();
		waitForAndGetElement(ELEMENT_BOARD_STATUS_TITLE_INPUT).sendKeys(newTitle);
        driver.findElement(ELEMENT_BOARD_STATUS_TITLE_INPUT).sendKeys(Keys.ENTER);
        Utils.pause(500);
        waitForAndGetElement(ELEMENT_BOARD_STATUS.replace("$flow", newTitle));
	}
	/**
	 * Add status
	 * @param status
	 * 				status where we add new status
	 * @param newTitle
	 * 				new status is added
	 */
	public void addStatus(String status,String newTitle){
		info("add new status "+newTitle);
		mouseHoverByJavaScript(ELEMENT_BOARD_STATUS.replace("$flow", status));
		clickByJavascript(ELEMENT_BOARD_STATUS_ADD_ICON.replace("$flow", status),2);
		waitForAndGetElement(ELEMENT_BOARD_STATUS_TITLE_INPUT_NEW).sendKeys(newTitle);
        driver.findElement(ELEMENT_BOARD_STATUS_TITLE_INPUT_NEW).sendKeys(Keys.ENTER);
        Utils.pause(500);
        waitForAndGetElement(ELEMENT_BOARD_STATUS.replace("$flow", newTitle));
	}
	/**
	 * Check No Task screen
	 * @param project
	 */
	public void checkNoTask(String project){
		openProject(project);
		Utils.pause(500);
		waitForAndGetElement(ELEMENT_TEXT_NOTASK_DEFAULT);
	}
	/**
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	 * Show left menu
	 */
	public void showLeftMenu(){
		info("show left menu");
		click(ELEMENT_LEFT_PANE_SHOWHIDE_RIGHT,0,true);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECTS);
	}
	/**
	 * Hide left menu
	 */
	public void hideLeftMenu(){
		info("hide left menu");
		click(ELEMENT_LEFT_PANE_SHOWHIDE_LEFT,0,true);
		waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECTS);
	}
	/**
	 * Check editable of Calendar Integration field
	 */
	public void checkEditableOfCalIntegrationField(){
		info("Check editable of Calendar Integration field");
		click(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_TEXT);
		waitForAndGetElement(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_TEXT);
	}
	 /** 
	  * check permission open project
=======
	 * check permission open project
>>>>>>> FQA-2696:[Task Management]- Write scripts for Labels - Edit a label
=======
	 * check permission open project
>>>>>>> FQA-2697:[Task Management]- Write scripts for Labels - Delete label
=======
	 * check permission open project
>>>>>>> FQA-2692:[Task Management]- Write scripts for RC1: Projects - Create a project
=======
	 * check permission open project
>>>>>>> FQA-2692:[Task Management]- Write scripts for RC1: Projects - Create a project
=======
	 * check permission open project
>>>>>>> FQA-2692:[Task Management]- Write scripts for RC1: Projects - Create a project
	 * @param project
	 * @param isEnable
	 */
	public void checkPermOpenProject(String project,boolean isEnable){
		info("Check permission open project");
		mouseHoverByJavaScript(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project",project),2);
		if(isEnable){
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_MENU.replace("$project", project));
		}else{
			waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECT_MENU.replace("$project", project));
		}
	}
	/**
	 * Check expanded/collapsed
	 * @param exp
	 * 			element to expand
	 * @param col
	 * 			element to collapse
	 */
	public void checkExpandCollapse(Object exp,Object col){
		info("check expaned/collapsed");
		click(exp,0,true);
		waitForAndGetElement(col);
	}
	/**
	 * Check list view permalink
	 * @param project
	 */
	public void checkPermalinkListView(String project){
		info("check permalink in list view");
		click(ELEMENT_PROJECT_PERMALINK.replace("$project", project),0,true);
		//todo
	}
	/**
	 * Check manager field
	 * @param project
	 * @param user
	 */
	public void checkManagerField(String project,String user){
		info("check manager field");
		selectOpContMenuGivenProject(project, optionContMenuGivenProject.Edit);
		waitForAndGetElement(ELEMENT_PROJECT_MANAGER_NAME.replace("$user",user));
	}
}
