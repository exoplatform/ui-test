package org.exoplatform.selenium.platform.answers.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.AnswerManagement;
import org.exoplatform.selenium.platform.answer.FaqHomePage;
import org.exoplatform.selenium.platform.answer.QuestionManagement;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement.actionCategoryOption;
import org.exoplatform.selenium.platform.gatein.AnswerPage;
import org.exoplatform.selenium.platform.gatein.FaqPage;
import org.exoplatform.selenium.platform.gatein.PageEditor;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class Addons_Faq   extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	AnswerCategoryManagement cMang;
	QuestionManagement qMang;
	AnswerHomePage aHome;
	UserDatabase userData;
	PageEditor pagEditor;
	Button button;
	AnswerManagement aMang;
	FaqHomePage fhPage;
	AnswerPage aPage;
	PageCreationWizard pMang;
	FaqPage fPage;
	String question1;
	String content1;
	String paCat1;
	String paDes1;
	String paCat2;
	String paDes2;
	String question2;
	String content2;
	String answer1;
	String answer2;
	String urlFAQ;
	String fullName;
	@BeforeClass
	public void setUpBeforeTest() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		cMang = new AnswerCategoryManagement(driver);
		button = new Button(driver);
		aHome=new AnswerHomePage(driver);
		qMang = new QuestionManagement(driver);
		aMang = new AnswerManagement(driver);
		pagEditor = new PageEditor(driver);
		pMang = new PageCreationWizard(driver);
		fPage = new FaqPage(driver);
		aPage = new AnswerPage(driver);
		fhPage = new FaqHomePage(driver);
		txData = new TextBoxDatabase();
		userData = new UserDatabase();
		magAc.signIn(DATA_USER1, DATA_PASS);
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		question1 = txData.getContentByArrayTypeRandom(1)+"q1116917";
		content1 = txData.getContentByArrayTypeRandom(1)+"qc1116917";
		paCat1 = txData.getContentByArrayTypeRandom(1)+"p1116917";
		paDes1 = txData.getContentByArrayTypeRandom(1)+"p1116917";
		question2 = txData.getContentByArrayTypeRandom(1)+"q2116917";
		content2 = txData.getContentByArrayTypeRandom(1)+"qc2116917";
		paCat2 = txData.getContentByArrayTypeRandom(1)+"p2116917";
		paDes2 = txData.getContentByArrayTypeRandom(1)+"p2116917";
		answer1 = txData.getContentByArrayTypeRandom(1)+"a1116917";
		answer2 = txData.getContentByArrayTypeRandom(1)+"a2116917";
		fullName = userData.fullName.get(0);
		createDataTest();
	}

	@AfterClass
	public void afterTest(){
		magAc.signIn(DATA_USER1, DATA_PASS);
		deleteDataTest();
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
		urlFAQ=baseUrl+"/intranet/home/FAQ";
		driver.get(urlFAQ);
		if(waitForAndGetElement(fhPage.ELEMENT_FAQ_QUESTION_LIST, 5000,0)==null){
			fPage.createFaqPage();
		}
		info("create data test");
		info("Create category 1");
		driver.get(baseUrl);
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

	}

	public void deleteDataTest(){
		info("Delete data test");
		info("Create category");
		hp.goToAnswer();
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		cMang.deleteCategory(paCat1);
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		cMang.deleteCategory(paCat2);
	}
	/**
	 * Case ID:116917.
	 * Test Case Name: Browse Category/Sub-category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test01_BrowseCategorySubCategory() {
		info("Test 1: Browse Category/Sub-category");
		/*Step Number: 1
		 *Step Name: Prepare data: Create a page FAQ
		 *Step Description: 
			- Create a page, named FAQ by winzard
			- Drag and drop a FAQ portlet to this page
			- Save and close
		 *Input Data: 

		 *Expected Outcome: 
			- FAQ page is created successfully,
			-Categories, Questions, answers of Answer page is displayed on this page*/

		/*Step number: 2
		 *Step Name: Browse Category and sub category
		 *Step Description: 
			- Open FAQ page 
			- Browse Category/Sub
			-category by click on category name
		 *Input Data: 

		 *Expected Outcome: 
			Category is viewed and Question in this category is displayed in list*/ 
		hp.goToFaq();
		waitForAndGetElement(fhPage.ELEMENT_FAQ_CATEGORY.replace("$category", paCat1));
		waitForAndGetElement(fhPage.ELEMENT_FAQ_CATEGORY.replace("$category", paCat2));
		click(fhPage.ELEMENT_FAQ_CATEGORY.replace("$category", paCat1));
		waitForAndGetElement(fhPage.ELEMENT_FAQ_QUESTION.replace("$question", question1));
		waitForAndGetElement(fhPage.ELEMENT_FAQ_ANSWER.replace("$answer", answer1));
		waitForElementNotPresent(fhPage.ELEMENT_FAQ_CATEGORY.replace("$category", paCat1));
		waitForElementNotPresent(fhPage.ELEMENT_FAQ_CATEGORY.replace("$category", paCat2));
		waitForElementNotPresent(fhPage.ELEMENT_FAQ_QUESTION.replace("$question", question2));
		waitForElementNotPresent(fhPage.ELEMENT_FAQ_ANSWER.replace("$answer", answer2));
	}

	/**
	 * Case ID:116918.
	 * Test Case Name: View question detail.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test02_ViewQuestionDetail() {
		info("Test 2: View question detail");
		/*Step Number: 1
		 *Step Name: View questionon FAQ porlet
		 *Step Description: 
			View question by click on Question's title
		 *Input Data: 

		 *Expected Outcome: 
			Question and Answers in question are displayed*/ 
		hp.goToFaq();
		waitForAndGetElement(fhPage.ELEMENT_FAQ_CATEGORY.replace("$category", paCat1));
		waitForAndGetElement(fhPage.ELEMENT_FAQ_CATEGORY.replace("$category", paCat2));
		click(fhPage.ELEMENT_FAQ_CATEGORY.replace("$category", paCat1));
		click(fhPage.ELEMENT_FAQ_QUESTION.replace("$question", question1));
		waitForElementNotPresent(fhPage.ELEMENT_FAQ_CATEGORY.replace("$category", paCat1));
		waitForElementNotPresent(fhPage.ELEMENT_FAQ_CATEGORY.replace("$category", paCat2));
		waitForAndGetElement(fhPage.ELEMENT_FAQ_ANSWER.replace("$answer", answer1));
		waitForElementNotPresent(fhPage.ELEMENT_FAQ_QUESTION.replace("$question", question2));
		waitForElementNotPresent(fhPage.ELEMENT_FAQ_ANSWER.replace("$answer", answer2));

	}

	/**
	 * Case ID:116919.
	 * Test Case Name: Setting FAQ porlet.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test
	public  void test03_SettingFAQPorlet() {
		String data = "<div>\n <h2>Edit Template 1</h2>\n </div>";
		info("Test 3: Setting FAQ porlet");
		/*Step Number: 1
		 *Step Name: Setting FAQ porlet form edit mode
		 *Step Description: 
			- Go to FAQ page, go to edit mode
			- Edit FAQ portlet
			- Show/hide category
			- Edit template of FAQ porlet
			- Use/not use ajax
		 *Input Data: 

		 *Expected Outcome: 
			- Edit template is successful
			- Selected category is shown/hide in page*/ 
		hp.goToFaq();
		waitForElementNotPresent(By.xpath("//h2[text()='Edit Template 1']"));
		fPage.goToEditFaqPortlet();
		fPage.settingFAQTemplate(data);
		click(pagEditor.ELEMENT_PAGE_EDITOR_CLOSE_BUTTON);
		pagEditor.finishEditLayout();
		waitForAndGetElement(By.xpath("//h2[text()='Edit Template 1']"));
	}
}