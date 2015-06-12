package org.exoplatform.selenium.platform.forum.functional.forum.poll;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.PageManagement;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePoll;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * 
 * @author havtt
 * @date 21-Jan-2014
 * ISSUE Pending because https://jira.exoplatform.org/browse/FORUM-998
 */

public class Forum_Forum_Poll_AddEditDelete extends ForumBase {

	ManageAccount acc;
	ForumManageCategory fmCat;
	ForumManageForum fmForum;
	ForumManageTopic fmTopic;
	ForumManagePoll fmPoll;
	NavigationToolbar nav;
	HomePageActivity home;
	PageManagement mPage; 
	PageEditor pageEdit;
	
	@BeforeMethod
	public void setUpBeforeTest() {
		initSeleniumTest();
		driver.get(baseUrl);
		fmCat = new ForumManageCategory(driver,this.plfVersion);
		fmForum = new ForumManageForum(driver,this.plfVersion);
		fmPoll = new ForumManagePoll(driver,this.plfVersion);
		fmTopic = new ForumManageTopic(driver,this.plfVersion);
		nav = new NavigationToolbar(driver, this.plfVersion);
		home = new HomePageActivity(driver, this.plfVersion);
		mPage = new PageManagement(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		acc = new ManageAccount(driver,this.plfVersion);
		pageEdit = new PageEditor(driver, this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);
		alert = new ManageAlert(driver);
	}

	@AfterMethod
	public void afterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/**
	 * Case ID: 72312
	 * Add Poll in case only have 2 options
	 */
	@Test
	public void test01_AddPollWithTwoOptions() {
		String categoryName = "category72321";
		String forumName = "forum72312";
		String topicName = "topic72312";
		String pollQuestion = "Do you love me?";
		String[] pollOption = {"Yes", "No"};
		String timeout = "1";
		
		info("Go to Forum");
		goToForums();
		
		info("Create a new category and forum inside");
		fmForum.addCategoryForum(categoryName, forumName);
		
		info("Creat a new topic");
		click(fmTopic.ELEMENT_START_TOPIC_BUTTON);
		fmTopic.startTopic(topicName, topicName, "", 0, null, true, true);
		waitForAndGetElement(By.linkText(topicName)).click();
		
		info("Add a new poll with 2 options into topic");
		fmPoll.addPoll(pollQuestion,pollOption,timeout,false, false);
		driver.navigate().refresh();
		
		info("Verify 2 options are displayed");
		waitForAndGetElement(fmPoll.ELEMENT_OPTION.replace("${option}",pollOption[0]));
		waitForAndGetElement(fmPoll.ELEMENT_OPTION.replace("${option}",pollOption[1]));
		
		info("Restore data");
		goToForumHome();
		click(By.linkText(categoryName));
		fmCat.deleteCategoryInForum(categoryName, true);	
	}
	
	/**
	 * Case ID: 72471
	 * Add Poll in case have more than 2 options
	 */
	@Test
	public void test02_AddPollWithMoreThanTwoOptions() {
		String categoryName = "category72471";
		String forumName = "forum72471";
		String topicName = "topic72471";
		String pollQuestion = "Do you love me?";
		String[] pollOption = {"Yes", "No", "Not sure"};
		String timeout = "1";
		
		info("Go to Forum");
		goToForums();
		
		info("Create a new category and forum inside");
		fmForum.addCategoryForum(categoryName, forumName);
		
		info("Creat a new topic");
		click(fmTopic.ELEMENT_START_TOPIC_BUTTON);
		fmTopic.startTopic(topicName, topicName, "", 0, null, true, true);
		waitForAndGetElement(By.linkText(topicName)).click();
		
		info("Add a new poll with more than 2 options into topic");
		fmPoll.goToAddPoll();
		//Add more field(s) to fill more than 2 options
		click(fmPoll.ELEMENT_ADDMORE_ITEMS);
		fmPoll.inputFormPoll(pollQuestion, pollOption, timeout, false, false);
		click(fmPoll.ELEMENT_POLL_SUBMIT_BUTTON);
		
		info("Verify 3 options are displayed");
		waitForAndGetElement(fmPoll.ELEMENT_OPTION.replace("${option}",pollOption[0]));
		waitForAndGetElement(fmPoll.ELEMENT_OPTION.replace("${option}",pollOption[1]));
		waitForAndGetElement(fmPoll.ELEMENT_OPTION.replace("${option}",pollOption[2]));
		
		info("Restore data");
		goToForumHome();
		click(By.linkText(categoryName));
		fmCat.deleteCategoryInForum(categoryName, true);	
	}	
	
	/**
	 * Case ID: 724588
	 * Add Poll in case have only 1 option
	 */
	@Test
	public void test03_AddPollWithOnlyOneOption() {
		String categoryName = "category724588";
		String forumName = "forum724588";
		String topicName = "topic724588";
		String pollQuestion = "Do you love me?";
		String[] pollOption = {"Not sure"};
		String timeout = "1";
		
		info("Go to Forum");
		goToForums();
		
		info("Create a new category and forum inside");
		fmForum.addCategoryForum(categoryName, forumName);
		
		info("Creat a new topic");
		click(fmTopic.ELEMENT_START_TOPIC_BUTTON);
		fmTopic.startTopic(topicName, topicName, "", 0, null, true, true);
		waitForAndGetElement(By.linkText(topicName)).click();
		
		info("Add a new poll with 2 options into topic");
		fmPoll.goToAddPoll();
		fmPoll.inputFormPoll(pollQuestion, pollOption, timeout, false, false);
	
		info("Verify the alert msg to warn user to add more options");
		click(fmPoll.ELEMENT_POLL_SUBMIT_BUTTON);
		waitForAndGetElement(fmPoll.ELEMENT_WARNING_ADD_MORE_OPTS);
		driver.navigate().refresh();
		
		info("Restore data");
		goToForumHome();
		click(By.linkText(categoryName));
		fmCat.deleteCategoryInForum(categoryName, true);	
	}
	
	/**
	 * Case ID: 72686
	 * Add Poll with single choice and no change
	 */
	@Test
	public void test04_AddPollWithSingleChoiceAndNUableToChange() {
		String categoryName = "category72686";
		String forumName = "forum72686";
		String topicName = "topic72686";
		String pollQuestion = "Do you love me?";
		String[] pollOption = {"Yes","No"};
		String timeout = "1";
		
		info("Go to Forum");
		goToForums();
		
		info("Create a new category and forum inside");
		fmForum.addCategoryForum(categoryName, forumName);
		
		info("Creat a new topic");
		click(fmTopic.ELEMENT_START_TOPIC_BUTTON);
		fmTopic.startTopic(topicName, topicName, "", 0, null, true, true);
		waitForAndGetElement(By.linkText(topicName)).click();
		
		info("Add a new poll with 2 options into topic");
		fmPoll.addPoll(pollQuestion,pollOption,timeout,false, false);
		driver.navigate().refresh();
		
		info("Verify options are single choice");
		isElementPresent(fmPoll.ELEMENT_OPTION_RADIO.replace("${option}",pollOption[0]));
		isElementPresent(fmPoll.ELEMENT_OPTION_RADIO.replace("${option}",pollOption[1]));
		
		
		info("Vote once time");
		check(fmPoll.ELEMENT_OPTION_RADIO.replace("${option}",pollOption[0]),2);
		click(fmPoll.ELEMENT_VOTE_NOW_BUTTON);
		
		info("Verify options are impossible to be changed");
		waitForElementNotPresent(fmPoll.ELEMENT_VOTE_AGAIN);
		
		info("Restore data");
		goToForumHome();
		click(By.linkText(categoryName));
		fmCat.deleteCategoryInForum(categoryName, true);	
	}
	
	/**
	 * Case ID: 72763
	 * Add Poll with single choice and user can change vote
	 */
	@Test
	public void test05_AddPollWithSingleChoiceAndAbleToChange() {
		String categoryName = "category72763";
		String forumName = "forum72763";
		String topicName = "topic72763";
		String pollQuestion = "Do you love me?";
		String[] pollOption = {"Yes","No"};
		String timeout = "1";
		
		info("Go to Forum");
		goToForums();
		
		info("Create a new category and forum inside");
		fmForum.addCategoryForum(categoryName, forumName);
		
		info("Creat a new topic");
		click(fmTopic.ELEMENT_START_TOPIC_BUTTON);
		fmTopic.startTopic(topicName, topicName, "", 0, null, true, true);
		waitForAndGetElement(By.linkText(topicName)).click();
		
		info("Add a new poll with 2 options into topic");
		fmPoll.addPoll(pollQuestion,pollOption,timeout,true, false);
		driver.navigate().refresh();
		
		info("Verify options are single choice");
		isElementPresent(fmPoll.ELEMENT_OPTION_RADIO.replace("${option}",pollOption[0]));
		isElementPresent(fmPoll.ELEMENT_OPTION_RADIO.replace("${option}",pollOption[1]));
		
		info("Vote once time");
		check(fmPoll.ELEMENT_OPTION_RADIO.replace("${option}",pollOption[0]),2);
		click(fmPoll.ELEMENT_VOTE_NOW_BUTTON);
		
		info("Verify options are possible to be changed");
		waitForAndGetElement(fmPoll.ELEMENT_VOTE_AGAIN);
		
		info("Restore data");
		goToForumHome();
		click(By.linkText(categoryName));
		fmCat.deleteCategoryInForum(categoryName, true);	
	}
	
	/**
	 * Case ID: 73333
	 * Add Poll  with multiple choices and don't allow change vote
	 */
	@Test
	public void test06_AddPollWithMultipleChoiceAndAbleToChange() {
		String categoryName = "category73333";
		String forumName = "forum73333";
		String topicName = "topic3333";
		String pollQuestion = "Do you love me?";
		String[] pollOption = {"Yes","No"};
		String timeout = "1";
		
		info("Go to Forum");
		goToForums();
		
		info("Create a new category and forum inside");
		fmForum.addCategoryForum(categoryName, forumName);
		
		info("Creat a new topic");
		click(fmTopic.ELEMENT_START_TOPIC_BUTTON);
		fmTopic.startTopic(topicName, topicName, "", 0, null, true, true);
		waitForAndGetElement(By.linkText(topicName)).click();
		
		info("Add a new poll with 2 options into topic");
		fmPoll.addPoll(pollQuestion,pollOption,timeout,true, true);
		driver.navigate().refresh();
		
		info("Verify options are multiple choice");
		isElementPresent(fmPoll.ELEMENT_OPTION_CHECKBOX.replace("${option}",pollOption[0]));
		isElementPresent(fmPoll.ELEMENT_OPTION_CHECKBOX.replace("${option}",pollOption[1]));
		
		info("Vote once time");
		check(fmPoll.ELEMENT_OPTION_CHECKBOX.replace("${option}",pollOption[0]),2);
		click(fmPoll.ELEMENT_VOTE_NOW_BUTTON);
		
		info("Verify options are possible to be changed");
		waitForAndGetElement(fmPoll.ELEMENT_VOTE_AGAIN);
		
		info("Restore data");
		goToForumHome();
		click(By.linkText(categoryName));
		fmCat.deleteCategoryInForum(categoryName, true);	
	}
	
	/**
	 * Case ID: 72976
	 * Add Poll when set time to close poll
	 */
	
	@Test(groups={"pending"})
	public void test07_AddPollWithClosingTimeSetting() {
		//Wait for issue: FQA-1612
	}
	
	/**
	 * Case ID: 72313
	 * Edit Poll
	 * This case relates to issue: https://jira.exoplatform.org/browse/FORUM-998
	 */
	@Test (groups = "pending")
	public void test10_EditPoll() {
		String categoryName = "category72313";
		String forumName = "forum72313";
		String topicName = "topic72313";
		String pollQuestion = "Do you love me?";
		String pollQuestion_edited = "Do you love me, sweety?";
		String[] pollOption = {"Yes", "No"};
		String timeout = "1";
		String timeout_edited = "2";
		
		info("Go to Forum");
		goToForums();
		
		info("Create a new category and forum inside");
		fmForum.addCategoryForum(categoryName, forumName);
		
		info("Creat a new topic");
		click(fmTopic.ELEMENT_START_TOPIC_BUTTON);
		fmTopic.startTopic(topicName, topicName, "", 0, null, true, true);
		waitForAndGetElement(By.linkText(topicName)).click();
		
		info("Add a new poll with 2 options into topic");
		fmPoll.addPoll(pollQuestion,pollOption,timeout,false, false);
		driver.navigate().refresh();
		
		info("Verify 2 options are displayed");
		waitForAndGetElement(fmPoll.ELEMENT_OPTION.replace("${option}",pollOption[0]));
		waitForAndGetElement(fmPoll.ELEMENT_OPTION.replace("${option}",pollOption[1]));
		
		info("Edit poll");
		fmPoll.editPoll(pollQuestion_edited, pollOption, timeout_edited, false, true);
		
		info("Restore data");
		goToForumHome();
		click(By.linkText(categoryName));
		fmCat.deleteCategoryInForum(categoryName, true);	
	}
	
	
	/**
	 * Case ID: 72314
	 * Add Poll in case only have 2 options
	 */
	@Test
	public void test11_DeletePoll() {
		String categoryName = "category72314";
		String forumName = "forum72314";
		String topicName = "topic72314";
		String pollQuestion = "Do you love me?";
		String[] pollOption = {"Yes", "No"};
		String timeout = "1";
		
		info("Go to Forum");
		goToForums();
		
		info("Create a new category and forum inside");
		fmForum.addCategoryForum(categoryName, forumName);
		
		info("Creat a new topic");
		click(fmTopic.ELEMENT_START_TOPIC_BUTTON);
		fmTopic.startTopic(topicName, topicName, "", 0, null, true, true);
		waitForAndGetElement(By.linkText(topicName)).click();
		
		info("Add a new poll with 2 options into topic");
		fmPoll.addPoll(pollQuestion,pollOption,timeout,false, false);
		driver.navigate().refresh();
		
		info("Delete poll");
		fmPoll.deletePollInTopic(pollQuestion);
		
		info("Restore data");
		goToForumHome();
		click(By.linkText(categoryName));
		fmCat.deleteCategoryInForum(categoryName, true);	
	}
	
	/**
	 * TC_ID:105960
	 * @author quynhpt
	 */
	@Test
	public void test08_AddPollInCasePublicData(){
		info("Test 08: Add Poll in case public Data");
		String nodeName = getRandomString();
		String num=getRandomNumber();
		String displayName = "Node_"+num;
		String title = "Collaboration";
		Map<String, String> portletIds= new HashMap<String, String>();
		portletIds.put("Collaboration/PollPortlet", "");
		String pollQuestion = "Poll_"+num;
		String[] options = {"option1", "option2"};

		/*
		- Connect to Intranet
		- Create a page by winzard
		- Choose Poll porlet at step 3
		 *Input Data: 
		 *Expected Outcome: Page with Poll portlet is create successfully		*/
		nav.goToPageCreationWizard();
		Utils.pause(5000);
		click(ELEMENT_UP_LEVEL);
		info("Create page");
		mPage.addNewPageEditor(nodeName, displayName,"",title, portletIds, false, true);
		Utils.pause(5000);		

		/*
		- Open above page
		- Click Edit > Layout
		- Edit Poll portlet
		- Click on Add Poll
		- Fill all valid values
		- Click Save > Finish 
		 *Input Data: 
		 *Expected Outcome: Poll is added and shown in Page		*/
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);
		click(ELEMENT_EDIT_PORTLET_ICON);
		click(fmPoll.ELEMENT_ADD_POLL_BUTTON);
		info("Add Poll in Portlet");
		fmPoll.inputFormPoll(pollQuestion, options, "", false, false);
		click(fmPoll.ELEMENT_POLL_SUBMIT_BUTTON);
		Utils.pause(500);
		button.close();
		pageEdit.addAccessPermissionforPortlet(ELEMENT_FRAME_CONTAIN_PORTLET, "", "", true);
		click(ELEMENT_PAGE_FINISH_BUTTON);
		waitForTextNotPresent("Page Editor");
		Utils.pause(1000);
		waitForAndGetElement(fmPoll.ELEMENT_POLL_TITLE.replace("${poll}", pollQuestion));
        
		/*
		- Login with normal user
		- Go to the portlet
		 *Expected Outcome: 
		- The poll portlet is shown and user can choose and vote	*/ 
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		click(ELEMENT_LEFT_NAVIGATION_ITEM.replace("${menuItem}", displayName));
		waitForAndGetElement(fmPoll.ELEMENT_POLL_TITLE.replace("${poll}", pollQuestion));
		fmPoll.votePollSingleChoice(false,options[1]);
		
		/*
		- Login with admin account
		- Open page with Poll portlet
		- Remove this poll
		 *Input Data: 
		 *Expected Outcome: Poll is deleted		*/
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		info("Remove Poll in Portlet");
		click(ELEMENT_LEFT_NAVIGATION_ITEM.replace("${menuItem}", displayName));
		click(fmPoll.ELEMENT_POLL_MORE_ACTION);
		click(fmPoll.ELEMENT_POLL_DELETE_LINK);		
		Utils.pause(500);
		alert.acceptAlert();

		//delete data
		info("Delete page");
		mPage.deletePageAtManagePageAndPortalNavigation(nodeName, true, "intranet", false, null,displayName);	
	}
	
	/**
	 * TC_ID: 105990
	 */
	@Test
	public void test09_AddPollInCaseLimitViewRight(){
		info("Test 09: Add Poll in case limit view Right");
		String nodeName = getRandomString();
		String num=getRandomNumber();
		String displayName = "Node_"+num;
		String title = "Collaboration";
		Map<String, String> portletIds= new HashMap<String, String>();
		portletIds.put("Collaboration/PollPortlet", "");
		String pollQuestion = "Poll_"+num;
		String options = "option1/option2";
		String group ="Platform/Content Management";

		/*
		- Connect to Intranet
		- Create a page by winzard
		- Choose Poll porlet at step 3
		 *Input Data: 
		 *Expected Outcome: Page with Poll portlet is create successfully		*/
		nav.goToPageCreationWizard();
		Utils.pause(5000);
		click(ELEMENT_UP_LEVEL);
		info("Create page");
		mPage.addNewPageEditor(nodeName, displayName,"",title, portletIds, false, true);
		Utils.pause(5000);		

		/*
		- Open above page
		- Click Edit > Layout
		- Edit Poll portlet
		- Click on Add Poll
		- Fill all valid values
		- Click Save > Finish 
		 *Input Data: 
		 *Expected Outcome: Poll is added and shown in Page		*/
		mouseOver(ELEMENT_FRAME_CONTAIN_PORTLET,true);
		click(ELEMENT_EDIT_PORTLET_ICON);
		fmPoll.addPollAndSelectPoll(pollQuestion,options,false,group,"",false,false);
		waitForAndGetElement(fmPoll.ELEMENT_POLL_TITLE.replace("${poll}", pollQuestion));
		String[] pollOption = options.split("/");
		/*
		- Login with normal user
		- Go to the portlet
		 *Expected Outcome: 
		- The poll portlet isn't shown and user cannot choose and vote	*/ 
		acc.signOut();
		acc.signIn(DATA_USER4, DATA_PASS);
		click(ELEMENT_LEFT_NAVIGATION_ITEM.replace("${menuItem}", displayName));
		waitForElementNotPresent(fmPoll.ELEMENT_POLL_TITLE.replace("${poll}", pollQuestion));
		
		/*
		- Login with mary account of Content Management group
		- Go to the portlet
		 *Expected Outcome: The poll portlet is shown and user can choose and vote*/
		acc.signOut();
		acc.signIn(DATA_USER2, DATA_PASS);
		click(ELEMENT_LEFT_NAVIGATION_ITEM.replace("${menuItem}", displayName));
		waitForAndGetElement(fmPoll.ELEMENT_POLL_TITLE.replace("${poll}", pollQuestion));
		fmPoll.votePollSingleChoice(false,pollOption[1]);
		
		/*
		 - Login with admin account
		 - delete poll portlet
		 */
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		info("Remove Poll in Portlet");
		click(ELEMENT_LEFT_NAVIGATION_ITEM.replace("${menuItem}", displayName));
		click(fmPoll.ELEMENT_POLL_MORE_ACTION);
		click(fmPoll.ELEMENT_POLL_DELETE_LINK);		
		Utils.pause(500);
		alert.acceptAlert();

		//delete data
		info("Delete page");
		mPage.deletePageAtManagePageAndPortalNavigation(nodeName, true, "intranet", false, null,displayName);	
	}
}
