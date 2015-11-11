package org.exoplatform.selenium.platform.plf.functional.homepagegadgets;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.HomePageGadget;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.ManageApplications;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManagePoll;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.wiki.BasicAction;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author HangNTT
 *
 */
public class PLF_HomepageGadgets_FeaturePoll extends BasicAction{

	//Platform
	ManageAccount magAcc;
	NavigationToolbar naviToolbar;
	HomePageActivity home;

	//Forum
	ForumManageTopic mngTopic;
	ForumManagePoll mngPoll;
	ForumManageCategory mngCat;
	ManageApplications app;
	PageEditor pageEditor;
	HomePageGadget hpGadget;

	@BeforeMethod
	public void beforeMethods(){	
		initSeleniumTest();
		driver.get(plfURL);
		magAcc = new ManageAccount(driver, this.plfVersion);
		naviToolbar = new NavigationToolbar(driver, this.plfVersion);
		mngTopic = new ForumManageTopic(driver, this.plfVersion);
		mngPoll = new ForumManagePoll(driver, this.plfVersion);
		mngCat = new ForumManageCategory(driver, this.plfVersion);
		app = new ManageApplications(driver, this.plfVersion);
		pageEditor = new PageEditor(driver, this.plfVersion);
		home = new HomePageActivity(driver, this.plfVersion);
		hpGadget = new HomePageGadget(driver, this.plfVersion);
		magAcc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();

	}

	/**
	* Case ID:74329.
	* Test Case Name: Check the displaying of poll.
	* Pre-Condition: 
	* Post-Condition: 
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/02/10 15:08:39
	*/
	@Test
	public  void test01_CheckTheDisplayingOfPoll() {
		String titleCat = "Category 74329";
		String titleForum = "Forum 74329";
		String titleTop = "Topic 74329";
		String poll = "Poll of topic 74329";
		String[] options =  {"Option 743291","Option 743292"};
		String[] rate = {"0","0"};
		By ELEMENT_TOPIC_TITLE_1 = By.linkText(titleTop);
		
		info("Test 1: Check the displaying of poll");
		/*Step Number: 1
		*Step Name: -
		*Step Description: 
			Step 1: Add new poll
		*Input Data: 
			- Go to Forum page
			- Create new category, forum, topic
			- Select topic
			- Click on More Action → Add Poll
			- Fill value in required fields
			- Save
		*Expected Outcome: 
			New poll is added successfully*/
		mngTopic.goToForums();
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,titleTop);
		click(ELEMENT_TOPIC_TITLE_1);
		mngPoll.addPoll(poll, options, "3000", false, false);
		
		/*Step number: 2
		*Step Name: -
		*Step Description: 
			Step 2: Check the displaying of poll in Featured Poll gadget
		*Input Data: 
			- Go to homepage of intranet site
		*Expected Outcome: 
			New poll is shown*/
		naviToolbar.goToHomePage();
		home.checkAddPoll(titleTop, poll, options, rate);
		
		/*Step number: 3
		*Step Name: -
		*Step Description: 
			Step 3: Vote for poll
		*Input Data: 
			- Select 1 option in poll
			- Click on Vote
		*Expected Outcome: 
			Show the result of poll*/
		home.openVoteFromActivity(poll);
		mngPoll.votePollSingleChoice(false, options[0]);
		waitForAndGetElement(mngPoll.ELEMENT_TOTAL_VOTE).getText().contains("Total Voters: 1");
		waitForAndGetElement(mngPoll.ELEMENT_VOTE_POLL_NUMBER.replace("${pollOption}", options[0]).replace("${voteNumber}", "1"));
		
		/*Clear data*/
		info("Clear data");
		mngTopic.goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat);
 	}

	/**
	* Case ID:74330.
	* Test Case Name: Setting for the gadget.
	* Pre-Condition: 
	* Post-Condition:
	* Done with OSs and browsers : 
	* Generated by phuongdt at 2014/02/10 15:08:39
	*/
	@Test
	public  void test02_SettingForTheGadget() {
		String[] permission = {};
		String titleCat = "Category 74330";
		String titleForum = "Forum 74330";
		String titleTop1 = "Topic 174330";
		String titleTop2 = "Topic 274330";
		String poll1 = "Poll of topic 174330";
		String poll2 = "Poll of topic 274330";
		String[] options1 =  {"Option 1743301","Option 1743302"};
		String[] options2 =  {"Option 2743301","Option 2743302"};
		By ELEMENT_TOPIC_TITLE_1 = By.linkText(titleTop1);
		By ELEMENT_TOPIC_TITLE_2 = By.linkText(titleTop2);
		
		
		info("Test 2: Setting for the gadget");
		/*Step Number: 1
		*Step Name: Step 1: Add new polls
		*Step Description: 
			- Go to Forum page
			- Create new category, forum, topic
			- Select topic
			- Click on More Action → Add Poll
			- Fill value in required fields
			- Save
		*Input Data: 
			
		*Expected Outcome: 
			New poll are added successfully*/
		mngTopic.goToForums();
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop1,titleTop1);
		click(ELEMENT_TOPIC_TITLE_1);
		mngPoll.addPoll(poll1, options1, "3000", false, false);
		waitForAndGetElement(By.linkText(titleForum)).click();
		mngTopic.startTopic(titleTop2, titleTop2, "", 0, permission, false, false, false);
		click(ELEMENT_TOPIC_TITLE_2);
		mngPoll.addPoll(poll2, options2, "3000", false, false);
		
		/*Step number: 2
		*Step Name: Step 2:Setting for the gadget
		*Step Description: 
			- Go to intranet homepageand add Featured poll gadget
			- Click on Setting icon
			- Choose poll that you want
		*Input Data: 
			
		*Expected Outcome: 
			The selected poll is shown */
		//Add poll gadget
		// Import all application
		app.showImportApplication(true);
		app.importApplication();
		naviToolbar.goToHomePage();
		naviToolbar.goToEditPageEditor();
		click(ELEMENT_CATEGORY_COLLABORATION);
		dragAndDropToObject(hpGadget.ELEMENT_APPLICATION_POLL, hpGadget.ELEMENT_MIDDLE_CONTAINER);
		waitForAndGetElement(pageEditor.ELEMENT_PORTLET_DRAGGED.replace("${portlet}", "Featured Poll"));
		pageEditor.finishEditLayout();	
		naviToolbar.goToHomePage();
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_GADGET_WORKSPACE_FRAME));
		click(hpGadget.ELEMENT_SETTING_POLL_GADGET);
		waitForAndGetElement(hpGadget.ELEMENT_SELECT_BOX_FEATURED_POLL);
		String pollValue = waitForAndGetElement(hpGadget.ELEMENT_SELECT_BOX_FEATURED_ITEM.replace("${pollName}", poll2)).getAttribute("value");
		selectOption(hpGadget.ELEMENT_SELECT_BOX_FEATURED_POLL, pollValue);
		waitForAndGetElement(hpGadget.ELEMENT_POLL_NAME_ITEM.replace("${pollOption}", options2[0]));
		waitForAndGetElement(hpGadget.ELEMENT_POLL_NAME_ITEM.replace("${pollOption}", options2[1]));
		waitForElementNotPresent(hpGadget.ELEMENT_POLL_NAME_ITEM.replace("${pollOption}", options1[0]));
		waitForElementNotPresent(hpGadget.ELEMENT_POLL_NAME_ITEM.replace("${pollOption}", options1[1]));
		driver.switchTo().defaultContent();
		
		/*Clear data*/
		info("Clear data");
		naviToolbar.goToEditPageEditor();
		click(ELEMENT_SWITCH_VIEW_MODE);
		waitForAndGetElement(pageEditor.ELEMENT_VIEW_PAGE_PROPERTIES);
		pageEditor.removePortlet(hpGadget.ELEMENT_GADGET_PORLET_IN_MIDDLE_HOME_PAGE, hpGadget.ELEMENT_DELETE_ICON_GADGET_PORLET_IN_MIDDLE_HOME_PAGE);
		mngTopic.goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat);
 	}
}
