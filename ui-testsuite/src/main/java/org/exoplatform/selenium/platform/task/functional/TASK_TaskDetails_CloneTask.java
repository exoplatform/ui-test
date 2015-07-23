package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;

	public class TASK_TaskDetails_CloneTask extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128251.</li>
	*<li> Test Case Name: Check a task can be cloned from Arrow menu > Clone.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created and added to project A</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128253.</li>
	*<li> Test Case Name: Check clone action of task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created and added to project A</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128252.</li>
	*<li> Test Case Name: Check name of cloned task.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedtask A is created and added to project A</li>
	*<li> Post-Condition: </li>
	*BUG:https://jira.exoplatform.org/browse/TA-187
	*/
	@Test(groups="pending")
	public  void test01_02_03_CheckATaskCanBeClonedFromArrowMenuClone() {
		info("Test 1: Check a task can be cloned from Arrow menu > Clone");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Open project A
		*Step Description: 
			- Click on project A on left pane to open
		*Input Data: 
			
		*Expected Outcome: 
			- task list of project A is opened*/
		mgTask.addTask(project, task1);
		
		/*Step number: 3
		*Step Name: Step 3: Check name of cloned task
		*Step Description: 
			- Click on task A on central pane
			- on right pane, from Arrow menu > Clone
		*Input Data: 
			
		*Expected Outcome: 
			- detail task is open on right pane
			- A cloned task is automatically given the same name prefixed by "Copy of"*/ 
		mgTask.cloneTask(task1);
		
		info("delete data");
		mgProject.deleteProject(project, true);
 	}

}