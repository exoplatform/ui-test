package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.calendar.CalendarBase;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;

/**
 * @date: 25/04/2014
 * @author lientm
 * @description: update suggestion date follow https://jira.exoplatform.org/browse/FQA-1721
 */

public class Calendar_Task extends CalendarBase {

	ManageAccount acc;
	Event evt;
	Task tsk;

	@BeforeMethod
	public void setUpBeforeTest(){
//		getDriverAutoSave();
		initSeleniumTest();
		acc = new ManageAccount(driver);
		evt = new Event(driver);
		tsk = new Task(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		goToCalendarPage();
		goToCalendarSettings();
		settingCalendar("Week", "mm/dd/yyyy", null, null, "Monday", null, null);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**Testcase to check add task from 3 ways
	 * CaseID 109238: add new task from action bar or on a calendar
	 * CaseID: 69266: edit task
	 * CaseID: 69267: delete task
	 */
	
	/* caseId: 109238 -> Add new task by clicking Task on action bar or [Add task] in a calendar*/
	@Test
	public void test01_AddEditDeleteTask_FromActionBar(){
		String calendar = "Calendar_109238_1";
		String color = "sky_blue";
		String task = "Task_109238_1";
		String newTask = "Task_109238_1 update";
		String note = "Update new task";
		
		goToCalendarPage();
		setTimezoneForCalendar("(GMT +07:00) Asia/Ho_Chi_Minh");
		
		addCalendar(calendar, null, color);
		tsk.goToAddTaskFromActionBar();
		tsk.checkSuggestionTaskTime(null, 30);
		tsk.checkSuggestionTaskTime("07:00", 30);
		tsk.inputDataTask(task, null, null, null, false, calendar);
		tsk.editTask(task, newTask, note, getDate(0,"MM/dd/yyyy") + " 12:00", getDate(0,"MM/dd/yyyy") + " 13:00", false, "/TestDate/Winter.jpg");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", newTask)));

		tsk.deleteEventTask(newTask);
		deleteCalendar(calendar);
	}
	
	@Test
	public void test01_AddEditDeleteTask_FromOnCalendar(){
		String calendar = "Calendar_109238_2";
		String color = "sky_blue";
		String task = "Task_109238_2";
		String newTask = "Task_109238_2 update";
		String note = "Update new task";
		
		goToCalendarPage();
		setTimezoneForCalendar("(GMT +07:00) Asia/Ho_Chi_Minh");
		
		addCalendar(calendar, null, color);
		tsk.goToAddTaskFromCalendar(calendar);
		tsk.checkSuggestionTaskTime(null, 30);
		tsk.checkSuggestionTaskTime("07:00", 30);
		tsk.inputDataTask(task, null, null, null, false, calendar);
		tsk.editTask(task, newTask, note, getDate(0,"MM/dd/yyyy") + " 12:00", getDate(0,"MM/dd/yyyy") + " 13:00", false, "/TestDate/Winter.jpg");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", newTask)));

		tsk.deleteEventTask(newTask);
		deleteCalendar(calendar);
	}
	
	
	/* caseId: 99374 -> Add a Task by click on calendar main pane*/
	@Test
	public void test01_AddEditDeleteTask_FromMainPane(){
		String calendar = "Calendar_99374";
		String color = "sky_blue";
		String task = "Task_99374";
		String newTask = "Task_99374 update";
		String note = "Update new task";
		
		goToCalendarPage();
		setTimezoneForCalendar("(GMT +07:00) Asia/Ho_Chi_Minh");
		
		addCalendar(calendar, null, color);
		tsk.goToAddTaskFromMainPane("12:00");
		tsk.checkSuggestionTaskTime(null, 30);
		tsk.checkSuggestionTaskTime("07:00", 30);
		tsk.inputDataTask(task, null, null, null, false, calendar);
		tsk.editTask(task, newTask, note, getDate(0,"MM/dd/yyyy") + " 12:00", getDate(0,"MM/dd/yyyy") + " 13:00", false, "/TestDate/Winter.jpg");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", newTask)));

		tsk.deleteEventTask(newTask);
		deleteCalendar(calendar);
	}

	/** 
	 * Check pop-up reminder of a task
	 * CaseID: 68654
	 * PENDING: refer https://jira.exoplatform.org/browse/FQA-1352
	 */
	@Test(groups={"pending"})
	public void test02_CheckPopupReminderOfTask() {

		String CALENDAR02 = "CALENDAR_02";

		info("Go to Intranet Calendar");
		goToCalendarPage();
		driver.navigate().refresh();

		info("Add a new task");
		tsk.goToAddTaskFromActionBar();
		tsk.inputBasicQuickTask(CALENDAR02,CALENDAR02);
		tsk.inputFromToTask(getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false);
		info("Setting reminder for task");
		tsk.gotoSetPopupReminder();
		click(tsk.ELEMENT_BUTTON_TASK_SAVE);

		info("Check pop-up reminder appear");
		//TO-DO: need to add confirmation here after finishing setting reminder methods

		info("Restore data");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ALL_DAY.replace("${taskTitle}", CALENDAR02)));
		deleteEventTask(CALENDAR02,selectDayOption.ONEDAY);
	}

	/** 
	 * Check E-mail reminder of a task
	 * CaseID: 68655
	 * PENDING: refer https://jira.exoplatform.org/browse/FQA-1352
	 */
	@Test(groups={"pending"})
	public void test03_CheckEmailReminderOfTask() {

		String CALENDAR03 = "CALENDAR_03";
		//TO-DO: update later
		String ELEMENT_CAL_GMAIL_REMINDER = "";
		String ELEMENT_GMAIL_CONTENT = "";
		String FROM_TIME = getDate(0,"MM/dd/yyyy");
		String TO_TIME = getDate(0,"MM/dd/yyyy");

		info("Go to Intranet Calendar");
		goToCalendarPage();
		driver.navigate().refresh();

		info("Add a new task");
		tsk.goToAddTaskFromActionBar();
		tsk.inputBasicQuickTask(CALENDAR03,CALENDAR03);
		tsk.inputFromToTask(FROM_TIME,TO_TIME,false);
		info("Setting reminder for task");
		tsk.gotoSetEmailReminder();
		//TO-DO: update after finishing setting reminder methods
		click(tsk.ELEMENT_BUTTON_TASK_SAVE);
		Utils.pause(3000);

		info("Check if e-mail is sent");
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkAndDeleteMail(By.xpath(ELEMENT_CAL_GMAIL_REMINDER.replace("${taskName}", CALENDAR03)), ELEMENT_GMAIL_CONTENT);
		//TO-DO: update after finishing setting reminder methods
		switchToParentWindow();

		info("Restore data");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ALL_DAY.replace("${taskTitle}", CALENDAR03)));
		deleteEventTask(CALENDAR03,selectDayOption.ONEDAY);
	}

	/** 
	 * Drag & drop a task
	 * CaseID: 75249
	 */
	@Test
	public void test06_DragDropTask(){
		String CALENDAR06 = "CALENDAR_06";

		info("Go to Intranet Calendar");
		goToCalendarPage();
		driver.navigate().refresh();

		info("Add a new task");
		tsk.addQuickTask(CALENDAR06,CALENDAR06,getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false);

		info("Drag & drop a task");
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", CALENDAR06)));
		dragAndDropToObject(By.xpath(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", CALENDAR06)),ELEMENT_TARGET_DATE);

		info("Restore data");
		deleteEventTask(CALENDAR06, selectDayOption.ONEDAY);
	}

	/** 
	 * Resize a task
	 * CaseID: 75250
	 * PENDING: refer https://jira.exoplatform.org/browse/FQA-1351
	 */
	@Test(groups={"pending"})
	public void test07_ResizeTask(){
		String CALENDAR07 = "CALENDAR_07";

		info("Go to Intranet Calendar");
		goToCalendarPage();

		info("Add a new task");
		tsk.goToAddTaskFromActionBar();
		tsk.inputBasicQuickTask(CALENDAR07,CALENDAR07);
		tsk.inputFromToTask(getDate(0,"MM/dd/yyyy"),getDate(0,"MM/dd/yyyy"),false);
		waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_ALL_DAY.replace("${taskTitle}", CALENDAR07)));

		info("Resize a task to change date-time");
		//TO-DO: update later

		info("Restore data");
		Utils.pause(3000);
		deleteEventTask(CALENDAR07, selectDayOption.ONEDAY);
	}
}