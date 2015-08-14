package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_TaskDetails_EditTask_TaskTags extends TASK_TestConfig_1{
		
	/**
	*<li> Case ID:130954.</li>
	*<li> Test Case Name: Check a task can be assigned to multiple tags.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130955.</li>
	*<li> Test Case Name: Check all tags are displayed in the same color.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_02_CheckATaskCanBeAssignedToMultipleTags() {
		info("Test 1: Check a task can be assigned to multiple tags");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tag1 = txData.getContentByArrayTypeRandom(2)+getRandomNumber();
		String tag2 = txData.getContentByArrayTypeRandom(2)+getRandomNumber();
		String tag3 = txData.getContentByArrayTypeRandom(2)+getRandomNumber();
		String[] tags={tag1,tag2,tag3};
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
		*Step Name: Step 3: Check a task can be assigned to multiple tags
		*Step Description: 
			- Click on Tag to add 3 tags
		*Input Data: 
			
		*Expected Outcome: 
			A task can be assigned to multiple tags.*/ 
		mgTask.editTaskTag(task1, tags);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130952.</li>
	*<li> Test Case Name: Check autocomplete list displaying all tags.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/TA-152
	*/
	@Test (groups="pending")
	public  void test03_CheckAutocompleteListDisplayingAllTags() {
		info("Test 3: Check autocomplete list displaying all tags");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		*Step Name: Step 3: Check autocomplete list displaying all tags
		*Step Description: 
			- Click on Tag to add tag
		*Input Data: 
			
		*Expected Outcome: 
			There is an autocomplete list displaying all tags that matches with input value.*/ 
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}

	/**
	*<li> Case ID:130953.</li>
	*<li> Test Case Name: Check case new tag is input, it can be saved as a new one.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:130951.</li>
	*<li> Test Case Name: Check display of a tag in edit mode.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_05_CheckCaseNewTagIsInputItCanBeSavedAsANewOne() {
		info("Test 4: Check case new tag is input, it can be saved as a new one");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tag = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
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
		*Step Name: Step 3: Check case new tag is input, it can be saved as a new one
		*Step Description: 
			- Click on Tag to add new tag which doesn't exist
		*Input Data: 
			
		*Expected Outcome: 
			In case new tag is input, it can be saved as a new one.*/ 
		mgTask.editTaskTag(task1, tag);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}
	/**
	*<li> Case ID:130950.</li>
	*<li> Test Case Name: Check there are tag icon and Tags label in case of no tad added.</li>
	*<li> Pre-Condition: exo-tasks add-on is installed</li>
	*<li> Post-Condition: </li>
	*/
	@Test 
	public  void test06_CheckThereAreTagIconAndTagsLabelInCaseOfNoTadAdded() {
		info("Test 6: Check there are tag icon and Tags label in case of no tad added");
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String tag = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check there are tag icon and Tags label in case of no tad added
		*Step Description: 
			- Add New Task in Incoming
		*Input Data: 
			
		*Expected Outcome: 
			- New task is created
			- By default or in case of no tag added, there are tag icon and Tags label that will be hidden when there is a tag added.*/ 
		mgTask.addTaskDirectly(task1,true);
		mgTask.checkDefaultTag(task1, true);
		mgTask.editTaskTag(task1, tag);
		mgTask.checkDefaultTag(task1,false);
		
		info("delete data");
		mgTask.deleteTask(task1);
 	}}