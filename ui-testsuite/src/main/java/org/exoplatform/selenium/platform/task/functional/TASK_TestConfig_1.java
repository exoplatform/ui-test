package org.exoplatform.selenium.platform.task.functional;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.HomePagePlatform;
import org.exoplatform.selenium.platform.ManageLogInOut;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.objectdatabase.common.TextBoxDatabase;
import org.exoplatform.selenium.platform.objectdatabase.task.ColorDatabase;
import org.exoplatform.selenium.platform.objectdatabase.task.GroupByDatabase;
import org.exoplatform.selenium.platform.objectdatabase.task.SortByDatabase;
import org.exoplatform.selenium.platform.objectdatabase.task.WelcomeMessageDatabase;
import org.exoplatform.selenium.platform.objectdatabase.task.WorkflowDatabase;
import org.exoplatform.selenium.platform.social.SpaceHomePage;
import org.exoplatform.selenium.platform.social.SpaceManagement;
import org.exoplatform.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.selenium.platform.task.ManagementLabels;
import org.exoplatform.selenium.platform.task.ManagementProjects;
import org.exoplatform.selenium.platform.task.ManagementTasks;
import org.exoplatform.selenium.platform.task.TaskManagementHome;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TASK_TestConfig_1 extends PlatformBase {
	HomePagePlatform hp;
	ManageLogInOut magAc;
	Button button;
	
	SpaceManagement spaMg;
	SpaceHomePage spaHome;
	SpaceSettingManagement setSpaceMg;
	TaskManagementHome taskMgHome;
	ManagementProjects mgProject;
	ManagementLabels mgLabel;
	ManagementTasks mgTask;
	
	TextBoxDatabase txData;
	WelcomeMessageDatabase welcomeMesData;
	GroupByDatabase groupByData;
	SortByDatabase sortByData;
	ColorDatabase colorData;
	WorkflowDatabase flowData;
	
	@BeforeMethod
	public void setUpBeforeMethod() throws Exception{
		info("Start setUpBeforeMethod");
		initSeleniumTest();
		getDefaultUserPass(userDataFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		magAc = new ManageLogInOut(driver);
		button = new Button(driver);
		magAc.signIn(DATA_USER1, DATA_PASS);
		
		hp = new HomePagePlatform(driver);
		spaMg = new SpaceManagement(driver);
		spaHome = new SpaceHomePage(driver);
		setSpaceMg = new SpaceSettingManagement(driver);
		
		taskMgHome = new TaskManagementHome(driver);
		mgLabel = new ManagementLabels(driver);
		mgProject = new ManagementProjects(driver);
		mgTask = new ManagementTasks(driver);
		
		txData = new TextBoxDatabase();
		txData.setContentData(texboxFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlAttach);
		
		welcomeMesData = new WelcomeMessageDatabase();
		welcomeMesData.setWelcomeMessageData(welcomeMesFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		sortByData = new SortByDatabase();
		sortByData.setSortByData(sortByFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		groupByData = new GroupByDatabase();
		groupByData.setGroupByData(groupByFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		colorData = new ColorDatabase();
		colorData.setData(colorNamefilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		flowData = new WorkflowDatabase();
		flowData.setWorkflowData(workflowByFilePath,defaultSheet,isUseFile,jdbcDriver,dbUrl,user,pass,sqlUser);
		
		info("End setUpBeforeMethod");
	}

	@AfterMethod
	public void afterMethod(){
		info("Start afterMethod");
		driver.manage().deleteAllCookies();
		driver.quit();
		info("End afterMethod");
	}
}



