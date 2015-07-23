package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;

	public class TASK_Projects_ShowHideProject extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131004.</li>
	*<li> Test Case Name: Check a project can be hidden from contextual menu > Hide.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128237.</li>
	*<li> Test Case Name: Check when a project is hidden.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_08_CheckAProjectCanBeHiddenFromContextualMenuHide() {
		info("Test 1: Check a project can be hidden from contextual menu > Hide");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(name,"", false);
		mgProject.goToProjects();
		
		/*Step number: 2
		*Step Name: Step 2: Check a project can be hidden from contextual menu > Hide
		*Step Description: 
			- From contextual menu of project A > Hide
		*Input Data: 
			
		*Expected Outcome: 
			project A is hidden*/ 
		mgProject.hideProject(name);
		
		info("delete data");
		mgProject.showProject(name);
		mgProject.deleteProject(name, false);
 	}

	/**
	*<li> Case ID:128241.</li>
	*<li> Test Case Name: Check display of hidden project.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A, project B are created and hidden</li>
	*<li> Post-Condition: </li>
	* CANNOT AUTOMAT displayed with the opacity 50%
	*/
	@Test (groups="pending")
	public  void test02_CheckDisplayOfHiddenProject() {
		info("Test 2: Check display of hidden project");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Check when Show Hidden Projects is clicked
		*Step Description: 
			-from the contextual menu of Projects > Show Hidden Projects
		*Input Data: 
			
		*Expected Outcome: 
			- all hidden projects are displayed with the opacity 50%*/ 

 	}

	/**
	*<li> Case ID:128243.</li>
	*<li> Test Case Name: Check display when hidden project is shown.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created and hidden</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMAT displayed with the opacity 50%
	*/
	@Test (groups="pending")
	public  void test03_CheckDisplayWhenHiddenProjectIsShown() {
		info("Test 3: Check display when hidden project is shown");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Check when Show Hidden Projects is clicked
		*Step Description: 
			-from the contextual menu of Projects > Show Hidden Projects
		*Input Data: 
			
		*Expected Outcome: 
			- all hidden projects are displayed with the opacity 50%*/

		/*Step number: 3
		*Step Name: Step 3: Check display when hidden project is shown
		*Step Description: 
			-from the contextual menu of project A > Show
		*Input Data: 
			
		*Expected Outcome: 
			- project A can be shown with opacity 100%*/ 

 	}

	/**
	*<li> Case ID:128240.</li>
	*<li> Test Case Name: Check hidden projects can be shown from the contextual menu.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created and hidden</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:128242.</li>
	*<li> Test Case Name: Check Hide Hidden Projects.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedproject A is created and hidden</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_05_CheckHiddenProjectsCanBeShownFromTheContextualMenu() {
		info("Test 4: Check hidden projects can be shown from the contextual menu");
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
		mgProject.goToProjects();
		mgProject.hideProject(name);
		
		/*Step number: 2
		*Step Name: Step 2: Check Hide Hidden Projects
		*Step Description: 
			- From the contextual menu of Projects > Show Hidden Projects
		*Input Data: 
			
		*Expected Outcome: 
			- project A is shown
			- Show Hidden Projects is turned into Hide Hidden Projects to hide all being displayed hidden projects again*/
		mgProject.showProject(name);
		
		info("delete data");
		mgProject.deleteProject(name, false);
 	}

	/**
	*<li> Case ID:131008.</li>
	*<li> Test Case Name: Check hide project is personal display settings.</li>
	*<li> Pre-Condition: exo-tasks add-on is installeduser A created project A and share to user B</li>
	*<li> Post-Condition: </li>
	*BUG: https://jira.exoplatform.org/browse/TA-182
	*/
	@Test (groups="pending")
	public  void test06_CheckHideProjectIsPersonalDisplaySettings() {
		info("Test 6: Check hide project is personal display settings");
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] user1 = {DATA_USER2};
		/*Step Number: 1
		*Step Name: Step 1: Login as user A
		*Step Description: 
			- Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Login successfully*/
		
		/*Step number: 2
		*Step Name: Step 2: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(name,"", false);
		mgProject.goToProjects();
		mgProject.shareProject(name,user1, true);
		
		/*Step number: 3
		*Step Name: Step 3: Hide project A
		*Step Description: 
			- From contextual menu of project A> Hide
		*Input Data: 
			
		*Expected Outcome: 
			project A is hidden*/
		mgProject.hideProject(name);
		
		/*Step number: 4
		*Step Name: Step 4: Login as user B
		*Step Description: 
			- Login to PLF
		*Input Data: 
			
		*Expected Outcome: 
			Login successfully*/
		info("login as mary");
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		
		/*Step number: 5
		*Step Name: Step 5: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 6
		*Step Name: Step 6: Check hide project is personal display settings
		*Step Description: 
			- Check project A
		*Input Data: 
			
		*Expected Outcome: 
			project A is still displayed (not hidden)*/ 
		waitForTextPresent(name);
		
		info("delete data");
		mgProject.deleteProject(name, false);
	
 	}

	/**
	*<li> Case ID:131005.</li>
	*<li> Test Case Name: Check sub-projects when a project is hidden.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A1, project A2 to project A</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckSubprojectsWhenAProjectIsHidden() {
		info("Test 7: Check sub-projects when a project is hidden");
		String prj1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String prj12 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String[] subs = {prj11,prj12};
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		mgProject.addProject(prj1,"", false);
		mgProject.addSubProject(prj1,prj11,"", false);
		mgProject.addSubProject(prj1,prj12,"", false);
		mgProject.goToProjects();
		
		/*Step number: 2
		*Step Name: Step 2: Check sub
		-projects when a project is hidden
		*Step Description: 
			- Hide project A
		*Input Data: 
			
		*Expected Outcome: 
			project A1, project A1 are hidden also*/ 
		mgProject.hideProject(prj1,subs);
		
		info("delete data");
		mgProject.showProject(prj1);
		mgProject.deleteProject(prj1, true,subs);
 	}

	/**
	*<li> Case ID:131007.</li>
	*<li> Test Case Name: Check when all sub projects are hidden.</li>
	*<li> Pre-Condition: exo-tasks add-on is installedadd project A1,project A2 to project A</li>
	*<li> Post-Condition: </li>
	*NOT IMPLEMENT YET
	*/
	@Test (groups="pending")
	public  void test09_CheckWhenAllSubProjectsAreHidden() {
		info("Test 9: Check when all sub projects are hidden");
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/

		/*Step number: 2
		*Step Name: Step 2: Check when all sub projects are hidden
		*Step Description: 
			- From contextual menu of project A> Hide
		*Input Data: 
			
		*Expected Outcome: 
			There is not arrow icon of the project A to indicate it has children.*/ 

 	}}