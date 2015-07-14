package org.exoplatform.selenium.platform.task;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;

/**
 * This class will be define locators for Task Management feature
 * The feature has following parts:
 * 1. Left PANE
 * 2. Central PANE
 * 3. Right PANE
 *
 */

public class TaskManagementLocatorObject extends PlatformBase {
	
	/***********************************************************LEFT PANE******************************************************/
	//General
	
	
	//Task area
	public final By ELEMENT_TASKS_LINK_PLF=By.xpath("//*[@data-original-title='Tasks']");
	public By ELEMENT_TASKS_PORTLET = By.id("taskManagement");
	
	//Project area-->General
	public final String ELEMENT_LEFT_PANE_PROJECT_NAME=".//*[contains(@class,'project-name')][contains(.,'$project')]";
	public final String ELEMENT_LEFT_PANE_SUBPROJECT_L1="//*[contains(@class,'dropdown project-item')]/*[contains(@class,'list-projects')]//*[contains(@class,'project-name')][contains(.,'$sub')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_ID=".//*[contains(@data-id,'$id')][contains(.,'$project')]";
	public final By ELEMENT_LEFT_PANE_PROJECTS_PLUS_MENU=By.xpath("//*[@class='uiIconLightGray uiIconSimplePlusMini addProject']");
	public final By ELEMENT_LEFT_PANE_PROJECTS_ADD=By.xpath("//*[@class='uiDropdownMenu']//*[@class='uiIconAddProject uiIconLightGray']");
	
	
	//Project area-->Context Menu
	public final String ELEMENT_LEFT_PANE_PROJECT_MENU="//*[@class='project-name' and contains(.,'$project')]/../*[@class='dropdown-toggle pull-right']/*[@class='uiIconRightMenu uiIconLightGray']";
	public final String ELEMENT_LEFT_PANE_PROJECT_MENU_ID="//*[contains(@data-id,'$id')][contains(.,'$project')]/../*[@class='dropdown-toggle pull-right']/*[@class='uiIconRightMenu uiIconLightGray']";
	public final String ELEMENT_LEFT_PANE_PROJECT_HIDE="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='actionHideProject']/*[contains(.,'Hide')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_SHOW="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='actionHideProject']/*[contains(.,'Show')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_ADD="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='new-project']/*[@class='uiIconAddProject uiIconLightGray']";
	public final String ELEMENT_LEFT_PANE_PROJECT_SHARE="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='share-project']";
	public final String ELEMENT_LEFT_PANE_PROJECT_CLONE="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='clone-project']";
	public final String ELEMENT_LEFT_PANE_PROJECT_DELETE="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='delete-project']/*[@class='uiIconTrash uiIconLightGray']";


	
	
	//Labels area
	
	
	
	
	
	/**********************************************************CENTRAL PANE******************************************************/
	
	//*************************************Task Management*****************************************//
	
	//Task area-->General
	public final String ELEMENT_TASK_TITLE= "//*[@class='taskList']//*[contains(@class,'column-item column-title')][contains(.,'$task')]";
	public final By ELEMENT_GROUPBY_ICON = By.xpath("//*[@class='actionIcon dropdown-toggle']/*[@class='uiIconViewList uiIconLightGray']");
	public final By ELEMENT_SORTBY_ICON = By.xpath("//*[@class='actionIcon dropdown-toggle']/*[@class='uiIconSortDown uiIconLightGray']");
	public final String ELEMENT_SORTBY_ITEM ="//*[@data-original-title='Due date']//../*[contains(.,'$item')]";
	public final String ELEMENT_GROUPBY_ITEM ="//*[@data-original-title='group By']//../*[contains(.,'$item')]";
	
	//Add Task form
	public final By ELEMENT_ADD_TASK_BTN = By.xpath("//*[@class='btn btn-primary btn-add-task']/*[@class='uiIconSimplePlusMini']");
	public final By ELEMENT_ADD_TASK_TITLE = By.name("taskTitle");
	
	
	//Task area-->Incoming
	
	
	//Task area-->All tasks
	
	
	//Task area--->Overdue
	
	
	//Task area--->Today
	
	
	//Task area-->Tomorrow
	
	
	//Task area-->Upcoming
	
	
	
	
	//*************************************Project Management*****************************************//

	//Project-->General
	public final By ELEMENT_PROJECT_WELCOME_IMG=By.xpath("//*[@class='empty-content']/img");
	public final String ELEMENT_PROJECT_WELCOME_TEXT="//*[@class='empty-content']/*[contains(.,'$message')]";
	
	
	
	//Share project form
	
	
	
	//Color
	public final String ELEMENT_COLOR_TABLE_ITEM=".//*[contains(@class,'project-name')][contains(.,'$project')]/..//*[contains(@class,'$color')]";
	
	
	//Clone project
	
	
	
	//Show and Hide project
	
	
	//Delete project
	public final By ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX=By.xpath(".//*[contains(@class,'checkbox deleteChild')]");
	public final By ELEMENT_DELETE_PROJECT_CONFIRM_DELETE_BTN=By.xpath("//*[@class='btn btn-primary confirmDelete']");
	
	//*************************************Labels Management*****************************************//
	
	//Labels-->General
	
	
	//Add labels form
	
	
	//Edit labels form
	
	
	//Delete label
	
	
	//Show and Hide labels
	
	
	//Change color
	
	
	
	//*************************************List View*****************************************//
	
	//List View-->General
	public final String ELEMENT_PROJECT_DEFAULT_SORTBY = "//*[@class='uiIconSortDown uiIconLightGray']/../*[contains(.,'$sort')]";
	public final String ELEMENT_PROJECT_DEFAULT_GROUPBY = "//*[@class='uiIconViewList uiIconLightGray']/../*[contains(.,'$group')]";
	//List View-->Group by Status
	
	
	//List View-->Group by Project
	
	
	//List View-->Group by Assignee
	
	
	//List View --> Group by Label
	
	
	//List View - Group by Due Date
	
	
	//List View - Sort By Rank
	
	
	//List View - Sort by Priority
	
	
	//List View - Sort by Created Date
	
	
	
	
	/***********************************************************RIGHT PANE******************************************************/
	// Project Overview
	public final By ELEMENT_RIGHT_PANE_ARROW_MENU= By.xpath("//*[contains(@class,'uiIconArrowDown uiIconLightGray')]");
	public final By ELEMENT_RIGHT_PANE_ARROW_MENU_CLONE= By.xpath("//*[contains(@class,'action-clone-project')]/*[@class='uiIconCloneNode uiIconLightGray']");
	public final By ELEMENT_RIGHT_PANE_ARROW_MENU_DELETE= By.xpath("//*[contains(@class,'action-delete-project')]/*[@class='uiIconDelete uiIconLightGray']");
	public final By ELEMENT_RIGHT_PANE_PARENT_PATH_LINK=By.xpath("//*[@data-name='parent']");
	public final String ELEMENT_RIGHT_PANE_PARENT_PATH_TEXT="//*[@data-name='parent']/*[@class='active'][contains(.,'$project')]";
	public final String ELEMENT_RIGHT_PANE_MANAGER_NAME ="//*[@class='row-fluid prjAuthor']/*[contains(.,'$user')]";

	//Add project form
	public final String ELEMENT_ADD_PROJECT_HEADER = "//*[@class='addProject uiBox']/*[@class='title']/*[contains(.,'$header')]";
	public final By ELEMENT_ADD_PROJECT_PARENT_PROJECT=By.xpath("(.//*[contains(@class,'addProject')]//*[contains(@class,'hoverStatusBlock')])[1]");
	public final By ELEMENT_ADD_PROJECT_TITLE_INPUT=By.xpath(".//*[contains(@class,'addProject')]//*[contains(@name,'name')][@placeholder='Untitled Project']");
	public final By ELEMENT_EDIT_PROJECT_TITLE_INPUT=By.xpath("//*[@class='editable-input']/*[@type='text']");
	public final By ELEMENT_EDIT_PROJECT_PATH_INPUT=By.xpath("//*[@class='editable-input']//*[@name='search']");
	public final String ELEMENT_ADD_PROJECT_TITLE_TEXT= ".//*[contains(@class,'addProject')]//*[contains(@data-name,'name')][contains(.,'$title')]";
    public final By ELEMENT_ADD_PROJECT_DES_INPUT=By.xpath(".//*[contains(@class,'addProject')]//*[contains(@name,'description')]");
    public final String ELEMENT_ADD_PROJECT_DES_TEXT=".//*[contains(@class,'addProject')]//*[contains(@data-name,'description')][contains(.,'$des')]";
    public final By ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX=By.xpath(".//*[contains(@class,'addProject')]//*[contains(@class,'checkbox')]");
    public final By ELEMENT_ADD_PROJECT_ENABLE_CALENDAR=By.xpath(".//*[@class='checkbox']/../*[contains(.,'Enable Calendar Integration')]");
 
	//Edit project form
    public final String ELEMENT_RIGHT_PANE_PARENT_PATH_MATCH_VALUE="//*[contains(@class,'uiDropdownMenu dropdown-menu')]//strong[contains(.,'$text')]";
    public final String ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU="//*[contains(@class,'uiDropdownMenu dropdown-menu')]//a[contains(.,'$project')]";
    public final String ELEMENT_RIGHT_PANE_PARENT_PATH_FULL="//*[contains(@class,'uiDropdownMenu dropdown-menu')]//*[contains(.,'Projects')]/*[contains(.,'$parent')]/li[contains(.,'$child')]";
	
    //Task Detail
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU=By.xpath("//*[contains(@class,'dropdown-toggle actionIconSmall')]/*[@class='uiIconArrowDown']");
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_CLONE= By.xpath("//*[contains(@class,'action-clone-task')]/*[@class='uiIconCloneNode']");
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_DELETE= By.xpath("//*[contains(@class,'action-delete-task')]/*[@class='uiIconDelete']");
}
