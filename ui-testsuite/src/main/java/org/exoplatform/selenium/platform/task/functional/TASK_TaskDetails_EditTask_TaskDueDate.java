package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementTasks.optDueDate;
import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_TaskDueDate extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130934.</li>
	*<li> Test Case Name: Check case of selecting None.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test01_CheckCaseOfSelectingNone() {
		info("Test 1: Check case of selecting None");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check case of selecting None
		*Step Description: 
			- Click on Due Date but select None
		*Input Data: 
			
		*Expected Outcome: 
			No Due Date is displayed.*/ 
		mgTask.selectDueDate(task1, optDueDate.None);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130935.</li>
	*<li> Test Case Name: Check case of selecting Today, Tomorrow or Next Week.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130937.</li>
	*<li> Test Case Name: Check display of due date popover in case the being edited due date matches option.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-159
	*/
	@Test (groups="pending")
	public  void test02_05_CheckCaseOfSelectingTodayTomorrowOrNextWeek() {
		info("Test 2: Check case of selecting Today, Tomorrow or Next Week");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check case of selecting Today, Tomorrow or Next Week
		*Step Description: 
			- Click on Due Date to select Today, Tomorrow or Next Week
		*Input Data: 
			
		*Expected Outcome: 
			the due date is displayed with corresponding date*/ 
		mgTask.selectDueDate(task1, optDueDate.Today);
		mgTask.checkDisplayOfDueDatePopup(optDueDate.Today);
		mgTask.selectDueDate(task1, optDueDate.Tomorrow);
		mgTask.checkDisplayOfDueDatePopup(optDueDate.Tomorrow);
		mgTask.selectDueDate(task1, optDueDate.NextWeek);
		mgTask.checkDisplayOfDueDatePopup(optDueDate.NextWeek);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}	

	/**
	*<li> Case ID:130936.</li>
	*<li> Test Case Name: Check display of calendar in case a specific due date is defined.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckDisplayOfCalendarInCaseASpecificDueDateIsDefined() {
		info("Test 3: Check display of calendar in case a specific due date is defined");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check display of calendar in case a specific due date is defined
		*Step Description: 
			- Click on Due Date to select specific due date
		*Input Data: 
			
		*Expected Outcome: 
			the calendar is displayed exactly with the month, year that includes the edited date*/ 
		mgTask.setDueDate(task1, getDate(3,"dd MMM yyyy"),getDate(3,"dd"),0);
		mgTask.checkSelectedDay(getDate(3,"dd"));;
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130932.</li>
	*<li> Test Case Name: Check display of due date popover.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130931.</li>
	*<li> Test Case Name: Check No Due Date is default value.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test04_06_CheckDisplayOfDueDatePopover() {
		info("Test 4: Check display of due date popover");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			new task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check display of due date popover
		*Step Description: 
			- Click on Due date in task details pane to edit
		*Input Data: 
			
		*Expected Outcome: 
			due date popover displays 
			- calendar component 
			- and 4 more options:NoneTodayTomorrowNext Week*/ 
		mgTask.checkDisplayOfDueDatePopupByDefault(task1);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}
	
	/**
	*<li> Case ID:130933.</li>
	*<li> Test Case Name: Check the due date is displayed with the selected date from the calendar.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckTheDueDateIsDisplayedWithTheSelectedDateFromTheCalendar() {
		info("Test 7: Check the due date is displayed with the selected date from the calendar");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			new task is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check the due date is displayed with the selected date from the calendar
		*Step Description: 
			- Click on Due Date to select date
		*Input Data: 
			
		*Expected Outcome: 
			due date is displayed with the selected date from the calendar*/ 
		mgTask.setDueDate(task1, getDate(3,"dd MMM yyyy"),getDate(3,"dd"),0);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}}