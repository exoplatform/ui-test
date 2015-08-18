package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementTasks.optDueDate;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionGroupBy;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionSortBy;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionTask;
import org.testng.annotations.*;


	public class TASK_Tasks_Incoming extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128088.</li>
	*<li> Test Case Name: Check display of Incoming group by.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	*- add some tasks with different : Assignee, Label, Due Date</li>
	*<li> Post-Condition: </li>
	* NOT FULL IMPLEMENT
	*/
	@Test
	public  void test01_CheckDisplayOfIncomingGroupBy() {
		info("Test 1: Check display of Incoming group by");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open Incoming overview
		*Step Description: 
			- Click on Incoming overview on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			Incoming overview is opened*/
		mgTask.addTaskDirectly(task1,true);
		mgTask.addTaskDirectly(task2,true);
		mgTask.addTaskDirectly(task3,true);
		mgTask.editTaskAssignee(task1, DATA_USER1);
		mgTask.editTaskAssignee(task2, DATA_USER2);
		mgTask.editTaskAssignee(task3, DATA_USER2);
		mgTask.selectDueDate(task1, optDueDate.Today);
		mgTask.selectDueDate(task2, optDueDate.Today);
		mgTask.selectDueDate(task3, optDueDate.Tomorrow);
		
		/*Step number: 4
		*Step Name: Step 4: Group by Assignee
		*Step Description: 
			- Choose Assignee from Group by list
		*Input Data: 
			
		*Expected Outcome: 
			Task is grouped by Assignee*/
		mgTask.selectOptGroupBy(optionGroupBy.Assignee);
		mgTask.checkGroupBy(true, DATA_NAME_USER1, 1);
		mgTask.checkGroupBy(true, DATA_NAME_USER2, 2);
		
		/*Step number: 3
		*Step Name: Step 3: Group by None
		*Step Description: 
			- Choose None from Group by list
		*Input Data: 
			
		*Expected Outcome: 
			Task is not grouped*/
		mgTask.selectOptGroupBy(optionGroupBy.None);
		mgTask.checkGroupBy(false, DATA_NAME_USER1, 1);
		
		/*Step number: 5
		*Step Name: Step 5: Group by Label
		*Step Description: 
			- Choose Label from Group by list
		*Input Data: 
			
		*Expected Outcome: 
			Task is grouped by Label*/

		/*Step number: 6
		*Step Name: Step 6: Group by Due Date
		*Step Description: 
			- Choose Due Date from Group by list
		*Input Data: 
			
		*Expected Outcome: 
			Task is grouped by Due Date*/ 
		mgTask.selectOptGroupBy(optionGroupBy.Due_Date);
		mgTask.checkGroupBy(true, "Today", 2);
		mgTask.checkGroupBy(true, "Tomorrow", 1);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
		mgTask.deleteTask(task3);
 	}

	/**
	*<li> Case ID:128087.</li>
	*<li> Test Case Name: Check display of Incoming sort by.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- add some tasks with different :Title, Created Date, Due Date, Priority</li>
	*<li> Post-Condition: </li>
	*BUG:https://jira.exoplatform.org/browse/TA-211
	*/
	@Test (groups="pending")
	public  void test02_CheckDisplayOfIncomingSortBy() {
		info("Test 2: Check display of Incoming sort by");
		String task1 = "a"+txData.getContentByArrayTypeRandom(1);
		String task2 = "b"+txData.getContentByArrayTypeRandom(1);
		String task3 = "c"+txData.getContentByArrayTypeRandom(1);
		String[] tasks = {task1,task2,task3};
		String high = priorityData.getPriorityByArrayTypeRandom(2);
		String normal = priorityData.getPriorityByArrayTypeRandom(3);
		String low = priorityData.getPriorityByArrayTypeRandom(4);
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
		mgTask.addTaskDirectly(task3,true);
		mgTask.selectDueDate(task1, optDueDate.Today);
		mgTask.selectDueDate(task2, optDueDate.Tomorrow);
		mgTask.selectDueDate(task3, optDueDate.NextWeek);
		mgTask.editTaskPriority(task1, low);
		mgTask.editTaskPriority(task2, normal);
		mgTask.editTaskPriority(task3, high);
		
		/*Step number: 2
		*Step Name: Step 2: Open Incoming overview
		*Step Description: 
			- Click on Incoming overview on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			Incoming overview is opened*/
		
		/*Step number: 3
		*Step Name: Step 3: Sort by Title
		*Step Description: 
			- Choose Title from Sort by list
		*Input Data: 
			
		*Expected Outcome: 
			Task is sorted by alphanumeric*/
		mgTask.selectOptSortBy(optionSortBy.Title);
		mgTask.checkSortByTitle(tasks);

		/*Step number: 4
		*Step Name: Step 4: Sort by Created Date
		*Step Description: 
			- Choose Created Date from Sort by list
		*Input Data: 
			
		*Expected Outcome: 
			Task is sorted: most recent on top*/
		mgTask.selectOptSortBy(optionSortBy.Created_Date);
		mgTask.checkSortByCreatedDate(tasks);
		
		/*Step number: 5
		*Step Name: Step 5: Sort by Due Date
		*Step Description: 
			- Choose Due Date from Sort by list
		*Input Data: 
			
		*Expected Outcome: 
			Task is sorted: oldest first*/
		mgTask.selectOptSortBy(optionSortBy.Due_Date);
		mgTask.checkSortByTitle(tasks);
		
		/*Step number: 6
		*Step Name: Step 6: Sort by Priority
		*Step Description: 
			- Choose Priorityfrom Sort by list
		*Input Data: 
			
		*Expected Outcome: 
			Task is sorted: high priority on top*/ 
		mgTask.selectOptSortBy(optionSortBy.Priority);
		mgTask.checkSortByCreatedDate(tasks);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
		mgTask.deleteTask(task3);
 	}

	/**
	*<li> Case ID:128086.</li>
	*<li> Test Case Name: Check Incoming default settings.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckIncomingDefaultSettings() {
		info("Test 3: Check Incoming default settings");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String none = groupByData.getGroupBy(0);
		String createdDate = sortByData.getSortBy(2);
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
		mgTask.addTaskDirectly(task3,true);
		
		/*Step number: 2
		*Step Name: Step 2: Open Incoming overview
		*Step Description: 
			- Click on Incoming overview on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			Incoming overview is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Incoming default settings
		*Step Description: 
			- Check Incoming default settings of Group by and Sort by
		*Input Data: 
			
		*Expected Outcome: 
			- Group by: None 
			- Sort by: Created Date*/ 
		mgProject.checkDefaultGroupSort("Incoming", none, createdDate,false);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
		mgTask.deleteTask(task3);
 	}

	/**
	*<li> Case ID:128083.</li>
	*<li> Test Case Name: Check Incoming displays all tasks that are not assigned to a project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128084.</li>
	*<li> Test Case Name: Check Incoming's layout and behaviour.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are added</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_06_CheckIncomingDisplaysAllTasksThatAreNotAssignedToAProject() {
		info("Test 4: Check Incoming displays all tasks that are not assigned to a project");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgTask.selectOptionTask(optionTask.All_Tasks);
		
		/*Step number: 2
		*Step Name: Step 2: Create Task but not assigned to a project
		*Step Description: 
			- Click on New Task button on the central pane
			- Add a task but not assigned to a project
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1,false);
		
		/*Step number: 3
		*Step Name: Step 3: Check task in Incoming overview
		*Step Description: 
			- Click on Incoming overview on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			New task is added to list of Incoming on the central pane*/ 
		/*Step number: 2
		*Step Name: Step 2: Open Incoming overview
		*Step Description: 
			- Click on Incoming overview on the left pane
			- Check Incoming's layout and behaviour
		*Input Data: 
			
		*Expected Outcome: 
			Incomingoffers a list layout and behaviour similar to the List view*/ 
		mgTask.selectOptionTask(optionTask.Incoming);
		mgTask.selectDueDate(task1, optDueDate.Today);
		driver.navigate().refresh();
		mgTask.checkDisplayOfListView(task1);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:128085.</li>
	*<li> Test Case Name: Check Incoming hides completed tasks.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckIncomingHidesCompletedTasks() {
		info("Test 5: Check Incoming hides completed tasks");
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
		*Step Name: Step 2: Open Incoming overview
		*Step Description: 
			- Click on Incoming overview on the left pane
		*Input Data: 
			
		*Expected Outcome: 
			Incoming overview is opened*/

		/*Step number: 3
		*Step Name: Step 3: Create Task in Incoming overview
		*Step Description: 
			- Click on Incoming overview on the left pane
			- Click on New Task button on the central pane
			- Add a task
		*Input Data: 
			
		*Expected Outcome: 
			Task is added to the list*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 4
		*Step Name: Step 4: Check Incoming hides completed tasks
		*Step Description: 
			- Click on checkbox after task name
		*Input Data: 
			
		*Expected Outcome: 
			Task is changed to completed so it's hidden from the list*/ 
		mgTask.completeTask(task1);
	}
}