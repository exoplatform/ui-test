package org.exoplatform.selenium.platform.disableUser.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.exoplatform.selenium.platform.answer.AnswerCategoryManagement;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.QuestionManagement;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.TaskManagement;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.MailSuffixDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GateinPortalMemberShipsPermissionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.UserSearchOptionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.WikiMessageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.WikiRichTextDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.WikiTemplateDatabase;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.exoplatform.selenium.platform.wiki.RichTextEditor;
import org.exoplatform.selenium.platform.wiki.WikiDraftPage;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.exoplatform.selenium.platform.wiki.WikiSearch;
import org.exoplatform.selenium.platform.wiki.WikiSettingPage;
import org.testng.annotations.*;


public class Disable_User_TestConfig extends PlatformBase {

	HomePagePlatform hp;
	ActivityStream hpAct;
	ManageLogInOut magAc;
	
	PlatformPermission PlfPerm;
	
	WikiManagement wikiMg;
	WikiHomePage wHome;
	WikiDraftPage wDraft;
	WikiSearch wSearchMg;
	WikiSettingPage wSettingMg;
	
	RichTextEditor rtMode;
	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	SpaceSettingManagement setSpaceMg;
	
	TextBoxDatabase txData;
	AttachmentFileDatabase fData;
	WikiRichTextDatabase wData;
	WikiTemplateDatabase wTempData;
	WikiMessageDatabase  wMessage;
	
	UserInfoDatabase userInfoData;
	
	MailSuffixDatabase mailSuffixData;
	
	NavigationToolbar navToolBar;
	
	UserAddManagement addUserPage;
	UserProfilePage myProfile;
	UserSearchOptionDatabase userSearchOptionData;
	
	UserAndGroupManagement userAndGroup;
	GateinPortalMemberShipsPermissionDatabase portMemPermisData;
	
	ConnectionsManagement connMag;
	AnswerCategoryManagement aCatMg;
	QuestionManagement qMang;
	Button button;
	AnswerHomePage aHome;
	
	CalendarManagement cMang;
	CalendarHomePage cHome;
	EventManagement evMg;
	TaskManagement tasMg;
	
	String username;
	String firstName;
	String password;
	String lastName;
	String email;
	String membership;
	String searchUserName ;
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		info("Start setUpBeforeClass");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		hp = new HomePagePlatform(driver);
		hpAct = new ActivityStream(driver);
		
		navToolBar = new NavigationToolbar(driver);
		connMag = new ConnectionsManagement(driver);
		
		PlfPerm = new PlatformPermission(driver);
		
		wikiMg = new WikiManagement(driver);
		wHome = new WikiHomePage(driver);
		wDraft = new WikiDraftPage(driver);
		wSearchMg = new WikiSearch(driver);
		wSettingMg= new WikiSettingPage(driver);
		
		rtMode = new RichTextEditor(driver);
		spaMg = new SpaceManagement(driver);
		spaHome = new SpaceHomePage(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		
		aCatMg = new AnswerCategoryManagement(driver);
		myProfile = new UserProfilePage(driver);
		qMang = new QuestionManagement(driver);
		button = new Button(driver);
		aHome = new AnswerHomePage(driver);
		evMg = new EventManagement(driver);
		tasMg =  new TaskManagement(driver);
		
		cMang = new CalendarManagement(driver);
		cHome = new CalendarHomePage(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		fData = new AttachmentFileDatabase();
		fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		wData = new WikiRichTextDatabase();
		wData.setWikiData(wikiRichTextFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		wTempData = new WikiTemplateDatabase();
		wTempData.setWikiTemplateData(wikiTemplateFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
	
		wMessage = new WikiMessageDatabase();
		wMessage.setWikiMessageData(wikiMessageFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		userInfoData = new UserInfoDatabase();
		userInfoData.setUserInfoData(userInfoFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		mailSuffixData = new MailSuffixDatabase();
		mailSuffixData.setMailSuffixData(mailSuffixFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		addUserPage = new UserAddManagement(driver);
		
		userSearchOptionData = new UserSearchOptionDatabase();
		userSearchOptionData.setUserSearchOptionData(userSearchOptionFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		userAndGroup = new UserAndGroupManagement(driver);
		
		portMemPermisData = new GateinPortalMemberShipsPermissionDatabase();
		portMemPermisData.setData(portalPermisMemFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		info("End setUpBeforeClass");
	}
	
	@AfterClass
	public void afterTest(){
		info("Start setUpBeforeClass");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End setUpBeforeClass");
	}

	
}