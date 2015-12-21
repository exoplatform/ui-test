package org.exoplatform.selenium.platform.calendar;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CalendarHomePage extends CalendarLocator{

	
	PlatformPermission per;
	ManageAlert alert;
	/**
	 * constructor
	 * @param dr
	 */
	public CalendarHomePage(WebDriver dr){
		this.driver=dr;
		alert = new ManageAlert(dr);
	}

	/**
	 * Category list
	 */
	public enum selectCategoryOption{
		ALL, MEETING, CALL, CLIENT, ANNIVERSARY, HOLIDAY
	}

	/**
	 * View list in calendar
	 */
	public enum selectViewOption{
		DAY, WEEK, LIST, MONTH, WORKWEEK;
	}

	/**
	 * Select an option when creating an Event/Task: ONE DAY or ALL DAY  
	 */
	public enum selectDayOption{
		DETAILTIME, ALLDAY;
	}
	/**
	 * Go to a view from calendar panel
	 * @param view
	 * 				name of view: get value from selectViewOption
	 */
	public void goToView(selectViewOption view){
		info("Go to view "+ view);
		Utils.pause(2000);
		switch(view){
		case DAY:
			click(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Day"));
			waitForAndGetElement(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Day"), DEFAULT_TIMEOUT, 1, 2);
			break;
		case WEEK:
			waitForAndGetElement(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Week"), DEFAULT_TIMEOUT,1);
			click(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Week"));
			waitForAndGetElement(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Week"), DEFAULT_TIMEOUT,1,2);
			break;
		case LIST:
			waitForAndGetElement(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "List"), DEFAULT_TIMEOUT, 1);
			click(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "List"));
			waitForAndGetElement(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "List"), DEFAULT_TIMEOUT, 1,2);
			break;
		case MONTH:
			waitForAndGetElement(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Month"), DEFAULT_TIMEOUT, 1);
			clickByJavascript(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Month"));
			waitForAndGetElement(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Month"), DEFAULT_TIMEOUT,1,2);
			Utils.pause(2000);
			break;
		case WORKWEEK:
			waitForAndGetElement(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Work Week"), DEFAULT_TIMEOUT, 1);
			click(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Work Week"));
			waitForAndGetElement(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Work Week"), 1, 2);
			break;
		default:
			waitForAndGetElement(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Week"), DEFAULT_TIMEOUT, 1);
			click(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Week"));
			waitForAndGetElement(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Week"), DEFAULT_TIMEOUT, 1,2);
			break;
		}
		Utils.pause(500);
	}

	/**
	 * goToRightMenuTaskEventFromDayView
	 * @param name
	 * 				name of event or task
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 */
	public void goToRightMenuTaskEventFromDayView(String name, selectDayOption optionDay){
		info("Got to edit task from day view");
		goToView(selectViewOption.DAY);
		click(ELEMENT_CALENDAR_VIEW_BUTTON.replace("$view", "Day"));
		waitForAndGetElement(ELEMENT_CALENDAR_ACTIVE_VIEW.replace("$view", "Day"));
		switch(optionDay){
		case DETAILTIME:
			scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name)));
			rightClickOnElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
			break;
		case ALLDAY:
			rightClickOnElement(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name));
			break;
		default:
			scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name)));
			rightClickOnElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
			break;
		}
	}

	/**
	 * goToRightMenuTaskEventFromWeekView
	 * @param name
	 * 				name of event or task
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 * @param date
	 * 				date of event: format (MMM dd yyyy)
	 */
	public void goToRightMenuTaskEventFromWeekView(String name, selectDayOption optionDay, String date){
		info("Got to edit task from week view");
		goToView(selectViewOption.WEEK);
		if(date!=null && date!=""){
			switch(optionDay){
			case DETAILTIME:
				scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date)));
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;
			case ALLDAY:
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date", date));
				break;
			default:
				scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date)));
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;	
			}
		}
		else{
			switch(optionDay){
			case DETAILTIME:
				scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name)));
				rightClickOnElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				rightClickOnElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name)));
				rightClickOnElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
		}
	}

	/**
	 * goToRightMenuTaskEventFromMonthView
	 * @param name
	 * 				name of event or task
	 * @param date
	 * 				date of event: format (MMM dd yyyy)
	 */
	public void goToRightMenuTaskEventFromMonthView(String name, String date){
		info("Got to edit task from month view");
		goToView(selectViewOption.MONTH);
		if(date!=null && date!=""){
			if(waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date), 5000,0)==null
					&& waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date), 5000,0)!=null){
				info("Click more button");
				click(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date),2);
				scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE.replace("$name", name).replace("$date", date)));
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE.replace("$name", name).replace("$date", date));
			}
			else{
				scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date)));
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date));
			}
		}
		else{
			scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name)));
			rightClickOnElement(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name));
		}
	}

	/**
	 * goToRightMenuTaskEventFromListView
	 * @param name
	 * 				name of event or task
	 * @param date
	 * 				date of event: format (MMM dd yyyy)
	 */
	public void goToRightMenuTaskEventFromListView(String name, String date){
		info("Got to edit task from list view");
		goToView(selectViewOption.LIST);
		if(date!=null && date!=""){
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
				info("paginator page in calendar list view");
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while((waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name),5000,0)==null)
						&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
					click(ELEMENT_NEXT_PAGE);
				waitForAndGetElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
				rightClickOnElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
			}
			else{
				waitForAndGetElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
				rightClickOnElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
			}			
		}
		else{
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while((waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name),5000,0)==null)
						&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
					click(ELEMENT_NEXT_PAGE);
				waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
				rightClickOnElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
			}
			else{
				waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
				rightClickOnElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
			}	
		}
	}

	/**
	 * goToRightMenuTaskEventFromWorkWeekView
	 * @param name
	 * 				name of event or task
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 * @param date
	 * 				date of event: format (MMM dd yyyy)
	 */
	public void goToRightMenuTaskEventFromWorkWeekView(String name, selectDayOption optionDay, String date){
		info("Got to edit task from work week view");
		goToView(selectViewOption.WORKWEEK);
		if(date!=null && date!=""){
			switch(optionDay){
			case DETAILTIME:
				scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date)));
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;
			case ALLDAY:
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date", date));
				break;
			default:
				scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date)));
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;	
			}
		}
		else{
			switch(optionDay){
			case DETAILTIME:
				scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name)));
				rightClickOnElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				rightClickOnElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				scrollBarToGetElement(By.xpath(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name)));
				rightClickOnElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;

			}
		}
	}
	/**
	 * Open "Edit task" form popup
	 * 
	 * 
	 * @param name
	 * 				name of event or task
	 * @param view
	 * 				view: DAY, WEEK, LIST, MONTH, WORKWEEK;
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 */
	public void goToEditTaskFromPopover(String name, selectViewOption view, selectDayOption optionDay){
		info("Verify task and event is not displayed on calendar panel");
		doubleClickOnElement(getEventTaskElement(name, view, optionDay));
		waitForAndGetElement(ELEMENT_ADD_EDIT_TASK_POPUP);
	}
	
	/**
	 * Open "Edit task" form by right click
	 * 
	 * @param name
	 * 				name of event or task
	 * @param view
	 * 				view: DAY, WEEK, LIST, MONTH, WORKWEEK;
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 * @param date
	 * 				date of event: format (MMM dd yyyy)
	 */
	public void goToEditEventTaskFormByRightClick(String name, selectViewOption view, selectDayOption optionDay, String date){
		info("Open Edit Task Event form");
		switch(view){
		case DAY:
			goToRightMenuTaskEventFromDayView(name,optionDay);
			break;
		case WEEK:
			goToRightMenuTaskEventFromWeekView(name,optionDay,date);
			break;
		case LIST:
			goToRightMenuTaskEventFromListView(name,date);
			break;
		case MONTH:
			goToRightMenuTaskEventFromMonthView(name,date);
			break;
		case WORKWEEK:
			goToRightMenuTaskEventFromWorkWeekView(name,optionDay,date);
			break;
		default:
			goToRightMenuTaskEventFromDayView(name,optionDay);
			break;
		}
		click(ELEMENT_CONTEXT_MENU_EDIT);
		waitForAndGetElement(ELEMENT_ADD_EDIT_TASK_POPUP);
	}

	/**
	 * Verify event or task is not displayed on calendar panel
	 * 
	 * 
	 * @param name
	 * 				name of event or task
	 * @param view
	 * 				view: DAY, WEEK, LIST, MONTH, WORKWEEK;
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 */
	public void verifyIsNotPresentEventTask(String name, selectViewOption view, selectDayOption optionDay){
		info("Verify task and event is not displayed on calendar panel");
		goToView(view);
		switch(view){
		case DAY:
			switch(optionDay){
			case DETAILTIME:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		case WEEK:
			switch(optionDay){
			case DETAILTIME:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
		case LIST:
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,10000,0)!=null){
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while(!(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText()))){
					click(ELEMENT_NEXT_PAGE);
					waitForElementNotPresent(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
				}
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
			}
			else{
				waitForElementNotPresent(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
			}
			break;
		case MONTH:
			waitForElementNotPresent(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name));
			break;
		case WORKWEEK:
			switch(optionDay){
			case DETAILTIME:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		default:
			switch(optionDay){
			case DETAILTIME:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		}
	}

	/**
	 * Verify event or task is displayed on calendar panel
	 * 
	 * 
	 * @param name
	 * 				name of event or task
	 * @param view
	 * 				view: DAY, WEEK, LIST, MONTH, WORKWEEK;
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 */
	public void verifyIsPresentEventTask(String name, selectViewOption view, selectDayOption optionDay){
		info("Verify task and event is not displayed on calendar panel");
		goToView(view);
		switch(view){
		case DAY:
			switch(optionDay){
			case DETAILTIME:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		case WEEK:
			switch(optionDay){
			case DETAILTIME:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		case LIST:
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while((waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name),5000,0)==null)
						&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
					click(ELEMENT_NEXT_PAGE);
				waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
			}
			else{
				waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
			}
			break;
		case MONTH:
			waitForAndGetElement(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name));
			break;
		case WORKWEEK:
			switch(optionDay){
			case DETAILTIME:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		default:
			switch(optionDay){
			case DETAILTIME:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		}
	}
	
	/**
	 * Open "Edit task" form popup
	 * 
	 * 
	 * @param name
	 * 				name of event or task
	 * @param view
	 * 				view: DAY, WEEK, LIST, MONTH, WORKWEEK;
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 */
	public WebElement getEventTaskElement(String name, selectViewOption view, selectDayOption optionDay){
		info("Verify task and event is not displayed on calendar panel");
		WebElement element=null;
		goToView(view);
		switch(view){
		case DAY:
			switch(optionDay){
			case DETAILTIME:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		case WEEK:
			switch(optionDay){
			case DETAILTIME:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		case LIST:
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
				element = waitForAndGetElement(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while((waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name),5000,0)==null)
						&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
					click(ELEMENT_NEXT_PAGE);
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
				element = waitForAndGetElement(ELEMENT_ANY_PAGE.replace("$page", "1"));
			}
			else{
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", name));
			}
			break;
		case MONTH:
			element = waitForAndGetElement(ELEMENT_EVENT_TASK_MONTH_VIEW.replace("$name", name));
			break;
		case WORKWEEK:
			switch(optionDay){
			case DETAILTIME:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		default:
			switch(optionDay){
			case DETAILTIME:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				element = waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		}
		return element;
	}

	/**
	 * verify event exitst or not
	 * @param name
	 * 				name of event or task
	 * @param view
	 * 				view: DAY, WEEK, LIST, MONTH, WORKWEEK;
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 * @param date
	 * 				date of event: format (MMM dd yyyy)
	 */
	public void verifyIsPresentEventTaskWithDateTime(String name, String date, selectViewOption view, selectDayOption optionDay){
		info("Verify task and event is not displayed on calendar panel");
		goToView(view);
		switch(view){
		case DAY:
			switch(optionDay){
			case DETAILTIME:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		case WEEK:
			switch(optionDay){
			case DETAILTIME:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;
			case ALLDAY:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date", date));
				break;
			default:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;
			}
			break;
		case LIST:
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while((waitForAndGetElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date),5000,0)==null)
						&& !(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText())))
					click(ELEMENT_NEXT_PAGE);
				waitForAndGetElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
			}
			else{
				waitForAndGetElement(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
			}
			break;
		case MONTH:
			if(waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date), 5000,0)==null
			&& waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date), 5000,0)!=null){
				click(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date),2);
				waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE.replace("$name", name).replace("$date", date));
			}
			else
				waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date));
			break;
		case WORKWEEK:
			switch(optionDay){
			case DETAILTIME:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;
			case ALLDAY:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date", date));
				break;
			default:
				waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;
			}
			break;
		default:
			switch(optionDay){
			case DETAILTIME:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForAndGetElement(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		}
	}
	
	
	/**
	 * Open Add/Edit task/event by left clicking
	 * @param date
	 *          date of event: format (MMM dd yyyy HH:mm:ss)
	 * @param view
	 *          view: DAY, WEEK, MONTH, WORKWEEK;
	 * @param optionDay
	 *          select ONEDAY or ALLDAY
	 */
	public void openAddEditEventTaskByLeftClick(String date, selectViewOption view, selectDayOption optionDay){
		info("Open Quick Add/EDit task/event by left click");
		goToView(view);
		switch (view) {
		case DAY:
			click(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$date", date));
			break;
		case WEEK:
			switch (optionDay) {
			case DETAILTIME:
				click(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$date", date));
				break;
			case ALLDAY:
				click(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ALL_DAY.replace(
						"$date", date));
				break;
			default:
				click(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$date", date));
				break;
			}
			break;
		case MONTH:
			click(ELEMENT_EVENT_TASK_MONTH_DATE.replace("$date", date));
			break;
		case WORKWEEK:
			switch (optionDay) {
			case DETAILTIME:
				click(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$date", date));
				break;
			case ALLDAY:
				click(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ALL_DAY.replace(
						"$date", date));
				break;
			default:
				click(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$date", date));
				break;
			}
			break;
		default:
			info("You don't select a datetime.Please select a datetime.");
			break;
		}
		Utils.pause(2000);
	
	}
	
	/**
	 * Open Add/Edit task/event by right clicking
	 * @param date
	 *          date of event: format (MMM dd yyyy HH:mm:ss)
	 * @param view
	 *          view: DAY, WEEK, MONTH, WORKWEEK;
	 * @param optionDay
	 *          select ONEDAY or ALLDAY
	 */
	public void openAddEditEventTaskByRightClick(String date, selectViewOption view, selectDayOption optionDay,contextMenuAddEditEvenTaskOption option){
		info("Open Quick Add/EDit task/event by right click");
		goToView(view);
		switch (view) {
		case DAY:
			rightClickOnElement(ELEMENT_EVENT_TASK_DAY_ONE_DAY.replace("$time", date));
			Utils.pause(2000);
			selectOptionByRightclickOnDateTime(option);
			break;
		case WEEK:
			switch (optionDay) {
			case DETAILTIME:
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace(
						"$date", date));
				Utils.pause(2000);
				selectOptionByRightclickOnDateTime(option);
				break;
			case ALLDAY:
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ALL_DAY.replace(
						"$date", date));
				Utils.pause(2000);
				selectOptionByRightclickOnDateTime(option);
				break;
			default:
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace(
						"$date", date));
				Utils.pause(2000);
				selectOptionByRightclickOnDateTime(option);
				break;
			}
			break;
		case MONTH:
			rightClickOnElement(ELEMENT_EVENT_TASK_MONTH_DATE.replace("$date", date));
			Utils.pause(2000);
			selectOptionByRightclickOnDateTime(option);
			break;
		case WORKWEEK:
			switch (optionDay) {
			case DETAILTIME:
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace(
						"$date", date));
				Utils.pause(2000);
				selectOptionByRightclickOnDateTime(option);
				break;
			case ALLDAY:
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ALL_DAY.replace(
						"$date", date));
				Utils.pause(2000);
				selectOptionByRightclickOnDateTime(option);
				break;
			default:
				rightClickOnElement(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_ONE_DAY.replace(
						"$date", date));
				Utils.pause(2000);
				selectOptionByRightclickOnDateTime(option);
				break;
			}
			break;
		default:
			info("You don't select a optionDay.Please select other optionDay.");
			break;
		}
		Utils.pause(2000);
	
	}
	/**
	 * 
	 * right click on a datetime to open Quick ADD/EDIT an event/task 
	 */
	public enum contextMenuAddEditEvenTaskOption{
		ADD_EVENT,ADD_TASK;
	}
	
	/**
	 * Select an option in context menu
	 * @param option
	 */
	public void selectOptionByRightclickOnDateTime(contextMenuAddEditEvenTaskOption option){
		switch(option){
		case ADD_EVENT:
			info("Select Add new event option");
			click(ELEMENT_CONTEXT_MENU_ADD_EVENT);
			break;
		case ADD_TASK:
			info("Select Add new task option");
			click(ELEMENT_CONTEXT_MENU_ADD_TASK);
			break;
		default:
			info("No option to select");
			break;
		}
	}
	/**
	 * verify event is not exitst
	 * @param name
	 * 				name of event or task
	 * @param view
	 * 				view: DAY, WEEK, LIST, MONTH, WORKWEEK;
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 * @param date
	 * 				date of event: format (MMM dd yyyy)
	 */
	public void verifyIsNotPresentEventTaskWithDateTime(String name, String date, selectViewOption view, selectDayOption optionDay){
		info("Verify task and event is not displayed on calendar panel");
		goToView(view);
		switch(view){
		case DAY:
			switch(optionDay){
			case DETAILTIME:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DAY_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		case WEEK:
			switch(optionDay){
			case DETAILTIME:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;
			case ALLDAY:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date", date));
				break;
			default:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;
			}
			break;
		case LIST:
			if(waitForAndGetElement(ELEMENT_TOTAL_PAGE,5000,0)!=null){
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
				while(!(waitForAndGetElement(ELEMENT_TOTAL_PAGE).getText().equals(waitForAndGetElement(ELEMENT_CURRENT_PAGE).getText()))){
					click(ELEMENT_NEXT_PAGE);
					waitForElementNotPresent(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
				}
				click(ELEMENT_ANY_PAGE.replace("$page", "1"));
			}
			else{
				waitForElementNotPresent(ELEMENT_EVENT_TASK_START_DETAIL_DATE_LIST_VIEW.replace("$name", name).replace("$date", date));
			}
			break;
		case MONTH:
			if(waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date), 5000,0)==null
			&& waitForAndGetElement(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date), 5000,0)!=null){
				click(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE_ICON.replace("$date", date),2);
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW_MORE.replace("$name", name).replace("$date", date));
			}else
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", name).replace("$date", date));
			break;
		case WORKWEEK:
			switch(optionDay){
			case DETAILTIME:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;
			case ALLDAY:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name).replace("$date", date));
				break;
			default:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_DATE_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name).replace("$date", date));
				break;
			}
			break;
		default:
			switch(optionDay){
			case DETAILTIME:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			case ALLDAY:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ALL_DAY.replace("$name", name));
				break;
			default:
				waitForElementNotPresent(ELEMENT_EVENT_TASK_WORK_WEEK_VIEW_ONE_DAY.replace("$name", name));
				break;
			}
			break;
		}
	}

	/**
	 * goToRightMenuTaskEventFromAnyView
	 * @param name
	 * 				name of event or task
	 * @param view
	 * 				view: DAY, WEEK, LIST, MONTH, WORKWEEK;
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 * @param date
	 * 				date of event: format (MMM dd yyyy)
	 */
	public void goToRightMenuTaskEventFromAnyView(String name, selectViewOption view, selectDayOption optionDay, String date){
		info("click right menu task/event");
		switch(view){
		case DAY:
			goToRightMenuTaskEventFromDayView(name,optionDay);
			break;
		case WEEK:
			goToRightMenuTaskEventFromWeekView(name,optionDay,date);
			break;
		case LIST:
			goToRightMenuTaskEventFromListView(name,date);
			break;
		case MONTH:
			goToRightMenuTaskEventFromMonthView(name,date);
			break;
		case WORKWEEK:
			goToRightMenuTaskEventFromWorkWeekView(name,optionDay,date);
			break;
		default:
			goToRightMenuTaskEventFromDayView(name,optionDay);
			break;
		}

	}

	/**
	 * Delete an event or task
	 * 
	 * 
	 * @param name
	 * 				name of event or task
	 * @param view
	 * 				view: DAY, WEEK, LIST, MONTH, WORKWEEK;
	 * @param optionDay
	 * 				select ONEDAY or ALLDAY
	 * @param date
	 * 				date of event: format (MMM dd yyyy)
	 * @param opParams
	 * 				opParams[0]: 	false - Don't verify confirm message to delete task/event
	 * 								true - Verify confirm message to delete task/event
	 * 				opParms[1]: 	true - Confirm message of event
	 * 								false - Confirm message of task
	 */
	public void deleteEventTask(String name, selectViewOption view, selectDayOption optionDay, String date, Object... opParams){
		boolean isVerify = (Boolean) (opParams.length > 0 ? opParams[0]: false);
		boolean isEvent = (Boolean) (opParams.length > 1 ? opParams[1]: false);
		info("Delete event/tak: " + name);
		Button button = new Button(driver);
		goToRightMenuTaskEventFromAnyView(name,view,optionDay,date);
		click(ELEMENT_CONTEXT_MENU_DELETE);
		if(isVerify){
			if(isEvent)
				alert.verifyAlertMessage(ELEMENT_CONFIRM_DELETE_EVENT_MSG);
			else
				alert.verifyAlertMessage(ELEMENT_CONFIRM_DELETE_TASK_MSG);
			driver.navigate().refresh();
			Utils.pause(1000);
			verifyIsNotPresentEventTask(name, view, optionDay);
		}
		else
			button.yes();
	}

	/**
	 * select category from list
	 * @param option
	 * 				category type: call, all, client, holiday,anniversary
	 */
	public void selectCategory(selectCategoryOption option){
		info("Select category from list");
		if(option!=null){
			switch(option){
			case ALL:
				select(ELEMENT_CATEGORY_OPTION,"All",2);
				break;
			case ANNIVERSARY:
				select(ELEMENT_CATEGORY_OPTION,"Anniversary",2);
				break;
			case CALL:
				select(ELEMENT_CATEGORY_OPTION,"Calls",2);
				break;
			case CLIENT:
				select(ELEMENT_CATEGORY_OPTION,"Clients",2);
				break;
			case HOLIDAY:
				select(ELEMENT_CATEGORY_OPTION,"Holiday",2);
				break;
			case MEETING:
				select(ELEMENT_CATEGORY_OPTION,"Meeting",2);
				break;
			default:
				break;

			}
		}
	}

	/**
	 * Do quick calendar search
	 * @param keyword
	 * 				keyword which is to input into search box 
	 */
	public void quickSearchCalendar(String keyword){
		info("----Type in quick search box----");
		type(ELEMENT_QUICK_SEARCH_INPUT,keyword,true);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.RETURN).build().perform();
		waitForAndGetElement(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT);
	}

	/** 
	 * Advance search in Calendar
	 * 
	 * @param keyword
	 * 			keyword which is to input into search box 
	 */
	public void advanceSearchCalendar(String keyword){
		info("----Open Advance Search window----");
		waitForAndGetElement(ELEMENT_BUTTON_OPEN_ADVANCE_SEARCH_FORM);
		click(ELEMENT_BUTTON_OPEN_ADVANCE_SEARCH_FORM);
		info("----Input keyword----");
		waitForAndGetElement(ELEMENT_INPUT_TEXT_ADVANCE_SEARCH);
		type(ELEMENT_INPUT_TEXT_ADVANCE_SEARCH,keyword,true);
		click(ELEMENT_BUTTON_SEARCH_ADVANCE_SEARCH);
		info("----Confirm search result displayed----");
		Utils.pause(3000);
		waitForAndGetElement(ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT);
	}
	/**
	 * check on checkboxes of events/tasks that has different name in Month view
	 * @param names
	 */
	public void checkBoxEventTaskInMonthView(String[] names){
		//goToView(selectViewOption.MONTH);
		for(int i=0;i< names.length;i++){
			info("Select the events/tasks:"+names[i]);
			check(ELEMENT_EVENT_TASK_CHECKBOX.replace("$name",names[i]),2);
		}
		Utils.pause(2000);
	}
	/**
	 * check on check boxes of recurring event that have same name in Monthview
	 * @param name
	 *              the name of recurring event
	 * @param number
	 *              the event's number of recurring event as first event =1, second event =2,..
	 *              Example: number="2". This means that second recurring event will be checked
	 * @param opt
	 *              the summary of recurring event instances of the same series that will be checked.
	 *              Example:opt=3. This means that 3 recurring events will be checked
	 */            
	public void checkBoxEventTaskInMonthView(String name,String number, int... opt){
		//goToView(selectViewOption.MONTH);
		if(opt.length>0){
			int event_Start=Integer.parseInt(number); // from the event that will be checked
			int number_event=opt[0]+event_Start;      // sum event's number will be checked
			for(int i=event_Start;i<number_event;i++){
				info("Click on check box of event's number:"+i);
				check(ELEMENT_EVENT_TASK_NUMBER_RECURRING_MONTH_VIEW_CHECKBOX.replace("$name",name).
						replace("$number",Integer.toString(i)),2);
			}
		}else{
			info("Click on check box of event's number:"+number);
			check(ELEMENT_EVENT_TASK_NUMBER_RECURRING_MONTH_VIEW_CHECKBOX.replace("$name",name).
				replace("$number",number),2);
		}
		Utils.pause(2000);
	}
	/**
	 * Check box on Event/task by date of Month and Week view
	 * @param name
	 * @param date
	 */
	public void checkBoxEventTaskInMonthView(String name,String date){
		info("Select the event/task");
		check(ELEMENT_EVENT_TASK_NUMBER_RECURRING_MONTH_VIEW_CHECKBOX_DATE.replace("$date",date).replace("$name",name),2);
		Utils.pause(2000);
	}
	/**
	 * Click on Next arrow of header panel to jump to next days/weeks/months
	 * @param number
	 */
	public void nextDate(int number){
		if(number!=0){
			info("Jump to next:"+number+" days/weeks/months");
			for(int i=0;i<number;i++){
				info("Click on Next arrow");
				click(ELEMENT_NEXT_BUTTON_ANY_VIEW);
				Utils.pause(2000);
			}
		}
	}
	/**
	 * Click on Next arrow to jump to next month in Calendar mini
	 * @param number
	 */
    public void nextMonth(int number){
    	if(number!=0){
			info("Jump to next:"+number+" months");
			for(int i=0;i<number;i++){
				info("Click on Next arrow");
				click(ELEMENT_CALENDAR_MINI_NEXT_MONTH);
				Utils.pause(2000);
			}
		}
	}

	/**
	 * Click on Previous arrow of header panel to back previous days/weeks/months
	 * @param number
	 */
	public void previousDate(int number){
		if(number!=0){
			info("Jump to previous:"+number+" days/weeks/months");
			for(int i=0;i<number;i++){
				info("Click on Previous arrow");
				click(ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);
				Utils.pause(2000);
			}
		}
	}
	/**
	 * Click on previous arrow to jump to previous month in Calendar mini
	 * @param number
	 */
	public void previousMonth(int number){
		if(number!=0){
			info("Jump to previous:"+number+" months");
			for(int i=0;i<number;i++){
				info("Click on Previous arrow");
				click(ELEMENT_CALENDAR_MINI_PREVIOUS_MONTH);
				Utils.pause(2000);
			}
		}
	}
	/**
	 * Show/Hide left panel
	 */
	public void showHideLefPanel(){
		info("Click on the show/hide button");
		if(waitForAndGetElement(ELEMENT_CALENDAR_MINI,2000,0)!=null){
			click(ELEMENT_SHOW_HIDE_LEFT_PANEL);
			waitForElementNotPresent(ELEMENT_CALENDAR_MINI);
			info("The left panel is hidded");
		}else{
			click(ELEMENT_SHOW_HIDE_LEFT_PANEL);
			waitForAndGetElement(ELEMENT_CALENDAR_MINI);
			info("The left panel is shown");
		}
	}
}