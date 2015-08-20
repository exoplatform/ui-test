package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_TaskStatus extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130991.</li>
	*<li> Test Case Name: Check status of task when a task is added to a project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130992.</li>
	*<li> Test Case Name: Check status of task when a task is moved to another project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_CheckStatusOfTaskWhenATaskIsAddedToAProject() {
		info("Test 1: Check status of task when a task is added to a project");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String status = flowData.getFlowByArrayTypeRandom(1);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj2, "", false);
		mgProject.addProject(prj1, "", false);
		
		/*Step number: 2
		*Step Name: Step 2: Check status of task when a task is added to a project
		*Step Description: 
			- Add task A to project A
		*Input Data: 
			
		*Expected Outcome: 
			- task A is created
			- the task status is automatically set to the first status of the project workflow*/ 
		mgTask.addTask(prj1, task1);
		mgTask.checkDefaultTaskStatus(task1,status,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check status of task when a task is moved to another project
		*Step Description: 
			- Change project path of task A to another project
		*Input Data: 
			
		*Expected Outcome: 
			- task is setted to the same status in the new project workflow.
			- If not status matches in the new project workflow, the status is set to the first status in the new project workflow.*/
		mgTask.searchTaskProject(task1, prj2);
		mgTask.checkDefaultTaskStatus(task1,status,true);
		
		info("delete data");
		mgProject.deleteProject(prj2, false);
		mgProject.deleteProject(prj1, false);
	}

	/**
	*<li> Case ID:130990.</li>
	*<li> Test Case Name: Check status of task when creating outside a project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckStatusOfTaskWhenCreatingOutsideAProject() {
		info("Test 3: Check status of task when creating outside a project");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String status = flowData.getFlowByArrayTypeRandom(1);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check status of task when creating outside a project
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			- task A is created
			- the status of a task is void (not displayed) and it cannot be set in task details.*/ 
		mgTask.addTaskDirectly(task1,true);
		mgTask.checkDefaultTaskStatus(task1,status,false);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}}