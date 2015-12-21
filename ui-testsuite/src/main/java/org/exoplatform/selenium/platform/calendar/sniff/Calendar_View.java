package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.TaskManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectCategoryOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.testng.annotations.*;


public class Calendar_View extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	CalendarHomePage cHome;
	EventManagement event;
	CalendarManagement cMang;
	UserDatabase userData;
	TaskManagement task;
	String fullName;
	ManageAlert alert;
	String titleEvent;
	String titleTask;
	String content;

	String titleEventCur;
	String titleEventNext;
	String titleEventPre;
	String titleEventNextWeek;
	String titleEventPreWeek;
	String titleEventNextMonth;
	String titleEventPreMonth;

	String titleTaskCur;
	String titleTaskNext;
	String titleTaskPre;
	String titleTaskNextWeek;
	String titleTaskPreWeek;
	String titleTaskNextMonth;
	String titleTaskPreMonth;
	String defaultFormatDate;

	String titleEventMeeting;
	String titleEventCall;
	String titleEventClient;
	String titleEventHoliday;
	String titleEventAnni;

	String titleTaskMeeting;
	String titleTaskCall;
	String titleTaskClient;
	String titleTaskHoliday;
	String titleTaskAnni;

	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		alert = new ManageAlert(driver);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		cHome= new CalendarHomePage(driver);
		event= new EventManagement(driver);
		task= new TaskManagement(driver);
		cMang = new CalendarManagement(driver);
		txData = new TextBoxDatabase();
		userData = new UserDatabase();
		magAc.signIn(DATA_USER1, DATA_PASS);
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fullName = userData.fullName.get(0);

		info("Create data test");
		hp.goToCalendarPage();
		titleEvent = txData.getContentByArrayTypeRandom(1)+"e115595";
		titleTask = txData.getContentByArrayTypeRandom(1)+"t115595";
		content = txData.getContentByArrayTypeRandom(1)+"115595";

		titleEventCur=titleEvent+"cur";
		titleTaskCur=titleTask+"cur";

		//Day
		titleEventNext=titleEvent+"next";
		titleEventPre=titleEvent+"pre";
		titleTaskNext=titleTask+"next";
		titleTaskPre=titleTask+"pre";

		//week
		titleEventNextWeek=titleEvent+"wnext";
		titleEventPreWeek=titleEvent+"wpre";
		titleTaskNextWeek=titleTask+"wnext";
		titleTaskPreWeek=titleTask+"wpre";

		//Month
		titleEventNextMonth=titleEvent+"mnext";
		titleEventPreMonth=titleEvent+"mpre";
		titleTaskNextMonth=titleTask+"mnext";
		titleTaskPreMonth=titleTask+"mpre";

		//Category
		titleEventMeeting=titleEvent+"meeting";
		titleEventCall=titleEvent+"call";
		titleEventClient=titleEvent+"client";
		titleEventHoliday=titleEvent+"holiday";
		titleEventAnni=titleEvent+"anni";

		titleTaskMeeting=titleTask+"meeting";
		titleTaskCall=titleTask+"call";
		titleTaskClient=titleTask+"client";
		titleTaskHoliday=titleTask+"holiday";
		titleTaskAnni=titleTask+"anni";

		defaultFormatDate="MM/dd/yyyy";
		//hp.goToCalendarPage();

		//category
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventMeeting, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true,null,"Meeting");
		event.saveQuickAddEvent();

		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventCall, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true,null,"Calls");
		event.saveQuickAddEvent();

		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventClient, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true,null,"Clients");
		event.saveQuickAddEvent();

		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventHoliday, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true,null,"Holiday");
		event.saveQuickAddEvent();

		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventAnni, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true,null,"Anniversary");
		event.saveQuickAddEvent();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskMeeting, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true,null,"Meeting");
		task.saveQuickAddTask();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskCall, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true,null,"Calls");
		task.saveQuickAddTask();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskClient, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true,null,"Clients");
		task.saveQuickAddTask();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskHoliday, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true,null,"Holiday");
		task.saveQuickAddTask();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskAnni, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true,null,"Anniversary");
		task.saveQuickAddTask();

		//current
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventCur, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true);
		event.saveQuickAddEvent();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskCur, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),true);
		task.saveQuickAddTask();

		//day
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventNext, content, getDate(1,defaultFormatDate), getDate(1,defaultFormatDate),true);
		event.saveQuickAddEvent();

		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventPre, content, getDate(-1,defaultFormatDate), getDate(-1,defaultFormatDate),true);
		event.saveQuickAddEvent();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskNext, content, getDate(1,defaultFormatDate), getDate(1,defaultFormatDate),true);
		task.saveQuickAddTask();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskPre, content, getDate(-1,defaultFormatDate), getDate(-1,defaultFormatDate),true);
		task.saveQuickAddTask();

		//week
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventNextWeek, content, getDate(7,defaultFormatDate), getDate(7,defaultFormatDate),true);
		event.saveQuickAddEvent();

		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventPreWeek, content, getDate(-7,defaultFormatDate), getDate(-7,defaultFormatDate),true);
		event.saveQuickAddEvent();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskNextWeek, content, getDate(7,defaultFormatDate), getDate(7,defaultFormatDate),true);
		task.saveQuickAddTask();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskPreWeek, content, getDate(-7,defaultFormatDate), getDate(-7,defaultFormatDate),true);
		task.saveQuickAddTask();

		//Month
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventNextMonth, content, getDate(31,defaultFormatDate), getDate(31,defaultFormatDate),true);
		event.saveQuickAddEvent();

		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEventPreMonth, content, getDate(-31,defaultFormatDate), getDate(-31,defaultFormatDate),true);
		event.saveQuickAddEvent();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskNextMonth, content, getDate(31,defaultFormatDate), getDate(31,defaultFormatDate),true);
		task.saveQuickAddTask();

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTaskPreMonth, content, getDate(-31,defaultFormatDate), getDate(-31,defaultFormatDate),true);
		task.saveQuickAddTask();
	}

	@AfterMethod
	public void afterMethod(){
		hp.goToCalendarPage();
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		cHome.goToView(selectViewOption.WEEK);

		cHome.deleteEventTask( titleEventMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleEventCall, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleEventClient, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleEventHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleEventAnni, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleTaskMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleTaskCall, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleTaskClient, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleTaskHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleTaskAnni, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleTaskCur, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));
		cHome.deleteEventTask( titleEventCur, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(0,"MMM dd yyyy"));

		cHome.deleteEventTask( titleTaskNext, selectViewOption.MONTH, selectDayOption.ALLDAY,getDate(1,"MMM dd yyyy"));
		cHome.deleteEventTask( titleEventNext, selectViewOption.MONTH, selectDayOption.ALLDAY,getDate(1,"MMM dd yyyy"));

		cHome.deleteEventTask( titleTaskPre, selectViewOption.MONTH, selectDayOption.ALLDAY,getDate(-1,"MMM dd yyyy"));
		cHome.deleteEventTask( titleEventPre, selectViewOption.MONTH, selectDayOption.ALLDAY,getDate(-1,"MMM dd yyyy"));

		hp.goToCalendarPage();
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		cHome.goToView(selectViewOption.WEEK);
		click(cHome.ELEMENT_NEXT_BUTTON_ANY_VIEW);
		cHome.deleteEventTask( titleEventNextWeek, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(7,"MMM dd yyyy"));
		cHome.deleteEventTask( titleTaskNextWeek, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(7,"MMM dd yyyy"));

		hp.goToCalendarPage();
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		cHome.goToView(selectViewOption.WEEK);
		click(cHome.ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);
		cHome.deleteEventTask( titleTaskPreWeek, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(-7,"MMM dd yyyy"));
		cHome.deleteEventTask( titleEventPreWeek, selectViewOption.WEEK, selectDayOption.ALLDAY,getDate(-7,"MMM dd yyyy"));

		cHome.goToView(selectViewOption.MONTH);
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		click(cHome.ELEMENT_NEXT_BUTTON_ANY_VIEW);
		cHome.deleteEventTask( titleEventNextMonth, selectViewOption.MONTH, selectDayOption.ALLDAY,null);
		cHome.deleteEventTask( titleTaskNextMonth, selectViewOption.MONTH, selectDayOption.ALLDAY,null);

		cHome.goToView(selectViewOption.MONTH);
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		click(cHome.ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);
		cHome.deleteEventTask( titleEventPreMonth, selectViewOption.MONTH, selectDayOption.ALLDAY,null);
		cHome.deleteEventTask( titleTaskPreMonth, selectViewOption.MONTH, selectDayOption.ALLDAY,null);

		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:115595.
	 * Test Case Name: Check Today view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test01_CheckTodayView() {
		info("Test 1: Check Today view");
		/*Step Number: 1
		 *Step Name: Step 1: Add task/ event
		 *Step Description: 
			- Go to calendar
			- Add new task/event
		 *Input Data: 

		 *Expected Outcome: 
			Task/ event are created*/

		/*Step number: 2
		 *Step Name: Step 2: Select view
		 *Step Description: 
			- Select view (day/week/month/list/work week) which has not the current day
		 *Input Data: 

		 *Expected Outcome: 
			- Calendar is displayed with the selected view(day/week/month/list/work week)*/

		/*Step number: 3
		 *Step Name: Step 3: Check displaying of task and event.
		 *Step Description: 
			- Click on Today on the main bar
		 *Input Data: 

		 *Expected Outcome: 
			- Go to the current view which has the current day (today view). Mini calendar is updated to the week has current day*/
		hp.goToCalendarPage();
		cHome.goToView(selectViewOption.DAY);
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		cHome.verifyIsPresentEventTask(titleEventCur, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskCur, selectViewOption.DAY, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNext, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNext, selectViewOption.DAY, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPre, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPre, selectViewOption.DAY, selectDayOption.ALLDAY);
	}

	/**
	 * Case ID:115596.
	 * Test Case Name: Check displaying added task/event in work week view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test02_CheckDisplayingAddedTaskeventInWorkWeekView() {
		info("Test 2: Check displaying added task/event in work week view");
		/*Step Number: 1
		 *Step Name: Step 1: Add task/ event
		 *Step Description: 
			- Go to calendar
			- Add new task/event
		 *Input Data: 

		 *Expected Outcome: 
			Task/ event are created*/

		/*Step number: 2
		 *Step Name: Step 2: Check displaying of task and event.
		 *Step Description: 
			- Click on Work Week on the main bar
		 *Input Data: 

		 *Expected Outcome: 
			Event/ task is displayed in Work Week view*/

		/*Step number: 3
		 *Step Name: Step 3: Check next week/previous week
		 *Step Description: 
			- Click next week/previous week icon
		 *Input Data: 

		 *Expected Outcome: 
			- Next week/previous week is displayed correctly
			- Mini calendar is updated also*/ 
		hp.goToCalendarPage();
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		Utils.pause(500);
		cHome.goToView(selectViewOption.WORKWEEK);
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		cHome.verifyIsPresentEventTask(titleEventCur, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskCur, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNextWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNextWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPreWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPreWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);

		click(cHome.ELEMENT_NEXT_BUTTON_ANY_VIEW);
		cHome.verifyIsNotPresentEventTask(titleEventCur, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCur, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventNextWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskNextWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPreWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPreWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);

		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		click(cHome.ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);
		cHome.verifyIsNotPresentEventTask(titleEventCur, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCur, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNextWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNextWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventPreWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskPreWeek, selectViewOption.WORKWEEK, selectDayOption.ALLDAY);
	}

	/**
	 * Case ID:115597.
	 * Test Case Name: Check displaying added task/event in list view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test03_CheckDisplayingAddedTaskeventInListView() {
		info("Test 3: Check displaying added task/event in list view");
		/*Step Number: 1
		 *Step Name: Step 1: Add task/ event
		 *Step Description: 
			- Go to calendar
			- Add new task/event
		 *Input Data: 

		 *Expected Outcome: 
			Task/ event are created*/

		/*Step number: 2
		 *Step Name: Step 2: Check displaying of task and event.
		 *Step Description: 
			- Click on List on the main bar
		 *Input Data: 

		 *Expected Outcome: 
			Event/ task is displayed in List view*/

		/*Step number: 3
		 *Step Name: Step 3: Check next week/previous day
		 *Step Description: 
			- Click on next week/previous day icon
		 *Input Data: 

		 *Expected Outcome: 
			- Next week/previous day is displayed correctly
			- Mini calendar is updated also*/ 
		hp.goToCalendarPage();
		cHome.goToView(selectViewOption.LIST);
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		cHome.verifyIsPresentEventTask(titleEventCur, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskCur, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNext, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNext, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPre, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPre, selectViewOption.LIST, selectDayOption.ALLDAY);

		click(cHome.ELEMENT_NEXT_BUTTON_ANY_VIEW);
		cHome.verifyIsNotPresentEventTask(titleEventCur, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCur, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventNext, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskNext, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPre, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPre, selectViewOption.LIST, selectDayOption.ALLDAY);

		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		click(cHome.ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);
		cHome.verifyIsNotPresentEventTask(titleEventCur, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCur, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNext, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNext, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventPre, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskPre, selectViewOption.LIST, selectDayOption.ALLDAY);
	}

	/**
	 * Case ID:115598.
	 * Test Case Name: Check displaying added task/event in month view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test04_CheckDisplayingAddedTaskeventInMonthView() {
		info("Test 4: Check displaying added task/event in month view");
		/*Step Number: 1
		 *Step Name: Step 1: Add task/ event
		 *Step Description: 
			- Go to calendar
			- Add new task/event
		 *Input Data: 

		 *Expected Outcome: 
			Task/ event are created*/

		/*Step number: 2
		 *Step Name: Step 2: Check displaying of task and event.
		 *Step Description: 
			- Click on Month on the main bar
		 *Input Data: 

		 *Expected Outcome: 
			Event/ task is displayed in Month view*/

		/*Step number: 3
		 *Step Name: Step 3: Check next month/previous month
		 *Step Description: 
			- Click on next month/previous month icon
		 *Input Data: 

		 *Expected Outcome: 
			- Next month/previous month is displayed correctly
			- Mini calendar is updated also*/ 
		hp.goToCalendarPage();
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		Utils.pause(500);
		cHome.goToView(selectViewOption.MONTH);

		cHome.verifyIsPresentEventTaskWithDateTime(titleEventCur, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTaskWithDateTime(titleTaskCur, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNextMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNextMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPreMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPreMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);

		click(cHome.ELEMENT_NEXT_BUTTON_ANY_VIEW);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEventCur, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleTaskCur, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventNextMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskNextMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPreMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPreMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);

		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		click(cHome.ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleEventCur, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTaskWithDateTime(titleTaskCur, getDate(0,"MMM dd yyyy"), selectViewOption.MONTH, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNextMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNextMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventPreMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskPreMonth, selectViewOption.MONTH, selectDayOption.ALLDAY);

	}

	/**
	 * Case ID:115599.
	 * Test Case Name: Check displaying added task/event in week view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test05_CheckDisplayingAddedTaskeventInWeekView() {
		info("Test 5: Check displaying added task/event in week view");
		/*Step Number: 1
		 *Step Name: Step 1: Add task/ event
		 *Step Description: 
			- Go to calendar
			- Add new task/event
		 *Input Data: 

		 *Expected Outcome: 
			Task/ event are created*/

		/*Step number: 2
		 *Step Name: Step 2: Check displaying of task and event.
		 *Step Description: 
			- Click on Week on the main bar
		 *Input Data: 

		 *Expected Outcome: 
			Event/ task is displayed in Week view*/

		/*Step number: 3
		 *Step Name: Step 3: Check next week/previous week
		 *Step Description: 
			- Click next week/previous week icon
		 *Input Data: 

		 *Expected Outcome: 
			- Next week/previous week is displayed correctly
			- Mini calendar is updated also*/ 
		hp.goToCalendarPage();
		cHome.goToView(selectViewOption.WEEK);
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		cHome.verifyIsPresentEventTask(titleEventCur, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskCur, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNextWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNextWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPreWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPreWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);

		click(cHome.ELEMENT_NEXT_BUTTON_ANY_VIEW);
		cHome.verifyIsNotPresentEventTask(titleEventCur, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCur, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventNextWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskNextWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPreWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPreWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);

		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		click(cHome.ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);
		cHome.verifyIsNotPresentEventTask(titleEventCur, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCur, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNextWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNextWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventPreWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskPreWeek, selectViewOption.WEEK, selectDayOption.ALLDAY);
	}

	/**
	 * Case ID:115600.
	 * Test Case Name: Check displaying added task/event in day view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test06_CheckDisplayingAddedTaskeventInDayView() {
		info("Test 6: Check displaying added task/event in day view");
		/*Step Number: 1
		 *Step Name: Step 1: Add task/ event
		 *Step Description: 
			- Go to calendar
			- Add new task/event
		 *Input Data: 

		 *Expected Outcome: 
			Task/ event are created*/

		/*Step number: 2
		 *Step Name: Step 2: Check displaying of task and event.
		 *Step Description: 
			- Click on Day on the main bar
		 *Input Data: 

		 *Expected Outcome: 
			Event/ task is displayed in Day view*/

		/*Step number: 3
		 *Step Name: Step 3: Check next day/previous day
		 *Step Description: 
			- Click next day/ previous day icon
		 *Input Data: 

		 *Expected Outcome: 
			- Next day/previous day is displayed correctly
			- Mini calendar is updated also*/ 
		hp.goToCalendarPage();
		cHome.goToView(selectViewOption.DAY);
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		cHome.verifyIsPresentEventTask(titleEventCur, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskCur, selectViewOption.DAY, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNext, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNext, selectViewOption.DAY, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPre, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPre, selectViewOption.DAY, selectDayOption.ALLDAY);

		click(cHome.ELEMENT_NEXT_BUTTON_ANY_VIEW);
		cHome.verifyIsNotPresentEventTask(titleEventCur, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCur, selectViewOption.DAY, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventNext, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskNext, selectViewOption.DAY, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventPre, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskPre, selectViewOption.DAY, selectDayOption.ALLDAY);

		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		click(cHome.ELEMENT_PREVIOUS_BUTTON_ANY_VIEW);
		cHome.verifyIsNotPresentEventTask(titleEventCur, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCur, selectViewOption.DAY, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventNext, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskNext, selectViewOption.DAY, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventPre, selectViewOption.DAY, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskPre, selectViewOption.DAY, selectDayOption.ALLDAY);

	}

	/**
	 * Case ID:115647.
	 * Test Case Name: Check category filter in Week view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test07_CheckCategoryFilterInWeekView() {
		info("Test 7: Check category filter in Week view");
		/*Step Number: 1
		 *Step Name: Create some event/task
		 *Step Description: 
			- goto Calendar and click [Week] to show week view
			- Create some event/tasks belong to some different category like anniversary, holiday, meeting, call
			- in Categories drop
			-down box, choose in turn these categories
		 *Input Data: 

		 *Expected Outcome: 
			- Events/Tasks belong to the category are shown in main pane
			- When selecting [All], all the Events are shown*/ 
		hp.goToCalendarPage();
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		cHome.goToView(selectViewOption.WEEK);
		cHome.selectCategory(selectCategoryOption.MEETING);
		cHome.verifyIsNotPresentEventTask(titleTaskClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.selectCategory(selectCategoryOption.ANNIVERSARY);
		cHome.verifyIsNotPresentEventTask(titleTaskClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.selectCategory(selectCategoryOption.CALL);
		cHome.verifyIsNotPresentEventTask(titleTaskClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.selectCategory(selectCategoryOption.HOLIDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.selectCategory(selectCategoryOption.CLIENT);
		cHome.verifyIsPresentEventTask(titleTaskClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.selectCategory(selectCategoryOption.ALL);
		cHome.verifyIsPresentEventTask(titleTaskClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventClient, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventHoliday, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventCall, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventAnni, selectViewOption.WEEK, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventMeeting, selectViewOption.WEEK, selectDayOption.ALLDAY);

	}

	/**
	 * Case ID:115648.
	 * Test Case Name: Check category filter in List view.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test08_CheckCategoryFilterInListView() {
		info("Test 8: Check category filter in List view");
		/*Step Number: 1
		 *Step Name: Create some events/tasks
		 *Step Description: 
			- goto Calendar and click [List] to show List view
			- Create some event/tasks belong to some different category like anniversary, holiday, meeting, call
			- in Categories drop
			-down box, choose in turn these categories
		 *Input Data: 

		 *Expected Outcome: 
			- Events/Tasks belong to the category are shown in main pane
			- When selecting [All], all the Events are shown*/ 
		cHome.goToView(selectViewOption.LIST);
		click(cHome.ELEMENT_TODAY_ACTION_BAR);
		cHome.selectCategory(selectCategoryOption.ANNIVERSARY);
		cHome.verifyIsNotPresentEventTask(titleTaskClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);
		
		cHome.selectCategory(selectCategoryOption.MEETING);
		cHome.verifyIsNotPresentEventTask(titleTaskClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.selectCategory(selectCategoryOption.CALL);
		cHome.verifyIsNotPresentEventTask(titleTaskClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.selectCategory(selectCategoryOption.HOLIDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsNotPresentEventTask(titleEventClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.selectCategory(selectCategoryOption.CLIENT);
		cHome.verifyIsPresentEventTask(titleTaskClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleTaskMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsNotPresentEventTask(titleEventMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.selectCategory(selectCategoryOption.ALL);
		cHome.verifyIsPresentEventTask(titleTaskClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleTaskMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

		cHome.verifyIsPresentEventTask(titleEventClient, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventHoliday, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventCall, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventAnni, selectViewOption.LIST, selectDayOption.ALLDAY);
		cHome.verifyIsPresentEventTask(titleEventMeeting, selectViewOption.LIST, selectDayOption.ALLDAY);

	}
}