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
import org.exoplatform.selenium.platform.answer.AnswerManagement.actionAnswerOption;
import org.exoplatform.selenium.platform.answer.CommentManagement.actionCommentOption;
import org.exoplatform.selenium.platform.gatein.AnswerPage;
import org.exoplatform.selenium.platform.gatein.ApplicationRegistry;
import org.exoplatform.selenium.platform.gatein.PageEditor;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class Addons_Answers_Answers  extends PlatformBase {
	AnswerPage aPage;
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	QuestionManagement qMang;
	AnswerCategoryManagement cMang;
	AnswerManagement aMang;
	AnswerHomePage aHome;
	CommentManagement comMang;
	AttachmentFileDatabase fData;
	ApplicationRegistry cPage;
	UserDatabase userData;
	ManageAlert alert;
	PageEditor pagEditor;
	Button button;
	String question1;
	String content1;
	String fullName;
	String question2;
	String content2;

	@BeforeClass
	public void setUpBeforeTest() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		pagEditor = new PageEditor(driver);
		qMang = new QuestionManagement(driver);
		cMang = new AnswerCategoryManagement(driver);
		comMang = new CommentManagement(driver);
		aMang = new AnswerManagement(driver);
		aPage = new AnswerPage(driver);
		button = new Button(driver);
		aHome=new AnswerHomePage(driver);
		alert = new ManageAlert(driver);
		cPage = new ApplicationRegistry(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		magAc.signIn(DATA_USER1, DATA_PASS);
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		question1 = txData.getContentByArrayTypeRandom(1)+"1116814";
		content1 = txData.getContentByArrayTypeRandom(1)+"1116814";
		question2 = txData.getContentByArrayTypeRandom(1)+"2116814";
		content2 = txData.getContentByArrayTypeRandom(1)+"2116814";
		fullName = userData.fullName.get(0);
		createDataTest();
	}

	@AfterClass
	public void afterTest(){
		magAc.signIn(DATA_USER1, DATA_PASS);
		deleteDataTest();
		magAc.signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	@AfterMethod
	public void afterMethod(){
		magAc.signOut();
	}
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		magAc.signIn(DATA_USER1, DATA_PASS);
	}

	public void createDataTest(){
		info("create data test");
		if(waitForAndGetElement(hp.ELEMENT_ANSWER_LINK_PLF, 5000,0)==null){
			aPage.createAnswerPage();
			aPage.goToEditAnswerPortlet();
			aPage.doPublicMode(true);
			click(pagEditor.ELEMENT_PAGE_EDITOR_SAVE_AND_CLOSE_BUTTON);
			pagEditor.finishEditLayout();
		}
		hp.goToAnswer();
		info("Create question");
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(question1, content1, null, null);
		//click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		clickByJavascript(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
		click(button.ELEMENT_OK_BUTTON_LINK);
		
		info("Create question");
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(question2, content2, null, null);
		//click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		clickByJavascript(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON, 2);
		click(button.ELEMENT_OK_BUTTON_LINK);
	}

	public void deleteDataTest(){
		info("Delete data test");
		info("Create category");
		hp.goToAnswer();
		qMang.deleteQuestion(question1);
		qMang.deleteQuestion(question2);
	}
	/**
	 * Case ID:116814.
	 * Test Case Name: Rate/Sort answers.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test01_RateSortAnswers() {
		String answer1 = txData.getContentByArrayTypeRandom(1)+"a1116814";
		String answer2 = txData.getContentByArrayTypeRandom(1)+"a2116814";
		String answer3 = txData.getContentByArrayTypeRandom(1)+"a3116814";

		info("Create answer");
		hp.goToAnswer();
		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer1, null, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer1).replace("$fullname", fullName));

		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer2, null, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer2).replace("$fullname", fullName));

		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer3, null, null, null);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer3).replace("$fullname", fullName));

		info("Test 1: Rate/Sort answers");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Rate and Sort Answer
		 *Input Data: 
			- Open a question having answers
			- Rate answers by click on hand up or down icon
			- Sort answers by click sort icon
		 *Expected Outcome: 
			- Answer is rated with index 
			- Answer is sorted by ascending/descending*/ 
		info("Rate answer");
		aMang.rateAnswer(answer3, true);
		aMang.rateAnswer(answer2, false);

		info("Sort by rate");
		click(aMang.ELEMENT_SORT_BY_RATE);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_POSITION_IN_LIST.replace("${answer}", answer3).replace("${no}", "1"));
		waitForAndGetElement(aMang.ELEMENT_ANSWER_POSITION_IN_LIST.replace("${answer}", answer1).replace("${no}", "2"));
		waitForAndGetElement(aMang.ELEMENT_ANSWER_POSITION_IN_LIST.replace("${answer}", answer2).replace("${no}", "3"));

		info("Clear data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		aMang.deleteAnswer(answer1);
		aMang.deleteAnswer(answer2);
		aMang.deleteAnswer(answer3);
	}

	/**
	 * Case ID:116815.
	 * Test Case Name: Activate/Deactivate answer.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test02_ActivateDeactivateAnswer() {
		String answer = txData.getContentByArrayTypeRandom(1)+"a116815";
		info("Test 2: Activate/Deactivate answer");
		/*Step Number: 1
		 *Step Name: Prepare data: Create a question, answer
		 *Step Description: 
			- Login as admin
			- Go to Answers
			- Create a question
			- Answer this question
		 *Input Data: 

		 *Expected Outcome: 
			- Question is created successfully
			- Answer successfully*/
		hp.goToAnswer();
		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer, null, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		/*Step number: 2
		 *Step Name: Deactivate answer
		 *Step Description: 
			-Click More Actions 
			-
			-> Deactivate
		 *Input Data: 

		 *Expected Outcome: 
			Answer will be disappeared with normal users*/
		aMang.goToActionOfAnswerFromMoreAction(answer,actionAnswerOption.EDIT);
		aMang.inputDataToAnswer(null, null, false, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		waitForElementNotPresent(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		/*Step number: 3
		 *Step Name: Activate answer
		 *Step Description: 
			- Click More Actions 
			-
			-> Activate
		 *Input Data: 

		 *Expected Outcome: 
			Answer will be appeared with normal users*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		aMang.goToActionOfAnswerFromMoreAction(answer,actionAnswerOption.EDIT);
		aMang.inputDataToAnswer(null, null, true, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		info("Clear data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		aMang.deleteAnswer(answer);
	}

	/**
	 * Case ID:116816.
	 * Test Case Name: Approve/ Disapprove answer.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test03_ApproveDisapproveAnswer() {
		String answer = txData.getContentByArrayTypeRandom(1)+"a116816";
		info("Test 3: Approve/ Disapprove answer");
		/*Step Number: 1
		 *Step Name: Prepare data: Create a question, answer
		 *Step Description: 
			- Login as john
			- Go to Answers
			- Create a category, with moderate Answer
			- Create a question in this category
			- Login as mary
			- Answer this question
		 *Input Data: 

		 *Expected Outcome: 
			- Category is created successfully.
			- Question is created successfully
			- Answer successfully, but this answer is at Disapprove status and invisible with normal users*/
		hp.goToAnswer();
		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer, false, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);

		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		waitForElementNotPresent(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		/*Step number: 2
		 *Step Name: Approve answer
		 *Step Description: 
			- Login as john
			- Click on icon Approve corresponding to an answer
			- Login as mary
		 *Input Data: 

		 *Expected Outcome: 
			- Answer is at Approved status, and visible with normal users
			- See this answer*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		aMang.goToActionOfAnswerFromMoreAction(answer,actionAnswerOption.EDIT);
		aMang.inputDataToAnswer(null, true, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		/*Step number: 3
		 *Step Name: Disapprove answer
		 *Step Description: 
			- Login as john
			- Click on icon Disapprove corresponding to an answer
			- Login as mary
		 *Input Data: 

		 *Expected Outcome: 
			Answer will be invisible with normal users
			- Cannot see this answer*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		aMang.goToActionOfAnswerFromMoreAction(answer,actionAnswerOption.EDIT);
		aMang.inputDataToAnswer(null, false, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		waitForElementNotPresent(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));


		info("Clear data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		aMang.deleteAnswer(answer);
	}

	/**
	 * Case ID:116817.
	 * Test Case Name: Answer a question.
	 * Case ID:116818.
	 * Test Case Name: Edit an answer.
	 * Case ID:116819.
	 * Test Case Name: Delete an answer.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test04_05_06_AddEditDeleteAnswerAQuestion() {
		String answer = txData.getContentByArrayTypeRandom(1)+"a116817";
		String newanswer = txData.getContentByArrayTypeRandom(1)+"n116817";

		info("Test 4: Answer a question");
		/*Step Number: 1
		 *Step Name: Prepare data: Create a category, a question,
		 *Step Description: 
			- Login as john
			- Go to Answers
			- Create a category
			- Create a question in this category
		 *Input Data: 

		 *Expected Outcome: 
			- Category is created successfully.
			- Question is created successfully*/

		/*Step number: 2
		 *Step Name: Answer a question
		 *Step Description: 
			- Open that question
			-Click on Answer,
			- Put data into editor
			- Set other fields
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Answer a question successfully,
			- Show the answer below this question*/ 
		hp.goToAnswer();
		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer, null, null, null);
		///click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		info("Test 5: Edit an answer");
		/*Step number: 3
		 *Step Name: Edit an answer
		 *Step Description: 
			- Open that answer, click More Actions 
			-
			-> Edit Answer
			- Input data into editor
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- An "Edit answer" popup is shown.
			- New content of answer is saved*/
		aMang.goToActionOfAnswerFromMoreAction(answer,actionAnswerOption.EDIT);
		aMang.inputDataToAnswer(newanswer, null, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", newanswer).replace("$fullname", fullName));
		waitForElementNotPresent(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		info("Test 6: Delete an answer");
		/*Step number: 3
		 *Step Name: Delete an answer
		 *Step Description: 
			- Open that answer
			- Click More Actions 
			-
			-> Delete Answer
		 *Input Data: 

		 *Expected Outcome: 
			The answer is deleted successfully*/ 
		aMang.deleteAnswer(newanswer);
	}

	/**
	 * Case ID:116820.
	 * Test Case Name: Add a comment.
	 * Case ID:116821.
	 * Test Case Name: Edit a comment.
	 * Case ID:116822.
	 * Test Case Name: Delete a comment.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test07_08_09_AddEditDeleteAComment() {
		String answer = txData.getContentByArrayTypeRandom(1)+"a116820";
		String comment = txData.getContentByArrayTypeRandom(1)+"c116820";
		String newcomment = txData.getContentByArrayTypeRandom(1)+"nc116820";
		info("Test 7: Add a comment");
		/*Step Number: 1
		 *Step Name: Prepare data: Create a question, answer
		 *Step Description: 
			- Go to Answers
			- Create a question
			- Answer this question
		 *Input Data: 

		 *Expected Outcome: 
			- Question is created successfully
			- Answer successfully*/
		hp.goToAnswer();
		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer, null, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		/*Step number: 2
		 *Step Name: Comment on a question
		 *Step Description: 
			- Open that question
			- Click on Comment link
			- Put values
			- Click on save
		 *Input Data: 

		 *Expected Outcome: 
			- Comment successfully*/ 
		comMang.goToCommentQuestion(question1);
		comMang.inputDataToComment(comment);
		//click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		clickByJavascript(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(comMang.ELEMENT_COMMENT_AUTHOR.replace("$comment", comment).replace("$fullname", fullName));

		info("Test 8: Edit a comment");
		/*Step number: 3
		 *Step Name: Edit a comment
		 *Step Description: 
			- Open a comment
			- Click More Actions 
			-
			-> Edit Comment
			- Input data into fields
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Edit comment successfully
			- New content of comment is saved*/ 
		comMang.goToActionOfCommentFromMoreAction(comment,actionCommentOption.EDIT);
		comMang.inputDataToComment(newcomment);
		//click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		clickByJavascript(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(comMang.ELEMENT_COMMENT_AUTHOR.replace("$comment", newcomment).replace("$fullname", fullName));
		waitForElementNotPresent(comMang.ELEMENT_COMMENT_AUTHOR.replace("$comment", comment).replace("$fullname", fullName));

		info("Test 9: Delete a comment");
		/*Step number: 3
		 *Step Name: Delete a comment
		 *Step Description: 
			- Open a comment
			- Click More Actions 
			-
			-> Delete Comment
			- Click OK in the confirmation message
		 *Input Data: 

		 *Expected Outcome: 
			- Delete a comment successfully*/ 
		comMang.deleteComment(newcomment);
	}

	/**
	 * Case ID:116823.
	 * Test Case Name: Promote a comment.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test10_PromoteAComment() {
		String answer = txData.getContentByArrayTypeRandom(1)+"a116823";
		String comment = txData.getContentByArrayTypeRandom(1)+"c116823";
		info("Test 10 Promote a comment");
		/*Step Number: 1
		 *Step Name: Prepare data: Create a question, answer
		 *Step Description: 
			- Go to Answers
			- Create a question
			- Answer this question
		 *Input Data: 

		 *Expected Outcome: 
			- Question is created successfully
			- Answer successfully*/
		hp.goToAnswer();
		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer, null, null, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		/*Step number: 2
		 *Step Name: Comment on a question
		 *Step Description: 
			- Open that question
			- Click on Comment link
			- Put values
			- Click on save
		 *Input Data: 

		 *Expected Outcome: 
			- Comment successfully*/
		comMang.goToCommentQuestion(question1);
		comMang.inputDataToComment(comment);
		//click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		clickByJavascript(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON, 2);
		waitForAndGetElement(comMang.ELEMENT_COMMENT_AUTHOR.replace("$comment", comment).replace("$fullname", fullName));

		/*Step number: 3
		 *Step Name: Promote a comment
		 *Step Description: 
			- Open a comment
			- Click More Actions 
			-
			-> Promote as Answer
		 *Input Data: 

		 *Expected Outcome: 
			This comment becomes an answer*/ 
		comMang.goToActionOfCommentFromMoreAction(comment,actionCommentOption.PROMOTE);

		info("Clear data");
		aMang.deleteAnswer(answer);
		aMang.deleteAnswer(comment);
	}

	/**
	 * Case ID:116832.
	 * Test Case Name: Answer a question in Manage Question form.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test11_AnswerAQuestionInManageQuestionForm() {
		String answer = txData.getContentByArrayTypeRandom(1)+"a116832";
		info("Test 11 Answer a question in Manage Question form");
		/*Step Number: 1
		 *Step Name: Add a question
		 *Step Description: 
			- Select 1 category and click on Submit question
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Question is added new and shown in selected category*/

		/*Step number: 2
		 *Step Name: -Answer a question
		 *Step Description: 
			- Click Manage Question in the main menu
			-Select Open Question tab
			- Click Answer on question want to answer
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Answer is added to selected question
			- Question is not show at tab "Open Question"*/ 
		hp.goToAnswer();
		qMang.goToManageQuestionForm();
		click(qMang.ELEMENT_MANAGE_QUESTION_FORM_OPEN_QUESTION_TAB);
		click(qMang.ELEMENT_MANAGE_QUESTION_ANSWER_QUESTION.replace("$question", question2));
		aMang.inputDataToAnswer(answer, true, true, null);
		//click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		clickByJavascript(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON, 2);
		waitForElementNotPresent(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		waitForAndGetElement(qMang.ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question2)));
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		info("Clear data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		click(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question2)));
		aMang.deleteAnswer(answer);
	}
}