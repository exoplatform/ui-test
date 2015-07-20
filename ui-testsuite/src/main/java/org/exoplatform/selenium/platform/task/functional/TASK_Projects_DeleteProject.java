package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuGivenProject;
import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuProject;

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
		String mes = welcomeMesData.getWelcomeMessage(6);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		taskMgHome.goToTasks();
		info("add project 1 from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		
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
		info("delete project");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		waitForAndGetElement(mgProject.ELEMENT_CONFIRMATION_POPUP_TITLE);
		waitForAndGetElement(mgProject.ELEMENT_DELETE_PROJECT_POPUP_CANCEL_BTN);
		waitForAndGetElement(mgProject.ELEMENT_DELETE_PROJECT_POPUP_DELETE_BTN);
		waitForAndGetElement(mgProject.ELEMENT_DELETE_PROJECT_POPUP_MESSAGE.replace("${project}",prj1));
		waitForAndGetElement(mgProject.ELEMENT_DELETE_PROJECT_DELETE_SUBPRJ_CHECKBOX_TEXT.replace("$mes", mes));
		
		mgProject.deleteProject(prj1, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
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
		info("open task page");
		taskMgHome.goToTasks();
		info("add project 1 from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Check cancel action when deleting project
		*Step Description: 
			- from contextual menu of project A on left pane> Delete
			- Click on Cancel button
		*Input Data: 
			
		*Expected Outcome: 
			- A confirmation dialog opens
			- Cancel dismisses the dialog without making a change*/ 
		info("click Cancel delete");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		waitForAndGetElement(mgProject.ELEMENT_CONFIRMATION_POPUP_TITLE);
		click(mgProject.ELEMENT_DELETE_PROJECT_POPUP_CANCEL_BTN);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
		
		info("delete project");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
 	}

	/**
	*<li> Case ID:128249.</li>
	*<li> Test Case Name: Check Delete action is not available to the main space project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedspaceA,spaceB is createdTask application is added to spaceA,spaceB</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-176
	*/
	@Test (groups="pending")
	public  void test04_CheckDeleteActionIsNotAvailableToTheMainSpaceProject() {
		info("Test 4: Check Delete action is not available to the main space project");
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("open task page");
		taskMgHome.goToTasks();
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", space1));
		
		/*Step number: 2
		*Step Name: Step 2: Check Delete action is not available to the main space project
		*Step Description: 
			- on left pane, check Delete action from contextual menu of spaceA project
		*Input Data: 
			
		*Expected Outcome: 
			- Delete action is not available to the main space project*/
		info("cannot delete space project");
		mgProject.goToContMenuGivenProject(space1);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_DELETE.replace("$project", space1));
		
		/*Step number: 3
		*Step Name: Step 3: Delete space
		*Step Description: 
			- Goto My space to delete spaceA
		*Input Data: 
			
		*Expected Outcome: 
			- spaceA is deleted*/
		info("Delete space");
		hp.goToMySpaces();
		spaMg.deleteSpace(space1,false);
		
		/*Step number: 4
		*Step Name: Step 4: Check spaceA project
		*Step Description: 
			- Click on Tasks on the left navigation to check project list
		*Input Data: 
			
		*Expected Outcome: 
			- spaceA project is deleted from project list*/
		info("open task page");
		taskMgHome.goToTasks();
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", space1));
		
		/*Step number: 5
		*Step Name: Step 5: Remove Task application in space
		*Step Description: 
			- Open Application of spaceB to remove Task application
		*Input Data: 
			
		*Expected Outcome: 
			- Task application is removed from project B*/

		/*Step number: 6
		*Step Name: Step 6: Check spaceB project
		*Step Description: 
			- Click on Tasks on the left navigation to check project list
		*Input Data: 
			
		*Expected Outcome: 
			- spaceB project is deleted from project list*/ 

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
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		taskMgHome.goToTasks();
		
		info("add project 1 from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		info("add project 11 from project 1");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj11,"", false);
		info("add project 12 from project 1");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj12,"", false);
		
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
		info("delete project and check sub-project");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj11));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj12));
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
		info("open task page");
		taskMgHome.goToTasks();
		
		info("add project 1 from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		info("add project 11 from project 1");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj11,"", false);
		
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
		info("uncheck sub-project");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, false);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj11));
		
		info("delete project");
		mgProject.selectOpContMenuGivenProject(prj11,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj11, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj11));
		
 	}}