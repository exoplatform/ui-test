package org.exoplatform.selenium.platform.forum.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePoll;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Thuntn
 * @date 05 Sep 2013
 */
public class Forum_Forum_PublishActivity_PollActivity extends ForumBase{

	ManageAccount magAc;
	ForumManageCategory mngCat;
	ForumManageForum mngFru;
	ForumManageTopic mngTopic;
	ForumManagePost mngPost;
	HomePageActivity hpgAct;
	ForumManagePoll mngPoll;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		mngFru = new ForumManageForum(driver);
		mngCat = new ForumManageCategory(driver);
		mngPost = new ForumManagePost(driver);
		mngTopic = new ForumManageTopic(driver);
		hpgAct = new HomePageActivity(driver);
		mngPoll = new ForumManagePoll(driver);
		navTool = new NavigationToolbar(driver);

		magAc.signIn(DATA_USER_JOHN, DATA_PASS);
		goToForums();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * CaseID: 75281
	 * Edit a poll
	 */
	@Test
	public void test01_EditPoll(){
		String titleCat = "Category75281";
		String titleForum = "Forum75281";
		String titleTop = "Topic75281";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";
		String pollQuestion = "Poll75281";
		String[] pollOptions = {"Option 01", "Option 02"};
		String[] newOptions = {"new Option 01", "new Option 02"};

		info("Edit a poll");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));

		//Add a poll
		mngPoll.addPoll(pollQuestion, pollOptions, "2", true, true);

		//Edit a poll
		mngPoll.editPoll(pollQuestion, newOptions, "3", false);

		//Check activity
		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop));
		}
		hpgAct.checkEditPoll(pollQuestion);

		//Delete data
		goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);	
	}

	/**
	 * CaseID: 75283
	 * Add a new poll
	 */
	@Test
	public void test02_AddPoll(){
		String titleCat = "Category75283";
		String titleForum = "Forum75283";
		String titleTop = "Topic75283";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";
		String pollQuestion = "Poll75283";
		String[] pollOptions = {"Option 01", "Option 02"};
		String[] rate = {"0","0"};

		info("Add a new poll");
		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));

		//Add a poll
		mngPoll.addPoll(pollQuestion, pollOptions, "2", true, true);

		//Check activity
		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop));
		}
		hpgAct.checkAddPoll(titleTop,pollQuestion,pollOptions,rate);

		//Delete data
		goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);	
	}

	/**
	 * CaseID: 75284
	 * Delete a poll
	 */
	@Test
	public void test03_DeletePoll(){
		String titleCat = "Category75284";
		String titleForum = "Forum75284";
		String titleTop = "Topic75284";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";
		String pollQuestion = "Poll75284";
		String[] pollOptions = {"Option 01", "Option 02"};
		String[] rate = {"0","0"};

		info("Delete a poll");

		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));

		//Add a poll
		mngPoll.addPoll(pollQuestion, pollOptions, "2", true, true);

		//Check activity
		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop));
		}
		hpgAct.checkAddPoll(titleTop,pollQuestion,pollOptions,rate);

		//Delete this poll
		goToForums();
		mngPoll.deletePollInTopic(pollQuestion);

		//Check activity after deleting the poll
		navTool.goToHomePage();
		assert (waitForAndGetElement(By.linkText(pollQuestion),5000,0) == null);

		//Delete data
		goToForums();
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);		
	}

	/**
	 * CaseID: 75285
	 * Redirect to the poll by clicking on "Vote"
	 */
	@Test
	public void test04_RedirectToPollByClickingVote(){
		String titleCat = "Category75285";
		String titleForum = "Forum75285";
		String titleTop = "Topic75285";
		String descTop = "line1<br>line2<br>line3<br>line4<br>line5";
		String pollQuestion = "Poll75285";
		String[] pollOptions = {"Option 01", "Option 02"};

		info("Redirect to the poll by clicking on Vote");

		//create category, forum, topic
		mngTopic.addCategoryForumTopic(titleCat, titleForum, titleTop,descTop);
		click(mngFru.ELEMENT_TOPIC_LINK.replace("${topic}", titleTop));
		waitForAndGetElement(mngPost.ELEMENT_POST_REPLY_BUTTON);

		//Add a poll
		mngPoll.addPoll(pollQuestion, pollOptions, "2", true, true);

		//Check activity
		navTool.goToHomePage();
		if(waitForAndGetElement(By.linkText(titleTop),DEFAULT_TIMEOUT,0) == null){
			driver.navigate().refresh();
			waitForAndGetElement(By.linkText(titleTop));
		}

		hpgAct.openVoteFromActivity(pollQuestion);

		//Delete data
		click(By.linkText(titleCat));
		mngCat.deleteCategoryInForum(titleCat, true);	
	}

}