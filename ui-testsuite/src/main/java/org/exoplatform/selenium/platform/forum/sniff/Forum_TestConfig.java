package org.exoplatform.selenium.platform.forum.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement;
import org.exoplatform.selenium.platform.ecms.SiteExplorerHome;
import org.exoplatform.selenium.platform.forum.ForumCategoryManagement;
import org.exoplatform.selenium.platform.forum.ForumHomePage;
import org.exoplatform.selenium.platform.forum.ForumForumManagement;
import org.exoplatform.selenium.platform.forum.ForumTopicManagement;
import org.exoplatform.selenium.platform.forum.PrivateMessageManagement;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.testng.annotations.*;
/**
 * 
 * @author quynhpt
 * date: 03/02/2015
 */

public class Forum_TestConfig extends PlatformBase{

	HomePagePlatform hp;
	NavigationToolbar navTool;
	ForumCategoryManagement forumCatMag;
	ForumHomePage forumHP;
	ForumForumManagement forumMag;
	ManageLogInOut magAc;
	UserProfilePage myPro;

	AttachmentFileDatabase fData;
	TextBoxDatabase txData;
	
	SiteExplorerHome SEHome;
	ManageAlert magAlert;
	AnswerCategoryManagement cMang;
	Button button;
	ForumTopicManagement foTopic;
	ActivityStream aHome;
	PrivateMessageManagement msgManage;
	PageCreationWizard pagCW;
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		info("Start setUpBeforeClass");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		hp = new HomePagePlatform(driver);
		navTool = new NavigationToolbar(driver);
		forumHP = new ForumHomePage(driver);
		magAc = new ManageLogInOut(driver);
		myPro = new UserProfilePage(driver);
		SEHome = new SiteExplorerHome(driver);
		magAlert = new ManageAlert(driver);
		cMang = new AnswerCategoryManagement(driver);
		button = new Button(driver);
		foTopic = new ForumTopicManagement(driver);
		aHome = new ActivityStream(driver);
		
		pagCW = new PageCreationWizard(driver);
		msgManage = new PrivateMessageManagement(driver);
		forumCatMag = new ForumCategoryManagement(driver);
		forumMag = new ForumForumManagement(driver);

		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		fData = new AttachmentFileDatabase();
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		magAc.signIn(USER_ROOT,PASS_ROOT);
		info("End setUpBeforeClass");
	}

	@AfterClass
	public void afterClass(){
		info("Start AfterClass");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End AfterClass");
	}
	
}