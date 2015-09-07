package org.exoplatform.selenium.platform.wiki.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.DataTestPathDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.ActivityCommentDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.ReadThreeColDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.ReadTwoColDatabase;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.exoplatform.selenium.platform.wiki.RichTextEditor;
import org.exoplatform.selenium.platform.wiki.SourceTextEditor;
import org.exoplatform.selenium.platform.wiki.WikiDraftPage;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.exoplatform.selenium.platform.wiki.WikiPermission;
import org.exoplatform.selenium.platform.wiki.WikiValidattions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WIKI_TestConfig extends PlatformBase {
	
	ManageLogInOut magAc;
	
	NavigationToolbar navTool;
	HomePagePlatform hp;
	ActivityStream hpAct;
	
	WikiHomePage wHome;
	WikiManagement wikiMg;
	WikiDraftPage wDraf;
	WikiPermission wPerm;
	WikiValidattions wValidate;
	WikiPermission wPermission;
	RichTextEditor richEditor;
	SourceTextEditor sourceEditor;
	
	SpaceHomePage spaHome;
	SpaceManagement spaMg;
	SpaceSettingManagement spSettingMg;
	
	TextBoxDatabase txData;
	UserInfoDatabase userInfoData;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	UserProfilePage userProPage;
	ConnectionsManagement connMag;
	ActivityCommentDatabase actCommentData;
	ReadTwoColDatabase imgLinkData;
	ReadTwoColDatabase wikiWarningData;
	ReadThreeColDatabase permisGroups;
	ReadTwoColDatabase permisMem;
	ReadThreeColDatabase sourceTextEffect;
	
	AttachmentFileDatabase attFileData;
	DataTestPathDatabase dataTestForlderPath;
	ArrayList<String> arrayPage;
	ArrayList<String> arrayUsers;
	
	
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
		userProPage = new UserProfilePage(driver);
		
		
		hp = new HomePagePlatform(driver);
		connMag = new ConnectionsManagement(driver);
		hpAct = new ActivityStream(driver);
		wHome=new WikiHomePage(driver);
		wikiMg = new WikiManagement(driver);
		wDraf = new WikiDraftPage(driver);
		wPerm = new WikiPermission(driver);
		richEditor = new RichTextEditor(driver);
		sourceEditor = new SourceTextEditor(driver);
		wValidate = new WikiValidattions(driver);
		wPermission = new WikiPermission(driver);
		
		spaHome = new SpaceHomePage(driver);
		spaMg = new SpaceManagement(driver);
		spSettingMg = new SpaceSettingManagement(driver);
		
		userInfoData = new UserInfoDatabase();
		userInfoData.setUserInfoData(userInfoFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		actCommentData = new ActivityCommentDatabase();
		actCommentData.setData(actCommentFilePath, defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		attFileData = new AttachmentFileDatabase();
		attFileData.setAttachFileData(attachmentFilePath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		imgLinkData = new ReadTwoColDatabase();
		imgLinkData.setData(imageLinksFilePath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		wikiWarningData = new ReadTwoColDatabase();
		wikiWarningData.setData(wikiWarningsFilePath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		permisGroups = new ReadThreeColDatabase();
		permisGroups.setData(permisGroupFilePath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		
		permisMem = new ReadTwoColDatabase();
		permisMem.setData(permisMemFilePath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		sourceTextEffect = new ReadThreeColDatabase();
		sourceTextEffect.setData(sourceTextEffectFilePath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		arrayPage  = new ArrayList<String>();
		arrayUsers = new ArrayList<String>();
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterMethod");
		deleteAllUsers();
		deleteAllWikiPages();
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterMethod");
	}
	
	/**
	 * Delete all wiki pages that are created in testing proccess
	 */
	public void deleteAllWikiPages(){
		if(arrayPage.size()>0){
			magAc.signOut();
			magAc.signIn(DATA_USER1,DATA_PASS);
			for(String title:arrayPage){
				info("Delete the page:"+title);
				driver.get(baseUrl);
				hp.goToWiki();
				wHome.deleteWiki(title);
			}
		}
	}
	/**
	 * Delete all users that are created in testing process
	 */
	public void deleteAllUsers(){
		if(arrayUsers.size()>0){
			magAc.signOut();
			magAc.signIn(DATA_USER1,DATA_PASS);
			navTool.goToUsersAndGroupsManagement();
			userAndGroup.deleteAllUsers(arrayUsers);
		}
	}
	
}