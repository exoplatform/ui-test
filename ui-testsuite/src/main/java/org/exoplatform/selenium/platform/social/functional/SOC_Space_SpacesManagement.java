package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ecms.CreateNewDocument.selectDocumentType;
import org.openqa.selenium.By;
import org.testng.annotations.*;


	public class SOC_Space_SpacesManagement extends SOC_TestConfig{
	/**
	*<li> Case ID:122298.</li>
	*<li> Test Case Name: Edit name of space in visible and open mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_EditNameOfSpaceInVisibleAndOpenMode() {
		info("Test 1: Edit name of space in visible and open mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Create space with visible and open mode
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Click on [Add new space] button 
			- Enter valid data for space: + Name+ Choose option visible and open.+ Invite user of other space.
			- Click on [Create] button.
		*Input Data: 
			
		*Expected Outcome: 
			- Create a new space successfully. New space appears.
			- By default, the space is featured with some default applications pages (Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) on the space navigation bar.*/

		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(0);
		String[] arrayRight ={regist};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 2
		*Step Name: Step 2: Edit name of space
		*Step Description: 
			- Access the created space at step 1 and select space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.*/

		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
		/*Step number: 3
		*Step Name: Step 3: Check the display name of space on left navigation
		*Step Description: 
			- Look at top of left navigation
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToSpecificSpace(newName);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		
		/*Step number: 4
		*Step Name: Step 4: Check the display name of space on URL
		*Step Description: 
			- Look at URL to space on address bar
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		/*Step number: 5
		*Step Name: Step 5: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/

		hp.goToSpecificSpace(newName);
		info("All default portlet is displayed");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the display name of space in "All spaces" page after renaming
		*Step Description: 
			- From left navigation: choose "Join a space"
			- Look at "All spaces" tab
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/

		hp.goToAllSpace();
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
		/*Step number: 7
		*Step Name: Step 7: Check the display name of space in "My spaces" page after renaming
		*Step Description: 
			- From left navigation: choose "Join a space"
			- Look at "My spaces" tab
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/ 
		hp.goToMySpaces();
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
 	}

	/**
	*<li> Case ID:122299.</li>
	*<li> Test Case Name: Edit name of space in visible and close mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_EditNameOfSpaceInVisibleAndCloseMode() {
		info("Test 2: Edit name of space in visible and close mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Create space with visible and closed mode
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Click on [Add new space] button 
			- Enter valid data for space: + Name+ Choose option visible and closed.+ Invite user of other space.
			- Click on [Create] button.
		*Input Data: 
			
		*Expected Outcome: 
			- Create a new space successfully. New space appears.
			- By default, the space is featured with some default applications pages (Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) on the space navigation bar.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(2);
		String[] arrayRight ={regist};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 2
		*Step Name: Step 2: Edit name of space
		*Step Description: 
			- Access the created space at step 1 and select space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.*/
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		/*Step number: 3
		*Step Name: Step 3: Check the display name of space on left navigation
		*Step Description: 
			- Look at top of left navigation
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToSpecificSpace(newName);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		
		/*Step number: 4
		*Step Name: Step 4: Check the display name of space on URL
		*Step Description: 
			- Look at URL to space on address bar
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		/*Step number: 5
		*Step Name: Step 5: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/
		hp.goToSpecificSpace(newName);
		info("All default portlet is displayed");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the display name of space in "All spaces" page after renaming
		*Step Description: 
			- From left navigation: choose "Join a space"
			- Look at "All spaces" tab
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToAllSpace();
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		/*Step number: 7
		*Step Name: Step 7: Check the display name of space in "My spaces" page after renaming
		*Step Description: 
			- From left navigation: choose "Join a space"
			- Look at "My spaces" tab
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/ 
		hp.goToMySpaces();
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
 	}

	/**
	*<li> Case ID:122300.</li>
	*<li> Test Case Name: Edit name of space in hidden and open mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test03_EditNameOfSpaceInHiddenAndOpenMode() {
		info("Test 3: Edit name of space in hidden and open mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Create space with hidden and open mode
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Click on [Add new space] button 
			- Enter valid data for space: + Name+ Choose option hidden and open.+ Invite user of other space.
			- Click on [Create] button.
		*Input Data: 
			
		*Expected Outcome: 
			- Create a new space successfully. New space appears.
			- By default, the space is featured with some default applications pages (Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) on the space navigation bar.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 2
		*Step Name: Step 2: Edit name of space
		*Step Description: 
			- Access the created space at step 1 and select space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.*/
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		/*Step number: 3
		*Step Name: Step 3: Check the display name of space on left navigation
		*Step Description: 
			- Look at top of left navigation
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToSpecificSpace(newName);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		
		/*Step number: 4
		*Step Name: Step 4: Check the display name of space on URL
		*Step Description: 
			- Look at URL to space on address bar
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		/*Step number: 5
		*Step Name: Step 5: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/
		hp.goToSpecificSpace(newName);
		info("All default portlet is displayed");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the display name of space in "All spaces" page after renaming
		*Step Description: 
			- From left navigation: choose "Join a space"
			- Look at "All spaces" tab
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToAllSpace();
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		/*Step number: 7
		*Step Name: Step 7: Check the display name of space in "My spaces" page after renaming
		*Step Description: 
			- From left navigation: choose "Join a space"
			- Look at "My spaces" tab
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/ 
		hp.goToMySpaces();
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
 	}

	/**
	*<li> Case ID:122301.</li>
	*<li> Test Case Name: Edit name of space in hidden and close mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test04_EditNameOfSpaceInHiddenAndCloseMode() {
		info("Test 4: Edit name of space in hidden and close mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Create space with hidden and close mode
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Click on [Add new space] button 
			- Enter valid data for space: + Name+ Choose option hidden and close.+ Invite user of other space.
			- Click on [Create] button.
		*Input Data: 
			
		*Expected Outcome: 
			- Create a new space successfully. New space appears.
			- By default, the space is featured with some default applications pages (Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) on the space navigation bar.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(2);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 2
		*Step Name: Step 2: Edit name of space
		*Step Description: 
			- Access the created space at step 1 and select space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.*/
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		/*Step number: 3
		*Step Name: Step 3: Check the display name of space on left navigation
		*Step Description: 
			- Look at top of left navigation
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToSpecificSpace(newName);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		
		/*Step number: 4
		*Step Name: Step 4: Check the display name of space on URL
		*Step Description: 
			- Look at URL to space on address bar
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		/*Step number: 5
		*Step Name: Step 5: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/
		hp.goToSpecificSpace(newName);
		info("All default portlet is displayed");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the display name of space in "All spaces" page after renaming
		*Step Description: 
			- From left navigation: choose "Join a space"
			- Look at "All spaces" tab
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToAllSpace();
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		/*Step number: 7
		*Step Name: Step 7: Check the display name of space in "My spaces" page after renaming
		*Step Description: 
			- From left navigation: choose "Join a space"
			- Look at "My spaces" tab
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/ 
		hp.goToMySpaces();
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
 	}

	/**
	*<li> Case ID:122302.</li>
	*<li> Test Case Name: Edit name of space in hidden and validation mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test05_EditNameOfSpaceInHiddenAndValidationMode() {
		info("Test 5: Edit name of space in hidden and validation mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Create space with hidden and validation mode
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Click on [Add new space] button 
			- Enter valid data for space: + Name+ Choose option hidden and validation.+ Invite user of other space.
			- Click on [Create] button.
		*Input Data: 
			
		*Expected Outcome: 
			- Create a new space successfully. New space appears.
			- By default, the space is featured with some default applications pages (Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) on the space navigation bar.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(1);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 2
		*Step Name: Step 2: Edit name of space
		*Step Description: 
			- Access the created space at step 1 and select space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.*/
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		/*Step number: 3
		*Step Name: Step 3: Check the display name of space on left navigation
		*Step Description: 
			- Look at top of left navigation
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToSpecificSpace(newName);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		
		/*Step number: 4
		*Step Name: Step 4: Check the display name of space on URL
		*Step Description: 
			- Look at URL to space on address bar
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		/*Step number: 5
		*Step Name: Step 5: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/
		hp.goToSpecificSpace(newName);
		info("All default portlet is displayed");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the display name of space in "All spaces" page after renaming
		*Step Description: 
			- From left navigation: choose "Join a space"
			- Look at "All spaces" tab
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/
		hp.goToAllSpace();
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		/*Step number: 7
		*Step Name: Step 7: Check the display name of space in "My spaces" page after renaming
		*Step Description: 
			- From left navigation: choose "Join a space"
			- Look at "My spaces" tab
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is displayed correctly*/ 
		hp.goToMySpaces();
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
 	}

	/**
	*<li> Case ID:122303.</li>
	*<li> Test Case Name: Checking the access of all applications after renaming space in hidden and close mode.</li>
	*<li> Pre-Condition: -
	- A space in hidden and close mode are created => The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name
	-
	- On created space:
	- In "Activity Stream": share some activities
	- In "Forum": create some topics
	- In "Wiki": create some Wiki pages
	- In "Documents": create some folders and upload some documents and create some new contents
	- In "Agenda": create some Events / Tasks
	- In "Answer": create some categories, questions include answers and comments
	- In "Members": invite some members to join space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test06_CheckingTheAccessOfAllApplicationsAfterRenamingSpaceInHiddenAndCloseMode() {
		info("Test 6: Checking the access of all applications after renaming space in hidden and close mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Edit name of space
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Open created space in pre
			-conditionselect space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.
			- The URL to access to space after renaming is:
			http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(2);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		
		/*Step number: 2
		*Step Name: Step 2: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/
		hp.goToSpecificSpace(newName);
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		/*Step number: 3
		*Step Name: Step 3: Check the accessing to "Activity Stream"
		*Step Description: 
			- On Space navigation: choose "Activity Stream"
			- Share some new activities
		*Input Data: 
			
		*Expected Outcome: 
			- "Activity Stream" of Space is displayed.
			- The URL to access to Activity Stream is:http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit
			- Shared activities in pre
			-condition are displayed correctly.
			- New shared activities are displayed correctly.*/
       
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 4
		*Step Name: Step 4: Check the access to "Forums"
		*Step Description: 
			- On Space navigation: choose "Forums"
			- Create some new topics
		*Input Data: 
			
		*Expected Outcome: 
			- "Forums" application of Space is displayed normally
			- The URL to access to Space Forums page is:http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/forum
			- Created topics in pre
			-condition are displayed correctly.
			- New created topics are displayed correctly.*/
        spaMg.goToForumTab();
        String currentUrl1=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl1);
		String expectUrl1=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/forum";
		info("expectUrl:"+expectUrl1);
		if(currentUrl1.equalsIgnoreCase(expectUrl1)==false)
			assert false:"The URL is displayed incorrectly";
		
		info("Created a topic");
		String topicName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		topicMg.addTopicSimple(topicName, topicName);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 5
		*Step Name: Step 5: Check the access to "Wiki"
		*Step Description: 
			- On Space navigation: choose "Wiki"
			- Create some new wiki pages
		*Input Data: 
			
		*Expected Outcome: 
			- "Wiki" page of Space is displayed normally
			- The URL to access to Space Wiki page is:http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/wiki
			- Created wiki pages in pre
			-condition are displayed correctly.
			- New created wiki pages are displayed correctly.*/
        spaMg.goToWikiTab();
        String currentUrl2=driver.getCurrentUrl();
		info("currentUrl2:"+currentUrl2);
		String expectUrl2=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/wiki";
		info("expectUrl2:"+expectUrl2);
		if(currentUrl2.equalsIgnoreCase(expectUrl2)==false)
			assert false:"The URL is displayed incorrectly";
		
		String wikiText = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wikiText, wikiText);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wikiText),2000,1);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the access to "Documents"
		*Step Description: 
			- On Space navigation: choose "Documents"
			- Create some folders and upload some documents and create some new contents
		*Input Data: 
			
		*Expected Outcome: 
			- "Documents" page of Space is displayed normally
			- Created folders, documents, contents in pre
			-condition are displayed correctly.
			- The URL to access to Space Documents page is:http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/documents
			- New created folders, documents, contents are displayed correctly.*/

		  spaMg.goToDocumentTab();
	      String currentUrl3=driver.getCurrentUrl();
		  info("currentUrl3:"+currentUrl3);
		  String expectUrl3=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/documents";
		  info("expectUrl3:"+expectUrl3);
		  if(currentUrl3.equalsIgnoreCase(expectUrl3)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new folder");
		  SEHome.goToAddNewFolder();
		  String folderTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  SEHome.createFolder(folderTitle,"");
		  
		  info("Create a new documents in the folder");
		  String docTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  SEHome.openAFolder(folderTitle);
		  SEHome.openListDocumentTemplateByRightClick();
		  creatDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		  creatDoc.addNewWebContent(docTitle,docTitle);
		  creatDoc.saveAndClose();
		  waitForAndGetElement(creatDoc.ELEMENT_DOCUMENT_VIEW_TAB,2000,1);
		  info("Document is created successfully");
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
			
		/*Step number: 7
		*Step Name: Step 7: Check the access to "Agenda"
		*Step Description: 
			- On Space navigation: choose "Agenda"
			- Create some Events / Tasks
		*Input Data: 
			
		*Expected Outcome: 
			- "Agenda" application of Space is displayed normally
			- The URL to access to Space Calendar page is:http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/calendar
			- Created Events / Tasks in precondition are displayed correctly.
			- New created Events / Tasks are displayed correctly.*/

		  spaMg.goToAgendaTab();
	      String currentUrl4=driver.getCurrentUrl();
		  info("currentUrl4:"+currentUrl4);
		  String expectUrl4=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/calendar";
		  info("expectUrl4:"+expectUrl4);
		  if(currentUrl4.equalsIgnoreCase(expectUrl4)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new event");
		  String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  info("Add a event");
		  evMg.goToAddEventFromActionBar();
		  evMg.inputBasicQuickEvent(titleEvent, content);
		  evMg.saveQuickAddEvent();
		  waitForAndGetElement(evMg.ELEMENT_EVENT_TITLE.replace("${name}",titleEvent),2000,1);
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		  
		/*Step number: 8
		*Step Name: Step 8: Check the access to "Space settings"
		*Step Description: 
			- On Space navigation: choose "Space settings"
		*Input Data: 
			
		*Expected Outcome: 
			- "Space Configuration" page are displayed with 5 following tabs:+ Settings+ Access & Edit+ Member+ Applications+ Navigations
			- The URL to access to Space setting page is:http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/settings
			- Name of space is the name after renaming at step 1
			- All others setting when creating Space are kept*/
		  spaHome.goToSpaceSettingTab();
          String currentUrl5=driver.getCurrentUrl();
		  info("currentUrl5:"+currentUrl5);
		  String expectUrl5=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/settings";
		  info("expectUrl5:"+expectUrl5);
		  if(currentUrl5.equalsIgnoreCase(expectUrl5)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
        
		/*Step number: 9
		*Step Name: Step 9: Check the access to "Answers"
		*Step Description: 
			- On Space navigation: choose "Answers"
			- Create some new categories, questions include answers and comments
		*Input Data: 
			
		*Expected Outcome: 
			- "Answers" application of Space is displayed normally
			- The URL to access to Space Answer page is:http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/answer
			- Created categories, questions, answers and comments in precondition are displayed correctly.
			- New created categories, questions, answers and comments are displayed correctly.*/

		  /**
		   * Answers portlet is removed.So we don't check this case.
		   */
		/*Step number: 10
		*Step Name: Step 10: Check the access to "FAQ"
		*Step Description: 
			- On Space navigation: choose "FAQ"
		*Input Data: 
			
		*Expected Outcome: 
			- "FAQ" application of Space is displayed correctly with all created questions and answers.
			- The URL to access to Space FAQ page is:http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/faq*/

		  /**
		   * Answers portlet is removed.So we don't check this case.
		   */
		/*Step number: 11
		*Step Name: Step 11: Check the access to "Members"
		*Step Description: 
			- On Space navigation: choose "Members"
			- Invite some new members
		*Input Data: 
			
		*Expected Outcome: 
			- "Members" page of Space is displayed with correctly information of Administrator and Space members
			- The URL to access to Space member page is:http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/members
			- Invited members in precondition are displayed correctly
			- New invited members are displayed correctly.*/ 
		    spaHome.goToSpaceSettingTab();
		    setSpaceMg.goToMemberTab();
		    info("Invite a user");
		    setSpaceMg.inviteUser(username2,true,fullName2);
			
			magAc.signIn(username2,password);
			spaMg.goToAllSpacesTab();
			spaMg.acceptAInvitation(newName);
			
			magAc.signIn(username1,password);
			
			spaHome.goToSpace(newName);
		    spaMg.goToMemberTab();
		    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_INFOR,2000,1);
		    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_SEARCH);
		    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_CONTACT_LIST);
		    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_NAME.replace("${fullName}",fullName2));
		    
		    String currentUrl6=driver.getCurrentUrl();
			info("currentUrl6:"+currentUrl6);
			String expectUrl6=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/members";
		    info("expectUrl6:"+expectUrl6);
			if(currentUrl6.equalsIgnoreCase(expectUrl6)==false)
			assert false:"The URL is displayed incorrectly";
			
 	}

	/**
	*<li> Case ID:122304.</li>
	*<li> Test Case Name: Checking the access of all applications after renaming space in hidden and open mode.</li>
	*<li> Pre-Condition: -
	- A space in hidden and open mode are created=> The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name
	-
	- On created space:
	- In "Activity Stream": share some activities
	- In "Forum": create some topics
	- In "Wiki": create some Wiki pages
	- In "Documents": create some folders and upload some documents and create some new contents
	- In "Agenda": create some Events / Tasks
	- In "Answer": create some categories, questions include answers and comments
	- In "Members": invite some members to join space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test07_CheckingTheAccessOfAllApplicationsAfterRenamingSpaceInHiddenAndOpenMode() {
		info("Test 7: Checking the access of all applications after renaming space in hidden and open mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Edit name of space
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Open created space in pre
			-conditionselect space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.
			- The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		
		/*Step number: 2
		*Step Name: Step 2: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/
		hp.goToSpecificSpace(newName);
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 3
		*Step Name: Step 3: Check the access to "Activity Stream"
		*Step Description: 
			- On Space navigation: choose "Activity Stream"
			- Share some new activities
		*Input Data: 
			
		*Expected Outcome: 
			- "Activity Stream" of Space is displayed
			- The URL to access to space activity stream is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit
			- Shared activities in pre
			-condition are displayed correctly.
			- New shared activities are displayed correctly.*/
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 4
		*Step Name: Step 4: Check the access to "Forums"
		*Step Description: 
			- On Space navigation: choose "Forums"
			- Create some new topics
		*Input Data: 
			
		*Expected Outcome: 
			- "Forums" application of Space is displayed normally
			- The URL to access to space forum page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/forum
			- Created topics in pre
			-condition are displayed correctly.
			- New created topics are displayed correctly.*/
	    spaMg.goToForumTab();
        String currentUrl1=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl1);
		String expectUrl1=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/forum";
		info("expectUrl:"+expectUrl1);
		if(currentUrl1.equalsIgnoreCase(expectUrl1)==false)
			assert false:"The URL is displayed incorrectly";
		
		info("Created a topic");
		String topicName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		topicMg.addTopicSimple(topicName, topicName);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 5
		*Step Name: Step 5: Check the access to "Wiki"
		*Step Description: 
			- On Space navigation: choose "Wiki"
			- Create some new wiki pages
		*Input Data: 
			
		*Expected Outcome: 
			- "Wiki" page of Space is displayed normally
			- The URL to access to space wiki page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/wiki
			- Created wiki pages in pre
			-condition are displayed correctly.
			- New created wiki pages are displayed correctly.*/
		spaMg.goToWikiTab();
        String currentUrl2=driver.getCurrentUrl();
		info("currentUrl2:"+currentUrl2);
		String expectUrl2=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/wiki";
		info("expectUrl2:"+expectUrl2);
		if(currentUrl2.equalsIgnoreCase(expectUrl2)==false)
			assert false:"The URL is displayed incorrectly";
		
		String wikiText = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wikiText, wikiText);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wikiText),2000,1);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the access to "Documents"
		*Step Description: 
			- On Space navigation: choose "Documents"
			- Create some folders and upload some documents and create some new contents
		*Input Data: 
			
		*Expected Outcome: 
			- "Documents" page of Space is displayed normally
			- Created folders, documents, contents in pre
			-condition are displayed correctly.
			- The URL to access to space document page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/documents
			- New created folders, documents, contents are displayed correctly.*/
		  spaMg.goToDocumentTab();
	      String currentUrl3=driver.getCurrentUrl();
		  info("currentUrl3:"+currentUrl3);
		  String expectUrl3=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/documents";
		  info("expectUrl3:"+expectUrl3);
		  if(currentUrl3.equalsIgnoreCase(expectUrl3)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new folder");
		  SEHome.goToAddNewFolder();
		  String folderTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  SEHome.createFolder(folderTitle,"");
		  
		  info("Create a new documents in the folder");
		  String docTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  SEHome.openAFolder(folderTitle);
		  SEHome.openListDocumentTemplateByRightClick();
		  creatDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		  creatDoc.addNewWebContent(docTitle,docTitle);
		  creatDoc.saveAndClose();
		  waitForAndGetElement(creatDoc.ELEMENT_DOCUMENT_VIEW_TAB,2000,1);
		  info("Document is created successfully");
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
			
		/*Step number: 7
		*Step Name: Step 7: Check the access to "Agenda"
		*Step Description: 
			- On Space navigation: choose "Agenda"
			- Create some Events / Tasks
		*Input Data: 
			
		*Expected Outcome: 
			- "Agenda" application of Space is displayed normally
			- The URL to access to space calendar page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/calendar
			- Created Events / Tasks in pre
			-condition are displayed correctly.
			- New created Events / Tasks are displayed correctly.*/
		  spaMg.goToAgendaTab();
	      String currentUrl4=driver.getCurrentUrl();
		  info("currentUrl4:"+currentUrl4);
		  String expectUrl4=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/calendar";
		  info("expectUrl4:"+expectUrl4);
		  if(currentUrl4.equalsIgnoreCase(expectUrl4)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new event");
		  String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  info("Add a event");
		  evMg.goToAddEventFromActionBar();
		  evMg.inputBasicQuickEvent(titleEvent, content);
		  evMg.saveQuickAddEvent();
		  waitForAndGetElement(evMg.ELEMENT_EVENT_TITLE.replace("${name}",titleEvent),2000,1);
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		  
		/*Step number: 8
		*Step Name: Step 8: Check the access to "Space settings"
		*Step Description: 
			- On Space navigation: choose "Space settings"
		*Input Data: 
			
		*Expected Outcome: 
			- "Space Configuration" page are displayed with 5 following tabs:+ Settings+ Access & Edit+ Member+ Applications+ Navigation
			- The URL to access to space setting page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/settings
			- Name of space is the name after renaming at step 1<br />
			- All others setting when creating Space are kept*/
		  spaHome.goToSpaceSettingTab();
	        
          String currentUrl5=driver.getCurrentUrl();
		  info("currentUrl5:"+currentUrl5);
		  String expectUrl5=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/settings";
		  info("expectUrl5:"+expectUrl5);
		  if(currentUrl5.equalsIgnoreCase(expectUrl5)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
        
		/*Step number: 9
		*Step Name: Step 9: Check the access to "Answers"
		*Step Description: 
			- On Space navigation: choose "Answers"
			- Create some new categories, questions include answers and comments
		*Input Data: 
			
		*Expected Outcome: 
			- "Answers" application of Space is displayed normally
			- The URL to access to space answer page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/answer
			- Created categories, questions, answers and comments in pre
			-condition are displayed correctly.
			- New created categories, questions, answers and comments are displayed correctly.*/
		  /**
		   * Answers portlet is removed.So we don't check this case.
		   */
		/*Step number: 10
		*Step Name: Step 10: Check the access to "FAQ"
		*Step Description: 
			- On Space navigation: choose "FAQ"
		*Input Data: 
			
		*Expected Outcome: 
			- "FAQ" application of Space is displayed correctly with all created questions and answers.
			- The URL to access to space FAQ page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/faq*/
		  /**
		   * FAQ portlet is removed.So we don't check this case.
		   */
		/*Step number: 11
		*Step Name: Step 11: Check the access to "Members"
		*Step Description: 
			- On Space navigation: choose "Members"
			- Invite some new members
		*Input Data: 
			
		*Expected Outcome: 
			- "Members" page of Space is displayed with correctly information of Administrator and Space members
			- The URL to access to space member page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/members
			- Invited members in pre
			-condition are displayed correctly
			- New invited members are displayed correctly.*/ 
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
	    info("Invite a user");
	    setSpaceMg.inviteUser(username2,true,fullName2);
		
		magAc.signIn(username2,password);
		spaMg.goToAllSpacesTab();
		spaMg.acceptAInvitation(newName);
		
		magAc.signIn(username1,password);
		
		spaHome.goToSpace(newName);
	    spaMg.goToMemberTab();
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_INFOR,2000,1);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_SEARCH);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_CONTACT_LIST);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_NAME.replace("${fullName}",fullName2));
	    
	    String currentUrl6=driver.getCurrentUrl();
		info("currentUrl6:"+currentUrl6);
		String expectUrl6=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/members";
	    info("expectUrl6:"+expectUrl6);
		if(currentUrl6.equalsIgnoreCase(expectUrl6)==false)
		assert false:"The URL is displayed incorrectly";
 	}

	/**
	*<li> Case ID:122305.</li>
	*<li> Test Case Name: Checking the access of all applications after renaming space in hidden and validation mode.</li>
	*<li> Pre-Condition: -
	- A space in hidden and validation mode are created=> The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name
	-
	- On created space:
	- In "Activity Stream": share some activities
	- In "Forum": create some topics
	- In "Wiki": create some Wiki pages
	- In "Documents": create some folders and upload some documents and create some new contents
	- In "Agenda": create some Events / Tasks
	- In "Answer": create some categories, questions include answers and comments
	- In "Members": invite some members to join space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test08_CheckingTheAccessOfAllApplicationsAfterRenamingSpaceInHiddenAndValidationMode() {
		info("Test 8: Checking the access of all applications after renaming space in hidden and validation mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Edit name of space
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Open created space in pre
			-conditionselect space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.
			- The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(1);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		
		/*Step number: 2
		*Step Name: Step 2: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/
		hp.goToSpecificSpace(newName);
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 3
		*Step Name: Step 3: Check the access to "Activity Stream"
		*Step Description: 
			- On Space navigation: choose "Activity Stream"
			- Share some new activities
		*Input Data: 
			
		*Expected Outcome: 
			- "Activity Stream" of Space is displayed
			- The URL to access to space activity stream is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit
			- Shared activities in pre
			-condition are displayed correctly.
			- New shared activities are displayed correctly.*/
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 4
		*Step Name: Step 4: Check the access to "Forums"
		*Step Description: 
			- On Space navigation: choose "Forums"
			- Create some new topics
		*Input Data: 
			
		*Expected Outcome: 
			- "Forums" application of Space is displayed normally
			- The URL to access to space forum page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/forum
			- Created topics in pre
			-condition are displayed correctly.
			- New created topics are displayed correctly.*/
	    spaMg.goToForumTab();
        String currentUrl1=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl1);
		String expectUrl1=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/forum";
		info("expectUrl:"+expectUrl1);
		if(currentUrl1.equalsIgnoreCase(expectUrl1)==false)
			assert false:"The URL is displayed incorrectly";
		
		info("Created a topic");
		String topicName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		topicMg.addTopicSimple(topicName, topicName);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 5
		*Step Name: Step 5: Check the access to "Wiki"
		*Step Description: 
			- On Space navigation: choose "Wiki"
			- Create some new wiki pages
		*Input Data: 
			
		*Expected Outcome: 
			- "Wiki" page of Space is displayed normally
			- The URL to access to space wiki page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/wiki
			- Created wiki pages in pre
			-condition are displayed correctly.
			- New created wiki pages are displayed correctly.*/
		spaMg.goToWikiTab();
        String currentUrl2=driver.getCurrentUrl();
		info("currentUrl2:"+currentUrl2);
		String expectUrl2=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/wiki";
		info("expectUrl2:"+expectUrl2);
		if(currentUrl2.equalsIgnoreCase(expectUrl2)==false)
			assert false:"The URL is displayed incorrectly";
		
		String wikiText = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wikiText, wikiText);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wikiText),2000,1);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the access to "Documents"
		*Step Description: 
			- On Space navigation: choose "Documents"
			- Create some folders and upload some documents and create some new contents
		*Input Data: 
			
		*Expected Outcome: 
			- "Documents" page of Space is displayed normally
			- The URL to access to space document page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/documents
			- Created folders, documents, contents in pre
			-condition are displayed correctly.
			- New created folders, documents, contents are displayed correctly.*/
		 spaMg.goToDocumentTab();
	      String currentUrl3=driver.getCurrentUrl();
		  info("currentUrl3:"+currentUrl3);
		  String expectUrl3=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/documents";
		  info("expectUrl3:"+expectUrl3);
		  if(currentUrl3.equalsIgnoreCase(expectUrl3)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new folder");
		  SEHome.goToAddNewFolder();
		  String folderTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  SEHome.createFolder(folderTitle,"");
		  
		  info("Create a new documents in the folder");
		  String docTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  SEHome.openAFolder(folderTitle);
		  SEHome.openListDocumentTemplateByRightClick();
		  creatDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		  creatDoc.addNewWebContent(docTitle,docTitle);
		  creatDoc.saveAndClose();
		  waitForAndGetElement(creatDoc.ELEMENT_DOCUMENT_VIEW_TAB,2000,1);
		  info("Document is created successfully");
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
			
		/*Step number: 7
		*Step Name: Step 7: Check the access to "Agenda"
		*Step Description: 
			- On Space navigation: choose "Agenda"
			- Create some Events / Tasks
		*Input Data: 
			
		*Expected Outcome: 
			- "Agenda" application of Space is displayed normally
			- The URL to access to space calendar is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/calendar
			- Created Events / Tasks in pre
			-condition are displayed correctly.
			- New created Events / Tasks are displayed correctly.*/

		  spaMg.goToAgendaTab();
	      String currentUrl4=driver.getCurrentUrl();
		  info("currentUrl4:"+currentUrl4);
		  String expectUrl4=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/calendar";
		  info("expectUrl4:"+expectUrl4);
		  if(currentUrl4.equalsIgnoreCase(expectUrl4)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new event");
		  String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  info("Add a event");
		  evMg.goToAddEventFromActionBar();
		  evMg.inputBasicQuickEvent(titleEvent, content);
		  evMg.saveQuickAddEvent();
		  waitForAndGetElement(evMg.ELEMENT_EVENT_TITLE.replace("${name}",titleEvent),2000,1);
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		  /*Step number: 8
		*Step Name: Step 8: Check the access to "Space settings"
		*Step Description: 
			- On Space navigation: choose "Space settings"
		*Input Data: 
			
		*Expected Outcome: 
			- "Space Configuration" page are displayed with 5 following tabs:+ Settings+ Access & Edit+ Member+ Applications+ Navigations
			- The URL to access to space setting page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/settings
			- Name of space is the name after renaming at step 1
			- All others setting when creating Space are kept*/
		  spaHome.goToSpaceSettingTab();
	        
          String currentUrl5=driver.getCurrentUrl();
		  info("currentUrl5:"+currentUrl5);
		  String expectUrl5=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/settings";
		  info("expectUrl5:"+expectUrl5);
		  if(currentUrl5.equalsIgnoreCase(expectUrl5)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
        		  
		/*Step number: 9
		*Step Name: Step 9: Check the access to "Answers"
		*Step Description: 
			- On Space navigation: choose "Answers"
			- Create some new categories, questions include answers and comments
		*Input Data: 
			
		*Expected Outcome: 
			- "Answers" application of Space is displayed normally
			- The URL to access to space answer page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/answer
			- Created categories, questions, answers and comments in pre
			-condition are displayed correctly.
			- New created categories, questions, answers and comments are displayed correctly.*/
		  /**
		   * Answers portlet is removed.So we don't check this case.
		   */
		/*Step number: 10
		*Step Name: Step 10: Check the access to "FAQ"
		*Step Description: 
			- On Space navigation: choose "FAQ"
		*Input Data: 
			
		*Expected Outcome: 
			- "FAQ" application of Space is displayed correctly with all created questions and answers.
			- The URL to access to space FAQ page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/faq*/
		  /**
		   * FAQ portlet is removed.So we don't check this case.
		   */
		/*Step number: 11
		*Step Name: Step 11: Check the access to "Members"
		*Step Description: 
			- On Space navigation: choose "Members"
			- Invite some new members
		*Input Data: 
			
		*Expected Outcome: 
			- "Members" page of Space is displayed with correctly information of Administrator and Space members.
			- The URL to access to space member page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/members
			- Invited members in pre
			-condition are displayed correctly
			- New invited members are displayed correctly.*/ 
		spaHome.goToSpaceSettingTab();
	    info("Invite a user");
	    setSpaceMg.goToMemberTab();
	    setSpaceMg.inviteUser(username2,true,fullName2);
		
		magAc.signIn(username2,password);
		spaMg.goToAllSpacesTab();
		spaMg.acceptAInvitation(newName);
		
		magAc.signIn(username1,password);
		
		spaHome.goToSpace(newName);
	    spaMg.goToMemberTab();
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_INFOR,2000,1);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_SEARCH);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_CONTACT_LIST);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_NAME.replace("${fullName}",fullName2));
	    
	    String currentUrl6=driver.getCurrentUrl();
		info("currentUrl6:"+currentUrl6);
		String expectUrl6=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/members";
	    info("expectUrl6:"+expectUrl6);
		if(currentUrl6.equalsIgnoreCase(expectUrl6)==false)
		assert false:"The URL is displayed incorrectly";
		
 	}

	/**
	*<li> Case ID:122306.</li>
	*<li> Test Case Name: Checking the access of all applications after renaming space in visible and close mode.</li>
	*<li> Pre-Condition: -
	- A space in visible and close mode are created=> The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name
	-
	- On created space:
	- In "Activity Stream": share some activities
	- In "Forum": create some topics
	- In "Wiki": create some Wiki pages
	- In "Documents": create some folders and upload some documents and create some new contents
	- In "Agenda": create some Events / Tasks
	- In "Answer": create some categories, questions include answers and comments
	- In "Members": invite some members to join space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test09_CheckingTheAccessOfAllApplicationsAfterRenamingSpaceInVisibleAndCloseMode() {
		info("Test 9: Checking the access of all applications after renaming space in visible and close mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Edit name of space
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Open created space in pre
			-conditionselect space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.
			- The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(2);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		
		/*Step number: 2
		*Step Name: Step 2: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/
		hp.goToSpecificSpace(newName);
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 3
		*Step Name: Step 3: Check the access to "Activity Stream"
		*Step Description: 
			- On Space navigation: choose "Activity Stream"
			- Share some new activities
		*Input Data: 
			
		*Expected Outcome: 
			- "Activity Stream" of Space is displayed.
			- The URL to access to space activity stream is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit
			- Shared activities in pre
			-condition are displayed correctly.
			- New shared activities are displayed correctly.*/
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 4
		*Step Name: Step 4: Check the access to "Forums"
		*Step Description: 
			- On Space navigation: choose "Forums"
			- Create some new topics
		*Input Data: 
			
		*Expected Outcome: 
			- "Forums" application of Space is displayed normally.
			- The URL to access to space forum is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/forum
			- Created topics in pre
			-condition are displayed correctly.
			- New created topics are displayed correctly.*/
	    spaMg.goToForumTab();
        String currentUrl1=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl1);
		String expectUrl1=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/forum";
		info("expectUrl:"+expectUrl1);
		if(currentUrl1.equalsIgnoreCase(expectUrl1)==false)
			assert false:"The URL is displayed incorrectly";
		
		info("Created a topic");
		String topicName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		topicMg.addTopicSimple(topicName, topicName);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 5
		*Step Name: Step 5: Check the access to "Wiki"
		*Step Description: 
			- On Space navigation: choose "Wiki"
			- Create some new wiki pages
		*Input Data: 
			
		*Expected Outcome: 
			- "Wiki" page of Space is displayed normally.
			- The URL to access to space wiki page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/wiki
			- Created wiki pages in pre
			-condition are displayed correctly.
			- New created wiki pages are displayed correctly.*/
		spaMg.goToWikiTab();
        String currentUrl2=driver.getCurrentUrl();
		info("currentUrl2:"+currentUrl2);
		String expectUrl2=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/wiki";
		info("expectUrl2:"+expectUrl2);
		if(currentUrl2.equalsIgnoreCase(expectUrl2)==false)
			assert false:"The URL is displayed incorrectly";
		
		String wikiText = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wikiText, wikiText);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wikiText),2000,1);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the access to "Documents"
		*Step Description: 
			- On Space navigation: choose "Documents"
			- Create some folders and upload some documents and create some new contents
		*Input Data: 
			
		*Expected Outcome: 
			- "Documents" page of Space is displayed normally.
			- The URL to access to space document page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/documents
			- Created folders, documents, contents in pre
			-condition are displayed correctly.
			- New created folders, documents, contents are displayed correctly.*/
	  spaMg.goToDocumentTab();
      String currentUrl3=driver.getCurrentUrl();
	  info("currentUrl3:"+currentUrl3);
	  String expectUrl3=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/documents";
	  info("expectUrl3:"+expectUrl3);
	  if(currentUrl3.equalsIgnoreCase(expectUrl3)==false)
			assert false:"The URL is displayed incorrectly";
	  
	  info("Create a new folder");
	  SEHome.goToAddNewFolder();
	  String folderTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
	  SEHome.createFolder(folderTitle,"");
	  
	  info("Create a new documents in the folder");
	  String docTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
	  SEHome.openAFolder(folderTitle);
	  SEHome.openListDocumentTemplateByRightClick();
	  creatDoc.createNewDoc(selectDocumentType.WEBCONTENT);
	  creatDoc.addNewWebContent(docTitle,docTitle);
	  creatDoc.saveAndClose();
	  waitForAndGetElement(creatDoc.ELEMENT_DOCUMENT_VIEW_TAB,2000,1);
	  info("Document is created successfully");
	  
	  info("Verify that list of applications of the space is shown with correct order");
      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
	  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
	  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
	  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
	  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
	  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
	  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 7
		*Step Name: Step 7: Check the access to "Agenda"
		*Step Description: 
			- On Space navigation: choose "Agenda"
			- Create some Events / Tasks
		*Input Data: 
			
		*Expected Outcome: 
			- "Agenda" application of Space is displayed normally
			- The URL to access to space calendar page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/calendar
			- Created Events / Tasks in pre
			-condition are displayed correctly.<br />
			- New created Events / Tasks are displayed correctly.*/
		  spaMg.goToAgendaTab();
	      String currentUrl4=driver.getCurrentUrl();
		  info("currentUrl4:"+currentUrl4);
		  String expectUrl4=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/calendar";
		  info("expectUrl4:"+expectUrl4);
		  if(currentUrl4.equalsIgnoreCase(expectUrl4)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new event");
		  String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  info("Add a event");
		  evMg.goToAddEventFromActionBar();
		  evMg.inputBasicQuickEvent(titleEvent, content);
		  evMg.saveQuickAddEvent();
		  waitForAndGetElement(evMg.ELEMENT_EVENT_TITLE.replace("${name}",titleEvent),2000,1);
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 8
		*Step Name: Step 8: Check the access to "Space settings"
		*Step Description: 
			- On Space navigation: choose "Space settings"
		*Input Data: 
			
		*Expected Outcome: 
			- "Space Configuration" page are displayed with 5 following tabs:+ Settings+ Access & Edit+ Member+ Applications+ Navigations
			- Name of space is the name after renaming at step 1
			- The URL to access to space setting page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/settings
			- All others setting when creating Space are kept*/
		  spaHome.goToSpaceSettingTab();
	        
          String currentUrl5=driver.getCurrentUrl();
		  info("currentUrl5:"+currentUrl5);
		  String expectUrl5=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/settings";
		  info("expectUrl5:"+expectUrl5);
		  if(currentUrl5.equalsIgnoreCase(expectUrl5)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
        
		/*Step number: 9
		*Step Name: Step 9: Check the access to "Answers"
		*Step Description: 
			- On Space navigation: choose "Answers"
			- Create some new categories, questions include answers and comments
		*Input Data: 
			
		*Expected Outcome: 
			- "Answers" application of Space is displayed normally.
			- The URL to access to space answer page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/answer
			- Created categories, questions, answers and comments in pre
			-condition are displayed correctly.
			- New created categories, questions, answers and comments are displayed correctly.*/
		  /**
		   * Answers portlet is removed.So we don't check this case.
		   */
		/*Step number: 10
		*Step Name: Step 10: Check the access to "FAQ"
		*Step Description: 
			- On Space navigation: choose "FAQ"
		*Input Data: 
			
		*Expected Outcome: 
			- "FAQ" application of Space is displayed correctly with all created questions and answers.
			- The URL to access to space FAQ page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/faq*/
		  /**
		   * FAQ portlet is removed.So we don't check this case.
		   */
		/*Step number: 11
		*Step Name: Step 11: Check the access to "Members"
		*Step Description: 
			- On Space navigation: choose "Members"
			- Invite some new members
		*Input Data: 
			
		*Expected Outcome: 
			- "Members" page of Space is displayed with correctly information of Administrator and Space members.
			- The URL to access to space member page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/members.
			- Invited members in pre
			-condition are displayed correctly
			- New invited members are displayed correctly.*/ 
		spaHome.goToSpaceSettingTab();
	    info("Invite a user");
	    setSpaceMg.goToMemberTab();
	    setSpaceMg.inviteUser(username2,true,fullName2);
		
		magAc.signIn(username2,password);
		spaMg.goToAllSpacesTab();
		spaMg.acceptAInvitation(newName);
		
		magAc.signIn(username1,password);
		
		spaHome.goToSpace(newName);
	    spaMg.goToMemberTab();
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_INFOR,2000,1);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_SEARCH);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_CONTACT_LIST);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_NAME.replace("${fullName}",fullName2));
	    
	    String currentUrl6=driver.getCurrentUrl();
		info("currentUrl6:"+currentUrl6);
		String expectUrl6=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/members";
	    info("expectUrl6:"+expectUrl6);
		if(currentUrl6.equalsIgnoreCase(expectUrl6)==false)
		assert false:"The URL is displayed incorrectly";
		
 	}

	/**
	*<li> Case ID:122307.</li>
	*<li> Test Case Name: Checking the access of all applications after renaming space in visible and open mode.</li>
	*<li> Pre-Condition: -
	- A space in visible and open mode are created=> The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name
	-
	- On created space:
	- In "Activity Stream": share some activities
	- In "Forum": create some topics
	- In "Wiki": create some Wiki pages
	- In "Documents": create some folders and upload some documents and create some new contents
	- In "Agenda": create some Events / Tasks
	- In "Answer": create some categories, questions include answers and comments
	- In "Members": invite some members to join space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test10_CheckingTheAccessOfAllApplicationsAfterRenamingSpaceInVisibleAndOpenMode() {
		info("Test 10 Checking the access of all applications after renaming space in visible and open mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Edit name of space
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Open created space in pre
			-conditionselect space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.
			- The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		
		/*Step number: 2
		*Step Name: Step 2: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/
		hp.goToSpecificSpace(newName);
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 3
		*Step Name: Step 3: Check the access to "Activity Stream"
		*Step Description: 
			- On Space navigation: choose "Activity Stream"
			- Share some new activities
		*Input Data: 
			
		*Expected Outcome: 
			- "Activity Stream" of Space is displayed.
			- The URL to access to space activity stream is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit
			- Shared activities in pre
			-condition are displayed correctly.
			- New shared activities are displayed correctly.*/
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 4
		*Step Name: Step 4: Check the access to "Forums"
		*Step Description: 
			- On Space navigation: choose "Forums"
			- Create some new topics
		*Input Data: 
			
		*Expected Outcome: 
			- "Forums" application of Space is displayed normally.
			- The URL to access to space forum page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/forum
			- Created topics in pre
			-condition are displayed correctly.
			- New created topics are displayed correctly.*/
	    spaMg.goToForumTab();
        String currentUrl1=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl1);
		String expectUrl1=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/forum";
		info("expectUrl:"+expectUrl1);
		if(currentUrl1.equalsIgnoreCase(expectUrl1)==false)
			assert false:"The URL is displayed incorrectly";
		
		info("Created a topic");
		String topicName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		topicMg.addTopicSimple(topicName, topicName);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 5
		*Step Name: Step 5: Check the access to "Wiki"
		*Step Description: 
			- On Space navigation: choose "Wiki"
			- Create some new wiki pages
		*Input Data: 
			
		*Expected Outcome: 
			- "Wiki" page of Space is displayed normally.
			- The URL to access to space wiki page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/wiki
			- Created wiki pages in pre
			-condition are displayed correctly.
			- New created wiki pages are displayed correctly.*/
		spaMg.goToWikiTab();
        String currentUrl2=driver.getCurrentUrl();
		info("currentUrl2:"+currentUrl2);
		String expectUrl2=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/wiki";
		info("expectUrl2:"+expectUrl2);
		if(currentUrl2.equalsIgnoreCase(expectUrl2)==false)
			assert false:"The URL is displayed incorrectly";
		
		String wikiText = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wikiText, wikiText);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wikiText),2000,1);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the access to "Documents"
		*Step Description: 
			- On Space navigation: choose "Documents"
			- Create some folders and upload some documents and create some new contents
		*Input Data: 
			
		*Expected Outcome: 
			- "Documents" page of Space is displayed normally.
			- The URL to access to space document page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/documents
			- Created folders, documents, contents in pre
			-condition are displayed correctly.
			- New created folders, documents, contents are displayed correctly.*/
		  spaMg.goToDocumentTab();
	      String currentUrl3=driver.getCurrentUrl();
		  info("currentUrl3:"+currentUrl3);
		  String expectUrl3=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/documents";
		  info("expectUrl3:"+expectUrl3);
		  if(currentUrl3.equalsIgnoreCase(expectUrl3)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new folder");
		  SEHome.goToAddNewFolder();
		  String folderTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  SEHome.createFolder(folderTitle,"");
		  
		  info("Create a new documents in the folder");
		  String docTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  SEHome.openAFolder(folderTitle);
		  SEHome.openListDocumentTemplateByRightClick();
		  creatDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		  creatDoc.addNewWebContent(docTitle,docTitle);
		  creatDoc.saveAndClose();
		  waitForAndGetElement(creatDoc.ELEMENT_DOCUMENT_VIEW_TAB,2000,1);
		  info("Document is created successfully");
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
			
		/*Step number: 7
		*Step Name: Step 7: Check the access to "Agenda"
		*Step Description: 
			- On Space navigation: choose "Agenda"
			- Create some Events / Tasks
		*Input Data: 
			
		*Expected Outcome: 
			- "Agenda" application of Space is displayed normally
			- The URL to access to space calendar page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/calendar
			- Created Events / Tasks in pre
			-condition are displayed correctly.
			- New created Events / Tasks are displayed correctly.*/
		  spaMg.goToAgendaTab();
	      String currentUrl4=driver.getCurrentUrl();
		  info("currentUrl4:"+currentUrl4);
		  String expectUrl4=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/calendar";
		  info("expectUrl4:"+expectUrl4);
		  if(currentUrl4.equalsIgnoreCase(expectUrl4)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new event");
		  String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  info("Add a event");
		  evMg.goToAddEventFromActionBar();
		  evMg.inputBasicQuickEvent(titleEvent, content);
		  evMg.saveQuickAddEvent();
		  waitForAndGetElement(evMg.ELEMENT_EVENT_TITLE.replace("${name}",titleEvent),2000,1);
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 8
		*Step Name: Step 8: Check the access to "Space settings"
		*Step Description: 
			- On Space navigation: choose "Space settings"
		*Input Data: 
			
		*Expected Outcome: 
			- "Space Configuration" page are displayed with 5 following tabs:+ Settings+ Access & Edit+ Member+ Applications+ Navigation
			- The URL to access to space setting page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/settings
			- Name of space is the name after renaming at step 1
			- All others setting when creating Space are kept.*/
		  spaHome.goToSpaceSettingTab();
	        
          String currentUrl5=driver.getCurrentUrl();
		  info("currentUrl5:"+currentUrl5);
		  String expectUrl5=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/settings";
		  info("expectUrl5:"+expectUrl5);
		  if(currentUrl5.equalsIgnoreCase(expectUrl5)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
        
		/*Step number: 9
		*Step Name: Step 9: Check the access to "Answers"
		*Step Description: 
			- On Space navigation: choose "Answers"
			- Create some new categories, questions include answers and comments
		*Input Data: 
			
		*Expected Outcome: 
			- "Answers" application of Space is displayed normally.
			- The URL to access to space answer page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/answer
			- Created categories, questions, answers and comments in pre
			-condition are displayed correctly.
			- New created categories, questions, answers and comments are displayed correctly.*/
		  /**
		   * Answers portlet is removed.So we don't check this case.
		   */
		/*Step number: 10
		*Step Name: Step 10: Check the access to "FAQ"
		*Step Description: 
			- On Space navigation: choose "FAQ"
		*Input Data: 
			
		*Expected Outcome: 
			- "FAQ" application of Space is displayed correctly with all created questions and answers.
			- The URL to access to space FAQ page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/faq*/
		  /**
		   * FAQ portlet is removed.So we don't check this case.
		   */
		/*Step number: 11
		*Step Name: Step 11: Check the access to "Members"
		*Step Description: 
			- On Space navigation: choose "Members"
			- Invite some new members
		*Input Data: 
			
		*Expected Outcome: 
			- "Members" page of Space is displayed with correctly information of Administrator and Space members
			- The URL to access to space member page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/members
			- Invited members in pre
			-condition are displayed correctly
			- New invited members are displayed correctly.*/ 
		spaHome.goToSpaceSettingTab();
	    info("Invite a user");
	    setSpaceMg.goToMemberTab();
	    setSpaceMg.inviteUser(username2,true,fullName2);
		
		magAc.signIn(username2,password);
		spaMg.goToAllSpacesTab();
		spaMg.acceptAInvitation(newName);
		
		magAc.signIn(username1,password);
		
		spaHome.goToSpace(newName);
	    spaMg.goToMemberTab();
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_INFOR,2000,1);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_SEARCH);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_CONTACT_LIST);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_NAME.replace("${fullName}",fullName2));
	    
	    String currentUrl6=driver.getCurrentUrl();
		info("currentUrl6:"+currentUrl6);
		String expectUrl6=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/members";
	    info("expectUrl6:"+expectUrl6);
		if(currentUrl6.equalsIgnoreCase(expectUrl6)==false)
		assert false:"The URL is displayed incorrectly";
		
 	}

	/**
	*<li> Case ID:122308.</li>
	*<li> Test Case Name: Checking the access of all applications after renaming space in visible and validation mode.</li>
	*<li> Pre-Condition: -
	- A space in visible and validation mode are created=> The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name
	-
	- On created space:
	- In "Activity Stream": share some activities
	- In "Forum": create some topics
	- In "Wiki": create some Wiki pages
	- In "Documents": create some folders and upload some documents and create some new contents
	- In "Agenda": create some Events / Tasks
	- In "Answer": create some categories, questions include answers and comments
	- In "Members": invite some members to join space</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test11_CheckingTheAccessOfAllApplicationsAfterRenamingSpaceInVisibleAndValidationMode() {
		info("Test 11 Checking the access of all applications after renaming space in visible and validation mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Edit name of space
		*Step Description: 
			- Sign in system
			- On intranet homepage: Click on [Join a space] link on left navigation
			- Open created space in pre
			-conditionselect space settings on space navigation
			- On "Settings" tab and enter the new valid name of space
			- Click Save
		*Input Data: 
			
		*Expected Outcome: 
			- New space's name is saved successfully.
			- The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(1);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		info("Set permission for the space");
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToAccessEditTab();
		setSpaceMg.setPermissionForSpace(arrayRight);
		
		String newName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Edit the space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.editSpaceSimple(space, newName, newName,false,"");
		spaMg.saveChangesSpace();
		waitForAndGetElement(hpAct.ELEMENT_SPACE_MENU_ACTIVITY_PORTLET,2000,1);
 
		info("All changes are saved");
		hp.goToMySpaces();
		spaMg.searchSpace(newName,"");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}", newName),2000,1);
		
		hp.goToSpecificSpace(newName);
		String currentUrl=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl);
		String expectUrl=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase();
		info("expectUrl:"+expectUrl);
		if(currentUrl.equalsIgnoreCase(expectUrl)==false)
			assert false:"New space's name is displayed incorrectly in URL link";
		/*Step number: 2
		*Step Name: Step 2: Check the display of all application on space navigation bar
		*Step Description: 
			- Look at space navigation bar
		*Input Data: 
			
		*Expected Outcome: 
			- On Space navigation: all the applications(Activity Stream, Forums, Wiki, Documents, Agenda, Space Settings, Answer, FAQ, Members) are displayed well*/
		hp.goToSpecificSpace(newName);
		info("Get list of applications");
		ArrayList<String> listApp = spaceUI.getArrayAppNameByType(1);
		String activity_Stream = listApp.get(0);
		String forum = listApp.get(1);
		String wiki = listApp.get(2);
		String document = listApp.get(3);
		String agenda = listApp.get(4);
		String space_setting = listApp.get(5);
		String member = listApp.get(6);
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 3
		*Step Name: Step 3: Check the access to "Activity Stream"
		*Step Description: 
			- On Space navigation: choose "Activity Stream"
			- Share some new activities
		*Input Data: 
			
		*Expected Outcome: 
			- "Activity Stream" of Space is displayed
			- Shared activities in pre
			-condition are displayed correctly.
			- The URL to access to space activity stream is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit
			- New shared activities are displayed correctly.*/
		String activity = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hpAct.addActivity(activity,"");
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 4
		*Step Name: Step 4: Check the access to "Forums"
		*Step Description: 
			- On Space navigation: choose "Forums"
			- Create some new topics
		*Input Data: 
			
		*Expected Outcome: 
			- "Forums" application of Space is displayed normally.
			- The URL to access to space forum page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/forum
			- Created topics in pre
			-condition are displayed correctly.
			- New created topics are displayed correctly.*/
	    spaMg.goToForumTab();
        String currentUrl1=driver.getCurrentUrl();
		info("currentUrl:"+currentUrl1);
		String expectUrl1=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/forum";
		info("expectUrl:"+expectUrl1);
		if(currentUrl1.equalsIgnoreCase(expectUrl1)==false)
			assert false:"The URL is displayed incorrectly";
		
		info("Created a topic");
		String topicName = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		topicMg.addTopicSimple(topicName, topicName);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 5
		*Step Name: Step 5: Check the access to "Wiki"
		*Step Description: 
			- On Space navigation: choose "Wiki"
			- Create some new wiki pages
		*Input Data: 
			
		*Expected Outcome: 
			- "Wiki" page of Space is displayed normally.
			- The URL to access to space wiki page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/wiki
			- Created wiki pages in pre
			-condition are displayed correctly.
			- New created wiki pages are displayed correctly.*/
		spaMg.goToWikiTab();
        String currentUrl2=driver.getCurrentUrl();
		info("currentUrl2:"+currentUrl2);
		String expectUrl2=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/wiki";
		info("expectUrl2:"+expectUrl2);
		if(currentUrl2.equalsIgnoreCase(expectUrl2)==false)
			assert false:"The URL is displayed incorrectly";
		
		String wikiText = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		wHome.goToAddBlankPage();
		wikiMg.goToSourceEditor();
		sourceEditor.addSimplePage(wikiText, wikiText);
		wikiMg.saveAddPage();
		waitForAndGetElement(wHome.ELEMENT_TREE_WIKI_NAME.replace("${name}",wikiText),2000,1);
		
		info("Verify that list of applications of the space is shown with correct order");
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 6
		*Step Name: Step 6: Check the access to "Documents"
		*Step Description: 
			- On Space navigation: choose "Documents"
			- Create some folders and upload some documents and create some new contents
		*Input Data: 
			
		*Expected Outcome: 
			- "Documents" page of Space is displayed normally
			- The URL to access to space is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/documents
			- Created folders, documents, contents in pre
			-condition are displayed correctly.
			- New created folders, documents, contents are displayed correctly.*/
		  spaMg.goToDocumentTab();
	      String currentUrl3=driver.getCurrentUrl();
		  info("currentUrl3:"+currentUrl3);
		  String expectUrl3=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/documents";
		  info("expectUrl3:"+expectUrl3);
		  if(currentUrl3.equalsIgnoreCase(expectUrl3)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new folder");
		  SEHome.goToAddNewFolder();
		  String folderTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  SEHome.createFolder(folderTitle,"");
		  
		  info("Create a new documents in the folder");
		  String docTitle = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  SEHome.openAFolder(folderTitle);
		  SEHome.openListDocumentTemplateByRightClick();
		  creatDoc.createNewDoc(selectDocumentType.WEBCONTENT);
		  creatDoc.addNewWebContent(docTitle,docTitle);
		  creatDoc.saveAndClose();
		  waitForAndGetElement(creatDoc.ELEMENT_DOCUMENT_VIEW_TAB,2000,1);
		  info("Document is created successfully");
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
			
		/*Step number: 7
		*Step Name: Step 7: Check the access to "Agenda"
		*Step Description: 
			- On Space navigation: choose "Agenda"
			- Create some Events / Tasks
		*Input Data: 
			
		*Expected Outcome: 
			- "Agenda" application of Space is displayed normally.
			- The URL to access to space calendar is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/calendar
			- Created Events / Tasks in pre
			-condition are displayed correctly.
			- New created Events / Tasks are displayed correctly.*/
		  spaMg.goToAgendaTab();
	      String currentUrl4=driver.getCurrentUrl();
		  info("currentUrl4:"+currentUrl4);
		  String expectUrl4=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/calendar";
		  info("expectUrl4:"+expectUrl4);
		  if(currentUrl4.equalsIgnoreCase(expectUrl4)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Create a new event");
		  String titleEvent = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  String content = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		  info("Add a event");
		  evMg.goToAddEventFromActionBar();
		  evMg.inputBasicQuickEvent(titleEvent, content);
		  evMg.saveQuickAddEvent();
		  waitForAndGetElement(evMg.ELEMENT_EVENT_TITLE.replace("${name}",titleEvent),2000,1);
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
		
		/*Step number: 8
		*Step Name: Step 8: Check the access to "Space settings"
		*Step Description: 
			- On Space navigation: choose "Space settings"
		*Input Data: 
			
		*Expected Outcome: 
			- "Space Configuration" page are displayed with 5 following tabs:+ Settings+ Access & Edit+ Member+ Applications+ Navigations
			- The URL to access to space settings page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/settings
			- Name of space is the name after renaming at step 1
			- All others setting when creating Space are kept*/
		  spaHome.goToSpaceSettingTab();
	        
          String currentUrl5=driver.getCurrentUrl();
		  info("currentUrl5:"+currentUrl5);
		  String expectUrl5=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/settings";
		  info("expectUrl5:"+expectUrl5);
		  if(currentUrl5.equalsIgnoreCase(expectUrl5)==false)
				assert false:"The URL is displayed incorrectly";
		  
		  info("Verify that list of applications of the space is shown with correct order");
	      waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","1").replace("${tab}",activity_Stream),3000,1);
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","2").replace("${tab}",forum));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","3").replace("${tab}",wiki));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","4").replace("${tab}",document));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","5").replace("${tab}",agenda));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","6").replace("${tab}",space_setting));
		  waitForAndGetElement(spaHome.ELEMENT_SPACE_MENU_DISPLAYORDER_ID.replace("${number}","7").replace("${tab}",member));
        
		/*Step number: 9
		*Step Name: Step 9: Check the access to "Answers"
		*Step Description: 
			- On Space navigation: choose "Answers"
			- Create some new categories, questions include answers and comments
		*Input Data: 
			
		*Expected Outcome: 
			- "Answers" application of Space is displayed normally.
			- The URL to access to space answer page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/answer
			- Created categories, questions, answers and comments in pre
			-condition are displayed correctly.
			- New created categories, questions, answers and comments are displayed correctly.*/
		  /**
		   * Answers portlet is removed.So we don't check this case.
		   */
		/*Step number: 10
		*Step Name: Step 10: Check the access to "FAQ"
		*Step Description: 
			- On Space navigation: choose "FAQ"
		*Input Data: 
			
		*Expected Outcome: 
			- "FAQ" application of Space is displayed correctly with all created questions and answers.
			- The URL to access to space FAQ page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/faq*/
		  /**
		   * FAQ portlet is removed.So we don't check this case.
		   */
		/*Step number: 11
		*Step Name: Step 11: Check the access to "Members"
		*Step Description: 
			- On Space navigation: choose "Members"
			- Invite some new members
		*Input Data: 
			
		*Expected Outcome: 
			- "Members" page of Space is displayed with correctly information of Administrator and Space members.
			- The URL to access to space member page is http://IP_Server_Address:8080/portal/g/:spaces:GroupSite/Space_Name_Edit/members
			- Invited members in pre
			-condition are displayed correctly
			- New invited members are displayed correctly.*/ 
		spaHome.goToSpaceSettingTab();
	    info("Invite a user");
	    setSpaceMg.goToMemberTab();
	    setSpaceMg.inviteUser(username2,true,fullName2);
		
		magAc.signIn(username2,password);
		spaMg.goToAllSpacesTab();
		spaMg.acceptAInvitation(newName);
		
		magAc.signIn(username1,password);
		
		spaHome.goToSpace(newName);
	    spaMg.goToMemberTab();
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_INFOR,2000,1);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_SEARCH);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_CONTACT_LIST);
	    waitForAndGetElement(spaMg.ELEMENT_MEMBER_USER_NAME.replace("${fullName}",fullName2));
	    
	    String currentUrl6=driver.getCurrentUrl();
		info("currentUrl6:"+currentUrl6);
		String expectUrl6=baseUrl+"/g/:spaces:"+space.toLowerCase()+"/"+newName.toLowerCase()+"/members";
	    info("expectUrl6:"+expectUrl6);
		if(currentUrl6.equalsIgnoreCase(expectUrl6)==false)
		assert false:"The URL is displayed incorrectly";
		
 	}

	/**
	*<li> Case ID:122311.</li>
	*<li> Test Case Name: Create a new Space by default.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test12_CreateANewSpaceByDefault() {
		info("Test 12 Create a new Space by default");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Go to space page
		*Step Description: 
			- Sign in system
			- Select space page
		*Input Data: 
			
		*Expected Outcome: 
			- Show content of Space page*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		
		/*Step number: 2
		*Step Name: Step 2: Show form create new space
		*Step Description: 
			- Click on button Add new space
		*Input Data: 
			
		*Expected Outcome: 
			- Show form to create a spacethis form contains the name of the space field*/

		/*Step number: 3
		*Step Name: Step 3: create space
		*Step Description: 
			- Enter a valid name for space
			- Click on button [Create]
		*Input Data: 
			
		*Expected Outcome: 
			Create a new space Successfully:
			- in My space list: Show displaying name of the space which has created. 
			  The displaying name gets value of name of the space field. 
			  Default: if creator doesn't select mode of the space, 
			  it gets the default value a Visible/validation mode.
			- Creator is manager of Space. she/he can access and edit this pace. 
			  And at this time, the space has only one member.
			- User is manager of the space, by side the name of space, user can see some icons: edit, delete and [Leave]. 
			With other user, only see [Request To Join ] button to send request to join in the Space*/ 
		spaMg.addNewSpaceSimple(space,space,60000);
		hp.goToMySpaces();
		info("in My space list: Show displaying name of the space which has created");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
		info(" The displaying name gets value of name of the space field.");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_MEMBER_INFOR.replace("${space}",space));
		waitForAndGetElement(spaMg.ELEMENT_SPACE_DESC_INFOR.replace("${space}",space));
		info("Creator is manager of Space");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_MANAGER_STATUS.replace("${space}",space));
		info("User is manager of the space, by side the name of space, user can see some icons: edit, delete and [Leave]");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_DELETE_BUTTON.replace("${space}",space));
		waitForAndGetElement(spaMg.ELEMENT_SPACE_LEAVE_BTN.replace("${space}",space));
		waitForAndGetElement(spaMg.ELEMENT_SPACE_EDIT_BTN.replace("${space}",space));
		
		spaHome.goToSpace(space);
		spaHome.goToSpaceSettingTab();
		info("she/he can access and edit this pace");
		waitForAndGetElement(spaMg.ELEMENT_SPACE_ACCESS_EDIT_TAB,2000,1);
		setSpaceMg.goToAccessEditTab();
		info("it gets the default value a Visible/validation mode.");
		waitForAndGetElement(setSpaceMg.ELEMENT_ACCESS_VISIBILITY_RADIO_CHECKED,2000,2);
		waitForAndGetElement(setSpaceMg.ELEMENT_ACCESS_VALIDATION_RADIO_CHECKED,2000,2);
		setSpaceMg.goToMemberTab();
		info("And at this time, the space hasn't only one member.");
		waitForAndGetElement(setSpaceMg.ELEMENT_ACCESS_ONLY_ONE_MANAGER_NUMBER,2000,2);
		waitForElementNotPresent(setSpaceMg.ELEMENT_ACCESS_MORE_ONE_MANAGER_NUMBER,2000,2);
		
		
		magAc.signIn(username2,password);
		String space1 = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space1,space1);
		
		magAc.signIn(username1,password);
		info("With other user, only see [Request To Join ] button to send request to join in the Space");
		hp.goToAllSpace();
		waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN.replace("${space}",space1),2000,1);
		waitForElementNotPresent(spaMg.ELEMENT_SPACE_DELETE_BUTTON.replace("${space}",space1));
		waitForElementNotPresent(spaMg.ELEMENT_SPACE_LEAVE_BTN.replace("${space}",space1));
		waitForElementNotPresent(spaMg.ELEMENT_SPACE_EDIT_BTN.replace("${space}",space1));
		
 	}

	/**
	*<li> Case ID:122313.</li>
	*<li> Test Case Name: Access Space page.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test13_AccessSpacePage() {
		info("Test 13 Access Space page");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Log in
		*Step Description: 
			- Log in intranet home
		*Input Data: 
			
		*Expected Outcome: 
			- Log in successfully*/

		/*Step number: 2
		*Step Name: Step 2: Go to Space page
		*Step Description: 
			- Click "Join a space" button
		*Input Data: 
			
		*Expected Outcome: 
			Show content of Space Page: 
			- Search text box
			- My Spaces list
			- Invitations Received list
			- Requets Pending list
			- All Spaces list
			- Default focus on Alll Spaces list
			- There is a Add new space button to allow user adds new space.*/ 
         hp.goToAllSpace();
         info("Search text box");
         waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_SEARCH_TEXT_BOX,2000,1);
         info("All Spaces list and default focus on Alll Spaces list");
         waitForAndGetElement(spaMg.ELEMENT_ALL_SPACE_ACTIVE_TAB);
         info("My Spaces list");
         waitForAndGetElement(spaMg.ELEMENT_SPACE_MY_SPACE_TAB);
         info("Invitations Received list");
         waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_INVITATION_RECEIVED);
         info("Requets Pending list");
         waitForAndGetElement(spaMg.ELEMENT_MY_SPACE_REQUEST_PENDING_TAB);
         info("There is a Add new space button to allow user adds new space");
         waitForAndGetElement(spaMg.ELEMENT_ADD_NEW_SPACE_BUTTON);
         
	}
	/**
	*<li> Case ID:122425.</li>
	*<li> Test Case Name: Show Edit space form by user who is manager of the space or user Root.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test14_ShowEditSpaceFormByUserWhoIsManagerOfTheSpaceOrUserRoot() {
		info("Test 14 Show Edit space form by user who is manager of the space or user Root");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String fullName1=username1+" "+username1;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Create a space
		*Step Description: 
			- Sign in system
			- Select space page
			- Click on [create a space] button 
			- Enter all valid data and click on [create] button.
		*Input Data: 
			
		*Expected Outcome: 
			Space is created successfully.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step number: 2
		*Step Name: Step 2: Access Edit space portlet by click on Edit icon
		*Step Description: 
			- Select a space which has create at step 1
			- Click on [Edit space] icon
		*Input Data: 
			
		*Expected Outcome: 
			User is access on Space settings portlet of the space. This portlet contains five tabs:
			- Settings: manage information about name, description, and avatar of the space
			- Visibility: reset mode and the registration way for the space
			- Members: Manage all members of the space.
			- Applications: manage all applications on the space.
			- Space navigations: allow manager of space manage navigation of space.*/ 
		hp.goToMySpaces();
		spaMg.searchSpace(space,"");
		if (waitForAndGetElement(spaMg.ELEMENT_SPACE_EDIT_BTN.replace("${space}", space), 3000, 0) != null)
			click(spaMg.ELEMENT_SPACE_EDIT_BTN.replace("${space}", space));
		spaHome.goToSpaceSettingTab();
		info("Settings: manage information about name, description, and avatar of the space");
		waitForAndGetElement(setSpaceMg.ELEMENT_NAVIGATION_SPACE_SETTING_TAB,2000,1);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_NAME_INPUT);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_DESCRIPTION_INPUT);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_CHANGE_AVATAR_BTN);
		
		info("Visibility: reset mode and the registration way for the space");
		setSpaceMg.goToAccessEditTab();
		String registOpen =spRegisData.getSpaceRegistration(0);
		String registValidation =spRegisData.getSpaceRegistration(1);
		String registClosed =spRegisData.getSpaceRegistration(2);
		String visib = spVisiData.getSpaceVisible(0);
		String hidden = spVisiData.getSpaceVisible(1);
		waitForAndGetElement(setSpaceMg.ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}",registOpen),2000,2);
		waitForAndGetElement(setSpaceMg.ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}",registValidation),2000,2);
		waitForAndGetElement(setSpaceMg.ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}",registClosed),2000,2);
		waitForAndGetElement(setSpaceMg.ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}",visib),2000,2);
		waitForAndGetElement(setSpaceMg.ELEMENT_ACCESS_PERMISSION_RADIO.replace("${right}",hidden),2000,2);
		
		info("Members: Manage all members of the space");
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_SETTINGS_MEMBERS_TAB,2000,1);
		setSpaceMg.goToMemberTab();
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_MEMBERS_SELECT_USER,2000,1);
		waitForAndGetElement(setSpaceMg.ELEMENT_INPUT_USER);
		waitForAndGetElement(setSpaceMg.ELEMENT_SELECT_USER_FROM_GROUP);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_MEMBERS_INVITE);
		waitForAndGetElement(setSpaceMg.ELEMENT_USER_IN_MEMBER_TABLE.replace("${fullName}",fullName1));
		
		info(" Applications: manage all applications on the space.");
		waitForAndGetElement(setSpaceMg.ELEMENT_SETTINGS_APP_TAB,2000,1);
		setSpaceMg.goToApplicationTab();
		waitForAndGetElement(setSpaceMg.ELEMENT_APPLICATION_TAB_ADD_APPLICATION_BTN,2000,1);
		waitForAndGetElement(setSpaceMg.ELEMENT_APPLICATION_TAB_LIST_APPLICATIONS);
		
		info(" Space navigations: allow manager of space manage navigation of space.");
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_SETTING_NAVIGATION_TAB,2000,1);
		setSpaceMg.goToNavigationTab();
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_NAVIGATION_ADD_NODE_BUTTON,2000,1);
		waitForAndGetElement(setSpaceMg.ELEMENT_SPACE_NAVIGATION_NODE_LIST);
		
 	}

	/**
	*<li> Case ID:122426.</li>
	*<li> Test Case Name: Create new space at visible and Validation mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test15_CreateNewSpaceAtVisibleAndValidationMode() {
		info("Test 15 Create new space at visible and Validation mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Go to Space page
		*Step Description: 
			- Login portal
			- Click [Join a space]
		*Input Data: 
			
		*Expected Outcome: 
			Display the space page*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(1);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		ArrayList<String> groups=spGroupsData.getArrayGroupByType(3);
		ArrayList<String> subgroups=spGroupsData.getArraySubGroupByType(3);
		String[] arrayGroupPath ={groups.get(0),subgroups.get(2)};
		hp.goToAllSpace();
		/*Step number: 2
		*Step Name: Step 2: Show form create new space
		*Step Description: 
			- Click on button Add new space
		*Input Data: 
			
		*Expected Outcome: 
			- Show form to create a space. this form contains the name of the space field*/
		if (waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_BUTTON, 3000, 0) != null)
			click(spaMg.ELEMENT_ADDNEWSPACE_BUTTON);
		else click(spaMg.ELEMENT_ADDNEWSPACE_ICON);
		waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_FORM,3000,1);
		
		/*Step number: 3
		*Step Name: Step 2: Enter name of space
		*Step Description: 
			- Select settings tab
			- Enter a valid name for space
			- Enter data for description
		*Input Data: 
			
		*Expected Outcome: 
			Enter all data for settings tab*/
		type(spaMg.ELEMENT_SPACE_NAME_INPUT, space, true);
		type(spaMg.ELEMENT_SPACE_DESCRIPTION_INPUT, space, true);
		
		/*Step number: 4
		*Step Name: Step 3: Set mode of space
		*Step Description: 
			- Select [Access & Edit ]tab
			- On Visibility field: check in Visible
			- On Registration field: check in Validation radio button
		*Input Data: 
			
		*Expected Outcome: 
			Set mode for space successfully*/
         setSpaceMg.goToAccessEditTab();
         setSpaceMg.setPermissionForSpaceFromPopup(arrayRight);
		/*Step number: 5
		*Step Name: Step 4: Invite users from group
		*Step Description: 
			- Select [Invite users from group] tab
			- Check on "Select a group of people to invite in your space" 
			and select group or keep default value
		*Input Data: 
			
		*Expected Outcome: 
			Set member of space successfully*/
        setSpaceMg.goToInviteUserFromGroupTab();
        check(setSpaceMg.ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX,2);
        setSpaceMg.selectGroup(arrayGroupPath);
        
		/*Step number: 6
		*Step Name: Step 5: Finish create new space
		*Step Description: 
			- Click on Create button
		*Input Data: 
			
		*Expected Outcome: 
			- New space has created
			- With visible and validation mode: User will become member of the space 
			after manager of space validate join request or user accept invitation from manager*/ 
		
        setSpaceMg.saveChanges();
		info("New space has created");
		waitForAndGetElement(By.linkText(space),60000);
		
	    magAc.signIn(username2,password);
	    hp.goToAllSpace();
	    spaMg.acceptAInvitation(space);
	    
	    magAc.signIn(username1,password);
	    info(" User will become member of the space after user accept invitation from manager");
	    hp.goToAllSpace();
	    spaHome.goToSpace(space);
	    spaHome.goToSpaceSettingTab();
	    setSpaceMg.goToMemberTab();
	    waitForAndGetElement(setSpaceMg.ELEMENT_USER_IN_MEMBER_TABLE.replace("${fullName}",fullName2),2000,1);
	    
 	}

	/**
	*<li> Case ID:122436.</li>
	*<li> Test Case Name: Check the displaying of drive after creating/deleting new space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test16_CheckTheDisplayingOfDriveAfterCreatingdeletingNewSpace() {
		info("Test 16 Check the displaying of drive after creating/deleting new space");
		
		/*Step Number: 1
		*Step Name: Step 1: Create new space
		*Step Description: 
			- Go to intranet site
			- Click [Join a space] at left navigation
			- Put name's space
			- Click Create button
		*Input Data: 
			
		*Expected Outcome: 
			New space is created successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToAllSpace();
		spaMg.addNewSpaceSimple(space,space,60000);
		/*Step number: 2
		*Step Name: Step 2: Check the displaying of new drive space
		*Step Description: 
			- Go to Content Explorer
			- Browse all drives
		*Input Data: 
		
		*Expected Outcome: 
			New drive corresponding with new space is shown*/
         navTool.goToSiteExplorer();
         info("Browse all drives");
 		 waitForAndGetElement(SEHome.ELEMENT_ACTIONBAR_SHOWDRIVES);
 		 click(SEHome.ELEMENT_ACTIONBAR_SHOWDRIVES);
 		 info("New drive corresponding with new space is shown");
 		 waitForAndGetElement(SEHome.ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}",space),2000,1);
		/*Step number: 3
		*Step Name: Step 3: Delete new space
		*Step Description: 
			- Go to intranet site
			- Click [Join a space] at left navigation
			- Click on Delete icon of space at step 1
		*Input Data: 
			
		*Expected Outcome: 
			Space is deleted*/
 		info("Delete created space");
		hp.goToAllSpace();
		spaMg.searchSpace(space,"");
		spaMg.deleteSpace(space,false);
		info("The space is deleted successfully");
		/*Step number: 4
		*Step Name: Step 4: Check the displaying of new drive space
		*Step Description: 
			- Go to Content Explorer
			- Browse all drives
		*Input Data: 
			
		*Expected Outcome: 
			New drive corresponding with deleted space is not shown*/ 
		 navTool.goToSiteExplorer();
         info("Browse all drives");
 		 waitForAndGetElement(SEHome.ELEMENT_ACTIONBAR_SHOWDRIVES);
 		 click(SEHome.ELEMENT_ACTIONBAR_SHOWDRIVES);
 		 info("New drive corresponding with new space isnot shown");
 		 waitForElementNotPresent(SEHome.ELEMENT_ACTIONBAR_SELECTED_DRIVE.replace("${drive}",space),2000,1);
 	}

	/**
	*<li> Case ID:122500.</li>
	*<li> Test Case Name: Create new space at visible and close mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test17_CreateNewSpaceAtVisibleAndCloseMode() {
		info("Test 17 Create new space at visible and close mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Go to space page
		*Step Description: 
			- Sign in system
			- Select [Join a space]
		*Input Data: 
			
		*Expected Outcome: 
			- Show content of Space page*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(2);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		ArrayList<String> groups=spGroupsData.getArrayGroupByType(3);
		ArrayList<String> subgroups=spGroupsData.getArraySubGroupByType(3);
		String[] arrayGroupPath ={groups.get(0),subgroups.get(0)};
		hp.goToAllSpace();
		/*Step number: 2
		*Step Name: Step 2: Show form create new space
		*Step Description: 
			- Click on button Add new space
		*Input Data: 
			
		*Expected Outcome: 
			- Show form to create a spacethis form contains the name of the space field*/
		if (waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_BUTTON, 3000, 0) != null)
			click(spaMg.ELEMENT_ADDNEWSPACE_BUTTON);
		else click(spaMg.ELEMENT_ADDNEWSPACE_ICON);
		waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_FORM,3000,1);
		/*Step number: 3
		*Step Name: Step 3: Enter name of space
		*Step Description: 
			- Select [settings] tab
			- Enter a valid name for space
			- Enter data for description
		*Input Data: 
			
		*Expected Outcome: 
			Enter all data for settings tab*/
		type(spaMg.ELEMENT_SPACE_NAME_INPUT, space, true);
		type(spaMg.ELEMENT_SPACE_DESCRIPTION_INPUT, space, true);
		
		/*Step number: 4
		*Step Name: Step 4: Set mode of space
		*Step Description: 
			- Select [Access & Edit ]tab
			- On Visibility field: check in Visible
			- On Registration field: check in Close radio button
		*Input Data: 
			
		*Expected Outcome: 
			Set mode for space successfully*/
		setSpaceMg.goToAccessEditTab();
        setSpaceMg.setPermissionForSpaceFromPopup(arrayRight);
        
		/*Step number: 5
		*Step Name: Step 5: Set Invite users from group
		*Step Description: 
			- Select [Invite users from group] tab
			- Check on "Select a group of people to invite in your space" and 
			select group or keep default value
		*Input Data: 
			
		*Expected Outcome: 
			Set member of space successfully*/
        setSpaceMg.goToInviteUserFromGroupTab();
        check(setSpaceMg.ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX,2);
        setSpaceMg.selectGroup(arrayGroupPath);
		/*Step number: 6
		*Step Name: Step 6: Finish create new space
		*Step Description: 
			- Click on [Create] button
		*Input Data: 
			
		*Expected Outcome: 
			- New space has created
			- With visible and close mode: other user can not send request to join space. 
			Only manager of space can send invitation to user.*/ 
        setSpaceMg.saveChanges();
		info("New space has created");
		waitForAndGetElement(By.linkText(space), 60000);
		
	    magAc.signIn(username2,password);
	    info(" With visible and close mode: other user can not send request to join space.");
	    hp.goToAllSpace();
	    spaMg.searchSpace(space,"");
	    waitForElementNotPresent(spaMg.ELEMENT_MY_SPACE_ALL_SPACES_REQUEST_TO_JOIN_BTN.replace("${space}",space),2000,1);
	  
 	}

	/**
	*<li> Case ID:122503.</li>
	*<li> Test Case Name: Create new space at hidden and open mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test18_CreateNewSpaceAtHiddenAndOpenMode() {
		info("Test 18 Create new space at hidden and open mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 2: Show form create new space
		*Input Data: 
			- Click on button Add new space
		*Expected Outcome: 
			- Show form to create a spacethis form contains the name of the space field*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		ArrayList<String> groups=spGroupsData.getArrayGroupByType(3);
		ArrayList<String> subgroups=spGroupsData.getArraySubGroupByType(3);
		String[] arrayGroupPath ={groups.get(0),subgroups.get(0)};
		hp.goToAllSpace();
		if (waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_BUTTON, 3000, 0) != null)
			click(spaMg.ELEMENT_ADDNEWSPACE_BUTTON);
		else click(spaMg.ELEMENT_ADDNEWSPACE_ICON);
		waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_FORM,3000,1);
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 3: Enter name of space
		*Input Data: 
			- Select settings tab
			- Enter a valid name for space
			- Enter data for description
		*Expected Outcome: 
			Enter all data for settings tab*/
		type(spaMg.ELEMENT_SPACE_NAME_INPUT, space, true);
		type(spaMg.ELEMENT_SPACE_DESCRIPTION_INPUT, space, true);
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 4: Set mode of space
		*Input Data: 
			- Select Visibility tab
			- On Visibility field: check in Hidden
			- On Registration field: check in open radio button
		*Expected Outcome: 
			Set mode for space successfully*/
		setSpaceMg.goToAccessEditTab();
        setSpaceMg.setPermissionForSpaceFromPopup(arrayRight);
        
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 5: Invite users from group
		*Input Data: 
			- Select Invite users from group tab
			- Check on â€œuse an group existingâ€ and select group or keep default value
		*Expected Outcome: 
			Set member of space successfully*/
        setSpaceMg.goToInviteUserFromGroupTab();
        check(setSpaceMg.ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX,2);
        setSpaceMg.selectGroup(arrayGroupPath);
        
		/*Step number: 5
		*Step Name: -
		*Step Description: 
			Step 6: Finish create new space
		*Input Data: 
			- Click on Create button
		*Expected Outcome: 
			- New space has created
			- With hidden mode: other user can not see the space to send request joining. 
			Only manager of space can send invitation to user.*/ 

        setSpaceMg.saveChanges();
		info("New space has created");
		waitForAndGetElement(By.linkText(space), 60000);
		
	    magAc.signIn(username2,password);
	    info(" With visible and close mode: other user can not send request to join space.");
	    hp.goToAllSpace();
	    spaMg.searchSpace(space,"");
	    waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	  
 	}

	/**
	*<li> Case ID:122507.</li>
	*<li> Test Case Name: Create new space with hidden and Validation mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test19_CreateNewSpaceWithHiddenAndValidationMode() {
		info("Test 19 Create new space with hidden and Validation mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Go to space page
		*Input Data: 
			- Sign in system
			- Select space page
		*Expected Outcome: 
			- Show content of Space page*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(1);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		ArrayList<String> groups=spGroupsData.getArrayGroupByType(3);
		ArrayList<String> subgroups=spGroupsData.getArraySubGroupByType(3);
		String[] arrayGroupPath ={groups.get(0),subgroups.get(0)};
		hp.goToAllSpace();
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Show form create new space
		*Input Data: 
			- Click on button Add new space
		*Expected Outcome: 
			- Show form to create a spacethis form contains the name of the space field*/
		if (waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_BUTTON, 3000, 0) != null)
			click(spaMg.ELEMENT_ADDNEWSPACE_BUTTON);
		else click(spaMg.ELEMENT_ADDNEWSPACE_ICON);
		waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_FORM,3000,1);
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Enter name of space
		*Input Data: 
			- Select settings tab
			- Enter a valid name for space
			- Enter data for description
		*Expected Outcome: 
			Enter all data for settings tab*/
		type(spaMg.ELEMENT_SPACE_NAME_INPUT, space, true);
		type(spaMg.ELEMENT_SPACE_DESCRIPTION_INPUT, space, true);
        
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Set mode of space
		*Input Data: 
			- Select Visibility tab
			- On Visibility field: check in Hidden
			- On Registration field: check in Validation radio button
		*Expected Outcome: 
			Set mode for space successfully*/
		setSpaceMg.goToAccessEditTab();
        setSpaceMg.setPermissionForSpaceFromPopup(arrayRight);
        
		/*Step number: 5
		*Step Name: -
		*Step Description: 
			Step 5: Invite users from group
		*Input Data: 
			- Select Invite users from groups tab
			- Check on â€œuse an group existingâ€ and select group or keep default value
		*Expected Outcome: 
			Set member of space successfully*/
        setSpaceMg.goToInviteUserFromGroupTab();
        check(setSpaceMg.ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX,2);
        setSpaceMg.selectGroup(arrayGroupPath);
		/*Step number: 6
		*Step Name: -
		*Step Description: 
			Step 6: Finish create new space
		*Input Data: 
			- Click on Create button
		*Expected Outcome: 
			- New space has created
			- With hidden mode: other user can not see the space to send request joining. 
			Only manager of space can send invitation to user.*/ 
        setSpaceMg.saveChanges();
		Utils.pause(6000);
		
	    magAc.signIn(username2,password);
	    info(" With visible and close mode: other user can not send request to join space.");
	    hp.goToAllSpace();
	    spaMg.searchSpace(space,"");
	    waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	  
 	}

	/**
	*<li> Case ID:122512.</li>
	*<li> Test Case Name: Create new space with hidden and close mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test20_CreateNewSpaceWithHiddenAndCloseMode() {
		info("Test 20 Create new space with hidden and close mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Go to space page
		*Input Data: 
			- Sign in system
			- Select space page
		*Expected Outcome: 
			- Show content of Space page*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(2);
		String visib = spVisiData.getSpaceVisible(1);
		String[] arrayRight ={regist,visib};
		ArrayList<String> groups=spGroupsData.getArrayGroupByType(3);
		ArrayList<String> subgroups=spGroupsData.getArraySubGroupByType(3);
		String[] arrayGroupPath ={groups.get(0),subgroups.get(0)};
		hp.goToAllSpace();
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Show form create new space
		*Input Data: 
			- Click on button [Add new space]
		*Expected Outcome: 
			- Show form to create a spacethis form contains the name of the space field*/
		if (waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_BUTTON, 3000, 0) != null)
			click(spaMg.ELEMENT_ADDNEWSPACE_BUTTON);
		else click(spaMg.ELEMENT_ADDNEWSPACE_ICON);
		waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_FORM,3000,1);
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Enter name of space
		*Input Data: 
			- Select settings tab
			- Enter a valid name for space
			- Enter data for description
		*Expected Outcome: 
			Enter all data for settings tab*/
		type(spaMg.ELEMENT_SPACE_NAME_INPUT, space, true);
		type(spaMg.ELEMENT_SPACE_DESCRIPTION_INPUT, space, true);
		
		/*Step number: 4
		*Step Name: -
		*Step Description: 
			Step 4: Set mode of space
		*Input Data: 
			- Select Visibility tab
			- On Visibility field: check in Hidden
			- On Registration field: check in close radio button
		*Expected Outcome: 
			Set mode for space successfully*/
		setSpaceMg.goToAccessEditTab();
        setSpaceMg.setPermissionForSpaceFromPopup(arrayRight);
        
		/*Step number: 5
		*Step Name: -
		*Step Description: 
			Step 5: Invite users from group
		*Input Data: 
			- Select Invite users from group tab
			- Check on â€œuse an group existingâ€ and select group or keep default value
		*Expected Outcome: 
			Set member of space successfully*/
        setSpaceMg.goToInviteUserFromGroupTab();
        check(setSpaceMg.ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX,2);
        setSpaceMg.selectGroup(arrayGroupPath);
        
		/*Step number: 6
		*Step Name: -
		*Step Description: 
			Step 6: Finish create new space
		*Input Data: 
			- Click on Create button
		*Expected Outcome: 
			- New space has created
			- With hidden mode: other user can not see the space to send request joining. 
			Only manager of space can send invitation to user.*/ 
        setSpaceMg.saveChanges();
		info("New space has created");
		waitForAndGetElement(By.linkText(space), 60000);
		
	    magAc.signIn(username2,password);
	    info(" With visible and close mode: other user can not send request to join space.");
	    hp.goToAllSpace();
	    spaMg.searchSpace(space,"");
	    waitForElementNotPresent(spaMg.ELEMENT_SPACE_TITLE.replace("${space}",space),2000,1);
	  
 	}

	/**
	*<li> Case ID:122520.</li>
	*<li> Test Case Name: Create new space at visible and open mode.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test21_CreateNewSpaceAtVisibleAndOpenMode() {
		info("Test 21 Create new space at visible and open mode");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: Step 1: Go to space page
		*Step Description: 
			- Sign in system
			- Click [Join a sapce]
		*Input Data: 
			
		*Expected Outcome: 
			- Show content of Space page*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String regist =spRegisData.getSpaceRegistration(0);
		String visib = spVisiData.getSpaceVisible(0);
		String[] arrayRight ={regist,visib};
		ArrayList<String> groups=spGroupsData.getArrayGroupByType(3);
		ArrayList<String> subgroups=spGroupsData.getArraySubGroupByType(3);
		String[] arrayGroupPath ={groups.get(0),subgroups.get(0)};
		hp.goToAllSpace();
		/*Step number: 2
		*Step Name: Step 2: Show form create new space
		*Step Description: 
			- Click on button Add new space
		*Input Data: 
			
		*Expected Outcome: 
			- Show form to create a spacethis form contains the name of the space field*/
		if (waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_BUTTON, 3000, 0) != null)
			click(spaMg.ELEMENT_ADDNEWSPACE_BUTTON);
		else click(spaMg.ELEMENT_ADDNEWSPACE_ICON);
		waitForAndGetElement(spaMg.ELEMENT_ADDNEWSPACE_FORM,3000,1);
		/*Step number: 3
		*Step Name: Step 3: Enter name of space
		*Step Description: 
			- Select settings tab
			- Enter a valid name for space
			- Enter data for description
		*Input Data: 
			
		*Expected Outcome: 
			Enter all data for settings tab*/
		type(spaMg.ELEMENT_SPACE_NAME_INPUT, space, true);
		type(spaMg.ELEMENT_SPACE_DESCRIPTION_INPUT, space, true);
		/*Step number: 4
		*Step Name: Step 4: Set mode of space
		*Step Description: 
			- Select [Access & Edit] tab
			- On Visibility field: check in Visible
			- On Registration field: check in Open radio button
		*Input Data: 
			
		*Expected Outcome: 
			Set mode for space successfully*/
		setSpaceMg.goToAccessEditTab();
        setSpaceMg.setPermissionForSpaceFromPopup(arrayRight);
		/*Step number: 5
		*Step Name: Step 5: Invite users from group
		*Step Description: 
			- Select Invite users from group tab
			- Check on "Select a group of people to invite in your space" and select group or keep default value
		*Input Data: 
			
		*Expected Outcome: 
			Set member of space successfully*/
        setSpaceMg.goToInviteUserFromGroupTab();
        check(setSpaceMg.ELEMENT_SPACE_INVITE_USERS_FROM_GROUP_CHECKBOX,2);
        setSpaceMg.selectGroup(arrayGroupPath);
		/*Step number: 6
		*Step Name: Step 6: Finish create new space
		*Step Description: 
			- Click on Create button
		*Input Data: 
			
		*Expected Outcome: 
			- New space has created
			- With visible and open mode: 
			other user will automatically became member of the space 
			after join space or user accept invitation from manager of space*/ 
        setSpaceMg.saveChanges();
		info("New space has created");
		waitForAndGetElement(By.linkText(space), 60000);
		
	    magAc.signIn(username2,password);
	    info(" With visible and open mode: other user will automatically became member of the space after send request to join space");
	    hp.goToAllSpace();
	    spaMg.searchSpace(space,"");
	    spaMg.joinToASpace(space);
	  
	    magAc.signIn(username1,password);
	    info(" User will become member of the space after send request to join space");
	    hp.goToAllSpace();
	    spaHome.goToSpace(space);
	    spaHome.goToSpaceSettingTab();
	    setSpaceMg.goToMemberTab();
	    waitForAndGetElement(setSpaceMg.ELEMENT_USER_IN_MEMBER_TABLE.replace("${fullName}",fullName2),2000,1);
 	}

	/**
	*<li> Case ID:122631.</li>
	*<li> Test Case Name: Show Edit space form by user who is not manager of the space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test22_ShowEditSpaceFormByUserWhoIsNotManagerOfTheSpace() {
		info("Test 22 Show Edit space form by user who is not manager of the space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+"@gmail.com";
		String fullName2=username2+" "+username2;
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Creates space
		*Input Data: 
			- Sign in system
			- Select space page to view
			- Click on [Add new a space] button
			- Enter all valid data and click on [create] button
			- Send invitation to other user
		*Expected Outcome: 
			- Create new space successfully*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		spaHome.goToSpaceSettingTab();
		setSpaceMg.goToMemberTab();
		setSpaceMg.inviteUser(username2,true,fullName2);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Show form edit space
		*Input Data: 
			- Sign in by user who has been sent invitation
			- Select Invitations list and accept theinvitation
			- Select my spaces list and access the space which user has accept invitation
		*Expected Outcome: 
			User can not see [Edit] button to edit space. 
			After user accesses the space successfully user can not see [Space Setting] portlet too*/ 
	    magAc.signIn(username2,password);
	    hp.goToAllSpace();
	    spaMg.goToInvitationsReceivedTab();
	    spaMg.acceptAInvitation(space);
	    hp.goToMySpaces();
	    waitForElementNotPresent(spaMg.ELEMENT_SPACE_EDIT_BTN.replace("${space}",space),2000,1);
	    spaHome.goToSpace(space);
	    waitForElementNotPresent(spaHome.ELEMENT_SPACE_SPACE_SETTINGS,2000,1);
 	}

	/**
	*<li> Case ID:122632.</li>
	*<li> Test Case Name: Delete space.</li>
	*<li> Pre-Condition: </li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test23_DeleteSpace() {
		info("Test 23 Delete space");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1:Create the private space
		*Input Data: 
			- Sign in system
			- Select space page
			- Click on [Add new space] button 
			- Enter valid name 
			- Click on [create] button.
		*Expected Outcome: 
			New space is created.*/
		String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		info("Create a space");
		hp.goToMySpaces();
		spaMg.addNewSpaceSimple(space,space,60000);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Delete space
		*Input Data: 
			- Go to My Space page,select the space 
			- Click on Delete Space icon
			- Click on Ok button to confirm
		*Expected Outcome: 
			- Display warning message â€œAre you sure to delete this space? This can not be undone. All page navigations and this group will be deleted, too.â€
			- The space is deleted. Any thing relates to space, are deleted.The group of space is deleted.The navigation of the space is deleted too*/ 
		 info("Delete created space");
		 hp.goToMySpaces();
		 spaMg.deleteSpace(space,false);
		 info("The space is deleted successfully");
 	}
}