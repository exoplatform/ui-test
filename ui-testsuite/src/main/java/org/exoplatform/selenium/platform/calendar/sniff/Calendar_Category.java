package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.testng.annotations.*;


public class Calendar_Category extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	CalendarHomePage cHome;
	EventManagement event;
	CalendarManagement cMang;
	UserDatabase userData;

	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp = new HomePagePlatform(driver);
		cHome= new CalendarHomePage(driver);
		event= new EventManagement(driver);
		cMang = new CalendarManagement(driver);
		txData = new TextBoxDatabase();
		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
	}

	@AfterMethod
	public void afterMethod(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	/**
	 * Case ID:115653.
	 * Test Case Name: Add Event Category.
	 * Case ID:115654.
	 * Test Case Name: Edit Event Category.
	 * Case ID:115655.
	 * Test Case Name: Delete Event Category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test01_AddEventCategory() {
		String oldNameCategory = txData.getContentByArrayTypeRandom(1)+"115653";
		String newNameCategory = txData.getContentByArrayTypeRandom(1)+"115653";

		info("Test 1: Add Event Category");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Show Event categories form
		 *Input Data: 
			Click on Calendar Action icon from left pane and select Add event category in menu
		 *Expected Outcome: 
			Event Categories form is displayed with a list of all existing event categories and a sub form to add new category*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.CALSETTING);
		cMang.changeSettingCalendar(null,"(GMT +07:00) Asia/Ho_Chi_Minh",null,null,null,null,null);
		cMang.saveSetting();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCATEGORY);
		
		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Add new category
		 *Input Data: 
			- Input valid data into text field.
			- Click Add
		 *Expected Outcome: 
			- Add category successfully
			- Added category is displayed in list above
			- This category is visible while creating an event/task and used for the creator only*/ 
		cMang.addEventCategory(oldNameCategory);
		event.goToAddEventFromActionBar();
		waitForAndGetElement(event.ELEMENT_ITEM_QUICK_EVENT_CATEGORY_OPTION.replace("$category", oldNameCategory));
		event.cancelQuickAddEditEvent();
		
		info("Test 2: Edit Event Category");
		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Edit category with valid data
		 *Input Data: 
			- Make change on Event category name 
			- Click Update
		 *Expected Outcome: 
			- Change is saved, all values disappears from edit form
			- Existing categorylist also is updated*/ 
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCATEGORY);
		cMang.editEventCategory(oldNameCategory, newNameCategory);
		event.goToAddEventFromActionBar();
		waitForAndGetElement(event.ELEMENT_ITEM_QUICK_EVENT_CATEGORY_OPTION.replace("$category", newNameCategory));
		event.cancelQuickAddEditEvent();
		
		info("Test 3: Delete Event Category");
		/*Step number: 2
		 *Step Name: - Step 2: Delete category
		 *Step Description: 
			- Click on Calendar icon from left panel and select Add event category in menu
			- Click Delete icon corresponding to thecategory you want to deletein Event categories form
			- Click OKto confirm
		 *Input Data: 

		 *Expected Outcome: 
			- Confirm message is shown.
			- This category is deleted from the category management list
			- Event(s)/task(s) that was added into this category are not deleted and they have "all" as category*/
		hp.goToCalendarPage();
		cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCATEGORY);
		cMang.deleteEventCategory(newNameCategory);
	}
}