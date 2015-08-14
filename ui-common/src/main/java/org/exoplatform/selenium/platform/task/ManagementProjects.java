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
	}
	/**
	 * Open Labels
	 */
	public void goToLabels(){
		info("open Labels");
		click(ELEMENT_LEFT_PANE_LABELS,0,true);
	}
	/**
	 * Open project
	 */
	public void openProject(String project){
		info("open project: "+project);
		click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project),0,true);
	}
	/**
	 * Close right pane
	 */
	public void closeProject(){
		info("close project detail");
		click(ELEMENT_RIGHT_PANE_CLOSE_ICON);
		waitForElementNotPresent(ELEMENT_RIGHT_PANE_CLOSE_ICON);
	}
	/**
	 * Hide project detail on right pane
	 */
	public void hideProjectDetail(){
		info("hide detail of project on right pane");
		click(ELEMENT_RIGHT_PANE_HIDE_ICON);
		waitForElementNotPresent(ELEMENT_RIGHT_PANE_CLOSE_ICON);
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
		Utils.pause(1000);
		mouseHoverByJavaScript(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project",project),2);
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
		click(ELEMENT_LEFT_PANE_COLOR_TABLE_ITEM.replace("$project",project).replace("$color",color));
		Utils.pause(2000);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_COLOR.replace("$project", project).replace("$color", color));
		if(tasks.length>0){
			for (String task : tasks) {
				openProject(project);
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
		waitForAndGetElement(ELEMENT_RIGHT_PANE_PARENT_PATH_TEXT.replace("$project", "Projects"));
		waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_INPUT);
		waitForAndGetElement(ELEMENT_ADD_PROJECT_TITLE_INPUT);
		waitForAndGetElement(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR);
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
	public void addProject(String title,String des,boolean enableCalendar){
		selectOpContMenuProject(optionContMenuProject.Add_Project);
		info("Create project:" + title);
		if(!des.isEmpty()){
			info("Input description");
			type(ELEMENT_ADD_PROJECT_DES_INPUT,des,true);
		}
		if(!title.isEmpty()){
			info("Input title");
			waitForAndGetElement(ELEMENT_ADD_PROJECT_TITLE_INPUT).sendKeys(title);
	        driver.findElement(ELEMENT_ADD_PROJECT_TITLE_INPUT).sendKeys(Keys.ENTER);
		}
		
		if(enableCalendar){
			info("Enable Calendar intergration");
			check(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}else{
			info("Disable Calendar intergration");
			uncheck(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}
		Utils.pause(1000);
		waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_TEXT.replace("$des", des));
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", title));
		waitForElementNotPresent(ELEMENT_LEFT_PANE_TOOLTIP_PROJECT);
	}
	/**
	 * Check project detail by default
	 * @param project
	 */
	public void checkProjectDetail(String project){
		waitForAndGetElement(ELEMENT_RIGHT_PANE_PARENT_PATH_TEXT.replace("$project", "Projects"));
		waitForAndGetElement(ELEMENT_WELCOME_TEXT_PROJECT1);
		waitForAndGetElement(ELEMENT_WELCOME_TEXT_PROJECT2);
		mouseOver(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project),false);
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_MENU.replace("$project",project));
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
	public void addSubProject(String parent, String child, String des,boolean enableCalendar){
		selectOpContMenuGivenProject(parent, optionContMenuGivenProject.Add_Project);
		info("Create project:" + child);
		if(des!=null || des!=""){
			info("Input description");
			type(ELEMENT_ADD_PROJECT_DES_INPUT,des,true);
		}
		if(child!=null || child!=""){
			info("Input title");
			//type(ELEMENT_ADD_PROJECT_TITLE,title,true);
			waitForAndGetElement(ELEMENT_ADD_PROJECT_TITLE_INPUT).sendKeys(child);
	        Utils.pause(500);
	        driver.findElement(ELEMENT_ADD_PROJECT_TITLE_INPUT).sendKeys(Keys.ENTER);
		}
		
		if(enableCalendar){
			info("Enable Calendar intergration");
			check(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}else{
			info("Disable Calendar intergration");
			uncheck(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", child));
		waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_TEXT.replace("$des", des));
		waitForAndGetElement(ELEMENT_RIGHT_PANE_PARENT_PATH_TEXT.replace("$project", parent));
	}
	
	/**
	 * Save all changes for adding project
	 */
	public void saveAddProject(){
		info("Click on Save button");
		
	}
	
	/**
	 * Cancel all changes for adding project
	 */
	public void cancelAddProject(){
		info("Click on Cancel button");
		
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
	public void editProject(String projectPath,String title,String des,boolean... opt){
		selectProject(projectPath);
		if(title!=null || title!=""){
			info("Input title");
			if(opt.length>0 && opt[0]==true)
				type(ELEMENT_ADD_PROJECT_TITLE_INPUT,title,false);//Input a new title with keeping old title
			else
				type(ELEMENT_ADD_PROJECT_TITLE_INPUT,title,true);//Input a new title with clearing all old title
		}
		if(des!=null || des!=""){
			info("Input description");
			if(opt.length>0 && opt[0]==true)
				type(ELEMENT_ADD_PROJECT_DES_INPUT,des,false);//Input a new description with keeping old description
			else 
				type(ELEMENT_ADD_PROJECT_DES_INPUT,des,true);//Input a new description with clearing all old description
		}
		
		if(opt.length>1 && opt[1]==true){
			info("Enable Calendar intergration");
			check(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}else{
			info("Disable Calendar intergration");
			uncheck(ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX,2);
		}
	}
	
	/**
	 * Save all changes when editing a project
	 */
	public void saveEditProject(){
		info("Click on Save button");
	}
	
	/**
	 * Cancel all changes when editing a project
	 */
	public void cancelEditProject(){
		info("Click on Cancel button");
	}
	/**
	 * Change Project Parent
	 * @param parentPrj
	 */
	public void changeParentProject(String parentPrj){
		info("Click on Parent project field");
		click(ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
		click(ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU.replace("$project",parentPrj));
		waitForAndGetElement(ELEMENT_RIGHT_PANE_PARENT_PATH_TEXT.replace("$project", parentPrj));
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
		Utils.pause(1000);
		clickByJavascript(ELEMENT_CLOSE_BTN,2);
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
			if(subs.length>0){
			for (String subprj : subs) {
				waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", subprj));
				}
			}
		}else{
			info("no delete all sub-projects");
			uncheck(ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX,2);
			click(ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN);
			if(subs.length>0){
			for (String subprj : subs) {
				waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", subprj));
				}
			}
		}
		Utils.pause(1000);
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
		click(ELEMENT_LEFT_PANE_PROJECT_DELETE.replace("$project", project),0,true);
		if(subPrj){
			info("also delete all sub-projects");
			check(ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX,2);
			click(ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN);
			if(subs.length>0){
			for (String subprj : subs) {
				waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", subprj));
				}
			}
		}else{
			info("no delete all sub-projects");
			uncheck(ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX,2);
			click(ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN);
			if(subs.length>0){
			for (String subprj : subs) {
				waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", subprj));
				}
			}
		}
		
		waitForElementNotPresent(ELEMENT_LEFT_PANE_PROJECT_ID.replace("$project", project).replace("$id", id));
	}
	/**
	 * Check confirm delete project
	 * @param project
	 */
	public void checkConfirmDeleteProject(String project){
		info("open delete confirmation popup");
		selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
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
		waitForAndGetElement(ELEMENT_CONFIRMATION_POPUP_TITLE);
		click(ELEMENT_DELETE_PROJECT_POPUP_CANCEL_BTN);
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
			for (String task : tasks) {
				waitForAndGetElement(ELEMENT_TASK_TITLE.replace("$task","Copy of "+task));
			}
		}else{
			info("no clone uncompleted task");
			uncheck(ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX,2);
			click(ELEMENT_CLONE_PROJECT_CLONE_BUTTON);
			openProject("Copy of "+project);
			if(tasks.length>0){
			for (String task : tasks) {
				waitForElementNotPresent(ELEMENT_TASK_TITLE.replace("$task","Copy of "+task));
				}
			}
		}
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
		if (parent !=null && parent!= "" ){
			waitForAndGetElement(ELEMENT_RIGHT_PANE_PARENT_PATH_TEXT.replace("$project",parent));
		}
		if (manager !=null && manager!="" ){
			waitForAndGetElement(ELEMENT_RIGHT_PANE_MANAGER_NAME.replace("$user", manager));
		}
		if (color !=null && color!="" ){
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_COLOR.replace("$project", "Copy of "+project).replace("$color", color));
		}
		if (des !=null && des!="" ){
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
			//Edit is not available
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_ADD.replace("$project", project));
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_SHARE.replace("$project", project));
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_CLONE.replace("$project", project));
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_DELETE.replace("$project", project));
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_HIDE.replace("$project", project));
		}
		else{
			info("menu of participant");
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
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECTS_SHOWHIDDEN);
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECTS_ADD);
		}
		else{
			info("menu of normal user");
			waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECTS_SHOWHIDDEN);
		}
	}
	/**
	 * Check GroupBy list in Projects
	 * @param project
	 * @param groups
	 */
	public void checkGroupByInProjects(String project,String[] groups){
		info("check group by list of project");
		openProject(project);
		click(ELEMENT_GROUPBY_ICON);
		for (String group : groups) {
			waitForAndGetElement(ELEMENT_GROUPBY_ITEM.replace("$item",group));
		}
	}
	/**
	 * Check SortBy list in Projects
	 * @param project
	 * @param sorts
	 */
	public void checkSortByInProjects(String project,String[] sorts){
		info("check sort by list of project");
		openProject(project);
		click(ELEMENT_SORTBY_ICON);
		for (String sort : sorts) {
			waitForAndGetElement(ELEMENT_SORTBY_ITEM.replace("$item",sort));
		}
	}
	/**
	 * Check first access of group by, sort by
	 * @param isPresent
	 */
	public void checkFirstAccessGroupSort(boolean isPresent){
		if(isPresent){
			waitForAndGetElement(ELEMENT_GROUPBY_ICON);
			waitForAndGetElement(ELEMENT_SORTBY_ICON);
		}else{
			waitForElementNotPresent(ELEMENT_GROUPBY_ICON);
			waitForElementNotPresent(ELEMENT_SORTBY_ICON);
		}
	}
	/**
	 * Check no Board view in Projects
	 * @param project
	 */
	public void checkNoBoardInProjects(String project){
		info("check no Board view");
		openProject(project);
		waitForElementNotPresent(ELEMENT_BOARD_VIEW);
	}
	/**
	 * Check Projects by default
	 */
	public void checkProjectsByDefault(){
		goToProjects();
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
	public void checkDefaultGroupSort(String project,String group,String sort){
		openProject(project);
		waitForAndGetElement(ELEMENT_SORTBY_ITEM.replace("$item",sort));
		waitForAndGetElement(ELEMENT_GROUPBY_ITEM.replace("$item", group));
	}
	/**
	 * Check default shared project
	 * @param project
	 */
	public void checkDefaultSharedProject(String project){
		openProject(project);
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
		if(participants.length>0 && !participants[0].isEmpty()){
		for (String participant : participants) {
			waitForAndGetElement(ELEMENT_SHARE_PROJECT_DISPLAY_PARTICIPANT.replace("$user",participant));
			}
		}
		if(managers.length>0 && managers[0].isEmpty()){
		for (String manager : managers) {
			waitForAndGetElement(ELEMENT_SHARE_PROJECT_DISPLAY_MANAGER.replace("$user",manager));
			}
		}
		click(ELEMENT_CLOSE_BTN);
	}
	/**
	 * Check share project popup
	 */
	public void checkShareProjectPopup(){
		click(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_ICON);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_INPUT);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SAVE_BTN);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SELECT_GROUP_ICON);
		waitForAndGetElement(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SELECT_USER_ICON);
		click(ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_ICON);
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
		waitForAndGetElement(ELEMENT_LEFT_PANE_COLOR_TABLE_ITEM.replace("$project", project).replace("$color", "noColor"));
		waitForAndGetElement(ELEMENT_LEFT_PANE_COLOR_TABLE_ITEM.replace("$project", project).replace("$color", "red"));
		goToContMenuGivenProject(project);
	}
	/**
	 * Decorate description
	 * @param project
	 * @param des
	 * 			 description of project
	 */
	public void decorateDescription(String project, String des){
		openProject(project);
		Utils.pause(1000);
		click(ELEMENT_RIGHT_PANE_EDIT_PROJECT_DES_INPUT);
		if(des.isEmpty()){
			info("left description blank");
			inputFrame(ELEMENT_CKEDITOR_IFRAME, "");
			mouseOverAndClick(ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
			waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_EMPTY);
		}else{
			info("decorate description");
			inputFrame(ELEMENT_CKEDITOR_IFRAME, des);
			cke_NumberList();
			cke_Bold();
			mouseOverAndClick(ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
			waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_CKEDITOR.replace("$des", des));
		}
	}
	/**
	 * Search project in Project Path
	 * @param project: search key
	 */
	public void searchProjectPath(String project){
		info("search project");
		click(ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
		type(ELEMENT_EDIT_PROJECT_PATH_INPUT,project,true);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_PARENT_PATH_MATCH_VALUE.replace("$text",project));
	}
	/**
	 * Left project title blank
	 * @param project
	 */
	public void leftProjectTitleBlank(String project){
		info("left title blank");
		click(ELEMENT_ADD_PROJECT_TITLE_TEXT.replace("$title",project));
		waitForAndGetElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).clear();
        Utils.pause(500);
        driver.findElement(ELEMENT_EDIT_PROJECT_TITLE_INPUT).sendKeys(Keys.ENTER);
        waitForAndGetElement(ELEMENT_ADD_PROJECT_UNTITLED);
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
		click(ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
		waitForAndGetElement(ELEMENT_RIGHT_PANE_PARENT_PATH_FULL.replace("$parent", prj).replace("$child",sub));
	}
	/**
	 * Check display of List, Board
	 * @param isPresent
	 */
	public void checkDisplayOfListBoard(boolean isPresent){
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
	 * check top of list view
	 * @param project
	 * @param newTask
	 * 				true if it'll displayed
	 * 				false if it won't displayed
	 */
	public void checkTopOfListView(String project,boolean newTask,boolean isProjects){
		
		if(newTask){
			openProject(project);
			waitForAndGetElement(ELEMENT_ADD_TASK_BTN);
			waitForAndGetElement(ELEMENT_ADD_TASK_TITLE);
		}else{
			if (isProjects){
				goToProjects();
				waitForElementNotPresent(ELEMENT_ADD_TASK_BTN);
				waitForElementNotPresent(ELEMENT_ADD_TASK_TITLE);
			}else{
				goToLabels();
				waitForElementNotPresent(ELEMENT_ADD_TASK_BTN);
				waitForElementNotPresent(ELEMENT_ADD_TASK_TITLE);
			}
		}
		
	}
}
