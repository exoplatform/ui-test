package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_Labels_DeleteLabel extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131559.</li>
	*<li> Test Case Name: Check a label can be deleted via contextual menu.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131561.</li>
	*<li> Test Case Name: Check delete label action.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_04_CheckALabelCanBeDeletedViaContextualMenu() {
		info("Test 1: Check a label can be deleted via contextual menu");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		
		/*Step number: 2
		*Step Name: Step 2: Check a label can be deleted via contextual menu
		*Step Description: 
			- Click on menu of labelA > Delete
		*Input Data: 
			
		*Expected Outcome: 
			- labelA is deleted*/ 
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131562.</li>
	*<li> Test Case Name: Check Cancel action when delete label.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_CheckCancelActionWhenDeleteLabel() {
		info("Test 2: Check Cancel action when delete label");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		
		/*Step number: 2
		*Step Name: Step 2: Check delete label action
		*Step Description: 
			- Click on menu of labelA> Delete
			- Click on Cancel button
		*Input Data: 
			
		*Expected Outcome: 
			- confirmation dialog opens
			- labelA is not deleted*/ 
		mgLabel.checkCancelDeleteLabel(label1);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131560.</li>
	*<li> Test Case Name: Check confirmation dialog when detete label.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_CheckConfirmationDialogWhenDeteteLabel() {
		info("Test 3: Check confirmation dialog when detete label");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		
		/*Step number: 2
		*Step Name: Step 2: Check confirmation dialog when detete label
		*Step Description: 
			- Click on menu of labelA> Delete
		*Input Data: 
			
		*Expected Outcome: 
			A confirmation dialog opens:
			- title : Confirmation
			- message : Are you sure you want to delete $Label label?
			- actions : [Delete] [Cancel]*/ 
		mgLabel.checkDeleteLabelPopup(label1);
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}
}