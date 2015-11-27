package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.task.ManagementTasks.optionTask;
import org.testng.annotations.*;

	public class TASK_TaskDetails_EditTask_TaskLabel extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131714.</li>
	*<li> Test Case Name: Check  label icon and Labels label by default.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckLabelIconAndLabelsLabelByDefault() {
		info("Test 1: Check  label icon and Labels label by default");
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
		mgLabel.addLabel(label1);
		
		/*Step number: 2
		*Step Name: Step 2: Checklabel icon and Labels label by default
		*Step Description: 
			- Add taskA
		*Input Data: 
			
		*Expected Outcome: 
			- on task detail, there are label icon and Labels label*/
		mgTask.selectOptionTask(optionTask.Incoming);
		mgTask.addTaskDirectly(task1, true);
		mgLabel.checkLabelInTask(task1);
		
		/*Step number: 3
		*Step Name: Step 3: AddlabelA to taskA
		*Step Description: 
			- Click on Labels to add labelA to taskA
		*Input Data: 
			
		*Expected Outcome: 
			- Labels label is replaced by labelA*/ 
		mgLabel.addLabelToTask(task1, label1);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131718.</li>
	*<li> Test Case Name: Check a task can be assigned to multiple labels.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131716.</li>
	*<li> Test Case Name: Check autocomplete list when inputing value.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA,labelB are created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_03_CheckATaskCanBeAssignedToMultipleLabels() {
		info("Test 2: Check a task can be assigned to multiple labels");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String label2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] labels = {label1,label2};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		mgLabel.addLabel(label2);
		
		/*Step number: 2
		*Step Name: Step 2: Add taskA
		*Step Description: 
			- Add new taskA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgTask.selectOptionTask(optionTask.Incoming);
		mgTask.addTaskDirectly(task1, true);
		
		/*Step number: 3
		*Step Name: Step 3: Add label to taskA
		*Step Description: 
			- On task detail, click on Labels to add label, then input : label
		*Input Data: 
			
		*Expected Outcome: 
			- There is an autocomplete list displaying all labels tags that matches with input value: labelA,labelB*/
		mgLabel.checkAutoCompleteLabel(task1,"Content", labels);
		
		/*Step number: 3
		*Step Name: Step 3: Check a task can be assigned to multiple labels
		*Step Description: 
			- On task detail, click on Labels to add label: labelA, labelB,labelC
		*Input Data: 
			
		*Expected Outcome: 
			- labelA,labelB,labelC are added to taskA*/
		mgLabel.addLabelToTask(task1, labels);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgLabel.deleteLabel(label1);
		mgLabel.deleteLabel(label2);
 	}

	/**
	*<li> Case ID:131724.</li>
	*<li> Test Case Name: Check case label view.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created
	- taskA,taskB are created with labelA</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test04_CheckCaseLabelView() {
		info("Test 4: Check case label view");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] tasks= {task1,task2};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		mgTask.addTaskDirectly(task1, true);
		mgTask.addTaskDirectly(task2, true);
		
		/*Step number: 2
		*Step Name: Step 2: Group by label
		*Step Description: 
			- Click on Incoming on left pane
			- On central pane, click on group by label
		*Input Data: 
			
		*Expected Outcome: 
		    - Incoming is opened
			- on central pane, list of tasks displays without label*/ 
		mgTask.selectOptionTask(optionTask.Incoming);
		mgLabel.checkGroupByLabel(label1, tasks);
		
		/*Step number: 3
		*Step Name: Step 3: Open labelA
		*Step Description: 
			- Click on labelA
		*Input Data: 
			
		*Expected Outcome: 
			- on central pane,taskA, taskB displays without label*/
		mgLabel.checkLabelInLabelView(label1, tasks);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131719.</li>
	*<li> Test Case Name: Check color of task label.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA with red color
	- labelB with no color</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckColorOfTaskLabel() {
		info("Test 5: Check color of task label");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String label2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String color1 = colorData.getClassNameByArrayTypeRandom(1);
		String[] labels ={label1,label2};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		mgLabel.selectColor(label1, color1);
		mgLabel.addLabel(label2);
		mgTask.addTask("Incoming", task1);
		
		/*Step number: 2
		*Step Name: Step 2: Add taskA
		*Step Description: 
			- Add taskA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgLabel.addLabelToTask(task1, labels);
		
		/*Step number: 3
		*Step Name: Step 3:Check color of task label
		*Step Description: 
			- On task detail, click on Labels to add : labelA,labelB
		*Input Data: 
			
		*Expected Outcome: 
			- labelA is displayed with red color
			- labelB is displayed with default style*/ 
		mgLabel.checkColorTaskLabel(task1, label1, color1);
		mgLabel.checkColorTaskLabel(task1, label2, "");
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgLabel.deleteLabel(label1);
		mgLabel.deleteLabel(label2);
 	}

	/**
	*<li> Case ID:131723.</li>
	*<li> Test Case Name: Check display of task label.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131715.</li>
	*<li> Test Case Name: Check label in edit mode.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-351
	*/
	@Test (groups="pending")
	public  void test06_07_CheckDisplayOfTaskLabel() {
		info("Test 6: Check display of task label");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		*Step Name: Step 2: Add taskA
		*Step Description: 
			- Add new taskA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgLabel.addLabel(label1);
		mgTask.addTask("Incoming", task1);
		
		/*Step number: 3
		*Step Name: Step 3: Add labelA to taskA
		*Step Description: 
			- Click on Labels on task detail to add labelA
		*Input Data: 
			
		*Expected Outcome: 
			- labelA is displayed with x icon that allow to remove it*/
		/*Step number: 3
		*Step Name: Step 3: Check display of task label
		*Step Description: 
			- On task detail, click on Labels to add : labelA, then Mouse over to save
		*Input Data: 
			
		*Expected Outcome: 
			- labelA displays below the task title in the right pane.
			- labelA displays on the right of tasks title on central pane*/ 
		mgLabel.addLabelToTask(task1, label1);
		mgLabel.checkDisplayOfTaskLabel(task1, label1);
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131717.</li>
	*<li> Test Case Name: New label can be saved as new one.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_NewLabelCanBeSavedAsNewOne() {
		info("Test 8: New label can be saved as new one");
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
		*Step Name: Step 2: Add new taskA
		*Step Description: 
			- Add new taskA
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgTask.addTaskDirectly(task1, true);
		
		/*Step number: 3
		*Step Name: Step 3: Add label to taskA
		*Step Description: 
			- Click on Labels to add label: newLabel
		*Input Data: 
			
		*Expected Outcome: 
			- In case new label is input, it can be saved as a new one: newLabel*/ 
		mgLabel.addLabelToTask(task1, label1);
		driver.navigate().refresh();
		waitForAndGetElement(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label", label1));
		
		info("delete data");
		mgTask.deleteTask(task1);
		mgLabel.deleteLabel(label1);
		
 	}}