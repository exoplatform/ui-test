package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_TaskDescription extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130947.</li>
	*<li> Test Case Name: Check  there is an editor when adding a task or no description input.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130948.</li>
	*<li> Test Case Name: Check when a description is edited by clicking.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_03_CheckThereIsAnEditorWhenAddingATaskOrNoDescriptionInput() {
		info("Test 1: Check  there is an editor when adding a task or no description input");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check there is an editor when adding a task or no description input
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			- New task is created
			- there is an editor to enter the description*/ 
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check when a description is edited by clicking
		*Step Description: 
			- Click on Description to edit
		*Input Data: 
			
		*Expected Outcome: 
			the current description is displayed in a editor to modify*/ 
		mgTask.editTaskDescription(task1, des);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130949.</li>
	*<li> Test Case Name: Check description can be decorated with simple effect.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckDescriptionCanBeDecoratedWithSimpleEffect() {
		info("Test 2: Check description can be decorated with simple effect");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		mgTask.addTaskDirectly(task1,true);
		
		/*Step number: 3
		*Step Name: Step 3: Check description can be decorated with simple effect
		*Step Description: 
			- Click on Description to edit and decorate with bold, italic, underline, strike-through, list
		*Input Data: 
			
		*Expected Outcome: 
			The description can be decorated with simple effect: bold, italic, underline, strike-through, list...*/ 
		mgTask.decorateDescription(task1, des);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}
}