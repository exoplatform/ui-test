package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_BoardView_ManagingProjectWorkflow extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131709.</li>
	*<li> Test Case Name: Check Board displays the project workflow as panes.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB are added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckBoardDisplaysTheProjectWorkflowAsPanes() {
		info("Test 1: Check Board displays the project workflow as panes");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String waitingOn = flowData.getFlowByArrayTypeRandom(3);
		String todo = flowData.getFlowByArrayTypeRandom(1);
		String done = flowData.getFlowByArrayTypeRandom(4);
		String inProgress = flowData.getFlowByArrayTypeRandom(2);
		String[] flows = {waitingOn,todo,done,inProgress};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "","",false);
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.editTaskStatus(task2, waitingOn);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check Board displays the project workflow as panes
		*Step Description: 
			- Click on Board on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Board should display the project workflow as panes (TO DO / IN PROGRESS / WAITING ON / DONE).*/ 
		mgProject.checkDisplayOfBoard(flows);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131712.</li>
	*<li> Test Case Name: Check status column can be deleted.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB are added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckStatusColumnCanBeDeleted() {
		info("Test 2: Check status column can be deleted");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String waitingOn = flowData.getFlowByArrayTypeRandom(3);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "","", false);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Open Board view
		*Step Description: 
			- Click on Board on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is opened*/
		mgProject.openBoard();
		
		/*Step number: 4
		*Step Name: Step 4: Check status column can be deleted
		*Step Description: 
			- Hovering on the status name
			- Click on delete icon
		*Input Data: 
			
		*Expected Outcome: 
			- delete status icon is visible to the project manager
			- status is deleted*/ 
		mgProject.deleteStatus(waitingOn);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131711.</li>
	*<li> Test Case Name: Check Status name can be changed.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB are added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckStatusNameCanBeChanged() {
		info("Test 3: Check Status name can be changed");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String done = flowData.getFlowByArrayTypeRandom(4);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "","", false);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Open Board view
		*Step Description: 
			- Click on Board on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is opened*/
		mgProject.openBoard();
		
		/*Step number: 4
		*Step Name: Step 4: Check Status name can be changed
		*Step Description: 
			- double clicking the name in the column to edit status
		*Input Data: 
			
		*Expected Outcome: 
			- status is in edit mode*/ 
		mgProject.editStatus(done, title);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131713.</li>
	*<li> Test Case Name: Check tasks when deleting status columns.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB,taskC are added to projectA
	- taskA is To Do
	- taskB is In Progress
	- taskC is Waiting On</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-343
	*/
	@Test (groups="pending")
	public  void test04_CheckTasksWhenDeletingStatusColumns() {
		info("Test 4: Check tasks when deleting status columns");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String waitingOn = flowData.getFlowByArrayTypeRandom(3);
		String inProgress = flowData.getFlowByArrayTypeRandom(2);
		String todo = flowData.getFlowByArrayTypeRandom(1);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "","", false);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.addTask(prj1, task3);
		mgTask.editTaskStatus(task2, inProgress);
		mgTask.editTaskStatus(task3, waitingOn);
		
		/*Step number: 3
		*Step Name: Step 3: Open Board view
		*Step Description: 
			- Click on Board on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is opened*/
		mgProject.openBoard();
		
		/*Step number: 4
		*Step Name: Step 4: Delete To Do status
		*Step Description: 
			- Hover on To Do column and click delete icon
		*Input Data: 
			
		*Expected Outcome: 
			- To Do is deleted
			- taskA is changed to In Progress*/
		mgProject.deleteStatus(todo);
		mgTask.checkTaskLocation(task1,1);
		
		/*Step number: 5
		*Step Name: Step 5: Delete Waiting On status
		*Step Description: 
			- Hover on Waiting On column and click delete icon
		*Input Data: 
			
		*Expected Outcome: 
			- Waiting On is deleted
			- taskC is changed to In Progress*/ 
		mgProject.deleteStatus(waitingOn);
		mgTask.checkTaskLocation(task3,1);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131710.</li>
	*<li> Test Case Name: Check when Click on Add Status button.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB are added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckWhenClickOnAddStatusButton() {
		info("Test 5: Check when Click on Add Status button");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String done = flowData.getFlowByArrayTypeRandom(4);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "","", false);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/

		/*Step number: 3
		*Step Name: Step 3: Open Board view
		*Step Description: 
			- Click on Board on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is opened*/
		mgProject.openBoard();
		
		/*Step number: 4
		*Step Name: Step 4: Check when Click on Add Status button
		*Step Description: 
			- hover last column status and Click on Add Status button
		*Input Data: 
			
		*Expected Outcome: 
			-a column with an editable status name displays*/ 
		mgProject.addStatus(done, title);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}}