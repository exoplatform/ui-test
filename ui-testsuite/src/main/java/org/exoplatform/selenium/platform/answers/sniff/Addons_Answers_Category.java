package org.exoplatform.selenium.platform.answers.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.AnswerManagement;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement;
import org.exoplatform.selenium.platform.answer.QuestionManagement;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement.actionCategoryOption;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.openqa.selenium.By;
import org.testng.annotations.*;


public class Addons_Answers_Category  extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	TextBoxDatabase txData;
	AnswerCategoryManagement cMang;
	QuestionManagement qMang;
	AnswerHomePage aHome;
	AttachmentFileDatabase fData;
	UserDatabase userData;
	ManageAlert alert;
	Button button;
	AnswerManagement aMang;
	UserProfilePage myProfile;
	NavigationToolbar navTool;
	@BeforeClass
	public void setUpBeforeTest() throws Exception{
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		driver.get(baseUrl);
		navTool = new NavigationToolbar(driver);
		myProfile = new UserProfilePage(driver);
		magAc = new ManageLogInOut(driver);
		hp = new HomePagePlatform(driver);
		cMang = new AnswerCategoryManagement(driver);
		button = new Button(driver);
		aHome=new AnswerHomePage(driver);
		qMang = new QuestionManagement(driver);
		aMang = new AnswerManagement(driver);
		alert = new ManageAlert(driver);
		txData = new TextBoxDatabase();
		fData = new AttachmentFileDatabase();
		userData = new UserDatabase();
		userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
	}

	@AfterClass
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	@AfterMethod
	public void afterMethod(){
		switchToParentWindow();
		magAc.signOut();
	}
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		magAc.signIn(DATA_USER1, DATA_PASS);
	}

	/**
	 * Case ID:116809.
	 * Test Case Name: Drag and Drop category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=1)
	public  void test01_DragAndDropCategory() {
		String paCat1 = txData.getContentByArrayTypeRandom(1)+"p1116809";
		String paDes1 = txData.getContentByArrayTypeRandom(1)+"p1116809";

		String paCat2 = txData.getContentByArrayTypeRandom(1)+"p2116809";
		String paDes2 = txData.getContentByArrayTypeRandom(1)+"p2116809";

		info("Create parent categories");
		hp.goToAnswer();
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));

		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));

		info("Test 1: Drag and Drop category");
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create categories after drag and drop them
		 *Input Data: 
			- Create some categories 
			- Drag and drop a category into another category
		 *Expected Outcome: 
			Category is dragged and dropped successfully to selected category*/ 
		dragAndDropToObject(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1), cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		
		info("Clear data");
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		cMang.deleteCategory(paCat2);
	}

	/**
	 * Case ID:116810.
	 * Test Case Name: Export/Import category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=5)
	public  void test02_ExportImportCategory() {
		String paCat1 = txData.getContentByArrayTypeRandom(1)+"p1116810";
		String paDes1 = txData.getContentByArrayTypeRandom(1)+"p1116810";
		String question1 = txData.getContentByArrayTypeRandom(1)+"q1116810";
		String content1 = txData.getContentByArrayTypeRandom(1)+"qc1116810";
		String paCat2 = txData.getContentByArrayTypeRandom(1)+"p2116810";
		String paDes2 = txData.getContentByArrayTypeRandom(1)+"p2116810";
		String fileName = getRandomNumber();
		info("Test 2: Export/Import category");
		/*Step Number: 1
		 *Step Name: Create categories, questions
		 *Step Description: 
			- Create some categories
			- Create some questions in categories
		 *Input Data: 

		 *Expected Outcome: 
			Category and its questions are exported/imported successfully*/
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
		
		info("Create category 2");
		aHome.goToHomeCategory();
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));

		/*Step number: 2
		 *Step Name: Export categories
		 *Step Description: 
			- Right click on 1 category and select Export
			- Put file's name and Export
		 *Input Data: 

		 *Expected Outcome: 
			Category and its questions are exported successfully*/
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		cMang.exportAnswerCategory(fileName);
		cMang.deleteCategory(paCat1);
		
		/*Step number: 3
		 *Step Name: Import categories
		 *Step Description: 
			- Remove the above category
			- Right click on another category and select Import
			- Choose the file which is exported above
		 *Input Data: 

		 *Expected Outcome: 
			- The category is removed successfully.
			- That category and its questions are imported successfully*/ 
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		cMang.importAnswerCategory("TestData/TestOutput/" + fileName+".zip");
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", question1)));
		
		info("Clear data");
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		cMang.deleteCategory(paCat2);
	}

	/**
	 * Case ID:116811.
	 * Test Case Name: Watch/Unwatch category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=4)
	public  void test03_WatchUnwatchCategory() {
		String paCat1 = txData.getContentByArrayTypeRandom(1)+"p1116811";
		String paDes1 = txData.getContentByArrayTypeRandom(1)+"p1116811";
		String question1 = txData.getContentByArrayTypeRandom(1)+"q1116811";
		String content1 = txData.getContentByArrayTypeRandom(1)+"qc1116811";
		String answer1 = txData.getContentByArrayTypeRandom(1)+"a1116811";
		String answer2 = txData.getContentByArrayTypeRandom(1)+"a2116811";
		String contentMail = "A new question has been posted "+paCat1+" "+question1;
		
		//update email
		info("edit contact info");
		info("edit info");
		navTool.goToMyProfile();
		info("goto edit profile page");
		click(myProfile.ELEMENT_EDIT_MY_PROFILE_LINK);
		myProfile.updateBasicInformation(null, null, EMAIL_ADDRESS2);
		myProfile.saveCancelUpdateInfo(true);
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		info("Test 3: Watch/Unwatch category");
		/*Step Number: 1
		 *Step Name: Create category, question
		 *Step Description: 
			Create some categories, questions
		 *Input Data: 

		 *Expected Outcome: 
			Categories, questions are created successfully*/
		info("Create category 1");
		hp.goToAnswer();
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));

		info("Create question 1");
		cMang.goToActionOfCategoryFromRightClick(paCat1, actionCategoryOption.SUBMITQUESTION);
		qMang.inputDataToQuestionForm(question1, content1, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		
		/*Step number: 2
		 *Step Name: Unwatch
		 *Step Description: 
			- Right click on 1 watching category and select Unwatch
		 *Input Data: 

		 *Expected Outcome: 
			Category is watched/unwatched successfully*/
		aHome.goToHomeCategory();
		cMang.goToActionOfCategoryFromRightClick(paCat1, actionCategoryOption.WATCH);
		cMang.goToActionOfCategoryFromRightClick(paCat1, actionCategoryOption.UNWATCH);
		Utils.pause(3000);
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer2, null, null, null);
		click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);
		
		//Check email
		goToMail(EMAIL_ADDRESS2, EMAIL_PASS);
		checkEmailNotification(contentMail,false,false);
		switchToParentWindow();
		
		/*Step number: 3
		 *Step Name: UnWatch
		 *Step Description: 
			- Right click on 1 category and select Watch
		 *Input Data: 

		 *Expected Outcome: 
			Category is watched/unwatched successfully*/ 
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		aHome.goToHomeCategory();
		cMang.goToActionOfCategoryFromRightClick(paCat1, actionCategoryOption.WATCH);
		magAc.signOut();
		magAc.signIn(DATA_USER2, DATA_PASS);
		hp.goToAnswer();
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		aMang.goToAnswerQuestion(question1);
		aMang.inputDataToAnswer(answer1, null, null, null);
		click(aMang.ELEMENT_ANSWER_FORM_SAVE_BUTTON);

		//Check email
		goToMail(EMAIL_ADDRESS2, EMAIL_PASS);
		checkEmailNotification(contentMail,true,false);
		switchToParentWindow();
		
		info("Clear data");
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		hp.goToAnswer();
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		cMang.deleteCategory(paCat1);
	}

	/**
	 * Case ID:116824.
	 * Test Case Name: Add a category.
	 * Case ID:116825.
	 * Test Case Name: Edit a category.
	 * Case ID:116826.
	 * Test Case Name: Delete a category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=2)
	public  void test04_05_06_AddEditDeleteACategory() {
		String cat = txData.getContentByArrayTypeRandom(1)+"116824";
		String des = txData.getContentByArrayTypeRandom(1)+"116824";

		String newcat = txData.getContentByArrayTypeRandom(1)+"n116824";
		String newdes = txData.getContentByArrayTypeRandom(1)+"n116824";

		info("Test 4: Add a category");
		/*Step Number: 1
		 *Step Name: Add a category
		 *Step Description: 
			- Go to Answers page
			- Select Category, then Add Category
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Category is added new and shown in Answer page*/ 
		hp.goToAnswer();
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(cat, null, des, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));

		info("Test 5: Edit a category");
		/*Step number: 2
		 *Step Name: Edit a category
		 *Step Description: 
			- Open this category
			- Select Category, then Edit
			- Input some data into fields
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Category is edited successfully*/ 
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.EDIT);
		cMang.inputDataToSettingTab(newcat, null, newdes, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		aHome.goToHomeCategory();
		waitForElementNotPresent(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", newcat));

		info("Test 6: Delete a category");
		/*Step number: 2
		 *Step Name: Delete a category
		 *Step Description: 
			- Open this category
			- Select Category, then Delete
		 *Input Data: 

		 *Expected Outcome: 
			- Category is deleted successfully*/ 
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", newcat));
		cMang.deleteCategory(newcat);
	}

	/**
	 * Case ID:116827.
	 * Test Case Name: Move a category.
	 * Pre-Condition: 
	 * Post-Condition: 
	 */
	@Test(priority=3)
	public  void test07_MoveACategory() {
		String paCat1 = txData.getContentByArrayTypeRandom(1)+"p1116827";
		String paDes1 = txData.getContentByArrayTypeRandom(1)+"p1116827";

		String paCat2 = txData.getContentByArrayTypeRandom(1)+"p2116827";
		String paDes2 = txData.getContentByArrayTypeRandom(1)+"p2116827";

		String chCat = txData.getContentByArrayTypeRandom(1)+"c116827";
		String chDes = txData.getContentByArrayTypeRandom(1)+"c116827";

		info("Create parent categories");
		hp.goToAnswer();
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat1, null, paDes1, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));

		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(paCat2, null, paDes2, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));

		info("Test 7: Move a category");
		/*Step Number: 1
		 *Step Name: Add a category
		 *Step Description: 
			- Go to Answers page
			- Select Category, then Add Category
			- Put values
			- Save
		 *Input Data: 

		 *Expected Outcome: 
			- Category is added new and shown in Answer page*/
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		cMang.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		cMang.inputDataToSettingTab(chCat, null, chDes, null, null, null);
		click(cMang.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", chCat));

		/*Step number: 2
		 *Step Name: Move a category
		 *Step Description: 
			- Right click on this category
			- Select Move
			- Double clicka destination category
		 *Input Data: 

		 *Expected Outcome: 
			- Move category form is shown.
			-Category is moved to a destination category*/ 
		cMang.goToActionOfCategoryFromRightClick(chCat, actionCategoryOption.MOVE);
		cMang.moveCategory(paCat2);
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		waitForAndGetElement(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", chCat));
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		waitForElementNotPresent(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", chCat));

		info("Clear data");
		aHome.goToHomeCategory();
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat1));
		cMang.deleteCategory(paCat1);
		click(cMang.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", paCat2));
		cMang.deleteCategory(paCat2);
	}
}