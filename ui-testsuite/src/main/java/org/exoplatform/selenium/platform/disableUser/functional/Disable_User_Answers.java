package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement.actionCategoryOption;
import org.testng.annotations.*;


	public class Disable_User_Answers extends Disable_User_TestConfig{
		public void createUser(){
			searchEmail = userSearchOptionData.getUserSearchOptionByIndex(3);
			membership = portMemPermisData.getContentByIndex(0);
            username = userInfoData.getUserNameByIndex(5)+getRandomString();
			password = userInfoData.getPassWordByIndex(5)+getRandomString();
			lastName = userInfoData.getLastNameByIndex(5)+getRandomString();
			email = EMAIL_ADDRESS1;
			searchUserName = userSearchOptionData.getUserSearchOptionByIndex(0);
			info("remove existed user with EMAIL_ADDRESS1");
			navToolBar.goToUsersAndGroupsManagement();
			userAndGroup.searchUser(EMAIL_ADDRESS1, searchEmail);
			if(isTextPresent(EMAIL_ADDRESS1))
			userAndGroup.deleteUser();
			
			info("Create new user");
			navToolBar.goToAddUser();
			addUserPage.addUser(username, password, email, username, lastName);
		}
		
	/**
	*<li> Case ID:128003.</li>
	*<li> Test Case Name: Check disabled user in Permission of Answers.</li>
	*<li> Pre-Condition: User A is disabled</li>
	*<li> Post-Condition: </li>
	*/
	@Test
	public  void test01_CheckDisabledUserInPermissionOfAnswers() {
		info("Test 1: Check disabled user in Permission of Answers");
		createUser();
		disableUser();
		/*Step Number: 1
		*Step Name: Step 1: Select User
		*Step Description: 
			- Connect to Intranet with User B
			- Go toAnswers > Add/Edit Category > Permissions > Select User
		*Input Data: 
			
		*Expected Outcome: 
			- User A is not displayed*/ 
		hp.goToAnswer();
		aCatMg.checkPermUserList(username,false);
		deleteUser();
 	}

	/**
	*<li> Case ID:128004.</li>
	*<li> Test Case Name: Check email from watch on answers app for disabled user.</li>
	*<li> Pre-Condition: User A has watched an answer
	*User A is disabledUser B is admin</li>
	*<li> Post-Condition: </li>
	* BUG: https://jira.exoplatform.org/browse/ANS-17
	*/
	@Test (groups="pending")
	public  void test02_CheckEmailFromWatchOnAnswersAppForDisabledUser() {
		info("Test 2: Check email from watch on answers app for disabled user");
		String cat1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String des1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String ques1 = txData.getContentByArrayTypeRandom(1) + getRandomNumber();
		String contentMail = "A new question has been posted "+cat1+" "+ques1;
		
		createUser();
		info("Create category 1");
		hp.goToAnswer();
		aCatMg.goToActionOfCategoryFromActionBar(actionCategoryOption.ADD);
		aCatMg.inputDataToSettingTab(cat1, null, des1, null, null, null);
		click(aCatMg.ELEMENT_CATEGORY_ADD_FORM_SAVE_BUTTON);
		waitForAndGetElement(aCatMg.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat1));
		magAc.signOut();
		magAc.signIn(username,password);
		
		info("watch category");
		hp.goToAnswer();
		aCatMg.goToActionOfCategoryFromRightClick(cat1, actionCategoryOption.WATCH);
		
		/*Step Number: 1
		*Step Name: Step 1: Edit a watched answer
		*Step Description: 
			- Connect to Intranet with User B
			- Edit an answers already watched by the user A
		*Input Data: 
			
		*Expected Outcome: 
			- The answer is edited*/
		magAc.signOut();
		magAc.signIn(DATA_USER1, DATA_PASS);
		disableUser();
		
		info("Create question 1");
		hp.goToAnswer();
		aCatMg.goToActionOfCategoryFromRightClick(cat1, actionCategoryOption.SUBMITQUESTION);
		qMang.inputDataToQuestionForm(ques1, des1, null, null);
		click(qMang.ELEMENT_SUBMIT_QUESTION_FORM_SAVE_BUTTON);
		click(button.ELEMENT_OK_BUTTON_LINK);
		
		/*Step number: 2
		*Step Name: Step 2: Check inbox email of disabled user
		*Step Description: 
			- Check the email inbox of the User A
		*Input Data: 
			
		*Expected Outcome: 
			- answer watch email is not received*/ 
		info("check mail");
		goToMail(EMAIL_ADDRESS1, EMAIL_PASS);
		checkEmailNotification(contentMail,false,false);
		switchToParentWindow();
		
		info("Clear data");
		aHome.goToHomeCategory();
		click(aCatMg.ELEMENT_CATEGORY_LIST_ITEM.replace("$category", cat1));
		aCatMg.deleteCategory(cat1);
		deleteUser();
 	}}