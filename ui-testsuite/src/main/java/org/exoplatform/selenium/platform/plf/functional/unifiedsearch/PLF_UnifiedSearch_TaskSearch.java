package org.exoplatform.selenium.platform.plf.functional.unifiedsearch;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.HomePageGadget;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.calendar.CalendarBase;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.testng.annotations.*;
import org.exoplatform.selenium.Utils;


public class PLF_UnifiedSearch_TaskSearch extends CalendarBase {


	ManageAccount magAcc;
	ManageMember magMember;
	Event evt;
	Task task;
	SettingSearchPage qsPage;
	SpaceManagement spaceMag;
	HomePageGadget hGadget; 
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
		hGadget = new HomePageGadget(driver);
		magMember = new ManageMember(driver,this.plfVersion);
		evt = new Event(driver, this.plfVersion);
		task = new Task(driver, this.plfVersion);
		qsPage = new SettingSearchPage(driver);
		spaceMag = new SpaceManagement(driver);
		button = new Button(driver);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();		
		driver.quit();
	}


	/*
	 * ==Click on task should open event details in calendar application==
	 * Test ID :104365
	 * Step : Search for diner and click on task result
	 */
	@Test
	public void test01_ClickOnTaskShouldOpenEventDetailsInCalendarApplication(){
		String taskName="Diner";
		String description="Diner with family";
		info("Start test 104365 : Click on task should open event details in calendar application");
		/*
		 * pre conditions
		 */
		goToCalendarPage();
		info("Add a task which match with Diner");
		task.addQuickTask(taskName,description,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),false);

		/*
		 * step 1 : search for Diner
		 */
		qsPage.quickSearch(taskName);
		waitForAndGetElement(qsPage.ELEMENT_RESULT_ITEM.replace("${keySearch}", taskName)).click();
		waitForAndGetElement(task.ELEMENT_TASK_PREVIEW_DESCRIPTION.replace("${keySearch}", description));
		button.close();

		/*
		 * Delete datas
		 */
		task.deleteEventTask(taskName);

	}

	/*
	 * ==Search on cancelled tasks of users calendars==
	 * Test ID :104368
	 * Step 1 : create a task with cancelled status and then search this one in search box
	 */
	@Test
	public void test02_SearchOnCancelledTasksOfUsersCalendars(){
		String taskName="task cancel";
		String description="This task is down";
		info("Start test 104368 : Search on cancelled tasks of users calendars ");
		/*
		 * Step one : Add an event with canceled status and search it
		 */
		goToCalendarPage();
		info("Add a task canceled");
		task.goToAddTaskFromActionBar();
		click(task.ELEMENT_BUTTON_TASK_MORE_DETAILS);
		task.inputDataTabDetailTask(taskName, description, getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"), false, null, "John Smith",null, "Canceled");
		click(task.ELEMENT_BUTTON_TASK_SAVE_DETAILS);
		goToCalendarPage();
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		type(ELEMENT_QUICK_SEARCH_TEXTBOX,taskName,true);
		Utils.pause(2000);
		info("Look if the task is in the search box");
		waitForElementNotPresent((qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$result}", taskName).replace("{$position}", "1")));
		/*
		 * Delete data
		 */
		info("Delete task");
		task.deleteEventTask(taskName);
	}

	/*
	 * ==Search On Completed Tasks Of Users Calendars==
	 * Test ID : 104367
	 * Step 1 : create a task with completed status and then search this one in search box
	 */
	@Test
	public void test03_SearchOnCompletedTasksOfUsersCalendars(){
		String taskName="task comp";
		String description="This task is comp";
		info("Start test 104367 : Search On Completed Tasks Of Users Calendars");
		/*
		 * Step one : Add an event with completed status and search it
		 */
		goToCalendarPage();
		info("Add a task completed");
		task.goToAddTaskFromActionBar();
		click(task.ELEMENT_BUTTON_TASK_MORE_DETAILS);
		task.inputDataTabDetailTask(taskName, description, getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"), false, null, "John Smith",null, "Completed");
		click(task.ELEMENT_BUTTON_TASK_SAVE_DETAILS);
		goToCalendarPage();
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		type(ELEMENT_QUICK_SEARCH_TEXTBOX,taskName,true);
		Utils.pause(2000);
		info("Look if the task is in the search box");
		waitForElementNotPresent((qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS
				.replace("{$result}", taskName).replace("{$position}", "1")));

		/*
		 * Delete data
		 */
		info("Delete task");
		task.deleteEventTask(taskName);
	}

	/*
	 * ==Search on in progress tasks of users calendars==
	 * ID Test : 104366
	 * Step 1 : create a task with in progress status and then search this one in search box
	 */
	@Test
	public void test04_SearchOnInProgressTasksOfUsersCalendars(){
		String taskName="task progr";
		String description="This task is in progress";
		info("Start test 104366 : Search On in progress Tasks Of Users Calendars");
		/*
		 * Step one : Add an event with In progress status and search it
		 */
		goToCalendarPage();
		info("Add a task in progress");
		task.goToAddTaskFromActionBar();
		click(task.ELEMENT_BUTTON_TASK_MORE_DETAILS);
		task.inputDataTabDetailTask(taskName, description, getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"), false, null, "John Smith",null, "In Progress");
		click(task.ELEMENT_BUTTON_TASK_SAVE_DETAILS);
		goToCalendarPage();
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		type(ELEMENT_QUICK_SEARCH_TEXTBOX,taskName,true);
		Utils.pause(2000);
		info("Look if the task is in the search box");
		waitForAndGetElement((qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$result}", taskName).replace("{$position}", "1")));

		/*
		 * Delete data
		 */
		info("Delete task");
		task.deleteEventTask(taskName);

	}

	/*
	 * ==Search on needs action tasks of users calendars==
	 * ID Test : 104363
	 * Step 1 : create a task with needs action and and then search this one in search box
	 */
	@Test
	public void test05_SearchOnNeedsActionTasksOfUsersCalendars(){
		String taskName="task need";
		String description="This task need action";
		info("Start test 104363 : Search on needs action tasks of users calendars");
		/*
		 * Step one : Add an event with needs action and search it
		 */
		goToCalendarPage();
		info("Add a task with need action");
		task.goToAddTaskFromActionBar();
		click(task.ELEMENT_BUTTON_TASK_MORE_DETAILS);
		task.inputDataTabDetailTask(taskName, description, getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"), false, null, "John Smith",null, "Needs Action");
		click(task.ELEMENT_BUTTON_TASK_SAVE_DETAILS);
		goToCalendarPage();
		click(ELEMENT_QUICK_SEARCH_ICON);
		Utils.pause(2000);
		type(ELEMENT_QUICK_SEARCH_TEXTBOX,taskName,true);
		Utils.pause(2000);
		info("Look if the task is in the search box");
		waitForAndGetElement((qsPage.ELEMENT_QUICK_SEARCH_BOX_RESULTS.replace("{$result}", taskName).replace("{$position}", "1")));

		/*
		 * Delete data
		 */
		info("Delete task");
		task.deleteEventTask(taskName);

	}

	/*
	 * ==task fields weight order==
	 * ID Test : 104364
	 * Step 1 : search for diner
	 * https://jira.exoplatform.org/browse/CAL-1007
	 */
	@Test (groups="pending")
	public void test06_TaskFieldsWeightOrder(){
		String diner="Diner";
		String taskname2="task 2";
		String description="Task 1";
		info("Start test 104364 : task fields weight order");
		/*
		 * pre condition
		 */
		goToCalendarPage();
		info("Add a task with diner in title");
		task.addQuickTask(diner,description,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),false);
		info("Add a task with diner in the note");
		task.addQuickTask(taskname2,diner,getDate(1,"MM/dd/yyyy"),getDate(1,"MM/dd/yyyy"),false);

		driver.navigate().refresh();
		Utils.pause(2000);

		/*
		 * Step one : search for Diner
		 */
		qsPage.quickSearch(diner);
		info("Search for Diner");
		// Look of the task one named " Diner " is the first element
		waitForAndGetElement((qsPage.ELEMENT_RESULT_ITEM_ORDER_BY.replace("${keySearch}", diner).replace("{$index}","2")));
		// Look of the task two is the second element
		waitForAndGetElement((qsPage.ELEMENT_RESULT_ITEM_ORDER_BY.replace("${keySearch}", taskname2).replace("{$index}","1")));

		goToCalendarPage();

		/*
		 * Delete data
		 */
		info("Delete task");
		task.deleteEventTask(diner);
		task.deleteEventTask(taskname2);
	}

}

