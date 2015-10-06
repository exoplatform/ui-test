package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_Projects_DeleteProject extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128244.</li>
	*<li> Test Case Name: Check a project can be deleted from Arrow menu > Delete.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131009.</li>
	*<li> Test Case Name: Check confirm dialog when deleting project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128245.</li>
	*<li> Test Case Name: Check delete action when deleting project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_03_CheckAProjectCanBeDeletedFromArrowMenuDelete() {
		info("Test 1: Check a project can be deleted from Arrow menu > Delete");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"","", false);
		
		/*Step number: 2
		*Step Name: Step 2: Check a project can be deleted from Arrow menu > Delete
		*Step Description: 
			- Click on contextual menu of project A on left pane> Delete
		*Input Data: 
			
		*Expected Outcome: 
			- pop-up confirm is opened to delete project*/ 
		/*Step number: 2
		*Step Name: Step 2: Check confirm dialog when deleting project
		*Step Description: 
			- From contextual of project A>Delete
		*Input Data: 
			
		*Expected Outcome: 
			A confirmation dialog opens:
			- title : Confirmation
			- message : You are about to delete $Project project and all its tasks. Please confirm
			- checkbox : also delete all sub-projects.
			- actions : [Delete] [Cancel]*/ 
		mgProject.checkConfirmDeleteProject(prj1);
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:128246.</li>
	*<li> Test Case Name: Check cancel action when deleting project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckCancelActionWhenDeletingProject() {
		info("Test 2: Check cancel action when deleting project");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"","", false);
		
		/*Step number: 2
		*Step Name: Step 2: Check cancel action when deleting project
		*Step Description: 
			- from contextual menu of project A on left pane> Delete
			- Click on Cancel button
		*Input Data: 
			
		*Expected Outcome: 
			- A confirmation dialog opens
			- Cancel dismisses the dialog without making a change*/ 
		mgProject.cancelDeleteProject(prj1);
	
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:128249.</li>
	*<li> Test Case Name: Check Delete action is not available to the main space project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedspaceA,spaceB is createdTask application is added to spaceA,spaceB</li>
	*<li> Post-Condition: </li>
	* TESTCASE IS REMOVED
	*/
	@Test (groups="pending")
	public  void test04_CheckDeleteActionIsNotAvailableToTheMainSpaceProject() {
		
 	}

	/**
	*<li> Case ID:128247.</li>
	*<li> Test Case Name: Check when option to delete all sub-projects is checked.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is createdproject A1, project A2 are sub-projects of project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckWhenOptionToDeleteAllSubProjectsIsChecked() {
		info("Test 6: Check when option to delete all sub-projects is checked");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj12 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] subprjs = {prj11,prj12};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"","", false);
		mgProject.addSubProject(prj1,prj11,"","", false);
		mgProject.addSubProject(prj1,prj12,"","", false);
		
		/*Step number: 2
		*Step Name: Step 2: Check when option to delete all sub
		-projects is checked
		*Step Description: 
			- from contextual menu of project A on left pane> Delete
			- Check option: also delete all sub
			-projects, and click on Delete button
		*Input Data: 
			
		*Expected Outcome: 
			- A confirmation dialog opens
			- project A and project A1, project A2 are deleted*/ 
		info("delete data");
		mgProject.deleteProject(prj1, true, subprjs);
		
 	}

	/**
	*<li> Case ID:128248.</li>
	*<li> Test Case Name: Check when option to delete all sub-projects is unchecked.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is createdproject A1, project A2 are sub-projects of project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckWhenOptionToDeleteAllSubProjectsIsUnchecked() {
		info("Test 7: Check when option to delete all sub-projects is unchecked");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"","", false);
		mgProject.addSubProject(prj1,prj11,"","", false);
		
		/*Step number: 2
		*Step Name: Step 2: Check when option to delete all sub
		-projects is unchecked
		*Step Description: 
			- from contextual menu of project A on left pane> Delete
			- Uncheck option: also delete all sub
			-projects, and click on Delete button
		*Input Data: 
			
		*Expected Outcome: 
			- A confirmation dialog opens
			- project A is deleted, project A1, project A2 are moved to the grandparent node*/ 
		mgProject.deleteProject(prj1, false,prj11);
		info("delete data");
		mgProject.deleteProject(prj11, false);
 	}}