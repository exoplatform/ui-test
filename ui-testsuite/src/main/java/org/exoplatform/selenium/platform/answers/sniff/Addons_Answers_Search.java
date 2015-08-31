package org.exoplatform.selenium.platform.answers.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.AnswerManagement;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement;
import org.exoplatform.selenium.platform.answer.CommentManagement;
import org.exoplatform.selenium.platform.answer.QuestionManagement;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement.actionCategoryOption;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.testng.annotations.*;


public class Addons_Answers_Search  extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	QuestionManagement qMang;
	AnswerCategoryManagement cMang;
	AnswerManagement aMang;
	AnswerHomePage aHome;
	CommentManagement comMang;
	AttachmentFileDatabase fData;
	UserDatabase userData;
	ManageAlert alert;
	Button button;
	String question1;
	String content1;
	String paCat1;
	String paDes1;
	String fullName;
	String paCat2;
	String paDes2;
	String question2;
	String content2;
	String answer1;
	String answer2;
	String comment1;
	String comment2;

	@BeforeClass
	public void setUpBeforeTest() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp = new HomePagePlatform(driver);
		qMang = new QuestionManagement(driver);
		cMang = new AnswerCategoryManagement(driver);
		comMang = new CommentManagement(driver);
		aMang = new AnswerManagement(driver);
		button = new Button(driver);
		aHome=new AnswerHomePage(driver);
		alert = new ManageAlert(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		question1 = txData.getContentByArrayTypeRandom(1)+"q1116806";
		content1 = txData.getContentByArrayTypeRandom(1)+"qc1116806";
		paCat1 = txData.getContentByArrayTypeRandom(1)+"p1116806";
		paDes1 = txData.getContentByArrayTypeRandom(1)+"p1116806";
		question2 = txData.getContentByArrayTypeRandom(1)+"q2116806";
		content2 = txData.getContentByArrayTypeRandom(1)+"qc2116806";
		paCat2 = txData.getContentByArrayTypeRandom(1)+"p2116806";
		paDes2 = txData.getContentByArrayTypeRandom(1)+"p2116806";
		answer1 = txData.getContentByArrayTypeRandom(1)+"a1116806";
		answer2 = txData.getContentByArrayTypeRandom(1)+"a2116806";
		comment1 = txData.getContentByArrayTypeRandom(1)+"c1116806";
		comment2 = txData.getContentByArrayTypeRandom(1)+"c2116806";
		fullName = userData.fullName.get(0);
		createDataTest();
	}

	@AfterClass
	public void afterTest(){
		deleteDataTest();
		magAc.signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	public void createDataTest(){
		info("create data test");
		info("Create category 1");
		hp.goToAnswer();
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));

		info("Create question 1");
		cMang.goToActionOfCategoryFromRightClick(paCat1, actionCategoryOption.SUBMITQUESTION);
		qMang.inputDataToQuestionForm(question1, content1, null, null);
		//click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		clickByJavascript(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
		click(button.ELEMENT_OK_BUTTON_LINK);

		info("Create answer 1");
		aHome.goToHomeCategory();
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer1, null, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer1).replace("$fullname", fullName));

		info("Create comment 1");
		aHome.goToHomeCategory();
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		comMang.goToCommentQuestion(question1);
		comMang.inputDataToComment(comment1);
		//click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		clickByJavascript(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(comMang.ELEMENT_COMMENT_AUTHOR.replace("$comment", comment1).replace("$fullname", fullName));

		info("Create category 2");
		aHome.goToHomeCategory();
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));

		info("Create question 2");
		cMang.goToActionOfCategoryFromRightClick(paCat2, actionCategoryOption.SUBMITQUESTION);
		qMang.inputDataToQuestionForm(question2, content2, null, null);
		//click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		clickByJavascript(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
		click(button.ELEMENT_OK_BUTTON_LINK);

		info("Create answer 2");
		aHome.goToHomeCategory();
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		aMang.goToAnswerQuestion(question2);
		aMang.inputDataToAnswer(answer2, null, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer2).replace("$fullname", fullName));

		info("Create comment 2");
		aHome.goToHomeCategory();
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		comMang.goToCommentQuestion(question2);
		comMang.inputDataToComment(comment2);
		//click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		clickByJavascript(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(comMang.ELEMENT_COMMENT_AUTHOR.replace("$comment", comment2).replace("$fullname", fullName));

	}

	public void deleteDataTest(){
		info("Delete data test");
		info("Create category");
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		cMang.deleteCategory(paCat1);
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		cMang.deleteCategory(paCat2);
	}

	/**
	 * Case ID:116806.
	 * Test Case Name: Quick search.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test01_QuickSearch() {
		info("Test 1: Quick search");
		/*Step Number: 1
		 *Step Name: Quick Search
		 *Step Description: 
			- Enter a search keyword
			- Click quick search icon or press Enter
		 *Input Data: 

		 *Expected Outcome: 
			Return search result with items matched key word search*/ 
		info("Search category");
		aHome.doQuickSearch(paCat1);
		waitForAndGetElement(aHome.ELEMENT_QUICK_SEARCH_RESULT_ITEM.replace("$name", paCat1));
		click(aHome.ELEMENT_QUICK_SEARCH_CLOSE);

		info("Search question");
		aHome.doQuickSearch(question2);
		waitForAndGetElement(aHome.ELEMENT_QUICK_SEARCH_RESULT_ITEM.replace("$name", question2));
		click(aHome.ELEMENT_QUICK_SEARCH_CLOSE);

		info("Search answer");
		aHome.doQuickSearch(answer1);
		waitForAndGetElement(aHome.ELEMENT_QUICK_SEARCH_RESULT_ITEM.replace("$name", question1));
		click(aHome.ELEMENT_QUICK_SEARCH_CLOSE);

		info("Search answer");
		aHome.doQuickSearch(comment2);
		waitForAndGetElement(aHome.ELEMENT_QUICK_SEARCH_RESULT_ITEM.replace("$name", question2));
		click(aHome.ELEMENT_QUICK_SEARCH_CLOSE);

		info("Search content");
		aHome.doQuickSearch(content1);
		waitForAndGetElement(aHome.ELEMENT_QUICK_SEARCH_RESULT_ITEM.replace("$name", question1));
		click(aHome.ELEMENT_QUICK_SEARCH_CLOSE);
	}

	/**
	 * Case ID:116807.
	 * Test Case Name: Advanced search.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test02_AdvancedSearch() {
		info("Test 2: Advanced search");
		/*Step Number: 1
		 *Step Name: Advanced Search
		 *Step Description: 
			- Open Advanced Search form by clicking on icon "Advanced search"
			- Set value to fields
			- Enter text into boxes 
			- Hit "Search" button
		 *Input Data: 

		 *Expected Outcome: 
			Return search result with items matched key word and conditions search*/ 
		aHome.goToAdvanceSearch();
		info("Search category");
		type(aHome.ELEMENT_ADVANCE_SEARCH_KEY_INPUT,paCat1,true);
		click(aHome.ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON);
		waitForAndGetElement(aHome.ELEMENT_ADVANCE_SEARCH_RESULT_ITEM.replace("$name", paCat1));

		info("Search question");
		type(aHome.ELEMENT_ADVANCE_SEARCH_KEY_INPUT,question2,true);
		click(aHome.ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON);
		waitForAndGetElement(aHome.ELEMENT_ADVANCE_SEARCH_RESULT_ITEM.replace("$name", question2));

		info("Search answer");
		type(aHome.ELEMENT_ADVANCE_SEARCH_KEY_INPUT,answer1,true);
		click(aHome.ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON);
		waitForAndGetElement(aHome.ELEMENT_ADVANCE_SEARCH_RESULT_ITEM.replace("$name", question1));

		info("Search comment");
		type(aHome.ELEMENT_ADVANCE_SEARCH_KEY_INPUT,comment2,true);
		click(aHome.ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON);
		waitForAndGetElement(aHome.ELEMENT_ADVANCE_SEARCH_RESULT_ITEM.replace("$name", question2));

		info("Search content");
		type(aHome.ELEMENT_ADVANCE_SEARCH_KEY_INPUT,content1,true);
		click(aHome.ELEMENT_ADVANCE_SEARCH_ADVANCE_SEARCH_BUTTON);
		waitForAndGetElement(aHome.ELEMENT_ADVANCE_SEARCH_RESULT_ITEM.replace("$name", question1));
		click(aHome.ELEMENT_ADVANCE_SEARCH_ADVANCE_CLOSE_BUTTON);
	}
}