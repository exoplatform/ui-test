package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_UIOverview extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128106.</li>
	*<li> Test Case Name: Check first access of welcome screen.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128107.</li>
	*<li> Test Case Name: Check first access of Incoming.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128108.</li>
	*<li> Test Case Name: Check first access of project and label.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_03_CheckFirstAccessOfWelcomeScreen() {
		info("Test 1: Check first access of welcome screen");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check first access of welcome screen
		*Step Description: 
			- Check first access of welcome screen on central pane
		*Input Data: 
			
		*Expected Outcome: 
			In the central pane, it displays: 
			- Big icon of Task
			- Welcome to eXo Tasks*/ 
		/*Step number: 2
		*Step Name: Step 2: Check first access of Incoming
		*Step Description: 
			- Check first access of Incoming on central pane
		*Input Data: 
			
		*Expected Outcome: 
			On first access, Incoming is selected, main tab contains an empty task line with input focus ready to enter the task description. a tooltip invite the user to create his first task let's create your first task*/
		/*Step number: 2
		*Step Name: Step 2: Check first access of project and label
		*Step Description: 
			- Check first access of project and label on central pane
		*Input Data: 
			
		*Expected Outcome: 
			On first access there is no project and no label in the left pane.
			- Below Projects, it displays: No Project
			- Below Labels, it displays: No Label*/ 
		mgTask.checkFirstAccess();
 	}

	/**
	*<li> Case ID:128109.</li>
	*<li> Test Case Name: Check first access of sort by and group by.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckFirstAccessOfSortByAndGroupBy() {
		info("Test 4: Check first access of sort by and group by");
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
		*Step Name: Step 2: Check first access of sort by and group by
		*Step Description: 
			- Check first access of sort by and group by on central pane
		*Input Data: 
			
		*Expected Outcome: 
			Sort by and Group by options are hidden until there are at least 2 tasks added*/
		mgProject.checkFirstAccessGroupSort(false);
		mgTask.addTaskDirectly(task1);
		mgTask.addTaskDirectly(task2);
		mgTask.addTaskDirectly(task3);
		
		/*Step number: 3
		*Step Name: Step 3: Create 2 tasks
		*Step Description: 
			- Check first access of sort by and group by on central pane
		*Input Data: 
			
		*Expected Outcome: 
			Sort by and Group by options are displayed*/ 
		mgProject.checkFirstAccessGroupSort(true);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgTask.deleteTask(task2);
		mgTask.deleteTask(task3);
 	}

	/**
	*<li> Case ID:128110.</li>
	*<li> Test Case Name: Check Left Pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128111.</li>
	*<li> Test Case Name: Check Central Pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130872.</li>
	*<li> Test Case Name: Check Tasks menu on left pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_06_10_CheckLeftPane() {
		info("Test 5: Check Left Pane");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check Left Pane
		*Step Description: 
			- Check Left Pane
		*Input Data: 
			
		*Expected Outcome: 
			The left column is split in 3 areas : 
			- Tasks
			- Projects 
			- Labels*/ 
		mgProject.checkDisplayOfLeftPane();
		
		/*Step number: 2
		*Step Name: Step 2:Check Central Pane
		*Step Description: 
			- Check Central Pane
		*Input Data: 
			
		*Expected Outcome: 
			By default there is a List and Board views available in central pane*/ 
		mgProject.checkDisplayOfListBoard(true);
 	}

	/**
	*<li> Case ID:128112.</li>
	*<li> Test Case Name: Check close Right Pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128114.</li>
	*<li> Test Case Name: Check open Right Pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_08_CheckCloseRightPane() {
		info("Test 7: Check close Right Pane");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgTask.addTask(prj1, task1);
		
		/*Step number: 2
		*Step Name: Step 2: Open Right Pane
		*Step Description: 
			Right pane can be opened by:
			- Click on a task in the task list.
			- Click on a project in the project list.
			- Click on filter icon to show filter detail, click on it again to hide it.
		*Input Data: 
			
		*Expected Outcome: 
			Right Pane is opened*/ 
		mgProject.openProject(prj1);
		mgProject.closeProject();
		
		/*Step number: 2
		*Step Name: Step 2: Close Right Pane
		*Step Description: 
			- Click on one task/project to open right pane
			- Click on close icon on top right of Right Pane
		*Input Data: 
			
		*Expected Outcome: 
			Right Pane is closed*/ 
		mgTask.openTask(task1);
		mgTask.closeTask();
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:128115.</li>
	*<li> Test Case Name: Check hidden Right Pane.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckHiddenRightPane() {
		info("Test 9: Check hidden Right Pane");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1, "", false);
		mgProject.hideProjectDetail();
		mgTask.addTask(prj1, task1);
		
		/*Step number: 2
		*Step Name: Step 2: Hide Right Pane
		*Step Description: 
			- Click on one task/project to open Right Pane
			- Clicking on the show/hide icon
		*Input Data: 
			
		*Expected Outcome: 
			Right Pane is hidden*/ 
		mgTask.openTask(task1);
		mgTask.hideTaskDetail();
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:130873.</li>
	*<li> Test Case Name: Check main header can be expanded/collapsed.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130874.</li>
	*<li> Test Case Name: Check project list can be expanded/collapsed.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd sub-project A1 to project A</li>
	*<li> Post-Condition: </li>
	* NOT IMPLEMENT YET
	*/
	@Test (groups="pending")
	public  void test11_12_CheckMainHeaderCanBeExpandedcollapsed() {
		info("Test 11 Check main header can be expanded/collapsed");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Check main header can be expanded/collapsed
		*Step Description: 
			- Click on the show/hide icon area ofTasks/Projects/Labels
		*Input Data: 
			
		*Expected Outcome: 
			Tasks/Projects/Labels can be expanded/collapsed*/ 

 	}

	/**
	*<li> Case ID:130875.</li>
	*<li> Test Case Name: Check label list can be expanded/collapsed.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd sub-label A1 to label A</li>
	*<li> Post-Condition: </li>
	* NOT IMPLEMENT YET
	*/
	@Test (groups="pending")
	public  void test13_CheckLabelListCanBeExpandedcollapsed() {
		info("Test 13 Check label list can be expanded/collapsed");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Check label list can be expanded/collapsed
		*Step Description: 
			- Click on the show/hide icon area of label A1
		*Input Data: 
			
		*Expected Outcome: 
			label A1 can be expanded/collapsed*/ 

 	}}