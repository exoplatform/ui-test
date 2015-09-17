package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_Projects_ProjectStatus extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131517.</li>
	*<li> Test Case Name: Check case status is the first of the list when workflow status is deteted.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCaseStatusIsTheFirstOfTheListWhenWorkflowStatusIsDeteted() {
		info("Test 1: Check case status is the first of the list when workflow status is deteted");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String todo = flowData.getFlowByArrayTypeRandom(1);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Open Board
		*Step Description: 
			- Click on Board
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is opened*/
		mgProject.openBoard();
		
		/*Step number: 4
		*Step Name: Step 4: Delete first status
		*Step Description: 
			- Mouse over and delete To Do status
		*Input Data: 
			
		*Expected Outcome: 
			- To Do is delete
			- taskA is changed to In Progress*/ 
		mgProject.deleteStatus(todo);
		mgTask.checkTaskLocation(task1,1);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131518.</li>
	*<li> Test Case Name: Check case status is the last when workflow status is deteted.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test02_CheckCaseStatusIsTheLastWhenWorkflowStatusIsDeteted() {
		info("Test 2: Check case status is the last when workflow status is deteted");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String todo = flowData.getFlowByArrayTypeRandom(1);
		String inProgress = flowData.getFlowByArrayTypeRandom(2);
		String waitingOn = flowData.getFlowByArrayTypeRandom(3);
		String done = flowData.getFlowByArrayTypeRandom(4);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Open Board
		*Step Description: 
			- Click on Board
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is opened*/
		mgProject.openBoard();
		
		/*Step number: 4
		*Step Name: Step 4: Check case status is the last when workflow status is deteted
		*Step Description: 
			- Mouse over and delete To Do> In Progress> Waiting On > Done
		*Input Data: 
			
		*Expected Outcome: 
			- cannot delete Done*/ 
		mgProject.deleteStatus(todo);
		mgProject.deleteStatus(waitingOn);
		mgProject.deleteStatus(inProgress);
		mgProject.checkDeleteStatus(done, false);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131516.</li>
	*<li> Test Case Name: Check when a workflow status is deleted.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- taskA is added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckWhenAWorkflowStatusIsDeleted() {
		info("Test 3: Check when a workflow status is deleted");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String waitingOn = flowData.getFlowByArrayTypeRandom(3);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.editTaskStatus(task1, waitingOn);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Open Board
		*Step Description: 
			- Click on Board
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is opened*/
		mgProject.openBoard();
		
		/*Step number: 4
		*Step Name: Step 4: Check when a workflow status is deleted
		*Step Description: 
			- Mouse over and delete Waiting On
		*Input Data: 
			
		*Expected Outcome: 
			- Waiting On is delete
			- taskA is changed to previous: In Progress*/ 
		mgProject.deleteStatus(waitingOn);
		mgTask.checkTaskLocation(task1,2);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}}