package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.task.ManagementProjects.optionContMenuGivenProject;

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
		
		hp.goToTasks();
		mgProject.addProject(parent,parent,"", false);
		mgProject.addSubProject(parent,child, "","",false);
		mgTask.addTask(child, task);
		mgTask.addTaskComment(task,DATA_NAME_USER1,comment1);
		
		info("share project with mary");
		mgProject.shareProject(child,user1, false);
		
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
		mgProject.checkPermOpenProject(parent,false);
		
		/*Step number: 4
		*Step Name: Step 4: Check member permission
		*Step Description: 
			- Check permission of user A: add and edit, view and comment on tasks of the project
		*Input Data: 
			
		*Expected Outcome: 
			user A can add and edit, view and comment on tasks of the project*/
		mgTask.checkCommentOfUser(child, task, comment1,DATA_NAME_USER1);
		mgTask.addTaskComment(task,DATA_NAME_USER2,comment2);
		
		info("login as john");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		
		info("delete data");
		mgProject.deleteProject(parent, true,child);
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
		hp.goToTasks();
		mgProject.addProject(project,"", "",false);
		mgTask.addTask(project, task);
		mgTask.addTaskComment(task,DATA_NAME_USER1,comment1);
		
		/*Step number: 2
		*Step Name: Step 2: Share project A with user A
		*Step Description: 
			- Click on contextual menu of project A on left pane
			- Click on Share to share manager permission with user A
		*Input Data: 
			
		*Expected Outcome: 
			- project A is shared to user A with manager permission*/
		mgProject.shareProject(project,user1, true);
		
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
		mgProject.checkMenuGivenProjectOfUser(project, true);
		mgTask.checkCommentOfUser(project, task, comment1,DATA_NAME_USER1);
		mgTask.addTaskComment(task,DATA_NAME_USER2,comment2);
		
		info("delete data");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:128239.</li>
	*<li> Test Case Name: Check Message pop up in case of no permission access.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed
	*project A is created but not share to user Aproject A1 is child of project A, and share to user A</li>
	*<li> Post-Condition: </li>
	*https://jira.exoplatform.org/browse/TA-330
	*/
	@Test (groups="pending")
	public  void test04_CheckMessagePopUpInCaseOfNoPermissionAccess() {
		info("Test 4: Check Message pop up in case of no permission access");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String [] users={DATA_USER3};
		hp.goToTasks();
		mgProject.addProject(prj1,"", "",false);
		mgProject.addSubProject(prj1, prj11, "", "",false);
		mgProject.shareProject(prj11, users, false);
		
		/*Step Number: 1
		*Step Name: Step 1: Login as user A
		*Step Description: 
			-Login as user A
		*Input Data: 
			
		*Expected Outcome: 
			- Login successfully*/
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		
		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Check case user has share permission on sub
		-project but don't have permission on the parent
		*Step Description: 
			- Click on project A on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- pop-up is opened+ Pop up title: Project Permission+ Content: You don't have permission to access $Project project.+ Action button: OK 
			- to close the pop up.*/ 
		mgProject.checkPermOpenProject(prj1, false);
		
		info("delete data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToTasks();
		mgProject.deleteProject(prj1, true);
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
		Utils.pause(2000);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.inviteUser(DATA_USER2,false,"");
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Check manager permission of user A on spaceA project
		*Step Description: 
			- Check manager permission of user A on spaceA project:+ can add and edit, view and comment on tasks of spaceA project+ can edit, delete, share, edit workflow of spaceA project
		*Input Data: 
			
		*Expected Outcome: 
			- user Acan add and edit, view and comment on tasks of spaceA project
			- user A can edit, delete, share, edit workflow of spaceA project*/ 
		mgTask.addTask(space, task);
		mgTask.addTaskComment(task,DATA_NAME_USER1,comment1);
		mgProject.checkMenuGivenProjectOfUser(space, true);
		
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
		hp.goToTasks();
		
		/*Step number: 3
		*Step Name: Step 3: Check participant permission of user A on spaceA project
		*Step Description: 
			- Check participant permission of user A on spaceA project:+ can add and edit, view and comment on tasks of spaceA project
		*Input Data: 
			
		*Expected Outcome: 
			- user Acan add and edit, view and comment on tasks of spaceA project*/ 
		mgTask.checkCommentOfUser(space, task, comment1, DATA_NAME_USER1);
		mgTask.addTaskComment(task,DATA_NAME_USER2,comment2);
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
		String[] user0 = {DATA_USER1};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"","", false);
		mgProject.shareProject(prj1,user1, false);
		
		/*Step number: 2
		*Step Name: Step 2: Create sub
		-project of project A
		*Step Description: 
			- Add Project from contextual menu of project A: project A1
		*Input Data: 
			
		*Expected Outcome: 
			- project A1 is added to parent project A
			- in Project Overview, manager is creator, paticipant is user A (permission is inherit from parent)*/ 
		mgProject.addSubProject(prj1,prj11,"","", false);
		mgProject.checkShareUsers(prj11, user0, user1);
		
		info("delete data");
		mgProject.deleteProject(prj1, true,prj11);
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
		hp.goToTasks();
		mgProject.addProject(prj1,"","", false);
		
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
		mgProject.checkShareProjectPopup();
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}}