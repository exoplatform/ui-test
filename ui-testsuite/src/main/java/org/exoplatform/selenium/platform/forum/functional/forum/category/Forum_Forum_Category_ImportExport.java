package org.exoplatform.selenium.platform.forum.functional.forum.category;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Dialog;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.forum.ForumBase;
import org.exoplatform.selenium.platform.forum.ForumManageCategory;
import org.exoplatform.selenium.platform.forum.ForumManageForum;
import org.exoplatform.selenium.platform.forum.ForumManageTopic;
import org.exoplatform.selenium.platform.forum.ForumManagePost;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author lanltk
 * @date 21 Nov 2013
 * @author lientm migrate to 4.1.x (@date 17 Jun 2014)
 */
public class Forum_Forum_Category_ImportExport extends ForumBase {
  ManageAccount magAc;
  ForumManageCategory magCat;
  ForumManageForum magForum;
  ForumManageTopic magTopic;
  ForumManagePost magPost;
  Button button;
  Dialog dialog;

  @BeforeMethod
  public void setUpBeforeTest(){
    getDriverAutoSave();
    magAc = new ManageAccount(driver);
    magCat = new ForumManageCategory(driver);
    magForum = new ForumManageForum(driver);
    magTopic = new ForumManageTopic(driver);
    magPost = new ForumManagePost(driver);
    button = new Button(driver);
    dialog = new Dialog(driver);

    magAc.signIn(DATA_USER1, DATA_PASS);
    goToForums();

  }

  @AfterMethod
  public void afterTest(){
    driver.manage().deleteAllCookies();
    driver.quit();
  }
  
  /*
   * CaseId: 109065, 109047 -> Export, import category with mode export all
   */
  @Test  
  public void test01_ExportImportCategory_ModeExportAll(){
    String catName1 = "Category_109065_01";
    String restrict = DATA_USER2;
    String[] restricted1 = {restrict};
    String description1 = "Add new category 109065_01";
    String priority1 = "1";
    String catName2 = "Category_109065_02";
    String[] restricted2 = {restrict};
    String description2 = "Add new category 109065_02";    
    String forumName1 = "Forum_01_109065";
    String forumName2 = "Forum_02_109065";    
    String topicName1 = "Topic_01_109065";
    String topicName2 = "Topic_02_109065";   
    String post1 = "Make a post 1";  
    String fileName = "FileExportAllCategories";
    String ip = "192.168.168.1";
	String tag = "TAGEXPORT";
	String replace = "<TAGEXPORT>{replace}</TAGEXPORT>";
	String description = "TAGEXPORT";
	String example = "<TAGEXPORT>tag01</TAGEXPORT>";
    
    //Create 2 categoryies, 2 forums, 2 topics
    magCat.addNewCategoryInForum(catName1, priority1, 1, restricted1, description1, 0, null);
    magForum.quickAddForum(forumName1);
    magTopic.quickStartTopic(topicName1,"Start topic_01_109065");
    click(By.linkText(topicName1));
    magPost.quickReply(post1);
    click(By.linkText(catName1));
    magForum.quickAddForum(forumName2);
    magTopic.quickStartTopic(topicName2,"Start topic_02_109065");
  
    goToForumHome();
    magCat.addNewCategoryInForum(catName2, "2", 1, restricted2, description2, 0, null);
    
    //Create banIP
    setBanIp(ip);
    //Create BBcode
	goToBBCodeManagement();
	addBBCode(tag, replace, description, example, false);  
	click(ELEMENT_BBCODE_CLOSE_BUTTON);
	
    //export all
    goToForumHome();
    magCat.exportCategoryInForum(fileName, true);
    Utils.pause(3000);
    assert checkFileExisted("TestOutput/" + fileName + ".zip"); 

    /* Delete all categories, Forum and topic, banIP, BBCode before running Import test case */
    click(By.linkText(catName1));
    magCat.deleteCategoryInForum(catName1);
    click(By.linkText(catName2));
    magCat.deleteCategoryInForum(catName2);
	deleteBanIp(ip);
	goToBBCodeManagement();
	deleteBBcode(tag);
	click(ELEMENT_BBCODE_CLOSE_BUTTON);

    //Import category with mode export all
    magCat.importCategoryInForum("TestOutput/" + fileName + ".zip");
    info("Import all categories with the file exported all.");
    Utils.pause(500);
    deleteFile("TestOutput/" + fileName + ".zip");
    
    info("Check data after import");
    waitForAndGetElement(By.linkText(catName1));
    waitForAndGetElement(By.linkText(forumName1));
    waitForAndGetElement(By.linkText(forumName2));
    waitForAndGetElement(By.linkText(catName2));
    waitForAndGetElement(By.linkText(topicName1));
    waitForAndGetElement(By.linkText(topicName2));
    
    info("Check data of category 1, forum 1, topic 1, post 1");
    click(By.linkText(catName1));
    magCat.goToEditCategoryInForum();
    assert getValue(magCat.ELEMENT_CATEGORY_TITLE).equalsIgnoreCase(catName1);
    assert getValue(magCat.ELEMENT_CATEGORY_ORDER).equalsIgnoreCase(priority1);
    assert getValue(magCat.ELEMENT_DESCRIPTION).equalsIgnoreCase(description1);
    assert getText(magCat.ELEMENT_RESTRICTED_AUDIENCE).equalsIgnoreCase(restrict);
    click(magCat.ELEMENT_CANCEL_ADD_CATEGORY_BUTTON);
    click(By.linkText(forumName1));
    click(By.linkText(topicName1));
    waitForAndGetElement(magPost.ELEMENT_POST_CONTENT.replace("${postContent}", post1));   
    
    info("Check data of banip, bbcode then delete them");
    deleteBanIp(ip);
	goToBBCodeManagement();
	deleteBBcode(tag); 
	click(ELEMENT_BBCODE_CLOSE_BUTTON);
    
    /* Delete all categories, Forum and topic before exit test case */
    click(By.linkText(catName1));
    magCat .deleteCategoryInForum(catName1);
    click(By.linkText(catName2));
    magCat .deleteCategoryInForum(catName2);
  }

  /*
   * CaseID: 109059, 109054 -> Export, import category with mode Only category
   */

  @Test
  public void test02_ExportImportCategory_ModeOnlyCategory(){
	String catName1 = "Category_01_109059";
	String restrict = DATA_USER2;
	String[] restricted1 = {restrict};
	String description1 = "Add new category 01_109059";
	String catName2 = "Category_02_109059";
	String[] restricted2 = {restrict};
	String description2 = "Add new category 02_109059";	    
	String forumName1 = "Forum_01_109059";
	String forumName2 = "Forum_02_109059";	    
	String topicName1 = "Topic_01_109059";
	String topicName2 = "Topic_02_109059";	
	String post1 = "Make a post 109059";	
    String fileName1 = "FileExportCategoriesOnly";   
    
    magCat.addNewCategoryInForum(catName1, "1", 1, restricted1, description1, 0, null);
    magForum.quickAddForum(forumName1);
    magTopic.quickStartTopic(topicName1,"Start topic_01_109059");
    click(By.linkText(topicName1));
    magPost.quickReply(post1);

    click(By.linkText(catName1));
    magForum.quickAddForum(forumName2);
    magTopic.quickStartTopic(topicName2,"Start topic_02_109059");
  
    goToForumHome();
    magCat.addNewCategoryInForum(catName2, "2", 1, restricted2, description2, 0, null);    

    info("export only category 1");
    goToForumHome();
    magCat.exportCategoryInForum(fileName1, false, catName1);
    Utils.pause(3000);
    assert checkFileExisted("TestOutput/" + fileName1 + ".zip");

    /* Delete all categories, Forum and topic before running Import test case*/
    click(By.linkText(catName1));
    magCat.deleteCategoryInForum(catName1);
    click(By.linkText(catName2));
    magCat.deleteCategoryInForum(catName2);

    info("Import category 1");
    magCat.importCategoryInForum("TestOutput/"+fileName1 + ".zip");
    Utils.pause(500);
    deleteFile("TestOutput/" + fileName1 + ".zip");

    info("Check having only data of category 1");
    waitForElementNotPresent(By.linkText(catName2));
    click(By.linkText(catName1));
    magCat.goToEditCategoryInForum();
    assert getValue(magCat.ELEMENT_CATEGORY_TITLE).equalsIgnoreCase(catName1);
    assert getValue(magCat.ELEMENT_CATEGORY_ORDER).equalsIgnoreCase("1");
    assert getValue(magCat.ELEMENT_DESCRIPTION).equalsIgnoreCase(description1);
    assert getText(magCat.ELEMENT_RESTRICTED_AUDIENCE).equalsIgnoreCase(restrict);
    click(magCat.ELEMENT_CANCEL_ADD_CATEGORY_BUTTON);
    click(By.linkText(forumName1));
    click(By.linkText(topicName1));
    waitForAndGetElement(magPost.ELEMENT_POST_CONTENT.replace("${postContent}", post1));
    click(By.linkText(catName1));
    click(By.linkText(forumName2));
    waitForAndGetElement(By.linkText(topicName2));
  
    /* Delete all categories, Forum and topic before exit test case */
    click(By.linkText(catName1));
    magCat.deleteCategoryInForum(catName1);
  }

  /* CaseId: 109117 -> Export categories in case don't check any categories
   * 
   */
  @Test
  public void test03_ExportCategories_NotCheckAnyCategory(){
		String catName1 = "Category_01_109059";
		String restrict = DATA_USER2;
		String[] restricted1 = {restrict};
		String description1 = "Add new category 01_109059";
		
	    magCat.addNewCategoryInForum(catName1, "1", 1, restricted1, description1, 0, null);
	    info("Export category but not select any category");
		if(waitForAndGetElement(ELEMENT_ADMINISTRATION,10000,0) == null)
			click(ELEMENT_MORE_BUTTON);
		click(ELEMENT_ADMINISTRATION);
		click(ELEMENT_EXPORT_CATEGORY);
		waitForAndGetElement(magCat.ELEMENT_EXPORT_CATEGORY_POPUP);
		uncheck(magCat.ELEMENT_EXPORT_CHECK_ALL, 2);
		check(magCat.ELEMENT_EXPORT_CATEGORY_ONLY, 2);
		button.save();
		waitForAndGetElement(dialog.ELEMENT_POPUP_WARNING.replace("${message}", magCat.MSG_WARNING_EMPTY_CATEGORY_EXPORT));
		button.ok();
		button.cancel();
		
	    magCat.deleteCategoryInForum(catName1);
  	}
}