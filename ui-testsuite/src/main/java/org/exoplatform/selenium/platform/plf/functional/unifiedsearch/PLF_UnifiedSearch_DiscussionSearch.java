package org.exoplatform.selenium.platform.plf.functional.unifiedsearch;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationManagement;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.SearchAdministration;
import org.exoplatform.selenium.platform.SettingSearchPage;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.forum.ForumPermission;

/**
 * 
 * @date 28/08/2014
 * 
 */

public class PLF_UnifiedSearch_DiscussionSearch extends PlatformBase {

	// General
	Button button;
	ManageAlert mgAlert;

	// Platform
	NavigationToolbar naviToolbar;
	NavigationManagement navMag;
	ManageAccount magAcc;

	SearchAdministration searchAdmin;
	SettingSearchPage qsPage;

	ForumManageForum mngFru;
	ForumManageTopic mngTopic;
	ForumManagePost mngPost;
	ForumManageCategory mngCat;

	ForumPermission frumPer;
	
	String searchText = "topic";
	String category1 = "";
	String forum1 = "";
	String topic1 = "";
	String topic2 = "";
	String text_Search = "Discussions";
	boolean isPermission = false;

	/**
	 * this void will call the browser and set up all classes before starting
	 * all test cases
	 * 
	 * @author quynhpt
	 * @throws Exception
	 */

	@BeforeMethod
	public void setBeforeTest() throws Exception {
		initSeleniumTest();
		driver.get(plfURL);
		info("Login with " + DATA_USER1);
		frumPer = new ForumPermission(driver,this.plfVersion);
		magAcc = new ManageAccount(driver, this.plfVersion);
		naviToolbar = new NavigationToolbar(driver, this.plfVersion);
		button = new Button(driver, this.plfVersion);
		mngFru = new ForumManageForum(driver, this.plfVersion);
		mngTopic = new ForumManageTopic(driver, this.plfVersion);
		mngPost = new ForumManagePost(driver, this.plfVersion);
		mngCat = new ForumManageCategory(driver, this.plfVersion);
		navMag = new NavigationManagement(driver, this.plfVersion);
		qsPage = new SettingSearchPage(driver);
		mgAlert = new ManageAlert(driver);
		magAcc.signIn(DATA_USER1, DATA_PASS);

	}

	/**
	 * this void will close the browser after finished all test cases
	 * 
	 * @throws Exception
	 */

	@AfterMethod
	public void setAfterTest() throws Exception {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * TC_104211:Display a Discussion in Floating Result
	 * Precondition: a Discussion exist in a forum
	 * Steps:
	 * 1. Login with root account
	 * 2. Go to the home page by the
	 * link:http://localhost:8080/portal/intranet/home
	 * 3. Create a category,forum and topic without permission
	 * 4. In the Quick Search box, input a valid characters (Ex. forum) to
	 * search a Discussion
	 * 5. Observer the results
	 * Expected: The Discussion "forum" is displayed in the Floating Result:
	 * 1.The post title
	 * 2. The forum name
	 * 3. The topic icon such as displayed in the forum application
	 * 
	 * @throws Exception
	 */

	@Test
	public void test01_DisplayDisInFloatingResult() {
		isPermission = false;
		// Create data test
		AddCategoryForumTopicByViewedAnyOne();

		// - Type some text into search box
		qsPage.quickSearchType(searchText);

		// - Verify results should display: the topic icon such as displayed in
		// the forum application, the post title, forum name
		info("-- Verify the topic icon --");
		waitForAndGetElement(ELEMENT_RESULT_FLOATING_RESULTS_DISCUSSION_ICON);

		info("-- Verify the post title --");
		assert waitForAndGetElement(
				qsPage.ELEMENT_RESULT_FLOATING_RESULTS_NAME.replace(
						"${type_Search}", text_Search).replace(
						"${detail_Name}", topic1)).getText().contains(topic1);

		info("-- Verify the forum name --");
		assert waitForAndGetElement(
				qsPage.ELEMENT_RESULT_FLOATING_RESULTS_NAME.replace(
						"${type_Search}", text_Search).replace(
						"${detail_Name}", forum1)).getText().contains(forum1);
		DeleteData();
	}

	/**
	 * TC_104222:Display a Discussion in the Search Result page
	 * Preconditions: a Discussion exist in a forum
	 * Steps:
	 * 1. Log in with root account
	 * 2. Go to the link://localhost:8080/portal/intranet/home
	 * 3. Create a category,forum and a topic without permission
	 * 4. In the Quick Search box, input a valid characters (Ex. forum) to
	 * search a Discussion
	 * 5. Click on "See All Search Results"
	 * 6. Uncheck "All content types" checkbox
	 * 7. Check "Discussion" checkbox
	 * 8. Observer search results
	 * Expected: The Discussion is displayed in the list of Results:1.The topic
	 * icon such as displayed in the forum application 2.The post title 3.An
	 * excerpt 4.The Forum name 5.The post date 6.The rating 7.The number of
	 * replies in the topic
	 * related issue: FORUM-1146
	 */

	@Test (groups="pending")
	public void test02_DisplayDisInSearPageResult() throws Exception {
		isPermission = false;
		// Create data test
		AddCategoryForumTopicByViewedAnyOne();

		// - Type some text into search box, Click on Search
		qsPage.quickSearchType(topic1);

		// - Click on "See all search results" link
		click(ELEMENT_SEE_ALL_SEARCH_RESULTS);
		// - Uncheck "All content types" checkbox
		uncheck(qsPage.ELEMENT_FILTER_SEARCH_ALL_CONTENTTYE_CHECKBOX, 2);
		// - Check "Discussion" checkbox
		check(qsPage.ELEMENT_FILTER_SEARCH_DISCUSSION_CHECKBOX, 2);

		// - Verify results should display: the topic icon such as displayed in
		// the forum application, the post title, an excerpt, forum name, the
		// post date, the rating, the number of replies in the topic
		info("-- Verify the topic icon --");
		waitForAndGetElement(qsPage.ELEMENT_RESULT_FORUM_ICON);

		info("-- Verify the post title --");
		assert waitForAndGetElement(
				qsPage.ELEMENT_SEARCH_RESULT_TITLE.replace("${tileName}",
						topic1)).getText().contains(topic1);

		info("-- Verify the forum name on excerpt --");
		assert waitForAndGetElement(
				qsPage.ELEMENT_SEARCH_RESULT_EXCERPT.replace("${keySearch}",
						forum1)).getText().contains(forum1);

		info("-- Verify the post date --");
		assert waitForAndGetElement(
				qsPage.ELEMENT_SEARCH_RESULT_CONTENT_DETAIL.replace(
						"${keySearch1}", forum1).replace("${keySearch2}",
						getCurrentDate("EEEEE, MMMMM d, yyyy"))).getText()
				.contains(getCurrentDate("EEEEE, MMMMM d, yyyy"));

		info("-- Verify the rating --");
		waitForAndGetElement(qsPage.ELEMENT_SEARCH_RESULT_FORUM_VOTE.replace(
				"${keySearch}", topic1));

		info("-- Verify the the number of replies in the topic --");
		assert waitForAndGetElement(
				qsPage.ELEMENT_SEARCH_RESULT_CONTENT_DETAIL.replace(
						"${keySearch1}", forum1).replace("${keySearch2}",
						getCurrentDate("EEEEE, MMMMM d, yyyy"))).getText()
				.contains("0 replies");

		// - Items in search result is clickable and open it when user click
		waitForAndGetElement(
				qsPage.ELEMENT_SEARCH_RESULT_TITLE.replace("${tileName}",
						topic1)).click();
		DeleteData();
	}

	/**
	 * TC_104224:Display a Discussion in Floating Result by Pertinence
	 * Preconditions: 2 Discussions containing the word: "forum"
	 * - Post 1: (forum) in Title
	 * - Post 2: (forum) in post body
	 * Steps:
	 * 1.Login with root account
	 * 2. Go to the home page by the
	 * link:http://localhost:8080/portal/intranet/home
	 * 3. Create a category,forum and topic without permission
	 * 4. In the Quick Search box, input a valid characters (Ex. forum) to
	 * search a Discussion
	 * 5. Delete the category
	 * Expected: The discussions are displayed in the Floating Result in the
	 * following order: Post 1, Post 2
	 */

	@Test
	public void test03_DisplayDisInFloatResultByPertinence() throws Exception {
		isPermission = false;
		// Create preconditions
		createNameCategoryForumTopic();

		// Create data
		// Forums, topics, posts are existed on Forum application.
		info("Add a post");
		mngFru.goToForums();
		mngTopic.addCategoryForumWithTwoTopics(category1, forum1, topic1,
				topic2, topic2, topic1);

		// Type some text into search box, Click on Search
		qsPage.quickSearchType(searchText);

		info("-- Verify the post title of topic 1 --");
		assert waitForAndGetElement(
				qsPage.ELEMENT_RESULT_FLOATING_RESULTS_NAME.replace(
						"${type_Search}", text_Search).replace(
						"${detail_Name}", topic1)).getText().contains(topic1);

		info("-- Verify the post title of topic 2 --");
		assert waitForAndGetElement(
				qsPage.ELEMENT_RESULT_FLOATING_RESULTS_NAME.replace(
						"${type_Search}", text_Search).replace(
						"${detail_Name}", topic2)).getText().contains(topic2);

		DeleteData();

	}

	/**
	 * TC_104226: Open a Post from the Search Result page
	 * Preconditions: a Post exist in Forum
	 * Steps:
	 * 1. Log in with root account
	 * 2. Go to the home page by the
	 * link:http://localhost:8080/portal/intranet/home
	 * 3. Create a category, forum and topic without the permission
	 * 4. In the Quick Search box, input a valid characters (Ex. forum) to
	 * search a Discussion
	 * 5. Click on the Post's name
	 * Expected: The post is opened in the forum app
	 */

	@Test
	public void test04_OpenPostFromSearResult() throws Exception {
		isPermission = false;
		// Create data test
		AddCategoryForumTopicByViewedAnyOne();
		// - Type some text into search box, Click on Search
		qsPage.quickSearchType(searchText);
		// - Click on "See all search results" link

		click(qsPage.ELEMENT_RESULT_FLOATING_RESULTS_NAME.replace(
				"${type_Search}", text_Search)
				.replace("${detail_Name}", topic1));

		info("-- Verify the forum name on excerpt --");
		assert waitForAndGetElement(
				qsPage.ELEMENT_TOPIC_POST_TITLE.replace("${titleTopic}", topic1))
				.getText().contains(topic1);
		DeleteData();
	}

	/**
	 * TC_104223: Not Display a Discussion for forum where user is not allowed
	 * to view
	 * Preconditions: a Discussion exist in a forum Discussion containing search
	 * characters is available on a forum where user is not allowed to view
	 * - Select this forum > select topic > More actions > Edit 
	 * - Permission tab > Add the Administration group for all right 
	 * - Check this case with demo user
	 * Steps:
	 * 1. Login with John user (root account)
	 * 2. Go to the home page by the
	 * link:http://localhost:8080/portal/intranet/home
	 * 3. Create a category only for manager group that is allowed to view
	 * 4. Log out and log in with Demo user (normal account)
	 * 5. In the Quick Search box, input a valid characters (Ex. forum) to
	 * search a Discussion
	 * Expected: The Discussion "Test" is not displayed in the Floating Result
	 * this issue releated to FORUM-978 --> This issue was resolved because steps and expected
	 * result in this issue is normal behavior as Tu's comment in this issue.
	 * This case was passed
	 */

	@Test
	public void test05_NoDisplayForumForUserNotAllowView() {
		String category1 = "cat1042231";
		String forum1 = "for1042231";
		String topic1 = "top1042231";
		String[] permisionName1 = {DATA_USER1};
		
		// Create data test
		mngFru.goToForums();
		mngCat.goToAddCategory();
		mngCat.inputTitleOrderDescriptionCategory(category1, "1", category1);
		mngCat.selectRestricted(1, permisionName1);
		button.save();
		mngFru.quickAddForum(forum1);
		click(mngTopic.ELEMENT_START_TOPIC_BUTTON);
		mngTopic.startTopic(topic1, topic1, "", 0, permisionName1, false, false, false);
		
		// -logout
		magAcc.signOut();
		// -sign in with normal user: here is "demo" user
		magAcc.signIn(DATA_USER4, DATA_PASS);
		isPermission = true;
		
		// - Type some text into search box, Click on Search
		qsPage.quickSearchType(forum1);

		info("-- Verify not display forum1 --");
		waitForElementNotPresent(qsPage.ELEMENT_RESULT_FLOATING_RESULTS_NAME.replace("${type_Search}", text_Search).replace("${detail_Name}", forum1));
		waitForElementNotPresent(By.linkText(forum1));

		info("-- Verify not display forum1 --");
		waitForElementNotPresent("//strong[text()='" + forum1 + "']");
		waitForElementNotPresent(By.linkText(forum1));
		magAcc.signOut();
		// -sign in with admin user: here is "john" user
		magAcc.signIn(DATA_USER1, DATA_PASS);
		mngFru.goToForums();
		click(By.linkText(category1));
		mngCat.deleteCategoryInForum(category1, true);
	}

	/**
	 * TC_104225: Not Display a Discussion for forum where user without read
	 * permission on post
	 * Preconditions: a Discussion exist in a forum
	 * - current user has not read permission on posts to see them as search
	 * results
	 * Steps:
	 * 1. Login with John user (root account)
	 * 2. Go to the home page by the
	 * link:http://localhost:8080/portal/intranet/home
	 * 3. Create a category only for manager group that is allowed to view
	 * 4. Log out and log in with Demo user (normal account)
	 * 5. In the Quick Search box, input a valid characters (Ex. forum) to
	 * search a Discussion
	 * Expected: The Discussion "Test" is not displayed in the Floating Result
	 * 
	 * this issue releated to FORUM-978
	 */

	@Test
	public void test06_NoDisplayTopicForUserNotAllowRead() {
		String category1 = "cat104225";
		String forum1 = "for104225";
		String topic1 = "top104225";
		String[] permisionName1 = {DATA_USER1};
		
		// Create data test
		mngFru.goToForums();
		mngCat.goToAddCategory();
		mngCat.inputTitleOrderDescriptionCategory(category1, "1", category1);
		frumPer.configPermission4ForumCategory(2, permisionName1, true, true, true, true);
		button.save();
		mngFru.quickAddForum(forum1);
		click(mngTopic.ELEMENT_START_TOPIC_BUTTON);
		mngTopic.startTopic(topic1, topic1, "", 0, permisionName1, false, false, false);
		
		// -logout
		magAcc.signOut();
		// -sign in with normal user: here is "demo" user
		magAcc.signIn(DATA_USER4, DATA_PASS);
		isPermission = true;
		
		// - Type some text into search box, Click on Search
		qsPage.quickSearchType(topic1);

		info("-- Verify not display topic --");
		click(qsPage.ELEMENT_RESULT_FLOATING_RESULTS_NAME.replace("${type_Search}", text_Search).replace("${detail_Name}", topic1));
		mgAlert.verifyAlertMessage("You are not allowed to view this topic");
		button.ok();
		
		magAcc.signOut();
		// -sign in with admin user: here is "john" user
		magAcc.signIn(DATA_USER1, DATA_PASS);
		mngFru.goToForums();
		click(By.linkText(category1));
		mngCat.deleteCategoryInForum(category1, true);
	}

	/**
	 * Create a category, a forum and a topic that is viewed anyone in the
	 * system.
	 * 
	 * 
	 */
	private void AddCategoryForumTopicByViewedAnyOne() {
		// TODO Auto-generated method stub
		/* Declare variables */
		createNameCategoryForumTopic();

		// Create data
		// Forums, topics, posts are existed on Forum application.
		info("Add a post");
		mngFru.goToForums();
		mngTopic.addCategoryForumTopic(category1, forum1, topic1, topic1);

	}

	/**
	 * Create names for category, forum and topic
	 * 
	 * @param category1
	 *            the name of category
	 * @param forum1
	 *            the name of forum
	 * @param topic1
	 *            the name of topic1
	 * @param topic2
	 *            the name of topic 2
	 */
	private void createNameCategoryForumTopic() {
		String prefix_number = getRandomNumber();
		category1 = "category" + prefix_number;
		forum1 = "forum" + prefix_number;
		topic1 = "topic on forum" + prefix_number;
		topic2 = "topic" + prefix_number;
	}

	/**
	 * Delete the category and all forums and topic of this categroy
	 * 
	 * @param category1
	 *            the name of category
	 * @param forum1
	 *            the name of forum
	 * 
	 */
	private void DeleteData(){
		// TODO Auto-generated method stub
		/* Clear data */
		info("-- Clear data --");
		if (isPermission == true) {
			info("-- logout normal user and login admin account --");
			magAcc.signOut();
			// -sign in with admin user: here is "john" user
			magAcc.signIn(DATA_USER1, DATA_PASS);

		}
		mngFru.goToForums();
		System.out.print("category1:" + category1);
		click(By.linkText(category1));
		mngCat.deleteCategoryInForum(category1, true);

	}

}
