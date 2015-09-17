package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;

	public class TASK_Projects_CreateProject extends TASK_TestConfig_1{

	/**
	*<li> Case ID:128209.</li>
	*<li> Test Case Name: A project can be saved with the duplicated title.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed, add project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_AProjectCanBeSavedWithTheDuplicatedTitle() {
		info("Test 1: A project can be saved with the duplicated title");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();	
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new project with duplicate title
		*Step Description: 
			- Add Project from contextual menu of Projects on left pane: project A
		*Input Data: 
			
		*Expected Outcome: 
			- another project A is added*/ 
		mgProject.addProject(name,"", false);
		int prj1_id= mgProject.getDataId(mgProject.ELEMENT_LEFT_PANE_PROJECT_NAME.replace("$project", name));
		mgProject.addProject(name,"", false);
		waitForAndGetElement(mgProject.ELEMENT_LEFT_PANE_PROJECT_ID
				.replace("$id",String.valueOf(prj1_id+1))
				.replace("$project", name));
		
		info("delete data");
		mgProject.deleteProjectByDataId(name,String.valueOf(prj1_id), false);
		mgProject.deleteProjectByDataId(name,String.valueOf(prj1_id+1), false);
 	}

	/**
	*<li> Case ID:128203.</li>
	*<li> Test Case Name: Change project parent.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A,add project B as child of project A</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128204.</li>
	*<li> Test Case Name: Check auto-completion drop-down displaying the project list.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A,add project A1 as child of project Aadd project B,add project B1 as child of project Bproject A,project B are childs of Projects</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test02_03_ChangeProjectParent() {
		info("Test 2: Change project parent");
		String parent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String child = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(name,"", false);
		mgProject.addProject(parent,"", false);
		mgProject.addSubProject(parent,child, "", false);
		
		/*Step number: 2
		*Step Name: Step 2: Open project B
		*Step Description: 
			- Click on project B on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Project Overview of project B is opened to edit*/

		/*Step number: 3
		*Step Name: Step 3: Change project parent
		*Step Description: 
			- On project overview, hover on project path to locate project B to Projects
		*Input Data: 
			
		*Expected Outcome: 
			- project B is now child of Projects, not child of project A*/ 
		info("locate child project to parent Projects");
		mgProject.changeParentProject("Projects");
		waitForElementNotPresent(mgProject.ELEMENT_LEFT_PANE_SUBPROJECT_L1.replace("sub", child));
		
		info("Test 3: Check auto-completion drop-down displaying the project list");
		click(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_LINK);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU.replace("$project", "Projects"));
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU.replace("$project", name));
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU.replace("$project", parent));
		
		info("delete data");
		mgProject.deleteProject(name, false);
		mgProject.deleteProject(child, false);
		mgProject.deleteProject(parent, false);
 	}

	/** 
	*<li> Case ID:130909.</li>
	*<li> Test Case Name: Check Cancel action when creating project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is added</li>
	*<li> Post-Condition: </li>
	* NOT YET IMPLEMENT
	*/
	@Test 
	public  void test04_CheckCancelActionWhenCreatingProject() {
		info("Test 4: Check Cancel action when creating project");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		/*Step number: 2
		*Step Name: Step 2: Open project A
		*Step Description: 
			- click on Projects> project A
		*Input Data: 
			
		*Expected Outcome: 
			- project A is opened*/
		/*Step number: 3
		*Step Name: Step 3: Edit project A
		*Step Description: 
			- Edit some field of project A
			- Click on Cancel
		*Input Data: 
			
		*Expected Outcome: 
			- Save is enable
			- Dismiss the pop up without making a change.*/ 

 	}

	/**
	*<li> Case ID:130911.</li>
	*<li> Test Case Name: Check default setting after creating project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test05_CheckDefaultSettingAfterCreatingProject() {
		info("Test 5: Check default setting after creating project");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String sort = sortByData.getSortBy(0);
		String group = groupByData.getGroupBy(0);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project,"", false);
		mgTask.addTask(project, task1);
		mgTask.addTask(project, task2);
		mgTask.addTask(project, task3);
		
		/*Step number: 2
		*Step Name: Step 2: Check default setting when creating project
		*Step Description: 
			- Add project from contextual menu of Projects
		*Input Data: 
			
		*Expected Outcome: 
			Project is created, Default settings in a group:
			- Group by: None 
			- Sort by: Due Date*/ 
		mgProject.checkDefaultGroupSort(project, group, sort,false);
		
		info("delete data");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:128219.</li>
	*<li> Test Case Name: Check description can be decorated.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test06_CheckDescriptionCanBeDecorated() {
		info("Test 6: Check description can be decorated");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String des = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create new project
		*Step Description: 
			- Add Project from contextual menu on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- new project is created*/
		mgProject.addProject(project,"", false);
		
		/*Step number: 3
		*Step Name: Step 3: Check description can be decorated
		*Step Description: 
			- On right pane, add description with simple effect: bold, italic, underline, strike-through, list... and save- left blank description and save
		*Input Data: 
			
		*Expected Outcome: 
			- description is saved with decorated
			- description can be left blank*/ 
		mgProject.decorateDescription(project, des);
		mgProject.decorateDescription(project, "");
		
		info("delete data");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:130910.</li>
	*<li> Test Case Name: Check display after creating project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckDisplayAfterCreatingProject() {
		info("Test 7: Check display after creating project");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();

		/*Step number: 2
		*Step Name: Step 2: Check display after creating project
		*Step Description: 
			- Add project from contextual menu of Projects
		*Input Data: 
			
		*Expected Outcome: 
			Project is created: it becomes the being selected one, new project welcome screen is displayed*/ 
		mgProject.addProject(name,"", false);
		mgProject.checkProjectDetail(name);
		
		info("delete data");
		mgProject.deleteProject(name, false);
 	}

	/**
	*<li> Case ID:128210.</li>
	*<li> Test Case Name: Check display of project title.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTOMATE name+...
	*/ 
	@Test
	public  void test08_CheckDisplayOfProjectTitle() {
		info("Test 8: Check display of project title");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		
		/*Step number: 2
		*Step Name: Step 2: Check display of project title
		*Step Description: 
			- Add Project with long title from contextual menu of Projects
		*Input Data: 
			
		*Expected Outcome: 
			Project title is displayed on 1 row in the left menu and the middle pane. In case it is longer than the width of left menu and middle panel, it is cut off + "..." automatically.*/ 
		
 	}

	/**
	*<li> Case ID:128197.</li>
	*<li> Test Case Name: Check group by of tasks in a project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project Aadd some tasks to project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test09_CheckGroupByOfTasksInAProject() {
		info("Test 9: Check group by of tasks in a project");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String none = groupByData.getGroupBy(0);
		String duedate = groupByData.getGroupBy(1);
		//String label = groupByData.getGroupBy(2);
		String status = groupByData.getGroupBy(3);
		String assignee = groupByData.getGroupBy(4);
		String[] groups={none,duedate,status,assignee};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project,"", false);
		mgTask.addTask(project, task1);
		mgTask.addTask(project, task2);
		mgTask.addTask(project, task3);
		
		/*Step number: 2
		*Step Name: Step 2: Check group by of tasks in a project
		*Step Description: 
			- Click on project A on left pane
			- Click on Group by on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks of project A are Grouped by: None, Due Date, Label, Assignee, Status.*/ 
		mgProject.checkGroupByInProjects(project, groups,false);
	
		info("delete project");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:128189.</li>
	*<li> Test Case Name: Check input field when Add Project is clicked.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: wrong text Enable Calendar integration 
	*/
	@Test (groups = "pending")
	public  void test10_CheckInputFieldWhenAddProjectIsClicked() {
		info("Test 10 Check input field when Add Project is clicked");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check input field when Add Project is clicked
		*Step Description: 
			- Click on Add Project from contextual menu on left pane
		*Input Data: 
			
		*Expected Outcome: 
			Add Project popup is displayed.
			- Popup header: Add Project.
			- Path of project parent.
			- Project title field with text holder Untitled Project.
			- Editor with text holder 
			- Description.
			- Enable Calendar Integration option.
			- Save and Cancel button.*/ 
		mgProject.checkAddProjectPopup();
 	}

	/**
	*<li> Case ID:128202.</li>
	*<li> Test Case Name: Check location of added project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckLocationOfAddedProject() {
		info("Test 11 Check location of added project");
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
		
		/*Step number: 2
		*Step Name: Step 2: Add Project from the contextual menu of Projects
		*Step Description: 
			- Add Project from the contextual menu of Projects
		*Input Data: 
			
		*Expected Outcome: 
			- New project is created with the parents value is Projects*/
		mgProject.addProject(prj1,"", false);
		
		/*Step number: 3
		*Step Name: Step 3: Add Project from the contextual menu of project A
		*Step Description: 
			- Add Project from the contextual menu of project A
		*Input Data: 
			
		*Expected Outcome: 
			- New project is created with the parents value is Projects - project A*/ 
		mgProject.addSubProject(prj1,prj11,"", false);
		
		info("delete project");
		mgProject.deleteProject(prj1, true,prj11);
	}

	/**
	*<li> Case ID:128213.</li>
	*<li> Test Case Name: Check manager field.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test12_CheckManagerField() {
		info("Test 12 Check manager field");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check manager field
		*Step Description: 
			- Add Project from contextual menu on left pane
			- Check manager field
		*Input Data: 
			
		*Expected Outcome: 
			- new project is created
			- The creator is default value of Manager field. 
			- In the project overview, the manager field is not able to click to edit.*/ 
		mgProject.addProject(prj1,"", false);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_MANAGER_NAME.replace("$user",DATA_NAME_USER1));
		
		info("delete project");
		mgProject.deleteProject(prj1, true);
 	}

	/**
	*<li> Case ID:128207.</li>
	*<li> Test Case Name: Check matched project is displayed with its full path.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A,add project A1 as child of project Aadd project B,add project B1 as child of project Bproject A,project B are childs of Projects</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_CheckMatchedProjectIsDisplayedWithItsFullPath() {
		info("Test 13 Check matched project is displayed with its full path");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"", false);
		mgProject.addSubProject(prj1,prj11, "", false);
		mgProject.addProject(prj2,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Open project A1
		*Step Description: 
			- Click on project A1 on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Project Overview of project A1 is opened to edit*/

		/*Step number: 3
		*Step Name: Step 3: Check matched project is displayed with its full path
		*Step Description: 
			- On project overview, hover on project path, input text "project B" into path field
		*Input Data: 
			
		*Expected Outcome: 
			- matched project is displayed:Projects project Bproject B1*/ 
		mgProject.searchProjectPath(prj11);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_FULL
				.replace("$parent",prj1).replace("$child", prj11));
		
		info("delete data");
		mgProject.deleteProject(prj1, true,prj11);
		mgProject.deleteProject(prj2, false);
 	}

	/**
	*<li> Case ID:130908.</li>
	*<li> Test Case Name: Check Save action when editing project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is added</li>
	*<li> Post-Condition: </li>
	* NOT YET IMPLEMENT
	*/
	@Test
	public  void test14_CheckSaveActionWhenEditingProject() {
		info("Test 14 Check Save action when editing project");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Open project A
		*Step Description: 
			- click on Projects> project A
		*Input Data: 
			
		*Expected Outcome: 
			- project A is opened*/

		/*Step number: 3
		*Step Name: Step 3: Edit project A
		*Step Description: 
			- Edit some field of project A
			- Click on Save
		*Input Data: 
			
		*Expected Outcome: 
			- Save is enable
			- Save all changes*/ 
 	}

	/**
	*<li> Case ID:128198.</li>
	*<li> Test Case Name: Check sort by of tasks in a project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project Aadd some tasks to project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test15_CheckSortByOfTasksInAProject() {
		info("Test 15 Check sort by of tasks in a project");
		String project = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task3 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String dueDate = sortByData.getSortBy(0);
		String title = sortByData.getSortBy(1);
		String createdDate = sortByData.getSortBy(2);
		String priority = sortByData.getSortBy(3);
		String[] sorts = {dueDate,title,createdDate,priority};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(project,"", false);
		mgTask.addTask(project, task1);
		mgTask.addTask(project, task2);
		mgTask.addTask(project, task3);
		
		/*Step number: 2
		*Step Name: Step 2: Check sort by of tasks in a project
		*Step Description: 
			- Click on project A on left pane
			- Click on Sort by on central pane
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks of project A are Sorted by: Title, Created Date, Due Date, Priority*/ 
		mgProject.checkSortByInProjects(project, sorts,false);
		
		info("delete data");
		mgProject.deleteProject(project, false);
 	}

	/**
	*<li> Case ID:128206.</li>
	*<li> Test Case Name: Check the project autocomplete filter.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A,add project A1 as child of project Aadd project B,add project B1 as child of project Bproject A,project B are childs of Projects</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128205.</li>
	*<li> Test Case Name: Current project and its descendants are not displayed in parent project autocompletion.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A,add project A1 as child of project Aadd project B,add project B1 as child of project Bproject A,project B are childs of Projects</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_test20_CheckTheProjectAutocompleteFilter() {
		info("Test 16 Check the project autocomplete filter");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj111 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj2,"", false);
		mgProject.addProject(prj1,"", false);
		mgProject.addSubProject(prj1,prj11, "", false);
		mgProject.addSubProject(prj11,prj111, "", false);
		
		/*Step number: 2
		*Step Name: Step 2: Open project A1
		*Step Description: 
			- Click on project A1 on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Project Overview of project A1 is opened to edit*/
		info("open child1");
		mgProject.openProject(prj11);
		
		/*Step number: 3
		*Step Name: Step 3: Check the project autocomplete filter
		*Step Description: 
			- On project overview, hover on project path, input text "proj" into path field
		*Input Data: 
			
		*Expected Outcome: 
			- The project autocomplete list is filtered following the input text. 
			- The matched value is bold.*/ 
		mgProject.searchProjectPath("Content");
		waitForElementNotPresent(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU.replace("$project",prj11));
		waitForElementNotPresent(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU.replace("$project",prj111));
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU.replace("$project", prj1));
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_PARENT_PATH_DROPDOWN_MENU.replace("$project", prj2));
		
		info("delete data");
		mgProject.deleteProject(prj1, true);
		mgProject.deleteProject(prj2, true);
 	}

	/**
	*<li> Case ID:128208.</li>
	*<li> Test Case Name: Check Untitled Project title after removing project title.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-147
	*/
	@Test (groups = "pending")
	public  void test17_CheckUntitledProjectTitleAfterRemovingProjectTitle() {
		info("Test 17 Check Untitled Project title after removing project title");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Open project A
		*Step Description: 
			- Click on project A on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Project Overview of project A is opened*/
		mgProject.addProject(name,"", false);
		
		/*Step number: 3
		*Step Name: Step 3: Remove project title
		*Step Description: 
			- on right pane, click on project title to edit
			- left project title blank and save
		*Input Data: 
			
		*Expected Outcome: 
			- Untitled Project title is saved*/ 
		mgProject.leftProjectTitleBlank(name);
        
        info("delete data");
		mgProject.deleteProject("Untitled Project", false);
	}

	/**
	*<li> Case ID:128187.</li>
	*<li> Test Case Name: Create a project from contextual menu.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test18_CreateAProjectFromContextualMenu() {
		info("Test 18 Create a project from contextual menu");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Create a project from contextual menu
		*Step Description: 
			- Create a project from the contextual menu of Projects: Add Project on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- new project is created under Projects on left pane*/ 
		mgProject.addProject(name,"", false);
		mgProject.checkProjectDetail(name);
		
		info("delete data");
		mgProject.deleteProject(name, false);
 	}

	/**
	*<li> Case ID:128188.</li>
	*<li> Test Case Name: Create a sub-project from contextual menu.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test19_CreateASubprojectFromContextualMenu() {
		info("Test 19 Create a sub-project from contextual menu");
		String parent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String child = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(parent,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Create a sub
		-project from contextual menu
		*Step Description: 
			- Create a project from the contextual menu of project A: Add Project on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- new sub-project is created under project A on left pane*/ 
		mgProject.addSubProject(parent,child, "", false);
		
		info("delete data");
		mgProject.deleteProject(parent, true,child);
 	}

	/**
	*<li> Case ID:128215.</li>
	*<li> Test Case Name: Update manager field.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test21_UpdateManagerField() {
		info("Test 21 Update manager field");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] user1 = {DATA_USER2};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"", false);
		
		/*Step number: 2
		*Step Name: Step 2: Update manager field by sharing project
		*Step Description: 
			- Share project A with user A with manager permission
		*Input Data: 
			
		*Expected Outcome: 
			- project A is now shared with user A*/
		mgProject.shareProject(prj1,user1, true);
		
		/*Step number: 3
		*Step Name: Step 3: Check manager field
		*Step Description: 
			- Click on project A on left pane
		*Input Data: 
			
		*Expected Outcome: 
			- Project Overview is opened with manager: user A inside*/ 
		info("mary is now manager of project 1");
		mgProject.goToProjects();
		mgProject.openProject(prj1);
		waitForAndGetElement(mgProject.ELEMENT_RIGHT_PANE_MANAGER_NAME
				.replace("$user", DATA_NAME_USER2),DEFAULT_TIMEOUT,1);
		
		info("delete data");
		mgProject.deleteProject(prj1, true);
 	}
	/**
	*<li> Case ID:131525.</li>
	*<li> Test Case Name: Check all created tasks in the project will be synchronized in calendar.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test22_CheckAllCreatedTasksInTheProjectWillBeSynchronizedInCalendar() {
		info("Test 22: Check all created tasks in the project will be synchronized in calendar");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
	
		/*Step number: 2
		*Step Name: Step 2: Add projectA with Enable Calendar Integration
		*Step Description: 
			- Add projectA from contextual menu of Projects with Enable Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created
			- calendar is automatically created: projectA Tasks*/
		mgProject.addProject(prj1,"", true);
		
		/*Step number: 3
		*Step Name: Step 3: Add taskA
		*Step Description: 
			- Add taskA to projectA with workplan
		*Input Data: 
			
		*Expected Outcome: 
			- taskA is created*/
		mgTask.addTask(prj1, task1);
		mgTask.editTaskWorkPlan(task1, getDate(0,"dd"), 0, getDate(2,"dd"), 0, "", "",true);
		
		/*Step number: 4
		*Step Name: Step 4: Open Calendar apps
		*Step Description: 
			- Click on Calendar on the left navigation
		*Input Data: 
			
		*Expected Outcome: 
			- projectA Tasks calendar opens with taskA inside*/ 
		hp.goToCalendarPage();
		cMang.checkDisplayOfTaskCal(prj1+" Tasks", true);
		tasMg.checkDisplayOfTask(task1,getDate(0,"dd"),true);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131519.</li>
	*<li> Test Case Name: Check calendar name when Enable Calendar Integration is ticked.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test23_CheckCalendarNameWhenEnableCalendarIntegrationIsTicked() {
		info("Test 23: Check calendar name when Enable Calendar Integration is ticked");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Add projectA with Enable Calendar Integration
		*Step Description: 
			- Add projectA from contextual menu of Projects with Enable Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created
			- calendar is automatically created: projectA Tasks*/
		mgProject.addProject(prj1,"", true);
		
		/*Step number: 3
		*Step Name: Step 3: Check calendar
		*Step Description: 
			- projectA Tasks is in list calendar
		*Input Data: 
			
		*Expected Outcome: 
			- projectA Tasks is in list calendar*/ 
		hp.goToCalendarPage();
		cMang.checkDisplayOfTaskCal(prj1+" Tasks", true);
		
		info("delete data");
		hp.goToTasks();
		mgProject.deleteProject(prj1, false);
 	}

	/**
	*<li> Case ID:131520.</li>
	*<li> Test Case Name: Check Enable Calendar Integration can not be editable.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test24_CheckEnableCalendarIntegrationCanNotBeEditable() {
		info("Test 24: Check Enable Calendar Integration can not be editable");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Add projectA with Enable Calendar Integration
		*Step Description: 
			- Add projectA from contextual menu of Projects with Enable Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- projectA is created
			- calendar is automatically created: projectA Tasks*/
		mgProject.addProject(prj1,"", true);
		
		/*Step number: 3
		*Step Name: Step 3: Check Enable Calendar Integration can not be editable
		*Step Description: 
			- Click on field Enable Calendar Integration
		*Input Data: 
			
		*Expected Outcome: 
			- cannot edit this field*/ 
		mgProject.checkEditableOfCalIntegrationField();
		
		info("delete data");
		mgProject.deleteProject(prj1, false);
 	}
	}