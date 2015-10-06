package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


	public class TASK_Projects_EditProject extends TASK_TestConfig_1{

	/**
	*<li> Case ID:130912.</li>
	*<li> Test Case Name: Check a project can be edited from the contextual menu of a project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130913.</li>
	*<li> Test Case Name: Check when Edit project is clicked.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130914.</li>
	*<li> Test Case Name: Check all fields in Edit Project pop up.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_03_CheckAProjectCanBeEditedFromTheContextualMenuOfAProject() {
		info("Test 1: Check a project can be edited from the contextual menu of a project");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"","", false);
		
		/*Step number: 2
		*Step Name: Step 2: Check a project can be edited from the contextual menu of a project
		*Step Description: 
			- From the contextual menu of project A> Edit.
		*Input Data: 
			
		*Expected Outcome: 
			Edit Project popup is displayed*/ 
		mgProject.checkEditProjectPopup(prj1);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

}