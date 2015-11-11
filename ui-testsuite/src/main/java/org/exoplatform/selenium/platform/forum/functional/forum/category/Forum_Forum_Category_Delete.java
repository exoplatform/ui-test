package org.exoplatform.selenium.platform.forum.functional.forum.category;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author khanhnt
 * Done 1/1 Test Cases
 * Update chinhdtt
 *
 */
public class Forum_Forum_Category_Delete extends ForumBase{
	ManageAccount acc;
	ForumManageCategory fmCat;

	@BeforeMethod
	public void setUpBeforeTest() {
		initSeleniumTest();
		driver.get(plfURL);
		fmCat = new ForumManageCategory(driver);
		acc = new ManageAccount(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		button = new Button(driver);
	}

	@AfterMethod
	public void afterTest() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:109045.
	 * Test Case Name: Delete Category with confirmation Forum.
	 * Created by khanhnt at 2013/11/20 17:40:11 
	 */

	@Test
	public  void test01_DeleteCategoryWithConfirmation() {
		info("Test 1: Delete Category with confirmation");
		String catName = "test 01 Delete Category";
		String order = "0";
		int chooseRestricted = 0;
		String[] restricted = { "" };
		String description = "Description";
		int setPermission = 0;
		String[] userGroup = null;
		boolean permission = false;

		/*
		- Login as administrator user.
		- Go to Forum Application.
		- Add a new category that allows anyone to access ([Restricted Audience] = blank).
		 *Expected Outcome: 
		- New category is added successfully.		*/
		goToForums();
		fmCat.addNewCategoryInForum(catName, order, chooseRestricted,
				restricted, description, setPermission, userGroup, permission);		

		/*
		-Login as normal user.
		- Go to Forum application.
		- Click on Category that was created in step1.
		 *Input Data: 
		 *Expected Outcome: 
		- There's no way for normal user to delete this category.		*/ 
		acc.userSignIn(userType.PUBLISHER);
		goToForums();
		fmCat.checkUserCanEditDeleteACategory(catName,false);

		/*
		- Login as administrator user.
		- Go to Forum application.
		- Click on Category that you want to delete.
		 *Input Data: 
		 *Expected Outcome: 
		- All forums of selected category are listed.
		- There is a [Manage Category] button in action bar.		*/
		/*
		- Click on [Mange Category] button.
		- Select [Delete] Category option in popup menu.
		- Click [OK] button to confirm deleting Category.
		 *Input Data: 
		 *Expected Outcome: 
		- A popup menu is displayed with [Delete] button for Category section.
		- A Confirmation Deleting message is shown when [Delete] button is click.
		- Category is deleted successfully when [OK] button is clicked.
		- All forums and topics of forum that belongs to this category are also deleted.
		- Total topics/post in Forum statistics porlet is updated following.		*/
		acc.userSignIn(userType.ADMIN);
		goToForums();
		fmCat.checkUserCanEditDeleteACategory(catName,true);

		goToForumHome();
		waitForAndGetElement(By.linkText(catName)).click();
		fmCat.deleteCategoryInForum(catName);
	}
}
