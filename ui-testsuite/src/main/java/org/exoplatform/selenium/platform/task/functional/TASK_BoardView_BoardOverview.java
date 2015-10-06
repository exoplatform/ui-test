package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_BoardView_BoardOverview extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131697.</li>
	*<li> Test Case Name: Check Board View are available inside a project.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131695.</li>
	*<li> Test Case Name: Check each status is displayed as column.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA is added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_03_CheckBoardViewAreAvailableInsideAProject() {
		info("Test 1: Check Board View are available inside a project");
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
		mgProject.addProject(prj1, "","", false);
		mgTask.addTask(prj1, task1);
		mgTask.addTask(prj1, task2);
		mgTask.editTaskStatus(task2, waitingOn);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened
			- Board is on central pane*/
		mgProject.checkDisplayOfListBoard(prj1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check each status is displayed as column
		*Step Description: 
			- Click on Board
		*Input Data: 
			
		*Expected Outcome: 
			- Each status is displayed as column in Board view: TO DO / IN PROGRESS / WAITING ON / DONE*/ 
		mgProject.checkDisplayOfBoard(flows);
		
		/*Step number: 3
		*Step Name: Step 3: Open Projects
		*Step Description: 
			- Click on Projects on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Projects is opened
			- No Board on central pane*/ 
		mgProject.checkDisplayOfListBoard("Projects",false);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131698.</li>
	*<li> Test Case Name: Check Board view default settings.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB,taskC are added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckBoardViewDefaultSettings() {
		info("Test 2: Check Board view default settings");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String none = groupByData.getGroupBy(0);
		String duedate = sortByData.getSortBy(0);
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
		mgTask.addTask(prj1, task2);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/
		
		/*Step number: 3
		*Step Name: Step 3: Check Board view default settings
		*Step Description: 
			- Click on Board on central pane
		*Input Data: 
			
		*Expected Outcome: 
			Board view default settings are :
			- Group By : None
			- Sort By : Due Date*/ 
		mgProject.checkDefaultGroupSort(prj1,none,duedate,true);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}


	/**
	*<li> Case ID:131699.</li>
	*<li> Test Case Name: Check group by and sort by in Board view.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB,taskC are added to projectA</li>
	*<li> Post-Condition: </li>
	* NOT FULL IMPLEMENT
	*/
	@Test
	public  void test04_CheckGroupByAndSortByInBoardView() {
		info("Test 4: Check group by and sort by in Board view");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String none = groupByData.getGroupBy(0);
		//String label = groupByData.getGroupBy(2);
		String assignee = groupByData.getGroupBy(4);
		String duedate = sortByData.getSortBy(0);
		String priority = sortByData.getSortBy(3);
		String rank = sortByData.getSortBy(4);
		String[] groups = {none,assignee};
		String[] sorts = {duedate,priority,rank};
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
		
		/*Step number: 4
		*Step Name: Step 4: Check group by and sort by in Board view
		*Step Description: 
			- Click on group by
			- Click on sort by
		*Input Data: 
			
		*Expected Outcome: 
			- list of group by: None, Label, Assignee
			- list of sorted by Due Date, Priority, Rank*/ 
		mgProject.checkGroupByInProjects(prj1, groups, true);
		mgProject.checkSortByInProjects(prj1, sorts, true);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131696.</li>
	*<li> Test Case Name: Check number of tasks displayed in status column.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- projectA is created
	- taskA,taskB are added to projectA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckNumberOfTasksDisplayedInStatusColumn() {
		info("Test 5: Check number of tasks displayed in status column");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String todo = flowData.getFlowByArrayTypeRandom(1);
		String inProgress = flowData.getFlowByArrayTypeRandom(2);
		
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
		mgTask.addTask(prj1, task2);
		mgTask.addTask(prj1, task3);
		mgTask.editTaskStatus(task3,inProgress);
		
		/*Step number: 2
		*Step Name: Step 2: Open projectA
		*Step Description: 
			- Click on projectA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is opened*/
		mgProject.openBoard();
		
		/*Step number: 3
		*Step Name: Step 3: Check number of tasks displayed in status column
		*Step Description: 
			- Click on Board on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Number of tasks displayed in status column (e.g TODO (2)).*/ 
		mgProject.checkDisplayNumberOfStatus(todo,2);
		mgProject.checkDisplayNumberOfStatus(inProgress,1);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}}