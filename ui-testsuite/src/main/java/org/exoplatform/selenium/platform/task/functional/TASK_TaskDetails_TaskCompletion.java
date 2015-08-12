package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_TaskDetails_TaskCompletion extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128256.</li>
	*<li> Test Case Name: Check completion status in task details.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created and added to project A</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128257.</li>
	*<li> Test Case Name: Check completion status Uncompleted or Completed.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A,task B is created and added to project Aset Filter to view completed task</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128258.</li>
	*<li> Test Case Name: Check when hovering on the check box.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A,task B is created and added to project Aset Filter to view completed task</li>
	*<li> Post-Condition: </li>
	* NOT IMPLEMENT YET
	*/
	@Test (groups="pending")
	public  void test01_02_03_CheckCompletionStatusInTaskDetails() {
		info("Test 1: Check completion status in task details");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		*Step Name: Step 2: Open project A
		*Step Description: 
			- Click on project A on left pane to open
		*Input Data: 
			
		*Expected Outcome: 
			- task list of project A is opened*/
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check completion status in task details
		*Step Description: 
			- Click on task A on central pane to open
		*Input Data: 
			
		*Expected Outcome: 
			- Task completion status can be viewed and set on a checkbox in task details or task list*/ 
		mgTask.checkDisplayOfTaskCheckbox(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check completion status Uncompleted (uncheck)
		*Step Description: 
			- Click on task A on central pane to open
			- hover on the check box
		*Input Data: 
			
		*Expected Outcome: 
			- task detail is opened on right pane with Uncompleted status (uncheck checkbox)
			- "Mark as completed" tooltip*/

		/*Step number: 4
		*Step Name: Step 4: Check completion status Completed (check)
		*Step Description: 
			- Click on checkbox of task B
			- Click on task B on central pane
			- hover on the check box
		*Input Data: 
			
		*Expected Outcome: 
			- task detail is opened on right pane with Completed status (check checkbox)
			- "Mark as uncompleted" tooltip*/ 
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:128259.</li>
	*<li> Test Case Name: Check Board and List views do not display completed tasks.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created and added to project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckBoardAndListViewsDoNotDisplayCompletedTasks() {
		info("Test 4: Check Board and List views do not display completed tasks");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		*Step Name: Step 2: Open project A
		*Step Description: 
			- Click on project A on left pane to open
		*Input Data: 
			
		*Expected Outcome: 
			- task list of project A is opened*/
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check Board and List views do not display completed tasks
		*Step Description: 
			- Click on checkbox of task A to complete task
		*Input Data: 
			
		*Expected Outcome: 
			- task A is not displayed in List views anymore*/ 
		mgTask.completeTask(task1);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}}