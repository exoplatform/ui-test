package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class TASK_Labels_EditLabel extends TASK_TestConfig_1{

	/**
	*<li> Case ID:131554.</li>
	*<li> Test Case Name: Check case the label title is left blank.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckCaseTheLabelTitleIsLeftBlank() {
		info("Test 1: Check case the label title is left blank");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String label11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		mgLabel.addSubLabel(label1,label11 );
		
		/*Step number: 2
		*Step Name: Step 2: Open Edit Label popup
		*Step Description: 
			- Click on menu of labelA > Edit
		*Input Data: 
			
		*Expected Outcome: 
			- Edit Label popup opens*/
		mgLabel.editLabel(label11, "","Label");
		
		/*Step number: 3
		*Step Name: Step 3: Check case the label title is left blank
		*Step Description: 
			- Left Label Name blank
		*Input Data: 
			
		*Expected Outcome: 
			- label is saved as Untitled Label*/ 
		waitForAndGetElement(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label", "Untitled Label"));
		
		info("delete data");
		mgLabel.deleteLabel("Untitled Label");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131549.</li>
	*<li> Test Case Name: Check label can be edited via the contextual menu.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131551.</li>
	*<li> Test Case Name: Check Label popup when a label is edited.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_03_CheckLabelPopupWhenALabelIsEdited() {
		info("Test 3: Check Label popup when a label is edited");
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
		*Step Name: Step 2: Check Label popup when a label is edited
		*Step Description: 
			- Click on menu of labelA> Edit
		*Input Data: 
			
		*Expected Outcome: 
			Edit Label pop up is displayed:
			- Label Parent: The parent path of the current label.
			- Label Name: The title of the label.
			- [Save]: Save all changes.
			- [Cancel]: Dismiss the pop up without making a change*/ 
		mgLabel.checkEditLabelPopup(label1,"Label");
		
		info("delete data");
		mgLabel.deleteLabel(label1);
 	}

	/**
	*<li> Case ID:131553.</li>
	*<li> Test Case Name: Check Parent field does not display the current label and its descendants.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA,labelB are added to Labels
	- labelA1 is added to labelA</li>
	*<li> Post-Condition: </li>
	*/
	/**
	*<li> Case ID:131552.</li>
	*<li> Test Case Name: Check select box to change label parent.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created
	- labelB is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_06_CheckParentFieldDoesNotDisplayTheCurrentLabelAndItsDescendants() {
		info("Test 4: Check Parent field does not display the current label and its descendants");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String label11 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String label2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		int label1_id= mgLabel.getDataId(ELEMENT_LEFT_PANE_LABEL_NAME.replace("$label", label1));
		mgLabel.addLabel(label2);
		mgLabel.addSubLabel(label1, label11);
		
		/*Step number: 2
		*Step Name: Step 2: Open Edit Label popup
		*Step Description: 
			- Click on menu of labelA > Edit
		*Input Data: 
			
		*Expected Outcome: 
			- Edit Label popup opens*/

		/*Step number: 3
		*Step Name: Step 3: Check Parent field does not display the current label and its descendants
		*Step Description: 
			- Click on Label Parent
		*Input Data: 
			
		*Expected Outcome: 
			- list of label is displayed:LabelslabelB*/ 
		mgLabel.checkListLabelInParent(label1, true, "0",String.valueOf(label1_id+1));
		mgLabel.checkListLabelInParent(label1, false, String.valueOf(label1_id),String.valueOf(label1_id+2));
		
		info("delete data");
		mgLabel.deleteLabel(label1);
		mgLabel.deleteLabel(label2);
 	}

	/**
	*<li> Case ID:131555.</li>
	*<li> Test Case Name: Check save button on Edit label popup.</li>
	*<li> Pre-Condition: - exo-tasks add-on is installed
	- labelA is created
	- labelB is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_CheckSaveButtonOnEditLabelPopup() {
		info("Test 5: Check save button on Edit label popup");
		String label1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String label2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String name = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		/*Step Number: 1
		*Step Name: Step 1: Open Tasks page
		*Step Description: 
			- Click on Tasks on the left navigation.
		*Input Data: 
			
		*Expected Outcome: 
			- Tasks page is opened*/
		hp.goToTasks();
		mgLabel.addLabel(label1);
		mgLabel.addLabel(label2);
		
		/*Step number: 2
		*Step Name: Step 2: Open Edit Label popup
		*Step Description: 
			- Click on menu of labelA > Edit
		*Input Data: 
			
		*Expected Outcome: 
			- Edit Label popup opens
			- Save button is disable*/
		
		/*Step number: 3
		*Step Name: Step 3: Check save button on Edit label popup
		*Step Description: 
			- Edit name of label
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			- Save is enable
			- new name is saved*/
		mgLabel.editLabel(label1, name, "");
		
		/*Step number: 4
		*Step Name: Step 4: Open Edit Label popup
		*Step Description: 
			- Click on menu of labelA > Edit
		*Input Data: 
			
		*Expected Outcome: 
			- Edit Label popup opens
			- Save button is disable*/

		/*Step number: 5
		*Step Name: Step 5: Check save button on Edit label popup
		*Step Description: 
			- Change parent of labelA to labelB
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			- Save is enable
			- new parent is saved*/ 
		mgLabel.editLabel(name, "", label2);
		
		info("delete data");
		mgLabel.deleteLabel(label2);
 	}
}