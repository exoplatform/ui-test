package org.exoplatform.selenium.platform.plf.functional.homepagegadgets;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.HomePageGadget;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ActionBar;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu;
import org.exoplatform.selenium.platform.ecms.contentexplorer.ContextMenu.actionType;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.ManageMember;
import org.exoplatform.selenium.platform.social.PeopleConnection;
import org.exoplatform.selenium.platform.social.PeopleProfile;
import org.exoplatform.selenium.platform.social.SpaceManagement;

/**
 * @author HaVTT
 * date: 13/02/2014
 *    
 */

public class PLF_HomepageGadgets_GettingStartedGadget extends Activity{

	ManageAccount acc; 
	HomePageGadget homeGad;
	ManageAccount magAcc;
	ManageMember magMember;
	SpaceManagement spaceMag;
	PeopleConnection peoConn; 
	PeopleProfile peoPro;
	HomePageActivity activity;
	NavigationToolbar navToolBar;
	ActionBar actBar;
	ContextMenu cMenu;
	EcmsBase ecms;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		acc = new ManageAccount(driver);
		acc.signIn(DATA_USER1, DATA_PASS);
		homeGad = new HomePageGadget(driver);
		magMember = new ManageMember(driver);
		spaceMag = new SpaceManagement(driver);
		peoConn = new PeopleConnection(driver); 
		peoPro = new PeopleProfile(driver);
		activity = new HomePageActivity(driver);
		navToolBar = new NavigationToolbar(driver);
		cMenu= new ContextMenu(driver);
		ecms = new EcmsBase(driver);
		actBar = new ActionBar(driver);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	} 
	
	/**
	 * CaseID 121271
	 * Display Getting started gadget
	 * 
	 * CaseID 121761
	 * Show default task of Getting started gadget
	 * 
	 */
	@Test(priority=0)
	public void test01_02_CheckDisplayOfGettingStartedGadget() { 
		
		driver.navigate().refresh();
		
		info("79575: check display of Getting Started Gadget on Homepage");
		waitForAndGetElement(homeGad.ELEMENT_GETTING_STARTED_GADGET_FORM);
		
		info("79578: check display of taks in Getting Started Gadget on Homepage");
		waitForAndGetElement(homeGad.ELEMENT_PROFILE_PICTURE);
		waitForAndGetElement(homeGad.ELEMENT_CONNECT_TO_COWORKERS);
		waitForAndGetElement(homeGad.ELEMENT_JOIN_A_SPACE);
		waitForAndGetElement(homeGad.ELEMENT_POST_AN_ACTIVITY);
		waitForAndGetElement(homeGad.ELEMENT_UPLOAD_A_DOCUMENT);
	}
	

	/**
	 * CaseID 121319
	 * Display Profile page from Getting started gadget
	 * 
	 * CaseID 121248
	 * Display Connections page from Getting started gadget
	 * 
	 * CaseID 121223
	 * Display All Spaces page from Getting started gadget
	 * 
	 * CaseID 121274
	 * Display Home page from Getting started gadget
	 * 
	 * CaseID 121259
	 * Display Documents page from Getting started gadget
	 */
	@Test(priority=1)
	public void test03_04_05_06_07_CheckRedirectionOfGettingStartedGadget() { 
		
		driver.navigate().refresh();
		waitForAndGetElement(homeGad.ELEMENT_GETTING_STARTED_GADGET_FORM);
		
		info("79579: Display Profile page from Getting started gadget");
		waitForAndGetElement(homeGad.ELEMENT_PROFILE_PICTURE);
		click(homeGad.ELEMENT_PROFILE_PICTURE);
		waitForAndGetElement(peoPro.ELEMENT_EDIT_MY_PROFILE_BUTTON);
		
		info("79580: Display Connections page from Getting started gadget");
		navToolBar.goToHomePage();
		waitForAndGetElement(homeGad.ELEMENT_CONNECT_TO_COWORKERS);
		click(homeGad.ELEMENT_CONNECT_TO_COWORKERS);
		waitForAndGetElement(peoConn.ELEMENT_MY_CONNECTIONS_TAB);
		
		info("79581: Display All Spaces page from Getting started gadget");
		navToolBar.goToHomePage();
		waitForAndGetElement(homeGad.ELEMENT_JOIN_A_SPACE);
		click(homeGad.ELEMENT_JOIN_A_SPACE);
		waitForAndGetElement(spaceMag.ELEMENT_ADDNEWSPACE_BUTTON);
		
		info("79583: Display Documents page from Getting started gadget");
		navToolBar.goToHomePage();
		waitForAndGetElement(homeGad.ELEMENT_UPLOAD_A_DOCUMENT);
		click(homeGad.ELEMENT_UPLOAD_A_DOCUMENT);
//		driver.navigate().refresh();
		click(ecms.ELEMENT_MORE_LINK_WITHOUT_BLOCK);
		waitForAndGetElement(ecms.ELEMENT_UPLOAD_FILE_LINK);
		
		info("79582: Display Home page from Getting started gadget");
		navToolBar.goToHomePage();
		waitForAndGetElement(homeGad.ELEMENT_POST_AN_ACTIVITY);
		click(homeGad.ELEMENT_POST_AN_ACTIVITY);
		waitForAndGetElement(homeGad.ELEMENT_GETTING_STARTED_GADGET_FORM);
		waitForAndGetElement(ELEMENT_ACTIVITY_STREAM);
	}
	
	/**
	 * CaseID 121656, 121657
	 * Perform "Add a profile picture" action from Gadget
	 * 
	 * CaseID 121658, 121659
	 * Perform "Connect to coworkers" action from Gadget
	 * 
	 * CaseID 121661, 121660
	 * Perform "Join a space" action from Gadget
	 * 
	 * CaseID 121663,121662
	 * Perform "Post an activity" action from Gadget
	 * 
	 * CaseID 121665, 121664
	 * Perform "Upload a document" action from Gadget
	 * 
	 * CaseID 121689
	 * Remove the Getting started gadget by performing all actions
	 * 
	 */
	@Test(priority=3)
	public void test08_09_10_11_12_13_CheckTaskDoneOnGettingStartedGadget() { 
		
		String driverName = "Personal Drives";
		String folderPath = "Personal Documents";
		String uploadFileName = "upload78611.pdf";
		String spaceName = "space79586";
		String activity1 = "activity 79587";
		String file = "ECMS_DMS_SE_Upload_imgfile.jpg";
		
		magMember.goToMySpacePage();
		magMember.addNewSpace(spaceName, "", "Visible", "Open", "", "");
		//Sign in by other user (mary williams)
		acc.signOut();
		acc.signIn(DATA_USER2, DATA_PASS);
		
		info("121665: Upload a document");
		selectFile(driverName,true,folderPath,"",uploadFileName);
		driver.navigate().refresh();
		waitForAndGetElement(homeGad.ELEMENT_FINISH_UPLOAD_FILE.replace("${status}", "done"));
		
		info("121661: Join a space");
		magMember.goToAllSpaces();
		magMember.joinOpenSpace(spaceName);
		magMember.goToMySpacePage();
		waitForAndGetElement(By.linkText(spaceName));
		waitForAndGetElement(ELEMENT_SPACE_IN_MY_SPACE_LIST.replace("${space}", spaceName));
		navToolBar.goToHomePage();
		waitForAndGetElement(homeGad.ELEMENT_FINISH_JOIN_TO_SPACE.replace("${status}", "done"));
		
		info("121663: Post an activity");
		addActivity(true, activity1, false,"");
		driver.navigate().refresh();
		
		info("121657: Add a profile picture");
		click(homeGad.ELEMENT_PROFILE_PICTURE);
		peoPro.goToEditProfile();
		peoPro.changeAvatar("TestData/"+file);
		navToolBar.goToHomePage();
		
		info("121659: Connect to co-workers");
		click(homeGad.ELEMENT_CONNECT_TO_COWORKERS);
		click(peoConn.ELEMENT_EVERYONE_TAB);
		peoConn.connectPeople("John Smith"); 
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS); 
		peoConn.acceptInvitation("Mary Williams");
		acc.signOut();
		acc.signIn(DATA_USER2, DATA_PASS); 
		
		info("121689: Remove gadget after all actions are done");
		//confirm progress bar
		navToolBar.goToHomePage();
		waitForAndGetElement(homeGad.ELEMENT_CLOSE_GADGET_GETTING_STARTED);
		waitForAndGetElement(homeGad.ELEMENT_INPROGRESS_COMPLETE);
		//Close the gadget
		mouseOverAndClick(homeGad.ELEMENT_CLOSE_GADGET_GETTING_STARTED);
		driver.navigate().refresh();
		waitForElementNotPresent(homeGad.ELEMENT_GETTING_STARTED_GADGET_FORM);
		
		info("Restore data");
		activity.deleteActivity(activity1);
		
		navToolBar.goToSiteExplorer();
		actBar.chooseDrive(ecms.ELEMENT_PERSONAL_DRIVE);
		actBar.actionsOnElement(uploadFileName, actionType.DELETE,true,true);

		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		magMember.goToAllSpaces();
		magMember.deleteSpace(spaceName,300000);
	}
	
	/**
	 * CaseID 121690
	 * Remove the Getting started gadget by the close button
	 * 
	 * CaseID 121359
	 * Display the close icon
	 * 
	 * CaseID 121875
	 * Verify the remove of Getting started gadget
	 * 
	 * CaseID 121272
	 * Display Getting started gadget for other users in case admin deletes his started gadget
	 * 
	 * CaseID 121835
	 * Upload a document from a drive other than "Personal Documents"
	 * 
	 */
	@Test(priority=2)
	public void test14_15_16_17_18_RemoveGettingStartedGadget() { 
		String file1 = "ECMS_Admin_ManageCategories_Display.jpg";
		
		info("121835: Upload a document from a drive other than [Personal Documents]");
		navToolBar.goToSiteExplorer();
		ecms.uploadMultiFileSerial(file1);
		navToolBar.goToHomePage();
		waitForAndGetElement(homeGad.ELEMENT_GETTING_STARTED_GADGET_FORM);
		waitForAndGetElement(homeGad.ELEMENT_FINISH_UPLOAD_FILE.replace("${status}", "done"));
		
		info("121359: confirm display [x] close button");
		driver.navigate().refresh();
		click(By.xpath("//*[@class='gadgetTitle title center']"));
		waitForAndGetElement(homeGad.ELEMENT_X_CLOSE_BUTTON_GADGET_GETTING_STARTED);
		
		info("121690: remove gadget by clicking [x] close button");
		mouseOverAndClick(homeGad.ELEMENT_X_CLOSE_BUTTON_GADGET_GETTING_STARTED);
		
		info("121875: verify gadget is removed");
		driver.navigate().refresh();
		waitForElementNotPresent(homeGad.ELEMENT_GETTING_STARTED_GADGET_FORM);
		
		info("121272: display Getting started gadget for other users in case admin deletes his started gadget");
		acc.signOut();
		acc.signIn(DATA_USER2, DATA_PASS);
		driver.navigate().refresh();
		waitForAndGetElement(homeGad.ELEMENT_GETTING_STARTED_GADGET_FORM);
		
		info("Restore data");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		navToolBar.goToSiteExplorer();
		cMenu.deleteData(By.linkText(file1));
		acc.signOut();		
	}

}
