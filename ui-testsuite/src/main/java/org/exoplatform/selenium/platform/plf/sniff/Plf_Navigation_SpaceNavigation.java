package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


	/**
	* @author eXo
	*
	*/
	public class Plf_Navigation_SpaceNavigation extends Plf_TestConfig{
		
	/**
	*<li> Case ID:120901.</li>
	*<li> Test Case Name: Show the space menu.</li>
	*<li> Pre-Condition: a space is created</li>
	*<li> Case ID:120902.</li>
	*<li> Test Case Name: Remove application of space's toolbar.</li>
	*/
	@Test
	public  void test01_ShowTheSpaceMenu(){
		info("Test 1: Show the space menu");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		String space1= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space1, space1);
		
		/*Step Number: 1
		*Step Name: Show space applications
		*Step Description: 
			- Connect to Intranet
			- Open a Space
		*Input Data: 
			
		*Expected Outcome: 
			- The Horizontal toolbar is displayed
			- On the left of the Space toolbar, we display the icon and name of the current space.
			- All applications dedicated to the space are shown on space menu
			- Click on each applications, the application will show up in the main page*/ 
		info("Verify the expected outcome");
		waitForAndGetElement(spaceHome.ELEMENT_SPACE_MENU_ACTIVITY_STREAM,3000,1);
		waitForAndGetElement(spaceHome.ELEMENT_SPACE_MENU_AGENDA,3000,1);
		waitForAndGetElement(spaceHome.ELEMENT_SPACE_MENU_WIKI,3000,1);
		waitForAndGetElement(spaceHome.ELEMENT_SPACE_MENU_DOCUMENTS,3000,1);
		waitForAndGetElement(spaceHome.ELEMENT_SPACE_MENU_SETTINGS,3000,1);
		waitForAndGetElement(spaceHome.ELEMENT_SPACE_MENU_ANSWER,3000,1);
		waitForAndGetElement(spaceHome.ELEMENT_SPACE_MENU_FORUMS,3000,1);
		
 	}
	
	@Test
	public  void test02_RemoveApplicationToolbar() {
		info("Test 02: Remove application of space's toolbar");
		String space1= txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		int index = spAppData.getRandomIndexByType(1);
		info("index:"+index);
		String app = spAppData.newApplication.get(index);
		info("app:"+app);
		String category = spAppData.newCategory.get(index);
		info("cate:"+category);
		
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1, password);
		
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(space1, space1);
		
		/*Step number: 2
		*Step Name: Remove an application
		*Step Description: 
			- From space's settings, remove an application
		*Input Data: 
			
		*Expected Outcome: 
			- The application is removed from the space's toolbar*/ 
		
		info(" Click on Add Application, select application and click add button");
		spaceHome.goToSpaceSettingTab();
		setMag.goToApplicationTab();
		setMag.addApplication(category,app);
		
		info("Verify that Application is added to space");
		waitForAndGetElement(setMag.ELEMENT_APPLICATION_TAB_APPLICATION_LIST_CONTENT.replace("${app}",app),3000,1);
		setMag.removeApplication(app);
		waitForElementNotPresent(hpAct.ELEMENT_SPACE_MENU_MORE_BTN,3000,1);
		waitForElementNotPresent(hpAct.ELEMENT_SPACE_MENU_APPLICATION_PORTLET.replace("${app}",app),3000,1);
		
	}

}