package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.testng.annotations.*;


public class Plf_GettintStartedGadget extends Plf_TestConfig {
	/**
	 *<li> Case ID:120844.</li>
	 *<li> Test Case Name: Check display of Getting started Gadget.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_CheckDisplayOfGettingStartedGadget() {
		info("Test 1: Check display of Getting started Gadget");
		ArrayList<String> tasks= getStartData.getArrayTaskByType(1);
		String task1 = tasks.get(0);
		info("task1:"+task1);
		String task2 = tasks.get(1);
		info("task2:"+task2);
		String task3 = tasks.get(2);
		info("task3:"+task3);
		String task4 = tasks.get(3);
		info("task4:"+task4);
		String task5 = tasks.get(4);
		info("task5:"+task5);
		/*Step Number: 1
		 *Step Name: Check display of Getting Started Gadget
		 *Step Description: 
			- Login and Open intranet homepage
			- Check getting started gadget
		 *Input Data: 

		 *Expected Outcome: 
			This gadget is displayed at the first gadget level (top right), from the first user's connection
			- The gadget should have the following tasks:+ Add a profile picture+ Connect to coworkers+Join a space+ Post an activity+ Upload a document*/ 
		info("Verify that getting started is shown with full tasks");
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_BOX);
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_TASKS.replace("${name}",task1));
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_TASKS.replace("${name}",task2));
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_TASKS.replace("${name}",task3));
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_TASKS.replace("${name}",task4));
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_TASKS.replace("${name}",task5));
	}

	/**
	 *<li> Case ID:120845.</li>
	 *<li> Test Case Name: Perform an action on Getting started Gadget.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_PerformAnActionOnGettingStartedGadget() {
		info("Test 2: Perform an action on Getting started Gadget");
		/*Step Number: 1
		 *Step Name: Connect to intranet home page
		 *Step Description: 
			- Login and connect to intranet home page
		 *Input Data: 

		 *Expected Outcome: 
			- Intranet home page is shown with Getting started gadget*/
		info("Verify that getting started is shown");
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_BOX);
		/*Step number: 2
		 *Step Name: Perform an action
		 *Step Description: 
			- Click on an action, for example, Upload a profile picture
			- Complete to upload a profile picture
		 *Input Data: 

		 *Expected Outcome: 
			- The arrow is transformed into a check icon
			- The action is strikedthrough
			- The completion percentage is updated*/ 
		info("click on connect to coworker link");
		click(hp.ELEMENT_HP_GETTINGSTARTED_CONNETTOCOWORKERS);
		info("Connect to a coworker");
		click((hp.ELEMENT_HP_GETTINGSTARTED_CONNETTOCOWORKERSBTN).replace("${name}", "James Davis"));
		magAc.signOut();
		magAc.signIn(DATA_USER3, DATA_PASS);
		info("Accept an requested connection");
		hp.goToConnections();
		click(hp.ELEMENT_HP_GETTINGSTARTED_ACCEPTTOCOWORKERSBTN);
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		info("Verify that connect to coworker action is done");
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_CONNETTOCOWORKERS_DONE);
	}

	/**
	 *<li> Case ID:120846.</li>
	 *<li> Test Case Name: Check direction when performing an action.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_CheckDirectionWhenPerformingAnAction() {
		info("Test 3: Check direction when performing an action");
		/*Step Number: 1
		 *Step Name: Connect to intranet home page
		 *Step Description: 
			- Login and connect to intranet home page
		 *Input Data: 

		 *Expected Outcome: 
			- Intranet home page is shown*/

		/*Step number: 2
		 *Step Name: Perform a task: Add a picture
		 *Step Description: 
			- Click on "Add a picture"
		 *Input Data: 

		 *Expected Outcome: 
			Open Profile page*/
		info("Click on add a picture link");
		click(hp.ELEMENT_HP_GETTINGSTARTED_ADDPROFILEPIC);
		info("Verify that the profile page is shown");
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_PROFILEPAGE);
		
		/*Step number: 3
		 *Step Name: Perform a task: Connect to coworkers
		 *Step Description: 
			- Click on "Connect to coworkers"
		 *Input Data: 

		 *Expected Outcome: 
			Open Employee Directory*/
		info("click o connect to coworker link");
		hp.goToHomePage();
		click(hp.ELEMENT_HP_GETTINGSTARTED_CONNETTOCOWORKERS);
		info("Verify that connection page is shown");
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_CONNECTIONPAGE);
		
		/*Step number: 4
		 *Step Name: Perform a task: Join a space
		 *Step Description: 
			- Click on "Join a space"
		 *Input Data: 

		 *Expected Outcome: 
			Open Space directory*/
		info("Click on join to space link");
		hp.goToHomePage();
		click(hp.ELEMENT_HP_GETTINGSTARTED_JOINSPACE);
		info("Verify that check join space page is shown");
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_CHECKJOINSPACE);
	
		/*Step number: 5
		 *Step Name: Perform a task: Post an activity
		 *Step Description: 
			- Click on "Post an activity"
		 *Input Data: 

		 *Expected Outcome: 
			Open Homepage (use #)*/
		info("Click on post an activity link");
		hp.goToHomePage();
		click(hp.ELEMENT_HP_GETTINGSTARTED_POSTACTIVITY);
		info("Verify that the activity page is shown");
		waitForAndGetElement(hp.ELEMENT_HOMPAGE_MIDDLE_PANEL);
		/*Step number: 6
		 *Step Name: Perform a task: Upload a document
		 *Step Description: 
			Click on "Upload a document"
		 *Input Data: 

		 *Expected Outcome: 
			Open Document Explorer (in Personal Documents Folders)*/ 
		info("Click on upload a document link");
		click(hp.ELEMENT_HP_GETTINGSTARTED_UPLOADDOC);
		info("Verify that upload document page is shown");
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_CHECKUPLOADDOC);
	}

	/**
	 *<li> Case ID:120848.</li>
	 *<li> Test Case Name: Complete this Getting Started gadget.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test04_CompleteThisGettingStartedGadget() {
		info("Test 04: Complete this Getting Started gadget");
		
		String path = "Winter.jpg";
		String text = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		String spacename = txData.getContentByArrayTypeRandom(1)+getRandomNumber();
		
		/*Step Number: 1
		 *Step Name: Connect to intranet home page
		 *Step Description: 
			- Login and connect to intranet home page
		 *Input Data: 

		 *Expected Outcome: 
			- Intranet home page is shown*/

		/*Step number: 2
		 *Step Name: Complete the Getting Started Gadget
		 *Step Description: 
			- Perform all tasks of this gadget
		 *Input Data: 

		 *Expected Outcome: 
			- The progress is 100%
			- A link labelled "Close" is displayed to invite the user to remove the gadget*/ 
		info("add an avatar");
		click(hp.ELEMENT_HP_GETTINGSTARTED_ADDPROFILEPIC);
		myProfile.changeAvatar("TestData/"+path);
		info("upload a document");
		hp.goToHomePage();
		click(hp.ELEMENT_HP_GETTINGSTARTED_UPLOADDOC);
		SEHome.uploadFile("TestData/"+path);
		info("Post an activity");
		hp.goToHomePage();
		hpAct.addActivity(text,"");
		this.driver.navigate().refresh();
		info("Joint a space");
		hp.goToMySpaces();
		spaceMg.addNewSpaceSimple(spacename, spacename);
		info("Verify that the progress is completed 100%");
		hp.goToHomePage();
		waitForAndGetElement(hp.ELEMENT_HP_GETTINGSTARTED_PROGRESSRATE);
	}
	
	/**
	 *<li> Case ID:120847.</li>
	 *<li> Test Case Name: Remove Getting Started gadget.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_RemoveGettingStartedGadget() {
		info("Test 05: Remove Getting Started gadget");
		/*Step Number: 1
		 *Step Name: Connect to intranet home page
		 *Step Description: 
			- Login and connect to intranet home page
		 *Input Data: 

		 *Expected Outcome: 
			- Intranet home page is shown*/

		/*Step number: 2
		 *Step Name: Remove Getting Started gadget
		 *Step Description: 
			- Click the close icon in upper right corner of Getting Started gadget
		 *Input Data: 

		 *Expected Outcome: 
			- Getting Started gadget is removed permanently
			- Once the gadget is removed, there is no way to bring it back.*/ 
		info("Hover over on the title of getting started gadget");
		mouseOver(hp.ELEMENT_HP_GETTINGSTARTED_TITLE, true);
		info("click on close icon");
		click(hp.ELEMENT_HP_GETTINGSTARTED_CLOSEBOX);
		info("Verify that getting started box is hidden");
		waitForElementNotPresent(hp.ELEMENT_HP_GETTINGSTARTED_BOX);
	}
}