package org.exoplatform.selenium.platform.cs;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ks.ForumBase.ELEMENT_DELETE;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/**
 * <li>Common functions for Calendar/Event</li>
 * @author vuna2
 * @Date: Tuesday, 8 January, 2013
 */
public class Event extends Task{
	
	//-----------------------------Add event form-------------------------------------------------
	public static By ELEMENT_QUICK_ADD_EVENT_POPUP = By.xpath("//*[@id='UIQuckAddEventPopupWindow']//span[text()='Quick Add Event']");
	public static By ELEMENT_INPUT_EVENT_DESCRIPTION = By.id("description");
	public static By ELEMENT_SELECT_TYPE_CALENDAR = By.xpath("//*[@id='UIQuickAddEvent']//select[@name='calendar']");
	public static By ELEMENT_TYPE_EVENT_CATEGORY = By.xpath("//*[@id='UIQuickAddEvent']//select[@id='category']");
	public static By ELEMENT_ALLDAY_CHECKBOX = By.id("allDay");
	public static By ELEMENT_TIME_FROM_CHECKBOX = By.xpath("//*[@id='UIQuickAddEvent']//input[@format='MM/dd/yyyy' and @name='from']");
	public static By ELEMENT_TIME_TO_CHECKBOX = By.xpath("//*[@id='UIQuickAddEvent']//input[@format='MM/dd/yyyy' and @name='to']");
	public static String ELEMENT_SET_TIME_DATE = "//a[contains(@href,'eXo.cs.UIDateTimePicker.setDate') and contains(text(),'${date}')]";
	public static By ELEMENT_SET_TIME_FROM_HOUR_CHECKBOX = By.xpath("//*[@id='UIQuickAddEvent']//input[@class='UIComboboxInput']");
	public static By ELEMENT_SET_TIME_TO_HOUR_CHECKBOX = By.xpath("//*[@id='toTime']/..//input[@class='UIComboboxInput']");
	public static String ELEMENT_INPUT_SET_TIME_FROM_HOUR = "//*[@id='UIQuickAddEvent']//a[@class='UIComboboxItem' and @value='${hour}']";
	public static String ELEMENT_INPUT_SET_TIME_TO_HOUR = "//*[@id='toTime']/../div//a[@class='UIComboboxItem' and @value='${hour}']";
	public static String ELEMENT_EVENT = "//*[@class='busyIcon']/../..//*[@class='EventContainer' and contains(text(),'${eventName}')]";
	public static String ELEMENT_EVENT_HIGH_PRIORITY = "//*[@class='busyIcon']/*[@class='highPriorityIcon']/../../..//*[@class='EventContainer' and contains(text(),'${eventName}')]";
	public static String ELEMENT_EVENT_NORMAL_PRIORITY = "//*[@class='busyIcon']/*[@class='normalPriorityIcon']/../../..//*[@class='EventContainer' and contains(text(),'${eventName}')]";
	public static String ELEMENT_EVENT_LOW_PRIORITY = "//*[@class='busyIcon']/*[@class='lowPriorityIcon']/../../..//*[@class='EventContainer' and contains(text(),'${eventName}')]";
	public static By ELEMENT_EVENT_MORE_DETAIL_BUTTON = By.xpath("//*[@id='UIQuckAddEventPopupWindow']//a[text() =  'More Details']");
	
	//-----------------------------Add detail event form-------------------------------------------
	public static By ELEMENT_ADD_DETAIL_EVENT_FORM = By.xpath("//*[@id='UICalendarPopupWindow']//span[text()='Add/Edit Event']");
	//----Detail tab-------------
	public static By ELEMENT_ADD_DETAIL_EVENT_DETAIL_TAB = By.xpath("//*[@id='UIPopupAddEventContainer']//*[@class='MiddleTab' and text()='Detail']");
	public static By ELEMENT_EVENT_LOCATION = By.id("place");
	public static By ELEMENT_EVENT_PRIORITY = By.id("priority");
	public static By ELEMENT_REPEAT_CHECKBOX = By.id("isRepeat");
	public static By ELEMENT_ATTACH_FILE_ICON = By.xpath(" //*[@id='eventDetail']//div[@class='AddAttachmentIcon']//img[@class='AddNewNodeIcon']");
	
	public static By ELEMENT_INPUT_EVENT_SUMMARY_DETAILS = By.xpath("//*[@id='eventDetail']//*[@id='eventName']");
	public static By ELEMENT_INPUT_EVENT_DESCRIPTION_DETAILS = By.xpath("//*[@id='eventDetail']//*[@id='description']");
	public static By ELEMENT_TIME_FROM_CHECKBOX_DETAILS = By.xpath("//*[@id='eventDetail']//input[@format='MM/dd/yyyy' and @name='from']");
	public static By ELEMENT_TIME_TO_CHECKBOX_DETAILS = By.xpath("//*[@id='eventDetail']//input[@format='MM/dd/yyyy' and @name='to']");
	public static String ELEMENT_SET_TIME_DATE_DETAILS = "//*[@id='eventDetail']" + ELEMENT_SET_TIME_DATE;
	public static By ELEMENT_SET_TIME_FROM_HOUR_CHECKBOX_DETAILS = By.xpath("//*[@id='eventDetail']//input[@class='UIComboboxInput']");
	public static By ELEMENT_SET_TIME_TO_HOUR_CHECKBOX_DETAILS = By.xpath("//*[@id='eventDetail']//*[@id='toTime']/..//input[@class='UIComboboxInput']");
	public static String ELEMENT_INPUT_SET_TIME_FROM_HOUR_DETAILS = "//*[@id='eventDetail']//a[@class='UIComboboxItem' and @value='${hour}']";
	public static String ELEMENT_INPUT_SET_TIME_TO_HOUR_DETAILS = "//*[@id='eventDetail']" + ELEMENT_INPUT_SET_TIME_TO_HOUR;
	public static By ELEMENT_ALLDAY_CHECKBOX_DETAILS = By.xpath("//*[@id='eventDetail']//*[@id='allDay']");
	public static By ELEMENT_SELECT_TYPE_CALENDAR_DETAILS = By.xpath("//*[@id='eventDetail']//select[@name='calendar']");
	public static By ELEMENT_TYPE_EVENT_CATEGORY_DETAILS = By.xpath("//*[@id='eventDetail']//select[@id='category']");
	public static String MSG_ADD_EVENT_FROM_GREATER_TO = "The To date must be greater than the From date.";
	public static String MSG_ADD_EVENT_ATTACHMENT_GREATER_10MB = "The total size of files must be less than 10MB.";
	public static String MSG_ADD_EVENT_ATTACHMENT_NO_FILE = "Please upload the selected file by using the upload icon.";
	public static String ELEMENT_ADD_EVENT_FILE_ATTACHMENT = "//*[@id='eventDetail']//a[contains(@title,'${fileName}')]";
	public static String ELEMENT_ADD_EVENT_FILE_REMOVE_ICON = "//*[@id='eventDetail']//a[contains(@title,'${fileName}')]/..//a[@title='Remove']";
	
	//Repeating event
	public static By ELEMENT_REPEATING_EVENT = By.id("repeatType");
	public static By ELEMENT_INTERVAL_REPEATING_EVENT = By.id("interval"); 
	public static By ELEMENT_REPEAT_TYPE_NEVER = By.id("endNever");
	public static By ELEMENT_REPEAT_TYPE_AFTER= By.id("endAfter");
	public static By ELEMENT_REPEAT_TYPE_AFTER_OCCURRENCES = By.id("endAfterNumber");
	public static By ELEMENT_REPEAT_TYPE_DATE = By.id("endByDate");	
	public static By ELEMENT_REPEAT_INPUT_TYPE_DATE = By.xpath("//*[@id='endDate']/input[@format='MM/dd/yyyy']");
	//More details --> Attachments
	public static By ELEMENT_CHOOSE_FILE_LINK = By.id("file");
	public static By ELEMENT_CALENDAR_POPUP_WINDOW = By.xpath("//*[@id='UICalendarChildPopupWindow']/div[2]/div");
	
	//-------------Reminder tab--------
	public static By ELEMENT_ADD_DETAIL_EVENT_REMINDER_TAB = By.xpath("//*[@id='UIPopupAddEventContainer']//*[@class='MiddleTab' and text()='Reminders']");
	public static By ELEMENT_REMIND_BY_MAIL_CHECKBOX = By.id("mailReminder");
	public static By ELEMENT_EMAIL_REPEAT = By.id("emailIsRepeat");
	public static By ELEMENT_POPUP_EMAIL_REPEAT = By.id("popupIsRepeat");
	public static By ELEMENT_MAIL_REMINDER_TIME = By.id("mailReminderTime");
	public static By ELEMENT_EMAIL_REPEAT_INTERVAL = By.id("emailRepeatInterval");
	public static By ELEMENT_ADD_MORE_PEOPLE_BUTTON = By.xpath("//*[@id='eventReminder']//img[@class='AddNewNodeIcon']");
	public static By ELEMENT_INPUT_KEYWORD_NAME = By.id("keyWord");
	public static By ELEMENT_SEARCH_ICON_REMINDERS_TAB = By.xpath("//*[@id='UIAddressForm']//a[@class='SearchIcon']");
	public static String ELEMENT_USER_CHECKBOX = "//input[@class='checkbox' and @id='${username}']";
	public static By ELEMENT_POPUP_NOTIFICATION = By.id("popupReminder");
	public static By ELEMENT_POPUP_REMINDER_TIME = By.id("popupReminderTime");
	public static By ELEMENT_REPEAT_POPUP_INTERVAL = By.id("popupRepeatInterval");
	
	//----------More details --> Participants tab--------
	public static By ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB = By.xpath("//*[@id='UIPopupAddEventContainer']//*[@class='MiddleTab' and text()='Participants']");
	public static By ELEMENT_SHARED_EVENT_PUBLIC = By.id("shareEvent_public");
	public static By ELEMENT_SHARED_EVENT_PRIVATE = By.id("shareEvent_private");
	public static By ELEMENT_AVAILABLE_STATUS_BUSY = By.id("status_busy");
	public static By ELEMENT_AVAILABLE_STATUS_AVAILABLE = By.id("status_available");
	public static By ELEMENT_AVAILABLE_STATUS_OUTSIDE = By.id("status_outside");
	public static By ELEMENT_INVITATION_TYPE_NEVER = By.id("send_never");
	public static By ELEMENT_INVITATION_TYPE_ALWAYS = By.id("send_always");
	public static By ELEMENT_INVITATION_TYPE_ASK = By.id("send_ask");
	public static By ELEMENT_ADD_MORE_PARTICIPANT_ICON = By.xpath("//*[@id='eventShare']//img[@class='AddNewNodeIcon']");
	public static By ELEMENT_ADD_CONTACT_PARTICIPANT_ICON = By.xpath("//*[@id='UIInvitationForm']//img[@class='AddContactParticipantIcon']");
	public static By ELEMENT_INPUT_INVITATION_MESSAGE = By.id("invitation-msg");
	public static By ELEMENT_INPUT_PARTICIPANT_TEXTAREA = By.id("participant");
	public static String ELEMENT_PARTICIPANT_IN_TEXTAREA = "//*[@id='participant' and contains(text(), '${user}')]";
	public static String ELEMENT_PARTICIPANT_IN_LIST = "//*[@id='UIParticipantList']//*[@title='${user}']";
	public static String ELEMENT_PARTICIPANT_REMOVE_ICON = "//*[@title='${user}']/../..//img[@class='DeleteIcon']";
	public static By ELEMENT_SELECT_USER_PARTICIPANT = By.xpath("//*[@id='UIInvitationForm']//img[@class='SelectUserIcon']");
	public static String MSG_PARTICIPANT_INVALID = "'${user}; ' is not a valid participant.";
	
	//------More details --> Schedule tab---------------
	public static By ELEMENT_ADD_DETAIL_EVENT_SCHEDULE_TAB = By.xpath("//*[@id='UIPopupAddEventContainer']//*[@class='MiddleTab' and text()='Schedule']");
	public static By ELEMENT_SELECTED_DATE_CHECKBOX = By.id("checkTime"); 
	public static By ELEMENT_SCHEDULE_ALLDAY_CHECBOX = By.id("dateAll");
	public static By ELEMENT_SCHEDULE_FROM_TIME_BOX = By.xpath("//*[@id='timeFrom']/..//input[@class='UIComboboxInput']");
	public static String ELEMENT_SCHEDULE_INPUT_FROM_TIME_BOX = "//*[@id='timeFrom']/..//div[@class='UIComboboxItemContainer']/a[@value='${hour}']";
	public static By ELEMENT_SCHEDULE_TO_TIME_BOX = By.xpath("//*[@id='timeTo']/..//input[@class='UIComboboxInput']");
	public static String ELEMENT_SCHEDULE_INPUT_TO_TIME_BOX = "//*[@id='timeTo']/..//div[@class='UIComboboxItemContainer']/a[@value='${hour}']";
	public static By ELEMENT_SCHEDULE_ADD_USER_ICON = By.xpath("//*[@id='RowContainerDay']//div[@class='AddUserIcon' and @title='Add Attendee']");
	public static By ELEMENT_SCHEDULE_INPUT_USERNAME = By.id("QuickSearch");
	public static By ELEMENT_SCHEDULE_GROUP_SEARCH_ICON = By.xpath("//a[@class='SearchIcon' and @title='Group:']");
	public static By ELEMENT_SCHEDULE_USER_SEARCH_ICON = By.xpath("//a[@class='SearchIcon' and @title='Quick Search']");

	//Left panel/Calendars
	public static String ELEMENT_PERSONAL_CALENDARS = "//*[contains(text(),'Personal Calendars')]/../..//a[@title='${titleCalendar}']";
	public static String ELEMENT_CALENDARS_SETTINGS =	ELEMENT_PERSONAL_CALENDARS + "/../..//div[@class='IconHoverSetting']";
	public static By ELEMENT_ADD_EVENT_LINK = By.xpath(" //*[@id='tmpMenuElement']//a[@class='ItemIcon AddNewEvent']");

	//----------------common function for event-------------------------------
	/**
	 * function select user when add event
	 * @param userInfo: userInfo[0] ->  user first name (Mary, Jack, etc...) ; 
	 * userInfo[1] -> username ("mary", "james", "demo", etc...) 
	 */
	public static void selectUserForEvent(String[] userInfo){
		info("-- Selecting an user: " + userInfo[1]);
		type(ELEMENT_INPUT_KEYWORD_NAME, userInfo[0], true);
		click(ELEMENT_SEARCH_ICON_REMINDERS_TAB);
		click(ELEMENT_USER_CHECKBOX.replace("${username}", userInfo[1]));
		pause(1000);
	}

	/**
	 * 
	 * @param remindByMail: boolean
	 * @param reminderTime: reminder time (eg "5 minutes")
	 * @param remindRepeat: boolean
	 * @param reminderRepeatTime: reminder repeat time
	 * @param addMorePeople: boolean
	 * @param userInfo: 
	 * userInfo[0] ->  user first name (Mary, Jack, etc...) ; 
	 * userInfo[1] -> username ("mary", "james", "demo", etc...)
	 * @param notificationPopUp: optional
	 */
	public static void eventReminders(boolean remindByMail, String reminderTime, 
			boolean remindRepeat, String reminderRepeatTime,
			boolean addMorePeople, String[] userInfo, Object... notificationPopUp){
		boolean showNotification = false;
		boolean repeatPopUpNotification = false;
		String[] popupReminderTime = null;

		if (notificationPopUp.length > 0){
			if (!(notificationPopUp[0] instanceof Boolean)) { 
				throw new IllegalArgumentException("-- Argument should be a boolean --");
			}
			showNotification = (Boolean)notificationPopUp[0];
		}
		if (notificationPopUp.length > 1){
			if (!(notificationPopUp[1] instanceof Boolean)) { 
				throw new IllegalArgumentException("-- Argument should be a boolean --");
			}
			repeatPopUpNotification = (Boolean)notificationPopUp[1];
		}
		if (notificationPopUp.length > 2){
			if (!(notificationPopUp[2] instanceof String[])) { 
				throw new IllegalArgumentException("-- Argument should be an array (String) --");
			}
			popupReminderTime = (String[])notificationPopUp[2];
		}	

		info("-- Adding a reminder --");

		if (isElementNotPresent(ELEMENT_REMIND_BY_MAIL_CHECKBOX)){
			click(ELEMENT_REMINDERS_TAB);
		}
		//waitForTextPresent("Remind by Email");
		WebElement element = waitForAndGetElement(ELEMENT_REMIND_BY_MAIL_CHECKBOX);
		String status = element.getAttribute("value");
		if (remindByMail){
			if (!(status.equalsIgnoreCase("true"))) check(ELEMENT_REMIND_BY_MAIL_CHECKBOX); 
		}
		select(ELEMENT_MAIL_REMINDER_TIME, reminderTime);
		if (remindRepeat){
			check(ELEMENT_EMAIL_REPEAT);
			select(ELEMENT_EMAIL_REPEAT_INTERVAL,reminderRepeatTime);
		}
		if (addMorePeople){
			click(ELEMENT_ADD_MORE_PEOPLE_BUTTON);
			selectUserForEvent(userInfo);
			click(ELEMENT_ADD_BUTTON);
		}
		pause(1000);
		if (showNotification){
			check(ELEMENT_POPUP_NOTIFICATION);
			select(ELEMENT_POPUP_REMINDER_TIME, popupReminderTime[0]);
			if (repeatPopUpNotification){
				check(ELEMENT_POPUP_EMAIL_REPEAT);
				select(ELEMENT_REPEAT_POPUP_INTERVAL, popupReminderTime[1]);
			}		
		}  
		//save();
	}

	/**
	 * 
	 * @param privacy: privacy type for participant (PRIVATE or PUBLIC)
	 * @param available: types available (BUSY, AVAILABLE, OUTSIDE)
	 * @param invite: type of invite (NEVER, ALWAYS, ASK)
	 * @param participants: optional
	 */
	public static void eventParticipants(participantPrivacy privacy, participantAvailable available, 
			participantInviteType invite, Object... participants){

		boolean addMorePeople = false;
		String[] userInfo = null;
		String inviteMessage = "";
		if (participants.length > 0){
			if (!(participants[0] instanceof Boolean)) { 
				throw new IllegalArgumentException("-- Argument should be a boolean --");
			}
			addMorePeople = (Boolean)participants[0];
		}
		if (participants.length > 1){
			if (!(participants[1] instanceof String[])) { 
				throw new IllegalArgumentException("-- Argument should be an array (String) --");
			}
			userInfo = (String[])participants[1];
		}
		if (participants.length > 2){
			if (!(participants[2] instanceof String)) { 
				throw new IllegalArgumentException("-- Argument should be a String --");
			}
			inviteMessage = (String)participants[2];
		}	
		info("-- Adding a participant --");
		if (isElementNotPresent(ELEMENT_SHARED_EVENT_PRIVATE)){
			click(ELEMENT_PARTICIPANTS_TAB);
		}
		//Set a privacy type for participant
		switch (privacy) {
		case PRIVATE:
			check(ELEMENT_SHARED_EVENT_PRIVATE);		
			break;
		case PUBLIC:
			check(ELEMENT_SHARED_EVENT_PUBLIC);
			break;	
		default:
			break;
		}
		//Set types available	
		switch (available) {
		case BUSY:
			check(ELEMENT_AVAILABLE_STATUS_BUSY);
			break;
		case AVAILABLE:
			check(ELEMENT_AVAILABLE_STATUS_AVAILABLE);
			break;
		case OUTSIDE:
			check(ELEMENT_AVAILABLE_STATUS_OUTSIDE);
			break;
		default:
			break;
		}
		//Set a type of invite  
		switch (invite) {
		case NEVER:
			check(ELEMENT_INVITATION_TYPE_NEVER);
			break;
		case ALWAYS:
			check(ELEMENT_INVITATION_TYPE_ALWAYS);
			break;
		case ASK:
			check(ELEMENT_INVITATION_TYPE_ASK);
			break;
		default:
			break;
		}		
		if (addMorePeople){
			click(ELEMENT_ADD_MORE_PARTICIPANT_ICON);
			click(ELEMENT_ADD_CONTACT_PARTICIPANT_ICON);
			selectUserForEvent(userInfo);
			click(ELEMENT_ADD_BUTTON);
			type(ELEMENT_INPUT_INVITATION_MESSAGE, inviteMessage, true);
			save();
		}
		pause(1000);
	}

	//privacy type for participant
	public static enum participantPrivacy{
		PRIVATE, PUBLIC;
	}

	//types available
	public static enum participantAvailable{
		BUSY, AVAILABLE, OUTSIDE;
	}

	//type of invite 
	public static enum participantInviteType{
		NEVER, ALWAYS, ASK;
	}

	/**
	 * 
	 * @param applySelectedDate: boolean
	 * @param time: time[0] -> from time; time[1] -> to time 
	 * @param allDay: boolean
	 * @param participants: optional
	 */
	public static void eventSchedule(boolean applySelectedDate, String[] time, boolean allDay, Object... participants){
		boolean addMorePeople = false;
		String username = "";
		if (participants.length > 0){
			if (!(participants[0] instanceof Boolean)) { 
				throw new IllegalArgumentException("-- Argument should be a boolean --");
			}
			addMorePeople = (Boolean)participants[0];
		}
		if (participants.length > 1){
			if (!(participants[1] instanceof String)) { 
				throw new IllegalArgumentException("-- Argument should be a String --");
			}
			username = (String)participants[1];
		}	
		info("-- Adding a schedule --");
		if (applySelectedDate){
			check(ELEMENT_SELECTED_DATE_CHECKBOX);
			click(ELEMENT_SCHEDULE_FROM_TIME_BOX);
			click(ELEMENT_SCHEDULE_INPUT_FROM_TIME_BOX.replace("${hour}", time[0]));
			click(ELEMENT_SCHEDULE_TO_TIME_BOX);
			click(ELEMENT_SCHEDULE_INPUT_TO_TIME_BOX.replace("${hour}", time[1]));	
		}
		if (allDay){
			check(ELEMENT_SCHEDULE_ALLDAY_CHECBOX);		
		}
		if (addMorePeople){
			click(ELEMENT_SCHEDULE_ADD_USER_ICON);
			type(ELEMENT_SCHEDULE_INPUT_USERNAME, username, true);
			click(ELEMENT_SCHEDULE_USER_SEARCH_ICON);
			click(ELEMENT_USER_CHECKBOX.replace("${username}", username));
			click(ELEMENT_ADD_BUTTON);
			//waitForElementPresent(By.xpath("//div[contains(text(), '"+ username +"')]"));
			pause(1000);
		}		
	}
	
	/**function input data in quick and event form
	 * @author lientm
	 * @param name: summary of event
	 * @param desc: description of event
	 * @param allDay: = true: full day
	 *                = false: not full day
	 * @param from: format mm/dd/yyyy hh:mm
	 * @param to: format mm/dd/yyyy hh:mm
	 * @param opt[0]: calendar name
	 * 		  opt[1]: category name
	 */
	public static void inputDataQuickAddEventForm(String name, String desc, String from, String to, boolean allDay, String...opt){
		if (name != null){
			type(ELEMENT_INPUT_EVENT_SUMMARY, name, true);
		}
		if (desc != null){
			type(ELEMENT_INPUT_EVENT_DESCRIPTION, desc, true);
		}
		WebElement day = waitForAndGetElement(ELEMENT_ALLDAY_CHECKBOX);
		if (allDay && !day.isSelected()){
			check(ELEMENT_ALLDAY_CHECKBOX);
		} else if (!allDay && day.isSelected()){
			uncheck(ELEMENT_ALLDAY_CHECKBOX);
		}
		if (from != null){
			if (from != ""){
				if (allDay){
					type(ELEMENT_TIME_FROM_CHECKBOX, from, true);
				}else {
					String[] dateTime = from.split(" ");
					type(ELEMENT_TIME_FROM_CHECKBOX, dateTime[0], true);
					waitForAndGetElement(ELEMENT_SET_TIME_FROM_HOUR_CHECKBOX).sendKeys(Keys.LEFT_CONTROL + "a");
					type(ELEMENT_SET_TIME_FROM_HOUR_CHECKBOX, dateTime[1], false);
				}
			}
		}
		if (to != null){
			if (to != ""){
				if (allDay){
					type(ELEMENT_TIME_TO_CHECKBOX, to, true);
				}else{
					String[] dateTime = to.split(" ");
					type(ELEMENT_TIME_TO_CHECKBOX, dateTime[0], true);
					waitForAndGetElement(ELEMENT_SET_TIME_TO_HOUR_CHECKBOX).sendKeys(Keys.LEFT_CONTROL + "a");
					type(ELEMENT_SET_TIME_TO_HOUR_CHECKBOX, dateTime[1], false);
				}
			}
		}
		if (opt.length > 0 && opt[0] != null){
			select(ELEMENT_SELECT_TYPE_CALENDAR, opt[0]);
		}
		if (opt.length > 1 && opt[1] != null){
			select(ELEMENT_TYPE_EVENT_CATEGORY, opt[1]);
		}
		save();
	}
	
	/**function and event form
	 * @author lientm
	 * @param name
	 * @param desc
	 * @param from
	 * @param to
	 * @param allDay
	 * @param opt
	 */
	public static void addEvent(String name, String desc, String from, String to, boolean allDay, String...opt){
		info("Add new event");
		goToEvent();
		inputDataQuickAddEventForm(name, desc, from, to, allDay, opt);
		if (opt.length < 3){
			waitForElementNotPresent(ELEMENT_QUICK_ADD_EVENT_POPUP);
			waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT.replace("${eventName}", name)));
		}
	}
	
	/**function quick add event with summary and description
	 * @author lientm
	 * @param name
	 * @param desc
	 * @param verify
	 */
	public static void quickAddEvent(String name, String desc, String calendar, boolean...verify){
		boolean check = true;
		if (verify.length > 0){
			check = verify[0];
		}
		if (check){
			addEvent(name, desc, null, null, false, calendar);
		}else {
			addEvent(name, desc, null, null, false, calendar, null, "");
		}
	}
	
	/**function input data in detail tab when add event detail
	 * @author lientm
	 * @param summary
	 * @param desc
	 * @param location
	 * @param allDay
	 * @param from
	 * @param to
	 * @param repeat
	 * @param typeRepeat
	 * @param occurrencesAndDate
	 * @param opt
	 * @param file
	 */
	public static void inputDataDetailEvent_DetailTab(String name, String desc, String location, boolean allDay, String from, String to,
			            boolean repeat, String[] typeRepeat, String[] occurrencesAndDate, String[] opt, String... file){
		if (name != null){
			type(ELEMENT_INPUT_EVENT_SUMMARY_DETAILS, name, true);
		}
		if (desc != null){
			type(ELEMENT_INPUT_EVENT_DESCRIPTION_DETAILS, desc, true);
		}
		if (location != null){
			type(ELEMENT_EVENT_LOCATION, location, true);
		}
		WebElement day = waitForAndGetElement(ELEMENT_ALLDAY_CHECKBOX_DETAILS);
		if (allDay && !day.isSelected()){
			check(ELEMENT_ALLDAY_CHECKBOX_DETAILS);
		} else if (!allDay && day.isSelected()){
			uncheck(ELEMENT_ALLDAY_CHECKBOX_DETAILS);
		}
		if (from != null){
			if (from != ""){
				if (allDay){
					type(ELEMENT_TIME_FROM_CHECKBOX_DETAILS, from, true);
				}else {
					String[] dateTime = from.split(" ");
					type(ELEMENT_TIME_FROM_CHECKBOX_DETAILS, dateTime[0], true);
					waitForAndGetElement(ELEMENT_SET_TIME_FROM_HOUR_CHECKBOX_DETAILS).sendKeys(Keys.LEFT_CONTROL + "a");
					type(ELEMENT_SET_TIME_FROM_HOUR_CHECKBOX_DETAILS, dateTime[1], false);
				}
			}
		}
		if (to != null){
			if (to != ""){
				if (allDay){
					type(ELEMENT_TIME_TO_CHECKBOX_DETAILS, to, true);
				}else{
					String[] dateTime = to.split(" ");
					type(ELEMENT_TIME_TO_CHECKBOX_DETAILS, dateTime[0], true);
					waitForAndGetElement(ELEMENT_SET_TIME_TO_HOUR_CHECKBOX_DETAILS).sendKeys(Keys.LEFT_CONTROL + "a");
					type(ELEMENT_SET_TIME_TO_HOUR_CHECKBOX_DETAILS, dateTime[1], false);
				}
			}
		}
		WebElement repeat_checkbox = waitForAndGetElement(ELEMENT_REPEAT_CHECKBOX);
		if (repeat && !repeat_checkbox.isSelected()){
			check(ELEMENT_REPEAT_CHECKBOX);
			repeatingEvent(typeRepeat, occurrencesAndDate);
		} else if (!allDay && day.isSelected()){
			uncheck(ELEMENT_REPEAT_CHECKBOX);
		}
		if (opt[0] != null && opt[0] != ""){
			select(ELEMENT_EVENT_PRIORITY, opt[0]);
		}
		if (opt[1] != null && opt[1] != ""){
			select(ELEMENT_SELECT_TYPE_CALENDAR_DETAILS, opt[1]);
		}
		if (opt[2] != null && opt[2] != ""){
			select(ELEMENT_TYPE_EVENT_CATEGORY_DETAILS, opt[2]);
		}
		attachSomeFiles(file);
	}

	/**function quick add a Detail Event
	 * @author lientm
	 * @param summary
	 * @param desc
	 * @param calendar
	 */
	public static void quickAddDetailEvent(String name, String desc, String calendar, boolean...verify){
		String[] typeRepeat = {};
		String[] occurrencesAndDate = {};
		String[] opt = {"", calendar, ""};
		boolean check = verify.length > 0 ? verify[0] : true;
		
		info("Quick add detail event with name, description, calendar");
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_FORM);
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_DETAIL_TAB);
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_REMINDER_TAB);
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB);
		waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_SCHEDULE_TAB);
		inputDataDetailEvent_DetailTab(name, desc, null, false, null, null, false, typeRepeat, occurrencesAndDate, opt);
		save();
		save();
		if (check){
			waitForElementNotPresent(ELEMENT_ADD_DETAIL_EVENT_FORM);
			waitForElementPresentNotDisplay(By.xpath(ELEMENT_EVENT.replace("${eventName}", name)));
			info("Quick add detail event successfully");
		}		
	}

	public static void addEventSetParticipant(String eventName, String calendarName, String participant, int privacy, int available, int invitation, boolean ...notAsk){
		boolean not_ask = notAsk.length > 0 ? notAsk[0] : true;
		boolean send = notAsk.length > 1 ? notAsk[1] : true;
		
		goToEvent();
		info("Quick add event with set participants");
		click(ELEMENT_EVENT_MORE_DETAIL_BUTTON);		
		type(ELEMENT_INPUT_EVENT_SUMMARY_DETAILS, eventName, true);
		select(ELEMENT_SELECT_TYPE_CALENDAR_DETAILS, calendarName);
		click(ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB);
		switch (privacy) {
		case 1:
			check(ELEMENT_SHARED_EVENT_PRIVATE);		
			break;
		case 2:
			check(ELEMENT_SHARED_EVENT_PUBLIC);
			break;	
		default:
			break;
		}
		switch (available) {
		case 1:
			check(ELEMENT_AVAILABLE_STATUS_BUSY);
			break;
		case 2:
			check(ELEMENT_AVAILABLE_STATUS_AVAILABLE);
			break;
		case 3:
			check(ELEMENT_AVAILABLE_STATUS_OUTSIDE);
			break;
		default:
			break;
		}
		//Set a type of invite  
		switch (invitation) {
		case 1:
			check(ELEMENT_INVITATION_TYPE_NEVER);
			break;
		case 2:
			check(ELEMENT_INVITATION_TYPE_ALWAYS);
			break;
		case 3:
			check(ELEMENT_INVITATION_TYPE_ASK);
			break;
		default:
			break;
		}		
		click(ELEMENT_ADD_MORE_PARTICIPANT_ICON);
		type(ELEMENT_INPUT_PARTICIPANT_TEXTAREA, participant, true);
		save();
		save();
		if (!not_ask){
			assert getText(By.id("confirm")).equalsIgnoreCase("\"Are you sure to save this event and send invitations?\"");
			if (!send){
				save();
			}else {
				click(ELEMENT_SAVE_AND_BUTTON);
			}
		}
		waitForElementNotPresent(ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB);
	}
	
	/**
	 * 
	 * @param typeRepeat: String (Daily, Weekly,etc...)
	 * @param intervalRepeat: String ("1" --> "30") 
	 * @param typeEndRepeat: String (Never, After, By date...)
	 */
    public static void repeatingEvent(String[] typeRepeat, String[] occurrencesAndDate){
    	
    	select(ELEMENT_REPEATING_EVENT, typeRepeat[0]);
    	select(ELEMENT_INTERVAL_REPEATING_EVENT, typeRepeat[1]);
    	pause(1000);
    	if (typeRepeat[2].matches("Never")){
    		check(ELEMENT_REPEAT_TYPE_NEVER);
    	}else if (typeRepeat[2].matches("After")){
    		check(ELEMENT_REPEAT_TYPE_AFTER);
    		type(ELEMENT_REPEAT_TYPE_AFTER_OCCURRENCES, occurrencesAndDate[0], true);
    	}else if (typeRepeat[2].matches("Date")){
    		check(ELEMENT_REPEAT_TYPE_DATE);
    		type(ELEMENT_REPEAT_INPUT_TYPE_DATE, occurrencesAndDate[1], true);
    	}
    	save();
    }

    /**function go to edit an event
     * @author lientm
     * @param eventName: name of even
     * @param mode: view mode
     */
    public static void goToEditEvent(String eventName, int...mode){
		By task = getTaskFromViewMode(eventName, mode);
		info("Go to edit task");
		for (int i = 0; i < 5; i++){
			rightClickOnElement(task);
			WebElement edit = waitForAndGetElement(ELEMENT_TASK_EDIT, 5000, 0);
			if (edit != null){
				click(ELEMENT_TASK_EDIT);
				waitForElementPresent(ELEMENT_ADD_DETAIL_EVENT_FORM);
				break;
			}
		}
    }
    
    /**function check dates default display in Add/Edit Event
     * @author lientm
     * @param time: optional (use when need check hour)
     */
    public static void checkDateDefaultWhenAddEvent(String...time){
    	info("Check date default display in from and to when add new event");
    	assert getValue(ELEMENT_TIME_FROM_CHECKBOX).equalsIgnoreCase(getCurrentDate("MM/dd/yyyy"));
    	assert getValue(ELEMENT_TIME_TO_CHECKBOX).equalsIgnoreCase(getCurrentDate("MM/dd/yyyy"));
    	if (time.length > 1){
    		assert getValue(ELEMENT_SET_TIME_FROM_HOUR_CHECKBOX).equalsIgnoreCase(time[0]);
    		assert getValue(ELEMENT_SET_TIME_TO_HOUR_CHECKBOX).equalsIgnoreCase(time[1]);
    	}
    }
    
	public static List<WebElement> getListElement(By parent, By locator) {
		WebElement e = waitForAndGetElement(parent);
		try {
			List<WebElement> element = e.findElements(locator);
			if (e != null){
				return element;
			}
		} catch (NoSuchElementException ex) {
			info("NoSuchElementException");
		}catch(StaleElementReferenceException ex)
		{
			checkCycling(ex, 10);
			pause(WAIT_INTERVAL);
			getListElement(parent, locator);
		}
		finally{
			loopCount=0;
		}
		return null;
	}
	
	/**function check invitation mail after add event then delete its
	 * @author lientm
	 * @param eventName
	 */
	public static void checkInvitationWhenAddEventThenDetele(String eventName){
		By mail = By.xpath("//b[contains(text(), '[Invitation] "+ eventName + "')]");
		By attach_file = By.xpath("//b[text()='icalendar.ics']");
		
		click(mail);
		waitForElementPresent(attach_file);
		click(ELEMENT_DELETE);
	}
	
	/**function check status of a participant
	 * @author lientm
	 * @param participant: name of participant
	 * @param status: name of status (pending, yes)
	 */
	public static void checkStatusOfParticipant(String participant, String status){
		By element_status = By.xpath("//*[@id='UIParticipantList']//td/div[text()='" + participant + "']/../../td[3]/div");
		
		click(ELEMENT_ADD_DETAIL_EVENT_PARTICIPANT_TAB);
		assert waitForAndGetElementNotDisplay(element_status).getAttribute("title").equalsIgnoreCase(status);
		cancel();
	}
}