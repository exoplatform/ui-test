package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.task.ManagementTasks.optionSortBy;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionTask;

import org.testng.annotations.*;


	public class TASK_TaskDetails_CreateTask extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128266.</li>
	*<li> Test Case Name: Check a task can be created by 2 ways.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckATaskCanBeCreatedBy2Ways() {
		info("Test 1: Check a task can be created by 2 ways");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Create task by clicking on New Task button
		*Step Description: 
			- Click on New Task button, the mouse is focused on the blank field below to input task title.
		*Input Data: 
			
		*Expected Outcome: 
			- new task is created*/
		mgTask.addTask(project, task1);
	
		/*Step number: 3
		*Step Name: Step 3: Create task by clicking directly on the blank field
		*Step Description: 
			- Click directly on the blank field on the top of the pane to input task title.
		*Input Data: 
			
		*Expected Outcome: 
			- new task is created*/ 
        mgTask.addTaskDirectly(task2,true);

        info("delete data");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:128271.</li>
	*<li> Test Case Name: Check creating task action.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128272.</li>
	*<li> Test Case Name: Check display of new task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_03_CheckCreatingTaskAction() {
		info("Test 2: Check creating task action");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String defaultStatus = flowData.getFlowByArrayTypeRandom(1);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Check display of new task
		*Step Description: 
			- Input new task and Enter on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- The new added task will be displayed on the top below Untitled Task field.*/
		mgTask.addTask(project, task1);
		mgTask.checkTaskDetail(task1,true,project,defaultStatus);
		
		info("delete data");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:128276.</li>
	*<li> Test Case Name: Check display of tasks when the page is refreshed.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedsome tasks are created in Incoming</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-213
	*/
	@Test (groups="pending")
	public  void test04_CheckDisplayOfTasksWhenThePageIsRefreshed() {
		info("Test 4: Check display of tasks when the page is refreshed");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = sortByData.getSortBy(1);
		String[] tasks = {task1,task2,task3};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task in Incoming
		*Step Description: 
			- Input new task on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- New task is created*/
		mgTask.addTaskDirectly(task1, true);
		mgTask.addTaskDirectly(task2, true);
		mgTask.selectOptSortBy(optionSortBy.Title);
		
		/*Step number: 3
		*Step Name: Step 3: Check display of tasks when the page is refreshed
		*Step Description: 
			- Refresh the page
		*Input Data: 
			
		*Expected Outcome: 
			- The added tasks will be rearranged following the Group By & Sort By options.*/ 
		mgTask.addTaskDirectly(task3, true);
		driver.navigate().refresh();
		mgTask.checkSortByTitle(tasks);
		waitForAndGetElement(mgProject.ELEMENT_SORTBY_ITEM.replace("$sort",title));
		
		info("delete task");
		mgTask.deleteTask(task3);
		mgTask.deleteTask(task2);
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:128270.</li>
	*<li> Test Case Name: Check position to add task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is createdlabel A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128274.</li>
	*<li> Test Case Name: Check when a task is created in a specific project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_09_CheckPositionToAddTask() {
		info("Test 5: Check position to add task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create task from Incoming
		*Step Description: 
			- Click on Incoming on left pane
			- Input new task on central pane and Enter
		*Input Data: 
			
		*Expected Outcome: 
			- new task í created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Create task from project A
		*Step Description: 
			- Click on project A on left pane
			- Input new task on central pane and Enter
		*Input Data: 
			
		*Expected Outcome: 
			- new task í created*/
		mgProject.addProject(prj1,"", false);
		mgTask.addTask(prj1, task2);
		
		/*Step number: 4
		*Step Name: Step 4: Create task from label A
		*Step Description: 
			- Click on label A on left pane
			- Input new task on central pane and Enter
		*Input Data: 
			
		*Expected Outcome: 
			- new task í created*/ 
		info("delete data");
		mgTask.selectOptionTask(optionTask.Incoming);
		mgTask.deleteTask(task1);
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:128267.</li>
	*<li> Test Case Name: Check right pane when creating a task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128268.</li>
	*<li> Test Case Name: Check task detail form when a title is input in the blank row.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_07_CheckRightPaneWhenCreatingATask() {
		info("Test 6: Check right pane when creating a task");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 2
		*Step Name: Step 2: Check right pane when creating a task
		*Step Description: 
			- Enter a new task on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- the right pane is opened with task details that can be editable.*/ 
		mgTask.editTaskTitle(task1, title);
		mgTask.editTaskDescription(title, des);
		
		info("delete task");
		mgTask.selectOptionTask(optionTask.Incoming);
		mgTask.deleteTask(title);
 	}

	/**
	*<li> Case ID:128269.</li>
	*<li> Test Case Name: Check the arrow menu on the top of task detail pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckTheArrowMenuOnTheTopOfTaskDetailPane() {
		info("Test 8: Check the arrow menu on the top of task detail pane");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Create task A on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- task A is created*/
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check the arrow menu on the top of task detail pane
		*Step Description: 
			- on right pane, click on arrow menu
		*Input Data: 
			
		*Expected Outcome: 
			- The arrow menu on the top of task detail pane to display other actions: Clone, Watch, Delete.*/ 
		info("delete data");
		mgTask.deleteTask(task1);
 	}
}