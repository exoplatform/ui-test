package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_Projects_ProjectWorkFlow extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130994.</li>
	*<li> Test Case Name: Check default workflow of project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130995.</li>
	*<li> Test Case Name: Check project workflows is set at creation time.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_CheckDefaultWorkflowOfProject() {
		info("Test 1: Check default workflow of project");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String toDo = flowData.getFlowByArrayTypeRandom(1);
		String inProgress = flowData.getFlowByArrayTypeRandom(2);
		String waitingOn = flowData.getFlowByArrayTypeRandom(3);
		String done = flowData.getFlowByArrayTypeRandom(4);
		String[] flows = {toDo,inProgress,waitingOn,done};
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
		*Step Name: Step 2: Check default workflow of project
		*Step Description: 
			- Create new project
		*Input Data: 
			
		*Expected Outcome: 
			Any new project has a default workflow : To Do, In Progress, Waiting On, Done.*/ 
		mgTask.checkListFlow(task1, toDo,flows);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
		
 	}
}