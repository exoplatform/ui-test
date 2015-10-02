package org.exoplatform.selenium.platform.answers.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.AnswerManagement;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement;
import org.exoplatform.selenium.platform.answer.CommentManagement;
import org.exoplatform.selenium.platform.answer.QuestionManagement;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement.actionCategoryOption;
import org.exoplatform.selenium.platform.answer.QuestionManagement.actionQuestionOption;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class Addons_Answers_PublishActivity   extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	QuestionManagement qMang;
	AnswerCategoryManagement cMang;
	AnswerManagement aMang;
	AnswerHomePage aHome;
	CommentManagement comMang;
	ActivityStream hpAct;
	AttachmentFileDatabase fData;
	UserDatabase userData;
	ManageAlert alert;
	Button button;
	String question;
	String content;
	String paCat1;
	String paDes1;
	String fullName;

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
		hpAct = new ActivityStream(driver);
		button = new Button(driver);
		aHome=new AnswerHomePage(driver);
		alert = new ManageAlert(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		question = txData.getContentByArrayTypeRandom(1)+"116814";
		content = txData.getContentByArrayTypeRandom(1)+"116814";
		paCat1 = txData.getContentByArrayTypeRandom(1)+"p116814";
		paDes1 = txData.getContentByArrayTypeRandom(1)+"p116814";
		fullName = userData.fullName.get(0);
	}

	@AfterClass
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:116838.
	 * Test Case Name: Create a question.
	 * Case ID:116844.
	 * Test Case Name: Delete a question.
	 * Pre-Condition: - Create a new page to add new answer portlet (call it Answer Page in this test case)
	 * Post-Condition: 
	 */
	@Test
	public  void test01_07_CreateDeleteAQuestion() {
		String question = txData.getContentByArrayTypeRandom(1)+"q116838";
		String content = "line1<br>line2<br>line3<br>line4<br>line5<br>";
		String paCat1 = txData.getContentByArrayTypeRandom(1)+"p116838";
		String paDes1 = txData.getContentByArrayTypeRandom(1)+"p116838";
		String answer = txData.getContentByArrayTypeRandom(1)+"a116838";
		String comment = txData.getContentByArrayTypeRandom(1)+"c116838";
		info("Test 1: Create a question");
		/*Step Number: 1
		 *Step Name: - Create a new question
		 *Step Description: 
			- Goto answer page
			- Create new Category
			- create new question
		 *Input Data: 

		 *Expected Outcome: 
			- Question is created successfully
			- an Activity is added into activity stream
			- Informations that are displayed in the featured content :1. Question's title2. First 4 lines of question's details3. Number of replies4. Number of comments5. Average rating of the question*/ 
		hp.goToAnswer();
		info("Create category");
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));

		info("Create question");
		cMang.goToActionOfCategoryFromRightClick(paCat1, actionCategoryOption.SUBMITQUESTION);
		qMang.inputDataToQuestionForm(question, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);

		info("Create answer");
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		aMang.goToAnswerQuestion(question);
		aMang.inputDataToAnswer(answer, null, null, null);
		click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer).replace("$fullname", fullName));

		info("Create comment");
		comMang.goToCommentQuestion(question);
		comMang.inputDataToComment(comment);
		click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		waitForAndGetElement(comMang.ELEMENT_COMMENT_AUTHOR.replace("$comment", comment).replace("$fullname", fullName));

		info("Check homepage activity");
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_TITLE.replace("$user", DATA_USER1).replace("$question", question));
		waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_RATING.replace("$user", DATA_USER1).replace("$question", question));
		waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_ANSWER_NUMBER.replace("$user", DATA_USER1).replace("$question", question).replace("$number", "1"));
		waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_COMMENT_NUMBER.replace("$user", DATA_USER1).replace("$question", question).replace("$number", "1"));
		String conAct=waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_CONTENT.replace("$user", DATA_USER1).replace("$question", question)).getText();
		info(conAct);
		hpAct.checkContentOfActivity(conAct,content);

		info("Test 7: Delete a question");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- Open Answers application
			- Go to the question and click More Actions 
			-
			-> Delete
			- Click OK to summit
			- Back to the Hompage
		 *Input Data: 

		 *Expected Outcome: 
			- The Answers activity related to the question is removed from the activity stream*/ 
		hp.goToAnswer();
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		qMang.deleteQuestion(question);

		info("Check homepage activity");
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_QUESTION_ACTIVITY_TITLE.replace("$user", DATA_USER1).replace("$question", question));

		info("Clear data test");
		hp.goToAnswer();
		qMang.deleteQuestion(question);
	}

	/**
	 * Case ID:116842.
	 * Test Case Name: Submit an answer.
	 * Case ID:116843.
	 * Test Case Name: Submit a comment.
	 * Case ID:116839.
	 * Test Case Name: Check number of comments and answers.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test02_05_06_CheckNumberOfCommentsAndAnswers() {
		String question = txData.getContentByArrayTypeRandom(1)+"q116839";
		String content = txData.getContentByArrayTypeRandom(1)+"con116839";

		String answer1 = "line1<br>line2<br>line3<br>line4<br>line5<br>";
		String answer2 = txData.getContentByArrayTypeRandom(1)+"a2116839";
		String comment = txData.getContentByArrayTypeRandom(1)+"c116839";
		info("Create data test");
		hp.goToAnswer();

		info("Create question");
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(question, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);

		info("Test 5: Submit an answer");
		/*Step Number: 1
		 *Step Name: - Submit an answer
		 *Step Description: 
			- Connect to Intranet
			- Open Answers application
			- goto the question and click [Answer]
			- Fill the answer and click [save]
			- Back to the Hompage
		 *Input Data: 

		 *Expected Outcome: 
			- The activity content is updated in the activity stream: the number of answers is updated
			- A comment is added: Answer has been submitted: $value. where $value, is first 4 lines of the answer.*/
		info("Create answer");
		aMang.goToAnswerQuestion(question);
		aMang.inputDataToAnswer(answer1, null, null, null);
		click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);

		info("Check homepage activity");
		hp.goToHomePage();
		String ansComment=waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_COMMENT_CONTENT.replace("$userActivity", DATA_USER1).replace("$question", question).replace("$userComment", DATA_USER1).replace("$index", "1")).getText();
		info(ansComment);
		assert ansComment.contains(hpAct.ELEMENT_QUESTION_ACTIVITY_COMMENT_ANSWER);
		hpAct.checkContentOfActivity(ansComment,answer1);

		info("Test 6: Submit a comment");
		/*Step Number: 1
		 *Step Name: - Submit a comment
		 *Step Description: 
			- Connect to Intranet
			- Open Answers application
			- Goto question and click [Comment] to comment
			- Fill the comment and click [save]
			- Back to the Hompage
		 *Input Data: 

		 *Expected Outcome: 
			- The activity content is updated in the activity stream: The number of comments is updated
			- Corresponding activity's comment is added*/ 
		info("Create comment");
		hp.goToAnswer();
		comMang.goToCommentQuestion(question);
		comMang.inputDataToComment(comment);
		click(comMang.ELEMENT_COMMENT_FORM_SAVE_BUTTON);
		waitForAndGetElement(comMang.ELEMENT_COMMENT_AUTHOR.replace("$comment", comment).replace("$fullname", fullName));

		info("Check homepage activity");
		hp.goToHomePage();
		String commComment=waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_COMMENT_CONTENT.replace("$userActivity", DATA_USER1).replace("$question", question).replace("$userComment", DATA_USER1).replace("$index", "2")).getText();
		info(commComment);
		hpAct.checkContentOfActivity(commComment,comment);

		info("Test 2: Check number of comments and answers");
		/*Step Number: 1
		 *Step Name: - Create a question
		 *Step Description: 
			- Goto answer page and create new question
			- make some answers and comments
			- Check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			The number of comments attached to the activity is equal to the number of comments + the number of answers to the question.For example, if there are 2 comments and 4 answers, it will display 6 comments for the activity.*/
		info("Create answer");
		hp.goToAnswer();
		aMang.goToAnswerQuestion(question);
		aMang.inputDataToAnswer(answer2, null, null, null);
		click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		waitForAndGetElement(aMang.ELEMENT_ANSWER_AUTHOR.replace("$answer", answer2).replace("$fullname", fullName));

		info("Check homepage activity");
		hp.goToHomePage();
		String numberComment=waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_NUMBER_COMMENT.replace("$user", DATA_USER1).replace("$question", question)).getText();
		info(numberComment);
		assert numberComment.contains("3");

		info("Clear data test");
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		qMang.deleteQuestion(question);
	}

	/**
	 * Case ID:116840.
	 * Test Case Name: Unactivate a question.
	 * Case ID:116841.
	 * Test Case Name: Activate a question.
	 * Pre-Condition: the question is already exist in Answers application
	 * Post-Condition: 
	 */
	@Test
	public  void test03_04_UnactivateActiveAQuestion() {
		String question = txData.getContentByArrayTypeRandom(1)+"q116840";
		String content = txData.getContentByArrayTypeRandom(1)+"con116840";
	
		info("Create data test");
		hp.goToAnswer();

		info("Create question");
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(question, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		
		info("Test 3: Unactivate a question");
		/*Step Number: 1
		 *Step Name: - Unactivate a question
		 *Step Description: 
			- Connect to Intranet
			- Open Answers application
			- Goto [Manage Question]
			- Select question to unactivate and click Edit.
			- in question form, uncheck [Activated] checkbox and click [Save]
			- Go to Homepage to check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- The activity content isn't updated in the activity stream
			- A comment is added: Question has been unactivated.*/ 
		qMang.goToManageQuestionForm();
		qMang.activeQuestionFromManageQuestionForm(question, false);
		waitForAndGetElement(qMang.ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
		info("Check homepage activity");
		hp.goToHomePage();
		String comment=waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_COMMENT_CONTENT.replace("$userActivity", DATA_USER1).replace("$question", question).replace("$userComment", DATA_USER1).replace("$index", "1")).getText();
		info(comment);
		hpAct.checkContentOfActivity(comment,hpAct.ELEMENT_QUESTION_ACTIVITY_UNACTIVATE_COMMENT);

		info("Test 4: Activate a question");
		/*Step Number: 1
		 *Step Name: - Unactivate a question
		 *Step Description: 
			- Connect to Intranet
			- Open Answers application
			- Goto [Manage Question]
			- Select question to activate and click Edit.
			- in question form, unchecked [Activate] checkbox and click [Save]
			- Goto Homepage to check activity stream
		 *Input Data: 

		 *Expected Outcome: 
			- The activity content isn't updated in the activity stream
			- A comment is added: Question has been activated.*/ 
		hp.goToAnswer();
		qMang.goToManageQuestionForm();
		qMang.activeQuestionFromManageQuestionForm(question, true);
		waitForAndGetElement(qMang.ELEMENT_MANAGE_QUESTION_CLOSE_BUTTON).click();
		
		info("Check homepage activity");
		hp.goToHomePage();
		comment=waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_COMMENT_CONTENT.replace("$userActivity", DATA_USER1).replace("$question", question).replace("$userComment", DATA_USER1).replace("$index", "2")).getText();
		info(comment);
		hpAct.checkContentOfActivity(comment,hpAct.ELEMENT_QUESTION_ACTIVITY_ACTIVATE_COMMENT);

		info("Clear data test");
		hp.goToAnswer();
		qMang.deleteQuestion(question);
	}

	/**
	 * Case ID:116845.
	 * Test Case Name: Edit question title.
	 * Pre-Condition: the question is already exist in Answers application
	 * Post-Condition: 
	 */
	@Test
	public  void test08_EditQuestionTitle() {
		String question = txData.getContentByArrayTypeRandom(1)+"q116845";
		String newquestion = txData.getContentByArrayTypeRandom(1)+"qn116845";
		String content = txData.getContentByArrayTypeRandom(1)+"con116845";

		info("Create data test");
		hp.goToAnswer();

		info("Create question");
		qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(question, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		
		info("Test 8: Edit question title");
		/*Step Number: 1
		 *Step Name: - Update question title
		 *Step Description: 
			- Connect to Intranet
			- Open Answers application
			- Go to question and click More Actions 
			-
			-> Edit
			- Update question title and click [Save]
			- Back to the Hompage
		 *Input Data: 

		 *Expected Outcome: 
			- The activity content is updated with the new title
			- A comment is added: Title has been updated to: $value.*/ 
		qMang.goToActionOfQuestionByRightClick(question, actionQuestionOption.EDIT);
		qMang.inputDataToQuestionForm(newquestion, null, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		waitForElementNotPresent(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question)));
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", newquestion)));

		info("Check homepage activity");
		hp.goToHomePage();
		String comment=waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_COMMENT_CONTENT.replace("$userActivity", DATA_USER1).replace("$question", newquestion).replace("$userComment", DATA_USER1).replace("$index", "1")).getText();
		info(comment);
		hpAct.checkContentOfActivity(comment,hpAct.ELEMENT_QUESTION_ACTIVITY_UPDAT_TITLE_COMMENT.replace("$value", newquestion));
		
		info("Clear data test");
		hp.goToAnswer();
		qMang.deleteQuestion(newquestion);
	}

	/**
	 * Case ID:116846.
	 * Test Case Name: Open Answers application from Answer's activity.
	 * Case ID:116847.
	 * Test Case Name: Delete a question activity by deleting its category.
	 * Pre-Condition: an answers activity is already shared in the activity stream
	 * Post-Condition: 
	 */
	@Test
	public  void test09_10_OpenAnswersApplicationFromAnswersActivity() {
		String question = txData.getContentByArrayTypeRandom(1)+"q116846";
		String content = txData.getContentByArrayTypeRandom(1)+"con116846";
		String paCat1 = txData.getContentByArrayTypeRandom(1)+"p116846";
		String paDes1 = txData.getContentByArrayTypeRandom(1)+"p116846";
		info("Create data test");
		hp.goToAnswer();
		info("Create category");
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));

		info("Create question");
		cMang.goToActionOfCategoryFromRightClick(paCat1, actionCategoryOption.SUBMITQUESTION);
		qMang.inputDataToQuestionForm(question, content, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		
		info("Test 9: Open Answers application from Answer's activity");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- From the activity stream, click on the Question title in answers activity
		 *Input Data: 

		 *Expected Outcome: 
			- The answer application is opened in the corresponding question(if the application is into a space, the question is opened under the correct space)*/
		info("Check homepage activity");
		hp.goToHomePage();
		waitForAndGetElement(hpAct.ELEMENT_QUESTION_ACTIVITY_TITLE.replace("$user", DATA_USER1).replace("$question", question));
		click(hpAct.ELEMENT_QUESTION_ACTIVITY_TITLE.replace("$user", DATA_USER1).replace("$question", question));
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_SELECTED_ITEM.replace("$question", question)));
		
		info("Test 10 Delete a question activity by deleting its category");
		/*Step Number: 1
		 *Step Name: 
		 *Step Description: 
			- Connect to Intranet
			- Open Answers application
			- Create a new category
			- Create a new question in this category
		 *Input Data: 

		 *Expected Outcome: 
			- Question is created in new category*/

		/*Step number: 2
		 *Step Name: 
		 *Step Description: 
			- Delete category at step 1
			- Back to the Hompage
		 *Input Data: 

		 *Expected Outcome: 
			-Question activity is deleted*/ 
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		cMang.deleteCategory(paCat1);
		
		info("Check homepage activity");
		hp.goToHomePage();
		waitForElementNotPresent(hpAct.ELEMENT_QUESTION_ACTIVITY_TITLE.replace("$user", DATA_USER1).replace("$question", question));

	}
}