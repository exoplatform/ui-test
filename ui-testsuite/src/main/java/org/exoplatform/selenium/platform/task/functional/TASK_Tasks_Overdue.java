package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_Tasks_Overdue extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130885.</li>
	*<li> Test Case Name: Check Overdue.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added ,assign to me and overdue date</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckOverdue() {
		info("Test 1: Check Overdue");
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
		mgTask.setDueDate(task1, getDate(-2, "dd MMM yyyy"), getDate(-2, "dd"), 0);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.setDueDate(task2, getDate(-3, "dd MMM yyyy"), getDate(-3, "dd"), 0);
		
		/*Step number: 2
		*Step Name: Step 2: Check Overdue
		*Step Description: 
			- Click on Overdue on left pane
		*Input Data: 
			
		*Expected Outcome: 
			filters tasks with a past due date.*/ 
		mgTask.checkTasksOfProject("Overdue",tasks);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130887.</li>
	*<li> Test Case Name: Check Overdue group by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and overdue date</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckOverdueGroupBy() {
		info("Test 2: Check Overdue group by");
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
		mgTask.setDueDate(task1, getDate(-2, "dd MMM yyyy"), getDate(-2, "dd"), 0);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.setDueDate(task2, getDate(-3, "dd MMM yyyy"), getDate(-3, "dd"), 0);
		
		/*Step number: 2
		*Step Name: Step 2: Open Overdue
		*Step Description: 
			- Click on Overdue on left pane
		*Input Data: 
			
		*Expected Outcome: 
			Overdue are opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Overdue group by
		*Step Description: 
			- Click on filter Group By
		*Input Data: 
			
		*Expected Outcome: 
			Overdue view allows to group by: None, Project, Label.*/ 
		mgProject.checkGroupByInProjects("Overdue", groups, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130888.</li>
	*<li> Test Case Name: Check Overdue sort by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and overdue date</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckOverdueSortBy() {
		info("Test 3: Check Overdue sort by");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = sortByData.getSortBy(1);
		String duedate = sortByData.getSortBy(0);
		String priority = sortByData.getSortBy(3);
		String [] sorts={title,duedate,priority};
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
		mgTask.setDueDate(task1, getDate(-2, "dd MMM yyyy"), getDate(-2, "dd"), 0);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.setDueDate(task2, getDate(-3, "dd MMM yyyy"), getDate(-3, "dd"), 0);
		
		/*Step number: 2
		*Step Name: Step 2: Open Overdue
		*Step Description: 
			- Click on Overdue on left pane
		*Input Data: 
			
		*Expected Outcome: 
			Overdue are opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Overdue sort by
		*Step Description: 
			- Click on filter Sort By
		*Input Data: 
			
		*Expected Outcome: 
			Overdue view allows to sort by: Title, Priority, Due Date.*/ 
		mgProject.checkSortByInProjects("Overdue", sorts, false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}

	/**
	*<li> Case ID:130886.</li>
	*<li> Test Case Name: Check Overdue view default settings.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added,assign to me and overdue date</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckOverdueViewDefaultSettings() {
		info("Test 4: Check Overdue view default settings");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String group = groupByData.getGroupBy(5);
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
		mgTask.setDueDate(task1, getDate(-2, "dd MMM yyyy"), getDate(-2, "dd"), 0);
		mgTask.editTaskAssignee(task2, DATA_USER1);
		mgTask.setDueDate(task2, getDate(-3, "dd MMM yyyy"), getDate(-3, "dd"), 0);
		
		/*Step number: 2
		*Step Name: Step 2: Check Overdue view default settings
		*Step Description: 
			- Click on Overdue on left pane
		*Input Data: 
			
		*Expected Outcome: 
			On central pane, Overdue view default settings are
			- Group By : Project
			- Sort By : Due Date*/ 
		mgProject.checkDefaultGroupSort("Overdue",group,sort,false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
 	}}