package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_Tasks_AllTasks extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130881.</li>
	*<li> Test Case Name: Check All Tasks.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added and assign to me</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckAllTasks() {
		info("Test 1: Check All Tasks");
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
		
		/*Step number: 2
		*Step Name: Step 2: Check All Tasks
		*Step Description: 
			- Click on All Tasks on left pane
		*Input Data: 
			
		*Expected Outcome: 
			unfiltered all tasks which are assigned to me*/ 
		mgTask.checkTasksOfProject("All Tasks",tasks);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130883.</li>
	*<li> Test Case Name: Check All Tasks group by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added and assign to me</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckAllTasksGroupBy() {
		info("Test 2: Check All Tasks group by");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String none = groupByData.getGroupBy(0);
		String project = groupByData.getGroupBy(5);
		//String label = groupByData.getGroupBy(2);
		String duedate = groupByData.getGroupBy(1);
		String [] groups={none,project,duedate};
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
		
		/*Step number: 2
		*Step Name: Step 2: Open All Tasks
		*Step Description: 
			- Click on All Tasks on left pane
		*Input Data: 
			
		*Expected Outcome: 
			All Tasks are opened*/

		/*Step number: 3
		*Step Name: Step 3: Check All Tasks group by
		*Step Description: 
			- Click on filter Group By
		*Input Data: 
			
		*Expected Outcome: 
			All Tasks view allows to group by: None, Project, Label, Due Date.*/ 
		mgProject.checkGroupByInProjects("All Tasks", groups, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130884.</li>
	*<li> Test Case Name: Check All Tasks sort by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added and assign to me</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckAllTasksSortBy() {
		info("Test 3: Check All Tasks sort by");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = sortByData.getSortBy(1);
		String status = sortByData.getSortBy(5);
		String duedate = sortByData.getSortBy(0);
		String priority = sortByData.getSortBy(3);
		String rank = sortByData.getSortBy(4);
		String [] sorts={title,status,duedate,priority,rank};
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
		
		/*Step number: 2
		*Step Name: Step 2: Open All Tasks
		*Step Description: 
			- Click on All Tasks on left pane
		*Input Data: 
			
		*Expected Outcome: 
			All Tasks are opened*/

		/*Step number: 3
		*Step Name: Step 3: Check All Tasks sort by
		*Step Description: 
			- Click on filter Sort By
		*Input Data: 
			
		*Expected Outcome: 
			All Tasks view allows to sort by: Title, Status, Due Date, Priority, Rank.*/ 
		mgProject.checkSortByInProjects("All Tasks", sorts, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130882.</li>
	*<li> Test Case Name: Check All Tasks view default settings.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added and assign to me</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckAllTasksViewDefaultSettings() {
		info("Test 4: Check All Tasks view default settings");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String group = groupByData.getGroupBy(1);
		String sort = sortByData.getSortBy(0);
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
		
		/*Step number: 2
		*Step Name: Step 2: Check All Tasks view default settings
		*Step Description: 
			- Click on All Tasks on left pane
		*Input Data: 
			
		*Expected Outcome: 
			On central pane, All Tasks view default settings are :
			- Group By : Due Date
			- Sort By : Due Date*/ 
		mgProject.checkDefaultGroupSort("All Tasks",group,sort,false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}}