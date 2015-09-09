package org.exoplatform.selenium.platform.wildcard.functional;

import static org.exoplatform.selenium.TestLogger.info;
import java.util.ArrayList;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.QuestionManagement;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfCalendarOption;
import org.exoplatform.selenium.platform.calendar.CalendarManagement.menuOfMainCalendar;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.MailSuffixDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Wildcard_TestConfig_1 extends PlatformBase {
	
	ManageLogInOut magAc;
	Button button;
	NavigationToolbar navTool;
	HomePagePlatform hp;
	
	TextBoxDatabase txData;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	
	AnswerCategoryManagement aCatMg;
	MailSuffixDatabase mailSuffixData;
	ArrayList<String> arrayUsers;
	AnswerHomePage aHome;
	QuestionManagement qMang;
	AttachmentFileDatabase fData;
	
	CalendarManagement cMang;
	SpaceManagement spaMg;
	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeMethod");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		navTool = new NavigationToolbar(driver);
		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);
		MailSuffixDatabase mailSuffixData;
		
		hp = new HomePagePlatform(driver);
		aCatMg = new AnswerCategoryManagement(driver);
		aHome = new AnswerHomePage(driver);
		qMang = new QuestionManagement(driver);
		
		cMang = new CalendarManagement(driver);
		button = new Button(driver);
		
		spaMg = new SpaceManagement(driver);
		
		mailSuffixData = new MailSuffixDatabase();
		mailSuffixData.setMailSuffixData(mailSuffixFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		fData = new AttachmentFileDatabase();
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		arrayUsers = new ArrayList<String>();
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterMethod");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterMethod");
	}
	
	public void addGroup(String name){
		navTool.goToUsersAndGroupsManagement();
		userAndGroup.goToGroupTab();
		userAndGroup.addGroup(name, name,name, true);
	}
	public void addUserToGroup(String user,String group){
 	 	click (userAndGroup.ELEMENT_GROUP_MANAGEMENT_SELECT_GROUP.replace("${name}",group));
 	 	userAndGroup.addUsersToGroup(user,"*", false, true);
	}
	public void deleteGroup(String name){
		navTool.goToUsersAndGroupsManagement();
 	 	userAndGroup.goToGroupTab();
		userAndGroup.deleteGroup(name, true);
	}
	/**
	 * Delete all users that are created in testing process
	 */
	public void deleteAllUsers(){
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		navTool.goToUsersAndGroupsManagement();
		if(arrayUsers.size()>0){
			userAndGroup.deleteAllUsers(arrayUsers);
		}
	}
	/**
	 * Add category and question
	 * @param cat
	 * @param ques
	 * @param link
	 */
	public void addCatAndQues(String cat,String ques,String link){
		info("Create category");
	 	hp.goToAnswer();
 	 	aCatMg.createCategory(cat);
 	 	info("Add question");
 	 	click(aCatMg.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
 	 	qMang.goToSubmitQuestion();
		qMang.inputDataToQuestionForm(ques,ques, null, "TestData/" + link);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		waitForAndGetElement(By.xpath(aHome.ELEMENT_QUESTION_LIST_ITEM.replace("$question", ques)));
	}
	/**
	 * Delete category
	 * @param cat
	 */
	public void deleteCat(String cat){
		hp.goToAnswer();
		aHome.goToHomeCategory();
		click(aCatMg.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat));
		aCatMg.deleteCategory(cat);
	}
	/**
	 * Create calendar
	 * @param cal
	 * @param group
	 */
	public void createCalendar(String cal,String...group){
		info("Create a new calendar");
        hp.goToCalendarPage();
        cMang.goToMenuFromMainCalendar(menuOfMainCalendar.ADDCAL);
        cMang.inputDataInDetailTabCalendarForm(cal, cal,null);
        if(group.length>0){
	        cMang.selectGroupInGroupTabCalendarForm(group[0],true);
	        cMang.selectGroupPermission(group[0],"*");
        }
        cMang.saveAddCalendar();
        waitForAndGetElement(cMang.ELEMENT_CALENDAR_LIST_ITEM.replace("$calendar",cal));
	}
	/**
	 * edit group calendar permission
	 * @param cal
	 * @param group
	 */
	public void editPermGroupCalendar(String cal,String...group){
		info("Edit permission of "+cal);
		hp.goToCalendarPage();
		cMang.executeActionCalendar(cal, menuOfCalendarOption.EDIT);
        cMang.selectGroupInGroupTabCalendarForm(group[0],true);
        cMang.selectGroupPermission(group[0],"*");
        cMang.saveAddCalendar();
	}
	/**
	 * delete calendar
	 * @param cal
	 */
	public void deleteCalendar(String cal){
		  info("delete calendar:"+cal);
		  hp.goToCalendarPage();
		  cMang.deleteCalendar(cal, true);
	}
}