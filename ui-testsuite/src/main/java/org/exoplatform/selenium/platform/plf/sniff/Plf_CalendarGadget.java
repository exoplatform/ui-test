package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.testng.annotations.*;


public class Plf_CalendarGadget extends Plf_TestConfig {
	/**
	 *<li> Case ID:120849.</li>
	 *<li> Test Case Name: Switch between days.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test01_SwitchBetweenDays() {
		info("Test 1: Switch between days");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: Connect to intranet home page
		 *Step Description: 
			- Login and connect to intranet home page
		 *Input Data: 

		 *Expected Outcome: 
			- Intranet home page is shown*/

		/*Step number: 2
		 *Step Name: Switch to next day
		 *Step Description: 
			- Click on arrow on the right of date on calendar gadget
		 *Input Data: 

		 *Expected Outcome: 
			"TOMORROW" is displayed in title*/
		info("Click on next arrow of Calendar gadget");
		click(hp.ELEMENT_HP_CALENDARGADGET_RIGHTARROW);
		info("Verify that 'Tomorrow' text is shown");
		waitForAndGetElement((hp.ELEMENT_HP_CALENDARGADGET_DISPLAYEDDAY).replace("${day}", "Tomorrow"));
		/*Step number: 3
		 *Step Name: Switch to previous day
		 *Step Description: 
			- Click on arrow on the left of date on calendar gadget
		 *Input Data: 

		 *Expected Outcome: 
			"YESTERDAY" is displayed in title*/ 
		info("Click on previous arrow of Calendar gadget");
		click(hp.ELEMENT_HP_CALENDARGADGET_LEFTARROW);
		click(hp.ELEMENT_HP_CALENDARGADGET_LEFTARROW);
		info("Verify that 'Yesterday' text is shown");
		waitForAndGetElement((hp.ELEMENT_HP_CALENDARGADGET_DISPLAYEDDAY).replace("${day}", "Yesterday"));

	}

	/**
	 *<li> Case ID:120850.</li>
	 *<li> Test Case Name: Configure calendar gadget.</li>
	 *<li> Pre-Condition: - Create some Calendars
	- Create some events, tasks</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test02_ConfigureCalendarGadget() {
		info("Test 2: Configure calendar gadget");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: -Go into gadget's settings
		 *Step Description: 
			- Login and go to intranet
			- Hovering this gadget
			- Clicks on Setting button
		 *Input Data: 

		 *Expected Outcome: 
			- A small configuration (Setting) button will appear
			- Settings are displayed.
			- User can see the list of already selected calendars. 
			- A small sentence "Displayed calendars" is displayed before the list of events' calendar.*/ 
		info("Hover over on the calendar box");
		mouseOver(hp.ELEMENT_HP_CALENDARGADGET_BOX, true);
		info("Click on Settings icon");
		click(hp.ELEMENT_HP_CALENDARGADGET_SETTINGS);
		info("Verify the results");
		waitForAndGetElement(hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_DISPLAYEDCAL);
		waitForAndGetElement(hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_SETTINGSCAL);
	}

	/**
	 *<li> Case ID:120851.</li>
	 *<li> Test Case Name: Add a calendar to the list of followed calendars.</li>
	 *<li> Pre-Condition: Create some Calendars: "calendar new", "old cal"</li>
	 *<li> Post-Condition: </li>
	 *
	 *
	 *<li> Case ID:120852.</li>
	 *<li> Test Case Name: Remove a calendar from the list of followed calendars.</li>
	 *<li> Pre-Condition: - Create some Calendars: "calendar new", "old cal"</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test03_04_DeleteAddACalendarToTheListOfFollowedCalendars() {
		info("Test 3: Add a calendar to the list of followed calendars");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: -Go into gadget's settings
		 *Step Description: 
			- Login and Open intranet home
			- Mouse over Calendar gadget
			- Clicks on Setting button
		 *Input Data: 

		 *Expected Outcome: 
			- "Calendar new", "old cal" are displayed
			- A small configuration (Setting) button will appear
			- Settings are displayed.*/
		info("Hover over on the calendar box");
		mouseOver(hp.ELEMENT_HP_CALENDARGADGET_BOX, true);
		info("Click on Settings icon");
		click(hp.ELEMENT_HP_CALENDARGADGET_SETTINGS);
		/*Step number: 2
		 *Step Name: - Remove a calendar
		 *Step Description: 
			- Mouse over a calendar 
			- "old cal" calendar
			- Click on Remove icon at "old cal" calendar
			- Click on OK at the bottom
		 *Input Data: 

		 *Expected Outcome: 
			- The calendar is removed from the list of followed calendars*/
		info("Click on Remove icon of the old calendar");
		click((hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_REMOVECAL).replace("${title}", "Content Management"));
		info("Verify that the old calendar is removed");
		waitForElementNotPresent((hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_REMOVECAL).replace("${title}", "Content Management"));
		/*Step number: 3
		 *Step Name: - Add a calendar so that it is displayed on this gadget
		 *Step Description: 
			- Mouse over a calendar 
			- "old cal"
			- Click on Add button
			- Click on OK at the bottom
		 *Input Data: 

		 *Expected Outcome: 
			- An "ADD" button is displayed.
			- The "old cal" calendar is added is the list of followed calendars and remove from the of calendars that can be added.
			- The setting is saved*/ 
		info("Hover over the calendar box");
		mouseOver((hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_ADDCALNAME).replace("${title}", "Content Management"), true);
		info("Click on add button");
		click(hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_ADDCAL);
		info("Verifyt that the calendar is added");
		waitForAndGetElement((hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_REMOVECAL).replace("${title}", "Content Management"));
	}

	/**
	 *<li> Case ID:120853.</li>
	 *<li> Test Case Name: Filter calendars in settings.</li>
	 *<li> Pre-Condition: - Create some Calendars: "calendar new", "old cal"</li>
	 *<li> Post-Condition: </li>
	 */
	@Test
	public  void test05_FilterCalendarsInSettings() {
		info("Test 5: Filter calendars in settings");
		/*Create data test*/
		String username1 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email1 = username1+ mailSuffixData.getMailSuffixRandom();
		String username2 = txData.getContentByArrayTypeRandom(4) + getRandomString();
		String email2 = username2+ mailSuffixData.getMailSuffixRandom();
		
		info("Add user");
		navToolBar.goToAddUser();
		addUserPage.addUser(username1, password, email1, username1, username1);
		addUserPage.addUser(username2, password, email2, username2, username2);
		magAc.signIn(username1, password);
		/*Step Number: 1
		 *Step Name: -Go into gadget's settings
		 *Step Description: 
			- Login and Open intranet home
			- Move mouse over Calendar gadget
			- Clicks on Setting button
		 *Input Data: 

		 *Expected Outcome: 
			- Settings are displayed.*/
		info("Hover over on the calendar box");
		mouseOver(hp.ELEMENT_HP_CALENDARGADGET_BOX, true);
		info("Click on settings icon");
		click(hp.ELEMENT_HP_CALENDARGADGET_SETTINGS);
		/*Step number: 2
		 *Step Name: - Filter calendars
		 *Step Description: 
			-Enter text "cal" into the Search field in settings form:
		 *Input Data: 

		 *Expected Outcome: 
			- The search is starting with the first letter entered in the text field, then show "calendar new"*/ 
		info("remove a calendar ");
		click((hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_REMOVECAL).replace("${title}", "Content Management"));
		info("input a calendar");
		type(hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_SEARCHCAL, "Content", true);
		info("Hover over on add button");
		mouseOver((hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_ADDCALNAME).replace("${title}", "Content Management"), true);
		info("Click on add button");
		click(hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_ADDCAL);
		info("Verify that the calendar is added");
		waitForAndGetElement((hp.ELEMENT_HP_CALENDARGADGET_SETTINGS_REMOVECAL).replace("${title}", "Content Management"));
	}
}