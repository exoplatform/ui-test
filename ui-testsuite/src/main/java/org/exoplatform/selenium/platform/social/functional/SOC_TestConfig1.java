package org.exoplatform.selenium.platform.social.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.UserInfoDatabase;
import org.exoplatform.selenium.platform.social.MyNotificationsSetting;
import org.exoplatform.selenium.platform.social.NotificationsAdminSeting;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SOC_TestConfig1 extends PlatformBase {
	
	ManageLogInOut magAc;
	Button button;
	
	NavigationToolbar navTool;
	NotificationsAdminSeting notiAdmin;
	MyNotificationsSetting myNoti;
	
	TextBoxDatabase txData;
	Boolean resetNotifitcaiton;
	UserInfoDatabase userInfoData;
	UserAddManagement addUserPage;
	UserAndGroupManagement userAndGroup;
	
	String username;
	String firstname;
	
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
		
		userInfoData = new UserInfoDatabase();
		userInfoData.setUserInfoData(userInfoFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		resetNotifitcaiton=false;
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterMethod");
		if(resetNotifitcaiton){
			magAc.signIn(USER_ROOT,PASS_ROOT);
			navTool.goToAdminNotifications();
			notiAdmin.resetAllChangedNotifications();
		}
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterMethod");
	}

}
