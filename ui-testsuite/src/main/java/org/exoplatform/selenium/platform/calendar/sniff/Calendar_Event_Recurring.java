package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.EventManagement.recurringType;
import org.exoplatform.selenium.platform.calendar.EventManagement.repeatEndType;
import org.exoplatform.selenium.platform.calendar.EventManagement.repeatType;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class Calendar_Event_Recurring extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	CalendarHomePage cHome;
	EventManagement event;
	AttachmentFileDatabase fData;
	CalendarManagement cMang;
	UserDatabase userData;

	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp = new HomePagePlatform(driver);
		cHome= new CalendarHomePage(driver);
		event= new EventManagement(driver);
		cMang = new CalendarManagement(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
	}

	@AfterMethod
	public void afterMethod(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:115634.
	 * Test Case Name: Edit a recurring event with "Following events" option.
	 * Case ID:115632.
	 * Test Case Name: Delete Following events in recurring event.
	 * Pre-Condition: A recurring events is created
	 * Post-Condition: 
	 */
	@Test
	public  void test01_02_EditDeleteFollowingEventsInRecurringEvent() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"1115632";
		String content = txData.getContentByArrayTypeRandom(1)+"1115632";
		String titleEvent2 = txData.getContentByArrayTypeRandom(1)+"2115632";
		String contentEvent2 = txData.getContentByArrayTypeRandom(1)+"2115632";
		info("Add a event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.moreDetailsEvent();
		check(event.ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX,2);
		event.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,"5");
		click(event.ELEMENT_SAVE_EVENT_OCCURRING);
		event.saveAddEventDetails();

		info("Test 01 Edit a recurring event with Following events option");
		/*Step number: 2
		 *Step Name: Step 2: Show Edit an event form
		 *Step Description: 
			- Edit an even from the series
		 *Input Data: 

		 *Expected Outcome: 
			- The pop up "Edit Recurring event" is displayed
			- The icon "Repeat" is checked*/

		/*Step number: 3
		 *Step Name: Step 3: Edit repeat option
		 *Step Description: 
			- Edit the repeat option
			- Click "Save"
		 *Input Data: 

		 *Expected Outcome: 
			- A confirmation pop up is displayed to ask user: "Would you like to change only this event, all events in the series, or this and all following events in the series?"
			- 3 options:  + Only this event + Following events + All events
			- The option "Only this Event" is selcted by Default*/

		/*Step number: 4
		 *Step Name: Step 4: Choose option to change
		 *Step Description: 
			- Choose "Following events"
			- Click "Save"
		 *Input Data: 

		 *Expected Outcome: 
			- Changes are appliyed for current & following events*/ 
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.WEEK, selectDayOption.DETAILTIME, getDate(1,"MMM dd yyyy"));
		event.inputDataEventInDetailForm(titleEvent2, contentEvent2, null,null, false);
		//click(event.ELEMENT_BUTTON_EVENT_SAVE_DETAILS);
		waitForAndGetElement(event.ELEMENT_BUTTON_EVENT_SAVE_DETAILS, DEFAULT_TIMEOUT, 1);
		clickByJavascript(event.ELEMENT_BUTTON_EVENT_SAVE_DETAILS, 2);
		event.editRecurringEvent(recurringType.FOLLOW_EVENT, true);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(1,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(2,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(3,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(4,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);

		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(1,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(2,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(3,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(4,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);

		info("Test 02 Delete Following events in recurring event");
		/*Step number: 2
		 *Step Name: <p>Step 2: Select a recurring event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			- Right click on event from the series
			- Choose the option "Delete"
		 *Input Data: 

		 *Expected Outcome: 
			- A pop upis displayed with:* A title: "Delete Recurring Event"* A message: Would you like to delete only this event, all events in the series, or this and all following events in the series?* The option 'Only this event" is selected by default* A button "Delete"*/

		/*Step number: 3
		 *Step Name: <p>Step 3: Delete only this event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			<p>
			- Choose the default option "Following events"<br>
			- Click "Delete"</p>
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- Selected and following event are deleted</p>*/ 
		event.deleteRecurringEvent(titleEvent2, selectViewOption.WEEK, selectDayOption.DETAILTIME, recurringType.FOLLOW_EVENT,getDate(2,"MMM dd yyyy"),true);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(1,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent2, getDate(2,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent2, getDate(3,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent2, getDate(4,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);

		info("Clear data");
		event.deleteRecurringEvent(titleEvent, selectViewOption.MONTH, selectDayOption.DETAILTIME, recurringType.FOLLOW_EVENT,getDate(0,"MMM dd yyyy"),true);
		event.deleteRecurringEvent(titleEvent2, selectViewOption.MONTH, selectDayOption.DETAILTIME, recurringType.FOLLOW_EVENT,getDate(1,"MMM dd yyyy"),true);
	}

	/**
	 * Case ID:115639.
	 * Test Case Name: Delete only a current recurring event.
	 * Pre-Condition: A recurring events is created
	 * Post-Condition: 
	 */
	@Test
	public  void test03_04_EditDeleteOnlyACurrentRecurringEvent() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"1115639";
		String content = txData.getContentByArrayTypeRandom(1)+"1115639";
		String titleEvent2 = txData.getContentByArrayTypeRandom(1)+"2115639";
		String contentEvent2 = txData.getContentByArrayTypeRandom(1)+"2115639";
		info("Add a event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.moreDetailsEvent();
		check(event.ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX,2);
		event.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,"5");
		click(event.ELEMENT_SAVE_EVENT_OCCURRING);
		event.saveAddEventDetails();
		info("Test 07 Edit a recurring event with Only this event option");	
		/*Step number: 2
		 *Step Name: Step 2: Show Edit an event form
		 *Step Description: 
			- Edit an even from the series
		 *Input Data: 

		 *Expected Outcome: 
			- The pop up "Edit Recurring event" is displayed
			- The icon "Repeat" is checked*/
		
		/*Step number: 3
		 *Step Name: Step 3: Edit repeat option
		 *Step Description: 
			- Edit any information of event
			- Click "Save"
		 *Input Data: 

		 *Expected Outcome: 
			- A confirmation pop up is displayed to ask user: "Would you like to change only this event, all events in the series, or this and all following events in the series?"
			- 3 new options: Only this event, Following events, All events.
			- The option "Only this event" is selected by default.*/

		/*Step number: 4
		 *Step Name: Step 4: Choose option to change
		 *Step Description: 
			- Choose the default option "Only this event"
			- Click "Save"
		 *Input Data: 

		 *Expected Outcome: 
			- Changes are restricted to the edited recurring event*/ 
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.WEEK, selectDayOption.DETAILTIME, getDate(0,"MMM dd yyyy"));
		event.inputDataEventInDetailForm(titleEvent2, contentEvent2, null,null, false);
		//click(event.ELEMENT_BUTTON_EVENT_SAVE_DETAILS);
		waitForAndGetElement(event.ELEMENT_BUTTON_EVENT_SAVE_DETAILS, DEFAULT_TIMEOUT, 1);
		clickByJavascript(event.ELEMENT_BUTTON_EVENT_SAVE_DETAILS, 2);
		event.editRecurringEvent(recurringType.ONLY_EVENT, true);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(1,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(2,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(3,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(4,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);

		info("Test 04 Delete only a current recurring event");
		/*Step Number: 1
		 *Step Name: <p>Step 1: Show calendar application<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			- Connect to Intranet
			- Open "Calendar" application
		 *Input Data: 

		 *Expected Outcome: 
			- A recurring events is displayed*/

		/*Step number: 2
		 *Step Name: <p>Step 2: Select a recurring event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			- Right click on event from the series
			- Choose the option "Delete"
		 *Input Data: 

		 *Expected Outcome: 
			- A pop upis displayed with:* A title: "Delete Recurring Event"* A message: Would you like to delete only this event, all events in the series, or this and all following events in the series?* The option 'Only this event" is selected by default* A button "Delete"*/

		/*Step number: 3
		 *Step Name: <p>Step 3: Delete only this event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			- Choose the default option "Only this event"
			- Click "Delete"
		 *Input Data: 

		 *Expected Outcome: 
			- Only current event is deleted from the series*/ 
		event.deleteRecurringEvent(titleEvent, selectViewOption.WEEK, selectDayOption.DETAILTIME, recurringType.ONLY_EVENT, getDate(1,"MMM dd yyyy"),true);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(1,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(2,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(3,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(4,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);

		info("Clear data");
		cHome.deleteEventTask(titleEvent2, selectViewOption.WEEK, selectDayOption.DETAILTIME, getDate(0,"MMM dd yyyy"),true, true);
		event.deleteRecurringEvent(titleEvent, selectViewOption.WEEK, selectDayOption.DETAILTIME, recurringType.ALL_EVENT, getDate(2,"MMM dd yyyy"),true);
	}
	
	/**
	 * Case ID:115644.
	 * Test Case Name: Add a Recurring Events.
	 * Case ID:115633.
	 * Test Case Name: Edit a recurring event with "All events" option.
	 * Case ID:115641.
	 * Test Case Name: Delete all events.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test05_06_07_AddDeleteAllRecurringEvents() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"1115644";
		String content = txData.getContentByArrayTypeRandom(1)+"1115644";
		String titleEvent2 = txData.getContentByArrayTypeRandom(1)+"2115644";
		String contentEvent2 = txData.getContentByArrayTypeRandom(1)+"2115644";
		info("Test 05 Add a Recurring Events");
		/*Step Number: 1
		 *Step Name: Step 1: Show calendar application
		 *Step Description: 
			- Connect to Intranet
			- Choose Calendar application
		 *Input Data: 

		 *Expected Outcome: 
			- The Calendar is displayed*/
		info("Add a event");
		hp.goToCalendarPage();

		/*Step number: 2
		 *Step Name: Step 2: Show Add event form
		 *Step Description: 
			Click Event on action bar
		 *Input Data: 

		 *Expected Outcome: 
			- The pop up "Quick Add Event" is displayed*/
		event.goToAddEventFromActionBar();
		
		/*Step number: 3
		 *Step Name: Step 3: Add a recurring event
		 *Step Description: 
			- Input some requiredfields
			- Click [More Details]
			- Tick Repeat option 
			- Select Repeat, eg daily, Repeat every 1 day andEnd repeat after 5 occurrence
			- Save
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			-The default duration for Event (From
			-To) is 1 hour
			- Series of events is displayed as setting repeat.*/
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.moreDetailsEvent();
		check(event.ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX,2);
		event.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,"5");
		click(event.ELEMENT_SAVE_EVENT_OCCURRING);
		event.saveAddEventDetails();
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(1,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(2,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(3,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent, getDate(4,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		
		info("Test 06 Edit a recurring event with All events option");
		/*Step Number: 1
		 *Step Name: Step 1: Show recurring event
		 *Step Description: 
			- Connect to Intranet
			- Choose Calendar application
		 *Input Data: 

		 *Expected Outcome: 
			- The Calendar is displayed
			- A recurring event is displayed*/

		/*Step number: 2
		 *Step Name: Step 2: Show Edit an event form
		 *Step Description: 
			- Edit an even from the series
		 *Input Data: 

		 *Expected Outcome: 
			- The pop up "Edit Recurring event" is displayed
			- The icon "Repeat" is checked*/

		/*Step number: 3
		 *Step Name: Step 3: Edit repeat option
		 *Step Description: 
			- Edit the repeat option
			- Click "Save"
		 *Input Data: 

		 *Expected Outcome: 
			- A confirmation pop up is displayed to ask user: "Would you like to change only this event, all events in the series, or this and all following events in the series?"
			- 3 options:  + Only this event + Following events + All events
			- The option "Only this Event" is selcted by Default*/

		/*Step number: 4
		 *Step Name: Step 4: Choose option to change
		 *Step Description: 
			- Choose "All events"
			- Click "Save"
		 *Input Data: 

		 *Expected Outcome: 
			- Changes are appliyed for all events*/ 
		cHome.goToEditEventTaskFormByRightClick(titleEvent, selectViewOption.DAY, selectDayOption.DETAILTIME,getDate(0,"MMM dd yyyy"));
		event.inputDataEventInDetailForm(titleEvent2, contentEvent2, null,null, false);
		//click(event.ELEMENT_BUTTON_EVENT_SAVE_DETAILS);
		waitForAndGetElement(event.ELEMENT_BUTTON_EVENT_SAVE_DETAILS, DEFAULT_TIMEOUT, 1);
		clickByJavascript(event.ELEMENT_BUTTON_EVENT_SAVE_DETAILS, 2);
		event.editRecurringEvent(recurringType.ALL_EVENT, true);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(1,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(2,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(3,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent, getDate(4,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);

		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(1,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(2,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(3,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsPresentEventTaskWithDateTime(titleEvent2, getDate(4,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);

		info("Test 07 Delete all events");
		/*Step number: 3
		 *Step Name: <p>Step 3: Delete event<br data
		-mce
		-bogus="1"></p>
		 *Step Description: 
			<p>
			- Choose the default option "All events"<br>
			- Click "Delete"</p>
		 *Input Data: 

		 *Expected Outcome: 
			<p>
			- All events are deletes</p><p>
			- Series no longer exists</p>*/ 
		event.deleteRecurringEvent(titleEvent2, selectViewOption.DAY, selectDayOption.DETAILTIME, recurringType.ALL_EVENT, getDate(0,"MMM dd yyyy"),true);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent2, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent2, getDate(1,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent2, getDate(2,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent2, getDate(3,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEvent2, getDate(4,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.DETAILTIME);

	}
	
	/**
	 * Case ID:115640.
	 * Test Case Name: Drag and drop an occurrence in recurring event.
	 * Pre-Condition: A recurring event is created
	 * Post-Condition: 
	 */
	@Test
	public  void test08_DragAndDropAnOccurrenceInRecurringEvent() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115640";
		String content = txData.getContentByArrayTypeRandom(1)+"115640";

		info("Test 05 Drag and drop an occurrence in recurring event");
		/*Step Number: 1
		 *Step Name: Step 1: Show recurring event
		 *Step Description: 
			- Connect to Intranet
			- Choose Calendar application
		 *Input Data: 

		 *Expected Outcome: 
			- The Calendar is displayed
			- A recurring event is displayed*/
		info("Add a event");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.moreDetailsEvent();
		check(event.ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX,2);
		event.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,"5");
		click(event.ELEMENT_SAVE_EVENT_OCCURRING);
		event.saveAddEventDetails();
		
		/*Step number: 2
		 *Step Name: Step 2: Drag and drop an event
		 *Step Description: 
			- Drag and drop a single event from the series, Select only this event. Save
		 *Input Data: 

		 *Expected Outcome: 
			- Start and End dates and times of event are updated
			- Event is marked as 'edited' by an icon with tooltip.*/
		cHome.goToView(selectViewOption.MONTH);
		dragAndDropToObject(By.xpath(cHome.ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", titleEvent).replace("$date", getDate(0, "MMM dd yyyy"))),By.xpath(cHome.ELEMENT_ANY_TARGET_DATE.replace("${targetDate}", getDate(-1, "MMM dd yyyy"))));

		/*Step number: 3
		 *Step Name: Step 3: Check show tooltip after drag and drop event
		 *Step Description: 
			- Move the mouse over the icon
		 *Input Data: 

		 *Expected Outcome: 
			A tooltip is displayed "edited recurring event"*/ 
		cHome.goToView(selectViewOption.MONTH);
		mouseOver(cHome.ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", titleEvent).replace("$date", getDate(-1, "MMM dd yyyy")),true);
		waitForAndGetElement(event.ELEMENT_TITLE_RECURRING_EVENT);
		waitForAndGetElement(event.ELEMENT_DATE_TIME_RECURRING_EVENT);
		assert waitForAndGetElement(event.ELEMENT_EDITED_RECURRING_TEXT_RECURRING_EVENT).getText().contains("Edited Recurring event");

		info("Clear data");
		cHome.deleteEventTask(titleEvent, selectViewOption.MONTH, selectDayOption.DETAILTIME,getDate(-1,"MMM dd yyyy"));
		event.deleteRecurringEvent(titleEvent, selectViewOption.MONTH, selectDayOption.DETAILTIME, recurringType.ALL_EVENT, getDate(1,"MMM dd yyyy"),false);
	}
	
	/**
	 * Case ID:115643.
	 * Test Case Name: An extra icon is displayed for a repeated event.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test09_AnExtraIconIsDisplayedForARepeatedEvent() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"115643";
		String content = txData.getContentByArrayTypeRandom(1)+"115643";

		info("Test 08 An extra icon is displayed for a repeated event");
		/*Step Number: 1
		 *Step Name: Step 1: Open the calendar application
		 *Step Description: 
			- Connect to Intranet
			- Choose Calendar application
		 *Input Data: 

		 *Expected Outcome: 
			- The Calendar is displayed*/
		info("Add a event");
		hp.goToCalendarPage();

		/*Step number: 2
		 *Step Name: Step 2: Add a recurring event
		 *Step Description: 
			- Create a Recurring event
		 *Input Data: 

		 *Expected Outcome: 
			- Series of events is displayed with an extra icon, see attachment [extraIcon.png]*/
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,"MM/dd/yyyy HH")+":00", getDate(0,"MM/dd/yyyy HH")+":30",false);
		event.moreDetailsEvent();
		check(event.ELEMENT_ADD_EDIT_EVENT_REPEAT_CHECKBOX,2);
		event.inputRecurringInfoEvent(repeatType.Daily,"1",null,repeatEndType.After,"5");
		click(event.ELEMENT_SAVE_EVENT_OCCURRING);
		event.saveAddEventDetails();

		/*Step number: 3
		 *Step Name: Step 3: Check show the popover
		 *Step Description: 
			- Move the mouse over the event
		 *Input Data: 

		 *Expected Outcome: 
			- The popover of the event shows + Title of event+ Description+ Location+ Time+ Icon is the same icon with the label "Recurring event", pls see attachment [Recurring_icon_On_Popover.png]*/
		cHome.goToView(selectViewOption.MONTH);
		mouseOver(cHome.ELEMENT_EVENT_TASK_DETAIL_DATE_MONTH_VIEW.replace("$name", titleEvent).replace("$date", getDate(0, "MMM dd yyyy")),true);
		waitForAndGetElement(event.ELEMENT_TITLE_RECURRING_EVENT);
		waitForAndGetElement(event.ELEMENT_DATE_TIME_RECURRING_EVENT);
		waitForAndGetElement(event.ELEMENT_RECURRING_TEXT_RECURRING_EVENT);
		
		info("Clear data");
		event.deleteRecurringEvent(titleEvent, selectViewOption.DAY, selectDayOption.DETAILTIME, recurringType.ALL_EVENT, getDate(0,"MMM dd yyyy"),false);
	}
}

