package org.exoplatform.selenium.platform.forum.functional.forum.administration;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Thuntn
 * @date 19 Nov 2013
 * update chinhdtt
 */
public class Forum_Forum_Administration_AutoPrune extends ForumBase {
	ManageAccount magAc;
	ForumManageCategory cat;
	ForumManageForum mForum;
	ForumManageTopic magtopic;

	@BeforeMethod
	public void setUpBeforeTest(){
		setPreferenceRunTime();
		initSeleniumTest();
		magAc = new ManageAccount(driver, this.plfVersion);
		cat = new ForumManageCategory(driver, this.plfVersion);
		mForum = new ForumManageForum(driver, this.plfVersion);
		magtopic = new ForumManageTopic(driver, this.plfVersion);
		magAc.signIn(DATA_USER1, DATA_PASS);
		goToForums();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:106949.
	 * Setting Auto-Prune when input value is number for inactive day field
	 */
	@Test
	public void test01_SettingAutoPruneWhenInputNumberForInactiveDayField(){
		info("Test 1: Setting Auto-Prune when input value is number for inactive day field");
		String category = "Category 106949";
		String forum = "Forum 106949";
		String topic = "Topic 106949";

		/*
		- Login by the Administrator
		- Create categories, forums, topics, posts
		 *Expected Outcome: Forum Administration form appears including some tabs		*/
		magtopic.addCategoryForumTopic(category, forum, topic, topic);

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto-Prune dialog is shown property with list forum existing		*/
		/*Click on Prune setting icon corresponding of forum which want to Set Auto-Prune
		 *Input Data: 
		 *Expected Outcome: Prune setting pop up is shown property		*/
		/*
		- Input value is number into Inactive day field
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Value of Inactive day is set successful		*/ 
		goToPruneManagement();
		pruneSetting(category, forum, "1", "days", "", "");
		click(ELEMENT_PRUNE_CLOSE_BUTTON);

		//Delete data
		click(mForum.ELEMENT_CATEGORY_BREAD.replace("${category}", category));
		cat.deleteCategoryInForum(category);
	}

	/**
	 * Case ID:106955.
	 * Setting Auto-Prune when input some text into inactive day field
	 */
	@Test
	public void test02_SettingAutoPruneWhenInputTextForInactiveDayField(){
		info("Setting Auto-Prune when input some text into inactive day field");
		String category = "Category 106955";
		String forum = "Forum 106955";
		String topic = "Topic 106955";

		/*
		- Login by the Administrator
		- Create categories, forums, topics, posts
		 *Expected Outcome: Forum Administration form appears including some tabs		*/
		magtopic.addCategoryForumTopic(category, forum, topic, topic);

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto-Prune dialog is shown property with list forum existing		*/
		/*Click on "Prune setting" icon corresponding of forum which want to Set Auto
		-Prune
		 *Input Data: 
		 *Expected Outcome: Prune setting pop up is shown property		*/
		/*
		- Input some text into Inactive day field
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Inputed text into Inactive day is reset to the old value and alert message to notify input number for day		*/ 
		goToPruneManagement();
		pruneSetting(category, forum, "prune", "days", "", "");
		waitForMessage(MSG_PRUNE_INVALID_INACTIVE_DAY);
		click(ELEMENT_PRUNE_INVALID_INACTIVE_DAY_OK);
		assert waitForAndGetElement(ELEMENT_PRUNE_ACTIVE_DAY).getAttribute("value").equals("0"):"Not reset the inactive day field";
		click(ELEMENT_PRUNE_CLOSE_BUTTON);

		//Delete data
		click(mForum.ELEMENT_CATEGORY_BREAD.replace("${category}", category));
		cat.deleteCategoryInForum(category);
	}

	/**
	 * Case ID:106959.
	 * Setting Auto-Prune when input value is number for Run prune every  field
	 */
	@Test
	public void test03_SettingAutoPruneWhenInputNumberForRunPruneField(){
		info("Setting Auto-Prune when input value is number for Run prune every field");
		String category = "Category 106959";
		String forum = "Forum 106959";
		String topic = "Topic 106959";		

		/*
		- Login by the Administrator
		- Create categories, forums, topics, posts
		 *Expected Outcome: Forum Administration form appears including some tabs		*/
		magtopic.addCategoryForumTopic(category, forum, topic, topic);

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto-Prune dialog is shown property with list forum existing		*/

		/*Click on "Prune setting" icon corresponding of forum which want to Set Auto-Prune
		 *Input Data: 
		 *Expected Outcome: Prune setting pop up is shown property		*/
		/*
		- Input value is number into Run prune every field
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Value of Run prune every is set successful		*/ 
		goToPruneManagement();
		pruneSetting(category, forum, null, null, "1", "days");
		click(ELEMENT_PRUNE_CLOSE_BUTTON);

		//Delete data
		click(mForum.ELEMENT_CATEGORY_BREAD.replace("${category}", category));
		cat.deleteCategoryInForum(category);
	}

	/**
	 * Case ID:106964.
	 * Setting Auto-Prune when input some text for Run prune every  field
	 */
	@Test
	public void test04_SettingAutoPruneWhenInputTextForRunPruneField(){
		String category = "Category 106964";
		String forum = "Forum 106964";
		String topic = "Topic 106964";
		info("Setting Auto-Prune when input some text for Run prune every  field");

		/*
		- Login by the Administrator
		- Create categories, forums, topics, posts
		 *Expected Outcome: Forum Administration form appears including some tabs		*/
		magtopic.addCategoryForumTopic(category, forum, topic, topic);

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto-Prune dialog is shown property with list forum existing		*/
		/*Click on Prune setting icon corresponding of forum which want to Set Auto-Prune
		 *Input Data: 
		 *Expected Outcome: Prune setting pop up is shown property		*/
		/*
		- Input some text into Run prune every field
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Inputed text into Run Prune every is reset to the old value. Inputed text into Inactive day is reset to the old value and alert message to notify input number for day.		*/ 
		goToPruneManagement();
		pruneSetting(category, forum, null, null, "prune", "days");

		waitForMessage(MSG_PRUNE_INVALID_JOB_DAY);
		click(ELEMENT_PRUNE_INVALID_RUN_JOB_OK);
		assert waitForAndGetElement(ELEMENT_PRUNE_JOB_DAY).getAttribute("value").equals("0"):"Not reset the run prune field";
		click(ELEMENT_PRUNE_CLOSE_BUTTON);

		//Delete data
		click(mForum.ELEMENT_CATEGORY_BREAD.replace("${category}", category));
		cat.deleteCategoryInForum(category);
	}

	/**
	 * Case ID:106977.
	 * Run Prune in case Prune setting have not been defined for forum
	 */
	@Test
	public void test05_RunPruneInCasePruneSettingHaveNotBeenDefinedForForum(){
		String category = "Category 106977";
		String forum = "Forum 106977";
		String topic = "Topic 106977";
		info("Run Prune in case Prune setting have not been defined for forum");

		/*
		- Login by administration
		- Create categories, forums, topics and posts inside
		 *Expected Outcome: 
		- Login successfully
		- Categories, forums, topics and posts are created		*/
		magtopic.addCategoryForumTopic(category, forum, topic, topic);

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto
		-Prune dialog is shown property with list forum existing		*/
		goToPruneManagement();

		/*
		- Select category at step 1 and click Run
		 *Input Data: 
		 *Expected Outcome: Show alert message "Please configure the prune settings for this item."		*/ 
		click(ELEMENT_PRUNE_RUN.replace("${category}", category).replace("{$forum}", forum));
		waitForMessage(MSG_PRUNE_NOT_CONFIG);
		click(ELEMENT_PRUNE_NOT_CONFIG_OK);
		click(ELEMENT_PRUNE_CLOSE_BUTTON);

		//Delete data
		click(mForum.ELEMENT_CATEGORY_BREAD.replace("${category}", category));
		cat.deleteCategoryInForum(category);
	}

	/**
	 * Case ID:106969.
	 * Test Case Name: Test Auto-Prune in case Forum contains some topics which don't have new post.
	 * Pre-Condition: Go to Forum / Settings: Open Form Settings tab.Change the Time Zone to the same time zone of your computer.
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/03 16:58:45
	 * Pending this case because can not check setting time. 
	 */
	@Test (groups = "pending")
	public  void test06_TestAutoPruneInCaseForumContainsSomeTopicsWhichDontHaveNewPost() {
		info("Test 6: Test Auto-Prune in case Forum contains some topics which don't have new post");
		/*
		- Login by administration
		- Create categories, forums, topics and posts inside
		 *Expected Outcome: 
		- Login successfully
		- Categories, forums, topics and posts are created		*/


		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto
		-Prune dialog is shown property with list forum existing		*/


		/*Click on Prune setting icon corresponding of forum which want to Set Auto-Prune
		 *Input Data: 
		 *Expected Outcome: Prune setting pop up is shown property		*/


		/*
		- Input value into Inactive day field (ex: 2 days) and Run Prune field
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Value of Inactive day is set successful		*/

		/*
		- Don't add any post in topics of forum which setting prune at step 5
		- After 2 days,Goto Prune setting dialog of forum which setting at step 5
		- Click on Run button.Note:Tester should change server date-time to check the Auto-Prune effects
		 *Input Data: 
		 *Expected Outcome: Display the number of topic would be pruned.		*/ 

	}

	/**
	 * Case ID:106973.
	 * Test Case Name: Test Auto-Prune in case Forum contains some topics which have new posts.
	 * Pre-Condition: Go to Forum / settings to set Time Zone same with Time Zone of your computer.
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/03 16:58:45
	 * Pending this case because can not check setting time. 
	 */
	@Test (groups = "pending")
	public  void test07_TestAutoPruneInCaseForumContainsSomeTopicsWhichHaveNewPosts() {
		info("Test 7: Test Auto-Prune in case Forum contains some topics which have new posts");
		/*
		- Login by administration
		- Create categories, forums, topics and posts inside
		 *Expected Outcome: 
		- Login successfully
		- Categories, forums, topics and posts are created		*/

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto-Prune dialog is shown property with list forum existing		*/

		/*Click on [Prune setting] icon corresponding of forum which want to Set Auto-Prune
		 *Input Data: 
		 *Expected Outcome: Prune setting pop up is shown property		*/

		/*
		- Input value into Inactive day field (ex: 2 days) and Run Prune
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Value of Inactive day is set successful		*/

		/*
		- Add some posts in topics of forum which setting prune at step 5
		- After 2 days,Goto [Prune setting] dialog of forum which setting at step 5
		- Click on [Run] button
		 *Input Data: 
		 *Expected Outcome: Display the number of pruned topic is "0"		*/ 
	}

	/**
	 * Case ID:106980.
	 * Test Case Name: Run Prune in case Prune setting have defined for forum and there are pruned topics.
	 * Pre-Condition: Go to Forum / settings to set Time Zone same with Time Zone of your computer.
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/03 16:58:45
	 * Pending this case because can not check setting time. 
	 */
	@Test (groups = "pending")
	public  void test08_RunPruneInCasePruneSettingHaveDefinedForForumAndThereArePrunedTopics() {
		info("Test 8: Run Prune in case Prune setting have defined for forum and there are pruned topics");
		/*
		- Login by administration
		- Create categories, forums, topics and posts inside
		 *Expected Outcome: 
		- Login successfully
		- Categories, forums, topics and posts are created		*/

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto-Prune dialog is shown property with list forum existing		*/

		/*Click on Prune setting icon corresponding of forum which want to Set Auto-Prune
		 *Input Data: 
		 *Expected Outcome: Prune setting pop up is shown property		*/

		/*
		- Input value into Inactive day field (ex: 2 days) and Run Prune field
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Value of Inactive day and Run Prune are set successful		*/

		/*
		- Don't add any post in topics of forum which setting prune at step 5
		- After 2 days, Click on Run icon corresponding of forum which setting prune
		 *Input Data: 
		 *Expected Outcome: Run Prune pop up is displayed and shown the total topic which will pruned		*/

		/*Click on Run button
		 *Input Data: 
		 *Expected Outcome: 
		- Run Prune in forum is successful
		- Displaying time run prune in Last run column of Auto-Prune dialog		*/ 

	}

	/**
	 * Case ID:106982.
	 * Test Case Name: Run Prune in case Prune setting have defined for forum and there is no pruned topic.
	 * Pre-Condition: Go to Forum / settings to set Time Zone same with Time Zone of your computer.
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/03 16:58:45
	 * Pending this case because can not check setting time. 
	 */
	@Test (groups = "pending")
	public  void test09_RunPruneInCasePruneSettingHaveDefinedForForumAndThereIsNoPrunedTopic() {
		info("Test 9: Run Prune in case Prune setting have defined for forum and there is no pruned topic");
		/*
		- Login by administration
		- Create categories, forums, topics and posts inside
		 *Expected Outcome: 
		- Login successfully
		- Categories, forums, topics and posts are created		*/

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto-Prune dialog is shown property with list forum existing		*/

		/*Click on [Prune setting] icon corresponding of forum which want to Set Auto
		-Prune
		 *Input Data: 
		 *Expected Outcome: Prune setting pop up is shown property		*/

		/*
		- Input value into Inactive day field (ex: 2 days) and run schedule field
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Value of Inactive day is set successful		*/

		/*
		- Add some post in topics of forum which setting prune at step 5
		- After 2 days, Click on [Run] icon corresponding of forum which setting prune
		 *Input Data: 
		 *Expected Outcome: Show warning message "This forum has no pruned topic"		*/ 

	}

	/**
	 * Case ID:106985.
	 * Test Case Name: Active Auto-Prune in case Prune setting have defined for forum.
	 * Pre-Condition: Go to Forum / settings to set Time Zone same with Time Zone of your computer.
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/03 16:58:45
	 * Pending this case because can not check setting time. 
	 */
	@Test (groups = "pending")
	public  void test10_ActiveAutoPruneInCasePruneSettingHaveDefinedForForum() {
		info("Test 10 Active Auto-Prune in case Prune setting have defined for forum");
		/*
		- Login by administration
		- Create categories, forums, topics and posts inside
		 *Expected Outcome: 
		- Login successfully
		- Categories, forums, topics and posts are created		*/

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto-Prune dialog is shown property with list forum existing		*/

		/*Click on "Prune setting" icon corresponding of forum which want to Set Auto-Prune
		 *Input Data: 
		 *Expected Outcome: Prune setting pop up is shown property		*/

		/*
		- Input value into Inactive day field and Run Prune field
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Value of Inactive day is set successful		*/

		/*Tick on check box corresponding of forum which want to active.Note:Tester should change server date-time to check the Auto-Prune effects
		 *Input Data: 
		 *Expected Outcome: 
		- Auto-Prune is active for forum as setting at that time
		- Run prune is automatic as selected period time
		- Pruned topic is displayed with status is inactive, all normal user can not view right it except administrator and forum's moderator		*/ 
	}

	/**
	 * Case ID:106987.
	 * Test Case Name: Active Auto-Prune in case Prune setting have not been defined for forum.
	 * Pre-Condition: Go to Forum / settings to set Time Zone same with Time Zone of your computer.
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/03 16:58:45
	 * Pending this case because can not check setting time. 
	 */
	@Test (groups = "pending")
	public  void test11_ActiveAutoPruneInCasePruneSettingHaveNotBeenDefinedForForum() {
		info("Test 11 Active Auto-Prune in case Prune setting have not been defined for forum");
		/*
		- Login by administration
		- Create categories, forums, topics and posts inside
		 *Expected Outcome: 
		- Login successfully
		- Categories, forums, topics and posts are created		*/

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto-Prune dialog is shown property with list forum existing		*/

		/*Tick on check box corresponding of forum which have been not setting prune
		 *Input Data: 
		 *Expected Outcome: Prune setting dialog appears and allows set value for prune		*/

		/*
		- Input value into Inactive day field and Run Prune field
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: 
		- Auto-Prune is active for forum as setting at that time
		- Run prune is automatic as selected period time
		- Pruned topic is displayed with status is inactive, all normal user can not view right it except administrator and forum's moderator		*/ 

	}

	/**
	 * Case ID:106989.
	 * Test Case Name: Desactive Auto-Prune of forum which is being activated.
	 * Pre-Condition: Go to Forum / settings to set Time Zone same with Time Zone of your computer.
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/07/03 16:58:45
	 * Pending this case because can not check setting time. 
	 */
	@Test (groups = "pending")
	public  void test12_DesactiveAutoPruneOfForumWhichIsBeingActivated() {
		info("Test 12 Desactive Auto-Prune of forum which is being activated");
		/*
		- Login by administration
		- Create categories, forums, topics and posts inside
		 *Expected Outcome: 
		- Login successfully
		- Categories, forums, topics and posts are created		*/

		/*
		- Click on [Administration] and select [Pruning ] in drop down menu
		 *Input Data: 
		 *Expected Outcome: Auto-Prune dialog is shown property with list forum existing		*/

		/*Click on "Prune setting" icon corresponding of forum which want to Set Auto
		-Prune
		 *Input Data: 
		 *Expected Outcome: Prune setting pop up is shown property		*/

		/*
		- Input value into Inactive day field 
		- Click on Save button
		 *Input Data: 
		 *Expected Outcome: Value of Inactive day is set successful		*/

		/*Tick on check box corresponding of forum which want to active
		 *Input Data: 
		 *Expected Outcome: 
		- Auto-Prune is active for forum as setting at that time
		- Run prune is automatic as selected period time
		- Pruned topic is displayed with status is inactive, all normal user can not view right it except administrator and forum's moderator		*/

		/*
		- Go to [Auto-Prune] tab
		- Untick on check box corresponding of forum which is being activated Auto-Prune
		 *Input Data: 
		 *Expected Outcome: Forum is deactivate Auto-Prune at that time		*/ 

	}
}