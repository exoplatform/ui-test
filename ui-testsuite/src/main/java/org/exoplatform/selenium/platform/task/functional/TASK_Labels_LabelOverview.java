package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementProjects.optionNoNewTask;
import org.testng.annotations.*;


	public class TASK_Labels_LabelOverview extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131534.</li>
	*<li> Test Case Name: Check central pane with case of no task in label.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCentralPaneWithCaseOfNoTaskInLabel() {
		info("Test 1: Check central pane with case of no task in label");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		*Step Name: Step 2: Check central pane with case of no task in label
		*Step Description: 
			- Add labelA from contextual menu of Labels
		*Input Data: 
			
		*Expected Outcome: 
			- labelA is created
			- No task is displayed*/ 
		mgLabel.checkLabelByDefault(label1,true);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131540.</li>
	*<li> Test Case Name: Check default settings of a label view.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created
	- taskA,taskB are added to labelA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDefaultSettingsOfALabelView() {
		info("Test 2: Check default settings of a label view");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String group = groupByData.getGroupBy(0);
		String sort = sortByData.getSortBy(0);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		mgLabel.openLabel(label1);
		mgTask.addTaskDirectly(task1, true);
		mgTask.addTaskDirectly(task2, true);
		
		/*Step number: 2
		*Step Name: Step 2: Check default settings of a label view
		*Step Description: 
			- Click on labelA on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- default settings are:Group By: None, Sort By: Due Date*/ 
		mgLabel.checkDefaultGroupSort(label1, group, sort);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131539.</li>
	*<li> Test Case Name: Check default settings of Labels view.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA,labelB are created
	- taskA is added to labelA
	- taskB,taskC are added to labelB</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckDefaultSettingsOfLabelsView() {
		info("Test 3: Check default settings of Labels view");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String group = groupByData.getGroupBy(2);
		String sort = sortByData.getSortBy(0);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		mgLabel.openLabel(label1);
		mgTask.addTaskDirectly(task1, true);
		mgTask.addTaskDirectly(task2, true);
		
		/*Step number: 2
		*Step Name: Step 2: Check default settings of Labels view
		*Step Description: 
			- Click on Labels on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Labels view default settings are :Group By : Label, Sort By : Due Date*/ 
		mgLabel.checkDefaultGroupSort("Labels", group, sort);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131541.</li>
	*<li> Test Case Name: Check group by of each label in Labels.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created
	- taskA,taskB,taskC are added to labelA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_CheckGroupByOfEachLabelInLabels() {
		info("Test 4: Check group by of each label in Labels");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String none = groupByData.getGroupBy(0);
		String project = groupByData.getGroupBy(0);
		String label = groupByData.getGroupBy(0);
		String duedate = groupByData.getGroupBy(0);
		String status = groupByData.getGroupBy(0);
		String[] groups = {none,project,label,duedate,status};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		mgLabel.openLabel(label1);
		mgTask.addTaskDirectly(task1, true);
		mgTask.addTaskDirectly(task2, true);
		
		/*Step number: 2
		*Step Name: Step 2: Open Labels
		*Step Description: 
			- Click on Labels on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Labels is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check group by of each label in Labels
		*Step Description: 
			- Click on group by
		*Input Data: 
			
		*Expected Outcome: 
			- list of group by: None, Project, Label, Due Date, Status*/ 
		mgLabel.checkGroupByInLabels("Labels", groups);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131538.</li>
	*<li> Test Case Name: Check no Board view with label.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelAis created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckNoBoardViewWithLabel() {
		info("Test 5: Check no Board view with label");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		*Step Name: Step 2: Check no Board view with label
		*Step Description: 
			- Click on Labels on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is not available*/
		mgLabel.checkDisplayOfListBoard("Labels",false);
		
		/*Step number: 3
		*Step Name: Step 3: Check no Board view with label
		*Step Description: 
			- Click on labelA
		*Input Data: 
			
		*Expected Outcome: 
			- Board view is not available*/ 
		mgLabel.checkDisplayOfListBoard(label1,false);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131533.</li>
	*<li> Test Case Name: Check no label by default.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- no task is added</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131536.</li>
	*<li> Test Case Name: Welcome screen disappears when adding new label.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-285
	*/
	@Test (groups="pending")
	public  void test06_10_CheckNoLabelByDefault() {
		info("Test 6: Check no label by default");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check no label by default
		*Step Description: 
			- Check no label by default
		*Input Data: 
			
		*Expected Outcome: 
			- There is no label created by default in the label list. 
			- Label is No label in left menu and middle pane.*/ 
		mgLabel.checkLabelsByDefault(true);
		
		/*Step number: 2
		*Step Name: Step 2: Welcome screen disappears when adding new label
		*Step Description: 
			- Create labelA from contextual menu of Labels
		*Input Data: 
			
		*Expected Outcome: 
			- labelA is created
			- Create here to create your first label and No Label screen are disappeared*/
		mgLabel.addLabel(label1);
		mgLabel.checkLabelsByDefault(false);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131537.</li>
	*<li> Test Case Name: Check no New Task in Labels view.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckNoNewTaskInLabelsView() {
		info("Test 7: Check no New Task in Labels view");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check no New Task in Labels view
		*Step Description: 
			- Click on Labels on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Add task function is not available in Labels view.*/ 
		mgProject.checkTopOfListView("",false,optionNoNewTask.Labels);
 	}

	/**
	*<li> Case ID:131542.</li>
	*<li> Test Case Name: Check sort by of each label in Labels.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created
	- taskA,taskB,taskC are added to labelA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckSortByOfEachLabelInLabels() {
		info("Test 8: Check sort by of each label in Labels");
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
		mgLabel.addLabel(label1);
		mgLabel.openLabel(label1);
		mgTask.addTaskDirectly(task1, true);
		mgTask.addTaskDirectly(task2, true);
		
		/*Step number: 2
		*Step Name: Step 2: Open Labels
		*Step Description: 
			- Click on Labels on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Labels is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check sort by of each label in Labels
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
	*<li> Case ID:131535.</li>
	*<li> Test Case Name: Check tooltip with case of no task in label.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckTooltipWithCaseOfNoTaskInLabel() {
		info("Test 9: Check tooltip with case of no task in label");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		*Step Name: Step 2: Check tooltip with case of no task in label
		*Step Description: 
			- Create labelA from contextual menu of Labels
		*Input Data: 
			
		*Expected Outcome: 
			- labelA is created
			- tooltip Let's create your first task. is displayed*/
		mgLabel.addLabel(label1);
		mgLabel.openLabel(label1);
		mgLabel.checkLabelByDefault(label1, true);
		
		/*Step number: 3
		*Step Name: Step 3: Add new task
		*Step Description: 
			- Add taskA to labelA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is added
			- tooltip disappears*/ 
		mgTask.addTaskDirectly(task1, true);
		mgLabel.checkLabelByDefault(label1, false);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}
}