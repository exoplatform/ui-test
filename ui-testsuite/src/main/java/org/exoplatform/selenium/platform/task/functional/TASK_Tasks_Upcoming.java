package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementTasks.optDueDate;
import org.testng.annotations.*;


	public class TASK_Tasks_Upcoming extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130897.</li>
	*<li> Test Case Name: Check Upcoming.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is further than tomorrow</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckUpcoming() {
		info("Test 1: Check Upcoming");
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
		mgTask.selectDueDate(task1, optDueDate.NextWeek);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.NextWeek);
		
		/*Step number: 2
		*Step Name: Step 2: Check Upcoming
		*Step Description: 
			- Click on Upcoming on left pane
		*Input Data: 
			
		*Expected Outcome: 
			filter tasks due in further.*/ 
		mgTask.checkTasksOfProject("Upcoming",tasks);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130899.</li>
	*<li> Test Case Name: Check Upcoming group by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is further than tomorrow</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckUpcomingGroupBy() {
		info("Test 2: Check Upcoming group by");
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
		mgTask.selectDueDate(task1, optDueDate.NextWeek);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.NextWeek);
		
		/*Step number: 2
		*Step Name: Step 2: Open Upcoming
		*Step Description: 
			- Click on Upcoming on left pane
		*Input Data: 
			
		*Expected Outcome: 
			Upcoming are opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Upcoming group by
		*Step Description: 
			- Click on filter Group By
		*Input Data: 
			
		*Expected Outcome: 
			Upcoming view allows to group by: None, Project, Label*/ 
		mgProject.checkGroupByInProjects("Upcoming", groups, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130900.</li>
	*<li> Test Case Name: Check Upcoming sort by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is further than tomorrow</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckUpcomingSortBy() {
		info("Test 3: Check Upcoming sort by");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = sortByData.getSortBy(1);
		String rank = sortByData.getSortBy(4);
		String priority = sortByData.getSortBy(3);
		String duedate = sortByData.getSortBy(0);
		String [] sorts={title,rank,priority,duedate};
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
		mgTask.selectDueDate(task1, optDueDate.NextWeek);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.NextWeek);
		
		/*Step number: 2
		*Step Name: Step 2: Open Upcoming
		*Step Description: 
			- Click on Upcoming on left pane
		*Input Data: 
			
		*Expected Outcome: 
			Upcoming are opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Upcoming sort by
		*Step Description: 
			- Click on filter Sort By
		*Input Data: 
			
		*Expected Outcome: 
			Upcoming view allows to sort by: Title, Priority, Rank, Due Date*/ 
		mgProject.checkSortByInProjects("Upcoming", sorts, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130898.</li>
	*<li> Test Case Name: Check Upcoming view default settings.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is further than tomorrow</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckUpcomingViewDefaultSettings() {
		info("Test 4: Check Upcoming view default settings");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String group = groupByData.getGroupBy(0);
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
		mgTask.editTaskAssignee(task1, DATA_USER1);
		mgTask.selectDueDate(task1, optDueDate.NextWeek);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.NextWeek);
		
		/*Step number: 2
		*Step Name: Step 2: Check Upcoming view default settings
		*Step Description: 
			- Click on Upcoming on left pane
		*Input Data: 
			
		*Expected Outcome: 
			On central pane, Upcoming view default settings are :
			- Group By : None
			- Sort By : Due Date*/ 
		mgProject.checkDefaultGroupSort("Upcoming",group,sort,false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}}