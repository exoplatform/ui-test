package org.exoplatform.selenium.platform.calendar.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectDayOption;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage.selectViewOption;
import org.exoplatform.selenium.platform.calendar.CalendarLocator;
import org.testng.annotations.*;


public class CAL_Task_Popover extends CAL_TestConfig{

	/**
	 *<li> Case ID:116532.</li>
	 *<li> Test Case Name: Edit task by click on popover.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_EditTaskByClickOnPopover() {
		info("Test 1: Edit task by click on popover");
		String titleTask = txData.getContentByArrayTypeRandom(1)+"1116532";
		String contentTask = txData.getContentByArrayTypeRandom(1)+"1116532";
		String titleTask2 = txData.getContentByArrayTypeRandom(1)+"2116532";
		String contentTask2 = txData.getContentByArrayTypeRandom(1)+"2116532";
		
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create a task
		 *Input Data: 
			- Click on Task button
			- Input task nfo
			- Click Save
		 *Expected Outcome: 
			Task is added successfully*/
		info("Add a task");
		hp.goToCalendarPage();
		tasMg.goToAddTaskFromActionBar();
		tasMg.inputDataTaskInQuickForm(titleTask, contentTask, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		tasMg.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: View task by rollover
		 *Input Data: 
			- Mouse focus on the created event at step 1
		 *Expected Outcome: 
			A small pop up display correct information of the added task*/
		cHome.goToEditTaskFromPopover(titleTask, selectViewOption.WEEK, selectDayOption.DETAILTIME);
		
		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Edit task by click on rollover pop up
		 *Input Data: 
			- Click on the task's popover
			- Modify and Save
		 *Expected Outcome: 
			- Task can be edited normally*/ 
		tasMg.inputDataTaskInDetailForm(titleTask2, contentTask2, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"), false);
		tasMg.saveAddTaskDetails();
		cHome.verifyIsPresentEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME);
		
		info("Delete data");
		cHome.deleteEventTask(titleTask2, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
	}

	/**
	 *<li> Case ID:116382.</li>
	 *<li> Test Case Name: View event without location info by popover.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_ViewEventWithoutLocationInfoByPopover() {
		info("Test 2: View event without location info by popover");
		String titleEvent = txData.getContentByArrayTypeRandom(1)+"116382";
		String contentEvent = txData.getContentByArrayTypeRandom(1)+"116382";

		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create a event
		 *Input Data: 
			- Click on Add Event button
			- Input all field except location
			- Click Save
		 *Expected Outcome: 
			Event is added successfully*/
		info("Add a task");
		hp.goToCalendarPage();
		evMg.goToAddEventFromActionBar();
		evMg.inputDataEventInQuickForm(titleEvent, contentEvent, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		evMg.saveQuickAddEvent();
		cHome.verifyIsPresentEventTask(titleEvent, selectViewOption.DAY, selectDayOption.DETAILTIME);

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: View event by popover
		 *Input Data: 
			- Mouse focus on the created event at step 1
		 *Expected Outcome: 
			A small pop up display correct information of the added event without location information*/ 
		mouseOver(cHome.getEventTaskElement(titleEvent, selectViewOption.WEEK, selectDayOption.DETAILTIME),true);
		waitForAndGetElement(CalendarLocator.ELEMENT_EVENT_POPOVER_TITLE_INFO.replace("$info", titleEvent));
		waitForAndGetElement(CalendarLocator.ELEMENT_EVENT_POPOVER_DESCRIPTION_INFO.replace("$info", contentEvent));
		waitForElementNotPresent(CalendarLocator.ELEMENT_EVENT_POPOVER_LOCATION_ELEMENT);
		
		info("Delete data");
		cHome.deleteEventTask(titleEvent, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
	}

	/**
	 *<li> Case ID:116531.</li>
	 *<li> Test Case Name: View task by popover.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_ViewTaskByPopover() {
		info("Test 3: View task by popover");
		String titleTask = txData.getContentByArrayTypeRandom(1)+"116531";
		String contentTask = txData.getContentByArrayTypeRandom(1)+"116531";
		
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create a task
		 *Input Data: 
			- Click on Add Task button
			- Input full task information
			- Click Save
		 *Expected Outcome: 
			Task is added successfully*/
		info("Add a task");
		hp.goToCalendarPage();
		tasMg.goToAddTaskFromActionBar();
		tasMg.moreDetailsTask();
		info("Add attachment");
		tasMg.inputBasicDetailTask(titleTask, contentTask);
		tasMg.saveAddTaskDetails();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: View task by popover
		 *Input Data: 
			- Mouse focus on the created event at step 1
		 *Expected Outcome: 
			A small pop up display correct information of the added task without User's availability status*/ 
		mouseOver(cHome.getEventTaskElement(titleTask, selectViewOption.WEEK, selectDayOption.DETAILTIME),true);
		waitForAndGetElement(CalendarLocator.ELEMENT_TASK_POPOVER_TITLE_INFO.replace("$info", titleTask));
		waitForAndGetElement(CalendarLocator.ELEMENT_TASK_POPOVER_DESCRIPTION_INFO.replace("$info", contentTask));
		
		info("Delete data");
		cHome.deleteEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
	}

	/**
	 *<li> Case ID:116383.</li>
	 *<li> Test Case Name: View task without description by popover.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_ViewTaskWithoutDescriptionByPopover() {
		info("Test 4: View task without description by popover");
		String titleTask = txData.getContentByArrayTypeRandom(1)+"116383";
	
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create a task
		 *Input Data: 
			- Click on Add Task button
			- Input all field except description
			- Click Save
		 *Expected Outcome: 
			Task is added successfully*/
		info("Add a task");
		hp.goToCalendarPage();
		tasMg.goToAddTaskFromActionBar();
		tasMg.inputDataTaskInQuickForm(titleTask, null, getDate(0,"MM/dd/yyyy"), getDate(0,"MM/dd/yyyy"),false);
		tasMg.saveQuickAddTask();
		cHome.verifyIsPresentEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME);
		
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: View task by popover
		 *Input Data: 
			- Mouse focus on the created event at step 1
		 *Expected Outcome: 
			A small pop up display correct information of the added task with Description*/ 
		mouseOver(cHome.getEventTaskElement(titleTask, selectViewOption.WEEK, selectDayOption.DETAILTIME),true);
		waitForAndGetElement(CalendarLocator.ELEMENT_TASK_POPOVER_TITLE_INFO.replace("$info", titleTask));
		waitForElementNotPresent(CalendarLocator.ELEMENT_TASK_POPOVER_DESCRIPTION_ELEMENT);
		
		info("Delete data");
		cHome.deleteEventTask(titleTask, selectViewOption.DAY, selectDayOption.DETAILTIME,null);
	}
}