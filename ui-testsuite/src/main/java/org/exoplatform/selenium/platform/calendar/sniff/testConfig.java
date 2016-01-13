package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.calendar.CalendarHomePage;
import org.exoplatform.selenium.platform.calendar.CalendarManagement;
import org.exoplatform.selenium.platform.calendar.EventManagement;
import org.exoplatform.selenium.platform.calendar.TaskManagement;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.AttachmentFileDatabase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.user.UserDatabase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


	public class testConfig extends PlatformBase {
		
		NavigationToolbar navTool;
		Button button;
		TextBoxDatabase txData;
		UserAddManagement addUserPage;
		UserAndGroupManagement userAndGroup;
		
		HomePagePlatform hp;
		ManageLogInOut magAc;
		CalendarHomePage cHome;
		TaskManagement task;
		CalendarManagement cMang;
		UserDatabase userData;
		EventManagement event;
		AttachmentFileDatabase fData;
		
		String fullName;
		
		@BeforeMethod
		public void setUpBeforeMethod() throws Exception{
			info("Start setUpBeforeMethod");
			initSeleniumTest();
			getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
			magAc = new ManageLogInOut(driver);
			magAc.signIn(USER_ROOT,PASS_ROOT);
			
			addUserPage = new UserAddManagement(driver);
			userAndGroup = new UserAndGroupManagement(driver);
			hp = new HomePagePlatform(driver);
			cHome= new CalendarHomePage(driver);
			task= new TaskManagement(driver);
			cMang = new CalendarManagement(driver);
			event= new EventManagement(driver);
			
			button = new Button(driver);
			txData = new TextBoxDatabase();
			txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);

			userData = new UserDatabase();
			userData.setUserData(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
			fData = new AttachmentFileDatabase();
			fData.setAttachFileData(attachmentFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
			info("End setUpBeforeMethod");
		}

		
		@AfterMethod
		public void afterMethod(){	
			driver.manage().deleteAllCookies();
			driver.quit();			
		}	
		
}