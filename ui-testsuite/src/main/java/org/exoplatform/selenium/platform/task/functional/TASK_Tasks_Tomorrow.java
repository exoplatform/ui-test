package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementTasks.optDueDate;
import org.testng.annotations.*;


	public class TASK_Tasks_Tomorrow extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130893.</li>
	*<li> Test Case Name: Check Tomorrow.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is tomorrow</li>
	*<li> Post-Condition: </li>
	* https://jira.exoplatform.org/browse/TA-202
	*/
	@Test (groups="pending")
	public  void test01_CheckTomorrow() {
		info("Test 1: Check Tomorrow");
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
		mgTask.selectDueDate(task1, optDueDate.Tomorrow);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.Tomorrow);
		
		/*Step number: 2
		*Step Name: Step 2: Check Tomorrow
		*Step Description: 
			- Click on Tomorrow on left pane
		*Input Data: 
			
		*Expected Outcome: 
			filters tasks due tomorrow*/ 
		mgTask.checkTasksOfProject("Tomorrow",tasks);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130895.</li>
	*<li> Test Case Name: Check Tomorrow group by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is tomorrow</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-202
	*/
	@Test (groups="pending")
	public  void test02_CheckTomorrowGroupBy() {
		info("Test 2: Check Tomorrow group by");
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
		mgTask.selectDueDate(task1, optDueDate.Tomorrow);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.Tomorrow);
		
		/*Step number: 2
		*Step Name: Step 2: Open Tomorrow
		*Step Description: 
			- Click on Tomorrowon left pane
		*Input Data: 
			
		*Expected Outcome: 
			Tomorroware opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Tomorrowgroup by
		*Step Description: 
			- Click on filter Group By
		*Input Data: 
			
		*Expected Outcome: 
			Tomorrow view allows to group by: None, Project, Label.*/ 
		mgProject.checkGroupByInProjects("Tomorrow", groups, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130896.</li>
	*<li> Test Case Name: Check Tomorrow sort by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is tomorrow</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-202
	*/
	@Test (groups="pending")
	public  void test03_CheckTomorrowSortBy() {
		info("Test 3: Check Tomorrow sort by");
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
		mgTask.selectDueDate(task1, optDueDate.Tomorrow);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.Tomorrow);
		
		/*Step number: 2
		*Step Name: Step 2: Open Tomorrow
		*Step Description: 
			- Click onTomorrowon left pane
		*Input Data: 
			
		*Expected Outcome: 
			Tomorroware opened*/

		/*Step number: 3
		*Step Name: Step 3: CheckTomorrowsort by
		*Step Description: 
			- Click on filter Sort By
		*Input Data: 
			
		*Expected Outcome: 
			Tomorrow view allows to sort by: Title, Priority, Rank.*/ 
		mgProject.checkSortByInProjects("Tomorrow", sorts, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130894.</li>
	*<li> Test Case Name: Check Tomorrow view default settings.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and due date is tomorrow</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-202
	*/
	@Test (groups="pending")
	public  void test04_CheckTomorrowViewDefaultSettings() {
		info("Test 4: Check Tomorrow view default settings");
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
		mgTask.selectDueDate(task2, optDueDate.Tomorrow);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.selectDueDate(task2, optDueDate.Tomorrow);
		
		/*Step number: 2
		*Step Name: Step 2: Check Tomorrow view default settings
		*Step Description: 
			- Click on Tomorrow on left pane
		*Input Data: 
			
		*Expected Outcome: 
			On central pane, Tomorrow view default settings are :
			- Group By : None
			- Sort By : Priority*/ 
		mgProject.checkDefaultGroupSort("Tomorrow",group,sort,false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}}