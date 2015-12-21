package org.exoplatform.selenium.platform.restrictedPageEditor;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.administration.ManageLayout;
import org.exoplatform.selenium.platform.gatein.PageCreationWizard;
import org.exoplatform.selenium.platform.gatein.PageEditor;
import org.exoplatform.selenium.platform.gatein.PortalManagePages;
import org.exoplatform.selenium.platform.gatein.PortalManageSites;
import org.exoplatform.selenium.platform.gatein.UserAddManagement;
import org.exoplatform.selenium.platform.gatein.UserAndGroupManagement;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ApplicationGateinDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ApplicationLayoutDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.ContainersDatabase;
import org.exoplatform.selenium.platform.objectdatabase.gatein.GateinPortalMemberShipsPermissionDatabase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


	public class testConfig extends PlatformBase {
		
		ManageLogInOut manageLoginOut;
		HomePagePlatform homepage;
		NavigationToolbar navigationToolbar;
		ManageLayout manageLayout;
		Button button;
		TextBoxDatabase txData;
		UserAddManagement addUserPage;
		UserAndGroupManagement userAndGroup;
		PageCreationWizard pgCreateWiz;
		ApplicationGateinDatabase appGateData;
		
		PortalManagePages portalManagePages;
		PageEditor pageEditor;
		ApplicationLayoutDatabase appLayData;
		GateinPortalMemberShipsPermissionDatabase portMemPermisData;
		ContainersDatabase contaiData;
		PortalManageSites portalManageSites;
						
		@BeforeMethod
		public void setUpBeforeMethod() throws Exception{
			info("Start setUpBeforeMethod");
			initSeleniumTest();
			getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
			manageLoginOut = new ManageLogInOut(driver);
			manageLoginOut.signIn(USER_ROOT,PASS_ROOT);
			homepage = new HomePagePlatform(driver);
			navigationToolbar = new NavigationToolbar(driver);
			manageLayout = new ManageLayout(driver);
			addUserPage = new UserAddManagement(driver);
			userAndGroup = new UserAndGroupManagement(driver);
			pgCreateWiz = new PageCreationWizard(driver);
			appGateData = new ApplicationGateinDatabase();
			
			portalManagePages = new PortalManagePages(driver);
			pageEditor = new PageEditor(driver);
			
			button = new Button(driver);
			txData = new TextBoxDatabase();
			portMemPermisData = new GateinPortalMemberShipsPermissionDatabase();
			appLayData = new ApplicationLayoutDatabase();
			
			portMemPermisData.setData(portalPermisMemFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
			appLayData.setApplicationLayoutData(appLayoutFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
			
			txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
			appGateData.setApplicationGateinData(appGateinDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
			
			contaiData = new ContainersDatabase();
			contaiData.setContainersData(containerFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
			
			portMemPermisData = new GateinPortalMemberShipsPermissionDatabase();
			portMemPermisData.setData(portalPermisMemFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
			info("End setUpBeforeMethod");
		}

		
		@AfterMethod
		public void afterMethod(){	
			driver.manage().deleteAllCookies();
			driver.quit();			
		}	
		
}