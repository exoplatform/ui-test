package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuGivenProject;
import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuProject;

import org.testng.annotations.*;


	public class TASK_Projects_ShareProject extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128238.</li>
	*<li> Test Case Name: Check case user has share permission on sub-project but don't have permission on the parent.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created but not share to user Aproject A1 is child of project A, and share to user A</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128225.</li>
	*<li> Test Case Name: Check member permission when sharing project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is createdsome tasks are added to project Auser A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_03_CheckCaseUserHasSharePermissionOnSubprojectButDontHavePermissionOnTheParent() {
		info("Test 1: Check case user has share permission on sub-project but don't have permission on the parent");
		String parent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String child = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] user1 = {DATA_USER2};
		
		info("open task page");
		hp.goToTasks();
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(parent,parent, false);
		mgProject.selectOpContMenuGivenProject(parent, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(child, "",false);
		mgTask.addTask(child, task);
		mgTask.addTaskComment(comment1);
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", DATA_NAME_USER1).replace("$comment", comment1));
		
		info("share project with mary");
		mgProject.selectOpContMenuGivenProject(child, optionContMenuGivenProject.Share);
		mgProject.shareProject(user1, false);
		
		/*Step Number: 1
		*Step Name: Step 1: Login as user A
		*Step Description: 
			-Login as user A
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		info("login as mary");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		
		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Check case user has share permission on sub
		-project but don't have permission on the parent
		*Step Description: 
			- Click on project A on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- The project A is still visible on the project list in the left menu but it is impossible see in details.*/ 
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", parent));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", child));
		info("click on parent project");
		doubleClickOnElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", parent));
		waitForElementNotPresent(mgProject.ELEMENT_ADD_PROJECT_DES_TEXT.replace("$des", parent));
		
		/*Step number: 4
		*Step Name: Step 4: Check member permission
		*Step Description: 
			- Check permission of user A: add and edit, view and comment on tasks of the project
		*Input Data: 
			
		*Expected Outcome: 
			user A can add and edit, view and comment on tasks of the project*/
		info("check comment of John");
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", child));
		click(mgProject.ELEMENT_TASK_TITLE.replace("$task", task));
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", DATA_NAME_USER1).replace("$comment", comment1));
		mgTask.addTaskComment(comment2);
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", DATA_NAME_USER2).replace("$comment", comment2));
		
		info("login as john");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		info("open task page");
		hp.goToTasks();
		info("delete data");
		mgProject.selectOpContMenuGivenProject(parent,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(parent, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", parent));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", child));
 	}

	/**
	*<li> Case ID:128226.</li>
	*<li> Test Case Name: Check manager permission when sharing project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is createdsome tasks are added to project Auser A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckManagerPermissionWhenSharingProject() {
		info("Test 2: Check manager permission when sharing project");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] user1 = {DATA_USER2};
		
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(project,"", false);
		mgTask.addTask(project, task);
		mgTask.addTaskComment(comment1);
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", DATA_NAME_USER1).replace("$comment", comment1));
		/*Step number: 2
		*Step Name: Step 2: Share project A with user A
		*Step Description: 
			- Click on contextual menu of project A on left pane
			- Click on Share to share manager permission with user A
		*Input Data: 
			
		*Expected Outcome: 
			- project A is shared to user A with manager permission*/
		info("share project with mary");
		mgProject.selectOpContMenuGivenProject(project, optionContMenuGivenProject.Share);
		mgProject.shareProject(user1, true);
		
		/*Step number: 3
		*Step Name: Step 3: Login as user A
		*Step Description: 
			- Login as user A
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		info("login as mary");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		info("open task page");
		hp.goToTasks();
		
		/*Step number: 4
		*Step Name: Step 4: Check manager permission
		*Step Description: 
			- Check manager permission of user A:+ edit, delete, share, edit workflow of a project+ add and edit, view and comment on tasks of the project
		*Input Data: 
			
		*Expected Outcome: 
			- user A can edit, delete, share, edit workflow of a project
			- user A can add and edit, view and comment on tasks of the project*/
		info("have full permisson on project");
		mgProject.goToContMenuGivenProject(project);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_DELETE.replace("$project", project));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_SHARE.replace("$project", project));
		info("check comment of John");
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
		click(mgProject.ELEMENT_TASK_TITLE.replace("$task", task));
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", DATA_NAME_USER1).replace("$comment", comment1));
		mgTask.addTaskComment(comment2);
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", DATA_NAME_USER2).replace("$comment", comment2));
		
		info("delete data");
		mgProject.selectOpContMenuGivenProject(project,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(project, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", project));
 	}

	/**
	*<li> Case ID:128239.</li>
	*<li> Test Case Name: Check Message pop up in case of no permission access.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created but not share to user Aproject A1 is child of project A, and share to user A</li>
	*<li> Post-Condition: </li>
	* NOT IMPLEMENT YET
	*/
	@Test (groups="pending")
	public  void test04_CheckMessagePopUpInCaseOfNoPermissionAccess() {
		info("Test 4: Check Message pop up in case of no permission access");
		/*Step Number: 1
		*Step Name: Step 1: Login as user A
		*Step Description: 
			-Login as user A
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/

		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 3
		*Step Name: Step 3: Check case user has share permission on sub
		-project but don't have permission on the parent
		*Step Description: 
			- Click on project A on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- pop-up is opened+ Pop up title: Project Permission+ Content: You don't have permission to access $Project project.+ Action button: OK 
			- to close the pop up.*/ 

 	}

	/**
	*<li> Case ID:128230.</li>
	*<li> Test Case Name: Check Space manager automatically get manager permission.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedspaceA is createduser A is manager of spaceATask application is added to spaceA</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128229.</li>
	*<li> Test Case Name: Check Space members automatically get participant permission.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedspaceA is createduser A is member of spaceATask application is added to spaceA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_06_CheckSpaceManagerAutomaticallyGetManagerPermission() {
		info("Test 5: Check Space manager automatically get manager permission");
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String comment2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(DATA_USER2,false,"");
		
		info("open task page");
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Check manager permission of user A on spaceA project
		*Step Description: 
			- Check manager permission of user A on spaceA project:+ can add and edit, view and comment on tasks of spaceA project+ can edit, delete, share, edit workflow of spaceA project
		*Input Data: 
			
		*Expected Outcome: 
			- user Acan add and edit, view and comment on tasks of spaceA project
			- user A can edit, delete, share, edit workflow of spaceA project*/ 
		info("add task to space");
		mgTask.addTask(space, task);
		mgTask.addTaskComment(comment1);
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", DATA_NAME_USER1).replace("$comment", comment1));
		
		info("check manager permission");
		mgProject.goToContMenuGivenProject(space);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_DELETE.replace("$project", space));
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_SHARE.replace("$project", space));
		
		/*Step Number: 1
		*Step Name: Step 1: Login as user A
		*Step Description: 
			- Login as user A
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		info("login as mary");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToMySpaces();
		spaMg.acceptAInvitation(space);
		info("open task page");
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Check participant permission of user A on spaceA project
		*Step Description: 
			- Check participant permission of user A on spaceA project:+ can add and edit, view and comment on tasks of spaceA project
		*Input Data: 
			
		*Expected Outcome: 
			- user Acan add and edit, view and comment on tasks of spaceA project*/ 
		info("check comment of John");
		click(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", space));
		click(mgProject.ELEMENT_TASK_TITLE.replace("$task", task));
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", DATA_NAME_USER1).replace("$comment", comment1));
		mgTask.addTaskComment(comment2);
		waitForAndGetElement(mgTask.ELEMENT_RIGHT_PANE_COMMENT_TEXT.replace("$user", DATA_NAME_USER2).replace("$comment", comment2));
 	}

	/**
	*<li> Case ID:128228.</li>
	*<li> Test Case Name: Check Sub-projects inherit permissions from the parent project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A, share project A with user A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckSubprojectsInheritPermissionsFromTheParentProject() {
		info("Test 7: Check Sub-projects inherit permissions from the parent project");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] user1 = {DATA_USER2};

		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		info("add participant");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Share);
		mgProject.shareProject(user1, false);
		
		/*Step number: 2
		*Step Name: Step 2: Create sub
		-project of project A
		*Step Description: 
			- Add Project from contextual menu of project A: project A1
		*Input Data: 
			
		*Expected Outcome: 
			- project A1 is added to parent project A
			- in Project Overview, manager is creator, paticipant is user A (permission is inherit from parent)*/ 
		info("add project to project 1");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Add_Project);
		mgProject.addProject(prj11,"", false);
		mgProject.selectOpContMenuGivenProject(prj11, optionContMenuGivenProject.Share);
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_DISPLAY_PARTICIPANT.replace("$user",DATA_NAME_USER2));
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_DISPLAY_MANAGER.replace("$user",DATA_NAME_USER1));
		click(mgProject.ELEMENT_CLOSE_BTN);
		
		info("delete data");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj11));
		
 	}

	/**
	*<li> Case ID:128224.</li>
	*<li> Test Case Name: Open Share pop-up.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_OpenSharePopup() {
		info("Test 8: Open Share pop-up");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		info("open task page");
		hp.goToTasks();
		info("add project from Projects");
		mgProject.selectOpContMenuProject(optionContMenuProject.Add_Project);
		mgProject.addProject(prj1,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Open Share pop
		-up
		*Step Description: 
			- Click on contextual menu of project A on left pane
			- Click on Share
		*Input Data: 
			
		*Expected Outcome: 
			- there is a pop up that can be opened from the action menu to set permissions for a project.
			- Project Share popup follows UX Permission Setting Spec*/ 
		info("open Share popup");
		mgProject.selectOpContMenuGivenProject(prj1, optionContMenuGivenProject.Share);
		click(mgProject.ELEMENT_SHARE_PROJECT_EDIT_MANAGER_ICON);
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_EDIT_MANAGER_INPUT);
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SAVE_BTN);
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SELECT_GROUP_ICON);
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_EDIT_MANAGER_SELECT_USER_ICON);
		click(mgProject.ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_ICON);
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_INPUT);
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SAVE_BTN);
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SELECT_GROUP_ICON);
		waitForAndGetElement(mgProject.ELEMENT_SHARE_PROJECT_EDIT_PARTICIPANT_SELECT_USER_ICON);
		
		info("delete data");
		mgProject.selectOpContMenuGivenProject(prj1,optionContMenuGivenProject.Delete);
		mgProject.deleteProject(prj1, true);
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", prj1));
 	}}