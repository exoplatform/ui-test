package org.exoplatform.selenium.platform.calendar;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.EventManagement.recurringType;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
public class CalendarManagement extends CalendarLocatorObject{
	
	PlatformPermission pPer;
	EventManagement evMg;
	ManageAlert alert;
    Button button;
    CalendarHomePage cHome;
	/**
	 * constructor
	 * @param dr
	 */
	public CalendarManagement(WebDriver dr){
		this.driver=dr;
		pPer = new PlatformPermission(dr);
		alert = new ManageAlert(dr);
		button = new Button(dr);
		evMg = new EventManagement(dr);
		cHome = new CalendarHomePage(dr);
	};

	/**
	 * View list in calendar (Click icon +)
	 */
	public enum menuOfMainCalendar{
		IMPORT, ADDCAL, REMOTECAL, ADDCATEGORY, CALSETTING
	}

	

	/**
	 * Execute action of calendar: Edit, Delete, Share, export....
	 * @param action
	 * 				action that needs to be done, e.g.: "ShareCalendar"
	 */
	public void goToMenuFromMainCalendar(menuOfMainCalendar action){
		info("Select action from menu");
		click(ELEMENT_CALENDAR_MENU_ACTIONS_ICON,0,true);
		waitForAndGetElement(ELEMENT_CALENDAR_MENU);
		switch(action){
		case ADDCAL:
			info("Go to add calendar");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_ADD,0,true);
			waitForAndGetElement(ELEMENT_CALENDAR_ADD_FORM);
			break;
		case REMOTECAL:
			info("Go to remote calendar");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_REMOTE,0,true);
			waitForAndGetElement(ELEMENT_REMOTE_CALENDAR_FORM);
			break;
		case ADDCATEGORY:
			info("Go to add category calendar");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_ADD_EVENT_CATEGORY,0,true);
			waitForAndGetElement(ELEMENT_ADD_EVENT_CATEGORY_FORM);
			break;
		case CALSETTING:
			info("Go to calendar setting");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_CALENDAR_SETTING,0,true);
			waitForAndGetElement(ELEMENT_CALENDAR_SETTING_FORM);
			break;
		case IMPORT:
			info("Import calendar");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_IMPORT,0,true);
			waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
			break;
		default:
			info("Go to add calendar");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_ADD,0,true);
			waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM);
			break;
		}
	}
	/**
	 * View list in calendar (click on an calendar - Click icon *)
	 */
	public enum menuOfCalendarOption{
		ADDTASK, ADDEVENT, EDIT, REMOVE, SHARE, IMPORT, EXPORT, REFRESH,COLOR
	}
	
	/**
	 * Open Calendar setting form from Setting button on Actions Bar
	 */
	public void openSettingCalendar(){
		info("Click on Settings icon");
		click(ELEMENT_ACTION_BAR_SETTING_ICON);
		waitForAndGetElement(ELEMENT_CALENDAR_SETTING_FORM);
		info("Settings Calendar form is shown");
	}
	
	/** 
	 * Delete a calendar
	 * 
	 * @param name
	 * 				name of calendar
	 * @param verify
	 * 				optional parameter. If not be set, by default, it is considered that calendar is deleted 
	 * 				= true: verify that calendar is deleted, 
	 * 				= false: not verify that calendar is deleted, 
	 */
	public void deleteCalendar(String name, boolean...verify){
		if(waitForAndGetElement(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar",name),2000,0)!=null){
			boolean isVerify = (verify.length > 0 ? verify[0]: false);
			info("Remove calendar");
			driver.navigate().refresh();
			executeActionCalendar(name, menuOfCalendarOption.REMOVE);
			if(isVerify)
				alert.verifyAlertMessage(ELEMENT_CONFIRM_REMOVE_CALENDAR_MSG);
			else
				click(ELEMENT_YES_BUTTON);
			waitForElementNotPresent(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", name));
		}
		
	}

	/** 
	 * Edit a calendar
	 * 
	 * @param oldName
	 * 				old name of calendar
	 * @param name
	 * 				new name of calendar
	 * @param description
	 * 				new description of calendar
	 * @param color
	 * 				new color of calendar
	 * @param group
	 * 				group: example (/developers, /platform/administrators, /platform/users, /platform/web-contributors, 
	 * 				/organization/management/executive-board, /organization/employees
	 */
	public void editCalendar(String oldName,String name, String description, String color, String group){
		executeActionCalendar(oldName, menuOfCalendarOption.EDIT);
		inputDataInDetailTabCalendarForm(name, description, color);
		if(group!=null && group!="")
			selectGroupInGroupTabCalendarForm(group,true);
	}

	/** 
	 * Input into tab Detail of Add calendar form
	 * 
	 * @param name
	 * 			name of calendar
	 * @param description
	 * 			description of calendar
	 * @param color
	 * 			color of calendar
	 */
	public void inputDataInDetailTabCalendarForm(String name, String description, String color){
		info("Input into tab Detail of Add calendar form");
		click(ELEMENT_CALENDAR_DETAIL_TAB);
		if(name != null && name!="")
			type(ELEMENT_CALENDAR_DISPLAY_NAME_INPUT,name,true);
		if(description != null && description!="")
			type(ELEMENT_CALENDAR_DESC_INPUT, description,true);
		if(color != null && color !=""){
			click(ELEMENT_CALENDAR_COLOR);
			click(ELEMENT_CALENDAR_COLOR_SELECT.replace("${color}", color));
		}
	}

	/**
	 * selectGroupInGroupTabCalendarForm
	 * @param group
	 * 				group: example (/developers, /platform/administrators, /platform/users, /platform/web-contributors, 
	 * 				/organization/management/executive-board, /organization/employees
	 * @param isType
	 * 				true: select a group by typing
	 * 				false: select a group by select group icon
	 */
	public void selectGroupInGroupTabCalendarForm(String group,boolean isType){
		info("Input into tab Show in Group of Add calendar form");
		click(ELEMENT_CALENDAR_GROUP_TAB);
		if(group!=null&&group!=""){
			if(isType){
				type(ELEMENT_CALENDAR_GROUP_INPUT,group,true);
			}
			else{
				click(ELEMENT_CALENDAR_SELECT_GROUP_ICON);
				click(group);
			}
		}
		click(ELEMENT_CALENDAR_ADD_GROUP_BUTTON);
	}
	/**
	 * Remove a group in group table of Calendar form
	 * @param groupName
	 */
	public void removeGroupInGroupTabCalendarForm(String groupName){
		info("Input into tab Show in Group of Add calendar form");
		click(ELEMENT_CALENDAR_GROUP_TAB);
		click(ELEMENT_CALENDAR_GROUP_REMOVE_BTN.replace("$group",groupName));
		waitForElementNotPresent(ELEMENT_CALENDAR_GROUP_REMOVE_BTN.replace("$group",groupName));
	}
	/**
	 * Select a user/role who has edit permission in a group
	 * @param user
	 * @param mode
	 * 				way to input users, groups.
	 *            =0: type directly
	 *            =1: select user
	 *            =2: select role
	 */
	public void selectUserEditPermissionInGroup(String[] user,int...mode){
		for(int i = 0; i < user.length; i++){
			int modeUser = mode.length > i ? mode[i] : 0;
			switch (modeUser){
			case 0: 
				info("user"+user[i]);
				type(ELEMENT_CALENDAR_GROUP_INPUT_USER,user[i],true);
				break;
			case 1:
				click(ELEMENT_CALENDAR_GROUP_SELECT_USER_BTN); 
				click(ELEMENT_CALENDAR_GROUP_USER_IN_SELECT_FORM.replace("$user",user[i])); break;
			case 2: 
				String[] groupMem = user[i].split(":");
				String[] membership = groupMem[1].split(".");
				click(ELEMENT_CALENDAR_GROUP_SELECT_ROLE_BTN);
				pPer.selectGroupMembership(groupMem[0],membership[1]); break;
			}
		}
	}
	/**
	 * Select group permission
	 * @param group
	 * @param membership
	 */
	public void selectGroupPermission(String group,String membership){
		info("select group:"+group);
		click(ELEMENT_CALENDAR_SELECT_GROUP_ICON,0,true);
		waitForAndGetElement(ELEMENT_CALENDAR_GROUP_SELECT_FORM);
		click(ELEMENT_CALENDAR_GROUP_SELECT_LIST.replace("$group", group));
		waitForElementNotPresent(ELEMENT_CALENDAR_GROUP_SELECT_FORM);
		click(ELEMENT_CALENDAR_ADD_GROUP_BUTTON,0,true);
		Utils.pause(1000);
		click(ELEMENT_CALENDAR_GROUP_SELECT_ROLE_BTN);
		waitForAndGetElement(ELEMENT_CALENDAR_GROUP_SELECT_FORM);
		click(ELEMENT_CALENDAR_GROUP_SELECT_LIST.replace("$group", membership));
		waitForElementNotPresent(ELEMENT_CALENDAR_GROUP_SELECT_FORM);
		Utils.pause(1000);
		
	}
	/**
	 * Check user selector in group calendar
	 * @param cal
	 * @param user
	 * @param isPresent
	 */
	public void checkUserSelectorOfGroupCalendar(String cal,String user,boolean isPresent){
		executeActionCalendar(cal, menuOfCalendarOption.EDIT);
		click(ELEMENT_CALENDAR_GROUP_TAB);
		click(ELEMENT_CALENDAR_GROUP_SELECT_USER_BTN); 
		if(isPresent)
			waitForAndGetElement(ELEMENT_CALENDAR_GROUP_USER_IN_SELECT_FORM.replace("$user", user));
		else
			waitForElementNotPresent(ELEMENT_CALENDAR_GROUP_USER_IN_SELECT_FORM.replace("$user", user));
	}
	/**
	 * Save add calendar
	 */
	public void saveAddCalendar(){
		info("Save add calendar");
		click(ELEMENT_CALENDAR_ADD_SAVE_BUTTON);
		Utils.pause(2000);
	}
	/**
	 * Cancel add calendar
	 */
	public void cancelAddCalendar(){
		info("Click on Cancel button");
		click(ELEMENT_CALENDAR_ADD_CANCEL_BUTTON);
		Utils.pause(2000);
	}

	/** 
	 * Go to Calendar Actions> Add Event Category
	 * 
	 * @param categoryName
	 * 				category name of Calendar
	 */
	public void addEventCategory(String categoryName){
		info("----Add new event category----");
		goToMenuFromMainCalendar(menuOfMainCalendar.ADDCATEGORY);
		type(ELEMENT_ADD_EVENT_CATEGORY_INPUT,categoryName,true);
		click(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_ADD);
		info("----Verify if event category is added in Category List or not----");
		waitForAndGetElement(ELEMENT_LIST_EVENT_CATEGORY.replace("${categoryName}", categoryName));
		click(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE);	 
	}

	/** 
	 * Delete Event Category
	 * 
	 * @param categoryName
	 * 				category name of calendar
	 */
	public void deleteEventCategory(String categoryName){
		info("Delete category");
		goToMenuFromMainCalendar(menuOfMainCalendar.ADDCATEGORY);
		waitForAndGetElement(ELEMENT_LIST_DELETE_EVENT_BUTTON.replace("${categoryName}",categoryName));
		click(ELEMENT_LIST_DELETE_EVENT_BUTTON.replace("${categoryName}",categoryName));
		alert.acceptAlert();
		button.yes();
		waitForElementNotPresent(ELEMENT_LIST_DELETE_EVENT_BUTTON.replace("${categoryName}",categoryName));
		click(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE);	
	}

	/** 
	 * Edit Event Category
	 * 
	 * @param oldCategory
	 * 						old category name
	 * @param newCategory
	 * 						new category name
	 */

	public void editEventCategory(String oldCategory, String newCategory){
		waitForAndGetElement(ELEMENT_LIST_EDIT_EVENT_BUTTON.replace("${categoryName}",oldCategory));
		click(ELEMENT_LIST_EDIT_EVENT_BUTTON.replace("${categoryName}",oldCategory));
		type(ELEMENT_ADD_EVENT_CATEGORY_INPUT,newCategory,true);
		click(ELEMENT_EDIT_EVENT_CATEGORY_BUTTON_UPDATE);
		waitForAndGetElement(ELEMENT_LIST_EDIT_EVENT_BUTTON.replace("${categoryName}",newCategory));
		click(ELEMENT_ADD_EVENT_CATEGORY_BUTTON_CLOSE);
	}

	/**
	 * Open menu an a calendar
	 * @param calendar
	 * 					name of calendar
	 */
	public void openMenuOfCalendar(String calendar){
		info("Open menu of a calendar");
		Utils.pause(2000);
		mouseHoverByJavaScript(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar", calendar),2);
		clickByJavascript(ELEMENT_CALENDAR_SETTING_ICON.replace("$calendar", calendar),2);
		waitForAndGetElement(ELEMENT_CALENDAR_RIGHT_MENU);
		Utils.pause(1000);
	}

	/**
	 * Execute action of calendar: Edit, Delete, Share, export....
	 * 
	 * @param calendar
	 * 				name of calendar
	 * @param action
	 * 				action that needs to be done, e.g.: "ShareCalendar"
	 * @param color
	 *              color that is selected for calendar
	 */
	public void executeActionCalendar(String calendar, menuOfCalendarOption action,String... color){
		info("Select action from menu");
		openMenuOfCalendar(calendar);
		switch(action){
		case ADDTASK:
			clickByJavascript(ELEMENT_CALENDAR_ADD_TASK_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM);
			break;
		case ADDEVENT:
			clickByJavascript(ELEMENT_CALENDAR_ADD_EVENT_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM);
			break;
		case EDIT:
			clickByJavascript(ELEMENT_CALENDAR_EDIT_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_ADD_FORM);
			break;
		case REMOVE:
			clickByJavascript(ELEMENT_CALENDAR_REMOVE_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_REMOVE_FORM);
			break;
		case SHARE:
			clickByJavascript(ELEMENT_CALENDAR_SHARE_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_SHARE_FORM);
			break;
		case IMPORT:
			clickByJavascript(ELEMENT_CALENDAR_IMPORT_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
			break;
		case EXPORT:
			clickByJavascript(ELEMENT_CALENDAR_EXPORT_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_POPUP_FORM);
			break;
		case REFRESH:
			clickByJavascript(ELEMENT_CALENDAR_REFRESH_MENU,2);
			break;
		case COLOR:
			info("Select a color");
			click(ELEMENT_CALENDAR_MENU_ACTIONS_COLOR.replace("${color}",color[0]));
			break;
		default:
			click(ELEMENT_CALENDAR_ADD_TASK_MENU,2);
			waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_TASK_FORM);
			break;
		}
	}

	/** 
	 * Share a calendar
	 * 
	 * @param calendar
	 * 				name of calendar
	 * @param userGroup
	 * 				array of users or groups that are shared with
	 * @param canEdit
	 * 				array of "canEdit" permissions for users, groups respectively inputed by userGroup variable 
	 * @param mode
	 * 				way to input users, groups.
	 *            =0: type directly
	 *            =1: select user
	 *            =2: select group
	 *            =3: select membership
	 */
	public void shareCalendar(String calendar, String[] userGroup, boolean[] canEdit, int...mode){
		info("Share calendar");
		executeActionCalendar(calendar, menuOfCalendarOption.SHARE);
		if (userGroup.length>0 && !userGroup[0].isEmpty()) {
			for (int i = 0; i < userGroup.length; i++) {
				int modeUser = mode.length > i ? mode[i] : 0;
				switch (modeUser) {
				case 0:
					info("userGroup[i]" + userGroup[i]);
					type(ELEMENT_CALENDAR_SHARE_INPUT, userGroup[i], true);
					break;
				case 1:
					click(ELEMENT_CALENDAR_SELECT_USER_ICON);
					pPer.selectUserPermission(userGroup[i], 1);
					break;
				case 2:
					String[] group = userGroup[i].split(":");
					click(ELEMENT_CALENDAR_SELECT_GROUP_ICON);
					pPer.selectGroupPermission(group[0]);
					break;
				case 3:
					String[] groupMem = userGroup[i].split(":");
					String[] membership = groupMem[1].split(".");
					click(ELEMENT_CALENDAR_SELECT_MEMBERSHIP_ICON);
					pPer.selectGroupMembership(groupMem[0], membership[1]);
					break;
				}
			}
			click(ELEMENT_CALENDAR_SHARE_ADD_BUTTON);
		}
		
		for(int j=0; j < canEdit.length; j++){
			if(canEdit[j]==true){
				check(ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION.replace("${user}", userGroup[j]),2);
			}
			else{
				uncheck(ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION.replace("${user}", userGroup[j]),2);
			}
		}
		click(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
	}
	/**
	 * Check user selector of share calendar
	 * @param cal
	 * @param user
	 * @param isPresent
	 */
	public void checkUserSelectorOfShareCalendar(String cal,String user,boolean isPresent){
		executeActionCalendar(cal, menuOfCalendarOption.SHARE);
		click(ELEMENT_CALENDAR_SELECT_USER_ICON);
		Utils.pause(500);
		pPer.checkUserSelector(user, isPresent);
		click(pPer.ELEMENT_USER_CLOSE_BUTTON);
	}
	/** 
	 * Share a calendar
	 * 
	 * @param calendar
	 * 				name of calendar
	 * @param userGroup
	 * 				array of users or groups that are shared with
	 */
	public void removeUserGrooupFromShareCalendar(String calendar, String[] userGroup){
		info("Share calendar");
		executeActionCalendar(calendar, menuOfCalendarOption.SHARE);
		for(int i = 0; i < userGroup.length; i++){
			click(By.xpath(ELEMENT_DELETE_SHARE_USER.replace("{$user}",userGroup[i])));
		}
		click(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
	}

	/** 
	 * Upload calendar
	 * 
	 * @param path
	 * 			path of a file which will be uploaded.
	 */
	public void uploadCalendar(String path){
		info("--Upload Calendar--");
		WebElement element = waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_SELECT_FILE, DEFAULT_TIMEOUT, 1, 2);
		((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';", element);
		element.sendKeys(getAbsoluteFilePath(path));
		String[] links = path.split("/");
		waitForAndGetElement(".//*[contains(@class,'selectFileFrame')]//*[contains(text(),'"+links[links.length-1]+"')]");
		//waitForAndGetElement("//*[text()='"+links[links.length-1]+"']");
		info("import " + getAbsoluteFilePath(path));
		switchToParentWindow();
		waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_DELETE_ICON);
	}

	/** 
	 * Import calendar
	 * 
	 * @param path
	 * 				path of a file which is for upload
	 * @param name
	 * 				name of calendar
	 * @param description
	 * 				description of calendar
	 * @param color
	 * 				color of calendar
	 */
	public void importCalendar(String path, String name, String description, String color){
		/*click(ELEMENT_CALENDAR_MENU_ACTIONS_ICON);
		Utils.pause(3000);
		click(ELEMENT_CALENDAR_MENU_ACTIONS_IMPORT);
		*/
		goToMenuFromMainCalendar(menuOfMainCalendar.IMPORT);
		//waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
		uploadCalendar(path);
		if(name != null)
			type(ELEMENT_CALENDAR_IMPORT_NAME_INPUT,name,true);
		if(description != null)
			type(ELEMENT_CALENDAR_IMPORT_DESC_INPUT,description,true);

		if (color != null){
			click(ELEMENT_CALENDAR_IMPORT_COLOR);
			click(ELEMENT_CALENDAR_COLOR_SELECT.replace("${color}", color));
		}
		click(ELEMENT_CALENDAR_IMPORT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
		driver.navigate().refresh();
		waitForAndGetElement(By.linkText(name));
		button.ok();
	}

	/**
	 * Import event/task to an calendar
	 * @param calendar
	 *         name of calendar will be imported
	 * @param path
	 *         path of a file which is for upload
	 */      
	public void importTaskEvent(String calendar,String path){
		executeActionCalendar(calendar, menuOfCalendarOption.IMPORT);
		uploadCalendar(path);
		click(ELEMENT_CALENDAR_IMPORT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_IMPORT_POPUP_FORM);
		this.driver.navigate().refresh();
	}


	/**
	 * Export calendar
	 * 
	 * @param calendar
	 * 				name of calendar
	 * @param name
	 * 				filename of exported calendar
	 */
	public void exportCalendar(String calendar, String name){
		info("Export calendar");
		executeActionCalendar(calendar, menuOfCalendarOption.EXPORT);
		waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_POPUP_FORM);
		type(ELEMENT_CALENDAR_EXPORT_FILE_NAME,name,true);
		click(ELEMENT_CALENDAR_EXPORT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_EXPORT_POPUP_FORM);
	}
	/**
	 * Export Task/Event
	 * @param taskEvent
	 *                name of task or event
	 * @param name
	 *                filenam of exported task/event
	 */
	public void exportTaskEvent(String taskEvent, String name){
		info("Export calendar");
		rightClickOnElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}",taskEvent));
		selectOptionByRightclickOnEvent(contextMenuEditEvenOption.EXPORT);
		waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_POPUP_FORM);
		type(ELEMENT_CALENDAR_EXPORT_FILE_NAME,name,true);
		click(ELEMENT_CALENDAR_EXPORT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_EXPORT_POPUP_FORM);
	}

	/**
	 * change values in setting tab of calendar setting form
	 * @param viewtype
	 * 					default view fof calendar: Week, Day, Month, List, Work Week
	 * @param timezone
	 * 					time zone of calendar, e.g.: (GMT -11:00) Pacific/Samoa	 * 				
	 * @param dateformat
	 * 					date format of calendar:dd/mm/yyyy, dd-mm-yyyy, mm/dd/yyyy, mm-dd-yyyy
	 * @param timeformat
	 * 					time format of calendar: AM/PM, 24 Hours
	 * @param day
	 * 			 start day of week: monday, tuesday, wednesday, thursday, saturday, sunday
	 * @param isShow
	 * 			true: show working time
	 * 			false: don't show working time
	 * @param option
	 * 			option to send mail inviatation: NEVER, ASK, ALWAYS
	 */
	public void changeSettingCalendar(String viewtype, String timezone, String dateformat, String timeformat,
			String day, Boolean isShow, selectInvitationOption option){
		if(viewtype!=null && viewtype != ""){
			info("-- Select filter option of view type --");
			select(ELEMENT_CALENDAR_SETTING_VIEW_TYPE,viewtype);
		}
		if(timezone!=null && timezone != ""){
			info("-- Select filter option of Timezone --");
			select(ELEMENT_CALENDAR_SETTING_TIME_ZONE,timezone);	
		}
		if(dateformat!=null && dateformat != ""){
			info("-- Select filter option of date format --");
			select(ELEMENT_CALENDAR_SETTING_DATE_FORMAT,dateformat);
		}
		if(timeformat!=null && timeformat != ""){
			info("-- Select filter option of time format --");
			select(ELEMENT_CALENDAR_SETTING_TIME_FORMAT,timeformat);
		}
		if(day!=null && day != ""){
			info("-- Select filter option of week start on --");
			select(ELEMENT_CALENDAR_SETTING_WEEK_START_ON,day);
		}
		if(isShow!=null){
			info("-- Select show working time or not --");
			if(isShow)
				check(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_TIME_CHECKBOX,2);
			else
				uncheck(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_TIME_CHECKBOX,2);
		}

		if(option!=null){
			info("-- Select send invitaion option --");
			switch(option){
			case NEVER:
				check(ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX,2);
				break;
			case ALWAYS:
				check(ELEMENT_CALENDAR_SETTING_ALWAYS_SEND_INVITE_CHECKBOX,2);
				break;
			case ASK:
				check(ELEMENT_CALENDAR_SETTING_ASK_SEND_INVITE_CHECKBOX,2);
				break;
			default:
				check(ELEMENT_CALENDAR_SETTING_NEVER_SEND_INVITE_CHECKBOX,2);
				break;
			}
		}
	}

	/**
	 * change start and end time for working time in calendar setting form
	 * @param startTime
	 * @param endTime
	 */
	public void changeWorkingTime(String startTime,String endTime){
		if(!startTime.isEmpty())
			select(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_BEGIN_TIME,startTime);
		if(!endTime.isEmpty())
			select(ELEMENT_CALENDAR_SETTING_SHOW_WORKING_END_TIME,endTime);
	}
	/**
	 * save setting form
	 */
	public void saveSetting(){
		click(ELEMENT_SETTING_FORM_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_SETTING_FORM);
	}

	/**
	 * cancel setting form
	 */
	public void cancelSetting(){
		click(ELEMENT_SETTING_FORM_CANCEL_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_SETTING_FORM);

	}

	/**
	 * show/hide personal calendar in tab displayed calendar of calendar setting form
	 * @param calendarName
	 * 						name of calendar
	 * @param isShow
	 * 						true: check show calendar
	 * 						false: hide calendar
	 */
	public void showHideCalendar(String calendarName, Boolean isShow){
		info("Show/Hide personal calenar");
		goToDisplayCalendarTab();
		if(isShow!=null){
			if(isShow){
				if(waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName),5000,0)!=null)
					click(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName));
				waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName));
			}
			else{
				if(waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName),5000,0)!=null)
					click(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName));
				waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName));
			}
		}
		else{
			if(waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName),5000,0)!=null)
				click(ELEMENT_DISPLAY_CALENDAR_FORM_UNCHECKED.replace("$calendar", calendarName));
			waitForAndGetElement(ELEMENT_DISPLAY_CALENDAR_FORM_CHECKED.replace("$calendar", calendarName));
		}
	}
	/**
	 * Show/Hide event/task from Calendar list
	 * @param calendar
	 */
	public void showHideEventTask(String calendar){
		info("Show/Hide event/task");
		click(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar",calendar));
		Utils.pause(2000);
	}

	/**
	 * Add/Edit new feed
	 * @param name
	 * 				name of feed
	 * @param url
	 * 				url of feed
	 * @param calendars
	 * 				calendar  list (split by "/": ex: John Smith/Development)
	 */
	public void addEditFeed(String name, String url, String calendars){
		info("Add new feed");
		if(name!=null && name!=""){
			type(ELEMENT_FEED_NAME_INPUT,name,true);
		}
		if(url!=null && url!=""){
			type(ELEMENT_FEED_URL_INPUT,name,true);
		}
		if(calendars!=null && calendars!=""){
			String[] calendar = calendars.split("/");
			for(int i =0; i<calendar.length; i++){
				select(ELEMENT_FEED_CALENDAR_OPTION,calendar[i]);
				click(ELEMENT_FEED_EDIT_FEED_ADD_CALENDAR);
			}
		}
	}

	/**
	 * Generate URL
	 */
	public void generateUrl(){
		info("Generate URL");
		click(ELEMENT_FEED_EDIT_FEED_GENERATE_URL);
	}

	/**
	 * Reset URL
	 */
	public void resetUrl(){
		info("Reset URL");
		click(ELEMENT_FEED_EDIT_FEED_RESET_URL);
	}

	/**
	 * delete calendar from feed
	 * @param calendar
	 * 					name of calendar
	 */
	public void deleteCalendarFromFeed(String calendar){
		info("delete calendar from feed");
		if(calendar!=null && calendar!=""){
			click(ELEMENT_FEED_EDIT_FEED_DELETE_CALENDAR.replace("$name", calendar));
			waitForElementNotPresent(ELEMENT_FEED_EDIT_FEED_DELETE_CALENDAR.replace("$name", calendar));
		}
	}
	
	/**
	 * Delete Feed
	 * @param feed
	 * 				name of feed
	 * @param isVerify
	 * 				true: verify confirm message
	 * 				false: not verify confirm message
	 */
	public void deleteFeed(String feed, Boolean isVerify){
		info("Delete Feed");
		ManageAlert alert = new ManageAlert(driver);
		Button button = new Button(driver);
		click(ELEMENT_FEED_LIST_ITEM_DELETE_BUTTON.replace("$name", feed));
		if(isVerify!=null){
			if(isVerify)
				alert.verifyAlertMessage(ELEMENT_FEED_CONFIRM_DELETE);
			else
				click(button.ELEMENT_YES_BUTTON);
		}
		else
			click(button.ELEMENT_YES_BUTTON);
	}
	
	
	
	/**
	 * 
	 * right click on an even to show context menu
	 */
	public enum contextMenuEditEvenOption{
		VIEW,EDIT,DELETE,DELETE_RECURRING,EXPORT;
	}
	/**
	 * Select an option in context menu
	 * @param option
	 */
	public void selectOptionByRightclickOnEvent(contextMenuEditEvenOption option){
		switch(option){
		case VIEW:
			info("Select View option");
			click(ELEMENT_CONTEXT_MENU_VIEW);
			break;
		case EDIT:
			info("Select Edit option");
			click(ELEMENT_CONTEXT_MENU_EDIT);
			break;
		case DELETE:
			info("Select Delete option");
			click(ELEMENT_CONTEXT_MENU_DELETE);
			//click(ELEMENT_CONFIRM_POPUP_OK);
			break;
		case DELETE_RECURRING:
			info("Select Delete option");
			click(ELEMENT_CONTEXT_MENU_DELETE);
			break;
		case EXPORT:
			info("Select Export option");
			click(ELEMENT_CONTEXT_MENU_EXPORT);
			break;
		default:
			info("No option to select");
			break;
		}
	}
	/**
	 * Open edit event form by right click on the event
	 * @param name
	 */
	public void openEditPopupEventByRightClick(String name){
		info("Right click on an Event/Task");
		rightClickOnElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name));
		selectOptionByRightclickOnEvent(contextMenuEditEvenOption.EDIT);
		waitForAndGetElement(ELEMENT_ADD_EDIT_EVENT_POPUP,3000,1);
		info("Edit/Task form is shown");
	}
	
	/**
	 * Open add/edit event popup by double clicking
	 * @param name
	 * @param opt is an instance of a repeated event as 1,2,3,4....
	 */
	public void openEditEventTaskPopup(String name,selectViewOption view, String... opt) {
		info("Edit an event");
		Actions action = new Actions(this.driver);
		cHome.goToView(view);
		if (opt.length > 0 && opt[0] != null) {
			switch(view){
			case WEEK:
				WebElement el_week = waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY
						.replace("$date", opt[0]).replace("$name",name));
				Utils.pause(1000);
				scrollElementIntoView(el_week);
				action.moveToElement(el_week).doubleClick().perform();
				break;
			case MONTH:
				WebElement el_month = waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.
						replace("$date",opt[0]).replace("$name",name));
				Utils.pause(1000);
				scrollElementIntoView(el_month);
				action.moveToElement(el_month).doubleClick().perform();
				break;
			case WORKWEEK:
				WebElement el_workweek = waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY
						.replace("$date", opt[0]).replace("$name",name));
				Utils.pause(1000);
				scrollElementIntoView(el_workweek);
				action.moveToElement(el_workweek).doubleClick().perform();
				break;
			default:
				info("Please input only Month, Week and WorkWeek view");
				break;
			}
			info("Double click on the event");
			
		}else{
			info("Double click on the event");
			scrollElementIntoView(this.driver.findElement(By
					.xpath(ELEMENT_EVENT_TASK_TITLE.replace("${name}", name))));
			action.moveToElement(
					waitForAndGetElement(ELEMENT_EVENT_TASK_TITLE.replace(
							"${name}", name))).doubleClick().perform();
		}
		waitForAndGetElement(ELEMENT_ADD_EDIT_EVENT_POPUP,4000,0);
		info("The edit form is shown");
	}
	
	/**
	 * Open quick Add Event/Task popup by index in Month View
	 * @param col
	 * @param row
	 */
	public void openQuickAddByClickOnIndexInMonthView(String col,String row){
		info("Go to Month view");
		cHome.goToView(selectViewOption.MONTH);
		click(ELEMENT_EVENT_TASK_DAY_BY_INDEX_MONTH_VIEW.replace("$col",col).replace("$row",row));
		waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM);
		info("Quick Add Event task is shown");
	}
	
	/**
	 * Remove an event or a task in any views by right Click
	 * @param name
	 */
	public void deleteTaskEvent(String name){
			info("Right click on an Event/Task");
			//scrollElementIntoView(this.driver.findElement(By.xpath(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name))));
			rightClickOnElement(By.xpath(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name)));
			selectOptionByRightclickOnEvent(contextMenuEditEvenOption.DELETE);
			click(ELEMENT_YES_BUTTON);
			waitForElementNotPresent(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name));
	}
	/**
	 * Delete task/event by selecting an task/event's checkbox in List View
	 * @param name
	 */
	public void deleteTaskEventInListView(String name){
		if(!name.isEmpty()){
			cHome.goToView(selectViewOption.LIST);
			info("Select the event/task");
			check(ELEMENT_EVENT_TASK_CHECKBOX_LIST_VIEW.replace("$name",name),2);
			deleteTaskEvent();
			waitForElementNotPresent(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name));
		}
		
	}
	
	/**
	 * Delete task/events by click on Delete button on header bar of Month or List View
	 */
	public void deleteTaskEvent(recurringType... type){
		info("Click on Delete button");
		click(ELEMENT_EVENT_TASK_DELETE_BUTTON);
		confirmDeleteEventTask();
		Utils.pause(2000);
	}
	/**
	 * Confirm deleting Events/Tasks
	 */
	public void confirmDeleteEventTask(recurringType... type){
		if (waitForAndGetElement(ELEMENT_CONFIRM_POPUP_OK, 2000, 0) != null)
			click(ELEMENT_CONFIRM_POPUP_OK);
		if (waitForAndGetElement(evMg.ELEMENT_EDIT_DELETE_ONE_EVENT,2000, 0) != null){
			if(type.length>0)
				evMg.deleteRecurringConfirm(type[0]);
			else
				evMg.deleteRecurringConfirm(recurringType.ALL_EVENT);
		}
		if (waitForAndGetElement(ELEMENT_CONFIRM_POPUP_DELETE, 2000, 0) != null){
			click(ELEMENT_CONFIRM_POPUP_DELETE);
			waitForElementNotPresent(ELEMENT_CONFIRM_POPUP_DELETE);
		}
			
	}
	
	/**
	 * Remove an event or a task on List tab
	 * @param name
	 */
	public void deleteAllTaskEvent() {
		if (waitForAndGetElement(ELMENT_CALENDAR_TAB_LIST_EMPTY, 3000, 0) == null) {
			info("Select all task/events in the list tab");
			check(ELEMENT_CALENDAR_LIST_TAB_SELECT_ALL_CHECKBOX, 2);
			click(ELEMENT_EVENT_TASK_DELETE_BUTTON);
			if (waitForAndGetElement(ELEMENT_CONFIRM_POPUP_OK, 2000, 0) != null)
				click(ELEMENT_CONFIRM_POPUP_OK);
			if (waitForAndGetElement(evMg.ELEMENT_EDIT_DELETE_ONE_EVENT,2000, 0) != null){
				evMg.deleteRecurringConfirm(recurringType.ALL_EVENT);
			}
			if (waitForAndGetElement(ELEMENT_CONFIRM_POPUP_DELETE, 2000, 0) != null)
				click(ELEMENT_CONFIRM_POPUP_DELETE);
		}
		Utils.pause(2000);

	}

	/**
	 * Scroll to element to view
	 * @param element
	 */
	public void scrollElementIntoView(WebElement element) {
		info("Scroll to the element to view");
	    ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/**
	 * function: check content of mail then delete mail in email server
	 * @param titleEventTask
	 * @param fileAttch
	 * @param link
	 * @param user
	 * @param opParams
	 */
	public void checkEmailNotificationCalendar(String titleEventTask,String fileAttch,String link,String user,Object... opParams){
		info("Check and delete mail");
		Boolean checkOrNo = (Boolean)(opParams.length > 0 ? opParams[0]: true);
		String parentWindow = driver.getWindowHandle();
		info("parentWindow:"+parentWindow);
		  for(String windowHandle  : driver.getWindowHandles()){
			     driver.switchTo().window(windowHandle);
			     info("driver.title:"+driver.getTitle());
		}
		if (opParams.length > 0) {
			if (checkOrNo == true)
				waitForAndGetElement(ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL.replace("$eventTask",titleEventTask),30000,1);
            else 
            	waitForElementNotPresent(ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL.replace("$eventTask",titleEventTask),30000,1);
		}
		if(opParams.length>1){
			info("Click on the email");
			click(ELEMENT_GMAIL_CONTENT_INVITATION_EMAIL.replace("$eventTask",titleEventTask));
			Utils.pause(2000);
			for(String windowHandle  : driver.getWindowHandles()){
			     driver.switchTo().window(windowHandle);
			     info("driver.title:"+driver.getTitle());
		    }
			if(!fileAttch.isEmpty())
				waitForAndGetElement(ELEMENT_GMAIL_EMAIL_ICS_FILE.replace("$file",fileAttch));
			
			if(!link.isEmpty()){
				info("Click on the link");
				click(ELEMENT_GMAIL_EMAIL_DETAIL_LINK.replace("$link",link));
				for(String windowHandle  : driver.getWindowHandles()){
				     driver.switchTo().window(windowHandle);
				     info("driver.title:"+driver.getTitle());
			    }
				if (!user.isEmpty()) {
					if (waitForAndGetElement(ELEMENT_REFUSE_INVITATION_MESSAGE.replace("$user",user), 2000, 0) != null)
						assert true;
					else if (waitForAndGetElement(ELEMENT_MAYBE_INVITATION_MESSAGE.replace("$user",user), 2000, 0) != null)
						assert true;
					else if (waitForAndGetElement(ELEMENT_ACCEPT_INVITATION_MESSAGE.replace("$user",user), 2000, 0) != null)
						assert true;
					else
						assert false : "Wrong message or Cannot access to the link";
				}
			}
		}
	}
	/**
	 * Open display calendar tab in Calendar setting form
	 */
	public void goToDisplayCalendarTab(){
		info("Click on the Display calendar tab");
		click(ELEMENT_CALENDAR_SETTING_DISPLAY_TAB);
		Utils.pause(2000);
	}
	/**
	 * Open Feed tab in Calendar setting form
	 */
	public void goToFeedTab(){
		info("Click on the Display calendar tab");
		click(ELEMENT_CALENDAR_SETTING_FEED_TAB);
		Utils.pause(2000);
	}
	
	/**
	 * Enabled or disabled public access for an calendar
	 * @param calendar
	 */
	public void enabledPublicAccess(String calendar){
		info("Click on Edit link");
		executeActionCalendar(calendar, menuOfCalendarOption.EDIT);
		info("Click on the link");
		if (waitForAndGetElement(ELEMENT_CALENDAR_EDIT_ENABLE_PUBLIC_LINK,3000, 0) != null){
			click(ELEMENT_CALENDAR_EDIT_ENABLE_PUBLIC_LINK);
			waitForAndGetElement(ELEMENT_CALENDAR_EDIT_DISNABLE_PUBLIC_LINK);
		}else{
			click(ELEMENT_CALENDAR_EDIT_DISNABLE_PUBLIC_LINK);
			waitForAndGetElement(ELEMENT_CALENDAR_EDIT_ENABLE_PUBLIC_LINK);
		}
		
	}
	/**
	 * Get public access link of public calendar
	 * @return returnText
	 */
	public String getPublicAccessLink(String calendar){
		info("Click on Edit link");
		if (waitForAndGetElement(ELEMENT_CALENDAR_EDIT_DISNABLE_PUBLIC_LINK,3000, 0) != null){
			click(ELEMENT_CALENDAR_EDIT_PUBLIC_LINK_BTN);
		}
		String returnText= waitForAndGetElement(ELEMENT_CALENDAR_EDIT_FEED_LINK).getText();
		click(ELEMENT_CALENDAR_EDIT_FEED_CLOSED_BTN);
		return returnText;
		
	}
	
    /**
     * Add Remote Calendar
     * @param url
     *             url of the remote calendar
     * @param isChangeType
     *             true: if want to change type of remote calendar
     *             false: if want to keep default type of remote calendar
     * @param name
     *             the display name of remote calendar
     * @param des  
     *             the description of remote calendar
     * @param type
     *             the mode radio of remote calendar
     */
	
	public void addRemoteCalender(String url,boolean isChangeType,String name, String des,int... type){
		goToMenuFromMainCalendar(menuOfMainCalendar.REMOTECAL);
		if(isChangeType){
			info("Select a type");
			if(type.length>0)
				check(ELEMENT_REMOTE_CALENDAR_ICALENDAR_RADIO,2);
			else
				check(ELEMENT_REMOTE_CALENDAR_CALDAV_RADIO,2);
		}
		info("input url link to the field");
		if(!url.isEmpty())
			type(ELEMENT_REMOTE_CALENDAR_URL,url,true);
		
		info("Click on Next button");
		click(ELMENT_REMOTE_CALENDAR_NEXT_BTN);
		
		if(!name.isEmpty()){
			info("Type a name");
			type(ELEMENT_REMOTE_CALENDAR_NAME,name,true);
		}
		
		if(!des.isEmpty()){
			info("Type a description");
			type(ELEMENT_REMOTE_CALENDAR_DES,des,true);
		}
		Utils.pause(2000);
	}
	
	public void changeColorRemoteCalendar(String color){
		if (!color.isEmpty()){
			info("Click on color field");
			click(ELEMENT_REMOTE_CALENDAR_COLOR_FIELD);
			click(ELEMENT_REMOTE_CALENDAR_COLOR_SELECT.replace("${color}", color));
		}
	}
	/**
	 * UnCheck user authentication checkbox
	 */
	public void unCheckUserAuthentication(){
		info("UnCheck user Authentication checkbox");
		uncheck(ELEMENT_REMOTE_CALENDAR_AUTHENTICATION_CHECKBOX);
		waitForAndGetElement(ELEMENT_REMOTE_CALENDAR_USERNAME_FIELD_DISABLED);
	}
	/**
	 * Check user authentication checkbox
	 */
	public void checkUserAuthentication(){
		info("Check user Authentication checkbox");
		check(ELEMENT_REMOTE_CALENDAR_AUTHENTICATION_CHECKBOX);
		waitForElementNotPresent(ELEMENT_REMOTE_CALENDAR_USERNAME_FIELD_DISABLED);
	}
	/**
	 * Input username and password of user authentication in remote calendar
	 * @param username
	 * @param password
	 */
	public void addUserAuthentication(String username,String password){
		if(!username.isEmpty()){
			info("Input username");
			type(ELEMENT_REMOTE_CALENDAR_USERNAME_FIELD_ENABLED,username,true);
		}
		if(!password.isEmpty()){
			info("Input password");
			type(ELEMENT_REMOTE_CALENDAR_PASSWORD_FIELD_ENABLED,password,true);
		}
		
		Utils.pause(2000);
	}
	
	/**
	 * Save all changes of Remote calendar
	 */
	public void saveRemoteCalendar(){
		info("Click on Save button");
		click(ELEMENT_REMOTE_CALENDAR_SAVE_BTN);
		Utils.pause(2000);
	}
	
	/**
	 * Cancel all changes of remote calendar
	 */
	public void cancelRemoteCalendar(){
		info("Click on Cancel button");
		click(ELEMENT_REMOTE_CALENDAR_CANCEL_BTN);
		Utils.pause(2000);
	}
	/**
	 * Click on Back button of remote calendar
	 */
	public void backRemoteCalendar(){
		info("Click on Back button");
		click(ELEMENT_REMOTE_CALENDAR_BACK_BTN);
		Utils.pause(2000);
	}
	/**
	 * quick search an event/task
	 * @param name
	 */
	public void searchQuickEventTask(String name){
		info("Search an event/task");
		if(!name.isEmpty()){
			info("Input key search");
			type(ELEMENT_EVENT_TASK_QUICK_SEARCH,name,true);
			click(ELEMENT_EVENT_TASK_SEARCH_BTN);
			Utils.pause(2000);
			waitForAndGetElement(ELEMENT_EVENT_TASK_TITLE.replace("${name}",name));
		}
	}
	/**
	 * Close search function
	 */
	public void closeSearch(){
		info("click on Close search button");
		click(ELEMENT_EVENT_TASK_CLOSE_SEARCH_BTN);
		Utils.pause(2000);
	}
	/**
	 * Right click on Event/Tasks
	 * @param name
	 * @param date
	 */
	public void rightClickEventTaskInMonth(String name, String date){
		info("Right click on Event/Task");
		rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$date",
        		getLastDayOfWeek("MMM dd yyyy")).replace("$name",name));
		waitForAndGetElement(ELEMENT_CONTEXT_MENU);
	}
	/**
	 * Delete event/tasks in Month view by right click
	 * @param name
	 * @param date
	 */
	public void deleteEventTaskInMonthView(String name, String date){
		info("Delete event/tasks in Month view by right click");
		rightClickEventTaskInMonth(name, date);
		selectOptionByRightclickOnEvent(contextMenuEditEvenOption.DELETE);
		confirmDeleteEventTask();
		Utils.pause(2000);
	}
	/***
	 * View detail of an event/task in List View
	 * @param name
	 */
	public void viewDetailsEventTaskInList(String name){
		info("Open list View");
		cHome.goToView(selectViewOption.LIST);
		info("Click on the event/task");
		click(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name",name));
		waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_LIST_VIEW.replace("$name",name));
	}
	/**
	 * View large image of Task/Event in List View
	 * @param number
	 *             the number of images as: =1 is first image;=2 is second image;...
	 */
	public void viewLargeImageInList(String number,boolean verify){
		info("Click on image");
		Dimension size_img=waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL.
				replace("$number",number)).getSize();
		mouseOver(ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL_CONTAINER.replace("$number",number),true);
		click(ELEMENT_EVENT_TASK_DETAIL_IMAGE_THUMBNAIL_VIEW.replace("$number",number));
		
		if(verify==true){
			Dimension size_large_img=waitForAndGetElement(ELEMENT_EVENT_TASK_LARGE_IMAGE).getSize();
			if(size_large_img.width>size_img.width && size_large_img.height>size_img.height){
				if(size_large_img.width==170 || size_large_img.height==170)
					assert true;
				else assert false:"Large image is incorrect size";
			}else assert false:"Large image is not shown";
		}
		Utils.pause(2000);
		info("Large image is shown");
	}
	/**
	 * Close large image of task/event in list view
	 * @param verify
	 */
	public void closeViewLargeImageInList(boolean verify){
		info("Click on Close button");
		click(ELMEENT_EVENT_TASK_LARGE_IMAGE_CLOSE);
		if(verify==true){
			Dimension size_large_img=driver.findElement(ELEMENT_EVENT_TASK_LARGE_IMAGE).getSize();
			info("size_large_img.width:"+size_large_img.width);
			info("size_large_img.height:"+size_large_img.height);
			if(size_large_img.width==0 && size_large_img.height==0)
				assert true;
			else assert false:"Large image can not closed";
		}
		Utils.pause(2000);
	}
	/**
	 * Click on large image of task/event in List view to download the image
	 */
	public void downloadImageInList(){
		if(waitForAndGetElement(ELEMENT_EVENT_TASK_LARGE_IMAGE).getSize().width>0){
			info("Click on large image");
			click(ELEMENT_EVENT_TASK_LARGE_IMAGE_DOWNLOAD);
		}
		Utils.pause(2000);
	}
	/**
	 * Open Quick Add Event/Tasks by drag and drop row's time in Day view
	 * @param sourceTimeHour
	 * @param targetTimeHour
	 */
	public void openQuickAddEventTaskInDayView(String sourceTimeHour,String targetTimeHour){
		info("Click on Day view");
		cHome.goToView(selectViewOption.DAY);
		if(sourceTimeHour!=null || sourceTimeHour!=""){
			info("Drag and drop time row");
			dragAndDropToObject(ELEMENT_EVENT_TASK_DATE_TIME_VALUE.replace("$time",sourceTimeHour+":00"),
	        		ELEMENT_EVENT_TASK_DATE_TIME_VALUE.replace("$time",targetTimeHour+":00"));
			waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM,3000,0);
			info("The quick add form is shown");
		}
		
	}
	/**
	 * Open Quick Add Event/Tasks by drag and drop the time in Week view
	 * @param sourceDateTime 
	 *                          has the format:Tue Jun 23 2015 15:30
	 * @param targetDateTime
	 *                          has the format:Tue Jun 23 2015 15:30
	 */
	public void openQuickAddEventTaskInWeekView(String sourceDateTime,String targetDateTime){
		info("Click on Day view");
		cHome.goToView(selectViewOption.WEEK);
		if(sourceDateTime!=null || targetDateTime!=""){
			info("Drag and drop time row");
			dragAndDropToObject(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace("$date",sourceDateTime+":00"),
					ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace("$date",targetDateTime+":00"));
			waitForAndGetElement(ELEMENT_CALENDAR_QUICK_ADD_EVENT_FORM,3000,0);
			info("The quick add form is shown");
		}
		
	}
	
	/**
<<<<<<< HEAD
	 * Check accessibility of share calendar
	 * @param isAccess
	 * 				true if user can view calendar
	 * @param isEdit
	 * 				true if user can edit calendar
	 * @param cal
	 */
	public void checkAccessibilityOfShareCalendar(boolean isAccess,boolean isEdit,String cal){
		if(isAccess){
			waitForAndGetElement(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar",cal));
			openMenuOfCalendar(cal);
			waitForAndGetElement(ELEMENT_CALENDAR_REMOVE_SHARE_CALENDAR);
			waitForAndGetElement(ELEMENT_CALENDAR_REFRESH_MENU);
			if(isEdit){
				waitForAndGetElement(ELEMENT_CALENDAR_EDIT_MENU,DEFAULT_TIMEOUT,1,2);
				waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_MENU);
				waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_MENU);
				waitForAndGetElement(ELEMENT_CALENDAR_ADD_EVENT_MENU,DEFAULT_TIMEOUT,1,2);
				}
		}else{
			waitForElementNotPresent(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar",cal));
		}
	}
	/**
	 * Check accessibility of calendar
	 * @param isAccess
	 * 				true if user can view calendar
	 * @param isEdit
	 * 				true if user can edit calendar
	 * @param cal
	 */
	public void checkAccessibilityOfCalendar(boolean isAccess,boolean isEdit,String cal){
		if(isAccess){
			waitForAndGetElement(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar",cal));
			openMenuOfCalendar(cal);
			waitForAndGetElement(ELEMENT_CALENDAR_REMOVE_MENU);
			waitForAndGetElement(ELEMENT_CALENDAR_REFRESH_MENU);
			if(isEdit){
				waitForAndGetElement(ELEMENT_CALENDAR_EDIT_MENU,DEFAULT_TIMEOUT,1,2);
				waitForAndGetElement(ELEMENT_CALENDAR_EXPORT_MENU);
				waitForAndGetElement(ELEMENT_CALENDAR_IMPORT_MENU);
				waitForAndGetElement(ELEMENT_CALENDAR_ADD_EVENT_MENU,DEFAULT_TIMEOUT,1,2);
				}
		}else{
			waitForElementNotPresent(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar",cal));
		}
	}
	/**
	 * Share calendar to group
	 * @param calendar
	 * @param group
	 * @param membership
	 * @param canEdit
	 */
	public void shareCalendarToGroup(String calendar, String group, String membership,boolean canEdit){
		info("Share calendar to group user");
		executeActionCalendar(calendar, menuOfCalendarOption.SHARE);
		click(ELEMENT_CALENDAR_SELECT_MEMBERSHIP_ICON,0,true);
		pPer.selectGroupMembership(group,membership);
		click(ELEMENT_CALENDAR_SHARE_ADD_BUTTON,0,true);
		if(canEdit){
			check(ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION.replace("${user}", group),2);
		}
		else{
			uncheck(ELEMENT_CALENDAR_SHARE_EDIT_PERMISSION.replace("${user}", group),2);
		}
		click(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_CALENDAR_SHARE_SAVE_BUTTON);
	}
/**
 * Check display of calendar
 * @param cal
 * @param isPresent
 */
	public void checkDisplayOfCalendar(String cal,boolean isPresent){
		if(isPresent)
			waitForAndGetElement(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar",cal));
		else
			waitForElementNotPresent(ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar",cal));
	}

}
