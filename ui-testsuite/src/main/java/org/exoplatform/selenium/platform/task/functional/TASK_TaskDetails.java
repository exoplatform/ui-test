package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


	public class TASK_TaskDetails extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128254.</li>
	*<li> Test Case Name: Check display of task detail.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created and added to project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckDisplayOfTaskDetail() {
		info("Test 1: Check display of task detail");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String defaultStatus = flowData.getFlowByArrayTypeRandom(1);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "","", false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 2
		*Step Name: Step 2: Open project A
		*Step Description: 
			- Click on project A on left pane to open
		*Input Data: 
			
		*Expected Outcome: 
			- task list of project A is opened*/
		
		/*Step number: 3
		*Step Name: Step 3: Check display of task detail
		*Step Description: 
			- Click on task A on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Task details information is displayed in the right pane*/
		mgTask.checkTaskDetail(task1,true, prj1,defaultStatus);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}
	/**
	*<li> Case ID:130993.</li>
	*<li> Test Case Name: Check permalink in task detail.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckPermalinkInTaskDetail() {
		info("Test 2: Check permalink in task detail");
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
		*Step Name: Step 2: Check permalink in task detail
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			- task A is created
			- Task details should offer a permalink that allows to directly open a task. (chain icon at the end of the task title)*/
		mgTask.addTaskDirectly(task1, true);
		mgTask.checkTaskPermalink(task1);

		info("delete data");
		mgTask.deleteTask(task1);
 	}
	}