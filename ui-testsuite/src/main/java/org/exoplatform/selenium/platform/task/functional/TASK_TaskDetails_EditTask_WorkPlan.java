package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_WorkPlan extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130958.</li>
	*<li> Test Case Name: Check All Day checkbox.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test01_CheckAllDayCheckbox() {
		info("Test 1: Check All Day checkbox");
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
		*Step Name: Step 3: Check All Day checkbox
		*Step Description: 
			- Click on work plan on task details to edit
		*Input Data: 
			
		*Expected Outcome: 
			By default an All Day checkbox is checked (equivalent to From time: 00:00, To time: 23:59). When unchecked, From: and To: time fields are revealed and can be changed by selecting from a combo list with the period interval 30 minutes.*/ 
		mgTask.checkAllDayCheckboxByDefault(task1);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130959.</li>
	*<li> Test Case Name: Check calculation of total working.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-163
	*/
	@Test (groups="pending")
	public  void test02_CheckCalculationOfTotalWorking() {
		info("Test 2: Check calculation of total working");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String time = welcomeMesData.getWelcomeMessage(21);
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
		mgTask.editTaskWorkPlan(task1, getDate(1,"dd"), 0, getDate(1,"dd"), 0, "00:00", "02:00",false);
		
		/*Step number: 3
		*Step Name: Step 3: Check calculation of total working
		*Step Description: 
			- Click on work plan on task details to edit time
		*Input Data: 
			
		*Expected Outcome: 
			The total working is calculated depending on input date time for From To field into days, hours and minute values.*/ 
		mgTask.checkDisplayOfWorkPlan(time,getDate(1, "dd MMM yyyy"));
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130960.</li>
	*<li> Test Case Name: Check display of date time plan.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-163
	*/
	@Test (groups="pending")
	public  void test03_CheckDisplayOfDateTimePlan() {
		info("Test 3: Check display of date time plan");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String time1 = welcomeMesData.getWelcomeMessage(21);
		String time2 = welcomeMesData.getWelcomeMessage(22);
		String time3 = welcomeMesData.getWelcomeMessage(23);
		String time4 = welcomeMesData.getWelcomeMessage(24);
		String time5 = welcomeMesData.getWelcomeMessage(25);
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
		*Step Name: Step 3: Check display of date time plan In case of some hours
		*Step Description: 
			- Click on work plan on task details to edit time
		*Input Data: 
			
		*Expected Outcome: 
			Work planned for $date ($hours hours)*/
		mgTask.editTaskWorkPlan(task1, getDate(1,"dd"), 0, getDate(1,"dd"), 0, "00:00", "02:00",false);
		mgTask.checkDisplayOfWorkPlan(time1,getDate(1, "dd MMM yyyy"));
		
		/*Step number: 4
		*Step Name: Step 4: Check display of date time plan In case of 1 hour
		*Step Description: 
			- Click on work plan on task details to edit time
		*Input Data: 
			
		*Expected Outcome: 
			Work planned for $date (1 hour)*/
		mgTask.editTaskWorkPlan(task1, getDate(1,"dd"), 0, getDate(1,"dd"), 0, "00:00", "01:00",false);
		mgTask.checkDisplayOfWorkPlan(time2,getDate(1, "dd MMM yyyy"));
		
		/*Step number: 5
		*Step Name: Step 5: Check display of date time plan In case of 30 minutes
		*Step Description: 
			- Click on work plan on task details to edit time
		*Input Data: 
			
		*Expected Outcome: 
			Work planned for $date (30 minutes)*/
		mgTask.editTaskWorkPlan(task1, getDate(1,"dd"), 0, getDate(1,"dd"), 0, "00:00", "00:30",false);
		mgTask.checkDisplayOfWorkPlan(time3,getDate(1, "dd MMM yyyy"));
		
		/*Step number: 6
		*Step Name: Step 6: Check display of date time plan In case of some hours and 30 minutes
		*Step Description: 
			- Click on work plan on task details to edit time
		*Input Data: 
			
		*Expected Outcome: 
			Work planned for $date ($hours hours 30 minutes)*/
		mgTask.editTaskWorkPlan(task1, getDate(1,"dd"), 0, getDate(1,"dd"), 0, "00:00", "02:30",false);
		mgTask.checkDisplayOfWorkPlan(time4,getDate(1, "dd MMM yyyy"));
		
		/*Step number: 7
		*Step Name: Step 7: Check display of date time plan In case of 1 day
		*Step Description: 
			- Click on work plan on task details to edit time
		*Input Data: 
			
		*Expected Outcome: 
			Work planned for $date (all day)*/
		mgTask.editTaskWorkPlan(task1, getDate(1,"dd"), 0, getDate(1,"dd"), 0, "", "",true);
		mgTask.checkDisplayOfWorkPlan(time5,getDate(1, "dd MMM yyyy"));
		
		/*Step number: 8
		*Step Name: Step 8: Check display of date time plan In case of some days, some hours
		*Step Description: 
			- Click on work plan on task details to edit time
		*Input Data: 
			
		*Expected Outcome: 
			Work planned from $fromdate to $todate ($hours hours) (In case from date and to date have the same month or year, do not display month and year of from date. E.g: Work planned from 12 to 13 Feb 2008 (19 hours)*/
		
		
		/*Step number: 9
		*Step Name: Step 9: Check display of date time plan In case of some days, some hours, 30 minutes
		*Step Description: 
			- Click on work plan on task details to edit time
		*Input Data: 
			
		*Expected Outcome: 
			Work planned from $fromdate to $todate ($hours hours 30 minutes)*/ 
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130956.</li>
	*<li> Test Case Name: Check No work planned is the default value.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130957.</li>
	*<li> Test Case Name: Check popover contains calendar + time fields in edit mode.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_06_CheckNoWorkPlannedIsTheDefaultValue() {
		info("Test 4: Check No work planned is the default value");
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
		*Step Name: Step 2: Check No work planned is the default value
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			- New task is created
			- No work planned is the default value.*/ 
		mgTask.addTaskDirectly(task1,true);
		mgTask.checkDefaultOfWorkPlan(task1);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130961.</li>
	*<li> Test Case Name: Check popover containing calendars + time fields are displayed exactly the value being edited.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created with working plan</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckPopoverContainingCalendarsTimeFieldsAreDisplayedExactlyTheValueBeingEdited() {
		info("Test 5: Check popover containing calendars + time fields are displayed exactly the value being edited");
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
		*Step Name: Step 2: Open task A
		*Step Description: 
			- Click on task A to open task details
		*Input Data: 
			
		*Expected Outcome: 
			Task details is opened*/
		mgTask.addTaskDirectly(task1,true);
		mgTask.editTaskWorkPlan(task1, getDate(1,"dd"),0, getDate(2,"dd"), 0, "00:00", "02:30", false);
		
		/*Step number: 3
		*Step Name: Step 3: Check popover containing calendars + time fields are displayed exactly the value being edited
		*Step Description: 
			- Click on work plan on task details to edit time of task A
		*Input Data: 
			
		*Expected Outcome: 
			The popover containing calendars + time fields are displayed exactly the value being edited.*/ 
		mgTask.checkPopupContainsCalendarTime(task1,getDate(1,"dd"),getDate(2,"dd"),"00:00", "02:30");
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130964.</li>
	*<li> Test Case Name: Check task when deleting the work plan.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created with due date and work plantask B is created with work plan but without due date</li>
	*<li> Post-Condition: </li>
	* NOT IMPLEMENT YET
	*/
	@Test (groups="pending")
	public  void test07_CheckTaskWhenDeletingTheWorkPlan() {
		info("Test 7: Check task when deleting the work plan");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgTask.addTaskDirectly(task1,true);
		mgTask.addTaskDirectly(task2,true);
		mgTask.editTaskWorkPlan(task2, getDate(0,"dd"), 0, getDate(1,"dd"), 0, "", "", true);
		mgTask.editTaskWorkPlan(task1, getDate(0,"dd"), 0, getDate(1,"dd"), 0, "", "", true);
		mgTask.setDueDate(task1, getDate(1,"dd MMM yyyy"), getDate(1,"dd"), 0);
		
		/*Step number: 2
		*Step Name: Step 2: Open task A
		*Step Description: 
			- Click on task A to open task details
		*Input Data: 
			
		*Expected Outcome: 
			Task details is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check task A when deleting the work plan
		*Step Description: 
			- Delete work plan of task A
		*Input Data: 
			
		*Expected Outcome: 
			task A should appear to due date in the Calendar app.*/
		mgTask.deleteTaskWorkPlan();
		
		/*Step number: 4
		*Step Name: Step 4: Open task B
		*Step Description: 
			- Click on task B to open task details
		*Input Data: 
			
		*Expected Outcome: 
			Task details is opened*/
		mgTask.openTask(task2);
		
		/*Step number: 5
		*Step Name: Step 5: Check task B when deleting the work plan
		*Step Description: 
			- Delete work plan of task B
		*Input Data: 
			
		*Expected Outcome: 
			task B is removed from the calendar app*/ 
		mgTask.deleteTaskWorkPlan();
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130962.</li>
	*<li> Test Case Name: Check when the work plan date time is updated.</li>
<<<<<<< HEAD
<<<<<<< HEAD
	*<li> Pre-Condition: exo-tasks add-on is installed -projectA has calendar integration-task A is created with work plan in projectA</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-251
=======
	*<li> Pre-Condition: exo-tasks add-on is installed
    *<li>      projectA has calendar integration
    *<li>      task A is created with work plan in projectA</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-334
>>>>>>> FQA-2695:[Task Management]- Write scripts for Labels - Create a label
=======
	*<li> Pre-Condition: exo-tasks add-on is installed
    *<li>      projectA has calendar integration
    *<li>      task A is created with work plan in projectA</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-334
>>>>>>> FQA-2696:[Task Management]- Write scripts for Labels - Edit a label
	*/
	@Test (groups="pending")
	public  void test08_CheckWhenTheWorkPlanDateTimeIsUpdated() {
		info("Test 8: Check when the work plan date time is updated");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "","", true);
		mgTask.addTask(prj1, task1);
		mgTask.editTaskWorkPlan(task1, getDate(0,"dd"), 0, getDate(1,"dd"), 0, "", "", true);
		
		/*Step number: 2
		*Step Name: Step 2: Uncheck Calendar Integration of projectA
		*Step Description: 
			- Open projectA
			- uncheck Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- projectA has not calendar*/
		mgProject.editProject(prj1, "", "", "", false);
		
		/*Step number: 3
		*Step Name: Step 3: Check taskA
		*Step Description: 
			- Open taskA
		*Input Data: 
			
		*Expected Outcome: 
			- in task detail, icon calendar before workplan cannot be clicked*/ 
		mgTask.checkClickableOfCalIcon(task1, false);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:130963.</li>
	*<li> Test Case Name: Check work plan can be cleared.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created with work plan</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckWorkPlanCanBeCleared() {
		info("Test 9: Check work plan can be cleared");
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
		*Step Name: Step 2: Open task A
		*Step Description: 
			- Click on task A to open task details
		*Input Data: 
			
		*Expected Outcome: 
			Task details is opened*/
		mgTask.addTaskDirectly(task1,true);
		mgTask.editTaskWorkPlan(task1, getDate(0,"dd"), 0, getDate(1,"dd"), 0, "", "", true);
		
		/*Step number: 3
		*Step Name: Step 3: Check work plan can be cleared
		*Step Description: 
			- Click on work plan on task details to edit time
			- Click on delete icon to clear work plan
		*Input Data: 
			
		*Expected Outcome: 
			Work plan is cleared*/ 
		mgTask.deleteTaskWorkPlan();
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}}