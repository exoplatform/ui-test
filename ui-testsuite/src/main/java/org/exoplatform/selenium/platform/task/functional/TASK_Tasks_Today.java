package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementTasks.optDueDate;
import org.testng.annotations.*;


	public class TASK_Tasks_Today extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130889.</li>
	*<li> Test Case Name: Check Today.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is today</li>
	*<li> Post-Condition: </li>
	* https://jira.exoplatform.org/browse/TA-202
	*/
	@Test (groups="pending")
	public  void test01_CheckToday() {
		info("Test 1: Check Today");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String [] tasks={task1,task2};
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
		mgTask.editTaskAssignee(task1, DATA_USER1);
		mgTask.selectDueDate(task1, optDueDate.Today);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.Today);
		
		/*Step number: 2
		*Step Name: Step 2: Check Today
		*Step Description: 
			- Click on Today on left pane
		*Input Data: 
			
		*Expected Outcome: 
			filters tasks due today.*/ 
		mgTask.checkTasksOfProject("Today",tasks);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130891.</li>
	*<li> Test Case Name: Check Today group by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is today</li>
	*<li> Post-Condition: </li>
	* https://jira.exoplatform.org/browse/TA-202
	*/
	@Test (groups="pending")
	public  void test02_CheckTodayGroupBy() {
		info("Test 2: Check Today group by");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String none = groupByData.getGroupBy(0);
		String project = groupByData.getGroupBy(5);
		//String label = groupByData.getGroupBy(2);
		String [] groups={none,project};
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
		mgTask.editTaskAssignee(task1, DATA_USER1);
		mgTask.selectDueDate(task1, optDueDate.Today);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.Today);
		
		/*Step number: 2
		*Step Name: Step 2: Open Today
		*Step Description: 
			- Click on Today on left pane
		*Input Data: 
			
		*Expected Outcome: 
			Today are opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Today group by
		*Step Description: 
			- Click on filter Group By
		*Input Data: 
			
		*Expected Outcome: 
			Today view allows to group by: None, Project, Label.*/ 
		mgProject.checkGroupByInProjects("Today", groups, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130892.</li>
	*<li> Test Case Name: Check Today sort by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is today</li>
	*<li> Post-Condition: </li>
	* https://jira.exoplatform.org/browse/TA-202
	*/
	@Test (groups="pending")
	public  void test03_CheckTodaySortBy() {
		info("Test 3: Check Today sort by");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = sortByData.getSortBy(1);
		String rank = sortByData.getSortBy(4);
		String priority = sortByData.getSortBy(3);
		String [] sorts={title,rank,priority};
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
		mgTask.editTaskAssignee(task1, DATA_USER1);
		mgTask.selectDueDate(task1, optDueDate.Today);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.Today);
		
		/*Step number: 2
		*Step Name: Step 2: Open Today
		*Step Description: 
			- Click on Today on left pane
		*Input Data: 
			
		*Expected Outcome: 
			Today are opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Today sort by
		*Step Description: 
			- Click on filter Sort By
		*Input Data: 
			
		*Expected Outcome: 
			Today view allows to sort by: Title, Priority, Rank.*/ 
		mgProject.checkSortByInProjects("Today", sorts, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130890.</li>
	*<li> Test Case Name: Check Today view default settings.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is today</li>
	*<li> Post-Condition: </li>
	* https://jira.exoplatform.org/browse/TA-202
	*/
	@Test (groups="pending")
	public  void test04_CheckTodayViewDefaultSettings() {
		info("Test 4: Check Today view default settings");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String group = groupByData.getGroupBy(0);
		String sort = sortByData.getSortBy(3);
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
		mgTask.editTaskAssignee(task1, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.Today);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.Today);
		
		/*Step number: 2
		*Step Name: Step 2: Check Today view default settings
		*Step Description: 
			- Click on Today on left pane
		*Input Data: 
			
		*Expected Outcome: 
			On central pane, Today view default settings are :
			- Group By : None
			- Sort By : Priority*/ 
		mgProject.checkDefaultGroupSort("Today",group,sort,false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}}