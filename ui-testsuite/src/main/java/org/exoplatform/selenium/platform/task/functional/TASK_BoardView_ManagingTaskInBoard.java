package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.task.ManagementTasks.optionGroupBy;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionSortBy;

import org.testng.annotations.*;


	public class TASK_BoardView_ManagingTaskInBoard extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131704.</li>
	*<li> Test Case Name: Check hover last task of a column to enter new task.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB are added to projectA
	- taskA, taskB are assigned to different users</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckHoverLastTaskOfAColumnToEnterNewTask() {
		info("Test 1: Check hover last task of a column to enter new task");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task4 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		mgTask.addTask(prj1, task2);
		mgTask.editTaskAssignee(task1, DATA_USER1);
		mgTask.editTaskAssignee(task2, DATA_USER2);
		
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
		*Step Name: Step 4: Check hover last task of a column to enter new task
		*Step Description: 
			- hover last task of a column
		*Input Data: 
			
		*Expected Outcome: 
			- an input field displays to enter new task*/
		mgTask.addTaskInBoard(1, true,prj1, task3,"");
		
		/*Step number: 5
		*Step Name: Step 5: Group by assign
		*Step Description: 
			- Click on group by assignee
		*Input Data: 
			
		*Expected Outcome: 
			- tasks are grouped by assignee*/
		mgTask.selectOptGroupBy(optionGroupBy.Assignee);
		
		/*Step number: 6
		*Step Name: Step 6: Check hover last task of a column to enter new task
		*Step Description: 
			- hover last task of group
		*Input Data: 
			
		*Expected Outcome: 
			- an input field displays to enter new task*/ 
		mgTask.addTaskInBoard(1, false, prj1,task4, DATA_USER2);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131707.</li>
	*<li> Test Case Name: Check task is overdue.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA is overdue is added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckTaskIsOverdue() {
		info("Test 2: Check task is overdue");
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
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.setDueDate(task1, getDate(-3, "dd MMM yyyy"), getDate(-3, "dd"), 0);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/
		
		/*Step number: 3
		*Step Name: Step 3: Check task is overdue
		*Step Description: 
			- Click on Board on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- ! symbol in red is added before taskA*/ 
		mgProject.openBoard();
		mgTask.checkTaskSymbol(task1, false);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131700.</li>
	*<li> Test Case Name: Check user can drag and drop of cards.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB are added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckUserCanDragAndDropOfCards() {
		info("Test 3: Check user can drag and drop of cards");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 3
		*Step Name: Step 3: Open Board view
		*Step Description: 
			- Click on Board on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is opened*/
		mgProject.openBoard();
		
		/*Step number: 4
		*Step Name: Step 4: Check user can drag and drop of cards
		*Step Description: 
			- Drag and drop taskA to In Progress column
		*Input Data: 
			
		*Expected Outcome: 
			- status of taskA is changed to IN Progress*/ 
		mgTask.checkDragDropTask(task1, 1, 2,false);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131702.</li>
	*<li> Test Case Name: Check user can drag in same column when Sort by Rank is selected.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB are added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckUserCanDragInSameColumnWhenSortByRankIsSelected() {
		info("Test 4: Check user can drag in same column when Sort by Rank is selected");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		mgTask.addTask(prj1, task2);
		
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
		*Step Name: Step 4: Select sort by Rank
		*Step Description: 
			- Click on sort by > Rank
		*Input Data: 
			
		*Expected Outcome: 
			- tasks are sorted by Rank*/
		mgTask.selectOptSortBy(optionSortBy.Rank);
		
		/*Step number: 5
		*Step Name: Step 5: Check user can drag in same column when Sort by Rank is selected
		*Step Description: 
			- Drag and drop taskA in To Do column
		*Input Data: 
			
		*Expected Outcome: 
			- taskA can be drag and drop*/ 
		mgTask.checkDragDropTask(task1, 1, 2,true);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}
	/**
	*<li> Case ID:140385.</li>
	*<li> Test Case Name: Check user can drag and drop of cards across swimlanes.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB are added to projectA
	- taskA is assigned to userA
	- taskB is assigned to userB</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-253
	*/
	@Test (groups="pending")
	public  void test05_CheckUserCanDragAndDropOfCardsAcrossSwimlanes() {
		info("Test 5: Check user can drag and drop of cards across swimlanes");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		mgTask.editTaskAssignee(task1, DATA_USER1);
		mgTask.addTask(prj1, task2);
		mgTask.editTaskAssignee(task2, DATA_USER2);
		/*Step number: 3
		*Step Name: Step 3: Open Board view
		*Step Description: 
			- Click on Board on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is opened*/
		mgProject.openBoard();
		
		/*Step number: 4
		*Step Name: Step 4: Select group by assignee
		*Step Description: 
			- Click on Assignee
		*Input Data: 
			
		*Expected Outcome: 
			- tasks are grouped into userA,userB*/
		mgTask.selectOptGroupBy(optionGroupBy.Assignee);
		
		/*Step number: 5
		*Step Name: Step 5: Check user can drag and drop of cards across swimlanes
		*Step Description: 
			- Drag and drop taskA from userA to userB
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is now assigned to userB*/ 
		mgTask.checkDragDropTaskAcrossSwimlanes(task1,1,DATA_USER2);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}
	}