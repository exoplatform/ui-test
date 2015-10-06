package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


	public class TASK_Labels_CreateLabel extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131546.</li>
	*<li> Test Case Name: Check created new label action.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131545.</li>
	*<li> Test Case Name: Check input field of Add Label.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131543.</li>
	*<li> Test Case Name: Check label can be created via the contextual menu of Labels.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_03_04_CheckCreatedNewLabelAction() {
		info("Test 1: Check created new label action");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open Add Label input
		*Step Description: 
			- Click on contextual menu of Label> Add Label
		*Input Data: 
			
		*Expected Outcome: 
			- input field is displayed*/

		/*Step number: 3
		*Step Name: Step 3: Check created new label action
		*Step Description: 
			- Input name of label 
			- enter or mousing out the input field.
		*Input Data: 
			
		*Expected Outcome: 
			- name of new label is saved*/ 
		mgLabel.addLabel(label1);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131547.</li>
	*<li> Test Case Name: Check group by of tasks in a label.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created
	- taskA,taskB,taskC are added to labelA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckGroupByOfTasksInALabel() {
		info("Test 2: Check group by of tasks in a label");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String none = groupByData.getGroupBy(0);
		String project = groupByData.getGroupBy(0);
		String duedate = groupByData.getGroupBy(0);
		String status = groupByData.getGroupBy(0);
		String[] groups = {none,project,duedate,status};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open labelA
		*Step Description: 
			- Click on labelA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- labelA is opened*/
		mgLabel.addLabel(label1);
		mgTask.addTaskDirectly(task1, true);
		mgTask.addTaskDirectly(task2, true);
		driver.navigate().refresh();
		mgLabel.openLabel(label1);
		
		/*Step number: 3
		*Step Name: Step 3: Check group by of tasks in a label
		*Step Description: 
			- Click on group by
		*Input Data: 
			
		*Expected Outcome: 
			- list of group by: None, Project, Due Date, Status.*/ 
		mgLabel.checkGroupByInLabels(label1, groups);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131548.</li>
	*<li> Test Case Name: Check sort by of tasks in a label.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created
	- taskA,taskB,taskC are added to labelA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckSortByOfTasksInALabel() {
		info("Test 5: Check sort by of tasks in a label");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String title = sortByData.getSortBy(1);
		String createdDate = sortByData.getSortBy(2);
		String dueDate = sortByData.getSortBy(0);
		String priority = sortByData.getSortBy(3);
		String[] sorts = {title,createdDate,dueDate,priority};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open labelA
		*Step Description: 
			- Click on labelA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- labelA is opened*/
		mgLabel.addLabel(label1);
		mgTask.addTaskDirectly(task1, true);
		mgTask.addTaskDirectly(task2, true);
		driver.navigate().refresh();
		mgLabel.openLabel(label1);
		
		/*Step number: 3
		*Step Name: Step 3: Check sort by of tasks in a label
		*Step Description: 
			- Click on sort by
		*Input Data: 
			
		*Expected Outcome: 
			- list of sort by: Title, Created Date, Due Date, Priority*/ 
		mgLabel.checkSortByInLabels(label1, sorts);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131544.</li>
	*<li> Test Case Name: Check sublabel can be created via the contextual menu of a label.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckSublabelCanBeCreatedViaTheContextualMenuOfALabel() {
		info("Test 6: Check sublabel can be created via the contextual menu of a label");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String label11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		
		/*Step number: 2
		*Step Name: Step 2: Check sublabel can be created via the contextual menu of a label
		*Step Description: 
			- Add labelA1 from contextual menu of labelA> Add Label
		*Input Data: 
			
		*Expected Outcome: 
			- labelA1 is added*/ 
		mgLabel.addSubLabel(label1, label11);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}}