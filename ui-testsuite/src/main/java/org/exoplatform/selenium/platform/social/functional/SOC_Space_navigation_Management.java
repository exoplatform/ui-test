package org.exoplatform.selenium.platform.social.functional;


import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;

public class SOC_Space_navigation_Management extends SOC_TestConfig{
	/**
	 *<li> Case ID:122618.</li>
	 *<li> Test Case Name: Create new node as child of existing node.</li>
	 */
	@Test
	public void Test01_CreateNewNodeAsChildOfExistingNode(){
		String space = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String parentNodenode = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String childrenNode = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			View Space navigation tab
		 *Input Data: 
		 	- User logs in system
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
		 *Expected Outcome: 
			- space navigation is openned. Default we see one root node and 3 childrens node*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", space));
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			Show form to add new node
		 *Input Data: 
		 	- Choose a parent node
			- Right click on one node and select Add new node
		 *Expected Outcome: 
			- Add new node form is shown properly*/
		/*Step Number: 3
		 *Step Name: Step 3: 
		 *Step Description: 
			Create node
		 *Input Data: 
		 	- Input values for required field
			- Click Save
		 *Expected Outcome: 
			- New node is created successfully and displayed as sub node of selected parent*/
		info("Add new node");
		setSpaceMg.addANodeSimple(parentNodenode);
		info("Add child node for parent node above");
		setSpaceMg.addChildrenNodeSimple(parentNodenode, childrenNode);
		
		info("Reset data");
		hp.goToAllSpace();
		spaMg.searchSpace(space, "");
		spaMg.deleteSpace(space, false);
	}
	
	/**
	 *<li> Case ID:122626.</li>
	 *<li> Test Case Name: Copy or Paste a node into another node in the same navigation.</li>
	 */
	@Test
	public void Test02_CopyOrPasteANodeIntoAnotherNodeIntheSameNavigation(){
		String space = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String node2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			Show Edit page and Nav form
		 *Input Data: 
		 	- User logs in system
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
		 *Expected Outcome: 
			- Edit navigation form is shown properly*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			Copy or Paste node
		 *Input Data: 
		 	- Choose a node 
			- Right click on a node and select Copy node
			- Select another node, right click and select Paste node
		 *Expected Outcome: 
			Node is made a copy in new place*/
		info("Add 2 new nodes");
		setSpaceMg.addANodeSimple(node1);
		setSpaceMg.addANodeSimple(node2);
		info("Copy node1 and paste into node2");
		setSpaceMg.copyANode(node1);
		setSpaceMg.pasteANode(true, node2);
		
		info("Verify node is copy and paste successfully");
		info("click to up level to come back route node and make sure node 1 still exists in route node");
		click(setSpaceMg.ELEMENT_SPACE_NAVIGATION_UP_LEVEL_BUTTON);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", node1));
		info("click to node 2 and make sure node1 is under node2");
		click(setSpaceMg.ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", node2));
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_NAVIGAION_ADD_NODE_CHILDREN_UNDER_PARENT.replace("${childrenNode}", node1).replace("${parentNode}", node2));
	}
	
	/**
	 *<li> Case ID:122455.</li>
	 *<li> Test Case Name: Copy or Paste a node into another node on different space navigation.</li>
	 */
	@Test
	public void Test03_CopyOrPasteANodeANodeIntoAntherNodeOnDiffernentSpaceNavigation(){
		String space1 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String space2 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String node2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			Copy node
		 *Input Data: 
		 	- User logs in system
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
			- Select a node
			- Right click on a node and select Copy node
		 *Expected Outcome: 
			- Node is copied*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		info("Add node1 for space1");
		setSpaceMg.addANodeSimple(node1);
		info("copy node1");
		setSpaceMg.copyANode(node1);
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			Paste node into another  space navigation
		 *Input Data: 
		 	- Select another space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
			- Select a node
			- Right click on a node and  find Paste to paste the coppied node
		 *Expected Outcome: 
			Don't see the Paste function. Can not Coppy/paste node on different space navigation*/
		info("Add a new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space2,space2);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		info("add node2 for space2");
		setSpaceMg.addANodeSimple(node2);
		info("Paste node1 to node2 but Don't see the Paste function");
		setSpaceMg.pasteANode(false, node2);
		
	}

	/**
	 *<li> Case ID:122603.</li>
	 *<li> Test Case Name: Copy or Paste a node into the same place.</li>
	 */
	@Test
	public void Test04_CopyOrPasteANodeANodeIntoTheSamePlace(){
		String space1 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			Show Edit page and Nav form
		 *Input Data: 
		 	- User logs in system
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
		 *Expected Outcome: 
			- Edit navigation form is shown properly*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			Copy or Paste node
		 *Input Data: 
		 	- Choose a navigation from list 
			- Right click on node and select Copy node
			- Right click in the same level and select Paste node
		 *Expected Outcome: 
			- Show message alerts can not do this action*/
		info("add anode");
		setSpaceMg.addANodeSimple(node1);
		info("Copy a node");
		setSpaceMg.copyANode(node1);
		info("Right click in the same level and select Paste node");
		setSpaceMg.pasteANode(true, space1);
		info("Show message alerts can not do this action");
		waitForMessage(setSpaceMg.ELEMENT_SPACE_NAVIGATION_COPY_AT_SAME_LEVEL);
		button.ok();
		
	}
	
	/**
	 *<li> Case ID:122619.</li>
	 *<li> Test Case Name: Cut or Paste a node to new place in the same navigation.</li>
	 */
	@Test
	public void Test05_CutOrPasteANodeToNewPlaceInTheSameNavigation(){
		String space = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String node2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			Show Edit Navigation form
		 *Input Data: 
		 	- User logs in system
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
		 *Expected Outcome: 
			- Show Navigation management form*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", space));
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			Cut or Paste node
		 *Input Data: 
		 	- Right click on node and select Cut node 
			- Right click on another node and select Paste node
		 *Expected Outcome: 
			- Node is removed from current place to new place*/
		info("Add 2 new nodes");
		setSpaceMg.addANodeSimple(node1);
		setSpaceMg.addANodeSimple(node2);
		info("Copy node1 and paste into node2");
		setSpaceMg.cutANode(node1);
		setSpaceMg.pasteANode(true, node2);
		
		info("Verify node is copy and paste successfully");
		info("click to up level to come back route node and make sure node 1 does not exist in route node");
		click(setSpaceMg.ELEMENT_SPACE_NAVIGATION_UP_LEVEL_BUTTON);
		waitForElementNotPresent(setSpaceMg.ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", node1));
		info("click to node 2 and make sure node1 is under node2");
		click(setSpaceMg.ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", node2));
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_NAVIGAION_ADD_NODE_CHILDREN_UNDER_PARENT.replace("${childrenNode}", node1).replace("${parentNode}", node2));
		
	}
	
	/**
	 *<li> Case ID:122604.</li>
	 *<li> Test Case Name: Cut or Paste a node to new place on different space navigation.</li>
	 */
	@Test
	public void Test06_CutOrPasteANodeToNewPlaceOnDifferentSpaceNavigation(){
		String space1 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String space2 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String node2 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			Cut node
		 *Input Data: 
		 	- User logs in system
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
			- Right click on node in current navigation and select Cut node
		 *Expected Outcome: 
			- Node is cut*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		info("Add node1 for space1");
		setSpaceMg.addANodeSimple(node1);
		info("copy node1");
		setSpaceMg.cutANode(node1);
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			Paste node on onother space navigation
		 *Input Data: 
		 	- Go to spaces page
			- Select another space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
			- Right click on node and find Paste node
		 *Expected Outcome: 
			Don't see the Paste function. Can not Cut/paste node on different space navigation*/
		info("Add a new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space2,space2);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		info("add node2 for space2");
		setSpaceMg.addANodeSimple(node2);
		info("Paste node1 to node2 but Don't see the Paste function");
		setSpaceMg.pasteANode(false, node2);
		
	}
	
	/**
	 *<li> Case ID:122627.</li>
	 *<li> Test Case Name: Cut or Paste node to same place.</li>
	 */
	@Test
	public void Test07_CutOrPasteNodeToTHeSamePlace(){
		String space1 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			Show Edit Navigation form
		 *Input Data: 
		 	- Login by admin or user i	s manager of at least the exited group navigation
			- Go to Navigation management		
		 *Expected Outcome: 
			- Show Navigation management form*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", space1));
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			Cut or Paste node
		 *Input Data: 
		 	- Right click on node and select Cut node
			- Right click in the same level with cut node and select Paste node
		 *Expected Outcome: 
			- Show message alerts can not do this action*/
		info("add a node");
		setSpaceMg.addANodeSimple(node1);
		info("Cut a node");
		setSpaceMg.cutANode(node1);
		info("Right click in the same level and select Paste node");
		setSpaceMg.pasteANode(true, space1);
		info("Show message alerts can not do this action");
		waitForMessage(setSpaceMg.ELEMENT_SPACE_NAVIGATION_COPY_AT_SAME_LEVEL);
		button.ok();
		
	}
	
	/**
	 *<li> Case ID:122424.</li>
	 *<li> Test Case Name: Delete node with deleting confirmation.</li>
	 */
	@Test
	public void Test08_DeleteNodeWithDeletingConfirmation(){
		String space1 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		/*Step Number: 1
		 *Step Name: Step 1: Show Edit Navigation form
		 *Step Description: 
			- User logs in system
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
		 *Input Data: 
		 *Expected Outcome: 
			- Show Navigation management form*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_NAVIGATION_ADD_NODE_LIST.replace("${name}", space1));
		
		/*Step Number: 2
		 *Step Name: Step 2: Delete node
		 *Step Description: 
			- Chose a node
			- Right click on node and select Delete node
			- Click OK to confirm
		 *Input Data: 
		 *Expected Outcome: 
			- Node is removed */
		info("add a node with page Selector");
		setSpaceMg.addANodeWithPageSelector(node1, true, "English", "", pageName, pageName, "", "",true, false);
		info("Delete a node");
		setSpaceMg.deleteANode(node1);
		
		/*Step Number: 3
		 *Step Name: Step 3: Check page of deleted node
		 *Step Description: 
			- Go to [Administration]/[Portal]/[Pages]
			- Find page to which deleted node linked 
		 *Input Data: 
		 *Expected Outcome: 
			- Page which is linked to deleted node is still shown */
		navTool.goToPotalPages();
		portManagePage.searchPage(pageName, "", "group", true);
			
	}
	
	/**
	 *<li> Case ID:122589.</li>
	 *<li> Test Case Name: Check Finish function after edited container of nodes page.</li>
	 */
	@Test
	public void Test09_CheckFinishFunctionAfterEditedContaierOFNodesPage(){
		String space1 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String oneRowContainer = containerData.getidByIndex(0);
		String newContainerTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String engLang = langData.getLanguageByIndex(1);
		String width = txData.getContentByArrayTypeRandom(3);
		String height = txData.getContentByArrayTypeRandom(3);
		
		
		/*Step Number: 1
		 *Step Name: Step 1: Add page
		 *Step Description: 
			- Create new page by wizard with designed container layout
		 *Input Data: 
		 *Expected Outcome: 
			- Create page successfully*/
		navTool.goToAddPage();
		pgCreateWiz.addAPageSimple(pageName, pageName);
		navTool.goToPotalPages();
		portManagePage.editPage(pageName, "portal");
		pgCreateWiz.addContainer(oneRowContainer);
		
		/*Step Number: 2
		 *Step Name: Step 2: Show form to edit added container
		 *Step Description: 
			- In Edit Page and Nav form:     
			- Select above added page (node) 
			- Right click and select Edit nodes page
			- Select container tab on page editor
			- Move mouse on the container and click on Edit icon of added container on mark layer
		 *Input Data: 
		 *Expected Outcome: 
			- Edit container form appears with 2 tabs:
			+ Container settings:
				- Container Id: can not be changed
				- Container Title, Width, Height are blank
			+ Access permissions: all user with membership/group can access the container. By default, it is public for all */
		/*Step Number: 3
		 *Step Name: Step 3: Edit container
		 *Step Description: 
			- Make change(s)
			- Click Save
		 *Input Data: 
		 *Expected Outcome: 
			- Change is saved*/
		/*Step Number: 4
		 *Step Name: Step 4: Check  Finish
		 *Step Description: 
			- Click on Finish icon on Page editor
		 *Input Data: 
		 *Expected Outcome: 
			- Page editor is closed
			- Changes on edited container above was saved*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Access space and select Space settings portlet or Click on Space setting icon");
		spaHome.goToSpaceSettingTab();
		info("Select Space navigation tab");
		setSpaceMg.goToNavigationTab();
		info("Create new node and select existed page for that  node");
		setSpaceMg.addANodeWithPageSelector(node1, true, engLang, "", "", "", pageName, "", false, false);
		info("Right click on node and click on Edit nodes page");
		setSpaceMg.goToEditNodePage(node1);
		pgCreateWiz.editContainer("Container", newContainerTitle, width, height);
		
		setSpaceMg.goToEditNodePage(node1);
		String assert1 = waitForAndGetElement(pgCreateWiz.ELEMENT_EDITED_COTAINER).getAttribute("style");
		System.out.println(assert1);
		assert waitForAndGetElement(pgCreateWiz.ELEMENT_EDITED_COTAINER).getAttribute("style").equals("width: 150px; height: 150px;");
		mouseOver(pgCreateWiz.ELEMENT_DROP_SOURCE_HAS_LAYOUT, true);
		waitForTextPresent(newContainerTitle);
		pgCreateWiz.saveChangesPageEditor();
		
	}
	
	/**
	 *<li> Case ID:122570.</li>
	 *<li> Test Case Name: Check Finish function after change nodes pages container layout.</li>
	 */
	@Test
	public void Test10_CheckFinishFunctionAfterChangeNodesPagesContainerLayout(){
		String space1 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String engLang = langData.getLanguageByIndex(1);
		
		String oneRowContainer = containerData.getidByIndex(0);
		
		/*Step Number: 1
		 *Step Name: Step 1: Add page
		 *Step Description: 
			- Create new page by wizard with designed container layout
		 *Input Data: 
		 *Expected Outcome: 
			- Create page successfully*/
		navTool.goToAddPage();
		pgCreateWiz.addAPageSimple(pageName, pageName);
		navTool.goToPotalPages();
		portManagePage.editPage(pageName, "portal");
		pgCreateWiz.addContainer(oneRowContainer);
		
		
		/*Step Number: 2
		 *Step Name: Step 2: Make change for pages container layout
		 *Step Description: 
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
			- Create new node and select existed page for that  node
			- Select above added node
			- Right click on node and click on Edit nodes page
			- Select container tab on page editor
			- Make change for layout 
			- Click Save
		 *Input Data: 
		 *Expected Outcome: 
			- Change is saved */
		/*Step Number: 3
		 *Step Name: Step 3: Check Save and Finish
		 *Step Description: 
			- Click Finish icon on Page editor 
		 *Input Data: 
		 *Expected Outcome: 
			- Page editor form is closed
			- Changes on container layout above was saved*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Access space and select Space settings portlet or Click on Space setting icon");
		spaHome.goToSpaceSettingTab();
		info("Select Space navigation tab");
		setSpaceMg.goToNavigationTab();
		info("Create new node and select existed page for that  node");
		setSpaceMg.addANodeWithPageSelector(node1, true, engLang, "", "", "", pageName, "", false, false);
		info("Right click on node and click on Edit nodes page");
		setSpaceMg.goToEditNodePage(node1);
		pgCreateWiz.addContainer(oneRowContainer, false);
		
	}
	
	
	/**
	 *<li> Case ID:122511.</li>
	 *<li> Test Case Name: Check Finish function on Editing page after edited page portlet layout.</li>
	 */
	@Test
	public void Test11_CheckFinishFunctionOnEditingPageAfterEditedPagePortletLayout(){
		String space1 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String engLang = langData.getLanguageByIndex(1);
		
		String appNewUser = appGateData.getnameByIndex(3);
		String appOrganization = appGateData.getnameByIndex(4);
		
		/*Step Number: 1
		 *Step Name: Step 1: Add page
		 *Step Description: 
			- Add new page by wizard with some portlets in page content
		 *Input Data: 
		 *Expected Outcome: 
			- Create page successfully
			- Page is displayed with content as you designed*/
		navTool.goToAddPage();
		pgCreateWiz.addAPageSimple(pageName, pageName);
		navTool.goToPotalPages();
		portManagePage.editPage(pageName, "portal");
		pgCreateWiz.addApplication(pgCreateWiz.ELEMENT_APPLICATION_ADMINISTRATION_TAB,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",appNewUser));
		pgCreateWiz.switchViewMode(false);
		info("Check page after add New user application");
		waitForAndGetElement(userManage.ELEMENT_USERNAME);
		waitForAndGetElement(userManage.ELEMENT_PASSWORD);
		waitForAndGetElement(userManage.ELEMENT_EMAIL);
		waitForAndGetElement(userManage.ELEMENT_FIRSTNAME);
		waitForAndGetElement(userManage.ELEMENT_LASTNAME);
		pgCreateWiz.saveChangesPageEditor();
		
		/*Step Number: 2
		 *Step Name: Step 2: Show portlet control form for editing page
		 *Step Description: 
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
			- Create new node and select existed page for that  node
			- Select above added node
			- Right click on node and click on Edit nodes page
			- Select Application tab on Page editor
			- Click Save
		 *Input Data: 
		 *Expected Outcome: 
			- Portlet control form to edit page is shown properly */
		/*Step Number: 3
		 *Step Name: Step 3: Add new portlet
		 *Step Description: 
			- Drag and drop portlet from list in left pane to page area in right pane 
		 *Input Data: 
		 *Expected Outcome: 
			- New portlet is added in page area*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Access space and select Space settings portlet or Click on Space setting icon");
		spaHome.goToSpaceSettingTab();
		info("Select Space navigation tab");
		setSpaceMg.goToNavigationTab();
		info("Create new node and select existed page for that  node");
		setSpaceMg.addANodeWithPageSelector(node1, true, engLang, "", "", "", pageName, "", false, false);
		info("Right click on node and click on Edit nodes page");
		setSpaceMg.goToEditNodePage(node1);
		info("Select Application tab on Page editor, add new portlet and click save");
		pgCreateWiz.addApplication(pgCreateWiz.ELEMENT_APPLICATION_ADMINISTRATION_TAB,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",appOrganization));
		
		/*Step Number: 4
		 *Step Name: Step 4: Check Save and Finish
		 *Step Description: 
			- Click Finish icon on Page editor
			- Click Save on Navigation management
		 *Input Data: 
		 *Expected Outcome: 
			- Navigation management is closed
			- Changes on Portlet layout was saved*/
		pgCreateWiz.switchViewMode(false);
		waitForAndGetElement(userGroupMg.ELEMENT_GROUP_MANAGEMENT_TAB);
		waitForAndGetElement(userGroupMg.ELEMENT_TAB_MEMBERSHIP_MANAGEMENT);
		pgCreateWiz.saveChangesPageEditor();
		
	}
	
	/**
	 *<li> Case ID:122519.</li>
	 *<li> Test Case Name: Check Finish function after edit porlet with saving page.</li>
	 */
	@Test
	public void Test12_CheckFinishFunctionAfterEditPortletWithSavingPage(){
		String space1 = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		String node1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String pageName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String engLang = langData.getLanguageByIndex(1);
		String newAppTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		String appNewUser = appGateData.getnameByIndex(3);
		String appNewUserTitle = appLayData.getTitleApplicationByIndex(3);
		
		String width = txData.getContentByArrayTypeRandom(3);
		String height = txData.getContentByArrayTypeRandom(3);
		
		/*Step Number: 1
		 *Step Name: Step 1: Add page
		 *Step Description: 
			- Add new page by wizard with some portlets in page content
		 *Input Data: 
		 *Expected Outcome: 
			- Create page successfully
			- Page is displayed with content as you designed*/
		navTool.goToAddPage();
		pgCreateWiz.addAPageSimple(pageName, pageName);
		navTool.goToPotalPages();
		portManagePage.editPage(pageName, "portal");
		pgCreateWiz.addApplication(pgCreateWiz.ELEMENT_APPLICATION_ADMINISTRATION_TAB,pgCreateWiz.ELEMENT_APPLICATION_APPLICATION.replace("${name}",appNewUser));
		pgCreateWiz.switchViewMode(false);
		info("Check page after add New user application");
		waitForAndGetElement(userManage.ELEMENT_USERNAME);
		waitForAndGetElement(userManage.ELEMENT_PASSWORD);
		waitForAndGetElement(userManage.ELEMENT_EMAIL);
		waitForAndGetElement(userManage.ELEMENT_FIRSTNAME);
		waitForAndGetElement(userManage.ELEMENT_LASTNAME);
		pgCreateWiz.saveChangesPageEditor();
		
		/*Step Number: 2
		 *Step Name: Step 2: Show form to edit portlet when edit nodes page
		 *Step Description: 
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
			- Create new node and select existed page for that  node
			- Select above added node
			- Right click on node and click on Edit nodes page
			- Select Application tab on Page editor
			- Move mouse over the portlet and click Edit icon on Mark layer
		 *Input Data: 
		 *Expected Outcome: 
			- Edit portlet form is shown properly with current main information in form includes:
			- Portlet Setting
			- Select Icon
			- Decoration theme
			- Access permission */
		/*Step Number: 3
		 *Step Name: Step 3: Edit portlet
		 *Step Description: 
			- Make change for current portlet
			- Click Save in Edit portlet form
		 *Input Data: 
		 *Expected Outcome: 
			- Change is saved and displayed as edited when open form to edit that portlet again*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		info("Access space and select Space settings portlet or Click on Space setting icon");
		spaHome.goToSpaceSettingTab();
		info("Select Space navigation tab");
		setSpaceMg.goToNavigationTab();
		info("Create new node and select existed page for that  node");
		setSpaceMg.addANodeWithPageSelector(node1, true, engLang, "", "", "", pageName, "", false, false);
		info("Right click on node and click on Edit nodes page");
		setSpaceMg.goToEditNodePage(node1);
		info("Select Application tab on Page editor, edit portlet and click save");
		pgCreateWiz.editApplication(appNewUserTitle, newAppTitle, width, height);
		button.saveAndClose();
		/*Step Number: 4
		 *Step Name: Step 4: Check Save and Finish
		 *Step Description: 
			- Click finish icon on page editor
			- Click Save button on navigation management
		 *Input Data: 
		 *Expected Outcome: 
			- Edit page and Nav form is closed
			- When shown form to edit above portlet like step 3, change was saved*/
		pgCreateWiz.switchViewMode(false);
		String assert2 = waitForAndGetElement(pgCreateWiz.ELEMENT_PORTLET_FRAGMENT_2).getAttribute("style");
		System.out.println(assert2);
		assert waitForAndGetElement(pgCreateWiz.ELEMENT_PORTLET_FRAGMENT_2).getAttribute("style").contains("height: 150px;");
		String assert1 = waitForAndGetElement(pgCreateWiz.ELEMENT_UIWINDOW_DEFAULT_THEME).getAttribute("style");
		System.out.println(assert1);
		assert waitForAndGetElement(pgCreateWiz.ELEMENT_UIWINDOW_DEFAULT_THEME).getAttribute("style").contains("width: 150px;");
		mouseOver(pgCreateWiz.ELEMENT_PORTLET_VIEW_PAGE, true);
		waitForTextPresent(newAppTitle);
		pgCreateWiz.saveChangesPageEditor();
		
	}
	
	/**
	 *<li> Case ID:122422.</li>
	 *<li> Test Case Name: Change order of node.</li>
	 */
	@Test
	public void Test13_ChangeOrderOfNode(){
		String space = txData.getContentByArrayTypeRandom(1).toLowerCase()+getRandomNumber();
		
		String defaultNodeForum = spaceDefaultNodesData.getNodesByIndex(0);
		String defaultNodeWiki = spaceDefaultNodesData.getNodesByIndex(1);
		String defaultNodeSettings = spaceDefaultNodesData.getNodesByIndex(4);
		String defaultNodeMembers = spaceDefaultNodesData.getNodesByIndex(5);
		
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		
		/*Step Number: 1
		 *Step Name: Step 1: 
		 *Step Description: 
			Show Edit page and Nav form
		 *Input Data: 
		 	- User logs in system
			- Go to spaces page
			- Add new space
			- Access space and select Space settings portlet or Click on Space setting icon
			- Select Space navigation tab
		 *Expected Outcome: 
			- Edit navigation form is shown properly*/
		info("Add new space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space);
		info("Open Setting tab");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToNavigationTab();
		
		/*Step Number: 2
		 *Step Name: Step 2: 
		 *Step Description: 
			Check move up when node is the first 
		 *Input Data: 
		 	- Select a node
			- Right click on the first node in list and select Move Up
		 *Expected Outcome: 
			- Nothing happens with that node*/
		setSpaceMg.moveUpANode(true, defaultNodeForum, "", "1", "");
		
		/*Step Number: 3
		 *Step Name: Step 3: 
		 *Step Description: 
			Check move down when node is the last
		 *Input Data: 
		 	- Right click on the last node in list and select Move Down
		 *Expected Outcome: 
			- Nothing happens with that node*/
		setSpaceMg.moveDownANode(true, defaultNodeMembers, "", "6", "");
		
		/*Step Number: 4
		 *Step Name: Step 4: 
		 *Step Description: 
			- Move up valid node
		 *Input Data: 
		 	- Right click on node that is not in the first of list and select Move up
		 *Expected Outcome: 
			- That node and the node above is exchanged*/
		setSpaceMg.moveUpANode(false, defaultNodeMembers, defaultNodeSettings, "6", "5");
		
		/*Step Number: 5
		 *Step Name: Step 5: 
		 *Step Description: 
			- Move down valid node
		 *Input Data: 
		 	- Right click on node that is not in the last of list and select Move down
		 *Expected Outcome: 
			- That node and the node beLow is exchanged*/
		setSpaceMg.moveDownANode(false, defaultNodeForum, defaultNodeWiki, "1", "2");
		
	}
}
