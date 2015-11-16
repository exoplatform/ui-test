package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_Labels_ChangeLabelColor extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131556.</li>
	*<li> Test Case Name: Check a label can be set color from contextual menu.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckALabelCanBeSetColorFromContextualMenu() {
		info("Test 1: Check a label can be set color from contextual menu");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		
		/*Step number: 2
		*Step Name: Step 2: Check a label can be set color from contextual menu
		*Step Description: 
			- Click on contextual menu of labelA
		*Input Data: 
			
		*Expected Outcome: 
			- No Color and a table of color are opened*/ 
		mgLabel.addLabel(label1);
		mgLabel.checkColorTable(label1);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131557.</li>
	*<li> Test Case Name: Check when a color is ticked.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created
	- taskA is added to labelA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckWhenAColorIsTicked() {
		info("Test 2: Check when a color is ticked");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String color1 = colorData.getClassNameByArrayTypeRandom(1);
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		mgTask.addTaskDirectly(task1, true);
		
		/*Step number: 2
		*Step Name: Step 2: Check when a color is ticked
		*Step Description: 
			- Click on menu of labelA
			- Choose red color
		*Input Data: 
			
		*Expected Outcome: 
			- There is a block with red color before labelA on left pane and before taskA on central pane*/ 
		mgLabel.selectColor(label1, color1, task1);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131558.</li>
	*<li> Test Case Name: Check when No Color is ticked.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created
	- taskA is added to labelA</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckWhenNoColorIsTicked() {
		info("Test 3: Check when No Color is ticked");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String task1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		mgTask.addTaskDirectly(task1, true);
		
		/*Step number: 2
		*Step Name: Step 2: Check when No Color is ticked
		*Step Description: 
			- Click on menu of labelA
			- Choose No Color
		*Input Data: 
			
		*Expected Outcome: 
			- There is no color block in front of the labelA and taskA*/ 
		mgLabel.selectNoColor(label1,task1);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}}