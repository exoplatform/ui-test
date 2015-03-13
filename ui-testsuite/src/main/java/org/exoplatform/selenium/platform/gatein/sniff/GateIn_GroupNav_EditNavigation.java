package org.exoplatform.selenium.platform.gatein.sniff;

import static org.exoplatform.selenium.TestLogger.info;
import org.testng.annotations.*;


	public class GateIn_GroupNav_EditNavigation{

	/**
	*<li> Case ID:123044.</li>
	*<li> Test Case Name: Edit priority for group navigation.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_EditPriorityForGroupNavigation() {
		info("Test 1: Edit priority for group navigation");
		/*Step Number: 1
		*Step Name: Step 1: Edit priority for group navigation
		*Step Description: 
			- Go to Administration/Portal/Group Sites
			- Select a group navigation and clickedit properties
			- Change priority for this group and click Save
		*Input Data: 
			
		*Expected Outcome: 
			- Priority is changed successfully*/ 

 	}

	/**
	*<li> Case ID:123051.</li>
	*<li> Test Case Name: Delete node for group.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_DeleteNodeForGroup() {
		info("Test 2: Delete node for group");
		/*Step Number: 1
		*Step Name: Step 1: Delete Node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node and choose Delete node by right click 
			- Click OK in the confirmation message to accept your deletion. 
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- The node is removed from the list*/ 

 	}

	/**
	*<li> Case ID:123052.</li>
	*<li> Test Case Name: Change node order.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_ChangeNodeOrder() {
		info("Test 3: Change node order");
		/*Step Number: 1
		*Step Name: Step 1: Change node order
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Select Move Up or Move Down from the drop
			-down menu
			- Click Save to accept your changes.
		*Input Data: 
			
		*Expected Outcome: 
			- Position of node is changed successfully*/ 

 	}

	/**
	*<li> Case ID:123053.</li>
	*<li> Test Case Name: Edit node's page properties.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_EditNodesPageProperties() {
		info("Test 4: Edit node's page properties");
		/*Step Number: 1
		*Step Name: Step 1: Edit node's page properties
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Choose View Page properties on Page Editor
			- Edit some changes
			- Click Save button
		*Input Data: 
			
		*Expected Outcome: 
			Page Setting, Permission setting tab is updated successfully with new changes*/ 

 	}

	/**
	*<li> Case ID:123054.</li>
	*<li> Test Case Name: Move container when edit page properties of node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_MoveContainerWhenEditPagePropertiesOfNode() {
		info("Test 5: Move container when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Move container when edit page properties of node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Select Container tab from Page Editor
			- Select container in page and drag& drop it into new place
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- The container is move to new place
			- The page is displayed in the view mode with all changes*/ 

 	}

	/**
	*<li> Case ID:123055.</li>
	*<li> Test Case Name: Move application when edit page properties of node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_MoveApplicationWhenEditPagePropertiesOfNode() {
		info("Test 6: Move application when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Move application when edit page properties of node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Select Application tab from edit inline composer
			- Move application on page by drag & drop into new place
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- The application is move to new place
			- The page is displayed in the view mode with all changes*/ 

 	}

	/**
	*<li> Case ID:123056.</li>
	*<li> Test Case Name: Add application into container when edit page properties of node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_AddApplicationIntoContainerWhenEditPagePropertiesOfNode() {
		info("Test 7: Add application into container when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Add application into container when edit page properties of node
		*Step Description: 
			- Go to Group Sties/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Select Container tab from edit inline composer
			- Add container by drag & drop
			- Select Application tab from edit inline composer
			- Drag & drop application into the container added above
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- Add application into container successfully
			- The page is displayed in the view mode with all changes*/ 

 	}

	/**
	*<li> Case ID:123111.</li>
	*<li> Test Case Name: Add application when edit page properties of node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_AddApplicationWhenEditPagePropertiesOfNode() {
		info("Test 8: Add application when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Add application when edit page properties of node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Select Application tab from edit inline composer
			- Add application by drag & drop
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- Add application successfully
			- The page is displayed in the view mode with all changes*/ 

 	}

	/**
	*<li> Case ID:123112.</li>
	*<li> Test Case Name: Edit application when edit page properties of node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_EditApplicationWhenEditPagePropertiesOfNode() {
		info("Test 9: Edit application when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Edit application when edit page properties of node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Select Application tab from edit inline composer
			- Choose application on page and click [Edit portlet]
			- Make change something
			- Click Save and Close
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- The application is updated with the change value
			- The page is displayed in the view mode with all changes*/ 

 	}

	/**
	*<li> Case ID:123113.</li>
	*<li> Test Case Name: Remove application when edit page properties of node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_RemoveApplicationWhenEditPagePropertiesOfNode() {
		info("Test 10 Remove application when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Remove application when edit page properties of node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Select Application tab from edit inline composer
			- Choose application on page and click Delete portlet
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- The application is removed successfully
			- The page is displayed in the view mode with all changes*/ 

 	}

	/**
	*<li> Case ID:123114.</li>
	*<li> Test Case Name: Add container when edit page properties of node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_AddContainerWhenEditPagePropertiesOfNode() {
		info("Test 11 Add container when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Add container when edit page properties of node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Select Container tab from Page Editor
			- Add container by drag & drop
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- Add container successfully
			- The page is displayed in the view mode with all changes*/ 

 	}

	/**
	*<li> Case ID:123115.</li>
	*<li> Test Case Name: Edit container when edit page properties of node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_EditContainerWhenEditPagePropertiesOfNode() {
		info("Test 12 Edit container when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Edit container when edit page properties of node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Select Container tab from Page Editor
			- Select Container in page and click Edit container
			- Make change something
			- Click Save
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- The container is updated with the change value
			- The page is displayed in the view mode with all changes*/ 

 	}

	/**
	*<li> Case ID:123116.</li>
	*<li> Test Case Name: Delete container when edit page properties of node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_DeleteContainerWhenEditPagePropertiesOfNode() {
		info("Test 13 Delete container when edit page properties of node");
		/*Step Number: 1
		*Step Name: Step 1: Delete container when edit page properties of node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click and choose Edit node's page
			- Select Container tab from Page Editor
			- Select container in page and click Delete container
			- Switch view mode
		*Input Data: 
			
		*Expected Outcome: 
			- The container is removed successfully
			- The page is displayed in the view mode with all changes*/ 

 	}

	/**
	*<li> Case ID:123117.</li>
	*<li> Test Case Name: Copy and Paste node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_CopyAndPasteNode() {
		info("Test 14 Copy and Paste node");
		/*Step Number: 1
		*Step Name: Step 1: Copy and Paste node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click on node and choose Copy from the drop
			-down menu. 
			- Right
			-click the position you want to paste this node and select Paste Node.
		*Input Data: 
			
		*Expected Outcome: 
			- Node is copied to new place*/ 

 	}

	/**
	*<li> Case ID:123118.</li>
	*<li> Test Case Name: Cut and Paste Node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_CutAndPasteNode() {
		info("Test 15 Cut and Paste Node");
		/*Step Number: 1
		*Step Name: Step 1: Copy and Paste node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click on node and choose Cut from the drop
			-down menu. 
			- Right
			-click the position you want to paste this node and select Paste Node.
		*Input Data: 
			
		*Expected Outcome: 
			- Node is cut to new place*/ 

 	}

	/**
	*<li> Case ID:123119.</li>
	*<li> Test Case Name: Clone and Paste Node.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_CloneAndPasteNode() {
		info("Test 16 Clone and Paste Node");
		/*Step Number: 1
		*Step Name: Step 1: Clone and Paste node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node
			- Right click on node and choose Clone Node from the drop
			-down menu. 
			- Right
			-click the position that you want to paste this node and select Paste Node
		*Input Data: 
			
		*Expected Outcome: 
			Node is clone successfully and have properties is the same with node is copied*/ 

 	}

	/**
	*<li> Case ID:123120.</li>
	*<li> Test Case Name: Add node for group.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test17_AddNodeForGroup() {
		info("Test 17 Add node for group");
		/*Step Number: 1
		*Step Name: Step 1: Add node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Add new node by click add new node button or right click
			- Input value for Page Node Setting and Page Selector form
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- Add node successfully*/ 

 	}

	/**
	*<li> Case ID:123121.</li>
	*<li> Test Case Name: Edit node for group.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test18_EditNodeForGroup() {
		info("Test 18 Edit node for group");
		/*Step Number: 1
		*Step Name: Step 1: Edit Node
		*Step Description: 
			- Go to Group Sites/Edit navigation
			- Select a node and chooseEdit node by right click 
			- Change values in fields of the current node, except the Node Name. 
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- The node is updated with the change value*/ 

 	}}