package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_TaskPriority extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130966.</li>
	*<li> Test Case Name: Check combo box to select priority.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130965.</li>
	*<li> Test Case Name: Check the default value of task priority.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-154
	*/
	@Test (groups="pending")
	public  void test01_03_CheckComboBoxToSelectPriority() {
		info("Test 1: Check combo box to select priority");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String none = priorityData.getPriorityByArrayTypeRandom(1);
		String high = priorityData.getPriorityByArrayTypeRandom(2);
		String normal = priorityData.getPriorityByArrayTypeRandom(3);
		String low = priorityData.getPriorityByArrayTypeRandom(4);
		String[] priorities = {none,high,low,normal};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open task A
		*Step Description: 
			- Click on task A to open task details
		*Input Data: 
			
		*Expected Outcome: 
			Task details is opened*/
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check combo box to select priority
		*Step Description: 
			- Click on Priority of task details
		*Input Data: 
			
		*Expected Outcome: 
			There is a combo box to select with 4 values:HighNormalLowNone*/ 
		mgTask.checkDefaultPriority(task1, normal, priorities);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130967.</li>
	*<li> Test Case Name: Check display of priority.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDisplayOfPriority() {
		info("Test 2: Check display of priority");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String high = priorityData.getPriorityByArrayTypeRandom(2);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new task
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			New task is created*/
		mgTask.addTaskDirectly(task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check display of priority
		*Step Description: 
			- Click on priority with High/Normal/Low/None
		*Input Data: 
			
		*Expected Outcome: 
			The value is displayed with syntax: $PriorityIcon + $Priority.*/ 
		mgTask.editTaskPriority(task1, high);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}}