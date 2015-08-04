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
	public final By ELEMENT_LEFT_PANE_NO_PROJECT = By.xpath("//*[@class='list-projects']/*[contains(.,'No Project')]");
	public final String ELEMENT_LEFT_PANE_TOOLTIP = "//*[@class='popover-content'][contains(.,\"$mes\")]";
	public final By ELEMENT_TASK_APP_ICON = By.xpath("//*[@class='uiIconAppTaskManagementApplication uiIconDefaultApp']");
	
	//Project area-->General
	public final String ELEMENT_LEFT_PANE_PROJECT_NAME="//*[contains(@class,'list-projects')]//*[contains(@class,'project-name')][contains(.,'$project')]";
	public final By ELEMENT_LEFT_PANE_PROJECTS=By.xpath("//*[contains(@class,'project-name')][contains(.,'Projects')]");
	public final String ELEMENT_LEFT_PANE_SUBPROJECT_L1="//*[contains(@class,'dropdown project-item')]/*[contains(@class,'list-projects')]//*[contains(@class,'project-name')][contains(.,'$sub')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_ID=".//*[contains(@data-id,'$id')][contains(.,'$project')]";
	public final By ELEMENT_LEFT_PANE_PROJECTS_PLUS_MENU=By.xpath("//*[@class='uiIconLightGray uiIconSimplePlusMini addProject']");
	public final By ELEMENT_LEFT_PANE_PROJECTS_ADD=By.xpath("//*[@class='uiDropdownMenu']//*[@class='uiIconAddProject uiIconLightGray']");
	public final By ELEMENT_LEFT_PANE_PROJECTS_SHOWHIDDEN=By.xpath("//*[@class='uiDropdownMenu']//*[@class='uiIconShowProject uiIconLightGray']/../*[contains(.,'Show Hidden project')]");
	public final By ELEMENT_LEFT_PANE_PROJECTS_HIDEHIDDEN=By.xpath("//*[@class='uiDropdownMenu']//*[@class='uiIconShowProject uiIconLightGray']/../*[contains(.,'Hide Hidden project')]");
	public final String ELEMENT_LEFT_PANE_PROJECT_COLOR = "//*[contains(@class,'project-name')][contains(.,'$project')]/../*[contains(@class,'$color')][contains(@class,'colorPie')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_NO_COLOR = "//*[contains(@class,'project-name')][contains(.,'$project')]/../*[@class=' colorPie']";
	
	//Project area-->Context Menu
	public final String ELEMENT_LEFT_PANE_PROJECT_MENU="//*[@class='project-name' and contains(.,'$project')]/../*[@class='dropdown-toggle pull-right']/*[@class='uiIconRightMenu uiIconLightGray']";
	public final String ELEMENT_LEFT_PANE_PROJECTID_MENU="//*[contains(@data-id,'$id')][contains(.,'$project')]/../*[@class='dropdown-toggle pull-right']/*[@class='uiIconRightMenu uiIconLightGray']";
	public final String ELEMENT_LEFT_PANE_PROJECT_MENU_ID="//*[contains(@data-id,'$id')][contains(.,'$project')]/../*[@class='dropdown-toggle pull-right']/*[@class='uiIconRightMenu uiIconLightGray']";
	public final String ELEMENT_LEFT_PANE_PROJECT_HIDE="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='uiIconHide uiIconLightGray']/../*[contains(.,'Hide')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_SHOW="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='uiIconHide uiIconLightGray']/../*[contains(.,'Show')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_ADD="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='new-project']/*[@class='uiIconAddProject uiIconLightGray']";
	public final String ELEMENT_LEFT_PANE_PROJECT_SHARE="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='uiIconShare uiIconLightGray']";
	public final String ELEMENT_LEFT_PANE_PROJECT_CLONE="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='uiIconCloneNode uiIconLightGray']";
	public final String ELEMENT_LEFT_PANE_PROJECT_DELETE="//*[@class='project-name' and contains(.,'$project')]/..//*[@class='delete-project']/*[@class='uiIconTrash uiIconLightGray']";
	public final String ELEMENT_LEFT_PANE_PROJECT_ISHIDDEN="//*[@class='list-projects']/*[contains(@data-hiddenproject,'$bool')]//*[@class='project-name' and contains(.,'$project')]";
	public final String ELEMENT_LEFT_PANE_PROJECT_ISSHOWHIDDEN="//*[@data-showhiddenproject='$bool']//*[@class='project-name' and contains(.,'$project')]";
	
	//Color
	public final String ELEMENT_LEFT_PANE_COLOR_TABLE_ITEM=".//*[contains(@class,'project-name')][contains(.,'$project')]/..//*[contains(@class,'$color')]";
	public final String ELEMENT_LEFT_PANE_COLOR_NO_COLOR = "//*[contains(@class,'project-name')][contains(.,'$project')]/..//li[contains(@class,'noColor changeProjectColor')]";
	
	
	//Labels area
	
	
	
	
	
	/**********************************************************CENTRAL PANE******************************************************/
	
	//*************************************Task Management*****************************************//
	
	//Task area-->General
	public final String ELEMENT_TASK_TITLE= "//*[@class='taskList']//*[contains(@class,'column-item column-title')][contains(.,'$task')]";
	public final By ELEMENT_GROUPBY_ICON = By.xpath("//*[@class='actionIcon dropdown-toggle']/*[@class='uiIconViewList uiIconLightGray']");
	public final By ELEMENT_SORTBY_ICON = By.xpath("//*[@class='actionIcon dropdown-toggle']/*[@class='uiIconSortDown uiIconLightGray']");
	public final String ELEMENT_SORTBY_ITEM ="//*[@data-original-title='Due date']//../*[contains(.,'$item')]";
	public final String ELEMENT_GROUPBY_ITEM ="//*[@data-original-title='group By']//../*[contains(.,'$item')]";
	public final String ELEMENT_TASK_COLOR=".//*[contains(@class,'column-item column-title')][contains(.,'$task')]/..//*[contains(@class,'$color')][contains(@class,'project-color')]";
	public final String ELEMENT_TASK_NO_COLOR=".//*[contains(@class,'column-item column-title')][contains(.,'$task')]/..//*[@class='project-color ']";
	public final String ELEMENT_TASK_ORDER = ".//*[@id='taskManagement']//li[$num]/*[contains(.,'$task')]";
	
	//Add Task form
	public final By ELEMENT_ADD_TASK_BTN = By.xpath("//*[@class='btn btn-primary btn-add-task']/*[@class='uiIconSimplePlusMini']");
	public final By ELEMENT_ADD_TASK_TITLE = By.name("taskTitle");
	public final String ELEMENT_UNTITLEDTASK_AND_TASK_INPUT = "//*[@class='column-item column-title taskName'][contains(.,'$task')]/../../../../../..//*[@class='form-create-task']/input";
	
	//Task area-->Incoming
    public final String ELEMENT_INCOMING_ROW_TASK = "//li[@class='row-item row-odd hover-action-reveal has-border has-background taskItem']/*[contains(.,'$task')]";
	
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
	public final By ELEMENT_SHARE_PROJECT_EDIT_MANAGER_ICON = By.xpath("//*[@data-type='manager'][@class='manager']//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_ICON =  By.xpath("//*[@data-type='participant'][@class='manager']//*[@class='uiIconEdit uiIconLightGray']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SAVE_BTN = By.xpath("//*[@data-type='manager'][@class='manager']//*[@class='btn savePermission']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SAVE_BTN = By.xpath("//*[@data-type='participant'][@class='manager']//*[@class='btn savePermission']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_MANAGER_INPUT = By.xpath("//*[@data-type='manager'][@class='manager']//*[@type='text']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_INPUT = By.xpath("//*[@data-type='participant'][@class='manager']//*[@type='text']");
	public final By ELEMENT_CLOSE_BTN = By.xpath("//*[@class='btn'][contains(.,'Close')]");
	public final String ELEMENT_SHARE_PROJECT_EDIT_MANAGER_TEXT = "//*[@data-type='manager'][@class='manager']//*[@class='replaceTextArea editable' ]/*[contains(.,'$user')]";
	public final String ELEMENT_SHARE_PROJECT_EDIT_MANAGER_REMOVE_ICON = "//*[@data-type='manager'][@class='manager']//*[@class='replaceTextArea editable' ]/*[contains(.,'$user')]//*[@class='uiIconClose uiIconLightGray']";
	public final String ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_TEXT = "//*[@data-type='participant'][@class='manager']//*[@class='replaceTextArea editable' ]/*[contains(.,'$user')]";
	public final String ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_REMOVE_ICON = "//*[@data-type='participant'][@class='manager']//*[@class='replaceTextArea editable' ]/*[contains(.,'$user')]//*[@class='uiIconClose uiIconLightGray']";
	public final String ELEMENT_SHARE_PROJECT_DISPLAY_MANAGER = "//*[@data-type='manager'][@class='manager']//*[@class='list-users'][contains(.,'$user')]";
	public final String ELEMENT_SHARE_PROJECT_DISPLAY_PARTICIPANT = "//*[@data-type='participant'][@class='manager']//*[@class='list-users'][contains(.,'$user')]";
	public final By ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SELECT_USER_ICON = By.xpath("//*[@data-type='manager'][@class='manager']//*[@class='uiIconUser uiIconLightGray']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SELECT_GROUP_ICON = By.xpath("//*[@data-type='manager'][@class='manager']//*[@class='uiIconGroup uiIconLightGray']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SELECT_USER_ICON = By.xpath("//*[@data-type='participant'][@class='manager']//*[@class='uiIconUser uiIconLightGray']");
	public final By ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SELECT_GROUP_ICON = By.xpath("//*[@data-type='participant'][@class='manager']//*[@class='uiIconGroup uiIconLightGray']");
	
	//Clone project
	public final By ELEMENT_CLONE_PROJECT_CLONE_BUTTON = By.xpath("//*[@class='confirmCloneProject uiPopup modal hide fade in']//*[@class='btn btn-primary']");
	public final By ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX = By.xpath("//*[@class='confirmCloneProject uiPopup modal hide fade in']//*[@class='checkbox cloneTask']");
	public final By ELEMENT_CLONE_PROJECT_CLONE_TASK_CHECKBOX_TEXT = By.xpath("//*[@class='confirmCloneProject uiPopup modal hide fade in']//*[@class='checkbox cloneTask']/../*[contains(.,'also clone uncompleted task.')]");
	public final By ELEMENT_CLONE_PROJECT_CANCEL_BUTTON = By.xpath("//*[@class='confirmCloneProject uiPopup modal hide fade in']//*[@class='btn'][contains(.,'Cancel')]");
	public final String ELEMENT_CLONE_PROJECT_POPUP_MESSAGE="//*[@data-msg='You are about to clone <strong>{}</strong> project. Please confirm']/*[contains(.,'$project')]";
	
	
	//Show and Hide project
	
	
	//Delete project
	public final By ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX=By.xpath(".//*[contains(@class,'checkbox deleteChild')]");
	public final By ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX_TEXT=By.xpath(".//*[contains(@class,'checkbox deleteChild')]/../*[contains(.,'also delete all sub-projects')]");
	public final By ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN=By.xpath("//*[@class='btn btn-primary confirmDelete']");
	public final By ELEMENT_CONFIRMATION_POPUP_TITLE=By.xpath("//*[@class='PopupTitle popupTitle'][contains(.,'Confirmation')]");
	public final By ELEMENT_DELETE_PROJECT_POPUP_CANCEL_BTN=By.xpath("//*[@class='btn'][contains(.,'Cancel')]");
	public final String ELEMENT_DELETE_PROJECT_POPUP_MESSAGE="//*[contains(@class,'msg')]/../*[contains(.,'You are about to delete ${project} project and all its tasks. Please confirm')]";
	
	//*************************************Labels Management*****************************************//
	
	//Labels-->General
	
	
	//Add labels form
	
	
	//Edit labels form
	
	
	//Delete label
	
	
	//Show and Hide labels
	
	
	//Change color
	
	
	
	//*************************************List View*****************************************//
	public final By ELEMENT_LIST_VIEW = By.xpath("//*[@class='alignBtnGroup center']/*[contains(@class,'btn')]/*[contains(.,'List')]");
	public final By ELEMENT_BOARD_VIEW = By.xpath("//*[@class='alignBtnGroup center']/*[contains(@class,'btn')]/*[contains(.,'Board')]");
	
	//List View-->General
	public final String ELEMENT_PROJECT_DEFAULT_SORTBY = "//*[@class='uiIconSortDown uiIconLightGray']/../*[contains(.,'$sort')]";
	public final String ELEMENT_PROJECT_DEFAULT_GROUPBY = "//*[@class='uiIconViewList uiIconLightGray']/../*[contains(.,'$group')]";
	public final String ELEMENT_TASK_CLOCK_ICON = "//*[@class='column-item column-title taskName'][contains(.,'$task')]/..//*[contains(@class,'uiIcon uiIconClock')]";
	public final String ELEMENT_TASK_DUEDATE = "//*[@class='column-item column-title taskName'][contains(.,'$task')]/../*[@class='column-item column-time']/*[contains(.,'$day')]";
	public final String ELEMENT_TASK_COMPLETE_CHECKBOX = "//*[@class='column-item column-title taskName'][contains(.,'$task')]/../*[@class='column-item column-checkbox']/*[contains(@class,'uiCheckbox')]/*[contains(@class,'checkbox')]";
	public final String ELEMENT_TASK_COMPLETE_DISPLAY_CHECKBOX = "//*[@class='column-item column-title taskName'][contains(.,'$task')]/../*[@class='column-item column-checkbox']/*[contains(@class,'uiCheckbox')]";
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
    public final String ELEMENT_ADD_PROJECT_DES_CKEDITOR="//*[contains(@class,'addProject')]//*[contains(@data-name,'description')]/ol/li/strong[contains(.,'$des')]";
    public final By ELEMENT_ADD_PROJECT_ENABLE_CALENDAR_CHECKBOX=By.xpath(".//*[contains(@class,'addProject')]//*[contains(@class,'checkbox')]");
    public final By ELEMENT_ADD_PROJECT_ENABLE_CALENDAR=By.xpath(".//*[@class='checkbox']/../*[contains(.,'Enable Calendar Integration')]");
 
	//Edit project form
    public final String ELEMENT_RIGHT_PANE_PARENT_PATH_MATCH_VALUE="//*[contains(@class,'uiDropdownMenu dropdown-menu')]//strong[contains(.,'$text')]";
    public final String ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU="//*[contains(@class,'uiDropdownMenu dropdown-menu')]//a[contains(.,'$project')]";
    public final String ELEMENT_RIGHT_PANE_PARENT_PATH_FULL="//*[contains(@class,'uiDropdownMenu dropdown-menu')]//*[contains(.,'Projects')]/*[contains(.,'$parent')]/li[contains(.,'$child')]";
	public final By ELEMENT_RIGHT_PANE_EDIT_PROJECT_DES_INPUT = By.xpath("//*[contains(@class,'addProject')]//*[contains(@data-name,'description')]");
    
	//Task Detail
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU=By.xpath("//*[contains(@class,'dropdown-toggle actionIconSmall')]/*[@class='uiIconArrowDown']");
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_CLONE= By.xpath("//*[contains(@class,'action-clone-task')]/*[@class='uiIconCloneNode']");
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_DELETE= By.xpath("//*[contains(@class,'action-delete-task')]/*[@class='uiIconDelete']");
	public final By ELEMENT_RIGHT_PANE_TASK_ARROW_MENU_WATCH= By.xpath("//*[@class='uiIconWatch']");
	public final String ELEMENT_RIGHT_PANE_TASK_WORKFLOW_TEXT = "//*[@class='toDo']/*[@class='uiEditableInline']/*[contains(.,'$flow')]";
	public final By ELEMENT_RIGHT_PANE_TASK_PROJECT_LINK = By.xpath("//*[@data-name='project']");
	public final String ELEMENT_RIGHT_PANE_TASK_PROJECT_TEXT = "//*[@data-name='project']/*[contains(.,'$project')]";
	public final String ELEMENT_RIGHT_PANE_TASK_TITLE_TEXT = "//*[@data-name='title'][contains(.,'$task')]";
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_LINK = By.xpath("//*[@data-name='duedate']");
	public final String ELEMENT_RIGHT_PANE_TASK_DUEDATE_TEXT = "//*[@data-name='duedate'][contains(.,'$date')]";
	public final String ELEMENT_RIGHT_PANE_TASK_DUEDATE_DAY = "//*[@class='calendarComponent']//*[@id='BlockCalendar']//*[@class='weekDays']//a[.='$day']";
	public final By ELEMENT_RIGHT_PANE_TASK_STATUS_LINK = By.xpath("//*[@data-name='status']");
	public final By ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_LINK = By.xpath("//*[@class='taskDescription']/*[@data-name='description']");
	public final String ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_TEXT = "//*[contains(@class,'taskDescription')]/*[@data-name='description']/*[contains(.,'$des')]";
	public final By ELEMENT_RIGHT_PANE_TASK_DESCRIPTION_INPUT = By.xpath("//*[contains(@class,'addTask')]//*[@data-name='description']");
	public final By ELEMENT_RIGHT_PANE_TASK_ASSIGN_LINK = By.xpath("//*[@class='unassigned ']/*[@class='hoverStatus small editableField editAssignee']");
	public final By ELEMENT_RIGHT_PANE_TASK_WORKFLOW_LINK = By.xpath("//*[@class='hoverStatus hoverStatusBlock small editableField']");
	public final By ELEMENT_RIGHT_PANE_TASK_PRIORITY_LINK = By.xpath("//*[@class='task-priority hoverStatus editableField editable small editable-click']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_NONE = By.xpath("//*[@class='popover-content']//a[@data-date='none']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_TODAY = By.xpath("//*[@class='popover-content']//a[@data-date='today']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_TOMORROW = By.xpath("//*[@class='popover-content']//a[@data-date='tomorrow']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_NEXTWEEK = By.xpath("//*[@class='popover-content']//a[@data-date='nextweek']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_NEXTMONTH_ICON = By.xpath("//*[@class='calendarComponent']//*[@id='BlockCalendar']//*[@data-original-title='Next Month']/*[@class='uiIconMiniArrowRight uiIconLightGray']");
	public final By ELEMENT_RIGHT_PANE_TASK_DUEDATE_PREVIOUSMONTH_ICON = By.xpath(".//*[@id='BlockCalendar'][1]//*[@data-original-title='Previous Month']/*[@class='uiIconMiniArrowLeft uiIconLightGray']");
	
	//CKEditor
	public final By ELEMENT_CKEDITOR_BOLD = By.xpath("//*[@class='cke_button_icon cke_button__bold_icon']");
	public final By ELEMENT_CKEDITOR_ITALIC = By.xpath("//*[@class='cke_button_icon cke_button__italic_icon']");
	public final By ELEMENT_CKEDITOR_NUMBERLIST = By.xpath("//*[@class='cke_button_icon cke_button__numberedlist_icon']");
	public final By ELEMENT_CKEDITOR_IFRAME = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");
	
	//Task Comment
	public final String ELEMENT_RIGHT_PANE_COMMENT_TEXT = "//*[@class='author'][contains(.,'$user')]/../*[@class='contentComment'][contains(.,'$comment')]";
	public final String ELEMENT_RIGHT_PANE_COMMENT_INPUT = "//*[@class='commentInput']//*[@class='replaceTextArea editable'][contains(.,'$comment')]";
	public final By ELEMENT_RIGHT_PANE_COMMENT_LINK = By.id("Displayundefined");
	public final By ELEMENT_RIGHT_PANE_COMMENT_BUTTON = By.id("taskCommentButton");
	public final By ELEMENT_RIGHT_PANE_COMMENT_TAB_LINK = By.xpath("//*[@href='#tab-comments']");
	
	//Task Change
	public final By ELEMENT_RIGHT_PANE_CHANGE_TAB_LINK = By.xpath("//*[@href='#tab-changes']");
}
