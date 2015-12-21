package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.DownloadFileControl;
import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.administration.ChangeLanguages;
import org.exoplatform.selenium.platform.answer.AnswerHomePage;
import org.exoplatform.selenium.platform.answer.AnswerManagement;
import org.exoplatform.selenium.platform.answer.QuestionManagement;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.ecms.DocumentPreview;
import org.exoplatform.selenium.platform.forum.ForumCategoryManagement;
import org.exoplatform.selenium.platform.forum.ForumForumManagement;
import org.exoplatform.selenium.platform.forum.ForumHomePage;
import org.exoplatform.selenium.platform.forum.ForumTopicManagement;
import org.exoplatform.selenium.platform.gatein.EditorPortlet;
import org.exoplatform.selenium.platform.gatein.PageEditor;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.DataTestPathDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.LanguageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.LinksDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerDriveDatabase;
import org.exoplatform.selenium.platform.objectdatabase.ecms.SiteExplorerPathDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.ActivityCommentDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.NotificationCategoryDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.NotificationDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceRegistrationDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.SpaceVisibilityDatabase;
import org.exoplatform.selenium.platform.social.EmailNotifications;
import org.exoplatform.selenium.platform.social.IntranetNotification;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting;
import org.exoplatform.selenium.platform.social.NotificationActivity;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.UserPageBase;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.exoplatform.selenium.platform.wiki.SourceTextEditor;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SOC_TestConfig3 extends PlatformBase {
	
	ManageLogInOut magAc;
	Button button;
	
	NavigationToolbar navTool;
	NotificationsAdminSeting notiAdmin;
	MyNotificationsSetting myNoti;
	HomePagePlatform hp;
	ActivityStream hpAct;
	IntranetNotification intraNot;
	EmailNotifications emailNot;
	NotificationActivity notAct;
	ChangeLanguages changeLang;
	
	WikiHomePage wHome;
	WikiManagement wikiMg;
	SourceTextEditor sourceEditor;
	
	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	SpaceSettingManagement setSpaceMg;
	EventManagement evMg;
	
	TextBoxDatabase txData;
	UserInfoDatabase userInfoData;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	UserProfilePage userProPage;
	ConnectionsManagement connMag;
	NotificationDatabase notiIntranetData;
	NotificationDatabase notiEmailData;
	NotificationDatabase notiFormatEmailData;
	NotificationDatabase notiLabelData;
	NotificationDatabase notiMessData;
	ActivityCommentDatabase actCommentData;
	NotificationCategoryDatabase notiCatData;
	LanguageDatabase langData;
	
	LinksDatabase linkData;
	AttachmentFileDatabase attFileData;
	SiteExplorerDriveDatabase siteExDrive;
	SiteExplorerPathDatabase siteExPath;
	DataTestPathDatabase dataTestForlderPath;
	SpaceRegistrationDatabase spRegisData;
	SpaceVisibilityDatabase spVisiData;
	PageEditor pgEditor;
	EditorPortlet edPortlet;
	QuestionManagement questionMg;
	AnswerManagement answerMg;
	AnswerHomePage ansHome;
	DocumentPreview docPreview;
	DownloadFileControl downloadControl;
	ForumCategoryManagement forumCatMag;
	ForumHomePage forumHP;
	ForumForumManagement forumMag;
	ForumTopicManagement foTopic;
	UserPageBase uBase;
	
	ArrayList<String> arrayUser;
	ArrayList<String> comments;
	
	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeMethod");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		button = new Button(driver);
		magAc.signIn(USER_ROOT,PASS_ROOT);
		
		navTool = new NavigationToolbar(driver);
		notiAdmin = new NotificationsAdminSeting(driver);
		myNoti = new MyNotificationsSetting(driver);
		addUserPage = new UserAddManagement(driver);
		userAndGroup = new UserAndGroupManagement(driver);
		userProPage = new UserProfilePage(driver);
		
		
		hp = new HomePagePlatform(driver);
		connMag = new ConnectionsManagement(driver);
		hpAct = new ActivityStream(driver);
		intraNot = new IntranetNotification(driver);
		emailNot = new EmailNotifications(driver);
		wHome=new WikiHomePage(driver);
		wikiMg = new WikiManagement(driver);
		sourceEditor = new SourceTextEditor(driver);
		changeLang = new ChangeLanguages(driver);
		
		spaHome = new SpaceHomePage(driver);
		spaMg = new SpaceManagement(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		evMg = new EventManagement(driver);
		pgEditor = new PageEditor(driver);
		edPortlet = new EditorPortlet(driver);
		questionMg = new QuestionManagement(driver);
		answerMg = new AnswerManagement(driver);
		ansHome = new AnswerHomePage(driver);
		docPreview = new DocumentPreview(driver);
		downloadControl = new DownloadFileControl(driver);
		forumCatMag = new ForumCategoryManagement(driver);
		forumMag = new ForumForumManagement(driver);
		forumHP= new ForumHomePage(driver);
		foTopic = new ForumTopicManagement(driver);
		notAct = new NotificationActivity(driver);
		uBase = new UserPageBase(driver);
		
		userInfoData = new UserInfoDatabase();
		userInfoData.setUserInfoData(userInfoFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		notiIntranetData = new NotificationDatabase();
		notiIntranetData.setData(notiIntranetFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		notiEmailData = new NotificationDatabase();
		notiEmailData.setData(notiEmailFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		notiFormatEmailData = new NotificationDatabase();
		notiFormatEmailData.setData(notiFormatEmailFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		actCommentData = new ActivityCommentDatabase();
		actCommentData.setData(actCommentFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		notiCatData = new NotificationCategoryDatabase();
		notiCatData.setData(notiCatFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		notiLabelData = new NotificationDatabase();
		notiLabelData.setData(notiLabelFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		notiMessData = new NotificationDatabase();
		notiMessData.setData(notiMessageFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		linkData = new LinksDatabase();
		linkData.setLinkData(linkPath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		attFileData = new AttachmentFileDatabase();
		attFileData.setAttachFileData(attachmentFilePath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		siteExDrive = new SiteExplorerDriveDatabase();
		siteExDrive.setSiteExpDriveData(siteExpDrivePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		siteExPath = new SiteExplorerPathDatabase();
		siteExPath.setSiteExpPathData(siteExpPathPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		langData = new LanguageDatabase();
		langData.setLanguageData(languageFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlContent);
		
		spRegisData = new SpaceRegistrationDatabase();
		spRegisData.setSpaceRegistrationData(spaceRegistrationFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		spVisiData = new SpaceVisibilityDatabase();
		spVisiData.setSpaceVisibleData(spaceVisibleFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		dataTestForlderPath = new DataTestPathDatabase();
		dataTestForlderPath.setDataTestPathData(dataTestFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

		comments = new ArrayList<String>();
		arrayUser  = new ArrayList<String>();
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterMethod");
		if(arrayUser.size()>0)
			deleteUsers(arrayUser);
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterMethod");
	}
	
	/**
	 * Create many users
	 * @param number
	 *               is the number of users that want to create
	 */
	public void createNewUser(int number){
		navTool.goToAddUser();
		for(int i=0;i<number;i++){
			info("Add new a user");
			String user=getRandomString();
			String email=user+"@gmail.com";
			addUserPage.addUser(user,password, email,user,user);
			info("Add users to user's array");
			arrayUser.add(user);
			info("User"+i+": "+user);
		}
	}
	
	/**
	 * Delete many users
	 * @param users
	 *             is array of users
	 */
	public void deleteUsers(ArrayList<String> users){
		info("Delete all new users");
		switchToParentWindow();
		magAc.signIn(USER_ROOT,PASS_ROOT);
		navTool.goToUsersAndGroupsManagement();
		for(int i=0;i<users.size();i++){
			info("Delete user:"+users.get(i));
			userAndGroup.deleteUser(users.get(i));
			info("Delete user:"+users.get(i)+" successfully");
		}
	}

}
