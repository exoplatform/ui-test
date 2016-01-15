package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.TaskManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.testng.annotations.*;


public class Calendar_Search extends PlatformBase {
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
		magAc.signIn(USER_ROOT,PASS_ROOT);
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fullName = userData.fullName.get(0);
	}

	@AfterMethod
	public void afterMethod(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:115694.
	 * Test Case Name: Quick search.
	 * Pre-Condition: Some events/tasks contaning the word "dinner" already exists in Calendar
	 * Post-Condition: 
	 */
	@Test
	public  void test01_QuickSearch() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"dinnere115694"+" dinner";
		String titleTask = txData.getContentByArrayTypeRandom(1)+"dinnert115694"+ " dinner";
		String content = txData.getContentByArrayTypeRandom(1)+"115694";
		String defaultFormatDate="MM/dd/yyyy";
		String key="dinner";
		String key1="dinnertest";

		info("create data test");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.DETAILTIME);

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.LIST, selectDayOption.DETAILTIME);

		info("Test 1: Quick search");
		/*Step Number: 1
		 *Step Name: - Step 1: Quick search
		 *Step Description: 
			- Input the word "dinner" in search text box
			- Hit Enter
		 *Input Data: 

		 *Expected Outcome: 
			Return search results matching with search keyword with no error*/ 
		cHome.quickSearchCalendar(key);
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent));
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleTask));
		click(cHome.ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT);

		cHome.quickSearchCalendar(key1);
		waitForElementNotPresent(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent));
		waitForElementNotPresent(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleTask));
		click(cHome.ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT);

		info("Delete data");
		cHome.deleteEventTask(titleEvent, selectViewOption.LIST, selectDayOption.DETAILTIME,null);
		cHome.deleteEventTask(titleTask, selectViewOption.LIST, selectDayOption.DETAILTIME,null);
	}

	/**
	 * Case ID:115695.
	 * Test Case Name: Advanced search.
	 * Pre-Condition: Some events/tasks containing the word "dinner" already exist.
	 * Post-Condition: 
	 */
	@Test
	public  void test02_AdvancedSearch() {
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"dinnere115695"+" dinner";
		String titleTask = txData.getContentByArrayTypeRandom(1)+"dinnert115695"+" dinner";
		String content = txData.getContentByArrayTypeRandom(1)+"115695";
		String defaultFormatDate="MM/dd/yyyy";
		String key="dinner";
		String key1="dinnertest";

		info("create data test");
		hp.goToCalendarPage();
		event.goToAddEventFromActionBar();
		event.inputDataEventInQuickForm(titleEvent, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		event.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.LIST, selectDayOption.DETAILTIME);

		task.goToAddTaskFromActionBar();
		task.inputDataTaskInQuickForm(titleTask, content, getDate(0,defaultFormatDate), getDate(0,defaultFormatDate),false);
		task.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.LIST, selectDayOption.DETAILTIME);

		info("Test 2: Advanced search");
		/*Step Number: 1
		 *Step Name: Step 1: Advanced search
		 *Step Description: 
			- Input the word "dinner" in search text box
			- Press Enter
			- In search result screen, Click Advanced Search icon
			- Input search condition
			- Click Search
		 *Input Data: 

		 *Expected Outcome: 
			Return search result matching with search condition with no error*/ 
		cHome.quickSearchCalendar(key);
		cHome.advanceSearchCalendar(key);
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent));
		waitForAndGetElement(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleTask));
		click(cHome.ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT);

		cHome.quickSearchCalendar(key1);
		cHome.advanceSearchCalendar(key1);
		waitForElementNotPresent(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleEvent));
		waitForElementNotPresent(cHome.ELEMENT_EVENT_TASK_LIST_VIEW.replace("$name", titleTask));
		click(cHome.ELEMENT_BUTTON_CLOSE_QUICK_SEARCH_RESULT);

		info("Delete data");
		cHome.deleteEventTask(titleEvent, selectViewOption.LIST, selectDayOption.DETAILTIME,null);
		cHome.deleteEventTask(titleTask, selectViewOption.LIST, selectDayOption.DETAILTIME,null);

	}
}