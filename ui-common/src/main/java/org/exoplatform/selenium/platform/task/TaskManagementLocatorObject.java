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
	public final By ELEMENT_LEFT_PANE_NO_PROJECT = By.xpath("//*[contains(@class,'list-projects')]/*[contains(.,'No project')]");
	public final By ELEMENT_LEFT_PANE_TOOLTIP_TASK = By.xpath("//*[@class='popover-content'][contains(.,\"Let's create your first task.\")]");
	public final By ELEMENT_LEFT_PANE_TOOLTIP_PROJECT = By.xpath("//*[@class='popover-content'][contains(.,'No Project. Click here to create your first project.')]");
	public final By ELEMENT_TASK_APP_ICON = By.xpath("//*[contains(@class,'TaskManagementApplication')]");
	public final String ELEMENT_PROFILE_PORTLET = ".//*[@id='UIStatusProfilePortlet']/*[contains(.,'$user')]";
	public final By ELEMENT_LEFT_PANE_SHOWHIDE_LEFT = By.xpath(".//*[@id='ShowHideLeft']/*[contains(@class,'ArrowLeft')]");
	public final By ELEMENT_LEFT_PANE_SHOWHIDE_RIGHT = By.xpath(".//*[@id='ShowHideLeft']/*[contains(@class,'ArrowRight')]");
	public final By ELEMENT_SHOW_LEFT = By.xpath("//*[@id='ShowHideLeft']");
	public final By ELEMENT_HIDE_LEFT = By.xpath("//*[@class='uiIconMiniArrowLeft']");
	public final String ELEMENT_LEFT_PANE_INCOMING_BADGE ="//*[contains(@class,'project-name')][contains(.,'Incoming')]/*[contains(.,'$num')]";
	
	//Project area-->General
	public final String ELEMENT_LEFT_PANE_PROJECT_NAME="//*[contains(@class,'project-name')][contains(.,'$project')]";
	public final By ELEMENT_LEFT_PANE_PROJECTS=By.xpath("//*[contains(@class,'project-name')][contains(.,'Projects')]");
	public final By ELEMENT_LEFT_PANE_TASKS=By.xpath("//*[contains(@class,'project-name')][contains(.,'Tasks')]");
	public final By ELEMENT_LEFT_PANE_INCOMING=By.xpath("//*[contains(@class,'project-name')][contains(.,'Incoming')]");
	public final By ELEMENT_LEFT_PANE_ALLTASKS=By.xpath("//*[contains(@class,'project-name')][contains(.,'All Tasks')]");
	public final By ELEMENT_LEFT_PANE_TODAY=By.xpath("//*[contains(@class,'project-name')][contains(.,'Today')]");
	public final By ELEMENT_LEFT_PANE_TOMORROW=By.xpath("//*[contains(@class,'project-name')][contains(.,'Tomorrow')]");
	public final By ELEMENT_LEFT_PANE_UPCOMING=By.xpath("//*[contains(@class,'project-name')][contains(.,'Upcoming')]");
	public final By ELEMENT_LEFT_PANE_OVERDUE=By.xpath("//*[contains(@class,'project-name')][contains(.,'Overdue')]");
	
	public final String ELEMENT_LEFT_PANE_SUBPROJECT_L1="//*[contains(@class,'list-projects')]//*[contains(@class,'project-name')][contains(.,'$sub')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_ID=".//*[contains(@data-id,'$id')][contains(.,'$project')]";
	public final By ELEMENT_LEFT_PANE_PROJECTS_PLUS_MENU=By.xpath("//*[contains(@class,'project-name')][contains(.,'Projects')]/../*[contains(@class,'add-new-project')]/*[contains(@class,'PlusMini')]");
	public final By ELEMENT_LEFT_PANE_PROJECTS_ARROW_MENU_DOWN=By.xpath("//*[contains(@class,'project-name')][contains(.,'Projects')]/../*[contains(@class,'collapseTree')]/*[contains(@class,'ArrowDownMini')]");
	public final By ELEMENT_LEFT_PANE_PROJECTS_ARROW_MENU_RIGHT=By.xpath("//*[contains(@class,'project-name')][contains(.,'Projects')]/../*[contains(@class,'collapseTree')]/*[contains(@class,'ArrowRightMini')]");
	public final String ELEMENT_LEFT_PANE_PROJECT_ARROW_MENU_DOWN="//*[contains(@class,'project-name')][contains(.,'$project')]/../*[@class='collapseSubProject']//*[contains(@class,'ArrowDown')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_ARROW_MENU_RIGHT="//*[contains(@class,'project-name')][contains(.,'$project')]/../*[@class='collapseSubProject']//*[contains(@class,'ArrowRight')]";
	public final By ELEMENT_LEFT_PANE_PROJECTS_ADD=By.xpath("//*[contains(@class,'project-name')][contains(.,'Projects')]/../*[contains(@class,'uiDropdownWithIcon')]//*[contains(@class,'AddProject')]");
	public final By ELEMENT_LEFT_PANE_PROJECTS_SHOWHIDDEN=By.xpath("//*[contains(@class,'ShowProject')]/../*[contains(.,'Show Hidden Projects')]");
	public final By ELEMENT_LEFT_PANE_PROJECTS_HIDEHIDDEN=By.xpath("//*[contains(@class,'ShowProject')]/../*[contains(.,'Hide Hidden Projects')]");
	public final String ELEMENT_LEFT_PANE_PROJECT_COLOR = "//*[contains(@class,'project-name')][contains(.,'$project')]/../../*[contains(@class,'$color')][contains(@class,'colorPie')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_NO_COLOR = "//*[contains(@class,'project-name')][contains(.,'$project')]/../../*[@class=' colorPie']";
	
	//Project area-->Context Menu
	public final String ELEMENT_LEFT_PANE_PROJECT_MENU="//*[contains(@class,'project-name')][contains(.,'$project')]/../..//*[contains(@class,'RightMenu')]";
	public final String ELEMENT_LEFT_PANE_PROJECTID_MENU="//*[contains(@data-id,'$id')][contains(.,'$project')]/../..//*[contains(@class,'RightMenu')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_HIDE="//*[contains(@class,'project-name')][contains(.,'$project')]/../..//*[contains(@class,'uiIconHide')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_SHOW="//*[contains(@class,'project-name')][contains(.,'$project')]/../..//*[contains(@class,'uiIconShow')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_ADD="//*[contains(@class,'project-name')][contains(.,'$project')]/../..//*[contains(@class,'AddProject')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_SHARE="//*[contains(@class,'project-name')][contains(.,'$project')]/../..//*[contains(@class,'Share')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_EDIT="//*[contains(@class,'project-name')][contains(.,'$project')]/../..//*[contains(@class,'Edit')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_CLONE="//*[contains(@class,'project-name')][contains(.,'$project')]/../..//*[contains(@class,'CloneNode')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_DELETE="//*[contains(@class,'project-name')][contains(.,'$project')]/../..//*[contains(@class,'Trash')]";
	public final String ELEMENT_LEFT_PANE_PROJECTID_DELETE="//*[contains(@data-id,'$id')][contains(.,'$project')]/../..//*[contains(@class,'Trash')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_ISHIDDEN="//*[contains(@hiddenproject,'$bool')]//*[contains(@class,'project-name')][contains(.,'$project')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_ISSHOWHIDDEN="//*[contains(@showhiddenproject,'$bool')]//*[contains(@class,'project-name')][contains(.,'$project')]";
	
	//Color
	public final String ELEMENT_LEFT_PANE_COLOR_TABLE_ITEM="//*[contains(@class,'project-name')][contains(.,'$project')]/../..//*[contains(@class,'$color')]";
	
	
	/**********************************************************CENTRAL PANE******************************************************/
	
	//*************************************Task Management*****************************************//
	
	//Task area-->General
	public final String ELEMENT_TASK_TITLE= "//*[contains(@class,'row-item')]/*[contains(@class,'taskName')][.='$task']";
	public final String ELEMENT_TASK_ID = "//*[contains(@data-taskid,'$id')]";
	public final String ELEMENT_TASK_TITLE_ID="//*[contains(@class,'taskName')][.='$task']/..";
	public final By ELEMENT_GROUPBY_ICON = By.xpath("//*[contains(@class,'ViewList')]");
	public final By ELEMENT_SORTBY_ICON = By.xpath("//*[contains(@class,'SortDown')]");
	public final String ELEMENT_TASK_COLOR="//*[contains(@class,'title')][contains(.,'$task')]/..//*[contains(@class,'project-color $color')]";
	public final String ELEMENT_TASK_NO_COLOR=".//*[contains(@class,'title')][contains(.,'$task')]/..//*[@class='project-color ']";
	public final String ELEMENT_TASK_SYMBOL_BLUE = "//*[contains(.,'$task')]/..//*[contains(@class,'uiIconColorWarningInBlue')]";
	public final String ELEMENT_TASK_SYMBOL_RED = "//*[contains(.,'$task')]/..//*[contains(@class,'uiIconColorWarningInRed')]";
	
	public final By ELEMENT_WELCOME_TEXT_TASK_DEFAULT= By.xpath("//*[@class='empty-content']/*[contains(.,'Welcome to eXo Tasks')]");
	public final By ELEMENT_TEXT_NOTASK_DEFAULT= By.xpath("//*[@class='empty-content']/*[contains(.,'No Task')]");
	public final By ELEMENT_LEFT_PANE_TASKS_ARROW_MENU_DOWN=By.xpath("//*[contains(@class,'project-name')][contains(.,'Tasks')]/../*[contains(@class,'collapseTree')]/*[contains(@class,'ArrowDownMini')]");
	public final By ELEMENT_LEFT_PANE_TASKS_ARROW_MENU_RIGHT=By.xpath("//*[contains(@class,'project-name')][contains(.,'Tasks')]/../*[contains(@class,'collapseTree')]/*[contains(@class,'ArrowRightMini')]");
	
	//Add Task form
	public final By ELEMENT_ADD_TASK_BTN = By.xpath("//*[contains(@class,'add-task')]/*[contains(@class,'PlusMini')]");
	public final By ELEMENT_ADD_TASK_TITLE = By.name("taskTitle");
	public final String ELEMENT_UNTITLEDTASK_AND_TASK_INPUT = "//*[contains(@class,'taskName')][.='$task']/../../../../../..//*[contains(@class,'create-task')]/input";
	
	//Task area-->Incoming
	public final By ELEMENT_LEFT_PANE_INCOMING_ACTIVE=By.xpath("//*[contains(@class,'active')]//*[contains(@class,'project-name')][contains(.,'Incoming')]");
	public final String ELEMENT_LEFT_PANE_PROJECT_ACTIVE="//*[contains(@class,'active')]//*[contains(@class,'project-name')][contains(.,'$project')]";
	
	//Task area-->All tasks
	
	
	//Task area--->Overdue
	
	
	//Task area--->Today
	
	
	//Task area-->Tomorrow
	
	
	//Task area-->Upcoming
	
	
	
	
	//*************************************Project Management*****************************************//
   
	//Project-->General
	public final By ELEMENT_WELCOME_IMG=By.xpath("//*[@class='empty-content']/img");
	public final By ELEMENT_WELCOME_TEXT_PROJECT1= By.xpath("//*[@class='empty-content']/*[contains(.,'This is a personal project.')]");
	public final By ELEMENT_WELCOME_TEXT_PROJECT2= By.xpath("//*[@class='empty-content']/*[contains(.,'You can share it for work collaboration.')]");
	public final By ELEMENT_WELCOME_TEXT_PROJECT_DEFAULT= By.xpath("//*[@class='empty-content']/*[contains(.,'No Project')]");
	public final By ELEMENT_WELCOME_TEXT_PROJECT_SHARE1= By.xpath("//*[@class='empty-content']/*[contains(.,'This is John Smith's project.')]");
	public final By ELEMENT_WELCOME_TEXT_PROJECT_SHARE2= By.xpath("//*[@class='empty-content']/*[contains(.,'There is no task to do.')]");
	public final String ELEMENT_PROJECT_TITLE = "//*[contains(@class,'centerPanel')]//*[contains(@class,'projectName')][contains(.,'$project')]";
	public final String ELEMENT_PROJECT_PERMALINK = "//*[contains(@class,'centerPanel')]//*[contains(@class,'projectName')][contains(.,'$project')]/..//*[contains(@class,'uiIconLink')]";
	// Project Overview
	public final By ELEMENT_PROJECT_ARROW_MENU= By.xpath("//*[contains(@class,'addProject')]//*[contains(@class,'ArrowDown')]");
	public final By ELEMENT_PROJECT_ARROW_MENU_CLONE= By.xpath("//*[contains(@class,'action-clone-project')]/*[contains(@class,'uiIconLightGray')]");
	public final By ELEMENT_PROJECT_ARROW_MENU_DELETE= By.xpath("//*[contains(@class,'action-delete-project')]/*[contains(@class,'uiIconLightGray')]");
	public final By ELEMENT_PROJECT_PARENT_PATH_LINK=By.xpath("//*[@data-name='parent']");
	public final String ELEMENT_PROJECT_PARENT_PATH_TEXT="//*[@data-name='parent']/*[@class='active'][contains(.,'$project')]";
	public final String ELEMENT_PROJECT_MANAGER_NAME ="//*[contains(@class,'prjAuthor')]//*[contains(@class,'avatar')][contains(.,'$user')]";
	public final By ELEMENT_PROJECT_CLOSE_ICON = By.xpath("//*[@class='title']/*[contains(.,'Project overview')]/..//*[contains(@class,'Close')]");
	public final By ELEMENT_PROJECT_HIDE_ICON = By.xpath(".//*[@id='ShowHideRight']/i");
	
	//Add project form
	public final By ELEMENT_ADD_PROJECT_HEADER = By.xpath(".//*[contains(@class,'PopupTitle')][contains(.,'Project overview')]");
	public final By ELEMENT_ADD_PROJECT_PARENT_PROJECT=By.xpath("(.//*[contains(@class,'addProject')]//*[contains(@data-name,'parent')]");
	public final By ELEMENT_ADD_PROJECT_TITLE_INPUT=By.xpath(".//*[contains(@class,'addProject')]//*[contains(@name,'name')][@placeholder='Untitled Project']");
	public final By ELEMENT_EDIT_PROJECT_PATH_INPUT=By.xpath("//*[@class='editable-input']//*[@name='search']");
	public final String ELEMENT_ADD_PROJECT_TITLE_TEXT= ".//*[contains(@class,'addProject')]//*[contains(@data-name,'name')][contains(.,'$title')]";
	public final String ELEMENT_ADD_PROJECT_PARENT_PROJECT_TEXT="//*[contains(@class,'addProject')]//*[contains(@data-name,'parent')]/*[contains(.,'$path')]";
    public final String ELEMENT_ADD_PROJECT_DES_TEXT=".//*[contains(@class,'addProject')]//*[contains(@data-name,'description')][contains(.,'$des')]";
    public final By ELEMENT_ADD_PROJECT_DES_EMPTY=By.xpath(".//*[contains(@class,'addProject')]//*[contains(@data-name,'description')][contains(.,'Empty')]");
    public final String ELEMENT_ADD_PROJECT_DES_CKEDITOR="//*[contains(@class,'addProject')]//*[contains(@data-name,'description')]//strong[contains(.,'$des')]";
    public final By ELEMENT_ADD_PROJECT_DES_CKEDITOR_INPUT = By.xpath(".//*[@id='cke_description']");
    public final By ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX=By.xpath(".//*[contains(@class,'addProject')]//*[contains(@class,'checkbox')]");
    public final By ELEMENT_ADD_PROJECT_ENABLE_CALENDAR=By.xpath(".//*[contains(@class,'checkbox')]/../*[contains(.,'Enable Calendar integration')]");
 
	//Edit project form
    public final By ELEMENT_EDIT_PROJECT_HEADER = By.xpath(".//*[contains(@class,'PopupTitle')][contains(.,'Project overview')]");
    public final By ELEMENT_EDIT_PROJECT_TITLE_INPUT=By.xpath("//*[contains(@data-name,'name')]/..//input[@class='blackLarge']");
    public final String ELEMENT_PARENT_PATH_MATCH_VALUE="//*[contains(@class,'dropdown-menu')]//strong[contains(.,'$text')]";
    public final String ELEMENT_PARENT_PATH_DROPDOWN_MENU="//*[contains(@class,'dropdown-menu')]//a[contains(.,'$project')][@data-matched='true']";
    public final String ELEMENT_PARENT_PATH_FULL="//*[contains(@class,'dropdown-menu')]//*[contains(.,'Projects')]/*[contains(.,'$parent')]/li[contains(.,'$child')]";
	public final By ELEMENT_EDIT_PROJECT_DES_INPUT_LINK = By.xpath("//*[contains(@class,'addProject')]//*[contains(@data-name,'description')]");
	public final By ELEMENT_EDIT_PROJECT_TITLE_INPUT_LINK = By.xpath("//*[contains(@class,'addProject')]//*[contains(@data-name,'name')]");
	public final By ELEMENT_EDIT_PROJECT_SAVE_BTN = By.xpath(".//*[contains(@class,'saveLabel')]");
	public final By ELEMENT_EDIT_PROJECT_SAVE_BTN_DISABLED = By.xpath("//*[contains(@class,'saveLabel')][@disabled='']");
	public final By ELEMENT_EDIT_PROJECT_CANCEL_BTN = By.xpath(".//*[@class='btn'][contains(.,'Close')]");
	public final By ELEMENT_EDIT_PROJECT_UNTITLED= By.xpath(".//*[contains(@class,'addProject')]//*[contains(@data-name,'name')][contains(.,'Untitled Project')]");
	
	//Share project form
	public final By ELEMENT_SHARE_PROJECT_EDIT_MANAGER_ICON = By.xpath("//*[@data-type='manager']//*[contains(@class,'EditPermission')]/*[contains(@class,'Edit')]");
	public final By ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_ICON =  By.xpath("//*[@data-type='participant']//*[contains(@class,'EditPermission')]/*[contains(@class,'Edit')]");
	public final By ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SAVE_BTN = By.xpath("//*[@data-type='manager'][@class='manager']//*[@class='btn savePermission']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SAVE_BTN = By.xpath("//*[@data-type='participant'][@class='manager']//*[@class='btn savePermission']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_MANAGER_INPUT = By.xpath("//*[@data-type='manager'][@class='manager']//*[@type='text']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_INPUT = By.xpath("//*[@data-type='participant'][@class='manager']//*[@type='text']");
	public final By ELEMENT_CLOSE_BTN = By.xpath("//*[@class='btn'][contains(.,'Close')]");
	public final String ELEMENT_SHARE_PROJECT_EDIT_MANAGER_TEXT = "//*[@data-type='manager']//*[contains(@style,'overflow')]/*[contains(.,'$user')]";
	public final String ELEMENT_SHARE_PROJECT_EDIT_MANAGER_REMOVE_ICON = "//*[@data-type='manager']//*[contains(@style,'overflow')]/*[contains(.,'$user')]//*[contains(@class,'Close')]";
	public final String ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_TEXT = "//*[@data-type='participant']//*[contains(@style,'overflow')]/*[contains(.,'$user')]";
	public final String ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_REMOVE_ICON = "//*[@data-type='participant']//*[contains(@style,'overflow')]/*[contains(.,'$user')]//*[contains(@class,'Close')]";
	public final String ELEMENT_SHARE_PROJECT_DISPLAY_MANAGER = "//*[@data-type='manager'][@class='manager']//*[@class='list-users'][contains(.,'$user')]";
	public final String ELEMENT_SHARE_PROJECT_DISPLAY_PARTICIPANT = "//*[@data-type='participant'][@class='manager']//*[@class='list-users'][contains(.,'$user')]";
	public final By ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SELECT_USER_ICON = By.xpath("//*[contains(@data-type,'manager')]//*[contains(@class,'uiIconUser')]");
	public final By ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SELECT_GROUP_ICON = By.xpath("//*[contains(@data-type,'manager')]//*[contains(@class,'uiIconGroup')]");
	public final By ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SELECT_USER_ICON = By.xpath("//*[contains(@data-type,'participant')]//*[contains(@style,'overflow')]/..//*[contains(@class,'uiIconUser')]");
	public final By ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SELECT_GROUP_ICON = By.xpath("//*[contains(@data-type,'participant')]//*[contains(@style,'overflow')]/..//*[contains(@class,'uiIconGroup')]");
	
	//Clone project
	public final By ELEMENT_CLONE_PROJECT_CLONE_BUTTON = By.xpath("//*[contains(@class,'CloneProject')]//*[contains(@class,'btn-primary')]");
	public final By ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX = By.xpath("//*[contains(@class,'CloneProject')]//*[@class='checkbox cloneTask']");
	public final By ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX_TEXT = By.xpath("//*[contains(@class,'CloneProject')]//*[@class='checkbox cloneTask']/../*[contains(.,'also clone uncompleted task.')]");
	public final By ELEMENT_CLONE_PROJECT_CANCEL_BUTTON = By.xpath("//*[contains(@class,'CloneProject')]//*[@class='btn'][contains(.,'Cancel')]");
	public final String ELEMENT_CLONE_PROJECT_POPUP_MESSAGE="//*[@data-msg='You are about to clone <strong>{}</strong> project. Please confirm']/*[contains(.,'$project')]";
	
	
	//Show and Hide project
	
	
	//Delete project
	public final By ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX=By.xpath(".//*[contains(@class,'deleteChild')]");
	public final By ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX_TEXT=By.xpath(".//*[contains(@class,'deleteChild')]/../*[contains(.,'also delete all sub-projects')]");
	public final By ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN=By.xpath("//*[contains(@class,'btn-primary')][contains(.,'Delete')]");
	public final By ELEMENT_CONFIRMATION_POPUP_TITLE=By.xpath("//*[contains(@class,'Title')][contains(.,'Confirmation')]");
	public final By ELEMENT_DELETE_PROJECT_POPUP_CANCEL_BTN=By.xpath("//*[@class='btn'][contains(.,'Cancel')]");
	public final String ELEMENT_DELETE_PROJECT_POPUP_MESSAGE="//*[contains(@class,'msg')]/../*[contains(.,'You are about to delete ${project} project and all its tasks. Please confirm')]";
	
	//*************************************Labels Management*****************************************//
	
	//Labels area-->General
	public final By ELEMENT_LEFT_PANE_LABELS_ARROW_MENU=By.xpath("//*[contains(@class,'label-name')][contains(.,'Labels')]/../*[contains(@class,'collapseTree')]/*[contains(@class,'ArrowDownMini')]");
	public final By ELEMENT_LEFT_PANE_LABELS_ARROW_MENU_DOWN=By.xpath("//*[contains(@class,'label-name')][contains(.,'Labels')]/../*[contains(@class,'collapseTree')]/*[contains(@class,'ArrowDownMini')]");
	public final By ELEMENT_LEFT_PANE_LABELS_ARROW_MENU_RIGHT=By.xpath("//*[contains(@class,'label-name')][contains(.,'Labels')]/../*[contains(@class,'collapseTree')]/*[contains(@class,'ArrowRightMini')]");
	public final String ELEMENT_LEFT_PANE_LABEL_ARROW_MENU_DOWN="//*[contains(@class,'label-name')][contains(.,'$label')]/../*[@class='collapseSubLabel']/*[contains(@class,'uiIconArrowDownMini')]";
	public final String ELEMENT_LEFT_PANE_LABEL_ARROW_MENU_RIGHT="//*[contains(@class,'label-name')][contains(.,'$label')]/../*[@class='collapseSubLabel']/*[contains(@class,'uiIconArrowRightMini')]";
	public final By ELEMENT_LEFT_PANE_NO_LABEL = By.xpath("//*[contains(@class,'list-labels')]/*[contains(.,'No Label')]");
	public final By ELEMENT_LEFT_PANE_TOOLTIP_LABEL = By.xpath("//*[@class='popover-content'][contains(.,'No Label. Click here to create your first label')]");
	public final By ELEMENT_LEFT_PANE_LABELS_PLUS_MENU=By.xpath("//*[contains(@class,'label-name')][contains(.,'Labels')]/../*[contains(@class,'add-new-label')]/*[contains(@class,'PlusMini')]");
	public final String ELEMENT_LEFT_PANE_LABEL_NAME="//*[contains(@class,'label-name')][contains(.,'$label')]";
	public final By ELEMENT_LEFT_PANE_LABELS=By.xpath("//*[contains(@class,'label-name')][contains(.,'Labels')]");
	public final By ELEMENT_LEFT_PANE_LABELS_ADD=By.xpath("//*[contains(@class,'label-name')][contains(.,'Labels')]/../*[contains(@class,'uiDropdownWithIcon')]//*[contains(@class,'AddLabel')]");
	public final String ELEMENT_LABEL_NAME = "//*[contains(@class,'label-title')][contains(.,'$label')]";
	public final By ELEMENT_WELCOME_TEXT_LABEL_DEFAULT= By.xpath("//*[@class='empty-content']/*[contains(.,'No Label')]");
	
	//Labels area-->Context menu
	public final String ELEMENT_LEFT_PANE_LABEL_MENU="//*[contains(@class,'label-name')][contains(.,'$label')]/../..//*[contains(@class,'RightMenu')]";
	public final String ELEMENT_LEFT_PANE_LABEL_ADD="//*[contains(@class,'label-name')][contains(.,'$label')]/../..//*[contains(@class,'AddLabel')]";
	public final String ELEMENT_LEFT_PANE_LABEL_EDIT="//*[contains(@class,'label-name')][contains(.,'$label')]/../..//*[contains(@class,'Edit')]";
	public final String ELEMENT_LEFT_PANE_LABEL_DELETE="//*[contains(@class,'label-name')][contains(.,'$label')]/../..//*[contains(@class,'Trash')]";
	
    //Add labels form
	public final By ELEMENT_ADD_LABEL_NAME_INPUT=By.xpath("//*[@class='list-labels']//input");
	
    //Edit labels form
	public final By ELEMENT_EDIT_LABEL_PARENT_SELECT=By.xpath(".//*[@class='lblParent']");
	public final String ELEMENT_EDIT_LABEL_PARENT_TEXT="//*[@id='lblParent']//*[contains(.,'$parent')]";
	public final By ELEMENT_EDIT_LABEL_DIALOG=By.xpath(".//*[contains(@class,'edit-label-dialog')]");
	public final By ELEMENT_EDIT_LABEL_CLOSE_BTN=By.xpath("//*[contains(@class,'edit-label-dialog')]//button[contains(.,'Close')]");
	public final By ELEMENT_EDIT_LABEL_SAVE_BTN=By.xpath("//*[contains(@class,'edit-label-dialog')]//button[contains(.,'Save')]");
	public final By ELEMENT_EDIT_LABEL_NAME = By.xpath(".//*[@id='lblName']");
	public final String ELEMENT_EDIT_LABEL_NAME_TEXT = ".//*[@id='lblParent']/*[@value='$id']";
	
	//Delete label
	public final By ELEMENT_DELETE_LABEL_POPUP_DELETE_BTN=By.xpath("//*[contains(@class,'btn-primary')][contains(.,'Delete')]");
	public final By ELEMENT_DELETE_LABEL_POPUP_CANCEL_BTN=By.xpath("//*[@class='btn'][contains(.,'Cancel')]");
	public final String ELEMENT_DELETE_LABEL_POPUP_MESSAGE="//*[contains(@class,'msg')]/../*[contains(.,'Are you sure you want to delete $label label')]";
	
	//Show and Hide labels
	public final By ELEMENT_LEFT_PANE_LABELS_SHOWHIDDEN=By.xpath("//*[contains(@class,'ShowLabel')]/../*[contains(.,'Show Hidden Labels')]");
	public final By ELEMENT_LEFT_PANE_LABELS_HIDEHIDDEN=By.xpath("//*[contains(@class,'ShowLabel')]/../*[contains(.,'Hide Hidden Labels')]");
	public final String ELEMENT_LEFT_PANE_LABEL_HIDE="//*[contains(@class,'label-name')][contains(.,'$label')]/../..//*[contains(@class,'uiIconHide')]/../*[contains(.,'Hide')]";
	public final String ELEMENT_LEFT_PANE_LABEL_SHOW="//*[contains(@class,'label-name')][contains(.,'$label')]/../..//*[contains(@class,'uiIconHide')]/../*[contains(.,'Show')]";
	
	//Change color
	public final String ELEMENT_LEFT_PANE_LABEL_COLOR = "//*[contains(@class,'label-name')][contains(.,'$label')]/../../*[contains(@class,'$color')][contains(@class,'colorPie')]";
	public final String ELEMENT_LEFT_PANE_LABEL_NO_COLOR = "//*[contains(@class,'label-name')][contains(.,'$label')]/../../*[@class=' colorPie']";
	
	//*************************************List View*****************************************//
	public final By ELEMENT_LIST_VIEW = By.xpath("//*[contains(@class,'btn-group')]/*[contains(@class,'btn')]/*[contains(.,'List')]");
	
	
	//List View-->General
	public final String ELEMENT_SORTBY_ITEM = "//*[contains(@class,'SortDown')]/../../*[contains(@class,'dropdown-menu')]/*[contains(.,'$sort')]";
	public final String ELEMENT_SORTBY_ITEM_DEFAULT = "//*[contains(@class,'SortDown')]/../*[contains(.,'$sort')]";
	public final String ELEMENT_GROUPBY_ITEM = "//*[contains(@class,'ViewList')]/../../*[contains(@class,'dropdown-menu')]/*[contains(.,'$group')]";
	public final String ELEMENT_GROUPBY_ITEM_DEFAULT = "//*[contains(@class,'ViewList')]/../*[contains(.,'$group')]";
	public final String ELEMENT_TASK_CLOCK_ICON = "//*[@class='taskList']//*[contains(@class,'taskName')][contains(.,'$task')]/..//*[contains(@class,'IconClock')]";
	public final String ELEMENT_TASK_CLOCK_ICON_TODAY = "//*[@class='taskList']//*[contains(@class,'taskName')][contains(.,'$task')]/..//*[contains(@class,'dropdown-menu')]/*[contains(.,'Later Today (+5 hours)')]";
	public final String ELEMENT_TASK_CLOCK_ICON_TOMORROW_MOR = "//*[@class='taskList']//*[contains(@class,'taskName')][contains(.,'$task')]/..//*[contains(@class,'dropdown-menu')]/*[contains(.,'Tomorrow Morning (9am the next day)')]";
	public final String ELEMENT_TASK_CLOCK_ICON_TOMORROW_AFT = "//*[@class='taskList']//*[contains(@class,'taskName')][contains(.,'$task')]/..//*[contains(@class,'dropdown-menu')]/*[contains(.,'Tomorrow Afternoon (1pm the next day)')]";
	public final String ELEMENT_TASK_CLOCK_ICON_NEXTWEEK = "//*[@class='taskList']//*[contains(@class,'taskName')][contains(.,'$task')]/..//*[contains(@class,'dropdown-menu')]/*[contains(.,'Next Week (next monday 9am)')]";
	public final String ELEMENT_TASK_DUEDATE = "//*[contains(@class,'taskName')][contains(.,'$task')]/../*[contains(.,'$day')]";
	public final String ELEMENT_TASK_DUEDATE_TODAY = "//*[contains(@class,'taskName')][contains(.,'$task')]/../*[contains(.,'Today')]";
	public final String ELEMENT_TASK_DUEDATE_TOMORROW = "//*[contains(@class,'taskName')][contains(.,'$task')]/../*[contains(.,'Tomorrow')]";
	public final String ELEMENT_TASK_DUEDATE_YESTERDAY = "//*[contains(@class,'taskName')][contains(.,'$task')]/../*[contains(.,'Yesterday')]";
	public final String ELEMENT_TASK_COMPLETE_ICON = "//*[contains(@class,'taskName')][contains(.,'$task')]/..//*[contains(@class,'Validate')]";
	
	//List View-->Group by Status
	public final By ELEMENT_GROUPBY_STATUS = By.xpath("//*[@data-groupby='status']");
	
	//List View-->Group by Project
	public final By ELEMENT_GROUPBY_PROJECT = By.xpath("//*[@data-groupby='project']");
	
	//List View-->Group by Assignee
	public final By ELEMENT_GROUPBY_ASSIGNEE = By.xpath("//*[@data-groupby='assignee']");
	public final String ELEMENT_GROUPBY_HEADER_FULLNAME = "//*[contains(@class,'group-name')][contains(.,'$header')]";
	public final String ELEMENT_GROUPBY_HEADER_NUM = "//*[contains(@class,'group-name')][contains(.,'$header')]/../*[contains(@class,'amount-item')][contains(.,'$num')]";
	public final String ELEMENT_GROUPBY_HEADER_SORT = "//*[contains(@class,'primaryBackground')][$num]//*[contains(@class,'group-name')][contains(.,'$header')]";
	//List View --> Group by Label
	public final By ELEMENT_GROUPBY_LABEL = By.xpath("//*[@data-groupby='label']");
	
	//List View - Group by Due Date
	public final By ELEMENT_GROUPBY_DUEDATE = By.xpath("//*[@data-groupby='dueDate']");
	//List View - Group by None
	public final By ELEMENT_GROUPBY_NONE = By.xpath("//*[@data-groupby='none']");
	//List View - Sort By Rank
	public final By ELEMENT_SORTBY_RANK = By.xpath("//*[@data-orderby='rank']");
	
	//List View - Sort by Priority
	public final By ELEMENT_SORTBY_PRIORITY = By.xpath("//*[@data-orderby='priority']");
	
	//List View - Sort by Created Date
	public final String ELEMENT_TASK_ORDER = ".//*[@id='taskManagement']//li[$num]/*[contains(.,'$task')]";
	public final By ELEMENT_SORTBY_CREATEDDATE = By.xpath("//*[@data-orderby='createdTime']");
	//List View - Sort by Due Date
	public final By ELEMENT_SORTBY_DUEDATE = By.xpath("//*[@data-orderby='dueDate']");
	
	//List View - Sort by Title
	public final By ELEMENT_SORTBY_TITLE = By.xpath("//*[@data-orderby='title']");
	
	//*************************************Board View*****************************************//
	public final By ELEMENT_BOARD_VIEW = By.xpath("//*[contains(@class,'btn-group')]/*[contains(@class,'btn')]/*[contains(.,'Board')]");
	public final String ELEMENT_BOARD_STATUS = "//*[contains(@class,'col')]/*[@data-name='name'][contains(.,'$flow')]";
	public final String ELEMENT_BOARD_STATUS_NUMBER = "//*[contains(@class,'col')]/*[@data-name='name'][contains(.,'$flow')]/../*[contains(@class,'number-tasks')][contains(.,'$num')]";
	public final String ELEMENT_BOARD_STATUS_DELETE_ICON = "//*[contains(@class,'col')]/*[@data-name='name'][contains(.,'$flow')]/..//*[contains(@class,'Delete')]";
	public final String ELEMENT_BOARD_STATUS_ADD_ICON = "//*[contains(@class,'col')]/*[@data-name='name'][contains(.,'$flow')]/..//*[contains(@class,'Plus')]";
	public final By ELEMENT_BOARD_STATUS_TITLE_INPUT= By.xpath("//*[contains(@class,'input-board')]");
	public final By ELEMENT_BOARD_STATUS_TITLE_INPUT_NEW= By.xpath("//*[@name='statusName']");
	public final String ELEMENT_BOARD_TASK_CONTAINER = "//*[contains(@class,'col$num')][contains(@data-connected,'taskContainer')]";
	public final String ELEMENT_BOARD_TASK_CONTAINER_GROUPBY_ASSIGNEE = "//*[contains(@class,'col$num')][contains(@data-connected,'$user')]";
	public final String ELEMENT_BOARD_TASK_TITLE_GROUPBY_ASSIGNEE_INPUT = "//*[contains(@class,'col$num')]//*[contains(@class,'createTaskInListView')]/*[@value='$user']/../*[@name='taskTitle']";
	public final String ELEMENT_BOARD_TASK_TITLE_INPUT = "//*[contains(@class,'col$num')]//*[contains(@class,'createTaskInListView')]/*[@value='none']/../*[@name='taskTitle']";
	public final String ELEMENT_BOARD_TASK_TITLE= "//*[contains(@class,'col$num')]//*[contains(@class,'taskName')][contains(.,'$task')]";
	public final String ELEMENT_BOARD_TASK_DRAG_ICON ="//*[contains(@class,'col$num')]//*[contains(@class,'taskName')][contains(.,'$task')]/../../span[contains(@class,'dragable')]";
	public final String ELEMENT_BOARD_TASK_BOX = "//*[contains(@class,'taskName')][contains(.,'$task')]/../..";
	public final String ELEMENT_BOARD_COL_ITEM="//*[contains(@class,'col$num1')]//*[contains(@class,'taskItem')][$num2]";
	public final String ELEMENT_BOARD_COL_ITEM_TASK ="//*[contains(@class,'col$num1')]//*[contains(@class,'taskItem')][$num2]//*[contains(@class,'taskName')][contains(.,'$task')]";
	public final String ELEMENT_BOARD_COL_ASSIGNEE = "//*[contains(@class,'col$num')][contains(@data-connected,'$user')]";
	public final String ELEMENT_BOARD_COL_ASSIGNEE_TASK = "//*[contains(@class,'col$num')][contains(@data-connected,'$user')]//*[contains(@class,'taskName')][contains(.,'$task')]";
	/***********************************************************RIGHT PANE******************************************************/
	// Project Overview
	public final By ELEMENT_RIGHT_PANE_ARROW_MENU= By.xpath("//*[contains(@class,'addProject')]//*[contains(@class,'ArrowDown')]");
	public final By ELEMENT_RIGHT_PANE_ARROW_MENU_CLONE= By.xpath("//*[contains(@class,'action-clone-project')]/*[contains(@class,'uiIconLightGray')]");
	public final By ELEMENT_RIGHT_PANE_ARROW_MENU_DELETE= By.xpath("//*[contains(@class,'action-delete-project')]/*[contains(@class,'uiIconLightGray')]");
	public final By ELEMENT_RIGHT_PANE_PARENT_PATH_LINK=By.xpath("//*[@data-name='parent']");
	public final String ELEMENT_RIGHT_PANE_PARENT_PATH_TEXT="//*[@data-name='parent']/*[@class='active'][contains(.,'$project')]";
	public final String ELEMENT_RIGHT_PANE_MANAGER_NAME ="//*[contains(@class,'prjAuthor')]//*[contains(@class,'avatar')][contains(.,'$user')]";
	public final By ELEMENT_RIGHT_PANE_CLOSE_ICON = By.xpath("//*[@class='title']/*[contains(.,'Project overview')]/..//*[contains(@class,'Close')]");
	public final By ELEMENT_RIGHT_PANE_HIDE_ICON = By.xpath(".//*[@id='ShowHideRight']/i");
	//Add project form
	public final By ELEMENT_ADD_PROJECT_UNTITLED= By.xpath(".//*[contains(@class,'addProject')]//*[contains(@data-name,'name')][contains(.,'Untitled Project')]");
    public final By ELEMENT_ADD_PROJECT_DES_INPUT=By.xpath(".//*[contains(@class,'addProject')]//*[contains(@name,'description')]");
    public final By ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_TEXT=By.xpath("//*[contains(@class,'uiGroupBoundCheckBox')]/*[contains(.,'Enable Calendar integration')]");
    
	//Edit project form
    public final String ELEMENT_RIGHT_PANE_PARENT_PATH_MATCH_VALUE="//*[contains(@class,'dropdown-menu')]//strong[contains(.,'$text')]";
    public final String ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU="//*[contains(@class,'dropdown-menu')]//a[contains(.,'$project')][@data-matched='true']";
    public final String ELEMENT_RIGHT_PANE_PARENT_PATH_FULL="//*[contains(@class,'dropdown-menu')]//*[contains(.,'Projects')]/*[contains(.,'$parent')]/li[contains(.,'$child')]";
	public final By ELEMENT_RIGHT_PANE_EDIT_PROJECT_DES_INPUT = By.xpath("//*[contains(@class,'addProject')]//*[contains(@data-name,'description')]");
	//Task Detail
	//arrow menu
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU=By.xpath("//*[contains(@class,'actionIcon')]//*[contains(@class,'ArrowDown')]");
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_CLONE= By.xpath("//*[contains(@class,'clone-task')]/*[contains(@class,'CloneNode')]");
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_DELETE= By.xpath("//*[contains(@class,'delete-task')]/*[contains(@class,'Delete')]");
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_WATCH= By.xpath("//*[@class='uiIconWatch']");
	public final By ELEMENT_RIGHT_PANE_TASK_CLOSE_ICON=By.xpath("//*[@class='uiIconClose']");
	public final By ELEMENT_DELETE_TASK_OK_BTN =By.xpath(".//*[contains(@class,'confirmDeleteTask')]//button[contains(.,'OK')]");
	public final By ELEMENT_DELETE_TASK_CANCEL_BTN =By.xpath(".//*[contains(@class,'confirmDeleteTask')]//button[contains(.,'Cancel')]");
	public final String ELEMENT_RIGHT_PANE_TASK_PERMALINK ="//*[@data-name='title'][contains(.,'$task')][@data-original-title='Click to edit']/../../..//*[contains(@class,'taskPermalink')]/*[@class='uiIconPermalink']";
	public final By ELEMENT_RIGHT_PANE_TASK_PERMALINK_VALUE = By.xpath("//*[@class='taskPermalinkContainer']//input");
	
	//title
	public final String ELEMENT_RIGHT_PANE_TASK_COMPLETE_ICON="//*[@data-name='title'][contains(.,'$task')]/..//*[contains(@class,'Validate')]";
	public final By ELEMENT_RIGHT_PANE_TASK_PROJECT_LINK = By.xpath("//*[@data-original-title='Click to edit']/*[@data-name='project']");
	public final By ELEMENT_RIGHT_PANE_TASK_PROJECT_REMOVE_ICON=By.xpath("//*[contains(@class,'removeProject')]/*[contains(@class,'Close')]");
	public final String ELEMENT_RIGHT_PANE_TASK_PROJECT_TEXT = "//*[@data-name='project']/*[contains(.,'$project')]";
	public final By ELEMENT_RIGHT_PANE_TASK_NOPROJECT_TEXT = By.xpath("//*[@data-name='project'][contains(.,'No project')]");
	public final String ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT = "//*[@data-name='title'][contains(.,'$task')][@data-original-title='Click to edit']";
	public final By ELEMENT_RIGHT_PANE_TASK_UNTITLED = By.xpath("//*[@data-name='title'][contains(.,'Untitled Task')][@data-original-title='Click to edit']");
	public final By ELEMENT_EDIT_TASK_TITLE_INPUT_LINK = By.xpath("//*[contains(@class,'taskName')]//*[contains(@data-name,'title')]/..//*[@class='blackLarge']");
	
	//description
	public final By ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_LINK = By.xpath("//*[@class='taskDescription']/*[@data-name='description'][@data-original-title='Click to edit']");
	public final String ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_TEXT = "//*[contains(@class,'taskDescription')]/*[@data-name='description']/*[contains(.,'$des')]";
	public final By ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_EMPTY = By.xpath("//*[contains(@class,'taskDescription')]/*[@data-name='description']/*[contains(.,'Empty')]");
	public final By ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_INPUT = By.xpath("//*[contains(@class,'addTask')]//*[@data-name='description']");
	//assign
	public final By ELEMENT_RIGHT_PANE_TASK_ASSIGN_LINK = By.xpath("//*[contains(@class,'assigned')]//*[@data-original-title='Click to edit']");
	public final By ELEMENT_RIGHT_PANE_TASK_ASSIGN_LINK_USER=By.xpath("//*[contains(@class,'assigned')]/*[contains(@class,'avatar')]/../*[@data-original-title='Click to edit']");
	public final String ELEMENT_RIGHT_PANE_TASK_ASSIGN = "//*[contains(@class,'assigned')]/*[contains(.,'$user')]";
	public final By ELEMENT_RIGHT_PANE_TASK_ASSIGN_INPUT = By.xpath("//*[@class='contBlock']//*[contains(@class,'TextArea')]/*[@type='text']");
	public final By ELEMENT_RIGHT_PANE_TASK_ASSIGN_INPUT_DISABLED = By.xpath("//*[@class='contBlock']//*[contains(@class,'TextArea')]/*[@type='text'][@disabled='']");
	public final String ELEMENT_RIGHT_PANE_TASK_ASSIGN_AUTOCOMPLETE = "//*[contains(@class,'dropdown')]/*[@data-value='$user']";
	public final By ELEMENT_RIGHT_PANE_TASK_COWORKER_INPUT = By.xpath("//*[@class='contBlock tags']//*[contains(@class,'TextArea')]/*[@type='text']");
	public final String ELEMENT_RIGHT_PANE_TASK_ASSIGN_TEXT = "//*[@class='contBlock']//*[contains(@class,'TextArea')]/*[contains(.,'$user')]";
	public final String ELEMENT_RIGHT_PANE_TASK_ASSIGN_REMOVE_ICON = "//*[@class='contBlock']//*[contains(@class,'TextArea')]/*[@data-value='$user']//*[contains(@class,'Close')]";
	public final String ELEMENT_RIGHT_PANE_TASK_ASSIGN_REMOVE_ICON1 = "//*[@class='contBlock']//*[contains(@class,'TextArea')]/*[contains(.,'$username')]//*[contains(@class,'Close')]";
	public final String ELEMENT_RIGHT_PANE_TASK_COWORKER_TEXT = "//*[@class='contBlock tags']//*[contains(@class,'TextArea')]/*[contains(.,'$user')]";
	public final String ELEMENT_RIGHT_PANE_TASK_COWORKER_REMOVE_ICON = "//*[@class='contBlock tags']//*[contains(@class,'TextArea')]/*[@data-value='$user']//*[contains(@class,'Close')]";
	public final String ELEMENT_RIGHT_PANE_TASK_COWORKER_REMOVE_ICON1 = "//*[@class='contBlock tags']//*[contains(@class,'TextArea')]/*[contains(.,'$username')]//*[contains(@class,'Close')]";
	public final String ELEMENT_RIGHT_PANE_TASK_DISPLAY_ASSIGN_COWORKER = ".//*[contains(@class,'assigned')]/*[contains(@class,'avatarMini')]/img/../../*[contains(.,'$num')]";
	public final String ELEMENT_RIGHT_PANE_TASK_DISPLAY_ONLY_COWORKER = "//*[contains(@class,'assigned')]/*[contains(@class,'avatarMini')]/img[contains(@src,'UserAvtDefault')]/../../*[contains(.,'$num')]";
			
	//workplan
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_LINK = By.xpath("//*[contains(@class,'StatusBlock')][@data-original-title='Click to edit']");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_DELETE_ICON = By.xpath("//*[contains(@class,'TrashMini')]");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_VISIBLE = By.xpath("//*[@class='date-work-plan']/*[contains(.,'Work planned')]");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_NEXTMONTH_ICON1 = By.xpath("//*[@id='BlockCalendar1']//*[@data-original-title='Next Month']/*[contains(@class,'ArrowRight')]");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_NEXTMONTH_ICON2 = By.xpath("//*[@id='BlockCalendar2']//*[@data-original-title='Next Month']/*[contains(@class,'ArrowRight')]");
	public final String ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROM_DAY = "//*[@id='BlockCalendar1']//*[@class='weekDays']//a[.='$day']";
	public final String ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TO_DAY = "//*[@id='BlockCalendar2']//*[@class='weekDays']//a[.='$day']";
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TO_CALENDAR = By.xpath("//*[@id='BlockCalendar2']//*[@class='weekDays']");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROM_CALENDAR = By.xpath("//*[@id='BlockCalendar1']//*[@class='weekDays']");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_ALLDAY_CHECKBOX=By.xpath("//*[@class='date-work-plan']//*[@type='checkbox']");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_ALLDAY_CHECKBOX_CHECK=By.xpath("//*[contains(@class,'all-day')]//*[@class='uiCheckbox']");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TOTIME_LINK= By.xpath("//*[@class='date-work-plan']//*[@name='toTime']");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROMTIME_LINK= By.xpath("//*[@class='date-work-plan']//*[@name='fromTime']");
	public final String ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TOTIME ="//*[@class='date-work-plan']//*[@name='toTime']/..//*[@data-time='$time']";
	public final String ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROMTIME ="//*[@class='date-work-plan']//*[@name='fromTime']/..//*[@data-time='$time']";
	public final String ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TEXT_DIFFDAY ="//*[@class='date-work-plan']/*[contains(.,'Work planned from $date1 to $date2 ($time)')]";
	public final String ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TEXT_1DAY ="//*[@class='date-work-plan']/*[contains(.,'Work planned for $date ($time)')]";
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_DEFAULT = By.xpath("//*[@class='date-work-plan']/*[contains(.,'Unscheduled')]");
	public final String ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TOTIME_SELECTED = "//*[@class='date-work-plan']//*[@name='toTime'][@value='$time']";
	public final String ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROMTIME_SELECTED = "//*[@class='date-work-plan']//*[@name='fromTime'][@value='$time']";
	public final String ELEMENT_RIGHT_PANE_TASK_WORKPLAN_TO_SELECTED = "//*[@id='BlockCalendar2']//*[@class='weekDays']//a[.='$day'][@class='selected']";
	public final String ELEMENT_RIGHT_PANE_TASK_WORKPLAN_FROM_SELECTED = "//*[@id='BlockCalendar1']//*[@class='weekDays']//a[.='$day'][@class='selected']";
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_CAL_ICON=By.xpath("//*[@class='date-work-plan']/i");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_REMOVE_CAL = By.xpath("//*[@class='date-work-plan']/*[@data-original-title='Click to remove from Calendar']");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKPLAN_SHOW_CAL = By.xpath("//*[@class='date-work-plan']/*[@data-original-title='Click to show in Calendar']");
	
	//status
	public final String ELEMENT_RIGHT_PANE_TASK_STATUS_TEXT = "//*[@class='toDo']/*[contains(@class,'uiEditableInline')]/*[contains(.,'$flow')]";
	public final By ELEMENT_RIGHT_PANE_TASK_STATUS_LINK = By.xpath("//*[@class='toDo']/*[contains(@class,'uiEditableInline')]/*[@data-name='status'][@data-original-title='Click to edit']");
	public final String ELEMENT_RIGHT_PANE_TASK_STATUS_SELECT = "//*[@class='toDo']//*[@class='selectboxMini']/option[.='$opt']";
	public final By ELEMENT_RIGHT_PANE_TASK_STATUS_SELECT_LINK = By.xpath("//*[@class='toDo']//*[@class='selectboxMini']");
	//priority
	public final String ELEMENT_RIGHT_PANE_TASK_PRIORITY_SELECT = "//*[@class='selectboxMini']/option[.='$opt']";
	public final By ELEMENT_RIGHT_PANE_TASK_PRIORITY_SELECT_LINK = By.xpath("//*[@class='selectboxMini']");
	public final By ELEMENT_RIGHT_PANE_TASK_PRIORITY_LINK = By.xpath("//*[contains(@class,'task-priority')][@data-original-title='Click to edit']");
	public final String ELEMENT_RIGHT_PANE_TASK_PRIORITY_TEXT = "//*[contains(@class,'priority')]/*[contains(.,'$priority')]";
	public final String ELEMENT_RIGHT_PANE_TASK_PRIORITY_ICON = "//*[@class='uiIconColorPriority$priority']";
	public final By ELEMENT_RIGHT_PANE_TASK_PRIORITY_NONE = By.xpath("//*[@class='selectboxMini']/option[.='UNDEFINED']");
	
	//duedate
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_LINK = By.xpath("//*[@data-name='duedate'][@data-original-title='Click to edit']");
	public final String ELEMENT_RIGHT_PANE_TASK_DUEDATE_TEXT = "//*[@data-name='duedate'][contains(.,'$date')]";
	public final By ELEMENT_RIGHT_PANE_TASK_NODUEDATE = By.xpath("//*[@data-name='duedate'][contains(.,'Due date')]");
	public final String ELEMENT_RIGHT_PANE_TASK_DUEDATE_DAY = "//*[@class='calendarComponent']//*[@id='BlockCalendar']//*[@class='weekDays']//a[.='$day']";
	public final String ELEMENT_RIGHT_PANE_TASK_DUEDATE_DAY_SELECTED = "//*[@class='calendarComponent']//*[@id='BlockCalendar']//*[@class ='weekDays']//a[.='$day'][@class='selected']";                                               
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_CALENDAR = By.xpath("//*[@class='calendarComponent']//*[@id='BlockCalendar']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_NONE = By.xpath("//*[@class='popover-content']//a[@data-date='none']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_TODAY = By.xpath("//*[@class='popover-content']//a[@data-date='today']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_TOMORROW = By.xpath("//*[@class='popover-content']//a[@data-date='tomorrow']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_NEXTWEEK = By.xpath("//*[@class='popover-content']//a[@data-date='nextweek']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_NEXTMONTH_ICON = By.xpath("//*[@class='calendarComponent']//*[@id='BlockCalendar']//*[@data-original-title='Next Month']/*[contains(@class,'ArrowRight')]");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_PREVIOUSMONTH_ICON = By.xpath("//*[@class='calendarComponent']//*[@id='BlockCalendar']//*[@data-original-title='Previous Month']/*[contains(@class,'ArrowLeft')]");
	//tag
	public final By ELEMENT_RIGHT_PANE_TASK_TAG_LINK=By.xpath("//*[@data-name='tags'][@data-original-title='Click to edit']");
	public final By ELEMENT_RIGHT_PANE_TASK_TAG_INPUT = By.xpath("//*[contains(@class,'tags uiEditableInline')]//*[contains(@class,'TextArea')]/*[@type='text']");
	public final String ELEMENT_RIGHT_PANE_TASK_TAG_TEXT="//*[contains(@class,'tags')]//*[contains(@class,'TextArea')]/*[contains(.,'$tag')]";
	public final String ELEMENT_RIGHT_PANE_TASK_TAG_TEXT1="//*[contains(@class,'tags')]//*[contains(@class,'label')][contains(.,'$tag')]";
	public final String ELEMENT_RIGHT_PANE_TASK_TAG_SELECT_NEW ="//*[contains(@class,'dropdown')]/*[contains(@class,'create')]/*[contains(.,'$tag')]";
	public final String ELEMENT_RIGHT_PANE_TASK_TAG_REMOVE_ICON="//*[contains(@class,'tags')]//*[contains(@class,'TextArea')]/*[contains(.,'$tag')]/*[contains(@class,'remove')]";
	public final By ELEMENT_RIGHT_PANE_TASK_TAG_DEFAULT_ICON = By.xpath("//*[contains(@class,'Hag')]");
	public final By ELEMENT_RIGHT_PANE_TASK_TAG_DEFAULT_LABEL = By.xpath("//*[contains(@class,'tags')]/*[contains(.,'Tags')]");
	//CKEditor
	public final By ELEMENT_CKEDITOR_BOLD = By.xpath("//*[contains(@class,'bold_icon')]");
	public final By ELEMENT_CKEDITOR_ITALIC = By.xpath("//*[contains(@class,'italic_icon')]");
	public final By ELEMENT_CKEDITOR_NUMBERLIST = By.xpath("//*[contains(@class,'numberedlist_icon')]");
	public final By ELEMENT_CKEDITOR_IFRAME = By.xpath("//iframe[contains(@class,'frame')]");
	
	//Task Comment
	public final String ELEMENT_RIGHT_PANE_COMMENT_TEXT = "//*[@class='author'][contains(.,'$user')]/../*[@class='contentComment'][contains(.,'$comment')]";
	public final String ELEMENT_RIGHT_PANE_COMMENT_TIME = "//*[@class='author'][contains(.,'$user')]/../*[@class='contentComment'][contains(.,'$comment')]/..//*[@class='date'][contains(.,'$time')]";
	public final String ELEMENT_RIGHT_PANE_COMMENT_INPUT = "//*[@class='commentInput']//*[contains(@class,'TextArea')][contains(.,'$comment')]";
	public final By ELEMENT_RIGHT_PANE_COMMENT_INPUT_TEXTAREA = By.xpath("//*[@class='commentInput']//*[contains(@class,'TextArea')]");
	public final By ELEMENT_RIGHT_PANE_COMMENT_LINK = By.id("Displayundefined");
	public final By ELEMENT_RIGHT_PANE_COMMENT_BUTTON = By.id("taskCommentButton");
	public final By ELEMENT_RIGHT_PANE_COMMENT_BUTTON_DISABLED = By.xpath("//*[@id='taskCommentButton'][@disabled='disabled']");
	public final By ELEMENT_RIGHT_PANE_COMMENT_TAB_LINK = By.xpath("//*[@href='#tab-comments']");
	public final String ELEMENT_RIGHT_PANE_COMMENT_VIEW_ALL = ".//*[@class='load-all-comments'][contains(.,'View all $num comments.')]";
	public final String ELEMENT_RIGHT_PANE_COMMENT_HIDE_ALL = ".//*[@class='load-all-comments'][contains(.,'Hide all $num comments.')]";
	public final String ELEMENT_RIGHT_PANE_COMMENT_MENTION = "//*[@class='autocomplete-menu']//*[@data-display='$user']";
	public final String ELEMENT_RIGHT_PANE_COMMENT_TRASH_ICON = "//*[@class='commentList']//*[@class='contentComment'][contains(.,'$comment')]/../..//*[contains(@class,'TrashMini')]";
	public final String ELEMENT_RIGHT_PANE_COMMENT_USER = "//*[@class='contentComment'][contains(.,'$comment')]/../..//*[@alt='$user']";
	//Task Change
	public final By ELEMENT_RIGHT_PANE_CHANGE_TAB_LINK = By.xpath("//*[@href='#tab-changes']");
	public final String ELEMENT_RIGHT_PANE_CHANGE_TEXT = "//*[@id='tab-changes']//a[contains(.,'$user')]/../*[contains(.,'$text')]";
	public final String ELEMENT_RIGHT_PANE_CHANGE_TEXT1 = "//*[@id='tab-changes']//a[contains(.,'$user')]/../*[contains(.,'$text')][contains(.,'$opt')]";
	public final String ELEMENT_RIGHT_PANE_CHANGE_TIME = "//*[@id='tab-changes']//a[contains(.,'$user')]/../*[contains(.,'$text')]/../../*[contains(@class,'date')][contains(.,'$time')]";
	
	public final String ELEMENT_AS_TASK_LINK = "//*[@class='description']/*[contains(.,'$task')]";
	//Label
	public final String ELEMENT_RIGHT_PANE_LABEL_TEXT = "//*[contains(@class,'rightPanelContent')]//*[contains(@class,'label')][contains(.,'$label')]";
}
