package org.exoplatform.selenium.platform.wiki.functional;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.ArrayList;

import org.exoplatform.selenium.platform.ActivityStream;
import org.exoplatform.selenium.platform.ConnectionsManagement;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.administration.ManageLayout;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.TaskManagement.ColorDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.DataTestPathDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.LinksDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.UserSearchOptionDatabase;
import org.exoplatform.selenium.platform.objectdatabase.social.ActivityCommentDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.ReadThreeColDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.ReadTwoColDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.WikiMacroDatabase;
import org.exoplatform.selenium.platform.objectdatabase.wiki.WikiTemplateDatabase;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.social.UserPageBase;
import org.exoplatform.selenium.platform.social.UserProfilePage;
import org.exoplatform.selenium.platform.wiki.RichTextEditor;
import org.exoplatform.selenium.platform.wiki.SourceTextEditor;
import org.exoplatform.selenium.platform.wiki.WikiDraftPage;
import org.exoplatform.selenium.platform.wiki.WikiHomePage;
import org.exoplatform.selenium.platform.wiki.WikiLocators;
import org.exoplatform.selenium.platform.wiki.WikiManagement;
import org.exoplatform.selenium.platform.wiki.WikiPageInformation;
import org.exoplatform.selenium.platform.wiki.WikiPermission;
import org.exoplatform.selenium.platform.wiki.WikiSearch;
import org.exoplatform.selenium.platform.wiki.WikiSettingPage;
import org.exoplatform.selenium.platform.wiki.WikiValidattions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WIKI_TestConfig extends PlatformBase {
	
	ManageLogInOut magAc;
	
	NavigationToolbar navTool;
	HomePagePlatform hp;
	ActivityStream hpAct;
	ManageLayout mLayout;
	
	WikiHomePage wHome;
	WikiManagement wikiMg;
	WikiDraftPage wDraf;
	WikiValidattions wValidate;
	WikiPermission wPermission;
	WikiSettingPage wSetting;
	WikiSearch wSearch;
	WikiPageInformation wPageInfo;
	RichTextEditor richEditor;
	SourceTextEditor sourceEditor;
	
	SpaceHomePage spaHome;
	SpaceManagement spaMg;
	SpaceSettingManagement spSettingMg;
	WikiLocators wikiLoc;
	
	WikiTemplateDatabase wTempData;
	TextBoxDatabase txData;
	UserInfoDatabase userInfoData;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	UserProfilePage userProPage;
	UserPageBase userPageBase;
	ConnectionsManagement connMag;
	ActivityCommentDatabase actCommentData;
	ReadTwoColDatabase imgLinkData;
	ReadTwoColDatabase wikiWarningData;
	ReadThreeColDatabase permisGroups;
	ReadTwoColDatabase permisMem;
	ReadThreeColDatabase sourceTextEffect;
	UserSearchOptionDatabase userSearchOptionData;
	WikiMacroDatabase wikiMacroData;
	LinksDatabase lnkData;
	ColorDatabase colorData;
	
	AttachmentFileDatabase attFileData;
	DataTestPathDatabase dataTestForlderPath;
	ArrayList<String> arrayPage;
	ArrayList<String> arrayDraft;
	ArrayList<String> arrayUsers;
	ArrayList<String> arrayGroups;
	ArrayList<String> arraySpace;
	ArrayList<String> arrayTemplate;
	
	
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
		userPageBase = new UserPageBase(driver);
		wikiLoc = new WikiLocators();
		mLayout = new ManageLayout(driver);
		 
		hp = new HomePagePlatform(driver);
		connMag = new ConnectionsManagement(driver);
		hpAct = new ActivityStream(driver);
		wHome=new WikiHomePage(driver);
		wikiMg = new WikiManagement(driver);
		wDraf = new WikiDraftPage(driver);
		richEditor = new RichTextEditor(driver);
		sourceEditor = new SourceTextEditor(driver);
		wValidate = new WikiValidattions(driver);
		wPermission = new WikiPermission(driver);
		wSetting = new WikiSettingPage(driver);
		wSearch = new WikiSearch(driver);
		wPageInfo = new WikiPageInformation(driver);
		
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
		
		wTempData = new WikiTemplateDatabase();
		wTempData.setWikiTemplateData(wikiTemplateFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		permisMem = new ReadTwoColDatabase();
		permisMem.setData(permisMemFilePath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		sourceTextEffect = new ReadThreeColDatabase();
		sourceTextEffect.setData(sourceTextEffectFilePath, defaultSheet, isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		userSearchOptionData = new UserSearchOptionDatabase();
		userSearchOptionData.setUserSearchOptionData(userSearchOptionFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		wikiMacroData = new WikiMacroDatabase();
		wikiMacroData.setWikiMacroData(wikiMacroFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		lnkData = new LinksDatabase();
		lnkData.setLinkData(linkPath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);

		colorData = new ColorDatabase();
		colorData.setData(colorNamefilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		arrayPage  = new ArrayList<String>();
		arrayUsers = new ArrayList<String>();
		arraySpace = new ArrayList<String>();
		arrayTemplate = new ArrayList<String>();
		arrayGroups = new ArrayList<String>();
		arrayDraft = new ArrayList<String>();
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterMethod");
		switchToParentWindow();
		magAc.signOut();
		magAc.signIn(DATA_USER1,DATA_PASS);
		deleteAllUsers();
		deleteAllGroups();
		deleteAllWikiPages();
		deleteAllTemplates();
		deleteAllSpaces();
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterMethod");
	}
	
	/**
	 * Delete all wiki pages that are created in testing proccess
	 */
	public void deleteAllWikiPages(){
		if(arrayPage.size()>0){
			for(String title:arrayPage){
				info("Delete the page:"+title);
				driver.get(baseUrl);
				hp.goToWiki();
				wHome.deleteWiki(title);
			}
		} else if(arrayDraft.size()>0){
			for(String title:arrayDraft){
				info("Delete the page:"+title);
				driver.get(baseUrl);
				hp.goToWiki();
				wHome.goToMyDraft();
				wDraf.deleteDraft(title);
			}
		}
	}
	/**
	 * Delete all users that are created in testing process
	 */
	public void deleteAllUsers(){
		if(arrayUsers.size()>0){
			navTool.goToUsersAndGroupsManagement();
			userAndGroup.deleteAllUsers(arrayUsers);
		}
	}
	/**
	 * Delete all groups
	 */
	public void deleteAllGroups(){
		if(arrayGroups.size()>0){
			navTool.goToUsersAndGroupsManagement();
			userAndGroup.goToGroupTab();
			for(String group:arrayGroups){
				info("Delete a group");
				userAndGroup.selectGroup(group);
				userAndGroup.deleteGroup(group,true);
			}
			
		}
	}
	
	/**
	 * Delete all Spaces that are created in testing process
	 */
	public void deleteAllSpaces(){
		if(arraySpace.size()>0){
			for(String title:arraySpace){
				info("Delete the space:"+title);
				driver.get(baseUrl);
				hp.goToMySpaces();
				spaMg.searchSpace(title, "");
				spaMg.deleteSpace(title, false);
			}
		}
	}
	/**
	 * Delete all templates
	 */
	public void deleteAllTemplates(){
		if(arrayTemplate.size()>0){
			for(String template:arrayTemplate){
				info("Delete the template:"+template);
				driver.get(baseUrl);
				hp.goToWiki();
				wHome.goToWikiSettingPage();
				wSetting.searchTemplate(template);
				wSetting.deleteTemplate(template);
			}
		}
	}
}