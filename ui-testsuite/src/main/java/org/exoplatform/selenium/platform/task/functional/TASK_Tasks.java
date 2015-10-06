package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementTasks.optDueDate;
import org.testng.annotations.*;


	public class TASK_Tasks extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130880.</li>
	*<li> Test Case Name: Check List view is default view of all filter.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test01_CheckListViewIsDefaultViewOfAllFilter() {
		info("Test 1: Check List view is default view of all filter");
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
		mgProject.addProject(prj1, "","", false);
		mgTask.addTask(prj1, task1);
		mgTask.setDueDate(task1, getDate(0, "dd MMM yyyy"), getDate(0, "dd"), 0);

		/*Step number: 3
		*Step Name: Step 3: Board view is not available in all views of Tasks
		*Step Description: 
			- Click on one view of Tasks to check: Today/Tomorrow/Incoming.....
		*Input Data: 
			
		*Expected Outcome: 
			Board view is not available in all views of Tasks*/ 
		mgProject.checkDisplayOfListBoard("Incoming",false);
		
		/*Step number: 2
		*Step Name: Step 2: Check List view is default view of all filter
		*Step Description: 
			- Click on one view to check
		*Input Data: 
			
		*Expected Outcome: 
			List view is default view of all filter*/
		mgProject.openProject(prj1);
		mgTask.checkDisplayOfListView(task1);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:130879.</li>
	*<li> Test Case Name: Check no task screen with each view filter.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added but removed afterthat , s
	*o no task left in the menu</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test02_CheckNoTaskScreenWithEachViewFilter() {
		info("Test 2: Check no task screen with each view filter");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		magAc.signOut();
		magAc.signIn(DATA_USER4, DATA_PASS);
		hp.goToTasks();
		mgTask.addTaskDirectly(task1, true);
		mgTask.deleteTask(task1);
		/*Step number: 2
		*Step Name: Step 2: Check no task screen with Incoming
		*Step Description: 
			- Click on All Tasks on left pane
		*Input Data: 
			
		*Expected Outcome: 
			No Task screen is displayed*/
		mgProject.checkNoTask("Incoming");
		
 	}

	/**
	*<li> Case ID:130877.</li>
	*<li> Test Case Name: Check tasks filtered.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added with due date: today,tomorrow, in the future, incoming...</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckTasksFiltered() {
		info("Test 3: Check tasks filtered");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task4 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task5 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();

		/*Step number: 2
		*Step Name: Step 2: Check tasks filtered: Incoming
		*Step Description: 
			- Click on Incoming on left pane
		*Input Data: 
			
		*Expected Outcome: 
			All tasks are not assigned to any project are listed*/
		mgTask.addTaskDirectly(task1, true);
		mgTask.addTaskDirectly(task2, true);
		mgTask.addTaskDirectly(task3, true);
		mgTask.addTaskDirectly(task4, true);
		mgTask.addTaskDirectly(task5, true);
		mgTask.setDueDate(task2, getDate(-2, "dd MMM yyyy"), getDate(-2, "dd"), 0);
		mgTask.selectDueDate(task3, optDueDate.Today);
		mgTask.selectDueDate(task4, optDueDate.Tomorrow);
		mgTask.selectDueDate(task5, optDueDate.NextWeek);
		
		/*Step number: 3
		*Step Name: Step 3: Check tasks filtered: All Tasks
		*Step Description: 
			- Click on All Tasks on left pane
		*Input Data: 
			
		*Expected Outcome: 
			All tasks are listed*/
		mgTask.checkTasksOfProject("All Tasks",task1);
		mgTask.deleteTask(task1);
		
		/*Step number: 4
		*Step Name: Step 4: Check tasks filtered: Overdue
		*Step Description: 
			- Click on Overdue on left pane
		*Input Data: 
			
		*Expected Outcome: 
			All tasks are overdue date are listed*/
		mgTask.checkTasksOfProject("Overdue",task2);
		mgTask.deleteTask(task2);
		
		/*Step number: 5
		*Step Name: Step 5: Check tasks filtered: Today
		*Step Description: 
			- Click on Today on left pane
		*Input Data: 
			
		*Expected Outcome: 
			All tasks are due today are listed*/
		mgTask.checkTasksOfProject("Today",task3);
		mgTask.deleteTask(task3);
		
		/*Step number: 6
		*Step Name: Step 6: Check tasks filtered: Tomorrow
		*Step Description: 
			- Click on Tomorrow on left pane
		*Input Data: 
			
		*Expected Outcome: 
			All tasks are due tomorrow are listed*/
		mgTask.checkTasksOfProject("Tomorrow",task4);
		mgTask.deleteTask(task4);
		
		/*Step number: 7
		*Step Name: Step 7: Check tasks filtered: Upcoming
		*Step Description: 
			- Click on Upcoming on left pane
		*Input Data: 
			
		*Expected Outcome: 
			All tasks are due in the future are listed*/ 
		mgTask.checkTasksOfProject("Upcoming",task5);
		mgTask.deleteTask(task5);
 	}

	/**
	*<li> Case ID:130878.</li>
	*<li> Test Case Name: Check welcome screen with each view filter.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedno task is added</li>
	*<li> Post-Condition: </li>
	* BUG
	*/
	@Test (groups="pending")
	public  void test04_CheckWelcomeScreenWithEachViewFilter() {
		info("Test 4: Check welcome screen with each view filter");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check welcome screen with each view filter
		*Step Description: 
			- Click on Projects/Label on left pane
		*Input Data: 
			
		*Expected Outcome: 
			welcome screen displays like Incoming first access*/ 
		mgProject.checkProjectsByDefault();
 	}}