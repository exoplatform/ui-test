package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.openqa.selenium.Dimension;
import org.testng.annotations.*;


	public class SOC_Space_Space_Information extends SOC_TestConfig{

	/**
	*<li> Case ID:122460.</li>
	*<li> Test Case Name: Verify the size of the space's informations toolbar.</li>
	*<li> Pre-Condition: a space is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_VerifyTheSizeOfTheSpacesInformationsToolbar() {
		info("Test 1: Verify the size of the space's informations toolbar");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet
			- Open a Space
		*Input Data: 
			
		*Expected Outcome: 
			- The Horizontal toolbar is displayed
			- In the left of the space toolbar icon and space's name are displayed
			- The size of the left part of the toolbar takes the same width as the left navigation panel*/ 
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 ArrayList<String> listApp = spaceUI.getArrayItemNameByType(1);
		 String activity_Stream = listApp.get(0);
		 String forum = listApp.get(1);
		 String wiki = listApp.get(2);
		 String document = listApp.get(3);
		 String agenda = listApp.get(4);
		 String space_setting = listApp.get(5);
		 String member = listApp.get(6);
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 info("The Horizontal toolbar is displayed");
		 waitForAndGetElement(spaHome.ELEMENT_HORIZOLTAL_MENU_BAR,2000,1);
		 info("In the left of the space toolbar icon and space's name are displayed");
		 waitForAndGetElement(spaHome.ELEMENT_ACTIVITY_STREAM_TAB);
		 waitForAndGetElement(spaHome.ELEMENT_SPACE_TAB_NAME.replace("${name}",activity_Stream));
		 waitForAndGetElement(spaHome.ELEMENT_FORUM_TAB);
		 waitForAndGetElement(spaHome.ELEMENT_SPACE_TAB_NAME.replace("${name}",forum));
		 waitForAndGetElement(spaHome.ELEMENT_WIKI_TAB);
		 waitForAndGetElement(spaHome.ELEMENT_SPACE_TAB_NAME.replace("${name}",wiki));
		 waitForAndGetElement(spaHome.ELEMENT_DOCUMENT_TAB );
		 waitForAndGetElement(spaHome.ELEMENT_SPACE_TAB_NAME.replace("${name}",document));
		 waitForAndGetElement(spaHome.ELEMENT_AGENDA_TAB);
		 waitForAndGetElement(spaHome.ELEMENT_SPACE_TAB_NAME.replace("${name}",agenda));
		 waitForAndGetElement(spaHome.ELEMENT_SPACE_SETTING_TAB);
		 waitForAndGetElement(spaHome.ELEMENT_SPACE_TAB_NAME.replace("${name}",space_setting));
		 waitForAndGetElement(spaHome.ELEMENT_MEMBER_TAB);
		 waitForAndGetElement(spaHome.ELEMENT_SPACE_TAB_NAME.replace("${name}",member));
		 
 	}

	/**
	*<li> Case ID:122461.</li>
	*<li> Test Case Name: Verify the size of the space's icon.</li>
	*<li> Pre-Condition: a space is created</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test02_VerifyTheSizeOfTheSpacesIcon() {
		info("Test 2: Verify the size of the space's icon");
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+"@gmail.com";
		info("Add new user");
		navTool.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		magAc.signIn(username1,password);
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet
			- Open a Space
		*Input Data: 
			
		*Expected Outcome: 
			- The Horizontal toolbar is displayed
			- The size of the space's icon is 35*35*/ 
		 String space = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 waitForAndGetElement(spaHome.ELEMENT_HORIZOLTAL_MENU_BAR,2000,1);
		 Dimension act_stream=waitForAndGetElement(spaHome.ELEMENT_ACTIVITY_STREAM_TAB).getSize();
		 Dimension forum= waitForAndGetElement(spaHome.ELEMENT_FORUM_TAB).getSize();
		 Dimension wiki=waitForAndGetElement(spaHome.ELEMENT_WIKI_TAB).getSize();
		 Dimension document= waitForAndGetElement(spaHome.ELEMENT_DOCUMENT_TAB ).getSize();
		 Dimension agenda= waitForAndGetElement(spaHome.ELEMENT_AGENDA_TAB).getSize();
		 Dimension space_setting= waitForAndGetElement(spaHome.ELEMENT_SPACE_SETTING_TAB).getSize();
		 Dimension member=waitForAndGetElement(spaHome.ELEMENT_MEMBER_TAB).getSize();
		 if(act_stream.getHeight()!=35 & act_stream.getWidth()!=35)
			 assert false:"incorrect size of act_stream app";
		 if(forum.getHeight()!=35 & forum.getWidth()!=35)
			 assert false:"incorrect size of forum app";
		 if(wiki.getHeight()!=35 & wiki.getWidth()!=35)
			 assert false:"incorrect size of wiki app";
		 if(document.getHeight()!=35 & document.getWidth()!=35)
			 assert false:"incorrect size of document app";
		 if(agenda.getHeight()!=35 & agenda.getWidth()!=35)
			 assert false:"incorrect size of agenda app";
		 if(space_setting.getHeight()!=35 & space_setting.getWidth()!=35)
			 assert false:"incorrect size of space_setting app";
		 if(member.getHeight()!=35 & member.getWidth()!=35)
			 assert false:"incorrect size of member app";
 	}

	/**
	*<li> Case ID:122462.</li>
	*<li> Test Case Name: Display a long space's name.</li>
	*<li> Pre-Condition: a space is created with a long name</li>
	*<li> Post-Condition: </li>
	*CANNOT AUTOMATE: BECAUSE LONG TITLE CAN NOT VERIFY BY XPATH
	*/
	@Test(groups="pending")
	public  void test03_DisplayALongSpacesName() {
		info("Test 3: Display a long space's name");
		/*Step Number: 1
		*Step Name: 
		*Step Description: 
			- Connect to Intranet
			- Open a Space
		*Input Data: 
			
		*Expected Outcome: 
			- The Horizontal toolbar is displayed
			- The long name of space is truncated and append "..."*/ 
		 String space = txData.getContentByArrayTypeRandom(5);
		 info("Create a space");
		 hp.goToAllSpace();
		 spaMg.addNewSpaceSimple(space,space);
		 
 	}}