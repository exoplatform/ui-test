package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_TaskTitle extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130927.</li>
	*<li> Test Case Name: Check a task can be saved with the duplicated title.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckATaskCanBeSavedWithTheDuplicatedTitle() {
		info("Test 1: Check a task can be saved with the duplicated title");
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
		*Step Name: Step 2: Check a task can be saved with the duplicated title
		*Step Description: 
			- Add New Task in Incoming with name: task A
		*Input Data: 
			
		*Expected Outcome: 
			- task A is created successfully*/ 
		mgTask.addTaskDirectly(task1,true);
		int id1= mgTask.getTaskId(mgTask.ELEMENT_TASK_TITLE.replace("$task", task1));
		mgTask.addTaskDirectly(task1,true);
		
		info("delete data");
		mgTask.deleteTaskById(id1);
		mgTask.deleteTaskById(id1+1);
 	}

	/**
	*<li> Case ID:130928.</li>
	*<li> Test Case Name: Check task title in central pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130929.</li>
	*<li> Test Case Name: Check task title in task details pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_03_CheckTaskTitleInCentralPane() {
		info("Test 2: Check task title in central pane");
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		for(int i=0;i<5;i++){
			title=title+txData.getContentByArrayTypeRandom(1);
		}
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check the task title field can be expanded
		*Step Description: 
			- Add New Task in Incoming with very long title
		*Input Data: 
			
		*Expected Outcome: 
			Task title is displayed on 1 row in the middle pane. In case it is longer than the width of middle pane, it is cut off + "..." automatically.*/ 
		/*Step number: 2
		*Step Name: Step 2: Check task title in task details pane
		*Step Description: 
			- Add New Task with very long title
		*Input Data: 
			
		*Expected Outcome: 
			Task title is displayed fully (multiple rows in case of long title)in the task details pane.*/
		mgTask.addTaskDirectly(title,true);
		mgTask.checkDisplayOfTitle(title);
		
		info("delete data");
		mgTask.deleteTask(title);
	}

	/**
	*<li> Case ID:130930.</li>
	*<li> Test Case Name: Check the check box in front of a task title.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckTheCheckBoxInFrontOfATaskTitle() {
		info("Test 4: Check the check box in front of a task title");
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
		*Step Name: Step 2: Check the check box in front of a task title
		*Step Description: 
			- Add New Task in Incoming
			- Check checkbox before task in central pane and details pane
		*Input Data: 
			
		*Expected Outcome: 
			- new task is created
			- The Mark as completed icon in front of a task title is to mark it done.*/ 
		mgTask.addTaskDirectly(task1,true);
		mgTask.checkDisplayOfTaskCheckbox(task1);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130926.</li>
	*<li> Test Case Name: Check Untitled Task is default value of a task title.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-147
	*/
	@Test (groups="pending")
	public  void test05_CheckUntitledTaskIsDefaultValueOfATaskTitle() {
		info("Test 5: Check Untitled Task is default value of a task title");
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
		*Step Name: Step 2: Add new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			new task is created*/ 
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check Untitled Task is default value of a task title
		*Step Description: 
			- Click on name of created task below, left name blank
		*Input Data: 
			
		*Expected Outcome: 
			Task is saved with name: Untitled Task*/ 
		mgTask.editTaskTitle(task1, "");
		
		info("delete data");
		mgTask.deleteTask("Untitled Task");
 	}}