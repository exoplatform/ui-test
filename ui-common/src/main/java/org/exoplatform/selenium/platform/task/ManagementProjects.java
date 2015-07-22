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
	 * Open Context Menu of Projects by clicking on "+" icon
	 */
	public void goToContMenuProject(){
		info("Click on + icon to open Context Menu of Projects");
		click(ELEMENT_LEFT_PANE_PROJECTS_PLUS_MENU,0,true);
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
	    	click(ELEMENT_LEFT_PANE_PROJECT_SHARE.replace("$project", project));
	    	break;
	    case Clone:
	    	info("Select Clone option");
	    	click(ELEMENT_LEFT_PANE_PROJECT_CLONE.replace("$project", project));
	    	break;
	    case Hide:
	    	info("Select Hide option");
	    	click(ELEMENT_LEFT_PANE_PROJECT_HIDE.replace("$project", project));
	    	break;
	    case Show:
	    	info("Select Show option");
	    	click(ELEMENT_LEFT_PANE_PROJECT_SHOW.replace("$project", project));
	    	break;
	    case Delete:
	    	info("Select Delete option");
	    	click(ELEMENT_LEFT_PANE_PROJECT_DELETE.replace("$project", project),0,true);
	    	break;
	    case Add_Project:
	    	info("Select Add Project option");
	    	click(ELEMENT_LEFT_PANE_PROJECT_ADD.replace("$project", project),0,true);
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
	public void selectColor(String project,String color){
		goToContMenuGivenProject(project);
		info("Select "+color+" in color list");
		click(ELEMENT_LEFT_PANE_COLOR_TABLE_ITEM.replace("$project",project).replace("$color",color));
		Utils.pause(2000);
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
		info("Create a new project");
		if(des!=null || des!=""){
			info("Input description");
			type(ELEMENT_ADD_PROJECT_DES_INPUT,des,true);
		}
		if(title!=null || title!=""){
			info("Input title");
			//type(ELEMENT_ADD_PROJECT_TITLE,title,true);
			waitForAndGetElement(ELEMENT_ADD_PROJECT_TITLE_INPUT).sendKeys(title);
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
		waitForAndGetElement(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", title));
		waitForAndGetElement(ELEMENT_ADD_PROJECT_DES_TEXT.replace("$des", des));
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
			click(ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project",project));
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
	 * @user
	 * 		can be a user/users/group user
	 * @manager
	 * 		true if user is setted as manager
	 * 		false if user is setted as paticipant
	 */
	public void shareProject(String[] users, boolean manager) {
		if (manager){
			for (String user : users) {
				click(ELEMENT_SHARE_PROJECT_EDIT_MANAGER_ICON);
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
			for (String user : users) {
				click(ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_ICON);
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
		click(ELEMENT_CLOSE_BTN);
	}
	
	/**
	 * Delete project
	 * @param project
	 * @param subPrj = true if delete also all sub-projects
	 * 				 = false if not delete
	 */
	public void deleteProject(String project,boolean subPrj){
		if(subPrj){
			info("also delete all sub-projects");
			check(ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX,2);
		}else{
			info("no delete all sub-projects");
			uncheck(ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX,2);
		}
		click(ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN);
		
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
	public void cloneProject(String project, boolean uncompleteTask) {
	if(uncompleteTask){
		info("also clone uncompleted task");
		check(ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX,2);
	}else{
		info("no delete all sub-projects");
		uncheck(ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX,2);
	}
	click(ELEMENT_CLONE_PROJECT_CLONE_BUTTON);
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
}
